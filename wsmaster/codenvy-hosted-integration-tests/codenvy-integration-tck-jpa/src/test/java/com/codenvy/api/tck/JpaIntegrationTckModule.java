/*
 * Copyright (c) [2012] - [2017] Red Hat, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Red Hat, Inc. - initial API and implementation
 */
package com.codenvy.api.tck;

import static org.eclipse.persistence.config.PersistenceUnitProperties.JDBC_DRIVER;
import static org.eclipse.persistence.config.PersistenceUnitProperties.JDBC_PASSWORD;
import static org.eclipse.persistence.config.PersistenceUnitProperties.JDBC_URL;
import static org.eclipse.persistence.config.PersistenceUnitProperties.JDBC_USER;
import static org.eclipse.persistence.config.PersistenceUnitProperties.TRANSACTION_TYPE;

import com.codenvy.api.invite.InviteImpl;
import com.codenvy.spi.invite.InviteDao;
import com.codenvy.spi.invite.jpa.JpaInviteDao;
import com.google.inject.TypeLiteral;
import com.google.inject.persist.jpa.JpaPersistModule;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.persistence.spi.PersistenceUnitTransactionType;
import org.eclipse.che.api.workspace.server.model.impl.WorkspaceImpl;
import org.eclipse.che.commons.test.tck.JpaCleaner;
import org.eclipse.che.commons.test.tck.TckModule;
import org.eclipse.che.commons.test.tck.TckResourcesCleaner;
import org.eclipse.che.commons.test.tck.repository.JpaTckRepository;
import org.eclipse.che.commons.test.tck.repository.TckRepository;
import org.eclipse.che.core.db.DBInitializer;
import org.eclipse.che.core.db.schema.SchemaInitializer;
import org.eclipse.che.core.db.schema.impl.flyway.FlywaySchemaInitializer;
import org.eclipse.che.multiuser.organization.spi.impl.OrganizationImpl;
import org.postgresql.ds.PGSimpleDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Module for testing JPA DAO.
 *
 * @author Mihail Kuznyetsov
 */
public class JpaIntegrationTckModule extends TckModule {

  private static final Logger LOG = LoggerFactory.getLogger(JpaIntegrationTckModule.class);

  @Override
  protected void configure() {
    final Map<String, String> properties = new HashMap<>();
    properties.put(TRANSACTION_TYPE, PersistenceUnitTransactionType.RESOURCE_LOCAL.name());

    final String dbUrl = System.getProperty("jdbc.url");
    final String dbUser = System.getProperty("jdbc.user");
    final String dbPassword = System.getProperty("jdbc.password");

    waitConnectionIsEstablished(dbUrl, dbUser, dbPassword);

    properties.put(JDBC_URL, dbUrl);
    properties.put(JDBC_USER, dbUser);
    properties.put(JDBC_PASSWORD, dbPassword);
    properties.put(JDBC_DRIVER, System.getProperty("jdbc.driver"));

    JpaPersistModule main = new JpaPersistModule("main");
    main.properties(properties);
    install(main);
    final PGSimpleDataSource dataSource = new PGSimpleDataSource();
    dataSource.setUser(dbUser);
    dataSource.setPassword(dbPassword);
    dataSource.setUrl(dbUrl);
    bind(SchemaInitializer.class)
        .toInstance(new FlywaySchemaInitializer(dataSource, "che-schema", "codenvy-schema"));
    bind(DBInitializer.class).asEagerSingleton();
    bind(TckResourcesCleaner.class).to(JpaCleaner.class);

    bind(new TypeLiteral<TckRepository<InviteImpl>>() {})
        .toInstance(new JpaTckRepository<>(InviteImpl.class));
    bind(new TypeLiteral<TckRepository<OrganizationImpl>>() {})
        .toInstance(new JpaTckRepository<>(OrganizationImpl.class));
    bind(new TypeLiteral<TckRepository<WorkspaceImpl>>() {})
        .toInstance(new JpaTckRepository<>(WorkspaceImpl.class));

    bind(InviteDao.class).to(JpaInviteDao.class);
  }

  private static void waitConnectionIsEstablished(String dbUrl, String dbUser, String dbPassword) {
    boolean isAvailable = false;
    for (int i = 0; i < 20 && !isAvailable; i++) {
      try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
        isAvailable = true;
      } catch (SQLException x) {
        LOG.warn(
            "An attempt to connect to the database failed with an error: {}",
            x.getLocalizedMessage());
        try {
          TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException interruptedX) {
          throw new RuntimeException(interruptedX.getLocalizedMessage(), interruptedX);
        }
      }
    }
    if (!isAvailable) {
      throw new IllegalStateException("Couldn't initialize connection with a database");
    }
  }
}
