package defpackage;

import com.huawei.ui.device.declaration.xmlparser.DeclarationConstants;

/* loaded from: classes6.dex */
public class nzr extends nzc {
    private static final long serialVersionUID = 7974573066439337805L;

    /* renamed from: a, reason: collision with root package name */
    protected String f15570a;
    protected DeclarationConstants.PlaceholderType c;

    public String e() {
        return this.f15570a;
    }

    public void e(String str) {
        this.f15570a = str;
    }

    public DeclarationConstants.PlaceholderType b() {
        return this.c;
    }

    public void d(DeclarationConstants.PlaceholderType placeholderType) {
        this.c = placeholderType;
    }

    public String toString() {
        return "Placeholder{value='" + this.f15570a + "', placeholderType=" + this.c + '}';
    }
}
