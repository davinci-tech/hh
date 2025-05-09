package com.huawei.ui.openservice.db.model;

/* loaded from: classes7.dex */
public class ServiceVersionTable {
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_MODIFY_TIME = "modify_time";
    public static final String COLUMN_VERSION = "version";
    public static final String TABLE_NAME = "service_version";

    public static String[] getColumns() {
        return new String[]{"id", "version", "date", "modify_time"};
    }

    public static String getCreateTableSQL() {
        return "create table  IF NOT EXISTS service_version(id integer primary key not null,version text,date text,modify_time integer)";
    }
}
