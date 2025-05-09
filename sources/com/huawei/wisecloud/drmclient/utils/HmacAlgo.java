package com.huawei.wisecloud.drmclient.utils;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes9.dex */
public enum HmacAlgo {
    DEFAULT(0, "HmacSHA256"),
    HMACSHA256(1, "HmacSHA256");

    private static final Map<Integer, String> PREFERRED_ALGS = new HashMap();
    private String algoName;
    private int id;

    static {
        Iterator it = EnumSet.allOf(HmacAlgo.class).iterator();
        while (it.hasNext()) {
            HmacAlgo hmacAlgo = (HmacAlgo) it.next();
            PREFERRED_ALGS.put(Integer.valueOf(hmacAlgo.id), hmacAlgo.algoName);
        }
    }

    HmacAlgo(int i, String str) {
        this.id = i;
        this.algoName = str;
    }

    public static String getAlgoName(int i) {
        return PREFERRED_ALGS.get(Integer.valueOf(i));
    }
}
