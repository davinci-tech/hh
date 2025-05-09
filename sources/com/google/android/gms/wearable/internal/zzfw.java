package com.google.android.gms.wearable.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes8.dex */
public final class zzfw extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzfw> CREATOR = new zzfx();
    private final int versionCode;
    private final zzem zzaz;

    zzfw(int i, IBinder iBinder) {
        this.versionCode = i;
        zzem zzemVar = null;
        if (iBinder == null) {
            this.zzaz = null;
            return;
        }
        if (iBinder != null) {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.wearable.internal.IWearableListener");
            zzemVar = queryLocalInterface instanceof zzem ? (zzem) queryLocalInterface : new zzeo(iBinder);
        }
        this.zzaz = zzemVar;
    }

    public zzfw(zzem zzemVar) {
        this.versionCode = 1;
        this.zzaz = zzemVar;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        zzem zzemVar = this.zzaz;
        SafeParcelWriter.writeIBinder(parcel, 2, zzemVar == null ? null : zzemVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
