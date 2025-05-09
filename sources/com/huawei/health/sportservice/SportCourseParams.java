package com.huawei.health.sportservice;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;
import com.google.gson.annotations.SerializedName;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import defpackage.moj;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public class SportCourseParams implements Parcelable {
    public static final Parcelable.Creator<SportCourseParams> CREATOR = new Parcelable.Creator<SportCourseParams>() { // from class: com.huawei.health.sportservice.SportCourseParams.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: axx_, reason: merged with bridge method [inline-methods] */
        public SportCourseParams createFromParcel(Parcel parcel) {
            return new SportCourseParams(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public SportCourseParams[] newArray(int i) {
            return new SportCourseParams[i];
        }
    };

    @SerializedName("courseDurationList")
    private List<Integer> mCourseDurationList;

    @SerializedName("courseHeartRateList")
    private List<Pair<Integer, Integer>> mCourseHeartRateList;

    @SerializedName("courseNameList")
    private List<String> mCourseNameList;

    @SerializedName("courseTotalTime")
    private int mCourseTotalTime;

    @SerializedName(JsbMapKeyNames.H5_EXTRAS)
    private Map<String, String> mExtras;

    @SerializedName("isCourseFinished")
    private boolean mIsCourseFinished;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SportCourseParams() {
        this.mExtras = new HashMap();
    }

    protected SportCourseParams(Parcel parcel) {
        this.mExtras = new HashMap();
        if (parcel != null) {
            this.mIsCourseFinished = parcel.readByte() != 0;
            parcel.readList(this.mCourseNameList, String.class.getClassLoader());
            parcel.readList(this.mCourseDurationList, Integer.class.getClassLoader());
            parcel.readList(this.mCourseHeartRateList, Pair.class.getClassLoader());
            this.mCourseTotalTime = parcel.readInt();
            this.mExtras = parcel.readHashMap(HashMap.class.getClassLoader());
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel != null) {
            parcel.writeByte(this.mIsCourseFinished ? (byte) 1 : (byte) 0);
            parcel.writeList(this.mCourseNameList);
            parcel.writeList(this.mCourseDurationList);
            parcel.writeList(this.mCourseHeartRateList);
            parcel.writeInt(this.mCourseTotalTime);
            parcel.writeMap(this.mExtras);
        }
    }

    public SportCourseParams setCourseFinished(boolean z) {
        this.mIsCourseFinished = z;
        return this;
    }

    public SportCourseParams setCourseNameList(List<String> list) {
        this.mCourseNameList = list;
        return this;
    }

    public SportCourseParams setCourseDurationList(List<Integer> list) {
        this.mCourseDurationList = list;
        return this;
    }

    public SportCourseParams setCourseHeartRateList(List<Pair<Integer, Integer>> list) {
        this.mCourseHeartRateList = list;
        return this;
    }

    public SportCourseParams setCourseTotalTime(int i) {
        this.mCourseTotalTime = i;
        return this;
    }

    public SportCourseParams addExtra(String str, Object obj) {
        this.mExtras.put(str, moj.e(obj));
        return this;
    }

    public boolean isCourseFinished() {
        return this.mIsCourseFinished;
    }

    public List<String> getCourseNameList() {
        return this.mCourseNameList;
    }

    public List<Integer> getCourseDurationList() {
        return this.mCourseDurationList;
    }

    public List<Pair<Integer, Integer>> getCourseHeartRateList() {
        return this.mCourseHeartRateList;
    }

    public int getCourseTotalTime() {
        return this.mCourseTotalTime;
    }

    public <T> T getExtra(String str, Class<T> cls) {
        String str2 = this.mExtras.get(str);
        if (str2 == null || str2.isEmpty()) {
            return null;
        }
        return (T) moj.e(this.mExtras.get(str), cls);
    }

    public <T> T getExtra(String str, Type type) {
        String str2 = this.mExtras.get(str);
        if (str2 == null || str2.isEmpty()) {
            return null;
        }
        return (T) moj.b(this.mExtras.get(str), type);
    }

    public <T> T getExtra(String str, Class<T> cls, T t) {
        String str2 = this.mExtras.get(str);
        return (str2 == null || str2.isEmpty()) ? t : (T) moj.e(this.mExtras.get(str), cls);
    }
}
