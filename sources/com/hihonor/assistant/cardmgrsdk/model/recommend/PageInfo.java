package com.hihonor.assistant.cardmgrsdk.model.recommend;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes8.dex */
public class PageInfo implements Parcelable {
    public static final Parcelable.Creator<PageInfo> CREATOR = new Parcelable.Creator<PageInfo>() { // from class: com.hihonor.assistant.cardmgrsdk.model.recommend.PageInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PageInfo[] newArray(int i) {
            return new PageInfo[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PageInfo createFromParcel(Parcel parcel) {
            return new PageInfo(parcel);
        }
    };
    public List<AppInfo> appList;
    public int appSize;
    public List<Integer> cardTypeList;
    public long pageId;
    public List<WidgetInfo> recoWidgetList;
    public List<RecommendWidget> recoWidgets;
    public List<WidgetInfo> widgetList;
    public int widgetSize;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.pageId);
        parcel.writeInt(this.appSize);
        parcel.writeTypedList(this.recoWidgets);
        parcel.writeInt(this.widgetSize);
        parcel.writeTypedList(this.appList);
        parcel.writeTypedList(this.widgetList);
        parcel.writeTypedList(this.recoWidgetList);
    }

    public void setWidgetSize(int i) {
        this.widgetSize = i;
    }

    public void setWidgetList(List<WidgetInfo> list) {
        this.widgetList = list;
    }

    public void setRecoWidgets(List<RecommendWidget> list) {
        this.recoWidgets = list;
    }

    public void setRecoWidgetList(List<WidgetInfo> list) {
        this.recoWidgetList = list;
    }

    public void setPageId(long j) {
        this.pageId = j;
    }

    public void setCardTypeList(List<Integer> list) {
        this.cardTypeList = list;
    }

    public void setAppSize(int i) {
        this.appSize = i;
    }

    public void setAppList(List<AppInfo> list) {
        this.appList = list;
    }

    public int getWidgetSize() {
        return this.widgetSize;
    }

    public List<WidgetInfo> getWidgetList() {
        return this.widgetList;
    }

    public List<RecommendWidget> getRecoWidgets() {
        return this.recoWidgets;
    }

    public List<WidgetInfo> getRecoWidgetList() {
        return this.recoWidgetList;
    }

    public long getPageId() {
        return this.pageId;
    }

    public List<Integer> getCardTypeList() {
        return this.cardTypeList;
    }

    public int getAppSize() {
        return this.appSize;
    }

    public List<AppInfo> getAppList() {
        return this.appList;
    }

    public PageInfo(Parcel parcel) {
        this.pageId = parcel.readLong();
        this.appSize = parcel.readInt();
        this.recoWidgets = parcel.createTypedArrayList(RecommendWidget.CREATOR);
        this.widgetSize = parcel.readInt();
        this.appList = parcel.createTypedArrayList(AppInfo.CREATOR);
        this.widgetList = parcel.createTypedArrayList(WidgetInfo.CREATOR);
        this.recoWidgetList = parcel.createTypedArrayList(WidgetInfo.CREATOR);
    }

    public PageInfo() {
    }
}
