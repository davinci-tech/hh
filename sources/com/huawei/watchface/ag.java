package com.huawei.watchface;

import android.app.Activity;
import android.content.Context;
import com.huawei.hms.api.HuaweiApiClient;
import com.huawei.hms.support.hwid.HuaweiIdAuthAPIManager;
import com.huawei.hms.support.hwid.request.HuaweiIdAuthParams;
import com.huawei.hms.support.hwid.request.HuaweiIdAuthParamsHelper;
import com.huawei.watchface.utils.HwLog;
import java.util.concurrent.ExecutorService;

/* loaded from: classes7.dex */
public class ag {

    /* renamed from: a, reason: collision with root package name */
    private HuaweiApiClient f10890a;
    private ExecutorService b;
    private Activity c;

    public ag() {
    }

    public ag(Activity activity) {
        this.c = activity;
    }

    public void a(Context context, HuaweiApiClient.ConnectionCallbacks connectionCallbacks, HuaweiApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        HuaweiIdAuthParams createParams = new HuaweiIdAuthParamsHelper(HuaweiIdAuthParams.DEFAULT_AUTH_REQUEST_PARAM).createParams();
        if (context == null) {
            HwLog.i("Login_AccessTokenManager", "hmsConnect context == null");
            return;
        }
        HuaweiApiClient build = new HuaweiApiClient.Builder(context.getApplicationContext()).addApi(HuaweiIdAuthAPIManager.HUAWEI_OAUTH_API, createParams).addScope(HuaweiIdAuthAPIManager.HUAWEIID_BASE_SCOPE).addConnectionCallbacks(connectionCallbacks).addOnConnectionFailedListener(onConnectionFailedListener).build();
        this.f10890a = build;
        build.connect(this.c);
    }

    public void a() {
        ExecutorService executorService = this.b;
        if (executorService != null) {
            executorService.shutdown();
        }
        HuaweiApiClient huaweiApiClient = this.f10890a;
        if (huaweiApiClient != null) {
            huaweiApiClient.disconnect();
            this.f10890a = null;
        }
        this.c = null;
    }
}
