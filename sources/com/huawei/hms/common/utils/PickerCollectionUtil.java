package com.huawei.hms.common.utils;

import java.util.Collection;

/* loaded from: classes4.dex */
public class PickerCollectionUtil {
    public static Boolean isNotEmpty(Collection collection) {
        return Boolean.valueOf((collection == null || collection.size() == 0) ? false : true);
    }
}
