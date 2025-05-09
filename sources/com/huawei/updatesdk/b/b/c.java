package com.huawei.updatesdk.b.b;

import android.content.Context;
import android.os.Build;
import com.huawei.updatesdk.service.appmgr.bean.SDKNetTransmission;

/* loaded from: classes7.dex */
public class c extends com.huawei.updatesdk.a.b.c.c.c {

    @SDKNetTransmission
    private String brand;

    @SDKNetTransmission
    private String buildNumber;

    @SDKNetTransmission
    private String density;

    @SDKNetTransmission
    private int emuiApiLevel;

    @SDKNetTransmission
    public String firmwareVersion;

    @SDKNetTransmission
    private int harmonyApiLevel;

    @SDKNetTransmission
    private int magicApiLevel;

    @SDKNetTransmission
    private String magicVer;

    @SDKNetTransmission
    private String manufacturer;

    @SDKNetTransmission
    private int odm;

    @SDKNetTransmission
    private String osBrand;

    @SDKNetTransmission
    private String phoneType;

    @SDKNetTransmission
    private String resolution;

    @SDKNetTransmission
    private String sdkVersion;

    @SDKNetTransmission
    private long ts;

    public c() {
        Context a2 = com.huawei.updatesdk.a.b.a.a.c().a();
        this.ts = System.currentTimeMillis();
        this.firmwareVersion = Build.VERSION.RELEASE.trim();
        this.buildNumber = com.huawei.updatesdk.a.a.d.i.c.d();
        com.huawei.updatesdk.b.c.c a3 = com.huawei.updatesdk.b.c.b.a();
        this.phoneType = a3.e();
        this.density = com.huawei.updatesdk.a.a.d.i.c.e(a2);
        this.resolution = com.huawei.updatesdk.a.a.d.i.c.d(a2);
        this.emuiApiLevel = com.huawei.updatesdk.b.h.a.f().a();
        this.manufacturer = a3.d();
        this.brand = com.huawei.updatesdk.a.a.d.i.c.d;
        this.odm = com.huawei.updatesdk.a.a.d.i.c.h ? 1 : 0;
        this.sdkVersion = "5.0.2.300";
        this.harmonyApiLevel = com.huawei.updatesdk.a.a.d.i.c.i;
        this.osBrand = com.huawei.updatesdk.a.a.d.i.c.j;
        this.magicApiLevel = com.huawei.updatesdk.b.h.a.f().b();
        this.magicVer = com.huawei.updatesdk.b.h.a.f().c();
    }
}
