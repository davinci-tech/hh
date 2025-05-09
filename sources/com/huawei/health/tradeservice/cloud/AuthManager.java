package com.huawei.health.tradeservice.cloud;

import android.text.TextUtils;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.operation.utils.CloudParamKeys;
import defpackage.gla;
import defpackage.moj;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.LinkedHashMap;

/* loaded from: classes4.dex */
public class AuthManager {
    private static final String OP_KEY_RESULT_CODE = "resultCode";
    private static final String TAG = "TradeService_AuthManager";

    private AuthManager() {
    }

    public static void resourceAuthInfo(int i, String str, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a(TAG, "resourceAuthInfo enter");
        if (TextUtils.isEmpty(str)) {
            iBaseResponseCallback.d(-1, null);
            return;
        }
        if (LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
            LogUtil.h(TAG, "resourceAuthInfo isBrowseMode");
            iBaseResponseCallback.d(-2, null);
            return;
        }
        HashMap<String, String> hashMap = new HashMap<>(16);
        hashMap.put(CloudParamKeys.CLIENT_TYPE, String.valueOf(gla.d()));
        hashMap.put("country", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010));
        hashMap.put("resType", String.valueOf(i));
        hashMap.put("resId", str);
        LogUtil.a(TAG, "resourceAuthInfo get params: ", hashMap.toString());
        TradeServiceCloudMgr.getInstance().resourceAuthInfo(new ResourceAuthReq(), hashMap, new IBaseResponseCallback() { // from class: com.huawei.health.tradeservice.cloud.AuthManager.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
                linkedHashMap.put("resultCode", String.valueOf(i2));
                OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_TRADE_RES_80070003.value(), linkedHashMap);
                if (obj instanceof ResourceAuthRsp) {
                    ResourceAuthRsp resourceAuthRsp = (ResourceAuthRsp) obj;
                    ReleaseLogUtil.e(AuthManager.TAG, "resourceAuthInfo: ", moj.e(resourceAuthRsp));
                    IBaseResponseCallback.this.d(i2, Integer.valueOf(resourceAuthRsp.getAuthResult()));
                    return;
                }
                IBaseResponseCallback.this.d(i2, null);
            }
        });
    }

    public static void functionAuthInfo(String str, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a(TAG, "functionAuthInfo enter");
        if (TextUtils.isEmpty(str)) {
            iBaseResponseCallback.d(-1, null);
            return;
        }
        if (LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
            LogUtil.h(TAG, "functionAuthInfo isBrowseMode");
            iBaseResponseCallback.d(-2, null);
            return;
        }
        HashMap<String, String> hashMap = new HashMap<>(16);
        hashMap.put(CloudParamKeys.CLIENT_TYPE, String.valueOf(gla.d()));
        hashMap.put("country", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010));
        LogUtil.a(TAG, "functionAuthInfo get params: ", hashMap.toString());
        TradeServiceCloudMgr.getInstance().functionAuthInfo(new FunctionAuthReq(), str, hashMap, new IBaseResponseCallback() { // from class: com.huawei.health.tradeservice.cloud.AuthManager.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
                linkedHashMap.put("resultCode", String.valueOf(i));
                OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_TRADE_FUNC_80070004.value(), linkedHashMap);
                if (obj instanceof FunctionAuthRsp) {
                    IBaseResponseCallback.this.d(i, ((FunctionAuthRsp) obj).getFunctionAuthResult());
                } else {
                    IBaseResponseCallback.this.d(i, null);
                }
            }
        });
    }
}
