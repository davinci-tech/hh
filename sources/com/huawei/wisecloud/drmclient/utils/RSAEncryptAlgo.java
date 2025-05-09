package com.huawei.wisecloud.drmclient.utils;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: classes7.dex */
public enum RSAEncryptAlgo {
    RSA_ECB_PSS(1, "RSA/ECB/OAEPWithSHA-256AndMGF1Padding"),
    RSA_ECB_PKCS1(2, "RSA/ECB/PKCS1Padding");

    private static final Map<Integer, String> PREFERRED_ALGS_RSA_ENCRYPT = new HashMap();
    private String algoName;
    private int id;

    static {
        Iterator it = EnumSet.allOf(RSAEncryptAlgo.class).iterator();
        while (it.hasNext()) {
            RSAEncryptAlgo rSAEncryptAlgo = (RSAEncryptAlgo) it.next();
            PREFERRED_ALGS_RSA_ENCRYPT.put(Integer.valueOf(rSAEncryptAlgo.id), rSAEncryptAlgo.algoName);
        }
    }

    RSAEncryptAlgo(int i, String str) {
        this.id = i;
        this.algoName = str;
    }

    public static String getAlgoName(int i) {
        return PREFERRED_ALGS_RSA_ENCRYPT.get(Integer.valueOf(i));
    }

    public static Set<Integer> getAlgoNameList() {
        return PREFERRED_ALGS_RSA_ENCRYPT.keySet();
    }
}
