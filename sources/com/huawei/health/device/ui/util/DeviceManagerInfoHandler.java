package com.huawei.health.device.ui.util;

import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.health.device.util.EventBus;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class DeviceManagerInfoHandler {

    /* renamed from: a, reason: collision with root package name */
    private String f2265a;
    private GetManagerAccountInfoListener d;
    private final String[] c = {"manager_info_success", "manager_info_failed", "set_manager_info_result"};
    private EventBus.ICallback b = new EventBus.ICallback() { // from class: com.huawei.health.device.ui.util.DeviceManagerInfoHandler.4
        @Override // com.huawei.health.device.util.EventBus.ICallback
        public void onEvent(EventBus.b bVar) {
            DeviceManagerInfoHandler.this.c();
            String e = bVar.e();
            Bundle Kl_ = bVar.Kl_();
            if (Kl_ == null) {
                return;
            }
            if ("manager_info_success".equals(e)) {
                DeviceManagerInfoHandler.this.f2265a = Kl_.getString("accountInfo");
                LogUtil.a("DeviceManagerInfoHandler", "DeviceManagerInfoHandler mManagerAccountId: ", DeviceManagerInfoHandler.this.f2265a);
                if (!TextUtils.isEmpty(DeviceManagerInfoHandler.this.f2265a)) {
                    DeviceManagerInfoHandler deviceManagerInfoHandler = DeviceManagerInfoHandler.this;
                    deviceManagerInfoHandler.f2265a = deviceManagerInfoHandler.f2265a.trim();
                }
                if (DeviceManagerInfoHandler.this.d != null) {
                    DeviceManagerInfoHandler.this.d.managerAccountInfo(DeviceManagerInfoHandler.this.f2265a);
                    return;
                } else {
                    LogUtil.h("DeviceManagerInfoHandler", "DeviceManagerInfoHandler mGetManagerAccountInfoListener is null!");
                    return;
                }
            }
            if ("manager_info_failed".equals(e)) {
                LogUtil.h("DeviceManagerInfoHandler", "DeviceManagerInfoHandler get manager info fail!");
            } else if ("set_manager_info_result".equals(e)) {
                LogUtil.h("DeviceManagerInfoHandler", "DeviceManagerInfoHandler mEventCallback result", Integer.valueOf(Kl_.getInt("ret")));
            } else {
                LogUtil.h("DeviceManagerInfoHandler", "DeviceManagerInfoHandler mEventCallback else");
            }
        }
    };

    public interface GetManagerAccountInfoListener {
        void managerAccountInfo(String str);
    }

    public void e(GetManagerAccountInfoListener getManagerAccountInfoListener) {
        this.d = getManagerAccountInfoListener;
    }

    public void a() {
        d();
        Bundle bundle = new Bundle();
        bundle.putBoolean("isGetAccountInfo", true);
        EventBus.d(new EventBus.b("get_manager_info", bundle));
    }

    private void d() {
        EventBus.d(this.b, 0, this.c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        EventBus.a(this.b, this.c);
    }
}
