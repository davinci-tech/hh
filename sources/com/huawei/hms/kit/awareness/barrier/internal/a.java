package com.huawei.hms.kit.awareness.barrier.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.barrier.AwarenessBarrier;

/* loaded from: classes4.dex */
public class a implements Parcelable {
    public static final Parcelable.Creator<a> CREATOR = new Parcelable.Creator<a>() { // from class: com.huawei.hms.kit.awareness.barrier.internal.a.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public a[] newArray(int i) {
            return new a[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public a createFromParcel(Parcel parcel) {
            return new a(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private final String f4848a;
    private final int b;
    private final AwarenessBarrier c;
    private final PendingIntent d;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f4848a);
        parcel.writeInt(this.b);
        parcel.writeParcelable(this.c, i);
        parcel.writeParcelable(this.d, i);
    }

    public int f() {
        AwarenessBarrier awarenessBarrier = this.c;
        if (awarenessBarrier instanceof com.huawei.hms.kit.awareness.barrier.internal.a.c) {
            return ((com.huawei.hms.kit.awareness.barrier.internal.a.c) awarenessBarrier).n().b();
        }
        return 10007;
    }

    public PendingIntent e() {
        return this.d;
    }

    public String d() {
        return this.f4848a;
    }

    public int c() {
        return this.b;
    }

    public AwarenessBarrier b() {
        return this.c;
    }

    public boolean a() {
        AwarenessBarrier awarenessBarrier = this.c;
        if (awarenessBarrier instanceof com.huawei.hms.kit.awareness.barrier.internal.a.c) {
            return ((com.huawei.hms.kit.awareness.barrier.internal.a.c) awarenessBarrier).n().a();
        }
        return false;
    }

    int a(Context context) {
        if (c() != 1 && this.c == null) {
            return 0;
        }
        if (c() != 1 || this.d == null) {
            return 10007;
        }
        AwarenessBarrier awarenessBarrier = this.c;
        if (!(awarenessBarrier instanceof com.huawei.hms.kit.awareness.barrier.internal.a.c)) {
            return 10007;
        }
        com.huawei.hms.kit.awareness.barrier.internal.a.c cVar = (com.huawei.hms.kit.awareness.barrier.internal.a.c) awarenessBarrier;
        if (cVar.j()) {
            return cVar.a(context);
        }
        return 10006;
    }

    public static a a(String str, int i, AwarenessBarrier awarenessBarrier, PendingIntent pendingIntent) {
        return new a(str, i, awarenessBarrier, pendingIntent);
    }

    private a(String str, int i, AwarenessBarrier awarenessBarrier, PendingIntent pendingIntent) {
        this.f4848a = str;
        this.b = i;
        this.c = awarenessBarrier;
        this.d = pendingIntent;
    }

    private a(Parcel parcel) {
        this.f4848a = parcel.readString();
        this.b = parcel.readInt();
        this.c = (AwarenessBarrier) parcel.readParcelable(AwarenessBarrier.class.getClassLoader());
        this.d = (PendingIntent) parcel.readParcelable(PendingIntent.class.getClassLoader());
    }
}
