package org.eclipse.californium.scandium.dtls;

import defpackage.vbn;
import defpackage.vbo;
import defpackage.vcb;
import defpackage.vdb;
import org.eclipse.californium.scandium.dtls.AlertMessage;
import org.eclipse.californium.scandium.dtls.HelloExtension;

/* loaded from: classes7.dex */
public class MaxFragmentLengthExtension extends HelloExtension {
    private final Length e;

    @Override // org.eclipse.californium.scandium.dtls.HelloExtension
    protected int getExtensionLength() {
        return 1;
    }

    public MaxFragmentLengthExtension(Length length) {
        super(HelloExtension.ExtensionType.MAX_FRAGMENT_LENGTH);
        if (length == null) {
            throw new NullPointerException("Length must not be null");
        }
        this.e = length;
    }

    public Length d() {
        return this.e;
    }

    @Override // org.eclipse.californium.scandium.dtls.HelloExtension
    public String toString(int i) {
        return super.toString(i) + vcb.b(i + 1) + "Code: " + this.e.code() + " (" + this.e.length() + " bytes)" + vcb.a();
    }

    @Override // org.eclipse.californium.scandium.dtls.HelloExtension
    protected void writeExtensionTo(vbo vboVar) {
        vboVar.b(this.e.code, 8);
    }

    public static MaxFragmentLengthExtension b(vbn vbnVar) throws vdb {
        int c = vbnVar.c(8);
        Length fromCode = Length.fromCode(c);
        if (fromCode != null) {
            return new MaxFragmentLengthExtension(fromCode);
        }
        throw new vdb(String.format("Peer uses unknown code [%d] in %s extension", Integer.valueOf(c), HelloExtension.ExtensionType.MAX_FRAGMENT_LENGTH.name()), new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.ILLEGAL_PARAMETER));
    }

    public enum Length {
        BYTES_512(1, 512),
        BYTES_1024(2, 1024),
        BYTES_2048(3, 2048),
        BYTES_4096(4, 4096);

        private final int code;
        private final int length;

        Length(int i, int i2) {
            this.code = i;
            this.length = i2;
        }

        public int code() {
            return this.code;
        }

        public int length() {
            return this.length;
        }

        public static Length fromCode(int i) {
            if (i == 1) {
                return BYTES_512;
            }
            if (i == 2) {
                return BYTES_1024;
            }
            if (i == 3) {
                return BYTES_2048;
            }
            if (i != 4) {
                return null;
            }
            return BYTES_4096;
        }
    }
}
