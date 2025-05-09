package defpackage;

import android.content.Context;
import android.content.Intent;
import com.huawei.health.sport.utils.SportSupportUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes4.dex */
public class fgb {
    public static void awY_(Context context, Intent intent) {
        if (SportSupportUtil.h()) {
            mwq.d().launchActivity(context, intent);
        } else {
            mwr.b().launchActivity(context, intent);
        }
    }

    public static void awX_(int i, Context context, Intent intent) {
        if (SportSupportUtil.i()) {
            mwo.d().launchActivity(context, intent);
        } else {
            ReleaseLogUtil.d("Track_SportLaunchUtil", "startAiSportExam failed, not support sportType", Integer.valueOf(i));
        }
    }

    public static void awW_(Context context, Intent intent) {
        if (mwt.d().getModelType() != -1) {
            mon.d().launchActivity(context, intent);
        } else {
            ReleaseLogUtil.d("Track_SportLaunchUtil", "startAiActionSport failed, not support sportType");
        }
    }

    public static boolean awV_(int i, Context context, Intent intent) {
        if (SportSupportUtil.e(i)) {
            awX_(i, context, intent);
            return true;
        }
        if (i == 700001) {
            awW_(context, intent);
            return true;
        }
        ReleaseLogUtil.d("Track_SportLaunchUtil", "startAiActionSport failed, not support sportType");
        return false;
    }
}
