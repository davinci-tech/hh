package com.huawei.health.tradeservice.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.health.tradeservice.cloud.OrderManager;
import com.huawei.hms.framework.network.download.internal.constants.ExceptionCode;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.trade.datatype.OrderBriefInfo;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.gkx;
import defpackage.koq;
import health.compact.a.CommonUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class TradeOrderLayout extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f3469a;
    private RelativeLayout b;
    private int c;
    private Handler d;
    private RelativeLayout e;
    private TradeOrderAdapter f;
    private int g;
    private RelativeLayout h;
    private HealthTextView i;
    private List<OrderBriefInfo> j;
    private HealthRecycleView l;

    static class b extends BaseHandler<TradeOrderLayout> {
        b(TradeOrderLayout tradeOrderLayout) {
            super(tradeOrderLayout);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: aNW_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(TradeOrderLayout tradeOrderLayout, Message message) {
            int i = message.what;
            if (i == 1101) {
                tradeOrderLayout.a();
            } else if (i == 1102) {
                tradeOrderLayout.d();
            } else {
                LogUtil.h("TradeOrderLayout", "handleMessageWhenReferenceNotNull not match ");
            }
        }
    }

    public TradeOrderLayout(Context context, int i) {
        super(context);
        this.g = i;
        this.d = new b(this);
        b();
        c();
    }

    private void b() {
        LogUtil.a("TradeOrderLayout", "initView");
        this.l = (HealthRecycleView) LayoutInflater.from(BaseApplication.getContext()).inflate(R.layout.trade_order_layout, this).findViewById(R.id.trade_recyle_view);
        this.e = (RelativeLayout) findViewById(R.id.rl_hint);
        this.f3469a = (ImageView) findViewById(R.id.img_hint);
        this.i = (HealthTextView) findViewById(R.id.tv_hint);
        this.b = (RelativeLayout) findViewById(R.id.rl_content);
        this.h = (RelativeLayout) findViewById(R.id.order_loading);
        this.f = new TradeOrderAdapter(BaseApplication.getContext(), new ArrayList());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BaseApplication.getContext());
        linearLayoutManager.setOrientation(1);
        this.l.setLayoutManager(linearLayoutManager);
        this.l.setAdapter(this.f);
    }

    private void c() {
        if (!CommonUtil.aa(com.huawei.haf.application.BaseApplication.e())) {
            e();
        } else {
            OrderManager.orderQueryHistory(0L, System.currentTimeMillis(), 50L, new a(this));
            gkx.e().e("");
        }
    }

    static class a implements IBaseResponseCallback {
        private final WeakReference<TradeOrderLayout> b;

        a(TradeOrderLayout tradeOrderLayout) {
            this.b = new WeakReference<>(tradeOrderLayout);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            TradeOrderLayout tradeOrderLayout = this.b.get();
            if (tradeOrderLayout == null) {
                LogUtil.a("TradeOrderLayout", "layout is destroyed");
                return;
            }
            if (i == 0 && (obj instanceof List)) {
                LogUtil.h("TradeOrderLayout", "orderQueryHistory success");
                tradeOrderLayout.j = (List) obj;
                if (!koq.b(tradeOrderLayout.j)) {
                    tradeOrderLayout.d.sendEmptyMessage(1101);
                    return;
                }
                LogUtil.h("TradeOrderLayout", "mProductSummary = null");
                tradeOrderLayout.c = ExceptionCode.CHECK_FILE_SIZE_FAILED;
                tradeOrderLayout.d.sendEmptyMessage(1102);
                return;
            }
            LogUtil.h("TradeOrderLayout", "orderQueryHistory fail errorCode = ", Integer.valueOf(i));
            tradeOrderLayout.c = ExceptionCode.CHECK_FILE_SIZE_FAILED;
            tradeOrderLayout.d.sendEmptyMessage(1102);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        this.f.b(this.j, this.g);
        this.h.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        this.h.setVisibility(8);
        this.b.setVisibility(8);
        this.e.setVisibility(0);
        if (this.c == 1105) {
            this.i.setText(BaseApplication.getContext().getString(R.string._2130844074_res_0x7f0219aa));
            this.f3469a.setImageResource(R.drawable._2131427934_res_0x7f0b025e);
        } else {
            this.i.setText(BaseApplication.getContext().getString(R.string._2130841548_res_0x7f020fcc));
        }
    }

    private void e() {
        if (this.g == 0) {
            LogUtil.h("TradeOrderLayout", "net not ok");
        } else {
            d();
        }
    }
}
