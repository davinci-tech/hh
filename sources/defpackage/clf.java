package defpackage;

import android.app.Activity;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.device.ui.measure.fragment.HagridDeviceManagerFragment;
import com.huawei.health.device.ui.util.DeviceManagerInfoHandler;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class clf extends BaseHandler<HagridDeviceManagerFragment> {
    private HagridDeviceManagerFragment b;
    private Activity c;

    public clf(HagridDeviceManagerFragment hagridDeviceManagerFragment, Activity activity) {
        super(Looper.getMainLooper(), hagridDeviceManagerFragment);
        this.c = activity;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.haf.handler.BaseHandler
    /* renamed from: HA_, reason: merged with bridge method [inline-methods] */
    public void handleMessageWhenReferenceNotNull(HagridDeviceManagerFragment hagridDeviceManagerFragment, Message message) {
        if (message == null) {
        }
        LogUtil.a("R_Weight_ConditionBeforeRequestHandler", "handleMessageWhenReferenceNotNull message.what: ", Integer.valueOf(message.what));
        if (hagridDeviceManagerFragment == null || hagridDeviceManagerFragment.isDestory()) {
            LogUtil.h("R_Weight_ConditionBeforeRequestHandler", "ConditionBeforeRequestHandler object is null or destroyed");
            return;
        }
        this.b = hagridDeviceManagerFragment;
        switch (message.what) {
            case 10:
                b();
                cqh.c().Le_(this.c);
                break;
            case 11:
                b();
                if (this.b.getIsClickDeviceVersionUpdateItem()) {
                    this.b.startVersionActivity(true);
                    break;
                } else {
                    final DeviceManagerInfoHandler deviceManagerInfoHandler = new DeviceManagerInfoHandler();
                    deviceManagerInfoHandler.e(new DeviceManagerInfoHandler.GetManagerAccountInfoListener() { // from class: clc
                        @Override // com.huawei.health.device.ui.util.DeviceManagerInfoHandler.GetManagerAccountInfoListener
                        public final void managerAccountInfo(String str) {
                            clf.this.a(deviceManagerInfoHandler, str);
                        }
                    });
                    deviceManagerInfoHandler.a();
                    break;
                }
            case 12:
                b();
                if (this.b.getIsClickDeviceVersionUpdateItem()) {
                    this.b.startVersionActivity(false);
                    break;
                } else {
                    this.b.clickWifiItem();
                    break;
                }
        }
    }

    /* synthetic */ void a(DeviceManagerInfoHandler deviceManagerInfoHandler, String str) {
        HagridDeviceManagerFragment hagridDeviceManagerFragment = this.b;
        if (hagridDeviceManagerFragment == null || hagridDeviceManagerFragment.isDestory()) {
            deviceManagerInfoHandler.e(null);
            LogUtil.h("ConditionBeforeRequestHandler", "fragment is destroyed, unregisterListener");
            return;
        }
        HagridDeviceManagerFragment hagridDeviceManagerFragment2 = this.b;
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        hagridDeviceManagerFragment2.setAccountInfo(str);
        if (this.b.getManagerHandler() != null) {
            this.b.getManagerHandler().sendEmptyMessage(15);
        }
    }

    private void b() {
        if (this.b.getLoadingConditionTimer() != null) {
            this.b.getLoadingConditionTimer().cancel();
        }
        this.b.destroyLoadingDialog();
    }

    public void e() {
        this.c = null;
        this.b = null;
    }
}
