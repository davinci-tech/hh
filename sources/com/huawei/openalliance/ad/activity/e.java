package com.huawei.openalliance.ad.activity;

import android.os.Bundle;
import android.view.ViewGroup;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.dd;

/* loaded from: classes5.dex */
public abstract class e extends f {
    protected ViewGroup p;

    protected abstract void a();

    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onCreate(Bundle bundle) {
        StringBuilder sb;
        super.onCreate(bundle);
        try {
            a();
            b();
        } catch (Exception e) {
            e = e;
            sb = new StringBuilder("error occurs,");
            sb.append(e.getClass().getSimpleName());
            ho.c("PPSBaseActivity", sb.toString());
            ho.a(5, e);
        } catch (Throwable th) {
            e = th;
            sb = new StringBuilder("error occurs,");
            sb.append(e.getClass().getSimpleName());
            ho.c("PPSBaseActivity", sb.toString());
            ho.a(5, e);
        }
    }

    private void b() {
        dd.a(this.p, this);
    }
}
