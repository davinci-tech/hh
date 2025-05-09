package com.huawei.hms.update.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import android.view.KeyEvent;
import com.huawei.appgallery.agd.api.AgdConstant;
import com.huawei.hms.activity.BridgeActivity;
import com.huawei.hms.activity.IBridgeActivityDelegate;
import com.huawei.hms.common.util.AGCUtils;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hms.support.hianalytics.HiAnalyticsUtils;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.AgHmsUpdateState;
import com.huawei.hms.utils.NetWorkUtil;
import com.huawei.hms.utils.PackageManagerHelper;
import com.huawei.hms.utils.RegionUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public abstract class AbsUpdateWizard implements IBridgeActivityDelegate {
    protected static final int APPTOUCH_REQUEST_CODE = 2007;
    protected static final int HIAPP_DL_REQUEST_CODE = 2006;
    protected static final int HIAPP_REQUEST_CODE = 2005;
    protected static final int HIAPP_WAP_REQUEST_CODE = 2004;
    public static final int RESULT_CANCELED = 13;
    public static final int RESULT_FAILURE = 8;
    public static final int RESULT_SUCCESS = 0;
    public static final int RESULT_TIMEOUT = 14;
    protected static final int SILENT_DOWNLOAD_REQUEST_CODE = 2000;
    protected static final int THIRD_PARTY_REQUEST_CODE = 2008;
    public IBridgeActivityDelegate mBridgeActivityDelegate;
    public WeakReference<Activity> mThisWeakRef;
    protected UpdateBean bean = null;
    protected AbstractDialog mLatestDialog = null;
    protected boolean needTransfer = false;
    protected int updateType = -1;
    protected String mPackageName = null;
    protected String mClientAppName = null;
    protected int mClientVersionCode = 0;
    protected String mClientAppId = null;

    public interface UpdateType {
        public static final int APPTOUCH = 7;
        public static final int HIAPP = 5;
        public static final int HIAPP_DL = 6;
        public static final int INIT = -1;
        public static final int INSTALL = 8;
        public static final int NONE = -2;
        public static final int SILENT = 0;
        public static final int THIRD_PARTY_MARKET = 9;
    }

    private String a(PackageManagerHelper packageManagerHelper) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(HiAnalyticsConstant.HaKey.BI_KEY_FINGERPRINT, packageManagerHelper.getPackageSigningCertificate(this.mPackageName));
        } catch (JSONException e) {
            HMSLog.e("AbsUpdateWizard", "put fingerprint in jsonObject error: " + e.getMessage());
        }
        return jSONObject.toString();
    }

    abstract void a(Class<? extends AbstractDialog> cls);

    protected void biReportEvent(int i, int i2) {
        if (!RegionUtils.isChinaROM(getActivity())) {
            HMSLog.i("AbsUpdateWizard", "not ChinaROM ");
            return;
        }
        Activity activity = getActivity();
        if (activity == null || activity.isFinishing()) {
            return;
        }
        PackageManagerHelper packageManagerHelper = new PackageManagerHelper(activity);
        boolean isPackageFreshInstall = packageManagerHelper.isPackageFreshInstall(this.mPackageName);
        SharedPreferences sharedPreferences = activity.getSharedPreferences(HiAnalyticsConstant.BI_TYPE_UPDATE, 0);
        if (sharedPreferences.getInt(HiAnalyticsConstant.SP_KEY_INSTALL_REPORTED, 0) == 1 && i == 0 && isPackageFreshInstall) {
            HMSLog.i("AbsUpdateWizard", "not need report");
            return;
        }
        String str = isPackageFreshInstall ? "1" : "2";
        int packageVersionCode = packageManagerHelper.getPackageVersionCode(this.mPackageName);
        int packageVersionCode2 = packageManagerHelper.getPackageVersionCode(activity.getPackageName());
        HashMap hashMap = new HashMap();
        hashMap.put("package", activity.getPackageName());
        hashMap.put(HiAnalyticsConstant.BI_KEY_PACKAGE_VER, String.valueOf(packageVersionCode2));
        hashMap.put(HiAnalyticsConstant.BI_KEY_TARGET_PACKAGE, this.mPackageName);
        hashMap.put(HiAnalyticsConstant.BI_KEY_TARGET_VER, String.valueOf(packageVersionCode));
        hashMap.put(HiAnalyticsConstant.BI_KEY_TARGET_FACTORY, Build.MANUFACTURER);
        hashMap.put(HiAnalyticsConstant.BI_KEY_TARGET_BRAND, Build.BRAND);
        hashMap.put("sdk_ver", String.valueOf(61300303));
        hashMap.put("app_id", AGCUtils.getAppId(activity));
        hashMap.put(HiAnalyticsConstant.BI_KEY_TRIGGER_API, "core.connnect");
        hashMap.put(HiAnalyticsConstant.BI_KEY_UPDATE_TYPE, String.valueOf(i2));
        hashMap.put(HiAnalyticsConstant.BI_KEY_NET_TYPE, String.valueOf(NetWorkUtil.getNetworkType(activity)));
        hashMap.put(HiAnalyticsConstant.BI_KEY_INSTALL_TYPE, str);
        hashMap.put("service", HiAnalyticsConstant.BI_VALUE_SDK_SERVICE);
        hashMap.put("result", a(i, i2));
        if (AgHmsUpdateState.getInstance().isUpdateHms()) {
            hashMap.put(HiAnalyticsConstant.SP_KEY_HMS_CORE_AUTH, a(packageManagerHelper));
        }
        HiAnalyticsUtils.getInstance().onEvent(activity, HiAnalyticsConstant.BI_TYPE_UPDATE, hashMap);
        if (i == 0 && isPackageFreshInstall) {
            sharedPreferences.edit().putInt(HiAnalyticsConstant.SP_KEY_INSTALL_REPORTED, 1).commit();
        }
    }

    protected void dismissDialog() {
        AbstractDialog abstractDialog = this.mLatestDialog;
        if (abstractDialog == null) {
            return;
        }
        try {
            abstractDialog.dismiss();
            this.mLatestDialog = null;
        } catch (IllegalStateException e) {
            HMSLog.e("AbsUpdateWizard", "In dismissDialog, Failed to dismiss the dialog." + e.getMessage());
        }
    }

    protected void finishBridgeActivity(int i, int i2) {
        Activity activity = getActivity();
        if (activity == null || activity.isFinishing()) {
            return;
        }
        biReportEvent(i, i2);
        if (i2 == 6 && i == 0) {
            HMSLog.i("AbsUpdateWizard", "HI_APP_DL update success, target version: " + this.mClientVersionCode);
            AgHmsUpdateState.getInstance().resetUpdateState();
        }
        Intent intent = new Intent();
        intent.putExtra(BridgeActivity.EXTRA_DELEGATE_CLASS_NAME, getClass().getName());
        intent.putExtra(BridgeActivity.EXTRA_RESULT, i);
        activity.setResult(-1, intent);
        activity.finish();
    }

    protected Activity getActivity() {
        WeakReference<Activity> weakReference = this.mThisWeakRef;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    protected boolean isUpdated(String str, int i) {
        Activity activity;
        return (TextUtils.isEmpty(str) || (activity = getActivity()) == null || activity.isFinishing() || new PackageManagerHelper(activity).getPackageVersionCode(str) < i) ? false : true;
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeActivityCreate(Activity activity) {
        if (activity == null || activity.isFinishing()) {
            HMSLog.e("AbsUpdateWizard", "activity is null or finishing");
            return;
        }
        this.mThisWeakRef = new WeakReference<>(activity);
        if (this.bean == null) {
            Intent intent = activity.getIntent();
            if (intent == null) {
                return;
            }
            try {
                this.bean = (UpdateBean) intent.getSerializableExtra(BridgeActivity.EXTRA_DELEGATE_UPDATE_INFO);
            } catch (Exception e) {
                HMSLog.e("AbsUpdateWizard", "get bean exception:" + e.getMessage());
            }
            if (this.bean == null) {
                return;
            }
        }
        this.mPackageName = this.bean.getClientPackageName();
        this.mClientAppName = this.bean.getClientAppName();
        this.mClientVersionCode = this.bean.getClientVersionCode();
        this.mClientAppId = this.bean.getClientAppId();
        this.mBridgeActivityDelegate = null;
        this.needTransfer = false;
        this.updateType = -1;
        ConfigChangeHolder.getInstance().setChanged(false);
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeActivityDestroy() {
        IBridgeActivityDelegate iBridgeActivityDelegate;
        this.mThisWeakRef = null;
        dismissDialog();
        if (!this.needTransfer || (iBridgeActivityDelegate = this.mBridgeActivityDelegate) == null) {
            return;
        }
        iBridgeActivityDelegate.onBridgeActivityDestroy();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeConfigurationChanged() {
        IBridgeActivityDelegate iBridgeActivityDelegate;
        if (this.needTransfer && (iBridgeActivityDelegate = this.mBridgeActivityDelegate) != null) {
            iBridgeActivityDelegate.onBridgeConfigurationChanged();
            return;
        }
        AbstractDialog abstractDialog = this.mLatestDialog;
        if (abstractDialog == null) {
            return;
        }
        Class<?> cls = abstractDialog.getClass();
        this.mLatestDialog.dismiss();
        this.mLatestDialog = null;
        ConfigChangeHolder.getInstance().setChanged(true);
        a((Class<? extends AbstractDialog>) cls);
    }

    public void onCancel(AbstractDialog abstractDialog) {
    }

    public void onDoWork(AbstractDialog abstractDialog) {
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onKeyUp(int i, KeyEvent keyEvent) {
        IBridgeActivityDelegate iBridgeActivityDelegate;
        if (!this.needTransfer || (iBridgeActivityDelegate = this.mBridgeActivityDelegate) == null) {
            return;
        }
        iBridgeActivityDelegate.onKeyUp(i, keyEvent);
    }

    protected boolean startNextWizard(boolean z) {
        Activity activity = getActivity();
        boolean z2 = false;
        if (activity == null) {
            return false;
        }
        ArrayList typeList = this.bean.getTypeList();
        if (typeList.size() > 0) {
            typeList.remove(0);
        }
        if (this.mBridgeActivityDelegate == null) {
            a(typeList);
        }
        if (this.mBridgeActivityDelegate != null) {
            HMSLog.i("AbsUpdateWizard", "<startNextWizard> " + this.mBridgeActivityDelegate.getClass().getSimpleName());
            z2 = true;
            this.needTransfer = true;
            this.bean.setTypeList(typeList);
            this.bean.setNeedConfirm(z);
            IBridgeActivityDelegate iBridgeActivityDelegate = this.mBridgeActivityDelegate;
            if (iBridgeActivityDelegate instanceof AbsUpdateWizard) {
                ((AbsUpdateWizard) iBridgeActivityDelegate).a(this.bean);
            }
            this.mBridgeActivityDelegate.onBridgeActivityCreate(activity);
        }
        return z2;
    }

    public static String getClassName(int i) {
        if (i == 0) {
            return SilentUpdateWizard.class.getName();
        }
        switch (i) {
            case 5:
                return HiappWizard.class.getName();
            case 6:
                return UpdateWizard.class.getName();
            case 7:
                return AppTouchWizard.class.getName();
            case 8:
                return ConfirmInstallWizard.class.getName();
            case 9:
                return ThirdPartyMarketWizard.class.getName();
            default:
                return "";
        }
    }

    private String a(int i, int i2) {
        String valueOf = String.valueOf(i);
        if (i2 == 0) {
            return AgdConstant.INSTALL_TYPE_DEFAULT + valueOf;
        }
        if (i2 == 5) {
            return "5000" + valueOf;
        }
        if (i2 == 6) {
            return "4000" + valueOf;
        }
        if (i2 == 8) {
            return "8000" + valueOf;
        }
        if (i2 != 9) {
            return valueOf;
        }
        return "9000" + valueOf;
    }

    private void a(ArrayList arrayList) {
        String className = (arrayList == null || arrayList.size() <= 0) ? null : getClassName(((Integer) arrayList.get(0)).intValue());
        if (className == null) {
            return;
        }
        try {
            this.mBridgeActivityDelegate = (IBridgeActivityDelegate) Class.forName(className).asSubclass(IBridgeActivityDelegate.class).newInstance();
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            HMSLog.e("AbsUpdateWizard", "getBridgeActivityDelegate error" + e.getMessage());
        }
    }

    private void a(UpdateBean updateBean) {
        this.bean = updateBean;
    }
}
