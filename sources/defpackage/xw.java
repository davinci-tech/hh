package defpackage;

import androidx.webkit.ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0;
import com.huawei.android.appbundle.splitinstall.SplitSessionLoader;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes8.dex */
public final class xw {
    private static final AtomicReference<SplitSessionLoader> b = new AtomicReference<>();

    static SplitSessionLoader e() {
        return b.get();
    }

    public static void d(SplitSessionLoader splitSessionLoader) {
        ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(b, null, splitSessionLoader);
    }
}
