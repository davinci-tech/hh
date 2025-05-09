package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.ui.DeviceMainActivity;
import com.huawei.health.device.wifi.interfaces.CommBaseCallbackInterface;
import com.huawei.health.suggestion.PluginSuggestion;
import com.huawei.healthcloud.plugintrack.ui.activity.TrackMainMapActivity;
import com.huawei.hihealth.dictionary.constants.ProductMap;
import com.huawei.hihealth.dictionary.constants.ProductMapInfo;
import com.huawei.hihealth.dictionary.utils.ProductMapParseUtil;
import com.huawei.hms.mlsdk.common.internal.client.event.MonitorResult;
import com.huawei.hwadpaterhealthmgr.PluginHealthTrackAdapterImpl;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.activity.IndoorEquipConnectedActivity;
import com.huawei.indoorequip.activity.IndoorEquipDisplayActivity;
import com.huawei.indoorequip.activity.IndoorEquipLandDisplayActivity;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler;
import com.huawei.ui.homehealth.qrcode.util.QrCodeDataBase;
import health.compact.a.SharedPreferenceManager;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class ooc extends QrCodeBaseHandler {
    private static final Set<String> b = new HashSet(10);

    /* renamed from: a, reason: collision with root package name */
    private ooe f15821a;
    private boolean e;

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public void handleMessage(Message message) {
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public void mainHandleMessage(Message message, Activity activity) {
    }

    public ooc(Activity activity, CommBaseCallbackInterface commBaseCallbackInterface, Bundle bundle) {
        super(activity, commBaseCallbackInterface, bundle);
        this.e = false;
        Set<String> set = b;
        set.add(BleConstants.SPORT_TYPE_BIKE);
        set.add("260");
        set.add("261");
        set.add("31");
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public QrCodeDataBase parser(String str, Object obj) {
        Activity activity = this.mActivity.get();
        if (activity == null) {
            LogUtil.b("IndoorEquipQrCodeHandler", "parser: activity is null");
            return null;
        }
        if (str != null && obj != null) {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            activity.runOnUiThread(new Runnable() { // from class: ood
                @Override // java.lang.Runnable
                public final void run() {
                    ooc.this.c(countDownLatch);
                }
            });
            try {
                countDownLatch.await(2L, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                LogUtil.b("IndoorEquipQrCodeHandler", e.getMessage());
            }
            if (this.e) {
                LogUtil.a("IndoorEquipQrCodeHandler", "parser: SportManager is running");
                return null;
            }
            ooe ooeVar = new ooe(str);
            int parser = ooeVar.parser(obj);
            if (parser == -2) {
                LogUtil.b("IndoorEquipQrCodeHandler", "PARSER_ERROR_CODE");
                ope.deL_(this.mActivity.get());
            } else {
                if (parser == 0) {
                    LogUtil.a("IndoorEquipQrCodeHandler", MonitorResult.SUCCESS);
                    return ooeVar;
                }
                LogUtil.b("IndoorEquipQrCodeHandler", "error:", Integer.valueOf(parser));
            }
        }
        return null;
    }

    /* synthetic */ void c(CountDownLatch countDownLatch) {
        this.e = b(this.mActivity.get());
        countDownLatch.countDown();
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public boolean verify(QrCodeDataBase qrCodeDataBase) {
        if (!(qrCodeDataBase instanceof ooe)) {
            return false;
        }
        LogUtil.a("IndoorEquipQrCodeHandler", "verify() get data from QrcodeDataBase");
        this.f15821a = (ooe) qrCodeDataBase;
        return true;
    }

    private void ddV_(Activity activity, String str) {
        Intent intent = new Intent();
        intent.putExtra("PAYLOAD_FROM_NFC", str);
        intent.putExtra("PROTOCOL_FROM_QRCODE", this.f15821a.g());
        intent.putExtra("ENTER_TYPE", "QRCODE");
        intent.setClass(activity, DeviceMainActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public void execute() {
        LogUtil.a("IndoorEquipQrCodeHandler", "enter execute()");
        Activity activity = this.mActivity.get();
        if (activity == null) {
            LogUtil.b("IndoorEquipQrCodeHandler", "execute: activity is null");
            return;
        }
        if (!obb.e(activity)) {
            obb.cTV_(activity);
            return;
        }
        String a2 = a();
        LogUtil.a("IndoorEquipQrCodeHandler", "execute", ",productId:", a2);
        if (b.contains(this.f15821a.d())) {
            ddX_(activity, a2);
        } else {
            ddW_(activity);
        }
    }

    private void ddW_(Activity activity) {
        Intent intent = new Intent();
        intent.putExtra("PROTOCOL_FROM_QRCODE", this.f15821a.g());
        intent.putExtra("BLE_FROM_QRCODE", !TextUtils.isEmpty(this.f15821a.c()) ? this.f15821a.c() : this.f15821a.a());
        intent.putExtra("BLENAME_FROM_QRCODE", this.f15821a.b());
        intent.putExtra("DEVICE_TYPE_INDEX", this.f15821a.d());
        intent.putExtra("PID_FROM_QRCODE", this.f15821a.e());
        if (this.f15821a.d().equals("262")) {
            LogUtil.c("IndoorEquipQrCodeHandler", "execute: TYPE_ROPE_INDEX");
            intent.setClass(this.mActivity.get(), DeviceMainActivity.class);
            intent.putExtra("KEY_TO_GET_START_FROM_QRCODE", true);
        } else if (kzc.n().t()) {
            intent.setClass(this.mActivity.get(), IndoorEquipDisplayActivity.class);
            intent.putExtra("show tips key", true);
        } else if (kzc.n().x()) {
            intent.setClass(this.mActivity.get(), IndoorEquipLandDisplayActivity.class);
            intent.putExtra("show tips key", true);
        } else {
            intent.setClass(this.mActivity.get(), IndoorEquipConnectedActivity.class);
        }
        LogUtil.a("IndoorEquipQrCodeHandler", "execute() will start IndoorEquipConnectedActivity");
        activity.startActivity(intent);
        activity.overridePendingTransition(0, 0);
        activity.finish();
    }

    private void ddX_(Activity activity, String str) {
        String e;
        LogUtil.a("IndoorEquipQrCodeHandler", "toProductPage:");
        if (TextUtils.isEmpty(this.f15821a.e()) && !TextUtils.isEmpty(str)) {
            LogUtil.a("IndoorEquipQrCodeHandler", "toProductPage find pid from productMap");
            ProductMapParseUtil.b(BaseApplication.getContext());
            Iterator<ProductMapInfo> it = ProductMap.d().iterator();
            while (true) {
                if (!it.hasNext()) {
                    e = "";
                    break;
                }
                ProductMapInfo next = it.next();
                if (next.h().equals(str)) {
                    e = next.f();
                    break;
                }
            }
        } else {
            e = this.f15821a.e();
        }
        boolean equals = "1".equals(this.f15821a.g());
        StringBuilder sb = new StringBuilder("ftmp=");
        sb.append(equals ? "0" : "1");
        sb.append("&t=");
        sb.append(this.f15821a.d());
        sb.append("&n=");
        sb.append(this.f15821a.b());
        sb.append("&pid=");
        sb.append(e);
        sb.append("&ble=");
        sb.append(this.f15821a.a());
        String sb2 = sb.toString();
        LogUtil.a("IndoorEquipQrCodeHandler", "payload:", sb2);
        ddV_(activity, sb2);
    }

    private String a() {
        return lbq.c(HealthDevice.HealthDeviceKind.getHealthDeviceKind(this.f15821a.d()).name(), this.f15821a.b());
    }

    private boolean b(Context context) {
        PluginSuggestion pluginSuggestion = PluginSuggestion.getInstance();
        if (!(context instanceof Activity) || pluginSuggestion == null) {
            return false;
        }
        pluginSuggestion.init(context);
        gso.e().setAdapter(PluginHealthTrackAdapterImpl.getInstance(context));
        gso.e().init(context);
        int i = gso.e().i();
        boolean k = gso.e().k();
        String b2 = SharedPreferenceManager.b(context, Integer.toString(20002), "iscrash");
        if (k || i == 1 || i == 2) {
            LogUtil.a("IndoorEquipQrCodeHandler", "track module is running");
            if (gtx.c(gxf.d()).r() != 1) {
                Intent intent = new Intent(context, (Class<?>) TrackMainMapActivity.class);
                intent.setFlags(AppRouterExtras.COLDSTART);
                intent.putExtra("isSelected", false);
                LogUtil.a("IndoorEquipQrCodeHandler", "track module is not auto track, resume");
                context.startActivity(intent);
            }
            return true;
        }
        if ("true".equals(b2)) {
            LogUtil.a("IndoorEquipQrCodeHandler", "crash flag of track module is true");
            return true;
        }
        if (gtx.c(gxf.d()).r() == 1) {
            LogUtil.a("IndoorEquipQrCodeHandler", "track module has auto track, break it");
            gtx.c(gxf.d()).cb();
        }
        LogUtil.a("IndoorEquipQrCodeHandler", "track module is not running");
        if (dum.d() != null) {
            LogUtil.a("IndoorEquipQrCodeHandler", "getInstance of Mediator success");
        }
        return false;
    }
}
