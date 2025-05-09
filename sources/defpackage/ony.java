package defpackage;

import android.app.Activity;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.health.device.wifi.interfaces.CommBaseCallbackInterface;
import com.huawei.hms.mlsdk.common.internal.client.event.MonitorResult;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler;
import com.huawei.ui.homehealth.qrcode.util.QrCodeDataBase;

/* loaded from: classes6.dex */
public class ony extends QrCodeBaseHandler {
    private onx d;

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public void handleMessage(Message message) {
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public void mainHandleMessage(Message message, Activity activity) {
    }

    public ony(Activity activity, CommBaseCallbackInterface commBaseCallbackInterface) {
        super(activity, commBaseCallbackInterface);
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public QrCodeDataBase parser(String str, Object obj) {
        if (this.mActivity.get() == null) {
            LogUtil.b("ThirdVentilatorQrCodeHandler", "parser: activity is null");
            return null;
        }
        if (str != null && obj != null) {
            onx onxVar = new onx(str);
            int parser = onxVar.parser(obj);
            if (parser == -3) {
                LogUtil.b("ThirdVentilatorQrCodeHandler", "QR_CODE_DATA_NULL");
            } else if (parser == -2) {
                LogUtil.b("ThirdVentilatorQrCodeHandler", "PARSER_ERROR_CODE");
            } else {
                if (parser == 0) {
                    LogUtil.a("ThirdVentilatorQrCodeHandler", MonitorResult.SUCCESS);
                    return onxVar;
                }
                LogUtil.b("ThirdVentilatorQrCodeHandler", "error:", Integer.valueOf(parser));
            }
            opf.deU_(this.mActivity.get());
        }
        return null;
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public boolean verify(QrCodeDataBase qrCodeDataBase) {
        if (!(qrCodeDataBase instanceof onx)) {
            LogUtil.b("ThirdVentilatorQrCodeHandler", "QrCodeDataBase type error");
            return false;
        }
        onx onxVar = (onx) qrCodeDataBase;
        this.d = onxVar;
        return (TextUtils.isEmpty(onxVar.c()) || TextUtils.isEmpty(this.d.b())) ? false : true;
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public void execute() {
        Activity activity = this.mActivity.get();
        if (activity == null) {
            LogUtil.h("ThirdVentilatorQrCodeHandler", "execute activity is null");
        } else {
            ddU_(activity);
        }
    }

    private void ddU_(Activity activity) {
        LogUtil.a("ThirdVentilatorQrCodeHandler", "downloadResource");
        new dhy(activity, "", this.d.c(), this.d.b(), this.d.d()).d("HDK_VENTILATOR");
    }
}
