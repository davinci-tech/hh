package com.huawei.haf.router.facade.model;

import java.util.Map;

/* loaded from: classes.dex */
public class RouteMeta {
    private final int mExtra;
    private final Map<String, Integer> mParamsType;
    private final int mPriority;
    private final Class<?> mRouteClass;
    private final RouteType mType;

    protected RouteMeta(RouteType routeType, Class<?> cls, int i, int i2, Map<String, Integer> map) {
        this.mType = routeType;
        this.mRouteClass = cls;
        this.mPriority = i;
        this.mExtra = i2;
        this.mParamsType = map;
    }

    public static RouteMeta build(RouteType routeType, Class<?> cls, int i, int i2, Map<String, Integer> map) {
        return new RouteMeta(routeType, cls, i, i2, map);
    }

    public Map<String, Integer> getParamsType() {
        return this.mParamsType;
    }

    public RouteType getType() {
        return this.mType;
    }

    public Class<?> getRouteClass() {
        return this.mRouteClass;
    }

    public int getPriority() {
        return this.mPriority;
    }

    public int getExtra() {
        return this.mExtra;
    }

    public String toString() {
        return "RouteMeta{type=" + this.mType + ", destClass=" + this.mRouteClass + ", priority=" + this.mPriority + ", extra=" + this.mExtra + ", paramsType=" + this.mParamsType + "}";
    }
}
