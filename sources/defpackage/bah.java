package defpackage;

import android.text.TextUtils;
import android.util.SparseArray;
import com.huawei.basichealthmodel.bean.ChallengeConfigBean;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class bah {
    public static int e() {
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

    public static List<String> e(int i) {
        LogUtil.a("HealthLife_HealthChallengeUtil", "isPlanTask challengeId= ", Integer.valueOf(i));
        ChallengeConfigBean b = b(i);
        if (b == null) {
            return new ArrayList();
        }
        return b.getChallengeTaskId();
    }

    public static boolean d(int i, int i2) {
        LogUtil.a("HealthLife_HealthChallengeUtil", "isPlanTask challengeId= ", Integer.valueOf(i), ",taskId= ", Integer.valueOf(i2));
        List<String> e = e(i);
        return koq.c(e) && e.contains(String.valueOf(i2));
    }

    public static boolean b() {
        boolean ad = azi.ad();
        boolean e = bat.e();
        boolean z = !TextUtils.isEmpty(bao.e("health_model_challenge_id"));
        LogUtil.a("HealthLife_HealthChallengeUtil", "isJoinHealthGoal isDoctorPlan= ", Boolean.valueOf(ad), ",isJoinChallenge= ", Boolean.valueOf(e), ",isValidGoal=", Boolean.valueOf(z));
        return (e && z) || ad;
    }
}
