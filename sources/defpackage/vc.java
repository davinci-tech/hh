package defpackage;

import com.huawei.ads.adsrec.h0;
import java.util.List;

/* loaded from: classes2.dex */
public class vc {
    public static h0 c(String str, Object... objArr) {
        List list = (List) objArr[0];
        return "strategySortation".equals(str) ? new vd(list) : "modelSortation".equals(str) ? new vr(list, (vt) objArr[1]) : "ecpmSortation".equals(str) ? new vx(list) : "mixEcpmSortation".equals(str) ? new vz(list, (List) objArr[1], (List) objArr[2]) : new vo(list);
    }
}
