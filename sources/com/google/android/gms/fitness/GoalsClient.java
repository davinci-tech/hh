package com.google.android.gms.fitness;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.fitness.data.Goal;
import com.google.android.gms.fitness.request.GoalsReadRequest;
import com.google.android.gms.internal.fitness.zzab;
import com.google.android.gms.internal.fitness.zzdg;
import com.google.android.gms.tasks.Task;
import java.util.List;

/* loaded from: classes8.dex */
public class GoalsClient extends GoogleApi<FitnessOptions> {
    private static final GoalsApi zzo = new zzdg();

    GoalsClient(Context context, FitnessOptions fitnessOptions) {
        super(context, zzab.zzew, fitnessOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    GoalsClient(Activity activity, FitnessOptions fitnessOptions) {
        super(activity, zzab.zzew, fitnessOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public Task<List<Goal>> readCurrentGoals(GoalsReadRequest goalsReadRequest) {
        return PendingResultUtil.toTask(zzo.readCurrentGoals(asGoogleApiClient(), goalsReadRequest), zzh.zzf);
    }
}
