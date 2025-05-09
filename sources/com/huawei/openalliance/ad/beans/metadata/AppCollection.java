package com.huawei.openalliance.ad.beans.metadata;

import com.huawei.openalliance.ad.annotations.a;
import java.util.List;

/* loaded from: classes5.dex */
public class AppCollection {
    private String acceler;
    private String adIntentScore;
    private String adMergedScore;
    private String adScore;
    private String adType;

    @a
    private String adid;

    @a
    private List<AppCollectInfo> appInfos;
    private Integer appUsageCollect;
    private String bagId;
    private String baro;
    private String battery;

    @a
    private List<BluetoothInfo> blueToothList;
    private Long bootTime;
    private String brand;
    private Integer btListRetcode;
    private String buildVer;
    private String charging;
    private String cost7d;
    private String cpuCoreCnt;
    private String cpuModel;
    private String cpuSpeed;
    private Long dataTime;
    private String dayIntentId1st;
    private String dayIntentId2nd;
    private String dspId;

    @a
    private String ecpm;
    private String emuiVer;
    private String eventType;

    @a
    private String extra1;
    private String extra2;
    private String extra3;
    private String extra4;
    private String extra5;
    private String freeSto;

    @a
    private String gaid;

    @a
    private String gaidTrackingEnabled;
    private String gpuModel;
    private String gyro;
    private String hmsVersion;
    private String industryId1st;
    private String industryId2nd;
    private String interactionType;
    private String internetAccess;

    @a
    private Integer isOaidTrackingEnabled;
    private String kitVersion;
    private String lang;
    private String magicUIVer;
    private String magnet;
    private String maker;
    private String mcc;
    private String mediaType;
    private String mnc;
    private String model;

    @a
    private String oaid;

    @a
    private String odid;
    private String pdtName;
    private Long reportTimestamp;
    private String reqIntentId1st;
    private String reqIntentId2nd;
    private String routerCountry;
    private long scrOnT;
    private String seqId;
    private String timeZone;
    private String totalMem;
    private String totalSto;

    @a
    private String udid;

    @a
    private String uuid;
    private String vendCountry;
    private String vendor;
    private String wifi;

    @a
    private List<WifiInfo> wifiList;
    private Integer wifiListRetcode;

    public void z(String str) {
        this.cpuSpeed = str;
    }

    public String z() {
        return this.pdtName;
    }

    public void y(String str) {
        this.cpuCoreCnt = str;
    }

    public String y() {
        return this.lang;
    }

    public void x(String str) {
        this.cpuModel = str;
    }

    public String x() {
        return this.wifi;
    }

    public void w(String str) {
        this.pdtName = str;
    }

    public String w() {
        return this.mnc;
    }

    public void v(String str) {
        this.lang = str;
    }

    public String v() {
        return this.internetAccess;
    }

    public void u(String str) {
        this.wifi = str;
    }

    public String u() {
        return this.kitVersion;
    }

    public void t(String str) {
        this.mcc = str;
    }

    public String t() {
        return this.hmsVersion;
    }

    public void s(String str) {
        this.mnc = str;
    }

    public List<AppCollectInfo> s() {
        return this.appInfos;
    }

    public void r(String str) {
        this.internetAccess = str;
    }

    public String r() {
        return this.extra4;
    }

    public void q(String str) {
        this.kitVersion = str;
    }

    public String q() {
        return this.extra3;
    }

    public void p(String str) {
        this.hmsVersion = str;
    }

    public String p() {
        return this.extra2;
    }

    public void o(String str) {
        this.odid = str;
    }

    public String o() {
        return this.extra1;
    }

    public void n(String str) {
        this.eventType = str;
    }

    public String n() {
        return this.eventType;
    }

    public void m(String str) {
        this.extra5 = str;
    }

    public String m() {
        return this.udid;
    }

    public void l(String str) {
        this.extra4 = str;
    }

    public Integer l() {
        return this.isOaidTrackingEnabled;
    }

    public void k(String str) {
        this.extra3 = str;
    }

    public String k() {
        return this.oaid;
    }

    public void j(String str) {
        this.extra2 = str;
    }

    public String j() {
        return this.timeZone;
    }

    public void i(String str) {
        this.extra1 = str;
    }

    public Integer i() {
        return this.appUsageCollect;
    }

    public void h(String str) {
        this.udid = str;
    }

    public Long h() {
        return this.dataTime;
    }

    public void g(String str) {
        this.oaid = str;
    }

    public String g() {
        return this.magicUIVer;
    }

    public void f(String str) {
        this.timeZone = str;
    }

    public String f() {
        return this.emuiVer;
    }

    public void e(String str) {
        this.magicUIVer = str;
    }

    public String e() {
        return this.buildVer;
    }

    public void d(String str) {
        this.emuiVer = str;
    }

    public void d(Integer num) {
        this.btListRetcode = num;
    }

    public String d() {
        return this.model;
    }

    public void c(List<WifiInfo> list) {
        this.wifiList = list;
    }

    public void c(String str) {
        this.buildVer = str;
    }

    public void c(Long l) {
        this.bootTime = l;
    }

