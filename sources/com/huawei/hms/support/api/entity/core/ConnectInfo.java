package com.huawei.hms.support.api.entity.core;

import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.core.aidl.annotation.Packed;
import com.huawei.hms.support.api.entity.auth.Scope;
import java.util.List;

/* loaded from: classes4.dex */
public class ConnectInfo implements IMessageEntity {

    /* renamed from: a, reason: collision with root package name */
    @Packed
    private List<String> f5959a;

    @Packed
    private List<Scope> b;

    @Packed
    private String c;

    @Packed
    private String d;

    public ConnectInfo() {
    }

    public List<String> getApiNameList() {
        return this.f5959a;
    }

    public String getFingerprint() {
        return this.c;
    }

    public List<Scope> getScopeList() {
        return this.b;
    }

    public String getSubAppID() {
        return this.d;
    }

    public void setApiNameList(List<String> list) {
        this.f5959a = list;
    }

    public void setFingerprint(String str) {
        this.c = str;
    }

    public void setScopeList(List<Scope> list) {
        this.b = list;
    }

    public void setSubAppID(String str) {
        this.d = str;
    }

    public ConnectInfo(List<String> list, List<Scope> list2, String str, String str2) {
        this.f5959a = list;
        this.b = list2;
        this.c = str;
        this.d = str2;
    }
}
