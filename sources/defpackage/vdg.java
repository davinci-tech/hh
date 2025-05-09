package defpackage;

/* loaded from: classes7.dex */
public class vdg {
    private final vcp c;
    private final Object e;

    public vdg(vcp vcpVar, Object obj) {
        if (vcpVar == null) {
            throw new NullPointerException("cid must not be null!");
        }
        this.c = vcpVar;
        this.e = obj;
    }

    public vcp a() {
        return this.c;
    }

    public Object j() {
        return this.e;
    }
}
