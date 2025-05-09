package com.huawei.hianalytics.kit;

import android.content.Context;
import com.huawei.hianalytics.r0;
import com.huawei.hianalytics.s0;
import com.huawei.hianalytics.u0;
import com.huawei.hianalytics.v0;
import com.huawei.hianalytics.w0;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.api.Api;
import com.huawei.hms.common.HuaweiApi;

/* loaded from: classes4.dex */
public class HiAnalyticsClientImpl extends HuaweiApi<v0> implements HiAnalyticsClient {
    public static final Api<v0> HI_ANALYTICS_OPTIONS_API = new Api<>("HuaweiHASDK.API");
    public static final r0 CLIENT_BUILDER = new r0();

    @Override // com.huawei.hianalytics.kit.HiAnalyticsClient
    public Task<w0> getAesKey(u0 u0Var) {
        u0Var.getClass();
        return doWrite(new HiAnalyticsTaskApiCall("qoes.getKey", "{\"type\" : \"" + u0Var.f3901a + "\", \"source\" : \"" + u0Var.b + "\", \"pub_key\" : \"" + u0Var.c + "\", \"version\" : \"" + u0Var.d + "\"}"));
    }

    @Override // com.huawei.hianalytics.kit.HiAnalyticsClient
    public Task<w0> getHaConfig(s0 s0Var) {
        s0Var.getClass();
        return doWrite(new HiAnalyticsTaskApiCall("qoes.getHaConfig", "{\"type\" : \"" + s0Var.f3898a + "\", \"source\" : \"" + s0Var.b + "\"}"));
    }

    public HiAnalyticsClientImpl(Context context, v0 v0Var) {
        super(context, HI_ANALYTICS_OPTIONS_API, v0Var, CLIENT_BUILDER);
    }
}
