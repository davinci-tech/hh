package com.huawei.ads.adsrec;

import android.content.Context;
import com.huawei.ads.fund.util.ListUtil;
import com.huawei.openplatform.abl.log.HiAdLog;
import defpackage.vg;
import defpackage.vh;
import defpackage.vt;
import defpackage.wd;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class k0 implements g0 {
    private Context b;

    @Override // com.huawei.ads.adsrec.g0
    public <T> vh a(vt vtVar, vh vhVar) {
        return null;
    }

    @Override // com.huawei.ads.adsrec.g0
    public <T> vh a(vt vtVar, T t) {
        HiAdLog.i("LocalRecallTask", "recall");
        if (vtVar == null) {
            HiAdLog.i("LocalRecallTask", "adRecallParam is null");
            return null;
        }
        vh vhVar = new vh(vtVar.a(), new e(this.b).b(vtVar.a()));
        vhVar.a(vtVar.c());
        List<String> d = vtVar.d();
        if (ListUtil.isEmpty(d)) {
            HiAdLog.i("LocalRecallTask", "empty slotIds");
        } else {
            ArrayList arrayList = new ArrayList(d.size());
            Iterator<String> it = d.iterator();
            while (it.hasNext()) {
                vg c = wd.c(this.b, vtVar.a(), it.next(), vtVar.h());
                if (c != null) {
                    c.h();
                    arrayList.add(c);
                }
            }
            vhVar.a(arrayList);
            vhVar.b(200);
        }
        return vhVar;
    }

    public k0(Context context) {
        this.b = context.getApplicationContext();
    }
}
