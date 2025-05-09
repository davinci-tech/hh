package com.huawei.health.h5pro.jsbridge.system.option;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.google.gson.Gson;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProBridgeManager;
import com.huawei.health.h5pro.core.H5ProBundle;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.h5pro.jsbridge.base.JsModuleBase;
import com.huawei.health.h5pro.utils.GsonUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.webkit.trustlist.H5ProTrustListChecker;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.operation.ble.BleConstants;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class LaunchOptionEntry extends JsBaseModule {
    public static final Object e = new Object();
    public String b;

    @JavascriptInterface
    public void startH5LightApp(String str) {
        synchronized (e) {
            LogUtil.i(this.TAG, "startH5LightApp");
            if (TextUtils.isEmpty(str)) {
                LogUtil.i(this.TAG, "startH5LightApp: params is null");
                return;
            }
            LightAppStartObj lightAppStartObj = (LightAppStartObj) GsonUtil.parseJson(str, LightAppStartObj.class);
            if (lightAppStartObj == null) {
                LogUtil.i(this.TAG, "startH5LightApp: H5LightAppStartParamsObj is null");
                return;
            }
            final String url = lightAppStartObj.getUrl();
            final H5ProLaunchOption.Builder b = b(lightAppStartObj.getLaunchOption(), lightAppStartObj.getMapJson());
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.huawei.health.h5pro.jsbridge.system.option.LaunchOptionEntry.1
                @Override // java.lang.Runnable
                public void run() {
                    if (LaunchOptionEntry.this.mContext == null || TextUtils.isEmpty(url)) {
                        return;
                    }
                    Context context = LaunchOptionEntry.this.mContext;
                    String str2 = url;
                    H5ProLaunchOption.Builder builder = b;
                    H5ProClient.startH5LightApp(context, str2, builder == null ? null : builder.build());
                }
            });
        }
    }

    @JavascriptInterface
    public void startActivity(String str) {
        LogUtil.i(this.TAG, "startActivity");
        if (TextUtils.isEmpty(str)) {
            LogUtil.w(this.TAG, " params is null");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("type");
            if (TextUtils.isEmpty(optString)) {
                LogUtil.w(this.TAG, "type is empty");
                return;
            }
            String optString2 = jSONObject.optString(BleConstants.KEY_PATH);
            if (TextUtils.isEmpty(optString2)) {
                LogUtil.w(this.TAG, "path is empty");
                return;
            }
            String optString3 = jSONObject.optString("packageName");
            if (!TextUtils.equals(optString.toUpperCase(Locale.ENGLISH), "DEEPLINK")) {
                LogUtil.w(this.TAG, "This type(" + optString + ") is not implemented");
                return;
            }
            if (!H5ProTrustListChecker.isTrustedToLoad(optString2)) {
                LogUtil.w(this.TAG, "untrusted -> " + optString2);
                return;
            }
            Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(optString2));
            if (!TextUtils.isEmpty(optString3)) {
                intent.setPackage(optString3);
            }
            intent.setFlags(268435456);
            if (this.mContext == null) {
                LogUtil.w(this.TAG, "context is null");
            } else {
                this.mContext.startActivity(intent);
            }
        } catch (ActivityNotFoundException | JSONException e2) {
            LogUtil.e(this.TAG, "Exception -> " + e2.getMessage());
        }
    }

    @JavascriptInterface
    public void setCustomizeArg(String str) {
        LogUtil.i(this.TAG, "setCustomizeArg");
        if (TextUtils.isEmpty(str)) {
            LogUtil.i(this.TAG, "setCustomizeArg: mapJson is null");
            return;
        }
        Map<String, Object> parseMapJson = GsonUtil.parseMapJson(str);
        if (parseMapJson.isEmpty()) {
            return;
        }
        if (TextUtils.isEmpty(this.b)) {
            this.b = "{}";
        }
        Map<String, Object> parseMapJson2 = GsonUtil.parseMapJson(this.b);
        for (String str2 : parseMapJson.keySet()) {
            Object obj = parseMapJson.get(str2);
            if (str2 == null || !(obj instanceof String)) {
                LogUtil.i(this.TAG, "Not supported: key = " + str2 + " value = " + obj);
            } else {
                parseMapJson2.put(str2, obj);
            }
        }
        this.b = new Gson().toJson(parseMapJson2);
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule
    public void onCreate(H5ProBundle h5ProBundle) {
        Map<? extends String, ? extends Object> data;
        super.onCreate(h5ProBundle);
        if (h5ProBundle == null || (data = h5ProBundle.getData()) == null || data.isEmpty()) {
            return;
        }
        Map<String, Object> parseMapJson = GsonUtil.parseMapJson(this.b);
        parseMapJson.putAll(data);
        this.b = GsonUtil.toJson(parseMapJson);
    }

    @JavascriptInterface
    public String getLocale() {
        String language = Locale.getDefault().getLanguage();
        String country = Locale.getDefault().getCountry();
        String script = Locale.getDefault().getScript();
        if ("ZH".equalsIgnoreCase(language)) {
            if ("HANS".equalsIgnoreCase(script)) {
                return ProfileRequestConstants.X_LANGUAGE_VALUE;
            }
            if ("HANT".equalsIgnoreCase(script) && "CN".equalsIgnoreCase(country)) {
                return "zh_HK";
            }
        }
        return TextUtils.concat(language, "_", country).toString();
    }

    @JavascriptInterface
    public void getAllCustomizeArgAsync(long j) {
        LogUtil.i(this.TAG, "getAllCustomizeArgAsync==" + this.b);
        onSuccessCallback(j, this.b);
    }

    @JavascriptInterface
    public String getAllCustomizeArg() {
        LogUtil.i(this.TAG, "getAllCustomizeArg==" + this.b);
        return this.b;
    }

    @JavascriptInterface
    public void addCustomizeArg(String str) {
        String str2;
        JSONObject jSONObject;
        LogUtil.i(this.TAG, "addCustomizeArg");
        if (TextUtils.isEmpty(str)) {
            LogUtil.i(this.TAG, "addCustomizeArg: params is null");
            return;
        }
        String str3 = null;
        try {
            jSONObject = new JSONObject(str);
            str2 = jSONObject.getString(MedalConstants.EVENT_KEY);
        } catch (JSONException unused) {
            str2 = null;
        }
        try {
            str3 = jSONObject.getString("value");
        } catch (JSONException unused2) {
            LogUtil.e(this.TAG, "addCustomizeArg===JSONException");
            if (TextUtils.isEmpty(str2)) {
                return;
            } else {
                return;
            }
        }
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return;
        }
        setCustomizeArg("{'" + str2 + "':'" + str3 + "'}");
    }

    private H5ProLaunchOption.Builder b(String str, String str2) {
        Map<String, Object> parseMapJson = GsonUtil.parseMapJson(str2);
        H5ProLaunchOption.Builder builder = !parseMapJson.isEmpty() ? new H5ProLaunchOption.Builder() : null;
        if (builder != null) {
            for (String str3 : parseMapJson.keySet()) {
                Object obj = parseMapJson.get(str3);
                if (str3 == null || !(obj instanceof String)) {
                    LogUtil.i(this.TAG, "Not supported: key = " + str3 + " value = " + obj);
                } else {
                    builder.addCustomizeArg(str3, (String) obj);
                }
            }
        }
        LaunchOptionObj launchOptionObj = (LaunchOptionObj) GsonUtil.parseJson(str, LaunchOptionObj.class);
        if (launchOptionObj == null) {
            return builder;
        }
        if (builder == null) {
            builder = new H5ProLaunchOption.Builder();
        }
        if (launchOptionObj.getBackgroundColor() != -1) {
            builder.setBackgroundColor(launchOptionObj.getBackgroundColor());
        }
        if (!TextUtils.isEmpty(launchOptionObj.getPath())) {
            builder.addPath(launchOptionObj.getPath());
        }
        if (launchOptionObj.getIsImmerse() != 0) {
            builder.setImmerse();
        }
        if (launchOptionObj.getIsNeedSoftInputAdapter() != 0) {
            builder.setNeedSoftInputAdapter();
        }
        if (launchOptionObj.getIsShowStatusBar() != 0) {
            builder.showStatusBar();
        }
        builder.setStatusBarTextBlack(launchOptionObj.getIsStatusBarTextBlack() != 0);
        builder.setForceDarkMode(launchOptionObj.getForceDarkMode());
        builder.setActivityStartFlag(launchOptionObj.getStartFlag());
        return c(launchOptionObj, builder);
    }

    private H5ProLaunchOption.Builder c(LaunchOptionObj launchOptionObj, H5ProLaunchOption.Builder builder) {
        Map<String, Class<? extends JsModuleBase>> customJsModule;
        Class<? extends JsModuleBase> cls;
        if (launchOptionObj != null && launchOptionObj.getIsKeepCustomizeJsModule() != 0 && (customJsModule = H5ProBridgeManager.getInstance().getCustomJsModule(this.mH5ProInstance)) != null && !customJsModule.isEmpty()) {
            for (String str : customJsModule.keySet()) {
                if (!TextUtils.isEmpty(str) && (cls = customJsModule.get(str)) != null) {
                    builder.addCustomizeJsModule(str, cls);
                }
            }
        }
        return builder;
    }

    public LaunchOptionEntry(String str) {
        this.b = TextUtils.isEmpty(str) ? "{}" : str;
    }
}
