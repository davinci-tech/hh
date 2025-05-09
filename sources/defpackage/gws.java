package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.view.View;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.healthcloudconfig.listener.DownloadCallback;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.maps.internal.HmsUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginmgr.EzPluginType;
import com.huawei.pluginmgr.filedownload.PullListener;
import com.huawei.ui.commonui.dialog.CustomProgressDialog;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import health.compact.a.CommonUtil;
import health.compact.a.GrsDownloadPluginUrl;
import health.compact.a.LogAnonymous;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class gws {
    private static final String c = mrv.d + "smart_coach_index" + File.separator;

    /* renamed from: a, reason: collision with root package name */
    private Activity f12977a;
    private CustomProgressDialog b;
    private CustomProgressDialog.Builder e;
    private String m;
    private mrx s = new mrx(EzPluginType.SMART_COACH_RESOURCES_TYPE, null);
    private List<msa> o = new ArrayList(16);
    private List<dqh> g = new ArrayList();
    private List<String> j = new ArrayList();
    private long r = 0;
    private long h = 0;
    private long n = 0;
    private boolean k = false;
    private boolean l = false;
    private CountDownLatch d = new CountDownLatch(1);
    private PullListener i = new PullListener() { // from class: gws.1
        @Override // com.huawei.pluginmgr.filedownload.PullListener
        public void onPullingChange(msq msqVar, mso msoVar) {
            if (msoVar == null) {
                LogUtil.b("Track_SmartCoachDownloadUtils", "result is null");
                return;
            }
            int i = msoVar.i();
            LogUtil.a("Track_SmartCoachDownloadUtils", "mDownloadConfigFileListener index file status = ", Integer.valueOf(i));
            if (i == 1) {
                gws.this.a(BaseApplication.getContext());
            }
        }
    };
    private Handler f = new Handler(Looper.getMainLooper()) { // from class: gws.4
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.b("Track_SmartCoachDownloadUtils", "mHandler msg is null.");
                return;
            }
            super.handleMessage(message);
            if (gws.this.f12977a != null) {
                int i = message.what;
                if (i == -1) {
                    gws.this.o();
                    nrh.b(gws.this.f12977a, R.string._2130840215_res_0x7f020a97);
                } else if (i == 100) {
                    gws.this.k();
                } else if (i == 102) {
                    gws.this.y();
                } else {
                    if (i != 103) {
                        return;
                    }
                    gws.this.s();
                }
            }
        }
    };

    private static JSONObject m() {
        String e = mrx.e(new File(CommonUtil.c(c + File.separator + "smart_coach_index.json")));
        LogUtil.a("Track_SmartCoachDownloadUtils", "getCloudConfigScriptJsonObject jsonData", e);
        if (TextUtils.isEmpty(e)) {
            e = b("local_smart_coach_index.json");
        }
        try {
            return new JSONObject(e);
        } catch (JSONException unused) {
            LogUtil.b("Track_SmartCoachDownloadUtils", "getCloudConfigScriptJsonObject jsonException");
            return null;
        }
    }

    public void a(Context context) {
        CountDownLatch countDownLatch;
        if (context == null) {
            LogUtil.b("Track_SmartCoachDownloadUtils", "setMapConfig context is null.");
            return;
        }
        JSONObject m = m();
        if (m == null) {
            LogUtil.b("Track_SmartCoachDownloadUtils", "setMapConfig jsonData is null");
            SharedPreferenceManager.e(context, Integer.toString(20002), "hms_map_config_key", "false", new StorageParams());
            return;
        }
        try {
            try {
                String string = m.getString("hmsEnable");
                SharedPreferenceManager.e(context, Integer.toString(20002), "hms_forbidden_key", m.getJSONArray("noHmsCountries").toString(), new StorageParams());
                LogUtil.a("Track_SmartCoachDownloadUtils", "setMapConfig isForbiddenCountry", Boolean.valueOf(gwg.j()));
                LogUtil.a("Track_SmartCoachDownloadUtils", "setMapConfig hmsEnable: ", string);
                SharedPreferenceManager.e(context, Integer.toString(20002), "hms_map_config_key", string, new StorageParams());
                countDownLatch = this.d;
                if (countDownLatch == null) {
                    return;
                }
            } catch (JSONException unused) {
                LogUtil.b("Track_SmartCoachDownloadUtils", "setMapConfig JSONException");
                countDownLatch = this.d;
                if (countDownLatch == null) {
                    return;
                }
            }
            countDownLatch.countDown();
        } catch (Throwable th) {
            CountDownLatch countDownLatch2 = this.d;
            if (countDownLatch2 != null) {
                countDownLatch2.countDown();
            }
            throw th;
        }
    }

    private static String b(String str) {
        try {
            return gzv.a(BaseApplication.getContext().getAssets().open(str));
        } catch (IOException unused) {
            LogUtil.b("Track_SmartCoachDownloadUtils", "getString IOException");
            return "";
        }
    }

    public void aVg_(Activity activity, DownloadCallback downloadCallback) {
        if (activity == null || downloadCallback == null) {
            LogUtil.h("Track_SmartCoachDownloadUtils", "checkDownloadVoiceResource failed, activity or callback is null.");
            return;
        }
        this.f12977a = activity;
        this.m = b();
        drd.a(p(), "smart_coach_index", (DownloadCallback<File>) downloadCallback);
    }

    private dql p() {
        HashMap hashMap = new HashMap();
        hashMap.put("configType", "SmartCoach");
        return new dql("com.huawei.health_Sport_Common", hashMap);
    }

    public void c(IBaseResponseCallback iBaseResponseCallback) {
        if (this.r == 0 || koq.b(this.o)) {
            LogUtil.h("Track_SmartCoachDownloadUtils", "getResourcesFromIndexFile mNeedDownloadByte = ", Long.valueOf(this.r), " mIndexInfo = ", this.o);
            return;
        }
        this.h = 0L;
        this.n = 0L;
        this.k = false;
        this.l = false;
        this.j = new ArrayList();
        b(iBaseResponseCallback);
    }

    private List<String> f() {
        ArrayList arrayList = new ArrayList();
        for (msa msaVar : this.o) {
            arrayList.add(msaVar.b());
            if (!koq.b(this.g)) {
                for (dqh dqhVar : this.g) {
                    if (msaVar.b().equals(dqhVar.a())) {
                        this.j.add(dqhVar.d());
                    }
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        try {
            File file = new File(str);
            if (sqd.d(file, new File(mrv.d).getCanonicalPath())) {
                ReleaseLogUtil.b("Track_SmartCoachDownloadUtils", "removeAllResourceFiles isDeleted ", Boolean.valueOf(b(file)));
            } else {
                LogUtil.h("Track_SmartCoachDownloadUtils", "removeAllResourceFiles isValidFile false path ", str);
            }
        } catch (IOException e) {
            ReleaseLogUtil.c("Track_SmartCoachDownloadUtils", "removeAllResourceFiles exception ", ExceptionUtils.d(e));
        }
    }

    private boolean b(File file) {
        File[] listFiles;
        if (file == null || !file.exists()) {
            LogUtil.a("Track_SmartCoachDownloadUtils", "deleteFiles, root == null or not exist");
            return false;
        }
        if (file.isFile()) {
            return file.delete();
        }
        if (file.isDirectory() && (listFiles = file.listFiles()) != null && listFiles.length > 0) {
            for (File file2 : listFiles) {
                b(file2);
            }
        }
        LogUtil.a("Track_SmartCoachDownloadUtils", "delete result= ", Boolean.valueOf(file.delete()));
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: gws.2
                @Override // java.lang.Runnable
                public void run() {
                    gws.this.w();
                }
            });
            return;
        }
        LogUtil.a("Track_SmartCoachDownloadUtils", "updateIndexFile, queryStr = ", this.s.e(new GrsDownloadPluginUrl().getDownloadPluginUrl(null, true), (String) null));
        this.s.e(this.i);
    }

    public void j() {
        w();
    }

    private void b(IBaseResponseCallback iBaseResponseCallback) {
        Message obtain = Message.obtain();
        obtain.what = 102;
        this.f.sendMessage(obtain);
        d(f(), iBaseResponseCallback);
    }

    public long c() {
        List<msa> b = this.s.b();
        long j = 0;
        if (koq.b(b)) {
            LogUtil.h("Track_SmartCoachDownloadUtils", "indexInfo is null");
            return 0L;
        }
        while (b.iterator().hasNext()) {
            j += r0.next().e();
        }
        return j;
    }

    private void d(List<String> list, final IBaseResponseCallback iBaseResponseCallback) {
        drd.d(p(), "Track_SmartCoachDownloadUtils", list, new DownloadCallback<List<File>>() { // from class: gws.5
            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onFinish(List<File> list2) {
                if (koq.b(list2)) {
                    LogUtil.h("Track_SmartCoachDownloadUtils", "downloadBatchList finish, data is empty.");
                    return;
                }
                LogUtil.a("Track_SmartCoachDownloadUtils", "downloadBatchList finish, data is = ", list2.toString());
                gws gwsVar = gws.this;
                gwsVar.a(gwsVar.c("1.1.1"));
                gws.this.c(list2);
                gws.this.f.sendEmptyMessage(100);
            }

            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            public void onProgress(long j, long j2, boolean z, String str) {
                LogUtil.a("Track_SmartCoachDownloadUtils", "downloadBatchList fileId = ", str, " downloadedBytes = ", Long.valueOf(j));
                if (j - gws.this.n > PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED || z) {
                    gws.this.n = j;
                    gws.this.h = j;
                    gws.this.f.sendEmptyMessage(103);
                }
            }

            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            public void onFail(int i, Throwable th) {
                LogUtil.h("Track_SmartCoachDownloadUtils", "downloadBatchList failed, errCode = ", Integer.valueOf(i));
                if (gws.this.k || gws.this.l) {
                    return;
                }
                gws.this.l = true;
                IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                if (iBaseResponseCallback2 != null) {
                    iBaseResponseCallback2.d(-1, null);
                }
                gws.this.f.sendEmptyMessage(-1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        CustomProgressDialog customProgressDialog = this.b;
        if (customProgressDialog != null && customProgressDialog.isShowing() && !this.f12977a.isFinishing()) {
            this.b.cancel();
        }
        LogUtil.a("Track_SmartCoachDownloadUtils", "finish closeProgressDialog cancel");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<File> list) {
        final String q = q();
        for (final File file : list) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: gwt
                @Override // java.lang.Runnable
                public final void run() {
                    gws.this.b(file, q);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void b(File file, String str) {
        try {
            String canonicalPath = file.getCanonicalPath();
            String substring = canonicalPath.substring(canonicalPath.lastIndexOf("/") + 1, canonicalPath.indexOf(".zip"));
            LogUtil.a("Track_SmartCoachDownloadUtils", "dealMoveAllResourcesFile filePath = ", canonicalPath, ", fileId = ", substring);
            int e = dro.e(canonicalPath, str);
            if (TextUtils.isEmpty(substring) || e == -1) {
                return;
            }
            a(c(substring), str);
            FileUtils.d(new File(canonicalPath));
        } catch (IOException unused) {
            LogUtil.h("Track_SmartCoachDownloadUtils", "dealMoveAllResourcesFile exception.");
        }
    }

    private String q() {
        StringBuilder sb = new StringBuilder(32);
        sb.append(mrv.d);
        sb.append("smart_coach_res");
        sb.append(File.separator);
        return sb.toString();
    }

    public String c(String str) {
        return q() + str + File.separator;
    }

    public boolean h() {
        this.s.i();
        List<msa> b = this.s.b();
        long d = hab.d();
        long b2 = b(b);
        LogUtil.a("Track_SmartCoachDownloadUtils", "isNeedTriggerUpdate localExistFileCount = ", Long.valueOf(d), " currentVersionFileCount = ", Long.valueOf(b2));
        boolean z = e() || d == 0 || d < b2;
        if (z) {
            this.r = c();
            this.o = b;
            n();
        }
        return z;
    }

    public boolean i() {
        String d = drd.d("com.huawei.health_Sport_Common", "smart_coach_index", "json");
        if (TextUtils.isEmpty(d)) {
            LogUtil.h("Track_SmartCoachDownloadUtils", "isIndexFileMoveFinish cloudFilePath is empty.");
            return false;
        }
        try {
            String indexFileSavePath = this.s.a().getIndexFileSavePath();
            FileUtils.d(new File(d), new File(indexFileSavePath), true);
            LogUtil.a("Track_SmartCoachDownloadUtils", "moveFile success, fromPath = ", d, " toPath = ", indexFileSavePath);
            return true;
        } catch (IOException unused) {
            LogUtil.h("Track_SmartCoachDownloadUtils", "isIndexFileMoveFinish moveFile failed.");
            return false;
        }
    }

    public boolean d() {
        boolean z = hab.d() <= 0;
        if (z) {
            this.r = c();
            this.o = this.s.b();
            n();
        }
        return z;
    }

    public void g() {
        a(c);
    }

    private void a(String str, String str2) {
        File file = new File(str);
        if (!CommonUtil.c(file)) {
            LogUtil.b("Track_SmartCoachDownloadUtils", "file path not valid");
            return;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length == 0) {
            return;
        }
        File file2 = new File(str2);
        if (!file2.exists()) {
            LogUtil.c("Track_SmartCoachDownloadUtils", "mkdirs result= ", Boolean.valueOf(file2.mkdirs()));
        }
        boolean z = true;
        for (File file3 : listFiles) {
            if (file3.isDirectory()) {
                a(file3.getPath(), str2 + File.separator + file3.getName());
            }
            if (file3.isFile()) {
                StringBuilder sb = new StringBuilder(32);
                sb.append(file2);
                sb.append(File.separator);
                sb.append(file3.getName());
                z &= file3.renameTo(new File(sb.toString()));
            }
            z &= file3.delete();
        }
        LogUtil.a("Track_SmartCoachDownloadUtils", "fromDir, isFileDelete = ", Boolean.valueOf(file.delete() & z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        CustomProgressDialog customProgressDialog = this.b;
        if (customProgressDialog != null && customProgressDialog.isShowing()) {
            LogUtil.b("Track_SmartCoachDownloadUtils", "startDownLoadProgress exists");
            return;
        }
        this.b = new CustomProgressDialog(this.f12977a);
        CustomProgressDialog.Builder builder = new CustomProgressDialog.Builder(this.f12977a);
        this.e = builder;
        builder.d(this.f12977a.getString(R.string._2130850271_res_0x7f0231df)).cyH_(new View.OnClickListener() { // from class: gws.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Track_SmartCoachDownloadUtils", "startDownLoadProgress onclick cancel");
                gws.this.k = true;
                gws.this.t();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomProgressDialog e = this.e.e();
        this.b = e;
        e.setCanceledOnTouchOutside(false);
        if (!this.f12977a.isFinishing()) {
            this.b.show();
            this.e.d(0);
            this.e.e(UnitUtil.e(0.0d, 2, 0));
        }
        LogUtil.a("Track_SmartCoachDownloadUtils", "mCustomProgressDialog.show()");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        CustomProgressDialog customProgressDialog = this.b;
        if (customProgressDialog == null || !customProgressDialog.isShowing() || this.r == 0) {
            return;
        }
        LogUtil.c("Track_SmartCoachDownloadUtils", "showDownloadProgress downloaded", Long.valueOf(this.h), "total", Long.valueOf(this.r));
        long j = (this.h * 100) / this.r;
        if (j > 99) {
            j = 99;
        }
        this.e.d((int) j);
        String e = UnitUtil.e(j, 2, 0);
        this.e.e(e);
        LogUtil.c("Track_SmartCoachDownloadUtils", "showDownloadProgress percentNum", e);
    }

    private void n() {
        if (koq.b(this.o)) {
            LogUtil.h("Track_SmartCoachDownloadUtils", "acquireFileList failed, mIndexInfo is empty.");
            this.g = new ArrayList();
            return;
        }
        dqi d = drd.d(this.o.get(0).b(), p());
        if (d == null) {
            LogUtil.h("Track_SmartCoachDownloadUtils", "acquireFileList failed, response is null.");
            this.g = new ArrayList();
        } else {
            this.g = d.e();
            LogUtil.a("Track_SmartCoachDownloadUtils", "acquireFileList end.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        LogUtil.a("Track_SmartCoachDownloadUtils", "enter handleCancel");
        o();
        Iterator<String> it = this.j.iterator();
        while (it.hasNext()) {
            lqi.d().d(it.next());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        LogUtil.a("Track_SmartCoachDownloadUtils", "enter closeProgress");
        o();
        l();
        SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(20002), "smart_coach_update_layout_count", String.valueOf(0), (StorageParams) null);
        nrh.b(this.f12977a, R.string._2130843721_res_0x7f021849);
    }

    private void l() {
        LogUtil.a("Track_SmartCoachDownloadUtils", "enter closeSportTabDownloadResDialog()");
        Intent intent = new Intent();
        intent.setPackage(BaseApplication.getContext().getPackageName());
        intent.setAction("com.huawei.health.action_receive_data_notify");
        LocalBroadcastManager.getInstance(BaseApplication.getContext()).sendBroadcast(intent);
    }

    public void d(final Context context) {
        ThreadPoolManager.d().d("checkHmsUpdate", new Runnable() { // from class: gws.8
            @Override // java.lang.Runnable
            public void run() {
                gws.this.j();
                try {
                    if (gws.this.d != null) {
                        LogUtil.a("Track_SmartCoachDownloadUtils", "has wait 5 seconds, isCountDown: ", Boolean.valueOf(gws.this.d.await(5L, TimeUnit.SECONDS)));
                    }
                } catch (InterruptedException e) {
                    LogUtil.b("Track_SmartCoachDownloadUtils", LogAnonymous.b((Throwable) e));
                }
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: gws.8.4
                    @Override // java.lang.Runnable
                    public void run() {
                        if (context != null && gwe.d(context) && Utils.o() && gws.this.r()) {
                            if (CommonUtil.b(context)) {
                                LogUtil.a("Track_SmartCoachDownloadUtils", "HmsUtil status: ", Integer.valueOf(HmsUtil.isHmsAvailable(context)));
                                return;
                            }
                            NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(context);
                            builder.e(R.string._2130841236_res_0x7f020e94).czC_(R.string._2130841237_res_0x7f020e95, new View.OnClickListener() { // from class: gws.8.4.5
                                @Override // android.view.View.OnClickListener
                                public void onClick(View view) {
                                    ViewClickInstrumentation.clickOnView(view);
                                }
                            });
                            builder.e().show();
                        }
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean r() {
        return (gwg.j() || gwg.h()) ? false : true;
    }

    public void d(IBaseResponseCallback iBaseResponseCallback) {
        if (CommonUtil.ah(BaseApplication.getContext())) {
            c(String.format(this.f12977a.getResources().getString(R.string._2130850270_res_0x7f0231de), this.f12977a.getString(R.string.IDS_device_upgrade_file_size_mb, new Object[]{Integer.valueOf(((int) this.r) / 1048576)})), iBaseResponseCallback);
            return;
        }
        c(iBaseResponseCallback);
    }

    private void c(final String str, final IBaseResponseCallback iBaseResponseCallback) {
        Activity activity;
        if (str == null || (activity = this.f12977a) == null) {
            LogUtil.h("Track_SmartCoachDownloadUtils", "showSmartCoachDownloadTips text or mActivity is null.");
        } else {
            activity.runOnUiThread(new Runnable() { // from class: gws.7
                @Override // java.lang.Runnable
                public void run() {
                    new CustomTextAlertDialog.Builder(gws.this.f12977a).b(R.string._2130839727_res_0x7f0208af).e(str).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: gws.7.3
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            LogUtil.c("Track_SmartCoachDownloadUtils", "showSmartCoachDownloadTips setNegativeButton onclick called String");
                            ViewClickInstrumentation.clickOnView(view);
                        }
                    }).cyU_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: gws.7.2
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            LogUtil.c("Track_SmartCoachDownloadUtils", "setPositiveButton onclick called String");
                            gws.this.c(iBaseResponseCallback);
                            ViewClickInstrumentation.clickOnView(view);
                        }
                    }).a().show();
                }
            });
        }
    }

    public long d(String str) {
        List<msa> b = this.s.b();
        long j = 0;
        if (koq.b(b)) {
            LogUtil.h("Track_SmartCoachDownloadUtils", "indexInfo is null");
            return 0L;
        }
        for (msa msaVar : b) {
            if (str.equals(msaVar.h())) {
                j += msaVar.c();
            }
        }
        return j;
    }

    public long b(List<msa> list) {
        long j = 0;
        if (koq.b(list)) {
            LogUtil.h("Track_SmartCoachDownloadUtils", "indexInfo is null");
            return 0L;
        }
        Iterator<msa> it = list.iterator();
        while (it.hasNext()) {
            j += it.next().c();
        }
        return j;
    }

    public String b() {
        return this.s.i() ? this.s.d() : "";
    }

    public boolean e() {
        if (TextUtils.isEmpty(this.m)) {
            return true;
        }
        this.s.i();
        String d = this.s.d();
        LogUtil.a("Track_SmartCoachDownloadUtils", "isNeedUpdateNewVersion lastVersion = ", this.m, " currentIndexFileVersion = ", d);
        if (TextUtils.isEmpty(d)) {
            return false;
        }
        return d(this.m, d);
    }

    private boolean d(String str, String str2) {
        int h;
        int h2;
        if (TextUtils.isEmpty(str2)) {
            return false;
        }
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        if (split.length < split2.length) {
            return true;
        }
        if (split.length > split2.length) {
            return false;
        }
        for (int i = 0; i < split.length && (h = CommonUtil.h(split[i])) <= (h2 = CommonUtil.h(split2[i])); i++) {
            if (h < h2) {
                return true;
            }
        }
        return false;
    }

    public static boolean a() {
        JSONObject m = m();
        if (m == null) {
            LogUtil.h("Track_SmartCoachDownloadUtils", "isSupportCurrentPhoneModule jsonData is null.");
            return false;
        }
        String str = Build.BRAND + " " + Build.MODEL;
        LogUtil.c("Track_SmartCoachDownloadUtils", "isSupportCurrentPhoneModule phoneMode = ", str);
        try {
        } catch (JSONException unused) {
            LogUtil.b("Track_SmartCoachDownloadUtils", "isSupportCurrentPhoneModule JSONException");
        }
        if (!m.has("smartCoachSupportPhoneModle")) {
            return true;
        }
        JSONArray jSONArray = m.getJSONArray("smartCoachSupportPhoneModle");
        for (int i = 0; i < jSONArray.length(); i++) {
            if (str.contains(jSONArray.getString(i))) {
                return true;
            }
        }
        return false;
    }
}