    public void c(Integer num) {
        this.wifiListRetcode = num;
    }

    public String c() {
        return this.seqId;
    }

    public void b(List<BluetoothInfo> list) {
        this.blueToothList = list;
    }

    public void b(String str) {
        this.model = str;
    }

    public void b(Long l) {
        this.dataTime = l;
    }

    public void b(Integer num) {
        this.isOaidTrackingEnabled = num;
    }

    public Long b() {
        return this.reportTimestamp;
    }

    public String an() {
        return this.adid;
    }

    public String am() {
        return this.bagId;
    }

    public String al() {
        return this.dayIntentId2nd;
    }

    public String ak() {
        return this.dayIntentId1st;
    }

    public String aj() {
        return this.reqIntentId2nd;
    }

    public String ai() {
        return this.reqIntentId1st;
    }

    public String ah() {
        return this.cost7d;
    }

    public void ag(String str) {
        this.adid = str;
    }

    public String ag() {
        return this.dspId;
    }

    public void af(String str) {
        this.bagId = str;
    }

    public String af() {
        return this.adType;
    }

    public void ae(String str) {
        this.dayIntentId2nd = str;
    }

    public String ae() {
        return this.mediaType;
    }

    public void ad(String str) {
        this.dayIntentId1st = str;
    }

    public String ad() {
        return this.industryId2nd;
    }

    public void ac(String str) {
        this.reqIntentId2nd = str;
    }

    public String ac() {
        return this.industryId1st;
    }

    public void ab(String str) {
        this.reqIntentId1st = str;
    }

    public String ab() {
        return this.interactionType;
    }

    public void aa(String str) {
        this.cost7d = str;
    }

    public String aa() {
        return this.adMergedScore;
    }

    public void a(List<AppCollectInfo> list) {
        this.appInfos = list;
    }

    public void a(String str) {
        this.seqId = str;
    }

    public void a(Long l) {
        this.reportTimestamp = l;
    }

    public void a(Integer num) {
        this.appUsageCollect = num;
    }

    public void a(long j) {
        this.scrOnT = j;
    }

    public long a() {
        return this.scrOnT;
    }

    public void Z(String str) {
        this.dspId = str;
    }

    public String Z() {
        return this.adIntentScore;
    }

    public void Y(String str) {
        this.adType = str;
    }

    public String Y() {
        return this.adScore;
    }

    public void X(String str) {
        this.mediaType = str;
    }

    public String X() {
        return this.ecpm;
    }

    public void W(String str) {
        this.industryId2nd = str;
    }

    public List<WifiInfo> W() {
        return this.wifiList;
    }

    public void V(String str) {
        this.industryId1st = str;
    }

    public String V() {
        return this.brand;
    }

    public void U(String str) {
        this.interactionType = str;
    }

    public String U() {
        return this.maker;
    }

    public void T(String str) {
        this.adMergedScore = str;
    }

    public List<BluetoothInfo> T() {
        return this.blueToothList;
    }

    public void S(String str) {
        this.adIntentScore = str;
    }

    public Integer S() {
        return this.btListRetcode;
    }

    public void R(String str) {
        this.adScore = str;
    }

    public Integer R() {
        return this.wifiListRetcode;
    }

    public void Q(String str) {
        this.ecpm = str;
    }

    public String Q() {
        return this.uuid;
    }

    public void P(String str) {
        this.brand = str;
    }

    public String P() {
        return this.charging;
    }

    public void O(String str) {
        this.maker = str;
    }

    public String O() {
        return this.battery;
    }

    public void N(String str) {
        this.uuid = str;
    }

    public String N() {
        return this.baro;
    }

    public void M(String str) {
        this.charging = str;
    }

    public String M() {
        return this.magnet;
    }

    public void L(String str) {
        this.battery = str;
    }

    public String L() {
        return this.acceler;
    }

    public void K(String str) {
        this.baro = str;
    }

    public String K() {
        return this.gyro;
    }

    public void J(String str) {
        this.magnet = str;
    }

    public String J() {
        return this.routerCountry;
    }

    public void I(String str) {
        this.acceler = str;
    }

    public String I() {
        return this.vendCountry;
    }

    public void H(String str) {
        this.gyro = str;
    }

    public String H() {
        return this.vendor;
    }

    public void G(String str) {
        this.routerCountry = str;
    }

    public String G() {
        return this.freeSto;
    }

    public void F(String str) {
        this.vendCountry = str;
    }

    public String F() {
        return this.totalSto;
    }

    public void E(String str) {
        this.vendor = str;
    }

    public String E() {
        return this.totalMem;
    }

    public void D(String str) {
        this.freeSto = str;
    }

    public String D() {
        return this.gpuModel;
    }

    public void C(String str) {
        this.totalSto = str;
    }

    public String C() {
        return this.cpuSpeed;
    }

    public void B(String str) {
        this.totalMem = str;
    }

    public String B() {
        return this.cpuCoreCnt;
    }

    public void A(String str) {
        this.gpuModel = str;
    }

    public String A() {
        return this.cpuModel;
    }
}
