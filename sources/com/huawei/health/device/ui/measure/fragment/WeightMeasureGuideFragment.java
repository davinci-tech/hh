package com.huawei.health.device.ui.measure.fragment;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.GravityCompat;
import com.huawei.health.R;
import com.huawei.health.device.callback.ScaleDialogCallback;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.device.ui.DeviceMainActivity;
import com.huawei.health.device.ui.measure.fragment.WeightMeasureGuideFragment;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.BluetoothMeasureFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.ProductIntroductionFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.device.open.data.MeasureResult;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwbasemgr.WeightBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import defpackage.ceo;
import defpackage.cfe;
import defpackage.cfi;
import defpackage.cgt;
import defpackage.cjx;
import defpackage.ckn;
import defpackage.ckq;
import defpackage.cld;
import defpackage.cpa;
import defpackage.cpl;
import defpackage.cpz;
import defpackage.cqh;
import defpackage.cxh;
import defpackage.dcq;
import defpackage.dcx;
import defpackage.dcz;
import defpackage.dhd;
import defpackage.dic;
import defpackage.dja;
import defpackage.koq;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes3.dex */
public class WeightMeasureGuideFragment extends BluetoothMeasureFragment {
    private static final int GET_NETWORK_USER_INFO = 2;
    private static final String GO_BACK = "goback";
    private static final String GUEST_MEASURE = "guest_measure";
    private static final String GUEST_USER = "guest_user";
    private static final String HONOUR_DEVICE = "honour_device";
    private static final double LAST_BODY_FAT = 0.5d;
    private static final String PRODUCT_ID = "productId";
    private static final String TAG = "PluginDevice_WeightMeasureGuideFragment";
    private static final String TYPE = "type";
    private static final int USER_INFO = 1;
    private static final int WATER_RATE_LIMIT = 100;
    private static final String WEIGHT_BEAN = "weightBean";
    private static final int ZIP_MEASURE = 0;
    private static final int ZIP_MEASURE_NEW = 2;
    private Handler mHandler;
    private boolean mIsGotoAppSetting;
    private dcz mProductInfo;
    private View mView;
    private cld mWeightInteractor;
    private BaseFragment mWeightResultFragment;
    private boolean mIsGuestMeasure = false;
    private cfi mUser = MultiUsersManager.INSTANCE.getCurrentUser();

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceMeasureGuideFragment
    public dhd getMode() {
        LogUtil.a(TAG, "WeightMeasureGuideFragment getmode()");
        dcz d = ResourceManager.e().d(this.mProductId);
        this.mProductInfo = d;
        if (d == null) {
            LogUtil.h(TAG, "getMode mProductInfo is null");
            return null;
        }
        ArrayList<dcz.d> q = d.q();
        if (koq.b(q)) {
            LogUtil.h(TAG, "getMode measureGuides is null or size is zero");
            return null;
        }
        dhd dhdVar = new dhd();
        dhdVar.e(getMeasure(q));
        int i = q.size() > 3 ? 2 : 0;
        dhdVar.c(getSpannableString(dja.c(q, this.mProductId, i), dja.c(q, this.mProductId, i + 1)));
        dhdVar.a(getMeasureListForString());
        dhdVar.b(GravityCompat.START);
        dhdVar.e(true);
        dhdVar.a(true);
        super.setTitle(dcx.d(this.mProductId, this.mProductInfo.n().b()));
        return dhdVar;
    }

    private SpannableString getSpannableString(String str, String str2) {
        if (str == null) {
            LogUtil.h(TAG, "getSpannableString guidePromptMain is null");
            str = "";
        }
        if (str2 == null || str.equals(str2)) {
            LogUtil.h(TAG, "getSpannableString guidePromptSecond is null");
            str2 = "";
        }
        String str3 = str + System.lineSeparator() + System.lineSeparator() + str2;
        SpannableString spannableString = new SpannableString(str3);
        spannableString.setSpan(new TextAppearanceSpan(BaseApplication.getContext(), R.style.plugin_device_scale_guide_title_style), 0, str.length(), 33);
        spannableString.setSpan(new TextAppearanceSpan(BaseApplication.getContext(), R.style.plugin_device_scale_guide_description_style), str.length(), str3.length(), 33);
        return spannableString;
    }

