package com.huawei.health.suggestion.util;

import android.content.ContentValues;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.health.R;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.ecologydevice.fitness.datastruct.DeviceInformation;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.suggestion.h5pro.CourseDetailH5Bridge;
import com.huawei.health.suggestion.model.FitRunPlayAudio;
import com.huawei.health.suggestion.ui.fitness.activity.TrainDetail;
import com.huawei.health.suggestion.ui.fitness.helper.JumpConnectHelper;
import com.huawei.health.suggestion.ui.fitness.module.CoachData;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import defpackage.ash;
import defpackage.cei;
import defpackage.ceo;
import defpackage.dds;
import defpackage.dij;
import defpackage.fqw;
import defpackage.koq;
import defpackage.mmp;
import defpackage.nrh;
import defpackage.nsn;
import health.compact.a.Services;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

/* loaded from: classes4.dex */
public class CourseEquipmentConnectionTipsUtil {

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f3430a = {"O03P", "2G97", "2G98", "2GYA", "2IX6"};

    /* loaded from: classes.dex */
    public interface DevicesConnectDialogCallback {
        void devicesConnectDialogNavigation();

        void devicesConnectDialogPositive();
    }

    public static int c(int i) {
        if (i != 1) {
            return i != 2 ? i != 3 ? 0 : 283 : OldToNewMotionPath.SPORT_TYPE_INDOOR_BIKE;
        }
        return 264;
    }

    public static void b(int i, String str, TrainDetail trainDetail) {
        if (i == 3) {
            if (!a()) {
                nrh.d(BaseApplication.getContext(), trainDetail.getString(R.string._2130845060_res_0x7f021d84));
                return;
            } else {
                trainDetail.devicesConnectDialogNavigation();
                return;
            }
        }
        trainDetail.devicesConnectDialogPositive();
        a(i, str, trainDetail);
    }

    private static void a(int i, String str, JumpConnectHelper.JumpActivityHandleInterface jumpActivityHandleInterface) {
        JumpConnectHelper.c().b().b(str, new WeakReference<>(jumpActivityHandleInterface));
        ArrayList<ContentValues> d = d(i);
        LogUtil.a("Suggestion_CourseEquipmentConnectionTipsUtil", "connectDevice connectedDeviceList ", Integer.valueOf(d.size()));
        if (d.size() == 1) {
            JumpConnectHelper.c().e(d, 1, i);
        } else {
            JumpConnectHelper.c().d(1, i);
        }
    }

    public static void e(int i, String str, CourseDetailH5Bridge courseDetailH5Bridge) {
        if (i == 3) {
            if (!a()) {
                nrh.d(BaseApplication.getContext(), BaseApplication.getContext().getString(R.string._2130845060_res_0x7f021d84));
                return;
            } else {
                courseDetailH5Bridge.devicesConnectDialogNavigation();
                return;
            }
        }
        courseDetailH5Bridge.devicesConnectDialogPositive();
        a(i, str, courseDetailH5Bridge);
    }

    public static void e(mmp mmpVar, fqw fqwVar, FitWorkout fitWorkout, CoachData coachData, int i) {
        if (mmpVar == null || fqwVar == null || fitWorkout == null || coachData == null) {
            LogUtil.h("Suggestion_CourseEquipmentConnectionTipsUtil", "buildStartLongCoachInfo params is null");
            return;
        }
        fqwVar.a(fitWorkout);
        fqwVar.e(coachData);
        fqwVar.c(i);
        fqwVar.c(mmpVar.a());
        fqwVar.b(mmpVar.q());
        fqwVar.a(mmpVar.am());
        fqwVar.b(mmpVar.al());
        fqwVar.e(mmpVar.w());
        fqwVar.a(mmpVar.f());
    }

    public static void a(fqw fqwVar, int i, String str, ArrayList<WorkoutRecord> arrayList, WorkoutRecord workoutRecord) {
        if (fqwVar == null || TextUtils.isEmpty(str) || arrayList == null || workoutRecord == null) {
            LogUtil.h("Suggestion_CourseEquipmentConnectionTipsUtil", "buildInfo params is null");
            return;
        }
        fqwVar.b(i);
        fqwVar.a(str);
        fqwVar.e(arrayList);
        fqwVar.d(workoutRecord);
        ash.a("course_equipment_start_long_info", new Gson().toJson(fqwVar));
    }

