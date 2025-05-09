package defpackage;

import com.huawei.ui.device.declaration.xmlparser.DeclarationConstants;

/* loaded from: classes6.dex */
public class nzj extends nzc {
    private static final long serialVersionUID = -9205085941889233060L;
    private boolean b;
    private String d;
    private DeclarationConstants.Position e;

    public String d() {
        return this.d;
    }

    public void d(String str) {
        this.d = str;
    }

    public DeclarationConstants.Position c() {
        return this.e;
    }

    public void c(DeclarationConstants.Position position) {
        this.e = position;
    }

    public boolean e() {
        return this.b;
    }

    public void d(boolean z) {
        this.b = z;
    }

    public String toString() {
        return "Title{value='" + this.d + "', checkboxPosition=" + this.e + ", isChecked=" + this.b + '}';
    }
}
