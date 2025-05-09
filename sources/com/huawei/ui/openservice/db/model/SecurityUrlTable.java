package com.huawei.ui.openservice.db.model;

/* loaded from: classes7.dex */
public class SecurityUrlTable {
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_MODIFY_TIME = "modify_time";
    public static final String COLUMN_SERVICE_ID = "service_id";
    public static final String COLUMN_URL = "url";
    public static final String TABLE_NAME = "security_url_table";

    public static String[] getColumns() {
        return new String[]{"id", "service_id", "url", "modify_time"};
    }

    public static String getCreateTableSQL() {
        return "create table  IF NOT EXISTS security_url_table(id integer primary key not null,service_id text not null,url text,modify_time integer )";
    }
}
