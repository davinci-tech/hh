package defpackage;

import java.util.Arrays;
import org.eclipse.californium.elements.util.StandardCharsets;

/* loaded from: classes7.dex */
public final class vdt extends vbj {
    public static final vdt e = new vdt("");

    /* renamed from: a, reason: collision with root package name */
    private boolean f17687a;
    private String b;

    private vdt(byte[] bArr) {
        this(new String(bArr, StandardCharsets.UTF_8), bArr);
    }

    public vdt(String str) {
        super(str == null ? null : str.getBytes(StandardCharsets.UTF_8), 65535, false);
        this.b = str;
        this.f17687a = true;
    }

    public vdt(String str, byte[] bArr) {
        super(bArr, 65535, false);
        this.b = str;
        this.f17687a = Arrays.equals(bArr, str.getBytes(StandardCharsets.UTF_8));
    }

    public String a() {
        return this.b;
    }

    @Override // defpackage.vbj
    public String toString() {
        if (this.f17687a) {
            return this.b;
        }
        return this.b + "/" + e();
    }

    public static vdt e(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return e;
        }
        return new vdt(bArr);
    }
}
