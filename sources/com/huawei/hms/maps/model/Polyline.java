package com.huawei.hms.maps.model;

import android.os.RemoteException;
import com.huawei.hms.feature.dynamic.ObjectWrapper;
import com.huawei.hms.maps.model.internal.IPolylineDelegate;
import com.huawei.hms.maps.utils.LogM;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public final class Polyline {

    /* renamed from: a, reason: collision with root package name */
    private IPolylineDelegate f5008a;

    public void setZIndex(float f) {
        try {
            this.f5008a.setZIndex(f);
        } catch (RemoteException e) {
            LogM.e("Polyline", "setZIndex RemoteException: " + e.toString());
        }
    }

    public void setWidth(float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("Polyline width value is illegal ,this value must be non-negative");
        }
        try {
            this.f5008a.setWidth(f);
        } catch (RemoteException e) {
            LogM.e("Polyline", "setWidth RemoteException: " + e.toString());
        }
    }

    public void setVisible(boolean z) {
        try {
            this.f5008a.setVisible(z);
        } catch (RemoteException e) {
            LogM.e("Polyline", "setVisible RemoteException: " + e.toString());
        }
    }

    public void setTag(Object obj) {
        try {
            this.f5008a.setTag(ObjectWrapper.wrap(obj));
        } catch (RemoteException e) {
            LogM.e("Polyline", "setTag RemoteException: " + e.toString());
        }
    }

    public void setStartCap(Cap cap) {
        try {
            this.f5008a.setStartCap(cap);
        } catch (RemoteException e) {
            LogM.e("Polyline", "setStartCap RemoteException: " + e.toString());
        }
    }

    public void setPoints(List<LatLng> list) {
        try {
            this.f5008a.setPoints(list);
        } catch (RemoteException e) {
            LogM.e("Polyline", "setPoints RemoteException: " + e.toString());
        }
    }

    public void setPattern(List<PatternItem> list) {
        try {
            this.f5008a.setPattern(list);
        } catch (RemoteException e) {
            LogM.e("Polyline", "setPattern RemoteException: " + e.toString());
        }
    }

    public void setJointType(int i) {
        try {
            this.f5008a.setJointType(i);
        } catch (RemoteException e) {
            LogM.e("Polyline", "setJointType RemoteException: " + e.toString());
        }
    }

    public void setGradient(boolean z) {
        try {
            this.f5008a.setGradient(z);
        } catch (RemoteException e) {
            LogM.e("Polyline", "setGradient RemoteException: " + e.toString());
        }
    }

    public void setGeodesic(boolean z) {
        try {
            this.f5008a.setGeodesic(z);
        } catch (RemoteException e) {
            LogM.e("Polyline", "setGeodesic RemoteException: " + e.toString());
        }
    }

    public void setEndCap(Cap cap) {
        try {
            this.f5008a.setEndCap(cap);
        } catch (RemoteException e) {
            LogM.e("Polyline", "setEndCap RemoteException: " + e.toString());
        }
    }

    public void setColorValues(List<Integer> list) {
        try {
            this.f5008a.setColorValues(list);
        } catch (RemoteException e) {
            LogM.e("Polyline", "setColorValues RemoteException: " + e.toString());
        }
    }

    public void setColor(int i) {
        try {
            this.f5008a.setColor(i);
        } catch (RemoteException e) {
            LogM.e("Polyline", "setColor RemoteException: " + e.toString());
        }
    }

    public void setClickable(boolean z) {
        try {
            this.f5008a.setClickable(z);
        } catch (RemoteException e) {
            LogM.e("Polyline", "setClickable RemoteException: " + e.toString());
        }
    }

    public void remove() {
        try {
            this.f5008a.remove();
        } catch (RemoteException e) {
            LogM.e("Polyline", "remove RemoteException: " + e.toString());
        }
    }

    public boolean isVisible() {
        try {
            return this.f5008a.isVisible();
        } catch (RemoteException e) {
            LogM.e("Polyline", "isVisible RemoteException: " + e.toString());
            return true;
        }
    }

    public boolean isGradient() {
        try {
            return this.f5008a.isGradient();
        } catch (RemoteException e) {
            LogM.e("Polyline", "isGradient RemoteException: " + e.toString());
            return false;
        }
    }

    public boolean isGeodesic() {
        try {
            return this.f5008a.isGeodesic();
        } catch (RemoteException e) {
            LogM.e("Polyline", "isGeodesic RemoteException: " + e.toString());
            return true;
        }
    }

    public boolean isClickable() {
        try {
            return this.f5008a.isClickable();
        } catch (RemoteException e) {
            LogM.e("Polyline", "isClickable RemoteException: " + e.toString());
            return true;
        }
    }

    public int hashCode() {
        try {
            return this.f5008a.hashCodeRemote();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public float getZIndex() {
        try {
            return this.f5008a.getZIndex();
        } catch (RemoteException e) {
            LogM.e("Polyline", "getZIndex RemoteException: " + e.toString());
            return -1.0f;
        }
    }

    public float getWidth() {
        try {
            return this.f5008a.getWidth();
        } catch (RemoteException e) {
            LogM.e("Polyline", "getId RemoteException: " + e.toString());
            return 0.0f;
        }
    }

    public Object getTag() {
        try {
            return ObjectWrapper.unwrap(this.f5008a.getTag());
        } catch (RemoteException e) {
            LogM.e("Polyline", "getTag RemoteException: " + e.toString());
            return null;
        }
    }

    public Cap getStartCap() {
        try {
            return this.f5008a.getStartCap();
        } catch (RemoteException e) {
            LogM.e("Polyline", "getStartCap RemoteException: " + e.toString());
            return null;
        }
    }

    public List<LatLng> getPoints() {
        try {
            return this.f5008a.getPoints();
        } catch (RemoteException e) {
            LogM.e("Polyline", "getPoints RemoteException: " + e.toString());
            return null;
        }
    }

    public List<PatternItem> getPattern() {
        try {
            return this.f5008a.getPattern();
        } catch (RemoteException e) {
            LogM.e("Polyline", "getPattern RemoteException: " + e.toString());
            return null;
        }
    }

    public int getJointType() {
        try {
            return this.f5008a.getJointType();
        } catch (RemoteException e) {
            LogM.e("Polyline", "getJointType RemoteException: " + e.toString());
            return -1;
        }
    }

    public String getId() {
        try {
            return this.f5008a.getId();
        } catch (RemoteException e) {
            LogM.e("Polyline", "getId RemoteException: " + e.toString());
            return null;
        }
    }

    public Cap getEndCap() {
        try {
            return this.f5008a.getEndCap();
        } catch (RemoteException e) {
            LogM.e("Polyline", "getEndCap RemoteException: " + e.toString());
            return null;
        }
    }

    public List<Integer> getColorValues() {
        try {
            List colorValues = this.f5008a.getColorValues();
            ArrayList arrayList = new ArrayList();
            for (Object obj : colorValues) {
                if (obj instanceof Integer) {
                    arrayList.add((Integer) obj);
                }
            }
            return arrayList;
        } catch (RemoteException e) {
            LogM.e("Polyline", "getId RemoteException: " + e.toString());
            return new ArrayList(0);
        }
    }

    public int getColor() {
        try {
            return this.f5008a.getColor();
        } catch (RemoteException e) {
            LogM.e("Polyline", "getId RemoteException: " + e.toString());
            return 0;
        }
    }

    public boolean equals(Object obj) {
        try {
            if (obj instanceof Polyline) {
                return this.f5008a.equalsRemote(((Polyline) obj).f5008a);
            }
        } catch (RemoteException e) {
            LogM.e("Polyline", "equals RemoteException: " + e.toString());
        }
        return false;
    }

    public Polyline(IPolylineDelegate iPolylineDelegate) {
        this.f5008a = iPolylineDelegate;
    }
}
