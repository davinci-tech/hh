package com.huawei.hianalytics.framework.listener;

/* loaded from: classes4.dex */
public interface IHAEventListener {
    void metricReport(String str);

    void onEvent(String str, String str2, String str3);

    void onReport(String str);

    void reportAppEvent();
}
