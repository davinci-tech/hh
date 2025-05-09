package com.huawei.hms.hihealth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.huawei.hms.api.Api;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.health.aacd;
import com.huawei.hms.health.aaci;
import com.huawei.hms.hihealth.data.Scopes;
import com.huawei.hms.support.api.entity.auth.Scope;
import com.huawei.hms.support.hwid.result.AuthHuaweiId;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class HuaweiHiHealth {
    public static final String ACTION_TRACK = "vnd.huawei.hihealth.TRACK";
    public static final String ACTION_VIEW = "vnd.huawei.hihealth.VIEW";
    public static final String ACTION_VIEW_GOAL = "vnd.huawei.hihealth.VIEW_GOAL";
    public static final String EXTRA_END_TIME = "vnd.huawei.hihealth.end_time";
    public static final String EXTRA_START_TIME = "vnd.huawei.hihealth.start_time";
    public static final Api<Api.ApiOptions.NoOptions> DATA_MANAGER = aacd.aab;
    public static final Api<Api.ApiOptions.NoOptions> AUTORECORDER_MANAGER = aacd.aabb;
    public static final Api<Api.ApiOptions.NoOptions> ACTIVITY_RECORD_MANAGER = aacd.aaba;
    public static final Api<Api.ApiOptions.NoOptions> SETTING_MANAGER = aacd.aabc;
    public static final Api<Api.ApiOptions.NoOptions> BLE_MANAGER = aacd.aabd;
    public static final Api<Api.ApiOptions.NoOptions> SENSORS_MANAGER = aacd.aabe;
    public static final Scope SCOPE_HEALTHKIT_HEIGHTWEIGHT_READ = new Scope(Scopes.HEALTHKIT_HEIGHTWEIGHT_READ);
    public static final Scope SCOPE_HEALTHKIT_HEIGHTWEIGHT_WRITE = new Scope(Scopes.HEALTHKIT_HEIGHTWEIGHT_WRITE);
    public static final Scope SCOPE_HEALTHKIT_HEIGHTWEIGHT_BOTH = new Scope("https://www.huawei.com/healthkit/heightweight.both");
    public static final Scope SCOPE_HEALTHKIT_STEP_READ = new Scope(Scopes.HEALTHKIT_STEP_READ);
    public static final Scope SCOPE_HEALTHKIT_STEP_WRITE = new Scope(Scopes.HEALTHKIT_STEP_WRITE);
    public static final Scope SCOPE_HEALTHKIT_STEP_BOTH = new Scope("https://www.huawei.com/healthkit/step.both");
    public static final Scope SCOPE_HEALTHKIT_LOCATION_READ = new Scope(Scopes.HEALTHKIT_LOCATION_READ);
    public static final Scope SCOPE_HEALTHKIT_LOCATION_WRITE = new Scope(Scopes.HEALTHKIT_LOCATION_WRITE);
    public static final Scope SCOPE_HEALTHKIT_LOCATION_BOTH = new Scope(Scopes.HEALTHKIT_LOCATION_BOTH);
    public static final Scope SCOPE_HEALTHKIT_HEARTRATE_READ = new Scope(Scopes.HEALTHKIT_HEARTRATE_READ);
    public static final Scope SCOPE_HEALTHKIT_HEARTRATE_WRITE = new Scope(Scopes.HEALTHKIT_HEARTRATE_WRITE);
    public static final Scope SCOPE_HEALTHKIT_HEARTRATE_BOTH = new Scope(Scopes.HEALTHKIT_HEARTRATE_BOTH);
    public static final Scope SCOPE_HEALTHKIT_BLOODGLUCOSE_READ = new Scope(Scopes.HEALTHKIT_BLOODGLUCOSE_READ);
    public static final Scope SCOPE_HEALTHKIT_BLOODGLUCOSE_WRITE = new Scope(Scopes.HEALTHKIT_BLOODGLUCOSE_WRITE);
    public static final Scope SCOPE_HEALTHKIT_BLOODGLUCOSE_BOTH = new Scope("https://www.huawei.com/healthkit/bloodglucose.both");
    public static final Scope SCOPE_HEALTHKIT_DISTANCE_READ = new Scope(Scopes.HEALTHKIT_DISTANCE_READ);
    public static final Scope SCOPE_HEALTHKIT_DISTANCE_WRITE = new Scope(Scopes.HEALTHKIT_DISTANCE_WRITE);
    public static final Scope SCOPE_HEALTHKIT_DISTANCE_BOTH = new Scope(Scopes.HEALTHKIT_DISTANCE_BOTH);
    public static final Scope SCOPE_HEALTHKIT_SPEED_READ = new Scope(Scopes.HEALTHKIT_SPEED_READ);
    public static final Scope SCOPE_HEALTHKIT_SPEED_WRITE = new Scope(Scopes.HEALTHKIT_SPEED_WRITE);
    public static final Scope SCOPE_HEALTHKIT_SPEED_BOTH = new Scope(Scopes.HEALTHKIT_SPEED_BOTH);
    public static final Scope SCOPE_HEALTHKIT_CALORIES_READ = new Scope(Scopes.HEALTHKIT_CALORIES_READ);
    public static final Scope SCOPE_HEALTHKIT_CALORIES_WRITE = new Scope(Scopes.HEALTHKIT_CALORIES_WRITE);
    public static final Scope SCOPE_HEALTHKIT_CALORIES_BOTH = new Scope(Scopes.HEALTHKIT_CALORIES_BOTH);
    public static final Scope SCOPE_HEALTHKIT_PULMONARY_READ = new Scope(Scopes.HEALTHKIT_PULMONARY_READ);
    public static final Scope SCOPE_HEALTHKIT_PULMONARY_WRITE = new Scope(Scopes.HEALTHKIT_PULMONARY_WRITE);
    public static final Scope SCOPE_HEALTHKIT_PULMONARY_BOTH = new Scope(Scopes.HEALTHKIT_PULMONARY_BOTH);
    public static final Scope SCOPE_HEALTHKIT_STRENGTH_READ = new Scope(Scopes.HEALTHKIT_STRENGTH_READ);
    public static final Scope SCOPE_HEALTHKIT_STRENGTH_WRITE = new Scope(Scopes.HEALTHKIT_STRENGTH_WRITE);
    public static final Scope SCOPE_HEALTHKIT_STRENGTH_BOTH = new Scope(Scopes.HEALTHKIT_STRENGTH_BOTH);
    public static final Scope SCOPE_HEALTHKIT_ACTIVITY_READ = new Scope(Scopes.HEALTHKIT_ACTIVITY_READ);
    public static final Scope SCOPE_HEALTHKIT_ACTIVITY_WRITE = new Scope(Scopes.HEALTHKIT_ACTIVITY_WRITE);
    public static final Scope SCOPE_HEALTHKIT_ACTIVITY_BOTH = new Scope("https://www.huawei.com/healthkit/activity.both");
    public static final Scope SCOPE_HEALTHKIT_BODYFAT_READ = new Scope(Scopes.HEALTHKIT_BODYFAT_READ);
    public static final Scope SCOPE_HEALTHKIT_BODYFAT_WRITE = new Scope(Scopes.HEALTHKIT_BODYFAT_WRITE);
    public static final Scope SCOPE_HEALTHKIT_BODYFAT_BOTH = new Scope(Scopes.HEALTHKIT_BODYFAT_BOTH);
    public static final Scope SCOPE_HEALTHKIT_SLEEP_READ = new Scope(Scopes.HEALTHKIT_SLEEP_READ);
    public static final Scope SCOPE_HEALTHKIT_SLEEP_WRITE = new Scope(Scopes.HEALTHKIT_SLEEP_WRITE);
    public static final Scope SCOPE_HEALTHKIT_SLEEP_BOTH = new Scope("https://www.huawei.com/healthkit/sleep.both");
    public static final Scope SCOPE_HEALTHKIT_NUTRITION_READ = new Scope(Scopes.HEALTHKIT_NUTRITION_READ);
    public static final Scope SCOPE_HEALTHKIT_NUTRITION_WRITE = new Scope(Scopes.HEALTHKIT_NUTRITION_WRITE);
    public static final Scope SCOPE_HEALTHKIT_NUTRITION_BOTH = new Scope(Scopes.HEALTHKIT_NUTRITION_BOTH);
    public static final Scope SCOPE_HEALTHKIT_BLOODPRESSURE_READ = new Scope(Scopes.HEALTHKIT_BLOODPRESSURE_READ);
    public static final Scope SCOPE_HEALTHKIT_BLOODPRESSURE_WRITE = new Scope(Scopes.HEALTHKIT_BLOODPRESSURE_WRITE);
    public static final Scope SCOPE_HEALTHKIT_BLOODPRESSURE_BOTH = new Scope("https://www.huawei.com/healthkit/bloodpressure.both");

    @Deprecated
    public static final Scope SCOPE_HEALTHKIT_OXYGENSTATURATION_READ = new Scope("https://www.huawei.com/healthkit/oxygensaturation.read");

    @Deprecated
    public static final Scope SCOPE_HEALTHKIT_OXYGENSTATURATION_WRITE = new Scope("https://www.huawei.com/healthkit/oxygensaturation.write");

    @Deprecated
    public static final Scope SCOPE_HEALTHKIT_OXYGENSTATURATION_BOTH = new Scope("https://www.huawei.com/healthkit/oxygensaturation.both");
    public static final Scope SCOPE_HEALTHKIT_OXYGEN_SATURATION_READ = new Scope("https://www.huawei.com/healthkit/oxygensaturation.read");
    public static final Scope SCOPE_HEALTHKIT_OXYGEN_SATURATION_WRITE = new Scope("https://www.huawei.com/healthkit/oxygensaturation.write");
    public static final Scope SCOPE_HEALTHKIT_OXYGEN_SATURATION_BOTH = new Scope("https://www.huawei.com/healthkit/oxygensaturation.both");
    public static final Scope SCOPE_HEALTHKIT_BODYTEMPERATURE_READ = new Scope(Scopes.HEALTHKIT_BODYTEMPERATURE_READ);
    public static final Scope SCOPE_HEALTHKIT_BODYTEMPERATURE_WRITE = new Scope(Scopes.HEALTHKIT_BODYTEMPERATURE_WRITE);
    public static final Scope SCOPE_HEALTHKIT_BODYTEMPERATURE_BOTH = new Scope(Scopes.HEALTHKIT_BODYTEMPERATURE_BOTH);
    public static final Scope SCOPE_HEALTHKIT_REPRODUCTIVE_READ = new Scope(Scopes.HEALTHKIT_REPRODUCTIVE_READ);
    public static final Scope SCOPE_HEALTHKIT_REPRODUCTIVE_WRITE = new Scope(Scopes.HEALTHKIT_REPRODUCTIVE_WRITE);
    public static final Scope SCOPE_HEALTHKIT_REPRODUCTIVE_BOTH = new Scope(Scopes.HEALTHKIT_REPRODUCTIVE_BOTH);
    public static final Scope SCOPE_HEALTHKIT_ACTIVITY_RECORD_READ = new Scope(Scopes.HEALTHKIT_ACTIVITY_RECORD_READ);
    public static final Scope SCOPE_HEALTHKIT_ACTIVITY_RECORD_WRITE = new Scope(Scopes.HEALTHKIT_ACTIVITY_RECORD_WRITE);
    public static final Scope SCOPE_HEALTHKIT_ACTIVITY_RECORD_BOTH = new Scope(Scopes.HEALTHKIT_ACTIVITY_RECORD_WRITE);
    public static final Scope SCOPE_HEALTHKIT_STRESS_READ = new Scope(Scopes.HEALTHKIT_STRESS_READ);
    public static final Scope SCOPE_HEALTHKIT_STRESS_WRITE = new Scope(Scopes.HEALTHKIT_STRESS_WRITE);
    public static final Scope SCOPE_HEALTHKIT_STRESS_BOTH = new Scope(Scopes.HEALTHKIT_STRESS_BOTH);
    public static final Scope SCOPE_HEALTHKIT_HEARTHEALTH_READ = new Scope(Scopes.HEALTHKIT_HEARTHEALTH_READ);
    public static final Scope SCOPE_HEALTHKIT_HEARTHEALTH_WRITE = new Scope(Scopes.HEALTHKIT_HEARTHEALTH_WRITE);
    public static final Scope SCOPE_HEALTHKIT_HEARTHEALTH_BOTH = new Scope(Scopes.HEALTHKIT_HEARTHEALTH_BOTH);
    public static final Scope SCOPE_HEALTHKIT_HUAWEIHEALTH_LINK = new Scope(Scopes.HEALTHKIT_HUAWEIHEALTH_LINK);

    @Deprecated
    public static long getStartTime(Intent intent, TimeUnit timeUnit) {
        return aab(intent, timeUnit, EXTRA_START_TIME);
    }

    @Deprecated
    public static SettingController getSettingController(Context context, AuthHuaweiId authHuaweiId) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(authHuaweiId);
        HiHealthKitClient.getInstance().setContext(context);
        return new SettingController(authHuaweiId);
    }

    public static SettingController getSettingController(Context context) {
        Preconditions.checkNotNull(context);
        HiHealthKitClient.getInstance().setContext(context);
        return new SettingController();
    }

    @Deprecated
    public static SettingController getSettingController(Activity activity, AuthHuaweiId authHuaweiId) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(authHuaweiId);
        HiHealthKitClient.getInstance().setContext(activity.getApplicationContext());
        return new SettingController(authHuaweiId);
    }

    public static SettingController getSettingController(Activity activity) {
        Preconditions.checkNotNull(activity);
        HiHealthKitClient.getInstance().setContext(activity.getApplicationContext());
        return new SettingController();
    }

    public static HealthRecordController getHealthRecordController(Context context) {
        Preconditions.checkNotNull(context);
        HiHealthKitClient.getInstance().setContext(context);
        return new HealthRecordController();
    }

    public static HealthRecordController getHealthRecordController(Activity activity) {
        Preconditions.checkNotNull(activity);
        HiHealthKitClient.getInstance().setContext(activity.getApplicationContext());
        return new HealthRecordController();
    }

    @Deprecated
    public static long getEndTime(Intent intent, TimeUnit timeUnit) {
        return aab(intent, timeUnit, EXTRA_END_TIME);
    }

    @Deprecated
    public static DataController getDataController(Context context, AuthHuaweiId authHuaweiId) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(authHuaweiId);
        HiHealthKitClient.getInstance().setContext(context);
        return new DataController(authHuaweiId);
    }

    public static DataController getDataController(Context context) {
        Preconditions.checkNotNull(context);
        HiHealthKitClient.getInstance().setContext(context);
        return new DataController();
    }

    @Deprecated
    public static DataController getDataController(Activity activity, AuthHuaweiId authHuaweiId) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(authHuaweiId);
        HiHealthKitClient.getInstance().setContext(activity.getApplicationContext());
        return new DataController(authHuaweiId);
    }

    public static DataController getDataController(Activity activity) {
        Preconditions.checkNotNull(activity);
        HiHealthKitClient.getInstance().setContext(activity.getApplicationContext());
        return new DataController();
    }

    @Deprecated
    public static ConsentsController getConsentsController(Context context, AuthHuaweiId authHuaweiId) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(authHuaweiId);
        HiHealthKitClient.getInstance().setContext(context);
        return new ConsentsController(authHuaweiId);
    }

    public static ConsentsController getConsentsController(Context context) {
        Preconditions.checkNotNull(context);
        HiHealthKitClient.getInstance().setContext(context);
        return new ConsentsController();
    }

    @Deprecated
    public static ConsentsController getConsentsController(Activity activity, AuthHuaweiId authHuaweiId) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(authHuaweiId);
        HiHealthKitClient.getInstance().setContext(activity.getApplicationContext());
        return new ConsentsController(authHuaweiId);
    }

    public static ConsentsController getConsentsController(Activity activity) {
        Preconditions.checkNotNull(activity);
        HiHealthKitClient.getInstance().setContext(activity.getApplicationContext());
        return new ConsentsController();
    }

    @Deprecated
    public static AutoRecorderController getAutoRecorderController(Context context, AuthHuaweiId authHuaweiId) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(authHuaweiId);
        HiHealthKitClient.getInstance().setContext(context);
        return new AutoRecorderController(authHuaweiId);
    }

    public static AutoRecorderController getAutoRecorderController(Context context) {
        Preconditions.checkNotNull(context);
        HiHealthKitClient.getInstance().setContext(context);
        return new AutoRecorderController();
    }

    @Deprecated
    public static AutoRecorderController getAutoRecorderController(Activity activity, AuthHuaweiId authHuaweiId) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(authHuaweiId);
        HiHealthKitClient.getInstance().setContext(activity.getApplicationContext());
        return new AutoRecorderController(authHuaweiId);
    }

    public static AutoRecorderController getAutoRecorderController(Activity activity) {
        Preconditions.checkNotNull(activity);
        HiHealthKitClient.getInstance().setContext(activity.getApplicationContext());
        return new AutoRecorderController();
    }

    @Deprecated
    public static AuthController getAuthController(Context context, AuthHuaweiId authHuaweiId) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(authHuaweiId);
        HiHealthKitClient.getInstance().setContext(context);
        return new AuthController(authHuaweiId);
    }

    public static AuthController getAuthController(Context context) {
        Preconditions.checkNotNull(context);
        HiHealthKitClient.getInstance().setContext(context);
        return new AuthController();
    }

    @Deprecated
    public static AuthController getAuthController(Activity activity, AuthHuaweiId authHuaweiId) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(authHuaweiId);
        HiHealthKitClient.getInstance().setContext(activity.getApplicationContext());
        return new AuthController(authHuaweiId);
    }

    public static AuthController getAuthController(Activity activity) {
        Preconditions.checkNotNull(activity);
        HiHealthKitClient.getInstance().setContext(activity.getApplicationContext());
        return new AuthController();
    }

    @Deprecated
    public static ActivityRecordsController getActivityRecordsController(Context context, AuthHuaweiId authHuaweiId) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(authHuaweiId);
        HiHealthKitClient.getInstance().setContext(context);
        return new ActivityRecordsController(authHuaweiId);
    }

    public static ActivityRecordsController getActivityRecordsController(Context context) {
        Preconditions.checkNotNull(context);
        HiHealthKitClient.getInstance().setContext(context);
        return new ActivityRecordsController();
    }

    @Deprecated
    public static ActivityRecordsController getActivityRecordsController(Activity activity, AuthHuaweiId authHuaweiId) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(authHuaweiId);
        HiHealthKitClient.getInstance().setContext(activity.getApplicationContext());
        return new ActivityRecordsController(authHuaweiId);
    }

    public static ActivityRecordsController getActivityRecordsController(Activity activity) {
        Preconditions.checkNotNull(activity);
        HiHealthKitClient.getInstance().setContext(activity.getApplicationContext());
        return new ActivityRecordsController();
    }

    public static void disconnect() {
        HiHealthKitClient.getInstance().disconnect();
    }

    public static void clearCache() {
        if (HiHealthKitClient.getInstance().getContext() == null) {
            return;
        }
        aaci.aabc().aab();
    }

    private static long aab(Intent intent, TimeUnit timeUnit, String str) {
        Preconditions.checkNotNull(intent);
        try {
            long longExtra = intent.getLongExtra(str, -1L);
            if (longExtra == -1) {
                return -1L;
            }
            return timeUnit.convert(longExtra, TimeUnit.MILLISECONDS);
        } catch (Throwable unused) {
            return -1L;
        }
    }

    private HuaweiHiHealth() {
    }
}
