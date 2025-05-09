package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* loaded from: classes5.dex */
public class kyj {
    public static kxl a(InputStream inputStream, kxl kxlVar) {
        if (inputStream != null && kxlVar != null) {
            ArrayList arrayList = new ArrayList();
            try {
                XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
                newPullParser.setInput(inputStream, "UTF-8");
                kxl kxlVar2 = null;
                String str = "";
                for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
                    String name = newPullParser.getName();
                    if (eventType == 2) {
                        if ("file".equalsIgnoreCase(name)) {
                            LogUtil.c("BuildFileListXmlUtil", "enter file!!!");
                            kxl kxlVar3 = new kxl();
                            kxlVar3.i(str);
                            kxlVar2 = kxlVar3;
                        } else if ("name".equalsIgnoreCase(name)) {
                            str = newPullParser.nextText();
                        } else {
                            d(name, newPullParser, kxlVar2);
                        }
                    } else if (eventType == 3 && "file".equalsIgnoreCase(name) && kxlVar2 != null) {
                        arrayList.add(kxlVar2);
                    }
                }
            } catch (IOException e) {
                LogUtil.b("BuildFileListXmlUtil", "buildFileListXml, IOException", e.getMessage());
            } catch (XmlPullParserException e2) {
                LogUtil.b("BuildFileListXmlUtil", "buildFileListXml, XmlPullParserException", e2.getMessage());
            } catch (Exception unused) {
                LogUtil.b("BuildFileListXmlUtil", "buildFileListXml, Exception");
            }
            b(kxlVar, arrayList);
            LogUtil.a("BuildFileListXmlUtil", "buildFileListXml end newVersionInfo=", kxlVar.toString());
        }
        return kxlVar;
    }

    private static void d(String str, XmlPullParser xmlPullParser, kxl kxlVar) throws XmlPullParserException, IOException {
        e(str, xmlPullParser, kxlVar);
        b(str, xmlPullParser, kxlVar);
    }

    private static void b(String str, XmlPullParser xmlPullParser, kxl kxlVar) throws XmlPullParserException, IOException {
        if (kxlVar != null) {
            c(str, xmlPullParser, kxlVar);
            a(str, xmlPullParser, kxlVar);
        }
    }

    private static void c(String str, XmlPullParser xmlPullParser, kxl kxlVar) throws XmlPullParserException, IOException {
        if ("packageName".equalsIgnoreCase(str)) {
            kxlVar.n(xmlPullParser.nextText());
            return;
        }
        if ("versionName".equalsIgnoreCase(str)) {
            kxlVar.s(xmlPullParser.nextText());
        } else if ("versionCode".equalsIgnoreCase(str)) {
            kxlVar.p(xmlPullParser.nextText());
        } else {
            LogUtil.h("BuildFileListXmlUtil", "setPackageAndVersion unknown node: ", str);
        }
    }

    private static void a(String str, XmlPullParser xmlPullParser, kxl kxlVar) throws XmlPullParserException, IOException {
        if ("versionType".equalsIgnoreCase(str)) {
            kxlVar.w(xmlPullParser.nextText());
        } else if ("osVersion".equalsIgnoreCase(str)) {
            kxlVar.l(xmlPullParser.nextText());
        } else {
            LogUtil.h("BuildFileListXmlUtil", "setPartApplicationInfo unknown node: ", str);
        }
    }

    private static void e(String str, XmlPullParser xmlPullParser, kxl kxlVar) throws XmlPullParserException, IOException {
        if (kxlVar != null) {
            if ("spath".equalsIgnoreCase(str)) {
                kxlVar.k(xmlPullParser.nextText());
                return;
            }
            if ("dpath".equalsIgnoreCase(str)) {
                kxlVar.b(xmlPullParser.nextText());
                return;
            }
            if ("md5".equalsIgnoreCase(str)) {
                kxlVar.f(xmlPullParser.nextText());
                return;
            }
            if ("newmd5".equalsIgnoreCase(str)) {
                kxlVar.h(xmlPullParser.nextText());
                return;
            }
            if ("sha256".equalsIgnoreCase(str)) {
                kxlVar.o(xmlPullParser.nextText());
            } else if ("newsize".equalsIgnoreCase(str)) {
                kxlVar.a(Long.parseLong(xmlPullParser.nextText()));
            } else if ("size".equalsIgnoreCase(str)) {
                kxlVar.e(Long.parseLong(xmlPullParser.nextText()));
            }
        }
    }

    private static void b(kxl kxlVar, ArrayList<kxl> arrayList) {
        int size = arrayList.size();
        LogUtil.a("BuildFileListXmlUtil", "makeList size=", Integer.valueOf(size));
        for (int i = 0; i < size; i++) {
            kxl kxlVar2 = arrayList.get(i);
            if (kxlVar2 != null && !TextUtils.isEmpty(kxlVar2.r())) {
                LogUtil.a("BuildFileListXmlUtil", "makeList appInfo = ", kxlVar2);
                if (kxlVar2.r().endsWith(".apk") || kxlVar2.r().endsWith(".delta") || kxlVar2.r().endsWith(".zip")) {
                    kxlVar.i(kxlVar2.m());
                    kxlVar.k(kxlVar2.r());
                    kxlVar.b(kxlVar2.g());
                    kxlVar.f(kxlVar2.h());
                    kxlVar.h(kxlVar2.l());
                    kxlVar.o(kxlVar2.s());
                    kxlVar.e(kxlVar2.c());
                    kxlVar.a(kxlVar2.o());
                    kxlVar.n(kxlVar2.q());
                    kxlVar.s(kxlVar2.v());
                    kxlVar.p(kxlVar2.x());
                    kxlVar.r(kxlVar2.u());
                    kxlVar.w(kxlVar2.ac());
                    kxlVar.l(kxlVar2.k());
                    LogUtil.a("BuildFileListXmlUtil", "makeList find apk! ", kxlVar.toString());
                    return;
                }
            }
        }
    }
}
