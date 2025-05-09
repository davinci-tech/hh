package com.huawei.healthcloud.plugintrack.runningroute.data;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.runningroute.data.RouteSiteDistanceSortDialog;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.adapter.BaseRecyclerAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.HealthButtonBarLayout;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.emj;
import defpackage.eni;
import defpackage.gyq;
import defpackage.gyu;
import defpackage.koq;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes4.dex */
public class RouteSiteDistanceSortDialog {
    private Context b;
    private LinearLayout c;
    private HealthRecycleView d;
    private int f;
    private int h;
    private HealthButton i;
    private HealthRecycleView k;
    private View l;
    private CustomAlertDialog.Builder m;
    private CustomAlertDialog n;

    /* renamed from: a, reason: collision with root package name */
    private final List<d> f3552a = new ArrayList();
    private final List<d> g = new ArrayList();
    private final a e = new a();
    private final a j = new a();

    public interface OnDialogClickListener {
        void onDialogClick(gyq gyqVar);
    }

    public RouteSiteDistanceSortDialog(Context context) {
        this.b = context;
        this.l = View.inflate(context, R.layout.dialog_path_type_sort, null);
        this.m = new CustomAlertDialog.Builder(context).cyp_(this.l);
        this.c = (LinearLayout) this.l.findViewById(R.id.distance_layout);
        this.k = (HealthRecycleView) this.l.findViewById(R.id.pathType_recycle);
        this.d = (HealthRecycleView) this.l.findViewById(R.id.distance_recycle);
        CustomAlertDialog c = this.m.c();
        this.n = c;
        c.setCancelable(false);
        ((HealthButtonBarLayout) this.n.findViewById(R.id.button_bar)).setDividerDrawable(ContextCompat.getDrawable(this.b, R.drawable._2131427925_res_0x7f0b0255));
        this.i = (HealthButton) this.n.findViewById(R.id.dialog_btn_negative);
    }

    private void d(gyq gyqVar, OnDialogClickListener onDialogClickListener) {
        a(gyqVar);
        e(gyqVar);
        e(gyqVar, onDialogClickListener);
        b(gyqVar, onDialogClickListener);
    }

