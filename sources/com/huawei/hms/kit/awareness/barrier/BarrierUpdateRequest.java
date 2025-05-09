package com.huawei.hms.kit.awareness.barrier;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.barrier.internal.f;
import com.huawei.hms.kit.awareness.barrier.internal.f.b;

/* loaded from: classes4.dex */
public abstract class BarrierUpdateRequest implements Parcelable {
    public static final Parcelable.Creator<BarrierUpdateRequest> CREATOR = new Parcelable.Creator<BarrierUpdateRequest>() { // from class: com.huawei.hms.kit.awareness.barrier.BarrierUpdateRequest.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BarrierUpdateRequest[] newArray(int i) {
            return new f[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BarrierUpdateRequest createFromParcel(Parcel parcel) {
            return new f(parcel);
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
    }

    /* loaded from: classes9.dex */
    public static final class Builder {
        private final f mRequest = new f();

        public Builder deleteBarrier(String str) {
            b.a(str);
            this.mRequest.a(str);
            return this;
        }

        public Builder deleteBarrier(PendingIntent pendingIntent) {
            b.a(pendingIntent);
            this.mRequest.a(pendingIntent);
            return this;
        }

        public Builder deleteAll() {
            this.mRequest.c();
            return this;
        }

        public BarrierUpdateRequest build() {
            return this.mRequest;
        }

        public Builder addBarrier(String str, AwarenessBarrier awarenessBarrier, PendingIntent pendingIntent) {
            b.a(str);
            b.a(awarenessBarrier);
            b.a(pendingIntent);
            this.mRequest.a(str, awarenessBarrier, pendingIntent);
            return this;
        }
    }
}
