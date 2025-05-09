package com.huawei.hms.network.embedded;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.NetworkUtil;
import com.huawei.hms.framework.common.hianalytics.LinkedHashMapPack;
import com.huawei.hms.network.httpclient.Interceptor;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.httpclient.ResponseBody;
import com.huawei.hms.network.httpclient.Submit;
import com.huawei.hms.network.netdiag.tools.NetDetectAndPolicy;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class q4 extends p4 {
    public static final String i = "NetDiagManagerImpl";
    public boolean e;
    public l5 f;
    public NetDetectAndPolicy g;
    public AtomicBoolean h = new AtomicBoolean(false);

    @Override // com.huawei.hms.network.embedded.p4
    public void d() {
        if (this.h.get()) {
            this.f.a();
        } else {
            Logger.w(i, "start has error! pls check init is ok?");
        }
    }

    @Override // com.huawei.hms.network.embedded.p4
    public void c(boolean z) {
        if (!this.h.get()) {
            Logger.w(i, "stop has error! pls check init is ok?");
            return;
        }
        this.f.a();
        if (this.e) {
            this.g.executeDetectPolicy(Boolean.valueOf(z));
        }
    }

    @Override // com.huawei.hms.network.embedded.p4
    public Map<String, Integer> c() {
        return NetworkUtil.getLteSignalInfo(ContextHolder.getResourceContext());
    }

    @Override // com.huawei.hms.network.embedded.p4
    public boolean b(long j, long j2) {
        return this.g.networkUnavailable(j, j2);
    }

    @Override // com.huawei.hms.network.embedded.p4
    public void b(boolean z) {
        this.e = z;
    }

    @Override // com.huawei.hms.network.embedded.p4
    public String b() {
        return o5.c().a();
    }

    @Override // com.huawei.hms.network.embedded.p4
    public boolean a() {
        if (this.h.get()) {
            return this.g.executeDetectPolicy();
        }
        return false;
    }

    @Override // com.huawei.hms.network.embedded.p4
    public void a(Interceptor.Chain chain) {
        o5.c().a(chain);
    }

    @Override // com.huawei.hms.network.embedded.p4
    public void a(Context context) {
        if (context == null) {
            Logger.w(i, "context == null");
        } else if (this.h.compareAndSet(false, true)) {
            l4.b().a(new m5(this.f));
        }
    }

    @Override // com.huawei.hms.network.embedded.p4
    public z4 a(long j, long j2) {
        return this.h.get() ? this.g.netDetDiagInfo(j, j2) : new a5();
    }

    @Override // com.huawei.hms.network.embedded.p4
    public t5 a(boolean z) {
        return r5.a().a(z);
    }

    @Override // com.huawei.hms.network.embedded.p4
    public n5 a(String str, List<InetAddress> list) {
        String str2;
        if (TextUtils.isEmpty(str) || list == null || list.isEmpty()) {
            str2 = "domain or addressList is empty";
        } else {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject = new JSONObject().put("enable_site_detect", true);
            } catch (JSONException e) {
                Logger.w(i, "recordMap fail to put: ", e);
            }
            try {
                Submit<ResponseBody> newSubmit = p4.e().newSubmit(p4.e().newRequest().url("https://" + str).method("GET").tag(list).options(jSONObject.toString()).build());
                Response<ResponseBody> execute = newSubmit.execute();
                if (execute == null) {
                    Logger.w(i, "Connect detect response is null");
                    return null;
                }
                Logger.i(i, "Site detect domain %s status is %d ", str, Integer.valueOf(execute.getCode()));
                return new n5(newSubmit.getRequestFinishedInfo());
            } catch (IOException unused) {
                str2 = "Connect detect failed";
            }
        }
        Logger.w(i, str2);
        return null;
    }

    @Override // com.huawei.hms.network.embedded.p4
    public LinkedHashMapPack a(String str) {
        return new LinkedHashMapPack();
    }

    public q4() {
        l5 l5Var = new l5();
        this.f = l5Var;
        this.g = new NetDetectAndPolicy(l5Var);
    }
}
