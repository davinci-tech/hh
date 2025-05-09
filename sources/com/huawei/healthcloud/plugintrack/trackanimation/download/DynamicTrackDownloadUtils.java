package com.huawei.healthcloud.plugintrack.trackanimation.download;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.healthcloud.plugintrack.trackanimation.bgm.choosedownload.MusicInformation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.pluginmgr.EzPluginType;
import com.huawei.pluginmgr.filedownload.PullListener;
import defpackage.gww;
import defpackage.ham;
import defpackage.koq;
import defpackage.mrv;
import defpackage.mrx;
import defpackage.msa;
import defpackage.msj;
import defpackage.msn;
import defpackage.mso;
import defpackage.msq;
import health.compact.a.CommonUtil;
import health.compact.a.GrsDownloadPluginUrl;
import health.compact.a.LogAnonymous;
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
public class DynamicTrackDownloadUtils {
    private static volatile DynamicTrackDownloadUtils b;
    private static final Object c = new Object();
    private msa d;
    private DownloadResponseCallback e;
    private List<msa> i;
    private DownloadResponseCallback j;
    private String m;
    private mrx l = new mrx(EzPluginType.DYNAMIC_TRACK_RESOURCES_TYPE, null);
    private int h = 0;
    private boolean g = false;

    /* renamed from: a, reason: collision with root package name */
    private PullListener f3583a = new PullListener() { // from class: com.huawei.healthcloud.plugintrack.trackanimation.download.DynamicTrackDownloadUtils.2
        @Override // com.huawei.pluginmgr.filedownload.PullListener
        public void onPullingChange(msq msqVar, mso msoVar) {
            if (msoVar == null) {
                LogUtil.b("Track_DynamicTrackDownloadUtils", "result is null");
                return;
            }
            int i = msoVar.i();
            LogUtil.c("Track_DynamicTrackDownloadUtils", "download index file status=", Integer.valueOf(i));
            if (i != 1) {
                if (i == 0) {
                    LogUtil.c("Track_DynamicTrackDownloadUtils", "download index file status in progress");
                    return;
                }
                OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_DYNAMIC_TRAJECTORY_MUSIC_DOWNLOAD_85070015.value(), i);
                LogUtil.h("Track_DynamicTrackDownloadUtils", "download index file status fail");
                DynamicTrackDownloadUtils dynamicTrackDownloadUtils = DynamicTrackDownloadUtils.this;
                dynamicTrackDownloadUtils.d(dynamicTrackDownloadUtils.i());
                return;
            }
            LogUtil.a("Track_DynamicTrackDownloadUtils", "onPullingChange download index file status is success");
            DynamicTrackDownloadUtils dynamicTrackDownloadUtils2 = DynamicTrackDownloadUtils.this;
            dynamicTrackDownloadUtils2.i = dynamicTrackDownloadUtils2.l.b();
            if (DynamicTrackDownloadUtils.this.i != null) {
                DynamicTrackDownloadUtils.this.l.a(msoVar.a());
                DynamicTrackDownloadUtils dynamicTrackDownloadUtils3 = DynamicTrackDownloadUtils.this;
                dynamicTrackDownloadUtils3.d = (msa) dynamicTrackDownloadUtils3.i.get(0);
                OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_DYNAMIC_TRAJECTORY_MUSIC_DOWNLOAD_85070015.value(), 0);
                if (DynamicTrackDownloadUtils.this.g()) {
                    if (CommonUtil.ah(BaseApplication.getContext()) && DynamicTrackDownloadUtils.this.g) {
                        Message aXR_ = DynamicTrackDownloadUtils.this.aXR_(103, 105);
                        aXR_.obj = Integer.valueOf(DynamicTrackDownloadUtils.this.d.e());
                        DynamicTrackDownloadUtils.this.f.sendMessage(aXR_);
                        return;
                    }
                    DynamicTrackDownloadUtils.this.a();
                    return;
                }
                return;
            }
            LogUtil.b("Track_DynamicTrackDownloadUtils", "mIndexInfo is null");
        }
    };
    private Handler f = new e(Looper.getMainLooper(), this);
    private gww k = new gww(BaseApplication.getContext(), new StorageParams(), Integer.toString(20002));

    public interface DownloadResponseCallback {
        void onFail();

        void onMobile(int i);

        void onProgress(int i);

        void onStart();

        void onSuccess();
    }

    private DynamicTrackDownloadUtils() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.trackanimation.download.DynamicTrackDownloadUtils.4
            @Override // java.lang.Runnable
            public void run() {
                DynamicTrackDownloadUtils dynamicTrackDownloadUtils = DynamicTrackDownloadUtils.this;
                dynamicTrackDownloadUtils.m = dynamicTrackDownloadUtils.k.r();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aXS_(Message message) {
        DownloadResponseCallback downloadResponseCallback;
        DownloadResponseCallback downloadResponseCallback2;
        int i = this.h;
        if (message.obj instanceof Integer) {
            i = ((Integer) message.obj).intValue();
            this.h = i;
        }
        if (message.arg1 == 110 && (downloadResponseCallback2 = this.j) != null) {
            downloadResponseCallback2.onProgress(i);
        } else {
            if (message.arg1 != 105 || (downloadResponseCallback = this.e) == null) {
                return;
            }
            downloadResponseCallback.onProgress(i);
        }
    }

    public static DynamicTrackDownloadUtils d() {
        if (b == null) {
            synchronized (c) {
                if (b == null) {
                    b = new DynamicTrackDownloadUtils();
                }
            }
        }
        return b;
    }

    public void a() {
        if (g()) {
            String h = this.d.h();
            this.m = h;
            this.k.h(h);
            final String b2 = this.d.b();
            this.f.sendMessage(aXR_(102, 105));
            this.l.e(b2, new PullListener() { // from class: com.huawei.healthcloud.plugintrack.trackanimation.download.DynamicTrackDownloadUtils.5
                @Override // com.huawei.pluginmgr.filedownload.PullListener
                public void onPullingChange(msq msqVar, mso msoVar) {
                    if (msoVar == null) {
                        LogUtil.h("Track_DynamicTrackDownloadUtils", "onPullingChange result is null.");
                        return;
                    }
                    int i = msoVar.i();
                    if (i == 1) {
                        DynamicTrackDownloadUtils.this.b(b2);
                        DynamicTrackDownloadUtils.this.f.sendMessage(DynamicTrackDownloadUtils.this.aXR_(100, 105));
                        if (!DynamicTrackDownloadUtils.this.g) {
                            DynamicTrackDownloadUtils.this.h();
                        }
                        OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_DYNAMIC_TRAJECTORY_MUSIC_DOWNLOAD_85070015.value(), 0);
                        return;
                    }
                    if (i == 0) {
                        msa c2 = DynamicTrackDownloadUtils.this.c(b2);
                        if (!DynamicTrackDownloadUtils.this.g || c2 == null) {
                            return;
                        }
                        int b3 = c2.e() != 0 ? (msoVar.b() * 100) / c2.e() : 0;
                        if (b3 > 99) {
                            b3 = 99;
                        }
                        Message aXR_ = DynamicTrackDownloadUtils.this.aXR_(104, 105);
                        aXR_.obj = Integer.valueOf(b3);
                        DynamicTrackDownloadUtils.this.f.sendMessage(aXR_);
                        return;
                    }
                    OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_DYNAMIC_TRAJECTORY_MUSIC_DOWNLOAD_85070015.value(), i);
                    DynamicTrackDownloadUtils.this.f.sendMessage(DynamicTrackDownloadUtils.this.aXR_(101, 105));
                }
            });
        }
    }

    public boolean g() {
        if (koq.b(this.i) || koq.b(this.l.b())) {
            return false;
        }
        List<msa> b2 = this.l.b();
        this.i = b2;
        if (b2 == null) {
            return false;
        }
        this.d = b2.get(0);
        return (!TextUtils.isEmpty(this.m) && this.m.equals(this.d.h()) && new File(ham.b()).exists()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Message aXR_(int i, int i2) {
        Message obtain = Message.obtain();
        obtain.arg1 = i2;
        obtain.what = i;
        return obtain;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public msa c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("Track_DynamicTrackDownloadUtils", "uuid is empty");
            return null;
        }
        List<msa> list = this.i;
        if (list != null) {
            for (msa msaVar : list) {
                if (str.equals(msaVar.b())) {
                    return msaVar;
                }
            }
        }
        return null;
    }

    public void c(String str, DownloadResponseCallback downloadResponseCallback) {
        msa msaVar;
        this.j = downloadResponseCallback;
        if (CommonUtil.ah(BaseApplication.getContext())) {
            if (this.i == null) {
                LogUtil.b("Track_DynamicTrackDownloadUtils", "mIndexInfo is null, try get info from cache again");
                List<msa> b2 = this.l.b();
                if (b2 == null) {
                    b2 = new ArrayList<>();
                }
                this.i = b2;
            }
            Iterator<msa> it = this.i.iterator();
            while (true) {
                if (!it.hasNext()) {
                    msaVar = null;
                    break;
                }
                msaVar = it.next();
                if (msaVar == null) {
                    LogUtil.b("Track_DynamicTrackDownloadUtils", "ezPluginIndexInfo is null");
                } else if (msaVar.b().equals(str)) {
                    break;
                }
            }
            Message aXR_ = aXR_(103, 110);
            aXR_.obj = Integer.valueOf(msaVar != null ? msaVar.e() : 0);
            this.f.sendMessage(aXR_);
            return;
        }
        d(str, downloadResponseCallback);
    }

    public void d(final String str, DownloadResponseCallback downloadResponseCallback) {
        this.j = downloadResponseCallback;
        LogUtil.c("Track_DynamicTrackDownloadUtils", "enter downloadZipFileOneByOne index:", str);
        this.f.sendMessage(aXR_(102, 110));
        this.l.e(str, new PullListener() { // from class: com.huawei.healthcloud.plugintrack.trackanimation.download.DynamicTrackDownloadUtils.3
            @Override // com.huawei.pluginmgr.filedownload.PullListener
            public void onPullingChange(msq msqVar, mso msoVar) {
                if (msoVar == null) {
                    LogUtil.h("Track_DynamicTrackDownloadUtils", "onPullingChange result is null");
                    return;
                }
                int i = msoVar.i();
                if (i == 1) {
                    DynamicTrackDownloadUtils.this.b(str);
                    DynamicTrackDownloadUtils.this.f.sendMessage(DynamicTrackDownloadUtils.this.aXR_(100, 110));
                    OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_DYNAMIC_TRAJECTORY_MUSIC_DOWNLOAD_85070015.value(), 0);
                } else {
                    if (i == 0) {
                        msa c2 = DynamicTrackDownloadUtils.this.c(str);
                        if (c2 != null) {
                            int b2 = c2.e() != 0 ? (msoVar.b() * 100) / c2.e() : 0;
                            if (b2 > 99) {
                                b2 = 99;
                            }
                            Message aXR_ = DynamicTrackDownloadUtils.this.aXR_(104, 110);
                            aXR_.obj = Integer.valueOf(b2);
                            DynamicTrackDownloadUtils.this.f.sendMessage(aXR_);
                            return;
                        }
                        return;
                    }
                    OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_DYNAMIC_TRAJECTORY_MUSIC_DOWNLOAD_85070015.value(), i);
                    DynamicTrackDownloadUtils.this.f.sendMessage(DynamicTrackDownloadUtils.this.aXR_(101, 110));
                }
            }
        });
    }

    public void a(DownloadResponseCallback downloadResponseCallback, boolean z) {
        this.g = z;
        this.e = downloadResponseCallback;
        b();
        j();
    }

    public static void e() {
        if (b != null) {
            b.c();
            b = null;
        }
    }

    public void b() {
        LogUtil.a("Track_DynamicTrackDownloadUtils", "enter cancelDownloading");
        Iterator<msq> it = msn.c().b().iterator();
        while (it.hasNext()) {
            msq next = it.next();
            if (a(next)) {
                msj.a().b(next);
                d(i());
            }
        }
    }

    private boolean a(msq msqVar) {
        List<msa> b2 = this.l.b();
        if (koq.b(b2)) {
            LogUtil.h("Track_DynamicTrackDownloadUtils", "isDynamicTrackTask allIndexInfo is empty");
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
    public void j() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.trackanimation.download.DynamicTrackDownloadUtils.1
                @Override // java.lang.Runnable
                public void run() {
                    DynamicTrackDownloadUtils.this.j();
                }
            });
            return;
        }
        LogUtil.c("Track_DynamicTrackDownloadUtils", "downloadIndexFile,queryStr:", this.l.e(new GrsDownloadPluginUrl().getDownloadPluginUrl(null, true), (String) null));
        this.l.e(this.f3583a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) {
        LogUtil.a("Track_DynamicTrackDownloadUtils", "deleteTempFiles,isDeleted is:", Boolean.valueOf(Utils.c(new File(str))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        Utils.d(a(str), f());
    }

    private String f() {
        StringBuilder sb = new StringBuilder(16);
        sb.append(mrv.d);
        sb.append("dynamic_track_music_resource");
        sb.append(File.separator);
        return sb.toString();
    }

    private String a(String str) {
        StringBuilder sb = new StringBuilder(16);
        sb.append(f());
        sb.append(str);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String i() {
        return this.l.a().getIndexFileSavePath();
    }

    public String h() {
        List<String> b2 = Utils.b(ham.d());
        Gson gson = new Gson();
        gww gwwVar = new gww(BaseApplication.getContext(), new StorageParams(), Integer.toString(20002));
        ArrayList arrayList = new ArrayList(b2.size());
        String c2 = Utils.c(ham.c());
        if (TextUtils.isEmpty(c2)) {
            return c2;
        }
        try {
            JSONObject jSONObject = new JSONObject(c2);
            for (String str : b2) {
                JSONArray jSONArray = jSONObject.getJSONArray(str);
                for (int i = 0; i < jSONArray.length(); i++) {
                    MusicInformation musicInformation = (MusicInformation) gson.fromJson(jSONArray.getJSONObject(i).toString(), MusicInformation.class);
                    musicInformation.setMusicBackgroundPath(ham.a(str));
                    if (musicInformation.getIsDefault()) {
                        musicInformation.setIsSelected(true);
                        musicInformation.setIsDownloaded(true);
                        StringBuilder sb = new StringBuilder(16);
                        sb.append(ham.a());
                        sb.append(str);
                        sb.append(".");
                        sb.append("aac");
                        musicInformation.setMusicResourcePath(sb.toString());
                        arrayList.add(0, musicInformation);
                        gwwVar.e(ham.c(musicInformation.getMusicName(), musicInformation.getIsDefault()));
                    } else {
                        arrayList.add(musicInformation);
                    }
                }
            }
        } catch (JSONException e2) {
            LogUtil.b("Track_DynamicTrackDownloadUtils", "resolvePackageResource,", LogAnonymous.b((Throwable) e2));
        }
        arrayList.add(0, new MusicInformation());
        String json = new Gson().toJson(arrayList);
        gwwVar.a(json);
        return json;
    }

    public void c() {
        Handler handler = this.f;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    static class e extends BaseHandler<DynamicTrackDownloadUtils> {
        public e(Looper looper, DynamicTrackDownloadUtils dynamicTrackDownloadUtils) {
            super(looper, dynamicTrackDownloadUtils);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: aXY_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(DynamicTrackDownloadUtils dynamicTrackDownloadUtils, Message message) {
            if (message == null) {
                LogUtil.h("Track_DynamicTrackDownloadUtils", "mHandler msg is null");
            } else {
                aXX_(dynamicTrackDownloadUtils, message);
            }
        }

        private void aXX_(DynamicTrackDownloadUtils dynamicTrackDownloadUtils, Message message) {
            switch (message.what) {
                case 100:
                    aXV_(dynamicTrackDownloadUtils, message);
                    break;
                case 101:
                    aXT_(dynamicTrackDownloadUtils, message);
                    break;
                case 102:
                    aXW_(dynamicTrackDownloadUtils, message);
                    break;
                case 103:
                    aXU_(dynamicTrackDownloadUtils, message);
                    break;
                case 104:
                    dynamicTrackDownloadUtils.aXS_(message);
                    break;
            }
        }

        private void aXU_(DynamicTrackDownloadUtils dynamicTrackDownloadUtils, Message message) {
            int intValue = message.obj instanceof Integer ? ((Integer) message.obj).intValue() : 0;
            if (message.arg1 == 110 && dynamicTrackDownloadUtils.j != null) {
                dynamicTrackDownloadUtils.j.onMobile(intValue);
            } else {
                if (message.arg1 != 105 || dynamicTrackDownloadUtils.e == null) {
                    return;
                }
                dynamicTrackDownloadUtils.e.onMobile(intValue);
            }
        }

        private void aXV_(DynamicTrackDownloadUtils dynamicTrackDownloadUtils, Message message) {
            if (message.arg1 == 110 && dynamicTrackDownloadUtils.j != null) {
                dynamicTrackDownloadUtils.j.onSuccess();
            } else {
                if (message.arg1 != 105 || dynamicTrackDownloadUtils.e == null) {
                    return;
                }
                dynamicTrackDownloadUtils.e.onSuccess();
            }
        }

        private void aXT_(DynamicTrackDownloadUtils dynamicTrackDownloadUtils, Message message) {
            if (message.arg1 == 110 && dynamicTrackDownloadUtils.j != null) {
                dynamicTrackDownloadUtils.j.onFail();
            } else {
                if (message.arg1 != 105 || dynamicTrackDownloadUtils.e == null) {
                    return;
                }
                dynamicTrackDownloadUtils.e.onFail();
            }
        }

        private void aXW_(DynamicTrackDownloadUtils dynamicTrackDownloadUtils, Message message) {
            if (message.arg1 == 110 && dynamicTrackDownloadUtils.j != null) {
                dynamicTrackDownloadUtils.j.onStart();
            } else {
                if (message.arg1 != 105 || dynamicTrackDownloadUtils.e == null) {
                    return;
                }
                dynamicTrackDownloadUtils.e.onStart();
            }
        }
    }
}
