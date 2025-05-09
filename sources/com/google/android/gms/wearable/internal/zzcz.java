package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.DataItemAsset;

/* loaded from: classes2.dex */
public final class zzcz implements DataItemAsset {
    private final String zzdm;
    private final String zzdn;

    public zzcz(DataItemAsset dataItemAsset) {
        this.zzdm = dataItemAsset.getId();
        this.zzdn = dataItemAsset.getDataItemKey();
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final /* bridge */ /* synthetic */ DataItemAsset freeze() {
        return this;
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final boolean isDataValid() {
        return true;
    }

    @Override // com.google.android.gms.wearable.DataItemAsset
    public final String getId() {
        return this.zzdm;
    }

    @Override // com.google.android.gms.wearable.DataItemAsset
    public final String getDataItemKey() {
        return this.zzdn;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("DataItemAssetEntity[@");
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
