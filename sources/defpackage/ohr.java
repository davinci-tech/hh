package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.os.Process;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.bundle.AppBundle;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.haf.common.utils.NetworkUtil;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData;
import com.huawei.health.manager.util.TimeUtil;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.motion.HealthOpenSDK;
import com.huawei.hihealth.motion.IExecuteResult;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.homehealth.dialogcarddata.DialogCardViewHolder;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.me.util.StepCounterSupportUtil;
import com.huawei.ui.main.stories.nps.activity.EcologyNpsSurveyActivity;
import com.huawei.ui.main.stories.nps.activity.QuestionMainActivity;
import com.huawei.ui.main.stories.nps.harid.HagridNpsManager;
import com.huawei.ui.main.stories.nps.interactors.HwNpsManager;
import com.huawei.ui.main.stories.nps.interactors.mode.QuestionSurveyDetailResponse;
import com.huawei.ui.main.stories.nps.npsstate.NpsUserShowController;
import com.huawei.utils.FoundationCommonUtil;
import defpackage.ohr;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.DaemonServiceSpUtils;
import health.compact.a.EnvironmentInfo;
import health.compact.a.GRSManager;
import health.compact.a.KeyValDbManager;
import health.compact.a.LanguageUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes6.dex */
public class ohr extends AbstractBaseCardData {

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f15672a = {"samsung", "vivo", "realme", "oppo", "meizu", "lenovo", "honor", "oneplus", "xiaomi"};
    protected Handler c;
    private String g;
    private DialogCardViewHolder i;
    private Context j;
    private boolean k;
    private CustomViewDialog n;
    private sbk p;
    private h r;
    private int t;
    protected List<pwb> b = new ArrayList(10);
    protected int e = StepCounterSupportUtil.a(BaseApplication.getContext());
    private HealthOpenSDK f = null;
    private String s = null;
    private final List<Integer> q = new ArrayList();
    private String m = "";
    private a d = new a(this);
    private boolean o = false;
    private volatile boolean h = true;
    private BroadcastReceiver l = new BroadcastReceiver() { // from class: ohr.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.b("SCUI_DialogCardData", "BroadcastReceiver intent == null");
                return;
            }
            String action = intent.getAction();
            if (action == null) {
                return;
            }
            LogUtil.a("SCUI_DialogCardData", "mNewTipReceiver onReceive ", action);
            if ("com.huawei.health.action.NPL_SERVICE_NOT_AVALIABLE".equals(action)) {
                ohr.this.d(4);
            } else if ("com.huawei.health.action.NO_CALL_REJECT_PERMISION".equals(action)) {
                ohr.this.ah();
            } else if ("com.huawei.bone.action.NEW_ECG_DATA_RECEIVED".equals(action)) {
                ohr.this.d(10);
            }
        }
    };

    public ohr(Context context, boolean z) {
        this.j = context == null ? BaseApplication.getContext() : context;
        this.k = z;
        this.c = new e(Looper.getMainLooper(), this);
        al();
        ObserverManagerUtil.d(this.d, "closeStepsTips");
        ObserverManagerUtil.d(new Observer() { // from class: ohr.11
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                if (objArr == null || objArr.length <= 0) {
                    return;
                }
                if (ohr.this.b.size() > 1) {
                    if (ohr.this.q.contains(6)) {
                        return;
                    }
                    LogUtil.a("SCUI_DialogCardData", "TIPS_OTHER_DEVICES_SYNC_STEPS add");
                    ohr.this.d(6);
                    return;
                }
                ReleaseLogUtil.d("SCUI_DialogCardData", "OriginListData is null or size less than two");
            }
        }, "TIPS_OTHER_DEVICES_SYNC_STEPS");
        l();
    }

    private void l() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: ohr.22
            @Override // java.lang.Runnable
            public void run() {
                if (ohr.this.p == null) {
                    ohr ohrVar = ohr.this;
                    ohrVar.p = sbk.a(ohrVar.j);
                }
                LogUtil.a("SCUI_DialogCardData", "start query bind");
                ohr.this.h = !"false".equals(r0.p.i(ohr.this.j));
                LogUtil.a("SCUI_DialogCardData", "end to query bind");
            }
        });
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public RecyclerView.ViewHolder getCardViewHolder(ViewGroup viewGroup, LayoutInflater layoutInflater) {
        e();
        if (layoutInflater == null) {
            LogUtil.b("SCUI_DialogCardData", "getCardViewHolder layoutInflater == null");
            layoutInflater = LayoutInflater.from(BaseApplication.getContext());
        }
        DialogCardViewHolder dialogCardViewHolder = new DialogCardViewHolder(layoutInflater.inflate(R.layout.layout_step_card_container, viewGroup, false), this.j, false);
        this.i = dialogCardViewHolder;
        daB_(dialogCardViewHolder.daH_());
        i();
        return this.i;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void onResume() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onResume();
        l();
        if (!this.k) {
            b();
        }
        n();
        o();
        ReleaseLogUtil.e("SCUI_DialogCardData", "main card onResume finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (t() == null || !t().ad()) {
            return;
        }
        daB_(this.i.daH_());
        i();
        onResume();
    }

    public void h() {
        LogUtil.a("SCUI_DialogCardData", "====onPause====");
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void onDestroy() {
        super.onDestroy();
        bd();
        BroadcastManagerUtil.bFJ_(this.j, this.r);
        ObserverManagerUtil.e(this.d, "closeStepsTips");
        ObserverManagerUtil.e("TIPS_OTHER_DEVICES_SYNC_STEPS");
        Handler handler = this.c;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    public void e(boolean z) {
        this.k = z;
    }

    public void b() {
        LogUtil.a("SCUI_DialogCardData", "executeAfterGuide");
        an();
        af();
        boolean isLogined = LoginInit.getInstance(BaseApplication.getContext()).getIsLogined();
        a(isLogined);
        b(isLogined);
        ag();
        as();
    }

    private void b(boolean z) {
        if (z) {
            return;
        }
        if (t() == null || t().daK_() == null) {
            LogUtil.h("SCUI_DialogCardData", "setSyncCloudDataFailLayoutVisibility viewHolder or mSyncCloudDataFailLayout = null");
        } else {
            this.q.remove((Object) 100);
        }
    }

    protected void i() {
        StepCounterSupportUtil.c(new StepCounterSupportUtil.StepCounterClassCallback() { // from class: ohr.23
            @Override // com.huawei.ui.main.stories.me.util.StepCounterSupportUtil.StepCounterClassCallback
            public void getDeviceClass(int i) {
                ohr.this.e = i;
                if (ohr.this.e == 3) {
                    ohr.this.d(1);
                }
                ohr.this.refreshCardData();
            }
        });
        m();
    }

    private void o() {
        Context context;
        if (System.currentTimeMillis() - SharedPreferenceManager.b(Integer.toString(10000), "KEY_HAS_STEP_NOTIFICATION", 0L) > Constants.ANALYSIS_EVENT_KEEP_TIME && (context = this.j) != null && this.f != null && LanguageUtil.j(context) && v() && !CommonUtil.bh()) {
            this.f.d((IExecuteResult) new b(this));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ay() {
        View inflate = View.inflate(this.j, R.layout.step_message_switch_dialog, null);
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.j);
        builder.a(this.j.getString(R.string._2130847857_res_0x7f022871)).czg_(inflate).cze_(R.string.IDS_device_bt_right_btn_info, new View.OnClickListener() { // from class: ohr.24
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ohr.this.f.d(true, (IExecuteResult) null);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.czc_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: ohr.21
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("SCUI_DialogCardData", " checkMessageSwitchDialog setNegativeButton onClick.");
                SharedPreferenceManager.e(Integer.toString(10000), "KEY_HAS_STEP_NOTIFICATION", System.currentTimeMillis());
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomViewDialog e2 = builder.e();
        this.n = e2;
        e2.setCancelable(false);
        if (this.o) {
            return;
        }
        this.n.show();
        this.o = true;
    }

    private boolean v() {
        return Build.VERSION.SDK_INT >= 28 && this.e != 3;
    }

    private void al() {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("sync_cloud_data_action");
            h hVar = new h(this);
            this.r = hVar;
            BroadcastManagerUtil.bFz_(this.j, hVar, intentFilter);
        } catch (Exception unused) {
            LogUtil.b("SCUI_DialogCardData", "registerSyncCloudDataReceiver Exception");
        }
    }

    private void bd() {
        try {
            LogUtil.c("SCUI_DialogCardData", "Enter unRegisterNewTipBroadcast register");
            Context context = this.j;
            if (context != null) {
                context.unregisterReceiver(this.l);
            }
        } catch (IllegalArgumentException e2) {
            LogUtil.b("SCUI_DialogCardData", LogAnonymous.b((Throwable) e2));
        } catch (RuntimeException e3) {
            LogUtil.b("SCUI_DialogCardData", LogAnonymous.b((Throwable) e3));
        }
    }

    private void ag() {
        jdx.b(new Runnable() { // from class: ohr.26
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("SCUI_DialogCardData", "enter queryPluginTips");
                ohr.this.g = lsp.d().e();
                if (!TextUtils.isEmpty(ohr.this.g)) {
                    if (ohr.this.q.contains(9)) {
                        ohr.this.c.sendEmptyMessage(35);
                        return;
                    } else {
                        ohr.this.c.sendEmptyMessage(36);
                        return;
                    }
                }
                LogUtil.h("SCUI_DialogCardData", "queryPluginTips mDownloadPluginName is empty");
            }
        });
    }

    private void a(boolean z) {
        LogUtil.a("SCUI_DialogCardData", "enter queryIsShowBindTips");
        if (!z || Utils.o() || LoginInit.getInstance(BaseApplication.getContext()).isKidAccount()) {
            LogUtil.h("SCUI_DialogCardData", "queryIsShowBindTips not login or isOverSea");
            i(8);
        } else if (q()) {
            LogUtil.a("SCUI_DialogCardData", "queryIsShowBindTips has show");
            i(8);
        } else if (this.h) {
            LogUtil.a("SCUI_DialogCardData", "isBinded or isOevesea");
            this.c.sendEmptyMessage(31);
        } else {
            this.c.sendEmptyMessage(30);
        }
    }

    private boolean q() {
        Pair<Long, Integer> day_ = day_();
        long longValue = ((Long) day_.first).longValue();
        int intValue = ((Integer) day_.second).intValue();
        LogUtil.a("SCUI_DialogCardData", "hasShownTips showTimes ", Integer.valueOf(intValue), " lastShowTime ", Long.valueOf(longValue));
        if (longValue == 0 && intValue == 0) {
            return false;
        }
        return intValue >= 4 || System.currentTimeMillis() - longValue <= 604800000;
    }

    private Pair<Long, Integer> day_() {
        String b2 = SharedPreferenceManager.b(this.j, Integer.toString(10000), "BIND_WECHAT_TIME_AND_INTERVAL");
        if (TextUtils.isEmpty(b2)) {
            return new Pair<>(0L, 0);
        }
        String[] split = b2.split("#");
        if (split.length == 2) {
            return new Pair<>(Long.valueOf(CommonUtil.n(this.j, split[0])), Integer.valueOf(CommonUtil.m(this.j, split[1])));
        }
        return new Pair<>(0L, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(int i) {
        if (t() == null || t().daN_() == null) {
            LogUtil.h("SCUI_DialogCardData", "setWeChatLayoutVisibility mStepsCardViewHolder = null");
            return;
        }
        t().daN_().setVisibility(i);
        if (i == 8) {
            this.q.remove((Object) 7);
        }
    }

    private void af() {
        LogUtil.a("SCUI_DialogCardData", "enter queryIsHiAiTips");
        if (u()) {
            if (this.q.contains(8)) {
                LogUtil.a("SCUI_DialogCardData", "send show HI AI message");
                this.c.sendEmptyMessage(34);
                return;
            } else {
                this.c.sendEmptyMessage(32);
                return;
            }
        }
        this.c.sendEmptyMessage(33);
    }

    private boolean u() {
        String e2 = KeyValDbManager.b(BaseApplication.getContext()).e("downloadHiAiPlugin");
        LogUtil.a("SCUI_DialogCardData", "HI_AI_PLUGIN is:", e2);
        return (!AppBundle.c().isBundleModule("PluginHiAiEngine") || AppBundle.e().getInstalledModules().contains("PluginHiAiEngine") || TextUtils.isEmpty(e2)) ? false : true;
    }

    private void an() {
        if (z()) {
            LogUtil.a("SCUI_DialogCardData", "showHealthNps");
            String string = this.j.getResources().getString(R.string._2130847320_res_0x7f022658);
            this.m = string;
            this.t = 3;
            a(true, string, 3);
            return;
        }
        if (ac()) {
            LogUtil.a("SCUI_DialogCardData", "ShowDeviceNps");
            if (HagridNpsManager.getInstance().isWeightDeviceNps()) {
                this.m = this.j.getResources().getString(R.string._2130838115_res_0x7f020263);
            } else {
                this.m = this.j.getResources().getString(R.string._2130837995_res_0x7f0201eb);
            }
            this.t = 4;
            a(true, this.m, 4);
            return;
        }
        if (ab()) {
            String sharedPreference = HwNpsManager.getInstance().getSharedPreference(HwNpsManager.KEY_ECOLOGY_DEVICE_UUID);
            dcz d2 = ResourceManager.e().d(sharedPreference);
            if (d2 == null) {
                LogUtil.b("SCUI_DialogCardData", "ecology nps device is null");
                return;
            }
            String format = String.format(Locale.ENGLISH, this.j.getResources().getString(R$string.IDS_hw_ecology_nps_survey), dcx.d(sharedPreference, d2.n().b()));
            this.m = format;
            this.t = 5;
            a(true, format, 5);
            return;
        }
        a(false, "", 0);
    }

    private boolean ac() {
        boolean isShowToDo = this.j != null ? HwNpsManager.getInstance().isShowToDo() : false;
        LogUtil.a("SCUI_DialogCardData", "isShowDeviceNps:", Boolean.valueOf(isShowToDo));
        return isShowToDo;
    }

    private void h(int i) {
        HwNpsManager hwNpsManager;
        QuestionSurveyDetailResponse detailResponse;
        LogUtil.a("SCUI_DialogCardData", "setNpsBiAnalytics:NPS_SHOW");
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        if (i == 4 && (detailResponse = (hwNpsManager = HwNpsManager.getInstance()).getDetailResponse()) != null) {
            hashMap.put("questionTime", Integer.valueOf(detailResponse.getQueryTimes()));
            hashMap.put("questionType", hwNpsManager.getQuestionType(detailResponse.getSurveyID()));
        }
        LogUtil.a("SCUI_DialogCardData", "setNpsBiAnalytics map:", hashMap);
        ixx.d().d(this.j, AnalyticsValue.HEALTH_HOME_NPS_SHOW_2010094.value(), hashMap, 0);
    }

    private boolean ab() {
        if (this.j != null) {
            return HwNpsManager.getInstance().isShowEcologyNps();
        }
        return false;
    }

    private boolean z() {
        boolean isShowNps = NpsUserShowController.getInstance(this.j).isShowNps();
        LogUtil.a("NpsUserShowController", "todoCardData isShowNps: ", Boolean.valueOf(isShowNps));
        return isShowNps;
    }

    protected void m() {
        ba();
        bb();
        ax();
        bc();
        ap();
    }

    private void ap() {
        boolean i = DaemonServiceSpUtils.i();
        ReleaseLogUtil.e("SCUI_DialogCardData", "showAbnormalStepDialog ", Boolean.valueOf(i));
        if (i && DaemonServiceSpUtils.g() && ntf.b().a()) {
            ReleaseLogUtil.e("SCUI_DialogCardData", "showAbnormalStepDialog enter");
            d(103);
        }
    }

    private void as() {
        if (Build.VERSION.SDK_INT <= 28) {
            return;
        }
        boolean z = false;
        boolean z2 = ContextCompat.checkSelfPermission(this.j, "android.permission.ACTIVITY_RECOGNITION") == 0;
        LogUtil.a("SCUI_DialogCardData", "showActivityRecognitionGuideTips isGranted ", Boolean.valueOf(z2), " mDeviceOriginalType ", Integer.valueOf(this.e));
        if (z2 || this.e == 3) {
            y();
            return;
        }
        if (this.q.contains(102)) {
            LogUtil.a("SCUI_DialogCardData", "showActivityRecognitionGuideTips has add TIPS_ACTIVITY_RECOGNITION_NOT_GRANTED");
            return;
        }
        if (this.q.contains(7)) {
            this.q.remove((Object) 7);
            z = true;
        }
        d(102);
        if (z) {
            d(7);
        }
    }

    private void y() {
        if (koq.c(this.q) && this.q.contains(102)) {
            LogUtil.a("SCUI_DialogCardData", "mTipsList.get(0) ", this.q.get(0));
            if (this.q.get(0).intValue() == 102) {
                c();
            } else {
                this.q.remove((Object) 102);
            }
        }
    }

    private void bc() {
        LogUtil.c("SCUI_DialogCardData", "showNoCallPermissionTips enter");
        String b2 = SharedPreferenceManager.b(this.j, Integer.toString(10000), "no_call_permision_tips");
        LogUtil.a("SCUI_DialogCardData", "noCallPermisionRecord = ", b2);
        if ("0".equals(b2)) {
            d(5);
        }
    }

    private void ba() {
        LogUtil.a("SCUI_DialogCardData", "showProtectionTips");
        String valueOf = String.valueOf(10000);
        int a2 = SharedPreferenceManager.a(valueOf, "sp_key_briefs_thread_pid", -1);
        if (a2 == -1) {
            LogUtil.h("SCUI_DialogCardData", "pId is null");
            return;
        }
        String b2 = SharedPreferenceManager.b(this.j, valueOf, "sp_key_briefs_is_resting");
        try {
            if (TextUtils.isEmpty(b2) || !Boolean.parseBoolean(b2) || ad() || a2 == Process.myPid()) {
                return;
            }
            d(101);
            LogUtil.a("SCUI_DialogCardData", "showProtectionTips addNewTips");
        } catch (NumberFormatException e2) {
            LogUtil.b("SCUI_DialogCardData", "showProtectionTips exception : ", e2.getMessage());
        }
    }

    private boolean ad() {
        Object systemService = BaseApplication.getContext().getSystemService("power");
        if (systemService instanceof PowerManager) {
            return ((PowerManager) systemService).isIgnoringBatteryOptimizations(BaseApplication.getContext().getPackageName());
        }
        return false;
    }

    private void ax() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: ohp
            @Override // java.lang.Runnable
            public final void run() {
                ohr.this.f();
            }
        });
    }

    /* synthetic */ void f() {
        LogUtil.a("SCUI_DialogCardData", "showDaemonKilled");
        HealthOpenSDK healthOpenSDK = this.f;
        if (healthOpenSDK == null) {
            ReleaseLogUtil.d("SCUI_DialogCardData", "showDaemonKilled opensdk null,return");
        } else {
            healthOpenSDK.i(new c(this));
        }
    }

    private void bb() {
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), "if_has_tiped_do_not_support_step");
        ReleaseLogUtil.e("SCUI_DialogCardData", "mHealthOpenSdk.getDeviceOriginalClass()==", Integer.valueOf(this.e));
        if (this.j == null || "alread_tips".equals(b2) || this.e != 3 || jct.d(this.j)) {
            return;
        }
        d(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ah() {
        LogUtil.c("SCUI_DialogCardData", "processNoCallPermission enter");
        String b2 = SharedPreferenceManager.b(this.j, Integer.toString(10000), "no_call_permision_tips");
        LogUtil.a("SCUI_DialogCardData", "noCallPermisionRecord = ", b2);
        if ("1".equals(b2)) {
            return;
        }
        SharedPreferenceManager.e(this.j, Integer.toString(10000), "no_call_permision_tips", Integer.toString(0), new StorageParams());
        d(5);
    }

    private void a(boolean z, String str, final int i) {
        if (t() == null || t().daI_() == null) {
            LogUtil.h("SCUI_DialogCardData", "mStepsCardViewHolder == null");
            return;
        }
        if (z && !TextUtils.isEmpty(str) && i != 0) {
            if (b(i)) {
                LogUtil.h("SCUI_DialogCardData", "not show nps");
                t().daI_().setVisibility(8);
                return;
            }
            t().daI_().setVisibility(0);
            t().x().setText(str);
            t().m().setOnClickListener(new View.OnClickListener() { // from class: ohr.28
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ohr.this.o(i);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            t().l().setOnClickListener(new View.OnClickListener() { // from class: ohr.30
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ohr.this.t().daI_().setVisibility(8);
                    Intent intent = new Intent();
                    intent.setAction("com.huawei.health.nps_detail_sync_hide");
                    intent.setPackage(ohr.this.j.getPackageName());
                    ohr.this.j.sendBroadcast(intent, LocalBroadcast.c);
                    ohr.this.f(i);
                    ohr.this.p();
                    ohr.this.g(i);
                    HwNpsManager.getInstance().cancelNotify();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            h(i);
            return;
        }
        t().daI_().setVisibility(8);
        p();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(int i) {
        if (i != 4) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        HwNpsManager hwNpsManager = HwNpsManager.getInstance();
        hashMap.put("showDay", Integer.valueOf(hwNpsManager.getShowDay()));
        hashMap.put("ignoreTime", nsj.d());
        QuestionSurveyDetailResponse detailResponse = hwNpsManager.getDetailResponse();
        if (detailResponse != null) {
            hashMap.put("questionTime", Integer.valueOf(detailResponse.getQueryTimes()));
            hashMap.put("questionType", hwNpsManager.getQuestionType(detailResponse.getSurveyID()));
        }
        LogUtil.a("SCUI_DialogCardData", "setIgnoresBiAnalytics map:", hashMap);
        ixx.d().d(this.j, AnalyticsValue.HEALTH_IGNORE_NPS_ENTER_2010220.value(), hashMap, 0);
    }

    private boolean b(int i) {
        if (i == 4 && !HagridNpsManager.getInstance().isWeightDeviceNps() && !HwNpsManager.getInstance().isTimeOfNps()) {
            ReleaseLogUtil.e("Nps_SCUI_DialogCardData", "isHideNps sendNotify: not support send notify time");
            return true;
        }
        if (koq.b(this.q)) {
            ReleaseLogUtil.e("Nps_SCUI_DialogCardData", "mTipsList is null");
            return false;
        }
        if (this.q.get(0).intValue() == 102) {
            nsy.cMA_(t().daF_(), 8);
            return false;
        }
        if (this.q.get(0).intValue() == 7) {
            nsy.cMA_(t().daN_(), 8);
            return false;
        }
        if (i == 4 && this.q.get(0).intValue() == 10) {
            nsy.cMA_(t().daG_(), 8);
            return false;
        }
        if (i != 4 || this.q.get(0).intValue() != 101) {
            return true;
        }
        nsy.cMA_(t().daF_(), 8);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        synchronized (this.q) {
            if (this.q.size() > 0) {
                e(this.q.get(0).intValue());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o(int i) {
        if (i == 3) {
            NpsUserShowController.getInstance(BaseApplication.getContext()).showNpsPage(this.j);
            return;
        }
        if (i == 4) {
            gnm.aPB_(this.j, new Intent(this.j, (Class<?>) QuestionMainActivity.class));
        } else if (i == 5) {
            gnm.aPB_(this.j, new Intent(this.j, (Class<?>) EcologyNpsSurveyActivity.class));
        } else {
            LogUtil.a("SCUI_DialogCardData", "type is invalid ", Integer.valueOf(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(int i) {
        if (i == 3) {
            NpsUserShowController npsUserShowController = NpsUserShowController.getInstance(BaseApplication.getContext());
            int surveyTime = npsUserShowController.getSurveyTime();
            LogUtil.a("SCUI_DialogCardData", "NpsQuestionPageActivity onCreate() surveyTime: ", Integer.valueOf(surveyTime));
            if (surveyTime == 0) {
                LogUtil.h("SCUI_DialogCardData", "surveyTime = 0");
                return;
            } else {
                npsUserShowController.setTheSurveyUnNeeded(surveyTime);
                return;
            }
        }
        if (i == 4 || i == 5) {
            if (TextUtils.isEmpty(HwNpsManager.getInstance().getSharedPreference(HwNpsManager.KEY_NPS_SHOW))) {
                return;
            }
            LogUtil.a("SCUI_DialogCardData", "save KEY_NPS_SHOW result ", Integer.valueOf(HwNpsManager.getInstance().setSharedPreference(HwNpsManager.KEY_NPS_SHOW, "")));
            HwNpsManager.getInstance().setSharedPreference(HwNpsManager.KEY_NPS_LAST_TIME, String.valueOf(System.currentTimeMillis()), null);
            if (TextUtils.isEmpty(HwNpsManager.getInstance().getSharedPreference(HwNpsManager.MESSAGE_NPS_ID))) {
                return;
            }
            ((MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class)).setMessageRead(HwNpsManager.getInstance().getSharedPreference(HwNpsManager.MESSAGE_NPS_ID));
            return;
        }
        LogUtil.a("SCUI_DialogCardData", "type is not equal");
    }

    public void a() {
        if (t() == null) {
            LogUtil.a("SCUI_DialogCardData", "closeSyncCloudDataFailTips mStepsCardViewHolder == null");
        } else {
            t().daK_().setVisibility(8);
        }
    }

    protected void c() {
        LogUtil.a("SCUI_DialogCardData", "closeCurrentTips");
        if (this.q == null) {
            LogUtil.h("SCUI_DialogCardData", "closeCurrentTips find mTipsList null,warnning!!! logic error??? return");
            return;
        }
        if (t() == null) {
            LogUtil.b("SCUI_DialogCardData", "closeCurrentTips mStepsCardViewHolder == null");
            return;
        }
        synchronized (this.q) {
            if (this.q.size() == 0) {
                t().daK_().setVisibility(8);
                t().daF_().setVisibility(8);
                t().daJ_().setVisibility(8);
                LogUtil.h("SCUI_DialogCardData", "closeCurrentTips find no tips in list,warnning!!! logic error??? return");
                return;
            }
            j(this.q.get(0).intValue());
            this.q.remove(0);
            if (aa()) {
                t().daK_().setVisibility(8);
                t().daF_().setVisibility(8);
                t().daJ_().setVisibility(8);
                an();
                LogUtil.h("SCUI_DialogCardData", "closeCurrentTips,not have any more,set view gone");
                return;
            }
            LogUtil.a("SCUI_DialogCardData", "displayTips:", this.q.get(0));
            e(this.q.get(0).intValue());
        }
    }

    private boolean aa() {
        return this.q.size() <= 0 || this.q.get(0).intValue() == 102 || this.q.get(0).intValue() == 7;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        LogUtil.a("SCUI_DialogCardData", "closeSyncOtherDeviceStepsTipsWhenDayChange mTipsList=", this.q);
        if (koq.b(this.q)) {
            return;
        }
        if (this.q.get(0).intValue() == 6) {
            this.q.remove(0);
            if (this.q.size() > 0) {
                e(this.q.get(0).intValue());
                return;
            } else {
                if (t() != null) {
                    t().daF_().setVisibility(8);
                    t().daJ_().setVisibility(8);
                    return;
                }
                return;
            }
        }
        for (int i = 0; i < this.q.size(); i++) {
            if (this.q.get(i).intValue() == 6) {
                this.q.remove(i);
            }
        }
    }

    private void j(int i) {
        LogUtil.a("SCUI_DialogCardData", "processTipsCloseEvent types=", Integer.valueOf(i));
        if (i == 1) {
            SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10000), "if_has_tiped_do_not_support_step", "alread_tips", new StorageParams());
            return;
        }
        if (i == 2) {
            if (this.f == null) {
                ReleaseLogUtil.d("SCUI_DialogCardData", "processTipsCloseEvent opensdk null,return");
                return;
            } else {
                LogUtil.a("SCUI_DialogCardData", "close keep-alive-tips,makePromptNoSense");
                this.f.e();
                return;
            }
        }
        if (i == 4) {
            LogUtil.a("SCUI_DialogCardData", "TIPS_NPL_SERVICE_NA only close tips");
            return;
        }
        if (i == 5) {
            SharedPreferenceManager.e(this.j, Integer.toString(10000), "no_call_permision_tips", Integer.toString(1), new StorageParams());
            return;
        }
        if (i == 6) {
            SharedPreferenceManager.e(this.j, Integer.toString(10000), "show_data_origin_tips_times", System.currentTimeMillis() + "#1", new StorageParams());
            return;
        }
        LogUtil.a("SCUI_DialogCardData", "getTipsString types=", Integer.valueOf(i), " not support");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void d(final int i) {
        if (!HandlerExecutor.c()) {
            ReleaseLogUtil.e("SCUI_DialogCardData", "addNewTips post to ui thread ", Integer.valueOf(i));
            HandlerExecutor.e(new Runnable() { // from class: ohu
                @Override // java.lang.Runnable
                public final void run() {
                    ohr.this.d(i);
                }
            });
            return;
        }
        ReleaseLogUtil.e("SCUI_DialogCardData", "addNewTips tips = ", Integer.valueOf(i));
        synchronized (this.q) {
            if (i == 10) {
                if (this.q.contains(10)) {
                    return;
                }
            }
            this.q.add(Integer.valueOf(i));
            be();
            ReleaseLogUtil.e("SCUI_DialogCardData", "addNewTips mTipsList = ", this.q.toString());
            if (x()) {
                return;
            }
            av();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void av() {
        if (aa()) {
            an();
        } else {
            e(this.q.get(0).intValue());
            this.c.sendEmptyMessage(26);
        }
    }

    private boolean x() {
        if (!this.q.contains(2) || this.q.indexOf(2) == 0 || CommonUtil.bf()) {
            return false;
        }
        ThreadPoolManager.d().execute(new AnonymousClass1());
        return true;
    }

    /* renamed from: ohr$1, reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            final List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "SCUI_DialogCardData");
            HandlerExecutor.yF_().post(new Runnable() { // from class: ohs
                @Override // java.lang.Runnable
                public final void run() {
                    ohr.AnonymousClass1.this.d(deviceList);
                }
            });
        }

        /* synthetic */ void d(List list) {
            if ((!koq.b(list)) && ohr.this.q.remove((Object) 2)) {
                ohr.this.q.add(0, 2);
            }
            ohr.this.av();
        }
    }

    private void be() {
        if (this.q.remove((Object) 100)) {
            this.q.add(0, 100);
        }
        if (this.q.get(0).intValue() == 102) {
            this.q.remove((Object) 102);
            this.q.add(102);
            if (this.q.contains(7)) {
                this.q.remove((Object) 7);
                this.q.add(7);
            }
        }
    }

    private void e(int i) {
        if (t() == null) {
            LogUtil.h("SCUI_DialogCardData", "displayTips mStepsCardViewHolder == null");
            return;
        }
        final String str = Build.BRAND;
        LogUtil.a("SCUI_DialogCardData", "displayTips phoneBrand:", str);
        if (i == 100) {
            az();
            return;
        }
        if (i == 4 && ("OPPO".equals(str) || "vivo".equals(str))) {
            w();
            t().daJ_().setVisibility(0);
            t().i().setOnClickListener(new View.OnClickListener() { // from class: ohr.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ohr.this.t().daJ_().setVisibility(8);
                    ohr.this.b(str);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            t().n().setOnClickListener(new View.OnClickListener() { // from class: ohr.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ohr.this.c();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            LogUtil.a("SCUI_DialogCardData", "displayTips the phone is oppo or vivo :", str);
            return;
        }
        if (i == 7) {
            bg();
            return;
        }
        if (i == 8) {
            aw();
            return;
        }
        if (i == 9) {
            at();
        } else if (i == 10) {
            au();
        } else {
            b(i, str);
        }
    }

    private void az() {
        w();
        t().daK_().setVisibility(0);
        t().q().setOnClickListener(new View.OnClickListener() { // from class: ohr.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("SCUI_DialogCardData", "showSyncCloudDataFailCard onClick mSyncIgnoreTextView is clicked");
                ohr.this.t().daK_().setVisibility(8);
                owq.a(false);
                ohr.this.c();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        t().u().setOnClickListener(new View.OnClickListener() { // from class: ohr.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("SCUI_DialogCardData", "showSyncCloudDataFailCard onClick mSyncTryAgainTextView is clicked");
                if (NetworkUtil.i()) {
                    ohr.this.ak();
                } else {
                    Toast.makeText(ohr.this.j, R.string._2130839502_res_0x7f0207ce, 1).show();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void b(final int i, String str) {
        boolean a2 = SharedPreferenceManager.a(Integer.toString(10000), "protection_show_value", false);
        if (i == 101 && (a2 || !ae())) {
            LogUtil.a("SCUI_DialogCardData", "showOtherCard TIPS_PROTECTION isTodayShown");
            return;
        }
        String c2 = c(i);
        if (t() == null) {
            LogUtil.h("SCUI_DialogCardData", "showOtherCard mStepsCardViewHolder is null");
            return;
        }
        w();
        t().p().setText(c2);
        t().daF_().setVisibility(0);
        t().f().setVisibility(0);
        t().f().setText(R.string._2130841132_res_0x7f020e2c);
        t().g().setVisibility(8);
        t().daL_().setVisibility(8);
        t().k().setVisibility(8);
        if (i == 101 || i == 2) {
            l(i);
        }
        if (i == 103) {
            k();
        }
        if (i == 6) {
            t().p().setOnClickListener(new View.OnClickListener() { // from class: ohr.6
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (ohr.this.b.size() > 0) {
                        ObserverManagerUtil.c("showFitnessDataOriginDialog", new Object[0]);
                        ohr.this.c();
                        ohr.this.c.removeMessages(21);
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            if (!this.c.hasMessages(21)) {
                this.c.sendEmptyMessageDelayed(21, 300000L);
            }
        }
        if (i == 102) {
            aq();
        }
        t().f().setOnClickListener(new View.OnClickListener() { // from class: ohq
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ohr.this.daA_(i, view);
            }
        });
        LogUtil.a("SCUI_DialogCardData", "showOtherCard the phone is not oppo or vivo :", str);
    }

    /* synthetic */ void daA_(int i, View view) {
        if (nsn.o()) {
            LogUtil.a("SCUI_DialogCardData", "HwCancelBtn click too fast");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            if (i == 6) {
                this.c.removeMessages(21);
            }
            c();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void aq() {
        oht.b("type", 1, "event", 0, AnalyticsValue.HEALTH_HOME_TIPS_20401106);
        t().f().setVisibility(8);
        t().k().setVisibility(0);
        t().k().setText(R.string._2130846701_res_0x7f0223ed);
        t().daL_().setVisibility(8);
        t().k().setOnClickListener(new View.OnClickListener() { // from class: ohr.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                oht.b("type", 1, "event", 1, AnalyticsValue.HEALTH_HOME_TIPS_20401106);
                jeg.d().bGx_(BaseApplication.getActivity(), new String[]{"android.permission.ACTIVITY_RECOGNITION"}, new j());
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private String c(int i) {
        if (i == 1) {
            if (EnvironmentInfo.k()) {
                return this.j.getResources().getString(R.string._2130846130_res_0x7f0221b2);
            }
            if (nsn.ae(com.huawei.haf.application.BaseApplication.e())) {
                return this.j.getResources().getString(R.string._2130844369_res_0x7f021ad1);
            }
            return this.j.getResources().getString(R.string._2130841917_res_0x7f02113d);
        }
        if (i == 2) {
            return this.j.getResources().getString(R.string._2130846661_res_0x7f0223c5);
        }
        if (i == 4) {
            return this.j.getResources().getString(R.string._2130842369_res_0x7f021301);
        }
        if (i == 5) {
            return this.j.getResources().getString(R.string._2130842370_res_0x7f021302);
        }
        if (i != 6) {
            switch (i) {
                case 101:
                    return this.j.getResources().getString(R.string._2130845565_res_0x7f021f7d);
                case 102:
                    return this.j.getResources().getString(R.string._2130846366_res_0x7f02229e);
                case 103:
                    return this.j.getString(R.string._2130847437_res_0x7f0226cd);
                default:
                    LogUtil.a("SCUI_DialogCardData", "getTipsString types=", Integer.valueOf(i), " not support");
                    break;
            }
        } else if (this.j != null) {
            return ai();
        }
        return "";
    }

    private String ai() {
        if (this.b.size() <= 2) {
            return this.b.size() == 2 ? this.j.getResources().getString(R.string._2130842437_res_0x7f021345, e(this.b.get(0)), e(this.b.get(1))) : "";
        }
        pwb b2 = b(this.b);
        if (b2 != null) {
            return this.j.getResources().getString(R.string._2130842438_res_0x7f021346, e(b2));
        }
        return this.j.getResources().getString(R.string._2130842438_res_0x7f021346, this.j.getResources().getString(R.string.IDS_hw_data_origin_unknow_device));
    }

    private String e(pwb pwbVar) {
        if (this.j == null) {
            return "";
        }
        if (pwbVar.c() == 32) {
            if (pwbVar.a() != null) {
                return pwbVar.a();
            }
            return this.j.getResources().getString(R.string._2130841476_res_0x7f020f84);
        }
        if (pwbVar.d() != null) {
            return pwbVar.d();
        }
        return this.j.getResources().getString(R.string.IDS_hw_data_origin_unknow_device);
    }

    private pwb b(List<pwb> list) {
        pwb pwbVar = null;
        if (this.j == null || list == null || list.size() == 0) {
            return null;
        }
        int i = 0;
        while (true) {
            if (i < list.size()) {
                pwb pwbVar2 = list.get(i);
                if (pwbVar2 != null && pwbVar2.e() != null && !pwbVar2.e().equals(FoundationCommonUtil.getAndroidId(this.j)) && pwbVar2.c() == 32) {
                    pwbVar = pwbVar2;
                    break;
                }
                i++;
            } else {
                break;
            }
        }
        return pwbVar == null ? d(list) : pwbVar;
    }

    private pwb d(List<pwb> list) {
        for (pwb pwbVar : list) {
            if (pwbVar != null && pwbVar.e() != null && !pwbVar.e().equals(FoundationCommonUtil.getAndroidId(this.j))) {
                return pwbVar;
            }
        }
        return null;
    }

    private boolean ae() {
        long b2 = SharedPreferenceManager.b(Integer.toString(10000), "sp_key_briefs_begin_time", 0L);
        return b2 != 0 && TimeUtil.b(b2, System.currentTimeMillis());
    }

    private void l(final int i) {
        final String str;
        SharedPreferenceManager.e(Integer.toString(10000), "protection_show_value", false);
        if (Arrays.asList(f15672a).contains(Build.BRAND.toLowerCase(Locale.ENGLISH))) {
            t().daL_().setVisibility(0);
            t().f().setVisibility(8);
        }
        if (i == 2) {
            t().g().setVisibility(0);
            List<DeviceInfo> c2 = oae.c(BaseApplication.getContext()).c();
            if (!koq.b(c2)) {
                str = c2.get(0).getDeviceName();
                t().o().setOnClickListener(new View.OnClickListener() { // from class: ohr.7
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        new owf().e(ohr.this.j);
                        if (i == 2) {
                            oau.c(100105, str);
                        }
                        ohr.this.c();
                        ViewClickInstrumentation.clickOnView(view);
                    }
                });
                t().d().setOnClickListener(new View.OnClickListener() { // from class: ohr.9
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        SharedPreferenceManager.e(Integer.toString(10000), "protection_show_value", true);
                        if (i == 2) {
                            oau.c(100106, str);
                        }
                        ohr.this.t().daF_().setVisibility(8);
                        ohr.this.c();
                        ViewClickInstrumentation.clickOnView(view);
                    }
                });
            }
        }
        str = "";
        t().o().setOnClickListener(new View.OnClickListener() { // from class: ohr.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                new owf().e(ohr.this.j);
                if (i == 2) {
                    oau.c(100105, str);
                }
                ohr.this.c();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        t().d().setOnClickListener(new View.OnClickListener() { // from class: ohr.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SharedPreferenceManager.e(Integer.toString(10000), "protection_show_value", true);
                if (i == 2) {
                    oau.c(100106, str);
                }
                ohr.this.t().daF_().setVisibility(8);
                ohr.this.c();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void k() {
        ReleaseLogUtil.e("SCUI_DialogCardData", "abnormalStepTips");
        DaemonServiceSpUtils.a(false);
        DaemonServiceSpUtils.h();
        gnj.g();
        t().daL_().setVisibility(0);
        t().f().setVisibility(8);
        t().g().setVisibility(8);
        t().o().setText(R.string._2130847436_res_0x7f0226cc);
        t().o().setOnClickListener(new View.OnClickListener() { // from class: ohr.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ReleaseLogUtil.e("SCUI_DialogCardData", "abnormalStepTips goto step description");
                DaemonServiceSpUtils.b(System.currentTimeMillis());
                pxy.a(ohr.this.j, "/hwtips/topic/health_help_all/%s/SF-10190178_f6174.html?channel=04");
                gnj.c();
                ohr.this.c();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        t().d().setOnClickListener(new View.OnClickListener() { // from class: ohr.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ReleaseLogUtil.e("SCUI_DialogCardData", "abnormalStepTips cancel");
                gnj.e();
                ohr.this.t().daF_().setVisibility(8);
                ohr.this.c();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final String str) {
        jdx.b(new Runnable() { // from class: ohr.13
            @Override // java.lang.Runnable
            public void run() {
                String countryCode = GRSManager.a(ohr.this.j).getCountryCode();
                ohr ohrVar = ohr.this;
                ohrVar.s = GRSManager.a(ohrVar.j).getNoCheckUrl("domainTipsResDbankcdn", countryCode);
                if (ohr.this.s != null) {
                    ohr.this.e(str);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        String str2;
        String str3;
        final HashMap hashMap = new HashMap(1);
        final String str4 = null;
        if ("OPPO".equals(str)) {
            if (this.s.contains("dra")) {
                if (LanguageUtil.m(this.j)) {
                    str3 = this.s + "/handbook/OPPO_note/EMUI9.0/C001B001/zh-CN/index.html";
                } else {
                    str3 = this.s + "/handbook/OPPO_note/EMUI9.0/C001B001/en-US/index.html";
                }
            } else if (this.s.contains("drcn")) {
                if (LanguageUtil.m(this.j)) {
                    str3 = this.s + "/OPPO_note/EMUI9.0/C001B001/zh-CN/index.html";
                } else {
                    str3 = this.s + "/OPPO_note/EMUI9.0/C001B001/en-US/index.html";
                }
            } else {
                LogUtil.h("SCUI_DialogCardData", "mTipsHostUrl is error.");
                hashMap.put("phone_type", "oppo");
            }
            str4 = str3;
            hashMap.put("phone_type", "oppo");
        }
        if ("vivo".equals(str)) {
            if (this.s.contains("dra")) {
                if (LanguageUtil.m(this.j)) {
                    str2 = this.s + "/handbook/vivo_note/EMUI9.0/C001B001/zh-CN/index.html";
                } else {
                    str2 = this.s + "/handbook/vivo_note/EMUI9.0/C001B001/en-US/index.html";
                }
            } else if (this.s.contains("drcn")) {
                if (LanguageUtil.m(this.j)) {
                    str2 = this.s + "/vivo_note/EMUI9.0/C001B001/zh-CN/index.html";
                } else {
                    str2 = this.s + "/vivo_note/EMUI9.0/C001B001/en-US/index.html";
                }
            } else {
                LogUtil.h("SCUI_DialogCardData", "mTipsHostUrl is error.");
                hashMap.put("phone_type", "vivo");
            }
            str4 = str2;
            hashMap.put("phone_type", "vivo");
        }
        HandlerExecutor.a(new Runnable() { // from class: oho
            @Override // java.lang.Runnable
            public final void run() {
                ohr.this.a(hashMap, str4);
            }
        });
    }

    /* synthetic */ void a(Map map, String str) {
        e((Map<String, Object>) map, str);
    }

    private void e(Map<String, Object> map, String str) {
        Intent intent = new Intent(this.j, (Class<?>) WebViewActivity.class);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.NOTIFY_ERROR_ALERT_1090025.value(), map, 0);
        LogUtil.c("SCUI_DialogCardData", "jumpToWebViewActivity url = ", str);
        intent.putExtra("url", str);
        intent.putExtra(com.huawei.operation.utils.Constants.JUMP_MODE_KEY, 0);
        gnm.aPB_(this.j, intent);
    }

    private void bg() {
        LogUtil.a("SCUI_DialogCardData", "enter showWeChatCard");
        if (t() == null || t().daN_() == null || this.j == null) {
            LogUtil.h("SCUI_DialogCardData", "mStepsCardViewHolder == null");
            return;
        }
        t().ac().setOnClickListener(new View.OnClickListener() { // from class: ohr.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("SCUI_DialogCardData", "click link");
                if (ohr.this.p != null) {
                    ohr.this.p.e(ohr.this.j);
                }
                oht.b("type", 2, "event", 1, AnalyticsValue.HEALTH_HOME_TIPS_20401106);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        t().aa().setOnClickListener(new View.OnClickListener() { // from class: ohr.19
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("SCUI_DialogCardData", "click ignore");
                ohr.this.ar();
                ohr.this.i(8);
                oht.b("type", 2, "event", 2, AnalyticsValue.HEALTH_HOME_TIPS_20401106);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        oht.b("type", 2, "event", 0, AnalyticsValue.HEALTH_HOME_TIPS_20401106);
        w();
        i(0);
    }

    private void au() {
        LogUtil.a("SCUI_DialogCardData", "showEcgDataRemind");
        if (t() == null) {
            LogUtil.a("SCUI_DialogCardData", "showEcgDataRemind viewholder is null");
            return;
        }
        nsy.cMn_(t().c(), new View.OnClickListener() { // from class: ohr.20
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                nsd.e(false);
                H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
                builder.addCustomizeArg("from", "6");
                bzs.e().loadH5ProApp(ohr.this.j, "com.huawei.health.h5.ecgce", builder);
                nsy.cMA_(ohr.this.t().daG_(), 8);
                ohr.this.c();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        nsy.cMn_(t().b(), new View.OnClickListener() { // from class: ohr.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                nsy.cMA_(ohr.this.t().daG_(), 8);
                ohr.this.c();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        w();
        nsy.cMA_(t().daG_(), 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ar() {
        int intValue = ((Integer) day_().second).intValue();
        LogUtil.a("SCUI_DialogCardData", "setWeChatIsSHow times ", Integer.valueOf(intValue));
        SharedPreferenceManager.e(this.j, Integer.toString(10000), "BIND_WECHAT_TIME_AND_INTERVAL", System.currentTimeMillis() + "#" + (intValue + 1), (StorageParams) null);
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ak() {
        Intent intent = new Intent();
        intent.setAction("sync_cloud_data_again_action");
        intent.putExtra("sync_cloud_data_again", true);
        BroadcastManagerUtil.bFI_(this.j, intent);
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DialogCardViewHolder t() {
        return this.i;
    }

    protected void daB_(LinearLayout linearLayout) {
        if (linearLayout.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) linearLayout.getLayoutParams();
            Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
            marginLayoutParams.setMarginStart(this.j.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e) + ((Integer) safeRegionWidth.first).intValue());
            marginLayoutParams.setMarginEnd(this.j.getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d) + ((Integer) safeRegionWidth.second).intValue());
            linearLayout.setLayoutParams(marginLayoutParams);
        }
    }

    protected void e() {
        HealthOpenSDK d2 = dss.c(this.j).d();
        this.f = d2;
        if (d2 == null) {
            ReleaseLogUtil.d("SCUI_DialogCardData", "mHealthOpenSdk null");
        } else {
            j();
        }
    }

    protected void j() {
        LogUtil.c("SCUI_DialogCardData", "registerNewTipBroadcast register");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.health.action.NPL_SERVICE_NOT_AVALIABLE");
        intentFilter.addAction("com.huawei.health.action.NO_CALL_REJECT_PERMISION");
        intentFilter.addAction("com.huawei.bone.action.NEW_ECG_DATA_RECEIVED");
        BroadcastManagerUtil.bFC_(this.j, this.l, intentFilter, LocalBroadcast.c, null);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void refreshCardData() {
        n();
    }

    public void d() {
        HandlerExecutor.e(new Runnable() { // from class: ohv
            @Override // java.lang.Runnable
            public final void run() {
                ohr.this.g();
            }
        });
    }

    /* synthetic */ void g() {
        LogUtil.a("SCUI_DialogCardData", "account is logout");
        List<Integer> list = this.q;
        if (list == null) {
            LogUtil.h("SCUI_DialogCardData", "LoginOut mTipsList is null");
            return;
        }
        synchronized (list) {
            this.q.clear();
            c();
        }
    }

    protected void n() {
        LogUtil.a("SCUI_DialogCardData", "updateFitnessDataOrigin enter ");
        pwm.a().a(jdl.t(System.currentTimeMillis()), new d(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final List<pwb> list) {
        if (koq.b(list)) {
            ReleaseLogUtil.e("SCUI_DialogCardData", "refreshOriginList is empty.");
        } else {
            HandlerExecutor.a(new Runnable() { // from class: ohn
                @Override // java.lang.Runnable
                public final void run() {
                    ohr.this.e(list);
                }
            });
        }
    }

    /* synthetic */ void e(List list) {
        List<pwb> list2 = this.b;
        if (list2 != null) {
            list2.clear();
            this.b.addAll(list);
            LogUtil.a("SCUI_DialogCardData", "updateFitnessDataOrigin end data ", Integer.valueOf(this.b.size()));
        }
    }

    public static class d implements CommonUiBaseResponse {
        private WeakReference<ohr> d;

        d(ohr ohrVar) {
            this.d = new WeakReference<>(ohrVar);
        }

        @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
        public void onResponse(int i, Object obj) {
            ohr ohrVar = this.d.get();
            if (ohrVar == null) {
                LogUtil.h("SCUI_DialogCardData", "stepsCardData is null");
            } else if (koq.e(obj, pwb.class)) {
                ohrVar.a((List<pwb>) obj);
            } else {
                LogUtil.h("SCUI_DialogCardData", "msg.obj not instanceof List");
            }
        }
    }

    static class b implements IExecuteResult {

        /* renamed from: a, reason: collision with root package name */
        WeakReference<ohr> f15682a;

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onFailed(Object obj) {
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onServiceException(Object obj) {
        }

        protected b(ohr ohrVar) {
            this.f15682a = new WeakReference<>(ohrVar);
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onSuccess(Object obj) {
            WeakReference<ohr> weakReference = this.f15682a;
            if (weakReference == null) {
                LogUtil.h("SCUI_DialogCardData", "mWeakRef == null");
                return;
            }
            ohr ohrVar = weakReference.get();
            if (ohrVar == null) {
                LogUtil.h("SCUI_DialogCardData", "cardData == null");
                return;
            }
            if (!(obj instanceof Bundle)) {
                LogUtil.h("SCUI_DialogCardData", "obj not instanceof Bundle");
                return;
            }
            boolean z = ((Bundle) obj).getBoolean("stepsNotifiState");
            LogUtil.a("SCUI_DialogCardData", "MessageSwitch :", Boolean.valueOf(z));
            if (z) {
                return;
            }
            ohrVar.ay();
        }
    }

    static class c implements IExecuteResult {
        WeakReference<ohr> b;

        protected c(ohr ohrVar) {
            this.b = new WeakReference<>(ohrVar);
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onSuccess(Object obj) {
            LogUtil.a("SCUI_DialogCardData", "isNeedPromptKeepAlive onSuccess");
            WeakReference<ohr> weakReference = this.b;
            if (weakReference == null) {
                LogUtil.h("SCUI_DialogCardData", "mWeakRef == null");
                return;
            }
            ohr ohrVar = weakReference.get();
            if (ohrVar == null) {
                LogUtil.h("SCUI_DialogCardData", "cardData == null");
                return;
            }
            if (obj instanceof Bundle) {
                boolean z = ((Bundle) obj).getBoolean("isNeedPromptKeepAlive", false);
                LogUtil.a("SCUI_DialogCardData", "isNeedPromptKeepAlive:", Boolean.valueOf(z));
                if (z) {
                    ohrVar.d(2);
                    LogUtil.a("SCUI_DialogCardData", "add keep alive tips");
                }
            }
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onFailed(Object obj) {
            LogUtil.h("SCUI_DialogCardData", "isNeedPromptKeepAlive onFailed");
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onServiceException(Object obj) {
            LogUtil.h("SCUI_DialogCardData", "isNeedPromptKeepAlive onServiceException");
        }
    }

    static class a implements Observer {
        private WeakReference<ohr> d;

        a(ohr ohrVar) {
            this.d = new WeakReference<>(ohrVar);
        }

        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            ohr ohrVar;
            if (!"closeStepsTips".equals(str) || (ohrVar = this.d.get()) == null) {
                return;
            }
            ohrVar.r();
        }
    }

    static class h extends BroadcastReceiver {
        private WeakReference<ohr> d;

        public h(ohr ohrVar) {
            this.d = new WeakReference<>(ohrVar);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            ohr ohrVar = this.d.get();
            if (ohrVar != null) {
                if (intent == null || ohrVar.j == null) {
                    LogUtil.h("SCUI_DialogCardData", "SyncCloudDataFailReceiver onReceive intent is null or context is null");
                    return;
                }
                LogUtil.a("SCUI_DialogCardData", "SyncCloudDataFailReceiver onReceive to enter, action = ", intent.getAction());
                String stringExtra = intent.getStringExtra("sync_cloud_data_status");
                LogUtil.a("SCUI_DialogCardData", "SyncCloudDataFailReceiver onReceive to enter, status = ", stringExtra);
                if ("sync_cloud_data_fail".equals(stringExtra)) {
                    if (!CommonUtil.aj()) {
                        ohrVar.d(100);
                        return;
                    } else {
                        LogUtil.h("SCUI_DialogCardData", "SyncCloudDataFailReceiver is Astronauts version, no dialog");
                        return;
                    }
                }
                return;
            }
            LogUtil.h("SCUI_DialogCardData", "SyncCloudDataFailReceiver DialogCardData is null");
        }
    }

    static class e extends BaseHandler<ohr> {
        public e(Looper looper, ohr ohrVar) {
            super(looper, ohrVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: daC_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(ohr ohrVar, Message message) {
            int i = message.what;
            if (i != 11) {
                if (i == 21) {
                    ohrVar.c();
                }
            } else if (ohrVar.t() != null) {
                ohrVar.t().daF_().setVisibility(8);
                ohrVar.t().daJ_().setVisibility(8);
            }
            ohrVar.daz_(message);
        }
    }

    protected void daz_(Message message) {
        int i = message.what;
        if (i == 26) {
            LogUtil.a("SCUI_DialogCardData", "isShowNps MSG_CLOSE_NPS_TIPS!");
            a(false, "", this.t);
        }
        switch (i) {
            case 30:
                if (!this.q.contains(7)) {
                    d(7);
                    break;
                }
                break;
            case 31:
                i(8);
                break;
            case 32:
                d(8);
                break;
            case 33:
                aj();
                break;
            case 34:
                aw();
                break;
            case 35:
                at();
                break;
            case 36:
                d(9);
                break;
        }
    }

    private void at() {
        LogUtil.a("SCUI_DialogCardData", "enter showDownloadPremisesPlugin");
        if (t() == null || t().daM_() == null || this.j == null) {
            LogUtil.h("SCUI_DialogCardData", "showDownloadPremisesPlugin mStepsCardViewHolder == null");
            return;
        }
        String c2 = lsp.d().c(this.g);
        if (TextUtils.isEmpty(c2)) {
            LogUtil.h("SCUI_DialogCardData", "showDownloadPremisesPlugin downloadTitle is empty");
            return;
        }
        t().j().setText(c2);
        t().y().setText(R.string.IDS_bundle_download_button);
        t().y().setOnClickListener(new View.OnClickListener() { // from class: ohr.18
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("SCUI_DialogCardData", "click download");
                ohr.this.t().daM_().setVisibility(8);
                ohr.this.c();
                lsp.d().e(ohr.this.g);
                Intent intent = new Intent();
                intent.putExtra("moduleName", ohr.this.g);
                AppBundle.e().launchActivity(ohr.this.j, intent, new AppBundleLauncher.InstallCallback() { // from class: ohr.18.2
                    @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
                    public boolean call(Context context, Intent intent2) {
                        nrh.e(ohr.this.j, R.string._2130843721_res_0x7f021849);
                        return true;
                    }
                });
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        t().v().setOnClickListener(new View.OnClickListener() { // from class: ohr.17
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("SCUI_DialogCardData", "click cancel");
                ohr.this.t().daM_().setVisibility(8);
                lsp.d().e(ohr.this.g);
                ohr.this.c();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        w();
        t().daM_().setVisibility(0);
        t().v().setVisibility(0);
        LogUtil.a("SCUI_DialogCardData", "showDownloadPremisesPlugin dialog is showing");
    }

    private void aj() {
        if (t() == null || t().daM_() == null) {
            LogUtil.h("SCUI_DialogCardData", "setVoiceRelativeLayout mStepsCardViewHolder = null");
        } else {
            t().daM_().setVisibility(8);
        }
    }

    private void aw() {
        LogUtil.a("SCUI_DialogCardData", "enter showVoiceCard");
        if (t() == null || t().daM_() == null || this.j == null) {
            LogUtil.h("SCUI_DialogCardData", "mStepsCardViewHolder == null");
            return;
        }
        if (ao()) {
            t().y().setText(R.string._2130845681_res_0x7f021ff1);
            t().v().setVisibility(8);
            t().y().setOnClickListener(new View.OnClickListener() { // from class: ohr.25
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a("SCUI_DialogCardData", "click know button");
                    ohr.this.t().daM_().setVisibility(8);
                    ohr.this.am();
                    ohr.this.c();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            w();
            t().daM_().setVisibility(0);
            LogUtil.a("SCUI_DialogCardData", "HiAi dialog is showing");
            ArrayList arrayList = new ArrayList();
            arrayList.add("PluginHiAiEngine");
            AppBundle.e().preDownloadPlugins(BaseApplication.getContext(), arrayList, true, true);
        }
    }

    private boolean ao() {
        String e2 = KeyValDbManager.b(BaseApplication.getContext()).e("downloadHiAiPlugin");
        long moduleZipSize = AppBundle.c().getModuleZipSize(BaseApplication.getContext(), "PluginHiAiEngine");
        if (TextUtils.isEmpty(e2) || moduleZipSize == 0) {
            LogUtil.a("SCUI_DialogCardData", "show flag is enpty  or pageSize is 0");
            return false;
        }
        String b2 = nsn.b(BaseApplication.getContext(), moduleZipSize);
        if ("voiceAssistant".equals(e2)) {
            t().j().setText(BaseApplication.getContext().getResources().getString(R.string._2130845679_res_0x7f021fef, b2));
            return true;
        }
        if ("bumpPass".equals(e2)) {
            t().j().setText(BaseApplication.getContext().getResources().getString(R.string._2130845124_res_0x7f021dc4, b2));
            return true;
        }
        t().j().setText(BaseApplication.getContext().getResources().getString(R.string._2130845125_res_0x7f021dc5, b2));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void am() {
        KeyValDbManager.b(BaseApplication.getContext()).d("downloadHiAiPlugin", "", null);
        LogUtil.a("SCUI_DialogCardData", "reset flag is");
    }

    private void w() {
        if (t() == null) {
            LogUtil.h("SCUI_DialogCardData", "hideAllTipDialog mStepsCardViewHolder == null");
            return;
        }
        t().daG_().setVisibility(8);
        t().daK_().setVisibility(8);
        t().daF_().setVisibility(8);
        t().daJ_().setVisibility(8);
        t().daI_().setVisibility(8);
        if (t().daN_() != null) {
            t().daN_().setVisibility(8);
        }
        t().daM_().setVisibility(8);
    }

    static class j extends PermissionsResultAction {
        private j() {
        }

        @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onGranted() {
            LogUtil.a("SCUI_DialogCardData", "mBaseCallback onGranted");
        }

        @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onDenied(String str) {
            LogUtil.h("SCUI_DialogCardData", "mBaseCallback permission denied by the user ", str);
            oht.e(BaseApplication.getActivity(), BaseApplication.getContext().getResources().getString(R.string._2130846367_res_0x7f02229f));
        }

        @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
            LogUtil.h("SCUI_DialogCardData", "mBaseCallback permission onForeverDenied by the user ", permissionType);
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public String getCardName() {
        return "SCUI_DialogCardData";
    }
}
