package com.huawei.ads.adsrec;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.ads.adsrec.recall.IAdRequestDelegate;
import com.huawei.openplatform.abl.log.HiAdLog;
import defpackage.vg;
import defpackage.vh;
import defpackage.vt;
import defpackage.wc;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class s implements g0 {
    private Context d;

    @Override // com.huawei.ads.adsrec.g0
    public <T> vh a(vt vtVar, T t) {
        HiAdLog.i("CloudRecallTask", "recall");
        if (!(t instanceof IAdRequestDelegate)) {
            HiAdLog.i("CloudRecallTask", "not delegate");
            return null;
        }
        try {
            String requestAd = ((IAdRequestDelegate) t).requestAd();
            if (requestAd == null) {
                return null;
            }
            vh vhVar = new vh(vtVar.a(), new JSONObject(requestAd));
            e(vhVar);
            return vhVar;
        } catch (Throwable th) {
            HiAdLog.w("CloudRecallTask", "delegate recall err: " + th.getClass().getSimpleName());
            return null;
        }
    }

    @Override // com.huawei.ads.adsrec.g0
    public <T> vh a(vt vtVar, vh vhVar) {
        e(vhVar);
        return vhVar;
    }

    private void d(vh vhVar) {
        new e(this.d).c(vhVar.j());
    }

    private void c(vh vhVar) {
        List<vg> j = vhVar.j();
        wc d = wc.d(this.d);
        if (j != null) {
            for (vg vgVar : j) {
                JSONObject c = vgVar.c();
                if (c != null) {
                    String optString = c.optString("configMap");
                    if (!TextUtils.isEmpty(optString)) {
                        try {
                            d.e(vgVar.h(), new JSONObject(optString));
                        } catch (Throwable unused) {
                            HiAdLog.w("CloudRecallTask", "save cfgs json err");
                        }
                    }
                }
            }
        }
    }

    private void e(vh vhVar) {
        c(vhVar);
        b(vhVar);
        d(vhVar);
    }

    private void b(vh vhVar) {
        new e(this.d).a(vhVar.c());
    }

    public s(Context context) {
        this.d = context.getApplicationContext();
    }
}
