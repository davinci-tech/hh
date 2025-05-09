package com.huawei.updatesdk.service.appmgr.bean;

import android.content.Context;
import android.content.pm.PackageInfo;
import com.huawei.updatesdk.a.a.d.i.b;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class d extends com.huawei.updatesdk.b.b.c {
    public static final String APIMETHOD = "client.updateCheck";
    public static final int DEFAULT_UPGRADE_RESULT = 0;
    public static final int FULL_UPGRADE_RESULT = 1;
    public static final int INSTALL_CHECK_DEFAULT = 0;
    private String agVersion_;

    @SDKNetTransmission
    private String cno;
    private com.huawei.updatesdk.a.a.d.i.b deviceSpecParams_;
    private int getSafeGame_;
    private int gmsSupport_;

    @SDKNetTransmission
    private int hardwareType;

    @SDKNetTransmission
    private String harmonyDeviceType;
    private int installCheck_;
    private int isFullUpgrade_;

    @SDKNetTransmission
    private int isUpdateSdk;

    @SDKNetTransmission
    private String locale;
    private int mapleVer_;
    private String packageName_;

    @SDKNetTransmission
    private int pcEmulator;
    private a pkgInfo_;
    private String serviceCountry_;

    @SDKNetTransmission
    private int serviceType;
    private int supportMaple_;
    private int versionCode_;
    private String version_;

    public void h(String str) {
        this.version_ = str;
    }

    public void g(String str) {
        this.serviceCountry_ = str;
    }

    public void g(int i) {
        this.versionCode_ = i;
    }

    public void f(String str) {
        this.packageName_ = str;
    }

    public void f(int i) {
        this.supportMaple_ = i;
    }

    public void e(String str) {
        this.cno = str;
    }

    public void e(int i) {
        this.serviceType = i;
    }

    public void d(String str) {
        this.agVersion_ = str;
    }

    public void d(int i) {
        this.pcEmulator = i;
    }

    public void c(int i) {
        this.mapleVer_ = i;
    }

    public void b(int i) {
        this.installCheck_ = i;
    }

    public void a(a aVar) {
        this.pkgInfo_ = aVar;
    }

    public void a(int i) {
        this.gmsSupport_ = i;
    }

    public static class a extends com.huawei.updatesdk.a.b.c.c.b {
        private List<Param> params_;

        public void a(List<Param> list) {
            this.params_ = list;
        }
    }

    public static d a(List<PackageInfo> list) {
        d dVar = new d();
        a aVar = new a();
        dVar.a(aVar);
        ArrayList arrayList = new ArrayList();
        aVar.a(arrayList);
        Iterator<PackageInfo> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new Param(it.next()));
        }
        return dVar;
    }

    public d(List<Param> list) {
        this();
        a aVar = new a();
        aVar.a(list);
        a(aVar);
    }

    public d() {
        this.serviceType = 0;
        this.isUpdateSdk = 1;
        this.installCheck_ = 0;
        this.isFullUpgrade_ = 0;
        this.getSafeGame_ = 1;
        this.supportMaple_ = 0;
        Context a2 = com.huawei.updatesdk.a.b.a.a.c().a();
        a(APIMETHOD);
        b("1.2");
        this.locale = com.huawei.updatesdk.a.a.d.i.c.k();
        h(com.huawei.updatesdk.a.a.d.i.c.g(a2));
        g(com.huawei.updatesdk.a.a.d.i.c.f(a2));
        a(com.huawei.updatesdk.a.a.d.i.c.j(a2) ? 1 : 0);
        f(com.huawei.updatesdk.a.b.a.a.c().a().getPackageName());
        g(com.huawei.updatesdk.b.b.a.d().a());
        f(com.huawei.updatesdk.a.a.d.i.c.f().a());
        c(com.huawei.updatesdk.a.a.d.i.c.f().b());
        this.deviceSpecParams_ = new b.C0275b(a2).a(true).a();
        this.hardwareType = com.huawei.updatesdk.a.a.d.i.c.e();
        this.harmonyDeviceType = com.huawei.updatesdk.a.a.d.e.b();
        d(com.huawei.updatesdk.a.a.d.i.c.f().c());
    }
}
