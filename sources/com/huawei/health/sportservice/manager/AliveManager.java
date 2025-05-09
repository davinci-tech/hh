package com.huawei.health.sportservice.manager;

import android.content.Context;
import android.content.Intent;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.SportLaunchParams;
import com.huawei.health.sportservice.constants.SportParamsType;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.healthcloud.plugintrack.service.KeepForegroundNoLocationService;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import defpackage.cag;
import defpackage.cas;
import defpackage.fgm;
import defpackage.gsx;
import health.compact.a.CommonUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SportComponentType(classify = 1, name = ComponentName.ALIVE_MANAGER)
/* loaded from: classes8.dex */
public class AliveManager implements ManagerComponent {
    private static final String TAG = "SportService_AliveManager";
    private Context mContext;
    private cag mHelper;
    private SportLaunchParams mSportLaunchParams;
    private gsx mTrackAlive;
    private boolean mIsSportStarted = false;
    private boolean mIsCancel = false;
    private fgm mSportCallbackOption = new fgm();

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onCountDown() {
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public void init() {
        this.mContext = BaseApplication.e();
        this.mHelper = new cag(BaseSportManager.getInstance().getSportType());
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public void setParas(SportParamsType sportParamsType, Object obj) {
        if (obj instanceof SportLaunchParams) {
            LogUtil.a(TAG, "setParas paramsType:", sportParamsType, " paraData:", obj);
            SportLaunchParams sportLaunchParams = (SportLaunchParams) obj;
            this.mSportLaunchParams = sportLaunchParams;
            if (this.mIsSportStarted && sportLaunchParams.isNeedRestart()) {
                cas.b(this.mSportLaunchParams);
                registerTime();
            }
        }
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public boolean supportParas(SportParamsType sportParamsType) {
        return SportParamsType.SPORT_LAUNCH_PARAS.equals(sportParamsType);
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        this.mTrackAlive = gsx.a();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
        apply();
        dealStartFlag(true);
        this.mIsSportStarted = true;
        gsx gsxVar = this.mTrackAlive;
        if (gsxVar != null) {
            gsxVar.e(this.mContext);
        }
        SportLaunchParams sportLaunchParams = this.mSportLaunchParams;
        if (sportLaunchParams != null && sportLaunchParams.isNeedRestart()) {
            cas.b(this.mSportLaunchParams);
            registerTime();
        }
        HandlerExecutor.e(new Runnable() { // from class: com.huawei.health.sportservice.manager.AliveManager$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                AliveManager.this.m458x4c708e2b();
            }
        });
    }

    /* renamed from: lambda$onStartSport$0$com-huawei-health-sportservice-manager-AliveManager, reason: not valid java name */
    /* synthetic */ void m458x4c708e2b() {
        handleKeepForeground(false);
    }

    private void handleKeepForeground(boolean z) {
        if (!CommonUtil.bk()) {
            LogUtil.b(TAG, "no need to startKeepForegroundService");
            return;
        }
        if (this.mContext == null) {
            LogUtil.b(TAG, "startKeepForegroundService failed. context is null");
            return;
        }
        try {
            Intent intent = new Intent();
            intent.setClass(this.mContext, KeepForegroundNoLocationService.class);
            intent.putExtra(BleConstants.SPORT_TYPE, BaseSportManager.getInstance().getSportType());
            intent.putExtra("isStop", z);
            intent.putExtra("id", TAG);
            intent.putExtra("stringKey", R.string._2130843715_res_0x7f021843);
            intent.putExtra("isFromTreadmill", true);
            if (BaseSportManager.getInstance().getDataSource() == 100) {
                intent.putExtra("isNeedControlAction", true);
                intent.putExtra("isSportFromWear", true);
            }
            intent.putExtra("NOTIFICATION_TYPE", 1);
            this.mContext.startForegroundService(intent);
            LogUtil.a(TAG, "startForegroundService");
        } catch (Throwable th) {
            ReleaseLogUtil.c(TAG, ExceptionUtils.d(th));
        }
    }

    private void registerTime() {
        SportDataNotify sportDataNotify = new SportDataNotify() { // from class: com.huawei.health.sportservice.manager.AliveManager$$ExternalSyntheticLambda0
            @Override // com.huawei.health.sportservice.SportDataNotify
            public final void onChange(List list, Map map) {
                AliveManager.this.m459x949adf0b(list, map);
            }
        };
        ArrayList arrayList = new ArrayList();
        arrayList.add("TIME_ONE_SECOND_DURATION");
        this.mSportCallbackOption.a(arrayList);
        this.mSportCallbackOption.c(ComponentName.ALIVE_MANAGER);
        BaseSportManager.getInstance().subscribeNotify(this.mSportCallbackOption, sportDataNotify);
    }

    /* renamed from: lambda$registerTime$1$com-huawei-health-sportservice-manager-AliveManager, reason: not valid java name */
    /* synthetic */ void m459x949adf0b(List list, Map map) {
        if (list.contains("TIME_ONE_SECOND_DURATION") && (map.get("TIME_ONE_SECOND_DURATION") instanceof Long)) {
            gsx gsxVar = this.mTrackAlive;
            if (gsxVar != null) {
                gsxVar.b();
            }
            apply();
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onResumeSport() {
        gsx gsxVar = this.mTrackAlive;
        if (gsxVar != null) {
            gsxVar.e(this.mContext);
        }
        apply();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPauseSport() {
        gsx gsxVar = this.mTrackAlive;
        if (gsxVar != null) {
            gsxVar.c(this.mContext);
        }
        unapply();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        gsx gsxVar = this.mTrackAlive;
        if (gsxVar != null) {
            gsxVar.c(this.mContext);
        }
        this.mIsSportStarted = false;
        handleKeepForeground(true);
        this.mIsCancel = true;
        cas.b();
        dealStartFlag(false);
        unapply();
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public void destroy() {
        gsx gsxVar = this.mTrackAlive;
        if (gsxVar != null && gsxVar.e()) {
            this.mTrackAlive.d(false);
            gsx.b(this.mContext);
        }
        if (!this.mIsCancel) {
            handleKeepForeground(true);
        }
        BaseSportManager.getInstance().cancelSubscribeNotify(this.mSportCallbackOption);
        dealStartFlag(false);
    }

    private void dealStartFlag(boolean z) {
        KeyValDbManager.b(BaseApplication.e()).e("already_sport", String.valueOf(z));
    }

    private void apply() {
        if (this.mHelper == null) {
            this.mHelper = new cag(BaseSportManager.getInstance().getSportType());
        }
        this.mHelper.a();
    }

    private void unapply() {
        if (this.mHelper == null) {
            this.mHelper = new cag(BaseSportManager.getInstance().getSportType());
        }
        this.mHelper.e();
    }
}
