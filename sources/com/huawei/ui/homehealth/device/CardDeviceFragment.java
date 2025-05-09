package com.huawei.ui.homehealth.device;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.callback.DeviceStatusCallback;
import com.huawei.common.Constant;
import com.huawei.datatype.DeviceStatusParam;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.clouddevice.CloudSwatchDeviceInfo;
import com.huawei.health.ecologydevice.clouddevice.DownloadCloudDeviceManager;
import com.huawei.health.ecologydevice.util.FAUtil;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.health.marketing.datatype.templates.GridTemplate;
import com.huawei.health.userlabelmgr.api.UserLabelServiceApi;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceManager;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.commonservice.WatchFaceWearService;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice.WatchFaceIdReportInfo;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.util.WatchFaceUtil;
import com.huawei.hwdevice.outofprocess.mgr.device.CloudDeviceInfo;
import com.huawei.hwdevice.phoneprocess.mgr.notification.SensitivePermissionStatus;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.trade.PayApi;
import com.huawei.trade.datatype.DeviceBenefitQueryParam;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.dotspageindicator.HealthDotsPageIndicator;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.device.activity.pairing.EarphonePairActivity;
import com.huawei.ui.device.activity.screenshot.ScreenshotPermissionDialogActivity;
import com.huawei.ui.device.interactors.CompatibilityInteractor;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import com.huawei.ui.device.utlis.BluetoothPermisionUtils;
import com.huawei.ui.device.utlis.clouddevice.DownloadCloudDeviceResource;
import com.huawei.ui.homehealth.device.CardDeviceFragment;
import com.huawei.ui.homehealth.device.adapter.CardDeviceAdapter;
import com.huawei.ui.homehealth.device.adapter.DeviceRecommendedAdapter;
import com.huawei.ui.homehealth.device.callback.HonorPrivacyCallback;
import com.huawei.ui.homehealth.device.callback.IMarketingCallback;
import com.huawei.ui.homehealth.device.callback.WaterMarkCallback;
import com.huawei.ui.homehealth.device.sitting.RecommendedItem;
import com.huawei.ui.homehealth.device.util.CardDeviceFragmentMarketingUtil;
import com.huawei.ui.homewear21.home.manager.hwnfcwalletmgr.WalletAppManager;
import com.huawei.ui.openservice.db.control.OpenServiceControl;
import com.huawei.watchface.api.HwWatchFaceApi;
import defpackage.ceo;
import defpackage.cjv;
import defpackage.cpl;
import defpackage.cpm;
import defpackage.cpo;
import defpackage.cun;
import defpackage.cvt;
import defpackage.cwi;
import defpackage.cww;
import defpackage.gnm;
import defpackage.iyl;
import defpackage.jah;
import defpackage.jfq;
import defpackage.jfu;
import defpackage.jfv;
import defpackage.jgs;
import defpackage.jiw;
import defpackage.job;
import defpackage.jpt;
import defpackage.jpu;
import defpackage.jqf;
import defpackage.jqi;
import defpackage.jre;
import defpackage.jrg;
import defpackage.jrm;
import defpackage.knl;
import defpackage.koq;
import defpackage.njn;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.nuo;
import defpackage.oat;
import defpackage.obb;
import defpackage.obi;
import defpackage.ocp;
import defpackage.oee;
import defpackage.oek;
import defpackage.ofo;
import defpackage.ogj;
import defpackage.ogq;
import defpackage.ogw;
import defpackage.owp;
import defpackage.oxa;
import defpackage.pdz;
import defpackage.pep;
import defpackage.pex;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes6.dex */
public class CardDeviceFragment<T> extends BaseFragment {
    public HealthButton ag;
    public LinearLayout aj;
    public DeviceRecommendedAdapter al;
    public LinearLayout am;
    public String ao;
    public HealthRecycleView ap;
    public HealthRecycleView aq;
    public RelativeLayout ar;
    public HealthRecycleView aw;
    protected ofo ay;
    public RelativeLayout az;
    public DownloadCloudDeviceResource b;
    protected ogw bb;
    public HealthViewPager bc;
    protected View bf;
    public pdz bg;
    private CompatibilityInteractor bh;
    private int bj;
    private DownloadCloudDeviceManager bl;
    private boolean bo;
    private Observer<Boolean> bs;
    private Map<Integer, ResourceResultInfo> bu;
    private PayApi bv;
    private CardDeviceFragmentMarketingUtil bw;
    private WaterMarkCallback by;
    public HealthButton c;
    private UserLabelServiceApi ca;
    public LinearLayout d;
    public LinearLayout f;
    public Context g;
    public LinearLayout h;
    public LinearLayout i;
    public CardDeviceAdapter j;
    public HealthCardView k;
    public HealthCardView m;
    public View o;
    public String p;
    public HealthDotsPageIndicator q;
    public LinearLayout r;
    public ActivityResultLauncher<IntentSenderRequest> t;
    public oek x;
    public boolean y;
    public List<cjv> ak = new ArrayList(16);
    public List<RecommendedItem> as = new ArrayList(16);
    public Handler w = new oee(this);
    public ExecutorService s = Executors.newCachedThreadPool();
    public boolean ab = true;
    public int be = 0;
    protected int at = -1;
    public List<cjv> au = new ArrayList(16);
    public boolean z = false;
    protected boolean ai = false;
    public boolean aa = false;
    public boolean ac = false;
    public boolean af = false;
    public String v = "";
    public ResourceBriefInfo ax = null;
    public GridTemplate u = null;

