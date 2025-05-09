package com.huawei.updatesdk.service.otaupdate;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* loaded from: classes7.dex */
public class c {

    public interface a {
        void a(Boolean bool);
    }

    static class b extends AsyncTask<Void, Void, Boolean> {

        /* renamed from: a, reason: collision with root package name */
        final a f10873a;
        String b;

        public void a(String str) {
            this.b = str;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Boolean bool) {
            a aVar = this.f10873a;
            if (aVar != null) {
                aVar.a(bool);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Boolean doInBackground(Void... voidArr) {
            String str = "AutoUpdateUtil";
            Cursor cursor = null;
            boolean z = false;
            try {
                try {
                    cursor = com.huawei.updatesdk.a.b.a.a.c().a().getContentResolver().query(Uri.parse(this.b), null, null, null, null);
                    if (cursor == null || !cursor.moveToFirst()) {
                        com.huawei.updatesdk.a.a.a.a("AutoUpdateUtil", "cursor = null");
                        str = str;
                    } else {
                        boolean parseBoolean = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex("isagree")));
                        boolean parseBoolean2 = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex("isopenautoupdate")));
                        boolean parseBoolean3 = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex("isneverreminder")));
                        str = parseBoolean3;
                        str = parseBoolean3;
                        str = parseBoolean3;
                        if (parseBoolean && !parseBoolean2 && parseBoolean3 == 0) {
                            z = true;
                            str = parseBoolean3;
                        }
                    }
                } catch (Exception e) {
                    com.huawei.updatesdk.a.a.a.a(str, "query cursor error: " + e.getMessage());
                }
                com.huawei.updatesdk.b.h.d.a(cursor);
                return Boolean.valueOf(z);
            } catch (Throwable th) {
                com.huawei.updatesdk.b.h.d.a(cursor);
                throw th;
            }
        }

        public b(a aVar) {
            this.f10873a = aVar;
        }
    }

    public void a(Context context, String str, a aVar, boolean z) {
        if (!com.huawei.updatesdk.b.e.e.a(z).c(context) || !f.e().d()) {
            aVar.a(false);
            return;
        }
        String format = String.format(Locale.ROOT, "content://%s.commondata/item/4", str);
        if (!com.huawei.updatesdk.b.h.d.a(context, Uri.parse(format), str)) {
            aVar.a(false);
            return;
        }
        b bVar = new b(aVar);
        try {
            bVar.a(format);
            bVar.executeOnExecutor(com.huawei.updatesdk.b.g.c.f10843a, new Void[0]);
            bVar.get(500L, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            bVar.cancel(true);
            com.huawei.updatesdk.a.a.a.a("AutoUpdateUtil", "init AutoUpdateInfo error: " + e.getMessage());
            aVar.a(false);
        }
    }
}
