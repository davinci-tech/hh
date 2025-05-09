package com.huawei.wisesecurity.ucs.credential.crypto.cipher;

import defpackage.tty;
import defpackage.tvz;

/* loaded from: classes9.dex */
public class CredentialCipherText {
    private CredentialCipherAlg algId;
    private byte[] cipherBytes;
    private byte[] iv;
    private byte[] plainBytes;

    public void setPlainBytes(byte[] bArr) {
        this.plainBytes = tty.d(bArr);
    }

    public void setIv(byte[] bArr) {
        this.iv = tty.d(bArr);
    }

    public void setCipherBytes(byte[] bArr) {
        this.cipherBytes = tty.d(bArr);
    }

    public void setAlgId(CredentialCipherAlg credentialCipherAlg) {
        this.algId = credentialCipherAlg;
    }

    public byte[] getPlainBytes() {
        return tty.d(this.plainBytes);
    }

    public byte[] getIv() {
        return tty.d(this.iv);
    }

    public byte[] getCipherBytes() {
        return tty.d(this.cipherBytes);
    }

    public int getAlgId() {
        return this.algId.getId();
    }

    public void checkParam(boolean z) throws tvz {
        byte[] bArr = this.iv;
        if (bArr == null || (this.algId == CredentialCipherAlg.AES_GCM && bArr.length != 12)) {
            throw new tvz("illegal iv param..");
        }
        if (z) {
            byte[] bArr2 = this.plainBytes;
            if (bArr2 == null || bArr2.length == 0) {
                throw new tvz("plainBytes data can not be empty..");
            }
            return;
        }
        byte[] bArr3 = this.cipherBytes;
        if (bArr3 == null || bArr3.length == 0) {
            throw new tvz("cipherBytes data can not be empty..");
        }
    }
}
