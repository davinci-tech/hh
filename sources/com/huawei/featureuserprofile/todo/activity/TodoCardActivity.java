package com.huawei.featureuserprofile.todo.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.featureuserprofile.todo.adapter.TodoCardRecyAdapter;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.nps.api.NpsExternalApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.TodoTaskInterface;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.stories.soical.interactor.OperationDetailActivityStartCallback;
import com.huawei.ui.main.stories.soical.interactor.OperationInteractorsApi;
import defpackage.bvp;
import defpackage.bvw;
import defpackage.ceb;
import defpackage.gka;
import defpackage.koq;
import health.compact.a.GRSManager;
import health.compact.a.Services;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class TodoCardActivity extends BaseActivity implements TodoCardRecyAdapter.OnItemClickListener {

    /* renamed from: a, reason: collision with root package name */
    private TodoCardRecyAdapter f2044a;
    private RelativeLayout c;
    private b d;
    private c e;
    private OperationInteractorsApi h;
    private CustomTitleBar i;
    private HealthRecycleView j;
    private List<gka> f = new ArrayList(10);
    private Context b = null;

    /* loaded from: classes7.dex */
    static class c extends BaseHandler<TodoCardActivity> {
        private c(TodoCardActivity todoCardActivity) {
            super(todoCardActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: vn_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(TodoCardActivity todoCardActivity, Message message) {
            int i = message.what;
            if (i == 1) {
                if (message.obj instanceof List) {
                    todoCardActivity.c(bvp.c().d((List<gka>) message.obj));
                    return;
                }
                return;
            }
            LogUtil.h("TodoCardActivity", "handleMessageWhenReferenceNotNull what ", Integer.valueOf(i));
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("TodoCardActivity", "onCreate");
        setContentView(R.layout.activity_todo_card);
        getWindow().setBackgroundDrawable(null);
        this.b = this;
        this.d = new b(this);
        this.e = new c();
        this.h = (OperationInteractorsApi) Services.a("OperationBundle", OperationInteractorsApi.class);
        d();
        c();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        bvp.c().a(this.d);
    }

    private void d() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.me_todoCard_titlebar);
        this.i = customTitleBar;
        customTitleBar.setTitleText(getString(R.string._2130842595_res_0x7f0213e3));
        this.i.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.featureuserprofile.todo.activity.TodoCardActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                TodoCardActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.c = (RelativeLayout) findViewById(R.id.no_todo_data);
        HealthRecycleView healthRecycleView = (HealthRecycleView) findViewById(R.id.rv_todoActivity);
        this.j = healthRecycleView;
        healthRecycleView.setFocusableInTouchMode(false);
        this.j.a(false);
        this.j.d(false);
    }

    private void c() {
        this.f2044a = new TodoCardRecyAdapter(this.b, this.f);
        this.j.setLayoutManager(new LinearLayoutManager(this.b));
        this.f2044a.c(this);
        this.j.setAdapter(this.f2044a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<gka> list) {
        if (koq.b(list)) {
            LogUtil.h("TodoCardActivity", "models is empty.");
            this.c.setVisibility(0);
            this.j.setVisibility(8);
            return;
        }
        this.f.clear();
        this.f.addAll(list);
        LogUtil.a("TodoCardActivity", "mTodoCardDataLists.size  = ", Integer.valueOf(this.f.size()));
        this.c.setVisibility(8);
        this.j.setVisibility(0);
        this.f2044a.d(this.f.size());
        this.f2044a.e(this.f);
        this.j.getRecycledViewPool().clear();
        this.f2044a.notifyDataSetChanged();
    }

    private void b() {
        this.f2044a = null;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        b();
        HealthRecycleView healthRecycleView = this.j;
        if (healthRecycleView != null) {
            healthRecycleView.setOnScrollListener(null);
            this.j = null;
        }
    }

    @Override // com.huawei.featureuserprofile.todo.adapter.TodoCardRecyAdapter.OnItemClickListener
    public void onItemClicked(int i) {
        List<gka> list = this.f;
        if (list == null || list.size() <= i) {
            LogUtil.h("TodoCardActivity", "mTodoCardRecyModels is null or position is error");
            return;
        }
        NpsExternalApi npsExternalApi = (NpsExternalApi) Services.c("Main", NpsExternalApi.class);
        gka gkaVar = this.f.get(i);
        LogUtil.a("TodoCardActivity", "onItemClicked, position = ", Integer.valueOf(i), ", toDoModel.getType = ", Integer.valueOf(gkaVar.k()));
        int k = gkaVar.k();
        if (k == 768) {
            bvw.c(gkaVar.e());
            return;
        }
        if (k == 1536) {
            bvw.a(gkaVar);
            return;
        }
        if (k == 1792) {
            TodoTaskInterface o = gkaVar.o();
            if (o == null || !(o instanceof ceb)) {
                return;
            }
            d((ceb) o);
            return;
        }
        if (k == 2048) {
            npsExternalApi.showNpsPage(this.b);
        } else if (k == 2304) {
            npsExternalApi.showDeviceNpsPage(this.b);
        } else {
            LogUtil.a("TodoCardActivity", "error type");
        }
    }

    public void d(final ceb cebVar) {
        GRSManager.a(this.b).e("domainContentcenterDbankcdnNew", new GrsQueryCallback() { // from class: com.huawei.featureuserprofile.todo.activity.TodoCardActivity.4
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                LogUtil.c("TodoCardActivity", "GRSManager onCallBackSuccess ACTIVITY_KEY url = " + str);
                TodoCardActivity.this.h.gotoOperationActivityDetail(TodoCardActivity.this.b, str, cebVar, new OperationDetailActivityStartCallback() { // from class: com.huawei.featureuserprofile.todo.activity.TodoCardActivity.4.3
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
                LogUtil.c("TodoCardActivity", "GRSManager onCallBackFail ACTIVITY_KEY i = " + i);
            }
        });
    }

    /* loaded from: classes7.dex */
    static class b implements IBaseResponseCallback {
        private WeakReference<TodoCardActivity> c;

        b(TodoCardActivity todoCardActivity) {
            this.c = new WeakReference<>(todoCardActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            TodoCardActivity todoCardActivity = this.c.get();
            if (todoCardActivity == null) {
                LogUtil.h("TodoCardActivity", "mTodoCardActivity is null");
                return;
            }
            if (i == 0 && (obj instanceof List)) {
                Message obtainMessage = todoCardActivity.e.obtainMessage();
                obtainMessage.what = 1;
                obtainMessage.obj = obj;
                todoCardActivity.e.sendMessage(obtainMessage);
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
