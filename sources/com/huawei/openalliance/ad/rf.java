package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.beans.vast.Tracking;
import com.huawei.openalliance.ad.constant.VastAttribute;
import com.huawei.openalliance.ad.rh;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes5.dex */
public class rf extends rb {

    /* renamed from: a, reason: collision with root package name */
    private Set<String> f7503a = new HashSet(Arrays.asList(VastAttribute.CREATIVE_VIEW, "start", "firstQuartile", "midpoint", "thirdQuartile", "complete", "mute", "unmute", VastAttribute.PAUSE, "resume", "close"));

    @Override // com.huawei.openalliance.ad.rb
    protected void a(XmlPullParser xmlPullParser, List<Tracking> list, Map<String, rh.a> map) {
    }

    @Override // com.huawei.openalliance.ad.rb
    protected Set<String> a() {
        return this.f7503a;
    }
}
