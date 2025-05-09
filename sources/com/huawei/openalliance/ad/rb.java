package com.huawei.openalliance.ad;

import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.vast.NonLinear;
import com.huawei.openalliance.ad.beans.vast.NonLinearAds;
import com.huawei.openalliance.ad.beans.vast.Tracking;
import com.huawei.openalliance.ad.constant.VastTag;
import com.huawei.openalliance.ad.rh;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes5.dex */
public abstract class rb {

    /* renamed from: a, reason: collision with root package name */
    private static final List<String> f7489a = Arrays.asList(VastTag.IFRAME_RESOURCE, VastTag.STATIC_RESOURCE, VastTag.HTML_RESOURCE, VastTag.NON_LINEAR_CLICK_THROUGH);

    protected abstract Set<String> a();

    protected abstract void a(XmlPullParser xmlPullParser, List<Tracking> list, Map<String, rh.a> map);

    public void a(final NonLinearAds nonLinearAds, final XmlPullParser xmlPullParser) {
        if (nonLinearAds == null || xmlPullParser == null) {
            return;
        }
        ho.b("BaseNonLinearParser", "read nonlinearAds start");
        final ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        hashMap.put(VastTag.NON_LINEAR, new rh.a() { // from class: com.huawei.openalliance.ad.rb.1
            @Override // com.huawei.openalliance.ad.rh.a
            public void a() {
                arrayList.add(rb.this.a(xmlPullParser));
            }
        });
        hashMap.put(VastTag.TRACKING_EVENTS, new rh.a() { // from class: com.huawei.openalliance.ad.rb.2
            @Override // com.huawei.openalliance.ad.rh.a
            public void a() {
                nonLinearAds.a(rb.this.b(xmlPullParser));
            }
        });
        rh.a(xmlPullParser, hashMap, (List<String>) Collections.singletonList(VastTag.NON_LINEAR));
        ho.a("BaseNonLinearParser", "nonLinearAds setNonLinears: %s", com.huawei.openalliance.ad.utils.be.b(arrayList));
        nonLinearAds.a(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, List<Tracking>> b(final XmlPullParser xmlPullParser) {
        if (xmlPullParser == null) {
            return null;
        }
        ho.b("BaseNonLinearParser", "start read tracking events");
        xmlPullParser.require(2, VastTag.NAMESPACE, VastTag.TRACKING_EVENTS);
        final HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        hashMap2.put(VastTag.TRACKING, new rh.a() { // from class: com.huawei.openalliance.ad.rb.3
            @Override // com.huawei.openalliance.ad.rh.a
            public void a() {
                String attributeValue = xmlPullParser.getAttributeValue(VastTag.NAMESPACE, "event");
                String a2 = rh.a(xmlPullParser);
                if (rb.this.a().contains(attributeValue)) {
                    if (hashMap.get(attributeValue) == null) {
                        hashMap.put(attributeValue, new ArrayList());
                    }
                    ((List) hashMap.get(attributeValue)).add(new Tracking(a2, attributeValue));
                }
            }
        });
        rh.a(xmlPullParser, hashMap2, (List<String>) Collections.emptyList());
        if (ho.a()) {
            ho.a("BaseNonLinearParser", "read tracking events finish, trackingEvents: %s", hashMap);
        }
        return hashMap;
    }

    private void a(Map<String, rh.a> map, NonLinear nonLinear, XmlPullParser xmlPullParser) {
        if (map == null) {
            return;
        }
        for (String str : f7489a) {
            map.put(str, new rn(str, nonLinear, xmlPullParser));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public NonLinear a(XmlPullParser xmlPullParser) {
        if (xmlPullParser == null) {
            return null;
        }
        xmlPullParser.require(2, VastTag.NAMESPACE, VastTag.NON_LINEAR);
        ho.b("BaseNonLinearParser", "readNonLinear");
        NonLinear nonLinear = new NonLinear();
        nonLinear.a(xmlPullParser.getAttributeValue(VastTag.NAMESPACE, "id"));
        String attributeValue = xmlPullParser.getAttributeValue(VastTag.NAMESPACE, "height");
        String attributeValue2 = xmlPullParser.getAttributeValue(VastTag.NAMESPACE, "width");
        if (TextUtils.isEmpty(attributeValue2) || TextUtils.isEmpty(attributeValue)) {
            ho.c("BaseNonLinearParser", "missing required attribute.");
            return null;
        }
        nonLinear.a(com.huawei.openalliance.ad.utils.cz.h(attributeValue).intValue());
        nonLinear.b(com.huawei.openalliance.ad.utils.cz.h(attributeValue2).intValue());
        HashMap hashMap = new HashMap();
        a(hashMap, nonLinear, xmlPullParser);
        ArrayList arrayList = new ArrayList();
        a(xmlPullParser, arrayList, hashMap);
        rh.a(xmlPullParser, hashMap, (List<String>) Collections.emptyList());
        nonLinear.a(arrayList);
        if (ho.a()) {
            ho.a("BaseNonLinearParser", "readNonlinear finish, nonLinear: %s", com.huawei.openalliance.ad.utils.be.b(nonLinear));
        }
        return nonLinear;
    }
}
