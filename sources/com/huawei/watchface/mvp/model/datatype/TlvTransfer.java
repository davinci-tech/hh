package com.huawei.watchface.mvp.model.datatype;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class TlvTransfer {
    private int mIndex;
    private String mNewType;
    private String mOldType;
    public List<TlvTransfer> mTlvTransfers;

    public TlvTransfer(int i, String str, String str2) {
        this.mIndex = i;
        this.mNewType = str2;
        this.mOldType = str;
    }

    public TlvTransfer() {
        this.mTlvTransfers = new ArrayList(32);
    }

    public int getTransferDataIndex() {
        return this.mIndex;
    }

    public void setTransferDataIndex(int i) {
        this.mIndex = i;
    }

    public String getNewTypeValue() {
        return this.mNewType;
    }

    public void setNewTypeValue(String str) {
        this.mNewType = str;
    }

    public String getOldTypeValue() {
        return this.mOldType;
    }

    public void setOldTypeValue(String str) {
        this.mOldType = str;
    }
}