    private void e(final gyq gyqVar, final OnDialogClickListener onDialogClickListener) {
        this.m.cyo_(R.string._2130841395_res_0x7f020f33, new DialogInterface.OnClickListener() { // from class: gyw
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                RouteSiteDistanceSortDialog.this.aWS_(gyqVar, onDialogClickListener, dialogInterface, i);
            }
        }).cyn_(R.string._2130840116_res_0x7f020a34, new DialogInterface.OnClickListener() { // from class: gys
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                RouteSiteDistanceSortDialog.aWR_(RouteSiteDistanceSortDialog.OnDialogClickListener.this, gyqVar, dialogInterface, i);
            }
        });
    }

    public /* synthetic */ void aWS_(gyq gyqVar, OnDialogClickListener onDialogClickListener, DialogInterface dialogInterface, int i) {
        LogUtil.a("RouteSiteDistanceSortDialog", "showFeedBackDialog click cancel");
        gyqVar.b(this.h);
        gyqVar.e(this.f);
        onDialogClickListener.onDialogClick(gyqVar);
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    public static /* synthetic */ void aWR_(OnDialogClickListener onDialogClickListener, gyq gyqVar, DialogInterface dialogInterface, int i) {
        LogUtil.a("RouteSiteDistanceSortDialog", "showFeedBackDialog click cancel");
        onDialogClickListener.onDialogClick(gyqVar);
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    private void b(final gyq gyqVar, final OnDialogClickListener onDialogClickListener) {
        this.i.setTextColor(BaseApplication.getContext().getResources().getColor(R.color._2131296928_res_0x7f0902a0, null));
        this.i.setOnClickListener(new View.OnClickListener() { // from class: gyt
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RouteSiteDistanceSortDialog.this.aWT_(gyqVar, onDialogClickListener, view);
            }
        });
    }

    public /* synthetic */ void aWT_(gyq gyqVar, OnDialogClickListener onDialogClickListener, View view) {
        LogUtil.a("RouteSiteDistanceSortDialog", "initTypeSortItems click agree");
        if (this.i.getCurrentTextColor() == BaseApplication.getContext().getColor(R.color._2131297805_res_0x7f09060d)) {
            b(0);
            e(0);
            this.j.e(this.g);
            this.e.e(this.f3552a);
            gyqVar.b(0);
            gyqVar.e(0);
            this.n.dismiss();
        }
        onDialogClickListener.onDialogClick(gyqVar);
        ViewClickInstrumentation.clickOnView(view);
    }

    public void a(gyq gyqVar, OnDialogClickListener onDialogClickListener) {
        if (gyqVar == null || gyqVar.a() == null || onDialogClickListener == null) {
            ReleaseLogUtil.d("RouteSiteDistanceSortDialog", "routeInfo or listener is null");
            return;
        }
        d(gyqVar, onDialogClickListener);
        if (!b()) {
            this.i.setTextColor(BaseApplication.getContext().getResources().getColor(R.color._2131297805_res_0x7f09060d, null));
        } else {
            this.i.setTextColor(BaseApplication.getContext().getResources().getColor(R.color._2131296928_res_0x7f0902a0, null));
        }
        if (this.n.isShowing()) {
            return;
        }
        this.n.show();
        this.n.setCanceledOnTouchOutside(true);
    }

    private boolean b() {
        return this.h == 0 && this.f == 0;
    }

    private void a(gyq gyqVar) {
        if (UnitUtil.h()) {
            LogUtil.h("RouteSiteDistanceSortDialog", "ImperialUnit, hide distance");
            this.c.setVisibility(8);
            this.d.setVisibility(8);
            return;
        }
        emj a2 = gyqVar.a();
        this.h = gyqVar.b();
        if (a2 == null || koq.b(a2.b())) {
            LogUtil.b("RouteSiteDistanceSortDialog", "DistanceTypes is empty");
            return;
        }
        this.f3552a.clear();
        List<eni> b = a2.b();
        b.sort(Comparator.comparingInt(new gyu()));
        for (eni eniVar : b) {
            this.f3552a.add(new d(eniVar, eniVar.d() == this.h));
        }
        this.e.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() { // from class: gyv
            @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter.OnItemClickListener
            public final void onItemClicked(RecyclerHolder recyclerHolder, int i, Object obj) {
                RouteSiteDistanceSortDialog.this.e(recyclerHolder, i, (RouteSiteDistanceSortDialog.d) obj);
            }
        });
        this.d.setAdapter(this.e);
        this.d.setLayoutManager(new HealthLinearLayoutManager(this.b));
        this.e.e(this.f3552a);
    }

    public /* synthetic */ void e(RecyclerHolder recyclerHolder, int i, d dVar) {
        int d2 = dVar.a().d();
        LogUtil.a("RouteSiteDistanceSortDialog", "item click with position: ", Integer.valueOf(i), " Id: ", Integer.valueOf(d2));
        if (this.h == d2) {
            return;
        }
        b(d2);
        c();
        this.e.e(this.f3552a);
    }

    private void e(gyq gyqVar) {
        emj a2 = gyqVar.a();
        this.f = gyqVar.d();
        if (a2 == null || koq.b(a2.c())) {
            return;
        }
        this.g.clear();
        List<eni> c = a2.c();
        c.sort(Comparator.comparingInt(new gyu()));
        for (eni eniVar : c) {
            this.g.add(new d(eniVar, eniVar.d() == this.f));
        }
        this.j.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() { // from class: gyz
            @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter.OnItemClickListener
            public final void onItemClicked(RecyclerHolder recyclerHolder, int i, Object obj) {
                RouteSiteDistanceSortDialog.this.d(recyclerHolder, i, (RouteSiteDistanceSortDialog.d) obj);
            }
        });
        this.k.setAdapter(this.j);
        this.k.setLayoutManager(new HealthLinearLayoutManager(this.b));
        this.j.e(this.g);
    }

    public /* synthetic */ void d(RecyclerHolder recyclerHolder, int i, d dVar) {
        int d2 = dVar.a().d();
        LogUtil.a("RouteSiteDistanceSortDialog", "item click with position: ", Integer.valueOf(i), " Id: ", Integer.valueOf(d2));
        if (this.f == d2) {
            return;
        }
        e(d2);
        c();
        this.j.e(this.g);
    }

    public void b(int i) {
        this.h = i;
        for (d dVar : this.f3552a) {
            dVar.d(dVar.a().d() == this.h);
        }
    }

    public void e(int i) {
        this.f = i;
        for (d dVar : this.g) {
            dVar.d(dVar.a().d() == this.f);
        }
    }

    private void c() {
        if (b()) {
            this.i.setTextColor(BaseApplication.getContext().getColor(R.color._2131296928_res_0x7f0902a0));
        } else {
            this.i.setTextColor(BaseApplication.getContext().getColor(R.color._2131297805_res_0x7f09060d));
        }
    }

    static class a extends BaseRecyclerAdapter<d> {
        private List<d> b;

        a() {
            super(new ArrayList(), R.layout.dialog_path_type_sort_item);
            this.b = new ArrayList();
        }

        @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void convert(RecyclerHolder recyclerHolder, int i, d dVar) {
            if (recyclerHolder == null || dVar == null || dVar.a() == null) {
                ReleaseLogUtil.d("RouteSiteDistanceSortDialog", "convert holder ", recyclerHolder, " itemData ", dVar, " position ", Integer.valueOf(i));
                return;
            }
            if (i == this.b.size() - 1) {
                recyclerHolder.a(R.id.item_divider, 8);
            } else {
                recyclerHolder.a(R.id.item_divider, 0);
            }
            CheckedTextView checkedTextView = (CheckedTextView) recyclerHolder.cwK_(R.id.item_divider_checked_textview);
            if (checkedTextView != null) {
                checkedTextView.setText(dVar.a().b());
                checkedTextView.setChecked(dVar.b());
            }
        }

        public void e(List<d> list) {
            this.b = list;
            refreshDataChange(list);
        }
    }

    public static class d {
        private final eni c;
        private boolean e;

        d(eni eniVar, boolean z) {
            this.c = eniVar;
            this.e = z;
        }

        public eni a() {
            return this.c;
        }

        public boolean b() {
            return this.e;
        }

        public void d(boolean z) {
            this.e = z;
        }
    }
}
