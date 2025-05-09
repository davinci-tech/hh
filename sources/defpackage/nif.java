package defpackage;

import android.content.Context;
import android.util.Log;
import com.huawei.openalliance.ad.constant.Constants;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.slf4j.Marker;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/* loaded from: classes6.dex */
public class nif {
    /* JADX WARN: Code restructure failed: missing block: B:11:0x004c, code lost:
    
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0050, code lost:
    
        android.util.Log.e("AarParseXml", "close inputstream error");
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x004a, code lost:
    
        if (r3 == null) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0040, code lost:
    
        if (r3 != null) goto L26;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.HashMap<java.lang.String, java.lang.String> e(java.lang.String r7, android.content.Context r8) {
        /*
            r6 = this;
            java.lang.String r0 = "close inputstream error"
            java.lang.String r1 = "AarParseXml"
            java.util.HashMap r2 = new java.util.HashMap
            r2.<init>()
            r3 = 0
            javax.xml.parsers.DocumentBuilderFactory r4 = javax.xml.parsers.DocumentBuilderFactory.newInstance()     // Catch: java.lang.Throwable -> L43 java.lang.Throwable -> L45
            d(r4)     // Catch: java.lang.Throwable -> L43 java.lang.Throwable -> L45
            javax.xml.parsers.DocumentBuilder r4 = r4.newDocumentBuilder()     // Catch: java.lang.Throwable -> L43 java.lang.Throwable -> L45
            java.lang.String r7 = e(r7)     // Catch: java.lang.Throwable -> L43 java.lang.Throwable -> L45
            android.content.res.AssetManager r5 = r8.getAssets()     // Catch: java.lang.Throwable -> L43 java.lang.Throwable -> L45
            java.lang.String[] r5 = r5.list(r7)     // Catch: java.lang.Throwable -> L43 java.lang.Throwable -> L45
            int r5 = r5.length     // Catch: java.lang.Throwable -> L43 java.lang.Throwable -> L45
            if (r5 == 0) goto L40
            android.content.res.AssetManager r8 = r8.getAssets()     // Catch: java.lang.Throwable -> L43 java.lang.Throwable -> L45
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L43 java.lang.Throwable -> L45
            r5.<init>()     // Catch: java.lang.Throwable -> L43 java.lang.Throwable -> L45
            r5.append(r7)     // Catch: java.lang.Throwable -> L43 java.lang.Throwable -> L45
            java.lang.String r7 = "/taboo-data.xml"
            r5.append(r7)     // Catch: java.lang.Throwable -> L43 java.lang.Throwable -> L45
            java.lang.String r7 = r5.toString()     // Catch: java.lang.Throwable -> L43 java.lang.Throwable -> L45
            java.io.InputStream r3 = r8.open(r7)     // Catch: java.lang.Throwable -> L43 java.lang.Throwable -> L45
            e(r2, r4, r3)     // Catch: java.lang.Throwable -> L43 java.lang.Throwable -> L45
        L40:
            if (r3 == 0) goto L53
            goto L4c
        L43:
            r7 = move-exception
            goto L54
        L45:
            java.lang.String r7 = "parse error"
            android.util.Log.e(r1, r7)     // Catch: java.lang.Throwable -> L43
            if (r3 == 0) goto L53
        L4c:
            r3.close()     // Catch: java.io.IOException -> L50
            goto L53
        L50:
            android.util.Log.e(r1, r0)
        L53:
            return r2
        L54:
            if (r3 == 0) goto L5d
            r3.close()     // Catch: java.io.IOException -> L5a
            goto L5d
        L5a:
            android.util.Log.e(r1, r0)
        L5d:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.nif.e(java.lang.String, android.content.Context):java.util.HashMap");
    }

    private static void d(DocumentBuilderFactory documentBuilderFactory) {
        if (documentBuilderFactory == null) {
            Log.e("AarParseXml", "setXmlEntityPolicy failed,builderFactory is null.");
            return;
        }
        c(documentBuilderFactory, "http://apache.org/xml/features/disallow-doctype-decl", true);
        c(documentBuilderFactory, "http://xml.org/sax/features/external-general-entities", false);
        c(documentBuilderFactory, "http://xml.org/sax/features/external-parameter-entities", false);
        c(documentBuilderFactory, "http://javax.xml.XMLConstants/feature/secure-processing", true);
        documentBuilderFactory.setExpandEntityReferences(false);
    }

    private static void c(DocumentBuilderFactory documentBuilderFactory, String str, boolean z) {
        if (documentBuilderFactory == null || str == null) {
            Log.e("AarParseXml", "setXmlSecurityFeature failed, builderFactory = " + documentBuilderFactory + ", featureName = " + str);
            return;
        }
        try {
            documentBuilderFactory.setFeature(str, z);
        } catch (ParserConfigurationException unused) {
            Log.e("AarParseXml", "setXmlSecurityFeature occur ParserConfigurationException: set " + str + " to " + z + " failed.");
        }
    }

    public static ArrayList<String> d(Context context) {
        String str;
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            for (String str2 : context.getAssets().list("tabooxml")) {
                if (str2.startsWith("xml")) {
                    int indexOf = str2.indexOf(Constants.LINK);
                    if (indexOf != -1) {
                        str = str2.substring(indexOf + 1);
                        if (str.contains(Marker.ANY_NON_NULL_MARKER)) {
                            str = str.replace("b+", "").replace(Marker.ANY_NON_NULL_MARKER, Constants.LINK);
                        } else if (str.contains(Constants.LINK)) {
                            str = str.replace("-r", Constants.LINK);
                        }
                    } else {
                        str = "en";
                    }
                    arrayList.add(str);
                }
            }
        } catch (IOException unused) {
            Log.e("AarParseXml", "getXmlLanguageList error");
        }
        return arrayList;
    }

    private static String e(String str) {
        Locale forLanguageTag = Locale.forLanguageTag(str);
        String script = forLanguageTag.getScript();
        String language = forLanguageTag.getLanguage();
        String country = forLanguageTag.getCountry();
        String replaceAll = str.replaceAll(Constants.LINK, Marker.ANY_NON_NULL_MARKER);
        if ("".equals(language)) {
            return "";
        }
        if (!script.isEmpty() || country.length() > 2) {
            return "tabooxml/xml-b+" + replaceAll;
        }
        if (script.isEmpty() && country.isEmpty() && language.equals("en")) {
            return "tabooxml/xml";
        }
        String str2 = "tabooxml/xml-" + language;
        if (country.isEmpty()) {
            return str2;
        }
        return str2 + "-r" + country;
    }

    private static void e(HashMap<String, String> hashMap, DocumentBuilder documentBuilder, InputStream inputStream) throws SAXException, IOException {
        NodeList elementsByTagName;
        Document parse = documentBuilder.parse(inputStream);
        if (parse == null || (elementsByTagName = parse.getElementsByTagName("item")) == null) {
            return;
        }
        int length = elementsByTagName.getLength();
        for (int i = 0; i < length; i++) {
            NamedNodeMap attributes = elementsByTagName.item(i).getAttributes();
            hashMap.put(attributes.getNamedItem("name").getNodeValue(), attributes.getNamedItem("value").getNodeValue());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0032, code lost:
    
        android.util.Log.e("AarParseXml", "close inputstream error");
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002c, code lost:
    
        if (r3 == null) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0022, code lost:
    
        if (r3 != null) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x002e, code lost:
    
        r3.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.HashMap<java.lang.String, java.lang.String> b(android.content.Context r6) {
        /*
            java.lang.String r0 = "close inputstream error"
            java.lang.String r1 = "AarParseXml"
            java.util.HashMap r2 = new java.util.HashMap
            r2.<init>()
            r3 = 0
            javax.xml.parsers.DocumentBuilderFactory r4 = javax.xml.parsers.DocumentBuilderFactory.newInstance()     // Catch: java.lang.Throwable -> L25 java.lang.Throwable -> L27
            d(r4)     // Catch: java.lang.Throwable -> L25 java.lang.Throwable -> L27
            javax.xml.parsers.DocumentBuilder r4 = r4.newDocumentBuilder()     // Catch: java.lang.Throwable -> L25 java.lang.Throwable -> L27
            android.content.res.AssetManager r6 = r6.getAssets()     // Catch: java.lang.Throwable -> L25 java.lang.Throwable -> L27
            java.lang.String r5 = "tabooxml/taboo-config.xml"
            java.io.InputStream r3 = r6.open(r5)     // Catch: java.lang.Throwable -> L25 java.lang.Throwable -> L27
            e(r2, r4, r3)     // Catch: java.lang.Throwable -> L25 java.lang.Throwable -> L27
            if (r3 == 0) goto L35
            goto L2e
        L25:
            r6 = move-exception
            goto L36
        L27:
            java.lang.String r6 = "parseConfigXml: error"
            android.util.Log.e(r1, r6)     // Catch: java.lang.Throwable -> L25
            if (r3 == 0) goto L35
        L2e:
            r3.close()     // Catch: java.io.IOException -> L32
            goto L35
        L32:
            android.util.Log.e(r1, r0)
        L35:
            return r2
        L36:
            if (r3 == 0) goto L3f
            r3.close()     // Catch: java.io.IOException -> L3c
            goto L3f
        L3c:
            android.util.Log.e(r1, r0)
        L3f:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.nif.b(android.content.Context):java.util.HashMap");
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0032, code lost:
    
        android.util.Log.e("AarParseXml", "close inputstream error");
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002c, code lost:
    
        if (r3 == null) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0022, code lost:
    
        if (r3 != null) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x002e, code lost:
    
        r3.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.HashMap<java.lang.String, java.lang.String> e(android.content.Context r6) {
        /*
            java.lang.String r0 = "close inputstream error"
            java.lang.String r1 = "AarParseXml"
            java.util.HashMap r2 = new java.util.HashMap
            r2.<init>()
            r3 = 0
            javax.xml.parsers.DocumentBuilderFactory r4 = javax.xml.parsers.DocumentBuilderFactory.newInstance()     // Catch: java.lang.Throwable -> L25 java.lang.Throwable -> L27
            d(r4)     // Catch: java.lang.Throwable -> L25 java.lang.Throwable -> L27
            javax.xml.parsers.DocumentBuilder r4 = r4.newDocumentBuilder()     // Catch: java.lang.Throwable -> L25 java.lang.Throwable -> L27
            android.content.res.AssetManager r6 = r6.getAssets()     // Catch: java.lang.Throwable -> L25 java.lang.Throwable -> L27
            java.lang.String r5 = "tabooxml/taboo-region.xml"
            java.io.InputStream r3 = r6.open(r5)     // Catch: java.lang.Throwable -> L25 java.lang.Throwable -> L27
            e(r2, r4, r3)     // Catch: java.lang.Throwable -> L25 java.lang.Throwable -> L27
            if (r3 == 0) goto L35
            goto L2e
        L25:
            r6 = move-exception
            goto L36
        L27:
            java.lang.String r6 = "parseConfigXml error"
            android.util.Log.e(r1, r6)     // Catch: java.lang.Throwable -> L25
            if (r3 == 0) goto L35
        L2e:
            r3.close()     // Catch: java.io.IOException -> L32
            goto L35
        L32:
            android.util.Log.e(r1, r0)
        L35:
            return r2
        L36:
            if (r3 == 0) goto L3f
            r3.close()     // Catch: java.io.IOException -> L3c
            goto L3f
        L3c:
            android.util.Log.e(r1, r0)
        L3f:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.nif.e(android.content.Context):java.util.HashMap");
    }
}
