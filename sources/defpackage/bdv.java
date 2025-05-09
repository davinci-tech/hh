package defpackage;

import android.app.Notification;
import android.os.Bundle;
import android.os.Handler;
import androidx.media.MediaBrowserProtocol;
import com.huawei.bone.ui.setting.voip.VoipCallHandle;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes8.dex */
class bdv extends bdt {
    bdv() {
    }

    @Override // defpackage.bdt, com.huawei.bone.ui.setting.voip.VoipCallHandle
    public boolean isPushVoipCalling(bds bdsVar, Handler handler, Bundle bundle, Notification notification, boolean z) {
        Notification.Action[] actionArr = notification.actions;
        String string = bundle.getString(MediaBrowserProtocol.DATA_PACKAGE_NAME);
        if (actionArr == null || actionArr.length == 0) {
            LogUtil.h(VoipCallHandle.TAG, "onNotificationPosted VOIP! ", string, " has no action button in statusBar");
            return false;
        }
        boolean z2 = false;
        boolean z3 = false;
        for (Notification.Action action : actionArr) {
            String pc_ = bdo.pc_(action);
            if (pc_.equals(bdsVar.d.get(string))) {
                z2 = true;
            }
            if (pc_.equals(bdsVar.e.get(string))) {
                z3 = true;
            }
        }
        LogUtil.h(VoipCallHandle.TAG, "isContainsRejectIntent is:", Boolean.valueOf(z2), ", isContainsHangupIntent is:", Boolean.valueOf(z3));
        if (actionArr.length == 1 && z3) {
            bundle.putInt("voip_type", 2);
            handler.removeMessages(12);
            ReleaseLogUtil.e(VoipCallHandle.TAG_RELEASE, "onNotificationPosted mHandler MSG_ACTION_REMOVE_VOIP_CALLING.");
            return true;
        }
        if (z2) {
            bundle.putInt("voip_type", 1);
            handler.removeMessages(12);
            ReleaseLogUtil.e(VoipCallHandle.TAG_RELEASE, "onNotificationPosted mHandler MSG_ACTION_REMOVE_VOIP_CALLING.");
            if (z) {
                if (bds.f338a.containsKey(string)) {
                    ReleaseLogUtil.e(VoipCallHandle.TAG_RELEASE, string, "mResendLongVibrateCache contains key,filter is true.");
                    return false;
                }
                ReleaseLogUtil.e(VoipCallHandle.TAG_RELEASE, string, "mResendLongVibrateCache not contains key,filter is false.");
                bdsVar.b(string);
                return true;
            }
        }
        return false;
    }
}
