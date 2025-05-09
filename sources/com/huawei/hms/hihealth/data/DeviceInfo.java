package com.huawei.hms.hihealth.data;

import android.content.Context;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.health.aaca;
import com.huawei.hms.health.aacs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

/* loaded from: classes9.dex */
public class DeviceInfo implements Parcelable {
    public static final Parcelable.Creator<DeviceInfo> CREATOR = new aab();
    public static final int PLATFORM_DEFAULT = 0;
    public static final int PLATFORM_OTHER = 2;
    private static final CharSequence SEPARATOR;
    public static final String STR_TYPE_BLOOD_GLUCOSE_MONITOR = "Blood glucose monitor";
    public static final String STR_TYPE_BLOOD_PRESSURE_MONITOR = "Blood pressure monitor";
    public static final String STR_TYPE_ECG_EQUIPMENT = "Ecg equipment";
    public static final String STR_TYPE_ELLIPTICAL_MACHINE = "Elliptical machine";
    public static final String STR_TYPE_EXERCISE_BIKE = "Exercise bike";
    public static final String STR_TYPE_HEART_RATE_MONITOR = "Heart rate monitor";
    public static final String STR_TYPE_OXIMETER = "Oximeter";
    public static final String STR_TYPE_PHONE = "Phone";
    public static final String STR_TYPE_ROPE_SKIPPING = "Rope skipping";
    public static final String STR_TYPE_ROWING_MACHINE = "Rowing machine";
    public static final String STR_TYPE_SCALES = "Scales";
    public static final String STR_TYPE_SMART_BAND = "Smart band";
    private static final String STR_TYPE_SMART_BAND_OLD = "Smart Band";
    public static final String STR_TYPE_SMART_EARPHONE = "Smart earphone";
    public static final String STR_TYPE_SMART_WATCH = "Smart watch";
    private static final String STR_TYPE_SMART_WATCH_OLD = "Smart Watch";
    public static final String STR_TYPE_THERMOMETERS = "Thermometers";
    public static final String STR_TYPE_THIRD_WATCH = "Third watch";
    public static final String STR_TYPE_TREADMILL = "Treadmill";
    public static final String STR_TYPE_UNKNOWN = "Unknown";
    public static final String STR_TYPE_WALKING_MACHINE = "Walking machine";
    public static final int TYPE_BLOOD_GLUCOSE_MONITOR = 6;
    public static final int TYPE_BLOOD_PRESSURE_MONITOR = 7;
    public static final int TYPE_ECG_EQUIPMENT = 9;
    public static final int TYPE_ELLIPTICAL_MACHINE = 13;
    public static final int TYPE_EXERCISE_BIKE = 14;
    public static final int TYPE_HEART_RATE_MONITOR = 8;
    public static final int TYPE_OXIMETER = 11;
    public static final int TYPE_PHONE = 1;
    public static final int TYPE_ROPE_SKIPPING = 17;
    public static final int TYPE_ROWING_MACHINE = 15;
    public static final int TYPE_SCALES = 2;
    public static final int TYPE_SMART_BAND = 4;
    public static final int TYPE_SMART_EARPHONE = 5;
    public static final int TYPE_SMART_WATCH = 3;
    public static final int TYPE_THERMOMETERS = 10;
    public static final int TYPE_THIRD_WATCH = 18;
    public static final int TYPE_TREADMILL = 12;
    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_WALKING_MACHINE = 16;
    private static Map<Integer, String> deviceType2Str;
    private int deviceType;
    private boolean isFromBleDevice;
    private String manufacturer;
    private String modelName;
    private int platformType;
    private String productId;
    private String uuid;

