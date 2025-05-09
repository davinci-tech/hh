package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.google.gson.Gson;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.sportservice.download.listener.AudioResDownloadInterface;
import com.huawei.health.sportservice.download.listener.ResDownloadCallback;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$string;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class fgq implements AudioResDownloadInterface {
    private fgy b;
    private String c;
    private fhj d;
    private fhj e;
    private String f;
    private String g;
    private CourseApi h;
    private ResDownloadCallback k;
    private fgy l;

    /* renamed from: a, reason: collision with root package name */
    private fgn f12498a = new fgn();
    private HashMap<String, Boolean> o = new HashMap<>();
    private int n = 0;
    private int i = 0;
    private boolean j = true;
    private List<String> m = new ArrayList();

    @Override // com.huawei.health.sportservice.download.listener.AudioResDownloadInterface
    public void downAudioResource(Context context, String str, String str2, String str3, ResDownloadCallback resDownloadCallback) {
        this.k = resDownloadCallback;
        a(context, str, str2, str3);
    }

    public void a(final Context context, final String str, String str2, final String str3) {
        this.f = str3;
        e(str, str2, str3);
        LogUtil.a("AudioResourceDownloadUtil", "mBusinessResourceDataKey= ", this.g, "mBaseResourceDataKey= ", this.c);
        ThreadPoolManager.d().execute(new Runnable() { // from class: fgu
            @Override // java.lang.Runnable
            public final void run() {
                fgq.this.a(str, context, str3);
            }
        });
    }

    /* synthetic */ void a(String str, Context context, final String str2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(c(str));
        fgz.a().e(context, arrayList, new UiCallback<List<fgy>>() { // from class: fgq.3
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str3) {
                LogUtil.h("AudioResourceDownloadUtil", "onFailure errorCode ", Integer.valueOf(i), " errorInfo= ", str3);
                fgq.this.o.put("commonAudioDownloadState", false);
                fgq.this.o.put("audioBusinessCompleteDownloadState", false);
                fgq.this.k.onQueryCloudResult(fgq.this.o, fgq.this.j, 0L);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List<fgy> list) {
                long j;
                LogUtil.a("AudioResourceDownloadUtil", "onSuccess = ", str2);
                fgq fgqVar = fgq.this;
                fgqVar.l = fgqVar.d(list, fgqVar.c, "Common");
                fgq fgqVar2 = fgq.this;
                fgqVar2.b = fgqVar2.d(list, fgqVar2.g, str2);
                if (fgq.this.b != null) {
                    j = fgq.this.b.d();
                    fgq.this.o.put("audioBusinessCompleteDownloadState", true);
                } else {
                    fgq.this.o.put("audioBusinessCompleteDownloadState", false);
                    j = 0;
                }
                if (fgq.this.b != null && fgq.this.l != null) {
                    j += fgq.this.l.d();
                    fgq.this.o.put("commonAudioDownloadState", true);
                } else {
                    fgq.this.o.put("commonAudioDownloadState", false);
                }
                fgq.this.k.onQueryCloudResult(fgq.this.o, fgq.this.j, j);
            }
        });
    }

    private fha c(String str) {
        fha fhaVar = new fha();
        fhaVar.d(str);
        ArrayList arrayList = new ArrayList();
        int i = CommonUtil.bv() ? 1004 : 4;
        for (int i2 = CommonUtil.bv() ? 1001 : 1; i2 <= i; i2++) {
            arrayList.add(Integer.valueOf(i2));
        }
        fhaVar.a(arrayList);
        LogUtil.a("AudioResourceDownloadUtil", "requestInfo: == ", new Gson().toJson(fhaVar));
        return fhaVar;
    }

    @Override // com.huawei.health.sportservice.download.listener.AudioResDownloadInterface
    public void startDownload() {
        this.e = new fhj(this.l);
        fgy fgyVar = this.l;
        if (fgyVar != null) {
            String c = c("Common", fgyVar, fgyVar.a());
            this.n += 2;
            e(c, "commonAudioDownloadState", this.l.a(), this.e, 0);
            fgy fgyVar2 = this.l;
            e(c("Common", fgyVar2, fgyVar2.c()), "Common_Json", this.l.c(), this.e, 1);
        } else {
            LogUtil.a("AudioResourceDownloadUtil", "mVoiceBroadcastInfo = null");
        }
        this.d = new fhj(this.b);
        fgy fgyVar3 = this.b;
        if (fgyVar3 != null) {
            String c2 = c(this.f, fgyVar3, fgyVar3.a());
            this.n += 2;
            e(c2, "audioBusinessCompleteDownloadState", this.b.a(), this.d, 0);
            String str = this.f;
            fgy fgyVar4 = this.b;
            e(c(str, fgyVar4, fgyVar4.c()), "Business_json", this.b.c(), this.d, 1);
        } else {
            LogUtil.a("AudioResourceDownloadUtil", "mBusinessInfo = null");
        }
        if (this.b == null && this.l == null) {
            this.k.onInstallSuccess("audioBusinessCompleteDownloadState");
        }
    }

    private void e(final String str, final String str2, String str3, final fhj fhjVar, final int i) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("AudioResourceDownloadUtil", "filePath is null");
            c();
        } else {
            CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
            this.h = courseApi;
            courseApi.downloadFile(str3, str, new UiCallback() { // from class: fgq.2
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i2, String str4) {
                    LogUtil.h("AudioResourceDownloadUtil", "downloadFile onFailure= ", Integer.valueOf(i2), " errorInfo= ", str4);
                    fgq.this.k.onDownloadFail(i2, str4);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onSuccess(Object obj) {
                    LogUtil.h("AudioResourceDownloadUtil", "downloadFile onSuccess ", str2);
                    fgq.this.k.onCompleteDownload(str2);
                    fgq.this.a(str, str2, fhjVar, i);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onProgress(long j, long j2) {
                    super.onProgress(j, j2);
                    fgq.this.k.onProgress((int) ((j * 100) / j2), str2);
                }
            }, true);
        }
    }

    public View.OnClickListener axz_() {
        return new View.OnClickListener() { // from class: fgs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                fgq.this.axA_(view);
            }
        };
    }

    /* synthetic */ void axA_(View view) {
        if (this.h != null) {
            LogUtil.a("AudioResourceDownloadUtil", "cancel download");
            fgy fgyVar = this.l;
            if (fgyVar != null) {
                this.h.cancelDownloadCourseMediaFiles(fgyVar.a());
                this.h.cancelDownloadCourseMediaFiles(this.l.c());
            }
            fgy fgyVar2 = this.b;
            if (fgyVar2 != null) {
                this.h.cancelDownloadCourseMediaFiles(fgyVar2.a());
                this.h.cancelDownloadCourseMediaFiles(this.b.c());
            }
        } else {
            LogUtil.h("AudioResourceDownloadUtil", "mCourseApi is null");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2, fhj fhjVar, int i) {
        String str3 = ".zip";
        String str4 = null;
        if (str.contains(".zip")) {
            this.m.add(str);
        } else {
            str3 = null;
        }
        if (str.contains(".json")) {
            this.i++;
            fhjVar.a(str);
            LogUtil.a("AudioResourceDownloadUtil", "json download success");
            e(str2);
            return;
        }
        if (str3 == null) {
            LogUtil.h("AudioResourceDownloadUtil", "fileFormat is null");
            c();
            return;
        }
        int lastIndexOf = str.lastIndexOf(str3);
        if (lastIndexOf > 0 && lastIndexOf < str.length()) {
            str4 = str.substring(0, lastIndexOf);
        }
        if (TextUtils.isEmpty(str4)) {
            LogUtil.h("AudioResourceDownloadUtil", "downloadFile: unZipFile isEmpty");
            str4 = "";
        }
        if (msp.c(str, str4) > -1) {
            this.i++;
            this.k.onCompleteDownload(str2);
            LogUtil.a("AudioResourceDownloadUtil", "downloadFile: unzip success ", this.g);
            if (i == 0) {
                fhjVar.c(str4);
            }
            e(str2);
            return;
        }
        c();
        LogUtil.h("AudioResourceDownloadUtil", "downloadFile: unZipFile error");
    }

    private String c(String str, fgy fgyVar, String str2) {
        String d = drd.d(str, fgyVar.e() + "_" + fgyVar.f(), str2);
        LogUtil.h("AudioResourceDownloadUtil", "filePath= ", d);
        return d;
    }

    private void e(String str) {
        int e;
        int b;
        LogUtil.a("AudioResourceDownloadUtil", "mCurrentDownloadSuccessTimes = ", Integer.valueOf(this.i), " mTotalDownloads= ", Integer.valueOf(this.n));
        if (this.i < this.n) {
            return;
        }
        if (this.l == null) {
            e = this.f12498a.d(this.c);
            b = this.f12498a.a(this.c);
        } else {
            e = this.e.e();
            b = this.e.b();
        }
        e(this.d, this.e, str, e, b);
    }

    private void e(fhj fhjVar, fhj fhjVar2, String str, int i, int i2) {
        if (fhjVar2 == null || fhjVar == null) {
            LogUtil.h("AudioResourceDownloadUtil", "baseFileInfo == null || businessResourceInfo == null");
            return;
        }
        String d = fhjVar.d();
        if (TextUtils.isEmpty(d)) {
            LogUtil.h("AudioResourceDownloadUtil", "businessResourcePath is null");
            return;
        }
        if (!new File(d).exists()) {
            LogUtil.h("AudioResourceDownloadUtil", "businessResourcePath file not exists");
            c();
            return;
        }
        String a2 = msb.a(new File(d + File.separator + "scenario.json"));
        if (TextUtils.isEmpty(a2)) {
            LogUtil.h("AudioResourceDownloadUtil", "scenarioInfo is null");
            c();
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(a2);
            if (!jSONObject.has("supportService")) {
                LogUtil.h("AudioResourceDownloadUtil", "BUSINESS_RESOURCE_SUPPORT_SERVICE_KEY is not exists");
                c();
                return;
            }
            String string = jSONObject.getString("supportService");
            LogUtil.a("AudioResourceDownloadUtil", "supportVersion = ", string);
            int c = fgt.c(string, "\\.", 0);
            int c2 = fgt.c(string, "\\.", 1);
            LogUtil.a("AudioResourceDownloadUtil", "supportServiceVersion = ", Integer.valueOf(c), " And= ", Integer.valueOf(c2));
            if (c > i) {
                LogUtil.h("AudioResourceDownloadUtil", "fail! baseFileInfo ServiceVersion = ", Integer.valueOf(i));
                c();
                return;
            }
            LogUtil.a("AudioResourceDownloadUtil", " baseFileInfo ServiceVersion = ", Integer.valueOf(i), " res ServiceVersion", Integer.valueOf(i2));
            if (c > 0 && c >= i && c2 >= i2) {
                e();
                if (this.l == null) {
                    b(fhjVar, this.g);
                } else {
                    b(fhjVar, this.g);
                    b(fhjVar2, this.c);
                }
                this.k.onInstallSuccess(str);
                d();
                LogUtil.a("AudioResourceDownloadUtil", "save");
                if (d("duyin.dat")) {
                    b("duyin.dat");
                    b("duyin_es.dat");
                    b("duyin_pl.dat");
                    b("i18n.dat");
                    return;
                }
                return;
            }
            c();
            LogUtil.h("AudioResourceDownloadUtil", "version nonmatching");
        } catch (JSONException e) {
            LogUtil.h("AudioResourceDownloadUtil", "JSONException: ", LogAnonymous.b((Throwable) e));
            c();
        }
    }

    private void e() {
        fgy fgyVar = this.l;
        if (fgyVar == null) {
            fgyVar = this.b;
        }
        String f = fgyVar.f();
        String c = mxb.c();
        mxb.a().d(c, f);
        LogUtil.a("AudioResourceDownloadUtil", "saveSpeaker is ", f);
        e(c, f, this.f);
    }

    private void e(String str, String str2, String str3) {
        this.g = fgt.d(str, str2, str3);
        this.c = fgt.d(str, str2, "Common");
    }

    private void d() {
        for (String str : this.m) {
            if (!TextUtils.isEmpty(str)) {
                LogUtil.a("AudioResourceDownloadUtil", "delete result is ", Boolean.valueOf(FileUtils.i(new File(str))));
            }
        }
    }

    private void b(fhj fhjVar, String str) {
        this.f12498a.d(str, fhjVar.d());
        LogUtil.c("AudioResourceDownloadUtil", "mBusinessResourceDataKey=", str, " path=", fhjVar.c());
        if (fhjVar.c() != null) {
            this.f12498a.b(str, fhjVar.c());
        } else {
            LogUtil.h("AudioResourceDownloadUtil", "json file path is null", str);
        }
        this.f12498a.d(str, fhjVar.e());
        this.f12498a.a(str, fhjVar.b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public fgy d(List<fgy> list, String str, String str2) {
        int d = this.f12498a.d(str);
        int a2 = this.f12498a.a(str);
        if (d > 0 || a2 > 0) {
            this.j = false;
        }
        LogUtil.a("AudioResourceDownloadUtil", "localServiceVersion = ", Integer.valueOf(d), " localResourceVersion= ", Integer.valueOf(a2));
        int i = CommonUtil.bv() ? 1004 : 4;
        int i2 = CommonUtil.bv() ? 1000 : 0;
        fgy fgyVar = null;
        for (fgy fgyVar2 : list) {
            LogUtil.a("AudioResourceDownloadUtil", "voiceBroadcastInfo.getModuleName() = ", fgyVar2.b());
            int c = fgt.c(fgyVar2.g(), "\\.", 0);
            if (c <= i && c >= i2 && fgyVar2.b().equals(str2)) {
                fgyVar = fgyVar2;
            }
        }
        if (fgyVar == null) {
            return null;
        }
        int c2 = fgt.c(fgyVar.g(), "\\.", 0);
        int c3 = fgt.c(fgyVar.g(), "\\.", 1);
        LogUtil.a("AudioResourceDownloadUtil", "cloudServiceVersion = ", Integer.valueOf(c2), " cloudResourceVersion= ", Integer.valueOf(c3));
        if (c2 > i || c3 < i2) {
            LogUtil.h("AudioResourceDownloadUtil", "current version not supported ", Integer.valueOf(i), "localAudioServiceMinVersion");
            return null;
        }
        if (c2 > d) {
            return fgyVar;
        }
        if (c2 != d || c3 <= a2) {
            return null;
        }
        return fgyVar;
    }

    private boolean d(String str) {
        try {
            for (String str2 : BaseApplication.getContext().getAssets().list("")) {
                if (str2.equals(str.trim())) {
                    return true;
                }
            }
            return false;
        } catch (IOException unused) {
            LogUtil.h("AudioResourceDownloadUtil", "IOException fileName = ", str);
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00d6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:62:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00c9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v2, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r7v3, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r9v0, types: [java.io.FileOutputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void b(java.lang.String r13) {
        /*
            Method dump skipped, instructions count: 226
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.fgq.b(java.lang.String):void");
    }

    private void c() {
        this.k.onDownloadFail(-1, BaseApplication.getContext().getString(R$string.IDS_update_download_failed));
    }
}
