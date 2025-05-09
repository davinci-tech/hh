package com.huawei.multisimsdk.multidevicemanager.model;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.multisimsdk.multidevicemanager.common.AuthParam;
import com.huawei.multisimsdk.multidevicemanager.common.InProgressData;
import com.huawei.multisimsdk.multidevicemanager.util.ResponseHandler;
import defpackage.lnc;
import defpackage.lnm;
import defpackage.lnp;
import defpackage.lnr;
import defpackage.lnx;
import defpackage.lny;
import defpackage.loa;
import defpackage.lod;
import defpackage.loh;
import defpackage.loi;
import defpackage.lop;
import defpackage.loq;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes5.dex */
public class QueryManager {

    /* renamed from: a, reason: collision with root package name */
    private static final String f6534a = "QueryManager";
    private static int c = 3;
    private static int e = 3000;
    private AuthParam b;
    private Context d;
    private InProgressData f;
    private Timer g;
    private int h;
    private TimerTask i;
    private ResponseHandler j;

    public void d(InProgressData inProgressData) {
        this.f = inProgressData;
    }

    public QueryManager(Context context) {
        this(context, null);
    }

    public QueryManager(Context context, AuthParam authParam) {
        this.g = null;
        this.i = null;
        this.h = 1;
        this.b = null;
        this.j = new ResponseHandler() { // from class: com.huawei.multisimsdk.multidevicemanager.model.QueryManager.2
            @Override // com.huawei.multisimsdk.multidevicemanager.util.ResponseHandler
            public void onCallback(String str) {
                ArrayList<lnm> a2;
                loa loaVar = new loa();
                loaVar.c(str);
                lnx d = loaVar.d();
                lny c2 = loaVar.c();
                if (c2 != null && c2.d() != null && (a2 = c2.d().a()) != null) {
                    int size = a2.size();
                    for (int i = 0; i < size; i++) {
                        lnm lnmVar = a2.get(i);
                        if (TextUtils.isEmpty(lnmVar.e())) {
                            lnmVar.b(lnmVar.c());
                        }
                    }
                }
                if (QueryManager.this.c(d).booleanValue()) {
                    QueryManager.this.d(c2);
                }
            }
        };
        this.d = context;
        this.b = authParam;
    }

