package defpackage;

import android.os.Bundle;
import com.huawei.health.device.api.EventBusApi;
import com.huawei.health.device.util.EventBus;
import com.huawei.health.devicemgr.api.constant.ExecuteMode;
import com.huawei.hmf.annotation.ApiDefine;
import health.compact.a.util.LogUtil;

@ApiDefine(uri = EventBusApi.class)
/* loaded from: classes3.dex */
public class dyp implements EventBusApi {
    private static EventBus.ICallback e = new EventBus.ICallback() { // from class: dyp.5
        @Override // com.huawei.health.device.util.EventBus.ICallback
        public void onEvent(EventBus.b bVar) {
            if ("sub_am16_bind_success".equals(bVar.e())) {
                cun.c().executeDeviceRelatedLogic(ExecuteMode.FORCE_INIT_PHONE_SERVICE, null, "EventBusImpl");
            } else {
                LogUtil.c("EventBusImpl", "event.getAction() is not am16 bind success");
            }
        }
    };

    @Override // com.huawei.health.device.api.EventBusApi
    public void publishDeviceDownMsg() {
        LogUtil.d("EventBusImpl", "publishDeviceDownMsg");
        EventBus.d(new EventBus.b("device_download_dialog"));
    }

    @Override // com.huawei.health.device.api.EventBusApi
    public void publishSingleDeviceDownMsg() {
        LogUtil.d("EventBusImpl", "publishSingleDeviceDownMsg");
        EventBus.d(new EventBus.b("single_device_download_dialog"));
    }

    @Override // com.huawei.health.device.api.EventBusApi
    public void subscribeAm16Bind() {
        EventBus.d(e, 0, "sub_am16_bind_success");
    }

    @Override // com.huawei.health.device.api.EventBusApi
    public void publishHmsLoginState(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("sign_account_hms_login_state_key", str);
        EventBus.d(new EventBus.b("sign_account_hms_login_state_action", bundle));
    }
}
