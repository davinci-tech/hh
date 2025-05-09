package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.hms.ads.common.inter.LoaderSpHandlerInter;

/* loaded from: classes5.dex */
public class fa implements LoaderSpHandlerInter {

    /* renamed from: a, reason: collision with root package name */
    private static fa f6859a;
    private static gc b;
    private static final byte[] c = new byte[0];

    @Override // com.huawei.hms.ads.common.inter.LoaderSpHandlerInter
    public void setKitloaderLastCheckTime(long j) {
        b.p(j);
    }

    @Override // com.huawei.hms.ads.common.inter.LoaderSpHandlerInter
    public boolean getLoaderEngineUpdate(String str) {
        return b.A(str);
    }

    @Override // com.huawei.hms.ads.common.inter.LoaderSpHandlerInter
    public int getLoaderEngineInterval(String str) {
        return b.C(str);
    }

    @Override // com.huawei.hms.ads.common.inter.LoaderSpHandlerInter
    public int getLoaderEngin2KitUpdate(String str) {
        return b.B(str);
    }

    @Override // com.huawei.hms.ads.common.inter.LoaderSpHandlerInter
    public long getKitloaderLastCheckTime() {
        return b.cm();
    }

    private static fa b(Context context) {
        fa faVar;
        synchronized (c) {
            if (f6859a == null) {
                f6859a = new fa(context);
            }
            faVar = f6859a;
        }
        return faVar;
    }

    public static fa a(Context context) {
        return b(context);
    }

    private fa(Context context) {
        b = fh.b(context);
    }
}
