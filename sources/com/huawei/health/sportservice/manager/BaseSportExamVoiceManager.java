package com.huawei.health.sportservice.manager;

import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.healthcloud.plugintrack.manager.voice.BaseSoundEffectManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.fgm;
import defpackage.gxc;
import defpackage.gxd;
import defpackage.gyr;
import defpackage.koq;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SportComponentType(classify = 1, name = ComponentName.VOICE_MANAGER)
/* loaded from: classes8.dex */
public abstract class BaseSportExamVoiceManager implements ManagerComponent {
    public static final String SOUND_SUCCESS_ACTION = "SOUND_SUCCESS_ACTION";
    private static final String TAG = "SportService_BaseSportExamVoiceManager";
    private int mPreStartSportLastCode;
    protected BaseSoundEffectManager mSoundEffectManager;
    private fgm mSportCallbackOption = new fgm();

    public abstract String getLogTag();

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public void init() {
        gxd.a().h(getLogTag());
        this.mSoundEffectManager = new BaseSoundEffectManager(BaseApplication.getContext());
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("STATUS_CODE_DATA");
        arrayList.add("TIME_ONE_SECOND_DURATION");
        this.mSportCallbackOption.a(arrayList);
        this.mSportCallbackOption.c(TAG);
        BaseSportManager.getInstance().subscribeNotify(this.mSportCallbackOption, new SportDataNotify() { // from class: com.huawei.health.sportservice.manager.BaseSportExamVoiceManager$$ExternalSyntheticLambda0
            @Override // com.huawei.health.sportservice.SportDataNotify
            public final void onChange(List list, Map map) {
                BaseSportExamVoiceManager.this.m460x7446e933(list, map);
            }
        });
    }

    /* renamed from: lambda$onPreSport$0$com-huawei-health-sportservice-manager-BaseSportExamVoiceManager, reason: not valid java name */
    /* synthetic */ void m460x7446e933(List list, Map map) {
        if (koq.b(list) || map == null) {
            LogUtil.h(getLogTag(), "SportDataNotify failed");
            return;
        }
        if (list.contains("STATUS_CODE_DATA")) {
            Object obj = map.get("STATUS_CODE_DATA");
            if (obj instanceof Integer) {
                int intValue = ((Integer) obj).intValue();
                if (this.mPreStartSportLastCode != intValue) {
                    preSportVoice(intValue);
                }
                this.mPreStartSportLastCode = intValue;
            }
        }
    }

    private void preSportVoice(int i) {
        if (i == -11) {
            speakNativeVoice("keep_one_person");
        } else {
            if (i != -10) {
                return;
            }
            speakNativeVoice("keep_light");
        }
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public void destroy() {
        gxd.a().f(getLogTag());
        BaseSportManager.getInstance().cancelSubscribeNotify(this.mSportCallbackOption);
    }

    public void speakNativeVoice(String str) {
        LogUtil.a(getLogTag(), "speakNativeVoice() voice: ", str);
        String a2 = gyr.e().a(str, "mp3", "zh-CN");
        gxd.a().b("assert" + a2);
    }

    public void loadDownSound() {
        gxc gxcVar = new gxc(SOUND_SUCCESS_ACTION, 0, gyr.e().a("S001", "ogg", "zh-CN"));
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(gxcVar);
        BaseSoundEffectManager baseSoundEffectManager = this.mSoundEffectManager;
        if (baseSoundEffectManager != null) {
            baseSoundEffectManager.a(arrayList);
        }
    }
}
