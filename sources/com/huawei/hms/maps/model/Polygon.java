package com.huawei.hms.maps.model;

import android.os.RemoteException;
import com.huawei.hms.feature.dynamic.ObjectWrapper;
import com.huawei.hms.maps.model.internal.IPolygonDelegate;
import com.huawei.hms.maps.utils.LogM;
import java.util.List;

/* loaded from: classes4.dex */
public final class Polygon {

    /* renamed from: a, reason: collision with root package name */
    private IPolygonDelegate f5006a;

    public void setZIndex(float f) {
        try {
            this.f5006a.setZIndex(f);
        } catch (RemoteException e) {
            LogM.e("Polygon", "setZIndex RemoteException: " + e.toString());
        }
    }

    public void setVisible(boolean z) {
        try {
            this.f5006a.setVisible(z);
        } catch (RemoteException e) {
            LogM.e("Polygon", "setVisible RemoteException: " + e.toString());
        }
    }

    public void setTag(Object obj) {
        try {
            this.f5006a.setTag(ObjectWrapper.wrap(obj));
        } catch (RemoteException e) {
            LogM.d("Polygon", "setTag RemoteException: " + e.toString());
        }
    }

    public void setStrokeWidth(float f) {
        try {
            this.f5006a.setStrokeWidth(f);
        } catch (RemoteException e) {
            LogM.d("Polygon", "setStrokeWidth RemoteException: " + e.toString());
        }
    }

    public void setStrokePattern(List<PatternItem> list) {
        try {
            this.f5006a.setStrokePattern(list);
        } catch (RemoteException e) {
            LogM.e("Polygon", "setStrokePattern RemoteException: " + e.toString());
        }
    }

    public void setStrokeJointType(int i) {
        try {
            this.f5006a.setStrokeJointType(i);
        } catch (RemoteException e) {
            LogM.e("Polygon", "setStrokeJointType RemoteException: " + e.toString());
        }
    }

    public void setStrokeColor(int i) {
        try {
            this.f5006a.setStrokeColor(i);
        } catch (RemoteException e) {
            LogM.d("Polygon", "setStrokeColor RemoteException: " + e.toString());
        }
    }

    public void setPoints(List<LatLng> list) {
        try {
            this.f5006a.setPoints(list);
        } catch (RemoteException e) {
            LogM.d("Polygon", "setPoints RemoteException: " + e.toString());
        }
    }

    public void setHoles(List<? extends List<LatLng>> list) {
        try {
            this.f5006a.setHoles(list);
        } catch (RemoteException e) {
            LogM.e("Polygon", "setHoles RemoteException: " + e.toString());
        }
    }

    public void setGeodesic(boolean z) {
        try {
            this.f5006a.setGeodesic(z);
        } catch (RemoteException e) {
            LogM.e("Polygon", "setGeodesic RemoteException: " + e.toString());
        }
    }

    public void setFillColor(int i) {
        try {
            this.f5006a.setFillColor(i);
        } catch (RemoteException e) {
            LogM.d("Polygon", "setFillColor RemoteException: " + e.toString());
        }
    }

    public void setClickable(boolean z) {
        try {
            this.f5006a.setClickable(z);
        } catch (RemoteException e) {
            LogM.d("Polygon", "setClickable RemoteException: " + e.toString());
        }
    }

    public void remove() {
        try {
            this.f5006a.remove();
        } catch (RemoteException e) {
            LogM.d("Polygon", "remove RemoteException: " + e.toString());
        }
    }

    public boolean isVisible() {
        try {
            return this.f5006a.isVisible();
        } catch (RemoteException e) {
            LogM.e("Polygon", "isVisible RemoteException: " + e.toString());
            return true;
        }
    }

    public boolean isGeodesic() {
        try {
            return this.f5006a.isGeodesic();
        } catch (RemoteException e) {
            LogM.e("Polygon", "isGeodesic RemoteException: " + e.toString());
            return true;
        }
    }

    public boolean isClickable() {
        try {
            return this.f5006a.isClickable();
        } catch (RemoteException e) {
            LogM.d("Polygon", "isClickable RemoteException: " + e.toString());
            return true;
        }
    }

    public int hashCode() {
        try {
            return this.f5006a.hashCodeRemote();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public float getZIndex() {
        try {
            return this.f5006a.getZIndex();
        } catch (RemoteException e) {
            LogM.e("Polygon", "getZIndex RemoteException: " + e.toString());
            return -1.0f;
        }
    }

    public Object getTag() {
        try {
            return ObjectWrapper.unwrap(this.f5006a.getTag());
        } catch (RemoteException e) {
            LogM.d("Polygon", "getTag RemoteException: " + e.toString());
            return null;
        }
    }

    public float getStrokeWidth() {
        try {
            return this.f5006a.getStrokeWidth();
        } catch (RemoteException e) {
            LogM.d("Polygon", "getStrokeWidth RemoteException: " + e.toString());
            return -1.0f;
        }
    }

    public List<PatternItem> getStrokePattern() {
        try {
            return this.f5006a.getStrokePattern();
        } catch (RemoteException e) {
            LogM.e("Polygon", "getStrokePattern RemoteException: " + e.toString());
            return null;
        }
    }

    public int getStrokeJointType() {
        try {
            return this.f5006a.getStrokeJointType();
        } catch (RemoteException e) {
            LogM.d("Polygon", "getStrokeJointType RemoteException: " + e.toString());
            return 0;
        }
    }

    public int getStrokeColor() {
        try {
            return this.f5006a.getStrokeColor();
        } catch (RemoteException e) {
            LogM.d("Polygon", "getStrokeColor RemoteException: " + e.toString());
            return 0;
        }
    }

    public List<LatLng> getPoints() {
        try {
            return this.f5006a.getPoints();
        } catch (RemoteException e) {
            LogM.d("Polygon", "getPoints RemoteException: " + e.toString());
            return null;
        }
    }

    public String getId() {
        try {
            return this.f5006a.getId();
        } catch (RemoteException e) {
            LogM.d("Polygon", "getId RemoteException: " + e.toString());
            return null;
        }
    }

    public List<List<LatLng>> getHoles() {
        try {
            return this.f5006a.getHoles();
        } catch (RemoteException e) {
            LogM.e("Polygon", "getHoles RemoteException: " + e.toString());
            return null;
        }
    }

    public int getFillColor() {
        try {
            return this.f5006a.getFillColor();
        } catch (RemoteException e) {
            LogM.d("Polygon", "getFillColor RemoteException: " + e.toString());
            return 0;
        }
    }

    public boolean equals(Object obj) {
        try {
            if (obj instanceof Polygon) {
                return this.f5006a.equalsRemote(((Polygon) obj).f5006a);
            }
        } catch (RemoteException e) {
            LogM.d("Polygon", "equals RemoteException: " + e.toString());
        }
        return false;
    }

    public Polygon(IPolygonDelegate iPolygonDelegate) {
        LogM.d("Polygon", "Polygon: ");
        this.f5006a = iPolygonDelegate;
    }
}
