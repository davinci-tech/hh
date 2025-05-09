package com.huawei.hwcloudmodel.model.unite;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class DeviceServiceInfo {
    private static final String KEY_DEVUSERINFO = "devUserInfo";
    private static final String KEY_NEW_DEVUSERINFO = "devUserInfoNew";
    private static final String TAG = "DeviceServiceInfo";
    private Map<String, String> data;
    private String sid;
    private String userData;

    public String getUserData() {
        return this.userData;
    }

    public void setUserData(String str) {
        this.userData = str;
    }

    public void setSid(String str) {
        this.sid = str;
    }

    public String getSid() {
        return this.sid;
    }

    public void setData(Map<String, String> map) {
        this.data = map;
    }

    public Map<String, String> getData() {
        return this.data;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("DeviceServiceInfo{sid='");
        stringBuffer.append(this.sid).append("', data='");
        stringBuffer.append(this.data).append("', userData='");
        stringBuffer.append(this.userData).append("'}");
        return stringBuffer.toString();
    }

    public void toObject(JSONObject jSONObject) {
        try {
            if (jSONObject.has("sid")) {
                setSid(jSONObject.getString("sid"));
            } else {
                LogUtil.a(TAG, "toObject not has sid");
            }
            if (jSONObject.has("data")) {
                if (KEY_NEW_DEVUSERINFO.equals(this.sid)) {
                    setUserData(jSONObject.get("data").toString());
                }
                if (KEY_DEVUSERINFO.equals(this.sid)) {
                    Object obj = jSONObject.get("data");
                    if (obj instanceof JSONObject) {
                        Iterator<String> keys = ((JSONObject) obj).keys();
                        while (keys.hasNext()) {
                            if (keys.next().contains("id_")) {
                                setUserData(obj.toString());
                                return;
                            }
                        }
                        setDataMap(jSONObject.getJSONObject("data"));
                        return;
                    }
                    if (obj instanceof JSONArray) {
                        setUserData(obj.toString());
                        return;
                    } else {
                        LogUtil.b(TAG, " toObject(),data type error = ", obj.toString());
                        return;
                    }
                }
                setDataMap(jSONObject.getJSONObject("data"));
                return;
            }
            LogUtil.a(TAG, "toObject not has data");
        } catch (JSONException e) {
            LogUtil.b(TAG, " toObject(),JSONException= ", e.getMessage());
        }
    }

    private void setDataMap(JSONObject jSONObject) throws JSONException {
        this.data = new HashMap(16);
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            if (!TextUtils.isEmpty(next)) {
                this.data.put(next, jSONObject.getString(next));
            }
        }
    }
}
