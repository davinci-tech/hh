package defpackage;

import android.os.Bundle;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes4.dex */
public class gzw {

    /* renamed from: a, reason: collision with root package name */
    private static gzw f13026a;
    private static final Object b = new Object();

    private gzw() {
    }

    public static gzw b() {
        gzw gzwVar;
        synchronized (b) {
            if (f13026a == null) {
                f13026a = new gzw();
            }
            gzwVar = f13026a;
        }
        return gzwVar;
    }

    public void a(final MotionPathSimplify motionPathSimplify) {
        if (motionPathSimplify == null) {
            LogUtil.h("Track_VoiceGuideAfterSport", "handleAfterSport motionPathSimplify is null.");
            return;
        }
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(1);
        newFixedThreadPool.submit(new Runnable() { // from class: gzw.2
            @Override // java.lang.Runnable
            public void run() {
                int i;
                long requestTotalTime = motionPathSimplify.requestTotalTime();
                float requestTotalDistance = (float) (motionPathSimplify.requestTotalDistance() * 0.001d);
                String[] strArr = new String[0];
                Map<String, Integer> requestSportData = motionPathSimplify.requestSportData();
                if (requestSportData == null || !requestSportData.containsKey("recovery_time") || requestSportData.get("recovery_time") == null) {
                    i = 0;
                } else {
                    i = requestSportData.get("recovery_time").intValue() / 60;
                    LogUtil.a("Track_VoiceGuideAfterSport", "handleAfterSport recoverTime = ", Integer.valueOf(i));
                }
                if (requestSportData != null && requestSportData.containsKey("etraining_effect") && requestSportData.get("etraining_effect") != null) {
                    double floor = Math.floor(requestSportData.get("etraining_effect").intValue() * 0.1d);
                    if (floor > 0.0d) {
                        strArr = new String[]{gzw.this.d((int) floor)};
                    }
                }
                gzw.this.a(strArr, requestTotalTime, requestTotalDistance, i);
                HashMap hashMap = new HashMap(10);
                if (i > 0) {
                    hashMap.put("type", 12);
                }
                hashMap.put("click", 1);
                hashMap.put("type", 11);
                ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_SMART_COACH_2010101.value(), hashMap, 0);
            }
        });
        newFixedThreadPool.shutdown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String[] strArr, float f, float f2, int i) {
        Bundle bundle = new Bundle();
        try {
            bundle.putString("voiceType", "sportSmartCoach");
            bundle.putStringArray("beforeSportFirstVoicePath", strArr);
            bundle.putFloat("sectionLower", f);
            bundle.putFloat("sectionUpper", f2);
            bundle.putInt("sportDuration", i);
            hab.b(new gxi(32, bundle));
        } catch (NumberFormatException unused) {
            LogUtil.b("Track_VoiceGuideAfterSport", "voiceConstructorPlay numberFormatException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String d(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "" : "L156" : "L155" : "L154" : "L153" : "L152";
    }
}
