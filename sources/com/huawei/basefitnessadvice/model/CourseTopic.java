package com.huawei.basefitnessadvice.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.pluginachievement.manager.db.IAchieveDBMgr;
import java.util.List;

/* loaded from: classes8.dex */
public class CourseTopic implements Parcelable {
    public static final Parcelable.Creator<CourseTopic> CREATOR = new Parcelable.Creator<CourseTopic>() { // from class: com.huawei.basefitnessadvice.model.CourseTopic.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CourseTopic createFromParcel(Parcel parcel) {
            return new CourseTopic(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CourseTopic[] newArray(int i) {
            return new CourseTopic[i];
        }
    };

    @SerializedName("hasMore")
    private boolean mHasMore;

    @SerializedName(IAchieveDBMgr.PARAM_PAGE_SIZE)
    private int mPageSize;

    @SerializedName("resultCode")
    private String mResultCode;

    @SerializedName("resultDesc")
    private String mResultDesc;

    @SerializedName("topicList")
    private List<Topic> mTopicList;

    @SerializedName("total")
    private int mTotal;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected CourseTopic(Parcel parcel) {
        this.mResultCode = parcel.readString();
        this.mResultDesc = parcel.readString();
        this.mTotal = parcel.readInt();
        this.mPageSize = parcel.readInt();
        this.mHasMore = parcel.readByte() != 0;
        this.mTopicList = parcel.createTypedArrayList(Topic.CREATOR);
    }

    public CourseTopic() {
    }

    public String getResultCode() {
        return this.mResultCode;
    }

    public void saveResultCode(String str) {
        this.mResultCode = str;
    }

    public String getResultDesc() {
        return this.mResultDesc;
    }

    public void saveResultDesc(String str) {
        this.mResultDesc = str;
    }

    public int getTotal() {
        return this.mTotal;
    }

    public void saveTotal(int i) {
        this.mTotal = i;
    }

    public int getPageSize() {
        return this.mPageSize;
    }

    public void savePageSize(int i) {
        this.mPageSize = i;
    }

    public boolean isHasMore() {
        return this.mHasMore;
    }

    public void saveHasMore(boolean z) {
        this.mHasMore = z;
    }

    public List<Topic> getTopicList() {
        return this.mTopicList;
    }

    public void saveTopicList(List<Topic> list) {
        this.mTopicList = list;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mResultCode);
        parcel.writeString(this.mResultDesc);
        parcel.writeInt(this.mTotal);
        parcel.writeInt(this.mPageSize);
        parcel.writeByte(this.mHasMore ? (byte) 1 : (byte) 0);
        parcel.writeTypedList(this.mTopicList);
    }
}
