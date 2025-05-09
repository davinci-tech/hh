package defpackage;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.dictionary.constants.ProductMap;
import com.huawei.hihealth.dictionary.constants.ProductMapInfo;
import com.huawei.hihealth.dictionary.utils.ProductMapParseUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.util.WatchFaceUtil;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.maps.offlinedata.health.init.OfflineMapInitManager;
import com.huawei.maps.offlinedata.health.init.OnCheckUpdatesListener;
import com.huawei.maps.offlinedata.health.init.OnCreateResultListener;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.trade.PayApi;
import com.huawei.trade.datatype.DeviceBenefitQueryParam;
import com.huawei.trade.datatype.DeviceBenefits;
import com.huawei.trade.datatype.DeviceInboxInfo;
import com.huawei.trade.datatype.RepeatResourceBenefitInfo;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.device.activity.intelligenthome.IntelligentHomeLinkageActivity;
import com.huawei.ui.homewear21.home.WearHomeActivity;
import com.huawei.ui.homewear21.home.adapter.GridSpacingItemDecoration;
import com.huawei.ui.homewear21.home.adapter.WearHomeFeatureCardAdapter;
import com.huawei.ui.homewear21.home.card.WearHomeBaseCard;
import com.huawei.ui.homewear21.home.card.WechatDeviceProviderHelper;
import com.huawei.ui.homewear21.home.holder.WearHomeFeatureHolder;
import com.huawei.ui.homewear21.home.listener.WearHomeListener;
import com.huawei.ui.homewear21.home.manager.hwnfcwalletmgr.WalletAppManager;
import com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback.DataCallback;
import com.huawei.wear.oversea.overseamanger.SatcomQueryCallBack;
import com.huawei.wear.oversea.satcomcard.SatcomCardSupportInfo;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.KeyValDbManager;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.ToIntFunction;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class pbu extends WearHomeBaseCard {

    /* renamed from: a, reason: collision with root package name */
    private static String f16055a = "";
    private static final Object d = new Object();
    private pad f;
    private boolean i;
    private WearHomeFeatureCardAdapter r;
    private WearHomeFeatureHolder p = null;
    private List<pbo> k = new ArrayList(16);
    private boolean j = false;
    private boolean n = false;
    private String g = "";
    private String h = "";
    private String e = "";
    private String c = "";
    private String s = "";
    private ock l = null;
    private volatile boolean m = false;
    private boolean o = false;
    private final BroadcastReceiver b = new BroadcastReceiver() { // from class: pbu.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("WearHomeFeatureCard", "mDeviceAppUpdateBroadcastReceiver intent is null");
                return;
            }
            LogUtil.a("WearHomeFeatureCard", "mDeviceAppUpdateBroadcastReceiver intent action is:", intent.getAction());
            if ("com.huawei.appmarket.action.REFRESH_DEVICE_APP_UPDATE".equals(intent.getAction())) {
                pbu.this.a(intent.getBooleanExtra("isEnable", false));
            }
        }
    };

    public pbu(Context context, WearHomeActivity wearHomeActivity) {
        this.mContext = context;
        this.mActivity = wearHomeActivity;
        this.f = new pad(context, wearHomeActivity);
    }

    @Override // com.huawei.ui.homewear21.home.card.WearHomeBaseCard
    public RecyclerView.ViewHolder getCardViewHolder(ViewGroup viewGroup, LayoutInflater layoutInflater) {
        this.p = new WearHomeFeatureHolder(layoutInflater.inflate(R.layout.wear_home_feature_layout, viewGroup, false));
        this.r = new WearHomeFeatureCardAdapter(this.mContext, this.k);
        this.p.e().setLayoutManager(ai());
        this.p.e().addItemDecoration(af());
        this.p.e().setAdapter(this.r);
        this.p.e().setLayerType(2, null);
        this.p.e().setItemAnimator(new DefaultItemAnimator());
        this.r.b(new WearHomeListener() { // from class: pbs
            @Override // com.huawei.ui.homewear21.home.listener.WearHomeListener
            public final void onItemClick(int i) {
                pbu.this.b(i);
            }
        });
        ax();
        return this.p;
    }

    @Override // com.huawei.ui.homewear21.home.card.WearHomeBaseCard
    public void deviceConnectionChange(int i) {
        if (this.mActivity == null || this.p == null || this.r == null) {
            LogUtil.h("WearHomeFeatureCard", "deviceConnectionChange fail mActivity:", this.mActivity, " mWearHomeFeatureHolder:", this.p, " mWearHomeFeatureCardAdapter:", this.r);
            return;
        }
        this.mActivity.f9644a = this.mActivity.d.e(this.mActivity.g);
        if (this.mActivity.f9644a == null) {
            this.p.e().setVisibility(8);
            return;
        }
        this.mActivity.b = oxa.a().b(this.mActivity.g);
        if (this.mActivity.b == null) {
            return;
        }
        ak();
        if (this.k.size() == 0) {
            this.p.e().setVisibility(8);
            return;
        }
        this.p.e().setVisibility(0);
        LogUtil.a("WearHomeFeatureCard", "deviceConnectionChange state:", Integer.valueOf(i));
        l(i);
    }

    private void aw() {
        this.r.a(this.k);
        this.p.e().getRecycledViewPool().clear();
        this.p.e().setLayoutManager(ai());
        this.p.e().setAdapter(this.r);
    }

    public void e(int i) {
        this.f.b(i);
    }

    public void k() {
        LogUtil.a("WearHomeFeatureCard", "checkLocationServiceStatus() ENTER");
        if (this.mActivity == null) {
            LogUtil.h("WearHomeFeatureCard", "mActivity is null");
            return;
        }
        if (!pep.a("wear_local_service_time")) {
            LogUtil.a("WearHomeFeatureCard", "showGpsNoteDialog <48h");
            return;
        }
        if (this.mActivity.d == null) {
            LogUtil.h("WearHomeFeatureCard", "checkLocationServiceStatus() if (mDeviceInteractors null)");
            g(R.string._2130846626_res_0x7f0223a2);
        } else {
            this.mActivity.f9644a = this.mActivity.d.e(this.mActivity.g);
            g(R.string._2130846626_res_0x7f0223a2);
        }
    }

    private void g(final int i) {
        if (this.i) {
            LogUtil.h("WearHomeFeatureCard", "gps dialog is open");
        } else {
            this.mActivity.runOnUiThread(new Runnable() { // from class: pcn
                @Override // java.lang.Runnable
                public final void run() {
                    pbu.this.c(i);
                }
            });
        }
    }

    /* synthetic */ void c(int i) {
        a(i, 101);
    }

    private void a(int i, final int i2) {
        this.i = true;
        LogUtil.a("WearHomeFeatureCard", "showGpsDialog() mIsGpsOpenDialog is open");
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.mContext).e(this.mContext.getString(i)).czC_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: pcm
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                pbu.this.dlz_(i2, view);
            }
        }).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: pcq
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                pbu.this.dlA_(view);
            }
        }).e();
        e.setCanceledOnTouchOutside(false);
        e.setCancelable(false);
        if (e.isShowing() || this.mActivity.isFinishing()) {
            return;
        }
        e.show();
        KeyValDbManager.b(BaseApplication.getContext()).e("wear_local_service_time", String.valueOf(System.currentTimeMillis()));
    }

    /* synthetic */ void dlz_(int i, View view) {
        this.i = false;
        LogUtil.a("WearHomeFeatureCard", "showGPSSettingDialog() setPositiveButton");
        Intent intent = new Intent();
        intent.setAction("android.settings.LOCATION_SOURCE_SETTINGS");
        try {
            dly_(intent, i);
        } catch (ActivityNotFoundException unused) {
            intent.setAction("android.settings.SETTINGS");
            try {
                dly_(intent, i);
            } catch (ActivityNotFoundException unused2) {
                LogUtil.b("WearHomeFeatureCard", "startActivity exception");
            }
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void dlA_(View view) {
        LogUtil.a("WearHomeFeatureCard", "showGpsDialog onclick NegativeButton");
        this.i = false;
        ViewClickInstrumentation.clickOnView(view);
    }

    private void dly_(Intent intent, int i) throws ActivityNotFoundException {
        ResolveInfo resolveActivity;
        PackageManager packageManager = this.mActivity.getPackageManager();
        if (packageManager == null || (resolveActivity = packageManager.resolveActivity(intent, 65536)) == null) {
            return;
        }
        intent.setComponent(new ComponentName(resolveActivity.activityInfo.packageName, resolveActivity.activityInfo.name));
        this.mActivity.startActivityForResult(intent, i);
    }

    private void l(int i) {
        if (i == 2) {
            z();
        } else {
            for (pbo pboVar : this.k) {
                LogUtil.c("WearHomeFeatureCard", "checkIsOtaEnd OTA item isEnable");
                pboVar.c(false);
                if (pboVar.a() == 129 || pboVar.a() == 128 || pboVar.a() == 107) {
                    pboVar.c(true);
                } else if (pboVar.a() == 101) {
                    DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, "R_WearHomeFeatureCard");
                    DeviceInfo deviceInfo2 = this.mActivity.b;
                    if (deviceInfo != null && deviceInfo2 != null && Objects.equals(deviceInfo.getDeviceIdentify(), deviceInfo2.getDeviceIdentify())) {
                        pboVar.c(true);
                    }
                }
            }
        }
        aw();
    }

    private void z() {
        if (this.mActivity.b != null && cvt.c(this.mActivity.b.getProductType())) {
            am();
            return;
        }
        if (ar()) {
            LogUtil.a("WearHomeFeatureCard", "refreshSettingView() wear device is OTA");
            for (pbo pboVar : this.k) {
                pboVar.c(false);
                if (pboVar.a() == 129 || pboVar.a() == 128 || pboVar.a() == 107) {
                    pboVar.c(true);
                }
                if (this.mActivity.k && (pboVar.a() == 115 || pboVar.a() == 114)) {
                    pboVar.c(true);
                }
            }
            return;
        }
        this.m = false;
        for (pbo pboVar2 : this.k) {
            if (this.mActivity.b != null && !pep.d(this.mActivity.b.getProductType())) {
                pboVar2.c(true);
            } else if (!this.mActivity.k) {
                pboVar2.c(true);
            } else {
                pboVar2.c(false);
                if (c(pboVar2)) {
                    pboVar2.c(true);
                }
            }
        }
    }

    private boolean c(pbo pboVar) {
        int a2 = pboVar.a();
        if (a2 != 107 && a2 != 128 && a2 != 129) {
            switch (a2) {
                case 113:
                case 114:
                case 115:
                    break;
                default:
                    LogUtil.h("WearHomeFeatureCard", "isEnable default");
                    return false;
            }
        }
        return true;
    }

    private void am() {
        if (ar()) {
            LogUtil.a("WearHomeFeatureCard", "refreshSettingView() AW70 is OTA");
            for (pbo pboVar : this.k) {
                pboVar.c(false);
                if (pboVar.a() == 129 || pboVar.a() == 107) {
                    pboVar.c(true);
                }
            }
        }
    }

    private boolean ar() {
        return this.mActivity.d.e(this.mActivity.g) != null && this.mActivity.d.e(this.mActivity.g).isOtaUpdate() && jkj.d().c(this.mActivity.g) == 6;
    }

    @Override // com.huawei.ui.homewear21.home.card.WearHomeBaseCard
    public void onResume() {
        LogUtil.a("WearHomeFeatureCard", "onResume");
        an();
    }

    @Override // com.huawei.ui.homewear21.home.card.WearHomeBaseCard
    public void onDestroy() {
        LogUtil.a("WearHomeFeatureCard", "onDestroy");
        pep.dmZ_(this.b);
        this.j = false;
    }

    public void l() {
        LogUtil.a("WearHomeFeatureCard", "updateFeatureCard");
        an();
    }

    public static String e() {
        return f16055a;
    }

    private GridLayoutManager ai() {
        if (this.r.getItemCount() == 1) {
            GridLayoutManager d2 = d(1);
            WearHomeFeatureHolder.a(1);
            return d2;
        }
        if (this.r.getItemCount() == 2) {
            GridLayoutManager d3 = d(2);
            WearHomeFeatureHolder.a(2);
            return d3;
        }
        if (nsn.ag(this.mContext)) {
            GridLayoutManager d4 = d(3);
            WearHomeFeatureHolder.a(3);
            return d4;
        }
        GridLayoutManager d5 = d(2);
        WearHomeFeatureHolder.a(2);
        return d5;
    }

    private GridLayoutManager d(int i) {
        return new GridLayoutManager(this.mContext, i, 1, false) { // from class: pbu.3
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return false;
            }
        };
    }

    private void ax() {
        LogUtil.a("WearHomeFeatureCard", "onUiCreate");
        this.f.c();
        an();
    }

    private void an() {
        if (this.mActivity == null || this.p == null || this.r == null) {
            LogUtil.h("WearHomeFeatureCard", "initView fail mActivity:", this.mActivity, " mWearHomeFeatureHolder:", this.p, " mWearHomeFeatureCardAdapter:", this.r);
            return;
        }
        if (this.mActivity.f9644a == null) {
            this.p.e().setVisibility(8);
            LogUtil.h("WearHomeFeatureCard", "initView fail mActivity.mDeviceCapability is null");
            return;
        }
        this.mActivity.b = oxa.a().b(this.mActivity.g);
        if (this.mActivity.b == null) {
            LogUtil.h("WearHomeFeatureCard", "initView fail mActivity.mCurrentDeviceInfo is null");
        } else {
            ak();
            bj();
        }
    }

    private void bj() {
        this.mActivity.b = oxa.a().b(this.mActivity.g);
        if (this.k.size() == 0 || this.mActivity.b == null) {
            LogUtil.h("WearHomeFeatureCard", "updateWearHomeFeatureCard fail mWearHomeFeatureBeans size is 0");
            this.p.e().setVisibility(8);
        } else {
            this.p.e().setVisibility(0);
            l(this.mActivity.b.getDeviceConnectState());
        }
    }

    private GridSpacingItemDecoration af() {
        return new GridSpacingItemDecoration(false, nrr.e(this.mContext, 0.0f));
    }

    private void bd() {
        if (Utils.n()) {
            ReleaseLogUtil.e("R_WearHomeFeatureCard", "hide map by country");
            return;
        }
        boolean z = cwi.c(this.mActivity.b, 201) || cwi.c(this.mActivity.b, 202);
        ReleaseLogUtil.e("R_WearHomeFeatureCard", "showOfflineMapCard enter ", Boolean.valueOf(z));
        if (z) {
            ReleaseLogUtil.e("R_WearHomeFeatureCard", "mCurrentDeviceInfo getProductType", Integer.valueOf(this.mActivity.b.getProductType()));
            if (this.mActivity.b.getProductType() == 99 && CommonUtil.bv()) {
                return;
            }
            int deviceConnectState = this.mActivity.b.getDeviceConnectState();
            LogUtil.a("WearHomeFeatureCard", "onReceive connectState ", Integer.valueOf(deviceConnectState));
            if (deviceConnectState == 2 && a(111) == null) {
                oat.c().d(BaseApplication.getContext(), this.mActivity.b.getDeviceUdid());
                bg();
            } else if (this.o) {
                d(false);
            }
            b(111, this.mActivity.getString(R.string.IDS_device_home_offline_map), R.mipmap._2131821040_res_0x7f1101f0, this.mActivity.i);
        }
    }

    private void bg() {
        LogUtil.a("WearHomeFeatureCard", "OfflineMapInitManager checkForUpdates");
        OfflineMapInitManager.getInstance().checkForUpdates(this.mActivity.getApplicationContext(), this.mActivity.b.getDeviceUdid(), new OnCheckUpdatesListener() { // from class: pcs
            @Override // com.huawei.maps.offlinedata.health.init.OnCheckUpdatesListener
            public final void onResult(boolean z) {
                pbu.this.d(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        LogUtil.h("WearHomeFeatureCard", "OfflineMapInitManager updateMapItem: isShowRedDot ", Boolean.valueOf(z));
        pbo a2 = a(111);
        if (a2 != null) {
            LogUtil.a("WearHomeFeatureCard", "OfflineMapInitManager  isEnable", Boolean.valueOf(a2.j()));
            a2.d(z);
            a(a2);
            return;
        }
        LogUtil.h("WearHomeFeatureCard", "DeviceSettingConstants.ID_DEVICE_OFFLINE_MAP is null");
    }

    private void ak() {
        this.k.clear();
        ad();
        s();
        DeviceInfo deviceInfo = this.mActivity.b;
        if (WatchFaceUtil.isSupportMyWatch(deviceInfo)) {
            b(101, this.mActivity.getString(R.string._2130847219_res_0x7f0225f3), R.mipmap._2131821118_res_0x7f11023e, true);
        }
        if (as()) {
            b(116, this.mActivity.getString(R.string.IDS_device_home_health_monitor), R.drawable._2131430429_res_0x7f0b0c1d, this.mActivity.i);
        }
        v();
        if (deviceInfo == null) {
            LogUtil.h("WearHomeFeatureCard", "showItemCaseOne: currentDeviceInfo is null");
        } else {
            String deviceName = deviceInfo.getDeviceName();
            ReleaseLogUtil.e("R_WearHomeFeatureCard", "showHeartIndexItem currentDeviceName:", deviceName);
            if (!TextUtils.isEmpty(deviceName) && deviceInfo.getProductType() == 11 && deviceName.contains("HUAWEI CM-R1P")) {
                ba();
            }
        }
        ReleaseLogUtil.e("R_WearHomeFeatureCard", "TYPE_MUSIC isSupportMusicInfoList:", Boolean.valueOf(this.mActivity.f9644a.isSupportMusicInfoList()));
        if (this.mActivity.f9644a.isSupportMusicInfoList()) {
            b(110, this.mActivity.getString(R.string._2130842049_res_0x7f0211c1), R.drawable._2131430203_res_0x7f0b0b3b, this.mActivity.i);
        }
        boolean c = jhb.c(this.mActivity.g);
        ReleaseLogUtil.e("R_WearHomeFeatureCard", "SUPPORT_FAMILY_MODE_HEART isSupportFamilyModeHeart:", Boolean.valueOf(c), " PairMode", Integer.valueOf(jpo.c(this.mActivity.g)));
        if (c && jhb.d(this.mActivity.g)) {
            b(117, this.mActivity.getString(R.string._2130843118_res_0x7f0215ee), R.mipmap._2131821111_res_0x7f110237, this.mActivity.i);
        }
        ReleaseLogUtil.e("R_WearHomeFeatureCard", "initAppMarketCard isSupportMarketFace:", Boolean.valueOf(this.mActivity.f9644a.isSupportMarketFace()), " isSupportMarketParams", Boolean.valueOf(this.mActivity.f9644a.isSupportMarketParams()), " isSupportAppGallery:", Boolean.valueOf(cwi.c(this.mActivity.b, 44)));
        if (this.mActivity.f9644a.isSupportMarketFace() || this.mActivity.f9644a.isSupportMarketParams() || cwi.c(this.mActivity.b, 44)) {
            aj();
        } else if (!cvt.c(this.mActivity.f)) {
            OpAnalyticsUtil.getInstance().setProbabilityProblemEvent("initAppMarketCard failed", String.format("device name=%s, HiLinkId=%s, Model=%s Version=%s", this.mActivity.b.getDeviceName(), this.mActivity.b.getHiLinkDeviceId(), this.mActivity.b.getDeviceModel(), this.mActivity.b.getSoftVersion()));
        }
        ReleaseLogUtil.e("R_WearHomeFeatureCard", "DEVICE_SETTING_NOTAIFICATION isMessageAlert:", Boolean.valueOf(this.mActivity.f9644a.isMessageAlert()));
        if (this.mActivity.f9644a.isMessageAlert()) {
            if (TextUtils.isEmpty(this.s)) {
                this.s = this.mActivity.getString(R.string._2130841535_res_0x7f020fbf);
            }
            d(102, this.mActivity.getString(R.string._2130842216_res_0x7f021268), R.drawable._2131430176_res_0x7f0b0b20, this.mActivity.i, this.s);
            bf();
        }
        bd();
        if (pep.a(this.mActivity.b)) {
            b(112, this.mActivity.getString(R.string._2130846774_res_0x7f022436), R.mipmap._2131821404_res_0x7f11035c, this.mActivity.i);
        }
        w();
    }

    private void p() {
        boolean c;
        if (Utils.o()) {
            c = cwi.c(this.mActivity.b, 113);
        } else {
            c = cwi.c(this.mActivity.b, 168);
        }
        ReleaseLogUtil.e("R_WearHomeFeatureCard", "addArrhythmiaItem isSupportArrhythmia = ", Boolean.valueOf(c));
        if (c) {
            boolean c2 = drl.c("com.huawei.health_deviceFeature_config", "txt", "com.huawei.health.h5.ppg");
            boolean av = av();
            ReleaseLogUtil.e("R_WearHomeFeatureCard", "addArrhythmiaItem isFeatureSupport = ", Boolean.valueOf(c2), ", isSupportPpg:", Boolean.valueOf(av));
            if (c2 || av) {
                b(120, this.mActivity.getString(R.string._2130839621_res_0x7f020845), R.mipmap._2131820812_res_0x7f11010c, this.mActivity.i);
            }
        }
    }

    private boolean av() {
        dqo a2 = drl.a("com.huawei.health_deviceFeature_config", "txt", "com.huawei.health.h5.ppg");
        if (a2 == null || a2.a() == null) {
            ReleaseLogUtil.c("R_WearHomeFeatureCard", "isSupportPpg featureConfig or getExtInfo() is null");
            return false;
        }
        Map a3 = a2.a();
        if (a3 == null) {
            ReleaseLogUtil.c("R_WearHomeFeatureCard", "isSupportPpg extInfo is null");
            return false;
        }
        Object obj = a3.get("support_ppg");
        LogUtil.a("WearHomeFeatureCard", "isSupportPpg supportPpg:", obj);
        return (obj instanceof Double) && ((Double) obj).doubleValue() > 0.0d;
    }

    private void ad() {
        ReleaseLogUtil.e("R_WearHomeFeatureCard", "dealEarphoneHealth isSupportEarphone:", Boolean.valueOf(cwi.c(this.mActivity.b, 112)));
        if (cwi.c(this.mActivity.b, 112)) {
            LogUtil.a("WearHomeFeatureCard", "show the Headset");
            b(103, this.mActivity.getString(R.string.IDS_wear_home_device_earphone), R.mipmap._2131820885_res_0x7f110155, this.mActivity.i);
        }
    }

    private void aj() {
        if (cwi.c(this.mActivity.b, 44)) {
            b(108, this.mActivity.getString(R.string.IDS_device_fragment_application_market), R.drawable._2131430420_res_0x7f0b0c14, this.mActivity.i);
        } else {
            jiw.a().c(this.mActivity.f9644a, new IBaseResponseCallback() { // from class: pce
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    pbu.this.c(i, obj);
                }
            });
        }
    }

    /* synthetic */ void c(int i, Object obj) {
        LogUtil.a("WearHomeFeatureCard", "showAppMarket errorCode:", Integer.valueOf(i));
        if (i == 1) {
            r();
        }
    }

    private void bf() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: pcp
            @Override // java.lang.Runnable
            public final void run() {
                pbu.this.j();
            }
        });
    }

    /* synthetic */ void j() {
        final boolean c = jjb.b().c();
        final int a2 = cvz.a();
        HandlerExecutor.e(new Runnable() { // from class: pcu
            @Override // java.lang.Runnable
            public final void run() {
                pbu.this.d(a2, c);
            }
        });
    }

    /* synthetic */ void d(int i, boolean z) {
        this.s = this.mActivity.getString(R.string._2130841535_res_0x7f020fbf);
        if (this.mActivity.q != null) {
            String c = oak.b().c();
            if (this.mActivity.f != 32 ? !(!this.mActivity.q.b() || !this.mActivity.q.e()) : this.mActivity.q.a(c)) {
                this.s = this.mActivity.getString(R.string._2130841536_res_0x7f020fc0);
            }
        }
        int i2 = i == 1 ? z ? R.drawable.ic_device_massage : R.drawable.ic_device_massage_close : z ? R.drawable._2131430176_res_0x7f0b0b20 : R.drawable._2131430177_res_0x7f0b0b21;
        Iterator<pbo> it = this.k.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            pbo next = it.next();
            if (next != null && next.a() == 102) {
                LogUtil.h("WearHomeFeatureCard", "refreshNotificationPushRedDot isShowRedDot: ", Boolean.valueOf(nwy.b()));
                next.d(nwy.b());
                LogUtil.a("WearHomeFeatureCard", "update notification icon and status");
                next.d(this.s);
                next.d(i2);
                break;
            }
        }
        aw();
    }

    private void r() {
        this.mActivity.runOnUiThread(new Runnable() { // from class: pbw
            @Override // java.lang.Runnable
            public final void run() {
                pbu.this.be();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void be() {
        LogUtil.a("WearHomeFeatureCard", "updateAppMarketShow");
        if (!this.j) {
            LogUtil.a("WearHomeFeatureCard", "updateAppMarketShow registerLocalBroadcast");
            pep.dmS_(this.b, "com.huawei.appmarket.action.REFRESH_DEVICE_APP_UPDATE");
            this.j = true;
        }
        jiw.a().i();
        b(108, this.mActivity.getString(R.string.IDS_device_fragment_application_market), R.drawable._2131430420_res_0x7f0b0c14, this.mActivity.i);
        au();
        jiw.a().b();
        aw();
    }

    private void au() {
        pbo a2 = a(108);
        if (a2 == null) {
            LogUtil.h("WearHomeFeatureCard", "setAppMarketRedDotByCache id is error");
        } else {
            a2.d(jiw.a().d(this.mActivity.b.getDeviceIdentify()));
        }
    }

    private pbo a(int i) {
        if (CollectionUtils.d(this.k)) {
            return null;
        }
        return c(i, 0, this.k.size() - 1);
    }

    private pbo c(int i, int i2, int i3) {
        if (i2 > i3) {
            return null;
        }
        int i4 = (i2 + i3) / 2;
        pbo pboVar = this.k.get(i4);
        if (pboVar.a() == i) {
            return pboVar;
        }
        if (pboVar.a() > i) {
            return c(i, i2, i4 - 1);
        }
        return c(i, i4 + 1, i3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final boolean z) {
        if (this.mActivity == null) {
            LogUtil.h("WearHomeFeatureCard", "refreshAppMarketRedDot mActivity is null");
            return;
        }
        if (this.mActivity.b != null) {
            jiw.a().a(this.mActivity.b.getDeviceIdentify(), z);
        }
        this.mActivity.runOnUiThread(new Runnable() { // from class: pck
            @Override // java.lang.Runnable
            public final void run() {
                pbu.this.b(z);
            }
        });
    }

    /* synthetic */ void b(boolean z) {
        Iterator<pbo> it = this.k.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            pbo next = it.next();
            if (next != null && next.a() == 108) {
                LogUtil.h("WearHomeFeatureCard", "refreshAppMarketRedDot, get apps card and set red dot");
                next.d(z);
                break;
            }
        }
        aw();
    }

    private void w() {
        t();
        ay();
        ReleaseLogUtil.e("R_WearHomeFeatureCard", "DEVICE_SETTING_ALARM isEventAlarm:", Boolean.valueOf(this.mActivity.f9644a.isEventAlarm()), " isSmartAlarm", Boolean.valueOf(this.mActivity.f9644a.isSmartAlarm()));
        if (this.mActivity.f9644a.isEventAlarm() || this.mActivity.f9644a.isSmartAlarm()) {
            b(114, this.mActivity.getString(R.string._2130841509_res_0x7f020fa5), R.mipmap._2131821112_res_0x7f110238, this.mActivity.i);
        }
        ReleaseLogUtil.e("R_WearHomeFeatureCard", "DEVICE_SETTING_WEATHER isWeatherPush:", Boolean.valueOf(this.mActivity.f9644a.isWeatherPush()));
        if (this.mActivity.f9644a.isWeatherPush()) {
            b(115, this.mActivity.getString(R.string._2130841556_res_0x7f020fd4), R.mipmap._2131821117_res_0x7f11023d, this.mActivity.i);
        }
        x();
        ReleaseLogUtil.e("R_WearHomeFeatureCard", "DEVICE_SETTING_CONTACTS isContacts:", Boolean.valueOf(this.mActivity.f9644a.isContacts()));
        if (this.mActivity.f9644a.isContacts()) {
            b(124, this.mActivity.getString(R.string._2130841402_res_0x7f020f3a), R.drawable._2131430427_res_0x7f0b0c1b, this.mActivity.i);
        }
        ab();
        ReleaseLogUtil.e("R_WearHomeFeatureCard", "SMS_QUICK_REPLY isSupportEditSms:", Boolean.valueOf(pep.c(this.mActivity.b)));
        if (pep.c(this.mActivity.b)) {
            b(125, this.mActivity.getString(R.string._2130845286_res_0x7f021e66), R.mipmap._2131821115_res_0x7f11023b, this.mActivity.i);
        }
        if (ao()) {
            b(127, this.mActivity.getString(R.string._2130844224_res_0x7f021a40), R.drawable._2131430424_res_0x7f0b0c18, this.mActivity.i);
        }
        if (lcu.e()) {
            b(107, aa(), R.drawable._2131430512_res_0x7f0b0c70, true);
        }
        if (this.mActivity.d() != null) {
            this.mActivity.d().d(new WechatDeviceProviderHelper.CheckResultListener() { // from class: pcv
                @Override // com.huawei.ui.homewear21.home.card.WechatDeviceProviderHelper.CheckResultListener
                public final void onResult(boolean z) {
                    pbu.this.c(z);
                }
            });
        }
        bc();
    }

    /* synthetic */ void c(boolean z) {
        ReleaseLogUtil.e("R_WearHomeFeatureCard", "SUPPORT_WECHAT_DEVICE_BIND isSupportWechatDevice:", Boolean.valueOf(z));
        if (z) {
            b(128, this.mActivity.getString(R.string.IDS_device_wechat_bind_card), R.mipmap._2131821204_res_0x7f110294, true);
            this.p.e().post(new Runnable() { // from class: pco
                @Override // java.lang.Runnable
                public final void run() {
                    pbu.this.a();
                }
            });
        }
    }

    /* synthetic */ void a() {
        this.r.notifyDataSetChanged();
    }

    private String aa() {
        cvc pluginInfoByDeviceType;
        String string = this.mActivity.getString(R.string.IDS_hw_disconnected_device);
        DeviceInfo deviceInfo = this.mActivity.b;
        if (deviceInfo == null) {
            LogUtil.h("WearHomeFeatureCard", "getDeviceProtectString, deviceinfo is null");
            return string;
        }
        int o = jfu.c(deviceInfo.getProductType()).o();
        if (o == 0 && (pluginInfoByDeviceType = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByDeviceType(deviceInfo.getProductType())) != null && pluginInfoByDeviceType.f() != null) {
            o = pluginInfoByDeviceType.f().au();
        }
        LogUtil.a("WearHomeFeatureCard", "hiType: ", Integer.valueOf(o));
        ProductMapParseUtil.b(BaseApplication.getContext());
        List<ProductMapInfo> b = ProductMap.b("deviceId", String.valueOf(o));
        if (CollectionUtils.d(b)) {
            return string;
        }
        ProductMapInfo productMapInfo = b.get(0);
        if (productMapInfo == null) {
            LogUtil.h("WearHomeFeatureCard", "getDeviceProtectString, productMapInfo is null");
            return string;
        }
        String e = productMapInfo.e();
        LogUtil.a("WearHomeFeatureCard", "deviceType: ", e);
        if (TextUtils.isEmpty(e)) {
            return string;
        }
        if ("06D".equals(e)) {
            return this.mActivity.getString(R.string._2130846712_res_0x7f0223f8);
        }
        return "06E".equals(e) ? this.mActivity.getString(R.string._2130846713_res_0x7f0223f9) : string;
    }

    private void az() {
        boolean c = cwi.c(this.mActivity.b, 75);
        boolean z = c && drl.c("com.huawei.health_deviceFeature_config", "txt", "com.huawei.health.h5.vascular-health");
        ReleaseLogUtil.e("R_WearHomeFeatureCard", "setVascularItem isSupportCapability:", Boolean.valueOf(c), " isSupportVascular:", Boolean.valueOf(z));
        if (z) {
            b(122, this.mActivity.getString(R.string._2130845800_res_0x7f022068), R.mipmap._2131821137_res_0x7f110251, this.mActivity.i);
        }
    }

    private void x() {
        ReleaseLogUtil.e("R_WearHomeFeatureCard", "addSimCardItem isSupportEsim:", Boolean.valueOf(this.mActivity.f9644a.isSupportEsim()));
        if (nsn.ae(BaseApplication.getContext()) || !this.mActivity.f9644a.isSupportEsim()) {
            return;
        }
        ReleaseLogUtil.e("R_WearHomeFeatureCard", "addSimCardItem isSupportNewEsim:", Boolean.valueOf(this.mActivity.f9644a.isSupportNewEsim()));
        if (this.mActivity.f9644a.isSupportNewEsim()) {
            b(106, this.mActivity.getString(R.string._2130843870_res_0x7f0218de), R.mipmap._2131820899_res_0x7f110163, true);
        } else {
            b(106, this.mActivity.getString(R.string._2130841906_res_0x7f021132), R.drawable._2131430433_res_0x7f0b0c21, true);
        }
    }

    private void y() {
        LogUtil.a("WearHomeFeatureCard", "addOverseaWalletItem enter");
        if (LoginInit.getInstance(BaseApplication.getContext()).isMinorAccount()) {
            LogUtil.a("WearHomeFeatureCard", "addOverseaWalletItem minor account");
            return;
        }
        final DeviceInfo deviceInfo = this.mActivity.b;
        if (deviceInfo == null) {
            LogUtil.a("WearHomeFeatureCard", "addOverseaWalletItem device info is null");
            return;
        }
        final boolean isSupportWalletApp = WalletAppManager.getInstance().getIsSupportWalletApp(deviceInfo.getDeviceIdentify());
        ReleaseLogUtil.e("WearHomeFeatureCard", "addOverseaWalletItem show wallet: ", Boolean.valueOf(isSupportWalletApp));
        if (isSupportWalletApp) {
            b(109, this.mActivity.getString(R.string.IDS_device_wallet), R.mipmap._2131821266_res_0x7f1102d2, this.mActivity.i);
        }
        WalletAppManager.getInstance().getValidWalletApplication(deviceInfo.getDeviceIdentify(), new ResponseCallback() { // from class: pcd
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                pbu.this.b(isSupportWalletApp, deviceInfo, i, (List) obj);
            }
        });
    }

    /* synthetic */ void b(boolean z, DeviceInfo deviceInfo, int i, List list) {
        boolean z2 = !CollectionUtils.d(list);
        ReleaseLogUtil.e("WearHomeFeatureCard", "addOverseaWalletItem reload wallet icon:", Boolean.valueOf(z2));
        if (z2 == z) {
            return;
        }
        ReleaseLogUtil.e("WearHomeFeatureCard", "addOverseaWalletItem reload wallet icon");
        WalletAppManager.getInstance().setIsSupportWalletApp(deviceInfo.getDeviceIdentify(), z2);
        if (z2) {
            this.mActivity.runOnUiThread(new Runnable() { // from class: pbv
                @Override // java.lang.Runnable
                public final void run() {
                    pbu.this.b();
                }
            });
        } else if (i(109)) {
            this.p.e().post(new Runnable() { // from class: pcf
                @Override // java.lang.Runnable
                public final void run() {
                    pbu.this.f();
                }
            });
        }
    }

    /* synthetic */ void b() {
        b(109, this.mActivity.getString(R.string.IDS_device_wallet), R.mipmap._2131821266_res_0x7f1102d2, this.mActivity.i);
        a(a(109));
    }

    /* synthetic */ void f() {
        this.r.notifyDataSetChanged();
    }

    private void ab() {
        if (Utils.o()) {
            y();
            LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1014);
            m();
        } else {
            if (ocp.c(this.mActivity.g, this.mActivity.f9644a)) {
                b(109, this.mActivity.getString(R.string.IDS_device_wallet), R.mipmap._2131821116_res_0x7f11023c, this.mActivity.i);
                LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1014);
                m();
                return;
            }
            LogUtil.h("WearHomeFeatureCard", "not support language.");
        }
    }

    public void m() {
        ReleaseLogUtil.e("R_WearHomeFeatureCard", "querySupportSatcom enter, mWalletSatcomCardInfo:", this.l);
        ock ockVar = this.l;
        if (ockVar != null && ockVar.d()) {
            b(105, this.mActivity.getString(R.string.IDS_device_wallet_satcom_card), R.mipmap._2131821114_res_0x7f11023a, this.mActivity.i);
            Iterator<pbo> it = this.k.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                pbo next = it.next();
                if (next != null && next.a() == 105) {
                    next.b(this.l.b() == 1);
                    next.d(aq());
                }
            }
            ReleaseLogUtil.e("R_WearHomeFeatureCard", "querySupportSatcom isSupportSatCom is true");
            return;
        }
        if (this.l == null) {
            this.l = new ock();
        }
        ocp.d(this.mActivity.b, new SatcomQueryCallBack() { // from class: pbu.4
            @Override // com.huawei.wear.oversea.overseamanger.SatcomQueryCallBack
            public int onSuccess(SatcomCardSupportInfo satcomCardSupportInfo) {
                int e = satcomCardSupportInfo.e();
                boolean c = satcomCardSupportInfo.c();
                int a2 = satcomCardSupportInfo.a();
                ReleaseLogUtil.e("R_WearHomeFeatureCard", "querySupportSatcom onSuccess returnCode:", Integer.valueOf(e), ", isSupportSatcomCard:", Boolean.valueOf(c), ", activityPeriod:", Integer.valueOf(a2));
                if (c && a2 != 0) {
                    pbu.this.l.d(true);
                    pbu.this.l.c(a2);
                    pbu pbuVar = pbu.this;
                    pbuVar.d(pbuVar.l);
                }
                return e;
            }

            @Override // com.huawei.wear.oversea.overseamanger.SatcomQueryCallBack
            public int onFail(SatcomCardSupportInfo satcomCardSupportInfo) {
                int e = satcomCardSupportInfo.e();
                ReleaseLogUtil.d("R_WearHomeFeatureCard", "querySupportSatcom fail returnCode is:", Integer.valueOf(e));
                return e;
            }
        });
    }

    private boolean aq() {
        return !SharedPreferenceManager.a(String.valueOf(10008), "click_satcom_card", false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final ock ockVar) {
        this.mActivity.runOnUiThread(new Runnable() { // from class: pbx
            @Override // java.lang.Runnable
            public final void run() {
                pbu.this.c(ockVar);
            }
        });
    }

    /* synthetic */ void c(ock ockVar) {
        pbo pboVar = new pbo();
        pboVar.a(105);
        pboVar.b(this.mActivity.getString(R.string.IDS_device_wallet_satcom_card));
        pboVar.d(R.mipmap._2131821114_res_0x7f11023a);
        pboVar.c(this.mActivity.i);
        pboVar.d("");
        pboVar.b(ockVar.b() == 1);
        pboVar.d(aq());
        a(pboVar);
    }

    private void a(pbo pboVar) {
        pbo a2 = a(pboVar.a());
        if (a2 == null) {
            e(pboVar);
        } else {
            a2.b(pboVar.e());
            a2.b(pboVar.f());
            a2.d(pboVar.c());
            a2.d(pboVar.b());
            a2.d(pboVar.i());
            a2.e(pboVar.d());
            a2.c(pboVar.j());
        }
        this.p.e().post(new Runnable() { // from class: pcb
            @Override // java.lang.Runnable
            public final void run() {
                pbu.this.i();
            }
        });
    }

    /* synthetic */ void i() {
        this.r.notifyDataSetChanged();
    }

    private void t() {
        boolean c = cwi.c(this.mActivity.b, 79);
        ReleaseLogUtil.e("R_WearHomeFeatureCard", "addFindDeviceItem isSupportFindDevice:", Boolean.valueOf(c));
        if (c) {
            b(113, this.mActivity.getString(R.string.IDS_bolt_find_device), R.drawable.ic_setup_list_find_device, this.mActivity.i);
        }
    }

    private void ay() {
        boolean c = cwi.c(this.mActivity.b, 98);
        ReleaseLogUtil.e("R_WearHomeFeatureCard", "setRimSizeItem isSupportSetWheelSize:", Boolean.valueOf(c));
        if (c) {
            d(126, this.mActivity.getString(R.string._2130845460_res_0x7f021f14), R.mipmap._2131821113_res_0x7f110239, this.mActivity.i, jnr.b().c(this.mActivity.b));
        }
    }

    private boolean ao() {
        boolean z = false;
        if (Utils.o()) {
            LogUtil.a("WearHomeFeatureCard", "isIntelligentHomeShow version is oversea or googlePlay");
            return false;
        }
        boolean ae = ae();
        boolean isSupportIntelligentHomeLinkage = this.mActivity.f9644a.isSupportIntelligentHomeLinkage();
        ReleaseLogUtil.e("R_WearHomeFeatureCard", "intelligentHomeSwitch:", Boolean.valueOf(ae), "; isSupportIntelligentHomeLinkage:", Boolean.valueOf(isSupportIntelligentHomeLinkage));
        if (CommonUtil.bv() && isSupportIntelligentHomeLinkage && ae) {
            return true;
        }
        if (isSupportIntelligentHomeLinkage) {
            if (ae) {
                z = true;
            } else {
                String b = SharedPreferenceManager.b(this.mContext, String.valueOf(10008), "intelligent_home_switch");
                LogUtil.a("WearHomeFeatureCard", "devicePairSwitchFlag:", b);
                if (b != null && !TextUtils.isEmpty(b)) {
                    z = Boolean.parseBoolean(b);
                }
            }
        }
        LogUtil.a("WearHomeFeatureCard", "isIntelligentHomeVisible:", Boolean.valueOf(z));
        return z;
    }

    private boolean ae() {
        DeviceInfo deviceInfo = this.mActivity.b;
        if (deviceInfo == null || deviceInfo.getProductType() <= 71) {
            LogUtil.a("WearHomeFeatureCard", "getIntelligentHomeSwitchFromCloud not new product");
            return true;
        }
        String hiLinkDeviceId = deviceInfo.getHiLinkDeviceId();
        if (hiLinkDeviceId == null || hiLinkDeviceId.isEmpty()) {
            LogUtil.h("WearHomeFeatureCard", "getIntelligentHomeSwitchFromCloud HiLinkDeviceId is null");
            return false;
        }
        cvc pluginInfoByUuid = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(jfu.j(deviceInfo.getProductType()));
        if (pluginInfoByUuid == null || pluginInfoByUuid.f() == null) {
            LogUtil.h("WearHomeFeatureCard", "getIntelligentHomeSwitchFromCloud pluginInfo is null");
            return true;
        }
        cvj f = pluginInfoByUuid.f();
        if (f != null) {
            try {
                String string = new JSONObject(f.b()).getString("ai_tips_product_" + hiLinkDeviceId);
                LogUtil.a("WearHomeFeatureCard", "hiLinkDeviceInfo is:", string);
                JSONObject jSONObject = new JSONObject(string);
                if (jSONObject.has("intelligent_home_switch")) {
                    return jSONObject.optBoolean("intelligent_home_switch");
                }
                return true;
            } catch (JSONException unused) {
                LogUtil.b("WearHomeFeatureCard", "getIntelligentHomeSwitchFromCloud JSONException");
                return true;
            }
        }
        LogUtil.h("WearHomeFeatureCard", "pluginInfoForWear is null");
        return true;
    }

    private void bc() {
        boolean isSupportHelp = this.mActivity.f9644a.isSupportHelp();
        boolean z = isSupportHelp && jfu.c(this.mActivity.b.getProductType()).z();
        ReleaseLogUtil.e("R_WearHomeFeatureCard", "DEVICE_SETTING_HELP isSupportHelp:", Boolean.valueOf(isSupportHelp), " isSupportHelpUx:", Boolean.valueOf(z));
        if (z) {
            b(129, this.mActivity.getString(R.string._2130841564_res_0x7f020fdc), R.drawable._2131430423_res_0x7f0b0c17, true);
            this.f.a();
        }
        boolean b = jhb.b(this.mActivity.g);
        ReleaseLogUtil.e("R_WearHomeFeatureCard", "SUPPORT_STUDENT_INFO isSupportFamilyModeUserInfo:", Boolean.valueOf(b), " PairMode:", Integer.valueOf(jpo.c(this.mActivity.g)));
        if (b && jhb.d(this.mActivity.g)) {
            b(56, this.mActivity.getString(R.string._2130841533_res_0x7f020fbd), R.mipmap._2131821126_res_0x7f110246, this.mActivity.i);
        }
    }

    private void v() {
        q();
        p();
        az();
        u();
        ReleaseLogUtil.e("R_WearHomeFeatureCard", "isSupportInnovationResearch: ", Boolean.valueOf(ap()));
        if (Utils.o() || !ap()) {
            return;
        }
        LogUtil.h("WearHomeFeatureCard", "show the innovation research card");
        b(123, this.mActivity.getString(R.string._2130847783_res_0x7f022827), R.mipmap._2131821065_res_0x7f110209, this.mActivity.i);
        ah();
    }

    private boolean ap() {
        if (!cwi.c(this.mActivity.b, 100) && cwi.c(this.mActivity.b, 59)) {
            return true;
        }
        DeviceInfo a2 = jpt.a("WearHomeFeatureCard");
        if ((this.mActivity.f9644a.isSupportAtrialOperator() && !cvz.c(a2)) || this.mActivity.f9644a.isSupportEcgAuth() || cwi.c(this.mActivity.b, 116) || cwi.c(this.mActivity.b, 95)) {
            return true;
        }
        return this.mActivity.f9644a.isSupportSleepBreathe();
    }

    private void u() {
        boolean e = jlj.a().e(this.mActivity.b);
        ReleaseLogUtil.e("R_WearHomeFeatureCard", "show the Sleep Breath Pause isSupportSleepBreathe: ", Boolean.valueOf(e));
        if (e) {
            b(121, this.mActivity.getString(jlj.a().d() ? R.string._2130847164_res_0x7f0225bc : R.string._2130846650_res_0x7f0223ba), R.mipmap._2131821121_res_0x7f110241, this.mActivity.i);
        }
    }

    private void q() {
        boolean z = false;
        boolean isSupportEcgAuth = this.mActivity.f9644a != null ? this.mActivity.f9644a.isSupportEcgAuth() : false;
        boolean z2 = cwi.c(this.mActivity.b, 226) && cwi.c(this.mActivity.b, 106) && pxz.h();
        if (cwi.c(this.mActivity.b, 106) && drl.c("com.huawei.health_deviceFeature_config", "txt", "com.huawei.health.h5.ecgce")) {
            z = true;
        }
        ReleaseLogUtil.e("R_WearHomeFeatureCard", "addEcgItem isSupportEcgAuth:", Boolean.valueOf(isSupportEcgAuth), ", isSupportEcgAnalysis:", Boolean.valueOf(z), ", isSupportExtInfoSupportEcg:", Boolean.valueOf(z2));
        if (z || z2) {
            b(118, this.mActivity.getString(R.string.IDS_quick_app_ecg_analysis), R.mipmap._2131820886_res_0x7f110156, this.mActivity.i);
        } else {
            if (!isSupportEcgAuth || Utils.o()) {
                return;
            }
            b(49, this.mActivity.getString(R.string.IDS_quick_app_ecg), R.mipmap._2131821111_res_0x7f110237, this.mActivity.i);
        }
    }

    private void ba() {
        b(43, this.mActivity.getString(R.string._2130842596_res_0x7f0213e4), R.drawable._2131430426_res_0x7f0b0c1a, this.mActivity.i);
    }

    private boolean as() {
        if ((this.mActivity.f9644a.isSupportCoreSleep() && jad.d(58)) || this.mActivity.f9644a.isActivityReminder()) {
            return true;
        }
        if (this.mActivity.f9644a.isSupportContinueHeartRate()) {
            LogUtil.a("WearHomeFeatureCard", "prepare add press continue heart rate item");
            return true;
        }
        if (this.mActivity.f9644a.isSupportHeartRateEnable() && !this.mActivity.f9644a.isSupportContinueHeartRate()) {
            LogUtil.a("WearHomeFeatureCard", "prepare add press cycle heart rate item");
            return true;
        }
        if (this.mActivity.f9644a.isSupportPressAutoMonitor()) {
            LogUtil.a("WearHomeFeatureCard", "prepare add press auto monitor item");
            return true;
        }
        if (jlj.a().e(this.mActivity.b)) {
            return true;
        }
        return cwi.c(this.mActivity.b, 29);
    }

    private boolean i(int i) {
        LogUtil.a("WearHomeFeatureCard", "removeGeneralSettingItem:", Integer.valueOf(i));
        synchronized (d) {
            Iterator<pbo> it = this.k.iterator();
            while (it.hasNext()) {
                if (it.next().a() == i) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }
    }

    private void b(int i, String str, int i2, boolean z) {
        d(i, str, i2, z, "");
    }

    private void d(int i, String str, int i2, boolean z, String str2) {
        pbo pboVar = new pbo();
        pboVar.a(i);
        pboVar.b(str);
        pboVar.d(i2);
        pboVar.c(z);
        pboVar.d(str2);
        e(pboVar);
    }

    private void e(pbo pboVar) {
        LogUtil.a("WearHomeFeatureCard", "addGeneralSettingItem :", Integer.valueOf(pboVar.a()), ",item:", pboVar.e());
        if (a(pboVar.a()) != null) {
            return;
        }
        synchronized (d) {
            this.k.add(pboVar);
            this.k.sort(Comparator.comparingInt(new ToIntFunction() { // from class: pbt
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    return ((pbo) obj).a();
                }
            }));
        }
    }

    private void ah() {
        if (!TextUtils.isEmpty(f16055a)) {
            LogUtil.a("WearHomeFeatureCard", "Heart Vascular study AppMarket UrlHost is not empty.");
        } else {
            this.mActivity.x.execute(new Runnable() { // from class: pbz
                @Override // java.lang.Runnable
                public final void run() {
                    pbu.c();
                }
            });
        }
    }

    static /* synthetic */ void c() {
        String url = GRSManager.a(BaseApplication.getContext()).getUrl("domainAppgalleryCloudHuawei");
        f16055a = url;
        LogUtil.c("WearHomeFeatureCard", "sHeartVascularStudyAppMarketUrlHost:", url);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j, reason: merged with bridge method [inline-methods] */
    public void b(int i) {
        if (!nsn.o() && i < this.k.size()) {
            pbo pboVar = this.k.get(i);
            int a2 = pboVar.a();
            if (a2 == 101) {
                oau.a("my_face");
                this.f.o();
                return;
            }
            if (a2 == 102) {
                al();
                return;
            }
            if (a2 == 112) {
                oau.a("live_notifications");
                this.f.w();
                return;
            }
            if (a2 == 127) {
                oau.a("smart_life");
                this.mActivity.a(IntelligentHomeLinkageActivity.class, 2);
                nsn.ai(this.mContext);
                return;
            }
            if (a2 == 129) {
                this.f.j();
                return;
            }
            switch (a2) {
                case 106:
                    this.f.m();
                    break;
                case 107:
                    this.f.e();
                    break;
                case 108:
                    this.f.k();
                    break;
                case 109:
                    this.f.y();
                    SharedPreferenceManager.c("privacy_center", "bank_account", String.valueOf(System.currentTimeMillis()));
                    break;
                case 110:
                    this.f.l();
                    break;
                default:
                    switch (a2) {
                        case 114:
                            this.f.r();
                            break;
                        case 115:
                            oau.a(HwExerciseConstants.JSON_NAME_WORKOUT_WEATHER);
                            this.f.z();
                            break;
                        case 116:
                            oau.a("health_monitoring");
                            this.f.u();
                            break;
                        default:
                            h(pboVar.a());
                            break;
                    }
            }
        }
    }

    private void al() {
        if (pep.d(this.mContext, this.mActivity.f)) {
            bb();
        } else {
            this.f.d();
        }
    }

    private void bb() {
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.mContext).e(this.mContext.getResources().getString(R.string._2130847550_res_0x7f02273e)).czC_(R.string._2130847540_res_0x7f022734, new View.OnClickListener() { // from class: pcc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                pbu.this.dlB_(view);
            }
        }).czz_(R.string._2130841424_res_0x7f020f50, new View.OnClickListener() { // from class: pcg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                pbu.this.dlC_(view);
            }
        }).e();
        e.setCancelable(false);
        e.show();
    }

    /* synthetic */ void dlB_(View view) {
        this.mActivity.t = true;
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(this.mActivity.g);
        if (this.mActivity.c("showMissSensitiveNotificationDialog")) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        boolean z = this.mActivity.b.getDeviceBluetoothType() == 1;
        this.mActivity.c(z);
        ReleaseLogUtil.e("R_WearHomeFeatureCard", "unbindAndReconnectDevice;isDeleteDevice is ", Boolean.valueOf(z));
        if (z) {
            SharedPreferenceManager.e(Integer.toString(1000), "wearable_unpair_reconnection", true);
            ReleaseLogUtil.e("R_WearHomeFeatureCard", "set WEARABLE_UNPAIR_RECONNECTION true");
        }
        oxa.a().c(arrayList, z);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void dlC_(View view) {
        this.f.d();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void h(int i) {
        if (i == 43) {
            oau.a("heart_index");
            this.f.n();
            return;
        }
        if (i == 49) {
            oau.a("ecg");
            this.f.h();
            return;
        }
        if (i == 53) {
            this.f.a(this.n);
            return;
        }
        if (i == 56) {
            this.f.x();
            return;
        }
        if (i == 104) {
            oau.a("guardian_service");
            this.f.c(this.g);
            return;
        }
        if (i == 117) {
            this.f.s();
            return;
        }
        if (i != 128) {
            switch (i) {
                case 124:
                    this.f.q();
                    break;
                case 125:
                    oau.a("quick_reply");
                    this.f.t();
                    break;
                case 126:
                    oau.a("wheel_size");
                    this.f.e(this.mActivity.b);
                    break;
                default:
                    f(i);
                    break;
            }
            return;
        }
        LogUtil.a("WearHomeFeatureCard", "ID_SUPPORT_WECHAT_DEVICE_BIND gotoWechat");
        this.mActivity.d().d(this.mActivity);
        oau.a("wechat_devices");
    }

    /* synthetic */ void e(boolean z) {
        this.o = z;
    }

    private void f(int i) {
        if (i == 103) {
            oau.a("earbuds");
            this.f.b(this.mActivity.b, 103, false);
        }
        if (i == 105) {
            oau.a("beidou_satellite_messaging");
            this.f.v();
            return;
        }
        if (i == 111) {
            oau.a("map");
            this.f.d(this.mActivity.b, new OnCreateResultListener() { // from class: pcl
                @Override // com.huawei.maps.offlinedata.health.init.OnCreateResultListener
                public final void onCreateResult(boolean z) {
                    pbu.this.e(z);
                }
            });
            return;
        }
        if (i == 113) {
            oau.a("find_device");
            if (cwi.c(this.mActivity.b, 112)) {
                this.f.b(this.mActivity.b);
                return;
            } else {
                this.f.d(this.mActivity.b);
                return;
            }
        }
        if (i == 118) {
            oau.a("electrocardiogram");
            this.f.f();
            return;
        }
        switch (i) {
            case 120:
                oau.a("pulse_wave_arrhythmia_analysis");
                this.f.p();
                break;
            case 121:
                oau.a("sleep_apnea");
                if (this.mActivity != null) {
                    LogUtil.a("WearHomeFeatureCard", "setOnItemClick sleep breath pause");
                    hyk.b().startOsaH5(this.mActivity, "Device_0001");
                    break;
                }
                break;
            case 122:
                oau.a("arterial_stiffness_detection");
                this.f.g();
                break;
            case 123:
                oau.a("research");
                this.f.i();
                break;
            default:
                LogUtil.h("WearHomeFeatureCard", "setOnItemClick default");
                break;
        }
    }

    public void n() {
        if (this.mActivity == null) {
            LogUtil.h("WearHomeFeatureCard", "updateWheelSize mActivity is null");
            return;
        }
        if (this.mActivity.b == null) {
            LogUtil.h("WearHomeFeatureCard", "updateWheelSize mCurrentDeviceInfo is null");
            return;
        }
        for (pbo pboVar : this.k) {
            if (pboVar.a() == 126) {
                LogUtil.a("WearHomeFeatureCard", "update bolt wheel size");
                pboVar.d(jnr.b().c(this.mActivity.b));
                l(this.mActivity.b.getDeviceConnectState());
                return;
            }
        }
    }

    private void s() {
        List<String> fieldList = this.mActivity.b.getFieldList();
        if (fieldList == null || fieldList.isEmpty()) {
            LogUtil.a("WearHomeFeatureCard", "addExclusiveGuardianItem not support exclusive guardian");
            return;
        }
        bk();
        if (CommonUtil.aa(this.mActivity)) {
            ac();
        }
    }

    private void bk() {
        String c = pep.c(this.mActivity, this.mActivity.b.getDeviceIdentify(), "exclusiveGuardStatus");
        this.h = c;
        ReleaseLogUtil.e("R_WearHomeFeatureCard", "updateStatusFromLocal mExclusiveGuardianLocalStatus = ", c);
        String str = this.h;
        this.g = str;
        if ("1".equals(str)) {
            e(104, this.mActivity.getString(R.string._2130845570_res_0x7f021f82), R.mipmap._2131820903_res_0x7f110167, this.mActivity.i, this.mActivity.getString(R.string._2130845571_res_0x7f021f83));
            return;
        }
        if ("2".equals(this.g)) {
            e(104, this.mActivity.getString(R.string._2130845570_res_0x7f021f82), R.mipmap._2131820903_res_0x7f110167, this.mActivity.i, this.mActivity.getString(R.string._2130845572_res_0x7f021f84));
            return;
        }
        if ("3".equals(this.g)) {
            String c2 = pep.c(this.mActivity, this.mActivity.b.getDeviceIdentify(), "exclusiveGuardDoctorImgUrl");
            this.c = c2;
            LogUtil.a("WearHomeFeatureCard", "updateStatusFromLocal mDoctorImgLocalUrl = ", c2);
            String str2 = this.c;
            if (str2 == null) {
                str2 = "";
            }
            this.c = str2;
            this.e = str2;
            e(104, this.mActivity.getString(R.string._2130845570_res_0x7f021f82), R.mipmap._2131820902_res_0x7f110166, this.mActivity.i, "");
            return;
        }
        LogUtil.h("WearHomeFeatureCard", "updateStatusFromLocal else branch");
    }

    private void ac() {
        LogUtil.a("WearHomeFeatureCard", "getDeviceBenefits");
        PayApi payApi = (PayApi) Services.a("TradeService", PayApi.class);
        if (payApi == null) {
            LogUtil.h("WearHomeFeatureCard", "getDeviceBenefits payApi is null");
        } else {
            payApi.getDeviceBenefits(njn.e(this.mActivity.b, DeviceBenefitQueryParam.DeviceBenefitType.DEVICE_BENEFIT_TYPE_INBOX, true), new IBaseResponseCallback() { // from class: pci
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    pbu.this.b(i, obj);
                }
            });
        }
    }

    /* synthetic */ void b(int i, Object obj) {
        LogUtil.a("WearHomeFeatureCard", "getDeviceBenefits errorCode = ", Integer.valueOf(i), ", objectData = ", obj);
        if (i != 0 || !(obj instanceof DeviceBenefits)) {
            LogUtil.h("WearHomeFeatureCard", "getDeviceBenefits fail");
        } else {
            LogUtil.c("WearHomeFeatureCard", "getDeviceBenefits objectData = ", HiJsonUtil.e(obj));
            b((DeviceBenefits) obj);
        }
    }

    private void b(DeviceBenefits deviceBenefits) {
        List<DeviceInboxInfo> inboxInfos = deviceBenefits.getInboxInfos();
        if (inboxInfos == null || inboxInfos.isEmpty()) {
            LogUtil.h("WearHomeFeatureCard", "handleDeviceBenefitsResult inboxInfoList is null or empty");
            return;
        }
        boolean z = false;
        for (DeviceInboxInfo deviceInboxInfo : inboxInfos) {
            if (pep.e(deviceInboxInfo)) {
                if (deviceInboxInfo.getActiveStatus() == 1) {
                    bm();
                    return;
                } else if (deviceInboxInfo.getActiveStatus() == 2) {
                    z = true;
                } else {
                    LogUtil.h("WearHomeFeatureCard", "handleDeviceBenefitsResult else branch");
                }
            }
        }
        ReleaseLogUtil.e("R_WearHomeFeatureCard", "handleDeviceBenefitsResult isActiveStatus = ", Boolean.valueOf(z));
        if (z) {
            at();
        }
    }

    private void at() {
        LogUtil.a("WearHomeFeatureCard", "queryBenefitInfo");
        PayApi payApi = (PayApi) Services.a("TradeService", PayApi.class);
        if (payApi == null) {
            LogUtil.h("WearHomeFeatureCard", "queryBenefitInfo payApi is null");
        } else {
            payApi.queryBenefitInfo(9, "", new IBaseResponseCallback() { // from class: pby
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    pbu.this.d(i, obj);
                }
            });
        }
    }

    /* synthetic */ void d(int i, Object obj) {
        LogUtil.a("WearHomeFeatureCard", "queryBenefitInfo errorCode = ", Integer.valueOf(i), ", objectData = ", obj);
        if (i != 0 || !(obj instanceof RepeatResourceBenefitInfo)) {
            LogUtil.h("WearHomeFeatureCard", "queryBenefitInfo fail");
            return;
        }
        LogUtil.a("WearHomeFeatureCard", "queryBenefitInfo objectData = ", HiJsonUtil.e(obj));
        RepeatResourceBenefitInfo repeatResourceBenefitInfo = (RepeatResourceBenefitInfo) obj;
        long nowTime = repeatResourceBenefitInfo.getNowTime() != 0 ? repeatResourceBenefitInfo.getNowTime() : System.currentTimeMillis();
        LogUtil.a("WearHomeFeatureCard", "queryBenefitInfo currentTime = ", Long.valueOf(nowTime));
        if (repeatResourceBenefitInfo.getExpireTime().longValue() >= nowTime) {
            ag();
        } else {
            bl();
        }
    }

    private void ag() {
        LogUtil.a("WearHomeFeatureCard", "getDoctorBasicInfo");
        pug.a().getDoctorBasicInfo(new DataCallback() { // from class: pbu.5
            @Override // com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback.DataCallback
            public void onFailure(int i, String str) {
                LogUtil.h("WearHomeFeatureCard", "getDoctorBasicInfo errorCode = ", i + ", errorInfo = ", str);
            }

            @Override // com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                LogUtil.a("WearHomeFeatureCard", "getDoctorBasicInfo data = " + jSONObject.toString());
                try {
                    if (jSONObject.getInt("resultCode") == 0) {
                        if (TextUtils.isEmpty(jSONObject.optString("doctorId"))) {
                            pbu.this.bl();
                            LogUtil.a("WearHomeFeatureCard", "getDoctorBasicInfo doctorId is empty");
                            return;
                        }
                        String optString = jSONObject.optString("doctorHeadIcon");
                        pbu.this.e = "";
                        if (!TextUtils.isEmpty(optString)) {
                            pbu.this.e = optString;
                        }
                        pbu.this.bh();
                    }
                } catch (JSONException unused) {
                    LogUtil.b("WearHomeFeatureCard", "getDoctorBasicInfo JSONException");
                }
            }
        });
    }

    private void bm() {
        this.g = "1";
        this.e = "";
        if ("1".equals(this.h)) {
            LogUtil.a("WearHomeFeatureCard", "updatePendingStatus status not change, return");
            return;
        }
        LogUtil.a("WearHomeFeatureCard", "updatePendingStatus");
        pep.a(this.mActivity, this.mActivity.b.getDeviceIdentify(), "exclusiveGuardDoctorImgUrl", "");
        pep.a(this.mActivity, this.mActivity.b.getDeviceIdentify(), "exclusiveGuardStatus", this.g);
        this.mActivity.runOnUiThread(new Runnable() { // from class: pca
            @Override // java.lang.Runnable
            public final void run() {
                pbu.this.h();
            }
        });
    }

    /* synthetic */ void h() {
        e(104, this.mActivity.getString(R.string._2130845570_res_0x7f021f82), R.mipmap._2131820903_res_0x7f110167, this.mActivity.i, this.mActivity.getString(R.string._2130845571_res_0x7f021f83));
        bj();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bl() {
        this.g = "2";
        this.e = "";
        if ("2".equals(this.h)) {
            LogUtil.a("WearHomeFeatureCard", "updateToBeSignedStatus status not change, return");
            return;
        }
        LogUtil.a("WearHomeFeatureCard", "updateToBeSignedStatus");
        pep.a(this.mActivity, this.mActivity.b.getDeviceIdentify(), "exclusiveGuardDoctorImgUrl", "");
        pep.a(this.mActivity, this.mActivity.b.getDeviceIdentify(), "exclusiveGuardStatus", this.g);
        this.mActivity.runOnUiThread(new Runnable() { // from class: pcj
            @Override // java.lang.Runnable
            public final void run() {
                pbu.this.o();
            }
        });
    }

    /* synthetic */ void o() {
        e(104, this.mActivity.getString(R.string._2130845570_res_0x7f021f82), R.mipmap._2131820903_res_0x7f110167, this.mActivity.i, this.mActivity.getString(R.string._2130845572_res_0x7f021f84));
        bj();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bh() {
        this.g = "3";
        if ("3".equals(this.h) && this.e.equals(this.c)) {
            LogUtil.a("WearHomeFeatureCard", "updateGuardedStatus status not change, return");
            return;
        }
        LogUtil.a("WearHomeFeatureCard", "updateGuardedStatus");
        pep.a(this.mActivity, this.mActivity.b.getDeviceIdentify(), "exclusiveGuardDoctorImgUrl", this.e);
        pep.a(this.mActivity, this.mActivity.b.getDeviceIdentify(), "exclusiveGuardStatus", this.g);
        this.mActivity.runOnUiThread(new Runnable() { // from class: pch
            @Override // java.lang.Runnable
            public final void run() {
                pbu.this.g();
            }
        });
    }

    /* synthetic */ void g() {
        e(104, this.mActivity.getString(R.string._2130845570_res_0x7f021f82), R.mipmap._2131820902_res_0x7f110166, this.mActivity.i, "");
        bj();
    }

    private void e(int i, String str, int i2, boolean z, String str2) {
        pbo pboVar = new pbo();
        pboVar.a(i);
        pboVar.b(str);
        pboVar.d(i2);
        pboVar.c(z);
        pboVar.d(str2);
        pboVar.d("1".equals(this.g));
        pboVar.e(this.e);
        a(pboVar);
    }

    public void e(DeviceInfo deviceInfo, int i, boolean z) {
        LogUtil.a("WearHomeFeatureCard", "showEarphonePairGuide in. ");
        this.f.b(deviceInfo, i, z);
    }

    public boolean d() {
        return this.f.b();
    }
}
