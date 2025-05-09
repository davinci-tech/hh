package defpackage;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.health.R;
import com.huawei.health.device.ui.DeviceMainActivity;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.ui.util.PermissionDialogHelper;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.health.marketing.views.ColumnLinearLayout;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.operation.beans.TitleBean;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.dotspageindicator.HealthDotsPageIndicator;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.device.activity.appmarket.HwSmartAppMarketLoadingActivity;
import com.huawei.ui.device.utlis.BluetoothPermisionUtils;
import com.huawei.ui.homehealth.device.CardDeviceFragment;
import com.huawei.ui.homehealth.device.adapter.CardDeviceAdapter;
import com.huawei.ui.homehealth.device.adapter.DeviceRecommendedAdapter;
import com.huawei.ui.homehealth.device.devicelist.DeviceMoreListActivity;
import com.huawei.ui.homehealth.device.devicelist.MoreSportDeviceActivity;
import com.huawei.ui.homehealth.device.sitting.RecommendedItem;
import com.huawei.ui.homehealth.device.view.DeviceItemDecoration;
import com.huawei.ui.homehealth.device.view.RecommendItemDecoration;
import com.huawei.ui.homehealth.syncwifi.WifiConnectReceiver;
import com.huawei.ui.homewear21.home.MusicSecondaryMenuActivity;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.mvp.model.latona.provider.WatchFaceProvider;
import com.huawei.watchface.mvp.ui.activity.PrivacyStatementActivity;
import defpackage.oek;
import defpackage.ogq;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.DeviceUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.LanguageUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.LogAnonymous;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class oek {
    private static final Object c = new Object();
    private static oek d;
    private CustomTextAlertDialog b;
    private CustomTextAlertDialog g = null;

    /* renamed from: a, reason: collision with root package name */
    private CustomTextAlertDialog f15635a = null;
    private NoTitleCustomAlertDialog j = null;
    private NoTitleCustomAlertDialog e = null;
    private final BroadcastReceiver f = new BroadcastReceiver() { // from class: oek.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("CardDeviceFragment CardDeviceHelper", "mWatchfaceBroadcastReceiver intent is null");
            } else {
                if (PrivacyStatementActivity.ACTION_WATCHFACE_SERVICE_DISABLE.equals(intent.getAction())) {
                    if (Utils.l()) {
                        return;
                    }
                    jqi.a().setSwitchSetting("watch_face_privacy_service_status", "2", null);
                    return;
                }
                LogUtil.h("CardDeviceFragment CardDeviceHelper", "mNonLocalBroadcastReceiver()  intent : ", intent.getAction());
            }
        }
    };

    private oek() {
    }

    public void e(ogq.c cVar, CardDeviceFragment cardDeviceFragment, int i, int i2) {
        if (cardDeviceFragment == null) {
            LogUtil.h("CardDeviceFragment CardDeviceHelper", "checkHonorPrivacyType fragment == null");
            return;
        }
        if (i2 == 1) {
            if (i < 0 || i >= cardDeviceFragment.ak.size()) {
                return;
            }
            cXs_(cardDeviceFragment, i, null);
            return;
        }
        if (i2 == 2) {
            if (cVar == null || cVar.d() == null || !(cVar.d().i() instanceof cpm)) {
                return;
            }
            e(cardDeviceFragment, (cpm) cVar.d().i());
            return;
        }
        if (i2 != 5) {
            LogUtil.h("CardDeviceFragment CardDeviceHelper", "mHonorPrivacyCallback clickType is unknown.");
            return;
        }
        if (cVar == null) {
            LogUtil.h("CardDeviceFragment CardDeviceHelper", "CLICK_DEVICE_RECOMMENDED_ZONE parameter == null");
            return;
        }
        RecommendedItem e = cVar.e();
        if (e != null) {
            LogUtil.a("CardDeviceFragment CardDeviceHelper", "mHonorPrivacyCallback id: ", Integer.valueOf(e.getId()));
            c(cardDeviceFragment, e.getId());
        }
    }

    public void cXQ_(Context context, Intent intent, CardDeviceFragment cardDeviceFragment, Handler handler) {
        if ("com.huawei.bone.action.DEVICE_UPGRADING".equals(intent.getAction()) || "action_ota_check_new_version_state".equals(intent.getAction())) {
            LogUtil.a("CardDeviceFragment CardDeviceHelper", "mNonLocalBroadcastReceiver ACTION_DEVICE_UPGRADING ACTION_OTA_CHECK_NEW_VERSION_STATE");
            cardDeviceFragment.j.notifyDataSetChanged();
            return;
        }
        if ("com.huawei.bone.action.DEVICE_THIRD_DELETE".equals(intent.getAction())) {
            handler.sendEmptyMessage(35);
            return;
        }
        if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction()) || "com.huawei.health.action.CLOUD_CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
            cardDeviceFragment.cXe_(intent, handler);
            cardDeviceFragment.cXb_(intent);
            return;
        }
        if ("com.huawei.bone.action.PHONE_SERVICE_BIND_SUCCESS".equals(intent.getAction()) || "com.huawei.bone.action.UI_DEVICE_LIST_CHANGED".equals(intent.getAction())) {
            cardDeviceFragment.h();
            return;
        }
        if ("broadcast_receiver_user_setting".equals(intent.getAction())) {
            String stringExtra = intent.getStringExtra("pairGuideSelectAddress");
            LogUtil.a("CardDeviceFragment CardDeviceHelper", "mNonLocalBroadcastReceiver() device Pair failed");
            ogj.b(stringExtra);
        } else {
            if ("com.huawei.bone.action.SYSTEM_BLUETOOTH_UNBIND_DEVICE".equals(intent.getAction())) {
                try {
                    String stringExtra2 = intent.getStringExtra("DEVICE_SECURITY_UUID");
                    LogUtil.a("CardDeviceFragment CardDeviceHelper", "SYSTEM_BLUETOOTH_UNBIND_DEVICE securityUuid:", CommonUtil.l(stringExtra2));
                    owp.c(stringExtra2 + "#ANDROID21");
                    return;
                } catch (ClassCastException unused) {
                    LogUtil.b("CardDeviceFragment CardDeviceHelper", "ClassCastException");
                    return;
                }
            }
            if ("com.huawei.health.action.PAIR_DEVICE_SUCCESS".equals(intent.getAction())) {
                ogj.b(cardDeviceFragment.s);
            } else {
                LogUtil.a("CardDeviceFragment CardDeviceHelper", "mNonLocalBroadcastReceiver()  intent : ", intent.getAction());
            }
        }
    }

    public static oek e() {
        synchronized (c) {
            if (d == null) {
                d = new oek();
            }
        }
        return d;
    }

    public void c(CardDeviceFragment cardDeviceFragment) {
        if (cardDeviceFragment == null) {
            LogUtil.a("CardDeviceFragment CardDeviceHelper", "setAllBroadcastReceiver deviceCardView or cardDeviceFragment is null");
            return;
        }
        if (CommonUtil.ce()) {
            jfr.d("CardDeviceFragment CardDeviceHelper", cardDeviceFragment.e);
            ogj.cZI_(cardDeviceFragment.an);
            ogj.cZG_(cardDeviceFragment.l);
            ogj.cZJ_(this.f, cardDeviceFragment.ba);
            ogj.cZH_(cardDeviceFragment.getActivity(), cardDeviceFragment.ah, cardDeviceFragment.aa);
            pep.dmS_(cardDeviceFragment.n, "com.huawei.appmarket.action.REFRESH_DEVICE_APP_UPDATE");
            if (CommonUtil.ar()) {
                ogj.n();
            }
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(HwWatchFaceApi.ACTION_FOREGROUND_STATUS);
            intentFilter.addAction("com.huawei.health.action.SCREEN_SHOT_PERMISSION_QUEST");
            BroadcastManagerUtil.bFE_(cardDeviceFragment.g, cardDeviceFragment.av, intentFilter);
        }
    }

    public void cXL_(CardDeviceFragment cardDeviceFragment, View view) {
        ViewStub viewStub;
        if (view == null || cardDeviceFragment == null) {
            LogUtil.a("CardDeviceFragment CardDeviceHelper", "initDeviceCardView deviceCardView or fragment is null");
            return;
        }
        cardDeviceFragment.f = (LinearLayout) nsy.cMd_(view, R.id.auto_switch_layout);
        cardDeviceFragment.k = (HealthCardView) nsy.cMd_(view, R.id.device_card_normal);
        cardDeviceFragment.az = (RelativeLayout) nsy.cMd_(view, R.id.vmall_recommended_layout);
        cardDeviceFragment.aw = (HealthRecycleView) nsy.cMd_(view, R.id.card_device_list);
        cardDeviceFragment.bc = (HealthViewPager) nsy.cMd_(view, R.id.view_pager_vip);
        cardDeviceFragment.q = (HealthDotsPageIndicator) nsy.cMd_(view, R.id.indicator_vip);
        cardDeviceFragment.r = (LinearLayout) nsy.cMd_(view, R.id.device_function_layout);
        cardDeviceFragment.m = (HealthCardView) nsy.cMd_(view, R.id.device_function_card);
        cardDeviceFragment.aq = (HealthRecycleView) nsy.cMd_(view, R.id.recommended_list);
        cardDeviceFragment.ap = (HealthRecycleView) nsy.cMd_(view, R.id.vmall_recommended_list);
        cardDeviceFragment.ar = (RelativeLayout) nsy.cMd_(view, R.id.recommended_layout);
        cardDeviceFragment.am = (LinearLayout) nsy.cMd_(view, R.id.device_card_list_more_add);
        cardDeviceFragment.aj = (LinearLayout) nsy.cMd_(view, R.id.no_device_marketing_layout);
        cardDeviceFragment.d = (LinearLayout) nsy.cMd_(view, R.id.add_device_marketing_layout);
        if (Utils.o()) {
            viewStub = (ViewStub) nsy.cMd_(view, R.id.oversea_adddevice);
        } else {
            viewStub = (ViewStub) nsy.cMd_(view, R.id.domestic_adddevice);
        }
        if (viewStub != null) {
            cardDeviceFragment.i = (LinearLayout) viewStub.inflate().findViewById(R.id.bottom_layout);
            cardDeviceFragment.ag = (HealthButton) nsy.cMd_(cardDeviceFragment.i, R.id.more_device_click);
            cardDeviceFragment.c = (HealthButton) nsy.cMd_(cardDeviceFragment.i, R.id.add_device_click);
        }
    }

    public void cXN_(CardDeviceFragment cardDeviceFragment, View view) {
        FragmentActivity activity = cardDeviceFragment.getActivity();
        if (activity == null || view == null) {
            LogUtil.h("CardDeviceFragment CardDeviceHelper", "initNearbyPermissionTip activity or deviceCardView == null");
            return;
        }
        final LinearLayout linearLayout = (LinearLayout) nsy.cMd_(view, R.id.nearby_permission_top_layout);
        if (SharedPreferenceManager.a(Integer.toString(PrebakedEffectId.RT_FLY), "is_base_service_model", false)) {
            LogUtil.a("CardDeviceFragment CardDeviceHelper", "initNearbyPermissionTip is base service model");
            linearLayout.setVisibility(8);
        } else if (BluetoothPermisionUtils.e(activity)) {
            LogUtil.a("CardDeviceFragment CardDeviceHelper", "initNearbyPermissionTip havePermission");
            linearLayout.setVisibility(8);
        } else {
            BluetoothPermisionUtils.NearbyPermissionAction nearbyPermissionAction = new BluetoothPermisionUtils.NearbyPermissionAction(activity) { // from class: oek.2
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    LogUtil.a("CardDeviceFragment CardDeviceHelper", "tryRequestNearbyPermission granted");
                    jfq.c().d("startAllReconnect", jpt.a("CardDeviceFragment CardDeviceHelper"), 0, "");
                    linearLayout.setVisibility(8);
                }
            };
            cXH_(view, activity, linearLayout, nearbyPermissionAction);
            e(cardDeviceFragment, nearbyPermissionAction);
        }
    }

    private void cXH_(View view, final Activity activity, final LinearLayout linearLayout, final BluetoothPermisionUtils.NearbyPermissionAction nearbyPermissionAction) {
        if (!pep.a("wear_nearby_permission_tip_time")) {
            ReleaseLogUtil.d("R_CardDeviceFragment CardDeviceHelper", "showNearbyPermissionTip < 48h");
            return;
        }
        HealthTextView healthTextView = (HealthTextView) nsy.cMd_(view, R.id.btn_open);
        HealthTextView healthTextView2 = (HealthTextView) nsy.cMd_(view, R.id.btn_cancle);
        ((HealthTextView) nsy.cMd_(view, R.id.device_content)).setText(activity.getResources().getString(R.string._2130846463_res_0x7f0222ff));
        linearLayout.setVisibility(0);
        healthTextView.setOnClickListener(new View.OnClickListener() { // from class: oew
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                oek.cXD_(activity, nearbyPermissionAction, view2);
            }
        });
        healthTextView2.setOnClickListener(new View.OnClickListener() { // from class: oev
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                oek.cXE_(linearLayout, view2);
            }
        });
    }

    static /* synthetic */ void cXD_(Activity activity, BluetoothPermisionUtils.NearbyPermissionAction nearbyPermissionAction, View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
        } else {
            PermissionUtil.b(activity, PermissionUtil.PermissionType.SCAN, nearbyPermissionAction);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    static /* synthetic */ void cXE_(LinearLayout linearLayout, View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        KeyValDbManager.b(BaseApplication.getContext()).e("wear_nearby_permission_tip_time", String.valueOf(System.currentTimeMillis()));
        linearLayout.setVisibility(8);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e(CardDeviceFragment cardDeviceFragment, CustomPermissionAction customPermissionAction) {
        if (!pep.a("wear_nearby_permission_time")) {
            ReleaseLogUtil.d("R_CardDeviceFragment CardDeviceHelper", "tryRequestNearbyPermission < 48h");
        } else if (cardDeviceFragment.s == null) {
            ReleaseLogUtil.d("R_CardDeviceFragment CardDeviceHelper", "tryRequestNearbyPermission mGetDataService == null");
        } else {
            b(cardDeviceFragment, new AnonymousClass9(cardDeviceFragment, customPermissionAction));
        }
    }

    /* renamed from: oek$9, reason: invalid class name */
    class AnonymousClass9 implements Runnable {
        final /* synthetic */ CardDeviceFragment b;
        final /* synthetic */ CustomPermissionAction e;

        AnonymousClass9(CardDeviceFragment cardDeviceFragment, CustomPermissionAction customPermissionAction) {
            this.b = cardDeviceFragment;
            this.e = customPermissionAction;
        }

        @Override // java.lang.Runnable
        public void run() {
            final FragmentActivity activity = this.b.getActivity();
            if (activity == null || BaseApplication.getActivity() != activity) {
                ReleaseLogUtil.d("R_CardDeviceFragment CardDeviceHelper", "tryRequestNearbyPermission activity not top Activity");
                return;
            }
            CustomTextAlertDialog.Builder cyR_ = new CustomTextAlertDialog.Builder(activity).b(R.string._2130842089_res_0x7f0211e9).e(activity.getString(R.string._2130847253_res_0x7f022615)).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: oez
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    oek.AnonymousClass9.cXR_(view);
                }
            });
            final CustomPermissionAction customPermissionAction = this.e;
            cyR_.cyU_(R.string._2130846701_res_0x7f0223ed, new View.OnClickListener() { // from class: oex
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    oek.AnonymousClass9.cXS_(activity, customPermissionAction, view);
                }
            }).a().show();
        }

        static /* synthetic */ void cXR_(View view) {
            KeyValDbManager.b(BaseApplication.getContext()).e("wear_nearby_permission_time", String.valueOf(System.currentTimeMillis()));
            ViewClickInstrumentation.clickOnView(view);
        }

        static /* synthetic */ void cXS_(Activity activity, CustomPermissionAction customPermissionAction, View view) {
            PermissionUtil.b(activity, PermissionUtil.PermissionType.SCAN, customPermissionAction);
            KeyValDbManager.b(BaseApplication.getContext()).e("wear_nearby_permission_time", String.valueOf(System.currentTimeMillis()));
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void b(final CardDeviceFragment cardDeviceFragment, final Runnable runnable) {
        if (cardDeviceFragment.s == null) {
            ReleaseLogUtil.d("R_CardDeviceFragment CardDeviceHelper", "tryRequestNearbyPermission mGetDataService is null");
        } else {
            cardDeviceFragment.s.execute(new Runnable() { // from class: oek.10
                @Override // java.lang.Runnable
                public void run() {
                    if (!DeviceUtil.a()) {
                        ReleaseLogUtil.d("R_CardDeviceFragment CardDeviceHelper", "tryRequestNearbyPermission not hasDeviceDbInfo");
                        return;
                    }
                    Handler handler = cardDeviceFragment.w;
                    if (handler == null) {
                        ReleaseLogUtil.d("R_CardDeviceFragment CardDeviceHelper", "tryRequestNearbyPermission handler == null");
                    } else {
                        handler.post(runnable);
                    }
                }
            });
        }
    }

    public void cXJ_(final CardDeviceFragment cardDeviceFragment, View view) {
        if (view == null || cardDeviceFragment == null) {
            LogUtil.a("CardDeviceFragment CardDeviceHelper", "initDeviceAutoOtaTip deviceCardView or fragment is null");
            return;
        }
        cardDeviceFragment.h = (LinearLayout) nsy.cMd_(view, R.id.auto_ota_top_layout);
        HealthTextView healthTextView = (HealthTextView) nsy.cMd_(view, R.id.btn_update_top_open);
        HealthTextView healthTextView2 = (HealthTextView) nsy.cMd_(view, R.id.btn_update_top_cancle);
        healthTextView.setOnClickListener(new View.OnClickListener() { // from class: oel
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                oek.cXu_(CardDeviceFragment.this, view2);
            }
        });
        healthTextView2.setOnClickListener(new View.OnClickListener() { // from class: oep
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                oek.cXv_(CardDeviceFragment.this, view2);
            }
        });
    }

    static /* synthetic */ void cXu_(CardDeviceFragment cardDeviceFragment, View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        oau.d("1");
        cardDeviceFragment.h.setVisibility(8);
        ogj.l();
        nrh.e(cardDeviceFragment.getActivity(), R.string._2130842836_res_0x7f0214d4);
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void cXv_(CardDeviceFragment cardDeviceFragment, View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        oau.d("0");
        cardDeviceFragment.h.setVisibility(8);
        ogj.p();
        ViewClickInstrumentation.clickOnView(view);
    }

    void cXK_(final CardDeviceFragment cardDeviceFragment, View view) {
        if (view == null || cardDeviceFragment == null) {
            LogUtil.h("CardDeviceFragment CardDeviceHelper", "initDeviceAutoSwitchTip deviceCardView or fragment is null");
            return;
        }
        if (cardDeviceFragment.h.getVisibility() == 0) {
            LogUtil.h("CardDeviceFragment CardDeviceHelper", "initDeviceAutoSwitchTip mAutoOtaTipLayout VISIBLE");
            return;
        }
        DeviceInfo a2 = jpt.a("CardDeviceFragment CardDeviceHelper");
        if (a2 == null || a2.getDeviceConnectState() != 2) {
            LogUtil.h("CardDeviceFragment CardDeviceHelper", "initDeviceAutoSwitchTip !DeviceConnectState.DEVICE_CONNECTED");
            return;
        }
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10024), "firstReportSwitch");
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10024), "firstReportSwitchDeviceName");
        if (!TextUtils.equals(b, "1") || !TextUtils.equals(b2, a2.getDeviceName())) {
            LogUtil.h("CardDeviceFragment CardDeviceHelper", "initDeviceAutoSwitchTip not first switch");
            return;
        }
        cardDeviceFragment.f.setVisibility(0);
        HealthTextView healthTextView = (HealthTextView) nsy.cMd_(view, R.id.btn_know);
        ((HealthTextView) nsy.cMd_(view, R.id.auto_switch_tip)).setText(String.format(Locale.ROOT, cardDeviceFragment.getActivity().getResources().getString(R.string.IDS_device_auto_switch_tips), b2));
        healthTextView.setOnClickListener(new View.OnClickListener() { // from class: oeh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                oek.cXw_(CardDeviceFragment.this, view2);
            }
        });
    }

    static /* synthetic */ void cXw_(CardDeviceFragment cardDeviceFragment, View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10024), "firstReportSwitch", "2", new StorageParams());
        cardDeviceFragment.f.setVisibility(8);
        if (SharedPreferenceManager.a(String.valueOf(10024), "auto_ota_tip_status", false) && BluetoothPermisionUtils.e(cardDeviceFragment.g)) {
            cardDeviceFragment.h.setVisibility(0);
            SharedPreferenceManager.e(String.valueOf(10024), "auto_ota_tip_status", false);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public void cXM_(final CardDeviceFragment cardDeviceFragment, View view) {
        if (view == null || cardDeviceFragment == null) {
            LogUtil.a("CardDeviceFragment CardDeviceHelper", "initDeviceOtherView deviceCardView or fragment is null");
            return;
        }
        cardDeviceFragment.ag.setOnClickListener(new View.OnClickListener() { // from class: oej
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                oek.cXx_(CardDeviceFragment.this, view2);
            }
        });
        cardDeviceFragment.c.setOnClickListener(new View.OnClickListener() { // from class: oet
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                oek.cXy_(CardDeviceFragment.this, view2);
            }
        });
        ImageView imageView = (ImageView) nsy.cMd_(view, R.id.device_card_normal_img);
        if (LanguageUtil.bc(cardDeviceFragment.getActivity())) {
            imageView.setBackgroundResource(R.mipmap._2131821443_res_0x7f110383);
        } else {
            imageView.setBackgroundResource(R.mipmap._2131821442_res_0x7f110382);
        }
        cardDeviceFragment.k.setOnClickListener(new View.OnClickListener() { // from class: oey
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                oek.cXz_(CardDeviceFragment.this, view2);
            }
        });
        i(cardDeviceFragment);
    }

    static /* synthetic */ void cXx_(CardDeviceFragment cardDeviceFragment, View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (!Utils.o()) {
            Intent intent = new Intent();
            intent.setClass(cardDeviceFragment.getActivity(), DeviceMoreListActivity.class);
            ogj.cZC_(cardDeviceFragment.getActivity(), intent, "DeviceMoreListActivity");
            nsn.ai(cardDeviceFragment.g);
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        cardDeviceFragment.z = !cardDeviceFragment.z;
        cardDeviceFragment.h();
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void cXy_(CardDeviceFragment cardDeviceFragment, View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
        } else {
            obb.c(cardDeviceFragment);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    static /* synthetic */ void cXz_(CardDeviceFragment cardDeviceFragment, View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
        } else {
            obb.c(cardDeviceFragment);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void i(final CardDeviceFragment cardDeviceFragment) {
        cardDeviceFragment.j = new CardDeviceAdapter(cardDeviceFragment.getActivity(), cardDeviceFragment, new CardDeviceAdapter.PersonalItemReconnectListener() { // from class: oek.6
            @Override // com.huawei.ui.homehealth.device.adapter.CardDeviceAdapter.PersonalItemReconnectListener
            public void onReconnect(cpm cpmVar) {
                LogUtil.a("CardDeviceFragment CardDeviceHelper", "setRecyclerView onReconnect");
                if (cardDeviceFragment.getActivity() == null) {
                    ReleaseLogUtil.d("R_CardDeviceFragment CardDeviceHelper", "onReconnect getActivity() == null");
                } else {
                    oau.c(100108, cpmVar.d());
                    oek.this.b(cardDeviceFragment, cpmVar);
                }
            }

            @Override // com.huawei.ui.homehealth.device.adapter.CardDeviceAdapter.PersonalItemReconnectListener
            public void onPersonalEquipment(int i, View view) {
                LogUtil.a("CardDeviceFragment CardDeviceHelper", "setRecyclerView onPersonalEquipment");
                oek.this.cXt_(cardDeviceFragment, i, view);
            }

            @Override // com.huawei.ui.homehealth.device.adapter.CardDeviceAdapter.PersonalItemReconnectListener
            public void onMoreEquipment() {
                LogUtil.a("CardDeviceFragment CardDeviceHelper", "setRecyclerView onMoreEquipment");
                if (nsn.o()) {
                    return;
                }
                obb.c(cardDeviceFragment);
            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(cardDeviceFragment.getActivity(), 2, 1, false) { // from class: oek.8
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return false;
            }
        };
        cardDeviceFragment.aw.addItemDecoration(new DeviceItemDecoration(nrr.e(cardDeviceFragment.getActivity(), 12.0f), 0));
        cardDeviceFragment.aw.setLayoutManager(gridLayoutManager);
        cardDeviceFragment.j.c(cardDeviceFragment.au);
        cardDeviceFragment.aw.setAdapter(cardDeviceFragment.j);
        h(cardDeviceFragment);
    }

    private void h(final CardDeviceFragment cardDeviceFragment) {
        cardDeviceFragment.aq.addItemDecoration(new RecommendItemDecoration(nrr.e(cardDeviceFragment.getActivity(), 12.0f), nrr.e(cardDeviceFragment.getActivity(), 0.0f)));
        cardDeviceFragment.al = new DeviceRecommendedAdapter(cardDeviceFragment.getActivity());
        cardDeviceFragment.al.b(cardDeviceFragment.as);
        cardDeviceFragment.aq.setAdapter(cardDeviceFragment.al);
        cardDeviceFragment.ap.setAdapter(cardDeviceFragment.al);
        cardDeviceFragment.al.e(new DeviceRecommendedAdapter.RecommendListener() { // from class: oek.7
            @Override // com.huawei.ui.homehealth.device.adapter.DeviceRecommendedAdapter.RecommendListener
            public void onPersonalEquipment(RecommendedItem recommendedItem) {
                oek.this.a(cardDeviceFragment, recommendedItem);
            }
        });
        cardDeviceFragment.g();
    }

    public void b(CardDeviceFragment cardDeviceFragment) {
        if (cardDeviceFragment == null) {
            LogUtil.h("CardDeviceFragment CardDeviceHelper", "showRecommendedView fragment is null");
            return;
        }
        if (cardDeviceFragment.as.size() == 0) {
            cardDeviceFragment.ar.setVisibility(8);
            cardDeviceFragment.az.setVisibility(8);
            cardDeviceFragment.r.setVisibility(8);
            cardDeviceFragment.m.setVisibility(8);
            return;
        }
        if (!Utils.o()) {
            j(cardDeviceFragment);
            return;
        }
        cardDeviceFragment.az.setVisibility(8);
        cardDeviceFragment.ar.setVisibility(0);
        cardDeviceFragment.r.setVisibility(8);
        cardDeviceFragment.m.setVisibility(8);
    }

    private void j(CardDeviceFragment cardDeviceFragment) {
        if (cardDeviceFragment.af) {
            cardDeviceFragment.m.setVisibility(0);
            cardDeviceFragment.r.setVisibility(0);
            cardDeviceFragment.az.setVisibility(8);
            cardDeviceFragment.ar.setVisibility(8);
            return;
        }
        cardDeviceFragment.r.setVisibility(8);
        cardDeviceFragment.m.setVisibility(8);
        if (cardDeviceFragment.as.size() > 2) {
            cardDeviceFragment.az.setVisibility(0);
            cardDeviceFragment.ar.setVisibility(8);
        } else {
            cardDeviceFragment.az.setVisibility(8);
            cardDeviceFragment.ar.setVisibility(0);
        }
    }

    public void a(CardDeviceFragment cardDeviceFragment, boolean z) {
        LogUtil.a("CardDeviceFragment CardDeviceHelper", "showCardNormal enter");
        if (cardDeviceFragment == null || cardDeviceFragment.ak.size() > 0) {
            LogUtil.h("CardDeviceFragment CardDeviceHelper", "showCardNormal fragment is null or mProductInfoLists.size() > 0");
            return;
        }
        LinearLayout.LayoutParams layoutParams = cardDeviceFragment.k.getLayoutParams() instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) cardDeviceFragment.k.getLayoutParams() : null;
        if (z && layoutParams != null) {
            cardDeviceFragment.aj.setVisibility(0);
            layoutParams.bottomMargin = 0;
            layoutParams.height = nsn.c(cardDeviceFragment.g, 112.0f);
        }
        if (layoutParams != null) {
            cardDeviceFragment.k.setLayoutParams(layoutParams);
        }
        cardDeviceFragment.k.setVisibility(0);
        cardDeviceFragment.am.setVisibility(8);
        cardDeviceFragment.ar.setVisibility(8);
        cardDeviceFragment.i.setVisibility(8);
        cardDeviceFragment.az.setVisibility(8);
        cardDeviceFragment.r.setVisibility(8);
        cardDeviceFragment.m.setVisibility(8);
    }

    public void e(CardDeviceFragment cardDeviceFragment) {
        if (cardDeviceFragment == null) {
            LogUtil.h("CardDeviceFragment CardDeviceHelper", "setRecommendationCard fragment is null");
            return;
        }
        if (!Utils.o()) {
            if (cardDeviceFragment.as.size() > 2) {
                if (nsn.r() && cardDeviceFragment.as.size() > 3 && !nsn.ag(cardDeviceFragment.getActivity())) {
                    cardDeviceFragment.ap.setLayoutManager(ogj.e());
                    return;
                } else {
                    cardDeviceFragment.ap.setLayoutManager(ogj.d(cardDeviceFragment.as.size()));
                    return;
                }
            }
            cardDeviceFragment.aq.setLayoutManager(ogj.b(cardDeviceFragment.as));
            return;
        }
        cardDeviceFragment.aq.setLayoutManager(ogj.b(cardDeviceFragment.as));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cXt_(CardDeviceFragment cardDeviceFragment, int i, View view) {
        if (nsn.o()) {
            return;
        }
        if (!obb.e(cardDeviceFragment.getActivity()) && (cardDeviceFragment.getActivity() instanceof Activity)) {
            obb.cTO_(cardDeviceFragment.getActivity());
            return;
        }
        if (i < 0 || i >= cardDeviceFragment.ak.size()) {
            return;
        }
        cjv cjvVar = new cjv();
        if (cardDeviceFragment.ak.get(i) instanceof cjv) {
            cjvVar = cardDeviceFragment.ak.get(i);
        }
        if (ogq.b(cardDeviceFragment.getActivity(), cjvVar)) {
            ogq.c cVar = new ogq.c();
            cVar.b(cjvVar);
            cVar.e(new RecommendedItem());
            cVar.c(i);
            cVar.b(1);
            ogq.b(cardDeviceFragment.getActivity(), cVar, cardDeviceFragment.ad);
            return;
        }
        cXs_(cardDeviceFragment, i, view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(CardDeviceFragment cardDeviceFragment, RecommendedItem recommendedItem) {
        String str;
        if (nsn.o()) {
            return;
        }
        int id = recommendedItem.getId();
        if (id == 5) {
            if (TextUtils.isEmpty(cardDeviceFragment.v)) {
                str = jah.c().e("domain_play_machine") + "/hwtips/app/health_app/zh-CN/index.html#/app?cid=11068";
            } else {
                str = cardDeviceFragment.v;
            }
            LogUtil.h("CardDeviceFragment CardDeviceHelper", "personalEquipmentMethod httpUrl is ", str);
            Intent intent = new Intent();
            intent.setClass(cardDeviceFragment.getActivity(), WebViewActivity.class);
            intent.putExtra("url", str);
            ogj.cZC_(cardDeviceFragment.getActivity(), intent, "WebViewActivity");
            cardDeviceFragment.getActivity().overridePendingTransition(0, R.anim._2130771980_res_0x7f01000c);
            return;
        }
        if (!cardDeviceFragment.ac && id != 1 && id != 6) {
            ogj.e(cardDeviceFragment.getActivity());
            return;
        }
        if (ogj.d() && !WatchFaceProvider.BACKGROUND_LABEL.equals(jsc.d(cardDeviceFragment.getActivity()))) {
            LogUtil.h("CardDeviceFragment CardDeviceHelper", "not back update");
            ogj.c(cardDeviceFragment.getActivity(), R.string.IDS_device_ota_later_note);
            return;
        }
        cjv e = ogq.e(cardDeviceFragment.ak, recommendedItem);
        if (ogq.b(cardDeviceFragment.getActivity(), e)) {
            ogq.c cVar = new ogq.c();
            cVar.b(e);
            cVar.e(recommendedItem);
            cVar.c(-1);
            cVar.b(5);
            ogq.b(cardDeviceFragment.getActivity(), cVar, cardDeviceFragment.ad);
            return;
        }
        c(cardDeviceFragment, id);
    }

    private void c(CardDeviceFragment cardDeviceFragment, int i) {
        if (i == 1) {
            ogj.cZE_(cardDeviceFragment.getActivity());
            return;
        }
        if (i == 2) {
            try {
                oau.d();
                Intent intent = new Intent();
                intent.putExtra("device_id", cardDeviceFragment.p);
                intent.setClass(cardDeviceFragment.getActivity(), MusicSecondaryMenuActivity.class);
                ogj.cZC_(cardDeviceFragment.getActivity(), intent, "MusicSecondaryMenuActivity");
                cardDeviceFragment.getActivity().overridePendingTransition(R.anim._2130771980_res_0x7f01000c, R.anim._2130771980_res_0x7f01000c);
                return;
            } catch (ActivityNotFoundException unused) {
                LogUtil.b("CardDeviceFragment CardDeviceHelper", "personalEquipmentMethodDetails ActivityNotFoundException");
                return;
            }
        }
        if (i == 3) {
            ogj.e(cardDeviceFragment.getActivity(), cardDeviceFragment.p);
            return;
        }
        if (i == 4) {
            l(cardDeviceFragment);
        } else if (i == 6) {
            ogj.o();
        } else {
            LogUtil.h("CardDeviceFragment CardDeviceHelper", "id is not exist");
        }
    }

    private void cXs_(CardDeviceFragment cardDeviceFragment, int i, View view) {
        if (cardDeviceFragment.ak.get(i) instanceof cjv) {
            cjv cjvVar = cardDeviceFragment.ak.get(i);
            boolean a2 = cardDeviceFragment.j.a();
            if (cjvVar.a() == 0) {
                LogUtil.a("CardDeviceFragment CardDeviceHelper", "DeviceManagerCardNoDeviceValueViewHolder onItemClick postion ", Integer.valueOf(i));
                cjvVar.FT_();
                a(i, cardDeviceFragment, cjvVar);
                return;
            }
            if (cjvVar.a() == 1) {
                e(cardDeviceFragment, cjvVar, a2);
                return;
            }
            if (cjvVar.a() == 3) {
                cardDeviceFragment.startActivity(cww.QP_(cjvVar));
                return;
            }
            if (cjvVar.a() == 4) {
                Bundle bundle = view != null ? ActivityOptionsCompat.makeSceneTransitionAnimation(cardDeviceFragment.getActivity(), Pair.create(view, TitleBean.RIGHT_BTN_TYPE_SHARE)).toBundle() : null;
                Intent intent = new Intent();
                intent.putExtra("common_device_name", cjvVar.e());
                intent.setClass(cardDeviceFragment.getActivity(), MoreSportDeviceActivity.class);
                try {
                    if (bundle != null) {
                        cardDeviceFragment.startActivity(intent, bundle);
                    } else {
                        cardDeviceFragment.startActivity(intent);
                    }
                    return;
                } catch (ActivityNotFoundException unused) {
                    LogUtil.b("CardDeviceFragment CardDeviceHelper", "MoreSportDeviceActivity ActivityNotFoundException");
                    return;
                }
            }
            if (cjvVar.a() == 6) {
                String asString = cjvVar.FT_().getAsString("productId");
                String asString2 = cjvVar.FT_().getAsString("mac");
                Intent intent2 = new Intent(cardDeviceFragment.getActivity(), (Class<?>) DeviceMainActivity.class);
                intent2.putExtra("PID_FROM_QRCODE", "ZAA6");
                intent2.putExtra("productId", asString);
                intent2.putExtra("Device_Type", "082");
                intent2.putExtra("macAddress", asString2);
                if (cardDeviceFragment.getActivity() != null) {
                    cardDeviceFragment.getActivity().startActivity(intent2);
                    return;
                }
                return;
            }
            LogUtil.h("CardDeviceFragment CardDeviceHelper", "other device");
        }
    }

    private void a(int i, final CardDeviceFragment cardDeviceFragment, cjv cjvVar) {
        LogUtil.a("CardDeviceFragment CardDeviceHelper", "startThirdPartAction");
        final dcz dczVar = (dcz) cjvVar.i();
        ContentValues FT_ = cjvVar.FT_();
        if (dczVar.e().size() <= 0) {
            cXI_(cardDeviceFragment, i, dczVar, FT_);
            return;
        }
        if (BleConstants.BLE_THIRD_DEVICE_H5.equals(dczVar.m().d())) {
            final String asString = FT_.getAsString("uniqueId");
            final String t = dczVar.t();
            PermissionDialogHelper.Vx_(cardDeviceFragment.getActivity(), new PermissionDialogHelper.OpenBlueToothAction() { // from class: oek.11
                @Override // com.huawei.health.ecologydevice.ui.util.PermissionDialogHelper.OpenBlueToothAction
                public void onPermissionGranted() {
                    if (cardDeviceFragment.getActivity() == null) {
                        LogUtil.h("CardDeviceFragment CardDeviceHelper", "startThirdPartAction onPermissionGranted activity is null");
                    } else if ("1".equals(dczVar.j())) {
                        LogUtil.a("CardDeviceFragment CardDeviceHelper", "startThirdPartAction onPermissionGranted switchToH5ProIntro");
                        dks.d(cardDeviceFragment.getActivity(), dczVar, t, asString);
                    } else {
                        LogUtil.a("CardDeviceFragment CardDeviceHelper", "startThirdPartAction onPermissionGranted startWebViewActivity");
                        ogj.c(cardDeviceFragment.getActivity(), t, asString, dczVar.m().d());
                    }
                }

                @Override // com.huawei.health.ecologydevice.ui.util.PermissionDialogHelper.OpenBlueToothAction
                public void onPermissionDenied() {
                    LogUtil.a("CardDeviceFragment CardDeviceHelper", "Permission onPermissionDenied");
                }
            });
        } else if ("4bfc5a27-f2b9-4c41-bd9b-a4a2a18f752c".equals(cjvVar.FT_().getAsString("productId"))) {
            LogUtil.a("CardDeviceFragment CardDeviceHelper", "enter enmo");
            cXr_(cardDeviceFragment, FT_);
        } else {
            ogj.cZF_(cardDeviceFragment.getActivity(), FT_, dczVar);
        }
    }

    private void cXr_(CardDeviceFragment cardDeviceFragment, ContentValues contentValues) {
        LogUtil.a("CardDeviceFragment CardDeviceHelper", "enter dealJumpNemoActivity...");
        if (cardDeviceFragment.getActivity() == null || contentValues == null) {
            LogUtil.h("CardDeviceFragment CardDeviceHelper", "activity or deviceInfo is null");
            return;
        }
        String asString = contentValues.getAsString("uniqueId");
        if (dij.Vb_(contentValues)) {
            Intent intent = new Intent(cardDeviceFragment.getActivity(), (Class<?>) DeviceMainActivity.class);
            intent.putExtra("PID_FROM_QRCODE", "ZAA6");
            intent.putExtra("productId", contentValues.getAsString("productId"));
            intent.putExtra("Device_Type", "082");
            intent.putExtra("macAddress", asString);
            cardDeviceFragment.getActivity().startActivity(intent);
            return;
        }
        LogUtil.a("CardDeviceFragment CardDeviceHelper", "enter enmo");
        new dcp().d(cardDeviceFragment.getActivity(), asString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(CardDeviceFragment cardDeviceFragment, cpm cpmVar) {
        cjv cjvVar = new cjv();
        cjvVar.a(1);
        cjvVar.c(cpmVar);
        if (ogq.b(cardDeviceFragment.getActivity(), cjvVar)) {
            ogq.c cVar = new ogq.c();
            cVar.b(cjvVar);
            cVar.e(new RecommendedItem());
            cVar.c(-1);
            cVar.b(2);
            ogq.b(cardDeviceFragment.getActivity(), cVar, cardDeviceFragment.ad);
            return;
        }
        e(cardDeviceFragment, cpmVar);
    }

    private void e(CardDeviceFragment cardDeviceFragment, cpm cpmVar) {
        oaf.b(cardDeviceFragment.getActivity()).h(cpmVar.a());
        c(cardDeviceFragment, cpmVar, 0);
    }

    void c(final CardDeviceFragment cardDeviceFragment, cpm cpmVar, int i) {
        if (jkj.d().j()) {
            LogUtil.a("CardDeviceFragment CardDeviceHelper", "user choose connect other wear device is OTAing");
            ogj.c(cardDeviceFragment.getActivity(), R.string.IDS_main_device_ota_error_message);
            return;
        }
        final List<DeviceInfo> b = jfv.b();
        ogj.b(cpmVar, b);
        String a2 = cpmVar.a();
        if (cpmVar.g()) {
            obi.a().cUG_(cpmVar.i(), cpmVar.d(), cardDeviceFragment.getActivity(), cardDeviceFragment.t);
            a2 = cpmVar.i() + Constants.LINK + cpmVar.a();
        } else if (cardDeviceFragment.b != null) {
            cardDeviceFragment.b.c();
        }
        final String str = a2;
        if (cpmVar != null) {
            SharedPreferenceManager.c(String.valueOf(10), "app_big_data_device_name", cpmVar.d());
        }
        final boolean g = cpmVar.g();
        final String c2 = cpmVar.c();
        cardDeviceFragment.s.execute(new Runnable() { // from class: oek.13
            @Override // java.lang.Runnable
            public void run() {
                jfv.a(b, str);
                new obq().d(cardDeviceFragment.b, c2, g, str, cardDeviceFragment.getActivity());
            }
        });
        cardDeviceFragment.w.sendMessageDelayed(ogj.cZA_(g, cpmVar, i), 20000L);
        LogUtil.a("CardDeviceFragment CardDeviceHelper", "device connect time out");
    }

    private void l(final CardDeviceFragment cardDeviceFragment) {
        if (jiw.a().f()) {
            LogUtil.h("CardDeviceFragment CardDeviceHelper", "startAppMarket isDeviceVersionNotSupport");
            m(cardDeviceFragment);
        } else {
            jiw.a().e(cardDeviceFragment.getActivity(), new AppBundleLauncher.InstallCallback() { // from class: oek.4
                @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
                public boolean call(Context context, Intent intent) {
                    jiw.a().j();
                    if (cwi.c(jpt.a("CardDeviceFragment CardDeviceHelper"), 44)) {
                        try {
                            Intent intent2 = new Intent();
                            intent2.setClass(cardDeviceFragment.getActivity(), HwSmartAppMarketLoadingActivity.class);
                            cardDeviceFragment.getActivity().startActivity(intent2);
                            return false;
                        } catch (ActivityNotFoundException unused) {
                            LogUtil.b("CardDeviceFragment CardDeviceHelper", "startAppMarket NotFoundException");
                            return false;
                        }
                    }
                    jiw.a().g();
                    return false;
                }
            });
            oau.a();
        }
    }

    public void g(CardDeviceFragment cardDeviceFragment) {
        if (cardDeviceFragment == null) {
            LogUtil.h("CardDeviceFragment CardDeviceHelper", "updateMarketingCard fragment is null");
            return;
        }
        if (cardDeviceFragment.u == null || koq.b(cardDeviceFragment.u.getGridContents())) {
            return;
        }
        List<SingleGridContent> gridContents = cardDeviceFragment.u.getGridContents();
        ArrayList arrayList = new ArrayList();
        ColumnLinearLayout columnLinearLayout = new ColumnLinearLayout(BaseApplication.getContext());
        b(cardDeviceFragment, columnLinearLayout, gridContents, arrayList);
        if (!koq.b(cardDeviceFragment.f9368a, arrayList, false)) {
            cardDeviceFragment.u.setGridContents(arrayList);
            columnLinearLayout.e(4050, cardDeviceFragment.ax, cardDeviceFragment.u);
            cardDeviceFragment.r.removeAllViews();
            cardDeviceFragment.r.addView(columnLinearLayout);
            cardDeviceFragment.u.setGridContents(gridContents);
            cardDeviceFragment.f9368a.clear();
            cardDeviceFragment.f9368a.addAll(arrayList);
        }
        cardDeviceFragment.af = true;
    }

    private void b(final CardDeviceFragment cardDeviceFragment, final ColumnLinearLayout columnLinearLayout, List<SingleGridContent> list, List<SingleGridContent> list2) {
        for (RecommendedItem recommendedItem : cardDeviceFragment.as) {
            if ((recommendedItem instanceof RecommendedItem ? recommendedItem : null) != null && koq.d(list, r1.getId() - 1)) {
                list2.add(list.get(r1.getId() - 1));
            }
        }
        c(cardDeviceFragment, list2);
        LogUtil.h("CardDeviceFragment CardDeviceHelper", "setMarketingCard template is ", list2);
        columnLinearLayout.setItemClickListener(new View.OnClickListener() { // from class: oes
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                oek.cXA_(CardDeviceFragment.this, columnLinearLayout, view);
            }
        });
    }

    static /* synthetic */ void cXA_(CardDeviceFragment cardDeviceFragment, ColumnLinearLayout columnLinearLayout, View view) {
        if (!cardDeviceFragment.ac) {
            ogj.e(cardDeviceFragment.getActivity());
            columnLinearLayout.setShowDialogFlag(true);
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (ogj.d() && !WatchFaceProvider.BACKGROUND_LABEL.equals(jsc.d(cardDeviceFragment.getActivity()))) {
            LogUtil.h("CardDeviceFragment CardDeviceHelper", "not back update");
            ogj.c(cardDeviceFragment.getActivity(), R.string.IDS_device_ota_later_note);
            columnLinearLayout.setShowDialogFlag(true);
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (cardDeviceFragment.as.get(0) instanceof RecommendedItem) {
            RecommendedItem recommendedItem = cardDeviceFragment.as.get(0);
            cjv e = ogq.e(cardDeviceFragment.ak, recommendedItem);
            if (ogq.b(cardDeviceFragment.getActivity(), e)) {
                ogq.c cVar = new ogq.c();
                cVar.b(e);
                cVar.e(recommendedItem);
                cVar.c(-1);
                cVar.b(5);
                ogq.b(cardDeviceFragment.getActivity(), cVar, cardDeviceFragment.ad);
                columnLinearLayout.setShowDialogFlag(true);
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
        }
        columnLinearLayout.setShowDialogFlag(false);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void c(CardDeviceFragment cardDeviceFragment, List<SingleGridContent> list) {
        if (list == null || TextUtils.isEmpty(cardDeviceFragment.v)) {
            LogUtil.h("CardDeviceFragment CardDeviceHelper", "updatePlayMachineUrl content or helpUrl is null");
            return;
        }
        for (SingleGridContent singleGridContent : list) {
            if (singleGridContent != null) {
                try {
                    if (CommonUtils.h(Uri.parse(nsa.g(singleGridContent.getLinkValue())).getQueryParameter("deviceMgrType")) == 7) {
                        singleGridContent.setLinkValue(cardDeviceFragment.v);
                    }
                } catch (IllegalArgumentException | UnsupportedOperationException e) {
                    LogUtil.b("CardDeviceFragment CardDeviceHelper", "get type IllegalArgumentException:", LogAnonymous.b(e));
                }
            }
        }
        LogUtil.h("CardDeviceFragment CardDeviceHelper", "updateHelpGuideUrl helpUrl is", cardDeviceFragment.v);
    }

    private void cXI_(final CardDeviceFragment cardDeviceFragment, final int i, final dcz dczVar, final ContentValues contentValues) {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(cardDeviceFragment.getActivity());
        builder.e(R.string.IDS_device_selection_cancel_unbind_device);
        builder.czC_(R.string.IDS_device_ui_dialog_yes, new View.OnClickListener() { // from class: oen
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                oek.cXG_(contentValues, dczVar, cardDeviceFragment, i, view);
            }
        });
        builder.czz_(R.string.IDS_device_ui_dialog_no, new View.OnClickListener() { // from class: oeo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                oek.cXF_(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        e.setCancelable(false);
        e.show();
    }

    static /* synthetic */ void cXG_(ContentValues contentValues, dcz dczVar, CardDeviceFragment cardDeviceFragment, int i, View view) {
        if (contentValues == null || dczVar == null) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        String asString = contentValues.getAsString("uniqueId");
        if (cjx.e().o(asString)) {
            cjx.e().d(dczVar.t(), asString);
            cardDeviceFragment.ak.remove(i);
            cardDeviceFragment.j.notifyDataSetChanged();
            Intent intent = new Intent();
            intent.setAction("com.huawei.bone.action.DEVICE_THIRD_DELETE");
            cardDeviceFragment.getActivity().sendBroadcast(intent, LocalBroadcast.c);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void cXF_(View view) {
        LogUtil.c("CardDeviceFragment CardDeviceHelper", "onClick");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void m(final CardDeviceFragment cardDeviceFragment) {
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(cardDeviceFragment.getActivity()).b(R.string._2130839506_res_0x7f0207d2).d(R.string.IDS_app_market_device_update).cyR_(R.string._2130841855_res_0x7f0210ff, new View.OnClickListener() { // from class: oeq
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                oek.this.cXP_(view);
            }
        }).cyU_(R.string._2130841856_res_0x7f021100, new View.OnClickListener() { // from class: oeu
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                oek.cXC_(CardDeviceFragment.this, view);
            }
        }).a();
        this.b = a2;
        a2.setCancelable(false);
        if (this.b.isShowing() || cardDeviceFragment.getActivity().isFinishing()) {
            return;
        }
        this.b.show();
    }

    /* synthetic */ void cXP_(View view) {
        CustomTextAlertDialog customTextAlertDialog = this.b;
        if (customTextAlertDialog != null) {
            customTextAlertDialog.dismiss();
            this.b = null;
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void cXC_(CardDeviceFragment cardDeviceFragment, View view) {
        ogj.cZK_(cardDeviceFragment.getActivity(), cardDeviceFragment.getActivity(), cardDeviceFragment.s);
        ViewClickInstrumentation.clickOnView(view);
    }

    public void d(CardDeviceFragment cardDeviceFragment) {
        LogUtil.a("CardDeviceFragment CardDeviceHelper", "showBandUnavailableDialog");
        if (cardDeviceFragment == null) {
            LogUtil.h("CardDeviceFragment CardDeviceHelper", "showBandUnavailableDialog fragment is null");
            return;
        }
        if (CommonUtil.h(cardDeviceFragment.getActivity(), "com.huawei.health.MainActivity")) {
            CustomTextAlertDialog customTextAlertDialog = this.g;
            if (customTextAlertDialog != null && customTextAlertDialog.isShowing()) {
                LogUtil.a("CardDeviceFragment CardDeviceHelper", "showBandUnavailableDialog Already show!");
                return;
            }
            CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(cardDeviceFragment.getActivity()).b(R.string.IDS_service_area_notice_title).e(cardDeviceFragment.getActivity().getString(R.string._2130842667_res_0x7f02142b)).cyU_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: oem
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    oek.cXB_(view);
                }
            }).a();
            this.g = a2;
            a2.setCancelable(false);
            if (this.g.isShowing() || cardDeviceFragment.getActivity().isFinishing()) {
                return;
            }
            this.g.show();
        }
    }

    static /* synthetic */ void cXB_(View view) {
        LogUtil.c("CardDeviceFragment CardDeviceHelper", "onClick");
        ViewClickInstrumentation.clickOnView(view);
    }

    public void a(CardDeviceFragment cardDeviceFragment) {
        if (cardDeviceFragment == null || CommonUtil.h(cardDeviceFragment.getActivity(), "com.huawei.ui.device.activity.pairing.DevicePairGuideActivity")) {
            LogUtil.h("CardDeviceFragment CardDeviceHelper", "accountInconsistentAlertDialog fragment is null");
            return;
        }
        if (this.f15635a == null) {
            CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(cardDeviceFragment.getActivity()).b(cardDeviceFragment.getActivity().getResources().getString(R.string._2130843841_res_0x7f0218c1)).e(cardDeviceFragment.getActivity().getResources().getString(R.string._2130843842_res_0x7f0218c2)).cyR_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: oer
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    oek.this.cXO_(view);
                }
            }).a();
            this.f15635a = a2;
            a2.setCancelable(false);
            if (this.f15635a.isShowing() || cardDeviceFragment.getActivity().isFinishing()) {
                return;
            }
            this.f15635a.show();
        }
    }

    /* synthetic */ void cXO_(View view) {
        LogUtil.a("CardDeviceFragment CardDeviceHelper", "user indicates know");
        this.f15635a.dismiss();
        this.f15635a = null;
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e(CardDeviceFragment cardDeviceFragment, cjv cjvVar, boolean z) {
        LogUtil.a("CardDeviceFragment CardDeviceHelper", "onItemClick wear");
        if (cjvVar.i() instanceof cpm) {
            cpm cpmVar = (cpm) cjvVar.i();
            if (z && cardDeviceFragment.ao != null && !cardDeviceFragment.ao.equals(cpmVar.a()) && cpmVar.e() != 2) {
                LogUtil.a("CardDeviceFragment CardDeviceHelper", "other devices is connection,can not start activity.");
                return;
            }
            if (cpmVar.e() == 2) {
                ogj.c(cardDeviceFragment.getActivity(), cpmVar.a());
            } else if (cpmVar.g()) {
                oau.d(4);
                ogj.d(cardDeviceFragment.getActivity(), cpmVar, cjvVar.j());
            } else {
                ogj.c(cardDeviceFragment.getActivity(), cpmVar.a());
            }
        }
    }

    public void f(CardDeviceFragment cardDeviceFragment) {
        if (cardDeviceFragment == null || !CommonUtil.ce()) {
            return;
        }
        jfr.b("CardDeviceFragment CardDeviceHelper");
        ogj.cZL_(cardDeviceFragment.l);
        ogj.cZL_(cardDeviceFragment.an);
        ogj.cZM_(this.f, cardDeviceFragment.ba);
        ogj.cZL_(cardDeviceFragment.ah);
        pep.dmZ_(cardDeviceFragment.n);
        WifiConnectReceiver.a().b();
        BroadcastManagerUtil.bFJ_(cardDeviceFragment.g, cardDeviceFragment.av);
    }

    public void e(CardDeviceFragment cardDeviceFragment, DeviceInfo deviceInfo) {
        if (cardDeviceFragment == null || !CommonUtil.h(cardDeviceFragment.getActivity(), "com.huawei.health.MainActivity")) {
            LogUtil.h("CardDeviceFragment CardDeviceHelper", "accountInconsistentAlertDialog fragment is null");
            return;
        }
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.j;
        if (noTitleCustomAlertDialog != null && noTitleCustomAlertDialog.isShowing()) {
            this.j.cancel();
        }
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(cardDeviceFragment.getActivity()).e(String.format(Locale.ENGLISH, cardDeviceFragment.getActivity().getString(R.string._2130845618_res_0x7f021fb2), deviceInfo.getDeviceName())).czC_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: oek.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("CardDeviceFragment CardDeviceHelper", "showKidAccountNotSupportPairDialog, click");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        this.j = e;
        e.setCancelable(false);
        if (this.j.isShowing() || cardDeviceFragment.getActivity().isFinishing()) {
            return;
        }
        this.j.show();
    }

    public void b(CardDeviceFragment cardDeviceFragment, DeviceInfo deviceInfo) {
        if (cardDeviceFragment == null) {
            LogUtil.h("CardDeviceFragment CardDeviceHelper", "showCheckDeleteBluetoothDialog fragment is null");
            return;
        }
        if (cardDeviceFragment.getActivity().isFinishing() || !CommonUtil.h(cardDeviceFragment.getActivity(), "com.huawei.health.MainActivity")) {
            LogUtil.h("CardDeviceFragment CardDeviceHelper", "showCheckDeleteBluetoothDialog activity is not available");
            return;
        }
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.e;
        if (noTitleCustomAlertDialog != null && noTitleCustomAlertDialog.isShowing()) {
            this.e.cancel();
        }
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(cardDeviceFragment.getActivity()).e(String.format(Locale.ENGLISH, cardDeviceFragment.getActivity().getString(R.string._2130847195_res_0x7f0225db), deviceInfo.getDeviceName())).czC_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: oek.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("CardDeviceFragment CardDeviceHelper", "showCheckDeleteBluetoothDialog, click");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        this.e = e;
        e.setCancelable(false);
        this.e.show();
    }

    public static void d() {
        LogUtil.a("CardDeviceFragment CardDeviceHelper", "destroyInstance() start.");
        synchronized (c) {
            d = null;
        }
    }
}
