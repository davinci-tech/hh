package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class SearchCity implements Parcelable {
    public static final Parcelable.Creator<SearchCity> CREATOR = new Parcelable.Creator<SearchCity>() { // from class: com.amap.api.services.route.SearchCity.1
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ SearchCity[] newArray(int i) {
            return null;
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ SearchCity createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        private static SearchCity a(Parcel parcel) {
            return new SearchCity(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private String f1577a;
    private String b;
    private String c;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getSearchCityName() {
        return this.f1577a;
    }

    public void setSearchCityName(String str) {
        this.f1577a = str;
    }

    public String getSearchCitycode() {
        return this.b;
    }

    public void setSearchCitycode(String str) {
        this.b = str;
    }

    public String getSearchCityAdCode() {
        return this.c;
    }

    public void setSearchCityhAdCode(String str) {
        this.c = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1577a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
    }

    public SearchCity(Parcel parcel) {
        this.f1577a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
    }

    public SearchCity() {
    }
}
