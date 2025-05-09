package defpackage;

import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.bundle.AppBundleDownloader;
import com.huawei.haf.bundle.extension.BundleExtension;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.download.DownloadPluginCallback;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import health.compact.a.AuthorizationUtils;
import health.compact.a.GrsDownloadPluginUrl;
import health.compact.a.LogUtil;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
final class ccm implements AppBundleDownloader {

    /* renamed from: a, reason: collision with root package name */
    private mtk f611a;
    private boolean b;
    private final Map<Integer, Integer> e = new HashMap();

    ccm() {
    }

    @Override // com.huawei.haf.bundle.AppBundleDownloader
    public void startDownload(int i, List<AppBundleDownloader.DownloadRequest> list, AppBundleDownloader.DownloadCallback downloadCallback) {
        d(false, i, list, downloadCallback);
    }

    @Override // com.huawei.haf.bundle.AppBundleDownloader
    public void deferredDownload(int i, List<AppBundleDownloader.DownloadRequest> list, AppBundleDownloader.DownloadCallback downloadCallback) {
        d(true, i, list, downloadCallback);
    }

    @Override // com.huawei.haf.bundle.AppBundleDownloader
    public boolean cancelDownloadSync(int i) {
        int d2 = d(i);
        if (d2 <= 0) {
            return true;
        }
        e().b(d2);
        return true;
    }

    private void d(boolean z, int i, List<AppBundleDownloader.DownloadRequest> list, AppBundleDownloader.DownloadCallback downloadCallback) {
        if (e(i)) {
            LogUtil.c("Bundle_Downloader", "exist same download task. taskId=", String.valueOf(i), ", ", "isDeferred=", String.valueOf(z));
            if (z) {
                return;
            }
        }
        if (!AuthorizationUtils.a(BaseApplication.e())) {
            downloadCallback.onError(7);
            LogUtil.a("Bundle_Downloader", "not authorize");
            return;
        }
        GrsDownloadPluginUrl a2 = a();
        List<String> b2 = d.b(list);
        if (b2.isEmpty()) {
            downloadCallback.onCompleted();
            return;
        }
        e(b2);
        if (d(i, z, e().a(b2, -1, new e(z, i, list, downloadCallback, b2), a2), b2)) {
            return;
        }
        downloadCallback.onError(3);
        LogUtil.a("Bundle_Downloader", "updatePlugins task fail.");
    }

    private mtk e() {
        if (this.f611a == null) {
            mtk d2 = d.d((String) null);
            this.f611a = d2;
            mrx.d(new a(d2.c().getIndexSaveKey()));
        }
        return this.f611a;
    }

    private GrsDownloadPluginUrl a() {
        String str;
        boolean b2 = BundleExtension.a().b();
        if (BundleExtension.a().e()) {
            str = BundleExtension.a().d();
            String a2 = BundleExtension.a().a();
            LogUtil.c("Bundle_Downloader", "isTestAllow isUseHealthConfigCenter=", String.valueOf(b2), ", verion=", String.valueOf(a2));
            this.f611a = d.d(a2);
            this.b = true;
        } else {
            if (this.b) {
                this.f611a = d.d((String) null);
                this.b = false;
            }
            str = null;
        }
        return new c(str, b2);
    }

    private boolean d(int i, boolean z, int i2, List<String> list) {
        if (i2 <= 0) {
            return false;
        }
        synchronized (this.e) {
            this.e.put(Integer.valueOf(i), Integer.valueOf(z ? -i2 : i2));
        }
        LogUtil.c("Bundle_Downloader", "begin download taskId=", Integer.valueOf(i), ", taskNo=", Integer.valueOf(i2), ", isDeferred=", String.valueOf(z), ", plugins=", list.toString());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, boolean z, int i2) {
        int d2;
        synchronized (this.e) {
            d2 = d(i);
            if (d2 < 0 && z) {
                d2 = -d2;
                this.e.remove(Integer.valueOf(i));
            } else if (d2 <= 0 || z) {
                d2 = 0;
            } else {
                this.e.remove(Integer.valueOf(i));
            }
        }
        LogUtil.c("Bundle_Downloader", "end download taskId=", Integer.valueOf(i), ", taskNo=", Integer.valueOf(d2), ", isDeferred=", String.valueOf(z), ", result=", Integer.valueOf(i2));
    }

    private int d(int i) {
        int intValue;
        synchronized (this.e) {
            Integer num = this.e.get(Integer.valueOf(i));
            intValue = num instanceof Integer ? num.intValue() : 0;
        }
        return intValue;
    }

    private boolean e(int i) {
        boolean containsKey;
        synchronized (this.e) {
            containsKey = this.e.containsKey(Integer.valueOf(i));
        }
        return containsKey;
    }

