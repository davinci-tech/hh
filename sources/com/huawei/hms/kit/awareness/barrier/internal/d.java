package com.huawei.hms.kit.awareness.barrier.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.barrier.BarrierStatus;
import com.huawei.hms.kit.awareness.internal.communication.Constants;

/* loaded from: classes4.dex */
public class d extends BarrierStatus implements Parcelable {

    /* renamed from: a, reason: collision with root package name */
    private int f4865a;
    private int b;
    private long c;
    private final String d;

    @Override // com.huawei.hms.kit.awareness.barrier.BarrierStatus, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f4865a);
        parcel.writeInt(this.b);
        parcel.writeLong(this.c);
        parcel.writeString(this.d);
    }

    @Override // com.huawei.hms.kit.awareness.barrier.BarrierStatus
    public int getPresentStatus() {
        return this.f4865a;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.BarrierStatus
    public int getLastStatus() {
        return this.b;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.BarrierStatus
    public long getLastBarrierUpdateTime() {
        return this.c;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.BarrierStatus
    public String getBarrierLabel() {
        return this.d;
    }

    public Intent c() {
        return b(null);
    }

    public Intent b(String str) {
        Intent intent = new Intent();
        if (!com.huawei.hms.kit.awareness.barrier.internal.f.c.a(str)) {
            intent.setAction(Constants.ACTION_INNER_BARRIER_TRIGGER);
            intent.setPackage(str);
        }
        intent.putExtra("context_barrier_current_state", this.f4865a);
        intent.putExtra("context_barrier_previous_state", this.b);
        intent.putExtra("context_barrier_last_updated_time", getLastBarrierUpdateTime());
        intent.putExtra("context_barrier_key", getBarrierLabel());
        return intent;
    }

    public void a(long j) {
        this.c = j;
    }

    public void a(int i) {
        this.b = this.f4865a;
        this.f4865a = i;
    }

    public static d a(String str) {
        return new d(2, 2, System.currentTimeMillis(), str);
    }

    public static d a(Parcel parcel) {
        return new d(parcel);
    }

    public static d a(int i, int i2, long j, String str) {
        return new d(i, i2, j, str);
    }

    private d(Parcel parcel) {
        int readInt = parcel.readInt();
        int readInt2 = parcel.readInt();
        this.f4865a = readInt;
        this.b = readInt2;
        this.c = parcel.readLong();
        this.d = parcel.readString();
    }

    private d(int i, int i2, long j, String str) {
        this.f4865a = i;
        this.b = i2;
        this.c = j;
        this.d = str;
    }
}
