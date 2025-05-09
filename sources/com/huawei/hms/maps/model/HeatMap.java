package com.huawei.hms.maps.model;

import android.content.Context;
import android.os.RemoteException;
import com.huawei.hms.maps.model.HeatMapOptions;
import com.huawei.hms.maps.model.internal.IHeatMapDelegate;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class HeatMap {

    /* renamed from: a, reason: collision with root package name */
    private IHeatMapDelegate f4995a;

    public void setVisible(boolean z) {
        try {
            this.f4995a.setVisible(z);
        } catch (RemoteException e) {
            throw new com.huawei.hms.feature.dynamic.RuntimeRemoteException(e);
        }
    }

    public void setRadiusUnit(HeatMapOptions.RadiusUnit radiusUnit) {
        try {
            this.f4995a.setRadiusUnit(radiusUnit.equals(HeatMapOptions.RadiusUnit.METER) ? 2 : 1);
        } catch (RemoteException e) {
            throw new com.huawei.hms.feature.dynamic.RuntimeRemoteException(e);
        }
    }

    public void setRadius(Map<Float, Float> map) {
        if (map == null) {
            return;
        }
        try {
            this.f4995a.setRadiusMap(a(map));
        } catch (RemoteException e) {
            throw new com.huawei.hms.feature.dynamic.RuntimeRemoteException(e);
        }
    }

    public void setRadius(Float f) {
        try {
            this.f4995a.setRadius(String.valueOf(f));
        } catch (RemoteException e) {
            throw new com.huawei.hms.feature.dynamic.RuntimeRemoteException(e);
        }
    }

    public void setOpacity(Map<Float, Float> map) {
        if (map == null) {
            return;
        }
        try {
            this.f4995a.setOpacityMap(a(map));
        } catch (RemoteException e) {
            throw new com.huawei.hms.feature.dynamic.RuntimeRemoteException(e);
        }
    }

    public void setOpacity(Float f) {
        if (f.floatValue() < 0.0f || f.floatValue() > 1.0f) {
            throw new IllegalArgumentException("Opacity must be in the range [0..1]");
        }
        try {
            this.f4995a.setOpacity(String.valueOf(f));
        } catch (RemoteException e) {
            throw new com.huawei.hms.feature.dynamic.RuntimeRemoteException(e);
        }
    }

    public void setIntensity(Map<Float, Float> map) {
        if (map == null) {
            return;
        }
        try {
            this.f4995a.setIntensityMap(a(map));
        } catch (RemoteException e) {
            throw new com.huawei.hms.feature.dynamic.RuntimeRemoteException(e);
        }
    }

    public void setIntensity(Float f) {
        try {
            this.f4995a.setIntensity(String.valueOf(f));
        } catch (RemoteException e) {
            throw new com.huawei.hms.feature.dynamic.RuntimeRemoteException(e);
        }
    }

    public void setColor(Map<Float, Integer> map) {
        if (map == null) {
            return;
        }
        try {
            this.f4995a.setColor(b(map));
        } catch (RemoteException e) {
            throw new com.huawei.hms.feature.dynamic.RuntimeRemoteException(e);
        }
    }

    public void remove() {
        try {
            this.f4995a.remove();
        } catch (RemoteException e) {
            throw new com.huawei.hms.feature.dynamic.RuntimeRemoteException(e);
        }
    }

    public HeatMapOptions.RadiusUnit getRadiusUnit() {
        try {
            return this.f4995a.getRadiusUnit() == 2 ? HeatMapOptions.RadiusUnit.METER : HeatMapOptions.RadiusUnit.PIXEL;
        } catch (RemoteException e) {
            throw new com.huawei.hms.feature.dynamic.RuntimeRemoteException(e);
        }
    }

    public String getId() {
        try {
            return this.f4995a.getId();
        } catch (RemoteException e) {
            throw new com.huawei.hms.feature.dynamic.RuntimeRemoteException(e);
        }
    }

    public void changeDataSet(String str) {
        try {
            this.f4995a.changeDataSet(str);
        } catch (RemoteException e) {
            throw new com.huawei.hms.feature.dynamic.RuntimeRemoteException(e);
        }
    }

    public void changeDataSet(Context context, int i) {
        try {
            this.f4995a.changeDataSetFromRes(i);
        } catch (RemoteException e) {
            throw new com.huawei.hms.feature.dynamic.RuntimeRemoteException(e);
        }
    }

    private Map<String, String> b(Map<Float, Integer> map) {
        HashMap hashMap = new HashMap();
        for (Map.Entry<Float, Integer> entry : map.entrySet()) {
            hashMap.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
        }
        return hashMap;
    }

    private Map<String, String> a(Map<Float, Float> map) {
        HashMap hashMap = new HashMap();
        for (Map.Entry<Float, Float> entry : map.entrySet()) {
            hashMap.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
        }
        return hashMap;
    }

    public HeatMap(IHeatMapDelegate iHeatMapDelegate) {
        if (iHeatMapDelegate == null) {
            throw new NullPointerException("Object is null");
        }
        this.f4995a = iHeatMapDelegate;
    }
}
