package com.huawei.health.h5pro.jsbridge.system.prerequest;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.h5pro.utils.CommonUtil;
import com.huawei.health.h5pro.utils.GsonUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: classes3.dex */
public class PreRequestEntry extends JsBaseModule {
    public Map<String, PreResult> e = new HashMap(3);
    public Set<String> d = new HashSet();

    public boolean putPreRequestResult(String str, PreResult preResult) {
        synchronized (this) {
            LogUtil.i(this.TAG, "putPreRequestResult enter: " + str);
            if (this.d.contains(str)) {
                return false;
            }
            this.e.put(str, preResult);
            return true;
        }
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule, com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onDestroy() {
        super.onDestroy();
        clearAll();
    }

    @JavascriptInterface
    public String getPreResult(String str) {
        synchronized (this) {
            LogUtil.i(this.TAG, "getPreResult enter");
            if (TextUtils.isEmpty(str)) {
                LogUtil.w(this.TAG, "getPreResult: requestKey is null");
            } else if (CommonUtil.isMapEmpty(this.e)) {
                LogUtil.w(this.TAG, "getPreResult: preResultCache is null");
            } else if (this.d.contains(str)) {
                LogUtil.w(this.TAG, "getPreResult: preResultCache deleted");
            } else {
                PreResult preResult = this.e.get(str);
                if (preResult != null) {
                    return GsonUtil.contentValueToJson(preResult);
                }
                LogUtil.w(this.TAG, "getPreResult: preResult is null");
            }
            return "";
        }
    }

    @JavascriptInterface
    public String getAllPreResults() {
        synchronized (this) {
            LogUtil.i(this.TAG, "getAllPreResults enter");
            if (CommonUtil.isMapEmpty(this.e)) {
                LogUtil.w(this.TAG, "getAllPreResults: preResult is null");
            } else {
                if (!this.d.isEmpty()) {
                    Iterator<String> it = this.d.iterator();
                    while (it.hasNext()) {
                        this.e.remove(it.next());
                    }
                }
                if (!CommonUtil.isMapEmpty(this.e)) {
                    return GsonUtil.contentValueToJson(this.e);
                }
                LogUtil.w(this.TAG, "getAllPreResults: preResult is null");
            }
            return "";
        }
    }

    @JavascriptInterface
    public void clearPreResult(String str) {
        synchronized (this) {
            LogUtil.i(this.TAG, "clearPreResult enter");
            if (TextUtils.isEmpty(str)) {
                LogUtil.w(this.TAG, "clearPreResult: requestKey is null");
            } else if (CommonUtil.isMapEmpty(this.e)) {
                LogUtil.w(this.TAG, "clearPreResult: preResult is null");
            } else {
                this.d.add(str);
                this.e.remove(str);
            }
        }
    }

    @JavascriptInterface
    public void clearAll() {
        synchronized (this) {
            LogUtil.i(this.TAG, "clearAll enter");
            if (CommonUtil.isMapEmpty(this.e)) {
                return;
            }
            this.e.clear();
            this.d.clear();
        }
    }
}
