package com.luan.config;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.output.MigrateResult;

public final class DatabaseMigration {
    private DatabaseMigration() {
    }

    public static MigrateResult migrate() {
        /* creates a Flyway instance using the configured PostgreSQL connection */
        Flyway flyway = Flyway.configure()
                .dataSource(
                        DatabaseConfig.getUrl(),
                        DatabaseConfig.getUser(),
                        DatabaseConfig.getPassword())
                .load();

        /* validates previous migrations and applies only the pending ones */
        return flyway.migrate();
    }
}
