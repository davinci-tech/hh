package health.compact.a;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Looper;
import android.text.TextUtils;
import com.huawei.haf.download.DownloadPluginUrl;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.pluginmgr.filedownload.PullListener;
import defpackage.koq;
import defpackage.mrp;
import defpackage.mru;
import defpackage.mrv;
import defpackage.msa;
import defpackage.msb;
import defpackage.msc;
import defpackage.msi;
import defpackage.msj;
import defpackage.msk;
import defpackage.msl;
import defpackage.msn;
import defpackage.mso;
import defpackage.msq;
import defpackage.msr;
import defpackage.mst;
import defpackage.msx;
import health.compact.a.EzPluginManager;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class EzPluginManager extends HwBaseManager {
    private static volatile EzPluginManager d;
    private msi e;
    private msi f;
    private msi g;
    private msi h;
    private msi i;
    private msi j;
    private Context k;
    private msi l;
    private CopyOnWriteArrayList<msc> m;
    private DownloadPluginUrl n;
    private msi o;
    private msi p;
    private msi q;
    private msi r;
    private msi s;
    private msi t;
    private msi u;
    private msi v;
    private msi w;
    private msi y;
    private static String[] b = {"sr-Latn", "jv-Latn"};

    /* renamed from: a, reason: collision with root package name */
    private static HashMap<String, String> f13115a = new HashMap<String, String>() { // from class: health.compact.a.EzPluginManager.4
        private static final long serialVersionUID = 1212859686823339267L;

        {
            put("sr-Latn", "b+sr+Latn");
            put("jv-Latn", "b+jv+Latn");
        }
    };
    private static List<msc> c = new ArrayList(16);

    private EzPluginManager(Context context) {
        super(context);
        this.r = new msi();
        this.e = new msi();
        this.g = new msi();
        this.i = new msi();
        this.h = new msi();
        this.j = new msi();
        this.l = new msi();
        this.y = new msi();
        this.s = new msi();
        this.v = new msi();
        this.o = new msi();
        this.q = new msi();
        this.f = new msi();
        this.w = new msi();
        this.u = new msi();
        this.t = new msi();
        this.p = new msi();
        this.m = new CopyOnWriteArrayList<>();
        this.k = BaseApplication.getContext();
        c(msn.c().d());
    }

    public static EzPluginManager a() {
        if (d == null) {
            synchronized (msl.b) {
                if (d == null) {
                    d = new EzPluginManager(BaseApplication.getContext());
                }
            }
        }
        return d;
    }

    public void c(DownloadPluginUrl downloadPluginUrl) {
        com.huawei.hwlogsmodel.LogUtil.a("EzPlugin_EzPluginManager", "enter setDownloadPluginUrl ");
        this.n = downloadPluginUrl;
    }

    public msa d(String str) {
        if (TextUtils.isEmpty(str)) {
            com.huawei.hwlogsmodel.LogUtil.h("EzPlugin_EzPluginManager", "getPluginIndexInfo uuid is empty");
            return null;
        }
        List<msa> f = f();
        if (!koq.b(f)) {
            com.huawei.hwlogsmodel.LogUtil.c("EzPlugin_EzPluginManager", "getPluginIndexInfo ezPluginInfos size is :", Integer.valueOf(f.size()));
            for (msa msaVar : f) {
                if (str.equals(msaVar.b())) {
                    return msaVar;
                }
            }
        }
        return null;
    }

    public msc c(String str) {
        msc d2 = msl.d(this.r, c, str);
        if (d2 != null) {
            com.huawei.hwlogsmodel.LogUtil.c("EzPlugin_EzPluginManager", "getPluginInfo get cachePluginInfo");
            return d2;
        }
        boolean f = f(str);
        com.huawei.hwlogsmodel.LogUtil.a("EzPlugin_EzPluginManager", "enter to getPluginInfo isDeprecated is :", Boolean.valueOf(f));
        msc b2 = !f ? msl.b(this.k, str) : null;
        d(b2, str);
        return b2;
    }

    public void d(msc mscVar, String str) {
        msl.a(c, mscVar, str);
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 20010;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private msi k(String str) {
        char c2;
        msi t = t(str);
        if (t != null && !TextUtils.isEmpty(t.b())) {
            return t;
        }
        str.hashCode();
        switch (str.hashCode()) {
            case -1742636370:
                if (str.equals("HDK_ROPE_SKIPPING")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -1605993952:
                if (str.equals("HDK_ROWING_MACHINE")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case -1371741911:
                if (str.equals("HDK_SMART_WATCH")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case -690748027:
                if (str.equals("HDK_ELLIPTICAL_MACHINE")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 129710486:
                if (str.equals("HDK_TREADMILL")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case 232428455:
                if (str.equals("HDK_SMART_PILLOW")) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            case 495164536:
                if (str.equals("HDK_EXERCISE_BIKE")) {
                    c2 = 6;
                    break;
                }
                c2 = 65535;
                break;
            case 1132613073:
                if (str.equals("HDK_WALKING_MACHINE")) {
                    c2 = 7;
                    break;
                }
                c2 = 65535;
                break;
            case 2066052828:
                if (str.equals("HDK_MASSAGE_GUN")) {
                    c2 = '\b';
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        switch (c2) {
            case 0:
                t = this.s;
                break;
            case 1:
                t = this.q;
                break;
            case 2:
                t = this.u;
                break;
            case 3:
                t = this.f;
                break;
            case 4:
                t = this.v;
                break;
            case 5:
                t = this.t;
                break;
            case 6:
                t = this.o;
                break;
            case 7:
                t = this.w;
                break;
            case '\b':
                t = this.p;
                break;
        }
        if (t == null || TextUtils.isEmpty(t.b())) {
            return null;
        }
        return t;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private msi t(String str) {
        char c2;
        str.hashCode();
        switch (str.hashCode()) {
            case -2017367641:
                if (str.equals("HDK_BODY_TEMPERATURE")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -1079925272:
                if (str.equals("HDK_WEIGHT")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case -187341623:
                if (str.equals("HDK_HEART_RATE")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 376592149:
                if (str.equals("HDK_BLOOD_OXYGEN")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 1279483514:
                if (str.equals("HDK_BLOOD_PRESSURE")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case 1494700537:
                if (str.equals("HDK_ECG")) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            case 1816850689:
                if (str.equals("HDK_BLOOD_SUGAR")) {
                    c2 = 6;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        switch (c2) {
            case 0:
                return this.y;
            case 1:
                return this.j;
            case 2:
                return this.l;
            case 3:
                return this.e;
            case 4:
                return this.i;
            case 5:
                return this.g;
            case 6:
                return this.h;
            default:
                return null;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void a(String str, msi msiVar) {
        char c2;
        if (b(str, msiVar)) {
        }
        str.hashCode();
        switch (str.hashCode()) {
            case -1605993952:
                if (str.equals("HDK_ROWING_MACHINE")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -1371741911:
                if (str.equals("HDK_SMART_WATCH")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case -690748027:
                if (str.equals("HDK_ELLIPTICAL_MACHINE")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 129710486:
                if (str.equals("HDK_TREADMILL")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 232428455:
                if (str.equals("HDK_SMART_PILLOW")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case 495164536:
                if (str.equals("HDK_EXERCISE_BIKE")) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            case 1132613073:
                if (str.equals("HDK_WALKING_MACHINE")) {
                    c2 = 6;
                    break;
                }
                c2 = 65535;
                break;
            case 2066052828:
                if (str.equals("HDK_MASSAGE_GUN")) {
                    c2 = 7;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        switch (c2) {
            case 0:
                this.q = msiVar;
                break;
            case 1:
                this.u = msiVar;
                break;
            case 2:
                this.f = msiVar;
                break;
            case 3:
                this.v = msiVar;
                break;
            case 4:
                this.t = msiVar;
                break;
            case 5:
                this.o = msiVar;
                break;
            case 6:
                this.w = msiVar;
                break;
            case 7:
                this.p = msiVar;
                break;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private boolean b(String str, msi msiVar) {
        char c2;
        str.hashCode();
        switch (str.hashCode()) {
            case -2017367641:
                if (str.equals("HDK_BODY_TEMPERATURE")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -1742636370:
                if (str.equals("HDK_ROPE_SKIPPING")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case -1079925272:
                if (str.equals("HDK_WEIGHT")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case -187341623:
                if (str.equals("HDK_HEART_RATE")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 376592149:
                if (str.equals("HDK_BLOOD_OXYGEN")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case 1279483514:
                if (str.equals("HDK_BLOOD_PRESSURE")) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            case 1494700537:
                if (str.equals("HDK_ECG")) {
                    c2 = 6;
                    break;
                }
                c2 = 65535;
                break;
            case 1816850689:
                if (str.equals("HDK_BLOOD_SUGAR")) {
                    c2 = 7;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        switch (c2) {
            case 0:
                this.y = msiVar;
                return true;
            case 1:
                this.s = msiVar;
                return true;
            case 2:
                this.j = msiVar;
                return true;
            case 3:
                this.l = msiVar;
                return true;
            case 4:
                this.e = msiVar;
                return true;
            case 5:
                this.i = msiVar;
                return true;
            case 6:
                this.g = msiVar;
                return true;
            case 7:
                this.h = msiVar;
                return true;
            default:
                return false;
        }
    }

    public List<msa> b() {
        synchronized (this) {
            com.huawei.hwlogsmodel.LogUtil.a("EzPlugin_EzPluginManager", "getIndexList enter to GetPluginsIndexInfos");
            ArrayList arrayList = new ArrayList();
            List<msx> c2 = mst.a().c();
            ArrayList<msx> arrayList2 = new ArrayList();
            arrayList2.addAll(c2);
            for (msx msxVar : arrayList2) {
                if (msxVar == null) {
                    com.huawei.hwlogsmodel.LogUtil.a("EzPlugin_EzPluginManager", "getIndexList devicePluginInfoBeans is null ");
                } else {
                    List<msa> i = msxVar.i();
                    if (koq.c(i)) {
                        arrayList.addAll(i);
                    }
                }
            }
            if (this.r.d() != null) {
                com.huawei.hwlogsmodel.LogUtil.a("EzPlugin_EzPluginManager", "mIndexFileStruct.getPlugins start");
                arrayList.addAll(this.r.d());
                return arrayList;
            }
            String c3 = msl.c();
            boolean exists = new File(c3).exists();
            com.huawei.hwlogsmodel.LogUtil.a("EzPlugin_EzPluginManager", "getIndexList isExistThisIndex is =", Boolean.valueOf(exists));
            if (exists) {
                String e = msl.e(this.k, c3);
                com.huawei.hwlogsmodel.LogUtil.c("EzPlugin_EzPluginManager", "getIndex indexJson = ", e);
                p(e);
                if (this.r.d() != null) {
                    com.huawei.hwlogsmodel.LogUtil.a("EzPlugin_EzPluginManager", "mIndexFileStruct.getPlugins");
                    arrayList.addAll(this.r.d());
                    return arrayList;
                }
            }
            return null;
        }
    }

    public List<msa> e(String str) {
        msi k = k(str);
        List<msa> d2 = k != null ? k.d() : null;
        if (d2 != null) {
            return d2;
        }
        String a2 = msl.a(str);
        if (!new File(a2).exists()) {
            return d2;
        }
        String e = msl.e(this.k, a2);
        Object[] objArr = new Object[3];
        objArr[0] = "index Data:";
        objArr[1] = e;
        objArr[2] = TextUtils.isEmpty(e) ? ",indexJson is null" : "";
        com.huawei.hwlogsmodel.LogUtil.c("EzPlugin_EzPluginManager", objArr);
        if (TextUtils.isEmpty(e)) {
            return null;
        }
        msi c2 = msb.c(e);
        a(str, c2);
        return c2.d();
    }

    private List<msa> f() {
        com.huawei.hwlogsmodel.LogUtil.c("EzPlugin_EzPluginManager", "getAllIndexList");
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = msr.c.keySet().iterator();
        while (it.hasNext()) {
            List<msa> e = e(it.next());
            if (e != null) {
                arrayList.addAll(e);
            }
        }
        List<msa> b2 = b();
        if (b2 != null) {
            arrayList.addAll(b2);
        }
        return arrayList;
    }

    public List<msa> d() {
        com.huawei.hwlogsmodel.LogUtil.c("EzPlugin_EzPluginManager", "getThirdPartIndexList");
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = msr.c.keySet().iterator();
        while (it.hasNext()) {
            List<msa> e = e(it.next());
            if (e != null) {
                arrayList.addAll(e);
            }
        }
        return arrayList;
    }

    private void p(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            com.huawei.hwlogsmodel.LogUtil.h("EzPlugin_EzPluginManager", "parseVersionIndexFile indexJson is null");
            return;
        }
        try {
            msi msiVar = this.r;
            if (msiVar == null) {
                str2 = "";
            } else {
                str2 = msiVar.b();
            }
            if (TextUtils.isEmpty(str2)) {
                this.r = msb.c(str);
                msl.a(c);
            } else {
                if (str2.equals(new JSONObject(str).optString("version"))) {
                    return;
                }
                this.r = msb.c(str);
                msl.a(c);
            }
        } catch (JSONException unused) {
            com.huawei.hwlogsmodel.LogUtil.b("EzPlugin_EzPluginManager", "parseVersionIndexFile JSONException");
        }
    }

    @Deprecated
    public msc c(int i) {
        msc c2;
        List<msa> b2 = b();
        if (b2 == null || b2.size() == 0) {
            return null;
        }
        com.huawei.hwlogsmodel.LogUtil.c("EzPlugin_EzPluginManager", "getPluginInfoByType indexInfos.size > 0");
        for (msa msaVar : b2) {
            if (msaVar.b() != null && (msaVar.k().equals("SMART_WATCH") || msaVar.k().equals("SMART_BAND"))) {
                if (a().g(msaVar.b()) && (c2 = a().c(msaVar.b())) != null && c2.g() != null && c2.g().c() == i) {
                    return c2;
                }
            }
        }
        return null;
    }

    public boolean g(String str) {
        return msl.e(str, f(str));
    }

    public boolean f(String str) {
        return msl.c(str, d(str));
    }

    public void e(String str, PullListener pullListener) {
        e(str, (CommonUtil.ag(this.k) || i()) ? "com.huawei.health_HwWear_deviceConfig" : "com.huawei.health_HwWear_deviceConfigBeta", "", pullListener);
    }

    public void e(final String str, final String str2, final String str3, final PullListener pullListener) {
        PullListener pullListener2 = new PullListener() { // from class: mrz
            @Override // com.huawei.pluginmgr.filedownload.PullListener
            public final void onPullingChange(msq msqVar, mso msoVar) {
                EzPluginManager.this.e(str2, str, str3, pullListener, msqVar, msoVar);
            }
        };
        String o = o(str2);
        com.huawei.hwlogsmodel.LogUtil.a("EzPlugin_EzPluginManager", "updateDescription file id ", o);
        d(o, str2, m(str2), pullListener2);
    }

    public /* synthetic */ void e(String str, String str2, String str3, PullListener pullListener, msq msqVar, mso msoVar) {
        int i = msoVar.i();
        if (i != 1 && i != -11) {
            if (i == 0) {
                com.huawei.hwlogsmodel.LogUtil.a("EzPlugin_EzPluginManager", "updateDescription Downloading index file");
                return;
            } else {
                if (pullListener != null) {
                    pullListener.onPullingChange(msqVar, msoVar);
                    return;
                }
                return;
            }
        }
        String m = m(str);
        com.huawei.hwlogsmodel.LogUtil.a("EzPlugin_EzPluginManager", "updateDescription savePathIndex is =", m);
        String e = msl.e(this.k, m);
        com.huawei.hwlogsmodel.LogUtil.c("EzPlugin_EzPluginManager", "updateDescription indexJson = ", e);
        p(e);
        SharedPreferenceManager.e(this.k, String.valueOf(getModuleId()), "updateTime", String.valueOf(new Date().getTime()), (StorageParams) null);
        msa d2 = d(str2);
        String str4 = str + str3;
        if (TextUtils.equals(str3, "?deviceType=bodyFatScales")) {
            com.huawei.hwlogsmodel.LogUtil.a("EzPlugin_EzPluginManager", "matchRules is equals MATCH_RULES");
            str4 = mrp.e(this.k, "HDK_WEIGHT");
        }
        b(d2, str4, pullListener, msqVar, msoVar);
    }

    private String m(String str) {
        String q = q(str);
        return !TextUtils.isEmpty(q) ? msl.a(q) : msl.c();
    }

    public void d(final String str, final PullListener pullListener) {
        msc c2 = c(str);
        if (c2 != null) {
            a(str, this.n, pullListener, c2);
        } else {
            e(str, new PullListener() { // from class: mry
                @Override // com.huawei.pluginmgr.filedownload.PullListener
                public final void onPullingChange(msq msqVar, mso msoVar) {
                    EzPluginManager.this.c(str, pullListener, msqVar, msoVar);
                }
            });
        }
    }

    public /* synthetic */ void c(String str, PullListener pullListener, msq msqVar, mso msoVar) {
        int i = msoVar.i();
        if (i == 1) {
            if (c(str) != null) {
                d(str, pullListener);
                return;
            } else {
                com.huawei.hwlogsmodel.LogUtil.h("EzPlugin_EzPluginManager", "inform error");
                return;
            }
        }
        if (i == 0 || pullListener == null) {
            return;
        }
        pullListener.onPullingChange(msqVar, msoVar);
    }

    private String l(String str) {
        String str2 = str + "com.huawei.health_HwWear_deviceConfigBeta";
        if (!CommonUtil.ag(this.k) && !i()) {
            return str2;
        }
        return str + "com.huawei.health_HwWear_deviceConfig";
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void a(final String str, final DownloadPluginUrl downloadPluginUrl, final PullListener pullListener, final msc mscVar) {
        String str2;
        if (downloadPluginUrl == null) {
            str2 = "";
        } else {
            if (Looper.getMainLooper() == Looper.myLooper()) {
                ThreadPoolManager.d().execute(new Runnable() { // from class: msg
                    @Override // java.lang.Runnable
                    public final void run() {
                        EzPluginManager.this.a(str, downloadPluginUrl, pullListener, mscVar);
                    }
                });
                return;
            }
            str2 = downloadPluginUrl.getDownloadPluginUrl(null, true);
        }
        String l = l(str2);
        com.huawei.hwlogsmodel.LogUtil.a("EzPlugin_EzPluginManager", "startUpdatePlugin pluginUrl is = ", l);
        msj.a().b(str, l, msn.a(), mscVar, pullListener);
    }

    public void b(String str, String str2, PullListener pullListener) {
        a(str, str2, "", pullListener);
    }

    public void a(final String str, final String str2, String str3, final PullListener pullListener) {
        String str4;
        msc c2 = c(str);
        if (c2 != null && !TextUtils.isEmpty(str) && str.equals(c2.a())) {
            String str5 = str2 + str3;
            if (TextUtils.equals(str3, "?deviceType=bodyFatScales")) {
                com.huawei.hwlogsmodel.LogUtil.a("EzPlugin_EzPluginManager", "matchRules is equals MATCH_RULES");
                str5 = mrp.e(this.k, "HDK_WEIGHT");
            }
            DownloadPluginUrl downloadPluginUrl = this.n;
            if (downloadPluginUrl != null) {
                str4 = downloadPluginUrl.getDownloadPluginUrl(null, true);
            } else {
                com.huawei.hwlogsmodel.LogUtil.c("EzPlugin_EzPluginManager", "updateThirdPartyPlugin : mDownloadPluginUrl = null");
                str4 = "";
            }
            String str6 = str4 + str5;
            com.huawei.hwlogsmodel.LogUtil.a("EzPlugin_EzPluginManager", "updateThirdPartyPlugin thirdPartyPluginUrl is = ", str6);
            mru mruVar = new mru(str, str6, null, c2);
            msj.a().a(str + "_new_v1.1", mruVar, pullListener);
            return;
        }
        e(str, str2, str3, new PullListener() { // from class: msf
            @Override // com.huawei.pluginmgr.filedownload.PullListener
            public final void onPullingChange(msq msqVar, mso msoVar) {
                EzPluginManager.this.d(str, str2, pullListener, msqVar, msoVar);
            }
        });
    }

    public /* synthetic */ void d(String str, String str2, PullListener pullListener, msq msqVar, mso msoVar) {
        int i = msoVar.i();
        if (i == 1) {
            if (c(str) != null) {
                b(str, str2, pullListener);
                return;
            } else {
                com.huawei.hwlogsmodel.LogUtil.h("EzPlugin_EzPluginManager", "updateThirdPartyPlugininform error");
                return;
            }
        }
        if (i == 0) {
            com.huawei.hwlogsmodel.LogUtil.a("EzPlugin_EzPluginManager", "updateThirdPartyPlugin description file is updating");
        } else if (pullListener != null) {
            pullListener.onPullingChange(msqVar, msoVar);
        }
    }

    private void b(msa msaVar, String str, String str2, PullListener pullListener) {
        if (msaVar != null) {
            if (!f(str)) {
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
        com.huawei.hwlogsmodel.LogUtil.h("EzPlugin_EzPluginManager", "downloadDescriptioninform error");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void d(final String str, final DownloadPluginUrl downloadPluginUrl, final String str2, final PullListener pullListener) {
        String str3;
        if (downloadPluginUrl == null) {
            str3 = "";
        } else {
            if (Looper.getMainLooper() == Looper.myLooper()) {
                ThreadPoolManager.d().execute(new Runnable() { // from class: msh
                    @Override // java.lang.Runnable
                    public final void run() {
                        EzPluginManager.this.d(str, downloadPluginUrl, str2, pullListener);
                    }
                });
                return;
            }
            str3 = downloadPluginUrl.getDownloadPluginUrl(null, true);
        }
        String str4 = str3 + str2;
        com.huawei.hwlogsmodel.LogUtil.a("EzPlugin_EzPluginManager", "startUpdateDescription pluginUrl is = ", str4);
        msj.a().c(str, str4, pullListener, s(str4) ? msn.a() : null);
    }

    private boolean s(String str) {
        return str.contains("com.huawei.health_HwWear_deviceConfigBeta") || str.contains("com.huawei.health_HwWear_deviceConfig");
    }

    public void b(PullListener pullListener) {
        d("plugin_index", (CommonUtil.ag(this.k) || i()) ? "com.huawei.health_HwWear_deviceConfig" : "com.huawei.health_HwWear_deviceConfigBeta", msl.c(), pullListener);
    }

    public void d(String str, String str2, String str3, PullListener pullListener) {
        String b2 = b((String) null, true);
        msq msqVar = new msq();
        mso msoVar = new mso();
        msoVar.b(-1);
        if (TextUtils.isEmpty(b2) || TextUtils.isEmpty(str2)) {
            com.huawei.hwlogsmodel.LogUtil.h("EzPlugin_EzPluginManager", "updateIndex base url is empty");
            if (pullListener != null) {
                pullListener.onPullingChange(msqVar, msoVar);
                return;
            }
            return;
        }
        if (TextUtils.isEmpty(str)) {
            com.huawei.hwlogsmodel.LogUtil.h("EzPlugin_EzPluginManager", "updateIndex base fileId is empty");
            if (pullListener != null) {
                pullListener.onPullingChange(msqVar, msoVar);
                return;
            }
            return;
        }
        b(str, b2 + str2, str3, pullListener);
    }

    public void e(String str, String str2, PullListener pullListener) {
        DownloadPluginUrl downloadPluginUrl = this.n;
        if (downloadPluginUrl != null) {
            com.huawei.hwlogsmodel.LogUtil.a("EzPlugin_EzPluginManager", "updateIndex mDownloadPluginUrl=", downloadPluginUrl);
        }
        DownloadPluginUrl downloadPluginUrl2 = this.n;
        if (downloadPluginUrl2 != null && !downloadPluginUrl2.isNetworkConnected()) {
            com.huawei.hwlogsmodel.LogUtil.a("EzPlugin_EzPluginManager", "updateIndex isNetworkConnected = false");
        } else {
            com.huawei.hwlogsmodel.LogUtil.h("EzPlugin_EzPluginManager", "updateIndex download is null");
            c(str, str2, msl.a(str), pullListener);
        }
    }

    private void b(String str, String str2, String str3, PullListener pullListener) {
        mru mruVar = new mru();
        mruVar.e(str2);
        d(str, mruVar, str3, pullListener);
    }

    private void d(final String str, final mru mruVar, final String str2, final PullListener pullListener) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: mse
                @Override // java.lang.Runnable
                public final void run() {
                    msj.a().a(str, mruVar, str2, true, pullListener);
                }
            });
        } else {
            msj.a().a(str, mruVar, str2, true, pullListener);
        }
    }

    public void c() {
        DownloadPluginUrl downloadPluginUrl = this.n;
        if (downloadPluginUrl == null) {
            com.huawei.hwlogsmodel.LogUtil.h("EzPlugin_EzPluginManager", "resetIndexEtag mDownloadPluginUrl = null");
            return;
        }
        String downloadPluginUrl2 = downloadPluginUrl.getDownloadPluginUrl(null, true);
        if (CommonUtil.ag(this.k)) {
            Iterator<String> it = msr.f15154a.values().iterator();
            while (it.hasNext()) {
                String str = downloadPluginUrl2 + it.next();
                com.huawei.hwlogsmodel.LogUtil.c("EzPlugin_EzPluginManager", "resetIndexEtag indexDownLoadUrlRelease = ", str);
                msn.c().d(str, "");
            }
        } else {
            Iterator<String> it2 = msr.b.values().iterator();
            while (it2.hasNext()) {
                String str2 = downloadPluginUrl2 + it2.next();
                com.huawei.hwlogsmodel.LogUtil.c("EzPlugin_EzPluginManager", "resetIndexEtag indexDownLoadUrlBeta = ", str2);
                msn.c().d(str2, "");
            }
        }
        String l = l(downloadPluginUrl2);
        com.huawei.hwlogsmodel.LogUtil.c("EzPlugin_EzPluginManager", "resetIndexEtag hwWearPluginIndexUrl = ", l);
        msn.c().d(l, "");
        msn.c().d(l + "device_index_all", "");
        for (String str3 : msr.d.values()) {
            com.huawei.hwlogsmodel.LogUtil.c("EzPlugin_EzPluginManager", "resetIndexEtag indexFileId = ", str3);
            msn.c().a(str3, "");
        }
        msn.c().a("plugin_index", "");
        msn.c().a("device_index_all", "");
    }

    private void y(String str) {
        String str2;
        if (this.n == null) {
            com.huawei.hwlogsmodel.LogUtil.h("EzPlugin_EzPluginManager", "resetIndexEtagAndFileVer mDownloadPluginUrl is null");
            return;
        }
        health.compact.a.hwlogsmodel.ReleaseLogUtil.e("DEVMGR_EzPlugin_EzPluginManager", "resetIndexEtagAndFileVer deviceType is ", str);
        String downloadPluginUrl = this.n.getDownloadPluginUrl(null, true);
        if (CommonUtil.ag(this.k)) {
            str2 = downloadPluginUrl + msr.f15154a.get(str);
        } else {
            str2 = downloadPluginUrl + msr.b.get(str);
        }
        com.huawei.hwlogsmodel.LogUtil.c("EzPlugin_EzPluginManager", "updateAndParseThirdIndex indexDownLoadUrl = ", str2);
        msn.c().d(str2, "");
        String str3 = msr.d.get(str);
        com.huawei.hwlogsmodel.LogUtil.c("EzPlugin_EzPluginManager", "updateAndParseThirdIndex indexFileId = ", str3);
        msn.c().a(str3, "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j, reason: merged with bridge method [inline-methods] */
    public void c(final String str, final String str2, final String str3, final PullListener pullListener) {
        String str4;
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: msd
                @Override // java.lang.Runnable
                public final void run() {
                    EzPluginManager.this.c(str, str2, str3, pullListener);
                }
            });
            return;
        }
        DownloadPluginUrl downloadPluginUrl = this.n;
        if (downloadPluginUrl != null) {
            str4 = downloadPluginUrl.getDownloadPluginUrl(null, true);
        } else {
            com.huawei.hwlogsmodel.LogUtil.c("EzPlugin_EzPluginManager", "startUpdateThirdIndex : mDownloadPluginUrl = null");
            str4 = "";
        }
        mru mruVar = new mru();
        mruVar.e(str4 + str2);
        msj.a().a(msr.d.get(str), mruVar, str3, true, pullListener);
    }

    private void b(msa msaVar, String str, PullListener pullListener, msq msqVar, mso msoVar) {
        Object[] objArr = new Object[2];
        objArr[0] = "checkAppVersion enter check version";
        objArr[1] = msaVar == null ? ",checkAppVersion:ezPluginIndexInfo is null" : "";
        com.huawei.hwlogsmodel.LogUtil.a("EzPlugin_EzPluginManager", objArr);
        if (msaVar == null) {
            return;
        }
        String b2 = msaVar.b();
        msk j = msaVar.j();
        if (j == null) {
            com.huawei.hwlogsmodel.LogUtil.h("EzPlugin_EzPluginManager", "checkAppVersion no have ApplyRules");
            b(msaVar, b2, str, pullListener);
            return;
        }
        if (j.a() == null) {
            com.huawei.hwlogsmodel.LogUtil.h("EzPlugin_EzPluginManager", "checkAppVersion not have minAppVersion");
            a(msaVar, str, pullListener, msqVar, msoVar);
            return;
        }
        int m = CommonUtil.m(this.k, j.a());
        int d2 = CommonUtil.d(this.k);
        Object[] objArr2 = new Object[5];
        objArr2[0] = "checkAppVersion minAppVersionCode is ";
        objArr2[1] = Integer.valueOf(m);
        objArr2[2] = " appCode is ";
        objArr2[3] = Integer.valueOf(d2);
        objArr2[4] = d2 < m ? ",checkAppVersion appVersion less than this plugin minAppVersion" : "";
        com.huawei.hwlogsmodel.LogUtil.a("EzPlugin_EzPluginManager", objArr2);
        if (d2 < m) {
            msoVar.b(-30);
            if (pullListener != null) {
                pullListener.onPullingChange(msqVar, msoVar);
                return;
            }
            return;
        }
        a(msaVar, str, pullListener, msqVar, msoVar);
    }

    private void a(msa msaVar, String str, PullListener pullListener, msq msqVar, mso msoVar) {
        msk j = msaVar.j();
        String b2 = msaVar.b();
        if (this.r != null) {
            if (msl.e(this.k, j.b(), this.r.b())) {
                b(msaVar, b2, str, pullListener);
                return;
            }
            com.huawei.hwlogsmodel.LogUtil.a("EzPlugin_EzPluginManager", "checkIndexVersion indexVersion less than this plugin minIndexVersion");
            msoVar.b(-20);
            if (pullListener != null) {
                pullListener.onPullingChange(msqVar, msoVar);
                return;
            }
            return;
        }
        com.huawei.hwlogsmodel.LogUtil.h("EzPlugin_EzPluginManager", "checkIndexVersion mIndexFileStruct is null");
    }

    public void a(msq msqVar) {
        if (msqVar != null) {
            msj.a().b(msqVar);
        } else {
            com.huawei.hwlogsmodel.LogUtil.h("EzPlugin_EzPluginManager", "canclePluginDownload cancle task failure ,task param is null");
        }
    }

    public void b(String str, PullListener pullListener) {
        com.huawei.hwlogsmodel.LogUtil.a("EzPlugin_EzPluginManager", "enter checkPluginNewVersionForWear");
        mso msoVar = new mso();
        msq msqVar = new msq();
        msa d2 = d(str);
        msc c2 = c(str);
        if (c2 == null || d2 == null) {
            com.huawei.hwlogsmodel.LogUtil.h("EzPlugin_EzPluginManager", "checkPluginNewVersionForWear ezPluginInfo is null");
            msoVar.b(20);
            if (pullListener != null) {
                pullListener.onPullingChange(msqVar, msoVar);
                return;
            }
            return;
        }
        String h = d2.h();
        String i = c2.i();
        String str2 = d2.b() + "_version";
        String b2 = SharedPreferenceManager.b(this.k, String.valueOf(1003), str2);
        com.huawei.hwlogsmodel.LogUtil.a("EzPlugin_EzPluginManager", "checkPluginNewVersionForWear getKey is ", str2, ",spVersion is ", b2, ",indexPluginVersion is:", h, ",descVersion is:", i);
        boolean e = msl.e(i, h);
        if (TextUtils.isEmpty(i) || !msl.d(str)) {
            e = true;
        }
        boolean e2 = msl.e(b2, h);
        Object[] objArr = new Object[1];
        objArr[0] = (e || e2) ? "checkPluginNewVersionForWear have new version" : "checkPluginNewVersionForWear not have new version";
        com.huawei.hwlogsmodel.LogUtil.a("EzPlugin_EzPluginManager", objArr);
        boolean exists = new File(msj.a().d(str) + File.separator + str + File.separator + "img").exists();
        boolean exists2 = new File(msj.a().d(str) + File.separator + str + File.separator + "lang").exists();
        com.huawei.hwlogsmodel.LogUtil.a("EzPlugin_EzPluginManager", "imgFile is exist:", Boolean.valueOf(exists), "langFile is exist:", Boolean.valueOf(exists2));
        if (e || e2 || (!exists && exists2)) {
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

    public void c(String str, PullListener pullListener) {
        a(str, (CommonUtil.ag(this.k) || i()) ? "com.huawei.health_HwWear_deviceConfig" : "com.huawei.health_HwWear_deviceConfigBeta", pullListener);
    }

    public void a(String str, String str2, PullListener pullListener) {
        b(d(str), str2, pullListener, new msq(), new mso());
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
    public Bitmap cop_(msc mscVar, String str) {
        InputStream inputStream;
        ?? r5;
        Object[] objArr = new Object[2];
        objArr[0] = "loadImageForWear enter loadImageForWear";
        objArr[1] = mscVar == null ? ",loadImageForWear ezPluginInfo is null" : "";
        com.huawei.hwlogsmodel.LogUtil.a("EzPlugin_EzPluginManager", objArr);
        InputStream inputStream2 = null;
        inputStream2 = null;
        inputStream2 = null;
        Bitmap bitmap = null;
        inputStream2 = null;
        if (mscVar == null) {
            return null;
        }
        try {
            try {
                String str2 = msj.a().d(mscVar.a()) + File.separator + mscVar.a() + File.separator + "img" + File.separator + str + ".png";
                if (new File(str2).exists()) {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                    options.inInputShareable = true;
                    r5 = BitmapFactory.decodeFile(str2, options);
                    try {
                        com.huawei.hwlogsmodel.LogUtil.a("EzPlugin_EzPluginManager", "enter loadImageForWear have bitmap imageKey:", str, " fetchPluginUuid:", mscVar.a());
                        r5 = r5;
                    } catch (Resources.NotFoundException unused) {
                        com.huawei.hwlogsmodel.LogUtil.b("EzPlugin_EzPluginManager", "loadImageForWear loadImage NotFoundException");
                        IoUtils.e(inputStream2);
                        return r5;
                    }
                } else {
                    BitmapFactory.Options options2 = new BitmapFactory.Options();
                    options2.inPreferredConfig = Bitmap.Config.RGB_565;
                    options2.inPurgeable = true;
                    options2.inInputShareable = true;
                    inputStream = BaseApplication.getContext().getResources().openRawResource(n("hw_device_default"));
                    try {
                        bitmap = BitmapFactory.decodeStream(inputStream, null, options2);
                        com.huawei.hwlogsmodel.LogUtil.a("EzPlugin_EzPluginManager", "enter loadImageForWear have no bitmap :", str, " fetchPluginUuid:", mscVar.a());
                        r5 = bitmap;
                        inputStream2 = inputStream;
                    } catch (Resources.NotFoundException unused2) {
                        r5 = bitmap;
                        inputStream2 = inputStream;
                        com.huawei.hwlogsmodel.LogUtil.b("EzPlugin_EzPluginManager", "loadImageForWear loadImage NotFoundException");
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
    public Bitmap coo_(String str) {
        InputStream inputStream;
        ?? r7;
        Object[] objArr = new Object[2];
        objArr[0] = "enter loadImageForImagePath";
        objArr[1] = TextUtils.isEmpty(str) ? ",loadImageForImagePath imagePath is null" : "";
        com.huawei.hwlogsmodel.LogUtil.a("EzPlugin_EzPluginManager", objArr);
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
                        com.huawei.hwlogsmodel.LogUtil.a("EzPlugin_EzPluginManager", "enter loadImageForImagePath have bitmap imageKey");
                        r7 = r7;
                    } catch (Resources.NotFoundException | OutOfMemoryError unused) {
                        com.huawei.hwlogsmodel.LogUtil.b("EzPlugin_EzPluginManager", "loadImageForImagePath loadImage NotFoundException");
                        IoUtils.e(inputStream2);
                        return r7;
                    }
                } else {
                    BitmapFactory.Options options2 = new BitmapFactory.Options();
                    options2.inPreferredConfig = Bitmap.Config.RGB_565;
                    options2.inPurgeable = true;
                    options2.inInputShareable = true;
                    inputStream = BaseApplication.getContext().getResources().openRawResource(n("hw_device_default"));
                    try {
                        bitmap = BitmapFactory.decodeStream(inputStream, null, options2);
                        com.huawei.hwlogsmodel.LogUtil.a("EzPlugin_EzPluginManager", "enter loadImageForImagePath have no bitmap");
                        r7 = bitmap;
                        inputStream2 = inputStream;
                    } catch (Resources.NotFoundException | OutOfMemoryError unused2) {
                        r7 = bitmap;
                        inputStream2 = inputStream;
                        com.huawei.hwlogsmodel.LogUtil.b("EzPlugin_EzPluginManager", "loadImageForImagePath loadImage NotFoundException");
                        IoUtils.e(inputStream2);
                        return r7;
                    } catch (Throwable th) {
                        th = th;
                        IoUtils.e(inputStream);
                        throw th;
                    }
                }
            } catch (Resources.NotFoundException | OutOfMemoryError unused3) {
                r7 = inputStream2;
            }
            IoUtils.e(inputStream2);
            return r7;
        } catch (Throwable th2) {
            th = th2;
            inputStream = inputStream2;
        }
    }

    private int n(String str) {
        return BaseApplication.getContext().getResources().getIdentifier(str, "drawable", BaseApplication.getContext().getPackageName());
    }

    public void e() {
        String c2 = msl.c();
        boolean exists = new File(c2).exists();
        com.huawei.hwlogsmodel.LogUtil.a("EzPlugin_EzPluginManager", "updateIndexCacheForWear isExistThisIndex is = ", Boolean.valueOf(exists));
        if (exists) {
            String e = msl.e(this.k, c2);
            com.huawei.hwlogsmodel.LogUtil.a("EzPlugin_EzPluginManager", "updateIndexCacheForWear indexJson = ", e);
            p(e);
            com.huawei.hwlogsmodel.LogUtil.a("EzPlugin_EzPluginManager", "updateIndexCacheForWear end");
        }
    }

    public void i(String str) {
        String a2 = msl.a(str);
        if (new File(a2).exists()) {
            String e = msl.e(this.k, a2);
            com.huawei.hwlogsmodel.LogUtil.c("EzPlugin_EzPluginManager", "index Data:", e);
            if (TextUtils.isEmpty(e)) {
                com.huawei.hwlogsmodel.LogUtil.h("EzPlugin_EzPluginManager", "indexJson is null");
                y(str);
            } else {
                a(str, msb.c(e));
            }
        }
    }

    public void a(String str) {
        File file = new File((mrv.d + str + File.separator) + "description.json");
        if (file.exists()) {
            com.huawei.hwlogsmodel.LogUtil.a("EzPlugin_EzPluginManager", "isDeleteDescriptionForwear is = ", Boolean.valueOf(file.delete()));
        }
    }

    public static List<String> c(msc mscVar, String str) {
        JSONObject a2;
        synchronized (EzPluginManager.class) {
            com.huawei.hwlogsmodel.LogUtil.c("EzPlugin_EzPluginManager", " enter loadStringForWear.");
            File a3 = a(mscVar);
            ArrayList arrayList = new ArrayList(10);
            if (a3 == null) {
                com.huawei.hwlogsmodel.LogUtil.h("EzPlugin_EzPluginManager", "loadStringForWear jsonFile == null.");
                return arrayList;
            }
            com.huawei.hwlogsmodel.LogUtil.c("EzPlugin_EzPluginManager", "loadStringForWear jsonFile.name is ", a3.getName());
            try {
                a2 = a(a3);
            } catch (JSONException unused) {
                com.huawei.hwlogsmodel.LogUtil.b("EzPlugin_EzPluginManager", "loadStringForWear JSONException");
            }
            if (a2 == null) {
                return arrayList;
            }
            if (!a2.has(str)) {
                com.huawei.hwlogsmodel.LogUtil.h("EzPlugin_EzPluginManager", "loadStringForWear jsonObject not has key");
                return arrayList;
            }
            Object obj = a2.get(str);
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

    /* JADX WARN: Removed duplicated region for block: B:45:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00ba  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static org.json.JSONObject a(java.io.File r9) {
        /*
            java.lang.String r0 = "EzPlugin_EzPluginManager"
            r1 = 0
            java.lang.String r2 = r9.getCanonicalPath()     // Catch: java.lang.Throwable -> L85 org.json.JSONException -> L88 java.io.IOException -> L8a java.io.UnsupportedEncodingException -> Lc5 java.io.FileNotFoundException -> Ld6
            java.lang.String r2 = health.compact.a.CommonUtil.c(r2)     // Catch: java.lang.Throwable -> L85 org.json.JSONException -> L88 java.io.IOException -> L8a java.io.UnsupportedEncodingException -> Lc5 java.io.FileNotFoundException -> Ld6
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Throwable -> L85 org.json.JSONException -> L88 java.io.IOException -> L8a java.io.UnsupportedEncodingException -> Lc5 java.io.FileNotFoundException -> Ld6
            r4 = 0
            if (r3 == 0) goto L2d
            r9 = 1
            java.lang.Object[] r9 = new java.lang.Object[r9]     // Catch: java.lang.Throwable -> L85 org.json.JSONException -> L88 java.io.IOException -> L8a java.io.UnsupportedEncodingException -> Lc5 java.io.FileNotFoundException -> Ld6
            java.lang.String r2 = "getJsonObjectFromFile safePath is empty"
            r9[r4] = r2     // Catch: java.lang.Throwable -> L85 org.json.JSONException -> L88 java.io.IOException -> L8a java.io.UnsupportedEncodingException -> Lc5 java.io.FileNotFoundException -> Ld6
            com.huawei.hwlogsmodel.LogUtil.h(r0, r9)     // Catch: java.lang.Throwable -> L85 org.json.JSONException -> L88 java.io.IOException -> L8a java.io.UnsupportedEncodingException -> Lc5 java.io.FileNotFoundException -> Ld6
            boolean r9 = android.text.TextUtils.isEmpty(r1)
            if (r9 != 0) goto L29
            java.lang.Object[] r9 = new java.lang.Object[]{r1}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r9)
        L29:
            health.compact.a.IoUtils.e(r1)
            return r1
        L2d:
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L85 org.json.JSONException -> L88 java.io.IOException -> L8a java.io.UnsupportedEncodingException -> Lc5 java.io.FileNotFoundException -> Ld6
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L85 org.json.JSONException -> L88 java.io.IOException -> L8a java.io.UnsupportedEncodingException -> Lc5 java.io.FileNotFoundException -> Ld6
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: org.json.JSONException -> L81 java.io.IOException -> L83 java.lang.Throwable -> Lb3 java.io.UnsupportedEncodingException -> Lc6 java.io.FileNotFoundException -> Ld7
            r5 = 16
            r2.<init>(r5)     // Catch: org.json.JSONException -> L81 java.io.IOException -> L83 java.lang.Throwable -> Lb3 java.io.UnsupportedEncodingException -> Lc6 java.io.FileNotFoundException -> Ld7
            long r5 = r9.length()     // Catch: org.json.JSONException -> L81 java.io.IOException -> L83 java.lang.Throwable -> Lb3 java.io.UnsupportedEncodingException -> Lc6 java.io.FileNotFoundException -> Ld7
            r7 = 0
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 <= 0) goto L72
            int r9 = (int) r5     // Catch: org.json.JSONException -> L81 java.io.IOException -> L83 java.lang.Throwable -> Lb3 java.io.UnsupportedEncodingException -> Lc6 java.io.FileNotFoundException -> Ld7
            byte[] r9 = new byte[r9]     // Catch: org.json.JSONException -> L81 java.io.IOException -> L83 java.lang.Throwable -> Lb3 java.io.UnsupportedEncodingException -> Lc6 java.io.FileNotFoundException -> Ld7
        L46:
            int r5 = r3.read(r9)     // Catch: org.json.JSONException -> L81 java.io.IOException -> L83 java.lang.Throwable -> Lb3 java.io.UnsupportedEncodingException -> Lc6 java.io.FileNotFoundException -> Ld7
            r6 = -1
            if (r5 == r6) goto L58
            java.lang.String r6 = new java.lang.String     // Catch: org.json.JSONException -> L81 java.io.IOException -> L83 java.lang.Throwable -> Lb3 java.io.UnsupportedEncodingException -> Lc6 java.io.FileNotFoundException -> Ld7
            java.nio.charset.Charset r7 = java.nio.charset.StandardCharsets.UTF_8     // Catch: org.json.JSONException -> L81 java.io.IOException -> L83 java.lang.Throwable -> Lb3 java.io.UnsupportedEncodingException -> Lc6 java.io.FileNotFoundException -> Ld7
            r6.<init>(r9, r4, r5, r7)     // Catch: org.json.JSONException -> L81 java.io.IOException -> L83 java.lang.Throwable -> Lb3 java.io.UnsupportedEncodingException -> Lc6 java.io.FileNotFoundException -> Ld7
            r2.append(r6)     // Catch: org.json.JSONException -> L81 java.io.IOException -> L83 java.lang.Throwable -> Lb3 java.io.UnsupportedEncodingException -> Lc6 java.io.FileNotFoundException -> Ld7
            goto L46
        L58:
            org.json.JSONObject r9 = new org.json.JSONObject     // Catch: org.json.JSONException -> L81 java.io.IOException -> L83 java.lang.Throwable -> Lb3 java.io.UnsupportedEncodingException -> Lc6 java.io.FileNotFoundException -> Ld7
            java.lang.String r2 = r2.toString()     // Catch: org.json.JSONException -> L81 java.io.IOException -> L83 java.lang.Throwable -> Lb3 java.io.UnsupportedEncodingException -> Lc6 java.io.FileNotFoundException -> Ld7
            r9.<init>(r2)     // Catch: org.json.JSONException -> L81 java.io.IOException -> L83 java.lang.Throwable -> Lb3 java.io.UnsupportedEncodingException -> Lc6 java.io.FileNotFoundException -> Ld7
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto L6e
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r1)
        L6e:
            health.compact.a.IoUtils.e(r3)
            return r9
        L72:
            boolean r9 = android.text.TextUtils.isEmpty(r1)
            if (r9 != 0) goto Le6
            java.lang.Object[] r9 = new java.lang.Object[]{r1}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r9)
            goto Le6
        L81:
            r9 = move-exception
            goto L8c
        L83:
            r9 = move-exception
            goto L8c
        L85:
            r9 = move-exception
            r3 = r1
            goto Lb4
        L88:
            r9 = move-exception
            goto L8b
        L8a:
            r9 = move-exception
        L8b:
            r3 = r1
        L8c:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb3
            r2.<init>()     // Catch: java.lang.Throwable -> Lb3
            java.lang.String r4 = "getJsonObjectFromFile "
            r2.append(r4)     // Catch: java.lang.Throwable -> Lb3
            java.lang.Class r9 = r9.getClass()     // Catch: java.lang.Throwable -> Lb3
            java.lang.String r9 = r9.getSimpleName()     // Catch: java.lang.Throwable -> Lb3
            r2.append(r9)     // Catch: java.lang.Throwable -> Lb3
            java.lang.String r9 = r2.toString()     // Catch: java.lang.Throwable -> Lb3
            boolean r2 = android.text.TextUtils.isEmpty(r9)
            if (r2 != 0) goto Le6
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r9)
            goto Le6
        Lb3:
            r9 = move-exception
        Lb4:
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto Lc1
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r1)
        Lc1:
            health.compact.a.IoUtils.e(r3)
            throw r9
        Lc5:
            r3 = r1
        Lc6:
            java.lang.String r9 = "getJsonObjectFromFile UnsupportedEncodingException"
            boolean r2 = android.text.TextUtils.isEmpty(r9)
            if (r2 != 0) goto Le6
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r9)
            goto Le6
        Ld6:
            r3 = r1
        Ld7:
            java.lang.String r9 = "getJsonObjectFromFile FileNotFoundException"
            boolean r2 = android.text.TextUtils.isEmpty(r9)
            if (r2 != 0) goto Le6
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r9)
        Le6:
            health.compact.a.IoUtils.e(r3)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: health.compact.a.EzPluginManager.a(java.io.File):org.json.JSONObject");
    }

    private static File a(msc mscVar) {
        Locale locale = Resources.getSystem().getConfiguration().locale;
        return b(mscVar, locale.getLanguage(), locale.getCountry(), locale.toLanguageTag());
    }

    private static File b(msc mscVar, String str, String str2, String str3) {
        String str4;
        com.huawei.hwlogsmodel.LogUtil.c("EzPlugin_EzPluginManager", " enter getTargetJsonFile.");
        if (TextUtils.isEmpty(str)) {
            com.huawei.hwlogsmodel.LogUtil.h("EzPlugin_EzPluginManager", " getTargetJsonFile TextUtils.isEmpty(language).");
            return null;
        }
        if (TextUtils.isEmpty(str2)) {
            str4 = str + ".json";
        } else {
            str4 = str + "-r" + str2 + ".json";
        }
        String str5 = msj.a().d(mscVar.a()) + File.separator + mscVar.a() + File.separator + "lang";
        File file = new File(str5);
        if (file.exists() && file.isDirectory()) {
            return a(file.listFiles(), str3, str4, str);
        }
        com.huawei.hwlogsmodel.LogUtil.h("EzPlugin_EzPluginManager", "getTargetJsonFile wrong dir path, not exists or should be dir but is file : ", str5);
        com.huawei.hwlogsmodel.LogUtil.h("EzPlugin_EzPluginManager", "getTargetJsonFile can not find target file, return null.");
        return null;
    }

    private static File a(File[] fileArr, String str, String str2, String str3) {
        String j;
        if (fileArr == null || fileArr.length == 0) {
            com.huawei.hwlogsmodel.LogUtil.h("EzPlugin_EzPluginManager", "getFileByName wrong dir.no file. can not find target file, return null.");
            return null;
        }
        if (str != null && (j = j(str)) != null) {
            String str4 = f13115a.get(j) + ".json";
            com.huawei.hwlogsmodel.LogUtil.a("EzPlugin_EzPluginManager", "getFileByName fileNameTag : ", str4);
            File e = e(fileArr, str4);
            if (e != null && e.length() > 0) {
                com.huawei.hwlogsmodel.LogUtil.a("EzPlugin_EzPluginManager", "getFileByName file is ok");
                return e;
            }
            File e2 = e(fileArr, "en.json");
            if (e2 != null && e2.length() > 0) {
                com.huawei.hwlogsmodel.LogUtil.a("EzPlugin_EzPluginManager", "getFileByName en file is ok");
                return e2;
            }
        }
        for (File file : fileArr) {
            if (str2.equals(file.getName())) {
                return file;
            }
        }
        com.huawei.hwlogsmodel.LogUtil.c("EzPlugin_EzPluginManager", "getFileByName can not find file : ", str2);
        if (str2.contains("-r")) {
            str2 = b(str3) + ".json";
            for (File file2 : fileArr) {
                if (str2.equals(file2.getName())) {
                    return file2;
                }
            }
        }
        com.huawei.hwlogsmodel.LogUtil.c("EzPlugin_EzPluginManager", "getFileByName can not find file : ", str2);
        for (File file3 : fileArr) {
            if ("en.json".equals(file3.getName())) {
                return file3;
            }
        }
        com.huawei.hwlogsmodel.LogUtil.c("EzPlugin_EzPluginManager", "getFileByName can not find file : ", "en.json");
        com.huawei.hwlogsmodel.LogUtil.h("EzPlugin_EzPluginManager", "getFileByName can not find target file, return null.");
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

    public static String b(String str) {
        char c2;
        Locale locale = Resources.getSystem().getConfiguration().locale;
        String script = locale.getScript();
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == 3149) {
            if (str.equals("bo")) {
                c2 = 0;
            }
            c2 = 65535;
        } else if (hashCode == 3241) {
            if (str.equals("en")) {
                c2 = 1;
            }
            c2 = 65535;
        } else if (hashCode != 3500) {
            if (hashCode == 3886 && str.equals(MLAsrConstants.LAN_ZH)) {
                c2 = 3;
            }
            c2 = 65535;
        } else {
            if (str.equals("my")) {
                c2 = 2;
            }
            c2 = 65535;
        }
        return c2 != 0 ? c2 != 1 ? c2 != 2 ? c2 != 3 ? str : locale.toString().endsWith("#Hans") ? "zh-rCN" : "zh-rTW" : "Qaag".equals(script) ? "my-rZG" : "my" : "Qaag".equals(script) ? "en-rGB" : str : "bo-rCN";
    }

    private static String j(String str) {
        String str2;
        String[] strArr = b;
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
        com.huawei.hwlogsmodel.LogUtil.c("EzPlugin_EzPluginManager", "changeTag is ", str, "after changeTag is ", str2);
        return str2;
    }

    public static List<String> a(String str, msc mscVar) {
        ArrayList arrayList = new ArrayList(16);
        List<String> c2 = c(mscVar, str);
        if (c2.size() == 0) {
            return arrayList;
        }
        for (int i = 0; i < c2.size(); i++) {
            List<String> c3 = c(mscVar, str + "Other" + i);
            String str2 = c2.get(i);
            if (c3.size() == 0) {
                arrayList.add(str2);
            } else {
                List<Object> b2 = b(c3);
                int i2 = 0;
                while (i2 < b2.size()) {
                    i2++;
                    str2 = str2.replace("%" + i2 + "$d", "%" + i2 + "$s");
                }
                arrayList.add(String.format(Locale.ROOT, str2, b2.toArray()));
            }
        }
        return arrayList;
    }

    private static List<Object> b(List<String> list) {
        ArrayList arrayList = new ArrayList(16);
        for (String str : list) {
            if (r(str)) {
                try {
                    String e = UnitUtil.e(Long.parseLong(str), 1, 0);
                    com.huawei.hwlogsmodel.LogUtil.c("EzPlugin_EzPluginManager", "isNumber formatValue:", e);
                    arrayList.add(e);
                } catch (NumberFormatException e2) {
                    com.huawei.hwlogsmodel.LogUtil.b("EzPlugin_EzPluginManager", e2.getMessage());
                }
            } else {
                try {
                    String[] split = str.split("\\.");
                    if (split.length > 1) {
                        str = UnitUtil.e(Double.parseDouble(str), 1, split[1].length());
                    }
                } catch (NumberFormatException unused) {
                }
                com.huawei.hwlogsmodel.LogUtil.c("EzPlugin_EzPluginManager", "string formatValue:", str);
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    private static boolean r(String str) {
        return Pattern.compile("[0-9]+").matcher(str).matches();
    }

    public String b(String str, boolean z) {
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), "developeroptions", "developerswitch");
        String b3 = SharedPreferenceManager.b(BaseApplication.getContext(), "APP_TEST_TO_BETA", "app_test_change_beta_url");
        com.huawei.hwlogsmodel.LogUtil.a("EzPlugin_EzPluginManager", "developer:", b2);
        if (CommonUtil.cc() && "1".equals(b2) && !TextUtils.isEmpty(b3)) {
            return "https://" + b3 + "/servicesupport/updateserver/data/";
        }
        DownloadPluginUrl downloadPluginUrl = this.n;
        if (downloadPluginUrl != null && downloadPluginUrl.isNetworkConnected()) {
            return this.n.getDownloadPluginUrl(str, z);
        }
        com.huawei.hwlogsmodel.LogUtil.h("EzPlugin_EzPluginManager", "updateIndex download is null");
        return "";
    }

    private String o(String str) {
        String q = q(str);
        return TextUtils.isEmpty(q) ? "plugin_index" : msr.d.get(q);
    }

    private String q(String str) {
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
        com.huawei.hwlogsmodel.LogUtil.h("EzPlugin_EzPluginManager", "getThirdDeviceType deviceType is ", str2);
        return str2;
    }

    private static boolean i() {
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), "developeroptions", "developerswitch");
        String b3 = SharedPreferenceManager.b(BaseApplication.getContext(), "APP_TEST_SITE", "app_test_site_type");
        com.huawei.hwlogsmodel.LogUtil.a("EzPlugin_EzPluginManager", "deviceSite:", b3);
        return CommonUtil.as() && "1".equals(b2) && "release".equals(b3);
    }
}