    public void b() {
        InProgressData inProgressData = this.f;
        if (inProgressData == null) {
            return;
        }
        int type = inProgressData.getType();
        if (100 == type || 101 == type) {
            d(this.f.getTime() / 6);
        } else {
            e();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        String c2 = lod.c();
        String a2 = a();
        loi.d().d(c2, a2, null, lop.a(this.d, lnc.e(this.d, this.b), "authorization"), this.j);
    }

    private String a() {
        String str;
        lnr lnrVar = new lnr();
        lnrVar.d(lop.c());
        lnrVar.d("GetDevServInfo");
        InProgressData inProgressData = this.f;
        if (inProgressData != null) {
            str = inProgressData.getPrimary();
            lnrVar.f(this.f.getServiceType());
            lnrVar.a(str);
            lnrVar.c(this.f.getPrimaryIDtype());
            lnrVar.j(this.f.getSecondarytype());
            lnrVar.e(this.f.getSecondaryID());
        } else {
            str = null;
        }
        String a2 = lop.a(this.d, str, "OldTimeStamp");
        if (!TextUtils.isEmpty(a2)) {
            lnrVar.b(a2);
        }
        lnp lnpVar = new lnp();
        lnpVar.c(lnrVar);
        lnpVar.a(lop.d(this.d, lnc.e(this.d, this.b)));
        return lnpVar.a();
    }

    private void b(lny lnyVar) {
        if (lnyVar != null) {
            int c2 = lnyVar.c();
            this.f.setResultcode(c2);
            String str = f6534a;
            loq.c(str, "Get query resultcode=" + c2);
            if (1500 == c2) {
                loq.c(str, "get add-query result");
                lop.d(this.d, this.f.getPrimary(), "OldTimeStamp", lnyVar.e());
                this.f.setMultiSIMServiceInfo(lnyVar.d());
                lop.b(125, this.f);
                return;
            }
            if (1502 == c2) {
                long time = this.f.getTime() / 6;
                int i = this.h;
                if (i < c) {
                    this.h = i + 1;
                    loq.c(str, "get add-query timer = " + this.h);
                    d();
                    if (time > 0) {
                        d(time * this.h);
                        return;
                    } else {
                        d(e);
                        return;
                    }
                }
                loq.c(str, "get add-query result");
                lop.b(126, this.f);
                return;
            }
            loq.c(str, "get query response send to fail");
            lop.b(126, this.f);
            return;
        }
        loq.c(f6534a, "responseGetDevServInfo is null");
        this.f.setResultcode(99);
        lop.b(126, this.f);
    }

    private void a(lny lnyVar) {
        if (lnyVar != null) {
            int c2 = lnyVar.c();
            this.f.setResultcode(c2);
            String str = f6534a;
            loq.c(str, "get query resultcode=" + c2);
            if (1502 == c2) {
                loq.c(str, "get Remove-query result");
                lop.d(this.d, this.f.getPrimary(), "OldTimeStamp", lnyVar.e());
                this.f.setMultiSIMServiceInfo(lnyVar.d());
                lop.b(125, this.f);
                return;
            }
            if (1500 == c2) {
                long time = this.f.getTime() / 6;
                int i = this.h;
                if (i < c) {
                    this.h = i + 1;
                    loq.c(str, "get Remove-query timer = " + this.h);
                    d();
                    if (time > 0) {
                        d(time * this.h);
                        return;
                    } else {
                        d(e);
                        return;
                    }
                }
                loq.c(str, "get Remove-query result ");
                lop.b(126, this.f);
                return;
            }
            loq.c(str, "get query response send to fail ");
            lop.b(126, this.f);
            return;
        }
        loq.c(f6534a, "responseGetDevServInfo is null");
        this.f.setResultcode(99);
        lop.b(126, this.f);
    }

    private void e(lny lnyVar) {
        if (lnyVar != null) {
            int c2 = lnyVar.c();
            this.f.setResultcode(c2);
            String str = f6534a;
            loq.c(str, "get query resultcode=" + c2);
            String e2 = lnc.e(this.d, this.b);
            lop.d(this.d, e2, "manager_url", lnyVar.b());
            lop.d(this.d, e2, "manager_post_data", lnyVar.a());
            loh lohVar = new loh();
            lohVar.c(lnyVar.b());
            lohVar.a(lnyVar.a());
            this.f.setWebViewData(lohVar);
            if (1500 == c2) {
                loq.c(str, "start query service");
                lop.d(this.d, this.f.getPrimary(), "OldTimeStamp", lnyVar.e());
                this.f.setMultiSIMServiceInfo(lnyVar.d());
                lop.b(125, this.f);
                return;
            }
            if (1501 == c2) {
                loq.c(str, "get query response data no change ");
                lop.b(125, this.f);
                return;
            } else {
                loq.c(str, "get query response send to fail resultcode =" + c2);
                lop.b(126, this.f);
                return;
            }
        }
        loq.c(f6534a, "responseGetDevServInfo is null");
        this.f.setResultcode(99);
        lop.b(126, this.f);
    }

    private void d(long j) {
        if (this.g == null) {
            this.g = new Timer();
        }
        if (this.i == null) {
            this.i = new TimerTask() { // from class: com.huawei.multisimsdk.multidevicemanager.model.QueryManager.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    QueryManager.this.e();
                }
            };
        }
        this.g.schedule(this.i, j);
    }

    private void d() {
        Timer timer = this.g;
        if (timer != null) {
            timer.cancel();
            this.g = null;
        }
        TimerTask timerTask = this.i;
        if (timerTask != null) {
            timerTask.cancel();
            this.i = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Boolean c(lnx lnxVar) {
        if (lnxVar != null) {
            int e2 = lnxVar.e();
            this.f.setResultcode(e2);
            String str = f6534a;
            loq.c(str, "handlerQuickAuthenResult.getResultcode()=" + e2);
            if (1000 != e2) {
                if (1004 == e2) {
                    loq.c(str, "TokenAuthen is invalid");
                    lop.b(107, this.f);
                    return false;
                }
                loq.c(str, "TokenAuthen is fail");
                lop.b(106, this.f);
                return false;
            }
            String e3 = lnc.e(this.d, this.b);
            String a2 = lop.a(this.d, e3, "Tag");
            if (!TextUtils.isEmpty(e3)) {
                lop.d(this.d, e3, "authen_Token", lnxVar.d());
            }
            if (!TextUtils.isEmpty(a2)) {
                lop.d(this.d, a2, "authen_Token", lnxVar.d());
            }
            if (!TextUtils.isEmpty(e3) && !TextUtils.isEmpty(a2)) {
                lop.e(this.d, a2, e3);
            }
            loq.c(str, "TokenAuthen is valid");
            return true;
        }
        loq.c(f6534a, "responseAuthFirstInfo is null");
        lop.b(106, this.f);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(lny lnyVar) {
        InProgressData inProgressData = this.f;
        if (inProgressData == null) {
            return;
        }
        int type = inProgressData.getType();
        loq.c(f6534a, "type = " + type);
        if (100 == type) {
            b(lnyVar);
        } else if (101 == type) {
            a(lnyVar);
        } else {
            e(lnyVar);
        }
    }
}
