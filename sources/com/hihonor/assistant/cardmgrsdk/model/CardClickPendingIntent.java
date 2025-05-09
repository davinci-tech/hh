package com.hihonor.assistant.cardmgrsdk.model;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes8.dex */
public class CardClickPendingIntent implements Parcelable {
    public static final Parcelable.Creator<CardClickPendingIntent> CREATOR = new Parcelable.Creator<CardClickPendingIntent>() { // from class: com.hihonor.assistant.cardmgrsdk.model.CardClickPendingIntent.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CardClickPendingIntent[] newArray(int i) {
            return new CardClickPendingIntent[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CardClickPendingIntent createFromParcel(Parcel parcel) {
            return new CardClickPendingIntent(parcel);
        }
    };
    public Intent intent;
    public int type;
    public int viewId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.viewId);
        parcel.writeInt(this.type);
        parcel.writeParcelable(this.intent, i);
    }

    public void setViewId(int i) {
        this.viewId = i;
    }

    public void setType(int i) {
        this.type = i;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public int getViewId() {
        return this.viewId;
    }

    public int getType() {
        return this.type;
    }

    public Intent getIntent() {
        return this.intent;
    }

    public CardClickPendingIntent(Parcel parcel) {
        this.viewId = parcel.readInt();
        this.type = parcel.readInt();
        this.intent = (Intent) parcel.readParcelable(Intent.class.getClassLoader());
    }

    public CardClickPendingIntent(int i, int i2, Intent intent) {
        this.viewId = i;
        this.type = i2;
        this.intent = intent;
    }

    public CardClickPendingIntent() {
    }
}
