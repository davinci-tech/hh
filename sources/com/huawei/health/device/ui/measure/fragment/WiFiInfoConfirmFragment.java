package com.huawei.health.device.ui.measure.fragment;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.ui.measure.adapter.WifiListAdapter;
import com.huawei.health.device.wifi.interfaces.CommBaseCallback;
import com.huawei.health.device.wifi.lib.handler.StaticHandler;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.edittext.HealthIconTextLayout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import defpackage.cpa;
import defpackage.cpz;
import defpackage.ctz;
import defpackage.cub;
import defpackage.cug;
import defpackage.dcz;
import defpackage.dij;
import defpackage.jeg;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public class WiFiInfoConfirmFragment extends BaseFragment implements View.OnClickListener {
    private static final int BIND_RESULT_REQUEST_CODE = 1000;
    private static final int DEFAULT_POSITION = -1;
    private static final int GET_WIFI_LIST_MSG = 1001;
    private static final int HALF_INT = 2;
    private static final String[] PERMISSIONS = {"android.permission.ACCESS_COARSE_LOCATION"};
    private static final int SCAN_INTERVAL_TIME = 5000;
    private static final int SCAN_WIFI_LIST_MSG = 1002;
    private static final String STRING_PLACEHOLDER_INDEX_2 = "%2$s";
    private static final String STRING_PLACEHOLDER_INDEX_3 = "%3$s";
    private static final String TAG = "WiFiInfoConfirmFragment";
    private static final int UPDATE_WIFI_LIST_MSG = 1000;
    private static final int WIFI_CONNECT_FAILURE_MSG = 1004;
    private static final int WIFI_CONNECT_SUCCESS_MSG = 1003;
    private static final int WIFI_INFO_REFRESH_MSG = 1005;
    private static final int WIFI_PASSWORD_MAX_LENGTH = 64;
    private static final int WIFI_REFRESH_DELAY_TIME = 500;
    private ImageView mBackArrowImg;
    private LinearLayout mBackLayout;
    private HealthTextView mBackStepTv;
    private CustomAlertDialog.Builder mBuilder;
    private ctz mConnUtils;
    private String mDeviceSsid;
    private MyHandler mHandler;
    private HealthProgressBar mLoadingBar;
    private ImageView mNextArrowImg;
    private LinearLayout mNextLayout;
    private HealthTextView mNextStepTv;
    private dcz mProductInfo;
    private ScanResult mScanResult;
    private ImageButton mShowWifiBtn;
    private LinearLayout mWifiConnectLayout;
    private HealthTextView mWifiErrorTv;
    private HealthTextView mWifiFailMsg;
    private HealthTextView mWifiFailTitle;
    private WifiListAdapter mWifiListAdapter;
    private CustomAlertDialog mWifiListDialog;
    private ListView mWifiListView;
    private cug mWifiLrcCache;
    private HealthTextView mWifiNameTv;
    private HealthIconTextLayout mWifiPasswordLayout;
    private HealthTextView mWifiPromptTv;
    private LinearLayout mWifiSelectorLayout;
    private LinearLayout mWifiShowLayout;
    private List<ScanResult> mShowWifiList = new ArrayList(16);
    private List<ScanResult> mStorageWifiList = new ArrayList(16);
    private String mWifiSsid = "";
    private String mWifiPd = "";
    private int mPwdMode = 0;
    private String mProductId = "";
    private ContentValues mDeviceInfo = null;
    private String mDefaultSsid = "";
    private int mConfigMode = 1;
    private boolean mIs24Wifi = false;
    private boolean isOpenWifi = false;
    private BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.huawei.health.device.ui.measure.fragment.WiFiInfoConfirmFragment.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.a(WiFiInfoConfirmFragment.TAG, "onReceive action: ", intent.getAction());
            if ("android.net.wifi.STATE_CHANGE".equals(intent.getAction()) || "android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
                if (networkInfo == null || networkInfo.getState() == null) {
                    WiFiInfoConfirmFragment.this.mHandler.sendEmptyMessageDelayed(1004, 500L);
                    return;
                } else {
                    if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                        LogUtil.a(WiFiInfoConfirmFragment.TAG, "wifi wifi network connect.");
                        WiFiInfoConfirmFragment.this.mHandler.sendEmptyMessageDelayed(1005, 500L);
                        return;
                    }
                    return;
                }
            }
            if ("android.net.wifi.SCAN_RESULTS".equals(intent.getAction())) {
                LogUtil.a(WiFiInfoConfirmFragment.TAG, "get wifi scanResult list.");
                WiFiInfoConfirmFragment.this.mHandler.sendEmptyMessage(1001);
            } else {
                LogUtil.h(WiFiInfoConfirmFragment.TAG, "mReceiver default");
            }
        }
    };
    private CommBaseCallback mConnectCallback = new CommBaseCallback<WiFiInfoConfirmFragment>(this) { // from class: com.huawei.health.device.ui.measure.fragment.WiFiInfoConfirmFragment.2
        @Override // com.huawei.health.device.wifi.interfaces.CommBaseCallback
        public void onResult(WiFiInfoConfirmFragment wiFiInfoConfirmFragment, int i, String str, Object obj) {
            if (wiFiInfoConfirmFragment == null) {
                LogUtil.h(WiFiInfoConfirmFragment.TAG, "connect WiFiInfoConfirmFragment is null");
                return;
            }
            if (i == 101 || i == 103) {
                wiFiInfoConfirmFragment.mHandler.sendEmptyMessage(1004);
            } else if (i == 102) {
                wiFiInfoConfirmFragment.mHandler.sendEmptyMessage(1003);
            } else {
                LogUtil.h(WiFiInfoConfirmFragment.TAG, "connect wifi error is other code: ", Integer.valueOf(i));
            }
        }
    };

    static class MyHandler extends StaticHandler<WiFiInfoConfirmFragment> {
        MyHandler(WiFiInfoConfirmFragment wiFiInfoConfirmFragment) {
            super(wiFiInfoConfirmFragment);
        }

        @Override // com.huawei.health.device.wifi.lib.handler.StaticHandler
        public void handleMessage(WiFiInfoConfirmFragment wiFiInfoConfirmFragment, Message message) {
            if (wiFiInfoConfirmFragment.isDestroy()) {
            }
            if (!wiFiInfoConfirmFragment.isAdded()) {
                LogUtil.b(WiFiInfoConfirmFragment.TAG, "MyHandler mFragment is not add");
                return;
            }
            LogUtil.a(WiFiInfoConfirmFragment.TAG, "MyHandler what:", Integer.valueOf(message.what));
            switch (message.what) {
                case 1000:
                    wiFiInfoConfirmFragment.refreshView();
                    break;
                case 1001:
                    wiFiInfoConfirmFragment.getWifiListInfo();
                    break;
                case 1002:
                    cub.l(wiFiInfoConfirmFragment.mainActivity);
                    if (Build.VERSION.SDK_INT < 28) {
                        wiFiInfoConfirmFragment.mHandler.sendEmptyMessageDelayed(1002, 5000L);
                        break;
                    }
                    break;
                case 1003:
                    wiFiInfoConfirmFragment.mWifiFailTitle.setVisibility(8);
                    wiFiInfoConfirmFragment.mWifiFailMsg.setVisibility(8);
                    wiFiInfoConfirmFragment.mWifiPromptTv.setText(nsn.d(wiFiInfoConfirmFragment.mainActivity, R.string.IDS_device_wifi_confirm_prompt_msg));
                    wiFiInfoConfirmFragment.goToNextView();
                    break;
                case 1004:
                    wiFiInfoConfirmFragment.setConnectFailMsg();
                    break;
                case 1005:
                    wiFiInfoConfirmFragment.refreshWifiInfo();
                    break;
                default:
                    LogUtil.h("MyHandler what is other", new Object[0]);
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setConnectFailMsg() {
        this.mWifiFailMsg.setText(this.mainActivity.getResources().getString(R.string.IDS_device_wifi_connect_failure_msg_1, UnitUtil.e(1.0d, 1, 0)));
        this.mWifiFailTitle.setVisibility(0);
        this.mWifiFailMsg.setVisibility(0);
        this.mWifiPromptTv.setVisibility(0);
        handleWifiConnecting(false);
        setConnectFailClick();
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a(TAG, "onCreate");
        nsn.cLz_(this.mainActivity);
        this.mHandler = new MyHandler(this);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewGroup viewGroup2 = super.onCreateView(layoutInflater, viewGroup, bundle) instanceof ViewGroup ? (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle) : null;
        this.child = layoutInflater.inflate(R.layout.wifi_info_confirm_fragment_layout, viewGroup, false);
        setTitle(this.mainActivity.getResources().getString(R.string.IDS_device_mgr_device_pair_guide_note));
        initView();
        initData();
        if (viewGroup2 != null) {
            viewGroup2.addView(this.child);
        }
        return viewGroup2;
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onViewStateRestored(Bundle bundle) {
        super.onViewStateRestored(bundle);
        LogUtil.a(TAG, "onCreate");
        this.mWifiPasswordLayout.setText(this.mWifiPd);
    }

    private void initData() {
        LogUtil.a(TAG, "initData()");
        if (getArguments() != null) {
            this.mConfigMode = getArguments().getInt("config_mode", 1);
            this.mProductId = getArguments().getString("productId");
            ContentValues contentValues = (ContentValues) getArguments().getParcelable("commonDeviceInfo");
            this.mDeviceInfo = contentValues;
            if (contentValues != null) {
                this.mProductId = contentValues.getAsString("productId");
            }
            this.mDeviceSsid = getArguments().getString("deviceSsid");
        }
        dcz d = ResourceManager.e().d(this.mProductId);
        this.mProductInfo = d;
        if (d == null) {
            LogUtil.b(TAG, "initData mProductInfo is null");
            switchFragment(null);
            return;
        }
        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
        this.mainActivity.registerReceiver(this.mReceiver, intentFilter);
        if (this.mWifiLrcCache == null) {
            this.mWifiLrcCache = cug.c();
        }
        this.mConnUtils = ctz.c(this.mainActivity);
        initWifiInfo();
    }

    private void initWifiInfo() {
        LogUtil.a(TAG, "initWifiInfo()");
        this.mDefaultSsid = nsn.d(this.mainActivity, R.string.IDS_device_wifi_select_msg);
        int i = this.mConfigMode;
        if (i == 1 || i == 5) {
            this.mBackStepTv.setText(this.mainActivity.getResources().getString(R.string._2130841130_res_0x7f020e2a));
            initWifiForMulticast();
        } else if (i == 2) {
            this.mBackStepTv.setText(this.mainActivity.getResources().getString(R.string._2130841127_res_0x7f020e27));
            initWifiForSoftAp();
        } else {
            LogUtil.h(TAG, "initWifiInfo default");
        }
        this.mWifiSsid = this.mWifiNameTv.getText().toString();
        this.mWifiPd = this.mWifiPasswordLayout.getText().toString();
    }

    private void initWifiForMulticast() {
        LogUtil.a(TAG, "initWifiForMulticast()");
        String d = this.mWifiLrcCache.d("wifi_ssid_key");
        String d2 = this.mWifiLrcCache.d("wifi_pwd_key");
        boolean booleanValue = Boolean.valueOf(this.mWifiLrcCache.d("wifi_frequency")).booleanValue();
        boolean booleanValue2 = Boolean.valueOf(this.mWifiLrcCache.d("wifi_security")).booleanValue();
        if (cub.g(this.mainActivity)) {
            String e = cub.e(cub.c(this.mainActivity));
            if (!TextUtils.isEmpty(e)) {
                if (!TextUtils.isEmpty(d) && e.equals(d)) {
                    showLrcCacheWifiInfo(d, d2, booleanValue, booleanValue2);
                    return;
                } else {
                    showConnectedWifiView(e);
                    return;
                }
            }
            showSelectWifiView();
            return;
        }
        showSelectWifiView();
    }

    private void initWifiForSoftAp() {
        LogUtil.a(TAG, "initWifiForSoftAp()");
        String d = this.mWifiLrcCache.d("wifi_ssid_key");
        String d2 = this.mWifiLrcCache.d("wifi_pwd_key");
        boolean booleanValue = Boolean.valueOf(this.mWifiLrcCache.d("wifi_frequency")).booleanValue();
        boolean booleanValue2 = Boolean.valueOf(this.mWifiLrcCache.d("wifi_security")).booleanValue();
        if (TextUtils.isEmpty(d)) {
            if (cub.g(this.mainActivity)) {
                String e = cub.e(cub.c(this.mainActivity));
                if (!TextUtils.isEmpty(e)) {
                    showConnectedWifiView(e);
                    return;
                } else {
                    showSelectWifiView();
                    return;
                }
            }
            showSelectWifiView();
            return;
        }
        showLrcCacheWifiInfo(d, d2, booleanValue, booleanValue2);
    }

    private void showConnectedWifiView(String str) {
        this.mWifiNameTv.setText(str);
        this.mWifiPasswordLayout.setText("");
        ScanResult MA_ = cub.MA_(this.mainActivity, str);
        this.mScanResult = MA_;
        checkWifiLegal(MA_);
        checkWifiOpenMode(cub.i(this.mainActivity));
    }

    private void showSelectWifiView() {
        this.mHandler.sendEmptyMessage(1002);
        this.mWifiNameTv.setText(nsn.d(this.mainActivity, R.string.IDS_device_wifi_select_msg));
        this.mWifiPasswordLayout.setText("");
        showWifiErrorView(true);
        checkWifiOpenMode(false);
        isClickNextStep(false);
    }

    private void showLrcCacheWifiInfo(String str, String str2, boolean z, boolean z2) {
        LogUtil.a(TAG, "is24G: ", Boolean.valueOf(z), " isOpen: ", Boolean.valueOf(z2));
        this.mIs24Wifi = z;
        this.mWifiNameTv.setText(str);
        this.mWifiPasswordLayout.setText(str2);
        showWifiErrorView(z);
        checkWifiOpenMode(z2);
        isClickNextStep(this.mIs24Wifi);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshWifiInfo() {
        LogUtil.a(TAG, "refreshWifiInfo()");
        String e = cub.g(this.mainActivity) ? cub.e(cub.c(this.mainActivity)) : "";
        HealthTextView healthTextView = this.mWifiNameTv;
        if (healthTextView != null && this.mDefaultSsid.equals(healthTextView.getText().toString()) && !TextUtils.isEmpty(e)) {
            this.mWifiNameTv.setText(e);
            this.mWifiPasswordLayout.setText("");
            ScanResult MA_ = cub.MA_(this.mainActivity, e);
            this.mScanResult = MA_;
            checkWifiLegal(MA_);
            checkWifiOpenMode(cub.i(this.mainActivity));
        } else {
            LogUtil.a(TAG, "refreshWifiInfo() don't need to refresh wifi info.");
        }
        HealthTextView healthTextView2 = this.mWifiNameTv;
        if (healthTextView2 != null && this.mWifiPasswordLayout != null) {
            this.mWifiSsid = healthTextView2.getText().toString();
            this.mWifiPd = this.mWifiPasswordLayout.getText().toString();
        }
        this.mWifiListAdapter.b(getConnectWifiPosition());
        this.mWifiListAdapter.notifyDataSetChanged();
    }

    private void initView() {
        LogUtil.a(TAG, "initView()");
        findViews();
        initWifiListDialog();
        initWifiPasswordLayout();
        this.mWifiSelectorLayout.setOnClickListener(this);
        this.mBackLayout.setOnClickListener(this);
        this.mNextLayout.setOnClickListener(this);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        switchFragment(null);
        return super.onBackPressed();
    }

    private void initWifiPasswordLayout() {
        this.mWifiPasswordLayout.getEditText().setLongClickable(false);
        this.mWifiPasswordLayout.getEditText().setTextIsSelectable(false);
        this.mWifiPasswordLayout.getEditText().setFilters(new InputFilter[]{new InputFilter.LengthFilter(64)});
        this.mWifiPasswordLayout.getEditText().setImeOptions(268435456);
        this.mWifiPasswordLayout.getEditText().setCustomSelectionActionModeCallback(new ActionMode.Callback() { // from class: com.huawei.health.device.ui.measure.fragment.WiFiInfoConfirmFragment.3
            @Override // android.view.ActionMode.Callback
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                return false;
            }

            @Override // android.view.ActionMode.Callback
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override // android.view.ActionMode.Callback
            public void onDestroyActionMode(ActionMode actionMode) {
            }

            @Override // android.view.ActionMode.Callback
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }
        });
        this.mWifiPasswordLayout.getEditText().addTextChangedListener(new TextWatcher() { // from class: com.huawei.health.device.ui.measure.fragment.WiFiInfoConfirmFragment.4
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                WiFiInfoConfirmFragment.this.checkWifiInfoContent();
            }
        });
    }

    private void findViews() {
        this.mWifiShowLayout = (LinearLayout) this.child.findViewById(R.id.wifi_show_layout);
        this.mWifiConnectLayout = (LinearLayout) this.child.findViewById(R.id.wifi_connect_load_layout);
        this.mWifiSelectorLayout = (LinearLayout) this.child.findViewById(R.id.wifi_name_select_layout);
        this.mWifiPasswordLayout = (HealthIconTextLayout) this.child.findViewById(R.id.wifi_password_layout);
        this.mWifiNameTv = (HealthTextView) this.child.findViewById(R.id.wifi_name_tv);
        this.mWifiErrorTv = (HealthTextView) this.child.findViewById(R.id.wifi_network_error_prompt_tv);
        this.mWifiPromptTv = (HealthTextView) this.child.findViewById(R.id.wifi_connect_prompt_tv);
        this.mWifiFailTitle = (HealthTextView) this.child.findViewById(R.id.wifi_connect_prompt_title);
        this.mWifiFailMsg = (HealthTextView) this.child.findViewById(R.id.wifi_connect_prompt_msg_1);
        this.mBackStepTv = (HealthTextView) this.child.findViewById(R.id.back_step_tv);
        this.mNextStepTv = (HealthTextView) this.child.findViewById(R.id.next_step_tv);
        this.mShowWifiBtn = (ImageButton) this.child.findViewById(R.id.show_wifi_list_btn);
        this.mBackLayout = (LinearLayout) this.child.findViewById(R.id.back_step_button_layout);
        this.mNextLayout = (LinearLayout) this.child.findViewById(R.id.next_step_button_layout);
        this.mBackArrowImg = (ImageView) this.child.findViewById(R.id.back_step_arrow_img);
        this.mNextArrowImg = (ImageView) this.child.findViewById(R.id.next_step_arrow_img);
        HealthProgressBar healthProgressBar = (HealthProgressBar) this.child.findViewById(R.id.wifi_connect_loading_bar);
        this.mLoadingBar = healthProgressBar;
        healthProgressBar.setLayerType(1, null);
        HealthTextView healthTextView = (HealthTextView) this.child.findViewById(R.id.wifi_name_title);
        this.mWifiFailTitle.setText(nsn.d(this.mainActivity, R.string.IDS_device_wifi_connect_failure_title));
        this.mWifiPromptTv.setText(nsn.d(this.mainActivity, R.string.IDS_device_wifi_confirm_prompt_msg));
        healthTextView.setText(nsn.d(this.mainActivity, R.string.IDS_device_wifi_name_title_msg));
        this.mWifiNameTv.setText(nsn.d(this.mainActivity, R.string.IDS_device_wifi_select_msg));
        if (LanguageUtil.bc(this.mainActivity)) {
            this.mBackArrowImg.setBackgroundResource(R.drawable.wifi_device_arrow_right);
            this.mNextArrowImg.setBackgroundResource(R.drawable.wifi_device_arrow_left);
            this.mShowWifiBtn.setBackgroundResource(R.drawable.wifi_device_public_next_left);
        }
    }

    private void initWifiListDialog() {
        LogUtil.a(TAG, "initWifiListDialog()");
        View inflate = LayoutInflater.from(this.mainActivity).inflate(R.layout.scan_wifi_list_layout, (ViewGroup) null);
        this.mWifiListView = (ListView) nsy.cMd_(inflate, R.id.wifi_list);
        WifiListAdapter wifiListAdapter = new WifiListAdapter(this.mainActivity, this.mShowWifiList);
        this.mWifiListAdapter = wifiListAdapter;
        this.mWifiListView.setAdapter((ListAdapter) wifiListAdapter);
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(this.mainActivity);
        this.mBuilder = builder;
        builder.a(nsn.d(this.mainActivity, R.string.IDS_device_wifi_select_msg)).cyp_(inflate).cyn_(R.string._2130841130_res_0x7f020e2a, new DialogInterface.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.WiFiInfoConfirmFragment.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                LogUtil.a(WiFiInfoConfirmFragment.TAG, "setPositiveButton cancel");
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        }).e(false);
        this.mWifiListDialog = this.mBuilder.c();
        this.mWifiListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.WiFiInfoConfirmFragment.6
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                LogUtil.a(WiFiInfoConfirmFragment.TAG, "select wifi position: ", Integer.valueOf(i), " mShowWifiList size: ", Integer.valueOf(WiFiInfoConfirmFragment.this.mShowWifiList.size()));
                if (i < WiFiInfoConfirmFragment.this.mShowWifiList.size()) {
                    WiFiInfoConfirmFragment wiFiInfoConfirmFragment = WiFiInfoConfirmFragment.this;
                    wiFiInfoConfirmFragment.mScanResult = (ScanResult) wiFiInfoConfirmFragment.mShowWifiList.get(i);
                    if (WiFiInfoConfirmFragment.this.mScanResult != null) {
                        WiFiInfoConfirmFragment.this.mWifiListAdapter.b(i);
                        WiFiInfoConfirmFragment.this.mWifiListAdapter.notifyDataSetChanged();
                        if (!WiFiInfoConfirmFragment.this.mWifiSsid.equals(WiFiInfoConfirmFragment.this.mScanResult.SSID)) {
                            WiFiInfoConfirmFragment.this.mWifiNameTv.setText(WiFiInfoConfirmFragment.this.mScanResult.SSID);
                            WiFiInfoConfirmFragment.this.mWifiPasswordLayout.setText("");
                            WiFiInfoConfirmFragment wiFiInfoConfirmFragment2 = WiFiInfoConfirmFragment.this;
                            wiFiInfoConfirmFragment2.checkWifiLegal(wiFiInfoConfirmFragment2.mScanResult);
                            WiFiInfoConfirmFragment wiFiInfoConfirmFragment3 = WiFiInfoConfirmFragment.this;
                            wiFiInfoConfirmFragment3.checkWifiOpenMode(cub.MC_(wiFiInfoConfirmFragment3.mScanResult));
                        }
                        WiFiInfoConfirmFragment wiFiInfoConfirmFragment4 = WiFiInfoConfirmFragment.this;
                        wiFiInfoConfirmFragment4.mWifiSsid = wiFiInfoConfirmFragment4.mWifiNameTv.getText().toString();
                        WiFiInfoConfirmFragment wiFiInfoConfirmFragment5 = WiFiInfoConfirmFragment.this;
                        wiFiInfoConfirmFragment5.mWifiPd = wiFiInfoConfirmFragment5.mWifiPasswordLayout.getText().toString();
                        WiFiInfoConfirmFragment wiFiInfoConfirmFragment6 = WiFiInfoConfirmFragment.this;
                        wiFiInfoConfirmFragment6.mPwdMode = ctz.Ml_(wiFiInfoConfirmFragment6.mScanResult);
                        if (WiFiInfoConfirmFragment.this.mWifiListDialog.isShowing()) {
                            WiFiInfoConfirmFragment.this.mWifiListDialog.dismiss();
                        }
                        ViewClickInstrumentation.clickOnListView(adapterView, view, i);
                        return;
                    }
                    LogUtil.h(WiFiInfoConfirmFragment.TAG, "select wifi is null");
                    ViewClickInstrumentation.clickOnListView(adapterView, view, i);
                    return;
                }
                LogUtil.h(WiFiInfoConfirmFragment.TAG, "select wifi position is error");
                ViewClickInstrumentation.clickOnListView(adapterView, view, i);
            }
        });
    }

    private int calculateDialogHeight() {
        int Va_ = dij.Va_(this.mWifiListView);
        if (!(this.mainActivity.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR) instanceof WindowManager)) {
            LogUtil.h(TAG, "mainActivity.getSystemService(Context.WINDOW_SERVICE) not instanceof WindowManager");
            return 0;
        }
        int height = ((WindowManager) this.mainActivity.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)).getDefaultDisplay().getHeight() / 2;
        if (Va_ >= height) {
            Va_ = height;
        }
        return Va_ + (this.mShowWifiList.size() - 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshView() {
        LogUtil.a(TAG, "refreshView().");
        this.mShowWifiList.clear();
        this.mShowWifiList.addAll(this.mStorageWifiList);
        this.mWifiListAdapter.b(getConnectWifiPosition());
        this.mWifiListAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkWifiLegal(ScanResult scanResult) {
        if (scanResult != null) {
            this.mIs24Wifi = cub.MB_(scanResult);
        } else {
            this.mIs24Wifi = cub.f(this.mainActivity);
        }
        showWifiErrorView(this.mIs24Wifi);
    }

    private void showWifiErrorView(boolean z) {
        if (z) {
            this.mWifiErrorTv.setVisibility(8);
        } else {
            this.mWifiErrorTv.setVisibility(0);
        }
    }

    private boolean isClickNextStep(boolean z) {
        if (this.mainActivity == null) {
            return false;
        }
        if (z) {
            this.mNextLayout.setEnabled(true);
            this.mNextStepTv.setTextColor(this.mainActivity.getResources().getColor(R.color._2131299236_res_0x7f090ba4));
            if (LanguageUtil.bc(this.mainActivity)) {
                this.mNextArrowImg.setBackgroundResource(R.drawable.wifi_device_arrow_left);
            } else {
                this.mNextArrowImg.setBackgroundResource(R.drawable.wifi_device_arrow_right);
            }
            return true;
        }
        this.mNextLayout.setEnabled(false);
        this.mNextStepTv.setTextColor(this.mainActivity.getResources().getColor(R.color._2131299244_res_0x7f090bac));
        if (LanguageUtil.bc(this.mainActivity)) {
            this.mNextArrowImg.setBackgroundResource(R.drawable.wifi_device_arrow_disable_left);
        } else {
            this.mNextArrowImg.setBackgroundResource(R.drawable.wifi_device_arrow_disable_right);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkWifiInfoContent() {
        LogUtil.a(TAG, "checkWifiInfoContent() isOpenWifi: ", Boolean.valueOf(this.isOpenWifi));
        String obj = this.mWifiNameTv.getText().toString();
        String obj2 = this.mWifiPasswordLayout.getText().toString();
        if (!this.mIs24Wifi) {
            isClickNextStep(false);
        } else if (this.isOpenWifi) {
            if (!this.mDefaultSsid.equals(obj)) {
                isClickNextStep(true);
            } else {
                isClickNextStep(false);
            }
        } else if (!this.mDefaultSsid.equals(obj) && !TextUtils.isEmpty(obj2)) {
            isClickNextStep(true);
        } else {
            isClickNextStep(false);
        }
        setWifiInfoProperty(obj);
    }

    private void setWifiInfoProperty(String str) {
        if (this.mainActivity == null || this.mainActivity.getResources() == null) {
            LogUtil.h(TAG, "setWifiInfoProperty mainActivity or mainActivity.getResources() = null");
        } else if (!this.mDefaultSsid.equals(str)) {
            this.mWifiNameTv.setTextColor(this.mainActivity.getResources().getColor(R.color._2131299236_res_0x7f090ba4));
        } else {
            this.mWifiNameTv.setTextColor(this.mainActivity.getResources().getColor(R.color._2131299241_res_0x7f090ba9));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkWifiOpenMode(boolean z) {
        LogUtil.a(TAG, "checkWifiOpenMode isOpen: ", Boolean.valueOf(z));
        this.isOpenWifi = z;
        if (z) {
            this.mWifiPasswordLayout.setVisibility(8);
            this.mWifiPromptTv.setVisibility(8);
        } else {
            this.mWifiPasswordLayout.setVisibility(0);
            this.mWifiPromptTv.setVisibility(0);
        }
        checkWifiInfoContent();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.wifi_name_select_layout) {
            LogUtil.a(TAG, "onClick select layout");
            showWifiList();
        } else if (id == R.id.back_step_button_layout) {
            goBackPressed();
        } else if (id == R.id.next_step_button_layout) {
            goNextStep();
        } else {
            LogUtil.h(TAG, "onClick default");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void showWifiList() {
        CustomAlertDialog customAlertDialog;
        if (!this.mShowWifiList.isEmpty() && (customAlertDialog = this.mWifiListDialog) != null && !customAlertDialog.isShowing()) {
            this.mBuilder.a(calculateDialogHeight());
            this.mWifiListDialog.show();
        } else {
            this.mHandler.sendEmptyMessage(1002);
        }
    }

    private void goNextStep() {
        LogUtil.a(TAG, "goNextStep() is24GWifi: ", Boolean.valueOf(this.mIs24Wifi));
        this.mWifiSsid = this.mWifiNameTv.getText().toString();
        this.mWifiPd = this.mWifiPasswordLayout.getText().toString();
        if (this.isOpenWifi) {
            this.mWifiPd = "";
        }
        this.mWifiLrcCache.e();
        this.mWifiLrcCache.a("wifi_ssid_key", this.mWifiSsid);
        this.mWifiLrcCache.a("wifi_pwd_key", this.mWifiPd);
        this.mWifiLrcCache.a("wifi_frequency", String.valueOf(this.mIs24Wifi));
        this.mWifiLrcCache.a("wifi_security", String.valueOf(this.isOpenWifi));
        if (cub.g(this.mainActivity)) {
            if (!cub.d(this.mainActivity, this.mWifiSsid)) {
                connectNewWifi();
                return;
            } else {
                goToNextView();
                return;
            }
        }
        connectNewWifi();
    }

    private void connectNewWifi() {
        if (cpa.ae(this.mProductId)) {
            goToNextView();
            return;
        }
        LogUtil.a(TAG, "connectNewWifi() is not wsp product");
        handleWifiConnecting(true);
        ScanResult scanResult = this.mScanResult;
        if (scanResult != null) {
            this.mConnUtils.Mo_(scanResult, this.mWifiPd, true, this.mConnectCallback);
        } else {
            if (!TextUtils.isEmpty(this.mWifiSsid)) {
                int b = cub.b(this.mWifiSsid, this.mainActivity);
                this.mPwdMode = b;
                this.mConnUtils.c(this.mWifiSsid, this.mWifiPd, b, true, this.mConnectCallback);
                return;
            }
            LogUtil.h(TAG, "connectNewWifi() mWifiSsid is empty.");
        }
    }

    private void handleWifiConnecting(boolean z) {
        if (z) {
            this.mWifiShowLayout.setVisibility(8);
            this.mWifiConnectLayout.setVisibility(0);
        } else {
            this.mWifiShowLayout.setVisibility(0);
            this.mWifiConnectLayout.setVisibility(8);
        }
    }

    private void goBackPressed() {
        LogUtil.a(TAG, "goBackPressed");
        switchFragment(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void goToNextView() {
        LogUtil.c(TAG, "goToNextView()...mConfigMode: ", Integer.valueOf(this.mConfigMode));
        cpz.d(this.mConfigMode);
        Bundle bundle = new Bundle();
        bundle.putString("productId", this.mProductId);
        bundle.putString("outhName", this.mWifiSsid);
        bundle.putString("outhPd", this.mWifiPd);
        bundle.putString("deviceSsid", this.mDeviceSsid);
        bundle.putInt("config_mode", this.mConfigMode);
        ContentValues contentValues = new ContentValues();
        contentValues.put("productId", this.mProductId);
        contentValues.put("uniqueId", this.mDeviceSsid);
        bundle.putParcelable("commonDeviceInfo", contentValues);
        if (cpa.ae(this.mProductId)) {
            bundle.putInt(ParamConstants.Param.VIEW_TYPE, 7);
            Fragment hagridDeviceBindResultFragment = new HagridDeviceBindResultFragment();
            hagridDeviceBindResultFragment.setArguments(bundle);
            switchFragment(hagridDeviceBindResultFragment);
            return;
        }
        if (this.mProductInfo.d().size() > 0) {
            Fragment wiFiDeviceBindGuideFragment = new WiFiDeviceBindGuideFragment();
            wiFiDeviceBindGuideFragment.setArguments(bundle);
            switchFragment(wiFiDeviceBindGuideFragment);
            return;
        }
        int i = this.mConfigMode;
        if (i == 1 || i == 5) {
            Fragment wiFiDeviceBindResultFragment = new WiFiDeviceBindResultFragment();
            wiFiDeviceBindResultFragment.setArguments(bundle);
            switchFragment(wiFiDeviceBindResultFragment);
        } else {
            Fragment wiFiDeviceScanFragment = new WiFiDeviceScanFragment();
            wiFiDeviceScanFragment.setArguments(bundle);
            switchFragment(wiFiDeviceScanFragment);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getWifiListInfo() {
        LogUtil.a(TAG, "getWifiListInfo()");
        ThreadPoolManager.d().c(TAG, new Runnable() { // from class: com.huawei.health.device.ui.measure.fragment.WiFiInfoConfirmFragment.7
            @Override // java.lang.Runnable
            public void run() {
                WiFiInfoConfirmFragment.this.mStorageWifiList.clear();
                WiFiInfoConfirmFragment.this.mStorageWifiList.addAll(cub.c(WiFiInfoConfirmFragment.this.mainActivity, false));
                if (cub.g(WiFiInfoConfirmFragment.this.mainActivity)) {
                    String e = cub.e(cub.c(WiFiInfoConfirmFragment.this.mainActivity));
                    for (int i = 0; i < WiFiInfoConfirmFragment.this.mStorageWifiList.size(); i++) {
                        if (WiFiInfoConfirmFragment.this.mStorageWifiList.get(i) != null && e.equals(((ScanResult) WiFiInfoConfirmFragment.this.mStorageWifiList.get(i)).SSID) && i != 0) {
                            Collections.swap(WiFiInfoConfirmFragment.this.mStorageWifiList, i, 0);
                        }
                    }
                }
                WiFiInfoConfirmFragment.this.mHandler.sendEmptyMessage(1000);
            }
        });
    }

    private int getConnectWifiPosition() {
        int i = -1;
        if (cub.g(this.mainActivity)) {
            String e = cub.e(cub.c(this.mainActivity));
            for (int i2 = 0; i2 < this.mStorageWifiList.size(); i2++) {
                if (this.mStorageWifiList.get(i2) != null && e.equals(this.mStorageWifiList.get(i2).SSID)) {
                    i = i2;
                }
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDestroy() {
        FragmentActivity activity = getActivity();
        if (activity != null && !activity.isFinishing() && !activity.isDestroyed()) {
            return false;
        }
        LogUtil.h(TAG, "DeviceMainActivity is Destroyed");
        return true;
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        clearData();
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mConnUtils.d();
        if (this.mainActivity != null) {
            this.mainActivity.unregisterReceiver(this.mReceiver);
        }
    }

    private void clearData() {
        MyHandler myHandler = this.mHandler;
        if (myHandler != null) {
            myHandler.removeCallbacksAndMessages(null);
        }
        CustomAlertDialog customAlertDialog = this.mWifiListDialog;
        if (customAlertDialog != null) {
            customAlertDialog.dismiss();
            this.mWifiListDialog = null;
        }
        if (this.mWifiLrcCache != null) {
            this.mWifiLrcCache = null;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        LogUtil.a(TAG, "onRequestPermissionsResult(),permissions: ", Arrays.toString(strArr));
        jeg.d().d(strArr, iArr);
        if (PERMISSIONS[0].equals(strArr[0]) && iArr[0] == 0) {
            LogUtil.a(TAG, "onRequestPermissionsResult go to bind device");
            this.mHandler.sendEmptyMessage(1002);
        }
    }

    private void setConnectFailClick() {
        LogUtil.a(TAG, "setConnectFailClick()");
        String e = nsn.e(this.mainActivity, this.mainActivity.getResources().getString(R.string.IDS_device_wifi_connect_failure_msg_2, UnitUtil.e(2.0d, 1, 0), STRING_PLACEHOLDER_INDEX_2, STRING_PLACEHOLDER_INDEX_3));
        int indexOf = e.indexOf(STRING_PLACEHOLDER_INDEX_2);
        if (indexOf == -1) {
            LogUtil.b(TAG, "setConnectFailClick startIndex error");
            return;
        }
        int indexOf2 = e.indexOf(STRING_PLACEHOLDER_INDEX_3);
        if (indexOf2 == -1) {
            LogUtil.b(TAG, "setConnectFailClick lastIndex error");
            return;
        }
        if (indexOf2 < indexOf) {
            LogUtil.h(TAG, "setSpan source:", e, " startIndex：", Integer.valueOf(indexOf), " lastIndex:", Integer.valueOf(indexOf2));
            return;
        }
        int color = this.mainActivity.getResources().getColor(R.color._2131299387_res_0x7f090c3b);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(e);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(color), indexOf, indexOf2, 33);
        spannableStringBuilder.setSpan(new ClickableSpan() { // from class: com.huawei.health.device.ui.measure.fragment.WiFiInfoConfirmFragment.8
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                cub.m(WiFiInfoConfirmFragment.this.mainActivity);
                ViewClickInstrumentation.clickOnView(view);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setUnderlineText(false);
            }
        }, indexOf, indexOf2, 33);
        spannableStringBuilder.delete(indexOf, indexOf + 4);
        int indexOf3 = spannableStringBuilder.toString().indexOf(STRING_PLACEHOLDER_INDEX_3);
        if (indexOf3 == -1) {
            LogUtil.b(TAG, "setConnectFailClick spannableString lastIndex error");
            return;
        }
        spannableStringBuilder.delete(indexOf3, indexOf3 + 4);
        this.mWifiPromptTv.setMovementMethod(LinkMovementMethod.getInstance());
        this.mWifiPromptTv.setHighlightColor(this.mainActivity.getResources().getColor(android.R.color.transparent));
        this.mWifiPromptTv.setText(spannableStringBuilder);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.a(TAG, "onActivityResult: ", Integer.valueOf(i2), ",requestCode: ", Integer.valueOf(i));
        if (i2 == -1 && i == 1000 && intent != null) {
            setArguments(intent.getExtras());
        }
    }
}
