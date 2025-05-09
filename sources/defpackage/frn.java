package defpackage;

import android.graphics.drawable.AnimationDrawable;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.health.basesport.helper.HeartRateConfigHelper;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import health.compact.a.UnitUtil;

/* loaded from: classes4.dex */
public class frn {

    /* renamed from: a, reason: collision with root package name */
    private LinearLayout f12622a;
    private LinearLayout ac;
    private int ad = 10001;
    private ImageView b;
    private HealthTextView c;
    private HealthProgressBar d;
    private AnimationDrawable e;
    private boolean f;
    private HeartRateConfigHelper g;
    private LinearLayout h;
    private e i;
    private HealthTextView j;
    private ImageView k;
    private HealthTextView l;
    private int m;
    private ImageView n;
    private ImageView o;
    private ImageView p;
    private LinearLayout q;
    private ImageView r;
    private int s;
    private ImageView t;
    private HealthTextView u;
    private LinearLayout v;
    private View w;
    private HealthTextView x;
    private RelativeLayout y;
    private HealthTextView z;

    static class e extends BaseHandler<frn> {
        e(Looper looper, frn frnVar) {
            super(looper, frnVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: aEZ_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(frn frnVar, Message message) {
            LogUtil.a("Suggestion_CalorieHeartViewHolder", "handleMessage msg.what = ", Integer.valueOf(message.what));
            if (message.what == 10001) {
                frnVar.g();
                frnVar.i();
            }
        }
    }

    public frn() {
        b();
    }

    private void b() {
        View inflate = LayoutInflater.from(arx.b()).inflate(R.layout.layout_heartrate_and_calories, (ViewGroup) null);
        this.w = inflate;
        this.h = (LinearLayout) inflate.findViewById(R.id.ll_calorie_heart_container);
        this.q = (LinearLayout) this.w.findViewById(R.id.rate_container);
        this.f12622a = (LinearLayout) this.w.findViewById(R.id.ll_calorie_container);
        this.v = (LinearLayout) this.w.findViewById(R.id.ll_rope_skipnum_container);
        this.ac = (LinearLayout) this.w.findViewById(R.id.ll_rope_speed_container);
        this.j = (HealthTextView) this.w.findViewById(R.id.heart_rate);
        HealthTextView healthTextView = (HealthTextView) this.w.findViewById(R.id.calorie);
        this.c = healthTextView;
        healthTextView.setText(UnitUtil.e(0.0d, 1, 0));
        this.l = (HealthTextView) this.w.findViewById(R.id.heart_rate_stage);
        this.x = (HealthTextView) this.w.findViewById(R.id.rope_skipnum);
        this.u = (HealthTextView) this.w.findViewById(R.id.rope_speed);
        HealthTextView healthTextView2 = (HealthTextView) this.w.findViewById(R.id.rope_skipnum_uint);
        this.z = healthTextView2;
        healthTextView2.setText(arx.b().getResources().getQuantityString(R.plurals._2130903274_res_0x7f0300ea, 0, "").trim());
        this.n = (ImageView) this.w.findViewById(R.id.mark);
        this.o = (ImageView) this.w.findViewById(R.id.point1);
        this.k = (ImageView) this.w.findViewById(R.id.point2);
        this.t = (ImageView) this.w.findViewById(R.id.point3);
        this.r = (ImageView) this.w.findViewById(R.id.point4);
        this.p = (ImageView) this.w.findViewById(R.id.point5);
        this.y = (RelativeLayout) this.w.findViewById(R.id.rl_corser);
        this.d = (HealthProgressBar) this.w.findViewById(R.id.calorie_progressbar);
        this.b = (ImageView) this.w.findViewById(R.id.fitness_fire);
        this.s = nsn.c(BaseApplication.getContext(), 96.0f);
        this.i = new e(Looper.getMainLooper(), this);
    }

    public void a(int i) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(new ViewGroup.MarginLayoutParams(-2, -2));
        layoutParams.addRule(21);
        layoutParams.addRule(15);
        layoutParams.setMarginEnd(i + nsn.c(BaseApplication.getContext(), BaseApplication.getContext().getResources().getDimension(R.dimen._2131362365_res_0x7f0a023d)));
        this.w.setLayoutParams(layoutParams);
    }

