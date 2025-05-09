package com.huawei.ui.main.stories.health.request;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes8.dex */
public class InvoiceResponse implements Parcelable {
    public static final Parcelable.Creator<InvoiceResponse> CREATOR = new Parcelable.Creator<InvoiceResponse>() { // from class: com.huawei.ui.main.stories.health.request.InvoiceResponse.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: dFU_, reason: merged with bridge method [inline-methods] */
        public InvoiceResponse createFromParcel(Parcel parcel) {
            return new InvoiceResponse(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public InvoiceResponse[] newArray(int i) {
            return new InvoiceResponse[i];
        }
    };
    private int resultCode;
    private String resultDesc;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public InvoiceResponse(Parcel parcel) {
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
