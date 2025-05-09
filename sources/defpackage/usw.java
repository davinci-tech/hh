package defpackage;

import java.io.File;
import java.nio.charset.Charset;

/* loaded from: classes7.dex */
public final class usw {
    public static final String b = File.separator;
    public static final Charset c;
    public static final Charset e;

    static {
        Charset forName = Charset.forName("UTF-8");
        e = forName;
        c = forName;
    }
}
