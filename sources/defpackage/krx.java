package defpackage;

import android.content.Context;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hms.framework.network.restclient.hwhttp.ResponseBody;
import com.huawei.hwidauth.h.f;
import com.huawei.secure.android.common.intent.SafeBundle;

/* loaded from: classes5.dex */
public class krx extends f<kqy> {
    public krx(kqy kqyVar, Context context, String str, f.a aVar) {
        this.f6367a = kqyVar;
        this.b = context;
        this.c = str;
        this.d = aVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hwidauth.h.f
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void a(kqy kqyVar, Response<ResponseBody> response) {
        ksy.b("GetDevAuthCodeCase", "handleRequestGetDevAuthCode ==", true);
        int code = response.getCode();
        ksy.b("GetDevAuthCodeCase", "code:" + code, true);
        if (code == 200) {
            try {
                kqyVar.c(new String(response.getBody().bytes(), "UTF-8"));
                if (kqyVar.j() == 0) {
                    SafeBundle safeBundle = new SafeBundle();
                    safeBundle.putString("devAuthCode", kqyVar.d());
                    safeBundle.putString("devSecretKey", kqyVar.e());
                    safeBundle.putString("randomID", kqyVar.f());
                    a(safeBundle);
                } else {
                    ksy.b("GetDevAuthCodeCase", "getErrorCode ==" + kqyVar.g(), true);
                    ksy.b("GetDevAuthCodeCase", "getErrorDesc ==" + kqyVar.h(), false);
                    a(kqyVar.g(), kqyVar.h());
                }
                return;
            } catch (Exception e) {
                ksy.b("GetDevAuthCodeCase", "Exception:" + e.getClass().getSimpleName(), true);
                a(2015, "Request Error:" + e.getClass().getSimpleName());
                return;
            }
        }
        a(2005, "Request Error:code is " + code);
    }
}
