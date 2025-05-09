package com.huawei.ui.main.stories.health.request;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes8.dex */
public class InvoiceDetailInfo implements Parcelable {
    public static final Parcelable.Creator<InvoiceDetailInfo> CREATOR = new Parcelable.Creator<InvoiceDetailInfo>() { // from class: com.huawei.ui.main.stories.health.request.InvoiceDetailInfo.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: dFS_, reason: merged with bridge method [inline-methods] */
        public InvoiceDetailInfo createFromParcel(Parcel parcel) {
            return new InvoiceDetailInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public InvoiceDetailInfo[] newArray(int i) {
            return new InvoiceDetailInfo[i];
        }
    };
    private static final String TAG = "InvoiceDetailInfo";
    private Long invoiceAmount;
    private String invoiceCode;
    private String invoiceCreator;
    private String invoiceId;
    private String invoiceMemo;
    private String invoiceNo;
    private Integer invoiceStatus;
    private Long invoiceTime;
    private String relationId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public InvoiceDetailInfo(Parcel parcel) {
        this.relationId = parcel.readString();
        this.invoiceStatus = Integer.valueOf(parcel.readInt());
        this.invoiceId = parcel.readString();
        this.invoiceCode = parcel.readString();
        this.invoiceNo = parcel.readString();
        this.invoiceAmount = Long.valueOf(parcel.readLong());
        this.invoiceTime = Long.valueOf(parcel.readLong());
        this.invoiceMemo = parcel.readString();
        this.invoiceCreator = parcel.readString();
    }

    public InvoiceDetailInfo() {
    }

    public String getRelationId() {
        return this.relationId;
    }

    public void setRelationId(String str) {
        this.relationId = str;
    }

    public Integer getInvoiceStatus() {
        return this.invoiceStatus;
    }

    public void setInvoiceStatus(Integer num) {
        this.invoiceStatus = num;
    }

    public String getInvoiceId() {
        return this.invoiceId;
    }

    public void setInvoiceId(String str) {
        this.invoiceId = str;
    }

    public String getInvoiceCode() {
        return this.invoiceCode;
    }

    public void setInvoiceCode(String str) {
        this.invoiceCode = str;
    }

    public String getInvoiceNo() {
        return this.invoiceNo;
    }

    public void setInvoiceNo(String str) {
        this.invoiceNo = str;
    }

    public Long getInvoiceAmount() {
        return this.invoiceAmount;
    }

    public void setInvoiceAmount(Long l) {
        this.invoiceAmount = l;
    }

    public String getInvoiceMemo() {
        return this.invoiceMemo;
    }

    public void setInvoiceMemo(String str) {
        this.invoiceMemo = str;
    }

    public Long getInvoiceTime() {
        return this.invoiceTime;
    }

    public void setInvoiceTime(Long l) {
        this.invoiceTime = l;
    }

    public String getInvoiceCreator() {
        return this.invoiceCreator;
    }

    public void setInvoiceCreator(String str) {
        this.invoiceCreator = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.relationId);
        parcel.writeInt(this.invoiceStatus.intValue());
        parcel.writeString(this.invoiceId);
        parcel.writeString(this.invoiceCode);
        parcel.writeString(this.invoiceNo);
        parcel.writeLong(this.invoiceAmount.longValue());
        parcel.writeLong(this.invoiceTime.longValue());
        parcel.writeString(this.invoiceMemo);
        parcel.writeString(this.invoiceCreator);
    }

    public String toString() {
        return "InvoiceDetailInfo{relationId='" + this.relationId + "', invoiceStatus=" + this.invoiceStatus + ", invoiceId='" + this.invoiceId + "', invoiceCode='" + this.invoiceCode + "', invoiceNo='" + this.invoiceNo + "', invoiceAmount=" + this.invoiceAmount + ", invoiceMemo='" + this.invoiceMemo + "', invoiceTime='" + this.invoiceTime + "', invoiceCreator='" + this.invoiceCreator + "'}";
    }
}
