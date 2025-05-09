package com.huawei.watchface;

import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.picture.security.account.bean.SignInRequest;
import com.huawei.picture.security.account.bean.SignInResponse;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.mvp.model.helper.GsonHelper;
import com.huawei.watchface.mvp.model.thread.BaseHttpRequest;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.MobileInfoHelper;
import com.huawei.watchface.utils.WatchFaceHttpUtil;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import java.util.LinkedHashMap;

/* loaded from: classes7.dex */
public class bu extends BaseHttpRequest<SignInResponse> {

    /* renamed from: a, reason: collision with root package name */
    private Bundle f10935a;

    public bu(Bundle bundle) {
        this.f10935a = bundle;
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public SignInResponse c(String str) {
        if (TextUtils.isEmpty(str)) {
            HwLog.i("HitopRefreshTokenThread", "dealReceive json is empty");
            return new SignInResponse();
        }
        try {
            return (SignInResponse) GsonHelper.fromJson(str, SignInResponse.class);
        } catch (Exception e) {
            HwLog.i("HitopRefreshTokenThread", "handleJsonData exception: " + HwLog.printException(e));
            return new SignInResponse();
        }
    }

    public String c() {
        return a(WatchFaceHttpUtil.y(), d(), getReqHeaders());
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    public LinkedHashMap<String, String> getReqHeaders() {
        LinkedHashMap<String, String> reqHeaders = super.getReqHeaders();
        reqHeaders.put("versionCode", MobileInfoHelper.getVersion());
        reqHeaders.put("x-hc", HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getCommonCountryCode());
        reqHeaders.put("x-packagename", Environment.b());
        reqHeaders.put("appBrand", String.valueOf(cv.c()));
        reqHeaders.put("x-brandChannel", String.valueOf(cv.e()));
        reqHeaders.put(WatchFaceConstant.CIPHER_VERSION, "1,2");
        reqHeaders.put("personSwitch", String.valueOf(1));
        reqHeaders.put("x-accountBrand", HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getAccountBrand());
        reqHeaders.put("Content-Type", "application/json");
        reqHeaders.remove("authtype");
        reqHeaders.remove("usertoken");
        reqHeaders.remove("deviceid");
        reqHeaders.remove("deviceType");
        reqHeaders.remove("terminalType");
        return reqHeaders;
    }

    private String d() {
        SignInRequest signInRequest = new SignInRequest();
        if (this.f10935a.containsKey("userRefreshToken")) {
            signInRequest.setUserRefreshToken(this.f10935a.getString("userRefreshToken"));
        }
        if (this.f10935a.containsKey("appId")) {
            signInRequest.setAppId(this.f10935a.getString("appId"));
        }
        if (this.f10935a.containsKey("timestamp")) {
            signInRequest.setTimestamp(this.f10935a.getString("timestamp"));
        }
        if (this.f10935a.containsKey(HwPayConstant.KEY_SIGN)) {
            signInRequest.setSign(this.f10935a.getString(HwPayConstant.KEY_SIGN));
        }
        return GsonHelper.toJson(signInRequest);
    }
}
