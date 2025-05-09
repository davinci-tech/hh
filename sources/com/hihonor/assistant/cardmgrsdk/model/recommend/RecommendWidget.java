package com.hihonor.assistant.cardmgrsdk.model.recommend;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes8.dex */
public class RecommendWidget implements Parcelable {
    public static final Parcelable.Creator<RecommendWidget> CREATOR = new Parcelable.Creator<RecommendWidget>() { // from class: com.hihonor.assistant.cardmgrsdk.model.recommend.RecommendWidget.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RecommendWidget[] newArray(int i) {
            return new RecommendWidget[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RecommendWidget createFromParcel(Parcel parcel) {
            return new RecommendWidget(parcel);
        }
    };
    public List<AppInfo> appList;
    public int id;
    public int type;
    public List<WidgetInfo> widgetInfoList;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.type);
        parcel.writeInt(this.id);
        parcel.writeTypedList(this.appList);
        parcel.writeTypedList(this.widgetInfoList);
    }

    public String toString() {
        return "RecommendWidget{type='" + this.type + "', id=" + this.id + ", appList=" + this.appList + '}';
    }

    public void setWidgetInfoList(List<WidgetInfo> list) {
        this.widgetInfoList = list;
    }

    public void setType(int i) {
        this.type = i;
    }

    public void setId(int i) {
        this.id = i;
    }

    public void setAppList(List<AppInfo> list) {
        this.appList = list;
    }

    public List<WidgetInfo> getWidgetInfoList() {
        return this.widgetInfoList;
    }

    public int getType() {
        return this.type;
    }

    public int getId() {
        return this.id;
    }

    public List<AppInfo> getAppList() {
        return this.appList;
    }

    public RecommendWidget(Parcel parcel) {
        this.type = parcel.readInt();
        this.id = parcel.readInt();
        this.appList = parcel.createTypedArrayList(AppInfo.CREATOR);
        this.widgetInfoList = parcel.createTypedArrayList(WidgetInfo.CREATOR);
    }

    public RecommendWidget() {
    }
}
