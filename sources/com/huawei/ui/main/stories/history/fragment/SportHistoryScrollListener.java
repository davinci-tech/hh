package com.huawei.ui.main.stories.history.fragment;

import android.widget.AbsListView;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.koq;
import defpackage.rdo;
import defpackage.rdr;
import java.util.List;

/* loaded from: classes7.dex */
public class SportHistoryScrollListener implements AbsListView.OnScrollListener {

    /* renamed from: a, reason: collision with root package name */
    private final List<rdo> f10313a;
    private long c;
    private final SportScrollCallback d;

    public interface SportScrollCallback {
        void onResponse(long j, boolean z);

        void onScroll(int i);
    }

    public SportHistoryScrollListener(SportScrollCallback sportScrollCallback, List<rdo> list) {
        this.d = sportScrollCallback;
        this.f10313a = list;
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (i == 1) {
            long d = d();
            LogUtil.a("Track_SportHistoryScrollListener", "SCROLL_STATE_TOUCH loadTime", Long.valueOf(d));
            if (d != 0 && this.c != d) {
                this.c = d;
                this.d.onResponse(d, true);
            } else {
                this.d.onResponse(0L, false);
            }
        }
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        this.d.onScroll(i);
    }

    private long d() {
        if (koq.b(this.f10313a)) {
            LogUtil.h("Track_SportHistoryScrollListener", "isCheckLoadMore mGroupData is empty.");
            return 0L;
        }
        for (int i = 0; i < this.f10313a.size(); i++) {
            rdo rdoVar = this.f10313a.get(i);
            if (rdoVar != null && rdoVar.k()) {
                rdr a2 = rdoVar.a(rdoVar.d() - 1);
                if (a2 == null) {
                    return 0L;
                }
                return a2.v();
            }
        }
        return 0L;
    }

    public void e(long j) {
        this.c = j;
    }
}
