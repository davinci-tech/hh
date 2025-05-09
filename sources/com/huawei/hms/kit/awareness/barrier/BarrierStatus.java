package com.huawei.hms.kit.awareness.barrier;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.barrier.internal.d;

/* loaded from: classes4.dex */
public abstract class BarrierStatus implements Parcelable {
    public static final Parcelable.Creator<BarrierStatus> CREATOR = new Parcelable.Creator<BarrierStatus>() { // from class: com.huawei.hms.kit.awareness.barrier.BarrierStatus.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BarrierStatus[] newArray(int i) {
            return new BarrierStatus[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BarrierStatus createFromParcel(Parcel parcel) {
            return d.a(parcel);
        }
    };
    public static final int FALSE = 0;
    public static final int TRUE = 1;
    public static final int UNKNOWN = 2;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public abstract String getBarrierLabel();

    public abstract long getLastBarrierUpdateTime();

    public abstract int getLastStatus();

    public abstract int getPresentStatus();

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
    }

    public static BarrierStatus extract(Intent intent) {
        return d.a(intent.getIntExtra("context_barrier_current_state", 0), intent.getIntExtra("context_barrier_previous_state", 0), intent.getLongExtra("context_barrier_last_updated_time", 0L), intent.getStringExtra("context_barrier_key"));
    }
}
