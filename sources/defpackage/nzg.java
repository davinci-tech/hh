package defpackage;

import com.huawei.ui.device.declaration.xmlparser.DeclarationConstants;

/* loaded from: classes6.dex */
public class nzg extends nzh {
    private static final long serialVersionUID = 232733746650473673L;
    private String c;
    private String d;
    private DeclarationConstants.SwitchFaceState e;

    public String b() {
        return this.c;
    }

    public void b(String str) {
        this.c = str;
    }

    public DeclarationConstants.SwitchFaceState c() {
        return this.e;
    }

    public void c(DeclarationConstants.SwitchFaceState switchFaceState) {
        this.e = switchFaceState;
    }

    public void c(String str) {
        this.d = str;
    }

    public String toString() {
        return "SwitchFaceConfig{state=" + this.e + ", action='" + this.d + "'}";
    }
}
