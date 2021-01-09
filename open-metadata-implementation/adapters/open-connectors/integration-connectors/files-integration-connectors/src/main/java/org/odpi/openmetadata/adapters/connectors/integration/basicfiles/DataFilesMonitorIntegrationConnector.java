/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */

package org.odpi.openmetadata.adapters.connectors.integration.basicfiles;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.odpi.openmetadata.accessservices.datamanager.metadataelements.DataFileElement;
import org.odpi.openmetadata.accessservices.datamanager.metadataelements.FileFolderElement;
import org.odpi.openmetadata.accessservices.datamanager.properties.ArchiveProperties;
import org.odpi.openmetadata.accessservices.datamanager.properties.DataFileProperties;
import org.odpi.openmetadata.accessservices.datamanager.properties.TemplateProperties;
import org.odpi.openmetadata.adapters.connectors.integration.basicfiles.ffdc.BasicFilesIntegrationConnectorsAuditCode;
import org.odpi.openmetadata.adapters.connectors.integration.basicfiles.ffdc.BasicFilesIntegrationConnectorsErrorCode;
import org.odpi.openmetadata.adapters.connectors.integration.basicfiles.ffdc.exception.FileException;
import org.odpi.openmetadata.frameworks.connectors.ffdc.ConnectorCheckedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.*;


/**
 * DataFilesMonitorIntegrationConnector monitors a file directory and catalogues the files it finds.
 */
public class DataFilesMonitorIntegrationConnector extends BasicFilesMonitorIntegrationConnectorBase
{
    private static final Logger log = LoggerFactory.getLogger(DataFilesMonitorIntegrationConnector.class);

    private String templateGUID = null;

    /**
     * Set up the file listener class - this is implemented by the subclasses
     *
     * @return file alteration listener implementation
     */
    @Override
    FileAlterationListenerAdaptor getListener()
    {
        return new FileCataloguingListener(this);
    }


    /**
     * Inner class for the directory listener logic
     */
    class FileCataloguingListener extends FileAlterationListenerAdaptor
    {
        private DataFilesMonitorIntegrationConnector connector;

        FileCataloguingListener(DataFilesMonitorIntegrationConnector connector)
        {
            this.connector = connector;
        }

        @Override
        public void onFileCreate(File file)
        {
            final String methodName = "onFileCreate";

            log.debug("File created: " + file.getName());
            connector.catalogFile(file, methodName);
        }

        @Override
        public void onFileDelete(File file)
        {
            final String methodName = "onFileDelete";

            log.debug("File deleted: " + file.getName());
            connector.archiveFileInCatalog(file, null, methodName);
        }

        @Override
        public void onFileChange(File file)
        {
            log.debug("File changed: " + file.getName());
            connector.updateFileInCatalog(file);
        }

        @Override
        public void onDirectoryCreate(File directory)
        {
            final String methodName = "onDirectoryCreate";

            log.debug("Folder created: " + directory.getName());
            initiateDirectoryMonitoring(directory, methodName);
        }

        @Override
        public void onDirectoryDelete(File directory)
        {
            final String methodName = "onDirectoryDelete";

            log.debug("Folder deleted: " + directory.getName());
            stopDirectoryMonitoring(directory.getName(), methodName);
        }
    }


