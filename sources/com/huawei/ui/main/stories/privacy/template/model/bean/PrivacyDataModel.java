package com.huawei.ui.main.stories.privacy.template.model.bean;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes7.dex */
public class PrivacyDataModel implements Parcelable {
    public static final Parcelable.Creator<PrivacyDataModel> CREATOR = new Parcelable.Creator<PrivacyDataModel>() { // from class: com.huawei.ui.main.stories.privacy.template.model.bean.PrivacyDataModel.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: dQA_, reason: merged with bridge method [inline-methods] */
        public PrivacyDataModel createFromParcel(Parcel parcel) {
            return new PrivacyDataModel(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public PrivacyDataModel[] newArray(int i) {
            return new PrivacyDataModel[i];
        }
    };
    private static final int DEFAULT_INT_VALUE = Integer.MIN_VALUE;
    private int clientId;
    private String dataDesc;
    private String dataTitle;
    private String deviceName;
    private double doubleValue;
    private long endTime;
    private ContentValues extendValues;
    private int intValue;
    private long longValue;
    private long modifyTime;
    private int pageType;
    private long startTime;
    private String stringValue;
    private int type;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PrivacyDataModel() {
        this("", "");
    }

    public PrivacyDataModel(String str, String str2) {
        this.dataTitle = str;
        this.dataDesc = str2;
        this.extendValues = new ContentValues();
    }

    protected PrivacyDataModel(Parcel parcel) {
        this(parcel.readString(), parcel.readString());
        this.pageType = parcel.readInt();
        this.startTime = parcel.readLong();
        this.endTime = parcel.readLong();
        this.modifyTime = parcel.readLong();
        this.doubleValue = parcel.readDouble();
        this.stringValue = parcel.readString();
        this.intValue = parcel.readInt();
        this.longValue = parcel.readLong();
        this.type = parcel.readInt();
        this.clientId = parcel.readInt();
        this.deviceName = parcel.readString();
        this.extendValues = (ContentValues) parcel.readParcelable(ContentValues.class.getClassLoader());
    }

    public String getDataTitle() {
        return this.dataTitle;
    }

    public void setDataTitle(String str) {
        this.dataTitle = str;
    }

    public String getDataDesc() {
        return this.dataDesc;
    }

    public void setDataDesc(String str) {
        this.dataDesc = str;
    }

    public int getPageType() {
        return this.pageType;
    }

    public void setPageType(int i) {
        this.pageType = i;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(long j) {
        this.startTime = j;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(long j) {
        this.endTime = j;
    }

    public long getModifyTime() {
        return this.modifyTime;
    }

    public void setModifyTime(long j) {
        this.modifyTime = j;
    }

    public double getDoubleValue() {
        return this.doubleValue;
    }

    public void setDoubleValue(double d) {
        this.doubleValue = d;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public int getClientId() {
        return this.clientId;
    }

    public void setClientId(int i) {
        this.clientId = i;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public void setDeviceName(String str) {
        this.deviceName = str;
    }

    public ContentValues getContentValues() {
        return this.extendValues;
    }

    public void setContentValues(ContentValues contentValues) {
        this.extendValues = contentValues;
    }

    public final void putString(String str, String str2) {
        this.extendValues.put(str, str2);
    }

    public String getString(String str) {
        return this.extendValues.getAsString(str);
    }

    public void putInt(String str, int i) {
        this.extendValues.put(str, Integer.valueOf(i));
    }

    public int getInt(String str) {
        Integer asInteger = this.extendValues.getAsInteger(str);
        if (asInteger == null) {
            return Integer.MIN_VALUE;
        }
        return asInteger.intValue();
    }

    public void putBoolean(String str, boolean z) {
        this.extendValues.put(str, Boolean.valueOf(z));
    }

    public boolean getBoolean(String str) {
        Boolean asBoolean = this.extendValues.getAsBoolean(str);
        if (asBoolean == null) {
            return false;
        }
        return asBoolean.booleanValue();
    }

    public void putLong(String str, long j) {
        this.extendValues.put(str, Long.valueOf(j));
    }

    public long getLong(String str) {
        Long asLong = this.extendValues.getAsLong(str);
        if (asLong == null) {
            return Long.MIN_VALUE;
        }
        return asLong.longValue();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.dataTitle);
        parcel.writeString(this.dataDesc);
        parcel.writeInt(this.pageType);
        parcel.writeLong(this.startTime);
        parcel.writeLong(this.endTime);
        parcel.writeLong(this.modifyTime);
        parcel.writeDouble(this.doubleValue);
        parcel.writeString(this.stringValue);
        parcel.writeInt(this.intValue);
        parcel.writeLong(this.longValue);
        parcel.writeInt(this.type);
        parcel.writeInt(this.clientId);
        parcel.writeString(this.deviceName);
        parcel.writeParcelable(this.extendValues, i);
    }

    public String toString() {
        return "PrivacyDataModel{dataTitle=" + this.dataTitle + ", dataDesc=" + this.dataDesc + ", pageType=" + this.pageType + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", modifyTime=" + this.modifyTime + ", type=" + this.type + ", clientId=" + this.clientId + ", deviceName=" + this.deviceName + '}';
    }

    public int getIntValue() {
        return this.intValue;
    }

    public void setIntValue(int i) {
        this.intValue = i;
    }

    public long getLongValue() {
        return this.longValue;
    }

    public void setLongValue(long j) {
        this.longValue = j;
    }
}
