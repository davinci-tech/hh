package com.huawei.ui.main.stories.health.request;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes8.dex */
public class InvoiceRequestInfo implements Parcelable {
    public static final Parcelable.Creator<InvoiceRequestInfo> CREATOR = new Parcelable.Creator<InvoiceRequestInfo>() { // from class: com.huawei.ui.main.stories.health.request.InvoiceRequestInfo.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: dFT_, reason: merged with bridge method [inline-methods] */
        public InvoiceRequestInfo createFromParcel(Parcel parcel) {
            return new InvoiceRequestInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public InvoiceRequestInfo[] newArray(int i) {
            return new InvoiceRequestInfo[i];
        }
    };
    private static final String TAG = "InvoiceRequestInfo";
    private String customerName;
    private Integer customerType;
    private List<InvoiceDetailInfo> invoiceList;
    private String orderCode;
    private String requestId;
    private Integer requestStatus;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public InvoiceRequestInfo(Parcel parcel) {
        this.requestId = parcel.readString();
        this.orderCode = parcel.readString();
        this.requestStatus = Integer.valueOf(parcel.readInt());
        this.customerType = Integer.valueOf(parcel.readInt());
        this.customerName = parcel.readString();
        this.invoiceList = parcel.createTypedArrayList(InvoiceDetailInfo.CREATOR);
    }

    public String getRequestId() {
        return this.requestId;
    }

    public void setRequestId(String str) {
        this.requestId = str;
    }

    public String getOrderCode() {
        return this.orderCode;
    }

    public void setOrderCode(String str) {
        this.orderCode = str;
    }

    public Integer getRequestStatus() {
        return this.requestStatus;
    }

    public void setRequestStatus(Integer num) {
        this.requestStatus = num;
    }

    public Integer getCustomerType() {
        return this.customerType;
    }

    public void setCustomerType(Integer num) {
        this.customerType = num;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String str) {
        this.customerName = str;
    }

    public List<InvoiceDetailInfo> getInvoiceList() {
        return this.invoiceList;
    }

    public void setInvoiceList(List<InvoiceDetailInfo> list) {
        this.invoiceList = list;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.requestId);
        parcel.writeString(this.orderCode);
        parcel.writeInt(this.requestStatus.intValue());
        parcel.writeInt(this.customerType.intValue());
        parcel.writeString(this.customerName);
        parcel.writeTypedList(this.invoiceList);
    }

    public String toString() {
        return "InvoiceRequestInfo{requestId='" + this.requestId + "', orderCode='" + this.orderCode + "', requestStatus=" + this.requestStatus + ", customerType=" + this.customerType + ", customerName='" + this.customerName + "', invoiceList=" + this.invoiceList + '}';
    }
}
