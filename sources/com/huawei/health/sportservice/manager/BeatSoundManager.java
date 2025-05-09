package com.huawei.health.sportservice.manager;

import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.constants.SportParamsType;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.healthcloud.plugintrack.model.SportBeat;
import defpackage.hpg;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

@SportComponentType(classify = 1, name = ComponentName.BEAT_SOUND_MANAGER)
/* loaded from: classes8.dex */
public class BeatSoundManager implements ManagerComponent, SportLifecycle {
    private static final String TAG = "SportService_BeatSoundManager";
    private hpg sportBeatPlayer;

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public void setParas(SportParamsType sportParamsType, Object obj) {
        if (SportParamsType.SPORT_BEAT_CHANGE.equals(sportParamsType) && (obj instanceof SportBeat)) {
            SportBeat sportBeat = (SportBeat) obj;
            ReleaseLogUtil.e(TAG, sportBeat);
            if (this.sportBeatPlayer == null) {
                this.sportBeatPlayer = new hpg(sportBeat);
                if (BaseSportManager.getInstance().getStatus() == 1 && sportBeat.isOpen()) {
                    this.sportBeatPlayer.j();
                    return;
                }
                return;
            }
            if (sportBeat.getPlayStatus() == 2) {
                this.sportBeatPlayer.h();
                return;
            }
            if (sportBeat.getPlayStatus() == 1) {
                this.sportBeatPlayer.a(sportBeat);
                return;
            }
            this.sportBeatPlayer.h();
            this.sportBeatPlayer.a(sportBeat);
            if (sportBeat.isOpen()) {
                this.sportBeatPlayer.j();
            }
        }
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public boolean supportParas(SportParamsType sportParamsType) {
        return SportParamsType.SPORT_BEAT_CHANGE.equals(sportParamsType);
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
        hpg hpgVar = this.sportBeatPlayer;
        if (hpgVar == null || !hpgVar.e()) {
            return;
        }
        this.sportBeatPlayer.j();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPauseSport() {
        hpg hpgVar = this.sportBeatPlayer;
        if (hpgVar != null) {
            hpgVar.h();
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onResumeSport() {
        hpg hpgVar = this.sportBeatPlayer;
        if (hpgVar == null || !hpgVar.e()) {
            return;
        }
        this.sportBeatPlayer.f();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStopSport() {
        hpg hpgVar = this.sportBeatPlayer;
        if (hpgVar != null) {
            hpgVar.i();
        }
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public void destroy() {
        hpg hpgVar = this.sportBeatPlayer;
        if (hpgVar != null) {
            hpgVar.d();
        }
    }
}
