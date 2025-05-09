package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.route.RouteSearch;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class WalkRouteResult extends RouteResult implements Parcelable {
    public static final Parcelable.Creator<WalkRouteResult> CREATOR = new Parcelable.Creator<WalkRouteResult>() { // from class: com.amap.api.services.route.WalkRouteResult.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ WalkRouteResult createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ WalkRouteResult[] newArray(int i) {
            return a(i);
        }

        private static WalkRouteResult a(Parcel parcel) {
            return new WalkRouteResult(parcel);
        }

        private static WalkRouteResult[] a(int i) {
            return new WalkRouteResult[i];
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private List<WalkPath> f1586a;
    private RouteSearch.WalkRouteQuery b;

    @Override // com.amap.api.services.route.RouteResult, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<WalkPath> getPaths() {
        return this.f1586a;
    }

    public void setPaths(List<WalkPath> list) {
        this.f1586a = list;
    }

    public RouteSearch.WalkRouteQuery getWalkQuery() {
        return this.b;
    }

    public void setWalkQuery(RouteSearch.WalkRouteQuery walkRouteQuery) {
        this.b = walkRouteQuery;
    }

    @Override // com.amap.api.services.route.RouteResult, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeTypedList(this.f1586a);
        parcel.writeParcelable(this.b, i);
    }

    public WalkRouteResult(Parcel parcel) {
        super(parcel);
        this.f1586a = new ArrayList();
        this.f1586a = parcel.createTypedArrayList(WalkPath.CREATOR);
        this.b = (RouteSearch.WalkRouteQuery) parcel.readParcelable(RouteSearch.WalkRouteQuery.class.getClassLoader());
    }

    public WalkRouteResult() {
        this.f1586a = new ArrayList();
    }
}
