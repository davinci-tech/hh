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
public class ool extends QrCodeBaseHandler {

    /* renamed from: a, reason: collision with root package name */
    private oom f15824a;

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public void handleMessage(Message message) {
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public void mainHandleMessage(Message message, Activity activity) {
    }

    public ool(Activity activity, CommBaseCallbackInterface commBaseCallbackInterface) {
        super(activity, commBaseCallbackInterface);
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public QrCodeDataBase parser(String str, Object obj) {
        Activity activity = this.mActivity.get();
        oom oomVar = new oom(str);
        if (activity == null) {
            LogUtil.b("WatchFaceQrCodeHandler", "parser: activity is null");
            return oomVar;
        }
        if (str != null && obj != null) {
            int parser = oomVar.parser(obj);
            if (parser == -3) {
                LogUtil.b("WatchFaceQrCodeHandler", "QR_CODE_DATA_NULL");
            } else if (parser == -2) {
                LogUtil.b("WatchFaceQrCodeHandler", "PARSER_ERROR_CODE");
            } else {
                if (parser == 0) {
                    LogUtil.a("WatchFaceQrCodeHandler", MonitorResult.SUCCESS);
                    return oomVar;
                }
                LogUtil.b("WatchFaceQrCodeHandler", "error:", Integer.valueOf(parser));
            }
            opf.deU_(this.mActivity.get());
        }
        return oomVar;
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public boolean verify(QrCodeDataBase qrCodeDataBase) {
        if (!(qrCodeDataBase instanceof oom)) {
            LogUtil.b("WatchFaceQrCodeHandler", "QrCodeDataBase type error");
            return false;
        }
        this.f15824a = (oom) qrCodeDataBase;
        return !TextUtils.isEmpty(r2.b());
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public void execute() {
        LogUtil.a("WatchFaceQrCodeHandler", "execute in");
        Activity activity = this.mActivity.get();
        if (activity == null) {
            LogUtil.h("WatchFaceQrCodeHandler", "execute activity is null");
            return;
        }
        jls.e(activity);
        jls.c(activity, this.f15824a.b());
        activity.finish();
    }
}