    /**
     * Requests that the connector does a comparison of the metadata in the third party technology and open metadata repositories.
     * Refresh is called when the integration connector first starts and then at intervals defined in the connector's configuration
     * as well as any external REST API calls to explicitly refresh the connector.
     *
     * This method performs two sweeps.  It first retrieves the files in the directory and validates that are in the
     * catalog - adding or updating them if necessary.  The second sweep is to ensure that all of the assets catalogued
     * in this directory actually exist on the file system.
     *
     * @throws ConnectorCheckedException there is a problem with the connector.  It is not able to refresh the metadata.
     */
    @Override
    public void refresh() throws ConnectorCheckedException
    {
        final String methodName = "refresh";

        File directory = this.getRootDirectoryFile();

        if (directory != null)
        {
            /*
             * Sweep one - cataloguing all files
             */
            File[] filesArray = directory.listFiles();

            if (filesArray != null)
            {
                for (File file : filesArray)
                {
                    if (file != null)
                    {
                        this.catalogFile(file, methodName);
                    }
                }
            }

            /*
             * Sweep two - ensuring all catalogued files still exist.  Notice that if the folder does not exist, it is
             * ignored.  It will be dynamically created when a new file is added.
             */
            try
            {
                FileFolderElement folder = super.getFolderElement();

                if (folder != null)
                {
                    int startFrom = 0;
                    int pageSize  = 100;

                    List<DataFileElement> cataloguedFiles = context.getFolderFiles(folder.getElementHeader().getGUID(), startFrom, pageSize);

                    while ((cataloguedFiles != null) && (! cataloguedFiles.isEmpty()))
                    {
                        for (DataFileElement dataFile : cataloguedFiles)
                        {
                            if (dataFile != null)
                            {
                                if ((dataFile.getElementHeader() != null) && (dataFile.getElementHeader().getGUID() != null) &&
                                    (dataFile.getDataFileProperties() != null) && (dataFile.getDataFileProperties().getQualifiedName() != null))
                                {
                                    File file = new File(dataFile.getDataFileProperties().getQualifiedName());

                                    if (! file.exists())
                                    {
                                        this.archiveFileInCatalog(file, dataFile, methodName);
                                    }
                                }
                                else
                                {
                                    if (auditLog != null)
                                    {
                                        auditLog.logMessage(methodName,
                                                            BasicFilesIntegrationConnectorsAuditCode.BAD_FILE_ELEMENT.getMessageDefinition(connectorName,
                                                                                                                                           dataFile.toString()));
                                    }
                                }
                            }
                        }

                        startFrom = startFrom + cataloguedFiles.size();
                        cataloguedFiles = context.getFolderFiles(folder.getElementHeader().getGUID(), startFrom, pageSize);
                    }
                }
            }
            catch (Exception error)
            {
                if (auditLog != null)
                {
                    auditLog.logException(methodName,
                                          BasicFilesIntegrationConnectorsAuditCode.UNEXPECTED_EXC_DATA_FILE_UPDATE.getMessageDefinition(error.getClass().getName(),
                                                                                                                                        connectorName,
                                                                                                                                        directory.getAbsolutePath(),
                                                                                                                                        error.getMessage()),
                                          error);

                }

                throw new FileException(
                        BasicFilesIntegrationConnectorsErrorCode.UNEXPECTED_EXC_DATA_FILE_UPDATE.getMessageDefinition(error.getClass().getName(),
                                                                                                                      connectorName,
                                                                                                                      directory.getAbsolutePath(),
                                                                                                                      error.getMessage()),
                        error.getClass().getName(),
                        methodName,
                        error,
                        directory.getAbsolutePath());
            }
        }
    }


