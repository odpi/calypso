/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.userinterface.uichassis.springboot.service;


import org.odpi.openmetadata.frameworks.connectors.ffdc.InvalidParameterException;
import org.odpi.openmetadata.frameworks.connectors.ffdc.PropertyServerException;
import org.odpi.openmetadata.governanceservers.openlineage.client.OpenLineageClient;
import org.odpi.openmetadata.governanceservers.openlineage.ffdc.OpenLineageException;
import org.odpi.openmetadata.governanceservers.openlineage.model.LineageVertex;
import org.odpi.openmetadata.governanceservers.openlineage.model.LineageVerticesAndEdges;
import org.odpi.openmetadata.governanceservers.openlineage.model.Scope;
import org.odpi.openmetadata.userinterface.uichassis.springboot.beans.Edge;
import org.odpi.openmetadata.userinterface.uichassis.springboot.beans.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class responsibility is to interact with Open Lineage Services(OLS), process the returned response and return it in a format understood by view
 */
@Service
public class OpenLineageService {

    public static final String EDGES_LABEL = "edges";
    public static final String NODES_LABEL = "nodes";
    private static final String TABULAR_COLUMN = "TabularColumn";
    private static final String TABULAR_SCHEMA_TYPE = "TabularSchemaType";
    private static final String SUB_PROCESS = "subProcess";
    private static final String VERTEX_INSTANCE_PROPQUALIFIED_NAME = "vertex--InstancePropqualifiedName";
    private static final String TRANSFORMATION_PROJECT = "transformationProject";
    private static final String TRANSFORMATION_PROJECT_NAME_PATTERN = "transformation_project\\)=(.*?)::";
    private final OpenLineageClient openLineageClient;
    private static final Logger LOG = LoggerFactory.getLogger(OpenLineageService.class);

    /**
     * @param openLineageClient client to connect to open lineage services
     */
    @Autowired
    public OpenLineageService(OpenLineageClient openLineageClient) {
        this.openLineageClient = openLineageClient;
    }

    /**
     * @param userId           id of the user triggering the request
     * @param guid             unique identifier if the asset
     * @param includeProcesses if true includes processes in the response
     * @return map of nodes and edges describing the ultimate sources for the asset
     */
    public Map<String, List> getUltimateSource(String userId,
                                               String guid,
                                               boolean includeProcesses) {
        try {
            LineageVerticesAndEdges response = openLineageClient.lineage(userId, Scope.ULTIMATE_SOURCE, guid, "", includeProcesses);
            return processResponse(response,guid);
        } catch (InvalidParameterException | PropertyServerException | OpenLineageException e) {
            LOG.error("Cannot get ultimate source lineage for guid {}", guid);
            throw new RuntimeException("ultimate source lineage error", e);
        }
    }


    /**
     * @param userId           id of the user triggering the request
     * @param guid             unique identifier if the asset
     * @param includeProcesses if true includes processes in the response
     * @return map of nodes and edges describing the end to end flow
     */
    public Map<String, List> getEndToEndLineage(String userId,
                                                String guid,
                                                boolean includeProcesses) {
        try {
            LineageVerticesAndEdges response =  openLineageClient.lineage(userId, Scope.END_TO_END, guid, "", includeProcesses);
            return processResponse(response,guid);
        } catch (InvalidParameterException | PropertyServerException | OpenLineageException e) {
            LOG.error("Cannot get end to end lineage for guid {}", guid);
            throw new RuntimeException("end2end lineage error", e);
        }
    }

    /**
     * @param userId           id of the user triggering the request
     * @param guid             unique identifier if the asset
     * @param includeProcesses if true includes processes in the response
     * @return map of nodes and edges describing the ultimate destinations of the asset
     */
    public Map<String, List> getUltimateDestination(String userId,
                                                    String guid,
                                                    boolean includeProcesses) {
        try {
            LineageVerticesAndEdges response = openLineageClient.lineage(userId, Scope.ULTIMATE_DESTINATION, guid, "",
                    includeProcesses);
            return processResponse(response,guid);
        } catch (InvalidParameterException | PropertyServerException | OpenLineageException e) {
            LOG.error("Cannot get ultimate destination lineage for guid {}", guid);
            throw new RuntimeException("ultimate destination lineage error", e);
        }

    }

