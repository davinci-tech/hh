package com.huawei.watchface;

import com.huawei.watchface.utils.analytice.data.BaseEvent;
import java.util.LinkedHashMap;

/* loaded from: classes7.dex */
public class ed extends BaseEvent<ed> {

    /* renamed from: a, reason: collision with root package name */
    private LinkedHashMap<String, String> f11003a;

    public ed(LinkedHashMap<String, String> linkedHashMap) {
        this.f11003a = linkedHashMap;
    }

    @Override // com.huawei.watchface.em
    public LinkedHashMap<String, String> b() {
        return this.f11003a;
    }

    public static void a(LinkedHashMap<String, String> linkedHashMap) {
        new ed(linkedHashMap).a_();
    }

    @Override // com.huawei.watchface.em
    public String a() {
        return "WATCHFACE_CRASH";
    }
}
