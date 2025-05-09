package defpackage;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.widget.Toast;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.configresource.downloaddevicemanager.DownloadPullerCallback;
import com.huawei.health.devicemgr.business.entity.downloadresouce.DeviceDownloadSourceInfo;
import com.huawei.health.healthcloudconfig.listener.DownloadCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginmgr.filedownload.PullHealthBiListener;
import com.huawei.pluginmgr.filedownload.PullListener;
import com.huawei.ui.main.stories.recommendcloud.constants.RecommendConstants;
import defpackage.dqk;
import health.compact.a.CommonUtil;
import health.compact.a.IoUtils;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
class cdv {
    private static final Object d = new Object();
    private static cdv e;
    private Context c = BaseApplication.getContext();

    private cdv() {
    }

    public static cdv b() {
        cdv cdvVar;
        synchronized (d) {
            if (e == null) {
                e = new cdv();
            }
            cdvVar = e;
        }
        return cdvVar;
    }

    public void c(msq msqVar) {
        msn.c().c(msqVar);
    }

    public void c(String str, cdj cdjVar, String str2, boolean z, PullListener pullListener) {
        LogUtil.a("ResourceHandlePuller", "pullIndexFile pull json task");
        msq d2 = d(str, cdjVar, str2, z, pullListener);
        d2.i(c(str, d2, true));
        if (msr.d.containsValue(str) || "device_index_all".equals(str) || "plugin_index".equals(str) || "index_sport_intensity".equals(d2.c())) {
            e(str, cdjVar, d2);
            d2.g("GET");
        }
        msn.c().a(d2);
    }

    private msq d(String str, cdj cdjVar, String str2, boolean z, PullListener pullListener) {
        msq msqVar = new msq();
        msqVar.a(str2);
        msqVar.h(cdjVar.a());
        msqVar.e(cdjVar.e());
        msqVar.b(pullListener);
        msqVar.d(0);
        msqVar.c(z);
        msqVar.b(str);
        return msqVar;
    }

