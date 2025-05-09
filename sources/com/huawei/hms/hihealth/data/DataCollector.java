package com.huawei.hms.hihealth.data;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.common.internal.safeparcel.SafeParcelReader;
import com.huawei.hms.common.internal.safeparcel.SafeParcelWriter;
import com.huawei.hms.common.internal.safeparcel.SafeParcelable;
import com.huawei.hms.health.aabz;
import com.huawei.hms.health.aacs;

/* loaded from: classes9.dex */
public class DataCollector implements SafeParcelable {
    private static final int APPPACKAGENAMEID = 1;
    public static final Parcelable.Creator<DataCollector> CREATOR = new aab();
    private static final int DATATYPEID = 7;
    public static final int DATA_TYPE_CLEAN = 2;
    public static final int DATA_TYPE_CONVERTED = 3;
    public static final int DATA_TYPE_DERIVED = 1;
    public static final int DATA_TYPE_INIT = -1;
    public static final int DATA_TYPE_MERGED = 4;
    public static final int DATA_TYPE_POLYMERIZED = 5;
    public static final int DATA_TYPE_RAW = 0;
    private static final int DEVICEID = 6;
    private static final int EXPECTED_BUFFER_DATA = 1024;
    public static final String EXTRA_DATA_SOURCE = "vnd.huawei.hihealth.data_collector";
    public static final int HEALTH_DATA_QUALITY_BLOOD_GLUCOSE_ISO151972003 = 8;
    public static final int HEALTH_DATA_QUALITY_BLOOD_GLUCOSE_ISO151972013 = 9;
    public static final int HEALTH_DATA_QUALITY_BLOOD_PRESSURE_AAMI = 3;
    public static final int HEALTH_DATA_QUALITY_BLOOD_PRESSURE_BHS_A_A = 4;
    public static final int HEALTH_DATA_QUALITY_BLOOD_PRESSURE_BHS_A_B = 5;
    public static final int HEALTH_DATA_QUALITY_BLOOD_PRESSURE_BHS_B_A = 6;
    public static final int HEALTH_DATA_QUALITY_BLOOD_PRESSURE_BHS_B_B = 7;
    public static final int HEALTH_DATA_QUALITY_BLOOD_PRESSURE_ESH2002 = 1;
    public static final int HEALTH_DATA_QUALITY_BLOOD_PRESSURE_ESH2010 = 2;
    private static final int ISLOCLALISEDID = 8;
    private static final int NAMEID = 2;
    private static final int STREAMIDENTIFIERID = 4;
    private static final int STREAMNAMEID = 3;
    private static final String TAG = "DataCollector";
    private static final int TYPEID = 5;
    private String appId;
    private String dataCollectorName;
    private int dataGenerateType;
    private String dataStreamId;
    private String dataStreamName;
    private DataType dataType;

    @Deprecated
    private String deviceId;
    private DeviceInfo deviceInfo;
    private boolean isLocalized;
    private String packageName;

    public static DataCollector extract(Intent intent) {
        return null;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getPackageName(), false);
        SafeParcelWriter.writeString(parcel, 2, getDataCollectorName(), false);
        SafeParcelWriter.writeString(parcel, 3, getDataStreamName(), false);
        SafeParcelWriter.writeString(parcel, 4, getDataStreamId(), false);
        SafeParcelWriter.writeInt(parcel, 5, getDataGenerateType());
        SafeParcelWriter.writeParcelable(parcel, 6, getDeviceInfo(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 7, getDataType(), i, false);
        SafeParcelWriter.writeBoolean(parcel, 8, isLocalized());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("DataCollector{");
        sb.append(getTypeString());
        if (this.dataCollectorName != null) {
            sb.append(":");
            sb.append(this.dataCollectorName);
        }
        if (this.packageName != null) {
            sb.append(":");
            sb.append(this.packageName);
        }
        if (this.deviceInfo != null) {
            sb.append(":");
            sb.append(this.deviceInfo);
        }
        if (this.dataStreamName != null) {
            sb.append(":");
            sb.append(this.dataStreamName);
        }
        sb.append(":");
        sb.append(this.dataType);
        sb.append("}");
        return sb.toString();
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public boolean isLocalized() {
        return this.isLocalized;
    }

    public int hashCode() {
        return (this.packageName + this.dataStreamId + this.dataCollectorName).hashCode();
    }

    public int[] getQualityMetrics() {
        throw new IllegalArgumentException("get qualityMetrics failed! This method is deprecated!");
    }

    public String getPackageName() {
        return this.packageName;
    }

    public DeviceInfo getDeviceInfo() {
        return this.deviceInfo;
    }

    @Deprecated
    public String getDeviceId() {
        return this.deviceId;
    }

    public DataType getDataType() {
        return this.dataType;
    }

    public String getDataStreamName() {
        return this.dataStreamName;
    }

    public String getDataStreamId() {
        return this.dataStreamId;
    }

    public int getDataGenerateType() {
        return this.dataGenerateType;
    }

    public String getDataCollectorName() {
        return this.dataCollectorName;
    }

    public String getAppId() {
        return this.appId;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DataCollector) {
            return this.dataStreamId.equals(((DataCollector) obj).dataStreamId);
        }
        return false;
    }

