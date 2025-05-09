package com.huawei.openalliance.ad.utils;

import android.content.Context;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.util.Pair;
import com.huawei.openalliance.ad.constant.Action;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.gc;
import com.huawei.openalliance.ad.ho;

/* loaded from: classes5.dex */
public class ai {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f7577a = false;

    private static void i(Context context) {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(Action.ACTION_HW_ACCOUNT_LOGIN);
            intentFilter.addAction(Action.ACTION_HW_ACCOUNT_LOGOUT);
            ho.b("GroupIdUtils", "register hw account receiver");
            ao.a(context, new com.huawei.openalliance.ad.inter.e(), intentFilter, "com.huawei.hms.permission.INNER_BROADCAST", null);
        } catch (Throwable th) {
            ho.b("GroupIdUtils", "register hw account receiver ex: %s", th.getClass().getSimpleName());
        }
    }

    private static Pair<String, Integer> h(Context context) {
        String str = "";
        if (context == null) {
            return new Pair<>("", 1);
        }
        if (com.huawei.openalliance.ad.bz.a(context).d()) {
            return new Pair<>("", 12);
        }
        Uri parse = Uri.parse("content://com.huawei.hms.ads.brain.open/groupid/query");
        int i = 11;
        if (!ao.b(context, parse)) {
            ho.b("GroupIdUtils", "target provider not exists");
            return new Pair<>("", 11);
        }
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(parse, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                str = cursor.getString(cursor.getColumnIndex("group_id"));
                i = cursor.getInt(cursor.getColumnIndex("code"));
            }
        } finally {
            try {
                cy.a(cursor);
                return new Pair<>(str, Integer.valueOf(i));
            } catch (Throwable th) {
            }
        }
        cy.a(cursor);
        return new Pair<>(str, Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void g(Context context) {
        Pair<String, Integer> h = h(context);
        ho.a("GroupIdUtils", "update groupId: %s", dl.a((String) h.first));
        ho.b("GroupIdUtils", "update grpIdStatusCode: %s", h.second);
        cg a2 = cg.a(context);
        a2.b(((Integer) h.second).intValue());
        a2.D((String) h.first);
    }

    private static boolean f(Context context) {
        gc b = fh.b(context);
        long cf = b.cf();
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - cf < 43200000) {
            ho.a("GroupIdUtils", "update groupId too quick");
            return true;
        }
        b.m(currentTimeMillis);
        return false;
    }

    private static void e(final Context context) {
        if (f(context)) {
            return;
        }
        m.f(new Runnable() { // from class: com.huawei.openalliance.ad.utils.ai.1
            @Override // java.lang.Runnable
            public void run() {
                ai.g(context);
            }
        });
    }

    public static void c(final Context context) {
        if (f7577a) {
            Log.d("GroupIdUtils", "hasInit true");
            return;
        }
        Log.i("GroupIdUtils", "init");
        f7577a = true;
        if (f(context)) {
            Log.d("GroupIdUtils", "update groupId too quick");
        } else {
            m.f(new Runnable() { // from class: com.huawei.openalliance.ad.utils.ai.3
                @Override // java.lang.Runnable
                public void run() {
                    ai.g(context);
                }
            });
        }
        i(context);
    }

    public static void b(final Context context) {
        bo.a(new Runnable() { // from class: com.huawei.openalliance.ad.utils.ai.2
            @Override // java.lang.Runnable
            public void run() {
                m.f(new Runnable() { // from class: com.huawei.openalliance.ad.utils.ai.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ai.g(context);
                    }
                });
            }
        }, 1000L);
    }

    public static Pair<String, Integer> a(Context context) {
        e(context);
        cg a2 = cg.a(context);
        String I = a2.I();
        int S = a2.S();
        ho.a("GroupIdUtils", "get groupId: %s", dl.a(I));
        ho.b("GroupIdUtils", "get grpIdStatusCode: %s", Integer.valueOf(S));
        return new Pair<>(I, Integer.valueOf(S));
    }
}
