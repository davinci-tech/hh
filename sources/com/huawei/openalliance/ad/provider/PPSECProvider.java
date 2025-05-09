package com.huawei.openalliance.ad.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.cb;
import com.huawei.openalliance.ad.utils.i;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class PPSECProvider extends ContentProvider {

    /* renamed from: a, reason: collision with root package name */
    private final Map<String, a> f7456a = new HashMap();
    private final a b = new a() { // from class: com.huawei.openalliance.ad.provider.PPSECProvider.1
        @Override // com.huawei.openalliance.ad.provider.a
        public Bundle a(String str, Bundle bundle) {
            String a2;
            Bundle bundle2 = new Bundle();
            try {
                a2 = cb.a(PPSECProvider.this.getContext());
                ho.b("PPSATProvider", "callingPackage " + a2);
            } catch (Throwable th) {
                bundle2.putInt("errcode", 5);
                ho.c("PPSATProvider", "getAccessToken exception: " + th.getClass().getSimpleName());
            }
            if (TextUtils.isEmpty(a2)) {
                bundle2.putInt("errcode", 2);
                return bundle2;
            }
            if (!i.g(PPSECProvider.this.getContext(), a2)) {
                bundle2.putInt("errcode", 3);
                return bundle2;
            }
            String b = com.huawei.openalliance.ad.inter.a.a().b();
            if (TextUtils.isEmpty(b)) {
                ho.c("PPSATProvider", "at is null.");
                bundle2.putInt("errcode", 4);
                return bundle2;
            }
            bundle2.putString("accessToken", b);
            bundle2.putInt("errcode", 0);
            return bundle2;
        }
    };

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        this.f7456a.put("getAccessToken", this.b);
        return false;
    }

    @Override // android.content.ContentProvider
    public Bundle call(String str, String str2, Bundle bundle) {
        ho.b("PPSATProvider", "call method: " + str);
        a aVar = this.f7456a.get(str);
        if (aVar != null) {
            return aVar.a(str2, bundle);
        }
        Bundle bundle2 = new Bundle();
        bundle2.putInt("errcode", 1);
        return bundle2;
    }
}
