package defpackage;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwOtaUpgradeManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwversionmgr.manager.HwVersionManager;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.phoneservice.feedbackcommon.utils.AsCache;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.radiobutton.HealthRadioButton;
import com.huawei.ui.homewear21.home.WearHomeActivity;
import com.huawei.ui.homewear21.home.adapter.GridSpacingItemDecoration;
import com.huawei.ui.homewear21.home.adapter.WearHomeGeneralCardAdapter;
import com.huawei.ui.homewear21.home.card.WearHomeBaseCard;
import com.huawei.ui.homewear21.home.holder.WearHomeGeneralHolder;
import com.huawei.ui.homewear21.home.listener.WearHomeListener;
import health.compact.a.CommonUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class pct extends WearHomeBaseCard {

    /* renamed from: a, reason: collision with root package name */
    private pai f16064a;
    private HealthRadioButton b;
    private RelativeLayout c;
    private CustomAlertDialog d;
    private RelativeLayout f;
    private WearHomeGeneralCardAdapter h;
    private HealthRadioButton i;
    private WearHomeGeneralHolder g = null;
    private List<pbr> j = new ArrayList(16);
    private String e = "";

    public pct(Context context, WearHomeActivity wearHomeActivity) {
        this.mContext = context;
        this.mActivity = wearHomeActivity;
        this.f16064a = new pai(context, wearHomeActivity);
    }

    @Override // com.huawei.ui.homewear21.home.card.WearHomeBaseCard
    public RecyclerView.ViewHolder getCardViewHolder(ViewGroup viewGroup, LayoutInflater layoutInflater) {
        this.g = new WearHomeGeneralHolder(layoutInflater.inflate(R.layout.wear_home_general_layout, viewGroup, false));
        this.h = new WearHomeGeneralCardAdapter(this.mContext, this.j);
        this.g.d().addItemDecoration(new GridSpacingItemDecoration(true, nrr.e(this.mContext, 0.0f)));
        this.g.d().setLayoutManager(new LinearLayoutManager(this.mContext));
        this.g.d().a(false);
        this.g.d().setAdapter(this.h);
        this.g.d().setLayerType(2, null);
        this.g.d().setItemAnimator(new DefaultItemAnimator());
        this.h.e(new WearHomeListener() { // from class: pct.4
            @Override // com.huawei.ui.homewear21.home.listener.WearHomeListener
            public void onItemClick(int i) {
                pct.this.c(i);
            }
        });
        r();
        return this.g;
    }

    private void r() {
        m();
    }

    @Override // com.huawei.ui.homewear21.home.card.WearHomeBaseCard
    public void deviceConnectionChange(int i) {
        if (this.mActivity == null || this.g == null || this.h == null || this.f16064a == null) {
            LogUtil.h("WearHomeGeneralCard", "deviceConnectionChange fail mActivity:", this.mActivity, " mWearHomeGeneralHolder:", this.g, " mWearHomeGeneralCardAdapter:", this.h);
            return;
        }
        this.mActivity.f9644a = this.mActivity.d.e(this.mActivity.g);
        if (this.mActivity.f9644a == null) {
            LogUtil.h("WearHomeGeneralCard", "deviceConnectionChange fail mActivity.mDeviceCapability == null");
            this.g.dmw_().setVisibility(8);
            return;
        }
        this.mActivity.b = oxa.a().b(this.mActivity.g);
        if (this.mActivity.b == null) {
            LogUtil.h("WearHomeGeneralCard", "deviceConnectionChange fail mActivity.mCurrentDeviceInfo == null");
            return;
        }
        n();
        if (this.j.size() == 0) {
            LogUtil.h("WearHomeGeneralCard", "deviceConnectionChange mWearHomeGeneralBeans.size() == 0");
            this.g.dmw_().setVisibility(8);
        } else {
            this.g.dmw_().setVisibility(0);
            b(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        if (i == 2) {
            h();
        } else {
            for (pbr pbrVar : this.j) {
                LogUtil.a("WearHomeGeneralCard", "checkIsOtaEnd OTA item isEnable");
                if (jkj.d().c(this.mActivity.g) != 6) {
                    LogUtil.a("WearHomeGeneralCard", "checkIsOtaEnd current device is not OTAing");
                    pbrVar.d(false);
                }
            }
        }
        this.h.d(this.j);
        this.g.d().getRecycledViewPool().clear();
        this.g.d().setAdapter(this.h);
        LogUtil.a("WearHomeGeneralCard", "size() = ", Integer.valueOf(this.j.size()));
    }

    private void h() {
        if (this.mActivity.b != null && cvt.c(this.mActivity.b.getProductType())) {
            l();
            return;
        }
        if (this.mActivity.d.e(this.mActivity.g) != null && this.mActivity.d.e(this.mActivity.g).isOtaUpdate() && jkj.d().c(this.mActivity.g) == 6) {
            LogUtil.a("WearHomeGeneralCard", "refreshSettingView() wear device is OTA");
            for (pbr pbrVar : this.j) {
                pbrVar.d(false);
                if (pbrVar.b() == 13) {
                    pbrVar.d(true);
                }
            }
            return;
        }
        p();
    }

    private void p() {
        boolean z;
        boolean z2;
        if (this.mActivity.b != null) {
            z = cwi.c(this.mActivity.b, 58);
            z2 = cwi.c(this.mActivity.b, 108);
        } else {
            z = false;
            z2 = false;
        }
        for (pbr pbrVar : this.j) {
            if (this.mActivity.k && this.mActivity.b != null && pep.d(this.mActivity.b.getProductType())) {
                pbrVar.d(false);
                if (pbrVar.b() == 46 || pbrVar.b() == 32 || pbrVar.b() == 33 || pbrVar.b() == 55) {
                    pbrVar.d(true);
                }
                if (z || z2) {
                    if (pbrVar.b() == 13) {
                        pbrVar.d(true);
                    }
                }
            } else {
                pbrVar.d(true);
            }
        }
    }

    private void l() {
        if (this.mActivity.d.e(this.mActivity.g) != null && this.mActivity.d.e(this.mActivity.g).isOtaUpdate() && jkj.d().c(this.mActivity.g) == 6) {
            LogUtil.a("WearHomeGeneralCard", "refreshSettingView() AW70 is OTA");
            for (pbr pbrVar : this.j) {
                pbrVar.d(false);
                if (pbrVar.b() == 13) {
                    pbrVar.d(true);
                }
            }
        }
    }

    @Override // com.huawei.ui.homewear21.home.card.WearHomeBaseCard
    public void onResume() {
        m();
        t();
    }

    private void t() {
        LogUtil.a("WearHomeGeneralCard", "refreshOtaLittleRed");
        if (this.mActivity.b == null || !cwi.c(this.mActivity.b, 108) || Utils.l()) {
            return;
        }
        HwOtaUpgradeManager.a().d(this.mActivity.b, new HwOtaUpgradeManager.RefreshLittleRedListener() { // from class: pct.3
            @Override // com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwOtaUpgradeManager.RefreshLittleRedListener
            public void refreshLittleRed() {
                boolean n = HwVersionManager.c(pct.this.mContext).n(pct.this.mActivity.g);
                LogUtil.a("WearHomeGeneralCard", "refreshLittleRed isNewVersion:", Boolean.valueOf(n), ", size:", Integer.valueOf(pct.this.j.size()));
                for (pbr pbrVar : pct.this.j) {
                    if (pbrVar.b() == 13 && pct.this.mActivity.b != null) {
                        LogUtil.a("WearHomeGeneralCard", "refreshLittleRed setNewVersion");
                        pbrVar.a(n);
                        pct pctVar = pct.this;
                        pctVar.b(pctVar.mActivity.b.getDeviceConnectState());
                        return;
                    }
                }
            }
        });
    }

    @Override // com.huawei.ui.homewear21.home.card.WearHomeBaseCard
    public void onDestroy() {
        LogUtil.a("WearHomeGeneralCard", "onDestroy");
        this.f16064a.o();
    }

    private void m() {
        if (this.mActivity == null || this.g == null || this.h == null || this.f16064a == null) {
            LogUtil.h("WearHomeGeneralCard", "initView fail mActivity:", this.mActivity, " mWearHomeGeneralHolder:", this.g, " mWearHomeGeneralCardAdapter:", this.h);
            return;
        }
        if (this.mActivity.f9644a == null) {
            LogUtil.h("WearHomeGeneralCard", "initView fail mActivity.mDeviceCapability == null");
            this.g.dmw_().setVisibility(8);
            return;
        }
        b(false);
        this.mActivity.b = oxa.a().b(this.mActivity.g);
        if (this.mActivity.b == null) {
            LogUtil.h("WearHomeGeneralCard", "initView fail mActivity.mCurrentDeviceInfo == null");
            return;
        }
        n();
        if (this.j.size() == 0) {
            LogUtil.h("WearHomeGeneralCard", "initView mWearHomeGeneralBeans.size() == 0");
            this.g.dmw_().setVisibility(8);
        } else {
            this.g.dmw_().setVisibility(0);
            b(this.mActivity.b.getDeviceConnectState());
        }
    }

    public void b() {
        m();
    }

    private void n() {
        this.j.clear();
        if (q()) {
            c(45, this.mActivity.getString(R.string.IDS_device_wear_home_device_setting), "", this.mActivity.i);
        }
        if (this.mActivity.f9644a.isSupportFootWear() || this.mActivity.f9644a.isSupportAutoDetectMode() || this.mActivity.f9644a.isSupportRunPosture()) {
            w();
        }
        if (this.mActivity.f9644a.isFactoryReset()) {
            c(14, this.mActivity.getString(R.string._2130841511_res_0x7f020fa7), "", this.mActivity.i);
        }
        o();
        k();
        if (this.mActivity.b != null && this.mActivity.b.getAutoDetectSwitchStatus() != 1) {
            j();
            s();
        }
        LogUtil.a("WearHomeGeneralCard", "device model is ", this.mActivity.b.getDeviceModel());
        if (this.mActivity.f != 11) {
            c(65, this.mActivity.getString(R.string.IDS_wear_home_device_info), "", true);
        }
        String e = KeyValDbManager.b(BaseApplication.getContext()).e("test_ecg_nmpa_key");
        boolean c = cwi.c(this.mActivity.b, 27);
        LogUtil.a("WearHomeGeneralCard", "isSupportNmpa ", Boolean.valueOf(c));
        if (c && !TextUtils.isEmpty(e) && Boolean.parseBoolean(e)) {
            LogUtil.a("WearHomeGeneralCard", "show ecg nmpa.");
            c(51, this.mActivity.getString(R.string._2130849767_res_0x7f022fe7), "", this.mActivity.i);
        }
    }

    private void s() {
        if (CommonUtil.as()) {
            LogUtil.h("WearHomeGeneralCard", "beta app not support");
            return;
        }
        boolean bu = CommonUtil.bu();
        boolean i = Utils.i();
        if (bu || !i) {
            return;
        }
        if (CommonUtil.m(BaseApplication.getContext(), LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009)) != 1 || LanguageUtil.j(this.mContext) || LanguageUtil.p(this.mContext)) {
            c(46, this.mActivity.getString((njn.e(this.mContext) || com.huawei.operation.utils.Utils.isShowJapanCustomer(this.mContext)) ? R.string._2130843618_res_0x7f0217e2 : R.string._2130842101_res_0x7f0211f5), "", this.mActivity.i);
        }
    }

    private void j() {
        if (cvz.c(this.mActivity.b)) {
            LogUtil.h("WearHomeGeneralCard", "new honor device does not support");
            return;
        }
        if (CommonUtil.bv()) {
            LogUtil.h("Dfx_WearHomeGeneralCard", "release version not support");
            return;
        }
        String e = KeyValDbManager.b(this.mContext).e("is_support_dfx_status");
        boolean equals = "true".equals(e);
        LogUtil.a("WearHomeGeneralCard", "dfxStatus = ", e);
        boolean z = cwi.c(this.mActivity.b, 34) && jgp.a(BaseApplication.getContext()).b(this.mActivity.g) && equals;
        if (Utils.o() && z) {
            LogUtil.a("WearHomeGeneralCard", "show setting_about_beta_question.");
            c(33, this.mActivity.getString(R.string.IDS_device_beta_user_profile_questions_suggestions), "", this.mActivity.i);
        }
    }

    private void o() {
        boolean c = cwi.c(this.mActivity.b, 58);
        boolean c2 = cwi.c(this.mActivity.b, 108);
        LogUtil.a("WearHomeGeneralCard", "initFirmwareUpgradeItem isSupportOtaUpgrade:", Boolean.valueOf(c2));
        if (this.mActivity.f9644a.isOtaUpdate() || c || c2) {
            boolean n = HwVersionManager.c(this.mContext).n(this.mActivity.g);
            LogUtil.a("WearHomeGeneralCard", "initGeneralList() isNewVersion is ", Boolean.valueOf(n));
            pbr pbrVar = new pbr();
            pbrVar.a(13);
            pbrVar.a(this.mActivity.getString(R.string.IDS_device_wear_home_device_ota_upgrade));
            pbrVar.e("");
            pbrVar.d(this.mActivity.i);
            pbrVar.c("");
            d(pbrVar, n);
        }
    }

    private void k() {
        boolean z;
        boolean z2;
        LogUtil.a("WearHomeGeneralCard", "enter initDetectionItem");
        if (this.mActivity == null) {
            LogUtil.h("WearHomeGeneralCard", "mActivity is null");
            return;
        }
        if (this.mActivity.i) {
            if (this.mActivity.b != null) {
                z = cwi.c(this.mActivity.b, 71);
                z2 = cwi.c(this.mActivity.b, 115);
            } else {
                z = false;
                z2 = false;
            }
            LogUtil.a("WearHomeGeneralCard", "isSupportDetecttion = ", Boolean.valueOf(z), " isSupportDiagnosisOut = ", Boolean.valueOf(z2), " isHarmony = ", Boolean.valueOf(CommonUtil.az()));
            boolean z3 = CommonUtil.az() && z;
            boolean z4 = !CommonUtil.az() && z2;
            if ((z3 || z4) && pep.i()) {
                LogUtil.a("WearHomeGeneralCard", "show detection entrance");
                c(55, this.mActivity.getString(R.string.IDS_device_detection), "", this.mActivity.i);
                return;
            }
            return;
        }
        if (pep.i()) {
            LogUtil.a("WearHomeGeneralCard", "show isSupportConnectDetect");
            c(55, this.mActivity.getString(R.string.IDS_device_detection), "", true);
        } else {
            LogUtil.a("WearHomeGeneralCard", "not show isOversea or below android9");
        }
    }

    private void w() {
        String string;
        String str;
        StorageParams storageParams = new StorageParams();
        if (this.mActivity.f9644a.isSupportRunPosture()) {
            if (cvt.a(this.mActivity.f)) {
                str = this.mActivity.getString(R.string._2130843180_res_0x7f02162c);
                string = this.mActivity.getString(R.string._2130843181_res_0x7f02162d);
            } else {
                str = this.mActivity.getString(R.string._2130842734_res_0x7f02146e);
                string = this.mActivity.getString(R.string._2130842736_res_0x7f021470);
            }
            SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10000), "KEY_AW70_WARE_MODE", "RUNNING_MODE", storageParams);
        } else {
            string = this.mActivity.getString(R.string._2130842735_res_0x7f02146f);
            SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10000), "KEY_AW70_WARE_MODE", "HANDRING_MODE", storageParams);
            str = "";
        }
        String string2 = this.mActivity.getString(R.string._2130842733_res_0x7f02146d);
        c(36, string2, string, this.mActivity.i, str);
        LogUtil.a("WearHomeGeneralCard", "After adding AW70 mode select item");
    }

    private boolean q() {
        if (this.mActivity.f9644a.isAvoidDisturb()) {
            return true;
        }
        return (this.mActivity.f9644a.isSupportPhonesInfo() && this.mActivity.f9644a.isSupportNotifyDeviceBroadCast()) || this.mActivity.f9644a.isBluetoothOffAlert() || this.mActivity.f9644a.isSupportOneLevelMenu() || this.mActivity.f9644a.isSupportAutoLightScreen() || this.mActivity.f9644a.isSupportRotateSwitchScreen() || this.mActivity.f9644a.isSupportLeftRightHandWearMode();
    }

    private void c(int i, String str, String str2, boolean z) {
        c(i, str, str2, z, "");
    }

    private void c(int i, String str, String str2, boolean z, String str3) {
        pbr pbrVar = new pbr();
        pbrVar.a(i);
        pbrVar.a(str);
        pbrVar.e(str2);
        pbrVar.d(z);
        pbrVar.c(str3);
        d(pbrVar, false);
    }

    private void d(pbr pbrVar, boolean z) {
        pbrVar.a(z);
        this.j.add(pbrVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        if (!nsn.o() && i < this.j.size()) {
            CommonUtil.d();
            int b = this.j.get(i).b();
            LogUtil.a("WearHomeGeneralCard", "id:", Integer.valueOf(b), ", mGeneralAction: ", this.f16064a);
            if (b == 13) {
                this.f16064a.q();
                return;
            }
            if (b == 14) {
                this.f16064a.m();
                return;
            }
            if (b == 32) {
                oau.a(AsCache.FEED_BACK_CACHE_FILE_NAME);
                this.f16064a.r();
                return;
            }
            if (b == 33) {
                oau.a("beta_feedback");
                this.f16064a.l();
                return;
            }
            if (b == 36) {
                this.f16064a.n();
                return;
            }
            if (b == 51) {
                LogUtil.a("WearHomeGeneralCard", "ecg duration setting.");
                oau.a("sleep_apnea_test");
                x();
                return;
            }
            if (b == 55) {
                oau.a("device_checkup");
                v();
                return;
            }
            if (b == 65) {
                oau.a("device_info");
                this.f16064a.i();
            } else if (b == 45) {
                oau.a("device_set");
                this.f16064a.h();
            } else if (b == 46) {
                oau.a("help_customer_service");
                this.f16064a.p();
            } else {
                LogUtil.h("WearHomeGeneralCard", "setOnItemClick default");
            }
        }
    }

    private void v() {
        if (this.mActivity.i) {
            this.f16064a.t();
            LogUtil.a("WearHomeGeneralCard", "startDetection");
        } else {
            LogUtil.a("WearHomeGeneralCard", "startConnectDetection");
            this.mActivity.r.d("LOCAL_TYPE");
        }
    }

    private void x() {
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(this.mActivity);
        Object systemService = BaseApplication.getContext().getSystemService("layout_inflater");
        if (systemService instanceof LayoutInflater) {
            View inflate = ((LayoutInflater) systemService).inflate(R.layout.hw_ecg_duration_dialog, (ViewGroup) null);
            builder.a(this.mActivity.getString(R.string._2130849765_res_0x7f022fe5)).cyp_(inflate).cyn_(R.string._2130837555_res_0x7f020033, new DialogInterface.OnClickListener() { // from class: pda
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    pct.dlE_(dialogInterface, i);
                }
            });
            dlD_(inflate);
            CustomAlertDialog c = builder.c();
            this.d = c;
            c.show();
        }
    }

    static /* synthetic */ void dlE_(DialogInterface dialogInterface, int i) {
        LogUtil.c("WearHomeGeneralCard", "cancel showEcgDurationDialog");
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    private void dlD_(View view) {
        this.b = (HealthRadioButton) view.findViewById(R.id.settings_close_ecg_button);
        this.i = (HealthRadioButton) view.findViewById(R.id.settings_open_ecg_button);
        this.c = (RelativeLayout) view.findViewById(R.id.settings_close_ecg_duration);
        this.f = (RelativeLayout) view.findViewById(R.id.settings_open_ecg_duration);
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.settings_close_ecg_text);
        String string = this.mActivity.getResources().getString(R.string._2130849766_res_0x7f022fe6);
        healthTextView.setText(String.format(Locale.ENGLISH, string, 30));
        ((HealthTextView) view.findViewById(R.id.settings_open_ecg_text)).setText(String.format(Locale.ENGLISH, string, 120));
        this.c.setOnClickListener(new View.OnClickListener() { // from class: pcr
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                pct.this.dlG_(view2);
            }
        });
        this.f.setOnClickListener(new View.OnClickListener() { // from class: pcx
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                pct.this.dlH_(view2);
            }
        });
        u();
    }

    /* synthetic */ void dlG_(View view) {
        a(0);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void dlH_(View view) {
        a(1);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a(int i) {
        jfz.d().d(i, new IBaseResponseCallback() { // from class: pcy
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i2, Object obj) {
                pct.b(i2, obj);
            }
        });
        CustomAlertDialog customAlertDialog = this.d;
        if (customAlertDialog != null) {
            customAlertDialog.dismiss();
        }
        e(d(i));
        u();
    }

    static /* synthetic */ void b(int i, Object obj) {
        if (i == 0) {
            LogUtil.a("WearHomeGeneralCard", "send 5.35.16 success.");
        } else {
            LogUtil.h("WearHomeGeneralCard", "sendEcgDuration error : ", Integer.valueOf(i));
        }
    }

    private JSONObject d(int i) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("get_ecg_authentication_switch_from_device", i);
        } catch (JSONException unused) {
            LogUtil.b("WearHomeGeneralCard", "createJson JSONException");
        }
        return jSONObject;
    }

    private void u() {
        int a2 = jfz.d().a();
        HealthRadioButton healthRadioButton = this.b;
        if (healthRadioButton == null || this.i == null) {
            LogUtil.h("WearHomeGeneralCard", "showEcgDurationButton button is null.");
            return;
        }
        if (a2 == 0) {
            healthRadioButton.setChecked(true);
            this.i.setChecked(false);
        } else if (a2 == 1) {
            healthRadioButton.setChecked(false);
            this.i.setChecked(true);
        } else {
            LogUtil.h("WearHomeGeneralCard", "no support status;");
            this.b.setChecked(true);
            this.i.setChecked(false);
        }
    }

    private void e(JSONObject jSONObject) {
        SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(35), "storage_ecg_duration_json", jSONObject.toString(), new StorageParams(1));
    }

    public void f() {
        this.f16064a.d(false);
        if (!this.f16064a.d() || this.mActivity.isFinishing()) {
            return;
        }
        LogUtil.a("WearHomeGeneralCard", "upload log overtimes toast");
        if (!this.f16064a.g()) {
            LogUtil.a("WearHomeGeneralCard", "uploadLogTimeout 5.10.12 not timeout");
            nrh.e(this.mContext, R.string._2130842450_res_0x7f021352);
        }
        this.f16064a.e(false);
        jsd.e(false);
    }

    public void dlF_(Message message) {
        if (message == null || this.f16064a.j()) {
            LogUtil.h("WearHomeGeneralCard", "isCollectOver = ", Boolean.valueOf(this.f16064a.j()));
            return;
        }
        String str = message.obj instanceof String ? (String) message.obj : "";
        a(message.arg1, str);
        if (str.equals(this.e)) {
            return;
        }
        this.e = str;
        LogUtil.a("WearHomeGeneralCard", "getDeviceLogProcess mLastProgressDesc :", str);
    }

    public void i() {
        this.f16064a.s();
    }

    public void d() {
        LogUtil.a("WearHomeGeneralCard", 1, "WearHomeGeneralCard", "reset factory success");
        this.mActivity.d.b();
        b(false);
        this.f16064a.c();
        ixx.d().a(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011));
    }

    public void b(boolean z) {
        this.f16064a.b(z);
    }

    public void a() {
        this.f16064a.f();
    }

    public void d(String str) {
        this.f16064a.d(str);
    }

    public void g() {
        this.f16064a.k();
    }

    private void a(int i, String str) {
        this.f16064a.b();
        this.f16064a.b(i, str);
        LogUtil.a("WearHomeGeneralCard", "mCustomProgressDialog.show()");
    }

    public void c(int i, String str, String str2) {
        this.f16064a.d(i, str, str2);
    }

    public void c() {
        LogUtil.a("WearHomeGeneralCard", "enter handlerDeviceGotoFeedBack");
        if (Utils.o()) {
            jeq.e().setProductType(20);
        } else {
            jeq.e().setProductType(1);
        }
        this.f16064a.a();
    }

    public void e() {
        this.f16064a.e(10001, "5.10.12 cancel timeout 7 minutes");
    }
}
