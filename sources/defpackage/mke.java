package defpackage;

import com.huawei.pluginachievement.ui.model.BestAchievementBasic;

/* loaded from: classes6.dex */
public class mke extends BestAchievementBasic {

    /* renamed from: a, reason: collision with root package name */
    private int f15033a;
    private int c;

    public int a() {
        return this.f15033a;
    }

    public void e(int i) {
        this.f15033a = i;
    }

    public int d() {
        return this.c;
    }

    public void d(int i) {
        this.c = i;
    }

    public String toString() {
        return "BestSteps{value=" + this.f15033a + ", recordDay=" + this.c + '}';
    }
}
