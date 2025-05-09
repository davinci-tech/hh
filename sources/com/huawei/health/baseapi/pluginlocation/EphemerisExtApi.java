package com.huawei.health.baseapi.pluginlocation;

import defpackage.eyk;
import defpackage.eyl;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public interface EphemerisExtApi {
    eyk getEphRequest(List<String> list);

    Map<String, String> getEphResponse(eyl eylVar);
}
