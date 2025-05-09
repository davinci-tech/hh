package defpackage;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.huawei.featureuserprofile.todo.activity.TodoListActivity;
import com.huawei.featureuserprofile.todo.datatype.TodoSwitchSync;
import com.huawei.featureuserprofile.todo.utils.TodoAnimatorUtil;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class bva implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private final Activity f519a;
    private ImageView b;
    private long c;
    private View f;
    private View h;
    private View j;
    private volatile int g = 0;
    private d d = new d(this);
    private final Handler e = new e();

    static class e extends BaseHandler<bva> {
        private e(bva bvaVar) {
            super(bvaVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: vr_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(bva bvaVar, Message message) {
            int i = message.what;
            if (i != 1) {
                if (i != 2) {
                    return;
                }
                SharedPreferenceManager.e(bvaVar.f519a, String.valueOf(10006), "SP_KEY_HOME_TODO_ICON_VISIBLE", "false", (StorageParams) null);
                bvaVar.d();
                return;
            }
            int i2 = message.arg1;
            bvaVar.g = i2;
            bvaVar.c(i2, message.arg2);
            bvaVar.d();
        }
    }

    public bva(Activity activity) {
        this.f519a = activity;
    }

    public void a() {
        this.j = this.f519a.getLayoutInflater().inflate(R.layout.home_todo_float_view, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 8388693;
        layoutParams.bottomMargin = this.f519a.getResources().getDimensionPixelSize(R.dimen._2131363084_res_0x7f0a050c);
        layoutParams.rightMargin = this.f519a.getResources().getDimensionPixelSize(R.dimen._2131362906_res_0x7f0a045a);
        ((FrameLayout) this.f519a.getWindow().getDecorView().findViewById(android.R.id.content)).addView(this.j, layoutParams);
        this.j.bringToFront();
        this.f = this.j.findViewById(R.id.todo_float_prompt_layout);
        this.h = this.j.findViewById(R.id.todo_float_icon_layout);
        this.b = (ImageView) this.j.findViewById(R.id.todo_float_icon);
        View findViewById = this.j.findViewById(R.id.todo_float_clickable);
        findViewById.setOnClickListener(this);
        jcf.bEA_(findViewById, nsf.h(R.string._2130842595_res_0x7f0213e3), Button.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        String b = SharedPreferenceManager.b(this.f519a, Integer.toString(10006), "SP_KEY_HOME_TODO_ICON_VISIBLE");
        boolean z = TextUtils.isEmpty(b) || "true".equalsIgnoreCase(b);
        if (z) {
            Handler handler = this.e;
            handler.sendMessageDelayed(Message.obtain(handler, 2), OpAnalyticsConstants.H5_LOADING_DELAY);
        }
        this.f.setVisibility(z ? 0 : 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, int i2) {
        LogUtil.a("TodoFloatingViewInteractor", "updateIcon", Integer.valueOf(i));
        View view = this.j;
        if (view == null) {
            LogUtil.h("TodoFloatingViewInteractor", "updateIcon mRootView is null, return");
            return;
        }
        boolean z = i != 2;
        if (view.getVisibility() == 0 && z) {
            bvw.c(this.f519a, AnalyticsValue.HEALTH_LIFE_TODO_ICON_EXPOSE_2040177, null, i2, 0);
        }
        this.h.setVisibility(z ? 0 : 8);
        if (this.b == null || !z) {
            return;
        }
        int i3 = LanguageUtil.bc(BaseApplication.getContext()) ? R.drawable._2131430525_res_0x7f0b0c7d : R.drawable._2131430524_res_0x7f0b0c7c;
        ImageView imageView = this.b;
        if (i != 0) {
            i3 = R.drawable._2131430522_res_0x7f0b0c7a;
        }
        imageView.setImageResource(i3);
    }

    private void b() {
        LogUtil.a("TodoFloatingViewInteractor", "fetchTodo1:");
        this.c = SystemClock.elapsedRealtime();
        bvp.c().a(this.d);
    }

    public void c(boolean z) {
        LogUtil.a("TodoFloatingViewInteractor", "refreshUi isVisible = ", Boolean.valueOf(z));
        if (this.j == null) {
            LogUtil.h("TodoFloatingViewInteractor", "refreshUi mRootView is null");
            return;
        }
        boolean z2 = bvw.b(this.f519a) && z;
        LogUtil.a("TodoFloatingViewInteractor", "refreshUi isShowFloatView = ", Boolean.valueOf(z2));
        e(z2);
        if (z2) {
            if (this.c == 0) {
                this.d.d(0, bvp.c().a());
            }
            b();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (nsn.cLk_(view)) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view.getId() == R.id.todo_float_clickable) {
            Message.obtain(this.e, 2).sendToTarget();
            c(false);
            Intent intent = new Intent(this.f519a, (Class<?>) TodoListActivity.class);
            intent.putExtra("todo_status", this.g);
            this.f519a.startActivity(intent);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e(boolean z) {
        if (z) {
            if (this.j.getVisibility() == 8) {
                this.j.setVisibility(0);
                d(z);
            } else {
                this.j.setVisibility(0);
            }
        } else {
            this.j.setVisibility(8);
        }
        ntd.b().cMD_(this.j, true);
    }

    private void d(final boolean z) {
        TodoAnimatorUtil.vN_(z, this.b, new Animation.AnimationListener() { // from class: bva.5
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                bva.this.j.setVisibility(z ? 0 : 8);
            }
        });
    }

    static class d implements IBaseResponseCallback {
        private final WeakReference<bva> b;

        d(bva bvaVar) {
            this.b = new WeakReference<>(bvaVar);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("TodoFloatingViewInteractor", "fetchTodo2:");
            bva bvaVar = this.b.get();
            if (bvaVar == null) {
                return;
            }
            int i2 = 0;
            int i3 = 2;
            if (((obj instanceof List) && koq.c((List) obj) && koq.e(obj, gka.class)) != false) {
                TodoSwitchSync d = Looper.myLooper() == Looper.getMainLooper() ? bvw.d(bvaVar.f519a) : bvw.a(bvaVar.f519a);
                List list = (List) obj;
                ArrayList arrayList = new ArrayList();
                if (koq.c(list)) {
                    arrayList.addAll(list);
                }
                bvw.b((List<gka>) arrayList, d, false);
                i2 = arrayList.size();
                if (koq.c(arrayList)) {
                    bvw.b((List<gka>) arrayList, d, true);
                    i3 = !koq.c(arrayList) ? 1 : 0;
                }
            }
            bvaVar.e.obtainMessage(1, i3, i2).sendToTarget();
        }
    }
}
