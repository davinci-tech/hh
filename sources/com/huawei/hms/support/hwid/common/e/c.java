package com.huawei.hms.support.hwid.common.e;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.Constants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes9.dex */
public class c {
    public static boolean a(Context context) {
        g.a("BaseUtil", "enter networkIsAvaiable");
        if (context == null) {
            return false;
        }
        Object systemService = context.getSystemService("connectivity");
        if (systemService == null) {
            g.a("BaseUtil", "connectivity is null,so networkIsAvaiable is unaviable");
            return false;
        }
        NetworkInfo[] allNetworkInfo = ((ConnectivityManager) systemService).getAllNetworkInfo();
        if (allNetworkInfo == null || allNetworkInfo.length == 0) {
            g.a("BaseUtil", "NetworkInfo is null,so networkIsAvaiable is unaviable");
            return false;
        }
        for (NetworkInfo networkInfo : allNetworkInfo) {
            if (networkInfo != null && networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                return true;
            }
        }
        g.a("BaseUtil", "NetworkInfo  state is unaviable");
        return false;
    }

    public static String b(Context context) {
        return context.getResources().getConfiguration().locale.getLanguage().toLowerCase(Locale.getDefault());
    }

    public static String c(Context context) {
        return context.getResources().getConfiguration().locale.getCountry().toLowerCase(Locale.getDefault());
    }

    private static String g(Context context) {
        return context.getResources().getConfiguration().locale.getScript();
    }

    public static String d(Context context) {
        if (context == null) {
            return "";
        }
        if (TextUtils.isEmpty(g(context))) {
            return b(context) + Constants.LINK + c(context).toUpperCase(Locale.getDefault());
        }
        return b(context) + Constants.LINK + g(context) + Constants.LINK + c(context).toUpperCase(Locale.getDefault());
    }

    public static List<String> e(Context context) {
        ArrayList arrayList = new ArrayList();
        XmlResourceParser xml = context.getResources().getXml(h.a(context, "usesdk_packagename"));
        if (xml == null) {
            return arrayList;
        }
        try {
            for (int eventType = xml.getEventType(); 1 != eventType; eventType = xml.next()) {
                String name = xml.getName();
                if (eventType == 2 && "package".equals(name)) {
                    arrayList.add(xml.nextText());
                }
            }
        } catch (IOException unused) {
            g.b("BaseUtil", "Parser xml exception: IOException");
        } catch (XmlPullParserException unused2) {
            g.b("BaseUtil", "Parser xml exception: XmlPullParserException");
        }
        return arrayList;
    }

    public static boolean f(Context context) {
        List<String> list;
        if (context == null) {
            g.b("BaseUtil", "context is null.");
            return true;
        }
        com.huawei.hms.support.hwid.common.a a2 = com.huawei.hms.support.hwid.common.a.a();
        if (a2.c() == null) {
            try {
                list = e(context);
                try {
                    a2.a(list);
                } catch (Exception unused) {
                    g.b("BaseUtil", "Exception");
                    if (list == null) {
                    }
                }
            } catch (Exception unused2) {
                list = null;
            }
        } else {
            list = a2.c();
        }
        return (list == null && list.contains(context.getPackageName())) ? false : true;
    }

    public static boolean a(String str) {
        return (TextUtils.isEmpty(str) || str.equals("0") || str.equals("1") || str.equals("2") || str.equals("8")) ? false : true;
    }

    public static String a() {
        return "HwID_6.11.0.300";
    }
}
