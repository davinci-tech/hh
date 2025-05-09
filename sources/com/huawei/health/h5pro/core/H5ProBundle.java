package com.huawei.health.h5pro.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.Map;

/* loaded from: classes.dex */
public final class H5ProBundle extends H5ProBaseBundle implements Parcelable {
    public static final Parcelable.Creator<H5ProBundle> CREATOR = new Parcelable.Creator<H5ProBundle>() { // from class: com.huawei.health.h5pro.core.H5ProBundle.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public H5ProBundle[] newArray(int i) {
            return new H5ProBundle[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public H5ProBundle createFromParcel(Parcel parcel) {
            return new H5ProBundle(parcel);
        }
    };
    public static final String MODULE_NAME_DEF = "option";
    public final String moduleName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.huawei.health.h5pro.core.H5ProBaseBundle, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.moduleName);
        super.writeToParcel(parcel, i);
    }

    @Override // com.huawei.health.h5pro.core.H5ProBaseBundle
    public /* bridge */ /* synthetic */ void putString(String str, String str2) {
        super.putString(str, str2);
    }

    @Override // com.huawei.health.h5pro.core.H5ProBaseBundle
    public /* bridge */ /* synthetic */ void putLong(String str, long j) {
        super.putLong(str, j);
    }

    @Override // com.huawei.health.h5pro.core.H5ProBaseBundle
    public /* bridge */ /* synthetic */ void putInt(String str, int i) {
        super.putInt(str, i);
    }

    @Override // com.huawei.health.h5pro.core.H5ProBaseBundle
    public /* bridge */ /* synthetic */ void putFloat(String str, float f) {
        super.putFloat(str, f);
    }

    @Override // com.huawei.health.h5pro.core.H5ProBaseBundle
    public /* bridge */ /* synthetic */ void putDouble(String str, double d) {
        super.putDouble(str, d);
    }

    @Override // com.huawei.health.h5pro.core.H5ProBaseBundle
    public /* bridge */ /* synthetic */ void putBoolean(String str, boolean z) {
        super.putBoolean(str, z);
    }

    @Override // com.huawei.health.h5pro.core.H5ProBaseBundle
    public /* bridge */ /* synthetic */ void putAll(H5ProBaseBundle h5ProBaseBundle) {
        super.putAll(h5ProBaseBundle);
    }

    @Override // com.huawei.health.h5pro.core.H5ProBaseBundle
    public /* bridge */ /* synthetic */ String getString(String str, String str2) {
        return super.getString(str, str2);
    }

    @Override // com.huawei.health.h5pro.core.H5ProBaseBundle
    public /* bridge */ /* synthetic */ String getString(String str) {
        return super.getString(str);
    }

    public String getModuleName() {
        return this.moduleName;
    }

    @Override // com.huawei.health.h5pro.core.H5ProBaseBundle
    public /* bridge */ /* synthetic */ long getLong(String str, long j) {
        return super.getLong(str, j);
    }

    @Override // com.huawei.health.h5pro.core.H5ProBaseBundle
    public /* bridge */ /* synthetic */ long getLong(String str) {
        return super.getLong(str);
    }

    @Override // com.huawei.health.h5pro.core.H5ProBaseBundle
    public /* bridge */ /* synthetic */ int getInt(String str, int i) {
        return super.getInt(str, i);
    }

    @Override // com.huawei.health.h5pro.core.H5ProBaseBundle
    public /* bridge */ /* synthetic */ int getInt(String str) {
        return super.getInt(str);
    }

    @Override // com.huawei.health.h5pro.core.H5ProBaseBundle
    public /* bridge */ /* synthetic */ float getFloat(String str, float f) {
        return super.getFloat(str, f);
    }

    @Override // com.huawei.health.h5pro.core.H5ProBaseBundle
    public /* bridge */ /* synthetic */ float getFloat(String str) {
        return super.getFloat(str);
    }

    @Override // com.huawei.health.h5pro.core.H5ProBaseBundle
    public /* bridge */ /* synthetic */ double getDouble(String str, double d) {
        return super.getDouble(str, d);
    }

    @Override // com.huawei.health.h5pro.core.H5ProBaseBundle
    public /* bridge */ /* synthetic */ double getDouble(String str) {
        return super.getDouble(str);
    }

    @Override // com.huawei.health.h5pro.core.H5ProBaseBundle
    public /* bridge */ /* synthetic */ Map getData() {
        return super.getData();
    }

    @Override // com.huawei.health.h5pro.core.H5ProBaseBundle
    public /* bridge */ /* synthetic */ boolean getBoolean(String str, boolean z) {
        return super.getBoolean(str, z);
    }

    @Override // com.huawei.health.h5pro.core.H5ProBaseBundle
    public /* bridge */ /* synthetic */ boolean getBoolean(String str) {
        return super.getBoolean(str);
    }

    public H5ProBundle(String str) {
        this.moduleName = TextUtils.isEmpty(str) ? "option" : str;
    }

    public H5ProBundle(Parcel parcel) {
        this.moduleName = parcel.readString();
        super.readFromParcel(parcel, H5ProBundle.class.getClassLoader());
    }

    public H5ProBundle() {
        this("option");
    }
}
