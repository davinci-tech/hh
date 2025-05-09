package com.huawei.basichealthmodel.ui.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.gson.reflect.TypeToken;
import com.huawei.basichealthmodel.R$plurals;
import com.huawei.basichealthmodel.R$string;
import com.huawei.basichealthmodel.listener.TaskDataListener;
import com.huawei.basichealthmodel.ui.adapter.TaskDialogEventRecyclerAdapter;
import com.huawei.basichealthmodel.ui.adapter.TaskDialogGridAdapter;
import com.huawei.basichealthmodel.ui.dialog.HealthLifeTaskDialog;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.arkuix.utils.ArkUIXConstants;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.healthmodel.bean.BloodPressurePlanResultBean;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.health.healthmodel.bean.HealthLifeTaskBean;
import com.huawei.health.healthmodel.bean.ImageBean;
import com.huawei.health.healthmodel.bean.PictureBean;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.gridview.HealthGridView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.recycleview.RecyclerItemDecoration;
import defpackage.atz;
import defpackage.aug;
import defpackage.aun;
import defpackage.auz;
import defpackage.awq;
import defpackage.ayq;
import defpackage.aza;
import defpackage.azi;
import defpackage.bcl;
import defpackage.bda;
import defpackage.cun;
import defpackage.cwi;
import defpackage.dpd;
import defpackage.dse;
import defpackage.jdl;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nrh;
import defpackage.nsj;
import defpackage.nsn;
import health.compact.a.CommonUtils;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class HealthLifeTaskDialog extends BaseDialog {
    private HealthLifeTaskDialog(Context context) {
        super(context, R.style.CustomDialog, 0);
    }

    public static class Builder implements View.OnTouchListener, View.OnClickListener, AdapterView.OnItemClickListener, ResponseCallback<atz> {

        /* renamed from: a, reason: collision with root package name */
        private Drawable f1919a;
        private final ArrayList<Drawable> aa;
        private int ab;
        private int ac;
        private HealthGridView ad;
        private final boolean ae;
        private final ImageBean af;
        private final int ag;
        private boolean ah;
        private final boolean ai;
        private final boolean aj;
        private boolean ak;
        private boolean al;
        private final boolean am;
        private boolean an;
        private String ao;
        private final int ap;
        private HealthTextView aq;
        private HealthTextView ar;
        private LinearLayout as;
        private int at;
        private HealthTextView au;
        private HealthTextView av;
        private HealthTextView aw;
        private HealthTextView ax;
        private HealthRecycleView ay;
        private final String az;
        private FrameLayout b;
        private final Resources ba;
        private HealthTextView bb;
        private int bc;
        private final HealthLifeTaskBean bd;
        private SparseArray<List<HealthLifeBean>> be;
        private HealthTextView bf;
        private final String bg;
        private HealthTextView bh;
        private View bk;
        private int bl;
        private SparseIntArray bm;
        private Drawable c;
        private Drawable d;
        private final Context e;
        private final HealthLifeBean f;
        private View g;
        private ImageView h;
        private List<BloodPressurePlanResultBean> i;
        private HealthTextView j;
        private int k;
        private int l;
        private HealthTextView m;
        private NoTitleCustomAlertDialog n;
        private final ResponseCallback<HealthLifeTaskBean> o;
        private LinearLayout p;
        private HealthTextView q;
        private final Context r;
        private HealthLifeTaskDialog s;
        private LinearLayout t;
        private Drawable u;
        private int v;
        private LinearLayout w;
        private TaskDialogEventRecyclerAdapter x;
        private final String y;
        private final ayq z;

        public Builder(Context context, HealthLifeTaskBean healthLifeTaskBean, String str, ResponseCallback<HealthLifeTaskBean> responseCallback) {
            Context e = BaseApplication.e();
            this.r = e;
            this.ba = e.getResources();
            this.aa = new ArrayList<>();
            this.i = new ArrayList();
            this.al = true;
            if (context instanceof Activity) {
                this.e = context;
            } else {
                LogUtil.h("HealthLife_HealthLifeTaskDialog", "Builder activity instanceof Activity false");
                this.e = BaseApplication.wa_();
            }
            if (healthLifeTaskBean == null) {
                LogUtil.h("HealthLife_HealthLifeTaskDialog", "Builder taskBean is null");
                this.bd = new HealthLifeTaskBean();
            } else {
                this.bd = healthLifeTaskBean;
            }
            if (TextUtils.isEmpty(str)) {
                this.y = "";
            } else {
                this.y = str;
            }
            this.o = responseCallback;
            this.ap = azi.t();
            this.z = new ayq(this);
            this.af = this.bd.getDialogImageBean();
            HealthLifeBean healthLifeBean = this.bd.getHealthLifeBean();
            if (healthLifeBean == null) {
                LogUtil.h("HealthLife_HealthLifeTaskDialog", "Builder mBean is null");
                this.f = new HealthLifeBean();
            } else {
                this.f = healthLifeBean;
            }
            int id = this.f.getId();
            this.ag = id;
            this.az = this.f.getResult();
            this.bg = this.f.getTarget();
            int complete = this.f.getComplete();
            this.l = complete;
            this.am = complete > 0;
            this.aj = id == 3;
            this.ai = id == 5;
            this.ae = id == 12;
            this.be = bda.nM_();
        }

        public HealthLifeTaskDialog a() {
            int nm_ = bcl.nm_(this.be, this.ag);
            this.bc = nm_;
            this.ao = bcl.e(this.f, nm_);
            o();
            l();
            s();
            i();
            g();
            bcl.b(this.bh, bcl.g(this.f));
            r();
            u();
            bcl.b(this.bb, bcl.f(this.f));
            p();
            if (this.aj) {
                m();
            }
            if (this.ae && !azi.x()) {
                c();
            }
            return this.s;
        }

        private void m() {
            List<atz> a2 = bcl.a(this.f);
            if (koq.b(a2)) {
                LogUtil.h("HealthLife_HealthLifeTaskDialog", "initLayoutForForIntensity list ", a2);
                return;
            }
            View inflate = View.inflate(this.r, R.layout.dialog_task_recycler_event, null);
            this.ay = (HealthRecycleView) inflate.findViewById(R.id.dialog_task_recycler_event);
            ArrayList arrayList = new ArrayList(4);
            arrayList.add(0);
            arrayList.add(0);
            arrayList.add(0);
            arrayList.add(0);
            int i = this.bl;
            int dimensionPixelSize = this.ba.getDimensionPixelSize(R.dimen._2131362466_res_0x7f0a02a2);
            this.ay.addItemDecoration(new RecyclerItemDecoration((((i - dimensionPixelSize) - this.ba.getDimensionPixelSize(R.dimen._2131362465_res_0x7f0a02a1)) - (this.ba.getDimensionPixelSize(R.dimen._2131363090_res_0x7f0a0512) * 3)) / 2, 0, arrayList));
            this.t.addView(inflate);
            this.t.setVisibility(0);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.r);
            linearLayoutManager.setOrientation(0);
            this.ay.setLayoutManager(linearLayoutManager);
            TaskDialogEventRecyclerAdapter taskDialogEventRecyclerAdapter = new TaskDialogEventRecyclerAdapter(a2);
            this.x = taskDialogEventRecyclerAdapter;
            taskDialogEventRecyclerAdapter.c(this);
            this.ay.setAdapter(this.x);
        }

        private void o() {
            ImageBean imageBean = this.af;
            if (imageBean == null) {
                this.c = lf_(nrf.cHR_(R$drawable.health_life_background));
                return;
            }
            if (this.ag != imageBean.getId() || !"1".equals(this.af.getImageScenario())) {
                this.c = lf_(nrf.cHR_(R$drawable.health_life_background));
                return;
            }
            ArrayList<PictureBean> pictureList = this.af.getPictureList();
            if (koq.b(pictureList)) {
                this.c = lf_(nrf.cHR_(R$drawable.health_life_background));
                return;
            }
            Iterator<PictureBean> it = pictureList.iterator();
            while (it.hasNext()) {
                PictureBean next = it.next();
                if (next != null) {
                    String path = next.getPath();
                    if (!azi.h(path)) {
                        int scenario = next.getScenario();
                        if (scenario != 110) {
                            switch (scenario) {
                                case 100:
                                    this.c = lf_(nrf.cHQ_(path));
                                    break;
                                case 101:
                                    this.f1919a = lf_(nrf.cHQ_(path));
                                    break;
                                case 102:
                                    this.d = lf_(nrf.cHQ_(path));
                                    break;
                                default:
                                    LogUtil.c("HealthLife_HealthLifeTaskDialog", "initImageList scenario ", Integer.valueOf(scenario));
                                    break;
                            }
                        } else {
                            this.u = lf_(nrf.cHQ_(path));
                        }
                    }
                }
            }
        }

        private Drawable lf_(Bitmap bitmap) {
            if (bitmap == null) {
                LogUtil.h("HealthLife_HealthLifeTaskDialog", "getRoundedDrawable bitmap is null");
            } else {
                this.bl = bitmap.getWidth();
                this.ab = bitmap.getHeight();
            }
            RoundedBitmapDrawable create = RoundedBitmapDrawableFactory.create(this.ba, bitmap);
            create.setAntiAlias(true);
            create.setDither(true);
            create.setCornerRadius(this.ba.getDimensionPixelSize(R.dimen._2131362601_res_0x7f0a0329));
            return create;
        }

        private void l() {
            if (this.bk == null) {
                this.bk = View.inflate(this.r, R.layout.dialog_task, null);
            }
            if (this.s == null) {
                HealthLifeTaskDialog healthLifeTaskDialog = new HealthLifeTaskDialog(this.e);
                this.s = healthLifeTaskDialog;
                healthLifeTaskDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: aym
                    @Override // android.content.DialogInterface.OnDismissListener
                    public final void onDismiss(DialogInterface dialogInterface) {
                        HealthLifeTaskDialog.Builder.this.lj_(dialogInterface);
                    }
                });
            }
            this.r.getTheme().resolveAttribute(R.attr._2131099975_res_0x7f060147, new TypedValue(), true);
            boolean y = nsn.y(this.e);
            this.ak = y;
            int d = dpd.d(this.e, y);
            if (this.ab <= 0) {
                this.ab = 1764;
            }
            if (this.bl <= 0) {
                this.bl = 984;
            }
            this.ab = dpd.a(this.e, this.ak, (this.ab * d) / this.bl);
            this.bl = d;
            Window window = this.s.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = this.bl;
            attributes.height = this.ab;
            if (new HealthColumnSystem(this.r, 0).e()) {
                attributes.y = this.ba.getDimensionPixelSize(R.dimen._2131362464_res_0x7f0a02a0);
                window.setGravity(80);
            } else {
                window.setGravity(17);
            }
            window.setAttributes(attributes);
            this.s.setContentView(this.bk);
        }

        public /* synthetic */ void lj_(DialogInterface dialogInterface) {
            if (this.an) {
                LogUtil.h("HealthLife_HealthLifeTaskDialog", "initDialog setOnDismissListener mIsExecute true");
            } else {
                v();
            }
        }

        private void s() {
            this.b = (FrameLayout) this.bk.findViewById(R.id.dialog_base_task_background);
            this.w = (LinearLayout) this.bk.findViewById(R.id.dialog_base_task_foreground);
            this.as = (LinearLayout) this.bk.findViewById(R.id.dialog_base_task_layout);
            this.bh = (HealthTextView) this.bk.findViewById(R.id.dialog_base_task_title);
            this.bf = (HealthTextView) this.bk.findViewById(R.id.dialog_base_task_subtitle);
            this.m = (HealthTextView) this.bk.findViewById(R.id.dialog_base_task_content);
            this.bb = (HealthTextView) this.bk.findViewById(R.id.dialog_base_task_rule);
            this.p = (LinearLayout) this.bk.findViewById(R.id.dialog_base_task_custom);
            this.t = (LinearLayout) this.bk.findViewById(R.id.dialog_base_task_event);
            this.aw = (HealthTextView) this.bk.findViewById(R.id.dialog_base_task_positive);
            this.av = (HealthTextView) this.bk.findViewById(R.id.dialog_base_task_neutral);
            this.ar = (HealthTextView) this.bk.findViewById(R.id.dialog_base_task_negative);
            this.q = (HealthTextView) this.bk.findViewById(R.id.dialog_base_task_detail);
            this.aq = (HealthTextView) this.bk.findViewById(R.id.dialog_base_task_know);
            this.bk.findViewById(R.id.dialog_base_task_about).setOnClickListener(this);
        }

        private void i() {
            Drawable drawable = this.c;
            Drawable drawable2 = this.am ? this.f1919a : drawable;
            if (drawable2 != null) {
                drawable = drawable2;
            }
            if (drawable == null) {
                LogUtil.h("HealthLife_HealthLifeTaskDialog", "initBackground drawable is null");
                drawable = lf_(nrf.cHR_(R$drawable.health_life_background));
            }
            this.b.setBackground(bcl.nk_(drawable, this.ak));
            ViewGroup.LayoutParams layoutParams = this.b.getLayoutParams();
            layoutParams.width = this.bl;
            layoutParams.height = this.ab;
            this.b.setLayoutParams(layoutParams);
            this.w.setBackground(this.ak ? null : this.u);
            ViewGroup.LayoutParams layoutParams2 = this.as.getLayoutParams();
            if (layoutParams2 instanceof FrameLayout.LayoutParams) {
                FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) layoutParams2;
                layoutParams3.gravity = this.ak ? 48 : 80;
                this.as.setLayoutParams(layoutParams3);
            }
        }

        private void g() {
            b(this.aw, bcl.i(this.f), true);
            b(this.av, bcl.b(this.f), true);
            b(this.ar, this.ao, this.al);
            b(this.q, bcl.c(this.f), true);
            b(this.aq, bcl.e(this.f), true);
            x();
            y();
        }

        private void b(HealthTextView healthTextView, String str, boolean z) {
            if (TextUtils.isEmpty(str)) {
                healthTextView.setVisibility(8);
                return;
            }
            healthTextView.setText(str);
            if (z) {
                healthTextView.setVisibility(0);
                healthTextView.setOnClickListener(this);
                healthTextView.setOnTouchListener(this);
                return;
            }
            healthTextView.setVisibility(4);
        }

        private void x() {
            if (this.aw.getVisibility() != 8) {
                c(this.aw, this.ba.getDimensionPixelSize(R.dimen._2131362566_res_0x7f0a0306));
                return;
            }
            if (this.av.getVisibility() != 8) {
                c(this.av, this.ba.getDimensionPixelSize(R.dimen._2131362566_res_0x7f0a0306));
                return;
            }
            if (this.ar.getVisibility() != 8) {
                c(this.ar, this.ba.getDimensionPixelSize(R.dimen._2131362566_res_0x7f0a0306));
                return;
            }
            if (this.q.getVisibility() != 8) {
                c(this.q, this.ba.getDimensionPixelSize(R.dimen._2131362566_res_0x7f0a0306));
            }
            if (this.aq.getVisibility() != 8) {
                c(this.aq, this.ba.getDimensionPixelSize(R.dimen._2131362363_res_0x7f0a023b));
            }
        }

        private void c(HealthTextView healthTextView, int i) {
            ViewGroup.LayoutParams layoutParams = healthTextView.getLayoutParams();
            if (layoutParams instanceof LinearLayout.LayoutParams) {
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
                layoutParams2.topMargin = this.ba.getDimensionPixelSize(R.dimen._2131362367_res_0x7f0a023f);
                layoutParams2.bottomMargin = i;
                healthTextView.setLayoutParams(layoutParams2);
            }
        }

        private void y() {
            if (this.aw.getVisibility() == 0) {
                d(this.aw);
                return;
            }
            if (this.av.getVisibility() == 0) {
                d(this.av);
                return;
            }
            if (this.ar.getVisibility() == 0) {
                d(this.ar);
            } else if (this.q.getVisibility() == 0) {
                d(this.q);
            } else if (this.aq.getVisibility() == 0) {
                d(this.aq);
            }
        }

        private void d(HealthTextView healthTextView) {
            HealthTextView healthTextView2 = this.aw;
            if (healthTextView2 != null) {
                healthTextView2.setBackground(null);
            }
            HealthTextView healthTextView3 = this.av;
            if (healthTextView3 != null) {
                healthTextView3.setBackground(null);
            }
            HealthTextView healthTextView4 = this.ar;
            if (healthTextView4 != null) {
                healthTextView4.setBackground(null);
            }
            HealthTextView healthTextView5 = this.q;
            if (healthTextView5 != null) {
                healthTextView5.setBackground(null);
            }
            HealthTextView healthTextView6 = this.aq;
            if (healthTextView6 != null) {
                healthTextView6.setBackground(null);
            }
            if (healthTextView != null) {
                healthTextView.setBackground(ContextCompat.getDrawable(this.r, R$drawable.button_background_model_dialog));
            }
        }

        private void r() {
            azi.b(ThreadPoolManager.d(), "HealthModelQueryCompletedTask", new Runnable() { // from class: ays
                @Override // java.lang.Runnable
                public final void run() {
                    HealthLifeTaskDialog.Builder.this.b();
                }
            });
        }

        public /* synthetic */ void b() {
            azi.lZ_(this.z, 2000, Integer.valueOf(auz.a(this.ap, this.f.getRecordDay(), this.ag)));
        }

        private void u() {
            bcl.b(this.m, bcl.d(this.f));
            if (this.l <= 0) {
                return;
            }
            t();
        }

        private void t() {
            int i = this.ap;
            if (i <= 0) {
                LogUtil.h("HealthLife_HealthLifeTaskDialog", "initRank mJoinTime", Integer.valueOf(i));
            } else {
                azi.b(ThreadPoolManager.d(), "HealthModelGetHealthLifeStat", new Runnable() { // from class: ayp
                    @Override // java.lang.Runnable
                    public final void run() {
                        HealthLifeTaskDialog.Builder.this.d();
                    }
                });
            }
        }

        public /* synthetic */ void d() {
            ArrayList arrayList = new ArrayList();
            arrayList.add(Integer.valueOf(this.ag));
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(Integer.valueOf(jdl.e(DateFormatUtil.c(this.ap), System.currentTimeMillis(), 1)));
            aug.d().c(arrayList, arrayList2, new IBaseResponseCallback() { // from class: ayg
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    HealthLifeTaskDialog.Builder.this.a(i, obj);
                }
            });
        }

        public /* synthetic */ void a(int i, Object obj) {
            if (i == -1) {
                LogUtil.h("HealthLife_HealthLifeTaskDialog", "initRank onFailure ");
                return;
            }
            if (obj instanceof aun) {
                aun aunVar = (aun) obj;
                if (!koq.b(aunVar.e())) {
                    dse dseVar = aunVar.e().get(0);
                    if (dseVar != null) {
                        azi.lZ_(this.z, 3000, Integer.valueOf(dseVar.c()));
                        return;
                    }
                    return;
                }
            }
            LogUtil.h("HealthLife_HealthLifeTaskDialog", "initRank onSuccess stats is null or length less than zero");
        }

        private void p() {
            String string;
            if (azi.d(awq.e(), this.ag).getGoalCycleType() != 2) {
                return;
            }
            SparseIntArray no_ = bcl.no_(this.be, this.ag);
            this.bm = no_;
            if (no_.size() <= 0) {
                this.p.setVisibility(8);
                w();
                return;
            }
            this.p.setVisibility(0);
            View inflate = View.inflate(this.r, R.layout.dialog_task_week, null);
            this.p.addView(inflate);
            lg_(inflate);
            HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.dialog_task_week_description);
            if (azi.v() == 7 && this.am) {
                healthTextView.setVisibility(8);
                return;
            }
            healthTextView.setVisibility(0);
            if (this.bc > 0) {
                Resources resources = this.ba;
                int i = R$string.IDS_health_model_rest_number;
                Resources resources2 = this.ba;
                int i2 = R$plurals.IDS_health_model_mission_complete_number;
                int i3 = this.bc;
                string = resources.getString(i, resources2.getQuantityString(i2, i3, Integer.valueOf(i3)));
            } else {
                string = this.ba.getString(R$string.IDS_health_model_not_rest_number);
            }
            healthTextView.setText(string);
        }

        private void w() {
            final int b = DateFormatUtil.b(System.currentTimeMillis());
            azi.d(ThreadPoolManager.d(), new Runnable() { // from class: ayt
                @Override // java.lang.Runnable
                public final void run() {
                    HealthLifeTaskDialog.Builder.this.b(b);
                }
            });
        }

        public /* synthetic */ void b(final int i) {
            awq.e().a(azi.p(), i, new TaskDataListener() { // from class: com.huawei.basichealthmodel.ui.dialog.HealthLifeTaskDialog.Builder.4
                @Override // com.huawei.basichealthmodel.listener.TaskDataListener
                public void onDataChange(int i2, SparseArray<HealthLifeBean> sparseArray) {
                }

                @Override // com.huawei.basichealthmodel.listener.TaskDataListener
                public void onAllDataChange(int i2, SparseArray<List<HealthLifeBean>> sparseArray) {
                    if (i2 != 0 || sparseArray == null || sparseArray.size() <= 0 || DateFormatUtil.b(jdl.c(DateFormatUtil.c(i), 1, 0)) != DateFormatUtil.b(jdl.c(DateFormatUtil.c(awq.e().e(i)), 1, 0))) {
                        return;
                    }
                    bda.nR_(sparseArray);
                }
            });
        }

        private void lg_(View view) {
            int[] iArr = {R.id.dialog_task_week_sunday, R.id.dialog_task_week_monday, R.id.dialog_task_week_tuesday, R.id.dialog_task_week_wednesday, R.id.dialog_task_week_thursday, R.id.dialog_task_week_friday, R.id.dialog_task_week_saturday};
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < 7; i++) {
                arrayList.add((ImageView) view.findViewById(iArr[i]));
            }
            int size = this.bm.size();
            ArrayList<Integer> arrayList2 = new ArrayList<>();
            SparseIntArray lM_ = azi.lM_();
            for (int i2 = 0; i2 < size; i2++) {
                int keyAt = this.bm.keyAt(i2);
                int i3 = this.bm.get(keyAt);
                if (i3 == 1) {
                    int h = CommonUtils.h(this.bg);
                    if (this.aj && h > 0 && CommonUtils.h(this.az) >= h) {
                        li_(arrayList2, lM_, keyAt);
                    } else {
                        arrayList2.add(Integer.valueOf(R.drawable._2131430641_res_0x7f0b0cf1));
                    }
                } else if (i3 == 200) {
                    if (this.aj) {
                        li_(arrayList2, lM_, keyAt);
                    } else {
                        arrayList2.add(Integer.valueOf(R.drawable._2131430636_res_0x7f0b0cec));
                    }
                } else {
                    arrayList2.add(Integer.valueOf(R.drawable._2131430644_res_0x7f0b0cf4));
                }
            }
            int v = azi.v();
            for (int i4 = 0; i4 < arrayList.size(); i4++) {
                ImageView imageView = (ImageView) arrayList.get(i4);
                if (imageView != null) {
                    int intValue = arrayList2.get(i4).intValue();
                    if (v - 1 == i4) {
                        imageView.setImageResource(intValue);
                    } else {
                        imageView.setImageBitmap(azi.lH_(intValue, R$color.common_white_60alpha));
                    }
                }
            }
        }

        private void li_(ArrayList<Integer> arrayList, SparseIntArray sparseIntArray, int i) {
            Integer valueOf = Integer.valueOf(R.drawable._2131430636_res_0x7f0b0cec);
            if (sparseIntArray == null || sparseIntArray.size() <= 0) {
                arrayList.add(valueOf);
                return;
            }
            int i2 = sparseIntArray.get(i);
            if (i2 == 300) {
                arrayList.add(Integer.valueOf(R.drawable._2131430642_res_0x7f0b0cf2));
            } else if (i2 == 301) {
                arrayList.add(Integer.valueOf(R.drawable._2131430637_res_0x7f0b0ced));
            } else {
                arrayList.add(valueOf);
            }
        }

        private void j() {
            this.p.setVisibility(0);
            View inflate = View.inflate(this.r, R.layout.dialog_task_grid, null);
            this.ad = (HealthGridView) inflate.findViewById(R.id.dialog_task_grid);
            this.p.addView(inflate);
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent == null || motionEvent.getAction() != 0) {
                return false;
            }
            int id = view.getId();
            if (id == R.id.dialog_base_task_positive) {
                d(this.aw);
                return false;
            }
            if (id == R.id.dialog_base_task_neutral) {
                d(this.av);
                return false;
            }
            if (id == R.id.dialog_base_task_negative) {
                d(this.ar);
                return false;
            }
            if (id == R.id.dialog_base_task_detail) {
                d(this.q);
                return false;
            }
            if (id == R.id.dialog_base_task_know) {
                d(this.aq);
                return false;
            }
            LogUtil.h("HealthLife_HealthLifeTaskDialog", "onTouch id ", Integer.valueOf(id));
            return false;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (view == null) {
                LogUtil.h("HealthLife_HealthLifeTaskDialog", "onClick view is null");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            int id = view.getId();
            if (id == R.id.dialog_base_task_about) {
                aza.d(this.ag);
                bcl.c(this.f, this.e);
            } else if (id == R.id.dialog_base_task_positive) {
                aza.b(this.ag, String.valueOf(this.aw.getText()));
                bcl.b(this.f, this.e);
                if (this.ae) {
                    aa();
                }
            } else if (id == R.id.dialog_base_task_neutral) {
                aza.b(this.ag, String.valueOf(this.av.getText()));
                a(AnalyticsValue.HEALTH_HEALTH_BLOODPRESS_DETAIL_RECORD_2030020);
                bcl.e(this.f, this.e, this.y);
            } else if (id == R.id.dialog_base_task_negative) {
                aza.b(this.ag, String.valueOf(this.ar.getText()));
                a(AnalyticsValue.HEALTH_HOME_BLOOD_PRESSURE_DETAIL_2010025);
                bcl.b(this.bd, this.e, this.o);
            } else if (id == R.id.dialog_base_task_detail) {
                aza.b(this.ag, String.valueOf(this.q.getText()));
                bcl.e(this.f, this.e);
            } else if (id == R.id.dialog_base_task_know) {
                aza.b(this.ag, String.valueOf(this.aq.getText()));
            } else {
                LogUtil.h("HealthLife_HealthLifeTaskDialog", "onClick id ", Integer.valueOf(id));
            }
            HealthLifeTaskDialog healthLifeTaskDialog = this.s;
            if (healthLifeTaskDialog != null) {
                healthLifeTaskDialog.dismiss();
            }
            ViewClickInstrumentation.clickOnView(view);
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (this.ae && koq.d(this.i, this.ac)) {
                this.ac = i;
                e(this.i.get(i));
                ad();
            } else {
                LogUtil.h("HealthLife_HealthLifeTaskDialog", "onItemClick other!");
            }
            ViewClickInstrumentation.clickOnListView(adapterView, view, i);
        }

        private void v() {
            ResponseCallback<HealthLifeTaskBean> responseCallback = this.o;
            if (responseCallback != null) {
                responseCallback.onResponse(0, this.bd);
            }
        }

        private void aa() {
            a(AnalyticsValue.HEALTH_HEALTH_BLOODPRESS_DETAIL_MEASURE_2030023);
            if (f()) {
                nrh.b(this.e, R$string.IDS_blood_pressure_not_range);
                return;
            }
            DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "HealthLife_HealthLifeTaskDialog");
            if (deviceInfo == null) {
                LogUtil.h("HealthLife_HealthLifeTaskDialog", "setPositiveEventForBloodPressure no device");
                q();
            } else {
                if (cwi.c(deviceInfo, 59)) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(ArkUIXConstants.FROM_TYPE, 11);
                    AppRouter.b("/Main/BloodMeasureHintActivity").b(268435456).zF_(bundle).c(this.e);
                    a(2);
                    return;
                }
                q();
            }
        }

        private void a(int i) {
            aza.d(AnalyticsValue.BLOOD_PRESSURE_DETAIL_MEASURE_2040205, "/todoTask/TodoListActivity".equals(this.y) ? 3 : 2, i);
        }

        private void q() {
            this.an = true;
            azi.d(ThreadPoolManager.d(), new Runnable() { // from class: ayn
                @Override // java.lang.Runnable
                public final void run() {
                    HealthLifeTaskDialog.Builder.this.e();
                }
            });
        }

        public /* synthetic */ void e() {
            final int h = h();
            Context context = this.e;
            if (context instanceof Activity) {
                ((Activity) context).runOnUiThread(new Runnable() { // from class: ayr
                    @Override // java.lang.Runnable
                    public final void run() {
                        HealthLifeTaskDialog.Builder.this.e(h);
                    }
                });
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:21:0x003a, code lost:
        
            if (r3 != null) goto L27;
         */
        /* JADX WARN: Code restructure failed: missing block: B:22:0x0059, code lost:
        
            return r4;
         */
        /* JADX WARN: Code restructure failed: missing block: B:23:0x0056, code lost:
        
            r3.close();
         */
        /* JADX WARN: Code restructure failed: missing block: B:29:0x0054, code lost:
        
            if (r3 != null) goto L27;
         */
        /* JADX WARN: Removed duplicated region for block: B:34:0x005d  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0062  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private int h() {
            /*
                r13 = this;
                r0 = 1
                r1 = 0
                r2 = 0
                android.content.Context r3 = r13.e     // Catch: java.lang.Throwable -> L3f android.database.SQLException -> L42
                java.lang.String r4 = "device.db"
                android.database.sqlite.SQLiteDatabase r3 = r3.openOrCreateDatabase(r4, r1, r2)     // Catch: java.lang.Throwable -> L3f android.database.SQLException -> L42
                java.lang.String[] r7 = new java.lang.String[r0]     // Catch: android.database.SQLException -> L3d java.lang.Throwable -> L5a
                java.lang.String r4 = "productId"
                r7[r1] = r4     // Catch: android.database.SQLException -> L3d java.lang.Throwable -> L5a
                java.lang.String r6 = "device"
                java.lang.String r8 = "kind = 'HDK_BLOOD_PRESSURE'"
                r9 = 0
                r10 = 0
                r11 = 0
                r12 = 0
                r5 = r3
                android.database.Cursor r2 = r5.query(r6, r7, r8, r9, r10, r11, r12)     // Catch: android.database.SQLException -> L3d java.lang.Throwable -> L5a
                if (r2 != 0) goto L2b
                if (r2 == 0) goto L25
                r2.close()
            L25:
                if (r3 == 0) goto L2a
                r3.close()
            L2a:
                return r1
            L2b:
                r4 = r1
            L2c:
                boolean r5 = r2.moveToNext()     // Catch: android.database.SQLException -> L44 java.lang.Throwable -> L5a
                if (r5 == 0) goto L35
                int r4 = r4 + 1
                goto L2c
            L35:
                if (r2 == 0) goto L3a
                r2.close()
            L3a:
                if (r3 == 0) goto L59
                goto L56
            L3d:
                r4 = r1
                goto L44
            L3f:
                r0 = move-exception
                r3 = r2
                goto L5b
            L42:
                r4 = r1
                r3 = r2
            L44:
                java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> L5a
                java.lang.String r5 = "getBondedProducts SQLException"
                r0[r1] = r5     // Catch: java.lang.Throwable -> L5a
                java.lang.String r1 = "HealthLife_HealthLifeTaskDialog"
                com.huawei.hwlogsmodel.LogUtil.b(r1, r0)     // Catch: java.lang.Throwable -> L5a
                if (r2 == 0) goto L54
                r2.close()
            L54:
                if (r3 == 0) goto L59
            L56:
                r3.close()
            L59:
                return r4
            L5a:
                r0 = move-exception
            L5b:
                if (r2 == 0) goto L60
                r2.close()
            L60:
                if (r3 == 0) goto L65
                r3.close()
            L65:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.huawei.basichealthmodel.ui.dialog.HealthLifeTaskDialog.Builder.h():int");
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: g, reason: merged with bridge method [inline-methods] */
        public void e(int i) {
            if (i > 0) {
                e("HDK_BLOOD_PRESSURE", 0);
            } else {
                j(this.ag);
            }
        }

        private void e(String str, int i) {
            Bundle bundle = new Bundle();
            bundle.putString("kind", str);
            bundle.putString("view", "MeasureDevice");
            if (i != 0) {
                bundle.putInt("type", i);
            }
            bundle.putInt(CommonUtil.PARAM_HEALTH_TYPE, 6);
            AppRouter.b("/PluginDevice/DeviceMainActivity").zF_(bundle).c(this.e);
            v();
            a(1);
        }

        private void j(final int i) {
            NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.e);
            builder.e(this.ba.getString(R$string.IDS_health_model_dialog_bind)).czE_(this.ba.getString(com.huawei.ui.commonui.R$string.IDS_yes), new View.OnClickListener() { // from class: ayk
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HealthLifeTaskDialog.Builder.this.lk_(i, view);
                }
            }).czA_(this.ba.getString(com.huawei.ui.commonui.R$string.IDS_dialog_checkbox_cancel), new View.OnClickListener() { // from class: ayl
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            if (this.n == null) {
                this.n = builder.e();
            }
            if (!this.n.isShowing()) {
                this.n.show();
            }
            this.n.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: ayo
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    HealthLifeTaskDialog.Builder.this.ll_(dialogInterface);
                }
            });
        }

        public /* synthetic */ void lk_(int i, View view) {
            String str;
            Bundle bundle = new Bundle();
            if (i == 12) {
                str = "HDK_BLOOD_PRESSURE";
            } else if (i != 14) {
                LogUtil.h("HealthLife_HealthLifeTaskDialog", "error id = ", Integer.valueOf(i));
                str = null;
            } else {
                str = "HDK_WEIGHT";
            }
            bundle.putString("kind_id", str);
            AppRouter.b("/Device/AllDeviceListActivity").zF_(bundle).c(this.e);
            ViewClickInstrumentation.clickOnView(view);
        }

        public /* synthetic */ void ll_(DialogInterface dialogInterface) {
            v();
        }

        private void a(AnalyticsValue analyticsValue) {
            if (this.ae) {
                aza.a(analyticsValue, "/todoTask/TodoListActivity".equals(this.y) ? 3 : 2);
            }
        }

        private boolean f() {
            BloodPressurePlanResultBean bloodPressurePlanResultBean;
            if (!this.ae) {
                return false;
            }
            int size = this.i.size();
            int i = this.ac;
            if (size <= i || (bloodPressurePlanResultBean = this.i.get(i)) == null) {
                return false;
            }
            long e = azi.e(bloodPressurePlanResultBean.getHour(), bloodPressurePlanResultBean.getMinute());
            long currentTimeMillis = System.currentTimeMillis();
            LogUtil.a("HealthLife_HealthLifeTaskDialog", "handleBloodPressureToast timestamp ", Long.valueOf(currentTimeMillis), ", hourAndMinute ", Long.valueOf(e));
            return currentTimeMillis < e - 1800000 || currentTimeMillis > e + 1800000;
        }

        public void d(int i) {
            int max = Math.max(i, 0);
            this.k = max;
            HealthTextView healthTextView = this.bf;
            if (healthTextView == null) {
                LogUtil.h("HealthLife_HealthLifeTaskDialog", "setSubtitle mSubtitle is null");
                return;
            }
            if (max <= 0) {
                healthTextView.setVisibility(8);
                return;
            }
            healthTextView.setVisibility(0);
            HealthTextView healthTextView2 = this.bf;
            Resources resources = this.ba;
            int i2 = R$string.IDS_health_model_target_over_number;
            Resources resources2 = this.ba;
            int i3 = R$plurals.IDS_health_model_target_over_all_number;
            int i4 = this.k;
            healthTextView2.setText(resources.getString(i2, resources2.getQuantityString(i3, i4, Integer.valueOf(i4))));
        }

        public void c(int i) {
            HealthTextView healthTextView = this.m;
            if (healthTextView != null) {
                healthTextView.setVisibility(8);
            }
            HealthTextView healthTextView2 = this.bf;
            if (healthTextView2 == null) {
                LogUtil.h("HealthLife_HealthLifeTaskDialog", "setRank mSubtitle is null");
                return;
            }
            if (i <= 0) {
                LogUtil.h("HealthLife_HealthLifeTaskDialog", "setRank rank ", Integer.valueOf(i));
                return;
            }
            this.at = i;
            if (this.k <= 0) {
                healthTextView2.setVisibility(8);
                return;
            }
            healthTextView2.setVisibility(0);
            HealthTextView healthTextView3 = this.bf;
            Resources resources = this.ba;
            int i2 = R$string.IDS_dialog_subtitle;
            Resources resources2 = this.ba;
            int i3 = R$plurals.IDS_health_model_target_over_all_number;
            int i4 = this.k;
            healthTextView3.setText(resources.getString(i2, resources2.getQuantityString(i3, i4, Integer.valueOf(i4)), azi.c(i, 2, 0)));
        }

        private void ab() {
            View inflate = View.inflate(this.r, R.layout.dialog_task_time, null);
            HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.dialog_task_time_plan_content);
            this.au = healthTextView;
            azi.e(healthTextView, R.dimen._2131362869_res_0x7f0a0435, 1.2f);
            HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.dialog_task_time_punch_content);
            this.ax = healthTextView2;
            azi.e(healthTextView2, R.dimen._2131362869_res_0x7f0a0435, 1.2f);
            if (this.ae) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
                layoutParams.topMargin = this.ba.getDimensionPixelSize(R.dimen._2131362566_res_0x7f0a0306);
                layoutParams.bottomMargin = this.ba.getDimensionPixelSize(R.dimen._2131362566_res_0x7f0a0306);
                inflate.setLayoutParams(layoutParams);
            }
            this.p.addView(inflate);
        }

        private void ad() {
            View childAt;
            for (int i = 0; i < this.v; i++) {
                View childAt2 = this.ad.getChildAt(i);
                if (childAt2 instanceof LinearLayout) {
                    LinearLayout linearLayout = (LinearLayout) childAt2;
                    if (linearLayout.getChildCount() != 0 && (childAt = linearLayout.getChildAt(0)) != null) {
                        if (i == this.ac) {
                            if (this.ae) {
                                childAt.setBackground(ContextCompat.getDrawable(this.r, this.ah ? R.drawable._2131428436_res_0x7f0b0454 : R.drawable._2131428422_res_0x7f0b0446));
                            } else {
                                LogUtil.h("HealthLife_HealthLifeTaskDialog", "setGridSelectionForMedicine other");
                            }
                        } else {
                            childAt.setBackground(null);
                        }
                    }
                }
            }
        }

        public void c() {
            String extendInfo = this.f.getExtendInfo();
            if (TextUtils.isEmpty(extendInfo)) {
                LogUtil.h("HealthLife_HealthLifeTaskDialog", "initGridForBloodPressure extendInfo is empty");
                return;
            }
            List<BloodPressurePlanResultBean> list = (List) HiJsonUtil.b(extendInfo, new TypeToken<List<BloodPressurePlanResultBean>>() { // from class: com.huawei.basichealthmodel.ui.dialog.HealthLifeTaskDialog.Builder.5
            }.getType());
            this.i = list;
            if (koq.b(list)) {
                LogUtil.h("HealthLife_HealthLifeTaskDialog", "initGridForBloodPressure mBloodPressureList is empty");
                return;
            }
            j();
            n();
            TaskDialogGridAdapter taskDialogGridAdapter = new TaskDialogGridAdapter(this.aa, this.bl, false);
            taskDialogGridAdapter.b(true);
            this.ad.setAdapter((ListAdapter) taskDialogGridAdapter);
            this.ad.setNumColumns(5);
            this.ad.setEnabled(true);
            int dimensionPixelSize = this.ba.getDimensionPixelSize(R.dimen._2131363003_res_0x7f0a04bb);
            int dimensionPixelSize2 = this.ba.getDimensionPixelSize(R.dimen._2131362886_res_0x7f0a0446);
            int dimensionPixelSize3 = this.ba.getDimensionPixelSize(R.dimen._2131363094_res_0x7f0a0516);
            this.ad.setVerticalSpacing(dimensionPixelSize);
            this.ad.setPadding(dimensionPixelSize2, dimensionPixelSize3, dimensionPixelSize2, dimensionPixelSize3);
            this.ad.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.basichealthmodel.ui.dialog.HealthLifeTaskDialog.Builder.1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    Builder.this.ad.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    Builder.this.k();
                }
            });
            this.ad.setOnItemClickListener(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void k() {
            this.v = this.ad.getCount();
            ab();
            View inflate = View.inflate(this.r, R.layout.item_task_dialog_blood_pressure, null);
            this.g = inflate;
            this.h = (ImageView) inflate.findViewById(R.id.item_blood_pressure_result_status);
            this.j = (HealthTextView) this.g.findViewById(R.id.item_blood_pressure_result_value);
            this.p.addView(this.g);
            e(this.i.get(this.ac));
            ad();
        }

        private void e(BloodPressurePlanResultBean bloodPressurePlanResultBean) {
            if (bloodPressurePlanResultBean == null) {
                LogUtil.h("HealthLife_HealthLifeTaskDialog", "refreshTimeForBloodPressure alarmInfo is null");
                return;
            }
            LogUtil.c("HealthLife_HealthLifeTaskDialog", "refreshTimeForBloodPressure: ", bloodPressurePlanResultBean.toString());
            this.au.setText(this.ba.getString(R$string.IDS_card_plan, nsj.c(this.r, azi.e(bloodPressurePlanResultBean.getHour(), bloodPressurePlanResultBean.getMinute()), 1)));
            long pressureTime = bloodPressurePlanResultBean.getPressureTime();
            if (pressureTime > 0) {
                this.ah = true;
                this.ax.setVisibility(0);
                this.ax.setText(this.ba.getString(R$string.IDS_card_clock, nsj.c(this.r, pressureTime, 1)));
            } else {
                this.ah = false;
                this.ax.setVisibility(8);
            }
            double bloodPressureDiastolic = bloodPressurePlanResultBean.getBloodPressureDiastolic();
            double bloodPressureSystolic = bloodPressurePlanResultBean.getBloodPressureSystolic();
            if (bloodPressureSystolic < 1.0d && bloodPressureDiastolic < 1.0d) {
                LogUtil.c("HealthLife_HealthLifeTaskDialog", "refreshTimeForBloodPressure pressure result fail");
                this.g.setVisibility(4);
                return;
            }
            this.j.setText(this.ba.getQuantityString(R$plurals.IDS_blood_pressure_result, UnitUtil.e(bloodPressureDiastolic), azi.c((int) bloodPressureSystolic, 1, 0), azi.c((int) bloodPressureDiastolic, 1, 0)));
            if (bloodPressureSystolic <= 139.0d && bloodPressureSystolic > 90.0d && bloodPressureDiastolic > 60.0d && bloodPressureDiastolic <= 89.0d) {
                this.h.setImageResource(R.drawable._2131428421_res_0x7f0b0445);
            } else {
                this.h.setImageResource(R.drawable._2131428420_res_0x7f0b0444);
            }
            this.g.setVisibility(0);
        }

        /* JADX WARN: Removed duplicated region for block: B:20:0x00c6 A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:25:0x00ca A[ADDED_TO_REGION, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private void n() {
            /*
                r13 = this;
                java.util.List<com.huawei.health.healthmodel.bean.BloodPressurePlanResultBean> r0 = r13.i
                int r0 = r0.size()
                long r1 = java.lang.System.currentTimeMillis()
                int r1 = defpackage.azi.c(r1)
                r2 = 0
                r3 = r2
            L10:
                r4 = 1
                if (r2 >= r0) goto Lce
                java.util.List<com.huawei.health.healthmodel.bean.BloodPressurePlanResultBean> r5 = r13.i
                java.lang.Object r5 = r5.get(r2)
                com.huawei.health.healthmodel.bean.BloodPressurePlanResultBean r5 = (com.huawei.health.healthmodel.bean.BloodPressurePlanResultBean) r5
                if (r5 != 0) goto L1f
                goto Lca
            L1f:
                long r6 = r5.getPressureTime()
                r8 = 0
                int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
                if (r6 <= 0) goto L4d
                if (r2 == 0) goto L3e
                int r6 = r0 + (-1)
                if (r2 != r6) goto L30
                goto L3e
            L30:
                java.util.ArrayList<android.graphics.drawable.Drawable> r6 = r13.aa
                android.content.Context r7 = r13.r
                int r10 = com.huawei.ui.commonui.R$drawable.health_life_icon_task_blood_pressure
                android.graphics.drawable.Drawable r7 = androidx.core.content.ContextCompat.getDrawable(r7, r10)
                r6.add(r7)
                goto L71
            L3e:
                java.util.ArrayList<android.graphics.drawable.Drawable> r6 = r13.aa
                android.content.Context r7 = r13.r
                r10 = 2131430647(0x7f0b0cf7, float:1.8483E38)
                android.graphics.drawable.Drawable r7 = androidx.core.content.ContextCompat.getDrawable(r7, r10)
                r6.add(r7)
                goto L71
            L4d:
                if (r2 == 0) goto L63
                int r6 = r0 + (-1)
                if (r2 != r6) goto L54
                goto L63
            L54:
                java.util.ArrayList<android.graphics.drawable.Drawable> r6 = r13.aa
                android.content.Context r7 = r13.r
                r10 = 2131430649(0x7f0b0cf9, float:1.8483005E38)
                android.graphics.drawable.Drawable r7 = androidx.core.content.ContextCompat.getDrawable(r7, r10)
                r6.add(r7)
                goto L71
            L63:
                java.util.ArrayList<android.graphics.drawable.Drawable> r6 = r13.aa
                android.content.Context r7 = r13.r
                r10 = 2131430648(0x7f0b0cf8, float:1.8483003E38)
                android.graphics.drawable.Drawable r7 = androidx.core.content.ContextCompat.getDrawable(r7, r10)
                r6.add(r7)
            L71:
                int r6 = r5.getHour()
                int r6 = r6 * 3600
                int r7 = r5.getMinute()
                int r7 = r7 * 60
                int r6 = r6 + r7
                java.lang.Integer r7 = java.lang.Integer.valueOf(r6)
                java.lang.String r10 = ", seconds "
                java.lang.Integer r11 = java.lang.Integer.valueOf(r1)
                java.lang.String r12 = "initGridDrawableForBloodPressure planSeconds "
                java.lang.Object[] r7 = new java.lang.Object[]{r12, r7, r10, r11}
                java.lang.String r10 = "HealthLife_HealthLifeTaskDialog"
                com.huawei.hwlogsmodel.LogUtil.a(r10, r7)
                int r7 = r6 - r1
                int r7 = java.lang.Math.abs(r7)
                r11 = 1800(0x708, float:2.522E-42)
                if (r7 >= r11) goto Laf
                if (r3 != 0) goto Laf
                long r5 = r5.getPressureTime()
                int r5 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
                if (r5 <= 0) goto Lac
                int r4 = r2 + 1
                r13.ac = r4
                goto Lc2
            Lac:
                r13.ac = r2
                goto Lb5
            Laf:
                if (r6 <= r1) goto Lb7
                if (r3 != 0) goto Lb7
                r13.ac = r2
            Lb5:
                r3 = r4
                goto Lc2
            Lb7:
                java.lang.Integer r4 = java.lang.Integer.valueOf(r6)
                java.lang.Object[] r4 = new java.lang.Object[]{r12, r4}
                com.huawei.hwlogsmodel.LogUtil.h(r10, r4)
            Lc2:
                int r4 = r0 + (-1)
                if (r2 != r4) goto Lca
                if (r3 != 0) goto Lca
                r13.ac = r2
            Lca:
                int r2 = r2 + 1
                goto L10
            Lce:
                int r1 = r13.ac
                if (r1 < r0) goto Ld5
                int r0 = r0 - r4
                r13.ac = r0
            Ld5:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.huawei.basichealthmodel.ui.dialog.HealthLifeTaskDialog.Builder.n():void");
        }

        @Override // com.huawei.hwbasemgr.ResponseCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, atz atzVar) {
            if (atzVar == null) {
                LogUtil.h("HealthLife_HealthLifeTaskDialog", "onResponse taskDialogEvent is null");
                return;
            }
            int e = atzVar.e();
            if (e == 1) {
                bcl.a(this.e);
            } else if (e == 2) {
                bcl.d(this.e);
            } else if (e == 3) {
                bcl.c(this.e);
            } else {
                LogUtil.h("HealthLife_HealthLifeTaskDialog", "onResponse type ", Integer.valueOf(e));
            }
            HealthLifeTaskDialog healthLifeTaskDialog = this.s;
            if (healthLifeTaskDialog != null) {
                healthLifeTaskDialog.dismiss();
            }
        }
    }
}
