package com.huawei.health.sportservice.manager;

import android.content.Context;
import android.os.Vibrator;
import com.huawei.health.sportservice.SportBleStatus;
import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.SportLaunchParams;
import com.huawei.health.sportservice.constants.SportParamsType;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.jsoperation.JsUtil;
import defpackage.ffs;
import defpackage.fgm;
import defpackage.fhs;
import defpackage.gso;
import defpackage.guq;
import defpackage.gww;
import defpackage.gxd;
import defpackage.hab;
import defpackage.kor;
import defpackage.lcc;
import defpackage.mxb;
import health.compact.a.CloudUtils;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SportComponentType(classify = 1, name = ComponentName.VOICE_MANAGER)
/* loaded from: classes8.dex */
public class VoiceManager implements ManagerComponent, SportBleStatus {
    private static final int MS_FOR_VIBRATE = 500;
    private static final int NUMBER_ONE_FOR_PUBLIC_USE = 1;
    private static final int NUMBER_THOUSAND_FOR_PUBLIC_USE = 1000;
    private static final String TAG = "SportService_VoiceManager";
    private boolean isRestart;
    private Context mContext;
    private HeartRateData mRealTimeHeartRateDataForPlayVoice;
    private lcc mScienceGuideVoiceHelper;
    private guq mTrackVoiceManager;
    private Vibrator mVibrator;
    private int mSportType = 0;
    private fgm mSportCallbackOption = new fgm();
    private boolean mHasSetTrackVoiceManagerPlayCount = false;
    private boolean mHasPlayStartVoice = false;
    private boolean mHasPrintLocationLog = false;
    private boolean isHrControl = false;
    private boolean mHasInitTrackVoiceManagerParams = false;

