package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginachievement.gltexture.impl.AchieveMedalCallBack;
import com.huawei.pluginachievement.gltexture.util.FileUtil;
import com.huawei.pluginachievement.manager.model.MedalBasic;
import com.huawei.pluginachievement.manager.model.MedalConfigInfo;
import com.huawei.pluginachievement.manager.model.MedalLocation;
import health.compact.a.CommonUtil;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mdu {
    private static Context b;
    private static volatile mdu d;
    private meh g;
    private Map<String, Integer> j = Collections.synchronizedMap(new HashMap(2));
    private ArrayList<String> i = new ArrayList<>(0);
    private Map<String, String> h = new HashMap(0);
    private volatile boolean c = false;

    /* renamed from: a, reason: collision with root package name */
    private AtomicInteger f14901a = new AtomicInteger(0);
    private ExecutorService e = Executors.newSingleThreadExecutor();

    private mdu() {
    }

    public static mdu b(Context context) {
        if (b == null) {
            b = context.getApplicationContext();
        }
        if (d == null) {
            synchronized (mdu.class) {
                if (d == null) {
                    d = new mdu();
                }
            }
        }
        return d;
    }

    public boolean d() {
        return this.g != null;
    }

    public void b(meh mehVar) {
        if (mehVar != null) {
            this.g = mehVar;
        }
    }

    public void a() {
        LogUtil.a("PLGACHIEVE_AchieveMedalDownload", "enter downTextureRes");
        String b2 = mct.b(b, "_medalTextureDownload");
        if (TextUtils.isEmpty(mct.b(b, "medalZipDelete")) && !TextUtils.isEmpty(b2)) {
            final FileUtil fileUtil = new FileUtil(b);
            if (this.e.isShutdown()) {
                this.e = Executors.newSingleThreadExecutor();
            }
            this.e.execute(new Runnable() { // from class: mdu.3
                @Override // java.lang.Runnable
                public void run() {
                    String str;
                    try {
                        str = mdu.b.getFilesDir().getCanonicalPath() + File.separator + "achievemedal";
                    } catch (IOException unused) {
                        LogUtil.b("PLGACHIEVE_AchieveMedalDownload", "getCanonicalPath IOException");
                        str = null;
                    }
                    fileUtil.c(str);
                    mct.b(mdu.b, "medalZipDelete", "done");
                }
            });
        }
        if (1 != CommonUtil.l(b)) {
            LogUtil.a("PLGACHIEVE_AchieveMedalDownload", "getNetEnvironment is not WIFI");
            return;
        }
        if (!TextUtils.isEmpty(mct.b(b, "clearMedalResCache"))) {
            LogUtil.a("PLGACHIEVE_AchieveMedalDownload", "clearCache is done!");
        } else {
            if ("doing".equals(mct.b(b, "_medalTextureStatusDownloadDoing"))) {
                return;
            }
            mct.b(b, "_medalTextureStatusDownloadDoing", "doing");
            j();
        }
    }

    private void b() {
        LogUtil.a("PLGACHIEVE_AchieveMedalDownload", "enter download");
        FileUtil fileUtil = new FileUtil(b);
        ArrayList<String> arrayList = this.i;
        if (arrayList == null || arrayList.size() == 0) {
            f();
            LogUtil.a("PLGACHIEVE_AchieveMedalDownload", "exit download");
            return;
        }
        int size = this.i.size();
        if (this.h != null) {
            mct.b(b, "_medalTextureStatusDownloadDoing", "doing");
            LogUtil.a("PLGACHIEVE_AchieveMedalDownload", "enter download file length= ", Integer.valueOf(size));
            String str = this.i.get(size - 1);
            Iterator<String> it = this.i.iterator();
            while (it.hasNext()) {
                String next = it.next();
                a(next, this.h.get(next), str, fileUtil);
            }
            if (this.i.size() > 0) {
                i();
            }
            mmb.d(this.e);
        }
    }

    private void i() {
        this.c = false;
        final Timer timer = new Timer("PLGACHIEVE_AchieveMedalDownload");
        final int[] iArr = {0};
        timer.scheduleAtFixedRate(new TimerTask() { // from class: mdu.4
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                int[] iArr2 = iArr;
                iArr2[0] = iArr2[0] + 1;
                LogUtil.a("PLGACHIEVE_AchieveMedalDownload", "medalDownloadChecker: " + iArr[0]);
                if (mdu.this.c || ((iArr[0] >= 6 && CommonUtil.x(BaseApplication.getContext())) || iArr[0] >= 180)) {
                    if (mdu.this.e != null) {
                        LogUtil.a("PLGACHIEVE_AchieveMedalDownload", "shutdownNow executor");
                        mdu.this.e.shutdownNow();
                    }
                    timer.cancel();
                    LogUtil.a("PLGACHIEVE_AchieveMedalDownload", "medalDownloadChecker finish.");
                }
            }
        }, PreConnectManager.CONNECT_INTERNAL, PreConnectManager.CONNECT_INTERNAL);
    }

    public void a(final String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("PLGACHIEVE_AchieveMedalDownload", "medalId is null");
            return;
        }
        if (1 != CommonUtil.l(b)) {
            LogUtil.a("PLGACHIEVE_AchieveMedalDownload", "getNetEnvironment is not WIFI");
            return;
        }
        if (TextUtils.isEmpty(mct.b(b, "clearMedalResCache"))) {
            LogUtil.a("PLGACHIEVE_AchieveMedalDownload", "clearCache is null!");
            return;
        }
        if (!"doing".equals(mct.b(b, "_medalTextureStatusDownloadDoing"))) {
            mct.b(b, "_medalTextureStatusDownloadDoing", "doing");
            final FileUtil fileUtil = new FileUtil(b);
            final meh c = meh.c(BaseApplication.getContext());
            if (this.e.isShutdown()) {
                this.e = Executors.newSingleThreadExecutor();
            }
            this.e.execute(new Runnable() { // from class: mdu.2
                @Override // java.lang.Runnable
                public void run() {
                    HashMap hashMap = new HashMap(2);
                    hashMap.put("medalID", str);
                    mcz d2 = c.d(7, hashMap);
                    if (!(d2 instanceof MedalBasic)) {
                        mdu.this.f();
                    } else {
                        mdu.this.b(str, ((MedalBasic) d2).acquireVeinUrl(), fileUtil);
                    }
                }
            });
            mmb.d(this.e);
            return;
        }
        LogUtil.a("PLGACHIEVE_AchieveMedalDownload", "DOING_DOWNLOAD");
    }

    private void a(final String str, final String str2, final String str3, final FileUtil fileUtil) {
        if (this.e.isShutdown()) {
            this.e = Executors.newSingleThreadExecutor();
        }
        this.e.execute(new Runnable() { // from class: mdu.5
            @Override // java.lang.Runnable
            public void run() {
                if (1 == CommonUtil.l(mdu.b)) {
                    if (mdu.this.j.get(str) != null && ((Integer) mdu.this.j.get(str)).intValue() != 0) {
                        if (str.equals(str3)) {
                            mct.b(mdu.b, "_medalTextureStatusDownloadDoing", "done");
                            mdu.this.c = true;
                            return;
                        }
                        return;
                    }
                    fileUtil.a(str2, str, new AchieveMedalCallBack() { // from class: mdu.5.4
                        @Override // com.huawei.pluginachievement.gltexture.impl.AchieveMedalCallBack
                        public void onResponse(int i) {
                            LogUtil.a("PLGACHIEVE_AchieveMedalDownload", "download resCode", Integer.valueOf(i), str);
                            if (i == 0) {
                                if (mdu.this.j.get(str) != null) {
                                    LogUtil.c("PLGACHIEVE_AchieveMedalDownload", "medalName download success!");
                                    mdu.this.e(str);
                                }
                            } else {
                                LogUtil.h("PLGACHIEVE_AchieveMedalDownload", "DownMedals fail! ", str);
                                mdu.this.f14901a.incrementAndGet();
                            }
                            if (str.equals(str3) || mdu.this.f14901a.get() >= 6) {
                                LogUtil.a("PLGACHIEVE_AchieveMedalDownload", "download finish or failures reaches the upper limit. failCount = " + mdu.this.f14901a.get());
                                mct.b(mdu.b, "_medalTextureStatusDownloadDoing", "done");
                                mdu.this.c = true;
                            }
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final String str, final String str2, final FileUtil fileUtil) {
        if (this.e.isShutdown()) {
            this.e = Executors.newSingleThreadExecutor();
        }
        try {
            this.e.execute(new Runnable() { // from class: mdu.1
                @Override // java.lang.Runnable
                public void run() {
                    if (1 == CommonUtil.l(mdu.b)) {
                        fileUtil.a(str2, str, new AchieveMedalCallBack() { // from class: mdu.1.3
                            @Override // com.huawei.pluginachievement.gltexture.impl.AchieveMedalCallBack
                            public void onResponse(int i) {
                                LogUtil.a("PLGACHIEVE_AchieveMedalDownload", "download resCode", Integer.valueOf(i), str);
                                if (i == 0) {
                                    if (mdu.this.j.get(str) != null) {
                                        LogUtil.c("PLGACHIEVE_AchieveMedalDownload", "medalName download success!");
                                        mdu.this.e(str);
                                    }
                                    mdu.this.f14901a.set(0);
                                }
                                mct.b(mdu.b, "_medalTextureStatusDownloadDoing", "done");
                            }
                        });
                    }
                }
            });
        } catch (RejectedExecutionException unused) {
            LogUtil.b("PLGACHIEVE_AchieveMedalDownload", "downloadSingleByMedalId RejectedExecutionException.");
        }
    }

    private void j() {
        LogUtil.a("PLGACHIEVE_AchieveMedalDownload", "enter getMedalData");
        final meh c = meh.c(BaseApplication.getContext());
        ArrayList<String> arrayList = this.i;
        if (arrayList != null && arrayList.size() != 0) {
            this.i.clear();
        }
        if (this.e.isShutdown()) {
            this.e = Executors.newSingleThreadExecutor();
        }
        this.e.execute(new Runnable() { // from class: mdu.9
            @Override // java.lang.Runnable
            public void run() {
                HashMap hashMap = new HashMap(2);
                List<mcz> b2 = c.b(7, hashMap);
                if (b2 == null) {
                    LogUtil.a("PLGACHIEVE_AchieveMedalDownload", "getData data is null ");
                    mdu.this.f();
                    return;
                }
                int size = b2.size();
                if (size != 0 && size < 19) {
                    LogUtil.a("PLGACHIEVE_AchieveMedalDownload", "get medal");
                    mct.b(mdu.b, "medalIsException", "done");
                    mfg.b(mdu.b);
                    HashMap hashMap2 = new HashMap(2);
                    hashMap2.put("countryCode", LoginInit.getInstance(mdu.b).getAccountInfo(1010));
                    c.a(8, hashMap2);
                }
                LogUtil.a("PLGACHIEVE_AchieveMedalDownload", "getData data size is", Integer.valueOf(size));
                ArrayList e = mdu.this.e(c.b(8, hashMap), c.b(9, hashMap));
                Iterator<mcz> it = b2.iterator();
                while (it.hasNext()) {
                    mdu.this.d((MedalBasic) it.next(), e);
                }
                mdu.this.m();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        String b2 = mct.b(b, "_medalTextureDownload");
        if (TextUtils.isEmpty(b2)) {
            g();
        } else {
            d(b2);
            if (c()) {
                f();
                LogUtil.a("PLGACHIEVE_AchieveMedalDownload", "allMedalTexture is Download!");
                return;
            }
        }
        synchronized (this) {
            b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<String> e(List<mcz> list, List<mcz> list2) {
        ArrayList<String> arrayList = new ArrayList<>(0);
        if (list == null || list2 == null) {
            LogUtil.a("PLGACHIEVE_AchieveMedalDownload", "medalLocation or medalConfigInfoList is null");
            return arrayList;
        }
        for (mcz mczVar : list) {
            if (mczVar instanceof MedalLocation) {
                MedalLocation medalLocation = (MedalLocation) mczVar;
                if (medalLocation.acquireGainedCount() > 0 && !mlb.a().contains(medalLocation.acquireMedalID())) {
                    arrayList.add(medalLocation.acquireMedalID());
                }
            }
        }
        ArrayList<String> a2 = mdw.a(list2, arrayList);
        for (mcz mczVar2 : list2) {
            if (mczVar2 instanceof MedalConfigInfo) {
                MedalConfigInfo medalConfigInfo = (MedalConfigInfo) mczVar2;
                String acquireMedalType = medalConfigInfo.acquireMedalType();
                String acquireMedalID = medalConfigInfo.acquireMedalID();
                if (!TextUtils.isEmpty(acquireMedalType) && !arrayList.contains(acquireMedalID)) {
                    if (mlb.i(acquireMedalType)) {
                        if (mlb.d(medalConfigInfo.acquireEndTime(), 0) && !mlb.a().contains(medalConfigInfo.acquireMedalID())) {
                            arrayList.add(medalConfigInfo.acquireMedalID());
                        }
                    } else if (!mlb.a().contains(medalConfigInfo.acquireMedalID()) && a2.contains(acquireMedalID)) {
                        arrayList.add(medalConfigInfo.acquireMedalID());
                    } else {
                        LogUtil.h("PLGACHIEVE_AchieveMedalDownload", "isOversea(),19 medalID don't need to download 3d.");
                    }
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(MedalBasic medalBasic, ArrayList<String> arrayList) {
        String acquireMedalID = medalBasic.acquireMedalID();
        if (arrayList.contains(acquireMedalID)) {
            String acquireVeinUrl = medalBasic.acquireVeinUrl();
            ArrayList<String> arrayList2 = this.i;
            if (arrayList2 != null) {
                arrayList2.add(acquireMedalID);
            } else {
                ArrayList<String> arrayList3 = new ArrayList<>(2);
                this.i = arrayList3;
                arrayList3.add(acquireMedalID);
            }
            this.h.put(acquireMedalID, acquireVeinUrl);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        this.j.put(str, 1);
        h();
    }

    private void g() {
        LogUtil.c("PLGACHIEVE_AchieveMedalDownload", "enter initTextureMap");
        ArrayList<String> arrayList = this.i;
        if (arrayList == null) {
            return;
        }
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            this.j.put(it.next(), 0);
        }
    }

    private void d(String str) {
        LogUtil.a("PLGACHIEVE_AchieveMedalDownload", "enter parseJsonData");
        if (str == null) {
            return;
        }
        Map<String, Integer> map = this.j;
        if (map != null) {
            map.clear();
        } else {
            this.j = new HashMap(2);
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            LogUtil.a("PLGACHIEVE_AchieveMedalDownload", "medalIdList length is", Integer.valueOf(this.i.size()));
            Iterator<String> it = this.i.iterator();
            while (it.hasNext()) {
                String next = it.next();
                this.j.put(next, Integer.valueOf(meb.c(next, jSONObject)));
            }
        } catch (JSONException unused) {
            LogUtil.b("PLGACHIEVE_AchieveMedalDownload", "parseJsonData Exception");
        }
    }

    private void h() {
        mct.b(b, "_medalTextureDownload", new JSONObject(this.j).toString());
    }

    private boolean c() {
        LogUtil.c("PLGACHIEVE_AchieveMedalDownload", "enter allMedalTextureIsDownload  ", this.i.toString());
        Iterator<String> it = this.i.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (this.j.get(next) == null || 1 != this.j.get(next).intValue()) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        mct.b(b, "_medalTextureStatusDownloadDoing", "done");
    }
}
