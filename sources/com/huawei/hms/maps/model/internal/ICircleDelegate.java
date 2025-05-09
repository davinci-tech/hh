package com.huawei.hms.maps.model.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.feature.dynamic.IObjectWrapper;
import com.huawei.hms.maps.internal.IAnimationListener;
import com.huawei.hms.maps.model.LatLng;
import com.huawei.hms.maps.model.PatternItem;
import com.huawei.hms.maps.model.animation.Animation;
import java.util.List;

/* loaded from: classes9.dex */
public interface ICircleDelegate extends maa {
    boolean clearAnimation();

    boolean equalsRemote(ICircleDelegate iCircleDelegate);

    LatLng getCenter();

    int getFillColor();

    double getRadius();

    int getStrokeColor();

    List<PatternItem> getStrokePattern();

    float getStrokeWidth();

    void setAnimation(Animation animation);

    void setAnimationListener(IAnimationListener iAnimationListener);

    void setCenter(LatLng latLng);

    void setFillColor(int i);

    void setRadius(double d);

    void setStrokeColor(int i);

    void setStrokePattern(List<PatternItem> list);

    void setStrokeWidth(float f);

    void startAnimation();

    public static abstract class Stub extends Binder implements ICircleDelegate {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        static class Proxy implements ICircleDelegate {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f5019a;

            @Override // com.huawei.hms.maps.model.internal.ICircleDelegate
            public void startAnimation() {
                mab.a(this.f5019a, "com.huawei.hms.maps.model.internal.ICircleDelegate", 26, new Parcelable[0]);
            }

            @Override // com.huawei.hms.maps.model.internal.maa
            public void setZIndex(float f) {
                mab.a(this.f5019a, "com.huawei.hms.maps.model.internal.ICircleDelegate", 23, Float.valueOf(f));
            }

            @Override // com.huawei.hms.maps.model.internal.maa
            public void setVisible(boolean z) {
                mab.a(z, this.f5019a, "com.huawei.hms.maps.model.internal.ICircleDelegate", 22);
            }

