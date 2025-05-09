package defpackage;

import android.os.Build;
import android.util.Xml;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes6.dex */
public class pvo {
    private static String e;

    static {
        try {
            e = BaseApplication.getContext().getFilesDir().getCanonicalPath() + File.separator + "healthdevice" + File.separator + "case_phone.xml";
        } catch (IOException unused) {
            LogUtil.b("IOException", new Object[0]);
        }
    }

    public static boolean a() {
        List<b> d = d();
        if (d == null) {
            LogUtil.h("CasePhones", "phoneList is null");
            return false;
        }
        LogUtil.a("CasePhones", "phoneList Size = ", Integer.valueOf(d.size()));
        String str = Build.BRAND + Constants.LINK + Build.MODEL;
        if (str.length() < 6) {
            LogUtil.a("CasePhones", "my phone length < 6, my phone length = ", Integer.valueOf(str.length()));
            return false;
        }
        String upperCase = str.replace(" ", "").toUpperCase(Locale.ENGLISH);
        LogUtil.a("CasePhones", "my phone type toUpperCase = ", upperCase);
        for (b bVar : d) {
            String str2 = bVar.a() + Constants.LINK + bVar.b();
            LogUtil.a("CasePhones", "my local_phone = ", str2);
            if (str2.toUpperCase(Locale.ENGLISH).equals(upperCase)) {
                LogUtil.a("CasePhones", "my local_phone = pnoneName ");
                return true;
            }
            String substring = upperCase.substring(0, upperCase.length() - 5);
            LogUtil.a("CasePhones", "my changePhone type = ", substring);
            if (substring.equals(str2)) {
                LogUtil.a("CasePhones", "my changePhone type = local_phone");
                return true;
            }
        }
        return false;
    }

    public static List<b> d() {
        File file = new File(e);
        List<b> list = null;
        if (!file.exists()) {
            LogUtil.h("CasePhones", "XML not found");
            return null;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                list = c(fileInputStream);
                fileInputStream.close();
            } finally {
            }
        } catch (IOException unused) {
            LogUtil.b("CasePhones", "I have cathc an io exception");
        } catch (XmlPullParserException unused2) {
            LogUtil.b("CasePhones", "XmlPullParserException ");
        }
        return list;
    }

    private static List<b> c(FileInputStream fileInputStream) throws XmlPullParserException, IOException {
        LogUtil.a("CasePhones", "inputStream not null");
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setInput(fileInputStream, "utf-8");
        ArrayList arrayList = null;
        b bVar = null;
        for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
            if (eventType != 2) {
                if (eventType == 3) {
                    if (newPullParser.getName() != null) {
                        if ("phone".equals(newPullParser.getName()) && arrayList != null && bVar != null) {
                            arrayList.add(bVar);
                        }
                    } else {
                        LogUtil.h("CasePhones", "phone is null");
                    }
                } else {
                    LogUtil.a("CasePhones", "it excute here");
                }
            } else if ("casePhone".equals(newPullParser.getName())) {
                arrayList = new ArrayList(10);
            } else if ("phone".equals(newPullParser.getName())) {
                bVar = new b();
            } else if ("phone_type".equals(newPullParser.getName())) {
                String nextText = newPullParser.nextText();
                if (nextText != null && bVar != null) {
                    bVar.c(nextText);
                } else {
                    LogUtil.h("CasePhones", "phone_type or phones is null");
                }
            } else if ("phone_mode".equals(newPullParser.getName())) {
                String nextText2 = newPullParser.nextText();
                if (nextText2 != null && bVar != null) {
                    bVar.e(nextText2);
                } else {
                    LogUtil.h("CasePhones", "phone_mode or phones is null");
                }
            } else {
                LogUtil.b("CasePhones", "XmlPullParser.START_TAG err");
            }
        }
        return arrayList;
    }

    public static class b {
        private String b;
        private String c;

        public String a() {
            return this.c;
        }

        public void c(String str) {
            this.c = str;
        }

        public String b() {
            return this.b;
        }

        public void e(String str) {
            this.b = str;
        }
    }
}
