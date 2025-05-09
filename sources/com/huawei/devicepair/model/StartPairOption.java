package com.huawei.devicepair.model;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes3.dex */
public class StartPairOption implements Parcelable {
    private static final String BLUETOOTH_NAME = "name";
    public static final Parcelable.Creator<StartPairOption> CREATOR = new Parcelable.Creator<StartPairOption>() { // from class: com.huawei.devicepair.model.StartPairOption.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: qV_, reason: merged with bridge method [inline-methods] */
        public StartPairOption createFromParcel(Parcel parcel) {
            return new StartPairOption(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public StartPairOption[] newArray(int i) {
            return new StartPairOption[i];
        }
    };
    private static final String DEEP_LINK_PREFIX = "huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.dm?h5pro=true&urlType=4&pName=com.huawei.health&path=devicePairing&info=";
    private static final String DEVICE_MAC = "uuid";
    private static final String IS_CLOUND_DEVICE = "isCloundDevice";
    private static final String KIND_ID = "kindId";
    private static final String PAIR_INFO = "pairInfo";
    private static final String RESOURCE_UUIDS = "uuids";
    private static final String TAG = "StartPairOption";
    private List<String> mUuids;
    private final ContentValues mValueHolder;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String creatDeepLink() {
        HashMap hashMap = new HashMap();
        hashMap.put(RESOURCE_UUIDS, getUuids());
        hashMap.put(IS_CLOUND_DEVICE, Boolean.valueOf(isCloundDevice(false)));
        hashMap.put("name", getBluetoothName(""));
        HashMap hashMap2 = new HashMap();
        hashMap2.put("uuid", getDeviceMac(""));
        hashMap2.put("name", getBluetoothName(""));
        hashMap2.put(KIND_ID, getKindId(""));
        hashMap2.put(PAIR_INFO, hashMap);
        String str = DEEP_LINK_PREFIX + HiJsonUtil.e(hashMap2);
        LogUtil.a(TAG, "creatDeepLink, deepLink is ", str);
        return str;
    }

    protected StartPairOption(Parcel parcel) {
        this.mValueHolder = (ContentValues) parcel.readParcelable(ContentValues.class.getClassLoader());
    }

    private StartPairOption(ContentValues contentValues, List<String> list) {
        this.mUuids = list;
        this.mValueHolder = contentValues;
    }

    public String getDeviceMac(String str) {
        ContentValues contentValues = this.mValueHolder;
        return contentValues != null ? contentValues.getAsString("uuid") : str;
    }

    public String getBluetoothName(String str) {
        ContentValues contentValues = this.mValueHolder;
        return contentValues != null ? contentValues.getAsString("name") : str;
    }

    public String getKindId(String str) {
        ContentValues contentValues = this.mValueHolder;
        return contentValues != null ? contentValues.getAsString(KIND_ID) : str;
    }

    public List<String> getUuids() {
        return this.mUuids;
    }

    public boolean isCloundDevice(boolean z) {
        return getBooleanValue(IS_CLOUND_DEVICE, z);
    }

    public String getCustomParameter(String str) {
        return getString(str);
    }

    private boolean getBooleanValue(String str, boolean z) {
        ContentValues contentValues = this.mValueHolder;
        return (contentValues == null || !contentValues.containsKey(str) || this.mValueHolder.getAsBoolean(str) == null) ? z : this.mValueHolder.getAsBoolean(str).booleanValue();
    }

    public String getString(String str) {
        return this.mValueHolder.getAsString(str);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mValueHolder, i);
    }

    public static a builder() {
        return new a();
    }

    public static class a {
        private ContentValues b;
        private List<String> c;

        public StartPairOption c() {
            return new StartPairOption(qW_(), b());
        }

        public a c(String str) {
            qW_().put("uuid", str);
            return this;
        }

        public a d(String str) {
            qW_().put("name", str);
            return this;
        }

        public a b(String str) {
            qW_().put(StartPairOption.KIND_ID, str);
            return this;
        }

        public a e(List<String> list) {
            d(list);
            return this;
        }

        public a a(boolean z) {
            qW_().put(StartPairOption.IS_CLOUND_DEVICE, Boolean.valueOf(z));
            return this;
        }

        private ContentValues qW_() {
            if (this.b == null) {
                this.b = new ContentValues();
            }
            return this.b;
        }

        private List<String> b() {
            if (this.c == null) {
                this.c = new ArrayList();
            }
            return this.c;
        }

        private void d(List<String> list) {
            this.c = list;
        }
    }
}
