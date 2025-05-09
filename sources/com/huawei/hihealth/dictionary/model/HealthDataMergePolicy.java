package com.huawei.hihealth.dictionary.model;

import com.huawei.hihealth.dictionary.utils.DictUtils;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class HealthDataMergePolicy {
    private static final Set<String> ALL_TYPES;
    public static final String CATEGORY_PRIORITY = "CATEGORY_PRIORITY";
    public static final String MAX = "MAX";
    public static final String MIN = "MIN";
    public static final String NEW = "NEW";
    public static final String OLD = "OLD";
    public static final String SOURCE_PRIORITY = "SOURCE_PRIORITY";

    static {
        HashSet hashSet = new HashSet();
        ALL_TYPES = hashSet;
        hashSet.addAll(DictUtils.e(HealthDataMergePolicy.class));
    }

    public static boolean validate(String str) {
        if (str == null) {
            return true;
        }
        return ALL_TYPES.contains(str);
    }
}
