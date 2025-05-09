package defpackage;

import com.huawei.operation.utils.Constants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.eclipse.californium.scandium.dtls.HelloExtension;
import org.eclipse.californium.scandium.dtls.cipher.XECDHECryptography;

/* loaded from: classes7.dex */
public final class ven extends HelloExtension {

    /* renamed from: a, reason: collision with root package name */
    private final List<XECDHECryptography.SupportedGroup> f17694a;

    public ven(List<XECDHECryptography.SupportedGroup> list) {
        super(HelloExtension.ExtensionType.ELLIPTIC_CURVES);
        this.f17694a = list;
    }

    public List<XECDHECryptography.SupportedGroup> b() {
        return this.f17694a;
    }

    @Override // org.eclipse.californium.scandium.dtls.HelloExtension
    public String toString(int i) {
        StringBuilder sb = new StringBuilder(super.toString(i));
        String b = vcb.b(i + 1);
        String b2 = vcb.b(i + 2);
        sb.append(b);
        sb.append("Elliptic Curves (");
        sb.append(this.f17694a.size());
        sb.append(" curves):");
        sb.append(vcb.a());
        for (XECDHECryptography.SupportedGroup supportedGroup : this.f17694a) {
            sb.append(b2);
            sb.append("Elliptic Curve: ");
            sb.append(supportedGroup.name());
            sb.append(" (");
            sb.append(supportedGroup.getId());
            sb.append(Constants.RIGHT_BRACKET_ONLY);
            sb.append(vcb.a());
        }
        return sb.toString();
    }

    @Override // org.eclipse.californium.scandium.dtls.HelloExtension
    public int getExtensionLength() {
        return (this.f17694a.size() * 2) + 2;
    }

    @Override // org.eclipse.californium.scandium.dtls.HelloExtension
    public void writeExtensionTo(vbo vboVar) {
        vboVar.b(this.f17694a.size() * 2, 16);
        Iterator<XECDHECryptography.SupportedGroup> it = this.f17694a.iterator();
        while (it.hasNext()) {
            vboVar.b(it.next().getId(), 16);
        }
    }

    public static ven a(vbn vbnVar) {
        ArrayList arrayList = new ArrayList();
        vbn b = vbnVar.b(vbnVar.c(16));
        while (b.e()) {
            XECDHECryptography.SupportedGroup fromId = XECDHECryptography.SupportedGroup.fromId(b.c(16));
            if (fromId != null) {
                arrayList.add(fromId);
            }
        }
        return new ven(Collections.unmodifiableList(arrayList));
    }
}
