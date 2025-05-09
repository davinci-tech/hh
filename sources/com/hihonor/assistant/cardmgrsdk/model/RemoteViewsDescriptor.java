package com.hihonor.assistant.cardmgrsdk.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.RemoteViews;

/* loaded from: classes8.dex */
public class RemoteViewsDescriptor implements Parcelable {
    public static final Parcelable.Creator<RemoteViewsDescriptor> CREATOR = new Parcelable.Creator<RemoteViewsDescriptor>() { // from class: com.hihonor.assistant.cardmgrsdk.model.RemoteViewsDescriptor.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RemoteViewsDescriptor[] newArray(int i) {
            return new RemoteViewsDescriptor[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RemoteViewsDescriptor createFromParcel(Parcel parcel) {
            return new RemoteViewsDescriptor(parcel);
        }
    };
    public RemoteViews remoteViews;
    public String sizeDescribe;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.sizeDescribe);
        parcel.writeParcelable(this.remoteViews, i);
    }

    public RemoteViewsDescriptor(RemoteViews remoteViews, String str) {
        this.remoteViews = remoteViews;
        this.sizeDescribe = str;
    }

    public RemoteViewsDescriptor(RemoteViews remoteViews) {
        this.remoteViews = remoteViews;
        this.sizeDescribe = "normal";
    }

    public RemoteViewsDescriptor(Parcel parcel) {
        this.sizeDescribe = parcel.readString();
        this.remoteViews = (RemoteViews) parcel.readParcelable(RemoteViews.class.getClassLoader());
    }
}
