package com.huawei.health.sportservice.manager.aitrain;

import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.SportLaunchParams;
import com.huawei.health.sportservice.constants.SportParamsType;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.manager.ManagerComponent;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.AIActionBundle;
import defpackage.fgm;
import defpackage.gxd;
import defpackage.gyl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SportComponentType(classify = 1, name = ComponentName.VOICE_MANAGER)
/* loaded from: classes8.dex */
public class ActionTrainVoiceManager implements ManagerComponent {
    private static final String TAG = "SportService_ActionTrainVoiceManager";
    private int mActionType = -1;
    private String mLanguage = "zh-CN";
    private int mLastStatusCode = 0;
    private fgm mSportCallbackOption = new fgm();

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public void init() {
        gxd.a().h(TAG);
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        LogUtil.a(TAG, "onPreSport");
        getActionType();
        speakNativeVoice("adjust_device_body_in");
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("TIME_ONE_SECOND_DURATION");
        arrayList.add("STATUS_CODE_DATA");
        arrayList.add("AI_TRAIN_RESULT_CODE");
        arrayList.add("AI_TRAIN_VALID_TIMES");
        this.mSportCallbackOption.a(arrayList);
        this.mSportCallbackOption.c(TAG);
        BaseSportManager.getInstance().subscribeNotify(this.mSportCallbackOption, new SportDataNotify() { // from class: com.huawei.health.sportservice.manager.aitrain.ActionTrainVoiceManager$$ExternalSyntheticLambda0
            @Override // com.huawei.health.sportservice.SportDataNotify
            public final void onChange(List list, Map map) {
                ActionTrainVoiceManager.this.m479x825f5ad5(list, map);
            }
        });
    }

    /* renamed from: lambda$onStartSport$0$com-huawei-health-sportservice-manager-aitrain-ActionTrainVoiceManager, reason: not valid java name */
    /* synthetic */ void m479x825f5ad5(List list, Map map) {
        int intValue;
        LogUtil.a(TAG, "tagList: ", list, ", sportDataList: ", map);
        if (list.contains("TIME_ONE_SECOND_DURATION")) {
            Object obj = map.get("TIME_ONE_SECOND_DURATION");
            if (!(obj instanceof Long)) {
                return;
            }
            if (BaseSportManager.getInstance().getTargetType() == 0) {
                Long l = (Long) obj;
                countdownVoice(l.longValue());
                halfTargetFinishVoice(l.longValue() / 1000);
            }
        }
        if (list.contains("AI_TRAIN_VALID_TIMES")) {
            if (!(map.get("AI_TRAIN_VALID_TIMES") instanceof Integer)) {
                return;
            }
            if (BaseSportManager.getInstance().getTargetType() == 5) {
                halfTargetFinishVoice(((Integer) r0).intValue());
            }
        }
        if (list.contains("STATUS_CODE_DATA")) {
            Object obj2 = map.get("STATUS_CODE_DATA");
            if ((obj2 instanceof Integer) && this.mLastStatusCode != (intValue = ((Integer) obj2).intValue())) {
                statusCodeVoice(intValue);
                this.mLastStatusCode = intValue;
            }
        }
        if (list.contains("AI_TRAIN_RESULT_CODE")) {
            Object obj3 = map.get("AI_TRAIN_RESULT_CODE");
            if (obj3 instanceof Integer) {
                resultCodeVoice(((Integer) obj3).intValue());
            }
        }
    }

    private void halfTargetFinishVoice(long j) {
        if (BaseSportManager.getInstance().getTargetValue() / 2.0f == j) {
            speakNativeVoice("target_finish_half");
        }
    }

    private void statusCodeVoice(int i) {
        if (i == -5) {
            speakNativeVoice("make_sure_only_one_person_is_in_frame");
        } else {
            if (i != -1) {
                return;
            }
            speakNativeVoice("keep_all_body_in_frame");
        }
    }

    private void resultCodeVoice(int i) {
        if (i != 1 && i != 2 && i != 3) {
            if (i != 4) {
                return;
            }
            speakNativeVoice("count_num_sound_effect");
        } else if (this.mActionType == 1) {
            speakNativeVoice("count_time_sound_effect");
        } else {
            speakNativeVoice("count_num_sound_effect");
        }
    }

    private void countdownVoice(long j) {
        int targetValue = ((int) BaseSportManager.getInstance().getTargetValue()) - Math.round((j * 1.0f) / 1000.0f);
        if (targetValue == 1) {
            speakNativeVoice("countdown1");
            return;
        }
        if (targetValue == 2) {
            speakNativeVoice("countdown2");
            return;
        }
        if (targetValue == 3) {
            speakNativeVoice("countdown3");
        } else if (targetValue == 4) {
            speakNativeVoice("countdown4");
        } else {
            if (targetValue != 6) {
                return;
            }
            speakNativeVoice("countdown5");
        }
    }

    private void speakNativeVoice(String str) {
        String e = gyl.b().e(str, "mp3", this.mLanguage);
        LogUtil.a(TAG, "speakNativeVoice voicePath: ", e);
        gxd.a().b("assert" + e);
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        if (BaseSportManager.getInstance().isToSave()) {
            speakNativeVoice("action_finish");
        }
        gxd.a().f(TAG);
        BaseSportManager.getInstance().cancelSubscribeNotify(this.mSportCallbackOption);
    }

    private void getActionType() {
        AIActionBundle aIActionBundle;
        SportLaunchParams sportLaunchParams = (SportLaunchParams) BaseSportManager.getInstance().getParas(SportParamsType.SPORT_LAUNCH_PARAS);
        if (sportLaunchParams == null || (aIActionBundle = (AIActionBundle) sportLaunchParams.getExtra("AI_ACTION_BUNDLE", AIActionBundle.class)) == null) {
            return;
        }
        this.mActionType = aIActionBundle.getAiMeasurement();
    }
}
