package com.huawei.ui.main.stories.health.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.health.constants.ObserveLabels;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.WeightBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.WeightBodyDataActivity;
import com.huawei.ui.main.stories.health.adapter.WeightBodyIndexRecycleAdapter;
import defpackage.cfe;
import defpackage.cff;
import defpackage.cfi;
import defpackage.doj;
import defpackage.gnm;
import defpackage.hcn;
import defpackage.ixx;
import defpackage.jdl;
import defpackage.koq;
import defpackage.qkd;
import defpackage.qqy;
import defpackage.qrf;
import defpackage.qsa;
import defpackage.qsj;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes6.dex */
public class WeightBodyIndexRecycleAdapter extends RecyclerView.Adapter {

    /* renamed from: a, reason: collision with root package name */
    private final Context f10141a;
    private final Context b;
    private List<qkd> c;
    private CustomTextAlertDialog d;
    private cfe e;
    private ArrayList<String> f;
    private boolean g;
    private byte h;
    private boolean i;
    private boolean j;
    private boolean k;
    private final Resources l;
    private ArrayList<String> m;
    private boolean n;
    private long o;
    private int r;
    private cfi t;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    public WeightBodyIndexRecycleAdapter(Context context, List<qkd> list, cfe cfeVar, boolean z) {
        this(context, list, cfeVar, z, false);
    }

    public WeightBodyIndexRecycleAdapter(Context context, List<qkd> list, cfe cfeVar, boolean z, boolean z2) {
        this.t = MultiUsersManager.INSTANCE.getCurrentUser();
        this.k = true;
        this.c = new ArrayList(16);
        this.f = new ArrayList<>();
        this.m = new ArrayList<>();
        Context e = BaseApplication.e();
        this.b = e;
        this.l = e.getResources();
        this.f10141a = context;
        this.i = z;
        this.n = z2;
        this.c.clear();
        this.c.addAll(list);
        this.e = cfeVar;
        if (cfeVar == null) {
            LogUtil.h("HealthWeight_WeightBodyIndexRecycleAdapter", "WeightBodyIndexRecycleAdapter mBean is null");
            return;
        }
        e();
        if (this.e.e() > 0 && this.e.t() > 0) {
            this.h = cff.c(this.e.an());
        } else {
            b();
        }
    }

    private void b() {
        MultiUsersManager.INSTANCE.getCurrentUser(new WeightBaseResponseCallback() { // from class: qgk
            @Override // com.huawei.hwbasemgr.WeightBaseResponseCallback
            public final void onResponse(int i, Object obj) {
                WeightBodyIndexRecycleAdapter.this.c(i, (cfi) obj);
            }
        });
    }

    public /* synthetic */ void c(final int i, final cfi cfiVar) {
        Context context = this.f10141a;
        if (!(context instanceof Activity)) {
            LogUtil.h("HealthWeight_WeightBodyIndexRecycleAdapter", "asyncGetUser mActivity instanceof Activity false");
        } else {
            ((Activity) context).runOnUiThread(new Runnable() { // from class: qgi
                @Override // java.lang.Runnable
                public final void run() {
                    WeightBodyIndexRecycleAdapter.this.d(cfiVar, i);
                }
            });
        }
    }

    public /* synthetic */ void d(cfi cfiVar, int i) {
        if (cfiVar != null && i == 0) {
            this.t = cfiVar;
        } else {
            LogUtil.h("HealthWeight_WeightBodyIndexRecycleAdapter", "asyncGetUser user is null or errorCode not success");
            this.t = MultiUsersManager.INSTANCE.getCurrentUser();
        }
        cfi cfiVar2 = this.t;
        if (cfiVar2 == null) {
            LogUtil.h("HealthWeight_WeightBodyIndexRecycleAdapter", "asyncGetUser mUser is null");
        } else {
            this.h = cff.c(cfiVar2.c());
            notifyDataSetChanged();
        }
    }

    public void d(cfe cfeVar) {
        this.e = cfeVar;
        e();
    }

