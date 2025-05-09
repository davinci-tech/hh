package com.huawei.hianalytics.visual.autocollect;

import java.util.Map;

/* loaded from: classes4.dex */
public interface ParamCollector {
    public static final int TYPE_ALL = 0;
    public static final int TYPE_CLICK = 1;
    public static final int TYPE_PAGE = 2;

    Map<String, String> toMap(int i);
}
