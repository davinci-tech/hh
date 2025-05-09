package com.huawei.health.suggestion.h5pro;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import androidx.core.app.ActivityCompat;
import com.google.gson.Gson;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.devicemgr.api.constant.DataCallbackType;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.distributedservice.api.DistributedApi;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.suggestion.h5pro.CourseDetailH5Bridge;
import com.huawei.health.suggestion.model.DeviceResponse;
import com.huawei.health.suggestion.ui.fitness.helper.JumpConnectHelper;
import com.huawei.health.suggestion.util.CourseEquipmentConnectionTipsUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import defpackage.arx;
import defpackage.ase;
import defpackage.cun;
import defpackage.fey;
import defpackage.fgc;
import defpackage.fnq;
import defpackage.fny;
import defpackage.fot;
import defpackage.fpq;
import defpackage.ggr;
import defpackage.ggx;
import defpackage.gij;
import defpackage.koq;
import defpackage.mmp;
import defpackage.mnf;
import defpackage.mod;
import defpackage.mon;
import defpackage.nrh;
import defpackage.squ;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public class CourseDetailH5Bridge extends JsBaseModule implements JumpConnectHelper.JumpActivityHandleInterface, CourseEquipmentConnectionTipsUtil.DevicesConnectDialogCallback {
    private static final String COURSE_INFO = "courseInfo";
    private static final String COURSE_PARAMETER = "courseParameter";
    private static final String CURRENT_WEEK = "currentWeek";
    private static final int ERROR_CODE_FAIL = -1;
    private static final int FINISH_PROGRESS = 1;
    private static final String LANGUAGE = "language";
    private static final String PLAN_EXECUTE_TIME = "planExecuteTime";
    private static final String TAG = "Suggestion_CourseH5Repository";
    private mmp mCourseJumpParameter;
    private String mCurrentCourseInfo;
    private long mDeviceCallbackId;
    private DownloadUiCallback mDownloadUiCallback;
    private float mDownloaded;
    private FitWorkout mFitWorkout;
    private boolean mIsWorkoutDownloaded;
    private float mProgress;
    private float mUnDownloadedProgress;

    @JavascriptInterface
    public void pushCourseInfo(long j, String str) {
        ReleaseLogUtil.e(TAG, "pushCourseInfo enter", Long.valueOf(j), "courseInfo:", str);
        this.mCurrentCourseInfo = str;
        restData();
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "pushCourseInfo courseInfo null");
            onFailureCallback(j, "pushCourseInfo courseInfo null", -1);
            return;
        }
        try {
            FitWorkout b = mnf.b(arx.a(), new JSONObject(str));
            this.mFitWorkout = b;
            initCourseDb(b);
            if (this.mFitWorkout != null) {
                onSuccessCallback(j);
            } else {
                onFailureCallback(j, "pushCourseInfo fitWorkout null", -1);
            }
        } catch (JSONException e) {
            ReleaseLogUtil.c(TAG, "pushCourseInfo exception:", LogAnonymous.b((Throwable) e));
        }
    }

    private void restData() {
        this.mIsWorkoutDownloaded = false;
        this.mProgress = 0.0f;
    }

    private void initCourseDb(FitWorkout fitWorkout) {
        ((CourseApi) Services.c("CoursePlanService", CourseApi.class)).updateCourseDbData(fitWorkout);
    }

    @JavascriptInterface
    public void registerDeviceDataCallback(long j) {
        this.mDeviceCallbackId = j;
        ReleaseLogUtil.e(TAG, "registerDeviceDataCallback enter", Long.valueOf(j));
        cun.c().registerDataCallback(new DeviceStateCallback(j), DataCallbackType.EXERCISE_ADVICE_MANAGER, TAG);
        cun.c().registerDataCallback(new DeviceStateCallback(j), DataCallbackType.DEVICE_FONT_MANAGER, TAG);
    }

    @JavascriptInterface
    public void isRunCourseAbility(long j) {
        cun.c().sendDeviceData(22, 12, null, null, TAG);
        onSuccessCallback(j);
    }

    @JavascriptInterface
    public void sendCmdToGetDeviceLan(long j) {
        ReleaseLogUtil.e(TAG, "sendCmdToGetDeviceLan callbackId", Long.valueOf(j));
        cun.c().sendDeviceData(12, 2, null, null, TAG);
        onSuccessCallback(j);
    }

    @JavascriptInterface
    public void getDeviceInfo(long j) {
        ReleaseLogUtil.e(TAG, "getDeviceInfo callbackId", Long.valueOf(j));
        onSuccessCallback(j, cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, TAG));
    }

    @JavascriptInterface
    public void isConnectedHeartRateDeviceWear(long j) {
        ReleaseLogUtil.e(TAG, "isConnectedHeartRateDeviceWear callbackId", Long.valueOf(j));
        onSuccessCallback(j, Boolean.valueOf(ggx.a(BaseApplication.e().getApplicationContext(), gij.b())));
    }

    @JavascriptInterface
    public void checkHeartRateDeviceStatus(final long j) {
        ReleaseLogUtil.e(TAG, "checkHeartRateDeviceStatus callbackId", Long.valueOf(j));
        new fny(new Handler(), this.mContext).d(new IBaseResponseCallback() { // from class: com.huawei.health.suggestion.h5pro.CourseDetailH5Bridge$$ExternalSyntheticLambda0
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                CourseDetailH5Bridge.this.m502x750b970c(j, i, obj);
            }
        }, false);
    }

    /* renamed from: lambda$checkHeartRateDeviceStatus$0$com-huawei-health-suggestion-h5pro-CourseDetailH5Bridge, reason: not valid java name */
    /* synthetic */ void m502x750b970c(long j, int i, Object obj) {
        onSuccessCallback(j, obj);
    }

    @JavascriptInterface
    public void openBluetooth(long j) {
        ReleaseLogUtil.e(TAG, "checkHeartRateDeviceStatus callbackId", Long.valueOf(j));
        try {
            if (ActivityCompat.checkSelfPermission(this.mContext, "android.permission.BLUETOOTH_CONNECT") != 0) {
                ReleaseLogUtil.e(TAG, "checkHeartRateDeviceStatus NO PERMISSION");
                BluetoothAdapter.getDefaultAdapter().enable();
            } else {
                onSuccessCallback(j);
            }
        } catch (RuntimeException e) {
            ReleaseLogUtil.c(TAG, "user choose open BT error :", LogAnonymous.b((Throwable) e));
        }
    }

    @JavascriptInterface
    public void isSupportFaController(long j, int i) {
        ReleaseLogUtil.e(TAG, "isSupportFaController:", Integer.valueOf(i));
        onSuccessCallback(j, Boolean.valueOf(CourseControlManager.isSupportFaController(i)));
    }

    @JavascriptInterface
    public void registerInitFa(final long j, String str) {
        ReleaseLogUtil.e(TAG, "registerInitFa params", str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("planId");
            long optLong = jSONObject.optLong(PLAN_EXECUTE_TIME);
            this.mFitWorkout = getFitWorkout(str);
            CourseControlManager.initFa(this.mContext, this.mFitWorkout, optString, optLong, new UiCallback<Integer>() { // from class: com.huawei.health.suggestion.h5pro.CourseDetailH5Bridge.1
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str2) {
                    LogUtil.h(CourseDetailH5Bridge.TAG, "registerInitFa errorCode:", Integer.valueOf(i), "errorInfo:", str2);
                    CourseDetailH5Bridge.this.onFailureCallback(j, str2, i);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onSuccess(Integer num) {
                    LogUtil.h(CourseDetailH5Bridge.TAG, "registerInitFa onSuccess:", num);
                    CourseDetailH5Bridge.this.onSuccessCallback(j, num);
                }
            });
        } catch (JSONException e) {
            ReleaseLogUtil.c(TAG, "registerInitFa exception:", LogAnonymous.b((Throwable) e));
        }
    }

    @JavascriptInterface
    public void isSupportAiCourse(long j, int i) {
        LogUtil.a(TAG, "isSupportAiCourse aiType", Integer.valueOf(i));
        onSuccessCallback(j, Boolean.valueOf(fpq.b(i)));
    }

    @JavascriptInterface
    public void getPaceZoneArray(long j) {
        int[] e = fgc.e();
        if (e.length <= 1) {
            LogUtil.h(TAG, "getPaceZoneArray paceZoneArray is default");
            e = fgc.d();
        }
        onSuccessCallback(j, e);
    }

    @JavascriptInterface
    public void sendCourseToDevice(long j, String str) {
        ReleaseLogUtil.e(TAG, "sendCourseToDevice enter", Long.valueOf(j), "courseInfo:", str);
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "sendCourseToDevice courseInfo null");
            onFailureCallback(j, "sendCourseToDevice courseInfo null", -1);
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString(COURSE_INFO);
            String optString2 = jSONObject.optString("language");
            JSONObject jSONObject2 = new JSONObject(optString);
            if (TextUtils.isEmpty(optString2)) {
                optString2 = arx.a();
            }
            FitWorkout b = mnf.b(optString2, jSONObject2);
            if (b != null) {
                cun.c().pushFitRunCourseData(b);
                onSuccessCallback(j);
            } else {
                onFailureCallback(j, "sendCourseToDevice mFitWorkout null", -1);
            }
        } catch (JSONException e) {
            ReleaseLogUtil.c(TAG, "sendCourseToDevice exception:", LogAnonymous.b((Throwable) e));
        }
    }

    @JavascriptInterface
    public void startAiCourse(long j, String str) {
        ReleaseLogUtil.e(TAG, "startAiCourse courseParameter：", str);
        this.mFitWorkout = getFitWorkout(str);
        this.mCourseJumpParameter = getCourseJumpParameter(str);
        if (isIllegalData(j, str)) {
            return;
        }
        AiSportVoiceHelper.getInstance().setHelperType(FitWorkout.acquireComeFrom(this.mFitWorkout.acquireId()));
        ggr.b(1, this.mFitWorkout.acquireId(), this.mFitWorkout.acquireName());
        checkMultilingualAudio(j, true, new AnonymousClass2());
    }

    /* renamed from: com.huawei.health.suggestion.h5pro.CourseDetailH5Bridge$2, reason: invalid class name */
    class AnonymousClass2 extends UiCallback<Integer> {
        AnonymousClass2() {
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.h(CourseDetailH5Bridge.TAG, "checkMultilingualAudio errorInfo", str);
        }

        /* renamed from: lambda$onSuccess$1$com-huawei-health-suggestion-h5pro-CourseDetailH5Bridge$2, reason: not valid java name */
        /* synthetic */ void m505x97d979bf() {
            mon.d().launchActivity(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), new Intent(), new AppBundleLauncher.InstallCallback() { // from class: com.huawei.health.suggestion.h5pro.CourseDetailH5Bridge$2$$ExternalSyntheticLambda1
                @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
                public final boolean call(Context context, Intent intent) {
                    return CourseDetailH5Bridge.AnonymousClass2.this.m504x8723acfe(context, intent);
                }
            });
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onSuccess(Integer num) {
            HandlerExecutor.e(new Runnable() { // from class: com.huawei.health.suggestion.h5pro.CourseDetailH5Bridge$2$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    CourseDetailH5Bridge.AnonymousClass2.this.m505x97d979bf();
                }
            });
        }

        /* renamed from: lambda$onSuccess$0$com-huawei-health-suggestion-h5pro-CourseDetailH5Bridge$2, reason: not valid java name */
        /* synthetic */ boolean m504x8723acfe(Context context, Intent intent) {
            LogUtil.a(CourseDetailH5Bridge.TAG, "InteractiveTraining launchActivity");
            CourseControlManager.startAiCourse(CourseDetailH5Bridge.this.mContext, CourseDetailH5Bridge.this.mFitWorkout, CourseDetailH5Bridge.this.mCourseJumpParameter, CourseDetailH5Bridge.this.mIsWorkoutDownloaded);
            return false;
        }
    }

    @JavascriptInterface
    public void startSport(final long j, String str) {
        ReleaseLogUtil.e(TAG, "startCourse courseParameter：", str);
        this.mFitWorkout = getFitWorkout(str);
        this.mCourseJumpParameter = getCourseJumpParameter(str);
        if (isIllegalData(j, str)) {
            return;
        }
        checkMultilingualAudio(j, false, new UiCallback<Integer>() { // from class: com.huawei.health.suggestion.h5pro.CourseDetailH5Bridge.3
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str2) {
                LogUtil.h(CourseDetailH5Bridge.TAG, "checkMultilingualAudio errorInfo", str2);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onSuccess(Integer num) {
                if (!CourseDetailH5Bridge.this.mFitWorkout.isLongVideoCourse()) {
                    if (CourseDetailH5Bridge.this.mFitWorkout.isRunModelCourse()) {
                        CourseControlManager.startTrackActivity(CourseDetailH5Bridge.this.mContext, CourseDetailH5Bridge.this.mFitWorkout, CourseDetailH5Bridge.this.mCourseJumpParameter);
                        return;
                    } else {
                        CourseControlManager.startCoachActivity(CourseDetailH5Bridge.this.mContext, CourseDetailH5Bridge.this.mFitWorkout, CourseDetailH5Bridge.this.mCourseJumpParameter, false);
                        return;
                    }
                }
                CourseDetailH5Bridge courseDetailH5Bridge = CourseDetailH5Bridge.this;
                courseDetailH5Bridge.starLongCoachActivity(j, courseDetailH5Bridge.mCourseJumpParameter);
            }
        });
    }

    @JavascriptInterface
    public void startEquipment(long j, String str) {
        ReleaseLogUtil.e(TAG, "startEquipment courseParameter:", str);
        this.mFitWorkout = getFitWorkout(str);
        this.mCourseJumpParameter = getCourseJumpParameter(str);
        if (isIllegalData(j, str)) {
            return;
        }
        if (this.mCourseJumpParameter == null) {
            ReleaseLogUtil.d(TAG, "startCourse courseJumpParameter null");
            onFailureCallback(j, "startCourse courseJumpParameter null");
        } else {
            checkMultilingualAudio(j, false, new UiCallback<Integer>() { // from class: com.huawei.health.suggestion.h5pro.CourseDetailH5Bridge.4
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str2) {
                    LogUtil.h(CourseDetailH5Bridge.TAG, "checkMultilingualAudio errorInfo", str2);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onSuccess(Integer num) {
                    CourseEquipmentConnectionTipsUtil.e(CourseEquipmentConnectionTipsUtil.d(CourseDetailH5Bridge.this.mFitWorkout, CourseDetailH5Bridge.this.mCourseJumpParameter.c()), CourseDetailH5Bridge.TAG, CourseDetailH5Bridge.this);
                }
            });
        }
    }

    @JavascriptInterface
    public void getCourseUnDownMediaFilesLength(long j, String str) {
        ReleaseLogUtil.e(TAG, "startEquipment courseParameter:", str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            onSuccessCallback(j, Integer.valueOf(((CourseApi) Services.c("CoursePlanService", CourseApi.class)).getCourseMediaFilesLength(jSONObject.optString(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID), jSONObject.optString("version"))));
        } catch (JSONException e) {
            LogUtil.a(TAG, "getCourseUnDownMediaFilesLength exception", LogAnonymous.b((Throwable) e));
        }
    }

    @JavascriptInterface
    public void isPlanNeedUpdate(long j, String str) {
        ReleaseLogUtil.e(TAG, "isPlanNeedUpdate courseParameter:", str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            onSuccessCallback(j, Boolean.valueOf(ase.c(jSONObject.optInt(CURRENT_WEEK), jSONObject.optString("planId"))));
        } catch (JSONException e) {
            ReleaseLogUtil.c(TAG, "isPlanNeedUpdate exception", LogAnonymous.b((Throwable) e));
            onFailureCallback(j, "isPlanNeedUpdate JSONException");
        }
    }

    @JavascriptInterface
    public void isConnectRopeDevice(long j) {
        boolean a2 = CourseEquipmentConnectionTipsUtil.a();
        ReleaseLogUtil.e(TAG, "isConnectRopeDevice:", Boolean.valueOf(a2));
        onSuccessCallback(j, Boolean.valueOf(a2));
    }

    @JavascriptInterface
    public void getAvailableFaDevice(final long j) {
        ReleaseLogUtil.e(TAG, "getAvailableFaDevice:", Long.valueOf(j));
        ((DistributedApi) Services.c("DistributedService", DistributedApi.class)).getAvailableFaDevice(new UiCallback<List<?>>() { // from class: com.huawei.health.suggestion.h5pro.CourseDetailH5Bridge.5
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                ReleaseLogUtil.d(CourseDetailH5Bridge.TAG, "getAvailableFaDevice errorCode:", Integer.valueOf(i), "errorInfo:", str);
                CourseDetailH5Bridge.this.onFailureCallback(j, str);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onSuccess(List<?> list) {
                ReleaseLogUtil.e(CourseDetailH5Bridge.TAG, "getAvailableFaDevice", Boolean.valueOf(koq.b(list)));
                CourseDetailH5Bridge.this.onSuccessCallback(j, list);
            }
        });
    }

    @JavascriptInterface
    public void cancelDownloadCourseMediaFiles(long j, String str) {
        ReleaseLogUtil.e(TAG, "cancelDownloadCourseMediaFiles", Long.valueOf(j), "params:", str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID);
            int optInt = jSONObject.optInt("userDefinedType");
            ((CourseApi) Services.c("CoursePlanService", CourseApi.class)).cancelDownloadCourseMediaFiles(new fey.d().e(optString).d(optInt).c(jSONObject.optString("timbre")).a());
            DownloadUiCallback downloadUiCallback = this.mDownloadUiCallback;
            if (downloadUiCallback != null) {
                downloadUiCallback.mIsCancelDownload = true;
                onCompleteCallback(this.mDownloadUiCallback.mCallbackId, String.valueOf(1), 0);
            }
            onSuccessCallback(j);
        } catch (JSONException e) {
            onFailureCallback(j, "cancelDownloadCourseMediaFiles exception");
            ReleaseLogUtil.c(TAG, "registerInitFa exception:", LogAnonymous.b((Throwable) e));
        }
    }

    @Override // com.huawei.health.suggestion.util.CourseEquipmentConnectionTipsUtil.DevicesConnectDialogCallback
    public void devicesConnectDialogNavigation() {
        ReleaseLogUtil.e(TAG, "devicesConnectDialogNavigation");
        FitWorkout fitWorkout = this.mFitWorkout;
        if (fitWorkout == null) {
            ReleaseLogUtil.d(TAG, "devicesConnectDialogNavigation mFitWorkout == null");
        } else if (fitWorkout.isLongVideoCourse()) {
            CourseControlManager.startLongCoachActivity(this.mContext, this.mFitWorkout, this.mCourseJumpParameter);
        } else {
            CourseControlManager.startCoachActivity(this.mContext, this.mFitWorkout, this.mCourseJumpParameter, true);
        }
    }

    @Override // com.huawei.health.suggestion.util.CourseEquipmentConnectionTipsUtil.DevicesConnectDialogCallback
    public void devicesConnectDialogPositive() {
        ReleaseLogUtil.e(TAG, "devicesConnectDialogPositive");
        WorkoutRecord c = mod.c(this.mFitWorkout, this.mCourseJumpParameter);
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(c);
        CourseControlManager.devicesConnectDialogPositive(this.mContext, this.mFitWorkout, this.mCourseJumpParameter, arrayList);
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule, com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onDestroy() {
        super.onDestroy();
        ReleaseLogUtil.e(TAG, "onDestroy");
        FitWorkout fitWorkout = this.mFitWorkout;
        if (fitWorkout != null) {
            if (this.mIsWorkoutDownloaded) {
                this.mProgress = 1.0f;
            }
            ReleaseLogUtil.e(TAG, "onDestroy workoutId", fitWorkout.acquireId(), "mIsWorkoutDownloaded:", Boolean.valueOf(this.mIsWorkoutDownloaded));
            if (!CommonUtil.c(this.mProgress)) {
                mod.d(this.mFitWorkout.acquireId(), this.mProgress);
            }
            ((CourseApi) Services.c("CoursePlanService", CourseApi.class)).cancelDownloadCourseMediaFiles(new fey.d().e(this.mFitWorkout.acquireId()).d(this.mFitWorkout.getCourseDefineType()).a());
        }
        DownloadUiCallback downloadUiCallback = this.mDownloadUiCallback;
        if (downloadUiCallback != null) {
            downloadUiCallback.mIsCancelDownload = true;
        }
        fot.a().c();
        onCompleteCallback(this.mDeviceCallbackId, "onDestroy", 0);
        ((DistributedApi) Services.c("DistributedService", DistributedApi.class)).releaseDeviceManager();
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.JumpConnectHelper.JumpActivityHandleInterface
    public void startLongCoachAfter() {
        CourseControlManager.startLongCoachAfterAction(this.mFitWorkout, this.mCourseJumpParameter);
        JumpConnectHelper.c().b().b(TAG);
        JumpConnectHelper.c().i();
    }

    private void checkMultilingualAudio(final long j, final boolean z, final UiCallback<Integer> uiCallback) {
        CourseControlManager.checkMultilingualAudio(this.mContext, this.mFitWorkout, new UiCallback<Integer>() { // from class: com.huawei.health.suggestion.h5pro.CourseDetailH5Bridge.6
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                LogUtil.a(CourseDetailH5Bridge.TAG, "checkMultilingualAudio errorInfo:", str);
                CourseDetailH5Bridge.this.onFailureCallback(j, str);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onSuccess(Integer num) {
                if (!CourseDetailH5Bridge.this.mFitWorkout.isLongVideoCourse()) {
                    CourseDetailH5Bridge.this.downCourse(j, z, uiCallback);
                } else {
                    uiCallback.onSuccess(1);
                    CourseDetailH5Bridge.this.onCompleteCallback(j, String.valueOf(1), 0);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downCourse(long j, boolean z, UiCallback<Integer> uiCallback) {
        DownloadUiCallback downloadUiCallback = this.mDownloadUiCallback;
        if (downloadUiCallback != null) {
            downloadUiCallback.mIsCancelDownload = true;
        }
        CourseApi courseApi = (CourseApi) Services.c("CoursePlanService", CourseApi.class);
        float c = mod.c(this.mFitWorkout.acquireId());
        this.mDownloaded = c;
        ReleaseLogUtil.e(TAG, "downCourse mDownloaded:", Float.valueOf(c));
        float f = this.mDownloaded;
        this.mUnDownloadedProgress = 1.0f - f;
        onSuccessCallback(j, Float.valueOf(f));
        int courseMediaFilesLength = courseApi.getCourseMediaFilesLength(this.mFitWorkout.acquireId(), this.mFitWorkout.accquireVersion());
        ReleaseLogUtil.e(TAG, "downCourse leaveLength:", Integer.valueOf(courseMediaFilesLength));
        if (this.mDownloaded >= 1.0f && courseMediaFilesLength > 0) {
            mod.d(this.mFitWorkout.acquireId(), 0.0f);
            ReleaseLogUtil.e(TAG, "adjust progress");
            this.mDownloaded = 0.0f;
            this.mUnDownloadedProgress = 1.0f;
        }
        if (courseMediaFilesLength == 0) {
            onCompleteCallback(j, String.valueOf(1), 0);
            uiCallback.onSuccess(1);
            mod.d(this.mFitWorkout.acquireId(), 1.0f);
            ggr.a();
            return;
        }
        this.mDownloadUiCallback = new DownloadUiCallback(j, uiCallback, this);
        courseApi.downloadCourseMediaFiles(new fey.d().e(this.mFitWorkout.acquireId()).c(this.mFitWorkout.getTimbre()).d(this.mFitWorkout.accquireVersion()).d(this.mFitWorkout.getCourseDefineType()).a(), this.mDownloadUiCallback);
        FitWorkout fitWorkout = this.mFitWorkout;
        ggr.c(fitWorkout, CourseControlManager.getBiMapData(fitWorkout, this.mCourseJumpParameter), z);
        fnq.b(this.mFitWorkout);
    }

    static class DownloadUiCallback extends UiCallback<String> {
        private final long mCallbackId;
        private boolean mIsCancelDownload = false;
        private final UiCallback<Integer> mUiCallback;
        private WeakReference<CourseDetailH5Bridge> mWeakReferenceTrainDetail;

        DownloadUiCallback(long j, UiCallback<Integer> uiCallback, CourseDetailH5Bridge courseDetailH5Bridge) {
            this.mWeakReferenceTrainDetail = null;
            this.mCallbackId = j;
            this.mUiCallback = uiCallback;
            this.mWeakReferenceTrainDetail = new WeakReference<>(courseDetailH5Bridge);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            CourseDetailH5Bridge courseDetailH5Bridge = this.mWeakReferenceTrainDetail.get();
            ReleaseLogUtil.d(CourseDetailH5Bridge.TAG, "down fail-- errorCode = ", Integer.valueOf(i), " errorInfo = ", str);
            if (courseDetailH5Bridge != null) {
                courseDetailH5Bridge.onFailureCallback(this.mCallbackId, str, i);
            }
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onProgress(long j, long j2) {
            CourseDetailH5Bridge courseDetailH5Bridge = this.mWeakReferenceTrainDetail.get();
            if (courseDetailH5Bridge != null) {
                courseDetailH5Bridge.mProgress = (courseDetailH5Bridge.mUnDownloadedProgress * (j / j2)) + courseDetailH5Bridge.mDownloaded;
                if (courseDetailH5Bridge.mProgress > 1.0f) {
                    courseDetailH5Bridge.mProgress = 1.0f;
                    this.mUiCallback.onSuccess(0);
                }
                LogUtil.a(CourseDetailH5Bridge.TAG, "onProgress: ", Float.valueOf(courseDetailH5Bridge.mProgress));
                courseDetailH5Bridge.onSuccessCallback(this.mCallbackId, Float.valueOf(courseDetailH5Bridge.mProgress));
                return;
            }
            LogUtil.h(CourseDetailH5Bridge.TAG, "DownloadUiCallback courseDetailH5Bridge == null");
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public boolean isCanceled() {
            CourseDetailH5Bridge courseDetailH5Bridge = this.mWeakReferenceTrainDetail.get();
            if (courseDetailH5Bridge == null) {
                return true;
            }
            if (this.mIsCancelDownload) {
                courseDetailH5Bridge.onCompleteCallback(this.mCallbackId, String.valueOf(courseDetailH5Bridge.mProgress), 0);
            }
            return this.mIsCancelDownload;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onSuccess(String str) {
            LogUtil.a(CourseDetailH5Bridge.TAG, "down success--");
            CourseDetailH5Bridge courseDetailH5Bridge = this.mWeakReferenceTrainDetail.get();
            if (courseDetailH5Bridge != null) {
                courseDetailH5Bridge.mIsWorkoutDownloaded = true;
                courseDetailH5Bridge.onCompleteCallback(this.mCallbackId, String.valueOf(1), 0);
                this.mUiCallback.onSuccess(0);
                mod.d(courseDetailH5Bridge.mFitWorkout.acquireId(), 1.0f);
                CourseControlManager.recordTrainEvent(0, courseDetailH5Bridge.mFitWorkout, courseDetailH5Bridge.mCourseJumpParameter.ad());
                fot.a().a(courseDetailH5Bridge.mFitWorkout);
                return;
            }
            LogUtil.h(CourseDetailH5Bridge.TAG, "down success--");
        }
    }

    private boolean isIllegalData(long j, String str) {
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d(TAG, "startCourse courseParameter null");
            onFailureCallback(j, "startCourse courseParameter null");
            return true;
        }
        if (this.mFitWorkout == null) {
            ReleaseLogUtil.d(TAG, "startCourse fitWorkout null");
            onFailureCallback(j, "startCourse fitWorkout null");
            return true;
        }
        if (this.mCourseJumpParameter != null) {
            return false;
        }
        ReleaseLogUtil.d(TAG, "startCourse courseJumpParameter null");
        onFailureCallback(j, "startCourse courseJumpParameter null");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void starLongCoachActivity(final long j, final mmp mmpVar) {
        String s = mmpVar.s();
        LogUtil.a(TAG, "subtitleUrl:", s);
        if (TextUtils.isEmpty(s)) {
            CourseControlManager.startLongCoachActivity(this.mContext, this.mFitWorkout, mmpVar);
            onSuccessCallback(j, 1);
        } else if (!CommonUtil.aa(com.huawei.hwcommonmodel.application.BaseApplication.getContext())) {
            nrh.b(this.mContext, R.string._2130841884_res_0x7f02111c);
        } else if (new File(squ.k(s)).exists()) {
            downloadSubtitlesEndAndJump(j, mmpVar);
        } else {
            ((CourseApi) Services.a("CoursePlanService", CourseApi.class)).downloadSubtitles(mmpVar.s(), new UiCallback<String>() { // from class: com.huawei.health.suggestion.h5pro.CourseDetailH5Bridge.7
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    ReleaseLogUtil.d(CourseDetailH5Bridge.TAG, "downLoadLongSrt onFailure. errorCode = ", Integer.valueOf(i), ", errorInfo = ", str);
                    CourseDetailH5Bridge.this.downloadSubtitlesEndAndJump(j, mmpVar);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onSuccess(String str) {
                    ReleaseLogUtil.d(CourseDetailH5Bridge.TAG, "downLoadLongSrt onSuccess.");
                    CourseDetailH5Bridge.this.downloadSubtitlesEndAndJump(j, mmpVar);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadSubtitlesEndAndJump(final long j, final mmp mmpVar) {
        HandlerExecutor.e(new Runnable() { // from class: com.huawei.health.suggestion.h5pro.CourseDetailH5Bridge$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                CourseDetailH5Bridge.this.m503x86e3b1aa(mmpVar, j);
            }
        });
    }

    /* renamed from: lambda$downloadSubtitlesEndAndJump$1$com-huawei-health-suggestion-h5pro-CourseDetailH5Bridge, reason: not valid java name */
    /* synthetic */ void m503x86e3b1aa(mmp mmpVar, long j) {
        CourseControlManager.startLongCoachActivity(this.mContext, this.mFitWorkout, mmpVar);
        onSuccessCallback(j, 1);
    }

    class DeviceStateCallback implements IBaseResponseCallback {
        private final long mCallbackId;

        public DeviceStateCallback(long j) {
            this.mCallbackId = j;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a(CourseDetailH5Bridge.TAG, "err_code:", Integer.valueOf(i), " objData:", obj);
            if (i == 2) {
                if (obj == null) {
                    ReleaseLogUtil.e(CourseDetailH5Bridge.TAG, "GET_DEVICE_FONT_INFO objData == null");
                    DeviceResponse deviceResponse = new DeviceResponse();
                    deviceResponse.setLanguage(null);
                    deviceResponse.setErrorCode(2);
                    CourseDetailH5Bridge.this.onSuccessCallback(this.mCallbackId, deviceResponse);
                    return;
                }
                if (obj instanceof String[]) {
                    DeviceResponse deviceResponse2 = new DeviceResponse();
                    deviceResponse2.setLanguage((String[]) obj);
                    deviceResponse2.setErrorCode(2);
                    CourseDetailH5Bridge.this.onSuccessCallback(this.mCallbackId, deviceResponse2);
                    return;
                }
                return;
            }
            if (i != 12) {
                if (i == 13) {
                    DeviceResponse deviceResponse3 = new DeviceResponse();
                    deviceResponse3.setSendResult(((Integer) obj).intValue());
                    deviceResponse3.setErrorCode(13);
                    CourseDetailH5Bridge.this.onSuccessCallback(this.mCallbackId, deviceResponse3);
                    return;
                }
                LogUtil.h(CourseDetailH5Bridge.TAG, "COMMAND_ID not found!");
                return;
            }
            if (obj instanceof Integer) {
                int intValue = ((Integer) obj).intValue();
                DeviceResponse deviceResponse4 = new DeviceResponse();
                deviceResponse4.setAbility(intValue);
                deviceResponse4.setErrorCode(12);
                CourseDetailH5Bridge.this.onSuccessCallback(this.mCallbackId, deviceResponse4);
            }
        }
    }

    private FitWorkout getFitWorkout(String str) {
        try {
            String optString = new JSONObject(str).optString(COURSE_INFO);
            if (TextUtils.isEmpty(optString)) {
                ReleaseLogUtil.d(TAG, "getFitWorkout courseInfoStr");
                return null;
            }
            if (optString.equals(this.mCurrentCourseInfo) && this.mFitWorkout != null) {
                ReleaseLogUtil.e(TAG, "getFitWorkout same data");
                return this.mFitWorkout;
            }
            FitWorkout b = mnf.b(arx.a(), new JSONObject(optString));
            initCourseDb(b);
            return b;
        } catch (JSONException e) {
            ReleaseLogUtil.c(TAG, "getFitWorkout exception:", LogAnonymous.b((Throwable) e));
            return null;
        }
    }

    public mmp getCourseJumpParameter(String str) {
        try {
            String optString = new JSONObject(str).optString(COURSE_PARAMETER);
            if (TextUtils.isEmpty(optString)) {
                ReleaseLogUtil.d(TAG, "getFitWorkout courseInfoStr");
            }
            return (mmp) new Gson().fromJson(optString, mmp.class);
        } catch (JSONException e) {
            ReleaseLogUtil.c(TAG, "getCourseJumpParameter exception:", LogAnonymous.b((Throwable) e));
            return null;
        }
    }
}
