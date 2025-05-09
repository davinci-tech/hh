package com.hihonor.assistant.cardmgrsdk.model.recommend;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes8.dex */
public class WidgetInfo implements Parcelable {
    public static final Parcelable.Creator<WidgetInfo> CREATOR = new Parcelable.Creator<WidgetInfo>() { // from class: com.hihonor.assistant.cardmgrsdk.model.recommend.WidgetInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WidgetInfo[] newArray(int i) {
            return new WidgetInfo[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WidgetInfo createFromParcel(Parcel parcel) {
            return new WidgetInfo(parcel);
        }
    };
    public String appName;
    public int cardId;
    public String contentText;
    public String contentTitle;
    public byte[] iconBgBytes;
    public byte[] iconBytes;
    public String iconUrl;
    public String jumpInfo;
    public int positionX;
    public int positionY;
    public ServiceInfo serviceInfo;
    public String widgetPackage;
    public String widgetProvider;
    public int widgetServiceId;
    public int widgetSize;
    public int widgetType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.contentTitle);
        parcel.writeString(this.contentText);
        parcel.writeByteArray(this.iconBytes);
        parcel.writeInt(this.widgetType);
        parcel.writeInt(this.positionX);
        parcel.writeInt(this.positionY);
        parcel.writeInt(this.widgetSize);
        parcel.writeInt(this.widgetServiceId);
        parcel.writeString(this.jumpInfo);
        parcel.writeInt(this.cardId);
        parcel.writeString(this.widgetPackage);
        parcel.writeString(this.widgetProvider);
        parcel.writeByteArray(this.iconBgBytes);
        parcel.writeString(this.appName);
        parcel.writeParcelable(this.serviceInfo, i);
    }

    public void setWidgetType(int i) {
        this.widgetType = i;
    }

    public void setWidgetSize(int i) {
        this.widgetSize = i;
    }

    public void setWidgetServiceId(int i) {
        this.widgetServiceId = i;
    }

    public void setWidgetProvider(String str) {
        this.widgetProvider = str;
    }

    public void setWidgetPackage(String str) {
        this.widgetPackage = str;
    }

    public void setServiceInfo(ServiceInfo serviceInfo) {
        this.serviceInfo = serviceInfo;
    }

    public void setPositionY(int i) {
        this.positionY = i;
    }

    public void setPositionX(int i) {
        this.positionX = i;
    }

    public void setJumpInfo(String str) {
        this.jumpInfo = str;
    }

    public void setIconUrl(String str) {
        this.iconUrl = str;
    }

    public void setIconBytes(byte[] bArr) {
        this.iconBytes = bArr;
    }

    public void setIconBgBytes(byte[] bArr) {
        this.iconBgBytes = bArr;
    }

    public void setContentTitle(String str) {
        this.contentTitle = str;
    }

    public void setContentText(String str) {
        this.contentText = str;
    }

    public void setCardId(int i) {
        this.cardId = i;
    }

    public void setAppName(String str) {
        this.appName = str;
    }

    public int getWidgetType() {
        return this.widgetType;
    }

    public int getWidgetSize() {
        return this.widgetSize;
    }

    public int getWidgetServiceId() {
        return this.widgetServiceId;
    }

    public String getWidgetProvider() {
        return this.widgetProvider;
    }

    public String getWidgetPackage() {
        return this.widgetPackage;
    }

    public ServiceInfo getServiceInfo() {
        return this.serviceInfo;
    }

    public int getPositionY() {
        return this.positionY;
    }

    public int getPositionX() {
        return this.positionX;
    }

    public String getJumpInfo() {
        return this.jumpInfo;
    }

    public String getIconUrl() {
        return this.iconUrl;
    }

    public byte[] getIconBytes() {
        return this.iconBytes;
    }

    public byte[] getIconBgBytes() {
        return this.iconBgBytes;
    }

    public String getContentTitle() {
        return this.contentTitle;
    }

    public String getContentText() {
        return this.contentText;
    }

    public int getCardId() {
        return this.cardId;
    }

    public String getAppName() {
        return this.appName;
    }

    public WidgetInfo(Parcel parcel) {
        this.contentTitle = parcel.readString();
        this.contentText = parcel.readString();
        this.iconBytes = parcel.createByteArray();
        this.widgetType = parcel.readInt();
        this.positionX = parcel.readInt();
        this.positionY = parcel.readInt();
        this.widgetSize = parcel.readInt();
        this.widgetServiceId = parcel.readInt();
        this.jumpInfo = parcel.readString();
        this.cardId = parcel.readInt();
        this.widgetPackage = parcel.readString();
        this.widgetProvider = parcel.readString();
        this.iconBgBytes = parcel.createByteArray();
        this.appName = parcel.readString();
        this.serviceInfo = (ServiceInfo) parcel.readParcelable(ServiceInfo.class.getClassLoader());
    }

    public WidgetInfo() {
    }
}
