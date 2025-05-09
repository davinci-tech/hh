package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataItem;

/* loaded from: classes2.dex */
public final class zzcx implements DataEvent {
    private int type;
    private DataItem zzdg;

    public zzcx(DataEvent dataEvent) {
        this.type = dataEvent.getType();
        this.zzdg = dataEvent.getDataItem().freeze();
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final /* bridge */ /* synthetic */ DataEvent freeze() {
        return this;
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final boolean isDataValid() {
        return true;
    }

    @Override // com.google.android.gms.wearable.DataEvent
    public final DataItem getDataItem() {
        return this.zzdg;
    }

    @Override // com.google.android.gms.wearable.DataEvent
    public final int getType() {
        return this.type;
    }

    public final String toString() {
        String str = getType() == 1 ? "changed" : getType() == 2 ? "deleted" : "unknown";
        String valueOf = String.valueOf(getDataItem());
        StringBuilder sb = new StringBuilder(str.length() + 35 + String.valueOf(valueOf).length());
        sb.append("DataEventEntity{ type=");
        sb.append(str);
        sb.append(", dataitem=");
        sb.append(valueOf);
        sb.append(" }");
        return sb.toString();
    }
}
