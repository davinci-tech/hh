package com.huawei.agconnect.apms;

import com.huawei.agconnect.apms.collect.model.event.network.HttpEvent;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.internal.Internal;
import com.squareup.okhttp.internal.http.StreamAllocation;
import java.io.IOException;

/* loaded from: classes8.dex */
public class kji implements Callback {
    public wxy abc;
    public Callback bcd;

    public kji(Callback callback, wxy wxyVar) {
        this.bcd = callback;
        this.abc = wxyVar;
    }

    public final void abc() {
        Object obj;
        gfe gfeVar = new gfe();
        if (gfeVar.bcd != 2 || gfeVar.cde < 7 || (obj = this.abc.lkj) == null || !(obj instanceof Call)) {
            return;
        }
        StreamAllocation callEngineGetStreamAllocation = Internal.instance.callEngineGetStreamAllocation((Call) obj);
        if (callEngineGetStreamAllocation.connection() == null || callEngineGetStreamAllocation.connection().socket == null || callEngineGetStreamAllocation.connection().socket.getInetAddress() == null) {
            return;
        }
        this.abc.cde(callEngineGetStreamAllocation.connection().socket.getInetAddress().getHostAddress());
    }

    public void onFailure(Request request, IOException iOException) {
        try {
            abc(iOException);
        } catch (Throwable th) {
            this.abc.qpo = r0.abc();
            abc(new Exception(th));
        }
        this.bcd.onFailure(request, iOException);
    }

    public void onResponse(Response response) throws IOException {
        try {
            abc();
            if (!this.abc.edc()) {
                response = hgf.abc(this.abc, response);
            }
        } catch (Throwable th) {
            this.abc.qpo = r0.abc();
            abc(new Exception(th));
        }
        this.bcd.onResponse(response.newBuilder().body(new dcb(response.body(), this.abc)).build());
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
}