    private static ArrayList<ContentValues> d(int i) {
        ArrayList<ContentValues> d;
        ArrayList<ContentValues> arrayList = new ArrayList<>();
        if (i == 1) {
            d = ceo.d().d(HealthDevice.HealthDeviceKind.HDK_TREADMILL);
        } else if (i == 2) {
            d = ceo.d().d(HealthDevice.HealthDeviceKind.HDK_EXERCISE_BIKE);
        } else {
            LogUtil.h("Suggestion_CourseEquipmentConnectionTipsUtil", "is not treadmill or bicycle connect");
            return arrayList;
        }
        LogUtil.a("Suggestion_CourseEquipmentConnectionTipsUtil", "getConnectEquipmentDeviceList bondedDeviceByDeviceKind ", Integer.valueOf(d.size()));
        if (koq.b(d)) {
            return new ArrayList<>();
        }
        return ((IndoorEquipManagerApi) Services.c("PluginLinkIndoorEquip", IndoorEquipManagerApi.class)).isDeviceBtConnected() ? b(d) : d;
    }

    private static ArrayList<ContentValues> b(ArrayList<ContentValues> arrayList) {
        ArrayList<ContentValues> arrayList2 = new ArrayList<>();
        IndoorEquipManagerApi indoorEquipManagerApi = (IndoorEquipManagerApi) Services.c("PluginLinkIndoorEquip", IndoorEquipManagerApi.class);
        Iterator<ContentValues> it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ContentValues next = it.next();
            if (next != null) {
                String asString = next.getAsString("uniqueId");
                if (!TextUtils.isEmpty(asString) && asString.equals(indoorEquipManagerApi.getCurrentMacAddress())) {
                    arrayList2.add(next);
                    break;
                }
            }
        }
        return arrayList2;
    }

    public static int d(FitWorkout fitWorkout, String str) {
        int b = b(str);
        if (b != 0) {
            return b;
        }
        if (fitWorkout == null || !fitWorkout.isEquipmentCourse()) {
            LogUtil.h("Suggestion_CourseEquipmentConnectionTipsUtil", "getCourseEquipmentType Course is not EquipmentCourse");
            return 0;
        }
        if (fitWorkout.getEquipmentWorkoutAction() == null) {
            LogUtil.h("Suggestion_CourseEquipmentConnectionTipsUtil", "getEquipmentWorkoutAction is null.");
            return 0;
        }
        int equipmentType = fitWorkout.getEquipmentWorkoutAction().getEquipmentType();
        if (equipmentType == 1 || equipmentType == 2) {
            return equipmentType;
        }
        LogUtil.a("Suggestion_CourseEquipmentConnectionTipsUtil", "Course equipmentType", Integer.valueOf(equipmentType));
        return 0;
    }

    private static int b(String str) {
        if ("from_treadmill_details".equals(str)) {
            return 1;
        }
        if ("from_bicycle_details".equals(str)) {
            return 2;
        }
        return ("from_rope_details".equals(str) && a()) ? 3 : 0;
    }

    public static boolean a() {
        cei.b().init();
        String productId = cei.b().getProductId();
        if (TextUtils.isEmpty(productId)) {
            return false;
        }
        if ("true".equals(dij.c("isSupportAutoPauseSuprression", productId)) && cei.b().isRopeDeviceBtConnected()) {
            return true;
        }
        String e = dij.e(productId);
        for (String str : f3430a) {
            if (str.equals(e) && cei.b().isRopeDeviceBtConnected()) {
                LogUtil.a("Suggestion_CourseEquipmentConnectionTipsUtil", "smartProId ", str);
                return c(str);
            }
        }
        return false;
    }

    private static boolean c(String str) {
        if (!str.equals("O03P") && !str.equals("2GYA")) {
            return true;
        }
        DeviceInformation a2 = dds.c().a();
        return (a2 == null || a2.getSoftwareVersion() == null || e(a2.getSoftwareVersion()) <= e("v1.1.2")) ? false : true;
    }

    private static int e(String str) {
        return nsn.e(str.toLowerCase(Locale.ENGLISH).replace(FitRunPlayAudio.PLAY_TYPE_V, "").replace(".", ""));
    }
}
