package com.huawei.networkclient.cache;

import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public interface ICacheStrategy {
    int getCacheStrategy();

    Long getDiskTimeOut();

    TimeUnit getDiskTimeUnit();

    String getKey();

    boolean needEncrypt();
}
