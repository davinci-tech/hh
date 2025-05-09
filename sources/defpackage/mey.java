package defpackage;

import android.content.Context;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.MedalConfigInfo;
import java.util.Date;

/* loaded from: classes6.dex */
public class mey {
    public static boolean e(MedalConfigInfo medalConfigInfo, Context context, long j, long j2) {
        if (medalConfigInfo != null && medalConfigInfo.acquireActionType() == 13) {
            return c(medalConfigInfo, context, j, j2);
        }
        return false;
    }

    private static boolean c(MedalConfigInfo medalConfigInfo, Context context, long j, long j2) {
        LogUtil.a("PLGACHIEVE_AchievePeriodMedalService", "enter dealFitnessMedal");
        if (1 == medalConfigInfo.acquireMedalUnit()) {
            int totalFitDuration = mcv.d(context).getAdapter().getTotalFitDuration(new Date(mfg.c(j, true)), new Date(mfg.c(j2, false)));
            int acquireMedalLevel = medalConfigInfo.acquireMedalLevel();
            LogUtil.a("PLGACHIEVE_AchievePeriodMedalService", "dealFitnessMedal medalLevel=", Integer.valueOf(acquireMedalLevel), "duration=", Integer.valueOf(totalFitDuration));
            if (totalFitDuration >= acquireMedalLevel) {
                return true;
            }
        }
        return false;
    }
}
