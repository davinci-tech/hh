package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes5.dex */
public class jfx {
    public static void d(cvc cvcVar, cuw cuwVar, HashMap<String, Integer> hashMap, Map<Integer, ArrayList<String>> map) {
        LogUtil.a("PluginDeviceInfoManager", "enter loadPluginDeviceInfo");
        a(cvcVar, cuwVar);
        c(cvcVar, cuwVar);
        cvj f = cvcVar.f();
        if (f == null) {
            return;
        }
        b(f, cuwVar);
        b(cvcVar, f, hashMap, map);
    }

    private static void a(cvc cvcVar, cuw cuwVar) {
        if (cvcVar.e() != null) {
            cuwVar.h(cvcVar.e());
        }
        if (cvcVar.f() == null) {
            return;
        }
        String ae = cvcVar.f().ae();
        if ("".equals(ae)) {
            return;
        }
        cuwVar.a(ae);
        LogUtil.a("PluginDeviceInfoManager", " deviceName : ", cuwVar.f());
    }

    private static void c(cvc cvcVar, cuw cuwVar) {
        if (cvcVar.f() == null) {
            return;
        }
        String i = cvcVar.f().i();
        if (!"".equals(i)) {
            cuwVar.d(i);
            LogUtil.a("PluginDeviceInfoManager", " briefDescription : ", cuwVar.f());
        }
        ArrayList<String> ad = cvcVar.f().ad();
        if (ad.size() > 0) {
            cuwVar.b(ad);
            LogUtil.a("PluginDeviceInfoManager", " deviceIntro : ", cuwVar.j().toString());
        }
    }

    private static void b(cvj cvjVar, cuw cuwVar) {
        e(cvjVar, cuwVar);
        d(cvjVar, cuwVar);
        c(cvjVar, cuwVar);
        a(cvjVar, cuwVar);
    }

    private static void a(cvj cvjVar, cuw cuwVar) {
        cuwVar.d(cvjVar.bz());
        cuwVar.e(cvjVar.bu());
        cuwVar.b(cvjVar.bv());
    }

    private static void e(cvj cvjVar, cuw cuwVar) {
        LogUtil.a("PluginDeviceInfoManager", "enter loadWearDeviceInfoOne");
        String f = cvjVar.f();
        if (!TextUtils.isEmpty(f)) {
            try {
                cuwVar.f(Integer.parseInt(f));
                LogUtil.a("PluginDeviceInfoManager", " manu type : ", Integer.valueOf(cuwVar.p()));
            } catch (NumberFormatException e) {
                LogUtil.b("PluginDeviceInfoManager", e.getMessage());
            }
        }
        String bh = cvjVar.bh();
        if (!"".equals(bh)) {
            cuwVar.f(bh);
            LogUtil.a("PluginDeviceInfoManager", " pairGuideList : ", cuwVar.y());
        }
        int au = cvjVar.au();
        if (au != -1) {
            cuwVar.g(au);
            LogUtil.a("PluginDeviceInfoManager", " hiType : ", Integer.valueOf(cuwVar.o()));
        }
        int af = cvjVar.af();
        if (af != -1) {
            cuwVar.b(af);
            LogUtil.a("PluginDeviceInfoManager", " deviceType : ", Integer.valueOf(cuwVar.m()));
        }
        int h = cvjVar.h();
        if (h != -1) {
            cuwVar.a(h);
            LogUtil.a("PluginDeviceInfoManager", " bluetoothType : ", Integer.valueOf(cuwVar.d()));
        }
        int ah = cvjVar.ah();
        if (ah != -1 && ah != 1) {
            cuwVar.c((Boolean) false);
            LogUtil.a("PluginDeviceInfoManager", "isBand : ", Boolean.valueOf(cuwVar.s()));
        }
        int ag = cvjVar.ag();
        if (ag != -1) {
            cuwVar.d(ag);
            LogUtil.a("PluginDeviceInfoManager", " deviceShapes : ", Integer.valueOf(cuwVar.n()));
        }
    }

