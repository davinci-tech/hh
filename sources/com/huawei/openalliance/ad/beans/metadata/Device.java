package com.huawei.openalliance.ad.beans.metadata;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.openalliance.ad.annotations.a;
import com.huawei.openalliance.ad.annotations.g;
import com.huawei.openalliance.ad.beans.inner.CountryCodeBean;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.co;
import com.huawei.openalliance.ad.constant.OsType;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.utils.ae;
import com.huawei.openalliance.ad.utils.am;
import com.huawei.openalliance.ad.utils.cg;
import com.huawei.openalliance.ad.utils.cw;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.d;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.di;
import com.huawei.openalliance.ad.utils.x;
import com.huawei.wear.oversea.util.SystemPropertyValues;
import java.util.List;
import java.util.Locale;

/* loaded from: classes5.dex */
public class Device {
    private static final String TAG = "Device";

    @g
    private String adid;
    private Integer adsLoc;
    private String agCountryCode;

    @g
    private String agcAaid;

    @a
    private String androidid;
    private List<App> appList;
    private String belongCountry;
    private String brand;
    private String brandCust;
    private String buildVersion;
    private String clientTime;
    private int dpi;
    private Integer emuiSdkInt;
    private String emuiVer;
    private Integer encodingMode;
    private Long freeDiskSize;
    private Long freeSdcardSize;

    @g
    private String gaid;
    private String gaidTrackingEnabled;
    private Integer gpsOn;

    @a
    private String groupId;
    private int height;
    private Integer hmSdkInt;
    private String hmVer;
    private Integer hmftype;

    @a
    private List<String> insApps;
    private boolean isChinaROM;
    private String isTrackingEnabled;
    private String language;
    private String localeCountry;
    private String mVer;
    private String magicUIVer;
    private String maker;
    private String model;
    private String oVer;

    @g
    private String oaid;
    private String partnerChannel;
    private float pxratio;
    private String roLocale;
    private String roLocaleCountry;
    private String routerCountry;
    private String script;
    private Integer sdkType;
    private String simCountryIso;
    private Long totalDiskSize;
    private Long totalSdcardSize;
    private String tvModel;

    @g
    private String udid;
    private List<App> uninstalledPreAppList;
    private String useragent;

    @g
    private String uuid;
    private String vVer;
    private String vendor;
    private String vendorCountry;
    private String verCodeOfAG;
    private String verCodeOfHms;
    private String verCodeOfHsf;
    private String version;
    private int width;
    private int type = 4;
    private String os = OsType.ANDROID;
    private DeviceExt ext = new DeviceExt();

    public String z() {
        return this.gaid;
    }

    public String y() {
        return this.clientTime;
    }

    public String x() {
        return this.belongCountry;
    }

    public String w() {
        return this.simCountryIso;
    }

    public String v() {
        return this.localeCountry;
    }

    public String u() {
        return this.verCodeOfHms;
    }

    public String t() {
        return this.magicUIVer;
    }

    public String s() {
        return this.emuiVer;
    }

    public String r() {
        return this.verCodeOfHsf;
    }

    public String q() {
        return this.isTrackingEnabled;
    }

    public String p() {
        return this.oaid;
    }

    public String o() {
        return this.udid;
    }

    public String n() {
        return this.useragent;
    }

    public float m() {
        return this.pxratio;
    }

    public int l() {
        return this.dpi;
    }

    public void k(String str) {
        this.adid = str;
    }

    public String k() {
        return this.tvModel;
    }

    public void j(String str) {
        this.groupId = str;
    }

    public String j() {
        return this.buildVersion;
    }

    public void i(String str) {
        this.agcAaid = str;
    }

    public String i() {
        return this.androidid;
    }

    public void h(String str) {
        this.agCountryCode = str;
    }

    public String h() {
        return this.language;
    }

    public void g(String str) {
        this.uuid = str;
    }

    public int g() {
        return this.height;
    }

    public void f(String str) {
        this.gaidTrackingEnabled = str;
    }

    public int f() {
        return this.width;
    }

    public void e(String str) {
        this.gaid = str;
    }

    public String e() {
        return this.model;
    }

    public void d(String str) {
        this.isTrackingEnabled = str;
    }

    public String d() {
        return this.maker;
    }

    public void c(String str) {
        this.oaid = str;
    }

    public void c(Integer num) {
        this.encodingMode = num;
    }

    public String c() {
        return this.version;
    }

    public void b(List<App> list) {
        this.uninstalledPreAppList = list;
    }

    public void b(String str) {
        this.udid = str;
    }

    public void b(Integer num) {
        this.adsLoc = num;
    }

    public String b() {
        return this.os;
    }

    public String aj() {
        return this.adid;
    }

    public Integer ai() {
        return this.sdkType;
    }

    public List<App> ah() {
        return this.appList;
    }

    public boolean ag() {
        return this.isChinaROM;
    }

    public List<App> af() {
        return this.uninstalledPreAppList;
    }

    public String ae() {
        return this.groupId;
    }

    public DeviceExt ad() {
        return this.ext;
    }

    public String ac() {
        return this.agcAaid;
    }

    public Integer ab() {
        return this.hmSdkInt;
    }

    public Integer aa() {
        return this.hmftype;
    }

    public void a(List<String> list) {
        this.insApps = list;
    }

    public void a(String str) {
        this.androidid = str;
    }

    public void a(Integer num) {
        this.gpsOn = num;
    }

