package com.huawei.health.device.model;

import android.os.Parcel;
import com.huawei.health.device.datatype.AbstractFitnessData;

/* loaded from: classes3.dex */
public abstract class FitnessData extends AbstractFitnessData {
    public static final int FITNESS_DATA_SUPPORT_RANGE = 12;

    public FitnessData(Parcel parcel) {
        super(parcel);
    }

    public FitnessData() {
    }
}
