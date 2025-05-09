package defpackage;

import com.huawei.health.hwhealthtrackalgo.i;
import java.util.LinkedList;

/* loaded from: classes3.dex */
public final class dwy extends i {
    private LinkedList<dwl> c;
    private dwt d;
    private LinkedList<dwl> e;

    @Override // com.huawei.health.hwhealthtrackalgo.a
    public final int a(dwl dwlVar) {
        dwy dwyVar = this;
        if (dwyVar.c.size() < dwyVar.d.i()) {
            return 0;
        }
        LinkedList<dwl> linkedList = dwyVar.c;
        LinkedList<dwl> linkedList2 = dwyVar.e;
        int size = linkedList.size();
        if (size >= 3) {
            if (linkedList2.size() == 0 && 2 < linkedList.size()) {
                dwl dwlVar2 = linkedList.get(0);
                dwl dwlVar3 = linkedList.get(1);
                dwl dwlVar4 = linkedList.get(2);
                double[] dArr = {dwlVar2.g(), dwlVar3.g(), dwlVar4.g()};
                double[] dArr2 = {dwlVar2.j(), dwlVar3.j(), dwlVar4.j()};
                dwl dwlVar5 = new dwl(dwlVar2);
                dwlVar5.b(d(dArr));
                dwlVar5.a(d(dArr2));
                linkedList2.add(dwlVar5);
                dwlVar2.c(true);
            }
            int i = 0;
            while (i < size - 1) {
                dwl dwlVar6 = linkedList.get(i);
                if (!dwlVar6.f()) {
                    if (i == 0) {
                        linkedList2.add(dwlVar6);
                        dwlVar6.c(true);
                    } else {
                        dwl dwlVar7 = linkedList.get(i - 1);
                        dwl dwlVar8 = linkedList.get(i);
                        dwl dwlVar9 = linkedList.get(i + 1);
                        if (dwlVar9.h() - dwlVar8.h() > dwyVar.d.g() || dwlVar8.h() - dwlVar7.h() > dwyVar.d.g()) {
                            dwlVar6.c(true);
                            dwlVar9.c(true);
                            linkedList2.add(dwlVar9);
                        } else {
                            double[] dArr3 = {dwlVar7.g(), dwlVar8.g(), dwlVar9.g()};
                            double[] dArr4 = {dwlVar7.j(), dwlVar8.j(), dwlVar9.j()};
                            dwl dwlVar10 = new dwl(dwlVar6);
                            dwlVar10.b(b(dArr3));
                            dwlVar10.a(b(dArr4));
                            linkedList2.add(dwlVar10);
                            dwlVar6.c(true);
                        }
                        i++;
                        dwyVar = this;
                    }
                }
                i++;
                dwyVar = this;
            }
        }
        return 0;
    }

    private static double b(double[] dArr) {
        return ((dArr[0] + dArr[1]) + dArr[2]) / 3.0d;
    }

    private static double d(double[] dArr) {
        return (((dArr[0] * 5.0d) + (dArr[1] * 2.0d)) + (dArr[2] * (-1.0d))) / 6.0d;
    }

    public dwy(dwq dwqVar) {
        super(dwqVar);
        this.d = this.f2496a.f();
        this.c = this.f2496a.a();
        this.e = this.f2496a.d();
    }
}