    public final void a(Context context, int i, int i2, int i3) {
        this.width = i;
        this.height = i2;
        this.language = d.a();
        this.script = d.b();
        this.type = i3;
        this.localeCountry = dd.d();
        this.simCountryIso = dd.e(context);
        this.belongCountry = fh.b(context).bw();
        this.clientTime = di.a();
        this.routerCountry = bz.a(context).o();
        this.appList = fh.b(context).bH();
        this.ext.a(cg.a(context).N());
        if (this.isChinaROM) {
            String f = x.f();
            if (TextUtils.isEmpty(f)) {
                return;
            }
            this.ext.b(f);
        }
    }

    public void a(Context context) {
        co a2 = bz.a(context);
        this.version = Build.VERSION.RELEASE;
        this.maker = a2.j();
        this.brand = a2.k();
        String b = a2.b();
        this.model = b;
        if (b != null) {
            this.model = b.toUpperCase(Locale.ENGLISH);
        }
        this.language = d.a();
        this.script = d.b();
        this.emuiVer = a2.e();
        this.emuiSdkInt = a2.h();
        this.magicUIVer = a2.i();
        this.verCodeOfHsf = d.l(context);
        this.verCodeOfHms = d.m(context);
        this.verCodeOfAG = d.n(context);
        this.agCountryCode = d.o(context);
        this.localeCountry = dd.d();
        this.simCountryIso = dd.e(context);
        this.roLocaleCountry = cz.l(dd.a("ro.product.locale.region"));
        this.roLocale = cz.l(dd.a(SystemPropertyValues.PROP_LOCALE_KEY));
        this.vendorCountry = cz.l(dd.a(CountryCodeBean.VENDORCOUNTRY_SYSTEMPROP));
        this.vendor = cz.l(dd.a(CountryCodeBean.VENDOR_SYSTEMPROP));
        this.type = x.k(context);
        this.sdkType = d.A(context);
        b(context);
    }

    public int a() {
        return this.type;
    }

    public String Z() {
        return this.hmVer;
    }

    public Integer Y() {
        return this.encodingMode;
    }

    public List<String> X() {
        return this.insApps;
    }

    public String W() {
        return this.partnerChannel;
    }

    public String V() {
        return this.brandCust;
    }

    public Integer U() {
        return this.emuiSdkInt;
    }

    public String T() {
        return this.script;
    }

    public String S() {
        return this.brand;
    }

    public String R() {
        return this.oVer;
    }

    public String Q() {
        return this.vVer;
    }

    public String P() {
        return this.mVer;
    }

    public Integer O() {
        return this.adsLoc;
    }

    public Integer N() {
        return this.gpsOn;
    }

    public String M() {
        return this.vendor;
    }

    public String L() {
        return this.agCountryCode;
    }

    public String K() {
        return this.verCodeOfAG;
    }

    public Long J() {
        return this.freeSdcardSize;
    }

    public Long I() {
        return this.totalSdcardSize;
    }

    public Long H() {
        return this.freeDiskSize;
    }

    public Long G() {
        return this.totalDiskSize;
    }

    public String F() {
        return this.roLocale;
    }

    public String E() {
        return this.routerCountry;
    }

    public String D() {
        return this.roLocaleCountry;
    }

    public String C() {
        return this.vendorCountry;
    }

    public String B() {
        return this.uuid;
    }

    public String A() {
        return this.gaidTrackingEnabled;
    }

    private void c(Context context) {
        String f = cw.f(context);
        if (!TextUtils.isEmpty(f)) {
            this.totalDiskSize = ae.d(f);
            this.freeDiskSize = ae.c(f);
        }
        String g = cw.g(context);
        if (TextUtils.isEmpty(g)) {
            return;
        }
        this.totalSdcardSize = ae.d(g);
        this.freeSdcardSize = ae.c(g);
    }

    private void b(Context context) {
        this.hmVer = x.m(context);
        if (am.a(context)) {
            this.hmftype = 1;
            this.os = am.a();
        }
        this.hmSdkInt = am.b(context);
    }

    private void a(Context context, boolean z) {
        co a2 = bz.a(context);
        this.dpi = d.i(context);
        this.pxratio = d.j(context);
        this.roLocale = cz.l(dd.a(SystemPropertyValues.PROP_LOCALE_KEY));
        this.version = Build.VERSION.RELEASE;
        this.maker = a2.j();
        this.brand = a2.k();
        String b = a2.b();
        this.model = b;
        if (b != null) {
            this.model = b.toUpperCase(Locale.ENGLISH);
        }
        this.buildVersion = a2.c();
        this.useragent = d.k(context);
        this.verCodeOfHsf = d.l(context);
        this.emuiVer = a2.e();
        this.magicUIVer = a2.i();
        this.verCodeOfHms = d.m(context);
        this.verCodeOfAG = d.n(context);
        this.brandCust = d.v(context);
        this.partnerChannel = dd.a("ro.build.2b2c.partner.ext_channel");
        if (z && this.isChinaROM && !bz.b(context)) {
            this.androidid = d.g(context);
        }
        if (this.isChinaROM) {
            String e = x.e();
            if (!TextUtils.isEmpty(e)) {
                this.ext.a(e);
            }
        }
        if (z) {
            this.udid = d.h(context);
            this.uuid = d.a(context, true);
        }
        cz.l(a2.q());
        this.vendor = cz.l(a2.p());
        this.roLocaleCountry = cz.l(dd.a("ro.product.locale.region"));
        if (this.isChinaROM) {
            this.agcAaid = com.huawei.openalliance.ad.utils.a.a(context);
        }
        this.sdkType = d.A(context);
        b(context);
        c(context);
    }

    public Device(Context context, boolean z) {
        this.isChinaROM = bz.a(context).d();
        a(context, z);
    }

    public Device(Context context, int i, int i2, int i3, boolean z) {
        this.isChinaROM = bz.a(context).d();
        a(context, z);
        a(context, i, i2, i3);
    }

    public Device() {
    }
}
