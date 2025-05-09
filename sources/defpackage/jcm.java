package defpackage;

import com.huawei.hwcommonmodel.accessibility.AbstractTouchNode;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class jcm {

    /* renamed from: a, reason: collision with root package name */
    private final String f13742a = "ChartTouchProvider";
    private List<AbstractTouchNode> d = new ArrayList();
    private Class<? extends AbstractTouchNode> e;

    public jcm(Class<? extends AbstractTouchNode> cls) {
        this.e = cls;
    }

    public int c() {
        return this.d.size();
    }

    public int b(int i, int i2) {
        for (int i3 = 0; i3 < this.d.size(); i3++) {
            if (this.d.get(i3).getRect().contains(i, i2)) {
                return i3;
            }
        }
        return -1;
    }

    public AbstractTouchNode a(int i) {
        if (i >= this.d.size()) {
            for (int size = this.d.size(); size <= i; size++) {
                e();
            }
        }
        return this.d.get(i);
    }

    public void d() {
        this.d.clear();
    }

    private void e() {
        try {
            this.d.add(this.e.newInstance());
        } catch (IllegalAccessException | InstantiationException unused) {
            ReleaseLogUtil.c("ChartTouchProvider", "reflect error");
        }
    }
}
