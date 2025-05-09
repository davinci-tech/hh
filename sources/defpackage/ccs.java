package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class ccs {
    private static final Object c = new Object();
    private static ccs d;

    /* renamed from: a, reason: collision with root package name */
    private cuy f618a = null;

    private ccs() {
    }

    public static ccs d() {
        ccs ccsVar;
        synchronized (c) {
            if (d == null) {
                d = new ccs();
            }
            ccsVar = d;
        }
        return ccsVar;
    }

    public List<cve> e() {
        cuy b = b();
        ArrayList arrayList = new ArrayList(16);
        if (b == null) {
            LogUtil.h("DeviceIndexAllInfoManager", "getAllDeviceList mIndexAllBean is null");
            return arrayList;
        }
        List<cva> e = b.e();
        if (e != null && !e.isEmpty()) {
            e(e, arrayList);
        }
        return arrayList;
    }

    private void e(List<cva> list, List<cve> list2) {
        if (list == null) {
            LogUtil.h("DeviceIndexAllInfoManager", "kindBeans is null");
            return;
        }
        Iterator<cva> it = list.iterator();
        while (it.hasNext()) {
            List<cve> c2 = it.next().c();
            if (c2 == null) {
                LogUtil.h("DeviceIndexAllInfoManager", "deviceSingleSeriesInfoBeans is null");
            } else {
                list2.addAll(c2);
            }
        }
    }

    public void c() {
        File file = new File(cvy.c());
        boolean exists = file.exists();
        LogUtil.a("DeviceIndexAllInfoManager", "updateIndexCacheForWear isExistThisIndex is = ", Boolean.valueOf(exists));
        if (exists) {
            a(cdm.a(file));
        } else {
            this.f618a = null;
        }
    }

    public cuy b() {
        if (this.f618a == null) {
            c();
        }
        cuy cuyVar = this.f618a;
        if (cuyVar != null) {
            LogUtil.a("DeviceIndexAllInfoManager", "mIndexAllBean, version: ", cuyVar.i());
            g();
        }
        return this.f618a;
    }

    private void a(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DeviceIndexAllInfoManager", "parseVersionIndexFile indexJson is null");
            return;
        }
        try {
            cuy cuyVar = this.f618a;
            if (cuyVar == null) {
                str2 = "";
            } else {
                str2 = cuyVar.i();
            }
            if (TextUtils.isEmpty(str2)) {
                this.f618a = cdl.e(str);
                g();
            } else {
                if (str2.equals(new JSONObject(str).optString("version"))) {
                    return;
                }
                this.f618a = cdl.e(str);
                g();
            }
        } catch (JSONException unused) {
            LogUtil.b("DeviceIndexAllInfoManager", "parseVersionIndexFile JSONException");
        }
    }

    private void g() {
        boolean z;
        cuy cuyVar = this.f618a;
        boolean z2 = false;
        if (cuyVar != null) {
            z = c("img_index_all", cuyVar.a());
            LogUtil.a("DeviceIndexAllInfoManager", "isImageZip: ", Boolean.valueOf(z));
        } else {
            z = false;
        }
        cuy cuyVar2 = this.f618a;
        if (cuyVar2 != null) {
            z2 = c("lang_index_all", cuyVar2.d());
            LogUtil.a("DeviceIndexAllInfoManager", "isLangZip: ", Boolean.valueOf(z2));
        }
        if (!z || !z2) {
            this.f618a = null;
        }
        if (a()) {
            return;
        }
        LogUtil.a("DeviceIndexAllInfoManager", "done file is not exist");
        this.f618a = null;
    }

    private boolean a() {
        return new File(cvy.a()).exists();
    }

    private boolean c(String str, String str2) {
        synchronized (this) {
            File file = new File(cvy.b(str) + str2 + File.separator);
            if (!file.exists() || !file.isDirectory()) {
                return false;
            }
            String[] list = file.list();
            if (list != null) {
                if (list.length != 0) {
                    return true;
                }
            }
            return false;
        }
    }

    public List<cve> d(String str) {
        List<String> e;
        ArrayList arrayList = new ArrayList(16);
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DeviceIndexAllInfoManager", "getDeviceInfoByBluetooth, bluetooth is empty");
            return arrayList;
        }
        List<cve> e2 = e();
        String lowerCase = str.toLowerCase(Locale.ENGLISH);
        for (cve cveVar : e2) {
            if (cveVar != null) {
                String b = cveVar.b();
                if (!TextUtils.isEmpty(b) && (e = cveVar.e()) != null && !e.isEmpty()) {
                    for (String str2 : e) {
                        if (!TextUtils.isEmpty(str2) && e(b, lowerCase, str2.toLowerCase(Locale.ENGLISH))) {
                            arrayList.add(cveVar);
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0064, code lost:
    
        if (r7 != 4) goto L51;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean e(java.lang.String r7, java.lang.String r8, java.lang.String r9) {
        /*
            r6 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r7)
            r1 = 0
            if (r0 != 0) goto L83
            boolean r0 = android.text.TextUtils.isEmpty(r8)
            if (r0 != 0) goto L83
            boolean r0 = android.text.TextUtils.isEmpty(r9)
            if (r0 == 0) goto L15
            goto L83
        L15:
            r7.hashCode()
            int r0 = r7.hashCode()
            r2 = 4
            r3 = 3
            r4 = 2
            r5 = 1
            switch(r0) {
                case -1926781294: goto L50;
                case -1838093487: goto L45;
                case 66096429: goto L3a;
                case 66409183: goto L2f;
                case 1669509300: goto L24;
                default: goto L23;
            }
        L23:
            goto L5b
        L24:
            java.lang.String r0 = "CONTAIN"
            boolean r7 = r7.equals(r0)
            if (r7 != 0) goto L2d
            goto L5b
        L2d:
            r7 = r2
            goto L5c
        L2f:
            java.lang.String r0 = "EXACT"
            boolean r7 = r7.equals(r0)
            if (r7 != 0) goto L38
            goto L5b
        L38:
            r7 = r3
            goto L5c
        L3a:
            java.lang.String r0 = "EMPTY"
            boolean r7 = r7.equals(r0)
            if (r7 != 0) goto L43
            goto L5b
        L43:
            r7 = r4
            goto L5c
        L45:
            java.lang.String r0 = "SUFFIX"
            boolean r7 = r7.equals(r0)
            if (r7 != 0) goto L4e
            goto L5b
        L4e:
            r7 = r5
            goto L5c
        L50:
            java.lang.String r0 = "PREFIX"
            boolean r7 = r7.equals(r0)
            if (r7 != 0) goto L59
            goto L5b
        L59:
            r7 = r1
            goto L5c
        L5b:
            r7 = -1
        L5c:
            if (r7 == 0) goto L7c
            if (r7 == r5) goto L75
            if (r7 == r4) goto L6e
            if (r7 == r3) goto L67
            if (r7 == r2) goto L6e
            goto L83
        L67:
            boolean r7 = java.util.Objects.equals(r8, r9)
            if (r7 == 0) goto L83
            return r5
        L6e:
            boolean r7 = r8.contains(r9)
            if (r7 == 0) goto L83
            return r5
        L75:
            boolean r7 = r8.endsWith(r9)
            if (r7 == 0) goto L83
            return r5
        L7c:
            boolean r7 = r8.startsWith(r9)
            if (r7 == 0) goto L83
            return r5
        L83:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ccs.e(java.lang.String, java.lang.String, java.lang.String):boolean");
    }
}
