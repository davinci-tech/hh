package com.huawei.health.sportservice.manager;

import android.content.Context;
import android.os.Bundle;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.utils.ScreenUtil;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.SportLaunchParams;
import com.huawei.health.sportservice.constants.SportParamsType;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.healthcloud.plugintrack.notification.HealthSportingNotificationHelper;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import defpackage.fgm;
import defpackage.fhs;
import defpackage.gvv;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SportComponentType(classify = 1, name = ComponentName.NOTIFICATION_MANAGER)
/* loaded from: classes8.dex */
public class NotificationManager implements ManagerComponent {
    public static final float SPEED_HUNDRED_TRANSFER = 100.0f;
    private static final String TAG = "SportService_NotificationManager";
    public static final float TOTAL_DISTANCE_KILO_TRANSFER = 1000.0f;
    private static final int TRANSFER = 3600;
    private Context mContext;
    private int mSportType = 0;
    private HealthSportingNotificationHelper mSportingNotification = null;
    private fgm mSportCallbackOption = new fgm();

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        LogUtil.a(TAG, "onPreSport()");
        this.mContext = BaseApplication.e();
        this.mSportType = BaseSportManager.getInstance().getSportType();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
        LogUtil.a(TAG, "onStartSport()");
        HealthSportingNotificationHelper healthSportingNotificationHelper = new HealthSportingNotificationHelper(this.mContext, this.mSportType, true, true, true);
        this.mSportingNotification = healthSportingNotificationHelper;
        healthSportingNotificationHelper.a(BaseSportManager.getInstance().getDataSource() == 100);
        this.mSportingNotification.d(BaseSportManager.getInstance().getDataSource() == 100);
        ArrayList arrayList = new ArrayList();
        arrayList.add("TIME_ONE_SECOND_DURATION");
        arrayList.add("UPDATE_VIEW_SIGNAL");
        arrayList.add("DISTANCE_DATA");
        arrayList.add("SPEED_DATA");
        arrayList.add("PACE_DATA");
        arrayList.add("HEART_RATE_DATA");
        arrayList.add("CALORIES_DATA");
        if (isSkip()) {
            arrayList.add("SKIP_NUM_DATA");
            arrayList.add("SKIPPING_AVG_SPEED_DATA");
        }
        arrayList.add("PADDLE_DATA");
        arrayList.add("WEIGHT_DATA");
        this.mSportCallbackOption.a(arrayList);
        this.mSportCallbackOption.c(TAG);
        BaseSportManager.getInstance().subscribeNotify(this.mSportCallbackOption, getSportDataNotify());
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public void setParas(SportParamsType sportParamsType, Object obj) {
        if (SportParamsType.SPORT_LAUNCH_PARAS.equals(sportParamsType) && (obj instanceof SportLaunchParams) && this.mSportingNotification != null && ScreenUtil.a()) {
            LogUtil.a(TAG, "SPORT_LAUNCH_PARAS change, updateCurrentNotification");
            this.mSportingNotification.g();
        }
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public boolean supportParas(SportParamsType sportParamsType) {
        return SportParamsType.SPORT_LAUNCH_PARAS.equals(sportParamsType);
    }

    private SportDataNotify getSportDataNotify() {
        return new SportDataNotify() { // from class: com.huawei.health.sportservice.manager.NotificationManager$$ExternalSyntheticLambda0
            @Override // com.huawei.health.sportservice.SportDataNotify
            public final void onChange(List list, Map map) {
                NotificationManager.this.m467xd13fc717(list, map);
            }
        };
    }

    /* renamed from: lambda$getSportDataNotify$0$com-huawei-health-sportservice-manager-NotificationManager, reason: not valid java name */
    /* synthetic */ void m467xd13fc717(List list, Map map) {
        String valueOf;
        if (list.contains("TIME_ONE_SECOND_DURATION") || list.contains("UPDATE_VIEW_SIGNAL")) {
            LogUtil.a(TAG, "tagList: ", list, ", sportDataMap: ", map);
            long longValue = ((Long) map.get("TIME_ONE_SECOND_DURATION")).longValue();
            int intValue = ((Integer) map.get("DISTANCE_DATA")).intValue();
            int intValue2 = ((Integer) map.get("SPEED_DATA")).intValue();
            int intValue3 = ((Integer) map.get("PACE_DATA")).intValue();
            int intValue4 = ((Integer) map.get("HEART_RATE_DATA")).intValue();
            int intValue5 = ((Integer) map.get("CALORIES_DATA")).intValue();
            Bundle bundle = new Bundle();
            bundle.putString("duration", String.valueOf(UnitUtil.d(((int) longValue) / 1000)));
            bundle.putString("distance", getTotalDistance(intValue));
            bundle.putString("speed", getSpeedForNotify(intValue2));
            bundle.putString("pace", getPaceForNotify(intValue3, intValue2));
            bundle.putInt(BleConstants.SPORT_TYPE, this.mSportType);
            bundle.putString(IndoorEquipManagerApi.KEY_HEART_RATE, getHeartRate(intValue4));
            bundle.putString("calorie", String.valueOf(intValue5));
            if (isSkip()) {
                int intValue6 = ((Integer) map.get("SKIP_NUM_DATA")).intValue();
                int intValue7 = ((Integer) map.get("SKIPPING_AVG_SPEED_DATA")).intValue();
                if (intValue7 == -1) {
                    valueOf = gvv.e(com.huawei.hwcommonmodel.application.BaseApplication.getContext());
                } else {
                    valueOf = String.valueOf(intValue7);
                }
                bundle.putString("skippingSpeed", valueOf);
                bundle.putString("skippingNum", String.valueOf(intValue6));
            }
            if (BaseSportManager.getInstance().getSportMode().equals("291")) {
                bundle.putInt("rowerWeight", getIntegerValue(map.get("WEIGHT_DATA")));
                bundle.putInt("rowerTimes", getIntegerValue(map.get("PADDLE_DATA")));
                bundle.putBoolean("rowerStrength", true);
            }
            if (this.mSportingNotification == null || !ScreenUtil.a()) {
                return;
            }
            LogUtil.a(TAG, "mSportingNotification.updateSportViewFragment()");
            this.mSportingNotification.updateSportViewFragment(bundle);
        }
    }

    private String getHeartRate(int i) {
        return fhs.c(i) ? String.valueOf(i) : "";
    }

    private int getIntegerValue(Object obj) {
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    private String getTotalDistance(int i) {
        if (UnitUtil.h()) {
            return String.valueOf(UnitUtil.e(i / 1000.0f, 3));
        }
        return String.valueOf(i / 1000.0f);
    }

    private String getSpeedForNotify(int i) {
        int i2 = BaseSportManager.getInstance().getDataSource() == 100 ? 2 : 1;
        if (UnitUtil.h()) {
            return UnitUtil.e(UnitUtil.e(i / 100.0f, 3), 1, i2);
        }
        return UnitUtil.e(i / 100.0f, 1, i2);
    }

    private String getPaceForNotify(int i, int i2) {
        if (this.mSportType == 274) {
            return String.valueOf(i);
        }
        if (i2 != 0) {
            if (UnitUtil.h()) {
                return String.valueOf(3600.0d / UnitUtil.e(i2 / 100.0f, 3));
            }
            return String.valueOf(3600.0f / (i2 / 100.0f));
        }
        return String.valueOf(0);
    }

    private boolean isSkip() {
        return this.mSportType == 283;
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        LogUtil.a(TAG, "onStopSport()");
        if (this.mSportingNotification != null) {
            LogUtil.a(TAG, "onStopSport() mSportingNotification.closeNotification()");
            this.mSportingNotification.d();
            this.mSportingNotification = null;
        }
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public void destroy() {
        LogUtil.a(TAG, "destroy()");
        if (this.mSportingNotification != null) {
            LogUtil.a(TAG, "destroy() mSportingNotification.closeNotification()");
            this.mSportingNotification.d();
            this.mSportingNotification = null;
        }
        BaseSportManager.getInstance().cancelSubscribeNotify(this.mSportCallbackOption);
    }
}
