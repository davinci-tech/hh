package com.hihonor.assistant.cardmgrsdk.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.hihonor.assistant.cardmgrsdk.CardMgrSdkConst;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes2.dex */
public class CardDisplayRequestArg implements Parcelable {
    public static final Parcelable.Creator<CardDisplayRequestArg> CREATOR = new Parcelable.Creator<CardDisplayRequestArg>() { // from class: com.hihonor.assistant.cardmgrsdk.model.CardDisplayRequestArg.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CardDisplayRequestArg[] newArray(int i) {
            return new CardDisplayRequestArg[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CardDisplayRequestArg createFromParcel(Parcel parcel) {
            return new CardDisplayRequestArg(parcel);
        }
    };
    public String business;
    public String businessId;
    public String businessParams;

    @Deprecated
    public RemoteViews cardRemoteViews;
    public List<ClickPendingIntent> clickPendingIntents;
    public String detailType;
    public long endTime;
    public HashMap<String, String> extras;
    public List<ImageEntity> imageEntities;

    @Deprecated
    public int operation;
    public String type;
    public long versionCode;
    public String viewData;
    public String widgetPackage;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.businessId);
        parcel.writeString(this.businessParams);
        parcel.writeLong(this.endTime);
        parcel.writeString(this.widgetPackage);
        parcel.writeInt(this.operation);
        parcel.writeString(this.business);
        parcel.writeString(this.type);
        parcel.writeString(this.detailType);
        parcel.writeParcelable(this.cardRemoteViews, i);
        parcel.writeString(this.viewData);
        parcel.writeLong(this.versionCode);
        parcel.writeTypedList(this.clickPendingIntents);
        parcel.writeTypedList(this.imageEntities);
        parcel.writeMap(this.extras);
    }

    public String toString() {
        return "CardDisplayRequestArg{businessId='" + this.businessId + "', businessParams='" + this.businessParams + "', endTime=" + this.endTime + ", widgetPackage='" + this.widgetPackage + "', operation=" + this.operation + ", business='" + this.business + "', type='" + this.type + "', detailType='" + this.detailType + "', cardRemoteViews=" + this.cardRemoteViews + ", viewData='" + this.viewData + "', versionCode=" + this.versionCode + ", clickPendingIntents=" + this.clickPendingIntents + ", imageEntities=" + this.imageEntities + ", extras=" + this.extras + '}';
    }

    public void setWidgetPackage(String str) {
        this.widgetPackage = str;
    }

    public void setWidgetClass(String str) {
        this.extras.put("widgetClass", str);
    }

    public void setViewData(String str) {
        this.viewData = str;
    }

    public void setVersionCode(long j) {
        this.versionCode = j;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setTitle(String str) {
        if (this.extras == null) {
            this.extras = new HashMap<>();
        }
        this.extras.put("title", str);
    }

    public void setSessionId(String str) {
        if (this.extras == null) {
            this.extras = new HashMap<>();
        }
        this.extras.put("sessionId", str);
    }

    @Deprecated
    public void setOperation(int i) {
        this.operation = i;
    }

    public void setImageEntities(List<ImageEntity> list) {
        this.imageEntities = list;
    }

    public void setExtras(HashMap<String, String> hashMap) {
        this.extras = hashMap;
    }

    public void setEndTime(long j) {
        this.endTime = j;
    }

    public void setDisplaySize(String str) {
        this.extras.put(CardMgrSdkConst.CardInfoDescEX.DISPLAY_SIZE, str);
    }

    public void setDetailType(String str) {
        this.detailType = str;
    }

    public void setClickPendingIntents(List<ClickPendingIntent> list) {
        this.clickPendingIntents = list;
    }

    @Deprecated
    public void setCardRemoteViews(RemoteViews remoteViews) {
        this.cardRemoteViews = remoteViews;
    }

    public void setBusinessParams(String str) {
        this.businessParams = str;
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
        if (this.extras == null) {
            this.extras = new HashMap<>();
        }
        return this.extras.get("widgetClass");
    }

    public String getViewData() {
        return this.viewData;
    }

    public long getVersionCode() {
        return this.versionCode;
    }

    public String getType() {
        return this.type;
    }

    public String getTitle() {
        if (this.extras == null) {
            this.extras = new HashMap<>();
        }
        return this.extras.get("title");
    }

    public String getSessionId() {
        if (this.extras == null) {
            this.extras = new HashMap<>();
        }
        return this.extras.get("sessionId");
    }

    public int getOperation() {
        return this.operation;
    }

    public List<ImageEntity> getImageEntities() {
        return this.imageEntities;
    }

    public HashMap<String, String> getExtras() {
        return this.extras;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public String getDisplaySize() {
        if (this.extras == null) {
            this.extras = new HashMap<>();
        }
        String str = this.extras.get(CardMgrSdkConst.CardInfoDescEX.DISPLAY_SIZE);
        return TextUtils.isEmpty(str) ? "normal" : str;
    }

    public String getDetailType() {
        return this.detailType;
    }

    public List<ClickPendingIntent> getClickPendingIntents() {
        return this.clickPendingIntents;
    }

    @Deprecated
    public RemoteViews getCardRemoteViews() {
        return this.cardRemoteViews;
    }

    public String getBusinessParams() {
        return this.businessParams;
    }

    public String getBusinessId() {
        return this.businessId;
    }

    public String getBusiness() {
        return this.business;
    }

    public CardDisplayRequestArg(String str, String str2, int i) {
        this.extras = new HashMap<>();
        this.businessId = str2;
        this.business = str;
        this.operation = i;
    }

    public CardDisplayRequestArg(CardDispReqBuilder cardDispReqBuilder) {
        this.extras = new HashMap<>();
        this.businessId = cardDispReqBuilder.getBusinessId();
        this.business = cardDispReqBuilder.getBusiness();
        this.operation = cardDispReqBuilder.getOperation();
        this.businessParams = cardDispReqBuilder.getBusinessParams();
        this.endTime = cardDispReqBuilder.getEndTime();
        this.widgetPackage = cardDispReqBuilder.getWidgetPackage();
        this.type = cardDispReqBuilder.getType();
        this.detailType = cardDispReqBuilder.getDetailType();
        this.cardRemoteViews = cardDispReqBuilder.getCardRemoteViews();
        this.viewData = cardDispReqBuilder.getViewData();
        this.versionCode = cardDispReqBuilder.getVersionCode();
        this.clickPendingIntents = cardDispReqBuilder.getClickPendingIntents();
        this.imageEntities = cardDispReqBuilder.getImageEntities();
        HashMap<String, String> extras = cardDispReqBuilder.getExtras();
        this.extras = extras;
        if (extras == null) {
            this.extras = new HashMap<>();
        }
    }

    public CardDisplayRequestArg(Parcel parcel) {
        this.extras = new HashMap<>();
        this.businessId = parcel.readString();
        this.businessParams = parcel.readString();
        this.endTime = parcel.readLong();
        this.widgetPackage = parcel.readString();
        this.operation = parcel.readInt();
        this.business = parcel.readString();
        this.type = parcel.readString();
        this.detailType = parcel.readString();
        this.cardRemoteViews = (RemoteViews) parcel.readParcelable(RemoteViews.class.getClassLoader());
        this.viewData = parcel.readString();
        this.versionCode = parcel.readLong();
        this.clickPendingIntents = parcel.createTypedArrayList(ClickPendingIntent.CREATOR);
        this.imageEntities = parcel.createTypedArrayList(ImageEntity.CREATOR);
        HashMap<String, String> readHashMap = parcel.readHashMap(getClass().getClassLoader());
        this.extras = readHashMap;
        if (readHashMap == null) {
            this.extras = new HashMap<>();
        }
    }
}
