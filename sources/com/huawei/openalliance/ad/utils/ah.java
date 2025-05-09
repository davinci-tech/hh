package com.huawei.openalliance.ad.utils;

import android.content.Context;
import android.location.Geocoder;
import com.huawei.openalliance.ad.beans.metadata.Address;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.ho;
import java.util.List;
import java.util.Locale;

/* loaded from: classes5.dex */
public class ah {
    public static boolean a(Context context) {
        if (context == null) {
            return false;
        }
        if (fh.b(context).w()) {
            return true;
        }
        ho.b("GeoLocationUtil", "collect geoinfo switch off");
        return false;
    }

    public static Address a(android.location.Address address) {
        if (address == null) {
            return null;
        }
        Address address2 = new Address();
        address2.a(address.getCountryCode());
        address2.b(cz.d(address.getAdminArea()));
        address2.c(cz.d(address.getLocality()));
        address2.d(cz.d(address.getSubLocality()));
        address2.e(cz.d(address.getSubAdminArea()));
        address2.f(cz.d(address.getThoroughfare()));
        address2.g(cz.d(address.getSubThoroughfare()));
        address2.h(cz.d(address.getFeatureName()));
        address2.i(address.getLocale().toString());
        return address2;
    }

    public static android.location.Address a(Context context, Double d, Double d2) {
        List<android.location.Address> list;
        if (context == null) {
            return null;
        }
        try {
            list = new Geocoder(context, Locale.getDefault()).getFromLocation(d2.doubleValue(), d.doubleValue(), 1);
        } catch (Throwable th) {
            ho.c("GeoLocationUtil", "onGetGeoError Exception:" + th.getClass().getSimpleName());
            list = null;
        }
        if (list == null || list.size() <= 0) {
            return null;
        }
        return list.get(0);
    }
}
