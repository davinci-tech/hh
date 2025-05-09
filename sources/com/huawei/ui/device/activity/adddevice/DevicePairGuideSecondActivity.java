package com.huawei.ui.device.activity.adddevice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.android.gms.wearable.PutDataRequest;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadDeviceInfoCallBack;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.adapter.BaseRecyclerAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.device.adapter.RightListAdapter;
import defpackage.cup;
import defpackage.cuw;
import defpackage.cvc;
import defpackage.cvk;
import defpackage.cvm;
import defpackage.cwc;
import defpackage.jfu;
import defpackage.jph;
import defpackage.koq;
import defpackage.msj;
import defpackage.msn;
import defpackage.msq;
import defpackage.nsy;
import defpackage.nuc;
import defpackage.nyq;
import defpackage.oae;
import defpackage.oba;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.Utils;
import health.compact.a.util.LogUtil;
import java.io.File;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class DevicePairGuideSecondActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private oae f9020a;
    private CustomTitleBar c;
    private Context d;
    private HealthRecycleView e;
    private LinearLayout i;
    private String j;
    private LinearLayout k;
    private HealthTextView l;
    private HealthProgressBar m;
    private RightListAdapter o;
    private nuc p;
    private List<nyq> b = new ArrayList(16);
    private List<String> q = new ArrayList(16);
    private Object t = null;
    private Map<String, String> n = new HashMap(16);
    private Map<String, cvm> s = new HashMap(16);
    private List<String> g = new ArrayList(16);
    private BroadcastReceiver f = new BroadcastReceiver() { // from class: com.huawei.ui.device.activity.adddevice.DevicePairGuideSecondActivity.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || intent.getAction() == null) {
                LogUtil.c("DevicePairGuideSecondActivity", "NetBroadcastReceiver intent is null");
                return;
            }
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                if (CommonUtil.aa(BaseApplication.getContext())) {
                    DevicePairGuideSecondActivity.this.o();
                    DevicePairGuideSecondActivity.this.l();
                } else {
                    LogUtil.c("DevicePairGuideSecondActivity", "net work is error");
                }
            }
        }
    };
    private BroadcastReceiver h = new BroadcastReceiver() { // from class: com.huawei.ui.device.activity.adddevice.DevicePairGuideSecondActivity.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || intent.getAction() == null) {
                LogUtil.c("DevicePairGuideSecondActivity", "mPairBroadcastReceiver intent is null");
            } else if ("com.huawei.health.action.PAIR_DEVICE_SUCCESS".equals(intent.getAction())) {
                LogUtil.d("DevicePairGuideSecondActivity", "pair device success");
                if (DevicePairGuideSecondActivity.this.isFinishing()) {
                    return;
                }
                DevicePairGuideSecondActivity.this.finish();
            }
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.d = this;
        setContentView(R.layout.activity_devic_pair_guide_sceond);
        this.p = new nuc();
        b();
        d();
    }

    private void b() {
        this.c = (CustomTitleBar) nsy.cMc_(this, R.id.device_second_title);
        this.e = (HealthRecycleView) nsy.cMc_(this, R.id.device_list);
        this.i = (LinearLayout) nsy.cMc_(this, R.id.device_error_bad_layout);
        this.k = (LinearLayout) nsy.cMc_(this, R.id.device_download_bad_layout);
        this.m = (HealthProgressBar) nsy.cMc_(this, R.id.download_progress);
        this.l = (HealthTextView) nsy.cMc_(this, R.id.resource_error);
        this.c.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.DevicePairGuideSecondActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DevicePairGuideSecondActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        jph.bIM_(this, -1);
    }

    private void d() {
        Intent intent = getIntent();
        if (intent != null) {
            try {
                this.q = intent.getStringArrayListExtra("uuid_list");
            } catch (ArrayIndexOutOfBoundsException unused) {
                LogUtil.e("DevicePairGuideSecondActivity", "get intent is ArrayIndexOutOfBoundsException");
            }
            this.j = intent.getStringExtra("kind_id");
            Serializable serializableExtra = intent.getSerializableExtra("pair_guide_array");
            if (serializableExtra instanceof Map) {
                this.n = (Map) serializableExtra;
            }
            List<String> list = this.q;
            if (list == null || list.isEmpty()) {
                LogUtil.c("DevicePairGuideSecondActivity", "device uuids is empty");
                return;
            }
            if (TextUtils.isEmpty(this.j)) {
                LogUtil.c("DevicePairGuideSecondActivity", "mKindType is empty");
                return;
            }
            Map<String, String> map = this.n;
            if (map == null || map.isEmpty()) {
                LogUtil.c("DevicePairGuideSecondActivity", "mPairGuideArray is empty");
                return;
            }
            this.t = intent.getSerializableExtra("second_resource");
        }
        o();
        this.f9020a = oae.c(BaseApplication.getContext());
    }

    static class a implements DownloadDeviceInfoCallBack {
        private final WeakReference<DevicePairGuideSecondActivity> b;

        private a(DevicePairGuideSecondActivity devicePairGuideSecondActivity) {
            this.b = new WeakReference<>(devicePairGuideSecondActivity);
        }

        @Override // com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadDeviceInfoCallBack
        public void onSuccess() {
            DevicePairGuideSecondActivity devicePairGuideSecondActivity = this.b.get();
            if (d(devicePairGuideSecondActivity)) {
                devicePairGuideSecondActivity.h();
            }
        }

        @Override // com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadDeviceInfoCallBack
        public void onFailure(int i) {
            DevicePairGuideSecondActivity devicePairGuideSecondActivity = this.b.get();
            if (d(devicePairGuideSecondActivity)) {
                devicePairGuideSecondActivity.e(i);
            }
        }

        @Override // com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadDeviceInfoCallBack
        public void netWorkError() {
            DevicePairGuideSecondActivity devicePairGuideSecondActivity = this.b.get();
            if (d(devicePairGuideSecondActivity)) {
                devicePairGuideSecondActivity.g();
            }
        }

        @Override // com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadDeviceInfoCallBack
        public void onDownload(int i) {
            DevicePairGuideSecondActivity devicePairGuideSecondActivity = this.b.get();
            if (d(devicePairGuideSecondActivity)) {
                devicePairGuideSecondActivity.b(i);
            }
        }

        private boolean d(DevicePairGuideSecondActivity devicePairGuideSecondActivity) {
            if (devicePairGuideSecondActivity != null && !devicePairGuideSecondActivity.isFinishing() && !devicePairGuideSecondActivity.isDestroyed()) {
                return true;
            }
            LogUtil.d("DevicePairGuideSecondActivity", "activity is null, finish or destroyed");
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        this.m.setVisibility(0);
        ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).downloadIndexByUuidList(this.q, new a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        this.m.setVisibility(8);
        this.k.setVisibility(8);
        this.i.setVisibility(8);
        Object obj = this.t;
        if (obj != null && (obj instanceof Map)) {
            Map<String, cvm> map = (Map) obj;
            this.s = map;
            LogUtil.d("DevicePairGuideSecondActivity", "mSecondResourceMap: ", map.toString());
            j();
            return;
        }
        i();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        this.m.setVisibility(8);
        this.k.setVisibility(0);
        this.i.setVisibility(8);
        if (i == -3) {
            this.l.setText(R.string._2130844862_res_0x7f021cbe);
        } else {
            this.l.setText(R.string.IDS_no_device_res);
        }
        this.k.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.DevicePairGuideSecondActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DevicePairGuideSecondActivity.this.o();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            BroadcastManagerUtil.bFB_(this, this.f, intentFilter);
        } catch (IllegalArgumentException unused) {
            LogUtil.e("DevicePairGuideSecondActivity", "handleDownloadNetError register receiver is error");
        }
        this.m.setVisibility(8);
        this.i.setVisibility(0);
        this.k.setVisibility(8);
        this.i.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.DevicePairGuideSecondActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DevicePairGuideSecondActivity.this.p.a(DevicePairGuideSecondActivity.this);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        this.m.setVisibility(0);
        this.i.setVisibility(8);
        this.k.setVisibility(8);
        if (i > 0) {
            this.m.setProgress(i);
        }
    }

    private void j() {
        f();
        for (String str : this.s.keySet()) {
            LogUtil.d("DevicePairGuideSecondActivity", "setDeviceInfoByIndexAll mUuidList : ", this.q.toString());
            LogUtil.d("DevicePairGuideSecondActivity", "setDeviceInfo mDeviceUuidList : ", this.g.toString());
            LogUtil.d("DevicePairGuideSecondActivity", "setDeviceInfoByIndexAll secondResourcebean: ", this.s.get(str));
            String str2 = this.s.get(str).f().get(0);
            if (!cup.c().containsKey(str2) && !this.g.contains(str2)) {
                LogUtil.c("DevicePairGuideSecondActivity", "setDeviceInfoByIndexAll this device is not support show");
            } else {
                LogUtil.d("DevicePairGuideSecondActivity", "setDeviceInfo uuid : ", str2);
                nyq nyqVar = new nyq();
                nyqVar.f(this.n.get(str2));
                nyqVar.e(this.j);
                nyqVar.j(3);
                boolean d = cup.d(str2);
                oba obaVar = new oba(null);
                if (this.s.get(str) != null) {
                    nyqVar.a(obaVar.e(this.s.get(str).a()));
                    nyqVar.a(0);
                    nyqVar.j(str2);
                    nyqVar.d(this.s.get(str).c());
                    nyqVar.c(obaVar.e(this.s.get(str).e()));
                    this.b.add(nyqVar);
                } else {
                    cuw c = jfu.c(str2, d);
                    if (c == null) {
                        LogUtil.c("DevicePairGuideSecondActivity", " deviceInfoNew is null");
                    } else {
                        nyqVar.a(c.f());
                        nyqVar.a(1);
                        nyqVar.j(str2);
                        nyqVar.b(c.v());
                        nyqVar.c(c.h());
                        this.b.add(nyqVar);
                    }
                }
            }
        }
        a();
    }

    private void i() {
        f();
        for (String str : this.q) {
            if (!cup.c().containsKey(str) && !this.g.contains(str)) {
                LogUtil.c("DevicePairGuideSecondActivity", "this device is not support show");
            } else {
                nyq nyqVar = new nyq();
                nyqVar.f(this.n.get(str));
                nyqVar.e(this.j);
                nyqVar.j(3);
                cvc pluginInfoByUuid = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(str);
                boolean d = cup.d(str);
                if (pluginInfoByUuid != null && pluginInfoByUuid.f() != null) {
                    nyqVar.a(pluginInfoByUuid.f().ae());
                    nyqVar.a(0);
                    nyqVar.j(str);
                    nyqVar.d(e(pluginInfoByUuid));
                    nyqVar.c(pluginInfoByUuid.f().i());
                    this.b.add(nyqVar);
                } else {
                    cuw c = jfu.c(str, d);
                    if (c == null) {
                        LogUtil.c("DevicePairGuideSecondActivity", " deviceInfoNew is null");
                    } else {
                        nyqVar.a(c.f());
                        nyqVar.a(1);
                        nyqVar.j(str);
                        nyqVar.b(c.v());
                        nyqVar.c(c.h());
                        this.b.add(nyqVar);
                    }
                }
            }
        }
        a();
    }

    private void f() {
        this.g.clear();
        List<cvk> indexList = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getIndexList();
        if (indexList == null || indexList.size() <= 0) {
            LogUtil.d("DevicePairGuideSecondActivity", "have no index info");
            return;
        }
        for (cvk cvkVar : indexList) {
            if (cvkVar.g() != null) {
                String e = cvkVar.e();
                if (this.q.contains(e)) {
                    if (!cwc.e(cvkVar.i())) {
                        LogUtil.c("DevicePairGuideSecondActivity", "app version is not supported");
                    } else if (Utils.o()) {
                        if (TextUtils.equals(cvkVar.f(), "2") || TextUtils.equals(cvkVar.f(), "3")) {
                            this.g.add(e);
                        }
                    } else if (TextUtils.equals(cvkVar.f(), "1") || TextUtils.equals(cvkVar.f(), "3")) {
                        this.g.add(e);
                    }
                }
            }
        }
    }

    private String e(cvc cvcVar) {
        return msj.a().d(cvcVar.e()) + File.separator + cvcVar.e() + File.separator + "img" + File.separator + cvcVar.f().ay() + ".png";
    }

    private void a() {
        if (this.b.isEmpty()) {
            LogUtil.c("DevicePairGuideSecondActivity", "mDeviceInfos is empty");
            return;
        }
        this.o = new RightListAdapter(this.b);
        this.e.setLayoutManager(new LinearLayoutManager(this));
        this.e.setAdapter(this.o);
        this.o.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() { // from class: com.huawei.ui.device.activity.adddevice.DevicePairGuideSecondActivity.6
            @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter.OnItemClickListener
            public void onItemClicked(RecyclerHolder recyclerHolder, int i, Object obj) {
                if (obj instanceof nyq) {
                    DevicePairGuideSecondActivity.this.b((nyq) obj);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(nyq nyqVar) {
        e();
        String j = nyqVar.j();
        LogUtil.d("DevicePairGuideSecondActivity", "pairGuid,:", j);
        if (TextUtils.isEmpty(j)) {
            LogUtil.c("DevicePairGuideSecondActivity", "pairGuid is empty");
            return;
        }
        if (!this.j.startsWith(PutDataRequest.WEAR_URI_SCHEME)) {
            LogUtil.c("DevicePairGuideSecondActivity", "other device");
            return;
        }
        if ("3".equals(j)) {
            a(nyqVar);
            return;
        }
        ArrayList<String> arrayList = new ArrayList<>(16);
        String m = nyqVar.m();
        if (TextUtils.isEmpty(m)) {
            LogUtil.c("DevicePairGuideSecondActivity", "intentPairGuid uuid is empty");
            return;
        }
        if ("b81581e1-5c5a-4e30-b099-0a448ef56a24".equals(m) && this.t != null) {
            arrayList.addAll(this.s.get("b81581e1-5c5a-4e30-b099-0a448ef56a24").f());
        } else {
            arrayList.add(m);
        }
        if (koq.b(arrayList)) {
            LogUtil.c("DevicePairGuideSecondActivity", "intentPairGuid, uuidList is empty");
            return;
        }
        Intent intent = new Intent(this, (Class<?>) PairingGuideActivity.class);
        if (arrayList instanceof ArrayList) {
            intent.putStringArrayListExtra("uuid_list", arrayList);
        }
        intent.putExtra("kind_id", this.j);
        intent.putExtra("pair_guide", nyqVar.j());
        startActivity(intent);
    }

    private void a(nyq nyqVar) {
        ArrayList arrayList = new ArrayList(16);
        String m = nyqVar.m();
        if (TextUtils.isEmpty(m)) {
            LogUtil.c("DevicePairGuideSecondActivity", "enterLeoPairGuide uuid is empty");
            return;
        }
        arrayList.add(m);
        nyqVar.e(arrayList);
        this.p.e(nyqVar, this.d, this.f9020a);
    }

    private void e() {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.huawei.health.action.PAIR_DEVICE_SUCCESS");
            LocalBroadcastManager.getInstance(BaseApplication.getContext()).registerReceiver(this.h, intentFilter);
        } catch (IllegalArgumentException unused) {
            LogUtil.e("DevicePairGuideSecondActivity", "resisterPairReceiver is error");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        k();
        c();
    }

    private void c() {
        Iterator<msq> it = msn.c().b().iterator();
        while (it.hasNext()) {
            msq next = it.next();
            if (next != null && this.q.contains(next.c())) {
                msn.c().c(next);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        try {
            BroadcastReceiver broadcastReceiver = this.f;
            if (broadcastReceiver != null) {
                unregisterReceiver(broadcastReceiver);
                this.f = null;
            }
        } catch (IllegalArgumentException unused) {
            LogUtil.e("DevicePairGuideSecondActivity", "unRegisterNet is error");
        }
    }

    private void n() {
        try {
            if (this.h != null) {
                LogUtil.d("DevicePairGuideSecondActivity", "unregisterBroadcastReceiver mReceiver != null");
                LocalBroadcastManager.getInstance(BaseApplication.getContext()).unregisterReceiver(this.h);
                this.h = null;
            }
        } catch (IllegalArgumentException unused) {
            LogUtil.e("DevicePairGuideSecondActivity", "unRegisterNet is error");
        }
    }

    private void k() {
        l();
        n();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
