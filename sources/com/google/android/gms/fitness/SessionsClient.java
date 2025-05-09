package com.google.android.gms.fitness;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.fitness.result.SessionReadResponse;
import com.google.android.gms.internal.fitness.zzay;
import com.google.android.gms.internal.fitness.zzee;
import com.google.android.gms.tasks.Task;
import java.util.List;

/* loaded from: classes8.dex */
public class SessionsClient extends GoogleApi<FitnessOptions> {
    private static final SessionsApi zzab = new zzee();

    SessionsClient(Context context, FitnessOptions fitnessOptions) {
        super(context, zzay.zzew, fitnessOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    SessionsClient(Activity activity, FitnessOptions fitnessOptions) {
        super(activity, zzay.zzew, fitnessOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public Task<Void> startSession(Session session) {
        return PendingResultUtil.toVoidTask(zzab.startSession(asGoogleApiClient(), session));
    }

    public Task<List<Session>> stopSession(String str) {
        return PendingResultUtil.toTask(zzab.stopSession(asGoogleApiClient(), str), zzp.zzf);
    }

    public Task<Void> insertSession(SessionInsertRequest sessionInsertRequest) {
        return PendingResultUtil.toVoidTask(zzab.insertSession(asGoogleApiClient(), sessionInsertRequest));
    }

    public Task<SessionReadResponse> readSession(SessionReadRequest sessionReadRequest) {
        return PendingResultUtil.toResponseTask(zzab.readSession(asGoogleApiClient(), sessionReadRequest), new SessionReadResponse());
    }

    public Task<Void> registerForSessions(PendingIntent pendingIntent) {
        return PendingResultUtil.toVoidTask(zzab.registerForSessions(asGoogleApiClient(), pendingIntent));
    }

    public Task<Void> unregisterForSessions(PendingIntent pendingIntent) {
        return PendingResultUtil.toVoidTask(zzab.unregisterForSessions(asGoogleApiClient(), pendingIntent));
    }
}
