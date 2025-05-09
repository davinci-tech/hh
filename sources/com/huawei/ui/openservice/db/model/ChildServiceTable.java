package com.huawei.ui.openservice.db.model;

/* loaded from: classes7.dex */
public class ChildServiceTable {
    public static final String COLUMN_AUTH_TYPE = "auth_type";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_END_DATE = "end_date";
    public static final String COLUMN_HMS_AUTH = "hms_auth";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_IMAGE_URL = "image_Url";
    public static final String COLUMN_KEEP_1 = "keep_1";
    public static final String COLUMN_KEEP_2 = "keep_2";
    public static final String COLUMN_KEEP_3 = "keep_3";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_MODIFY_TIME = "modify_time";
    public static final String COLUMN_POSITION = "position";
    public static final String COLUMN_SERVICE_ID = "service_id";
    public static final String COLUMN_SERVICE_NAME = "service_name";
    public static final String COLUMN_START_DATE = "start_date";
    public static final String COLUMN_SUB_TYPE = "sub_type";
    public static final String COLUMN_URL = "url";
    public static final String TABLE_NAME = "child_service";

    public static String[] getColumns() {
        return new String[]{"id", "service_id", "position", "location", "description", COLUMN_IMAGE_URL, "url", "start_date", "end_date", COLUMN_HMS_AUTH, COLUMN_AUTH_TYPE, "modify_time", "service_name", COLUMN_SUB_TYPE, COLUMN_KEEP_1, COLUMN_KEEP_2, COLUMN_KEEP_3};
    }

    public static String getCreateTableSQL() {
        return "create table  IF NOT EXISTS child_service(id integer primary key not null,service_id text not null,position integer,location text,description text,image_Url text,url text,start_date integer,end_date integer,hms_auth integer,auth_type text,modify_time integer,service_name text,sub_type text,keep_1 text,keep_2 text,keep_3 text)";
    }
}
