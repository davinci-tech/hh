package com.google.android.gms.internal.fitness;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.DataType;
import java.util.Collections;
import java.util.List;

/* loaded from: classes8.dex */
public final class zzeu extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzeu> CREATOR = new zzev();
    private final List<DataType> zzah;

    public zzeu(List<DataType> list) {
        this.zzah = list;
    }

    public final List<DataType> getDataTypes() {
        return Collections.unmodifiableList(this.zzah);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("dataTypes", this.zzah).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, Collections.unmodifiableList(this.zzah), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