    private String getTypeString() {
        int i = this.dataGenerateType;
        return i != 0 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "derived" : "polymerized" : "merged" : "converted" : "cleaned" : "raw";
    }

    public static String getStandardByType(int i) {
        switch (i) {
            case 1:
                return "blood_pressure_esh2002";
            case 2:
                return "blood_pressure_esh2010";
            case 3:
                return "blood_pressure_aami";
            case 4:
                return "blood_pressure_bhs_a_a";
            case 5:
                return "blood_pressure_bhs_a_b";
            case 6:
                return "blood_pressure_bhs_b_a";
            case 7:
                return "blood_pressure_bhs_b_b";
            case 8:
                return "blood_glucose_iso151972003";
            case 9:
                return "blood_glucose_iso151972013";
            default:
                return "unknown";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String encodeDataStreamIdentifier() {
        StringBuilder sb = new StringBuilder(1024);
        sb.append(getTypeString());
        sb.append(":");
        sb.append(this.dataType.getName());
        if (this.packageName != null) {
            sb.append(":");
            sb.append(this.packageName);
        }
        if (this.deviceInfo != null) {
            sb.append(":");
            sb.append(this.deviceInfo.getDeviceIdentifier());
        }
        String str = this.dataStreamName;
        if (str != null && !str.isEmpty()) {
            sb.append(":");
            sb.append(this.dataStreamName);
        }
        return sb.toString();
    }

    public static class Builder {
        private String appId;
        private String dataCollectorName;
        private DataType dataType;

        @Deprecated
        private String deviceId;
        private DeviceInfo deviceInfo;
        private String packageName = "";
        private String dataStreamName = "";
        private int dataGenerateType = -1;
        private boolean isLocalized = false;

        public final Builder setQualityMetrics(int... iArr) {
            throw new IllegalArgumentException("set qualityMetrics failed! This method is deprecated!");
        }

        public Builder setPackageName(String str) {
            Preconditions.checkArgument("".equals(str) || aacs.aaba(str), "PackageName Length Is Illegal!");
            this.packageName = str;
            return this;
        }

        public Builder setPackageName(Context context) {
            this.packageName = context.getPackageName();
            return this;
        }

        public Builder setLocalized(boolean z) {
            this.isLocalized = z;
            return this;
        }

        public Builder setDeviceInfo(DeviceInfo deviceInfo) {
            this.deviceInfo = deviceInfo;
            return this;
        }

        @Deprecated
        public Builder setDeviceId(String str) {
            Preconditions.checkArgument(str == null || aacs.aabc(str), "DeviceId Length Is Illegal!");
            this.deviceId = str;
            return this;
        }

        public Builder setDataType(String str) {
            this.dataType = new DataType(str);
            return this;
        }

        public Builder setDataType(DataType dataType) {
            this.dataType = dataType;
            return this;
        }

        public Builder setDataStreamName(String str) {
            Preconditions.checkArgument("".equals(str) || aacs.aaba(str), "DataStreamName Length Is Illegal!");
            this.dataStreamName = str;
            return this;
        }

        public Builder setDataGenerateType(int i) {
            this.dataGenerateType = i;
            return this;
        }

        public Builder setDataCollectorName(String str) {
            Preconditions.checkArgument(str == null || aacs.aaba(str), "DataCollectorName Length Is Illegal!");
            this.dataCollectorName = str;
            return this;
        }

        public Builder setAppId(String str) {
            this.appId = str;
            return this;
        }

        public DataCollector build() {
            Preconditions.checkState(this.dataType != null, "DataType Is Null, Must Init It.");
            Preconditions.checkState(!this.dataType.equals(DataType.DT_UNUSED_DATA_TYPE), "unused data type");
            Preconditions.checkState(this.dataGenerateType >= 0, "Data Generate Type Not Init!");
            Preconditions.checkArgument("".equals(this.dataStreamName) || aacs.aaba(this.dataStreamName), "DataStreamName Length Is Illegal!");
            Preconditions.checkArgument("".equals(this.packageName) || aacs.aaba(this.packageName), "PackageName Length Is Illegal!");
            String str = this.dataCollectorName;
            Preconditions.checkArgument(str == null || aacs.aaba(str), "DataCollectorName Length Is Illegal!");
            String str2 = this.deviceId;
            Preconditions.checkArgument(str2 == null || aacs.aabc(str2), "DeviceId Length Is Illegal!");
            if (this.isLocalized) {
                Preconditions.checkState(this.deviceInfo != null, "DataCollector: Local device mast set the right deviceinfo");
            }
            DataCollector dataCollector = new DataCollector((aab) null);
            dataCollector.packageName = this.packageName;
            dataCollector.dataCollectorName = this.dataCollectorName;
            dataCollector.dataStreamName = this.dataStreamName;
            dataCollector.dataGenerateType = this.dataGenerateType;
            dataCollector.dataType = this.dataType;
            dataCollector.deviceInfo = this.deviceInfo;
            dataCollector.dataStreamId = dataCollector.encodeDataStreamIdentifier();
            dataCollector.isLocalized = this.isLocalized;
            dataCollector.deviceId = this.deviceId;
            dataCollector.appId = this.appId;
            return dataCollector;
        }
    }

    /* synthetic */ DataCollector(aab aabVar) {
        this();
    }

    protected DataCollector(Parcel parcel) {
        int i;
        int i2 = 0;
        this.isLocalized = false;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        while (true) {
            i = i2 + 1;
            if (i2 <= validateObjectHeader && parcel.dataPosition() < validateObjectHeader) {
                int readHeader = SafeParcelReader.readHeader(parcel);
                switch (SafeParcelReader.getFieldId(readHeader)) {
                    case 1:
                        this.packageName = SafeParcelReader.createString(parcel, readHeader);
                        break;
                    case 2:
                        this.dataCollectorName = SafeParcelReader.createString(parcel, readHeader);
                        break;
                    case 3:
                        this.dataStreamName = SafeParcelReader.createString(parcel, readHeader);
                        break;
                    case 4:
                        this.dataStreamId = SafeParcelReader.createString(parcel, readHeader);
                        break;
                    case 5:
                        this.dataGenerateType = SafeParcelReader.readInt(parcel, readHeader);
                        break;
                    case 6:
                        this.deviceInfo = (DeviceInfo) SafeParcelReader.createParcelable(parcel, readHeader, DeviceInfo.CREATOR);
                        break;
                    case 7:
                        this.dataType = (DataType) SafeParcelReader.createParcelable(parcel, readHeader, DataType.CREATOR);
                        break;
                    case 8:
                        this.isLocalized = SafeParcelReader.readBoolean(parcel, readHeader);
                        break;
                    default:
                        SafeParcelReader.skipUnknownField(parcel, readHeader);
                        break;
                }
                i2 = i;
            }
        }
        if (i > validateObjectHeader) {
            aabz.aab(TAG, "Max loop reached, dataCollector parcel failed");
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
    }

    class aab implements Parcelable.Creator<DataCollector> {
        @Override // android.os.Parcelable.Creator
        public DataCollector createFromParcel(Parcel parcel) {
            return new DataCollector(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public DataCollector[] newArray(int i) {
            return new DataCollector[i];
        }

        aab() {
        }
    }

    private DataCollector() {
        this.isLocalized = false;
    }
}
