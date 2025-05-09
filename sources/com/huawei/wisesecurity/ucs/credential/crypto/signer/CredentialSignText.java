package com.huawei.wisesecurity.ucs.credential.crypto.signer;

import defpackage.tty;
import defpackage.tvz;
import defpackage.twc;

/* loaded from: classes7.dex */
public class CredentialSignText {
    private CredentialSignAlg algId;
    private byte[] dataBytes;
    private byte[] signature;

    public void setSignature(byte[] bArr) {
        this.signature = tty.d(bArr);
    }

    public void setDataBytes(byte[] bArr) {
        this.dataBytes = tty.d(bArr);
    }

    public void setAlgId(CredentialSignAlg credentialSignAlg) {
        this.algId = credentialSignAlg;
    }

    public byte[] getSignature() {
        return tty.d(this.signature);
    }

    public byte[] getDataBytes() {
        return tty.d(this.dataBytes);
    }

    public int getAlgId() {
        return this.algId.getId();
    }

    public void checkParam(boolean z) throws twc {
        byte[] bArr = this.dataBytes;
        if (bArr == null || bArr.length == 0) {
            throw new tvz("dataBytes data can not be empty..");
        }
        if (z) {
            return;
        }
        byte[] bArr2 = this.signature;
        if (bArr2 == null || bArr2.length == 0) {
            throw new tvz("signature data can not be empty..");
        }
    }
}
