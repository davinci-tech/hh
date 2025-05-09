package defpackage;

import java.io.IOException;
import net.lingala.zip4j.crypto.Decrypter;
import net.lingala.zip4j.io.inputstream.CipherInputStream;

/* loaded from: classes10.dex */
class urp extends CipherInputStream<a> {
    public urp(urt urtVar, usq usqVar, char[] cArr, int i) throws IOException {
        super(urtVar, usqVar, cArr, i, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.io.inputstream.CipherInputStream
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public a initializeDecrypter(usq usqVar, char[] cArr, boolean z) {
        return new a();
    }

    static class a implements Decrypter {
        @Override // net.lingala.zip4j.crypto.Decrypter
        public int decryptData(byte[] bArr, int i, int i2) {
            return i2;
        }

        a() {
        }
    }
}
