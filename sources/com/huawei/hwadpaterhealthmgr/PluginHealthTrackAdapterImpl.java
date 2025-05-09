package com.huawei.hwadpaterhealthmgr;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.SparseArray;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.gson.Gson;
import com.huawei.exercise.modle.RunPlanRecordInfo;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.model.MeasureResult;
import com.huawei.health.userlabelmgr.api.UserLabelServiceApi;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter;
import com.huawei.healthcloud.plugintrack.manager.inteface.ITreadmillStyleCallback;
import com.huawei.healthcloud.plugintrack.manager.inteface.LocalPressureCallback;
import com.huawei.healthcloud.plugintrack.model.IHeartRateCallback;
import com.huawei.healthcloud.plugintrack.model.IRealStepCallback;
import com.huawei.healthcloud.plugintrack.model.IRidePostureDataCallback;
import com.huawei.healthcloud.plugintrack.model.IRunningPostureCallback;
import com.huawei.healthcloud.plugintrack.model.IStepRateCallback;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthApi;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.motion.HealthOpenSDK;
import com.huawei.hihealth.motion.IExecuteResult;
import com.huawei.hihealth.motion.IFlushResult;
import com.huawei.hihealth.motion.RealStepCallback;
import com.huawei.hwadpaterhealthmgr.PluginHealthTrackAdapterImpl;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf;
import com.huawei.hwdataaccessmodel.utils.StorageDataCallback;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwfoundationmodel.trackmodel.IReportDataCallback;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwfoundationmodel.trackmodel.StepRateData;
import com.huawei.hwidauth.datatype.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.utils.FoundationCommonUtil;
import com.huawei.utils.TreadmillManager;
import defpackage.blt;
import defpackage.bnl;
import defpackage.cej;
import defpackage.ffn;
import defpackage.ffs;
import defpackage.ffw;
import defpackage.gmz;
import defpackage.gtw;
import defpackage.gtx;
import defpackage.gvt;
import defpackage.gwe;
import defpackage.gwk;
import defpackage.gwo;
import defpackage.gwp;
import defpackage.gxn;
import defpackage.gyc;
import defpackage.iwz;
import defpackage.ixx;
import defpackage.jbr;
import defpackage.jca;
import defpackage.jdx;
import defpackage.jfa;
import defpackage.jfc;
import defpackage.jpt;
import defpackage.jpu;
import defpackage.knv;
import defpackage.kon;
import defpackage.koq;
import defpackage.kor;
import defpackage.kvq;
import defpackage.sqi;
import defpackage.sql;
import defpackage.sqs;
import health.compact.a.CommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.StorageResult;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class PluginHealthTrackAdapterImpl implements PluginSportTrackAdapter {
    private static final int DEFAULT_DELAY_INTERVAL = 1000;
    private static final int DEFAULT_POSTURE_DELAY_INTERVAL = 5000;
    private static final int HEART_RATE_DISTRIBUTION_SIZE = 6;
    private static final Object LOCK = new Object();
    private static final int MSG_REPORT_CURRENT_RIDE_POSTURE = 2005;
    private static final int MSG_REPORT_CURRENT_RUN_POSTURE = 2004;
    private static final int MSG_REPORT_CURRENT_STEPS = 2003;
    private static final int MSG_REPORT_STEP_FREQUENCY = 2002;
    private static final int MSG_STEP_FREQUENCY_TIMER = 2001;
    private static final int OVER_TIME_INTERVAL = 30000;
    private static final int OVER_TIME_MSG = 2;
    private static final int SUCCESS = 1;
    private static final int SYNC_CLOUD_MSG = 1;
    private static final String TAG = "Track_PluginHealthTrackAdapterImpl";
    private static int sCurrentSteps = -1;
    private static PluginHealthTrackAdapterImpl sInstance;
    private Context mContext;
    private ExtendHandler mExtendHandler;
    private HealthOpenSDK mHealthOpenSdk;
    private HeartZoneConf mHeartZoneConfigInfo;
    private a mSimpleStepFrequency;
    private int mStepGetTimes;
    private TreadmillManager mTreadmillManager;
    private sqs mTreadmillStepPointData;
    private UserProfileMgrApi mUserProfileMgrApi;
    private IHeartRateCallback mHeartRateCallback = null;
    private IStepRateCallback mStepRateCallback = null;
    private ITreadmillStyleCallback mTreadmillStyleCallback = null;
    private IRunningPostureCallback mRunningPostureCallback = null;
    private IReportDataCallback mReportDataCallback = null;
    private IRidePostureDataCallback mRidePostureCallback = null;
    private String mProductId = null;
    private String mUniqueId = null;
    private IRealStepCallback mRealStepCallback = null;
    private IRealStepCallback mCurrentStepCallback = null;
    private List<Integer> mRidePostureDataSuccessList = null;
    private boolean mIsSportResumed = true;
    private boolean mIsScreenOn = true;
    private volatile ffs mRunningPostureTemp = new ffs();
    private volatile ffn mRidePostureTemp = new ffn();
    private final HiSubscribeListener mRidePostureSubscribeListener = new HiSubscribeListener() { // from class: com.huawei.hwadpaterhealthmgr.PluginHealthTrackAdapterImpl.3
        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            if (koq.c(list)) {
                LogUtil.a(PluginHealthTrackAdapterImpl.TAG, "regRidePostureSubscribeListener success");
                PluginHealthTrackAdapterImpl.this.mRidePostureDataSuccessList = list;
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            if (hiHealthData == null) {
                LogUtil.b(PluginHealthTrackAdapterImpl.TAG, "onChange ridePostureData HiHealthData is null!");
                return;
            }
            LogUtil.a(PluginHealthTrackAdapterImpl.TAG, "ride newValue=", hiHealthData.toString());
            PluginHealthTrackAdapterImpl.this.mRidePostureTemp.c(hiHealthData.getEndTime());
            PluginHealthTrackAdapterImpl.this.mRidePostureTemp.b(hiHealthData.getSubType(), hiHealthData.getValue());
        }
    };
    private List<Integer> mRunningPostureSuccessList = null;
    private final HiSubscribeListener mRunningPostureSubscribeListener = new HiSubscribeListener() { // from class: com.huawei.hwadpaterhealthmgr.PluginHealthTrackAdapterImpl.15
        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            if (koq.c(list)) {
                LogUtil.a(PluginHealthTrackAdapterImpl.TAG, "regRunningPostureListener success");
                PluginHealthTrackAdapterImpl.this.mRunningPostureSuccessList = list;
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            if (hiHealthData == null) {
                LogUtil.b(PluginHealthTrackAdapterImpl.TAG, "onChange runningPosture HiHealthData is null!");
                return;
            }
            LogUtil.a(PluginHealthTrackAdapterImpl.TAG, "newValue=", hiHealthData.toString());
            PluginHealthTrackAdapterImpl.this.mRunningPostureTemp.e(hiHealthData.getEndTime());
            if (DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_STEPS.value() == hiHealthData.getSubType()) {
                if (!PluginHealthTrackAdapterImpl.this.mIsSportResumed) {
                    PluginHealthTrackAdapterImpl.this.mIsSportResumed = true;
                    return;
                }
                gtw.e().c(new knv(j, (int) (hiHealthData.getValue() + gtw.e().e(10))), 10);
                return;
            }
            PluginHealthTrackAdapterImpl.this.mRunningPostureTemp.c(hiHealthData.getSubType(), hiHealthData.getValue());
        }
    };
    private List<Integer> mHeartRateSuccessList = null;
    private HiSubscribeListener mSubscribeListener = new HiSubscribeListener() { // from class: com.huawei.hwadpaterhealthmgr.PluginHealthTrackAdapterImpl.11
        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            if (koq.c(list)) {
                LogUtil.a(PluginHealthTrackAdapterImpl.TAG, "registerHeartRateListener success");
                PluginHealthTrackAdapterImpl.this.mHeartRateSuccessList = list;
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            if (hiHealthData == null) {
                LogUtil.b(PluginHealthTrackAdapterImpl.TAG, "onChange HiHealthData is null!");
                return;
            }
            long startTime = hiHealthData.getStartTime();
            int intValue = hiHealthData.getIntValue();
            LogUtil.a(PluginHealthTrackAdapterImpl.TAG, "time: ", Long.valueOf(startTime), "; heartRate:", Integer.valueOf(intValue));
            if (PluginHealthTrackAdapterImpl.this.mHeartRateCallback != null) {
                PluginHealthTrackAdapterImpl.this.mHeartRateCallback.onChange(intValue, startTime);
            } else {
                ReleaseLogUtil.e(PluginHealthTrackAdapterImpl.TAG, "mHeartRateCallback == null");
            }
        }
    };
    private List<Integer> mReportDataSuccessList = null;
    private HiSubscribeListener mReportDataSubscribeListener = new HiSubscribeListener() { // from class: com.huawei.hwadpaterhealthmgr.PluginHealthTrackAdapterImpl.12
        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            if (koq.c(list)) {
                LogUtil.a(PluginHealthTrackAdapterImpl.TAG, "registerReportDataListener success");
                PluginHealthTrackAdapterImpl.this.mReportDataSuccessList = list;
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            if (hiHealthData == null) {
                LogUtil.h(PluginHealthTrackAdapterImpl.TAG, "onChange reportData HiHealthData is null!");
                return;
            }
            kon konVar = (kon) new Gson().fromJson(CommonUtil.z(hiHealthData.getMetaData()), kon.class);
            if (konVar == null) {
                return;
            }
            kvq kvqVar = new kvq();
            kvqVar.j(konVar.j());
            kvqVar.k(konVar.b());
            kvqVar.p(konVar.k());
            kvqVar.n(konVar.o());
            kvqVar.o(konVar.n());
            kvqVar.d(konVar.m());
            kvqVar.b(konVar.e());
            kvqVar.c(konVar.c());
            kvqVar.h((int) konVar.f());
            kvqVar.f(konVar.g());
            kvqVar.s((int) konVar.h());
            kvqVar.d(konVar.l());
            kvqVar.r(konVar.a());
            if (PluginHealthTrackAdapterImpl.this.mReportDataCallback != null) {
                PluginHealthTrackAdapterImpl.this.mReportDataCallback.onChange(kvqVar);
            }
        }
    };
    private Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.huawei.hwadpaterhealthmgr.PluginHealthTrackAdapterImpl.16
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.b(PluginHealthTrackAdapterImpl.TAG, "msg == null");
                return;
            }
            super.handleMessage(message);
            int i = message.what;
            if (i == 1) {
                PluginHealthTrackAdapterImpl.this.synCloud();
            } else {
                if (i != 2) {
                    return;
                }
                PluginHealthTrackAdapterImpl.this.reportSaveOverTime((String) message.obj);
            }
        }
    };

    interface IReportTotalSteps {
        void onReport(int i, long j, int i2, int i3, OperationState operationState);
    }

    enum OperationState {
        STATE_PAUSE,
        STATE_RESUME,
        STATE_STOP,
        STATE_REPORT,
        STATE_REPORT_CURRENT_STEP,
        STATE_REAL_TIME
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public int getDefaultDelayInterval() {
        return 1000;
    }

    static /* synthetic */ int access$2108(PluginHealthTrackAdapterImpl pluginHealthTrackAdapterImpl) {
        int i = pluginHealthTrackAdapterImpl.mStepGetTimes;
        pluginHealthTrackAdapterImpl.mStepGetTimes = i + 1;
        return i;
    }

    private PluginHealthTrackAdapterImpl(Context context) {
        if (context == null) {
            LogUtil.h(TAG, "context is null");
            return;
        }
        this.mContext = context.getApplicationContext();
        this.mExtendHandler = HandlerCenter.yt_(new e(), "Track_Adapter");
        HealthOpenSDK healthOpenSDK = getHealthOpenSDK();
        this.mHealthOpenSdk = healthOpenSDK;
        LogUtil.a(TAG, "initSDK ", healthOpenSDK);
        this.mUserProfileMgrApi = (UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class);
    }

    class e implements Handler.Callback {
        private e() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 2001:
                    if (PluginHealthTrackAdapterImpl.this.mSimpleStepFrequency == null) {
                        return true;
                    }
                    if (PluginHealthTrackAdapterImpl.this.isNeedAcquireStepFrequency()) {
                        PluginHealthTrackAdapterImpl.this.mSimpleStepFrequency.c();
                        PluginHealthTrackAdapterImpl.this.mExtendHandler.sendEmptyMessage(2001, 1000L);
                        return true;
                    }
                    ReleaseLogUtil.e(PluginHealthTrackAdapterImpl.TAG, "MSG_STEP_FREQUENCY_TIMER sports status stop or idle");
                    PluginHealthTrackAdapterImpl.this.mExtendHandler.removeMessages(2001);
                    return true;
                case 2002:
                    if (PluginHealthTrackAdapterImpl.this.mSimpleStepFrequency == null) {
                        return true;
                    }
                    PluginHealthTrackAdapterImpl.this.mSimpleStepFrequency.e();
                    return true;
                case 2003:
                    if (PluginHealthTrackAdapterImpl.this.mSimpleStepFrequency == null) {
                        return true;
                    }
                    PluginHealthTrackAdapterImpl.this.mSimpleStepFrequency.a();
                    return true;
                case 2004:
                    if (PluginHealthTrackAdapterImpl.this.mRunningPostureCallback != null && gtx.c(PluginHealthTrackAdapterImpl.this.mContext).ay()) {
                        PluginHealthTrackAdapterImpl.this.mRunningPostureCallback.onChange(PluginHealthTrackAdapterImpl.this.mRunningPostureTemp);
                        LogUtil.a(PluginHealthTrackAdapterImpl.TAG, "runningPosture = ", PluginHealthTrackAdapterImpl.this.mRunningPostureTemp.toString());
                        PluginHealthTrackAdapterImpl.this.mRunningPostureTemp = new ffs();
                    }
                    PluginHealthTrackAdapterImpl.this.mExtendHandler.sendEmptyMessage(2004, 5000L);
                    return true;
                case 2005:
                    if (PluginHealthTrackAdapterImpl.this.mRidePostureCallback != null && gtx.c(PluginHealthTrackAdapterImpl.this.mContext).ay()) {
                        LogUtil.a(PluginHealthTrackAdapterImpl.TAG, "ridePostureData onChange");
                        PluginHealthTrackAdapterImpl.this.mRidePostureCallback.onChange(PluginHealthTrackAdapterImpl.this.mRidePostureTemp);
                        PluginHealthTrackAdapterImpl.this.mRidePostureTemp = new ffn();
                    }
                    PluginHealthTrackAdapterImpl.this.mExtendHandler.sendEmptyMessage(2005, 1000L);
                    return true;
                default:
                    return false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isNeedAcquireStepFrequency() {
        int m;
        gtx c2 = gtx.c(BaseApplication.getContext());
        return (c2 == null || (m = c2.m()) == 3 || m == 0) ? false : true;
    }

    class a implements IReportTotalSteps {

        /* renamed from: a, reason: collision with root package name */
        private long f6169a;
        private boolean b;
        private int c;
        private long d;
        private long f;
        private long g;
        private double h;
        private int i;
        private int j;

        a(long j, int i) {
            this.f6169a = j;
            this.j = i;
        }

        public void d() {
            if (b(this.i, 0, this.g, OperationState.STATE_RESUME, this)) {
                this.g = System.currentTimeMillis();
            }
        }

        public void c(int i) {
            this.j = i;
        }

        void c(int i, double d, int i2) {
            this.f = i;
            this.h = d;
            this.c = i2;
        }

        void c() {
            LogUtil.c(PluginHealthTrackAdapterImpl.TAG, "onTick ", Long.valueOf(this.f), " mIsPause ", Boolean.valueOf(this.b));
            if (this.b) {
                return;
            }
            this.f++;
            long currentTimeMillis = System.currentTimeMillis();
            if (!gvt.e()) {
                g();
            }
            if (c(currentTimeMillis)) {
                b(this.i, this.c, this.g, OperationState.STATE_REPORT, this);
                this.g = currentTimeMillis;
                this.c = 0;
                this.f = 0L;
                this.d = 0L;
                this.h = 0.0d;
            }
        }

        private boolean b(int i, int i2, long j, OperationState operationState, IReportTotalSteps iReportTotalSteps) {
            int i3 = this.j;
            if (i3 == 1) {
                if (PluginHealthTrackAdapterImpl.this.mTreadmillManager != null) {
                    PluginHealthTrackAdapterImpl.this.mTreadmillManager.a(new b(i, i2, j, operationState, iReportTotalSteps));
                    return true;
                }
                LogUtil.h(PluginHealthTrackAdapterImpl.TAG, "mTreadmillManager = null");
                return false;
            }
            if (i3 != 2 && i3 != 3) {
                if (PluginHealthTrackAdapterImpl.this.mHealthOpenSdk != null) {
                    PluginHealthTrackAdapterImpl.this.mHealthOpenSdk.e(new b(i, i2, j, operationState, iReportTotalSteps));
                    return true;
                }
                LogUtil.h(PluginHealthTrackAdapterImpl.TAG, "mHealthOpenSdk = null");
                return false;
            }
            d(new b(i, i2, j, operationState, iReportTotalSteps));
            return true;
        }

        public boolean d(IExecuteResult iExecuteResult) {
            LogUtil.a(PluginHealthTrackAdapterImpl.TAG, "getLinkageSteps callback:", iExecuteResult);
            if (iExecuteResult != null) {
                try {
                    int a2 = gtw.e().a();
                    LogUtil.a(PluginHealthTrackAdapterImpl.TAG, "getLinkageSteps ", Integer.valueOf(a2));
                    Bundle bundle = new Bundle();
                    bundle.putInt("standSteps", a2);
                    iExecuteResult.onSuccess(bundle);
                } catch (Exception e) {
                    LogUtil.h(PluginHealthTrackAdapterImpl.TAG, "getLinkageSteps ", LogAnonymous.b((Throwable) e));
                }
            }
            return true;
        }

        private void g() {
            if (!PluginHealthTrackAdapterImpl.this.mIsScreenOn) {
                if (PluginHealthTrackAdapterImpl.this.mStepGetTimes >= 4) {
                    b();
                    return;
                } else {
                    PluginHealthTrackAdapterImpl.access$2108(PluginHealthTrackAdapterImpl.this);
                    return;
                }
            }
            b();
        }

        private void b() {
            b(this.i, this.c, this.g, OperationState.STATE_REAL_TIME, this);
            PluginHealthTrackAdapterImpl.this.mStepGetTimes = 0;
        }

        private boolean c(long j) {
            if (this.d == 0) {
                this.d = j - 1000;
            }
            double d = this.h + ((j - this.d) / 1000.0d);
            this.h = d;
            this.d = j;
            long j2 = this.f;
            long j3 = this.f6169a;
            if (j2 < j3 && d < j3) {
                return false;
            }
            LogUtil.a(PluginHealthTrackAdapterImpl.TAG, "isTimeOver ", Long.valueOf(j2), "  mUsedTime ", Double.valueOf(this.h));
            return true;
        }

        public void e() {
            b(this.i, this.c, this.g, OperationState.STATE_STOP, this);
        }

        public void a() {
            b(this.i, this.c, this.g, OperationState.STATE_REPORT_CURRENT_STEP, this);
        }

        public void e(boolean z) {
            this.b = z;
            OperationState operationState = OperationState.STATE_PAUSE;
            if (!z) {
                this.d = System.currentTimeMillis();
                operationState = OperationState.STATE_RESUME;
            }
            b(this.i, this.c, this.g, operationState, this);
        }

        private void a(int i) {
            int i2 = i - this.i;
            this.c += i2;
            LogUtil.a(PluginHealthTrackAdapterImpl.TAG, "diff ", Integer.valueOf(i2), " totalStep ", Integer.valueOf(i));
        }

        @Override // com.huawei.hwadpaterhealthmgr.PluginHealthTrackAdapterImpl.IReportTotalSteps
        public void onReport(int i, long j, int i2, int i3, OperationState operationState) {
            if (operationState == OperationState.STATE_RESUME) {
                this.i = i3;
                LogUtil.a(PluginHealthTrackAdapterImpl.TAG, "resume ", Integer.valueOf(i3));
                return;
            }
            if (operationState == OperationState.STATE_PAUSE) {
                a(i3);
                this.i = i3;
                LogUtil.a(PluginHealthTrackAdapterImpl.TAG, "pause ", Integer.valueOf(i3));
            } else if (operationState == OperationState.STATE_STOP) {
                LogUtil.a(PluginHealthTrackAdapterImpl.TAG, "stop ", Integer.valueOf(i3), ", st: ", Integer.valueOf(i), "ct: ", Integer.valueOf(i2));
                b(i3, j, i2, i3);
                PluginHealthTrackAdapterImpl.this.mRealStepCallback = null;
            } else if (operationState == OperationState.STATE_REPORT_CURRENT_STEP) {
                LogUtil.a(PluginHealthTrackAdapterImpl.TAG, "report current step ", Integer.valueOf(i3), ", st: ", Integer.valueOf(i), "ct: ", Integer.valueOf(i2));
                e(i, j, i2, i3);
            } else if (operationState == OperationState.STATE_REPORT) {
                LogUtil.a(PluginHealthTrackAdapterImpl.TAG, "report StepFrequency", Integer.valueOf(i3), ", st: ", Integer.valueOf(i), "ct: ", Integer.valueOf(i2));
                b(i, j, i2, i3);
            }
        }

        private void b(int i, long j, int i2, int i3) {
            long currentTimeMillis = System.currentTimeMillis();
            this.i = i3;
            LogUtil.a(PluginHealthTrackAdapterImpl.TAG, "reportStepFrequencyInter ", Integer.valueOf(i), " start : ", j + " end: ", Long.valueOf(currentTimeMillis));
            if (PluginHealthTrackAdapterImpl.this.mRealStepCallback != null) {
                PluginHealthTrackAdapterImpl.this.mRealStepCallback.onChange((i3 - i) + i2, j, currentTimeMillis);
            }
        }

        private void e(int i, long j, int i2, int i3) {
            int i4 = (i3 - i) + i2;
            long currentTimeMillis = System.currentTimeMillis();
            LogUtil.a(PluginHealthTrackAdapterImpl.TAG, "reportCurrentStepInter ", Integer.valueOf(i4), " start : ", Long.valueOf(j), " end: ", Long.valueOf(currentTimeMillis));
            if (PluginHealthTrackAdapterImpl.this.mCurrentStepCallback != null) {
                PluginHealthTrackAdapterImpl.this.mCurrentStepCallback.onChange(i4, currentTimeMillis, currentTimeMillis);
            }
        }
    }

    static class b implements IExecuteResult {

        /* renamed from: a, reason: collision with root package name */
        private int f6170a;
        private OperationState b;
        private int c;
        private long d;
        private IReportTotalSteps e;

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onFailed(Object obj) {
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onServiceException(Object obj) {
        }

        b(int i, int i2, long j, OperationState operationState, IReportTotalSteps iReportTotalSteps) {
            this.f6170a = i;
            this.c = i2;
            this.d = j;
            this.e = iReportTotalSteps;
            this.b = operationState;
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onSuccess(Object obj) {
            if (obj instanceof Bundle) {
                int i = ((Bundle) obj).getInt("standSteps", 0);
                int unused = PluginHealthTrackAdapterImpl.sCurrentSteps = i;
                this.e.onReport(this.f6170a, this.d, this.c, i, this.b);
            }
        }
    }

    private HealthOpenSDK getHealthOpenSDK() {
        if (this.mHealthOpenSdk == null) {
            HealthOpenSDK b2 = iwz.b();
            this.mHealthOpenSdk = b2;
            b2.initSDK(this.mContext, new IExecuteResult() { // from class: com.huawei.hwadpaterhealthmgr.PluginHealthTrackAdapterImpl.18
                @Override // com.huawei.hihealth.motion.IExecuteResult
                public void onSuccess(Object obj) {
                    LogUtil.a(PluginHealthTrackAdapterImpl.TAG, "healthOpenSDKCallback initSDK success");
                }

                @Override // com.huawei.hihealth.motion.IExecuteResult
                public void onFailed(Object obj) {
                    LogUtil.h(PluginHealthTrackAdapterImpl.TAG, "healthOpenSDKCallback : initSDK Failed");
                }

                @Override // com.huawei.hihealth.motion.IExecuteResult
                public void onServiceException(Object obj) {
                    LogUtil.b(PluginHealthTrackAdapterImpl.TAG, "healthOpenSDKCallback : initSDK onServiceException");
                }
            }, "HuaweiHealth");
        }
        return this.mHealthOpenSdk;
    }

    public static PluginHealthTrackAdapterImpl getInstance(Context context) {
        PluginHealthTrackAdapterImpl pluginHealthTrackAdapterImpl;
        synchronized (LOCK) {
            LogUtil.a(TAG, "sInstance is ", sInstance);
            if (sInstance == null) {
                sInstance = new PluginHealthTrackAdapterImpl(context);
            }
            pluginHealthTrackAdapterImpl = sInstance;
        }
        return pluginHealthTrackAdapterImpl;
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public void init() {
        this.mRunningPostureTemp = new ffs();
        this.mRidePostureTemp = new ffn();
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public int saveTrackData(MotionPathSimplify motionPathSimplify, MotionPath motionPath) {
        LogUtil.a(TAG, "saveTrackData MotionPath is enter");
        if (motionPathSimplify == null || motionPath == null) {
            LogUtil.h(TAG, "simplifyData or motionPath is null");
            return 5;
        }
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        sqi.e(BaseApplication.getContext(), motionPathSimplify, motionPath, hiDataInsertOption);
        LogUtil.a(TAG, "saveTrackData MotionPath convertInsertOption");
        long requestStartTime = motionPathSimplify.requestStartTime();
        HiHealthApi d = HiHealthManager.d(BaseApplication.getContext());
        final String valueOf = String.valueOf(requestStartTime);
        d.insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: com.huawei.hwadpaterhealthmgr.PluginHealthTrackAdapterImpl.19
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                LogUtil.a(PluginHealthTrackAdapterImpl.TAG, "saveTrackData MotionPath insertHiHealthData onResult");
                if (i != 0) {
                    LogUtil.b(PluginHealthTrackAdapterImpl.TAG, "saveTrackData MotionPath fail obj = ", obj, ", type = ", Integer.valueOf(i));
                } else {
                    Message obtain = Message.obtain();
                    obtain.what = 1;
                    PluginHealthTrackAdapterImpl.this.mHandler.sendMessage(obtain);
                    LogUtil.a(PluginHealthTrackAdapterImpl.TAG, "saveTrackData MotionPath success obj = ", obj, ", type = ", Integer.valueOf(i));
                }
                PluginHealthTrackAdapterImpl.this.sendBroadcast("com.huawei.bone.action.WORKOUT_CARD_UPDATED_DATA");
                PluginHealthTrackAdapterImpl.this.saveLastSavingSportStartTime(valueOf);
                LogUtil.a(PluginHealthTrackAdapterImpl.TAG, "save onResult finish");
            }
        });
        LogUtil.a(TAG, "saveTrackData MotionPath insertHiHealthData end");
        return 1;
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public long saveTrackData(MotionPathSimplify motionPathSimplify, String str) {
        gtx c2 = gtx.c(BaseApplication.getContext());
        if (c2 != null) {
            return saveTrackDataDetail(motionPathSimplify, str, c2.f());
        }
        ReleaseLogUtil.c(TAG, "saveTrackData sportManager is null");
        return 1L;
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public long saveTrackData(MotionPathSimplify motionPathSimplify, String str, kvq kvqVar) {
        return saveTrackDataDetail(motionPathSimplify, str, kvqVar);
    }

    private long saveTrackDataDetail(MotionPathSimplify motionPathSimplify, String str, kvq kvqVar) {
        ReleaseLogUtil.e(TAG, "saveTrackData String is enter");
        if (motionPathSimplify == null) {
            ReleaseLogUtil.d(TAG, "simplifyData is null");
            return 5L;
        }
        RunPlanRecordInfo c2 = bnl.a().c();
        HashMap hashMap = new HashMap(16);
        int requestSportType = motionPathSimplify.requestSportType();
        int requestSportDataSource = motionPathSimplify.requestSportDataSource();
        if (c2 != null) {
            sql.b(requestSportType, requestSportDataSource, hashMap, c2);
            motionPathSimplify.saveSportData(hashMap);
            motionPathSimplify.addExtendDataMap("eteAlgoKey", String.valueOf(c2.getRunPlanAlgoType()));
            ReleaseLogUtil.e(TAG, "save ete result to track sportData finish");
        } else {
            ReleaseLogUtil.d(TAG, "info is null");
            sql.a(hashMap, motionPathSimplify, kvqVar);
            motionPathSimplify.saveSportData(hashMap);
            if (kvqVar != null) {
                motionPathSimplify.addExtendDataMap("eteAlgoKey", String.valueOf(kvqVar.x()));
            }
            ReleaseLogUtil.e(TAG, "save realTime ete result to track sportData finish");
        }
        MotionPathSimplify d = gwk.d(this.mContext);
        if (d != null) {
            d.saveSportData(hashMap);
            gwo.a(this.mContext, d, "simplemotion.txt");
            gwo.a(this.mContext, d, "simplemotion_temp.txt");
        }
        saveTrackDataToOdmf(motionPathSimplify, str);
        insertTrackData(motionPathSimplify, str);
        return 1L;
    }

    private void insertTrackData(MotionPathSimplify motionPathSimplify, String str) {
        boolean e2 = gwo.e(BaseApplication.getContext(), "simplemotion.txt", "simplemotion_back.txt");
        boolean e3 = gwo.e(BaseApplication.getContext(), str, "motion_path2_back.txt");
        ReleaseLogUtil.e(TAG, "simple data copy result: ", Boolean.valueOf(e2), " motion path data copy result: ", Boolean.valueOf(e3));
        if (e2 && e3) {
            insertHiHealthData(motionPathSimplify, str, 2);
        } else {
            insertHiHealthData(motionPathSimplify, str, 0);
        }
    }

    private void insertHiHealthData(MotionPathSimplify motionPathSimplify, String str, int i) {
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        sqi.e(BaseApplication.getContext(), motionPathSimplify, str, hiDataInsertOption);
        insertHiHealthData(motionPathSimplify, hiDataInsertOption, i);
    }

    public void saveBackTrackDataToDb(long j) {
        if (!isPathFileExisted(this.mContext, "motion_path2_back.txt")) {
            ReleaseLogUtil.c(TAG, "saveBackTrackDataToDb isMotionPathFileExisted is false ");
            return;
        }
        MotionPathSimplify e2 = gwk.e(BaseApplication.getContext(), "simplemotion_back.txt");
        if (e2 == null) {
            ReleaseLogUtil.c(TAG, "saveBackTrackDataToDb simplifyData == null");
        } else {
            if (e2.requestStartTime() != j) {
                ReleaseLogUtil.c(TAG, "saveBackTrackDataToDb simplifyData time not match:", Long.valueOf(e2.requestStartTime()), "--", Long.valueOf(j));
                return;
            }
            ReleaseLogUtil.e(TAG, "saveBackTrackDataToDb time:", Long.valueOf(j));
            insertHiHealthData(e2, "motion_path2_back.txt", 3);
            updateUnSavedTrackStartTime(0L);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public void saveTrackDataWhenProcess(MotionPathSimplify motionPathSimplify, String str) {
        if (motionPathSimplify == null || TextUtils.isEmpty(str)) {
            LogUtil.b(TAG, "saveTrackDataWhenProcess simplifyData == null || TextUtils.isEmpty(path) ");
            return;
        }
        if (!isPathFileExisted(this.mContext, str)) {
            ReleaseLogUtil.c(TAG, "saveTrackDataWhenProcess isMotionPathFileExisted is false ");
            return;
        }
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        sqi.e(BaseApplication.getContext(), motionPathSimplify, str, hiDataInsertOption);
        saveTrackDataToOdmf(motionPathSimplify, str);
        insertHiHealthData(motionPathSimplify, hiDataInsertOption, 1);
    }

    public static boolean isPathFileExisted(Context context, String str) {
        if (context == null) {
            LogUtil.a(TAG, "isMotionPathFileExisted ", "context is null");
            return false;
        }
        return new File(context.getFilesDir(), str).exists();
    }

    private void saveTrackDataToOdmf(MotionPathSimplify motionPathSimplify, String str) {
        ((UserLabelServiceApi) Services.c("HWUserLabelMgr", UserLabelServiceApi.class)).saveTrackDataToOdmf(getTrackDataToOdmf(motionPathSimplify, str));
        ReleaseLogUtil.e(TAG, "saveTrackData String insertHiHealthData");
    }

    private void insertHiHealthData(final MotionPathSimplify motionPathSimplify, final HiDataInsertOption hiDataInsertOption, final int i) {
        long requestStartTime = motionPathSimplify.requestStartTime();
        if (i == 2) {
            updateUnSavedTrackStartTime(motionPathSimplify.requestStartTime());
        }
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        sendOverTimeMsg(String.valueOf(motionPathSimplify.requestTotalTime()));
        HiHealthApi d = HiHealthManager.d(BaseApplication.getContext());
        final String valueOf = String.valueOf(requestStartTime);
        d.insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: com.huawei.hwadpaterhealthmgr.PluginHealthTrackAdapterImpl.20
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i2, Object obj) {
                ReleaseLogUtil.e(PluginHealthTrackAdapterImpl.TAG, "saveTrackData String insertHiHealthData onResult");
                PluginHealthTrackAdapterImpl.this.removeOverTimeMsg();
                PluginHealthTrackAdapterImpl.this.reportBackSaveEvent(i, motionPathSimplify, i2);
                if (i2 == 0) {
                    Message obtain = Message.obtain();
                    obtain.what = 1;
                    PluginHealthTrackAdapterImpl.this.mHandler.sendMessage(obtain);
                    ReleaseLogUtil.e(PluginHealthTrackAdapterImpl.TAG, "saveTrackData String success errorCode ", Integer.valueOf(i2));
                    if (i == 2) {
                        PluginHealthTrackAdapterImpl.this.updateUnSavedTrackStartTime(0L);
                    }
                    PluginHealthTrackAdapterImpl.writeTrackTimeToList(motionPathSimplify);
                } else {
                    LogUtil.a(PluginHealthTrackAdapterImpl.TAG, "saveTrackData String fail object = ", obj);
                    ReleaseLogUtil.d(PluginHealthTrackAdapterImpl.TAG, "saveTrackData String fail errorCode ", Integer.valueOf(i2));
                    PluginHealthTrackAdapterImpl.this.reportSaveEvent(SystemClock.elapsedRealtime() - elapsedRealtime, i2, motionPathSimplify, i);
                    if (i2 == 3) {
                        List<HiHealthData> datas = hiDataInsertOption.getDatas();
                        if (koq.d(datas, 0) && datas.get(0) != null) {
                            LogUtil.h(PluginHealthTrackAdapterImpl.TAG, "saveTrackData String fail fuzzyDevice ", blt.a(datas.get(0).getDeviceUuid()));
                        }
                    }
                }
                PluginHealthTrackAdapterImpl.this.sendBroadcast("com.huawei.bone.action.WORKOUT_CARD_UPDATED_DATA");
                PluginHealthTrackAdapterImpl.this.saveLastSavingSportStartTime(valueOf);
                ReleaseLogUtil.e(PluginHealthTrackAdapterImpl.TAG, "save onResult finish");
            }
        });
        ReleaseLogUtil.e(TAG, "saveTrackData String insertHiHealthData end");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateUnSavedTrackStartTime(long j) {
        jfa.b(Integer.toString(20002), "last_unsaved_track", j);
        ReleaseLogUtil.e(TAG, "unsaved track time: ", Long.valueOf(j));
    }

    private void sendOverTimeMsg(String str) {
        Message obtain = Message.obtain();
        obtain.what = 2;
        obtain.obj = str;
        this.mHandler.sendEmptyMessageDelayed(2, OpAnalyticsConstants.H5_LOADING_DELAY);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeOverTimeMsg() {
        this.mHandler.removeMessages(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportBackSaveEvent(int i, MotionPathSimplify motionPathSimplify, int i2) {
        if (i == 3) {
            gwp.d(motionPathSimplify, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportSaveEvent(long j, int i, MotionPathSimplify motionPathSimplify, int i2) {
        gwp.a(j, i, motionPathSimplify, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportSaveOverTime(String str) {
        gwp.d(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void writeTrackTimeToList(MotionPathSimplify motionPathSimplify) {
        if (motionPathSimplify == null) {
            LogUtil.h(TAG, "writeTrackTimeToList pathSimplify is null");
            return;
        }
        if (motionPathSimplify.requestTrackType() != 1) {
            LogUtil.h(TAG, "writeTrackTimeToList requestTrackType not auto type");
            return;
        }
        HashMap hashMap = new HashMap(16);
        hashMap.put(Long.valueOf(motionPathSimplify.requestStartTime()), Long.valueOf(motionPathSimplify.requestEndTime()));
        jfc.c(BaseApplication.getContext(), hashMap, KeyValDbManager.b(BaseApplication.getContext()).e("user_id"), new StorageDataCallback() { // from class: com.huawei.hwadpaterhealthmgr.PluginHealthTrackAdapterImpl.17
            @Override // com.huawei.hwdataaccessmodel.utils.StorageDataCallback
            public void onProcessed(StorageResult storageResult) {
                if (storageResult != null) {
                    LogUtil.a(PluginHealthTrackAdapterImpl.TAG, "writeTrackTimeToList result:", Integer.valueOf(storageResult.d()));
                }
            }
        });
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "SPORT_CARD_RED_DOT_FOR_AUTO_TRACK", String.valueOf(true), new StorageParams());
    }

    public String getTrackDataToOdmf(MotionPathSimplify motionPathSimplify, String str) {
        if (motionPathSimplify == null) {
            LogUtil.h(TAG, "simplifyData is null");
            return null;
        }
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        sqi.e(BaseApplication.getContext(), motionPathSimplify, str, hiDataInsertOption);
        List<HiHealthData> datas = hiDataInsertOption.getDatas();
        int requestSportType = motionPathSimplify.requestSportType();
        String m = HiDateUtil.m(datas.get(0).getStartTime());
        String deviceUuid = datas.get(0).getDeviceUuid();
        long requestTotalTime = motionPathSimplify.requestTotalTime() / 1000;
        double ceil = Math.ceil(motionPathSimplify.requestTotalCalories() / 1000.0d);
        String sportStartGps = getSportStartGps();
        String sportSpeedDistribution = getSportSpeedDistribution(datas);
        String heartDistribution = getHeartDistribution(gwk.c(this.mContext, str), (int) requestTotalTime, motionPathSimplify.getExtendDataString("isTrustHeartRate"));
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("SportType", requestSportType);
            jSONObject.put("SportStartTime", m);
            jSONObject.put(DeviceInfo.TAG_DEVICE_ID, deviceUuid);
            jSONObject.put("SportStartGPS", sportStartGps);
            jSONObject.put("SportDuration", requestTotalTime);
            jSONObject.put("HeartDistribution", heartDistribution);
            jSONObject.put("SportSpeedDistribution", sportSpeedDistribution);
            jSONObject.put("HeatQuantity", ceil);
            return jSONObject.toString();
        } catch (JSONException e2) {
            LogUtil.b(TAG, LogAnonymous.b((Throwable) e2));
            return null;
        }
    }

    private String getHeartDistribution(MotionPath motionPath, int i, String str) {
        LogUtil.a(TAG, "getHeartDistribution enter ");
        JSONArray jSONArray = new JSONArray();
        for (int i2 = 0; i2 < 6; i2++) {
            jSONArray.put(0);
        }
        if (motionPath != null) {
            ArrayList<HeartRateData> requestHeartRateList = motionPath.requestHeartRateList();
            if (requestHeartRateList.size() > 0) {
                int[] c2 = ffw.c(requestHeartRateList, 100, i, str, this.mUserProfileMgrApi.getLocalUserInfo(), 0);
                int i3 = c2[0];
                int i4 = c2[1];
                int i5 = c2[2];
                int i6 = c2[3];
                int i7 = c2[4];
                int i8 = i3 + i4 + i5 + i6 + i7;
                if (i8 >= i) {
                    i = i8;
                }
                if (i == 0) {
                    return "";
                }
                JSONArray jSONArray2 = new JSONArray();
                int i9 = (i6 * 100) / i;
                jSONArray2.put(i9);
                int i10 = (i5 * 100) / i;
                jSONArray2.put(i10);
                int i11 = (i4 * 100) / i;
                jSONArray2.put(i11);
                int i12 = (i3 * 100) / i;
                jSONArray2.put(i12);
                jSONArray2.put(((((100 - i12) - i11) - i10) - i9) - ((i7 * 100) / i));
                return jSONArray2.toString();
            }
            LogUtil.h(TAG, "getHeartDistribution mHeartRateList = null ");
        }
        return jSONArray.toString();
    }

    private String getSportSpeedDistribution(List<HiHealthData> list) {
        LogUtil.a(TAG, "getSportSpeedDistribution enter");
        int[] iArr = {0, 0, 0, 0, 0, 0};
        JSONArray jSONArray = new JSONArray();
        try {
            JSONObject jSONObject = new JSONObject(new JSONObject(list.get(0).getMetaData()).get("paceMap").toString());
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (next instanceof String) {
                    double d = jSONObject.getDouble(next);
                    if (d < 300.0d) {
                        iArr[0] = iArr[0] + 1;
                    } else if (d < 360.0d) {
                        iArr[1] = iArr[1] + 1;
                    } else if (d < 420.0d) {
                        iArr[2] = iArr[2] + 1;
                    } else if (d < 480.0d) {
                        iArr[3] = iArr[3] + 1;
                    } else if (d < 540.0d) {
                        iArr[4] = iArr[4] + 1;
                    } else {
                        iArr[5] = iArr[5] + 1;
                    }
                }
            }
        } catch (JSONException e2) {
            LogUtil.b(TAG, LogAnonymous.b((Throwable) e2));
        }
        jSONArray.put(iArr[0]);
        jSONArray.put(iArr[1]);
        jSONArray.put(iArr[2]);
        jSONArray.put(iArr[3]);
        jSONArray.put(iArr[4]);
        jSONArray.put(iArr[5]);
        return jSONArray.toString();
    }

    private String getSportStartGps() {
        LogUtil.a(TAG, "getSportStartGps enter");
        MotionPath a2 = gwk.a(this.mContext);
        if (a2 != null) {
            Map<Long, double[]> requestLbsDataMap = a2.requestLbsDataMap();
            if (requestLbsDataMap == null || requestLbsDataMap.isEmpty()) {
                LogUtil.h(TAG, "getSportStartGps No GPS");
            } else {
                double[] dArr = requestLbsDataMap.get(0L);
                if (dArr == null || dArr.length < 2) {
                    LogUtil.a(TAG, "getSportStartGps No locationArray");
                    return null;
                }
                double d = dArr[0];
                double d2 = dArr[1];
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(String.valueOf(d2));
                jSONArray.put(String.valueOf(d));
                return jSONArray.toString();
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveLastSavingSportStartTime(String str) {
        SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(20002), "save_DB_End", str, (StorageParams) null);
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public void saveTrackPointData(List<gyc> list, int i) {
        HiHealthManager.d(BaseApplication.getContext()).insertHiHealthData(sqi.e(BaseApplication.getContext(), list, i), new HiDataOperateListener() { // from class: com.huawei.hwadpaterhealthmgr.PluginHealthTrackAdapterImpl.4
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i2, Object obj) {
                if (i2 == 0) {
                    ReleaseLogUtil.e(PluginHealthTrackAdapterImpl.TAG, "saveTrackPointData success obj = ", obj, ",type = ", Integer.valueOf(i2));
                } else {
                    ReleaseLogUtil.c(PluginHealthTrackAdapterImpl.TAG, "saveTrackPointData fail obj = ", obj, ",type = ", Integer.valueOf(i2));
                }
            }
        });
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public void insertIntensityData(List<gxn> list) {
        HiHealthManager.d(BaseApplication.getContext()).insertHiHealthData(sqi.c(BaseApplication.getContext(), list), new HiDataOperateListener() { // from class: ixc
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public final void onResult(int i, Object obj) {
                PluginHealthTrackAdapterImpl.lambda$insertIntensityData$0(i, obj);
            }
        });
    }

    public static /* synthetic */ void lambda$insertIntensityData$0(int i, Object obj) {
        if (obj != null) {
            LogUtil.a(TAG, "InsertCallBack errorCode = ", Integer.valueOf(i));
        } else {
            LogUtil.h(TAG, "InsertCallBack errorCode = ", Integer.valueOf(i), " obj == null ");
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public void startStepPoint() {
        if (this.mTreadmillStepPointData == null) {
            sqs sqsVar = new sqs();
            this.mTreadmillStepPointData = sqsVar;
            sqsVar.c(this.mContext, System.currentTimeMillis());
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public void stopStepPoint() {
        sqs sqsVar = this.mTreadmillStepPointData;
        if (sqsVar != null) {
            sqsVar.c(System.currentTimeMillis());
            this.mTreadmillStepPointData = null;
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public void updateStepPoint(int i, long j) {
        sqs sqsVar = this.mTreadmillStepPointData;
        if (sqsVar != null) {
            sqsVar.c(j, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void synCloud() {
        HiHealthNativeApi.a(BaseApplication.getContext()).synCloud(sqi.a(), null);
        LogUtil.a(TAG, "saveTrackData success synCloud begin...");
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public boolean regHeartRateListener(IHeartRateCallback iHeartRateCallback, int i) {
        HiHealthManager.d(this.mContext).subscribeHiHealthData(13, this.mSubscribeListener);
        this.mHeartRateCallback = iHeartRateCallback;
        return true;
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public void unregHeartRateListener() {
        List<Integer> list = this.mHeartRateSuccessList;
        if (list == null || list.isEmpty()) {
            return;
        }
        LogUtil.a(TAG, "unregHeartRateListener");
        HiHealthManager.d(this.mContext).unSubscribeHiHealthData(this.mHeartRateSuccessList, new HiUnSubscribeListener() { // from class: com.huawei.hwadpaterhealthmgr.PluginHealthTrackAdapterImpl.5
            @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
            public void onResult(boolean z) {
                LogUtil.a(PluginHealthTrackAdapterImpl.TAG, "unregHeartRateListener isSuccess = ", Boolean.valueOf(z));
                PluginHealthTrackAdapterImpl.this.mHeartRateCallback = null;
            }
        });
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public boolean regStepRateListener(IStepRateCallback iStepRateCallback, int i) {
        boolean z;
        HealthOpenSDK healthOpenSDK = this.mHealthOpenSdk;
        if (healthOpenSDK != null) {
            z = healthOpenSDK.d(new RealStepCallback() { // from class: com.huawei.hwadpaterhealthmgr.PluginHealthTrackAdapterImpl.2
                @Override // com.huawei.hihealth.motion.RealStepCallback
                public void onReport(int i2, int i3) {
                    if (PluginHealthTrackAdapterImpl.this.mStepRateCallback != null) {
                        PluginHealthTrackAdapterImpl.this.mStepRateCallback.report(i2, i3);
                    }
                }
            }, i);
        } else {
            LogUtil.b(TAG, "mHealthOpenSdk is null");
            z = false;
        }
        if (z) {
            this.mStepRateCallback = iStepRateCallback;
            LogUtil.a(TAG, "regStepRateListener success");
        } else {
            LogUtil.h(TAG, "regStepRateListener failed");
        }
        return z;
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public void regStepRateListener(final IStepRateCallback iStepRateCallback, final int i, int i2) {
        LogUtil.a(TAG, "regStepRateListener stepType is ", Integer.valueOf(i2));
        if (i2 == 1) {
            if (this.mTreadmillManager != null) {
                ThreadPoolManager.d().execute(new Runnable() { // from class: ixg
                    @Override // java.lang.Runnable
                    public final void run() {
                        PluginHealthTrackAdapterImpl.this.m644x8ffbcc08(i, iStepRateCallback);
                    }
                });
                return;
            } else {
                LogUtil.b(TAG, "mTreadmillManager is null");
                return;
            }
        }
        if (regStepRateListener(iStepRateCallback, i)) {
            return;
        }
        ReleaseLogUtil.d(TAG, "regStepRateListener failed");
    }

    /* renamed from: lambda$regStepRateListener$1$com-huawei-hwadpaterhealthmgr-PluginHealthTrackAdapterImpl, reason: not valid java name */
    public /* synthetic */ void m644x8ffbcc08(int i, IStepRateCallback iStepRateCallback) {
        if (this.mTreadmillManager.c(new RealStepCallback() { // from class: com.huawei.hwadpaterhealthmgr.PluginHealthTrackAdapterImpl.1
            @Override // com.huawei.hihealth.motion.RealStepCallback
            public void onReport(int i2, int i3) {
                if (PluginHealthTrackAdapterImpl.this.mStepRateCallback != null) {
                    PluginHealthTrackAdapterImpl.this.mStepRateCallback.report(i2, i3);
                }
            }
        }, i)) {
            this.mStepRateCallback = iStepRateCallback;
            LogUtil.a(TAG, "new regStepRateListener success");
        } else {
            ReleaseLogUtil.d(TAG, "new regStepRateListener failed");
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public void unregStepRateListener() {
        HealthOpenSDK healthOpenSDK;
        if (this.mStepRateCallback != null && (healthOpenSDK = this.mHealthOpenSdk) != null) {
            healthOpenSDK.b();
        } else {
            LogUtil.h(TAG, "unregStepRateListener mStepRateCallback is null");
        }
        this.mStepRateCallback = null;
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public void unregStepRateListener(int i) {
        TreadmillManager treadmillManager;
        LogUtil.h(TAG, "unregStepRateListener stepType is ", Integer.valueOf(i));
        if (i == 1) {
            if (this.mStepRateCallback != null && (treadmillManager = this.mTreadmillManager) != null) {
                treadmillManager.b();
            } else {
                LogUtil.h(TAG, "unregStepRateListener mStepRateCallback is null");
            }
            this.mStepRateCallback = null;
            return;
        }
        unregStepRateListener();
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public void startHeartDeviceMeasure() {
        LogUtil.a(TAG, "start measure!");
        cej.e().init(this.mContext);
        cej.e().c(HealthDevice.HealthDeviceKind.HDK_HEART_RATE, new c());
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public void stopHeartDeviceMeasure() {
        if (this.mProductId != null) {
            cej.e().e(this.mProductId, this.mUniqueId);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public void getStepRateList(final long j, final long j2, final Handler handler) {
        if (dealInvalid(j, handler)) {
            return;
        }
        this.mHealthOpenSdk.a(new IFlushResult() { // from class: com.huawei.hwadpaterhealthmgr.PluginHealthTrackAdapterImpl.6
            @Override // com.huawei.hihealth.motion.IFlushResult
            public void onSuccess(Bundle bundle) {
                LogUtil.a(PluginHealthTrackAdapterImpl.TAG, "flushCacheToDB onSuccess");
                e();
            }

            @Override // com.huawei.hihealth.motion.IFlushResult
            public void onFailed(Bundle bundle) {
                LogUtil.b(PluginHealthTrackAdapterImpl.TAG, "flushCacheToDB onFailed");
                e();
            }

            @Override // com.huawei.hihealth.motion.IFlushResult
            public void onServiceException(Bundle bundle) {
                LogUtil.c(PluginHealthTrackAdapterImpl.TAG, "flushCacheToDB onServiceException");
                e();
            }

            private void e() {
                HiDataReadOption hiDataReadOption = new HiDataReadOption();
                hiDataReadOption.setTimeInterval(HiDateUtil.d(j), HiDateUtil.d(j2 + 60000));
                hiDataReadOption.setType(new int[]{2});
                hiDataReadOption.setDeviceUuid(FoundationCommonUtil.getAndroidId(PluginHealthTrackAdapterImpl.this.mContext));
                hiDataReadOption.setReadType(2);
                HiHealthManager.d(PluginHealthTrackAdapterImpl.this.mContext).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: com.huawei.hwadpaterhealthmgr.PluginHealthTrackAdapterImpl.6.2
                    @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
                    public void onResultIntent(int i, Object obj, int i2, int i3) {
                    }

                    @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
                    public void onResult(Object obj, int i, int i2) {
                        if (obj == null) {
                            LogUtil.b(PluginHealthTrackAdapterImpl.TAG, "readHiHealthData onResult data==null");
                            b(null);
                        } else if (obj instanceof SparseArray) {
                            b(PluginHealthTrackAdapterImpl.this.getStepRateDataList((SparseArray) obj));
                        }
                    }
                });
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void b(ArrayList<StepRateData> arrayList) {
                Message obtainMessage = handler.obtainMessage(101, 0, 0, arrayList);
                handler.removeMessages(obtainMessage.what);
                handler.sendMessage(obtainMessage);
            }
        });
    }

    private boolean dealInvalid(long j, Handler handler) {
        if (this.mHealthOpenSdk == null) {
            LogUtil.b(TAG, "get-s getStepRateList mHealthOpenSdk = null!");
            return true;
        }
        if (j == 0) {
            LogUtil.a(TAG, "get-s flushCacheToDB one min time:", Long.valueOf(System.currentTimeMillis()));
            this.mHealthOpenSdk.a((IFlushResult) null);
            return true;
        }
        LogUtil.a(TAG, "get-s flushCacheToDB sport end time:", Long.valueOf(System.currentTimeMillis()));
        handler.sendMessageDelayed(handler.obtainMessage(101, 0, 0, null), 5000L);
        return false;
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public void registerRealStepListener(IRealStepCallback iRealStepCallback, long j) {
        LogUtil.a(TAG, "registerRealStepListener");
        startSimpleStepFrequency(iRealStepCallback, j, 0);
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public void recoveryStep(int i, double d, int i2) {
        a aVar = this.mSimpleStepFrequency;
        if (aVar != null) {
            aVar.c(i, d, i2);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public void registerRealStepListener(IRealStepCallback iRealStepCallback, long j, int i) {
        LogUtil.a(TAG, "registerRealStepListener");
        setCurrentSteps(-1);
        startSimpleStepFrequency(iRealStepCallback, j, i);
    }

    private void startSimpleStepFrequency(IRealStepCallback iRealStepCallback, long j, int i) {
        this.mRealStepCallback = iRealStepCallback;
        a aVar = new a(j, i);
        this.mSimpleStepFrequency = aVar;
        aVar.d();
        if (this.mExtendHandler == null) {
            this.mExtendHandler = HandlerCenter.yt_(new e(), "Track_Adapter");
        }
        this.mExtendHandler.removeMessages(2001);
        this.mExtendHandler.sendEmptyMessage(2001, 1000L);
    }

    private static void setCurrentSteps(int i) {
        sCurrentSteps = i;
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public void setStepType(int i) {
        this.mSimpleStepFrequency.c(i);
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public void unRegisterRealStepCallback() {
        LogUtil.a(TAG, "unRegisterRealStepCallback");
        ExtendHandler extendHandler = this.mExtendHandler;
        if (extendHandler != null) {
            extendHandler.removeMessages(2001);
            this.mExtendHandler.removeMessages(2003);
            this.mExtendHandler.sendEmptyMessage(2002);
        }
        this.mCurrentStepCallback = null;
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public void reportCurrentStepCallback(IRealStepCallback iRealStepCallback) {
        LogUtil.a(TAG, "reportCurrentStepCallback");
        this.mCurrentStepCallback = iRealStepCallback;
        ExtendHandler extendHandler = this.mExtendHandler;
        if (extendHandler != null) {
            extendHandler.sendEmptyMessage(2003);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public void pauseOrResumeStepRateRecord(boolean z) {
        LogUtil.a(TAG, "pauseOrResumeStepRateRecord ", Boolean.valueOf(z));
        a aVar = this.mSimpleStepFrequency;
        if (aVar != null) {
            if (z) {
                this.mIsSportResumed = false;
            }
            aVar.e(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendBroadcast(String str) {
        LogUtil.a(TAG, "sendBroadcast enter");
        Intent intent = new Intent();
        intent.setAction(str);
        if (LocalBroadcastManager.getInstance(BaseApplication.getContext()) != null) {
            LogUtil.a(TAG, "sendBroadcast start");
            LocalBroadcastManager.getInstance(BaseApplication.getContext()).sendBroadcast(intent);
        }
    }

    class c implements MeasureResult.MeasureResultListener {
        private c() {
        }

        @Override // com.huawei.health.device.model.MeasureResult.MeasureResultListener
        public void onMeasureFailed(MeasureResult.MeasureErrorCode measureErrorCode) {
            LogUtil.h(PluginHealthTrackAdapterImpl.TAG, "onMeasureCompleted");
        }

        @Override // com.huawei.health.device.model.MeasureResult.MeasureResultListener
        public void onMeasureDevice(String str, String str2, boolean z) {
            LogUtil.a(PluginHealthTrackAdapterImpl.TAG, "onMeasureDevice productId is ", str);
            PluginHealthTrackAdapterImpl.this.mProductId = str;
            PluginHealthTrackAdapterImpl.this.mUniqueId = str2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<StepRateData> getStepRateDataList(SparseArray<Object> sparseArray) {
        if (sparseArray == null || sparseArray.size() <= 0) {
            LogUtil.b(TAG, "getStepRateDataList data is null!");
            return null;
        }
        Object obj = sparseArray.get(2);
        if (!koq.e(obj, HiHealthData.class)) {
            LogUtil.b(TAG, "isListTypeMatch data is wrong!");
            return null;
        }
        ArrayList<StepRateData> arrayList = new ArrayList<>(16);
        for (HiHealthData hiHealthData : (List) obj) {
            StepRateData stepRateData = new StepRateData();
            stepRateData.saveTime(hiHealthData.getStartTime());
            stepRateData.saveStepRate(hiHealthData.getIntValue());
            arrayList.add(stepRateData);
        }
        return arrayList;
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public void startTickTrackDog() {
        this.mHealthOpenSdk.d(true);
        this.mHealthOpenSdk.a();
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public void stopTickTrackDog() {
        this.mHealthOpenSdk.d(false);
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public void tickTrackDog() {
        this.mHealthOpenSdk.a();
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public boolean isWarningEnable() {
        HeartZoneConf g = kor.a().g();
        this.mHeartZoneConfigInfo = g;
        if (g != null) {
            return g.isWarningEnble();
        }
        return true;
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public int getWarningLimitHeartRate() {
        HeartZoneConf g = kor.a().g();
        this.mHeartZoneConfigInfo = g;
        if (g != null) {
            return g.getWarningLimitHR();
        }
        return 176;
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public boolean isPrivacyOfSportDataSwitchOn() {
        String c2 = gmz.d().c(3);
        LogUtil.a(TAG, "isPrivacyOfSportDataSwitchOn = ", c2);
        if (TextUtils.isEmpty(c2)) {
            LogUtil.a(TAG, "isPrivacyOfSportDataSwitchOn not set");
            return false;
        }
        return "true".equals(c2);
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public void setPrivacyOfSportDataSwitch(boolean z) {
        LogUtil.a(TAG, "setPrivacyOfSportDataSwitch set to ", Boolean.valueOf(z));
        setPersonalPrivacySettingValue(3, z);
    }

    public void setPersonalPrivacySettingValue(int i, boolean z) {
        LogUtil.a(TAG, "setPersonalPrivacySettingValue... privacyId = ", Integer.valueOf(i), ", isOpen = ", Boolean.valueOf(z));
        gmz.d().c(i, z, String.valueOf(i), new IBaseResponseCallback() { // from class: com.huawei.hwadpaterhealthmgr.PluginHealthTrackAdapterImpl.7
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                if (i2 == 0) {
                    LogUtil.a(PluginHealthTrackAdapterImpl.TAG, "onResponse setUserPrivacy success ");
                } else {
                    LogUtil.b(PluginHealthTrackAdapterImpl.TAG, "onResponse setUserPrivacy failure");
                }
            }
        });
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public boolean isFitnessCourseDisplay() {
        return Utils.j();
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public boolean registerReportDataListener(IReportDataCallback iReportDataCallback) {
        HiHealthManager.d(this.mContext).subscribeHiHealthData(17, this.mReportDataSubscribeListener);
        this.mReportDataCallback = iReportDataCallback;
        return true;
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public void unRegisterReportDataListener() {
        List<Integer> list = this.mReportDataSuccessList;
        if (list == null || list.isEmpty()) {
            return;
        }
        LogUtil.a(TAG, "unRegisterReportDataListener");
        HiHealthManager.d(this.mContext).unSubscribeHiHealthData(this.mReportDataSuccessList, new HiUnSubscribeListener() { // from class: com.huawei.hwadpaterhealthmgr.PluginHealthTrackAdapterImpl.10
            @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
            public void onResult(boolean z) {
                LogUtil.a(PluginHealthTrackAdapterImpl.TAG, "unregReportDataListener isSuccess = ", Boolean.valueOf(z));
                PluginHealthTrackAdapterImpl.this.mReportDataCallback = null;
            }
        });
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public boolean registerRidePosture(IRidePostureDataCallback iRidePostureDataCallback) {
        HiHealthManager.d(this.mContext).subscribeHiHealthData(Arrays.asList(15, Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA.value())), this.mRidePostureSubscribeListener);
        this.mRidePostureCallback = iRidePostureDataCallback;
        if (this.mExtendHandler == null) {
            this.mExtendHandler = HandlerCenter.yt_(new e(), "Track_Adapter");
        }
        this.mExtendHandler.removeMessages(2005);
        this.mExtendHandler.sendEmptyMessage(2005);
        return true;
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public void unregisterRidePosture() {
        List<Integer> list = this.mRidePostureDataSuccessList;
        if (list != null && !list.isEmpty()) {
            LogUtil.a(TAG, "unregisterRidePosture");
            HiHealthManager.d(this.mContext).unSubscribeHiHealthData(this.mRidePostureDataSuccessList, new HiUnSubscribeListener() { // from class: com.huawei.hwadpaterhealthmgr.PluginHealthTrackAdapterImpl.9
                @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
                public void onResult(boolean z) {
                    LogUtil.a(PluginHealthTrackAdapterImpl.TAG, "unregReportDataListener isSuccess = ", Boolean.valueOf(z));
                    PluginHealthTrackAdapterImpl.this.mRidePostureCallback = null;
                }
            });
        }
        ExtendHandler extendHandler = this.mExtendHandler;
        if (extendHandler != null) {
            extendHandler.removeMessages(2005);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public boolean regRunningPostureListener(IRunningPostureCallback iRunningPostureCallback) {
        HiHealthManager.d(this.mContext).subscribeHiHealthData(Arrays.asList(15, 300001), this.mRunningPostureSubscribeListener);
        if (this.mExtendHandler == null) {
            this.mExtendHandler = HandlerCenter.yt_(new e(), "Track_Adapter");
        }
        this.mExtendHandler.removeMessages(2004);
        this.mExtendHandler.sendEmptyMessage(2004, 5000L);
        this.mRunningPostureCallback = iRunningPostureCallback;
        return true;
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public void unregRunningPostureListener() {
        List<Integer> list = this.mRunningPostureSuccessList;
        if (list != null && !list.isEmpty()) {
            LogUtil.a(TAG, "unregRunningPostureListener");
            HiHealthManager.d(this.mContext).unSubscribeHiHealthData(this.mRunningPostureSuccessList, new HiUnSubscribeListener() { // from class: com.huawei.hwadpaterhealthmgr.PluginHealthTrackAdapterImpl.8
                @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
                public void onResult(boolean z) {
                    LogUtil.a(PluginHealthTrackAdapterImpl.TAG, "unregRunningPostureListener isSuccess = ", Boolean.valueOf(z));
                    PluginHealthTrackAdapterImpl.this.mRunningPostureCallback = null;
                }
            });
        }
        ExtendHandler extendHandler = this.mExtendHandler;
        if (extendHandler != null) {
            extendHandler.removeMessages(2004);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public void startTreadmillStep() {
        if (this.mTreadmillManager == null && this.mContext != null) {
            this.mTreadmillManager = new TreadmillManager(this.mContext);
        }
        startStepPoint();
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public void stopTreadmillStep() {
        TreadmillManager treadmillManager = this.mTreadmillManager;
        if (treadmillManager != null) {
            treadmillManager.c();
            this.mTreadmillManager = null;
        }
        stopStepPoint();
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public void registerFreeIndoorRunningStyle(ITreadmillStyleCallback iTreadmillStyleCallback) {
        TreadmillManager treadmillManager = this.mTreadmillManager;
        if (treadmillManager != null) {
            treadmillManager.e(new com.huawei.exercise.modle.ITreadmillStyleCallback() { // from class: com.huawei.hwadpaterhealthmgr.PluginHealthTrackAdapterImpl.14
                @Override // com.huawei.exercise.modle.ITreadmillStyleCallback
                public void onTreadmillStyleChange(int i, long j) {
                    LogUtil.a(PluginHealthTrackAdapterImpl.TAG, "registerFreeIndoorRunningStyle onTreadmillStyleChange mTreadmillManager is ", Integer.valueOf(i));
                    if (PluginHealthTrackAdapterImpl.this.mTreadmillStyleCallback != null) {
                        PluginHealthTrackAdapterImpl.this.mTreadmillStyleCallback.onTreadmillStyleChange(i, j);
                    } else {
                        LogUtil.b(PluginHealthTrackAdapterImpl.TAG, "registerFreeIndoorRunningStyle mTreadmillStyleCallback is null ");
                    }
                }
            });
            this.mTreadmillStyleCallback = iTreadmillStyleCallback;
        } else {
            LogUtil.b(TAG, "registerFreeIndoorRunningStyle mTreadmillManager is null");
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public void unregisterFreeIndoorRunningStyle() {
        TreadmillManager treadmillManager = this.mTreadmillManager;
        if (treadmillManager != null) {
            treadmillManager.e();
        } else {
            LogUtil.b(TAG, "unregisterFreeIndoorRunningStyle mTreadmillManager is null");
        }
        this.mTreadmillStyleCallback = null;
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public int getCurrentSteps() {
        return sCurrentSteps;
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public boolean isWearDeviceConnected() {
        com.huawei.health.devicemgr.business.entity.DeviceInfo a2 = jpt.a(TAG);
        return a2 != null && a2.getDeviceConnectState() == 2;
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public void readLastVo2max(IBaseResponseCallback iBaseResponseCallback) {
        kor.a().e(iBaseResponseCallback);
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public void requestLocalPressure(final LocalPressureCallback localPressureCallback, final int i) {
        jdx.b(new Runnable() { // from class: com.huawei.hwadpaterhealthmgr.PluginHealthTrackAdapterImpl.13
            @Override // java.lang.Runnable
            public void run() {
                String str;
                if (i == 0) {
                    str = AnalyticsValue.HEALTH_QUARE_LOCAL_PRESSURE_1040060.value();
                    PluginHealthTrackAdapterImpl.this.setAltitudeBi("local_pressure", str);
                } else {
                    str = "";
                }
                if (localPressureCallback != null) {
                    if (CommonUtil.aa(PluginHealthTrackAdapterImpl.this.mContext)) {
                        Location aUH_ = gwe.aUH_(PluginHealthTrackAdapterImpl.this.mContext);
                        if (aUH_ == null) {
                            localPressureCallback.onFailed("location is null");
                            return;
                        }
                        jca b2 = jbr.e().b(CommonUtil.c(aUH_.getLatitude(), 3), CommonUtil.c(aUH_.getLongitude(), 3));
                        if (b2.e() != null && !b2.e().isEmpty()) {
                            ixx.d().d(BaseApplication.getContext(), str, b2.e(), 0);
                        }
                        localPressureCallback.onUpdateLocalPressure(b2.b());
                        if (Math.abs(b2.b()) > 1.0E-6d) {
                            PluginHealthTrackAdapterImpl.this.setAltitudeBi("get_pressure_success", AnalyticsValue.HEALTH_QUARE_GET_PRESSURE_SUCCESS_1040061.value());
                            return;
                        }
                        return;
                    }
                    localPressureCallback.onFailed("Network is Disconnect");
                    return;
                }
                LogUtil.h(PluginHealthTrackAdapterImpl.TAG, "callback is null");
            }
        });
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public com.huawei.health.devicemgr.business.entity.DeviceInfo getCurrentAw70DeviceInfo() {
        return jpu.d(TAG);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAltitudeBi(String str, String str2) {
        HashMap hashMap = new HashMap(1);
        hashMap.put("query", str);
        ixx.d().d(BaseApplication.getContext(), str2, hashMap, 0);
    }

    @Override // com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter
    public void setStepInterval(boolean z) {
        this.mIsScreenOn = z;
    }
}
