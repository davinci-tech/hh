package com.huawei.hwcloudjs.service.debugtool;

/* loaded from: classes9.dex */
public class SetUrl {

    /* renamed from: a, reason: collision with root package name */
    private static SetUrl f6230a;
    private GetUrlCallBack b;

    public interface GetUrlCallBack {
        void onResult(String str);
    }

    public GetUrlCallBack b() {
        return this.b;
    }

    public void a(GetUrlCallBack getUrlCallBack) {
        this.b = getUrlCallBack;
    }

    public static SetUrl a() {
        SetUrl setUrl;
        synchronized (SetUrl.class) {
            if (f6230a == null) {
                f6230a = new SetUrl();
            }
            setUrl = f6230a;
        }
        return setUrl;
    }
}
