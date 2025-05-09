package defpackage;

import android.os.Process;
import com.huawei.openplatform.abl.log.LogLevel;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* loaded from: classes5.dex */
public class lta {

    /* renamed from: a, reason: collision with root package name */
    private String f14863a;
    private String c;
    private String d;
    private int e;
    private int i;
    private long b = 0;
    private final StringBuilder h = new StringBuilder();

    public String toString() {
        StringBuilder sb = new StringBuilder();
        c(sb);
        a(sb);
        return sb.toString();
    }

    public String c() {
        StringBuilder sb = new StringBuilder();
        c(sb);
        return sb.toString();
    }

    public String e() {
        StringBuilder sb = new StringBuilder();
        a(sb);
        return sb.toString();
    }

    public <T> lta a(T t) {
        this.h.append(t);
        return this;
    }

    private StringBuilder c(StringBuilder sb) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.ENGLISH);
        sb.append('[');
        sb.append(simpleDateFormat.format(Long.valueOf(this.b)));
        String priorityToString = LogLevel.priorityToString(this.e);
        sb.append(' ');
        sb.append(priorityToString);
        sb.append('/');
        sb.append(this.d);
        sb.append('/');
        sb.append(this.c);
        sb.append(' ');
        sb.append(this.i);
        sb.append(':');
        sb.append(this.f14863a);
        sb.append(']');
        return sb;
    }

    private lta a() {
        this.b = System.currentTimeMillis();
        this.f14863a = Thread.currentThread().getName();
        this.i = Process.myPid();
        return this;
    }

    private StringBuilder a(StringBuilder sb) {
        sb.append(' ');
        sb.append((CharSequence) this.h);
        return sb;
    }

    lta(String str, int i, String str2) {
        this.d = null;
        this.c = "HA";
        this.e = 0;
        this.d = str;
        this.e = i;
        if (str2 != null) {
            this.c = str2;
        }
        a();
    }
}
