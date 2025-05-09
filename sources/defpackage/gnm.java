package defpackage;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.arkuix.engine.StageDelegateProxy;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;

/* loaded from: classes.dex */
public class gnm {
    public static boolean aPA_(Activity activity) {
        return (activity == null || activity.isDestroyed() || activity.isFinishing()) ? false : true;
    }

    public static void aPB_(Context context, Intent intent) {
        if (intent == null) {
            LogUtil.h("ActivityUtils", "startActivity intent is null.");
            return;
        }
        if (!(context instanceof Activity)) {
            Activity wa_ = BaseApplication.wa_();
            if (wa_ != null) {
                context = wa_;
            } else {
                intent.addFlags(268435456);
            }
        }
        if (context == null) {
            LogUtil.h("ActivityUtils", "startActivity context is null.");
            return;
        }
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("ActivityUtils", "startActivity exception: ", LogAnonymous.b((Throwable) e));
        }
    }

    public static void aPC_(Context context, Intent intent) {
        if (intent == null) {
            LogUtil.h("ActivityUtils", "startStageActivity intent is null.");
            return;
        }
        if (!(context instanceof Activity)) {
            Activity wa_ = BaseApplication.wa_();
            if (wa_ != null) {
                context = wa_;
            } else {
                intent.addFlags(268435456);
            }
        }
        if (context == null) {
            LogUtil.h("ActivityUtils", "startStageActivity context is null.");
        } else {
            StageDelegateProxy.getInstance().launchActivity(context, intent);
        }
    }
}
