package com.huawei.openalliance.ad.inter.data;

import java.io.Serializable;

/* loaded from: classes5.dex */
public class DeviceAiParam implements Serializable {
    private static final long serialVersionUID = -1906936698381690943L;
    private String basicTargetTag;
    private String creativeLabel;
    private String encryptEcpm;
    private String encryptPrice;
    private String priceType;
    private String tradeMode;

    public void f(String str) {
        this.tradeMode = str;
    }

    public void e(String str) {
        this.priceType = str;
    }

    public void d(String str) {
        this.encryptPrice = str;
    }

    public void c(String str) {
        this.encryptEcpm = str;
    }

    public void b(String str) {
        this.basicTargetTag = str;
    }

    public void a(String str) {
        this.creativeLabel = str;
    }
}
