package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;

/* loaded from: classes5.dex */
public abstract class i extends j {
    protected abstract void b(Context context, String str, RemoteCallResultCallback<String> remoteCallResultCallback);

    public String c(String str) {
        return str;
    }

    @Override // com.huawei.openalliance.ad.j, com.huawei.openalliance.ad.g
    public void a(final Context context, final String str, final RemoteCallResultCallback<String> remoteCallResultCallback) {
        if (ho.a()) {
            ho.a("CmdBaseAdRequest", "paramContent: %s", str);
        }
        com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.i.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    i.this.b(context, str, remoteCallResultCallback);
                } catch (Throwable th) {
                    ho.a(5, "CmdBaseAdRequest", "executeInNetworkThread exception", th);
                    j.a(remoteCallResultCallback, i.this.f7108a, -1, th.getClass().getSimpleName() + ":" + th.getMessage(), true);
                }
            }
        });
    }

    public i(String str) {
        super(str);
    }
}
