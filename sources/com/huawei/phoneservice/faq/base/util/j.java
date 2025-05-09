package com.huawei.phoneservice.faq.base.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Pair;
import com.google.gson.Gson;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.huawei.hms.framework.network.grs.GrsClient;
import com.huawei.hms.framework.network.grs.IQueryUrlsCallBack;
import com.huawei.hms.framework.network.restclient.hwhttp.Callback;
import com.huawei.hms.framework.network.restclient.hwhttp.Submit;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.phoneservice.faq.base.entity.Builder;
import com.huawei.phoneservice.faq.base.entity.FaqSdkServiceUrlResponse;
import com.huawei.phoneservice.faq.base.entity.ModuleConfigRequest;
import com.huawei.phoneservice.faq.base.entity.ModuleConfigResponse;
import com.huawei.phoneservice.faq.base.network.FaqCallback;
import com.huawei.phoneservice.faq.base.network.FaqRestClient;
import com.huawei.phoneservice.faq.base.network.FaqSdkAddressApi;
import com.huawei.phoneservice.faq.base.network.SdkAppInfo;
import com.huawei.phoneservice.faq.base.util.FaqHandler;
import java.io.File;
import java.lang.ref.WeakReference;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public final class j implements ISdk, Sdk, FaqHandler.CallBack {

    /* renamed from: a, reason: collision with root package name */
    private volatile com.huawei.phoneservice.faq.base.b f8242a;
    private Handler b;
    private WeakReference<SdkListener> c;
    private volatile com.huawei.phoneservice.faq.base.c d;
    private Application e;
    private WeakReference<SdkClickListener> f;
    private Map<String, String> g;
    private Map<String, String> h;
    private Map<String, String> i;
    private String j;
    private List<SdkUpdateListener> k;
    private FaqBaseCallback l;

    @Override // com.huawei.phoneservice.faq.base.util.ISdk
    public void unregisterUpdateListener(SdkUpdateListener sdkUpdateListener) {
        if (this.k != null) {
            ((BaseSdkUpdateRequest) sdkUpdateListener).a();
            this.k.remove(sdkUpdateListener);
        }
    }

    @Override // com.huawei.phoneservice.faq.base.util.ISdk
    public void showReleaseLog(boolean z) {
        i.cdg_(z, this.e);
    }

    @Override // com.huawei.phoneservice.faq.base.util.Sdk
    public void setUriFromFaq(Uri uri) {
        if (uri != null) {
            try {
                if (!FaqConstants.HOST_NAME.equals(URI.create(uri.toString()).getHost())) {
                    i.e("SDK INIT SET URI", " ERROR, HOST ILLEGAL !!!");
                    return;
                }
                if (this.g == null) {
                    this.g = new Builder().build();
                }
                this.g.putAll(new Builder().setUri(uri).build());
            } catch (IllegalArgumentException unused) {
                i.e("SDK INIT SET URI", "ERROR, IllegalArgumentException !!!");
            }
        }
    }

    @Override // com.huawei.phoneservice.faq.base.util.Sdk
    public void setSdkListener(SdkListener sdkListener) {
        synchronized (this) {
            if (sdkListener != null) {
                WeakReference<SdkListener> weakReference = this.c;
                if (weakReference != null) {
                    weakReference.clear();
                }
                this.c = new WeakReference<>(sdkListener);
            } else {
                WeakReference<SdkListener> weakReference2 = this.c;
                if (weakReference2 != null) {
                    weakReference2.clear();
                    this.c = null;
                }
            }
        }
    }

    @Override // com.huawei.phoneservice.faq.base.util.Sdk
    public void setLanguage(String str) {
        m.a(this.e, str);
    }

    @Override // com.huawei.phoneservice.faq.base.util.Sdk
    public void setClickListener(SdkClickListener sdkClickListener) {
        if (sdkClickListener != null) {
            WeakReference<SdkClickListener> weakReference = this.f;
            if (weakReference != null) {
                weakReference.clear();
            }
            this.f = new WeakReference<>(sdkClickListener);
            return;
        }
        WeakReference<SdkClickListener> weakReference2 = this.f;
        if (weakReference2 != null) {
            weakReference2.clear();
            this.f = null;
        }
    }

    @Override // com.huawei.phoneservice.faq.base.util.Sdk
    public void setCallback(FaqBaseCallback faqBaseCallback) {
        this.l = faqBaseCallback;
    }

    @Override // com.huawei.phoneservice.faq.base.util.Sdk
    public Sdk saveSdk(String str, String str2) {
        synchronized (this) {
            if (str != null) {
                String sdk = getSdk(str);
                Map<String, String> map = this.g;
                if (map != null) {
                    if (str2 != null) {
                        map.put(str, str2);
                        Map<String, String> map2 = this.i;
                        if (map2 != null) {
                            map2.put(str, str2);
                        }
                    }
                    List<SdkUpdateListener> list = this.k;
                    if (list != null) {
                        for (int size = list.size() - 1; size >= 0; size--) {
                            this.k.get(size).onSdkUpdate(str, sdk, str2);
                        }
                    }
                }
            }
        }
        return this;
    }

    @Override // com.huawei.phoneservice.faq.base.util.Sdk
    public void saveMapOnSaveInstanceState(String str) {
        String e2 = m.e(this.e);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Map<String, String> map = this.g;
        if (map == null) {
            this.g = new Builder().setJson(str).build();
            if (!TextUtils.isEmpty(e2)) {
                this.g.put("language", e2);
                m.a(this.e, "");
            }
        } else {
            map.putAll(new Builder().setJson(str).build());
            if (!TextUtils.isEmpty(e2)) {
                this.g.put("language", e2);
                m.a(this.e, "");
            }
            Map<String, String> map2 = this.i;
            if (map2 != null) {
                this.g.putAll(map2);
            }
        }
        this.h.putAll(this.g);
        this.f8242a = com.huawei.phoneservice.faq.base.b.INIT_SUCCESS;
    }

    @Override // com.huawei.phoneservice.faq.base.util.Sdk
    public void release() {
        WeakReference<SdkListener> weakReference = this.c;
        if (weakReference != null) {
            weakReference.clear();
            this.c = null;
        }
        WeakReference<SdkClickListener> weakReference2 = this.f;
        if (weakReference2 != null) {
            weakReference2.clear();
            this.f = null;
        }
        List<SdkUpdateListener> list = this.k;
        if (list != null) {
            list.clear();
            this.k = null;
        }
        Map<String, String> map = this.g;
        if (map != null) {
            map.clear();
            this.g = null;
        }
        Handler handler = this.b;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.b = null;
        }
        this.f8242a = com.huawei.phoneservice.faq.base.b.INIT_FAIL;
        this.e = null;
    }

    @Override // com.huawei.phoneservice.faq.base.util.ISdk
    public void registerUpdateListener(SdkUpdateListener sdkUpdateListener) {
        if (this.k == null) {
            this.k = new ArrayList();
        }
        ((BaseSdkUpdateRequest) sdkUpdateListener).a(new e(sdkUpdateListener));
        this.k.add(sdkUpdateListener);
    }

    @Override // com.huawei.phoneservice.faq.base.util.ISdk
    public Submit queryModuleList(Context context, ModuleConfigRequest moduleConfigRequest, Callback callback) {
        return FaqSdkAddressApi.f8232a.e(context).b(moduleConfigRequest, callback);
    }

    @Override // com.huawei.phoneservice.faq.base.util.ISdk
    public void onSdkInit(int i, String str) {
        SdkListener sdkListener;
        WeakReference<SdkListener> weakReference = this.c;
        if (weakReference == null || (sdkListener = weakReference.get()) == null) {
            return;
        }
        this.d = com.huawei.phoneservice.faq.base.c.INIT_DONE;
        if (i != 0 || this.f8242a.a() == 0) {
            sdkListener.onSdkInit(i, this.f8242a.a(), this.f8242a.b() + str);
            return;
        }
        sdkListener.onSdkInit(-1, this.f8242a.a(), this.f8242a.b() + str);
    }

    @Override // com.huawei.phoneservice.faq.base.util.ISdk
    public void onSdkErr(String str, String str2) {
        Handler handler = this.b;
        if (handler != null) {
            handler.removeMessages(0);
            Handler handler2 = this.b;
            handler2.sendMessageDelayed(handler2.obtainMessage(0, new Pair(str, str2)), 1000L);
        }
    }

    @Override // com.huawei.phoneservice.faq.base.util.ISdk
    public void onClick(String str, String str2, Object obj) {
        SdkClickListener sdkClickListener;
        WeakReference<SdkClickListener> weakReference = this.f;
        if (weakReference == null || (sdkClickListener = weakReference.get()) == null) {
            return;
        }
        sdkClickListener.onClick(str, str2, obj);
    }

    @Override // com.huawei.phoneservice.faq.base.util.Sdk
    public boolean initIsDone() {
        return this.d == com.huawei.phoneservice.faq.base.c.INIT_DONE;
    }

    @Override // com.huawei.phoneservice.faq.base.util.Sdk
    public boolean init(Application application) {
        this.e = application;
        this.b = new FaqHandler(this);
        return this.f8242a != com.huawei.phoneservice.faq.base.b.INIT_FAIL;
    }

    @Override // com.huawei.phoneservice.faq.base.util.Sdk
    public boolean init() {
        return this.f8242a != com.huawei.phoneservice.faq.base.b.INIT_FAIL;
    }

    @Override // com.huawei.phoneservice.faq.base.util.Sdk
    public int init(Application application, String str, SdkListener sdkListener) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            str2 = "ERROR, uriStr EMPTY !!!";
        } else {
            Uri parse = Uri.parse(str);
            if (parse != null) {
                return init(application, parse, sdkListener);
            }
            str2 = "ERROR, Uri NULL !!!";
        }
        i.e("SDK INIT", str2);
        return -1;
    }

    @Override // com.huawei.phoneservice.faq.base.util.Sdk
    public int init(Application application, Builder builder, SdkListener sdkListener) {
        this.e = application;
        this.f8242a = init() ? com.huawei.phoneservice.faq.base.b.INIT_AGAIN : com.huawei.phoneservice.faq.base.b.INIT_FAIL;
        this.d = com.huawei.phoneservice.faq.base.c.INIT_PROGRESS;
        if (sdkListener != null) {
            WeakReference<SdkListener> weakReference = this.c;
            if (weakReference != null) {
                weakReference.clear();
            }
            this.c = new WeakReference<>(sdkListener);
        }
        if (builder.isEmpty()) {
            this.f8242a = com.huawei.phoneservice.faq.base.b.PARAMETER_ERROR;
            onSdkInit(-1, "ERROR, parameter EMPTY !!!");
            return -1;
        }
        Map<String, String> build = builder.build();
        this.g = build;
        b(build.get("country"));
        return 0;
    }

    @Override // com.huawei.phoneservice.faq.base.util.Sdk
    public int init(Application application, Uri uri, SdkListener sdkListener) {
        if (uri == null) {
            return -1;
        }
        try {
            if (FaqConstants.HOST_NAME.equals(URI.create(uri.toString()).getHost())) {
                return init(application, new Builder().setUri(uri), sdkListener);
            }
            i.e("SDK INIT", "ERROR, HOST ILLEGAL !!!");
            return -1;
        } catch (IllegalArgumentException e2) {
            i.c("SDK INIT", e2.getMessage());
            return -1;
        }
    }

    @Override // com.huawei.phoneservice.faq.base.util.Sdk
    public int init(Activity activity, String str) {
        return init(activity.getApplication(), str, (SdkListener) null);
    }

    @Override // com.huawei.phoneservice.faq.base.util.ISdk
    public boolean haveSdkErr(String str) {
        SdkListener sdkListener;
        WeakReference<SdkListener> weakReference = this.c;
        if (weakReference == null || this.b == null || (sdkListener = weakReference.get()) == null) {
            return false;
        }
        return sdkListener.haveSdkErr(str);
    }

    @Override // com.huawei.phoneservice.faq.base.util.FaqHandler.CallBack
    public void handleMessage(int i, Message message) {
        SdkListener sdkListener;
        if (i != 0) {
            return;
        }
        Object obj = message.obj;
        if (obj instanceof Pair) {
            Pair pair = (Pair) obj;
            WeakReference<SdkListener> weakReference = this.c;
            if (weakReference == null || weakReference.get() == null || (sdkListener = this.c.get()) == null) {
                return;
            }
            sdkListener.onSdkErr((String) pair.first, (String) pair.second);
        }
    }

    @Override // com.huawei.phoneservice.faq.base.util.ISdk
    public boolean hadAddress() {
        return this.f8242a.c();
    }

    @Override // com.huawei.phoneservice.faq.base.util.ISdk
    public void getServiceUrl() {
        c(this.j);
    }

    @Override // com.huawei.phoneservice.faq.base.util.Sdk
    public String getSdkVersion() {
        return "24.5.0.2";
    }

    @Override // com.huawei.phoneservice.faq.base.util.Sdk
    public SdkListener getSdkListener() {
        synchronized (this) {
            WeakReference<SdkListener> weakReference = this.c;
            if (weakReference == null) {
                return null;
            }
            return weakReference.get();
        }
    }

    @Override // com.huawei.phoneservice.faq.base.util.ISdk
    public int getSdkInitCode() {
        return this.f8242a.a();
    }

    @Override // com.huawei.phoneservice.faq.base.util.Sdk
    public String getSdk(String str) {
        if (this.g == null) {
            if (this.e == null) {
                i.e("FaqSdk", "CONTENXT IS NULL");
                return null;
            }
            this.g = new Builder().setJson(m.d(this.e, FaqConstants.SDK_SP_FILE_NAME, FaqConstants.SDK_SP_KEY, "")).build();
        }
        Map<String, String> map = this.g;
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    @Override // com.huawei.phoneservice.faq.base.util.ISdk
    public void getModuleList() {
        d(this.j);
    }

    @Override // com.huawei.phoneservice.faq.base.util.Sdk
    public String getMapOnSaveInstance() {
        this.i.clear();
        return new JSONObject(this.h).toString();
    }

    @Override // com.huawei.phoneservice.faq.base.util.Sdk
    public String getMap() {
        return new Gson().toJson(r.e(this.g));
    }

    @Override // com.huawei.phoneservice.faq.base.util.ISdk
    public FaqBaseCallback getCallback() {
        return this.l;
    }

    @Override // com.huawei.phoneservice.faq.base.util.ISdk
    public boolean clearDownloadFile(Application application) {
        if (l.e(com.huawei.phoneservice.faq.base.util.e.b(application))) {
            return false;
        }
        String b2 = com.huawei.phoneservice.faq.base.util.e.b(application);
        return !TextUtils.isEmpty(b2) && e(com.huawei.phoneservice.faq.base.util.e.a(application)) && e(new File(b2));
    }

    @Override // com.huawei.phoneservice.faq.base.util.ISdk
    public void canceledSubmit(Context context) {
        FaqRestClient.initRestClientAnno(context).canceledSubmit(context);
    }

    @Override // com.huawei.phoneservice.faq.base.util.Sdk
    public int apply() {
        synchronized (this) {
            if (this.g == null || this.e == null) {
                return -1;
            }
            ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(this.g);
            this.h.putAll(this.g);
            concurrentHashMap.remove("accessToken");
            concurrentHashMap.remove(FaqConstants.FAQ_REFRESH);
            concurrentHashMap.remove(FaqConstants.FAQ_LOG_SERVER_SECRET_KEY);
            m.e(this.e, FaqConstants.SDK_SP_FILE_NAME, FaqConstants.SDK_SP_KEY);
            m.e(this.e, FaqConstants.SDK_SP_FILE_NAME, FaqConstants.SDK_SP_KEY, new JSONObject(concurrentHashMap).toString());
            return 0;
        }
    }

    public static Sdk c() {
        return a();
    }

    public static ISdk e() {
        return a();
    }

    private void b(String str) {
        this.j = str;
        SdkAppInfo.initAppInfo(this.e);
        GrsBaseInfo grsBaseInfo = new GrsBaseInfo();
        grsBaseInfo.setSerCountry(str);
        grsBaseInfo.setRegCountry(str);
        try {
            e(new GrsClient(this.e, grsBaseInfo), str);
        } catch (Exception e2) {
            this.f8242a = com.huawei.phoneservice.faq.base.b.NO_GRS_ADDRESS;
            onSdkInit(-1, "can not get address from GRS " + e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        i.e("FaqSdk getServiceByNet", "code:" + str);
        com.huawei.phoneservice.faq.base.entity.c cVar = new com.huawei.phoneservice.faq.base.entity.c();
        cVar.d(str);
        FaqSdkAddressApi.f8232a.e(this.e).d(cVar, new d(FaqSdkServiceUrlResponse.class, null, str));
    }

    private boolean e(File file) {
        boolean z = true;
        if (file == null || !file.exists()) {
            i.e("FILE DELETE", "FILE IS NOT EXISTS" + file);
            return true;
        }
        if (!file.isDirectory()) {
            return false;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return true;
        }
        for (File file2 : listFiles) {
            z &= file2.delete();
            if (!z) {
                i.e("FILE DELETE", "FILE CLEAR IS FAILED " + file2);
            }
        }
        return z;
    }

    private void d(String str) {
        ModuleConfigRequest moduleConfigRequest = new ModuleConfigRequest(str, getSdk("language"), r.e(), getSdk("channel"), c().getSdk(FaqConstants.FAQ_SHASN), com.huawei.phoneservice.faq.base.util.d.h(), getSdk("appVersion"), getSdk("model"), com.huawei.phoneservice.faq.base.util.d.b());
        i.e("FaqSdk", moduleConfigRequest.toString());
        FaqSdkAddressApi.f8232a.e(this.e).b(moduleConfigRequest, new c(ModuleConfigResponse.class, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(FaqSdkServiceUrlResponse faqSdkServiceUrlResponse, String str) {
        boolean z;
        Iterator<FaqSdkServiceUrlResponse.ServiceUrl> it = faqSdkServiceUrlResponse.a().iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            FaqSdkServiceUrlResponse.ServiceUrl next = it.next();
            if (str.equals(next.a())) {
                r.d(next, this.g);
                Application application = this.e;
                if (application != null) {
                    com.huawei.phoneservice.faq.base.tracker.d.b(application, next.b(), true, false);
                }
                z = !TextUtils.isEmpty("Y".equals(c().getSdk(FaqConstants.FAQ_USE_OLD_DOMAIN)) ? next.c() : next.d());
            }
        }
        if ("Y".equals(c().getSdk(FaqConstants.FAQ_IS_DEEPLICK))) {
            this.f8242a = com.huawei.phoneservice.faq.base.b.INIT_SUCCESS;
            onSdkInit(0, null);
        } else {
            if (z) {
                d(str);
                return;
            }
            this.f8242a = com.huawei.phoneservice.faq.base.b.NO_ADDRESS;
            onSdkInit(-1, "no address in countryCode:" + str);
            i.e("SDK_ERROR", "no address in countryCode:");
        }
    }

    private void e(GrsClient grsClient, String str) {
        grsClient.ayncGetGrsUrls(FaqConstants.GRS_SERVICE_NAME, new b(grsClient, str));
    }

    private static j a() {
        return a.e;
    }

    class b implements IQueryUrlsCallBack {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f8243a;
        final /* synthetic */ GrsClient e;

        @Override // com.huawei.hms.framework.network.grs.IQueryUrlsCallBack
        public void onCallBackSuccess(Map<String, String> map) {
            i.e("FaqSdk", "getGrsUrls success ");
            if (map == null || map.isEmpty()) {
                i.c("FaqSdk", "urlMap is null");
                return;
            }
            String synGetGrsUrl = this.e.synGetGrsUrl(FaqConstants.GRS_LOGSERVER_SERVICE_NAME, "ROOT");
            if (!TextUtils.isEmpty(synGetGrsUrl)) {
                map.put("ROOT", synGetGrsUrl);
            }
            r.d(map, (Map<String, String>) j.this.g);
            try {
                j.this.c(this.f8243a);
            } catch (Exception e) {
                j.this.f8242a = com.huawei.phoneservice.faq.base.b.NO_ADDRESS_SERVICE;
                j.this.onSdkInit(-1, "FaqSdk getServiceByNet " + e.getMessage());
            }
        }

        @Override // com.huawei.hms.framework.network.grs.IQueryUrlsCallBack
        public void onCallBackFail(int i) {
            i.e("FaqSdk", "testsynGetGrsUrlsGRSIndependent method failed and the errorCode is" + i);
            i.d("FaqSdk", "IS_CONSUMER");
            j.this.f8242a = com.huawei.phoneservice.faq.base.b.NO_GRS_ADDRESS;
            j.this.onSdkInit(-1, "can not get address from GRS. errorCode" + i);
            i.e("FaqSdk", "can not get address from GRS. errorCode" + i);
        }

        b(GrsClient grsClient, String str) {
            this.e = grsClient;
            this.f8243a = str;
        }
    }

    static class a {
        private static final j e = new j(null);
    }

    class c extends FaqCallback<ModuleConfigResponse> {
        @Override // com.huawei.phoneservice.faq.base.network.FaqCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onResult(Throwable th, ModuleConfigResponse moduleConfigResponse) {
            String str;
            String str2;
            ModuleConfigUtils.cleanSdkModuleList(j.this.e);
            if (th != null || moduleConfigResponse == null) {
                j.this.f8242a = com.huawei.phoneservice.faq.base.b.NO_MODULE_SERVICE;
            } else {
                j.this.f8242a = com.huawei.phoneservice.faq.base.b.INIT_SUCCESS;
                List<ModuleConfigResponse.ModuleListBean> moduleList = moduleConfigResponse.getModuleList();
                if (com.huawei.phoneservice.faq.base.util.b.b(moduleList)) {
                    j.this.f8242a = com.huawei.phoneservice.faq.base.b.NO_MODULE;
                    i.e("FaqSdk", "MODULE LIST IS NULL");
                } else {
                    ModuleConfigUtils.genSdkModuleList(j.this.e, moduleList);
                    for (ModuleConfigResponse.ModuleListBean moduleListBean : moduleList) {
                        if ("2".equals(moduleListBean.getModuleCode())) {
                            str = moduleListBean.getLinkAddress();
                            str2 = moduleListBean.getOpenType();
                            break;
                        }
                    }
                }
            }
            str = "";
            str2 = "";
            if (j.this.e != null) {
                m.e(j.this.e, "FAQ_IPCC_FILENAME", "FAQ_IPCC_KEY", str);
                m.e(j.this.e, "FAQ_IPCC_FILENAME", "FAQ_IPCC_TYPE", str2);
            }
            j.this.onSdkInit(0, null);
        }

        c(Class cls, Activity activity) {
            super(cls, activity);
        }
    }

    class d extends FaqCallback<FaqSdkServiceUrlResponse> {
        final /* synthetic */ String d;

        @Override // com.huawei.phoneservice.faq.base.network.FaqCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onResult(Throwable th, FaqSdkServiceUrlResponse faqSdkServiceUrlResponse) {
            j jVar;
            String str;
            if (th != null || faqSdkServiceUrlResponse == null) {
                j.this.f8242a = com.huawei.phoneservice.faq.base.b.NO_ADDRESS_SERVICE;
                jVar = j.this;
                str = "no address, network err";
            } else {
                if (!com.huawei.phoneservice.faq.base.util.b.b(faqSdkServiceUrlResponse.a())) {
                    j.this.e(faqSdkServiceUrlResponse, this.d);
                    return;
                }
                j.this.f8242a = com.huawei.phoneservice.faq.base.b.NO_ADDRESS;
                jVar = j.this;
                str = "no address in countryCode data is null";
            }
            jVar.onSdkInit(-1, str);
            i.e("SDK_ERROR", str);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(Class cls, Activity activity, String str) {
            super(cls, activity);
            this.d = str;
        }
    }

    static class e extends TimerTask {
        private final SdkUpdateListener d;

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            this.d.onSdkUpdate("accessToken", null, null);
            i.e("FaqSdk", "GET TOKEN TIME OUT");
        }

        e(SdkUpdateListener sdkUpdateListener) {
            this.d = sdkUpdateListener;
        }
    }

    /* synthetic */ j(d dVar) {
        this();
    }

    private j() {
        this.f8242a = com.huawei.phoneservice.faq.base.b.INIT_FAIL;
        this.d = com.huawei.phoneservice.faq.base.c.INIT_PROGRESS;
        this.h = new HashMap();
        this.i = new HashMap();
    }
}
