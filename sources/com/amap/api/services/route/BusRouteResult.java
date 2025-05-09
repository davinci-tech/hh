package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.route.RouteSearch;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class BusRouteResult extends RouteResult implements Parcelable {
    public static final Parcelable.Creator<BusRouteResult> CREATOR = new Parcelable.Creator<BusRouteResult>() { // from class: com.amap.api.services.route.BusRouteResult.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ BusRouteResult createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ BusRouteResult[] newArray(int i) {
            return a(i);
        }

        private static BusRouteResult a(Parcel parcel) {
            return new BusRouteResult(parcel);
        }

        private static BusRouteResult[] a(int i) {
            return new BusRouteResult[i];
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private float f1524a;
    private List<BusPath> b;
    private RouteSearch.BusRouteQuery c;

    @Override // com.amap.api.services.route.RouteResult, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public float getTaxiCost() {
        return this.f1524a;
    }

    public void setTaxiCost(float f) {
        this.f1524a = f;
    }

    public List<BusPath> getPaths() {
        return this.b;
    }

    public void setPaths(List<BusPath> list) {
        this.b = list;
    }

    public RouteSearch.BusRouteQuery getBusQuery() {
        return this.c;
    }

    public void setBusQuery(RouteSearch.BusRouteQuery busRouteQuery) {
        this.c = busRouteQuery;
    }

    @Override // com.amap.api.services.route.RouteResult, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeFloat(this.f1524a);
        parcel.writeTypedList(this.b);
        parcel.writeParcelable(this.c, i);
    }

    public BusRouteResult(Parcel parcel) {
        super(parcel);
        this.b = new ArrayList();
        this.f1524a = parcel.readFloat();
        this.b = parcel.createTypedArrayList(BusPath.CREATOR);
        this.c = (RouteSearch.BusRouteQuery) parcel.readParcelable(RouteSearch.BusRouteQuery.class.getClassLoader());
    }

    public BusRouteResult() {
        this.b = new ArrayList();
    }
}
