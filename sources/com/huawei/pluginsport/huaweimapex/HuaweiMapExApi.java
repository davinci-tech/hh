package com.huawei.pluginsport.huaweimapex;

import com.huawei.hms.maps.HuaweiMap;
import com.huawei.hms.maps.model.Marker;
import defpackage.hlg;

/* loaded from: classes6.dex */
public interface HuaweiMapExApi {
    boolean set3dMapType(HuaweiMap huaweiMap, boolean z);

    Marker set3dMarker(HuaweiMap huaweiMap, hlg hlgVar);

    boolean set3dMarkerScale(Marker marker, double d);

    boolean set3dScale(HuaweiMap huaweiMap, float f);

    boolean setRotate(Marker marker, float f, float f2, float f3);
}
