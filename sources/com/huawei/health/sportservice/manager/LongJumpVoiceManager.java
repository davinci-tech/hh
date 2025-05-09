package com.huawei.health.sportservice.manager;

import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.fgm;
import defpackage.koq;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SportComponentType(classify = 1, name = ComponentName.VOICE_MANAGER)
/* loaded from: classes8.dex */
public class LongJumpVoiceManager extends BaseSportExamVoiceManager implements ManagerComponent {
    private static final int FIRST_TEST = 1;
    private static final long INTERVAL_TIME_ONE = 1000;
    private static final long INTERVAL_TIME_TWO = 2000;
    private static final int MSG_START_PLAY_SCORE = 3;
    private static final int MSG_START_POSITION_LAST_TEST = 2;
    private static final int MSG_START_POSITION_SECOND_TEST = 1;
    private static final int SECOND_TEST = 2;
    private static final String SOUND_SUCCESS_ACTION = "SOUND_SUCCESS_ACTION";
    private static final String TAG = "SportService_LongJumpVoiceManager";
    private long mDuration;
    private int mStatus;
    private fgm mSportCallbackOption = new fgm();
    private boolean mIsStartSport = false;
    private Handler mHandler = new StatusVoiceHandler(Looper.getMainLooper(), this);
    private List<Integer> mScoreList = new ArrayList(3);
    private long mCurrentReadyTime = 0;
    private boolean mIsStopSport = false;
    private boolean mIsPlayForwardStartJump = false;

    private static boolean isSuccessCode(int i) {
        return i == -3000 || i == -3007;
    }

