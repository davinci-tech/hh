package com.huawei.hms.a.a.c;

import com.huawei.hms.support.picker.request.AccountPickerParams;
import com.huawei.hms.support.picker.result.AuthAccountPicker;
import defpackage.ksy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static final a f4243a = new a();
    private Map<String, String> b = new ConcurrentHashMap();

    private a() {
    }

    public static a a() {
        return f4243a;
    }

    public void a(AuthAccountPicker authAccountPicker, AccountPickerParams accountPickerParams) {
        String json;
        ksy.b("[ACCOUNTSDK]AccountPickerMemCache", "saveDefaultAccountPickerAccount start.", true);
        if (authAccountPicker != null) {
            try {
                json = authAccountPicker.toJson();
            } catch (Throwable th) {
                ksy.c("[ACCOUNTSDK]AccountPickerMemCache", "store SignInAccount faild, exception:" + th.getClass().getSimpleName(), true);
                return;
            }
        } else {
            json = null;
        }
        a(json, accountPickerParams != null ? accountPickerParams.toJson() : null);
    }

    private void a(String str, String str2) {
        ksy.b("[ACCOUNTSDK]AccountPickerMemCache", "saveDefaultAccountPickerAccount start.", true);
        this.b.remove("AccountPickerAccount");
        this.b.remove("AccountPickerAuthParams");
        if (str != null) {
            this.b.put("AccountPickerAccount", str);
        }
        if (str2 != null) {
            this.b.put("AccountPickerAuthParams", str2);
        }
    }

    public final AuthAccountPicker b() {
        ksy.b("[ACCOUNTSDK]AccountPickerMemCache", "getAccountPickerAccount start.", true);
        try {
            String str = this.b.get("AccountPickerAccount");
            if (str != null) {
                return AuthAccountPicker.fromJson(str);
            }
        } catch (Throwable th) {
            ksy.c("[ACCOUNTSDK]AccountPickerMemCache", "getHuaweiSignInAccount faild, exception:" + th.getClass().getSimpleName(), true);
        }
        return null;
    }

    public void c() {
        this.b.clear();
    }
}
