package com.huawei.hms.maps.model.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.huawei.hms.feature.dynamic.IObjectWrapper;
import com.huawei.hms.maps.model.Cap;
import com.huawei.hms.maps.model.LatLng;
import com.huawei.hms.maps.model.PatternItem;
import java.util.List;

/* loaded from: classes4.dex */
public interface IPolylineDelegate extends maa {
    boolean equalsRemote(IPolylineDelegate iPolylineDelegate);

    int getColor();

    List getColorValues();

    Cap getEndCap();

    int getJointType();

    List<PatternItem> getPattern();

    List<LatLng> getPoints();

    Cap getStartCap();

    float getWidth();

    boolean isGeodesic();

    boolean isGradient();

    void setColor(int i);

    void setColorValues(List list);

    void setEndCap(Cap cap);

    void setGeodesic(boolean z);

    void setGradient(boolean z);

    void setJointType(int i);

    void setPattern(List<PatternItem> list);

    void setPoints(List<LatLng> list);

    void setStartCap(Cap cap);

    void setWidth(float f);

    public static abstract class Stub extends Binder implements IPolylineDelegate {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        static class Proxy implements IPolylineDelegate {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f5027a;

            @Override // com.huawei.hms.maps.model.internal.maa
            public void setZIndex(float f) {
                mab.a(this.f5027a, "com.huawei.hms.maps.model.internal.IPolylineDelegate", 28, Float.valueOf(f));
            }

            @Override // com.huawei.hms.maps.model.internal.IPolylineDelegate
            public void setWidth(float f) {
                mab.a(this.f5027a, "com.huawei.hms.maps.model.internal.IPolylineDelegate", 27, Float.valueOf(f));
            }

            @Override // com.huawei.hms.maps.model.internal.maa
            public void setVisible(boolean z) {
                mab.a(z, this.f5027a, "com.huawei.hms.maps.model.internal.IPolylineDelegate", 26);
            }

