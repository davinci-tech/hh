package defpackage;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.download.DownloadPluginUrl;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.healthcloudconfig.listener.DownloadCallback;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginmgr.EzPluginPullerCallback;
import com.huawei.pluginmgr.filedownload.PullHealthBiListener;
import com.huawei.pluginmgr.filedownload.PullListener;
import com.huawei.pluginmgr.hwwear.bean.DeviceDownloadSourceInfo;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class cxl extends HwBaseManager {

    /* renamed from: a, reason: collision with root package name */
    private static List<msc> f11526a = new ArrayList(16);
    private static volatile cxl c;
    private msi b;
    private Context d;
    private Map<String, msi> e;

    private cxl(Context context) {
        super(context);
        this.b = new msi();
        this.e = new HashMap();
        this.d = context;
    }

    public static cxl b() {
        if (c == null) {
            synchronized (msl.b) {
                if (c == null) {
                    c = new cxl(BaseApplication.getContext());
                }
            }
        }
        return c;
    }

    public void d(DeviceDownloadSourceInfo deviceDownloadSourceInfo, DownloadCallback<dqi> downloadCallback, PullHealthBiListener pullHealthBiListener) {
        LogUtil.a("HealthCloudDownloadManager", "updateThirdIndex");
        if (deviceDownloadSourceInfo == null || downloadCallback == null) {
            LogUtil.a("HealthCloudDownloadManager", "updateThirdIndex deviceDownloadSourceInfo or callback null");
        } else {
            j(deviceDownloadSourceInfo.getIndexName());
            c(deviceDownloadSourceInfo, cxs.d(deviceDownloadSourceInfo.getIndexName()), downloadCallback, pullHealthBiListener);
        }
    }

    private void c(final DeviceDownloadSourceInfo deviceDownloadSourceInfo, final String str, final DownloadCallback<dqi> downloadCallback, final PullHealthBiListener pullHealthBiListener) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: cxp
                @Override // java.lang.Runnable
                public final void run() {
                    cxl.this.a(deviceDownloadSourceInfo, str, downloadCallback, pullHealthBiListener);
                }
            });
        } else {
            LogUtil.a("HealthCloudDownloadManager", "startUpdateThirdIndex");
            cxq.c().c(deviceDownloadSourceInfo, str, downloadCallback, pullHealthBiListener);
        }
    }

    /* synthetic */ void a(DeviceDownloadSourceInfo deviceDownloadSourceInfo, String str, DownloadCallback downloadCallback, PullHealthBiListener pullHealthBiListener) {
        c(deviceDownloadSourceInfo, str, (DownloadCallback<dqi>) downloadCallback, pullHealthBiListener);
    }

    public msa b(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            LogUtil.h("HealthCloudDownloadManager", "getPluginIndexInfo uuid is empty");
            return null;
        }
        List<msa> c2 = c(str);
        if (!koq.b(c2)) {
            LogUtil.a("HealthCloudDownloadManager", "getPluginIndexInfo ezPluginInfos size is :", Integer.valueOf(c2.size()));
            for (msa msaVar : c2) {
                if (str2.equals(msaVar.b())) {
                    return msaVar;
                }
            }
        }
        return null;
    }

    public List<msa> c(String str) {
        LogUtil.c("HealthCloudDownloadManager", "getAllIndexList");
        ArrayList arrayList = new ArrayList();
        List<msa> d = d(str);
        if (d != null) {
            arrayList.addAll(d);
        }
        List<msa> d2 = d();
        if (d2 != null) {
            arrayList.addAll(d2);
        }
        return arrayList;
    }

    public msc e(String str, String str2) {
        msc d = msl.d(this.b, f11526a, str2);
        if (d != null) {
            LogUtil.c("HealthCloudDownloadManager", "getPluginInfo get cachePluginInfo");
            return d;
        }
        boolean d2 = d(str, str2);
        LogUtil.a("HealthCloudDownloadManager", "enter to getPluginInfo isDeprecated is :", Boolean.valueOf(d2));
        msc b = !d2 ? msl.b(this.d, str2) : null;
        b(b, str2);
        return b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(msc mscVar, String str) {
        msl.a(f11526a, mscVar, str);
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 20010;
    }

    private msi e(String str) {
        return this.e.get(str);
    }

    private void a(String str, msi msiVar) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.e.put(str, msiVar);
    }

    public List<msa> d() {
        List<msa> i;
        LogUtil.c("HealthCloudDownloadManager", "getIndexList enter to GetPluginsIndexInfos");
        ArrayList arrayList = new ArrayList();
        List<msx> c2 = mst.a().c();
        for (int i2 = 0; i2 < c2.size(); i2++) {
            msx msxVar = c2.get(i2);
            if (msxVar != null && (i = msxVar.i()) != null && i.size() > 0) {
                arrayList.addAll(i);
            }
        }
        if (this.b.d() != null) {
            arrayList.addAll(this.b.d());
            return arrayList;
        }
        String c3 = msl.c();
        boolean exists = new File(c3).exists();
        LogUtil.a("HealthCloudDownloadManager", "getIndexList isExistThisIndex is =", Boolean.valueOf(exists));
        if (exists) {
            String e = msl.e(this.d, c3);
            LogUtil.c("HealthCloudDownloadManager", "getIndex indexJson = ", e);
            g(e);
            if (this.b.d() != null) {
                arrayList.addAll(this.b.d());
            }
        }
        return arrayList;
    }

    public List<msa> d(String str) {
        msi e = e(str);
        List<msa> d = e != null ? e.d() : null;
        if (d != null) {
            return d;
        }
        String d2 = cxs.d(str);
        if (!new File(d2).exists()) {
            return d;
        }
        String e2 = msl.e(this.d, d2);
        Object[] objArr = new Object[3];
        objArr[0] = "index Data:";
        objArr[1] = e2;
        objArr[2] = TextUtils.isEmpty(e2) ? ",indexJson is null" : "";
        LogUtil.a("HealthCloudDownloadManager", objArr);
        if (TextUtils.isEmpty(e2)) {
            return d;
        }
        msi c2 = msb.c(e2);
        a(str, c2);
        return c2.d();
    }

    private void g(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HealthCloudDownloadManager", "parseVersionIndexFile indexJson is null");
            return;
        }
        try {
            msi msiVar = this.b;
            if (msiVar == null) {
                str2 = "";
            } else {
                str2 = msiVar.b();
            }
            if (TextUtils.isEmpty(str2)) {
                this.b = msb.c(str);
                e();
            } else {
                if (str2.equals(new JSONObject(str).optString("version"))) {
                    return;
                }
                this.b = msb.c(str);
                e();
            }
        } catch (JSONException unused) {
            LogUtil.b("HealthCloudDownloadManager", "parseVersionIndexFile JSONException");
        }
    }

    public boolean a(String str, String str2) {
        return msl.e(str2, d(str, str2));
    }

    private boolean d(String str, String str2) {
        return msl.c(str2, b(str, str2));
    }

    public void e(final DeviceDownloadSourceInfo deviceDownloadSourceInfo, final String str, final PullListener pullListener, final PullHealthBiListener pullHealthBiListener) {
        msc e = e(deviceDownloadSourceInfo.getIndexName(), str);
        if (e != null && !TextUtils.isEmpty(str) && str.equals(e.a())) {
            mru mruVar = new mru(str, d(deviceDownloadSourceInfo), null, e);
            mruVar.b(deviceDownloadSourceInfo);
            cxq.c().a(str + "_new_v1.1", mruVar, pullListener, pullHealthBiListener);
            return;
        }
        b(deviceDownloadSourceInfo, str, new PullListener() { // from class: cxn
            @Override // com.huawei.pluginmgr.filedownload.PullListener
            public final void onPullingChange(msq msqVar, mso msoVar) {
                cxl.this.b(deviceDownloadSourceInfo, str, pullListener, pullHealthBiListener, msqVar, msoVar);
            }
        }, pullHealthBiListener);
    }

    /* synthetic */ void b(DeviceDownloadSourceInfo deviceDownloadSourceInfo, String str, PullListener pullListener, PullHealthBiListener pullHealthBiListener, msq msqVar, mso msoVar) {
        int i = msoVar.i();
        if (i == 1) {
            if (e(deviceDownloadSourceInfo.getIndexName(), str) != null) {
                e(deviceDownloadSourceInfo, str, pullListener, pullHealthBiListener);
                return;
            } else {
                LogUtil.h("HealthCloudDownloadManager", "updateThirdPartyPlugin inform error");
                return;
            }
        }
        if (i == 0) {
            LogUtil.a("HealthCloudDownloadManager", "updateThirdPartyPlugin description file is updating");
        } else if (pullListener != null) {
            pullListener.onPullingChange(msqVar, msoVar);
        }
    }

    private void c(DeviceDownloadSourceInfo deviceDownloadSourceInfo, msa msaVar, String str, PullListener pullListener, PullHealthBiListener pullHealthBiListener) {
        if (msaVar != null) {
            if (!d(deviceDownloadSourceInfo.getIndexName(), str)) {
                a(deviceDownloadSourceInfo, str, pullListener, pullHealthBiListener);
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
        LogUtil.h("HealthCloudDownloadManager", "downloadDescription inform error");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void a(final DeviceDownloadSourceInfo deviceDownloadSourceInfo, final String str, final PullListener pullListener, final PullHealthBiListener pullHealthBiListener) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: cxo
                @Override // java.lang.Runnable
                public final void run() {
                    cxl.this.a(deviceDownloadSourceInfo, str, pullListener, pullHealthBiListener);
                }
            });
            return;
        }
        String d = d(deviceDownloadSourceInfo);
        mru mruVar = new mru();
        mruVar.d(str);
        mruVar.e(d);
        mruVar.b(deviceDownloadSourceInfo);
        cxq.c().a(mruVar, pullListener, new EzPluginPullerCallback() { // from class: cxm
            @Override // com.huawei.pluginmgr.EzPluginPullerCallback
            public final void setEzPluginInfoCache(msc mscVar, String str2) {
                cxl.this.b(mscVar, str2);
            }
        }, pullHealthBiListener);
    }

    private void b(DeviceDownloadSourceInfo deviceDownloadSourceInfo, msa msaVar, PullListener pullListener, PullHealthBiListener pullHealthBiListener) {
        Object[] objArr = new Object[2];
        objArr[0] = "checkAppVersion enter check version";
        objArr[1] = msaVar == null ? ",checkAppVersion:ezPluginIndexInfo is null" : "";
        LogUtil.a("HealthCloudDownloadManager", objArr);
        if (msaVar == null) {
            return;
        }
        String b = msaVar.b();
        msk j = msaVar.j();
        if (j == null) {
            LogUtil.h("HealthCloudDownloadManager", "checkAppVersion no have ApplyRules");
            c(deviceDownloadSourceInfo, msaVar, b, pullListener, pullHealthBiListener);
            return;
        }
        if (j.a() == null) {
            LogUtil.h("HealthCloudDownloadManager", "checkAppVersion not have minAppVersion");
            c(deviceDownloadSourceInfo, msaVar, pullListener, pullHealthBiListener);
            return;
        }
        int m = CommonUtil.m(this.d, j.a());
        int d = CommonUtil.d(this.d);
        LogUtil.a("HealthCloudDownloadManager", "checkAppVersion minAppVersionCode is ", Integer.valueOf(m), " appCode is ", Integer.valueOf(d));
        if (d < m) {
            mso msoVar = new mso();
            msq msqVar = new msq();
            msoVar.b(-30);
            if (pullListener != null) {
                pullListener.onPullingChange(msqVar, msoVar);
                return;
            }
            return;
        }
        c(deviceDownloadSourceInfo, msaVar, pullListener, pullHealthBiListener);
    }

    private void c(DeviceDownloadSourceInfo deviceDownloadSourceInfo, msa msaVar, PullListener pullListener, PullHealthBiListener pullHealthBiListener) {
        msk j = msaVar.j();
        mso msoVar = new mso();
        msq msqVar = new msq();
        String b = msaVar.b();
        if (this.b != null) {
            if (msl.e(this.d, j.b(), this.b.b())) {
                c(deviceDownloadSourceInfo, msaVar, b, pullListener, pullHealthBiListener);
                return;
            }
            LogUtil.a("HealthCloudDownloadManager", "checkIndexVersion indexVersion less than this plugin minIndexVersion");
            msoVar.b(-20);
            if (pullListener != null) {
                pullListener.onPullingChange(msqVar, msoVar);
                return;
            }
            return;
        }
        LogUtil.h("HealthCloudDownloadManager", "checkIndexVersion mIndexFileStruct is null");
    }

    public void c(String str, String str2, PullListener pullListener) {
        if (pullListener == null) {
            LogUtil.a("HealthCloudDownloadManager", "checkPluginNewVersion callback is null");
            return;
        }
        LogUtil.a("HealthCloudDownloadManager", "enter checkPluginNewVersion");
        mso msoVar = new mso();
        msq msqVar = new msq();
        msa b = b(str, str2);
        msc e = e(str, str2);
        if (e == null || b == null) {
            LogUtil.h("HealthCloudDownloadManager", "checkPluginNewVersion ezPluginInfo is null");
            msoVar.b(20);
            pullListener.onPullingChange(msqVar, msoVar);
            return;
        }
        String h = b.h();
        String i = e.i();
        String str3 = b.b() + "_version";
        String b2 = SharedPreferenceManager.b(this.d, String.valueOf(1003), str3);
        LogUtil.a("HealthCloudDownloadManager", "checkPluginNewVersion getKey is ", str3, ",spVersion is ", b2, ",indexPluginVersion is:", h, ",descVersion is:", i);
        boolean e2 = msl.e(i, h);
        if (TextUtils.isEmpty(i) || !msl.d(str2)) {
            e2 = true;
        }
        boolean e3 = msl.e(b2, h);
        Object[] objArr = new Object[1];
        objArr[0] = (e2 || e3) ? "checkPluginNewVersion have new version" : "checkPluginNewVersion not have new version";
        LogUtil.a("HealthCloudDownloadManager", objArr);
        boolean exists = new File(c(str2, "img")).exists();
        boolean exists2 = new File(c(str2, "lang")).exists();
        LogUtil.a("HealthCloudDownloadManager", "imgFile is exist:", Boolean.valueOf(exists), " langFile is exist:", Boolean.valueOf(exists2));
        if (e2 || e3 || (!exists && exists2)) {
            msoVar.b(20);
            pullListener.onPullingChange(msqVar, msoVar);
        } else {
            msoVar.b(1);
            pullListener.onPullingChange(msqVar, msoVar);
        }
    }

    private String c(String str, String str2) {
        return cxs.b(str) + File.separator + str + File.separator + str2;
    }

    public void b(DeviceDownloadSourceInfo deviceDownloadSourceInfo, String str, PullListener pullListener, PullHealthBiListener pullHealthBiListener) {
        if (deviceDownloadSourceInfo == null || pullListener == null) {
            LogUtil.a("HealthCloudDownloadManager", "ezPluginFileInfo or callback is null");
        } else {
            b(deviceDownloadSourceInfo, b(deviceDownloadSourceInfo.getIndexName(), str), pullListener, pullHealthBiListener);
        }
    }

    public void b(String str) {
        String d = cxs.d(str);
        if (new File(d).exists()) {
            String e = msl.e(this.d, d);
            LogUtil.a("HealthCloudDownloadManager", "index Data:", e);
            if (TextUtils.isEmpty(e)) {
                LogUtil.h("HealthCloudDownloadManager", "indexJson is null");
            } else {
                a(str, msb.c(e));
            }
        }
    }

    public static void e() {
        msl.a(f11526a);
    }

    private String d(DeviceDownloadSourceInfo deviceDownloadSourceInfo) {
        String str;
        String configurationPoint = deviceDownloadSourceInfo.getConfigurationPoint();
        DownloadPluginUrl d = msn.c().d();
        if (d != null && deviceDownloadSourceInfo.getSite() == 1) {
            str = a(d);
            configurationPoint = a(configurationPoint);
        } else if (d != null) {
            str = d.getDownloadPluginUrl(null, true);
        } else {
            LogUtil.c("HealthCloudDownloadManager", "getDownLoadFileUrl is null");
            str = "";
        }
        return str + configurationPoint;
    }

    private String a(DownloadPluginUrl downloadPluginUrl) {
        String downloadPluginUrl2 = downloadPluginUrl.getDownloadPluginUrl("domainHealthCloudCommon", true);
        LogUtil.a("HealthCloudDownloadManager", "getDefaultHealthCloudUrl url null is ", downloadPluginUrl2);
        return downloadPluginUrl2 + "/commonAbility/configCenter/";
    }

    private String a(String str) {
        String e = msn.e();
        if (TextUtils.isEmpty(e)) {
            return str;
        }
        return str + "?Device-ID=" + e;
    }

    private void j(String str) {
        String sharedPreference = getSharedPreference("key_ezplugin_healthcloud_mgr");
        Gson gson = new Gson();
        HashSet hashSet = (HashSet) gson.fromJson(sharedPreference, new TypeToken<HashSet<String>>() { // from class: cxl.5
        }.getType());
        if (hashSet == null) {
            hashSet = new HashSet();
        }
        hashSet.add(str);
        setSharedPreference("key_ezplugin_healthcloud_mgr", gson.toJson(hashSet), null);
    }
}
