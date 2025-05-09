package com.google.android.gms.location;

import java.util.Comparator;

/* loaded from: classes8.dex */
final class zze implements Comparator<ActivityTransition> {
    @Override // java.util.Comparator
    public final /* synthetic */ int compare(ActivityTransition activityTransition, ActivityTransition activityTransition2) {
        ActivityTransition activityTransition3 = activityTransition;
        ActivityTransition activityTransition4 = activityTransition2;
        int activityType = activityTransition3.getActivityType();
        int activityType2 = activityTransition4.getActivityType();
        if (activityType != activityType2) {
            return activityType < activityType2 ? -1 : 1;
        }
        int transitionType = activityTransition3.getTransitionType();
        int transitionType2 = activityTransition4.getTransitionType();
        if (transitionType == transitionType2) {
            return 0;
        }
        return transitionType < transitionType2 ? -1 : 1;
    }

    zze() {
    }
}
