package com.huawei.hihealth.dictionary.model;

import com.huawei.hihealth.dictionary.utils.DictUtils;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class HealthDataType {
    private static final Set<Integer> ALL_TYPES;
    public static final int SAMPLE_POINT = 0;
    public static final int SAMPLE_SEQUENCE = 1;

    static {
        HashSet hashSet = new HashSet();
        ALL_TYPES = hashSet;
        hashSet.addAll(DictUtils.e(HealthDataType.class));
    }

    public static boolean validate(Integer num) {
        return ALL_TYPES.contains(num);
    }
}
