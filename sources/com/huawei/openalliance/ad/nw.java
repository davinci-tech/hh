package com.huawei.openalliance.ad;

import android.location.Location;
import com.huawei.openalliance.ad.beans.parameter.RequestOptions;
import com.huawei.openalliance.ad.inter.data.BannerSize;
import com.huawei.openalliance.ad.views.interfaces.h;
import java.util.List;

/* loaded from: classes9.dex */
public interface nw<V extends com.huawei.openalliance.ad.views.interfaces.h> {
    void a(Location location);

    void a(RequestOptions requestOptions);

    void a(com.huawei.openalliance.ad.inter.data.e eVar);

    void a(Integer num);

    void a(String str, int i, List<String> list, int i2);

    boolean a();

    boolean a(BannerSize bannerSize, float f);

    void b(Integer num);

    void b(String str);
}
