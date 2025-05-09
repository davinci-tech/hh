package com.huawei.ui.commonui.dialog;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.HealthDataInputDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.scrollview.ScrollScaleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class HealthDataInputDialog extends BaseDialog {

    /* renamed from: a, reason: collision with root package name */
    private Drawable f8809a;
    private boolean b;
    private HealthButton c;
    private HealthTextView d;
    private Context e;
    private View f;
    private Drawable g;
    private HealthSubHeader h;
    private HealthButton i;
    private List<a> j;
    private int m;
    private ViewGroup o;

    public interface DataSetFormatter {
        String format(int i);
    }

    public interface PositiveBtnClickListener {
        void onClick(List<Integer> list);
    }

    public interface SelectedValueProcessor {
        String format(int i);

        int process(int i, int i2);
    }

    public HealthDataInputDialog(Context context) {
        super(context, R.style.CustomDialog);
        this.j = new ArrayList();
        this.e = context;
        b();
    }

    private void b() {
        View inflate = LayoutInflater.from(this.e).inflate(R.layout.health_data_input_dialog_layout, (ViewGroup) null);
        this.f = inflate;
        inflate.setWillNotDraw(false);
        HealthSubHeader healthSubHeader = (HealthSubHeader) this.f.findViewById(R.id.sub_header);
        this.h = healthSubHeader;
        healthSubHeader.setSubHeaderBackgroundColor(ContextCompat.getColor(this.e, R$color.common_transparent));
        if (LanguageUtil.d(this.e) || LanguageUtil.p(this.e)) {
            this.h.setHeadTitleForEnglish();
        }
        this.o = (ViewGroup) this.f.findViewById(R.id.views_container);
        this.d = (HealthTextView) this.f.findViewById(R.id.dialog_content);
        this.i = (HealthButton) this.f.findViewById(R.id.positive_btn);
        HealthButton healthButton = (HealthButton) this.f.findViewById(R.id.negative_btn);
        this.c = healthButton;
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: nlm
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HealthDataInputDialog.this.czo_(view);
            }
        });
        setContentView(this.f);
    }

    public /* synthetic */ void czo_(View view) {
        dismiss();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d() {
        this.i.setBackground(this.g);
        this.i.setTextColor(ContextCompat.getColor(this.e, R$color.textColorPrimaryInverse));
        this.c.setBackground(this.f8809a);
        this.c.setTextColor(this.m);
    }

    public HealthDataInputDialog c(int i) {
        this.m = i;
        this.g = czm_(i);
        this.f8809a = czm_(ContextCompat.getColor(this.e, R$color.common_transparent));
        d();
        return this;
    }

    public HealthDataInputDialog e(String str) {
        this.h.setHeadTitleText(str);
        return this;
    }

    public HealthDataInputDialog a(String str) {
        this.d.setText(str);
        return this;
    }

    public HealthDataInputDialog e(boolean z) {
        this.d.setSplittable(z);
        return this;
    }

    public HealthDataInputDialog d(String str) {
        this.i.setText(str);
        return this;
    }

    public HealthDataInputDialog b(boolean z) {
        this.b = z;
        return this;
    }

    public HealthDataInputDialog d(e eVar) {
        View inflate = LayoutInflater.from(this.e).inflate(R.layout.health_data_input_scroll_ruler_layout, this.o, false);
        a aVar = new a(inflate, eVar);
        if (this.j.isEmpty() && aVar.f8810a != null) {
            aVar.f8810a.setVisibility(8);
        }
        this.j.add(aVar);
        this.o.addView(inflate);
        return this;
    }

    public void a(boolean z) {
        HealthButton healthButton = this.i;
        if (healthButton != null) {
            healthButton.setEnabled(z);
            if (!z) {
                this.i.setAlpha(0.4f);
            } else {
                this.i.setAlpha(1.0f);
                d();
            }
        }
    }

    public HealthDataInputDialog czn_(View view) {
        this.o.addView(view);
        return this;
    }

    private Drawable czm_(int i) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(i);
        gradientDrawable.setCornerRadius(this.e.getResources().getDimensionPixelSize(R.dimen._2131362954_res_0x7f0a048a));
        return gradientDrawable;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        List<a> list;
        super.onWindowFocusChanged(z);
        if (!z || (list = this.j) == null) {
            return;
        }
        for (a aVar : list) {
            if (aVar != null) {
                aVar.d();
            }
        }
    }

    public boolean a() {
        if (!this.b) {
            return false;
        }
        boolean z = true;
        for (a aVar : this.j) {
            if (aVar != null && aVar.d != null && !aVar.d.d) {
                z = false;
            }
        }
        return z;
    }

    public static class e {

        /* renamed from: a, reason: collision with root package name */
        String f8811a;
        DataSetFormatter b;
        boolean c;
        boolean d;
        int e;
        SelectedValueProcessor f;
        String g;
        int h;
        String i;
        int j;
        int o = 10;

        public e(int i, int i2, DataSetFormatter dataSetFormatter, SelectedValueProcessor selectedValueProcessor) {
            this.j = i;
            this.h = i2;
            this.b = dataSetFormatter;
            this.f = selectedValueProcessor;
        }

        public e b(String str) {
            this.g = str;
            return this;
        }

        public e c(int i) {
            this.e = i;
            return this;
        }

        public e a(String str) {
            this.i = str;
            return this;
        }

        public e d(int i) {
            this.o = i;
            return this;
        }

        public e d(String str) {
            this.f8811a = str;
            return this;
        }

        public e b(boolean z) {
            this.c = z;
            return this;
        }
    }

    public HealthDataInputDialog c(final PositiveBtnClickListener positiveBtnClickListener) {
        HealthButton healthButton = this.i;
        if (healthButton != null) {
            healthButton.setOnClickListener(new View.OnClickListener() { // from class: nlj
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HealthDataInputDialog.this.czp_(positiveBtnClickListener, view);
                }
            });
        }
        return this;
    }

    public /* synthetic */ void czp_(PositiveBtnClickListener positiveBtnClickListener, View view) {
        ArrayList arrayList = new ArrayList();
        for (a aVar : this.j) {
            if (aVar != null) {
                arrayList.add(Integer.valueOf(aVar.g));
            }
        }
        positiveBtnClickListener.onClick(arrayList);
        ViewClickInstrumentation.clickOnView(view);
    }

    public HealthDataInputDialog czq_(View.OnClickListener onClickListener) {
        HealthButton healthButton = this.c;
        if (healthButton != null) {
            healthButton.setOnClickListener(onClickListener);
        }
        return this;
    }

    public class a {

        /* renamed from: a, reason: collision with root package name */
        View f8810a;
        ScrollScaleView b;
        String c;
        e d;
        HealthTextView e;
        HealthTextView f;
        int g;
        HealthTextView h;
        HealthTextView i;
        View j;
        ConstraintLayout l;

        public a(View view, e eVar) {
            this.j = view;
            this.d = eVar;
            this.i = (HealthTextView) view.findViewById(R.id.selected_value_text);
            ScrollScaleView scrollScaleView = (ScrollScaleView) this.j.findViewById(R.id.scroll_scale);
            this.b = scrollScaleView;
            scrollScaleView.setCustomStartColor(HealthDataInputDialog.this.m);
            this.f = (HealthTextView) this.j.findViewById(R.id.selected_title_text);
            this.h = (HealthTextView) this.j.findViewById(R.id.selected_unit_text);
            this.f8810a = this.j.findViewById(R.id.divider);
            this.l = (ConstraintLayout) this.j.findViewById(R.id.blood_data_input_title_parent);
            this.e = (HealthTextView) this.j.findViewById(R.id.hint_content);
            if (LanguageUtil.bp(HealthDataInputDialog.this.e)) {
                this.l.setLayoutDirection(0);
            }
            int i = this.d.h;
            DataSetFormatter dataSetFormatter = this.d.b;
            ArrayList arrayList = new ArrayList();
            int i2 = this.d.o;
            for (int i3 = this.d.j; i3 <= i; i3 += i2) {
                arrayList.add(dataSetFormatter.format(i3));
            }
            this.b.setOnSelectedListener(new ScrollScaleView.OnSelectedListener() { // from class: nln
                @Override // com.huawei.ui.commonui.scrollview.ScrollScaleView.OnSelectedListener
                public final void onSelected(List list, int i4) {
                    HealthDataInputDialog.a.this.e(list, i4);
                }
            });
            this.b.setData(arrayList, 10, 40);
            this.b.setSelectedPosition(this.d.e);
            this.f.setText(this.d.g);
            this.h.setText(this.d.i);
            if (this.d.c) {
                this.l.setVisibility(8);
                this.e.setVisibility(0);
                this.e.setText(this.d.f8811a);
            }
        }

        public /* synthetic */ void e(List list, int i) {
            String valueOf;
            SelectedValueProcessor selectedValueProcessor = this.d.f;
            int i2 = this.d.j;
            if (selectedValueProcessor != null) {
                int process = selectedValueProcessor.process(i, i2);
                this.g = process;
                valueOf = selectedValueProcessor.format(process);
            } else {
                int i3 = i + i2;
                this.g = i3;
                valueOf = String.valueOf(i3);
            }
            this.c = valueOf;
            this.i.setText(valueOf);
            if (this.l.getVisibility() == 8) {
                this.e.setVisibility(8);
                this.l.setVisibility(0);
                this.d.d = true;
                if (HealthDataInputDialog.this.a()) {
                    HealthDataInputDialog.this.a(true);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d() {
            this.i.setText(this.c);
            this.i.setVisibility(0);
            this.f.setText(this.d.g);
            this.f.setVisibility(0);
            this.h.setText(this.d.i);
            this.h.setVisibility(0);
        }
    }
}
