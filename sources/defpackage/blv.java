package defpackage;

import health.compact.a.LogUtil;
import java.io.Closeable;
import java.io.IOException;

/* loaded from: classes3.dex */
public class blv {
    public static void d(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
                LogUtil.e("IoUtils", "closeQuietly occurIOException.");
            }
        }
    }
}
