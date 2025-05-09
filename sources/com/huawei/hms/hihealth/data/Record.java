package com.huawei.hms.hihealth.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.common.internal.Objects;
import com.huawei.hms.common.internal.Preconditions;

/* loaded from: classes9.dex */
public class Record implements Parcelable {
    private static final int ACCURACY_LEVEL = 2;
    public static final Parcelable.Creator<Record> CREATOR = new aab();
    private static final long SAMPLING_RATE = -1;
    private int accuracyLevel;
    private DataCollector dataCollector;
    private DataType dataType;
    private long samplingRate;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.accuracyLevel);
        parcel.writeLong(this.samplingRate);
        parcel.writeParcelable(this.dataCollector, i);
        parcel.writeParcelable(this.dataType, i);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("accuracyLevel", Integer.valueOf(this.accuracyLevel)).add("samplingRate", Long.valueOf(this.samplingRate)).add("dataCollector", this.dataCollector).add("dataType", this.dataType).toString();
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.accuracyLevel), Long.valueOf(this.samplingRate), this.dataCollector, this.dataType);
    }

    public DataType getDataType() {
        return this.dataType;
    }

    public static class Builder {
        private DataCollector dataCollector;
        private DataType dataType;
        private long samplingRate = -1;
        private int accuracyLevel = 2;

        public final Builder setDataType(DataType dataType) {
            this.dataType = dataType;
            return this;
        }

        public final Builder setDataCollector(DataCollector dataCollector) {
            this.dataCollector = dataCollector;
            return this;
        }

        public final Record build() {
            DataCollector dataCollector;
            boolean z = true;
            Preconditions.checkState((this.dataCollector == null && this.dataType == null) ? false : true, "Call either setDataCollector() or setDataType()");
            DataType dataType = this.dataType;
            if (dataType != null && (dataCollector = this.dataCollector) != null && !dataType.equals(dataCollector.getDataType())) {
                z = false;
            }
            Preconditions.checkState(z, "Target data type does not match the data type in the correlated data collector");
            return new Record(this, null);
        }
    }

    public DataCollector getDataCollector() {
        return this.dataCollector;
    }

    public final DataType findTrueDataType() {
        DataType dataType = this.dataType;
        return dataType == null ? this.dataCollector.getDataType() : dataType;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Record)) {
            return false;
        }
        Record record = (Record) obj;
        return Objects.equal(this.dataCollector, record.dataCollector) && Objects.equal(this.dataType, record.dataType) && this.samplingRate == record.samplingRate && this.accuracyLevel == record.accuracyLevel;
    }

    /* synthetic */ Record(Builder builder, aab aabVar) {
        this(builder);
    }

    private Record(Builder builder) {
        this.accuracyLevel = builder.accuracyLevel;
        this.samplingRate = builder.samplingRate;
        this.dataType = builder.dataType;
        this.dataCollector = builder.dataCollector;
    }

    protected Record(Parcel parcel) {
        this.accuracyLevel = parcel.readInt();
        this.samplingRate = parcel.readLong();
        this.dataCollector = (DataCollector) parcel.readParcelable(DataCollector.class.getClassLoader());
        this.dataType = (DataType) parcel.readParcelable(DataType.class.getClassLoader());
    }

    class aab implements Parcelable.Creator<Record> {
        @Override // android.os.Parcelable.Creator
        public Record createFromParcel(Parcel parcel) {
            return new Record(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public Record[] newArray(int i) {
            return new Record[i];
        }

        aab() {
        }
    }

    Record(int i, long j, DataCollector dataCollector, DataType dataType) {
        this.accuracyLevel = i;
        this.samplingRate = j;
        this.dataCollector = dataCollector;
        this.dataType = dataType;
    }
}
