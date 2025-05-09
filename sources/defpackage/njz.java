package defpackage;

import android.content.Context;
import java.io.Closeable;
import java.io.InputStream;

/* loaded from: classes9.dex */
public class njz {
    public static String a(Context context, String str) {
        try {
            InputStream open = context.getAssets().open(str);
            try {
                byte[] bArr = new byte[open.available()];
                open.read(bArr);
                String str2 = new String(bArr, "utf-8");
                if (open != null) {
                    open.close();
                }
                return str2;
            } finally {
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void b(Closeable... closeableArr) {
        if (closeableArr == null) {
            return;
        }
        for (Closeable closeable : closeableArr) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
