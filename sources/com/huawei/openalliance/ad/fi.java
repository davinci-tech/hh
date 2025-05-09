package com.huawei.openalliance.ad;

/* loaded from: classes5.dex */
public enum fi {
    CONTENT_BY_ID_WHERE("contentId = ?"),
    CONTENT_BY_IDS_WHERE("contentId = ? and slotId = ?"),
    CONTENT_BY_TASKID_WHERE("taskId = ?"),
    CONTENT_BY_DATE_NOT_EQUAL_WHERE("displayDate <> ?"),
    CONTENT_PORTRAIT_CACHE_SHOW_WHERE("(creativeType == 12 or width <= height) and startTime <= ? and endTime >= ? and slotId = ? and lastShowTime < ? and fcCtrlDate <> ? and splashPreContentFlag <> 0 and adType=?"),
    CONTENT_LANDSCAPE_CACHE_SHOW_WHERE("(creativeType != 12 and width > height) and startTime <= ? and endTime >= ? and slotId = ? and lastShowTime < ? and fcCtrlDate <> ? and splashPreContentFlag <> 0 and adType=?"),
    CONTENT_PORTRAIT_REAL_SHOW_WHERE("(creativeType == 12 or width <= height) and contentId = ? and slotId = ? and startTime <= ? and endTime >= ? and splashPreContentFlag <> 0"),
    CONTENT_LANDSCAPE_REAL_SHOW_WHERE("(creativeType != 12 and width > height) and contentId = ? and slotId = ? and startTime <= ? and endTime >= ? and splashPreContentFlag <> 0"),
    CONTENT_EXPIRE_WHERE("endTime < ? and splashPreContentFlag <> ?"),
    CONTENT_BY_ADTYPE_WHERE("adType = ?"),
    CONTENT_BY_ADTYPE_AND_WHERE_SLOTID("adType = ? and slotId = ?"),
    THIER_PARTY_EVENT_UPLOAD_ITEMS_WHERE("lockTime < ?"),
    EVENT_REPORT_FAILED_UPDATE_BY_ID_WHERE("_id = ?"),
    THIRD_PARTY_EVENT_UPDATE_BY_ID_WHERE("_id = ?"),
    EVENT_DELETE_BY_ID_WHERE("_id = ?"),
    THIRD_PARTY_EVENT_DELETE_BY_ID_WHERE("_id = ?"),
    USER_CLOSE_DELETE_EXPIRE_WHERE("timeStamp < ?"),
    EVENT_DELETE_EXPIRE_WHERE("time < ? and adType = ?"),
    THIRD_PARTY_EVENT_DELETE_EXPIRE_WHERE("time < ? and adType = ?"),
    USER_CLOSE_QUERY_BY_TIME_WHERE("timeStamp >= ? and timeStamp < ?"),
    TEMPLATE_BY_ID_WHERE("templateId = ?"),
    PLACEMENT_BY_ID_WHERE("contentId = ?"),
    CONTENT_BY_RESOURCE_NAME_AND_CACHETYPE_WHERE("fileName = ? and cacheType = ?"),
    CONTENT_BY_RESOURCE_NAME_WHERE("fileName = ?"),
    CONTENT_BY_CACHETYPE_WHERE("cacheType = ?"),
    CONTENT_BY_CONTENT_ID_WHERE("contentId = ?"),
    CONTENT_BY_CONTENT_ID_AND_RESOURCE_NAME_WHERE("contentId = ? and fileName = ?"),
    CONTENT_BY_RESOURCE_NAME_AND_CONTENTID_AND_CACHETYPE_AND_SLOTID_WHERE("fileName = ? and contentId = ? and cacheType = ? and slotId = ?"),
    EVENT_MONITOR_RECORD_BY_EVENT_ID_WHERE("eventId = ?"),
    EVENT_MONITOR_RECORD_DELETE_EXPIRE_WHERE("addTime < ? and adType = ?"),
    EVENT_MONITOR_RECORD_UPDATE_BY_EVENT_ID_WHERE("eventId = ?"),
    TEMPLATE_STYLE_BY_SLOTID_AND_TEMPLATEID("slotId = ? and templateId = ?"),
    TEMPLATE_STYLE_BY_TEMPLATEID("templateId = ?"),
    TEMPLATE_STYLE_NATIVE_BY_SLOTID("slotId = ? and tmpType <> 1"),
    TEMPLATE_STYLE_H5_BY_SLOTID("slotId = ? and tmpType = 1"),
    TEMPLATE_STYLE_H5("tmpType = 1"),
    CONTENT_RECORD_BY_IDS("contentId = ? and templateId = ? and slotId = ? "),
    CONTENT_TEMPLATE_RECORD_BY_IDS("contentId = ? and templateId = ? "),
    CONTENT_TEMPLATE_BY_ID_WHERE("contentId =? "),
    CONTENT_BY_SLOTID("slotId = ?"),
    CONTENT_V3_REAL_SHOW_WHERE("contentId = ? and templateId = ? and slotId = ? and startTime <= ? and endTime >= ? and splashPreContentFlag <> 0"),
    CONTENT_V3_PORTRAIT_CACHE_SHOW_WHERE("width <= height and startTime <= ? and endTime >= ? and slotId = ? and lastShowTime < ? and fcCtrlDate <> ?"),
    CONTENT_V3_LANDSCAPE_CACHE_SHOW_WHERE("width > height and startTime <= ? and endTime >= ? and slotId = ? and lastShowTime < ? and fcCtrlDate <> ?"),
    APP_DATA_COLLECTION_DELETE_EXPIRE_WHERE("reportTimestamp < ?"),
    APP_DATA_COLLECTION_DELETE_WHERE("_id = ?"),
    DELETED_CONTENT_IDS_WHERE("deleteTimestamp > ?"),
    DELETED_CONTENT_EXPIRE_WHERE("deleteTimestamp < ?"),
    DELETED_CONTENT_BY_ID_WHERE("contentId = ?"),
    CONTENT_CACHED_WHERE("splashMediaPath <> ''"),
    CONTENT_ALL_AVAILABLE_WHERE("endTime > ? and startTime < ? and splashMediaPath <> ? and slotId in (?)"),
    SDKCFG_SHA256_BY_PKG("pkgName = ?");

    private final String Z;

    public String a() {
        return this.Z;
    }

    fi(String str) {
        this.Z = str;
    }
}
