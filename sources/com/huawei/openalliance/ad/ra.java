package com.huawei.openalliance.ad;

import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.vast.ClickThrough;
import com.huawei.openalliance.ad.beans.vast.LinearCreative;
import com.huawei.openalliance.ad.beans.vast.Tracking;
import com.huawei.openalliance.ad.beans.vast.VastContent;
import com.huawei.openalliance.ad.beans.vast.VastMediaFile;
import com.huawei.openalliance.ad.beans.vast.VideoClicks;
import com.huawei.openalliance.ad.constant.VastAttribute;
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
public abstract class ra {
    protected abstract Set<String> a();

    protected abstract void a(LinearCreative linearCreative, XmlPullParser xmlPullParser, Map<String, rh.a> map);

    static class b implements rh.a {

        /* renamed from: a, reason: collision with root package name */
        private final List<VastMediaFile> f7482a;
        private final XmlPullParser b;

        @Override // com.huawei.openalliance.ad.rh.a
        public void a() {
            List<VastMediaFile> list = this.f7482a;
            if (list != null) {
                list.add(a(this.b));
            }
        }

        private VastMediaFile a(XmlPullParser xmlPullParser) {
            if (xmlPullParser == null) {
                return null;
            }
            ho.a("BaseLinearParser", "start read media file");
            xmlPullParser.require(2, VastTag.NAMESPACE, VastTag.MEDIA_FILE);
            VastMediaFile vastMediaFile = new VastMediaFile();
            vastMediaFile.b(xmlPullParser.getAttributeValue(VastTag.NAMESPACE, VastAttribute.DELIVERY));
            String attributeValue = xmlPullParser.getAttributeValue(VastTag.NAMESPACE, "height");
            String attributeValue2 = xmlPullParser.getAttributeValue(VastTag.NAMESPACE, "width");
            if (TextUtils.isEmpty(attributeValue) || TextUtils.isEmpty(attributeValue2)) {
                ho.b("BaseLinearParser", "media file missing required attribute");
                return null;
            }
            int a2 = com.huawei.openalliance.ad.utils.cz.a(attributeValue, 0);
            int a3 = com.huawei.openalliance.ad.utils.cz.a(attributeValue2, 0);
            if (a2 == 0 || a3 == 0) {
                ho.a("BaseLinearParser", "media file height or width is invalid.");
                return null;
            }
            vastMediaFile.b(a2);
            vastMediaFile.a(a3);
            vastMediaFile.a(xmlPullParser.getAttributeValue(VastTag.NAMESPACE, "id"));
            vastMediaFile.c(rh.a(xmlPullParser, "type"));
            String a4 = rh.a(xmlPullParser);
            if (TextUtils.isEmpty(a4) || a4.trim().isEmpty()) {
                ho.d("BaseLinearParser", "media file url not be empty");
                return null;
            }
            vastMediaFile.d(a4);
            return vastMediaFile;
        }

        public b(List<VastMediaFile> list, XmlPullParser xmlPullParser) {
            this.f7482a = list;
            this.b = xmlPullParser;
        }
    }

    static class c implements rh.a {

        /* renamed from: a, reason: collision with root package name */
        private final LinearCreative f7483a;
        private final XmlPullParser b;

        static class a implements rh.a {

            /* renamed from: a, reason: collision with root package name */
            private final XmlPullParser f7484a;
            private final Map<String, List<Tracking>> b;

            @Override // com.huawei.openalliance.ad.rh.a
            public void a() {
                XmlPullParser xmlPullParser = this.f7484a;
                if (xmlPullParser == null || this.b == null) {
                    return;
                }
                String attributeValue = xmlPullParser.getAttributeValue(VastTag.NAMESPACE, "event");
                String a2 = rh.a(this.f7484a);
                if (ri.c().a().contains(attributeValue)) {
                    if (this.b.get(attributeValue) == null) {
                        this.b.put(attributeValue, new ArrayList());
                    }
                    this.b.get(attributeValue).add(new Tracking(a2, attributeValue));
                }
            }

            public a(XmlPullParser xmlPullParser, Map<String, List<Tracking>> map) {
                this.f7484a = xmlPullParser;
                this.b = map;
            }
        }

        @Override // com.huawei.openalliance.ad.rh.a
        public void a() {
            LinearCreative linearCreative = this.f7483a;
            if (linearCreative != null) {
                linearCreative.a(a(this.b));
            }
        }

        private Map<String, List<Tracking>> a(XmlPullParser xmlPullParser) {
            if (xmlPullParser == null) {
                return null;
            }
            ho.a("BaseLinearParser", "start read tracking events");
            xmlPullParser.require(2, VastTag.NAMESPACE, VastTag.TRACKING_EVENTS);
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = new HashMap();
            hashMap2.put(VastTag.TRACKING, new a(xmlPullParser, hashMap));
            rh.a(xmlPullParser, hashMap2, (List<String>) Collections.emptyList());
            ho.a("BaseLinearParser", "read tracking events finish, tracking events: %s", hashMap);
            return hashMap;
        }

        public c(LinearCreative linearCreative, XmlPullParser xmlPullParser) {
            this.f7483a = linearCreative;
            this.b = xmlPullParser;
        }
    }

    static class e implements rh.a {

        /* renamed from: a, reason: collision with root package name */
        private final VideoClicks f7486a;
        private final XmlPullParser b;

        @Override // com.huawei.openalliance.ad.rh.a
        public void a() {
            VideoClicks videoClicks = this.f7486a;
            if (videoClicks != null) {
                videoClicks.a(a(this.b));
            }
        }

