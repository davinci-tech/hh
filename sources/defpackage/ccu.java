package defpackage;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import com.huawei.haf.download.DownloadPluginUrl;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.configresource.downloaddevicemanager.DownloadPullerCallback;
import com.huawei.health.devicemgr.business.entity.downloadresouce.DeviceDownloadSourceInfo;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginmgr.filedownload.PullHealthBiListener;
import com.huawei.pluginmgr.filedownload.PullListener;
import health.compact.a.CommonUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class ccu extends HwBaseManager {

    /* renamed from: a, reason: collision with root package name */
    private static volatile ccu f620a;
    private static List<cvc> b = new ArrayList(16);
    private cdi c;
    private Map<String, cdi> d;
    private Context e;

    private ccu(Context context) {
        super(context);
        this.c = new cdi();
        this.d = new HashMap();
        this.e = BaseApplication.getContext();
    }

    public static ccu d() {
        if (f620a == null) {
            synchronized (cdr.f649a) {
                if (f620a == null) {
                    f620a = new ccu(BaseApplication.getContext());
                }
            }
        }
        return f620a;
    }

    private cvk d(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            LogUtil.h("DownloadHealthCloudManager", "getPluginIndexInfo uuid is empty");
            return null;
        }
        List<cvk> a2 = a(str);
        if (!koq.b(a2)) {
            LogUtil.c("DownloadHealthCloudManager", "getPluginIndexInfo indexInfos size is :", Integer.valueOf(a2.size()));
            for (cvk cvkVar : a2) {
                if (str2.equals(cvkVar.e())) {
                    return cvkVar;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public cvc a(String str, String str2) {
        cvc a2 = cdr.a(this.c, b, str2);
        if (a2 != null) {
            LogUtil.c("DownloadHealthCloudManager", "getPluginInfo get cachePluginInfo");
            return a2;
        }
        boolean e = e(str, str2);
        LogUtil.a("DownloadHealthCloudManager", "enter to getPluginInfo isDeprecated is :", Boolean.valueOf(e));
        cvc a3 = !e ? cdr.a(this.e, str2) : null;
        d(a3, str2);
        return a3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(cvc cvcVar, String str) {
        cdr.a(b, cvcVar, str);
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 20010;
    }

    private cdi b(String str) {
        return this.d.get(str);
    }

    private void e(String str, cdi cdiVar) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.d.put(str, cdiVar);
    }

    private List<cvk> e() {
        LogUtil.c("DownloadHealthCloudManager", "getIndexList enter to GetPluginsIndexInfos");
        ArrayList arrayList = new ArrayList();
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList(ccs.d().e());
        for (int i = 0; i < copyOnWriteArrayList.size(); i++) {
            List<cvk> q = ((cve) copyOnWriteArrayList.get(i)).q();
            if (q != null && q.size() > 0) {
                arrayList.addAll(((cve) copyOnWriteArrayList.get(i)).q());
            }
        }
        String d = cdr.d();
        boolean exists = new File(d).exists();
        LogUtil.a("DownloadHealthCloudManager", "getIndexList isExistThisIndex is =", Boolean.valueOf(exists));
        if (exists) {
            String c = cdr.c(this.e, d);
            LogUtil.c("DownloadHealthCloudManager", "getIndex indexJson = ", c);
            d(c);
        }
        if (this.c.a() != null) {
            arrayList.addAll(this.c.a());
        }
        return arrayList;
    }

    private List<cvk> c(String str) {
        cdi b2 = b(str);
        List<cvk> a2 = b2 != null ? b2.a() : null;
        if (a2 != null) {
            return a2;
        }
        String a3 = cdr.a(str);
        if (!new File(a3).exists()) {
            return a2;
        }
        String c = cdr.c(this.e, a3);
        Object[] objArr = new Object[3];
        objArr[0] = "index Data:";
        objArr[1] = c;
        objArr[2] = TextUtils.isEmpty(c) ? ",indexJson is null" : "";
        LogUtil.a("DownloadHealthCloudManager", objArr);
        if (TextUtils.isEmpty(c)) {
            return a2;
        }
        cdi c2 = cde.c(c);
        e(str, c2);
        return c2.a();
    }

    private List<cvk> a(String str) {
        LogUtil.c("DownloadHealthCloudManager", "getAllIndexList");
        ArrayList arrayList = new ArrayList();
        List<cvk> c = c(str);
        if (c != null) {
            arrayList.addAll(c);
        }
        List<cvk> e = e();
        if (e != null) {
            arrayList.addAll(e);
        }
        return arrayList;
    }

    private void d(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DownloadHealthCloudManager", "parseVersionIndexFile indexJson is null");
            return;
        }
        try {
            cdi cdiVar = this.c;
            if (cdiVar == null) {
                str2 = "";
            } else {
                str2 = cdiVar.e();
            }
            if (TextUtils.isEmpty(str2)) {
                this.c = cde.c(str);
                a();
            } else {
                if (str2.equals(new JSONObject(str).optString("version"))) {
                    return;
                }
                this.c = cde.c(str);
                a();
            }
        } catch (JSONException unused) {
            LogUtil.b("DownloadHealthCloudManager", "parseVersionIndexFile JSONException");
        }
    }

    private boolean e(String str, String str2) {
        return cdr.b(str2, d(str, str2));
    }

    public void b(final DeviceDownloadSourceInfo deviceDownloadSourceInfo, final String str, final PullListener pullListener, final PullHealthBiListener pullHealthBiListener) {
        cvc a2 = a(deviceDownloadSourceInfo.getIndexName(), str);
        if (a2 != null && !TextUtils.isEmpty(str) && str.equals(a2.e())) {
            cdj cdjVar = new cdj(str, c(deviceDownloadSourceInfo), null, a2);
            cdjVar.c(deviceDownloadSourceInfo);
            cdv.b().b(str + "_new_v1.1", cdjVar, pullListener, pullHealthBiListener);
            return;
        }
        d(deviceDownloadSourceInfo, str, new PullListener() { // from class: ccu.3
            @Override // com.huawei.pluginmgr.filedownload.PullListener
            public void onPullingChange(msq msqVar, mso msoVar) {
                int i = msoVar.i();
                if (i == 1) {
                    if (ccu.this.a(deviceDownloadSourceInfo.getIndexName(), str) != null) {
                        ccu.this.b(deviceDownloadSourceInfo, str, pullListener, pullHealthBiListener);
                        return;
                    } else {
                        LogUtil.h("DownloadHealthCloudManager", "updateThirdPartyPlugin inform error");
                        return;
                    }
                }
                if (i == 0) {
                    LogUtil.a("DownloadHealthCloudManager", "updateThirdPartyPlugin description file is updating");
                    return;
                }
                PullListener pullListener2 = pullListener;
                if (pullListener2 != null) {
                    pullListener2.onPullingChange(msqVar, msoVar);
                }
            }
        }, pullHealthBiListener);
    }

    private void a(DeviceDownloadSourceInfo deviceDownloadSourceInfo, cvk cvkVar, String str, PullListener pullListener, PullHealthBiListener pullHealthBiListener) {
        if (cvkVar != null) {
            if (!e(deviceDownloadSourceInfo.getIndexName(), str)) {
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
        LogUtil.h("DownloadHealthCloudManager", "downloadDescription inform error");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final DeviceDownloadSourceInfo deviceDownloadSourceInfo, final String str, final PullListener pullListener, final PullHealthBiListener pullHealthBiListener) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: ccu.4
                @Override // java.lang.Runnable
                public void run() {
                    ccu.this.a(deviceDownloadSourceInfo, str, pullListener, pullHealthBiListener);
                }
            });
            return;
        }
        String c = c(deviceDownloadSourceInfo);
        cdj cdjVar = new cdj();
        cdjVar.a(str);
        cdjVar.c(c);
        cdjVar.c(deviceDownloadSourceInfo);
        cdv.b().c(cdjVar, pullListener, new DownloadPullerCallback() { // from class: ccu.2
            @Override // com.huawei.health.configresource.downloaddevicemanager.DownloadPullerCallback
            public void setDescriptionInfoCache(cvc cvcVar, String str2) {
                ccu.this.d(cvcVar, str2);
            }
        }, pullHealthBiListener);
    }

    private void e(DeviceDownloadSourceInfo deviceDownloadSourceInfo, cvk cvkVar, PullListener pullListener, PullHealthBiListener pullHealthBiListener) {
        Object[] objArr = new Object[2];
        objArr[0] = "checkAppVersion enter check version";
        objArr[1] = cvkVar == null ? ",checkAppVersion:indexInfo is null" : "";
        LogUtil.a("DownloadHealthCloudManager", objArr);
        if (cvkVar == null) {
            return;
        }
        String e = cvkVar.e();
        cvf d = cvkVar.d();
        if (d == null) {
            LogUtil.h("DownloadHealthCloudManager", "checkAppVersion no have ApplyRules");
            a(deviceDownloadSourceInfo, cvkVar, e, pullListener, pullHealthBiListener);
            return;
        }
        if (d.b() == null) {
            LogUtil.h("DownloadHealthCloudManager", "checkAppVersion not have minAppVersion");
            b(deviceDownloadSourceInfo, cvkVar, pullListener, pullHealthBiListener);
            return;
        }
        int m = CommonUtil.m(this.e, d.b());
        int d2 = CommonUtil.d(this.e);
        LogUtil.a("DownloadHealthCloudManager", "checkAppVersion minAppVersionCode is ", Integer.valueOf(m), " appCode is ", Integer.valueOf(d2));
        if (d2 < m) {
            mso msoVar = new mso();
            msq msqVar = new msq();
            msoVar.b(-30);
            if (pullListener != null) {
                pullListener.onPullingChange(msqVar, msoVar);
                return;
            }
            return;
        }
        b(deviceDownloadSourceInfo, cvkVar, pullListener, pullHealthBiListener);
    }

    private void b(DeviceDownloadSourceInfo deviceDownloadSourceInfo, cvk cvkVar, PullListener pullListener, PullHealthBiListener pullHealthBiListener) {
        cvf d = cvkVar.d();
        mso msoVar = new mso();
        msq msqVar = new msq();
        String e = cvkVar.e();
        if (this.c != null) {
            if (cdr.c(this.e, d.a(), this.c.e())) {
                a(deviceDownloadSourceInfo, cvkVar, e, pullListener, pullHealthBiListener);
                return;
            }
            LogUtil.a("DownloadHealthCloudManager", "checkIndexVersion indexVersion less than this plugin minIndexVersion");
            msoVar.b(-20);
            if (pullListener != null) {
                pullListener.onPullingChange(msqVar, msoVar);
                return;
            }
            return;
        }
        LogUtil.h("DownloadHealthCloudManager", "checkIndexVersion mIndexFileStruct is null");
    }

    public void d(DeviceDownloadSourceInfo deviceDownloadSourceInfo, String str, PullListener pullListener, PullHealthBiListener pullHealthBiListener) {
        if (deviceDownloadSourceInfo == null || pullListener == null) {
            LogUtil.a("DownloadHealthCloudManager", "ezPluginFileInfo or callback is null");
        } else {
            e(deviceDownloadSourceInfo, d(deviceDownloadSourceInfo.getIndexName(), str), pullListener, pullHealthBiListener);
        }
    }

    private static void a() {
        cdr.b(b);
    }

    private String c(DeviceDownloadSourceInfo deviceDownloadSourceInfo) {
        String str;
        String configurationPoint = deviceDownloadSourceInfo.getConfigurationPoint();
        DownloadPluginUrl d = msn.c().d();
        if (d != null && deviceDownloadSourceInfo.getSite() == 1) {
            str = b(d);
            configurationPoint = e(configurationPoint);
        } else if (d != null) {
            str = d.getDownloadPluginUrl(null, true);
        } else {
            LogUtil.c("DownloadHealthCloudManager", "getDownLoadFileUrl is null");
            str = "";
        }
        return str + configurationPoint;
    }

    private String b(DownloadPluginUrl downloadPluginUrl) {
        String downloadPluginUrl2 = downloadPluginUrl.getDownloadPluginUrl("domainHealthCloudCommon", true);
        LogUtil.a("DownloadHealthCloudManager", "getDefaultHealthCloudUrl url null is ", downloadPluginUrl2);
        return downloadPluginUrl2 + "/commonAbility/configCenter/";
    }

    private String e(String str) {
        String e = msn.e();
        if (TextUtils.isEmpty(e)) {
            return str;
        }
        return str + "?Device-ID=" + e;
    }
}
