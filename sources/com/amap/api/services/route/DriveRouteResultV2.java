package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.route.RouteSearchV2;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class DriveRouteResultV2 extends RouteResult implements Parcelable {
    public static final Parcelable.Creator<DriveRouteResultV2> CREATOR = new Parcelable.Creator<DriveRouteResultV2>() { // from class: com.amap.api.services.route.DriveRouteResultV2.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ DriveRouteResultV2 createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ DriveRouteResultV2[] newArray(int i) {
            return a(i);
        }

        private static DriveRouteResultV2 a(Parcel parcel) {
            return new DriveRouteResultV2(parcel);
        }

        private static DriveRouteResultV2[] a(int i) {
            return new DriveRouteResultV2[i];
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private float f1540a;
    private List<DrivePathV2> b;
    private RouteSearchV2.DriveRouteQuery c;

    @Override // com.amap.api.services.route.RouteResult, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public float getTaxiCost() {
        return this.f1540a;
    }

    public void setTaxiCost(float f) {
        this.f1540a = f;
    }

    public List<DrivePathV2> getPaths() {
        return this.b;
    }

    public void setPaths(List<DrivePathV2> list) {
        this.b = list;
    }

    public RouteSearchV2.DriveRouteQuery getDriveQuery() {
        return this.c;
    }

    public void setDriveQuery(RouteSearchV2.DriveRouteQuery driveRouteQuery) {
        this.c = driveRouteQuery;
    }

    @Override // com.amap.api.services.route.RouteResult, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeFloat(this.f1540a);
        parcel.writeTypedList(this.b);
        parcel.writeParcelable(this.c, i);
    }

    public DriveRouteResultV2(Parcel parcel) {
        super(parcel);
        this.b = new ArrayList();
        this.f1540a = parcel.readFloat();
        this.b = parcel.createTypedArrayList(DrivePathV2.CREATOR);
        this.c = (RouteSearchV2.DriveRouteQuery) parcel.readParcelable(RouteSearchV2.DriveRouteQuery.class.getClassLoader());
    }

    public DriveRouteResultV2() {
        this.b = new ArrayList();
    }
}
