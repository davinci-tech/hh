package org.eclipse.californium.scandium.dtls;

import defpackage.vbn;
import defpackage.vbo;
import defpackage.vcb;
import defpackage.vdb;
import org.eclipse.californium.scandium.dtls.AlertMessage;

/* loaded from: classes7.dex */
public final class ChangeCipherSpecMessage implements DTLSMessage {
    private final CCSType b = CCSType.CHANGE_CIPHER_SPEC;

    @Override // org.eclipse.californium.scandium.dtls.DTLSMessage
    public int size() {
        return 1;
    }

    public enum CCSType {
        CHANGE_CIPHER_SPEC(1);

        private int code;

        CCSType(int i) {
            this.code = i;
        }

        public int getCode() {
            return this.code;
        }
    }

    @Override // org.eclipse.californium.scandium.dtls.DTLSMessage
    public ContentType getContentType() {
        return ContentType.CHANGE_CIPHER_SPEC;
    }

    @Override // org.eclipse.californium.scandium.dtls.DTLSMessage
    public String toString(int i) {
        return vcb.b(i) + "Change Cipher Spec Message" + vcb.a();
    }

    public String toString() {
        return toString(0);
    }

    @Override // org.eclipse.californium.scandium.dtls.DTLSMessage
    public byte[] toByteArray() {
        vbo vboVar = new vbo(1);
        vboVar.b(this.b.getCode(), 8);
        return vboVar.c();
    }

    public static DTLSMessage a(byte[] bArr) throws vdb {
        int c = new vbn(bArr).c(8);
        if (c == CCSType.CHANGE_CIPHER_SPEC.getCode()) {
            return new ChangeCipherSpecMessage();
        }
        throw new vdb("Unknown Change Cipher Spec code received: " + c, new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.ILLEGAL_PARAMETER));
    }
}
