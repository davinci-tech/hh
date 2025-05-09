package com.amap.api.services.nearby;

import android.content.Context;
import com.amap.api.col.p0003sl.fd;
import com.amap.api.col.p0003sl.gx;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.interfaces.INearbySearch;

/* loaded from: classes2.dex */
public class NearbySearch {
    public static final int AMAP = 1;
    public static final int GPS = 0;

    /* renamed from: a, reason: collision with root package name */
    private static NearbySearch f1507a;
    private INearbySearch b;

    public interface NearbyListener {
        void onNearbyInfoSearched(NearbySearchResult nearbySearchResult, int i);

        void onNearbyInfoUploaded(int i);

        void onUserInfoCleared(int i);
    }

    public static NearbySearch getInstance(Context context) throws AMapException {
        NearbySearch nearbySearch;
        synchronized (NearbySearch.class) {
            if (f1507a == null) {
                try {
                    f1507a = new NearbySearch(context);
                } catch (AMapException e) {
                    throw e;
                }
            }
            nearbySearch = f1507a;
        }
        return nearbySearch;
    }

    private NearbySearch(Context context) throws AMapException {
        if (this.b == null) {
            try {
                this.b = new gx(context);
            } catch (Exception e) {
                e.printStackTrace();
                if (e instanceof AMapException) {
                    throw ((AMapException) e);
                }
            }
        }
    }

    public void addNearbyListener(NearbyListener nearbyListener) {
        synchronized (this) {
            INearbySearch iNearbySearch = this.b;
            if (iNearbySearch != null) {
                iNearbySearch.addNearbyListener(nearbyListener);
            }
        }
    }

    public void removeNearbyListener(NearbyListener nearbyListener) {
        synchronized (this) {
            INearbySearch iNearbySearch = this.b;
            if (iNearbySearch != null) {
                iNearbySearch.removeNearbyListener(nearbyListener);
            }
        }
    }

    public void clearUserInfoAsyn() {
        INearbySearch iNearbySearch = this.b;
        if (iNearbySearch != null) {
            iNearbySearch.clearUserInfoAsyn();
        }
    }

    public void setUserID(String str) {
        INearbySearch iNearbySearch = this.b;
        if (iNearbySearch != null) {
            iNearbySearch.setUserID(str);
        }
    }

    public void startUploadNearbyInfoAuto(UploadInfoCallback uploadInfoCallback, int i) {
        synchronized (this) {
            INearbySearch iNearbySearch = this.b;
            if (iNearbySearch != null) {
                iNearbySearch.startUploadNearbyInfoAuto(uploadInfoCallback, i);
            }
        }
    }

    public void stopUploadNearbyInfoAuto() {
        synchronized (this) {
            INearbySearch iNearbySearch = this.b;
            if (iNearbySearch != null) {
                iNearbySearch.stopUploadNearbyInfoAuto();
            }
        }
    }

    public void uploadNearbyInfoAsyn(UploadInfo uploadInfo) {
        INearbySearch iNearbySearch = this.b;
        if (iNearbySearch != null) {
            iNearbySearch.uploadNearbyInfoAsyn(uploadInfo);
        }
    }

    public void searchNearbyInfoAsyn(NearbyQuery nearbyQuery) {
        INearbySearch iNearbySearch = this.b;
        if (iNearbySearch != null) {
            iNearbySearch.searchNearbyInfoAsyn(nearbyQuery);
        }
    }

    public NearbySearchResult searchNearbyInfo(NearbyQuery nearbyQuery) throws AMapException {
        INearbySearch iNearbySearch = this.b;
        if (iNearbySearch != null) {
            return iNearbySearch.searchNearbyInfo(nearbyQuery);
        }
        return null;
    }

    /* loaded from: classes8.dex */
    public static class NearbyQuery {

        /* renamed from: a, reason: collision with root package name */
        private LatLonPoint f1509a;
        private NearbySearchFunctionType b = NearbySearchFunctionType.DISTANCE_SEARCH;
        private int c = 1000;
        private int d = AMapException.CODE_AMAP_CLIENT_ERRORCODE_MISSSING;
        private int e = 1;

        public void setCenterPoint(LatLonPoint latLonPoint) {
            this.f1509a = latLonPoint;
        }

        public LatLonPoint getCenterPoint() {
            return this.f1509a;
        }

        public int getRadius() {
            return this.c;
        }

        public void setRadius(int i) {
            if (i > 10000) {
                i = 10000;
            }
            this.c = i;
        }

        public void setType(NearbySearchFunctionType nearbySearchFunctionType) {
            this.b = nearbySearchFunctionType;
        }

        public int getType() {
            int i = AnonymousClass1.f1508a[this.b.ordinal()];
            return (i == 1 || i != 2) ? 0 : 1;
        }

        public void setCoordType(int i) {
            if (i != 0 && i != 1) {
                this.e = 1;
            } else {
                this.e = i;
            }
        }

        public int getCoordType() {
            return this.e;
        }

        /* JADX WARN: Code restructure failed: missing block: B:8:0x0008, code lost:
        
            if (r2 > 86400) goto L4;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void setTimeRange(int r2) {
            /*
                r1 = this;
                r0 = 5
                if (r2 >= r0) goto L5
            L3:
                r2 = r0
                goto Lb
            L5:
                r0 = 86400(0x15180, float:1.21072E-40)
                if (r2 <= r0) goto Lb
                goto L3
            Lb:
                r1.d = r2
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.nearby.NearbySearch.NearbyQuery.setTimeRange(int):void");
        }

        public int getTimeRange() {
            return this.d;
        }
    }

    /* renamed from: com.amap.api.services.nearby.NearbySearch$1, reason: invalid class name */
    static final /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f1508a;

        static {
            int[] iArr = new int[NearbySearchFunctionType.values().length];
            f1508a = iArr;
            try {
                iArr[NearbySearchFunctionType.DISTANCE_SEARCH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1508a[NearbySearchFunctionType.DRIVING_DISTANCE_SEARCH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static void destroy() {
        synchronized (NearbySearch.class) {
            NearbySearch nearbySearch = f1507a;
            if (nearbySearch != null) {
                try {
                    nearbySearch.a();
                } catch (Throwable th) {
                    fd.a(th, "NearbySearch", "destryoy");
                }
            }
            f1507a = null;
        }
    }

    private void a() {
        INearbySearch iNearbySearch = this.b;
        if (iNearbySearch != null) {
            iNearbySearch.destroy();
        }
        this.b = null;
    }
}
