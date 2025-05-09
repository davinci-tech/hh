package com.huawei.openalliance.ad;

import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.vast.Creative;
import com.huawei.openalliance.ad.beans.vast.StaticResource;
import com.huawei.openalliance.ad.beans.vast.VastContent;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.VastTag;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes5.dex */
public class rh {

    /* renamed from: a, reason: collision with root package name */
    private static final List<String> f7505a = Arrays.asList(VastTag.AD_SYSTEM, VastTag.AD_TITLE, VastTag.ADVERTISER, VastTag.DESCRIPTION, VastTag.IMPRESSION, VastTag.CREATIVES);
    private static final List<String> b = Arrays.asList(VastTag.AD_SYSTEM, VastTag.AD_TITLE, VastTag.ADVERTISER, VastTag.DESCRIPTION, VastTag.IMPRESSION, VastTag.CREATIVES);

    public interface a {
        void a();
    }

    private static void c(XmlPullParser xmlPullParser) {
        if (xmlPullParser == null || xmlPullParser.getEventType() != 2) {
            throw new IllegalStateException();
        }
        int i = 1;
        while (i != 0) {
            int next = xmlPullParser.next();
            if (next == 2) {
                i++;
            } else if (next == 3) {
                i--;
            }
        }
    }

    public static List<Creative> c(XmlPullParser xmlPullParser, VastContent vastContent) {
        ho.b("ParseUtil", "read creatives");
        xmlPullParser.require(2, VastTag.NAMESPACE, VastTag.CREATIVES);
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        hashMap.put(VastTag.CREATIVE, new rl(vastContent, xmlPullParser, arrayList));
        a(xmlPullParser, hashMap, (List<String>) Collections.singletonList(VastTag.CREATIVE));
        return arrayList;
    }

    public static void b(XmlPullParser xmlPullParser, VastContent vastContent) {
        a(xmlPullParser, vastContent, VastTag.INLINE, f7505a);
    }

    public static StaticResource b(XmlPullParser xmlPullParser) {
        if (xmlPullParser == null) {
            return null;
        }
        ho.a("ParseUtil", "start read static resource");
        StaticResource staticResource = new StaticResource();
        staticResource.a(a(xmlPullParser, "creativeType"));
        staticResource.b(a(xmlPullParser));
        ho.a("ParseUtil", "read static resource finish");
        return staticResource;
    }

    public static void a(XmlPullParser xmlPullParser, Map<String, a> map, List<String> list) {
        if (xmlPullParser == null || map == null || list == null) {
            return;
        }
        ArrayList arrayList = new ArrayList(list);
        while (xmlPullParser.next() != 3 && xmlPullParser.getEventType() != 1) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                Iterator it = arrayList.iterator();
                while (it.hasNext() && !((String) it.next()).equalsIgnoreCase(name)) {
                }
                if (map.containsKey(name)) {
                    a aVar = map.get(name);
                    if (aVar != null) {
                        aVar.a();
                    }
                } else {
                    c(xmlPullParser);
                }
            }
        }
    }

    private static void a(XmlPullParser xmlPullParser, VastContent vastContent, String str, List<String> list) {
        if (xmlPullParser == null || vastContent == null) {
            return;
        }
        xmlPullParser.require(2, VastTag.NAMESPACE, str);
        HashMap hashMap = new HashMap();
        a(hashMap, vastContent, xmlPullParser);
        a(xmlPullParser, hashMap, list);
    }

    public static void a(XmlPullParser xmlPullParser, VastContent vastContent) {
        a(xmlPullParser, vastContent, VastTag.WRAPPER, b);
    }

    private static void a(Map<String, a> map, VastContent vastContent, XmlPullParser xmlPullParser) {
        if (map == null || vastContent == null || xmlPullParser == null) {
            return;
        }
        for (String str : f7505a) {
            map.put(str, new rm(str, vastContent, xmlPullParser));
        }
    }

    private static SimpleDateFormat a(boolean z) {
        SimpleDateFormat simpleDateFormat = z ? new SimpleDateFormat(Constants.TIME_FORMAT_WITH_MILLS, Locale.ENGLISH) : new SimpleDateFormat(Constants.TIME_FORMAT_WITHOUT_MILLS, Locale.ENGLISH);
        simpleDateFormat.setTimeZone(Constants.GMT_STANDARD_TIME_ZONE);
        simpleDateFormat.setLenient(false);
        return simpleDateFormat;
    }

    public static String a(XmlPullParser xmlPullParser, String str) {
        if (xmlPullParser == null || TextUtils.isEmpty(str)) {
            return null;
        }
        String attributeValue = xmlPullParser.getAttributeValue(VastTag.NAMESPACE, str);
        if (!com.huawei.openalliance.ad.utils.cz.b(attributeValue)) {
            return attributeValue;
        }
        ho.c("ParseUtil", "missing required attribute: %s ", str);
        return null;
    }

    public static String a(XmlPullParser xmlPullParser) {
        if (xmlPullParser == null) {
            return null;
        }
        if (xmlPullParser.next() != 4) {
            return "";
        }
        String trim = xmlPullParser.getText().trim();
        xmlPullParser.nextTag();
        return trim;
    }

    public static String a(String str) {
        if (str == null) {
            ho.d("ParseUtil", "Parse time failed, source is empty");
            return null;
        }
        try {
            return String.valueOf(a(true).parse(str).getTime());
        } catch (ParseException unused) {
            try {
                return String.valueOf(a(false).parse(str).getTime());
            } catch (ParseException e) {
                ho.d("ParseUtil", "Parse time failed , time format is invalid", e);
                return null;
            }
        }
    }
}
