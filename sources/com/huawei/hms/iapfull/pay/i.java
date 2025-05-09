package com.huawei.hms.iapfull.pay;

import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.hms.iapfull.bean.PayResult;
import com.huawei.hms.iapfull.e1;
import com.huawei.hms.iapfull.network.model.DeveloperSignResponse;
import com.huawei.hms.iapfull.pay.g;
import com.huawei.hms.iapfull.q0;
import com.huawei.hms.iapfull.y0;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes4.dex */
class i implements q0 {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ j f4752a;

    @Override // com.huawei.hms.iapfull.q0
    public void a(String str, String str2) {
        this.f4752a.a().a();
        y0.b("PayPresenter", "requestDeveloperSign failed, errorCode is: " + str + ", desc:" + str2);
        if ("2".equals(str)) {
            e1.a().a(this.f4752a.a(), R.string._2130851289_res_0x7f0235d9);
        }
        if ("1".equals(str)) {
            e1.a().a(this.f4752a.a(), R.string._2130851286_res_0x7f0235d6);
        }
        if (!f.a(str)) {
            str = "client10008".equals(str) ? "30002" : "600000".equals(str) ? "30004" : "600010".equals(str) ? "30099" : "1".equals(str) ? "30001" : "-1";
        }
        PayResult payResult = new PayResult();
        payResult.setReturnCode(str);
        payResult.setErrMsg(str2);
        ((PayActivity) this.f4752a.d).a(payResult);
        this.f4752a.a().finish();
    }

    @Override // com.huawei.hms.iapfull.q0
    public void a(DeveloperSignResponse developerSignResponse) {
        String str;
        boolean f;
        this.f4752a.a().a();
        y0.b("PayPresenter", "request developerSign success...");
        this.f4752a.e = developerSignResponse.getHuaweiSDKKey();
        str = this.f4752a.e;
        if (!TextUtils.isEmpty(str)) {
            ((PayActivity) this.f4752a.d).a(true);
        }
        String packageName = this.f4752a.b.getPackageName();
        g gVar = g.a.f4750a;
        j jVar = this.f4752a;
        List<DeveloperSignResponse.MyPayType> payTypeList = developerSignResponse.getPayTypeList();
        jVar.getClass();
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        if (payTypeList == null) {
            y0.b("PayPresenter", "parsesPayTypeList, list is null");
            copyOnWriteArrayList = null;
        } else {
            for (int i = 0; i < payTypeList.size(); i++) {
                copyOnWriteArrayList.add(Integer.valueOf(payTypeList.get(i).getPayType()));
            }
        }
        gVar.a(copyOnWriteArrayList);
        g gVar2 = g.a.f4750a;
        gVar2.a(developerSignResponse.getDics(), packageName);
        ((PayActivity) this.f4752a.d).a(gVar2.a());
        f = this.f4752a.f();
        if (f) {
            ((PayActivity) this.f4752a.d).a(R.string._2130851295_res_0x7f0235df);
        }
        j jVar2 = this.f4752a;
        com.huawei.hms.iapfull.b.a(this.f4752a.a(), "iap_payment_launch", jVar2.a(jVar2.b));
    }

    i(j jVar) {
        this.f4752a = jVar;
    }
}
