package defpackage;

import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.hiai.awareness.AwarenessConstants;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class bat {
    public static boolean e() {
        String e = bao.e("health_model_challenge_join");
        LogUtil.c("HealthLife_HealthModelChallengeUtils", "isJoinHealthChallenge join = ", e);
        return "1".equals(e);
    }

    public static boolean d() {
        return (!"1".equals(bao.e("health_model_first_join_challenge")) || String.valueOf(true).equals(bao.e("health_model_twice_same_challenge")) || String.valueOf(AwarenessConstants.ERROR_NO_PERMISSION_CODE).equals(bao.e("health_model_challenge_id"))) ? false : true;
    }

    public static void b() {
        bao.e("health_model_challenge_join", "");
        bao.e("health_model_first_join_challenge", "");
        bao.e("health_model_twice_same_challenge", "");
        bao.e("health_model_challenge_id", "");
        bao.e("health_model_current_challenge_join_time", "");
    }

    public static ArrayList<Integer> a() {
        return dsl.d(R.array._2130968678_res_0x7f040066);
    }

    public static boolean e(int i) {
        ArrayList<Integer> a2 = a();
        if (koq.b(a2)) {
            return false;
        }
        return a2.contains(Integer.valueOf(i));
    }

    public static boolean c(List<HealthLifeBean> list) {
        HealthLifeBean next;
        if (koq.b(list)) {
            LogUtil.h("HealthLife_HealthModelChallengeUtils", "isBloodPressureGoal list is empty");
            return azi.y();
        }
        Iterator<HealthLifeBean> it = list.iterator();
        int i = 0;
        while (it.hasNext() && ((next = it.next()) == null || 5 != next.getId() || (i = next.getChallengeId()) <= 0)) {
        }
        if (i == 0) {
            LogUtil.a("HealthLife_HealthModelChallengeUtils", "isBloodPressureGoal not add challengeId field");
            return azi.y();
        }
        return azi.a().contains(Integer.valueOf(i));
    }

    public static boolean c() {
        boolean ad = azi.ad();
        boolean e = e();
        boolean z = !TextUtils.isEmpty(bao.e("health_model_challenge_id"));
        LogUtil.a("HealthLife_HealthModelChallengeUtils", "isJoinHealthGoal isDoctorPlan= ", Boolean.valueOf(ad), ",isJoinChallenge= ", Boolean.valueOf(e), ",isValidGoal=", Boolean.valueOf(z));
        return (e && z) || ad;
    }
}
