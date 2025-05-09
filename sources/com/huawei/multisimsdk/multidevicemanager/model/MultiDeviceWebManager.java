package com.huawei.multisimsdk.multidevicemanager.model;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.multisimsdk.multidevicemanager.common.AuthParam;
import com.huawei.multisimsdk.multidevicemanager.common.InProgressData;
import com.huawei.multisimsdk.multidevicemanager.util.ResponseHandler;
import defpackage.lnc;
import defpackage.lnj;
import defpackage.lnm;
import defpackage.lnp;
import defpackage.lnt;
import defpackage.lnu;
import defpackage.lnx;
import defpackage.lny;
import defpackage.lnz;
import defpackage.loa;
import defpackage.lob;
import defpackage.lod;
import defpackage.loh;
import defpackage.loi;
import defpackage.lop;
import defpackage.loq;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class MultiDeviceWebManager {
    private static final String e = "MultiDeviceWebManager";

    /* renamed from: a, reason: collision with root package name */
    private InProgressData f6533a;
    private AuthParam b;
    private Context c;
    private ResponseHandler d;

    public MultiDeviceWebManager(Context context) {
        this(context, null);
    }

    public MultiDeviceWebManager(Context context, AuthParam authParam) {
        this.b = null;
        this.d = new ResponseHandler() { // from class: com.huawei.multisimsdk.multidevicemanager.model.MultiDeviceWebManager.2
            @Override // com.huawei.multisimsdk.multidevicemanager.util.ResponseHandler
            public void onCallback(String str) {
                lny c;
                lnj d;
                ArrayList<lnm> a2;
                String a3;
                loa loaVar = new loa();
                loaVar.c(str);
                lnx d2 = loaVar.d();
                lnz b = loaVar.b();
                String b2 = loaVar.d().b();
                loq.c(MultiDeviceWebManager.e, "reqName=" + b2);
                if ("GetDevServInfo".equals(b2) && (c = loaVar.c()) != null && (d = c.d()) != null && (a2 = d.a()) != null) {
                    int size = a2.size();
                    for (int i = 0; i < size; i++) {
                        lnm lnmVar = a2.get(i);
                        if (lnmVar != null && (a3 = lnmVar.a()) != null) {
                            lop.d(MultiDeviceWebManager.this.c, lnc.e(MultiDeviceWebManager.this.c, MultiDeviceWebManager.this.b), "smdp_address", a3);
                        }
                    }
                }
                if (MultiDeviceWebManager.this.e(d2).booleanValue()) {
                    MultiDeviceWebManager.this.a(b);
                }
            }
        };
        if (context != null) {
            this.c = context.getApplicationContext();
        }
        this.b = authParam;
    }

    public void a(InProgressData inProgressData) {
        this.f6533a = inProgressData;
    }

    public void a() {
        InProgressData inProgressData = this.f6533a;
        if (inProgressData != null) {
            if (100 == inProgressData.getType()) {
                e();
            } else {
                d();
            }
        }
    }

    public void e() {
        String c = lod.c();
        String d = d(1);
        loi.d().d(c, d, null, lop.a(this.c, lnc.e(this.c, this.b), "authorization"), this.d);
    }

    private String d(int i) {
        lnu lnuVar = new lnu();
        lnt lntVar = new lnt();
        lnuVar.a(lop.c());
        if (this.f6533a != null) {
            if (1 == i) {
                lntVar.e("Binding");
                lntVar.d(this.f6533a.getNikename());
            } else if (2 == i) {
                lntVar.e("Unbinding");
            }
            String primary = this.f6533a.getPrimary();
            lntVar.c(this.f6533a.getPrimaryIDtype());
            lntVar.a(primary);
            lntVar.j(this.f6533a.getSecondarytype());
            lntVar.b(this.f6533a.getSecondaryID());
            lntVar.b(this.f6533a.getSecondaryDeviceId());
            lnuVar.c(primary);
            lnuVar.b(this.f6533a.getPrimaryIDtype());
            lnuVar.a(this.f6533a.getServiceType());
        }
        lnuVar.b(lntVar);
        lnp lnpVar = new lnp();
        lnpVar.d(lnuVar);
        lnpVar.a(lop.d(this.c, lnc.e(this.c, this.b)));
        return lnpVar.a();
    }

    public void d() {
        loi.d().c(lop.a(this.f6533a), d(2), this.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Boolean e(lnx lnxVar) {
        if (lnxVar != null) {
            int e2 = lnxVar.e();
            InProgressData inProgressData = this.f6533a;
            if (inProgressData != null) {
                inProgressData.setResultcode(e2);
            }
            String str = e;
            loq.c(str, "Handler quick authenResult, resultCode=" + e2);
            if (1000 != e2) {
                if (1004 == e2) {
                    loq.c(str, "TokenAuthen is invalid");
                    lop.b(107, this.f6533a);
                    return false;
                }
                loq.c(str, "TokenAuthen is fail");
                lop.b(106, this.f6533a);
                return false;
            }
            loq.c(str, "TokenAuthen is valid");
            String e3 = lnc.e(this.c, this.b);
            String a2 = lop.a(this.c, e3, "Tag");
            if (!TextUtils.isEmpty(e3)) {
                lop.d(this.c, e3, "authen_Token", lnxVar.d());
            }
            if (!TextUtils.isEmpty(a2)) {
                lop.d(this.c, a2, "authen_Token", lnxVar.d());
            }
            if (!TextUtils.isEmpty(e3) && !TextUtils.isEmpty(a2)) {
                lop.e(this.c, a2, e3);
            }
            return true;
        }
        loq.c(e, "ResponseAuthFirstInfo is null");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(lnz lnzVar) {
        if (lnzVar != null) {
            int a2 = lnzVar.a();
            InProgressData inProgressData = this.f6533a;
            if (inProgressData != null) {
                inProgressData.setResultcode(a2);
            }
            String str = e;
            loq.c(str, "ResponseServiceProvisionInfo.getResultcode()=" + a2);
            if (2000 == a2) {
                lob d = lnzVar.d();
                if (d != null) {
                    String a3 = d.a();
                    String b = d.b();
                    if (loq.b.booleanValue()) {
                        loq.c(str, "MultiServiceResponseHandler:url =" + a3 + ", postdata=" + b);
                    }
                    InProgressData inProgressData2 = this.f6533a;
                    if (inProgressData2 != null) {
                        inProgressData2.setTime(d.c());
                        this.f6533a.setPostdata(b);
                    }
                    loh lohVar = new loh();
                    lohVar.e(lnzVar.e());
                    lohVar.d(lnzVar.c());
                    lohVar.c(a3);
                    lohVar.a(b);
                    InProgressData inProgressData3 = this.f6533a;
                    if (inProgressData3 != null) {
                        inProgressData3.setWebViewData(lohVar);
                    }
                    lop.b(8890, this.f6533a);
                    return;
                }
                loq.c(str, "responseMultiSIMService is null");
                lop.b(115, this.f6533a);
                return;
            }
            loq.c(str, "responseServiceProvisionInfo.getResultcode() is no success");
            lop.b(115, this.f6533a);
            return;
        }
        loq.c(e, "responseServiceProvisionInfo is null");
        InProgressData inProgressData4 = this.f6533a;
        if (inProgressData4 != null) {
            inProgressData4.setResultcode(99);
        }
        lop.b(115, this.f6533a);
    }
}
