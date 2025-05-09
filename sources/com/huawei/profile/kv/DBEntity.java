package com.huawei.profile.kv;

import com.huawei.profile.utils.logger.DsLog;

/* loaded from: classes6.dex */
public class DBEntity {
    private String entityKey;
    private String entityValue;

    public DBEntity() {
    }

    public DBEntity(String str, String str2) {
        this.entityKey = str;
        this.entityValue = str2;
    }

    public void setEntityKey(String str) {
        this.entityKey = str;
    }

    public void setEntityValue(String str) {
        this.entityValue = str;
    }

    public String getEntityKey() {
        return this.entityKey;
    }

    public String getEntityValue() {
        return this.entityValue;
    }

    public boolean verify() {
        String str = this.entityKey;
        if (str == null || this.entityValue == null) {
            DsLog.et("DBEntity", "entityKey or entityValue is null", new Object[0]);
            return false;
        }
        if (!str.isEmpty() && !this.entityValue.isEmpty()) {
            return true;
        }
        DsLog.et("DBEntity", "entityKey or entityValue's length less than 1", new Object[0]);
        return false;
    }

    public String toString() {
        return "entityKey is " + getEntityKey() + ", entityValue " + getEntityValue();
    }
}
