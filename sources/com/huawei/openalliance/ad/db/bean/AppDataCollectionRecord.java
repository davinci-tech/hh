package com.huawei.openalliance.ad.db.bean;

import com.huawei.openalliance.ad.annotations.e;
import com.huawei.openalliance.ad.annotations.f;
import com.huawei.openalliance.ad.beans.metadata.AppCollectInfo;
import com.huawei.openalliance.ad.beans.metadata.BluetoothInfo;
import com.huawei.openalliance.ad.beans.metadata.WifiInfo;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes5.dex */
public class AppDataCollectionRecord extends a implements Serializable {
    public static final String REPORT_TIME = "reportTimestamp";
    private static final long serialVersionUID = 30469300;

    @e
    private String _id;
    private String acceler;
    private String adIntentScore;
    private String adMergedScore;
    private String adScore;
    private String adType;
    private String adid;

    @f
    private EncryptionField<List<AppCollectInfo>> appInfos;
    private String appUsageCollect;
    private String bagId;
    private String baro;
    private String battery;

    @f
    private EncryptionField<List<BluetoothInfo>> blueToothList;
    private String brand;
    private String btListRetcode;
    private String buildVer;
    private String charging;
    private String cost7d;
    private String cpuCoreCnt;
    private String cpuModel;
    private String cpuSpeed;
    private String dataTime;
    private String dayIntentId1st;
    private String dayIntentId2nd;
    private String dspId;

    @f
    private EncryptionField<String> ecpm;
    private String emuiVer;
    private String eventType;
    private String extra1;
    private String extra2;
    private String extra3;
    private String extra4;
    private String extra5;
    private String freeSto;
    private String gpuModel;
    private String gyro;
    private String hmsVersion;
    private String industryId1st;
    private String industryId2nd;
    private String interactionType;
    private String internetAccess;
    private int isOaidTrackingEnabled;
    private String kitVersion;
    private String lang;
    private String magicUIVer;
    private String magnet;
    private String maker;
    private String mcc;
    private String mediaType;
    private String mnc;
    private String model;
    private String oaid;
    private String odid;
    private String pdtName;
    private long reportTimestamp;
    private String reqIntentId1st;
    private String reqIntentId2nd;
    private String routerCountry;
    private long scrOnT;
    private String timeZone;
    private String totalMem;
    private String totalSto;
    private String udid;
    private String uuid;
    private String vendCountry;
    private String vendor;
    private String wifi;

    @f
    private EncryptionField<List<WifiInfo>> wifiList;
    private String wifiListRetcode;

    public void z(String str) {
        this.cpuSpeed = str;
    }

    public String z() {
        return this.wifi;
    }

    public void y(String str) {
        this.cpuCoreCnt = str;
    }

    public String y() {
        return this.mcc;
    }

    public void x(String str) {
        this.cpuModel = str;
    }

    public String x() {
        return this.mnc;
    }

    public void w(String str) {
        this.pdtName = str;
    }

    public String w() {
        return this.internetAccess;
    }

    public void v(String str) {
        this.lang = str;
    }

    public String v() {
        return this.kitVersion;
    }

    public void u(String str) {
        this.wifi = str;
    }

    public String u() {
        return this.hmsVersion;
    }

    public void t(String str) {
        this.mnc = str;
    }

    public String t() {
        return this.odid;
    }

    public void s(String str) {
        this.internetAccess = str;
    }

    public String s() {
        return this.magicUIVer;
    }

    public void r(String str) {
        this.kitVersion = str;
    }

    public String r() {
        return this.emuiVer;
    }

    public void q(String str) {
        this.hmsVersion = str;
    }

    public String q() {
        return this.buildVer;
    }

    public void p(String str) {
        this.magicUIVer = str;
    }

    public String p() {
        return this.model;
    }

    public void o(String str) {
        this.emuiVer = str;
    }

    public long o() {
        return this.reportTimestamp;
    }

    public void n(String str) {
        this.buildVer = str;
    }

    public String n() {
        return this.extra5;
    }

    public void m(String str) {
        this.model = str;
    }

    public String m() {
        return this.extra4;
    }

    public void l(String str) {
        this.extra5 = str;
    }

    public String l() {
        return this.extra3;
    }

    public void k(String str) {
        this.extra4 = str;
    }

    public String k() {
        return this.extra2;
    }

    public void j(String str) {
        this.extra3 = str;
    }

    public String j() {
        return this.extra1;
    }

    public void i(String str) {
        this.extra2 = str;
    }

    public String i() {
        return this.eventType;
    }

    public void h(String str) {
        this.extra1 = str;
    }

    public String h() {
        return this.udid;
    }

    public void g(String str) {
        this.eventType = str;
    }

    public int g() {
        return this.isOaidTrackingEnabled;
    }

    public void f(String str) {
        this.udid = str;
    }

    public String f() {
        return this.oaid;
    }

    public void e(String str) {
        this.oaid = str;
    }

    public String e() {
        return this.timeZone;
    }

    public void d(String str) {
        this.timeZone = str;
    }

    public String d() {
        return this.appUsageCollect;
    }

    public void c(List<AppCollectInfo> list) {
        EncryptionField<List<AppCollectInfo>> encryptionField = new EncryptionField<>(List.class, AppCollectInfo.class);
        encryptionField.a((EncryptionField<List<AppCollectInfo>>) list);
        this.appInfos = encryptionField;
    }

    public void c(String str) {
        this.appUsageCollect = str;
    }

    public String c() {
        return this.dataTime;
    }

