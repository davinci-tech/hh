package defpackage;

import android.content.Context;
import com.huawei.ads.adsrec.e0;
import com.huawei.ads.adsrec.k;
import com.huawei.ads.adsrec.s;
import com.huawei.openplatform.abl.log.HiAdLog;

/* loaded from: classes2.dex */
public class vu extends k {
    @Override // com.huawei.ads.adsrec.r0
    public <T> vh a(vt vtVar, T t) {
        HiAdLog.i("CROS", "recall");
        return new s(this.f1677a).a(vtVar, (vt) t);
    }

    @Override // com.huawei.ads.adsrec.r0
    public <T> vh a(vt vtVar, vh vhVar) {
        HiAdLog.i("CROS", "recall via api");
        return new s(this.f1677a).a(vtVar, vhVar);
    }

    public vu(Context context, e0 e0Var) {
        super(context, e0Var);
    }
}
