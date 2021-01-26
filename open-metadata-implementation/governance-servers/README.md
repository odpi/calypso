<!-- SPDX-License-Identifier: CC-BY-4.0 -->
<!-- Copyright Contributors to the ODPi Egeria project. -->

# Open Metadata Governance Server Services

The open metadata governance server services provide the primary function for 
Egeria's [governance servers](../admin-services/docs/concepts/governance-server-types.md).
They add services that either help to integrate third party technology, or
make use of open metadata to create new services.

More information about Governance Servers in general can be found in the [Administration Guide](../../a).
However each of the services below provide the principle service
for a particular type of governance server.

* **[data-engine-proxy-services](data-engine-proxy-services)** - bridge between data engines and the
    [Data Engine OMAS](../access-services/data-engine).

* **[integration-daemon-services](integration-daemon-services)** - metadata exchange with third party tools.

* **[open-lineage-services](open-lineage-services)** - provides a historic reporting warehouse for lineage.


----
Return to [open-metadata-implementation](..).

----
License: [CC BY 4.0](https://creativecommons.org/licenses/by/4.0/),
Copyright Contributors to the ODPi Egeria project.