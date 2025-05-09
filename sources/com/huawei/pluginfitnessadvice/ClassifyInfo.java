package com.huawei.pluginfitnessadvice;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.koq;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes6.dex */
public class ClassifyInfo implements Parcelable {
    public static final Parcelable.Creator<ClassifyInfo> CREATOR = new Parcelable.Creator<ClassifyInfo>() { // from class: com.huawei.pluginfitnessadvice.ClassifyInfo.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: clJ_, reason: merged with bridge method [inline-methods] */
        public ClassifyInfo createFromParcel(Parcel parcel) {
            return new ClassifyInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public ClassifyInfo[] newArray(int i) {
            return new ClassifyInfo[i];
        }
    };
    private static final String TAG = "ClassifyInfo";

    @SerializedName("courseCategory")
    private int mCourseCategory;

    @SerializedName("positionId")
    private int mPositionId;

    @SerializedName("positionName")
    private String mPositionName;

    @SerializedName("primaryClassify")
    private Classify mPrimaryClassify;

    @SerializedName("secondAttributeList")
    private List<Attribute> mSecondAttributeList;

    @SerializedName("secondClassifyList")
    private List<Classify> mSecondClassifyList;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ClassifyInfo(Classify classify, List<Classify> list) {
        this(classify, list, 0);
    }

    public ClassifyInfo(Classify classify, List<Classify> list, int i) {
        this.mPrimaryClassify = classify;
        ArrayList arrayList = new ArrayList(list);
        this.mSecondClassifyList = arrayList;
        this.mSecondAttributeList = secondClassifyToAttributes(arrayList);
        this.mCourseCategory = i;
    }

    protected ClassifyInfo(Parcel parcel) {
        if (parcel == null) {
            LogUtil.h(TAG, "ClassifyInfo in == null");
            return;
        }
        this.mPrimaryClassify = (Classify) parcel.readParcelable(Classify.class.getClassLoader());
        ArrayList createTypedArrayList = parcel.createTypedArrayList(Classify.CREATOR);
        this.mSecondClassifyList = createTypedArrayList;
        this.mSecondAttributeList = secondClassifyToAttributes(createTypedArrayList);
        this.mCourseCategory = parcel.readInt();
        this.mPositionId = parcel.readInt();
        this.mPositionName = parcel.readString();
    }

    public Classify getPrimaryClassify() {
        return this.mPrimaryClassify;
    }

    public List<Classify> getSecondClassifyList() {
        if (koq.b(this.mSecondClassifyList)) {
            LogUtil.h(TAG, "ClassifyInfo mSecondClassifyList == null");
            return Collections.emptyList();
        }
        return this.mSecondClassifyList;
    }

    public static List<Attribute> secondClassifyToAttributes(List<Classify> list) {
        ArrayList arrayList = new ArrayList();
        if (koq.b(list)) {
            LogUtil.h(TAG, "ClassifyInfo setSecondClassifyList : secondClassifyList is  null");
            return Collections.emptyList();
        }
        for (Classify classify : list) {
            if (classify != null) {
                arrayList.add(new Attribute(classify.getClassifyId(), classify.getClassifyName()));
            }
        }
        return arrayList;
    }

    public List<Attribute> getSecondAttributeList() {
        if (koq.b(this.mSecondAttributeList) && koq.b(this.mSecondClassifyList)) {
            LogUtil.h(TAG, "ClassifyInfo mSecondClassifyList == null");
            return Collections.emptyList();
        }
        if (koq.b(this.mSecondAttributeList)) {
            this.mSecondAttributeList = secondClassifyToAttributes(this.mSecondClassifyList);
        }
        return this.mSecondAttributeList;
    }

    public int getCourseCategory() {
        return this.mCourseCategory;
    }

    public int getPositionId() {
        return this.mPositionId;
    }

    public void setPositionId(int i) {
        this.mPositionId = i;
    }

    public String getPositionName() {
        return this.mPositionName;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            LogUtil.h(TAG, "writeToParcel dest == null");
            return;
        }
        parcel.writeParcelable(this.mPrimaryClassify, i);
        parcel.writeList(this.mSecondClassifyList);
        parcel.writeList(this.mSecondAttributeList);
        parcel.writeInt(this.mCourseCategory);
        parcel.writeInt(this.mPositionId);
        parcel.writeString(this.mPositionName);
    }

    public String toString() {
        return "ClassifyInfo:{mPrimaryClassify='" + this.mPrimaryClassify + ", mSecondClassifyList=" + this.mSecondClassifyList + ", mCourseCategory" + this.mCourseCategory + "}";
    }
}
