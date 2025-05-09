package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes8.dex */
public final class zzad extends AbstractSafeParcelable implements Result {
    private final Status zzbl;
    private static final zzad zzcr = new zzad(Status.RESULT_SUCCESS);
    public static final Parcelable.Creator<zzad> CREATOR = new zzae();

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getStatus(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zzbl;
    }

    public zzad(Status status) {
        this.zzbl = status;
    }
}
