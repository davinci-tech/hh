package com.huawei.openalliance.ad;

import android.text.TextUtils;
import android.util.Xml;
import com.huawei.openalliance.ad.beans.metadata.ImageInfo;
import com.huawei.openalliance.ad.beans.metadata.MediaFile;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.beans.metadata.Monitor;
import com.huawei.openalliance.ad.beans.metadata.VideoInfo;
import com.huawei.openalliance.ad.beans.vast.ClickThrough;
import com.huawei.openalliance.ad.beans.vast.Creative;
import com.huawei.openalliance.ad.beans.vast.Impression;
import com.huawei.openalliance.ad.beans.vast.LinearCreative;
import com.huawei.openalliance.ad.beans.vast.NonLinear;
import com.huawei.openalliance.ad.beans.vast.NonLinearAds;
import com.huawei.openalliance.ad.beans.vast.StaticResource;
import com.huawei.openalliance.ad.beans.vast.Tracking;
import com.huawei.openalliance.ad.beans.vast.VastContent;
import com.huawei.openalliance.ad.beans.vast.VastIcon;
import com.huawei.openalliance.ad.beans.vast.VastMediaFile;
import com.huawei.openalliance.ad.beans.vast.VideoClicks;
import com.huawei.openalliance.ad.constant.EventType;
import com.huawei.openalliance.ad.constant.MetaCreativeType;
import com.huawei.openalliance.ad.constant.MimeType;
import com.huawei.openalliance.ad.constant.VastTag;
import com.huawei.openalliance.ad.constant.VastVersion;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.db.bean.EncryptionField;
import com.huawei.openalliance.ad.inter.HiAd;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes5.dex */
public class ps {

    /* renamed from: a, reason: collision with root package name */
    private static Map<String, EventType> f7458a;

