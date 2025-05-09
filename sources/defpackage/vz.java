package defpackage;

import com.huawei.ads.adsrec.l;
import java.util.List;

/* loaded from: classes2.dex */
public class vz extends l {
    private List<vb> d;
    private List<vb> e;

    @Override // com.huawei.ads.adsrec.h0
    public void a() {
        int size = this.d.size();
        int size2 = this.e.size();
        this.f1678a.clear();
        int i = 0;
        int i2 = 0;
        while (i < size && i2 < size2) {
            vb vbVar = this.d.get(i);
            vb vbVar2 = this.e.get(i2);
            if (vbVar.y().booleanValue() && (!vbVar2.y().booleanValue() || vbVar.o() < vbVar2.o())) {
                this.f1678a.add(vbVar2);
                i2++;
            } else {
                this.f1678a.add(vbVar);
                i++;
            }
        }
        if (i < size) {
            this.f1678a.addAll(this.d.subList(i, size));
        } else {
            this.f1678a.addAll(this.e.subList(i2, size2));
        }
    }

    public vz(List<vb> list, List<vb> list2, List<vb> list3) {
        super(list);
        this.d = list2;
        this.e = list3;
    }
}
