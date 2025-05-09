package defpackage;

import android.content.Context;
import com.huawei.health.market.comment.MarketCommentDialog;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;

/* loaded from: classes3.dex */
public class ehr {
    private static volatile ehr d;
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private final Context f12017a;

    private ehr(Context context) {
        this.f12017a = context.getApplicationContext();
    }

    public static ehr b(Context context) {
        if (d == null) {
            synchronized (e) {
                if (d == null) {
                    d = new ehr(context);
                }
            }
        }
        return d;
    }

    public void a(Context context) {
        LogUtil.a("MarketMgr", "enter showMarektCommentDialog");
        if (context == null) {
            LogUtil.h("MarketMgr", "context is null");
            return;
        }
        if (!c()) {
            LogUtil.h("MarketMgr", "should not show");
            return;
        }
        MarketCommentDialog marketCommentDialog = new MarketCommentDialog(context);
        if (Utils.o()) {
            LogUtil.a("MarketMgr", "isOversea");
            if (jdm.a(this.f12017a, "com.huawei.appmarket")) {
                marketCommentDialog.e();
                ehx.e(this.f12017a).d();
                return;
            }
            return;
        }
        LogUtil.a("MarketMgr", "isNotOversea");
        if (jdm.b(this.f12017a, "com.huawei.appmarket")) {
            marketCommentDialog.e();
            ehx.e(this.f12017a).c();
        }
    }

    public void e(Context context, long j, int i) {
        LogUtil.a("MarketMgr", "fromTrack_trackTime ", Long.valueOf(j), "trackDis ", Integer.valueOf(i));
        if (i > 3000 || j > 1800000) {
            a(context);
        }
    }

    private boolean c() {
        long j;
        if (!CommonUtil.aa(this.f12017a.getApplicationContext()) || Utils.f()) {
            LogUtil.a("MarketMgr", "Network is not Connected");
            return false;
        }
        String b = Utils.o() ? "1" : SharedPreferenceManager.b(this.f12017a, Integer.toString(10000), "market_comment_status");
        try {
            j = Long.parseLong(SharedPreferenceManager.b(this.f12017a, Integer.toString(10000), "market_comment_time"));
        } catch (NumberFormatException unused) {
            LogUtil.b("MarketMgr", "parseLong Exception shouldShow Exception");
            j = 0;
        }
        long currentTimeMillis = System.currentTimeMillis() - j;
        LogUtil.a("MarketMgr", "MarketCommentCloud_shouldShow() commentStatus ", b, " spaceTime", Long.valueOf(currentTimeMillis), "lastShowTime ", Long.valueOf(j));
        return currentTimeMillis > 31536000000L && "1".equals(b);
    }
}
