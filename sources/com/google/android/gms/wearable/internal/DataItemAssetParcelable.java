package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.wearable.DataItemAsset;

/* loaded from: classes2.dex */
public class DataItemAssetParcelable extends AbstractSafeParcelable implements ReflectedParcelable, DataItemAsset {
    public static final Parcelable.Creator<DataItemAssetParcelable> CREATOR = new zzda();
    private final String zzdm;
    private final String zzdn;

    DataItemAssetParcelable(String str, String str2) {
        this.zzdm = str;
        this.zzdn = str2;
    }

    @Override // com.google.android.gms.common.data.Freezable
    public /* bridge */ /* synthetic */ DataItemAsset freeze() {
        return this;
    }

    @Override // com.google.android.gms.common.data.Freezable
    public boolean isDataValid() {
        return true;
    }

    public DataItemAssetParcelable(DataItemAsset dataItemAsset) {
        this.zzdm = (String) Preconditions.checkNotNull(dataItemAsset.getId());
        this.zzdn = (String) Preconditions.checkNotNull(dataItemAsset.getDataItemKey());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getId(), false);
        SafeParcelWriter.writeString(parcel, 3, getDataItemKey(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Override // com.google.android.gms.wearable.DataItemAsset
    public String getId() {
        return this.zzdm;
    }

    @Override // com.google.android.gms.wearable.DataItemAsset
    public String getDataItemKey() {
        return this.zzdn;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("DataItemAssetParcelable[@");
        sb.append(Integer.toHexString(hashCode()));
        if (this.zzdm == null) {
            sb.append(",noid");
        } else {
            sb.append(",");
            sb.append(this.zzdm);
        }
        sb.append(", key=");
        sb.append(this.zzdn);
        sb.append("]");
        return sb.toString();
    }
}