        private ClickThrough a(XmlPullParser xmlPullParser) {
            if (xmlPullParser == null) {
                return null;
            }
            ho.a("BaseLinearParser", "start read click through");
            xmlPullParser.require(2, VastTag.NAMESPACE, VastTag.CLICK_THROUGH);
            ClickThrough clickThrough = new ClickThrough();
            clickThrough.a(xmlPullParser.getAttributeValue(VastTag.NAMESPACE, "id"));
            clickThrough.b(rh.a(xmlPullParser));
            ho.a("BaseLinearParser", "finish read click trough: %s", clickThrough);
            return clickThrough;
        }

        public e(VideoClicks videoClicks, XmlPullParser xmlPullParser) {
            this.f7486a = videoClicks;
            this.b = xmlPullParser;
        }
    }

    static class f implements rh.a {

        /* renamed from: a, reason: collision with root package name */
        private final LinearCreative f7487a;
        private final XmlPullParser b;

        static class a implements rh.a {

            /* renamed from: a, reason: collision with root package name */
            private final XmlPullParser f7488a;
            private final List<Tracking> b;

            @Override // com.huawei.openalliance.ad.rh.a
            public void a() {
                XmlPullParser xmlPullParser = this.f7488a;
                if (xmlPullParser == null || this.b == null) {
                    return;
                }
                String attributeValue = xmlPullParser.getAttributeValue(VastTag.NAMESPACE, "id");
                this.b.add(new Tracking(rh.a(this.f7488a), attributeValue));
            }

            public a(XmlPullParser xmlPullParser, List<Tracking> list) {
                this.f7488a = xmlPullParser;
                this.b = list;
            }
        }

        @Override // com.huawei.openalliance.ad.rh.a
        public void a() {
            LinearCreative linearCreative = this.f7487a;
            if (linearCreative != null) {
                linearCreative.a(a(this.b));
            }
        }

        private VideoClicks a(XmlPullParser xmlPullParser) {
            if (xmlPullParser == null) {
                return null;
            }
            ho.a("BaseLinearParser", "start read video clicks");
            xmlPullParser.require(2, VastTag.NAMESPACE, VastTag.VIDEO_CLICKS);
            VideoClicks videoClicks = new VideoClicks();
            ArrayList arrayList = new ArrayList();
            HashMap hashMap = new HashMap();
            hashMap.put(VastTag.CLICK_THROUGH, new e(videoClicks, xmlPullParser));
            hashMap.put("ClickTracking", new a(xmlPullParser, arrayList));
            rh.a(xmlPullParser, hashMap, (List<String>) Collections.emptyList());
            videoClicks.a(arrayList);
            ho.a("BaseLinearParser", "finish read video clicks, video clicks: %s", videoClicks);
            return videoClicks;
        }

        public f(LinearCreative linearCreative, XmlPullParser xmlPullParser) {
            this.f7487a = linearCreative;
            this.b = xmlPullParser;
        }
    }

    static class a implements rh.a {

        /* renamed from: a, reason: collision with root package name */
        private final LinearCreative f7481a;
        private final XmlPullParser b;

        @Override // com.huawei.openalliance.ad.rh.a
        public void a() {
            LinearCreative linearCreative = this.f7481a;
            if (linearCreative != null) {
                linearCreative.a(com.huawei.openalliance.ad.utils.cz.a(rh.a(rh.a(this.b)), 0));
            }
        }

        public a(LinearCreative linearCreative, XmlPullParser xmlPullParser) {
            this.f7481a = linearCreative;
            this.b = xmlPullParser;
        }
    }

    static class d implements rh.a {

        /* renamed from: a, reason: collision with root package name */
        private final XmlPullParser f7485a;
        private final LinearCreative b;

        @Override // com.huawei.openalliance.ad.rh.a
        public void a() {
            XmlPullParser xmlPullParser = this.f7485a;
            if (xmlPullParser == null || this.b == null) {
                return;
            }
            xmlPullParser.require(2, VastTag.NAMESPACE, VastTag.MEDIA_FILES);
            ArrayList arrayList = new ArrayList();
            HashMap hashMap = new HashMap();
            hashMap.put(VastTag.MEDIA_FILE, new b(arrayList, this.f7485a));
            rh.a(this.f7485a, hashMap, (List<String>) Collections.singletonList(VastTag.MEDIA_FILE));
            this.b.a(arrayList);
        }

        public d(LinearCreative linearCreative, XmlPullParser xmlPullParser) {
            this.f7485a = xmlPullParser;
            this.b = linearCreative;
        }
    }

    public void a(LinearCreative linearCreative, XmlPullParser xmlPullParser, VastContent vastContent) {
        if (linearCreative == null || xmlPullParser == null || vastContent == null) {
            return;
        }
        ho.a("BaseLinearParser", "start read linear creative");
        xmlPullParser.require(2, VastTag.NAMESPACE, VastTag.LINEAR);
        HashMap hashMap = new HashMap();
        b(linearCreative, xmlPullParser, hashMap);
        rh.a(xmlPullParser, hashMap, (List<String>) (vastContent.g().booleanValue() ? Collections.emptyList() : Arrays.asList(VastTag.DURATION, VastTag.MEDIA_FILES)));
        ho.a("BaseLinearParser", "read linear creative finish");
    }

    private void b(LinearCreative linearCreative, XmlPullParser xmlPullParser, Map<String, rh.a> map) {
        if (map == null) {
            return;
        }
        map.put(VastTag.DURATION, new a(linearCreative, xmlPullParser));
        map.put(VastTag.MEDIA_FILES, new d(linearCreative, xmlPullParser));
        map.put(VastTag.VIDEO_CLICKS, new f(linearCreative, xmlPullParser));
        map.put(VastTag.TRACKING_EVENTS, new c(linearCreative, xmlPullParser));
        a(linearCreative, xmlPullParser, map);
    }
}
