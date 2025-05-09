package com.huawei.health.marketing.request;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes8.dex */
public class BaseResponse implements Parcelable {
    public static final Parcelable.Creator<BaseResponse> CREATOR = new Parcelable.Creator<BaseResponse>() { // from class: com.huawei.health.marketing.request.BaseResponse.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BaseResponse createFromParcel(Parcel parcel) {
            return new BaseResponse(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BaseResponse[] newArray(int i) {
            return new BaseResponse[i];
        }
    };
    private int resultCode;
    private String resultDesc;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BaseResponse(Parcel parcel) {
        this.resultCode = parcel.readInt();
        this.resultDesc = parcel.readString();
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int i) {
        this.resultCode = i;
    }

    public String getResultDesc() {
        return this.resultDesc;
    }

    public void setResultDesc(String str) {
        this.resultDesc = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.resultCode);
        parcel.writeString(this.resultDesc);
    }
}
