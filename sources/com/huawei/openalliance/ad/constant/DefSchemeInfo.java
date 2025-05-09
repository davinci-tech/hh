package com.huawei.openalliance.ad.constant;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes5.dex */
public class DefSchemeInfo {
    private static final String DEFAULT_SCHEME_AND_PACKAGE = "market:com.huawei.appmarket";
    public static final Set<String> SCHEME_INFO;
    private static final Set<String> SCHEME_INFO_ORI;

    static {
        HashSet hashSet = new HashSet();
        SCHEME_INFO_ORI = hashSet;
        hashSet.add(DEFAULT_SCHEME_AND_PACKAGE);
        SCHEME_INFO = Collections.unmodifiableSet(hashSet);
    }
}
