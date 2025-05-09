package defpackage;

import org.eclipse.californium.scandium.dtls.ContentType;
import org.eclipse.californium.scandium.dtls.DTLSMessage;

/* loaded from: classes7.dex */
public final class vcf implements DTLSMessage {
    private final byte[] c;

    public vcf(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException("data must not be null!");
        }
        this.c = bArr;
    }

    @Override // org.eclipse.californium.scandium.dtls.DTLSMessage
    public ContentType getContentType() {
        return ContentType.APPLICATION_DATA;
    }

    @Override // org.eclipse.californium.scandium.dtls.DTLSMessage
    public String toString(int i) {
        return vcb.b(i) + "Application Data: " + vcb.b(this.c, (char) 0, 32) + vcb.a();
    }

    public String toString() {
        return toString();
    }

    @Override // org.eclipse.californium.scandium.dtls.DTLSMessage
    public int size() {
        return this.c.length;
    }

    @Override // org.eclipse.californium.scandium.dtls.DTLSMessage
    public byte[] toByteArray() {
        return this.c;
    }

    public static DTLSMessage c(byte[] bArr) {
        return new vcf(bArr);
    }

    public byte[] b() {
        return this.c;
    }
}
