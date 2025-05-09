package com.huawei.wallet.proxy;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;

/* loaded from: classes9.dex */
public interface InitWalletApi {
    Object getContentProvider(Context context, String str, Uri uri);

    IBinder getServiceBinder(Context context, String str, Intent intent);

    void initBaseConfig(Context context);

    void initWearWallet(Context context);

    boolean isInitBaseCommonContext();

    default void receiverData(String str, String str2) {
    }

    void reverseWalletStartDeepLink(String str);

    void serviceOnCreate(Context context, String str);

    void serviceOnDestroy(Context context, String str);

    void walletAccountRemove(Context context, Intent intent);
}
