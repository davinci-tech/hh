package defpackage;

import com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.CloudApi;

/* loaded from: classes6.dex */
public class pug {

    /* renamed from: a, reason: collision with root package name */
    private static CloudApi f16263a;

    private pug() {
    }

    public static CloudApi a() {
        CloudApi cloudApi;
        synchronized (pug.class) {
            if (f16263a == null) {
                f16263a = new pud();
            }
            cloudApi = f16263a;
        }
        return cloudApi;
    }
}
