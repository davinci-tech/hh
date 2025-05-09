package com.huawei.healthcloud.plugintrack.offlinemap.manager.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Parcelable;
import android.text.TextUtils;
import android.widget.Toast;
import com.amap.api.maps.AMapException;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapManager;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import com.amap.api.services.district.DistrictSearchQuery;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.offlinemap.ui.activity.OfflineMapTabActivity;
import com.huawei.healthcloud.plugintrack.offlinemap.ui.view.CityListBean;
import com.huawei.healthcloud.plugintrack.offlinemap.ui.view.OfflineMapCityList;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import defpackage.gut;
import defpackage.gye;
import defpackage.gyg;
import defpackage.ixx;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes4.dex */
public class OfflineMapService extends Service implements OfflineMapManager.OfflineMapDownloadListener, OfflineMapManager.OfflineLoadedListener {
    private e h;
    private SharedPreferences r;
    private ExecutorService s;
    private String l = "北京";
    private ArrayList<String> m = new ArrayList<>(10);
    private OfflineMapManager e = null;
    private Context d = null;
    private final IBinder b = new ServiceBinder();
    private BroadcastReceiver c = new b(this);
    private a q = null;
    private ArrayList<OfflineMapCity> o = new ArrayList<>(10);
    private List<String> t = Collections.synchronizedList(new ArrayList(10));
    private boolean j = false;
    private boolean i = false;
    private boolean k = false;

    /* renamed from: a, reason: collision with root package name */
    private ExternalStatus f3536a = ExternalStatus.INVALID;
    private boolean g = false;
    private boolean n = false;
    private boolean f = true;

    public enum ExternalStatus {
        OFFLINE_ACTIVITY_DESTROY,
        OFFLINE_ACTIVITY_EXIT,
        MAIN_ACTIVITY_CREATE,
        INVALID
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        LogUtil.a("OfflineMapService", "onBind()");
        return this.b;
    }

    public class ServiceBinder extends Binder {
        public ServiceBinder() {
        }

        public OfflineMapService getService() {
            return OfflineMapService.this;
        }
    }

    static class e extends BaseHandler<OfflineMapService> {
        e(OfflineMapService offlineMapService) {
            super(offlineMapService);
        }

        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: aWz_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(OfflineMapService offlineMapService, Message message) {
            if (offlineMapService == null || message == null) {
                return;
            }
            Object obj = message.obj;
            switch (message.what) {
                case 101:
                    aWy_(offlineMapService, message);
                    break;
                case 102:
                    c(offlineMapService, obj);
                    break;
                case 103:
                    b(offlineMapService, obj);
                    break;
                case 104:
                    d(offlineMapService, obj);
                    break;
                case 106:
                    a(offlineMapService, obj);
                    break;
                case 107:
                    LogUtil.c("OfflineMapService", "RECEIVE_DESTROY_SERVICE11=", 107);
                    offlineMapService.stopSelf();
                    break;
                case 108:
                    offlineMapService.k = false;
                    offlineMapService.f3536a = ExternalStatus.OFFLINE_ACTIVITY_DESTROY;
                    offlineMapService.l();
                    break;
                case 109:
                    offlineMapService.j();
                    break;
            }
        }

        private void a(OfflineMapService offlineMapService, Object obj) {
            if (obj instanceof Intent) {
                offlineMapService.k = false;
                offlineMapService.j(((Intent) obj).getStringExtra("TAG_ONE_CITY_STRING_AS"));
            } else {
                LogUtil.b("OfflineMapService", "DownLoadHandler RECEIVE_WAIT_CITY obj is not intent");
            }
        }

        private void d(OfflineMapService offlineMapService, Object obj) {
            if (obj instanceof Intent) {
                String stringExtra = ((Intent) obj).getStringExtra("TAG_ONE_CITY_STRING_AS");
                if (stringExtra != null) {
                    offlineMapService.o(stringExtra);
                    return;
                }
                return;
            }
            LogUtil.b("OfflineMapService", "DownLoadHandler RECEIVE_UPDATE_CITY obj is not intent");
        }

        private void b(OfflineMapService offlineMapService, Object obj) {
            if (obj instanceof Intent) {
                Intent intent = (Intent) obj;
                String stringExtra = intent.getStringExtra("TAG_ONE_CITY_STRING_AS");
                boolean booleanExtra = intent.getBooleanExtra("TAG_BOOLEAN_ISRESTART_AS", false);
                offlineMapService.c(stringExtra);
                if (booleanExtra) {
                    offlineMapService.d((ArrayList<OfflineMapCity>) offlineMapService.o);
                    return;
                }
                return;
            }
            LogUtil.b("OfflineMapService", "DownLoadHandler RECEIVE_DELETE_CITY obj is not intent");
        }

