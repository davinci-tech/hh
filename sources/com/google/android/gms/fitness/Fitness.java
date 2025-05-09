package com.google.android.gms.fitness;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.fitness.zzab;
import com.google.android.gms.internal.fitness.zzag;
import com.google.android.gms.internal.fitness.zzam;
import com.google.android.gms.internal.fitness.zzas;
import com.google.android.gms.internal.fitness.zzay;
import com.google.android.gms.internal.fitness.zzct;
import com.google.android.gms.internal.fitness.zzdb;
import com.google.android.gms.internal.fitness.zzdg;
import com.google.android.gms.internal.fitness.zzdj;
import com.google.android.gms.internal.fitness.zzdt;
import com.google.android.gms.internal.fitness.zzea;
import com.google.android.gms.internal.fitness.zzee;
import com.google.android.gms.internal.fitness.zzv;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class Fitness {
    public static final String ACTION_TRACK = "vnd.google.fitness.TRACK";
    public static final String ACTION_VIEW = "vnd.google.fitness.VIEW";
    public static final String ACTION_VIEW_GOAL = "vnd.google.fitness.VIEW_GOAL";

    @Deprecated
    public static final Void API = null;
    public static final String EXTRA_END_TIME = "vnd.google.fitness.end_time";
    public static final String EXTRA_START_TIME = "vnd.google.fitness.start_time";
    public static final Api<Api.ApiOptions.NoOptions> SENSORS_API = zzas.API;
    public static final SensorsApi SensorsApi = new zzea();
    public static final Api<Api.ApiOptions.NoOptions> RECORDING_API = zzam.API;
    public static final RecordingApi RecordingApi = new zzdt();
    public static final Api<Api.ApiOptions.NoOptions> SESSIONS_API = zzay.API;
    public static final SessionsApi SessionsApi = new zzee();
    public static final Api<Api.ApiOptions.NoOptions> HISTORY_API = zzag.API;
    public static final HistoryApi HistoryApi = new zzdj();
    public static final Api<Api.ApiOptions.NoOptions> GOALS_API = zzab.API;
    public static final GoalsApi GoalsApi = new zzdg();
    public static final Api<Api.ApiOptions.NoOptions> CONFIG_API = zzv.API;
    public static final ConfigApi ConfigApi = new zzdb();
    public static final Api<Api.ApiOptions.NoOptions> BLE_API = com.google.android.gms.internal.fitness.zzp.API;
    public static final BleApi BleApi = new zzct();
    public static final Scope SCOPE_ACTIVITY_READ = new Scope(Scopes.FITNESS_ACTIVITY_READ);
    public static final Scope SCOPE_ACTIVITY_READ_WRITE = new Scope(Scopes.FITNESS_ACTIVITY_READ_WRITE);
    public static final Scope SCOPE_LOCATION_READ = new Scope(Scopes.FITNESS_LOCATION_READ);
    public static final Scope SCOPE_LOCATION_READ_WRITE = new Scope(Scopes.FITNESS_LOCATION_READ_WRITE);
    public static final Scope SCOPE_BODY_READ = new Scope(Scopes.FITNESS_BODY_READ);
    public static final Scope SCOPE_BODY_READ_WRITE = new Scope(Scopes.FITNESS_BODY_READ_WRITE);
    public static final Scope SCOPE_NUTRITION_READ = new Scope(Scopes.FITNESS_NUTRITION_READ);
    public static final Scope SCOPE_NUTRITION_READ_WRITE = new Scope(Scopes.FITNESS_NUTRITION_READ_WRITE);

    private Fitness() {
    }

    public static SensorsClient getSensorsClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new SensorsClient(activity, FitnessOptions.zza(googleSignInAccount).build());
    }

    public static SensorsClient getSensorsClient(Context context, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new SensorsClient(context, FitnessOptions.zza(googleSignInAccount).build());
    }

    public static RecordingClient getRecordingClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new RecordingClient(activity, FitnessOptions.zza(googleSignInAccount).build());
    }

    public static RecordingClient getRecordingClient(Context context, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new RecordingClient(context, FitnessOptions.zza(googleSignInAccount).build());
    }

    public static SessionsClient getSessionsClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new SessionsClient(activity, FitnessOptions.zza(googleSignInAccount).build());
    }

    public static SessionsClient getSessionsClient(Context context, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new SessionsClient(context, FitnessOptions.zza(googleSignInAccount).build());
    }

    public static HistoryClient getHistoryClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new HistoryClient(activity, FitnessOptions.zza(googleSignInAccount).build());
    }

    public static HistoryClient getHistoryClient(Context context, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new HistoryClient(context, FitnessOptions.zza(googleSignInAccount).build());
    }

    public static GoalsClient getGoalsClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new GoalsClient(activity, FitnessOptions.zza(googleSignInAccount).build());
    }

    public static GoalsClient getGoalsClient(Context context, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new GoalsClient(context, FitnessOptions.zza(googleSignInAccount).build());
    }

    public static ConfigClient getConfigClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new ConfigClient(activity, FitnessOptions.zza(googleSignInAccount).build());
    }

    public static ConfigClient getConfigClient(Context context, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new ConfigClient(context, FitnessOptions.zza(googleSignInAccount).build());
    }

    public static BleClient getBleClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new BleClient(activity, FitnessOptions.zza(googleSignInAccount).build());
    }

    public static BleClient getBleClient(Context context, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new BleClient(context, FitnessOptions.zza(googleSignInAccount).build());
    }

    public static long getStartTime(Intent intent, TimeUnit timeUnit) {
        long longExtra = intent.getLongExtra(EXTRA_START_TIME, -1L);
        if (longExtra == -1) {
            return -1L;
        }
        return timeUnit.convert(longExtra, TimeUnit.MILLISECONDS);
    }

    public static long getEndTime(Intent intent, TimeUnit timeUnit) {
        long longExtra = intent.getLongExtra(EXTRA_END_TIME, -1L);
        if (longExtra == -1) {
            return -1L;
        }
        return timeUnit.convert(longExtra, TimeUnit.MILLISECONDS);
    }
}
