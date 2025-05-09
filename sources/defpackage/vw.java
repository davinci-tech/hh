package defpackage;

import android.util.Pair;
import com.huawei.ads.adsrec.f0;
import com.huawei.ads.fund.util.ListUtil;
import com.huawei.openplatform.abl.log.HiAdLog;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class vw implements f0 {

    /* renamed from: a, reason: collision with root package name */
    private String f17720a;
    private List<vb> b;
    private vt d;

    protected Pair<List<vb>, List<vb>> dm_(List<vb> list) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (vb vbVar : list) {
            if (vbVar != null) {
                vbVar.f();
                vbVar.n();
                if ("1".equals(vbVar.n())) {
                    arrayList.add(vbVar);
                } else {
                    arrayList2.add(vbVar);
                }
            }
        }
        arrayList.size();
        arrayList2.size();
        return new Pair<>(arrayList, arrayList2);
    }

    @Override // com.huawei.ads.adsrec.f0
    public void a(int i) {
        if (HiAdLog.isDebugEnable() && !ListUtil.isEmpty(this.b)) {
            this.b.size();
        }
        if (i == 1) {
            a();
            return;
        }
        if (i == 2) {
            c(this.b);
            return;
        }
        if (i != 3) {
            if (i == 5) {
                e(this.b);
                return;
            } else if (i != 6) {
                e();
                return;
            }
        }
        d(this.b);
    }

    private void e(List<vb> list) {
        vc.c("strategySortation", list, this.f17720a, this.d).a();
    }

    private void c(List<vb> list) {
        vc.c("modelSortation", list, this.d).a();
    }

    private void e() {
        Pair<List<vb>, List<vb>> dm_ = dm_(this.b);
        e((List) dm_.first);
        d((List) dm_.second);
        vc.c("mixEcpmSortation", this.b, dm_.first, dm_.second).a();
    }

    private void d(List<vb> list) {
        vc.c("ecpmSortation", list, this.f17720a, this.d).a();
    }

    private void a() {
        Pair<List<vb>, List<vb>> dm_ = dm_(this.b);
        c((List) dm_.first);
        d((List) dm_.second);
        vc.c("mixEcpmSortation", this.b, dm_.first, dm_.second).a();
    }

    public vw(String str, List<vb> list, vt vtVar) {
        this.f17720a = str;
        this.b = list;
        this.d = vtVar;
    }
}
