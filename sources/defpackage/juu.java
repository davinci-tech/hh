package defpackage;

import android.os.Process;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevicemgr.R$string;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes.dex */
public class juu {
    private static final HashMap<Integer, Integer> b;
    private static final HashMap<Integer, String> c;

    /* renamed from: a, reason: collision with root package name */
    private static ArrayList<jux> f14103a = new ArrayList<>(0);
    private static HashMap<Integer, ArrayList<String>> e = new HashMap<>(16);
    private static final Object d = new Object();
    private static boolean i = true;

    private static boolean h(int i2) {
        return i2 == 23 || i2 == 24 || i2 == 36 || i2 == 37 || i2 == 63;
    }

    static {
        int i2 = 16;
        c = new HashMap(i2) { // from class: juu.4
            private static final long serialVersionUID = -8551301047341727270L;

            {
                put(0, "B1");
                put(1, "B2");
                put(4, "N1");
                put(7, "B3");
                put(14, "GRUS");
                put(11, "R1");
                put(5, "B0");
                put(8, "S1");
                put(12, "A2");
                put(13, "NYX");
                put(15, "ERIS");
                put(2, "K1");
                put(-2, "AF500");
                put(3, "W1");
                put(10, "W2");
                put(16, "JANUS");
                put(18, "CRIUS");
                put(19, "TERRA");
                put(20, "TALOS");
                put(21, "FORTUNA");
                put(23, "AW70B29");
                put(24, "AW70B19");
                put(32, "CASSINI");
                put(34, "LATONA");
                put(36, "AW70B39HW");
                put(37, "AW70B39HN");
                put(44, "ANDES_B19");
                put(45, "ANDES_B29");
                put(54, "Hagrid-B19");
                put(599, "Hagrid2021-B19");
                put(263, "Lupin-B19HN");
                put(70, "Herm-B19");
            }
        };
        b = new HashMap(i2) { // from class: juu.3
            private static final long serialVersionUID = 1982860079700417868L;

            {
                put(35, 89);
                put(64, 131);
                put(65, 137);
                put(61, 124);
                put(60, 122);
                put(58, 115);
            }
        };
        e();
    }

