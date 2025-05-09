package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.fitness.zzer;
import com.google.android.gms.internal.fitness.zzes;

/* loaded from: classes8.dex */
public final class zzah extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzah> CREATOR = new zzai();
    private final zzer zzhl;

    zzah(IBinder iBinder) {
        this.zzhl = zzes.zzk(iBinder);
    }

    public zzah(zzer zzerVar) {
        this.zzhl = zzerVar;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 1, this.zzhl.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
