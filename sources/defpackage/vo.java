package defpackage;

import com.huawei.ads.adsrec.IDsRelationCallback;
import com.huawei.ads.adsrec.l;
import com.huawei.ads.adsrec.o0;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes2.dex */
public class vo extends l {

    class d implements Comparator<vb> {
        final /* synthetic */ float c;
        final /* synthetic */ float e;

        @Override // java.util.Comparator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public int compare(vb vbVar, vb vbVar2) {
            int a2;
            if (vbVar.y().booleanValue() && vbVar2.y().booleanValue()) {
                a2 = o0.a((vbVar2.o() * this.e) + (vbVar2.h() * this.c), (vbVar.o() * this.e) + (vbVar.h() * this.c));
            } else {
                a2 = o0.a(vbVar, vbVar2);
            }
            return o0.a(vbVar, vbVar2, a2);
        }

        d(float f, float f2) {
            this.e = f;
            this.c = f2;
        }
    }

    @Override // com.huawei.ads.adsrec.h0
    public void a() {
        IDsRelationCallback a2 = uw.a();
        if (a2 == null) {
            return;
        }
        float relationCoL = a2.getRelationCoL(Constants.RELATION_COL_A_KEY);
        float relationCoL2 = a2.getRelationCoL(Constants.RELATION_COL_B_KEY);
        if (Math.abs(relationCoL) + Math.abs(relationCoL2) < 1.0E-6f) {
            return;
        }
        Collections.sort(this.f1678a, new d(relationCoL, relationCoL2));
    }

    public vo(List<vb> list) {
        super(list);
    }
}
