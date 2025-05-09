package com.huawei.watchface;

import android.text.TextUtils;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.api.HwWatchFaceBtManager;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.mvp.model.datatype.WatchFaceSignBean;
import com.huawei.watchface.mvp.model.thread.BaseHttpRequest;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.MobileInfoHelper;
import com.huawei.watchface.utils.WatchFaceHttpUtil;
import com.huawei.watchface.utils.callback.IBaseResponseCallback;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import java.util.LinkedHashMap;
import org.json.JSONException;
import org.json.JSONObject;

@el(a = {@ek(a = "resultinfo"), @ek(a = "resultcode"), @ek(a = "isDesigner")})
/* loaded from: classes7.dex */
public class u extends BaseHttpRequest<String> {

    /* renamed from: a, reason: collision with root package name */
    private final IBaseResponseCallback f11186a;

    public u(IBaseResponseCallback iBaseResponseCallback) {
        this.f11186a = iBaseResponseCallback;
    }

    private static String c() {
        String str;
        StringBuffer stringBuffer = new StringBuffer(2048);
        WatchFaceSignBean a2 = WatchFaceHttpUtil.a();
        if (a2 != null) {
            str = a2.getSign();
        } else {
            HwLog.e("PostJudgeDesigner", "getDesignerParameters() watchFaceSignBean is null.");
            str = null;
        }
        if (TextUtils.isEmpty(str)) {
            HwLog.e("PostJudgeDesigner", "getDesignerParameters() sign is null.");
            return "";
        }
        stringBuffer.append("sign=").append(str);
        stringBuffer.append("&themeVersion=").append(HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).getWatchFaceMaxVersion(true, false));
        stringBuffer.append("&buildNumber=").append(HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getSoftVersion());
        stringBuffer.append("&versionCode=80003&terminalType=123&deviceType=");
        String deviceType = HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceType();
        if (TextUtils.isEmpty(deviceType)) {
            HwLog.e("PostJudgeDesigner", "getDesignerParameters() deviceType is null.");
            return "";
        }
        stringBuffer.append(deviceType);
        n.a(Environment.getApplicationContext()).a(stringBuffer);
        String deviceId = HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceId();
        if (TextUtils.isEmpty(deviceId)) {
            HwLog.e("PostJudgeDesigner", "getDesignerParameters() deviceId is null.");
            return "";
        }
        stringBuffer.append("&deviceId=").append(deviceId);
        stringBuffer.append("&isVipVersion=").append(CommonUtils.A());
        return stringBuffer.toString();
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    public String a(String str) {
        HwLog.i("PostJudgeDesigner", "getResponse() enter.");
        String c = c();
        if (TextUtils.isEmpty(c)) {
            HwLog.i("PostJudgeDesigner", "getResponse() designerParameters is empty.");
            return "";
        }
        return a(WatchFaceHttpUtil.f(), c, d());
    }

    private LinkedHashMap<String, String> d() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(WatchFaceConstant.X_CLIENTTRACEID, MobileInfoHelper.generateUUID());
        linkedHashMap.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        return linkedHashMap;
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest, java.lang.Runnable
    public void run() {
        String a2 = a("");
        if (TextUtils.isEmpty(a2)) {
            HwLog.i("PostJudgeDesigner", "getDesignerParameters receive json, receive is empty.");
        } else if (!TextUtils.isEmpty(WatchFaceHttpUtil.e())) {
            c(a2);
        } else {
            HwLog.i("PostJudgeDesigner", "getDesignerParameters getBaseUrl() is null");
        }
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public String c(String str) {
        String str2 = null;
        if (str != null) {
            HwLog.i("PostJudgeDesigner", "dealReceive");
            if (!TextUtils.isEmpty(str)) {
                String str3 = "";
                int i = -1;
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if ("success".equals(jSONObject.getString("resultinfo"))) {
                        str3 = jSONObject.getString("isDesigner");
                        i = 0;
                    } else {
                        str2 = jSONObject.optString("resultinfo");
                        HwLog.i("PostJudgeDesigner", "getDesignerParameters error : " + str2);
                    }
                } catch (JSONException e) {
                    HwLog.i("PostJudgeDesigner", "getDesignerParameters is error " + HwLog.printException((Exception) e));
                }
                IBaseResponseCallback iBaseResponseCallback = this.f11186a;
                if (iBaseResponseCallback != null) {
                    iBaseResponseCallback.onResponse(i, str3);
                }
            }
        }
        return str2;
    }
}
