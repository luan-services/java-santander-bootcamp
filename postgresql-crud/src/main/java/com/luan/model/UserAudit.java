package com.luan.model;

import java.time.OffsetDateTime;

/* represents one immutable history entry created by the database trigger */
public class UserAudit {
    private final Long auditId;
    private final Long userId;
    private final String operation;
    private final String oldData;
    private final String newData;
    private final OffsetDateTime changedAt;

    public UserAudit(
            Long auditId,
            Long userId,
            String operation,
            String oldData,
            String newData,
            OffsetDateTime changedAt) {
        this.auditId = auditId;
        this.userId = userId;
        this.operation = operation;
        this.oldData = oldData;
        this.newData = newData;
        this.changedAt = changedAt;
    }

    public Long getAuditId() {
        return auditId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getOperation() {
        return operation;
    }

    public String getOldData() {
        return oldData;
    }

    public String getNewData() {
        return newData;
    }

    public OffsetDateTime getChangedAt() {
        return changedAt;
    }

    @Override
    public String toString() {
        return "UserAudit{auditId=" + auditId
                + ", userId=" + userId
                + ", operation='" + operation
                + "', oldData=" + oldData
                + ", newData=" + newData
                + ", changedAt=" + changedAt + "}";
    }
}