    public static void e() {
        b();
        LogUtil.a("DeviceInfoMgr", "enter init start");
        if (!i) {
            LogUtil.a("DeviceInfoMgr", "isResetInit is false");
            return;
        }
        i = false;
        e.clear();
        synchronized (d) {
            f14103a.clear();
        }
        q();
        v();
        n();
        s();
        l();
        p();
        h();
        i();
        k();
        r();
        j();
        m();
        u();
        w();
        o();
        x();
        t();
        g();
        f();
        ThreadPoolManager.d().d("DeviceInfoMgr", new Runnable() { // from class: juu.5
            @Override // java.lang.Runnable
            public void run() {
                ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).clearPluginInfoList();
                juu.c();
            }
        });
    }

    public static void f(int i2) {
        boolean z;
        b();
        final String d2 = d(i2);
        synchronized (d) {
            Iterator<jux> it = f14103a.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = true;
                    break;
                } else if (it.next().f().equals(d2)) {
                    z = false;
                    break;
                }
            }
        }
        LogUtil.a("DeviceInfoMgr", "updateCurrentDevice isNeedUpdate:", Boolean.valueOf(z));
        if (z) {
            ThreadPoolManager.d().d("DeviceInfoMgr", new Runnable() { // from class: juu.2
                @Override // java.lang.Runnable
                public void run() {
                    juu.e(((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(d2));
                }
            });
        }
    }

    public static String d(int i2) {
        b();
        String str = "";
        for (Map.Entry<String, Integer> entry : cup.b().entrySet()) {
            if (entry != null && entry.getKey() != null) {
                str = b(entry, i2);
            }
            if (!TextUtils.isEmpty(str)) {
                break;
            }
        }
        return str;
    }

    public static HashMap<Integer, String> a() {
        b();
        return c;
    }

    private static String b(Map.Entry<String, Integer> entry, int i2) {
        String key = entry.getKey();
        return (cup.b().get(key) != null ? cup.b().get(key).intValue() : 0) == i2 ? key : "";
    }

    private static void g() {
        jux juxVar = new jux();
        juxVar.c(44);
        juxVar.b(true);
        juxVar.e(92);
        synchronized (d) {
            f14103a.add(juxVar);
        }
    }

    private static void f() {
        jux juxVar = new jux();
        juxVar.c(45);
        juxVar.e(93);
        juxVar.b(true);
        synchronized (d) {
            f14103a.add(juxVar);
        }
    }

    private static void x() {
        jux juxVar = new jux();
        juxVar.b(BaseApplication.getContext().getString(R$string.IDS_app_display_name_talos));
        juxVar.c(20);
        juxVar.e(79);
        ArrayList<String> arrayList = new ArrayList<>(0);
        arrayList.add("talos-".toUpperCase(Locale.ENGLISH));
        arrayList.add("honor Watch-".toUpperCase(Locale.ENGLISH));
        e.put(20, arrayList);
        juxVar.b(false);
        Object obj = d;
        synchronized (obj) {
            f14103a.add(juxVar);
        }
        jux juxVar2 = new jux();
        juxVar2.b(BaseApplication.getContext().getString(R$string.IDS_app_display_name_fortuna));
        juxVar2.c(21);
        juxVar2.e(80);
        ArrayList<String> arrayList2 = new ArrayList<>(0);
        arrayList2.add("fortuna-".toUpperCase(Locale.ENGLISH));
        arrayList2.add("HUAWEI WATCH GT-".toUpperCase(Locale.ENGLISH));
        e.put(21, arrayList2);
        juxVar2.b(false);
        synchronized (obj) {
            f14103a.add(juxVar2);
        }
    }

    private static void o() {
        jux juxVar = new jux();
        juxVar.b(BaseApplication.getContext().getString(R$string.IDS_app_display_name_crius));
        juxVar.c(18);
        juxVar.e(77);
        ArrayList<String> arrayList = new ArrayList<>(0);
        arrayList.add("crius-".toUpperCase(Locale.ENGLISH));
        arrayList.add("Honor Band 4-".toUpperCase(Locale.ENGLISH));
        e.put(18, arrayList);
        juxVar.b(true);
        Object obj = d;
        synchronized (obj) {
            f14103a.add(juxVar);
        }
        jux juxVar2 = new jux();
        juxVar2.b(BaseApplication.getContext().getString(R$string.IDS_app_display_name_terra));
        juxVar2.c(19);
        juxVar2.e(78);
        ArrayList<String> arrayList2 = new ArrayList<>(0);
        arrayList2.add("terra-".toUpperCase(Locale.ENGLISH));
        arrayList2.add("HUAWEI Band 3 Pro-".toUpperCase(Locale.ENGLISH));
        e.put(19, arrayList2);
        juxVar2.b(true);
        synchronized (obj) {
            f14103a.add(juxVar2);
        }
    }

    private static void w() {
        jux juxVar = new jux();
        juxVar.b(BaseApplication.getContext().getString(R$string.IDS_huawei_r1_pro_content));
        juxVar.c(11);
        juxVar.a(R.mipmap._2131821240_res_0x7f1102b8);
        juxVar.e(43);
        ArrayList<String> arrayList = new ArrayList<>(0);
        arrayList.add("HUAWEI CM-R1P".toUpperCase(Locale.ENGLISH));
        arrayList.add("HUAWEI AM-R1".toUpperCase(Locale.ENGLISH));
        e.put(11, arrayList);
        if (Utils.g()) {
            return;
        }
        synchronized (d) {
            f14103a.add(juxVar);
        }
    }

    private static void t() {
        jux juxVar = new jux();
        juxVar.b(BaseApplication.getContext().getString(R$string.IDS_device_latona_name));
        juxVar.c(34);
        juxVar.a(R.mipmap._2131820674_res_0x7f110082);
        juxVar.b(false);
        juxVar.e(88);
        ArrayList<String> arrayList = new ArrayList<>(0);
        arrayList.add("HUAWEI LTN-".toUpperCase(Locale.ENGLISH));
        arrayList.add("HUAWEI DAN-".toUpperCase(Locale.ENGLISH));
        arrayList.add("HUAWEI WATCH GT 2-".toUpperCase(Locale.ENGLISH));
        e.put(34, arrayList);
        synchronized (d) {
            f14103a.add(juxVar);
        }
    }

    private static void m() {
        jux juxVar = new jux();
        juxVar.b(BaseApplication.getContext().getString(R$string.IDS_app_display_name_gemini));
        juxVar.c(7);
        juxVar.a(R.mipmap._2131821436_res_0x7f11037c);
        juxVar.e(39);
        ArrayList<String> arrayList = new ArrayList<>(0);
        arrayList.add("HUAWEI GE".toUpperCase(Locale.ENGLISH));
        arrayList.add("HUAWEI B3-".toUpperCase(Locale.ENGLISH));
        e.put(7, arrayList);
        synchronized (d) {
            f14103a.add(juxVar);
        }
    }

    private static void u() {
        jux juxVar = new jux();
        juxVar.b(BaseApplication.getContext().getString(R$string.IDS_huawei_r1_content));
        juxVar.c(11);
        juxVar.a(R.mipmap._2131821239_res_0x7f1102b7);
        juxVar.e(43);
        ArrayList<String> arrayList = new ArrayList<>(0);
        arrayList.add("HUAWEI AM-R1".toUpperCase(Locale.ENGLISH));
        e.put(11, arrayList);
        if (Utils.g()) {
            return;
        }
        synchronized (d) {
            f14103a.add(juxVar);
        }
    }

    private static void k() {
        jux juxVar = new jux();
        juxVar.b(BaseApplication.getContext().getString(R$string.IDS_app_display_name_honor_aw));
        juxVar.c(24);
        juxVar.e(83);
        ArrayList<String> arrayList = new ArrayList<>(0);
        arrayList.add("HONOR AW70");
        arrayList.add("honor Band 4R-");
        e.put(24, arrayList);
        juxVar.b(true);
        synchronized (d) {
            f14103a.add(juxVar);
        }
    }

    private static void r() {
        jux juxVar = new jux();
        juxVar.b(BaseApplication.getContext().getString(R$string.IDS_app_display_name_honor_aw_pro));
        juxVar.c(37);
        juxVar.e(91);
        ArrayList<String> arrayList = new ArrayList<>(0);
        arrayList.add("AW70B39HN");
        e.put(37, arrayList);
        juxVar.b(true);
        synchronized (d) {
            f14103a.add(juxVar);
        }
    }

    private static void j() {
        jux juxVar = new jux();
        juxVar.b(nsn.d(BaseApplication.getContext()));
        juxVar.c(12);
        juxVar.a(R.mipmap._2131821435_res_0x7f11037b);
        juxVar.e(44);
        ArrayList<String> arrayList = new ArrayList<>(0);
        arrayList.add("HUAWEI band A2".toUpperCase(Locale.ENGLISH));
        arrayList.add("honor band A2".toUpperCase(Locale.ENGLISH));
        arrayList.add("AW61".toUpperCase(Locale.ENGLISH));
        arrayList.add("HUAWEI Color Band A2".toUpperCase(Locale.ENGLISH));
        e.put(12, arrayList);
        synchronized (d) {
            f14103a.add(juxVar);
        }
    }

    private static void p() {
        jux juxVar = new jux();
        juxVar.b(BaseApplication.getContext().getString(R$string.IDS_app_display_name_nys));
        juxVar.c(13);
        juxVar.a(R.mipmap._2131821434_res_0x7f11037a);
        juxVar.b(true);
        juxVar.e(42);
        ArrayList<String> arrayList = new ArrayList<>(0);
        arrayList.add("HUAWEI NYX".toUpperCase(Locale.ENGLISH));
        arrayList.add("HONOR NYX".toUpperCase(Locale.ENGLISH));
        arrayList.add("Honor Band 3-".toUpperCase(Locale.ENGLISH));
        e.put(13, arrayList);
        synchronized (d) {
            f14103a.add(juxVar);
        }
    }

    private static void h() {
        jux juxVar = new jux();
        juxVar.b(BaseApplication.getContext().getString(R$string.IDS_app_display_name_aw));
        juxVar.c(23);
        juxVar.e(81);
        ArrayList<String> arrayList = new ArrayList<>(0);
        arrayList.add("HUAWEI Band 3e-");
        e.put(23, arrayList);
        juxVar.b(true);
        synchronized (d) {
            f14103a.add(juxVar);
        }
    }

    private static void i() {
        jux juxVar = new jux();
        juxVar.b(BaseApplication.getContext().getString(R$string.IDS_app_display_name_aw_pro));
        juxVar.c(36);
        juxVar.e(90);
        ArrayList<String> arrayList = new ArrayList<>(0);
        arrayList.add("AW70B39HW");
        arrayList.add("HUAWEI Band 4e-");
        e.put(36, arrayList);
        juxVar.b(true);
        synchronized (d) {
            f14103a.add(juxVar);
        }
    }

    private static void n() {
        jux juxVar = new jux();
        juxVar.b(BaseApplication.getContext().getString(R$string.IDS_app_display_name_grus));
        juxVar.c(14);
        juxVar.a(R.mipmap._2131821437_res_0x7f11037d);
        juxVar.e(45);
        ArrayList<String> arrayList = new ArrayList<>(0);
        arrayList.add("HUAWEI GRUS".toUpperCase(Locale.ENGLISH));
        arrayList.add("HUAWEI B3 Lite-".toUpperCase(Locale.ENGLISH));
        e.put(14, arrayList);
        synchronized (d) {
            f14103a.add(juxVar);
        }
    }

    private static void l() {
        jux juxVar = new jux();
        if (Utils.o()) {
            if (LanguageUtil.j(BaseApplication.getContext())) {
                juxVar.b(BaseApplication.getContext().getString(R$string.IDS_app_display_name_eris));
            } else {
                juxVar.b(BaseApplication.getContext().getString(R$string.IDS_app_display_name_eris_new));
            }
        } else {
            juxVar.b(BaseApplication.getContext().getString(R$string.IDS_app_display_name_eris));
        }
        juxVar.c(15);
        juxVar.a(R.mipmap._2131821438_res_0x7f11037e);
        juxVar.e(47);
        ArrayList<String> arrayList = new ArrayList<>(0);
        arrayList.add("Huawei Band 2-".toUpperCase(Locale.ENGLISH));
        e.put(15, arrayList);
        synchronized (d) {
            f14103a.add(juxVar);
        }
    }

    private static void s() {
        jux juxVar = new jux();
        juxVar.b(BaseApplication.getContext().getString(R$string.IDS_app_display_name_janus));
        juxVar.c(16);
        juxVar.b(true);
        juxVar.e(74);
        ArrayList<String> arrayList = new ArrayList<>(0);
        arrayList.add("HUAWEI JNS-".toUpperCase(Locale.ENGLISH));
        arrayList.add("HUAWEI B5-".toUpperCase(Locale.ENGLISH));
        e.put(16, arrayList);
        synchronized (d) {
            f14103a.add(juxVar);
        }
    }

    private static void v() {
        jux juxVar = new jux();
        juxVar.b(nsn.t(BaseApplication.getContext()));
        juxVar.c(8);
        juxVar.a(R.mipmap._2131821452_res_0x7f11038c);
        juxVar.e(41);
        ArrayList<String> arrayList = new ArrayList<>(0);
        arrayList.add("metis".toUpperCase(Locale.ENGLISH));
        arrayList.add("honor watch S1".toUpperCase(Locale.ENGLISH));
        arrayList.add("HUAWEI FIT".toUpperCase(Locale.ENGLISH));
        e.put(8, arrayList);
        synchronized (d) {
            f14103a.add(juxVar);
        }
    }

    private static void q() {
        jux juxVar = new jux();
        juxVar.b(BaseApplication.getContext().getString(R$string.IDS_app_display_name_leo));
        juxVar.c(10);
        juxVar.a(R.mipmap._2131821453_res_0x7f11038d);
        juxVar.e(46);
        juxVar.b(false);
        synchronized (d) {
            f14103a.add(juxVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c() {
        for (Map.Entry<String, Integer> entry : cup.b().entrySet()) {
            if (entry != null && entry.getKey() != null) {
                String key = entry.getKey();
                LogUtil.a("DeviceInfoMgr", "tempUuid is exist:", key);
                e(((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(key));
            }
        }
    }

    public static void e(cvc cvcVar) {
        b();
        LogUtil.a("DeviceInfoMgr", " enter loadDeviceInfoFromJson.");
        if (cvcVar == null) {
            LogUtil.h("DeviceInfoMgr", "null is info");
            return;
        }
        if (cvcVar.e() == null) {
            LogUtil.h("DeviceInfoMgr", " null is info.fetchPluginUuid()");
            return;
        }
        synchronized (d) {
            if (f14103a == null) {
                LogUtil.h("DeviceInfoMgr", "allDevices is null");
            } else {
                b(cvcVar);
            }
        }
    }

    private static void b(cvc cvcVar) {
        jux c2;
        HashMap<String, Integer> b2 = cup.b();
        if (b2.containsKey(cvcVar.e())) {
            int intValue = b2.get(cvcVar.e()).intValue();
            c2 = a(intValue);
            LogUtil.a("DeviceInfoMgr", "setLoadDeviceInfo fetchPluginUuid: ", cvcVar.e());
            if (c2 != null && c2.d() != intValue) {
                LogUtil.a("DeviceInfoMgr", "setLoadDeviceInfo getDeviceType(): ", Integer.valueOf(c2.d()));
                synchronized (d) {
                    LogUtil.a("DeviceInfoMgr", "setLoadDeviceInfo local add");
                    f14103a.add(c2);
                }
            }
        } else {
            LogUtil.a("DeviceInfoMgr", "setLoadDeviceInfo cloud");
            c2 = c(cvcVar);
        }
        b(cvcVar, c2, cup.b());
    }

    private static jux c(cvc cvcVar) {
        jux d2 = d(cvcVar.e());
        if (d2 == null && cvcVar.f() != null) {
            int af = cvcVar.f().af();
            LogUtil.a("DeviceInfoMgr", "setLoadDeviceInfo cloud deviceType:", Integer.valueOf(af));
            if (af != -1) {
                d2 = a(af);
            }
            Object[] objArr = new Object[2];
            objArr[0] = "setLoadDeviceInfo cloud getDeviceType: ";
            objArr[1] = d2 == null ? Constants.NULL : Integer.valueOf(d2.d());
            LogUtil.a("DeviceInfoMgr", objArr);
            if (d2 != null && d2.d() != af) {
                d2 = null;
            }
        }
        if (d2 == null) {
            d2 = new jux();
            synchronized (d) {
                LogUtil.a("DeviceInfoMgr", "setLoadDeviceInfo cloud add:");
                f14103a.add(d2);
            }
        }
        return d2;
    }

    private static void b(cvc cvcVar, jux juxVar, HashMap<String, Integer> hashMap) {
        LogUtil.a("DeviceInfoMgr", "enter loadPluginDeviceInfo");
        if (cvcVar.e() != null) {
            juxVar.e(cvcVar.e());
        }
        cvj f = cvcVar.f();
        if (f == null) {
            return;
        }
        String ae = f.ae();
        if (!"".equals(ae)) {
            juxVar.b(ae);
            LogUtil.a("DeviceInfoMgr", " deviceName : ", juxVar.c());
        }
        String bb = f.bb();
        if (!"".equals(bb)) {
            juxVar.d(bb);
            LogUtil.a("DeviceInfoMgr", " npsName : ", juxVar.g());
        }
        int af = f.af();
        if (af != -1) {
            juxVar.c(af);
            LogUtil.a("DeviceInfoMgr", " deviceType : ", Integer.valueOf(juxVar.d()));
        }
        String f2 = f.f();
        if (!TextUtils.isEmpty(f2)) {
            try {
                juxVar.b(Integer.parseInt(f2));
                LogUtil.a("DeviceInfoMgr", " manu type : ", Integer.valueOf(juxVar.i()));
            } catch (NumberFormatException e2) {
                LogUtil.b("DeviceInfoMgr", "brand NumberFormatException:", e2.getMessage());
            }
        }
        int ah = f.ah();
        if (ah != -1 && ah != 1) {
            juxVar.b(false);
        }
        int ah2 = f.ah();
        if (ah2 != -1) {
            juxVar.d(ah2);
            LogUtil.a("DeviceInfoMgr", "deviceCategory : ", Integer.valueOf(juxVar.a()));
        }
        int au = f.au();
        if (au != -1) {
            juxVar.e(au);
            LogUtil.a("DeviceInfoMgr", " hiType : ", Integer.valueOf(juxVar.b()));
        }
        d(cvcVar, hashMap, f);
    }

    private static void d(cvc cvcVar, HashMap<String, Integer> hashMap, cvj cvjVar) {
        int af;
        if (hashMap.containsKey(cvcVar.e())) {
            af = hashMap.get(cvcVar.e()).intValue();
        } else {
            af = cvjVar.af();
        }
        if (af != -1) {
            ArrayList<String> j = cvjVar.j();
            if (j == null || j.size() <= 0) {
                LogUtil.h("DeviceInfoMgr", " bluetooth Names is null or size is 0");
                return;
            }
            ArrayList<String> arrayList = new ArrayList<>(16);
            Iterator<String> it = j.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().toUpperCase(Locale.ENGLISH));
            }
            HashMap<Integer, ArrayList<String>> hashMap2 = e;
            if (hashMap2 != null) {
                hashMap2.put(Integer.valueOf(af), arrayList);
                LogUtil.a("DeviceInfoMgr", " bluetooth Names: ", e.get(Integer.valueOf(af)));
            }
        }
    }

    public static jux a(int i2) {
        b();
        jux juxVar = new jux();
        synchronized (d) {
            Iterator<jux> it = f14103a.iterator();
            while (it.hasNext()) {
                jux next = it.next();
                if (next.d() == i2) {
                    LogUtil.a("DeviceInfoMgr", "result.getDeviceType(): ", Integer.valueOf(next.d()));
                    return next;
                }
            }
            return juxVar;
        }
    }

    public static boolean j(int i2) {
        b();
        return a(i2).e();
    }

    public static int b(int i2) {
        b();
        return a(i2).a();
    }

    public static jux d(String str) {
        b();
        if (str == null || "".equals(str)) {
            return null;
        }
        synchronized (d) {
            Iterator<jux> it = f14103a.iterator();
            while (it.hasNext()) {
                jux next = it.next();
                if (str.equals(next.f())) {
                    return next;
                }
            }
            return null;
        }
    }

    public static String g(int i2) {
        b();
        synchronized (d) {
            Iterator<jux> it = f14103a.iterator();
            while (it.hasNext()) {
                jux next = it.next();
                if (i2 == next.d()) {
                    return next.f();
                }
            }
            return "";
        }
    }

    public static ArrayList<String> e(int i2) {
        b();
        return e.containsKey(Integer.valueOf(i2)) ? e.get(Integer.valueOf(i2)) : new ArrayList<>(0);
    }

    private static void b() {
        if (CompileParameterUtil.a("IS_RELEASE_VERSION")) {
            return;
        }
        String d2 = CommonUtil.d(Process.myPid());
        if (TextUtils.isEmpty(d2) || d2.endsWith(":PhoneService")) {
            return;
        }
        throw new RuntimeException("DeviceInfoMgr not init in process." + d2);
    }

    public static int c(int i2) {
        b();
        Integer num = b.get(Integer.valueOf(i2));
        if (num == null) {
            LogUtil.h("DeviceInfoMgr", "getHiDeviceType hiDeviceType is null.");
            return 0;
        }
        return num.intValue();
    }

    public static boolean i(int i2) {
        b();
        return i2 == 7 || i2 == 8 || k(i2) || i2 >= 32;
    }

    private static boolean k(int i2) {
        return i2 == 13 || i2 == 14 || o(i2);
    }

    private static boolean o(int i2) {
        return i2 == 15 || i2 == 16 || n(i2);
    }

    private static boolean n(int i2) {
        return i2 == 18 || i2 == 19 || m(i2);
    }

    private static boolean m(int i2) {
        return i2 == 20 || i2 == 21 || i2 == 10 || h(i2);
    }
}
