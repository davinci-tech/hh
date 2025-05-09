package com.huawei.watchface;

import android.text.TextUtils;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.api.HwWatchFaceBtManager;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.mvp.model.datatype.WatchFaceSignBean;
import com.huawei.watchface.mvp.model.thread.BaseHttpRequest;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.WatchFaceHttpUtil;
import defpackage.mcq;
import org.json.JSONObject;

@el(a = {@ek(a = "resultinfo"), @ek(a = "resultcode"), @ek(a = "content")})
/* loaded from: classes7.dex */
public class bt extends BaseHttpRequest<Boolean> {
    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    public String a(String str) {
        return a(WatchFaceHttpUtil.w(), str + c(), getReqHeaders());
    }

    private String c() {
        String str;
        StringBuffer stringBuffer = new StringBuffer(2048);
        WatchFaceSignBean b = WatchFaceHttpUtil.b();
        if (b != null) {
            str = b.getSign();
        } else {
            HwLog.e("HitopGetPersonalizedContentThread", "getPersonalizedRequestParams() watchFaceSignBean is null.");
            str = null;
        }
        if (TextUtils.isEmpty(str)) {
            HwLog.e("HitopGetPersonalizedContentThread", "getPersonalizedRequestParams() sign is null.");
            return "";
        }
        stringBuffer.append("sign=").append(str);
        stringBuffer.append("&type=1003&buildNumber=");
        stringBuffer.append(HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getSoftVersion());
        stringBuffer.append("&themeVersion=").append(HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).getWatchFaceMaxVersion(true, false));
        stringBuffer.append("&versionCode=").append(CommonUtils.getVersionCode());
        stringBuffer.append("&terminalType=1&deviceType=");
        String deviceType = HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceType();
        if (TextUtils.isEmpty(deviceType)) {
            HwLog.e("HitopGetPersonalizedContentThread", "getPersonalizedRequestParams() deviceType is null.");
            return "";
        }
        stringBuffer.append(deviceType);
        n.a(Environment.getApplicationContext()).a(stringBuffer);
        String deviceId = HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceId();
        if (TextUtils.isEmpty(deviceId)) {
            HwLog.e("HitopGetPersonalizedContentThread", "getPersonalizedRequestParams() deviceId is null.");
            return "";
        }
        stringBuffer.append("&deviceId=").append(deviceId);
        return stringBuffer.toString();
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    public String a() {
        return CommonUtils.getVersionCode() + "";
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public Boolean c(String str) {
        HwLog.e("HitopGetPersonalizedContentThread", "dealReceive json is empty? = " + mcq.a(str));
        if (mcq.a(str)) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (Integer.parseInt(jSONObject.optString("resultcode")) == 0) {
                String optString = jSONObject.optString("content");
                if (!mcq.a(optString)) {
                    String optString2 = new JSONObject(optString).optString("HWHDRecommendMsg");
                    if (!mcq.a(optString2) && "0".equalsIgnoreCase(optString2)) {
                        return false;
                    }
                }
                return true;
            }
            HwLog.e("HitopGetPersonalizedContentThread", "dealReceive resultCode != 0");
            return false;
        } catch (Exception e) {
            HwLog.e("HitopGetPersonalizedContentThread", "handleJsonData exception: " + HwLog.printException(e));
            return false;
        }
    }
}
