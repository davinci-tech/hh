package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Parcelable;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSampleConfig;
import com.huawei.hihealth.HiSampleConfigKey;
import com.huawei.hihealth.HiSampleConfigProcessOption;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.linkage.ConfigLinstener;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.IBaseCallback;
import com.huawei.hwservicesmgr.LinkageDeviceCommandListener;
import defpackage.kgc;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class kgc {
    private kfy b = null;
    private int e = 0;

    /* renamed from: a, reason: collision with root package name */
    private final BroadcastReceiver f14358a = new AnonymousClass3();

    /* renamed from: kgc$3, reason: invalid class name */
    class AnonymousClass3 extends BroadcastReceiver {
        AnonymousClass3() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.a("LINKAGE_ReverseLinkageManager", "mBtChangedReceiver intent is ", intent.getAction());
            if (context == null) {
                LogUtil.h("LINKAGE_ReverseLinkageManager", "mBtChangedReceiver context is null.");
                return;
            }
            if (!"com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                ReleaseLogUtil.e("R_LINKAGE_ReverseLinkageManager", "");
            }
            Parcelable parcelableExtra = intent.getParcelableExtra("deviceinfo");
            if (!(parcelableExtra instanceof DeviceInfo)) {
                LogUtil.h("LINKAGE_ReverseLinkageManager", "mBtChangedReceiver DeviceInfo error");
                return;
            }
            final DeviceInfo deviceInfo = (DeviceInfo) parcelableExtra;
            if (deviceInfo.getRelationship() != null && "followed_relationship".equals(deviceInfo.getRelationship())) {
                LogUtil.h("LINKAGE_ReverseLinkageManager", "mBtChangedReceiver This device does not have the correspond capability.");
                return;
            }
            int deviceConnectState = deviceInfo.getDeviceConnectState();
            LogUtil.a("LINKAGE_ReverseLinkageManager", "mBtChangedReceiver devicename: ", deviceInfo.getDeviceName(), ", state is ", Integer.valueOf(deviceConnectState));
            if (deviceConnectState != 2) {
                return;
            }
            if (kgc.this.b != null) {
                kgc.this.d(new ConfigLinstener() { // from class: kgg
                    @Override // com.huawei.hwdevice.phoneprocess.mgr.linkage.ConfigLinstener
                    public final void onResult(int i, HiSampleConfig hiSampleConfig) {
                        kgc.AnonymousClass3.this.d(deviceInfo, i, hiSampleConfig);
                    }
                });
            } else {
                ReleaseLogUtil.e("R_LINKAGE_ReverseLinkageManager", "mBtChangedReceiver  null");
            }
        }

        /* synthetic */ void d(DeviceInfo deviceInfo, int i, HiSampleConfig hiSampleConfig) {
            LogUtil.a("LINKAGE_ReverseLinkageManager", "mBtChangedReceiver getSwichFromDb errorCode ", Integer.valueOf(i));
            if (i != 0) {
                return;
            }
            String configData = hiSampleConfig.getConfigData();
            LogUtil.a("LINKAGE_ReverseLinkageManager", "mBtChangedReceiver configData", configData);
            long modifiedTime = hiSampleConfig.getModifiedTime();
            try {
                int parseInt = Integer.parseInt(((nji) HiJsonUtil.e(configData, nji.class)).e());
                ReleaseLogUtil.e("R_LINKAGE_ReverseLinkageManager", "mBtChangedReceiver switchValue ", Integer.valueOf(parseInt), ", time ", Long.valueOf(modifiedTime));
                kgc.this.b.b(deviceInfo, parseInt, modifiedTime);
            } catch (Exception unused) {
                ReleaseLogUtil.e("R_LINKAGE_ReverseLinkageManager", "mBtChangedReceiver sendConfig exception");
            }
        }
    }

    /* loaded from: classes5.dex */
    static class b {
        private static final kgc e = new kgc();
    }

    private void e() {
        IntentFilter intentFilter = new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        BroadcastManagerUtil.bFC_(BaseApplication.getContext(), this.f14358a, intentFilter, LocalBroadcast.c, null);
    }

    public void a(int i) {
        this.e = i;
    }

    public static kgc b() {
        return b.e;
    }

    public void d() {
        this.b = new kfy();
        cuk.b().registerDeviceSampleListener("hw.sport.linkage.app", this.b);
        e();
        c();
    }

    public void a(LinkageDeviceCommandListener linkageDeviceCommandListener) {
        kfy kfyVar = this.b;
        if (kfyVar == null) {
            ReleaseLogUtil.e("R_LINKAGE_ReverseLinkageManager", "registerDeviceCommandListener mLinkageEventChannel null");
        } else {
            kfyVar.c(linkageDeviceCommandListener);
        }
    }

    public void e(IBaseCallback iBaseCallback) {
        LogUtil.a("LINKAGE_ReverseLinkageManager", "registerLinkageDeviceDataListener: ", iBaseCallback);
        kfy kfyVar = this.b;
        if (kfyVar == null) {
            ReleaseLogUtil.e("R_LINKAGE_ReverseLinkageManager", "registerDeviceCommandListener mLinkageEventChannel null");
        } else {
            kfyVar.c(iBaseCallback);
        }
    }

    public void e(DeviceInfo deviceInfo, int i) {
        kfy kfyVar = this.b;
        if (kfyVar == null) {
            ReleaseLogUtil.e("R_LINKAGE_ReverseLinkageManager", "sendSampleEventToDevice mLinkageEventChannel null");
        } else {
            kfyVar.b(deviceInfo, i, this.e);
        }
    }

    public void c(DeviceInfo deviceInfo, int i, String str) {
        kfy kfyVar = this.b;
        if (kfyVar == null) {
            ReleaseLogUtil.e("R_LINKAGE_ReverseLinkageManager", "replySampleEventToDevice mLinkageEventChannel null");
        } else {
            kfyVar.e(deviceInfo, i, str, this.e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final ConfigLinstener configLinstener) {
        LogUtil.a("LINKAGE_ReverseLinkageManager", "getSwichFromDb start");
        HiSampleConfigProcessOption hiSampleConfigProcessOption = new HiSampleConfigProcessOption();
        ArrayList arrayList = new ArrayList();
        HiSampleConfigKey.Builder builder = new HiSampleConfigKey.Builder();
        builder.a(String.valueOf(0));
        builder.e(String.valueOf(0));
        builder.b("9002");
        builder.d("900200033");
        arrayList.add(new HiSampleConfigKey(builder));
        hiSampleConfigProcessOption.setSampleConfigKeyList(arrayList);
        HiHealthManager.d(com.huawei.haf.application.BaseApplication.e()).getSampleConfig(hiSampleConfigProcessOption, new HiDataReadResultListener() { // from class: kgc.4
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                ReleaseLogUtil.e("LINKAGE_ReverseLinkageManager", "getSwichFromDb errorCode: ", Integer.valueOf(i), ", data: ", obj);
                if (!koq.e(obj, HiSampleConfig.class)) {
                    LogUtil.h("LINKAGE_ReverseLinkageManager", "getSwichFromDb list getActiveGoals isListTypeMatch false ");
                    configLinstener.onResult(1, null);
                    return;
                }
                List list = (List) obj;
                if (koq.b(list)) {
                    LogUtil.h("LINKAGE_ReverseLinkageManager", "getSwichFromDb list is empty ");
                    configLinstener.onResult(1, null);
                } else {
                    configLinstener.onResult(0, (HiSampleConfig) list.get(0));
                }
            }
        });
    }

    private void c() {
        ArrayList arrayList = new ArrayList(10);
        ReleaseLogUtil.e("R_LINKAGE_ReverseLinkageManager", "subscribeLinkageConfig enter");
        arrayList.add(103);
        HiHealthNativeApi.a(com.huawei.haf.application.BaseApplication.e()).subscribeHiHealthData(arrayList, new AnonymousClass1());
    }

    /* renamed from: kgc$1, reason: invalid class name */
    class AnonymousClass1 implements HiSubscribeListener {
        AnonymousClass1() {
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            ReleaseLogUtil.e("R_LINKAGE_ReverseLinkageManager", "subscribeLinkageConfig onResult");
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            ReleaseLogUtil.e("R_LINKAGE_ReverseLinkageManager", "subscribeLinkageConfig changeKey ", str, " type is ", Integer.valueOf(i), " newValue is ", hiHealthData, " tim is ", Long.valueOf(j));
            if (i == 103 && "900200033".equals(str)) {
                kgc.this.d(new ConfigLinstener() { // from class: kgd
                    @Override // com.huawei.hwdevice.phoneprocess.mgr.linkage.ConfigLinstener
                    public final void onResult(int i2, HiSampleConfig hiSampleConfig) {
                        kgc.AnonymousClass1.this.d(i2, hiSampleConfig);
                    }
                });
            } else {
                ReleaseLogUtil.d("R_LINKAGE_ReverseLinkageManager", "mSubscribeListener unkonwn changeKey");
            }
        }

        /* synthetic */ void d(int i, HiSampleConfig hiSampleConfig) {
            LogUtil.h("LINKAGE_ReverseLinkageManager", "subscribeLinkageConfig errorCode", Integer.valueOf(i));
            if (i != 0) {
                return;
            }
            String configData = hiSampleConfig.getConfigData();
            LogUtil.h("LINKAGE_ReverseLinkageManager", "subscribeLinkageConfig configData", configData);
            int parseInt = Integer.parseInt(((nji) HiJsonUtil.e(configData, nji.class)).e());
            long modifiedTime = hiSampleConfig.getModifiedTime();
            ReleaseLogUtil.e("R_LINKAGE_ReverseLinkageManager", "subscribeLinkageConfig switchValue ", Integer.valueOf(parseInt), ", time ", Long.valueOf(modifiedTime));
            kgc.this.b.b(kgc.this.a(), parseInt, modifiedTime);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DeviceInfo a() {
        LogUtil.a("LINKAGE_ReverseLinkageManager", "phonservice getDeviceInfo ");
        DeviceInfo deviceInfo = null;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ACTIVE_MAIN_DEVICES, null, "LINKAGE_ReverseLinkageManager");
        if (deviceList == null) {
            ReleaseLogUtil.d("R_LINKAGE_ReverseLinkageManager", "getDeviceInfo deviceInfoList is null");
            return null;
        }
        Iterator<DeviceInfo> it = deviceList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            DeviceInfo next = it.next();
            if (!cvt.c(next.getProductType())) {
                deviceInfo = next;
                break;
            }
        }
        LogUtil.a("LINKAGE_ReverseLinkageManager", "getActiveDeviceInfo deviceInfo ", deviceInfo);
        return deviceInfo;
    }
}
