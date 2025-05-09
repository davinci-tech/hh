package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.util.Log;
import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataItemAsset;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.Constants;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public final class zzdf extends DataBufferRef implements DataItem {
    private final int zzdl;

    public zzdf(DataHolder dataHolder, int i, int i2) {
        super(dataHolder, i);
        this.zzdl = i2;
    }

    @Override // com.google.android.gms.wearable.DataItem
    public final Uri getUri() {
        return Uri.parse(getString(BleConstants.KEY_PATH));
    }

    @Override // com.google.android.gms.wearable.DataItem
    public final byte[] getData() {
        return getByteArray("data");
    }

    @Override // com.google.android.gms.wearable.DataItem
    public final Map<String, DataItemAsset> getAssets() {
        HashMap hashMap = new HashMap(this.zzdl);
        for (int i = 0; i < this.zzdl; i++) {
            zzdb zzdbVar = new zzdb(this.mDataHolder, this.mDataRow + i);
            if (zzdbVar.getDataItemKey() != null) {
                hashMap.put(zzdbVar.getDataItemKey(), zzdbVar);
            }
        }
        return hashMap;
    }

    @Override // com.google.android.gms.wearable.DataItem
    public final DataItem setData(byte[] bArr) {
        throw new UnsupportedOperationException();
    }

    public final String toString() {
        boolean isLoggable = Log.isLoggable("DataItem", 3);
        byte[] data = getData();
        Map<String, DataItemAsset> assets = getAssets();
        StringBuilder sb = new StringBuilder("DataItemRef{ ");
        String valueOf = String.valueOf(getUri());
        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 4);
        sb2.append("uri=");
        sb2.append(valueOf);
        sb.append(sb2.toString());
        String valueOf2 = String.valueOf(data == null ? Constants.NULL : Integer.valueOf(data.length));
        StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf2).length() + 9);
        sb3.append(", dataSz=");
        sb3.append(valueOf2);
        sb.append(sb3.toString());
        int size = assets.size();
        StringBuilder sb4 = new StringBuilder(23);
        sb4.append(", numAssets=");
        sb4.append(size);
        sb.append(sb4.toString());
        if (isLoggable && !assets.isEmpty()) {
            sb.append(", assets=[");
            String str = "";
            for (Map.Entry<String, DataItemAsset> entry : assets.entrySet()) {
                String key = entry.getKey();
                String id = entry.getValue().getId();
                StringBuilder sb5 = new StringBuilder(str.length() + 2 + String.valueOf(key).length() + String.valueOf(id).length());
                sb5.append(str);
                sb5.append(key);
                sb5.append(": ");
                sb5.append(id);
                sb.append(sb5.toString());
                str = ", ";
            }
            sb.append("]");
        }
        sb.append(" }");
        return sb.toString();
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final /* synthetic */ DataItem freeze() {
        return new zzdc(this);
    }
}
