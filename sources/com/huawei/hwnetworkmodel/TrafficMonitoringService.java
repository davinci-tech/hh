package com.huawei.hwnetworkmodel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.SpKeys;
import defpackage.ktz;
import defpackage.kuc;
import health.compact.a.CompileParameterUtil;
import java.util.Calendar;
import java.util.Date;

/* loaded from: classes5.dex */
public class TrafficMonitoringService {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f6388a = false;
    private static boolean b = false;
    private static boolean c = false;
    private static boolean d = false;
    private static HttpDatabaseHelper e;
    private static SharedPreferences i;
    private static kuc j;
    private d f;
    private Context g;
    private ConnectivityManager h;
    private e k;
    private c l;
    private Date o;

    public TrafficMonitoringService(Context context) {
        this.g = context;
    }

    public static boolean h() {
        return CompileParameterUtil.a("IS_TRAFFIC_LIMITED", false);
    }

    public static boolean e() {
        kuc kucVar;
        if (!h() || !c) {
            return false;
        }
        synchronized (TrafficMonitoringService.class) {
            kucVar = new kuc(j.a(), j.c(), j.d(), j.e());
        }
        if (d) {
            LogUtil.h("TrafficMonService", "isOverFlow network traffic overflow 3 days.");
            if (!b) {
                i();
            }
            return true;
        }
        if (kucVar.e() < 314572800) {
            return false;
        }
        LogUtil.h("TrafficMonService", "isOverFlow network traffic overflow.");
        if (!f6388a) {
            synchronized (TrafficMonitoringService.class) {
                i(true);
                Bundle bundle = new Bundle();
                bundle.putString(SpKeys.TODAY_DATE, kucVar.a());
                bundle.putLong("today_total", kucVar.e());
                bundle.putLong("today_requested", kucVar.c());
                bundle.putLong("today_received", kucVar.d());
                LogUtil.bRh_(907127001, "TrafficMonService", bundle, false, "network traffic overflow. ", bundle);
            }
        }
        return true;
    }

