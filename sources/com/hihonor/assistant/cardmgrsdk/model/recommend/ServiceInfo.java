package com.hihonor.assistant.cardmgrsdk.model.recommend;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes8.dex */
public class ServiceInfo implements Parcelable {
    public static final Parcelable.Creator<ServiceInfo> CREATOR = new Parcelable.Creator<ServiceInfo>() { // from class: com.hihonor.assistant.cardmgrsdk.model.recommend.ServiceInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ServiceInfo[] newArray(int i) {
            return new ServiceInfo[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ServiceInfo createFromParcel(Parcel parcel) {
            return new ServiceInfo(parcel);
        }
    };
    public int cardSize;
    public String city;
    public String name;
    public String serviceName;
    public String serviceType;
    public int widgetType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.serviceType);
        parcel.writeString(this.serviceName);
        parcel.writeString(this.city);
        parcel.writeInt(this.widgetType);
        parcel.writeInt(this.cardSize);
    }

    public void setWidgetType(int i) {
        this.widgetType = i;
    }

    public void setServiceType(String str) {
        this.serviceType = str;
    }

    public void setServiceName(String str) {
        this.serviceName = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public void setCardSize(int i) {
        this.cardSize = i;
    }

    public int getWidgetType() {
        return this.widgetType;
    }

    public String getServiceType() {
        return this.serviceType;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public String getName() {
        return this.name;
    }

    public String getCity() {
        return this.city;
    }

    public int getCardSize() {
        return this.cardSize;
    }

    public ServiceInfo(Parcel parcel) {
        this.name = parcel.readString();
        this.serviceType = parcel.readString();
        this.serviceName = parcel.readString();
        this.city = parcel.readString();
        this.widgetType = parcel.readInt();
        this.cardSize = parcel.readInt();
    }

    public ServiceInfo() {
    }
}
