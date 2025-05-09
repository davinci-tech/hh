package com.huawei.datatype;

import com.google.gson.annotations.SerializedName;
import defpackage.bky;
import defpackage.jdy;
import java.io.Serializable;

/* loaded from: classes3.dex */
public class PhoneNumber implements Serializable {

    @SerializedName("phone_number")
    private String mPhoneNumber;

    @SerializedName("phone_tag")
    private String mPhoneTag;

    public PhoneNumber() {
        this.mPhoneTag = "Mobile";
    }

    public PhoneNumber(String str, String str2) {
        this.mPhoneTag = "Mobile";
        this.mPhoneTag = (String) jdy.d(str);
        this.mPhoneNumber = (String) jdy.d(str2);
    }

    public String getPhoneNumber() {
        return (String) jdy.d(this.mPhoneNumber);
    }

    public void setPhoneNumber(String str) {
        this.mPhoneNumber = (String) jdy.d(str);
    }

    public String getPhoneTag() {
        return (String) jdy.d(this.mPhoneTag);
    }

    public void setPhoneTag(String str) {
        this.mPhoneTag = (String) jdy.d(str);
    }

    public String toString() {
        return "[PhoneNumber: phone_tag = " + this.mPhoneTag + ", phone_number = " + bky.e(this.mPhoneNumber) + "]";
    }
}
