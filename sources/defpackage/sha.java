package defpackage;

import android.util.Base64;
import health.compact.a.WhiteBoxManager;
import java.nio.charset.StandardCharsets;

/* loaded from: classes8.dex */
public class sha {
    public static String b(int i, int i2, int i3, int i4) {
        WhiteBoxManager d = WhiteBoxManager.d();
        return new String(d.a(Base64.decode(d.d(i, i2) + d.d(i, i3) + d.d(i, i4), 0)), StandardCharsets.UTF_8);
    }
}
