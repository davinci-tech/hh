package com.huawei.health.marketing.views;

import android.content.Context;
import android.os.Message;
import android.util.Pair;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nsn;
import health.compact.a.UnitUtil;

/* loaded from: classes3.dex */
public class HealthFloatingShapedBox extends HealthFloatBar {
    private static final int k = 2131363076;
    private c l;
    private HealthTextView m;
    private final Pair<Integer, Integer> n;
    private HealthTextView o;
    private LinearLayout p;
    private HealthTextView q;
    private HealthTextView r;
    private HealthTextView s;
    private HealthTextView t;

    static class c extends BaseHandler<HealthFloatingShapedBox> {
        c(HealthFloatingShapedBox healthFloatingShapedBox) {
            super(healthFloatingShapedBox);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: apq_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(HealthFloatingShapedBox healthFloatingShapedBox, Message message) {
            LogUtil.c("HealthFloatingShapedBox", "ShapeBoxHandler receive msg = ", Integer.valueOf(message.what));
            if (message.what != 1) {
                return;
            }
            healthFloatingShapedBox.g();
        }
    }

    public HealthFloatingShapedBox(Context context) {
        this(context, R.layout.bottom_shaped_floating_box);
    }

    public HealthFloatingShapedBox(Context context, int i) {
        super(context, i);
        this.n = BaseActivity.getSafeRegionWidth();
    }

    @Override // com.huawei.health.marketing.views.HealthFloatBar
    protected void e() {
        if (this.f == null) {
            this.f = (ImageView) findViewById(R.id.marketing_bar_image_shaped);
            this.g = (ImageView) findViewById(R.id.marketing_bar_image_shaped_wide);
            this.f.setOnClickListener(this);
            this.g.setOnClickListener(this);
            this.e = (ImageView) findViewById(R.id.marketing_close_icon);
            this.b = (HealthButton) findViewById(R.id.marketing_float_bar_button);
            this.b.setOnClickListener(this);
            this.h = (HealthTextView) findViewById(R.id.marketing_float_bar_text);
            this.h.setVisibility(8);
            this.b.setVisibility(8);
            this.p = (LinearLayout) findViewById(R.id.shape_endtime_layout);
            d();
            this.l = new c(this);
        }
        if (this.d == null) {
            return;
        }
        h();
        if (nsn.ag(this.c)) {
            setIconImage(this.g, this.d.getTahitiPicture());
            this.g.setVisibility(0);
            this.f.setVisibility(8);
        } else {
            setIconImage(this.f, this.d.getPicture());
            this.g.setVisibility(8);
            this.f.setVisibility(0);
        }
    }

    @Override // com.huawei.health.marketing.views.HealthFloatBar
    protected void c(boolean z) {
        if (!z) {
            LogUtil.h("HealthFloatingShapedBox", "float image not loaded");
            this.p.setVisibility(8);
            this.b.setVisibility(8);
            this.l.removeMessages(1);
            return;
        }
        this.e.setVisibility(0);
        if (this.d == null) {
            LogUtil.h("HealthFloatingShapedBox", "mMemberPromotionTemplate is empty");
            return;
        }
        if ("buttonName".equals(this.d.getDisplayControls())) {
            this.b.setVisibility(0);
            this.b.setText(this.d.getButtonName());
            this.p.setVisibility(8);
        } else if ("countdown".equals(this.d.getDisplayControls())) {
            this.p.setVisibility(0);
            this.b.setVisibility(8);
            b();
        } else {
            this.p.setVisibility(8);
            this.b.setVisibility(8);
        }
    }

    @Override // com.huawei.health.marketing.views.HealthFloatBar
    protected FrameLayout.LayoutParams getParams() {
        return (FrameLayout.LayoutParams) getLayoutParams();
    }

    private void h() {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) findViewById(R.id.shape_floating_box_layout).getLayoutParams();
        int dimensionPixelSize = this.c.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
        layoutParams.setMarginStart(((Integer) this.n.first).intValue() + dimensionPixelSize);
        layoutParams.setMarginEnd(dimensionPixelSize + ((Integer) this.n.second).intValue());
        layoutParams.bottomMargin = this.c.getResources().getDimensionPixelSize(k);
    }

