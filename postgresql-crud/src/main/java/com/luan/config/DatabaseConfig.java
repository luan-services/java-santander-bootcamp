package com.luan.config;

public final class DatabaseConfig {
    private static final String URL_VARIABLE = "DB_URL";
    private static final String USER_VARIABLE = "DB_USER";
    private static final String PASSWORD_VARIABLE = "DB_PASSWORD";

    private DatabaseConfig() {
    }

    /* reads the database URL without storing connection data in the source code */
    public static String getUrl() {
        return getRequiredEnvironmentVariable(URL_VARIABLE);
    }

    /* reads the database user from the operating system environment */
    public static String getUser() {
        return getRequiredEnvironmentVariable(USER_VARIABLE);
    }

    /* reads the database password from the operating system environment */
    public static String getPassword() {
        return getRequiredEnvironmentVariable(PASSWORD_VARIABLE);
    }

    /* fails early with a clear message when required configuration is absent */
    private static String getRequiredEnvironmentVariable(String variableName) {
        String value = System.getenv(variableName);

        if (value == null || value.isBlank()) {
            throw new IllegalStateException(
                    "Required environment variable is missing: " + variableName);
        }

        return value;
    }
}
