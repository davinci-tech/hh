package com.huawei.health.device.ui.measure.fragment;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.wifi.control.claim.ClaimWeightDataManager;
import com.huawei.health.device.wifi.lib.handler.StaticHandler;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceSelectBindFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetBindDeviceReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetWifiDeviceInfoReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetWifiDeviceInfoRsp;
import com.huawei.hwcloudmodel.model.userprofile.DeviceInfo;
import com.huawei.hwcloudmodel.model.userprofile.GetBindDeviceRsp;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ceo;
import defpackage.ckm;
import defpackage.ckq;
import defpackage.cpa;
import defpackage.cpw;
import defpackage.csf;
import defpackage.ctk;
import defpackage.cub;
import defpackage.dcq;
import defpackage.dcr;
import defpackage.dcx;
import defpackage.dcz;
import defpackage.dfb;
import defpackage.dhd;
import defpackage.dja;
import defpackage.jbs;
import defpackage.jec;
import defpackage.koq;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes3.dex */
public class WifiWeightMeasureGuideFragment extends BaseFragment {
    private static final int AGGREGATE_COUNT_ONE = 1;
    private static final int CLOUD_ERROR_CODE_SUCCESS = 0;
    private static final int GUIDE_IMAGE_VIEW_HEIGHT = 288;
    private static final int GUIDE_IMAGE_VIEW_HEIGHT_RATIO = 2;
    private static final int MSG_GET_NEW_WEIGHT_VALUE_FAILURE = 101;
    private static final int MSG_GET_NEW_WEIGHT_VALUE_SUCCESS = 100;
    private static final int MSG_NOT_EXIST_DEVICE = 104;
    private static final int MSG_UPDATE_NEW_WEIGHT_VALUE = 103;
    private static final int QUERY_OPPORTUNITY_FOUR = 110000;
    private static final int QUERY_OPPORTUNITY_ONE = 10000;
    private static final int QUERY_OPPORTUNITY_THREE = 80000;
    private static final int QUERY_OPPORTUNITY_TWO = 30000;
    private static final String TAG = "WifiWeightMeasureGuideFragment";
    private static final int TIMEOUT = 120000;
    private static final int WEIGHT_DATA_FAULT_TOLERANT_TIME = 15000;
    private static final int ZIP_MEASURE = 0;
    private static final int ZIP_MEASURE_NEW = 2;
    private dfb mAnimationHandler;
    private ContentValues mDeviceInfo;
    private ImageView mGuideImageView;
    private HealthTextView mGuideTextView;
    private dhd mMeasureModel;
    private LinearLayout mMessureGuideImageLayout;
    private dcz mProductInfo;
    private MyHandler myHandler;
    private String mProductId = "";
    private String mUniqueId = "";
    private long mStartTime = 0;
    private long mEndTime = 0;
    private final BroadcastReceiver mWeightPushInfoReceiver = new BroadcastReceiver() { // from class: com.huawei.health.device.ui.measure.fragment.WifiWeightMeasureGuideFragment.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            cpw.a(false, WifiWeightMeasureGuideFragment.TAG, "mWeightPushInfoReceiver onReceive");
            if (intent == null || !"com.huawei.health.action.ACTION_PUSH_DATA_DONE_ACTION".equals(intent.getAction())) {
                return;
            }
            cpw.a(false, WifiWeightMeasureGuideFragment.TAG, "receive push info,now get new weight measure data...");
            ThreadPoolManager.d().c(WifiWeightMeasureGuideFragment.TAG, new Runnable() { // from class: com.huawei.health.device.ui.measure.fragment.WifiWeightMeasureGuideFragment.1.1
                @Override // java.lang.Runnable
                public void run() {
                    WifiWeightMeasureGuideFragment.this.getMeasureWeightData();
                    ClaimWeightDataManager.INSTANCE.startSync();
                }
            });
        }
    };
    private View.OnClickListener negativeListener = new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.WifiWeightMeasureGuideFragment$$ExternalSyntheticLambda2
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            WifiWeightMeasureGuideFragment.this.m256xc7afc3b(view);
        }
    };
    private View.OnClickListener positiveListener = new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.WifiWeightMeasureGuideFragment$$ExternalSyntheticLambda3
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            WifiWeightMeasureGuideFragment.this.m257xd497abc(view);
        }
    };

    static class MyHandler extends StaticHandler<WifiWeightMeasureGuideFragment> {
        MyHandler(WifiWeightMeasureGuideFragment wifiWeightMeasureGuideFragment) {
            super(wifiWeightMeasureGuideFragment);
        }

        @Override // com.huawei.health.device.wifi.lib.handler.StaticHandler
        public void handleMessage(WifiWeightMeasureGuideFragment wifiWeightMeasureGuideFragment, Message message) {
            if (wifiWeightMeasureGuideFragment != null) {
                if (wifiWeightMeasureGuideFragment.isDestroy()) {
                    cpw.d(false, WifiWeightMeasureGuideFragment.TAG, "MyHandler activity is not exist");
                    return;
                }
                if (!wifiWeightMeasureGuideFragment.isAdded()) {
                    cpw.d(false, WifiWeightMeasureGuideFragment.TAG, "MyHandler mFragment is not add");
                    return;
                }
                if (message == null) {
                    cpw.d(false, WifiWeightMeasureGuideFragment.TAG, "MyHandler msg is null");
                    return;
                }
                cpw.a(false, WifiWeightMeasureGuideFragment.TAG, "MyHandler what:", Integer.valueOf(message.what));
                switch (message.what) {
                    case 100:
                        wifiWeightMeasureGuideFragment.myHandler.removeMessages(101);
                        if (message.obj instanceof ckm) {
                            wifiWeightMeasureGuideFragment.goToResultPage((ckm) message.obj);
                            break;
                        }
                        break;
                    case 101:
                        wifiWeightMeasureGuideFragment.myHandler.removeMessages(100);
                        wifiWeightMeasureGuideFragment.showMeasureTimeoutDialog(wifiWeightMeasureGuideFragment.mainActivity);
                        break;
                    case 102:
                    default:
                        cpw.d(false, "MyHandler what is other", new Object[0]);
                        break;
                    case 103:
                        wifiWeightMeasureGuideFragment.syncNewWeightData();
                        break;
                    case 104:
                        wifiWeightMeasureGuideFragment.myHandler.removeMessages(101);
                        wifiWeightMeasureGuideFragment.showNoDeviceDialog(message.obj == null ? null : String.valueOf(message.obj));
                        break;
                }
            }
            cpw.d(false, WifiWeightMeasureGuideFragment.TAG, "MyHandler object is null");
        }
    }

    /* renamed from: lambda$new$0$com-huawei-health-device-ui-measure-fragment-WifiWeightMeasureGuideFragment, reason: not valid java name */
    /* synthetic */ void m256xc7afc3b(View view) {
        this.mainActivity.finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: lambda$new$1$com-huawei-health-device-ui-measure-fragment-WifiWeightMeasureGuideFragment, reason: not valid java name */
    /* synthetic */ void m257xd497abc(View view) {
        dcr switchProductGroupItem = switchProductGroupItem(ResourceManager.e().a().e(), HealthDevice.HealthDeviceKind.HDK_WEIGHT.name());
        if (switchProductGroupItem != null) {
            cpw.a(false, TAG, "item = ", switchProductGroupItem.toString());
            DeviceSelectBindFragment deviceSelectBindFragment = new DeviceSelectBindFragment();
            Bundle bundle = new Bundle();
            bundle.putString("kind", switchProductGroupItem.c().name());
            bundle.putString("deviceType", this.mainActivity.getResources().getString(dcx.e(switchProductGroupItem.e())));
            deviceSelectBindFragment.setArguments(bundle);
            switchFragment((Fragment) null, deviceSelectBindFragment);
        } else {
            cpw.d(false, TAG, "WEIGHT ProductGroup is error ");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private dcr switchProductGroupItem(ArrayList<dcr> arrayList, String str) {
        Iterator<dcr> it = arrayList.iterator();
        dcr dcrVar = null;
        while (it.hasNext()) {
            dcr next = it.next();
            cpw.a(false, TAG, " item.kind.name() = ", next.c().name(), " device_Type = ", str);
            if (next.c().name().equals(str)) {
                dcrVar = next;
            }
        }
        return dcrVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showNoDeviceDialog(String str) {
        this.myHandler.removeCallbacksAndMessages(null);
        csf.LF_(this.mainActivity, str, this.negativeListener, this.positiveListener);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        cpw.a(false, TAG, "onCreate");
        this.myHandler = new MyHandler(this);
        SharedPreferenceManager.e(this.mainActivity, "wifi_weight_device", "weight_notify_key", "false", (StorageParams) null);
        long currentTimeMillis = System.currentTimeMillis();
        cpw.a(false, TAG, "currentTime: ", Long.valueOf(currentTimeMillis));
        this.mStartTime = currentTimeMillis - 15000;
        this.mEndTime = currentTimeMillis + 120000;
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        cpw.a(false, TAG, "onCreateView");
        ViewGroup viewGroup2 = super.onCreateView(layoutInflater, viewGroup, bundle) instanceof ViewGroup ? (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle) : null;
        this.child = layoutInflater.inflate(R.layout.wifi_weight_measure_layout, viewGroup, false);
        this.mGuideTextView = (HealthTextView) nsy.cMd_(this.child, R.id.hw_wifi_device_measure_prompt);
        this.mMessureGuideImageLayout = (LinearLayout) nsy.cMd_(this.child, R.id.hw_wifi_device_measure_image_view_layout);
        refreshImageLayout();
        ContentValues contentValues = (ContentValues) getArguments().getParcelable("commonDeviceInfo");
        this.mDeviceInfo = contentValues;
        if (contentValues != null) {
            this.mProductId = contentValues.getAsString("productId");
            this.mUniqueId = this.mDeviceInfo.getAsString("uniqueId");
        }
        this.mProductInfo = ResourceManager.e().d(this.mProductId);
        LogUtil.a(TAG, "productId: ", this.mProductId);
        dhd mode = getMode();
        this.mMeasureModel = mode;
        if (mode == null) {
            cpw.a(false, TAG, "getMode() is null");
            Serializable serializable = getArguments().getSerializable("DeviceMeasureOperateModel");
            if (serializable == null) {
                cpw.d(false, TAG, "onCreateView serializable = null");
            } else {
                initView(serializable);
            }
        } else {
            cpw.a(false, TAG, "getMode() is not null");
            initView(this.mMeasureModel);
        }
        if (viewGroup2 != null) {
            viewGroup2.addView(this.child);
        }
        initTitle();
        checkDevice();
        cpa.at(this.mUniqueId);
        return viewGroup2;
    }

    private void initTitle() {
        if (this.mProductInfo == null) {
            return;
        }
        String f = cpa.f(this.mUniqueId);
        if (TextUtils.isEmpty(f)) {
            super.setTitle(dcx.d(this.mProductId, this.mProductInfo.n().b()));
            return;
        }
        super.setTitle(dcx.d(this.mProductId, this.mProductInfo.n().b()) + " - " + f);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        registerPushInfoBroadcast();
        if (!CommonUtil.aa(this.mainActivity)) {
            showWiFiEnableDialog(this.mainActivity);
            return;
        }
        this.myHandler.sendEmptyMessageDelayed(103, PreConnectManager.CONNECT_INTERNAL);
        this.myHandler.sendEmptyMessageDelayed(103, OpAnalyticsConstants.H5_LOADING_DELAY);
        this.myHandler.sendEmptyMessageDelayed(103, 80000L);
        this.myHandler.sendEmptyMessageDelayed(103, 110000L);
        this.myHandler.sendEmptyMessageDelayed(101, 120000L);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        cpw.a(false, TAG, "onPause");
        unRegisterPushInfoBroadcast();
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        SharedPreferenceManager.e(this.mainActivity, "wifi_weight_device", "weight_notify_key", "true", (StorageParams) null);
        super.onDestroy();
        MyHandler myHandler = this.myHandler;
        if (myHandler != null) {
            myHandler.removeCallbacks(null);
        }
        if (this.mAnimationHandler != null) {
            this.mAnimationHandler = null;
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        if (cpa.ae(this.mProductId)) {
            popupFragment(HagridDeviceManagerFragment.class);
            return false;
        }
        popupFragment(WiFiProductIntroductionFragment.class);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDestroy() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            cpw.d(false, TAG, "DeviceMainActivity is null");
            return true;
        }
        if (!activity.isFinishing() && !activity.isDestroyed()) {
            return false;
        }
        cpw.d(false, TAG, "DeviceMainActivity is Destroyed");
        return true;
    }

    private void initView(Object obj) {
        lightTheScreen();
        if (this.child == null || !(obj instanceof dhd)) {
            return;
        }
        dhd dhdVar = (dhd) obj;
        cpw.a(false, TAG, "initView");
        this.mGuideTextView.setText(dhdVar.c());
        if (cpa.x(this.mProductId)) {
            initHagirdeView(dhdVar);
        }
    }

    private void showWiFiEnableDialog(final Context context) {
        if (context == null) {
            LogUtil.h(TAG, "showWiFiEnableDialog context is null");
            return;
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(context);
        String string = context.getResources().getString(R.string.IDS_device_wifi_device_product_name);
        builder.e(String.format(Locale.ENGLISH, context.getResources().getString(R.string.IDS_device_wifi_measure_no_network_msg), string)).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.WifiWeightMeasureGuideFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WifiWeightMeasureGuideFragment.this.m259x918a1014(view);
            }
        }).czC_(R.string.IDS_device_wifi_go_connect, new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.WifiWeightMeasureGuideFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WifiWeightMeasureGuideFragment.lambda$showWiFiEnableDialog$3(context, view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        e.setCancelable(false);
        e.show();
    }

    /* renamed from: lambda$showWiFiEnableDialog$2$com-huawei-health-device-ui-measure-fragment-WifiWeightMeasureGuideFragment, reason: not valid java name */
    /* synthetic */ void m259x918a1014(View view) {
        onBackPressed();
        LogUtil.a(TAG, "showWiFiEnableDialog: do nothing");
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void lambda$showWiFiEnableDialog$3(Context context, View view) {
        cub.k(context);
        ViewClickInstrumentation.clickOnView(view);
    }

    private SpannableString getSpannableString(String str, String str2) {
        if (str == null) {
            LogUtil.h(TAG, "getSpannableString guidePromptMain is null");
            str = "";
        }
        if (str2 == null) {
            LogUtil.h(TAG, "getSpannableString guidePromptSecond is null");
            str2 = "";
        }
        String str3 = str + System.lineSeparator() + System.lineSeparator() + str2;
        SpannableString spannableString = new SpannableString(str3);
        spannableString.setSpan(new TextAppearanceSpan(BaseApplication.getContext(), R.style.plugin_device_scale_guide_title_style), 0, str.length(), 33);
        spannableString.setSpan(new TextAppearanceSpan(BaseApplication.getContext(), R.style.plugin_device_scale_guide_description_style), str.length(), str3.length(), 33);
        return spannableString;
    }

    private void initHagirdeView(dhd dhdVar) {
        ImageView imageView = (ImageView) nsy.cMd_(this.child, R.id.hw_wifi_device_measure_image_view);
        this.mGuideImageView = imageView;
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.width = -1;
        layoutParams.height = nsn.c(this.mainActivity, 288.0f);
        this.mGuideImageView.setLayoutParams(layoutParams);
        this.mGuideImageView.setBackgroundResource(0);
        Object obj = dhdVar.a().get(0);
        if (obj instanceof Integer) {
            this.mGuideImageView.setImageResource(((Integer) obj).intValue());
        } else if (obj instanceof String) {
            this.mGuideImageView.setImageBitmap(dcx.TK_((String) obj));
        } else {
            cpw.d(false, TAG, "initHagirdeView() imageResource is error");
        }
    }

    private void lightTheScreen() {
        this.mainActivity.getWindow().setFlags(128, 128);
    }

    private dhd getMode() {
        cpw.a(false, TAG, "getmode()");
        dcz dczVar = this.mProductInfo;
        if (dczVar == null || dczVar.q().size() <= 0) {
            return null;
        }
        ArrayList<dcz.d> q = this.mProductInfo.q();
        if (koq.b(q)) {
            LogUtil.h(TAG, "getMode measureGuides is null or size is zero");
            return null;
        }
        dhd dhdVar = new dhd();
        dhdVar.e(getMeasureGuideImage(q));
        int i = q.size() > 3 ? 2 : 0;
        dhdVar.c(getSpannableString(dja.c(q, this.mProductId, i), dja.c(q, this.mProductId, i + 1)));
        return dhdVar;
    }

    private ArrayList<Object> getMeasureGuideImage(ArrayList<dcz.d> arrayList) {
        LogUtil.a(TAG, " getMeasureGuideImage()");
        ArrayList<Object> arrayList2 = new ArrayList<>(10);
        Iterator<dcz.d> it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(dcq.b().a(this.mProductId, it.next().e()));
        }
        LogUtil.a(TAG, "guideImages:", Integer.valueOf(arrayList2.size()));
        return arrayList2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showMeasureTimeoutDialog(Context context) {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(context);
        builder.e(R.string.IDS_device_wifi_measure_timeout_prompt_msg).czC_(R.string.IDS_device_measureactivity_result_confirm, new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.WifiWeightMeasureGuideFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WifiWeightMeasureGuideFragment.this.m258x3a30dda1(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        e.setCancelable(false);
        e.show();
    }

    /* renamed from: lambda$showMeasureTimeoutDialog$4$com-huawei-health-device-ui-measure-fragment-WifiWeightMeasureGuideFragment, reason: not valid java name */
    /* synthetic */ void m258x3a30dda1(View view) {
        popupFragment(WiFiProductIntroductionFragment.class);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void goToResultPage(ckm ckmVar) {
        if (ckmVar != null) {
            Fragment b = ckq.b(this.mProductId);
            if (cpa.x(this.mProductId)) {
                b = new WifiWeightResultFragment();
            }
            if (b == null) {
                cpw.d(false, TAG, "WeightMeasureGuideFragment handleDataChangedInUiThread fragment = null");
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putSerializable("HealthData", ckmVar);
            bundle.putString("productId", this.mProductId);
            bundle.putParcelable("commonDeviceInfo", this.mDeviceInfo);
            b.setArguments(bundle);
            switchFragment(b);
            SharedPreferenceManager.e(BaseApplication.getContext(), "wifi_weight_device", "weight_notify_key", "true", (StorageParams) null);
        }
    }

    private void registerPushInfoBroadcast() {
        cpw.a(false, TAG, "registerPushInfoBroadcast");
        IntentFilter intentFilter = new IntentFilter("com.huawei.health.action.ACTION_PUSH_DATA_DONE_ACTION");
        intentFilter.addAction("com.huawei.health.action.ACTION_PUSH_DATA_DONE_ACTION");
        BroadcastManagerUtil.bFA_(this.mainActivity, this.mWeightPushInfoReceiver, intentFilter, LocalBroadcast.c, null);
    }

    private void unRegisterPushInfoBroadcast() {
        cpw.a(false, TAG, "unRegisterPushInfoBroadcast");
        try {
            this.mainActivity.unregisterReceiver(this.mWeightPushInfoReceiver);
        } catch (IllegalArgumentException e) {
            cpw.e(false, TAG, "unRegisterPushInfoBroadcast ", e.getMessage());
        } catch (RuntimeException e2) {
            cpw.e(false, TAG, "unRegisterPushInfoBroadcast ", e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getMeasureWeightData() {
        cpw.a(false, TAG, "getMeasureWeightData mProductId is ", this.mProductId);
        ctk ctkVar = !TextUtils.isEmpty(this.mUniqueId) ? (ctk) ceo.d().d(this.mUniqueId, false) : null;
        long a2 = ctkVar != null ? ctkVar.a() : 0L;
        cpw.a(false, TAG, "getMeasureWeightData deviceCode is:", Long.valueOf(a2));
        if (a2 == 0) {
            cpw.d(false, TAG, "getMeasureWeightData deviceCode is zero");
            return;
        }
        GetBindDeviceReq getBindDeviceReq = new GetBindDeviceReq();
        getBindDeviceReq.setDeviceCode(Long.valueOf(a2));
        GetBindDeviceRsp c = jbs.a(this.mainActivity).c(getBindDeviceReq);
        if (!checkCloudResponse(c)) {
            cpw.d(false, TAG, "getMeasureWeightData checkCloudResponse error");
            return;
        }
        List<DeviceInfo> deviceInfos = c.getDeviceInfos();
        if (deviceInfos == null || deviceInfos.isEmpty()) {
            cpw.d(false, TAG, "getMeasureWeightData deviceInfos is null");
            return;
        }
        String uniqueId = deviceInfos.get(0).getUniqueId();
        cpw.a(false, TAG, "getMeasureWeightData deviceUUID is:", CommonUtil.l(uniqueId));
        if (uniqueId == null) {
            cpw.d(false, TAG, "getMeasureWeightData error, deviceUUID is null");
        } else {
            cpw.a(false, TAG, "mStartTime:", Long.valueOf(this.mStartTime), "|mEndTime:", Long.valueOf(this.mEndTime));
            statisticHiHealthData(getHiAggregateOption(uniqueId, new int[]{10006}, new String[]{BleConstants.WEIGHT_KEY}));
        }
    }

    private boolean checkCloudResponse(GetBindDeviceRsp getBindDeviceRsp) {
        if (getBindDeviceRsp == null) {
            LogUtil.h(TAG, "checkCloudResponse response is null");
            return false;
        }
        int intValue = getBindDeviceRsp.getResultCode().intValue();
        LogUtil.a(TAG, "checkCloudResponse response resultCode is,", Integer.valueOf(intValue));
        return intValue == 0;
    }

    private void statisticHiHealthData(HiAggregateOption hiAggregateOption) {
        HiHealthManager.d(this.mainActivity).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: com.huawei.health.device.ui.measure.fragment.WifiWeightMeasureGuideFragment.2
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i, int i2) {
                WifiWeightMeasureGuideFragment.this.handleWeightData(list);
            }
        });
    }

    private HiAggregateOption getHiAggregateOption(String str, int[] iArr, String[] strArr) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setTimeRange(this.mStartTime, this.mEndTime);
        hiAggregateOption.setType(iArr);
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setSortOrder(1);
        hiAggregateOption.setFilter("");
        hiAggregateOption.setConstantsKey(strArr);
        hiAggregateOption.setCount(1);
        hiAggregateOption.setDeviceUuid(str);
        return hiAggregateOption;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleWeightData(List<HiHealthData> list) {
        if (koq.b(list)) {
            LogUtil.h(TAG, "handleWeightData datas is null or empty");
            return;
        }
        double d = list.get(0).getDouble("bodyWeight");
        double d2 = list.get(0).getDouble(BleConstants.BODY_FAT_RATE);
        String l = Long.toString(list.get(0).getStartTime());
        ckm ckmVar = new ckm();
        ckmVar.setWeight((float) d);
        ckmVar.setBodyFatRat((float) d2);
        ckmVar.a(l);
        String metaData = list.get(0).getMetaData();
        if (metaData == null || metaData.length() == 0) {
            metaData = MultiUsersManager.INSTANCE.getMainUser().i();
        }
        if (Constants.NULL.equalsIgnoreCase(metaData) || "0".equals(metaData)) {
            metaData = MultiUsersManager.INSTANCE.getMainUser().i();
        }
        if ("-1".equals(metaData)) {
            ckmVar.b(true);
            ckmVar.e("");
        } else {
            ckmVar.b(false);
            ckmVar.e(metaData);
        }
        Message obtain = Message.obtain();
        obtain.what = 100;
        obtain.obj = ckmVar;
        this.myHandler.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void syncNewWeightData() {
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(2);
        hiSyncOption.setSyncDataType(20000);
        hiSyncOption.setSyncScope(1);
        hiSyncOption.setSyncMethod(2);
        hiSyncOption.setPushAction(2);
        cpw.a(false, TAG, "processPushMsg, startSync,time:", jec.c(new Date()));
        HiHealthNativeApi.a(this.mainActivity).synCloud(hiSyncOption, null);
    }

    private void checkDevice() {
        cpw.a(false, TAG, "checkDevice() checkDevice mProductId:", this.mProductId);
        if (ceo.d().d(this.mUniqueId, false) instanceof ctk) {
            final ctk ctkVar = (ctk) ceo.d().d(this.mUniqueId, false);
            if (ctkVar == null) {
                cpw.a(false, TAG, "checkDevice() mWiFiDevice is null");
                Message obtain = Message.obtain();
                obtain.what = 104;
                this.myHandler.sendMessage(obtain);
                return;
            }
            WifiDeviceGetWifiDeviceInfoReq wifiDeviceGetWifiDeviceInfoReq = new WifiDeviceGetWifiDeviceInfoReq();
            wifiDeviceGetWifiDeviceInfoReq.setDevId(ctkVar.b().a());
            jbs.a(this.mainActivity).a(wifiDeviceGetWifiDeviceInfoReq, new ICloudOperationResult() { // from class: com.huawei.health.device.ui.measure.fragment.WifiWeightMeasureGuideFragment$$ExternalSyntheticLambda4
                @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
                public final void operationResult(Object obj, String str, boolean z) {
                    WifiWeightMeasureGuideFragment.this.m255xac4b51e(ctkVar, (WifiDeviceGetWifiDeviceInfoRsp) obj, str, z);
                }
            });
            return;
        }
        LogUtil.h(TAG, "DeviceManager.getInstance().getBondedDeviceByUniqueId(mUniqueId, false) not instanceof WiFiDevice");
    }

    /* renamed from: lambda$checkDevice$5$com-huawei-health-device-ui-measure-fragment-WifiWeightMeasureGuideFragment, reason: not valid java name */
    /* synthetic */ void m255xac4b51e(ctk ctkVar, WifiDeviceGetWifiDeviceInfoRsp wifiDeviceGetWifiDeviceInfoRsp, String str, boolean z) {
        if (!z) {
            handleDeviceNotExist(wifiDeviceGetWifiDeviceInfoRsp, ctkVar);
            return;
        }
        if (wifiDeviceGetWifiDeviceInfoRsp != null && wifiDeviceGetWifiDeviceInfoRsp.getDeviceDetailInfo() == null) {
            cpw.a(false, TAG, "checkDevice() device already not exists");
            Message obtain = Message.obtain();
            obtain.obj = ctkVar.d();
            obtain.what = 104;
            this.myHandler.sendMessage(obtain);
            return;
        }
        cpw.a(false, TAG, "checkDevice() device already exists");
    }

    private void handleDeviceNotExist(WifiDeviceGetWifiDeviceInfoRsp wifiDeviceGetWifiDeviceInfoRsp, ctk ctkVar) {
        int i;
        String str;
        if (wifiDeviceGetWifiDeviceInfoRsp != null) {
            i = wifiDeviceGetWifiDeviceInfoRsp.getResultCode().intValue();
            str = wifiDeviceGetWifiDeviceInfoRsp.getResultDesc();
            if (i == 112000000) {
                cpw.a(false, TAG, "device already not exists");
                Message obtain = Message.obtain();
                obtain.obj = ctkVar.d();
                obtain.what = 104;
                this.myHandler.sendMessage(obtain);
            }
        } else {
            i = Constants.CODE_UNKNOWN_ERROR;
            str = "unknown error";
        }
        cpw.a(false, TAG, "handleCheckDeviceSuccess() errCode = ", Integer.valueOf(i), ",resultDesc:", str);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LogUtil.a(TAG, "onConfigurationChanged, refresh page");
        refreshImageLayout();
        super.onConfigurationChanged(configuration);
    }

    private void refreshImageLayout() {
        LinearLayout linearLayout = this.mMessureGuideImageLayout;
        if (linearLayout != null) {
            nsn.cLA_(linearLayout, 2);
        }
    }
}