    public void e(List<qkd> list) {
        this.c.clear();
        if (list.size() <= 3) {
            this.k = false;
        } else {
            this.k = true;
        }
        this.f.clear();
        this.m.clear();
        this.c.addAll(list);
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new d(LayoutInflater.from(this.b).inflate(R.layout.body_index_detail_recycle_items, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        d dVar = (d) viewHolder;
        if (koq.b(this.c, i)) {
            LogUtil.h("HealthWeight_WeightBodyIndexRecycleAdapter", "onBindViewHolder is out of bounds");
            return;
        }
        qkd qkdVar = this.c.get(i);
        if (qkdVar == null) {
            LogUtil.h("HealthWeight_WeightBodyIndexRecycleAdapter", "onBindViewHolder item is null");
            return;
        }
        int e = qkdVar.e();
        if ((LanguageUtil.b(this.b) || LanguageUtil.ai(this.b)) && e == 5) {
            dVar.c.findViewById(R.id.body_index_recycle_data_layout).setLayoutDirection(0);
        }
        c(dVar, i);
        dVar.g.setText(this.l.getString(qkdVar.d()));
        dVar.f10142a.setText(qkdVar.a());
        boolean z = true;
        boolean z2 = e == 12 && !qkdVar.i();
        boolean z3 = e == 13 || e == 27;
        if (e != 29 && e != 28) {
            z = false;
        }
        if (z2 || z3 || z) {
            dVar.d.setVisibility(8);
            dVar.f.setTypeface(Typeface.create(Constants.FONT, 0));
        } else {
            dVar.d.setVisibility(0);
        }
        dVar.f.setText(qkdVar.c());
        if ((e == 27 || e == 25) && this.n) {
            dVar.e.setVisibility(0);
        } else {
            dVar.e.setVisibility(8);
        }
        d(dVar, qkdVar, e);
    }

    private void c(d dVar, final int i) {
        dVar.c.setOnClickListener(new View.OnClickListener() { // from class: qgl
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WeightBodyIndexRecycleAdapter.this.dDj_(i, view);
            }
        });
    }

    public /* synthetic */ void dDj_(int i, View view) {
        if (koq.b(this.c, i)) {
            LogUtil.h("HealthWeight_WeightBodyIndexRecycleAdapter", "clickItemView is out of bounds");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        qkd qkdVar = this.c.get(i);
        if (qkdVar == null) {
            LogUtil.h("HealthWeight_WeightBodyIndexRecycleAdapter", "clickItemView item is null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (qkdVar.e() == 12 && !qkdVar.i()) {
            d();
        } else {
            if (this.e == null) {
                LogUtil.h("HealthWeight_WeightBodyIndexRecycleAdapter", "clickItemView mBean is null");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("click", 1);
            hashMap.put("type", Integer.valueOf(qkdVar.e()));
            ixx.d().d(this.b, AnalyticsValue.WEIGHT_PAGE_CLICK_DATA_DETAIL_2160113.value(), hashMap, 0);
            LogUtil.a("HealthWeight_WeightBodyIndexRecycleAdapter", "clickItemView() mIsGuestMeasure = ", Boolean.valueOf(this.j));
            Intent intent = new Intent(this.b, (Class<?>) WeightBodyDataActivity.class);
            intent.putExtra("isWeight", false);
            intent.putExtra("position", i);
            intent.putExtra("WeightBean", this.e);
            intent.putExtra("start_time", this.o);
            intent.putExtra("start_type", this.r);
            intent.putExtra("is_show_change", this.g);
            intent.putExtra("isGuestMeasure", this.j);
            gnm.aPB_(this.f10141a, intent);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d() {
        final cfi currentUser = MultiUsersManager.INSTANCE.getCurrentUser();
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.f10141a);
        builder.b(this.l.getString(R$string.IDS_hw_pressure_adjust)).e(this.l.getString(R$string.IDS_device_wifi_pressure_calibrate_guide_dialog_msg, currentUser.h())).cyV_(this.l.getString(R$string.IDS_hw_pressure_adjust), new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.adapter.WeightBodyIndexRecycleAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                qsa.d(WeightBodyIndexRecycleAdapter.this.f10141a, currentUser.i());
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyS_(this.l.getString(R$string.IDS_settings_button_cancal), new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.adapter.WeightBodyIndexRecycleAdapter.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        if (this.d == null) {
            this.d = builder.a();
        }
        CustomTextAlertDialog customTextAlertDialog = this.d;
        if (customTextAlertDialog != null) {
            customTextAlertDialog.setCanceledOnTouchOutside(false);
            if (this.d.isShowing()) {
                return;
            }
            this.d.show();
        }
    }

    private void d(d dVar, qkd qkdVar, int i) {
        double b = qkdVar.b();
        if (i == 2) {
            int b2 = doj.b(b, Utils.o(), this.h, this.e.e());
            dVar.b.setTextColor(qrf.e(b2));
            dVar.b.setText(qqy.a(0, b2));
            b("BMI", b2);
        } else if (i != 9) {
            switch (i) {
                case 11:
                    int b3 = doj.b(b);
                    dVar.b.setTextColor(qrf.e(b3));
                    dVar.b.setText(qqy.i(0, b3));
                    b(ObserveLabels.HEART_RATE, b3);
                    break;
                case 12:
                    int c = doj.c(b);
                    dVar.b.setTextColor(qrf.h(c));
                    dVar.b.setText(qqy.g(0, c));
                    b("PRESSURE_INDEX", c);
                    break;
                default:
                    switch (i) {
                        case 25:
                            int b4 = doj.b(this.h, b);
                            dVar.b.setTextColor(qrf.i(b4));
                            dVar.b.setText(qqy.o(0, b4));
                            if (b4 == 2) {
                                this.f.add("WAIST_HIP_RATIO");
                                break;
                            }
                            break;
                        case 26:
                            int e = doj.e(this.h, b, this.e.e());
                            dVar.b.setTextColor(qrf.e(e));
                            dVar.b.setText(qqy.k(0, e));
                            dVar.f.setText(qkdVar.c());
                            b("RASM", e);
                            break;
                        case 27:
                            break;
                        default:
                            if (this.e != null) {
                                c(dVar.b, dVar.f, i, (int) this.e.getDoubleOrIntLevelByType(i));
                                break;
                            }
                            break;
                    }
                case 13:
                    dVar.f10142a.setText(qkdVar.a());
                    dVar.f10142a.setTextSize(0, this.l.getDimensionPixelSize(R.dimen._2131362922_res_0x7f0a046a));
                    dVar.f10142a.setPadding(0, hcn.a(this.b, 4.0f), 0, 0);
                    dVar.f.setText("");
                    dVar.b.setText("");
                    dVar.f10142a.setAutoTextInfo(10, 1, 0);
                    break;
            }
        } else {
            a(dVar, b);
            dVar.b.setText("");
        }
        b(dVar.b, i);
    }

    private void c(HealthTextView healthTextView, HealthTextView healthTextView2, int i, int i2) {
        if (i == 1) {
            healthTextView.setTextColor(qrf.e(i2));
            healthTextView.setText(qqy.f(0, i2));
            b("BODY_FAT", i2);
        }
        if (i == 10) {
            healthTextView.setTextColor(qrf.e(i2));
            healthTextView.setText(qqy.l(0, i2));
            b("SKELETAL_MUSCLE", i2);
            return;
        }
        if (i == 14) {
            healthTextView.setTextColor(qrf.e(i2));
            healthTextView.setText(qqy.c(0, i2));
            b("FAT_FREE", i2);
            return;
        }
        switch (i) {
            case 3:
                healthTextView.setTextColor(qrf.e(i2));
                healthTextView.setText(qqy.m(0, i2));
                b("WATER_RATE", i2);
                break;
            case 4:
                healthTextView2.setAutoTextInfo(10, 1, 0);
                healthTextView.setTextColor(qrf.e(i2));
                healthTextView.setText(qqy.b(0, i2));
                b("METABOLISM", i2);
                break;
            case 5:
                healthTextView.setTextColor(qrf.g(i2));
                healthTextView.setText(qqy.n(0, i2));
                b("VISCERAL_FAT", i2);
                break;
            case 6:
                healthTextView.setTextColor(qrf.e(i2));
                healthTextView.setText(qqy.h(0, i2));
                b("MUSCLE", i2);
                break;
            case 7:
                healthTextView.setTextColor(qrf.e(i2));
                healthTextView.setText(qqy.d(0, i2));
                b("BONE", i2);
                break;
            case 8:
                healthTextView.setTextColor(qrf.e(i2));
                healthTextView.setText(qqy.j(0, i2));
                b("PROTEIN", i2);
                break;
            default:
                LogUtil.h("HealthWeight_WeightBodyIndexRecycleAdapter", "initCardItemForOther default type ", Integer.valueOf(i));
                break;
        }
    }

    private void b(HealthTextView healthTextView, int i) {
        if (i == 13 || i == 27 || i == 9) {
            healthTextView.setVisibility(4);
        } else {
            healthTextView.setVisibility(0);
        }
        if (!this.i || i == 2 || i == 11) {
            return;
        }
        healthTextView.setVisibility(4);
    }

    private void b(String str, int i) {
        if (this.k) {
            if (i > 2) {
                this.f.add(str);
            }
            if (i < 2) {
                this.m.add(str);
            }
        }
    }

    private void a(d dVar, double d2) {
        int i;
        if (d2 > 0.0d) {
            int i2 = (int) d2;
            String e = UnitUtil.e(i2, 1, 0);
            SpannableString spannableString = new SpannableString(this.l.getQuantityString(R.plurals._2130903043_res_0x7f030003, i2, e));
            int length = spannableString.length();
            int length2 = e.length();
            if (length2 > length) {
                LogUtil.h("HealthWeight_WeightBodyIndexRecycleAdapter", "judgeBodyYear ageLength is error");
                length2 = length;
            }
            int indexOf = spannableString.toString().indexOf(e);
            int i3 = indexOf + length2;
            int i4 = 12;
            if (LanguageUtil.ai(this.b)) {
                indexOf = length - length2;
                i3 = length;
                i4 = 10;
                i = 12;
            } else {
                i = 18;
            }
            spannableString.setSpan(new AbsoluteSizeSpan(i4, true), 0, length, 17);
            if (indexOf != -1) {
                spannableString.setSpan(new AbsoluteSizeSpan(i, true), indexOf, i3, 17);
            }
            dVar.f10142a.setText(spannableString);
            return;
        }
        dVar.f10142a.setText("--");
    }

    private void e() {
        if (this.e == null) {
            this.g = false;
            LogUtil.h("HealthWeight_WeightBodyIndexRecycleAdapter", "getBodyTypeData mBean is null");
            return;
        }
        this.g = false;
        this.r = 0;
        cfi cfiVar = this.t;
        final String i = cfiVar == null ? null : cfiVar.i();
        final int e = qsj.e(this.e);
        final long au = this.e.au();
        ThreadPoolManager.d().execute(new Runnable() { // from class: qgm
            @Override // java.lang.Runnable
            public final void run() {
                WeightBodyIndexRecycleAdapter.this.b(i, au, e);
            }
        });
    }

    public /* synthetic */ void b(String str, long j, int i) {
        long j2;
        int i2;
        ArrayList<cfe> b = qsj.b(str, j, this.t, this.i);
        if (b.size() <= 1) {
            return;
        }
        int size = b.size() - 1;
        cfe cfeVar = b.get(size);
        if (jdl.f(cfeVar.au(), j)) {
            return;
        }
        while (true) {
            if (size < 0) {
                break;
            }
            if (jdl.f(cfeVar.au(), b.get(size).au())) {
                size--;
            } else if (b.get(size).au() == j && cfeVar.t() > 0) {
                i2 = qsj.e(cfeVar);
                j2 = cfeVar.au();
            }
        }
        j2 = 0;
        i2 = 0;
        i = 0;
        a(i2, i2 != i, j2);
    }

    private void a(final int i, final boolean z, final long j) {
        Context context = this.f10141a;
        if (!(context instanceof Activity)) {
            LogUtil.h("HealthWeight_WeightBodyIndexRecycleAdapter", "updateBodyTypeChange mActivity is not Activity");
        } else {
            ((Activity) context).runOnUiThread(new Runnable() { // from class: qgp
                @Override // java.lang.Runnable
                public final void run() {
                    WeightBodyIndexRecycleAdapter.this.b(i, z, j);
                }
            });
        }
    }

    public /* synthetic */ void b(int i, boolean z, long j) {
        this.r = i;
        this.g = z;
        this.o = j;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.c.size();
    }

    public void c(boolean z) {
        this.j = z;
    }

    static class d extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private final HealthTextView f10142a;
        private final HealthTextView b;
        private final View c;
        private final View d;
        private final HealthImageView e;
        private final HealthTextView f;
        private final HealthTextView g;

        d(View view) {
            super(view);
            this.c = view;
            this.g = (HealthTextView) view.findViewById(R.id.body_index_recycle_title);
            this.f10142a = (HealthTextView) view.findViewById(R.id.body_index_recycle_value);
            this.f = (HealthTextView) view.findViewById(R.id.body_index_recycle_unit);
            this.b = (HealthTextView) view.findViewById(R.id.body_index_recycle_level);
            this.d = view.findViewById(R.id.body_index_recycle_margin_view);
            this.e = (HealthImageView) view.findViewById(R.id.body_index_tips_icon);
        }
    }
}
