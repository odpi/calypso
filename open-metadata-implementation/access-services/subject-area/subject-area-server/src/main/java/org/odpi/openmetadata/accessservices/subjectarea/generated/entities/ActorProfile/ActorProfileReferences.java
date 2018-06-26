/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.odpi.openmetadata.accessservices.subjectarea.generated.entities.ActorProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.instances.Relationship;

import org.odpi.openmetadata.accessservices.subjectarea.ffdc.exceptions.InvalidParameterException;
import org.odpi.openmetadata.accessservices.subjectarea.server.properties.line.Line;
import org.odpi.openmetadata.accessservices.subjectarea.generated.references.ActorProfileToActorProfile.FollowsReference;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.Leadership.Leadership;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.Leadership.LeadershipMapper;
import org.odpi.openmetadata.accessservices.subjectarea.generated.references.ActorProfileToActorProfile.LeadsReference;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.Leadership.Leadership;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.Leadership.LeadershipMapper;
import org.odpi.openmetadata.accessservices.subjectarea.generated.references.ActorProfileToContactDetails.ContactsReference;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.ContactThrough.ContactThrough;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.ContactThrough.ContactThroughMapper;
import org.odpi.openmetadata.accessservices.subjectarea.generated.references.ActorProfileToUserIdentity.UserIdentitiesReference;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.ProfileIdentity.ProfileIdentity;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.ProfileIdentity.ProfileIdentityMapper;
import org.odpi.openmetadata.accessservices.subjectarea.generated.references.ActorProfileToCommunity.CommunitiesReference;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.CommunityMembership.CommunityMembership;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.CommunityMembership.CommunityMembershipMapper;
import org.odpi.openmetadata.accessservices.subjectarea.generated.references.ActorProfileToGovernanceResponsibility.ContactForReference;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.ResponsibilityStaffContact.ResponsibilityStaffContact;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.ResponsibilityStaffContact.ResponsibilityStaffContactMapper;
import org.odpi.openmetadata.accessservices.subjectarea.generated.references.ActorProfileToActorProfile.PeersReference;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.Peer.Peer;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.Peer.PeerMapper;
import org.odpi.openmetadata.accessservices.subjectarea.generated.references.ActorProfileToReferenceable.ContributionsReference;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.Contributor.Contributor;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.Contributor.ContributorMapper;
import org.odpi.openmetadata.accessservices.subjectarea.generated.references.ActorProfileToCollection.ActorCollectionsReference;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.ActorCollection.ActorCollection;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.ActorCollection.ActorCollectionMapper;
import org.odpi.openmetadata.accessservices.subjectarea.generated.references.ReferenceableToPerson.StaffReference;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.StaffAssignment.StaffAssignment;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.StaffAssignment.StaffAssignmentMapper;
import org.odpi.openmetadata.accessservices.subjectarea.generated.references.ReferenceableToCertificationType.CertificationsReference;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.Certification.Certification;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.Certification.CertificationMapper;
import org.odpi.openmetadata.accessservices.subjectarea.generated.references.ReferenceableToRelatedMedia.RelatedMediaReference;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.MediaReference.MediaReference;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.MediaReference.MediaReferenceMapper;
import org.odpi.openmetadata.accessservices.subjectarea.generated.references.ReferenceableToPropertyFacet.FacetsReference;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.ReferenceableFacet.ReferenceableFacet;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.ReferenceableFacet.ReferenceableFacetMapper;
import org.odpi.openmetadata.accessservices.subjectarea.generated.references.ReferenceableToActorProfile.ContributorsReference;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.Contributor.Contributor;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.Contributor.ContributorMapper;
import org.odpi.openmetadata.accessservices.subjectarea.generated.references.ReferenceableToInformalTag.TagsReference;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.AttachedTag.AttachedTag;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.AttachedTag.AttachedTagMapper;
import org.odpi.openmetadata.accessservices.subjectarea.generated.references.ReferenceableToGlossaryTerm.MeaningReference;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.SemanticAssignment.SemanticAssignment;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.SemanticAssignment.SemanticAssignmentMapper;
import org.odpi.openmetadata.accessservices.subjectarea.generated.references.ReferenceableToExternalId.AlsoKnownAsReference;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.ExternalIdLink.ExternalIdLink;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.ExternalIdLink.ExternalIdLinkMapper;
import org.odpi.openmetadata.accessservices.subjectarea.generated.references.ReferenceableToExternalId.ManagedResourcesReference;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.ExternalIdScope.ExternalIdScope;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.ExternalIdScope.ExternalIdScopeMapper;
import org.odpi.openmetadata.accessservices.subjectarea.generated.references.ReferenceableToToDo.TodosReference;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.ToDoOnReferenceable.ToDoOnReferenceable;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.ToDoOnReferenceable.ToDoOnReferenceableMapper;
import org.odpi.openmetadata.accessservices.subjectarea.generated.references.ReferenceableToComment.CommentsReference;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.AttachedComment.AttachedComment;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.AttachedComment.AttachedCommentMapper;
import org.odpi.openmetadata.accessservices.subjectarea.generated.references.ReferenceableToExternalReference.ExternalReferenceReference;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.ExternalReferenceLink.ExternalReferenceLink;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.ExternalReferenceLink.ExternalReferenceLinkMapper;
import org.odpi.openmetadata.accessservices.subjectarea.generated.references.ReferenceableToNoteLog.NoteLogsReference;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.AttachedNoteLog.AttachedNoteLog;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.AttachedNoteLog.AttachedNoteLogMapper;
import org.odpi.openmetadata.accessservices.subjectarea.generated.references.ReferenceableToLike.LikesReference;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.AttachedLike.AttachedLike;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.AttachedLike.AttachedLikeMapper;
import org.odpi.openmetadata.accessservices.subjectarea.generated.references.ReferenceableToCollection.FoundInCollectionsReference;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.CollectionMember.CollectionMember;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.CollectionMember.CollectionMemberMapper;
import org.odpi.openmetadata.accessservices.subjectarea.generated.references.ReferenceableToLicenseType.LicensesReference;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.License.License;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.License.LicenseMapper;
import org.odpi.openmetadata.accessservices.subjectarea.generated.references.ReferenceableToToDo.ActionsReference;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.ToDoSource.ToDoSource;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.ToDoSource.ToDoSourceMapper;
import org.odpi.openmetadata.accessservices.subjectarea.generated.references.ReferenceableToMeeting.MeetingsReference;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.MeetingOnReferenceable.MeetingOnReferenceable;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.MeetingOnReferenceable.MeetingOnReferenceableMapper;
import org.odpi.openmetadata.accessservices.subjectarea.generated.references.ReferenceableToRating.StarRatingsReference;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.AttachedRating.AttachedRating;
import org.odpi.openmetadata.accessservices.subjectarea.generated.relationships.AttachedRating.AttachedRatingMapper;

