package com.google.android.gms.wearable;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.EntityBuffer;
import com.google.android.gms.wearable.internal.zzcy;
import com.huawei.operation.ble.BleConstants;

/* loaded from: classes2.dex */
public class DataEventBuffer extends EntityBuffer<DataEvent> implements Result {
    private final Status zzp;

    public DataEventBuffer(DataHolder dataHolder) {
        super(dataHolder);
        this.zzp = new Status(dataHolder.getStatusCode());
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.zzp;
    }

    @Override // com.google.android.gms.common.data.EntityBuffer
    public /* synthetic */ DataEvent getEntry(int i, int i2) {
        return new zzcy(this.mDataHolder, i, i2);
    }

    @Override // com.google.android.gms.common.data.EntityBuffer
    public String getPrimaryDataMarkerColumn() {
        return BleConstants.KEY_PATH;
    }
}
