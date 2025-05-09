package com.huawei.healthcloud.plugintrack.sportmusic;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.gww;
import health.compact.a.LogAnonymous;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;

/* loaded from: classes8.dex */
public class SportMusicProvider extends ContentProvider {
    private static final UriMatcher c;

    @Override // android.content.ContentProvider
    public int bulkInsert(Uri uri, ContentValues[] contentValuesArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return false;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    static {
        UriMatcher uriMatcher = new UriMatcher(-1);
        c = uriMatcher;
        uriMatcher.addURI("com.huawei.health.sport.musicprovider", null, 0);
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        if (uri == null || contentValues == null) {
            LogUtil.b("Track_SportMusicProvider", "uri or values is null");
            return null;
        }
        if (c.match(uri) == 0) {
            Integer asInteger = contentValues.getAsInteger("operatorType");
            String asString = contentValues.getAsString("contentId");
            String asString2 = contentValues.getAsString("contentElement");
            int b = b();
            LogUtil.a("SportMusicProvider", "operatorType ", asInteger, " contentId ", asString, " contentElement ", asString2, " sportType ", Integer.valueOf(b));
            aXL_(contentValues, b);
        }
        return null;
    }

    private void aXL_(ContentValues contentValues, int i) {
        gww gwwVar = new gww(BaseApplication.getContext(), new StorageParams(), Integer.toString(20002));
        if (contentValues.getAsInteger("operatorType") != null) {
            if (contentValues.getAsInteger("operatorType").intValue() == 1) {
                gwwVar.b(contentValues.getAsString("contentId"), i);
                gwwVar.e(contentValues.getAsString("contentElement"), i);
            } else {
                gwwVar.b("", i);
                gwwVar.e("", i);
            }
            gwwVar.a(contentValues.getAsInteger("operatorType").intValue(), i);
            return;
        }
        gwwVar.b("", i);
        gwwVar.e("", i);
    }

    private int b() {
        try {
            return Integer.parseInt(SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(20002), "map_tracking_sport_type"));
        } catch (NumberFormatException e) {
            LogUtil.b("Track_SportMusicProvider", "getSportType NumberFormatException", LogAnonymous.b((Throwable) e));
            return 0;
        }
    }
}
