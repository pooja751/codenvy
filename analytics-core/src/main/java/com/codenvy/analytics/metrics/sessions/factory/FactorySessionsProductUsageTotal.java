/*
 *
 * CODENVY CONFIDENTIAL
 * ________________
 *
 * [2012] - [2013] Codenvy, S.A.
 * All Rights Reserved.
 * NOTICE: All information contained herein is, and remains
 * the property of Codenvy S.A. and its suppliers,
 * if any. The intellectual and technical concepts contained
 * herein are proprietary to Codenvy S.A.
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Codenvy S.A..
 */
package com.codenvy.analytics.metrics.sessions.factory;

import com.codenvy.analytics.metrics.AbstractLongValueResulted;
import com.codenvy.analytics.metrics.MetricType;

import javax.annotation.security.RolesAllowed;

/** @author <a href="mailto:abazko@codenvy.com">Anatoliy Bazko</a> */
@RolesAllowed({"system/admin", "system/manager"})
public class FactorySessionsProductUsageTotal extends AbstractLongValueResulted {

    public FactorySessionsProductUsageTotal() {
        super(MetricType.FACTORY_PRODUCT_USAGE_TIME_TOTAL);
    }

    @Override
    public String getStorageCollectionName() {
        return MetricType.PRODUCT_USAGE_FACTORY_SESSIONS_LIST.name().toLowerCase();
    }

    @Override
    public String[] getTrackedFields() {
        return new String[]{ProductUsageFactorySessionsList.TIME};
    }

    @Override
    public String getDescription() {
        return "The total time spent by all users in temporary workspaces";
    }
}
