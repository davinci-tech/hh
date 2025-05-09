package com.huawei.hms.maps.internal;

import android.location.Location;
import android.os.RemoteException;
import com.huawei.hms.maps.LocationSource;
import com.huawei.hms.maps.internal.ILocationSourceDelegate;
import com.huawei.hms.maps.utils.LogM;

/* loaded from: classes9.dex */
public class maa extends ILocationSourceDelegate.Stub {

    /* renamed from: a, reason: collision with root package name */
    private LocationSource f4966a;

    /* renamed from: com.huawei.hms.maps.internal.maa$maa, reason: collision with other inner class name */
    public static class C0131maa implements LocationSource.OnLocationChangedListener {

        /* renamed from: a, reason: collision with root package name */
        IOnLocationChangeListener f4967a;

        @Override // com.huawei.hms.maps.LocationSource.OnLocationChangedListener
        public void onLocationChanged(Location location) {
            try {
                this.f4967a.onLocationChange(location);
            } catch (RemoteException unused) {
                LogM.e("LocationSourceDelegate", "onLocationChanged RemoteException ");
            }
        }

        public C0131maa(IOnLocationChangeListener iOnLocationChangeListener) {
            this.f4967a = iOnLocationChangeListener;
        }
    }

    @Override // com.huawei.hms.maps.internal.ILocationSourceDelegate
    public void deactivate() {
        LogM.d("LocationSourceDelegate", "deactivate");
        this.f4966a.deactivate();
    }

    @Override // com.huawei.hms.maps.internal.ILocationSourceDelegate
    public void activate(IOnLocationChangeListener iOnLocationChangeListener) {
        LogM.d("LocationSourceDelegate", "active");
        this.f4966a.activate(new C0131maa(iOnLocationChangeListener));
    }

    public maa(LocationSource locationSource) {
        this.f4966a = locationSource;
    }
}
