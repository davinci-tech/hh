package com.huawei.watchface;

import com.huawei.watchface.utils.analytice.data.BaseEvent;
import java.util.LinkedHashMap;

/* loaded from: classes7.dex */
public class ec extends BaseEvent<ec> {

    /* renamed from: a, reason: collision with root package name */
    private String f11002a;
    private LinkedHashMap<String, String> h;

    public ec(String str, LinkedHashMap<String, String> linkedHashMap) {
        this.f11002a = str;
        this.h = linkedHashMap;
    }

    @Override // com.huawei.watchface.em
    public String a() {
        return this.f11002a;
    }

    @Override // com.huawei.watchface.em
    public LinkedHashMap<String, String> b() {
        return this.h;
    }
}
