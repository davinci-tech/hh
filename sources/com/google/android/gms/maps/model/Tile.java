package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes8.dex */
public final class Tile extends AbstractSafeParcelable {
    public static final Parcelable.Creator<Tile> CREATOR = new zzr();
    public final byte[] data;
    public final int height;
    public final int width;

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.width);
        SafeParcelWriter.writeInt(parcel, 3, this.height);
        SafeParcelWriter.writeByteArray(parcel, 4, this.data, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public Tile(int i, int i2, byte[] bArr) {
        this.width = i;
        this.height = i2;
        this.data = bArr;
    }
}
