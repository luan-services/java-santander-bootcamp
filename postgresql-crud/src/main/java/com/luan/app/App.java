package com.luan.app;

import org.flywaydb.core.api.output.MigrateResult;

import com.luan.config.DatabaseMigration;

public class App {
    public static void main(String[] args) {
        /* migrations run before code that depends on the database structure */
        MigrateResult result = DatabaseMigration.migrate();

        System.out.println("Database migration completed. Migrations executed: "
                + result.migrationsExecuted);
    }
}
