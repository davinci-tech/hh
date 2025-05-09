package com.huawei.hms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.protobuf.DescriptorProtos;
import com.huawei.hms.common.parcel.ParcelReader;
import com.huawei.hms.common.parcel.ParcelWrite;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class PolylineOptions implements Parcelable {
    public static final Parcelable.Creator<PolylineOptions> CREATOR = new Parcelable.Creator<PolylineOptions>() { // from class: com.huawei.hms.maps.model.PolylineOptions.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public PolylineOptions[] newArray(int i) {
            return new PolylineOptions[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public PolylineOptions createFromParcel(Parcel parcel) {
            return new PolylineOptions(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private final List<LatLng> f5009a;
    private int b;
    private float c;
    private float d;
    private boolean e;
    private boolean f;
    private boolean g;
    private Cap h;
    private Cap i;
    private int j;
    private List<PatternItem> k;
    private List<Integer> l;
    private boolean m;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PolylineOptions zIndex(float f) {
        this.d = f;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ParcelWrite parcelWrite = new ParcelWrite(parcel);
        int beginObjectHeader = parcelWrite.beginObjectHeader();
        parcelWrite.writeTypedList(2, this.f5009a, false);
        parcelWrite.writeInt(3, this.b);
        parcelWrite.writeFloat(4, this.c);
        parcelWrite.writeFloat(5, this.d);
        parcelWrite.writeBoolean(6, this.e);
        parcelWrite.writeBoolean(7, this.f);
        parcelWrite.writeBoolean(8, this.g);
        parcelWrite.writeParcelable(9, this.h, i, false);
        parcelWrite.writeParcelable(10, this.i, i, false);
        parcelWrite.writeInt(11, this.j);
        parcelWrite.writeTypedList(12, this.k, false);
        parcelWrite.writeIntegerList(13, this.l, false);
        parcelWrite.writeBoolean(14, this.m);
        parcelWrite.finishObjectHeader(beginObjectHeader);
    }

    public PolylineOptions width(float f) {
        this.c = f;
        return this;
    }

    public PolylineOptions visible(boolean z) {
        this.e = z;
        return this;
    }

    public PolylineOptions startCap(Cap cap) {
        this.h = cap;
        return this;
    }

    public PolylineOptions pattern(List<PatternItem> list) {
        this.k = list;
        return this;
    }

    public PolylineOptions jointType(int i) {
        this.j = i;
        return this;
    }

    public boolean isVisible() {
        return this.e;
    }

    public boolean isGradient() {
        return this.m;
    }

    public boolean isGeodesic() {
        return this.g;
    }

    public boolean isClickable() {
        return this.f;
    }

    public PolylineOptions gradient(boolean z) {
        this.m = z;
        return this;
    }

    public float getZIndex() {
        return this.d;
    }

    public float getWidth() {
        return this.c;
    }

    public Cap getStartCap() {
        return this.h;
    }

    public List<LatLng> getPoints() {
        return this.f5009a;
    }

    public List<PatternItem> getPattern() {
        return this.k;
    }

    public int getJointType() {
        return this.j;
    }

    public Cap getEndCap() {
        return this.i;
    }

    public List<Integer> getColorValues() {
        return this.l;
    }

    public int getColor() {
        return this.b;
    }

    public PolylineOptions geodesic(boolean z) {
        this.g = z;
        return this;
    }

    public PolylineOptions endCap(Cap cap) {
        this.i = cap;
        return this;
    }

    public PolylineOptions colorValues(List<Integer> list) {
        if (list != null && list.size() > 1) {
            ArrayList arrayList = new ArrayList(list);
            this.l = arrayList;
            if (arrayList.size() >= 100000) {
                ArrayList arrayList2 = new ArrayList(this.l);
                this.l.clear();
                this.l.addAll(arrayList2.subList(0, DescriptorProtos.Edition.EDITION_99999_TEST_ONLY_VALUE));
            }
        }
        return this;
    }

    public PolylineOptions color(int i) {
        this.b = i;
        return this;
    }

    public PolylineOptions clickable(boolean z) {
        this.f = z;
        return this;
    }

    public PolylineOptions addAll(Iterable<LatLng> iterable) {
        Iterator<LatLng> it = iterable.iterator();
        while (it.hasNext()) {
            this.f5009a.add(it.next());
        }
        return this;
    }

    public PolylineOptions add(LatLng... latLngArr) {
        this.f5009a.addAll(Arrays.asList(latLngArr));
        return this;
    }

    public PolylineOptions add(LatLng latLng) {
        this.f5009a.add(latLng);
        return this;
    }

    protected PolylineOptions(Parcel parcel) {
        ParcelReader parcelReader = new ParcelReader(parcel);
        this.f5009a = parcelReader.createTypedList(2, LatLng.CREATOR, new ArrayList());
        this.b = parcelReader.readInt(3, 0);
        this.c = parcelReader.readFloat(4, 10.0f);
        this.d = parcelReader.readFloat(5, 0.0f);
        this.e = parcelReader.readBoolean(6, true);
        this.f = parcelReader.readBoolean(7, false);
        this.g = parcelReader.readBoolean(8, false);
        this.h = (Cap) parcelReader.readParcelable(9, Cap.CREATOR, null);
        this.i = (Cap) parcelReader.readParcelable(10, Cap.CREATOR, null);
        this.j = parcelReader.readInt(11, 0);
        this.k = parcelReader.createTypedList(12, PatternItem.CREATOR, null);
        this.l = parcelReader.createIntegerList(13, new ArrayList<>());
        this.m = parcelReader.readBoolean(14, false);
    }

    public PolylineOptions() {
        this.f5009a = new ArrayList();
        this.c = 10.0f;
        this.b = -16777216;
        this.f = false;
        this.g = false;
        this.e = true;
        this.h = new ButtCap();
        this.i = new ButtCap();
        this.j = 0;
        this.k = null;
        this.l = new ArrayList();
        this.m = false;
    }
}
