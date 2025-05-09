package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.controlcenter.featureability.sdk.IAuthCallback;
import com.huawei.controlcenter.featureability.sdk.IConnectCallback;
import com.huawei.controlcenter.featureability.sdk.model.ExtraParams;
import com.huawei.harmonyos.interwork.base.ability.IAbilityStartCallback;
import com.huawei.harmonyos.interwork.base.ability.IInitCallBack;
import com.huawei.harmonyos.interwork.base.bundle.AbilityInfo;
import com.huawei.harmonyos.interwork.base.bundle.ElementName;
import com.huawei.harmonyos.interwork.base.content.Intent;
import com.huawei.health.faservice.HopDeviceUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.onehop.fasdk.job.ResultCallback;
import com.huawei.onehop.fasdk.model.DeviceConnectState;
import com.huawei.watchface.videoedit.gles.Constant;
import health.compact.a.SystemProperties;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes3.dex */
public class dll {
    private dll() {
    }

    public static dll c() {
        return c.c;
    }

    public boolean d(Context context) {
        ReleaseLogUtil.e("BaseModule_HopManager", "isHopEnabled start executor");
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IllegalThreadStateException("time consuming, not support executor in main thread");
        }
        final boolean[] zArr = {false};
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        lsj.b(context, context.getPackageName(), new ResultCallback() { // from class: dlk
            @Override // com.huawei.onehop.fasdk.job.ResultCallback
            public final void onResult(String str, int i) {
                dll.a(countDownLatch, str, i);
            }
        }, new IAuthCallback.Stub() { // from class: dll.5
            @Override // com.huawei.controlcenter.featureability.sdk.IAuthCallback
            public void onAuthResult(boolean z) throws RemoteException {
                ReleaseLogUtil.e("BaseModule_HopManager", "onAuthResult = ", Boolean.valueOf(z));
                zArr[0] = z;
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            ReleaseLogUtil.d("BaseModule_HopManager", "isHopEnabled InterruptedException: ", e.getMessage());
        }
        ReleaseLogUtil.e("BaseModule_HopManager", "isHopEnabled result: ", Boolean.valueOf(zArr[0]));
        return zArr[0];
    }

    static /* synthetic */ void a(CountDownLatch countDownLatch, String str, int i) {
        ReleaseLogUtil.e("BaseModule_HopManager", "reqType:" + str + " ,resultCode:" + i);
        countDownLatch.countDown();
    }

    public int WI_(String str, IBinder iBinder, ExtraParams extraParams, IConnectCallback iConnectCallback) {
        int cad_ = lsj.cad_(str, iBinder, extraParams, iConnectCallback);
        ReleaseLogUtil.e("BaseModule_HopManager", "register executor result = ", Integer.valueOf(cad_));
        return cad_;
    }

    public boolean a(int i) {
        boolean e = lsj.e(i);
        ReleaseLogUtil.e("BaseModule_HopManager", "unregister FA,the result is: ", Boolean.valueOf(e));
        return e;
    }

    public String d(cwn cwnVar) {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        hashMap2.put("harmonyVersion", "2.0.0");
        hashMap.put("system", hashMap2);
        hashMap.put("groupType", "1");
        if (cwnVar != null) {
            hashMap.put("faFilter", cwnVar.toString());
        }
        hashMap.put("curComType", 4);
        HashMap hashMap3 = new HashMap();
        hashMap3.put("commonFilter", hashMap);
        HashMap hashMap4 = new HashMap();
        hashMap4.put(Constant.FILTER, hashMap3);
        hashMap4.put("isTurnOffRecommend", true);
        hashMap4.put("remoteAuthenticationDescription", null);
        hashMap4.put("remoteAuthenticationPicture", null);
        return HiJsonUtil.e(hashMap4);
    }

    public ExtraParams d(String[] strArr, String str, String str2, String str3) {
        ExtraParams extraParams = new ExtraParams();
        extraParams.setDevType(strArr);
        extraParams.setTargetPkgName(str);
        extraParams.setDescription(str2);
        extraParams.setJsonParams(str3);
        return extraParams;
    }

    public ExtraParams a(String str, String str2) {
        return d(new String[]{"09C"}, "com.huawei.ohos.healthtv.hmservice", str, str2);
    }

    public boolean c(int i, ExtraParams extraParams) {
        ReleaseLogUtil.e("BaseModule_HopManager", "start show DeviceList,the params is: ", extraParams);
        return lsj.c(i, extraParams);
    }

    public void d(String str, IInitCallBack iInitCallBack) {
        LogUtil.a("BaseModule_HopManager", "initDistributedEnvironment");
        try {
            bwh.b(str, iInitCallBack);
        } catch (bwi e) {
            ReleaseLogUtil.d("BaseModule_HopManager", "initDistributedEnvironment RemoteException: ", e.getMessage());
        } catch (IllegalArgumentException e2) {
            e = e2;
            ReleaseLogUtil.d("BaseModule_HopManager", "initDistributedEnvironment Exception: ", e.getMessage());
        } catch (IllegalStateException e3) {
            e = e3;
            ReleaseLogUtil.d("BaseModule_HopManager", "initDistributedEnvironment Exception: ", e.getMessage());
        } catch (SecurityException e4) {
            e = e4;
            ReleaseLogUtil.d("BaseModule_HopManager", "initDistributedEnvironment Exception: ", e.getMessage());
        }
    }

    public int a(Context context, String str, Intent intent) {
        LogUtil.a("BaseModule_HopManager", "startRemoteAbility");
        try {
            intent.setElement(new ElementName(str, "com.huawei.ohos.healthtv.hmservice", "com.huawei.ohos.healthtv.ability.HopControllerAbility"));
            final int[] iArr = {-1};
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            bwk.c(context, intent, new IAbilityStartCallback() { // from class: dlj
                @Override // com.huawei.harmonyos.interwork.base.ability.IAbilityStartCallback
                public final void onStartResult(int i) {
                    dll.e(iArr, countDownLatch, i);
                }
            });
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                ReleaseLogUtil.d("BaseModule_HopManager", "isHopEnabled InterruptedException: ", e.getMessage());
            }
            LogUtil.a("BaseModule_HopManager", "startAbility result: ", Integer.valueOf(iArr[0]));
            return iArr[0];
        } catch (IllegalArgumentException | IllegalStateException e2) {
            ReleaseLogUtil.d("BaseModule_HopManager", "startRemoteAbility Exception: ", e2.getMessage());
            return -1030;
        }
    }

