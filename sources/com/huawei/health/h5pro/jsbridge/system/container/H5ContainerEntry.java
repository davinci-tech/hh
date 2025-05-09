package com.huawei.health.h5pro.jsbridge.system.container;

import android.app.Activity;
import android.content.Context;
import android.provider.Settings;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import com.huawei.health.h5pro.core.ImmerseInfo;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.h5pro.utils.CommonUtil;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class H5ContainerEntry extends JsBaseModule {
    public WeakReference<H5Container> e;

    @JavascriptInterface
    public void setWindowBrightness(final long j, String str) {
        if (!(this.mContext instanceof Activity)) {
            LogUtil.e(this.TAG, "setWindowBrightness: context is null or is not a activity.");
            onFailureCallback(j, "context is null or is not a activity");
            return;
        }
        try {
            String optString = new JSONObject(str).optString("brightness", "-1");
            if (!CommonUtil.isNumber(optString)) {
                LogUtil.w(this.TAG, "setWindowBrightness: brightness is not a number.");
                onFailureCallback(j, "brightness is not a number");
                return;
            }
            final float parseFloat = Float.parseFloat(optString);
            if (parseFloat >= 0.0f && parseFloat <= 1.0f) {
                final Activity activity = (Activity) this.mContext;
                activity.runOnUiThread(new Runnable() { // from class: com.huawei.health.h5pro.jsbridge.system.container.H5ContainerEntry.2
                    @Override // java.lang.Runnable
                    public void run() {
                        Window window = activity.getWindow();
                        WindowManager.LayoutParams attributes = window.getAttributes();
                        attributes.screenBrightness = parseFloat;
                        window.setAttributes(attributes);
                        H5ContainerEntry.this.onSuccessCallback(j);
                    }
                });
                return;
            }
            LogUtil.w(this.TAG, "setWindowBrightness: brightness is between 0 and 1.");
            onFailureCallback(j, "brightness is between 0 and 1");
        } catch (JSONException e) {
            LogUtil.e(this.TAG, "setWindowBrightness: JSONException->" + e.getMessage());
            onFailureCallback(j, e.getMessage());
        }
    }

    @JavascriptInterface
    public void setScreenLandscape(String str) {
        H5Container h5Container = (H5Container) GeneralUtil.getReferent(this.e);
        if (h5Container != null) {
            try {
                h5Container.setScreenLandscape(new JSONObject(str).optBoolean("isLandscape"));
            } catch (JSONException e) {
                LogUtil.e(this.TAG, "setScreenLandscape: JSONException" + e.getMessage());
            }
        }
    }

    @JavascriptInterface
    public void setPageTitle(String str) {
        H5Container h5Container = (H5Container) GeneralUtil.getReferent(this.e);
        if (h5Container != null) {
            h5Container.setPageTitle(str);
        }
    }

    @JavascriptInterface
    public void setImmerse(String str) {
        H5Container h5Container = (H5Container) GeneralUtil.getReferent(this.e);
        if (h5Container != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                h5Container.setImmerse(new ImmerseInfo(jSONObject.optBoolean("isImmerse"), jSONObject.optBoolean("isShowStatusBar", true), jSONObject.optBoolean("isStatusBarTextBlack"), jSONObject.optBoolean("isStartAtBottomOfStatusBar"), jSONObject.optBoolean("isHideBottomVirtualKeys", false)));
            } catch (JSONException e) {
                LogUtil.e(this.TAG, "setImmerse: JSONException" + e.getMessage());
            }
        }
    }

    @JavascriptInterface
    public void registryTitleBarCallback(long j, String str) {
        H5Container h5Container = (H5Container) GeneralUtil.getReferent(this.e);
        if (h5Container != null) {
            h5Container.registryTitleBarCallback(j, str);
        }
    }

    @JavascriptInterface
    public void keepScreenOn(String str) {
        H5Container h5Container = (H5Container) GeneralUtil.getReferent(this.e);
        if (h5Container != null) {
            try {
                h5Container.keepScreenOn(new JSONObject(str).optBoolean("isOpen"));
            } catch (JSONException e) {
                LogUtil.e(this.TAG, "keepScreenOn: JSONException" + e.getMessage());
            }
        }
    }

    @JavascriptInterface
    public void interceptWebViewPause() {
        H5ProInstance h5ProInstance = this.mH5ProInstance;
        if (h5ProInstance == null) {
            LogUtil.w(this.TAG, "interceptWebViewPause: mH5ProInstance is null");
        } else {
            h5ProInstance.interceptWebViewPause(true);
        }
    }

    @JavascriptInterface
    public void hideTitleBarIcon(String str) {
        H5Container h5Container = (H5Container) GeneralUtil.getReferent(this.e);
        if (h5Container != null) {
            h5Container.hideTitleBarIcon(str);
        }
    }

    @JavascriptInterface
    public void goBack() {
        H5Container h5Container = (H5Container) GeneralUtil.getReferent(this.e);
        if (h5Container != null) {
            h5Container.goBack();
        }
    }

    @JavascriptInterface
    public void getWindowBrightness(final long j) {
        Context context = this.mContext;
        if (!(context instanceof Activity)) {
            LogUtil.w(this.TAG, "getWindowBrightness: context is null or is not a activity.");
            onFailureCallback(j, "context is null or is not a activity");
            return;
        }
        Activity activity = (Activity) context;
        float f = activity.getWindow().getAttributes().screenBrightness;
        if (f < 0.0f) {
            activity.runOnUiThread(new Runnable() { // from class: com.huawei.health.h5pro.jsbridge.system.container.H5ContainerEntry.1
                @Override // java.lang.Runnable
                public void run() {
                    H5ContainerEntry.this.onSuccessCallback(j, Double.valueOf(new BigDecimal(Settings.System.getInt(H5ContainerEntry.this.mContext.getContentResolver(), "screen_brightness", 125)).divide(new BigDecimal(255), 1, 4).doubleValue()));
                }
            });
        } else {
            onSuccessCallback(j, Float.valueOf(f));
        }
    }

    @JavascriptInterface
    public void exitWithResult(String str) {
        H5Container h5Container = (H5Container) GeneralUtil.getReferent(this.e);
        if (h5Container != null) {
            h5Container.exitWithResult(str);
        }
    }

    @JavascriptInterface
    public void exit() {
        H5Container h5Container = (H5Container) GeneralUtil.getReferent(this.e);
        if (h5Container != null) {
            h5Container.exit();
        }
    }

    public H5ContainerEntry(H5Container h5Container) {
        this.e = new WeakReference<>(h5Container);
    }
}
