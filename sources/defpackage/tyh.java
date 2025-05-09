package defpackage;

import java.io.Closeable;
import java.io.IOException;

/* loaded from: classes7.dex */
public class tyh {
    public static void c(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
