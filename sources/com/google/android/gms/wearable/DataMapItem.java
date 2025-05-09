package com.google.android.gms.wearable;

import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.common.internal.Asserts;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class DataMapItem {
    private final Uri uri;
    private final DataMap zzr;

    public static DataMapItem fromDataItem(DataItem dataItem) {
        Asserts.checkNotNull(dataItem, "dataItem must not be null");
        return new DataMapItem(dataItem);
    }

    private DataMapItem(DataItem dataItem) {
        this.uri = dataItem.getUri();
        this.zzr = zza(dataItem.freeze());
    }

    public Uri getUri() {
        return this.uri;
    }

    public DataMap getDataMap() {
        return this.zzr;
    }

    private static DataMap zza(DataItem dataItem) {
        if (dataItem.getData() == null && dataItem.getAssets().size() > 0) {
            throw new IllegalArgumentException("Cannot create DataMapItem from a DataItem  that wasn't made with DataMapItem.");
        }
        if (dataItem.getData() == null) {
            return new DataMap();
        }
        try {
            ArrayList arrayList = new ArrayList();
            int size = dataItem.getAssets().size();
            for (int i = 0; i < size; i++) {
                DataItemAsset dataItemAsset = dataItem.getAssets().get(Integer.toString(i));
                if (dataItemAsset == null) {
                    String valueOf = String.valueOf(dataItem);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 64);
                    sb.append("Cannot find DataItemAsset referenced in data at ");
                    sb.append(i);
                    sb.append(" for ");
                    sb.append(valueOf);
                    throw new IllegalStateException(sb.toString());
                }
                arrayList.add(Asset.createFromRef(dataItemAsset.getId()));
            }
            return com.google.android.gms.internal.wearable.zze.zza(new com.google.android.gms.internal.wearable.zzf(com.google.android.gms.internal.wearable.zzg.zza(dataItem.getData()), arrayList));
        } catch (com.google.android.gms.internal.wearable.zzs | NullPointerException e) {
            String valueOf2 = String.valueOf(dataItem.getUri());
            String encodeToString = Base64.encodeToString(dataItem.getData(), 0);
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 50 + String.valueOf(encodeToString).length());
            sb2.append("Unable to parse datamap from dataItem. uri=");
            sb2.append(valueOf2);
            sb2.append(", data=");
            sb2.append(encodeToString);
            Log.w("DataItem", sb2.toString());
            String valueOf3 = String.valueOf(dataItem.getUri());
            StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf3).length() + 44);
            sb3.append("Unable to parse datamap from dataItem.  uri=");
            sb3.append(valueOf3);
            throw new IllegalStateException(sb3.toString(), e);
        }
    }
}
