package com.huawei.operation.h5pro.jsmodules.healthengine;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.hihealth.listener.CapabilityResultCallback;
import com.huawei.hihealth.option.HiHealthCapabilityQuery;
import com.huawei.operation.h5pro.jsmodules.healthengine.service.HealthEngineService;
import defpackage.ifd;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class HealthyLivingEntry extends JsBaseModule {
    @JavascriptInterface
    public void query(final long j, String str) {
        LogUtil.i(this.TAG, "query enter");
        if (TextUtils.isEmpty(str)) {
            onFailureCallback(j, "param is empty");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("type");
            long optLong = jSONObject.optLong("startTime");
            HiHealthCapabilityQuery a2 = new HiHealthCapabilityQuery.Builder().b(optLong).e(jSONObject.optLong("endTime")).c(optInt).a();
            if (this.mContext == null || this.mH5ProInstance == null) {
                LogUtil.w(this.TAG, "query: context or h5ProInstance is null");
            } else {
                ifd.e(HealthEngineService.INSTANCE.getH5AppContext(this.mContext, this.mH5ProInstance), a2, new CapabilityResultCallback() { // from class: com.huawei.operation.h5pro.jsmodules.healthengine.HealthyLivingEntry.1
                    @Override // com.huawei.hihealth.listener.CapabilityResultCallback
                    public void onResult(int i, String str2) {
                        LogUtil.i(CapabilityResultCallback.TAG, "query onResult: " + i);
                        if (i == 0) {
                            HealthyLivingEntry.this.onSuccessCallback(j, str2);
                        } else {
                            HealthyLivingEntry.this.onFailureCallback(j, str2, i);
                        }
                    }
                });
            }
        } catch (JSONException unused) {
            onFailureCallback(j, "param error");
        }
    }
}
