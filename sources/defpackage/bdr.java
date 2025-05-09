package defpackage;

import android.app.Notification;
import android.os.Bundle;
import android.os.Handler;
import androidx.media.MediaBrowserProtocol;
import com.huawei.bone.ui.setting.voip.VoipCallHandle;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes8.dex */
class bdr extends bdt {
    bdr() {
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
            return true;
        }
        if (!z2) {
            return false;
        }
        bundle.putInt("voip_type", 1);
        return true;
    }
}
