package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.healthcloud.plugintrack.trackanimation.download.DynamicTrackDownloadUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.pluginmgr.EzPluginType;
import com.huawei.pluginmgr.filedownload.PullListener;
import health.compact.a.CommonUtil;
import health.compact.a.GrsDownloadPluginUrl;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class han {
    private static final Object b = new Object();
    private static volatile han e;

    /* renamed from: a, reason: collision with root package name */
    private String f13048a;
    private msa d;
    private DynamicTrackDownloadUtils.DownloadResponseCallback h;
    private gww k;
    private List<msa> o;
    private mrx n = new mrx(EzPluginType.DYNAMIC_TRACK_CUSTOM_MAP_RESOURCES_TYPE, null);
    private int f = 0;
    private boolean g = false;
    private int m = 0;
    private boolean i = false;
    private PullListener c = new PullListener() { // from class: han.1
        @Override // com.huawei.pluginmgr.filedownload.PullListener
        public void onPullingChange(msq msqVar, mso msoVar) {
            if (msoVar == null) {
                LogUtil.b("Track_DynamicTrackMapDownloadUtils", "result is null");
                return;
            }
            int i = msoVar.i();
            LogUtil.a("Track_DynamicTrackMapDownloadUtils", "download index file status=", Integer.valueOf(i));
            if (i != 1) {
                if (i == 0) {
                    LogUtil.c("Track_DynamicTrackMapDownloadUtils", "download index file status in progress");
                    return;
                }
                LogUtil.h("Track_DynamicTrackMapDownloadUtils", "download index file status fail");
                OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_DYNAMIC_TRAJECTORY_MUSIC_DOWNLOAD_85070015.value(), i);
                han hanVar = han.this;
                hanVar.b(hanVar.j());
                return;
            }
            han hanVar2 = han.this;
            hanVar2.o = hanVar2.n.b();
            if (han.this.o != null) {
                han.this.n.a(msoVar.a());
                han hanVar3 = han.this;
                hanVar3.d = (msa) hanVar3.o.get(0);
                OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_DYNAMIC_TRAJECTORY_MUSIC_DOWNLOAD_85070015.value(), 0);
                if (han.this.h()) {
                    if (CommonUtil.ah(BaseApplication.getContext()) && han.this.g) {
                        Message aYc_ = han.this.aYc_(103, 105);
                        aYc_.obj = Integer.valueOf(han.this.d.e());
                        han.this.j.sendMessage(aYc_);
                        return;
                    } else {
                        han hanVar4 = han.this;
                        hanVar4.d(hanVar4.m);
                        return;
                    }
                }
                return;
            }
            LogUtil.h("Track_DynamicTrackMapDownloadUtils", "mMapIndexInfo gis null");
        }
    };
    private Handler j = new e(Looper.getMainLooper(), this);

    private han() {
        gww gwwVar = new gww(BaseApplication.getContext(), new StorageParams(), Integer.toString(20002));
        this.k = gwwVar;
        String q = gwwVar.q();
        this.f13048a = q;
        LogUtil.a("Track_DynamicTrackMapDownloadUtils", " save Version = ", q);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aYd_(Message message) {
        DynamicTrackDownloadUtils.DownloadResponseCallback downloadResponseCallback;
        int i = this.f;
        if (message.obj instanceof Integer) {
            i = ((Integer) message.obj).intValue();
            this.f = i;
        }
        if (message.arg1 != 105 || (downloadResponseCallback = this.h) == null) {
            return;
        }
        downloadResponseCallback.onProgress(i);
    }

    public static han e() {
        if (e == null) {
            synchronized (b) {
                if (e == null) {
                    e = new han();
                }
            }
        }
        return e;
    }

    public void d(final int i) {
        if (h()) {
            this.m = i;
            String h = this.d.h();
            this.f13048a = h;
            this.k.i(h);
            final String b2 = this.d.b();
            this.j.sendMessage(aYc_(102, 105));
            this.n.e(b2, new PullListener() { // from class: han.5
                @Override // com.huawei.pluginmgr.filedownload.PullListener
                public void onPullingChange(msq msqVar, mso msoVar) {
                    if (msoVar == null) {
                        LogUtil.h("Track_DynamicTrackMapDownloadUtils", "onPullingChange result is null.");
                        return;
                    }
                    LogUtil.a("Track_DynamicTrackMapDownloadUtils", "result.fetchStatus()= ", Integer.valueOf(msoVar.i()));
                    int i2 = msoVar.i();
                    if (i2 == 1) {
                        han.this.a(b2);
                        han.this.j.sendMessage(han.this.aYc_(100, 105));
                        if (!han.this.g) {
                            han.this.e(hap.a(i), i);
                            han.this.f();
                        }
                        OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_DYNAMIC_TRAJECTORY_MUSIC_DOWNLOAD_85070015.value(), 0);
                        return;
                    }
                    if (i2 == 0) {
                        msa c = han.this.c(b2);
                        if (!han.this.g || c == null) {
                            return;
                        }
                        int b3 = c.e() != 0 ? (msoVar.b() * 100) / c.e() : 0;
                        if (b3 > 99) {
                            b3 = 99;
                        }
                        Message aYc_ = han.this.aYc_(104, 105);
                        aYc_.obj = Integer.valueOf(b3);
                        han.this.j.sendMessage(aYc_);
                        return;
                    }
                    OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_DYNAMIC_TRAJECTORY_MUSIC_DOWNLOAD_85070015.value(), i2);
                    han.this.j.sendMessage(han.this.aYc_(101, 105));
                }
            });
        }
    }

    public boolean h() {
        if (koq.b(this.o) || koq.b(this.n.b())) {
            return false;
        }
        List<msa> b2 = this.n.b();
        this.o = b2;
        if (b2 == null) {
            return false;
        }
        this.d = b2.get(0);
        return (!TextUtils.isEmpty(this.f13048a) && this.f13048a.equals(this.d.h()) && new File(hap.e("gaode_map")).exists()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Message aYc_(int i, int i2) {
        Message obtain = Message.obtain();
        obtain.arg1 = i2;
        obtain.what = i;
        return obtain;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public msa c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("Track_DynamicTrackMapDownloadUtils", "uuid is empty");
            return null;
        }
        List<msa> list = this.o;
        if (list != null) {
            for (msa msaVar : list) {
                if (str.equals(msaVar.b())) {
                    return msaVar;
                }
            }
        }
        return null;
    }

    public void b(DynamicTrackDownloadUtils.DownloadResponseCallback downloadResponseCallback, boolean z) {
        this.g = z;
        this.h = downloadResponseCallback;
        c();
        i();
    }

    public static void a() {
        if (e != null) {
            e.b();
            e = null;
        }
    }

    public void c() {
        LogUtil.a("Track_DynamicTrackMapDownloadUtils", "enter cancelDownloading map");
        Iterator<msq> it = msn.c().b().iterator();
        while (it.hasNext()) {
            msq next = it.next();
            if (b(next)) {
                msj.a().b(next);
                b(j());
            }
        }
    }

    private boolean b(msq msqVar) {
        List<msa> b2 = this.n.b();
        if (koq.b(b2)) {
            LogUtil.h("Track_DynamicTrackMapDownloadUtils", "isDynamicTrackMapTask allIndexInfo is empty");
            return false;
        }
        Iterator<msa> it = b2.iterator();
        while (it.hasNext()) {
            if (msqVar.c().equals(it.next().b())) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        LogUtil.a("Track_DynamicTrackMapDownloadUtils", "downloadIndexFile");
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: han.2
                @Override // java.lang.Runnable
                public void run() {
                    han.this.i();
                }
            });
            return;
        }
        LogUtil.c("Track_DynamicTrackMapDownloadUtils", "downloadIndexFile queryStr:", this.n.e(new GrsDownloadPluginUrl().getDownloadPluginUrl(null, true), (String) null));
        this.n.e(this.c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        LogUtil.a("Track_DynamicTrackMapDownloadUtils", "deleteTempFiles,isDeleted is:", Boolean.valueOf(Utils.c(new File(str))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        Utils.d(e(str), g());
    }

    private String g() {
        StringBuilder sb = new StringBuilder(16);
        sb.append(mrv.d);
        sb.append("dynamic_track_map_resource");
        sb.append(File.separator);
        return sb.toString();
    }

    private String e(String str) {
        StringBuilder sb = new StringBuilder(16);
        sb.append(g());
        sb.append(str);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String j() {
        return this.n.a().getIndexFileSavePath();
    }

    public String e(String str, int i) {
        ArrayList arrayList = new ArrayList();
        String c = Utils.c(hap.d(this.f13048a, hap.c(str)));
        if (TextUtils.isEmpty(c)) {
            LogUtil.h("Track_DynamicTrackMapDownloadUtils", "jsonString = null");
            return c;
        }
        try {
            JSONObject jSONObject = new JSONObject(c);
            try {
                JSONArray jSONArray = jSONObject.getJSONArray("styleList");
                String string = jSONObject.getString("version");
                LogUtil.a("Track_DynamicTrackMapDownloadUtils", "Map Version ", string);
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    hak hakVar = (hak) new Gson().fromJson(jSONArray.getJSONObject(i2).toString(), hak.class);
                    if (!TextUtils.isEmpty(hakVar.c())) {
                        arrayList.add(hakVar);
                    } else {
                        hakVar.d(hap.b(string, hakVar.k()));
                        hakVar.b(hap.a(string, hakVar.f(), "zh-rCN"));
                        hakVar.e(hap.a(string, str, hakVar.j(), i));
                        if ("gaode_map".equals(str)) {
                            hakVar.a(hap.b(string, str, hakVar.j()));
                        }
                        arrayList.add(hakVar);
                    }
                }
            } catch (JSONException unused) {
                LogUtil.h("Track_DynamicTrackMapDownloadUtils", "JSONException map");
            }
            String json = new Gson().toJson(arrayList);
            new gww(BaseApplication.getContext(), new StorageParams(), Integer.toString(20002)).c(json, i);
            return json;
        } catch (JSONException unused2) {
            LogUtil.h("Track_DynamicTrackMapDownloadUtils", " jsonObject fail ");
            return c;
        }
    }

    public String f() {
        ArrayList arrayList = new ArrayList();
        String c = Utils.c(hap.d(this.f13048a, hap.c("mark_config")));
        if (TextUtils.isEmpty(c)) {
            LogUtil.h("Track_DynamicTrackMapDownloadUtils", "jsonString = null");
            return c;
        }
        try {
            JSONObject jSONObject = new JSONObject(c);
            try {
                JSONArray jSONArray = jSONObject.getJSONArray("styleList");
                String string = jSONObject.getString("version");
                for (int i = 0; i < jSONArray.length(); i++) {
                    hah hahVar = (hah) new Gson().fromJson(jSONArray.getJSONObject(i).toString(), hah.class);
                    if (!TextUtils.isEmpty(hahVar.b())) {
                        hahVar.b(hap.b(string, hahVar.a()));
                        arrayList.add(hahVar);
                    } else {
                        hahVar.b(hap.b(string, hahVar.a()));
                        hahVar.a(hap.b(string, hahVar.f()));
                        hahVar.d(hap.b(string, hahVar.d()));
                        arrayList.add(hahVar);
                    }
                }
            } catch (JSONException unused) {
                LogUtil.h("Track_DynamicTrackMapDownloadUtils", "JSONException mark");
            }
            String json = new Gson().toJson(arrayList);
            new gww(BaseApplication.getContext(), new StorageParams(), Integer.toString(20002)).d(json);
            return json;
        } catch (JSONException unused2) {
            LogUtil.h("Track_DynamicTrackMapDownloadUtils", "new jsonObject fail");
            return c;
        }
    }

    public String d(String str) {
        String e2 = mtj.e(null);
        String c = CommonUtil.c(b(str, e2));
        if (c == null) {
            LogUtil.b("Track_DynamicTrackMapDownloadUtils", "languagePath is null");
            return "en";
        }
        if (!new File(c).exists()) {
            LogUtil.b("Track_DynamicTrackMapDownloadUtils", "language file is null");
            e2 = "en";
        }
        LogUtil.a("Track_DynamicTrackMapDownloadUtils", "language ", e2);
        return e2;
    }

    public String b(String str, String str2) {
        String substring = str.substring(str.length() - 11, str.length() - 5);
        if (substring.equals(str2)) {
            return str;
        }
        if (!TextUtils.isEmpty(str2)) {
            return str.replace(substring, str2);
        }
        LogUtil.h("Track_DynamicTrackMapDownloadUtils", "mCurrentLanguage = null");
        return null;
    }

    public boolean d() {
        return this.i;
    }

    public void c(boolean z) {
        this.i = z;
    }

    public void b() {
        Handler handler = this.j;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    static class e extends BaseHandler<han> {
        public e(Looper looper, han hanVar) {
            super(looper, hanVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: aYe_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(han hanVar, Message message) {
            if (message == null) {
                LogUtil.b("Track_DynamicTrackMapDownloadUtils", "mHandler msg is null");
            }
            switch (message.what) {
                case 100:
                    if (message.arg1 == 105 && hanVar.h != null) {
                        hanVar.h.onSuccess();
                        break;
                    }
                    break;
                case 101:
                    if (message.arg1 == 105 && hanVar.h != null) {
                        hanVar.h.onFail();
                        break;
                    }
                    break;
                case 102:
                    if (message.arg1 == 105 && hanVar.h != null) {
                        hanVar.h.onStart();
                        break;
                    }
                    break;
                case 103:
                    int intValue = message.obj instanceof Integer ? ((Integer) message.obj).intValue() : 0;
                    if (message.arg1 == 105 && hanVar.h != null) {
                        hanVar.h.onMobile(intValue);
                        break;
                    }
                    break;
                case 104:
                    hanVar.aYd_(message);
                    break;
            }
        }
    }
}
