package defpackage;

import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.basesport.helper.HeartRateConfigHelper;
import com.huawei.health.healthdatamgr.api.HealthDataMgrApi;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcloudmodel.model.unite.GetRunLevelRsp;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf;
import com.huawei.hwcommonmodel.fitnessdatatype.Vo2maxDetail;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.up.model.UserInfomation;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
public class dtk {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f11830a = new Object();
    private static volatile dtk e;
    private int b;
    private int c;
    private int d;
    private int g;
    private float h;
    private float i;
    private int j;

    public static dtk b() {
        if (e == null) {
            synchronized (f11830a) {
                if (e == null) {
                    e = new dtk();
                }
            }
        }
        return e;
    }

    public void c(final int i, final ResponseCallback responseCallback) {
        ReleaseLogUtil.e("R_HRControl_PersonalParamHelper", "initPersonParam callback = ", responseCallback);
        if (responseCallback == null) {
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: dtj
            @Override // java.lang.Runnable
            public final void run() {
                dtk.this.a(i, responseCallback);
            }
        });
    }

    /* synthetic */ void a(int i, ResponseCallback responseCallback) {
        ReleaseLogUtil.e("R_HRControl_PersonalParamHelper", "initPersonParam start");
        final CountDownLatch countDownLatch = new CountDownLatch(4);
        final int[] iArr = new int[3];
        final UserInfomation[] userInfomationArr = new UserInfomation[1];
        b(new ResponseCallback() { // from class: dtm
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i2, Object obj) {
                dtk.b(iArr, userInfomationArr, countDownLatch, i2, (UserInfomation) obj);
            }
        });
        final int[] iArr2 = new int[1];
        d(new ResponseCallback() { // from class: dts
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i2, Object obj) {
                dtk.b(iArr, iArr2, countDownLatch, i2, (Integer) obj);
            }
        });
        final float[] fArr = new float[1];
        e(new ResponseCallback() { // from class: dto
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i2, Object obj) {
                dtk.e(iArr, fArr, countDownLatch, i2, (Float) obj);
            }
        });
        HeartRateConfigHelper heartRateConfigHelper = new HeartRateConfigHelper(i, new HeartRateConfigHelper.OnConfigHelperListener() { // from class: dtq
            @Override // com.huawei.health.basesport.helper.HeartRateConfigHelper.OnConfigHelperListener
            public final void onInitComplete() {
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await(5000L, TimeUnit.MILLISECONDS);
            c(userInfomationArr[0], iArr2[0], fArr[0], heartRateConfigHelper);
            if (a(iArr)) {
                responseCallback.onResponse(0, null);
            } else {
                responseCallback.onResponse(-1, null);
            }
        } catch (InterruptedException unused) {
            ReleaseLogUtil.d("R_HRControl_PersonalParamHelper", "initPersonParam time out");
            c(null, 0, 0.0f, null);
            responseCallback.onResponse(-2, null);
        }
        ReleaseLogUtil.e("R_HRControl_PersonalParamHelper", "initPersonParam end");
    }

    static /* synthetic */ void b(int[] iArr, UserInfomation[] userInfomationArr, CountDownLatch countDownLatch, int i, UserInfomation userInfomation) {
        iArr[0] = i;
        userInfomationArr[0] = userInfomation;
        countDownLatch.countDown();
    }

    static /* synthetic */ void b(int[] iArr, int[] iArr2, CountDownLatch countDownLatch, int i, Integer num) {
        iArr[1] = i;
        iArr2[0] = num.intValue();
        countDownLatch.countDown();
    }

    static /* synthetic */ void e(int[] iArr, float[] fArr, CountDownLatch countDownLatch, int i, Float f) {
        iArr[2] = i;
        fArr[0] = f.floatValue();
        countDownLatch.countDown();
    }

    public int a() {
        return this.d;
    }

    public float g() {
        return this.i;
    }

    public float c() {
        return this.h;
    }

    public int h() {
        return this.g;
    }

    public int d() {
        return this.j;
    }

    public int e() {
        return this.b;
    }

    private boolean a(int[] iArr) {
        for (int i : iArr) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    private void c(UserInfomation userInfomation, int i, float f, HeartRateConfigHelper heartRateConfigHelper) {
        float f2;
        int i2;
        Object[] objArr = new Object[8];
        int i3 = 0;
        objArr[0] = "setPersonalParamOfProcessed userInformation = ";
        objArr[1] = userInfomation == null ? Constants.NULL : userInfomation.toString();
        objArr[2] = ", vo2Max = ";
        objArr[3] = Integer.valueOf(i);
        objArr[4] = ", runData = ";
        objArr[5] = Float.valueOf(f);
        objArr[6] = ", heartRateConfigHelper = ";
        objArr[7] = heartRateConfigHelper;
        ReleaseLogUtil.e("R_HRControl_PersonalParamHelper", objArr);
        if (userInfomation != null) {
            i3 = userInfomation.getAgeOrDefaultValue();
            f2 = userInfomation.getWeightOrDefaultValue();
            i2 = userInfomation.getHeightOrDefaultValue();
        } else {
            f2 = 0.0f;
            i2 = 0;
        }
        int a2 = a(heartRateConfigHelper);
        int e2 = e(heartRateConfigHelper);
        this.d = dtp.c(i3);
        this.i = dtp.e(f2);
        this.c = dtp.e(i2);
        this.g = dtp.b(i);
        this.h = dtp.e(f, i, f2, i2);
        this.j = dtp.a(a2);
        this.b = dtp.d(e2, i3);
        ReleaseLogUtil.e("R_HRControl_PersonalParamHelper", "setPersonalParamOfProcessed mAgeOfProcessed = ", Integer.valueOf(this.d), ", mWeightOfProcessed = ", Float.valueOf(this.i), ", mHeightOfProcessed = ", Integer.valueOf(this.c), ", mVo2MaxOfProcessed = ", Integer.valueOf(this.g), ", mRunDataOfProcessed = ", Float.valueOf(this.h), ", mRestHeartRateOfProcessed = ", Integer.valueOf(this.j), ", mMaxHeartRateOfProcessed = ", Integer.valueOf(this.b));
    }

    private void b(ResponseCallback<UserInfomation> responseCallback) {
        UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.a("HWUserProfileMgr", UserProfileMgrApi.class);
        LogUtil.a("HRControl_PersonalParamHelper", "getUserInfo userProfileMgrApi = ", userProfileMgrApi);
        if (userProfileMgrApi == null) {
            responseCallback.onResponse(-1, null);
        } else {
            responseCallback.onResponse(0, userProfileMgrApi.getLocalUserInfo());
        }
    }

    private void d(final ResponseCallback<Integer> responseCallback) {
        HealthDataMgrApi healthDataMgrApi = (HealthDataMgrApi) Services.c("HWHealthDataMgr", HealthDataMgrApi.class);
        LogUtil.a("HRControl_PersonalParamHelper", "getVo2Max healthDataMgrApi = ", healthDataMgrApi);
        if (healthDataMgrApi == null) {
            responseCallback.onResponse(-1, 0);
        } else {
            LogUtil.a("HRControl_PersonalParamHelper", "getVo2Max start");
            healthDataMgrApi.getLastVo2max(new IBaseResponseCallback() { // from class: dtl
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    dtk.a(ResponseCallback.this, i, obj);
                }
            });
        }
    }

    static /* synthetic */ void a(ResponseCallback responseCallback, int i, Object obj) {
        LogUtil.a("HRControl_PersonalParamHelper", "getVo2Max end errorCode = ", Integer.valueOf(i), ", objData = ", obj);
        if (i != 0) {
            responseCallback.onResponse(-1, 0);
        } else if (obj instanceof Vo2maxDetail) {
            responseCallback.onResponse(0, Integer.valueOf(((Vo2maxDetail) obj).getVo2maxValue()));
        } else {
            responseCallback.onResponse(-1, 0);
        }
    }

    private void e(final ResponseCallback<Float> responseCallback) {
        int a2 = ggl.a(new Date(System.currentTimeMillis()));
        LogUtil.a("HRControl_PersonalParamHelper", "getLastRunData start");
        eme.b().getUserRunLevelDataByRq(a2, a2, 0, new IBaseResponseCallback() { // from class: dtn
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                dtk.c(ResponseCallback.this, i, obj);
            }
        });
    }

    static /* synthetic */ void c(ResponseCallback responseCallback, int i, Object obj) {
        LogUtil.a("HRControl_PersonalParamHelper", "getLastRunData end errorCode = ", Integer.valueOf(i), ", objData = ", obj);
        Float valueOf = Float.valueOf(0.0f);
        if (i != 200) {
            responseCallback.onResponse(-1, valueOf);
        } else if (obj instanceof GetRunLevelRsp) {
            responseCallback.onResponse(0, Float.valueOf(((GetRunLevelRsp) obj).getUserRunLevelData().getLastCurrentRunLevel()));
        } else {
            responseCallback.onResponse(-1, valueOf);
        }
    }

    private int a(HeartRateConfigHelper heartRateConfigHelper) {
        HeartZoneConf a2;
        if (heartRateConfigHelper == null || (a2 = heartRateConfigHelper.a()) == null) {
            return 0;
        }
        int restHeartRate = a2.getRestHeartRate();
        ReleaseLogUtil.e("R_HRControl_PersonalParamHelper", "getRestHeartRate restHeartRate = ", Integer.valueOf(restHeartRate));
        return restHeartRate;
    }

    private int e(HeartRateConfigHelper heartRateConfigHelper) {
        HeartZoneConf a2;
        if (heartRateConfigHelper == null || (a2 = heartRateConfigHelper.a()) == null) {
            return 0;
        }
        int maxThreshold = a2.getMaxThreshold();
        ReleaseLogUtil.e("R_HRControl_PersonalParamHelper", "getMaxHeartRate maxHeartRate = ", Integer.valueOf(maxThreshold));
        return maxThreshold;
    }
}
