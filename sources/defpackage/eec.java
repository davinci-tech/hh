package defpackage;

import android.os.Looper;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.knit.data.KnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class eec extends SectionBean {
    private List<d> h;
    protected List<KnitDataProvider> j;

    public eec(int i, int i2, int i3, KnitDataProvider knitDataProvider, int i4, String str) {
        super(i, i2, i3, knitDataProvider, i4, str);
        this.j = new ArrayList();
        this.h = new ArrayList();
    }

    @Override // com.huawei.health.knit.section.model.SectionBean
    public d a(KnitDataProvider knitDataProvider) {
        if (koq.b(this.h)) {
            return null;
        }
        for (d dVar : this.h) {
            if (dVar.e() == knitDataProvider) {
                return dVar;
            }
        }
        return null;
    }

    @Override // com.huawei.health.knit.section.model.SectionBean
    public void e(final KnitDataProvider knitDataProvider, final Object obj) {
        if (knitDataProvider == null) {
            throw new IllegalArgumentException("dataProvider can not be null");
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            HandlerExecutor.a(new Runnable() { // from class: eec.4
                @Override // java.lang.Runnable
                public void run() {
                    eec.this.b(knitDataProvider, obj);
                }
            });
        } else {
            b(knitDataProvider, obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(KnitDataProvider knitDataProvider, Object obj) {
        synchronized (this) {
            boolean isActive = knitDataProvider.isActive(BaseApplication.getContext());
            List<d> list = this.h;
            if (list == null) {
                return;
            }
            if (list.size() != 0) {
                Iterator<d> it = this.h.iterator();
                while (true) {
                    if (it.hasNext()) {
                        d next = it.next();
                        if (next.e() == knitDataProvider) {
                            c(obj, isActive, next);
                            break;
                        }
                    } else if (obj == null) {
                        this.f2611a = SectionBean.Operation.UNKNOWN;
                    } else {
                        e(knitDataProvider, obj, isActive);
                    }
                }
            } else if (obj == null) {
                this.f2611a = SectionBean.Operation.UNKNOWN;
            } else {
                c(knitDataProvider, obj, isActive);
            }
            if (this.f != null) {
                this.f.b(this);
            }
        }
    }

    private void c(Object obj, boolean z, d dVar) {
        if (this.h.size() != 1) {
            if (obj != null && z) {
                this.f2611a = SectionBean.Operation.CHANGE;
                dVar.d(obj);
                return;
            } else {
                this.f2611a = SectionBean.Operation.CHANGE;
                dVar.d(null);
                return;
            }
        }
        if ((dVar.d() && !z) || (dVar.c() != null && obj == null)) {
            this.f2611a = SectionBean.Operation.REMOVE;
            dVar.d(null);
        } else {
            this.f2611a = SectionBean.Operation.CHANGE;
            dVar.d(obj);
        }
    }

    private void e(KnitDataProvider knitDataProvider, Object obj, boolean z) {
        if (z) {
            this.f2611a = SectionBean.Operation.CHANGE;
            this.h.add(new d(knitDataProvider, obj, true));
            this.j.add(knitDataProvider);
            return;
        }
        this.f2611a = SectionBean.Operation.UNKNOWN;
    }

    private void c(KnitDataProvider knitDataProvider, Object obj, boolean z) {
        if (z) {
            this.f2611a = SectionBean.Operation.INSERT;
            this.h.add(new d(knitDataProvider, obj, true));
            this.j.add(knitDataProvider);
            return;
        }
        this.f2611a = SectionBean.Operation.UNKNOWN;
    }

    @Override // com.huawei.health.knit.section.model.SectionBean
    public void v() {
        super.v();
        List<d> list = this.h;
        if (list != null) {
            list.clear();
        }
        List<KnitDataProvider> list2 = this.j;
        if (list2 != null) {
            Iterator<KnitDataProvider> it = list2.iterator();
            while (it.hasNext()) {
                it.next().onDestroy();
            }
        }
    }

    @Override // com.huawei.health.knit.section.model.SectionBean
    public boolean s() {
        return super.s() && koq.b(this.h);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(KnitDataProvider knitDataProvider) {
        List<d> list;
        if (knitDataProvider == null || (list = this.h) == null) {
            return;
        }
        for (d dVar : list) {
            if (dVar.e() == knitDataProvider) {
                this.h.remove(dVar);
                return;
            }
        }
    }

    public class d {

        /* renamed from: a, reason: collision with root package name */
        private boolean f11979a;
        private KnitDataProvider b;
        private HashMap<String, Object> d = new HashMap<>();
        private Object e;

        public d(KnitDataProvider knitDataProvider, Object obj, boolean z) {
            this.b = knitDataProvider;
            this.e = obj;
            this.f11979a = z;
        }

        public KnitDataProvider e() {
            return this.b;
        }

        public void d(Object obj) {
            if (obj == null) {
                LogUtil.a("SectionGroupBean", "map set to null");
                e(null);
                eec.this.d(this.b);
            }
            this.e = obj;
        }

        public Object c() {
            return this.e;
        }

        public boolean d() {
            return this.f11979a;
        }

        public void e(HashMap<String, Object> hashMap) {
            this.d = hashMap;
        }

        public HashMap<String, Object> a() {
            return this.d;
        }
    }
}
