package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.beans.vast.LinearCreative;
import com.huawei.openalliance.ad.beans.vast.VastIcon;
import com.huawei.openalliance.ad.constant.VastAttribute;
import com.huawei.openalliance.ad.constant.VastTag;
import com.huawei.openalliance.ad.rh;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes5.dex */
public class re extends ra {

    /* renamed from: a, reason: collision with root package name */
    private Set<String> f7497a = new HashSet(Arrays.asList(VastAttribute.CREATIVE_VIEW, "start", "firstQuartile", "midpoint", "thirdQuartile", "complete", "mute", "unmute", VastAttribute.PAUSE, "resume", VastAttribute.CLOSE_LINEAR, "skip", "progress"));

    static class a implements rh.a {

        /* renamed from: a, reason: collision with root package name */
        private final LinearCreative f7498a;
        private final XmlPullParser b;

        @Override // com.huawei.openalliance.ad.rh.a
        public void a() {
            LinearCreative linearCreative = this.f7498a;
            if (linearCreative != null) {
                linearCreative.b(a(this.b));
            }
        }

        private List<VastIcon> a(XmlPullParser xmlPullParser) {
            if (xmlPullParser == null) {
                return null;
            }
            ho.a("Linear30Parser", "start read icons");
            xmlPullParser.require(2, VastTag.NAMESPACE, VastTag.ICONS);
            ArrayList arrayList = new ArrayList();
            HashMap hashMap = new HashMap();
            hashMap.put(VastTag.ICON, new b(arrayList, xmlPullParser));
            rh.a(xmlPullParser, hashMap, (List<String>) Collections.emptyList());
            ho.a("Linear30Parser", "read icons finish");
            return arrayList;
        }

        public a(LinearCreative linearCreative, XmlPullParser xmlPullParser) {
            this.f7498a = linearCreative;
            this.b = xmlPullParser;
        }
    }

    static class b implements rh.a {

        /* renamed from: a, reason: collision with root package name */
        private final List<VastIcon> f7499a;
        private final XmlPullParser b;

        static class a implements rh.a {

            /* renamed from: a, reason: collision with root package name */
            private final VastIcon f7500a;
            private final XmlPullParser b;

            @Override // com.huawei.openalliance.ad.rh.a
            public void a() {
                VastIcon vastIcon = this.f7500a;
                if (vastIcon != null) {
                    vastIcon.e(rh.a(this.b));
                }
            }

            public a(VastIcon vastIcon, XmlPullParser xmlPullParser) {
                this.f7500a = vastIcon;
                this.b = xmlPullParser;
            }
        }

        /* renamed from: com.huawei.openalliance.ad.re$b$b, reason: collision with other inner class name */
        static class C0208b implements rh.a {

            /* renamed from: a, reason: collision with root package name */
            private final VastIcon f7501a;
            private final XmlPullParser b;

            @Override // com.huawei.openalliance.ad.rh.a
            public void a() {
                VastIcon vastIcon = this.f7501a;
                if (vastIcon != null) {
                    vastIcon.d(rh.a(this.b));
                }
            }

            public C0208b(VastIcon vastIcon, XmlPullParser xmlPullParser) {
                this.f7501a = vastIcon;
                this.b = xmlPullParser;
            }
        }

        static class c implements rh.a {

            /* renamed from: a, reason: collision with root package name */
            private final VastIcon f7502a;
            private final XmlPullParser b;

            @Override // com.huawei.openalliance.ad.rh.a
            public void a() {
                VastIcon vastIcon = this.f7502a;
                if (vastIcon != null) {
                    vastIcon.a(rh.b(this.b));
                }
            }

            public c(VastIcon vastIcon, XmlPullParser xmlPullParser) {
                this.f7502a = vastIcon;
                this.b = xmlPullParser;
            }
        }

        @Override // com.huawei.openalliance.ad.rh.a
        public void a() {
            List<VastIcon> list = this.f7499a;
            if (list != null) {
                list.add(a(this.b));
            }
        }

        private VastIcon a(XmlPullParser xmlPullParser) {
            if (xmlPullParser == null) {
                return null;
            }
            ho.a("Linear30Parser", "start read icon");
            xmlPullParser.require(2, VastTag.NAMESPACE, VastTag.ICON);
            VastIcon vastIcon = new VastIcon();
            String attributeValue = xmlPullParser.getAttributeValue(VastTag.NAMESPACE, VastAttribute.PROGRAM);
            if (attributeValue != null) {
                vastIcon.a(attributeValue);
            }
            String attributeValue2 = xmlPullParser.getAttributeValue(VastTag.NAMESPACE, "width");
            String attributeValue3 = xmlPullParser.getAttributeValue(VastTag.NAMESPACE, "height");
            if (attributeValue2 == null || attributeValue3 == null) {
                ho.c("Linear30Parser", "icon width or height is missing.");
                return null;
            }
            vastIcon.b(Integer.parseInt(attributeValue2));
            vastIcon.a(Integer.parseInt(attributeValue3));
            vastIcon.b(rh.a(xmlPullParser, VastAttribute.XPOSITION));
            vastIcon.c(rh.a(xmlPullParser, VastAttribute.YPOSITION));
            HashMap hashMap = new HashMap();
            hashMap.put(VastTag.STATIC_RESOURCE, new c(vastIcon, xmlPullParser));
            hashMap.put(VastTag.IFRAME_RESOURCE, new C0208b(vastIcon, xmlPullParser));
            hashMap.put(VastTag.HTML_RESOURCE, new a(vastIcon, xmlPullParser));
            rh.a(xmlPullParser, hashMap, (List<String>) Collections.emptyList());
            ho.a("Linear30Parser", "read icon finish, icon: %s", vastIcon);
            return vastIcon;
        }

        public b(List<VastIcon> list, XmlPullParser xmlPullParser) {
            this.f7499a = list;
            this.b = xmlPullParser;
        }
    }

    @Override // com.huawei.openalliance.ad.ra
    protected void a(LinearCreative linearCreative, XmlPullParser xmlPullParser, Map<String, rh.a> map) {
        if (map != null) {
            map.put(VastTag.ICONS, new a(linearCreative, xmlPullParser));
        }
    }

    @Override // com.huawei.openalliance.ad.ra
    protected Set<String> a() {
        return this.f7497a;
    }
}
