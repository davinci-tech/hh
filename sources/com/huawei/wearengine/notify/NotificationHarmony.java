package com.huawei.wearengine.notify;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.tos;
import defpackage.tpq;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class NotificationHarmony implements Parcelable {
    public static final Parcelable.Creator<NotificationHarmony> CREATOR = new Parcelable.Creator<NotificationHarmony>() { // from class: com.huawei.wearengine.notify.NotificationHarmony.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: fdE_, reason: merged with bridge method [inline-methods] */
        public NotificationHarmony createFromParcel(Parcel parcel) {
            return new NotificationHarmony(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public NotificationHarmony[] newArray(int i) {
            return (i > 65535 || i < 0) ? new NotificationHarmony[0] : new NotificationHarmony[i];
        }
    };
    private static final int DEFAULT_ID = -1;
    private static final String TAG = "NotificationHarmony";
    private HashMap<Integer, String> mButtonContents;
    private List<String> mButtonContentsConstraint;
    private List<String> mButtonContentsStr;
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

    public NotificationHarmony(tpq tpqVar) {
        this.mButtonContentsConstraint = new ArrayList(0);
        this.mButtonContentsStr = new ArrayList(0);
        this.mButtonContents = new HashMap<>(0);
        this.mRingtoneId = -1;
        this.mExtendJson = "";
        if (tpqVar == null) {
            return;
        }
        this.mTemplateId = tpqVar.e();
        this.mPackageName = tpqVar.d();
        this.mTitle = tpqVar.h();
        this.mText = tpqVar.c();
        setButtonContents(tpqVar.b());
    }

    protected NotificationHarmony(Parcel parcel) {
        this.mButtonContentsConstraint = new ArrayList(0);
        this.mButtonContentsStr = new ArrayList(0);
        this.mButtonContents = new HashMap<>(0);
        this.mRingtoneId = -1;
        this.mExtendJson = "";
        if (parcel == null) {
            return;
        }
        this.mTemplateId = parcel.readInt();
        this.mPackageName = parcel.readString();
        this.mTitle = parcel.readString();
        this.mText = parcel.readString();
        parcel.readStringList(this.mButtonContentsConstraint);
        parcel.readStringList(this.mButtonContentsStr);
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
        List<String> list = this.mButtonContentsConstraint;
        if (list == null || this.mButtonContentsStr == null) {
            return new HashMap<>(0);
        }
        if (list.size() != this.mButtonContentsStr.size()) {
            return new HashMap<>(0);
        }
        for (int i = 0; i < this.mButtonContentsConstraint.size(); i++) {
            try {
                this.mButtonContents.put(Integer.valueOf(Integer.parseInt(this.mButtonContentsConstraint.get(i))), this.mButtonContentsStr.get(i));
            } catch (NumberFormatException unused) {
                tos.e(TAG, "getButtonContents NumberFormatException");
            }
        }
        return this.mButtonContents;
    }

    public int getVibration() {
        return this.mVibration;
    }

    public int getRingtoneId() {
        return this.mRingtoneId;
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
        parcel.writeStringList(this.mButtonContentsConstraint);
        parcel.writeStringList(this.mButtonContentsStr);
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
        parcel.readStringList(this.mButtonContentsConstraint);
        parcel.readStringList(this.mButtonContentsStr);
        this.mVibration = parcel.readInt();
        this.mRingtoneId = parcel.readInt();
        this.mExtendJson = parcel.readString();
    }

    private void setButtonContents(HashMap<Integer, String> hashMap) {
        if (hashMap == null || hashMap.isEmpty()) {
            return;
        }
        for (Map.Entry<Integer, String> entry : hashMap.entrySet()) {
            this.mButtonContentsConstraint.add(String.valueOf(entry.getKey()));
            this.mButtonContentsStr.add(entry.getValue());
        }
    }
}
