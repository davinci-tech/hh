package com.amap.api.maps;

import android.content.Context;
import com.amap.api.col.p0003sl.Cdo;
import com.amap.api.col.p0003sl.ak;
import com.amap.api.col.p0003sl.ds;
import com.amap.api.col.p0003sl.iv;
import com.amap.api.maps.model.LatLng;
import com.amap.api.services.geocoder.GeocodeSearch;

/* loaded from: classes2.dex */
public class CoordinateConverter {
    private static final String TAG = "CoordinateConverter";
    private Context ctx;
    private CoordType coordType = null;
    private LatLng sourceLatLng = null;

    public enum CoordType {
        BAIDU,
        MAPBAR,
        GPS,
        MAPABC,
        SOSOMAP,
        ALIYUN,
        GOOGLE
    }

    public CoordinateConverter(Context context) {
        this.ctx = context;
    }

    public CoordinateConverter from(CoordType coordType) {
        this.coordType = coordType;
        return this;
    }

    public CoordinateConverter coord(LatLng latLng) {
        this.sourceLatLng = latLng;
        return this;
    }

    public LatLng convert() {
        String str;
        LatLng latLng = null;
        if (this.coordType == null || this.sourceLatLng == null) {
            return null;
        }
        try {
            switch (AnonymousClass1.f1414a[this.coordType.ordinal()]) {
                case 1:
                    latLng = ak.a(this.sourceLatLng);
                    str = "baidu";
                    break;
                case 2:
                    latLng = ak.b(this.ctx, this.sourceLatLng);
                    str = "mapbar";
                    break;
                case 3:
                    latLng = this.sourceLatLng;
                    str = "mapabc";
                    break;
                case 4:
                    latLng = this.sourceLatLng;
                    str = "sosomap";
                    break;
                case 5:
                    latLng = this.sourceLatLng;
                    str = "aliyun";
                    break;
                case 6:
                    latLng = this.sourceLatLng;
                    str = "google";
                    break;
                case 7:
                    latLng = ak.a(this.ctx, this.sourceLatLng);
                    str = GeocodeSearch.GPS;
                    break;
                default:
                    str = "";
                    break;
            }
            ds.a(this.ctx, str);
            return latLng;
        } catch (Throwable th) {
            th.printStackTrace();
            iv.c(th, TAG, "convert");
            return this.sourceLatLng;
        }
    }

    /* renamed from: com.amap.api.maps.CoordinateConverter$1, reason: invalid class name */
    static final /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f1414a;

        static {
            int[] iArr = new int[CoordType.values().length];
            f1414a = iArr;
            try {
                iArr[CoordType.BAIDU.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1414a[CoordType.MAPBAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1414a[CoordType.MAPABC.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1414a[CoordType.SOSOMAP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1414a[CoordType.ALIYUN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1414a[CoordType.GOOGLE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1414a[CoordType.GPS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public static boolean isAMapDataAvailable(double d, double d2) {
        return Cdo.a(d, d2);
    }
}
