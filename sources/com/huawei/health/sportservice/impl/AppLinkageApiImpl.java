package com.huawei.health.sportservice.impl;

import android.content.Intent;
import android.os.Bundle;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.healthcloud.plugintrack.ui.activity.TrackMainMapActivity;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.linkage.sportlinkage.AppLinkageApi;
import com.huawei.linkage.sportlinkage.LinkagePlatformApi;
import defpackage.gnm;
import defpackage.kwx;
import defpackage.nsn;
import health.compact.a.EnvironmentInfo;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

@ApiDefine(uri = AppLinkageApi.class)
@Singleton
/* loaded from: classes4.dex */
public class AppLinkageApiImpl implements AppLinkageApi {
    private static final String RELEASE_TAG = "R_SportService_AppLinkageApiImpl";
    private static final String TAG = "SportService_AppLinkageApiImpl";

    private boolean isLinkageSupportType(int i) {
        return i == 265 || i == 259;
    }

    @Override // com.huawei.linkage.sportlinkage.LinkageApi
    public void initSport(int i, int i2, int i3) {
        LogUtil.a(TAG, " AppLinkage initSport workoutType ", Integer.valueOf(i), " currentDeviceSportStatus ", Integer.valueOf(i2));
        if (i3 == 101) {
            resumeLinkage(i, i2);
        } else {
            startLinkage(i, i2);
        }
    }

    @Override // com.huawei.linkage.sportlinkage.LinkageApi
    public void startLinkage(int i, int i2) {
        ReleaseLogUtil.e(RELEASE_TAG, "startLinkage sportType ", Integer.valueOf(i), " currentDeviceSportStatus ", Integer.valueOf(i2));
        if (!isLinkageSupportType(i)) {
            ReleaseLogUtil.c(TAG, "startLinkage failed. sportType not support:", Integer.valueOf(i));
            replyDevice(100, "1");
        } else if (nsn.ae(BaseApplication.getContext()) || EnvironmentInfo.k()) {
            ReleaseLogUtil.c(TAG, "startLinkage failed. app client type not support:", Integer.valueOf(i));
            replyDevice(100, "1");
        } else if (kwx.c()) {
            ReleaseLogUtil.d(TAG, "startLinkage failed. APP is Sporting! Can‘t start linkage !");
            replyDevice(100, "1");
        } else {
            initSportService(i, i2);
        }
    }

    @Override // com.huawei.linkage.sportlinkage.AppLinkageApi
    public void resumeLinkage(int i, int i2) {
        ReleaseLogUtil.e(RELEASE_TAG, "resumeLinkage sportType ", Integer.valueOf(i), " deviceSportStatus ", Integer.valueOf(i2));
        if (!isLinkageSupportType(i)) {
            ReleaseLogUtil.c(TAG, "resumeLinkage failed. sportType not support:", Integer.valueOf(i));
            replyDevice(100, "1");
            return;
        }
        if (!BaseSportManager.getInstance().isAlreadyInit()) {
            ReleaseLogUtil.d(TAG, "resumeLinkage failed. SportService not init.");
            replyDevice(101, "1");
            return;
        }
        int sportType = BaseSportManager.getInstance().getSportType();
        if (i != sportType) {
            ReleaseLogUtil.d(TAG, "resumeLinkage failed. SportService type not match. deviceSportType", Integer.valueOf(i), " appSportType:", Integer.valueOf(sportType));
            replyDevice(101, "1");
            return;
        }
        if (BaseSportManager.getInstance().getStatus() != i2) {
            if (i2 == 1) {
                BaseSportManager.getInstance().onResumeSport("DEVICE");
            } else if (i2 == 2) {
                BaseSportManager.getInstance().onPauseSport("DEVICE");
            }
        }
        ReleaseLogUtil.d(TAG, "resumeLinkage success. sportType: ", Integer.valueOf(i), " sportStatus:", Integer.valueOf(i2));
        replyDevice(101, "0");
    }

