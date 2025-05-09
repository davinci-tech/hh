package com.amap.api.services.district;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.AMapException;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class DistrictResult implements Parcelable {
    public Parcelable.Creator<DistrictResult> CREATOR;

    /* renamed from: a, reason: collision with root package name */
    private DistrictSearchQuery f1488a;
    private ArrayList<DistrictItem> b;
    private int c;
    private AMapException d;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public DistrictResult(DistrictSearchQuery districtSearchQuery, ArrayList<DistrictItem> arrayList) {
        this.b = new ArrayList<>();
        this.CREATOR = new Parcelable.Creator<DistrictResult>() { // from class: com.amap.api.services.district.DistrictResult.1
            @Override // android.os.Parcelable.Creator
            public final /* synthetic */ DistrictResult createFromParcel(Parcel parcel) {
                return a(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public final /* synthetic */ DistrictResult[] newArray(int i) {
                return a(i);
            }

            private static DistrictResult a(Parcel parcel) {
                return new DistrictResult(parcel);
            }

            private static DistrictResult[] a(int i) {
                return new DistrictResult[i];
            }
        };
        this.f1488a = districtSearchQuery;
        this.b = arrayList;
    }

    public DistrictResult() {
        this.b = new ArrayList<>();
        this.CREATOR = new Parcelable.Creator<DistrictResult>() { // from class: com.amap.api.services.district.DistrictResult.1
            @Override // android.os.Parcelable.Creator
            public final /* synthetic */ DistrictResult createFromParcel(Parcel parcel) {
                return a(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public final /* synthetic */ DistrictResult[] newArray(int i) {
                return a(i);
            }

            private static DistrictResult a(Parcel parcel) {
                return new DistrictResult(parcel);
            }

            private static DistrictResult[] a(int i) {
                return new DistrictResult[i];
            }
        };
    }

    public final ArrayList<DistrictItem> getDistrict() {
        return this.b;
    }

    public final void setDistrict(ArrayList<DistrictItem> arrayList) {
        this.b = arrayList;
    }

    public final DistrictSearchQuery getQuery() {
        return this.f1488a;
    }

    public final void setQuery(DistrictSearchQuery districtSearchQuery) {
        this.f1488a = districtSearchQuery;
    }

    public final int getPageCount() {
        return this.c;
    }

    public final void setPageCount(int i) {
        this.c = i;
    }

    public final AMapException getAMapException() {
        return this.d;
    }

    public final void setAMapException(AMapException aMapException) {
        this.d = aMapException;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f1488a, i);
        parcel.writeTypedList(this.b);
    }

    protected DistrictResult(Parcel parcel) {
        this.b = new ArrayList<>();
        this.CREATOR = new Parcelable.Creator<DistrictResult>() { // from class: com.amap.api.services.district.DistrictResult.1
            @Override // android.os.Parcelable.Creator
            public final /* synthetic */ DistrictResult createFromParcel(Parcel parcel2) {
                return a(parcel2);
            }

            @Override // android.os.Parcelable.Creator
            public final /* synthetic */ DistrictResult[] newArray(int i) {
                return a(i);
            }

            private static DistrictResult a(Parcel parcel2) {
                return new DistrictResult(parcel2);
            }

            private static DistrictResult[] a(int i) {
                return new DistrictResult[i];
            }
        };
        this.f1488a = (DistrictSearchQuery) parcel.readParcelable(DistrictSearchQuery.class.getClassLoader());
        this.b = parcel.createTypedArrayList(DistrictItem.CREATOR);
    }

    public final int hashCode() {
        DistrictSearchQuery districtSearchQuery = this.f1488a;
        int hashCode = districtSearchQuery == null ? 0 : districtSearchQuery.hashCode();
        ArrayList<DistrictItem> arrayList = this.b;
        return ((hashCode + 31) * 31) + (arrayList != null ? arrayList.hashCode() : 0);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DistrictResult districtResult = (DistrictResult) obj;
        DistrictSearchQuery districtSearchQuery = this.f1488a;
        if (districtSearchQuery == null) {
            if (districtResult.f1488a != null) {
                return false;
            }
        } else if (!districtSearchQuery.equals(districtResult.f1488a)) {
            return false;
        }
        ArrayList<DistrictItem> arrayList = this.b;
        if (arrayList == null) {
            if (districtResult.b != null) {
                return false;
            }
        } else if (!arrayList.equals(districtResult.b)) {
            return false;
        }
        return true;
    }

    public final String toString() {
        return "DistrictResult [mDisQuery=" + this.f1488a + ", mDistricts=" + this.b + "]";
    }
}
