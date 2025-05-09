package com.huawei.hms.network.embedded;

import com.huawei.hms.network.netdiag.qoe.QoeMetrics;
import java.util.Map;

/* loaded from: classes9.dex */
public class t5 extends QoeMetrics {

    /* renamed from: a, reason: collision with root package name */
    public String f5499a;
    public Map<String, Integer> b;
    public boolean c;

    @Override // com.huawei.hms.network.netdiag.qoe.QoeMetrics
    public boolean isSuccess() {
        return this.c;
    }

    @Override // com.huawei.hms.network.netdiag.qoe.QoeMetrics
    public int getUlRtt() {
        Map<String, Integer> map = this.b;
        if (map == null || !this.c) {
            return Integer.MAX_VALUE;
        }
        return map.get(s5.h + this.f5499a).intValue();
    }

    @Override // com.huawei.hms.network.netdiag.qoe.QoeMetrics
    public int getUlRate() {
        Map<String, Integer> map = this.b;
        if (map == null || !this.c) {
            return Integer.MAX_VALUE;
        }
        return map.get(s5.l + this.f5499a).intValue();
    }

    @Override // com.huawei.hms.network.netdiag.qoe.QoeMetrics
    public int getUlPkgLossRate() {
        Map<String, Integer> map = this.b;
        if (map == null || !this.c) {
            return Integer.MAX_VALUE;
        }
        return map.get(s5.n + this.f5499a).intValue();
    }

    @Override // com.huawei.hms.network.netdiag.qoe.QoeMetrics
    public int getUlBandwidth() {
        Map<String, Integer> map = this.b;
        if (map == null || !this.c) {
            return Integer.MAX_VALUE;
        }
        return map.get(s5.j + this.f5499a).intValue();
    }

    @Override // com.huawei.hms.network.netdiag.qoe.QoeMetrics
    public int getNetQoeLevel() {
        Map<String, Integer> map = this.b;
        if (map == null || !this.c) {
            return Integer.MAX_VALUE;
        }
        return map.get(s5.o + this.f5499a).intValue();
    }

    @Override // com.huawei.hms.network.netdiag.qoe.QoeMetrics
    public int getDlRtt() {
        Map<String, Integer> map = this.b;
        if (map == null || !this.c) {
            return Integer.MAX_VALUE;
        }
        return map.get(s5.i + this.f5499a).intValue();
    }

    @Override // com.huawei.hms.network.netdiag.qoe.QoeMetrics
    public int getDlRate() {
        Map<String, Integer> map = this.b;
        if (map == null || !this.c) {
            return Integer.MAX_VALUE;
        }
        return map.get(s5.m + this.f5499a).intValue();
    }

    @Override // com.huawei.hms.network.netdiag.qoe.QoeMetrics
    public int getDlBandwidth() {
        Map<String, Integer> map = this.b;
        if (map == null || !this.c) {
            return Integer.MAX_VALUE;
        }
        return map.get(s5.k + this.f5499a).intValue();
    }

    @Override // com.huawei.hms.network.netdiag.qoe.QoeMetrics
    public int getChannelNum() {
        Map<String, Integer> map = this.b;
        if (map == null || !this.c) {
            return Integer.MAX_VALUE;
        }
        return map.get(s5.d).intValue();
    }

    @Override // com.huawei.hms.network.netdiag.qoe.QoeMetrics
    public int getChannelIndex() {
        Map<String, Integer> map = this.b;
        if (map == null || !this.c) {
            return Integer.MAX_VALUE;
        }
        return map.get(s5.e + this.f5499a).intValue();
    }

    public t5(Map<String, Integer> map) {
        String str;
        this.c = false;
        this.b = map;
        this.c = map.get("status").intValue() == 1;
        if (map.containsKey("channelIndex0")) {
            str = "0";
        } else {
            if (!map.containsKey("channelIndex2")) {
                this.c = false;
                return;
            }
            str = "2";
        }
        this.f5499a = str;
    }
}
