package defpackage;

import android.text.TextUtils;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* loaded from: classes3.dex */
public class dea {
    public static void c(DocumentBuilderFactory documentBuilderFactory) throws ParserConfigurationException {
        if (documentBuilderFactory == null) {
            LogUtil.a("XmlUtil", "setFeature builderFactory is null");
            return;
        }
        documentBuilderFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
        documentBuilderFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        documentBuilderFactory.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
        documentBuilderFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        documentBuilderFactory.setValidating(true);
    }

    public static String d(String str, String str2, String str3, String str4) {
        File file = new File(str2);
        String str5 = null;
        File[] listFiles = (file.exists() && file.isDirectory()) ? file.listFiles() : null;
        if (listFiles == null) {
            return null;
        }
        for (File file2 : listFiles) {
            String replace = file2.getName().replace("strings", "").replace(WatchFaceConstant.XML_SUFFIX, "");
            if (replace.contains(str4)) {
                if (replace.contains(str3)) {
                    return e(a(file2), str);
                }
                if (str5 == null) {
                    str5 = e(a(file2), str);
                }
            }
        }
        return str5 == null ? e(a(listFiles[listFiles.length - 1]), str) : str5;
    }

    private static String a(File file) {
        try {
            return file.getCanonicalPath();
        } catch (IOException unused) {
            LogUtil.b("XmlUtil", "IOException");
            return null;
        }
    }

    public static String e(String str, String str2) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        r3 = null;
        r3 = null;
        String str3 = null;
        try {
            try {
                XmlPullParserFactory newInstance = XmlPullParserFactory.newInstance();
                newInstance.setNamespaceAware(true);
                XmlPullParser newPullParser = newInstance.newPullParser();
                fileInputStream = new FileInputStream(str);
                try {
                    newPullParser.setInput(fileInputStream, "UTF-8");
                    str3 = d(newPullParser, str2);
                } catch (FileNotFoundException unused) {
                    LogUtil.b("XmlUtil", "not find xml file");
                    FileUtils.d(fileInputStream);
                    return str3;
                } catch (IOException unused2) {
                    LogUtil.b("XmlUtil", "not read text");
                    FileUtils.d(fileInputStream);
                    return str3;
                } catch (XmlPullParserException unused3) {
                    LogUtil.b("XmlUtil", "xml pull error");
                    FileUtils.d(fileInputStream);
                    return str3;
                }
            } catch (Throwable th) {
                th = th;
                fileInputStream2 = fileInputStream;
                FileUtils.d(fileInputStream2);
                throw th;
            }
        } catch (FileNotFoundException unused4) {
            fileInputStream = null;
        } catch (IOException unused5) {
            fileInputStream = null;
        } catch (XmlPullParserException unused6) {
            fileInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            FileUtils.d(fileInputStream2);
            throw th;
        }
        FileUtils.d(fileInputStream);
        return str3;
    }

    private static String d(XmlPullParser xmlPullParser, String str) throws IOException, XmlPullParserException {
        int eventType = xmlPullParser.getEventType();
        String str2 = null;
        while (eventType != 1) {
            str2 = c(xmlPullParser, str, eventType);
            if (!TextUtils.isEmpty(str2)) {
                break;
            }
            eventType = xmlPullParser.next();
        }
        return str2;
    }

    private static String c(XmlPullParser xmlPullParser, String str, int i) throws IOException, XmlPullParserException {
        if (i == 2 && "string".equals(xmlPullParser.getName())) {
            return d(str, xmlPullParser, xmlPullParser.getAttributeCount());
        }
        return null;
    }

    private static String d(String str, XmlPullParser xmlPullParser, int i) throws IOException, XmlPullParserException {
        if (i == 1 && str.equals(xmlPullParser.getAttributeValue(0))) {
            return xmlPullParser.nextText();
        }
        return null;
    }
}