    /**
     * @param userId           id of the user triggering the request
     * @param guid             unique identifier if the asset
     * @param includeProcesses if true includes processes in the response
     * @return map of nodes and edges describing the glossary terms linked to the asset
     */
    public Map<String, List> getVerticalLineage(String userId,
                                                String guid,
                                                boolean includeProcesses) {
        try {
            LineageVerticesAndEdges response =  openLineageClient.lineage(userId, Scope.VERTICAL, guid, "", includeProcesses);
            return processResponse(response,guid);
        } catch (InvalidParameterException | PropertyServerException | OpenLineageException e) {
            LOG.error("Cannot get glossary lineage for guid {}", guid);
            throw new RuntimeException("glossary lineage error", e);
        }

    }

    /**
     * @param userId           id of the user triggering the request
     * @param guid             unique identifier if the asset
     * @param includeProcesses if true includes processes in the response
     * @return map of nodes and edges describing the ultimate sources and destinations of the asset
     */
    public Map<String, List> getSourceAndDestination(String userId,
                                                     String guid,
                                                     boolean includeProcesses) {
        try {
            LineageVerticesAndEdges response = openLineageClient.lineage(userId, Scope.SOURCE_AND_DESTINATION, guid, "",
                    includeProcesses);
            return processResponse(response,guid);
        } catch (InvalidParameterException | PropertyServerException | OpenLineageException e) {
            LOG.error("Cannot get source and destination lineage for guid {}", guid);
            throw new RuntimeException("source and destination lineage error ", e);
        }

    }

    /**
     * @param response string returned from Open Lineage Services to be processed
     * @return map of nodes and edges describing the end to end flow
     */
    private Map<String, List> processResponse(LineageVerticesAndEdges response, String guid) {
        Map<String, List> graphData = new HashMap<>();
        List<Edge> edges = new ArrayList<>();
        List<Node> nodes = new ArrayList<>();

        LOG.debug("Received response from open lineage service: {}", response);
        if (response == null || CollectionUtils.isEmpty(response.getLineageVertices())) {
            graphData.put(EDGES_LABEL, edges);
            graphData.put(NODES_LABEL, nodes);
        }

        edges = Optional.ofNullable(response).map(LineageVerticesAndEdges::getLineageEdges)
                .map(Collection::stream)
                .orElseGet(Stream::empty)
                .map(e -> new Edge(e.getSourceNodeID(),
                        e.getDestinationNodeID()))
                .collect(Collectors.toList());

        nodes = Optional.ofNullable(response).map(LineageVerticesAndEdges::getLineageVertices)
                .map(Collection::stream)
                .orElseGet(Stream::empty)
                .map(this::createNode)
                .collect(Collectors.toList());

        List<Node> startList = nodes.stream()
                .filter(n -> n.getId().equals(guid))
                .collect(Collectors.toList());

        if(startList.size() > 0) {
            setNodesLevel(startList, new ArrayList<>(nodes), new ArrayList<>(edges));
        }

        if (isQueriedNodeTabularColumn(guid, nodes)) {
            adjustGraphForTabularColumn(edges, nodes);
        }

        nodes.stream().filter(node -> SUB_PROCESS.equals(node.getGroup())).forEach(this::extractTransformationProjectFromQualifiedName);

        graphData.put(EDGES_LABEL, edges);
        graphData.put(NODES_LABEL, nodes);

        return graphData;
    }

