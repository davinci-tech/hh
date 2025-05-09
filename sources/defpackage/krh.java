package defpackage;

/* loaded from: classes5.dex */
public class krh {
    private String b;
    private boolean c;
    private String d;

    static class d {
        public static krh b = new krh();
    }

    private krh() {
        this.c = false;
    }

    public static krh b() {
        return d.b;
    }

    public boolean d() {
        return this.c;
    }

    public String c() {
        return this.d;
    }

    public String a() {
        return this.b;
    }
}