    private void e(List<String> list) {
        new d(e(), false).c(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str, List<AppBundleDownloader.DownloadRequest> list) throws IOException {
        new d(e(), false).e(str, list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(List<String> list, List<AppBundleDownloader.DownloadRequest> list2, AppBundleDownloader.DownloadCallback downloadCallback) {
        return new d(e(), true).e(list, list2, downloadCallback);
    }

    /* loaded from: classes8.dex */
    static class a implements FilenameFilter {

        /* renamed from: a, reason: collision with root package name */
        private final String f612a;

        a(String str) {
            this.f612a = str;
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            if (str != null && str.startsWith("index_bundle_")) {
                return !str.equals(this.f612a);
            }
            return false;
        }
    }

    /* loaded from: classes8.dex */
    static class c extends GrsDownloadPluginUrl {
        private final String e;

        c(String str, boolean z) {
            super(z);
            this.e = str;
        }

        @Override // health.compact.a.GrsDownloadPluginUrl, com.huawei.haf.download.DownloadPluginUrl
        public String getDownloadPluginUrl(String str, boolean z) {
            return e(str, this.e, z);
        }
    }

    /* loaded from: classes8.dex */
    class e implements DownloadPluginCallback {

        /* renamed from: a, reason: collision with root package name */
        private boolean f614a;
        private boolean b;
        private final AppBundleDownloader.DownloadCallback d;
        private final boolean e;
        private final List<AppBundleDownloader.DownloadRequest> f;
        private final List<String> h;
        private int i = 0;
        private final int j;

        e(boolean z, int i, List<AppBundleDownloader.DownloadRequest> list, AppBundleDownloader.DownloadCallback downloadCallback, List<String> list2) {
            this.e = z;
            this.j = i;
            this.f = list;
            this.d = downloadCallback;
            this.h = list2;
        }

        @Override // com.huawei.haf.download.DownloadPluginCallback
        public boolean onDownloadPluginResult(int i, String str, int i2) {
            LogUtil.c("Bundle_Downloader", "onDownloadPluginResult taskNo=", Integer.valueOf(i), ", name=", str, ", result=", Integer.valueOf(i2));
            if (i2 != 0) {
                return false;
            }
            try {
                ccm.this.e(str, this.f);
                return false;
            } catch (IOException e) {
                LogUtil.a("Bundle_Downloader", "moveFile fail. ex=", LogUtil.a(e));
                this.b = true;
                return false;
            }
        }

        @Override // com.huawei.haf.download.DownloadPluginCallback
        public void startDownloadProgress(int i) {
            this.f614a = true;
            this.d.onStart();
        }

        @Override // com.huawei.haf.download.DownloadPluginCallback
        public void showDownloadProgress(int i, int i2, int i3) {
            if (i3 - this.i >= 5 || i3 >= 95) {
                this.d.onProgress(i2);
                this.i = i3;
            }
        }

        @Override // com.huawei.haf.download.DownloadPluginCallback
        public void onDownloadResult(int i, int i2) {
            LogUtil.c("Bundle_Downloader", "onDownloadResult taskNo=", Integer.valueOf(i), ", reason=", Integer.valueOf(i2));
            if (!this.f614a && ccm.this.c(this.h, this.f, this.d)) {
                a(0, false);
                return;
            }
            if (this.b) {
                i2 = 3;
            }
            a(i2, true);
        }

        private void a(int i, boolean z) {
            int i2;
            if (i != 0) {
                i2 = (this.e ? 100 : 0) + i;
            } else {
                i2 = i;
            }
            ccm.this.d(this.j, this.e, i2);
            if (i == 0) {
                this.d.onCompleted();
            } else if (i == 1) {
                this.d.onCanceling();
                this.d.onCanceled();
            } else {
                this.d.onError(i);
            }
            if (z) {
                OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_BUNDLE_DOWNLOAD_85070030.value(), i2);
            }
        }
    }

    /* loaded from: classes8.dex */
    static class d {
        private static boolean c = LogUtil.c();

        /* renamed from: a, reason: collision with root package name */
        private final b f613a;
        private final mtk e;

        d(mtk mtkVar, boolean z) {
            this.e = mtkVar;
            this.f613a = z ? new b(mtkVar.c().getVersion()) : null;
        }

        static mtk d(String str) {
            if (!TextUtils.isEmpty(str)) {
                c = str.indexOf(45) != -1;
            } else {
                c = LogUtil.c();
            }
            return new mtk(new ccn("com.huawei.health_bundle_two", str, c));
        }

        static List<String> b(List<AppBundleDownloader.DownloadRequest> list) {
            ArrayList arrayList = new ArrayList(list.size());
            for (AppBundleDownloader.DownloadRequest downloadRequest : list) {
                if (downloadRequest.getUrl().startsWith("config://")) {
                    b(arrayList, downloadRequest);
                }
            }
            return arrayList;
        }

        private static void b(List<String> list, AppBundleDownloader.DownloadRequest downloadRequest) {
            int fileNum = downloadRequest.getFileNum();
            if (fileNum <= 1) {
                list.add(a(downloadRequest.getModuleName()));
                return;
            }
            for (int i = 1; i <= fileNum; i++) {
                list.add(a(downloadRequest.getModuleName(), i));
            }
        }

        private static String a(String str, int i) {
            return a(str + "-split-" + i);
        }

        private static String a(String str) {
            if (!c) {
                return str;
            }
            return "Beta_" + str;
        }

        private static AppBundleDownloader.DownloadRequest a(String str, List<AppBundleDownloader.DownloadRequest> list) {
            for (AppBundleDownloader.DownloadRequest downloadRequest : list) {
                if (str.startsWith(a(downloadRequest.getModuleName()))) {
                    return downloadRequest;
                }
            }
            return null;
        }

        void e(String str, List<AppBundleDownloader.DownloadRequest> list) throws IOException {
            AppBundleDownloader.DownloadRequest a2 = a(str, list);
            if (a2 == null) {
                return;
            }
            if (str.equals(a(a2.getModuleName()))) {
                File e = e(str);
                if (e == null || !e.exists()) {
                    return;
                }
                File file = new File(a2.getFileDir(), a2.getFileName());
                try {
                    try {
                        FileUtils.d(e, file);
                        return;
                    } catch (IOException e2) {
                        FileUtils.i(file);
                        throw e2;
                    }
                } finally {
                    b(str);
                }
            }
            b(a2);
        }

        boolean e(List<String> list, List<AppBundleDownloader.DownloadRequest> list2, AppBundleDownloader.DownloadCallback downloadCallback) {
            b bVar = this.f613a;
            boolean z = false;
            if (bVar == null || !bVar.d()) {
                return false;
            }
            downloadCallback.onStart();
            long j = 0;
            for (String str : list) {
                try {
                    e(str, list2);
                } catch (IOException e) {
                    LogUtil.a("Bundle_Downloader", "moveFile fail. ex=", LogUtil.a(e));
                    z = true;
                }
                j += this.f613a.e(str);
                downloadCallback.onProgress(j);
            }
            return !z;
        }

        void c(List<String> list) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                b(it.next());
            }
        }

        void b(String str) {
            b bVar = this.f613a;
            if (bVar != null) {
                FileUtils.d(bVar.c(str));
            } else {
                FileUtils.e(new File(this.e.c().getPluginSavePath(str).toString()));
            }
        }

        private File e(String str) {
            b bVar = this.f613a;
            if (bVar != null) {
                return bVar.c(str);
            }
            return this.e.c(str);
        }

        private void b(AppBundleDownloader.DownloadRequest downloadRequest) throws IOException {
            int fileNum = downloadRequest.getFileNum();
            File[] fileArr = new File[fileNum];
            for (int i = 1; i <= fileNum; i++) {
                File e = e(a(downloadRequest.getModuleName(), i));
                if (e == null || !e.exists()) {
                    return;
                }
                fileArr[i - 1] = e;
            }
            File file = new File(downloadRequest.getFileDir(), downloadRequest.getFileName());
            try {
                try {
                    FileUtils.a(fileArr, file);
                } catch (IOException e2) {
                    FileUtils.i(file);
                    throw e2;
                }
            } finally {
                d(downloadRequest);
            }
        }

        private void d(AppBundleDownloader.DownloadRequest downloadRequest) {
            int fileNum = downloadRequest.getFileNum();
            for (int i = 1; i <= fileNum; i++) {
                b(a(downloadRequest.getModuleName(), i));
            }
        }
    }

