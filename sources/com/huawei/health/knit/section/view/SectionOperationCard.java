package com.huawei.health.knit.section.view;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.koq;
import defpackage.nrr;
import defpackage.nru;
import defpackage.nsy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class SectionOperationCard extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private SectionOperationCardAdapter f2708a;
    private c b;
    private HealthColumnSystem c;
    private Context d;
    private int e;
    private GridLayoutManager f;
    private OnClickSectionListener g;
    private HealthRecycleView h;
    private List<MessageObject> i;
    private LinearLayout j;
    private List<MessageObject> o;

    static /* synthetic */ int j(SectionOperationCard sectionOperationCard) {
        int i = sectionOperationCard.e + 1;
        sectionOperationCard.e = i;
        return i;
    }

    public SectionOperationCard(Context context) {
        super(context);
        this.e = 0;
        this.o = new ArrayList();
        this.i = new ArrayList();
        this.b = new c(this);
    }

    public SectionOperationCard(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = 0;
        this.o = new ArrayList();
        this.i = new ArrayList();
        this.b = new c(this);
    }

    public SectionOperationCard(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = 0;
        this.o = new ArrayList();
        this.i = new ArrayList();
        this.b = new c(this);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        this.d = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.home_item_layout_operation, (ViewGroup) this, false);
        ajo_(inflate, this.d);
        return inflate;
    }

    private void ajo_(View view, Context context) {
        HealthRecycleView healthRecycleView = (HealthRecycleView) view.findViewById(R.id.operation_rv);
        this.h = healthRecycleView;
        healthRecycleView.a(false);
        this.h.d(false);
        this.h.setFocusableInTouchMode(false);
        this.c = new HealthColumnSystem(this.d, 1);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, this.c.f() / 4, 1, false);
        this.f = gridLayoutManager;
        this.h.setLayoutManager(gridLayoutManager);
        this.h.addItemDecoration(new RecyclerView.ItemDecoration() { // from class: com.huawei.health.knit.section.view.SectionOperationCard.1
            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void getItemOffsets(Rect rect, View view2, RecyclerView recyclerView, RecyclerView.State state) {
                super.getItemOffsets(rect, view2, recyclerView, state);
                if (SectionOperationCard.this.f == null || SectionOperationCard.this.f.getSpanCount() <= 1) {
                    return;
                }
                int b = nrr.b(SectionOperationCard.this.d);
                if (recyclerView.getChildAdapterPosition(view2) % SectionOperationCard.this.f.getSpanCount() != 0) {
                    rect.left = b / 2;
                } else {
                    rect.right = b / 2;
                }
            }
        });
        this.j = (LinearLayout) view.findViewById(R.id.operation_card_layout);
        ((HealthSubHeader) view.findViewById(R.id.operation_card_sub_header)).setSubHeaderBackgroundColor(0);
        nsy.cMA_(this.j, 8);
    }

    public void setData(List<MessageObject> list) {
        SectionOperationCardAdapter sectionOperationCardAdapter = new SectionOperationCardAdapter(this.d, list);
        this.f2708a = sectionOperationCardAdapter;
        OnClickSectionListener onClickSectionListener = this.g;
        if (onClickSectionListener != null) {
            sectionOperationCardAdapter.a(onClickSectionListener);
        }
        this.h.setAdapter(this.f2708a);
    }

    public boolean b() {
        LogUtil.a("SectionOperationCard", "notifyDataSetChanged start");
        d();
        if (this.f2708a == null) {
            return false;
        }
        if (this.h.getScrollState() == 0 && !this.h.isComputingLayout()) {
            try {
                this.h.getRecycledViewPool().clear();
                this.f2708a.notifyDataSetChanged();
                LogUtil.a("SectionOperationCard", "notifyDataSetChanged succ");
                return true;
            } catch (Exception unused) {
                LogUtil.b("SectionOperationCard", "notifyDataSetChanged occurred unknown exception");
                return false;
            }
        }
        LogUtil.b("SectionOperationCard", "notifyDataSetChanged ----------- fail");
        return false;
    }

    private void d() {
        HealthColumnSystem healthColumnSystem = this.c;
        if (healthColumnSystem != null) {
            healthColumnSystem.e(this.d);
            GridLayoutManager gridLayoutManager = this.f;
            if (gridLayoutManager != null) {
                gridLayoutManager.setSpanCount(this.c.f() / 4);
            }
        }
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        b();
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        SectionOperationCardAdapter sectionOperationCardAdapter;
        OnClickSectionListener a2 = nru.a(hashMap, "CLICK_EVENT_LISTENER", null);
        this.g = a2;
        if (a2 != null && (sectionOperationCardAdapter = this.f2708a) != null) {
            sectionOperationCardAdapter.a(a2);
        }
        Object obj = hashMap.get("THREAD_LIST");
        List<MessageObject> list = koq.e(obj, MessageObject.class) ? (List) obj : null;
        if (list != null && list != this.o) {
            this.o = list;
        }
        Object obj2 = hashMap.get("DATA_LIST");
        List<MessageObject> list2 = koq.e(obj2, MessageObject.class) ? (List) obj2 : null;
        if (list2 != null && list2 != this.i) {
            this.i = list2;
            setData(list2);
        }
        if (nru.d((Map) hashMap, "NOTIFY_UPDATE", false)) {
            this.b.sendEmptyMessage(0);
        }
    }

    static class c extends BaseHandler<SectionOperationCard> {
        public c(SectionOperationCard sectionOperationCard) {
            super(sectionOperationCard);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: ajp_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(SectionOperationCard sectionOperationCard, Message message) {
            if (message.what != 0) {
                return;
            }
            if (!koq.b(sectionOperationCard.o)) {
                LogUtil.a("SectionOperationCard", "handleMessage UPDATE mThreadList size is ", Integer.valueOf(sectionOperationCard.o.size()));
                nsy.cMA_(sectionOperationCard.j, 0);
                sectionOperationCard.i.clear();
                sectionOperationCard.i.addAll(sectionOperationCard.o);
                LogUtil.a("SectionOperationCard", "handleMessage UPDATE mList size is ", Integer.valueOf(sectionOperationCard.i.size()));
                if (!sectionOperationCard.b()) {
                    if (sectionOperationCard.e < 5) {
                        LogUtil.a("SectionOperationCard", "handleMessage UPDATE again");
                        SectionOperationCard.j(sectionOperationCard);
                        sectionOperationCard.b.sendEmptyMessageDelayed(0, 300L);
                        return;
                    }
                    return;
                }
                sectionOperationCard.e = 0;
                return;
            }
            LogUtil.h("SectionOperationCard", "handleMessage UPDATE mThreadList is empty");
            nsy.cMA_(sectionOperationCard.j, 8);
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionOperationCard";
    }
}
