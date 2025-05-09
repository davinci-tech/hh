package com.huawei.hms.maps;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import com.huawei.health.R;
import com.huawei.hms.common.parcel.ParcelReader;
import com.huawei.hms.common.parcel.ParcelWrite;
import com.huawei.hms.maps.model.CameraPosition;
import com.huawei.hms.maps.model.LatLng;
import com.huawei.hms.maps.model.LatLngBounds;
import com.huawei.hms.maps.utils.LogM;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public final class HuaweiMapOptions implements Parcelable {
    public static final Parcelable.Creator<HuaweiMapOptions> CREATOR = new Parcelable.Creator<HuaweiMapOptions>() { // from class: com.huawei.hms.maps.HuaweiMapOptions.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public HuaweiMapOptions[] newArray(int i) {
            return new HuaweiMapOptions[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public HuaweiMapOptions createFromParcel(Parcel parcel) {
            return new HuaweiMapOptions(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private int f4930a;
    private Boolean b;
    private Boolean c;
    private CameraPosition d;
    private Boolean e;
    private Boolean f;
    private Boolean g;
    private Boolean h;
    private Boolean i;
    private Boolean j;
    private Boolean k;
    private Boolean l;
    private Boolean m;
    private Float n;
    private Float o;
    private LatLngBounds p;
    private Boolean q;
    private String r;
    private String s;
    private Boolean t;
    private Boolean u;
    private int v;
    private List<String> w;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HuaweiMapOptions zoomGesturesEnabled(boolean z) {
        this.h = Boolean.valueOf(z);
        return this;
    }

    public HuaweiMapOptions zoomControlsEnabled(boolean z) {
        this.e = Boolean.valueOf(z);
        return this;
    }

    public HuaweiMapOptions zOrderOnTop(boolean z) {
        this.b = Boolean.valueOf(z);
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ParcelWrite parcelWrite = new ParcelWrite(parcel);
        int beginObjectHeader = parcelWrite.beginObjectHeader();
        parcelWrite.writeInt(2, this.f4930a);
        parcelWrite.writeBooleanObject(3, this.b);
        parcelWrite.writeBooleanObject(4, this.c);
        parcelWrite.writeBooleanObject(5, this.e);
        parcelWrite.writeBooleanObject(6, this.f);
        parcelWrite.writeBooleanObject(7, this.g);
        parcelWrite.writeBooleanObject(8, this.h);
        parcelWrite.writeBooleanObject(9, this.i);
        parcelWrite.writeBooleanObject(10, this.j);
        parcelWrite.writeBooleanObject(11, this.k);
        parcelWrite.writeBooleanObject(12, this.l);
        parcelWrite.writeBooleanObject(13, this.m);
        parcelWrite.writeFloatObject(14, this.n, true);
        parcelWrite.writeFloatObject(15, this.o, true);
        parcelWrite.writeParcelable(16, this.p, i, false);
        parcelWrite.writeParcelable(17, this.d, i, false);
        parcelWrite.writeBooleanObject(18, this.q);
        parcelWrite.writeString(19, this.r, false);
        parcelWrite.writeString(20, this.s, false);
        parcelWrite.writeBooleanObject(21, this.t, false);
        parcelWrite.writeBooleanObject(22, this.u, false);
        parcelWrite.writeInt(23, this.v);
        parcelWrite.writeStringList(24, this.w, false);
        parcelWrite.finishObjectHeader(beginObjectHeader);
    }

    public HuaweiMapOptions useViewLifecycleInFragment(boolean z) {
        this.c = Boolean.valueOf(z);
        return this;
    }

    public String toString() {
        return "HuaweiMapOptions{mapType=" + this.f4930a + ", zOrderOnTop=" + this.b + ", isUseViewLifecycleInFragment=" + this.c + ", cameraPosition=" + this.d + ", isZoomControlsEnabled=" + this.e + ", isCompassEnabled=" + this.f + ", isScrollGesturesEnabled=" + this.g + ", isZoomGesturesEnabled=" + this.h + ", isTiltGesturesEnabled=" + this.i + ", isRotateGesturesEnabled=" + this.j + ", isLiteMode=" + this.k + ", isMapToolbarEnabled=" + this.l + ", isAmbientEnabled=" + this.m + ", minZoomLevel=" + this.n + ", maxZoomLevel=" + this.o + ", latLngBounds=" + this.p + ", styleId=" + this.r + ", previewId=" + this.s + '}';
    }

    public HuaweiMapOptions tiltGesturesEnabled(boolean z) {
        this.i = Boolean.valueOf(z);
        return this;
    }

    public HuaweiMapOptions styleId(String str) {
        this.r = str;
        return this;
    }

    public HuaweiMapOptions styleEnable(boolean z) {
        this.t = Boolean.valueOf(z);
        return this;
    }

    public HuaweiMapOptions scrollGesturesEnabled(boolean z) {
        this.g = Boolean.valueOf(z);
        return this;
    }

    public HuaweiMapOptions rotateGesturesEnabled(boolean z) {
        this.j = Boolean.valueOf(z);
        return this;
    }

    public HuaweiMapOptions previewId(String str) {
        this.s = str;
        return this;
    }

    public HuaweiMapOptions minZoomPreference(float f) {
        this.n = Float.valueOf(f);
        return this;
    }

    public HuaweiMapOptions maxZoomPreference(float f) {
        this.o = Float.valueOf(f);
        return this;
    }

    public HuaweiMapOptions mapType(int i) {
        this.f4930a = i;
        return this;
    }

    public HuaweiMapOptions mapToolbarEnabled(boolean z) {
        this.l = Boolean.valueOf(z);
        return this;
    }

    public HuaweiMapOptions mapStyle(int i) {
        this.v = i;
        return this;
    }

    public HuaweiMapOptions liteMode(boolean z) {
        this.k = Boolean.valueOf(z);
        return this;
    }

    public HuaweiMapOptions latLngBoundsForCameraTarget(LatLngBounds latLngBounds) {
        this.p = latLngBounds;
        return this;
    }

    public Boolean getZoomGesturesEnabled() {
        return this.h;
    }

    public Boolean getZoomControlsEnabled() {
        return this.e;
    }

    public Boolean getZOrderOnTop() {
        return this.b;
    }

    public Boolean getUseViewLifecycleInFragment() {
        return this.c;
    }

    public Boolean getTiltGesturesEnabled() {
        return this.i;
    }

    public String getStyleId() {
        return this.r;
    }

    public Boolean getStyleEnable() {
        return this.t;
    }

    public Boolean getScrollGesturesEnabled() {
        return this.g;
    }

    public Boolean getRotateGesturesEnabled() {
        return this.j;
    }

    public String getPreviewId() {
        return this.s;
    }

    public Float getMinZoomPreference() {
        return this.n;
    }

    public Float getMaxZoomPreference() {
        return this.o;
    }

    public int getMapType() {
        return this.f4930a;
    }

    public Boolean getMapToolbarEnabled() {
        return false;
    }

    public int getMapStyle() {
        return this.v;
    }

    public Boolean getLiteMode() {
        return this.k;
    }

    public LatLngBounds getLatLngBoundsForCameraTarget() {
        return this.p;
    }

    public Boolean getDark() {
        return this.u;
    }

    public List<String> getCustomStyles() {
        return this.w;
    }

    public Boolean getCompassEnabled() {
        return this.f;
    }

    public CameraPosition getCamera() {
        return this.d;
    }

    public Boolean getAmbientEnabled() {
        return false;
    }

    public HuaweiMapOptions dark(boolean z) {
        this.u = Boolean.valueOf(z);
        return this;
    }

    public HuaweiMapOptions customStyles(List<String> list) {
        this.w = list;
        return this;
    }

    public HuaweiMapOptions compassEnabled(boolean z) {
        this.f = Boolean.valueOf(z);
        return this;
    }

    public HuaweiMapOptions camera(CameraPosition cameraPosition) {
        this.d = cameraPosition;
        return this;
    }

    public HuaweiMapOptions ambientEnabled(boolean z) {
        this.m = Boolean.valueOf(z);
        return this;
    }

    public static HuaweiMapOptions createFromAttributes(Context context, AttributeSet attributeSet) {
        HuaweiMapOptions huaweiMapOptions = new HuaweiMapOptions();
        if (attributeSet == null) {
            LogM.d("ContentValues", "createFromAttributes() attrs is null");
            return huaweiMapOptions;
        }
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, new int[]{R.attr._2131099728_res_0x7f060050, R.attr._2131099829_res_0x7f0600b5, R.attr._2131099830_res_0x7f0600b6, R.attr._2131099831_res_0x7f0600b7, R.attr._2131099832_res_0x7f0600b8, R.attr._2131099833_res_0x7f0600b9, R.attr._2131099834_res_0x7f0600ba, R.attr._2131099835_res_0x7f0600bb, R.attr._2131099986_res_0x7f060152, R.attr._2131100700_res_0x7f06041c, R.attr._2131100701_res_0x7f06041d, R.attr._2131100702_res_0x7f06041e, R.attr._2131100703_res_0x7f06041f, R.attr._2131100811_res_0x7f06048b, R.attr._2131100815_res_0x7f06048f, R.attr._2131100816_res_0x7f060490, R.attr._2131100958_res_0x7f06051e, R.attr._2131101152_res_0x7f0605e0, R.attr._2131101153_res_0x7f0605e1, R.attr._2131101328_res_0x7f060690, R.attr._2131101329_res_0x7f060691, R.attr._2131101330_res_0x7f060692, R.attr._2131101331_res_0x7f060693, R.attr._2131101332_res_0x7f060694, R.attr._2131101333_res_0x7f060695, R.attr._2131101334_res_0x7f060696, R.attr._2131101335_res_0x7f060697, R.attr._2131101343_res_0x7f06069f, R.attr._2131101399_res_0x7f0606d7}, 0, 0);
        try {
            int i = obtainStyledAttributes.getInt(15, 1);
            LogM.d("ContentValues", "createFromAttributes() mapyType is " + i);
            if (i != 0 && i != 3 && i != 8) {
                i = 1;
            }
            huaweiMapOptions.f4930a = i;
            huaweiMapOptions.d = CameraPosition.createFromAttributes(context, attributeSet);
            huaweiMapOptions.e = Boolean.valueOf(obtainStyledAttributes.getBoolean(25, true));
            huaweiMapOptions.f = Boolean.valueOf(obtainStyledAttributes.getBoolean(19, true));
            if (obtainStyledAttributes.hasValue(26)) {
                huaweiMapOptions.h = Boolean.valueOf(obtainStyledAttributes.getBoolean(26, true));
            }
            if (obtainStyledAttributes.hasValue(22)) {
                huaweiMapOptions.g = Boolean.valueOf(obtainStyledAttributes.getBoolean(22, true));
            }
            if (obtainStyledAttributes.hasValue(21)) {
                huaweiMapOptions.j = Boolean.valueOf(obtainStyledAttributes.getBoolean(21, true));
            }
            if (obtainStyledAttributes.hasValue(24)) {
                huaweiMapOptions.i = Boolean.valueOf(obtainStyledAttributes.getBoolean(24, true));
            }
            huaweiMapOptions.b = Boolean.valueOf(obtainStyledAttributes.getBoolean(28, false));
            huaweiMapOptions.c = Boolean.valueOf(obtainStyledAttributes.getBoolean(27, true));
            huaweiMapOptions.k = Boolean.valueOf(obtainStyledAttributes.getBoolean(13, false));
            huaweiMapOptions.q = Boolean.valueOf(obtainStyledAttributes.getBoolean(23, false));
            huaweiMapOptions.l = Boolean.valueOf(obtainStyledAttributes.getBoolean(20, false));
            huaweiMapOptions.m = Boolean.valueOf(obtainStyledAttributes.getBoolean(0, false));
            huaweiMapOptions.n = Float.valueOf(obtainStyledAttributes.getFloat(3, 3.0f));
            huaweiMapOptions.o = Float.valueOf(obtainStyledAttributes.getFloat(2, 22.0f));
            huaweiMapOptions.r = obtainStyledAttributes.getString(18);
            huaweiMapOptions.s = obtainStyledAttributes.getString(16);
            huaweiMapOptions.t = Boolean.valueOf(obtainStyledAttributes.getBoolean(17, true));
            huaweiMapOptions.u = Boolean.valueOf(obtainStyledAttributes.getBoolean(8, false));
            huaweiMapOptions.latLngBoundsForCameraTarget(buildLatLngBounds(context, attributeSet));
            huaweiMapOptions.v = obtainStyledAttributes.getInt(14, 0);
            return huaweiMapOptions;
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public static LatLngBounds buildLatLngBounds(Context context, AttributeSet attributeSet) {
        if (context == null || attributeSet == null) {
            return null;
        }
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, new int[]{R.attr._2131099728_res_0x7f060050, R.attr._2131099829_res_0x7f0600b5, R.attr._2131099830_res_0x7f0600b6, R.attr._2131099831_res_0x7f0600b7, R.attr._2131099832_res_0x7f0600b8, R.attr._2131099833_res_0x7f0600b9, R.attr._2131099834_res_0x7f0600ba, R.attr._2131099835_res_0x7f0600bb, R.attr._2131099986_res_0x7f060152, R.attr._2131100700_res_0x7f06041c, R.attr._2131100701_res_0x7f06041d, R.attr._2131100702_res_0x7f06041e, R.attr._2131100703_res_0x7f06041f, R.attr._2131100811_res_0x7f06048b, R.attr._2131100815_res_0x7f06048f, R.attr._2131100816_res_0x7f060490, R.attr._2131100958_res_0x7f06051e, R.attr._2131101152_res_0x7f0605e0, R.attr._2131101153_res_0x7f0605e1, R.attr._2131101328_res_0x7f060690, R.attr._2131101329_res_0x7f060691, R.attr._2131101330_res_0x7f060692, R.attr._2131101331_res_0x7f060693, R.attr._2131101332_res_0x7f060694, R.attr._2131101333_res_0x7f060695, R.attr._2131101334_res_0x7f060696, R.attr._2131101335_res_0x7f060697, R.attr._2131101343_res_0x7f06069f, R.attr._2131101399_res_0x7f0606d7});
        Float valueOf = obtainAttributes.hasValue(11) ? Float.valueOf(obtainAttributes.getFloat(11, 0.0f)) : null;
        Float valueOf2 = obtainAttributes.hasValue(12) ? Float.valueOf(obtainAttributes.getFloat(12, 0.0f)) : null;
        Float valueOf3 = obtainAttributes.hasValue(9) ? Float.valueOf(obtainAttributes.getFloat(9, 0.0f)) : null;
        Float valueOf4 = obtainAttributes.hasValue(10) ? Float.valueOf(obtainAttributes.getFloat(10, 0.0f)) : null;
        obtainAttributes.recycle();
        if (valueOf == null || valueOf2 == null || valueOf3 == null || valueOf4 == null) {
            return null;
        }
        return new LatLngBounds(new LatLng(valueOf.floatValue(), valueOf2.floatValue()), new LatLng(valueOf3.floatValue(), valueOf4.floatValue()));
    }

    public static CameraPosition buildCameraPosition(Context context, AttributeSet attributeSet) {
        if (context == null || attributeSet == null) {
            return null;
        }
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, new int[]{R.attr._2131099728_res_0x7f060050, R.attr._2131099829_res_0x7f0600b5, R.attr._2131099830_res_0x7f0600b6, R.attr._2131099831_res_0x7f0600b7, R.attr._2131099832_res_0x7f0600b8, R.attr._2131099833_res_0x7f0600b9, R.attr._2131099834_res_0x7f0600ba, R.attr._2131099835_res_0x7f0600bb, R.attr._2131099986_res_0x7f060152, R.attr._2131100700_res_0x7f06041c, R.attr._2131100701_res_0x7f06041d, R.attr._2131100702_res_0x7f06041e, R.attr._2131100703_res_0x7f06041f, R.attr._2131100811_res_0x7f06048b, R.attr._2131100815_res_0x7f06048f, R.attr._2131100816_res_0x7f060490, R.attr._2131100958_res_0x7f06051e, R.attr._2131101152_res_0x7f0605e0, R.attr._2131101153_res_0x7f0605e1, R.attr._2131101328_res_0x7f060690, R.attr._2131101329_res_0x7f060691, R.attr._2131101330_res_0x7f060692, R.attr._2131101331_res_0x7f060693, R.attr._2131101332_res_0x7f060694, R.attr._2131101333_res_0x7f060695, R.attr._2131101334_res_0x7f060696, R.attr._2131101335_res_0x7f060697, R.attr._2131101343_res_0x7f06069f, R.attr._2131101399_res_0x7f0606d7});
        LatLng latLng = new LatLng(obtainAttributes.hasValue(4) ? obtainAttributes.getFloat(4, 0.0f) : 0.0f, obtainAttributes.hasValue(5) ? obtainAttributes.getFloat(5, 0.0f) : 0.0f);
        CameraPosition.Builder builder = CameraPosition.builder();
        builder.target(latLng);
        if (obtainAttributes.hasValue(7)) {
            builder.zoom(obtainAttributes.getFloat(7, 0.0f));
        }
        if (obtainAttributes.hasValue(1)) {
            builder.bearing(obtainAttributes.getFloat(1, 0.0f));
        }
        if (obtainAttributes.hasValue(6)) {
            builder.tilt(obtainAttributes.getFloat(6, 0.0f));
        }
        obtainAttributes.recycle();
        return builder.build();
    }

    protected HuaweiMapOptions(Parcel parcel) {
        this.f4930a = 1;
        this.e = true;
        this.f = true;
        this.g = true;
        this.h = true;
        this.i = true;
        this.j = true;
        this.k = false;
        this.n = Float.valueOf(3.0f);
        this.o = Float.valueOf(20.0f);
        this.p = null;
        this.t = true;
        this.u = false;
        this.v = 0;
        this.w = new ArrayList();
        ParcelReader parcelReader = new ParcelReader(parcel);
        this.f4930a = parcelReader.readInt(2, this.f4930a);
        this.b = parcelReader.readBooleanObject(3, null);
        this.c = parcelReader.readBooleanObject(4, null);
        this.e = parcelReader.readBooleanObject(5, null);
        this.f = parcelReader.readBooleanObject(6, null);
        this.g = parcelReader.readBooleanObject(7, null);
        this.h = parcelReader.readBooleanObject(8, null);
        this.i = parcelReader.readBooleanObject(9, null);
        this.j = parcelReader.readBooleanObject(10, null);
        this.k = parcelReader.readBooleanObject(11, null);
        this.l = parcelReader.readBooleanObject(12, null);
        this.m = parcelReader.readBooleanObject(13, null);
        this.n = parcelReader.readFloatObject(14, null);
        this.o = parcelReader.readFloatObject(15, null);
        this.p = (LatLngBounds) parcelReader.readParcelable(16, LatLngBounds.CREATOR, null);
        this.d = (CameraPosition) parcelReader.readParcelable(17, CameraPosition.CREATOR, null);
        this.q = parcelReader.readBooleanObject(18, null);
        this.r = parcelReader.createString(19, null);
        this.s = parcelReader.createString(20, null);
        this.t = parcelReader.readBooleanObject(21, null);
        this.u = parcelReader.readBooleanObject(22, null);
        this.v = parcelReader.readInt(23, this.v);
        this.w = parcelReader.createStringList(24, (ArrayList) this.w);
    }

    public HuaweiMapOptions() {
        this.f4930a = 1;
        this.e = true;
        this.f = true;
        this.g = true;
        this.h = true;
        this.i = true;
        this.j = true;
        this.k = false;
        this.n = Float.valueOf(3.0f);
        this.o = Float.valueOf(20.0f);
        this.p = null;
        this.t = true;
        this.u = false;
        this.v = 0;
        this.w = new ArrayList();
    }
}
