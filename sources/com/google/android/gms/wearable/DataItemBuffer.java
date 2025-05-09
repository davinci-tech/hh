package com.google.android.gms.wearable;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.EntityBuffer;
import com.google.android.gms.wearable.internal.zzdf;
import com.huawei.operation.ble.BleConstants;

/* loaded from: classes8.dex */
public class DataItemBuffer extends EntityBuffer<DataItem> implements Result {
    private final Status zzp;

    public DataItemBuffer(DataHolder dataHolder) {
        super(dataHolder);
        this.zzp = new Status(dataHolder.getStatusCode());
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.zzp;
    }

    @Override // com.google.android.gms.common.data.EntityBuffer
    public /* synthetic */ DataItem getEntry(int i, int i2) {
        return new zzdf(this.mDataHolder, i, i2);
    }

    @Override // com.google.android.gms.common.data.EntityBuffer
    public String getPrimaryDataMarkerColumn() {
        return BleConstants.KEY_PATH;
    }
}
