package defpackage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.common.Constant;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.baseapi.pluginachievement.baseapi.AchieveDeviceApi;
import com.huawei.health.deviceconnect.callback.PingCallback;
import com.huawei.health.userprofilemgr.model.BaseResponseCallback;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.pluginachievement.manager.model.MedalConfigInfo;
import com.huawei.pluginachievement.medal.model.TrackData;
import com.huawei.policy.MedalSyncPolicy;
import health.compact.a.Utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mxo implements MedalSyncPolicy, AchieveDeviceApi {

    /* renamed from: a, reason: collision with root package name */
    private static volatile mxo f15232a;
    private static final Object e = new Object();
    private long c;
    private int d;

    public static mxo d() {
        if (f15232a == null) {
            synchronized (e) {
                if (f15232a == null) {
                    f15232a = new mxo();
                }
            }
        }
        return f15232a;
    }

    @Override // com.huawei.health.baseapi.pluginachievement.baseapi.AchieveDeviceApi
    public void pingDevice(final String str) {
        iww.b().pingComand(new PingCallback() { // from class: mxo.1
            @Override // com.huawei.health.deviceconnect.callback.PingCallback
            public void onPingResult(int i) {
                LogUtil.a("MedalSyncPolicyControl", "pingDevice pkg == ", str, " code = ", Integer.valueOf(i));
            }
        }, str);
    }

    @Override // com.huawei.health.baseapi.pluginachievement.baseapi.AchieveDeviceApi
    public void syncInit() {
        LogUtil.a("MedalSyncPolicyControl", "syncInit HwSyncDeviceMgr = ");
        iww.b();
        iws.c();
        if (Utils.o()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: mxo.2
                @Override // java.lang.Runnable
                public void run() {
                    mxo.this.a();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        HashMap hashMap = new HashMap(16);
        mlb.a(hashMap);
        String str = spz.c + File.separator;
        File file = new File(str);
        if (!file.exists() && !file.mkdirs()) {
            LogUtil.b("MedalSyncPolicyControl", "mkdirResult is fail");
            return;
        }
        for (int i = 0; i < 19; i++) {
            if (hashMap.size() > i) {
                int i2 = i + 1;
                c(str, i2, ((Integer) hashMap.get(String.valueOf(i2))).intValue());
            }
        }
    }

    private void c(String str, int i, int i2) {
        FileOutputStream fileOutputStream;
        InputStream openRawResource = BaseApplication.e().getResources().openRawResource(i2);
        Bitmap decodeStream = BitmapFactory.decodeStream(openRawResource);
        File file = new File(str + i + "_lightListStyle.png");
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
            } catch (Throwable th) {
                th = th;
                fileOutputStream = null;
            }
        } catch (FileNotFoundException unused) {
        } catch (IOException unused2) {
        }
        if (!file.createNewFile()) {
            LogUtil.h("MedalSyncPolicyControl", "File ", file.getPath(), " already exists");
            d((FileOutputStream) null);
            c(openRawResource);
            return;
        }
        fileOutputStream = new FileOutputStream(file);
        try {
            decodeStream.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            d(fileOutputStream);
        } catch (FileNotFoundException unused3) {
            fileOutputStream2 = fileOutputStream;
            LogUtil.b("MedalSyncPolicyControl", "saveImage FileNotFoundException");
            d(fileOutputStream2);
            c(openRawResource);
        } catch (IOException unused4) {
            fileOutputStream2 = fileOutputStream;
            LogUtil.b("MedalSyncPolicyControl", "saveImage IOException");
            d(fileOutputStream2);
            c(openRawResource);
        } catch (Throwable th2) {
            th = th2;
            d(fileOutputStream);
            c(openRawResource);
            throw th;
        }
        c(openRawResource);
    }

    private static void d(FileOutputStream fileOutputStream) {
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (IOException unused) {
                LogUtil.b("MedalSyncPolicyControl", "closeStream, IOException");
            }
        }
    }

    public static void c(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
                LogUtil.b("MedalSyncPolicyControl", "closeInputStream IOException,closing failure");
            }
        }
    }

    @Override // com.huawei.health.baseapi.pluginachievement.baseapi.AchieveDeviceApi
    public void sendMessageToDevice(final int i) {
        LogUtil.a("MedalSyncPolicyControl", "sendMessageToDevice enter.");
        iww.b().pingComand(new PingCallback() { // from class: mxo.3
            @Override // com.huawei.health.deviceconnect.callback.PingCallback
            public void onPingResult(int i2) {
                LogUtil.a("MedalSyncPolicyControl", "pingDevice pkg == ", "com.huawei.sport.workout", " code = ", Integer.valueOf(i2));
                if (i2 == 202) {
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: mxo.3.2
                        @Override // java.lang.Runnable
                        public void run() {
                            mxo.this.a(i, "");
                        }
                    }, 200L);
                }
            }
        }, "com.huawei.sport.workout");
    }

    @Override // com.huawei.health.baseapi.pluginachievement.baseapi.AchieveDeviceApi
    public void sendMessageToDevice(int i, String str, String str2, BaseResponseCallback<Boolean> baseResponseCallback) {
        LogUtil.a("MedalSyncPolicyControl", "sendMessageToDevice enter.");
        boolean d = iws.d(155);
        LogUtil.a("MedalSyncPolicyControl", "sendMessageToDevice isSupportSportDevice: ", Boolean.valueOf(d));
        if (d) {
            if (!new File(spz.a(str)).exists()) {
                LogUtil.h("MedalSyncPolicyControl", "checkMedalPath medalId ", str, " isPngExists false!");
                baseResponseCallback.onResponse(0, false);
            } else {
                baseResponseCallback.onResponse(0, true);
                c(str);
            }
        } else {
            baseResponseCallback.onResponse(0, false);
        }
        if (str2.length() < 3 || mlb.n(str2)) {
            e(i, str, "com.huawei.sport.workout");
        }
    }

    private void c(final String str) {
        iws.c().pingComand(new PingCallback() { // from class: mxo.4
            @Override // com.huawei.health.deviceconnect.callback.PingCallback
            public void onPingResult(int i) {
                LogUtil.a("MedalSyncPolicyControl", "pingMessageDevice pkg == ", "com.huawei.sport.dailytrack", " code = ", Integer.valueOf(i));
                if (i != 202) {
                    LogUtil.h("MedalSyncPolicyControl", "pingMessageDevice fail!");
                }
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: mxo.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        mxo.this.a(5, str);
                    }
                }, 200L);
            }
        }, "com.huawei.sport.dailytrack");
    }

    private void e(final int i, final String str, final String str2) {
        iww.b().pingComand(new PingCallback() { // from class: mxo.7
            @Override // com.huawei.health.deviceconnect.callback.PingCallback
            public void onPingResult(int i2) {
                LogUtil.a("MedalSyncPolicyControl", "pingDevice pkg == ", str2, " code = ", Integer.valueOf(i2));
                if (i2 != 202) {
                    LogUtil.h("MedalSyncPolicyControl", "pingDevice fail!");
                }
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: mxo.7.1
                    @Override // java.lang.Runnable
                    public void run() {
                        mxo.this.a(i, str);
                    }
                }, 200L);
            }
        }, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, String str) {
        LogUtil.h("MedalSyncPolicyControl", "startSync，action == ", Integer.valueOf(i));
        if (i == 2) {
            onLightMedalStart(new lmz(b(), i, 1));
            return;
        }
        if (i == 3) {
            c(i);
        } else {
            if (i != 5) {
                return;
            }
            lmz lmzVar = new lmz(b(), i, 1);
            lmzVar.d(str);
            onLightMedalMessageListStart(lmzVar);
        }
    }

    private String b() {
        String replaceAll = UUID.randomUUID().toString().replaceAll(Constants.LINK, "");
        if (TextUtils.isEmpty(replaceAll)) {
            replaceAll = Constant.BROWSE_HUID;
        }
        return replaceAll.length() > 10 ? replaceAll.substring(0, 10) : replaceAll;
    }

    private void c(int i) {
        if (this.d == 0 || c()) {
            lmz lmzVar = new lmz(b(), i, 1);
            LogUtil.a("MedalSyncPolicyControl", "Start Syncing..");
            this.d = 1;
            onSyncMedalsStart(lmzVar);
            return;
        }
        LogUtil.h("MedalSyncPolicyControl", "Last Sync Task Is Not Finish!");
    }

    private boolean c() {
        if (this.c == 0 || System.currentTimeMillis() - this.c <= OpAnalyticsConstants.H5_LOADING_DELAY) {
            return false;
        }
        LogUtil.h("MedalSyncPolicyControl", "Last Sync Task isSyncOutTime 0.5mins.");
        this.c = 0L;
        return true;
    }

    @Override // com.huawei.policy.MedalSyncPolicy
    public void receiveFromDevice(int i, spn spnVar) {
        String str;
        int i2;
        LogUtil.a("MedalSyncPolicyControl", "receiveFromDevice errorCode ", Integer.valueOf(i));
        if (i != 530003) {
            return;
        }
        if (spnVar == null || spnVar.b() == null) {
            LogUtil.b("MedalSyncPolicyControl", "receiveFromDevice Message is null");
            return;
        }
        try {
            str = new String(spnVar.b(), "UTF_8");
        } catch (UnsupportedEncodingException unused) {
            LogUtil.b("MedalSyncPolicyControl", "receiveFromDevice get message data error!");
            str = "";
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        LogUtil.a("MedalSyncPolicyControl", "receiveFromDevice Message == ", str);
        try {
            i2 = mdn.e("action", new JSONObject(str));
        } catch (JSONException e2) {
            LogUtil.b("MedalSyncPolicyControl", "receiveFromDevice Exception:", e2.getMessage());
            i2 = 0;
        }
        LogUtil.a("MedalSyncPolicyControl", "receiveFromDevice action == ", Integer.valueOf(i2));
        if (i2 == 1) {
            onLightMedalListStart((lmy) new Gson().fromJson(str, lmv.class));
            return;
        }
        if (i2 == 2) {
            onLightMedalStart((lmy) new Gson().fromJson(str, lmv.class));
            return;
        }
        if (i2 == 3) {
            onSyncMedalsStart((lmy) new Gson().fromJson(str, lmv.class));
        } else if (i2 == 4) {
            onLaunchPageStart((lmy) new Gson().fromJson(str, lmv.class));
        } else {
            if (i2 != 5) {
                return;
            }
            onLightMedalMessageListStart((lmy) new Gson().fromJson(str, lmw.class));
        }
    }

    @Override // com.huawei.policy.MedalSyncPolicy
    public void onSyncMedalsStart(lmy lmyVar) {
        ThreadPoolManager.d().execute(new nii(lmyVar));
    }

    @Override // com.huawei.policy.MedalSyncPolicy
    public void onLightMedalStart(lmy lmyVar) {
        ThreadPoolManager.d().execute(new nil(lmyVar));
    }

    @Override // com.huawei.policy.MedalSyncPolicy
    public void onLightMedalListStart(lmy lmyVar) {
        if (lmyVar == null) {
            LogUtil.h("MedalSyncPolicyControl", "basicMessage is null");
            return;
        }
        LogUtil.a("MedalSyncPolicyControl", "State ", Integer.valueOf(lmyVar.a()), " mSyncStatus == ", Integer.valueOf(this.d));
        if (lmyVar.a() == 1 && this.d == 1) {
            lmyVar.d(401);
        }
        ThreadPoolManager.d().execute(new nij(lmyVar));
    }

    @Override // com.huawei.policy.MedalSyncPolicy
    public void onLightMedalMessageListStart(lmy lmyVar) {
        ThreadPoolManager.d().execute(new nih(lmyVar));
    }

    @Override // com.huawei.policy.MedalSyncPolicy
    public void onLaunchPageStart(lmy lmyVar) {
        ThreadPoolManager.d().execute(new nik(lmyVar));
    }

    public void e(int i) {
        this.d = i;
    }

    @Override // com.huawei.health.baseapi.pluginachievement.baseapi.AchieveDeviceApi
    public void sendMessageToDeviceForPackage(final int i) {
        LogUtil.a("MedalSyncPolicyControl", "sendMessageToDeviceForPackage enter.");
        if (i == 1 || i == 2) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: mxo.8
                @Override // java.lang.Runnable
                public void run() {
                    mxo.this.a(i);
                }
            });
        } else {
            if (i != 3) {
                return;
            }
            ThreadPoolManager.d().execute(new Runnable() { // from class: mxo.6
                @Override // java.lang.Runnable
                public void run() {
                    mxo.this.e();
                }
            });
        }
    }

    @Override // com.huawei.health.baseapi.pluginachievement.baseapi.AchieveDeviceApi
    public void dealAchieveTrackData(ArrayList<TrackData> arrayList) {
        lmu.e(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        List<mcz> b = meh.c(BaseApplication.e()).b(9, new HashMap(2));
        if (b == null) {
            return;
        }
        LogUtil.a("MedalSyncPolicyControl", "medalConfigInfos size ", Integer.valueOf(b.size()));
        for (mcz mczVar : b) {
            if (mczVar instanceof MedalConfigInfo) {
                MedalConfigInfo medalConfigInfo = (MedalConfigInfo) mczVar;
                if (medalConfigInfo.acquireMedalType().length() < 3) {
                    String acquireMedalID = medalConfigInfo.acquireMedalID();
                    if (mfg.b(acquireMedalID) <= 19) {
                        d(acquireMedalID);
                    }
                }
            }
        }
    }

    private void d(String str) {
        Task<mfo> obtainMedalInfo = uu.d().obtainMedalInfo(str);
        if (obtainMedalInfo == null) {
            LogUtil.h("MedalSyncPolicyControl", "startSyncMedalsForPackage getMedalConfig is null");
        } else {
            obtainMedalInfo.addOnSuccessListener(new OnSuccessListener<mfo>() { // from class: mxo.10
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public void onSuccess(mfo mfoVar) {
                    LogUtil.a("MedalSyncPolicyControl", "startSyncMedalsForPackage zipPath == ", spz.b(mfoVar, Constant.BROWSE_HUID));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final int i) {
        if (i == 1) {
            uu.d().obtainMedalGainStatus(null).addOnSuccessListener(new OnSuccessListener<ArrayList<mfs>>() { // from class: mxo.5
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void onSuccess(ArrayList<mfs> arrayList) {
                    if (koq.b(arrayList)) {
                        return;
                    }
                    LogUtil.a("MedalSyncPolicyControl", "lightlist zipPath dazhuang ", spz.e(spz.b, i, arrayList, Constant.BROWSE_HUID));
                }
            });
        } else {
            if (i != 2) {
                return;
            }
            uu.d().obtainCurrentMedalGainStatus(null).addOnSuccessListener(new OnSuccessListener<ArrayList<mfs>>() { // from class: mxo.9
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void onSuccess(ArrayList<mfs> arrayList) {
                    if (koq.b(arrayList)) {
                        return;
                    }
                    LogUtil.a("MedalSyncPolicyControl", "light zipPath dazhuang ", spz.e(spz.b, i, arrayList, Constant.BROWSE_HUID));
                }
            });
        }
    }
}
