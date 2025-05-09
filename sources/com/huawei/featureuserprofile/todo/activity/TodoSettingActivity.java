package com.huawei.featureuserprofile.todo.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import com.huawei.featureuserprofile.todo.activity.TodoSettingActivity;
import com.huawei.featureuserprofile.todo.adapter.TodoCheckListAdapter;
import com.huawei.featureuserprofile.todo.datatype.TodoCheckEntity;
import com.huawei.featureuserprofile.todo.datatype.TodoSwitchSync;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.healthmodel.bean.HealthLifeTaskBean;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.bvw;
import defpackage.dsl;
import defpackage.gpn;
import defpackage.koq;
import health.compact.a.util.LogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class TodoSettingActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {
    private HealthSwitchButton b;
    private HealthSwitchButton c;
    private TodoSwitchSync e;
    private LinearLayout f;
    private HealthSwitchButton g;
    private TodoCheckListAdapter k;
    private HealthSwitchButton o;
    private HealthSwitchButton p;
    private boolean j = false;
    private boolean m = false;
    private boolean n = false;
    private boolean h = false;
    private int l = 0;

    /* renamed from: a, reason: collision with root package name */
    private final List<HealthLifeTaskBean> f2047a = new ArrayList();
    private final Map<String, Integer> d = new HashMap();
    private final Handler i = new BaseHandler<TodoSettingActivity>(this) { // from class: com.huawei.featureuserprofile.todo.activity.TodoSettingActivity.4
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: vA_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(TodoSettingActivity todoSettingActivity, Message message) {
            int i = message.what;
            if (i == 1) {
                TodoSettingActivity.d(todoSettingActivity);
                Object obj = message.obj;
                if (obj instanceof TodoSwitchSync) {
                    todoSettingActivity.e = (TodoSwitchSync) obj;
                } else {
                    todoSettingActivity.e = null;
                }
                todoSettingActivity.n = true;
                if (todoSettingActivity.l >= 2) {
                    TodoSettingActivity.this.d(todoSettingActivity.e, (List<HealthLifeTaskBean>) todoSettingActivity.f2047a);
                    todoSettingActivity.l = 0;
                    return;
                }
                return;
            }
            if (i == 2) {
                todoSettingActivity.h();
                return;
            }
            if (i != 3) {
                return;
            }
            TodoSettingActivity.d(todoSettingActivity);
            Object obj2 = message.obj;
            todoSettingActivity.f2047a.clear();
            if ((obj2 instanceof List) && koq.e(obj2, HealthLifeTaskBean.class)) {
                todoSettingActivity.f2047a.addAll((List) obj2);
            }
            if (todoSettingActivity.l >= 2) {
                TodoSettingActivity.this.d(todoSettingActivity.e, (List<HealthLifeTaskBean>) todoSettingActivity.f2047a);
                todoSettingActivity.l = 0;
            }
        }
    };

    static /* synthetic */ int d(TodoSettingActivity todoSettingActivity) {
        int i = todoSettingActivity.l;
        todoSettingActivity.l = i + 1;
        return i;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setBackgroundDrawableResource(R.color._2131296690_res_0x7f0901b2);
        setContentView(R.layout.activity_health_todo_setting);
        d();
        e();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        LogUtil.d("TodoSettingActivity", "mIsSyncCloud:", Boolean.valueOf(this.n), "mIsHasChanged", Boolean.valueOf(this.h));
        if (this.n && this.h) {
            this.h = false;
            h();
        }
        super.onPause();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        if (this.n) {
            c();
        }
        this.i.removeMessages(2);
        this.i.removeMessages(1);
        this.d.clear();
        super.onDestroy();
    }

    private String b() {
        return getString(R$string.IDS_health_clover_title);
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        this.h = true;
        int id = compoundButton.getId();
        if (id == R.id.health_todo_main_switch) {
            this.m = z;
            this.d.put("t000", Integer.valueOf(bvw.d(z)));
            TodoCheckListAdapter todoCheckListAdapter = this.k;
            if (todoCheckListAdapter != null) {
                todoCheckListAdapter.c(z);
            }
            this.g.setEnabled(z);
            this.b.setEnabled(z);
            this.c.setEnabled(z);
            this.p.setEnabled(z);
            bvw.a(this, getString(R.string._2130838905_res_0x7f020579), getString(R.string._2130838905_res_0x7f020579), z);
        } else if (id == R.id.health_todo_live_main_swicth) {
            this.j = z;
            this.d.put("t100", Integer.valueOf(bvw.d(z)));
            TodoCheckListAdapter todoCheckListAdapter2 = this.k;
            if (todoCheckListAdapter2 != null) {
                todoCheckListAdapter2.a(z);
            }
            String b = b();
            bvw.a(this, b, b, z);
        } else if (id == R.id.health_todo_plan_performing_switch) {
            this.d.put("t201", Integer.valueOf(bvw.d(z)));
            bvw.a(this, getString(R.string._2130844186_res_0x7f021a1a), getString(R.string._2130845607_res_0x7f021fa7), z);
        } else if (id == R.id.health_todo_activity_activing_switch) {
            this.d.put("t301", Integer.valueOf(bvw.d(z)));
            bvw.a(this, getString(R.string._2130838907_res_0x7f02057b), getString(R.string._2130845606_res_0x7f021fa6), z);
        } else if (id == R.id.health_todo_cal_balance_switch) {
            this.d.put("t401", Integer.valueOf(bvw.d(z)));
            bvw.a(this, getString(R.string._2130838962_res_0x7f0205b2), getString(R.string._2130838961_res_0x7f0205b1), z);
        } else {
            Object tag = compoundButton.getTag(R.id.health_todo_live_item_switch);
            if (!(tag instanceof Integer)) {
                ViewClickInstrumentation.clickOnView(compoundButton);
                return;
            }
            TodoCheckEntity item = this.k.getItem(((Integer) tag).intValue());
            if (item != null) {
                item.setChecked(z);
                this.d.put(item.getKey(), Integer.valueOf(bvw.d(z)));
                bvw.a(this, item.getTitle(), b(), z);
            }
        }
        ViewClickInstrumentation.clickOnView(compoundButton);
    }

    private void d() {
        ((HealthTextView) findViewById(R.id.activity_health_todo_setting_text)).setText(b());
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.health_todo_titlebar);
        customTitleBar.setTitleBarBackgroundColor(getResources().getColor(R.color._2131296690_res_0x7f0901b2));
        customTitleBar.setTitleTextColor(getResources().getColor(R.color._2131299236_res_0x7f090ba4));
        customTitleBar.setLeftButtonClickable(true);
        customTitleBar.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: bve
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TodoSettingActivity.this.vz_(view);
            }
        });
        this.o = (HealthSwitchButton) findViewById(R.id.health_todo_main_switch);
        this.g = (HealthSwitchButton) findViewById(R.id.health_todo_live_main_swicth);
        this.p = (HealthSwitchButton) findViewById(R.id.health_todo_plan_performing_switch);
        this.b = (HealthSwitchButton) findViewById(R.id.health_todo_activity_activing_switch);
        this.c = (HealthSwitchButton) findViewById(R.id.health_todo_cal_balance_switch);
        if (!gpn.b()) {
            LogUtil.d("TodoSettingActivity", "is calBalance todo task Operated false ");
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.health_todo_cal_balance_switch_layout);
            HealthTextView healthTextView = (HealthTextView) findViewById(R.id.health_todo_cal_balance_switch_title);
            linearLayout.setVisibility(8);
            healthTextView.setVisibility(8);
        }
        this.k = new TodoCheckListAdapter();
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.health_todo_live_series_switch_layout);
        this.f = linearLayout2;
        this.k.vH_(linearLayout2);
    }

    public /* synthetic */ void vz_(View view) {
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void g() {
        this.k.vI_(this);
        this.o.setOnCheckedChangeListener(this);
        this.g.setOnCheckedChangeListener(this);
        this.p.setOnCheckedChangeListener(this);
        this.b.setOnCheckedChangeListener(this);
        this.c.setOnCheckedChangeListener(this);
    }

    private void a(TodoSwitchSync todoSwitchSync, List<HealthLifeTaskBean> list) {
        Map<String, Integer> a2 = bvw.a(todoSwitchSync, this.d);
        this.m = bvw.d(a2.get("t000"));
        this.j = bvw.d(a2.get("t100"));
        this.o.setChecked(this.m);
        this.g.setChecked(this.j);
        this.g.setEnabled(this.m);
        this.p.setChecked(bvw.d(a2.get("t201")));
        this.p.setEnabled(this.m);
        this.b.setChecked(bvw.d(a2.get("t301")));
        this.b.setEnabled(this.m);
        this.c.setChecked(bvw.d(a2.get("t401")));
        this.c.setEnabled(this.m);
        int size = koq.b(list) ? 0 : list.size();
        ArrayList arrayList = new ArrayList(size);
        if (size > 0) {
            for (HealthLifeTaskBean healthLifeTaskBean : list) {
                if (healthLifeTaskBean != null) {
                    String a3 = bvw.a(healthLifeTaskBean.getId());
                    if (!TextUtils.isEmpty(a3)) {
                        arrayList.add(new TodoCheckEntity(healthLifeTaskBean.getName(), a3, bvw.d(a3, a2.get(a3))));
                    }
                }
            }
        }
        this.k.a(this.j);
        this.k.c(this.m);
        this.k.e(arrayList);
    }

    private void e() {
        a(bvw.d(this), null);
        a();
    }

    private void a() {
        dsl.a(DateFormatUtil.b(System.currentTimeMillis()), new d(this));
        ThreadPoolManager.d().execute(new a(this, false));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(TodoSwitchSync todoSwitchSync, List<HealthLifeTaskBean> list) {
        a(todoSwitchSync, list);
        g();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        ThreadPoolManager.d().execute(new a(this, true));
    }

    private void c() {
        boolean isChecked = this.b.isChecked();
        boolean isChecked2 = this.p.isChecked();
        boolean isChecked3 = this.c.isChecked();
        List<TodoCheckEntity> b = this.k.b();
        int i = 0;
        if (koq.c(b)) {
            Iterator<TodoCheckEntity> it = b.iterator();
            while (it.hasNext()) {
                if (!it.next().isChecked()) {
                    i++;
                }
            }
        }
        bvw.a(this, b(), i);
        bvw.a(this, getString(R.string._2130845607_res_0x7f021fa7), !isChecked2 ? 1 : 0);
        bvw.a(this, getString(R.string._2130845606_res_0x7f021fa6), !isChecked ? 1 : 0);
        bvw.a(this, getString(R.string._2130838961_res_0x7f0205b1), !isChecked3 ? 1 : 0);
    }

    /* loaded from: classes3.dex */
    static class a implements Runnable {
        private final boolean b;
        private final WeakReference<TodoSettingActivity> d;

        a(TodoSettingActivity todoSettingActivity, boolean z) {
            this.d = new WeakReference<>(todoSettingActivity);
            this.b = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            TodoSettingActivity todoSettingActivity = this.d.get();
            if (todoSettingActivity == null) {
                return;
            }
            if (this.b) {
                LogUtil.d("TodoSettingActivity", "mIsSync:", Boolean.valueOf(this.b), ",isSuccess:", Boolean.valueOf(bvw.d(BaseApplication.e(), (Map<String, Integer>) todoSettingActivity.d)));
            } else {
                if (todoSettingActivity.isFinishing() || todoSettingActivity.isDestroyed()) {
                    return;
                }
                TodoSwitchSync a2 = bvw.a(todoSettingActivity);
                LogUtil.d("TodoSettingActivity", "mIsSync:", Boolean.valueOf(this.b), ",todoSwitchSync:", a2);
                if (a2 == null) {
                    a2 = bvw.d(todoSettingActivity);
                }
                todoSettingActivity.i.obtainMessage(1, a2).sendToTarget();
            }
        }
    }

    /* loaded from: classes3.dex */
    static class d implements ResponseCallback<List<HealthLifeTaskBean>> {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<TodoSettingActivity> f2048a;

        d(TodoSettingActivity todoSettingActivity) {
            this.f2048a = new WeakReference<>(todoSettingActivity);
        }

        @Override // com.huawei.hwbasemgr.ResponseCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, List<HealthLifeTaskBean> list) {
            TodoSettingActivity todoSettingActivity = this.f2048a.get();
            if (todoSettingActivity == null || todoSettingActivity.isFinishing() || todoSettingActivity.isDestroyed()) {
                return;
            }
            todoSettingActivity.i.obtainMessage(3, list).sendToTarget();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
