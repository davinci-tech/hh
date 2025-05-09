package com.huawei.healthcloud.plugintrack.ui.viewmodel;

import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.huawei.health.basesport.viewmodel.BaseSportingViewModel;
import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.constants.SportParamsType;
import com.huawei.healthcloud.plugintrack.ui.view.glrender.util.Camera2Helper;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import defpackage.fgj;
import defpackage.gxd;
import defpackage.gyi;
import defpackage.gyr;
import defpackage.kxa;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public abstract class SportExamViewModel extends BaseSportingViewModel implements SportDataNotify, Camera2Helper.OnPreviewListener, Handler.Callback {
    public static final int MAX_PROGRESS = 100;
    private static final int MSG_PRE_SPORT_VOICE = 3;
    private static final int MSG_UPDATE_DETECT_STATES = 1;
    private static final int MSG_UPDATE_PRE_PROGRESS = 2;
    private static final int PRE_PROGRESS_DURATION = 1000;
    private static final int PRE_PROGRESS_STEP = 5;
    private static final int SPORT_TIME_COUNT_DOWN = 10;
    private static final String TAG = "Track_SportExamViewModel";
    private int mHeight;
    private int mWidth;
    protected List<BaseSportingViewModel.ReceivedDataHandler> mReceivedData = new ArrayList();
    private int mPreDetectStatus = -3;
    private int mPreProgress = 0;
    private boolean mIsInit = false;
    private boolean mCanToStart = false;
    private int mCameraId = 0;

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public abstract void initFragment();

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel, com.huawei.health.basesport.viewmodel.ISportPagerConfig
    public boolean isCanScroll() {
        return false;
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public boolean isStopSportByTarget() {
        return false;
    }

    protected abstract void playPreSportTipsVoice();

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public void init(Bundle bundle) {
        super.init(bundle);
        addSportDataMap("sportExamScore");
        addSportDataMap("showSportTime");
        addSportDataMap("encourageType");
        addSportDataMap("continuouslyTimes");
        addSportDataMap("interruptTimes");
        addSportDataMap("bodyPosition");
        addSportDataMap("bodyDetectionError");
        addSportDataMap("preSportProgress");
        addSportDataMap("sportTimeCountDown");
        addSportDataMap(ParsedFieldTag.NPES_SPORT_TIME);
        addSportDataMap("calorie");
    }

    public void enableCanStart(boolean z) {
        this.mCanToStart = z;
        if (!z || this.mExtendHandler == null) {
            return;
        }
        this.mExtendHandler.sendEmptyMessage(3, 1000L);
        this.mExtendHandler.sendEmptyMessage(2);
    }

    public void speakNativeVoice(String str) {
        LogUtil.a(getTag(), "speakNativeVoice() voice: ", str);
        String c = gyr.e().c(str, "mp3");
        gxd.a().b("assert" + c);
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            int intValue = ((Integer) message.obj).intValue();
            if (this.mPreProgress >= 100) {
                LogUtil.a(TAG, "handleMessage detectStates", Integer.valueOf(intValue));
            } else if (this.mPreDetectStatus != intValue && this.mCanToStart && this.mExtendHandler != null) {
                this.mPreDetectStatus = intValue;
                this.mExtendHandler.sendEmptyMessage(2);
            }
            postValue("bodyDetectionError", Integer.valueOf(intValue));
        } else if (i == 2) {
            updatePreProgress();
        } else {
            if (i != 3) {
                return false;
            }
            playPreSportTipsVoice();
        }
        return true;
    }

    private void updatePreProgress() {
        if (this.mPreDetectStatus == gyi.d(getSportType())) {
            if (this.mPreProgress >= 100 || this.mExtendHandler == null) {
                return;
            }
            int i = this.mPreProgress + 5;
            this.mPreProgress = i;
            postValue("preSportProgress", Integer.valueOf(i));
            this.mExtendHandler.sendEmptyMessage(2, 50L);
            return;
        }
        if (this.mPreProgress < 100) {
            this.mPreProgress = 0;
            postValue("preSportProgress", 0);
        }
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public void observeSportingData(String str, LifecycleOwner lifecycleOwner, Observer observer) {
        super.observeSportingData(str, lifecycleOwner, observer);
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public void requestData() {
        super.requestData();
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public void requestLocalData() {
        super.requestLocalData();
        postValue("sportExamScore", 0);
        if (getSportTarget() == 0) {
            postValue("showSportTime", UnitUtil.a((int) getTargetValue()));
        } else {
            postValue("showSportTime", UnitUtil.a(0));
        }
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public List<String> getSubscribeTagList() {
        ArrayList arrayList = new ArrayList(6);
        arrayList.add("SPORT_EXAM_SCORE");
        arrayList.add("BODY_POINT_DATA");
        arrayList.add("CONTINUOUS_DATA");
        arrayList.add("TIME_ONE_SECOND_DURATION");
        arrayList.add("STATUS_CODE_DATA");
        arrayList.add("INTERRUPT_DATA");
        return arrayList;
    }

    public void onPreviewFrame(Image image, int i, int i2) {
        if (this.mSportDataOutputApi == null) {
            return;
        }
        if (this.mWidth != i || this.mHeight != i2) {
            this.mWidth = i;
            this.mHeight = i2;
            this.mSportDataOutputApi.setParas(SportParamsType.AI_ROPE_PARAS, new fgj(this.mWidth, this.mHeight, this.mCameraId));
            if (!this.mIsInit) {
                this.mIsInit = true;
                onPreSport();
            }
        }
        if (this.mSportDataOutputApi.isAlreadyInit()) {
            this.mSportDataOutputApi.setParas(SportParamsType.AI_ROPE_INPUT_IMAGE, image);
        }
    }

    public boolean isAlreadyInit() {
        if (this.mSportDataOutputApi != null) {
            return this.mSportDataOutputApi.isAlreadyInit();
        }
        return false;
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public void initReceivedDataHandlers() {
        receivedDataHandleAddScore();
        receivedDataHandleAddContinuousData();
        receivedDataHandleAddShowSportTime();
        receivedDataHandleAddBodyDetectionError();
        receivedDataHandleAddBodyPoint();
        receivedDataHandleAddInterruptTimes();
    }

    private void receivedDataHandleAddInterruptTimes() {
        this.mReceivedData.add(new BaseSportingViewModel.c(this, "INTERRUPT_DATA", "interruptTimes", Integer.class));
    }

    private void receivedDataHandleAddBodyDetectionError() {
        this.mReceivedData.add(new BaseSportingViewModel.ReceivedDataHandler<Integer>(this, "STATUS_CODE_DATA", "bodyDetectionError", Integer.class) { // from class: com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel.ReceivedDataHandler
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void handleInner(Integer num, Map map) {
                LogUtil.h(SportExamViewModel.this.getTag(), this.mManagerDataTag, num);
                if (SportExamViewModel.this.mExtendHandler != null) {
                    Message obtain = Message.obtain();
                    obtain.what = 1;
                    obtain.obj = num;
                    SportExamViewModel.this.mExtendHandler.sendMessage(obtain);
                }
            }
        });
    }

    private void receivedDataHandleAddShowSportTime() {
        this.mReceivedData.add(new BaseSportingViewModel.ReceivedDataHandler<Long>(this, "TIME_ONE_SECOND_DURATION", "showSportTime", Long.class) { // from class: com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel.5
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel.ReceivedDataHandler
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void handleInner(Long l, Map<String, Object> map) {
                int round = Math.round((l.longValue() * 1.0f) / 1000.0f);
                SportExamViewModel.this.sendSportTime(round);
                if (SportExamViewModel.this.getSportTarget() == 0) {
                    int targetValue = (int) (SportExamViewModel.this.getTargetValue() - round);
                    SportExamViewModel.this.postValue(this.mViewModelTag, UnitUtil.a(targetValue));
                    if (targetValue <= 10) {
                        SportExamViewModel.this.postValue("sportTimeCountDown", true);
                    }
                } else {
                    SportExamViewModel.this.postValue(this.mViewModelTag, UnitUtil.a(round));
                }
                if (map.containsKey("CALORIES_DATA")) {
                    Object obj = map.get("CALORIES_DATA");
                    SportExamViewModel.this.postValue("calorie", obj);
                    LogUtil.a(SportExamViewModel.TAG, " calorie ", obj);
                }
            }
        });
    }

    protected void sendSportTime(int i) {
        postValue(ParsedFieldTag.NPES_SPORT_TIME, Integer.valueOf(i));
    }

    private void receivedDataHandleAddContinuousData() {
        this.mReceivedData.add(new BaseSportingViewModel.ReceivedDataHandler<Integer>(this, "CONTINUOUS_DATA", "continuouslyTimes", Integer.class) { // from class: com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel.ReceivedDataHandler
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void handleInner(Integer num, Map<String, Object> map) {
                if (num.intValue() > 0) {
                    SportExamViewModel.this.postValue("continuouslyTimes", num);
                }
            }
        });
    }

    private void receivedDataHandleAddScore() {
        this.mReceivedData.add(new BaseSportingViewModel.ReceivedDataHandler<Integer>(this, "SPORT_EXAM_SCORE", "sportExamScore", Integer.class) { // from class: com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel.ReceivedDataHandler
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void handleInner(Integer num, Map<String, Object> map) {
                SportExamViewModel.this.handlePostSportExamScore(num.intValue());
            }
        });
    }

    private void receivedDataHandleAddBodyPoint() {
        this.mReceivedData.add(new BaseSportingViewModel.ReceivedDataHandler<kxa>(this, "BODY_POINT_DATA", "bodyPosition", kxa.class) { // from class: com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel.ReceivedDataHandler
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void handleInner(kxa kxaVar, Map<String, Object> map) {
                SportExamViewModel.this.postValue("bodyPosition", kxaVar);
            }
        });
    }

    protected void handlePostSportExamScore(int i) {
        postValue("sportExamScore", Integer.valueOf(i));
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel, com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
        super.onStartSport();
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel, com.huawei.health.basesport.viewmodel.ISportPagerConfig
    public int getDefaultItem() {
        if (this.mIsFirstIn) {
            return Math.max(0, getCount() - 1);
        }
        return 0;
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel, androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
    }

    public boolean getIsFirstInit() {
        return this.mIsFirstIn;
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel, com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        super.onPreSport();
        ReleaseLogUtil.e(TAG, "onPreSport()");
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel, com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        if (this.mSportLifeCircleApi != null) {
            ReleaseLogUtil.e(TAG, "onStopSport()");
            this.mSportLifeCircleApi.m134x32b3e3a1();
            gxd.a().f(TAG);
            this.mSportLifeCircleApi = null;
        }
    }

    public void switchCamera(boolean z) {
        int i;
        int i2 = this.mWidth;
        if (i2 == 0 || (i = this.mHeight) == 0) {
            LogUtil.h(getTag(), "mWidth or mHeight is zero, return");
        } else {
            this.mCameraId = z ? 1 : 0;
            this.mSportDataOutputApi.setParas(SportParamsType.AI_ROPE_PARAS, new fgj(i2, i, z ? 1 : 0));
        }
    }

    public void resetProgress() {
        this.mPreProgress = 0;
    }

    protected void receivedAllData() {
        if (this.mReceivedDataHandlers == null || this.mReceivedData == null) {
            return;
        }
        this.mReceivedDataHandlers.addAll(this.mReceivedData);
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public String getTag() {
        return TAG;
    }
}