    /**
     * Create a catalog entry for a specific file.
     *
     * @param file Java File accessor
     * @param methodName calling method
     */
    private void catalogFile(File   file,
                             String methodName)
    {
        try
        {
            DataFileElement cataloguedElement = context.getFileByPathName(file.getAbsolutePath());

            if (cataloguedElement == null)
            {
                if (templateQualifiedName == null)
                {
                    DataFileProperties properties = new DataFileProperties();

                    properties.setQualifiedName(file.getAbsolutePath());
                    properties.setDisplayName(file.getName());
                    properties.setModifiedTime(new Date(file.lastModified()));

                    List<String> guids = context.addDataFileToCatalog(properties, null);

                    if ((guids != null) && (! guids.isEmpty()) && (auditLog != null))
                    {
                        auditLog.logMessage(methodName,
                                            BasicFilesIntegrationConnectorsAuditCode.DATA_FILE_CREATED.getMessageDefinition(connectorName,
                                                                                                                            properties.getQualifiedName(),
                                                                                                                            guids.get(guids.size() - 1)));
                    }
                }
                else
                {
                    if (templateGUID == null)
                    {
                        DataFileElement templateElement = context.getFileByPathName(templateQualifiedName);

                        if (templateElement != null)
                        {
                            if ((templateElement.getElementHeader() != null) && (templateElement.getElementHeader().getGUID() != null))
                            {
                                templateGUID = templateElement.getElementHeader().getGUID();
                            }
                            else
                            {
                                if (auditLog != null)
                                {
                                    auditLog.logMessage(methodName,
                                                        BasicFilesIntegrationConnectorsAuditCode.BAD_FILE_ELEMENT.getMessageDefinition(connectorName,
                                                                                                                                       templateElement.toString()));
                                }
                            }
                        }
                        else
                        {
                            if (auditLog != null)
                            {
                                auditLog.logMessage(methodName,
                                                    BasicFilesIntegrationConnectorsAuditCode.MISSING_TEMPLATE.getMessageDefinition(connectorName,
                                                                                                                                   templateQualifiedName));
                            }
                        }
                    }

                    if (templateGUID != null)
                    {
                        TemplateProperties properties = new TemplateProperties();

                        properties.setQualifiedName(file.getAbsolutePath());
                        properties.setDisplayName(file.getName());

                        List<String> guids = context.addDataFileToCatalogFromTemplate(templateGUID, properties);

                        if ((guids != null) && (! guids.isEmpty()) && (auditLog != null))
                        {
                            auditLog.logMessage(methodName,
                                                BasicFilesIntegrationConnectorsAuditCode.DATA_FILE_CREATED_FROM_TEMPLATE.getMessageDefinition(connectorName,
                                                                                                                                              properties.getQualifiedName(),
                                                                                                                                              guids.get(guids.size() - 1),
                                                                                                                                              templateQualifiedName,
                                                                                                                                              templateGUID));
                        }
                    }
                }
            }
        }
        catch (Exception error)
        {
            if (auditLog != null)
            {
                auditLog.logException(methodName,
                                      BasicFilesIntegrationConnectorsAuditCode.UNEXPECTED_EXC_DATA_FILE_UPDATE.getMessageDefinition(error.getClass().getName(),
                                                                                                                                    connectorName,
                                                                                                                                    file.getAbsolutePath(),
                                                                                                                                    error.getMessage()),
                                      error);

            }
        }
    }