    public void e() {
        if (!this.f) {
            this.b.setImageResource(R.drawable._2131428252_res_0x7f0b039c);
            if (this.b.getDrawable() instanceof AnimationDrawable) {
                this.e = (AnimationDrawable) this.b.getDrawable();
            }
        }
        AnimationDrawable animationDrawable = this.e;
        if (animationDrawable == null) {
            LogUtil.h("Suggestion_CalorieHeartViewHolder", "startCalorieFiresAnim mCalorieFireAnim == null");
            return;
        }
        if (animationDrawable.isRunning()) {
            LogUtil.h("Suggestion_CalorieHeartViewHolder", "startCalorieFiresAnim mCalorieFireAnim isRunning");
            return;
        }
        this.e.start();
        this.i.sendEmptyMessageDelayed(10001, this.e.getNumberOfFrames() * this.e.getDuration(0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        this.e.stop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        this.b.setImageResource(R.drawable._2131428251_res_0x7f0b039b);
        if (this.b.getDrawable() instanceof AnimationDrawable) {
            AnimationDrawable animationDrawable = (AnimationDrawable) this.b.getDrawable();
            this.e = animationDrawable;
            animationDrawable.setOneShot(false);
            this.e.start();
        }
    }

    private void i(int i) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        if (this.d.getMax() == 0) {
            LogUtil.b("Suggestion_CalorieHeartViewHolder", "mCalorieProgressBar.getMax() == 0");
        } else {
            layoutParams.setMarginStart((int) ((i / (this.d.getMax() * 1.0f)) * this.s));
            this.b.setLayoutParams(layoutParams);
        }
    }

    public View aEY_() {
        return this.w;
    }

    public void b(float f, float f2) {
        this.f12622a.setVisibility(0);
        this.d.setVisibility(0);
        gdn.aLx_(this.f12622a, f, f2, 300);
    }

    public void d() {
        this.f12622a.setVisibility(8);
        this.d.setVisibility(8);
    }

    public void j(int i) {
        if (i != this.m) {
            this.d.setProgress(i, true);
            f(i);
            i(i);
        }
        this.m = i;
    }

    private void f(int i) {
        if (i / (this.d.getMax() * 1.0f) > 0.15f) {
            e();
            this.f = true;
        }
    }

    public void d(int i) {
        this.d.setMax(i);
    }

    public void e(double d) {
        this.c.setText(UnitUtil.e(d, 1, 0));
    }

    public void c(int i) {
        this.ad = i;
    }

    public void h(final int i) {
        if (i > 0 && i < 220) {
            g(0);
            if (this.q.getVisibility() != 0) {
                this.q.setVisibility(0);
            }
            if (this.y.getVisibility() != 0) {
                this.y.setVisibility(0);
            }
            if (this.l.getVisibility() != 0) {
                this.l.setVisibility(0);
            }
            HeartRateConfigHelper heartRateConfigHelper = this.g;
            if (heartRateConfigHelper == null) {
                this.g = new HeartRateConfigHelper(this.ad, new HeartRateConfigHelper.OnConfigHelperListener() { // from class: frl
                    @Override // com.huawei.health.basesport.helper.HeartRateConfigHelper.OnConfigHelperListener
                    public final void onInitComplete() {
                        frn.this.b(i);
                    }
                });
            } else {
                this.l.setText(heartRateConfigHelper.d(i));
                l(this.g.a(i));
            }
            this.j.setText(UnitUtil.e(i, 1, 0));
            return;
        }
        this.j.setText(R.string._2130851561_res_0x7f0236e9);
        this.l.setText("");
        c();
    }

    /* synthetic */ void b(final int i) {
        this.l.post(new Runnable() { // from class: fro
            @Override // java.lang.Runnable
            public final void run() {
                frn.this.e(i);
            }
        });
    }

    /* synthetic */ void e(int i) {
        this.l.setText(this.g.d(i));
        l(this.g.a(i));
    }

    public void e(int i, int i2) {
        if (this.v.getVisibility() != 0) {
            this.v.setVisibility(0);
        }
        if (this.ac.getVisibility() != 0) {
            this.ac.setVisibility(0);
        }
        this.x.setText(UnitUtil.e(i, 1, 0));
        this.u.setText(UnitUtil.e(i2, 1, 0));
    }

    private void c() {
        this.o.setVisibility(4);
        this.k.setVisibility(4);
        this.t.setVisibility(4);
        this.r.setVisibility(4);
        this.p.setVisibility(4);
        this.n.setImageResource(R.drawable._2131428256_res_0x7f0b03a0);
    }

    private void l(int i) {
        c();
        if (i == 0) {
            this.n.setImageResource(R.drawable._2131428257_res_0x7f0b03a1);
            this.o.setVisibility(0);
            return;
        }
        if (i == 1) {
            this.n.setImageResource(R.drawable._2131428258_res_0x7f0b03a2);
            this.k.setVisibility(0);
            return;
        }
        if (i == 2) {
            this.n.setImageResource(R.drawable._2131428259_res_0x7f0b03a3);
            this.t.setVisibility(0);
        } else if (i == 3) {
            this.n.setImageResource(R.drawable._2131428260_res_0x7f0b03a4);
            this.r.setVisibility(0);
        } else if (i == 4) {
            this.n.setImageResource(R.drawable._2131428261_res_0x7f0b03a5);
            this.p.setVisibility(0);
        } else {
            LogUtil.b("Suggestion_CalorieHeartViewHolder", "setCorserIndex unknow index");
        }
    }

    public void g(int i) {
        this.h.setVisibility(i);
    }
}
