package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor;
import com.huawei.health.health.utils.functionsetcard.manager.model.CardConfig;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.homehealth.functionsetcard.manager.ICardManager;
import com.huawei.ui.homehealth.functionsetcard.manager.strategy.CardShowStrategy;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class oik implements CardShowStrategy {

    /* renamed from: a, reason: collision with root package name */
    private boolean f15699a = false;
    private BroadcastReceiver b;
    protected ICardManager e;

    public oik(ICardManager iCardManager) {
        this.e = iCardManager;
        e();
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.strategy.CardShowStrategy
    public void doStrategy() {
        a();
        c();
        e();
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.strategy.CardShowStrategy
    public void release() {
        b();
    }

    private void c() {
        this.b = new BroadcastReceiver() { // from class: oik.4
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                DeviceInfo deviceInfo;
                if (intent == null) {
                    ReleaseLogUtil.c("FunctionCard_DeviceAbilityStrategy", "setOnBindDeviceListener BroadcastReceiver intent is null");
                    return;
                }
                if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction()) && (deviceInfo = (DeviceInfo) intent.getParcelableExtra("deviceinfo")) != null) {
                    if (deviceInfo.getDeviceConnectState() == 2) {
                        DeviceCapability e = cvs.e(deviceInfo.getDeviceIdentify());
                        if (e != null) {
                            oik.this.b(e);
                        }
                        ReleaseLogUtil.e("FunctionCard_DeviceAbilityStrategy", "DEVICE_CONNECTED");
                        return;
                    }
                    ReleaseLogUtil.e("FunctionCard_DeviceAbilityStrategy", "DEVICE_CONNECT_STATUS_OTHER");
                }
            }
        };
    }

    private void e() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        BroadcastManagerUtil.bFC_(BaseApplication.getContext(), this.b, intentFilter, LocalBroadcast.c, null);
        LogUtil.a("FunctionCard_DeviceAbilityStrategy", "registerBindingDeviceBroadcastReceiver");
        this.f15699a = true;
    }

    private void b() {
        ReleaseLogUtil.e("FunctionCard_DeviceAbilityStrategy", "release mConnectStateChangedReceiver broadCast start");
        if (!this.f15699a || this.b == null) {
            return;
        }
        try {
            BaseApplication.getContext().unregisterReceiver(this.b);
        } catch (IllegalArgumentException unused) {
            ReleaseLogUtil.e("FunctionCard_DeviceAbilityStrategy", "unregisterReceiver crash Illegal");
        }
        this.f15699a = false;
    }

    private void a() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "FunctionCard_DeviceAbilityStrategy");
        List<DeviceInfo> deviceList2 = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_AW70_DEVICES, null, "FunctionCard_DeviceAbilityStrategy");
        if (bkz.e(deviceList)) {
            deviceList = deviceList2;
        } else if (!cvt.c(deviceList.get(0).getProductType()) && !bkz.e(deviceList2)) {
            deviceList.addAll(deviceList2);
        }
        if (koq.b(deviceList)) {
            ReleaseLogUtil.d("FunctionCard_DeviceAbilityStrategy", "startLongTermStrategy connectDeviceList is null");
            return;
        }
        ArrayList arrayList = new ArrayList(deviceList.size());
        Iterator<DeviceInfo> it = deviceList.iterator();
        while (it.hasNext()) {
            DeviceCapability e = cvs.e(it.next().getDeviceIdentify());
            if (e == null) {
                ReleaseLogUtil.d("FunctionCard_DeviceAbilityStrategy", "getMarketParams capability is null");
            } else {
                arrayList.add(e);
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        ReleaseLogUtil.e("FunctionCard_DeviceAbilityStrategy", "deviceCapabilityList deviceCapabilityList.size:" + arrayList.size());
        e(arrayList);
    }

    private void e(List<DeviceCapability> list) {
        if (this.e == null) {
            LogUtil.h("FunctionCard_DeviceAbilityStrategy", "addDeviceCapabilityCard mCardImpl is null");
            return;
        }
        ReleaseLogUtil.e("FunctionCard_DeviceAbilityStrategy", "addDeviceCapabilityCard start");
        List<CardConfig> uninitializedCardsFromCache = this.e.getUninitializedCardsFromCache();
        if (koq.b(uninitializedCardsFromCache) || koq.b(list)) {
            ReleaseLogUtil.c("FunctionCard_DeviceAbilityStrategy", "addDeviceCapabilityCard---end--return---");
            return;
        }
        ArrayList arrayList = new ArrayList(uninitializedCardsFromCache.size());
        for (CardConfig cardConfig : uninitializedCardsFromCache) {
            CardConstructor cardConstructorById = this.e.getCardConstructorById(cardConfig.getCardId());
            if (cardConstructorById != null && cardConstructorById.getShowFlag(list) == 1) {
                ReleaseLogUtil.e("FunctionCard_DeviceAbilityStrategy", "addDeviceCapabilityCard---cardConstructor support Card Id is" + cardConfig.getCardId());
                arrayList.add(cardConfig.getCardId());
            }
        }
        this.e.onShowStatusChanged(arrayList, true, -1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Object obj) {
        if (obj instanceof DeviceCapability) {
            ArrayList arrayList = new ArrayList(1);
            arrayList.add((DeviceCapability) obj);
            e(arrayList);
        }
    }
}
