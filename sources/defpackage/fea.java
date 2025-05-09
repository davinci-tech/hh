package defpackage;

import com.huawei.health.socialshare.model.socialsharebean.ShareDataInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class fea {
    private Map<Integer, List<ShareDataInfo>> d = new HashMap();

    public fea() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        this.d.put(1, arrayList2);
        this.d.put(2, arrayList);
        this.d.put(3, arrayList3);
        this.d.put(4, arrayList4);
    }

    public List<ShareDataInfo> d() {
        return this.d.get(2);
    }

    public void a(List<ShareDataInfo> list) {
        this.d.put(2, list);
    }

    public List<ShareDataInfo> e() {
        return this.d.get(1);
    }

    public void f(List<ShareDataInfo> list) {
        this.d.put(1, list);
    }

    public List<ShareDataInfo> a() {
        return this.d.get(3);
    }

    public void g(List<ShareDataInfo> list) {
        this.d.put(3, list);
    }

    public List<ShareDataInfo> b() {
        return this.d.get(4);
    }

    public void j(List<ShareDataInfo> list) {
        this.d.put(4, list);
    }

    public List<ShareDataInfo> d(int i) {
        return this.d.get(Integer.valueOf(i)) == null ? Collections.emptyList() : this.d.get(Integer.valueOf(i));
    }

    public void a(int i, List<ShareDataInfo> list) {
        this.d.put(Integer.valueOf(i), list);
    }

    public List<ShareDataInfo> c() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(a());
        arrayList.addAll(d());
        arrayList.addAll(b());
        arrayList.addAll(e());
        return arrayList;
    }

    public void c(List<fec> list) {
        if (list != null) {
            d().clear();
            d().addAll(list);
        }
    }

    public void b(List<fdy> list) {
        if (list != null) {
            e().clear();
            e().addAll(list);
        }
    }

    public void e(List<fef> list) {
        if (list != null) {
            a().clear();
            a().addAll(list);
        }
    }

    public void d(List<fee> list) {
        if (list != null) {
            b().clear();
            b().addAll(list);
        }
    }
}
