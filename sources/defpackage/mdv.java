package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.gltexture.impl.AchieveMedalCallBack;
import com.huawei.pluginachievement.gltexture.util.FileUtil;
import com.huawei.pluginachievement.manager.model.MedalConfigInfo;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import health.compact.a.CommonUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mdv {

    /* renamed from: a, reason: collision with root package name */
    private static volatile Context f14910a;
    private static volatile mdv c;
    private int j;
    private meh o;
    private static final Object e = new Object();
    private static final Object d = new Object();
    private ConcurrentHashMap<String, ConcurrentHashMap<String, Integer>> b = new ConcurrentHashMap<>(0);
    private ArrayList<String> f = new ArrayList<>(8);
    private ArrayList<String> n = new ArrayList<>(8);
    private ArrayList<String> k = new ArrayList<>(8);
    private Map<String, Map<String, String>> m = new HashMap(0);
    private volatile boolean i = false;
    private AtomicInteger g = new AtomicInteger(0);
    private AtomicBoolean l = new AtomicBoolean(false);
    private ExecutorService h = Executors.newSingleThreadExecutor();

    private mdv() {
    }

    public static mdv a(Context context) {
        if (f14910a == null && context != null) {
            f14910a = context.getApplicationContext();
        }
        if (c == null) {
            synchronized (mdv.class) {
                if (c == null) {
                    c = new mdv();
                }
            }
        }
        return c;
    }

    public boolean c() {
        return this.o != null;
    }

    public void b(meh mehVar) {
        if (mehVar != null) {
            this.o = mehVar;
        }
    }

    public void b() {
        ReleaseLogUtil.e("PLGACHIEVE_AchieveMedalPngDownload", "enter DownMedalPngRes");
        String b = mct.b(f14910a, "_medalPngStatusDownloadDoing");
        synchronized (d) {
            if (!"doing".equals(b)) {
                mct.b(f14910a, "_medalPngStatusDownloadDoing", "doing");
                if (j()) {
                    ReleaseLogUtil.d("PLGACHIEVE_AchieveMedalPngDownload", "DownMedals last time not finish,return!");
                    return;
                }
                h();
            }
        }
    }

    private boolean j() {
        int size = this.f.size();
        ReleaseLogUtil.e("PLGACHIEVE_AchieveMedalPngDownload", "Enter DownMedals haveDownMedalSize = ", Integer.valueOf(size), " mCacheCount ", Integer.valueOf(this.j));
        if (size == 0) {
            this.j = 0;
            return false;
        }
        if (size == this.j) {
            return false;
        }
        this.j = size;
        return true;
    }

    private void h() {
        ReleaseLogUtil.e("PLGACHIEVE_AchieveMedalPngDownload", "enter getData");
        final String b = mct.b(f14910a, "_medalPngStatusDownload");
        final meh c2 = meh.c(BaseApplication.getContext());
        if (this.h.isShutdown()) {
            this.h = Executors.newSingleThreadExecutor();
        }
        this.h.execute(new Runnable() { // from class: mdv.5
            @Override // java.lang.Runnable
            public void run() {
                Iterator<mcz> it;
                ArrayList<String> arrayList;
                List<mcz> b2 = c2.b(9, new HashMap(2));
                ArrayList<String> l = c2.l();
                if (b2 == null) {
                    ReleaseLogUtil.e("PLGACHIEVE_AchieveMedalPngDownload", "getData data is null ");
                    mdv.this.f();
                    return;
                }
                boolean o = Utils.o();
                Iterator<mcz> it2 = b2.iterator();
                String str = "";
                while (it2.hasNext()) {
                    mcz next = it2.next();
                    if (next instanceof MedalConfigInfo) {
                        MedalConfigInfo medalConfigInfo = (MedalConfigInfo) next;
                        String acquireMedalID = medalConfigInfo.acquireMedalID();
                        if (!o || !mlb.c().contains(acquireMedalID)) {
                            if (mdv.this.b(medalConfigInfo, l)) {
                                it = it2;
                                arrayList = l;
                                LogUtil.c("PLGACHIEVE_AchieveMedalPngDownload", "isOperationMedalOverTimeAndNotGain medalId=", acquireMedalID, " type=", medalConfigInfo.acquireMedalType(), " name=", medalConfigInfo.acquireMedalName(), " endTime=", mfn.c(BaseApplication.getContext(), mht.a(medalConfigInfo.acquireEndTime())));
                            } else {
                                it = it2;
                                arrayList = l;
                                if (!TextUtils.isEmpty(medalConfigInfo.acquireShareImageUrl())) {
                                    str = medalConfigInfo.acquireShareImageUrl();
                                }
                                mdv.this.a(medalConfigInfo, str);
                                String str2 = b;
                                if (str2 == null || "".equals(str2)) {
                                    mdv.this.e(medalConfigInfo);
                                }
                            }
                            l = arrayList;
                            it2 = it;
                        }
                    }
                    it = it2;
                    arrayList = l;
                    l = arrayList;
                    it2 = it;
                }
                mdv.this.k();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(MedalConfigInfo medalConfigInfo, ArrayList<String> arrayList) {
        if (medalConfigInfo == null) {
            return false;
        }
        String acquireMedalID = medalConfigInfo.acquireMedalID();
        if (TextUtils.isEmpty(medalConfigInfo.acquireMedalType()) || TextUtils.isEmpty(acquireMedalID)) {
            return false;
        }
        return !mlb.d(medalConfigInfo.acquireEndTime(), arrayList.contains(acquireMedalID) ? 1 : 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        ReleaseLogUtil.e("PLGACHIEVE_AchieveMedalPngDownload", "enter startResDownload...");
        e(mct.b(f14910a, "_medalPngStatusDownload"));
        if (e()) {
            ReleaseLogUtil.e("PLGACHIEVE_AchieveMedalPngDownload", "allMedalTextureIsDownload success");
            f();
        } else {
            synchronized (this) {
                g();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(MedalConfigInfo medalConfigInfo, String str) {
        synchronized (d) {
            HashMap hashMap = new HashMap(5);
            hashMap.put(ParsedFieldTag.GRAY_LIST_STYLE, medalConfigInfo.acquireGrayListStyle());
            hashMap.put(ParsedFieldTag.LIGHT_LIST_STYLE, medalConfigInfo.acquireLightListStyle());
            hashMap.put(ParsedFieldTag.GRAY_DETAIL_STYLE, medalConfigInfo.acquireGrayDetailStyle());
            hashMap.put(ParsedFieldTag.LIGHT_DETAIL_STYLE, medalConfigInfo.acquireLightDetailStyle());
            if (TextUtils.isEmpty(medalConfigInfo.acquireShareImageUrl())) {
                hashMap.put(ParsedFieldTag.SHARE_IMAGE_URL, str);
                ReleaseLogUtil.d("PLGACHIEVE_AchieveMedalPngDownload", "initUrlData shareUrl isEmpty! id ", medalConfigInfo.acquireMedalID());
            } else {
                hashMap.put(ParsedFieldTag.SHARE_IMAGE_URL, medalConfigInfo.acquireShareImageUrl());
            }
            String acquireMedalID = medalConfigInfo.acquireMedalID();
            ArrayList<String> arrayList = this.k;
            if (arrayList != null) {
                arrayList.add(acquireMedalID);
            } else {
                ArrayList<String> arrayList2 = new ArrayList<>(2);
                this.k = arrayList2;
                arrayList2.add(acquireMedalID);
            }
            e(medalConfigInfo);
            LogUtil.c("PLGACHIEVE_AchieveMedalPngDownload", "enter initUrlData=", hashMap.toString());
            this.m.put(acquireMedalID, hashMap);
        }
    }

    private void b(String str) {
        if (str == null) {
            return;
        }
        String b = meb.b(str, true);
        String b2 = meb.b(str, false);
        if (TextUtils.isEmpty(b) || TextUtils.isEmpty(b2)) {
            return;
        }
        LogUtil.c("PLGACHIEVE_AchieveMedalPngDownload", "enter refreshStatus medalUrlType=", b2, " fileName=", str);
        ConcurrentHashMap<String, Integer> concurrentHashMap = this.b.get(b);
        if (concurrentHashMap != null) {
            if (concurrentHashMap.get(b2) != null) {
                concurrentHashMap.put(b2, 1);
            }
        } else {
            concurrentHashMap = new ConcurrentHashMap<>(2);
            concurrentHashMap.put(b2, 1);
        }
        this.b.put(b, concurrentHashMap);
        c(str);
    }

    private void c(String str) {
        if (this.f.size() == this.n.size()) {
            ReleaseLogUtil.e("PLGACHIEVE_AchieveMedalPngDownload", "DownMedals finish.mHaveDownMedals.size = ", Integer.valueOf(this.f.size()), "needDownMedals total = ", Integer.valueOf(this.n.size()), " lastOne=", str);
            i();
            f();
        } else if (this.n.size() < 100) {
            ReleaseLogUtil.e("PLGACHIEVE_AchieveMedalPngDownload", "DownMedals less ", str, " refresh size ", Integer.valueOf(this.f.size()));
            i();
        } else if (this.f.size() % 50 == 0 || this.f.size() <= 5) {
            ReleaseLogUtil.e("PLGACHIEVE_AchieveMedalPngDownload", "DownMedals large refresh sp", Integer.valueOf(this.f.size()));
            i();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(MedalConfigInfo medalConfigInfo) {
        ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>(5);
        concurrentHashMap.put(ParsedFieldTag.GRAY_LIST_STYLE, 0);
        concurrentHashMap.put(ParsedFieldTag.LIGHT_LIST_STYLE, 0);
        concurrentHashMap.put(ParsedFieldTag.GRAY_DETAIL_STYLE, 0);
        concurrentHashMap.put(ParsedFieldTag.LIGHT_DETAIL_STYLE, 0);
        concurrentHashMap.put(ParsedFieldTag.SHARE_IMAGE_URL, 0);
        this.b.put(medalConfigInfo.acquireMedalID(), concurrentHashMap);
    }

    private void e(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ConcurrentHashMap<String, ConcurrentHashMap<String, Integer>> concurrentHashMap = this.b;
        if (concurrentHashMap != null) {
            concurrentHashMap.clear();
        } else {
            this.b = new ConcurrentHashMap<>(8);
        }
        synchronized (d) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                Iterator<String> it = this.k.iterator();
                while (it.hasNext()) {
                    String next = it.next();
                    JSONObject e2 = e(next, jSONObject);
                    if (e2 != null) {
                        ConcurrentHashMap<String, Integer> concurrentHashMap2 = new ConcurrentHashMap<>(5);
                        concurrentHashMap2.put(ParsedFieldTag.GRAY_LIST_STYLE, Integer.valueOf(meb.c(ParsedFieldTag.GRAY_LIST_STYLE, e2)));
                        concurrentHashMap2.put(ParsedFieldTag.LIGHT_LIST_STYLE, Integer.valueOf(meb.c(ParsedFieldTag.LIGHT_LIST_STYLE, e2)));
                        concurrentHashMap2.put(ParsedFieldTag.GRAY_DETAIL_STYLE, Integer.valueOf(meb.c(ParsedFieldTag.GRAY_DETAIL_STYLE, e2)));
                        concurrentHashMap2.put(ParsedFieldTag.LIGHT_DETAIL_STYLE, Integer.valueOf(meb.c(ParsedFieldTag.LIGHT_DETAIL_STYLE, e2)));
                        concurrentHashMap2.put(ParsedFieldTag.SHARE_IMAGE_URL, Integer.valueOf(meb.c(ParsedFieldTag.SHARE_IMAGE_URL, e2)));
                        this.b.put(next, concurrentHashMap2);
                    }
                }
            } catch (JSONException unused) {
                ReleaseLogUtil.c("PLGACHIEVE_AchieveMedalPngDownload", "parseJsonData Exception");
            }
        }
    }

    private static JSONObject e(String str, JSONObject jSONObject) {
        if (jSONObject == null || str == null) {
            LogUtil.c("PLGACHIEVE_AchieveMedalPngDownload", "jsonObject is null");
            return null;
        }
        if (!jSONObject.has(str)) {
            return null;
        }
        try {
            return jSONObject.getJSONObject(str);
        } catch (JSONException unused) {
            LogUtil.b("PLGACHIEVE_AchieveMedalPngDownload", "parseJsonObject Exception!!!");
            return null;
        }
    }

    private void i() {
        if (this.l.compareAndSet(false, true)) {
            try {
                mct.b(f14910a, "_medalPngStatusDownload", new JSONObject(this.b).toString());
            } catch (ConcurrentModificationException unused) {
                ReleaseLogUtil.c("PLGACHIEVE_AchieveMedalPngDownload", "refreshSharePreference, ConcurrentModificationException.");
            }
            this.l.set(false);
        }
    }

    private boolean e() {
        LogUtil.c("PLGACHIEVE_AchieveMedalPngDownload", "enter allMedalTextureIsDownload");
        a();
        boolean z = true;
        for (Map.Entry<String, ConcurrentHashMap<String, Integer>> entry : this.b.entrySet()) {
            for (Map.Entry<String, Integer> entry2 : entry.getValue().entrySet()) {
                if (entry2.getValue().intValue() == 0) {
                    this.n.add(entry.getKey() + entry2.getKey());
                    z = false;
                }
            }
        }
        ReleaseLogUtil.e("PLGACHIEVE_AchieveMedalPngDownload", "needDownMedals total:", Integer.valueOf(this.n.size()), " medals ", this.n.toString());
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(Map<String, Integer> map, String str) {
        return map == null || map.get(str) == null || map.get(str).intValue() != 1;
    }

    private void g() {
        ReleaseLogUtil.e("PLGACHIEVE_AchieveMedalPngDownload", "enter DownloadMedalPngRes");
        if (!this.m.isEmpty()) {
            Iterator<Map.Entry<String, Map<String, String>>> it = this.m.entrySet().iterator();
            String str = null;
            String str2 = null;
            while (it.hasNext()) {
                Map.Entry<String, Map<String, String>> next = it.next();
                Map<String, String> value = next.getValue();
                String key = next.getKey();
                ConcurrentHashMap<String, Integer> concurrentHashMap = this.b.get(key);
                if (!it.hasNext()) {
                    str = key;
                }
                Iterator<Map.Entry<String, String>> it2 = value.entrySet().iterator();
                while (it2.hasNext()) {
                    Map.Entry<String, String> next2 = it2.next();
                    String key2 = next2.getKey();
                    if (!it2.hasNext()) {
                        str2 = key2;
                    }
                    a(next2, concurrentHashMap, key, str, str2);
                }
            }
            m();
            mmb.d(this.h);
            return;
        }
        f();
    }

    private void m() {
        this.i = false;
        final Timer timer = new Timer("PLGACHIEVE_AchieveMedalPngDownload");
        final int[] iArr = {0};
        timer.scheduleAtFixedRate(new TimerTask() { // from class: mdv.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                int[] iArr2 = iArr;
                iArr2[0] = iArr2[0] + 1;
                LogUtil.a("PLGACHIEVE_AchieveMedalPngDownload", "medalDownloadChecker: " + iArr[0]);
                if (mdv.this.i || ((iArr[0] >= 6 && CommonUtil.x(BaseApplication.getContext())) || iArr[0] >= 180)) {
                    if (mdv.this.h != null) {
                        LogUtil.a("PLGACHIEVE_AchieveMedalPngDownload", "shutdownNow executor");
                        mdv.this.h.shutdownNow();
                    }
                    timer.cancel();
                    LogUtil.a("PLGACHIEVE_AchieveMedalPngDownload", "medalDownloadChecker finish.");
                }
            }
        }, PreConnectManager.CONNECT_INTERNAL, PreConnectManager.CONNECT_INTERNAL);
    }

    private void a(Map.Entry<String, String> entry, final Map<String, Integer> map, final String str, final String str2, final String str3) {
        final String key = entry.getKey();
        final String value = entry.getValue();
        if (this.h.isShutdown()) {
            this.h = Executors.newSingleThreadExecutor();
        }
        this.h.execute(new Runnable() { // from class: mdv.3
            @Override // java.lang.Runnable
            public void run() {
                if (mdv.this.b((Map<String, Integer>) map, key)) {
                    FileUtil.c(mdv.f14910a).b(value, mlb.b(str), mlb.c(str, key), new AchieveMedalCallBack() { // from class: mdv.3.2
                        @Override // com.huawei.pluginachievement.gltexture.impl.AchieveMedalCallBack
                        public void onResponse(int i) {
                            mdv.this.b(i, str, key, str2, str3);
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, String str, String str2, String str3, String str4) {
        String str5 = str + str2 + i;
        synchronized (e) {
            this.f.add(str5);
        }
        if (i == 200) {
            b(mlb.c(str, str2));
            this.g.set(0);
        } else {
            ReleaseLogUtil.d("PLGACHIEVE_AchieveMedalPngDownload", "DownMedals fail! ", str5);
            this.g.incrementAndGet();
        }
        if (!(str.equals(str3) && str2.equals(str4)) && this.g.get() < 6) {
            return;
        }
        LogUtil.a("PLGACHIEVE_AchieveMedalPngDownload", "DownMedals is end or failures reaches the upper limit. failCount = " + this.g.get());
        this.g.set(0);
        f();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        mct.b(f14910a, "_medalPngStatusDownloadDoing", "done");
        a();
        this.i = true;
    }

    private void a() {
        synchronized (e) {
            this.f.clear();
            this.n.clear();
        }
    }
}
