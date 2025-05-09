package com.huawei.hihealth;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* loaded from: classes.dex */
public class HiAggregateOption extends HiDataReadOption {
    public static final Parcelable.Creator<HiAggregateOption> CREATOR = new Parcelable.Creator<HiAggregateOption>() { // from class: com.huawei.hihealth.HiAggregateOption.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bum_, reason: merged with bridge method [inline-methods] */
        public HiAggregateOption createFromParcel(Parcel parcel) {
            return new HiAggregateOption(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public HiAggregateOption[] newArray(int i) {
            return new HiAggregateOption[i];
        }
    };
    private int aggregateType;
    private String filter;
    private int groupUnitSize;
    private int groupUnitType;
    private String sortName;

    @Override // com.huawei.hihealth.HiDataReadOption, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HiAggregateOption() {
        this.sortOrder = 0;
    }

    protected HiAggregateOption(Parcel parcel) {
        this.startTime = parcel.readLong();
        this.endTime = parcel.readLong();
        this.filterStartTime = parcel.readString();
        this.filterEndTime = parcel.readString();
        this.modifiedStartTime = parcel.readLong();
        this.modifiedEndTime = parcel.readLong();
        this.type = parcel.createIntArray();
        this.constantsKey = parcel.createStringArray();
        this.alignType = parcel.readInt();
        this.aggregateType = parcel.readInt();
        this.filter = parcel.readString();
        this.deviceUuid = parcel.readString();
        this.ownerId = parcel.readInt();
        this.sortName = parcel.readString();
        this.sortOrder = parcel.readInt();
        this.groupUnitSize = parcel.readInt();
        this.groupUnitType = parcel.readInt();
        this.readType = parcel.readInt();
        this.anchor = parcel.readInt();
        this.count = parcel.readInt();
    }

    public int getGroupUnitSize() {
        return this.groupUnitSize;
    }

    public void setGroupUnitSize(int i) {
        this.groupUnitSize = ((Integer) ResultUtils.a(Integer.valueOf(i))).intValue();
    }

    public int getGroupUnitType() {
        return ((Integer) ResultUtils.a(Integer.valueOf(this.groupUnitType))).intValue();
    }

    public void setGroupUnitType(int i) {
        this.groupUnitType = ((Integer) ResultUtils.a(Integer.valueOf(i))).intValue();
    }

    public int getAggregateType() {
        return this.aggregateType;
    }

    public void setAggregateType(int i) {
        this.aggregateType = ((Integer) ResultUtils.a(Integer.valueOf(i))).intValue();
    }

    public String getFilter() {
        return this.filter;
    }

    public void setFilter(String str) {
        this.filter = (String) ResultUtils.a(str);
    }

    public String getSortName() {
        return this.sortName;
    }

    public void setSortName(String str) {
        this.sortName = str;
    }

    public void setSort(String str, int i) {
        this.sortName = str;
        this.sortOrder = i;
    }

    public void setTimeRange(long j, long j2) {
        this.startTime = j;
        this.endTime = j2;
    }

    public void setTimeGroup(long j, long j2, int i, int i2) {
        this.startTime = j;
        this.endTime = j2;
        this.groupUnitSize = i;
        this.groupUnitType = i2;
    }

    @Override // com.huawei.hihealth.HiDataReadOption, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.startTime);
        parcel.writeLong(this.endTime);
        parcel.writeString(this.filterStartTime);
        parcel.writeString(this.filterEndTime);
        parcel.writeLong(this.modifiedStartTime);
        parcel.writeLong(this.modifiedEndTime);
        parcel.writeIntArray(this.type);
        parcel.writeStringArray(this.constantsKey);
        parcel.writeInt(this.alignType);
        parcel.writeInt(this.aggregateType);
        parcel.writeString(this.filter);
        parcel.writeString(this.deviceUuid);
        parcel.writeInt(this.ownerId);
        parcel.writeString(this.sortName);
        parcel.writeInt(this.sortOrder);
        parcel.writeInt(this.groupUnitSize);
        parcel.writeInt(this.groupUnitType);
        parcel.writeInt(this.readType);
        parcel.writeInt(this.anchor);
        parcel.writeInt(this.count);
    }

    @Override // com.huawei.hihealth.HiDataReadOption
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("HiAggregateOption{sTm=");
        stringBuffer.append(this.startTime);
        stringBuffer.append(",eTm=").append(this.endTime);
        stringBuffer.append(",fltSTm=").append(this.filterStartTime);
        stringBuffer.append(",fltETm=").append(this.filterEndTime);
        stringBuffer.append(",mdfStm=").append(this.modifiedStartTime);
        stringBuffer.append(",mdfEtm=").append(this.modifiedEndTime);
        stringBuffer.append(",tp=").append(Arrays.toString(this.type));
        stringBuffer.append(",aliTp=").append(this.alignType);
        stringBuffer.append(",aggTp=").append(this.aggregateType);
        stringBuffer.append(",grpUntTp=").append(this.groupUnitType);
        stringBuffer.append(",ct=").append(this.count);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
