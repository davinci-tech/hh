package defpackage;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.BadParcelableException;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.os.RemoteException;
import android.text.TextUtils;
import android.widget.RemoteViews;
import androidx.core.app.NotificationManagerCompat;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huawei.android.app.ActivityManagerEx;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.constant.TransferBusinessType;
import com.huawei.health.devicemgr.business.constant.TransferFileReqType;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.SyncMusicBinder;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicFolderItem;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicFolderStruct;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicHistoryCallbackInterface;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicIndexCallbackInterface;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicSong;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicStruct;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.NegotiationCallbackInterface;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.NegotiationData;
import com.huawei.hwdevicemgr.R$string;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.HwMusicMgrCallback;
import com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback;
import com.huawei.hwservicesmgr.IBaseCallback;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.LanguageUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.LogAnonymous;
import health.compact.a.NotificationUtil;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jjt {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f13901a = new Object();
    private static jjt b;
    private SyncMusicBinder e;
    private String f;
    private RemoteViews q;
    private NotificationManager r;
    private Notification t;
    private String x;
    private ArrayList<MusicSong> l = new ArrayList<>(16);
    private ArrayList<MusicSong> i = new ArrayList<>(16);
    private boolean o = true;
    private int s = 10000;
    private CopyOnWriteArrayList<MusicSong> w = new CopyOnWriteArrayList<>();
    private ArrayList<MusicSong> y = new ArrayList<>(16);
    private MusicSong h = new MusicSong();
    private int j = 0;
    private boolean n = true;
    private Map<String, IBaseCallback> d = new HashMap(16);
    private boolean k = false;
    private String p = "";
    private IAppTransferFileResultAIDLCallback v = new IAppTransferFileResultAIDLCallback.Stub() { // from class: jjt.4
        @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
        public void onFileTransferState(int i, String str) {
            jjt.this.e(i);
        }

        @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
        public void onUpgradeFailed(int i, String str) {
            if (jjt.this.h != null) {
                ReleaseLogUtil.e("R_Music_SyncMusicManager", "sync the music UpgradeFailed, songName:", jjt.this.h.getSongName(), ";errorCode:", Integer.valueOf(i));
            }
            jjt.this.d(i, str);
        }

        @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
        public void onFileRespond(int i, String str) {
            ReleaseLogUtil.e("R_Music_SyncMusicManager", "onFileRespond: checkResult:", Integer.valueOf(i), ";songName:", jjt.this.h.getSongName());
            if (i == 1) {
                jjt.this.l.add(jjt.this.h);
                jjt.this.ah();
                jjt.this.c(0, "success");
            } else {
                jjt.this.i.add(jjt.this.h);
                jjt.this.c(1, "on file respond sync fail.");
            }
            jjt.this.am();
        }
    };
    private IAppTransferFileResultAIDLCallback c = new IAppTransferFileResultAIDLCallback.Stub() { // from class: jjt.2
        @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
        public void onFileTransferState(int i, String str) {
            LogUtil.a("SyncMusicManager", "cancelAllTask, onFileTransferState ", Integer.valueOf(i));
        }

        @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
        public void onUpgradeFailed(int i, String str) {
            LogUtil.a("SyncMusicManager", "cancelAllTask, onUpgradeFailed errorCode:", Integer.valueOf(i));
        }

        @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
        public void onFileRespond(int i, String str) {
            LogUtil.a("SyncMusicManager", "cancelTransferMusic response checkResult:" + i);
            if (i == 20003) {
                jjt.this.aj();
            }
        }
    };
    private HwMusicMgrCallback m = new HwMusicMgrCallback.Stub() { // from class: jjt.1
        @Override // com.huawei.hwservicesmgr.HwMusicMgrCallback
        public void executeCommand(String str, IBaseCallback iBaseCallback) throws RemoteException {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String optString = jSONObject.optString(ParsedFieldTag.ACTION_TYPE);
                ReleaseLogUtil.e("R_Music_SyncMusicManager", "executeCommand actionType:", optString);
                jjt.this.a(optString, str, iBaseCallback, jSONObject);
            } catch (RemoteException unused) {
                ReleaseLogUtil.c("R_Music_SyncMusicManager", "executeCommand RemoteException");
                iBaseCallback.onResponse(1, "RemoteException");
            } catch (JSONException unused2) {
                ReleaseLogUtil.c("R_Music_SyncMusicManager", "executeCommand JSONException");
                iBaseCallback.onResponse(1, "JSONException");
            }
        }
    };
    private IBaseCallback.Stub u = new IBaseCallback.Stub() { // from class: jjt.5
        @Override // com.huawei.hwservicesmgr.IBaseCallback
        public void onResponse(int i, String str) {
            LogUtil.a("SyncMusicManager", "mXiMaLaYaManagerCallback, errorCode: ", Integer.valueOf(i), " reason: ", str);
            if (jjt.this.k) {
                jjt.this.q();
            } else {
                LogUtil.a("SyncMusicManager", "mXiMaLaYaManagerCallback mIsSyncMusicTaskRunning is false ");
            }
        }
    };
    private BroadcastReceiver g = new BroadcastReceiver() { // from class: jjt.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            DeviceInfo deviceInfo;
            if (intent == null) {
                LogUtil.h("SyncMusicManager", "mConnectStateChangedReceiver, intent is null");
                return;
            }
            LogUtil.a("SyncMusicManager", "mConnectStateChangedReceiver intent : " + intent.getAction());
            if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                try {
                    if (!(intent.getParcelableExtra("deviceinfo") instanceof DeviceInfo) || (deviceInfo = (DeviceInfo) intent.getParcelableExtra("deviceinfo")) == null) {
                        return;
                    }
                    if (deviceInfo.getRelationship() == null || !"followed_relationship".equals(deviceInfo.getRelationship())) {
                        jjt.this.e(deviceInfo);
                    } else {
                        LogUtil.a("SyncMusicManager", "This device does not have the correspond capability.");
                    }
                } catch (BadParcelableException e) {
                    LogUtil.b("SyncMusicManager", "BadParcelableException:", ExceptionUtils.d(e));
                } catch (ClassCastException e2) {
                    LogUtil.b("SyncMusicManager", "ClassCastException:", ExceptionUtils.d(e2));
                }
            }
        }
    };

    private jjt() {
        ae();
        jez.b(new IBaseResponseCallback() { // from class: jjv
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                jjt.this.d(i, obj);
            }
        });
    }

    /* synthetic */ void d(int i, Object obj) {
        SyncMusicBinder syncMusicBinder = this.e;
        if (syncMusicBinder != null) {
            syncMusicBinder.notifyServiceCancel();
            this.e.notifyUiAllMusicsFinish();
        }
        c();
        jfq.c().d(40);
    }

    public static jjt e() {
        jjt jjtVar;
        synchronized (f13901a) {
            if (b == null) {
                b = new jjt();
            }
            jjtVar = b;
        }
        return jjtVar;
    }

    private void ae() {
        LogUtil.a("SyncMusicManager", "Enter registerConnectStateBroadcast");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        BroadcastManagerUtil.bFC_(BaseApplication.getContext(), this.g, intentFilter, LocalBroadcast.c, null);
    }

    public boolean m() {
        return this.k;
    }

    public void c(SyncMusicBinder syncMusicBinder, String str) {
        this.e = syncMusicBinder;
        this.p = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void a(String str, String str2, IBaseCallback iBaseCallback, JSONObject jSONObject) throws RemoteException {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -1149503253:
                if (str.equals("deleteAudio")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1060322202:
                if (str.equals("getAppState")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1016530986:
                if (str.equals("accountNotify")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -771961760:
                if (str.equals("sendBatchInfo")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -306736071:
                if (str.equals("getPlayedRecords")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 151507034:
                if (str.equals("getAppFolders")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 730585626:
                if (str.equals("isSyncMusicTaskRunning")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1574530674:
                if (str.equals("utilityNegotiate")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1900437811:
                if (str.equals("getDeviceConnectState")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 1957230553:
                if (str.equals("sendSongFile")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 2036285271:
                if (str.equals("cancleTransfer")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 2129008117:
                if (str.equals("getDevicesInfo")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                j(str2, iBaseCallback);
                break;
            case 1:
                d(str2, iBaseCallback);
                break;
            case 2:
                b(str2, iBaseCallback);
                break;
            case 3:
                c(str2, iBaseCallback);
                break;
            case 4:
                e(str2, iBaseCallback);
                break;
            case 5:
                a(str2, iBaseCallback);
                break;
            case 6:
                e(iBaseCallback);
                break;
            case 7:
                d(iBaseCallback);
                break;
            case '\b':
                a(iBaseCallback);
                break;
            case '\t':
                e(jSONObject);
                this.d.put(jSONObject.optString("musicId"), iBaseCallback);
                break;
            case '\n':
                a();
                break;
            case 11:
                c(iBaseCallback);
                break;
            default:
                iBaseCallback.onResponse(500001, "");
                break;
        }
    }

    private void a(IBaseCallback iBaseCallback) {
        try {
            if (w() == 0) {
                iBaseCallback.onResponse(0, "device connected");
            } else {
                iBaseCallback.onResponse(1, "device disconnect");
            }
        } catch (RemoteException unused) {
            LogUtil.b("SyncMusicManager", "setDeviceConnectState RemoteException");
        }
    }

    private String y() {
        DeviceInfo a2 = jpt.a("SyncMusicManager");
        if (a2 != null) {
            return a2.getDeviceIdentify();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            ReleaseLogUtil.d("R_Music_SyncMusicManager", "deviceInfo is null when dealNewConnectState.");
            return;
        }
        int deviceConnectState = deviceInfo.getDeviceConnectState();
        if (deviceConnectState == 1) {
            LogUtil.a("SyncMusicManager", "DEVICE_CONNECTING");
            return;
        }
        if (deviceConnectState != 2) {
            if (deviceConnectState == 3) {
                LogUtil.a("SyncMusicManager", "DEVICE_DISCONNECTED. device: ", blt.a(deviceInfo.getDeviceIdentify()));
                this.p = deviceInfo.getDeviceIdentify();
                return;
            } else {
                LogUtil.h("SyncMusicManager", "DEVICE_CONNECT_STATUS_OTHER");
                return;
            }
        }
        ReleaseLogUtil.e("R_Music_SyncMusicManager", "DEVICE_CONNECTED, mFailedTransferMusicList size:", Integer.valueOf(this.i.size()));
        String y = y();
        if (y != null && !y.equals(deviceInfo.getDeviceIdentify())) {
            LogUtil.a("SyncMusicManager", "onReceive cancelCurrentTransferTask");
            a();
            return;
        }
        if (!TextUtils.isEmpty(this.p) && !TextUtils.equals(deviceInfo.getDeviceIdentify(), this.p)) {
            LogUtil.a("SyncMusicManager", "not the same device. Cancel Transfer. LastDeviceIdentify = ", blt.a(this.p), ", now device = ", blt.a(deviceInfo.getDeviceIdentify()));
            a();
            this.p = deviceInfo.getDeviceIdentify();
            return;
        }
        this.p = deviceInfo.getDeviceIdentify();
        if (this.i.size() == 0) {
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        arrayList.addAll(this.i);
        v();
        ag();
        SyncMusicBinder syncMusicBinder = this.e;
        if (syncMusicBinder != null) {
            syncMusicBinder.notifyUiIsSyncMusicTaskRunning();
        }
        this.w.addAll(arrayList);
        this.y.addAll(arrayList);
        ThreadPoolManager.d().execute(new Runnable() { // from class: jjp
            @Override // java.lang.Runnable
            public final void run() {
                jjt.this.n();
            }
        });
    }

    /* synthetic */ void n() {
        a(this.w);
    }

    private int w() {
        DeviceInfo a2 = jpt.a("SyncMusicManager");
        return (a2 != null && a2.getDeviceConnectState() == 2) ? 0 : 1;
    }

    private void e(IBaseCallback iBaseCallback) {
        try {
            if (this.k) {
                iBaseCallback.onResponse(1, "music task running");
            } else {
                iBaseCallback.onResponse(0, "music task stop");
            }
        } catch (RemoteException unused) {
            LogUtil.b("SyncMusicManager", "sendIsTaskRunningToDevice RemoteException");
        }
    }

    private void j(String str, IBaseCallback iBaseCallback) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            ArrayList arrayList = new ArrayList(16);
            arrayList.add(Integer.valueOf(jSONObject.optInt("operation_type")));
            arrayList.add(Integer.valueOf(jSONObject.optInt("folder_index")));
            LogUtil.a("SyncMusicManager", "operation type is:", arrayList.get(0), "folder index is:", arrayList.get(1));
            if (arrayList.isEmpty()) {
                LogUtil.h("SyncMusicManager", "audioInfo is null");
                iBaseCallback.onResponse(2, "");
            } else {
                jjd.b(BaseApplication.getContext()).b(arrayList);
                iBaseCallback.onResponse(0, "");
            }
        } catch (RemoteException unused) {
            LogUtil.b("SyncMusicManager", "sendMusicDeleteAudioInfo RemoteException");
        } catch (JSONException unused2) {
            LogUtil.b("SyncMusicManager", "sendMusicDeleteAudioInfo JSONException");
        }
    }

    private void d(final String str, final IBaseCallback iBaseCallback) {
        jiw.a().e(new IBaseResponseCallback() { // from class: jjx
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                jjt.this.b(str, iBaseCallback, i, obj);
            }
        });
    }

    /* synthetic */ void b(String str, IBaseCallback iBaseCallback, int i, Object obj) {
        jiz jizVar;
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = new JSONObject();
            String optString = jSONObject.optString("package");
            if (obj != null && !"[]".equals(obj) && !TextUtils.isEmpty(optString)) {
                List list = (List) HiJsonUtil.b(HiJsonUtil.e(obj), new TypeToken<List<jiz>>() { // from class: jjt.7
                }.getType());
                if (list == null) {
                    LogUtil.h("SyncMusicManager", "getAppInstallInfo installAppList is null");
                    c(jSONObject2, 0, "");
                    iBaseCallback.onResponse(0, jSONObject2.toString());
                    return;
                }
                Iterator it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        jizVar = null;
                        break;
                    }
                    jizVar = (jiz) it.next();
                    if (jizVar == null) {
                        LogUtil.h("SyncMusicManager", "getAppInstallInfo, installAppInfo is null");
                    } else if (jizVar.e() != null && jizVar.e().contains(optString)) {
                        LogUtil.c("SyncMusicManager", "getAppInstallInfo, break");
                        break;
                    }
                }
                if (jizVar != null) {
                    c(jSONObject2, 1, jizVar.e());
                } else {
                    LogUtil.h("SyncMusicManager", "getAppInstallInfo, installAppItem is null");
                    c(jSONObject2, 0, "");
                }
                iBaseCallback.onResponse(0, jSONObject2.toString());
                return;
            }
            LogUtil.h("SyncMusicManager", "getAppInstallInfo result data error");
            c(jSONObject2, 0, "");
            iBaseCallback.onResponse(0, jSONObject2.toString());
        } catch (RemoteException | JSONException unused) {
            LogUtil.b("SyncMusicManager", "getAppInstallInfo JSONException or RemoteException");
        }
    }

    private void c(JSONObject jSONObject, int i, String str) throws JSONException {
        jSONObject.putOpt("packageInstall", Integer.valueOf(i));
        jSONObject.putOpt("version", str);
    }

    public void a() {
        LogUtil.a("SyncMusicManager", "TESTMUSIC cancelCurrentTransferTask");
        if (TextUtils.isEmpty(this.h.getFileName())) {
            LogUtil.h("SyncMusicManager", "mCurrentSyncMusic.getFileName()) is empty.");
            return;
        }
        jqj jqjVar = new jqj();
        jqjVar.a(this.h.getFileName());
        jqjVar.d(2);
        jqjVar.c(TransferFileReqType.APP_STOP);
        jqjVar.c((String) null);
        jfq.c().d(TransferBusinessType.FIVE_FORTY, jqjVar, this.c);
        aj();
        u();
        e(false);
    }

    private void c(IBaseCallback iBaseCallback) {
        DeviceCapability e;
        List<DeviceInfo> b2 = jpt.b("SyncMusicManager");
        try {
            if (b2 == null) {
                if (iBaseCallback != null) {
                    iBaseCallback.onResponse(1, "no connected devices");
                    return;
                }
                return;
            }
            JSONArray jSONArray = new JSONArray();
            for (DeviceInfo deviceInfo : b2) {
                if (deviceInfo != null && (e = cvs.e(deviceInfo.getDeviceIdentify())) != null && e.isSupportMusicInfoList() && !jjj.d()) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("deviceid", deviceInfo.getDeviceIdentify());
                    jSONObject.put("devicename", deviceInfo.getDeviceName());
                    jSONObject.put("expandCapability", deviceInfo.getExpandCapability());
                    jSONArray.put(jSONObject);
                }
            }
            if (jSONArray.length() > 0) {
                iBaseCallback.onResponse(0, jSONArray.toString());
            } else {
                iBaseCallback.onResponse(1, "no connected devices");
            }
        } catch (RemoteException unused) {
            LogUtil.b("SyncMusicManager", "getDevicesInfo RemoteException");
        } catch (JSONException unused2) {
            LogUtil.b("SyncMusicManager", "getDevicesInfo JSONException");
        }
    }

    private void c(String str, IBaseCallback iBaseCallback) {
        LogUtil.a("SyncMusicManager", "saveListMusicInfo enter");
        e(true);
        try {
            JSONObject jSONObject = new JSONObject(str);
            ArrayList<MusicSong> arrayList = new ArrayList<>(16);
            int optInt = jSONObject.optInt("musicAppType");
            String optString = jSONObject.optString("package");
            JSONArray jSONArray = jSONObject.getJSONArray("inputDescription");
            for (int i = 0; i < jSONArray.length(); i++) {
                MusicSong musicSong = new MusicSong();
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                if (jSONObject2 != null) {
                    String optString2 = jSONObject2.optString("musicId");
                    try {
                        musicSong.setSongFilePath(BaseApplication.getContext().getFilesDir().getCanonicalPath() + File.separator + optString2);
                    } catch (IOException unused) {
                        LogUtil.b("SyncMusicManager", "setSongPath IOException");
                    }
                    musicSong.setMusicId(optString2);
                    musicSong.setFileName(optString2);
                    musicSong.setSongName(jSONObject2.optString("musicName"));
                    musicSong.setSongSingerName(jSONObject2.optString("musicSinger"));
                    musicSong.setAlbumName(jSONObject2.optString("musicAlbum"));
                    musicSong.setAccountName(jSONObject2.optString("accountName"));
                    musicSong.setMusicType(jSONObject2.optInt("musicType"));
                    musicSong.setMusicAppType(optInt);
                    musicSong.setMusicCopyRight(jSONObject2.optInt("musicCopyright"));
                    musicSong.setMusicDuration(jSONObject2.optInt("musicDuration"));
                    c(musicSong, jSONObject2, optString);
                    arrayList.add(musicSong);
                    LogUtil.a("SyncMusicManager", "musicSong type:", Integer.valueOf(musicSong.getMusicType()));
                }
            }
            e(arrayList, iBaseCallback);
        } catch (JSONException unused2) {
            LogUtil.b("SyncMusicManager", "sendDeviceCommand JSONException");
        }
    }

    private void e(boolean z) {
        if (!bky.b()) {
            LogUtil.a("SyncMusicManager", "The device is not provided by Harmony.");
            return;
        }
        int myPid = Process.myPid();
        if (myPid != 0) {
            ReleaseLogUtil.e("SyncMusicManager", "PhoneService set Processing ：", Boolean.valueOf(z));
            try {
                ActivityManagerEx.setProcessForeground(new Binder(), myPid, z);
                LogUtil.a("SyncMusicManager", "setProcessForeground end.");
            } catch (Exception e) {
                LogUtil.b("SyncMusicManager", "setProcessForeground fail ", e.getMessage());
            }
        }
    }

    private void d(final IBaseCallback iBaseCallback) {
        LogUtil.a("SyncMusicManager", "getDeviceAndMusicType enter");
        jjd.b(BaseApplication.getContext()).b((NegotiationData) null, new NegotiationCallbackInterface() { // from class: jjy
            @Override // com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.NegotiationCallbackInterface
            public final void onGetNegotiationData(NegotiationData negotiationData, int i) {
                jjt.e(IBaseCallback.this, negotiationData, i);
            }
        });
    }

    static /* synthetic */ void e(IBaseCallback iBaseCallback, NegotiationData negotiationData, int i) {
        LogUtil.a("SyncMusicManager", "onGetNegotiationData rsp,errorCode:", Integer.valueOf(i));
        try {
            if (negotiationData == null) {
                iBaseCallback.onResponse(1, "");
                LogUtil.h("SyncMusicManager", "negotiationData is empty");
                return;
            }
            if (i == 100000) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("storageSpace", negotiationData.getStorageSpace());
                JSONArray jSONArray = new JSONArray();
                Iterator<String> it = negotiationData.getMusicFormatList().iterator();
                while (it.hasNext()) {
                    jSONArray.put(it.next());
                }
                jSONObject.put("musicFormat", jSONArray);
                jSONObject.put("musicQuality", negotiationData.getMusicQuality());
                jSONObject.put("typeStruct", new Gson().toJson(negotiationData.getTypeStructList()));
                String jSONObject2 = jSONObject.toString();
                LogUtil.a("SyncMusicManager", "onGetNegotiationData, jsonString :", jSONObject2);
                iBaseCallback.onResponse(0, jSONObject2);
                return;
            }
            iBaseCallback.onResponse(1, "");
        } catch (RemoteException unused) {
            LogUtil.b("SyncMusicManager", "getDeviceAndMusicType RemoteException");
        } catch (JSONException unused2) {
            LogUtil.b("SyncMusicManager", "getDeviceAndMusicType JSONException");
        }
    }

    private void u() {
        ContentProviderClient contentProviderClient;
        LogUtil.a("SyncMusicManager", "getBundleFromHwMusic enter");
        ContentResolver contentResolver = BaseApplication.getContext().getContentResolver();
        ContentProviderClient contentProviderClient2 = null;
        try {
            try {
                if (contentResolver != null) {
                    contentProviderClient = contentResolver.acquireUnstableContentProviderClient(Uri.parse("content://com.android.mediacenter.healthprovider/InfoForWatch"));
                    try {
                        if (contentProviderClient != null) {
                            contentResolver.call(Uri.parse("content://com.android.mediacenter.healthprovider/InfoForWatch"), "cancleTransfer", (String) null, (Bundle) null);
                        } else {
                            LogUtil.h("SyncMusicManager", "getBundleFromHwMusic, client is null.");
                        }
                        contentProviderClient2 = contentProviderClient;
                    } catch (RuntimeException unused) {
                        contentProviderClient2 = contentProviderClient;
                        LogUtil.b("SyncMusicManager", "getBundleFromHwMusic DeadObjectException");
                        if (contentProviderClient2 == null) {
                            return;
                        }
                        contentProviderClient2.close();
                    } catch (Throwable th) {
                        th = th;
                        if (contentProviderClient != null) {
                            contentProviderClient.close();
                        }
                        throw th;
                    }
                } else {
                    LogUtil.a("SyncMusicManager", "getBundleFromHwMusic: resolver is null");
                }
                if (contentProviderClient2 == null) {
                    return;
                }
            } catch (Throwable th2) {
                th = th2;
                contentProviderClient = null;
            }
        } catch (RuntimeException unused2) {
        }
        contentProviderClient2.close();
    }

    private void a(String str, final IBaseCallback iBaseCallback) {
        LogUtil.a("SyncMusicManager", "getMusicFolderList enter");
        try {
            jjd.b(BaseApplication.getContext()).d(new JSONObject(str).getString("package"), new IBaseResponseCallback() { // from class: jjr
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    jjt.this.a(iBaseCallback, i, obj);
                }
            });
        } catch (JSONException unused) {
            LogUtil.b("SyncMusicManager", "getMusicFolderList JSONException");
        }
    }

    /* synthetic */ void a(IBaseCallback iBaseCallback, int i, Object obj) {
        LogUtil.a("SyncMusicManager", "getMusicFolderList errorCode:", Integer.valueOf(i));
        if (koq.e(obj, MusicFolderStruct.class)) {
            try {
                iBaseCallback.onResponse(0, new Gson().toJson(e((List<MusicFolderStruct>) obj), new TypeToken<List<MusicFolderItem>>() { // from class: jjt.8
                }.getType()));
            } catch (RemoteException unused) {
                LogUtil.b("SyncMusicManager", "getMusicFolderList RemoteException");
            }
        }
    }

    private void e(String str, final IBaseCallback iBaseCallback) {
        LogUtil.a("SyncMusicManager", "getPlayedRecords enter");
        try {
            JSONObject jSONObject = new JSONObject(str);
            jjd.b(BaseApplication.getContext()).b(new MusicHistoryCallbackInterface() { // from class: jjt.10
                @Override // com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicHistoryCallbackInterface
                public void onGetHistoryRecords(int i, String str2, List<jka> list) {
                    LogUtil.a("SyncMusicManager", "onGetHistoryRecords req");
                    jjt.this.e(i, str2, list, iBaseCallback);
                }

                @Override // com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicHistoryCallbackInterface
                public void getHistoryRecordsBusy(int i, String str2, int i2) {
                    LogUtil.a("SyncMusicManager", "getHIstoryRecordsBusy response");
                    try {
                        IBaseCallback iBaseCallback2 = iBaseCallback;
                        if (iBaseCallback2 != null) {
                            iBaseCallback2.onResponse(1, null);
                        }
                    } catch (RemoteException unused) {
                        LogUtil.b("SyncMusicManager", "getHIstoryRecordsBusy IWearAIDL registerCallback exception.");
                    }
                }
            }, jSONObject.optInt("musicAppType"), jSONObject.optString("accountInfo"));
        } catch (JSONException unused) {
            LogUtil.b("SyncMusicManager", "JSONException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, String str, List<jka> list, IBaseCallback iBaseCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("musicAppType", i);
            jSONObject.put("accountInfo", str);
            JSONArray jSONArray = new JSONArray();
            for (jka jkaVar : list) {
                if (jkaVar != null) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("hitoryMusicId", jkaVar.b());
                    jSONObject2.put("historyPlayTime", jkaVar.c());
                    jSONObject2.put("historyPlayedTime", jkaVar.a());
                    jSONObject2.put("musicDuration", jkaVar.e());
                    jSONArray.put(jSONObject2);
                }
            }
            jSONObject.put("historyList", jSONArray);
            String jSONObject3 = jSONObject.toString();
            LogUtil.a("SyncMusicManager", "onGetHistoryRecords, accountInfo:", str);
            if (iBaseCallback != null) {
                iBaseCallback.onResponse(0, jSONObject3);
            }
        } catch (RemoteException unused) {
            LogUtil.b("SyncMusicManager", "IWearAIDL registerCallback RemoteException");
        } catch (JSONException unused2) {
            LogUtil.b("SyncMusicManager", "JSONException");
        }
    }

    private void b(String str, IBaseCallback iBaseCallback) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            jjz jjzVar = new jjz();
            jjzVar.b(jSONObject.optInt("accountDeadline"));
            jjzVar.d(jSONObject.optString("accountInfo"));
            jjzVar.d(jSONObject.optInt("accountNotifyType"));
            jjzVar.a(jSONObject.optInt("musicAppType"));
            LogUtil.a("SyncMusicManager", "sendMusicAccountInfo by bluetooth, appType:", Integer.valueOf(jjzVar.c()));
            jjd.b(BaseApplication.getContext()).c(jjzVar);
            iBaseCallback.onResponse(0, "");
        } catch (RemoteException unused) {
            LogUtil.b("SyncMusicManager", "sendMusicAccountInfo RemoteException");
        } catch (JSONException unused2) {
            LogUtil.b("SyncMusicManager", "sendMusicAccountInfo JSONException");
        }
    }

    private List<MusicFolderItem> e(List<MusicFolderStruct> list) {
        ArrayList arrayList = new ArrayList(16);
        for (MusicFolderStruct musicFolderStruct : list) {
            if (musicFolderStruct != null) {
                MusicFolderItem musicFolderItem = new MusicFolderItem();
                musicFolderItem.setFolderName(musicFolderStruct.getFolderName());
                musicFolderItem.setNumber(String.valueOf(musicFolderStruct.getFolderMusicAssociationFrameCount()));
                arrayList.add(musicFolderItem);
            }
        }
        return arrayList;
    }

    private void e(JSONObject jSONObject) {
        String optString = jSONObject.optString("musicId");
        Iterator<MusicSong> it = this.y.iterator();
        while (it.hasNext()) {
            MusicSong next = it.next();
            if (next != null && next.getMusicId().equals(optString)) {
                next.setMusicEncryption(jSONObject.optInt("musicEncryption"));
                next.setMusicKey(jSONObject.optString("musicKey"));
                next.setMusicIv(jSONObject.optString("musicIV"));
                next.setAccountName(jSONObject.optString("accountName"));
                next.setOnLineMusicCompletedFlag(1);
                LogUtil.a("SyncMusicManager", "saveMusicEncryptionInfo and complete info");
                return;
            }
        }
    }

    private void c(MusicSong musicSong, JSONObject jSONObject, String str) {
        String optString = jSONObject.optString(MusicSong.SORT_TYPE_FOLDER);
        if (!TextUtils.isEmpty(optString)) {
            musicSong.setMusicFolder(optString);
        }
        String optString2 = jSONObject.optString("albumId");
        if (!TextUtils.isEmpty(optString2)) {
            musicSong.setMusicAlbumId(optString2);
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        musicSong.setPackageName(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ah() {
        if (this.e == null || this.h.getMusicAppType() == 2) {
            return;
        }
        this.e.notifyUiCurrentMusicFinish();
    }

    private void af() {
        if (this.e == null || this.h.getMusicAppType() == 2) {
            return;
        }
        this.e.notifyUiAllMusicsFinish();
    }

    private void ai() {
        if (this.e == null || this.h.getMusicAppType() == 2) {
            return;
        }
        this.e.notifyUiBlueToothDisconnect();
    }

    public void c(ArrayList<MusicSong> arrayList) {
        this.w.removeAll(arrayList);
        this.w.addAll(arrayList);
        this.y.removeAll(arrayList);
        this.y.addAll(arrayList);
        b(this.h.getSongName());
    }

    private void an() {
        jjd.b(BaseApplication.getContext()).e(new MusicIndexCallbackInterface() { // from class: jju
            @Override // com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicIndexCallbackInterface
            public final void onGetMusicFileInfo(int i, String str, int i2) {
                jjt.this.e(i, str, i2);
            }
        });
    }

    /* synthetic */ void e(int i, String str, int i2) {
        LogUtil.a("SyncMusicManager", "receive device's getMusicFileInfo request! musicIndex:", Integer.valueOf(i), "; fileName:", str);
        b(i, str);
    }

    private void b(int i, String str) {
        Iterator<MusicSong> it = this.y.iterator();
        while (it.hasNext()) {
            MusicSong next = it.next();
            try {
            } catch (NumberFormatException unused) {
                ReleaseLogUtil.c("R_Music_SyncMusicManager", "onGetMusicFileInfo error, NumberFormatException");
            }
            if (!next.getFileName().equals(str)) {
                LogUtil.h("SyncMusicManager", "dealOnGetMusicFileInfoRspSuccess return.");
            } else {
                MusicStruct musicStruct = new MusicStruct();
                LogUtil.a("SyncMusicManager", "receive device's getMusicFileInfo request and sendMusicInfo to device!songName:", next.getSongName(), "; singerName: ", next.getSongSingerName(), ",index:", Integer.valueOf(i));
                next.setSongIndex(String.valueOf(i));
                musicStruct.setMusicName(next.getSongName());
                musicStruct.setMusicSinger(next.getSongSingerName());
                musicStruct.setMusicIndex(i);
                if (next.getMusicAppType() != 0) {
                    LogUtil.a("SyncMusicManager", "Not local music!");
                    musicStruct.setMusicIv(next.getMusicIv());
                    musicStruct.setMusicType(next.getMusicType());
                    musicStruct.setMusicCopyright(next.getMusicCopyRight());
                    musicStruct.setMusicEncryption(next.getMusicEncryption());
                    musicStruct.setAccountName(next.getAccountName());
                    musicStruct.setMusicAppType(next.getMusicAppType());
                    musicStruct.setMusicDuration(next.getMusicDuration());
                    musicStruct.setMusicId(next.getMusicId());
                    musicStruct.setFileName(next.getFileName());
                    musicStruct.setMusicKey(next.getMusicKey());
                    musicStruct.setMusicDuration(next.getMusicDuration());
                    musicStruct.setMusicAlbum(next.getAlbumName());
                    musicStruct.setMusicFolder(next.getMusicFolder());
                    musicStruct.setMusicAlbumId(next.getMusicAlbumId());
                    musicStruct.setPackageName(next.getPackageName());
                }
                jjd.b(BaseApplication.getContext()).e(musicStruct);
                return;
            }
        }
        LogUtil.a("SyncMusicManager", "receive device's getMusicFileInfo request, but can't match any file！");
    }

    private void ag() {
        LogUtil.a("SyncMusicManager", "sendNotifyMsgByRemoteView");
        this.q = new RemoteViews(BaseApplication.getContext().getPackageName(), R.layout.add_music_to_device_notify_layout);
        if (Build.VERSION.SDK_INT < 31 || aa()) {
            this.q = new RemoteViews(BaseApplication.getContext().getPackageName(), R.layout.add_music_to_device_notify_less_android13_layout);
        }
        Notification.Builder xf_ = jdh.c().xf_();
        xf_.setContentIntent(NotificationUtil.aOw_(BaseApplication.getContext()));
        xf_.setSmallIcon(R.mipmap._2131820756_res_0x7f1100d4);
        xf_.setShowWhen(true);
        xf_.setWhen(System.currentTimeMillis());
        xf_.setOngoing(true);
        xf_.setOnlyAlertOnce(true);
        xf_.setCustomContentView(this.q);
        xf_.setCustomBigContentView(this.q);
        this.t = xf_.build();
        if (this.e != null && z()) {
            this.e.startForeground(this.s, this.t);
        } else {
            LogUtil.h("SyncMusicManager", "mBinder is null.");
        }
    }

    private void ak() {
        String format;
        LogUtil.a("SyncMusicManager", "showLocalTransferResult. mSyncTaskInitialMusicList.size() = ", Integer.valueOf(this.y.size()), "; mFailedTransferMusicList.size() = ", Integer.valueOf(this.i.size()));
        if (this.y.size() == 0 || this.i.size() > this.y.size()) {
            LogUtil.h("SyncMusicManager", "List nums is wrong.");
            return;
        }
        String quantityString = BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903264_res_0x7f0300e0, this.i.size(), Integer.valueOf(this.i.size()));
        String quantityString2 = BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903264_res_0x7f0300e0, this.y.size() - this.i.size(), Integer.valueOf(this.y.size() - this.i.size()));
        String quantityString3 = BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903257_res_0x7f0300d9, this.i.size(), Integer.valueOf(this.i.size()));
        String quantityString4 = BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903257_res_0x7f0300d9, this.y.size() - this.i.size(), Integer.valueOf(this.y.size() - this.i.size()));
        if (this.i.size() == 0) {
            format = String.format(Locale.ENGLISH, BaseApplication.getContext().getString(R$string.IDS_hw_health_music_add_success_to_watch), quantityString2);
        } else if (this.i.size() == this.y.size()) {
            format = String.format(Locale.ENGLISH, BaseApplication.getContext().getString(R$string.IDS_hw_health_music_add_fail_to_watch), quantityString);
        } else {
            format = String.format(Locale.ENGLISH, BaseApplication.getContext().getString(R$string.IDS_hw_health_music_add_success_and_fail_to_watch), quantityString4, quantityString3);
        }
        c(format);
    }

    private void c(String str) {
        Notification.Builder xf_ = jdh.c().xf_();
        jdh.c().xi_(xf_);
        xf_.setSmallIcon(R.mipmap._2131820756_res_0x7f1100d4);
        xf_.setWhen(System.currentTimeMillis());
        xf_.setShowWhen(false);
        xf_.setOnlyAlertOnce(true);
        xf_.setAutoCancel(true);
        xf_.setContentIntent(NotificationUtil.aOw_(BaseApplication.getContext()));
        xf_.setPriority(0);
        xf_.setGroup("SyncMusicManager");
        xf_.setContentText(str);
        try {
            jdh.c().xh_(10001, xf_.build());
        } catch (SecurityException e) {
            LogUtil.h("SyncMusicManager", "createCommonNotification SecurityException", LogAnonymous.b((Throwable) e));
        }
    }

    public int d() {
        return this.j;
    }

    public String i() {
        return this.f;
    }

    public MusicSong b() {
        return this.h;
    }

    public List<MusicSong> h() {
        return this.i;
    }

    public List<MusicSong> j() {
        LogUtil.a("SyncMusicManager", "getHasTransferMusicList, mHasTransferMusicList size :", Integer.valueOf(this.l.size()));
        return this.l;
    }

    public boolean f() {
        return this.k;
    }

    public HwMusicMgrCallback g() {
        return this.m;
    }

    public IBaseCallback.Stub l() {
        return this.u;
    }

    private void b(String str) {
        this.j = 0;
        this.x = str;
        int size = this.l.size() + this.i.size() + 1;
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            String format = String.format(Locale.ENGLISH, "%d", Integer.valueOf(size));
            this.f = String.format(Locale.ENGLISH, "%d", Integer.valueOf(this.y.size())) + "/" + format;
        } else {
            this.f = String.format(Locale.ENGLISH, size + "/" + this.y.size(), new Object[0]);
        }
        if (EnvironmentInfo.j()) {
            d(this.x);
        } else if (CommonUtil.bm()) {
            LogUtil.a("SyncMusicManager", "Notification IS_UP_MIUI_10");
            a(this.x, this.f);
        } else {
            RemoteViews remoteViews = this.q;
            if (remoteViews != null) {
                remoteViews.setTextViewText(R.id.adding_to_watch_tv, String.format(Locale.ENGLISH, BaseApplication.getContext().getString(R$string.IDS_hw_health_music_adding_to_watch), str));
                this.q.setTextViewText(R.id.total_progress_tv, this.f);
                this.q.setProgressBar(R.id.transfer_music_progress_bar, 100, this.j, false);
                ac();
            } else {
                LogUtil.a("SyncMusicManager", "mRemoteView is null");
            }
        }
        if (this.e != null) {
            if (this.h.getMusicAppType() != 2) {
                this.e.notifyUiCurrentMusicNameAndTotalProgress();
                this.e.notifyUiCurrentMusicProgress();
            }
            this.e.notifyUiIsSyncMusicTaskRunning();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        if (i - this.j >= 1) {
            LogUtil.a("SyncMusicManager", "refreshProgressByResult percentage=", Integer.valueOf(i));
            this.j = i;
            this.q.setProgressBar(R.id.transfer_music_progress_bar, 100, i, false);
            if (EnvironmentInfo.j()) {
                d(this.x);
            } else if (CommonUtil.bm()) {
                LogUtil.a("SyncMusicManager", "Notification IS_UP_MIUI_10,percentage:", Integer.valueOf(i));
                a(this.x, this.f);
            } else {
                ac();
            }
            if (this.e != null && this.h.getMusicAppType() != 2) {
                this.e.notifyUiCurrentMusicProgress();
            }
            c(200, String.valueOf(i));
        }
    }

    private void d(String str) {
        String format = String.format(Locale.ENGLISH, BaseApplication.getContext().getString(R$string.IDS_hw_health_music_adding_to_watch), str);
        Notification.Builder xf_ = jdh.c().xf_();
        xf_.setContentIntent(NotificationUtil.aOw_(BaseApplication.getContext()));
        xf_.setWhen(System.currentTimeMillis());
        xf_.setShowWhen(true);
        xf_.setOnlyAlertOnce(true);
        xf_.setOngoing(true);
        xf_.setProgress(100, this.j, false);
        xf_.setContentTitle(format);
        xf_.setSmallIcon(R.mipmap._2131820756_res_0x7f1100d4);
        xf_.setContentText(BaseApplication.getContext().getString(R$string.IDS_settings_firmware_upgrade_band_transfer_finish) + " " + UnitUtil.e(this.j, 2, 0));
        this.t = xf_.build();
        jdh.c().xh_(this.s, this.t);
    }

    private void a(String str, String str2) {
        LogUtil.a("SyncMusicManager", "notifyXiaomiMessage subText:", str2);
        String format = String.format(Locale.ENGLISH, BaseApplication.getContext().getString(R$string.IDS_hw_health_music_adding_to_watch), str);
        Notification.Builder xf_ = jdh.c().xf_();
        xf_.setContentIntent(NotificationUtil.aOw_(BaseApplication.getContext()));
        xf_.setWhen(System.currentTimeMillis());
        xf_.setShowWhen(true);
        xf_.setOnlyAlertOnce(true);
        xf_.setOngoing(true);
        String e = UnitUtil.e(this.j, 2, 0);
        xf_.setContentTitle(format);
        xf_.setContentText(e);
        xf_.setSubText(str2);
        xf_.setSmallIcon(R.mipmap._2131820756_res_0x7f1100d4);
        xf_.setProgress(100, this.j, false);
        this.t = xf_.build();
        jdh.c().xh_(this.s, this.t);
    }

    private void ac() {
        if (z()) {
            Notification.Builder xf_ = jdh.c().xf_();
            xf_.setContentIntent(NotificationUtil.aOw_(BaseApplication.getContext()));
            xf_.setSmallIcon(R.mipmap._2131820756_res_0x7f1100d4);
            xf_.setOngoing(true);
            xf_.setCustomContentView(this.q);
            xf_.setCustomBigContentView(this.q);
            this.t = xf_.build();
            jdh.c().xh_(this.s, this.t);
            return;
        }
        if (this.r == null || !z()) {
            return;
        }
        this.r.notify(this.s, this.t);
    }

    private void t() {
        LogUtil.a("SyncMusicManager", "mToBeTransferMusicList size:", Integer.valueOf(this.w.size()), " , mFailedTransferMusicList size:", Integer.valueOf(this.i.size()));
        this.i.removeAll(this.w);
        this.i.addAll(this.w);
        LogUtil.a("SyncMusicManager", "mFailedTransferMusicList size:", Integer.valueOf(this.i.size()));
        p();
        ai();
        e(false);
    }

    private void s() {
        LogUtil.a("SyncMusicManager", "endSyncMusicTaskWhenDeviceTimeout enter, the sync music task is end!");
        t();
        if (this.o) {
            ak();
        }
        this.o = true;
    }

    private void e(ArrayList<MusicSong> arrayList, IBaseCallback iBaseCallback) {
        LogUtil.a("SyncMusicManager", "readyToStartSyncTask enter:");
        if (arrayList == null || iBaseCallback == null) {
            LogUtil.a("SyncMusicManager", "readyToStartSyncTask params error!");
            return;
        }
        try {
            if (this.k) {
                iBaseCallback.onResponse(1, "has sync task already,please wait!");
                LogUtil.a("SyncMusicManager", "saveListMusicInfo failed!");
            } else {
                LogUtil.a("SyncMusicManager", "saveListMusicInfo succeed");
                iBaseCallback.onResponse(0, "");
                d(arrayList);
            }
        } catch (RemoteException unused) {
            LogUtil.a("SyncMusicManager", "saveListMusicInfo RemoteException");
        }
        this.o = false;
    }

    public void d(ArrayList<MusicSong> arrayList) {
        LogUtil.a("SyncMusicManager", "dealNewTransferTask enter");
        v();
        this.w.addAll(arrayList);
        this.y.addAll(arrayList);
        an();
        ThreadPoolManager.d().execute(new Runnable() { // from class: jjs
            @Override // java.lang.Runnable
            public final void run() {
                jjt.this.k();
            }
        });
    }

    /* synthetic */ void k() {
        a(this.w);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        LogUtil.a("SyncMusicManager", "enter cancelXiMaLaYaTransferTask");
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.putOpt(ParsedFieldTag.ACTION_TYPE, "healthCancleTransfer");
            jSONObject.putOpt("deviceid", y());
            IBaseCallback iBaseCallback = this.d.get(this.h.getFileName());
            if (iBaseCallback != null) {
                iBaseCallback.onResponse(204, jSONObject.toString());
            } else {
                LogUtil.h("SyncMusicManager", "cancelXiMaLaYaTransferTask, mAckToHwMusicCallback is null");
            }
        } catch (RemoteException | JSONException unused) {
            LogUtil.b("SyncMusicManager", "cancelXiMaLaYaTransferTask JSONException or RemoteException");
        }
        jqj jqjVar = new jqj();
        jqjVar.a(this.h.getFileName());
        jqjVar.d(2);
        jqjVar.c(TransferFileReqType.APP_STOP);
        jqjVar.c((String) null);
        jfq.c().d(TransferBusinessType.FIVE_FORTY, jqjVar, this.c);
        aj();
    }

    private void p() {
        this.k = false;
        SyncMusicBinder syncMusicBinder = this.e;
        if (syncMusicBinder != null) {
            syncMusicBinder.notifyUiIsSyncMusicTaskRunning();
        }
        c();
    }

    public void c() {
        LogUtil.a("SyncMusicManager", "cancelNotification.");
        SyncMusicBinder syncMusicBinder = this.e;
        if (syncMusicBinder != null) {
            syncMusicBinder.stopForeground();
        }
        try {
            jdh.c().a(this.s);
        } catch (IllegalStateException unused) {
            LogUtil.b("SyncMusicManager", "delete health notification exception");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aj() {
        Iterator<MusicSong> it = this.w.iterator();
        while (it.hasNext()) {
            MusicSong next = it.next();
            if (next.getMusicAppType() != 0) {
                c(next);
            }
        }
        c();
        v();
        this.k = false;
        LogUtil.a("SyncMusicManager", "updateAllStatusForCancel enter, mIsSyncMusicTaskRunning is false.");
        this.n = false;
        SyncMusicBinder syncMusicBinder = this.e;
        if (syncMusicBinder != null) {
            syncMusicBinder.notifyUiIsSyncMusicTaskRunning();
        }
        this.o = true;
    }

    private void v() {
        this.y.clear();
        this.w.clear();
        this.i.clear();
        this.l.clear();
        this.j = 0;
        this.h = new MusicSong();
        this.k = true;
        this.f = "1/1";
    }

    private void r() {
        String fileName;
        IBaseCallback iBaseCallback;
        try {
            Iterator<MusicSong> it = this.w.iterator();
            while (it.hasNext()) {
                MusicSong next = it.next();
                if (next.getMusicAppType() != 0 && (iBaseCallback = this.d.get((fileName = next.getFileName()))) != null) {
                    iBaseCallback.onResponse(203, "");
                    LogUtil.a("SyncMusicManager", "sync task timeout, song:", fileName);
                }
            }
        } catch (RemoteException unused) {
            LogUtil.b("SyncMusicManager", "sync task timeout, RemoteException");
        }
        u();
        this.i.addAll(this.w);
        x();
    }

    private void al() {
        e(true);
        ag();
        String songName = this.h.getSongName();
        LogUtil.a("SyncMusicManager", "syncMusicsToDevice start, songName:", songName, ";size:", Long.valueOf(this.h.getSongSize()));
        b(songName);
        long sourceId = this.h.getMusicAppType() == 0 ? this.h.getSourceId() : -1L;
        jqj jqjVar = new jqj();
        jqjVar.a(this.h.getFileName());
        jqjVar.f(this.h.getSongFilePath());
        jqjVar.e(sourceId);
        jqjVar.d(2);
        jqjVar.c(TransferFileReqType.APP_DELIVERY);
        jqjVar.c((String) null);
        if (!TextUtils.isEmpty(this.h.getPackageName())) {
            jqjVar.j(this.h.getPackageName());
        }
        jfq.c().d(TransferBusinessType.FIVE_FORTY, jqjVar, this.v);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, String str) {
        try {
            if (this.h.getMusicAppType() != 0) {
                ReleaseLogUtil.e("R_Music_SyncMusicManager", "responseToHwMusicSyncStatus enter. code:", Integer.valueOf(i), "; msg:", str);
                IBaseCallback iBaseCallback = this.d.get(this.h.getFileName());
                if (iBaseCallback != null) {
                    iBaseCallback.onResponse(i, str);
                } else {
                    ReleaseLogUtil.e("R_Music_SyncMusicManager", "onFileTransferState, mAckToHwMusicCallback is null");
                }
            }
        } catch (RemoteException unused) {
            ReleaseLogUtil.c("R_Music_SyncMusicManager", "refreshProgressByResult to hwmusic RemoteException");
        }
    }

    private boolean ad() {
        this.n = true;
        boolean z = true;
        int i = 0;
        while (this.n) {
            boolean ab = ab();
            if (i >= 60 || (!ab && this.n)) {
                this.n = false;
                LogUtil.a("SyncMusicManager", "find available file timeout!findTimes:", Integer.valueOf(i));
                z = false;
            }
            i++;
            if (this.n) {
                try {
                    Thread.sleep(1000L);
                    LogUtil.a("SyncMusicManager", "sleep times:", Integer.valueOf(i));
                } catch (InterruptedException unused) {
                    LogUtil.a("SyncMusicManager", "waitting failed");
                    z = false;
                }
                LogUtil.a("SyncMusicManager", "waitting for next find file");
            }
        }
        return z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0044, code lost:
    
        if (r2 != false) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0053, code lost:
    
        r4 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0051, code lost:
    
        if (r3.getOnLineMusicCompletedFlag() != 0) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean ab() {
        /*
            r8 = this;
            java.util.concurrent.CopyOnWriteArrayList<com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicSong> r0 = r8.w
            java.util.Iterator r0 = r0.iterator()
            r1 = 1
            r2 = r1
        L8:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L65
            java.lang.Object r3 = r0.next()
            com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicSong r3 = (com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicSong) r3
            java.lang.String r4 = r3.getSongFilePath()
            java.lang.String r5 = "SyncMusicManager"
            if (r4 != 0) goto L26
            java.lang.String r3 = "isSupportSync, song file path is null."
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            com.huawei.hwlogsmodel.LogUtil.h(r5, r3)
            goto L8
        L26:
            java.io.File r4 = new java.io.File
            java.lang.String r6 = r3.getSongFilePath()
            r4.<init>(r6)
            int r6 = r3.getMusicAppType()
            r7 = 0
            if (r6 != 0) goto L47
            jjn r2 = defpackage.jjn.a()
            boolean r2 = r2.d(r3)
            boolean r4 = r4.exists()
            if (r4 == 0) goto L55
            if (r2 == 0) goto L55
            goto L53
        L47:
            boolean r4 = r4.exists()
            if (r4 == 0) goto L55
            int r4 = r3.getOnLineMusicCompletedFlag()
            if (r4 == 0) goto L55
        L53:
            r4 = r1
            goto L56
        L55:
            r4 = r7
        L56:
            if (r4 == 0) goto L8
            r8.h = r3
            r8.n = r7
            java.lang.String r0 = "find available file succeed"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.a(r5, r0)
        L65:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jjt.ab():boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, String str) {
        this.h.setSyncFailedErrorCode(i);
        switch (i) {
            case 109022:
                if (str != null && str.equals(this.p)) {
                    s();
                    LogUtil.a("SyncMusicManager", "OTA is running, stop sync music to device");
                    break;
                }
                break;
            case 140004:
                c(i, "songs nums limit");
                break;
            case 140008:
                c(0, "device has same song");
                this.l.add(this.h);
                am();
                break;
            case 141000:
                s();
                c(202, "");
                sqo.v("transfer file timeout");
                break;
            case 141001:
                t();
                c(201, "");
                break;
            default:
                this.i.add(this.h);
                c(1, "");
                am();
                sqo.v("music transfer file failed, error: " + i);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void am() {
        this.w.remove(this.h);
        if (this.h.getMusicAppType() != 0) {
            c(this.h);
        }
        if (this.w.size() != 0) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: jjq
                @Override // java.lang.Runnable
                public final void run() {
                    jjt.this.o();
                }
            });
            return;
        }
        LogUtil.a("SyncMusicManager", "sync all musics finished! mFailedTransferedMusicList size:", Integer.valueOf(this.i.size()));
        x();
        this.t = null;
        this.r = null;
    }

    /* synthetic */ void o() {
        a(this.w);
    }

    private void x() {
        LogUtil.a("SyncMusicManager", "endSyncMusicTaskWhenNormalFinish enter, the sync music task is end!");
        p();
        af();
        if (this.o) {
            ak();
        }
        this.o = true;
        e(false);
    }

    private void a(CopyOnWriteArrayList<MusicSong> copyOnWriteArrayList) {
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.size() <= 0) {
            LogUtil.a("SyncMusicManager", "syncMusicsToDevice musicList is null");
            return;
        }
        LogUtil.a("SyncMusicManager", "totalMusicFile: ", Integer.valueOf(copyOnWriteArrayList.size()), ",currentMusicFileSize: ", Long.valueOf(copyOnWriteArrayList.get(0).getSongSize()));
        if (copyOnWriteArrayList.get(0).getMusicAppType() == 2) {
            this.h = copyOnWriteArrayList.get(0);
        }
        SyncMusicBinder syncMusicBinder = this.e;
        if (syncMusicBinder != null) {
            syncMusicBinder.notifyUiIsSyncMusicTaskRunning();
        }
        LogUtil.a("SyncMusicManager", "mToBeTransferedMusicList size is ", Integer.valueOf(this.w.size()));
        if (ad()) {
            al();
        } else {
            LogUtil.a("SyncMusicManager", "find available file timeout! Task end, mToBeTransferedMusicList size:", Integer.valueOf(this.w.size()));
            r();
        }
    }

    private void c(MusicSong musicSong) {
        if (musicSong == null) {
            return;
        }
        String songFilePath = musicSong.getSongFilePath();
        LogUtil.a("SyncMusicManager", "deleteTempMusicFile:", songFilePath);
        File file = new File(songFilePath);
        if (!file.exists()) {
            LogUtil.a("SyncMusicManager", "no delete file:");
            return;
        }
        try {
            if (file.delete()) {
                LogUtil.a("SyncMusicManager", "delete success");
            } else {
                LogUtil.a("SyncMusicManager", "delete fail");
            }
            LogUtil.a("SyncMusicManager", "delete temp music file:", musicSong.getFileName());
        } catch (SecurityException unused) {
            LogUtil.b("SyncMusicManager", "SecurityException");
        }
    }

    private boolean z() {
        if (CommonUtil.bh() || NotificationManagerCompat.from(BaseApplication.getContext()).areNotificationsEnabled()) {
            return true;
        }
        LogUtil.h("SyncMusicManager", "notifyMsg notification bar permission is not enabled.");
        return false;
    }

    private boolean aa() {
        String lowerCase = Build.BRAND.toLowerCase(Locale.ENGLISH);
        String lowerCase2 = Build.MODEL.toLowerCase(Locale.ENGLISH);
        String lowerCase3 = Build.PRODUCT.toLowerCase(Locale.ENGLISH);
        if (!lowerCase.contains("redmi")) {
            return false;
        }
        if (!"22081212c".equals(lowerCase2) && !"diting".equals(lowerCase3)) {
            return false;
        }
        LogUtil.a("SyncMusicManager", "redmi k5");
        return true;
    }
}
