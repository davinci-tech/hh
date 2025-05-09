package com.huawei.wisesecurity.kfs.ha.message;

import java.util.LinkedHashMap;

/* loaded from: classes7.dex */
public interface ReportMsgBuilder {
    LinkedHashMap<String, String> build();

    String getEventId();
}
