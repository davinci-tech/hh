package com.huawei.hwcloudmodel.model.unite;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.operation.ble.BleConstants;
import com.huawei.route.Point;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class RouteData {

    @SerializedName("extendData")
    private String extendData;
    private transient int mRouteType;

    @SerializedName("routeDistance")
    private double routeDistance;

    @SerializedName("routeName")
    private String routeName;

    @SerializedName("routePoints")
    private List<Point> routePoints;

    @SerializedName("routeTime")
    private double routeTime;

    @SerializedName("routeVersion")
    private long routeVersion = 0;

    @SerializedName(BleConstants.SPORT_TYPE)
    private String sportType;

    @SerializedName("type")
    private int type;

    public String getRouteName() {
        return this.routeName;
    }

    public void setRouteName(String str) {
        this.routeName = str;
    }

    public String getSportType() {
        return this.sportType;
    }

    public void setSportType(String str) {
        this.sportType = str;
    }

    public double getRouteTime() {
        return this.routeTime;
    }

    public void setRouteTime(double d) {
        this.routeTime = d;
    }

    public double getRouteDistance() {
        return this.routeDistance;
    }

    public void setRouteDistance(double d) {
        this.routeDistance = d;
    }

    public long getRouteVersion() {
        return this.routeVersion;
    }

    public void setRouteVersion(long j) {
        this.routeVersion = j;
    }

    public List<Point> getRoutePoints() {
        if (CollectionUtils.d(this.routePoints)) {
            return new ArrayList();
        }
        return this.routePoints;
    }

    public void setRoutePoints(List<Point> list) {
        this.routePoints = list;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getExtendData() {
        return this.extendData;
    }

    public void setExtendData(String str) {
        this.extendData = str;
    }

    public void setRouteType(int i) {
        this.mRouteType = i;
    }

    public String getRouteId() {
        try {
            RouteExtendData routeExtendData = (RouteExtendData) new Gson().fromJson(this.extendData, RouteExtendData.class);
            return routeExtendData == null ? "" : routeExtendData.getRouteId();
        } catch (JsonSyntaxException unused) {
            return "";
        }
    }

    public int getRouteType() {
        try {
            RouteExtendData routeExtendData = (RouteExtendData) new Gson().fromJson(this.extendData, RouteExtendData.class);
            if (routeExtendData == null) {
                return this.mRouteType;
            }
            return routeExtendData.getRouteType();
        } catch (JsonSyntaxException unused) {
            return this.mRouteType;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof RouteData) && this.routeVersion == ((RouteData) obj).routeVersion;
    }

    public int hashCode() {
        long j = this.routeVersion;
        return (int) (j ^ (j >>> 32));
    }
}
