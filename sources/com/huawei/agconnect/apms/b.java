package com.huawei.agconnect.apms;

import com.huawei.agconnect.apms.collect.model.event.network.HttpEvent;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/* loaded from: classes2.dex */
public class b implements Callback {
    public wxy abc;
    public Callback bcd;

    public b(Callback callback, wxy wxyVar) {
        this.abc = wxyVar;
        this.bcd = callback;
    }

    public void abc(Exception exc) {
        wxy wxyVar = this.abc;
        xyz.abc(wxyVar, exc);
        if (wxyVar.edc() || wxyVar.gfe()) {
            return;
        }
        wxyVar.d();
        vwx e = wxyVar.e();
        if (e != null) {
            HttpEvent httpEvent = new HttpEvent(e, r0.abc());
            wxyVar.abc(true);
            rst.cde.add(httpEvent);
        }
    }

    @Override // okhttp3.Callback
    public void onFailure(Call call, IOException iOException) {
        try {
            abc(iOException);
        } catch (Throwable th) {
            this.abc.qpo = r0.abc();
            abc(new Exception(th));
        }
        this.bcd.onFailure(call, iOException);
    }

    @Override // okhttp3.Callback
    public void onResponse(Call call, Response response) throws IOException {
        try {
            if (!this.abc.edc()) {
                response = d.abc(this.abc, response);
            }
        } catch (Throwable th) {
            this.abc.qpo = r0.abc();
            abc(new Exception(th));
        }
        this.bcd.onResponse(call, response.newBuilder().body(new f(response.body(), this.abc)).build());
    }
}
