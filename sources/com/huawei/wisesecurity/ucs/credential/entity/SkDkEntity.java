package com.huawei.wisesecurity.ucs.credential.entity;

import com.huawei.hidatamanager.util.Const;
import com.huawei.wisesecurity.kfs.crypto.cipher.CipherAlg;
import defpackage.tsz;
import defpackage.ttp;
import defpackage.twc;
import defpackage.twf;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes7.dex */
public class SkDkEntity {
    private static final int GCM_IV_LEN = 12;
    private static final int GCM_TAG_LEN = 16;
    private byte[] iv;
    private byte[] secKey;

    public byte[] getSecKey() {
        return this.secKey;
    }

    public byte[] getIv() {
        return this.iv;
    }

    public byte[] decryptSkDk(byte[] bArr) throws twc {
        try {
            return new tsz.c().a(CipherAlg.AES_GCM).a(this.iv).e(new SecretKeySpec(bArr, "AES")).b().getDecryptHandler().from(this.secKey).to();
        } catch (ttp e) {
            StringBuilder e2 = twf.e("decrypt sk dk error : ");
            e2.append(e.getMessage());
            throw new twc(1003L, e2.toString());
        }
    }

    public static SkDkEntity from(byte[] bArr) throws twc {
        SkDkEntity skDkEntity = new SkDkEntity();
        if (bArr.length < 28) {
            throw new twc(Const.RawDataType.HEALTH_EVENT_RECORD, "SK DK format error");
        }
        byte[] bArr2 = new byte[12];
        skDkEntity.iv = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, 12);
        byte[] bArr3 = new byte[bArr.length - 12];
        skDkEntity.secKey = bArr3;
        System.arraycopy(bArr, 12, bArr3, 0, bArr.length - 12);
        return skDkEntity;
    }
}
