package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.fitness.zzch;
import com.google.android.gms.internal.fitness.zzci;

/* loaded from: classes8.dex */
public final class zzaj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzaj> CREATOR = new zzak();
    private final zzch zzhm;
    private final DataType zzq;

    zzaj(DataType dataType, IBinder iBinder) {
        this.zzq = dataType;
        this.zzhm = zzci.zzg(iBinder);
    }

    public zzaj(DataType dataType, zzch zzchVar) {
        this.zzq = dataType;
        this.zzhm = zzchVar;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzq, i, false);
        zzch zzchVar = this.zzhm;
        SafeParcelWriter.writeIBinder(parcel, 2, zzchVar == null ? null : zzchVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
