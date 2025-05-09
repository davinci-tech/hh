package defpackage;

import android.app.Activity;
import android.os.Message;
import com.huawei.health.R;
import com.huawei.health.device.wifi.interfaces.CommBaseCallbackInterface;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.maps.offlinedata.DeviceTypeConsts;
import com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler;
import com.huawei.ui.homehealth.qrcode.util.QrCodeDataBase;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;

/* loaded from: classes6.dex */
public class ons extends QrCodeBaseHandler {
    private final WeakReference<Activity> d;
    private String e;

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public void handleMessage(Message message) {
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public void mainHandleMessage(Message message, Activity activity) {
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public boolean verify(QrCodeDataBase qrCodeDataBase) {
        return true;
    }

    public ons(Activity activity, CommBaseCallbackInterface commBaseCallbackInterface) {
        super(activity, commBaseCallbackInterface);
        this.d = new WeakReference<>(activity);
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public QrCodeDataBase parser(String str, Object obj) {
        this.e = str;
        return new QrCodeDataBase(str) { // from class: ons.2
            @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeDataBase
            public int parser(Object obj2) {
                return 0;
            }
        };
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public void execute() {
        final Activity activity = this.d.get();
        if (activity == null) {
            LogUtil.h("EcgQrCodeHandler", "execute activity is null");
            return;
        }
        if (Utils.o()) {
            this.mMainThreadHandler.post(new Runnable() { // from class: onw
                @Override // java.lang.Runnable
                public final void run() {
                    ons.ddM_(activity);
                }
            });
            return;
        }
        LogUtil.a("EcgQrCodeHandler", "execute active: ", this.e);
        if ("activeEcgService".equals(this.e)) {
            mxv.b(activity, "card");
        } else if ("activeEcgWatch".equals(this.e)) {
            mxv.b(activity, DeviceTypeConsts.WATCH);
        } else {
            LogUtil.h("EcgQrCodeHandler", "execute mActive undefined");
        }
    }

    static /* synthetic */ void ddM_(Activity activity) {
        nrh.c(activity, BaseApplication.getContext().getString(R.string._2130838362_res_0x7f02035a));
        activity.finish();
    }
}
