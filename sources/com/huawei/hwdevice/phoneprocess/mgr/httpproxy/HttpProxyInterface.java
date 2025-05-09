package com.huawei.hwdevice.phoneprocess.mgr.httpproxy;

import java.util.Map;

/* loaded from: classes5.dex */
public interface HttpProxyInterface {
    void delete(String str, Map<String, String> map, com.huawei.networkclient.ResultCallback<byte[]> resultCallback);

    void get(String str, Map<String, String> map, Map<String, String> map2, com.huawei.networkclient.ResultCallback<byte[]> resultCallback);

    void post(String str, Map<String, String> map, String str2, com.huawei.networkclient.ResultCallback<byte[]> resultCallback);

    void put(String str, Map<String, String> map, String str2, com.huawei.networkclient.ResultCallback<byte[]> resultCallback);
}
