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

-- System permissions migration --------------------------------------------------
INSERT INTO che_system_permissions(id, user_id)
SELECT                             id, userid
FROM systempermissions;

INSERT INTO che_system_permissions_actions (system_permissions_id, actions)
SELECT                                      systempermissions_id,  actions
FROM systempermissions_actions;

DROP TABLE systempermissions_actions;
DROP TABLE systempermissions;
----------------------------------------------------------------------------------


-- Workers migration -------------------------------------------------------------
INSERT INTO che_worker(id, user_id, workspace_id)
SELECT                 id, userid,  workspaceid
FROM worker;

INSERT INTO che_worker_actions (worker_id, actions)
SELECT                          worker_id, actions
FROM worker_actions;

DROP TABLE worker_actions;
DROP TABLE worker;
----------------------------------------------------------------------------------


-- Stack permissions migration ---------------------------------------------------
INSERT INTO che_stack_permissions(id, stack_id, user_id)
SELECT                            id, stackid,  userid
FROM stackpermissions;

INSERT INTO che_stack_permissions_actions (stack_permissions_id, actions)
SELECT                                     stackpermissions_id,  actions
FROM stackpermissions_actions;

DROP TABLE stackpermissions_actions;
DROP TABLE stackpermissions;
----------------------------------------------------------------------------------


-- Recipe permissions migration --------------------------------------------------
INSERT INTO che_recipe_permissions(id, recipe_id, user_id)
SELECT                            id,  recipeid,  userid
FROM recipepermissions;

INSERT INTO che_recipe_permissions_actions (recipe_permissions_id, actions)
SELECT                                      recipepermissions_id, actions
FROM recipepermissions_actions;

DROP TABLE recipepermissions_actions;
DROP TABLE recipepermissions;
----------------------------------------------------------------------------------
