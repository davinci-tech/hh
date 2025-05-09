package defpackage;

import android.content.Context;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hms.framework.network.restclient.hwhttp.ResponseBody;
import com.huawei.hwidauth.h.f;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Opers;
import com.huawei.secure.android.common.intent.SafeBundle;

/* loaded from: classes5.dex */
public class krz extends f<kqu> {
    public krz(kqu kquVar, Context context, String str, f.a aVar) {
        this.f6367a = kquVar;
        this.b = context;
        this.c = str;
        this.d = aVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hwidauth.h.f
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void a(kqu kquVar, Response<ResponseBody> response) {
        int code = response.getCode();
        ksy.b("GetConfigurationCase", "code:" + code, true);
        if (code == 200) {
            try {
                String str = new String(response.getBody().bytes(), "UTF-8");
                ksy.b("GetConfigurationCase", "handleRequestNet data:".concat(str), false);
                kquVar.d(str);
                if (kquVar.j() == 0) {
                    SafeBundle safeBundle = new SafeBundle();
                    safeBundle.putString(TrackConstants$Opers.RESPONSE, kquVar.d());
                    a(safeBundle);
                } else {
                    ksy.b("GetConfigurationCase", "getErrorCode ==" + kquVar.g(), true);
                    ksy.b("GetConfigurationCase", "getErrorDesc ==" + kquVar.h(), false);
                    a(kquVar.g(), kquVar.h());
                }
                return;
            } catch (Exception e) {
                ksy.b("GetConfigurationCase", "Exception:" + e.getClass().getSimpleName(), true);
                a(2015, "Request Error:" + e.getClass().getSimpleName());
                return;
            }
        }
        a(2005, "Request Error:code is " + code);
    }
}
