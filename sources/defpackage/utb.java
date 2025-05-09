package defpackage;

import java.io.File;
import java.nio.file.Path;

/* loaded from: classes10.dex */
public class utb {
    public static void c(usm usmVar, File file) {
        try {
            Path path = file.toPath();
            uta.b(path, usmVar.a());
            uta.a(path, usmVar.getLastModifiedTime());
        } catch (NoSuchMethodError unused) {
            uta.d(file, usmVar.getLastModifiedTime());
        }
    }
}
