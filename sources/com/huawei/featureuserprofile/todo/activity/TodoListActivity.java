package com.huawei.featureuserprofile.todo.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.featureuserprofile.todo.activity.TodoListActivity;
import com.huawei.featureuserprofile.todo.adapter.RecyclerViewAdpater;
import com.huawei.featureuserprofile.todo.datatype.TodoSwitchSync;
import com.huawei.featureuserprofile.todo.utils.TodoAnimatorUtil;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.healthmodel.bean.HealthLifeTaskBean;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.main.stories.soical.interactor.OperationDetailActivityStartCallback;
import com.huawei.ui.main.stories.soical.interactor.OperationInteractorsApi;
import defpackage.bvp;
import defpackage.bvw;
import defpackage.bvy;
import defpackage.ceb;
import defpackage.dsl;
import defpackage.eiv;
import defpackage.gka;
import defpackage.ixx;
import defpackage.jcf;
import defpackage.koq;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.GRSManager;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class TodoListActivity extends Activity implements View.OnClickListener, OnItemClickListener<gka> {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f2045a;
    private HealthTextView b;
    private ImageView e;
    private long f;
    private RelativeLayout g;
    private OperationInteractorsApi h;
    private HealthRecycleView k;
    private RecyclerViewAdpater l;
    private View m;
    private View n;
    private ImageView o;
    private HealthButton q;
    private boolean j = true;
    private boolean i = false;
    private a c = new a(this);
    private final Handler d = new e();

    /* loaded from: classes3.dex */
    static class e extends BaseHandler<TodoListActivity> {
        private e(TodoListActivity todoListActivity) {
            super(todoListActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: vx_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(TodoListActivity todoListActivity, Message message) {
            if (message.what == 17) {
                Object obj = message.obj;
                todoListActivity.a(!((obj instanceof List) && koq.c((List) obj) && koq.e(obj, gka.class)), (List<gka>) obj);
            } else {
                if (message.what == 18) {
                    if (bvw.b(todoListActivity)) {
                        todoListActivity.g();
                        return;
                    } else {
                        todoListActivity.e();
                        return;
                    }
                }
                LogUtil.a("TodoListActivity", "other MSG_CODE");
            }
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        vv_(this);
        setContentView(R.layout.activity_todo_list_layout);
        i();
        f();
    }

    private void vv_(Activity activity) {
        if (activity == null) {
            LogUtil.h("TodoListActivity", "setImmersive activity is null");
            return;
        }
        Window window = activity.getWindow();
        if (window == null) {
            LogUtil.h("TodoListActivity", "setImmersive window is null");
            return;
        }
        window.clearFlags(201326592);
        window.getDecorView().setSystemUiVisibility(1280);
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(0);
        window.setNavigationBarColor(getColor(R.color._2131299263_res_0x7f090bbf));
        if (nsn.ag(getApplicationContext()) && nsn.l()) {
            vu_(1, this);
        }
    }

    private static void vu_(int i, Activity activity) {
        if (Build.VERSION.SDK_INT >= 28) {
            WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
            attributes.layoutInDisplayCutoutMode = i;
            activity.getWindow().setAttributes(attributes);
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.j) {
            this.j = false;
            h();
        } else {
            this.d.sendEmptyMessageDelayed(18, 250L);
        }
    }

    @Override // android.app.Activity
    /* renamed from: finish, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public void e() {
        super.finish();
        overridePendingTransition(0, R.anim._2130771984_res_0x7f010010);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Handler handler = this.d;
        if (handler != null) {
            handler.removeMessages(18);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (nsn.cLk_(view)) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        if (id == R.id.todolist_main_layout || id == R.id.todolist_recyclerview || id == R.id.todolist_icon_clickable) {
            if (id == R.id.todolist_icon_clickable) {
                TodoAnimatorUtil.vN_(false, this.e, null);
            }
            if (this.k.getVisibility() == 0) {
                TodoAnimatorUtil.e(this.k, new TodoAnimatorUtil.AnimatorFinishCallback() { // from class: bvb
                    @Override // com.huawei.featureuserprofile.todo.utils.TodoAnimatorUtil.AnimatorFinishCallback
                    public final void dismissAll() {
                        TodoListActivity.this.e();
                    }
                });
            } else {
                e();
            }
        } else if (id == R.id.todolist_to_todo_setting) {
            bvw.c(this, AnalyticsValue.HEALTH_LIFE_TODO_SETTINGS_ENTER_2040174, null, 0, 1);
            startActivity(new Intent(this, (Class<?>) TodoSettingActivity.class));
        } else {
            LogUtil.a("TodoListActivity", "Other view was clicked!");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.featureuserprofile.todo.activity.OnItemClickListener
    /* renamed from: vw_, reason: merged with bridge method [inline-methods] */
    public void onItemClick(View view, int i, gka gkaVar) {
        if (gkaVar == null || nsn.cLk_(view)) {
            return;
        }
        LogUtil.a("TodoListActivity", "onItemClicked, position = ", Integer.valueOf(i), ", toDoModel.getType = ", Integer.valueOf(gkaVar.k()));
        if (gkaVar.k() == 1536) {
            bvw.a(gkaVar);
        } else if (gkaVar.k() == 1792) {
            ceb cebVar = (ceb) gkaVar.o();
            if (cebVar != null) {
                b(cebVar);
            }
        } else if (gkaVar.k() == 512) {
            a(gkaVar);
        } else if (gkaVar.k() == 768) {
            bvw.c(gkaVar.e());
        } else {
            c(gkaVar);
        }
        bvw.c(this, AnalyticsValue.HEALTH_LIFE_TODO_TASK_CLICK_2040172, gkaVar.n(), 0, gkaVar.k());
        if (gkaVar.k() != 512) {
            TodoAnimatorUtil.e(this.k, new TodoAnimatorUtil.AnimatorFinishCallback() { // from class: buy
                @Override // com.huawei.featureuserprofile.todo.utils.TodoAnimatorUtil.AnimatorFinishCallback
                public final void dismissAll() {
                    TodoListActivity.this.b();
                }
            });
        }
    }

    private void c(gka gkaVar) {
        HealthLifeTaskBean healthLifeTaskBean = gkaVar.o() instanceof HealthLifeTaskBean ? (HealthLifeTaskBean) gkaVar.o() : null;
        LogUtil.a("TodoListActivity", "Health life type todoTask ", healthLifeTaskBean);
        dsl.ZK_(this, healthLifeTaskBean != null ? Uri.parse("").buildUpon().appendQueryParameter("id", String.valueOf(healthLifeTaskBean.getId())).appendQueryParameter("from", "/todoTask/TodoListActivity").build() : null);
    }

    private void a(gka gkaVar) {
        HealthLifeTaskBean healthLifeTaskBean = gkaVar.o() instanceof HealthLifeTaskBean ? (HealthLifeTaskBean) gkaVar.o() : null;
        if (healthLifeTaskBean == null) {
            LogUtil.h("TodoListActivity", "onMeasureTaskClick healthLifeTaskBean is null ");
        } else {
            LogUtil.a("TodoListActivity", "onMeasureTaskClick todoTask ", healthLifeTaskBean.getName());
            e(healthLifeTaskBean);
        }
    }

    private void e(HealthLifeTaskBean healthLifeTaskBean) {
        TodoAnimatorUtil.e(this.k, new TodoAnimatorUtil.AnimatorFinishCallback() { // from class: bvf
            @Override // com.huawei.featureuserprofile.todo.utils.TodoAnimatorUtil.AnimatorFinishCallback
            public final void dismissAll() {
                TodoListActivity.this.a();
            }
        });
        dsl.d(this, healthLifeTaskBean, "/todoTask/TodoListActivity", (ResponseCallback<HealthLifeTaskBean>) new ResponseCallback() { // from class: bvc
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                TodoListActivity.this.a(i, (HealthLifeTaskBean) obj);
            }
        });
    }

    public /* synthetic */ void a() {
        RelativeLayout relativeLayout = this.g;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(8);
        }
    }

    public /* synthetic */ void a(int i, HealthLifeTaskBean healthLifeTaskBean) {
        e();
    }

    @Override // com.huawei.featureuserprofile.todo.activity.OnItemClickListener
    public void onOtherClick() {
        TodoAnimatorUtil.e(this.k, new TodoAnimatorUtil.AnimatorFinishCallback() { // from class: bux
            @Override // com.huawei.featureuserprofile.todo.utils.TodoAnimatorUtil.AnimatorFinishCallback
            public final void dismissAll() {
                TodoListActivity.this.d();
            }
        });
    }

    private void b(final ceb cebVar) {
        GRSManager.a(this).e("domainContentcenterDbankcdnNew", new GrsQueryCallback() { // from class: com.huawei.featureuserprofile.todo.activity.TodoListActivity.3
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                LogUtil.c("TodoListActivity", "GRSManager onCallBackSuccess ACTIVITY_KEY url = " + str);
                TodoListActivity.this.h.gotoOperationActivityDetail(TodoListActivity.this, str, cebVar, new OperationDetailActivityStartCallback() { // from class: com.huawei.featureuserprofile.todo.activity.TodoListActivity.3.3
                    @Override // com.huawei.ui.main.stories.soical.interactor.OperationDetailActivityStartCallback
                    public void beforeDetailStart(ceb cebVar2) {
                    }

                    @Override // com.huawei.ui.main.stories.soical.interactor.OperationDetailActivityStartCallback
                    public void afterDetailStart(ceb cebVar2) {
                        bvw.c(AnalyticsValue.HEALTH_HOME_TODO_CARD_DATA_2010084.value(), cebVar2);
                    }
                });
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                LogUtil.c("TodoListActivity", "GRSManager onCallBackFail ACTIVITY_KEY error = " + i);
            }
        });
    }

    private void i() {
        this.g = (RelativeLayout) findViewById(R.id.todolist_main_layout);
        this.k = (HealthRecycleView) findViewById(R.id.todolist_recyclerview);
        ImageView imageView = (ImageView) findViewById(R.id.todolist_icon_close);
        this.e = imageView;
        TodoAnimatorUtil.vN_(true, imageView, null);
        View findViewById = findViewById(R.id.todolist_icon_clickable);
        this.m = findViewById;
        jcf.bEA_(findViewById, nsf.h(R.string._2130850611_res_0x7f023333), Button.class);
        this.q = (HealthButton) findViewById(R.id.todolist_to_todo_setting);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, 1, false);
        linearLayoutManager.setStackFromEnd(true);
        this.k.setLayoutManager(linearLayoutManager);
        this.k.a(false);
        TodoAnimatorUtil.e(this.k);
        RecyclerViewAdpater recyclerViewAdpater = new RecyclerViewAdpater();
        this.l = recyclerViewAdpater;
        this.k.setAdapter(recyclerViewAdpater);
        if (LanguageUtil.bc(this)) {
            this.k.setLayoutDirection(0);
        }
        this.n = findViewById(R.id.todolist_finined_layout);
        this.f2045a = (HealthTextView) findViewById(R.id.todolist_finish_title);
        this.b = (HealthTextView) findViewById(R.id.todolist_finish_content);
        ImageView imageView2 = (ImageView) findViewById(R.id.todolist_done_anim_img);
        this.o = imageView2;
        imageView2.setAnimation(vt_());
    }

    private AnimationSet vt_() {
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setFillAfter(true);
        animationSet.setDuration(1000L);
        animationSet.setInterpolator(new BounceInterpolator());
        animationSet.addAnimation(new ScaleAnimation(0.3f, 1.0f, 0.3f, 1.0f, 1, 0.5f, 1, 0.5f));
        animationSet.addAnimation(new AlphaAnimation(0.0f, 1.0f));
        return animationSet;
    }

    private void f() {
        this.h = (OperationInteractorsApi) Services.a("OperationBundle", OperationInteractorsApi.class);
        this.g.setOnClickListener(this);
        this.q.setOnClickListener(this);
        this.l.d(this);
        this.m.setOnClickListener(this);
        this.k.addOnItemTouchListener(this.l);
    }

    private void h() {
        List<gka> d = bvp.c().d();
        if (koq.b(d)) {
            d = bvp.c().a();
        }
        ArrayList arrayList = new ArrayList();
        if (koq.c(d)) {
            arrayList.addAll(d);
        }
        TodoSwitchSync d2 = bvw.d(this);
        bvw.b((List<gka>) arrayList, d2, false);
        if (koq.c(arrayList) && bvw.b(this)) {
            int size = arrayList.size();
            bvw.b((List<gka>) arrayList, d2, true);
            Collections.sort(arrayList, new bvy(true));
            boolean c = koq.c(arrayList);
            a(!c, arrayList);
            if (c) {
                return;
            }
            a(size);
            return;
        }
        e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        bvw.c(this, AnalyticsValue.HEALTH_LIFE_TODO_TASK_CLICK_2040173, null, i, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (SystemClock.elapsedRealtime() - 3000 > this.f) {
            bvp.c().a(this.c);
            this.f = SystemClock.elapsedRealtime();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z, List<gka> list) {
        if (!z) {
            z = !bvw.b(this);
        }
        LogUtil.a("TodoListActivity", "showUiByStatus:", Boolean.valueOf(z));
        this.k.setVisibility(z ? 8 : 0);
        this.n.setVisibility(z ? 0 : 8);
        if (z) {
            eiv.a(this.f2045a, false, false);
            eiv.a(this.b, false, true);
            ImageView imageView = this.o;
            if (imageView == null || this.i == z) {
                return;
            }
            imageView.setVisibility(0);
            ImageView imageView2 = this.o;
            imageView2.startAnimation(imageView2.getAnimation());
            bvw.c(this, AnalyticsValue.HEALTH_LIFE_TODO_TASK_CLICK_2040173, null, koq.c(list) ? list.size() : 0, 0);
        } else {
            this.l.b(list);
            int size = list.size();
            ArrayList arrayList = new ArrayList(size);
            Iterator<gka> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().n());
            }
            HashMap hashMap = new HashMap(3);
            hashMap.put(ParsedFieldTag.TASK_NAME, arrayList);
            hashMap.put("taskNumber", Integer.valueOf(size));
            hashMap.put("click", 1);
            ixx.d().d(this, AnalyticsValue.HEALTH_LIFE_TODO_LIST_EXPOSE_2040178.value(), hashMap, 0);
        }
        this.i = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean j() {
        return (isFinishing() || isDestroyed()) ? false : true;
    }

    /* loaded from: classes3.dex */
    static class a implements IBaseResponseCallback {
        private final WeakReference<TodoListActivity> d;

        a(TodoListActivity todoListActivity) {
            this.d = new WeakReference<>(todoListActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            TodoListActivity todoListActivity = this.d.get();
            if (todoListActivity == null || !todoListActivity.j()) {
                return;
            }
            TodoSwitchSync a2 = bvw.a(todoListActivity);
            ArrayList arrayList = new ArrayList();
            List list = (List) obj;
            if (koq.c(list)) {
                arrayList.addAll(list);
            }
            bvw.b((List<gka>) arrayList, a2, false);
            if (koq.c(arrayList)) {
                int size = arrayList.size();
                bvw.b((List<gka>) arrayList, a2, true);
                Collections.sort(arrayList, new bvy(true));
                boolean c = koq.c(arrayList);
                todoListActivity.d.obtainMessage(17, arrayList).sendToTarget();
                if (c) {
                    return;
                }
                todoListActivity.a(size);
                return;
            }
            todoListActivity.e();
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
