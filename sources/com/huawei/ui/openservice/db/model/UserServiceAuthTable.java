package com.huawei.ui.openservice.db.model;

/* loaded from: classes7.dex */
public class UserServiceAuthTable {
    public static final String COLUMN_HUID = "huid";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_MODIFY_TIME = "modify_time";
    public static final String COLUMN_SERVICE_AUTH = "service_auth";
    public static final String COLUMN_SERVICE_ID = "service_id";
    public static final String TABLE_NAME = "user_service_auth";

    public static String[] getColumns() {
        return new String[]{"id", "huid", "service_id", COLUMN_SERVICE_AUTH, "modify_time"};
    }

    public static String getCreateTableSQL() {
        return "create table  IF NOT EXISTS user_service_auth(id integer primary key not null,huid text not null,service_id text,service_auth integer,modify_time integer )";
    }
}