            @Override // com.huawei.hms.maps.model.internal.maa
            public void setTag(IObjectWrapper iObjectWrapper) {
                mab.a(this.f5027a, "com.huawei.hms.maps.model.internal.IPolylineDelegate", 25, iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
            }

            @Override // com.huawei.hms.maps.model.internal.IPolylineDelegate
            public void setStartCap(Cap cap) {
                mab.a(this.f5027a, "com.huawei.hms.maps.model.internal.IPolylineDelegate", 24, cap);
            }

            @Override // com.huawei.hms.maps.model.internal.IPolylineDelegate
            public void setPoints(List<LatLng> list) {
                mab.a(list, this.f5027a, "com.huawei.hms.maps.model.internal.IPolylineDelegate", 23);
            }

            @Override // com.huawei.hms.maps.model.internal.IPolylineDelegate
            public void setPattern(List<PatternItem> list) {
                mab.b(list, this.f5027a, "com.huawei.hms.maps.model.internal.IPolylineDelegate", 22);
            }

            @Override // com.huawei.hms.maps.model.internal.IPolylineDelegate
            public void setJointType(int i) {
                mab.a(this.f5027a, "com.huawei.hms.maps.model.internal.IPolylineDelegate", 21, Integer.valueOf(i));
            }

            @Override // com.huawei.hms.maps.model.internal.IPolylineDelegate
            public void setGradient(boolean z) {
                mab.a(z, this.f5027a, "com.huawei.hms.maps.model.internal.IPolylineDelegate", 31);
            }

            @Override // com.huawei.hms.maps.model.internal.IPolylineDelegate
            public void setGeodesic(boolean z) {
                mab.a(z, this.f5027a, "com.huawei.hms.maps.model.internal.IPolylineDelegate", 20);
            }

            @Override // com.huawei.hms.maps.model.internal.IPolylineDelegate
            public void setEndCap(Cap cap) {
                mab.a(this.f5027a, "com.huawei.hms.maps.model.internal.IPolylineDelegate", 19, cap);
            }

            @Override // com.huawei.hms.maps.model.internal.IPolylineDelegate
            public void setColorValues(List list) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.maps.model.internal.IPolylineDelegate");
                    obtain.writeList(list);
                    this.f5027a.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.maps.model.internal.IPolylineDelegate
            public void setColor(int i) {
                mab.a(this.f5027a, "com.huawei.hms.maps.model.internal.IPolylineDelegate", 18, Integer.valueOf(i));
            }

            @Override // com.huawei.hms.maps.model.internal.maa
            public void setClickable(boolean z) {
                mab.a(z, this.f5027a, "com.huawei.hms.maps.model.internal.IPolylineDelegate", 17);
            }

            @Override // com.huawei.hms.maps.model.internal.maa
            public void remove() {
                mab.i(this.f5027a, "com.huawei.hms.maps.model.internal.IPolylineDelegate", 16);
            }

            @Override // com.huawei.hms.maps.model.internal.maa
            public boolean isVisible() {
                return mab.a(this.f5027a, "com.huawei.hms.maps.model.internal.IPolylineDelegate", 15);
            }

            @Override // com.huawei.hms.maps.model.internal.IPolylineDelegate
            public boolean isGradient() {
                return mab.a(this.f5027a, "com.huawei.hms.maps.model.internal.IPolylineDelegate", 32);
            }

            @Override // com.huawei.hms.maps.model.internal.IPolylineDelegate
            public boolean isGeodesic() {
                return mab.a(this.f5027a, "com.huawei.hms.maps.model.internal.IPolylineDelegate", 14);
            }

            @Override // com.huawei.hms.maps.model.internal.maa
            public boolean isClickable() {
                return mab.a(this.f5027a, "com.huawei.hms.maps.model.internal.IPolylineDelegate", 13);
            }

            @Override // com.huawei.hms.maps.model.internal.maa
            public int hashCodeRemote() {
                return mab.b(this.f5027a, "com.huawei.hms.maps.model.internal.IPolylineDelegate", 12);
            }

            @Override // com.huawei.hms.maps.model.internal.maa
            public float getZIndex() {
                return mab.c(this.f5027a, "com.huawei.hms.maps.model.internal.IPolylineDelegate", 11);
            }

            @Override // com.huawei.hms.maps.model.internal.IPolylineDelegate
            public float getWidth() {
                return mab.c(this.f5027a, "com.huawei.hms.maps.model.internal.IPolylineDelegate", 10);
            }

            @Override // com.huawei.hms.maps.model.internal.maa
            public IObjectWrapper getTag() {
                return IObjectWrapper.Stub.asInterface(mab.j(this.f5027a, "com.huawei.hms.maps.model.internal.IPolylineDelegate", 9));
            }

            @Override // com.huawei.hms.maps.model.internal.IPolylineDelegate
            public Cap getStartCap() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.maps.model.internal.IPolylineDelegate");
                    this.f5027a.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? Cap.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.maps.model.internal.IPolylineDelegate
            public List<LatLng> getPoints() {
                return mab.g(this.f5027a, "com.huawei.hms.maps.model.internal.IPolylineDelegate", 7);
            }

            @Override // com.huawei.hms.maps.model.internal.IPolylineDelegate
            public List<PatternItem> getPattern() {
                return mab.h(this.f5027a, "com.huawei.hms.maps.model.internal.IPolylineDelegate", 6);
            }

            @Override // com.huawei.hms.maps.model.internal.IPolylineDelegate
            public int getJointType() {
                return mab.b(this.f5027a, "com.huawei.hms.maps.model.internal.IPolylineDelegate", 5);
            }

            public String getInterfaceDescriptor() {
                return "com.huawei.hms.maps.model.internal.IPolylineDelegate";
            }

            @Override // com.huawei.hms.maps.model.internal.maa
            public String getId() {
                return mab.d(this.f5027a, "com.huawei.hms.maps.model.internal.IPolylineDelegate", 4);
            }

            @Override // com.huawei.hms.maps.model.internal.IPolylineDelegate
            public Cap getEndCap() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.maps.model.internal.IPolylineDelegate");
                    this.f5027a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? Cap.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.maps.model.internal.IPolylineDelegate
            public List getColorValues() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.maps.model.internal.IPolylineDelegate");
                    this.f5027a.transact(30, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readArrayList(getClass().getClassLoader());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.maps.model.internal.IPolylineDelegate
            public int getColor() {
                return mab.b(this.f5027a, "com.huawei.hms.maps.model.internal.IPolylineDelegate", 2);
            }

            @Override // com.huawei.hms.maps.model.internal.IPolylineDelegate
            public boolean equalsRemote(IPolylineDelegate iPolylineDelegate) {
                return mab.a(iPolylineDelegate, this.f5027a, "com.huawei.hms.maps.model.internal.IPolylineDelegate", 1);
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f5027a;
            }

            Proxy(IBinder iBinder) {
                this.f5027a = iBinder;
            }
        }

        public static IPolylineDelegate asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.hms.maps.model.internal.IPolylineDelegate");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IPolylineDelegate)) ? new Proxy(iBinder) : (IPolylineDelegate) queryLocalInterface;
        }

        public Stub() {
            attachInterface(this, "com.huawei.hms.maps.model.internal.IPolylineDelegate");
        }
    }
}
