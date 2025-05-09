package com.huawei.openalliance.ad.beans.metadata;

import java.util.List;

/* loaded from: classes5.dex */
public class DeviceExt {
    private List<String> aVideoCodec;
    private String bootMark;
    private String updateMark;

    public String c() {
        return this.updateMark;
    }

    public void b(String str) {
        this.updateMark = str;
    }

    public String b() {
        return this.bootMark;
    }

    public void a(List<String> list) {
        this.aVideoCodec = list;
    }

    public void a(String str) {
        this.bootMark = str;
    }

    public List<String> a() {
        return this.aVideoCodec;
    }
}
