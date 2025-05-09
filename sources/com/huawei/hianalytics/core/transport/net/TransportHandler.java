package com.huawei.hianalytics.core.transport.net;

import android.text.TextUtils;
import com.huawei.hianalytics.core.transport.Utils;
import com.huawei.hianalytics.core.transport.net.Response;
import java.util.Map;

/* loaded from: classes4.dex */
public abstract class TransportHandler {

    /* renamed from: a, reason: collision with root package name */
    public String f3845a;
    public Map<String, String> b;
    public byte[] c;

    /* loaded from: classes8.dex */
    public interface Methods {
        public static final String METHOD_GET = "GET";
        public static final String METHOD_POST = "POST";
    }

    public abstract Response a();

    public abstract Response b();

    public Response execute(String str) {
        if (TextUtils.isEmpty(this.f3845a)) {
            return new Response(Response.Code.HOST_ERROR, "collectUrls is empty");
        }
        try {
            return "GET".equals(str) ? a() : b();
        } catch (Exception e) {
            return Utils.handlerException(e);
        }
    }

    public Response execute() {
        return execute("POST");
    }

    public TransportHandler(String str, Map<String, String> map, byte[] bArr) {
        this.f3845a = str;
        this.b = map;
        this.c = bArr != null ? (byte[]) bArr.clone() : new byte[0];
    }
}
