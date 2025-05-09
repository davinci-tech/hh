package defpackage;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Message;
import android.view.ViewGroup;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.functionsetcard.FunctionSetViewAdapter;
import com.huawei.health.functionsetcard.dailymoment.DailyMomentCardAdapter;
import com.huawei.health.functionsetcard.dailymoment.operation.IGetDataCallback;
import com.huawei.health.functionsetcard.dailymoment.operation.TimeTickReceiver;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.SingleDailyMomentContent;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class dnx {
    private static dnx d;

    /* renamed from: a, reason: collision with root package name */
    private DailyMomentCardAdapter f11743a;
    private List<SingleDailyMomentContent> b;
    private ResourceBriefInfo e;
    private TimeTickReceiver f;
    private dnv h;
    private volatile boolean j;
    private dog l;
    private FunctionSetViewAdapter m;
    private int o;
    private a g = new a(this);
    private boolean i = false;
    private Context c = BaseApplication.e();

    public static dnx d() {
        dnx dnxVar;
        synchronized (dnx.class) {
            if (d == null) {
                d = new dnx();
            }
            dnxVar = d;
        }
        return dnxVar;
    }

    private dnx() {
        this.j = true;
        boolean m = m();
        LogUtil.a("DailyMomentManager", "showStatus: ", Boolean.valueOf(m));
        if (m) {
            c(true);
            n();
            if (this.i || !i()) {
                return;
            }
            o();
            return;
        }
        this.j = false;
        LogUtil.a("DailyMomentManager", "cached show status is false");
    }

    public DailyMomentCardAdapter.DailyMomentViewHolder Xc_(ViewGroup viewGroup) {
        DailyMomentCardAdapter dailyMomentCardAdapter = this.f11743a;
        if (dailyMomentCardAdapter != null) {
            return dailyMomentCardAdapter.WX_(viewGroup);
        }
        return null;
    }

    public DailyMomentCardAdapter a() {
        return this.f11743a;
    }

    public final boolean i() {
        dog dogVar;
        List<SingleDailyMomentContent> list;
        return (!this.j || (dogVar = this.l) == null || !dogVar.b() || (list = this.b) == null || list.isEmpty()) ? false : true;
    }

    public void f() {
        this.j = false;
        FunctionSetViewAdapter functionSetViewAdapter = this.m;
        if (functionSetViewAdapter != null) {
            functionSetViewAdapter.notifyDataSetChanged();
        }
        dog dogVar = this.l;
        if (dogVar != null) {
            dogVar.c();
        }
    }

    public void g() {
        LogUtil.a("DailyMomentManager", "refreshByAnotherDay");
        DailyMomentCardAdapter dailyMomentCardAdapter = this.f11743a;
        if (dailyMomentCardAdapter != null && dailyMomentCardAdapter.e()) {
            LogUtil.a("DailyMomentManager", "music is playing, delay refreshByAnotherDay");
            return;
        }
        if (this.c == null) {
            return;
        }
        if (m()) {
            c(true);
            n();
            if (this.i || !i()) {
                return;
            }
            o();
            return;
        }
        this.j = false;
        LogUtil.a("DailyMomentManager", "cached show status is false");
    }

    public void j() {
        LogUtil.a("DailyMomentManager", "refreshByAnotherHour");
        DailyMomentCardAdapter dailyMomentCardAdapter = this.f11743a;
        if (dailyMomentCardAdapter != null && dailyMomentCardAdapter.e()) {
            LogUtil.a("DailyMomentManager", "music is playing, delay refreshByAnotherHour");
        } else {
            if (this.c == null) {
                return;
            }
            c(false);
        }
    }

    public List<SingleDailyMomentContent> e() {
        return new ArrayList(this.b);
    }

    public void c(FunctionSetViewAdapter functionSetViewAdapter) {
        this.m = functionSetViewAdapter;
    }

    public ResourceBriefInfo h() {
        return this.e;
    }

    public void c() {
        l();
        this.f = null;
        DailyMomentCardAdapter dailyMomentCardAdapter = this.f11743a;
        if (dailyMomentCardAdapter != null) {
            dailyMomentCardAdapter.b();
            this.f11743a = null;
        }
        List<SingleDailyMomentContent> list = this.b;
        if (list != null) {
            list.clear();
            this.b = null;
        }
        dog.d();
        this.l = null;
        dnv.c();
        this.h = null;
    }

    public static void b() {
        LogUtil.a("DailyMomentManager", "release DailyMomentManager instance");
        synchronized (dnx.class) {
            dnx dnxVar = d;
            if (dnxVar != null) {
                dnxVar.c();
                d = null;
            }
        }
    }

    private boolean m() {
        dog e = dog.e();
        this.l = e;
        e.a();
        return this.l.b();
    }

    private void c(boolean z) {
        LogUtil.a("DailyMomentManager", "initDailyMomentData");
        this.h = dnv.a();
        this.h.e(z, new IGetDataCallback() { // from class: dnx.2
            @Override // com.huawei.health.functionsetcard.dailymoment.operation.IGetDataCallback
            public void onComplete(List<SingleDailyMomentContent> list, ResourceBriefInfo resourceBriefInfo) {
                if (list != null && !list.isEmpty()) {
                    if (dnx.this.b != null) {
                        dnx.this.b.clear();
                    }
                    dnx.this.o = list.size();
                    dnx.this.b = new ArrayList(dnx.this.o);
                    dnx.this.b.addAll(list);
                    dnx.this.e = resourceBriefInfo;
                    if (!dnx.this.i && dnx.this.i()) {
                        dnx.this.o();
                    }
                    Iterator it = dnx.this.b.iterator();
                    while (it.hasNext()) {
                        LogUtil.a("DailyMomentManager", ((SingleDailyMomentContent) it.next()).toString());
                    }
                    dnx.this.e(201);
                    return;
                }
                LogUtil.b("DailyMomentManager", "request daily moment data failed");
                if (dnx.this.b != null) {
                    dnx.this.b.clear();
                    dnx.this.e(202);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        this.g.sendMessage(this.g.obtainMessage(i));
    }

    private void n() {
        if (this.f11743a == null) {
            this.f11743a = new DailyMomentCardAdapter(this.c);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        LogUtil.a("DailyMomentManager", "registerRefreshListener");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.TIME_TICK");
        TimeTickReceiver timeTickReceiver = new TimeTickReceiver(this);
        this.f = timeTickReceiver;
        Context context = this.c;
        if (context != null) {
            context.registerReceiver(timeTickReceiver, intentFilter);
            this.i = true;
        }
    }

    private void l() {
        TimeTickReceiver timeTickReceiver;
        Context context = this.c;
        if (context != null && (timeTickReceiver = this.f) != null && this.i) {
            context.unregisterReceiver(timeTickReceiver);
        }
        this.i = false;
    }

    static class a extends BaseHandler<dnx> {
        a(dnx dnxVar) {
            super(dnxVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: Xd_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(dnx dnxVar, Message message) {
            if (dnxVar != null && message != null) {
                switch (message.what) {
                    case 200:
                        if (dnxVar.f11743a != null) {
                            dnxVar.f11743a.c();
                            break;
                        }
                        break;
                    case 201:
                        if (dnxVar.m != null) {
                            dnxVar.m.notifyItemInserted(1);
                            break;
                        }
                        break;
                    case 202:
                        if (dnxVar.m != null) {
                            dnxVar.m.notifyItemRemoved(1);
                            break;
                        }
                        break;
                }
                return;
            }
            LogUtil.a("DailyMomentManager", "obj or message is null");
        }
    }
}
