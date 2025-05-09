package com.huawei.health.sportservice.manager.standflexion;

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
import com.huawei.hwsportmodel.StandFlexionAchieveType;
import com.huawei.operation.OpAnalyticsConstants;
import defpackage.fgm;
import defpackage.guq;
import defpackage.koq;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SportComponentType(classify = 1, name = ComponentName.VOICE_MANAGER)
/* loaded from: classes8.dex */
public class StandFlexionVoiceManager extends BaseSportExamVoiceManager implements ManagerComponent {
    private static final int ERROR_VOICE_FULL_BODY = 1;
    private static final int ERROR_VOICE_KNEE_SLIGHTLY = 3;
    private static final int ERROR_VOICE_SIDE_BODY = 2;
    private static final int FIRST_VOICE_STAGE = 1000;
    private static final int FOURTH_VOICE_STAGE = 210000;
    private static final int MSG_GET_SCORE_VOICE = 1;
    private static final int MSG_SOUND_SUCCESS = 4;
    private static final int MSG_SOUND_VOICE_ENCOURAGE = 5;
    private static final int PLAY_DELAY_TIME = 2000;
    private static final int SECOND_VOICE_STAGE = 30000;
    private static final String TAG = "SportService_StandFlexionVoiceManager";
    private static final int THIRD_VOICE_STAGE = 90000;
    private static final int VOICE_INTERVAL_ONE_SECOND = 1000;
    private StandFlexionAchieveType mAchieveType;
    private int mCurrentStatus;
    private boolean mHasSpeakFarWarning;
    private int mPreStatus;
    private int mScore;
    private int mStatus;
    private int mTempHighScore;
    private guq mTrackVoiceManager;
    private long mLastPlayScreenTime = 0;
    private long mLastPlaySideToScreen = 0;
    private long mLastStraightTime = 0;
    private long mLastPlayStartTime = 0;
    private fgm mSportCallbackOption = new fgm();
    private String mLanguage = "zh-CN";
    private boolean mIsStartSport = false;
    private boolean mIsLegalScore = false;
    private int playStraightSoundCount = 0;
    private Handler mHandler = new KeepStatusScoreHandler(Looper.getMainLooper(), this);

    @Override // com.huawei.health.sportservice.manager.BaseSportExamVoiceManager, com.huawei.health.sportservice.manager.ManagerComponent
    public void init() {
        super.init();
        this.mTrackVoiceManager = new guq(BaseSportManager.getInstance().getSportType(), -1);
    }

