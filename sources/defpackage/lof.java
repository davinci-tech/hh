package defpackage;

import android.content.Context;
import android.os.Message;
import com.huawei.multisimsdk.multidevicemanager.util.ResponseHandler;
import java.util.ArrayList;

/* loaded from: classes5.dex */
class lof {
    private static final String c = "AkaAuthen";
    private Message b;
    private Context e;

    /* renamed from: a, reason: collision with root package name */
    private ArrayList f14801a = new ArrayList();
    private ArrayList d = new ArrayList();
    private String i = null;
    private ResponseHandler j = new ResponseHandler() { // from class: lof.2
        @Override // com.huawei.multisimsdk.multidevicemanager.util.ResponseHandler
        public void onCallback(String str) {
            lnx lnxVar = new lnx();
            if (lop.i(str)) {
                lnxVar.e(str);
            } else {
                lnxVar.a(str);
            }
            if (!lof.this.a(Integer.valueOf(lnxVar.a()))) {
                loq.c(lof.c, "Handle Sms First Authen Response reqsn is error.");
                return;
            }
            if (!"DevAuth".equals(lnxVar.b())) {
                loq.c(lof.c, "Handle Sms First Authen Response reqname is error.");
                return;
            }
            int e = lnxVar.e();
            loq.c(lof.c, "HandleAkASFirstAuthenResponse resultcode=" + e);
            if (1008 == e) {
                lod.a(lof.this.e);
                String c2 = lod.c(lnxVar.c());
                if (lof.this.b == null) {
                    loq.c(lof.c, "Handle second response mMessage is null");
                }
                String e2 = lop.e(String.valueOf(lnxVar.a()));
                if (loq.b.booleanValue()) {
                    loq.c(lof.c, "HandleAKAFirstAuthenResponse--payload=" + c2);
                    loq.c(lof.c, "Handle Start to secondauthen cookie =" + e2);
                }
                lof.this.b(c2, e2);
                return;
            }
            lop.bYp_(lof.this.b, e);
        }
    };
    private ResponseHandler g = new ResponseHandler() { // from class: lof.5
        @Override // com.huawei.multisimsdk.multidevicemanager.util.ResponseHandler
        public void onCallback(String str) {
            lnw lnwVar = new lnw();
            if (lop.i(str)) {
                lnwVar.b(str);
            } else {
                lnwVar.d(str);
            }
            if (!lof.this.e(Integer.valueOf(lnwVar.d()))) {
                loq.c(lof.c, "Handle Sms second challenge Response reqsn is error.");
                return;
            }
            if (!"DevAuthChallenge".equals(lnwVar.e())) {
                loq.c(lof.c, "Handle Sms second challenge Response reqname is error.");
                return;
            }
            if (1000 == lnwVar.b()) {
                lof.this.i = lnwVar.a();
                loq.c(lof.c, "Handle second response mPhoneNumber = " + lof.this.i);
                if (lof.this.i != null) {
                    loq.c(lof.c, "Handle second response mPhoneNumber is not null");
                    lop.d(lof.this.e, lof.this.i, "authen_Token", lnwVar.c());
                }
            }
            if (lof.this.b != null) {
                lof.this.b.arg1 = lnwVar.b();
                lof.this.b.obj = lnwVar.a();
                lop.bYq_(lof.this.b);
                loq.c(lof.c, "Handle second response success");
            }
        }
    };

    lof(Context context, Message message) {
        this.e = context;
        this.b = message;
    }

    public void c(String str) {
        loi.d().c(lod.d(), b(str), this.j);
        loq.c(c, "Start aka first authen");
    }

    void b(String str, String str2) {
        if (this.b == null) {
            loq.c(c, "Start Aka Second Authen mMessage is null");
            return;
        }
        loi.d().d(lod.d(), d(str), str2, this.g);
        loq.c(c, "Start Aka Second Authen");
    }

    private void d(Integer num) {
        ArrayList arrayList = this.f14801a;
        if (arrayList == null || num == null) {
            return;
        }
        synchronized (arrayList) {
            if (!this.f14801a.contains(num)) {
                this.f14801a.add(num);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(Integer num) {
        ArrayList arrayList = this.f14801a;
        if (arrayList == null || num == null) {
            return false;
        }
        synchronized (arrayList) {
            if (!this.f14801a.contains(num)) {
                return false;
            }
            this.f14801a.remove(num);
            return true;
        }
    }

    private void c(Integer num) {
        ArrayList arrayList = this.d;
        if (arrayList == null || num == null) {
            return;
        }
        synchronized (arrayList) {
            if (!this.d.contains(num)) {
                this.d.add(num);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e(Integer num) {
        ArrayList arrayList = this.d;
        if (arrayList == null || num == null) {
            return false;
        }
        synchronized (arrayList) {
            if (!this.d.contains(num)) {
                return false;
            }
            this.d.remove(num);
            return true;
        }
    }

    private String b(String str) {
        lnq lnqVar = new lnq();
        int c2 = lop.c();
        d(Integer.valueOf(c2));
        lnqVar.c(c2);
        lnqVar.a("DevAuth");
        lnqVar.e("EAP-AKA");
        lnqVar.c(str);
        return lnqVar.a().toString();
    }

    private String d(String str) {
        lno lnoVar = new lno();
        int c2 = lop.c();
        c(Integer.valueOf(c2));
        lnoVar.c(c2);
        lnoVar.e("DevAuthChallenge");
        lnoVar.d(str);
        lnoVar.c("EAP-AKA");
        return lnoVar.b().toString();
    }
}
