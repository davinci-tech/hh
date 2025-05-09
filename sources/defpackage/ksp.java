package defpackage;

import android.content.Context;
import com.huawei.hwidauth.utils.globalcfg.CountryInfoForCFG;
import com.huawei.hwidauth.utils.globalcfg.SiteInfoForCFG;
import com.huawei.openalliance.ad.constant.VideoPlayFlag;
import java.io.IOException;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes5.dex */
public class ksp {

    /* renamed from: a, reason: collision with root package name */
    private final String f14577a;
    private final String b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private final String g;
    private final String h;
    private final String i;
    private final String j;
    private final String k;
    private final String l;
    private final String m;
    private final String n;
    private final String o;
    private final String t;

    static class c {

        /* renamed from: a, reason: collision with root package name */
        public static ksp f14578a = new ksp();
    }

    private ksp() {
        this.b = VideoPlayFlag.PLAY_IN_ALL;
        this.f14577a = "country";
        this.c = "country-plus";
        this.d = "site-id";
        this.e = "name-zh";
        this.j = "name-en";
        this.i = "iso-2code";
        this.f = "tel-code";
        this.g = "mcc";
        this.h = "sms";
        this.k = "register";
        this.m = "site";
        this.o = "id";
        this.l = "name-zh";
        this.n = "name-en";
        this.t = "register";
    }

    public static ksp a() {
        return c.f14578a;
    }

    public List<CountryInfoForCFG> d(Context context, List<CountryInfoForCFG> list, List<CountryInfoForCFG> list2, List<SiteInfoForCFG> list3) {
        if (context == null) {
            ksy.d(this.b, "parseSiteCountryXML mContext == null", true);
            return list;
        }
        try {
            XmlPullParser d = ksu.d(context, ksu.a(context), ksu.c(context));
            for (int eventType = d.getEventType(); 1 != eventType; eventType = d.next()) {
                String name = d.getName();
                if (eventType == 2) {
                    c(d, list, list2, list3, name);
                }
            }
        } catch (IOException unused) {
            ksy.c(this.b, "parseXMLGetCountryList IOException", true);
        } catch (XmlPullParserException unused2) {
            ksy.c(this.b, "parseXMLGetCountryList XmlPullParserException", true);
        }
        return list;
    }

    private void d(XmlPullParser xmlPullParser, List<CountryInfoForCFG> list, List<CountryInfoForCFG> list2, String str) {
        CountryInfoForCFG countryInfoForCFG = new CountryInfoForCFG();
        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            String attributeValue = xmlPullParser.getAttributeValue(i) == null ? "" : xmlPullParser.getAttributeValue(i);
            if ("site-id".equals(attributeName)) {
                countryInfoForCFG.a(attributeValue);
            } else if ("name-zh".equals(attributeName)) {
                countryInfoForCFG.b(attributeValue);
            } else if ("name-en".equals(attributeName)) {
                countryInfoForCFG.c(attributeValue);
            } else if ("iso-2code".equals(attributeName)) {
                countryInfoForCFG.d(attributeValue);
            } else if ("tel-code".equals(attributeName)) {
                countryInfoForCFG.e(attributeValue);
            } else if ("mcc".equals(attributeName)) {
                countryInfoForCFG.f(attributeValue);
            } else if ("sms".equals(attributeName)) {
                countryInfoForCFG.g(attributeValue);
            } else if ("register".equals(attributeName)) {
                countryInfoForCFG.h(attributeValue);
            }
        }
        if ("country".equals(str)) {
            list.add(countryInfoForCFG);
        } else {
            list2.add(countryInfoForCFG);
        }
    }

    private void e(XmlPullParser xmlPullParser, List<SiteInfoForCFG> list) {
        SiteInfoForCFG siteInfoForCFG = new SiteInfoForCFG();
        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            String attributeValue = xmlPullParser.getAttributeValue(i) == null ? "" : xmlPullParser.getAttributeValue(i);
            if ("id".equals(attributeName)) {
                siteInfoForCFG.a(attributeValue);
            } else if ("name-zh".equals(attributeName)) {
                siteInfoForCFG.b(attributeValue);
            } else if ("name-en".equals(attributeName)) {
                siteInfoForCFG.c(attributeValue);
            } else if ("register".equals(attributeName)) {
                siteInfoForCFG.d(attributeValue);
            }
        }
        list.add(siteInfoForCFG);
    }

    private void c(XmlPullParser xmlPullParser, List<CountryInfoForCFG> list, List<CountryInfoForCFG> list2, List<SiteInfoForCFG> list3, String str) {
        if ("country".equals(str) || "country-plus".equals(str)) {
            d(xmlPullParser, list, list2, str);
        } else if ("site".equals(str)) {
            e(xmlPullParser, list3);
        }
    }
}