    private boolean isNeedScienceGuide(int i) {
        return i == 264;
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public void setParas(SportParamsType sportParamsType, Object obj) {
        if (obj instanceof SportLaunchParams) {
            SportLaunchParams sportLaunchParams = (SportLaunchParams) obj;
            boolean isRestart = sportLaunchParams.isRestart();
            this.isRestart = isRestart;
            LogUtil.a(TAG, "setParas() isRestart: ", Boolean.valueOf(isRestart));
            boolean booleanValue = ((Boolean) sportLaunchParams.getExtra("isHrControlCourse", Boolean.class, false)).booleanValue();
            this.isHrControl = booleanValue;
            LogUtil.a(TAG, "setParas isHrControl: ", Boolean.valueOf(booleanValue));
        }
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public boolean supportParas(SportParamsType sportParamsType) {
        return SportParamsType.SPORT_LAUNCH_PARAS.equals(sportParamsType);
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        LogUtil.a(TAG, "onPreSport()");
        this.mContext = BaseApplication.getContext();
        this.mSportType = BaseSportManager.getInstance().getSportType();
        initVoiceHelper();
        initHeartRateZone();
        if (BaseSportManager.getInstance().isVoiceEnable()) {
            initVoiceAndSmartCoachService();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add("TIME_ONE_SECOND_DURATION");
        arrayList.add("DISTANCE_DATA");
        arrayList.add("HEART_RATE_DATA");
        arrayList.add("LAST_KILOMETER_PACE_DATA");
        this.mSportCallbackOption.a(arrayList);
        this.mSportCallbackOption.c(TAG);
        BaseSportManager.getInstance().subscribeNotify(this.mSportCallbackOption, getSportDataNotify());
        LogUtil.a(TAG, "BaseSportManager.getInstance().subscribeNotify()");
        BaseSportManager.getInstance().registerSportBleStatus(TAG, this);
        LogUtil.a(TAG, "BaseSportManager.getInstance().registerSportBleStatus()");
    }

    private SportDataNotify getSportDataNotify() {
        return new SportDataNotify() { // from class: com.huawei.health.sportservice.manager.VoiceManager$$ExternalSyntheticLambda0
            @Override // com.huawei.health.sportservice.SportDataNotify
            public final void onChange(List list, Map map) {
                VoiceManager.this.m478xb8b5c8e6(list, map);
            }
        };
    }

    /* renamed from: lambda$getSportDataNotify$0$com-huawei-health-sportservice-manager-VoiceManager, reason: not valid java name */
    /* synthetic */ void m478xb8b5c8e6(List list, Map map) {
        if (list.contains("TIME_ONE_SECOND_DURATION") || list.contains("LAST_KILOMETER_PACE_DATA")) {
            LogUtil.a(TAG, "tagList: ", list, ", sportDataMap: ", map);
            long sportDurationData = getSportDurationData(map);
            int sportDistanceData = getSportDistanceData(map);
            long sportLastKilometerPaceData = getSportLastKilometerPaceData(map);
            int sportHeartRateData = getSportHeartRateData(map);
            if (sportDurationData > 0 && list.contains("LAST_KILOMETER_PACE_DATA")) {
                playVoice(sportDurationData, sportDistanceData, sportLastKilometerPaceData, sportHeartRateData);
                return;
            }
            if (sportDurationData > 0 && sportDistanceData > 0) {
                onCreateVoice(sportDurationData, sportDistanceData);
            }
            if (sportDurationData > 0) {
                handleScienceGuideVoiceData();
                playVoice(sportDurationData, sportDistanceData, sportLastKilometerPaceData, sportHeartRateData);
                playWarningVoice(sportHeartRateData);
            }
            if (sportDistanceData > 0) {
                setTrackVoiceManagerPlayCount(sportDistanceData);
            }
        }
    }

    private long getSportDurationData(Map<String, Object> map) {
        Object obj = map.get("TIME_ONE_SECOND_DURATION");
        if (obj instanceof Long) {
            return ((Long) obj).longValue();
        }
        return 0L;
    }

    private int getSportDistanceData(Map<String, Object> map) {
        Object obj = map.get("DISTANCE_DATA");
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    private long getSportLastKilometerPaceData(Map<String, Object> map) {
        Object obj = map.get("LAST_KILOMETER_PACE_DATA");
        if (obj instanceof Long) {
            return ((Long) obj).longValue();
        }
        return -1L;
    }

    private int getSportHeartRateData(Map<String, Object> map) {
        Object obj = map.get("HEART_RATE_DATA");
        int intValue = obj instanceof Integer ? ((Integer) obj).intValue() : 0;
        if (fhs.c(intValue)) {
            return intValue;
        }
        return -1;
    }

    private void initVoiceHelper() {
        LogUtil.a(TAG, "initVoiceHelper()");
        if (this.mScienceGuideVoiceHelper == null && isEnableCurLang(this.mContext) && isNeedScienceGuide(this.mSportType)) {
            LogUtil.a(TAG, "initVoiceHelper() new ScienceGuideVoiceHelper()");
            this.mScienceGuideVoiceHelper = new lcc();
        }
    }

    private void initHeartRateZone() {
        int e;
        int c;
        LogUtil.a(TAG, "initHeartRateZone()");
        HeartZoneConf g = kor.a().g();
        if (g != null) {
            if (g.getClassifyMethod() == 0) {
                e = kor.a().i();
                c = kor.a().f();
            } else {
                e = kor.a().e();
                c = kor.a().c();
            }
            if (this.mScienceGuideVoiceHelper != null) {
                LogUtil.a(TAG, "initHeartRateZone() initParams() warmUp: ", Integer.valueOf(c), ", reduceFat: ", Integer.valueOf(e));
                this.mScienceGuideVoiceHelper.a(c, e - 1);
            }
        }
    }

    private void initVoiceAndSmartCoachService() {
        if (!UnitUtil.h()) {
            gxd.a().h(TAG);
            LogUtil.a(TAG, "startVoiceService");
            if (isEnableCurLang(this.mContext)) {
                LogUtil.a(TAG, "new TrackVoiceManager()");
                this.mTrackVoiceManager = new guq(this.mSportType, -1);
            }
        }
        if (this.mSportType != 264 || this.isHrControl) {
            return;
        }
        LogUtil.a(TAG, "startSmartCoachVoiceFunction()");
        hab.c(264, -1, true);
    }

    private void onCreateVoice(long j, int i) {
        if (this.mHasInitTrackVoiceManagerParams || this.mTrackVoiceManager == null) {
            return;
        }
        LogUtil.a(TAG, "onCreateVoice() sportDuration: ", Long.valueOf(j), ", totalDistanceValid: ", Integer.valueOf(i), ", mHasPlayStartVoice: ", Boolean.valueOf(this.mHasPlayStartVoice));
        this.mTrackVoiceManager.c(j, i);
        this.mHasInitTrackVoiceManagerParams = true;
        if (this.mHasPlayStartVoice || this.isRestart) {
            return;
        }
        vibrateAndPlayStartSportVoice();
    }

    private void setTrackVoiceManagerPlayCount(int i) {
        guq guqVar;
        if (!isEnableCurLang(this.mContext) || (guqVar = this.mTrackVoiceManager) == null || this.mHasSetTrackVoiceManagerPlayCount || guqVar.e() == 0 || this.mTrackVoiceManager.c() == 1) {
            return;
        }
        int e = i / this.mTrackVoiceManager.e();
        LogUtil.a(TAG, "setPlayCount() startDistance: ", Integer.valueOf(i), ", setPlayCount: ", Integer.valueOf(e));
        this.mTrackVoiceManager.d(e);
        this.mHasSetTrackVoiceManagerPlayCount = true;
    }

    private void handleScienceGuideVoiceData() {
        if (this.mContext == null || this.mScienceGuideVoiceHelper == null) {
            return;
        }
        Object data = BaseSportManager.getInstance().getData("SPEED_DATA");
        int intValue = data instanceof Integer ? ((Integer) data).intValue() : 0;
        Object data2 = BaseSportManager.getInstance().getData("STEP_RATE_DATA");
        int intValue2 = data2 instanceof Integer ? ((Integer) data2).intValue() : 0;
        Object data3 = BaseSportManager.getInstance().getData("RUNNING_POSTURE_DATA");
        ffs ffsVar = data3 instanceof ffs ? (ffs) data3 : null;
        Object data4 = BaseSportManager.getInstance().getData(JsUtil.DataFunc.MOTION_PATH_DATA);
        ArrayList<HeartRateData> requestHeartRateList = data4 instanceof MotionPath ? ((MotionPath) data4).requestHeartRateList() : null;
        if (this.isHrControl) {
            this.mScienceGuideVoiceHelper.b(intValue, intValue2, ffsVar, this.mContext);
        } else {
            this.mScienceGuideVoiceHelper.e(intValue, intValue2, ffsVar, requestHeartRateList, this.mContext);
        }
    }

    private void playVoice(long j, int i, long j2, int i2) {
        if (this.mTrackVoiceManager != null && isEnableCurLang(this.mContext) && !UnitUtil.h()) {
            long j3 = (i < 1000 || i % 1000 != 0) ? -1L : 1000 * j2;
            LogUtil.a(TAG, "playVoice() duration: ", Long.valueOf(j), ", distance: ", Integer.valueOf(i), ", lastKilometerPace: ", Long.valueOf(j2), ", heartRate: ", Integer.valueOf(i2));
            this.mTrackVoiceManager.d(j, i, j3, i2);
            return;
        }
        LogUtil.h(TAG, "will play voice but mTrackVoiceManager is null");
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
        LogUtil.a(TAG, "onStartSport() isRestart: ", Boolean.valueOf(this.isRestart));
        if (this.isRestart) {
            return;
        }
        vibrateAndPlayStartSportVoice();
    }

    private void vibrateAndPlayStartSportVoice() {
        vibrate();
        if (this.mTrackVoiceManager == null || !isEnableCurLang(this.mContext)) {
            return;
        }
        this.mTrackVoiceManager.g();
        this.mHasPlayStartVoice = true;
    }

    private void vibrate() {
        Vibrator vibrator = this.mVibrator;
        if (vibrator == null) {
            Object systemService = BaseApplication.getContext().getSystemService("vibrator");
            if (systemService instanceof Vibrator) {
                this.mVibrator = (Vibrator) systemService;
                return;
            }
            return;
        }
        vibrator.vibrate(500L);
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onResumeSport() {
        LogUtil.a(TAG, "onResumeSport()");
        vibrate();
        if (this.mTrackVoiceManager == null || !isEnableCurLang(this.mContext)) {
            return;
        }
        this.mTrackVoiceManager.h();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPauseSport() {
        LogUtil.a(TAG, "onPauseSport()");
        vibrate();
        if (this.mTrackVoiceManager == null || !isEnableCurLang(this.mContext)) {
            return;
        }
        this.mTrackVoiceManager.b();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        LogUtil.a(TAG, "onStopSport()");
        speakStopSport();
        gxd.a().f(TAG);
    }

    private void speakStopSport() {
        if (!BaseSportManager.getInstance().isToSave()) {
            LogUtil.a(TAG, "speakStopSport() not play stop sport voice");
            return;
        }
        vibrate();
        if (this.mTrackVoiceManager == null || !isEnableCurLang(this.mContext)) {
            return;
        }
        if (LanguageUtil.j(this.mContext)) {
            LogUtil.a(TAG, " stop voice");
            this.mTrackVoiceManager.l();
        } else {
            this.mTrackVoiceManager.j();
        }
    }

    private void playWarningVoice(int i) {
        if (this.mTrackVoiceManager != null && isEnableCurLang(this.mContext) && fhs.c(i)) {
            if (this.mRealTimeHeartRateDataForPlayVoice == null) {
                this.mRealTimeHeartRateDataForPlayVoice = new HeartRateData();
            }
            this.mRealTimeHeartRateDataForPlayVoice.saveHeartRate(i);
            this.mRealTimeHeartRateDataForPlayVoice.saveTime(System.currentTimeMillis());
            this.mTrackVoiceManager.d(this.mRealTimeHeartRateDataForPlayVoice);
            LogUtil.a(TAG, "play voice and play warning(hr value valid): HeartRateData is ", Integer.valueOf(this.mRealTimeHeartRateDataForPlayVoice.acquireHeartRate()));
        }
    }

    private boolean isEnableCurLang(Context context) {
        if (mxb.a().c(context)) {
            return true;
        }
        return isRealChineseMainland(context);
    }

    private boolean isRealChineseMainland(Context context) {
        if (context == null) {
            LogUtil.b(TAG, "context == null");
            return false;
        }
        boolean m = LanguageUtil.m(context);
        boolean d = CloudUtils.d();
        boolean y = CommonUtil.y(context);
        boolean h = UnitUtil.h();
        if (!this.mHasPrintLocationLog) {
            ReleaseLogUtil.e(TAG, "a: ", Boolean.valueOf(m), ", b: ", Boolean.valueOf(d), ", c: ", Boolean.valueOf(y), ", d: ", Boolean.valueOf(h));
            this.mHasPrintLocationLog = true;
        }
        return m && !d && y && !h;
    }

    public void startSportGuide(int i, Context context) {
        LogUtil.a(TAG, "startSportGuide() sportType: ", Integer.valueOf(i));
        if (isEnableCurLang(context) && gww.a()) {
            if (i == 264) {
                LogUtil.a(TAG, "startSportGuide() play voice 1");
                gso.e().a(9, 0);
            } else if (fhs.e(i) || i == 281) {
                LogUtil.a(TAG, "startSportGuide() play voice 2");
                gso.e().a(29, 0);
            } else {
                LogUtil.a(TAG, "startSportGuide() error sportType: ", Integer.valueOf(i));
            }
        }
    }

    public void playConnectVoice(int i, int i2) {
        if (isEnableCurLang(this.mContext) && gww.a()) {
            gso.e().a(i, i2);
        }
    }

    @Override // com.huawei.health.sportservice.SportBleStatus
    public void onConnected() {
        if (isEnableCurLang(this.mContext) && gww.a()) {
            LogUtil.a(TAG, "onConnected() play voice CONNECT_SUCCESS");
            gso.e().a(8, 0);
        }
    }

    @Override // com.huawei.health.sportservice.SportBleStatus
    public void onReadSuccess() {
        Object data = BaseSportManager.getInstance().getData("DISTANCE_DATA");
        LogUtil.a(TAG, "onReadSuccess() mSportType: ", Integer.valueOf(this.mSportType), ", mContext: ", this.mContext, ", distanceObj: ", data);
        if ((data instanceof Integer) && ((Integer) data).intValue() == 0) {
            startSportGuide(this.mSportType, this.mContext);
        }
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public void destroy() {
        BaseSportManager.getInstance().cancelSubscribeNotify(this.mSportCallbackOption);
    }
}
