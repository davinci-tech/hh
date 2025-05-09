package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.beans.vast.Vast;
import com.huawei.openalliance.ad.beans.vast.VastContent;
import com.huawei.openalliance.ad.constant.VastTag;
import com.huawei.openalliance.ad.rh;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes5.dex */
public abstract class rc {
    public abstract List<VastContent> a(XmlPullParser xmlPullParser);

    protected abstract void a(XmlPullParser xmlPullParser, VastContent vastContent);

    static class a implements rh.a {

        /* renamed from: a, reason: collision with root package name */
        private XmlPullParser f7494a;
        private VastContent b;

        @Override // com.huawei.openalliance.ad.rh.a
        public void a() {
            VastContent vastContent = this.b;
            if (vastContent == null || this.f7494a == null) {
                return;
            }
            ho.b("BaseVastParser", "read inline, %s.", vastContent.a());
            rh.b(this.f7494a, this.b);
        }

        public a(XmlPullParser xmlPullParser, VastContent vastContent) {
            this.f7494a = xmlPullParser;
            this.b = vastContent;
        }
    }

    static class b implements rh.a {

        /* renamed from: a, reason: collision with root package name */
        private final XmlPullParser f7495a;
        private final VastContent b;

        @Override // com.huawei.openalliance.ad.rh.a
        public void a() {
            VastContent vastContent = this.b;
            if (vastContent == null || this.f7495a == null) {
                return;
            }
            ho.b("BaseVastParser", "read warpper, %s.", vastContent.a());
            rh.a(this.f7495a, this.b);
        }

        public b(XmlPullParser xmlPullParser, VastContent vastContent) {
            this.f7495a = xmlPullParser;
            this.b = vastContent;
        }
    }

    protected static VastContent c(XmlPullParser xmlPullParser) {
        xmlPullParser.require(2, VastTag.NAMESPACE, VastTag.AD);
        VastContent vastContent = new VastContent();
        vastContent.a(xmlPullParser.getAttributeValue(VastTag.NAMESPACE, "id"));
        ri.a().a(xmlPullParser, vastContent);
        HashMap hashMap = new HashMap();
        hashMap.put(VastTag.INLINE, new a(xmlPullParser, vastContent));
        hashMap.put(VastTag.WRAPPER, new b(xmlPullParser, vastContent));
        try {
            rh.a(xmlPullParser, hashMap, (List<String>) Collections.emptyList());
        } catch (Throwable th) {
            ho.c("BaseVastParser", "attribute format error: %s", th.getClass().getSimpleName());
        }
        return vastContent;
    }

    protected static Vast b(final XmlPullParser xmlPullParser) {
        HashMap hashMap = new HashMap();
        final ArrayList arrayList = new ArrayList();
        hashMap.put(VastTag.AD, new rh.a() { // from class: com.huawei.openalliance.ad.rc.1
            @Override // com.huawei.openalliance.ad.rh.a
            public void a() {
                arrayList.add(rc.c(xmlPullParser));
            }
        });
        rh.a(xmlPullParser, hashMap, (List<String>) Collections.emptyList());
        return new Vast(arrayList);
    }
}
