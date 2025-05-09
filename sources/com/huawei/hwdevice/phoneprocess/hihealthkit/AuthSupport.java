package com.huawei.hwdevice.phoneprocess.hihealthkit;

import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.cvx;
import defpackage.cwd;
import defpackage.cwg;
import defpackage.cwl;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class AuthSupport {
    private Map<String, IAuthCallback> d = new HashMap();

    public interface IAuthCallback {
        void onResult(int i, String str);
    }

    static class d {
        private static final AuthSupport e = new AuthSupport();
    }

    public static AuthSupport d() {
        return d.e;
    }

    public void e(String str, IAuthCallback iAuthCallback) {
        if (TextUtils.isEmpty(str) || iAuthCallback == null) {
            ReleaseLogUtil.c("R_AuthSupport", "addAuthTask() deviceIdentify is empty or callback is null");
        } else {
            this.d.put(str, iAuthCallback);
        }
    }

    private IAuthCallback c(String str) {
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.c("R_AuthSupport", "getAuthTaskCallback() deviceIdentify is empty");
            return null;
        }
        if (!this.d.containsKey(str)) {
            ReleaseLogUtil.c("R_AuthSupport", "getAuthTaskCallback() deviceIdentify does not exist");
            return null;
        }
        return this.d.get(str);
    }

    public void d(String str) {
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.c("R_AuthSupport", "removeAuthTaskCallback() deviceIdentify is empty");
        } else if (this.d.containsKey(str)) {
            this.d.remove(str);
            LogUtil.a("AuthSupport", "removeAuthTaskCallback success");
        } else {
            ReleaseLogUtil.c("R_AuthSupport", "removeAuthTaskCallback() deviceIdentify does not exist");
        }
    }

    public void b(DeviceInfo deviceInfo, byte[] bArr) {
        ReleaseLogUtil.e("R_AuthSupport", "enter handleWearKitAuthValue");
        if (bArr == null || bArr.length <= 0) {
            ReleaseLogUtil.c("R_AuthSupport", "handleWearKitAuthValue() Received data is null");
            return;
        }
        if (deviceInfo == null) {
            ReleaseLogUtil.c("R_AuthSupport", "handleWearKitAuthValue() Received deviceInfo is null");
            return;
        }
        String d2 = cvx.d(bArr);
        LogUtil.a("AuthSupport", "handleWearKitAuthValue: ", d2);
        if (d2 != null && d2.length() >= 4) {
            try {
                List<cwd> e = new cwl().a(d2.substring(4)).e();
                if (e == null) {
                    ReleaseLogUtil.c("R_AuthSupport", "handleWearKitAuthValue() tlvList is null");
                    d(deviceInfo.getDeviceIdentify(), 1, "tlvList is null");
                    return;
                }
                int i = 0;
                for (cwd cwdVar : e) {
                    if (CommonUtil.w(cwdVar.e()) == 1) {
                        i = CommonUtil.w(cwdVar.c());
                    }
                }
                LogUtil.a("AuthSupport", "handleWearKitAuthValue() authorizationStatus: ", Integer.valueOf(i));
                d(deviceInfo.getDeviceIdentify(), i, "get authorization status success");
                return;
            } catch (cwg | NumberFormatException e2) {
                ReleaseLogUtil.c("R_AuthSupport", "handleWearKitAuthValue() Exception: ", e2.getMessage());
                d(deviceInfo.getDeviceIdentify(), 1, "failed to parse data");
                return;
            }
        }
        ReleaseLogUtil.d("R_AuthSupport", "handleWearKitAuthValue data is null, or data length less 4");
        d(deviceInfo.getDeviceIdentify(), 1, "tlvHex is null, or length less than 4");
    }

    private void d(String str, int i, String str2) {
        IAuthCallback c = c(str);
        if (c != null) {
            c.onResult(i, str2);
            d(str);
        } else {
            ReleaseLogUtil.d("R_AuthSupport", "responseAuthResultAndRemoveCallback() callback is null");
        }
    }
}
