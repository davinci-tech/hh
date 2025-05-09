package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.beans.vast.LinearCreative;
import com.huawei.openalliance.ad.constant.VastAttribute;
import com.huawei.openalliance.ad.rh;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes5.dex */
public class rd extends ra {

    /* renamed from: a, reason: collision with root package name */
    private Set<String> f7496a = new HashSet(Arrays.asList(VastAttribute.CREATIVE_VIEW, "start", "firstQuartile", "midpoint", "thirdQuartile", "complete", "mute", "unmute", VastAttribute.PAUSE, "resume", VastAttribute.CLOSE_LINEAR, "skip", "progress"));

    @Override // com.huawei.openalliance.ad.ra
    protected void a(LinearCreative linearCreative, XmlPullParser xmlPullParser, Map<String, rh.a> map) {
    }

    @Override // com.huawei.openalliance.ad.ra
    protected Set<String> a() {
        return this.f7496a;
    }
}
