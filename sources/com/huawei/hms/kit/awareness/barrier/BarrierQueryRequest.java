package com.huawei.hms.kit.awareness.barrier;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.barrier.internal.c;
import com.huawei.hms.kit.awareness.barrier.internal.f.b;
import java.util.Collection;

/* loaded from: classes4.dex */
public abstract class BarrierQueryRequest implements Parcelable {
    public static final Parcelable.Creator<BarrierQueryRequest> CREATOR = new Parcelable.Creator<BarrierQueryRequest>() { // from class: com.huawei.hms.kit.awareness.barrier.BarrierQueryRequest.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BarrierQueryRequest[] newArray(int i) {
            return new c[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BarrierQueryRequest createFromParcel(Parcel parcel) {
            return c.a(parcel);
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
    }

    public static BarrierQueryRequest forBarriers(String... strArr) {
        b.a(strArr);
        return c.a(strArr);
    }

    public static BarrierQueryRequest forBarriers(Collection<String> collection) {
        b.b(collection);
        return c.a(collection);
    }

    public static BarrierQueryRequest all() {
        return c.a();
    }
}
