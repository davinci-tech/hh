package com.google.android.gms.location;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import java.util.Collections;
import java.util.List;

/* loaded from: classes8.dex */
public class ActivityRecognitionResult extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<ActivityRecognitionResult> CREATOR = new zzb();
    private Bundle extras;
    private List<DetectedActivity> zze;
    private long zzf;
    private long zzg;
    private int zzh;

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, this.zze, false);
        SafeParcelWriter.writeLong(parcel, 2, this.zzf);
        SafeParcelWriter.writeLong(parcel, 3, this.zzg);
        SafeParcelWriter.writeInt(parcel, 4, this.zzh);
        SafeParcelWriter.writeBundle(parcel, 5, this.extras, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public String toString() {
        String valueOf = String.valueOf(this.zze);
        long j = this.zzf;
        long j2 = this.zzg;
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 124);
        sb.append("ActivityRecognitionResult [probableActivities=");
        sb.append(valueOf);
        sb.append(", timeMillis=");
        sb.append(j);
        sb.append(", elapsedRealtimeMillis=");
        sb.append(j2);
        sb.append("]");
        return sb.toString();
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zzf), Long.valueOf(this.zzg), Integer.valueOf(this.zzh), this.zze, this.extras);
    }

    public long getTime() {
        return this.zzf;
    }

    public List<DetectedActivity> getProbableActivities() {
        return this.zze;
    }

    public DetectedActivity getMostProbableActivity() {
        return this.zze.get(0);
    }

    public long getElapsedRealtimeMillis() {
        return this.zzg;
    }

    public int getActivityConfidence(int i) {
        for (DetectedActivity detectedActivity : this.zze) {
            if (detectedActivity.getType() == i) {
                return detectedActivity.getConfidence();
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ActivityRecognitionResult activityRecognitionResult = (ActivityRecognitionResult) obj;
        return this.zzf == activityRecognitionResult.zzf && this.zzg == activityRecognitionResult.zzg && this.zzh == activityRecognitionResult.zzh && Objects.equal(this.zze, activityRecognitionResult.zze) && zza(this.extras, activityRecognitionResult.extras);
    }

    private static boolean zza(Bundle bundle, Bundle bundle2) {
        if (bundle == null && bundle2 == null) {
            return true;
        }
        if ((bundle == null && bundle2 != null) || ((bundle != null && bundle2 == null) || bundle.size() != bundle2.size())) {
            return false;
        }
        for (String str : bundle.keySet()) {
            if (!bundle2.containsKey(str)) {
                return false;
            }
            if (bundle.get(str) == null) {
                if (bundle2.get(str) != null) {
                    return false;
                }
            } else if (bundle.get(str) instanceof Bundle) {
                if (!zza(bundle.getBundle(str), bundle2.getBundle(str))) {
                    return false;
                }
            } else if (!bundle.get(str).equals(bundle2.get(str))) {
                return false;
            }
        }
        return true;
    }

    private static List<ActivityRecognitionResult> zza(Intent intent) {
        if (intent != null && intent.hasExtra("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT_LIST")) {
            return SafeParcelableSerializer.deserializeIterableFromIntentExtra(intent, "com.google.android.location.internal.EXTRA_ACTIVITY_RESULT_LIST", CREATOR);
        }
        return null;
    }

    public static boolean hasResult(Intent intent) {
        if (intent == null) {
            return false;
        }
        if (intent != null && intent.hasExtra("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT")) {
            return true;
        }
        List<ActivityRecognitionResult> zza = zza(intent);
        return (zza == null || zza.isEmpty()) ? false : true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0020, code lost:
    
        if ((r0 instanceof com.google.android.gms.location.ActivityRecognitionResult) != false) goto L9;
     */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0028 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.google.android.gms.location.ActivityRecognitionResult extractResult(android.content.Intent r3) {
        /*
            boolean r0 = hasResult(r3)
            r1 = 0
            if (r0 == 0) goto L25
            android.os.Bundle r0 = r3.getExtras()
            java.lang.String r2 = "com.google.android.location.internal.EXTRA_ACTIVITY_RESULT"
            java.lang.Object r0 = r0.get(r2)
            boolean r2 = r0 instanceof byte[]
            if (r2 == 0) goto L1e
            byte[] r0 = (byte[]) r0
            android.os.Parcelable$Creator<com.google.android.gms.location.ActivityRecognitionResult> r2 = com.google.android.gms.location.ActivityRecognitionResult.CREATOR
            com.google.android.gms.common.internal.safeparcel.SafeParcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer.deserializeFromBytes(r0, r2)
            goto L22
        L1e:
            boolean r2 = r0 instanceof com.google.android.gms.location.ActivityRecognitionResult
            if (r2 == 0) goto L25
        L22:
            com.google.android.gms.location.ActivityRecognitionResult r0 = (com.google.android.gms.location.ActivityRecognitionResult) r0
            goto L26
        L25:
            r0 = r1
        L26:
            if (r0 == 0) goto L29
            return r0
        L29:
            java.util.List r3 = zza(r3)
            if (r3 == 0) goto L43
            boolean r0 = r3.isEmpty()
            if (r0 == 0) goto L36
            goto L43
        L36:
            int r0 = r3.size()
            int r0 = r0 + (-1)
            java.lang.Object r3 = r3.get(r0)
            com.google.android.gms.location.ActivityRecognitionResult r3 = (com.google.android.gms.location.ActivityRecognitionResult) r3
            return r3
        L43:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.location.ActivityRecognitionResult.extractResult(android.content.Intent):com.google.android.gms.location.ActivityRecognitionResult");
    }

    public ActivityRecognitionResult(List<DetectedActivity> list, long j, long j2, int i, Bundle bundle) {
        boolean z = false;
        Preconditions.checkArgument(list != null && list.size() > 0, "Must have at least 1 detected activity");
        if (j > 0 && j2 > 0) {
            z = true;
        }
        Preconditions.checkArgument(z, "Must set times");
        this.zze = list;
        this.zzf = j;
        this.zzg = j2;
        this.zzh = i;
        this.extras = bundle;
    }

    public ActivityRecognitionResult(List<DetectedActivity> list, long j, long j2) {
        this(list, j, j2, 0, (Bundle) null);
    }

    private ActivityRecognitionResult(DetectedActivity detectedActivity, long j, long j2, int i, Bundle bundle) {
        this((List<DetectedActivity>) Collections.singletonList(detectedActivity), j, j2, 0, (Bundle) null);
    }

    public ActivityRecognitionResult(DetectedActivity detectedActivity, long j, long j2) {
        this(detectedActivity, j, j2, 0, (Bundle) null);
    }
}
