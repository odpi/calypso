/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.userinterface.uichassis.springboot.service.graphrules;

import org.odpi.openmetadata.userinterface.uichassis.springboot.beans.Edge;
import org.odpi.openmetadata.userinterface.uichassis.springboot.beans.Graph;
import org.odpi.openmetadata.userinterface.uichassis.springboot.beans.Node;

import java.util.List;

public class InvertEdgeRule implements Rule {
    private String edgeType;
    private String sourceNodeType;
    private String destinationNodeType;

    public String getEdgeType() {
        return edgeType;
    }

    public void setEdgeType(String edgeType) {
        this.edgeType = edgeType;
    }

    public String getSourceNodeType() {
        return sourceNodeType;
    }

    public void setSourceNodeType(String sourceNodeType) {
        this.sourceNodeType = sourceNodeType;
    }

    public String getDestinationNodeType() {
        return destinationNodeType;
    }

    public void setDestinationNodeType(String destinationNodeType) {
        this.destinationNodeType = destinationNodeType;
    }


    /** Inverts the edge direction based on the configured parameters
     * @param graph
     * @param queriedNodeGUID
     */
    public void apply(Graph graph, String queriedNodeGUID) {
        for (Edge edge : graph.getEdges()) {
            List<Node> nodes = graph.getNodes();
            if (edgeType != null && edgeType.equals(edge.getLabel())) {
                invertEdge(nodes, edge);
            }
        }
    }

    private void invertEdge(List<Node> nodes, Edge edge) {
        if (this.getSourceNodeType() != null && this.getDestinationNodeType() != null) {
            applyRuleWithBothNodeTypes(nodes, edge);
        } else if (this.getSourceNodeType() != null && this.getDestinationNodeType() == null) {
            applyRuleWithOneNodeType(nodes, edge, edge.getFrom(), this.getSourceNodeType());
        } else if (this.getSourceNodeType() == null && this.getDestinationNodeType() != null) {
            applyRuleWithOneNodeType(nodes, edge, edge.getTo(), this.getDestinationNodeType());
        } else {
            invertEdgeDirection(edge);
        }
    }

    private void applyRuleWithBothNodeTypes(List<Node> nodes, Edge edge) {
        for (Node node : nodes) {
            if (node.getId().equals(edge.getFrom()) && node.getGroup().equals(this.getSourceNodeType())) {
                for (Node secondNode : nodes) {
                    if (secondNode.getId().equals(edge.getTo()) && secondNode.getGroup().equals(this.getDestinationNodeType())) {
                        invertEdgeDirection(edge);
                        break;
                    }
                }
            }
        }
    }

    private void applyRuleWithOneNodeType(List<Node> nodes, Edge edge, String nodeId, String ruleNodeType) {
        boolean findNode = false;
        for (Node node : nodes) {
            if (node.getId().equals(nodeId) && node.getGroup().equals(ruleNodeType)) {
                findNode = true;
            }
            if (findNode) {
                invertEdgeDirection(edge);
            }
        }
    }

    private void invertEdgeDirection(Edge edge) {
        String originalFrom = edge.getFrom();
        String originalTo = edge.getTo();
        edge.setFrom(originalTo);
        edge.setTo(originalFrom);
    }
}