    private void e(String str, cdj cdjVar, msq msqVar) {
        String a2 = cdjVar.a();
        if ("device_index_all".equals(str)) {
            a2 = cdjVar.a() + "device_index_all";
        }
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(1003), a2);
        if (TextUtils.isEmpty(b)) {
            return;
        }
        msqVar.d(b);
    }

    private void c(String str, String str2, String str3, String str4, PullListener pullListener) {
        LogUtil.a("ResourceHandlePuller", "pullDescriptionFile pull description task");
        msq msqVar = new msq();
        msqVar.i(c(a(str, str2, str3, pullListener, msqVar), msqVar, false));
        msqVar.e(str4);
        msn.c().a(msqVar);
    }

    private String a(String str, String str2, String str3, PullListener pullListener, msq msqVar) {
        msqVar.a(str3);
        msqVar.h(str2);
        msqVar.b(pullListener);
        msqVar.d(0);
        String str4 = str + "_description";
        msqVar.b(str4);
        return str4;
    }

    private String c(String str, msq msqVar, boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!d(jSONObject, msqVar, z, true)) {
                jSONObject.put(RecommendConstants.FILE_ID, str);
                jSONObject.put(RecommendConstants.VER, "0");
            }
        } catch (JSONException unused) {
            LogUtil.b("ResourceHandlePuller", "getDownloadParam JSONException");
        }
        return jSONObject.toString().trim();
    }

    private boolean d(JSONObject jSONObject, msq msqVar, boolean z, boolean z2) throws JSONException {
        int indexOf;
        String j = msqVar.j();
        if (TextUtils.isEmpty(j) || (indexOf = j.indexOf("/commonAbility/configCenter/")) == -1) {
            return false;
        }
        msqVar.d(indexOf > 0);
        if (!z || !msqVar.h()) {
            return false;
        }
        msqVar.g("POST");
        d(jSONObject, msqVar, j, indexOf, z2);
        b(jSONObject);
        return true;
    }

    private void d(JSONObject jSONObject, msq msqVar, String str, int i, boolean z) throws JSONException {
        jSONObject.put("ts", System.currentTimeMillis());
        jSONObject.put("source", 1);
        int i2 = i + 28;
        msqVar.h(str.substring(0, i2) + "getConfigInfo");
        int indexOf = str.indexOf("?", i2);
        if (indexOf > 0) {
            jSONObject.put("configId", str.substring(i2, indexOf));
            if (z) {
                c(jSONObject, str.substring(indexOf + 1));
                return;
            }
            return;
        }
        jSONObject.put("configId", str.substring(i2));
    }

    private void c(JSONObject jSONObject, String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String[] split = str.split("=");
        if (split.length == 0 || split.length % 2 != 0) {
            LogUtil.h("ResourceHandlePuller", "not support queryString=\"", str, "\"");
            return;
        }
        HashMap hashMap = new HashMap(split.length / 2);
        for (int i = 0; i < split.length; i += 2) {
            hashMap.put(split[i], split[i + 1]);
        }
        jSONObject.put("matchRules", new JSONObject(hashMap));
    }

    private void b(JSONObject jSONObject) throws JSONException {
        String e2 = msn.e();
        if (TextUtils.isEmpty(e2)) {
            return;
        }
        HashMap hashMap = new HashMap(1);
        hashMap.put("Device-ID", e2);
        jSONObject.put("greyRules", new JSONObject(hashMap));
    }

    public void d(msq msqVar, PullListener pullListener) {
        msqVar.b(pullListener);
        msqVar.d(2);
        msqVar.i(c(msqVar.c(), msqVar, false));
        msn.c().a(msqVar);
    }

    public void d(String str, String str2, String str3, cvc cvcVar, PullListener pullListener) {
        LogUtil.a("ResourceHandlePuller", "fileUrlJson is ", str3);
        b(str, new cdj(str, str2, str3, cvcVar), (String) null, pullListener);
    }

    public void b(final String str, cdj cdjVar, final PullListener pullListener, PullHealthBiListener pullHealthBiListener) {
        c(str, cdjVar, pullHealthBiListener, new PullListener() { // from class: cdv.1
            @Override // com.huawei.pluginmgr.filedownload.PullListener
            public void onPullingChange(msq msqVar, mso msoVar) {
                if (msoVar != null && msoVar.i() == 1) {
                    LogUtil.a("ResourceHandlePuller", "onPullingChange onResult is 200");
                    String e2 = msqVar.e();
                    if ("6d5416d9-2167-41df-ab10-c492e152b44f".equals(str)) {
                        e2 = cdv.this.d(str);
                    }
                    cdv.this.a(e2, pullListener, msqVar, msoVar);
                    return;
                }
                PullListener pullListener2 = pullListener;
                if (pullListener2 != null) {
                    pullListener2.onPullingChange(msqVar, msoVar);
                }
            }
        });
    }

    private void c(String str, cdj cdjVar, PullHealthBiListener pullHealthBiListener, PullListener pullListener) {
        msq msqVar = new msq();
        DeviceDownloadSourceInfo b = cdjVar.b();
        if (b != null && b.getSite() == 1) {
            msqVar.e(pullHealthBiListener);
        }
        msqVar.h(cdjVar.g());
        msqVar.i(b(cdjVar.b().getConfigurationPoint()));
        msqVar.b(str);
        String d2 = d(cdjVar.c());
        if (TextUtils.isEmpty(d2)) {
            d2 = d(cdjVar.c());
        }
        msqVar.a(d2);
        msqVar.b(pullListener);
        msqVar.f(cdjVar.c());
        cvc d3 = cdjVar.d();
        msqVar.d(CommonUtil.m(this.c, d3.c()));
        if (d() <= d3.d()) {
            LogUtil.a("ResourceHandlePuller", "storage is not enough");
            mso msoVar = new mso();
            msoVar.b(-9);
            pullListener.onPullingChange(msqVar, msoVar);
            return;
        }
        d(pullHealthBiListener, pullListener, d3, cdjVar, msqVar);
    }

    private String b(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ts", System.currentTimeMillis());
            jSONObject.put("configId", str);
        } catch (JSONException unused) {
            LogUtil.b("ResourceHandlePuller", "getDownloadParam JSONException");
        }
        return jSONObject.toString().trim();
    }

    private void d(PullHealthBiListener pullHealthBiListener, PullListener pullListener, cvc cvcVar, cdj cdjVar, msq msqVar) {
        mso msoVar = new mso();
        String b = b(msqVar);
        LogUtil.a("ResourceHandlePuller", "execHealthTask downloadCachePath", b);
        e(new AnonymousClass2(msoVar, pullListener, msqVar, cvcVar, b, pullHealthBiListener), cdjVar, msqVar, b);
    }

    /* renamed from: cdv$2, reason: invalid class name */
    class AnonymousClass2 implements DownloadCallback<dqi> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ cvc f652a;
        final /* synthetic */ PullHealthBiListener b;
        final /* synthetic */ PullListener c;
        final /* synthetic */ String e;
        final /* synthetic */ msq f;
        final /* synthetic */ mso g;

        AnonymousClass2(mso msoVar, PullListener pullListener, msq msqVar, cvc cvcVar, String str, PullHealthBiListener pullHealthBiListener) {
            this.g = msoVar;
            this.c = pullListener;
            this.f = msqVar;
            this.f652a = cvcVar;
            this.e = str;
            this.b = pullHealthBiListener;
        }

        @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onFinish(dqi dqiVar) {
            this.g.b(1);
            if (!dqiVar.c()) {
                LogUtil.a("ResourceHandlePuller", "execHealthTask getIsFromCloud", Boolean.valueOf(dqiVar.c()));
                this.c.onPullingChange(this.f, this.g);
                return;
            }
            if (msn.c(this.f652a.a(), this.e)) {
                if ((CommonUtil.m(cdv.this.c, this.f652a.c()) & 2) != 0) {
                    if (msp.c(this.e, this.f.e()) <= 0) {
                        LogUtil.a("ResourceHandlePuller", "onFinish: unzip failure");
                        this.g.b(-6);
                    }
                    cdv.this.a(this.e);
                }
                cdv.this.d(this.b, dqiVar.d(), dqiVar.d(), this.f);
                this.c.onPullingChange(this.f, this.g);
                return;
            }
            LogUtil.a("ResourceHandlePuller", "onFinish verifyHashCode fail ", -6);
            this.g.b(-6);
            this.c.onPullingChange(this.f, this.g);
        }

        @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
        public void onProgress(long j, long j2, boolean z, String str) {
            this.g.b(0);
            this.g.e((int) j);
            this.c.onPullingChange(this.f, this.g);
        }

        @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
        public void onFail(int i, Throwable th) {
            ReleaseLogUtil.c("R_ResourceHandlePuller", "execHealthTask on Fail: ", th.getMessage());
            if (i == 1005 && !CommonUtil.bv()) {
                HandlerExecutor.a(new Runnable() { // from class: cdx
                    @Override // java.lang.Runnable
                    public final void run() {
                        Toast.makeText(BaseApplication.getContext(), R.string._2130849763_res_0x7f022fe3, 1).show();
                    }
                });
            }
            cdv.this.d(this.b, i, i, this.f);
            this.g.b(i);
            this.c.onPullingChange(this.f, this.g);
        }
    }

    private void e(DownloadCallback downloadCallback, cdj cdjVar, msq msqVar, String str) {
        String[] list;
        File file = new File(msqVar.e() + File.separator + cdjVar.c());
        LogUtil.a("ResourceHandlePuller", "downloadFileResponse file: ", file.getPath());
        if (!file.exists()) {
            LogUtil.a("ResourceHandlePuller", "downloadFileResponse file not exists");
            drd.d(b(cdjVar, msqVar.c(), downloadCallback, str, false));
        } else if (file.exists() && file.isDirectory() && ((list = file.list()) == null || list.length == 0)) {
            LogUtil.a("ResourceHandlePuller", "downloadFileResponse file directory empty");
            drd.d(b(cdjVar, msqVar.c(), downloadCallback, str, false));
        } else {
            drd.d(b(cdjVar, msqVar.c(), downloadCallback, str, true));
        }
    }

    private dqk b(cdj cdjVar, String str, DownloadCallback downloadCallback, String str2, boolean z) {
        return new dqk.e().e(new dql(cdjVar.b().getConfigurationPoint(), new HashMap())).d(str).e(str2).d(z).a(downloadCallback).a();
    }

    private String b(msq msqVar) {
        if ((msqVar.f() & 6) != 0) {
            try {
                String str = this.c.getDir("cache", 0).getCanonicalPath() + File.pathSeparator;
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(jdq.e(this.c, msqVar.j() + msqVar.o()));
                return sb.toString();
            } catch (IOException e2) {
                LogUtil.b("ResourceHandlePuller", "getFilePathName:getCanonicalPath ex=", ExceptionUtils.d(e2));
                StringBuilder sb2 = new StringBuilder();
                sb2.append(File.pathSeparator);
                sb2.append(jdq.e(this.c, msqVar.j() + msqVar.o()));
                return sb2.toString();
            }
        }
        return msqVar.e() + ".tmp";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(PullHealthBiListener pullHealthBiListener, int i, int i2, msq msqVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("resultCode", i2);
        } catch (JSONException unused) {
            LogUtil.b("ResourceHandlePuller", "healthCloudBiCallback JSONException");
        }
        if (pullHealthBiListener != null) {
            pullHealthBiListener.onPullHealthBiCalling(i, jSONObject.toString(), msqVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        File file = new File(str);
        if (file.exists() && file.isFile()) {
            LogUtil.a("ResourceHandlePuller", "deleteDownloadFile", str, Boolean.valueOf(file.delete()));
        }
    }

    private void b(final String str, cdj cdjVar, String str2, final PullListener pullListener) {
        e(str, cdjVar, str2, new PullListener() { // from class: cdv.3
            @Override // com.huawei.pluginmgr.filedownload.PullListener
            public void onPullingChange(msq msqVar, mso msoVar) {
                if (msoVar != null && msoVar.i() == 1) {
                    LogUtil.a("ResourceHandlePuller", "onPullingChange onResult is 200");
                    String e2 = msqVar.e();
                    if ("6d5416d9-2167-41df-ab10-c492e152b44f".equals(str)) {
                        e2 = cdv.this.d(str);
                    }
                    cdv.this.a(e2, pullListener, msqVar, msoVar);
                    return;
                }
                PullListener pullListener2 = pullListener;
                if (pullListener2 != null) {
                    pullListener2.onPullingChange(msqVar, msoVar);
                }
            }
        });
    }

    private void e(String str, cdj cdjVar, String str2, PullListener pullListener) {
        msq msqVar = new msq();
        cvc d2 = cdjVar.d();
        msqVar.h(cdjVar.g());
        msqVar.e(cdjVar.e());
        if ("6d5416d9-2167-41df-ab10-c492e152b44f".equals(str)) {
            String str3 = str + "_new_v1.1";
            msqVar.i(c(str3, msqVar, false));
            msqVar.a(cuv.b);
            msqVar.b(str3);
        } else {
            msqVar.i(c(str, msqVar, false));
            msqVar.b(str);
        }
        c(cdjVar, str2, pullListener, msqVar, d2);
    }

    private void c(cdj cdjVar, String str, PullListener pullListener, msq msqVar, cvc cvcVar) {
        if (TextUtils.isEmpty(str)) {
            str = d(cdjVar.c());
        }
        msqVar.a(str);
        msqVar.c(cvcVar.a());
        msqVar.b(pullListener);
        msqVar.d(CommonUtil.m(this.c, cvcVar.c()));
        msqVar.b(cvcVar.d());
        msqVar.f(cdjVar.c());
        if (d() > cvcVar.d()) {
            LogUtil.a("ResourceHandlePuller", "addTask plugin task");
            msn.c().a(msqVar);
        } else {
            LogUtil.a("ResourceHandlePuller", "addTask this torage is not enough");
            mso msoVar = new mso();
            msoVar.b(-9);
            pullListener.onPullingChange(msqVar, msoVar);
        }
    }

    public void c(String str, String str2, PullListener pullListener, String str3) {
        LogUtil.c("ResourceHandlePuller", "downloadDescription eneter downlodDescription.fileUrlJson is ", str3);
        e(str, str2, cuv.f11488a + str + File.separator + "description.json", str3, pullListener);
    }

    public void c(cdj cdjVar, PullListener pullListener, DownloadPullerCallback downloadPullerCallback, PullHealthBiListener pullHealthBiListener) {
        if (pullListener == null || downloadPullerCallback == null) {
            LogUtil.a("ResourceHandlePuller", "downloadDescription callback or ezPluginHealthCloudCallback is null");
        } else {
            d(cdjVar, pullListener, downloadPullerCallback, pullHealthBiListener);
        }
    }

    private void d(cdj cdjVar, final PullListener pullListener, final DownloadPullerCallback downloadPullerCallback, PullHealthBiListener pullHealthBiListener) {
        final String str = cuv.f11488a + cdjVar.c() + File.separator + "description.json";
        d(cdjVar, str, pullHealthBiListener, new PullListener() { // from class: cdv.4
            @Override // com.huawei.pluginmgr.filedownload.PullListener
            public void onPullingChange(msq msqVar, mso msoVar) {
                cdv.this.a(msqVar, msoVar, str, pullListener, downloadPullerCallback);
            }
        });
    }

    private void d(cdj cdjVar, String str, PullHealthBiListener pullHealthBiListener, PullListener pullListener) {
        LogUtil.a("ResourceHandlePuller", "pullDescriptionFile pull description task");
        msq msqVar = new msq();
        DeviceDownloadSourceInfo b = cdjVar.b();
        if (b != null && b.getSite() == 1) {
            msqVar.e(pullHealthBiListener);
        }
        a(cdjVar.c(), cdjVar.a(), str, pullListener, msqVar);
        msqVar.i(b(cdjVar.b().getConfigurationPoint()));
        AnonymousClass5 anonymousClass5 = new AnonymousClass5(pullHealthBiListener, msqVar, new mso(), pullListener);
        dqk b2 = b(cdjVar, msqVar.c(), (DownloadCallback) anonymousClass5, str, true);
        if (!new File(str).exists()) {
            b2 = b(cdjVar, msqVar.c(), (DownloadCallback) anonymousClass5, str, false);
        }
        drd.d(b2);
    }

    /* renamed from: cdv$5, reason: invalid class name */
    class AnonymousClass5 implements DownloadCallback<dqi> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ PullListener f654a;
        final /* synthetic */ PullHealthBiListener b;
        final /* synthetic */ mso c;
        final /* synthetic */ msq e;

        AnonymousClass5(PullHealthBiListener pullHealthBiListener, msq msqVar, mso msoVar, PullListener pullListener) {
            this.b = pullHealthBiListener;
            this.e = msqVar;
            this.c = msoVar;
            this.f654a = pullListener;
        }

        @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onFinish(dqi dqiVar) {
            LogUtil.a("ResourceHandlePuller", "pullDescriptionFile onFinish, data is: ", dqiVar.toString());
            cdv.this.d(this.b, dqiVar.d(), dqiVar.d(), this.e);
            this.c.b(1);
            this.f654a.onPullingChange(this.e, this.c);
        }

        @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
        public void onProgress(long j, long j2, boolean z, String str) {
            LogUtil.a("ResourceHandlePuller", "pullDescriptionFile onProgress, handleBytes: ", Long.valueOf(j), ", contentLength: ", Long.valueOf(j2), ", isDone: ", Boolean.valueOf(z), ", fileId: ", str);
        }

        @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
        public void onFail(int i, Throwable th) {
            cdv.this.d(this.b, i, i, this.e);
            ReleaseLogUtil.c("R_ResourceHandlePuller", "pullDescriptionFile on Fail: ", th.getMessage());
            if (i == 1005 && !CommonUtil.bv()) {
                LogUtil.a("ResourceHandlePuller", "pullDescriptionFile toast");
                HandlerExecutor.a(new Runnable() { // from class: cdt
                    @Override // java.lang.Runnable
                    public final void run() {
                        Toast.makeText(BaseApplication.getContext(), R.string._2130849763_res_0x7f022fe3, 1).show();
                    }
                });
            }
            this.c.b(i);
            this.f654a.onPullingChange(this.e, this.c);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(msq msqVar, mso msoVar, String str, PullListener pullListener, DownloadPullerCallback downloadPullerCallback) {
        if (msoVar == null) {
            LogUtil.h("ResourceHandlePuller", "downloadDescriptionFile onPullingChange result is null");
            pullListener.onPullingChange(msqVar, null);
            return;
        }
        int i = msoVar.i();
        if (i != 1) {
            if (i == 0) {
                return;
            }
            pullListener.onPullingChange(msqVar, msoVar);
            return;
        }
        String a2 = a(this.c, str);
        LogUtil.c("ResourceHandlePuller", "downloadDescriptionFile descriptionJson success");
        cvc e2 = cde.e(a2);
        String o = msqVar.o();
        if (downloadPullerCallback == null) {
            cdk.e().e(e2, o);
        } else {
            downloadPullerCallback.setDescriptionInfoCache(e2, o);
        }
        msoVar.a(e2.d());
        pullListener.onPullingChange(msqVar, msoVar);
    }

    public void e(String str, String str2, final String str3, String str4, final PullListener pullListener) {
        c(str, str2, str3, str4, new PullListener() { // from class: cdv.6
            @Override // com.huawei.pluginmgr.filedownload.PullListener
            public void onPullingChange(msq msqVar, mso msoVar) {
                cdv.this.c(msqVar, msoVar, str3, pullListener);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(msq msqVar, mso msoVar, String str, PullListener pullListener) {
        if (msoVar == null) {
            LogUtil.h("ResourceHandlePuller", "downloadDescriptionFile onPullingChange result is null");
            pullListener.onPullingChange(msqVar, null);
            return;
        }
        int i = msoVar.i();
        if (i != 1) {
            if (i == 0) {
                return;
            }
            pullListener.onPullingChange(msqVar, msoVar);
            return;
        }
        String a2 = a(this.c, str);
        LogUtil.c("ResourceHandlePuller", "downloadDescriptionFile descriptionJson success");
        cvc e2 = cde.e(a2);
        cdk.e().e(e2, msqVar.o());
        msoVar.a(e2.d());
        pullListener.onPullingChange(msqVar, msoVar);
    }

    private String a(Context context, String str) {
        FileInputStream fileInputStream;
        LogUtil.a("ResourceHandlePuller", "enter readFileToData: filePath = ", str);
        FileInputStream fileInputStream2 = null;
        if (str == null || context == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(1024);
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
            LogUtil.b("ResourceHandlePuller", "readFileToData IOException");
            IoUtils.e(fileInputStream2);
            return sb.toString();
        } catch (Throwable th2) {
            th = th2;
            IoUtils.e(fileInputStream);
            throw th;
        }
        return sb.toString();
    }

    public String d(String str) {
        if ("6d5416d9-2167-41df-ab10-c492e152b44f".equals(str)) {
            return cuv.b;
        }
        return cuv.f11488a + str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, PullListener pullListener, msq msqVar, mso msoVar) {
        FileOutputStream fileOutputStream;
        String valueOf;
        LogUtil.a("ResourceHandlePuller", "createDoneMarkFile enter create done file, path is ", str);
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                File file = new File(str + File.separator + "done");
                if (!file.getParentFile().exists()) {
                    LogUtil.a("ResourceHandlePuller", "createDoneMarkFile isDirMade is ", Boolean.valueOf(file.mkdir()));
                }
                if (!file.exists()) {
                    LogUtil.a("ResourceHandlePuller", "createDoneMarkFile isNewFileCreated is ", Boolean.valueOf(file.createNewFile()));
                }
                valueOf = String.valueOf(new Date().getTime() / 1000);
                LogUtil.a("ResourceHandlePuller", "createDoneMarkFile timeStamp is =", valueOf);
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
            LogUtil.b("ResourceHandlePuller", "createDoneMarkFile createDoneFile IOException");
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

    public static long d() {
        if (!"mounted".equals(Environment.getExternalStorageState())) {
            return 0L;
        }
        try {
            StatFs statFs = new StatFs(CommonUtil.j((Context) null).getPath());
            return statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
        } catch (IllegalArgumentException unused) {
            LogUtil.b("ResourceHandlePuller", "getAvailCount Exception");
            return -1L;
        }
    }
}
