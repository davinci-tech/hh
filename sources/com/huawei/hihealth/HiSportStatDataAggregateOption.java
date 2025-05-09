package com.huawei.hihealth;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.operation.utils.Constants;
import java.util.Arrays;

/* loaded from: classes.dex */
public class HiSportStatDataAggregateOption extends HiDataReadOption {
    public static final Parcelable.Creator<HiSportStatDataAggregateOption> CREATOR = new Parcelable.Creator<HiSportStatDataAggregateOption>() { // from class: com.huawei.hihealth.HiSportStatDataAggregateOption.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: buU_, reason: merged with bridge method [inline-methods] */
        public HiSportStatDataAggregateOption createFromParcel(Parcel parcel) {
            return new HiSportStatDataAggregateOption(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public HiSportStatDataAggregateOption[] newArray(int i) {
            return new HiSportStatDataAggregateOption[i];
        }
    };
    private int aggregateType;
    private String filter;
    private int groupUnitSize;
    private int groupUnitType;
    private int[] hiHealthTypes;
    private String sortName;

    @Override // com.huawei.hihealth.HiDataReadOption, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HiSportStatDataAggregateOption() {
        this.sortOrder = 0;
    }

    protected HiSportStatDataAggregateOption(Parcel parcel) {
        this.startTime = parcel.readLong();
        this.endTime = parcel.readLong();
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
        this.hiHealthTypes = parcel.createIntArray();
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

    public int[] getHiHealthTypes() {
        int[] iArr = this.hiHealthTypes;
        if (iArr == null || iArr.length <= 0) {
            return null;
        }
        return Arrays.copyOf(iArr, iArr.length);
    }

    public void setHiHealthTypes(int[] iArr) {
        this.hiHealthTypes = (int[]) ResultUtils.a(iArr);
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
        parcel.writeIntArray(this.hiHealthTypes);
    }

    @Override // com.huawei.hihealth.HiDataReadOption
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("HiSportStatDataAggregateOption{startTime=");
        stringBuffer.append(this.startTime);
        stringBuffer.append(", endTime=").append(this.endTime);
        stringBuffer.append(", hiHealthTypes=");
        int i = 0;
        if (this.hiHealthTypes == null) {
            stringBuffer.append(Constants.NULL);
        } else {
            stringBuffer.append('[');
            int i2 = 0;
            while (i2 < this.hiHealthTypes.length) {
                stringBuffer.append(i2 == 0 ? "" : ", ").append(this.hiHealthTypes[i2]);
                stringBuffer.append(']');
                i2++;
            }
        }
        stringBuffer.append(", type=");
        if (this.type == null) {
            stringBuffer.append(Constants.NULL);
        } else {
            stringBuffer.append('[');
            int i3 = 0;
            while (i3 < this.type.length) {
                stringBuffer.append(i3 == 0 ? "" : ", ").append(this.type[i3]);
                stringBuffer.append(']');
                i3++;
            }
        }
        stringBuffer.append(", alignType=").append(this.alignType);
        stringBuffer.append(", aggregateType=").append(this.aggregateType);
        stringBuffer.append(", groupUnitType=").append(this.groupUnitType);
        stringBuffer.append(", hiHealthTypes=");
        if (this.hiHealthTypes == null) {
            stringBuffer.append(Constants.NULL);
        } else {
            stringBuffer.append('[');
            while (i < this.hiHealthTypes.length) {
                stringBuffer.append(i == 0 ? "" : ", ").append(this.hiHealthTypes[i]);
                stringBuffer.append(']');
                i++;
            }
        }
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
