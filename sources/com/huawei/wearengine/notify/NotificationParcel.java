package com.huawei.wearengine.notify;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.tpq;
import java.util.HashMap;

/* loaded from: classes7.dex */
public class NotificationParcel implements Parcelable {
    public static final Parcelable.Creator<NotificationParcel> CREATOR = new Parcelable.Creator<NotificationParcel>() { // from class: com.huawei.wearengine.notify.NotificationParcel.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: fdF_, reason: merged with bridge method [inline-methods] */
        public NotificationParcel createFromParcel(Parcel parcel) {
            return new NotificationParcel(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public NotificationParcel[] newArray(int i) {
            return (i > 65535 || i < 0) ? new NotificationParcel[0] : new NotificationParcel[i];
        }
    };
    private HashMap<Integer, String> mButtonContents;
    private String mExtendJson;
    private String mPackageName;
    private int mRingtoneId;
    private int mTemplateId;
    private String mText;
    private String mTitle;
    private int mVibration;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public NotificationParcel(tpq tpqVar) {
        this.mRingtoneId = -1;
        this.mExtendJson = "";
        if (tpqVar == null) {
            return;
        }
        this.mTemplateId = tpqVar.e();
        this.mPackageName = tpqVar.d();
        this.mTitle = tpqVar.h();
        this.mText = tpqVar.c();
        this.mButtonContents = tpqVar.b();
    }

    protected NotificationParcel(Parcel parcel) {
        this.mRingtoneId = -1;
        this.mExtendJson = "";
        if (parcel == null) {
            return;
        }
        this.mTemplateId = parcel.readInt();
        this.mPackageName = parcel.readString();
        this.mTitle = parcel.readString();
        this.mText = parcel.readString();
        this.mButtonContents = parcel.readHashMap(HashMap.class.getClassLoader());
        this.mVibration = parcel.readInt();
        this.mRingtoneId = parcel.readInt();
        this.mExtendJson = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeInt(this.mTemplateId);
        parcel.writeString(this.mPackageName);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mText);
        parcel.writeMap(this.mButtonContents);
        parcel.writeInt(this.mVibration);
        parcel.writeInt(this.mRingtoneId);
        parcel.writeString(this.mExtendJson);
    }

    public void readFromParcel(Parcel parcel) {
        if (parcel == null) {
            return;
        }
        this.mTemplateId = parcel.readInt();
        this.mPackageName = parcel.readString();
        this.mTitle = parcel.readString();
        this.mText = parcel.readString();
        this.mButtonContents = parcel.readHashMap(HashMap.class.getClassLoader());
        this.mVibration = parcel.readInt();
        this.mRingtoneId = parcel.readInt();
        this.mExtendJson = parcel.readString();
    }

    public int getTemplateId() {
        return this.mTemplateId;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getText() {
        return this.mText;
    }

    public HashMap<Integer, String> getButtonContents() {
        return this.mButtonContents;
    }

    public int getVibration() {
        return this.mVibration;
    }

    public int getRingtoneId() {
        return this.mRingtoneId;
    }
}
