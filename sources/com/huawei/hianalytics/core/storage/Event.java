package com.huawei.hianalytics.core.storage;

import android.text.TextUtils;
import com.huawei.hianalytics.core.log.HiLog;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class Event implements Cloneable {
    public static final Property COLUMN_CONTENT;
    public static final Property COLUMN_EVT_EX_HASH_CODE;
    public static final Property COLUMN_EVT_ID;
    public static final Property COLUMN_EVT_TIME;
    public static final Property COLUMN_EVT_TYPE;
    public static final Property COLUMN_ID;
    public static final Property COLUMN_IS_ENCRYPTED;
    public static final Property COLUMN_PROCESS_NAME;
    public static final Property COLUMN_SERVICE_TAG;
    public static final Property COLUMN_SESSION_ID;
    public static final Property COLUMN_SESSION_NAME;
    public static final Property COLUMN_SUB_COUNT;
    public static final Property[] PROPERTIES;
    public static final String TABLE_NAME = "EVENT";
    public static final String TAG = "Event";
    public String content;
    public String evtExHashCode;
    public String evtid;
    public String evttime;
    public String evttype;
    public Long id;
    public int isEncrypted;
    public String processname;
    public String servicetag;
    public String sessionid;
    public String sessionname;
    public int subCount;

    /* loaded from: classes8.dex */
    public interface EventConstants {
        public static final String ABS_TIME = "ha_abs_time";
        public static final String DATA_TYPE = "type";
        public static final String EVENTS = "events";
        public static final String EVENT_ID = "event";
        public static final String EVENT_ID_AGGREGATE = "ha_event_aggregate";
        public static final String IS_FIRST_SESSION = "first_session_event";
        public static final String NC_COMMON_FLAG = "nc_common_flag";
        public static final String PROPERTIES = "properties";
        public static final String RECORD_TIME = "eventtime";
        public static final String SESSION_NAME = "event_session_name";
        public static final String SUB_COUNT = "sub_count";
    }

    public void setSubCount(int i) {
        this.subCount = i;
    }

    public void setSessionname(String str) {
        this.sessionname = str;
    }

    public void setSessionid(String str) {
        this.sessionid = str;
    }

    public void setServicetag(String str) {
        this.servicetag = str;
    }

    public void setProcessname(String str) {
        this.processname = str;
    }

    public void setIsEncrypted(int i) {
        this.isEncrypted = i;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public void setEvttype(String str) {
        this.evttype = str;
    }

    public void setEvttime(String str) {
        this.evttime = str;
    }

    public void setEvtid(String str) {
        this.evtid = str;
    }

    public void setEvtExHashCode(String str) {
        this.evtExHashCode = str;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public int getSubCount() {
        return this.subCount;
    }

    public String getSessionname() {
        return this.sessionname;
    }

    public String getSessionid() {
        return this.sessionid;
    }

    public String getServicetag() {
        return this.servicetag;
    }

    public String getProcessname() {
        return this.processname;
    }

    public int getIsEncrypted() {
        return this.isEncrypted;
    }

    public Long getId() {
        return this.id;
    }

    public String getEvttype() {
        return this.evttype;
    }

    public String getEvttime() {
        return this.evttime;
    }

    public String getEvtid() {
        return this.evtid;
    }

    public String getEvtExHashCode() {
        return this.evtExHashCode;
    }

    public String getContent() {
        return this.content;
    }

    public void formJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        this.evtid = jSONObject.optString("event");
        this.evttime = jSONObject.optString(EventConstants.RECORD_TIME);
        this.evttype = jSONObject.optString("type");
        if (!TextUtils.isEmpty(EventConstants.NC_COMMON_FLAG)) {
            this.evtExHashCode = jSONObject.optString(EventConstants.NC_COMMON_FLAG);
        }
        if (!TextUtils.isEmpty(jSONObject.optString(EventConstants.IS_FIRST_SESSION))) {
            this.sessionid = jSONObject.optString(EventConstants.IS_FIRST_SESSION);
        }
        if (!TextUtils.isEmpty(jSONObject.optString(EventConstants.SESSION_NAME))) {
            this.sessionname = jSONObject.optString(EventConstants.SESSION_NAME);
        }
        this.subCount = jSONObject.optInt(EventConstants.SUB_COUNT, 1);
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public Event m547clone() {
        return (Event) super.clone();
    }

    public boolean appendToJsonArray(JSONArray jSONArray) {
        if (this.subCount <= 1) {
            Object json = toJson();
            if (json == null) {
                return false;
            }
            jSONArray.put(json);
            return true;
        }
        try {
            JSONArray optJSONArray = new JSONObject(this.content).optJSONArray(EventConstants.EVENTS);
            if (optJSONArray != null && optJSONArray.length() != 0) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        optJSONObject.put("type", this.evttype);
                        jSONArray.put(optJSONObject);
                    }
                }
                return true;
            }
        } catch (JSONException unused) {
        }
        return false;
    }

    private JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", this.evttype);
            jSONObject.put(EventConstants.RECORD_TIME, this.evttime);
            jSONObject.put("event", this.evtid);
            jSONObject.put(EventConstants.SESSION_NAME, this.sessionname);
            jSONObject.put(EventConstants.IS_FIRST_SESSION, this.sessionid);
            String str = this.content;
            if (str != null && !str.isEmpty()) {
                jSONObject.put(EventConstants.PROPERTIES, new JSONObject(this.content));
                return jSONObject;
            }
            HiLog.sw(TAG, "content is empty: evtId:" + this.evtid);
            return null;
        } catch (JSONException e) {
            HiLog.e(TAG, "JSONException: " + e.getMessage());
            return null;
        }
    }

    public Event(Long l, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, int i, int i2) {
        this.id = l;
        this.evtid = str;
        this.evttype = str2;
        this.content = str3;
        this.evttime = str4;
        this.servicetag = str5;
        this.sessionid = str6;
        this.sessionname = str7;
        this.evtExHashCode = str8;
        this.processname = str9;
        this.isEncrypted = i;
        this.subCount = i2;
    }

    public Event() {
        this.isEncrypted = 1;
        this.subCount = 1;
    }

    static {
        Property property = new Property(Long.class, "_id");
        COLUMN_ID = property;
        Property property2 = new Property(String.class, "EVTID");
        COLUMN_EVT_ID = property2;
        Property property3 = new Property(String.class, "EVTTYPE");
        COLUMN_EVT_TYPE = property3;
        Property property4 = new Property(String.class, "CONTENT");
        COLUMN_CONTENT = property4;
        Property property5 = new Property(String.class, "EVTTIME");
        COLUMN_EVT_TIME = property5;
        Property property6 = new Property(String.class, "SERVICETAG");
        COLUMN_SERVICE_TAG = property6;
        Property property7 = new Property(String.class, "SESSIONID");
        COLUMN_SESSION_ID = property7;
        Property property8 = new Property(String.class, "SESSIONNAME");
        COLUMN_SESSION_NAME = property8;
        Property property9 = new Property(String.class, "EVT_EX_HASH_CODE");
        COLUMN_EVT_EX_HASH_CODE = property9;
        Property property10 = new Property(String.class, "PROCESSNAME");
        COLUMN_PROCESS_NAME = property10;
        Property property11 = new Property(Integer.TYPE, "IS_ENCRYPTED");
        COLUMN_IS_ENCRYPTED = property11;
        Property property12 = new Property(Integer.TYPE, "SUB_COUNT");
        COLUMN_SUB_COUNT = property12;
        PROPERTIES = new Property[]{property, property2, property3, property4, property5, property6, property7, property8, property9, property10, property11, property12};
    }
}
