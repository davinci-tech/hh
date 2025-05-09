package defpackage;

import java.io.Serializable;

/* loaded from: classes3.dex */
public class ctc implements Serializable {
    private static final long serialVersionUID = -2788623292434891852L;
    private int e = -1;

    public int b() {
        return this.e;
    }

    public void c(int i) {
        this.e = i;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("BaseEntityModel{errorCode =");
        stringBuffer.append(this.e);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
