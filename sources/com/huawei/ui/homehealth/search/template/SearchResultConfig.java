package com.huawei.ui.homehealth.search.template;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.huawei.tabtemplate.BaseTemplateConfig;
import java.util.List;

/* loaded from: classes9.dex */
public class SearchResultConfig implements BaseTemplateConfig, Parcelable {
    public static final Parcelable.Creator<SearchResultConfig> CREATOR = new Parcelable.Creator<SearchResultConfig>() { // from class: com.huawei.ui.homehealth.search.template.SearchResultConfig.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: dha_, reason: merged with bridge method [inline-methods] */
        public SearchResultConfig createFromParcel(Parcel parcel) {
            return new SearchResultConfig(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public SearchResultConfig[] newArray(int i) {
            return new SearchResultConfig[i];
        }
    };

    @SerializedName("sub_tab")
    @Expose
    private List<SearchSubTabConfig> tabConfigList;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private SearchResultConfig() {
    }

    public SearchResultConfig(Parcel parcel) {
        this.tabConfigList = parcel.createTypedArrayList(SearchSubTabConfig.CREATOR);
    }

    public List<SearchSubTabConfig> getTabConfigList() {
        return this.tabConfigList;
    }

    public void setTabConfigList(List<SearchSubTabConfig> list) {
        this.tabConfigList = list;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.tabConfigList);
    }
}
