package com.huawei.health.ecologydevice.ui.measure.fragment;

import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.fragment.app.FragmentActivity;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.api.HealthDeviceEntryApi;
import com.huawei.health.device.api.HonourDeviceConstantsApi;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.health.device.manager.ResourceFileListener;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.manager.SportDeviceDataManager;
import com.huawei.health.ecologydevice.ui.measure.activity.DeviceUsageDescriptionActivity;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.popupview.PopViewList;
import com.huawei.ui.commonui.webview.WebViewActivity;
import defpackage.cpp;
import defpackage.czb;
import defpackage.czd;
import defpackage.dcq;
import defpackage.dcx;
import defpackage.dcz;
import defpackage.dks;
import defpackage.gnm;
import defpackage.ixx;
import defpackage.koq;
import defpackage.nsf;
import defpackage.nsi;
import defpackage.nsj;
import defpackage.nsn;
import health.compact.a.GRSManager;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import java.io.File;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes3.dex */
public class SportDeviceIntroductionFragment extends ProductFragment implements ResourceFileListener, View.OnClickListener, SportDeviceDataManager.SportDataListener {
    private static final String KEY_BLE = "BLE_FROM_QRCODE";
    private static final String KEY_BLE_NAME = "BLENAME_FROM_QRCODE";
    private static final String KEY_DEVICE_TYPE = "DEVICE_TYPE_INDEX";
    private static final String KEY_PROTOCOL = "PROTOCOL_FROM_QRCODE";
    private static final String KEY_SOURCE = "START_SPORT_SOURCE";
    private static final int LRU_CACHE_SIZE = 1048576;
    private static final int MSG_UPDATE_DEVICE_INFO = 2;
    private static final int MSG_UPDATE_HISTORY_DATA = 1;
    private static final int MSG_UPDATE_RESOURCE_FILE = 0;
    private static final String OPCODE_DELETE = "Delete";
    private static final int PLURAL_NUMBER = 2;
    private static final int SINGULAR_NUMBER = 1;
    private static final String TAG = "PDSPORT_DeviceIntroduction";
    private static final int TEXT_SIZE = 30;
    private static final int UNIT_TRANS_DISTANCE_PRECISION = 2;
    private static final int UNIT_TRANS_MILL_MIN = 60000;
    private static final int UNIT_TRANS_M_KM = 1000;
    private ImageView mDeviceImageIv;
    private View mDeviceInfoLayout;
    private HealthTextView mDeviceInfoTv;
    protected View mDeviceManualsLayout;
    protected String mDeviceName;
    protected IndoorEquipManagerApi mIndoorEquipManagerApi;
    private HealthTextView mLastTimeTv;
    private HealthTextView mLastValueTv;
    private LinearLayout mMarketingLayout;
    protected LinearLayout mModeLayout;
    protected HealthTextView mModeTv;
    protected HealthTextView mModeValueTv;
    protected SportDeviceDataManager mSportDataManager;
    protected HealthTextView mStartTv;
    private CustomTextAlertDialog mUnbindDialog;
    protected ArrayList<String> mMoreMenuList = new ArrayList<>();
    private LruCache<String, Bitmap> mBitmapLruCache = new LruCache<>(1048576);
    private ArrayList<String> mImagePathList = new ArrayList<>(10);
    private ArrayList<String> mTextList = new ArrayList<>(10);
    private SportDeviceHandler mSportDeviceHandler = new SportDeviceHandler();
    private String mMainTitleStr = "";

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mNeedOpenBlueTooth = false;
        ResourceManager.e().e(this);
        LogUtil.a(TAG, "in onCreate");
        if (getArguments() != null) {
            this.mProductId = getArguments().getString("productId");
            ContentValues contentValues = (ContentValues) getArguments().getParcelable("commonDeviceInfo");
            if (contentValues != null) {
                this.mProductId = contentValues.getAsString("productId");
                this.mUniqueId = contentValues.getAsString("uniqueId");
                this.mDeviceName = contentValues.getAsString("name");
            }
            SportDeviceDataManager sportDeviceDataManager = new SportDeviceDataManager(this.mProductId, this.mUniqueId, this);
            this.mSportDataManager = sportDeviceDataManager;
            this.mProductInfo = sportDeviceDataManager.d();
        }
        this.mIndoorEquipManagerApi = (IndoorEquipManagerApi) Services.c("PluginLinkIndoorEquip", IndoorEquipManagerApi.class);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (layoutInflater == null) {
            LogUtil.a(TAG, "onCreateView inflater is null");
            FragmentActivity activity = getActivity();
            if (activity != null) {
                activity.finish();
            }
            return null;
        }
        LogUtil.a(TAG, "in onCreateView");
        ViewGroup viewGroup2 = super.onCreateView(layoutInflater, viewGroup, bundle) instanceof ViewGroup ? (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle) : null;
        this.child = layoutInflater.inflate(R.layout.fragment_sport_device_introduction, viewGroup, false);
        if (viewGroup2 != null) {
            viewGroup2.addView(this.child);
            initView(this.child);
        }
        initTitleBar();
        if (!TextUtils.isEmpty(this.mUniqueId)) {
            ((HonourDeviceConstantsApi) Services.c("PluginDevice", HonourDeviceConstantsApi.class)).setDevicesUseTime(this.mUniqueId);
        }
        return viewGroup2;
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initUxConfig(new czb(this.mProductId).c());
    }

    protected void initView(View view) {
        this.mDeviceImageIv = (ImageView) view.findViewById(R.id.iv_device_image);
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.tv_device_start);
        this.mStartTv = healthTextView;
        healthTextView.setOnClickListener(this);
        this.mLastTimeTv = (HealthTextView) view.findViewById(R.id.htv_device_last_time);
        this.mLastValueTv = (HealthTextView) view.findViewById(R.id.htv_device_last_value);
        View findViewById = view.findViewById(R.id.layout_device_manuals);
        this.mDeviceManualsLayout = findViewById;
        findViewById.setOnClickListener(this);
        View findViewById2 = view.findViewById(R.id.layout_device_info);
        this.mDeviceInfoLayout = findViewById2;
        findViewById2.setOnClickListener(this);
        this.mDeviceInfoTv = (HealthTextView) view.findViewById(R.id.tv_device_info);
        this.mModeLayout = (LinearLayout) view.findViewById(R.id.sport_mode_desc_layout);
        this.mModeTv = (HealthTextView) view.findViewById(R.id.sport_mode_tv);
        this.mModeValueTv = (HealthTextView) view.findViewById(R.id.sport_mode_value_tv);
        this.mMarketingLayout = (LinearLayout) view.findViewById(R.id.introduction_marketing);
        initMarketing();
    }

    protected void initUxConfig(czd czdVar) {
        if (czdVar != null) {
            this.mMarketingLayout.setVisibility(czdVar.f() ? 0 : 8);
            if (!czdVar.d().c()) {
                return;
            }
        }
        this.mMoreMenuList.add(getResources().getString(R.string.IDS_device_wear_home_delete_device));
    }

    @Override // com.huawei.health.device.manager.ResourceFileListener
    public void onResult(int i, String str) {
        LogUtil.c(TAG, "resultCode = " + i + " resultValue = " + str);
        if (TextUtils.isEmpty(str) || i == -1) {
            LogUtil.h(TAG, "ProResourceFile load failure");
            return;
        }
        if (i == 200) {
            LogUtil.a(TAG, "ProResourceFile load success");
        } else if (i == 300) {
            LogUtil.a(TAG, "GET FILE SIZE SUCCESS");
        } else {
            LogUtil.h(TAG, "unhandled resultCode = ", Integer.valueOf(i));
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (getActivity() == null) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (nsn.o()) {
            LogUtil.a(TAG, "click too fast");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view == this.mStartTv) {
            LogUtil.c(TAG, "onClick start training.");
            startIndoorEquipActivity();
        } else if (view == this.mDeviceManualsLayout) {
            LogUtil.c(TAG, "onClick device manuals");
            startUsageDescriptionActivity();
        } else if (view == this.mDeviceInfoLayout || view == this.mDeviceInfoTv) {
            LogUtil.c(TAG, "onClick device info, unspported yet");
        } else {
            LogUtil.c(TAG, "onClick unknown view");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void startUsageDescriptionActivity() {
        final String a2 = this.mSportDataManager.a();
        if (TextUtils.isEmpty(a2)) {
            LogUtil.h(TAG, "H5 url is empty, showing default descriptions");
            Intent intent = new Intent(this.mainActivity, (Class<?>) DeviceUsageDescriptionActivity.class);
            intent.putExtra("productId", this.mProductId);
            startActivity(intent);
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.SportDeviceIntroductionFragment$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                SportDeviceIntroductionFragment.this.m344xe1f483ef(a2);
            }
        });
    }

    /* renamed from: lambda$startUsageDescriptionActivity$1$com-huawei-health-ecologydevice-ui-measure-fragment-SportDeviceIntroductionFragment, reason: not valid java name */
    /* synthetic */ void m344xe1f483ef(final String str) {
        String countryCode = LoginInit.getInstance(BaseApplication.getContext()).getCountryCode(null);
        if (TextUtils.isEmpty(countryCode)) {
            countryCode = "SG";
        }
        final String noCheckUrl = GRSManager.a(BaseApplication.getContext()).getNoCheckUrl("domainTipsResDbankcdn", countryCode);
        if (this.mainActivity == null) {
            LogUtil.h(TAG, "mainActivity is null");
        } else if (TextUtils.isEmpty(noCheckUrl)) {
            LogUtil.h(TAG, "url is empty");
        } else {
            LogUtil.c(TAG, "url:", noCheckUrl, str);
            this.mainActivity.runOnUiThread(new Runnable() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.SportDeviceIntroductionFragment$$ExternalSyntheticLambda7
                @Override // java.lang.Runnable
                public final void run() {
                    SportDeviceIntroductionFragment.this.m343x2a08166e(noCheckUrl, str);
                }
            });
        }
    }

    /* renamed from: lambda$startUsageDescriptionActivity$0$com-huawei-health-ecologydevice-ui-measure-fragment-SportDeviceIntroductionFragment, reason: not valid java name */
    /* synthetic */ void m343x2a08166e(String str, String str2) {
        Intent intent = new Intent(this.mainActivity, (Class<?>) WebViewActivity.class);
        intent.putExtra("WebViewActivity.REQUEST_URL_KEY", str + str2);
        intent.putExtra(Constants.JUMP_MODE_KEY, 4);
        this.mainActivity.startActivity(intent);
    }

    private void initTitleBar() {
        this.mCustomTitleBar.setRightButtonVisibility(0);
        Resources resources = BaseApplication.getContext().getResources();
        if (resources != null) {
            this.mCustomTitleBar.setTitleBarBackgroundColor(resources.getColor(R.color._2131299340_res_0x7f090c0c));
        }
        this.mCustomTitleBar.setRightButtonDrawable(this.mainActivity.getResources().getDrawable(R.drawable._2131430194_res_0x7f0b0b32), nsf.h(R.string._2130850635_res_0x7f02334b));
        this.mCustomTitleBar.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.SportDeviceIntroductionFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportDeviceIntroductionFragment.this.m340xfe15e009(view);
            }
        });
    }

    /* renamed from: lambda$initTitleBar$2$com-huawei-health-ecologydevice-ui-measure-fragment-SportDeviceIntroductionFragment, reason: not valid java name */
    /* synthetic */ void m340xfe15e009(View view) {
        showMoreDeviceMenu();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void showMoreDeviceMenu() {
        if (this.mainActivity == null || this.mCustomTitleBar == null) {
            LogUtil.h(TAG, "mainActivity or mCustomTitleBar is null");
        } else {
            new PopViewList(this.mainActivity, this.mCustomTitleBar, this.mMoreMenuList).e(new PopViewList.PopViewClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.SportDeviceIntroductionFragment$$ExternalSyntheticLambda3
                @Override // com.huawei.ui.commonui.popupview.PopViewList.PopViewClickListener
                public final void setOnClick(int i) {
                    SportDeviceIntroductionFragment.this.handleMoreMenuClick(i);
                }
            });
        }
    }

    protected void handleMoreMenuClick(int i) {
        if (TextUtils.equals(this.mMoreMenuList.get(i), getResources().getString(R.string.IDS_device_wear_home_delete_device))) {
            unbindHaveBindingDevice();
        } else {
            LogUtil.a(TAG, "showUnbindDeviceMenu is default");
        }
    }

    private void initMarketing() {
        try {
            int parseInt = Integer.parseInt(getMarketingPositionId());
            final MarketingApi marketingApi = (MarketingApi) Services.c("FeatureMarketing", MarketingApi.class);
            Task<Map<Integer, ResourceResultInfo>> resourceResultInfo = marketingApi.getResourceResultInfo(parseInt);
            resourceResultInfo.addOnSuccessListener(new OnSuccessListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.SportDeviceIntroductionFragment$$ExternalSyntheticLambda4
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    SportDeviceIntroductionFragment.this.m339x535d321b(marketingApi, (Map) obj);
                }
            });
            resourceResultInfo.addOnFailureListener(new OnFailureListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.SportDeviceIntroductionFragment$$ExternalSyntheticLambda5
                @Override // com.huawei.hmf.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    SportDeviceIntroductionFragment.lambda$initMarketing$4(exc);
                }
            });
        } catch (NumberFormatException unused) {
            LogUtil.b(TAG, "error Marketing PositionId");
        }
    }

    /* renamed from: lambda$initMarketing$3$com-huawei-health-ecologydevice-ui-measure-fragment-SportDeviceIntroductionFragment, reason: not valid java name */
    /* synthetic */ void m339x535d321b(MarketingApi marketingApi, Map map) {
        if (this.mMarketingLayout == null) {
            LogUtil.h(TAG, "initMarketing mMarketingLayout is null");
            return;
        }
        Iterator<View> it = marketingApi.getMarketingViewList(getContext(), marketingApi.filterMarketingRules((Map<Integer, ResourceResultInfo>) map)).iterator();
        while (it.hasNext()) {
            this.mMarketingLayout.addView(it.next());
        }
    }

    static /* synthetic */ void lambda$initMarketing$4(Exception exc) {
        Object[] objArr = new Object[2];
        objArr[0] = "initMarketing onFailure ";
        objArr[1] = exc == null ? "" : exc.getMessage();
        LogUtil.b(TAG, objArr);
    }

    private String getMarketingPositionId() {
        return ResourceManager.e().d(this.mProductId).c("marketing_position_id");
    }

    private void unbindHaveBindingDevice() {
        LogUtil.a(TAG, "enter unBindHaveBindDevice");
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, dks.e(this.mProductId, this.mSportDataManager.e()));
        showBluetoothUnBindDialog(hashMap);
    }

    private void showBluetoothUnBindDialog(final Map<String, Object> map) {
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(getActivity()).b(R.string.IDS_hw_health_wear_connect_device_unpair_button).d(R.string.IDS_unbind_device_wear_home).cyU_(R.string.IDS_hw_health_wear_connect_device_unpair_button, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.SportDeviceIntroductionFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportDeviceIntroductionFragment.this.m341x1ddd2316(map, view);
            }
        }).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.SportDeviceIntroductionFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportDeviceIntroductionFragment.this.m342xd5c99097(view);
            }
        }).a();
        this.mUnbindDialog = a2;
        a2.setCancelable(false);
        this.mUnbindDialog.show();
    }

    /* renamed from: lambda$showBluetoothUnBindDialog$5$com-huawei-health-ecologydevice-ui-measure-fragment-SportDeviceIntroductionFragment, reason: not valid java name */
    /* synthetic */ void m341x1ddd2316(Map map, View view) {
        CustomTextAlertDialog customTextAlertDialog = this.mUnbindDialog;
        if (customTextAlertDialog != null) {
            customTextAlertDialog.dismiss();
            this.mUnbindDialog = null;
            if (unBindDeviceUniversal(this.mProductId, this.mUniqueId)) {
                unBindDeviceSuccess(map);
            }
        } else {
            LogUtil.a(TAG, "setPositiveButton mUnbindDialog = null");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: lambda$showBluetoothUnBindDialog$6$com-huawei-health-ecologydevice-ui-measure-fragment-SportDeviceIntroductionFragment, reason: not valid java name */
    /* synthetic */ void m342xd5c99097(View view) {
        CustomTextAlertDialog customTextAlertDialog = this.mUnbindDialog;
        if (customTextAlertDialog != null) {
            customTextAlertDialog.dismiss();
            this.mUnbindDialog = null;
        } else {
            LogUtil.a(TAG, "setNegativeButton mUnbindDialog = null");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ProductFragment
    protected void autoConnectDevice() {
        LogUtil.a(TAG, "connectDevice");
    }

    protected void unBindDeviceSuccess(Map<String, Object> map) {
        this.mIndoorEquipManagerApi.unPair(this.mUniqueId);
        ((HealthDeviceEntryApi) Services.c("PluginDevice", HealthDeviceEntryApi.class)).stopMeasure(this.mProductId, this.mUniqueId);
        ixx.d().d(cpp.a(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_UNBIND_SUCCEED_2060014.value(), map, 0);
        ResourceManager.e().f();
        popupFragment(null);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ProductFragment, com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        LogUtil.a(TAG, "onResume");
        if (getArguments() != null && getArguments().getString("operateCode", "").equals(OPCODE_DELETE)) {
            unbindHaveBindingDevice();
            getArguments().remove("operateCode");
        }
        loadResourceFile();
        this.mSportDataManager.i();
    }

    private void loadResourceFile() {
        Message obtainMessage = this.mSportDeviceHandler.obtainMessage();
        obtainMessage.what = 0;
        this.mSportDeviceHandler.sendMessage(obtainMessage);
    }

    @Override // com.huawei.health.ecologydevice.manager.SportDeviceDataManager.SportDataListener
    public void onNewLastData(long j, int i, int i2) {
        Message obtainMessage = this.mSportDeviceHandler.obtainMessage();
        obtainMessage.what = 1;
        obtainMessage.obj = Long.valueOf(j);
        obtainMessage.arg1 = i;
        obtainMessage.arg2 = i2;
        this.mSportDeviceHandler.sendMessage(obtainMessage);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ProductFragment
    protected void updateUIDisconnect() {
        LogUtil.a(TAG, "upDateUIDisconnect");
    }

    static class SportDeviceHandler extends Handler {
        private WeakReference<SportDeviceIntroductionFragment> mFragment;

        private SportDeviceHandler(SportDeviceIntroductionFragment sportDeviceIntroductionFragment) {
            this.mFragment = new WeakReference<>(sportDeviceIntroductionFragment);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            SportDeviceIntroductionFragment sportDeviceIntroductionFragment = this.mFragment.get();
            if (sportDeviceIntroductionFragment == null || !sportDeviceIntroductionFragment.isAdded() || sportDeviceIntroductionFragment.isRemoving() || sportDeviceIntroductionFragment.isDetached()) {
                return;
            }
            int i = message.what;
            if (i == 0) {
                sportDeviceIntroductionFragment.onLoadResourceFileSuccess();
            } else {
                if (i != 1) {
                    return;
                }
                sportDeviceIntroductionFragment.onNewLastHistoryData(((Long) message.obj).longValue(), message.arg1, message.arg2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onNewLastHistoryData(long j, int i, int i2) {
        LogUtil.c(TAG, "lastDataTime:", Long.valueOf(j), ", lastDataValue:", Integer.valueOf(i2), ", dataType:", Integer.valueOf(i));
        this.mLastTimeTv.setVisibility(0);
        this.mLastTimeTv.setText(nsj.a(j));
        this.mLastValueTv.setText(getSpannableString(i, i2));
    }

    private Spannable getSpannableString(int i, int i2) {
        Object valueOf;
        int i3;
        int i4;
        if (i == 1) {
            i4 = i2 <= 1000 ? 1 : 2;
            valueOf = Float.valueOf(new BigDecimal(i2 / 1000.0d).setScale(2, 4).floatValue());
            i3 = R.plurals._2130903239_res_0x7f0300c7;
        } else {
            int i5 = i2 <= 60000 ? 1 : 2;
            valueOf = Integer.valueOf(i2 / 60000);
            int i6 = i5;
            i3 = R.plurals._2130903270_res_0x7f0300e6;
            i4 = i6;
        }
        SpannableString spannableString = new SpannableString(getResources().getQuantityString(i3, i4, valueOf));
        nsi.cKH_(spannableString, String.valueOf(valueOf), new ForegroundColorSpan(getResources().getColor(R.color._2131299236_res_0x7f090ba4)));
        nsi.cKH_(spannableString, String.valueOf(valueOf), new AbsoluteSizeSpan(30, true));
        return spannableString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onLoadResourceFileSuccess() {
        parseResourceFile();
        parseDeviceImage();
        setTitle(getTitle(this.mProductId, this.mProductInfo));
    }

    private void parseResourceFile() {
        if (this.mProductInfo.e().size() == 0) {
            LogUtil.h(TAG, "productInfo.descriptions is empty");
            return;
        }
        LogUtil.c(TAG, "ResourceFile DeviceId:", this.mProductInfo.g());
        int size = this.mProductInfo.e().size();
        if (LanguageUtil.bc(getActivity())) {
            for (int i = size - 1; i >= 0; i--) {
                this.mImagePathList.add(dcq.b().a(this.mProductId, this.mProductInfo.e().get(i).e()));
                this.mTextList.add(dcx.d(this.mProductId, this.mProductInfo.e().get(i).c()));
            }
            return;
        }
        for (int i2 = 0; i2 < size; i2++) {
            this.mImagePathList.add(dcq.b().a(this.mProductId, this.mProductInfo.e().get(i2).e()));
            this.mTextList.add(dcx.d(this.mProductId, this.mProductInfo.e().get(i2).c()));
        }
    }

    private void parseDeviceImage() {
        Bitmap bitmap;
        this.mDeviceImageIv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        if (koq.b(this.mImagePathList)) {
            return;
        }
        String str = this.mImagePathList.get(0);
        if (this.mBitmapLruCache.get(str) == null) {
            bitmap = dcx.TK_(str);
            if (new File(str).exists() && bitmap != null) {
                LogUtil.a(TAG, "show cache Image");
                this.mBitmapLruCache.put(str, bitmap);
            }
        } else {
            LogUtil.h(TAG, "load exists Image");
            bitmap = this.mBitmapLruCache.get(str);
        }
        if (bitmap == null) {
            LogUtil.h(TAG, "bitmap is null");
        } else {
            this.mDeviceImageIv.setImageBitmap(bitmap);
        }
    }

    private String getTitle(String str, dcz dczVar) {
        String d = dcx.d(str, dczVar.n().b());
        this.mMainTitleStr = d;
        return d;
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        finishFragment();
        return false;
    }

    private void finishFragment() {
        ResourceManager.e().f();
        popupFragment(null);
    }

    protected void startIndoorEquipActivity() {
        startIndoorEquipActivity(null);
    }

    protected void startIndoorEquipActivity(Bundle bundle) {
        String c = this.mSportDataManager.c();
        if (TextUtils.isEmpty(c)) {
            LogUtil.h(TAG, "Unknown device type.");
            return;
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString(KEY_DEVICE_TYPE, c);
        Intent createEquipConnectIntent = createEquipConnectIntent(bundle);
        LogUtil.a(TAG, "will start IndoorEquipConnectedActivity");
        gnm.aPB_(this.mainActivity, createEquipConnectIntent);
    }

    protected Intent createEquipConnectIntent(Bundle bundle) {
        Intent intent = new Intent();
        intent.putExtra(KEY_PROTOCOL, "2");
        String b = this.mSportDataManager.b();
        if (b != null) {
            intent.putExtra(KEY_BLE, b.replace(":", ""));
        }
        intent.putExtra(KEY_BLE_NAME, this.mDeviceName);
        intent.putExtra(KEY_SOURCE, "ScanBtnAPP");
        intent.putExtra("productId", this.mProductId);
        intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.indoorequip.activity.IndoorEquipConnectedActivity");
        intent.putExtras(bundle);
        return intent;
    }
}
