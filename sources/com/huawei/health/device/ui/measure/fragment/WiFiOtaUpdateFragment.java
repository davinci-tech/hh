package com.huawei.health.device.ui.measure.fragment;

import android.content.ContentValues;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.fragment.app.FragmentActivity;
import com.huawei.health.R;
import com.huawei.health.device.ui.util.RoundProgressView;
import com.huawei.health.device.wifi.interfaces.BaseCallbackInterface;
import com.huawei.health.device.wifi.interfaces.CommBaseCallbackInterface;
import com.huawei.health.device.wifi.lib.handler.StaticHandler;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.unite.DeviceServiceInfo;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceControlDataModelReq;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ceo;
import defpackage.cpw;
import defpackage.cpz;
import defpackage.csc;
import defpackage.csf;
import defpackage.ctk;
import defpackage.cuf;
import defpackage.jbs;
import defpackage.nrh;
import defpackage.nsn;
import defpackage.nsy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class WiFiOtaUpdateFragment extends BaseFragment implements View.OnClickListener {
    private static final float ALPHA_FLOAT = 0.3f;
    private static final float ALPHA_FLOAT_1 = 1.0f;
    private static final int COLLECTION_DEFAULT_SIZE = 16;
    private static final int MSG_UPDATE_END = 4;
    private static final int MSG_UPDATE_FAIL = 3;
    private static final int MSG_UPDATE_SUCCESS = 2;
    private static final int MSG_UPDATING = 1;
    private static final String PRODUCT_ID = "productId";
    private static final String TAG = "WiFiOtaUpdateFragment";
    private BaseCallbackInterface mBaseCallback;
    private ImageView mVersionLogo;
    private String mProductId = null;
    private String mUniqueId = null;
    private ContentValues mDeviceInfo = null;
    private String mNewVer = null;
    private String mReleaseNote = null;
    private String mNowVersion = null;
    private boolean mIsFromProductView = false;
    private HealthTextView mNowVersionText = null;
    private HealthTextView mNoNewVersionText = null;
    private HealthTextView mNewVersionText = null;
    private LinearLayout mLatestVersionLayout = null;
    private LinearLayout mNowVersionLayout = null;
    private WifiHandler mHandler = null;
    private RoundProgressView mRoundProgress = null;
    private HealthButton mCheckVersionUpdate = null;
    private ctk mWiFiDevice = null;

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewGroup viewGroup2 = super.onCreateView(layoutInflater, viewGroup, bundle) instanceof ViewGroup ? (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle) : null;
        this.child = layoutInflater.inflate(R.layout.wifi_upgrade_layout, viewGroup, false);
        initView();
        initData();
        if (viewGroup2 != null) {
            viewGroup2.addView(this.child);
        }
        return viewGroup2;
    }

    private void initView() {
        setTitle(this.mainActivity.getString(R.string.IDS_device_wear_home_device_ota_upgrade));
        this.mNowVersionText = (HealthTextView) nsy.cMd_(this.child, R.id.now_version_nodes_tv);
        this.mNoNewVersionText = (HealthTextView) nsy.cMd_(this.child, R.id.no_new_version_tv);
        this.mNewVersionText = (HealthTextView) nsy.cMd_(this.child, R.id.new_version_tv);
        this.mVersionLogo = (ImageView) nsy.cMd_(this.child, R.id.image_logo);
        this.mLatestVersionLayout = (LinearLayout) nsy.cMd_(this.child, R.id.latest_version_button);
        this.mRoundProgress = (RoundProgressView) nsy.cMd_(this.child, R.id.version_update_anim);
        this.mCheckVersionUpdate = (HealthButton) nsy.cMd_(this.child, R.id.check_version_update);
        LinearLayout linearLayout = (LinearLayout) nsy.cMd_(this.child, R.id.now_version_layout);
        this.mNowVersionLayout = linearLayout;
        linearLayout.setEnabled(false);
        this.mLatestVersionLayout.setOnClickListener(this);
        this.mCheckVersionUpdate.setOnClickListener(this);
        this.mLatestVersionLayout.setVisibility(0);
        this.mCheckVersionUpdate.setText(R.string._2130841549_res_0x7f020fcd);
    }

    private void initData() {
        this.mBaseCallback = new OtaStatusCallback(this);
        this.mHandler = new WifiHandler();
        if (getArguments() != null) {
            ContentValues contentValues = (ContentValues) getArguments().getParcelable("commonDeviceInfo");
            this.mDeviceInfo = contentValues;
            if (contentValues != null) {
                this.mProductId = contentValues.getAsString("productId");
                this.mUniqueId = this.mDeviceInfo.getAsString("uniqueId");
            }
            this.mNewVer = getArguments().getString("version");
            this.mNowVersion = getArguments().getString("cer_version");
            this.mReleaseNote = getArguments().getString("update_nodes");
            this.mNewVersionText.setText(this.mNewVer);
            this.mNowVersionText.setText(this.mNowVersion);
            this.mIsFromProductView = getArguments().getBoolean("fromProductView");
            if (!TextUtils.isEmpty(this.mProductId)) {
                if (ceo.d().d(this.mUniqueId, false) instanceof ctk) {
                    this.mWiFiDevice = (ctk) ceo.d().d(this.mUniqueId, false);
                }
            } else {
                cpw.a(false, TAG, "productId is empty, can't get WiFiDevice");
            }
            if (this.mWiFiDevice == null) {
                cpw.a(false, TAG, "initData WiFiDevice is empty");
                popupFragment(WiFiProductIntroductionFragment.class);
            } else {
                if ("a8ba095d-4123-43c4-a30a-0240011c58de".equals(this.mProductId)) {
                    this.mVersionLogo.setImageResource(R.drawable.wifi_device_honor_ota_logo);
                }
                isFromProductView();
            }
        }
    }

    private void isFromProductView() {
        if (this.mIsFromProductView) {
            Map<String, String> e = csc.d().e(this.mWiFiDevice.d());
            String str = e != null ? e.get("status") : null;
            if ("3".equals(str)) {
                Message obtain = Message.obtain();
                obtain.what = 4;
                this.mHandler.sendMessage(obtain);
                return;
            } else {
                if ("2".equals(str)) {
                    cpw.a(false, TAG, "start update");
                    this.mLatestVersionLayout.setEnabled(false);
                    cpz.h();
                    setCheckVersionUpdateEnable(1);
                    cuf.e(this.mainActivity.getApplicationContext()).c(this.mUniqueId, this.mBaseCallback);
                    this.mRoundProgress.b();
                    return;
                }
                cpw.d(false, TAG, "initData default");
                return;
            }
        }
        startUpgrade();
    }

    private void startUpgrade() {
        cpw.a(false, TAG, "start update");
        this.mLatestVersionLayout.setEnabled(false);
        cpz.h();
        setCheckVersionUpdateEnable(1);
        this.mRoundProgress.b();
        updatingVersion("CH");
    }

    private void updatingVersion(String str) {
        WifiDeviceControlDataModelReq wifiDeviceControlDataModelReq = new WifiDeviceControlDataModelReq();
        ArrayList arrayList = new ArrayList(16);
        HashMap hashMap = new HashMap(16);
        wifiDeviceControlDataModelReq.setDeviceServiceInfo(arrayList);
        ctk ctkVar = this.mWiFiDevice;
        if (ctkVar != null && ctkVar.b() != null) {
            wifiDeviceControlDataModelReq.setDevId(this.mWiFiDevice.b().a());
            cpw.c(false, TAG, "update device id: ", cpw.a(this.mWiFiDevice.b().a()));
        } else {
            cpw.c(false, TAG, "update device id is null");
        }
        hashMap.put("event", "2");
        hashMap.put("version", str);
        DeviceServiceInfo deviceServiceInfo = new DeviceServiceInfo();
        deviceServiceInfo.setData(hashMap);
        deviceServiceInfo.setSid("setDevParam");
        arrayList.add(deviceServiceInfo);
        cpw.a(false, TAG, "version: ", hashMap.get("version"), ", event: ", hashMap.get("event"), ", sid: ", deviceServiceInfo.getSid());
        jbs.a(this.mainActivity.getApplicationContext()).d(wifiDeviceControlDataModelReq, new ICloudOperationResult() { // from class: com.huawei.health.device.ui.measure.fragment.WiFiOtaUpdateFragment$$ExternalSyntheticLambda0
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str2, boolean z) {
                WiFiOtaUpdateFragment.this.m229xb17d466e((CloudCommonReponse) obj, str2, z);
            }
        });
    }

    /* renamed from: lambda$updatingVersion$0$com-huawei-health-device-ui-measure-fragment-WiFiOtaUpdateFragment, reason: not valid java name */
    /* synthetic */ void m229xb17d466e(CloudCommonReponse cloudCommonReponse, String str, boolean z) {
        cpw.a(false, TAG, "send update version. cloudCommonReponse: ", cloudCommonReponse, ", text: ", str, ", is success: ", Boolean.valueOf(z));
        Message obtain = Message.obtain();
        if (z) {
            obtain.what = 2;
        } else {
            obtain.what = 3;
        }
        this.mHandler.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDestroy() {
        FragmentActivity activity = getActivity();
        if (activity != null && !activity.isFinishing() && !activity.isDestroyed()) {
            return false;
        }
        cpw.e(false, TAG, "DeviceMainActivity is Destroyed");
        return true;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.latest_version_button) {
            Bundle bundle = new Bundle();
            bundle.putString("productId", this.mProductId);
            bundle.putString("version", this.mNewVer);
            bundle.putString("cer_version", this.mNowVersion);
            bundle.putString("update_nodes", this.mReleaseNote);
            WiFiVersionDetailsFragment wiFiVersionDetailsFragment = new WiFiVersionDetailsFragment();
            bundle.putParcelable("commonDeviceInfo", this.mDeviceInfo);
            wiFiVersionDetailsFragment.setArguments(bundle);
            switchFragment(wiFiVersionDetailsFragment);
        } else if (view.getId() == R.id.check_version_update) {
            if (view.getTag() != null) {
                if (nsn.e(view.getTag().toString()) == 1) {
                    cpw.a(false, TAG, "updating...");
                } else if (nsn.e(view.getTag().toString()) == 3) {
                    cpw.a(false, TAG, "update fail...");
                    startUpgrade();
                } else if (nsn.e(view.getTag().toString()) == 2) {
                    cpw.a(false, TAG, "update success...");
                    onBackPressed();
                } else {
                    cpw.d(false, TAG, "onClick default");
                }
            } else {
                startUpgrade();
            }
        } else {
            cpw.a(false, TAG, "--------no click------");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCheckVersionUpdateEnable(int i) {
        if (i == 1) {
            this.mNoNewVersionText.setText(R.string._2130841851_res_0x7f0210fb);
            this.mCheckVersionUpdate.setAlpha(ALPHA_FLOAT);
            this.mCheckVersionUpdate.setEnabled(false);
            this.mCheckVersionUpdate.setText(R.string._2130841463_res_0x7f020f77);
        } else if (i == 2) {
            this.mNoNewVersionText.setText(R.string._2130841497_res_0x7f020f99);
            this.mCheckVersionUpdate.setAlpha(1.0f);
            this.mCheckVersionUpdate.setEnabled(true);
            this.mCheckVersionUpdate.setText(R.string.IDS_device_show_complete);
        } else if (i == 3) {
            this.mNoNewVersionText.setText(R.string._2130841496_res_0x7f020f98);
            this.mCheckVersionUpdate.setAlpha(1.0f);
            this.mCheckVersionUpdate.setEnabled(true);
            this.mCheckVersionUpdate.setText(R.string._2130841467_res_0x7f020f7b);
        } else {
            cpw.a(false, TAG, "check is empty");
        }
        this.mCheckVersionUpdate.setTag(Integer.valueOf(i));
    }

    static class WifiHandler extends StaticHandler<WiFiOtaUpdateFragment> {
        private WifiHandler(WiFiOtaUpdateFragment wiFiOtaUpdateFragment) {
            super(wiFiOtaUpdateFragment);
        }

        @Override // com.huawei.health.device.wifi.lib.handler.StaticHandler
        public void handleMessage(WiFiOtaUpdateFragment wiFiOtaUpdateFragment, Message message) {
            if (wiFiOtaUpdateFragment.isDestroy()) {
                return;
            }
            if (!wiFiOtaUpdateFragment.isAdded()) {
                cpw.e(false, WiFiOtaUpdateFragment.TAG, "WifiHandler mFragment is not add");
                return;
            }
            cpw.a(false, WiFiOtaUpdateFragment.TAG, "WifiHandler what: " + message.what);
            int i = message.what;
            if (i == 1) {
                wiFiOtaUpdateFragment.mRoundProgress.c();
                wiFiOtaUpdateFragment.setCheckVersionUpdateEnable(1);
                return;
            }
            if (i == 2) {
                wiFiOtaUpdateFragment.checkUpdateResult();
                return;
            }
            if (i == 3) {
                wiFiOtaUpdateFragment.mLatestVersionLayout.setEnabled(true);
                wiFiOtaUpdateFragment.mRoundProgress.c();
                wiFiOtaUpdateFragment.setCheckVersionUpdateEnable(3);
            } else {
                if (i == 4) {
                    csc.d().c(wiFiOtaUpdateFragment.mWiFiDevice.d(), false);
                    wiFiOtaUpdateFragment.mLatestVersionLayout.setEnabled(true);
                    wiFiOtaUpdateFragment.mLatestVersionLayout.setVisibility(8);
                    wiFiOtaUpdateFragment.mNowVersionText.setText(wiFiOtaUpdateFragment.mNewVer);
                    wiFiOtaUpdateFragment.mRoundProgress.c();
                    wiFiOtaUpdateFragment.setCheckVersionUpdateEnable(2);
                    Map<String, String> e = csc.d().e(wiFiOtaUpdateFragment.mWiFiDevice.d());
                    e.put("status", "1");
                    csc.d().d(wiFiOtaUpdateFragment.mWiFiDevice.d(), e);
                    csf.c(wiFiOtaUpdateFragment.mWiFiDevice.d(), (CommBaseCallbackInterface) null);
                    return;
                }
                cpw.d(false, WiFiOtaUpdateFragment.TAG, "WifiHandler what is other");
            }
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        Bundle bundle = new Bundle();
        bundle.putString("productId", this.mProductId);
        WiFiProductIntroductionFragment wiFiProductIntroductionFragment = new WiFiProductIntroductionFragment();
        bundle.putParcelable("commonDeviceInfo", this.mDeviceInfo);
        wiFiProductIntroductionFragment.setArguments(bundle);
        switchFragment(wiFiProductIntroductionFragment);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkUpdateResult() {
        cuf.e(this.mainActivity.getApplicationContext()).e(this.mUniqueId, this.mBaseCallback);
    }

    class OtaStatusCallback implements BaseCallbackInterface {
        private WeakReference<WiFiOtaUpdateFragment> mActivity;

        private OtaStatusCallback(WiFiOtaUpdateFragment wiFiOtaUpdateFragment) {
            this.mActivity = new WeakReference<>(wiFiOtaUpdateFragment);
        }

        @Override // com.huawei.health.device.wifi.interfaces.BaseCallbackInterface
        public void onSuccess(Object obj) {
            if (isDestory() && obj != null && "3".equals(obj)) {
                Message obtain = Message.obtain();
                obtain.what = 4;
                WiFiOtaUpdateFragment.this.mHandler.sendMessage(obtain);
            }
        }

        private boolean isDestory() {
            WiFiOtaUpdateFragment wiFiOtaUpdateFragment;
            WeakReference<WiFiOtaUpdateFragment> weakReference = this.mActivity;
            return (weakReference == null || (wiFiOtaUpdateFragment = weakReference.get()) == null || wiFiOtaUpdateFragment.isDestory()) ? false : true;
        }

        @Override // com.huawei.health.device.wifi.interfaces.BaseCallbackInterface
        public void onFailure(int i) {
            Message obtain = Message.obtain();
            obtain.what = 3;
            WiFiOtaUpdateFragment.this.mHandler.sendMessage(obtain);
        }

        @Override // com.huawei.health.device.wifi.interfaces.BaseCallbackInterface
        public void onStatus(int i) {
            if (WiFiOtaUpdateFragment.this.mainActivity != null) {
                WiFiOtaUpdateFragment.this.mainActivity.runOnUiThread(new Runnable() { // from class: com.huawei.health.device.ui.measure.fragment.WiFiOtaUpdateFragment.OtaStatusCallback.1
                    @Override // java.lang.Runnable
                    public void run() {
                        nrh.b(WiFiOtaUpdateFragment.this.mainActivity, R.string.IDS_device_wifi_not_network);
                    }
                });
            }
            Message obtain = Message.obtain();
            obtain.what = 3;
            WiFiOtaUpdateFragment.this.mHandler.sendMessage(obtain);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDestory() {
        FragmentActivity activity = getActivity();
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            cpw.e(false, TAG, "DeviceMainActivity is Destroyed");
            return true;
        }
        if (!isAdded()) {
            cpw.e(false, TAG, "MyHandler mFragment is not add");
        }
        return false;
    }
}
