package com.huawei.hianalytics.visual.autocollect.instrument;

import android.text.TextUtils;
import android.view.View;
import com.huawei.health.R;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.visual.autocollect.HAWebViewInterface;
import com.huawei.hianalytics.visual.b;
import com.huawei.hianalytics.visual.h0;
import com.huawei.hianalytics.visual.n0;
import com.huawei.hianalytics.visual.t0;
import java.lang.reflect.Method;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class WebViewInstrumentation {
    public static void loadUrl(View view, String str) {
        String substring;
        Method method;
        Method method2;
        if (view == null) {
            HiLog.w("HAWI", "fail to init webView");
            return;
        }
        if (!b.a().b() && b.a().c() && view.getTag(R.id.hianalytics_web_view_visaul_bridge_tag) == null) {
            HiLog.d("VisualUtils", "buildH5VisualBridgeFromAndroid");
            view.setTag(R.id.hianalytics_web_view_visaul_bridge_tag, new Object());
            t0 t0Var = new t0();
            try {
                Class<?> cls = view.getClass();
                try {
                    Object invoke = cls.getMethod("getSettings", new Class[0]).invoke(view, new Object[0]);
                    if (invoke != null && (method2 = invoke.getClass().getMethod("setJavaScriptEnabled", Boolean.TYPE)) != null) {
                        method2.invoke(invoke, Boolean.TRUE);
                    }
                } catch (Exception e) {
                    HiLog.w("VisualUtils", "fail to find getSettings or setJavaScriptEnabled class Exception:" + e.getMessage());
                }
                cls.getMethod("addJavascriptInterface", Object.class, String.class).invoke(view, t0Var, "haVisualJsBridge");
            } catch (Exception e2) {
                HiLog.w("VisualUtils", "fail to find addJavascriptInterface class Exception:" + e2.getMessage());
            }
        }
        if (!b.a().b() && b.a().c() && view.getTag(R.id.hianalytics_web_view_bridge_tag) == null) {
            view.setTag(R.id.hianalytics_web_view_bridge_tag, new Object());
            HAWebViewInterface hAWebViewInterface = new HAWebViewInterface();
            try {
                Class<?> cls2 = view.getClass();
                try {
                    Object invoke2 = cls2.getMethod("getSettings", new Class[0]).invoke(view, new Object[0]);
                    if (invoke2 != null && (method = invoke2.getClass().getMethod("setJavaScriptEnabled", Boolean.TYPE)) != null) {
                        method.invoke(invoke2, Boolean.TRUE);
                    }
                } catch (Exception unused) {
                    HiLog.w("HAWI", "fail to find getSettings or setJavaScriptEnabled class");
                }
                cls2.getMethod("addJavascriptInterface", Object.class, String.class).invoke(view, hAWebViewInterface, "haJsBridge");
            } catch (Exception unused2) {
                HiLog.w("HAWI", "fail to find addJavascriptInterface class");
            }
        }
        if (!TextUtils.isEmpty(str) && str.contains("target_resource_scope")) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("$url", str);
                if (TextUtils.isEmpty(str)) {
                    substring = "";
                } else {
                    String substring2 = str.substring(str.indexOf("target_resource_scope"));
                    int indexOf = substring2.indexOf("&");
                    substring = indexOf == -1 ? substring2.substring(22) : substring2.substring(22, indexOf);
                }
                jSONObject.put("$scope", substring);
                n0.a(h0.b(h0.a(view.getContext(), view)), jSONObject);
                JSONObject jSONObject2 = (JSONObject) view.getTag(R.id.hianalytics_view_custom_property_tag);
                if (jSONObject2 != null) {
                    n0.a(jSONObject2, jSONObject);
                }
                b.a().a("$ResourceClick", jSONObject);
            } catch (Exception unused3) {
                HiLog.w("HAWI", "fail to report resource click event");
            }
        }
    }
}
