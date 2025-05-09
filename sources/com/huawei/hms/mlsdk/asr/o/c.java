package com.huawei.hms.mlsdk.asr.o;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.mlkit.common.ha.HianalyticsLogProvider;
import com.huawei.hms.mlsdk.asr.engine.utils.SmartLogger;
import com.huawei.hms.mlsdk.common.MLApplication;
import com.huawei.operation.OpAnalyticsConstants;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private volatile boolean f5094a = false;
    private Map<String, com.huawei.hms.mlsdk.asr.o.b> b = new HashMap();
    private boolean c;

    static class b {

        /* renamed from: a, reason: collision with root package name */
        private static final c f5095a = new c(null);
    }

    /* synthetic */ c(a aVar) {
    }

    public static String b() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(System.currentTimeMillis()));
        StringBuilder sb2 = new StringBuilder(3);
        try {
            SecureRandom instanceStrong = SecureRandom.getInstanceStrong();
            for (int i = 0; i < 3; i++) {
                sb2.append("0123456789".charAt(instanceStrong.nextInt(10)));
            }
        } catch (NoSuchAlgorithmException e) {
            StringBuilder a2 = com.huawei.hms.mlsdk.asr.o.a.a("e:");
            a2.append(e.getMessage());
            SmartLogger.i("HaAdapter_MLKitAsr", a2.toString());
        }
        sb.append(sb2.toString());
        return sb.toString();
    }

    public static c c() {
        return b.f5095a;
    }

    public void a(Context context, boolean z) {
        if (this.f5094a) {
            SmartLogger.i("HaAdapter_MLKitAsr", "has already startHaAnalysis");
            return;
        }
        if (HianalyticsLogProvider.getInstance().sdkForbiddenHiLog(context.getApplicationContext())) {
            SmartLogger.i("HaAdapter_MLKitAsr", "return startHaAnalysis,sdkForbiddenHiLog");
            return;
        }
        this.c = z;
        SmartLogger.i("HaAdapter_MLKitAsr", "Start ha moudle");
        if (z) {
            HianalyticsLogProvider.getInstance().initTimer("MLKitRtt");
        } else {
            HianalyticsLogProvider.getInstance().initTimer("MLKitAsr");
        }
        this.f5094a = true;
    }

    public Long d(String str) {
        if (TextUtils.isEmpty(str) || !this.f5094a || !this.b.containsKey(str)) {
            StringBuilder a2 = com.huawei.hms.mlsdk.asr.o.a.a("getSpeechEndTime return !isInfoGatherStart:!isInfoGatherStart !mEventsToBeReported.containsKey(taskId)");
            a2.append(!this.b.containsKey(str));
            SmartLogger.i("HaAdapter_MLKitAsr", a2.toString());
            return null;
        }
        com.huawei.hms.mlsdk.asr.o.b bVar = this.b.get(str);
        if (bVar != null) {
            return Long.valueOf(bVar.h());
        }
        SmartLogger.e("HaAdapter_MLKitAsr", "getSpeechEndTime taskId " + str + " 's asrAnalyticsEvent is null");
        return null;
    }

    public Long e(String str) {
        if (TextUtils.isEmpty(str) || !this.f5094a || !this.b.containsKey(str)) {
            StringBuilder a2 = com.huawei.hms.mlsdk.asr.o.a.a("getSpeechStartTime return !isInfoGatherStart:");
            a2.append(!this.f5094a);
            a2.append("!mEventsToBeReported.containsKey(taskId): ");
            a2.append(!this.b.containsKey(str));
            SmartLogger.i("HaAdapter_MLKitAsr", a2.toString());
            return null;
        }
        com.huawei.hms.mlsdk.asr.o.b bVar = this.b.get(str);
        if (bVar != null) {
            return Long.valueOf(bVar.i());
        }
        SmartLogger.e("HaAdapter_MLKitAsr", "getSpeechStartTime taskId " + str + " 's asrAnalyticsEvent is null");
        return null;
    }

    public void f(String str) {
        if (TextUtils.isEmpty(str) || !this.f5094a || !this.b.containsKey(str)) {
            StringBuilder a2 = com.huawei.hms.mlsdk.asr.o.a.a("postEvent return !isInfoGatherStart: ");
            a2.append(!this.f5094a);
            a2.append("!mEventsToBeReported.containsKey(taskId): ");
            a2.append(!this.b.containsKey(str));
            SmartLogger.i("HaAdapter_MLKitAsr", a2.toString());
            return;
        }
        SmartLogger.i("HaAdapter_MLKitAsr", "Post Event [" + str + "]");
        com.huawei.hms.mlsdk.asr.o.b bVar = this.b.get(str);
        if (bVar == null) {
            SmartLogger.e("HaAdapter_MLKitAsr", "eventDetail taskId " + str + " 's asrAnalyticsEvent is null");
        } else {
            SmartLogger.i("HaAdapter_MLKitAsr", "applanguage: " + bVar.a() + " speechStartTime:" + bVar.i() + ",speechEndTime: " + bVar.h() + ",firstWordCost: " + bVar.c() + ",lastWordCost: " + bVar.d() + ",voiceStreamTime:" + bVar.l() + ",uploadVoiceSize: " + bVar.k() + ",textLength: " + bVar.j() + ",chainBuildingDelay: " + bVar.b() + ",result: " + bVar.e() + ",errMsg: " + bVar.f());
        }
        com.huawei.hms.mlsdk.asr.o.b bVar2 = this.b.get(str);
        if (bVar2 != null) {
            HianalyticsLogProvider.getInstance().postEvent(MLApplication.getInstance().getAppContext(), 1, bVar2);
            return;
        }
        SmartLogger.e("HaAdapter_MLKitAsr", "postEvent taskId " + str + " 's asrAnalyticsEvent is null");
    }

    public long c(String str) {
        if (TextUtils.isEmpty(str) || !this.f5094a || !this.b.containsKey(str)) {
            StringBuilder a2 = com.huawei.hms.mlsdk.asr.o.a.a("getLastWordCost return !isInfoGatherStart:");
            a2.append(!this.f5094a);
            a2.append("!mEventsToBeReported.containsKey(taskId): ");
            a2.append(!this.b.containsKey(str));
            SmartLogger.i("HaAdapter_MLKitAsr", a2.toString());
            return 0L;
        }
        com.huawei.hms.mlsdk.asr.o.b bVar = this.b.get(str);
        if (bVar != null) {
            return bVar.g().longValue();
        }
        SmartLogger.e("HaAdapter_MLKitAsr", "getSendFinalDataTime taskId " + str + " 's asrAnalyticsEvent is null");
        return 0L;
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str) && this.f5094a) {
            SmartLogger.i("HaAdapter_MLKitAsr", "taskId: " + str);
            this.b.put(str, new com.huawei.hms.mlsdk.asr.o.b(this.c));
            return;
        }
        StringBuilder a2 = com.huawei.hms.mlsdk.asr.o.a.a("return createEvent: !isInfoGatherStart: ");
        a2.append(!this.f5094a);
        SmartLogger.i("HaAdapter_MLKitAsr", a2.toString());
    }

    public int b(String str) {
        if (!TextUtils.isEmpty(str) && this.f5094a && this.b.containsKey(str)) {
            com.huawei.hms.mlsdk.asr.o.b bVar = this.b.get(str);
            if (bVar == null) {
                SmartLogger.e("HaAdapter_MLKitAsr", "getLastWordCost taskId " + str + " 's asrAnalyticsEvent is null");
                return 0;
            }
            return bVar.d();
        }
        StringBuilder a2 = com.huawei.hms.mlsdk.asr.o.a.a("getLastWordCost return !isInfoGatherStart:");
        a2.append(!this.f5094a);
        a2.append("!mEventsToBeReported.containsKey(taskId): ");
        a2.append(!this.b.containsKey(str));
        SmartLogger.i("HaAdapter_MLKitAsr", a2.toString());
        return 0;
    }

    public void a() {
        if (this.f5094a) {
            SmartLogger.i("HaAdapter_MLKitAsr", "Stop ha moudle");
            if (this.c) {
                HianalyticsLogProvider.getInstance().reportAndCancelTimer("MLKitRtt");
            } else {
                HianalyticsLogProvider.getInstance().reportAndCancelTimer("MLKitAsr");
            }
            this.b.clear();
            this.f5094a = false;
        }
    }

    public void a(String str, int i, Bundle bundle) {
        SmartLogger.i("HaAdapter_MLKitAsr", "handleMsg, taskId: " + str + "msg: " + i);
        if (!TextUtils.isEmpty(str) && this.f5094a && this.b.containsKey(str)) {
            com.huawei.hms.mlsdk.asr.o.b bVar = this.b.get(str);
            if (bVar == null) {
                SmartLogger.e("HaAdapter_MLKitAsr", "handleMsg taskId " + str + " 's asrAnalyticsEvent is null");
                return;
            }
            switch (i) {
                case 0:
                    bVar.a(bundle.getString("applanguage"));
                    break;
                case 1:
                    bVar.b(bundle.getLong("speechStartTime"));
                    break;
                case 2:
                    if (bVar.h() == 0) {
                        bVar.a(bundle.getLong("speechEndTime"));
                        break;
                    } else {
                        SmartLogger.i("HaAdapter_MLKitAsr", "already set speechEndTime");
                        break;
                    }
                case 3:
                    bVar.b(bundle.getInt("firstWordCost"));
                    break;
                case 4:
                    bVar.c(bundle.getInt("lastWordCost"));
                    break;
                case 5:
                    bVar.f(bundle.getInt("voiceStreamTime"));
                    break;
                case 6:
                    bVar.e(bundle.getInt("uploadVoiceSize"));
                    break;
                case 7:
                    bVar.d(bundle.getInt(OpAnalyticsConstants.NOTIFICATION_TEXT_LENGTH));
                    break;
                case 8:
                    bVar.a(bundle.getInt("chainBuildingDelay"));
                    break;
                case 9:
                    bVar.b(String.valueOf(bundle.getInt("result")));
                    break;
                case 10:
                    bVar.c(bundle.getString("errMsg"));
                    break;
                case 11:
                    bVar.a(Long.valueOf(bundle.getLong("sendFinalDataTime")));
                    break;
            }
            return;
        }
        StringBuilder a2 = com.huawei.hms.mlsdk.asr.o.a.a("mEventsToBeReported: has no response event isInfoGatherStart:");
        a2.append(this.f5094a);
        a2.append("msg: ");
        a2.append(i);
        SmartLogger.e("HaAdapter_MLKitAsr", a2.toString());
    }
}
