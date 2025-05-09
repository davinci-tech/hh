package defpackage;

import java.io.File;

/* loaded from: classes4.dex */
public class hpt {
    public static String e(String str) {
        return e("common", str);
    }

    private static String e(String str, String str2) {
        return hap.e("3dMarker") + str + File.separator + str2;
    }

    public static String a(String str) {
        return hap.e("3dMarker") + "numbers" + File.separator + "number_" + str + ".gltf";
    }
}
