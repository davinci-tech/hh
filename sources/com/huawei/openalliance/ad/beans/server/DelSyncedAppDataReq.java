package com.huawei.openalliance.ad.beans.server;

import java.util.List;

/* loaded from: classes5.dex */
public class DelSyncedAppDataReq {
    private List<String> appCollections;
    private List<String> appInstallDatas;
    private String callerPkg;

    public void b(List<String> list) {
        this.appInstallDatas = list;
    }

    public void a(List<String> list) {
        this.appCollections = list;
    }

    public void a(String str) {
        this.callerPkg = str;
    }
}
