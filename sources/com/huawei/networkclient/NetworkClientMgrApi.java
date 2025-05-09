package com.huawei.networkclient;

import com.huawei.hms.framework.network.restclient.hwhttp.HttpClient;
import com.huawei.networkclient.cache.ICacheStrategy;
import defpackage.lqi;
import defpackage.lqq;
import java.util.Map;

/* loaded from: classes9.dex */
public interface NetworkClientMgrApi {
    <Rsp> void callHttpDelete(String str, Map<String, String> map, Class<Rsp> cls, ResultCallback<Rsp> resultCallback);

    <Rsp> Rsp callHttpGet(String str, Map<String, String> map, Map<String, String> map2, Class<Rsp> cls);

    <Rsp> void callHttpGet(String str, Map<String, String> map, Map<String, String> map2, Class<Rsp> cls, ResultCallback<Rsp> resultCallback);

    <Rsp> Rsp callHttpPost(String str, Map<String, String> map, String str2, Class<Rsp> cls);

    <Rsp> lqq<Rsp> callHttpPost(String str, Map<String, String> map, String str2, Class<Rsp> cls, ICacheStrategy iCacheStrategy);

    <Rsp> void callHttpPost(String str, Map<String, String> map, String str2, Class<Rsp> cls, ResultCallback<Rsp> resultCallback);

    <Rsp> void callHttpPost(String str, Map<String, String> map, String str2, Class<Rsp> cls, ResultCallback<lqq<Rsp>> resultCallback, ICacheStrategy iCacheStrategy);

    <Rsp> void callHttpPut(String str, Map<String, String> map, String str2, Class<Rsp> cls, ResultCallback<Rsp> resultCallback);

    boolean cancelDownload(String str);

    void clear();

    void commonDownload(IDownloadStrategy iDownloadStrategy);

    <T> T create(Class<? extends T> cls);

    lqi getDiyInstance(HttpClient httpClient);

    lqi getInstance();

    boolean isCanceledOrDone(String str);

    void release(String str);
}
