package defpackage;

import android.content.Context;
import com.huawei.health.healthcloudconfig.listener.DownloadCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginmgr.EzPluginPullerCallback;
import com.huawei.pluginmgr.filedownload.PullHealthBiListener;
import com.huawei.pluginmgr.filedownload.PullListener;
import com.huawei.pluginmgr.hwwear.bean.DeviceDownloadSourceInfo;
import defpackage.dqk;
import health.compact.a.CommonUtil;
import health.compact.a.EzPluginManager;
import health.compact.a.IoUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class cxq {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f11531a = new Object();
    private static cxq b;
    private Context c = BaseApplication.getContext();

    private cxq() {
    }

    public static cxq c() {
        cxq cxqVar;
        synchronized (f11531a) {
            if (b == null) {
                b = new cxq();
            }
            cxqVar = b;
        }
        return cxqVar;
    }

    public void c(DeviceDownloadSourceInfo deviceDownloadSourceInfo, String str, final DownloadCallback<dqi> downloadCallback, final PullHealthBiListener pullHealthBiListener) {
        if (deviceDownloadSourceInfo == null || downloadCallback == null) {
            LogUtil.a("HealthCloudDownloadPuller", "downloadSourceInfo or ezPluginFileInfo or callback is null");
            return;
        }
        LogUtil.a("HealthCloudDownloadPuller", "pullIndexFile begin");
        String configurationPoint = deviceDownloadSourceInfo.getConfigurationPoint();
        String indexName = deviceDownloadSourceInfo.getIndexName();
        dql dqlVar = new dql(configurationPoint, new HashMap());
        drd.d(new dqk.e().e(dqlVar).d(indexName).e(str).d(true).a(new DownloadCallback<dqi>() { // from class: cxq.4
            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onFinish(dqi dqiVar) {
                downloadCallback.onFinish(dqiVar);
                cxq.this.b(pullHealthBiListener, dqiVar.d(), dqiVar.d(), new msq());
            }

            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            public void onProgress(long j, long j2, boolean z, String str2) {
                downloadCallback.onProgress(j, j2, z, str2);
            }

            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            public void onFail(int i, Throwable th) {
                cxq.this.b(pullHealthBiListener, i, i, new msq());
                downloadCallback.onFail(i, th);
            }
        }).a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(PullHealthBiListener pullHealthBiListener, int i, int i2, msq msqVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("resultCode", i2);
        } catch (JSONException unused) {
            LogUtil.b("HealthCloudDownloadPuller", "healthCloudBiCallback JSONException");
        }
        if (pullHealthBiListener != null) {
            pullHealthBiListener.onPullHealthBiCalling(i, jSONObject.toString(), msqVar);
        }
    }

    private String a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ts", System.currentTimeMillis());
            jSONObject.put("configId", str);
        } catch (JSONException unused) {
            LogUtil.b("HealthCloudDownloadPuller", "getDownloadParam JSONException");
        }
        return jSONObject.toString().trim();
    }

    public void a(mru mruVar, PullListener pullListener, EzPluginPullerCallback ezPluginPullerCallback, PullHealthBiListener pullHealthBiListener) {
        if (pullListener == null || ezPluginPullerCallback == null) {
            LogUtil.a("HealthCloudDownloadPuller", "downloadDescription callback or ezPluginHealthCloudCallback is null");
        } else {
            d(mruVar, pullListener, ezPluginPullerCallback, pullHealthBiListener);
        }
    }

    private void d(final mru mruVar, final PullListener pullListener, final EzPluginPullerCallback ezPluginPullerCallback, final PullHealthBiListener pullHealthBiListener) {
        final msq msqVar = new msq();
        final mso msoVar = new mso();
        msqVar.f(mruVar.c());
        msqVar.b(pullListener);
        DeviceDownloadSourceInfo e = mruVar.e();
        if (e == null) {
            msoVar.b(1002);
            pullListener.onPullingChange(msqVar, msoVar);
            return;
        }
        msqVar.i(a(e.getConfigurationPoint()));
        if (e.getSite() == 1) {
            msqVar.e(pullHealthBiListener);
        }
        String str = mruVar.c() + "_description";
        dql dqlVar = new dql(e.getConfigurationPoint(), new HashMap());
        final String c = cxs.c(mruVar.c());
        drd.d(new dqk.e().e(dqlVar).d(str).e(c).a(new DownloadCallback<dqi>() { // from class: cxq.5
            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            public void onProgress(long j, long j2, boolean z, String str2) {
            }

            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onFinish(dqi dqiVar) {
                cxq.this.b(pullHealthBiListener, dqiVar.d(), dqiVar.d(), msqVar);
                msoVar.a(cxq.this.e(c, mruVar.c(), ezPluginPullerCallback).c());
                msoVar.b(1);
                pullListener.onPullingChange(msqVar, msoVar);
            }

            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            public void onFail(int i, Throwable th) {
                cxq.this.b(pullHealthBiListener, i, i, msqVar);
                msoVar.b(i);
                pullListener.onPullingChange(msqVar, msoVar);
            }
        }).a());
    }

    public void a(final String str, mru mruVar, final PullListener pullListener, PullHealthBiListener pullHealthBiListener) {
        c(mruVar, pullHealthBiListener, new PullListener() { // from class: cxu
            @Override // com.huawei.pluginmgr.filedownload.PullListener
            public final void onPullingChange(msq msqVar, mso msoVar) {
                cxq.this.b(str, pullListener, msqVar, msoVar);
            }
        });
    }

    /* synthetic */ void b(String str, PullListener pullListener, msq msqVar, mso msoVar) {
        if (msoVar == null || msoVar.i() != 1) {
            if (pullListener != null) {
                pullListener.onPullingChange(msqVar, msoVar);
            }
        } else {
            LogUtil.a("HealthCloudDownloadPuller", "onPullingChange onResult is 200");
            String e = msqVar.e();
            if ("6d5416d9-2167-41df-ab10-c492e152b44f".equals(str)) {
                e = cxs.b(str);
            }
            d(e, pullListener, msqVar, msoVar);
        }
    }

    private void c(mru mruVar, PullHealthBiListener pullHealthBiListener, PullListener pullListener) {
        msq msqVar = new msq();
        DeviceDownloadSourceInfo e = mruVar.e();
        if (e != null && e.getSite() == 1) {
            msqVar.i(a(e.getConfigurationPoint()));
            msqVar.e(pullHealthBiListener);
        }
        msqVar.a(cxs.b(mruVar.c()));
        msqVar.b(pullListener);
        msqVar.f(mruVar.c());
        a(mruVar, pullListener, pullHealthBiListener, msqVar, mruVar.a());
    }

    private void a(mru mruVar, final PullListener pullListener, final PullHealthBiListener pullHealthBiListener, final msq msqVar, final msc mscVar) {
        final mso msoVar = new mso();
        if (cxs.e() <= mscVar.c()) {
            LogUtil.a("HealthCloudDownloadPuller", "storage is not enough");
            msoVar.b(-9);
            pullListener.onPullingChange(msqVar, msoVar);
            return;
        }
        dql dqlVar = new dql(mruVar.e().getConfigurationPoint(), new HashMap());
        String str = mruVar.c() + "_new_v1.1";
        final String a2 = cxs.a(mruVar.j(), mruVar.c());
        drd.d(new dqk.e().e(dqlVar).d(str).e(a2).a(new DownloadCallback<dqi>() { // from class: cxq.1
            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onFinish(dqi dqiVar) {
                msoVar.b(1);
                if (msn.c(mscVar.b(), a2)) {
                    if ((CommonUtil.m(cxq.this.c, mscVar.d()) & 2) != 0) {
                        if (msp.c(a2, msqVar.e()) <= 0) {
                            LogUtil.a("HealthCloudDownloadPuller", "onFinish: unzip failure");
                            msoVar.b(-6);
                        }
                        cxs.d(a2, "unzip deleteFile is = ");
                    }
                    cxq.this.b(pullHealthBiListener, dqiVar.d(), dqiVar.d(), msqVar);
                    pullListener.onPullingChange(msqVar, msoVar);
                    return;
                }
                msoVar.b(-6);
                pullListener.onPullingChange(msqVar, msoVar);
            }

            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            public void onProgress(long j, long j2, boolean z, String str2) {
                msoVar.b(0);
                msoVar.e((int) j);
                pullListener.onPullingChange(msqVar, msoVar);
            }

            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            public void onFail(int i, Throwable th) {
                cxq.this.b(pullHealthBiListener, i, i, msqVar);
                msoVar.b(i);
                pullListener.onPullingChange(msqVar, msoVar);
            }
        }).a());
    }

    private String d(Context context, String str) {
        FileInputStream fileInputStream;
        LogUtil.a("HealthCloudDownloadPuller", "enter readFileToData: filePath = ", str);
        if (str == null || context == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder(1024);
        FileInputStream fileInputStream2 = null;
        try {
            try {
                fileInputStream = new FileInputStream(new File(str));
            } catch (IOException unused) {
            }
        } catch (Throwable th) {
            th = th;
            fileInputStream = fileInputStream2;
        }
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                sb.append(new String(bArr, 0, read, "UTF-8"));
            }
            IoUtils.e(fileInputStream);
        } catch (IOException unused2) {
            fileInputStream2 = fileInputStream;
            LogUtil.b("HealthCloudDownloadPuller", "readFileToData IOException");
            IoUtils.e(fileInputStream2);
            return sb.toString();
        } catch (Throwable th2) {
            th = th2;
            IoUtils.e(fileInputStream);
            throw th;
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public msc e(String str, String str2, EzPluginPullerCallback ezPluginPullerCallback) {
        String d = d(this.c, str);
        LogUtil.c("HealthCloudDownloadPuller", "downloadDescriptionFile descriptionJson success");
        msc d2 = msb.d(d);
        if (ezPluginPullerCallback == null) {
            EzPluginManager.a().d(d2, str2);
        } else {
            ezPluginPullerCallback.setEzPluginInfoCache(d2, str2);
        }
        return d2;
    }

    private void d(String str, PullListener pullListener, msq msqVar, mso msoVar) {
        FileOutputStream fileOutputStream;
        String valueOf;
        LogUtil.a("HealthCloudDownloadPuller", "createDoneMarkFile enter create done file, path is ", str);
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                File file = new File(str + File.separator + "done");
                if (!file.getParentFile().exists()) {
                    LogUtil.a("HealthCloudDownloadPuller", "createDoneMarkFile isDirMade is ", Boolean.valueOf(file.mkdir()));
                }
                if (!file.exists()) {
                    LogUtil.a("HealthCloudDownloadPuller", "createDoneMarkFile isNewFileCreated is ", Boolean.valueOf(file.createNewFile()));
                }
                valueOf = String.valueOf(new Date().getTime() / 1000);
                LogUtil.a("HealthCloudDownloadPuller", "createDoneMarkFile timeStamp is =", valueOf);
                fileOutputStream = new FileOutputStream(file);
            } catch (IOException unused) {
            }
        } catch (Throwable th) {
            th = th;
            fileOutputStream = fileOutputStream2;
        }
        try {
            byte[] bytes = valueOf.getBytes("UTF-8");
            fileOutputStream.write(bytes, 0, bytes.length);
            if (pullListener != null) {
                pullListener.onPullingChange(msqVar, msoVar);
            }
            IoUtils.e(fileOutputStream);
        } catch (IOException unused2) {
            fileOutputStream2 = fileOutputStream;
            LogUtil.b("HealthCloudDownloadPuller", "createDoneMarkFile createDoneFile IOException");
            msoVar.b(-1);
            if (pullListener != null) {
                pullListener.onPullingChange(msqVar, msoVar);
            }
            IoUtils.e(fileOutputStream2);
        } catch (Throwable th2) {
            th = th2;
            IoUtils.e(fileOutputStream);
            throw th;
        }
    }
}
