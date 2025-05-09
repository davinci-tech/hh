package com.huawei.hihealth.data.model;

import android.content.ContentValues;
import com.google.gson.annotations.SerializedName;
import com.huawei.operation.ble.BleConstants;

/* loaded from: classes.dex */
public class HiBloodSugarMetaData {

    @SerializedName(BleConstants.IS_CONFIRMED_DB)
    private boolean mIsConfirmed = false;

    @SerializedName("mValueHolder")
    private ContentValues mValueHolder;

    private ContentValues getValueHolder() {
        if (this.mValueHolder == null) {
            this.mValueHolder = new ContentValues();
        }
        return this.mValueHolder;
    }

    public boolean getConfirmed() {
        return this.mIsConfirmed;
    }

    public void setConfirmed(boolean z) {
        this.mIsConfirmed = z;
    }
}
