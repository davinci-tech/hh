package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.beans.vast.Creative;
import com.huawei.openalliance.ad.beans.vast.LinearCreative;
import com.huawei.openalliance.ad.beans.vast.NonLinearAds;
import com.huawei.openalliance.ad.beans.vast.VastContent;
import com.huawei.openalliance.ad.constant.VastAttribute;
import com.huawei.openalliance.ad.constant.VastTag;
import com.huawei.openalliance.ad.rh;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes5.dex */
public class rl implements rh.a {

    /* renamed from: a, reason: collision with root package name */
    private VastContent f7507a;
    private XmlPullParser b;
    private List<Creative> c;

    static class a implements rh.a {

        /* renamed from: a, reason: collision with root package name */
        private final XmlPullParser f7508a;
        private final Creative b;
        private final VastContent c;

        @Override // com.huawei.openalliance.ad.rh.a
        public void a() {
            if (this.b == null) {
                return;
            }
            LinearCreative linearCreative = new LinearCreative();
            ri.c().a(linearCreative, this.f7508a, this.c);
            this.b.a(linearCreative);
        }

        public a(XmlPullParser xmlPullParser, VastContent vastContent, Creative creative) {
            this.f7508a = xmlPullParser;
            this.c = vastContent;
            this.b = creative;
        }
    }

    static class b implements rh.a {

        /* renamed from: a, reason: collision with root package name */
        private final XmlPullParser f7509a;
        private final Creative b;

        @Override // com.huawei.openalliance.ad.rh.a
        public void a() {
            if (this.b == null) {
                return;
            }
            NonLinearAds nonLinearAds = new NonLinearAds();
            ri.b().a(nonLinearAds, this.f7509a);
            this.b.a(nonLinearAds);
        }

        public b(XmlPullParser xmlPullParser, Creative creative) {
            this.f7509a = xmlPullParser;
            this.b = creative;
        }
    }

    @Override // com.huawei.openalliance.ad.rh.a
    public void a() {
        List<Creative> list = this.c;
        if (list != null) {
            list.add(a(this.b, this.f7507a));
        }
    }

    private Creative a(XmlPullParser xmlPullParser, VastContent vastContent) {
        if (xmlPullParser == null || vastContent == null) {
            return null;
        }
        ho.a("CreativeTagHandle", "start read creative, ad id: %s", vastContent.a());
        xmlPullParser.require(2, VastTag.NAMESPACE, VastTag.CREATIVE);
        String attributeValue = xmlPullParser.getAttributeValue(VastTag.NAMESPACE, VastAttribute.AD_ID);
        String attributeValue2 = xmlPullParser.getAttributeValue(VastTag.NAMESPACE, "id");
        if (attributeValue2 == null) {
            attributeValue2 = com.huawei.openalliance.ad.utils.ci.a(8);
        }
        String attributeValue3 = xmlPullParser.getAttributeValue(VastTag.NAMESPACE, VastAttribute.SEQUENCE);
        Creative creative = new Creative(attributeValue2, attributeValue3 != null ? Integer.valueOf(attributeValue3) : null, attributeValue);
        HashMap hashMap = new HashMap();
        hashMap.put(VastTag.LINEAR, new a(xmlPullParser, vastContent, creative));
        hashMap.put(VastTag.NON_LINEAR_ADS, new b(xmlPullParser, creative));
        rh.a(xmlPullParser, hashMap, (List<String>) Arrays.asList(VastTag.LINEAR, VastTag.NON_LINEAR_ADS));
        return creative;
    }

    public rl(VastContent vastContent, XmlPullParser xmlPullParser, List<Creative> list) {
        this.f7507a = vastContent;
        this.b = xmlPullParser;
        this.c = list;
    }
}
