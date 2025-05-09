package com.huawei.hms.hihealth.data;

import android.os.Parcelable;
import android.util.Base64;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.health.aabq;
import com.huawei.hms.health.aabv;
import com.huawei.hms.health.aabw;
import com.huawei.hms.health.aaby;
import com.huawei.hms.health.aacs;
import com.huawei.openalliance.ad.constant.Constants;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class HealthRecord extends aabq {
    public static final Parcelable.Creator<HealthRecord> CREATOR = new aabq.aab(HealthRecord.class);

    @aaby(id = 3)
    private DataCollector mDataCollector;

    @aaby(id = 2)
    private long mEndTime;

    @aaby(id = 4)
    private Map<String, Value> mFieldValues;

    @aaby(id = 8)
    private String mHealthRecordId;

    @aaby(id = 5)
    private String mMetadata;

    @aaby(id = 1)
    private long mStartTime;

    @aaby(id = 7)
    private List<SampleSet> mSubDataDetails;

    @aaby(id = 6)
    private List<SamplePoint> mSubDataSummary;
    private List<SubDataRelation> subDataRelationList;

    public String toString() {
        return super.toString();
    }

    public void setSubDataSummary(List<SamplePoint> list) {
        this.mSubDataSummary = list;
    }

    public void setSubDataRelationList(List<SubDataRelation> list) {
        this.subDataRelationList = list;
    }

    public void setSubDataDetails(List<SampleSet> list) {
        this.mSubDataDetails = list;
    }

    public void setFieldValues(Map<String, Value> map) {
        if (map == null) {
            return;
        }
        for (Map.Entry<String, Value> entry : map.entrySet()) {
            this.mFieldValues.put(entry.getKey(), entry.getValue());
        }
    }

    public int hashCode() {
        return Objects.hash(Long.valueOf(this.mStartTime), Long.valueOf(this.mEndTime), this.mDataCollector, this.mFieldValues, this.mMetadata, this.mSubDataSummary, this.mSubDataDetails, this.mHealthRecordId);
    }

    public List<SamplePoint> getSubDataSummary() {
        return this.mSubDataSummary;
    }

    public List<SubDataRelation> getSubDataRelationList() {
        return this.subDataRelationList;
    }

    public static class Builder {
        private DataCollector mDataCollector;
        private long mEndTime;
        private String mHealthRecordId;
        private String mMetadata;
        private long mStartTime;
        private Map<String, Value> mFieldValues = new HashMap();
        private List<SamplePoint> mSubDataSummary = new ArrayList();
        private List<SampleSet> mSubDataDetails = new ArrayList();

        public HealthRecord build() {
            Preconditions.checkArgument(aacs.aab(this.mStartTime), "Must specify valid start time.");
            long j = this.mEndTime;
            Preconditions.checkArgument(j == 0 || (aacs.aab(j) && this.mEndTime >= this.mStartTime), "End time should be later than start time and the value ranges from 1388505600000000000ns to 4102415999000000000ns.");
            Preconditions.checkNotNull(this.mDataCollector, "DataCollector cannot be null");
            this.mHealthRecordId = Base64.encodeToString((this.mDataCollector.getDataStreamId() + Constants.LINK + this.mEndTime).getBytes(StandardCharsets.UTF_8), 2).replaceAll("\r|\n", "");
            return new HealthRecord(this.mStartTime, this.mEndTime, this.mDataCollector, this.mFieldValues, this.mMetadata, this.mSubDataSummary, this.mSubDataDetails, this.mHealthRecordId);
        }

        public Builder setSubDataSummary(List<SamplePoint> list) {
            if (list != null && !list.isEmpty()) {
                this.mSubDataSummary = list;
            }
            return this;
        }

        public Builder setSubDataDetails(List<SampleSet> list) {
            if (list != null && !list.isEmpty()) {
                this.mSubDataDetails = list;
            }
            return this;
        }

        public Builder setStartTime(long j, TimeUnit timeUnit) {
            long convert = TimeUnit.NANOSECONDS.convert(j, timeUnit);
            this.mStartTime = convert;
            Preconditions.checkArgument(aacs.aab(convert), "Start time has to be greater than 0 and the value ranges from 1388505600000000000ns to 4102415999000000000ns.");
            return this;
        }

        public Builder setMetadata(String str) {
            Preconditions.checkArgument(aacs.aabb(str), "HealthRecord mataData illegal");
            this.mMetadata = str;
            return this;
        }

        public Builder setFieldValue(Field field, Map<String, MapValue> map) {
            Value value = new Value(field.getFormat());
            for (Map.Entry<String, MapValue> entry : map.entrySet()) {
                int format = entry.getValue().getFormat();
                if (format == 1) {
                    value.setKeyValue(entry.getKey(), entry.getValue().asIntValue());
                } else if (format == 2) {
                    value.setKeyValue(entry.getKey(), entry.getValue().asDoubleValue());
                } else if (format == 3) {
                    value.setKeyValue(entry.getKey(), entry.getValue().asStringValue());
                } else if (format == 5) {
                    value.setKeyValue(entry.getKey(), entry.getValue().asLongValue());
                }
            }
            this.mFieldValues.put(field.toString(), value);
            return this;
        }

        public Builder setFieldValue(Field field, String str) {
            Value value = new Value(field.getFormat());
            value.setStringValue(str);
            this.mFieldValues.put(field.toString(), value);
            return this;
        }

        public Builder setFieldValue(Field field, long j) {
            Value value = new Value(field.getFormat());
            value.setLongValue(j);
            this.mFieldValues.put(field.toString(), value);
            return this;
        }

        public Builder setFieldValue(Field field, int i) {
            Value value = new Value(field.getFormat());
            value.setIntValue(i);
            this.mFieldValues.put(field.toString(), value);
            return this;
        }

        public Builder setFieldValue(Field field, float f) {
            Value value = new Value(field.getFormat());
            value.setDoubleValue(Double.parseDouble(String.valueOf(f)));
            this.mFieldValues.put(field.toString(), value);
            return this;
        }

        public Builder setFieldValue(Field field, double d) {
            Value value = new Value(field.getFormat());
            value.setDoubleValue(d);
            this.mFieldValues.put(field.toString(), value);
            return this;
        }

        public Builder setEndTime(long j, TimeUnit timeUnit) {
            long convert = TimeUnit.NANOSECONDS.convert(j, timeUnit);
            this.mEndTime = convert;
            Preconditions.checkArgument(aacs.aab(convert), "End time has to be equal to 0 or the value ranges from 1388505600000ms to 4102415999000ms.");
            return this;
        }

        public Builder setDataCollector(DataCollector dataCollector) {
            this.mDataCollector = dataCollector;
            return this;
        }

        public Builder(DataCollector dataCollector) {
            this.mDataCollector = dataCollector;
            for (Field field : dataCollector.getDataType().getFields()) {
                this.mFieldValues.put(field.toString(), new Value(field.getFormat()));
            }
        }
    }

    public List<SampleSet> getSubDataDetails() {
        return this.mSubDataDetails;
    }

    public long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.mStartTime, TimeUnit.NANOSECONDS);
    }

    public String getMetadata() {
        return this.mMetadata;
    }

    public String getHealthRecordId() {
        return this.mHealthRecordId;
    }

    public Map<String, Value> getFieldValues() {
        return this.mFieldValues;
    }

    public Value getFieldValue(Field field) {
        Preconditions.checkArgument(this.mFieldValues.containsKey(field.toString()), "Invalid field");
        return this.mFieldValues.get(field.toString());
    }

    public long getEndTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.mEndTime, TimeUnit.NANOSECONDS);
    }

    public DataCollector getDataCollector() {
        return this.mDataCollector;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HealthRecord)) {
            return false;
        }
        HealthRecord healthRecord = (HealthRecord) obj;
        return Objects.equals(Long.valueOf(this.mStartTime), Long.valueOf(healthRecord.mStartTime)) && Objects.equals(Long.valueOf(this.mEndTime), Long.valueOf(healthRecord.mEndTime)) && Objects.equals(this.mDataCollector, healthRecord.mDataCollector) && Objects.equals(this.mFieldValues, healthRecord.mFieldValues) && Objects.equals(this.mMetadata, healthRecord.mMetadata) && Objects.equals(this.mSubDataSummary, healthRecord.mSubDataSummary) && Objects.equals(this.mSubDataDetails, healthRecord.mSubDataDetails) && Objects.equals(this.mHealthRecordId, healthRecord.mHealthRecordId);
    }

    public void addSubDataSummary(List<SamplePoint> list) {
        this.mSubDataSummary.addAll(list);
    }

    public void addSubDataDetails(List<SampleSet> list) {
        this.mSubDataDetails.addAll(list);
    }

    @aabw
    private HealthRecord(@aabv(id = 1) long j, @aabv(id = 2) long j2, @aabv(id = 3) DataCollector dataCollector, @aabv(id = 4) Map<String, Value> map, @aabv(id = 5) String str, @aabv(id = 6) List<SamplePoint> list, @aabv(id = 7) List<SampleSet> list2, @aabv(id = 8) String str2) {
        this.mStartTime = j;
        this.mEndTime = j2;
        this.mDataCollector = dataCollector;
        this.mFieldValues = map;
        this.mMetadata = str;
        this.mSubDataSummary = list;
        this.mSubDataDetails = list2;
        this.mHealthRecordId = str2;
    }
}