        private void c(OfflineMapService offlineMapService, Object obj) {
            if (obj instanceof Intent) {
                offlineMapService.e(((Intent) obj).getStringExtra("TAG_ONE_CITY_STRING_AS"));
                offlineMapService.f();
            } else {
                LogUtil.b("OfflineMapService", "DownLoadHandler MSG_RECEIVE_LOAD_CITY obj is not intent");
            }
        }

        private void aWy_(OfflineMapService offlineMapService, Message message) {
            Bundle data = message.getData();
            if (data == null) {
                LogUtil.h("OfflineMapService", "DownLoadHandler MSG_GET_DATA_SUCCESS bundle is null");
                return;
            }
            String string = data.getString("TAG_MSG_SEND_INTENT");
            Parcelable parcelable = data.getParcelable("TAG_MSG_SEND_LIST");
            if (parcelable instanceof OfflineMapCityList) {
                offlineMapService.d(string, (OfflineMapCityList) parcelable);
            }
            if (data.getBoolean("TAG_IS_CHECK_UPDATE")) {
                LogUtil.a("OfflineMapService", "DownLoadHandler MSG_GET_DATA_SUCCESS mIsInitActivity OK");
                offlineMapService.j = true;
                offlineMapService.k = true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        LogUtil.a("OfflineMapService", "resetManager: ");
        e eVar = this.h;
        if (eVar != null) {
            eVar.removeMessages(109);
        }
        if (!this.f) {
            LogUtil.a("OfflineMapService", "resetManager: Manager VerifyCompleted");
            return;
        }
        LogUtil.a("OfflineMapService", "resetManager: reset Manager ");
        try {
            OfflineMapManager offlineMapManager = new OfflineMapManager(this, this);
            this.e = offlineMapManager;
            offlineMapManager.setOnOfflineLoadedListener(this);
        } catch (Exception e2) {
            LogUtil.h("OfflineMapService", "resetManager: ", LogAnonymous.b((Throwable) e2));
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        LogUtil.a("OfflineMapService", "onCreate()");
        super.onCreate();
        this.d = getApplicationContext();
        this.h = new e(this);
        this.s = Executors.newFixedThreadPool(4);
        MapsInitializer.updatePrivacyShow(this.d, true, true);
        MapsInitializer.updatePrivacyAgree(this.d, true);
        h();
    }

    private void h() {
        try {
            OfflineMapManager offlineMapManager = new OfflineMapManager(this, this);
            this.e = offlineMapManager;
            offlineMapManager.setOnOfflineLoadedListener(this);
        } catch (Exception e2) {
            LogUtil.h("OfflineMapService", "initFirst: ", LogAnonymous.b((Throwable) e2));
        }
        if (this.h != null) {
            LogUtil.a("OfflineMapService", "initFirst: Delayed send RESET_OFFLINE_MANAGER ");
            this.h.sendEmptyMessageDelayed(109, 5000L);
        }
        this.r = getSharedPreferences("TAG_SAVE_LOCAL_CITY_CODE", 0);
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(this, (Class<?>) OfflineMapTabActivity.class));
        intent.setFlags(270532608);
        i();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        LogUtil.a("OfflineMapService", "onStartCommand()");
        if (intent == null) {
            LogUtil.h("OfflineMapService", "handleIntent() intent null");
            return 2;
        }
        if (!this.f) {
            aWx_(intent);
        } else if (this.h != null) {
            LogUtil.a("OfflineMapService", "onStartCommand: Delayed send RESET_OFFLINE_MANAGER ");
            this.h.removeMessages(109);
            this.h.sendEmptyMessageDelayed(109, 5000L);
        }
        return 2;
    }

    private void aWx_(Intent intent) {
        this.j = false;
        if (this.r == null) {
            this.r = getSharedPreferences("TAG_SAVE_LOCAL_CITY_CODE", 0);
        }
        String string = this.r.getString("TAG_SAVE_LOCAL_CITY_CODE", "");
        ArrayList<String> a2 = a(this.r.getString("TAG_SAVE_LOADING_ERROR_CITY", ""));
        this.m = a2;
        LogUtil.c("OfflineMapService", "onStartCommand city", a2.toString());
        if (!TextUtils.isEmpty(string) && !Constants.NULL.equals(string)) {
            LogUtil.c("OfflineMapService", "onStartCommand() mLocalCode = code");
            this.l = string;
        }
        aWw_(intent);
        g();
    }

    private void aWw_(Intent intent) {
        if (intent == null) {
            LogUtil.h("OfflineMapService", "handleIntent() intent null");
            return;
        }
        String action = intent.getAction();
        LogUtil.a("OfflineMapService", "handleIntent() action: ", action);
        if (gyg.c(this.d)) {
            this.n = true;
        }
        if (action != null && action.equals("ACTION_OFFLINE_MAP_ACTIVTY_START_AS")) {
            this.f3536a = ExternalStatus.OFFLINE_ACTIVITY_EXIT;
            e();
        } else {
            if (action == null || !action.equals("ACTION_MAIN_ACTIVITY_START_AS")) {
                return;
            }
            this.k = false;
            this.f3536a = ExternalStatus.MAIN_ACTIVITY_CREATE;
            l();
        }
    }

    private void d(final String str, final boolean z) {
        LogUtil.c("OfflineMapService", "getAllData() intent:", str, ",isCheckUpdate:", Boolean.valueOf(z));
        if (this.s == null) {
            this.s = Executors.newFixedThreadPool(3);
        }
        this.s.execute(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.offlinemap.manager.service.OfflineMapService.5
            @Override // java.lang.Runnable
            public void run() {
                ArrayList b2 = OfflineMapService.this.b();
                OfflineMapService offlineMapService = OfflineMapService.this;
                OfflineMapCityList a2 = offlineMapService.a((ArrayList<OfflineMapProvince>) b2, (List<String>) offlineMapService.t);
                Message obtainMessage = OfflineMapService.this.h.obtainMessage();
                obtainMessage.what = 101;
                Bundle bundle = new Bundle();
                bundle.putString("TAG_MSG_SEND_INTENT", str);
                bundle.putParcelable("TAG_MSG_SEND_LIST", a2);
                bundle.putBoolean("TAG_IS_CHECK_UPDATE", z);
                obtainMessage.setData(bundle);
                OfflineMapService.this.h.sendMessage(obtainMessage);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<OfflineMapCity> c(ArrayList<OfflineMapProvince> arrayList) {
        int state;
        ArrayList<OfflineMapCity> arrayList2 = new ArrayList<>(10);
        Iterator<OfflineMapProvince> it = arrayList.iterator();
        while (it.hasNext()) {
            Iterator<OfflineMapCity> it2 = it.next().getCityList().iterator();
            while (it2.hasNext()) {
                OfflineMapCity next = it2.next();
                if (next != null && ((state = next.getState()) == 0 || state == 1 || state == 2)) {
                    arrayList2.add(next);
                }
            }
        }
        LogUtil.c("OfflineMapService", "getDownloadingCityList() finish mLoadingCityList:", Integer.valueOf(arrayList2.size()));
        this.o = arrayList2;
        return arrayList2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<OfflineMapProvince> b() {
        OfflineMapManager offlineMapManager = this.e;
        if (offlineMapManager == null) {
            LogUtil.h("OfflineMapService", "getProvinceList: offline manager is null");
            return new ArrayList<>();
        }
        return offlineMapManager.getOfflineMapProvinceList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public OfflineMapCityList a(ArrayList<OfflineMapProvince> arrayList, List<String> list) {
        d j = new d(arrayList, list).j();
        HashMap<Integer, CityListBean> b2 = j.b();
        ArrayList<OfflineMapProvince> c = j.c();
        this.n = false;
        e(arrayList, c);
        b2.put(0, j.h());
        b2.put(1, j.e());
        b2.put(2, j.a());
        OfflineMapCityList offlineMapCityList = new OfflineMapCityList();
        offlineMapCityList.setCityMap(b2);
        ArrayList<OfflineMapCity> i = j.i();
        ArrayList<OfflineMapCity> d2 = j.d();
        this.o = i;
        offlineMapCityList.setLoadingCityList(i);
        offlineMapCityList.setDownCityList(d2);
        offlineMapCityList.setProvinceList(arrayList);
        LogUtil.a("OfflineMapService", "getOfflineMapCityList() finish mProvinceList:", Integer.valueOf(arrayList.size()), ",mCityMap:", Integer.valueOf(b2.size()), ",mLoadingCityList:", Integer.valueOf(i.size()), ",mDownCityList:", Integer.valueOf(d2.size()));
        return offlineMapCityList;
    }

    private void e(ArrayList<OfflineMapProvince> arrayList, ArrayList<OfflineMapProvince> arrayList2) {
        OfflineMapProvince offlineMapProvince = new OfflineMapProvince();
        offlineMapProvince.setProvinceName("概要图");
        arrayList.add(0, offlineMapProvince);
        OfflineMapProvince offlineMapProvince2 = new OfflineMapProvince();
        offlineMapProvince2.setProvinceName("直辖市");
        arrayList.add(1, offlineMapProvince2);
        OfflineMapProvince offlineMapProvince3 = new OfflineMapProvince();
        offlineMapProvince3.setProvinceName("港澳");
        arrayList.add(2, offlineMapProvince3);
        arrayList.removeAll(arrayList2);
        OfflineMapProvince offlineMapProvince4 = new OfflineMapProvince();
        offlineMapProvince4.setProvinceName("台湾省");
        arrayList.add(offlineMapProvince4);
    }

    @Override // android.app.Service
    public void onDestroy() {
        LogUtil.a("OfflineMapService", "onDestroy()");
        super.onDestroy();
        OfflineMapManager offlineMapManager = this.e;
        if (offlineMapManager != null) {
            offlineMapManager.destroy();
        }
        gut.aUp_(this.d, this.c);
        a aVar = this.q;
        if (aVar != null) {
            unregisterReceiver(aVar);
        }
        ExecutorService executorService = this.s;
        if (executorService != null) {
            executorService.shutdown();
            this.s = null;
        }
        this.g = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        if (this.s == null) {
            this.s = Executors.newFixedThreadPool(3);
        }
        this.s.execute(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.offlinemap.manager.service.OfflineMapService.2
            @Override // java.lang.Runnable
            public void run() {
                boolean e2 = OfflineMapService.this.e((ArrayList<OfflineMapCity>) OfflineMapService.this.c((ArrayList<OfflineMapProvince>) OfflineMapService.this.b()));
                boolean e3 = gye.e(OfflineMapService.this.d, OfflineMapTabActivity.class.getName());
                LogUtil.a("OfflineMapService", "startStopServiceThread() mExternalStatus : ", OfflineMapService.this.f3536a, " ,isLoading : ", Boolean.valueOf(e2), " ,isTopActivity : ", Boolean.valueOf(e3));
                if (e3) {
                    OfflineMapService.this.k = true;
                } else {
                    if (e2 || OfflineMapService.this.f3536a == ExternalStatus.OFFLINE_ACTIVITY_EXIT) {
                        return;
                    }
                    OfflineMapService.this.h.sendEmptyMessage(107);
                }
            }
        });
    }

    private void g() {
        if (this.i) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        this.q = new a(this);
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(this.q, intentFilter);
        this.i = true;
    }

    private void i() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("ACITON_ACTIVITY_LOAD_CITY_AS");
        intentFilter.addAction("ACITON_ACTIVITY_DELETE_CITY_AS");
        intentFilter.addAction("ACITON_ACTIVITY_WAIT_CITY_AS");
        intentFilter.addAction("ACTION_ACTIVITY_PAUSE_CITY_AS");
        intentFilter.addAction("ACTION_ACITITY_DESTROY_AS");
        intentFilter.addAction("ACTION_SPORT_START_DESTROY_SERVICE_AS");
        intentFilter.addAction("ACTION_MAP_ONCHRCKUPDATE_AS");
        gut.aUm_(this.d, this.c, intentFilter);
        LogUtil.a("OfflineMapService", "registerMyBroadcast: completed");
    }

    static class a extends BroadcastReceiver {
        private final WeakReference<OfflineMapService> c;

        a(OfflineMapService offlineMapService) {
            this.c = new WeakReference<>(offlineMapService);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("OfflineMapService", "NetWorkBroadcastReceiver onReceive() intent null");
                return;
            }
            if (context == null) {
                LogUtil.h("OfflineMapService", "NetWorkBroadcastReceiver onReceive() context null");
                return;
            }
            OfflineMapService offlineMapService = this.c.get();
            if (offlineMapService != null) {
                if (offlineMapService.e == null) {
                    LogUtil.h("OfflineMapService", "onReceive: offline manager is null");
                    return;
                }
                String action = intent.getAction();
                LogUtil.a("OfflineMapService", "NetWorkBroadcastReceiver onReceive() action = ", action);
                if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
                    if (gyg.c(context)) {
                        LogUtil.a("OfflineMapService", "NetWorkBroadcastReceiver onReceive() network is wifi");
                        Iterator it = offlineMapService.o.iterator();
                        while (it.hasNext()) {
                            OfflineMapCity offlineMapCity = (OfflineMapCity) it.next();
                            if (offlineMapCity.getState() != 3) {
                                offlineMapService.e(offlineMapCity.getCity());
                            }
                        }
                        return;
                    }
                    if (gyg.b(context)) {
                        LogUtil.a("OfflineMapService", "NetWorkBroadcastReceiver onReceive() network is not wifi");
                        Iterator it2 = offlineMapService.o.iterator();
                        while (it2.hasNext()) {
                            if (((OfflineMapCity) it2.next()).getState() == 0) {
                                LogUtil.a("OfflineMapService", "NetWorkBroadcastReceiver onReceive() network is not wifi pause loading");
                                offlineMapService.e.pause();
                                offlineMapService.c(R.string._2130840221_res_0x7f020a9d);
                                return;
                            }
                        }
                        return;
                    }
                    LogUtil.h("OfflineMapService", "NetWorkBroadcastReceiver onReceive() no network");
                    return;
                }
                return;
            }
            LogUtil.h("OfflineMapService", "NetWorkBroadcastReceiver onReceive() service null");
        }
    }

    static class b extends BroadcastReceiver {
        private final WeakReference<OfflineMapService> c;

        b(OfflineMapService offlineMapService) {
            this.c = new WeakReference<>(offlineMapService);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("OfflineMapService", "MyBroadcastReceiver onReceive() intent null");
                return;
            }
            OfflineMapService offlineMapService = this.c.get();
            if (offlineMapService == null) {
                LogUtil.h("OfflineMapService", "MyBroadcastReceiver onReceive() service null");
                return;
            }
            String action = intent.getAction();
            LogUtil.a("OfflineMapService", "MyBroadcastReceiver onReceive() action: ", action);
            if ("ACITON_ACTIVITY_LOAD_CITY_AS".equals(action)) {
                offlineMapService.h.sendMessage(offlineMapService.h.obtainMessage(102, 0, 0, intent));
                return;
            }
            if ("ACITON_ACTIVITY_DELETE_CITY_AS".equals(action)) {
                offlineMapService.h.sendMessage(offlineMapService.h.obtainMessage(103, 0, 0, intent));
                return;
            }
            if ("ACTION_ACTIVITY_PAUSE_CITY_AS".equals(action)) {
                offlineMapService.h.sendMessage(offlineMapService.h.obtainMessage(105, 0, 0, intent));
                return;
            }
            if ("ACITON_ACTIVITY_WAIT_CITY_AS".equals(action)) {
                offlineMapService.h.sendMessage(offlineMapService.h.obtainMessage(106, 0, 0, intent));
                return;
            }
            if ("ACTION_ACITITY_DESTROY_AS".equals(action)) {
                offlineMapService.h.sendEmptyMessage(108);
                return;
            }
            if ("ACTION_SPORT_START_DESTROY_SERVICE_AS".equals(action)) {
                offlineMapService.f3536a = ExternalStatus.INVALID;
                offlineMapService.h.sendEmptyMessage(107);
            } else if ("ACTION_MAP_ONCHRCKUPDATE_AS".equals(action)) {
                offlineMapService.h.sendMessage(offlineMapService.h.obtainMessage(104, 0, 0, intent));
            }
        }
    }

    @Override // com.amap.api.maps.offlinemap.OfflineMapManager.OfflineMapDownloadListener
    public void onDownload(int i, int i2, String str) {
        LogUtil.a("OfflineMapService", "onDownload() ", "state:", Integer.valueOf(i), ", complete:", Integer.valueOf(i2));
        if (this.g) {
            LogUtil.a("OfflineMapService", "onDownload() Service is destroy");
            return;
        }
        if (i == -1) {
            f(str);
            h(str);
            d();
        } else if (i == 0) {
            h(str);
        } else {
            if (i != 3) {
                if (i == 4) {
                    i(str);
                } else if (i != 5) {
                    if (i == 6) {
                        d(str);
                        g(str);
                    } else if (i != 10 && i != 1002) {
                        switch (i) {
                            case 101:
                                h(str);
                                c(R.string._2130840215_res_0x7f020a97);
                                d();
                                break;
                            case 102:
                                n(str);
                                d();
                                break;
                            case 103:
                                a();
                                h(str);
                                d();
                                break;
                        }
                    }
                }
            }
            d();
        }
        if (this.j && this.k) {
            c();
        }
    }

    private void d(String str) {
        if (this.m.contains(str)) {
            LogUtil.a("OfflineMapService", "onRemove loadErrorCity=null");
            d(this.o);
        }
    }

    private void n(String str) {
        m(String.format(getString(R.string._2130840218_res_0x7f020a9a), str));
    }

    private void f(String str) {
        m(String.format(getString(R.string._2130839748_res_0x7f0208c4), str));
    }

    private void i(String str) {
        if (this.e == null) {
            LogUtil.h("OfflineMapService", "offlineMapDownSuccess: offline is null");
            return;
        }
        LogUtil.a("OfflineMapService", "onDownload SUCCESS");
        if (this.m.contains(str)) {
            b(str);
        }
        g(str);
        if (!gyg.c(this.d)) {
            LogUtil.a("OfflineMapService", "onDownload SUCCESS is no wifi manager pause");
            this.e.pause();
        }
        d();
    }

    private void d() {
        if (gye.e(this.d, OfflineMapTabActivity.class.getName())) {
            return;
        }
        l();
    }

    private void a() {
        long j;
        try {
            j = CommonUtil.j(this.d).getUsableSpace();
        } catch (SecurityException unused) {
            LogUtil.b("OfflineMapService", "getUsableSpace error");
            j = -1;
        }
        if (j < 0 || (j / 1024.0f) / 1024.0f > 10.0f) {
            c(R.string._2130842431_res_0x7f02133f);
        } else {
            c(R.string._2130840214_res_0x7f020a96);
        }
    }

    @Override // com.amap.api.maps.offlinemap.OfflineMapManager.OfflineMapDownloadListener
    public void onCheckUpdate(boolean z, String str) {
        LogUtil.a("OfflineMapService", "onCheckUpdate() isNew:", Boolean.valueOf(z));
    }

    @Override // com.amap.api.maps.offlinemap.OfflineMapManager.OfflineMapDownloadListener
    public void onRemove(boolean z, String str, String str2) {
        LogUtil.a("OfflineMapService", "onRemove() success:", Boolean.valueOf(z), " status:", str2);
        if (this.g) {
            LogUtil.a("OfflineMapService", "onDownload() Service is destroy");
            return;
        }
        if (z) {
            m(String.format(getString(R.string._2130840216_res_0x7f020a98), str));
            if (this.m.contains(str)) {
                LogUtil.a("OfflineMapService", "onRemove loadErrorCity=null");
                b(str);
                d(this.o);
            }
            g(str);
        } else {
            m(String.format(getString(R.string._2130840217_res_0x7f020a99), str));
        }
        if (this.j && this.k) {
            k();
        }
    }

    private void g(String str) {
        LogUtil.c("OfflineMapService", "removeUpdateListCity city:", str);
        for (int i = 0; i < this.t.size(); i++) {
            if (str.equals(this.t.get(i))) {
                LogUtil.c("OfflineMapService", "removeUpdateListCity equals remove : ", Integer.valueOf(i));
                this.t.remove(i);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final String str) {
        b(str);
        LogUtil.a("OfflineMapService", "deleteCity()");
        if (str == null) {
            LogUtil.h("OfflineMapService", "deleteCity() city is null");
            return;
        }
        if (Constants.NULL.equals(str) || str.isEmpty()) {
            LogUtil.h("OfflineMapService", "deleteCity() city is null");
            return;
        }
        if (this.s == null) {
            this.s = Executors.newFixedThreadPool(3);
        }
        this.s.execute(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.offlinemap.manager.service.OfflineMapService.1
            @Override // java.lang.Runnable
            public void run() {
                if (OfflineMapService.this.e != null) {
                    OfflineMapService.this.e.remove(str);
                } else {
                    LogUtil.h("OfflineMapService", "run: offline manager is null");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(final String str) {
        LogUtil.a("OfflineMapService", "loadWaitThread()");
        if (this.s == null) {
            this.s = Executors.newFixedThreadPool(3);
        }
        this.s.execute(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.offlinemap.manager.service.OfflineMapService.4
            @Override // java.lang.Runnable
            public void run() {
                OfflineMapService offlineMapService = OfflineMapService.this;
                List c = offlineMapService.c((List<OfflineMapCity>) offlineMapService.o);
                OfflineMapService.this.o();
                OfflineMapService.this.e(str);
                OfflineMapService.this.a((List<String>) c, str);
                OfflineMapService.this.m();
            }
        });
    }

    private void c() {
        d("ACTION_MAP_ONDOWNLOAD_SA", false);
    }

    private void e() {
        LogUtil.a("OfflineMapService", "initActivity()");
        this.t.clear();
        d("ACTION_INIT_ACTIVITY_SA", true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        d("ACTION_REFRESH_ACTIVITY_SA", false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        LogUtil.a("OfflineMapService", "updateWait()");
        OfflineMapCityList a2 = a(b(), this.t);
        Message obtainMessage = this.h.obtainMessage();
        obtainMessage.what = 101;
        Bundle bundle = new Bundle();
        bundle.putString("TAG_MSG_SEND_INTENT", "ACTION_MAP_LOAD_WAIT_SUCCESS_SA");
        bundle.putParcelable("TAG_MSG_SEND_LIST", a2);
        obtainMessage.setData(bundle);
        this.h.sendMessage(obtainMessage);
    }

    private void k() {
        LogUtil.a("OfflineMapService", "updateDelete()");
        d("ACTION_MAP_ONREMOVE_SA", false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        if (this.e == null) {
            LogUtil.h("OfflineMapService", "loadCity: offline manager is null");
            return;
        }
        if (str == null) {
            LogUtil.h("OfflineMapService", "loadCity() city is null");
            d(this.o);
            return;
        }
        if (Constants.NULL.equals(str) || str.isEmpty()) {
            LogUtil.h("OfflineMapService", "loadCity() city is null");
            d(this.o);
            return;
        }
        OfflineMapCity itemByCityName = this.e.getItemByCityName(str);
        if (itemByCityName == null) {
            LogUtil.h("OfflineMapService", "City : ", str, "not exist");
            return;
        }
        if (itemByCityName.getState() == 0) {
            LogUtil.a("OfflineMapService", "loadCity() city is loading");
            return;
        }
        try {
            HashMap hashMap = new HashMap(16);
            hashMap.put("click", 1);
            hashMap.put(DistrictSearchQuery.KEYWORDS_CITY, str);
            ixx.d().d(this.d, AnalyticsValue.HEALTH_MINE_SETTINGS_OFFINE_MAP_DOWNLOAD_2040017.value(), hashMap, 0);
            this.e.downloadByCityName(str);
            LogUtil.a("OfflineMapService", "loadCity() finish");
        } catch (AMapException e2) {
            String errorMessage = e2.getErrorMessage();
            LogUtil.b("OfflineMapService", errorMessage);
            m(errorMessage);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(ArrayList<OfflineMapCity> arrayList) {
        if (this.e == null) {
            LogUtil.h("OfflineMapService", "restart: offline manager is null");
            return;
        }
        int size = arrayList.size();
        LogUtil.a("OfflineMapService", "loadWaitCity() mLoadingCityList:", Integer.valueOf(size));
        if (size <= 0) {
            this.e.restart();
            return;
        }
        Iterator<OfflineMapCity> it = arrayList.iterator();
        while (it.hasNext()) {
            OfflineMapCity next = it.next();
            if (next != null) {
                int state = next.getState();
                String city = next.getCity();
                if (state == 2) {
                    e(city);
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<String> list, String str) {
        LogUtil.a("OfflineMapService", "loadWaitingCity() list:", Integer.valueOf(list.size()));
        for (String str2 : list) {
            if (!str2.equals(str)) {
                e(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<String> c(List<OfflineMapCity> list) {
        LogUtil.a("OfflineMapService", "getWaitCityList() list:", Integer.valueOf(list.size()));
        ArrayList arrayList = new ArrayList(10);
        for (OfflineMapCity offlineMapCity : list) {
            String city = offlineMapCity.getCity();
            if (offlineMapCity.getState() == 2 || offlineMapCity.getState() == 0) {
                arrayList.add(city);
            }
        }
        LogUtil.a("OfflineMapService", "getWaitCityList() waitList:", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        if (this.e == null) {
            LogUtil.h("OfflineMapService", "stopLoadingCity: offline manager is null");
        } else {
            LogUtil.a("OfflineMapService", "stopLoadingCity()");
            this.e.stop();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o(String str) {
        g(str);
        e(str);
    }

    private void m(final String str) {
        LogUtil.a("OfflineMapService", "toastMessage() message", str);
        e eVar = this.h;
        if (eVar == null) {
            LogUtil.h("OfflineMapService", "toastMessage() mHandler null");
        } else {
            eVar.post(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.offlinemap.manager.service.OfflineMapService.3
                @Override // java.lang.Runnable
                public void run() {
                    Toast.makeText(OfflineMapService.this.d, str, 1).show();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final int i) {
        LogUtil.a("OfflineMapService", "toastMessage() message", Integer.valueOf(i));
        e eVar = this.h;
        if (eVar == null) {
            LogUtil.h("OfflineMapService", "toastMessage() mHandler null");
        } else {
            eVar.post(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.offlinemap.manager.service.OfflineMapService.8
                @Override // java.lang.Runnable
                public void run() {
                    Toast.makeText(OfflineMapService.this.d, i, 1).show();
                }
            });
        }
    }

    private void h(String str) {
        StringBuilder sb = new StringBuilder(16);
        if (!this.m.contains(str)) {
            this.m.add(str);
        }
        Iterator<String> it = this.m.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append("|");
        }
        sb.append(str);
        if (this.r == null) {
            this.r = getSharedPreferences("TAG_SAVE_LOCAL_CITY_CODE", 0);
        }
        SharedPreferences.Editor edit = this.r.edit();
        edit.putString("TAG_SAVE_LOADING_ERROR_CITY", sb.toString());
        edit.commit();
    }

    private void b(String str) {
        StringBuilder sb = new StringBuilder(16);
        if (str == null) {
            return;
        }
        if (this.m.contains(str)) {
            this.m.remove(str);
        }
        Iterator<String> it = this.m.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append("|");
        }
        if (this.r == null) {
            this.r = getSharedPreferences("TAG_SAVE_LOCAL_CITY_CODE", 0);
        }
        SharedPreferences.Editor edit = this.r.edit();
        edit.putString("TAG_SAVE_LOADING_ERROR_CITY", sb.toString());
        edit.commit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str, OfflineMapCityList offlineMapCityList) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("OfflineMapService", "sendDataToActivity() action is empty");
            return;
        }
        LogUtil.a("OfflineMapService", "sendDataToActivity() action:", str);
        Intent intent = new Intent(str);
        Bundle bundle = new Bundle();
        bundle.putParcelable("TAG_ALL_CITY_LIST_SA", offlineMapCityList);
        intent.putExtras(bundle);
        if ("ACTION_MAP_LOAD_WAIT_SUCCESS_SA".equals(str)) {
            this.k = true;
        }
        gut.aUn_(this.d, intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e(ArrayList<OfflineMapCity> arrayList) {
        int size = arrayList.size();
        LogUtil.a("OfflineMapService", "isLoading() size:", Integer.valueOf(size));
        if (size > 0) {
            return true;
        }
        LogUtil.a("OfflineMapService", "mLoadingCityList.size()=0");
        return false;
    }

    private ArrayList<String> a(String str) {
        ArrayList<String> arrayList = new ArrayList<>(10);
        if (!TextUtils.isEmpty(str)) {
            for (String str2 : str.split("\\|")) {
                arrayList.add(str2);
            }
        }
        return arrayList;
    }

    @Override // com.amap.api.maps.offlinemap.OfflineMapManager.OfflineLoadedListener
    public void onVerifyComplete() {
        LogUtil.a("OfflineMapService", "onVerifyComplete: ");
        this.f = false;
        aWx_(new Intent("ACTION_OFFLINE_MAP_ACTIVTY_START_AS"));
    }

    class d {
        private ArrayList<OfflineMapProvince> b;
        private ArrayList<OfflineMapCity> c;
        private CityListBean d;
        private HashMap<Integer, CityListBean> e;
        private List<String> f;
        private ArrayList<OfflineMapProvince> g;
        private ArrayList<OfflineMapCity> h;
        private CityListBean i;
        private CityListBean j;

        d(ArrayList<OfflineMapProvince> arrayList, List<String> list) {
            this.g = arrayList;
            this.f = list;
        }

        public HashMap<Integer, CityListBean> b() {
            return this.e;
        }

        public ArrayList<OfflineMapProvince> c() {
            return this.b;
        }

        public CityListBean e() {
            return this.d;
        }

        public CityListBean a() {
            return this.i;
        }

        public CityListBean h() {
            return this.j;
        }

        public ArrayList<OfflineMapCity> d() {
            return this.c;
        }

        public ArrayList<OfflineMapCity> i() {
            return this.h;
        }

        public d j() {
            LogUtil.a("OfflineMapService", "getOfflineMapCityList() enter mProvinceList:", Integer.valueOf(this.g.size()));
            this.e = new HashMap<>(16);
            this.b = new ArrayList<>(10);
            this.d = new CityListBean();
            this.i = new CityListBean();
            this.j = new CityListBean();
            this.c = new ArrayList<>(10);
            this.h = new ArrayList<>(10);
            for (int i = 0; i < this.g.size(); i++) {
                OfflineMapProvince offlineMapProvince = this.g.get(i);
                CityListBean cityListBean = new CityListBean();
                d(offlineMapProvince, cityListBean);
                this.e.put(Integer.valueOf(i + 3), cityListBean);
            }
            return this;
        }

        private void d(OfflineMapProvince offlineMapProvince, CityListBean cityListBean) {
            Iterator<OfflineMapCity> it = offlineMapProvince.getCityList().iterator();
            while (it.hasNext()) {
                OfflineMapCity next = it.next();
                if (next != null) {
                    int state = next.getState();
                    String city = next.getCity();
                    c(next, state, city);
                    b(offlineMapProvince, cityListBean, next);
                    b(next, city);
                }
            }
            if (offlineMapProvince.getCityList().size() == 1) {
                this.b.add(offlineMapProvince);
            }
        }

        private void b(OfflineMapProvince offlineMapProvince, CityListBean cityListBean, OfflineMapCity offlineMapCity) {
            if (offlineMapProvince.getCityList().size() != 1) {
                cityListBean.add(offlineMapCity);
            }
        }

        private void b(OfflineMapCity offlineMapCity, String str) {
            if (str.contains("香港") || str.contains("澳门")) {
                this.i.add(offlineMapCity);
            }
            if (str.contains("全国概要图")) {
                this.j.add(offlineMapCity);
            }
            if (str.contains("北京") || str.contains("上海")) {
                this.d.add(offlineMapCity);
            }
            if (str.contains("天津") || str.contains("重庆")) {
                this.d.add(offlineMapCity);
            }
        }

        private void c(OfflineMapCity offlineMapCity, int i, String str) {
            if (i == 7) {
                this.f.add(str);
                return;
            }
            if (i != 10) {
                switch (i) {
                    case -1:
                        this.h.add(offlineMapCity);
                        d(str);
                        LogUtil.c("OfflineMapService", "offlineMapCity11==", Integer.valueOf(offlineMapCity.getState()));
                        return;
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 5:
                        break;
                    case 4:
                        this.c.add(offlineMapCity);
                        return;
                    default:
                        switch (i) {
                        }
                        return;
                }
                this.h.add(offlineMapCity);
                LogUtil.c("OfflineMapService", "offlineMapCity11==", Integer.valueOf(offlineMapCity.getState()));
                return;
            }
            this.c.add(offlineMapCity);
        }

        private void d(String str) {
            if (OfflineMapService.this.n) {
                OfflineMapService.this.e(str);
            }
        }
    }
}
