package com.huawei.hihealth;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import health.compact.a.HiDateUtil;
import java.util.Objects;

/* loaded from: classes.dex */
public class HiHealthData implements Parcelable, Comparable<HiHealthData> {
    public static final Parcelable.Creator<HiHealthData> CREATOR = new Parcelable.Creator<HiHealthData>() { // from class: com.huawei.hihealth.HiHealthData.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: buH_, reason: merged with bridge method [inline-methods] */
        public HiHealthData createFromParcel(Parcel parcel) {
            return new HiHealthData(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public HiHealthData[] newArray(int i) {
            return new HiHealthData[i];
        }
    };
    private static final int DEFAULT_INT_VALUE = 0;
    private static final long DEFAULT_LONG_VALUE = 0;
    private static final String DEFAULT_STRING_VALUE = "";

    @SerializedName("deviceUuid")
    private String deviceUuid;

    @SerializedName("ownerId")
    private int ownerId;

    @SerializedName("type")
    private int type;

    @SerializedName("valueHolder")
    private ContentValues valueHolder;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HiHealthData() {
        this.valueHolder = new ContentValues();
    }

    public HiHealthData(int i) {
        this.valueHolder = new ContentValues();
        this.type = i;
    }

    public HiHealthData(Parcel parcel) {
        this.deviceUuid = parcel.readString();
        this.ownerId = parcel.readInt();
        this.type = parcel.readInt();
        this.valueHolder = (ContentValues) parcel.readParcelable(ContentValues.class.getClassLoader());
    }

    public String getDeviceUuid() {
        return this.deviceUuid;
    }

    public void setDeviceUuid(String str) {
        this.deviceUuid = (String) ResultUtils.a(str);
    }

    public int getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(int i) {
        this.ownerId = i;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = ((Integer) ResultUtils.a(Integer.valueOf(i))).intValue();
    }

    private void setValueHolder(ContentValues contentValues) {
        this.valueHolder = contentValues;
    }

    public ContentValues getValueHolder() {
        return this.valueHolder;
    }

    public void setTimeInterval(long j, long j2) {
        this.valueHolder.put("start_time", (Long) ResultUtils.a(Long.valueOf(j)));
        this.valueHolder.put("end_time", (Long) ResultUtils.a(Long.valueOf(j2)));
    }

    public long getStartTime() {
        Long asLong = this.valueHolder.getAsLong("start_time");
        if (asLong == null) {
            return 0L;
        }
        return asLong.longValue();
    }

    public void setStartTime(long j) {
        this.valueHolder.put("start_time", (Long) ResultUtils.a(Long.valueOf(j)));
    }

    public long getDay() {
        if (this.valueHolder.getAsLong("start_time") == null) {
            return 0L;
        }
        return HiDateUtil.c(r0.longValue());
    }

    public long getEndTime() {
        Long asLong = this.valueHolder.getAsLong("end_time");
        if (asLong == null) {
            return 0L;
        }
        return asLong.longValue();
    }

    public void setEndTime(long j) {
        this.valueHolder.put("end_time", (Long) ResultUtils.a(Long.valueOf(j)));
    }

    public long getCreateTime() {
        Long asLong = this.valueHolder.getAsLong("create_time");
        if (asLong == null) {
            return System.currentTimeMillis();
        }
        return asLong.longValue();
    }

    public void setCreateTime(long j) {
        this.valueHolder.put("create_time", Long.valueOf(j));
    }

    public long getModifiedTime() {
        Long asLong = this.valueHolder.getAsLong("modified_time");
        if (asLong == null) {
            return System.currentTimeMillis();
        }
        return asLong.longValue();
    }

    public void setModifiedTime(long j) {
        this.valueHolder.put("modified_time", Long.valueOf(j));
    }

    public void setValue(double d) {
        this.valueHolder.put("point_value", (Double) ResultUtils.a(Double.valueOf(d)));
    }

    public void setValue(int i) {
        this.valueHolder.put("point_value", Integer.valueOf(i));
    }

    public double getValue() {
        Double asDouble = this.valueHolder.getAsDouble("point_value");
        if (asDouble == null) {
            return 0.0d;
        }
        return asDouble.doubleValue();
    }

    public void setValue(float f) {
        this.valueHolder.put("point_value", Float.valueOf(f));
    }

    public int getIntValue() {
        Integer asInteger = this.valueHolder.getAsInteger("point_value");
        if (asInteger == null) {
            return 0;
        }
        return asInteger.intValue();
    }

    public float getFloatValue() {
        Float asFloat = this.valueHolder.getAsFloat("point_value");
        if (asFloat == null) {
            return 0.0f;
        }
        return asFloat.floatValue();
    }

    public int getPointUnit() {
        Integer asInteger = this.valueHolder.getAsInteger(" point_unit");
        if (asInteger == null) {
            return 0;
        }
        return asInteger.intValue();
    }

    public void setPointUnit(int i) {
        this.valueHolder.put(" point_unit", Integer.valueOf(i));
    }

    public String getSequenceData() {
        return this.valueHolder.getAsString("sequence_data");
    }

    public void setSequenceData(String str) {
        this.valueHolder.put("sequence_data", str);
    }

    public String getSimpleData() {
        return this.valueHolder.getAsString("simple_data");
    }

    public void setSubType(int i) {
        this.valueHolder.put("sub_type_id", Integer.valueOf(i));
    }

    public int getSubType() {
        Integer asInteger = this.valueHolder.getAsInteger("sub_type_id");
        if (asInteger == null) {
            return 0;
        }
        return asInteger.intValue();
    }

    public void setReserve(String str) {
        this.valueHolder.put("reserve", str);
    }

    public String getReserve() {
        return this.valueHolder.getAsString("reserve");
    }

    public void setSimpleData(String str) {
        this.valueHolder.put("simple_data", str);
    }

    public String getSequenceFileUrl() {
        return this.valueHolder.getAsString("sequence_file_url");
    }

    public void setSequenceFileUrl(String str) {
        this.valueHolder.put("sequence_file_url", str);
    }

    public String getMetaData() {
        return this.valueHolder.getAsString("metadata");
    }

    public void setMetaData(String str) {
        this.valueHolder.put("metadata", str);
    }

    public String getTimeZone() {
        if (TextUtils.isEmpty(this.valueHolder.getAsString("time_zone"))) {
            return HiDateUtil.k(getStartTime());
        }
        return this.valueHolder.getAsString("time_zone");
    }

    public void setTimeZone(String str) {
        this.valueHolder.put("time_zone", str);
    }

    public int getSyncStatus() {
        Integer asInteger = this.valueHolder.getAsInteger("sync_status");
        if (asInteger == null) {
            return 0;
        }
        return asInteger.intValue();
    }

    public void setSyncStatus(int i) {
        this.valueHolder.put("sync_status", Integer.valueOf(i));
    }

    public int getMergedStatus() {
        Integer asInteger = this.valueHolder.getAsInteger("merged");
        if (asInteger == null) {
            return 999;
        }
        return asInteger.intValue();
    }

    public void setMergedStatus(int i) {
        this.valueHolder.put("merged", Integer.valueOf(i));
    }

    public long getDataId() {
        Long asLong = this.valueHolder.getAsLong("data_id");
        if (asLong == null) {
            return 0L;
        }
        return asLong.longValue();
    }

    public void setDataId(long j) {
        this.valueHolder.put("data_id", Long.valueOf(j));
    }

    public String getRelationInformations() {
        String asString = this.valueHolder.getAsString("relation_informations");
        return asString == null ? "" : asString;
    }

    public void setRelationInformations(String str) {
        this.valueHolder.put("relation_informations", str);
    }

    public String getDataSource() {
        String asString = this.valueHolder.getAsString("data_source");
        return asString == null ? "" : asString;
    }

    public void setDataSource(String str) {
        this.valueHolder.put("data_source", str);
    }

    public boolean getRelationFlag() {
        Boolean asBoolean = this.valueHolder.getAsBoolean("relation_flag");
        if (asBoolean == null) {
            return false;
        }
        return asBoolean.booleanValue();
    }

    public void setRelationFlag(boolean z) {
        this.valueHolder.put("relation_flag", Boolean.valueOf(z));
    }

    public int getClientId() {
        Integer asInteger = this.valueHolder.getAsInteger("client_id");
        if (asInteger == null) {
            return 0;
        }
        return asInteger.intValue();
    }

    public void setClientId(int i) {
        this.valueHolder.put("client_id", Integer.valueOf(i));
    }

    public int getDeviceId() {
        Integer asInteger = this.valueHolder.getAsInteger("device_id");
        if (asInteger == null) {
            return 0;
        }
        return asInteger.intValue();
    }

    public void setDeviceId(int i) {
        this.valueHolder.put("device_id", Integer.valueOf(i));
    }

    public int getUserId() {
        Integer asInteger = this.valueHolder.getAsInteger("user_id");
        if (asInteger == null) {
            return 0;
        }
        return asInteger.intValue();
    }

    public void setUserId(int i) {
        this.valueHolder.put("user_id", Integer.valueOf(i));
    }

    public int getContentLength() {
        return this.valueHolder.size();
    }

    public int getAppId() {
        Integer asInteger = this.valueHolder.getAsInteger("app_id");
        if (asInteger == null) {
            return 0;
        }
        return asInteger.intValue();
    }

    public void setAppId(int i) {
        this.valueHolder.put("app_id", Integer.valueOf(i));
    }

    public boolean getManualFlag() {
        Boolean asBoolean = this.valueHolder.getAsBoolean("is_manual_insert_data");
        if (asBoolean == null) {
            return false;
        }
        return asBoolean.booleanValue();
    }

    public void setManualFlag(boolean z) {
        this.valueHolder.put("is_manual_insert_data", Boolean.valueOf(z));
    }

    public String getFieldsValue() {
        return this.valueHolder.getAsString("fields_value");
    }

    public void setFieldsValue(String str) {
        this.valueHolder.put("fields_value", str);
    }

    public String getFieldsMetaData() {
        return this.valueHolder.getAsString("fields_metadata");
    }

    public void setFieldsMetaData(String str) {
        this.valueHolder.put("fields_metadata", str);
    }

    public void setFieldsModifyTime(String str) {
        this.valueHolder.put("fields_modifyTime", str);
    }

    public String getFieldsModifyTime() {
        return this.valueHolder.getAsString("fields_modifyTime");
    }

    public boolean getLastDataFlag() {
        Boolean asBoolean = this.valueHolder.getAsBoolean("last_data_flag");
        if (asBoolean == null) {
            return false;
        }
        return asBoolean.booleanValue();
    }

    public void setLastDataFlag(boolean z) {
        this.valueHolder.put("last_data_flag", Boolean.valueOf(z));
    }

    public String getChangeDataInfos() {
        String asString = this.valueHolder.getAsString("change_data_infos");
        return asString == null ? "" : asString;
    }

    public void setChangeDataInfos(String str) {
        this.valueHolder.put("change_data_infos", str);
    }

    public final void putString(String str, String str2) {
        this.valueHolder.put(str, str2);
    }

    public String getString(String str) {
        return this.valueHolder.getAsString(str);
    }

    public void putDouble(String str, double d) {
        this.valueHolder.put(str, Double.valueOf(d));
    }

    public double getDouble(String str) {
        Double asDouble = this.valueHolder.getAsDouble(str);
        if (asDouble == null) {
            return 0.0d;
        }
        return asDouble.doubleValue();
    }

    public final void putFloat(String str, float f) {
        this.valueHolder.put(str, Float.valueOf(f));
    }

    public float getFloat(String str) {
        Float asFloat = this.valueHolder.getAsFloat(str);
        if (asFloat == null) {
            return 0.0f;
        }
        return asFloat.floatValue();
    }

    public void putInt(String str, int i) {
        this.valueHolder.put(str, Integer.valueOf(i));
    }

    public int getInt(String str) {
        Integer asInteger = this.valueHolder.getAsInteger(str);
        if (asInteger == null) {
            return 0;
        }
        return asInteger.intValue();
    }

    public void putShort(String str, short s) {
        this.valueHolder.put(str, Short.valueOf(s));
    }

    public short getShort(String str) {
        Short asShort = this.valueHolder.getAsShort(str);
        if (asShort == null) {
            return (short) 0;
        }
        return asShort.shortValue();
    }

    public void putLong(String str, long j) {
        this.valueHolder.put(str, Long.valueOf(j));
    }

    public long getLong(String str) {
        Long asLong = this.valueHolder.getAsLong(str);
        if (asLong == null) {
            return 0L;
        }
        return asLong.longValue();
    }

    public void putBoolean(String str, boolean z) {
        this.valueHolder.put(str, Boolean.valueOf(z));
    }

    public boolean getBoolean(String str) {
        Boolean asBoolean = this.valueHolder.getAsBoolean(str);
        if (asBoolean == null) {
            return false;
        }
        return asBoolean.booleanValue();
    }

    public void putNull(String str) {
        this.valueHolder.putNull(str);
    }

    public final Object get(String str) {
        return this.valueHolder.get(str);
    }

    public void remove(String str) {
        this.valueHolder.remove(str);
    }

    public boolean containsKey(String str) {
        return this.valueHolder.containsKey(str);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.deviceUuid);
        parcel.writeInt(this.ownerId);
        parcel.writeInt(this.type);
        parcel.writeParcelable(this.valueHolder, i);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("HiHealthData{tp=");
        stringBuffer.append(this.type);
        stringBuffer.append(",day=").append(HiDateUtil.m(getStartTime()));
        if (this.type == 30001) {
            stringBuffer.append(",sTm=").append(getStartTime());
            stringBuffer.append(",eTm=").append(getEndTime());
            stringBuffer.append(",meteDt=").append(getMetaData());
            stringBuffer.append(",sequenceFileUrl=").append(getSequenceFileUrl());
        } else {
            stringBuffer.append(",vals=").append(this.valueHolder);
        }
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(HiHealthData hiHealthData) {
        return Long.compare(hiHealthData.getStartTime(), getStartTime());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        HiHealthData hiHealthData = (HiHealthData) obj;
        return this.ownerId == hiHealthData.ownerId && this.type == hiHealthData.type && TextUtils.equals(this.deviceUuid, hiHealthData.deviceUuid) && this.valueHolder.equals(hiHealthData.valueHolder);
    }

    public int hashCode() {
        return Objects.hash(this.deviceUuid, Integer.valueOf(this.ownerId), Integer.valueOf(this.type), this.valueHolder);
    }

    public Object getOrDefault(String str, Object obj) {
        Object obj2 = this.valueHolder.get(str);
        return obj2 != null ? obj2 : obj;
    }

    public HiHealthData copyData() {
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.setDeviceUuid(this.deviceUuid);
        hiHealthData.setOwnerId(this.ownerId);
        hiHealthData.setType(this.type);
        hiHealthData.setValueHolder(new ContentValues(this.valueHolder));
        return hiHealthData;
    }
}
