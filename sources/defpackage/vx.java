package defpackage;

import com.huawei.ads.adsrec.l;
import com.huawei.ads.adsrec.o0;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes2.dex */
public class vx extends l {

    class e implements Comparator<vb> {
        @Override // java.util.Comparator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public int compare(vb vbVar, vb vbVar2) {
            return o0.a(vbVar, vbVar2, (vbVar.y().booleanValue() && vbVar2.y().booleanValue()) ? o0.a(vbVar2.o(), vbVar.o()) : o0.a(vbVar, vbVar2));
        }

        e() {
        }
    }

    @Override // com.huawei.ads.adsrec.h0
    public void a() {
        Collections.sort(this.f1678a, new e());
    }

    public vx(List<vb> list) {
        super(list);
    }
}
