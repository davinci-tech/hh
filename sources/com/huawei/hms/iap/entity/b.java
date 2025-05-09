package com.huawei.hms.iap.entity;

import android.app.PendingIntent;
import com.huawei.hms.core.aidl.AbstractMessageEntity;
import com.huawei.hms.core.aidl.annotation.Packed;

/* loaded from: classes4.dex */
public class b extends AbstractMessageEntity {

    @Packed
    private int accountFlag;

    @Packed
    private String carrierId;

    @Packed
    private String country;

    @Packed
    private PendingIntent pendingIntent;

    @Packed
    private int returnCode;

    @Packed
    private String returnDesc;

    public void setReturnDesc(String str) {
        this.returnDesc = str;
    }

    public void setReturnCode(int i) {
        this.returnCode = i;
    }

    public String getReturnDesc() {
        return this.returnDesc;
    }

    public int getReturnCode() {
        return this.returnCode;
    }

    public PendingIntent getPendingIntent() {
        return this.pendingIntent;
    }

    public String getCountry() {
        return this.country;
    }

    public String getCarrierId() {
        return this.carrierId;
    }

    public int getAccountFlag() {
        return this.accountFlag;
    }
}
