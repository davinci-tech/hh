package com.huawei.health.sport.utils;

import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.text.TextUtils;
import androidx.core.app.ActivityCompat;
import com.google.gson.annotations.SerializedName;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.courseplanservice.api.SportServiceApi;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.sport.utils.SportSupportUtil;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import defpackage.ash;
import defpackage.cun;
import defpackage.cwi;
import defpackage.jah;
import defpackage.mwq;
import defpackage.mww;
import defpackage.nrv;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.utils.StringUtils;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class SportSupportUtil {

    /* loaded from: classes4.dex */
    public interface OnFitnessGetFilterTabListener {
        void onFilterTabSuccess(boolean z);
    }

    public static boolean b(int i) {
        return i == 258 || i == 264;
    }

    public static boolean c(int i) {
        return i == 258 || i == 264 || i == 257;
    }

    public static int d(int i) {
        if (i == 258) {
            return 1;
        }
        if (i != 259) {
            return i != 264 ? 0 : 5;
        }
        return 3;
    }

    public static boolean e(int i) {
        return i == 400000 || i == 400001 || i == 400002 || i == 400003;
    }

    public static boolean f(int i) {
        return i == 258 || i == 264 || i == 259;
    }

    public static boolean i(int i) {
        if (i == 264 || i == 265 || i == 273 || i == 274) {
            return true;
        }
        switch (i) {
            case 257:
            case 258:
            case 259:
            case 260:
                return true;
            default:
                switch (i) {
                    case 281:
                    case 282:
                    case 283:
                        return true;
                    default:
                        return false;
                }
        }
    }

    public static boolean g() {
        return (o() || k() || a() || m()) ? false : true;
    }

    public static boolean o() {
        return (p() || nsn.ae(BaseApplication.e())) ? false : true;
    }

    public static boolean k() {
        return e() || d();
    }

    public static boolean e() {
        return j() && !o();
    }

    public static boolean j() {
        return (LoginInit.getInstance(BaseApplication.e()).isKidAccount() || Utils.l() || CommonUtil.bu()) ? false : true;
    }

    public static boolean d() {
        return a() && j();
    }

    public static boolean m() {
        return !p() && (h() || n());
    }

    public static boolean n() {
        return Utils.d() == 1;
    }

    public static boolean h() {
        if (mwq.d().c() != -1) {
            return b() || c();
        }
        return false;
    }

    public static boolean i() {
        int modelType = mww.d().getModelType();
        LogUtil.a("Track_SportSupportUtil", "isSupportAiSportExam the modelType = ", Integer.valueOf(modelType));
        return modelType != -1 && (b() || c());
    }

    public static boolean c() {
        return a(1, 90);
    }

    public static boolean b() {
        return a(0, 270);
    }

    private static boolean a(final int i, final int i2) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        Boolean c = c(i, i2);
        if (c != null) {
            return c.booleanValue();
        }
        ThreadPoolManager.d().d("Track_SportSupportUtilgetCamera", new Runnable() { // from class: fgk
            @Override // java.lang.Runnable
            public final void run() {
                SportSupportUtil.d(i, i2, countDownLatch);
            }
        });
        try {
            countDownLatch.await(1L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            ReleaseLogUtil.b("Track_SportSupportUtil", "exception happens = ", e.getMessage());
        }
        Boolean c2 = c(i, i2);
        if (c2 == null) {
            return false;
        }
        return c2.booleanValue();
    }

    public static /* synthetic */ void d(int i, int i2, CountDownLatch countDownLatch) {
        Boolean d = d(i, i2);
        if (d != null) {
            a(i, i2, d.booleanValue());
        }
        countDownLatch.countDown();
    }

    private static Boolean d(int i, int i2) {
        Object systemService = BaseApplication.e().getSystemService("camera");
        if (!(systemService instanceof CameraManager)) {
            LogUtil.h("Track_SportSupportUtil", "initCameraInfo object instanceof CameraManager false");
            return null;
        }
        CameraManager cameraManager = (CameraManager) systemService;
        try {
            for (String str : cameraManager.getCameraIdList()) {
                if (!StringUtils.g(str)) {
                    CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics(str);
                    Integer num = (Integer) cameraCharacteristics.get(CameraCharacteristics.LENS_FACING);
                    Integer num2 = (Integer) cameraCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION);
                    if (num != null && num.intValue() == i && num2 != null && num2.intValue() == i2) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            LogUtil.b("Track_SportSupportUtil", "check front camera  CameraAccessException ", LogAnonymous.b((Throwable) e));
            return null;
        }
    }

    private static void a(int i, int i2, boolean z) {
        String a2 = nrv.a(new a(String.valueOf(z), System.currentTimeMillis() + TimeUnit.DAYS.toMillis(3L)));
        SharedPreferenceManager.e(BaseApplication.e(), String.valueOf(20002), b(i, i2), a2, (StorageParams) null);
    }

    private static Boolean c(int i, int i2) {
        a aVar = (a) nrv.b(SharedPreferenceManager.b(BaseApplication.e(), String.valueOf(20002), b(i, i2)), a.class);
        if (aVar == null || !aVar.b()) {
            return null;
        }
        return Boolean.valueOf(aVar.a());
    }

    private static String b(int i, int i2) {
        return "hascamera_" + i + "_" + i2;
    }

    public static boolean a(int i) {
        return c(i) || i == 283 || e(i);
    }

    public static boolean a() {
        if (CommonUtil.bb()) {
            return false;
        }
        if (!Utils.o()) {
            return LanguageUtil.m(BaseApplication.e());
        }
        if (Utils.l()) {
            return false;
        }
        String t = t();
        String b = ash.b(t);
        b(t, (OnFitnessGetFilterTabListener) null);
        if (TextUtils.isEmpty(b)) {
            return false;
        }
        return Boolean.parseBoolean(b);
    }

    public static void a(OnFitnessGetFilterTabListener onFitnessGetFilterTabListener) {
        if (onFitnessGetFilterTabListener == null) {
            LogUtil.h("Track_SportSupportUtil", "isFitnessOpen, getFilterTabListener is null.");
            return;
        }
        if (!Utils.o()) {
            onFitnessGetFilterTabListener.onFilterTabSuccess(LanguageUtil.m(BaseApplication.e()));
        } else if (Utils.l()) {
            onFitnessGetFilterTabListener.onFilterTabSuccess(false);
        } else {
            b(t(), onFitnessGetFilterTabListener);
        }
    }

    public static boolean f() {
        return !LanguageUtil.m(BaseApplication.e()) || Utils.o();
    }

    public static boolean l() {
        return !p() && !LoginInit.getInstance(BaseApplication.e()).isKidAccount() && q() && s();
    }

    private static boolean s() {
        return !Utils.l();
    }

    private static boolean q() {
        boolean bv = CommonUtil.bv();
        boolean bu = CommonUtil.bu();
        ReleaseLogUtil.b("Track_SportSupportUtil", "isSupportGolfConfig isReleaseVersion = ", Boolean.valueOf(bv), ", isDemoVersion = ", Boolean.valueOf(bu));
        if (!bv && !bu) {
            return true;
        }
        String e = jah.c().e("support_golf_tab");
        ReleaseLogUtil.b("Track_SportSupportUtil", "isSupportGolfConfig supportValue = ", e);
        return "true".equals(e);
    }

    public static boolean b(MotionPathSimplify motionPathSimplify) {
        return motionPathSimplify != null && 1 == motionPathSimplify.getExtendDataInt("extra_Sport_Type");
    }

    public static boolean j(int i) {
        boolean z = i == 259 || i == 265;
        if (!z) {
            ReleaseLogUtil.b("Track_SportSupportUtil", "isSupportReverseLink sportType:", Integer.valueOf(i), " isSupport:", false);
            return false;
        }
        boolean z2 = ActivityCompat.checkSelfPermission(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), "android.permission.START_ACTIVITIES_FROM_BACKGROUND") == 0;
        if (!z2) {
            ReleaseLogUtil.b("Track_SportSupportUtil", "isSupportReverseLink sportType:", Integer.valueOf(i), " isSportSupport:", Boolean.valueOf(z), " isPhoneSupport:", false);
            return false;
        }
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "Track_SportSupportUtil");
        if (deviceInfo == null) {
            return false;
        }
        boolean c = cwi.c(deviceInfo, com.huawei.hms.kit.awareness.barrier.internal.e.a.K);
        ReleaseLogUtil.b("Track_SportSupportUtil", "isSupportReverseLink sportType:", Integer.valueOf(i), " isSportSupport:", Boolean.valueOf(z), " isPhoneSupport:", Boolean.valueOf(z2), " isDeviceSupport:", Boolean.valueOf(c));
        return c;
    }

    private static boolean p() {
        return EnvironmentInfo.k();
    }

    private static String t() {
        return "coachGetFilterTabStatus_" + LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1010);
    }

    private static void b(String str, OnFitnessGetFilterTabListener onFitnessGetFilterTabListener) {
        long n = CommonUtil.n(BaseApplication.e(), ash.b("coachGetFilterTabTime"));
        if (TextUtils.isEmpty(ash.b(str))) {
            c(str, onFitnessGetFilterTabListener);
        } else if (System.currentTimeMillis() - n <= 3600000) {
            LogUtil.a("Track_SportSupportUtil", "requestFilterTab, coachTime is less than one hour.");
            e(str, onFitnessGetFilterTabListener);
        } else {
            c(str, onFitnessGetFilterTabListener);
        }
    }

    private static void c(final String str, final OnFitnessGetFilterTabListener onFitnessGetFilterTabListener) {
        LogUtil.a("Track_SportSupportUtil", "requestFilterTab, start.");
        SportServiceApi sportServiceApi = (SportServiceApi) Services.a("CoursePlanService", SportServiceApi.class);
        if (sportServiceApi == null) {
            LogUtil.h("Track_SportSupportUtil", "onCreate, sportServiceApi is null.");
            e(str, onFitnessGetFilterTabListener);
        } else {
            sportServiceApi.getFilterTab("10014", new UiCallback<Boolean>() { // from class: com.huawei.health.sport.utils.SportSupportUtil.2
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str2) {
                    LogUtil.h("Track_SportSupportUtil", "getFilterTab, onFailure errorCode = ", Integer.valueOf(i), ", errorInfo = ", str2);
                    SportSupportUtil.e(str, onFitnessGetFilterTabListener);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(Boolean bool) {
                    if (bool == null) {
                        LogUtil.h("Track_SportSupportUtil", "getFilterTab, onSuccess data is null.");
                        SportSupportUtil.e(str, onFitnessGetFilterTabListener);
                        return;
                    }
                    LogUtil.a("Track_SportSupportUtil", "getFilterTab, onSuccess data = ", bool);
                    ash.a("coachGetFilterTabTime", String.valueOf(System.currentTimeMillis()));
                    ash.a(str, String.valueOf(bool));
                    OnFitnessGetFilterTabListener onFitnessGetFilterTabListener2 = onFitnessGetFilterTabListener;
                    if (onFitnessGetFilterTabListener2 != null) {
                        onFitnessGetFilterTabListener2.onFilterTabSuccess(bool.booleanValue());
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(String str, OnFitnessGetFilterTabListener onFitnessGetFilterTabListener) {
        if (onFitnessGetFilterTabListener == null) {
            return;
        }
        String b = ash.b(str);
        if (!TextUtils.isEmpty(b)) {
            onFitnessGetFilterTabListener.onFilterTabSuccess(Boolean.parseBoolean(b));
        } else {
            onFitnessGetFilterTabListener.onFilterTabSuccess(false);
        }
    }

    /* loaded from: classes4.dex */
    public static class a {

        @SerializedName("value")
        private String c;

        @SerializedName("expires")
        private long d;

        public a(String str, long j) {
            this.c = str;
            this.d = j;
        }

        public String a() {
            return this.c;
        }

        public boolean b() {
            return this.d > System.currentTimeMillis();
        }
    }
}
