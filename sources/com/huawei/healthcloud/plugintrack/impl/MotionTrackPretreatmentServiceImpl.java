package com.huawei.healthcloud.plugintrack.impl;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.router.AppRouterUtils;
import com.huawei.haf.router.Guidepost;
import com.huawei.health.arkuix.utils.ArkUIXConstants;
import com.huawei.health.motiontrack.api.MotionTrackPretreatmentService;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.suggestion.model.FitRunPlayAudio;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.ble.BleConstants;
import defpackage.gso;
import defpackage.ixx;
import health.compact.a.AuthorizationUtils;
import health.compact.a.CommonUtils;
import health.compact.a.LogAnonymous;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class MotionTrackPretreatmentServiceImpl implements MotionTrackPretreatmentService {
    @Override // com.huawei.health.motiontrack.api.MotionTrackPretreatmentService
    public int getSportType(int i) {
        if (i == 1) {
            return 257;
        }
        if (i == 2) {
            return 258;
        }
        if (i != 3) {
            return i != 283 ? 0 : 283;
        }
        return 259;
    }

    @Override // com.huawei.haf.router.facade.template.ServiceProvider
    public void init(Context context) {
    }

    @Override // com.huawei.health.motiontrack.api.MotionTrackPretreatmentService
    public int getTargetType(String str) {
        char c;
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == 109) {
            if (str.equals(FitRunPlayAudio.OPPORTUNITY_M)) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode == 115) {
            if (str.equals("s")) {
                c = 1;
            }
            c = 65535;
        } else if (hashCode != 3426) {
            if (hashCode == 98254 && str.equals("cal")) {
                c = 3;
            }
            c = 65535;
        } else {
            if (str.equals("km")) {
                c = 2;
            }
            c = 65535;
        }
        if (c == 0) {
            return 1;
        }
        if (c == 1) {
            return 0;
        }
        if (c != 2) {
            return c != 3 ? -1 : 2;
        }
        return 1;
    }

    private void e(Context context, Map<String, String> map) {
        try {
            String str = map.get(BleConstants.SPORT_TYPE);
            r1 = TextUtils.isEmpty(str) ? 0 : getSportType(Integer.parseInt(str));
            String str2 = map.get(WorkoutRecord.Extend.COURSE_TARGET_TYPE);
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                float parseFloat = Float.parseFloat(map.get(WorkoutRecord.Extend.COURSE_TARGET_VALUE));
                if (CommonUtils.h(map.get(ArkUIXConstants.FROM_TYPE)) == 1) {
                    b(context);
                    a(context);
                }
                if (parseFloat == 5000.0f) {
                    d(context);
                }
                gso.e().c(0, r1, getTargetType(str2), parseFloat / 1000.0f, null, context.getApplicationContext());
                return;
            }
        } catch (NumberFormatException e) {
            LogUtil.b("MotionTrackPretreatmentServiceImpl", "goToSportTrack param is illegal, NumberFormatException: ", e.getMessage());
        } catch (Exception e2) {
            LogUtil.b("MotionTrackPretreatmentServiceImpl", "goToSportTrack is Exception: ", LogAnonymous.b((Throwable) e2));
        }
        a(context, r1);
    }

    private void a(Context context, int i) {
        AppRouter.b("/home/main").c(BleConstants.SPORT_TYPE, i).c("mLaunchSource", i == 0 ? 2 : 7).e("isToSportTab", true).c(context);
    }

    @Override // com.huawei.haf.router.facade.service.PretreatmentService
    public boolean onPretreatment(Context context, Guidepost guidepost) {
        int i = gso.e().getAdapter() != null ? gso.e().i() : 0;
        if (i != 1 && i != 2) {
            Uri zN_ = guidepost.zN_();
            if (zN_ == null || TextUtils.isEmpty(zN_.getQuery()) || LoginInit.getInstance(context).isBrowseMode() || !AuthorizationUtils.a(context)) {
                a(context, 0);
            } else {
                e(context, AppRouterUtils.zv_(zN_));
            }
        }
        return false;
    }

    private void b(Context context) {
        if (context == null) {
            LogUtil.h("MotionTrackPretreatmentServiceImpl", "doBiClickFromStepNotification context is null");
            return;
        }
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", 1);
        hashMap.put("type", 2);
        hashMap.put("jumpTo", 2);
        ixx.d().d(context, AnalyticsValue.HEALTH_PLUGIN_DAEMON_STEPS_NOTIFICATION_2080001.value(), hashMap, 0);
    }

    private void d(Context context) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("from", 3);
        ixx.d().d(context, AnalyticsValue.HEALTH_LOGIN_APP_WHITE_2050007.value(), hashMap, 0);
    }

    private void a(Context context) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put("from", 4);
        ixx.d().d(context, AnalyticsValue.HEALTH_LOGIN_APP_WHITE_2050007.value(), hashMap, 0);
    }
}
