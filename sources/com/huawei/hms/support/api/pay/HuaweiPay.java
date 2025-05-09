package com.huawei.hms.support.api.pay;

import com.huawei.hms.api.Api;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.support.api.entity.auth.Scope;

/* loaded from: classes4.dex */
public class HuaweiPay {
    public static final Scope SCOPE_IAP_QUERY_WALLETINFO = new Scope("https://www.huawei.com/auth/pay/walletinfo");
    public static final Api<Api.ApiOptions.NoOptions> PAY_API = new Api<>(HuaweiApiAvailability.HMS_API_NAME_PAY);
    public static final HuaweiPayApi HuaweiPayApi = new HuaweiPayApiImpl();
}
