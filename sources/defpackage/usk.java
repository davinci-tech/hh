package defpackage;

import net.lingala.zip4j.headers.HeaderSignature;
import net.lingala.zip4j.model.ZipHeader;

/* loaded from: classes7.dex */
public class usk extends ZipHeader {

    /* renamed from: a, reason: collision with root package name */
    private String f17529a = "";
    private long b;
    private int c;
    private int d;
    private long e;
    private int f;
    private int i;
    private int j;

    public usk() {
        setSignature(HeaderSignature.END_OF_CENTRAL_DIRECTORY);
    }

    public int c() {
        return this.d;
    }

    public void d(int i) {
        this.d = i;
    }

    public int b() {
        return this.c;
    }

    public void e(int i) {
        this.c = i;
    }

    public int j() {
        return this.j;
    }

    public void a(int i) {
        this.j = i;
    }

    public int f() {
        return this.f;
    }

    public void c(int i) {
        this.f = i;
    }

    public void b(int i) {
        this.i = i;
    }

    public long d() {
        return this.b;
    }

    public void c(long j) {
        this.b = j;
    }

    public long a() {
        return this.e;
    }

    public void d(long j) {
        this.e = j;
    }

    public String e() {
        return this.f17529a;
    }

    public void a(String str) {
        if (str != null) {
            this.f17529a = str;
        }
    }
}
