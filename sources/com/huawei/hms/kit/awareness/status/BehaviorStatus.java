package com.huawei.hms.kit.awareness.status;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/* loaded from: classes9.dex */
public class BehaviorStatus implements Parcelable {
    public static final Parcelable.Creator<BehaviorStatus> CREATOR = new Parcelable.Creator<BehaviorStatus>() { // from class: com.huawei.hms.kit.awareness.status.BehaviorStatus.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BehaviorStatus[] newArray(int i) {
            return new BehaviorStatus[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BehaviorStatus createFromParcel(Parcel parcel) {
            return new BehaviorStatus(parcel);
        }
    };
    private final long mElapsedRealtimeMillis;
    private final List<DetectedBehavior> mProbableBehavior;
    private final long mTime;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.mTime);
        parcel.writeLong(this.mElapsedRealtimeMillis);
        parcel.writeTypedList(this.mProbableBehavior);
    }

    public long getTime() {
        return this.mTime;
    }

    public List<DetectedBehavior> getProbableBehavior() {
        return this.mProbableBehavior;
    }

    public DetectedBehavior getMostLikelyBehavior() {
        if (this.mProbableBehavior.isEmpty()) {
            return null;
        }
        return this.mProbableBehavior.get(0);
    }

    public long getElapsedRealtimeMillis() {
        return this.mElapsedRealtimeMillis;
    }

    public int getBehaviorConfidence(final int i) {
        DetectedBehavior orElse;
        if (this.mProbableBehavior.isEmpty() || (orElse = this.mProbableBehavior.stream().filter(new Predicate() { // from class: com.huawei.hms.kit.awareness.status.BehaviorStatus$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return BehaviorStatus.lambda$getBehaviorConfidence$0(i, (DetectedBehavior) obj);
            }
        }).findAny().orElse(null)) == null) {
            return 0;
        }
        return orElse.getConfidence();
    }

    static /* synthetic */ boolean lambda$getBehaviorConfidence$0(int i, DetectedBehavior detectedBehavior) {
        return detectedBehavior.getType() == i;
    }

    public BehaviorStatus(List<DetectedBehavior> list, long j, long j2) {
        this.mProbableBehavior = list;
        this.mTime = j;
        this.mElapsedRealtimeMillis = j2;
    }

    private BehaviorStatus(Parcel parcel) {
        this.mTime = parcel.readLong();
        this.mElapsedRealtimeMillis = parcel.readLong();
        List<DetectedBehavior> createTypedArrayList = parcel.createTypedArrayList(DetectedBehavior.CREATOR);
        this.mProbableBehavior = createTypedArrayList == null ? Collections.emptyList() : createTypedArrayList;
    }
}
