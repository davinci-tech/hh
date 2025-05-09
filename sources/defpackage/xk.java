package defpackage;

import android.content.Context;
import android.content.Intent;
import androidx.webkit.ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0;
import com.huawei.android.appbundle.splitinstall.LoadedSplitFetcher;
import com.huawei.android.appbundle.splitinstall.SplitSessionLoader;
import com.huawei.android.bundlecore.load.listener.OnModuleLoadListener;
import com.huawei.hmf.tasks.TaskExecutors;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes8.dex */
public class xk {
    private static final AtomicReference<xk> e = new AtomicReference<>(null);

    private xk() {
    }

    public static boolean d(Context context) {
        if (!ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(e, null, new xk())) {
            return true;
        }
        xw.d(new d());
        xu.c(new a());
        return true;
    }

    static class a implements LoadedSplitFetcher {
        private a() {
        }

        @Override // com.huawei.android.appbundle.splitinstall.LoadedSplitFetcher
        public Set<String> loadedSplits() {
            return new HashSet(zh.a().getLoadedModuleNames());
        }

        @Override // com.huawei.android.appbundle.splitinstall.LoadedSplitFetcher
        public boolean loadLocalSplits(List<String> list) {
            return zh.a().loadInstalledModules(list);
        }

        @Override // com.huawei.android.appbundle.splitinstall.LoadedSplitFetcher
        public boolean checkModuleExisted(String str) {
            return zh.a().checkModuleExisted(str, true);
        }

        @Override // com.huawei.android.appbundle.splitinstall.LoadedSplitFetcher
        public boolean checkModuleUninstalled(String str) {
            return zh.a().checkModuleUninstalled(str, false);
        }
    }

    static class d implements SplitSessionLoader {
        private d() {
        }

        @Override // com.huawei.android.appbundle.splitinstall.SplitSessionLoader
        public void load(List<Intent> list, yd ydVar) {
            TaskExecutors.uiThread().execute(new c(list, ydVar));
        }
    }

    static class c implements Runnable, OnModuleLoadListener {
        private final List<Intent> b;
        private final yd c;

        c(List<Intent> list, yd ydVar) {
            this.b = list;
            this.c = ydVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            zh.a().c(this.b, this).run();
        }

        @Override // com.huawei.android.bundlecore.load.listener.OnModuleLoadListener
        public void onCompleted() {
            this.c.c(5);
        }

        @Override // com.huawei.android.bundlecore.load.listener.OnModuleLoadListener
        public void onFailed(int i) {
            this.c.e(6, i);
        }
    }
}
