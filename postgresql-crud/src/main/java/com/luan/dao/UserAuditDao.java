package com.luan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.luan.config.ConnectionFactory;
import com.luan.exception.DatabaseException;
import com.luan.model.UserAudit;

public class UserAuditDao {
    private static final String FIND_ALL_AUDIT_ENTRIES = """
            SELECT audit_id, user_id, operation, old_data, new_data, changed_at
            FROM user_audit
            ORDER BY audit_id
            """;

    public List<UserAudit> findAll() {
        List<UserAudit> entries = new ArrayList<>();

        try (Connection connection = ConnectionFactory.openConnection();
                PreparedStatement statement = connection.prepareStatement(FIND_ALL_AUDIT_ENTRIES);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                entries.add(new UserAudit(
                        resultSet.getLong("audit_id"),
                        resultSet.getLong("user_id"),
                        resultSet.getString("operation"),
                        resultSet.getString("old_data"),
                        resultSet.getString("new_data"),
                        resultSet.getObject("changed_at", OffsetDateTime.class)));
            }

            return entries;
        } catch (SQLException exception) {
            throw new DatabaseException("Could not query the user audit history", exception);
        }
    }
}