    @Override // com.huawei.linkage.sportlinkage.AppLinkageApi, com.huawei.linkage.sportlinkage.LinkageApi
    public void stopLinkage() {
        ReleaseLogUtil.e(RELEASE_TAG, "stopLinkage");
        if (!BaseSportManager.getInstance().isAlreadyInit()) {
            ReleaseLogUtil.d(TAG, "stopLinkage failed. SportService not init.");
            replyDevice(102, "1");
        } else {
            BaseSportManager.getInstance().onStopSport("DEVICE");
            replyDevice(102, "0");
        }
    }

    @Override // com.huawei.linkage.sportlinkage.AppLinkageApi, com.huawei.linkage.sportlinkage.LinkageApi
    public void start(int i) {
        ReleaseLogUtil.e(RELEASE_TAG, "starSport sportType", Integer.valueOf(i));
        if (!BaseSportManager.getInstance().isAlreadyInit()) {
            ReleaseLogUtil.d(TAG, "startSport failed. SportService not init.");
            replyDevice(201, "1");
        } else {
            BaseSportManager.getInstance().onStartSport("DEVICE");
            replyDevice(201, "0");
        }
    }

    @Override // com.huawei.linkage.sportlinkage.AppLinkageApi, com.huawei.linkage.sportlinkage.LinkageApi
    public void pause() {
        ReleaseLogUtil.e(RELEASE_TAG, "pauseSport");
        if (!BaseSportManager.getInstance().isAlreadyInit()) {
            ReleaseLogUtil.d(TAG, "pauseSport failed. SportService not init.");
            replyDevice(202, "1");
        } else {
            BaseSportManager.getInstance().onPauseSport("DEVICE");
            replyDevice(202, "0");
        }
    }

    @Override // com.huawei.linkage.sportlinkage.AppLinkageApi, com.huawei.linkage.sportlinkage.LinkageApi
    public void resume() {
        ReleaseLogUtil.e(RELEASE_TAG, "resumeSport");
        if (!BaseSportManager.getInstance().isAlreadyInit()) {
            ReleaseLogUtil.c(TAG, "resumeSport failed. SportService not init.");
            replyDevice(203, "1");
        } else {
            BaseSportManager.getInstance().onResumeSport("DEVICE");
            replyDevice(203, "0");
        }
    }

    @Override // com.huawei.linkage.sportlinkage.AppLinkageApi, com.huawei.linkage.sportlinkage.LinkageApi
    public void stop() {
        ReleaseLogUtil.e(RELEASE_TAG, "stopSport");
        if (!BaseSportManager.getInstance().isAlreadyInit()) {
            ReleaseLogUtil.c(TAG, "stopSport failed. SportService not init.");
            replyDevice(204, "1");
        } else {
            BaseSportManager.getInstance().onStopSport("DEVICE");
            replyDevice(204, "0");
        }
    }

    private void replyDevice(int i, String str) {
        ReleaseLogUtil.e(TAG, "replyDevice replyType:", Integer.valueOf(i), ", replycode:", str);
        LinkagePlatformApi linkagePlatformApi = (LinkagePlatformApi) Services.a("LinkagePlatform", LinkagePlatformApi.class);
        if (linkagePlatformApi == null) {
            ReleaseLogUtil.c(TAG, "deviceCommendReply failed. reverseLinkagePlatformApi == null");
        } else {
            linkagePlatformApi.replyDevice(i, str);
        }
    }

    private void initSportService(int i, int i2) {
        startSportingActivity(getStartSportBundle(i, i2));
    }

    private Bundle getStartSportBundle(int i, int i2) {
        Bundle bundle = new Bundle();
        bundle.putInt("map_tracking_sport_type_sportting", i);
        bundle.putInt("sport_target_type_sportting", -1);
        bundle.putInt("origintarget", -1);
        bundle.putFloat("sport_target_value_sportting", -1.0f);
        bundle.putInt("trackFrom", 100);
        bundle.putInt("initStatus", i2);
        bundle.putInt("sport_data_source_sportting", 100);
        return bundle;
    }

    private void startSportingActivity(Bundle bundle) {
        Intent intent = new Intent(BaseApplication.getContext(), (Class<?>) TrackMainMapActivity.class);
        intent.addFlags(268435456);
        intent.putExtra("sportdataparams", bundle);
        gnm.aPB_(com.huawei.haf.application.BaseApplication.e(), intent);
    }
}
