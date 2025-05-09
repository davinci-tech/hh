package com.huawei.basefitnessadvice.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class NavigationFilter implements Parcelable {
    public static final Parcelable.Creator<NavigationFilter> CREATOR = new Parcelable.Creator<NavigationFilter>() { // from class: com.huawei.basefitnessadvice.model.NavigationFilter.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NavigationFilter createFromParcel(Parcel parcel) {
            return new NavigationFilter(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NavigationFilter[] newArray(int i) {
            return new NavigationFilter[i];
        }
    };

    @SerializedName("leftNavigations")
    private List<Navigation> mLeftNavigations;

    @SerializedName("pageName")
    private String mPageName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public NavigationFilter() {
    }

    protected NavigationFilter(Parcel parcel) {
        this.mPageName = parcel.readString();
        ArrayList arrayList = new ArrayList();
        this.mLeftNavigations = arrayList;
        parcel.readList(arrayList, Navigation.class.getClassLoader());
    }

    private NavigationFilter(Builder builder) {
        this.mPageName = builder.mPageName;
        this.mLeftNavigations = builder.mLeftNavigations;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mPageName);
        parcel.writeList(this.mLeftNavigations);
    }

    /* loaded from: classes8.dex */
    public static final class Builder {
        private List<Navigation> mLeftNavigations;
        private String mPageName;

        public Builder pageName(String str) {
            this.mPageName = str;
            return this;
        }

        public Builder leftNavigations(List<Navigation> list) {
            this.mLeftNavigations = list;
            return this;
        }

        public NavigationFilter build() {
            return new NavigationFilter(this);
        }
    }

    public String getPageName() {
        return this.mPageName;
    }

    public List<Navigation> getLeftNavigations() {
        return this.mLeftNavigations;
    }
}
