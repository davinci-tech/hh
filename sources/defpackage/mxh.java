package defpackage;

import java.util.Locale;

/* loaded from: classes6.dex */
public class mxh {

    /* renamed from: a, reason: collision with root package name */
    private final String f15231a;
    private final Locale b;
    private final String d;
    private final String e;

    private mxh(String str, String str2, String str3, Locale locale) {
        this.f15231a = str;
        this.e = str2;
        this.d = str3;
        this.b = locale;
    }

    public static mxh d(String str, String str2, String str3, Locale locale) {
        return new mxh(str, str2, str3, locale);
    }

    public String e() {
        return this.f15231a;
    }

    public String b() {
        return this.e;
    }

    public String c() {
        return this.d;
    }

    public Locale d() {
        return this.b;
    }
}
