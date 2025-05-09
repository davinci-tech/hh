package defpackage;

import android.content.Context;
import android.content.res.XmlResourceParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes7.dex */
public class svs {
    private String b = null;
    private String c = null;
    private String d = null;

    /* renamed from: a, reason: collision with root package name */
    private svh f17255a = null;
    private List<svh> e = null;
    private Map<String, List<svh>> j = new HashMap();

    public void b(Context context, Map<String, List<svh>> map, int i) {
        try {
            eXO_(context, map, context.getResources().getXml(i));
        } catch (Exception unused) {
            stq.e("AddressNameMgr", "XmlParseUtil,parseXml-xmlRes exception", false);
        }
    }

    private void eXO_(Context context, Map<String, List<svh>> map, XmlResourceParser xmlResourceParser) {
        this.j = map;
        if (xmlResourceParser == null) {
            return;
        }
        try {
            int eventType = xmlResourceParser.getEventType();
            while (eventType != 1) {
                if (eventType == 2) {
                    b(xmlResourceParser);
                } else if (eventType == 3) {
                    eXN_(xmlResourceParser);
                }
                eventType = xmlResourceParser.next();
            }
        } catch (IOException | IndexOutOfBoundsException | XmlPullParserException unused) {
            stq.e("AddressNameMgr", " Process address mapping xml file failed.", null, false);
        }
    }

    private void eXN_(XmlResourceParser xmlResourceParser) {
        svh svhVar;
        if ("Module".equals(xmlResourceParser.getName())) {
            this.j.put(this.b, this.e);
            this.e = null;
        }
        if ("Param".equals(xmlResourceParser.getName()) && (svhVar = this.f17255a) != null) {
            svhVar.a(this.c, this.d);
        }
        if ("Address".equals(xmlResourceParser.getName())) {
            List<svh> list = this.e;
            if (list != null) {
                list.add(this.f17255a);
            }
            this.f17255a = null;
        }
    }

    private void b(XmlPullParser xmlPullParser) {
        if ("Module".equals(xmlPullParser.getName())) {
            this.b = xmlPullParser.getAttributeValue(null, "name");
            this.e = new ArrayList();
        } else if (this.e != null) {
            if ("Address".equals(xmlPullParser.getName())) {
                this.f17255a = new svh(xmlPullParser.getAttributeValue(null, "name"), xmlPullParser.getAttributeValue(null, "serverAddressName"));
            }
        } else {
            if (this.f17255a == null || !"Param".equals(xmlPullParser.getName())) {
                return;
            }
            this.c = xmlPullParser.getAttributeValue(null, "name");
            this.d = xmlPullParser.getAttributeValue(null, "value");
        }
    }
}
