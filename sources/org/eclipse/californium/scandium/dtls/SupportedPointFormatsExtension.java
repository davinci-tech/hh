package org.eclipse.californium.scandium.dtls;

import com.huawei.operation.utils.Constants;
import defpackage.vbn;
import defpackage.vbo;
import defpackage.vcb;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.eclipse.californium.scandium.dtls.HelloExtension;

/* loaded from: classes7.dex */
public class SupportedPointFormatsExtension extends HelloExtension {
    private static final List<ECPointFormat> b;
    public static final SupportedPointFormatsExtension d;
    private final List<ECPointFormat> e;

    static {
        List<ECPointFormat> singletonList = Collections.singletonList(ECPointFormat.UNCOMPRESSED);
        b = singletonList;
        d = new SupportedPointFormatsExtension(singletonList);
    }

    private SupportedPointFormatsExtension(List<ECPointFormat> list) {
        super(HelloExtension.ExtensionType.EC_POINT_FORMATS);
        this.e = list;
    }

    public boolean b(ECPointFormat eCPointFormat) {
        return this.e.contains(eCPointFormat);
    }

    @Override // org.eclipse.californium.scandium.dtls.HelloExtension
    public String toString(int i) {
        StringBuilder sb = new StringBuilder(super.toString(i));
        String b2 = vcb.b(i + 1);
        String b3 = vcb.b(i + 2);
        sb.append(b2);
        sb.append("Elliptic Curves Point Formats (");
        sb.append(this.e.size());
        sb.append(" formats):");
        sb.append(vcb.a());
        for (ECPointFormat eCPointFormat : this.e) {
            sb.append(b3);
            sb.append("EC point format: ");
            sb.append(eCPointFormat);
            sb.append(vcb.a());
        }
        return sb.toString();
    }

    @Override // org.eclipse.californium.scandium.dtls.HelloExtension
    protected int getExtensionLength() {
        return this.e.size() + 1;
    }

    @Override // org.eclipse.californium.scandium.dtls.HelloExtension
    protected void writeExtensionTo(vbo vboVar) {
        vboVar.b(this.e.size(), 8);
        Iterator<ECPointFormat> it = this.e.iterator();
        while (it.hasNext()) {
            vboVar.b(it.next().getId(), 8);
        }
    }

    public static HelloExtension d(vbn vbnVar) {
        ArrayList arrayList = new ArrayList();
        vbn b2 = vbnVar.b(vbnVar.c(8));
        while (b2.e()) {
            ECPointFormat eCPointFormatById = ECPointFormat.getECPointFormatById(b2.c(8));
            if (eCPointFormatById != null) {
                arrayList.add(eCPointFormatById);
            }
        }
        if (arrayList.size() == 1 && arrayList.contains(ECPointFormat.UNCOMPRESSED)) {
            return d;
        }
        return new SupportedPointFormatsExtension(arrayList);
    }

    public enum ECPointFormat {
        UNCOMPRESSED(0),
        ANSIX962_COMPRESSED_PRIME(1),
        ANSIX962_COMPRESSED_CHAR2(2);

        private final int id;

        ECPointFormat(int i) {
            this.id = i;
        }

        public int getId() {
            return this.id;
        }

        @Override // java.lang.Enum
        public String toString() {
            int i = this.id;
            if (i == 0) {
                return "uncompressed (" + this.id + Constants.RIGHT_BRACKET_ONLY;
            }
            if (i == 1) {
                return "ansiX962_compressed_prime (" + this.id + Constants.RIGHT_BRACKET_ONLY;
            }
            if (i != 2) {
                return "";
            }
            return "ansiX962_compressed_char2 (" + this.id + Constants.RIGHT_BRACKET_ONLY;
        }

        public static ECPointFormat getECPointFormatById(int i) {
            if (i == 0) {
                return UNCOMPRESSED;
            }
            if (i == 1) {
                return ANSIX962_COMPRESSED_PRIME;
            }
            if (i != 2) {
                return null;
            }
            return ANSIX962_COMPRESSED_CHAR2;
        }
    }
}
