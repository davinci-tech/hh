package com.huawei.phdkit;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.mbm;

/* loaded from: classes9.dex */
public class Notification implements Parcelable {
    public static final Parcelable.Creator<Notification> CREATOR = new Parcelable.Creator<Notification>() { // from class: com.huawei.phdkit.Notification.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public Notification[] newArray(int i) {
            return (i > 65535 || i < 0) ? new Notification[0] : new Notification[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: ccP_, reason: merged with bridge method [inline-methods] */
        public Notification createFromParcel(Parcel parcel) {
            Notification notification = new Notification();
            if (parcel != null) {
                notification.setPackageName(parcel.readString());
                notification.setTemplate(parcel.readInt());
                notification.setIconId(parcel.readInt());
                notification.setTitle(parcel.readString());
                notification.setSubtitle(parcel.readString());
                notification.setContent(parcel.readString());
                notification.setDate(parcel.readLong());
                notification.setGuideDistance(parcel.readInt());
                notification.setGuideDistanceUnit(parcel.readString());
                notification.setGuideDirectionId(parcel.readInt());
                notification.setGuideText(parcel.readString());
                notification.setVibrate(parcel.readInt());
            }
            return notification;
        }
    };
    private static final int STR_THRID = 16;
    private static final String TAG = "Notification";
    private String mContent;
    private long mDate;
    private int mGuideDirectionId;
    private int mGuideDistance;
    private String mGuideDistanceUnit;
    private String mGuideText;
    private int mIconId;
    private String mPackageName;
    private String mSubtitle;
    private int mTemplate;
    private String mTitle;
    private int mVibrate;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getPackageName() {
        return (String) mbm.c(this.mPackageName);
    }

    public void setPackageName(String str) {
        this.mPackageName = (String) mbm.c(str);
    }

    public int getTemplate() {
        return ((Integer) mbm.c(Integer.valueOf(this.mTemplate))).intValue();
    }

    public void setTemplate(int i) {
        this.mTemplate = ((Integer) mbm.c(Integer.valueOf(i))).intValue();
    }

    public int getIconId() {
        return ((Integer) mbm.c(Integer.valueOf(this.mIconId))).intValue();
    }

    public void setIconId(int i) {
        this.mIconId = ((Integer) mbm.c(Integer.valueOf(i))).intValue();
    }

    public String getTitle() {
        return (String) mbm.c(this.mTitle);
    }

    public void setTitle(String str) {
        this.mTitle = (String) mbm.c(str);
    }

    public String getSubtitle() {
        return (String) mbm.c(this.mSubtitle);
    }

    public void setSubtitle(String str) {
        this.mSubtitle = (String) mbm.c(str);
    }

    public String getContent() {
        return (String) mbm.c(this.mContent);
    }

    public void setContent(String str) {
        this.mContent = (String) mbm.c(str);
    }

    public long getDate() {
        return ((Long) mbm.c(Long.valueOf(this.mDate))).longValue();
    }

    public void setDate(long j) {
        this.mDate = ((Long) mbm.c(Long.valueOf(j))).longValue();
    }

    public int getGuideDistance() {
        return ((Integer) mbm.c(Integer.valueOf(this.mGuideDistance))).intValue();
    }

    public void setGuideDistance(int i) {
        this.mGuideDistance = ((Integer) mbm.c(Integer.valueOf(i))).intValue();
    }

    public String getGuideDistanceUnit() {
        return (String) mbm.c(this.mGuideDistanceUnit);
    }

    public void setGuideDistanceUnit(String str) {
        this.mGuideDistanceUnit = (String) mbm.c(str);
    }

    public int getGuideDirectionId() {
        return ((Integer) mbm.c(Integer.valueOf(this.mGuideDirectionId))).intValue();
    }

    public void setGuideDirectionId(int i) {
        this.mGuideDirectionId = ((Integer) mbm.c(Integer.valueOf(i))).intValue();
    }

    public String getGuideText() {
        return (String) mbm.c(this.mGuideText);
    }

    public void setGuideText(String str) {
        this.mGuideText = (String) mbm.c(str);
    }

    public int getVibrate() {
        return ((Integer) mbm.c(Integer.valueOf(this.mVibrate))).intValue();
    }

    public void setVibrate(int i) {
        this.mVibrate = ((Integer) mbm.c(Integer.valueOf(i))).intValue();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeString(this.mPackageName);
        parcel.writeInt(this.mTemplate);
        parcel.writeInt(this.mIconId);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mSubtitle);
        parcel.writeString(this.mContent);
        parcel.writeLong(this.mDate);
        parcel.writeInt(this.mGuideDistance);
        parcel.writeString(this.mGuideDistanceUnit);
        parcel.writeInt(this.mGuideDirectionId);
        parcel.writeString(this.mGuideText);
        parcel.writeInt(this.mVibrate);
    }

    public void readFromParcel(Parcel parcel) {
        if (parcel == null) {
            return;
        }
        this.mPackageName = parcel.readString();
        this.mTemplate = parcel.readInt();
        this.mIconId = parcel.readInt();
        this.mTitle = parcel.readString();
        this.mSubtitle = parcel.readString();
        this.mContent = parcel.readString();
        this.mDate = parcel.readLong();
        this.mGuideDistance = parcel.readInt();
        this.mGuideDistanceUnit = parcel.readString();
        this.mGuideDirectionId = parcel.readInt();
        this.mGuideText = parcel.readString();
        this.mVibrate = parcel.readInt();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("[mPackageNames:'");
        sb.append(this.mPackageName);
        sb.append("',mTemplate:'");
        sb.append(this.mTemplate);
        sb.append("',mIconId'");
        sb.append(this.mIconId);
        sb.append(",mTitles:'");
        sb.append(this.mTitle);
        sb.append("',mSubtitles:'");
        sb.append(this.mSubtitle);
        sb.append("',mContents'");
        sb.append(this.mContent);
        sb.append("',mDate:'");
        sb.append(this.mDate);
        sb.append("',mGuideDistance:'");
        sb.append(this.mGuideDistance);
        sb.append("',mGuideDistanceUnits'");
        sb.append(this.mGuideDistanceUnit);
        sb.append("',mGuideDirectionId:'");
        sb.append(this.mGuideDirectionId);
        sb.append("',mGuideTexts:'");
        sb.append(this.mGuideText);
        sb.append("',mVibrate'");
        sb.append(this.mVibrate);
        sb.append("'}");
        return sb.toString();
    }
}