import java.io.Serializable;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;

/**
 *
 */
@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class ActorProfileReferences implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(ActorProfileReferences.class);
    private static final String className = ActorProfileReferences.class.getName();

    public static final String[] REFERENCE_NAMES_SET_VALUES = new String[] {
             "follows",
             "leads",
             "contacts",
             "userIdentities",
             "communities",
             "contactFor",
             "peers",
             "contributions",
             "actorCollections",
             "staff",
             "certifications",
             "relatedMedia",
             "facets",
             "contributors",
             "tags",
             "meaning",
             "alsoKnownAs",
             "managedResources",
             "todos",
             "comments",
             "externalReference",
             "noteLogs",
             "likes",
             "foundInCollections",
             "licenses",
             "actions",
             "meetings",
             "starRatings",
             // Terminate the list
             null
    };

     public static final String[] RELATIONSHIP_NAMES_SET_VALUES = new String[] {
             "Leadership",
             "Leadership",
             "ContactThrough",
             "ProfileIdentity",
             "CommunityMembership",
             "ResponsibilityStaffContact",
             "Peer",
             "Contributor",
             "ActorCollection",
             "StaffAssignment",
             "Certification",
             "MediaReference",
             "ReferenceableFacet",
             "Contributor",
             "AttachedTag",
             "SemanticAssignment",
             "ExternalIdLink",
             "ExternalIdScope",
             "ToDoOnReferenceable",
             "AttachedComment",
             "ExternalReferenceLink",
             "AttachedNoteLog",
             "AttachedLike",
             "CollectionMember",
             "License",
             "ToDoSource",
             "MeetingOnReferenceable",
             "AttachedRating",
              // Terminate the list
              null
     };
     /**
       * We are passed a set of omrs relationships that are associated with a entity ActorProfile
       * Each of these relationships should map to a reference (a uniquely named attribute in ActorProfile).
       *
       * Relationships have cardinality. There are 2 sorts of cardinality relevant here, whether the relationship can be related to one or many
       * entities.
       *
       * @param lines
       * @param entityGuid
       * @throws InvalidParameterException
       */
     public ActorProfileReferences(String entityGuid, List<Line> lines) throws InvalidParameterException {
         for (Line relationship: lines) {
            for (int i=0;i< RELATIONSHIP_NAMES_SET_VALUES.length; i++) {
               if (relationship.getName().equals(RELATIONSHIP_NAMES_SET_VALUES[i])) {
                    String referenceName = REFERENCE_NAMES_SET_VALUES[i];

                    if ("follows".equals(referenceName)) {
                         Leadership leadership_relationship = (Leadership)relationship;
                         FollowsReference followsReference = new FollowsReference(entityGuid,leadership_relationship);
                         if ( follows== null ) {
                              follows = new HashSet();
                         }
                          follows.add(followsReference);
                    }
                    if ("leads".equals(referenceName)) {
                         Leadership leadership_relationship = (Leadership)relationship;
                         LeadsReference leadsReference = new LeadsReference(entityGuid,leadership_relationship);
                         if ( leads== null ) {
                              leads = new HashSet();
                         }
                          leads.add(leadsReference);
                    }
                    if ("contacts".equals(referenceName)) {
                         ContactThrough contactThrough_relationship = (ContactThrough)relationship;
                         ContactsReference contactsReference = new ContactsReference(entityGuid,contactThrough_relationship);
                         if ( contacts== null ) {
                              contacts = new HashSet();
                         }
                          contacts.add(contactsReference);
                    }
                    if ("userIdentities".equals(referenceName)) {
                         ProfileIdentity profileIdentity_relationship = (ProfileIdentity)relationship;
                         UserIdentitiesReference userIdentitiesReference = new UserIdentitiesReference(entityGuid,profileIdentity_relationship);
                         if ( userIdentities== null ) {
                              userIdentities = new HashSet();
                         }
                          userIdentities.add(userIdentitiesReference);
                    }
                    if ("communities".equals(referenceName)) {
                         CommunityMembership communityMembership_relationship = (CommunityMembership)relationship;
                         CommunitiesReference communitiesReference = new CommunitiesReference(entityGuid,communityMembership_relationship);
                         if ( communities== null ) {
                              communities = new HashSet();
                         }
                          communities.add(communitiesReference);
                    }
                    if ("contactFor".equals(referenceName)) {
                         ResponsibilityStaffContact responsibilityStaffContact_relationship = (ResponsibilityStaffContact)relationship;
                         ContactForReference contactForReference = new ContactForReference(entityGuid,responsibilityStaffContact_relationship);
                         if ( contactFor== null ) {
                              contactFor = new HashSet();
                         }
                          contactFor.add(contactForReference);
                    }
                    if ("peers".equals(referenceName)) {
                         Peer peer_relationship = (Peer)relationship;
                         PeersReference peersReference = new PeersReference(entityGuid,peer_relationship);
                         if ( peers== null ) {
                              peers = new HashSet();
                         }
                          peers.add(peersReference);
                    }
                    if ("contributions".equals(referenceName)) {
                         Contributor contributor_relationship = (Contributor)relationship;
                         ContributionsReference contributionsReference = new ContributionsReference(entityGuid,contributor_relationship);
                         if ( contributions== null ) {
                              contributions = new HashSet();
                         }
                          contributions.add(contributionsReference);
                    }
                    if ("actorCollections".equals(referenceName)) {
                         ActorCollection actorCollection_relationship = (ActorCollection)relationship;
                         ActorCollectionsReference actorCollectionsReference = new ActorCollectionsReference(entityGuid,actorCollection_relationship);
                         if ( actorCollections== null ) {
                              actorCollections = new HashSet();
                         }
                          actorCollections.add(actorCollectionsReference);
                    }
                    if ("staff".equals(referenceName)) {
                         StaffAssignment staffAssignment_relationship = (StaffAssignment)relationship;
                         StaffReference staffReference = new StaffReference(entityGuid,staffAssignment_relationship);
                         if ( staff== null ) {
                              staff = new HashSet();
                         }
                          staff.add(staffReference);
                    }
                    if ("certifications".equals(referenceName)) {
                         Certification certification_relationship = (Certification)relationship;
                         CertificationsReference certificationsReference = new CertificationsReference(entityGuid,certification_relationship);
                         if ( certifications== null ) {
                              certifications = new HashSet();
                         }
                          certifications.add(certificationsReference);
                    }
                    if ("relatedMedia".equals(referenceName)) {
                         MediaReference mediaReference_relationship = (MediaReference)relationship;
                         RelatedMediaReference relatedMediaReference = new RelatedMediaReference(entityGuid,mediaReference_relationship);
                         if ( relatedMedia== null ) {
                              relatedMedia = new HashSet();
                         }
                          relatedMedia.add(relatedMediaReference);
                    }
                    if ("facets".equals(referenceName)) {
                         ReferenceableFacet referenceableFacet_relationship = (ReferenceableFacet)relationship;
                         FacetsReference facetsReference = new FacetsReference(entityGuid,referenceableFacet_relationship);
                         if ( facets== null ) {
                              facets = new HashSet();
                         }
                          facets.add(facetsReference);
                    }
                    if ("contributors".equals(referenceName)) {
                         Contributor contributor_relationship = (Contributor)relationship;
                         ContributorsReference contributorsReference = new ContributorsReference(entityGuid,contributor_relationship);
                         if ( contributors== null ) {
                              contributors = new HashSet();
                         }
                          contributors.add(contributorsReference);
                    }
                    if ("tags".equals(referenceName)) {
                         AttachedTag attachedTag_relationship = (AttachedTag)relationship;
                         TagsReference tagsReference = new TagsReference(entityGuid,attachedTag_relationship);
                         if ( tags== null ) {
                              tags = new HashSet();
                         }
                          tags.add(tagsReference);
                    }
                    if ("meaning".equals(referenceName)) {
                         SemanticAssignment semanticAssignment_relationship = (SemanticAssignment)relationship;
                         MeaningReference meaningReference = new MeaningReference(entityGuid,semanticAssignment_relationship);
                         if ( meaning== null ) {
                              meaning = new HashSet();
                         }
                          meaning.add(meaningReference);
                    }
                    if ("alsoKnownAs".equals(referenceName)) {
                         ExternalIdLink externalIdLink_relationship = (ExternalIdLink)relationship;
                         AlsoKnownAsReference alsoKnownAsReference = new AlsoKnownAsReference(entityGuid,externalIdLink_relationship);
                         if ( alsoKnownAs== null ) {
                              alsoKnownAs = new HashSet();
                         }
                          alsoKnownAs.add(alsoKnownAsReference);
                    }
                    if ("managedResources".equals(referenceName)) {
                         ExternalIdScope externalIdScope_relationship = (ExternalIdScope)relationship;
                         ManagedResourcesReference managedResourcesReference = new ManagedResourcesReference(entityGuid,externalIdScope_relationship);
                         if ( managedResources== null ) {
                              managedResources = new HashSet();
                         }
                          managedResources.add(managedResourcesReference);
                    }
                    if ("todos".equals(referenceName)) {
                         ToDoOnReferenceable toDoOnReferenceable_relationship = (ToDoOnReferenceable)relationship;
                         TodosReference todosReference = new TodosReference(entityGuid,toDoOnReferenceable_relationship);
                         if ( todos== null ) {
                              todos = new HashSet();
                         }
                          todos.add(todosReference);
                    }
                    if ("comments".equals(referenceName)) {
                         AttachedComment attachedComment_relationship = (AttachedComment)relationship;
                         CommentsReference commentsReference = new CommentsReference(entityGuid,attachedComment_relationship);
                         if ( comments== null ) {
                              comments = new HashSet();
                         }
                          comments.add(commentsReference);
                    }
                    if ("externalReference".equals(referenceName)) {
                         ExternalReferenceLink externalReferenceLink_relationship = (ExternalReferenceLink)relationship;
                         ExternalReferenceReference externalReferenceReference = new ExternalReferenceReference(entityGuid,externalReferenceLink_relationship);
                         if ( externalReference== null ) {
                              externalReference = new HashSet();
                         }
                          externalReference.add(externalReferenceReference);
                    }
                    if ("noteLogs".equals(referenceName)) {
                         AttachedNoteLog attachedNoteLog_relationship = (AttachedNoteLog)relationship;
                         NoteLogsReference noteLogsReference = new NoteLogsReference(entityGuid,attachedNoteLog_relationship);
                         if ( noteLogs== null ) {
                              noteLogs = new HashSet();
                         }
                          noteLogs.add(noteLogsReference);
                    }
                    if ("likes".equals(referenceName)) {
                         AttachedLike attachedLike_relationship = (AttachedLike)relationship;
                         LikesReference likesReference = new LikesReference(entityGuid,attachedLike_relationship);
                         if ( likes== null ) {
                              likes = new HashSet();
                         }
                          likes.add(likesReference);
                    }
                    if ("foundInCollections".equals(referenceName)) {
                         CollectionMember collectionMember_relationship = (CollectionMember)relationship;
                         FoundInCollectionsReference foundInCollectionsReference = new FoundInCollectionsReference(entityGuid,collectionMember_relationship);
                         if ( foundInCollections== null ) {
                              foundInCollections = new HashSet();
                         }
                          foundInCollections.add(foundInCollectionsReference);
                    }
                    if ("licenses".equals(referenceName)) {
                         License license_relationship = (License)relationship;
                         LicensesReference licensesReference = new LicensesReference(entityGuid,license_relationship);
                         if ( licenses== null ) {
                              licenses = new HashSet();
                         }
                          licenses.add(licensesReference);
                    }
                    if ("actions".equals(referenceName)) {
                         ToDoSource toDoSource_relationship = (ToDoSource)relationship;
                         ActionsReference actionsReference = new ActionsReference(entityGuid,toDoSource_relationship);
                         if ( actions== null ) {
                              actions = new HashSet();
                         }
                          actions.add(actionsReference);
                    }
                    if ("meetings".equals(referenceName)) {
                         MeetingOnReferenceable meetingOnReferenceable_relationship = (MeetingOnReferenceable)relationship;
                         MeetingsReference meetingsReference = new MeetingsReference(entityGuid,meetingOnReferenceable_relationship);
                         if ( meetings== null ) {
                              meetings = new HashSet();
                         }
                          meetings.add(meetingsReference);
                    }
                    if ("starRatings".equals(referenceName)) {
                         AttachedRating attachedRating_relationship = (AttachedRating)relationship;
                         StarRatingsReference starRatingsReference = new StarRatingsReference(entityGuid,attachedRating_relationship);
                         if ( starRatings== null ) {
                              starRatings = new HashSet();
                         }
                          starRatings.add(starRatingsReference);
                    }

                 }
             }
         }
     }

    public static final Set<String> REFERENCE_NAMES_SET = new HashSet(new HashSet<>(Arrays.asList(REFERENCE_NAMES_SET_VALUES)));
    // there may be duplicate strings in RELATIONSHIP_NAMES_SET_VALUES, the following line deduplicates the Strings into a Set.
    public static final Set<String> RELATIONSHIP_NAMES_SET = new HashSet(new HashSet<>(Arrays.asList(RELATIONSHIP_NAMES_SET_VALUES)));
