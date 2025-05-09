package com.huawei.ui.main.stories.health.request;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class RespQueryResult extends InvoiceResponse implements Parcelable {
    public static final Parcelable.Creator<RespQueryResult> CREATOR = new Parcelable.Creator<RespQueryResult>() { // from class: com.huawei.ui.main.stories.health.request.RespQueryResult.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: dFW_, reason: merged with bridge method [inline-methods] */
        public RespQueryResult createFromParcel(Parcel parcel) {
            return new RespQueryResult(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public RespQueryResult[] newArray(int i) {
            return new RespQueryResult[i];
        }
    };
    private List<InvoiceRequestInfo> invoiceRequestInfos;

    @Override // com.huawei.ui.main.stories.health.request.InvoiceResponse, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RespQueryResult(Parcel parcel) {
        super(parcel);
        if (this.invoiceRequestInfos == null) {
            this.invoiceRequestInfos = new ArrayList();
        }
        parcel.readTypedList(this.invoiceRequestInfos, InvoiceRequestInfo.CREATOR);
    }

    public List<InvoiceRequestInfo> getInvoiceRequestInfos() {
        return this.invoiceRequestInfos;
    }

    public void setInvoiceRequestInfos(List<InvoiceRequestInfo> list) {
        this.invoiceRequestInfos = list;
    }

    @Override // com.huawei.ui.main.stories.health.request.InvoiceResponse, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeTypedList(this.invoiceRequestInfos);
    }
}
