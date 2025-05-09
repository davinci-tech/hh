package com.huawei.wisesecurity.kfs.crypto.cipher;

import com.google.flatbuffers.reflection.BaseType;
import com.huawei.hms.network.okhttp.PublicSuffixDatabase;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.eclipse.californium.elements.util.JceNames;

/* loaded from: classes7.dex */
public enum CipherAlg {
    UNKNOWN((byte) -1, "unknown", 0),
    AES_CBC((byte) 0, "AES/CBC/PKCS5Padding", 16),
    KEY_STORE_AES_CBC((byte) 0, "AES/CBC/PKCS7Padding", 16),
    AES_GCM((byte) 1, "AES/GCM/NoPadding", 12),
    RSA_OAEP(BaseType.Union, "RSA/ECB/OAEPWithSHA-256AndMGF1Padding", 0),
    ECIES((byte) 32, "ECIESwithAES/NONE/PKCS7Padding", 0),
    ECIES_CBC(PublicSuffixDatabase.i, "ECIESwithAES-CBC/NONE/PKCS7Padding", 0);

    private static final Map<Byte, CipherAlg> MAPPING = new HashMap();
    private static final Map<String, CipherAlg> PREFERRED_ALGS = new HashMap();
    private final byte id;
    private final int ivLen;
    private final String transformation;

    static {
        Iterator it = EnumSet.allOf(CipherAlg.class).iterator();
        while (it.hasNext()) {
            CipherAlg cipherAlg = (CipherAlg) it.next();
            MAPPING.put(Byte.valueOf(cipherAlg.id), cipherAlg);
        }
        Map<String, CipherAlg> map = PREFERRED_ALGS;
        map.put(JceNames.EC, ECIES);
        map.put(JceNames.RSA, RSA_OAEP);
        map.put("AES", AES_GCM);
    }

    CipherAlg(byte b, String str, int i) {
        this.id = b;
        this.transformation = str;
        this.ivLen = i;
    }

    public static CipherAlg id2Alg(byte b) {
        return MAPPING.get(Byte.valueOf(b));
    }

    public static CipherAlg getPreferredAlg(String str) {
        return PREFERRED_ALGS.get(str);
    }

    public byte getId() {
        return this.id;
    }

    public String getTransformation() {
        return this.transformation;
    }

    public int getIvLen() {
        return this.ivLen;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.transformation;
    }
}
