package com.huawei.watchface;

import android.text.TextUtils;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.utils.DensityUtil;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.callback.ILoginCallback;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import com.huawei.wisecloud.drmclient.exception.HwDrmException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class cg {

    /* renamed from: a, reason: collision with root package name */
    private int f10946a;
    private String b;
    private String c;
    private String d;
    private String e;

    public String a() {
        return this.e;
    }

    public String b() {
        return this.c;
    }

    public int c() {
        return this.f10946a;
    }

    public String a(String str) throws JSONException {
        String str2;
        JSONObject jSONObject = new JSONObject(str);
        this.b = jSONObject.optString("resultinfo");
        try {
            this.f10946a = jSONObject.getInt("resultcode");
            str2 = "";
        } catch (JSONException e) {
            str2 = HwLog.printException((Exception) e) + ",mResultInfo=" + this.b;
            this.f10946a = -1;
        }
        HwLog.i("TryOutWatchFaceInfo", "setDownloadInfo resultcode : " + this.f10946a + "  tryoutResultInfo:" + this.b);
        this.c = jSONObject.optString("downUrl");
        this.d = jSONObject.optString("licenseResp");
        this.e = jSONObject.optString(WatchFaceConstant.HASHCODE);
        try {
            g.a(Environment.getApplicationContext(), this.d);
        } catch (HwDrmException e2) {
            HwLog.e("TryOutWatchFaceInfo", "setDownloadInfo -- HwDrmException：" + HwLog.printException((Exception) e2));
        }
        if (this.f10946a == 0 && (TextUtils.isEmpty(this.c) || TextUtils.isEmpty(this.d))) {
            HwLog.w("TryOutWatchFaceInfo", "setDownloadInfo error : resultcode is success but downUrl or mLicenseRes is null or error");
            return "resultcode is success but downUrl or mLicenseRes is null or error";
        }
        if (-2 != this.f10946a) {
            return str2;
        }
        String stringById = DensityUtil.getStringById(R$string.IDS_watchface_pay_failed);
        HwLog.w("TryOutWatchFaceInfo", "setDownloadInfo error auth failed.");
        HwWatchFaceApi.getInstance(Environment.getApplicationContext()).loginByHealthHms(Environment.getApplicationContext(), new ILoginCallback() { // from class: com.huawei.watchface.cg.1
            @Override // com.huawei.watchface.utils.callback.ILoginCallback
            public void onLoginSuccess(Object obj) {
                HwLog.i("TryOutWatchFaceInfo", "setDownloadInfo() onLoginSuccess. ");
            }

            @Override // com.huawei.watchface.utils.callback.ILoginCallback
            public void onLoginFailed(Object obj) {
                HwLog.i("TryOutWatchFaceInfo", "setDownloadInfo() onLoginSuccess. ");
            }
        });
        return stringById;
    }
}
