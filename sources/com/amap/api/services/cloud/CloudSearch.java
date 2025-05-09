package com.amap.api.services.cloud;

import android.content.Context;
import com.amap.api.col.p0003sl.fd;
import com.amap.api.col.p0003sl.fv;
import com.amap.api.col.p0003sl.gs;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.interfaces.ICloudSearch;
import com.huawei.hihealth.HiDataFilter;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class CloudSearch {

    /* renamed from: a, reason: collision with root package name */
    private ICloudSearch f1477a;

    public interface OnCloudSearchListener {
        void onCloudItemDetailSearched(CloudItemDetail cloudItemDetail, int i);

        void onCloudSearched(CloudResult cloudResult, int i);
    }

    public CloudSearch(Context context) throws AMapException {
        if (this.f1477a == null) {
            try {
                this.f1477a = new gs(context);
            } catch (Exception e) {
                e.printStackTrace();
                if (e instanceof AMapException) {
                    throw ((AMapException) e);
                }
            }
        }
    }

    public void setOnCloudSearchListener(OnCloudSearchListener onCloudSearchListener) {
        ICloudSearch iCloudSearch = this.f1477a;
        if (iCloudSearch != null) {
            iCloudSearch.setOnCloudSearchListener(onCloudSearchListener);
        }
    }

    public void searchCloudAsyn(Query query) {
        ICloudSearch iCloudSearch = this.f1477a;
        if (iCloudSearch != null) {
            iCloudSearch.searchCloudAsyn(query);
        }
    }

    public void searchCloudDetailAsyn(String str, String str2) {
        ICloudSearch iCloudSearch = this.f1477a;
        if (iCloudSearch != null) {
            iCloudSearch.searchCloudDetailAsyn(str, str2);
        }
    }

    /* loaded from: classes8.dex */
    public static class Query implements Cloneable {

        /* renamed from: a, reason: collision with root package name */
        private String f1478a;
        private String d;
        private SearchBound e;
        private Sortingrules f;
        private int b = 1;
        private int c = 20;
        private ArrayList<fv> g = new ArrayList<>();
        private List<String> h = new ArrayList();

        public Query(String str, String str2, SearchBound searchBound) throws AMapException {
            if (fd.a(str) || searchBound == null) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            this.d = str;
            this.f1478a = str2;
            this.e = searchBound;
        }

        private Query() {
        }

        public String getQueryString() {
            return this.f1478a;
        }

        public void setTableID(String str) {
            this.d = str;
        }

        public String getTableID() {
            return this.d;
        }

        public int getPageNum() {
            return this.b;
        }

        public void setPageNum(int i) {
            this.b = i;
        }

        public void setPageSize(int i) {
            if (i <= 0) {
                this.c = 20;
            } else if (i > 100) {
                this.c = 100;
            } else {
                this.c = i;
            }
        }

        public int getPageSize() {
            return this.c;
        }

        public void setBound(SearchBound searchBound) {
            this.e = searchBound;
        }

        public SearchBound getBound() {
            return this.e;
        }

        public void addFilterString(String str, String str2) {
            if (str == null || str2 == null) {
                return;
            }
            this.h.add(str + str2);
        }

        public String getFilterString() {
            StringBuffer stringBuffer = new StringBuffer();
            try {
                int size = this.h.size();
                for (int i = 0; i < size; i++) {
                    stringBuffer.append(this.h.get(i));
                    if (i != size - 1) {
                        stringBuffer.append("&&");
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            return stringBuffer.toString();
        }

        public void addFilterNum(String str, String str2, String str3) {
            this.g.add(new fv(str, str2, str3));
        }

        private ArrayList<fv> a() {
            if (this.g == null) {
                return null;
            }
            ArrayList<fv> arrayList = new ArrayList<>();
            arrayList.addAll(this.g);
            return arrayList;
        }

        private ArrayList<String> b() {
            if (this.h == null) {
                return null;
            }
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.addAll(this.h);
            return arrayList;
        }

        public String getFilterNumString() {
            StringBuffer stringBuffer = new StringBuffer();
            try {
                int size = this.g.size();
                for (int i = 0; i < size; i++) {
                    fv fvVar = this.g.get(i);
                    stringBuffer.append(fvVar.a()).append(HiDataFilter.DataFilterExpression.BIGGER_EQUAL).append(fvVar.b()).append("&&").append(fvVar.a()).append(HiDataFilter.DataFilterExpression.LESS_EQUAL).append(fvVar.c());
                    if (i != size - 1) {
                        stringBuffer.append("&&");
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            return stringBuffer.toString();
        }

        public void setSortingrules(Sortingrules sortingrules) {
            this.f = sortingrules;
        }

        public Sortingrules getSortingrules() {
            return this.f;
        }

        private static boolean a(SearchBound searchBound, SearchBound searchBound2) {
            if (searchBound == null && searchBound2 == null) {
                return true;
            }
            if (searchBound == null || searchBound2 == null) {
                return false;
            }
            return searchBound.equals(searchBound2);
        }

        private static boolean a(Sortingrules sortingrules, Sortingrules sortingrules2) {
            if (sortingrules == null && sortingrules2 == null) {
                return true;
            }
            if (sortingrules == null || sortingrules2 == null) {
                return false;
            }
            return sortingrules.equals(sortingrules2);
        }

        public boolean queryEquals(Query query) {
            if (query == null) {
                return false;
            }
            if (query == this) {
                return true;
            }
            return CloudSearch.b(query.f1478a, this.f1478a) && CloudSearch.b(query.getTableID(), getTableID()) && CloudSearch.b(query.getFilterString(), getFilterString()) && CloudSearch.b(query.getFilterNumString(), getFilterNumString()) && query.c == this.c && a(query.getBound(), getBound()) && a(query.getSortingrules(), getSortingrules());
        }

        public int hashCode() {
            ArrayList<fv> arrayList = this.g;
            int hashCode = arrayList == null ? 0 : arrayList.hashCode();
            List<String> list = this.h;
            int hashCode2 = list == null ? 0 : list.hashCode();
            SearchBound searchBound = this.e;
            int hashCode3 = searchBound == null ? 0 : searchBound.hashCode();
            int i = this.b;
            int i2 = this.c;
            String str = this.f1478a;
            int hashCode4 = str == null ? 0 : str.hashCode();
            Sortingrules sortingrules = this.f;
            int hashCode5 = sortingrules == null ? 0 : sortingrules.hashCode();
            String str2 = this.d;
            return ((((((((((((((hashCode + 31) * 31) + hashCode2) * 31) + hashCode3) * 31) + i) * 31) + i2) * 31) + hashCode4) * 31) + hashCode5) * 31) + (str2 != null ? str2.hashCode() : 0);
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof Query)) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            Query query = (Query) obj;
            return queryEquals(query) && query.b == this.b;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:8:0x003b  */
        /* renamed from: clone, reason: merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.amap.api.services.cloud.CloudSearch.Query m92clone() {
            /*
                r4 = this;
                super.clone()     // Catch: java.lang.CloneNotSupportedException -> L4
                goto L8
            L4:
                r0 = move-exception
                r0.printStackTrace()
            L8:
                com.amap.api.services.cloud.CloudSearch$Query r0 = new com.amap.api.services.cloud.CloudSearch$Query     // Catch: com.amap.api.services.core.AMapException -> L33
                java.lang.String r1 = r4.d     // Catch: com.amap.api.services.core.AMapException -> L33
                java.lang.String r2 = r4.f1478a     // Catch: com.amap.api.services.core.AMapException -> L33
                com.amap.api.services.cloud.CloudSearch$SearchBound r3 = r4.e     // Catch: com.amap.api.services.core.AMapException -> L33
                r0.<init>(r1, r2, r3)     // Catch: com.amap.api.services.core.AMapException -> L33
                int r1 = r4.b     // Catch: com.amap.api.services.core.AMapException -> L31
                r0.setPageNum(r1)     // Catch: com.amap.api.services.core.AMapException -> L31
                int r1 = r4.c     // Catch: com.amap.api.services.core.AMapException -> L31
                r0.setPageSize(r1)     // Catch: com.amap.api.services.core.AMapException -> L31
                com.amap.api.services.cloud.CloudSearch$Sortingrules r1 = r4.getSortingrules()     // Catch: com.amap.api.services.core.AMapException -> L31
                r0.setSortingrules(r1)     // Catch: com.amap.api.services.core.AMapException -> L31
                java.util.ArrayList r1 = r4.a()     // Catch: com.amap.api.services.core.AMapException -> L31
                r0.g = r1     // Catch: com.amap.api.services.core.AMapException -> L31
                java.util.ArrayList r1 = r4.b()     // Catch: com.amap.api.services.core.AMapException -> L31
                r0.h = r1     // Catch: com.amap.api.services.core.AMapException -> L31
                goto L39
            L31:
                r1 = move-exception
                goto L36
            L33:
                r0 = move-exception
                r1 = r0
                r0 = 0
            L36:
                r1.printStackTrace()
            L39:
                if (r0 != 0) goto L40
                com.amap.api.services.cloud.CloudSearch$Query r0 = new com.amap.api.services.cloud.CloudSearch$Query
                r0.<init>()
            L40:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.cloud.CloudSearch.Query.m92clone():com.amap.api.services.cloud.CloudSearch$Query");
        }
    }

    /* loaded from: classes8.dex */
    public static class Sortingrules {
        public static final int DISTANCE = 1;
        public static final int WEIGHT = 0;

        /* renamed from: a, reason: collision with root package name */
        private int f1480a;
        private String b;
        private boolean c;

        public Sortingrules(String str, boolean z) {
            this.f1480a = 0;
            this.b = str;
            this.c = z;
        }

        public Sortingrules(int i) {
            this.c = true;
            this.f1480a = i;
        }

        public String toString() {
            if (fd.a(this.b)) {
                int i = this.f1480a;
                return i == 0 ? "_weight:desc" : i == 1 ? "_distance:asc" : "";
            }
            if (this.c) {
                return this.b + ":asc";
            }
            return this.b + ":desc";
        }

        public int hashCode() {
            int i = this.c ? 1231 : 1237;
            String str = this.b;
            return ((((i + 31) * 31) + (str == null ? 0 : str.hashCode())) * 31) + this.f1480a;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Sortingrules sortingrules = (Sortingrules) obj;
            if (this.c != sortingrules.c) {
                return false;
            }
            String str = this.b;
            if (str == null) {
                if (sortingrules.b != null) {
                    return false;
                }
            } else if (!str.equals(sortingrules.b)) {
                return false;
            }
            return this.f1480a == sortingrules.f1480a;
        }
    }

    /* loaded from: classes8.dex */
    public static class SearchBound implements Cloneable {
        public static final String BOUND_SHAPE = "Bound";
        public static final String LOCAL_SHAPE = "Local";
        public static final String POLYGON_SHAPE = "Polygon";
        public static final String RECTANGLE_SHAPE = "Rectangle";

        /* renamed from: a, reason: collision with root package name */
        private LatLonPoint f1479a;
        private LatLonPoint b;
        private int c;
        private LatLonPoint d;
        private String e;
        private List<LatLonPoint> f;
        private String g;

        public SearchBound(LatLonPoint latLonPoint, int i) {
            this.e = "Bound";
            this.c = i;
            this.d = latLonPoint;
        }

        public SearchBound(LatLonPoint latLonPoint, LatLonPoint latLonPoint2) {
            this.e = "Rectangle";
            if (a(latLonPoint, latLonPoint2)) {
                return;
            }
            new IllegalArgumentException("invalid rect ").printStackTrace();
        }

        public SearchBound(List<LatLonPoint> list) {
            this.e = "Polygon";
            this.f = list;
        }

        public SearchBound(String str) {
            this.e = LOCAL_SHAPE;
            this.g = str;
        }

        private boolean a(LatLonPoint latLonPoint, LatLonPoint latLonPoint2) {
            this.f1479a = latLonPoint;
            this.b = latLonPoint2;
            return latLonPoint != null && latLonPoint2 != null && latLonPoint.getLatitude() < this.b.getLatitude() && this.f1479a.getLongitude() < this.b.getLongitude();
        }

        public LatLonPoint getLowerLeft() {
            return this.f1479a;
        }

        public LatLonPoint getUpperRight() {
            return this.b;
        }

        public LatLonPoint getCenter() {
            return this.d;
        }

        public int getRange() {
            return this.c;
        }

        public String getShape() {
            return this.e;
        }

        public String getCity() {
            return this.g;
        }

        public List<LatLonPoint> getPolyGonList() {
            return this.f;
        }

        private static boolean a(List<LatLonPoint> list, List<LatLonPoint> list2) {
            if (list == null && list2 == null) {
                return true;
            }
            if (list == null || list2 == null || list.size() != list2.size()) {
                return false;
            }
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (!list.get(i).equals(list2.get(i))) {
                    return false;
                }
            }
            return true;
        }

        public int hashCode() {
            LatLonPoint latLonPoint = this.d;
            int hashCode = latLonPoint == null ? 0 : latLonPoint.hashCode();
            LatLonPoint latLonPoint2 = this.f1479a;
            int hashCode2 = latLonPoint2 == null ? 0 : latLonPoint2.hashCode();
            LatLonPoint latLonPoint3 = this.b;
            int hashCode3 = latLonPoint3 == null ? 0 : latLonPoint3.hashCode();
            List<LatLonPoint> list = this.f;
            int hashCode4 = list == null ? 0 : list.hashCode();
            int i = this.c;
            String str = this.e;
            int hashCode5 = str == null ? 0 : str.hashCode();
            String str2 = this.g;
            return ((((((((((((hashCode + 31) * 31) + hashCode2) * 31) + hashCode3) * 31) + hashCode4) * 31) + i) * 31) + hashCode5) * 31) + (str2 != null ? str2.hashCode() : 0);
        }

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof SearchBound)) {
                SearchBound searchBound = (SearchBound) obj;
                if (!getShape().equalsIgnoreCase(searchBound.getShape())) {
                    return false;
                }
                if (getShape().equals("Bound")) {
                    return searchBound.d.equals(this.d) && searchBound.c == this.c;
                }
                if (getShape().equals("Polygon")) {
                    return a(searchBound.f, this.f);
                }
                if (getShape().equals(LOCAL_SHAPE)) {
                    return searchBound.g.equals(this.g);
                }
                if (searchBound.f1479a.equals(this.f1479a) && searchBound.b.equals(this.b)) {
                    return true;
                }
            }
            return false;
        }

        private List<LatLonPoint> a() {
            if (this.f == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (LatLonPoint latLonPoint : this.f) {
                arrayList.add(new LatLonPoint(latLonPoint.getLatitude(), latLonPoint.getLongitude()));
            }
            return arrayList;
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] */
        public SearchBound m93clone() {
            try {
                super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            if (getShape().equals("Bound")) {
                return new SearchBound(this.d, this.c);
            }
            if (getShape().equals("Polygon")) {
                return new SearchBound(a());
            }
            if (getShape().equals(LOCAL_SHAPE)) {
                return new SearchBound(this.g);
            }
            return new SearchBound(this.f1479a, this.b);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(String str, String str2) {
        if (str == null && str2 == null) {
            return true;
        }
        if (str == null || str2 == null) {
            return false;
        }
        return str.equals(str2);
    }
}
