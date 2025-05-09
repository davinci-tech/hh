package com.huawei.openalliance.ad.utils;

import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Pair;
import com.huawei.openalliance.ad.ho;
import java.io.Closeable;

/* loaded from: classes5.dex */
public class bz {

    /* renamed from: a, reason: collision with root package name */
    private static final UriMatcher f7650a;

    public static Pair<Integer, String> a(Context context, String str, String str2) {
        Object th;
        Cursor cursor;
        Uri parse;
        if (context == null) {
            return null;
        }
        if (str2 == null) {
            str2 = "";
        }
        try {
            parse = Uri.parse("content://com.huawei.hms.ads.brain.open" + str);
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
        }
        if (!ao.b(context, parse)) {
            ho.c("OpenDcUtil", "provider uri invalid.");
            cy.a((Closeable) null);
            return null;
        }
        ho.b("OpenDcUtil", "call open dc provider");
        cursor = context.getContentResolver().query(parse, null, null, new String[]{String.valueOf(f7650a.match(parse)), str2}, null);
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    return new Pair<>(Integer.valueOf(cursor.getInt(cursor.getColumnIndex("code"))), cursor.getString(cursor.getColumnIndex("result")));
                }
            } catch (Throwable th3) {
                th = th3;
                try {
                    ho.c("OpenDcUtil", "sendCommandToDc " + th.getClass().getSimpleName());
                    return null;
                } finally {
                    cy.a(cursor);
                }
            }
        }
        return null;
    }

    static {
        UriMatcher uriMatcher = new UriMatcher(-1);
        f7650a = uriMatcher;
        uriMatcher.addURI("com.huawei.hms.ads.brain.open", "/insapp/query", 1);
        uriMatcher.addURI("com.huawei.hms.ads.brain.open", "/dd/sync", 14);
        uriMatcher.addURI("com.huawei.hms.ads.brain.open", "/adid/sync", 19);
    }
}
