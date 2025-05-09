package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;

/* loaded from: classes6.dex */
public class pwn {

    /* renamed from: a, reason: collision with root package name */
    private static pwn f16308a;
    private pwm e = pwm.a();

    private pwn() {
    }

    public static pwn b() {
        if (f16308a == null) {
            f16308a = new pwn();
        }
        return f16308a;
    }

    public void b(final CommonUiBaseResponse commonUiBaseResponse) {
        if (this.e != null) {
            LogUtil.a("FitnessThirdPartyDataInteractor", "requestGoogleFitPonitDatas ");
            this.e.a(new CommonUiBaseResponse() { // from class: pwn.1
                @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
                public void onResponse(int i, Object obj) {
                    commonUiBaseResponse.onResponse(i, obj);
                    LogUtil.c("FitnessThirdPartyDataInteractor", "requestGoogleFitPonitDatas  sucess !!! ");
                }
            });
        }
    }

    public void d(final CommonUiBaseResponse commonUiBaseResponse) {
        if (this.e != null) {
            LogUtil.a("FitnessThirdPartyDataInteractor", "requestGoogleFitSegentDatas");
            this.e.d(new CommonUiBaseResponse() { // from class: pwn.2
                @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
                public void onResponse(int i, Object obj) {
                    commonUiBaseResponse.onResponse(i, obj);
                    LogUtil.c("FitnessThirdPartyDataInteractor", "requestGoogleFitSegentDatas  sucess !!! ");
                }
            });
        }
    }
}