    private static List<ImageInfo> c(NonLinearAds nonLinearAds) {
        List<NonLinear> b;
        ImageInfo a2;
        if (nonLinearAds == null || (b = nonLinearAds.b()) == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (NonLinear nonLinear : b) {
            if (nonLinear != null && (a2 = a(nonLinear.a())) != null) {
                arrayList.add(a2);
            }
        }
        return arrayList;
    }

    private static String c(LinearCreative linearCreative) {
        VideoClicks e;
        ClickThrough a2;
        if (linearCreative == null || (e = linearCreative.e()) == null || (a2 = e.a()) == null) {
            return null;
        }
        return a2.a();
    }

    private static List<Monitor> b(ContentRecord contentRecord) {
        EncryptionField<List<Monitor>> K;
        if (contentRecord == null || (K = contentRecord.K()) == null) {
            return null;
        }
        return K.a(HiAd.a().d());
    }

    private static List<ImageInfo> b(LinearCreative linearCreative) {
        if (linearCreative == null) {
            return null;
        }
        List<VastIcon> d = linearCreative.d();
        if (com.huawei.openalliance.ad.utils.bg.a(d)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (VastIcon vastIcon : d) {
            ImageInfo a2 = a(vastIcon.c());
            if (a2 != null) {
                a2.b(vastIcon.a());
                a2.a(vastIcon.b());
                arrayList.add(a2);
            }
        }
        return arrayList;
    }

    private static String b(NonLinearAds nonLinearAds) {
        NonLinear nonLinear;
        if (nonLinearAds == null) {
            return null;
        }
        List<NonLinear> b = nonLinearAds.b();
        if (com.huawei.openalliance.ad.utils.bg.a(b) || (nonLinear = b.get(0)) == null) {
            return null;
        }
        return nonLinear.b();
    }

    private static MediaFile b(VastMediaFile vastMediaFile, MetaData metaData) {
        if (vastMediaFile == null) {
            return null;
        }
        MediaFile mediaFile = metaData.u() == null ? new MediaFile() : metaData.u();
        mediaFile.b(vastMediaFile.d());
        mediaFile.b(vastMediaFile.c());
        mediaFile.a(vastMediaFile.b());
        mediaFile.e(2);
        return mediaFile;
    }

    private static boolean a(NonLinearAds nonLinearAds) {
        if (!com.huawei.openalliance.ad.utils.bg.a(nonLinearAds.b())) {
            return true;
        }
        ho.b("VastAdapter", "no nonlinear ads in nonlinear creative, skip parse vastInfo.");
        return false;
    }

    private static boolean a(LinearCreative linearCreative, NonLinearAds nonLinearAds) {
        return a(linearCreative) && a(nonLinearAds);
    }

    private static boolean a(LinearCreative linearCreative) {
        if (!com.huawei.openalliance.ad.utils.bg.a(linearCreative.b())) {
            return true;
        }
        ho.b("VastAdapter", "no media file in linear creative, skip parse vastInfo.");
        return false;
    }

    private static boolean a(int i, int i2, LinearCreative linearCreative) {
        if (i != 3) {
            return i == 7 && com.huawei.openalliance.ad.utils.bc.f(i2);
        }
        if (9 == i2 || 12 == i2 || 106 == i2 || 6 == i2) {
            return true;
        }
        return linearCreative != null && a(linearCreative);
    }

    private static void a(Map<String, Monitor> map, List<Monitor> list) {
        if (com.huawei.openalliance.ad.utils.bg.a(list) || map == null) {
            return;
        }
        for (Monitor monitor : list) {
            if (monitor != null) {
                String a2 = monitor.a();
                Monitor monitor2 = map.get(a2);
                if (monitor2 != null) {
                    HashSet hashSet = new HashSet(monitor2.b());
                    hashSet.addAll(monitor.b());
                    monitor2.a(new ArrayList(hashSet));
                    map.put(a2, monitor2);
                } else {
                    map.put(monitor.a(), monitor);
                }
            }
        }
    }

    private static void a(List<Monitor> list, Map<String, List<Tracking>> map, String str, EventType eventType) {
        if (list == null || com.huawei.openalliance.ad.utils.bl.a(map) || TextUtils.isEmpty(str) || eventType == null || !map.containsKey(str)) {
            return;
        }
        List<Tracking> list2 = map.get(str);
        if (com.huawei.openalliance.ad.utils.bg.a(list2)) {
            return;
        }
        list.add(a(list2, eventType));
    }

    public static void a(ContentRecord contentRecord) {
        if (contentRecord == null) {
            return;
        }
        MetaData h = contentRecord.h();
        int e = contentRecord.e();
        VastContent a2 = a(h, e, contentRecord.D());
        a(h, a2, e);
        contentRecord.b(com.huawei.openalliance.ad.utils.be.b(h));
        a(a2, contentRecord);
    }

    private static void a(VastContent vastContent, ContentRecord contentRecord) {
        List<ImageInfo> o;
        int e;
        if (vastContent == null || contentRecord == null) {
            return;
        }
        contentRecord.d(true);
        MetaData h = contentRecord.h();
        if (h != null) {
            contentRecord.k(h.h());
        }
        List<Monitor> a2 = a(vastContent);
        if (ho.a()) {
            ho.a("VastAdapter", "monitors from vast: %s", com.huawei.openalliance.ad.utils.dl.a(com.huawei.openalliance.ad.utils.be.b(a2)));
        }
        if (!com.huawei.openalliance.ad.utils.bg.a(a2)) {
            List<Monitor> b = b(contentRecord);
            if (!com.huawei.openalliance.ad.utils.bg.a(b)) {
                a2 = a(b, a2);
            }
            contentRecord.c(a2);
        }
        VideoInfo R = contentRecord.R();
        if (R != null) {
            Float m = R.m();
            if (m == null) {
                return;
            }
            e = (int) ((720 * 1.0f) / m.floatValue());
            contentRecord.f(720);
        } else {
            if (h == null || (o = h.o()) == null || o.size() <= 0) {
                return;
            }
            ImageInfo imageInfo = o.get(0);
            contentRecord.i(imageInfo.c());
            contentRecord.f(imageInfo.d());
            e = imageInfo.e();
        }
        contentRecord.g(e);
    }

    public static void a(MetaData metaData, VastContent vastContent, int i, boolean z) {
        if (metaData == null || vastContent == null) {
            return;
        }
        List<Creative> e = vastContent.e();
        if (com.huawei.openalliance.ad.utils.bg.a(e)) {
            return;
        }
        Creative creative = e.get(0);
        if (creative == null) {
            ho.b("VastAdapter", "vast creative empty, skip merge.");
            return;
        }
        ho.a("VastAdapter", "merge metadata.");
        a(metaData, creative.b());
        a(metaData, i, creative.a());
        if (!TextUtils.isEmpty(vastContent.c())) {
            metaData.c(vastContent.c());
        }
        if (!TextUtils.isEmpty(vastContent.f())) {
            metaData.e(vastContent.f());
        }
        if (!TextUtils.isEmpty(vastContent.b())) {
            metaData.b(vastContent.b());
        }
        if (z) {
            metaData.q(null);
        }
    }

    public static void a(MetaData metaData, VastContent vastContent, int i) {
        a(metaData, vastContent, i, true);
    }

    private static void a(MetaData metaData, NonLinearAds nonLinearAds) {
        List<ImageInfo> c = c(nonLinearAds);
        if (!com.huawei.openalliance.ad.utils.bg.a(c)) {
            Iterator<ImageInfo> it = c.iterator();
            while (it.hasNext()) {
                it.next().d(1);
            }
            metaData.b(c);
        }
        String b = b(nonLinearAds);
        if (TextUtils.isEmpty(b)) {
            return;
        }
        metaData.d(b);
    }

    private static void a(MetaData metaData, int i, LinearCreative linearCreative) {
        VideoInfo a2;
        MediaFile b;
        VastMediaFile a3 = a(linearCreative.b());
        if (a3 != null && 60 == i && (b = b(a3, metaData)) != null) {
            metaData.a(b);
            if (linearCreative.a() > 0) {
                metaData.b(linearCreative.a());
            }
        }
        if (a3 != null && 60 != i && (a2 = a(a3, metaData)) != null) {
            metaData.a(a2);
            if (linearCreative.a() > 0) {
                a2.a(linearCreative.a());
                metaData.b(linearCreative.a());
            }
        }
        String c = c(linearCreative);
        if (!TextUtils.isEmpty(c)) {
            metaData.d(c);
        }
        List<ImageInfo> b2 = b(linearCreative);
        if (com.huawei.openalliance.ad.utils.bg.a(b2)) {
            return;
        }
        metaData.a(b2);
    }

    private static XmlPullParser a(InputStream inputStream) {
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", false);
        newPullParser.setInput(inputStream, "utf-8");
        newPullParser.nextTag();
        newPullParser.require(2, VastTag.NAMESPACE, VastTag.VAST);
        return newPullParser;
    }

    public static List<Monitor> a(List<Monitor> list, List<Monitor> list2) {
        HashMap hashMap = new HashMap();
        a(hashMap, list);
        a(hashMap, list2);
        return com.huawei.openalliance.ad.utils.bl.a(hashMap) ? Collections.EMPTY_LIST : new ArrayList(hashMap.values());
    }

    public static List<Monitor> a(VastContent vastContent) {
        ArrayList arrayList = null;
        if (vastContent == null) {
            return null;
        }
        List<Creative> e = vastContent.e();
        if (!com.huawei.openalliance.ad.utils.bg.a(e) && e.size() <= 1) {
            Creative creative = e.get(0);
            if (creative == null) {
                return null;
            }
            arrayList = new ArrayList();
            List<Impression> d = vastContent.d();
            if (!com.huawei.openalliance.ad.utils.bg.a(d)) {
                Monitor monitor = new Monitor();
                monitor.a(EventType.SHOW.value());
                ArrayList arrayList2 = new ArrayList();
                Iterator<Impression> it = d.iterator();
                while (it.hasNext()) {
                    arrayList2.add(it.next().a());
                }
                monitor.a(arrayList2);
                arrayList.add(monitor);
            }
            if (creative.a() != null && creative.a().e() != null) {
                List<Tracking> b = creative.a().e().b();
                if (!com.huawei.openalliance.ad.utils.bg.a(b)) {
                    arrayList.add(a(b, EventType.CLICK));
                }
            }
            HashMap hashMap = new HashMap();
            if (creative.a() != null && !com.huawei.openalliance.ad.utils.bl.a(creative.a().c())) {
                hashMap.putAll(creative.a().c());
            }
            if (creative.b() != null && !com.huawei.openalliance.ad.utils.bl.a(creative.b().a())) {
                hashMap.putAll(creative.b().a());
            }
            if (com.huawei.openalliance.ad.utils.bl.a(hashMap)) {
                return arrayList;
            }
            for (Map.Entry<String, EventType> entry : f7458a.entrySet()) {
                a(arrayList, hashMap, entry.getKey(), entry.getValue());
            }
        }
        return arrayList;
    }

    private static VastVersion a(XmlPullParser xmlPullParser) {
        String attributeValue = xmlPullParser.getAttributeValue(VastTag.NAMESPACE, "version");
        if (com.huawei.openalliance.ad.utils.cz.b(attributeValue)) {
            ho.d("VastAdapter", "Required version attribute in VAST tag");
        }
        return VastVersion.getVerFromStr(attributeValue);
    }

    private static VastMediaFile a(List<VastMediaFile> list) {
        VastMediaFile vastMediaFile;
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            return null;
        }
        Iterator<VastMediaFile> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                vastMediaFile = null;
                break;
            }
            vastMediaFile = it.next();
            if (vastMediaFile != null && "video/mp4".equalsIgnoreCase(vastMediaFile.a())) {
                break;
            }
        }
        if (vastMediaFile != null && !TextUtils.isEmpty(vastMediaFile.d()) && vastMediaFile.c() != 0 && vastMediaFile.b() != 0) {
            return vastMediaFile;
        }
        ho.b("VastAdapter", "vast mediaFile missing required attribute.");
        return null;
    }

    public static VastContent a(MetaData metaData, int i, int i2) {
        ByteArrayInputStream byteArrayInputStream;
        if (metaData == null) {
            return null;
        }
        String J = metaData.J();
        if (TextUtils.isEmpty(J)) {
            return null;
        }
        if (ho.a()) {
            ho.a("VastAdapter", "parse vastInfo.");
        }
        try {
            byteArrayInputStream = new ByteArrayInputStream(J.getBytes("UTF-8"));
            try {
                XmlPullParser a2 = a(byteArrayInputStream);
                ri.a(a(a2));
                List<VastContent> a3 = ri.a().a(a2);
                if (com.huawei.openalliance.ad.utils.bg.a(a3)) {
                    ho.b("VastAdapter", "parse vast info but vast content is empty");
                    return null;
                }
                if (a3.size() > 1) {
                    ho.b("VastAdapter", "multi vast content, skip parse vastInfo.");
                    return null;
                }
                VastContent vastContent = a3.get(0);
                List<Creative> e = vastContent.e();
                if (com.huawei.openalliance.ad.utils.bg.a(e)) {
                    ho.b("VastAdapter", "vast creatives empty, skip parse vastInfo.");
                    return null;
                }
                if (e.size() > 1) {
                    ho.b("VastAdapter", "multi vast creative, skip parse vastInfo.");
                    return null;
                }
                Creative creative = e.get(0);
                if (creative == null) {
                    ho.b("VastAdapter", "creative null, skip parse vastInfo.");
                    return null;
                }
                LinearCreative a4 = creative.a();
                NonLinearAds b = creative.b();
                if (a(i, i2, a4)) {
                    if (a4 != null && b != null) {
                        if (!a(a4, b)) {
                            ho.b("VastAdapter", "check ads with linear and nonlinear failed, skip parse vastInfo.");
                            return null;
                        }
                    }
                    ho.b("VastAdapter", "need both linear and nonlinear ad, skip parse vastInfo.");
                    return null;
                }
                if (!a(a4)) {
                    ho.b("VastAdapter", "not valid linear creative, skip parse vastInfo.");
                    return null;
                }
                return vastContent;
            } catch (Throwable th) {
                th = th;
                try {
                    ho.c("VastAdapter", "parseVastInfo error: %s", th.getClass().getSimpleName());
                    return null;
                } finally {
                    com.huawei.openalliance.ad.utils.cy.a((Closeable) byteArrayInputStream);
                }
            }
        } catch (Throwable th2) {
            th = th2;
            byteArrayInputStream = null;
        }
    }

    private static VideoInfo a(VastMediaFile vastMediaFile, MetaData metaData) {
        if (vastMediaFile == null) {
            return null;
        }
        VideoInfo videoInfo = metaData.d() == null ? new VideoInfo() : metaData.d();
        videoInfo.a(vastMediaFile.d());
        if (vastMediaFile.c() != 0) {
            videoInfo.a(Float.valueOf(vastMediaFile.b() / vastMediaFile.c()));
        }
        videoInfo.e(1);
        videoInfo.b(0);
        return videoInfo;
    }

    private static Monitor a(List<Tracking> list, EventType eventType) {
        if (com.huawei.openalliance.ad.utils.bg.a(list) || eventType == null) {
            return null;
        }
        Monitor monitor = new Monitor();
        monitor.a(eventType.value());
        ArrayList arrayList = new ArrayList();
        Iterator<Tracking> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().a());
        }
        monitor.a(arrayList);
        return monitor;
    }

    private static ImageInfo a(StaticResource staticResource) {
        if (staticResource == null || TextUtils.isEmpty(staticResource.b())) {
            return null;
        }
        ImageInfo imageInfo = new ImageInfo();
        imageInfo.c(staticResource.b());
        imageInfo.b(MimeType.GIF.equalsIgnoreCase(staticResource.a()) ? MetaCreativeType.GIF : "img");
        imageInfo.d(1);
        return imageInfo;
    }

    static {
        HashMap hashMap = new HashMap();
        f7458a = hashMap;
        hashMap.put("mute", EventType.SOUNDCLICKOFF);
        f7458a.put("complete", EventType.VASTPLAYCOMPLETE);
        f7458a.put("start", EventType.VASTPLAYSTART);
        f7458a.put("unmute", EventType.SOUNDCLICKON);
        f7458a.put("skip", EventType.CLOSE);
        f7458a.put("firstQuartile", EventType.VASTFIRSTQURAT);
        f7458a.put("midpoint", EventType.VASTMIDPOINT);
        f7458a.put("thirdQuartile", EventType.VASTTHIRDQUART);
    }
}
