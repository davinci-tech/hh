package com.huawei.health.featuremarketing.rules.activityinfo;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ParamsFactory;
import com.huawei.operation.utils.AppTypeUtils;
import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.operation.utils.PhoneInfoUtils;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import defpackage.eil;
import defpackage.jdl;
import defpackage.lql;
import defpackage.mtj;
import health.compact.a.CommonUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class ActivityInfoFactory implements ParamsFactory {

    /* renamed from: a, reason: collision with root package name */
    private Context f2353a;

    public ActivityInfoFactory(Context context) {
        this.f2353a = context;
    }

    @Override // com.huawei.networkclient.ParamsFactory
    public Map<String, String> getHeaders() {
        HashMap hashMap = new HashMap(16);
        String accountInfo = LoginInit.getInstance(this.f2353a).getAccountInfo(1011);
        if (accountInfo != null) {
            hashMap.put("x-huid", accountInfo);
        }
        hashMap.put("x-version", CommonUtil.c(this.f2353a));
        hashMap.put("Content-Type", "application/json; charset=utf-8");
        return hashMap;
    }

    @Override // com.huawei.networkclient.ParamsFactory
    public Map<String, Object> getBody(Object obj) {
        Map hashMap = obj == null ? new HashMap() : lql.d(obj);
        hashMap.put("tokenType", String.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
        String accountInfo = LoginInit.getInstance(this.f2353a).getAccountInfo(1008);
        if (!TextUtils.isEmpty(accountInfo)) {
            try {
                hashMap.put("token", URLEncoder.encode(accountInfo, StandardCharsets.UTF_8.name()));
            } catch (UnsupportedEncodingException e) {
                LogUtil.b("ActivityInfoFactory", "token encode Exception ", e.toString());
            }
        }
        hashMap.put("tokenType", String.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
        hashMap.put("siteId", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009));
        hashMap.put("appId", BaseApplication.getAppPackage());
        String deviceId = LoginInit.getInstance(this.f2353a).getDeviceId();
        if (TextUtils.isEmpty(deviceId)) {
            deviceId = "clientnull";
        }
        hashMap.put("deviceId", deviceId);
        hashMap.put("deviceType", PhoneInfoUtils.getDeviceModel());
        hashMap.put("sysVersion", Build.VERSION.RELEASE);
        hashMap.put("appType", String.valueOf(AppTypeUtils.getAppType()));
        hashMap.put("iVersion", "2");
        hashMap.put("language", mtj.e(null));
        hashMap.put("timeZone", jdl.q(System.currentTimeMillis()));
        hashMap.put("countryCode", LoginInit.getInstance(this.f2353a).getAccountInfo(1010));
        hashMap.put("phoneType", PhoneInfoUtils.getPhoneType());
        hashMap.put("ts", String.valueOf(System.currentTimeMillis()));
        hashMap.put(CloudParamKeys.CLIENT_TYPE, eil.a());
        hashMap.put("queryType", "0");
        return hashMap;
    }
}
