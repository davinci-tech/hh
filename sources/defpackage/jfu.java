package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.manager.util.Consts;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.datatypes.HealthSupportModel;
import com.huawei.hwdevicemgr.R$string;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.MagicBuild;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingFormatArgumentException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class jfu {

    /* renamed from: a, reason: collision with root package name */
    private static final HashMap<Integer, String> f13798a;
    private static volatile HashMap<Integer, Integer> ag;
    private static final HashMap<Integer, String> e;
    private static final int[] c = {60, 35, 18, 64, 65};
    private static cuw x = new cuw();
    private static cuw y = new cuw();
    private static cuw w = new cuw();
    private static cuw aa = new cuw();
    private static cuw q = new cuw();
    private static cuw s = new cuw();
    private static cuw o = new cuw();
    private static cuw u = new cuw();
    private static cuw f = new cuw();
    private static cuw k = new cuw();
    private static cuw t = new cuw();
    private static cuw p = new cuw();
    private static cuw h = new cuw();
    private static cuw l = new cuw();
    private static cuw r = new cuw();
    private static cuw z = new cuw();
    private static cuw ad = new cuw();
    private static cuw j = new cuw();
    private static cuw ab = new cuw();
    private static cuw n = new cuw();
    private static cuw m = new cuw();
    private static cuw ac = new cuw();
    private static cuw i = new cuw();
    private static cuw g = new cuw();
    private static ArrayList<cuw> b = new ArrayList<>(0);
    private static Map<Integer, ArrayList<String>> v = new ConcurrentHashMap(16);
    private static final Object d = new Object();
    private static boolean af = true;

    static {
        int i2 = 16;
        e = new HashMap(i2) { // from class: jfu.1
            private static final long serialVersionUID = -7637626950322857040L;

            {
                put(32, "CASSINI");
            }
        };
        f13798a = new HashMap(i2) { // from class: jfu.2
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
        ag = new HashMap(i2) { // from class: jfu.4
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
        f();
    }

    public static void n() {
        af = true;
        f();
    }

    public static void f() {
        m();
        if (!af) {
            LogUtil.a("DeviceInfoManager", "sResetInit is false");
            return;
        }
        af = false;
        LogUtil.a("DeviceInfoManager", "enter init start");
        String language = BaseApplication.getContext().getResources().getConfiguration().locale.getLanguage();
        LogUtil.a("DeviceInfoManager", "enter init language : ", language);
        v.clear();
        synchronized (d) {
            b.clear();
        }
        try {
            aa();
            z();
            j(language);
            v();
            w();
            d(language);
            g(language);
            p();
            s();
            u();
            y();
            l();
            t();
            ab();
            ad();
            x();
            ai();
            ac();
            r();
            q();
        } catch (MissingFormatArgumentException unused) {
            LogUtil.b("DeviceInfoManager", "init MissingFormatArgumentException");
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: jfu.3
            @Override // java.lang.Runnable
            public void run() {
                ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).clearPluginInfoList();
                jfu.o();
            }
        });
    }

    public static HashMap<String, Integer> i() {
        m();
        return cup.b();
    }

    public static String d(int i2) {
        m();
        for (Map.Entry<String, Integer> entry : cup.b().entrySet()) {
            if (entry != null && entry.getKey() != null) {
                String key = entry.getKey();
                if ((cup.b().get(key) != null ? cup.b().get(key).intValue() : 0) == i2) {
                    return key;
                }
            }
        }
        return "";
    }

    public static int a(int i2) {
        m();
        Integer num = ag.get(Integer.valueOf(i2));
        if (num == null) {
            LogUtil.h("DeviceInfoManager", "getHiDeviceType hiDeviceType is null.");
            return 0;
        }
        return num.intValue();
    }

    public static boolean n(int i2) {
        m();
        for (int i3 : c) {
            if (i3 == i2) {
                return true;
            }
        }
        if (i2 >= 74) {
            cuw c2 = c(i2);
            if (c2.p() == 2) {
                return true;
            }
            LogUtil.h("DeviceInfoManager", "manuType", Integer.valueOf(c2.p()));
        }
        return false;
    }

    private static void r() {
        i.b(44);
        i.g(92);
        i.a(2);
        i.c((Boolean) true);
        synchronized (d) {
            b.add(i);
        }
    }

    private static void q() {
        g.b(45);
        g.g(93);
        g.a(2);
        g.c((Boolean) true);
        synchronized (d) {
            b.add(g);
        }
    }

    private static void ai() {
        ab.a(BaseApplication.getContext().getString(R$string.IDS_app_display_name_talos));
        ab.b(20);
        ab.d(BaseApplication.getContext().getString(R$string.IDS_talos_device_description));
        ab.g(79);
        ab.a(2);
        ArrayList<String> arrayList = new ArrayList<>(0);
        arrayList.add("talos-".toUpperCase(Locale.ENGLISH));
        arrayList.add("honor Watch-".toUpperCase(Locale.ENGLISH));
        v.put(20, arrayList);
        ArrayList<String> arrayList2 = new ArrayList<>(0);
        arrayList2.add(BaseApplication.getContext().getString(R$string.IDS_hw_health_wear_talos_product_introduction));
        arrayList2.add(BaseApplication.getContext().getString(R$string.IDS_hw_health_wear_talos_product_introduction));
        ab.b(arrayList2);
        ab.c((Boolean) false);
        ab.f(2);
        Object obj = d;
        synchronized (obj) {
            b.add(ab);
        }
        n.a(BaseApplication.getContext().getString(R$string.IDS_app_display_name_fortuna));
        n.b(21);
        n.d(BaseApplication.getContext().getString(R$string.IDS_fortuna_device_description));
        n.g(80);
        n.a(2);
        ArrayList<String> arrayList3 = new ArrayList<>(0);
        arrayList3.add("fortuna-".toUpperCase(Locale.ENGLISH));
        arrayList3.add("HUAWEI WATCH GT-".toUpperCase(Locale.ENGLISH));
        v.put(21, arrayList3);
        ArrayList<String> arrayList4 = new ArrayList<>(0);
        arrayList4.add(BaseApplication.getContext().getString(R$string.IDS_hw_health_wear_fortuna_product_introduction));
        arrayList4.add(BaseApplication.getContext().getString(R$string.IDS_hw_health_wear_fortuna_product_introduction));
        n.b(arrayList4);
        n.c((Boolean) false);
        n.f(1);
        synchronized (obj) {
            b.add(n);
        }
    }

    private static void x() {
        m.a(BaseApplication.getContext().getString(R$string.IDS_app_display_name_crius));
        m.b(18);
        m.d(BaseApplication.getContext().getString(R$string.IDS_crius_device_description));
        m.g(77);
        m.a(2);
        ArrayList<String> arrayList = new ArrayList<>(0);
        arrayList.add("crius-".toUpperCase(Locale.ENGLISH));
        arrayList.add("Honor Band 4-".toUpperCase(Locale.ENGLISH));
        v.put(18, arrayList);
        ArrayList<String> arrayList2 = new ArrayList<>(0);
        arrayList2.add(String.format(Locale.ENGLISH, BaseApplication.getContext().getString(R$string.IDS_hw_wear_crius_intro), 50));
        arrayList2.add(String.format(Locale.ENGLISH, BaseApplication.getContext().getString(R$string.IDS_hw_wear_crius_intro), 50));
        m.b(arrayList2);
        m.c((Boolean) true);
        m.f(2);
        Object obj = d;
        synchronized (obj) {
            b.add(m);
        }
        ac.a(BaseApplication.getContext().getString(R$string.IDS_app_display_name_terra));
        ac.b(19);
        ac.d(BaseApplication.getContext().getString(R$string.IDS_terra_device_description));
        ac.g(78);
        ac.a(2);
        ArrayList<String> arrayList3 = new ArrayList<>(0);
        arrayList3.add("terra-".toUpperCase(Locale.ENGLISH));
        arrayList3.add("HUAWEI Band 3 Pro-".toUpperCase(Locale.ENGLISH));
        v.put(19, arrayList3);
        ArrayList<String> arrayList4 = new ArrayList<>(0);
        arrayList4.add(String.format(Locale.ENGLISH, BaseApplication.getContext().getString(R$string.IDS_hw_wear_terra_intro), 50));
        arrayList4.add(String.format(Locale.ENGLISH, BaseApplication.getContext().getString(R$string.IDS_hw_wear_terra_intro), 50));
        ac.b(arrayList4);
        ac.c((Boolean) true);
        ac.f(1);
        synchronized (obj) {
            b.add(ac);
        }
    }

    private static void ad() {
        z.a(BaseApplication.getContext().getString(R$string.IDS_huawei_r1_pro_content));
        z.b(11);
        z.d(BaseApplication.getContext().getString(R$string.IDS_device_r1_pro_name_title));
        z.j(R.mipmap._2131821232_res_0x7f1102b0);
        z.h(R.mipmap._2131821233_res_0x7f1102b1);
        z.g(43);
        z.e(R.mipmap._2131821240_res_0x7f1102b8);
        z.a(1);
        ad.m(R.mipmap._2131821232_res_0x7f1102b0);
        z.f(1);
        ArrayList<String> arrayList = new ArrayList<>(0);
        arrayList.add("HUAWEI CM-R1P".toUpperCase(Locale.ENGLISH));
        arrayList.add("HUAWEI AM-R1".toUpperCase(Locale.ENGLISH));
        v.put(11, arrayList);
        if (!Utils.g()) {
            synchronized (d) {
                b.add(z);
            }
        }
        ArrayList<String> arrayList2 = new ArrayList<>(0);
        arrayList2.add(BaseApplication.getContext().getString(R$string.IDS_hw_health_wear_r1pro_product_introduction));
        arrayList2.add(BaseApplication.getContext().getString(R$string.IDS_hw_health_wear_r1pro_product_introduction));
        z.b(arrayList2);
        ArrayList<Integer> arrayList3 = new ArrayList<>(0);
        arrayList3.add(Integer.valueOf(R.mipmap._2131821232_res_0x7f1102b0));
        arrayList3.add(Integer.valueOf(R.mipmap._2131821238_res_0x7f1102b6));
        z.c(arrayList3);
        j.a(BaseApplication.getContext().getString(R$string.IDS_DEVICE_HONOUR_WIRED_830_41));
        j.b(75);
        j.d(BaseApplication.getContext().getString(R$string.IDS_DEVICE_HEART_RATE_C_943_467));
        j.j(R.mipmap._2131821440_res_0x7f110380);
        j.h(R.mipmap._2131820668_res_0x7f11007c);
        j.g(75);
        j.e(R.mipmap._2131821239_res_0x7f1102b7);
        j.a(1);
        j.m(R.mipmap._2131821236_res_0x7f1102b4);
        j.f(2);
    }

    private static void ac() {
        r.a(BaseApplication.getContext().getString(R$string.IDS_device_latona_name));
        r.b(34);
        r.d(String.format(Locale.ENGLISH, BaseApplication.getContext().getString(R$string.IDS_device_latona_description_add2e), 46, 42));
        cuw cuwVar = r;
        Integer valueOf = Integer.valueOf(R.mipmap._2131820673_res_0x7f110081);
        cuwVar.j(R.mipmap._2131820673_res_0x7f110081);
        r.h(R.mipmap._2131820666_res_0x7f11007a);
        r.g(88);
        r.m(R.mipmap._2131820673_res_0x7f110081);
        r.e(R.mipmap._2131820674_res_0x7f110082);
        r.a(1);
        r.c((Boolean) false);
        r.f(1);
        ArrayList<String> arrayList = new ArrayList<>(0);
        arrayList.add("HUAWEI LTN-".toUpperCase(Locale.ENGLISH));
        arrayList.add("HUAWEI DAN-".toUpperCase(Locale.ENGLISH));
        arrayList.add("HUAWEI WATCH GT 2-".toUpperCase(Locale.ENGLISH));
        v.put(34, arrayList);
        ArrayList<String> arrayList2 = new ArrayList<>(0);
        arrayList2.add(BaseApplication.getContext().getString(R$string.IDS_hw_health_wear_b3_product_introduction_new));
        arrayList2.add(BaseApplication.getContext().getString(R$string.IDS_hw_health_wear_b3_product_introduction_new));
        r.b(arrayList2);
        ArrayList<Integer> arrayList3 = new ArrayList<>(0);
        arrayList3.add(valueOf);
        arrayList3.add(valueOf);
        r.c(arrayList3);
        synchronized (d) {
            b.add(r);
        }
    }

    private static void t() {
        l.a(BaseApplication.getContext().getString(R$string.IDS_app_display_name_gemini));
        l.b(7);
        l.d(BaseApplication.getContext().getString(R$string.IDS_startup_tablband_intro));
        l.j(R.mipmap._2131821246_res_0x7f1102be);
        l.h(R.mipmap._2131820666_res_0x7f11007a);
        l.g(39);
        l.m(R.mipmap._2131821246_res_0x7f1102be);
        l.e(R.mipmap._2131821436_res_0x7f11037c);
        l.a(1);
        l.f(1);
        ArrayList<String> arrayList = new ArrayList<>(0);
        arrayList.add("HUAWEI GE".toUpperCase(Locale.ENGLISH));
        arrayList.add("HUAWEI B3-".toUpperCase(Locale.ENGLISH));
        v.put(7, arrayList);
        ArrayList<String> arrayList2 = new ArrayList<>(0);
        arrayList2.add(BaseApplication.getContext().getString(R$string.IDS_hw_health_wear_b3_product_introduction_new));
        arrayList2.add(BaseApplication.getContext().getString(R$string.IDS_hw_health_wear_b3_product_introduction_new));
        l.b(arrayList2);
        ArrayList<Integer> arrayList3 = new ArrayList<>(0);
        arrayList3.add(Integer.valueOf(R.mipmap._2131821246_res_0x7f1102be));
        arrayList3.add(Integer.valueOf(R.mipmap._2131821247_res_0x7f1102bf));
        l.c(arrayList3);
        synchronized (d) {
            b.add(l);
        }
    }

    private static void ab() {
        ad.a(BaseApplication.getContext().getString(R$string.IDS_huawei_r1_content));
        ad.b(11);
        ad.d(BaseApplication.getContext().getString(R$string.IDS_device_r1_name_title));
        ad.j(R.mipmap._2131821236_res_0x7f1102b4);
        ad.h(R.mipmap._2131820668_res_0x7f11007c);
        ad.g(43);
        ad.e(R.mipmap._2131821239_res_0x7f1102b7);
        ad.a(1);
        ad.m(R.mipmap._2131821236_res_0x7f1102b4);
        ad.f(1);
        ArrayList<String> arrayList = new ArrayList<>(0);
        arrayList.add("HUAWEI AM-R1".toUpperCase(Locale.ENGLISH));
        v.put(11, arrayList);
        ArrayList<String> arrayList2 = new ArrayList<>(0);
        arrayList2.add(BaseApplication.getContext().getString(R$string.IDS_hw_health_wear_r1_product_introduction));
        arrayList2.add(BaseApplication.getContext().getString(R$string.IDS_hw_health_wear_r1_product_introduction));
        ad.b(arrayList2);
        ArrayList<Integer> arrayList3 = new ArrayList<>(0);
        arrayList3.add(Integer.valueOf(R.mipmap._2131821236_res_0x7f1102b4));
        arrayList3.add(Integer.valueOf(R.mipmap._2131821237_res_0x7f1102b5));
        ad.c(arrayList3);
        if (Utils.g()) {
            return;
        }
        synchronized (d) {
            b.add(ad);
        }
    }

    private static void u() {
        t.a(BaseApplication.getContext().getString(R$string.IDS_app_display_name_honor_aw));
        t.b(24);
        t.d(BaseApplication.getContext().getString(R$string.IDS_aw_content));
        t.g(83);
        t.a(2);
        ArrayList<String> arrayList = new ArrayList<>(0);
        arrayList.add("HONOR AW70");
        arrayList.add("honor Band 4R-");
        v.put(24, arrayList);
        ArrayList<String> arrayList2 = new ArrayList<>(0);
        arrayList2.add(String.format(Locale.ENGLISH, BaseApplication.getContext().getString(R$string.IDS_hw_health_wear_aw_product_introduction), 50));
        arrayList2.add(String.format(Locale.ENGLISH, BaseApplication.getContext().getString(R$string.IDS_hw_health_wear_aw_product_introduction), 50));
        t.b(arrayList2);
        t.c((Boolean) true);
        t.f(2);
        synchronized (d) {
            b.add(t);
        }
    }

    private static void y() {
        p.a(BaseApplication.getContext().getString(R$string.IDS_app_display_name_honor_aw_pro));
        p.b(37);
        p.d(BaseApplication.getContext().getString(R$string.IDS_aw_pro_support_basketball_run_bracelet));
        p.g(91);
        p.a(2);
        ArrayList<String> arrayList = new ArrayList<>(0);
        arrayList.add("AW70B39HN");
        v.put(37, arrayList);
        ArrayList<String> arrayList2 = new ArrayList<>(0);
        arrayList2.add(String.format(Locale.ENGLISH, BaseApplication.getContext().getString(R$string.IDS_aw_pro_support_run_data_fashion_bracelet), 50));
        arrayList2.add(String.format(Locale.ENGLISH, BaseApplication.getContext().getString(R$string.IDS_aw_pro_support_run_data_fashion_bracelet), 50));
        p.b(arrayList2);
        p.c((Boolean) true);
        p.f(2);
        synchronized (d) {
            b.add(p);
        }
    }

    private static void l() {
        h.a(nsn.d(BaseApplication.getContext()));
        h.b(12);
        h.d(BaseApplication.getContext().getString(R$string.IDS_huawei_a1p_content));
        h.j(R.mipmap._2131821234_res_0x7f1102b2);
        h.h(R.mipmap._2131820662_res_0x7f110076);
        h.g(44);
        h.m(R.mipmap._2131821234_res_0x7f1102b2);
        h.e(R.mipmap._2131821435_res_0x7f11037b);
        h.a(2);
        h.f(nsn.c(BaseApplication.getContext()));
        ArrayList<String> arrayList = new ArrayList<>(0);
        arrayList.add("HUAWEI band A2".toUpperCase(Locale.ENGLISH));
        arrayList.add("honor band A2".toUpperCase(Locale.ENGLISH));
        arrayList.add("AW61".toUpperCase(Locale.ENGLISH));
        arrayList.add("HUAWEI Color Band A2".toUpperCase(Locale.ENGLISH));
        v.put(12, arrayList);
        ArrayList<String> arrayList2 = new ArrayList<>(0);
        arrayList2.add(BaseApplication.getContext().getString(R$string.IDS_hw_health_wear_a1_product_introduction_new_fixed));
        arrayList2.add(BaseApplication.getContext().getString(R$string.IDS_hw_health_wear_a1_product_introduction_new_fixed));
        h.b(arrayList2);
        ArrayList<Integer> arrayList3 = new ArrayList<>(0);
        arrayList3.add(Integer.valueOf(R.mipmap._2131821234_res_0x7f1102b2));
        arrayList3.add(Integer.valueOf(R.mipmap._2131821235_res_0x7f1102b3));
        h.c(arrayList3);
        synchronized (d) {
            b.add(h);
        }
    }

    private static void g(String str) {
        u.a(BaseApplication.getContext().getString(R$string.IDS_app_display_name_nys));
        u.b(13);
        u.d(String.format(Locale.ENGLISH, BaseApplication.getContext().getString(R$string.IDS_nyx_content), 50));
        u.j(R.mipmap._2131821241_res_0x7f1102b9);
        u.h(R.mipmap._2131820672_res_0x7f110080);
        u.g(42);
        u.m(R.mipmap._2131821241_res_0x7f1102b9);
        u.e(R.mipmap._2131821434_res_0x7f11037a);
        u.a(2);
        u.c((Boolean) true);
        u.f(2);
        ArrayList<String> arrayList = new ArrayList<>(0);
        arrayList.add("HUAWEI NYX".toUpperCase(Locale.ENGLISH));
        arrayList.add("HONOR NYX".toUpperCase(Locale.ENGLISH));
        arrayList.add("Honor Band 3-".toUpperCase(Locale.ENGLISH));
        v.put(13, arrayList);
        ArrayList<String> arrayList2 = new ArrayList<>(0);
        if ("en".equals(str)) {
            arrayList2.add(BaseApplication.getContext().getString(R$string.IDS_hw_health_wear_nyx_product_introduction_new_5atm));
            arrayList2.add(BaseApplication.getContext().getString(R$string.IDS_hw_health_wear_nyx_product_introduction_new_5atm));
        } else {
            arrayList2.add(String.format(Locale.ENGLISH, BaseApplication.getContext().getString(R$string.IDS_hw_health_wear_nyx_product_introduction_new), 50));
            arrayList2.add(String.format(Locale.ENGLISH, BaseApplication.getContext().getString(R$string.IDS_hw_health_wear_nyx_product_introduction_new), 50));
        }
        u.b(arrayList2);
        ArrayList<Integer> arrayList3 = new ArrayList<>(0);
        arrayList3.add(Integer.valueOf(R.mipmap._2131821241_res_0x7f1102b9));
        arrayList3.add(Integer.valueOf(R.mipmap._2131821242_res_0x7f1102ba));
        u.c(arrayList3);
        synchronized (d) {
            b.add(u);
        }
    }

    private static void p() {
        f.a(BaseApplication.getContext().getString(R$string.IDS_app_display_name_aw));
        f.b(23);
        f.d(BaseApplication.getContext().getString(R$string.IDS_aw_content));
        f.g(81);
        f.a(2);
        ArrayList<String> arrayList = new ArrayList<>(0);
        arrayList.add("HUAWEI Band 3e-");
        v.put(23, arrayList);
        ArrayList<String> arrayList2 = new ArrayList<>(0);
        arrayList2.add(String.format(Locale.ENGLISH, BaseApplication.getContext().getString(R$string.IDS_hw_health_wear_aw_product_introduction), 50));
        arrayList2.add(String.format(Locale.ENGLISH, BaseApplication.getContext().getString(R$string.IDS_hw_health_wear_aw_product_introduction), 50));
        f.b(arrayList2);
        f.c((Boolean) true);
        f.f(1);
        synchronized (d) {
            b.add(f);
        }
    }

    private static void s() {
        k.a(BaseApplication.getContext().getString(R$string.IDS_app_display_name_aw_pro));
        k.b(36);
        k.d(BaseApplication.getContext().getString(R$string.IDS_aw_pro_support_basketball_run_bracelet));
        k.g(90);
        k.a(2);
        ArrayList<String> arrayList = new ArrayList<>(0);
        arrayList.add("AW70B39HW");
        arrayList.add("HUAWEI Band 4e-");
        v.put(36, arrayList);
        ArrayList<String> arrayList2 = new ArrayList<>(0);
        arrayList2.add(String.format(Locale.ENGLISH, BaseApplication.getContext().getString(R$string.IDS_aw_pro_support_run_data_fashion_bracelet), 50));
        arrayList2.add(String.format(Locale.ENGLISH, BaseApplication.getContext().getString(R$string.IDS_aw_pro_support_run_data_fashion_bracelet), 50));
        k.b(arrayList2);
        k.c((Boolean) true);
        k.f(1);
        synchronized (d) {
            b.add(k);
        }
    }

    private static void w() {
        s.a(BaseApplication.getContext().getString(R$string.IDS_app_display_name_grus));
        s.b(14);
        s.d(BaseApplication.getContext().getString(R$string.IDS_startup_tablband_intro));
        s.j(R.mipmap._2131821251_res_0x7f1102c3);
        s.h(R.mipmap._2131820667_res_0x7f11007b);
        s.g(45);
        s.m(R.mipmap._2131821251_res_0x7f1102c3);
        s.e(R.mipmap._2131821437_res_0x7f11037d);
        s.a(1);
        s.f(1);
        ArrayList<String> arrayList = new ArrayList<>(0);
        arrayList.add("HUAWEI GRUS".toUpperCase(Locale.ENGLISH));
        arrayList.add("HUAWEI B3 Lite-".toUpperCase(Locale.ENGLISH));
        v.put(14, arrayList);
        ArrayList<String> arrayList2 = new ArrayList<>(0);
        arrayList2.add(BaseApplication.getContext().getString(R$string.IDS_hw_health_wear_grus_product_introduction_new));
        arrayList2.add(BaseApplication.getContext().getString(R$string.IDS_hw_health_wear_grus_product_introduction_new));
        s.b(arrayList2);
        ArrayList<Integer> arrayList3 = new ArrayList<>(0);
        arrayList3.add(Integer.valueOf(R.mipmap._2131821251_res_0x7f1102c3));
        arrayList3.add(Integer.valueOf(R.mipmap._2131821252_res_0x7f1102c4));
        s.c(arrayList3);
        synchronized (d) {
            b.add(s);
        }
    }

    private static void d(String str) {
        if (Utils.o()) {
            if (LanguageUtil.j(BaseApplication.getContext())) {
                o.a(BaseApplication.getContext().getString(R$string.IDS_app_display_name_eris));
            } else {
                o.a(BaseApplication.getContext().getString(R$string.IDS_app_display_name_eris_new));
            }
        } else {
            o.a(BaseApplication.getContext().getString(R$string.IDS_app_display_name_eris));
        }
        o.b(15);
        try {
            o.d(String.format(Locale.ENGLISH, BaseApplication.getContext().getString(R$string.IDS_nyx_content), 50));
        } catch (MissingFormatArgumentException unused) {
            LogUtil.b("DeviceInfoManager", "initEris MissingFormatArgumentException");
        }
        o.j(R.mipmap._2131821248_res_0x7f1102c0);
        o.h(R.mipmap._2131820665_res_0x7f110079);
        o.g(47);
        o.m(R.mipmap._2131821248_res_0x7f1102c0);
        o.e(R.mipmap._2131821438_res_0x7f11037e);
        o.a(2);
        o.f(1);
        ArrayList<String> arrayList = new ArrayList<>(0);
        arrayList.add("Huawei Band 2-".toUpperCase(Locale.ENGLISH));
        v.put(15, arrayList);
        o.b(a(str));
        ArrayList<Integer> arrayList2 = new ArrayList<>(0);
        arrayList2.add(Integer.valueOf(R.mipmap._2131821248_res_0x7f1102c0));
        arrayList2.add(Integer.valueOf(R.mipmap._2131821249_res_0x7f1102c1));
        o.c(arrayList2);
        synchronized (d) {
            b.add(o);
        }
    }

    private static ArrayList<String> a(String str) {
        ArrayList<String> arrayList = new ArrayList<>(0);
        if ("en".equals(str)) {
            arrayList.add(BaseApplication.getContext().getString(R$string.IDS_hw_health_wear_eris_product_introduction_new_5atm));
            arrayList.add(BaseApplication.getContext().getString(R$string.IDS_hw_health_wear_eris_product_introduction_new_5atm));
        } else {
            try {
                arrayList.add(String.format(Locale.ENGLISH, BaseApplication.getContext().getString(R$string.IDS_hw_health_wear_eris_product_introduction_new), 50));
                arrayList.add(String.format(Locale.ENGLISH, BaseApplication.getContext().getString(R$string.IDS_hw_health_wear_eris_product_introduction_new), 50));
            } catch (MissingFormatArgumentException unused) {
                LogUtil.b("DeviceInfoManager", "string format exception");
            }
        }
        return arrayList;
    }

    private static void v() {
        q.a(BaseApplication.getContext().getString(R$string.IDS_app_display_name_janus));
        q.b(16);
        q.d(BaseApplication.getContext().getString(R$string.HomeDeviceCloud_IDS_Janus_Device_Description));
        q.g(74);
        q.a(1);
        q.c((Boolean) true);
        q.f(1);
        ArrayList<String> arrayList = new ArrayList<>(0);
        arrayList.add("HUAWEI JNS-".toUpperCase(Locale.ENGLISH));
        arrayList.add("HUAWEI B5-".toUpperCase(Locale.ENGLISH));
        v.put(16, arrayList);
        ArrayList<String> arrayList2 = new ArrayList<>(0);
        arrayList2.add(BaseApplication.getContext().getString(R$string.IDS_hw_health_wear_janus_product_introduction));
        arrayList2.add(BaseApplication.getContext().getString(R$string.IDS_hw_health_wear_janus_product_introduction));
        q.b(arrayList2);
        synchronized (d) {
            b.add(q);
        }
    }

    private static void j(String str) {
        aa.a(nsn.t(BaseApplication.getContext()));
        aa.b(8);
        try {
            aa.d(String.format(Locale.ENGLISH, BaseApplication.getContext().getString(R$string.IDS_S1_description_content), 50));
        } catch (MissingFormatArgumentException unused) {
            LogUtil.b("DeviceInfoManager", "initS1 MissingFormatArgumentException");
        }
        aa.j(R.mipmap._2131821243_res_0x7f1102bb);
        aa.h(R.mipmap._2131820671_res_0x7f11007f);
        aa.g(41);
        aa.m(R.mipmap._2131821243_res_0x7f1102bb);
        aa.e(R.mipmap._2131821452_res_0x7f11038c);
        aa.a(2);
        aa.f(nsn.s(BaseApplication.getContext()));
        ArrayList<String> arrayList = new ArrayList<>(0);
        arrayList.add("metis".toUpperCase(Locale.ENGLISH));
        arrayList.add("honor watch S1".toUpperCase(Locale.ENGLISH));
        arrayList.add("HUAWEI FIT".toUpperCase(Locale.ENGLISH));
        v.put(8, arrayList);
        ArrayList<String> arrayList2 = new ArrayList<>(0);
        if ("en".equals(str)) {
            arrayList2.add(BaseApplication.getContext().getString(R$string.IDS_hw_health_wear_s1_product_introduction_new_5atm));
            arrayList2.add(BaseApplication.getContext().getString(R$string.IDS_hw_health_wear_s1_product_introduction_new_5atm));
        } else {
            arrayList2.add(String.format(Locale.ENGLISH, BaseApplication.getContext().getString(R$string.IDS_hw_health_wear_s1_product_introduction_new), 50));
            arrayList2.add(String.format(Locale.ENGLISH, BaseApplication.getContext().getString(R$string.IDS_hw_health_wear_s1_product_introduction_new), 50));
        }
        aa.b(arrayList2);
        ArrayList<Integer> arrayList3 = new ArrayList<>(0);
        arrayList3.add(Integer.valueOf(R.mipmap._2131821243_res_0x7f1102bb));
        arrayList3.add(Integer.valueOf(R.mipmap._2131821244_res_0x7f1102bc));
        aa.c(arrayList3);
        synchronized (d) {
            b.add(aa);
        }
    }

    private static void aa() {
        x.a(BaseApplication.getContext().getString(R$string.IDS_app_display_name_leo));
        x.b(10);
        x.d(BaseApplication.getContext().getString(R$string.IDS_add_device_smart_watch_content));
        x.j(R.mipmap._2131821253_res_0x7f1102c5);
        x.h(R.mipmap._2131820670_res_0x7f11007e);
        x.g(46);
        x.e(R.mipmap._2131821453_res_0x7f11038d);
        x.a(0);
        x.m(R.mipmap._2131821253_res_0x7f1102c5);
        x.c((Boolean) false);
        x.f(1);
        ArrayList<String> arrayList = new ArrayList<>(0);
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_hw_health_wear_huawei_watch2_introduction));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_hw_health_wear_huawei_watch2_introduction));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_hw_health_wear_huawei_watch2_introduction));
        x.b(arrayList);
        ArrayList<Integer> arrayList2 = new ArrayList<>(0);
        arrayList2.add(Integer.valueOf(R.mipmap._2131821253_res_0x7f1102c5));
        arrayList2.add(Integer.valueOf(R.mipmap._2131821254_res_0x7f1102c6));
        arrayList2.add(Integer.valueOf(R.mipmap._2131821255_res_0x7f1102c7));
        x.c(arrayList2);
        synchronized (d) {
            b.add(x);
        }
        y.a(BaseApplication.getContext().getString(R$string.IDS_app_display_name_porc));
        y.b(10);
        y.d(BaseApplication.getContext().getString(R$string.IDS_add_device_smart_watch_content));
        y.j(R.mipmap._2131821260_res_0x7f1102cc);
        y.h(R.mipmap._2131820669_res_0x7f11007d);
        y.g(46);
        y.c((Boolean) false);
        y.f(1);
        ArrayList<String> arrayList3 = new ArrayList<>(0);
        arrayList3.add(BaseApplication.getContext().getString(R$string.IDS_hw_health_wear_huawei_watch2_proc_introduction));
        arrayList3.add(BaseApplication.getContext().getString(R$string.IDS_hw_health_wear_huawei_watch2_proc_introduction));
        y.b(arrayList3);
        ArrayList<Integer> arrayList4 = new ArrayList<>(0);
        arrayList4.add(Integer.valueOf(R.mipmap._2131821260_res_0x7f1102cc));
        arrayList4.add(Integer.valueOf(R.mipmap._2131821261_res_0x7f1102cd));
        y.c(arrayList4);
    }

    private static void z() {
        w.a(BaseApplication.getContext().getString(R$string.IDS_app_display_name_porc));
        w.b(10);
        w.d(BaseApplication.getContext().getString(R$string.IDS_huawei_watch_series));
        w.j(R.mipmap._2131820673_res_0x7f110081);
        w.h(R.mipmap._2131820669_res_0x7f11007d);
        w.g(46);
        w.c((Boolean) false);
        w.f(1);
        ArrayList<String> arrayList = new ArrayList<>(0);
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_hw_health_wear_huawei_watch2_proc_introduction));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_hw_health_wear_huawei_watch2_proc_introduction));
        w.b(arrayList);
        ArrayList<Integer> arrayList2 = new ArrayList<>(0);
        arrayList2.add(Integer.valueOf(R.mipmap._2131821260_res_0x7f1102cc));
        arrayList2.add(Integer.valueOf(R.mipmap._2131821261_res_0x7f1102cd));
        w.c(arrayList2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void o() {
        List<cvk> indexList = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getIndexList();
        if (indexList == null || indexList.size() <= 0) {
            return;
        }
        LogUtil.a("DeviceInfoManager", "getDownloadedEzPluginInfo.size > 0");
        for (cvk cvkVar : indexList) {
            if (((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).isResourcesAvailable(cvkVar.e())) {
                e(((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(cvkVar.e()));
            }
        }
    }

    public static void e(cvc cvcVar) {
        m();
        LogUtil.a("DeviceInfoManager", " enter loadDeviceInfoFromJson.");
        if (cvcVar == null) {
            LogUtil.a("DeviceInfoManager", "null is info");
            return;
        }
        LogUtil.a("DeviceInfoManager", "null is not info");
        if (cvcVar.e() == null) {
            LogUtil.a("DeviceInfoManager", " null is info.fetchPluginUuid()");
            return;
        }
        synchronized (d) {
            if (b == null) {
                LogUtil.h("DeviceInfoManager", "allDevices is null");
                return;
            }
            if (cup.b() != null && !cup.b().containsKey(cvcVar.e())) {
                LogUtil.a("DeviceInfoManager", " uuidTypes is not containsKey");
            }
            b(cvcVar);
        }
    }

    public static cuw c(String str, boolean z2) {
        m();
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DeviceInfoManager", "getDeviceInfo uuid is null");
            return null;
        }
        if (!cup.c().containsKey(str)) {
            return null;
        }
        int intValue = cup.c().get(str).intValue();
        if (intValue == 10) {
            if (z2) {
                return y;
            }
            return x;
        }
        if (intValue == 11) {
            if (cup.a(str)) {
                return ad;
            }
            return z;
        }
        return c(cup.c().get(str).intValue());
    }

    public static String e(int i2) {
        m();
        for (Map.Entry<String, Integer> entry : cup.c().entrySet()) {
            if (entry != null && entry.getKey() != null) {
                String key = entry.getKey();
                if ((cup.c().get(key) != null ? cup.c().get(key).intValue() : 0) == i2) {
                    return key;
                }
            }
        }
        return "";
    }

    private static void b(cvc cvcVar) {
        cuw e2;
        if (cup.b().containsKey(cvcVar.e())) {
            LogUtil.a("DeviceInfoManager", "setLoadDeviceInfo local");
            e2 = c(cup.b().get(cvcVar.e()).intValue());
            LogUtil.a("DeviceInfoManager", "setLoadDeviceInfo fetchPluginUuid: ", cvcVar.e());
            if (e2 != null && e2.m() != cup.b().get(cvcVar.e()).intValue()) {
                LogUtil.a("DeviceInfoManager", "setLoadDeviceInfo getDeviceType(): ", Integer.valueOf(e2.m()));
                synchronized (d) {
                    LogUtil.a("DeviceInfoManager", "setLoadDeviceInfo local add");
                    b.add(e2);
                }
            }
        } else {
            LogUtil.a("DeviceInfoManager", "setLoadDeviceInfo cloud");
            e2 = e(cvcVar.e());
            if (e2 == null && cvcVar.f() != null) {
                int af2 = cvcVar.f().af();
                LogUtil.a("DeviceInfoManager", "setLoadDeviceInfo cloud deviceType:", Integer.valueOf(af2));
                if (af2 != -1) {
                    e2 = c(af2);
                }
                Object[] objArr = new Object[2];
                objArr[0] = "setLoadDeviceInfo cloud getDeviceType:";
                objArr[1] = e2 == null ? Constants.NULL : Integer.valueOf(e2.m());
                LogUtil.a("DeviceInfoManager", objArr);
                if (e2 != null && e2.m() != af2) {
                    e2 = null;
                }
            }
            if (e2 == null) {
                e2 = new cuw();
                synchronized (d) {
                    LogUtil.a("DeviceInfoManager", "setLoadDeviceInfo cloud add:");
                    b.add(e2);
                }
            }
        }
        jfx.d(cvcVar, e2, cup.b(), v);
    }

    public static ArrayList<cuw> a(boolean z2) {
        m();
        ArrayList<cuw> arrayList = new ArrayList<>(0);
        LogUtil.a("DeviceInfoManager", " BETA_VERSION_DISABLE : ", false);
        arrayList.add(r);
        if (l(10) && !z2) {
            arrayList.add(x);
            arrayList.add(y);
        }
        if (jcu.j) {
            arrayList.add(aa);
        }
        return arrayList;
    }

    public static cuw b() {
        m();
        LogUtil.a("DeviceInfoManager", "enter getInfoForPorscheFirst");
        return w;
    }

    public static ArrayList<cuw> k() {
        m();
        LogUtil.a("DeviceInfoManager", "enter listInfoForPorscheSecond");
        ArrayList<cuw> arrayList = new ArrayList<>(0);
        arrayList.add(y);
        return arrayList;
    }

    public static ArrayList<cuw> d(boolean z2) {
        m();
        ArrayList<cuw> arrayList = new ArrayList<>(0);
        if (jcu.b && !z2) {
            arrayList.add(s);
        }
        if (jcu.c) {
            arrayList.add(o);
        }
        if (jcu.g) {
            arrayList.add(u);
        }
        if (jcu.d && !z2) {
            arrayList.add(h);
        }
        if (!z2) {
            arrayList.add(l);
        }
        return arrayList;
    }

    public static ArrayList<cuw> g() {
        m();
        ArrayList<cuw> arrayList = new ArrayList<>(0);
        if (jcu.i && !Utils.g()) {
            arrayList.add(ad);
            arrayList.add(z);
        }
        return arrayList;
    }

    public static cuw c(int i2) {
        m();
        cuw cuwVar = new cuw();
        synchronized (d) {
            Iterator<cuw> it = b.iterator();
            while (it.hasNext()) {
                cuw next = it.next();
                if (next.m() == i2) {
                    LogUtil.a("DeviceInfoManager", "result.getDeviceType():", Integer.valueOf(next.m()));
                    return next;
                }
            }
            return cuwVar;
        }
    }

    public static cuw e(String str) {
        ReleaseLogUtil.e("R_DeviceInfoManager", "uuid: ", str, ", allDevices size: ", Integer.valueOf(b.size()));
        m();
        if (str == null || "".equals(str)) {
            return null;
        }
        synchronized (d) {
            Iterator<cuw> it = b.iterator();
            while (it.hasNext()) {
                cuw next = it.next();
                ReleaseLogUtil.e("R_DeviceInfoManager", "deviceInfoNew uuid: ", next.ad());
                if (str.equals(next.ad())) {
                    return next;
                }
            }
            return null;
        }
    }

    public static cuw h() {
        m();
        return y;
    }

    public static cuw j() {
        m();
        return z;
    }

    public static cuw a() {
        m();
        return j;
    }

    public static int c(String str) {
        m();
        LogUtil.a("DeviceInfoManager", "enter getTypeByName name:", str);
        Set<Map.Entry<Integer, ArrayList<String>>> entrySet = v.entrySet();
        if (entrySet == null || entrySet.isEmpty()) {
            LogUtil.a("DeviceInfoManager", "enter getTypeByName entries is null");
            return -1;
        }
        for (Map.Entry<Integer, ArrayList<String>> entry : entrySet) {
            Iterator<String> it = entry.getValue().iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (str != null && next != null && str.toUpperCase(Locale.ENGLISH).contains(next.toUpperCase(Locale.ENGLISH))) {
                    return entry.getKey().intValue();
                }
            }
        }
        return -1;
    }

    public static ArrayList<String> b(int i2) {
        m();
        return v.containsKey(Integer.valueOf(i2)) ? v.get(Integer.valueOf(i2)) : new ArrayList<>(0);
    }

    public static boolean l(int i2) {
        boolean isSupportB3;
        m();
        HealthSupportModel c2 = cvw.c();
        switch (i2) {
            case 7:
                isSupportB3 = c2.isSupportB3();
                break;
            case 8:
                isSupportB3 = c2.isSupportMetis();
                break;
            case 9:
            default:
                isSupportB3 = false;
                break;
            case 10:
                isSupportB3 = c2.isSupportLeo();
                break;
            case 11:
                isSupportB3 = c2.isSupportR1();
                break;
            case 12:
                isSupportB3 = c2.isSupportA2();
                break;
            case 13:
                isSupportB3 = c2.isSupportNyx();
                break;
            case 14:
                isSupportB3 = c2.isSupportB3Lite();
                break;
            case 15:
                isSupportB3 = c2.isSupportEris();
                break;
        }
        LogUtil.a("DeviceInfoManager", "isHealthSupport deviceType:", i2 + "  isSupport:", Boolean.valueOf(isSupportB3));
        return isSupportB3;
    }

    public static List<String> e() {
        m();
        ArrayList arrayList = new ArrayList(0);
        arrayList.add("metis");
        arrayList.add("honor watch S1");
        arrayList.add("HUAWEI FIT");
        arrayList.add("HUAWEI band A2");
        arrayList.add("honor band A2");
        arrayList.add("AW61");
        arrayList.add("HUAWEI Color Band A2");
        if (jcu.g) {
            arrayList.add("HUAWEI NYX");
            arrayList.add("HONOR NYX");
            arrayList.add("Honor Band 3-");
        }
        arrayList.add("Huawei Band 2-");
        arrayList.add("HUAWEI GE");
        arrayList.add("HUAWEI B3-");
        arrayList.add("HUAWEI AM-R1");
        arrayList.add("HUAWEI CM-R1P");
        arrayList.add("HUAWEI GRUS");
        arrayList.add("HUAWEI B3 Lite-");
        arrayList.add("HUAWEI Band 3e-");
        arrayList.add("AW70B39HW");
        arrayList.add("HUAWEI Band 4e-");
        arrayList.add("HONOR AW70");
        arrayList.add("honor Band 4R-");
        arrayList.add("AW70B39HN");
        arrayList.add("crius-");
        arrayList.add("Honor Band 4-");
        arrayList.add("HONOR Band 5-");
        arrayList.add("terra-");
        arrayList.add("HUAWEI Band 3 Pro-");
        arrayList.add("HUAWEI Band 3-");
        arrayList.add("talos-");
        arrayList.add("honor Watch-");
        arrayList.add("fortuna-");
        arrayList.add("HUAWEI WATCH GT-");
        arrayList.add("HUAWEI LTN-");
        arrayList.add("HUAWEI WATCH GT 2-");
        arrayList.add("HUAWEI DAN-");
        arrayList.add("HONOR Band 4X-");
        arrayList.add("HONOR Band 5i-");
        arrayList.add("HUAWEI Band 3i-");
        arrayList.add("HONOR MagicWatch 2-");
        return arrayList;
    }

    public static String c(int i2, String str, boolean z2) {
        String str2;
        m();
        LogUtil.a("DeviceInfoManager", "getDeviceNameFromDeviceTypeForBI originalType:", Integer.valueOf(i2), " name:", str, " isPro:" + z2);
        switch (i2) {
            case 8:
                str2 = "IDS_device_metis_name_honor_watch_s1";
                break;
            case 9:
            default:
                str2 = t(i2);
                break;
            case 10:
                if (!z2) {
                    str2 = "IDS_app_display_name_leo";
                    break;
                } else {
                    str2 = "IDS_app_display_name_porc";
                    break;
                }
            case 11:
                if (!"HUAWEI CM-R1P".equals(str) && !BaseApplication.getContext().getString(R$string.IDS_huawei_r1_pro_content).equals(str) && !BaseApplication.getContext().getString(R$string.IDS_device_r1_pro_name_title).equals(str)) {
                    str2 = "IDS_huawei_r1_content";
                    break;
                } else {
                    str2 = "IDS_huawei_r1_pro_content";
                    break;
                }
            case 12:
                str2 = "IDS_select_device_talkband_a1";
                break;
            case 13:
                str2 = "IDS_app_display_name_nys";
                break;
            case 14:
                str2 = "IDS_app_display_name_grus";
                break;
            case 15:
                str2 = "IDS_app_display_name_eris";
                break;
            case 16:
                str2 = "JANUS";
                break;
        }
        LogUtil.a("DeviceInfoManager", "getDeviceNameFromDeviceTypeForBI:", str2);
        return str2;
    }

    private static String t(int i2) {
        String str;
        if (i2 == 7) {
            str = "IDS_app_display_name_gemini";
        } else if (i2 == 32) {
            str = "CASSINI";
        } else if (i2 == 34) {
            str = "LATONA";
        } else if (i2 == 23) {
            str = "AW70B29";
        } else if (i2 == 24) {
            str = "AW70B19";
        } else if (i2 == 36) {
            str = "AW70B39HW";
        } else if (i2 == 37) {
            str = "AW70B39HN";
        } else if (i2 == 44) {
            str = "ANDES_B19";
        } else if (i2 != 45) {
            switch (i2) {
                case 18:
                    str = "CRIUS";
                    break;
                case 19:
                    str = "TERRA";
                    break;
                case 20:
                    str = "TALOS";
                    break;
                case 21:
                    str = "FORTUNA";
                    break;
                default:
                    str = q(i2);
                    break;
            }
        } else {
            str = "ANDES_B29";
        }
        LogUtil.a("DeviceInfoManager", "getPartDeviceName is:", str);
        return str;
    }

    private static String q(int i2) {
        cuw c2 = c(i2);
        return (c2 == null || "".equals(c2.c())) ? "unknown" : c2.c();
    }

    public static HashMap<Integer, String> c() {
        m();
        return f13798a;
    }

    public static boolean o(int i2) {
        m();
        boolean y2 = CommonUtil.y(BaseApplication.getContext());
        LogUtil.a("DeviceInfoManager", "isSupportMidWare isGooglePlay:", false, ", isNewHonor:", Boolean.valueOf(MagicBuild.f13130a), ", isChinaRom:", Boolean.valueOf(y2));
        if (MagicBuild.f13130a && !y2) {
            LogUtil.h("DeviceInfoManager", "isSupportMidWare isNewHonor oversea phone return");
            return false;
        }
        if (i2 != 13 && i2 != 14 && i2 != 34 && i2 != 44 && i2 != 45) {
            switch (i2) {
                case 18:
                case 19:
                case 20:
                case 21:
                    break;
                default:
                    return v(i2);
            }
        }
        return true;
    }

    private static boolean v(int i2) {
        cuw c2 = c(i2);
        if (c2.m() != 0) {
            return c2.ae();
        }
        cvc pluginInfoByUuid = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(d(i2));
        if (pluginInfoByUuid == null || pluginInfoByUuid.f() == null) {
            LogUtil.h("DeviceInfoManager", "isSupportMidWareDefault info or WearDeviceInfo is null");
            return false;
        }
        return pluginInfoByUuid.f().cb();
    }

    public static int b(String str) {
        m();
        synchronized (d) {
            LogUtil.a("DeviceInfoManager", "allDevices size is ", Integer.valueOf(b.size()));
            Iterator<cuw> it = b.iterator();
            while (it.hasNext()) {
                cuw next = it.next();
                if (next.ad() != null && str != null) {
                    if (str.equals(next.ad())) {
                        return next.m();
                    }
                }
                LogUtil.h("DeviceInfoManager", "getTypeForPlugin() ", next.ad());
                return -1;
            }
            return -1;
        }
    }

    public static String j(int i2) {
        m();
        synchronized (d) {
            Iterator<cuw> it = b.iterator();
            while (it.hasNext()) {
                cuw next = it.next();
                if (i2 == next.m()) {
                    return next.ad();
                }
            }
            return d(i2);
        }
    }

    private static void m() {
        if (CompileParameterUtil.a("IS_RELEASE_VERSION")) {
            return;
        }
        String d2 = CommonUtil.d(Process.myPid());
        if (TextUtils.isEmpty(d2) || !d2.endsWith(":PhoneService")) {
            return;
        }
        throw new RuntimeException("DeviceInfoManager not init in process." + d2);
    }

    public static boolean m(int i2) {
        m();
        LogUtil.a("DeviceInfoManager", "enter isPluginDownloadByType");
        synchronized (d) {
            Iterator<cuw> it = b.iterator();
            while (it.hasNext()) {
                cuw next = it.next();
                if (i2 == next.m()) {
                    LogUtil.a("DeviceInfoManager", "device type is existence");
                    if (!"".equals(next.ad())) {
                        LogUtil.a("DeviceInfoManager", "uuid is not null");
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public static boolean h(int i2) {
        m();
        return c(i2).s();
    }

    public static boolean k(int i2) {
        m();
        cuw c2 = c(i2);
        if (c2.m() != 0) {
            return c2.aa();
        }
        cvc pluginInfoByUuid = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(d(i2));
        if (pluginInfoByUuid == null || pluginInfoByUuid.f() == null) {
            LogUtil.h("DeviceInfoManager", "isSupportMidWareDefault info or WearDeviceInfo is null");
            return false;
        }
        return pluginInfoByUuid.f().bs();
    }

    public static boolean i(int i2) {
        m();
        return i2 == 7 || i2 == 8 || x(i2) || i2 >= 32;
    }

    private static boolean x(int i2) {
        return i2 == 13 || i2 == 14 || r(i2);
    }

    private static boolean r(int i2) {
        return i2 == 15 || i2 == 16 || p(i2);
    }

    private static boolean p(int i2) {
        return i2 == 18 || i2 == 19 || u(i2);
    }

    private static boolean u(int i2) {
        return i2 == 20 || i2 == 21 || i2 == 10 || cvt.c(i2);
    }

    public static boolean d(int i2, String str) {
        m();
        return !"unknown".equals(c(i2, str, false)) && s(i2);
    }

    private static boolean s(int i2) {
        if (!CommonUtil.ag(BaseApplication.getContext())) {
            return true;
        }
        LogUtil.a("DeviceInfoManager", "getInfoByType(deviceType).isSupportRelease() : ", Boolean.valueOf(c(i2).af()));
        return c(i2).af();
    }

    public static boolean f(int i2) {
        m();
        return e.get(Integer.valueOf(i2)) != null;
    }

    public static boolean g(int i2) {
        m();
        return cup.b().containsValue(Integer.valueOf(i2));
    }

    public static void e(Context context) {
        m();
        Map<String, DeviceCapability> a2 = jfq.c().a(3, "", "DeviceInfoManager");
        if (a2 != null && !a2.isEmpty()) {
            cvs.d(a2);
        }
        DeviceInfo d2 = jpt.d("DeviceInfoManager");
        if (d2 != null) {
            Map<String, DeviceCapability> a3 = jfq.c().a(1, d2.getDeviceIdentify(), "DeviceInfoManager");
            DeviceCapability deviceCapability = (a3 == null || a3.isEmpty()) ? null : a3.get(d2.getDeviceIdentify());
            if (deviceCapability != null) {
                cvs.a(deviceCapability);
                a(deviceCapability, context);
                String json = new Gson().toJson(deviceCapability, DeviceCapability.class);
                LogUtil.c("DeviceInfoManager", "initDeviceInfo setSharedPreference");
                SharedPreferenceManager.e(context, String.valueOf(1), "kStorage_DeviceCfgMgr_Capability", json, (StorageParams) null);
            }
            cvs.b(d2);
            return;
        }
        Gson gson = new Gson();
        String b2 = SharedPreferenceManager.b(context, String.valueOf(1), "kStorage_DeviceCfgMgr_Capability");
        if (b2 == null || "".equalsIgnoreCase(b2)) {
            return;
        }
        LogUtil.c("DeviceInfoManager", "initDeviceInfo getSharedPreference");
        DeviceCapability deviceCapability2 = (DeviceCapability) gson.fromJson(b2, DeviceCapability.class);
        cvs.a(deviceCapability2);
        a(deviceCapability2, context);
    }

    private static void a(DeviceCapability deviceCapability, Context context) {
        if (deviceCapability != null) {
            Intent intent = new Intent();
            intent.setPackage(context.getPackageName());
            intent.setAction("com.huawei.health.action.ACTION_WEAR_DEVICE_CAPABILITY");
            intent.putExtra("WEAR_DEVICE_CAPBILITY", deviceCapability.isSupportGetHighAndMiddleSport());
            context.sendBroadcast(intent, Consts.f2803a);
        }
    }
}