    /**
     * The file no longer exists so this method updates the metadata catalog. This may be a delete or an archive action
     * depending on the setting of the allowCatalogDelete configuration property.
     *
     * @param file Java file access object
     * @param retrievedElement catalogued element
     * @param methodName calling method
     */
    private void archiveFileInCatalog(File            file,
                                      DataFileElement retrievedElement,
                                      String          methodName)
    {

        try
        {
            DataFileElement cataloguedElement = retrievedElement;

            if (cataloguedElement == null)
            {
                cataloguedElement = context.getFileByPathName(file.getAbsolutePath());
            }

            if (cataloguedElement == null)
            {
                return;
            }

            if ((cataloguedElement.getElementHeader() != null) && (cataloguedElement.getElementHeader().getGUID() != null) &&
                (cataloguedElement.getDataFileProperties() != null) && (cataloguedElement.getDataFileProperties().getQualifiedName() != null))
            {
                if (allowCatalogDelete)
                {
                    context.deleteDataFileFromCatalog(cataloguedElement.getElementHeader().getGUID(),
                                                      cataloguedElement.getDataFileProperties().getQualifiedName());

                    if (auditLog != null)
                    {
                        auditLog.logMessage(methodName,
                                            BasicFilesIntegrationConnectorsAuditCode.DATA_FILE_DELETED.getMessageDefinition(connectorName,
                                                                                                                            cataloguedElement.getDataFileProperties().getQualifiedName(),
                                                                                                                            cataloguedElement.getElementHeader().getGUID()));
                    }
                }
                else
                {
                    ArchiveProperties archiveProperties = new ArchiveProperties();

                    archiveProperties.setArchiveDate(new Date());
                    archiveProperties.setArchiveProcess(connectorName);

                    context.archiveDataFileInCatalog(cataloguedElement.getElementHeader().getGUID(), archiveProperties);

                    if (auditLog != null)
                    {
                        auditLog.logMessage(methodName,
                                            BasicFilesIntegrationConnectorsAuditCode.DATA_FILE_ARCHIVED.getMessageDefinition(connectorName,
                                                                                                                             cataloguedElement.getDataFileProperties().getQualifiedName(),
                                                                                                                             cataloguedElement.getElementHeader().getGUID()));
                    }
                }
            }
            else
            {
                if (auditLog != null)
                {
                    auditLog.logMessage(methodName,
                                        BasicFilesIntegrationConnectorsAuditCode.BAD_FILE_ELEMENT.getMessageDefinition(connectorName,
                                                                                                                       cataloguedElement.toString()));
                }
            }
        }
        catch (Exception error)
        {
            if (auditLog != null)
            {
                auditLog.logException(methodName,
                                      BasicFilesIntegrationConnectorsAuditCode.UNEXPECTED_EXC_DATA_FILE_UPDATE.getMessageDefinition(error.getClass().getName(),
                                                                                                                                    connectorName,
                                                                                                                                    file.getAbsolutePath(),
                                                                                                                                    error.getMessage()),
                                      error);
            }
        }
    }


    /**
     * Update the last modified time in the catalogued asset for the file.
     *
     * @param file file object from operating system
     */
    private void updateFileInCatalog(File   file)
    {
        final String methodName = "updateFileInCatalog";
        try
        {
            DataFileElement dataFileInCatalog = context.getFileByPathName(file.getAbsolutePath());

            if (dataFileInCatalog != null)
            {
                if ((dataFileInCatalog.getElementHeader() != null) && (dataFileInCatalog.getElementHeader().getGUID() != null) &&
                            (dataFileInCatalog.getDataFileProperties() != null) && (dataFileInCatalog.getDataFileProperties().getQualifiedName() != null))
                {
                    DataFileProperties properties = new DataFileProperties();

                    properties.setModifiedTime(new Date(file.lastModified()));
                    properties.setQualifiedName(dataFileInCatalog.getDataFileProperties().getQualifiedName());

                    context.updateDataFileInCatalog(dataFileInCatalog.getElementHeader().getGUID(), true, properties);

                    if (auditLog != null)
                    {
                        auditLog.logMessage(methodName,
                                            BasicFilesIntegrationConnectorsAuditCode.DATA_FILE_UPDATED.getMessageDefinition(connectorName,
                                                                                                                            dataFileInCatalog.getDataFileProperties().getQualifiedName(),
                                                                                                                            dataFileInCatalog.getElementHeader().getGUID()));
                    }
                }
                else
                {
                    if (auditLog != null)
                    {
                        auditLog.logMessage(methodName,
                                            BasicFilesIntegrationConnectorsAuditCode.BAD_FILE_ELEMENT.getMessageDefinition(connectorName,
                                                                                                                           dataFileInCatalog.toString()));
                    }
                }
            }
            else
            {
                this.catalogFile(file, methodName);
            }
        }
        catch (Exception error)
        {
            if (auditLog != null)
            {
                auditLog.logException(methodName,
                                      BasicFilesIntegrationConnectorsAuditCode.UNEXPECTED_EXC_DATA_FILE_UPDATE.getMessageDefinition(error.getClass().getName(),
                                                                                                                                    connectorName,
                                                                                                                                    file.getAbsolutePath(),
                                                                                                                                    error.getMessage()),
                                      error);
            }
        }
    }
}