    private static void i() {
        h(true);
        Bundle bundle = new Bundle();
        Date date = new Date();
        kuc e2 = e(date, -1);
        if (e2 != null) {
            bundle.putString("1_date", e2.a());
            bundle.putLong("1_total", e2.e());
            bundle.putLong("1_requested", e2.c());
            bundle.putLong("1_received", e2.d());
        }
        kuc e3 = e(date, -2);
        if (e3 != null) {
            bundle.putString("2_date", e3.a());
            bundle.putLong("2_total", e3.e());
            bundle.putLong("2_requested", e3.c());
            bundle.putLong("2_received", e3.d());
        }
        kuc e4 = e(date, -3);
        if (e4 != null) {
            bundle.putString("3_date", e4.a());
            bundle.putLong("3_total", e4.e());
            bundle.putLong("3_requested", e4.c());
            bundle.putLong("3_received", e4.d());
        }
        LogUtil.bRh_(907127002, "TrafficMonService", bundle, false, "reportOverFlowError network traffic overflow 3 days.", bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(kuc kucVar) {
        e.e(kucVar);
    }

    private static kuc e(Date date, int i2) {
        return e.d(d(date, i2));
    }

    private static String d(Date date, int i2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(5, i2);
        return ktz.c(calendar.getTime());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void i(boolean z) {
        f6388a = z;
        SharedPreferences.Editor edit = i.edit();
        edit.putBoolean("setReportTodayError isReportTodayError:", z);
        edit.commit();
    }

    private static void h(boolean z) {
        b = z;
        SharedPreferences.Editor edit = i.edit();
        edit.putBoolean("setReportThreeDaysError isReportThreeDaysError:", z);
        edit.commit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(boolean z) {
        d = z;
        SharedPreferences.Editor edit = i.edit();
        edit.putBoolean("setOverflowThreeDays isOverflowThreeDays:", z);
        edit.commit();
    }

    private static void c(boolean z) {
        c = z;
    }

    private static void c(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("TrafficMonitorPreferences", 0);
        i = sharedPreferences;
        d = sharedPreferences.getBoolean("isOverflow3days", false);
        f6388a = i.getBoolean("isReportTodayErr", false);
        b = i.getBoolean("isReport3DaysErr", false);
    }

    private static void c(kuc kucVar) {
        synchronized (TrafficMonitoringService.class) {
            j = kucVar;
        }
    }

    private static void d(HttpDatabaseHelper httpDatabaseHelper) {
        e = httpDatabaseHelper;
    }

    public static void b(long j2) {
        if (c) {
            synchronized (TrafficMonitoringService.class) {
                if (j != null) {
                    LogUtil.c("TrafficMonService", "requesting bytes:", Long.valueOf(j2), " total:", Long.valueOf(j.e()));
                    j.a(j2);
                }
            }
        }
    }

    public static void e(long j2) {
        if (c) {
            synchronized (TrafficMonitoringService.class) {
                if (j != null) {
                    LogUtil.c("TrafficMonService", "received bytes = ", Long.valueOf(j2), " total() = ", Long.valueOf(j.e()));
                    j.d(j2);
                }
            }
        }
    }

    public void f() {
        LogUtil.c("TrafficMonService", "onCreate");
        if (h()) {
            this.o = new Date();
            c(this.g);
            d(new HttpDatabaseHelper(this.g));
            if (!d) {
                b(e(this.o));
            }
            synchronized (TrafficMonitoringService.class) {
                c(e.d(ktz.c(this.o)));
                if (j == null) {
                    LogUtil.h("TrafficMonService", "onCreate init today traffic with 0");
                    c(new kuc(ktz.c(this.o)));
                    LogUtil.c("TrafficMonService", "onCreate ->1--> total is ", Long.valueOf(j.e()), "->1--> sTodayState is ", j.toString());
                    e.d(j);
                    LogUtil.c("TrafficMonService", "onCreate ->2--> total is ", Long.valueOf(j.e()), "->2--> sTodayState is ", j.toString());
                }
                LogUtil.c("TrafficMonService", "onCreate ->3--> total is ", Long.valueOf(j.e()), "->3-->sTodayState is ", j.toString());
            }
            this.l = new c();
            this.k = new e();
            c(j());
            if (c) {
                LogUtil.c("TrafficMonService", "onCreate start refresh thread.");
                if (!d) {
                    this.l.start();
                }
            }
            IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
            d dVar = new d();
            this.f = dVar;
            this.g.registerReceiver(dVar, intentFilter);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean j() {
        NetworkInfo networkInfo;
        if (this.g.getSystemService("connectivity") instanceof ConnectivityManager) {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.g.getSystemService("connectivity");
            this.h = connectivityManager;
            networkInfo = connectivityManager.getNetworkInfo(1);
        } else {
            networkInfo = null;
        }
        if (networkInfo != null) {
            boolean isConnected = networkInfo.isConnected();
            NetworkInfo networkInfo2 = this.h.getNetworkInfo(0);
            if (networkInfo2 != null) {
                boolean isConnected2 = networkInfo2.isConnected();
                if (isConnected) {
                    LogUtil.c("TrafficMonService", "isMobileConnection isWifiConn = true");
                    return false;
                }
                if (isConnected2) {
                    LogUtil.c("TrafficMonService", "isMobileConnection isMobileConn = true");
                    return true;
                }
                LogUtil.c("TrafficMonService", "isMobileConnection isWifiConn = false & isMobileConn = false");
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(Date date) {
        return ktz.c(new Date()).toString().equals(ktz.c(date).toString());
    }

    private boolean a(Date date, int i2) {
        kuc e2 = e(date, i2);
        return e2 != null && e2.e() >= 314572800;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e(Date date) {
        return a(date, -1) && a(date, -2) && a(date, -3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        synchronized (TrafficMonitoringService.class) {
            LogUtil.c("TrafficMonService", "updateTodayTraffic writing today traffic to DB: ", j);
            a(j);
        }
    }

    class d extends BroadcastReceiver {
        d() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (context == null || intent == null) {
                LogUtil.h("TrafficMonService", "ConnectionChangeReceiver onReceive null");
                return;
            }
            LogUtil.c("TrafficMonService", "ConnectionChangeReceiver onReceive, intent.action: ", intent.getAction(), " intent.type:", intent.getType());
            if (TrafficMonitoringService.this.j()) {
                LogUtil.c("TrafficMonService", "ConnectionChangeReceiver onReceive, isMobile is true");
                boolean unused = TrafficMonitoringService.c = true;
                if (TrafficMonitoringService.this.l == null || TrafficMonitoringService.this.l.isAlive() || TrafficMonitoringService.d) {
                    return;
                }
                LogUtil.c("TrafficMonService", "ConnectionChangeReceiver start refresh thread@ConnectionChangeReceiver");
                TrafficMonitoringService.this.l = TrafficMonitoringService.this.new c();
                TrafficMonitoringService.this.l.start();
                return;
            }
            LogUtil.c("TrafficMonService", "ConnectionChangeReceiver onReceive, isMobile is false");
            if (TrafficMonitoringService.c) {
                boolean unused2 = TrafficMonitoringService.c = false;
                LogUtil.c("TrafficMonService", "ConnectionChangeReceiver onReceive, from mobile switch to non-mobile");
                TrafficMonitoringService trafficMonitoringService = TrafficMonitoringService.this;
                if (trafficMonitoringService.a(trafficMonitoringService.o) && TrafficMonitoringService.this.k != null && !TrafficMonitoringService.this.k.isAlive()) {
                    LogUtil.c("TrafficMonService", "ConnectionChangeReceiver to start");
                    TrafficMonitoringService.this.k = TrafficMonitoringService.this.new e();
                    TrafficMonitoringService.this.k.start();
                }
            }
            boolean unused3 = TrafficMonitoringService.c = false;
            LogUtil.c("TrafficMonService", "ConnectionChangeReceiver onReceive, ready to close refresh thread");
        }
    }

    class c extends Thread {
        c() {
            super("TrafficMonitoringService-RefreshThread");
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            do {
                TrafficMonitoringService trafficMonitoringService = TrafficMonitoringService.this;
                if (!trafficMonitoringService.a(trafficMonitoringService.o)) {
                    synchronized (TrafficMonitoringService.class) {
                        LogUtil.c("TrafficMonService", "RefreshThread cross day");
                        TrafficMonitoringService.i(false);
                        LogUtil.c("TrafficMonService", " RefreshThread run Writing yesterday traffic to DB: ", TrafficMonitoringService.j);
                        TrafficMonitoringService.a(TrafficMonitoringService.j);
                        Date date = new Date();
                        TrafficMonitoringService.b(TrafficMonitoringService.this.e(date));
                        kuc unused = TrafficMonitoringService.j = TrafficMonitoringService.e.d(ktz.c(date));
                        if (TrafficMonitoringService.j == null) {
                            kuc unused2 = TrafficMonitoringService.j = new kuc(ktz.c(date));
                            TrafficMonitoringService.e.d(TrafficMonitoringService.j);
                        }
                        TrafficMonitoringService.this.o = date;
                    }
                }
                LogUtil.c("TrafficMonService", "RefreshThread run update today traffic");
                TrafficMonitoringService.this.g();
                try {
                    Thread.sleep(1200000L);
                } catch (InterruptedException unused3) {
                    LogUtil.b("TrafficMonService", "RefreshThread run InterruptedException");
                }
                if (!TrafficMonitoringService.c) {
                    return;
                }
            } while (!TrafficMonitoringService.d);
        }
    }

    class e extends Thread {
        e() {
            super("TrafficMonitoringService-RecordThread");
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            LogUtil.c("TrafficMonService", "RecordThread run updateTodayTraffic");
            TrafficMonitoringService.this.g();
        }
    }
}
