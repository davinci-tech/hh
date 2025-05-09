package com.huawei.watchface.mvp.model.thread;

import com.huawei.watchface.api.ResponseListener;
import com.huawei.watchface.cc;
import java.util.LinkedHashMap;

/* loaded from: classes7.dex */
public abstract class MembershipPostRequest<T> extends cc<T> {
    protected abstract void addHeaderParams(LinkedHashMap<String, String> linkedHashMap);

    @Override // com.huawei.watchface.cc
    public abstract String buildRequestParams();

    public MembershipPostRequest(ResponseListener<T> responseListener, String str) {
        a(responseListener, str);
    }

    @Override // com.huawei.watchface.cc
    public String b(String str) {
        return a(str, buildRequestParams(), c());
    }

    private LinkedHashMap<String, String> c() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        addHeaderParams(linkedHashMap);
        return linkedHashMap;
    }
}