// Singular properties
// Set properties

    private Set<FollowsReference> follows;
    private Set<LeadsReference> leads;
    private Set<ContactsReference> contacts;
    private Set<UserIdentitiesReference> userIdentities;
    private Set<CommunitiesReference> communities;
    private Set<ContactForReference> contactFor;
    private Set<PeersReference> peers;
    private Set<ContributionsReference> contributions;
    private Set<ActorCollectionsReference> actorCollections;
    private Set<StaffReference> staff;
    private Set<CertificationsReference> certifications;
    private Set<RelatedMediaReference> relatedMedia;
    private Set<FacetsReference> facets;
    private Set<ContributorsReference> contributors;
    private Set<TagsReference> tags;
    private Set<MeaningReference> meaning;
    private Set<AlsoKnownAsReference> alsoKnownAs;
    private Set<ManagedResourcesReference> managedResources;
    private Set<TodosReference> todos;
    private Set<CommentsReference> comments;
    private Set<ExternalReferenceReference> externalReference;
    private Set<NoteLogsReference> noteLogs;
    private Set<LikesReference> likes;
    private Set<FoundInCollectionsReference> foundInCollections;
    private Set<LicensesReference> licenses;
    private Set<ActionsReference> actions;
    private Set<MeetingsReference> meetings;
    private Set<StarRatingsReference> starRatings;

