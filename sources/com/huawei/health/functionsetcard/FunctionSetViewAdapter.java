package com.huawei.health.functionsetcard;

import android.content.Context;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.functionsetcard.dailymoment.DailyMomentCardAdapter;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.dnx;
import defpackage.doa;
import defpackage.koq;
import defpackage.nsn;
import health.compact.a.Utils;
import java.util.List;

/* loaded from: classes3.dex */
public class FunctionSetViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context d;
    private dnx e;
    private List<FunctionSetSubCardData> h;
    private Runnable i;
    private volatile int f = -1;
    private volatile boolean g = false;

    /* renamed from: a, reason: collision with root package name */
    private volatile boolean f2355a = false;
    private boolean b = true;
    private int j = 2;
    private Runnable c = new Runnable() { // from class: com.huawei.health.functionsetcard.FunctionSetViewAdapter.3
        @Override // java.lang.Runnable
        public void run() {
            FunctionSetViewAdapter.this.d();
        }
    };

    public FunctionSetViewAdapter(List<FunctionSetSubCardData> list, Context context) {
        this.h = list;
        this.d = context;
        if (this.e == null) {
            this.e = dnx.d();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (i == -1) {
            return this.e.Xc_(viewGroup);
        }
        FunctionSetSubCardData functionSetSubCardData = this.h.get(i);
        if (functionSetSubCardData == null) {
            LogUtil.h("R_FunctionSetViewAdapter", "onCreateViewHolder functionSetBeanReader is null");
            return null;
        }
        RecyclerView.ViewHolder cardViewHolder = functionSetSubCardData.getCardViewHolder(viewGroup, LayoutInflater.from(this.d), i);
        LogUtil.a("R_FunctionSetViewAdapter", "onCreateViewHolder finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime) + ", id: ", functionSetSubCardData.getCardConfig().getCardId());
        return cardViewHolder;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (!c(i)) {
            LogUtil.h("R_FunctionSetViewAdapter", "onBindViewHolder position is wrong :", Integer.valueOf(i));
            return;
        }
        if ((viewHolder instanceof DailyMomentCardAdapter.DailyMomentViewHolder) && i == 1 && this.e.a() != null) {
            this.e.a().b((DailyMomentCardAdapter.DailyMomentViewHolder) viewHolder, this.e);
            doa.e(this.d, AnalyticsValue.DAILY_MOMENT_11100301.value(), null, null);
            return;
        }
        FunctionSetSubCardData functionSetSubCardData = this.h.get(d(i));
        if (functionSetSubCardData == null) {
            LogUtil.h("R_FunctionSetViewAdapter", "onBindViewHolder functionSetSubCardData is null");
            return;
        }
        functionSetSubCardData.onBindViewHolder(viewHolder, functionSetSubCardData);
        LogUtil.a("R_FunctionSetViewAdapter", "onBindViewHolder finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime) + ", id: ", functionSetSubCardData.getCardConfig().getCardId());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        List<FunctionSetSubCardData> list = this.h;
        if (list == null || list.size() == 0 || i < 0) {
            LogUtil.a("R_FunctionSetViewAdapter", "position is invalid");
            return -2L;
        }
        if (this.e.i()) {
            if (i > this.h.size()) {
                LogUtil.a("R_FunctionSetViewAdapter", "position is invalid");
                return -2L;
            }
            if (i == 1) {
                return -1L;
            }
            return Math.max(i - 1, 0);
        }
        if (i < this.h.size()) {
            return i;
        }
        LogUtil.a("R_FunctionSetViewAdapter", "position is invalid");
        return -2L;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        List<FunctionSetSubCardData> list = this.h;
        if (list == null || list.size() == 0 || i < 0) {
            LogUtil.a("R_FunctionSetViewAdapter", "position is invalid");
            return -2;
        }
        if (this.e.i()) {
            if (i > this.h.size()) {
                LogUtil.a("R_FunctionSetViewAdapter", "position is invalid");
                return -2;
            }
            if (i == 1) {
                return -1;
            }
            return Math.max(i - 1, 0);
        }
        if (i < this.h.size()) {
            return i;
        }
        LogUtil.a("R_FunctionSetViewAdapter", "position is invalid");
        return -2;
    }

    public void e(int i) {
        if (a() && i == this.j && !this.g) {
            LogUtil.a("R_FunctionSetViewAdapter", "notifyCardScrolled");
            this.g = true;
            e();
        }
    }

    public void e(Runnable runnable) {
        if (!a()) {
            runnable.run();
        } else {
            if (this.f == 1) {
                runnable.run();
                return;
            }
            LogUtil.a("R_FunctionSetViewAdapter", "need to wait for tryNotifyItemInsertDelayed completed");
            this.f2355a = true;
            this.i = runnable;
        }
    }

    private void e() {
        Runnable runnable;
        LogUtil.a("R_FunctionSetViewAdapter", "tryNotifyItemInsertDelayed, mPreAttach: " + this.f);
        if (this.f == -1) {
            HandlerExecutor.yF_().postDelayed(this.c, 800L);
            return;
        }
        try {
            if (this.f == 1) {
                return;
            }
            try {
                HandlerExecutor.yF_().removeCallbacks(this.c);
                runnable = new Runnable() { // from class: com.huawei.health.functionsetcard.FunctionSetViewAdapter.4
                    @Override // java.lang.Runnable
                    public void run() {
                        FunctionSetViewAdapter.this.d();
                    }
                };
            } catch (RuntimeException unused) {
                LogUtil.b("R_FunctionSetViewAdapter", "removeCallbacks RuntimeException");
                runnable = new Runnable() { // from class: com.huawei.health.functionsetcard.FunctionSetViewAdapter.4
                    @Override // java.lang.Runnable
                    public void run() {
                        FunctionSetViewAdapter.this.d();
                    }
                };
            }
            HandlerExecutor.a(runnable);
        } catch (Throwable th) {
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.health.functionsetcard.FunctionSetViewAdapter.4
                @Override // java.lang.Runnable
                public void run() {
                    FunctionSetViewAdapter.this.d();
                }
            });
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        LogUtil.a("R_FunctionSetViewAdapter", "notifyItemInsert, mIsNeedWaitInsertCompleted: " + this.f2355a);
        this.f = 1;
        if (this.f2355a) {
            Runnable runnable = this.i;
            if (runnable != null) {
                runnable.run();
                return;
            }
            return;
        }
        LogUtil.a("R_FunctionSetViewAdapter", "no waiting notify task, notifyItemRangeInserted");
        List<FunctionSetSubCardData> list = this.h;
        if (list == null) {
            LogUtil.b("R_FunctionSetViewAdapter", "mLists is null");
        } else if (list.size() - 4 <= 0) {
            LogUtil.b("R_FunctionSetViewAdapter", "no item need insert");
        } else {
            notifyItemRangeInserted(4, this.h.size() - 4);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<FunctionSetSubCardData> list = this.h;
        if (list == null || list.size() == 0) {
            return 0;
        }
        if (!a()) {
            return b();
        }
        if (this.f != 1) {
            if (this.f != -1) {
                return 4;
            }
            e();
            this.f = 0;
            return 4;
        }
        return b();
    }

    private int b() {
        if (this.e.i()) {
            return this.h.size() + 1;
        }
        return this.h.size();
    }

    private boolean a() {
        return (this.h.size() <= 4 || Utils.o() || nsn.ag(this.d) || nsn.ae(this.d) || nsn.aa(this.d) || !this.b) ? false : true;
    }

    private boolean c(int i) {
        return e(this.h, i);
    }

    public boolean e(List<FunctionSetSubCardData> list, int i) {
        if (list == null || list.size() == 0 || i < 0) {
            return false;
        }
        return this.e.i() ? i <= list.size() : i < list.size();
    }

    public int d(int i) {
        dnx dnxVar = this.e;
        if (dnxVar == null || !dnxVar.i()) {
            return i;
        }
        if (i == 0) {
            return 0;
        }
        return i - 1;
    }

    public int b(int i) {
        dnx dnxVar = this.e;
        if (dnxVar == null || !dnxVar.i()) {
            return i;
        }
        if (i == 0) {
            return 0;
        }
        return i + 1;
    }

    public void c() {
        dnx.b();
        this.e = null;
        List<FunctionSetSubCardData> list = this.h;
        if (list != null) {
            list.clear();
            this.h = null;
        }
    }

    public int a(FunctionSetSubCardData functionSetSubCardData) {
        if (koq.b(this.h)) {
            LogUtil.a("R_FunctionSetViewAdapter", "mLists is null");
            return -1;
        }
        for (int i = 0; i < this.h.size(); i++) {
            FunctionSetSubCardData functionSetSubCardData2 = this.h.get(i);
            if (functionSetSubCardData2 != null && functionSetSubCardData2 == functionSetSubCardData) {
                return b(i);
            }
        }
        return -1;
    }

    public void a(boolean z) {
        this.b = z;
    }
}
