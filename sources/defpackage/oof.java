package defpackage;

import android.app.Activity;
import android.content.Intent;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.ui.DeviceMainActivity;
import com.huawei.health.device.wifi.interfaces.CommBaseCallbackInterface;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.hihealth.dictionary.constants.ProductMap;
import com.huawei.hihealth.dictionary.constants.ProductMapInfo;
import com.huawei.hihealth.dictionary.utils.ProductMapParseUtil;
import com.huawei.hms.mlsdk.common.internal.client.event.MonitorResult;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler;
import com.huawei.ui.homehealth.qrcode.util.QrCodeDataBase;

/* loaded from: classes6.dex */
public class oof extends QrCodeBaseHandler {
    private oob b;
    private String c;
    private String d;
    private dcz e;

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public void handleMessage(Message message) {
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public void mainHandleMessage(Message message, Activity activity) {
    }

    public oof(Activity activity, CommBaseCallbackInterface commBaseCallbackInterface) {
        super(activity, commBaseCallbackInterface);
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public QrCodeDataBase parser(String str, Object obj) {
        if (this.mActivity.get() == null) {
            LogUtil.b("MeasureQrCodeHandler", "parser: activity is null");
            return null;
        }
        if (str != null && obj != null) {
            oob oobVar = new oob(str);
            int parser = oobVar.parser(obj);
            if (parser == -2) {
                LogUtil.b("MeasureQrCodeHandler", "PARSER_ERROR_CODE");
                ope.deL_(this.mActivity.get());
            } else {
                if (parser == 0) {
                    LogUtil.a("MeasureQrCodeHandler", MonitorResult.SUCCESS);
                    return oobVar;
                }
                LogUtil.b("MeasureQrCodeHandler", "error:", Integer.valueOf(parser));
            }
        }
        return null;
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public boolean verify(QrCodeDataBase qrCodeDataBase) {
        if (!(qrCodeDataBase instanceof oob)) {
            return false;
        }
        LogUtil.a("MeasureQrCodeHandler", "verify() get data from QrcodeDataBase");
        this.b = (oob) qrCodeDataBase;
        return true;
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public void execute() {
        LogUtil.a("MeasureQrCodeHandler", "enter execute()");
        Activity activity = this.mActivity.get();
        if (activity == null) {
            LogUtil.b("MeasureQrCodeHandler", "execute: activity is null");
            return;
        }
        if (!obb.e(activity)) {
            obb.cTV_(activity);
            return;
        }
        Intent intent = new Intent();
        String b = this.b.b();
        String e = this.b.e();
        String c = this.b.c();
        ProductMapParseUtil.b(activity);
        ProductMapInfo d = ProductMap.d(e);
        if ("69".equals(b) && e(d, c)) {
            ddY_(activity);
            return;
        }
        intent.putExtra("BRAND_FROM_QRCODE", this.b.a());
        intent.putExtra("BLE_FROM_QRCODE", this.b.d());
        intent.putExtra("BLENAME_FROM_QRCODE", c);
        intent.putExtra("PID_FROM_QRCODE", e);
        intent.putExtra("PIN_FROM_QRCODE", this.b.g());
        intent.putExtra("DEVICE_TYPE_INDEX", b);
        intent.putExtra("KEY_TO_GET_START_FROM_QRCODE", true);
        intent.setClass(this.mActivity.get(), DeviceMainActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(0, 0);
        activity.finish();
    }

    private boolean e(ProductMapInfo productMapInfo, String str) {
        if (productMapInfo == null) {
            LogUtil.a("MeasureQrCodeHandler", "sportProductInfo is null");
            return false;
        }
        this.d = productMapInfo.h();
        dcz d = ResourceManager.e().d(this.d);
        this.e = d;
        if (d == null) {
            LogUtil.a("MeasureQrCodeHandler", "productInfo is null");
            return false;
        }
        MeasurableDevice e = ceo.d().e(this.d, str);
        if (e == null) {
            LogUtil.a("MeasureQrCodeHandler", "this device is unBind");
            return false;
        }
        this.c = e.getUniqueId();
        return BleConstants.BLE_THIRD_DEVICE_H5.equals(this.e.m().d());
    }

    private void ddY_(Activity activity) {
        if (TextUtils.isEmpty(this.c)) {
            LogUtil.a("MeasureQrCodeHandler", "macAddress is null");
            return;
        }
        if ("1".equals(this.e.j())) {
            dks.d(activity, this.e, this.d, this.c);
            return;
        }
        Intent Wx_ = dks.Wx_(this.e, this.d, this.c);
        if (Wx_ != null) {
            Wx_.putExtra("url", dcq.b().c(this.d) + "?enterType=QRCODE");
            activity.startActivity(Wx_);
            activity.finish();
        }
    }
}
