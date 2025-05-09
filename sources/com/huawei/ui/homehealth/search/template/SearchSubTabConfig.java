package com.huawei.ui.homehealth.search.template;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArrayMap;
import com.alipay.sdk.m.p.e;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.huawei.health.knit.model.KnitSectionConfig;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class SearchSubTabConfig implements Parcelable {
    public static final Parcelable.Creator<SearchSubTabConfig> CREATOR = new Parcelable.Creator<SearchSubTabConfig>() { // from class: com.huawei.ui.homehealth.search.template.SearchSubTabConfig.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: dhb_, reason: merged with bridge method [inline-methods] */
        public SearchSubTabConfig createFromParcel(Parcel parcel) {
            return new SearchSubTabConfig(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public SearchSubTabConfig[] newArray(int i) {
            return new SearchSubTabConfig[i];
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
    private ArrayList<KnitSectionConfig> sectionConfigList;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private SearchSubTabConfig() {
    }

    private SearchSubTabConfig(Parcel parcel) {
        this.pageType = parcel.readInt();
        this.params = new ArrayMap<>();
        this.sectionConfigList = parcel.createTypedArrayList(KnitSectionConfig.CREATOR);
    }

    public SearchSubTabConfig newInstance() {
        return new SearchSubTabConfig();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.pageType);
        parcel.writeTypedList(this.sectionConfigList);
    }

    public int getPageType() {
        return this.pageType;
    }

    public void setPageType(int i) {
        this.pageType = i;
    }

    public ArrayList<KnitSectionConfig> getSectionConfigList() {
        return this.sectionConfigList;
    }

    public void setSectionConfigList(ArrayList<KnitSectionConfig> arrayList) {
        this.sectionConfigList = arrayList;
    }

    public ArrayMap<String, String> getParams() {
        return this.params;
    }

    public void setParams(ArrayMap<String, String> arrayMap) {
        this.params = arrayMap;
    }
}
