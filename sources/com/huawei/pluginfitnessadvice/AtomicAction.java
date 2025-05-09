package com.huawei.pluginfitnessadvice;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import defpackage.moj;
import health.compact.a.CommonUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class AtomicAction extends BaseRecord {
    public static final Parcelable.Creator<AtomicAction> CREATOR = new Parcelable.Creator<AtomicAction>() { // from class: com.huawei.pluginfitnessadvice.AtomicAction.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: clA_, reason: merged with bridge method [inline-methods] */
        public AtomicAction createFromParcel(Parcel parcel) {
            return new AtomicAction(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public AtomicAction[] newArray(int i) {
            return new AtomicAction[i];
        }
    };
    private static final String TAG = "AtomicAction";
    private static final long serialVersionUID = -6187475281295073538L;

    @SerializedName(ParsedFieldTag.ACTION_TYPE)
    private int mActionSportType;

    @SerializedName("covers")
    private List<Cover> mCovers;

    @SerializedName("measurement")
    private int mDefaultTargetType;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("difficulty")
    private int mDifficulty;

    @SerializedName("equipments")
    private List<Attribute> mEquipments;

    @SerializedName("extendProperty")
    private Map<String, String> mExtendProperty;

    @SerializedName("lan")
    private String mLan;

    @SerializedName("orchestrationType")
    private int mOrchestrationType;

    @SerializedName("showInActionLibrary")
    private int mShowInActionLibrary;

    @SerializedName("sortWeight")
    private int mSortWeight;

    @SerializedName("trainingPoints")
    private List<Attribute> mTrainingPoints;

    @SerializedName("version")
    private String mVersion;

    public AtomicAction() {
        this.mExtendProperty = new HashMap();
    }

    public AtomicAction(Parcel parcel) {
        super(parcel);
        this.mExtendProperty = new HashMap();
        if (parcel != null) {
            this.mDescription = parcel.readString();
            this.mActionSportType = parcel.readInt();
            this.mOrchestrationType = parcel.readInt();
            this.mSortWeight = parcel.readInt();
            this.mDifficulty = parcel.readInt();
            this.mTrainingPoints = parcel.createTypedArrayList(Attribute.CREATOR);
            this.mEquipments = parcel.createTypedArrayList(Attribute.CREATOR);
            this.mCovers = parcel.createTypedArrayList(Cover.CREATOR);
            this.mVersion = parcel.readString();
            this.mDefaultTargetType = parcel.readInt();
            this.mShowInActionLibrary = parcel.readInt();
            this.mLan = parcel.readString();
            this.mExtendProperty = parcel.readHashMap(HashMap.class.getClassLoader());
        }
    }

    @Override // com.huawei.pluginfitnessadvice.BaseRecord, com.huawei.pluginfitnessadvice.Attribute, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        if (parcel != null) {
            parcel.writeString(this.mDescription);
            parcel.writeInt(this.mActionSportType);
            parcel.writeInt(this.mOrchestrationType);
            parcel.writeInt(this.mSortWeight);
            parcel.writeInt(this.mDifficulty);
            parcel.writeList(this.mTrainingPoints);
            parcel.writeList(this.mEquipments);
            parcel.writeList(this.mCovers);
            parcel.writeString(this.mVersion);
            parcel.writeInt(this.mDefaultTargetType);
            parcel.writeInt(this.mDefaultTargetType);
            parcel.writeLong(this.mShowInActionLibrary);
            parcel.writeString(this.mLan);
            parcel.writeMap(this.mExtendProperty);
        }
    }

    public String getDescription() {
        return this.mDescription;
    }

    public void setDescription(String str) {
        this.mDescription = str;
    }

    public int getActionSportType() {
        return this.mActionSportType;
    }

    public void setActionSportType(int i) {
        this.mActionSportType = i;
    }

    public int getOrchestrationType() {
        return this.mOrchestrationType;
    }

    public void setOrchestrationType(int i) {
        this.mOrchestrationType = i;
    }

    public int getSortWeight() {
        return this.mSortWeight;
    }

    public void setSortWeight(int i) {
        this.mSortWeight = i;
    }

    public int getDifficulty() {
        return this.mDifficulty;
    }

    public void setDifficulty(int i) {
        this.mDifficulty = i;
    }

    public List<Attribute> getTrainingPoints() {
        return this.mTrainingPoints;
    }

    public void setTrainingPoints(List<Attribute> list) {
        this.mTrainingPoints = list;
    }

    public List<Attribute> getEquipments() {
        return this.mEquipments;
    }

    public void setEquipments(List<Attribute> list) {
        this.mEquipments = list;
    }

    public List<Cover> getCovers() {
        return this.mCovers;
    }

    public void setCovers(List<Cover> list) {
        this.mCovers = list;
    }

    public String getVersion() {
        return this.mVersion;
    }

    public void setVersion(String str) {
        this.mVersion = str;
    }

    public int getDefaultTargetType() {
        return this.mDefaultTargetType;
    }

    public void setDefaultTargetType(int i) {
        this.mDefaultTargetType = i;
    }

    public int getShowInActionLibrary() {
        return this.mShowInActionLibrary;
    }

    public void setShowInActionLibrary(int i) {
        this.mShowInActionLibrary = i;
    }

    public String getLan() {
        return this.mLan;
    }

    public void setLan(String str) {
        this.mLan = str;
    }

    public void setExtendProperty(Map<String, String> map) {
        Map<String, String> map2;
        if (map == null || (map2 = this.mExtendProperty) == null) {
            return;
        }
        map2.clear();
        this.mExtendProperty.putAll(map);
    }

    public void putExtendProperty(String str, String str2) {
        if (this.mExtendProperty == null) {
            this.mExtendProperty = new HashMap();
        }
        if (str == null || str2 == null) {
            LogUtil.a(TAG, "putExtendProperty: key or value is null");
        } else {
            this.mExtendProperty.put(str, str2);
        }
    }

    public void putExtendProperty(String str, List list) {
        if (this.mExtendProperty == null) {
            this.mExtendProperty = new HashMap();
        }
        if (str == null || list == null) {
            LogUtil.a(TAG, "putExtendProperty: key or value is null");
        } else {
            this.mExtendProperty.put(str, moj.e(list));
        }
    }

    public <T> List<T> getExtendPropertyList(String str, Class<T[]> cls) {
        return moj.b(this.mExtendProperty.get(str), (Class) cls);
    }

    public <T> T getExtendProperty(String str, Class<T> cls) {
        return (T) moj.e(this.mExtendProperty.get(str), cls);
    }

    public Map<String, String> getExtendProperty() {
        return this.mExtendProperty;
    }

    public int getExtendPropertyInt(String str) {
        return getExtendPropertyInt(str, 0);
    }

    public int getExtendPropertyInt(String str, int i) {
        return CommonUtil.e(this.mExtendProperty.get(str), i);
    }

    public long getExtendPropertyLong(String str) {
        return getExtendPropertyLong(str, 0L);
    }

    public long getExtendPropertyLong(String str, long j) {
        return CommonUtil.b(this.mExtendProperty.get(str), j);
    }

    public String getExtendPropertyString(String str) {
        return getExtendPropertyString(str, null);
    }

    public String getExtendPropertyString(String str, String str2) {
        String str3 = this.mExtendProperty.get(str);
        return str3 != null ? str3 : str2;
    }

    public float getExtendPropertyFloat(String str) {
        return getExtendPropertyFloat(str, 0.0f);
    }

    public float getExtendPropertyFloat(String str, float f) {
        return CommonUtil.c(this.mExtendProperty.get(str), f);
    }

    public double getExtendPropertyDouble(String str) {
        return getExtendPropertyDouble(str, 0.0d);
    }

    public double getExtendPropertyDouble(String str, double d) {
        return CommonUtil.b(this.mExtendProperty.get(str), d);
    }
}