            @Override // com.huawei.hms.maps.model.internal.maa
            public void setTag(IObjectWrapper iObjectWrapper) {
                mab.a(this.f5019a, "com.huawei.hms.maps.model.internal.ICircleDelegate", 21, iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
            }

            @Override // com.huawei.hms.maps.model.internal.ICircleDelegate
            public void setStrokeWidth(float f) {
                mab.a(this.f5019a, "com.huawei.hms.maps.model.internal.ICircleDelegate", 20, Float.valueOf(f));
            }

            @Override // com.huawei.hms.maps.model.internal.ICircleDelegate
            public void setStrokePattern(List<PatternItem> list) {
                mab.b(list, this.f5019a, "com.huawei.hms.maps.model.internal.ICircleDelegate", 19);
            }

            @Override // com.huawei.hms.maps.model.internal.ICircleDelegate
            public void setStrokeColor(int i) {
                mab.a(this.f5019a, "com.huawei.hms.maps.model.internal.ICircleDelegate", 18, Integer.valueOf(i));
            }

            @Override // com.huawei.hms.maps.model.internal.ICircleDelegate
            public void setRadius(double d) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.maps.model.internal.ICircleDelegate");
                    obtain.writeDouble(d);
                    this.f5019a.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.maps.model.internal.ICircleDelegate
            public void setFillColor(int i) {
                mab.a(this.f5019a, "com.huawei.hms.maps.model.internal.ICircleDelegate", 16, Integer.valueOf(i));
            }

            @Override // com.huawei.hms.maps.model.internal.maa
            public void setClickable(boolean z) {
                mab.a(z, this.f5019a, "com.huawei.hms.maps.model.internal.ICircleDelegate", 15);
            }

            @Override // com.huawei.hms.maps.model.internal.ICircleDelegate
            public void setCenter(LatLng latLng) {
                mab.a(this.f5019a, "com.huawei.hms.maps.model.internal.ICircleDelegate", 14, latLng);
            }

            @Override // com.huawei.hms.maps.model.internal.ICircleDelegate
            public void setAnimationListener(IAnimationListener iAnimationListener) {
                mab.a(iAnimationListener != null ? iAnimationListener.asBinder() : null, this.f5019a, "com.huawei.hms.maps.model.internal.ICircleDelegate", 27);
            }

            @Override // com.huawei.hms.maps.model.internal.ICircleDelegate
            public void setAnimation(Animation animation) {
                mab.a(this.f5019a, "com.huawei.hms.maps.model.internal.ICircleDelegate", 25, animation);
            }

            @Override // com.huawei.hms.maps.model.internal.maa
            public void remove() {
                mab.i(this.f5019a, "com.huawei.hms.maps.model.internal.ICircleDelegate", 13);
            }

            @Override // com.huawei.hms.maps.model.internal.maa
            public boolean isVisible() {
                return mab.a(this.f5019a, "com.huawei.hms.maps.model.internal.ICircleDelegate", 12);
            }

            @Override // com.huawei.hms.maps.model.internal.maa
            public boolean isClickable() {
                return mab.a(this.f5019a, "com.huawei.hms.maps.model.internal.ICircleDelegate", 11);
            }

            @Override // com.huawei.hms.maps.model.internal.maa
            public int hashCodeRemote() {
                return mab.b(this.f5019a, "com.huawei.hms.maps.model.internal.ICircleDelegate", 10);
            }

            @Override // com.huawei.hms.maps.model.internal.maa
            public float getZIndex() {
                return mab.c(this.f5019a, "com.huawei.hms.maps.model.internal.ICircleDelegate", 9);
            }

            @Override // com.huawei.hms.maps.model.internal.maa
            public IObjectWrapper getTag() {
                return IObjectWrapper.Stub.asInterface(mab.j(this.f5019a, "com.huawei.hms.maps.model.internal.ICircleDelegate", 8));
            }

            @Override // com.huawei.hms.maps.model.internal.ICircleDelegate
            public float getStrokeWidth() {
                return mab.c(this.f5019a, "com.huawei.hms.maps.model.internal.ICircleDelegate", 7);
            }

            @Override // com.huawei.hms.maps.model.internal.ICircleDelegate
            public List<PatternItem> getStrokePattern() {
                return mab.h(this.f5019a, "com.huawei.hms.maps.model.internal.ICircleDelegate", 6);
            }

            @Override // com.huawei.hms.maps.model.internal.ICircleDelegate
            public int getStrokeColor() {
                return mab.b(this.f5019a, "com.huawei.hms.maps.model.internal.ICircleDelegate", 5);
            }

            @Override // com.huawei.hms.maps.model.internal.ICircleDelegate
            public double getRadius() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.maps.model.internal.ICircleDelegate");
                    this.f5019a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readDouble();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return "com.huawei.hms.maps.model.internal.ICircleDelegate";
            }

            @Override // com.huawei.hms.maps.model.internal.maa
            public String getId() {
                return mab.d(this.f5019a, "com.huawei.hms.maps.model.internal.ICircleDelegate", 3);
            }

            @Override // com.huawei.hms.maps.model.internal.ICircleDelegate
            public int getFillColor() {
                return mab.b(this.f5019a, "com.huawei.hms.maps.model.internal.ICircleDelegate", 2);
            }

            @Override // com.huawei.hms.maps.model.internal.ICircleDelegate
            public LatLng getCenter() {
                return mab.e(this.f5019a, "com.huawei.hms.maps.model.internal.ICircleDelegate", 1);
            }

            @Override // com.huawei.hms.maps.model.internal.ICircleDelegate
            public boolean equalsRemote(ICircleDelegate iCircleDelegate) {
                return mab.a(iCircleDelegate, this.f5019a, "com.huawei.hms.maps.model.internal.ICircleDelegate", 24);
            }

            @Override // com.huawei.hms.maps.model.internal.ICircleDelegate
            public boolean clearAnimation() {
                return mab.a(this.f5019a, "com.huawei.hms.maps.model.internal.ICircleDelegate", 28);
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f5019a;
            }

            Proxy(IBinder iBinder) {
                this.f5019a = iBinder;
            }
        }

        public static ICircleDelegate asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.hms.maps.model.internal.ICircleDelegate");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ICircleDelegate)) ? new Proxy(iBinder) : (ICircleDelegate) queryLocalInterface;
        }

        public Stub() {
            attachInterface(this, "com.huawei.hms.maps.model.internal.ICircleDelegate");
        }
    }
}
