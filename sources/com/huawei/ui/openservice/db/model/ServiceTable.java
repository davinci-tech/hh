package com.huawei.ui.openservice.db.model;

/* loaded from: classes7.dex */
public class ServiceTable {
    public static final String COLUMN_END_DATE = "end_date";
    public static final String COLUMN_HOME_PAGE_ICON = "home_page_icon";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_IS_PLUGIN = "is_plugin";
    public static final String COLUMN_IS_SERVICE_CARD = "is_service_card";
    public static final String COLUMN_MODIFY_TIME = "modify_time";
    public static final String COLUMN_NEED_RECOMMEND = "need_recommend";
    public static final String COLUMN_PLUGIN_URL = "plugin_url";
    public static final String COLUMN_SERVICE_APP_ID = "service_app_id";
    public static final String COLUMN_SERVICE_AUTH_TYPE = "service_auth_type";
    public static final String COLUMN_SERVICE_DESCRIPTION = "service_description";
    public static final String COLUMN_SERVICE_DETAIL = "service_detail";
    public static final String COLUMN_SERVICE_HMS_AUTH = "service_hms_auth";
    public static final String COLUMN_SERVICE_ICON = "service_icon";
    public static final String COLUMN_SERVICE_ID = "service_id";
    public static final String COLUMN_SERVICE_KEEP_1 = "service_keep_1";
    public static final String COLUMN_SERVICE_KEEP_2 = "service_keep_2";
    public static final String COLUMN_SERVICE_KEEP_3 = "service_keep_3";
    public static final String COLUMN_SERVICE_KEEP_4 = "service_keep_4";
    public static final String COLUMN_SERVICE_KEEP_5 = "service_keep_5";
    public static final String COLUMN_SERVICE_LABEL = "label";
    public static final String COLUMN_SERVICE_MID_ICON = "service_mid_icon";
    public static final String COLUMN_SERVICE_PRODUCT_NAME = "service_product_name";
    public static final String COLUMN_SERVICE_PROVIDER = "service_provider";
    public static final String COLUMN_SERVICE_REQUIRED_VERSION = "service_required_version";
    public static final String COLUMN_SERVICE_SOURCE = "service_source";
    public static final String COLUMN_SERVICE_TYPE_ID = "service_type";
    public static final String COLUMN_SERVICE_URL = "service_url";
    public static final String COLUMN_START_DATE = "start_date";
    public static final String COLUMN_SUB_POSITION = "sub_position";
    public static final String TABLE_NAME = "service";

    public static String[] getColumns() {
        return new String[]{"id", "service_id", "service_type", COLUMN_SERVICE_PRODUCT_NAME, COLUMN_SERVICE_DESCRIPTION, COLUMN_SERVICE_ICON, COLUMN_HOME_PAGE_ICON, COLUMN_SERVICE_PROVIDER, COLUMN_SERVICE_URL, COLUMN_SERVICE_REQUIRED_VERSION, COLUMN_SERVICE_HMS_AUTH, COLUMN_SERVICE_AUTH_TYPE, "start_date", "end_date", COLUMN_SUB_POSITION, COLUMN_SERVICE_APP_ID, "modify_time", "label", COLUMN_IS_SERVICE_CARD, COLUMN_NEED_RECOMMEND, COLUMN_IS_PLUGIN, COLUMN_PLUGIN_URL, COLUMN_SERVICE_SOURCE, COLUMN_SERVICE_DETAIL, COLUMN_SERVICE_MID_ICON, COLUMN_SERVICE_KEEP_1, COLUMN_SERVICE_KEEP_2, COLUMN_SERVICE_KEEP_3, COLUMN_SERVICE_KEEP_4, COLUMN_SERVICE_KEEP_5};
    }

    public static String getCreateTableSQL() {
        return "create table  IF NOT EXISTS service(id integer primary key not null,service_id text not null,service_type text not null,service_product_name text,service_description text,service_icon text,home_page_icon text,service_provider text,service_url text not null,service_required_version text,service_hms_auth integer,service_auth_type text,start_date integer,end_date integer,sub_position integer,service_app_id text,modify_time integer,label text,is_service_card integer,need_recommend integer,is_plugin integer,plugin_url text,service_source text,service_detail text,service_mid_icon text,service_keep_1 text,service_keep_2 text,service_keep_3 text,service_keep_4 text,service_keep_5 text)";
    }
}