// List properties

    // setters and setters

// Sets
    public Set<FollowsReference> getFollowsReferences() {
        return follows;
    }

    public void setFollowsReferences(Set<FollowsReference> follows) {
        this.follows =follows;
    }
    public Set<LeadsReference> getLeadsReferences() {
        return leads;
    }

    public void setLeadsReferences(Set<LeadsReference> leads) {
        this.leads =leads;
    }
    public Set<ContactsReference> getContactsReferences() {
        return contacts;
    }

    public void setContactsReferences(Set<ContactsReference> contacts) {
        this.contacts =contacts;
    }
    public Set<UserIdentitiesReference> getUserIdentitiesReferences() {
        return userIdentities;
    }

    public void setUserIdentitiesReferences(Set<UserIdentitiesReference> userIdentities) {
        this.userIdentities =userIdentities;
    }
    public Set<CommunitiesReference> getCommunitiesReferences() {
        return communities;
    }

    public void setCommunitiesReferences(Set<CommunitiesReference> communities) {
        this.communities =communities;
    }
    public Set<ContactForReference> getContactForReferences() {
        return contactFor;
    }

    public void setContactForReferences(Set<ContactForReference> contactFor) {
        this.contactFor =contactFor;
    }
    public Set<PeersReference> getPeersReferences() {
        return peers;
    }

    public void setPeersReferences(Set<PeersReference> peers) {
        this.peers =peers;
    }
    public Set<ContributionsReference> getContributionsReferences() {
        return contributions;
    }

    public void setContributionsReferences(Set<ContributionsReference> contributions) {
        this.contributions =contributions;
    }
    public Set<ActorCollectionsReference> getActorCollectionsReferences() {
        return actorCollections;
    }

    public void setActorCollectionsReferences(Set<ActorCollectionsReference> actorCollections) {
        this.actorCollections =actorCollections;
    }
    public Set<StaffReference> getStaffReferences() {
        return staff;
    }

    public void setStaffReferences(Set<StaffReference> staff) {
        this.staff =staff;
    }
    public Set<CertificationsReference> getCertificationsReferences() {
        return certifications;
    }

    public void setCertificationsReferences(Set<CertificationsReference> certifications) {
        this.certifications =certifications;
    }
    public Set<RelatedMediaReference> getRelatedMediaReferences() {
        return relatedMedia;
    }

    public void setRelatedMediaReferences(Set<RelatedMediaReference> relatedMedia) {
        this.relatedMedia =relatedMedia;
    }
    public Set<FacetsReference> getFacetsReferences() {
        return facets;
    }

    public void setFacetsReferences(Set<FacetsReference> facets) {
        this.facets =facets;
    }
    public Set<ContributorsReference> getContributorsReferences() {
        return contributors;
    }

    public void setContributorsReferences(Set<ContributorsReference> contributors) {
        this.contributors =contributors;
    }
    public Set<TagsReference> getTagsReferences() {
        return tags;
    }

    public void setTagsReferences(Set<TagsReference> tags) {
        this.tags =tags;
    }
    public Set<MeaningReference> getMeaningReferences() {
        return meaning;
    }

    public void setMeaningReferences(Set<MeaningReference> meaning) {
        this.meaning =meaning;
    }
    public Set<AlsoKnownAsReference> getAlsoKnownAsReferences() {
        return alsoKnownAs;
    }

    public void setAlsoKnownAsReferences(Set<AlsoKnownAsReference> alsoKnownAs) {
        this.alsoKnownAs =alsoKnownAs;
    }
    public Set<ManagedResourcesReference> getManagedResourcesReferences() {
        return managedResources;
    }

    public void setManagedResourcesReferences(Set<ManagedResourcesReference> managedResources) {
        this.managedResources =managedResources;
    }
    public Set<TodosReference> getTodosReferences() {
        return todos;
    }

    public void setTodosReferences(Set<TodosReference> todos) {
        this.todos =todos;
    }
    public Set<CommentsReference> getCommentsReferences() {
        return comments;
    }

    public void setCommentsReferences(Set<CommentsReference> comments) {
        this.comments =comments;
    }
    public Set<ExternalReferenceReference> getExternalReferenceReferences() {
        return externalReference;
    }

    public void setExternalReferenceReferences(Set<ExternalReferenceReference> externalReference) {
        this.externalReference =externalReference;
    }
    public Set<NoteLogsReference> getNoteLogsReferences() {
        return noteLogs;
    }

    public void setNoteLogsReferences(Set<NoteLogsReference> noteLogs) {
        this.noteLogs =noteLogs;
    }
    public Set<LikesReference> getLikesReferences() {
        return likes;
    }

    public void setLikesReferences(Set<LikesReference> likes) {
        this.likes =likes;
    }
    public Set<FoundInCollectionsReference> getFoundInCollectionsReferences() {
        return foundInCollections;
    }

    public void setFoundInCollectionsReferences(Set<FoundInCollectionsReference> foundInCollections) {
        this.foundInCollections =foundInCollections;
    }
    public Set<LicensesReference> getLicensesReferences() {
        return licenses;
    }

    public void setLicensesReferences(Set<LicensesReference> licenses) {
        this.licenses =licenses;
    }
    public Set<ActionsReference> getActionsReferences() {
        return actions;
    }

    public void setActionsReferences(Set<ActionsReference> actions) {
        this.actions =actions;
    }
    public Set<MeetingsReference> getMeetingsReferences() {
        return meetings;
    }

    public void setMeetingsReferences(Set<MeetingsReference> meetings) {
        this.meetings =meetings;
    }
    public Set<StarRatingsReference> getStarRatingsReferences() {
        return starRatings;
    }

    public void setStarRatingsReferences(Set<StarRatingsReference> starRatings) {
        this.starRatings =starRatings;
    }

