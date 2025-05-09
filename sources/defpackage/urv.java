package defpackage;

import java.io.IOException;
import net.lingala.zip4j.io.inputstream.CipherInputStream;

/* loaded from: classes10.dex */
class urv extends CipherInputStream<urg> {
    public urv(urt urtVar, usq usqVar, char[] cArr, int i, boolean z) throws IOException {
        super(urtVar, usqVar, cArr, i, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.io.inputstream.CipherInputStream
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public urg initializeDecrypter(usq usqVar, char[] cArr, boolean z) throws IOException {
        return new urg(cArr, usqVar.getCrc(), usqVar.getLastModifiedTime(), a(), z);
    }

    private byte[] a() throws IOException {
        byte[] bArr = new byte[12];
        readRaw(bArr);
        return bArr;
    }
}
