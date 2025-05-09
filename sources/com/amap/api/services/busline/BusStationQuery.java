package com.amap.api.services.busline;

import com.amap.api.col.p0003sl.fd;

/* loaded from: classes8.dex */
public class BusStationQuery implements Cloneable {

    /* renamed from: a, reason: collision with root package name */
    private String f1471a;
    private String b;
    private int c = 20;
    private int d = 1;

    public BusStationQuery(String str, String str2) {
        this.f1471a = str;
        this.b = str2;
        if (a()) {
            return;
        }
        new IllegalArgumentException("Empty query").printStackTrace();
    }

    private boolean a() {
        return !fd.a(this.f1471a);
    }

    public String getQueryString() {
        return this.f1471a;
    }

    public String getCity() {
        return this.b;
    }

    public int getPageSize() {
        return this.c;
    }

    public int getPageNumber() {
        return this.d;
    }

    public void setQueryString(String str) {
        this.f1471a = str;
    }

    public void setCity(String str) {
        this.b = str;
    }

    public void setPageSize(int i) {
        this.c = i;
    }

    public void setPageNumber(int i) {
        if (i <= 0) {
            i = 1;
        }
        this.d = i;
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public BusStationQuery m91clone() {
        BusStationQuery busStationQuery = new BusStationQuery(this.f1471a, this.b);
        busStationQuery.setPageNumber(this.d);
        busStationQuery.setPageSize(this.c);
        return busStationQuery;
    }

    public int hashCode() {
        String str = this.b;
        int hashCode = str == null ? 0 : str.hashCode();
        int i = this.d;
        int i2 = this.c;
        String str2 = this.f1471a;
        return ((((((hashCode + 31) * 31) + i) * 31) + i2) * 31) + (str2 != null ? str2.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BusStationQuery busStationQuery = (BusStationQuery) obj;
        String str = this.b;
        if (str == null) {
            if (busStationQuery.b != null) {
                return false;
            }
        } else if (!str.equals(busStationQuery.b)) {
            return false;
        }
        if (this.d != busStationQuery.d || this.c != busStationQuery.c) {
            return false;
        }
        String str2 = this.f1471a;
        if (str2 == null) {
            if (busStationQuery.f1471a != null) {
                return false;
            }
        } else if (!str2.equals(busStationQuery.f1471a)) {
            return false;
        }
        return true;
    }

    public boolean weakEquals(BusStationQuery busStationQuery) {
        if (this == busStationQuery) {
            return true;
        }
        if (busStationQuery == null) {
            return false;
        }
        String str = this.b;
        if (str == null) {
            if (busStationQuery.b != null) {
                return false;
            }
        } else if (!str.equals(busStationQuery.b)) {
            return false;
        }
        if (this.c != busStationQuery.c) {
            return false;
        }
        String str2 = this.f1471a;
        if (str2 == null) {
            if (busStationQuery.f1471a != null) {
                return false;
            }
        } else if (!str2.equals(busStationQuery.f1471a)) {
            return false;
        }
        return true;
    }
}