// Lists

 public StringBuilder toString(StringBuilder sb) {
        if (sb == null) {
            sb = new StringBuilder();
        }

        sb.append("ActorProfileReferences{");
        sb.append("followsReference='").append(follows.toString());
        sb.append("leadsReference='").append(leads.toString());
        sb.append("contactsReference='").append(contacts.toString());
        sb.append("userIdentitiesReference='").append(userIdentities.toString());
        sb.append("communitiesReference='").append(communities.toString());
        sb.append("contactForReference='").append(contactFor.toString());
        sb.append("peersReference='").append(peers.toString());
        sb.append("contributionsReference='").append(contributions.toString());
        sb.append("actorCollectionsReference='").append(actorCollections.toString());
        sb.append("staffReference='").append(staff.toString());
        sb.append("certificationsReference='").append(certifications.toString());
        sb.append("relatedMediaReference='").append(relatedMedia.toString());
        sb.append("facetsReference='").append(facets.toString());
        sb.append("contributorsReference='").append(contributors.toString());
        sb.append("tagsReference='").append(tags.toString());
        sb.append("meaningReference='").append(meaning.toString());
        sb.append("alsoKnownAsReference='").append(alsoKnownAs.toString());
        sb.append("managedResourcesReference='").append(managedResources.toString());
        sb.append("todosReference='").append(todos.toString());
        sb.append("commentsReference='").append(comments.toString());
        sb.append("externalReferenceReference='").append(externalReference.toString());
        sb.append("noteLogsReference='").append(noteLogs.toString());
        sb.append("likesReference='").append(likes.toString());
        sb.append("foundInCollectionsReference='").append(foundInCollections.toString());
        sb.append("licensesReference='").append(licenses.toString());
        sb.append("actionsReference='").append(actions.toString());
        sb.append("meetingsReference='").append(meetings.toString());
        sb.append("starRatingsReference='").append(starRatings.toString());

        sb.append('}');

        return sb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        if (!super.equals(o)) { return false; }
         ActorProfileReferences typedThat = (ActorProfileReferences) o;
      // compare single cardinality attributes
         if (this.follows != null && !Objects.equals(this.follows,typedThat.follows)) {
                            return false;
                 }
         if (this.leads != null && !Objects.equals(this.leads,typedThat.leads)) {
                            return false;
                 }
         if (this.contacts != null && !Objects.equals(this.contacts,typedThat.contacts)) {
                            return false;
                 }
         if (this.userIdentities != null && !Objects.equals(this.userIdentities,typedThat.userIdentities)) {
                            return false;
                 }
         if (this.communities != null && !Objects.equals(this.communities,typedThat.communities)) {
                            return false;
                 }
         if (this.contactFor != null && !Objects.equals(this.contactFor,typedThat.contactFor)) {
                            return false;
                 }
         if (this.peers != null && !Objects.equals(this.peers,typedThat.peers)) {
                            return false;
                 }
         if (this.contributions != null && !Objects.equals(this.contributions,typedThat.contributions)) {
                            return false;
                 }
         if (this.actorCollections != null && !Objects.equals(this.actorCollections,typedThat.actorCollections)) {
                            return false;
                 }
         if (this.staff != null && !Objects.equals(this.staff,typedThat.staff)) {
                            return false;
                 }
         if (this.certifications != null && !Objects.equals(this.certifications,typedThat.certifications)) {
                            return false;
                 }
         if (this.relatedMedia != null && !Objects.equals(this.relatedMedia,typedThat.relatedMedia)) {
                            return false;
                 }
         if (this.facets != null && !Objects.equals(this.facets,typedThat.facets)) {
                            return false;
                 }
         if (this.contributors != null && !Objects.equals(this.contributors,typedThat.contributors)) {
                            return false;
                 }
         if (this.tags != null && !Objects.equals(this.tags,typedThat.tags)) {
                            return false;
                 }
         if (this.meaning != null && !Objects.equals(this.meaning,typedThat.meaning)) {
                            return false;
                 }
         if (this.alsoKnownAs != null && !Objects.equals(this.alsoKnownAs,typedThat.alsoKnownAs)) {
                            return false;
                 }
         if (this.managedResources != null && !Objects.equals(this.managedResources,typedThat.managedResources)) {
                            return false;
                 }
         if (this.todos != null && !Objects.equals(this.todos,typedThat.todos)) {
                            return false;
                 }
         if (this.comments != null && !Objects.equals(this.comments,typedThat.comments)) {
                            return false;
                 }
         if (this.externalReference != null && !Objects.equals(this.externalReference,typedThat.externalReference)) {
                            return false;
                 }
         if (this.noteLogs != null && !Objects.equals(this.noteLogs,typedThat.noteLogs)) {
                            return false;
                 }
         if (this.likes != null && !Objects.equals(this.likes,typedThat.likes)) {
                            return false;
                 }
         if (this.foundInCollections != null && !Objects.equals(this.foundInCollections,typedThat.foundInCollections)) {
                            return false;
                 }
         if (this.licenses != null && !Objects.equals(this.licenses,typedThat.licenses)) {
                            return false;
                 }
         if (this.actions != null && !Objects.equals(this.actions,typedThat.actions)) {
                            return false;
                 }
         if (this.meetings != null && !Objects.equals(this.meetings,typedThat.meetings)) {
                            return false;
                 }
         if (this.starRatings != null && !Objects.equals(this.starRatings,typedThat.starRatings)) {
                            return false;
                 }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode()
    ,this.follows
    ,this.leads
    ,this.contacts
    ,this.userIdentities
    ,this.communities
    ,this.contactFor
    ,this.peers
    ,this.contributions
    ,this.actorCollections
    ,this.staff
    ,this.certifications
    ,this.relatedMedia
    ,this.facets
    ,this.contributors
    ,this.tags
    ,this.meaning
    ,this.alsoKnownAs
    ,this.managedResources
    ,this.todos
    ,this.comments
    ,this.externalReference
    ,this.noteLogs
    ,this.likes
    ,this.foundInCollections
    ,this.licenses
    ,this.actions
    ,this.meetings
    ,this.starRatings
       );
    }

    @Override
    public String toString() {
        return toString(new StringBuilder()).toString();
    }
}
