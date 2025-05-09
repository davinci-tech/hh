package com.huawei.wisecloud.drmclient.utils;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: classes7.dex */
public enum RSASignAlgo {
    SHA256withRSA(1, "SHA256withRSA"),
    SHA256WithRSAandMGF1(2, "SHA256WithRSA/PSS");

    private static final Map<Integer, String> PREFERRED_ALGS_RSA_SIGN = new HashMap();
    private String algoName;
    private int id;

    static {
        Iterator it = EnumSet.allOf(RSASignAlgo.class).iterator();
        while (it.hasNext()) {
            RSASignAlgo rSASignAlgo = (RSASignAlgo) it.next();
            PREFERRED_ALGS_RSA_SIGN.put(Integer.valueOf(rSASignAlgo.id), rSASignAlgo.algoName);
        }
    }

    RSASignAlgo(int i, String str) {
        this.id = i;
        this.algoName = str;
    }

    public static String getAlgoName(int i) {
        return PREFERRED_ALGS_RSA_SIGN.get(Integer.valueOf(i));
    }

    public static Set<Integer> getAlgoNameList() {
        return PREFERRED_ALGS_RSA_SIGN.keySet();
    }
}
