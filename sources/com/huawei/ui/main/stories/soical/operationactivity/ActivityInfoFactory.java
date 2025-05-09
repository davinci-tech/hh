package com.huawei.ui.main.stories.soical.operationactivity;

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
import defpackage.jec;
import defpackage.lql;
import defpackage.mtj;
import health.compact.a.CommonUtil;
import health.compact.a.HiDateUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class ActivityInfoFactory implements ParamsFactory {
    private static final String API_SUPPORT_VERSION = "2";
    private static final String PARAM_ACTIVITY_ID = "activityId";
    private static final String PARAM_ACTIVITY_ID_LIST = "activityList";
    private static final String QUERY_TYPE = "queryType";
    private static final String TAG = "ActivityInfoFactory";
    private Context mContext;

    public ActivityInfoFactory(Context context) {
        this.mContext = context;
    }

    @Override // com.huawei.networkclient.ParamsFactory
    public Map<String, String> getHeaders() {
        LogUtil.h(TAG, "getHeaders");
        HashMap hashMap = new HashMap(16);
        String accountInfo = LoginInit.getInstance(this.mContext).getAccountInfo(1011);
        if (accountInfo != null) {
            hashMap.put("x-huid", accountInfo);
        }
        hashMap.put("x-version", CommonUtil.c(this.mContext));
        hashMap.put("Content-Type", "application/json; charset=utf-8");
        return hashMap;
    }

    @Override // com.huawei.networkclient.ParamsFactory
    public Map<String, Object> getBody(Object obj) {
        LogUtil.h(TAG, "getBody");
        Map hashMap = obj == null ? new HashMap() : lql.d(obj);
        hashMap.put("tokenType", String.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
        hashMap.put("token", LoginInit.getInstance(this.mContext).getAccountInfo(1008));
        hashMap.put("tokenType", String.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
        hashMap.put("siteId", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009));
        hashMap.put("appId", BaseApplication.getAppPackage());
        String deviceId = LoginInit.getInstance(this.mContext).getDeviceId();
        if (TextUtils.isEmpty(deviceId)) {
            deviceId = "clientnull";
        }
        hashMap.put("deviceId", deviceId);
        hashMap.put("deviceType", PhoneInfoUtils.getDeviceModel());
        hashMap.put("sysVersion", Build.VERSION.RELEASE);
        hashMap.put("appType", String.valueOf(AppTypeUtils.getAppType()));
        hashMap.put("iVersion", "2");
        hashMap.put("language", mtj.e(null));
        hashMap.put("timeZone", HiDateUtil.d((String) null));
        hashMap.put("countryCode", LoginInit.getInstance(this.mContext).getAccountInfo(1010));
        hashMap.put("phoneType", PhoneInfoUtils.getPhoneType());
        hashMap.put("ts", String.valueOf(jec.h()));
        hashMap.put("manufacturer", Build.MANUFACTURER);
        hashMap.put(CloudParamKeys.CLIENT_TYPE, eil.a());
        return hashMap;
    }
}
