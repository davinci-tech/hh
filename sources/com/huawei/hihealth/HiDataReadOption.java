package com.huawei.hihealth;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealth.util.ReleaseLogUtil;
import health.compact.a.HiDateUtil;
import java.text.ParseException;
import java.util.TimeZone;

/* loaded from: classes.dex */
public class HiDataReadOption implements Parcelable {
    public static final Parcelable.Creator<HiDataReadOption> CREATOR = new Parcelable.Creator<HiDataReadOption>() { // from class: com.huawei.hihealth.HiDataReadOption.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bux_, reason: merged with bridge method [inline-methods] */
        public HiDataReadOption createFromParcel(Parcel parcel) {
            return new HiDataReadOption(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public HiDataReadOption[] newArray(int i) {
            return new HiDataReadOption[i];
        }
    };
    private static final String LOG_TAG = "HiH_HiDataReadOption";
    protected int alignType;
    protected int anchor;
    protected String[] constantsKey;
    protected int count;
    protected String deviceUuid;
    protected long endTime;
    protected String filterEndTime;
    protected String filterStartTime;
    protected long modifiedEndTime;
    protected long modifiedStartTime;
    protected int ownerId;
    protected int readType;
    protected int sortOrder;
    protected long startTime;
    protected int[] type;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected HiDataReadOption(Parcel parcel) {
        this.startTime = parcel.readLong();
        this.endTime = parcel.readLong();
        this.filterStartTime = parcel.readString();
        this.filterEndTime = parcel.readString();
        this.modifiedStartTime = parcel.readLong();
        this.modifiedEndTime = parcel.readLong();
        this.type = parcel.createIntArray();
        this.constantsKey = parcel.createStringArray();
        this.alignType = parcel.readInt();
        this.count = parcel.readInt();
        this.anchor = parcel.readInt();
        this.sortOrder = parcel.readInt();
        this.deviceUuid = parcel.readString();
        this.ownerId = parcel.readInt();
        this.readType = parcel.readInt();
    }

    public HiDataReadOption() {
        this.sortOrder = 1;
    }

    public int getReadType() {
        return this.readType;
    }

    public void setReadType(int i) {
        this.readType = i;
    }

    public void setTimeInterval(long j, long j2) {
        this.startTime = j;
        this.endTime = j2;
    }

    public void setTimeInterval(String str, String str2, TimeFormatType timeFormatType) {
        if (timeFormatType == TimeFormatType.DEFAULT_TIMESTAMP_FORMAT) {
            this.startTime = Long.parseLong(str);
            this.endTime = Long.parseLong(str2);
            return;
        }
        if (timeFormatType == TimeFormatType.DATE_FORMAT_YYYY_MM_DD) {
            try {
                this.startTime = HiDateUtil.d(str, "yyyyMMdd", TimeZone.getDefault()).getTime();
                this.endTime = HiDateUtil.f(HiDateUtil.d(str2, "yyyyMMdd", TimeZone.getDefault()).getTime());
                return;
            } catch (ParseException unused) {
                ReleaseLogUtil.b(LOG_TAG, "ParseException ", str, " ", str2, " ", timeFormatType);
                return;
            }
        }
        if (timeFormatType == TimeFormatType.DATE_FORMAT_MILLISECONDS) {
            try {
                this.startTime = HiDateUtil.d(str, "yyyy-MM-dd HH:mm:ss:SSS", TimeZone.getDefault()).getTime();
                this.endTime = HiDateUtil.d(str2, "yyyy-MM-dd HH:mm:ss:SSS", TimeZone.getDefault()).getTime();
                return;
            } catch (ParseException unused2) {
                ReleaseLogUtil.b(LOG_TAG, "ParseException ", str, " ", str2, " ", timeFormatType);
                return;
            }
        }
        ReleaseLogUtil.b(LOG_TAG, "ParseException ", str, " ", str2, " ", timeFormatType);
    }

    public long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(long j) {
        this.startTime = ((Long) ResultUtils.a(Long.valueOf(j))).longValue();
    }

    public long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(long j) {
        this.endTime = ((Long) ResultUtils.a(Long.valueOf(j))).longValue();
    }

    public void setFilterTimeInterval(String str, String str2) {
        this.filterStartTime = str;
        this.filterEndTime = str2;
    }

    public String getFilterStartTime() {
        return this.filterStartTime;
    }

    public void setFilterStartTime(String str) {
        this.filterStartTime = str;
    }

    public String getFilterEndTime() {
        return this.filterEndTime;
    }

    public void setFilterEndTime(String str) {
        this.filterEndTime = str;
    }

    public long getModifiedStartTime() {
        return this.modifiedStartTime;
    }

    public void setModifiedStartTime(long j) {
        this.modifiedStartTime = j;
    }

    public long getModifiedEndTime() {
        return this.modifiedEndTime;
    }

    public void setModifiedEndTime(long j) {
        this.modifiedEndTime = j;
    }

    public int[] getType() {
        int[] iArr = this.type;
        if (iArr == null) {
            return null;
        }
        return (int[]) iArr.clone();
    }

    public void setType(int[] iArr) {
        if (iArr == null) {
            this.type = null;
        } else {
            this.type = (int[]) iArr.clone();
        }
    }

    public void setType(int[] iArr, String[] strArr, int i) {
        if (iArr != null) {
            this.type = (int[]) iArr.clone();
        }
        if (strArr != null) {
            this.constantsKey = (String[]) strArr.clone();
        }
        this.alignType = i;
    }

    public String getDeviceUuid() {
        return this.deviceUuid;
    }

    public void setDeviceUuid(String str) {
        this.deviceUuid = (String) ResultUtils.a(str);
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int i) {
        this.count = ((Integer) ResultUtils.a(Integer.valueOf(i))).intValue();
    }

    public int getAnchor() {
        return this.anchor;
    }

    public void setAnchor(int i) {
        this.anchor = i;
    }

    public int getAlignType() {
        return this.alignType;
    }

    public void setAlignType(int i) {
        this.alignType = i;
    }

    public String[] getConstantsKey() {
        String[] strArr = this.constantsKey;
        if (strArr == null) {
            return null;
        }
        return (String[]) strArr.clone();
    }

    public void setConstantsKey(String[] strArr) {
        if (strArr == null) {
            this.constantsKey = null;
        } else {
            this.constantsKey = (String[]) strArr.clone();
        }
    }

    public int getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(int i) {
        this.ownerId = i;
    }

    public int getSortOrder() {
        return this.sortOrder;
    }

    public void setSortOrder(int i) {
        this.sortOrder = ((Integer) ResultUtils.a(Integer.valueOf(i))).intValue();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("HiDataReadOption{sTm=");
        stringBuffer.append(this.startTime);
        stringBuffer.append(",eTm=").append(this.endTime);
        stringBuffer.append(",fltSTm=").append(this.filterStartTime);
        stringBuffer.append(",fltETm=").append(this.filterEndTime);
        stringBuffer.append(",mdfStm=").append(this.modifiedStartTime);
        stringBuffer.append(",mdfEtm=").append(this.modifiedEndTime);
        stringBuffer.append(",tp=").append(HiJsonUtil.e(this.type));
        stringBuffer.append(",ct=").append(this.count);
        stringBuffer.append(",sortOdr=").append(this.sortOrder);
        stringBuffer.append(",rTp=").append(this.readType);
        stringBuffer.append(",aliTp=").append(this.alignType);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    @Override // android.os.Parcelable
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
        parcel.writeInt(this.count);
        parcel.writeInt(this.anchor);
        parcel.writeInt(this.sortOrder);
        parcel.writeString(this.deviceUuid);
        parcel.writeInt(this.ownerId);
        parcel.writeInt(this.readType);
    }

    public enum TimeFormatType {
        DEFAULT_TIMESTAMP_FORMAT(0),
        DATE_FORMAT_YYYY_MM_DD(1),
        DATE_FORMAT_MILLISECONDS(2);

        int mValue;

        TimeFormatType(int i) {
            this.mValue = i;
        }

        public int getValue() {
            return this.mValue;
        }
    }
}
