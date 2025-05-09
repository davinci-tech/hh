package defpackage;

import android.util.SparseArray;
import com.huawei.basichealthmodel.R$plurals;
import com.huawei.basichealthmodel.R$string;
import com.huawei.basichealthmodel.bean.ChallengeConfigBean;
import com.huawei.health.R;
import com.huawei.hiai.awareness.AwarenessConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes8.dex */
public class ban {

    /* renamed from: a, reason: collision with root package name */
    private static final Map<Integer, Integer> f304a;
    private static final Map<Integer, Integer> b;
    private static final Map<Integer, Integer> c;
    private static final Map<Integer, Integer> d;
    private static final Map<Integer, Integer> e;

    static {
        HashMap hashMap = new HashMap();
        f304a = hashMap;
        HashMap hashMap2 = new HashMap();
        b = hashMap2;
        HashMap hashMap3 = new HashMap();
        e = hashMap3;
        HashMap hashMap4 = new HashMap();
        d = hashMap4;
        HashMap hashMap5 = new HashMap();
        c = hashMap5;
        Integer valueOf = Integer.valueOf(AwarenessConstants.ERROR_NO_PERMISSION_CODE);
        hashMap.put(valueOf, Integer.valueOf(R$string.IDS_health_goal_free_matching));
        Integer valueOf2 = Integer.valueOf(AwarenessConstants.ERROR_INVALID_FREQUENCY_CODE);
        hashMap.put(valueOf2, Integer.valueOf(R$string.IDS_challenge_anxiety_name));
        Integer valueOf3 = Integer.valueOf(AwarenessConstants.ERROR_TIMEOUT_CODE);
        hashMap.put(valueOf3, Integer.valueOf(R$string.IDS_health_goal_weight));
        Integer valueOf4 = Integer.valueOf(AwarenessConstants.ERROR_UNKNOWN_CODE);
        hashMap.put(valueOf4, Integer.valueOf(R$string.IDS_challenge_sleep_name));
        Integer valueOf5 = Integer.valueOf(AwarenessConstants.ERROR_LIMITED_REGISTRY_CODE);
        hashMap.put(valueOf5, Integer.valueOf(R$string.IDS_challenge_immunity_name));
        Integer valueOf6 = Integer.valueOf(AwarenessConstants.ERROR_CANCEL_REGISTRY_CODE);
        hashMap.put(valueOf6, Integer.valueOf(R$string.IDS_health_goal_blood_pressure));
        Integer valueOf7 = Integer.valueOf(AwarenessConstants.ERROR_PARAMETER_CODE);
        hashMap.put(valueOf7, Integer.valueOf(R$string.IDS_health_goal_blood_pressure));
        Integer valueOf8 = Integer.valueOf(AwarenessConstants.ERROR_REGISTER_SAME_FENCE_CODE);
        hashMap.put(valueOf8, Integer.valueOf(R$string.IDS_health_goal_blood_pressure));
        hashMap2.put(valueOf, Integer.valueOf(R$string.IDS_health_goal_free_matching_join));
        hashMap2.put(valueOf2, Integer.valueOf(R$string.IDS_plan_anxiety_title));
        hashMap2.put(valueOf3, Integer.valueOf(R$string.IDS_plan_weight_title_new));
        hashMap2.put(valueOf4, Integer.valueOf(R$string.IDS_plan_sleep_title));
        hashMap2.put(valueOf5, Integer.valueOf(R$string.IDS_plan_immunity_title));
        hashMap2.put(valueOf6, Integer.valueOf(R$string.IDS_blood_pressure_plan_title));
        hashMap2.put(valueOf7, Integer.valueOf(R$string.IDS_blood_pressure_plan_title));
        hashMap2.put(valueOf8, Integer.valueOf(R$string.IDS_blood_pressure_plan_title));
        hashMap3.put(valueOf2, Integer.valueOf(R$string.IDS_share_relieve_anxiety));
        hashMap3.put(valueOf3, Integer.valueOf(R$string.IDS_share_managing_weight));
        hashMap3.put(valueOf4, Integer.valueOf(R$string.IDS_share_improves_sleep));
        hashMap3.put(valueOf5, Integer.valueOf(R$string.IDS_share_immunity));
        hashMap4.put(valueOf, Integer.valueOf(R$string.IDS_health_goal_free_matching));
        hashMap4.put(valueOf2, Integer.valueOf(R$string.IDS_challenge_anxiety_name));
        hashMap4.put(valueOf3, Integer.valueOf(R$string.IDS_health_goal_weight));
        hashMap4.put(valueOf4, Integer.valueOf(R$string.IDS_challenge_sleep_name));
        hashMap4.put(valueOf5, Integer.valueOf(R$string.IDS_challenge_immunity_name));
        hashMap4.put(valueOf6, Integer.valueOf(R$string.IDS_health_goal_blood_pressure));
        hashMap5.put(valueOf, Integer.valueOf(R$string.IDS_health_goal_free_matching_desc));
        hashMap5.put(valueOf2, Integer.valueOf(R$string.IDS_health_goal_anxiety_desc));
        hashMap5.put(valueOf3, Integer.valueOf(R$string.IDS_health_goal_weight_desc));
        hashMap5.put(valueOf4, Integer.valueOf(R$string.IDS_health_goal_sleep_desc));
        hashMap5.put(valueOf5, Integer.valueOf(R$string.IDS_health_goal_immunity_desc));
        hashMap5.put(valueOf6, Integer.valueOf(R$string.IDS_health_goal_blood_pressure_desc));
    }

