package com.huawei.networkclient;

import java.util.Map;

/* loaded from: classes5.dex */
public interface ParamsFactory {
    Map<String, Object> getBody(Object obj);

    Map<String, String> getHeaders();
}
