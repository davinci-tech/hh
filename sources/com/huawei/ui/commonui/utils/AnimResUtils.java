package com.huawei.ui.commonui.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ProgressListener;
import com.huawei.ui.commonui.utils.AnimResUtils;
import com.huawei.ui.commonui.utils.ResInfo;
import defpackage.jbj;
import defpackage.koq;
import defpackage.lqg;
import defpackage.lqi;
import defpackage.mrx;
import defpackage.msp;
import defpackage.nrf;
import defpackage.nri;
import defpackage.nrv;
import defpackage.nsj;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.SharedPreferenceManager;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

/* loaded from: classes6.dex */
public class AnimResUtils {
    private static final String c = File.separator + "anim" + File.separator;

    public interface FrameCallback {
        void doFrame(Drawable drawable);

        void showLastFrame();
    }

    interface ResourceListener {
        void onFailed(Throwable th);

        void onReady(List<String> list);
    }

    public static final class AnimFrameHandler extends Handler {

        /* renamed from: a, reason: collision with root package name */
        private static final HandlerCenter f8966a = HandlerCenter.a("anim_res");
        private FrameCallback b;
        private AtomicInteger c;
        private long d;
        private List<String> e;
        private boolean f;
        private volatile Drawable g;

        public AnimFrameHandler(Looper looper, List<String> list, long j, Drawable drawable, FrameCallback frameCallback) {
            super(looper);
            this.e = new ArrayList();
            this.c = new AtomicInteger(0);
            this.e.addAll(list);
            this.d = j;
            this.g = drawable;
            this.b = frameCallback;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i != 0) {
                if (i == 1) {
                    if (!(message.obj instanceof Drawable)) {
                        LogUtil.b("AnimResUtils", "handle MSG_ANIM_FRAME_SKIP_ALL_FRAME failed msg.obj = ", message.obj);
                        return;
                    }
                    this.g = (Drawable) message.obj;
                    removeMessages(0);
                    this.f = true;
                    this.b.doFrame(this.g);
                    return;
                }
                LogUtil.b("AnimResUtils", "unknown msg = ", Integer.valueOf(message.what));
                return;
            }
            if (this.f) {
                return;
            }
            LogUtil.a("AnimResUtils", "show frame = ", this.g);
            this.b.doFrame(this.g);
            final int incrementAndGet = this.c.incrementAndGet();
            List<String> list = this.e;
            if (incrementAndGet < (list != null ? list.size() : 0)) {
                sendMessageDelayed(Message.obtain(this, 0), this.d);
                f8966a.a(new Runnable() { // from class: com.huawei.ui.commonui.utils.AnimResUtils.AnimFrameHandler.1
                    @Override // java.lang.Runnable
                    public void run() {
                        LogUtil.a("AnimResUtils", "fetch currentFrameIndex:", Integer.valueOf(incrementAndGet));
                        long currentTimeMillis = System.currentTimeMillis();
                        if (koq.d(AnimFrameHandler.this.e, incrementAndGet)) {
                            String str = (String) AnimFrameHandler.this.e.get(incrementAndGet);
                            AnimFrameHandler.this.g = AnimResUtils.cHh_(str);
                            LogUtil.a("AnimResUtils", "fetch animPath = ", str, "  mNextFrame:", AnimFrameHandler.this.g);
                            long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                            if (currentTimeMillis2 > AnimFrameHandler.this.d) {
                                LogUtil.b("AnimResUtils", "fetch animPath = ", str, ", exceed too long timeElapsed = ", Long.valueOf(currentTimeMillis2), "ms, expected = ", Long.valueOf(AnimFrameHandler.this.d), "ms.");
                            }
                        }
                    }
                });
            } else {
                LogUtil.a("AnimResUtils", "showLastFrame");
                this.b.showLastFrame();
            }
        }
    }

    public static AnimFrameHandler cHi_(List<String> list, Drawable drawable, long j, FrameCallback frameCallback) {
        if (koq.b(list)) {
            LogUtil.b("AnimResUtils", "scheduleFrameAnimation failed, animResPathList is empty!");
            return null;
        }
        AnimFrameHandler animFrameHandler = new AnimFrameHandler(Looper.getMainLooper(), list, j, drawable, frameCallback);
        animFrameHandler.sendEmptyMessage(0);
        return animFrameHandler;
    }

    public static Drawable cHh_(String str) {
        return nrf.cIc_(BaseApplication.getContext(), str);
    }

    private static List<nri> d(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        hashMap.put("resources", str2);
        b bVar = (b) lqi.d().d(str, e(), HiJsonUtil.e(new e("com.huawei.health_common_config", hashMap)), b.class);
        if (bVar == null || bVar.a() != 0) {
            LogUtil.h("AnimResUtils", "getAnimResConfigList failed");
            return arrayList;
        }
        List<nri> d2 = bVar.d();
        if (koq.b(d2)) {
            LogUtil.h("AnimResUtils", "getAnimResConfigList failed, FileList is empty");
            return arrayList;
        }
        arrayList.addAll(d2);
        SharedPreferenceManager.c("threeCircleSp", "animConfigInfo", nrv.a(d2));
        SharedPreferenceManager.e("threeCircleSp", "getAnimConfigInfoTime", System.currentTimeMillis());
        return arrayList;
    }

    public static List<String> c(Context context, String str, ResInfo.Location location, d dVar) {
        String location2 = location.getLocation();
        ArrayList arrayList = new ArrayList();
        try {
            String str2 = context.getFilesDir().getCanonicalPath() + File.separator + location2 + c;
            c(str2);
            List<nri> c2 = b() ? c() : null;
            if (koq.b(c2)) {
                String noCheckUrl = GRSManager.a(context).getNoCheckUrl("domainHealthCloudCommon", GRSManager.a(context).getCommonCountryCode());
                if (TextUtils.isEmpty(noCheckUrl) || noCheckUrl == null) {
                    LogUtil.b("AnimResUtils", "fetchAnimation getUrl is empty or null.");
                    return arrayList;
                }
                c2 = d(noCheckUrl + "/commonAbility/configCenter/getConfigInfo", location2 + "_resources");
            }
            String str3 = str + "_header";
            d b2 = b(e(c2, str3), str2 + str3 + ".json", str3);
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("_res_wide_screen");
            arrayList.addAll(b(str2, e(context, c2, str + "_res", sb.toString()), b2));
            dVar.b = b2.b;
            dVar.c = b2.c;
            return arrayList;
        } catch (IOException e2) {
            LogUtil.b("AnimResUtils", "fetchAnimation failed, cause exception: ", e2.getMessage(), ", cause: ", e2.getCause());
            return arrayList;
        }
    }

    private static Map<String, String> e() {
        LogUtil.a("AnimResUtils", "enter getHeader");
        HashMap hashMap = new HashMap();
        hashMap.put("x-version", CommonUtil.c(BaseApplication.getContext()));
        hashMap.put("Content-Type", "application/json; charset=utf-8");
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (!TextUtils.isEmpty(accountInfo)) {
            hashMap.put("x-huid", accountInfo);
        }
        return hashMap;
    }

    private static boolean b() {
        long e2 = nsj.e(System.currentTimeMillis(), 0);
        long b2 = SharedPreferenceManager.b("threeCircleSp", "getAnimConfigInfoTime", 0L);
        LogUtil.a("AnimResUtils", "isGetConfigInfoToday curDarStartTime:", Long.valueOf(e2), " lastTime:", Long.valueOf(b2));
        return b2 > e2;
    }

    private static List<nri> c() {
        LogUtil.a("AnimResUtils", "getConfigInfoFromLocal");
        return (List) nrv.c(SharedPreferenceManager.e("threeCircleSp", "animConfigInfo", ""), new TypeToken<List<nri>>() { // from class: com.huawei.ui.commonui.utils.AnimResUtils.4
        }.getType());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(String str) {
        File file = new File(str);
        if (file.exists() || !file.mkdirs()) {
            LogUtil.b("AnimResUtils", "checkAndMkdirs failed for rootDir = ", str);
        }
    }

    private static d b(nri nriVar, String str, String str2) {
        if (nriVar == null) {
            return b(str);
        }
        String e2 = SharedPreferenceManager.e("anim_resources_module", str2, "");
        String a2 = nriVar.a();
        if (!e2.equals(a2)) {
            if (!FileUtils.d(new File(str))) {
                LogUtil.b("AnimResUtils", "delete animHeaderPath = ", str, " failed!");
            }
            if (!jbj.b(nriVar.b(), str)) {
                LogUtil.b("AnimResUtils", "fetchAnimHeader for animHeaderName = ", str2, "failed!");
                return b(str);
            }
            SharedPreferenceManager.c("anim_resources_module", str2, a2);
        }
        return b(str);
    }

    private static d b(String str) {
        d dVar = d.d;
        File file = new File(str);
        if (!file.exists() || !file.isFile()) {
            LogUtil.b("AnimResUtils", "createAnimHeaderFromFile failed, cause animHeaderPath = ", str, " not exists or is not file!");
            return dVar;
        }
        d dVar2 = (d) nrv.c(mrx.e(file), new TypeToken<d>() { // from class: com.huawei.ui.commonui.utils.AnimResUtils.5
        }.getType());
        return dVar2 == null ? d.d : dVar2;
    }

    private static nri e(List<nri> list, String str) {
        String d2;
        nri nriVar = null;
        if (koq.b(list)) {
            LogUtil.b("AnimResUtils", "extractAnimDownloadUrls extractConfigEntity failed, configEntityList is null!");
            return null;
        }
        for (nri nriVar2 : list) {
            if (nriVar2 != null && (d2 = nriVar2.d()) != null && d2.equals(str)) {
                nriVar = nriVar2;
            }
        }
        return nriVar;
    }

    private static List<nri> e(Context context, List<nri> list, String str, String str2) {
        List<nri> b2 = b(list, str);
        if (nsn.ag(BaseApplication.getActivity())) {
            List<nri> b3 = b(list, str2);
            if (!koq.b(b3)) {
                b2.clear();
                b2.addAll(b3);
            }
        }
        Collections.sort(b2, new Comparator<nri>() { // from class: com.huawei.ui.commonui.utils.AnimResUtils.1
            @Override // java.util.Comparator
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public int compare(nri nriVar, nri nriVar2) {
                if (nriVar == null || nriVar2 == null || nriVar.d() == null || nriVar2.d() == null) {
                    return 0;
                }
                return nriVar.d().compareTo(nriVar2.d());
            }
        });
        return b2;
    }

    private static List<nri> b(List<nri> list, String str) {
        String d2;
        ArrayList arrayList = new ArrayList();
        if (list == null) {
            LogUtil.b("AnimResUtils", "extractAnimDownloadUrls extractConfigEntityList failed, configEntityList is null!");
            return arrayList;
        }
        for (nri nriVar : list) {
            if (nriVar != null && (d2 = nriVar.d()) != null) {
                if (Pattern.compile("^(" + str + ")(_\\d)*$").matcher(d2).find()) {
                    arrayList.add(nriVar);
                }
            }
        }
        return arrayList;
    }

    private static int b(File file) {
        if (file == null) {
            return 0;
        }
        String name = file.getName();
        try {
            return Integer.parseInt(name.split("\\.")[0].split("_")[r1.length - 1]);
        } catch (Exception unused) {
            LogUtil.b("AnimResUtils", "getFileIndex failed, fileName = ", name, ", not match xxx_index.png format!", " fileName is ", name);
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<String> e(File file) {
        String str;
        ArrayList arrayList = new ArrayList();
        if (!file.isDirectory()) {
            LogUtil.b("AnimResUtils", "fetchAnimResList failed, cause animResDir = ", file.getAbsolutePath(), " is not directory");
            return arrayList;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            LogUtil.b("AnimResUtils", "fetchAnimResList failed, cause animResDir has no files");
            return arrayList;
        }
        Arrays.sort(listFiles, new Comparator() { // from class: nrd
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return AnimResUtils.e((File) obj, (File) obj2);
            }
        });
        for (File file2 : listFiles) {
            if (file2 != null) {
                try {
                    str = file2.getCanonicalPath();
                } catch (IOException e2) {
                    LogUtil.b("AnimResUtils", "createAnimResListFromFile IOException happened exception = ", e2.getMessage(), ", cause = ", e2.getCause());
                    str = "";
                }
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public static /* synthetic */ int e(File file, File file2) {
        if (file == null || file2 == null) {
            return 0;
        }
        return b(file) - b(file2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0062, code lost:
    
        if (r2.await(60000, java.util.concurrent.TimeUnit.MILLISECONDS) == false) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.util.List<java.lang.String> b(java.lang.String r8, java.util.List<defpackage.nri> r9, com.huawei.ui.commonui.utils.AnimResUtils.d r10) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.String r1 = "AnimResUtils"
            if (r9 != 0) goto L13
            java.lang.String r8 = "fetchAnimResList animResConfigEntityList is null"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r8)
            return r0
        L13:
            java.util.concurrent.CountDownLatch r2 = new java.util.concurrent.CountDownLatch
            int r3 = r9.size()
            r2.<init>(r3)
            java.util.Iterator r9 = r9.iterator()
        L20:
            boolean r3 = r9.hasNext()
            if (r3 == 0) goto L59
            java.lang.Object r3 = r9.next()
            nri r3 = (defpackage.nri) r3
            java.lang.String r4 = r3.d()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r8)
            r5.append(r4)
            java.lang.String r5 = r5.toString()
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r5)
            java.lang.String r7 = ".zip"
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            com.huawei.ui.commonui.utils.AnimResUtils$3 r7 = new com.huawei.ui.commonui.utils.AnimResUtils$3
            r7.<init>()
            b(r4, r5, r6, r3, r7)
            goto L20
        L59:
            java.util.concurrent.TimeUnit r8 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch: java.lang.InterruptedException -> L65
            r3 = 60000(0xea60, double:2.9644E-319)
            boolean r8 = r2.await(r3, r8)     // Catch: java.lang.InterruptedException -> L65
            if (r8 != 0) goto L7c
            goto L73
        L65:
            r8 = move-exception
            java.lang.String r9 = "fetchAnimResList exception happened = "
            java.lang.String r8 = com.huawei.haf.common.exception.ExceptionUtils.d(r8)
            java.lang.Object[] r8 = new java.lang.Object[]{r9, r8}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r8)
        L73:
            java.lang.String r8 = "fetchAnimResList time out!"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r8)
        L7c:
            java.util.Collections.sort(r0)
            int r8 = r0.size()
            int r9 = com.huawei.ui.commonui.utils.AnimResUtils.d.d(r10)
            if (r9 <= 0) goto La6
            int r9 = com.huawei.ui.commonui.utils.AnimResUtils.d.d(r10)
            if (r8 == r9) goto La6
            int r9 = com.huawei.ui.commonui.utils.AnimResUtils.d.d(r10)
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            java.lang.String r10 = ", but frameCount = "
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            java.lang.String r2 = "fetchAnimResList exception, cause animHeader.framesCount = "
            java.lang.Object[] r8 = new java.lang.Object[]{r2, r9, r10, r8}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r8)
        La6:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.commonui.utils.AnimResUtils.b(java.lang.String, java.util.List, com.huawei.ui.commonui.utils.AnimResUtils$d):java.util.List");
    }

    private static void b(String str, final String str2, String str3, final String str4, final String str5, final String str6, final ResourceListener resourceListener) {
        SharedPreferenceManager.c("anim_resources_module", str2, str3);
        lqi.d().d(new lqg(str, null, new File(str5), new ProgressListener<File>() { // from class: com.huawei.ui.commonui.utils.AnimResUtils.2
            @Override // com.huawei.networkclient.ProgressListener
            public void onProgress(long j, long j2, boolean z) {
            }

            @Override // com.huawei.networkclient.ProgressListener
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onFinish(File file) {
                LogUtil.a("AnimResUtils", "downloadAnimRes finish download file = ", file.getName());
                FileUtils.b(new File(str6));
                AnimResUtils.c(str6);
                if (msp.c(str5, str6 + File.separator) == -1) {
                    LogUtil.b("AnimResUtils", "downloadAnimRes unzip for downloadZipPath = ", str5, " to ", str6 + File.separator, " failed!");
                }
                if (!FileUtils.d(new File(str5))) {
                    LogUtil.b("AnimResUtils", "downloadAnimRes delete downloadZipPath = ", str5, " failed!");
                }
                resourceListener.onReady(AnimResUtils.e(new File(str6)));
            }

            @Override // com.huawei.networkclient.ProgressListener
            public void onFail(Throwable th) {
                LogUtil.b("AnimResUtils", "downloadAnimRes failed to download file = ", str5, ", exception = ", ExceptionUtils.d(th));
                SharedPreferenceManager.c("anim_resources_module", str2, str4);
                resourceListener.onFailed(th);
            }
        }));
    }

    private static void b(String str, String str2, String str3, nri nriVar, ResourceListener resourceListener) {
        File file = new File(str2);
        if (nriVar == null) {
            resourceListener.onReady(e(file));
            return;
        }
        String e2 = SharedPreferenceManager.e("anim_resources_module", str, "");
        String a2 = nriVar.a();
        if (!e2.equals(a2)) {
            b(nriVar.b(), str, a2, e2, str3, str2, resourceListener);
        } else {
            resourceListener.onReady(e(file));
        }
    }

    public static final class d {
        private static final d d = new d(-1, 33);

        @SerializedName("frameDuration")
        private int b;

        @SerializedName("framesCount")
        private int c;

        public d(int i, int i2) {
            this.c = i;
            this.b = i2;
        }

        public d() {
        }

        public int a() {
            return this.b;
        }
    }

    static class e {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("greyRules")
        private Map<String, Object> f8969a;

        @SerializedName("configId")
        private String b;

        @SerializedName("ts")
        private long c;

        @SerializedName("source")
        private int d;

        @SerializedName("matchRules")
        private Map<String, String> e;

        public e(String str, Map<String, String> map) {
            Objects.requireNonNull(str);
            if (TextUtils.isEmpty(str)) {
                LogUtil.h("RequestBody", "configId is empty.");
                return;
            }
            this.c = System.currentTimeMillis();
            this.d = 1;
            this.b = str;
            this.e = map;
        }
    }

    class b {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("fileList")
        private List<nri> f8968a;

        @SerializedName("isFromCloud")
        private boolean b;

        @SerializedName("duration")
        private int c;

        @SerializedName("resultDesc")
        private String d;

        @SerializedName("resultCode")
        private int e;

        @SerializedName("resultType")
        private int g;

        public int a() {
            return this.e;
        }

        public List<nri> d() {
            return this.f8968a;
        }
    }
}