    @Override // com.huawei.health.sportservice.manager.BaseSportExamVoiceManager, com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        super.onPreSport();
        loadDownSound();
        ArrayList arrayList = new ArrayList();
        arrayList.add("SPORT_EXAM_SCORE");
        arrayList.add("TIME_ONE_SECOND_DURATION");
        arrayList.add("STATUS_CODE_DATA");
        this.mSportCallbackOption.a(arrayList);
        this.mSportCallbackOption.c(TAG);
        BaseSportManager.getInstance().subscribeNotify(this.mSportCallbackOption, new SportDataNotify() { // from class: com.huawei.health.sportservice.manager.LongJumpVoiceManager$$ExternalSyntheticLambda0
            @Override // com.huawei.health.sportservice.SportDataNotify
            public final void onChange(List list, Map map) {
                LongJumpVoiceManager.this.m465x33a9fa2f(list, map);
            }
        });
    }

    /* renamed from: lambda$onPreSport$0$com-huawei-health-sportservice-manager-LongJumpVoiceManager, reason: not valid java name */
    /* synthetic */ void m465x33a9fa2f(List list, Map map) {
        if (koq.b(list) || map == null) {
            LogUtil.h(TAG, "SportDataNotify failed");
            return;
        }
        if (list.contains("STATUS_CODE_DATA")) {
            this.mStatus = ((Integer) map.get("STATUS_CODE_DATA")).intValue();
            playErrorVoice();
        }
        if (list.contains("TIME_ONE_SECOND_DURATION")) {
            this.mDuration = ((Long) map.get("TIME_ONE_SECOND_DURATION")).longValue();
            playForwardJumpVoice();
        }
        if (list.contains("SPORT_EXAM_SCORE")) {
            if (this.mSoundEffectManager != null) {
                this.mSoundEffectManager.b("SOUND_SUCCESS_ACTION");
            }
            this.mCurrentReadyTime = 0L;
            this.mIsPlayForwardStartJump = false;
            Message obtainMessage = this.mHandler.obtainMessage(3);
            obtainMessage.obj = map.get("SPORT_EXAM_SCORE");
            this.mHandler.sendMessageDelayed(obtainMessage, 1000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playScoreVoice(int i) {
        if (i < 50) {
            speakNativeVoice("L206");
            return;
        }
        this.mScoreList.add(Integer.valueOf(i));
        if (koq.b(this.mScoreList)) {
            LogUtil.h(TAG, "playScoreVoice mScoreList null");
            return;
        }
        if (this.mScoreList.size() == 1) {
            speakNativeVoice("L117");
            Handler handler = this.mHandler;
            handler.sendMessageDelayed(handler.obtainMessage(1), INTERVAL_TIME_TWO);
            return;
        }
        if (this.mScoreList.size() == 2) {
            if (this.mScoreList.get(1).intValue() > this.mScoreList.get(0).intValue()) {
                speakNativeVoice("L119");
            } else {
                speakNativeVoice("L115");
            }
        }
        if (this.mScoreList.size() == 2) {
            Handler handler2 = this.mHandler;
            handler2.sendMessageDelayed(handler2.obtainMessage(2), INTERVAL_TIME_TWO);
        }
    }

    private void playErrorVoice() {
        if (this.mIsStopSport) {
            return;
        }
        if (!isSuccessCode(this.mStatus)) {
            this.mIsPlayForwardStartJump = false;
            this.mCurrentReadyTime = 0L;
        }
        if (isLeaveScreen()) {
            playLeftScreenVoice();
            return;
        }
        int i = this.mStatus;
        if (i != -3000) {
            switch (i) {
                case -3006:
                    speakNativeVoice("L205");
                    break;
                case -3005:
                    speakNativeVoice("L204");
                    break;
                case -3004:
                    speakNativeVoice("L203");
                    break;
            }
            return;
        }
        if (this.mIsStartSport && this.mCurrentReadyTime == 0) {
            this.mCurrentReadyTime = this.mDuration;
            speakNativeVoice("L210");
        }
    }

    private void playForwardJumpVoice() {
        int i = this.mStatus;
        if (i == -3000 || i == -3007) {
            long j = this.mCurrentReadyTime;
            if (j == 0 || this.mIsPlayForwardStartJump || this.mDuration - j <= 4000) {
                return;
            }
            speakNativeVoice("L209");
            this.mCurrentReadyTime = 0L;
            this.mIsPlayForwardStartJump = true;
        }
    }

    private boolean isLeaveScreen() {
        int i = this.mStatus;
        return i == -3 || i == -2 || i == -1;
    }

    private void playLeftScreenVoice() {
        if (this.mIsStartSport) {
            speakNativeVoice("L104");
            return;
        }
        Resources resources = BaseApplication.getContext().getResources();
        if (resources == null) {
            LogUtil.h(TAG, "playLeftScreenVoice resources null");
        } else if (resources.getConfiguration().orientation == 2) {
            speakNativeVoice("L202");
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
        this.mIsStartSport = true;
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        this.mIsStopSport = true;
    }

    @Override // com.huawei.health.sportservice.manager.BaseSportExamVoiceManager, com.huawei.health.sportservice.manager.ManagerComponent
    public void destroy() {
        super.destroy();
        BaseSportManager.getInstance().cancelSubscribeNotify(this.mSportCallbackOption);
    }

    static class StatusVoiceHandler extends BaseHandler<LongJumpVoiceManager> {
        StatusVoiceHandler(Looper looper, LongJumpVoiceManager longJumpVoiceManager) {
            super(looper, longJumpVoiceManager);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        public void handleMessageWhenReferenceNotNull(LongJumpVoiceManager longJumpVoiceManager, Message message) {
            if (message == null) {
                LogUtil.h(LongJumpVoiceManager.TAG, "handleMessage message is null");
                return;
            }
            int i = message.what;
            if (i == 1) {
                longJumpVoiceManager.speakNativeVoice("L207");
                return;
            }
            if (i == 2) {
                longJumpVoiceManager.speakNativeVoice("L208");
            } else if (i == 3 && (message.obj instanceof Integer)) {
                longJumpVoiceManager.playScoreVoice(((Integer) message.obj).intValue());
            }
        }
    }

    @Override // com.huawei.health.sportservice.manager.BaseSportExamVoiceManager
    public String getLogTag() {
        return TAG;
    }
}
