package defpackage;

import com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.DataApi;

/* loaded from: classes6.dex */
public class pun {

    /* renamed from: a, reason: collision with root package name */
    private static DataApi f16266a;

    private pun() {
    }

    public static DataApi d() {
        DataApi dataApi;
        synchronized (pun.class) {
            if (f16266a == null) {
                f16266a = new pui();
            }
            dataApi = f16266a;
        }
        return dataApi;
    }
}
