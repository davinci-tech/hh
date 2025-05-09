package com.huawei.health.suggestion.ui.fitness.helper;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.suggestion.ui.fitness.activity.CourseEquipmentConnectActivity;
import com.huawei.health.suggestion.ui.fitness.activity.LongCoachActivity;
import com.huawei.health.suggestion.ui.fitness.module.CoachData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.db.bean.ContentTemplateRecord;
import com.huawei.pluginfitnessadvice.FitWorkout;
import defpackage.ash;
import defpackage.dib;
import defpackage.ffy;
import defpackage.fqw;
import defpackage.gnm;
import health.compact.a.Services;
import health.compact.a.utils.StringUtils;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class JumpConnectHelper {

    /* renamed from: a, reason: collision with root package name */
    private Gson f3164a;
    private int b;
    private boolean c;
    private int d;
    private c e;

    /* loaded from: classes.dex */
    public interface JumpActivityHandleInterface {
        default void showEquipmentMatchTip(int i) {
        }

        default void startLongCoachAfter() {
        }
    }

    private JumpConnectHelper() {
        this.d = 0;
        this.b = 0;
        this.f3164a = new Gson();
        this.e = new c();
        this.c = false;
    }

    public c b() {
        return this.e;
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        private static final JumpConnectHelper f3165a = new JumpConnectHelper();
    }

    public static JumpConnectHelper c() {
        return a.f3165a;
    }

    public boolean e() {
        return this.c;
    }

    public void c(boolean z) {
        this.c = z;
    }

    public void d(int i, int i2) {
        if (i == 0 || i2 == 0) {
            LogUtil.h("Suggestion_JumpConnectHelper", "startCourseConnectActivity entranceType or equipmentType invalid");
            return;
        }
        this.b = i;
        this.d = i2;
        Intent intent = new Intent(BaseApplication.getContext(), (Class<?>) CourseEquipmentConnectActivity.class);
        intent.putExtra("KEY_INTENT_COURSE_ENTRANCE", i);
        intent.putExtra("KEY_INTENT_EQUIPMENT_TYPE", i2);
        intent.addFlags(268435456);
        gnm.aPB_(BaseApplication.getContext(), intent);
    }

    public void e(List<ContentValues> list, int i, int i2) {
        if (i == 0 || i2 == 0) {
            LogUtil.h("Suggestion_JumpConnectHelper", "startCourseConnectActivity entranceType or equipmentType invalid");
            return;
        }
        this.b = i;
        this.d = i2;
        Intent intent = new Intent();
        intent.setClassName(BaseApplication.getContext(), "com.huawei.indoorequip.activity.IndoorEquipConnectedActivity");
        ContentValues contentValues = list.get(0);
        intent.putExtra("PAYLOAD_FROM_NFC", dib.c().UU_(contentValues));
        intent.putExtra("KEY_INTENT_COURSE_ENTRANCE", i);
        intent.putExtra("KEY_INTENT_EQUIPMENT_TYPE", dib.c().a(i2));
        String asString = contentValues.getAsString("uniqueId");
        IndoorEquipManagerApi indoorEquipManagerApi = (IndoorEquipManagerApi) Services.c("PluginLinkIndoorEquip", IndoorEquipManagerApi.class);
        if (indoorEquipManagerApi.isDeviceBtConnected() && !TextUtils.isEmpty(asString) && asString.equals(indoorEquipManagerApi.getCurrentMacAddress())) {
            intent.putExtra(WorkoutRecord.Extend.COURSE_TARGET_TYPE, 6);
        }
        Object[] objArr = new Object[2];
        objArr[0] = "startIndoorEquipmentConnectActivity,";
        objArr[1] = intent.getExtras() != null ? intent.getExtras().toString() : Integer.valueOf(i2);
        LogUtil.a("Suggestion_JumpConnectHelper", objArr);
        gnm.aPB_(BaseApplication.getContext(), intent);
    }

    public void j() {
        Intent intent = new Intent();
        intent.setClassName(BaseApplication.getContext(), "com.huawei.ui.device.activity.adddevice.OneKeyScanActivity");
        intent.putExtra("KEY_INTENT_COURSE_ENTRANCE", this.b);
        intent.putExtra("KEY_INTENT_EQUIPMENT_TYPE", dib.c().a(this.d));
        intent.addFlags(268435456);
        gnm.aPB_(BaseApplication.getContext(), intent);
    }

    public void d(Context context, int i) {
        if (!c(context, i)) {
            LogUtil.h("Suggestion_JumpConnectHelper", "startLongCoachActivity data is invalid");
            return;
        }
        fqw fqwVar = (fqw) this.f3164a.fromJson(ash.b("course_equipment_start_long_info"), fqw.class);
        CoachData c2 = fqwVar.c();
        Intent intent = new Intent(context, (Class<?>) LongCoachActivity.class);
        intent.putExtra("KEY_INTENT_COURSE_ENTRANCE", i);
        intent.putExtra("KEY_INTENT_EQUIPMENT_TYPE", this.d);
        intent.putExtra(ContentTemplateRecord.MOTIONS, c2);
        FitWorkout a2 = fqwVar.a();
        intent.putExtra("long_coach_type", a2.getVideoProperty());
        intent.putExtra("commodityFlag", a2.acquireCommodityFlag());
        intent.putExtra("workout_package_id", fqwVar.m());
        intent.putExtra("course_belong_type", fqwVar.b());
        intent.putExtra("long_coach_video_url", fqwVar.j());
        intent.putExtra("long_coach_show_action", fqwVar.f());
        intent.putExtra("long_coach_need_calories", a2.getShowCalories());
        intent.putExtra("long_coach_need_countdown", a2.getShowCountdown());
        intent.putExtra("long_coach_need_heart_rate", a2.getShowHeartRate());
        intent.putExtra("havenexttrain", fqwVar.e() != fqwVar.h().size() - 1);
        WorkoutRecord i2 = fqwVar.i();
        intent.putExtra("coach_detail_name", !TextUtils.isEmpty(i2.acquireWorkoutName()) ? i2.acquireWorkoutName() : a2.acquireName());
        intent.putExtra("coach_detail_picture", !TextUtils.isEmpty(a2.acquireMidPicture()) ? StringUtils.c((Object) a2.acquireMidPicture()) : StringUtils.c((Object) a2.acquirePicture()));
        intent.putExtra("workoutrecord", i2);
        Bundle bundle = new Bundle();
        bundle.putBoolean("isshowbutton", fqwVar.k());
        bundle.putInt("track_type", fqwVar.o());
        bundle.putLong("plan_execute_time", fqwVar.g());
        intent.putExtra("bundlekey", bundle);
        intent.putExtra("topic_name", fqwVar.n());
        intent.putExtra("entrance", fqwVar.d());
        intent.putExtra("ISPLANFIT", fqwVar.l());
        intent.addFlags(268435456);
        gnm.aPB_(BaseApplication.getContext(), intent);
        this.e.startLongCoachAfter();
    }

    private boolean c(Context context, int i) {
        String b = ash.b("course_equipment_start_long_info");
        if (context == null || i == 0 || TextUtils.isEmpty(b)) {
            LogUtil.h("Suggestion_JumpConnectHelper", "isValidData params is invalid");
            return false;
        }
        return ffy.c(((fqw) this.f3164a.fromJson(b, fqw.class)).c());
    }

    public int a() {
        return this.d;
    }

    public int d() {
        return this.b;
    }

    public void i() {
        this.e.e();
        this.d = 0;
        this.b = 0;
        ash.a("course_equipment_start_long_info", "");
    }

    public static class c implements JumpActivityHandleInterface {
        private Map<String, WeakReference<JumpActivityHandleInterface>> c = new ConcurrentHashMap();

        public void b(String str, WeakReference<JumpActivityHandleInterface> weakReference) {
            if (str == null || weakReference == null) {
                LogUtil.h("Suggestion_JumpConnectHelper", "JumpActivityHandle registerHandleInterface params is invalid");
            } else {
                this.c.put(str, weakReference);
            }
        }

        public void b(String str) {
            if (str == null) {
                LogUtil.h("Suggestion_JumpConnectHelper", "JumpActivityHandle unRegisterHandleInterface unregist activity tag is null");
            } else {
                this.c.remove(str);
            }
        }

        @Override // com.huawei.health.suggestion.ui.fitness.helper.JumpConnectHelper.JumpActivityHandleInterface
        public void startLongCoachAfter() {
            Iterator<WeakReference<JumpActivityHandleInterface>> it = this.c.values().iterator();
            while (it.hasNext()) {
                JumpActivityHandleInterface jumpActivityHandleInterface = it.next().get();
                if (jumpActivityHandleInterface == null) {
                    LogUtil.b("Suggestion_JumpConnectHelper", "startLongCoachAfter activityHandleInterface is null.");
                    return;
                }
                jumpActivityHandleInterface.startLongCoachAfter();
            }
        }

        @Override // com.huawei.health.suggestion.ui.fitness.helper.JumpConnectHelper.JumpActivityHandleInterface
        public void showEquipmentMatchTip(int i) {
            Iterator<WeakReference<JumpActivityHandleInterface>> it = this.c.values().iterator();
            while (it.hasNext()) {
                JumpActivityHandleInterface jumpActivityHandleInterface = it.next().get();
                if (jumpActivityHandleInterface == null) {
                    LogUtil.b("Suggestion_JumpConnectHelper", "showEquipmentMatchTip activityHandleInterface is null.");
                    return;
                }
                jumpActivityHandleInterface.showEquipmentMatchTip(i);
            }
        }

        public void e() {
            this.c.clear();
        }
    }
}
