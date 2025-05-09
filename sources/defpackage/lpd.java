package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import com.huawei.multisimsdk.multidevicemanager.common.AbsPairedDevice;
import com.huawei.multisimsdk.multidevicemanager.common.AbsPrimaryDevice;
import com.huawei.multisimsdk.multidevicemanager.common.AuthParam;
import com.huawei.multisimsdk.multidevicemanager.model.BaseAuthenAbs;
import com.huawei.multisimsdk.odsa.view.LoginWebActivity;

/* loaded from: classes5.dex */
public class lpd extends BaseAuthenAbs {
    private static lpd d;

    /* renamed from: a, reason: collision with root package name */
    private AbsPrimaryDevice f14825a;
    private loz c;
    private AbsPairedDevice e;

    public lpd(Context context, AuthParam authParam, AbsPrimaryDevice absPrimaryDevice, AbsPairedDevice absPairedDevice, Message message) {
        super(context, authParam, message);
        this.f14825a = absPrimaryDevice;
        this.e = absPairedDevice;
        this.c = loz.d(this.mContext, this.mAuthParam.getSlotId());
        c(this);
    }

    private static void c(lpd lpdVar) {
        d = lpdVar;
    }

    public static lpd a() {
        return d;
    }

    public AbsPrimaryDevice d() {
        return this.f14825a;
    }

    public AbsPairedDevice b() {
        return this.e;
    }

    public void d(int i) {
        if (this.mMessage != null) {
            loq.c("OpenIdAuth", "response result " + i + " to " + this.mMessage.getTarget());
            this.mMessage.arg1 = i;
            lop.bYq_(this.mMessage);
            this.mMessage = null;
        }
    }

    @Override // com.huawei.multisimsdk.multidevicemanager.model.BaseAuthenAbs
    public void startAuthLogin() {
        Intent intent = new Intent();
        intent.setClass(this.mContext, LoginWebActivity.class);
        intent.setFlags(268435456);
        intent.putExtra("slotId", this.mAuthParam.getSlotId());
        loq.c("OpenIdAuth", "start auth login " + intent);
        this.mContext.startActivity(intent);
    }
}
