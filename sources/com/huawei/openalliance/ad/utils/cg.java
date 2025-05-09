package com.huawei.openalliance.ad.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Pair;
import com.huawei.openalliance.ad.annotations.SecretField;
import com.huawei.openalliance.ad.ho;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class cg {
    private static final byte[] h = new byte[0];
    private static final byte[] i = new byte[0];
    private static final byte[] j = new byte[0];
    private static cg k;

    /* renamed from: a, reason: collision with root package name */
    private Context f7655a;
    private SharedPreferences b;
    private SharedPreferences c;
    private SharedPreferences d;
    private SharedPreferences e;
    private final byte[] f = new byte[0];
    private final byte[] g = new byte[0];
    private a l;
    private d m;
    private c n;
    private e o;

    public void z(String str) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.countryCode = str;
            a(this.l);
        }
    }

    public String z() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return "";
            }
            return aVar.baro;
        }
    }

    public void y(String str) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.charging = str;
            a(this.l);
        }
    }

    public String y() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return "";
            }
            return aVar.magnet;
        }
    }

    public void x(String str) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.battery = str;
            a(this.l);
        }
    }

    public String x() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return "";
            }
            return aVar.acceler;
        }
    }

    public void w(String str) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.baro = str;
            a(this.l);
        }
    }

    public String w() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return "";
            }
            return aVar.gyro;
        }
    }

    public void v(String str) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.magnet = str;
            a(this.l);
        }
    }

    public String v() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return "";
            }
            return aVar.vendCountry;
        }
    }

    public void u(String str) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.acceler = str;
            a(this.l);
        }
    }

    public String u() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return "";
            }
            return aVar.vendor;
        }
    }

    public void t(String str) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.gyro = str;
            a(this.l);
        }
    }

    public String t() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return "";
            }
            return aVar.freeSto;
        }
    }

    public void s(String str) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.vendCountry = str;
            a(this.l);
        }
    }

    public String s() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return "";
            }
            return aVar.totalSto;
        }
    }

    public void r(String str) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.vendor = str;
            a(this.l);
        }
    }

    public String r() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return "";
            }
            return aVar.totalMem;
        }
    }

    public void q(String str) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.freeSto = str;
            a(this.l);
        }
    }

    public String q() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return "";
            }
            return aVar.cpuSpeed;
        }
    }

    public void p(String str) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.totalSto = str;
            a(this.l);
        }
    }

    public String p() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return "";
            }
            return aVar.cpuCoreCnt;
        }
    }

    public void o(String str) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.totalMem = str;
            a(this.l);
        }
    }

    public String o() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return "";
            }
            return aVar.cpuModel;
        }
    }

    public void n(String str) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.cpuSpeed = str;
            a(this.l);
        }
    }

    public String n() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return "";
            }
            return aVar.pdtName;
        }
    }

    public void m(String str) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.cpuCoreCnt = str;
            a(this.l);
        }
    }

    public String m() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return "";
            }
            return aVar.agCountryCode;
        }
    }

    public void l(String str) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.cpuModel = str;
            a(this.l);
        }
    }

    public String l() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return "";
            }
            return aVar.agVersion;
        }
    }

    public void k(String str) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.pdtName = str;
            a(this.l);
        }
    }

    public String k() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return "";
            }
            return aVar.uuid;
        }
    }

    public void j(String str) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.agCountryCode = str;
            a(this.l);
        }
    }

    public String j() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return "";
            }
            return aVar.deviceMark;
        }
    }

    public void i(boolean z) {
        synchronized (i) {
            aj();
            c cVar = this.n;
            if (cVar == null) {
                return;
            }
            cVar.isTFua = Boolean.valueOf(z);
            a(this.n);
        }
    }

    public void i(String str) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.agVersion = str;
            a(this.l);
        }
    }

    public Pair<String, Boolean> i() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return null;
            }
            if (TextUtils.isEmpty(aVar.oaid) && this.l.isLimitTracking == null) {
                return null;
            }
            return new Pair<>(this.l.oaid, this.l.isLimitTracking);
        }
    }

    public void h(boolean z) {
        synchronized (i) {
            aj();
            c cVar = this.n;
            if (cVar == null) {
                return;
            }
            cVar.isCoppa = Boolean.valueOf(z);
            a(this.n);
        }
    }

    public void h(String str) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.uuid = str;
            a(this.l);
        }
    }

    public String h() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return "";
            }
            return aVar.magicuiVersionName;
        }
    }

    public void g(boolean z) {
        synchronized (i) {
            aj();
            c cVar = this.n;
            if (cVar == null) {
                return;
            }
            cVar.isChildMode = Boolean.valueOf(z);
            a(this.n);
        }
    }

    public void g(String str) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.deviceMark = str;
            a(this.l);
        }
    }

    public String g() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return "";
            }
            return aVar.hosVersionName;
        }
    }

    public void f(boolean z) {
        synchronized (i) {
            aj();
            c cVar = this.n;
            if (cVar == null) {
                return;
            }
            cVar.isWelinkUser = Boolean.valueOf(z);
            a(this.n);
        }
    }

    public void f(String str) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.magicuiVersionName = str;
            a(this.l);
        }
    }

    public void f(Boolean bool) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.isEmulator = bool;
            a(this.l);
        }
    }

    public String f() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return "";
            }
            return aVar.emuiVersionName;
        }
    }

    public void e(boolean z) {
        synchronized (i) {
            aj();
            c cVar = this.n;
            if (cVar == null) {
                return;
            }
            cVar.isHonor6UpPhone = String.valueOf(z);
            a(this.n);
        }
    }

    public void e(String str) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.hosVersionName = str;
            a(this.l);
        }
    }

    public void e(Boolean bool) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.usb = bool;
            a(this.l);
        }
    }

    public String e() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return "";
            }
            return aVar.hmsVersion;
        }
    }

    public void d(boolean z) {
        synchronized (i) {
            aj();
            c cVar = this.n;
            if (cVar == null) {
                return;
            }
            cVar.isHuaweiPhoneNew = String.valueOf(z);
            a(this.n);
        }
    }

    public void d(String str) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.emuiVersionName = str;
            a(this.l);
        }
    }

    public void d(Boolean bool) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.isDebug = bool;
            a(this.l);
        }
    }

    public String d() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return "";
            }
            return aVar.hsfVersion;
        }
    }

    public void c(boolean z) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.isTv = Boolean.valueOf(z);
            a(this.l);
        }
    }

    public void c(String str) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.hmsVersion = str;
            a(this.l);
        }
    }

    public void c(Boolean bool) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.isProxy = bool;
            a(this.l);
        }
    }

    public String c() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return "";
            }
            return aVar.isHuaweiPhone;
        }
    }

    public void b(boolean z) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.baseLocationSwitch = Boolean.valueOf(z);
            a(this.l);
        }
    }

    public void b(List<String> list) {
        synchronized (this.f) {
            ah();
            if (this.l == null) {
                return;
            }
            if (list != null && !list.isEmpty()) {
                this.l.insAppTypes = cz.a(list, ",");
                a(this.l);
            }
            this.l.insAppTypes = "";
            a(this.l);
        }
    }

    public void b(String str) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.hsfVersion = str;
            a(this.l);
        }
    }

    public void b(Integer num) {
        synchronized (i) {
            aj();
            c cVar = this.n;
            if (cVar == null) {
                return;
            }
            cVar.sptImgFormat = num;
            a(this.n);
        }
    }

    public void b(Boolean bool) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.hasAccAndRotate = bool;
            a(this.l);
        }
    }

    public void b(int i2) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.grpIdCode = Integer.valueOf(i2);
            a(this.l);
        }
    }

    public String b() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return "";
            }
            return aVar.useragent;
        }
    }

    public Pair<String, Boolean> ag() {
        synchronized (i) {
            aj();
            if (TextUtils.isEmpty(this.n.honorOaid) || this.n.honorLimitTracking == null) {
                return null;
            }
            return new Pair<>(this.n.honorOaid, this.n.honorLimitTracking);
        }
    }

    public Boolean af() {
        synchronized (i) {
            aj();
            c cVar = this.n;
            boolean z = false;
            if (cVar == null) {
                return false;
            }
            if (cVar.isTFua != null) {
                z = this.n.isTFua.booleanValue();
            }
            return Boolean.valueOf(z);
        }
    }

    public Boolean ae() {
        synchronized (i) {
            aj();
            c cVar = this.n;
            boolean z = false;
            if (cVar == null) {
                return false;
            }
            if (cVar.isCoppa != null) {
                z = this.n.isCoppa.booleanValue();
            }
            return Boolean.valueOf(z);
        }
    }

    public Boolean ad() {
        synchronized (i) {
            aj();
            c cVar = this.n;
            if (cVar == null) {
                return null;
            }
            return cVar.isChildMode;
        }
    }

    public Boolean ac() {
        synchronized (i) {
            aj();
            c cVar = this.n;
            if (cVar == null) {
                return null;
            }
            return cVar.isWelinkUser;
        }
    }

    public Integer ab() {
        synchronized (i) {
            aj();
            c cVar = this.n;
            if (cVar == null) {
                return null;
            }
            if (cVar.sptImgFormat == null) {
                return null;
            }
            return this.n.sptImgFormat;
        }
    }

    public Map<String, String> aa() {
        synchronized (j) {
            ak();
            e eVar = this.o;
            Map<String, String> map = null;
            if (eVar == null) {
                return null;
            }
            Map<String, String> map2 = (Map) be.b(eVar.tag, Map.class, new Class[0]);
            if (!bl.a(map2)) {
                map = map2;
            }
            return map;
        }
    }

    public void a(boolean z) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.isHuaweiPhone = String.valueOf(z);
            a(this.l);
        }
    }

    public void a(Map<String, String> map) {
        synchronized (j) {
            ak();
            e eVar = this.o;
            if (eVar == null) {
                return;
            }
            eVar.tag = be.b(map);
            a(this.o);
        }
    }

    public void a(List<String> list) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.aVideoCodec = list;
            a(this.l);
        }
    }

    public void a(String str, Boolean bool, boolean z) {
        synchronized (i) {
            aj();
            this.n.honorOaid = str;
            this.n.honorLimitTracking = bool;
            if (z) {
                this.l.oaid = str;
                this.l.isLimitTracking = bool;
            }
            a(this.n);
        }
    }

    public void a(String str, Boolean bool) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.oaid = str;
            this.l.isLimitTracking = bool;
            a(this.l);
        }
    }

    public void a(String str) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.useragent = str;
            a(this.l);
        }
    }

    public void a(Integer num) {
        synchronized (i) {
            aj();
            c cVar = this.n;
            if (cVar == null) {
                return;
            }
            cVar.sdkType = num;
            a(this.n);
        }
    }

    public void a(Boolean bool) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.gaidLimit = bool;
            a(this.l);
        }
    }

    public void a(int i2) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.type = Integer.valueOf(i2);
            a(this.l);
        }
    }

    public void a() {
        synchronized (this.f) {
            ah();
        }
        synchronized (this.g) {
            ai();
            aj();
        }
        synchronized (j) {
            ak();
        }
    }

    public Integer Z() {
        synchronized (i) {
            aj();
            c cVar = this.n;
            if (cVar == null) {
                return null;
            }
            return cVar.sdkType;
        }
    }

    public String Y() {
        synchronized (i) {
            aj();
            c cVar = this.n;
            if (cVar == null) {
                return null;
            }
            if (cVar.freeSdcard == null) {
                return null;
            }
            return this.n.freeSdcard;
        }
    }

    public String X() {
        synchronized (i) {
            aj();
            c cVar = this.n;
            if (cVar == null) {
                return null;
            }
            if (cVar.isHonor6UpPhone == null) {
                return null;
            }
            return this.n.isHonor6UpPhone;
        }
    }

    public String W() {
        synchronized (i) {
            aj();
            c cVar = this.n;
            if (cVar == null) {
                return null;
            }
            if (cVar.isHuaweiPhoneNew == null) {
                return null;
            }
            return this.n.isHuaweiPhoneNew;
        }
    }

    public String V() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return "";
            }
            return aVar.storageSize;
        }
    }

    public String U() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return "";
            }
            return aVar.memorySize;
        }
    }

    public String T() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return "";
            }
            return aVar.insAppTypes;
        }
    }

    public int S() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return 8;
            }
            if (aVar.grpIdCode == null) {
                return 8;
            }
            return this.l.grpIdCode.intValue();
        }
    }

    public Boolean R() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return null;
            }
            if (aVar.isEmulator == null) {
                return null;
            }
            return this.l.isEmulator;
        }
    }

    public Boolean Q() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return null;
            }
            if (aVar.usb != null) {
                return this.l.usb;
            }
            return this.l.usb;
        }
    }

    public Boolean P() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return null;
            }
            if (aVar.isDebug != null) {
                return this.l.isDebug;
            }
            return this.l.isDebug;
        }
    }

    public Boolean O() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return null;
            }
            if (aVar.isProxy != null) {
                return this.l.isProxy;
            }
            return this.l.isProxy;
        }
    }

    public List<String> N() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return null;
            }
            return aVar.aVideoCodec;
        }
    }

    public String M() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return "";
            }
            return aVar.agcAaid;
        }
    }

    public Boolean L() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return null;
            }
            return aVar.hasAccAndRotate;
        }
    }

    public Boolean K() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return null;
            }
            return aVar.gaidLimit;
        }
    }

    public String J() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return "";
            }
            return aVar.gaid;
        }
    }

    public void I(String str) {
        synchronized (i) {
            aj();
            c cVar = this.n;
            if (cVar == null) {
                return;
            }
            cVar.freeSdcard = str;
            a(this.n);
        }
    }

    public String I() {
        synchronized (this.g) {
            ai();
            d dVar = this.m;
            if (dVar == null) {
                return "";
            }
            return dVar.groupId;
        }
    }

    public void H(String str) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.storageSize = str;
            a(this.l);
        }
    }

    public String H() {
        String str;
        synchronized (this.g) {
            ai();
            d dVar = this.m;
            str = dVar == null ? null : dVar.hiadUTag;
        }
        return str;
    }

    public void G(String str) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.memorySize = str;
            a(this.l);
        }
    }

    public String G() {
        synchronized (this.g) {
            ai();
            d dVar = this.m;
            if (dVar == null) {
                return "";
            }
            return dVar.androidID;
        }
    }

    public void F(String str) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.agcAaid = str;
            a(this.l);
        }
    }

    public Integer F() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return null;
            }
            if (aVar.type == null) {
                return null;
            }
            return this.l.type;
        }
    }

    public void E(String str) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.gaid = str;
            a(this.l);
        }
    }

    public Boolean E() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return null;
            }
            if (aVar.isTv == null) {
                return null;
            }
            return this.l.isTv;
        }
    }

    public void D(String str) {
        synchronized (this.g) {
            ai();
            d dVar = this.m;
            if (dVar == null) {
                return;
            }
            dVar.groupId = str;
            a(this.m);
        }
    }

    public String D() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return "";
            }
            return aVar.brandCust;
        }
    }

    public boolean C() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return false;
            }
            if (aVar.baseLocationSwitch == null) {
                return false;
            }
            return this.l.baseLocationSwitch.booleanValue();
        }
    }

    public void C(String str) {
        synchronized (this.g) {
            ai();
            d dVar = this.m;
            if (dVar == null) {
                return;
            }
            dVar.hiadUTag = str;
            a(this.m);
        }
    }

    public void B(String str) {
        synchronized (this.g) {
            ai();
            d dVar = this.m;
            if (dVar == null) {
                return;
            }
            dVar.androidID = str;
            a(this.m);
        }
    }

    public String B() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return "";
            }
            return aVar.charging;
        }
    }

    public void A(String str) {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return;
            }
            aVar.brandCust = str;
            a(this.l);
        }
    }

    public String A() {
        synchronized (this.f) {
            ah();
            a aVar = this.l;
            if (aVar == null) {
                return "";
            }
            return aVar.battery;
        }
    }

    private void ak() {
        if (this.o == null) {
            String string = this.e.getString("cache_data", null);
            e eVar = TextUtils.isEmpty(string) ? null : (e) be.b(this.f7655a, string, e.class, new Class[0]);
            if (eVar == null) {
                eVar = new e();
            }
            this.o = eVar;
        }
    }

    private void aj() {
        c cVar;
        if (this.n == null) {
            SharedPreferences sharedPreferences = this.d;
            if (sharedPreferences == null) {
                cVar = new c();
            } else {
                String string = sharedPreferences.getString("cache_data", null);
                cVar = (string == null || string.length() <= 0) ? null : (c) be.b(this.f7655a, string, c.class, new Class[0]);
                if (cVar == null) {
                    cVar = new c();
                }
            }
            this.n = cVar;
        }
    }

    private void ai() {
        SharedPreferences sharedPreferences;
        if (this.m != null || (sharedPreferences = this.c) == null) {
            return;
        }
        String string = sharedPreferences.getString("cache_data", null);
        d dVar = TextUtils.isEmpty(string) ? null : (d) be.b(this.f7655a, string, d.class, new Class[0]);
        if (dVar == null) {
            dVar = new d();
        }
        this.m = dVar;
    }

    private void ah() {
        SharedPreferences sharedPreferences;
        if (this.l != null || (sharedPreferences = this.b) == null) {
            return;
        }
        a aVar = null;
        String string = sharedPreferences.getString("cache_data", null);
        if (string != null && string.length() > 0) {
            aVar = (a) be.b(this.f7655a, string, a.class, new Class[0]);
        }
        if (aVar == null) {
            aVar = new a();
        }
        this.l = aVar;
    }

    private void a(e eVar) {
        a(eVar, this.e);
    }

    private void a(d dVar) {
        a(dVar, this.c);
    }

    private void a(c cVar) {
        a(cVar, this.d);
    }

    private void a(b bVar, final SharedPreferences sharedPreferences) {
        if (bVar == null || sharedPreferences == null) {
            return;
        }
        final b clone = bVar.clone();
        m.f(new Runnable() { // from class: com.huawei.openalliance.ad.utils.cg.1
            @Override // java.lang.Runnable
            public void run() {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString("cache_data", be.b(cg.this.f7655a, clone));
                edit.commit();
            }
        });
    }

    private void a(a aVar) {
        a(aVar, this.b);
    }

    static class b implements Cloneable {
        @Override // 
        /* renamed from: b */
        public b clone() {
            return new b();
        }

        private b() {
        }
    }

    public static cg a(Context context) {
        cg cgVar;
        synchronized (h) {
            if (k == null) {
                k = new cg(context);
            }
            cgVar = k;
        }
        return cgVar;
    }

    public static final class a extends b {
        List<String> aVideoCodec;
        String acceler;
        String agCountryCode;
        String agVersion;
        String agcAaid;
        String arEngineVersion;
        String baro;
        Boolean baseLocationSwitch;
        String battery;
        String brandCust;
        String charging;
        String colorOSVersionName;
        String countryCode;
        String cpuCoreCnt;
        String cpuModel;
        String cpuSpeed;
        String deviceMark;
        Integer emuiSdkInt;
        String emuiVersionName;
        String freeSto;
        String funtouchOSVersionName;
        String gaid;
        Boolean gaidLimit;
        String gpuModel;
        Integer grpIdCode;
        String gyro;
        Boolean hasAccAndRotate;
        String hmsVersion;
        String hosVersionName;
        String hsfVersion;
        String insAppTypes;
        Boolean isDebug;
        Boolean isEmulator;
        String isHuaweiPhone;
        Boolean isLimitTracking;
        String isMIPhone;
        String isOPPOPhone;
        Boolean isProxy;
        Boolean isTv;
        String isVIVOPhone;
        String magicuiVersionName;
        String magnet;
        String memorySize;
        String miuiVersionName;
        String oaid;
        String pdtName;
        String routerCountry;
        String storageSize;
        String totalMem;
        String totalSto;
        Integer type;
        Boolean usb;
        String useragent;
        String uuid;
        String vendCountry;
        String vendor;
        String wifiName;
        String xrKitAppVersion;

        @Override // com.huawei.openalliance.ad.utils.cg.b
        /* renamed from: a, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public a clone() {
            a aVar = new a();
            aVar.useragent = this.useragent;
            aVar.isHuaweiPhone = this.isHuaweiPhone;
            aVar.isMIPhone = this.isMIPhone;
            aVar.isVIVOPhone = this.isVIVOPhone;
            aVar.isOPPOPhone = this.isOPPOPhone;
            aVar.hsfVersion = this.hsfVersion;
            aVar.emuiVersionName = this.emuiVersionName;
            aVar.hosVersionName = this.hosVersionName;
            aVar.magicuiVersionName = this.magicuiVersionName;
            aVar.miuiVersionName = this.miuiVersionName;
            aVar.funtouchOSVersionName = this.funtouchOSVersionName;
            aVar.colorOSVersionName = this.colorOSVersionName;
            aVar.hmsVersion = this.hmsVersion;
            aVar.oaid = this.oaid;
            aVar.isLimitTracking = this.isLimitTracking;
            aVar.deviceMark = this.deviceMark;
            aVar.uuid = this.uuid;
            aVar.agVersion = this.agVersion;
            aVar.agCountryCode = this.agCountryCode;
            aVar.wifiName = this.wifiName;
            aVar.pdtName = this.pdtName;
            aVar.cpuModel = this.cpuModel;
            aVar.cpuCoreCnt = this.cpuCoreCnt;
            aVar.cpuSpeed = this.cpuSpeed;
            aVar.gpuModel = this.gpuModel;
            aVar.totalMem = this.totalMem;
            aVar.totalSto = this.totalSto;
            aVar.freeSto = this.freeSto;
            aVar.vendor = this.vendor;
            aVar.vendCountry = this.vendCountry;
            aVar.routerCountry = this.routerCountry;
            aVar.gyro = this.gyro;
            aVar.acceler = this.acceler;
            aVar.magnet = this.magnet;
            aVar.baro = this.baro;
            aVar.battery = this.battery;
            aVar.charging = this.charging;
            aVar.baseLocationSwitch = this.baseLocationSwitch;
            aVar.countryCode = this.countryCode;
            aVar.emuiSdkInt = this.emuiSdkInt;
            aVar.arEngineVersion = this.arEngineVersion;
            aVar.xrKitAppVersion = this.xrKitAppVersion;
            aVar.brandCust = this.brandCust;
            aVar.isTv = this.isTv;
            aVar.type = this.type;
            aVar.gaid = this.gaid;
            aVar.gaidLimit = this.gaidLimit;
            aVar.hasAccAndRotate = this.hasAccAndRotate;
            aVar.agcAaid = this.agcAaid;
            aVar.aVideoCodec = this.aVideoCodec;
            aVar.isProxy = this.isProxy;
            aVar.isDebug = this.isDebug;
            aVar.usb = this.usb;
            aVar.isEmulator = this.isEmulator;
            aVar.grpIdCode = this.grpIdCode;
            aVar.insAppTypes = this.insAppTypes;
            aVar.storageSize = this.storageSize;
            aVar.memorySize = this.memorySize;
            return aVar;
        }

        public a() {
            super();
        }
    }

    public static final class c extends b {
        String freeSdcard;
        Boolean honorLimitTracking;
        String honorOaid;
        Boolean isChildMode;
        Boolean isCoppa;
        String isHonor6UpPhone;
        String isHuaweiPhoneNew;
        Boolean isTFua;
        Boolean isWelinkUser;
        Integer sdkType;
        Integer sptImgFormat;

        @Override // com.huawei.openalliance.ad.utils.cg.b
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public c clone() {
            c cVar = new c();
            cVar.isHuaweiPhoneNew = this.isHuaweiPhoneNew;
            cVar.isHonor6UpPhone = this.isHonor6UpPhone;
            cVar.sdkType = this.sdkType;
            cVar.freeSdcard = this.freeSdcard;
            cVar.sptImgFormat = this.sptImgFormat;
            cVar.isWelinkUser = this.isWelinkUser;
            cVar.isChildMode = this.isChildMode;
            cVar.isCoppa = this.isCoppa;
            cVar.isTFua = this.isTFua;
            cVar.honorOaid = this.honorOaid;
            cVar.honorLimitTracking = this.honorLimitTracking;
            return cVar;
        }

        public c() {
            super();
        }
    }

    public static final class d extends b {

        @SecretField
        String androidID;

        @SecretField
        String groupId;

        @SecretField
        String hiadUTag;

        @Override // com.huawei.openalliance.ad.utils.cg.b
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public d clone() {
            d dVar = new d();
            dVar.androidID = this.androidID;
            dVar.groupId = this.groupId;
            dVar.hiadUTag = this.hiadUTag;
            return dVar;
        }

        public d() {
            super();
        }
    }

    public static final class e extends b {
        String tag;

        @Override // com.huawei.openalliance.ad.utils.cg.b
        /* renamed from: b */
        public b clone() {
            e eVar = new e();
            eVar.tag = this.tag;
            return eVar;
        }

        public e() {
            super();
        }
    }

    private cg(Context context) {
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        try {
            this.b = context.getSharedPreferences("hiad_sp_properties_cache", 0);
            this.c = context.getSharedPreferences("hiad_sp_sec_properties_cache", 0);
            this.d = context.getSharedPreferences("hiad_sp_nor_properties_cache", 0);
            this.e = context.getSharedPreferences("hiad_sp_tag_cache", 0);
            this.f7655a = context.getApplicationContext();
        } catch (Throwable th) {
            ho.c("PropertiesCache", "get SharedPreference error: %s", th.getClass().getSimpleName());
        }
    }
}
