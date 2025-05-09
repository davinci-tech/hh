package defpackage;

import android.os.Handler;
import android.os.Message;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.CallbackBetweenIndoorEquipServiceAndActivity;
import com.huawei.indoorequip.datastruct.QrCodeOrNfcInfo;
import com.huawei.ui.commonui.base.BaseActivity;

/* loaded from: classes5.dex */
public class kyu implements CallbackBetweenIndoorEquipServiceAndActivity {

    /* renamed from: a, reason: collision with root package name */
    private boolean f14709a;
    private Handler c;

    public kyu(BaseActivity baseActivity, Handler handler, boolean z) {
        this.c = handler;
        this.f14709a = z;
    }

    private void c(int i, boolean z) {
        Handler handler = this.c;
        if (handler != null) {
            Message obtainMessage = handler.obtainMessage();
            obtainMessage.what = i;
            obtainMessage.obj = Boolean.valueOf(z);
            this.c.sendMessage(obtainMessage);
        }
    }

    @Override // com.huawei.indoorequip.CallbackBetweenIndoorEquipServiceAndActivity
    public void onNewEvent(int i, boolean z) {
        if (this.f14709a) {
            LogUtil.a("Track_IDEQ_ImpForCallbackIndoorEquip", "onNewEvent event = ", Integer.valueOf(i), " isFlag = ", Boolean.valueOf(z));
            c(i, z);
        }
    }

    @Override // com.huawei.indoorequip.CallbackBetweenIndoorEquipServiceAndActivity
    public void onNewEvent(int i, QrCodeOrNfcInfo qrCodeOrNfcInfo) {
        if (this.f14709a) {
            LogUtil.a("Track_IDEQ_ImpForCallbackIndoorEquip", "onNewEvent QRCodeOrNFCInfo ");
            Handler handler = this.c;
            if (handler != null) {
                Message obtainMessage = handler.obtainMessage();
                obtainMessage.what = i;
                obtainMessage.obj = qrCodeOrNfcInfo;
                this.c.sendMessage(obtainMessage);
            }
        }
    }

    @Override // com.huawei.indoorequip.CallbackBetweenIndoorEquipServiceAndActivity
    public void onNewEvent(int i) {
        if (this.f14709a) {
            LogUtil.a("Track_IDEQ_ImpForCallbackIndoorEquip", "onNewEvent newEvent = ", Integer.valueOf(i));
            Handler handler = this.c;
            if (handler != null) {
                handler.sendEmptyMessage(i);
            }
        }
    }

    public void e(boolean z) {
        this.f14709a = z;
    }
}
