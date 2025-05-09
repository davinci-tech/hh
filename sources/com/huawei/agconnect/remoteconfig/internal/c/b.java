package com.huawei.agconnect.remoteconfig.internal.c;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import com.huawei.agconnect.AGConnectInstance;
import com.huawei.agconnect.common.api.Logger;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes2.dex */
public class b {
    public static JSONObject a(Map<String, Object> map) {
        String key;
        JSONObject jSONObject = new JSONObject();
        if (map == null) {
            return jSONObject;
        }
        try {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Object value = entry.getValue();
                if (value instanceof String) {
                    key = entry.getKey();
                } else {
                    if (value instanceof Boolean) {
                        key = entry.getKey();
                    } else if (value instanceof Number) {
                        key = entry.getKey();
                    } else {
                        Logger.w("RemoteConfig", "default map ignore key");
                    }
                    value = value.toString();
                }
                jSONObject.putOpt(key, value);
            }
        } catch (JSONException e) {
            Logger.e("RemoteConfig", "default resource json exception", e);
        }
        return jSONObject;
    }

    public static JSONObject a(List<com.huawei.agconnect.remoteconfig.internal.a.b> list) {
        JSONObject jSONObject = new JSONObject();
        if (list == null) {
            return jSONObject;
        }
        try {
            for (com.huawei.agconnect.remoteconfig.internal.a.b bVar : list) {
                String key = bVar.getKey();
                if (key != null && key.length() != 0) {
                    String value = bVar.getValue();
                    if (value == null) {
                        value = "";
                    }
                    jSONObject.putOpt(key, value);
                }
            }
        } catch (JSONException e) {
            Logger.e("RemoteConfig", "parameter json exception", e);
        }
        return jSONObject;
    }

    public static JSONObject a(int i) {
        String str;
        JSONObject jSONObject = new JSONObject();
        try {
            XmlResourceParser xml = AGConnectInstance.getInstance().getContext().getResources().getXml(i);
            int eventType = xml.getEventType();
            while (eventType != 1) {
                if (eventType == 2) {
                    if ("value".equals(xml.getName())) {
                        String attributeValue = xml.getAttributeValue(null, MedalConstants.EVENT_KEY);
                        int next = xml.next();
                        if (next == 4) {
                            String text = xml.getText();
                            int next2 = xml.next();
                            if (next2 == 3 && "value".equals(xml.getName())) {
                                try {
                                    jSONObject.putOpt(attributeValue, text);
                                } catch (JSONException e) {
                                    Logger.e("RemoteConfig", "default resource json exception", e);
                                }
                            }
                            eventType = next2;
                        } else {
                            eventType = next;
                        }
                    }
                }
                eventType = xml.next();
            }
        } catch (Resources.NotFoundException e2) {
            e = e2;
            str = "default resource not found ";
            Logger.e("RemoteConfig", str, e);
            return jSONObject;
        } catch (IOException e3) {
            e = e3;
            str = "default resource io exception";
            Logger.e("RemoteConfig", str, e);
            return jSONObject;
        } catch (XmlPullParserException e4) {
            e = e4;
            str = "default resource xml parser exception";
            Logger.e("RemoteConfig", str, e);
            return jSONObject;
        }
        return jSONObject;
    }

    public static Map<String, String> a(JSONObject jSONObject) {
        HashMap hashMap = new HashMap(10);
        if (jSONObject == null) {
            return hashMap;
        }
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            if (next != null && jSONObject.opt(next) != null) {
                hashMap.put(next, jSONObject.optString(next));
            }
        }
        return hashMap;
    }

    public static List<Map<String, String>> a(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(a(jSONArray.optJSONObject(i)));
        }
        return arrayList;
    }
}
