package com.huawei.pluginfitnessadvice;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import java.util.List;

/* loaded from: classes9.dex */
public class TrainAction implements Parcelable {
    public static final Parcelable.Creator<TrainAction> CREATOR = new Parcelable.Creator<TrainAction>() { // from class: com.huawei.pluginfitnessadvice.TrainAction.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: cme_, reason: merged with bridge method [inline-methods] */
        public TrainAction createFromParcel(Parcel parcel) {
            return new TrainAction(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public TrainAction[] newArray(int i) {
            return new TrainAction[i];
        }
    };
    private static final String TAG = "TrainAction";

    @SerializedName("covers")
    private List<Cover> mCovers;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("difficulty")
    private int mDifficulty;

    @SerializedName("equipments")
    private List<Attribute> mEquipments;

    @SerializedName("id")
    private String mId;

    @SerializedName("modified")
    private long mModified;

    @SerializedName("motionType")
    private String mMotionType;

    @SerializedName("name")
    private String mName;

    @SerializedName("trainingpoints")
    private List<Attribute> mTrainingPoints;

    @SerializedName(JsbMapKeyNames.H5_USER_ID)
    private String mUserId;

    @SerializedName("version")
    private String mVersion;

    @SerializedName("mVideos")
    private List<Video> mVideos;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public TrainAction() {
    }

    public TrainAction(String str, String str2) {
        this.mId = str;
        this.mName = str2;
    }

    public TrainAction(String str, String str2, String str3) {
        this.mMotionType = str3;
        this.mId = str;
        this.mName = str2;
    }

    protected TrainAction(Parcel parcel) {
        if (parcel == null) {
            LogUtil.h(TAG, "TrainAction in == null");
            return;
        }
        this.mUserId = parcel.readString();
        this.mName = parcel.readString();
        this.mId = parcel.readString();
        this.mMotionType = parcel.readString();
        this.mDifficulty = parcel.readInt();
        this.mCovers = parcel.createTypedArrayList(Cover.CREATOR);
        this.mDescription = parcel.readString();
        this.mEquipments = parcel.createTypedArrayList(Attribute.CREATOR);
        this.mTrainingPoints = parcel.createTypedArrayList(Attribute.CREATOR);
        this.mVideos = parcel.createTypedArrayList(Video.CREATOR);
        this.mVersion = parcel.readString();
        this.mModified = parcel.readLong();
    }

    public String acquireName() {
        return this.mName;
    }

    public void saveName(String str) {
        this.mName = str;
    }

    public String acquireId() {
        return this.mId;
    }

    public void saveId(String str) {
        this.mId = str;
    }

    public String acquireMotionType() {
        return this.mMotionType;
    }

    public void saveMotionType(String str) {
        this.mMotionType = str;
    }

    public int acquireDifficulty() {
        return this.mDifficulty;
    }

    public void saveDifficulty(int i) {
        this.mDifficulty = i;
    }

    public String acquireDescription() {
        return this.mDescription;
    }

    public void saveDescription(String str) {
        this.mDescription = str;
    }

    public List<Attribute> acquireEquipments() {
        return this.mEquipments;
    }

    public void saveEquipments(List<Attribute> list) {
        this.mEquipments = list;
    }

    public List<Attribute> acquireTrainingpoints() {
        return this.mTrainingPoints;
    }

    public void saveTrainingpoints(List<Attribute> list) {
        this.mTrainingPoints = list;
    }

    public String acquireVersion() {
        return this.mVersion;
    }

    public void saveVersion(String str) {
        this.mVersion = str;
    }

    public long acquireModified() {
        return this.mModified;
    }

    public void saveModified(long j) {
        this.mModified = j;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            LogUtil.h(TAG, "writeToParcel dest == null");
            return;
        }
        parcel.writeString(this.mUserId);
        parcel.writeString(this.mName);
        parcel.writeString(this.mId);
        parcel.writeString(this.mMotionType);
        parcel.writeInt(this.mDifficulty);
        parcel.writeTypedList(this.mCovers);
        parcel.writeString(this.mDescription);
        parcel.writeTypedList(this.mEquipments);
        parcel.writeTypedList(this.mTrainingPoints);
        parcel.writeTypedList(this.mVideos);
        parcel.writeString(this.mVersion);
        parcel.writeLong(this.mModified);
    }

    public String acquireUserId() {
        return this.mUserId;
    }

    public void saveUserId(String str) {
        this.mUserId = str;
    }

    public List<Cover> acquireCovers() {
        return this.mCovers;
    }

    public void saveCovers(List<Cover> list) {
        this.mCovers = list;
    }

    public List<Video> acquireVideos() {
        return this.mVideos;
    }

    public void saveVideos(List<Video> list) {
        this.mVideos = list;
    }
}
