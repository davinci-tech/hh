package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes8.dex */
public class ActivityTransition extends AbstractSafeParcelable {
    public static final int ACTIVITY_TRANSITION_ENTER = 0;
    public static final int ACTIVITY_TRANSITION_EXIT = 1;
    public static final Parcelable.Creator<ActivityTransition> CREATOR = new zzc();
    private final int zzi;
    private final int zzj;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface SupportedActivityTransition {
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, getActivityType());
        SafeParcelWriter.writeInt(parcel, 2, getTransitionType());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public String toString() {
        int i = this.zzi;
        int i2 = this.zzj;
        StringBuilder sb = new StringBuilder(75);
        sb.append("ActivityTransition [mActivityType=");
        sb.append(i);
        sb.append(", mTransitionType=");
        sb.append(i2);
        sb.append(']');
        return sb.toString();
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zzi), Integer.valueOf(this.zzj));
    }

    public int getTransitionType() {
        return this.zzj;
    }

    public static class Builder {
        private int zzi = -1;
        private int zzj = -1;

        public Builder setActivityType(int i) {
            DetectedActivity.zzb(i);
            this.zzi = i;
            return this;
        }

        public Builder setActivityTransition(int i) {
            ActivityTransition.zza(i);
            this.zzj = i;
            return this;
        }

        public ActivityTransition build() {
            Preconditions.checkState(this.zzi != -1, "Activity type not set.");
            Preconditions.checkState(this.zzj != -1, "Activity transition type not set.");
            return new ActivityTransition(this.zzi, this.zzj);
        }
    }

    public int getActivityType() {
        return this.zzi;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ActivityTransition)) {
            return false;
        }
        ActivityTransition activityTransition = (ActivityTransition) obj;
        return this.zzi == activityTransition.zzi && this.zzj == activityTransition.zzj;
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x0003, code lost:
    
        if (r3 <= 1) goto L7;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void zza(int r3) {
        /*
            if (r3 < 0) goto L6
            r0 = 1
            if (r3 > r0) goto L6
            goto L7
        L6:
            r0 = 0
        L7:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r2 = 41
            r1.<init>(r2)
            java.lang.String r2 = "Transition type "
            r1.append(r2)
            r1.append(r3)
            java.lang.String r3 = " is not valid."
            r1.append(r3)
            java.lang.String r3 = r1.toString()
            com.google.android.gms.common.internal.Preconditions.checkArgument(r0, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.location.ActivityTransition.zza(int):void");
    }

    ActivityTransition(int i, int i2) {
        this.zzi = i;
        this.zzj = i2;
    }
}