    @Deprecated
    public static DeviceInfo getLocalDevice(Context context) {
        int aabb = aaca.aabb(context);
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        StringBuilder aab2 = com.huawei.hms.health.aab.aab("86");
        aab2.append(Build.MANUFACTURER.length() % 10);
        aab2.append(Build.MODEL.length() % 10);
        aab2.append(Build.BOARD.length() % 10);
        aab2.append(Build.BRAND.length() % 10);
        String sb = aab2.toString();
        return new DeviceInfo(str, str2, new UUID(sb.hashCode(), new StringBuilder(sb).reverse().toString().hashCode()).toString(), aabb, 2);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getManufacturer());
        parcel.writeString(getModelName());
        parcel.writeString(getUuid());
        parcel.writeInt(getDeviceType());
        parcel.writeInt(this.platformType);
        parcel.writeInt(this.isFromBleDevice ? 1 : 0);
    }

    public final String toString() {
        return String.format(Locale.ENGLISH, "DeviceInfo{%s:%s:%s}", getDeviceIdentifier(), Integer.valueOf(this.deviceType), Integer.valueOf(this.platformType));
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public boolean isFromBleDevice() {
        return this.isFromBleDevice;
    }

    public final int hashCode() {
        return (this.manufacturer + this.modelName + this.uuid + this.deviceType).hashCode();
    }

    public final String getUuid() {
        return this.uuid;
    }

    public String getProductId() {
        return this.productId;
    }

    public final int getPlatformType() {
        return this.platformType;
    }

    public final String getModelName() {
        return this.modelName;
    }

    public final String getManufacturer() {
        return this.manufacturer;
    }

    public final int getDeviceType() {
        return this.deviceType;
    }

    public final String getDeviceIdentifier() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.manufacturer);
        arrayList.add(this.modelName);
        arrayList.add(this.uuid);
        return TextUtils.join(SEPARATOR, arrayList);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DeviceInfo)) {
            return false;
        }
        DeviceInfo deviceInfo = (DeviceInfo) obj;
        return deviceInfo.manufacturer.equals(this.manufacturer) && deviceInfo.modelName.equals(this.modelName) && deviceInfo.uuid.equals(this.uuid) && deviceInfo.deviceType == this.deviceType && deviceInfo.platformType == this.platformType && deviceInfo.isFromBleDevice == this.isFromBleDevice;
    }

    public static String getDeviceTypeStringById(int i) {
        return deviceType2Str.containsKey(Integer.valueOf(i)) ? deviceType2Str.get(Integer.valueOf(i)) : STR_TYPE_UNKNOWN;
    }

    public static int getDeviceTypeIdByString(String str) {
        for (Map.Entry<Integer, String> entry : deviceType2Str.entrySet()) {
            if (entry.getValue().equals(str)) {
                return entry.getKey().intValue();
            }
        }
        if (STR_TYPE_SMART_WATCH_OLD.equals(str)) {
            return 3;
        }
        return STR_TYPE_SMART_BAND_OLD.equals(str) ? 4 : 0;
    }

    public DeviceInfo(String str, String str2, String str3, int i, int i2, boolean z, String str4) {
        this.isFromBleDevice = false;
        Preconditions.checkArgument(aacs.aaba(str), "DeviceManufacturer Length Is Illegal!");
        Preconditions.checkArgument(aacs.aaba(str2), "ModelName Length Is Illegal!");
        Preconditions.checkArgument(aacs.aaba(str3), "Uuid Length Is Illegal!");
        this.manufacturer = str;
        this.modelName = str2;
        this.isFromBleDevice = z;
        this.uuid = str3;
        this.deviceType = i;
        this.platformType = i2;
        this.productId = str4;
    }

    public DeviceInfo(String str, String str2, String str3, int i, int i2, boolean z) {
        this.isFromBleDevice = false;
        Preconditions.checkArgument(aacs.aaba(str), "DeviceManufacturer Length Is Illegal!");
        Preconditions.checkArgument(aacs.aaba(str2), "ModelName Length Is Illegal!");
        Preconditions.checkArgument(aacs.aaba(str3), "Uuid Length Is Illegal!");
        this.manufacturer = str;
        this.modelName = str2;
        this.isFromBleDevice = z;
        this.uuid = str3;
        this.deviceType = i;
        this.platformType = i2;
    }

    public DeviceInfo(String str, String str2, String str3, int i, int i2) {
        this.isFromBleDevice = false;
        Preconditions.checkArgument(aacs.aaba(str), "DeviceManufacturer Length Is Illegal!");
        Preconditions.checkArgument(aacs.aaba(str2), "ModelName Length Is Illegal!");
        Preconditions.checkArgument(aacs.aaba(str3), "Uuid Length Is Illegal!");
        this.manufacturer = str;
        this.modelName = str2;
        this.isFromBleDevice = false;
        this.uuid = str3;
        this.deviceType = i;
        this.platformType = i2;
    }

    public DeviceInfo(String str, String str2, String str3, int i) {
        this(str, str2, str3, i, 0);
    }

    class aab implements Parcelable.Creator<DeviceInfo> {
        @Override // android.os.Parcelable.Creator
        public DeviceInfo createFromParcel(Parcel parcel) {
            return new DeviceInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public DeviceInfo[] newArray(int i) {
            return new DeviceInfo[i];
        }

        aab() {
        }
    }

    protected DeviceInfo(Parcel parcel) {
        this.isFromBleDevice = false;
        this.manufacturer = parcel.readString();
        this.modelName = parcel.readString();
        this.uuid = parcel.readString();
        this.deviceType = parcel.readInt();
        this.platformType = parcel.readInt();
        this.isFromBleDevice = parcel.readInt() == 1;
    }

    static {
        HashMap hashMap = new HashMap();
        deviceType2Str = hashMap;
        hashMap.put(0, STR_TYPE_UNKNOWN);
        deviceType2Str.put(1, STR_TYPE_PHONE);
        deviceType2Str.put(2, STR_TYPE_SCALES);
        deviceType2Str.put(3, STR_TYPE_SMART_WATCH);
        deviceType2Str.put(4, STR_TYPE_SMART_BAND);
        deviceType2Str.put(5, STR_TYPE_SMART_EARPHONE);
        deviceType2Str.put(6, STR_TYPE_BLOOD_GLUCOSE_MONITOR);
        deviceType2Str.put(7, STR_TYPE_BLOOD_PRESSURE_MONITOR);
        deviceType2Str.put(8, STR_TYPE_HEART_RATE_MONITOR);
        deviceType2Str.put(9, STR_TYPE_ECG_EQUIPMENT);
        deviceType2Str.put(10, STR_TYPE_THERMOMETERS);
        deviceType2Str.put(11, STR_TYPE_OXIMETER);
        deviceType2Str.put(12, STR_TYPE_TREADMILL);
        deviceType2Str.put(13, STR_TYPE_ELLIPTICAL_MACHINE);
        deviceType2Str.put(14, STR_TYPE_EXERCISE_BIKE);
        deviceType2Str.put(15, STR_TYPE_ROWING_MACHINE);
        deviceType2Str.put(16, STR_TYPE_WALKING_MACHINE);
        deviceType2Str.put(17, STR_TYPE_ROPE_SKIPPING);
        deviceType2Str.put(18, STR_TYPE_THIRD_WATCH);
        SEPARATOR = ":";
    }
}
