package com.huawei.hihealth;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class HiHealthKitDataOhos implements Parcelable {
    public static final Parcelable.Creator<HiHealthKitDataOhos> CREATOR = new Parcelable.Creator<HiHealthKitDataOhos>() { // from class: com.huawei.hihealth.HiHealthKitDataOhos.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: buM_, reason: merged with bridge method [inline-methods] */
        public HiHealthKitDataOhos createFromParcel(Parcel parcel) {
            return new HiHealthKitDataOhos(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public HiHealthKitDataOhos[] newArray(int i) {
            return new HiHealthKitDataOhos[i];
        }
    };
    private static final int DEFAULT_SIZE = 16;
    private static final double DEFAULT_VALUE = -1.0d;
    private static final String DOUBLE = "Double";
    private static final String FLOAT = "Float";
    private static final String INTEGER = "Integer";
    private static final int INVALID_VALUE = -1;
    private static final String LONG = "Long";
    private static final int MAP_INITIAL_CAPACITY = 16;
    private static final String PRECISION_DEFAULT = "-1";
    private static final String PRECISION_DOUBLE = "3";
    private static final String PRECISION_FLOAT = "2";
    private static final String PRECISION_INT = "0";
    private static final String PRECISION_LONG = "1";
    private List<String> mHolderConstraint;
    private List<String> mHolderValue;
    private Map mMap;
    private List<String> mMapConstraint;
    private List<String> mMapValue;
    private List<String> mMapValueType;
    private int mType;
    private ContentValues mValueHolder;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HiHealthKitDataOhos() {
        this.mHolderConstraint = new ArrayList(16);
        this.mHolderValue = new ArrayList(16);
        this.mMapConstraint = new ArrayList(16);
        this.mMapValue = new ArrayList(16);
        this.mMapValueType = new ArrayList(16);
        this.mValueHolder = new ContentValues();
        this.mMap = new HashMap(16);
    }

    public HiHealthKitDataOhos(Parcel parcel) {
        this.mHolderConstraint = new ArrayList(16);
        this.mHolderValue = new ArrayList(16);
        this.mMapConstraint = new ArrayList(16);
        this.mMapValue = new ArrayList(16);
        this.mMapValueType = new ArrayList(16);
        parcel.readStringList(this.mHolderConstraint);
        parcel.readStringList(this.mHolderValue);
        parcel.readStringList(this.mMapConstraint);
        parcel.readStringList(this.mMapValue);
        parcel.readStringList(this.mMapValueType);
        this.mType = parcel.readInt();
        constructMap();
        constructValueHolder();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void constructMap() {
        Object keyOfMap;
        char c;
        List<String> list = this.mMapConstraint;
        if (list == null || list.isEmpty() || this.mMapConstraint.size() != this.mMapValue.size()) {
            return;
        }
        int size = this.mMapValue.size();
        for (int i = 0; i < size; i++) {
            if (this.mMap == null) {
                this.mMap = new HashMap(16);
            }
            if (this.mMapValueType.size() == size * 2) {
                keyOfMap = getKeyOfMap(this.mMapConstraint.get(i), this.mMapValueType.get(i + size));
            } else {
                keyOfMap = getKeyOfMap(this.mMapConstraint.get(i), "0");
            }
            String str = this.mMapValueType.get(i);
            str.hashCode();
            switch (str.hashCode()) {
                case 48:
                    if (str.equals("0")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 49:
                    if (str.equals("1")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 50:
                    if (str.equals("2")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 51:
                    if (str.equals("3")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            if (c == 0) {
                this.mMap.put(keyOfMap, Integer.valueOf(this.mMapValue.get(i)));
            } else if (c == 1) {
                this.mMap.put(keyOfMap, Long.valueOf(this.mMapValue.get(i)));
            } else if (c == 2) {
                this.mMap.put(keyOfMap, Float.valueOf(this.mMapValue.get(i)));
            } else if (c == 3) {
                this.mMap.put(keyOfMap, Double.valueOf(this.mMapValue.get(i)));
            } else {
                this.mMap.put(keyOfMap, this.mMapValue.get(i));
            }
        }
    }

    private Object getKeyOfMap(String str, String str2) {
        str2.hashCode();
        return !str2.equals("0") ? str : Integer.valueOf(str);
    }

    private void constructValueHolder() {
        List<String> list = this.mHolderValue;
        if (list == null || list.isEmpty() || this.mHolderValue.size() != this.mHolderConstraint.size()) {
            return;
        }
        for (int i = 0; i < this.mHolderConstraint.size(); i++) {
            if (this.mValueHolder == null) {
                this.mValueHolder = new ContentValues();
            }
            this.mValueHolder.put(this.mHolderConstraint.get(i), this.mHolderValue.get(i));
        }
    }

    public void setContentValues(ContentValues contentValues) {
        this.mValueHolder = contentValues;
        if (contentValues == null) {
            return;
        }
        for (Map.Entry<String, Object> entry : contentValues.valueSet()) {
            this.mHolderConstraint.add(entry.getKey());
            this.mHolderValue.add(String.valueOf(entry.getValue()));
        }
    }

    public ContentValues getContentValues() {
        return this.mValueHolder;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringList(this.mHolderConstraint);
        parcel.writeStringList(this.mHolderValue);
        parcel.writeStringList(this.mMapConstraint);
        parcel.writeStringList(this.mMapValue);
        parcel.writeStringList(this.mMapValueType);
        parcel.writeInt(this.mType);
    }

    public void setType(int i) {
        this.mType = i;
    }

    public int getType() {
        return this.mType;
    }

    public void setMap(Map map) {
        this.mMap = map;
        if (map == null || map.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        for (Map.Entry entry : this.mMap.entrySet()) {
            this.mMapConstraint.add(String.valueOf(entry.getKey()));
            arrayList.add(getMapType(entry.getKey()));
            this.mMapValue.add(String.valueOf(entry.getValue()));
            this.mMapValueType.add(getMapType(entry.getValue()));
        }
        this.mMapValueType.addAll(arrayList);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private String getMapType(Object obj) {
        String name = obj.getClass().getName();
        if (TextUtils.isEmpty(name)) {
            return "-1";
        }
        int lastIndexOf = name.lastIndexOf(".");
        char c = 65535;
        if (lastIndexOf == -1) {
            return "-1";
        }
        String substring = name.substring(lastIndexOf + 1);
        substring.hashCode();
        switch (substring.hashCode()) {
            case -672261858:
                if (substring.equals(INTEGER)) {
                    c = 0;
                    break;
                }
                break;
            case 2374300:
                if (substring.equals(LONG)) {
                    c = 1;
                    break;
                }
                break;
            case 67973692:
                if (substring.equals(FLOAT)) {
                    c = 2;
                    break;
                }
                break;
            case 2052876273:
                if (substring.equals(DOUBLE)) {
                    c = 3;
                    break;
                }
                break;
        }
        return c != 0 ? c != 1 ? c != 2 ? c != 3 ? "-1" : "3" : "2" : "1" : "0";
    }

    public Map getMap() {
        return this.mMap;
    }
}
