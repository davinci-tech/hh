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
public class ooh extends QrCodeBaseHandler {
    private ook e;

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public void handleMessage(Message message) {
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public void mainHandleMessage(Message message, Activity activity) {
    }

    public ooh(Activity activity, CommBaseCallbackInterface commBaseCallbackInterface) {
        super(activity, commBaseCallbackInterface);
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public QrCodeDataBase parser(String str, Object obj) {
        if (this.mActivity.get() == null) {
            LogUtil.b("ThirdEcgQrCodeHandler", "parser: activity is null");
            return null;
        }
        if (str != null && obj != null) {
            ook ookVar = new ook(str);
            int parser = ookVar.parser(obj);
            if (parser == -3) {
                LogUtil.b("ThirdEcgQrCodeHandler", "QR_CODE_DATA_NULL");
            } else if (parser == -2) {
                LogUtil.b("ThirdEcgQrCodeHandler", "PARSER_ERROR_CODE");
            } else {
                if (parser == 0) {
                    LogUtil.a("ThirdEcgQrCodeHandler", MonitorResult.SUCCESS);
                    return ookVar;
                }
                LogUtil.b("ThirdEcgQrCodeHandler", "error:", Integer.valueOf(parser));
            }
            opf.deU_(this.mActivity.get());
        }
        return null;
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public boolean verify(QrCodeDataBase qrCodeDataBase) {
        if (!(qrCodeDataBase instanceof ook)) {
            LogUtil.b("ThirdEcgQrCodeHandler", "QrCodeDataBase type error");
            return false;
        }
        ook ookVar = (ook) qrCodeDataBase;
        this.e = ookVar;
        return (TextUtils.isEmpty(ookVar.d()) || TextUtils.isEmpty(this.e.c())) ? false : true;
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public void execute() {
        Activity activity = this.mActivity.get();
        if (activity == null) {
            LogUtil.h("ThirdEcgQrCodeHandler", "execute activity is null");
        } else {
            deb_(activity);
        }
    }

    private void deb_(Activity activity) {
        LogUtil.a("ThirdEcgQrCodeHandler", "downloadResource");
        new dhy(activity, "", this.e.d(), this.e.c()).d("HDK_ECG");
    }
}
