package com.amap.api.location;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.app.NotificationCompat;
import com.autonavi.aps.amapapi.utils.b;
import com.huawei.operation.OpAnalyticsConstants;

/* loaded from: classes2.dex */
public class AMapLocationClientOption implements Parcelable, Cloneable {
    private static int d = 0;
    private static int e = 1;
    private static int f = 2;
    private static int g = 4;
    private boolean A;
    private int B;
    private int C;
    private float D;
    private AMapLocationPurpose E;
    boolean b;
    String c;
    private long h;
    private long i;
    private boolean j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private AMapLocationMode o;
    private boolean q;
    private boolean r;
    private boolean s;
    private boolean t;
    private boolean u;
    private boolean v;
    private boolean w;
    private long x;
    private long y;
    private GeoLanguage z;
    private static AMapLocationProtocol p = AMapLocationProtocol.HTTP;

    /* renamed from: a, reason: collision with root package name */
    static String f1388a = "";
    public static final Parcelable.Creator<AMapLocationClientOption> CREATOR = new Parcelable.Creator<AMapLocationClientOption>() { // from class: com.amap.api.location.AMapLocationClientOption.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ AMapLocationClientOption createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ AMapLocationClientOption[] newArray(int i) {
            return a(i);
        }

        private static AMapLocationClientOption a(Parcel parcel) {
            return new AMapLocationClientOption(parcel);
        }

