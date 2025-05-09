package com.huawei.health.sportservice.manager.supinelegraise;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.manager.BaseSportExamVoiceManager;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.manager.ManagerComponent;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsportmodel.SportAchieveSubscribe;
import com.huawei.hwsportmodel.SupineLegAchieveType;
import com.huawei.operation.OpAnalyticsConstants;
import defpackage.fgm;
import defpackage.guq;
import defpackage.koq;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SportComponentType(classify = 1, name = ComponentName.VOICE_MANAGER)
/* loaded from: classes8.dex */
public class SupineLegRaiseVoiceManager extends BaseSportExamVoiceManager implements ManagerComponent {
    private static final int ERROR_VOICE_FULL_BODY = 1;
    private static final int ERROR_VOICE_HEAD_RAISE = 2;
    private static final int ERROR_VOICE_KNEE_SLIGHTLY = 3;
    private static final int ERROR_VOICE_RAISE_MORE = 4;
    private static final int FIRST_VOICE_STAGE = 1000;
    private static final int FOURTH_VOICE_STAGE = 210000;
    private static final int MSG_AUTO_STOP_SPORT = 1;
    private static final int SECOND_VOICE_STAGE = 30000;
    private static final String SOUND_SUCCESS_ACTION = "SOUND_SUCCESS_ACTION";
    private static final String TAG = "SportService_SupineLegRaiseVoiceManager";
    private static final int THIRD_VOICE_STAGE = 90000;
    private static final int VOICE_INTERVAL_ONE_SECOND = 1000;
    private int mCurrentStatus;
    private int mStatus;
    private guq mTrackVoiceManager;
    private long mLastPlayScreenTime = 0;
    private long mLastPlayHandRaiseTime = 0;
    private long mLastStraightTime = 0;
    private long mLastRaiseLegTime = 0;
    private long mLastPlayStartTime = 0;
    private fgm mSportCallbackOption = new fgm();
    private boolean mIsStartSport = false;
    private long mLeftScreenTime = 0;
    private Map<Integer, SupineLegAchieveType> mAchieveVoiceMap = new HashMap();
    private Handler mHandler = new StatusVoiceHandler(Looper.getMainLooper(), this);
    private boolean mIsPlayedPause = false;
    private boolean mIsPlayedStop = false;

    @Override // com.huawei.health.sportservice.manager.BaseSportExamVoiceManager, com.huawei.health.sportservice.manager.ManagerComponent
    public void init() {
        super.init();
        this.mTrackVoiceManager = new guq(BaseSportManager.getInstance().getSportType(), 0);
        initAchieveMap();
    }

