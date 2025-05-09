package com.google.android.gms.location;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import java.util.Collections;
import java.util.List;

/* loaded from: classes8.dex */
public class ActivityTransitionResult extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ActivityTransitionResult> CREATOR = new zzg();
    private final List<ActivityTransitionEvent> zzn;

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getTransitionEvents(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public int hashCode() {
        return this.zzn.hashCode();
    }

    public List<ActivityTransitionEvent> getTransitionEvents() {
        return this.zzn;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.zzn.equals(((ActivityTransitionResult) obj).zzn);
    }

    public static boolean hasResult(Intent intent) {
        if (intent == null) {
            return false;
        }
        return intent.hasExtra("com.google.android.location.internal.EXTRA_ACTIVITY_TRANSITION_RESULT");
    }

    public static ActivityTransitionResult extractResult(Intent intent) {
        if (hasResult(intent)) {
            return (ActivityTransitionResult) SafeParcelableSerializer.deserializeFromIntentExtra(intent, "com.google.android.location.internal.EXTRA_ACTIVITY_TRANSITION_RESULT", CREATOR);
        }
        return null;
    }

    public ActivityTransitionResult(List<ActivityTransitionEvent> list) {
        Preconditions.checkNotNull(list, "transitionEvents list can't be null.");
        if (!list.isEmpty()) {
            for (int i = 1; i < list.size(); i++) {
                Preconditions.checkArgument(list.get(i).getElapsedRealTimeNanos() >= list.get(i + (-1)).getElapsedRealTimeNanos());
            }
        }
        this.zzn = Collections.unmodifiableList(list);
    }
}