    private ArrayList<Object> getMeasure(ArrayList<dcz.d> arrayList) {
        LogUtil.a(TAG, "WeightMeasureGuideFragment getMeasure()");
        ArrayList<Object> arrayList2 = new ArrayList<>(10);
        Iterator<dcz.d> it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(dcq.b().a(this.mProductId, it.next().e()));
        }
        LogUtil.a(TAG, "imgList = " + arrayList2.size());
        return arrayList2;
    }

    private ArrayList<String> getMeasureListForString() {
        ArrayList<String> arrayList = new ArrayList<>(10);
        Iterator<dcz.d> it = this.mProductInfo.q().iterator();
        while (it.hasNext()) {
            arrayList.add(dcx.d(this.mProductId, it.next().c()));
        }
        LogUtil.a(TAG, "imageListForString.size() = ", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceMeasureGuideFragment, com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        LogUtil.a(TAG, "WeightMeasureGuideFragment onCreate");
        super.onCreate(bundle);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceMeasureGuideFragment, com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a(TAG, "onCreateView");
        if (this.mView == null) {
            this.mView = super.onCreateView(layoutInflater, viewGroup, bundle);
            this.mWeightInteractor = cld.d(this.mProductId, this.mUniqueId);
            if (ceo.d().d(this.mUniqueId, true) instanceof cxh) {
                LogUtil.a(TAG, "init WeightInteractor and init Handler");
                this.mWeightInteractor.a();
            } else {
                LogUtil.a(TAG, "is not bleDevice");
            }
            if (this.mainActivity instanceof DeviceMainActivity) {
                ((DeviceMainActivity) this.mainActivity).d(this.mProductId);
            }
            initData();
            if (cpa.av(this.mProductId)) {
                cpa.at(this.mUniqueId);
            } else {
                LogUtil.a(TAG, "onCreateView device is ble");
            }
            return this.mView;
        }
        LogUtil.h(TAG, "onCreateView mView has already created");
        return this.mView;
    }

    private void initData() {
        this.child.findViewById(R.id.bt_device_measure_guide_next).setVisibility(8);
        this.mHandler = new Handler() { // from class: com.huawei.health.device.ui.measure.fragment.WeightMeasureGuideFragment.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                int i = message.what;
                if (i == 1) {
                    LogUtil.a(WeightMeasureGuideFragment.TAG, "WeightMeasureGuideFragment receive msg");
                    WeightMeasureGuideFragment.this.checkBluetoothPermissions();
                } else {
                    if (i == 2) {
                        LogUtil.a(WeightMeasureGuideFragment.TAG, "WeightMeasureGuideFragment startMeasure");
                        if (message.obj instanceof cfi) {
                            WeightMeasureGuideFragment.this.mUser = (cfi) message.obj;
                            WeightMeasureGuideFragment.this.fillUserInfoAndSendMsg();
                            return;
                        }
                        return;
                    }
                    LogUtil.h(WeightMeasureGuideFragment.TAG, "handleMessage default");
                }
            }
        };
        if (getArguments() != null) {
            this.mIsGuestMeasure = getArguments().getBoolean(GUEST_MEASURE, false);
        }
        cpz.c(this.mProductId);
        initByGuestMode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkBluetoothPermissions() {
        if (Build.VERSION.SDK_INT > 30 && PermissionUtil.e(this.mainActivity, PermissionUtil.PermissionType.SCAN) != PermissionUtil.PermissionResult.GRANTED) {
            PermissionUtil.b(this.mainActivity, PermissionUtil.PermissionType.SCAN, new AnonymousClass2(this.mainActivity));
        } else {
            startMeasure();
        }
    }

    /* renamed from: com.huawei.health.device.ui.measure.fragment.WeightMeasureGuideFragment$2, reason: invalid class name */
    class AnonymousClass2 extends CustomPermissionAction {
        AnonymousClass2(Context context) {
            super(context);
        }

        @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onGranted() {
            LogUtil.a(WeightMeasureGuideFragment.TAG, "startMeasure doActionWithPermissions onGranted");
            WeightMeasureGuideFragment.this.startMeasure();
        }

        @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onDenied(String str) {
            showAppSettingGuide();
        }

        @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
            showAppSettingGuide();
        }

        private void showAppSettingGuide() {
            nsn.cLJ_(WeightMeasureGuideFragment.this.mainActivity, PermissionUtil.PermissionType.SCAN, new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.WeightMeasureGuideFragment$2$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    WeightMeasureGuideFragment.AnonymousClass2.this.m216xbac25b44(view);
                }
            }, new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.WeightMeasureGuideFragment$2$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    WeightMeasureGuideFragment.AnonymousClass2.this.m217xee708605(view);
                }
            });
        }

        /* renamed from: lambda$showAppSettingGuide$0$com-huawei-health-device-ui-measure-fragment-WeightMeasureGuideFragment$2, reason: not valid java name */
        /* synthetic */ void m216xbac25b44(View view) {
            WeightMeasureGuideFragment.this.mIsGotoAppSetting = true;
            ViewClickInstrumentation.clickOnView(view);
        }

        /* renamed from: lambda$showAppSettingGuide$1$com-huawei-health-device-ui-measure-fragment-WeightMeasureGuideFragment$2, reason: not valid java name */
        /* synthetic */ void m217xee708605(View view) {
            WeightMeasureGuideFragment.this.onBackPressed();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.mIsGotoAppSetting) {
            this.mIsGotoAppSetting = false;
            checkBluetoothPermissions();
        }
    }

    private void initByGuestMode() {
        if (this.mArgs == null) {
            LogUtil.b(TAG, "mArgs is null");
            return;
        }
        this.mArgs.clear();
        if (!this.mIsGuestMeasure) {
            MultiUsersManager.INSTANCE.getCurrentUser(new WeightBaseResponseCallback() { // from class: com.huawei.health.device.ui.measure.fragment.WeightMeasureGuideFragment$$ExternalSyntheticLambda0
                @Override // com.huawei.hwbasemgr.WeightBaseResponseCallback
                public final void onResponse(int i, Object obj) {
                    WeightMeasureGuideFragment.this.m215x4abb07fa(i, (cfi) obj);
                }
            });
        } else {
            if (isGuestUserEffective()) {
                cfi cfiVar = (cfi) getArguments().getSerializable(GUEST_USER);
                this.mUser = cfiVar;
                if (cfiVar != null) {
                    this.mArgs.putString("uid", this.mUser.i());
                    this.mArgs.putInt(CommonConstant.KEY_GENDER, this.mUser.c());
                    this.mArgs.putInt("height", this.mUser.d());
                    this.mArgs.putInt("sex", this.mUser.c());
                    this.mArgs.putInt("age", this.mUser.a());
                    this.mArgs.putInt("birthday", this.mUser.g());
                } else {
                    LogUtil.h(TAG, "user is null");
                }
            }
            this.mArgs.putInt("guestUser", 1);
            String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
            if (accountInfo != null) {
                this.mArgs.putString("huid", accountInfo);
            }
            this.mArgs.putString("productId", this.mProductId);
            this.mArgs.putInt("type", -4);
            this.mArgs.putBoolean("activeMeasure", true);
        }
        LogUtil.a(TAG, "WeightMeasureGuideFragment send msg");
        this.mHandler.sendEmptyMessage(1);
        cpl.c().f(this.mProductId);
    }

    /* renamed from: lambda$initByGuestMode$0$com-huawei-health-device-ui-measure-fragment-WeightMeasureGuideFragment, reason: not valid java name */
    /* synthetic */ void m215x4abb07fa(int i, cfi cfiVar) {
        if (cfiVar == null || i != 0) {
            return;
        }
        Message obtain = Message.obtain();
        obtain.what = 2;
        obtain.obj = cfiVar;
        this.mHandler.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fillUserInfoAndSendMsg() {
        this.mArgs.putInt("height", this.mUser.d());
        this.mArgs.putInt("sex", this.mUser.c());
        this.mArgs.putInt("age", this.mUser.a());
        this.mArgs.putString("productId", this.mProductId);
        this.mArgs.putInt("type", -4);
        this.mArgs.putBoolean("activeMeasure", true);
        LogUtil.a(TAG, "fillUserInfoAndSendMsg onSuccess");
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        LogUtil.a(TAG, "WeightMeasureGuideFragment onStart");
        super.onStart();
        LogUtil.a(TAG, "WeightMeasureGuideFragment productId is " + this.mProductId);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceMeasureGuideFragment
    public void handleDataChangedInUiThread(HealthDevice healthDevice, HealthData healthData, boolean z) {
        if (healthData != null) {
            if (this.mIsGuestMeasure) {
                if (healthDevice == null) {
                    LogUtil.a(TAG, "saveWeightData Failed device null");
                    return;
                } else {
                    gotoWeightDetailActivity(ckn.c(healthDevice, healthData, this.mUser, 10006), this.mUser);
                    LogUtil.a(TAG, "Guest measure,jump to WeightDeatilActivity");
                    return;
                }
            }
            if (this.mWeightResultFragment != null) {
                LogUtil.h(TAG, "WeightResultFragment is showing");
                return;
            }
            BaseFragment b = ckq.b(this.mKind);
            if (b == null) {
                LogUtil.h(TAG, "WeightMeasureGuideFragment handleDataChangedInUiThread fragment = null");
                return;
            }
            if (isAdded()) {
                Bundle bundle = new Bundle();
                if (getArguments() != null && HONOUR_DEVICE.equals(getArguments().getString(GO_BACK, ""))) {
                    bundle.putString(GO_BACK, HONOUR_DEVICE);
                }
                bundle.putString("title", dcx.d(this.mProductId, this.mProductInfo.n().b()));
                bundle.putInt("content", R.string.IDS_device_importing_data);
                bundle.putSerializable("HealthData", healthData);
                bundle.putString("productId", this.mProductId);
                bundle.putInt("type", this.mType);
                bundle.putParcelable("commonDeviceInfo", this.mDeviceInfo);
                b.setArguments(bundle);
                jumpToResultFragment(b);
            }
        }
    }

    private void jumpToResultFragment(BaseFragment baseFragment) {
        switchFragment(baseFragment);
        this.mWeightResultFragment = baseFragment;
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceMeasureGuideFragment
    public void handleDataChangedInUiThreadUniversal(MeasureResult measureResult, boolean z) {
        if (measureResult == null) {
            return;
        }
        BaseFragment b = ckq.b(this.mKind);
        if (b == null) {
            LogUtil.h(TAG, "WeightMeasureGuideFragment handleDataChangedInUiThread fragment = null");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("content", R.string.IDS_device_importing_data);
        bundle.putSerializable("HealthData", dic.c().d(measureResult));
        bundle.putString("productId", this.mProductId);
        bundle.putInt("type", this.mType);
        bundle.putParcelable("commonDeviceInfo", this.mDeviceInfo);
        bundle.putString("title", dcx.d(this.mProductId, this.mProductInfo.n().b()));
        b.setArguments(bundle);
        switchFragment(b);
    }

    /* renamed from: lambda$handleStatusChangedInUiThread$1$com-huawei-health-device-ui-measure-fragment-WeightMeasureGuideFragment, reason: not valid java name */
    /* synthetic */ void m214x419b4364(int i) {
        LogUtil.a(TAG, "showWeightMeasureFailedDialog result: ", Integer.valueOf(i));
        if (i == 0) {
            if (getArguments() != null && getArguments().getString(GO_BACK, "").equals(HONOUR_DEVICE)) {
                onBackPressed();
                return;
            } else {
                popupFragment(ProductIntroductionFragment.class);
                return;
            }
        }
        LogUtil.a(TAG, "showWeightMeasureFailedDialog negative");
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceMeasureGuideFragment
    public void handleStatusChangedInUiThread(HealthDevice healthDevice, int i) {
        if (i == 9 || i == 8) {
            cqh.c().a(this.mainActivity, new ScaleDialogCallback() { // from class: com.huawei.health.device.ui.measure.fragment.WeightMeasureGuideFragment$$ExternalSyntheticLambda1
                @Override // com.huawei.health.device.callback.ScaleDialogCallback
                public final void operationResult(int i2) {
                    WeightMeasureGuideFragment.this.m214x419b4364(i2);
                }
            });
            return;
        }
        if (i == 15) {
            LogUtil.h(TAG, "WeightMeasureGuideFragment status: ", 15);
            if (cpa.ae(this.mProductId)) {
                cgt.e().e(-2);
                cjx.e().e(this.mProductId, this.mUniqueId, -2);
            }
            if (this.mainActivity instanceof DeviceMainActivity) {
                ((DeviceMainActivity) this.mainActivity).e(this.mUniqueId);
                ((DeviceMainActivity) this.mainActivity).a(this.mProductId);
                return;
            } else {
                LogUtil.h(TAG, "DeviceMainActivity is not active");
                return;
            }
        }
        if (i == 1) {
            LogUtil.a(TAG, "WeightInterator set status: ", Integer.valueOf(i));
            if (cpa.ae(this.mProductId)) {
                cld.d(this.mProductId, this.mUniqueId);
                return;
            }
            return;
        }
        LogUtil.h(TAG, "WeightInterator set status: ", Integer.valueOf(i));
        if (cpa.ae(this.mProductId)) {
            this.mWeightInteractor.i();
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceMeasureGuideFragment
    public void handleStatusChangedInUiThreadUniversal(int i) {
        LogUtil.a(TAG, "WeightMeasureGuideFragment handleStatusChangedInUiThreadUniversal");
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceMeasureGuideFragment
    public void handleFailedEventInUiThread(int i) {
        LogUtil.a(TAG, "WeightMeasureGuideFragment handleFailedEventInUiThread");
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.BluetoothMeasureFragment, com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceMeasureGuideFragment, com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        if (this.mWeightInteractor != null) {
            LogUtil.h(TAG, "mWeightInteractor destroy");
            this.mWeightInteractor.e();
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceMeasureGuideFragment, com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        if (getArguments() != null && getArguments().getString(GO_BACK, "").equals(HONOUR_DEVICE)) {
            popupFragment(HonourDeviceFragment.class);
            return false;
        }
        if (getArguments() != null && "hygride".equals(getArguments().getString(GO_BACK, ""))) {
            if (this.mIsGuestMeasure) {
                popupFragment(GuestUserInfoGuideFragment.class);
            } else {
                popupFragment(HagridDeviceManagerFragment.class);
            }
            return false;
        }
        return super.onBackPressed();
    }

    private void gotoWeightDetailActivity(cfe cfeVar, cfi cfiVar) {
        if (cfeVar == null) {
            LogUtil.a(TAG, "gotoWeightDetailActivity bean is null");
            return;
        }
        double a2 = cfeVar.a();
        Intent intent = new Intent();
        intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.ui.main.stories.health.activity.healthdata.WeightDetailActivity");
        LogUtil.a(TAG, "checkWeightDataValidation : true");
        constructIntentForWeight(cfeVar, cfiVar, a2, intent);
        if (BaseApplication.getActivity() != null) {
            try {
                BaseApplication.getActivity().startActivity(intent);
            } catch (ActivityNotFoundException unused) {
                LogUtil.b(TAG, "gotoWeightDetailActivity ActivityNotFoundException");
            }
        } else {
            LogUtil.h(TAG, "gotoWeightDetailActivity BaseApplication.getActivity() is null");
        }
        popupFragment(HagridDeviceManagerFragment.class);
    }

    private void constructIntentForWeight(cfe cfeVar, cfi cfiVar, double d, Intent intent) {
        cfeVar.a(cpa.f.get(this.mProductId).intValue());
        cfeVar.e(this.mUniqueId);
        intent.putExtra(WEIGHT_BEAN, cfeVar);
        intent.putExtra(GUEST_MEASURE, true);
        intent.putExtra("weight", cfeVar.ax());
        intent.putExtra("bodyFat", d);
        intent.putExtra("weightTime", cfeVar.au());
        intent.putExtra("deleteTime", cfeVar.au());
        if (cfeVar.ap() > 0.0d && cfeVar.ap() <= 100.0d) {
            intent.putExtra("water", cfeVar.ap());
        } else {
            intent.putExtra("water", cfeVar.al());
        }
        intent.putExtra("deleteEndTime", cfeVar.av());
        intent.putExtra("BITag", 1);
        intent.putExtra("resistance", cfeVar.ae());
        if (cfeVar.t() > 0) {
            intent.putExtra("userHeight", cfeVar.t());
        } else {
            intent.putExtra("userHeight", cfiVar.d());
        }
        intent.putExtra("isShowBodyFat", d >= 0.5d);
        intent.putExtra("isShowInput", false);
        intent.putExtra("dataType", cfeVar.l());
    }

    private boolean isGuestUserEffective() {
        return getArguments() != null && (getArguments().getSerializable(GUEST_USER) instanceof cfi);
    }
}
