package com.huawei.wisesecurity.kfs.crypto.signer;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.eclipse.californium.elements.util.JceNames;

/* loaded from: classes7.dex */
public enum SignAlg {
    UNKNOWN("UNKNOWN", "UNKNOWN"),
    ECDSA(JceNames.EC, "SHA256WithECDSA"),
    RSA_SHA256("RSA_SHA256", "SHA256WithRSA"),
    RSA_SHA256_PSS("RSA_SHA256_PSS", "SHA256withRSA/PSS"),
    HMAC_SHA256("HmacSHA256", "HmacSHA256");

    private static final Map<String, String> MAPPING = new HashMap();
    private static final Map<String, SignAlg> PREFERRED_ALGS = new HashMap();
    private final String alg;
    private final String keyType;

    static {
        Iterator it = EnumSet.allOf(SignAlg.class).iterator();
        while (it.hasNext()) {
            SignAlg signAlg = (SignAlg) it.next();
            MAPPING.put(signAlg.keyType, signAlg.alg);
        }
        Map<String, SignAlg> map = PREFERRED_ALGS;
        map.put(JceNames.EC, ECDSA);
        map.put(JceNames.RSA, RSA_SHA256);
        map.put("HMAC", HMAC_SHA256);
    }

    SignAlg(String str, String str2) {
        this.keyType = str;
        this.alg = str2;
    }

    public String getTransformation() {
        return this.alg;
    }

    public static SignAlg getPreferredAlg(String str) {
        return PREFERRED_ALGS.get(str);
    }
}
