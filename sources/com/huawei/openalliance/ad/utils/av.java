package com.huawei.openalliance.ad.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;
import com.huawei.agconnect.apms.collect.model.HeaderType;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.openalliance.ad.beans.AgendaBean;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.views.PPSWebView;
import com.huawei.operation.utils.Constants;
import com.huawei.wearengine.sensor.DataResult;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/* loaded from: classes5.dex */
public class av {

    /* renamed from: a, reason: collision with root package name */
    private static String[] f7598a = {"android.permission.READ_CALENDAR", "android.permission.WRITE_CALENDAR"};
    private static String[] b = {"android.permission.READ_CALENDAR"};
    private static String[] c;
    private static String[] d;
    private Context e;
    private ContentRecord f;
    private PPSWebView g;
    private com.huawei.openalliance.ad.analysis.h h;
    private String i;
    private AgendaBean j;
    private String k;
    private String l;
    private boolean m = false;

    @JavascriptInterface
    public void cancel(String str, String str2) {
        if (cz.b(str)) {
            ho.c("IPPSAppointJs", "cancel failed, title is empty.");
            a(str2, 1, R.string._2130851019_res_0x7f0234cb);
            this.h.c(this.f, 1);
            return;
        }
        if (ho.a()) {
            ho.a("IPPSAppointJs", "cancel title= %s", str);
        }
        if (cz.b(str2)) {
            ho.c("IPPSAppointJs", "cancel, recall funcName is empty.");
        }
        this.l = str2;
        this.k = str;
        if (a(this.f)) {
            e();
        } else {
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.utils.av.3
                @Override // java.lang.Runnable
                public void run() {
                    av.this.d();
                }
            });
        }
    }

    public void b(boolean z, boolean z2) {
        if (z) {
            b(this.k);
            return;
        }
        ho.c("IPPSAppointJs", "cancel failed, not allowed permissions");
        if (z2) {
            a(this.l, 5, R.string._2130851019_res_0x7f0234cb);
        } else {
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.utils.av.10
                @Override // java.lang.Runnable
                public void run() {
                    av avVar = av.this;
                    if (avVar.a(avVar.l)) {
                        WebView webView = av.this.g.getWebView();
                        String str = Constants.JAVA_SCRIPT + av.this.l + "(5)";
                        webView.loadUrl(str);
                        WebViewInstrumentation.loadUrl(webView, str);
                    }
                }
            });
        }
        this.h.c(this.f, 5);
    }

    @JavascriptInterface
    public void appoint(String str, String str2) {
        ho.b("IPPSAppointJs", "call appoint from js");
        if (cz.b(str)) {
            ho.c("IPPSAppointJs", "appoint failed: missing required parameters");
            a(str2, 1, R.string._2130851016_res_0x7f0234c8);
            this.h.a(this.f, 1);
            return;
        }
        if (ho.a()) {
            ho.a("IPPSAppointJs", "appoint info= %s", str);
        }
        if (cz.b(str2)) {
            ho.c("IPPSAppointJs", "appoint, recall funcName is empty.");
        }
        AgendaBean agendaBean = (AgendaBean) be.b(str, AgendaBean.class, new Class[0]);
        if (agendaBean == null) {
            ho.c("IPPSAppointJs", "appoint failed: missing required parameters");
            a(str2, 1, R.string._2130851016_res_0x7f0234c8);
            this.h.a(this.f, 1);
            return;
        }
        if (b(agendaBean) || a(agendaBean)) {
            ho.c("IPPSAppointJs", "appoint failed: missing required parameters");
            a(str2, 1, R.string._2130851016_res_0x7f0234c8);
            this.h.a(this.f, 1);
        } else {
            if (agendaBean.c() < System.currentTimeMillis()) {
                ho.c("IPPSAppointJs", "appoint failed: date start time before now");
                a(str2, 2, R.string._2130851016_res_0x7f0234c8);
                this.h.a(this.f, 2);
                return;
            }
            if (agendaBean.e() != 1 && agendaBean.e() != 0) {
                agendaBean.a(0);
            }
            this.j = agendaBean;
            this.i = str2;
            if (a(this.f)) {
                g();
            } else {
                dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.utils.av.4
                    @Override // java.lang.Runnable
                    public void run() {
                        av.this.f();
                    }
                });
            }
        }
    }

    public boolean a() {
        if (!this.m) {
            return false;
        }
        Cursor k = k();
        if (k != null) {
            try {
                if (k.getCount() > 0) {
                    if (k == null) {
                        return true;
                    }
                    k.close();
                    return true;
                }
            } finally {
                if (k != null) {
                    k.close();
                }
            }
        }
        return false;
    }

    public void a(boolean z, boolean z2) {
        if (!z) {
            ho.c("IPPSAppointJs", "appoint failed: not allowed permissions");
            if (z2) {
                a(this.i, 5, R.string._2130851016_res_0x7f0234c8);
            } else {
                dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.utils.av.9
                    @Override // java.lang.Runnable
                    public void run() {
                        av avVar = av.this;
                        if (avVar.a(avVar.i)) {
                            WebView webView = av.this.g.getWebView();
                            String str = Constants.JAVA_SCRIPT + av.this.i + "(5)";
                            webView.loadUrl(str);
                            WebViewInstrumentation.loadUrl(webView, str);
                        }
                    }
                });
            }
            this.h.a(this.f, 5);
            return;
        }
        if (!a()) {
            a(this.j, this.i);
            return;
        }
        ho.c("IPPSAppointJs", "appoint failed: already appointed");
        a(this.i, 3, R.string._2130851014_res_0x7f0234c6);
        this.h.a(this.f, 3);
    }

    public void a(AgendaBean agendaBean, String str) {
        String str2;
        int j = j();
        if (j < 0) {
            ho.c("IPPSAppointJs", "appoint failed: get calendar account error");
            a(this.i, 6, R.string._2130851016_res_0x7f0234c8);
            this.h.a(this.f, 6);
            return;
        }
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("title", agendaBean.a());
            contentValues.put("description", agendaBean.h());
            contentValues.put("eventLocation", agendaBean.b());
            contentValues.put("calendar_id", Integer.valueOf(j));
            if (agendaBean.e() == 0) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(agendaBean.c());
                contentValues.put("dtstart", Long.valueOf(calendar.getTime().getTime()));
                calendar.setTimeInMillis(agendaBean.d());
                contentValues.put("dtend", Long.valueOf(calendar.getTime().getTime()));
                str2 = agendaBean.f();
            } else {
                long a2 = a(new Date(agendaBean.c()));
                long a3 = a(new Date(agendaBean.d()));
                if (a2 == a3 || agendaBean.d() >= a3) {
                    ho.a("IPPSAppointJs", "add one day");
                    a3 += 86400000;
                }
                contentValues.put("dtstart", Long.valueOf(a2));
                contentValues.put("dtend", Long.valueOf(a3));
                str2 = DataResult.UTC;
            }
            contentValues.put("eventTimezone", str2);
            contentValues.put("allDay", Integer.valueOf(agendaBean.e()));
            contentValues.put("hasAlarm", (Integer) 1);
            contentValues.put("guestsCanModify", (Integer) 1);
            Uri parse = Uri.parse("content://com.android.calendar/events");
            if (!ao.b(this.e, parse)) {
                ho.c("IPPSAppointJs", "provider uri invalid.");
                a(this.i, 9, R.string._2130851016_res_0x7f0234c8);
                this.h.a(this.f, 9);
                return;
            }
            Uri insert = this.e.getContentResolver().insert(parse, contentValues);
            if (insert == null) {
                ho.c("IPPSAppointJs", "appoint failed: insert error");
                a(this.i, 7, R.string._2130851016_res_0x7f0234c8);
                this.h.a(this.f, 7);
                return;
            }
            ho.b("IPPSAppointJs", "appoint success");
            a(this.i, 0, R.string._2130851017_res_0x7f0234c9);
            this.h.g(this.f);
            if (agendaBean.g() == null || agendaBean.g().intValue() < 0) {
                return;
            }
            ContentValues contentValues2 = new ContentValues();
            contentValues2.put(HeaderType.EVENT_ID, Long.valueOf(ContentUris.parseId(insert)));
            contentValues2.put("minutes", agendaBean.g());
            contentValues2.put("method", (Integer) 1);
            Uri parse2 = Uri.parse("content://com.android.calendar/reminders");
            if (!ao.b(this.e, parse2)) {
                ho.c("IPPSAppointJs", "provider uri invalid.");
            } else if (this.e.getContentResolver().insert(parse2, contentValues2) == null) {
                ho.c("IPPSAppointJs", "add reminds error");
            }
        } catch (Throwable th) {
            ho.c("IPPSAppointJs", "addCalendarEvent error: " + th.getClass().getSimpleName());
            a(this.i, 7, R.string._2130851016_res_0x7f0234c8);
            this.h.a(this.f, 7);
        }
    }

    private Cursor k() {
        try {
            Uri parse = Uri.parse("content://com.android.calendar/events");
            if (!ao.b(this.e, parse)) {
                ho.c("IPPSAppointJs", "provider uri invalid.");
                return null;
            }
            if (this.j.e() == 0) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(this.j.c());
                long time = calendar.getTime().getTime();
                calendar.setTimeInMillis(this.j.d());
                return this.e.getContentResolver().query(parse, null, "title=? and dtstart=? and dtend=?", new String[]{this.j.a(), String.valueOf(time), String.valueOf(calendar.getTime().getTime())}, null);
            }
            long a2 = a(new Date(this.j.c()));
            long a3 = a(new Date(this.j.d()));
            if (a2 == a3 || this.j.d() >= a3) {
                ho.a("IPPSAppointJs", "add one day");
                a3 += 86400000;
            }
            ho.c("IPPSAppointJs", "startTime = %s   endTime= %s", Long.valueOf(a2), Long.valueOf(a3));
            return this.e.getContentResolver().query(parse, null, "title=? and dtstart=? and dtend=?", new String[]{this.j.a(), String.valueOf(a2), String.valueOf(a3)}, null);
        } catch (Throwable th) {
            ho.c("IPPSAppointJs", "query failed: error= " + th.getClass().getSimpleName());
            return null;
        }
    }

    private int j() {
        if (!this.m) {
            return 1;
        }
        int h = h();
        if (h >= 0) {
            return h;
        }
        if (i() >= 0) {
            return h();
        }
        return -1;
    }

    private long i() {
        try {
            TimeZone timeZone = TimeZone.getDefault();
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", "pps");
            contentValues.put("account_type", "com.android.huawei");
            contentValues.put("account_name", "pps");
            contentValues.put("calendar_displayName", "PPS账户");
            contentValues.put("calendar_color", (Integer) (-16776961));
            contentValues.put("visible", (Integer) 1);
            contentValues.put("calendar_access_level", (Integer) 700);
            contentValues.put("ownerAccount", "pps");
            contentValues.put("sync_events", (Integer) 1);
            contentValues.put("calendar_timezone", timeZone.getID());
            contentValues.put("canOrganizerRespond", (Integer) 0);
            Uri build = Uri.parse("content://com.android.calendar/calendars").buildUpon().appendQueryParameter("caller_is_syncadapter", "true").appendQueryParameter("account_type", "com.android.huawei").appendQueryParameter("account_name", "pps").build();
            if (!ao.b(this.e, build)) {
                ho.c("IPPSAppointJs", "provider uri invalid.");
                return -1L;
            }
            Uri insert = this.e.getContentResolver().insert(build, contentValues);
            if (insert == null) {
                return -1L;
            }
            return ContentUris.parseId(insert);
        } catch (Throwable th) {
            ho.c("IPPSAppointJs", "addCalendarAccount error: " + th.getClass().getSimpleName());
            return -1L;
        }
    }

    private int h() {
        Cursor query = this.e.getContentResolver().query(Uri.parse("content://com.android.calendar/calendars"), null, null, null, null);
        if (query == null) {
            return -1;
        }
        try {
            if (query.getCount() <= 0) {
                if (query != null) {
                    query.close();
                }
                return -1;
            }
            query.moveToFirst();
            int i = query.getInt(query.getColumnIndex("_id"));
            if (query != null) {
                query.close();
            }
            return i;
        } finally {
            if (query != null) {
                query.close();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (!cd.a(this.e, d)) {
            ho.b("IPPSAppointJs", "request permissions");
            cd.a((Activity) this.e, d, 11);
        } else {
            if (!a()) {
                a(this.j, this.i);
                return;
            }
            ho.c("IPPSAppointJs", "appoint failed: already appointed");
            a(this.i, 3, R.string._2130851014_res_0x7f0234c6);
            this.h.a(this.f, 3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        new AlertDialog.Builder(this.e).setTitle(R.string._2130851023_res_0x7f0234cf).setMessage(R.string._2130851015_res_0x7f0234c7).setNegativeButton(R.string._2130851070_res_0x7f0234fe, new DialogInterface.OnClickListener() { // from class: com.huawei.openalliance.ad.utils.av.8
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                ho.c("IPPSAppointJs", "appoint failed: not allowed");
                av avVar = av.this;
                avVar.a(avVar.i, 4, R.string._2130851016_res_0x7f0234c8);
                av.this.h.a(av.this.f, 4);
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        }).setPositiveButton(R.string._2130851013_res_0x7f0234c5, new DialogInterface.OnClickListener() { // from class: com.huawei.openalliance.ad.utils.av.7
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                av.this.g();
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        }).setCancelable(false).create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (cd.a(this.e, d)) {
            b(this.k);
        } else {
            ho.b("IPPSAppointJs", "cancel, request permissions");
            cd.a((Activity) this.e, d, 12);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        new AlertDialog.Builder(this.e).setTitle(R.string._2130851023_res_0x7f0234cf).setMessage(R.string._2130851018_res_0x7f0234ca).setNegativeButton(R.string._2130851070_res_0x7f0234fe, new DialogInterface.OnClickListener() { // from class: com.huawei.openalliance.ad.utils.av.6
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                ho.c("IPPSAppointJs", "cancel failed: not allowed");
                av avVar = av.this;
                avVar.a(avVar.l, 4, R.string._2130851019_res_0x7f0234cb);
                av.this.h.c(av.this.f, 4);
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        }).setPositiveButton(R.string._2130851021_res_0x7f0234cd, new DialogInterface.OnClickListener() { // from class: com.huawei.openalliance.ad.utils.av.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                av.this.e();
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        }).setCancelable(false).create().show();
    }

    private void c() {
        m.f(new Runnable() { // from class: com.huawei.openalliance.ad.utils.av.1
            @Override // java.lang.Runnable
            public void run() {
                av avVar = av.this;
                avVar.m = cd.b(avVar.e, "android.permission.READ_CALENDAR");
                if (av.this.m) {
                    String[] unused = av.d = av.f7598a;
                }
            }
        });
    }

    private boolean b(AgendaBean agendaBean) {
        return cz.b(agendaBean.a()) || cz.b(agendaBean.h());
    }

    private void b(String str) {
        try {
            Uri parse = Uri.parse("content://com.android.calendar/events");
            if (!ao.b(this.e, parse)) {
                ho.c("IPPSAppointJs", "provider uri invalid.");
                a(this.l, 9, R.string._2130851019_res_0x7f0234cb);
                this.h.c(this.f, 9);
            } else if (this.e.getContentResolver().delete(parse, "title=?", new String[]{str}) == -1) {
                ho.c("IPPSAppointJs", "cancel failed: delete error");
                a(this.l, 7, R.string._2130851019_res_0x7f0234cb);
                this.h.c(this.f, 7);
            } else {
                ho.b("IPPSAppointJs", "cancel success");
                a(this.l, 0, R.string._2130851020_res_0x7f0234cc);
                this.h.b(this.f, 0);
            }
        } catch (Throwable th) {
            ho.c("IPPSAppointJs", "cancel failed: delete error= " + th.getClass().getSimpleName());
            a(this.l, 7, R.string._2130851019_res_0x7f0234cb);
            this.h.c(this.f, 7);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(String str) {
        if (cz.b(str)) {
            return false;
        }
        return str.matches("^[0-9a-zA-Z_]+$");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(ContentRecord contentRecord) {
        if (contentRecord == null) {
            return false;
        }
        return "2".equals(contentRecord.ad()) || "1".equals(contentRecord.ad());
    }

    private boolean a(AgendaBean agendaBean) {
        return agendaBean.c() <= 0 || agendaBean.d() <= 0 || agendaBean.c() > agendaBean.d() || cz.b(agendaBean.f());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final String str, final int i, final int i2) {
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.utils.av.2
            @Override // java.lang.Runnable
            public void run() {
                if (av.this.a(str)) {
                    WebView webView = av.this.g.getWebView();
                    String str2 = Constants.JAVA_SCRIPT + str + Constants.LEFT_BRACKET_ONLY + i + Constants.RIGHT_BRACKET_ONLY;
                    webView.loadUrl(str2);
                    WebViewInstrumentation.loadUrl(webView, str2);
                }
                av avVar = av.this;
                if (avVar.a(avVar.f)) {
                    return;
                }
                Toast.makeText(av.this.e.getApplicationContext(), i2, 0).show();
            }
        });
    }

    private long a(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setTimeZone(TimeZone.getTimeZone(DataResult.UTC));
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTime().getTime();
    }

    public av(Context context, ContentRecord contentRecord, PPSWebView pPSWebView) {
        ho.b("IPPSAppointJs", "IPPSAppointJs init");
        this.e = context;
        this.f = contentRecord;
        this.g = pPSWebView;
        this.h = new com.huawei.openalliance.ad.analysis.h(context);
        c();
    }

    static {
        String[] strArr = {"android.permission.WRITE_CALENDAR"};
        c = strArr;
        d = strArr;
    }
}
