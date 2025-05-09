package defpackage;

import android.view.View;
import com.huawei.health.R;
import com.huawei.skinner.attrentry.SkinAttr;
import com.huawei.skinner.theme.ThemeServiceInterceptor;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class nci {

    /* renamed from: a, reason: collision with root package name */
    private WeakReference<View> f15246a;
    private boolean b;
    private ThemeServiceInterceptor c;
    public boolean e = false;
    private List<SkinAttr> d = new ArrayList();

    public void e(boolean z) {
        View csS_;
        if (ndb.a(this.d) || (csS_ = csS_()) == null) {
            return;
        }
        csS_.setTag(R.id.hw_tag_animate_enable, Boolean.valueOf(this.e));
        for (SkinAttr skinAttr : this.d) {
            if (!skinAttr.isCancled) {
                skinAttr.apply(csS_, z);
            }
        }
    }

    public void a() {
        ThemeServiceInterceptor themeServiceInterceptor;
        View csS_ = csS_();
        if (csS_ == null || !this.b || (themeServiceInterceptor = this.c) == null) {
            return;
        }
        themeServiceInterceptor.process(csS_);
    }

    public View csS_() {
        WeakReference<View> weakReference = this.f15246a;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public void csT_(View view) {
        Object tag = view.getTag(R.id.hw_theme_service_tag);
        if ((tag instanceof String) && "hwThemeServiceEnable".equals((String) tag)) {
            this.b = true;
        }
        this.f15246a = new WeakReference<>(view);
    }

    public List<SkinAttr> d() {
        return this.d;
    }

    public ThemeServiceInterceptor b() {
        return this.c;
    }

    public void a(ThemeServiceInterceptor themeServiceInterceptor) {
        this.c = themeServiceInterceptor;
    }

    public void e() {
        if (ndb.a(this.d)) {
            return;
        }
        Iterator<SkinAttr> it = this.d.iterator();
        while (it.hasNext()) {
            it.next().setCancled(true);
        }
        this.d.clear();
    }

    public String toString() {
        View csS_ = csS_();
        StringBuilder sb = new StringBuilder("SkinnableView [view=");
        sb.append(csS_ != null ? csS_.getClass().getSimpleName() : "Unknow");
        sb.append(", attrs=");
        sb.append(this.d);
        sb.append("]");
        return sb.toString();
    }

    public int hashCode() {
        View csS_ = csS_();
        return csS_ != null ? csS_.hashCode() : super.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof nci) {
            nci nciVar = (nci) obj;
            if (super.equals(obj)) {
                return true;
            }
            View csS_ = csS_();
            View csS_2 = nciVar.csS_();
            if (csS_ != null && csS_.equals(csS_2)) {
                return true;
            }
        }
        return false;
    }
}
