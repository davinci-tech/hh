package defpackage;

/* loaded from: classes7.dex */
public class vbe extends Exception {
    private static final long serialVersionUID = 1;

    public vbe() {
    }

    public vbe(String str) {
        super(str);
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        String message = super.getMessage();
        return message == null ? getClass().getSimpleName() : message;
    }
}
