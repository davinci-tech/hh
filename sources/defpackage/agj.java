package defpackage;

import android.content.Context;
import java.io.File;

/* loaded from: classes3.dex */
public class agj {
    private static final String e = "marketInstall" + File.separator + "download" + File.separator + "hiSpace.apk";

    public static String a(Context context) {
        return context.getFilesDir() + File.separator + e;
    }
}
