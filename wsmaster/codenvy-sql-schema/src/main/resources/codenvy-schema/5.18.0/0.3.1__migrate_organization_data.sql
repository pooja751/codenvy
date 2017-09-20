--
--  [2012] - [2017] Codenvy, S.A.
--  All Rights Reserved.
--
-- NOTICE:  All information contained herein is, and remains
-- the property of Codenvy S.A. and its suppliers,
-- if any.  The intellectual and technical concepts contained
-- herein are proprietary to Codenvy S.A.
-- and its suppliers and may be covered by U.S. and Foreign Patents,
-- patents in process, and are protected by trade secret or copyright law.
-- Dissemination of this information or reproduction of this material
-- is strictly forbidden unless prior written permission is obtained
-- from Codenvy S.A..
--

INSERT INTO che_organization(id, parent, account_id)
SELECT                       id, parent, account_id
FROM organization;

INSERT INTO che_member(id, organization_id, user_id)
SELECT                 id, organizationid, userid
FROM member;

INSERT INTO che_member_actions(member_id, actions)
SELECT                         member_id, actions
FROM member_actions;

INSERT INTO che_organization_distributed_resources(organization_id)
SELECT                                             organizationid
FROM organization_distributed_resources;

INSERT INTO che_organization_distributed_resources_resource(organization_distributed_resources_id, resource_id)
SELECT                 organization_distributed_resources_id, resource_id
FROM organization_distributed_resources_resource;

DROP TABLE member;
DROP TABLE organization;
DROP TABLE che_member_actions;
DROP TABLE che_organization_distributed_resources;
DROP TABLE che_organization_distributed_resources_resource;

-- TODO Migrate resources
