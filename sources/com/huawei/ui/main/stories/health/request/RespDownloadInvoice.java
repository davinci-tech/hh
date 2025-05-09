package com.huawei.ui.main.stories.health.request;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes8.dex */
public class RespDownloadInvoice extends InvoiceResponse implements Parcelable {
    public static final Parcelable.Creator<RespDownloadInvoice> CREATOR = new Parcelable.Creator<RespDownloadInvoice>() { // from class: com.huawei.ui.main.stories.health.request.RespDownloadInvoice.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: dFV_, reason: merged with bridge method [inline-methods] */
        public RespDownloadInvoice createFromParcel(Parcel parcel) {
            return new RespDownloadInvoice(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public RespDownloadInvoice[] newArray(int i) {
            return new RespDownloadInvoice[i];
        }
    };
    private String fileType;
    private String message;

    @Override // com.huawei.ui.main.stories.health.request.InvoiceResponse, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RespDownloadInvoice(Parcel parcel) {
        super(parcel);
        this.message = parcel.readString();
        this.fileType = parcel.readString();
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String getFileType() {
        return this.fileType;
    }

    public void setFileType(String str) {
        this.fileType = str;
    }

    @Override // com.huawei.ui.main.stories.health.request.InvoiceResponse, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.message);
        parcel.writeString(this.fileType);
    }
}
