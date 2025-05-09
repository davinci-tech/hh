package defpackage;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import androidx.core.app.NotificationCompat;
import com.google.gson.Gson;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.model.RelativeSportData;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.skinner.internal.OnRegisterSkinAttrsListener;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class gtc {
    public static void c(long j, long j2) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(j, j2);
        hiDataReadOption.setType(new int[]{OnRegisterSkinAttrsListener.REGISTER_BY_SCAN});
        HiHealthManager.d(BaseApplication.e()).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: gtc.4
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                if (obj == null) {
                    LogUtil.h("Track_TrackJumpUtils", "gotoTrackDetail data is null");
                    return;
                }
                if (!(obj instanceof SparseArray)) {
                    LogUtil.h("Track_TrackJumpUtils", "onResult !(data instanceof SparseArray)");
                    return;
                }
                SparseArray sparseArray = (SparseArray) obj;
                if (sparseArray.size() <= 0) {
                    LogUtil.h("Track_TrackJumpUtils", "gotoTrackDetail map size is 0");
                    return;
                }
                Object obj2 = sparseArray.get(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN);
                if (koq.e(obj2, HiHealthData.class)) {
                    List list = (List) obj2;
                    if (koq.b(list)) {
                        LogUtil.h("Track_TrackJumpUtils", "gotoTrackDetail list is null");
                        return;
                    }
                    HiHealthData hiHealthData = (HiHealthData) list.get(0);
                    if (hiHealthData == null) {
                        LogUtil.h("Track_TrackJumpUtils", "gotoTrackDetail hiHealthData is null");
                        return;
                    }
                    MotionPathSimplify motionPathSimplify = new MotionPathSimplify();
                    String e = kwz.e(hiHealthData, motionPathSimplify);
                    motionPathSimplify.saveDeviceType(hiHealthData.getInt("trackdata_deviceType"));
                    motionPathSimplify.saveProductId(hiHealthData.getString("device_prodid"));
                    LogUtil.a("Track_TrackJumpUtils", "gotoTrackDetailData is success!");
                    gso.e().init(BaseApplication.e());
                    if (motionPathSimplify.requestSportType() == 512) {
                        gso.e().d(e, motionPathSimplify, gtc.d(motionPathSimplify));
                    } else {
                        gso.e().e(e, motionPathSimplify);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<gxy> d(MotionPathSimplify motionPathSimplify) {
        List<RelativeSportData> requestChildSportItems = motionPathSimplify.requestChildSportItems();
        ArrayList arrayList = new ArrayList(3);
        if (koq.b(requestChildSportItems) || requestChildSportItems.size() > 3) {
            Object[] objArr = new Object[2];
            objArr[0] = "childSportItems is ";
            objArr[1] = requestChildSportItems == null ? null : Integer.valueOf(requestChildSportItems.size());
            ReleaseLogUtil.c("Track_TrackJumpUtils", objArr);
            return arrayList;
        }
        final CountDownLatch countDownLatch = new CountDownLatch(requestChildSportItems.size());
        for (int i = 0; i < requestChildSportItems.size(); i++) {
            RelativeSportData relativeSportData = requestChildSportItems.get(i);
            if (relativeSportData == null) {
                countDownLatch.countDown();
            } else {
                final gxy gxyVar = new gxy();
                arrayList.add(gxyVar);
                LogUtil.a("Track_TrackJumpUtils", BleConstants.SPORT_TYPE, Integer.valueOf(relativeSportData.getSportType()), "HasDetailInfo", Boolean.valueOf(relativeSportData.isHasDetailInfo()));
                gxyVar.c(relativeSportData);
                if (!requestChildSportItems.get(i).isHasDetailInfo()) {
                    countDownLatch.countDown();
                } else {
                    hps.a(relativeSportData.getStartTime(), relativeSportData.getEndTime(), new IBaseResponseCallback() { // from class: gte
                        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                        /* renamed from: onResponse */
                        public final void d(int i2, Object obj) {
                            gtc.d(countDownLatch, gxyVar, i2, obj);
                        }
                    });
                }
            }
        }
        try {
            countDownLatch.await(3L, TimeUnit.SECONDS);
        } catch (InterruptedException unused) {
            ReleaseLogUtil.c("Track_TrackJumpUtils", "interrupted while waiting for requestData");
        }
        return arrayList;
    }

    static /* synthetic */ void d(CountDownLatch countDownLatch, gxy gxyVar, int i, Object obj) {
        LogUtil.a("Track_TrackJumpUtils", "onResponse ", Integer.valueOf(i));
        if (!(obj instanceof knu)) {
            LogUtil.h("Track_TrackJumpUtils", "objData is not instance of HWHealthDataManager.PathData");
            countDownLatch.countDown();
        } else {
            knu knuVar = (knu) obj;
            gxyVar.d(knuVar.d());
            gxyVar.e(knuVar.b());
            countDownLatch.countDown();
        }
    }

    public static void c() {
        gso.e().init(BaseApplication.e());
        gso.e().ad();
    }

    public static void e(Context context, int i, long j, long j2) {
        if (nsn.a(2000)) {
            LogUtil.a("Track_TrackJumpUtils", "isClickFast");
        } else if (i != 0) {
            b(context, i);
        } else {
            c(j, j2);
        }
    }

    public static void b(Context context, int i) {
        if (!(context instanceof Activity)) {
            ReleaseLogUtil.d("Track_TrackJumpUtils", "gotoFitnessResultActivity context error");
            return;
        }
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            ReleaseLogUtil.d("Track_TrackJumpUtils", "gotoFitnessResultActivity courseApi == null");
            return;
        }
        WorkoutRecord acquireWorkoutRecordByRecordId = courseApi.acquireWorkoutRecordByRecordId(i);
        if (acquireWorkoutRecordByRecordId == null) {
            ReleaseLogUtil.d("Track_TrackJumpUtils", "gotoFitnessResultActivity record == null");
            return;
        }
        if (acquireWorkoutRecordByRecordId.getRecordModeType() == 1 && acquireWorkoutRecordByRecordId.acquireWearType() != 3) {
            String json = new Gson().toJson(acquireWorkoutRecordByRecordId);
            LogUtil.a("Track_TrackJumpUtils", "jumpTo H5 AI_FITNESS_COURSE");
            H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
            builder.addCustomizeArg("workoutRecord", json);
            builder.addPath("#/resultRecord?pullFrom=1");
            builder.addCustomizeJsModule(NotificationCompat.CATEGORY_SOCIAL, bzs.e().getCommonJsModule(NotificationCompat.CATEGORY_SOCIAL));
            builder.enableImageCache();
            bzs.e().loadH5ProApp(context, "com.huawei.health.h5.ai-fitness-course", builder);
            return;
        }
        if (acquireWorkoutRecordByRecordId.getRecordModeType() == 2) {
            LogUtil.a("Track_TrackJumpUtils", "jump to singleActionResultRecord");
            H5ProLaunchOption.Builder builder2 = new H5ProLaunchOption.Builder();
            builder2.addCustomizeArg("workoutRecord", new Gson().toJson(acquireWorkoutRecordByRecordId));
            builder2.addPath("#/SingleActionResultRecord?pullFrom=1");
            builder2.addCustomizeJsModule(NotificationCompat.CATEGORY_SOCIAL, bzs.e().getCommonJsModule(NotificationCompat.CATEGORY_SOCIAL));
            builder2.enableImageCache();
            bzs.e().loadH5ProApp(context, "com.huawei.health.h5.ai-fitness-course", builder2);
            return;
        }
        if (acquireWorkoutRecordByRecordId.acquireWearType() == 3) {
            HashMap hashMap = new HashMap(1);
            hashMap.put("click", 1);
            ixx.d().d(context, AnalyticsValue.HEALTH_FITNESS_LOOK_TV_RECORD_2040086.value(), hashMap, 0);
        }
        Bundle bundle = new Bundle();
        bundle.putInt("entrance", 1);
        bundle.putParcelable("workout_record", acquireWorkoutRecordByRecordId);
        AppRouter.b("/PluginFitnessAdvice/FitnessResultActivity").zF_(bundle).c(context);
    }
}
