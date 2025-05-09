package defpackage;

import android.content.Context;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.multisimsdk.multidevicemanager.common.AuthParam;
import com.huawei.multisimsdk.multidevicemanager.model.BaseAuthenAbs;
import com.huawei.multisimsdk.multidevicemanager.util.ResponseHandler;

/* loaded from: classes5.dex */
public class lom extends BaseAuthenAbs {
    private static final String d = "GBAAuthen";

    /* renamed from: a, reason: collision with root package name */
    private ResponseHandler f14812a;

    public lom(Context context, AuthParam authParam, Message message) {
        super(context, authParam, message);
        this.f14812a = new ResponseHandler() { // from class: lom.2
            @Override // com.huawei.multisimsdk.multidevicemanager.util.ResponseHandler
            public void onCallback(String str) {
                if (loq.b.booleanValue()) {
                    loq.c(lom.d, "Start GBA AUTH Second result =" + str);
                }
                lnw lnwVar = new lnw();
                if (lop.i(str)) {
                    lnwVar.b(str);
                } else {
                    lnwVar.d(str);
                }
                int b = lnwVar.b();
                String a2 = lnwVar.a();
                if (loq.b.booleanValue()) {
                    loq.c(lom.d, "Start GBA AUTH Second resultcode =" + b);
                }
                if (b == 1000) {
                    if (lom.this.mMessage != null) {
                        String d2 = lnc.d(lom.this.mContext, lom.this.mAuthParam.getSlotId());
                        if (!TextUtils.isEmpty(d2)) {
                            lop.d(lom.this.mContext, d2, "authen_Token", lnwVar.c());
                        }
                        if (!TextUtils.isEmpty(a2)) {
                            lop.d(lom.this.mContext, a2, "authen_Token", lnwVar.c());
                        }
                        if (!TextUtils.isEmpty(d2) && !TextUtils.isEmpty(a2)) {
                            lop.e(lom.this.mContext, a2, d2);
                        }
                        lom.this.mMessage.arg1 = b;
                        lom.this.mMessage.obj = a2;
                        lop.bYq_(lom.this.mMessage);
                        return;
                    }
                    return;
                }
                lop.bYp_(lom.this.mMessage, b);
            }
        };
    }

    @Override // com.huawei.multisimsdk.multidevicemanager.model.BaseAuthenAbs
    public void startAuthLogin() {
        loi d2 = loi.d();
        String authenParam = getAuthenParam(this.mContext, this.mAuthParam);
        String imsi = this.mAuthParam.getImsi();
        int slotId = this.mAuthParam.getSlotId();
        d2.a(this.mContext, authenParam, imsi, slotId, this.f14812a);
        if (loq.b.booleanValue()) {
            loq.c(d, "startAuthLogin slotId =" + slotId);
        }
    }
}
