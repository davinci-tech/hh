package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.IoUtils;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* loaded from: classes6.dex */
public class pup {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.io.Closeable, java.io.FileInputStream, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v13, types: [java.io.BufferedReader] */
    public static List<pur> d(String str) {
        Throwable th;
        InputStreamReader inputStreamReader;
        InputStreamReader inputStreamReader2;
        ?? r3;
        InputStreamReader inputStreamReader3;
        InputStreamReader inputStreamReader4;
        InputStreamReader inputStreamReader5;
        InputStreamReader inputStreamReader6;
        ?? r4;
        LogUtil.a("ParserHelper", "parseXml start");
        ArrayList arrayList = new ArrayList(10);
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("ParserHelper", "parseXml filePath is empty");
            return arrayList;
        }
        InputStreamReader inputStreamReader7 = null;
        try {
            r3 = new FileInputStream(str);
            try {
                inputStreamReader = new InputStreamReader((InputStream) r3, StandardCharsets.UTF_8);
                try {
                    r4 = new BufferedReader(inputStreamReader);
                    try {
                        b(arrayList, r4);
                        IoUtils.e((Closeable) r3);
                        inputStreamReader5 = r4;
                    } catch (IOException | XmlPullParserException unused) {
                        inputStreamReader7 = r4;
                        try {
                            LogUtil.b("ParserHelper", "parseXml XmlPullParserException | IOException");
                            IoUtils.e((Closeable) r3);
                            inputStreamReader5 = inputStreamReader7;
                            IoUtils.e(inputStreamReader);
                            IoUtils.e(inputStreamReader5);
                            LogUtil.a("ParserHelper", "parseXml end");
                            return arrayList;
                        } catch (Throwable th2) {
                            th = th2;
                            inputStreamReader3 = inputStreamReader7;
                            inputStreamReader4 = r3;
                            inputStreamReader7 = inputStreamReader4;
                            inputStreamReader2 = inputStreamReader3;
                            IoUtils.e(inputStreamReader7);
                            IoUtils.e(inputStreamReader);
                            IoUtils.e(inputStreamReader2);
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        inputStreamReader7 = inputStreamReader;
                        inputStreamReader6 = r4;
                        inputStreamReader = inputStreamReader7;
                        inputStreamReader4 = r3;
                        inputStreamReader3 = inputStreamReader6;
                        inputStreamReader7 = inputStreamReader4;
                        inputStreamReader2 = inputStreamReader3;
                        IoUtils.e(inputStreamReader7);
                        IoUtils.e(inputStreamReader);
                        IoUtils.e(inputStreamReader2);
                        throw th;
                    }
                } catch (IOException | XmlPullParserException unused2) {
                } catch (Throwable th4) {
                    th = th4;
                    r4 = 0;
                }
            } catch (IOException | XmlPullParserException unused3) {
                inputStreamReader = null;
            } catch (Throwable th5) {
                th = th5;
                inputStreamReader6 = null;
            }
        } catch (IOException | XmlPullParserException unused4) {
            inputStreamReader = null;
            r3 = 0;
        } catch (Throwable th6) {
            th = th6;
            inputStreamReader = null;
            inputStreamReader2 = null;
            IoUtils.e(inputStreamReader7);
            IoUtils.e(inputStreamReader);
            IoUtils.e(inputStreamReader2);
            throw th;
        }
        IoUtils.e(inputStreamReader);
        IoUtils.e(inputStreamReader5);
        LogUtil.a("ParserHelper", "parseXml end");
        return arrayList;
    }

    private static void b(List<pur> list, BufferedReader bufferedReader) throws IOException, XmlPullParserException {
        StringBuilder sb = new StringBuilder(16);
        while (true) {
            int read = bufferedReader.read();
            if (read == -1) {
                break;
            } else {
                sb.append((char) read);
            }
        }
        XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
        newPullParser.setInput(new StringReader(sb.toString()));
        for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
            if (eventType == 2) {
                if ("data".equals(newPullParser.getName())) {
                    pur purVar = new pur();
                    purVar.e(newPullParser.getAttributeValue("", "content"));
                    purVar.c(newPullParser.getAttributeValue("", "id"));
                    purVar.a(newPullParser.getAttributeValue("", "url"));
                    try {
                        purVar.c(Integer.parseInt(newPullParser.getAttributeValue("", "size")));
                    } catch (NumberFormatException unused) {
                        LogUtil.b("ParserHelper", "parseXmlData NumberFormatException");
                    }
                    purVar.d(newPullParser.getAttributeValue("", "name"));
                    list.add(purVar);
                } else {
                    LogUtil.a("ParserHelper", "XmlPullParser.START_TAG and parser.getName() not equals data");
                }
            }
        }
    }
}
