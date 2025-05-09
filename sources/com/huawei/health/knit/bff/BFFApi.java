package com.huawei.health.knit.bff;

import com.huawei.health.knit.bff.impl.IResourceRequestCallback;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public interface BFFApi {
    void getPageResource(List<Integer> list, boolean z, IResourceRequestCallback iResourceRequestCallback);

    void setExtraRequestArgs(JSONObject jSONObject);
}
