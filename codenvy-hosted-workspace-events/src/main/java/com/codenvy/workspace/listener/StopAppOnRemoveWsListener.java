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
package com.codenvy.workspace.listener;

import org.eclipse.che.api.core.NotFoundException;
import org.eclipse.che.api.core.notification.EventService;
import org.eclipse.che.api.core.notification.EventSubscriber;
/*import org.eclipse.che.api.runner.RunQueue;
import org.eclipse.che.api.runner.RunQueueTask;
import org.eclipse.che.api.runner.internal.RunnerEvent;*/
import com.codenvy.workspace.event.DeleteWorkspaceEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Stops running applications when workspace was removed
 *
 * @author Alexander Garagatyi
 */
@Singleton
public class StopAppOnRemoveWsListener {
    /*private static final Logger LOG = LoggerFactory.getLogger(StopAppOnRemoveWsListener.class);
    private final EventService                          eventService;
    private final RunQueue                              runQueue;
    private final EventSubscriber<DeleteWorkspaceEvent> deleteWsSubscriber;
    private final EventSubscriber<RunnerEvent>          runnerEventSubscriber;
    // Relation workspace ID to processes IDs
    private final Map<String, Set<Long>>                processes;

    @Inject
    public StopAppOnRemoveWsListener(EventService eventService, final RunQueue runQueue) {
        this.runQueue = runQueue;
        this.processes = new HashMap<>();
        this.eventService = eventService;
        this.deleteWsSubscriber = new EventSubscriber<DeleteWorkspaceEvent>() {
            @Override
            public void onEvent(DeleteWorkspaceEvent event) {
                stopProcesses(event.getWorkspace().getId());
            }
        };
        this.runnerEventSubscriber = new EventSubscriber<RunnerEvent>() {
            @Override
            public void onEvent(RunnerEvent event) {
                switch (event.getType()) {
                    case RUN_TASK_ADDED_IN_QUEUE:
                        addProcess(event.getWorkspace(), event.getProcessId());
                        break;
                    case STOPPED:
                    case RUN_TASK_QUEUE_TIME_EXCEEDED:
                    case ERROR:
                        removeProcess(event.getWorkspace(), event.getProcessId());
                        break;
                    default:
                        // do nothing
                }
            }
        };
    }

    private synchronized void addProcess(String workspace, Long processId) {
        Set<Long> ids;
        if (null == (ids = processes.get(workspace))) {
            processes.put(workspace, new HashSet<Long>());
            ids = processes.get(workspace);
        }
        ids.add(processId);
    }

    private synchronized void removeProcess(String workspace, Long processId) {
        final Set<Long> processesIds = processes.get(workspace);
        if (null != processesIds) {
            processesIds.remove(processId);
        }
    }

    private void stopProcesses(String workspace) {
        final Set<Long> appIds;
        synchronized (this) {
            appIds = processes.remove(workspace);
        }
        if (null != appIds) {
            for (Long appId : appIds) {
                try {
                    final RunQueueTask runTask = runQueue.getTask(appId);
                    runTask.cancel();
                } catch (NotFoundException ignored) {
                } catch (Exception e) {
                    LOG.error(e.getLocalizedMessage(), e);
                }
            }
        }
    }

    @PostConstruct
    private void subscribe() {
        eventService.subscribe(deleteWsSubscriber);
        eventService.subscribe(runnerEventSubscriber);
    }

    @PreDestroy
    private void unsubscribe() {
        eventService.unsubscribe(deleteWsSubscriber);
        eventService.unsubscribe(runnerEventSubscriber);
    }*/
}
