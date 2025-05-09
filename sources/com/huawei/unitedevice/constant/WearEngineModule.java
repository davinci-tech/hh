package com.huawei.unitedevice.constant;

import com.google.android.gms.fitness.FitnessActivities;
import com.huawei.maps.offlinedata.health.p2p.OfflineMapWearEngineManager;
import com.huawei.openalliance.ad.constant.Constants;

/* loaded from: classes7.dex */
public enum WearEngineModule {
    FASTING_LITE_MODULE("fastinglite"),
    MEDAL_MODULE("medal"),
    MEDAL_WORKOUT_MODULE("medalworkout"),
    HEALTH_MODEL_MODULE("healthmodel"),
    EXCELLENT_APP_MODULE("excellentapp"),
    DATA_DICTIONARY_SYNC_MODULE("datadictionarysync"),
    BTPROXY_MODULE("btproxy"),
    INTELLIGENT_MODULE("intelligentsleep"),
    FITNESS_MODULE("fitness"),
    BIND_ALIPAY_MODULE("bindalipay"),
    BLOOD_SUGAR_MODULE("bloodsugar"),
    BLOOD_PRESSURE_MODULE("bloodpressure"),
    ECG_REMINDER_MODULE("ecgrecorder"),
    MOTION_TRACK_MODULE("track"),
    HEALTH_ZONE_MODULE("healthzone"),
    MARKET_MODULE(Constants.SCHEME_MARKET),
    WIFIP2P_TRANSFER_MODULE("wifip2ptransfer"),
    OTA_TRANSFER_MODULE("ota"),
    LOGIN_AUTHORZE_MODULE("loginauthorize"),
    HIGH_LAND_MODULE("highlandbloodoxygen"),
    NOTIFICATION_PUSH_APP_MODULE("notificationpushapp"),
    SMS_EDITABLE_APP_MODULE("smsquick"),
    GOLF_MODULE(FitnessActivities.GOLF),
    OFFLINE_MAP_MODULE(OfflineMapWearEngineManager.OFFLINE_MAP_MODULE),
    WATCH_MODULE("ecologydevicediscoveryfree"),
    FEATURE_SOURCE_MODULE("featuresource"),
    CALENDAR_MODULE("calendarapp"),
    SCREENSHOT_MODULE("screenshot"),
    HTTPPROXY_MODULE("httpproxy"),
    STICKER_MODULE("watchface");

    private final String mValue;

    WearEngineModule(String str) {
        this.mValue = str;
    }

    public String getValue() {
        return this.mValue;
    }
}
