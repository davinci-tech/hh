package defpackage;

import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import com.huawei.health.R;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import health.compact.a.CompileParameterUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class qqv {
    public static List<qkd> e(cfe cfeVar, boolean z, boolean z2) {
        ArrayList arrayList = new ArrayList(31);
        if (cfeVar != null) {
            e((ArrayList<qkd>) arrayList, cfeVar, z2);
            i(arrayList, cfeVar, z2);
            k(arrayList, cfeVar, z2);
            t(arrayList, cfeVar, z2);
            l(arrayList, cfeVar, z2);
            s(arrayList, cfeVar, z2);
            a(arrayList, cfeVar, z2);
            b((ArrayList<qkd>) arrayList, cfeVar, z2);
            d(arrayList, cfeVar, z2);
            n(arrayList, cfeVar, z2);
            f(arrayList, cfeVar, z2);
            m(arrayList, cfeVar, z2);
            j(arrayList, cfeVar, z2);
            h(arrayList, cfeVar, z2);
            c(arrayList, cfeVar, z2);
            g(arrayList, cfeVar, z2);
            if (CompileParameterUtil.a("SUPPORT_WIFI_WEIGHT_DEVICE", false)) {
                o(arrayList, cfeVar, z);
            }
        }
        return arrayList;
    }

    private static void b(double d, qkd qkdVar, int i) {
        double c = UnitUtil.c(d, i);
        qkdVar.b((CharSequence) UnitUtil.e(c, 1, i));
        qkdVar.b(qsj.e(c, false));
    }

    private static void e(ArrayList<qkd> arrayList, cfe cfeVar, boolean z) {
        if (cfeVar.isVisible(2, z)) {
            double j = cfeVar.j();
            if (!dph.e(j, Utils.o())) {
                j = doj.a(cfeVar.ax(), cfeVar.t());
            }
            qkd qkdVar = new qkd();
            qkdVar.a(j);
            qkdVar.a(2);
            qkdVar.b(R$string.IDS_hw_show_BMI);
            qkdVar.b((CharSequence) UnitUtil.e(j, 1, 1));
            qkdVar.b(BaseApplication.getContext().getResources().getString(R$string.IDS_hw_health_empty_unit));
            arrayList.add(qkdVar);
        }
    }

    private static void i(ArrayList<qkd> arrayList, cfe cfeVar, boolean z) {
        if (cfeVar.isVisible(1, z)) {
            double a2 = cfeVar.a();
            qkd qkdVar = new qkd();
            qkdVar.a(a2);
            qkdVar.a(1);
            qkdVar.b(R$string.IDS_hw_health_show_healthdata_bodyfat_rate);
            qkdVar.b(dHy_(a2));
            arrayList.add(qkdVar);
        }
    }

    private static void k(ArrayList<qkd> arrayList, cfe cfeVar, boolean z) {
        if (cfeVar.isVisible(10, z)) {
            double aj = cfeVar.aj();
            qkd qkdVar = new qkd();
            qkdVar.a(aj);
            qkdVar.a(10);
            qkdVar.b(R$string.IDS_hw_show_skeletal_muscle_mass);
            b(aj, qkdVar, 1);
            arrayList.add(qkdVar);
        }
    }

    private static void t(ArrayList<qkd> arrayList, cfe cfeVar, boolean z) {
        if (cfeVar.isVisible(5, z)) {
            double s = cfeVar.s();
            qkd qkdVar = new qkd();
            qkdVar.a(s);
            qkdVar.a(5);
            qkdVar.b(R$string.IDS_hw_show_haslet);
            qkdVar.b((CharSequence) UnitUtil.e(s, 1, 1));
            qkdVar.b(BaseApplication.getContext().getResources().getString(R$string.IDS_hw_show_haslet_unit));
            arrayList.add(qkdVar);
        }
    }

    private static void l(ArrayList<qkd> arrayList, cfe cfeVar, boolean z) {
        if (cfeVar.isVisible(26, z)) {
            double af = cfeVar.af();
            qkd qkdVar = new qkd();
            qkdVar.a(af);
            qkdVar.a(26);
            qkdVar.b(R$string.IDS_weight_limb_skeletal_muscle_index);
            qkdVar.b((CharSequence) UnitUtil.e(af, 1, 1));
            qkdVar.b(BaseApplication.getContext().getResources().getString(R$string.IDS_weight_kg_square));
            arrayList.add(qkdVar);
        }
    }

    private static void s(ArrayList<qkd> arrayList, cfe cfeVar, boolean z) {
        if (cfeVar.isVisible(25, z)) {
            double as = cfeVar.as();
            double ao = cfeVar.ao();
            int i = R$string.IDS_weight_spectral_waist_to_hip_ratio;
            if (ao > 0.0d) {
                i = R$string.IDS_weight_waist_to_hip_ratio;
                as = ao;
            }
            qkd qkdVar = new qkd();
            qkdVar.a(as);
            qkdVar.a(25);
            qkdVar.b(i);
            qkdVar.b((CharSequence) UnitUtil.e(as, 1, 2));
            qkdVar.b(BaseApplication.getContext().getResources().getString(R$string.IDS_hw_health_empty_unit));
            arrayList.add(qkdVar);
        }
    }

    private static void a(ArrayList<qkd> arrayList, cfe cfeVar, boolean z) {
        if (cfeVar.isVisible(13, z)) {
            int e = qsj.e(cfeVar);
            qkd qkdVar = new qkd();
            qkdVar.a(e);
            qkdVar.a(13);
            qkdVar.b(R$string.IDS_hw_weight_body_type);
            qkdVar.b((CharSequence) qqy.a(e));
            qkdVar.b(BaseApplication.getContext().getResources().getString(R$string.IDS_hw_health_empty_unit));
            arrayList.add(qkdVar);
        }
    }

    private static void b(ArrayList<qkd> arrayList, cfe cfeVar, boolean z) {
        if (cfeVar.isVisible(27, z)) {
            int d = doj.d(cfeVar.an(), cfeVar.f());
            qkd qkdVar = new qkd();
            qkdVar.a(cfeVar.f());
            qkdVar.a(27);
            qkdVar.b(R$string.IDS_hw_weight_body_shape);
            qkdVar.b((CharSequence) qqy.d(d));
            qkdVar.b(BaseApplication.getContext().getResources().getString(R$string.IDS_hw_health_empty_unit));
            arrayList.add(qkdVar);
        }
    }

    private static void d(ArrayList<qkd> arrayList, cfe cfeVar, boolean z) {
        if (cfeVar.isVisible(4, z)) {
            double d = cfeVar.d();
            qkd qkdVar = new qkd();
            qkdVar.a(d);
            qkdVar.a(4);
            qkdVar.b(R$string.IDS_hw_show_metabolism);
            qkdVar.b((CharSequence) UnitUtil.e(d, 1, 0));
            qkdVar.b(BaseApplication.getContext().getResources().getString(R$string.IDS_hw_show_sport_cal_unit_new));
            arrayList.add(qkdVar);
        }
    }

    private static void n(ArrayList<qkd> arrayList, cfe cfeVar, boolean z) {
        if (cfeVar.isVisible(3, z)) {
            double ap = cfeVar.ap();
            if (!dph.t(ap)) {
                ap = cfeVar.al();
            }
            qkd qkdVar = new qkd();
            qkdVar.a(ap);
            qkdVar.a(3);
            qkdVar.b(R$string.IDS_hw_show_water);
            qkdVar.b(dHy_(ap));
            arrayList.add(qkdVar);
        }
    }

    private static void f(ArrayList<qkd> arrayList, cfe cfeVar, boolean z) {
        if (cfeVar.isVisible(7, z)) {
            double i = cfeVar.i();
            qkd qkdVar = new qkd();
            qkdVar.a(i);
            qkdVar.a(7);
            qkdVar.b(R$string.IDS_hw_show_bone);
            b(i, qkdVar, cfeVar.getFractionDigitByType(7));
            arrayList.add(qkdVar);
        }
    }

    private static void m(ArrayList<qkd> arrayList, cfe cfeVar, boolean z) {
        if (cfeVar.isVisible(8, z)) {
            double ap = cfeVar.ap();
            if (!dph.t(ap)) {
                ap = cfeVar.al();
            }
            double d = doj.d(cfeVar.ax(), ap, cfeVar.a(), cfeVar.i(), cfeVar.getFractionDigitByType(0));
            qkd qkdVar = new qkd();
            qkdVar.a(d);
            qkdVar.a(8);
            qkdVar.b(R$string.IDS_hw_show_protein);
            qkdVar.b(dHy_(d));
            arrayList.add(qkdVar);
        }
    }

    private static void j(ArrayList<qkd> arrayList, cfe cfeVar, boolean z) {
        if (cfeVar.isVisible(6, z)) {
            double z2 = cfeVar.z();
            qkd qkdVar = new qkd();
            qkdVar.a(z2);
            qkdVar.a(6);
            qkdVar.b(R$string.IDS_hw_show_muscle);
            b(z2, qkdVar, 1);
            arrayList.add(qkdVar);
        }
    }

    private static void h(ArrayList<qkd> arrayList, cfe cfeVar, boolean z) {
        if (cfeVar.isVisible(14, z)) {
            double e = doj.e(cfeVar.a(), cfeVar.ax());
            qkd qkdVar = new qkd();
            qkdVar.a(e);
            qkdVar.a(14);
            qkdVar.b(R$string.IDS_weight_fat_free);
            b(e, qkdVar, 1);
            arrayList.add(qkdVar);
        }
    }

    private static void c(ArrayList<qkd> arrayList, cfe cfeVar, boolean z) {
        if (cfeVar.isVisible(9, z)) {
            double b = cfeVar.b();
            qkd qkdVar = new qkd();
            qkdVar.a(b);
            qkdVar.a(9);
            qkdVar.b(R$string.IDS_hw_show_bodyage);
            qkdVar.b((CharSequence) UnitUtil.e(b, 1, 0));
            qkdVar.b(BaseApplication.getContext().getResources().getString(R$string.IDS_hw_health_empty_unit));
            arrayList.add(qkdVar);
        }
    }

    private static void g(ArrayList<qkd> arrayList, cfe cfeVar, boolean z) {
        if (cfeVar.isVisible(11, z)) {
            double p = cfeVar.p();
            qkd qkdVar = new qkd();
            qkdVar.a(p);
            qkdVar.a(11);
            qkdVar.b(R$string.IDS_main_watch_heart_rate_string);
            qkdVar.b((CharSequence) UnitUtil.e(p, 1, 0));
            qkdVar.b(BaseApplication.getContext().getResources().getString(R$string.IDS_device_measure_pressure_result_heart_rate_unit));
            arrayList.add(qkdVar);
        }
    }

    private static void o(ArrayList<qkd> arrayList, cfe cfeVar, boolean z) {
        int l = cfeVar.l();
        boolean d = d(l);
        boolean z2 = true;
        boolean z3 = l == 82 || l == 85;
        boolean z4 = l == 84 || l == 86;
        if (!z3 && !z4) {
            z2 = false;
        }
        if (z && z2) {
            if (!b(arrayList, cfeVar) && csf.d() && !d) {
                qkd qkdVar = new qkd();
                qkdVar.a(12);
                qkdVar.b(R$string.IDS_hw_show_pressure_index);
                qkdVar.b((CharSequence) BaseApplication.getContext().getResources().getString(R$string.IDS_hw_health_empty_unit));
                qkdVar.b(BaseApplication.getContext().getResources().getString(R$string.IDS_device_wifi_no_pressure_calibrate_msg));
                qkdVar.d(false);
                arrayList.add(qkdVar);
                return;
            }
            LogUtil.h("HealthWeight_BodyIndexConstructUtils", "Exist pressure value or don't bind wifi device or isPressureCalibrated");
            return;
        }
        b(arrayList, cfeVar);
    }

    private static boolean d(int i) {
        String str;
        cfi currentUser = MultiUsersManager.INSTANCE.getCurrentUser();
        cfi mainUser = MultiUsersManager.INSTANCE.getMainUser();
        String i2 = currentUser.i();
        String i3 = mainUser.i();
        if (TextUtils.isEmpty(i2) || TextUtils.isEmpty(i3)) {
            i2 = "";
        } else if (i2.equals(i3)) {
            LogUtil.a("HealthWeight_BodyIndexConstructUtils", "Current User is main user");
            i2 = "0";
        }
        if (i == 82 || i == 85) {
            str = "pressure_calibrate_hrv_userinfo_e4b0b1d5-2003-4d88-8b5f-c4f64542040b";
        } else if (i == 84 || i == 86) {
            str = "pressure_calibrate_hrv_userinfo_a8ba095d-4123-43c4-a30a-0240011c58de";
        } else {
            LogUtil.h("HealthWeight_BodyIndexConstructUtils", "no fliter");
            str = "pressure_calibrate_hrv_userinfo_";
        }
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), str);
        if (TextUtils.isEmpty(b)) {
            return false;
        }
        LogUtil.a("HealthWeight_BodyIndexConstructUtils", "hrvUserInfo details: ", b);
        for (String str2 : b.split(";")) {
            if (i2.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    private static boolean b(ArrayList<qkd> arrayList, cfe cfeVar) {
        double ad = cfeVar.ad();
        if (dph.m(ad) && ad > 0.0d) {
            qkd qkdVar = new qkd();
            qkdVar.a(ad);
            qkdVar.a(12);
            qkdVar.b(R$string.IDS_hw_show_pressure_index);
            qkdVar.b((CharSequence) UnitUtil.e(ad, 1, 0));
            qkdVar.b(BaseApplication.getContext().getResources().getString(R$string.IDS_hw_health_empty_unit));
            qkdVar.d(true);
            arrayList.add(qkdVar);
            return true;
        }
        LogUtil.h("HealthWeight_BodyIndexConstructUtils", "showPressureData() don't add small pressure card...");
        return false;
    }

    private static SpannableString dHy_(double d) {
        String e = UnitUtil.e(d, 1, 1);
        SpannableString spannableString = new SpannableString(UnitUtil.e(d, 2, 1));
        int indexOf = spannableString.toString().indexOf(e);
        if (indexOf != -1) {
            spannableString.setSpan(new TextAppearanceSpan(BaseApplication.getContext(), R.style.weight_body_index_unit_text_style), 0, spannableString.length(), 33);
            spannableString.setSpan(new TextAppearanceSpan(BaseApplication.getContext(), R.style.weight_body_index_value_text_style), indexOf, e.length() + indexOf, 33);
        }
        return spannableString;
    }
}
