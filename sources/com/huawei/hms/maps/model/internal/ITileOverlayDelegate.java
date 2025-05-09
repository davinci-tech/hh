package com.huawei.hms.maps.model.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* loaded from: classes9.dex */
public interface ITileOverlayDelegate extends IInterface {
    void clearTileCache();

    boolean equalsRemote(ITileOverlayDelegate iTileOverlayDelegate);

    boolean getFadeIn();

    String getId();

    float getTransparency();

    float getZIndex();

    int hashCodeRemote();

    boolean isVisible();

    void remove();

    void setFadeIn(boolean z);

    void setTransparency(float f);

    void setVisible(boolean z);

    void setZIndex(float f);

    public static abstract class Stub extends Binder implements ITileOverlayDelegate {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        static class Proxy implements ITileOverlayDelegate {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f5028a;

            @Override // com.huawei.hms.maps.model.internal.ITileOverlayDelegate
            public void setZIndex(float f) {
                mab.a(this.f5028a, "com.huawei.hms.maps.model.internal.ITileOverlayDelegate", 11, Float.valueOf(f));
            }

            @Override // com.huawei.hms.maps.model.internal.ITileOverlayDelegate
            public void setVisible(boolean z) {
                mab.a(z, this.f5028a, "com.huawei.hms.maps.model.internal.ITileOverlayDelegate", 10);
            }

            @Override // com.huawei.hms.maps.model.internal.ITileOverlayDelegate
            public void setTransparency(float f) {
                mab.a(this.f5028a, "com.huawei.hms.maps.model.internal.ITileOverlayDelegate", 9, Float.valueOf(f));
            }

            @Override // com.huawei.hms.maps.model.internal.ITileOverlayDelegate
            public void setFadeIn(boolean z) {
                mab.a(z, this.f5028a, "com.huawei.hms.maps.model.internal.ITileOverlayDelegate", 8);
            }

            @Override // com.huawei.hms.maps.model.internal.ITileOverlayDelegate
            public void remove() {
                mab.i(this.f5028a, "com.huawei.hms.maps.model.internal.ITileOverlayDelegate", 7);
            }

            @Override // com.huawei.hms.maps.model.internal.ITileOverlayDelegate
            public boolean isVisible() {
                return mab.a(this.f5028a, "com.huawei.hms.maps.model.internal.ITileOverlayDelegate", 6);
            }

            @Override // com.huawei.hms.maps.model.internal.ITileOverlayDelegate
            public int hashCodeRemote() {
                return mab.b(this.f5028a, "com.huawei.hms.maps.model.internal.ITileOverlayDelegate", 13);
            }

            @Override // com.huawei.hms.maps.model.internal.ITileOverlayDelegate
            public float getZIndex() {
                return mab.c(this.f5028a, "com.huawei.hms.maps.model.internal.ITileOverlayDelegate", 5);
            }

            @Override // com.huawei.hms.maps.model.internal.ITileOverlayDelegate
            public float getTransparency() {
                return mab.c(this.f5028a, "com.huawei.hms.maps.model.internal.ITileOverlayDelegate", 4);
            }

            public String getInterfaceDescriptor() {
                return "com.huawei.hms.maps.model.internal.ITileOverlayDelegate";
            }

            @Override // com.huawei.hms.maps.model.internal.ITileOverlayDelegate
            public String getId() {
                return mab.d(this.f5028a, "com.huawei.hms.maps.model.internal.ITileOverlayDelegate", 3);
            }

            @Override // com.huawei.hms.maps.model.internal.ITileOverlayDelegate
            public boolean getFadeIn() {
                return mab.a(this.f5028a, "com.huawei.hms.maps.model.internal.ITileOverlayDelegate", 2);
            }

            @Override // com.huawei.hms.maps.model.internal.ITileOverlayDelegate
            public boolean equalsRemote(ITileOverlayDelegate iTileOverlayDelegate) {
                return mab.a(iTileOverlayDelegate, this.f5028a, "com.huawei.hms.maps.model.internal.ITileOverlayDelegate", 12);
            }

            @Override // com.huawei.hms.maps.model.internal.ITileOverlayDelegate
            public void clearTileCache() {
                mab.i(this.f5028a, "com.huawei.hms.maps.model.internal.ITileOverlayDelegate", 1);
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f5028a;
            }

            Proxy(IBinder iBinder) {
                this.f5028a = iBinder;
            }
        }

        public static ITileOverlayDelegate asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.hms.maps.model.internal.ITileOverlayDelegate");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ITileOverlayDelegate)) ? new Proxy(iBinder) : (ITileOverlayDelegate) queryLocalInterface;
        }

        public Stub() {
            attachInterface(this, "com.huawei.hms.maps.model.internal.ITileOverlayDelegate");
        }
    }
}
