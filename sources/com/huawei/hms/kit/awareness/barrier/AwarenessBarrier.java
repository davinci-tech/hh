package com.huawei.hms.kit.awareness.barrier;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.barrier.internal.a.c;
import com.huawei.hms.kit.awareness.barrier.internal.f.b;
import java.util.Collection;

/* loaded from: classes4.dex */
public abstract class AwarenessBarrier implements Parcelable {
    public static final Parcelable.Creator<AwarenessBarrier> CREATOR = new Parcelable.Creator<AwarenessBarrier>() { // from class: com.huawei.hms.kit.awareness.barrier.AwarenessBarrier.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AwarenessBarrier[] newArray(int i) {
            return new AwarenessBarrier[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AwarenessBarrier createFromParcel(Parcel parcel) {
            return c.CREATOR.createFromParcel(parcel);
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
    }

    public static AwarenessBarrier or(AwarenessBarrier... awarenessBarrierArr) {
        b.a((Object[]) awarenessBarrierArr);
        return com.huawei.hms.kit.awareness.barrier.internal.a.b.a(2, awarenessBarrierArr);
    }

    public static AwarenessBarrier or(Collection<AwarenessBarrier> collection) {
        b.a((Collection) collection);
        return com.huawei.hms.kit.awareness.barrier.internal.a.b.a(2, collection);
    }

    public static AwarenessBarrier not(AwarenessBarrier awarenessBarrier) {
        if (com.huawei.hms.kit.awareness.barrier.internal.f.c.a(awarenessBarrier)) {
            throw new IllegalArgumentException();
        }
        return com.huawei.hms.kit.awareness.barrier.internal.a.b.a(3, awarenessBarrier);
    }

    public static AwarenessBarrier and(AwarenessBarrier... awarenessBarrierArr) {
        b.a((Object[]) awarenessBarrierArr);
        return com.huawei.hms.kit.awareness.barrier.internal.a.b.a(1, awarenessBarrierArr);
    }

    public static AwarenessBarrier and(Collection<AwarenessBarrier> collection) {
        b.a((Collection) collection);
        return com.huawei.hms.kit.awareness.barrier.internal.a.b.a(1, collection);
    }
}