    public static int b() {
        SparseArray<ChallengeConfigBean> kB_ = awq.e().kB_();
        if (kB_ == null) {
            LogUtil.h("HealthLife_HealthChallengeUtil", "load sparseArray is null");
            return 0;
        }
        ArrayList arrayList = new ArrayList(16);
        for (int i = 0; i < kB_.size(); i++) {
            int keyAt = kB_.keyAt(i);
            if (keyAt != 200001) {
                arrayList.add(Integer.valueOf(keyAt));
            }
        }
        if (koq.b(arrayList)) {
            LogUtil.h("HealthLife_HealthChallengeUtil", "load arrayList is empty");
            return 0;
        }
        return ((Integer) arrayList.get(nsg.b(arrayList.size()))).intValue();
    }

    public static ChallengeConfigBean b(int i) {
        return awq.e().b(i);
    }

    public static String b(int i, int i2) {
        Map<Integer, Integer> map = b;
        return map.containsKey(Integer.valueOf(i)) ? String.format(Locale.ENGLISH, nsf.h(map.get(Integer.valueOf(i)).intValue()), nsf.a(R$plurals.IDS_day_no_space, i2, Integer.valueOf(i2))) : "";
    }

    public static String c(int i) {
        Map<Integer, Integer> map = f304a;
        return map.containsKey(Integer.valueOf(i)) ? nsf.h(map.get(Integer.valueOf(i)).intValue()) : "";
    }

    public static String a(int i) {
        Map<Integer, Integer> map = e;
        return map.containsKey(Integer.valueOf(i)) ? nsf.h(map.get(Integer.valueOf(i)).intValue()) : "";
    }

    public static String a() {
        return bad.b().c() + "image/health_model_challenge_share.webp";
    }

    public static List<String> e(int i) {
        LogUtil.a("HealthLife_HealthChallengeUtil", "isPlanTask challengeId= ", Integer.valueOf(i));
        ChallengeConfigBean b2 = b(i);
        if (b2 == null) {
            return new ArrayList();
        }
        return b2.getChallengeTaskId();
    }

    public static boolean d(int i, int i2) {
        LogUtil.a("HealthLife_HealthChallengeUtil", "isPlanTask challengeId= ", Integer.valueOf(i), ",taskId= ", Integer.valueOf(i2));
        List<String> e2 = e(i);
        return koq.c(e2) && e2.contains(String.valueOf(i2));
    }

    public static List<aya> c() {
        ArrayList arrayList = new ArrayList();
        ArrayList<Integer> d2 = dsl.d(R.array._2130968679_res_0x7f040067);
        if (koq.b(d2)) {
            return arrayList;
        }
        Iterator<Integer> it = d2.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            aya ayaVar = new aya();
            ayaVar.c(intValue);
            ayaVar.a(nsf.h(d.get(Integer.valueOf(intValue)).intValue()));
            ayaVar.e(nsf.h(c.get(Integer.valueOf(intValue)).intValue()));
            ayaVar.b(0);
            arrayList.add(ayaVar);
        }
        return arrayList;
    }

    public static String d(int i) {
        ArrayList<Integer> a2 = azi.a();
        return Constants.H5PRO_PAGE_PREFIX + ((koq.c(a2) && a2.contains(Integer.valueOf(i))) ? "BasicSurvey" : "ManagePlan") + "?index=" + i + "&from=2";
    }
}
