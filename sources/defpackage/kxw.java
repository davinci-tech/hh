package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.kyl;
import health.compact.a.IoUtils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class kxw {
    private static kyl.c.e c;

    private static kyl.c b(String str, String str2, String str3) {
        try {
            if (TextUtils.isEmpty(str)) {
                LogUtil.c("ParseChangeLogXmlUtil", "ParseChangeLogXmlUtil parseChangeLog, changeLogFilePath is null");
                return null;
            }
            FileInputStream fileInputStream = new FileInputStream(str);
            kyl.c a2 = kyl.a(fileInputStream, str2, str3);
            IoUtils.e(fileInputStream);
            return a2;
        } catch (IOException unused) {
            LogUtil.a("ParseChangeLogXmlUtil", "ParseChangeLogXmlUtil parseChangeLog error, changeLogFilePath = ", str);
            return null;
        }
    }

    public static String e(String str, String str2, String str3) {
        kyl.c b = b(str, str2, str3);
        if (b == null) {
            LogUtil.a("ParseChangeLogXmlUtil", "getUpdateLog null, changeLogFilePath = ", str);
            return "";
        }
        kyl.c.e e = e(b);
        c = e;
        return c(e != null ? d(e.d) : null);
    }

    public static String d(String str, String str2, String str3) {
        kyl.c b = b(str, str2, str3);
        if (b == null) {
            LogUtil.a("ParseChangeLogXmlUtil", "getUpdateLog null, changeLogFilePath = ", str);
            return "";
        }
        List arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        kyl.c.e e = e(b);
        c = e;
        if (e != null) {
            arrayList = d(e.d);
            arrayList2 = e(c.f14701a);
        }
        arrayList.addAll(arrayList2);
        return c(arrayList);
    }

    private static String c(List<kxm> list) {
        if (list == null || list.isEmpty()) {
            LogUtil.a("ParseChangeLogXmlUtil", "upDateDetailToStr upDateDetailToStr=", "");
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer("");
        for (kxm kxmVar : list) {
            stringBuffer.append(kxmVar.e()).append(System.lineSeparator()).append(kxmVar.c());
        }
        String stringBuffer2 = stringBuffer.toString();
        LogUtil.a("ParseChangeLogXmlUtil", "upDateDetailToStr features =", stringBuffer2);
        return stringBuffer2;
    }

    private static List<kxm> d(List<kyl.c.a> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (kyl.c.a aVar : list) {
                kxm kxmVar = new kxm();
                StringBuffer stringBuffer = new StringBuffer("");
                if (aVar != null) {
                    kxmVar.c(aVar.e);
                    Iterator<String> it = aVar.d.iterator();
                    while (it.hasNext()) {
                        stringBuffer.append(it.next());
                    }
                    kxmVar.b(stringBuffer.toString());
                    arrayList.add(kxmVar);
                } else {
                    kxmVar.c(null);
                }
            }
        }
        return arrayList;
    }

    private static List<kxm> e(List<kyl.c.C0322c> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (kyl.c.C0322c c0322c : list) {
                kxm kxmVar = new kxm();
                StringBuffer stringBuffer = new StringBuffer("");
                if (c0322c != null) {
                    kxmVar.c(c0322c.b);
                    Iterator<String> it = c0322c.e.iterator();
                    while (it.hasNext()) {
                        stringBuffer.append(it.next());
                    }
                    kxmVar.b(stringBuffer.toString());
                    arrayList.add(kxmVar);
                } else {
                    kxmVar.c(null);
                }
            }
        }
        return arrayList;
    }

    private static kyl.c.e e(kyl.c cVar) {
        if (cVar == null) {
            LogUtil.h("ParseChangeLogXmlUtil", "getFeatureWhenPullChangeLogSuccess msgObjOfCallBack==null");
            return null;
        }
        LogUtil.c("ParseChangeLogXmlUtil", "getFeatureWhenPullChangeLogSuccess pull change log success", ", changeLogXml.mCurrentLanguage = ", Integer.valueOf(cVar.e));
        if (cVar.e != -1) {
            return cVar.d.get(cVar.e);
        }
        if (cVar.b != -1) {
            return cVar.d.get(cVar.b);
        }
        return null;
    }
}
