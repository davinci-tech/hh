package com.huawei.hms.maps;

import android.location.Location;

/* loaded from: classes9.dex */
public interface LocationSource {

    public interface OnLocationChangedListener {
        void onLocationChanged(Location location);
    }

    void activate(OnLocationChangedListener onLocationChangedListener);

    void deactivate();
}
