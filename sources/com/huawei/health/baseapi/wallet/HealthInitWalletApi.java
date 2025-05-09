package com.huawei.health.baseapi.wallet;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

/* loaded from: classes.dex */
public interface HealthInitWalletApi {
    IBinder getServiceBinder(Context context, String str, Intent intent);

    void initBaseConfig(Context context);

    void initHealthPayAdapter(Context context);

    void pushWearWalletMessage(Context context, String str);

    void serviceOnCreate(Context context, String str);

    void serviceOnDestroy(Context context, String str);

    void walletAccountRemove(Context context, Intent intent);
}
