package com.huawei.hihealth.dictionary.model;

import com.huawei.hihealth.dictionary.utils.DictUtils;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class HealthDataStatPolicy {
    private static final Set<String> ALL_TYPES;
    public static final String AVG = "AVG";
    public static final String COUNT = "COUNT";
    public static final String LAST = "LAST";
    public static final String MAX = "MAX";
    public static final String MIN = "MIN";
    public static final String SD = "SD";
    public static final String SUM = "SUM";

    static {
        HashSet hashSet = new HashSet();
        ALL_TYPES = hashSet;
        hashSet.addAll(DictUtils.e(HealthDataStatPolicy.class));
    }

    public static boolean validate(String str) {
        return ALL_TYPES.contains(str);
    }
}
