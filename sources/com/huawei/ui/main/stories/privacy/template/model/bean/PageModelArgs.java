package com.huawei.ui.main.stories.privacy.template.model.bean;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.ui.main.stories.privacy.datasourcemanager.bean.SourceInfo;

/* loaded from: classes7.dex */
public class PageModelArgs implements Parcelable {
    public static final Parcelable.Creator<PageModelArgs> CREATOR = new Parcelable.Creator<PageModelArgs>() { // from class: com.huawei.ui.main.stories.privacy.template.model.bean.PageModelArgs.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: dQz_, reason: merged with bridge method [inline-methods] */
        public PageModelArgs createFromParcel(Parcel parcel) {
            return new PageModelArgs(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public PageModelArgs[] newArray(int i) {
            return new PageModelArgs[i];
        }
    };
    private int classType;
    private ContentValues contentValues;
    private int dataSource;
    private boolean isOtherManufacturer;
    private int moduleType;
    private String packageName;
    private int pageType;
    private String serviceId;
    private SourceInfo sourceInfo;
    private long timestamp;
    private String uuid;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PageModelArgs() {
        this.contentValues = new ContentValues();
    }

    public PageModelArgs(int i, String str, int i2, int i3) {
        this(i, str, "", i2, i3);
    }

    public PageModelArgs(int i, String str, String str2, int i2, int i3) {
        this.contentValues = new ContentValues();
        this.pageType = i;
        this.serviceId = str;
        this.uuid = str2;
        this.dataSource = i2;
        this.moduleType = i3;
    }

    protected PageModelArgs(Parcel parcel) {
        this(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt());
        this.timestamp = parcel.readLong();
        this.classType = parcel.readInt();
        this.packageName = parcel.readString();
        this.isOtherManufacturer = parcel.readByte() != 0;
        this.sourceInfo = (SourceInfo) parcel.readParcelable(SourceInfo.class.getClassLoader());
        this.contentValues = (ContentValues) parcel.readParcelable(ContentValues.class.getClassLoader());
    }

    public int getPageType() {
        return this.pageType;
    }

    public void setPageType(int i) {
        this.pageType = i;
    }

    public String getUuid() {
        return this.uuid;
    }

    public String getServiceId() {
        return this.serviceId;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public int getModuleType() {
        return this.moduleType;
    }

    public void setModuleType(int i) {
        this.moduleType = i;
    }

    public int getDataSource() {
        return this.dataSource;
    }

    public void setDataSource(int i) {
        this.dataSource = i;
    }

    public int getClassType() {
        return this.classType;
    }

    public void setClassType(int i) {
        this.classType = i;
    }

    public void setServiceId(String str) {
        this.serviceId = str;
    }

    public void setUuid(String str) {
        this.uuid = str;
    }

    public SourceInfo getSourceInfo() {
        return this.sourceInfo;
    }

    public void setSourceInfo(SourceInfo sourceInfo) {
        this.sourceInfo = sourceInfo;
    }

    public void putInt(String str, int i) {
        if (this.contentValues.containsKey(str)) {
            this.contentValues.remove(str);
        }
        this.contentValues.put(str, Integer.valueOf(i));
    }

    public int getInt(String str) {
        Integer asInteger = this.contentValues.getAsInteger(str);
        if (asInteger == null) {
            return Integer.MIN_VALUE;
        }
        return asInteger.intValue();
    }

    public void putBoolean(String str, boolean z) {
        if (this.contentValues.containsKey(str)) {
            this.contentValues.remove(str);
        }
        this.contentValues.put(str, Boolean.valueOf(z));
    }

    public boolean getBoolean(String str) {
        Boolean asBoolean = this.contentValues.getAsBoolean(str);
        if (asBoolean == null) {
            return false;
        }
        return asBoolean.booleanValue();
    }

    public void putLong(String str, long j) {
        if (this.contentValues.containsKey(str)) {
            this.contentValues.remove(str);
        }
        this.contentValues.put(str, Long.valueOf(j));
    }

    public long getLong(String str) {
        Long asLong = this.contentValues.getAsLong(str);
        if (asLong == null) {
            return Long.MIN_VALUE;
        }
        return asLong.longValue();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.pageType);
        parcel.writeString(this.serviceId);
        parcel.writeString(this.uuid);
        parcel.writeInt(this.dataSource);
        parcel.writeInt(this.moduleType);
        parcel.writeLong(this.timestamp);
        parcel.writeInt(this.classType);
        parcel.writeString(this.packageName);
        parcel.writeByte(this.isOtherManufacturer ? (byte) 1 : (byte) 0);
        parcel.writeParcelable(this.sourceInfo, i);
        parcel.writeParcelable(this.contentValues, i);
    }

    public String getPackageName() {
        String str = this.packageName;
        return str == null ? "" : str;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public boolean isOtherManufacturer() {
        return this.isOtherManufacturer;
    }

    public void setOtherManufacturer(boolean z) {
        this.isOtherManufacturer = z;
    }

    public ContentValues getContentValues() {
        return this.contentValues;
    }

    public void setContentValues(ContentValues contentValues) {
        this.contentValues = contentValues;
    }
}
