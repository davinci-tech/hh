package defpackage;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.model.ecgservice.EcgServiceActivationData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.device.utlis.BluetoothPermisionUtils;
import com.huawei.ui.homewear21.home.WearHomeActivity;
import com.huawei.ui.homewear21.home.card.WearHomeBaseCard;
import com.huawei.ui.homewear21.home.holder.WearHomeTipHolder;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.util.Locale;

/* loaded from: classes6.dex */
public class pds extends WearHomeBaseCard {
    private WearHomeTipHolder b;
    private int c = 2;

    /* renamed from: a, reason: collision with root package name */
    private IBaseResponseCallback f16082a = new IBaseResponseCallback() { // from class: pds.4
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("WearHomeTipPermissionCard", "WearHomeTipPermissionCard Ecg Callback onResponse errCode:", Integer.valueOf(i));
            if (i == 0) {
                pds.this.j();
            }
            pds.this.a();
        }
    };
    private IBaseResponseCallback d = new IBaseResponseCallback() { // from class: pdv
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public final void d(int i, Object obj) {
            pds.this.d(i, obj);
        }
    };
    private IBaseResponseCallback e = new IBaseResponseCallback() { // from class: pds.1
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("WearHomeTipPermissionCard", "mBaseCallback onResponse errCode:", Integer.valueOf(i));
            if (i == 0) {
                LogUtil.a("WearHomeTipPermissionCard", "mBaseCallback onResponse success");
                pds.this.mActivity.h();
                pds.this.b();
                if (pds.this.mActivity != null) {
                    PermissionUtil.b(pds.this.mActivity, PermissionUtil.PermissionType.LOCATION, new CustomPermissionAction(pds.this.mActivity) { // from class: pds.1.1
                        @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                        public void onGranted() {
                            LogUtil.h("WearHomeTipPermissionCard", "mBaseCallback onGranted");
                            pds.this.e();
                        }

                        @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                        public void onDenied(String str) {
                            LogUtil.h("WearHomeTipPermissionCard", "mBaseCallback permission denied by the user");
                        }

                        @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                        public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
                            LogUtil.h("WearHomeTipPermissionCard", "mBaseCallback permission onForeverDenied by the user");
                            super.onForeverDenied(permissionType);
                        }
                    });
                }
            }
        }
    };

    /* synthetic */ void d(int i, Object obj) {
        LogUtil.a("WearHomeTipPermissionCard", "mNearbyPermissionCallback onResponse errCode:", Integer.valueOf(i));
        if (i == 0) {
            LogUtil.a("WearHomeTipPermissionCard", "mNearbyPermissionCallback onResponse success");
            this.mActivity.h();
            b();
            if (this.mActivity != null) {
                PermissionUtil.b(this.mActivity, PermissionUtil.PermissionType.SCAN, new CustomPermissionAction(this.mActivity) { // from class: pds.3
                    @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                    public void onGranted() {
                        LogUtil.a("WearHomeTipPermissionCard", "mNearbyPermissionCallback granted");
                    }
                });
            }
        }
    }

    public pds(Context context, WearHomeActivity wearHomeActivity) {
        this.mActivity = wearHomeActivity;
        this.mContext = context;
    }

    @Override // com.huawei.ui.homewear21.home.card.WearHomeBaseCard
    public RecyclerView.ViewHolder getCardViewHolder(ViewGroup viewGroup, LayoutInflater layoutInflater) {
        this.b = new WearHomeTipHolder(layoutInflater.inflate(R.layout.wear_home_tip_layout, viewGroup, false));
        LogUtil.a("WearHomeTipPermissionCard", "getCardViewHolder onUiCreate");
        g();
        return this.b;
    }

    private void g() {
        n();
    }

    @Override // com.huawei.ui.homewear21.home.card.WearHomeBaseCard
    public void deviceConnectionChange(int i) {
        LogUtil.a("WearHomeTipPermissionCard", "deviceConnectionChange:", Integer.valueOf(i));
        this.c = i;
    }

    @Override // com.huawei.ui.homewear21.home.card.WearHomeBaseCard
    public void onResume() {
        n();
    }

    @Override // com.huawei.ui.homewear21.home.card.WearHomeBaseCard
    public void onDestroy() {
        LogUtil.a("WearHomeTipPermissionCard", "onDestroy");
    }

    private void n() {
        if (this.b == null) {
            return;
        }
        h();
        b();
    }

    public void a() {
        LogUtil.a("WearHomeTipPermissionCard", "enter initPermissionView");
        if (BluetoothPermisionUtils.e(this.mContext) && PermissionUtil.e(this.mContext, PermissionUtil.PermissionType.SCAN) == PermissionUtil.PermissionResult.GRANTED) {
            this.b.dmG_().setVisibility(8);
            int i = AnonymousClass8.f16084a[PermissionUtil.e(this.mContext, PermissionUtil.PermissionType.LOCATION_WITH_BACKGROUND).ordinal()];
            if (i == 1) {
                LogUtil.a("WearHomeTipPermissionCard", "GRANTED");
                return;
            }
            if (i == 2) {
                LogUtil.a("WearHomeTipPermissionCard", "DENIED");
                f();
            } else if (i == 3) {
                LogUtil.a("WearHomeTipPermissionCard", "FOREVER_DENIED");
                i();
            } else {
                LogUtil.a("WearHomeTipPermissionCard", "default");
            }
        }
    }

    /* renamed from: pds$8, reason: invalid class name */
    static /* synthetic */ class AnonymousClass8 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f16084a;

        static {
            int[] iArr = new int[PermissionUtil.PermissionResult.values().length];
            f16084a = iArr;
            try {
                iArr[PermissionUtil.PermissionResult.GRANTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f16084a[PermissionUtil.PermissionResult.DENIED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f16084a[PermissionUtil.PermissionResult.FOREVER_DENIED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        boolean z = false;
        boolean isSupportEcgAuth = this.mActivity.f9644a != null ? this.mActivity.f9644a.isSupportEcgAuth() : false;
        if (cwi.c(this.mActivity.b, 106) && drl.c("com.huawei.health_deviceFeature_config", "txt", "com.huawei.health.h5.ecgce")) {
            z = true;
        }
        boolean o = Utils.o();
        LogUtil.a("WearHomeTipPermissionCard", "startEcgForParamsByCapability isSupportEcgAuth:", Boolean.valueOf(isSupportEcgAuth), ", isSupportEcgAnalysis:", Boolean.valueOf(z), ", isOversea:", Boolean.valueOf(o));
        if (z) {
            LogUtil.a("WearHomeTipPermissionCard", "startEcgForParamsByCapability start ecg analysis");
            mxv.b(BaseApplication.getContext(), "com.huawei.health.h5.ecgce", 2, "interpretation");
        } else {
            if (!isSupportEcgAuth || o) {
                return;
            }
            mxv.b(BaseApplication.getContext(), "com.huawei.health.ecg.collection", 2, "interpretation");
        }
    }

    private void h() {
        if (BluetoothPermisionUtils.e(this.mContext)) {
            this.b.dmG_().setVisibility(8);
            return;
        }
        this.b.c().setText(this.mContext.getResources().getString(R.string._2130844022_res_0x7f021976).toUpperCase(Locale.ENGLISH));
        this.b.b().setText(this.mContext.getResources().getString(R.string._2130841939_res_0x7f021153).toUpperCase(Locale.ENGLISH));
        String string = this.mContext.getResources().getString(R.string._2130846462_res_0x7f0222fe);
        String string2 = this.mContext.getResources().getString(R.string._2130846463_res_0x7f0222ff);
        nlg.cya_(BaseApplication.getContext(), "nearby_permissions_tip", this.d, this.b.dmG_(), string, string2);
        this.b.e().setText(string2);
    }

    private void f() {
        String string;
        if (jpo.c(this.mActivity.g) == 2) {
            LogUtil.h("WearHomeTipPermissionCard", "initPermissionToastView is family_pair_mode");
            return;
        }
        LogUtil.a("WearHomeTipPermissionCard", "initPermissionToastView");
        if (this.mContext == null) {
            return;
        }
        this.b.c().setText(this.mContext.getResources().getString(R.string._2130844022_res_0x7f021976).toUpperCase(Locale.ENGLISH));
        this.b.d().setText(this.mContext.getResources().getString(R.string._2130842880_res_0x7f021500).toUpperCase(Locale.ENGLISH));
        this.b.b().setText(this.mContext.getResources().getString(R.string._2130841939_res_0x7f021153).toUpperCase(Locale.ENGLISH));
        String string2 = this.mContext.getResources().getString(R.string._2130843660_res_0x7f02180c);
        if (this.mActivity.f == 75) {
            String string3 = this.mContext.getResources().getString(R.string._2130845604_res_0x7f021fa4);
            nlg.cya_(BaseApplication.getContext(), "locations_permissions_tip", this.e, this.b.dmG_(), string2, ((Object) dmc_(string3)) + "");
            this.b.e().setText(dmc_(string3));
            return;
        }
        boolean c = cwi.c(this.mActivity.b, 3);
        if (c) {
            string = this.mContext.getResources().getString(R.string._2130845939_res_0x7f0220f3);
        } else {
            string = this.mContext.getResources().getString(R.string._2130844018_res_0x7f021972);
        }
        nlg.cya_(BaseApplication.getContext(), "locations_permissions_tip", this.e, this.b.dmG_(), string2, ((Object) dmb_(string, c)) + "");
        this.b.e().setText(dmb_(string, c));
    }

    private SpannableString dmc_(String str) {
        String string = this.mContext.getResources().getString(R.string._2130845629_res_0x7f021fbd);
        String string2 = this.mContext.getResources().getString(R.string._2130844021_res_0x7f021975);
        String format = String.format(Locale.ENGLISH, str, string, string2);
        SpannableString spannableString = new SpannableString(format);
        int indexOf = format.indexOf(string);
        if (indexOf < 0) {
            LogUtil.h("WearHomeTipPermissionCard", "getPermissionToastStringBolt: firstStart is less than 0");
            return null;
        }
        spannableString.setSpan(new ForegroundColorSpan(this.mContext.getResources().getColor(R.color._2131299236_res_0x7f090ba4)), indexOf, string.length() + indexOf, 17);
        int indexOf2 = format.indexOf(string2);
        if (indexOf2 < 0) {
            LogUtil.h("WearHomeTipPermissionCard", "getPermissionToastStringBolt: secondStart is less than 0");
            return null;
        }
        spannableString.setSpan(new ForegroundColorSpan(this.mContext.getResources().getColor(R.color._2131299236_res_0x7f090ba4)), indexOf2, string2.length() + indexOf2, 17);
        LogUtil.h("WearHomeTipPermissionCard", "getPermissionToastStringBolt spannableString: ", ((Object) spannableString) + "");
        return spannableString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (!PermissionUtil.c()) {
            LogUtil.a("WearHomeTipPermissionCard", "Current version is not bigger than Q");
            return;
        }
        LogUtil.a("WearHomeTipPermissionCard", "applyBackgroundLocationPermission requestPermission");
        if (this.mActivity != null) {
            PermissionUtil.b(this.mActivity, PermissionUtil.PermissionType.LOCATION_WITH_BACKGROUND, new CustomPermissionAction(this.mActivity) { // from class: pds.5
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    LogUtil.a("WearHomeTipPermissionCard", "applyBackgroundLocationPermission onGranted");
                }

                @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onDenied(String str) {
                    LogUtil.a("WearHomeTipPermissionCard", "applyBackgroundLocationPermission permission denied by the user");
                }
            });
        }
    }

    private SpannableString dmb_(String str, boolean z) {
        String string;
        String format;
        String string2 = this.mContext.getResources().getString(R.string._2130844019_res_0x7f021973);
        String string3 = this.mContext.getResources().getString(R.string._2130844020_res_0x7f021974);
        if (Utils.o()) {
            string = this.mContext.getResources().getString(R.string._2130845940_res_0x7f0220f4);
        } else {
            string = this.mContext.getResources().getString(R.string._2130845941_res_0x7f0220f5);
        }
        LogUtil.h("WearHomeTipPermissionCard", "getPermissionToastString wifiGoTransfer: ", string);
        String string4 = this.mContext.getResources().getString(R.string._2130844021_res_0x7f021975);
        if (z) {
            format = String.format(Locale.ENGLISH, str, string2, string3, string, string4);
        } else {
            format = String.format(Locale.ENGLISH, str, string2, string3, string4);
        }
        return dmd_(z, string, format);
    }

    private SpannableString dmd_(boolean z, String str, String str2) {
        String string = this.mContext.getResources().getString(R.string._2130844019_res_0x7f021973);
        SpannableString spannableString = new SpannableString(str2);
        int indexOf = str2.indexOf(string);
        if (indexOf < 0) {
            LogUtil.h("WearHomeTipPermissionCard", "getPermissionToastString: firstStart is less than 0");
            return null;
        }
        spannableString.setSpan(new ForegroundColorSpan(this.mContext.getResources().getColor(R.color._2131299236_res_0x7f090ba4)), indexOf, string.length() + indexOf, 17);
        String string2 = this.mContext.getResources().getString(R.string._2130844020_res_0x7f021974);
        int indexOf2 = str2.indexOf(string2);
        if (indexOf2 < 0) {
            LogUtil.h("WearHomeTipPermissionCard", "getMidwareAuthorityDeclaration: secondStart is less than 0");
            return null;
        }
        spannableString.setSpan(new ForegroundColorSpan(this.mContext.getResources().getColor(R.color._2131299236_res_0x7f090ba4)), indexOf2, string2.length() + indexOf2, 17);
        if (z) {
            int indexOf3 = str2.indexOf(str);
            if (indexOf3 < 0) {
                LogUtil.h("WearHomeTipPermissionCard", "getMidwareAuthorityDeclaration: thirdStart is less than 0");
                return new SpannableString("");
            }
            spannableString.setSpan(new ForegroundColorSpan(this.mContext.getResources().getColor(R.color._2131299236_res_0x7f090ba4)), indexOf3, str.length() + indexOf3, 17);
        }
        String string3 = this.mContext.getResources().getString(R.string._2130844021_res_0x7f021975);
        int indexOf4 = str2.indexOf(string3);
        if (indexOf4 < 0) {
            LogUtil.h("WearHomeTipPermissionCard", "getMidwareAuthorityDeclaration: thirdStart is less than 0");
            return null;
        }
        spannableString.setSpan(new ForegroundColorSpan(this.mContext.getResources().getColor(R.color._2131299236_res_0x7f090ba4)), indexOf4, string3.length() + indexOf4, 17);
        LogUtil.h("WearHomeTipPermissionCard", "spannableString: ", ((Object) spannableString) + "");
        return spannableString;
    }

    private void i() {
        String string;
        LogUtil.a("WearHomeTipPermissionCard", "initToSetToastView");
        if (this.mContext == null) {
            return;
        }
        WearHomeTipHolder wearHomeTipHolder = this.b;
        if (wearHomeTipHolder == null) {
            LogUtil.h("WearHomeTipPermissionCard", "initToSetToastView mWearHomeTipHolder is null");
            return;
        }
        wearHomeTipHolder.dmG_().setVisibility(8);
        this.b.c().setText(this.mContext.getResources().getString(R.string._2130842041_res_0x7f0211b9).toUpperCase(Locale.ENGLISH));
        this.b.d().setText(this.mContext.getResources().getString(R.string._2130842880_res_0x7f021500).toUpperCase(Locale.ENGLISH));
        this.b.b().setText(this.mContext.getResources().getString(R.string._2130841939_res_0x7f021153).toUpperCase(Locale.ENGLISH));
        String string2 = this.mContext.getResources().getString(R.string._2130843660_res_0x7f02180c);
        boolean c = cwi.c(this.mActivity.b, 3);
        if (c) {
            string = this.mContext.getResources().getString(R.string._2130845939_res_0x7f0220f3);
        } else {
            string = this.mContext.getResources().getString(R.string._2130844018_res_0x7f021972);
        }
        String str = string + this.mContext.getResources().getString(nsn.d(PermissionUtil.PermissionType.LOCATION_WITH_BACKGROUND));
        nlg.cya_(BaseApplication.getContext(), "locations_permissions_tip", new IBaseResponseCallback() { // from class: pds.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("WearHomeTipPermissionCard", "initToSetToastView onResponse errCode:", Integer.valueOf(i));
                if (i == 0) {
                    LogUtil.a("WearHomeTipPermissionCard", "initToSetToastView onResponse success");
                    pds.this.mActivity.h();
                    nsn.ak(pds.this.mContext);
                }
            }
        }, this.b.dmG_(), string2, ((Object) dmb_(str, c)) + "");
        this.b.e().setText(dmb_(str, c));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (this.c != 2) {
            LogUtil.h("WearHomeTipPermissionCard", "initEcgServiceActiveCardView device disconnect.");
            this.b.dmG_().setVisibility(8);
            return;
        }
        if (this.mContext == null || Utils.o()) {
            LogUtil.h("WearHomeTipPermissionCard", "initEcgServiceActiveCardView mContext is null");
            a();
            return;
        }
        boolean c = this.mActivity.b != null ? cwi.c(this.mActivity.b, 45) : false;
        LogUtil.a("WearHomeTipPermissionCard", "isSupportEcgParsingService:", Boolean.valueOf(c));
        if (!c) {
            LogUtil.h("WearHomeTipPermissionCard", "not support EcgServiceIV");
            a();
        } else {
            jgc.a().e(new IBaseResponseCallback() { // from class: pds.7
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (!(obj instanceof EcgServiceActivationData)) {
                        LogUtil.h("WearHomeTipPermissionCard", "objData not instanceof EcgServiceActivationData");
                        pds.this.b(1038);
                        return;
                    }
                    EcgServiceActivationData ecgServiceActivationData = (EcgServiceActivationData) obj;
                    LogUtil.a("WearHomeTipPermissionCard", "WearHomeTipPermissionCard data is ok.");
                    if (ecgServiceActivationData.getStatus() != 0 && (ecgServiceActivationData.getStatus() != 1 || ecgServiceActivationData.getProbationUser() != 0)) {
                        pds.this.b(1038);
                    } else {
                        LogUtil.a("WearHomeTipPermissionCard", "Need show EcgServiceActiveCardView.");
                        pds.this.b(1036);
                    }
                }
            }, this.mActivity.b);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        if (this.mActivity == null || this.mActivity.djG_() == null) {
            return;
        }
        this.mActivity.djG_().sendEmptyMessage(i);
    }

    public void c() {
        this.b.c().setText(this.mContext.getResources().getString(R.string._2130838367_res_0x7f02035f).toUpperCase(Locale.ENGLISH));
        this.b.b().setText(this.mContext.getResources().getString(R.string._2130838404_res_0x7f020384).toUpperCase(Locale.ENGLISH));
        nlg.cxW_(BaseApplication.getContext(), this.f16082a, this.b.dmG_(), this.mContext.getResources().getString(R.string._2130838366_res_0x7f02035e), this.mContext.getResources().getString(R.string._2130838365_res_0x7f02035d, "599"));
    }

    public static void d() {
        if (SharedPreferenceManager.a(Integer.toString(10006), "has_synchronize_wear_activity_notify", false)) {
            return;
        }
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10006), "wear_activity_notify_open_no_again_tip");
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10006), "wear_activity_notify_open_dialog_time");
        String b3 = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10006), "wear_activity_notify_open_count");
        String b4 = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10006), "wear_activity_notify_open_is_shown");
        String[] strArr = {"locations_permissions_tip", "nearby_permissions_tip", "notification_open_tip"};
        for (int i = 0; i < 3; i++) {
            String str = strArr[i];
            String str2 = "wear_activity_tip_no_again" + str;
            String str3 = "wear_activity_tip_last_time" + str;
            String str4 = "wear_activity_tip_open_count " + str;
            String str5 = "wear_activity_tip_is_shown" + str;
            if (!TextUtils.isEmpty(b)) {
                SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10006), str2, b, new StorageParams());
            }
            if (!TextUtils.isEmpty(b2)) {
                SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10006), str3, b2, new StorageParams());
            }
            if (!TextUtils.isEmpty(b3)) {
                SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10006), str4, b3, new StorageParams());
            }
            if (!TextUtils.isEmpty(b)) {
                SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10006), str5, b4, new StorageParams());
            }
        }
        SharedPreferenceManager.e(Integer.toString(10006), "has_synchronize_wear_activity_notify", true);
    }
}
