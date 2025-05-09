package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.beans.inner.CountryCodeBean;

/* loaded from: classes5.dex */
public class cf extends cc {
    private static co c;
    private static final byte[] d = new byte[0];
    private CountryCodeBean e;

    @Override // com.huawei.openalliance.ad.cc, com.huawei.openalliance.ad.co
    public boolean l() {
        return false;
    }

    @Override // com.huawei.openalliance.ad.cc, com.huawei.openalliance.ad.co
    public boolean d() {
        return a();
    }

    @Override // com.huawei.openalliance.ad.cc, com.huawei.openalliance.ad.co
    public boolean a() {
        return "CN".equalsIgnoreCase(this.e.a());
    }

    private static co c(Context context) {
        co coVar;
        synchronized (d) {
            if (c == null) {
                c = new cf(context);
            }
            coVar = c;
        }
        return coVar;
    }

    public static co b(Context context) {
        return c(context);
    }

    protected cf(Context context) {
        super(context);
        this.e = new CountryCodeBean(context);
    }
}
