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

--TODO Revise and drop table because of using resources for organization resources storing
INSERT INTO che_resource(id, amount, type, unit)
SELECT                   id, amount, type, unit
FROM resource;

INSERT INTO che_free_resources_limit(account_id)
SELECT                               accountid
FROM freeresourceslimit;

INSERT INTO che_free_resources_limit_resource(free_resources_limit_account_id, resources_id)
SELECT                               freeresourceslimit_accountid, resources_id
FROM freeresourceslimit_resource;

DROP TABLE freeresourceslimit_resource;
DROP TABLE freeresourceslimit;