    static /* synthetic */ void e(int[] iArr, CountDownLatch countDownLatch, int i) {
        LogUtil.a("BaseModule_HopManager", "startAbility value: ", Integer.valueOf(i));
        iArr[0] = i;
        countDownLatch.countDown();
    }

    public void b(String str, IInitCallBack iInitCallBack) {
        try {
            LogUtil.a("BaseModule_HopManager", "start to unInitDistributedEnvironment,the deviceId is: ", str);
            bwh.a(str, iInitCallBack);
        } catch (bwi e) {
            ReleaseLogUtil.d("BaseModule_HopManager", "unInitDistributedEnvironment RemoteException: ", e.getMessage());
        } catch (IllegalArgumentException e2) {
            e = e2;
            ReleaseLogUtil.d("BaseModule_HopManager", "unInitDistributedEnvironment Exception: ", e.getMessage());
        } catch (IllegalStateException e3) {
            e = e3;
            ReleaseLogUtil.d("BaseModule_HopManager", "unInitDistributedEnvironment Exception: ", e.getMessage());
        } catch (SecurityException e4) {
            e = e4;
            ReleaseLogUtil.d("BaseModule_HopManager", "unInitDistributedEnvironment Exception: ", e.getMessage());
        }
    }

    public boolean a(int i, String str, DeviceConnectState deviceConnectState) {
        ReleaseLogUtil.e("BaseModule_HopManager", "updateConnectStatus, the deviceId is: ", str, "the connectState is: ", Integer.valueOf(deviceConnectState.getState()));
        return lsj.d(i, str, deviceConnectState);
    }

    public boolean a(String str) {
        ReleaseLogUtil.e("BaseModule_HopManager", "device = ", str);
        ElementName elementName = new ElementName();
        elementName.setBundleName(BaseApplication.APP_PACKAGE_HEALTH_TV);
        elementName.setAbilityName("com.huawei.homevision.healthtv.ui.sport.FreeSportActivity");
        elementName.setDeviceId(str);
        Intent intent = new Intent();
        intent.setAction("com.huawei.activity.action.LauncherActivity");
        intent.setElement(elementName);
        List<AbilityInfo> d = bwk.d(intent);
        if (!koq.b(d)) {
            ReleaseLogUtil.e("BaseModule_HopManager", "abilityInfoList size ", Integer.valueOf(d.size()));
            for (AbilityInfo abilityInfo : d) {
                if (abilityInfo != null && BaseApplication.APP_PACKAGE_HEALTH_TV.equals(abilityInfo.getBundleName())) {
                    return true;
                }
            }
            ReleaseLogUtil.e("BaseModule_HopManager", "device ", str, " has no ability");
            return false;
        }
        ReleaseLogUtil.e("BaseModule_HopManager", "abilityInfoList empty");
        return false;
    }

    public boolean a(Context context) {
        boolean z;
        int b = SystemProperties.b("hw_mc.controlcenter.displaymode", 0);
        LogUtil.a("BaseModule_HopManager", "displayModeValue: ", Integer.valueOf(b));
        try {
            context.getPackageManager().getServiceInfo(new ComponentName("com.huawei.controlcenter", "com.huawei.controlcenter.fatransfer.service.FeatureAbilityRegisterService"), 0);
            z = true;
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.b("BaseModule_HopManager", "can not find getServiceInfo.");
            z = false;
        }
        return (b & 128) == 0 && z;
    }

    static class c {
        private static final dll c = new dll();
    }

    public void d() {
        try {
            Class.forName("com.huawei.hwdevicemanager.IInitCallback");
            HopDeviceUtil.d().b();
        } catch (ClassNotFoundException e) {
            LogUtil.b("BaseModule_HopManager", "releaseDeviceManager ", e.getMessage());
        }
    }

    public void a(UiCallback<List<?>> uiCallback) {
        try {
            Class.forName("com.huawei.hwdevicemanager.IInitCallback");
            HopDeviceUtil.d().b(uiCallback);
        } catch (ClassNotFoundException e) {
            uiCallback.onFailure(2, "the system is not support.");
            LogUtil.b("BaseModule_HopManager", "getAvailableFaDevice ", e.getMessage());
        }
    }
}
