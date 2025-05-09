package com.huawei.health.suggestion.ui.fitness.module;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.huawei.health.suggestion.ui.fitness.activity.LongCoachActivity;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.lang.ref.WeakReference;

/* loaded from: classes4.dex */
public class CoachWifiBroadcastReceiver extends BroadcastReceiver {
    private WeakReference<LongCoachActivity> b;

    public CoachWifiBroadcastReceiver(LongCoachActivity longCoachActivity) {
        if (longCoachActivity != null) {
            this.b = new WeakReference<>(longCoachActivity);
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getAction() == null) {
            LogUtil.h("CoachWifiBroadcastReceiver", "onReceive intent or action is null");
            return;
        }
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
            LongCoachActivity longCoachActivity = this.b.get();
            if (longCoachActivity == null) {
                LogUtil.h("CoachWifiBroadcastReceiver", "onReceive mActivity is null");
                return;
            }
            if (!CommonUtil.aa(context)) {
                LogUtil.h("CoachWifiBroadcastReceiver", "onReceive net is not connect");
                return;
            }
            LongCoachView coachView = longCoachActivity.getCoachView();
            if (coachView != null && coachView.ac()) {
                LogUtil.a("CoachWifiBroadcastReceiver", "isHasRemindMobileConnected true");
                return;
            }
            if (CommonUtil.ah(context)) {
                if (longCoachActivity.a() != null) {
                    longCoachActivity.a().e();
                }
                if (longCoachActivity.getCoachView() != null) {
                    longCoachActivity.getCoachView().av();
                    longCoachActivity.getCoachView().at();
                    return;
                }
                return;
            }
            if (longCoachActivity.getCoachView() != null) {
                longCoachActivity.getCoachView().u();
            }
        }
    }
}
