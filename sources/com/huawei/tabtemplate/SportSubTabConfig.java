package com.huawei.tabtemplate;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArrayMap;
import com.alipay.sdk.m.p.e;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class SportSubTabConfig implements Parcelable {
    public static final Parcelable.Creator<SportSubTabConfig> CREATOR = new Parcelable.Creator<SportSubTabConfig>() { // from class: com.huawei.tabtemplate.SportSubTabConfig.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: cvg_, reason: merged with bridge method [inline-methods] */
        public SportSubTabConfig createFromParcel(Parcel parcel) {
            return new SportSubTabConfig(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public SportSubTabConfig[] newArray(int i) {
            return new SportSubTabConfig[i];
        }
    };

    @SerializedName("page_type")
    @Expose
    private int pageType;

    @SerializedName(e.n)
    @Expose
    private ArrayMap<String, String> params;

    @SerializedName("section_list")
    @Expose
    private ArrayList<SportSectionConfig> sectionConfigList;

    @SerializedName("sub_view")
    @Expose
    private ArrayList<SportSubViewConfig> viewConfigList;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private SportSubTabConfig() {
    }

    private SportSubTabConfig(Parcel parcel) {
        this.pageType = parcel.readInt();
        ArrayMap<String, String> arrayMap = new ArrayMap<>();
        this.params = arrayMap;
        parcel.readMap(arrayMap, getClass().getClassLoader());
        this.viewConfigList = parcel.createTypedArrayList(SportSubViewConfig.CREATOR);
    }

    public static SportSubTabConfig newInstance() {
        return new SportSubTabConfig();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.pageType);
        parcel.writeMap(this.params);
        parcel.writeTypedList(this.viewConfigList);
    }

    public int getPageType() {
        return this.pageType;
    }

    public void setPageType(int i) {
        this.pageType = i;
    }

    public ArrayMap<String, String> getParams() {
        return this.params;
    }

    public void setParams(ArrayMap<String, String> arrayMap) {
        this.params = arrayMap;
    }

    public ArrayList<SportSubViewConfig> getViewConfigList() {
        return this.viewConfigList;
    }

    public void setViewConfigList(ArrayList<SportSubViewConfig> arrayList) {
        this.viewConfigList = arrayList;
    }

    public ArrayList<SportSectionConfig> getSectionConfigList() {
        return this.sectionConfigList;
    }

    public void setSectionConfigList(ArrayList<SportSectionConfig> arrayList) {
        this.sectionConfigList = arrayList;
    }

    public String toString() {
        return "TabConfig{, pageType=" + this.pageType + ", params=" + this.params + ", viewConfigList=" + this.viewConfigList + ", sectionConfigList=" + this.sectionConfigList + '}';
    }
}
