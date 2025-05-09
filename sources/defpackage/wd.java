package defpackage;

import android.content.Context;
import com.huawei.ads.adsrec.e;
import com.huawei.ads.adsrec.u0;
import com.huawei.openplatform.abl.log.HiAdLog;

/* loaded from: classes2.dex */
public final class wd {
    public static vg c(Context context, String str, String str2, int i) {
        HiAdLog.i("SingleSlotLR", "recall %s slot: %s adType: %d", str, str2, Integer.valueOf(i));
        e eVar = new e(context);
        vg a2 = eVar.a(str, str2);
        wk b = new u0(context).b(str, str2);
        if (b != null) {
            a2.b(eVar.a(b));
        }
        a2.a(a2.g() ? 204 : 200);
        return a2;
    }
}
