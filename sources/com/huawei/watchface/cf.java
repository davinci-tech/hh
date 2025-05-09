package com.huawei.watchface;

import android.os.Build;
import android.text.TextUtils;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import com.huawei.secure.android.common.encrypt.hash.HMACSHA256;
import com.huawei.secure.android.common.util.SafeString;
import com.huawei.ui.main.stories.nps.component.NpsConstantValue;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.mvp.model.webview.JsInterfaceMarketing;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.DensityUtil;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.MobileInfoHelper;
import com.huawei.watchface.utils.WatchFaceHttpUtil;
import java.util.LinkedHashMap;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class cf extends bh<ae> {

    /* renamed from: a, reason: collision with root package name */
    private String f10945a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    public boolean b() {
        return false;
    }

    public cf(String str, String str2, String str3, String str4, String str5, String str6) {
        this.f10945a = str2;
        this.b = str3;
        this.c = str4;
        this.d = str5;
        this.e = str;
        this.f = str6;
    }

    public String d() {
        return a(WatchFaceHttpUtil.u(), e(), getReqHeaders());
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    public LinkedHashMap<String, String> getReqHeaders() {
        LinkedHashMap<String, String> c = c();
        String generateDeviceIDWithSeparator = MobileInfoHelper.generateDeviceIDWithSeparator();
        if (generateDeviceIDWithSeparator.contains(":")) {
            c.put("x-deviceid", SafeString.substring(generateDeviceIDWithSeparator, 0, generateDeviceIDWithSeparator.indexOf(":")));
        }
        c.put("token", this.d);
        if (WatchFaceHttpUtil.a() != null) {
            c.put(HwPayConstant.KEY_SIGN, WatchFaceHttpUtil.a().getSign());
        }
        c.put(ProfileRequestConstants.X_APPID_KEY, NpsConstantValue.QUERY_SYSTEM_BUSY);
        c.put("x-devicetype", SafeString.substring(generateDeviceIDWithSeparator, generateDeviceIDWithSeparator.indexOf("=") + 1));
        c.put("x-account-brand-id", "0");
        c.put("x-packagename", Environment.getApplicationContext().getPackageName());
        c.put("osVersion", Build.VERSION.RELEASE);
        if (!TextUtils.isEmpty(this.f)) {
            StringBuffer stringBuffer = new StringBuffer(200);
            stringBuffer.append("campid=").append(this.c).append(",operator=").append(this.f).append(",isVIP=1");
            c.put("x-camp-params", stringBuffer.toString());
        }
        return c;
    }

    private String e() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("adType", this.e);
            jSONObject.put("campId", this.c);
            String deviceDigest = JsInterfaceMarketing.getDeviceDigest();
            jSONObject.put("deviceDigest", deviceDigest);
            jSONObject.put("uniqueId", this.f10945a);
            jSONObject.put("adName", this.b);
            jSONObject.put("appId", "200002");
            String f = f();
            jSONObject.put("seq", f);
            String generateUUID = MobileInfoHelper.generateUUID();
            jSONObject.put("auth", a(generateUUID, f, deviceDigest));
            jSONObject.put("requestId", generateUUID);
            HwLog.i("UploadEncourageStatusThread", "buildRequestParams : " + jSONObject.length());
            return jSONObject.toString();
        } catch (Exception e) {
            HwLog.i("UploadEncourageStatusThread", "JSONException : " + HwLog.printException(e));
            return null;
        }
    }

    private String f() {
        return "888888" + dr.a() + "4444";
    }

    private String a(String str, String str2, String str3) {
        StringBuffer stringBuffer = new StringBuffer(200);
        stringBuffer.append("adName").append(this.b).append("adType").append(this.e).append("appId200002campId").append(this.c).append("deviceDigest").append(str3).append("requestId").append(str).append("seq").append(str2).append("uniqueId").append(this.f10945a);
        return "security:" + fa.a(HMACSHA256.hmacEncrypt(CommonUtils.e(stringBuffer.toString()), fa.a(ai.a(DensityUtil.getStringById(CommonUtils.z() ? R$string.authKey : R$string.authKeyBelowAndroidO)))));
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public ae c(String str) {
        ae aeVar = new ae();
        if (TextUtils.isEmpty(str)) {
            HwLog.e("UploadEncourageStatusThread", "dealReceive json is empty");
            return aeVar;
        }
        try {
            aeVar.retCode = new JSONObject(str).optString("retCode");
            return aeVar;
        } catch (Exception e) {
            HwLog.e("UploadEncourageStatusThread", "dealReceive exception: " + HwLog.printException(e));
            return aeVar;
        }
    }
}
