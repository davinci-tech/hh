package com.huawei.hianalytics.visual;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.core.storage.Event;
import com.huawei.hianalytics.framework.utils.JsonUtils;
import com.huawei.hianalytics.visual.view.model.config.VisualConfig;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class s0 {

    /* renamed from: a, reason: collision with root package name */
    public final r0 f3948a;
    public List<VisualConfig> b;
    public List<String> c;
    public String d;

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public static final s0 f3949a = new s0();
    }

    public s0() {
        r0 r0Var = new r0();
        this.f3948a = r0Var;
        this.b = r0Var.b();
        this.c = r0Var.a();
        this.d = a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Context context, HAAutoConfigOptions hAAutoConfigOptions) {
        String replace;
        try {
            String createABReqDefaultJson = JsonUtils.createABReqDefaultJson();
            if (context == null) {
                HiLog.e("RemoteConfigRequestBackend", "build request url but context is null");
                replace = "";
            } else {
                String remoteConfigUrl = hAAutoConfigOptions.getRemoteConfigUrl();
                if (TextUtils.isEmpty(remoteConfigUrl)) {
                    HiLog.d("RemoteConfigRequestBackend", "build remote url is null, get url from grs");
                    remoteConfigUrl = com.huawei.hianalytics.visual.a.a(context, "ABCONFIGURL");
                }
                replace = (remoteConfigUrl + "/abtest/1.0/${package_name}/WiseUBA/config").replace("${package_name}", n0.a(context));
            }
            String a2 = com.huawei.hianalytics.visual.a.a(createABReqDefaultJson, replace, "POST");
            if (TextUtils.isEmpty(a2)) {
                HiLog.e("RemoteConfigManager", "fail to request remote config");
                return;
            }
            HiLog.d("RemoteConfigManager", "success to request remote config");
            this.f3948a.f3947a.a(a2);
            this.b = this.f3948a.b();
            this.c = this.f3948a.a();
            this.d = a();
        } catch (Throwable unused) {
            HiLog.e("RemoteConfigManager", "fail to request remote config");
        }
    }

    public final String a() {
        List<VisualConfig> list;
        VisualConfig.VisualEvent visualEvent;
        if (this.b == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            list = this.b;
        } catch (JSONException e) {
            HiLog.w("RemoteConfigManager", "fail to merge visual page config, ex: " + e.getMessage());
        }
        if (list.isEmpty()) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (VisualConfig visualConfig : list) {
            if (visualConfig.isH5 && (visualEvent = visualConfig.event) != null) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("os", visualConfig.os);
                jSONObject2.put("packageName", visualConfig.packageName);
                jSONObject2.put("version", visualConfig.version);
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("eventName", visualEvent.eventName);
                jSONObject3.put("eventType", visualEvent.eventType);
                jSONObject3.put("isCollect", visualEvent.isCollect);
                jSONObject3.put("limitViewPosition", visualEvent.limitViewPosition);
                jSONObject3.put("limitViewContent", visualEvent.limitViewContent);
                jSONObject3.put("url", visualEvent.url);
                jSONObject3.put("viewContent", visualEvent.viewContent);
                jSONObject3.put("viewPath", visualEvent.viewPath);
                jSONObject3.put("viewPosition", visualEvent.viewPosition);
                jSONObject2.put("event", jSONObject3);
                List<VisualConfig.VisualProperty> list2 = visualConfig.property;
                JSONArray jSONArray2 = new JSONArray();
                if (list2 != null && !list2.isEmpty()) {
                    for (VisualConfig.VisualProperty visualProperty : list2) {
                        if (!TextUtils.equals(visualProperty.propertyType, "DYNAMIC_PROPERTY") || !TextUtils.isEmpty(visualProperty.viewPath)) {
                            JSONObject jSONObject4 = new JSONObject();
                            jSONObject4.put("name", visualProperty.name);
                            jSONObject4.put("propertyType", visualProperty.propertyType);
                            jSONObject4.put("regular", visualProperty.regular);
                            jSONObject4.put("type", TextUtils.equals(visualProperty.type, "string") ? visualProperty.type : "number");
                            jSONObject4.put("viewContent", visualProperty.viewContent);
                            jSONObject4.put("viewPath", visualProperty.viewPath);
                            jSONArray2.put(jSONObject4);
                        }
                    }
                }
                jSONObject2.put(Event.EventConstants.PROPERTIES, jSONArray2);
                jSONArray.put(jSONObject2);
            }
        }
        jSONObject.put(Event.EventConstants.EVENTS, jSONArray);
        return jSONObject.toString();
    }

    public void a(final Context context, final HAAutoConfigOptions hAAutoConfigOptions) {
        m0.a().execute(new Runnable() { // from class: com.huawei.hianalytics.visual.s0$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                s0.this.b(context, hAAutoConfigOptions);
            }
        });
    }

    public final List<VisualConfig> a(List<VisualConfig> list, String str, String str2, String str3, String str4, String str5) {
        ArrayList arrayList = new ArrayList();
        for (VisualConfig visualConfig : list) {
            VisualConfig.VisualEvent visualEvent = visualConfig.event;
            if (visualEvent != null && TextUtils.equals(visualEvent.eventType, "VIEW_CLICK") && n0.a(visualEvent.pageName, str)) {
                if (!TextUtils.equals(visualEvent.viewPath, str2)) {
                    HiLog.i("RemoteConfigManager", "view_path is not match, view_path: " + str2 + ", config view_path: " + visualEvent.viewPath);
                } else if (!TextUtils.equals(visualEvent.viewId, str5)) {
                    HiLog.i("RemoteConfigManager", "view_id is not match, view_id: " + str5 + ", config view_id: " + visualEvent.viewId);
                } else if (visualEvent.limitViewPosition && !TextUtils.equals(visualEvent.viewPosition, str3)) {
                    HiLog.i("RemoteConfigManager", "view_position is not match, view_position: " + str3 + ", config view_position: " + visualEvent.viewPosition);
                } else if (visualEvent.limitViewContent && !TextUtils.equals(visualEvent.viewContent, str4)) {
                    HiLog.i("RemoteConfigManager", "view_content is not match, view_content: " + str4 + ", config view_content: " + visualEvent.viewContent);
                } else {
                    arrayList.add(visualConfig);
                }
            }
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0147  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(com.huawei.hianalytics.visual.view.model.config.VisualConfig.VisualEvent r16, java.util.List<com.huawei.hianalytics.visual.view.model.config.VisualConfig.VisualProperty> r17, org.json.JSONObject r18, com.huawei.hianalytics.visual.u0 r19) {
        /*
            Method dump skipped, instructions count: 353
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hianalytics.visual.s0.a(com.huawei.hianalytics.visual.view.model.config.VisualConfig$VisualEvent, java.util.List, org.json.JSONObject, com.huawei.hianalytics.visual.u0):void");
    }

    public final void a(JSONObject jSONObject, String str, String str2, String str3, String str4) {
        try {
            JSONObject optJSONObject = jSONObject.optJSONObject("$custom_property");
            if (optJSONObject == null) {
                optJSONObject = new JSONObject();
            }
            boolean isEmpty = TextUtils.isEmpty(str3);
            if (!isEmpty && (TextUtils.equals("int", str) || TextUtils.equals("float", str))) {
                optJSONObject.put(str2, NumberFormat.getInstance(Locale.US).parse(str3));
            } else if (!isEmpty && TextUtils.equals("string", str)) {
                optJSONObject.put(str2, str3);
            }
            if (!TextUtils.isEmpty(str4)) {
                optJSONObject.put("$view_checked", str4);
            }
            jSONObject.put("$custom_property", optJSONObject);
        } catch (Exception unused) {
            HiLog.w("RemoteConfigManager", "fail to add property to click event");
        }
    }
}
