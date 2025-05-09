package defpackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;

/* loaded from: classes10.dex */
public class vbs {
    private BufferedReader c;
    private String e;

    /* renamed from: a, reason: collision with root package name */
    public static final Logger f17651a = vha.a((Class<?>) vbs.class);
    private static final Pattern d = Pattern.compile("^\\-+BEGIN\\s+([\\w\\s]+)\\-+$");
    private static final Pattern b = Pattern.compile("^\\-+END\\s+([\\w\\s]+)\\-+$");

    public vbs(InputStream inputStream) {
        this.c = new BufferedReader(new InputStreamReader(inputStream));
    }

    public void b() {
        try {
            this.c.close();
        } catch (IOException unused) {
        }
    }

    public String a() throws IOException {
        this.e = null;
        while (true) {
            String readLine = this.c.readLine();
            if (readLine == null) {
                break;
            }
            Matcher matcher = d.matcher(readLine);
            if (matcher.matches()) {
                String group = matcher.group(1);
                this.e = group;
                f17651a.debug("Found Begin of {}", group);
                break;
            }
        }
        return this.e;
    }

    public byte[] e() throws IOException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            String readLine = this.c.readLine();
            if (readLine == null) {
                break;
            }
            Matcher matcher = b.matcher(readLine);
            if (matcher.matches()) {
                String group = matcher.group(1);
                if (group.equals(this.e)) {
                    byte[] e = vbk.e(sb.toString());
                    f17651a.debug("Found End of {}", this.e);
                    return e;
                }
                f17651a.warn("Found End of {}, but expected {}!", group, this.e);
            } else {
                sb.append(readLine);
            }
        }
        this.e = null;
        return null;
    }
}
