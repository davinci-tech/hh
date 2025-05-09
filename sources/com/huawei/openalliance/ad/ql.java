package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.beans.metadata.DelayInfo;
import com.huawei.openalliance.ad.beans.parameter.AdSlotParam;
import com.huawei.openalliance.ad.beans.server.AdContentRsp;
import java.util.List;

/* loaded from: classes5.dex */
public interface ql {
    AdContentRsp a(Context context, AdSlotParam adSlotParam, int i);

    AdContentRsp a(AdSlotParam adSlotParam, List<String> list, List<String> list2);

    void a(DelayInfo delayInfo);

    void a(AdContentRsp adContentRsp, AdSlotParam adSlotParam, qw qwVar, qk qkVar, long j, int i);
}