    private void extractTransformationProjectFromQualifiedName(Node node) {
        String qualifiedName = node.getProperties().get(VERTEX_INSTANCE_PROPQUALIFIED_NAME);
        if (qualifiedName == null) {
            return;
        }
        Pattern pattern = Pattern.compile(TRANSFORMATION_PROJECT_NAME_PATTERN, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(qualifiedName);
        if (matcher.find()) {
            node.getProperties().put(TRANSFORMATION_PROJECT, matcher.group(1));
        }
        node.getProperties().remove(VERTEX_INSTANCE_PROPQUALIFIED_NAME);
    }

    /**
     * sets the level field for the nodes, in order to be displayed on levels
     * Stars from a start list of nodes and sets a the level+1 for the nodes on the ends of "to" edges and
     * legel -1 for the nodes from the "from" end of it's edges
     * once an edge or node is processed is removed from the list.
     *
     * @param startNodes the starting nodes
     * @param listNodes the list of nodes
     * @param listEdges the list of edges
     */
    private void setNodesLevel(List<Node> startNodes, List<Node> listNodes, List<Edge> listEdges) {

        ArrayList<Node> newStartNodes = new ArrayList<>();

        ListIterator<Edge> edgeListIterator = listEdges.listIterator();

        while (edgeListIterator.hasNext()) {
            Edge e = edgeListIterator.next();
            for ( Node node: startNodes ){
                if ( node.getId().equals(e.getFrom()) ) {

                    listNodes.stream()
                            .filter( n -> n.getLevel() == 0 && n.getId().equals( e.getTo() ))
                            .forEach( item -> {
                                item.setLevel( node.getLevel() + 1 );
                                newStartNodes.add(item);
                                edgeListIterator.remove();
                            });

                } else if ( node.getId().equals(e.getTo()) ) {

                    listNodes.stream()
                            .filter( n -> n.getLevel() == 0 && n.getId().equals( e.getFrom() ))
                            .forEach( item -> {
                                item.setLevel( node.getLevel() - 1 );
                                newStartNodes.add(item);
                                edgeListIterator.remove();
                            });

                }
                listNodes.removeAll(newStartNodes);
            }

        }

        if(newStartNodes.size() > 0 && listEdges.size() > 0){
            setNodesLevel(newStartNodes , listNodes, listEdges );
        }
    }

    /**
     * This method will create a new node in ui specific format based on the properties of the currentNode to be processed
     *
     * @param currentNode current node to be processed
     * @return the node in the format to be understand by the ui
     */
    private Node createNode(LineageVertex currentNode) {
        String displayName = currentNode.getDisplayName();
        Node node = new Node(currentNode.getNodeID(), displayName);
        node.setGroup(currentNode.getNodeType());
        node.setProperties(currentNode.getProperties());
        return node;
    }

    private boolean isQueriedNodeTabularColumn(String guid, List<Node> nodes) {
        return nodes.stream().anyMatch(node -> node.getId().equals(guid) && node.getGroup().equals(TABULAR_COLUMN));
    }

    /**
    * For vertical lineage the TabularSchemaType node should be removed when querying a TabularColumn
     * so the relationship appears between the TabularColumn and DataFile
     *
     * @param edges the edges of the graph
     * @param nodes nodes of the graph
    * */
    private void adjustGraphForTabularColumn(List<Edge> edges, List<Node> nodes) {
        List<Edge> edgesToRemove = new ArrayList<>();
        List<Node> nodesToRemove = new ArrayList<>();
        for (Node node : nodes) {
            if (node.getGroup().equals(TABULAR_SCHEMA_TYPE)) {
                nodesToRemove.add(node);
                String newFrom = "";
                List<String> newTo = new ArrayList<>();
                for (Edge edge : edges) {
                    if (edge.getTo().equals(node.getId())) {
                        edgesToRemove.add(edge);
                        newFrom = edge.getFrom();
                    }
                }
                for (Edge edge : edges) {
                    if (edge.getFrom().equals(node.getId())) {
                        edgesToRemove.add(edge);
                        newTo.add(edge.getTo());
                    }
                }
                for (String newTos : newTo) {
                    edges.add(new Edge(newFrom, newTos));
                }

            }
        }
        nodes.removeAll(nodesToRemove);
        edges.removeAll(edgesToRemove);
    }

}