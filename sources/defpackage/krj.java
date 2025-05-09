package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hwidauth.datatype.SiteDefaultInfo;
import com.huawei.hwidauth.datatype.SiteListInfo;
import com.huawei.hwidauth.utils.globalcfg.CountryInfoForCFG;
import com.huawei.hwidauth.utils.globalcfg.SiteInfoForCFG;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes5.dex */
public final class krj {
    private static krj d;
    private SiteDefaultInfo e = new SiteDefaultInfo();
    private ArrayList<String> b = new ArrayList<>();
    private ArrayList<SiteListInfo> c = new ArrayList<>();

    private krj() {
    }

    public static krj c() {
        krj krjVar;
        synchronized (krj.class) {
            if (d == null) {
                d = new krj();
            }
            krjVar = d;
        }
        return krjVar;
    }

    private void g(Context context) {
        synchronized (this) {
            if (this.b.isEmpty()) {
                ksy.b("SiteCountryDataManager", "inner update.", true);
                e(context);
            }
        }
    }

    public void e(final Context context) {
        boolean z;
        synchronized (this) {
            ksy.b("SiteCountryDataManager", "start countDownLatch innerInit", true);
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            kru.b().execute(new Runnable() { // from class: krj.5
                /* JADX WARN: Multi-variable type inference failed */
                /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.String] */
                /* JADX WARN: Type inference failed for: r0v3, types: [java.util.concurrent.CountDownLatch] */
                @Override // java.lang.Runnable
                public void run() {
                    String str = "finish thread innerInit";
                    ksy.b("SiteCountryDataManager", "start thread innerInit", true);
                    try {
                        try {
                            try {
                                krj.this.b.clear();
                                krj.this.c.clear();
                                ksu.e(context, krj.this.e, krj.this.b, krj.this.c);
                            } catch (Exception e) {
                                ksy.c("SiteCountryDataManager", "Exception = " + e.getClass().getSimpleName(), true);
                            }
                        } catch (IOException e2) {
                            ksy.c("SiteCountryDataManager", "IOException = " + e2.getClass().getSimpleName(), true);
                        } catch (XmlPullParserException e3) {
                            ksy.c("SiteCountryDataManager", "XmlPullParserException = " + e3.getClass().getSimpleName(), true);
                        }
                    } finally {
                        ksy.c("SiteCountryDataManager", str, true);
                        countDownLatch.countDown();
                    }
                }
            });
            try {
                z = countDownLatch.await(3L, TimeUnit.SECONDS);
            } catch (InterruptedException unused) {
                ksy.c("SiteCountryDataManager", "InterruptedException", true);
                z = false;
            }
            ksy.b("SiteCountryDataManager", "end countDownLatch innerInit awaitValue:" + z, true);
        }
    }

    public ArrayList<String> b(Context context) {
        ArrayList<String> arrayList;
        synchronized (this) {
            g(context);
            ksy.b("SiteCountryDataManager", "get allow list from file.", true);
            arrayList = this.b;
        }
        return arrayList;
    }

    public ArrayList<String> c(Context context) {
        ArrayList<String> b = b(context);
        return b == null ? new ArrayList<>(0) : b;
    }

    public String d(Context context) {
        synchronized (this) {
            g(context);
            String a2 = this.e.a();
            ksy.b("SiteCountryDataManager", "getLogOutUrl::=" + a2, false);
            if (TextUtils.isEmpty(a2)) {
                return "";
            }
            return "https://" + a2 + "/logout";
        }
    }

    public String a(Context context) {
        String str;
        synchronized (this) {
            g(context);
            int c = krn.c(context).c("siteID", -1);
            String b = c != -1 ? b(context, c) : this.e.c();
            if (TextUtils.isEmpty(b)) {
                str = "";
            } else {
                str = "https://" + b + "/AMW/mobile/delUser.html";
            }
        }
        return str;
    }

    public String f(Context context) {
        String str;
        synchronized (this) {
            g(context);
            int c = krn.c(context).c("siteID", -1);
            String b = c != -1 ? b(context, c) : this.e.c();
            if (TextUtils.isEmpty(b)) {
                str = "";
            } else {
                str = "https://" + b + "/AMW/mobile/appealSelf/bindNewFinish.html";
            }
        }
        return str;
    }

    public String i(Context context) {
        synchronized (this) {
            g(context);
            String b = this.e.b();
            ksy.b("SiteCountryDataManager", "getQrAuthUrl::=" + b, false);
            if (TextUtils.isEmpty(b)) {
                return "";
            }
            return "https://" + b + "/CAS/mobile/loginHmsSuccess.html";
        }
    }

    public String c(Context context, int i) {
        String str;
        synchronized (this) {
            str = "https://" + b(context, i);
        }
        return str;
    }

    private String b(Context context, int i) {
        String str;
        synchronized (this) {
            g(context);
            str = "";
            if (i > 0) {
                Iterator<SiteListInfo> it = this.c.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    SiteListInfo next = it.next();
                    if (next.a() == i) {
                        str = next.b();
                        ksy.b("SiteCountryDataManager", "find the site id " + i, true);
                        break;
                    }
                }
                if (TextUtils.isEmpty(str)) {
                    str = "";
                    ksr.c(context);
                }
            } else if (TextUtils.isEmpty("")) {
                str = this.e.c();
            }
        }
        return str;
    }

    private CountryInfoForCFG a(String str, List<CountryInfoForCFG> list) {
        CountryInfoForCFG countryInfoForCFG;
        synchronized (this) {
            Iterator<CountryInfoForCFG> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    countryInfoForCFG = null;
                    break;
                }
                countryInfoForCFG = it.next();
                if (str.equalsIgnoreCase(countryInfoForCFG.b())) {
                    break;
                }
            }
        }
        return countryInfoForCFG;
    }

    private SiteInfoForCFG b(String str, List<SiteInfoForCFG> list) {
        SiteInfoForCFG siteInfoForCFG;
        synchronized (this) {
            Iterator<SiteInfoForCFG> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    siteInfoForCFG = null;
                    break;
                }
                siteInfoForCFG = it.next();
                if (str.equals(siteInfoForCFG.a())) {
                    break;
                }
            }
        }
        return siteInfoForCFG;
    }

    private CountryInfoForCFG e(String str, List<CountryInfoForCFG> list) {
        CountryInfoForCFG countryInfoForCFG;
        synchronized (this) {
            Iterator<CountryInfoForCFG> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    countryInfoForCFG = null;
                    break;
                }
                countryInfoForCFG = it.next();
                if (str.equalsIgnoreCase(countryInfoForCFG.c())) {
                    break;
                }
            }
        }
        return countryInfoForCFG;
    }

    private String d(Context context, String str, String str2, List<CountryInfoForCFG> list, List<SiteInfoForCFG> list2, boolean z) {
        synchronized (this) {
            SiteInfoForCFG b = b(str2, list2);
            if (b == null) {
                ksy.c("SiteCountryDataManager", "not found the site info by site", true);
                return "";
            }
            if ("1".equals(b.b())) {
                return str;
            }
            if (!z) {
                return "";
            }
            return b(context, str, list, list2);
        }
    }

    private List<CountryInfoForCFG> a(List<CountryInfoForCFG> list, List<CountryInfoForCFG> list2) {
        ArrayList arrayList = new ArrayList();
        for (CountryInfoForCFG countryInfoForCFG : list) {
            Iterator<CountryInfoForCFG> it = list2.iterator();
            while (true) {
                if (it.hasNext()) {
                    CountryInfoForCFG next = it.next();
                    if (!TextUtils.isEmpty(countryInfoForCFG.b()) && countryInfoForCFG.b().equals(next.b())) {
                        arrayList.add(next);
                        break;
                    }
                } else {
                    arrayList.add(countryInfoForCFG);
                    break;
                }
            }
        }
        return arrayList;
    }

    private String c(Context context, String str, List<CountryInfoForCFG> list, List<SiteInfoForCFG> list2) {
        String c = ksf.d().c(context);
        if (krh.b().d() && !TextUtils.isEmpty(krh.b().a())) {
            c = krh.b().a();
        }
        ksy.a("SiteCountryDataManager", "sim card root MCC is " + c, false);
        CountryInfoForCFG e = e(c, list);
        if (e == null) {
            ksy.c("SiteCountryDataManager", "not found the country by mcc:", true);
            return "";
        }
        String d2 = e.d();
        if ("1".equals(d2)) {
            return e.b();
        }
        return TextUtils.isEmpty(d2) ? d(context, e.b(), e.a(), list, list2, false) : "";
    }

    private String b(Context context, String str, List<CountryInfoForCFG> list, List<SiteInfoForCFG> list2) {
        String c = ksf.d().c(context);
        if (krh.b().d() && !TextUtils.isEmpty(krh.b().a())) {
            c = krh.b().a();
        }
        ksy.a("SiteCountryDataManager", "sim card root MCC is " + c, false);
        CountryInfoForCFG e = e(c, list);
        if (e == null) {
            ksy.c("SiteCountryDataManager", "not found the country by mcc:" + str, false);
            return "";
        }
        String d2 = e.d();
        if ("1".equals(d2)) {
            return e.b();
        }
        return TextUtils.isEmpty(d2) ? d(context, e.b(), e.a(), list, list2, false) : "";
    }

    public String h(Context context) {
        synchronized (this) {
            String a2 = ksi.a(context);
            if (krh.b().d() && !TextUtils.isEmpty(krh.b().c())) {
                a2 = krh.b().c();
            }
            String str = a2;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            ksp.a().d(context, arrayList, arrayList2, arrayList3);
            List<CountryInfoForCFG> a3 = a(arrayList, arrayList2);
            CountryInfoForCFG a4 = a(str, a3);
            if (a4 == null) {
                return c(context, str, a3, arrayList3);
            }
            String d2 = a4.d();
            if ("1".equals(d2)) {
                return str;
            }
            if (TextUtils.isEmpty(d2)) {
                return d(context, str, a4.a(), a3, arrayList3, true);
            }
            return b(context, str, a3, arrayList3);
        }
    }
}
