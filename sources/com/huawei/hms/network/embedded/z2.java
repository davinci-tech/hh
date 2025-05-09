package com.huawei.hms.network.embedded;

import com.huawei.hms.network.embedded.h1;
import com.huawei.hms.network.httpclient.Request;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.httpclient.Submit;
import java.io.IOException;

/* loaded from: classes9.dex */
public abstract class z2 {
    public static final int ALL_LISTENER_FINISH = 3;
    public static final int NET_LIB_LISTENER_FINISH = 1;
    public static final z2 NONE = new a();
    public static final int RCEVENT_LISTENER_FINISH = 2;

    public class a extends z2 {
    }

    public interface c {
        z2 create(Submit submit);
    }

    public void acquireClient(a1 a1Var) {
    }

    public void acquireRequestEnd(h1.d dVar) {
    }

    public void acquireRequestStart() {
    }

    public void callEnd(Response response) {
    }

    public void callFailed(Exception exc) {
    }

    public void callFinishAtNetLib(int i) {
    }

    public void callStart() {
    }

    public void cancel() {
    }

    public void convertGrsEnd(String str) {
    }

    public void convertGrsStart(String str) {
    }

    public void cpApplicationInterceptorReqEnd() {
    }

    public void cpApplicationInterceptorResEnd() {
    }

    public void cpApplicationInterceptorResStart() {
    }

    public void cpNetworkInterceptorReqEnd() {
    }

    public void cpNetworkInterceptorReqStart() {
    }

    public void cpNetworkInterceptorResEnd() {
    }

    public void cpNetworkInterceptorResStart() {
    }

    public void rcNetworkInterceptorReqEnd(h1.d dVar) {
    }

    public void rcNetworkInterceptorResStart() {
    }

    public void recordCpApplicationInterceptorNums(int i) {
    }

    public void recordCpNetworkInterceptorNums(int i) {
    }

    public void responseBodyEnd() {
    }

    public void responseFailed() {
    }

    public void retryInterceptorEnd(Response response, a1 a1Var) {
    }

    public void retryInterceptorFailed(IOException iOException) {
    }

    public void retryInterceptorStart(Request request, d1 d1Var, long j) {
    }

    public void traceResponseNetworkKitInEvent(String str, String str2) {
    }

    public void traceResponseNetworkKitOutEvent(String str) {
    }

    public class b implements c {
        @Override // com.huawei.hms.network.embedded.z2.c
        public z2 create(Submit submit) {
            return z2.this;
        }

        public b() {
        }
    }

    public static c factory(z2 z2Var) {
        return z2Var.new b();
    }
}
