package com.huawei.hms.hihealth.data;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.core.location.LocationRequestCompat;
import com.huawei.hms.common.internal.Objects;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.health.aabz;
import com.huawei.hms.health.aac;
import com.huawei.hms.health.aacs;
import java.math.BigDecimal;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class SamplePoint implements Parcelable {
    private static final String APPID_SDKVER_CACHE = "sAppIdToSdkVersion";
    private static final String CALLING_APP_INFO_CLASS = "com.huawei.hms.hihealth.service.api.CallingAppInfo";
    public static final Parcelable.Creator<SamplePoint> CREATOR = new aab();
    private static final int INITIAL_ID = -1;
    private static final String PID_APPID_CACHE = "sAppIdCache";
    private static final String TAG = "SamplePoint";
    private DataCollector dataCollector;
    private int dataTypeId;
    private Map<String, Value> fieldValues;
    private long id;
    private long insertionTime;
    private boolean isMerged;
    private DataType mDataType;
    private String metadata;
    private int pushed;
    private DataCollector rawDataCollector;
    private long rawSamplingTime;
    private long samplingTime;
    private long startTime;

    protected SamplePoint(Parcel parcel) {
        this.rawSamplingTime = 0L;
        this.insertionTime = 0L;
        this.startTime = parcel.readLong();
        this.samplingTime = parcel.readLong();
        this.dataCollector = (DataCollector) parcel.readParcelable(SamplePoint.class.getClassLoader());
        this.rawDataCollector = (DataCollector) parcel.readParcelable(SamplePoint.class.getClassLoader());
        HashMap hashMap = new HashMap();
        this.fieldValues = hashMap;
        ClassLoader classLoader = Value.class.getClassLoader();
        int readInt = parcel.readInt();
        for (readInt = readInt > 300000 ? 300000 : readInt; readInt > 0; readInt--) {
            hashMap.put(parcel.readValue(classLoader), parcel.readValue(classLoader));
        }
        this.metadata = parcel.readString();
    }

    @Deprecated
    public static SamplePoint extract(Intent intent) {
        return null;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0066 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // android.os.Parcelable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void writeToParcel(android.os.Parcel r6, int r7) {
        /*
            r5 = this;
            java.lang.String r0 = "SamplePoint"
            java.lang.String r1 = "com.huawei.hms.hihealth.service.api.CallingAppInfo"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch: java.lang.Exception -> L3f java.lang.Throwable -> L45
            java.lang.reflect.Field[] r2 = r1.getDeclaredFields()     // Catch: java.lang.Exception -> L3f java.lang.Throwable -> L45
            java.lang.String r3 = "sAppIdToSdkVersion"
            java.lang.reflect.Field r3 = r1.getDeclaredField(r3)     // Catch: java.lang.Exception -> L3f java.lang.Throwable -> L45
            java.lang.String r4 = "sAppIdCache"
            java.lang.reflect.Field r1 = r1.getDeclaredField(r4)     // Catch: java.lang.Exception -> L3f java.lang.Throwable -> L45
            com.huawei.hms.hihealth.data.SamplePoint$aaba r4 = new com.huawei.hms.hihealth.data.SamplePoint$aaba     // Catch: java.lang.Exception -> L3f java.lang.Throwable -> L45
            r4.<init>(r3, r1)     // Catch: java.lang.Exception -> L3f java.lang.Throwable -> L45
            r4.run()     // Catch: java.lang.Exception -> L3f java.lang.Throwable -> L45
            java.lang.Object r3 = r3.get(r2)     // Catch: java.lang.Exception -> L3f java.lang.Throwable -> L45
            java.util.Map r3 = (java.util.Map) r3     // Catch: java.lang.Exception -> L3f java.lang.Throwable -> L45
            java.lang.Object r1 = r1.get(r2)     // Catch: java.lang.Exception -> L3f java.lang.Throwable -> L45
            java.util.Map r1 = (java.util.Map) r1     // Catch: java.lang.Exception -> L3f java.lang.Throwable -> L45
            int r2 = android.os.Binder.getCallingPid()     // Catch: java.lang.Exception -> L3f java.lang.Throwable -> L45
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch: java.lang.Exception -> L3f java.lang.Throwable -> L45
            java.lang.Object r1 = r1.get(r2)     // Catch: java.lang.Exception -> L3f java.lang.Throwable -> L45
            java.lang.Object r1 = r3.get(r1)     // Catch: java.lang.Exception -> L3f java.lang.Throwable -> L45
            java.lang.String r1 = (java.lang.String) r1     // Catch: java.lang.Exception -> L3f java.lang.Throwable -> L45
            goto L4b
        L3f:
            java.lang.String r1 = "catch basic exception"
            com.huawei.hms.health.aabz.aab(r0, r1)
            goto L4a
        L45:
            java.lang.String r1 = "catch ClassNotFoundException or NoSuchFieldException or SecurityException"
            com.huawei.hms.health.aabz.aabb(r0, r1)
        L4a:
            r1 = 0
        L4b:
            long r2 = r5.startTime
            r6.writeLong(r2)
            long r2 = r5.samplingTime
            r6.writeLong(r2)
            com.huawei.hms.hihealth.data.DataCollector r2 = r5.dataCollector
            r6.writeParcelable(r2, r7)
            com.huawei.hms.hihealth.data.DataCollector r2 = r5.rawDataCollector
            r6.writeParcelable(r2, r7)
            java.util.Map<java.lang.String, com.huawei.hms.hihealth.data.Value> r7 = r5.fieldValues
            r6.writeMap(r7)
            if (r1 == 0) goto L7d
            boolean r7 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.NumberFormatException -> L83
            if (r7 != 0) goto L88
            java.lang.String r7 = "."
            java.lang.String r2 = ""
            java.lang.String r7 = r1.replace(r7, r2)     // Catch: java.lang.NumberFormatException -> L83
            int r7 = java.lang.Integer.parseInt(r7)     // Catch: java.lang.NumberFormatException -> L83
            r1 = 520302(0x7f06e, float:7.29098E-40)
            if (r7 <= r1) goto L88
        L7d:
            java.lang.String r7 = r5.metadata     // Catch: java.lang.NumberFormatException -> L83
            r6.writeString(r7)     // Catch: java.lang.NumberFormatException -> L83
            goto L88
        L83:
            java.lang.String r6 = "parse sdkVersion fail : catch NumberFormatException"
            com.huawei.hms.health.aabz.aab(r0, r6)
        L88:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.hihealth.data.SamplePoint.writeToParcel(android.os.Parcel, int):void");
    }

    public final String toString() {
        StringBuilder aab2 = com.huawei.hms.health.aab.aab("SamplePoint{");
        aab2.append(Arrays.toString(this.fieldValues.values().toArray()));
        StringBuilder aab3 = com.huawei.hms.health.aab.aab("[");
        aab3.append(this.startTime);
        aab3.append(", ");
        aab3.append(this.samplingTime);
        aab2.append(aab3.toString());
        StringBuilder aab4 = com.huawei.hms.health.aab.aab(", rawSamplingTime=");
        aab4.append(this.rawSamplingTime);
        aab2.append(aab4.toString());
        StringBuilder aab5 = com.huawei.hms.health.aab.aab(", insertionTime=");
        aab5.append(this.insertionTime);
        aab5.append("]");
        aab2.append(aab5.toString());
        StringBuilder aab6 = com.huawei.hms.health.aab.aab("[");
        DataCollector dataCollector = this.dataCollector;
        aab6.append(dataCollector != null ? dataCollector.toString() : "NULL");
        aab6.append("]}");
        aab2.append(aab6.toString());
        StringBuilder aab7 = com.huawei.hms.health.aab.aab("[");
        DataType dataType = this.mDataType;
        aab7.append(dataType != null ? dataType.toString() : "NULL");
        aab7.append("]}");
        aab2.append(aab7.toString());
        StringBuilder aab8 = com.huawei.hms.health.aab.aab("/");
        DataCollector dataCollector2 = this.rawDataCollector;
        aab8.append(dataCollector2 != null ? dataCollector2.toString() : "NULL");
        aab8.append("]}");
        aab2.append(aab8.toString());
        return String.format(Locale.ENGLISH, aab2.toString(), new Object[0]);
    }

    public final SamplePoint setTimeInterval(long j, long j2, TimeUnit timeUnit) {
        DataType dataType = this.mDataType;
        DataCollector dataCollector = this.dataCollector;
        if (dataCollector != null) {
            dataType = dataCollector.getDataType();
        }
        checkIntervalValidity(j, j2, timeUnit, dataType);
        this.startTime = TimeUnit.NANOSECONDS.convert(j, timeUnit);
        this.samplingTime = TimeUnit.NANOSECONDS.convert(j2, timeUnit);
        Preconditions.checkState(this.startTime > 1388505600000000000L, "Start time must be later than default start time: 20140101 00:00:000");
        Preconditions.checkState(this.samplingTime >= this.startTime, "the start time must be less than the end time");
        return this;
    }

    public final SamplePoint setSamplingTime(long j, TimeUnit timeUnit) {
        Preconditions.checkArgument(timeUnit != null, "TimeUnit should not be null.");
        DataType dataType = this.mDataType;
        DataCollector dataCollector = this.dataCollector;
        if (dataCollector != null) {
            dataType = dataCollector.getDataType();
        }
        DataType dataType2 = dataType;
        Preconditions.checkState(!(this.startTime == 0 || this.samplingTime == 0) || aac.aaba(dataType2) < 2, "This is an interval data type, the start time has not been set yet, Call setTimeInterval() instead.");
        long convert = TimeUnit.NANOSECONDS.convert(j, timeUnit);
        long j2 = this.startTime;
        if (j2 == 0 || j2 == this.samplingTime) {
            j2 = convert;
        }
        checkIntervalValidity(j2, convert, TimeUnit.NANOSECONDS, dataType2);
        this.startTime = j2;
        this.samplingTime = convert;
        return this;
    }

    public void setPushed(int i) {
        this.pushed = i;
    }

    public void setMetadata(String str) {
        Preconditions.checkArgument(aacs.aabb(str), "SamplePoint mataData illegal");
        this.metadata = str;
    }

    public void setMerged(boolean z) {
        this.isMerged = z;
    }

    public static class Builder {
        private DataCollector dataCollector;
        private int dataTypeId;
        long id;
        private boolean isMerged;
        private DataType mDataType;
        private String metadata;
        private int pushed;
        private DataCollector rawDataCollector;
        private long samplingTime;
        private long startTime;
        private final Map<String, Value> valuesMap;

        public Builder setTimeInterval(long j, long j2, TimeUnit timeUnit) {
            DataType dataType = this.mDataType;
            DataCollector dataCollector = this.dataCollector;
            if (dataCollector != null) {
                dataType = dataCollector.getDataType();
            }
            SamplePoint.checkIntervalValidity(j, j2, timeUnit, dataType);
            this.startTime = TimeUnit.NANOSECONDS.convert(j, timeUnit);
            this.samplingTime = TimeUnit.NANOSECONDS.convert(j2, timeUnit);
            Preconditions.checkState(this.startTime > 1388505600000000000L, "Start time must be later than default start time: 20140101 00:00:000");
            Preconditions.checkState(this.samplingTime >= this.startTime, "the start time must be less than the end time");
            return this;
        }

        public Builder setSamplingTime(long j, TimeUnit timeUnit) {
            Preconditions.checkArgument(timeUnit != null, "TimeUnit should not be null.");
            DataType dataType = this.mDataType;
            DataCollector dataCollector = this.dataCollector;
            if (dataCollector != null) {
                dataType = dataCollector.getDataType();
            }
            DataType dataType2 = dataType;
            Preconditions.checkState(!(this.startTime == 0 || this.samplingTime == 0) || aac.aaba(dataType2) < 2, "This is an interval data type, the start time has not been set yet, Call setTimeInterval() instead.");
            long convert = TimeUnit.NANOSECONDS.convert(j, timeUnit);
            long j2 = this.startTime;
            if (j2 == 0 || j2 == this.samplingTime) {
                j2 = convert;
            }
            SamplePoint.checkIntervalValidity(j2, convert, TimeUnit.NANOSECONDS, dataType2);
            this.startTime = j2;
            this.samplingTime = convert;
            return this;
        }

        public Builder setRawDataCollector(DataCollector dataCollector) {
            this.rawDataCollector = dataCollector;
            return this;
        }

        public Builder setPushed(int i) {
            this.pushed = i;
            return this;
        }

        public Builder setMetadata(String str) {
            Preconditions.checkArgument(aacs.aabb(str), "SamplePoint mataData illegal");
            this.metadata = str;
            return this;
        }

        public Builder setMerged(boolean z) {
            this.isMerged = z;
            return this;
        }

        public Builder setIntValueBatch(int... iArr) {
            throw new IllegalArgumentException("set value failed! This method is deprecated!");
        }

        public void setIntFieldValue(String str, Map<String, Integer> map) {
            Value value = new Value(4);
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                value.setKeyValue(entry.getKey(), entry.getValue().intValue());
            }
            this.valuesMap.put(Field.getFieldString(str, 4), value);
        }

        public void setIntFieldValue(Field field, Map<String, Integer> map) {
            Value value = new Value(field.getFormat());
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                value.setKeyValue(entry.getKey(), entry.getValue().intValue());
            }
            this.valuesMap.put(field.toString(), value);
        }

        public Builder setId(long j) {
            this.id = j;
            return this;
        }

        public Builder setFloatValueBatch(float... fArr) {
            throw new IllegalArgumentException("set value failed! This method is deprecated!");
        }

        public Builder setFieldValue(String str, Map<String, Value> map) {
            Value value = new Value(4);
            for (Map.Entry<String, Value> entry : map.entrySet()) {
                int format = entry.getValue().getFormat();
                if (format == 1) {
                    value.setKeyValue(entry.getKey(), entry.getValue().asIntValue());
                } else if (format == 2) {
                    value.setKeyValue(entry.getKey(), entry.getValue().asDoubleValue());
                } else if (format == 3) {
                    value.setKeyValue(entry.getKey(), entry.getValue().asStringValue());
                } else if (format == 4) {
                    setFieldMapValue(str, entry.getValue().getMap());
                } else if (format == 5) {
                    value.setKeyValue(entry.getKey(), entry.getValue().asLongValue());
                }
            }
            this.valuesMap.put(Field.getFieldString(str, 4), value);
            return this;
        }

        public Builder setFieldValue(String str, String str2) {
            Value value = new Value(3);
            value.setStringValue(str2);
            this.valuesMap.put(Field.getFieldString(str, 3), value);
            return this;
        }

        public Builder setFieldValue(String str, long j) {
            Value value = new Value(5);
            value.setLongValue(j);
            this.valuesMap.put(Field.getFieldString(str, 5), value);
            return this;
        }

        public Builder setFieldValue(String str, int i) {
            Value value = new Value(1);
            value.setIntValue(i);
            this.valuesMap.put(Field.getFieldString(str, 1), value);
            return this;
        }

        @Deprecated
        public Builder setFieldValue(String str, float f) {
            Value value = new Value(2);
            value.setDoubleValue(new BigDecimal(String.valueOf(f)).doubleValue());
            this.valuesMap.put(Field.getFieldString(str, 2), value);
            return this;
        }

        public Builder setFieldValue(String str, double d) {
            Value value = new Value(2);
            value.setDoubleValue(d);
            this.valuesMap.put(Field.getFieldString(str, 2), value);
            return this;
        }

        public Builder setFieldValue(Field field, Map<String, Value> map) {
            Value value = new Value(field.getFormat());
            for (Map.Entry<String, Value> entry : map.entrySet()) {
                int format = entry.getValue().getFormat();
                if (format == 1) {
                    value.setKeyValue(entry.getKey(), entry.getValue().asIntValue());
                } else if (format == 2) {
                    value.setKeyValue(entry.getKey(), entry.getValue().asDoubleValue());
                } else if (format == 3) {
                    value.setKeyValue(entry.getKey(), entry.getValue().asStringValue());
                } else if (format == 4) {
                    setFieldMapValue(field, entry.getValue().getMap());
                } else if (format == 5) {
                    value.setKeyValue(entry.getKey(), entry.getValue().asLongValue());
                }
            }
            this.valuesMap.put(field.toString(), value);
            return this;
        }

        public Builder setFieldValue(Field field, String str) {
            Value value = new Value(field.getFormat());
            value.setStringValue(str);
            this.valuesMap.put(field.toString(), value);
            return this;
        }

        public Builder setFieldValue(Field field, long j) {
            Value value = new Value(field.getFormat());
            value.setLongValue(j);
            this.valuesMap.put(field.toString(), value);
            return this;
        }

        public Builder setFieldValue(Field field, int i) {
            Value value = new Value(field.getFormat());
            value.setIntValue(i);
            this.valuesMap.put(field.toString(), value);
            return this;
        }

        @Deprecated
        public Builder setFieldValue(Field field, float f) {
            Value value = new Value(field.getFormat());
            value.setDoubleValue(new BigDecimal(String.valueOf(f)).doubleValue());
            this.valuesMap.put(field.toString(), value);
            return this;
        }

        public Builder setFieldValue(Field field, double d) {
            Value value = new Value(field.getFormat());
            value.setDoubleValue(d);
            this.valuesMap.put(field.toString(), value);
            return this;
        }

        public Builder setFieldMapValue(String str, Map<String, MapValue> map) {
            Value value = new Value(4);
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
            this.valuesMap.put(Field.getFieldString(str, 4), value);
            return this;
        }

        public Builder setFieldMapValue(Field field, Map<String, MapValue> map) {
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
            this.valuesMap.put(field.toString(), value);
            return this;
        }

        public Builder setDataTypeId(int i) {
            this.dataTypeId = i;
            return this;
        }

        @Deprecated
        public Builder setBlobFieldValue(String str, Map<String, Float> map) {
            Value value = new Value(4);
            for (Map.Entry<String, Float> entry : map.entrySet()) {
                value.setKeyValue(entry.getKey(), entry.getValue().floatValue());
            }
            this.valuesMap.put(Field.getFieldString(str, 4), value);
            return this;
        }

        @Deprecated
        public Builder setBlobFieldValue(Field field, Map<String, Float> map) {
            Value value = new Value(field.getFormat());
            for (Map.Entry<String, Float> entry : map.entrySet()) {
                value.setKeyValue(entry.getKey(), entry.getValue().floatValue());
            }
            this.valuesMap.put(field.toString(), value);
            return this;
        }

        public Builder setBlobFieldDoubleValue(String str, Map<String, Double> map) {
            Value value = new Value(4);
            for (Map.Entry<String, Double> entry : map.entrySet()) {
                value.setKeyValue(entry.getKey(), entry.getValue().doubleValue());
            }
            this.valuesMap.put(Field.getFieldString(str, 4), value);
            return this;
        }

        public Builder setBlobFieldDoubleValue(Field field, Map<String, Double> map) {
            Value value = new Value(field.getFormat());
            for (Map.Entry<String, Double> entry : map.entrySet()) {
                value.setKeyValue(entry.getKey(), entry.getValue().doubleValue());
            }
            this.valuesMap.put(field.toString(), value);
            return this;
        }

        public Builder setActivityField(Field field, String str) {
            Value value = new Value(field.getFormat());
            value.setStringValue(str);
            this.valuesMap.put(field.toString(), value);
            return this;
        }

        public boolean isMerged() {
            return this.isMerged;
        }

        public int getDataTypeId() {
            return this.dataTypeId;
        }

        public SamplePoint build() {
            return new SamplePoint(this.dataCollector, this.rawDataCollector, this.startTime, this.samplingTime, this.valuesMap, this.id, this.isMerged, this.dataTypeId, this.pushed, this.metadata, this.mDataType, null);
        }

        public Builder(DataType dataType) {
            if (dataType == null || !(dataType.isPolymerized() || dataType.getName().startsWith("com.huawei.activity.feature"))) {
                aabz.aab(SamplePoint.TAG, "the dataType is illegal " + dataType);
                throw new IllegalStateException("only polymerized dataType can use this constructor, please use Builder(DataCollector) constructor");
            }
            this.mDataType = dataType;
            this.valuesMap = new HashMap();
            for (Field field : dataType.getFields()) {
                this.valuesMap.put(field.toString(), new Value(field.getFormat()));
            }
        }

        public Builder(DataCollector dataCollector) {
            this.dataCollector = dataCollector;
            this.valuesMap = new HashMap();
            this.id = -1L;
            for (Field field : dataCollector.getDataType().getFields()) {
                this.valuesMap.put(field.toString(), new Value(field.getFormat()));
            }
        }
    }

    @Deprecated
    public final SamplePoint setIntValues(int... iArr) {
        throw new IllegalArgumentException("set value failed! This method is deprecated!");
    }

    public void setInsertionTime(long j, TimeUnit timeUnit) {
        this.insertionTime = TimeUnit.NANOSECONDS.convert(j, timeUnit);
    }

    public void setId(long j) {
        this.id = j;
    }

    @Deprecated
    public final SamplePoint setFloatValues(float... fArr) {
        throw new IllegalArgumentException("set value failed! This method is deprecated!");
    }

    public void setFieldMapValue(Field field, Map<String, MapValue> map) {
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
        this.fieldValues.put(field.toString(), value);
    }

    public void setDataTypeId(int i) {
        this.dataTypeId = i;
    }

    public boolean isMerged() {
        return this.isMerged;
    }

    public final int hashCode() {
        return Objects.hashCode(this.dataCollector, Long.valueOf(this.samplingTime), Long.valueOf(this.startTime));
    }

    public long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.startTime, TimeUnit.NANOSECONDS);
    }

    public long getSamplingTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.samplingTime, TimeUnit.NANOSECONDS);
    }

    public final DataCollector getRawDataCollector() {
        DataCollector dataCollector = this.rawDataCollector;
        return dataCollector != null ? dataCollector : this.dataCollector;
    }

    public int getPushed() {
        return this.pushed;
    }

    public final DataCollector getNullableRawCollector() {
        return this.rawDataCollector;
    }

    public String getMetadata() {
        return this.metadata;
    }

    public long getInsertionTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.insertionTime, TimeUnit.NANOSECONDS);
    }

    public long getId() {
        return this.id;
    }

    public Map<String, Value> getFieldValues() {
        return this.fieldValues;
    }

    public Value getFieldValue(String str) {
        DataCollector dataCollector;
        Map<String, Value> map;
        if (this.fieldValues.containsKey(str)) {
            map = this.fieldValues;
        } else if (this.fieldValues.containsKey(Field.getFieldString(str, 0))) {
            map = this.fieldValues;
            str = Field.getFieldString(str, 0);
        } else {
            if (!this.fieldValues.containsKey(Field.getFieldString(str, 1))) {
                DataType dataType = this.mDataType;
                if ((dataType == null || !aabz.aaba(dataType.getFields()).booleanValue()) && ((dataCollector = this.dataCollector) == null || !aabz.aaba(dataCollector.getDataType().getFields()).booleanValue())) {
                    Value value = new Value(0);
                    this.fieldValues.put(str, value);
                    return value;
                }
                throw new IllegalArgumentException("Invalid field: " + str + " in dataType.");
            }
            map = this.fieldValues;
            str = Field.getFieldString(str, 1);
        }
        return map.get(str);
    }

    public Value getFieldValue(Field field) {
        return getFieldValue(field.getName());
    }

    public long getEndTime(TimeUnit timeUnit) {
        return getSamplingTime(timeUnit);
    }

    public int getDataTypeId() {
        return this.dataTypeId;
    }

    public DataType getDataType() {
        DataCollector dataCollector = this.dataCollector;
        return dataCollector == null ? this.mDataType : dataCollector.getDataType();
    }

    public DataCollector getDataCollector() {
        return this.dataCollector;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SamplePoint)) {
            return false;
        }
        SamplePoint samplePoint = (SamplePoint) obj;
        return this.samplingTime == samplePoint.samplingTime && this.startTime == samplePoint.startTime && Objects.equal(this.dataCollector, samplePoint.dataCollector) && Objects.equal(this.fieldValues, samplePoint.fieldValues) && Objects.equal(this.rawDataCollector, samplePoint.rawDataCollector);
    }

    public final void checkIntervalValidity() {
        checkIntervalValidity(this.startTime, this.samplingTime, TimeUnit.NANOSECONDS, getDataType());
    }

    public void addMetadata(String str, String str2) {
        Preconditions.checkArgument(aacs.aabb(str), "metadata key illegal");
        Preconditions.checkArgument(aacs.aabb(str2), "metadata value illegal");
        try {
            JSONObject jSONObject = TextUtils.isEmpty(this.metadata) ? new JSONObject() : new JSONObject(this.metadata);
            jSONObject.remove(str);
            jSONObject.put(str, str2);
            this.metadata = jSONObject.toString();
        } catch (JSONException unused) {
            aabz.aabb(TAG, "addMetadata catch JSONException");
            throw new IllegalArgumentException("addMetadata catch JSONException");
        }
    }

    public static SamplePoint create(DataCollector dataCollector) {
        return new SamplePoint(dataCollector);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void checkIntervalValidity(long j, long j2, TimeUnit timeUnit, DataType dataType) {
        boolean z;
        String str;
        Preconditions.checkArgument(j > 0 && j2 > 0, "Neither start time nor end time should be set to 0");
        Preconditions.checkArgument(TimeUnit.NANOSECONDS.convert(j, timeUnit) < LocationRequestCompat.PASSIVE_INTERVAL && TimeUnit.NANOSECONDS.convert(j2, timeUnit) < LocationRequestCompat.PASSIVE_INTERVAL, "The start or end time is too large, please check the time or unit.");
        long aab2 = aac.aab(dataType);
        Preconditions.checkArgument(TimeUnit.SECONDS.convert(j2, timeUnit) - TimeUnit.SECONDS.convert(j, timeUnit) <= aab2, String.format(Locale.ENGLISH, "The sampling time interval should be less than %s days.", Long.valueOf(TimeUnit.DAYS.convert(aab2, TimeUnit.SECONDS))));
        int aaba2 = aac.aaba(dataType);
        if (aaba2 == 2 || aaba2 == 3) {
            z = j < j2;
            str = "This is an interval data type, the start time should be less than the end time.";
        } else {
            z = j <= j2;
            str = "The start time should not be greater than end time";
        }
        Preconditions.checkArgument(z, str);
    }

    public static Builder builder(DataType dataType) {
        return new Builder(dataType);
    }

    public static Builder builder(DataCollector dataCollector) {
        Preconditions.checkNotNull(dataCollector, "DataCollector cannot be null");
        return new Builder(dataCollector);
    }

    /* synthetic */ SamplePoint(DataCollector dataCollector, DataCollector dataCollector2, long j, long j2, Map map, long j3, boolean z, int i, int i2, String str, DataType dataType, aab aabVar) {
        this(dataCollector, dataCollector2, j, j2, map, j3, z, i, i2, str, dataType);
    }

    static class aaba implements PrivilegedAction {
        private java.lang.reflect.Field aab;
        private java.lang.reflect.Field aaba;

        @Override // java.security.PrivilegedAction
        public Object run() {
            this.aab.setAccessible(true);
            this.aaba.setAccessible(true);
            return null;
        }

        aaba(java.lang.reflect.Field field, java.lang.reflect.Field field2) {
            this.aab = field;
            this.aaba = field2;
        }
    }

    private SamplePoint(DataCollector dataCollector, DataCollector dataCollector2, long j, long j2, Map<String, Value> map, long j3, boolean z, int i, int i2, String str, DataType dataType) {
        this.rawSamplingTime = 0L;
        this.insertionTime = 0L;
        this.dataCollector = dataCollector;
        this.rawDataCollector = dataCollector2;
        this.startTime = j;
        this.samplingTime = j2;
        this.fieldValues = map;
        this.id = j3;
        this.isMerged = z;
        this.dataTypeId = i;
        this.pushed = i2;
        this.metadata = str;
        this.mDataType = dataType;
    }

    class aab implements Parcelable.Creator<SamplePoint> {
        @Override // android.os.Parcelable.Creator
        public SamplePoint createFromParcel(Parcel parcel) {
            return new SamplePoint(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public SamplePoint[] newArray(int i) {
            return new SamplePoint[i];
        }

        aab() {
        }
    }

    protected SamplePoint(DataCollector dataCollector) {
        this.rawSamplingTime = 0L;
        this.insertionTime = 0L;
        Preconditions.checkNotNull(dataCollector, "DataCollector cannot be null");
        this.dataCollector = dataCollector;
        List<Field> fields = dataCollector.getDataType().getFields();
        this.fieldValues = new HashMap(fields.size());
        for (Field field : fields) {
            this.fieldValues.put(field.toString(), new Value(field.getFormat()));
        }
    }
}
