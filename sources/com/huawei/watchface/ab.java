package com.huawei.watchface;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.huawei.watchface.api.MembershipRepository;
import com.huawei.watchface.api.ResponseListener;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.mvp.model.datatype.querysubinfodetail.QuerySubInfoDetailResp;
import com.huawei.watchface.utils.BackgroundTaskUtils;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;

/* loaded from: classes7.dex */
public class ab {

    /* renamed from: a, reason: collision with root package name */
    private static final String f10883a = "ab";
    private static volatile ab d;
    private final Runnable b = new Runnable() { // from class: com.huawei.watchface.ab$$ExternalSyntheticLambda0
        @Override // java.lang.Runnable
        public final void run() {
            ab.this.k();
        }
    };
    private QuerySubInfoDetailResp c;

    public interface a {
        void a(QuerySubInfoDetailResp querySubInfoDetailResp);
    }

    public static ab a() {
        if (d == null) {
            synchronized (ab.class) {
                if (d == null) {
                    d = new ab();
                }
            }
        }
        return d;
    }

    private ab() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void k() {
        try {
            HwLog.i(f10883a, "update data on runnable");
            a(false);
        } catch (Exception e) {
            HwLog.e(f10883a, "update data exception: " + HwLog.printException(e));
        }
    }

    public void a(boolean z) {
        a(null, z);
    }

    public void a(final a aVar, final boolean z) {
        synchronized (this) {
            HwLog.i(f10883a, "update process");
            BackgroundTaskUtils.submit(new Runnable() { // from class: com.huawei.watchface.ab$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    ab.this.b(aVar, z);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(final a aVar, final boolean z) {
        MembershipRepository.getQueryAll(new ResponseListener<QuerySubInfoDetailResp>() { // from class: com.huawei.watchface.ab.1
            @Override // com.huawei.watchface.api.ResponseListener
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onResponse(QuerySubInfoDetailResp querySubInfoDetailResp) {
                ab.this.i();
                ab.this.a(querySubInfoDetailResp);
                a aVar2 = aVar;
                if (aVar2 != null) {
                    aVar2.a(querySubInfoDetailResp);
                }
                if (z) {
                    ac.a().b(CommonUtils.B());
                }
            }

            @Override // com.huawei.watchface.api.ResponseListener
            public void onError() {
                HwLog.i(ab.f10883a, "update -- getQueryAll -- onError endpoint error");
            }
        });
    }

    public QuerySubInfoDetailResp b() {
        return this.c;
    }

    public String c() {
        QuerySubInfoDetailResp querySubInfoDetailResp = this.c;
        return querySubInfoDetailResp == null ? "" : TextUtils.equals(querySubInfoDetailResp.getMemberStatus(), "1") ? (this.c.getSubInfo() == null || !TextUtils.equals(this.c.getSubInfo().getRenewFlag(), "1")) ? "2" : "1" : "3";
    }

    public void d() {
        synchronized (this) {
            if (this.b != null) {
                HwLog.i(f10883a, "stopPolling: removeTaskFromWorker");
                BackgroundTaskUtils.b(this.b);
            }
        }
    }

    public void e() {
        synchronized (this) {
            HwLog.i(f10883a, "clear:");
            d();
            d = null;
        }
    }

    public void a(QuerySubInfoDetailResp querySubInfoDetailResp) {
        this.c = querySubInfoDetailResp;
        boolean z = (querySubInfoDetailResp == null || querySubInfoDetailResp.getMemberStatus() == null || !querySubInfoDetailResp.getMemberStatus().equals("1")) ? false : true;
        HwLog.d(f10883a, "setMembershipInfo isVipMemberShip:" + z);
        b(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        synchronized (this) {
            d();
            HwLog.i(f10883a, "startPolling");
            BackgroundTaskUtils.a(this.b, 3600000);
        }
    }

    public boolean f() {
        HwLog.i(f10883a, "isVipMembership getFromShared " + j());
        return j();
    }

    public void b(boolean z) {
        SharedPreferences a2 = dp.a(Environment.getApplicationContext(), "MEMBERSHIP_INFO_DATA");
        if (a2 == null) {
            HwLog.i(f10883a, "preferences is null");
            return;
        }
        a2.edit().putBoolean("MEMBERSHIP_INFO", z).apply();
        HwLog.i(f10883a, "saveQueueTask boolean: " + z);
    }

    private boolean j() {
        SharedPreferences a2 = dp.a(Environment.getApplicationContext(), "MEMBERSHIP_INFO_DATA");
        if (a2 == null) {
            HwLog.i(f10883a, "sharedPreferences is null");
            return false;
        }
        return a2.getBoolean("MEMBERSHIP_INFO", false);
    }

    public void g() {
        SharedPreferences a2 = dp.a(Environment.getApplicationContext(), "MEMBERSHIP_INFO");
        if (a2 == null) {
            HwLog.i(f10883a, "sharedPreferences is null");
            return;
        }
        SharedPreferences.Editor edit = a2.edit();
        edit.clear();
        edit.apply();
        HwLog.i(f10883a, "clearSharedPreferencesData completed");
    }
}