    @Override // com.huawei.health.sportservice.manager.BaseSportExamVoiceManager, com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        super.onPreSport();
        loadDownSound();
        ArrayList arrayList = new ArrayList();
        arrayList.add("SPORT_EXAM_SCORE");
        arrayList.add("TIME_ONE_SECOND_DURATION");
        arrayList.add("CONTINUOUS_DATA");
        arrayList.add("STATUS_CODE_DATA");
        this.mSportCallbackOption.a(arrayList);
        this.mSportCallbackOption.c(TAG);
        BaseSportManager.getInstance().subscribeNotify(this.mSportCallbackOption, new SportDataNotify() { // from class: com.huawei.health.sportservice.manager.supinelegraise.SupineLegRaiseVoiceManager$$ExternalSyntheticLambda0
            @Override // com.huawei.health.sportservice.SportDataNotify
            public final void onChange(List list, Map map) {
                SupineLegRaiseVoiceManager.this.m492xed4b267e(list, map);
            }
        });
        BaseSportManager.getInstance().registerAchieveLevel(TAG, new SportAchieveSubscribe() { // from class: com.huawei.health.sportservice.manager.supinelegraise.SupineLegRaiseVoiceManager$$ExternalSyntheticLambda1
            @Override // com.huawei.hwsportmodel.SportAchieveSubscribe
            public final void onChange(Object obj) {
                SupineLegRaiseVoiceManager.this.m493x1bfc909d(obj);
            }
        });
    }

    /* renamed from: lambda$onPreSport$0$com-huawei-health-sportservice-manager-supinelegraise-SupineLegRaiseVoiceManager, reason: not valid java name */
    /* synthetic */ void m492xed4b267e(List list, Map map) {
        if (koq.b(list) || map == null) {
            LogUtil.h(TAG, "SportDataNotify failed");
            return;
        }
        if (list.contains("CONTINUOUS_DATA")) {
            playContinueVoice(((Integer) map.get("CONTINUOUS_DATA")).intValue());
        }
        if (list.contains("STATUS_CODE_DATA")) {
            this.mStatus = ((Integer) map.get("STATUS_CODE_DATA")).intValue();
            overTimeLeaveScreen();
        }
        if (list.contains("TIME_ONE_SECOND_DURATION")) {
            long longValue = ((Long) map.get("TIME_ONE_SECOND_DURATION")).longValue();
            countdownVoice(longValue);
            statusDetect(longValue);
        }
        playActionSound(list);
    }

    /* renamed from: lambda$onPreSport$1$com-huawei-health-sportservice-manager-supinelegraise-SupineLegRaiseVoiceManager, reason: not valid java name */
    /* synthetic */ void m493x1bfc909d(Object obj) {
        if (obj instanceof SupineLegAchieveType) {
            speakNativeVoice(((SupineLegAchieveType) obj).getVoicePath());
        }
    }

    private void playActionSound(List<String> list) {
        if (!list.contains("SPORT_EXAM_SCORE") || this.mSoundEffectManager == null) {
            return;
        }
        this.mSoundEffectManager.b("SOUND_SUCCESS_ACTION");
    }

    private void initAchieveMap() {
        for (SupineLegAchieveType supineLegAchieveType : SupineLegAchieveType.values()) {
            if (supineLegAchieveType != null) {
                this.mAchieveVoiceMap.put(Integer.valueOf(supineLegAchieveType.getContinuesTimes()), supineLegAchieveType);
            }
        }
    }

    private void playContinueVoice(int i) {
        SupineLegAchieveType supineLegAchieveType;
        Map<Integer, SupineLegAchieveType> map = this.mAchieveVoiceMap;
        if (map == null) {
            LogUtil.h(TAG, "playContinueVoice mAchieveVoiceMap == null");
        } else {
            if (!map.containsKey(Integer.valueOf(i)) || (supineLegAchieveType = this.mAchieveVoiceMap.get(Integer.valueOf(i))) == null) {
                return;
            }
            speakNativeVoice(supineLegAchieveType.getContinuesVoicePath());
        }
    }

    private void countdownVoice(long j) {
        int targetValue = ((int) BaseSportManager.getInstance().getTargetValue()) - Math.round((j * 1.0f) / 1000.0f);
        if (targetValue == 1) {
            speakNativeVoice("L113");
            return;
        }
        if (targetValue == 2) {
            speakNativeVoice("L112");
            return;
        }
        if (targetValue == 3) {
            speakNativeVoice("L111");
            return;
        }
        if (targetValue == 4) {
            speakNativeVoice("L110");
            return;
        }
        if (targetValue == 6) {
            speakNativeVoice("L109");
        } else if (targetValue == 11) {
            speakNativeVoice("L108");
        } else {
            if (targetValue != 31) {
                return;
            }
            speakNativeVoice("L107");
        }
    }

    private void statusDetect(long j) {
        if (this.mStatus == 0) {
            resetValue();
            return;
        }
        long j2 = this.mLastPlayStartTime;
        if (j2 == 0) {
            this.mLastPlayStartTime = j;
        } else {
            playErrorVoice(j - j2, j);
        }
    }

    private void resetValue() {
        this.mLastPlayStartTime = 0L;
        this.mLastPlayScreenTime = 0L;
        this.mLastPlayHandRaiseTime = 0L;
        this.mLastStraightTime = 0L;
        this.mLastRaiseLegTime = 0L;
        this.mCurrentStatus = 0;
        this.mLeftScreenTime = 0L;
    }

    private void playErrorVoice(long j, long j2) {
        if (isLeaveScreen()) {
            controlLeaveScreen(j, j2);
            return;
        }
        this.mLeftScreenTime = 0L;
        this.mCurrentStatus = 0;
        int i = this.mStatus;
        if (i == -2007) {
            if (isUpToThreshold(j, j2, this.mLastRaiseLegTime)) {
                speakNativeVoice("L102");
                this.mLastRaiseLegTime = j2;
                return;
            }
            return;
        }
        if (i == -2006) {
            long j3 = this.mLastPlayHandRaiseTime;
            if ((j2 - j3 > 20000 || j3 == 0) && isUpToThreshold(j, j2, j3)) {
                speakNativeVoice("L103");
                this.mLastPlayHandRaiseTime = j2;
                return;
            }
            return;
        }
        if (i == -2004 && isUpToThreshold(j, j2, this.mLastStraightTime)) {
            speakNativeVoice("L003");
            this.mLastStraightTime = j2;
        }
    }

    private boolean isLeaveScreen() {
        int i = this.mStatus;
        return i == -3 || i == -2 || i == -1 || i == -2003;
    }

    private void controlLeaveScreen(long j, long j2) {
        if (this.mCurrentStatus != 1) {
            this.mLeftScreenTime = System.currentTimeMillis();
        }
        this.mCurrentStatus = 1;
        if (isUpToThreshold(j, j2, this.mLastPlayScreenTime)) {
            speakNativeVoice("L104");
            this.mLastPlayScreenTime = j2;
        }
    }

    private void overTimeLeaveScreen() {
        if (this.mIsStartSport) {
            if (!isLeaveScreen() && this.mIsPlayedPause) {
                this.mLeftScreenTime = 0L;
                this.mCurrentStatus = 0;
                return;
            }
            if (this.mLeftScreenTime == 0) {
                LogUtil.a(TAG, "overTimeLeaveScreen mLeftScreenTime == 0");
                return;
            }
            if (System.currentTimeMillis() - this.mLeftScreenTime > OpAnalyticsConstants.H5_LOADING_DELAY && !this.mIsPlayedStop) {
                autoStopSport();
                this.mIsPlayedStop = true;
            } else {
                if (System.currentTimeMillis() - this.mLeftScreenTime <= 8000 || this.mIsPlayedPause) {
                    return;
                }
                BaseSportManager.getInstance().stagingAndNotification("INIT_START_SPORT", true);
                BaseSportManager.getInstance().onPauseSport();
                this.mIsPlayedPause = true;
            }
        }
    }

    private void autoStopSport() {
        if (this.mIsStartSport) {
            speakNativeVoice("L105");
            this.mHandler.removeMessages(1);
            Handler handler = this.mHandler;
            handler.sendMessageDelayed(handler.obtainMessage(1), 4000L);
        }
    }

    private boolean isUpToThreshold(long j, long j2, long j3) {
        LogUtil.a(TAG, "duration", Long.valueOf(j2), "mLastPlayTime", Long.valueOf(j3), "interval", Long.valueOf(j));
        if (j3 == 0) {
            return true;
        }
        if (j2 - j3 <= 1000) {
            return false;
        }
        if (j >= 1000 && j < 2000) {
            return true;
        }
        if (j >= OpAnalyticsConstants.H5_LOADING_DELAY && j < 31000) {
            return true;
        }
        if (j < 90000 || j >= 91000) {
            return j >= 210000 && j < 211000;
        }
        return true;
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
        this.mIsStartSport = true;
        speakNativeVoice("L101");
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onResumeSport() {
        speakNativeVoice("L114");
        this.mIsPlayedPause = false;
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPauseSport() {
        guq guqVar = this.mTrackVoiceManager;
        if (guqVar != null) {
            guqVar.b();
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        if (this.mTrackVoiceManager == null || !BaseSportManager.getInstance().isToSave()) {
            return;
        }
        this.mTrackVoiceManager.j();
    }

    @Override // com.huawei.health.sportservice.manager.BaseSportExamVoiceManager, com.huawei.health.sportservice.manager.ManagerComponent
    public void destroy() {
        super.destroy();
        BaseSportManager.getInstance().cancelSubscribeNotify(this.mSportCallbackOption);
    }

    static class StatusVoiceHandler extends BaseHandler<SupineLegRaiseVoiceManager> {
        StatusVoiceHandler(Looper looper, SupineLegRaiseVoiceManager supineLegRaiseVoiceManager) {
            super(looper, supineLegRaiseVoiceManager);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        public void handleMessageWhenReferenceNotNull(SupineLegRaiseVoiceManager supineLegRaiseVoiceManager, Message message) {
            if (message == null) {
                LogUtil.h(SupineLegRaiseVoiceManager.TAG, "handleMessage message is null");
            } else {
                if (message.what != 1) {
                    return;
                }
                BaseSportManager.getInstance().m134x32b3e3a1();
            }
        }
    }

    @Override // com.huawei.health.sportservice.manager.BaseSportExamVoiceManager
    public String getLogTag() {
        return TAG;
    }
}
