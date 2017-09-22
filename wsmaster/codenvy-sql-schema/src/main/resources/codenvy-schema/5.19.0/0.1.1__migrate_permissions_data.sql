--
-- Copyright (c) 2012-2017 Red Hat, Inc.
-- All rights reserved. This program and the accompanying materials
-- are made available under the terms of the Eclipse Public License v1.0
-- which accompanies this distribution, and is available at
-- http://www.eclipse.org/legal/epl-v10.html
--
-- Contributors:
--   Red Hat, Inc. - initial API and implementation
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
