package com.huawei.appgallery.serviceverifykit.c;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.appgallery.serviceverifykit.api.ServiceVerifyKit;
import defpackage.ahb;
import defpackage.ahd;
import defpackage.ahg;
import defpackage.ahi;
import defpackage.ahk;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private String f1889a;
    private String b;
    private String c;
    private int j;
    private Intent k;
    private String m;
    private String n;
    private ServiceVerifyKit.Builder.ComponentType o;
    private Context r;
    private String s;
    private String y;
    private Map<String, String[]> d = new HashMap();
    private Map<String, Integer> e = new HashMap();
    private Map<String, String> h = new HashMap();
    private List<String> i = new ArrayList();
    private List<ServiceVerifyKit.c> g = new ArrayList();
    private int f = 0;
    private int l = 0;
    private int q = 0;
    private int t = 0;
    private int p = 0;

    public boolean a(String str, String str2) {
        String[] strArr;
        if (this.d.containsKey(str) && (strArr = this.d.get(str)) != null) {
            for (String str3 : strArr) {
                if (str2.equals(str3)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void a(String str, String str2, String str3, Map<String, String[]> map, Map<String, Integer> map2, int i, List<String> list, List<ServiceVerifyKit.c> list2, int i2, String str4, String str5, Intent intent, ServiceVerifyKit.Builder.ComponentType componentType, Map<String, String> map3, String str6) {
        this.b = str;
        this.c = str2;
        this.f1889a = str3;
        this.d = map;
        this.e = map2;
        this.j = i;
        this.i = list;
        this.g = list2;
        this.f = i2;
        this.m = str4;
        this.n = str5;
        this.k = intent;
        this.o = componentType;
        this.h = map3;
        this.y = str6;
    }

    public List<ahb> d() {
        PackageManager packageManager = this.r.getPackageManager();
        List<ResolveInfo> hT_ = hT_(packageManager);
        if (hT_.size() == 0) {
            return null;
        }
        this.s = TextUtils.isEmpty(this.n) ? Build.MANUFACTURER : a(this.n);
        return hU_(hT_, packageManager, this.j | 192);
    }

    private boolean hW_(Bundle bundle, String str, String str2, String str3, String str4) {
        ahi ahiVar;
        String str5;
        StringBuilder sb;
        if (bundle.containsKey(str3) && bundle.containsKey(str4)) {
            if (TextUtils.isEmpty(this.y)) {
                sb = new StringBuilder();
            } else {
                sb = new StringBuilder();
                sb.append(this.y);
                sb.append("&");
            }
            sb.append(str);
            sb.append("&");
            sb.append(str2);
            if (d(sb.toString(), bundle.getString(str3), bundle.getString(str4))) {
                return true;
            }
            ahiVar = ahi.e;
            str5 = "checkSinger failed";
        } else {
            ahiVar = ahi.e;
            str5 = "skip package " + str + " for no signer or no certChain";
        }
        ahiVar.c("MatchAppFinder", str5);
        return false;
    }

    private boolean d(String str, String str2, String str3) {
        ahi ahiVar;
        String str4;
        byte[] bArr;
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            ahiVar = ahi.e;
            str4 = "args is invalid";
        } else {
            List<X509Certificate> a2 = ahg.a(str3);
            if (a2.size() == 0) {
                ahiVar = ahi.e;
                str4 = "certChain is empty";
            } else if (ahg.b(ahg.e(this.r), a2)) {
                X509Certificate x509Certificate = a2.get(0);
                if (!ahg.e(x509Certificate, this.c)) {
                    ahiVar = ahi.e;
                    str4 = "CN is invalid";
                } else if (ahg.c(x509Certificate, this.f1889a)) {
                    try {
                        bArr = str.getBytes("UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        ahi.e.d("MatchAppFinder", "checkCertChain UnsupportedEncodingException:", e);
                        bArr = null;
                    }
                    if (ahg.a(x509Certificate, bArr, ahd.a(str2))) {
                        return true;
                    }
                    ahiVar = ahi.e;
                    str4 = "signature is invalid";
                } else {
                    ahiVar = ahi.e;
                    str4 = "OU is invalid";
                }
            } else {
                ahiVar = ahi.e;
                str4 = "failed to verify cert chain";
            }
        }
        ahiVar.c("MatchAppFinder", str4);
        return false;
    }

    private boolean hV_(Bundle bundle, String str, String str2, int i) {
        for (Map.Entry<String, String> entry : this.h.entrySet()) {
            if (bundle.containsKey(entry.getKey()) || bundle.containsKey(entry.getValue())) {
                if (hW_(bundle, str2, str, entry.getKey(), entry.getValue())) {
                    this.q = 0;
                    this.l = i;
                    return true;
                }
                ahi.e.c("MatchAppFinder", "checkSinger failed, packageName is " + str2);
            }
        }
        if (!a(str2, str)) {
            return false;
        }
        this.q = 1;
        this.l = this.e.get(str2).intValue();
        ahi.e.a("MatchAppFinder", "Legacy is success, packageName is " + str2);
        return true;
    }

    private List<ahb> hU_(List<ResolveInfo> list, PackageManager packageManager, int i) {
        ahi ahiVar;
        StringBuilder sb;
        String str;
        PackageInfo packageInfo;
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo resolveInfo : list) {
            String hS_ = hS_(resolveInfo);
            try {
                packageInfo = packageManager.getPackageInfo(hS_, i);
            } catch (PackageManager.NameNotFoundException unused) {
                ahiVar = ahi.e;
                sb = new StringBuilder("skip package ");
                sb.append(hS_);
                str = " for PackageInfo is null with NameNotFoundException";
            } catch (Exception unused2) {
                ahiVar = ahi.e;
                sb = new StringBuilder("skip package ");
                sb.append(hS_);
                str = " for PackageInfo is null with Exception";
            }
            if (packageInfo.applicationInfo == null) {
                ahiVar = ahi.e;
                sb = new StringBuilder("skip package ");
                sb.append(hS_);
                str = " for ApplicationInfo is null";
            } else {
                Signature[] signatureArr = packageInfo.signatures;
                if (signatureArr == null || signatureArr.length <= 0) {
                    ahiVar = ahi.e;
                    sb = new StringBuilder("skip package ");
                    sb.append(hS_);
                    str = " for no sign";
                } else {
                    byte[] byteArray = signatureArr[0].toByteArray();
                    if (byteArray.length == 0) {
                        ahiVar = ahi.e;
                        sb = new StringBuilder("skip package ");
                        sb.append(hS_);
                        str = " for sign is empty";
                    } else {
                        try {
                            ahb hR_ = hR_(packageInfo, ahk.b(MessageDigest.getInstance("SHA-256").digest(byteArray), true), hS_, resolveInfo.priority);
                            if (hR_ != null) {
                                arrayList.add(hR_);
                            }
                        } catch (NoSuchAlgorithmException unused3) {
                            ahiVar = ahi.e;
                            sb = new StringBuilder("skip package ");
                            sb.append(hS_);
                            str = " for AlgorithmException";
                        }
                    }
                }
            }
            sb.append(str);
            ahiVar.c("MatchAppFinder", sb.toString());
        }
        return arrayList;
    }

    private List<ResolveInfo> hT_(PackageManager packageManager) {
        Intent intent;
        ServiceVerifyKit.Builder.ComponentType componentType = this.o;
        if (componentType == null) {
            intent = new Intent(this.b);
        } else {
            if (componentType == ServiceVerifyKit.Builder.ComponentType.ACTIVITY) {
                return packageManager.queryIntentActivities(this.k, this.j);
            }
            if (componentType == ServiceVerifyKit.Builder.ComponentType.BROADCAST) {
                return packageManager.queryBroadcastReceivers(this.k, this.j);
            }
            intent = this.k;
        }
        return packageManager.queryIntentServices(intent, this.j);
    }

    private static String a(String str) {
        ahi ahiVar;
        String str2;
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class).invoke(null, str);
        } catch (ClassNotFoundException unused) {
            ahiVar = ahi.e;
            str2 = "getSystemProperties ClassNotFoundException";
            ahiVar.c("MatchAppFinder", str2);
            return "";
        } catch (Exception unused2) {
            ahiVar = ahi.e;
            str2 = "getSystemProperties Exception while getting system property";
            ahiVar.c("MatchAppFinder", str2);
            return "";
        }
    }

    private String hS_(ResolveInfo resolveInfo) {
        ServiceVerifyKit.Builder.ComponentType componentType = this.o;
        return ((componentType == ServiceVerifyKit.Builder.ComponentType.ACTIVITY || componentType == ServiceVerifyKit.Builder.ComponentType.BROADCAST) ? resolveInfo.activityInfo.applicationInfo : resolveInfo.serviceInfo.applicationInfo).packageName;
    }

    private ahb hR_(PackageInfo packageInfo, String str, String str2, int i) {
        Bundle bundle = packageInfo.applicationInfo.metaData;
        if (bundle == null) {
            return null;
        }
        int hQ_ = hQ_(bundle, this.s);
        if (!hV_(bundle, str, str2, i)) {
            return null;
        }
        if (!this.i.isEmpty()) {
            this.t = b(str2, this.i);
        }
        if (!this.g.isEmpty()) {
            this.p = hP_(bundle, this.f, this.g);
            ahi.e.a("MatchAppFinder", "match conditions success, packageName is " + str2 + " condition type is " + this.f + " condition number is " + this.p);
        }
        return new ahb(str2, hO_(bundle, this.l), this.q, hQ_, this.t, this.p);
    }

    private int b(String str, List<String> list) {
        Iterator<String> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().equals(str)) {
                i = 1;
            }
        }
        return i;
    }

    private int hQ_(Bundle bundle, String str) {
        if (!bundle.containsKey(this.m)) {
            return 0;
        }
        if (!bundle.getString(this.m).equalsIgnoreCase(str)) {
            return -1;
        }
        ahi.e.a("MatchAppFinder", "matchProp is 1, MetaDataKey is " + str);
        return 1;
    }

    private int hP_(Bundle bundle, int i, List<ServiceVerifyKit.c> list) {
        int i2 = 0;
        for (ServiceVerifyKit.c cVar : list) {
            if (i == 1) {
                if (!bundle.containsKey(cVar.b()) || !bundle.get(cVar.b()).toString().equals(cVar.a())) {
                    return 0;
                }
                i2 = 1;
            } else if (i != 2) {
                ahi.e.c("MatchAppFinder", "error input preferred package name");
            } else if (bundle.containsKey(cVar.b()) && bundle.get(cVar.b()).toString().equals(cVar.a())) {
                i2++;
            }
        }
        return i2;
    }

    private int hO_(Bundle bundle, int i) {
        if (!bundle.containsKey("ag.application.base_priority")) {
            return i + 1000;
        }
        try {
            return i + bundle.getInt("ag.application.base_priority");
        } catch (Exception unused) {
            ahi.e.c("MatchAppFinder", "skip package " + bundle.getString("ag.application.base_priority") + " is not number");
            return i + 1000;
        }
    }

    public a(Context context) {
        this.r = context;
    }
}
