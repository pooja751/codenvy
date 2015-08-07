/*
 * CODENVY CONFIDENTIAL
 * __________________
 *
 *  [2012] - [2015] Codenvy, S.A.
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Codenvy S.A. and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Codenvy S.A.
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Codenvy S.A..
 */
package com.codenvy.analytics.metrics.sessions.factory;

import com.codenvy.analytics.metrics.AbstractCount;
import com.codenvy.analytics.metrics.Context;
import com.codenvy.analytics.metrics.MetricFilter;
import com.codenvy.analytics.metrics.MetricType;

import java.io.IOException;

/**
 * @author Anatoliy Bazko
 */
public class AbandonedFactorySessions extends AbstractCount {


    public AbandonedFactorySessions() {
        super(MetricType.ABANDONED_FACTORY_SESSIONS, MetricType.PRODUCT_USAGE_FACTORY_SESSIONS, SESSION_ID);
    }

    /** {@inheritDoc} */
    @Override
    public Context applySpecificFilter(Context context) throws IOException {
        Context.Builder builder = new Context.Builder(context);
        builder.put(MetricFilter.CONVERTED_FACTORY_SESSION, 0);
        return builder.build();
    }

    /** {@inheritDoc} */
    @Override
    public String getDescription() {
        return "The number of abandoned sessions in temporary workspaces";
    }
}
