package com.huawei.openalliance.ad;

import android.util.Log;

/* loaded from: classes5.dex */
public final class hq extends hm {
    @Override // com.huawei.openalliance.ad.ht
    public void a(hv hvVar, int i, String str) {
        if (hvVar == null) {
            return;
        }
        a(hvVar.b(), i, str);
        if (this.f6914a != null) {
            this.f6914a.a(hvVar, i, str);
        }
    }

    @Override // com.huawei.openalliance.ad.ht
    public ht a(String str, String str2) {
        if (this.f6914a != null) {
            this.f6914a.a(str, str2);
        }
        return this;
    }

    private void a(String str, int i, String str2) {
        if (str == null) {
            return;
        }
        if (i == 3) {
            Log.d(str2, str);
            return;
        }
        if (i != 4) {
            if (i == 5) {
                Log.w(str2, str);
                return;
            } else if (i == 6) {
                Log.e(str2, str);
                return;
            }
        }
        Log.i(str2, str);
    }

    public static ht a() {
        return new hq();
    }

    private hq() {
    }
}
