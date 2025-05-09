package defpackage;

import android.content.Context;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.device.utlis.clouddevice.DownloadCloudDeviceResource;

/* loaded from: classes6.dex */
public class obq {
    private DownloadCloudDeviceResource e;

    public void d(DownloadCloudDeviceResource downloadCloudDeviceResource, String str, boolean z, String str2, Context context) {
        this.e = downloadCloudDeviceResource;
        if (downloadCloudDeviceResource == null) {
            this.e = new DownloadCloudDeviceResource();
        }
        boolean a2 = this.e.a(str, z);
        LogUtil.a("SendPushMessage", "getDeviceProfileId: ", str, "isSupportSeizePush", Boolean.valueOf(a2));
        if (a2) {
            jpp.d(str2, context);
        }
        this.e.d();
    }
}