    private static void d(cvj cvjVar, cuw cuwVar) {
        int ah = cvjVar.ah();
        if (ah != -1) {
            cuwVar.c(ah);
            LogUtil.a("PluginDeviceInfoManager", " deviceType : ", Integer.valueOf(cuwVar.m()));
        }
        Map<String, String> av = cvjVar.av();
        if (av != null) {
            cuwVar.d(av);
            LogUtil.a("PluginDeviceInfoManager", " helpUrl : ", cuwVar.l());
        }
        Map<String, String> az = cvjVar.az();
        if (az != null) {
            cuwVar.b(az);
            LogUtil.a("PluginDeviceInfoManager", " honorHelpUrl : ", cuwVar.r());
        }
        Map<String, String> br = cvjVar.br();
        if (br != null) {
            cuwVar.c(br);
            LogUtil.a("PluginDeviceInfoManager", " upgradeCycle : ", cuwVar.x());
        }
        String l = cvjVar.l();
        if (l != null) {
            cuwVar.e(l);
            LogUtil.a("PluginDeviceInfoManager", " clubUrl : ", cuwVar.g());
        }
        String at = cvjVar.at();
        if (at != null) {
            cuwVar.b(at);
            LogUtil.a("PluginDeviceInfoManager", " honorClubUrl : ", cuwVar.t());
        }
        String bq = cvjVar.bq();
        if (!"".equals(bq)) {
            cuwVar.j(bq);
            LogUtil.a("PluginDeviceInfoManager", " wearType : ", cuwVar.ab());
        }
        Map<String, Integer> d = cvjVar.d();
        if (d != null) {
            cuwVar.a(d);
            LogUtil.a("PluginDeviceInfoManager", " appVersion : ", cuwVar.a());
        }
    }

    private static void c(cvj cvjVar, cuw cuwVar) {
        if (cvjVar.bo()) {
            cuwVar.a(true);
            LogUtil.a("PluginDeviceInfoManager", " brAndBle : ", Boolean.valueOf(cuwVar.ac()));
        }
        if (cvjVar.cb()) {
            cuwVar.h(true);
            LogUtil.a("PluginDeviceInfoManager", " supportMidWare : ", Boolean.valueOf(cuwVar.ae()));
        }
        if (cvjVar.bs()) {
            cuwVar.c(true);
            LogUtil.a("PluginDeviceInfoManager", " isSupportEarMuffGuide is true");
        }
        String bb = cvjVar.bb();
        if (!"".equals(bb)) {
            cuwVar.g(bb);
            LogUtil.a("PluginDeviceInfoManager", " npsName : ", cuwVar.q());
        }
        String e = cvjVar.e();
        if (!"".equals(e)) {
            cuwVar.c(e);
            LogUtil.a("PluginDeviceInfoManager", " biName : ", cuwVar.c());
        }
        int bk = cvjVar.bk();
        if (bk != -1) {
            cuwVar.i(bk);
            LogUtil.a("PluginDeviceInfoManager", " isSupportWlanAnto : ", Integer.valueOf(cuwVar.u()));
        }
    }

    private static void b(cvc cvcVar, cvj cvjVar, HashMap<String, Integer> hashMap, Map<Integer, ArrayList<String>> map) {
        int af;
        if (hashMap.containsKey(cvcVar.e())) {
            af = hashMap.get(cvcVar.e()).intValue();
        } else {
            af = cvcVar.f().af();
        }
        if (-1 != af) {
            ArrayList<String> j = cvjVar.j();
            if (j == null || j.size() <= 0) {
                LogUtil.h("PluginDeviceInfoManager", " bluetooth Names is null or size is 0");
                return;
            }
            ArrayList<String> arrayList = new ArrayList<>(16);
            Iterator<String> it = j.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().toUpperCase(Locale.ENGLISH));
            }
            if (map != null) {
                map.put(Integer.valueOf(af), arrayList);
                LogUtil.a("PluginDeviceInfoManager", " bluetooth Names : ", map.get(Integer.valueOf(af)));
            }
        }
    }
}
