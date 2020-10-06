<!-- SPDX-License-Identifier: CC-BY-4.0 -->
<!-- Copyright Contributors to the ODPi Egeria project. -->

# Configuring the Open Metadata View Services (OMVSs)

The [Open Metadata View Services (OMVSs)](../../../view-services) provide task oriented, domain-specific services
for user interfaces to integrate with open metadata. 

They run in a [View Server](../concepts/view-server.md), they have a REST API. View servers are configured with a remote 
OMAG Server name and platform root URL, which are used to issue downstream rest calls to another OMAG Server, for example to 
call [Open Metadata Access Services (OMASs)](../../../access-services) to work with open metadata content from the metadata repositories.   

## List the different types of view services

It is possible to list all of the available view services that are registered with the [OMAG Server Platform](../concepts/omag-server-platform.md)
using the following command.

```
GET {serverURLRoot}/open-metadata/platform-services/users/{adminUserId}/server-platform/registered-services/view-services
```
The result looks something like this:

```json
{
    "class": "RegisteredOMAGServicesResponse",
    "relatedHTTPCode": 200,
    "services": [
        {
            "serviceName": "Glossary Author",
            "serviceURLMarker": "glossary-author",
            "serviceDescription": "View Service for glossary authoring.",
            "serviceWiki": "https://odpi.github.io/egeria/open-metadata-implementation/access-services/subject-area/"
        },
        {
            "serviceName": "Repository Explorer",
            "serviceURLMarker": "rex",
            "serviceDescription": "Explore open metadata instances.",
            "serviceWiki": "https://odpi.github.io/egeria/open-metadata-implementation/access-services/subject-area/"
        },
        {
            "serviceName": "Type Explorer",
            "serviceURLMarker": "type-explorer",
            "serviceDescription": "Explore the open metadata types.",
            "serviceWiki": "https://odpi.github.io/egeria/open-metadata-implementation/access-services/subject-area/"
        }
    ]
}

```
These view services are available to configure either all together or individually.


## Configure all view services

To enable all of the view services with the supplied remote OMAG Server name {remoteServerName} and platform root url {remoteServerURLRoot}, use the following command.

```
POST {serverURLRoot}/open-metadata/admin-services/users/{adminUserId}/servers/{serverName}/view-services
```
With body : 
```
{ 
    "class":"ViewServiceConfig",
    "omagserverName":{remoteServerName},
    "omagserverPlatformRootURL":{remoteServerURLRoot}"
}
```

Alternatively, each service can be configured individually with the following command:

```
POST {serverURLRoot}/open-metadata/admin-services/users/{adminUserId}/servers/{serverName}/view-services/{serviceURLMarker}
```
The service URL marker for each service is shown in the list above.

In both cases, it is possible to pass a list of properties to the view service
that controls the behavior of each view service.
These are sent in the request body.
More details of which properties are supported
are documented with each view service.

## Disable the view services


The view services can be disabled with the following command.
This also disables the enterprise repository services since they
are not being used.

```
DELETE {serverURLRoot}/open-metadata/admin-services/users/{adminUserId}/servers/{serverName}/view-services
```

## Retrieve the current configuration for the view services

It is possible to retrieve the current configuration.

```
GET {serverURLRoot}/open-metadata/admin-services/users/{adminUserId}/servers/{serverName}/view-services/configuration
```

it is then possible to make changes to the configuration and save it back:

```
POST {serverURLRoot}/open-metadata/admin-services/users/{adminUserId}/servers/{serverName}/view-services/configuration
```

----
License: [CC BY 4.0](https://creativecommons.org/licenses/by/4.0/),
Copyright Contributors to the ODPi Egeria project.