    public void b(List<WifiInfo> list) {
        EncryptionField<List<WifiInfo>> encryptionField = new EncryptionField<>(List.class, WifiInfo.class);
        encryptionField.a((EncryptionField<List<WifiInfo>>) list);
        this.wifiList = encryptionField;
    }

    public void b(String str) {
        this.dataTime = str;
    }

    public void b(long j) {
        this.reportTimestamp = j;
    }

    public String b() {
        return this._id;
    }

    public String aq() {
        return this.adid;
    }

    public String ap() {
        return this.bagId;
    }

    public String ao() {
        return this.dayIntentId2nd;
    }

    public String an() {
        return this.dayIntentId1st;
    }

    public String am() {
        return this.reqIntentId2nd;
    }

    public String al() {
        return this.reqIntentId1st;
    }

    public String ak() {
        return this.cost7d;
    }

    public String aj() {
        return this.dspId;
    }

    public void ai(String str) {
        this.adid = str;
    }

    public String ai() {
        return this.adType;
    }

    public void ah(String str) {
        this.bagId = str;
    }

    public String ah() {
        return this.mediaType;
    }

    public void ag(String str) {
        this.dayIntentId2nd = str;
    }

    public String ag() {
        return this.industryId2nd;
    }

    public void af(String str) {
        this.dayIntentId1st = str;
    }

    public String af() {
        return this.industryId1st;
    }

    public void ae(String str) {
        this.reqIntentId2nd = str;
    }

    public String ae() {
        return this.interactionType;
    }

    public void ad(String str) {
        this.reqIntentId1st = str;
    }

    public String ad() {
        return this.adMergedScore;
    }

    public void ac(String str) {
        this.cost7d = str;
    }

    public String ac() {
        return this.adIntentScore;
    }

    public void ab(String str) {
        this.dspId = str;
    }

    public String ab() {
        return this.adScore;
    }

    public void aa(String str) {
        this.adType = str;
    }

    public EncryptionField<String> aa() {
        return this.ecpm;
    }

    public void a(List<BluetoothInfo> list) {
        EncryptionField<List<BluetoothInfo>> encryptionField = new EncryptionField<>(List.class, BluetoothInfo.class);
        encryptionField.a((EncryptionField<List<BluetoothInfo>>) list);
        this.blueToothList = encryptionField;
    }

    public void a(String str) {
        this._id = str;
    }

    public void a(long j) {
        this.scrOnT = j;
    }

    public void a(int i) {
        this.isOaidTrackingEnabled = i;
    }

    public long a() {
        return this.scrOnT;
    }

    public void Z(String str) {
        this.mediaType = str;
    }

    public String Z() {
        return this.brand;
    }

    public void Y(String str) {
        this.industryId2nd = str;
    }

    public String Y() {
        return this.maker;
    }

    public void X(String str) {
        this.industryId1st = str;
    }

    public EncryptionField<List<AppCollectInfo>> X() {
        return this.appInfos;
    }

    public void W(String str) {
        this.interactionType = str;
    }

    public EncryptionField<List<WifiInfo>> W() {
        return this.wifiList;
    }

    public void V(String str) {
        this.adMergedScore = str;
    }

    public EncryptionField<List<BluetoothInfo>> V() {
        return this.blueToothList;
    }

    public void U(String str) {
        this.adIntentScore = str;
    }

    public String U() {
        return this.btListRetcode;
    }

    public void T(String str) {
        this.adScore = str;
    }

    public String T() {
        return this.wifiListRetcode;
    }

    public void S(String str) {
        EncryptionField<String> encryptionField = new EncryptionField<>(String.class);
        encryptionField.a((EncryptionField<String>) str);
        this.ecpm = encryptionField;
    }

    public String S() {
        return this.uuid;
    }

    public void R(String str) {
        this.brand = str;
    }

    public String R() {
        return this.charging;
    }

    public void Q(String str) {
        this.maker = str;
    }

    public String Q() {
        return this.battery;
    }

    public void P(String str) {
        this.btListRetcode = str;
    }

    public String P() {
        return this.baro;
    }

    public void O(String str) {
        this.wifiListRetcode = str;
    }

    public String O() {
        return this.magnet;
    }

    public void N(String str) {
        this.uuid = str;
    }

    public String N() {
        return this.acceler;
    }

    public void M(String str) {
        this.charging = str;
    }

    public String M() {
        return this.gyro;
    }

    public void L(String str) {
        this.battery = str;
    }

    public String L() {
        return this.routerCountry;
    }

    public void K(String str) {
        if (str != null) {
            this.baro = str;
        }
    }

    public String K() {
        return this.vendCountry;
    }

    public void J(String str) {
        this.magnet = str;
    }

    public String J() {
        return this.vendor;
    }

    public void I(String str) {
        this.acceler = str;
    }

    public String I() {
        return this.freeSto;
    }

    public void H(String str) {
        this.gyro = str;
    }

    public String H() {
        return this.totalSto;
    }

    public void G(String str) {
        this.routerCountry = str;
    }

    public String G() {
        return this.totalMem;
    }

    public void F(String str) {
        this.vendCountry = str;
    }

    public String F() {
        return this.gpuModel;
    }

    public void E(String str) {
        this.vendor = str;
    }

    public String E() {
        return this.cpuSpeed;
    }

    public void D(String str) {
        this.freeSto = str;
    }

    public String D() {
        return this.cpuCoreCnt;
    }

    public void C(String str) {
        this.totalSto = str;
    }

    public String C() {
        return this.cpuModel;
    }

    public void B(String str) {
        this.totalMem = str;
    }

    public String B() {
        return this.pdtName;
    }

    public void A(String str) {
        this.gpuModel = str;
    }

    public String A() {
        return this.lang;
    }
}