    private void b() {
        LogUtil.a("HealthFloatingShapedBox", "initCountDownLayout DeadlineTime is ", Long.valueOf(this.d.getDeadlineTime()));
        if (this.d.getDeadlineTime() <= 0) {
            this.p.setVisibility(8);
            return;
        }
        if (this.o == null) {
            this.o = (HealthTextView) findViewById(R.id.shape_endtime_first_num);
            this.m = (HealthTextView) findViewById(R.id.shape_endtime_first);
            this.q = (HealthTextView) findViewById(R.id.shape_endtime_second_num);
            this.t = (HealthTextView) findViewById(R.id.shape_endtime_second);
            this.r = (HealthTextView) findViewById(R.id.shape_endtime_third_num);
            this.s = (HealthTextView) findViewById(R.id.shape_endtime_third);
        }
        if (this.l.hasMessages(1)) {
            return;
        }
        g();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (this.m == null) {
            LogUtil.h("HealthFloatingShapedBox", "updateTimer mShapeEndTimeFirst is not init, return");
            return;
        }
        long deadlineTime = this.d.getDeadlineTime() - System.currentTimeMillis();
        int[] b = b(deadlineTime);
        LogUtil.c("HealthFloatingShapedBox", "initCountDownLayout leftTime= ", Integer.valueOf(b[0]), Integer.valueOf(b[1]), Integer.valueOf(b[2]), Integer.valueOf(b[3]));
        if (deadlineTime < 86400000) {
            this.o.setText(c(b[1]));
            this.q.setText(c(b[2]));
            this.r.setText(c(b[3]));
            this.m.setText(this.c.getResources().getQuantityString(R.plurals._2130903503_res_0x7f0301cf, b[1]));
            this.t.setText(this.c.getResources().getQuantityString(R.plurals._2130903504_res_0x7f0301d0, b[2]));
            this.s.setText(this.c.getResources().getQuantityString(R.plurals._2130903505_res_0x7f0301d1, b[3]));
            if (deadlineTime >= 1000) {
                this.l.sendEmptyMessageDelayed(1, 1000L);
                return;
            }
            return;
        }
        this.o.setText(c(b[0]));
        this.q.setText(c(b[1]));
        this.r.setText(c(b[2]));
        this.m.setText(this.c.getResources().getQuantityString(R.plurals._2130903502_res_0x7f0301ce, b[0]));
        this.t.setText(this.c.getResources().getQuantityString(R.plurals._2130903503_res_0x7f0301cf, b[1]));
        this.s.setText(this.c.getResources().getQuantityString(R.plurals._2130903504_res_0x7f0301d0, b[2]));
        this.l.sendEmptyMessageDelayed(1, 60000L);
    }

    private int[] b(long j) {
        int[] iArr = new int[4];
        if (j <= 1000) {
            return iArr;
        }
        int i = (int) (j / 86400000);
        long j2 = j - (i * 86400000);
        int i2 = (int) (j2 / 3600000);
        long j3 = j2 - (i2 * 3600000);
        int i3 = (int) (j3 / 60000);
        iArr[0] = i;
        iArr[1] = i2;
        iArr[2] = i3;
        iArr[3] = (int) ((j3 - (i3 * 60000)) / 1000);
        return iArr;
    }

    private String c(int i) {
        String e = UnitUtil.e(i, 1, 0);
        String e2 = UnitUtil.e(0.0d, 1, 0);
        if (String.valueOf(i).length() != 1) {
            return e;
        }
        return e2 + e;
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        LogUtil.a("HealthFloatingShapedBox", "onWindowVisibilityChanged ");
        if (this.f2870a) {
            e(i);
        }
        super.onWindowVisibilityChanged(i);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        LogUtil.a("HealthFloatingShapedBox", "onDetachedFromWindow ");
        this.l.removeCallbacksAndMessages(null);
        super.onDetachedFromWindow();
    }

    @Override // com.huawei.health.marketing.views.HealthFloatBar
    protected void e(int i) {
        super.e(i);
        c cVar = this.l;
        if (cVar == null) {
            LogUtil.h("HealthFloatingShapedBox", "onPageVisibilityChange mHandler is null");
            return;
        }
        if (i == 0) {
            if (!cVar.hasMessages(1)) {
                this.l.sendEmptyMessage(1);
            }
        } else {
            cVar.removeMessages(1);
        }
        LogUtil.a("HealthFloatingShapedBox", "onPageVisibilityChange visibility = ", Integer.valueOf(i));
    }
}
