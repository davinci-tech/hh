package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hwidauth.datatype.ReadAllowListInfo;
import com.huawei.hwidauth.datatype.SiteDefaultInfo;
import com.huawei.hwidauth.datatype.SiteListInfo;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes5.dex */
public class ksu {

    /* renamed from: a, reason: collision with root package name */
    private static SiteListInfo f14581a;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r7v16 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v7, types: [java.io.BufferedReader] */
    public static String c(Context context) {
        Throwable th;
        InputStreamReader inputStreamReader;
        IOException e;
        BufferedReader bufferedReader;
        UnsupportedEncodingException e2;
        try {
            try {
                inputStreamReader = new InputStreamReader(context.getResources().getAssets().open("global_cfg_for_android_mobile.xml"), "UTF-8");
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                bufferedReader = new BufferedReader(inputStreamReader);
                try {
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            sb.append(readLine);
                        } else {
                            String sb2 = sb.toString();
                            e(inputStreamReader, bufferedReader, null);
                            return sb2;
                        }
                    }
                } catch (UnsupportedEncodingException e3) {
                    e2 = e3;
                    ksy.c("IpCountryUtil", "UnsupportedEncodingException" + e2.getClass().getSimpleName(), true);
                    e(inputStreamReader, bufferedReader, null);
                    e(inputStreamReader, bufferedReader, null);
                    return "";
                } catch (IOException e4) {
                    e = e4;
                    ksy.c("IpCountryUtil", "IOException " + e.getClass().getSimpleName(), true);
                    e(inputStreamReader, bufferedReader, null);
                    e(inputStreamReader, bufferedReader, null);
                    return "";
                }
            } catch (UnsupportedEncodingException e5) {
                e2 = e5;
                bufferedReader = null;
            } catch (IOException e6) {
                e = e6;
                bufferedReader = null;
            } catch (Throwable th3) {
                th = th3;
                context = 0;
                e(inputStreamReader, context, null);
                throw th;
            }
        } catch (UnsupportedEncodingException e7) {
            e2 = e7;
            bufferedReader = null;
            inputStreamReader = null;
        } catch (IOException e8) {
            e = e8;
            bufferedReader = null;
            inputStreamReader = null;
        } catch (Throwable th4) {
            th = th4;
            context = 0;
            inputStreamReader = null;
        }
    }

    private static void e(InputStreamReader inputStreamReader, BufferedReader bufferedReader, InputStream inputStream) {
        if (inputStreamReader != null) {
            try {
                inputStreamReader.close();
            } catch (IOException e) {
                ksy.c("IpCountryUtil", "IOException" + e.getClass().getSimpleName(), true);
            }
        }
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException e2) {
                ksy.c("IpCountryUtil", "IOException" + e2.getClass().getSimpleName(), true);
            }
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e3) {
                ksy.c("IpCountryUtil", "IOException" + e3.getClass().getSimpleName(), true);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r8v12, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r8v22, types: [java.io.InputStreamReader, java.io.Reader] */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v7 */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Type inference failed for: r8v9, types: [java.io.InputStreamReader] */
    public static String a(Context context) {
        Throwable th;
        FileInputStream fileInputStream;
        BufferedReader bufferedReader;
        IOException e;
        UnsupportedEncodingException e2;
        InputStreamReader inputStreamReader;
        InputStreamReader inputStreamReader2 = null;
        r2 = null;
        r2 = null;
        inputStreamReader2 = null;
        r2 = null;
        BufferedReader bufferedReader2 = null;
        try {
            try {
                fileInputStream = new FileInputStream((context.getFilesDir().getCanonicalPath() + "/") + "global_cfg_for_android_mobile.xml");
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = null;
            }
        } catch (UnsupportedEncodingException e3) {
            e = e3;
            fileInputStream = null;
        } catch (IOException e4) {
            e = e4;
            fileInputStream = null;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
            bufferedReader = null;
        }
        try {
            context = new InputStreamReader(fileInputStream, "UTF-8");
            try {
                bufferedReader = new BufferedReader(context);
            } catch (UnsupportedEncodingException e5) {
                e2 = e5;
            } catch (IOException e6) {
                e = e6;
            }
        } catch (UnsupportedEncodingException e7) {
            e = e7;
            e2 = e;
            context = 0;
            ksy.c("IpCountryUtil", "getFromDataFile UnsupportedEncodingException " + e2.getClass().getSimpleName(), true);
            e(context, bufferedReader2, fileInputStream);
            inputStreamReader = context;
            e(inputStreamReader, bufferedReader2, fileInputStream);
            return "";
        } catch (IOException e8) {
            e = e8;
            e = e;
            context = 0;
            ksy.c("IpCountryUtil", "getFromDataFile IOException " + e.getClass().getSimpleName(), true);
            e(context, bufferedReader2, fileInputStream);
            inputStreamReader = context;
            e(inputStreamReader, bufferedReader2, fileInputStream);
            return "";
        } catch (Throwable th4) {
            th = th4;
            bufferedReader = null;
            e(inputStreamReader2, bufferedReader, fileInputStream);
            throw th;
        }
        try {
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                } else {
                    String sb2 = sb.toString();
                    e(context, bufferedReader, fileInputStream);
                    return sb2;
                }
            }
        } catch (UnsupportedEncodingException e9) {
            e2 = e9;
            bufferedReader2 = bufferedReader;
            ksy.c("IpCountryUtil", "getFromDataFile UnsupportedEncodingException " + e2.getClass().getSimpleName(), true);
            e(context, bufferedReader2, fileInputStream);
            inputStreamReader = context;
            e(inputStreamReader, bufferedReader2, fileInputStream);
            return "";
        } catch (IOException e10) {
            e = e10;
            bufferedReader2 = bufferedReader;
            ksy.c("IpCountryUtil", "getFromDataFile IOException " + e.getClass().getSimpleName(), true);
            e(context, bufferedReader2, fileInputStream);
            inputStreamReader = context;
            e(inputStreamReader, bufferedReader2, fileInputStream);
            return "";
        } catch (Throwable th5) {
            th = th5;
            inputStreamReader2 = context;
            e(inputStreamReader2, bufferedReader, fileInputStream);
            throw th;
        }
    }

    private static void d(Context context, XmlPullParser xmlPullParser, SiteDefaultInfo siteDefaultInfo, ArrayList<String> arrayList, ArrayList<SiteListInfo> arrayList2, String str) {
        if ("site-list".equals(str)) {
            SiteDefaultInfo.a(xmlPullParser, siteDefaultInfo);
            return;
        }
        if ("domain".equals(str)) {
            ReadAllowListInfo.a(xmlPullParser, arrayList, str);
        } else if ("site".equals(str)) {
            SiteListInfo siteListInfo = new SiteListInfo();
            f14581a = siteListInfo;
            SiteListInfo.a(xmlPullParser, siteListInfo, str);
            arrayList2.add(f14581a);
        }
    }

    public static XmlPullParser d(Context context, String str, String str2) throws XmlPullParserException, IOException {
        int b = b(context, str);
        int b2 = b(context, str2);
        ksy.c("IpCountryUtil", "parseSiteCountryXML xmlDataFileCode " + b, true);
        ksy.c("IpCountryUtil", "parseSiteCountryXML xmlAssetsFileCode " + b2, true);
        if (b < b2) {
            ksy.c("IpCountryUtil", "parseSiteCountryXML xmlDataFileCode < xmlAssetsFileCode", true);
            return kri.a(str2.getBytes("UTF-8"));
        }
        ksy.c("IpCountryUtil", "parseSiteCountryXML else ", true);
        return kri.a(str.getBytes("UTF-8"));
    }

    public static void e(Context context, SiteDefaultInfo siteDefaultInfo, ArrayList<String> arrayList, ArrayList<SiteListInfo> arrayList2) throws XmlPullParserException, IOException {
        if (context == null) {
            ksy.d("IpCountryUtil", "parseSiteCountryXML mContext == null", true);
            return;
        }
        ksy.b("IpCountryUtil", "getFromDataFile", true);
        XmlPullParser d = d(context, a(context), c(context));
        for (int eventType = d.getEventType(); 1 != eventType; eventType = d.next()) {
            String name = d.getName();
            if (eventType == 2) {
                d(context, d, siteDefaultInfo, arrayList, arrayList2, name);
            }
        }
    }

    private static int b(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            ksy.b("IpCountryUtil", "getXmlVersionCode context is null or xmlStr is empty", true);
            return 0;
        }
        try {
            try {
                XmlPullParser a2 = kri.a(str.getBytes("UTF-8"));
                for (int eventType = a2.getEventType(); 1 != eventType; eventType = a2.next()) {
                    String name = a2.getName();
                    if (2 == eventType && "Infos".equals(name)) {
                        for (int i = 0; i < a2.getAttributeCount(); i++) {
                            if ("version-code".equals(a2.getAttributeName(i))) {
                                try {
                                    return krg.a(a2.getAttributeValue(i));
                                } catch (Exception e) {
                                    ksy.b("IpCountryUtil", "e = " + e.getClass().getSimpleName(), true);
                                    return 0;
                                }
                            }
                        }
                    }
                }
            } catch (Exception e2) {
                ksy.d("IpCountryUtil", "Exception " + e2.getClass().getSimpleName(), true);
            }
        } catch (UnsupportedEncodingException e3) {
            ksy.d("IpCountryUtil", "UnsupportedEncodingException " + e3.getClass().getSimpleName(), true);
        } catch (IOException e4) {
            ksy.d("IpCountryUtil", "IOException " + e4.getClass().getSimpleName(), true);
        } catch (XmlPullParserException e5) {
            ksy.d("IpCountryUtil", "XmlPullParserException " + e5.getClass().getSimpleName(), true);
        }
        return 0;
    }

    public static boolean c(Context context, String str, String str2) {
        FileOutputStream fileOutputStream = null;
        try {
            try {
                try {
                    context.deleteFile(str);
                    fileOutputStream = context.openFileOutput(str, 0);
                    fileOutputStream.write(str2.getBytes("UTF-8"));
                    fileOutputStream.close();
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException unused) {
                            ksy.c("IpCountryUtil", "IOException", true);
                        }
                    }
                    return true;
                } catch (IOException unused2) {
                    ksy.c("IpCountryUtil", "writeSMSAvailableCountryXML IOException", true);
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException unused3) {
                            ksy.c("IpCountryUtil", "IOException", true);
                        }
                    }
                    return false;
                }
            } catch (FileNotFoundException unused4) {
                ksy.c("IpCountryUtil", "writeSMSAvailableCountryXML FileNotFoundException", true);
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused5) {
                        ksy.c("IpCountryUtil", "IOException", true);
                    }
                }
                return false;
            }
        } catch (Throwable th) {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException unused6) {
                    ksy.c("IpCountryUtil", "IOException", true);
                }
            }
            throw th;
        }
    }
}
