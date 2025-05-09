package defpackage;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import androidx.core.view.LayoutInflaterCompat;
import com.huawei.skinner.attrentry.SkinAttr;
import com.huawei.skinner.internal.IDynamicNewView;
import com.huawei.skinner.internal.ISkinUpdate2;
import com.huawei.skinner.internal.ISkinnableViewManager2;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes6.dex */
public class ncq implements ISkinUpdate2, IDynamicNewView, ISkinnableViewManager2 {
    private static final String d = "SkinnableProcessor";

    /* renamed from: a, reason: collision with root package name */
    private final Activity f15254a;
    private ncu e;
    private ConcurrentHashMap<nci, nci> h = new ConcurrentHashMap<>();
    private boolean b = true;
    private boolean c = false;

    public ncq(Activity activity) {
        this.f15254a = activity;
    }

    public void b() {
        if (this.b) {
            this.e = new ncu(this);
            LayoutInflater layoutInflater = this.f15254a.getLayoutInflater();
            if (layoutInflater.getFactory() == null) {
                LayoutInflaterCompat.setFactory2(layoutInflater, this.e);
            } else {
                if (layoutInflater.getFactory2() instanceof ncu) {
                    return;
                }
                ncs.d.info(d, "init() The Activity's LayoutInflater already has a Factory installed so we can not install Skinner's");
            }
        }
    }

    public void d() {
        ncs.c(this.f15254a).attach(this);
    }

    public void e() {
        ncs.c(this.f15254a).detach(this);
        clean();
    }

    @Override // com.huawei.skinner.internal.ISkinUpdate
    public void onThemeServiceUpdate() {
        if (this.h.size() > 0) {
            for (nci nciVar : this.h.keySet()) {
                if (nciVar != null && nciVar.csS_() != null) {
                    nciVar.a();
                }
            }
        }
    }

    @Override // com.huawei.skinner.internal.ISkinUpdate
    public void onThemeUpdate() {
        onThemeUpdate(false);
    }

    @Override // com.huawei.skinner.internal.ISkinUpdate2
    public void onThemeUpdate(boolean z) {
        applySkin(z);
    }

    @Override // com.huawei.skinner.internal.IDynamicNewView
    public void dynamicAddSkinableView(View view, String str, int i) {
        if (h()) {
            this.e.cta_(this.f15254a, view, str, i);
        }
    }

    private boolean h() {
        return this.b && this.e != null;
    }

    @Override // com.huawei.skinner.internal.IDynamicNewView
    public void dynamicAddSkinableView(View view, List<ncg> list) {
        if (h()) {
            this.e.ctb_(this.f15254a, view, list);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.huawei.skinner.internal.ISkinnableViewManager
    public nci addSkinnableView(nci nciVar) {
        if (this.h.size() > 5000) {
            Iterator<Map.Entry<nci, nci>> it = this.h.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<nci, nci> next = it.next();
                if (next == null || next.getValue() == null || next.getValue().csS_() == null) {
                    it.remove();
                }
            }
        }
        nci nciVar2 = this.h.get(nciVar);
        if (nciVar2 != null) {
            List<SkinAttr> d2 = nciVar2.d();
            for (SkinAttr skinAttr : nciVar.d()) {
                int indexOf = d2.indexOf(skinAttr);
                if (indexOf >= 0) {
                    d2.set(indexOf, skinAttr);
                } else {
                    d2.add(skinAttr);
                }
            }
            if (nciVar.b() == null) {
                nciVar.a(ncv.c().c(nciVar.csS_().getClass()));
            }
            return nciVar2;
        }
        try {
            this.h.put(nciVar, nciVar);
        } catch (Exception e) {
            ncs.d.error(d, "addSkinnableView()", e);
        }
        nciVar.a(ncv.c().c(nciVar.csS_().getClass()));
        return nciVar;
    }

    @Override // com.huawei.skinner.internal.ISkinnableViewManager
    public nci removeSkinnableView(nci nciVar) {
        nci nciVar2 = this.h.get(nciVar);
        if (nciVar2 == null) {
            return null;
        }
        Iterator<SkinAttr> it = nciVar2.d().iterator();
        while (it.hasNext()) {
            it.next().setCancled(true);
        }
        try {
            this.h.remove(nciVar2);
        } catch (Exception e) {
            ncs.d.error(d, "removeSkinnableView()", e);
        }
        return nciVar2;
    }

    @Override // com.huawei.skinner.internal.ISkinnableViewManager
    public void applySkin() {
        applySkin(false);
    }

    @Override // com.huawei.skinner.internal.ISkinnableViewManager2
    public void applySkin(boolean z) {
        if (ndb.a(this.h)) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (nci nciVar : this.h.keySet()) {
            if (nciVar == null || nciVar.csS_() == null) {
                arrayList.add(nciVar);
            } else {
                nciVar.e = nciVar.csS_().isShown() && this.c;
                nciVar.e(false);
            }
        }
        if (ndb.a(arrayList)) {
            return;
        }
        try {
            this.h.keySet().removeAll(arrayList);
        } catch (Exception e) {
            ncs.d.error(d, "applySkin(isAnimateEnable)", e);
        }
    }

    @Override // com.huawei.skinner.internal.ISkinnableViewManager
    public void clean() {
        if (ndb.a(this.h)) {
            return;
        }
        for (nci nciVar : this.h.keySet()) {
            if (nciVar == null) {
                ncs.d.error(d, "clean() si is null");
            } else {
                nciVar.e();
            }
        }
    }

    public void c(boolean z) {
        this.b = z;
    }

    public boolean c() {
        return this.b;
    }

    public void b(boolean z) {
        this.c = z;
    }

    public boolean a() {
        return this.c;
    }
}
