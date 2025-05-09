package com.huawei.phoneservice.feedbackcommon.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/* loaded from: classes5.dex */
public class ProblemInfo implements Parcelable {
    public static final Parcelable.Creator<ProblemInfo> CREATOR = new a();
    public static final int FEEDBACK = 0;
    public static final int FEEDBACK_BIG_SCREEN = 3;
    public static final int FEEDBACK_DETAIL = 1;
    public static final int FEEDBACK_INTERACT = 2;
    public static final String FEEDBACK_INTERACT_TYPE = "DLHD";
    private String contact;
    private int fromPage;
    private String problemId;
    private String problemName;
    private String problemType;
    private int refresh;
    private String srCode;
    private int unread;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.fromPage);
        parcel.writeString(this.srCode);
        parcel.writeString(this.problemId);
        parcel.writeString(this.problemType);
        parcel.writeString(this.problemName);
        parcel.writeString(this.contact);
        parcel.writeInt(this.unread);
        parcel.writeInt(this.refresh);
    }

    public void setUnread(int i) {
        this.unread = i;
    }

    public void setSrCode(String str) {
        this.srCode = str;
    }

    public void setRefresh(int i) {
        this.refresh = i;
    }

    public void setProblemType(String str) {
        this.problemType = str;
    }

    public void setProblemName(String str) {
        this.problemName = str;
    }

    public void setProblemId(String str) {
        this.problemId = str;
    }

    public void setFromPage(int i) {
        this.fromPage = i;
    }

    public void setContact(String str) {
        this.contact = str;
    }

    public boolean isInteract() {
        return this.fromPage == 2 && !TextUtils.isEmpty(this.srCode);
    }

    public boolean isDetail() {
        return this.fromPage == 1 && !TextUtils.isEmpty(this.problemId);
    }

    public boolean isBigScreen() {
        return this.fromPage == 3;
    }

    public int getUnread() {
        return this.unread;
    }

    public String getSrCode() {
        return this.srCode;
    }

    public int getRefresh() {
        return this.refresh;
    }

    public String getProblemType() {
        return this.problemType;
    }

    public String getProblemName() {
        return this.problemName;
    }

    public String getProblemId() {
        return this.problemId;
    }

    public int getFromPage() {
        return this.fromPage;
    }

    public String getContact() {
        return this.contact;
    }

    public ProblemInfo(String str, String str2, String str3) {
        this.fromPage = 2;
        this.srCode = str;
        this.problemId = str2;
        this.problemType = FEEDBACK_INTERACT_TYPE;
        this.problemName = str3;
    }

    public ProblemInfo(String str, String str2) {
        this.fromPage = 1;
        this.problemId = str;
        this.problemType = str2;
    }

    class a implements Parcelable.Creator<ProblemInfo> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public ProblemInfo[] newArray(int i) {
            return new ProblemInfo[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: cfk_, reason: merged with bridge method [inline-methods] */
        public ProblemInfo createFromParcel(Parcel parcel) {
            return new ProblemInfo(parcel);
        }

        a() {
        }
    }

    protected ProblemInfo(Parcel parcel) {
        this.fromPage = parcel.readInt();
        this.srCode = parcel.readString();
        this.problemId = parcel.readString();
        this.problemType = parcel.readString();
        this.problemName = parcel.readString();
        this.contact = parcel.readString();
        this.unread = parcel.readInt();
        this.refresh = parcel.readInt();
    }

    public ProblemInfo(int i) {
        this.fromPage = 0;
        this.unread = i;
    }

    public ProblemInfo() {
    }
}
