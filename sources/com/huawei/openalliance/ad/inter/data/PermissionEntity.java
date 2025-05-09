package com.huawei.openalliance.ad.inter.data;

import java.io.Serializable;

/* loaded from: classes5.dex */
public class PermissionEntity implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private String f7047a;
    private int b;

    public int getType() {
        return this.b;
    }

    public String getName() {
        return this.f7047a;
    }

    public void a(String str) {
        this.f7047a = str;
    }

    public void a(int i) {
        this.b = i;
    }

    public PermissionEntity(String str, int i) {
        this.f7047a = str;
        this.b = i;
    }

    public PermissionEntity() {
    }
}
