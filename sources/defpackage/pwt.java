package defpackage;

import android.content.res.Resources;
import android.text.TextUtils;
import androidx.arch.core.util.Function;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.coresleep.SleepBreatheBannerProvider;
import defpackage.fcm;
import defpackage.fco;
import defpackage.pwt;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/* loaded from: classes6.dex */
public class pwt {

    /* renamed from: a, reason: collision with root package name */
    private static final Map<String, Integer[]> f16314a;
    private static final Map<String, Integer[]> e;
    private static Properties d = new Properties();
    private static Properties b = new Properties();

    static {
        HashMap hashMap = new HashMap();
        f16314a = hashMap;
        HashMap hashMap2 = new HashMap();
        e = hashMap2;
        hashMap.put("06100000", new Integer[]{Integer.valueOf(R$string.IDS_intel_sleep_interpretation_library_061000003_nmpa), Integer.valueOf(R$string.IDS_intel_sleep_interpretation_library_061000004_nmpa)});
        hashMap.put("06100100", new Integer[]{Integer.valueOf(R$string.IDS_intel_sleep_interpretation_library_061001003_nmpa), Integer.valueOf(R$string.IDS_intel_sleep_interpretation_library_061001004_nmpa)});
        hashMap.put("06100200", new Integer[]{Integer.valueOf(R$string.IDS_intel_sleep_interpretation_library_061002003_nmpa), Integer.valueOf(R$string.IDS_intel_sleep_interpretation_library_061002004_nmpa)});
        hashMap.put("06100300", new Integer[]{Integer.valueOf(R$string.IDS_intel_sleep_interpretation_library_061003003_nmpa), Integer.valueOf(R$string.IDS_intel_sleep_interpretation_library_061003004_nmpa)});
        hashMap2.put("06100000", new Integer[]{Integer.valueOf(R$string.IDS_intel_sleep_interpretation_library_061000003), Integer.valueOf(R$string.IDS_intel_sleep_interpretation_library_061000004)});
        hashMap2.put("06100100", new Integer[]{Integer.valueOf(R$string.IDS_intel_sleep_interpretation_library_061001003), Integer.valueOf(R$string.IDS_intel_sleep_interpretation_library_061001004)});
        hashMap2.put("06100200", new Integer[]{Integer.valueOf(R$string.IDS_intel_sleep_interpretation_library_061002003), Integer.valueOf(R$string.IDS_intel_sleep_interpretation_library_061002004)});
        hashMap2.put("06100300", new Integer[]{Integer.valueOf(R$string.IDS_intel_sleep_interpretation_library_061003003), Integer.valueOf(R$string.IDS_intel_sleep_interpretation_library_061003004)});
    }

    private pwt() {
    }

    public static int e(int i) {
        int b2;
        synchronized (pwt.class) {
            if (d.isEmpty()) {
                d = sdo.b("sleep/sleepSuggest.properties");
            }
            String property = d.getProperty(String.valueOf(i));
            b2 = property != null ? nsf.b(property, "string", R$string.class) : -1;
            if (b2 == -1) {
                b2 = R$string.IDS_details_sleep_content_4_no_harvard;
            }
        }
        return b2;
    }

    public static int a(String str) {
        synchronized (pwt.class) {
            if (TextUtils.isEmpty(str)) {
                return -1;
            }
            if (b.isEmpty()) {
                b = sdo.b("sleep/intelsleeplibrary.properties");
            }
            String property = b.getProperty(str);
            return property != null ? nsf.b(property, "string", R$string.class) : -1;
        }
    }

    public static int[] d() {
        return BaseApplication.getContext().getResources().getIntArray(R.array._2130968645_res_0x7f040045);
    }

    public static int[] a() {
        return BaseApplication.getContext().getResources().getIntArray(R.array._2130968646_res_0x7f040046);
    }

    public static int[] i() {
        return BaseApplication.getContext().getResources().getIntArray(R.array._2130968648_res_0x7f040048);
    }

    public static int[] f() {
        return BaseApplication.getContext().getResources().getIntArray(R.array._2130968649_res_0x7f040049);
    }

    public static int[] c() {
        return BaseApplication.getContext().getResources().getIntArray(R.array._2130968644_res_0x7f040044);
    }

    public static int[] b() {
        return BaseApplication.getContext().getResources().getIntArray(R.array._2130968643_res_0x7f040043);
    }

    public static int[] e() {
        return BaseApplication.getContext().getResources().getIntArray(R.array._2130968642_res_0x7f040042);
    }

    public static int[] g() {
        return BaseApplication.getContext().getResources().getIntArray(R.array._2130968647_res_0x7f040047);
    }

    public static String a(fda fdaVar) {
        if (fdaVar == null) {
            return "";
        }
        ArrayList arrayList = new ArrayList(Collections.nCopies(4, ""));
        int a2 = a(fdaVar.n());
        if (a2 != -1) {
            arrayList.set(0, nsf.h(a2));
        }
        arrayList.set(1, d(fdaVar));
        int a3 = a(fdaVar.m());
        if (a3 != -1) {
            arrayList.set(2, nsf.h(a3));
        }
        int a4 = a(fdaVar.f());
        if (a4 != -1) {
            arrayList.set(3, nsf.h(a4));
        }
        return nsf.b(R$string.IDS_three_parts, arrayList.get(0), arrayList.get(1), (String) (TextUtils.isEmpty((CharSequence) arrayList.get(2)) ? arrayList.get(3) : arrayList.get(2)));
    }

