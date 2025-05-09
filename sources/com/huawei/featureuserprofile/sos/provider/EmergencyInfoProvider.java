package com.huawei.featureuserprofile.sos.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Bundle;
import com.huawei.featureuserprofile.sos.manager.EmergencyInfoManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes8.dex */
public class EmergencyInfoProvider extends ContentProvider {

    /* renamed from: a, reason: collision with root package name */
    private static final String f2035a = "EmergencyInfoProvider";
    private UriMatcher c;
    private String e = BaseApplication.getAppPackage() + ".sos.provider";

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
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    private void c() {
        UriMatcher uriMatcher = new UriMatcher(-1);
        this.c = uriMatcher;
        uriMatcher.addURI(this.e, "getString", 1);
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        LogUtil.c(f2035a, "EmergencyInfoProvider onCreate().");
        c();
        return false;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        Bundle bundle = new Bundle();
        if (this.c.match(uri) == 1) {
            EmergencyInfoManager c = EmergencyInfoManager.c();
            if (c.f()) {
                c.c(false);
            } else {
                c.t();
            }
            bundle.putString("value", c.i());
            LogUtil.a(f2035a, "query getEmergencyInfo");
        } else {
            LogUtil.h(f2035a, "query getEmergencyInfo mUriMatcher not get string");
        }
        return new d(bundle);
    }

    static final class d extends MatrixCursor {

        /* renamed from: a, reason: collision with root package name */
        private Bundle f2036a;

        d(Bundle bundle) {
            super(new String[0], 0);
            this.f2036a = bundle;
        }

        @Override // android.database.AbstractCursor, android.database.Cursor
        public Bundle getExtras() {
            return this.f2036a;
        }

        @Override // android.database.AbstractCursor, android.database.Cursor
        public Bundle respond(Bundle bundle) {
            this.f2036a = bundle;
            return bundle;
        }
    }
}
