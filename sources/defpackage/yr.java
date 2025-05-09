package defpackage;

import com.huawei.haf.dynamic.DynamicDexOptimizer;
import java.io.File;
import java.util.List;

/* loaded from: classes8.dex */
final class yr {
    static void d(yi yiVar, List<String> list, File file, boolean z) {
        if (yiVar.l()) {
            File c2 = yw.a().c(yiVar);
            if (DynamicDexOptimizer.c(list, file, c2, false)) {
                return;
            }
            yo.a(new c(yiVar, c2));
        }
    }

    static class c implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private final yi f17764a;
        private final File e;

        c(yi yiVar, File file) {
            this.f17764a = yiVar;
            this.e = file;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (DynamicDexOptimizer.e(this.e)) {
                return;
            }
            File a2 = yw.a().a(this.f17764a);
            if (a2.exists()) {
                DynamicDexOptimizer.c(a2, this.e);
            }
        }
    }
}
