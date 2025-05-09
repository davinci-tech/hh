package com.huawei.health.suggestion.ui.fitness.viewholder;

import android.content.Context;
import androidx.fragment.app.FragmentManager;

/* loaded from: classes4.dex */
public class FitnessSessionManager {

    /* renamed from: a, reason: collision with root package name */
    private static FitnessSessionManager f3218a;
    private SessionActivityAction e;

    public interface SessionActivityAction {
        void startSportHistoryActivity(Context context);

        void startSportHistoryActivity(Context context, int i, int i2);
    }

    /* loaded from: classes8.dex */
    public interface SessionForFitness {
        void initOperationConfigured(int i, FragmentManager fragmentManager);
    }

    public static FitnessSessionManager e() {
        FitnessSessionManager fitnessSessionManager;
        synchronized (FitnessSessionManager.class) {
            if (f3218a == null) {
                f3218a = new FitnessSessionManager();
            }
            fitnessSessionManager = f3218a;
        }
        return fitnessSessionManager;
    }

    public SessionActivityAction c() {
        return this.e;
    }

    public void a(SessionActivityAction sessionActivityAction) {
        this.e = sessionActivityAction;
    }
}