    /* renamed from: a, reason: collision with root package name */
    public List<SingleGridContent> f9368a = new ArrayList();
    public boolean ae = true;
    private Map<String, LinkedList<String>> bd = new HashMap();
    public final BroadcastReceiver an = new BroadcastReceiver() { // from class: com.huawei.ui.homehealth.device.CardDeviceFragment.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Handler handler = CardDeviceFragment.this.w;
            if (handler == null) {
                LogUtil.h("CardDeviceFragment", "mNonLocalBroadcastReceiver handler is null");
                return;
            }
            LogUtil.a("CardDeviceFragment", "mNonLocalBroadcastReceiver() action：", intent.getAction());
            if (CardDeviceFragment.this.x != null) {
                CardDeviceFragment.this.x.cXQ_(context, intent, CardDeviceFragment.this, handler);
            }
        }
    };
    public final DeviceStatusCallback e = new DeviceStatusCallback() { // from class: com.huawei.ui.homehealth.device.CardDeviceFragment.15
        @Override // com.huawei.callback.DeviceStatusCallback
        public void onDeviceConnectedChanged(DeviceInfo deviceInfo, int i, DeviceStatusParam deviceStatusParam) {
            if (deviceInfo == null) {
                LogUtil.h("CardDeviceFragment", "onDeviceConnectedChanged device == null");
                return;
            }
            LogUtil.a("CardDeviceFragment", "onDeviceConnectedChanged CONNECT_STATE = ", Integer.valueOf(deviceInfo.getDeviceConnectState()), " DeviceIdentify:", CommonUtil.l(deviceInfo.getDeviceIdentify()));
            if (CardDeviceFragment.this.e(deviceInfo.getDeviceConnectState())) {
                Message obtainMessage = CardDeviceFragment.this.w.obtainMessage();
                obtainMessage.what = 46;
                obtainMessage.obj = deviceInfo;
                CardDeviceFragment.this.w.sendMessage(obtainMessage);
            }
        }
    };
    public BroadcastReceiver ah = new BroadcastReceiver() { // from class: com.huawei.ui.homehealth.device.CardDeviceFragment.16
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.a("CardDeviceFragment", "onReceive mLoginStatusReceiver:start");
            if (intent == null) {
                LogUtil.h("CardDeviceFragment", "onReceive mLoginStatusReceiver intent is null");
                return;
            }
            String action = intent.getAction();
            LogUtil.a("CardDeviceFragment", "onReceive mLoginStatusReceiver:", action);
            if (TextUtils.isEmpty(action)) {
                LogUtil.h("CardDeviceFragment", "onReceive mLoginStatusReceiver action is empty");
                return;
            }
            if ("com.huawei.plugin.account.login".equals(action)) {
                LogUtil.a("CardDeviceFragment", "AccountLoginReceiver login sendAccount");
                if (intent.getBooleanExtra(Constant.KEY_IS_REBOOT, false)) {
                    LogUtil.h("CardDeviceFragment", "login sendAccount app reBoot");
                    return;
                } else {
                    ogj.a(CardDeviceFragment.this.aa);
                    CardDeviceFragment.this.t();
                    return;
                }
            }
            if ("com.huawei.plugin.account.logout".equals(action)) {
                CardDeviceFragment.this.ai = false;
                CardDeviceFragment.this.t();
            } else {
                LogUtil.h("CardDeviceFragment", "LocalBroadcast default");
            }
        }
    };
    public final BroadcastReceiver l = new BroadcastReceiver() { // from class: com.huawei.ui.homehealth.device.CardDeviceFragment.20
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Handler handler = CardDeviceFragment.this.w;
            if (handler == null) {
                LogUtil.h("CardDeviceFragment", "mDeviceBatteryReceiver handler is null");
                return;
            }
            if (intent == null || !"com.huawei.bone.action.BATTERY_LEVEL".equals(intent.getAction())) {
                return;
            }
            LogUtil.a("CardDeviceFragment", "mDeviceBatteryReceiver ACTION_BATTERY_LEVEL");
            if (intent.getExtras() != null) {
                handler.sendEmptyMessage(34);
            }
        }
    };
    public final BroadcastReceiver n = new BroadcastReceiver() { // from class: com.huawei.ui.homehealth.device.CardDeviceFragment.19
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("CardDeviceFragment", "mDeviceAppUpdateBroadcastReceiver intent is null");
                return;
            }
            LogUtil.a("CardDeviceFragment", "mDeviceAppUpdateBroadcastReceiver intent action is:", intent.getAction());
            if ("com.huawei.appmarket.action.REFRESH_DEVICE_APP_UPDATE".equals(intent.getAction())) {
                boolean booleanExtra = intent.getBooleanExtra("isEnable", false);
                jiw.a().a(CardDeviceFragment.this.p, booleanExtra);
                Message obtain = Message.obtain();
                obtain.what = 391;
                obtain.arg1 = booleanExtra ? 1 : 0;
                CardDeviceFragment.this.cWZ_(obtain);
            }
        }
    };
    public BroadcastReceiver av = new BroadcastReceiver() { // from class: com.huawei.ui.homehealth.device.CardDeviceFragment.17
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                LogUtil.a("CardDeviceFragment", "mScreenPermissionReceiver  action is:", intent.getAction());
                if ("com.huawei.health.action.SCREEN_SHOT_PERMISSION_QUEST".equals(intent.getAction())) {
                    CardDeviceFragment.this.i();
                } else if (HwWatchFaceApi.ACTION_FOREGROUND_STATUS.equals(intent.getAction())) {
                    CardDeviceFragment.this.i();
                }
            }
        }
    };
    public BroadcastReceiver ba = new BroadcastReceiver() { // from class: com.huawei.ui.homehealth.device.CardDeviceFragment.18
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.a("CardDeviceFragment", "mSimStateChangedReceiver onReceive action");
            if (intent == null || intent.getAction() == null) {
                LogUtil.h("CardDeviceFragment", "intent error");
                return;
            }
            if ("android.intent.action.SIM_STATE_CHANGED".equals(intent.getAction())) {
                Object systemService = BaseApplication.getContext().getSystemService("phone");
                if (!(systemService instanceof TelephonyManager)) {
                    LogUtil.h("CardDeviceFragment", "Context.TELEPHONY_SERVICE object error");
                    return;
                }
                TelephonyManager telephonyManager = (TelephonyManager) systemService;
                if (CardDeviceFragment.this.at == telephonyManager.getSimState()) {
                    LogUtil.h("CardDeviceFragment", "same state, continue");
                    return;
                }
                CardDeviceFragment.this.at = telephonyManager.getSimState();
                LogUtil.a("CardDeviceFragment", "sim state changed, update white list");
                OpenServiceControl.getInstance(context).obtainWatchFaceGrsUrl();
            }
        }
    };
    public HonorPrivacyCallback ad = new HonorPrivacyCallback() { // from class: com.huawei.ui.homehealth.device.CardDeviceFragment.21
        @Override // com.huawei.ui.homehealth.device.callback.HonorPrivacyCallback
        public void signResult(ogq.c cVar) {
            if (cVar == null) {
                LogUtil.h("CardDeviceFragment", "mHonorPrivacyCallback parameter is null.");
                return;
            }
            if (!cVar.b()) {
                LogUtil.h("CardDeviceFragment", "mHonorPrivacyCallback refresh page after delete device.");
                if (CardDeviceFragment.this.j != null) {
                    CardDeviceFragment.this.j.a(false);
                }
                CardDeviceFragment.this.h();
                return;
            }
            int a2 = cVar.a();
            int c = cVar.c();
            LogUtil.a("CardDeviceFragment", "mHonorPrivacyCallback position: ", Integer.valueOf(a2), ", clickType: ", Integer.valueOf(c));
            if (CardDeviceFragment.this.x != null) {
                CardDeviceFragment.this.x.e(cVar, CardDeviceFragment.this, a2, c);
            }
        }
    };
    private List<T> bk = new ArrayList(16);
    private List<cjv> bt = new ArrayList(16);
    private List<cjv> bx = new ArrayList(16);
    private List<CloudSwatchDeviceInfo> bq = new ArrayList(16);
    private int bm = 0;
    private int bi = 0;
    private boolean bn = false;
    private boolean bp = false;
    private boolean br = false;

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e(int i) {
        return i == 1 || i == 2 || i == 3 || i == 4;
    }

    static /* synthetic */ int h(CardDeviceFragment cardDeviceFragment) {
        int i = cardDeviceFragment.bj;
        cardDeviceFragment.bj = i + 1;
        return i;
    }

    protected void i() {
        if (jrm.e(this.g) == PermissionUtil.PermissionResult.GRANTED) {
            jrm.e();
            return;
        }
        boolean d = jrm.d();
        boolean isRunningForeground = BaseApplication.isRunningForeground();
        boolean c = jrm.c();
        if (d && isRunningForeground && c && this.g != null && -1 == com.huawei.haf.application.BaseApplication.c(ScreenshotPermissionDialogActivity.class.getName())) {
            gnm.aPB_(this.g, new Intent(this.g, (Class<?>) ScreenshotPermissionDialogActivity.class));
        }
    }

    public CardDeviceFragment() {
        LogUtil.a("CardDeviceFragment", "CardDeviceFragment()");
    }

    public void a(String str) {
        this.ao = str;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        ogj.d(false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        nsn.a();
    }

    public void cXf_(Message message) {
        LogUtil.a("CardDeviceFragment", "enter updateVipView");
        if (((Integer) message.obj).intValue() != 0) {
            this.bc.setVisibility(8);
        } else {
            c(this.bk);
        }
    }

    public void c(List<T> list) {
        ofo ofoVar = new ofo(this.g, getParentFragment(), this.bc, this);
        this.ay = ofoVar;
        this.bc.setAdapter(ofoVar);
        this.ay.e(list);
        this.bc.setVisibility(0);
        af();
    }

    private void af() {
        HealthDotsPageIndicator healthDotsPageIndicator;
        LogUtil.a("CardDeviceFragment", "startAdAutoScroll enter");
        if (this.bk == null || (healthDotsPageIndicator = this.q) == null) {
            return;
        }
        healthDotsPageIndicator.setViewPager(this.bc);
        if (this.bk.size() > 1) {
            this.q.a(4000);
        } else {
            this.q.c();
        }
    }

    public void cXc_(Message message) {
        LogUtil.a("CardDeviceFragment", "refreshAppMarketCard");
        if (!(message.obj instanceof cpm)) {
            LogUtil.h("CardDeviceFragment", "refreshAppMarketCard message.obj is error");
            return;
        }
        cpm cpmVar = (cpm) message.obj;
        this.bm = message.arg1;
        DeviceCapability c = ogj.c(cpmVar.a());
        if (c != null) {
            LogUtil.a("CardDeviceFragment", "refreshAppMarketCard mAppMarketResult :", Integer.valueOf(this.bm));
            if (this.bm == 1) {
                jiw.a().i();
                c(ogj.c(4, cpmVar));
                jiw.a().b();
            }
            e(cpmVar, c);
            ogj.e(this.as);
            this.ae = true;
            g();
            return;
        }
        g();
    }

    private void d(cpm cpmVar) {
        LogUtil.a("CardDeviceFragment", "addOverseaWalletEntry enter");
        if (LoginInit.getInstance(BaseApplication.getContext()).isMinorAccount()) {
            LogUtil.a("CardDeviceFragment", "addOverseaWalletEntry minor account");
            a(cpmVar);
            return;
        }
        if (WalletAppManager.getInstance().getIsSupportWalletApp(cpmVar.a())) {
            if (!b(3)) {
                this.as.add(ogj.c(3, cpmVar));
            }
            LogUtil.a("CardDeviceFragment", "addOverseaWalletEntry show entry");
        }
        a(cpmVar);
        g(cpmVar);
    }

    private void e(cpm cpmVar, DeviceCapability deviceCapability) {
        if (Utils.o()) {
            d(cpmVar);
            return;
        }
        if (ocp.c(cpmVar.a(), deviceCapability)) {
            for (RecommendedItem recommendedItem : this.as) {
                if (recommendedItem != null && recommendedItem.getId() == 3) {
                    LogUtil.h("CardDeviceFragment", "addPayItem, had add");
                    return;
                }
            }
            this.as.add(ogj.c(3, cpmVar));
        }
        a(cpmVar);
    }

    public boolean b(int i) {
        for (RecommendedItem recommendedItem : this.as) {
            if (recommendedItem != null && recommendedItem.getId() == i) {
                LogUtil.h("CardDeviceFragment", "hadAddRecommendedItem, had add:", Integer.valueOf(i));
                return true;
            }
        }
        return false;
    }

    protected void a(cpm cpmVar) {
        DeviceInfo deviceInfo;
        String e = jah.c().e("domain_play_machine");
        if (!Utils.o() && !TextUtils.isEmpty(e) && jfu.c(cpmVar.i()).z()) {
            LogUtil.h("CardDeviceFragment", "addPlayMachine, isOversea and url is not null");
            if (!b(5) && this.as.size() < 5) {
                this.as.add(ogj.c(5, cpmVar));
            }
        } else {
            LogUtil.h("CardDeviceFragment", "addPlayMachine, isOversea or url is null");
        }
        if (cpmVar == null) {
            this.v = e;
            return;
        }
        String a2 = cpmVar.a();
        if (!TextUtils.isEmpty(a2)) {
            deviceInfo = jpt.e(a2, "CardDeviceFragment");
        } else {
            deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, "CardDeviceFragment");
        }
        a(deviceInfo, e);
    }

    private void a(final DeviceInfo deviceInfo, String str) {
        if (deviceInfo == null) {
            LogUtil.h("CardDeviceFragment", "requestHelpInteractorUrl deviceInfoForWear is null");
            this.v = str;
        } else {
            ExecutorService executorService = this.s;
            if (executorService != null) {
                executorService.execute(new Runnable() { // from class: com.huawei.ui.homehealth.device.CardDeviceFragment.25
                    @Override // java.lang.Runnable
                    public void run() {
                        String d = pex.a().d(deviceInfo);
                        CardDeviceFragment.this.v = d;
                        LogUtil.a("CardDeviceFragment", "requestHelpInteractorUrl helpUrl", d);
                        Handler handler = CardDeviceFragment.this.w;
                        if (handler != null) {
                            Message obtainMessage = handler.obtainMessage();
                            obtainMessage.what = 44;
                            handler.sendMessage(obtainMessage);
                        }
                    }
                });
            }
        }
    }

    public void j(CardDeviceFragment cardDeviceFragment) {
        oek oekVar = this.x;
        if (oekVar != null) {
            oekVar.g(cardDeviceFragment);
        }
    }

    public void cWY_(Message message) {
        LogUtil.a("CardDeviceFragment", "accountSwitch status == ", Integer.valueOf(message.arg1));
        int i = message.arg1;
        if (i != 9) {
            if (i == 10) {
                Object obj = message.obj;
                if (obj instanceof String) {
                    oxa.a().c((String) obj);
                    return;
                }
                return;
            }
            if (i != 13) {
                LogUtil.h("CardDeviceFragment", "message default");
                return;
            }
        }
        oek oekVar = this.x;
        if (oekVar != null) {
            oekVar.a(this);
        }
    }

    public void o() {
        LogUtil.a("CardDeviceFragment", "updateLayout enter updateLayoutIsShow");
        ExecutorService executorService = this.s;
        if (executorService != null) {
            executorService.execute(new Runnable() { // from class: odp
                @Override // java.lang.Runnable
                public final void run() {
                    CardDeviceFragment.this.f();
                }
            });
        }
    }

    public /* synthetic */ void f() {
        DeviceCapability e = DeviceSettingsInteractors.d(BaseApplication.getContext()).e();
        if (e == null) {
            LogUtil.h("CardDeviceFragment", "deviceCapability is null");
            return;
        }
        DeviceInfo f = oxa.a().f();
        if (f == null) {
            LogUtil.h("CardDeviceFragment", "updateLayoutIsShow deviceInfo is null");
            return;
        }
        int productType = f.getProductType();
        if (e.isOtaUpdate() && !cvt.c(productType) && productType != 12) {
            ah();
            return;
        }
        Handler handler = this.w;
        if (handler == null) {
            LogUtil.h("CardDeviceFragment", "updateLayoutIsShow handler == null");
        } else {
            handler.removeMessages(14);
            handler.sendEmptyMessage(14);
        }
    }

    private void ah() {
        jqi.a().getSwitchSetting("wlan_auto_update", new IBaseResponseCallback() { // from class: com.huawei.ui.homehealth.device.CardDeviceFragment.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("CardDeviceFragment", "updateLayoutIsShow errorCode = ", Integer.valueOf(i));
                boolean z = i == 0 && (obj instanceof String) && !"2".equals((String) obj);
                Handler handler = CardDeviceFragment.this.w;
                if (handler == null) {
                    LogUtil.h("CardDeviceFragment", "handler == null");
                    return;
                }
                if (!z) {
                    Message obtainMessage = handler.obtainMessage();
                    obtainMessage.what = 11;
                    handler.sendMessage(obtainMessage);
                } else {
                    handler.removeMessages(14);
                    handler.sendEmptyMessage(14);
                }
            }
        });
    }

    public void c(cpm cpmVar, DeviceCapability deviceCapability) {
        if (WatchFaceUtil.isSupportMyWatch()) {
            this.as.add(ogj.c(6, cpmVar));
        }
        this.bi = 0;
        if (deviceCapability.isSupportMusicInfoList()) {
            this.as.add(ogj.c(2, cpmVar));
        }
        if (deviceCapability.isSupportMarketFace() || deviceCapability.isSupportMarketParams() || cwi.c(jpt.a("CardDeviceFragment"), 44)) {
            d(cpmVar, deviceCapability);
        } else {
            e(cpmVar, deviceCapability);
        }
    }

    private void d(final cpm cpmVar, DeviceCapability deviceCapability) {
        if (cwi.c(jpt.a("CardDeviceFragment"), 44)) {
            this.as.add(ogj.c(4, cpmVar));
            e(cpmVar, deviceCapability);
        } else {
            int size = this.as.size();
            this.bi = size;
            LogUtil.a("CardDeviceFragment", "encapsulationRecommend mAppMarketIndex:", Integer.valueOf(size));
            jiw.a().c(deviceCapability, new IBaseResponseCallback() { // from class: com.huawei.ui.homehealth.device.CardDeviceFragment.2
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("CardDeviceFragment", "showAppMarket errorCode:", Integer.valueOf(i));
                    CardDeviceFragment.this.a(i, cpmVar);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, cpm cpmVar) {
        if (i == 1) {
            LogUtil.a("CardDeviceFragment", "encapsulationRecommend mAppMarketResult:", Integer.valueOf(this.bm));
            Message obtain = Message.obtain();
            obtain.what = 39;
            obtain.arg1 = 1;
            obtain.obj = cpmVar;
            cWZ_(obtain);
            return;
        }
        Message obtain2 = Message.obtain();
        obtain2.what = 39;
        obtain2.arg1 = 2;
        obtain2.obj = cpmVar;
        cWZ_(obtain2);
    }

    protected void cWZ_(Message message) {
        Handler handler = this.w;
        if (handler != null) {
            handler.sendMessage(message);
        }
    }

    private void c(RecommendedItem recommendedItem) {
        boolean d = jiw.a().d(this.p);
        LogUtil.a("CardDeviceFragment", "addAppMarketItem, redDotStatus : ", Boolean.valueOf(d));
        for (RecommendedItem recommendedItem2 : this.as) {
            if (recommendedItem2 != null && recommendedItem2.getId() == 4) {
                LogUtil.h("CardDeviceFragment", "addAppMarketItem, had add");
                recommendedItem2.setIsShowRedDot(d);
                LogUtil.a("CardDeviceFragment", "addAppMarketItem, had add setIsShowRedDot");
                return;
            }
        }
        if (this.bi <= this.as.size()) {
            recommendedItem.setIsShowRedDot(d);
            LogUtil.a("CardDeviceFragment", "addAppMarketItem, add setIsShowRedDot");
            this.as.add(this.bi, recommendedItem);
        }
    }

    public void cXd_(Message message) {
        boolean z = message.arg1 == 1;
        Iterator<RecommendedItem> it = this.as.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            RecommendedItem next = it.next();
            if (next != null && next.getId() == 4) {
                LogUtil.a("CardDeviceFragment", "refreshAppMarketRedDot, get apps card and set red dot, isShowRedDot:", Boolean.valueOf(z));
                next.setIsShowRedDot(z);
                break;
            }
        }
        ogj.e(this.as);
        this.ae = true;
        g();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        this.o = layoutInflater.inflate(R.layout.fragment_device_card, viewGroup, false);
        this.bb = new ogw();
        this.g = getActivity();
        x();
        oek e = oek.e();
        this.x = e;
        e.c(this);
        Handler handler = this.w;
        if (handler != null) {
            handler.sendEmptyMessageDelayed(40, PreConnectManager.CONNECT_INTERNAL);
            this.w.sendEmptyMessageDelayed(43, 5000L);
        }
        UserLabelServiceApi userLabelServiceApi = (UserLabelServiceApi) Services.c("HWUserLabelMgr", UserLabelServiceApi.class);
        this.ca = userLabelServiceApi;
        userLabelServiceApi.registerCallback(4);
        jfv.e();
        this.ca.registerCallback(5);
        this.bv = (PayApi) Services.a("TradeService", PayApi.class);
        ae();
        v();
        ai();
        l();
        ReleaseLogUtil.e("CardDeviceFragment", "onCreateView finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
        w();
        return this.o;
    }

    private void x() {
        this.t = registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(), new ActivityResultCallback() { // from class: com.huawei.ui.homehealth.device.CardDeviceFragment$$ExternalSyntheticLambda4
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                CardDeviceFragment.this.b((ActivityResult) obj);
            }
        });
    }

    /* synthetic */ void b(ActivityResult activityResult) {
        obi.a().e(String.valueOf(activityResult.getResultCode()));
        if (activityResult.getResultCode() == -1) {
            SharedPreferenceManager.c("SENSITIVE_PERMISSION_STATUS", "SENSITIVE_PERMISSION_STATUS", SensitivePermissionStatus.RESTART.getValue());
            obi.a().c();
        } else {
            obb.d(new IBaseResponseCallback() { // from class: com.huawei.ui.homehealth.device.CardDeviceFragment.5
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (i == -1) {
                        obi.a().b(CardDeviceFragment.this.g);
                    } else {
                        obi.a().c();
                    }
                }
            }, getActivity());
        }
    }

    private void w() {
        Context context;
        DeviceInfo a2 = jre.a("CardDeviceFragment");
        if (a2 == null) {
            ReleaseLogUtil.e("CardDeviceFragment", "handleScreenshotDialog connectMainDevice is null");
            return;
        }
        if (a2.getDeviceFactoryReset() == 1) {
            jrm.e();
            ReleaseLogUtil.e("CardDeviceFragment", "handleScreenshotDialog connectMainDevice is RESTORE_FACTORY_SETTING");
        } else if (jrm.e(this.g) != PermissionUtil.PermissionResult.GRANTED) {
            boolean d = jrm.d();
            boolean c = jrm.c();
            if (d && c && (context = this.g) != null) {
                gnm.aPB_(context, new Intent(this.g, (Class<?>) ScreenshotPermissionDialogActivity.class));
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Activity wa_ = com.huawei.haf.application.BaseApplication.wa_();
        if (wa_ == null || wa_ == getActivity()) {
            this.w.post(new Runnable() { // from class: com.huawei.ui.homehealth.device.CardDeviceFragment.3
                @Override // java.lang.Runnable
                public void run() {
                    Activity wa_2 = com.huawei.haf.application.BaseApplication.wa_();
                    if (wa_2 == null || wa_2 != CardDeviceFragment.this.getActivity()) {
                        return;
                    }
                    CardDeviceFragment.this.ad();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ad() {
        if (jpt.d("CardDeviceFragment") != null) {
            jrg.c(this.g, "openMainActivityPage");
        }
    }

    private void ae() {
        WatchFaceManager.getInstance().registerIdReportCb("CardDeviceFragment", new ResponseCallback() { // from class: oea
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                CardDeviceFragment.this.c(i, (WatchFaceIdReportInfo) obj);
            }
        });
    }

    public /* synthetic */ void c(int i, WatchFaceIdReportInfo watchFaceIdReportInfo) {
        if (watchFaceIdReportInfo.getReportStatus() != 1) {
            return;
        }
        if (this.x == null || this.o == null) {
            LogUtil.h("CardDeviceFragment", "onWatchFaceIdReportCb, mHelper or mDeviceCardView is null");
        } else {
            WatchFaceWearService.getInstance().setCurWatchFaceId(watchFaceIdReportInfo.getWatchFaceId());
            LogUtil.a("CardDeviceFragment", "onWatchFaceIdReportCb invoke");
        }
    }

    private void l() {
        if (jfq.c().d().getValue().booleanValue()) {
            return;
        }
        this.bs = new Observer<Boolean>() { // from class: com.huawei.ui.homehealth.device.CardDeviceFragment.9
            @Override // androidx.lifecycle.Observer
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onChanged(Boolean bool) {
                if (bool.booleanValue()) {
                    ReleaseLogUtil.e("CardDeviceFragment", "checkPhoneServiceBind initDeviceList");
                    jfq.c().d().removeObserver(CardDeviceFragment.this.bs);
                    CardDeviceFragment.this.bs = null;
                    CardDeviceFragment.this.h();
                }
            }
        };
        jfq.c().d().observe(getViewLifecycleOwner(), this.bs);
    }

    private void s() {
        boolean c = jqf.c();
        ReleaseLogUtil.e("R_CardDeviceFragment", "getCloudResourceData mIsLoginChange ", Boolean.valueOf(this.ai), " isRemovingDevice:", Boolean.valueOf(c));
        if (this.ai) {
            return;
        }
        if (CommonUtil.bv() || !c) {
            if (this.b == null) {
                this.b = new DownloadCloudDeviceResource();
            }
            this.b.c();
            this.b.d(new DownloadCloudDeviceResource.DeviceResourceCallback() { // from class: com.huawei.ui.homehealth.device.CardDeviceFragment.10
                @Override // com.huawei.ui.device.utlis.clouddevice.DownloadCloudDeviceResource.DeviceResourceCallback
                public void deviceResourceCallbackResultDeviceList(List<CloudDeviceInfo> list) {
                    LogUtil.c("CardDeviceFragment", "deviceResourceCallbackResultDeviceList");
                }

                @Override // com.huawei.ui.device.utlis.clouddevice.DownloadCloudDeviceResource.DeviceResourceCallback
                public void deviceResourceCallbackResult(boolean z) {
                    ReleaseLogUtil.e("R_CardDeviceFragment", "deviceResourceCallback deviceResourceCallback:", Boolean.valueOf(z));
                    if (z && CardDeviceFragment.this.getActivity() != null) {
                        CardDeviceFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.huawei.ui.homehealth.device.CardDeviceFragment.10.4
                            @Override // java.lang.Runnable
                            public void run() {
                                if (CommonUtil.bv()) {
                                    ReleaseLogUtil.e("R_CardDeviceFragment", "set mIsLoginChange true");
                                    CardDeviceFragment.this.ai = true;
                                }
                                CardDeviceFragment.this.h();
                            }
                        });
                    }
                }
            });
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        this.br = z;
        LogUtil.a("CardDeviceFragment", "setUserVisibleHint mIsUserVisible = ", Boolean.valueOf(z));
        if (this.br) {
            q();
            ogj.g();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        View view;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onResume();
        LogUtil.a("CardDeviceFragment", "isTahiti ", Boolean.valueOf(nsn.ag(this.g)));
        ogj.d(true);
        ogj.e(false);
        if (this.ab) {
            n();
        }
        p();
        h();
        a(jpt.e());
        s();
        if (this.br) {
            q();
        }
        r();
        LinearLayout linearLayout = this.h;
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
        u();
        ogq.c();
        oek oekVar = this.x;
        if (oekVar != null && (view = this.o) != null) {
            oekVar.cXN_(this, view);
        }
        ReleaseLogUtil.e("CardDeviceFragment", "onResume finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    private void u() {
        ExecutorService executorService = this.s;
        if (executorService != null) {
            executorService.execute(new Runnable() { // from class: com.huawei.ui.homehealth.device.CardDeviceFragment.7
                @Override // java.lang.Runnable
                public void run() {
                    if (CommonUtil.ce() && owp.o(CardDeviceFragment.this.g) && ogj.f() && owp.m(CardDeviceFragment.this.g) && CardDeviceFragment.this.w != null) {
                        Message obtainMessage = CardDeviceFragment.this.w.obtainMessage();
                        obtainMessage.what = 42;
                        CardDeviceFragment.this.w.sendMessage(obtainMessage);
                    }
                }
            });
        }
    }

    private void p() {
        if (!Utils.i() || Utils.f()) {
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: odn
            @Override // java.lang.Runnable
            public final void run() {
                CardDeviceFragment.this.c();
            }
        });
    }

    public /* synthetic */ void c() {
        FAUtil.c(new FAUtil.ResultCallback() { // from class: ody
            @Override // com.huawei.health.ecologydevice.util.FAUtil.ResultCallback
            public final void onResult(boolean z) {
                CardDeviceFragment.this.d(z);
            }
        });
    }

    public /* synthetic */ void d(boolean z) {
        if (z) {
            ac();
        }
    }

    public void c(cpm cpmVar) {
        ReleaseLogUtil.e("R_CardDeviceFragment", "refreshWatchFace enter");
        if (Utils.o()) {
            ViewStub viewStub = (ViewStub) nsy.cMd_(this.o, R.id.watchface_card);
            if (viewStub != null) {
                LogUtil.a("CardDeviceFragment", "refreshWatchFace ViewStub is loaded");
                this.bf = viewStub.inflate();
            }
            pep.a(cpmVar.i(), this.p, true);
            pdz pdzVar = new pdz(getActivity(), this.p);
            this.bg = pdzVar;
            pdzVar.dmj_(this.bf);
        }
    }

    public void a() {
        LogUtil.a("CardDeviceFragment", "autoScanDevice");
        if (this.bh == null) {
            this.bh = new CompatibilityInteractor();
        }
        this.bh.d(BaseApplication.getContext(), new IBaseResponseCallback() { // from class: com.huawei.ui.homehealth.device.CardDeviceFragment.6
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("CardDeviceFragment", "start auto scan begin:", Integer.valueOf(i), " objectData:", obj);
                if (i == 0 && (obj instanceof Boolean) && ((Boolean) obj).booleanValue()) {
                    LogUtil.a("CardDeviceFragment", "start auto scan device");
                    ThreadPoolManager.d().d("CardDeviceFragment", new Runnable() { // from class: com.huawei.ui.homehealth.device.CardDeviceFragment.6.1
                        @Override // java.lang.Runnable
                        public void run() {
                            nuo.e().c();
                        }
                    });
                }
            }
        });
    }

    private void q() {
        LogUtil.a("CardDeviceFragment", "getCloudDeviceData");
        if (!cww.d()) {
            LogUtil.h("CardDeviceFragment", "getCloudDeviceData not need update");
            return;
        }
        ceo.d().c();
        if (this.bl == null) {
            this.bl = new DownloadCloudDeviceManager();
        }
        this.bl.c(new DownloadCloudDeviceManager.DeviceDataCallback() { // from class: odw
            @Override // com.huawei.health.ecologydevice.clouddevice.DownloadCloudDeviceManager.DeviceDataCallback
            public final void deviceResourceCallbackResult(boolean z) {
                CardDeviceFragment.this.e(z);
            }
        });
    }

    public /* synthetic */ void e(boolean z) {
        LogUtil.a("CardDeviceFragment", "syncDeviceInfoFromProfileCloud CallbackResult isSuccess = ", Boolean.valueOf(z));
        if (z) {
            ac();
        }
    }

    private void ac() {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() { // from class: ods
                @Override // java.lang.Runnable
                public final void run() {
                    CardDeviceFragment.this.h();
                }
            });
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment
    public void initViewTahiti() {
        super.initViewTahiti();
        g();
        z();
    }

    private void v() {
        BaseActivity.setViewSafeRegion(true, this.o);
        oek oekVar = this.x;
        if (oekVar != null) {
            oekVar.cXL_(this, this.o);
            this.x.cXJ_(this, this.o);
            this.x.cXM_(this, this.o);
        }
        this.bc.setIsAutoHeight(true);
    }

    private void ai() {
        if (this.bw == null) {
            this.bw = new CardDeviceFragmentMarketingUtil(this.g);
        }
        this.bw.b(new IMarketingCallback() { // from class: com.huawei.ui.homehealth.device.CardDeviceFragment.8
            @Override // com.huawei.ui.homehealth.device.callback.IMarketingCallback
            public void onSuccess(GridTemplate gridTemplate, ResourceBriefInfo resourceBriefInfo) {
                LogUtil.a("CardDeviceFragment", "requestMarketingData onSuccess");
                CardDeviceFragment.this.ax = resourceBriefInfo;
                CardDeviceFragment.this.u = gridTemplate;
                CardDeviceFragment.this.g();
            }
        });
    }

    public void g() {
        if (this.g == null) {
            LogUtil.b("CardDeviceFragment", "refreshView context is null");
            return;
        }
        LogUtil.a("CardDeviceFragment", "mShowProductInfoLists ", Integer.valueOf(this.au.size()), "mRecommendedLists ", Integer.valueOf(this.as.size()), "mProductInfoLists ", Integer.valueOf(this.ak.size()));
        oek oekVar = this.x;
        if (oekVar != null) {
            oekVar.g(this);
        }
        if (this.ak.size() > 0) {
            this.k.setVisibility(8);
            this.aj.setVisibility(8);
            oek oekVar2 = this.x;
            if (oekVar2 != null) {
                oekVar2.b(this);
            }
            this.am.setVisibility(0);
            if (nsn.ag(this.g) && this.ak.size() == 1) {
                this.i.setVisibility(8);
            } else {
                this.i.setVisibility(0);
            }
            this.c.setVisibility(0);
            if (this.ak.size() <= 2) {
                this.ag.setVisibility(8);
            } else {
                this.ag.setVisibility(0);
            }
        } else {
            t();
        }
        if (this.z) {
            this.ag.setText(this.g.getString(R.string.IDS_device_health_retract));
        } else {
            this.ag.setText(this.g.getString(R.string.IDS_hw_common_ui_xlistview_footer_hint_moredevice));
        }
        oek oekVar3 = this.x;
        if (oekVar3 != null) {
            oekVar3.e(this);
        }
        this.j.c(this.au);
        if (this.ae) {
            this.al.b(this.as);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        Map<Integer, ResourceResultInfo> map;
        oek oekVar;
        LogUtil.a("CardDeviceFragment", "getNoDeviceMarketing");
        if (!this.y && (oekVar = this.x) != null) {
            oekVar.a((CardDeviceFragment) this, false);
        }
        if (this.ak.size() > 0) {
            LogUtil.h("CardDeviceFragment", "mProductInfoLists.size() > 0");
            return;
        }
        if (this.bo && (map = this.bu) != null && map.size() > 0) {
            ab();
            return;
        }
        LogUtil.a("CardDeviceFragment", "getNoDeviceMarketing request");
        this.bo = true;
        final MarketingApi marketingApi = (MarketingApi) Services.c("FeatureMarketing", MarketingApi.class);
        ArrayList arrayList = new ArrayList();
        arrayList.add(4086);
        arrayList.add(4156);
        Task<Map<Integer, ResourceResultInfo>> resourceResultInfo = marketingApi.getResourceResultInfo(arrayList);
        resourceResultInfo.addOnSuccessListener(new OnSuccessListener() { // from class: oeb
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                CardDeviceFragment.this.c(marketingApi, (Map) obj);
            }
        });
        resourceResultInfo.addOnFailureListener(new OnFailureListener() { // from class: oec
            @Override // com.huawei.hmf.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                CardDeviceFragment.this.c(exc);
            }
        });
    }

    public /* synthetic */ void c(MarketingApi marketingApi, Map map) {
        this.bu = marketingApi.filterMarketingRules((Map<Integer, ResourceResultInfo>) map);
        e(marketingApi);
        a(marketingApi);
    }

    public /* synthetic */ void c(Exception exc) {
        LogUtil.h("CardDeviceFragment", "getNoDeviceMarketing request fail");
        this.aj.setVisibility(8);
        oek oekVar = this.x;
        if (oekVar != null) {
            oekVar.a((CardDeviceFragment) this, false);
        }
    }

    private void a(MarketingApi marketingApi) {
        if (this.d == null) {
            LogUtil.h("CardDeviceFragment", "mAddDeviceMarketingLayout is null ");
            return;
        }
        if (!this.bu.containsKey(4156)) {
            LogUtil.h("CardDeviceFragment", "showAddDeviceResource resource is null ");
            this.d.setVisibility(8);
            return;
        }
        if (this.d.getVisibility() == 0 || this.d.getChildCount() != 0) {
            LogUtil.h("CardDeviceFragment", "mAddDeviceMarketingLayout has showed, need not refresh");
            oek oekVar = this.x;
            if (oekVar != null) {
                oekVar.a((CardDeviceFragment) this, true);
                return;
            }
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(4156, this.bu.get(4156));
        List<View> marketingViewList = marketingApi.getMarketingViewList(this.g, hashMap);
        LogUtil.a("CardDeviceFragment", "showAddDeviceResource viewList.size = ", Integer.valueOf(marketingViewList.size()));
        this.d.removeAllViews();
        if (koq.b(marketingViewList)) {
            this.d.setVisibility(8);
        } else {
            this.d.setVisibility(0);
            this.d.addView(marketingViewList.get(0));
        }
    }

    private void e(MarketingApi marketingApi) {
        ExecutorService executorService;
        if (!this.bu.containsKey(4086)) {
            LogUtil.h("CardDeviceFragment", "showNoDeviceLayout resource is null ");
            oek oekVar = this.x;
            if (oekVar != null) {
                oekVar.a((CardDeviceFragment) this, false);
                return;
            }
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(4086, this.bu.get(4086));
        final List<View> marketingViewList = marketingApi.getMarketingViewList(this.g, hashMap);
        LogUtil.a("CardDeviceFragment", "showNoDeviceLayout viewList.size = ", Integer.valueOf(marketingViewList.size()));
        this.aj.removeAllViews();
        if (koq.b(marketingViewList)) {
            this.aj.setVisibility(8);
            oek oekVar2 = this.x;
            if (oekVar2 != null) {
                oekVar2.a((CardDeviceFragment) this, false);
                return;
            }
            return;
        }
        if (this.x == null || (executorService = this.s) == null) {
            return;
        }
        executorService.execute(new Runnable() { // from class: odr
            @Override // java.lang.Runnable
            public final void run() {
                CardDeviceFragment.this.e(marketingViewList);
            }
        });
    }

    public /* synthetic */ void e(final List list) {
        if (!jfv.a().isEmpty()) {
            LogUtil.a("CardDeviceFragment", "showNoDeviceLayout wearInfoList not Empty");
            return;
        }
        FragmentActivity activity = getActivity();
        if (activity == null || activity.isDestroyed() || activity.isFinishing()) {
            LogUtil.a("CardDeviceFragment", "showNoDeviceLayout activity IllegalState");
        } else {
            activity.runOnUiThread(new Runnable() { // from class: odv
                @Override // java.lang.Runnable
                public final void run() {
                    CardDeviceFragment.this.b(list);
                }
            });
        }
    }

    public /* synthetic */ void b(List list) {
        this.aj.removeAllViews();
        oek oekVar = this.x;
        if (oekVar != null) {
            oekVar.a((CardDeviceFragment) this, true);
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            this.aj.addView((View) it.next());
        }
    }

    private void ab() {
        if (this.aj.getVisibility() == 0) {
            LogUtil.a("CardDeviceFragment", "getNoDeviceMarketing mNoDeviceMarketingLayout == View.VISIBLE");
            oek oekVar = this.x;
            if (oekVar != null) {
                oekVar.a((CardDeviceFragment) this, true);
                return;
            }
            return;
        }
        if (this.aj.getChildCount() != 0) {
            LogUtil.a("CardDeviceFragment", "getNoDeviceMarketing mNoDeviceMarketingLayout.getChildCount() != 0");
            oek oekVar2 = this.x;
            if (oekVar2 != null) {
                oekVar2.a((CardDeviceFragment) this, true);
                return;
            }
            return;
        }
        MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi == null) {
            LogUtil.h("CardDeviceFragment", "getNoDeviceMarketing marketingApi is null");
            oek oekVar3 = this.x;
            if (oekVar3 != null) {
                oekVar3.a((CardDeviceFragment) this, false);
                return;
            }
            return;
        }
        LogUtil.a("CardDeviceFragment", "getNoDeviceMarketing mNoDeviceMarketingColumnResultMap != null");
        e(marketingApi);
        a(marketingApi);
    }

    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void h() {
        LogUtil.a("CardDeviceFragment", "enter initList");
        ExecutorService executorService = this.s;
        if (executorService != null) {
            executorService.execute(new Runnable() { // from class: odq
                @Override // java.lang.Runnable
                public final void run() {
                    CardDeviceFragment.this.b();
                }
            });
        }
    }

    public /* synthetic */ void b() {
        List<cjv> arrayList = new ArrayList<>(16);
        ArrayList arrayList2 = new ArrayList(16);
        ogj.a(arrayList, arrayList2, this);
        arrayList.addAll(cww.b());
        ArrayList<cjv> c = ogj.c();
        if (koq.c(c)) {
            d(arrayList, c);
            arrayList.addAll(c);
        }
        Collections.sort(arrayList2);
        Collections.sort(arrayList);
        arrayList.addAll(0, arrayList2);
        Handler handler = this.w;
        if (handler == null) {
            return;
        }
        Message obtainMessage = handler.obtainMessage();
        obtainMessage.obj = arrayList;
        obtainMessage.what = 12;
        handler.sendMessage(obtainMessage);
    }

    private void d(List<cjv> list, ArrayList<cjv> arrayList) {
        Iterator<cjv> it = list.iterator();
        while (it.hasNext()) {
            cjv next = it.next();
            if (next.FT_() != null) {
                String asString = next.FT_().getAsString("mac");
                if (!TextUtils.isEmpty(asString)) {
                    Iterator<cjv> it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        cjv next2 = it2.next();
                        if (next2.FT_() != null && asString.equals(next2.FT_().getAsString("uniqueId"))) {
                            it.remove();
                        }
                    }
                }
            }
        }
    }

    public void j() {
        String str;
        for (cjv cjvVar : this.ak) {
            if (cjvVar.i() instanceof cpm) {
                cpm cpmVar = (cpm) cjvVar.i();
                CardDeviceAdapter cardDeviceAdapter = this.j;
                if (cardDeviceAdapter != null && cardDeviceAdapter.a() && (str = this.ao) != null && str.equals(cpmVar.a())) {
                    cpmVar.a(1);
                }
            }
        }
        z();
        LogUtil.a("CardDeviceFragment", "mProductInfoLists.size() is ", Integer.valueOf(this.ak.size()));
        ogj.d(this.s);
        ExecutorService executorService = this.s;
        if (executorService != null) {
            executorService.execute(new Runnable() { // from class: com.huawei.ui.homehealth.device.CardDeviceFragment.11
                @Override // java.lang.Runnable
                public void run() {
                    DeviceInfo a2 = jpt.a("CardDeviceFragment");
                    if (a2 == null || a2.getDeviceConnectState() != 2) {
                        return;
                    }
                    oxa.a().d();
                    Handler handler = CardDeviceFragment.this.w;
                    if (handler == null) {
                        return;
                    }
                    Message obtainMessage = handler.obtainMessage();
                    obtainMessage.obj = a2;
                    obtainMessage.what = 10;
                    handler.sendMessage(obtainMessage);
                }
            });
        }
    }

    private void z() {
        this.au.clear();
        this.bt.clear();
        this.bx.clear();
        if (nsn.ag(this.g)) {
            if (this.ak.size() == 1) {
                this.au.addAll(this.ak);
                cjv cjvVar = new cjv();
                cjvVar.a(2);
                this.au.add(cjvVar);
            } else if (!this.z && this.ak.size() > 2) {
                this.au.addAll(this.ak.subList(0, 2));
            } else {
                this.au.addAll(this.ak);
            }
        } else if (!this.z && this.ak.size() > 2) {
            this.au.addAll(this.ak.subList(0, 2));
        } else {
            this.au.addAll(this.ak);
        }
        if (this.ak.size() > 0) {
            k();
        } else {
            m();
            g();
        }
        if (!this.bn) {
            this.bn = true;
            if (!SharedPreferenceManager.a(Integer.toString(PrebakedEffectId.RT_FLY), "is_base_service_model", false) && LoginInit.getInstance(BaseApplication.getContext()).getIsLogined()) {
                job.e().b();
                new cpo().b();
            }
        }
        WaterMarkCallback waterMarkCallback = this.by;
        if (waterMarkCallback != null) {
            waterMarkCallback.refreshWaterMark();
        }
    }

    private void k() {
        cjv cjvVar;
        for (cjv cjvVar2 : this.ak) {
            if (cjvVar2 != null) {
                int a2 = cjvVar2.a();
                LogUtil.a("CardDeviceFragment", "deviceType:" + a2);
                if (a2 == 1) {
                    c(cjvVar2);
                } else if (a2 == 4) {
                    List<cjv> h = cjvVar2.h();
                    if (koq.b(h)) {
                        LogUtil.a("CardDeviceFragment", "sportDeviceList is empty");
                    } else {
                        Iterator<cjv> it = h.iterator();
                        while (it.hasNext()) {
                            c(it.next());
                        }
                    }
                }
            }
        }
        if (this.bt.size() > 0) {
            DeviceInfo d = jpt.d("CardDeviceFragment");
            DeviceInfo e = jpu.e("CardDeviceFragment");
            if (d != null) {
                LogUtil.a("CardDeviceFragment", "checkRecommendList deviceId:", CommonUtil.l(d.getDeviceIdentify()));
                cjvVar = ogj.c(d, this.au);
            } else if (e != null) {
                cjvVar = ogj.c(e, this.au);
            } else {
                cjvVar = this.bt.get(0);
            }
        } else if (this.bx.size() > 0) {
            cjvVar = this.bx.get(0);
        } else {
            LogUtil.h("CardDeviceFragment", "no wear device");
            cjvVar = null;
        }
        if (cjvVar != null && cjvVar.a() == 1) {
            cpm cpmVar = (cpm) cjvVar.i();
            b(cpmVar);
            if (cpmVar.e() == 2) {
                d(oxa.a().b(cpmVar.a()));
                return;
            }
            return;
        }
        m();
        g();
    }

    private void c(cjv cjvVar) {
        if (cjvVar == null) {
            LogUtil.a("CardDeviceFragment", "deviceGroupInfo is null");
            return;
        }
        if (cjvVar.a() != 1) {
            LogUtil.a("CardDeviceFragment", "Invalid devicetype");
            return;
        }
        Object i = cjvVar.i();
        if (!(i instanceof cpm)) {
            LogUtil.a("CardDeviceFragment", "Invalid object");
        } else if (((cpm) i).e() == 2) {
            this.bt.add(cjvVar);
        } else {
            this.bx.add(cjvVar);
        }
    }

    private void d(final DeviceInfo deviceInfo) {
        LogUtil.a("CardDeviceFragment", "initMapSdk ");
        ExecutorService executorService = this.s;
        if (executorService == null || deviceInfo == null) {
            return;
        }
        executorService.execute(new Runnable() { // from class: odz
            @Override // java.lang.Runnable
            public final void run() {
                CardDeviceFragment.b(DeviceInfo.this);
            }
        });
    }

    public static /* synthetic */ void b(DeviceInfo deviceInfo) {
        boolean z = false;
        boolean z2 = cwi.c(deviceInfo, 201) || cwi.c(deviceInfo, 202);
        if (deviceInfo != null && deviceInfo.getProductType() == 99 && CommonUtil.bv()) {
            z = true;
        }
        if (!z2 || z) {
            return;
        }
        LogUtil.a("CardDeviceFragment", "OfflineMapEngineManager  initWearEngineAbility");
        oat.c().d(BaseApplication.getContext(), deviceInfo.getDeviceUdid());
    }

    private void m() {
        pdz pdzVar = this.bg;
        if (pdzVar != null) {
            pdzVar.d();
        }
        this.as.clear();
    }

    private void b(cpm cpmVar) {
        LogUtil.a("CardDeviceFragment", "packRecommend");
        this.ae = false;
        this.p = cpmVar.a();
        if (cpmVar.e() == 2) {
            this.ac = true;
            DeviceCapability c = ogj.c(this.p);
            if (c == null) {
                g();
                return;
            }
            if (c.isSupportWatchFace()) {
                pep.a(cpmVar.i(), this.p, true);
            } else {
                pep.c(cpmVar.i(), this.p);
            }
            if ((cpmVar.j() != 1 || !pep.d(cpmVar.i())) && c.isSupportWatchFace()) {
                if (this.be == 1) {
                    this.as.clear();
                    if (!Utils.o()) {
                        this.as.add(ogj.c(1, cpmVar));
                    } else {
                        c(cpmVar);
                    }
                    c(cpmVar, c);
                    ogj.e(this.as);
                    if (c.isSupportMarketFace() || c.isSupportMarketParams()) {
                        return;
                    }
                    this.ae = true;
                    g();
                    return;
                }
                i(cpmVar);
                return;
            }
            m();
            c(cpmVar, c);
            ogj.e(this.as);
            this.ae = true;
            g();
            return;
        }
        e(cpmVar);
    }

    private void e(cpm cpmVar) {
        this.ac = false;
        this.as.clear();
        LogUtil.a("CardDeviceFragment", "dealWatchFaceDisConnect");
        if (pep.a(cpmVar.i(), this.p) && (cpmVar.j() != 1 || !pep.d(cpmVar.i()))) {
            if (this.be == 1) {
                c(cpmVar);
            } else {
                i(cpmVar);
            }
        }
        List<RecommendedItem> a2 = ogj.a();
        if ((a2.size() > 0 ? a2.get(0).getMac() : "").equalsIgnoreCase(knl.a(this.p))) {
            this.as.addAll(a2);
        }
        aa();
        g();
    }

    private void aa() {
        for (RecommendedItem recommendedItem : this.as) {
            if (recommendedItem.getId() == 4) {
                recommendedItem.setIsShowRedDot(jiw.a().d(this.p));
                return;
            }
        }
    }

    public void cXa_(Message message) {
        if (!(message.obj instanceof DeviceInfo)) {
            LogUtil.h("CardDeviceFragment", "handlerDeviceConnectedChanged message.obj not instanceof DeviceInfo");
            return;
        }
        DeviceInfo deviceInfo = (DeviceInfo) message.obj;
        LogUtil.a("CardDeviceFragment", "handlerDeviceConnectedChanged CONNECT_STATE = ", Integer.valueOf(deviceInfo.getDeviceConnectState()), " DeviceIdentify:", CommonUtil.l(deviceInfo.getDeviceIdentify()), " LastConnectedTime:", Long.valueOf(deviceInfo.getLastConnectedTime()));
        LinkedList<String> linkedList = this.bd.get(deviceInfo.getDeviceIdentify());
        if (linkedList == null) {
            linkedList = new LinkedList<>();
            this.bd.put(deviceInfo.getDeviceIdentify(), linkedList);
        }
        linkedList.addLast(String.valueOf(deviceInfo.getLastConnectedTime()) + deviceInfo.getDeviceConnectState());
        cWW_(deviceInfo, this.w);
    }

    public void cXe_(Intent intent, Handler handler) {
        LinkedList<String> linkedList;
        try {
            DeviceInfo deviceInfo = (DeviceInfo) intent.getParcelableExtra("deviceinfo");
            if (deviceInfo != null) {
                int deviceConnectState = deviceInfo.getDeviceConnectState();
                LogUtil.a("CardDeviceFragment", "switchDeviceHandler CONNECT_STATE = ", Integer.valueOf(deviceConnectState), " DeviceIdentify:", CommonUtil.l(deviceInfo.getDeviceIdentify()), " LastConnectedTime:", Long.valueOf(deviceInfo.getLastConnectedTime()));
                if (e(deviceConnectState) && (linkedList = this.bd.get(deviceInfo.getDeviceIdentify())) != null) {
                    if (linkedList.removeFirstOccurrence(String.valueOf(deviceInfo.getLastConnectedTime()) + deviceInfo.getDeviceConnectState())) {
                        LogUtil.a("CardDeviceFragment", "switchDeviceHandler removeFirstOccurrence");
                        return;
                    }
                }
                this.bd.remove(deviceInfo.getDeviceIdentify());
                cWW_(deviceInfo, handler);
                return;
            }
            LogUtil.h("CardDeviceFragment", "mNonLocalBroadcastReceiver() deviceInfo = null");
        } catch (ClassCastException e) {
            LogUtil.b("CardDeviceFragment", "DeviceInfo deviceInfo error :", e.getMessage());
        }
    }

    private void cWW_(DeviceInfo deviceInfo, Handler handler) {
        oek oekVar;
        Message obtainMessage = handler.obtainMessage();
        obtainMessage.obj = deviceInfo.getDeviceIdentify();
        LogUtil.a("CardDeviceFragment", "switchDeviceHandler CONNECT_STATE = ", Integer.valueOf(deviceInfo.getDeviceConnectState()));
        if (deviceInfo.getDeviceConnectState() == 2) {
            obtainMessage.what = 30;
            handler.sendMessage(obtainMessage);
            c(deviceInfo);
            return;
        }
        if (deviceInfo.getDeviceConnectState() == 3 || deviceInfo.getDeviceConnectState() == 4) {
            obtainMessage.what = 32;
            obtainMessage.arg1 = deviceInfo.getProductType();
            obtainMessage.obj = deviceInfo.getDeviceIdentify();
            handler.sendMessage(obtainMessage);
            cpl.c().f(-1);
            return;
        }
        if (deviceInfo.getDeviceConnectState() == 1 || deviceInfo.getDeviceConnectState() == 9 || deviceInfo.getDeviceConnectState() == 13 || deviceInfo.getDeviceConnectState() == 10) {
            obtainMessage.what = 33;
            obtainMessage.arg1 = deviceInfo.getDeviceConnectState();
            handler.sendMessage(obtainMessage);
            handler.sendMessageDelayed(ogj.cZB_(0, deviceInfo.getProductType()), 20000L);
            return;
        }
        if (deviceInfo.getDeviceConnectState() == 6 || deviceInfo.getDeviceConnectState() == 5) {
            handler.removeMessages(36);
            cWX_(deviceInfo, obtainMessage, handler);
            if (deviceInfo.getDeviceConnectState() != 5 || (oekVar = this.x) == null) {
                return;
            }
            oekVar.d(this);
            return;
        }
        if (deviceInfo.getDeviceConnectState() == 11) {
            if (deviceInfo.getDeviceFactoryReset() == 1 && CommonUtil.h(this.g, "com.huawei.health.MainActivity")) {
                ogj.c(this.g, deviceInfo);
                return;
            }
            return;
        }
        if (deviceInfo.getDeviceConnectState() == 12) {
            a(deviceInfo);
        } else if (deviceInfo.getDeviceConnectState() == 34) {
            this.x.b(this, deviceInfo);
        } else {
            LogUtil.h("CardDeviceFragment", "mNonLocalBroadcastReceiver() other state");
        }
    }

    private void a(DeviceInfo deviceInfo) {
        this.x.e(this, deviceInfo);
    }

    private void c(DeviceInfo deviceInfo) {
        boolean c = cwi.c(deviceInfo, 112);
        LogUtil.a("CardDeviceFragment", "afterConnectedProcess. isSupportEarPhone: ", Boolean.valueOf(c));
        if (WatchFaceUtil.isSupportMyWatch(deviceInfo)) {
            LogUtil.a("CardDeviceFragment", "afterConnectedProcess reload watchface params");
            WatchFaceManager.getInstance().refreshWatchFaceInfo(deviceInfo.getDeviceIdentify(), new ResponseCallback() { // from class: odu
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    LogUtil.a("CardDeviceFragment", "afterConnectedProcess reload watchface params finish");
                }
            });
            WatchFaceManager.getInstance().getWearWatchInfoFromDevice(deviceInfo.getDeviceIdentify(), WatchFaceManager.WATCH_FACE_LOCAL_LIST_KEY_PREFIX, new ResponseCallback() { // from class: odt
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    LogUtil.a("CardDeviceFragment", "afterConnectedProcess reload watchface list finish");
                }
            });
        }
        if (c && CommonUtil.h(this.g, "com.huawei.health.MainActivity")) {
            LogUtil.a("CardDeviceFragment", "afterConnectedProcess. in");
            if (!ogj.i()) {
                LogUtil.h("CardDeviceFragment", "afterConnectedProcess. is not currentActivity");
            } else {
                jgs.c().c(deviceInfo, new AnonymousClass14(deviceInfo));
            }
        }
    }

    /* renamed from: com.huawei.ui.homehealth.device.CardDeviceFragment$14, reason: invalid class name */
    public class AnonymousClass14 implements IBaseResponseCallback {
        final /* synthetic */ DeviceInfo c;

        AnonymousClass14(DeviceInfo deviceInfo) {
            this.c = deviceInfo;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (CardDeviceFragment.this.getActivity() != null) {
                FragmentActivity activity = CardDeviceFragment.this.getActivity();
                final DeviceInfo deviceInfo = this.c;
                activity.runOnUiThread(new Runnable() { // from class: oed
                    @Override // java.lang.Runnable
                    public final void run() {
                        CardDeviceFragment.AnonymousClass14.this.d(deviceInfo);
                    }
                });
            }
        }

        public /* synthetic */ void d(DeviceInfo deviceInfo) {
            Intent intent = new Intent();
            intent.setClass(CardDeviceFragment.this.g, EarphonePairActivity.class);
            intent.putExtra("device_identify", deviceInfo.getDeviceIdentify());
            CardDeviceFragment.this.startActivity(intent);
        }
    }

    private void cWX_(DeviceInfo deviceInfo, Message message, Handler handler) {
        Message obtain = Message.obtain();
        obtain.arg1 = deviceInfo.getProductType();
        obtain.what = 36;
        obtain.obj = message.obj;
        handler.sendMessage(obtain);
    }

    private void i(final cpm cpmVar) {
        ExecutorService executorService = this.s;
        if (executorService != null) {
            executorService.execute(new Runnable() { // from class: com.huawei.ui.homehealth.device.CardDeviceFragment.12
                @Override // java.lang.Runnable
                public void run() {
                    boolean isSupportWatchFaceMarket = WatchFaceUtil.isSupportWatchFaceMarket();
                    Message obtain = Message.obtain();
                    obtain.what = 38;
                    obtain.arg1 = isSupportWatchFaceMarket ? 1 : 2;
                    LogUtil.a("CardDeviceFragment", "message.arg1 ", Integer.valueOf(obtain.arg1));
                    obtain.obj = cpmVar;
                    CardDeviceFragment.this.w.sendMessage(obtain);
                }
            });
        }
    }

    private void g(final cpm cpmVar) {
        ReleaseLogUtil.e("R_CardDeviceFragment", "requestOverseaWallet enter");
        if (this.s == null) {
            LogUtil.a("CardDeviceFragment", "requestOverseaWallet mGetDataService is null");
        } else {
            WalletAppManager.getInstance().getValidWalletApplication(cpmVar.a(), new ResponseCallback() { // from class: odx
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    CardDeviceFragment.this.b(cpmVar, i, (List) obj);
                }
            });
        }
    }

    public /* synthetic */ void b(cpm cpmVar, int i, List list) {
        int i2;
        ReleaseLogUtil.e("R_CardDeviceFragment", "requestOverseaWallet get valid wallet");
        if (!koq.b(list)) {
            ReleaseLogUtil.e("R_CardDeviceFragment", "requestOverseaWallet is support");
            i2 = 1;
        } else {
            ReleaseLogUtil.e("R_CardDeviceFragment", "requestOverseaWallet not support");
            i2 = 2;
        }
        Message obtain = Message.obtain();
        obtain.what = 47;
        obtain.arg1 = i2;
        LogUtil.a("CardDeviceFragment", "requestOverseaWallet message:", Integer.valueOf(obtain.arg1));
        obtain.obj = cpmVar;
        this.w.sendMessage(obtain);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("CardDeviceFragment", "onDestroy ", this.g);
        DownloadCloudDeviceResource downloadCloudDeviceResource = this.b;
        if (downloadCloudDeviceResource != null) {
            downloadCloudDeviceResource.d();
            this.b = null;
        }
        UserLabelServiceApi userLabelServiceApi = this.ca;
        if (userLabelServiceApi != null) {
            userLabelServiceApi.unRegisterCallback(4);
            this.ca.unRegisterCallback(5);
        }
        if (this.bh != null) {
            this.bh = null;
        }
        List<CloudSwatchDeviceInfo> list = this.bq;
        if (list != null) {
            list.clear();
            this.bq = null;
        }
        DownloadCloudDeviceManager downloadCloudDeviceManager = this.bl;
        if (downloadCloudDeviceManager != null) {
            downloadCloudDeviceManager.b();
            this.bl = null;
        }
        oek oekVar = this.x;
        if (oekVar != null) {
            oekVar.f(this);
            oek.e();
            oek.d();
            this.x = null;
        }
        pdz pdzVar = this.bg;
        if (pdzVar != null) {
            pdzVar.onDestroy();
        }
        if (this.bs != null) {
            jfq.c().d().removeObserver(this.bs);
            this.bs = null;
        }
        WatchFaceManager.getInstance().unregisterIdReportCb("CardDeviceFragment");
    }

    private void n() {
        LogUtil.a("CardDeviceFragment", "enter checkEnableDevice");
        ExecutorService executorService = this.s;
        if (executorService != null) {
            executorService.execute(new Runnable() { // from class: oef
                @Override // java.lang.Runnable
                public final void run() {
                    CardDeviceFragment.this.d();
                }
            });
        }
    }

    public /* synthetic */ void d() {
        DeviceInfo deviceInfo;
        if (iyl.d().g() != 3) {
            LogUtil.h("CardDeviceFragment", "checkEnableDevice bluetooth off");
            return;
        }
        if (!BluetoothPermisionUtils.e(getActivity())) {
            LogUtil.h("CardDeviceFragment", "checkEnableDevice nearby permisison Denied");
            return;
        }
        List<DeviceInfo> h = cpl.c().h();
        if (h == null || h.size() <= 0) {
            LogUtil.h("CardDeviceFragment", "checkEnableDevice return");
            return;
        }
        Iterator<cpm> it = jfv.a().iterator();
        int i = 0;
        while (it.hasNext() && (!obb.e(it.next().i()) || (i = i + 1) <= 1)) {
        }
        Iterator<DeviceInfo> it2 = h.iterator();
        while (true) {
            if (!it2.hasNext()) {
                deviceInfo = null;
                break;
            }
            deviceInfo = it2.next();
            if (i != 2 || !obb.e(deviceInfo.getProductType())) {
                if (deviceInfo.getDeviceConnectState() == 3 || deviceInfo.getDeviceConnectState() == 4) {
                    if (deviceInfo.getDeviceActiveState() == 1 && deviceInfo.getDeviceBluetoothType() == 2) {
                        break;
                    }
                }
            }
        }
        e(h, deviceInfo);
    }

    private void e(List<DeviceInfo> list, DeviceInfo deviceInfo) {
        LogUtil.a("CardDeviceFragment", "autoReconnectDevice");
        if (deviceInfo != null) {
            LogUtil.a("CardDeviceFragment", "reconnect active bleDeviceInfo ", deviceInfo);
            deviceInfo.setDeviceConnectState(1);
            oxa.a().e(list, deviceInfo.getDeviceIdentify());
            Handler handler = this.w;
            if (handler == null) {
                return;
            }
            Message obtainMessage = handler.obtainMessage();
            obtainMessage.obj = deviceInfo;
            obtainMessage.what = 13;
            handler.sendMessage(obtainMessage);
        }
    }

    private void r() {
        if (this.bp || !this.br) {
            return;
        }
        List<DeviceInfo> h = cpl.c().h();
        if (h == null || h.isEmpty()) {
            LogUtil.h("CardDeviceFragment", "enter applyLocationPermission deviceList is null");
            return;
        }
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (DeviceInfo deviceInfo : h) {
            if (deviceInfo != null && deviceInfo.getDeviceActiveState() == 1 && deviceInfo.getDeviceConnectState() == 2) {
                ogj.d(deviceInfo, arrayList);
                z = true;
            }
        }
        if (!z || arrayList.size() <= 0) {
            return;
        }
        LogUtil.a("CardDeviceFragment", "checkPermissions permissionsList size ", Integer.valueOf(arrayList.size()));
        String[] strArr = new String[arrayList.size()];
        arrayList.toArray(strArr);
        if (pep.dmV_(getActivity(), strArr, null)) {
            this.bp = true;
            CommonUtil.k(BaseApplication.getContext(), "android.permission.ACCESS_COARSE_LOCATION");
        }
    }

    public void b(WaterMarkCallback waterMarkCallback) {
        this.by = waterMarkCallback;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 102) {
            obb.d(this.g);
        }
    }

    public void cXb_(Intent intent) {
        if (intent == null) {
            LogUtil.h("CardDeviceFragment", "markConnectingDevice intent is null.");
            return;
        }
        if ("com.huawei.health.action.CLOUD_CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
            LogUtil.h("CardDeviceFragment", "markConnectingDevice cloud device no handle.");
            return;
        }
        Parcelable parcelableExtra = intent.getParcelableExtra("deviceinfo");
        if (!(parcelableExtra instanceof DeviceInfo)) {
            LogUtil.h("CardDeviceFragment", "markConnectingDevice parcelable not instanceof DeviceInfo");
            return;
        }
        int deviceConnectState = ((DeviceInfo) parcelableExtra).getDeviceConnectState();
        if (deviceConnectState == 2 || deviceConnectState == 3 || deviceConnectState == 4) {
            this.aa = false;
        } else {
            this.aa = true;
        }
    }

    public void a(List<DeviceInfo> list) {
        if (this.bv == null) {
            LogUtil.h("CardDeviceFragment", "resetShowVipTipView mPayApi is null");
            return;
        }
        if (list == null || list.isEmpty()) {
            LogUtil.h("CardDeviceFragment", "resetShowVipTipView newDeviceInfo is null");
            this.w.sendMessage(ogj.cZz_(-1));
        } else {
            LogUtil.a("CardDeviceFragment", "enter resetShowVipTipView");
            d(list);
        }
    }

    private void d(final List<DeviceInfo> list) {
        this.bk.clear();
        this.bj = 0;
        for (final DeviceInfo deviceInfo : list) {
            LogUtil.a("CardDeviceFragment", "enter addAllDataInfoList");
            this.bv.getDeviceBenefits(njn.e(deviceInfo, DeviceBenefitQueryParam.DeviceBenefitType.DEVICE_BENEFIT_TYPE_ALL, true), new IBaseResponseCallback() { // from class: com.huawei.ui.homehealth.device.CardDeviceFragment.13
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    List<T> e = CardDeviceFragment.this.bb.e(i, obj, deviceInfo);
                    LogUtil.a("CardDeviceFragment", "addAllDataInfoList newAllDataInfoList size is ", Integer.valueOf(e.size()));
                    CardDeviceFragment.this.bk.addAll(e);
                    LogUtil.a("CardDeviceFragment", "addAllDataInfoList mAllDataInfoList size is ", Integer.valueOf(CardDeviceFragment.this.bk.size()));
                    CardDeviceFragment.h(CardDeviceFragment.this);
                    if (CardDeviceFragment.this.bj == list.size()) {
                        LogUtil.a("CardDeviceFragment", "addAllDataInfoList mAllDataInfoListNew size is ", Integer.valueOf(CardDeviceFragment.this.bk.size()));
                        CardDeviceFragment.this.bb.cZY_(CardDeviceFragment.this.w, CardDeviceFragment.this.bk);
                        if (CardDeviceFragment.this.bk.isEmpty()) {
                            CardDeviceFragment.this.c(true);
                        } else {
                            CardDeviceFragment.this.c(false);
                        }
                    }
                }
            });
        }
    }

    public void c(boolean z) {
        LogUtil.a("CardDeviceFragment", "showVipRedDot isAllActive is:", Boolean.valueOf(z), " update red dot");
        if (getParentFragment() instanceof DeviceFragment) {
            DeviceFragment deviceFragment = (DeviceFragment) getParentFragment();
            if (y()) {
                deviceFragment.d(false);
            } else if (!z) {
                deviceFragment.d(true);
            } else {
                deviceFragment.d(false);
            }
        }
    }

    private boolean y() {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        String e = SharedPreferenceManager.e(Integer.toString(10008), "equity_click_red_dot_status", "");
        if (TextUtils.isEmpty(e) || !e.contains(",")) {
            LogUtil.h("CardDeviceFragment", "showVipRedDot deviceStrategyIds is empty");
            return false;
        }
        List<String> asList = Arrays.asList(e.split(","));
        LogUtil.a("CardDeviceFragment", "showVipRedDot deviceStrategyIdList size is:", Integer.valueOf(asList.size()));
        boolean z = false;
        for (String str : asList) {
            z = SharedPreferenceManager.a(Integer.toString(10008), "equity_click_red_dot_status" + accountInfo + str, false);
            if (z) {
                break;
            }
        }
        return z;
    }
}
