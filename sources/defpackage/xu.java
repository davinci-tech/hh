package defpackage;

import androidx.webkit.ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0;
import com.huawei.android.appbundle.splitinstall.LoadedSplitFetcher;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes8.dex */
public final class xu {
    private static final AtomicReference<LoadedSplitFetcher> d = new AtomicReference<>(null);

    static LoadedSplitFetcher b() {
        return d.get();
    }

    public static void c(LoadedSplitFetcher loadedSplitFetcher) {
        ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(d, null, loadedSplitFetcher);
    }
}
