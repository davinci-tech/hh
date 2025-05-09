package com.huawei.watchface;

import com.huawei.watchface.utils.analytice.data.BaseEvent;
import java.util.LinkedHashMap;

/* loaded from: classes7.dex */
public class eb extends BaseEvent<eb> {

    /* renamed from: a, reason: collision with root package name */
    private LinkedHashMap<String, String> f11001a;

    public eb(LinkedHashMap<String, String> linkedHashMap) {
        this.f11001a = linkedHashMap;
    }

    @Override // com.huawei.watchface.em
    public LinkedHashMap<String, String> b() {
        return this.f11001a;
    }

    public static void a(LinkedHashMap<String, String> linkedHashMap) {
        new eb(linkedHashMap).a_();
    }

    @Override // com.huawei.watchface.em
    public String a() {
        return "WATCHFACE_ANR";
    }
}
