package com.huawei.ui.openservice.db.model;

/* loaded from: classes7.dex */
public class ServiceTypeTable {
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_MODIFY_TIME = "modify_time";
    public static final String COLUMN_SERVICE_TYPE_DESCRIPTION = "service_type_description";
    public static final String COLUMN_SERVICE_TYPE_ID = "service_type_id";
    public static final String COLUMN_SERVICE_TYPE_NAME = "service_type_Name";
    public static final String COLUMN_SERVICE_TYPE_POSITION = "service_type_position";
    public static final String TABLE_NAME = "service_type";

    public static String[] getColumns() {
        return new String[]{"id", COLUMN_SERVICE_TYPE_ID, COLUMN_SERVICE_TYPE_NAME, COLUMN_SERVICE_TYPE_DESCRIPTION, COLUMN_SERVICE_TYPE_POSITION, "modify_time"};
    }

    public static String getCreateTableSQL() {
        return "create table  IF NOT EXISTS service_type(id integer primary key not null,service_type_id text,service_type_Name text,service_type_description text,service_type_position integer,modify_time integer )";
    }
}
