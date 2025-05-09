package com.huawei.health.knit.section.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.health.knit.section.adapter.SectionRemindViewAdapter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.eek;
import defpackage.nru;
import defpackage.nrz;
import defpackage.nsy;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes3.dex */
public class SectionRemindView extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private List<eek> f2722a;
    private Context b;
    private SectionRemindViewAdapter c;
    private OnClickSectionListener d;
    private boolean e;
    private String f;
    private boolean g;
    private HealthRecycleView h;
    private String i;
    private HealthTextView j;
    private View k;
    private LinearLayout l;
    private boolean m;
    private HealthTextView n;
    private Integer o;
    private View p;
    private String q;
    private String r;
    private List<Object> s;
    private ImageView t;

    public SectionRemindView(Context context) {
        this(context, null);
    }

    public SectionRemindView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SectionRemindView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = false;
        this.f2722a = new ArrayList();
        this.b = context;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        LogUtil.a("SectionRemindView", "onCreateView");
        this.b = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.section_remind_view, (ViewGroup) this, false);
        this.p = inflate;
        return inflate;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        LogUtil.a("SectionRemindView", "start to bindViewParams");
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.a("SectionRemindView", "no need to bind");
            return;
        }
        this.q = (String) nru.c(hashMap, "REMIND_TEXT", String.class, "");
        this.s = (List) nru.c(hashMap, "REMIND_IMG_VIEW", ArrayList.class, null);
        this.o = (Integer) nru.c(hashMap, "REMIND_ARROWS", Integer.class, null);
        this.r = (String) nru.c(hashMap, "REMIND_HISTORY_TEXT", String.class, "");
        this.d = (OnClickSectionListener) nru.c(hashMap, "CLICK_EVENT_LISTENER", OnClickSectionListener.class, null);
        this.g = ((Boolean) nru.c(hashMap, "IS_REMIND_SHOW", Boolean.class, false)).booleanValue();
        this.m = ((Boolean) nru.c(hashMap, "REMIND_SHOW_MODEL", Boolean.class, false)).booleanValue();
        this.i = (String) nru.c(hashMap, "LOW_REMIND_TEXT", String.class, "");
        this.f = (String) nru.c(hashMap, "HIGH_REMIND_TEXT", String.class, "");
        this.f2722a = (List) nru.c(hashMap, "REMIND_DATA", ArrayList.class, null);
        if (!this.e) {
            LogUtil.a("SectionRemindView", "mHasInitDataAndView" + this.e);
            c();
            this.e = true;
        }
        LogUtil.a("SectionRemindView", "showData is " + this.m);
        b();
        b(this.m);
        setRemindVisible(this.g);
    }

    private void c() {
        this.l = (LinearLayout) findViewById(R.id.remind_layout_root);
        this.k = findViewById(R.id.warning_title);
        this.h = (HealthRecycleView) findViewById(R.id.warning_recycle_view);
        this.j = (HealthTextView) findViewById(R.id.warning_history_text);
        this.t = (ImageView) findViewById(R.id.warning_tip_img);
        this.n = (HealthTextView) findViewById(R.id.warning_des);
        a();
        j();
        f();
        d();
        e();
    }

    private void b() {
        this.h.setIsScroll(false);
        this.c = new SectionRemindViewAdapter(this.f2722a, R.layout.section_temperature_warning_home, this.i, this.f);
        this.h.setLayoutManager(new LinearLayoutManager(this.b) { // from class: com.huawei.health.knit.section.view.SectionRemindView.1
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return false;
            }
        });
        this.h.setAdapter(this.c);
        this.c.notifyDataSetChanged();
    }

    private void e() {
        LogUtil.a("SectionRemindView", "begin to setClickEventListener");
        if (this.d instanceof OnClickSectionListener) {
            if (this.t != null) {
                LogUtil.a("SectionRemindView", "mWarningTipImg listener");
                this.t.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.SectionRemindView.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        LogUtil.a("SectionRemindView", "set warningTipImg onClick");
                        SectionRemindView.this.d.onClick("REMIND_WARNING_TIP_IMG");
                        ViewClickInstrumentation.clickOnView(view);
                    }
                });
            }
            if (this.k != null) {
                LogUtil.a("SectionRemindView", "mTitle listener");
                this.k.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.SectionRemindView.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        LogUtil.a("SectionRemindView", "set mTitle onClick");
                        SectionRemindView.this.d.onClick("REMIND_TITLE");
                        ViewClickInstrumentation.clickOnView(view);
                    }
                });
            }
            if (this.j != null) {
                LogUtil.a("SectionRemindView", "mHistoryText listener");
                this.j.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.SectionRemindView.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        LogUtil.a("SectionRemindView", "set mHistoryText onClick");
                        SectionRemindView.this.d.onClick("REMIND_HISTORY_VIEW");
                        ViewClickInstrumentation.clickOnView(view);
                    }
                });
            }
        }
    }

    private void j() {
        List<Object> list = this.s;
        if (list != null && list.size() == 2 && (this.s.get(0) instanceof Integer)) {
            LogUtil.a("SectionRemindView", "start to set warning tip img");
            StateListDrawable ajB_ = ajB_(((Integer) this.s.get(0)).intValue(), ((Integer) this.s.get(1)).intValue());
            if (ajB_ != null) {
                this.t.setBackground(ajB_);
            }
        }
    }

    private void f() {
        Drawable drawable;
        if (this.o == null) {
            return;
        }
        ImageView imageView = (ImageView) findViewById(R.id.warning_arrow);
        if (LanguageUtil.bc(this.b)) {
            drawable = nrz.cKn_(this.b, this.o.intValue());
        } else {
            drawable = this.b.getResources().getDrawable(this.o.intValue());
        }
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        imageView.setImageDrawable(drawable);
    }

    private void a() {
        nsy.cMr_(this.n, this.q);
    }

    private void d() {
        nsy.cMr_(this.j, this.r);
    }

    private StateListDrawable ajB_(int i, int i2) {
        LogUtil.a("SectionRemindView", "addStateDrawable");
        Resources resources = this.b.getResources();
        if (resources != null) {
            StateListDrawable stateListDrawable = new StateListDrawable();
            Drawable drawable = i == -1 ? null : resources.getDrawable(i, null);
            Drawable drawable2 = i2 != -1 ? resources.getDrawable(i2, null) : null;
            stateListDrawable.addState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled}, drawable2);
            stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, drawable2);
            stateListDrawable.addState(new int[]{android.R.attr.state_enabled}, drawable);
            stateListDrawable.addState(new int[0], drawable);
            return stateListDrawable;
        }
        LogUtil.a("SectionRemindView", "get StateListDrawable fail");
        return null;
    }

    private void b(boolean z) {
        if (z) {
            this.k.setVisibility(0);
            this.h.setVisibility(0);
            this.j.setVisibility(8);
            return;
        }
        this.k.setVisibility(8);
        this.h.setVisibility(8);
        this.j.setVisibility(0);
        if (this.l.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.l.getLayoutParams();
            layoutParams.topMargin = 50;
            this.l.setLayoutParams(layoutParams);
        }
    }

    private void setRemindVisible(boolean z) {
        if (z) {
            this.l.setVisibility(0);
        } else {
            this.l.setVisibility(8);
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionRemindView";
    }
}
