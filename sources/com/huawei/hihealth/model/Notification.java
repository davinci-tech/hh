package com.huawei.hihealth.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hihealth.CommonParam;

/* loaded from: classes.dex */
public class Notification extends CommonParam {
    public static final Parcelable.Creator<Notification> CREATOR = new Parcelable.Creator<Notification>() { // from class: com.huawei.hihealth.model.Notification.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bwx_, reason: merged with bridge method [inline-methods] */
        public Notification createFromParcel(Parcel parcel) {
            return new Notification(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public Notification[] newArray(int i) {
            return new Notification[i];
        }
    };

    public Notification(int i, int i2, String str) {
        putInt("data_type", i);
        putInt("errorCode", i2);
        putString("errorMsg", str);
    }

    private Notification(Parcel parcel) {
        super(parcel);
    }

    public int getErrorCode() {
        return this.valueHolder.getInt("errorCode");
    }

    public void setErrorInfo(int i, String str) {
        putInt("errorCode", i);
        putString("errorMsg", str);
    }

    public String getErrorMsg() {
        return this.valueHolder.getString("errorMsg");
    }

    public int getDataType() {
        return this.valueHolder.getInt("data_type");
    }

    public void setDataType(int i) {
        putInt("data_type", i);
    }

    public void setSubscribeTag(boolean z) {
        this.valueHolder.putBoolean("subscribe_tag", z);
    }

    public boolean getSubscribeTag() {
        return this.valueHolder.getBoolean("subscribe_tag", true);
    }
}
