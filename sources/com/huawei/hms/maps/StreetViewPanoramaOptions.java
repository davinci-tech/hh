package com.huawei.hms.maps;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.maps.model.LatLng;
import com.huawei.hms.maps.model.StreetViewPanoramaCamera;
import com.huawei.hms.maps.model.StreetViewSource;

@Deprecated
/* loaded from: classes9.dex */
public final class StreetViewPanoramaOptions implements Parcelable {
    public static final Parcelable.Creator<StreetViewPanoramaOptions> CREATOR = new Parcelable.Creator<StreetViewPanoramaOptions>() { // from class: com.huawei.hms.maps.StreetViewPanoramaOptions.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public StreetViewPanoramaOptions[] newArray(int i) {
            return new StreetViewPanoramaOptions[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public StreetViewPanoramaOptions createFromParcel(Parcel parcel) {
            return new StreetViewPanoramaOptions(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private StreetViewPanoramaCamera f4942a;
    private String b;
    private LatLng c;
    private Integer d;
    private Boolean e = true;
    private Boolean f = true;
    private Boolean g = true;
    private Boolean h = true;
    private Boolean i;
    private StreetViewSource j;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
    }

    public StreetViewPanoramaOptions zoomGesturesEnabled(boolean z) {
        this.f = Boolean.valueOf(z);
        return this;
    }

    public StreetViewPanoramaOptions userNavigationEnabled(boolean z) {
        this.e = Boolean.valueOf(z);
        return this;
    }

    public StreetViewPanoramaOptions useViewLifecycleInFragment(boolean z) {
        this.i = Boolean.valueOf(z);
        return this;
    }

    public String toString() {
        return "";
    }

    public StreetViewPanoramaOptions streetNamesEnabled(boolean z) {
        this.h = Boolean.valueOf(z);
        return this;
    }

    public StreetViewPanoramaOptions position(LatLng latLng, Integer num, StreetViewSource streetViewSource) {
        this.c = latLng;
        this.d = num;
        this.j = streetViewSource;
        return this;
    }

    public StreetViewPanoramaOptions position(LatLng latLng, Integer num) {
        this.c = latLng;
        this.d = num;
        return this;
    }

    public StreetViewPanoramaOptions position(LatLng latLng, StreetViewSource streetViewSource) {
        this.c = latLng;
        this.j = streetViewSource;
        return this;
    }

    public StreetViewPanoramaOptions position(LatLng latLng) {
        this.c = latLng;
        return this;
    }

    public StreetViewPanoramaOptions panoramaId(String str) {
        this.b = str;
        return this;
    }

    public StreetViewPanoramaOptions panoramaCamera(StreetViewPanoramaCamera streetViewPanoramaCamera) {
        this.f4942a = streetViewPanoramaCamera;
        return this;
    }

    public StreetViewPanoramaOptions panningGesturesEnabled(boolean z) {
        this.g = Boolean.valueOf(z);
        return this;
    }

    public Boolean getZoomGesturesEnabled() {
        return this.f;
    }

    public Boolean getUserNavigationEnabled() {
        return this.e;
    }

    public Boolean getUseViewLifecycleInFragment() {
        return this.i;
    }

    public StreetViewPanoramaCamera getStreetViewPanoramaCamera() {
        return this.f4942a;
    }

    public Boolean getStreetNamesEnabled() {
        return this.h;
    }

    public StreetViewSource getSource() {
        return this.j;
    }

    public Integer getRadius() {
        return this.d;
    }

    public LatLng getPosition() {
        return this.c;
    }

    public String getPanoramaId() {
        return this.b;
    }

    public Boolean getPanningGesturesEnabled() {
        return this.g;
    }

    protected StreetViewPanoramaOptions(Parcel parcel) {
    }

    public StreetViewPanoramaOptions() {
    }
}
