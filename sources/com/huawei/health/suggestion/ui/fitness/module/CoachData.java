package com.huawei.health.suggestion.ui.fitness.module;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.Attribute;
import com.huawei.pluginfitnessadvice.Classify;
import defpackage.koq;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class CoachData implements Parcelable {
    private static final int BREATH_CLASSIFY_ID = 15101;
    public static final Parcelable.Creator<CoachData> CREATOR = new Parcelable.Creator<CoachData>() { // from class: com.huawei.health.suggestion.ui.fitness.module.CoachData.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: aCQ_, reason: merged with bridge method [inline-methods] */
        public CoachData createFromParcel(Parcel parcel) {
            return new CoachData(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public CoachData[] newArray(int i) {
            return new CoachData[i];
        }
    };
    private static final String TAG = "Suggestion_CoachData";
    private String detail;
    private int difficulty;
    private int duration;
    private List<Attribute> equipments;
    private String finishDate;
    private int gender;
    private boolean isPlan;
    private String mBackgroundMusicUrl;
    private int mCalorieStartTime;
    private List<Classify> mPrimaryClassify;
    private List<Motion> motions;
    private String picture;
    private String planId;
    private int planType;
    private String startDate;
    private String tag;
    private List<Attribute> trainingpoints;
    private String workId;
    private String workOutName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private CoachData(Parcel parcel) {
        this.planId = parcel.readString();
        this.duration = parcel.readInt();
        this.tag = parcel.readString();
        this.workOutName = parcel.readString();
        this.workId = parcel.readString();
        this.isPlan = parcel.readByte() != 0;
        this.startDate = parcel.readString();
        this.finishDate = parcel.readString();
        this.equipments = parcel.createTypedArrayList(Attribute.CREATOR);
        this.trainingpoints = parcel.createTypedArrayList(Attribute.CREATOR);
        this.difficulty = parcel.readInt();
        this.detail = parcel.readString();
        this.picture = parcel.readString();
        this.gender = parcel.readInt();
        this.planType = parcel.readInt();
        this.motions = parcel.createTypedArrayList(Motion.CREATOR);
        this.mPrimaryClassify = parcel.createTypedArrayList(Classify.CREATOR);
        this.mBackgroundMusicUrl = parcel.readString();
        this.mCalorieStartTime = parcel.readInt();
    }

    public CoachData() {
    }

    public String getPlanId() {
        return this.planId;
    }

    public void savePlanId(String str) {
        this.planId = str;
    }

    public void saveWorkId(String str) {
        this.workId = str;
    }

    public String acquireWorkId() {
        return this.workId;
    }

    public boolean isPlan() {
        return this.isPlan;
    }

    public void setPlan(boolean z) {
        this.isPlan = z;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String str) {
        this.startDate = str;
    }

    public List<Motion> acquireMotions() {
        return this.motions;
    }

    public void saveMotions(List<Motion> list) {
        this.motions = list;
    }

    public List<Attribute> getEquipments() {
        return this.equipments;
    }

    public void setEquipments(List<Attribute> list) {
        this.equipments = list;
    }

    public List<Attribute> getTrainingpoints() {
        return this.trainingpoints;
    }

    public void setTrainingpoints(List<Attribute> list) {
        this.trainingpoints = list;
    }

    public int getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(int i) {
        this.difficulty = i;
    }

    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String str) {
        this.detail = str;
    }

    public String getPicture() {
        return this.picture;
    }

    public int acquireDuration() {
        return this.duration;
    }

    public void saveDuration(int i) {
        this.duration = i;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String str) {
        this.tag = str;
    }

    public void setPicture(String str) {
        this.picture = str;
    }

    public int getGender() {
        return this.gender;
    }

    public void setGender(int i) {
        this.gender = i;
    }

    public List<Classify> getPrimaryClassify() {
        return this.mPrimaryClassify;
    }

    public void setPrimaryClassify(List<Classify> list) {
        this.mPrimaryClassify = list;
    }

    public boolean isBreathCourse() {
        if (koq.b(this.mPrimaryClassify)) {
            return false;
        }
        Iterator<Classify> it = this.mPrimaryClassify.iterator();
        while (it.hasNext()) {
            if (it.next().getClassifyId() == BREATH_CLASSIFY_ID) {
                return true;
            }
        }
        return false;
    }

    public String getBackgroundMusicUrl() {
        return this.mBackgroundMusicUrl;
    }

    public void setBackgroundMusicUrl(String str) {
        this.mBackgroundMusicUrl = str;
    }

    public int getCalorieStartTime() {
        return this.mCalorieStartTime;
    }

    public void setCalorieStartTime(int i) {
        this.mCalorieStartTime = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            LogUtil.b(TAG, "writeToParcel dest == null");
            return;
        }
        parcel.writeString(this.planId);
        parcel.writeInt(this.duration);
        parcel.writeString(this.tag);
        parcel.writeString(this.workOutName);
        parcel.writeString(this.workId);
        parcel.writeByte(this.isPlan ? (byte) 1 : (byte) 0);
        parcel.writeString(this.startDate);
        parcel.writeString(this.finishDate);
        parcel.writeTypedList(this.equipments);
        parcel.writeTypedList(this.trainingpoints);
        parcel.writeInt(this.difficulty);
        parcel.writeString(this.detail);
        parcel.writeString(this.picture);
        parcel.writeInt(this.gender);
        parcel.writeInt(this.planType);
        parcel.writeTypedList(this.motions);
        parcel.writeTypedList(this.mPrimaryClassify);
        parcel.writeString(this.mBackgroundMusicUrl);
        parcel.writeInt(this.mCalorieStartTime);
    }
}