        private static AMapLocationClientOption[] a(int i) {
            return new AMapLocationClientOption[i];
        }
    };
    public static boolean OPEN_ALWAYS_SCAN_WIFI = true;
    public static long SCAN_WIFI_INTERVAL = OpAnalyticsConstants.H5_LOADING_DELAY;

    public enum AMapLocationMode {
        Battery_Saving,
        Device_Sensors,
        Hight_Accuracy
    }

    public enum AMapLocationPurpose {
        SignIn,
        Transport,
        Sport
    }

    public enum GeoLanguage {
        DEFAULT,
        ZH,
        EN
    }

    public static boolean isDownloadCoordinateConvertLibrary() {
        return false;
    }

    public static void setDownloadCoordinateConvertLibrary(boolean z) {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void setCacheCallBack(boolean z) {
        this.A = z;
    }

    public boolean getCacheCallBack() {
        return this.A;
    }

    public void setCacheCallBackTime(int i) {
        this.B = i;
    }

    public int getCacheCallBackTime() {
        return this.B;
    }

    public void setCacheTimeOut(int i) {
        this.C = i;
    }

    public int getCacheTimeOut() {
        return this.C;
    }

    public enum AMapLocationProtocol {
        HTTP(0),
        HTTPS(1);


        /* renamed from: a, reason: collision with root package name */
        private int f1391a;

        AMapLocationProtocol(int i) {
            this.f1391a = i;
        }

        public final int getValue() {
            return this.f1391a;
        }
    }

    public static String getAPIKEY() {
        return f1388a;
    }

    public boolean isMockEnable() {
        return this.k;
    }

    public AMapLocationClientOption setMockEnable(boolean z) {
        this.k = z;
        return this;
    }

    public long getInterval() {
        return this.h;
    }

    public AMapLocationClientOption setInterval(long j) {
        if (j <= 800) {
            j = 800;
        }
        this.h = j;
        return this;
    }

    public boolean isOnceLocation() {
        return this.j;
    }

    public AMapLocationClientOption setOnceLocation(boolean z) {
        this.j = z;
        return this;
    }

    public boolean isNeedAddress() {
        return this.l;
    }

    public AMapLocationClientOption setNeedAddress(boolean z) {
        this.l = z;
        return this;
    }

    public boolean isWifiActiveScan() {
        return this.m;
    }

    public AMapLocationClientOption setWifiActiveScan(boolean z) {
        this.m = z;
        this.n = z;
        return this;
    }

    public boolean isWifiScan() {
        return this.w;
    }

    public AMapLocationClientOption setWifiScan(boolean z) {
        this.w = z;
        if (z) {
            this.m = this.n;
        } else {
            this.m = false;
        }
        return this;
    }

    public AMapLocationMode getLocationMode() {
        return this.o;
    }

    public AMapLocationClientOption setLocationMode(AMapLocationMode aMapLocationMode) {
        this.o = aMapLocationMode;
        return this;
    }

    public AMapLocationProtocol getLocationProtocol() {
        return p;
    }

    public static void setLocationProtocol(AMapLocationProtocol aMapLocationProtocol) {
        p = aMapLocationProtocol;
    }

    public boolean isKillProcess() {
        return this.q;
    }

    public AMapLocationClientOption setKillProcess(boolean z) {
        this.q = z;
        return this;
    }

    public boolean isGpsFirst() {
        return this.r;
    }

    public AMapLocationClientOption setGpsFirst(boolean z) {
        this.r = z;
        return this;
    }

    public AMapLocationClientOption setGpsFirstTimeout(long j) {
        if (j < 5000) {
            j = 5000;
        }
        if (j > OpAnalyticsConstants.H5_LOADING_DELAY) {
            j = 30000;
        }
        this.y = j;
        return this;
    }

    public long getGpsFirstTimeout() {
        return this.y;
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public AMapLocationClientOption m78clone() {
        try {
            super.clone();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return new AMapLocationClientOption().a(this);
    }

    public long getHttpTimeOut() {
        return this.i;
    }

    public AMapLocationClientOption setHttpTimeOut(long j) {
        this.i = j;
        return this;
    }

    public boolean isOffset() {
        return this.s;
    }

    public AMapLocationClientOption setOffset(boolean z) {
        this.s = z;
        return this;
    }

    public boolean isLocationCacheEnable() {
        return this.t;
    }

    public AMapLocationClientOption setLocationCacheEnable(boolean z) {
        this.t = z;
        return this;
    }

    public boolean isOnceLocationLatest() {
        return this.u;
    }

    public AMapLocationClientOption setOnceLocationLatest(boolean z) {
        this.u = z;
        return this;
    }

    public boolean isSensorEnable() {
        return this.v;
    }

    public AMapLocationClientOption setSensorEnable(boolean z) {
        this.v = z;
        return this;
    }

    public AMapLocationClientOption setLastLocationLifeCycle(long j) {
        this.x = j;
        return this;
    }

    public long getLastLocationLifeCycle() {
        return this.x;
    }

    public GeoLanguage getGeoLanguage() {
        return this.z;
    }

    public AMapLocationClientOption setGeoLanguage(GeoLanguage geoLanguage) {
        this.z = geoLanguage;
        return this;
    }

    public float getDeviceModeDistanceFilter() {
        return this.D;
    }

    public AMapLocationClientOption setDeviceModeDistanceFilter(float f2) {
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        this.D = f2;
        return this;
    }

    public AMapLocationClientOption setLocationPurpose(AMapLocationPurpose aMapLocationPurpose) {
        this.E = aMapLocationPurpose;
        if (aMapLocationPurpose != null) {
            int i = AnonymousClass2.f1389a[aMapLocationPurpose.ordinal()];
            if (i == 1) {
                this.o = AMapLocationMode.Hight_Accuracy;
                this.j = true;
                this.u = true;
                this.r = false;
                this.k = false;
                this.w = true;
                int i2 = d;
                int i3 = e;
                if ((i2 & i3) == 0) {
                    this.b = true;
                    d = i2 | i3;
                    this.c = "signin";
                }
            } else if (i == 2) {
                int i4 = d;
                int i5 = f;
                if ((i4 & i5) == 0) {
                    this.b = true;
                    d = i4 | i5;
                    this.c = NotificationCompat.CATEGORY_TRANSPORT;
                }
                this.o = AMapLocationMode.Hight_Accuracy;
                this.j = false;
                this.u = false;
                this.r = true;
                this.k = false;
                this.w = true;
            } else if (i == 3) {
                int i6 = d;
                int i7 = g;
                if ((i6 & i7) == 0) {
                    this.b = true;
                    d = i6 | i7;
                    this.c = "sport";
                }
                this.o = AMapLocationMode.Hight_Accuracy;
                this.j = false;
                this.u = false;
                this.r = true;
                this.k = false;
                this.w = true;
            }
        }
        return this;
    }

    /* renamed from: com.amap.api.location.AMapLocationClientOption$2, reason: invalid class name */
    static final /* synthetic */ class AnonymousClass2 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f1389a;

        static {
            int[] iArr = new int[AMapLocationPurpose.values().length];
            f1389a = iArr;
            try {
                iArr[AMapLocationPurpose.SignIn.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1389a[AMapLocationPurpose.Transport.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1389a[AMapLocationPurpose.Sport.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public AMapLocationPurpose getLocationPurpose() {
        return this.E;
    }

    private AMapLocationClientOption a(AMapLocationClientOption aMapLocationClientOption) {
        this.h = aMapLocationClientOption.h;
        this.j = aMapLocationClientOption.j;
        this.o = aMapLocationClientOption.o;
        this.k = aMapLocationClientOption.k;
        this.q = aMapLocationClientOption.q;
        this.r = aMapLocationClientOption.r;
        this.l = aMapLocationClientOption.l;
        this.m = aMapLocationClientOption.m;
        this.i = aMapLocationClientOption.i;
        this.s = aMapLocationClientOption.s;
        this.t = aMapLocationClientOption.t;
        this.u = aMapLocationClientOption.u;
        this.v = aMapLocationClientOption.isSensorEnable();
        this.w = aMapLocationClientOption.isWifiScan();
        this.x = aMapLocationClientOption.x;
        setLocationProtocol(aMapLocationClientOption.getLocationProtocol());
        this.z = aMapLocationClientOption.z;
        setDownloadCoordinateConvertLibrary(isDownloadCoordinateConvertLibrary());
        this.D = aMapLocationClientOption.D;
        this.E = aMapLocationClientOption.E;
        setOpenAlwaysScanWifi(isOpenAlwaysScanWifi());
        setScanWifiInterval(aMapLocationClientOption.getScanWifiInterval());
        this.y = aMapLocationClientOption.y;
        this.C = aMapLocationClientOption.getCacheTimeOut();
        this.A = aMapLocationClientOption.getCacheCallBack();
        this.B = aMapLocationClientOption.getCacheCallBackTime();
        return this;
    }

    public String toString() {
        return "interval:" + String.valueOf(this.h) + "#isOnceLocation:" + String.valueOf(this.j) + "#locationMode:" + String.valueOf(this.o) + "#locationProtocol:" + String.valueOf(p) + "#isMockEnable:" + String.valueOf(this.k) + "#isKillProcess:" + String.valueOf(this.q) + "#isGpsFirst:" + String.valueOf(this.r) + "#isNeedAddress:" + String.valueOf(this.l) + "#isWifiActiveScan:" + String.valueOf(this.m) + "#wifiScan:" + String.valueOf(this.w) + "#httpTimeOut:" + String.valueOf(this.i) + "#isLocationCacheEnable:" + String.valueOf(this.t) + "#isOnceLocationLatest:" + String.valueOf(this.u) + "#sensorEnable:" + String.valueOf(this.v) + "#geoLanguage:" + String.valueOf(this.z) + "#locationPurpose:" + String.valueOf(this.E) + "#callback:" + String.valueOf(this.A) + "#time:" + String.valueOf(this.B) + "#";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.h);
        parcel.writeLong(this.i);
        parcel.writeByte(this.j ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.k ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.l ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.m ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.n ? (byte) 1 : (byte) 0);
        AMapLocationMode aMapLocationMode = this.o;
        parcel.writeInt(aMapLocationMode == null ? -1 : aMapLocationMode.ordinal());
        parcel.writeByte(this.q ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.r ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.s ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.t ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.u ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.v ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.w ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.x);
        parcel.writeInt(p == null ? -1 : getLocationProtocol().ordinal());
        GeoLanguage geoLanguage = this.z;
        parcel.writeInt(geoLanguage == null ? -1 : geoLanguage.ordinal());
        parcel.writeFloat(this.D);
        AMapLocationPurpose aMapLocationPurpose = this.E;
        parcel.writeInt(aMapLocationPurpose != null ? aMapLocationPurpose.ordinal() : -1);
        parcel.writeInt(OPEN_ALWAYS_SCAN_WIFI ? 1 : 0);
        parcel.writeLong(this.y);
    }

    public AMapLocationClientOption() {
        this.h = 2000L;
        this.i = b.i;
        this.j = false;
        this.k = true;
        this.l = true;
        this.m = true;
        this.n = true;
        this.o = AMapLocationMode.Hight_Accuracy;
        this.q = false;
        this.r = false;
        this.s = true;
        this.t = true;
        this.u = false;
        this.v = false;
        this.w = true;
        this.x = OpAnalyticsConstants.H5_LOADING_DELAY;
        this.y = OpAnalyticsConstants.H5_LOADING_DELAY;
        this.z = GeoLanguage.DEFAULT;
        this.A = false;
        this.B = 1500;
        this.C = 21600000;
        this.D = 0.0f;
        this.E = null;
        this.b = false;
        this.c = null;
    }

    protected AMapLocationClientOption(Parcel parcel) {
        this.h = 2000L;
        this.i = b.i;
        this.j = false;
        this.k = true;
        this.l = true;
        this.m = true;
        this.n = true;
        this.o = AMapLocationMode.Hight_Accuracy;
        this.q = false;
        this.r = false;
        this.s = true;
        this.t = true;
        this.u = false;
        this.v = false;
        this.w = true;
        this.x = OpAnalyticsConstants.H5_LOADING_DELAY;
        this.y = OpAnalyticsConstants.H5_LOADING_DELAY;
        this.z = GeoLanguage.DEFAULT;
        this.A = false;
        this.B = 1500;
        this.C = 21600000;
        this.D = 0.0f;
        this.E = null;
        this.b = false;
        this.c = null;
        this.h = parcel.readLong();
        this.i = parcel.readLong();
        this.j = parcel.readByte() != 0;
        this.k = parcel.readByte() != 0;
        this.l = parcel.readByte() != 0;
        this.m = parcel.readByte() != 0;
        this.n = parcel.readByte() != 0;
        int readInt = parcel.readInt();
        this.o = readInt == -1 ? AMapLocationMode.Hight_Accuracy : AMapLocationMode.values()[readInt];
        this.q = parcel.readByte() != 0;
        this.r = parcel.readByte() != 0;
        this.s = parcel.readByte() != 0;
        this.t = parcel.readByte() != 0;
        this.u = parcel.readByte() != 0;
        this.v = parcel.readByte() != 0;
        this.w = parcel.readByte() != 0;
        this.x = parcel.readLong();
        int readInt2 = parcel.readInt();
        p = readInt2 == -1 ? AMapLocationProtocol.HTTP : AMapLocationProtocol.values()[readInt2];
        int readInt3 = parcel.readInt();
        this.z = readInt3 == -1 ? GeoLanguage.DEFAULT : GeoLanguage.values()[readInt3];
        this.D = parcel.readFloat();
        int readInt4 = parcel.readInt();
        this.E = readInt4 != -1 ? AMapLocationPurpose.values()[readInt4] : null;
        OPEN_ALWAYS_SCAN_WIFI = parcel.readByte() != 0;
        this.y = parcel.readLong();
    }

    public static boolean isOpenAlwaysScanWifi() {
        return OPEN_ALWAYS_SCAN_WIFI;
    }

    public static void setOpenAlwaysScanWifi(boolean z) {
        OPEN_ALWAYS_SCAN_WIFI = z;
    }

    public static void setScanWifiInterval(long j) {
        SCAN_WIFI_INTERVAL = j;
    }

    public long getScanWifiInterval() {
        return SCAN_WIFI_INTERVAL;
    }
}
