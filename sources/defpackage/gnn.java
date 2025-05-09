package defpackage;

import android.content.pm.PackageManager;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.json.JsonSanitizer;
import com.hihonor.assistant.cardmgrsdk.model.CardDispReqBuilder;
import com.hihonor.assistant.cardmgrsdk.model.CardDisplayRequestArg;
import com.hihonor.assistant.cardmgrsdk.model.ClickPendingIntent;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.os.SystemInfo;
import com.huawei.haf.common.utils.AppInfoUtils;
import com.huawei.health.R;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class gnn {
    public static final String d = BaseApplication.e().getPackageName();

    public static CardDisplayRequestArg d(int i, HiTrackMetaData hiTrackMetaData, long j, String str, String str2) {
        LogUtil.a("YoyoUtils", "getMovementCardArgs:  ", Integer.valueOf(i));
        StringBuilder sb = new StringBuilder();
        String str3 = d;
        sb.append(str3);
        sb.append("_add_one_card_");
        sb.append(i);
        glu gluVar = new glu(sb.toString());
        return new CardDispReqBuilder("health", str3 + "_add_one_card_" + i).businessParams("").detailType("").endTime(System.currentTimeMillis() + 900000).type(str).widgetPackage(str3).versionCode(b()).cardRemoteViews(gluVar.aON_(hiTrackMetaData, j, str2, str)).clickPendingIntents(gluVar.b()).setTitle(AppInfoUtils.b()).build();
    }

    public static CardDisplayRequestArg e(int i, WorkoutRecord workoutRecord, String str, String str2) {
        LogUtil.a("YoyoUtils", "getFitnessCardArgs:  ", Integer.valueOf(i));
        StringBuilder sb = new StringBuilder();
        String str3 = d;
        sb.append(str3);
        sb.append("_add_one_card_");
        sb.append(i);
        glw glwVar = new glw(sb.toString());
        return new CardDispReqBuilder("health", str3 + "_add_one_card_" + i).businessParams("").detailType("").endTime(System.currentTimeMillis() + 900000).type(str).widgetPackage(str3).versionCode(b()).cardRemoteViews(glwVar.aOL_(workoutRecord, str2, str)).clickPendingIntents(glwVar.e()).setTitle(AppInfoUtils.b()).build();
    }

    private static int b() {
        int i;
        LogUtil.a("YoyoUtils", "getVersioncode");
        int i2 = 1;
        try {
            i = BaseApplication.e().getPackageManager().getPackageInfo(BaseApplication.e().getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
        }
        try {
            LogUtil.a("YoyoUtils", "versioncode ", Integer.valueOf(i));
            return i;
        } catch (PackageManager.NameNotFoundException unused2) {
            i2 = i;
            LogUtil.b("YoyoUtils", "NameNotFoundException is error");
            return i2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static android.content.Intent aPE_(java.lang.String r9, java.lang.String r10) {
        /*
            java.lang.String r0 = "getHealthAppIntent selectClick="
            java.lang.String r1 = " showTrack="
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r9, r1, r10}
            java.lang.String r1 = "YoyoUtils"
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            android.content.Intent r0 = new android.content.Intent
            r0.<init>()
            r2 = 335544320(0x14000000, float:6.4623485E-27)
            r0.addFlags(r2)
            java.lang.String r2 = "walk"
            boolean r3 = r9.equals(r2)
            java.lang.String r4 = "running"
            if (r3 != 0) goto L27
            boolean r3 = r9.equals(r4)
            if (r3 == 0) goto L3b
        L27:
            android.content.Context r3 = com.huawei.haf.application.BaseApplication.e()
            java.lang.String r5 = "com.huawei.healthcloud.plugintrack.ui.activity.TrackDetailActivity"
            r0.setClassName(r3, r5)
            boolean r3 = android.text.TextUtils.isEmpty(r10)
            if (r3 != 0) goto L3b
            java.lang.String r3 = "movement"
            r0.putExtra(r3, r10)
        L3b:
            java.lang.String r3 = "stretch"
            boolean r5 = r9.equals(r3)
            java.lang.String r6 = "android.intent.action.VIEW"
            if (r5 == 0) goto Le5
            r5 = 0
            defpackage.gge.d(r5)
            com.huawei.health.sport.model.WorkoutRecord r5 = new com.huawei.health.sport.model.WorkoutRecord
            r5.<init>()
            java.util.Date r7 = new java.util.Date
            r7.<init>()
            long r7 = r7.getTime()
            r5.saveExerciseTime(r7)
            boolean r2 = r10.equals(r2)
            if (r2 != 0) goto L7f
            boolean r2 = r10.equals(r4)
            if (r2 == 0) goto L67
            goto L7f
        L67:
            java.lang.String r2 = "W010"
            r5.saveWorkoutId(r2)
            android.content.Context r2 = com.huawei.haf.application.BaseApplication.e()
            android.content.res.Resources r2 = r2.getResources()
            r4 = 2130843774(0x7f02187e, float:1.7292681E38)
            java.lang.String r2 = r2.getString(r4)
            r5.saveWorkoutName(r2)
            goto L96
        L7f:
            java.lang.String r2 = "R002"
            r5.saveWorkoutId(r2)
            android.content.Context r2 = com.huawei.haf.application.BaseApplication.e()
            android.content.res.Resources r2 = r2.getResources()
            r4 = 2130842372(0x7f021304, float:1.7289837E38)
            java.lang.String r2 = r2.getString(r4)
            r5.saveWorkoutName(r2)
        L96:
            java.lang.String r2 = ""
            r5.savePlanId(r2)
            java.util.ArrayList r2 = new java.util.ArrayList
            r4 = 1
            r2.<init>(r4)
            r2.add(r5)
            org.json.JSONObject r4 = new org.json.JSONObject
            r4.<init>()
            com.google.gson.Gson r7 = new com.google.gson.Gson     // Catch: org.json.JSONException -> Ldc
            r7.<init>()     // Catch: org.json.JSONException -> Ldc
            java.lang.String r8 = "workoutrecord"
            java.lang.String r2 = r7.toJson(r2)     // Catch: org.json.JSONException -> Ldc
            r4.put(r8, r2)     // Catch: org.json.JSONException -> Ldc
            android.content.Intent r2 = new android.content.Intent     // Catch: org.json.JSONException -> Ldc
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: org.json.JSONException -> Ldc
            java.lang.String r8 = "huaweischeme://healthapp/fitnesspage?skip_type=train_details&version=2.0&id="
            r7.<init>(r8)     // Catch: org.json.JSONException -> Ldc
            java.lang.String r5 = r5.acquireWorkoutId()     // Catch: org.json.JSONException -> Ldc
            r7.append(r5)     // Catch: org.json.JSONException -> Ldc
            java.lang.String r5 = r7.toString()     // Catch: org.json.JSONException -> Ldc
            android.net.Uri r5 = android.net.Uri.parse(r5)     // Catch: org.json.JSONException -> Ldc
            r2.<init>(r6, r5)     // Catch: org.json.JSONException -> Ldc
            java.lang.String r0 = r4.toString()     // Catch: org.json.JSONException -> Ldb
            r2.putExtra(r3, r0)     // Catch: org.json.JSONException -> Ldb
            r0 = r2
            goto Le5
        Ldb:
            r0 = r2
        Ldc:
            java.lang.String r2 = "TrainDetail is error "
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r2)
        Le5:
            java.lang.String r1 = "Fitness"
            boolean r9 = r9.equals(r1)
            if (r9 == 0) goto L104
            android.content.Intent r0 = new android.content.Intent
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r1 = "huaweischeme://healthapp/fitnesspage?skip_type=fitness_result&fitnessresult="
            r9.<init>(r1)
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            android.net.Uri r9 = android.net.Uri.parse(r9)
            r0.<init>(r6, r9)
        L104:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.gnn.aPE_(java.lang.String, java.lang.String):android.content.Intent");
    }

    public static List<ClickPendingIntent> d(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        ClickPendingIntent clickPendingIntent = new ClickPendingIntent(R.id.honor_yoyo_relative, 3, aPE_(str2, str));
        ClickPendingIntent clickPendingIntent2 = new ClickPendingIntent(R.id.honor_yoyo_image_start, 3, aPE_("stretch", str2));
        arrayList.add(clickPendingIntent);
        arrayList.add(clickPendingIntent2);
        return arrayList;
    }

    public static HiTrackMetaData d(HiHealthData hiHealthData) {
        if (hiHealthData != null) {
            try {
                return (HiTrackMetaData) new Gson().fromJson(JsonSanitizer.sanitize(hiHealthData.getMetaData()), HiTrackMetaData.class);
            } catch (JsonSyntaxException unused) {
                LogUtil.b("YoyoUtils", "parse onGetLastHistoryData error");
            }
        } else {
            LogUtil.c("YoyoUtils", "list is null");
        }
        return null;
    }

    public static String e(long j) {
        return UnitUtil.d((int) TimeUnit.MILLISECONDS.toSeconds(j));
    }

    public static String c(float f) {
        return f == 0.0f ? "0" : fhq.a(BaseApplication.e(), f);
    }

    public static String d(int i) {
        return UnitUtil.e((i * 1.0d) / 1000.0d, 1, 0);
    }

    public static double e(boolean z, int i, double d2) {
        if (!UnitUtil.b()) {
            return d2;
        }
        if (z && i == 3) {
            return UnitUtil.d(d2, 3);
        }
        if (i == 3) {
            return UnitUtil.e(d2, 3);
        }
        return -1.0d;
    }

    public static boolean d() {
        if (!LanguageUtil.m(BaseApplication.e())) {
            LogUtil.a("YoyoUtils", "not chineseSimplify");
            return false;
        }
        if (Utils.o()) {
            LogUtil.a("YoyoUtils", "oversea version, dont collect label");
            return false;
        }
        if (SystemInfo.h()) {
            return true;
        }
        LogUtil.a("YoyoUtils", "not Honor");
        return false;
    }
}
