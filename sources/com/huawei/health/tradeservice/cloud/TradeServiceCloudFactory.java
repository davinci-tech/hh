package com.huawei.health.tradeservice.cloud;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ParamsFactory;
import com.huawei.operation.utils.AppTypeUtils;
import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import health.compact.a.CommonUtil;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class TradeServiceCloudFactory implements ParamsFactory {
    private static final String CONTENT_TYPE = "application/json";
    private static final String TAG = "TradeService_TradeServiceCloudFactory";
    private Context context;

    public TradeServiceCloudFactory(Context context) {
        if (context == null) {
            this.context = BaseApplication.getContext();
        } else {
            this.context = context;
        }
    }

    @Override // com.huawei.networkclient.ParamsFactory
    public Map<String, String> getHeaders() {
        LoginInit loginInit = LoginInit.getInstance(this.context);
        HashMap hashMap = new HashMap(16);
        hashMap.put("Content-type", "application/json");
        String accountInfo = loginInit.getAccountInfo(1011);
        if (!TextUtils.isEmpty(accountInfo)) {
            hashMap.put("x-huid", accountInfo);
            hashMap.put(CloudParamKeys.X_TOKEN_TYPE, String.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
            hashMap.put(CloudParamKeys.X_TOKEN, loginInit.getAccountInfo(1008));
        }
        hashMap.put(CloudParamKeys.X_CLIENT_VERSION, CommonUtil.c(this.context));
        hashMap.put(CloudParamKeys.X_SITE_ID, loginInit.getAccountInfo(1009));
        hashMap.put(CloudParamKeys.X_APP_ID, BaseApplication.getAppPackage());
        String deviceId = loginInit.getDeviceId();
        if (TextUtils.isEmpty(deviceId)) {
            deviceId = "clientnull";
        }
        hashMap.put(CloudParamKeys.X_DEVICE_ID, deviceId);
        hashMap.put(CloudParamKeys.X_DEVICE_TYPE, loginInit.getDeviceType());
        hashMap.put(CloudParamKeys.X_TS, String.valueOf(getUtcTime()));
        hashMap.put(CloudParamKeys.X_APP_TYPE, String.valueOf(AppTypeUtils.getAppType()));
        hashMap.put("x-caller-trace-id", String.valueOf(getUtcTime()) + Math.random());
        return hashMap;
    }

    @Override // com.huawei.networkclient.ParamsFactory
    public Map<String, Object> getBody(Object obj) {
        return new HashMap();
    }

    private static long getUtcTime() {
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillis);
        return calendar.getTimeInMillis();
    }
}
