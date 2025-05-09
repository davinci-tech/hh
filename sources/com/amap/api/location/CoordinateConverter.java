package com.amap.api.location;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.autonavi.aps.amapapi.utils.b;
import com.autonavi.aps.amapapi.utils.d;
import com.autonavi.aps.amapapi.utils.g;
import com.autonavi.aps.amapapi.utils.i;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public class CoordinateConverter {
    private static int b = 0;
    private static int c = 1;
    private static int d = 2;
    private static int e = 4;
    private static int f = 8;
    private static int g = 16;
    private static int h = 32;
    private static int i = 64;
    private Context j;
    private CoordType k = null;
    private DPoint l = null;

    /* renamed from: a, reason: collision with root package name */
    DPoint f1397a = null;

    public enum CoordType {
        BAIDU,
        MAPBAR,
        MAPABC,
        SOSOMAP,
        ALIYUN,
        GOOGLE,
        GPS
    }

    public CoordinateConverter(Context context) {
        this.j = context;
    }

    public CoordinateConverter from(CoordType coordType) {
        synchronized (this) {
            this.k = coordType;
        }
        return this;
    }

    public CoordinateConverter coord(DPoint dPoint) throws Exception {
        synchronized (this) {
            if (dPoint == null) {
                throw new IllegalArgumentException("传入经纬度对象为空");
            }
            if (dPoint.getLongitude() > 180.0d || dPoint.getLongitude() < -180.0d) {
                throw new IllegalArgumentException("请传入合理经度");
            }
            if (dPoint.getLatitude() > 90.0d || dPoint.getLatitude() < -90.0d) {
                throw new IllegalArgumentException("请传入合理纬度");
            }
            this.l = dPoint;
        }
        return this;
    }

    public DPoint convert() throws Exception {
        DPoint dPoint;
        synchronized (this) {
            if (this.k == null) {
                throw new IllegalArgumentException("转换坐标类型不能为空");
            }
            DPoint dPoint2 = this.l;
            if (dPoint2 == null) {
                throw new IllegalArgumentException("转换坐标源不能为空");
            }
            if (dPoint2.getLongitude() > 180.0d || this.l.getLongitude() < -180.0d) {
                throw new IllegalArgumentException("请传入合理经度");
            }
            if (this.l.getLatitude() > 90.0d || this.l.getLatitude() < -90.0d) {
                throw new IllegalArgumentException("请传入合理纬度");
            }
            boolean z = false;
            String str = null;
            switch (AnonymousClass1.f1398a[this.k.ordinal()]) {
                case 1:
                    this.f1397a = d.a(this.l);
                    int i2 = b;
                    int i3 = c;
                    if ((i2 & i3) == 0) {
                        str = "baidu";
                        b = i2 | i3;
                        z = true;
                        break;
                    }
                    break;
                case 2:
                    this.f1397a = d.b(this.j, this.l);
                    int i4 = b;
                    int i5 = d;
                    if ((i4 & i5) == 0) {
                        str = "mapbar";
                        b = i4 | i5;
                        z = true;
                        break;
                    }
                    break;
                case 3:
                    int i6 = b;
                    int i7 = e;
                    if ((i6 & i7) == 0) {
                        str = "mapabc";
                        b = i6 | i7;
                        z = true;
                    }
                    this.f1397a = this.l;
                    break;
                case 4:
                    int i8 = b;
                    int i9 = f;
                    if ((i8 & i9) == 0) {
                        str = "sosomap";
                        b = i8 | i9;
                        z = true;
                    }
                    this.f1397a = this.l;
                    break;
                case 5:
                    int i10 = b;
                    int i11 = g;
                    if ((i10 & i11) == 0) {
                        str = "aliyun";
                        b = i10 | i11;
                        z = true;
                    }
                    this.f1397a = this.l;
                    break;
                case 6:
                    int i12 = b;
                    int i13 = h;
                    if ((i12 & i13) == 0) {
                        str = "google";
                        b = i12 | i13;
                        z = true;
                    }
                    this.f1397a = this.l;
                    break;
                case 7:
                    int i14 = b;
                    int i15 = i;
                    if ((i14 & i15) == 0) {
                        b = i14 | i15;
                        str = GeocodeSearch.GPS;
                        z = true;
                    }
                    this.f1397a = d.a(this.j, this.l);
                    break;
            }
            if (z) {
                JSONObject jSONObject = new JSONObject();
                if (!TextUtils.isEmpty(str)) {
                    jSONObject.put("amap_loc_coordinate", str);
                }
                g.a(this.j, "O021", jSONObject);
            }
            dPoint = this.f1397a;
        }
        return dPoint;
    }

    /* renamed from: com.amap.api.location.CoordinateConverter$1, reason: invalid class name */
    static final /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f1398a;

        static {
            int[] iArr = new int[CoordType.values().length];
            f1398a = iArr;
            try {
                iArr[CoordType.BAIDU.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1398a[CoordType.MAPBAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1398a[CoordType.MAPABC.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1398a[CoordType.SOSOMAP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1398a[CoordType.ALIYUN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1398a[CoordType.GOOGLE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1398a[CoordType.GPS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public static boolean isAMapDataAvailable(double d2, double d3) {
        return b.a(d2, d3);
    }

    public static float calculateLineDistance(DPoint dPoint, DPoint dPoint2) {
        try {
            return i.a(dPoint, dPoint2);
        } catch (Throwable unused) {
            return 0.0f;
        }
    }
}
