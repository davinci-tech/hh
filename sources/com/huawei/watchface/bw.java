package com.huawei.watchface;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.api.HwWatchFaceBtManager;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.mvp.model.datatype.WatchFaceDownloadQueryResp;
import com.huawei.watchface.mvp.model.helper.GsonHelper;
import com.huawei.watchface.mvp.model.thread.BaseHttpRequest;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.WatchFaceHttpUtil;
import com.huawei.watchface.utils.callback.ILoginCallback;
import com.huawei.watchface.utils.callback.OperateWatchFaceCallback;
import java.util.LinkedHashMap;

@el(a = {@ek(a = "resultinfo"), @ek(a = "resultcode"), @ek(a = "isOrdered"), @ek(a = "memberStatus")})
/* loaded from: classes7.dex */
public class bw extends BaseHttpRequest<WatchFaceDownloadQueryResp> {

    /* renamed from: a, reason: collision with root package name */
    private String f10937a;
    private String b;
    private int c;

    public bw(String str, String str2, int i) {
        this.f10937a = str;
        this.b = str2;
        this.c = i;
    }

    public String c() {
        return a(WatchFaceHttpUtil.q(), d(), getReqHeaders());
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    public String a() {
        return CommonUtils.getVersionCode() + "";
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    public LinkedHashMap<String, String> getReqHeaders() {
        return super.getReqHeaders();
    }

    private String d() {
        HwLog.i("HitopReqDownloadQueryThread", "buildRequestParams enter");
        try {
            String str = this.b + this.c;
            LinkedHashMap<String, ?> linkedHashMap = new LinkedHashMap<>();
            linkedHashMap.put("productId", this.f10937a);
            linkedHashMap.put("licenseReq", d(str));
            linkedHashMap.put(JsbMapKeyNames.H5_USER_ID, WatchFaceHttpUtil.a().getUserId());
            linkedHashMap.put("hitopid", this.b);
            linkedHashMap.put("type", Integer.valueOf(this.c));
            linkedHashMap.put("isSupportedFreeDiscount", "1");
            linkedHashMap.put("isVipVersion", Integer.valueOf(CommonUtils.A()));
            linkedHashMap.put(FaqConstants.FAQ_EMUIVERSION, HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).getWatchFaceMaxVersion(false, false));
            return a(linkedHashMap);
        } catch (Exception e) {
            HwLog.i("HitopReqDownloadQueryThread", "Exception : " + HwLog.printException(e));
            return "";
        }
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public WatchFaceDownloadQueryResp c(String str) {
        if (str == null || TextUtils.isEmpty(str)) {
            HwLog.i("HitopReqDownloadQueryThread", "dealReceive json is empty");
            return null;
        }
        try {
            return (WatchFaceDownloadQueryResp) GsonHelper.fromJson(str, WatchFaceDownloadQueryResp.class);
        } catch (Exception e) {
            HwLog.i("HitopReqDownloadQueryThread", "handleJsonData exception: " + HwLog.printException(e));
            return null;
        }
    }

    public void a(int i, OperateWatchFaceCallback operateWatchFaceCallback) {
        if (i != 11002 || operateWatchFaceCallback == null) {
            return;
        }
        HwWatchFaceApi.getInstance(Environment.getApplicationContext()).loginByHealthHms(Environment.getApplicationContext(), new ILoginCallback() { // from class: com.huawei.watchface.bw.1
            @Override // com.huawei.watchface.utils.callback.ILoginCallback
            public void onLoginSuccess(Object obj) {
                HwLog.i("HitopReqDownloadQueryThread", "dealWrongResultCode() onLoginSuccess. ");
            }

            @Override // com.huawei.watchface.utils.callback.ILoginCallback
            public void onLoginFailed(Object obj) {
                HwLog.i("HitopReqDownloadQueryThread", "dealWrongResultCode() onLoginSuccess. ");
            }
        });
        Context customWebViewContext = operateWatchFaceCallback.getCustomWebViewContext();
        if (!(customWebViewContext instanceof Activity)) {
            HwLog.w("HitopReqDownloadQueryThread", "customWebViewContext not suitable");
        } else {
            ((Activity) customWebViewContext).runOnUiThread(new Runnable() { // from class: com.huawei.watchface.bw$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    bw.e();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void e() {
        ds.a(R$string.IDS_watchface_pay_failed);
    }
}
