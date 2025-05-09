package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Looper;
import android.text.TextUtils;
import com.huawei.haf.download.DownloadPluginUrl;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.pluginmgr.filedownload.PullListener;
import health.compact.a.CloudUtils;
import health.compact.a.CommonUtil;
import health.compact.a.IoUtils;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class cdk extends HwBaseManager {
    private static volatile cdk e;
    private cdi c;
    private cdi f;
    private cdi g;
    private cdi h;
    private cdi i;
    private cdi j;
    private cdi k;
    private cdi l;
    private cdi m;
    private DownloadPluginUrl n;
    private Context o;
    private cdi p;
    private cdi q;
    private cdi r;
    private cdi s;
    private cdi t;
    private cdi u;
    private cdi v;
    private cdi y;
    private static String[] d = {"sr-Latn", "jv-Latn"};
    private static HashMap<String, String> b = new HashMap<String, String>() { // from class: cdk.2
        private static final long serialVersionUID = 1212859686823339267L;

        {
            put("sr-Latn", "b+sr+Latn");
            put("jv-Latn", "b+jv+Latn");
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private static List<cvc> f644a = new ArrayList(16);

    private cdk(Context context) {
        super(context);
        this.m = new cdi();
        this.c = new cdi();
        this.j = new cdi();
        this.g = new cdi();
        this.f = new cdi();
        this.h = new cdi();
        this.l = new cdi();
        this.q = new cdi();
        this.s = new cdi();
        this.v = new cdi();
        this.k = new cdi();
        this.p = new cdi();
        this.i = new cdi();
        this.y = new cdi();
        this.u = new cdi();
        this.t = new cdi();
        this.r = new cdi();
        this.o = BaseApplication.getContext();
        b(msn.c().d());
    }

    public static cdk e() {
        if (e == null) {
            synchronized (cdr.f649a) {
                if (e == null) {
                    e = new cdk(BaseApplication.getContext());
                }
            }
        }
        return e;
    }

    private void b(DownloadPluginUrl downloadPluginUrl) {
        LogUtil.a("ResourceHandleManager", "enter setDownloadPluginUrl ");
        this.n = downloadPluginUrl;
    }

    public cvk e(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("ResourceHandleManager", "getPluginIndexInfo uuid is empty");
            return null;
        }
        List<cvk> a2 = a();
        if (!koq.b(a2)) {
            LogUtil.c("ResourceHandleManager", "getPluginIndexInfo ezPluginInfos size is :", Integer.valueOf(a2.size()));
            for (cvk cvkVar : a2) {
                if (str.equals(cvkVar.e())) {
                    return cvkVar;
                }
            }
        }
        return null;
    }

    public cvc b(String str) {
        LogUtil.a("ResourceHandleManager", "enter getPluginInfo");
        cvc a2 = cdr.a(this.m, f644a, str);
        if (a2 != null) {
            LogUtil.c("ResourceHandleManager", "getPluginInfo get cachePluginInfo");
            return a2;
        }
        boolean r = r(str);
        LogUtil.a("ResourceHandleManager", "enter to getPluginInfo isDeprecated is :", Boolean.valueOf(r));
        cvc a3 = !r ? cdr.a(this.o, str) : null;
        e(a3, str);
        return a3;
    }

    public cvc c(String str) {
        boolean r = r(str);
        LogUtil.a("ResourceHandleManager", "enter to getPluginInfoNotCache isDeprecated is :", Boolean.valueOf(r));
        cvc a2 = !r ? cdr.a(this.o, str) : null;
        e(a2, str);
        return a2;
    }

    public void e(cvc cvcVar, String str) {
        cdr.a(f644a, cvcVar, str);
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 20010;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private cdi k(String str) {
        char c;
        cdi t = t(str);
        if (t != null && !TextUtils.isEmpty(t.e())) {
            return t;
        }
        str.hashCode();
        switch (str.hashCode()) {
            case -1742636370:
                if (str.equals("HDK_ROPE_SKIPPING")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1605993952:
                if (str.equals("HDK_ROWING_MACHINE")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1371741911:
                if (str.equals("HDK_SMART_WATCH")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -690748027:
                if (str.equals("HDK_ELLIPTICAL_MACHINE")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 129710486:
                if (str.equals("HDK_TREADMILL")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 232428455:
                if (str.equals("HDK_SMART_PILLOW")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 495164536:
                if (str.equals("HDK_EXERCISE_BIKE")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1132613073:
                if (str.equals("HDK_WALKING_MACHINE")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 2066052828:
                if (str.equals("HDK_MASSAGE_GUN")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                t = this.s;
                break;
            case 1:
                t = this.p;
                break;
            case 2:
                t = this.u;
                break;
            case 3:
                t = this.i;
                break;
            case 4:
                t = this.v;
                break;
            case 5:
                t = this.t;
                break;
            case 6:
                t = this.k;
                break;
            case 7:
                t = this.y;
                break;
            case '\b':
                t = this.r;
                break;
        }
        if (t == null || TextUtils.isEmpty(t.e())) {
            return null;
        }
        return t;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private cdi t(String str) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -2017367641:
                if (str.equals("HDK_BODY_TEMPERATURE")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1079925272:
                if (str.equals("HDK_WEIGHT")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -187341623:
                if (str.equals("HDK_HEART_RATE")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 376592149:
                if (str.equals("HDK_BLOOD_OXYGEN")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1279483514:
                if (str.equals("HDK_BLOOD_PRESSURE")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1494700537:
                if (str.equals("HDK_ECG")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1816850689:
                if (str.equals("HDK_BLOOD_SUGAR")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return this.q;
            case 1:
                return this.h;
            case 2:
                return this.l;
            case 3:
                return this.c;
            case 4:
                return this.g;
            case 5:
                return this.j;
            case 6:
                return this.f;
            default:
                return null;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void a(String str, cdi cdiVar) {
        char c;
        if (b(str, cdiVar)) {
        }
        str.hashCode();
        switch (str.hashCode()) {
            case -1605993952:
                if (str.equals("HDK_ROWING_MACHINE")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1371741911:
                if (str.equals("HDK_SMART_WATCH")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -690748027:
                if (str.equals("HDK_ELLIPTICAL_MACHINE")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 129710486:
                if (str.equals("HDK_TREADMILL")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 232428455:
                if (str.equals("HDK_SMART_PILLOW")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 495164536:
                if (str.equals("HDK_EXERCISE_BIKE")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1132613073:
                if (str.equals("HDK_WALKING_MACHINE")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 2066052828:
                if (str.equals("HDK_MASSAGE_GUN")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                this.p = cdiVar;
                break;
            case 1:
                this.u = cdiVar;
                break;
            case 2:
                this.i = cdiVar;
                break;
            case 3:
                this.v = cdiVar;
                break;
            case 4:
                this.t = cdiVar;
                break;
            case 5:
                this.k = cdiVar;
                break;
            case 6:
                this.y = cdiVar;
                break;
            case 7:
                this.r = cdiVar;
                break;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private boolean b(String str, cdi cdiVar) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -2017367641:
                if (str.equals("HDK_BODY_TEMPERATURE")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1742636370:
                if (str.equals("HDK_ROPE_SKIPPING")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1079925272:
                if (str.equals("HDK_WEIGHT")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -187341623:
                if (str.equals("HDK_HEART_RATE")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 376592149:
                if (str.equals("HDK_BLOOD_OXYGEN")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1279483514:
                if (str.equals("HDK_BLOOD_PRESSURE")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1494700537:
                if (str.equals("HDK_ECG")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1816850689:
                if (str.equals("HDK_BLOOD_SUGAR")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                this.q = cdiVar;
                return true;
            case 1:
                this.s = cdiVar;
                return true;
            case 2:
                this.h = cdiVar;
                return true;
            case 3:
                this.l = cdiVar;
                return true;
            case 4:
                this.c = cdiVar;
                return true;
            case 5:
                this.g = cdiVar;
                return true;
            case 6:
                this.j = cdiVar;
                return true;
            case 7:
                this.f = cdiVar;
                return true;
            default:
                return false;
        }
    }

    public List<cvk> c() {
        synchronized (this) {
            ArrayList arrayList = new ArrayList();
            List<cve> e2 = ccs.d().e();
            ArrayList arrayList2 = new ArrayList();
            arrayList2.addAll(e2);
            for (int i = 0; i < arrayList2.size(); i++) {
                List<cvk> q = ((cve) arrayList2.get(i)).q();
                if (q != null && q.size() > 0) {
                    arrayList.addAll(q);
                }
            }
            if (!koq.b(this.m.a())) {
                LogUtil.a("ResourceHandleManager", "mIndexFileStruct.getPlugins start");
                arrayList.addAll(this.m.a());
                return arrayList;
            }
            String d2 = cdr.d();
            boolean exists = new File(d2).exists();
            LogUtil.a("ResourceHandleManager", "getIndexList isExistThisIndex is =", Boolean.valueOf(exists));
            if (exists) {
                String c = cdr.c(this.o, d2);
                LogUtil.c("ResourceHandleManager", "getIndex indexJson = ", c);
                q(c);
                if (this.m.a() != null) {
                    LogUtil.a("ResourceHandleManager", "mIndexFileStruct.getPlugins");
                    arrayList.addAll(this.m.a());
                    return arrayList;
                }
            }
            return null;
        }
    }

    public List<cvk> d(String str) {
        synchronized (this) {
            cdi k = k(str);
            List<cvk> a2 = k != null ? k.a() : null;
            if (a2 != null) {
                return a2;
            }
            String b2 = cdr.b(str);
            if (new File(b2).exists()) {
                String c = cdr.c(this.o, b2);
                Object[] objArr = new Object[3];
                objArr[0] = "index Data:";
                objArr[1] = c;
                objArr[2] = TextUtils.isEmpty(c) ? ",indexJson is null" : "";
                LogUtil.a("ResourceHandleManager", objArr);
                if (TextUtils.isEmpty(c)) {
                    return null;
                }
                cdi c2 = cde.c(c);
                a(str, c2);
                a2 = c2.a();
            }
            return a2;
        }
    }

    public List<cvk> a() {
        LogUtil.c("ResourceHandleManager", "getAllIndexList");
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = msr.c.keySet().iterator();
        while (it.hasNext()) {
            List<cvk> d2 = d(it.next());
            if (d2 != null) {
                arrayList.addAll(d2);
            }
        }
        List<cvk> c = c();
        if (c != null) {
            arrayList.addAll(c);
        }
        return arrayList;
    }

    private void q(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("ResourceHandleManager", "parseVersionIndexFile indexJson is null");
            return;
        }
        try {
            cdi cdiVar = this.m;
            if (cdiVar == null) {
                str2 = "";
            } else {
                str2 = cdiVar.e();
            }
            if (TextUtils.isEmpty(str2)) {
                this.m = cde.c(str);
                cdr.b(f644a);
            } else {
                if (str2.equals(new JSONObject(str).optString("version"))) {
                    return;
                }
                this.m = cde.c(str);
                cdr.b(f644a);
            }
        } catch (JSONException unused) {
            LogUtil.b("ResourceHandleManager", "parseVersionIndexFile JSONException");
        }
    }

    public cvc e(int i) {
        cvc b2;
        List<cvk> c = c();
        if (koq.b(c)) {
            return null;
        }
        LogUtil.c("ResourceHandleManager", "getPluginInfoByType indexInfos.size > 0");
        for (cvk cvkVar : c) {
            if (cvkVar.e() != null && (cvkVar.g().equals("SMART_WATCH") || cvkVar.g().equals("SMART_BAND"))) {
                if (e().h(cvkVar.e()) && (b2 = e().b(cvkVar.e())) != null && b2.f() != null && b2.f().af() == i) {
                    return b2;
                }
            }
        }
        return null;
    }

    public cvc b(int i) {
        cvc b2;
        List<cvk> c = c();
        if (koq.b(c)) {
            return null;
        }
        LogUtil.a("ResourceHandleManager", "getPluginInfoByHiType indexInfos.size > 0 , hitype:", Integer.valueOf(i));
        for (cvk cvkVar : c) {
            if (cvkVar.e() != null && e().h(cvkVar.e()) && (b2 = e().b(cvkVar.e())) != null && b2.f() != null && a(b2, i)) {
                LogUtil.a("ResourceHandleManager", "getPluginInfoByHiType match info success pluginInfoHiType is ", Integer.valueOf(i));
                return b2;
            }
        }
        return null;
    }

    private boolean a(cvc cvcVar, int i) {
        if (cvcVar.f().au() == i) {
            LogUtil.a("ResourceHandleManager", "match getOldHiyType enter hiType is ", Integer.valueOf(i));
            return true;
        }
        try {
            String b2 = cvcVar.f().b();
            LogUtil.a("ResourceHandleManager", "isMatchHitype aitipeProduct is ", b2);
            JSONObject jSONObject = new JSONObject(b2);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (!next.contains("ai_tips_product")) {
                    LogUtil.a("ResourceHandleManager", "isMatchHitype key is ", next, " not contains ai_tips_product");
                } else if (j(jSONObject.getString(next)) == i) {
                    LogUtil.a("ResourceHandleManager", "isMatchHitype retur true");
                    return true;
                }
            }
        } catch (JSONException unused) {
            LogUtil.b("ResourceHandleManager", "isMatchHitype JSONException");
        }
        return false;
    }

    private int j(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        if (jSONObject.has("health_cloud_productId")) {
            int i = jSONObject.getInt("health_cloud_productId");
            LogUtil.a("ResourceHandleManager", "getAitipsPriductType productType is ", Integer.valueOf(i));
            return i;
        }
        LogUtil.a("ResourceHandleManager", "getAitipsPriductType no key");
        return -1;
    }

    public boolean h(String str) {
        return cdr.c(str, r(str));
    }

    private boolean r(String str) {
        return cdr.b(str, e(str));
    }

    public void d(String str, PullListener pullListener) {
        e(str, (CommonUtil.ag(this.o) || i()) ? "com.huawei.health_HwWear_deviceConfig" : "com.huawei.health_HwWear_deviceConfigBeta", "", pullListener);
    }

    public void e(final String str, final String str2, final String str3, final PullListener pullListener) {
        PullListener pullListener2 = new PullListener() { // from class: cdn
            @Override // com.huawei.pluginmgr.filedownload.PullListener
            public final void onPullingChange(msq msqVar, mso msoVar) {
                cdk.this.a(str2, str, str3, pullListener, msqVar, msoVar);
            }
        };
        String n = n(str2);
        LogUtil.a("ResourceHandleManager", "updateDescription file id ", n);
        String m = m(str2);
        String d2 = cdr.d();
        if (!TextUtils.isEmpty(m)) {
            d2 = cdr.b(m);
        }
        c(n, str2, d2, pullListener2);
    }

    /* synthetic */ void a(String str, String str2, String str3, PullListener pullListener, msq msqVar, mso msoVar) {
        int i = msoVar.i();
        if (i != 1 && i != -11) {
            if (i == 0) {
                LogUtil.a("ResourceHandleManager", "updateDescription Downloading index file");
                return;
            } else {
                if (pullListener != null) {
                    pullListener.onPullingChange(msqVar, msoVar);
                    return;
                }
                return;
            }
        }
        String l = l(str);
        LogUtil.a("ResourceHandleManager", "updateDescription savePathIndex is =", l);
        String c = cdr.c(this.o, l);
        LogUtil.c("ResourceHandleManager", "updateDescription indexJson = ", c);
        q(c);
        SharedPreferenceManager.e(this.o, String.valueOf(getModuleId()), "updateTime", String.valueOf(new Date().getTime()), (StorageParams) null);
        cvk e2 = e(str2);
        String str4 = str + str3;
        if (TextUtils.equals(str3, "?deviceType=bodyFatScales")) {
            LogUtil.a("ResourceHandleManager", "matchRules is equals MATCH_RULES");
            str4 = mrp.e(this.o, "HDK_WEIGHT");
        }
        e(e2, str4, pullListener, msqVar, msoVar);
    }

    private String l(String str) {
        String m = m(str);
        return !TextUtils.isEmpty(m) ? cdr.b(m) : cdr.a();
    }

    public void c(final String str, final PullListener pullListener) {
        cvc b2 = b(str);
        if (b2 != null) {
            e(str, this.n, pullListener, b2);
        } else {
            d(str, new PullListener() { // from class: cds
                @Override // com.huawei.pluginmgr.filedownload.PullListener
                public final void onPullingChange(msq msqVar, mso msoVar) {
                    cdk.this.d(str, pullListener, msqVar, msoVar);
                }
            });
        }
    }

    /* synthetic */ void d(String str, PullListener pullListener, msq msqVar, mso msoVar) {
        int i = msoVar.i();
        if (i == 1) {
            if (b(str) != null) {
                c(str, pullListener);
                return;
            } else {
                LogUtil.h("ResourceHandleManager", "inform error");
                return;
            }
        }
        if (i == 0 || pullListener == null) {
            return;
        }
        pullListener.onPullingChange(msqVar, msoVar);
    }

    private String o(String str) {
        String str2 = str + "com.huawei.health_HwWear_deviceConfigBeta";
        if (!CommonUtil.ag(this.o) && !i()) {
            return str2;
        }
        return str + "com.huawei.health_HwWear_deviceConfig";
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void e(final String str, final DownloadPluginUrl downloadPluginUrl, final PullListener pullListener, final cvc cvcVar) {
        String str2;
        if (downloadPluginUrl == null) {
            str2 = "";
        } else {
            if (Looper.getMainLooper() == Looper.myLooper()) {
                ThreadPoolManager.d().execute(new Runnable() { // from class: cdq
                    @Override // java.lang.Runnable
                    public final void run() {
                        cdk.this.e(str, downloadPluginUrl, pullListener, cvcVar);
                    }
                });
                return;
            }
            str2 = downloadPluginUrl.getDownloadPluginUrl(null, true);
        }
        String o = o(str2);
        LogUtil.a("ResourceHandleManager", "startUpdatePlugin pluginUrl is = ", o);
        cdv b2 = cdv.b();
        msn.c();
        b2.d(str, o, msn.a(), cvcVar, pullListener);
    }

    private void b(cvk cvkVar, String str, String str2, PullListener pullListener) {
        if (cvkVar != null) {
            if (!r(str)) {
                d(str, this.n, str2, pullListener);
                return;
            }
            mso msoVar = new mso();
            msq msqVar = new msq();
            msoVar.b(-5);
            if (pullListener != null) {
                pullListener.onPullingChange(msqVar, msoVar);
                return;
            }
            return;
        }
        LogUtil.h("ResourceHandleManager", "downloadDescriptioninform error");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void d(final String str, final DownloadPluginUrl downloadPluginUrl, final String str2, final PullListener pullListener) {
        String str3;
        String str4 = null;
        if (downloadPluginUrl == null) {
            str3 = "";
        } else {
            if (Looper.getMainLooper() == Looper.myLooper()) {
                ThreadPoolManager.d().execute(new Runnable() { // from class: cdp
                    @Override // java.lang.Runnable
                    public final void run() {
                        cdk.this.d(str, downloadPluginUrl, str2, pullListener);
                    }
                });
                return;
            }
            str3 = downloadPluginUrl.getDownloadPluginUrl(null, true);
        }
        String str5 = str3 + str2;
        LogUtil.a("ResourceHandleManager", "startUpdateDescription pluginUrl is = ", str5);
        if (p(str5)) {
            msn.c();
            str4 = msn.a();
        }
        cdv.b().c(str, str5, pullListener, str4);
    }

    private boolean p(String str) {
        return str.contains("com.huawei.health_HwWear_deviceConfigBeta") || str.contains("com.huawei.health_HwWear_deviceConfig");
    }

    public void e(PullListener pullListener) {
        c("plugin_index", (CommonUtil.ag(this.o) || i()) ? "com.huawei.health_HwWear_deviceConfig" : "com.huawei.health_HwWear_deviceConfigBeta", cdr.a(), pullListener);
    }

    public void c(String str, String str2, String str3, PullListener pullListener) {
        String c = c((String) null, true);
        msq msqVar = new msq();
        mso msoVar = new mso();
        msoVar.b(-1);
        if (TextUtils.isEmpty(c) || TextUtils.isEmpty(str2)) {
            sqo.k("updateIndex base url or configServiceName is empty.");
            LogUtil.h("ResourceHandleManager", "updateIndex base url is empty");
            if (pullListener != null) {
                pullListener.onPullingChange(msqVar, msoVar);
                return;
            }
            return;
        }
        if (TextUtils.isEmpty(str)) {
            sqo.k("updateIndex base fileId is empty.");
            LogUtil.h("ResourceHandleManager", "updateIndex base fileId is empty");
            if (pullListener != null) {
                pullListener.onPullingChange(msqVar, msoVar);
                return;
            }
            return;
        }
        b(str, c + str2, str3, pullListener);
    }

    private void b(String str, String str2, String str3, PullListener pullListener) {
        cdj cdjVar = new cdj();
        cdjVar.c(str2);
        d(str, cdjVar, str3, pullListener);
    }

    private void d(final String str, final cdj cdjVar, final String str2, final PullListener pullListener) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: cdo
                @Override // java.lang.Runnable
                public final void run() {
                    cdv.b().c(str, cdjVar, str2, true, pullListener);
                }
            });
        } else {
            cdv.b().c(str, cdjVar, str2, true, pullListener);
        }
    }

    private void e(cvk cvkVar, String str, PullListener pullListener, msq msqVar, mso msoVar) {
        Object[] objArr = new Object[2];
        objArr[0] = "checkAppVersion enter check version";
        objArr[1] = cvkVar == null ? ",checkAppVersion:ezPluginIndexInfo is null" : "";
        LogUtil.a("ResourceHandleManager", objArr);
        if (cvkVar == null) {
            return;
        }
        String e2 = cvkVar.e();
        cvf d2 = cvkVar.d();
        if (d2 == null) {
            LogUtil.h("ResourceHandleManager", "checkAppVersion no have ApplyRules");
            b(cvkVar, e2, str, pullListener);
            return;
        }
        if (d2.b() == null) {
            LogUtil.h("ResourceHandleManager", "checkAppVersion not have minAppVersion");
            a(cvkVar, str, pullListener, msqVar, msoVar);
            return;
        }
        int m = CommonUtil.m(this.o, d2.b());
        int d3 = CommonUtil.d(this.o);
        Object[] objArr2 = new Object[5];
        objArr2[0] = "checkAppVersion minAppVersionCode is ";
        objArr2[1] = Integer.valueOf(m);
        objArr2[2] = " appCode is ";
        objArr2[3] = Integer.valueOf(d3);
        objArr2[4] = d3 < m ? ",checkAppVersion appVersion less than this plugin minAppVersion" : "";
        LogUtil.a("ResourceHandleManager", objArr2);
        if (d3 < m) {
            msoVar.b(-30);
            if (pullListener != null) {
                pullListener.onPullingChange(msqVar, msoVar);
                return;
            }
            return;
        }
        a(cvkVar, str, pullListener, msqVar, msoVar);
    }

    private void a(cvk cvkVar, String str, PullListener pullListener, msq msqVar, mso msoVar) {
        cvf d2 = cvkVar.d();
        String e2 = cvkVar.e();
        if (this.m != null) {
            if (cdr.c(this.o, d2.a(), this.m.e())) {
                b(cvkVar, e2, str, pullListener);
                return;
            }
            LogUtil.a("ResourceHandleManager", "checkIndexVersion indexVersion less than this plugin minIndexVersion");
            msoVar.b(-20);
            if (pullListener != null) {
                pullListener.onPullingChange(msqVar, msoVar);
                return;
            }
            return;
        }
        LogUtil.h("ResourceHandleManager", "checkIndexVersion mIndexFileStruct is null");
    }

    public void d(msq msqVar) {
        if (msqVar != null) {
            cdv.b().c(msqVar);
        } else {
            LogUtil.h("ResourceHandleManager", "canclePluginDownload cancle task failure ,task param is null");
        }
    }

    public void b(String str, PullListener pullListener) {
        LogUtil.a("ResourceHandleManager", "enter checkPluginNewVersionForWear");
        mso msoVar = new mso();
        msq msqVar = new msq();
        cvk e2 = e(str);
        cvc b2 = b(str);
        if (b2 == null || e2 == null) {
            LogUtil.h("ResourceHandleManager", "checkPluginNewVersionForWear descriptionInfo is null");
            msoVar.b(20);
            if (pullListener != null) {
                pullListener.onPullingChange(msqVar, msoVar);
                return;
            }
            return;
        }
        String a2 = e2.a();
        String b3 = b2.b();
        String str2 = e2.e() + "_version";
        String b4 = SharedPreferenceManager.b(this.o, String.valueOf(1003), str2);
        LogUtil.a("ResourceHandleManager", "checkPluginNewVersionForWear getKey is ", str2, ",spVersion is ", b4, ",indexPluginVersion is:", a2, ",descVersion is:", b3);
        boolean e3 = cdr.e(b3, a2);
        if (TextUtils.isEmpty(b3) || !cdr.e(str)) {
            e3 = true;
        }
        boolean e4 = cdr.e(b4, a2);
        Object[] objArr = new Object[1];
        objArr[0] = (e3 || e4) ? "checkPluginNewVersionForWear have new version" : "checkPluginNewVersionForWear not have new version";
        LogUtil.a("ResourceHandleManager", objArr);
        boolean exists = new File(cdv.b().d(str) + File.separator + str + File.separator + "img").exists();
        boolean exists2 = new File(cdv.b().d(str) + File.separator + str + File.separator + "lang").exists();
        LogUtil.a("ResourceHandleManager", "imgFile is exist:", Boolean.valueOf(exists), "langFile is exist:", Boolean.valueOf(exists2));
        if (e3 || e4 || (!exists && exists2)) {
            msoVar.b(20);
            if (pullListener != null) {
                pullListener.onPullingChange(msqVar, msoVar);
                return;
            }
            return;
        }
        msoVar.b(1);
        if (pullListener != null) {
            pullListener.onPullingChange(msqVar, msoVar);
        }
    }

    public void a(String str, PullListener pullListener) {
        d(str, (CommonUtil.ag(this.o) || i()) ? "com.huawei.health_HwWear_deviceConfig" : "com.huawei.health_HwWear_deviceConfigBeta", pullListener);
    }

    public void d(String str, String str2, PullListener pullListener) {
        e(e(str), str2, pullListener, new msq(), new mso());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v10, types: [android.graphics.Bitmap] */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v2, types: [android.graphics.Bitmap] */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9 */
    public Bitmap Dg_(cvc cvcVar, String str) {
        InputStream inputStream;
        ?? r5;
        Object[] objArr = new Object[2];
        objArr[0] = "loadImageForWear enter loadImageForWear";
        objArr[1] = cvcVar == null ? ",loadImageForWear descriptionInfo is null" : "";
        LogUtil.a("ResourceHandleManager", objArr);
        InputStream inputStream2 = null;
        inputStream2 = null;
        inputStream2 = null;
        Bitmap bitmap = null;
        inputStream2 = null;
        if (cvcVar == null) {
            return null;
        }
        try {
            try {
                String str2 = cdv.b().d(cvcVar.e()) + File.separator + cvcVar.e() + File.separator + "img" + File.separator + str + ".png";
                if (new File(str2).exists()) {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                    options.inInputShareable = true;
                    r5 = BitmapFactory.decodeFile(str2, options);
                    try {
                        LogUtil.a("ResourceHandleManager", "enter loadImageForWear have bitmap imageKey:", str, " fetchPluginUuid:", cvcVar.e());
                        r5 = r5;
                    } catch (Resources.NotFoundException unused) {
                        LogUtil.b("ResourceHandleManager", "loadImageForWear loadImage NotFoundException");
                        IoUtils.e(inputStream2);
                        return r5;
                    }
                } else {
                    BitmapFactory.Options options2 = new BitmapFactory.Options();
                    options2.inPreferredConfig = Bitmap.Config.RGB_565;
                    options2.inPurgeable = true;
                    options2.inInputShareable = true;
                    inputStream = BaseApplication.getContext().getResources().openRawResource(g("hw_device_default"));
                    try {
                        bitmap = BitmapFactory.decodeStream(inputStream, null, options2);
                        LogUtil.a("ResourceHandleManager", "enter loadImageForWear have no bitmap :", str, " fetchPluginUuid:", cvcVar.e());
                        r5 = bitmap;
                        inputStream2 = inputStream;
                    } catch (Resources.NotFoundException unused2) {
                        r5 = bitmap;
                        inputStream2 = inputStream;
                        LogUtil.b("ResourceHandleManager", "loadImageForWear loadImage NotFoundException");
                        IoUtils.e(inputStream2);
                        return r5;
                    } catch (Throwable th) {
                        th = th;
                        IoUtils.e(inputStream);
                        throw th;
                    }
                }
            } catch (Resources.NotFoundException unused3) {
                r5 = inputStream2;
            }
            IoUtils.e(inputStream2);
            return r5;
        } catch (Throwable th2) {
            th = th2;
            inputStream = inputStream2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v12, types: [android.graphics.Bitmap] */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5, types: [android.graphics.Bitmap] */
    /* JADX WARN: Type inference failed for: r7v8 */
    public Bitmap Df_(String str) {
        InputStream inputStream;
        ?? r7;
        Object[] objArr = new Object[2];
        objArr[0] = "enter loadImageForImagePath";
        objArr[1] = TextUtils.isEmpty(str) ? ",loadImageForImagePath imagePath is null" : "";
        LogUtil.a("ResourceHandleManager", objArr);
        InputStream inputStream2 = null;
        inputStream2 = null;
        inputStream2 = null;
        Bitmap bitmap = null;
        inputStream2 = null;
        if (str == null || TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            try {
                if (new File(str).exists()) {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                    options.inInputShareable = true;
                    r7 = BitmapFactory.decodeFile(str, options);
                    try {
                        LogUtil.a("ResourceHandleManager", "enter loadImageForImagePath have bitmap imageKey");
                        r7 = r7;
                    } catch (Resources.NotFoundException unused) {
                        LogUtil.b("ResourceHandleManager", "loadImageForImagePath loadImage NotFoundException");
                        IoUtils.e(inputStream2);
                        return r7;
                    }
                } else {
                    BitmapFactory.Options options2 = new BitmapFactory.Options();
                    options2.inPreferredConfig = Bitmap.Config.RGB_565;
                    options2.inPurgeable = true;
                    options2.inInputShareable = true;
                    inputStream = BaseApplication.getContext().getResources().openRawResource(g("hw_device_default"));
                    try {
                        bitmap = BitmapFactory.decodeStream(inputStream, null, options2);
                        LogUtil.a("ResourceHandleManager", "enter loadImageForImagePath have no bitmap");
                        r7 = bitmap;
                        inputStream2 = inputStream;
                    } catch (Resources.NotFoundException unused2) {
                        r7 = bitmap;
                        inputStream2 = inputStream;
                        LogUtil.b("ResourceHandleManager", "loadImageForImagePath loadImage NotFoundException");
                        IoUtils.e(inputStream2);
                        return r7;
                    } catch (Throwable th) {
                        th = th;
                        IoUtils.e(inputStream);
                        throw th;
                    }
                }
            } catch (Resources.NotFoundException unused3) {
                r7 = inputStream2;
            }
            IoUtils.e(inputStream2);
            return r7;
        } catch (Throwable th2) {
            th = th2;
            inputStream = inputStream2;
        }
    }

    private int g(String str) {
        return BaseApplication.getContext().getResources().getIdentifier(str, "drawable", BaseApplication.getContext().getPackageName());
    }

    public void b() {
        String d2 = cdr.d();
        boolean exists = new File(d2).exists();
        LogUtil.a("ResourceHandleManager", "updateIndexCacheForWear isExistThisIndex is = ", Boolean.valueOf(exists));
        if (exists) {
            String c = cdr.c(this.o, d2);
            LogUtil.a("ResourceHandleManager", "updateIndexCacheForWear indexJson = ", c);
            q(c);
            LogUtil.a("ResourceHandleManager", "updateIndexCacheForWear end");
        }
    }

    public static void d() {
        cdr.b(f644a);
    }

    public void a(String str) {
        File file = new File((cuv.f11488a + str + File.separator) + "description.json");
        if (file.exists()) {
            LogUtil.a("ResourceHandleManager", "isDeleteDescriptionForwear is = ", Boolean.valueOf(file.delete()));
        }
    }

    private static List<String> b(cvc cvcVar, String str) {
        JSONObject b2;
        synchronized (cdk.class) {
            LogUtil.c("ResourceHandleManager", " enter loadStringForWear.");
            File e2 = e(cvcVar);
            ArrayList arrayList = new ArrayList(10);
            if (e2 == null) {
                LogUtil.h("ResourceHandleManager", "loadStringForWear jsonFile == null.");
                return arrayList;
            }
            LogUtil.c("ResourceHandleManager", "loadStringForWear jsonFile.name is ", e2.getName());
            try {
                b2 = cvy.b(e2);
            } catch (JSONException unused) {
                LogUtil.b("ResourceHandleManager", "loadStringForWear JSONException");
            }
            if (b2 == null) {
                return arrayList;
            }
            if (!b2.has(str)) {
                LogUtil.c("ResourceHandleManager", "loadStringForWear jsonObject not has key");
                return arrayList;
            }
            Object obj = b2.get(str);
            if (obj instanceof String) {
                arrayList.add((String) obj);
                return arrayList;
            }
            if (obj instanceof JSONArray) {
                JSONArray jSONArray = (JSONArray) obj;
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    arrayList.add(jSONArray.getString(i));
                }
                return arrayList;
            }
            return arrayList;
        }
    }

    private static File e(cvc cvcVar) {
        Locale locale = Resources.getSystem().getConfiguration().locale;
        return c(cvcVar, locale.getLanguage(), locale.getCountry(), locale.toLanguageTag());
    }

    private static File c(cvc cvcVar, String str, String str2, String str3) {
        String str4;
        LogUtil.c("ResourceHandleManager", " enter getTargetJsonFile.", str2, Constants.LINK, str3);
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("ResourceHandleManager", " getTargetJsonFile TextUtils.isEmpty(language).");
            return null;
        }
        if (TextUtils.isEmpty(str2)) {
            str4 = str + ".json";
        } else {
            str4 = str + "-r" + str2 + ".json";
        }
        String str5 = cdv.b().d(cvcVar.e()) + File.separator + cvcVar.e() + File.separator + "lang";
        File file = new File(str5);
        if (file.exists() && file.isDirectory()) {
            return e(file.listFiles(), str3, str4, str);
        }
        LogUtil.h("ResourceHandleManager", "getTargetJsonFile wrong dir path, not exists or should be dir but is file : ", str5);
        return null;
    }

    private static File e(File[] fileArr, String str, String str2, String str3) {
        String str4;
        if (fileArr == null || fileArr.length == 0) {
            LogUtil.h("ResourceHandleManager", "getFileByName wrong dir.no file. can not find target file, return null.");
            return null;
        }
        if (str != null) {
            String f = f(str);
            if (f != null) {
                String str5 = b.get(f) + ".json";
                LogUtil.a("ResourceHandleManager", "getFileByName fileNameTag : ", str5);
                File e2 = e(fileArr, str5);
                if (e2 != null && e2.length() > 0) {
                    LogUtil.a("ResourceHandleManager", "getFileByName file is ok");
                    return e2;
                }
                File e3 = e(fileArr, "en.json");
                if (e3 != null && e3.length() > 0) {
                    LogUtil.a("ResourceHandleManager", "getFileByName en file is ok");
                    return e3;
                }
            }
            String[] strArr = {"es-AR", "es-BO", "es-CL", "es-CO", "es-CR", "es-CU", "es-DO", "es-EC", "es-GT", "es-HN", "es-MX", "es-NI", "es-PA", "es-PE", "es-PR", "es-PY", "es-SV", "es-UY", "es-VE"};
            for (int i = 0; i < 19; i++) {
                if (strArr[i].equals(str)) {
                    str4 = "es-rUS.json";
                    break;
                }
            }
        }
        str4 = str2;
        for (File file : fileArr) {
            if (str4.equals(file.getName())) {
                return file;
            }
        }
        LogUtil.c("ResourceHandleManager", "getFileByName can not find file : ", str4);
        if (str4.contains("-r")) {
            str4 = cvy.d(str3) + ".json";
            for (File file2 : fileArr) {
                if (str4.equals(file2.getName())) {
                    return file2;
                }
            }
        }
        LogUtil.c("ResourceHandleManager", "getFileByName can not find file : ", str4);
        for (File file3 : fileArr) {
            if ("en.json".equals(file3.getName())) {
                return file3;
            }
        }
        LogUtil.c("ResourceHandleManager", "getFileByName can not find file : ", "en.json");
        LogUtil.h("ResourceHandleManager", "getFileByName can not find target file, return null.");
        return null;
    }

    private static File e(File[] fileArr, String str) {
        for (File file : fileArr) {
            if (str.equals(file.getName())) {
                return file;
            }
        }
        return null;
    }

    private static String f(String str) {
        String str2;
        String[] strArr = d;
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                str2 = null;
                break;
            }
            str2 = strArr[i];
            if (str.startsWith(str2)) {
                break;
            }
            i++;
        }
        LogUtil.c("ResourceHandleManager", "changeTag is ", str, "after changeTag is ", str2);
        return str2;
    }

    protected static List<String> c(String str, cvc cvcVar) {
        ArrayList arrayList = new ArrayList(16);
        List<String> b2 = b(cvcVar, str);
        if (b2.size() == 0) {
            return arrayList;
        }
        for (int i = 0; i < b2.size(); i++) {
            List<String> b3 = b(cvcVar, str + "Other" + i);
            String str2 = b2.get(i);
            if (b3.size() == 0) {
                arrayList.add(str2);
            } else {
                List<Object> a2 = a(b3);
                int i2 = 0;
                while (i2 < a2.size()) {
                    i2++;
                    str2 = str2.replace("%" + i2 + "$d", "%" + i2 + "$s");
                }
                arrayList.add(String.format(Locale.ROOT, str2, a2.toArray()));
            }
        }
        return arrayList;
    }

    private static List<Object> a(List<String> list) {
        ArrayList arrayList = new ArrayList(16);
        for (String str : list) {
            if (s(str)) {
                try {
                    String e2 = UnitUtil.e(Long.parseLong(str), 1, 0);
                    LogUtil.c("ResourceHandleManager", "isNumber formatValue:", e2);
                    arrayList.add(e2);
                } catch (NumberFormatException e3) {
                    LogUtil.b("ResourceHandleManager", e3.getMessage());
                }
            } else {
                try {
                    String[] split = str.split("\\.");
                    if (split.length > 1) {
                        str = UnitUtil.e(Double.parseDouble(str), 1, split[1].length());
                    }
                } catch (NumberFormatException unused) {
                }
                LogUtil.c("ResourceHandleManager", "string formatValue:", str);
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    private static boolean s(String str) {
        return Pattern.compile("[0-9]+").matcher(str).matches();
    }

    protected String c(String str, boolean z) {
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), "developeroptions", "developerswitch");
        String b3 = SharedPreferenceManager.b(BaseApplication.getContext(), "APP_TEST_TO_BETA", "app_test_change_beta_url");
        LogUtil.a("ResourceHandleManager", "developer:", b2);
        if (CommonUtil.cc() && "1".equals(b2) && !TextUtils.isEmpty(b3)) {
            return "https://" + b3 + "/servicesupport/updateserver/data/";
        }
        DownloadPluginUrl downloadPluginUrl = this.n;
        if (downloadPluginUrl != null && downloadPluginUrl.isNetworkConnected()) {
            return this.n.getDownloadPluginUrl(str, z);
        }
        LogUtil.h("ResourceHandleManager", "updateIndex download is null");
        return "";
    }

    private String n(String str) {
        String m = m(str);
        return TextUtils.isEmpty(m) ? "plugin_index" : msr.d.get(m);
    }

    private String m(String str) {
        String str2 = "";
        if (CloudUtils.d() && !CommonUtil.cc()) {
            return "";
        }
        if (CommonUtil.bv()) {
            Iterator<Map.Entry<String, String>> it = msr.f15154a.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry<String, String> next = it.next();
                if (TextUtils.equals(next.getValue(), str)) {
                    str2 = next.getKey();
                    break;
                }
            }
        } else {
            Iterator<Map.Entry<String, String>> it2 = msr.b.entrySet().iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                Map.Entry<String, String> next2 = it2.next();
                if (TextUtils.equals(next2.getValue(), str)) {
                    str2 = next2.getKey();
                    break;
                }
            }
        }
        LogUtil.h("ResourceHandleManager", "getThirdDeviceType deviceType is ", str2);
        return str2;
    }

    private static boolean i() {
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), "developeroptions", "developerswitch");
        String b3 = SharedPreferenceManager.b(BaseApplication.getContext(), "APP_TEST_SITE", "app_test_site_type");
        LogUtil.a("ResourceHandleManager", "deviceSite:", b3);
        return CommonUtil.as() && "1".equals(b2) && "release".equals(b3);
    }
}
