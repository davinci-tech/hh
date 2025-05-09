package com.huawei.openalliance.ad.constant;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes5.dex */
public class DefBrowserPkgList {
    public static final Set<String> CONTENT;
    private static final Set<String> CONTENT_ORI;
    private static final String DEFAULT_ANDROID_BROWSER = "com.android.browser";
    private static final String DEFAULT_HUAWEI_BROWSER = "com.huawei.browser";

    static {
        HashSet hashSet = new HashSet();
        CONTENT_ORI = hashSet;
        hashSet.add(DEFAULT_ANDROID_BROWSER);
        hashSet.add("com.huawei.browser");
        CONTENT = Collections.unmodifiableSet(hashSet);
    }
}
