package com.huawei.openalliance.ad;

import android.app.Activity;
import android.content.Context;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import com.huawei.openalliance.ad.utils.m;

/* loaded from: classes5.dex */
public interface g {
    m.a a();

    Object a(Context context, String str);

    void a(Activity activity);

    void a(Context context, String str, RemoteCallResultCallback<String> remoteCallResultCallback);

    void a(String str);

    void b(String str);
}