    @Override // com.huawei.health.sportservice.manager.BaseSportExamVoiceManager, com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        super.onPreSport();
        loadDownSound();
        ArrayList arrayList = new ArrayList();
        arrayList.add("STAND_FLEXION_HIGHEST_SCORE");
        arrayList.add("STATUS_CODE_DATA");
        arrayList.add("TIME_ONE_SECOND_DURATION");
        arrayList.add("STAND_FLEXION_SCORE_DATA");
        arrayList.add("STAND_FLEXION_IS_LEGAL_SCORE");
        arrayList.add("STAND_FLEXION_HIGHEST_SCORE_TEMP");
        this.mSportCallbackOption.a(arrayList);
        this.mSportCallbackOption.c(TAG);
        BaseSportManager.getInstance().subscribeNotify(this.mSportCallbackOption, new SportDataNotify() { // from class: com.huawei.health.sportservice.manager.standflexion.StandFlexionVoiceManager$$ExternalSyntheticLambda0
            @Override // com.huawei.health.sportservice.SportDataNotify
            public final void onChange(List list, Map map) {
                StandFlexionVoiceManager.this.m489xd4be6b3e(list, map);
            }
        });
        BaseSportManager.getInstance().registerAchieveLevel(TAG, new SportAchieveSubscribe() { // from class: com.huawei.health.sportservice.manager.standflexion.StandFlexionVoiceManager$$ExternalSyntheticLambda1
            @Override // com.huawei.hwsportmodel.SportAchieveSubscribe
            public final void onChange(Object obj) {
                StandFlexionVoiceManager.this.m490xedbfbcdd(obj);
            }
        });
    }

    /* renamed from: lambda$onPreSport$0$com-huawei-health-sportservice-manager-standflexion-StandFlexionVoiceManager, reason: not valid java name */
    /* synthetic */ void m489xd4be6b3e(List list, Map map) {
        if (koq.b(list) || map == null) {
            LogUtil.h(TAG, "SportDataNotify failed");
            return;
        }
        if (list.contains("STAND_FLEXION_SCORE_DATA")) {
            this.mScore = ((Integer) map.get("STAND_FLEXION_SCORE_DATA")).intValue();
        }
        if (list.contains("STAND_FLEXION_HIGHEST_SCORE_TEMP")) {
            int intValue = ((Integer) map.get("STAND_FLEXION_HIGHEST_SCORE_TEMP")).intValue();
            this.mTempHighScore = intValue;
            LogUtil.a(TAG, "STAND_FLEXION_HIGHEST_SCORE_TEMP:", Integer.valueOf(intValue));
        }
        if (list.contains("STAND_FLEXION_IS_LEGAL_SCORE")) {
            this.mIsLegalScore = ((Boolean) map.get("STAND_FLEXION_IS_LEGAL_SCORE")).booleanValue();
            playDistinguish();
        }
        if (list.contains("STATUS_CODE_DATA")) {
            this.mScore = 0;
            this.mTempHighScore = 0;
            this.mStatus = ((Integer) map.get("STATUS_CODE_DATA")).intValue();
        }
        if (list.contains("TIME_ONE_SECOND_DURATION")) {
            statusDetect(((Long) map.get("TIME_ONE_SECOND_DURATION")).longValue());
        }
    }

    /* renamed from: lambda$onPreSport$1$com-huawei-health-sportservice-manager-standflexion-StandFlexionVoiceManager, reason: not valid java name */
    /* synthetic */ void m490xedbfbcdd(Object obj) {
        if (obj instanceof StandFlexionAchieveType) {
            this.mAchieveType = (StandFlexionAchieveType) obj;
        } else {
            this.mAchieveType = null;
        }
    }

    private void playDistinguish() {
        LogUtil.a(TAG, "playDistinguish isRealLegalScore", Boolean.valueOf(isRealLegalScore()));
        if (isRealLegalScore()) {
            speakNativeVoice("L021");
            this.mHandler.removeMessages(4);
            Handler handler = this.mHandler;
            handler.sendMessageDelayed(handler.obtainMessage(4), 2000L);
        }
    }

    private boolean isRealLegalScore() {
        return this.mIsLegalScore && this.mTempHighScore > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playGetFinalScore() {
        LogUtil.a(TAG, "playGetFinalScore");
        speakNativeVoice("L022");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playSuccessDistinguishVoice() {
        ReleaseLogUtil.e(TAG, "playSuccessDistinguishVoice isRealLegalScore:", Boolean.valueOf(isRealLegalScore()));
        if (isRealLegalScore()) {
            speakNativeVoice("L024");
            BaseSportManager.getInstance().stagingAndNotification("STAND_FLEXION_HIGHEST_SCORE", Integer.valueOf(this.mTempHighScore));
            this.mHandler.removeMessages(5);
            Handler handler = this.mHandler;
            handler.sendMessageDelayed(handler.obtainMessage(5), 1000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playEncourageVoice() {
        Object[] objArr = new Object[2];
        objArr[0] = "playEncourageVoice mAchieveType:";
        objArr[1] = Boolean.valueOf(this.mAchieveType == null);
        ReleaseLogUtil.e(TAG, objArr);
        this.mHandler.removeMessages(1);
        StandFlexionAchieveType standFlexionAchieveType = this.mAchieveType;
        if (standFlexionAchieveType != null) {
            speakNativeVoice(standFlexionAchieveType.getVoicePath());
            Handler handler = this.mHandler;
            handler.sendMessageDelayed(handler.obtainMessage(1), 3000L);
        } else {
            Handler handler2 = this.mHandler;
            handler2.sendMessage(handler2.obtainMessage(1));
        }
    }

    private void statusDetect(long j) {
        if (this.mIsStartSport) {
            if (!this.mHasSpeakFarWarning && this.mStatus == -4) {
                speakNativeVoice("L023");
                this.mHasSpeakFarWarning = true;
            }
            if (this.mStatus == 100 || this.mScore > 0) {
                resetValue();
                this.mHasSpeakFarWarning = false;
                return;
            }
            long j2 = this.mLastPlayStartTime;
            if (j2 == 0) {
                this.mLastPlayStartTime = j;
            } else {
                playErrorVoice(j - j2, j);
            }
        }
    }

    private void resetValue() {
        this.mLastPlayStartTime = 0L;
        this.mLastPlayScreenTime = 0L;
        this.mLastPlaySideToScreen = 0L;
        this.mLastStraightTime = 0L;
        this.playStraightSoundCount = 0;
        this.mPreStatus = 0;
    }

    private boolean isNeedResetErrorIntervalTime() {
        int i;
        int i2 = this.mPreStatus;
        return (i2 == 0 || i2 == (i = this.mCurrentStatus) || i == 0) ? false : true;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void playErrorVoice(long r12, long r14) {
        /*
            r11 = this;
            int r0 = r11.mStatus
            r1 = -8
            r2 = 2
            r3 = 1
            if (r0 == r1) goto L4c
            r1 = 101(0x65, float:1.42E-43)
            if (r0 == r1) goto L4c
            r1 = -3
            if (r0 == r1) goto L35
            r1 = -2
            if (r0 == r1) goto L35
            r1 = -1
            if (r0 == r1) goto L35
            switch(r0) {
                case -1003: goto L18;
                case -1002: goto L18;
                case -1001: goto L4c;
                default: goto L17;
            }
        L17:
            goto L62
        L18:
            r0 = 3
            r11.mCurrentStatus = r0
            long r9 = r11.mLastStraightTime
            r4 = r11
            r5 = r12
            r7 = r14
            boolean r12 = r4.isUpToThreshold(r5, r7, r9)
            if (r12 == 0) goto L62
            r11.mPreStatus = r0
            java.lang.String r12 = "L003"
            r11.speakNativeVoice(r12)
            r11.mLastStraightTime = r14
            int r12 = r11.playStraightSoundCount
            int r12 = r12 + r3
            r11.playStraightSoundCount = r12
            goto L62
        L35:
            r11.mCurrentStatus = r3
            long r9 = r11.mLastPlayScreenTime
            r4 = r11
            r5 = r12
            r7 = r14
            boolean r12 = r4.isUpToThreshold(r5, r7, r9)
            if (r12 == 0) goto L62
            r11.mPreStatus = r3
            java.lang.String r12 = "L001"
            r11.speakNativeVoice(r12)
            r11.mLastPlayScreenTime = r14
            goto L62
        L4c:
            r11.mCurrentStatus = r2
            long r9 = r11.mLastPlaySideToScreen
            r4 = r11
            r5 = r12
            r7 = r14
            boolean r12 = r4.isUpToThreshold(r5, r7, r9)
            if (r12 == 0) goto L62
            r11.mPreStatus = r2
            java.lang.String r12 = "L002"
            r11.speakNativeVoice(r12)
            r11.mLastPlaySideToScreen = r14
        L62:
            int r12 = r11.mStatus
            r13 = -1002(0xfffffffffffffc16, float:NaN)
            r14 = 0
            if (r12 == r13) goto L70
            r13 = -1003(0xfffffffffffffc15, float:NaN)
            if (r12 == r13) goto L70
            r11.playStraightSoundCount = r14
            return
        L70:
            int r12 = r11.playStraightSoundCount
            if (r12 < r2) goto L83
            com.huawei.health.sportservice.manager.BaseSportManager r12 = com.huawei.health.sportservice.manager.BaseSportManager.getInstance()
            java.lang.String r13 = "STAND_FLEXION_BEND_SHOW"
            java.lang.Boolean r15 = java.lang.Boolean.valueOf(r3)
            r12.stagingAndNotification(r13, r15)
            r11.playStraightSoundCount = r14
        L83:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.sportservice.manager.standflexion.StandFlexionVoiceManager.playErrorVoice(long, long):void");
    }

    private boolean isUpToThreshold(long j, long j2, long j3) {
        if (isNeedResetErrorIntervalTime()) {
            LogUtil.a(TAG, "mPreStatus:", Integer.valueOf(this.mPreStatus), "mCurrentStatus:", Integer.valueOf(this.mCurrentStatus));
            resetValue();
            return false;
        }
        LogUtil.a(TAG, "duration", Long.valueOf(j2), "mLastPlayTime", Long.valueOf(j3), "interval", Long.valueOf(j));
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
        speakNativeVoice("L006");
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onResumeSport() {
        guq guqVar = this.mTrackVoiceManager;
        if (guqVar != null) {
            guqVar.h();
        }
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

    static class KeepStatusScoreHandler extends BaseHandler<StandFlexionVoiceManager> {
        KeepStatusScoreHandler(Looper looper, StandFlexionVoiceManager standFlexionVoiceManager) {
            super(looper, standFlexionVoiceManager);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        public void handleMessageWhenReferenceNotNull(StandFlexionVoiceManager standFlexionVoiceManager, Message message) {
            if (message == null) {
                LogUtil.h(StandFlexionVoiceManager.TAG, "handleMessage message is null");
                return;
            }
            int i = message.what;
            if (i == 1) {
                standFlexionVoiceManager.playGetFinalScore();
            } else if (i == 4) {
                standFlexionVoiceManager.playSuccessDistinguishVoice();
            } else {
                if (i != 5) {
                    return;
                }
                standFlexionVoiceManager.playEncourageVoice();
            }
        }
    }

    @Override // com.huawei.health.sportservice.manager.BaseSportExamVoiceManager
    public String getLogTag() {
        return TAG;
    }
}
