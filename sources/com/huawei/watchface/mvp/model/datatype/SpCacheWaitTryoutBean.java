package com.huawei.watchface.mvp.model.datatype;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes7.dex */
public class SpCacheWaitTryoutBean {
    private ConcurrentHashMap<String, LinkedHashMap<String, String>> tryoutWfDownUrlMap;
    private ConcurrentHashMap<String, TryoutBean> waitTryOutBeanMap;
    private List<String> waitTryOutWatchFaceIdList;

    public SpCacheWaitTryoutBean(List<String> list, ConcurrentHashMap<String, TryoutBean> concurrentHashMap, ConcurrentHashMap<String, LinkedHashMap<String, String>> concurrentHashMap2) {
        this.waitTryOutWatchFaceIdList = list;
        this.waitTryOutBeanMap = concurrentHashMap;
        this.tryoutWfDownUrlMap = concurrentHashMap2;
    }

    public List<String> getWaitTryOutWatchFaceIdList() {
        return this.waitTryOutWatchFaceIdList;
    }

    public void setWaitTryOutWatchFaceIdList(List<String> list) {
        this.waitTryOutWatchFaceIdList = list;
    }

    public ConcurrentHashMap<String, TryoutBean> getWaitTryOutBeanMap() {
        return this.waitTryOutBeanMap;
    }

    public void setWaitTryOutBeanMap(ConcurrentHashMap<String, TryoutBean> concurrentHashMap) {
        this.waitTryOutBeanMap = concurrentHashMap;
    }

    public ConcurrentHashMap<String, LinkedHashMap<String, String>> getTryoutWfDownUrlMap() {
        return this.tryoutWfDownUrlMap;
    }

    public void setTryoutWfDownUrlMap(ConcurrentHashMap<String, LinkedHashMap<String, String>> concurrentHashMap) {
        this.tryoutWfDownUrlMap = concurrentHashMap;
    }
}
