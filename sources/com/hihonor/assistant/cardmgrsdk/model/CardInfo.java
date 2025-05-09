package com.hihonor.assistant.cardmgrsdk.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.RemoteViews;
import com.hihonor.assistant.cardmgrsdk.CardMgrSdkConst;
import java.util.HashMap;

/* loaded from: classes8.dex */
public class CardInfo implements Parcelable {
    public static final Parcelable.Creator<CardInfo> CREATOR = new Parcelable.Creator<CardInfo>() { // from class: com.hihonor.assistant.cardmgrsdk.model.CardInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CardInfo[] newArray(int i) {
            return new CardInfo[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CardInfo createFromParcel(Parcel parcel) {
            return new CardInfo(parcel);
        }
    };
    public String business;
    public String businessId;
    public int cardDisplayType;
    public RemoteViews cardRemoteViews;
    public String cardTemplateContent;
    public int comparedFlag;
    public String detailType;
    public int displayResult;
    public long exposureDuration;
    public HashMap<String, String> extras;
    public String sessionId;
    public String type;
    public String uniqueId;
    public long updateTime;
    public String widgetClass;
    public String widgetPackage;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.uniqueId);
        parcel.writeString(this.businessId);
        parcel.writeString(this.business);
        parcel.writeString(this.type);
        parcel.writeString(this.detailType);
        parcel.writeString(this.sessionId);
        parcel.writeString(this.widgetPackage);
        parcel.writeString(this.widgetClass);
        parcel.writeLong(this.exposureDuration);
        parcel.writeLong(this.updateTime);
        parcel.writeInt(this.cardDisplayType);
        parcel.writeParcelable(this.cardRemoteViews, i);
        parcel.writeString(this.cardTemplateContent);
        parcel.writeInt(this.comparedFlag);
        parcel.writeInt(this.displayResult);
        parcel.writeMap(this.extras);
    }

    public void setWidgetPackage(String str) {
        this.widgetPackage = str;
    }

    public void setWidgetClass(String str) {
        this.widgetClass = str;
    }

    public void setUpdateTime(long j) {
        this.updateTime = j;
    }

    public void setUniqueId(String str) {
        this.uniqueId = str;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setTitle(String str) {
        this.extras.put("title", str);
    }

    public void setSessionId(String str) {
        this.sessionId = str;
    }

    public void setExtras(HashMap<String, String> hashMap) {
        this.extras = hashMap;
    }

    public void setExposureDuration(long j) {
        this.exposureDuration = j;
    }

    public void setDisplayResult(int i) {
        this.displayResult = i;
    }

    public void setDetailType(String str) {
        this.detailType = str;
    }

    public void setComparedFlag(int i) {
        this.comparedFlag = i;
    }

    public void setCardTemplateContent(String str) {
        this.cardTemplateContent = str;
    }

    public void setCardRemoteViews(RemoteViews remoteViews) {
        this.cardRemoteViews = remoteViews;
    }

    public void setCardDisplayType(int i) {
        this.cardDisplayType = i;
    }

    public void setBusinessId(String str) {
        this.businessId = str;
    }

    public void setBusiness(String str) {
        this.business = str;
    }

    public String getWidgetPackage() {
        return this.widgetPackage;
    }

    public String getWidgetClass() {
        return this.widgetClass;
    }

    public long getUpdateTime() {
        return this.updateTime;
    }

    public String getUniqueId() {
        return this.uniqueId;
    }

    public String getType() {
        return this.type;
    }

    public String getTitle() {
        return this.extras.get("title");
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public HashMap<String, String> getExtras() {
        return this.extras;
    }

    public long getExposureDuration() {
        return this.exposureDuration;
    }

    public int getDisplayResult() {
        return this.displayResult;
    }

    public String getDetailType() {
        return this.detailType;
    }

    public String getDescriptor() {
        String str;
        HashMap<String, String> hashMap = this.extras;
        return (hashMap == null || (str = hashMap.get(CardMgrSdkConst.CardInfoDescEX.DISPLAY_SIZE)) == null || str.length() == 0) ? "normal" : str;
    }

    public int getComparedFlag() {
        return this.comparedFlag;
    }

    public String getCardTemplateContent() {
        return this.cardTemplateContent;
    }

    public RemoteViews getCardRemoteViews() {
        return this.cardRemoteViews;
    }

    public int getCardDisplayType() {
        return this.cardDisplayType;
    }

    public String getBusinessId() {
        return this.businessId;
    }

    public String getBusiness() {
        return this.business;
    }

    public void addExtras(String str, String str2) {
        if (this.extras == null) {
            this.extras = new HashMap<>();
        }
        this.extras.put(str, str2);
    }

    public CardInfo(String str, long j) {
        this.cardDisplayType = 0;
        this.comparedFlag = -1;
        this.uniqueId = str;
        this.updateTime = j;
    }

    public CardInfo(Parcel parcel) {
        this.cardDisplayType = 0;
        this.comparedFlag = -1;
        this.uniqueId = parcel.readString();
        this.businessId = parcel.readString();
        this.business = parcel.readString();
        this.type = parcel.readString();
        this.detailType = parcel.readString();
        this.sessionId = parcel.readString();
        this.widgetPackage = parcel.readString();
        this.widgetClass = parcel.readString();
        this.exposureDuration = parcel.readLong();
        this.updateTime = parcel.readLong();
        this.cardDisplayType = parcel.readInt();
        this.cardRemoteViews = (RemoteViews) parcel.readParcelable(RemoteViews.class.getClassLoader());
        this.cardTemplateContent = parcel.readString();
        this.comparedFlag = parcel.readInt();
        this.displayResult = parcel.readInt();
        this.extras = parcel.readHashMap(CardInfo.class.getClassLoader());
    }

    public CardInfo() {
        this.cardDisplayType = 0;
        this.comparedFlag = -1;
    }
}
