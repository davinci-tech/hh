package defpackage;

import com.huawei.basefitnessadvice.model.DayInfo;
import com.huawei.basefitnessadvice.model.PlanWorkout;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class fex {

    /* renamed from: a, reason: collision with root package name */
    private int f12475a;
    private boolean b;
    private List<PlanWorkout> c = new ArrayList();
    private DayInfo e;

    public fex(int i, DayInfo dayInfo) {
        this.f12475a = i;
        this.e = dayInfo;
    }

    public void c(boolean z) {
        this.b = z;
    }

    public List<PlanWorkout> e() {
        return this.c;
    }
}
