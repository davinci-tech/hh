package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.busline.BusLineItem;
import com.amap.api.services.busline.BusStationItem;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class RouteBusLineItem extends BusLineItem implements Parcelable {
    public static final Parcelable.Creator<RouteBusLineItem> CREATOR = new Parcelable.Creator<RouteBusLineItem>() { // from class: com.amap.api.services.route.RouteBusLineItem.1
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ RouteBusLineItem[] newArray(int i) {
            return null;
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ RouteBusLineItem createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        private static RouteBusLineItem a(Parcel parcel) {
            return new RouteBusLineItem(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private BusStationItem f1552a;
    private BusStationItem b;
    private List<LatLonPoint> c;
    private int d;
    private List<BusStationItem> e;
    private float f;

    @Override // com.amap.api.services.busline.BusLineItem, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BusStationItem getDepartureBusStation() {
        return this.f1552a;
    }

    public void setDepartureBusStation(BusStationItem busStationItem) {
        this.f1552a = busStationItem;
    }

    public BusStationItem getArrivalBusStation() {
        return this.b;
    }

    public void setArrivalBusStation(BusStationItem busStationItem) {
        this.b = busStationItem;
    }

    public List<LatLonPoint> getPolyline() {
        return this.c;
    }

    public void setPolyline(List<LatLonPoint> list) {
        this.c = list;
    }

    public int getPassStationNum() {
        return this.d;
    }

    public void setPassStationNum(int i) {
        this.d = i;
    }

    public List<BusStationItem> getPassStations() {
        return this.e;
    }

    public void setPassStations(List<BusStationItem> list) {
        this.e = list;
    }

    public float getDuration() {
        return this.f;
    }

    public void setDuration(float f) {
        this.f = f;
    }

    @Override // com.amap.api.services.busline.BusLineItem, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.f1552a, i);
        parcel.writeParcelable(this.b, i);
        parcel.writeTypedList(this.c);
        parcel.writeInt(this.d);
        parcel.writeTypedList(this.e);
        parcel.writeFloat(this.f);
    }

    public RouteBusLineItem(Parcel parcel) {
        super(parcel);
        this.c = new ArrayList();
        this.e = new ArrayList();
        this.f1552a = (BusStationItem) parcel.readParcelable(BusStationItem.class.getClassLoader());
        this.b = (BusStationItem) parcel.readParcelable(BusStationItem.class.getClassLoader());
        this.c = parcel.createTypedArrayList(LatLonPoint.CREATOR);
        this.d = parcel.readInt();
        this.e = parcel.createTypedArrayList(BusStationItem.CREATOR);
        this.f = parcel.readFloat();
    }

    public RouteBusLineItem() {
        this.c = new ArrayList();
        this.e = new ArrayList();
    }

    @Override // com.amap.api.services.busline.BusLineItem
    public int hashCode() {
        int hashCode = super.hashCode();
        BusStationItem busStationItem = this.b;
        int hashCode2 = busStationItem == null ? 0 : busStationItem.hashCode();
        BusStationItem busStationItem2 = this.f1552a;
        return (((hashCode * 31) + hashCode2) * 31) + (busStationItem2 != null ? busStationItem2.hashCode() : 0);
    }

    @Override // com.amap.api.services.busline.BusLineItem
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj) || getClass() != obj.getClass()) {
            return false;
        }
        RouteBusLineItem routeBusLineItem = (RouteBusLineItem) obj;
        BusStationItem busStationItem = this.b;
        if (busStationItem == null) {
            if (routeBusLineItem.b != null) {
                return false;
            }
        } else if (!busStationItem.equals(routeBusLineItem.b)) {
            return false;
        }
        BusStationItem busStationItem2 = this.f1552a;
        if (busStationItem2 == null) {
            if (routeBusLineItem.f1552a != null) {
                return false;
            }
        } else if (!busStationItem2.equals(routeBusLineItem.f1552a)) {
            return false;
        }
        return true;
    }
}
