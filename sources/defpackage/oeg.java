package defpackage;

import android.os.Handler;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwdevice.mainprocess.mgr.hwdevicemodemgr.callback.WearPlaceCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.homehealth.device.CardDeviceFragment;
import java.lang.ref.WeakReference;

/* loaded from: classes6.dex */
public class oeg implements WearPlaceCallback {
    private WeakReference<CardDeviceFragment> e;

    public oeg(CardDeviceFragment cardDeviceFragment) {
        this.e = new WeakReference<>(cardDeviceFragment);
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.hwdevicemodemgr.callback.WearPlaceCallback
    public void onResponse(DeviceInfo deviceInfo, int i) {
        CardDeviceFragment cardDeviceFragment = this.e.get();
        if (deviceInfo == null) {
            LogUtil.h("CardDeviceFragmentPlaceCallback", "WearHomePlaceCallback enter deviceInfo is null");
            return;
        }
        Handler handler = cardDeviceFragment.w;
        if (handler == null) {
            LogUtil.h("CardDeviceFragmentPlaceCallback", "WearHomePlaceCallback enter handler is null");
        } else {
            handler.sendEmptyMessage(34);
        }
    }
}