    /* loaded from: classes8.dex */
    static class b {
        private List<msa> d;
        private final File e;

        b(String str) {
            File file = new File(BaseApplication.e().getExternalCacheDir(), "bundle-plugins");
            this.e = file;
            File file2 = new File(file, "index_plugins_" + str + ".json");
            if (file2.exists()) {
                this.d = msb.c(mrx.e(file2)).d();
                LogUtil.c("Bundle_Downloader", "DownloadPluginTestHelper indexFile=", file2.getName(), ", size=", Integer.valueOf(this.d.size()));
            }
        }

        boolean d() {
            List<msa> list = this.d;
            return (list == null || list.isEmpty()) ? false : true;
        }

        File c(String str) {
            msa d = d(str);
            String a2 = d != null ? d.a() : null;
            if (TextUtils.isEmpty(a2)) {
                return null;
            }
            File file = new File(this.e, a2);
            if (file.exists()) {
                return file;
            }
            return null;
        }

        long e(String str) {
            if (d(str) != null) {
                return r3.e();
            }
            return 0L;
        }

        private msa d(String str) {
            List<msa> list = this.d;
            if (list == null) {
                return null;
            }
            for (msa msaVar : list) {
                if (str.equals(msaVar.b())) {
                    return msaVar;
                }
            }
            return null;
        }
    }
}
