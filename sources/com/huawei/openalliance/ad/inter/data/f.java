package com.huawei.openalliance.ad.inter.data;

import java.util.Map;

/* loaded from: classes5.dex */
public class f implements IPreCheckInfo {

    /* renamed from: a, reason: collision with root package name */
    private String f7055a;
    private String b;
    private Map<String, String> c;
    private int d = -1;
    private boolean e;

    @Override // com.huawei.openalliance.ad.inter.data.IPreCheckInfo
    public boolean isStrategyFiltered() {
        return this.e;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IPreCheckInfo
    public String getSlotId() {
        return this.f7055a;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IPreCheckInfo
    public int getPreCheckResult() {
        return this.d;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IPreCheckInfo
    public Map<String, String> getExt() {
        return this.c;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IPreCheckInfo
    public String getContentId() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public void a(boolean z) {
        this.e = z;
    }

    public void a(Map<String, String> map) {
        this.c = map;
    }

    public void a(String str) {
        this.f7055a = str;
    }

    public void a(int i) {
        this.d = i;
    }
}
