package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.beans.server.AdContentRsp;
import com.huawei.openalliance.ad.constant.ErrorCode;
import com.huawei.openalliance.ad.inter.data.IPlacementAd;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class pk extends op {
    @Override // com.huawei.openalliance.ad.op
    public void b(AdContentRsp adContentRsp) {
        if (adContentRsp == null) {
            this.c.a(ErrorCode.ERROR_CODE_OTHER);
            ho.c("PlacementPreloadProcessor", "response is null");
            return;
        }
        Map<String, List<IPlacementAd>> b = b(adContentRsp.f());
        if (b.isEmpty()) {
            this.c.a(800);
        } else {
            this.c.a(null, b);
        }
    }

    public pk(Context context, qx qxVar) {
        super(context, qxVar);
    }
}
