package com.huawei.hms.maps.model.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import java.util.Map;

/* loaded from: classes9.dex */
public interface IHeatMapDelegate extends IInterface {

    public static class Default implements IHeatMapDelegate {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.hms.maps.model.internal.IHeatMapDelegate
        public void changeDataSet(String str) {
        }

        @Override // com.huawei.hms.maps.model.internal.IHeatMapDelegate
        public void changeDataSetFromRes(int i) {
        }

        @Override // com.huawei.hms.maps.model.internal.IHeatMapDelegate
        public String getId() {
            return null;
        }

        @Override // com.huawei.hms.maps.model.internal.IHeatMapDelegate
        public int getRadiusUnit() {
            return 0;
        }

        @Override // com.huawei.hms.maps.model.internal.IHeatMapDelegate
        public void remove() {
        }

        @Override // com.huawei.hms.maps.model.internal.IHeatMapDelegate
        public void setColor(Map map) {
        }

        @Override // com.huawei.hms.maps.model.internal.IHeatMapDelegate
        public void setIntensity(String str) {
        }

        @Override // com.huawei.hms.maps.model.internal.IHeatMapDelegate
        public void setIntensityMap(Map map) {
        }

        @Override // com.huawei.hms.maps.model.internal.IHeatMapDelegate
        public void setOpacity(String str) {
        }

        @Override // com.huawei.hms.maps.model.internal.IHeatMapDelegate
        public void setOpacityMap(Map map) {
        }

        @Override // com.huawei.hms.maps.model.internal.IHeatMapDelegate
        public void setRadius(String str) {
        }

        @Override // com.huawei.hms.maps.model.internal.IHeatMapDelegate
        public void setRadiusMap(Map map) {
        }

        @Override // com.huawei.hms.maps.model.internal.IHeatMapDelegate
        public void setRadiusUnit(int i) {
        }

        @Override // com.huawei.hms.maps.model.internal.IHeatMapDelegate
        public void setVisible(boolean z) {
        }
    }

    void changeDataSet(String str);

    void changeDataSetFromRes(int i);

    String getId();

    int getRadiusUnit();

    void remove();

    void setColor(Map map);

    void setIntensity(String str);

    void setIntensityMap(Map map);

    void setOpacity(String str);

    void setOpacityMap(Map map);

    void setRadius(String str);

    void setRadiusMap(Map map);

    void setRadiusUnit(int i);

    void setVisible(boolean z);

    public static abstract class Stub extends Binder implements IHeatMapDelegate {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        static class Proxy implements IHeatMapDelegate {
            public static IHeatMapDelegate sDefaultImpl;

            /* renamed from: a, reason: collision with root package name */
            private IBinder f5022a;

            @Override // com.huawei.hms.maps.model.internal.IHeatMapDelegate
            public void setVisible(boolean z) {
                mab.a(z, this.f5022a, "com.huawei.hms.maps.model.internal.IHeatMapDelegate", 13);
            }

            @Override // com.huawei.hms.maps.model.internal.IHeatMapDelegate
            public void setRadiusUnit(int i) {
                mab.a(this.f5022a, "com.huawei.hms.maps.model.internal.IHeatMapDelegate", 10, Integer.valueOf(i));
            }

            @Override // com.huawei.hms.maps.model.internal.IHeatMapDelegate
            public void setRadiusMap(Map map) {
                mab.a(map, this.f5022a, "com.huawei.hms.maps.model.internal.IHeatMapDelegate", 7);
            }

            @Override // com.huawei.hms.maps.model.internal.IHeatMapDelegate
            public void setRadius(String str) {
                mab.a(str, this.f5022a, "com.huawei.hms.maps.model.internal.IHeatMapDelegate", 6);
            }

            @Override // com.huawei.hms.maps.model.internal.IHeatMapDelegate
            public void setOpacityMap(Map map) {
                mab.a(map, this.f5022a, "com.huawei.hms.maps.model.internal.IHeatMapDelegate", 5);
            }

            @Override // com.huawei.hms.maps.model.internal.IHeatMapDelegate
            public void setOpacity(String str) {
                mab.a(str, this.f5022a, "com.huawei.hms.maps.model.internal.IHeatMapDelegate", 4);
            }

            @Override // com.huawei.hms.maps.model.internal.IHeatMapDelegate
            public void setIntensityMap(Map map) {
                mab.a(map, this.f5022a, "com.huawei.hms.maps.model.internal.IHeatMapDelegate", 9);
            }

            @Override // com.huawei.hms.maps.model.internal.IHeatMapDelegate
            public void setIntensity(String str) {
                mab.a(str, this.f5022a, "com.huawei.hms.maps.model.internal.IHeatMapDelegate", 8);
            }

            @Override // com.huawei.hms.maps.model.internal.IHeatMapDelegate
            public void setColor(Map map) {
                mab.a(map, this.f5022a, "com.huawei.hms.maps.model.internal.IHeatMapDelegate", 3);
            }

            @Override // com.huawei.hms.maps.model.internal.IHeatMapDelegate
            public void remove() {
                mab.i(this.f5022a, "com.huawei.hms.maps.model.internal.IHeatMapDelegate", 2);
            }

            @Override // com.huawei.hms.maps.model.internal.IHeatMapDelegate
            public int getRadiusUnit() {
                return mab.b(this.f5022a, "com.huawei.hms.maps.model.internal.IHeatMapDelegate", 14);
            }

            public String getInterfaceDescriptor() {
                return "com.huawei.hms.maps.model.internal.IHeatMapDelegate";
            }

            @Override // com.huawei.hms.maps.model.internal.IHeatMapDelegate
            public String getId() {
                return mab.d(this.f5022a, "com.huawei.hms.maps.model.internal.IHeatMapDelegate", 1);
            }

            @Override // com.huawei.hms.maps.model.internal.IHeatMapDelegate
            public void changeDataSetFromRes(int i) {
                mab.a(this.f5022a, "com.huawei.hms.maps.model.internal.IHeatMapDelegate", 12, Integer.valueOf(i));
            }

            @Override // com.huawei.hms.maps.model.internal.IHeatMapDelegate
            public void changeDataSet(String str) {
                mab.a(str, this.f5022a, "com.huawei.hms.maps.model.internal.IHeatMapDelegate", 11);
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f5022a;
            }

            Proxy(IBinder iBinder) {
                this.f5022a = iBinder;
            }
        }

        public static boolean setDefaultImpl(IHeatMapDelegate iHeatMapDelegate) {
            if (Proxy.sDefaultImpl != null || iHeatMapDelegate == null) {
                return false;
            }
            Proxy.sDefaultImpl = iHeatMapDelegate;
            return true;
        }

        public static IHeatMapDelegate getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static IHeatMapDelegate asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.hms.maps.model.internal.IHeatMapDelegate");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IHeatMapDelegate)) ? new Proxy(iBinder) : (IHeatMapDelegate) queryLocalInterface;
        }

        public Stub() {
            attachInterface(this, "com.huawei.hms.maps.model.internal.IHeatMapDelegate");
        }
    }
}
