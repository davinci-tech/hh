package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.homehealth.runcard.trackfragments.visibleutils.OnFragmentVisibilityChange;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class orx {
    private static final orx d = new orx();
    private int b = 0;

    /* renamed from: a, reason: collision with root package name */
    private List<OnFragmentVisibilityChange> f15924a = new ArrayList(10);

    private orx() {
    }

    public static orx c() {
        return d;
    }

    public void b() {
        if (this.b != 0) {
            LogUtil.a("Track_FVCUtils", "The Utils is working,failed");
            return;
        }
        this.b = 1;
        if (this.f15924a.size() != 0) {
            this.f15924a.clear();
        }
    }

    public void e() {
        this.b = 0;
        this.f15924a.clear();
    }

    public void b(int i) {
        Iterator<OnFragmentVisibilityChange> it = this.f15924a.iterator();
        while (it.hasNext()) {
            it.next().onChange(i);
        }
    }
}
