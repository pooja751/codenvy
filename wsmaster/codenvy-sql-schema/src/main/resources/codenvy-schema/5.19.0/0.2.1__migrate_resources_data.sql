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

-- Resources migration --------------------------------------------------------
INSERT INTO che_resource(id, amount, type, unit)
SELECT                   id, amount, type, unit
FROM resource;


-- Free resources limits migration --------------------------------------------
INSERT INTO che_free_resources_limit(account_id)
SELECT                               accountid
FROM freeresourceslimit;

INSERT INTO che_free_resources_limit_resource(free_resources_limit_account_id, resources_id)
SELECT                               freeresourceslimit_accountid, resources_id
FROM freeresourceslimit_resource;

DROP TABLE freeresourceslimit_resource;
DROP TABLE freeresourceslimit;


-- Update reference for organization distributed resources ---------------------
ALTER TABLE organization_distributed_resources_resource DROP CONSTRAINT fk_organization_distributed_resources_resource_resource_id;
ALTER TABLE organization_distributed_resources_resource ADD CONSTRAINT fk_organization_distributed_resources_resource_resource_id FOREIGN KEY (resource_id) REFERENCES che_resource (id);
