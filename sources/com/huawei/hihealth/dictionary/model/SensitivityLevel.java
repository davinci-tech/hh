package com.huawei.hihealth.dictionary.model;

import com.huawei.hihealth.dictionary.utils.DictUtils;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class SensitivityLevel {
    private static final Set<Integer> ALL_TYPES;
    public static final int EXTRA_HIGHT = 4;
    public static final int HIGHT = 3;
    public static final int LOW = 1;
    public static final int MID = 2;
    public static final int NONE = 0;

    static {
        HashSet hashSet = new HashSet();
        ALL_TYPES = hashSet;
        hashSet.addAll(DictUtils.e(SensitivityLevel.class));
    }

    public static boolean validate(Integer num) {
        return ALL_TYPES.contains(num);
    }
}
