package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.route.RouteSearch;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class RideRouteResult extends RouteResult implements Parcelable {
    public static final Parcelable.Creator<RideRouteResult> CREATOR = new Parcelable.Creator<RideRouteResult>() { // from class: com.amap.api.services.route.RideRouteResult.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ RideRouteResult createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ RideRouteResult[] newArray(int i) {
            return a(i);
        }

        private static RideRouteResult a(Parcel parcel) {
            return new RideRouteResult(parcel);
        }

        private static RideRouteResult[] a(int i) {
            return new RideRouteResult[i];
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private List<RidePath> f1550a;
    private RouteSearch.RideRouteQuery b;

    @Override // com.amap.api.services.route.RouteResult, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<RidePath> getPaths() {
        return this.f1550a;
    }

    public void setPaths(List<RidePath> list) {
        this.f1550a = list;
    }

    public RouteSearch.RideRouteQuery getRideQuery() {
        return this.b;
    }

    public void setRideQuery(RouteSearch.RideRouteQuery rideRouteQuery) {
        this.b = rideRouteQuery;
    }

    @Override // com.amap.api.services.route.RouteResult, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeTypedList(this.f1550a);
        parcel.writeParcelable(this.b, i);
    }

    public RideRouteResult(Parcel parcel) {
        super(parcel);
        this.f1550a = new ArrayList();
        this.f1550a = parcel.createTypedArrayList(RidePath.CREATOR);
        this.b = (RouteSearch.RideRouteQuery) parcel.readParcelable(RouteSearch.RideRouteQuery.class.getClassLoader());
    }

    public RideRouteResult() {
        this.f1550a = new ArrayList();
    }
}
