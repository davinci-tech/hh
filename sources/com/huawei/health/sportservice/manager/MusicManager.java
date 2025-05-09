package com.huawei.health.sportservice.manager;

import android.content.Context;
import android.content.Intent;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.healthcloud.plugintrack.sportmusic.SportMusicController;
import com.huawei.healthcloud.plugintrack.sportmusic.SportMusicInteratorService;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.gut;
import defpackage.gwg;
import defpackage.gww;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;

@SportComponentType(classify = 1, name = ComponentName.MUSIC_MANAGER)
/* loaded from: classes8.dex */
public class MusicManager implements ManagerComponent {
    private static final String TAG = "SportService_MusicManager";
    private Context mContext;
    private int mSportType = 0;
    private gww mTrackSharedPreferenceUtil;

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        LogUtil.a(TAG, "onPreSport()");
        this.mContext = BaseApplication.getContext();
        this.mSportType = BaseSportManager.getInstance().getSportType();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
        LogUtil.a(TAG, "onStartSport()");
        SportMusicInteratorService.e();
        if (BaseSportManager.getInstance().getDataSource() == 100) {
            SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(20002), "map_tracking_sport_type", Integer.toString(this.mSportType), new StorageParams());
            if (isSupportControlMusic()) {
                SportMusicController.a().b(this.mSportType);
            }
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onResumeSport() {
        LogUtil.a(TAG, "onResumeSport()");
        if (isSupportControlMusic()) {
            LogUtil.a(TAG, "onResumeSport() PLAY_MUSIC");
            SportMusicController.a().a(2, (String) null);
        }
    }

    private boolean isSupportControlMusic() {
        int i;
        if (this.mTrackSharedPreferenceUtil == null) {
            this.mTrackSharedPreferenceUtil = new gww(this.mContext, new StorageParams(), Integer.toString(20002));
        }
        return CommonUtil.bd() && gwg.a(this.mContext) && this.mTrackSharedPreferenceUtil.f(this.mSportType) == 1 && ((i = this.mSportType) == 264 || i == 283 || i == 259 || i == 265);
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPauseSport() {
        LogUtil.a(TAG, "onPauseSport()");
        if (isSupportControlMusic()) {
            LogUtil.a(TAG, "onPauseSport() PAUSE_MUSIC");
            SportMusicController.a().a(1, (String) null);
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        LogUtil.a(TAG, "onStopSport()");
        if (isSupportControlMusic()) {
            LogUtil.a(TAG, "onStopSport() PAUSE_MUSIC");
            SportMusicController.a().a(1, (String) null);
        }
        if (gwg.i(this.mContext)) {
            Intent intent = new Intent("action_stop_play_sport_music");
            intent.putExtra("stepRate", 0);
            gut.aUo_(this.mContext, intent);
        }
    }
}
