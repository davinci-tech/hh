package com.huawei.tabtemplate;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArrayMap;
import com.alipay.sdk.m.p.e;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes6.dex */
public class SportSubViewConfig implements Parcelable {
    public static final Parcelable.Creator<SportSubViewConfig> CREATOR = new Parcelable.Creator<SportSubViewConfig>() { // from class: com.huawei.tabtemplate.SportSubViewConfig.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: cvh_, reason: merged with bridge method [inline-methods] */
        public SportSubViewConfig createFromParcel(Parcel parcel) {
            return new SportSubViewConfig(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public SportSubViewConfig[] newArray(int i) {
            return new SportSubViewConfig[i];
        }
    };

    @SerializedName(e.n)
    @Expose
    private ArrayMap<String, String> params;

    @SerializedName("view_type")
    @Expose
    private int viewType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private SportSubViewConfig() {
    }

    private SportSubViewConfig(Parcel parcel) {
        this.viewType = parcel.readInt();
        ArrayMap<String, String> arrayMap = new ArrayMap<>();
        this.params = arrayMap;
        parcel.readMap(arrayMap, getClass().getClassLoader());
    }

    public static SportSubViewConfig getDefaultViewConfig(int i, ArrayMap<String, String> arrayMap) {
        SportSubViewConfig sportSubViewConfig = new SportSubViewConfig();
        sportSubViewConfig.setViewType(i);
        sportSubViewConfig.setParams(arrayMap);
        return sportSubViewConfig;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.viewType);
        parcel.writeMap(this.params);
    }

    public int getViewType() {
        return this.viewType;
    }

    public void setViewType(int i) {
        this.viewType = i;
    }

    public ArrayMap<String, String> getParams() {
        return this.params;
    }

    public void setParams(ArrayMap<String, String> arrayMap) {
        this.params = arrayMap;
    }

    public String toString() {
        return "ViewConfig{, viewType=" + this.viewType + ", params=" + this.params + '}';
    }
}
