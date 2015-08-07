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
package com.codenvy.analytics.metrics.tasks;

import com.codenvy.analytics.metrics.Context;
import com.codenvy.analytics.metrics.MetricFilter;
import com.codenvy.analytics.metrics.MetricType;

import java.io.IOException;

/** @author Dmytro Nochevnov */
public class TasksLaunchedWithAlwaysOn extends TasksLaunched {

    public TasksLaunchedWithAlwaysOn() {
        this(MetricType.TASKS_LAUNCHED_WITH_ALWAYS_ON);
    }

    public TasksLaunchedWithAlwaysOn(MetricType metricType) {
        super(metricType);
    }

    /** {@inheritDoc} */
    @Override
    public Context applySpecificFilter(Context context) throws IOException {
        Context.Builder builder = new Context.Builder(super.applySpecificFilter(context));
        builder.put(MetricFilter.LAUNCH_TYPE, ALWAYS_ON);
        return builder.build();
    }

    /** {@inheritDoc} */
    @Override
    public String getDescription() {
        return "The number tasks launched with always on";
    }
}