    public static String a(String str, List<fcm> list) {
        return a(list, str);
    }

    public static String a(List<fcm> list, String str) {
        int a2 = a(str);
        if (a2 == -1) {
            return "";
        }
        if (koq.b(list)) {
            return BaseApplication.getContext().getString(a2);
        }
        List<String> b2 = b(list, new Function() { // from class: com.huawei.ui.main.stories.fitness.interactors.SleepAdviceMapHelper$$ExternalSyntheticLambda0
            @Override // androidx.arch.core.util.Function
            public final Object apply(Object obj) {
                String b3;
                b3 = pwt.b(r1.e(), ((fcm) obj).d());
                return b3;
            }
        });
        b2.addAll(qms.a(str));
        return nsf.b(a2, b2.toArray());
    }

    public static String b(fda fdaVar) {
        if (fdaVar == null) {
            return "";
        }
        ArrayList arrayList = new ArrayList(Collections.nCopies(2, ""));
        arrayList.set(0, b(fdaVar.b(), fdaVar.d()));
        int a2 = a(fdaVar.a());
        if (a2 != -1) {
            arrayList.set(1, nsf.h(a2));
        }
        return c(arrayList);
    }

    private static String c(List<String> list) {
        if (list.size() != 2) {
            ReleaseLogUtil.d("R_Sleep_SleepAdviceMapHelper", "Invaild string list length: ", Integer.valueOf(list.size()));
            return "";
        }
        if ("".equals(list.get(0)) && "".equals(list.get(1))) {
            return "";
        }
        if (!"".equals(list.get(0)) && !"".equals(list.get(1))) {
            return nsf.b(R$string.IDS_two_parts, list.get(0), list.get(1));
        }
        return "".equals(list.get(0)) ? list.get(1) : list.get(0);
    }

    public static String b(List<fco> list, String str) {
        Map<String, Integer[]> map = SleepBreatheBannerProvider.b() ? f16314a : e;
        if (map.containsKey(str) && !koq.b(list)) {
            int c = list.get(0).c() - 3;
            if (c < 0 || c >= map.get(str).length) {
                ReleaseLogUtil.e("R_Sleep_SleepAdviceMapHelper", "error val: ", Integer.valueOf(c));
                return "";
            }
            return nsf.h(map.get(str)[c].intValue());
        }
        int a2 = a(str);
        if (a2 == -1) {
            return "";
        }
        if (koq.b(list)) {
            List<String> a3 = qms.a(str);
            if (koq.b(a3)) {
                return BaseApplication.getContext().getString(a2);
            }
            return BaseApplication.getContext().getString(a2, a3.toArray());
        }
        return BaseApplication.getContext().getString(a2, b(list, new Function() { // from class: com.huawei.ui.main.stories.fitness.interactors.SleepAdviceMapHelper$$ExternalSyntheticLambda1
            @Override // androidx.arch.core.util.Function
            public final Object apply(Object obj) {
                String b2;
                b2 = pwt.b(r1.c(), ((fco) obj).a());
                return b2;
            }
        }).toArray());
    }

    private static String d(fda fdaVar) {
        return fdaVar == null ? "" : a(fdaVar.g(), fdaVar.i());
    }

    public static String e(fci fciVar) {
        int a2 = a(fciVar.c());
        if (a2 == -1) {
            return null;
        }
        List<String> a3 = qms.a(fciVar.c());
        if (koq.b(a3)) {
            return BaseApplication.getContext().getString(a2);
        }
        return BaseApplication.getContext().getString(a2, a3.toArray());
    }

    private static <T> List<String> b(List<T> list, Function<T, String> function) {
        ArrayList arrayList = new ArrayList();
        if (koq.b(list)) {
            return arrayList;
        }
        for (T t : list) {
            if (t != null) {
                arrayList.add(function.apply(t));
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(int i, int i2) {
        Resources resources = BaseApplication.getContext().getResources();
        double d2 = i;
        String e2 = UnitUtil.e(d2, 1, 0);
        if (i2 != 10) {
            switch (i2) {
                case 0:
                    return UnitUtil.e(d2, 1, 0);
                case 1:
                    return UnitUtil.e(d2, 2, 0);
                case 2:
                    return UnitUtil.a("HH:mm", i * 1000);
                case 3:
                    return resources.getQuantityString(R.plurals._2130903199_res_0x7f03009f, i, e2);
                case 4:
                    return resources.getQuantityString(R.plurals._2130903200_res_0x7f0300a0, i, e2);
                case 5:
                    return resources.getQuantityString(R.plurals.IDS_user_profile_achieve_num_day, i, e2);
                case 6:
                    return resources.getQuantityString(R.plurals._2130903394_res_0x7f030162, i, e2);
                case 7:
                    return resources.getQuantityString(R.plurals._2130903393_res_0x7f030161, i, e2);
                default:
                    return "";
            }
        }
        return resources.getQuantityString(R.plurals._2130903418_res_0x7f03017a, i, e2);
    }
}
