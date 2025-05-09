package com.huawei.hms.support.sms;

import android.app.Activity;
import android.content.Context;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.api.Api;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.common.internal.AbstractClientBuilder;
import com.huawei.hms.hwid.ah;
import com.huawei.hms.hwid.aj;

/* loaded from: classes9.dex */
public class ReadSmsManager {

    /* renamed from: a, reason: collision with root package name */
    private static final Api<Api.ApiOptions.NoOptions> f6034a = new Api<>(HuaweiApiAvailability.HMS_API_NAME_ID);
    private static final ah b = new ah();

    public static Task<Void> start(Activity activity) {
        return new aj(activity, f6034a, (Api.ApiOptions.NoOptions) null, (AbstractClientBuilder) b).a();
    }

    public static Task<Void> start(Context context) {
        return new aj(context, f6034a, (Api.ApiOptions.NoOptions) null, b).a();
    }

    public static Task<Void> startConsent(Activity activity, String str) {
        return new aj(activity, f6034a, (Api.ApiOptions.NoOptions) null, (AbstractClientBuilder) b).a(str);
    }
}
