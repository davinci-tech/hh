package defpackage;

/* loaded from: classes7.dex */
public class ttn extends Exception {
    private final int b;

    public ttn(String str) {
        super(str);
        this.b = 100001;
    }

    public int c() {
        return this.b;
    }

    @Override // java.lang.Throwable
    public String toString() {
        return "[errorCode:" + this.b + " message:" + getMessage() + "]";
    }
}
