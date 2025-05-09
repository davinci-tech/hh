package com.huawei.ui.main.stories.me.activity;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.huawei.haf.bundle.AppBundle;
import com.huawei.haf.bundle.AppBundleExtension;
import com.huawei.haf.bundle.InstallSessionState;
import com.huawei.haf.bundle.guide.BundleInstallTaskManager;
import com.huawei.haf.bundle.guide.BundleInstaller;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hmf.tasks.OnCompleteListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.network.embedded.g4;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.downloadwidget.HealthDownLoadWidget;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import defpackage.mwq;
import defpackage.nrh;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.LogAnonymous;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes7.dex */
public class PluginManagerActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private long f10341a;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_plugin_manager);
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.plugin_management_title_layout);
        customTitleBar.setRightButtonVisibility(0);
        customTitleBar.setRightButtonDrawable(getResources().getDrawable(R.mipmap._2131820993_res_0x7f1101c1), nsf.h(R$string.IDS_main_btn_state_settings));
        customTitleBar.setRightButtonOnClickListener(this);
        a();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 111) {
            LogUtil.a("Bundle_PluginManagerActivity", "onActivityResult resultCode=", Integer.valueOf(i2));
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        nrh.e();
    }

    private void a() {
        ListView listView = (ListView) findViewById(R.id.plugin_manager_list_view);
        listView.setAdapter((ListAdapter) new c(this, R.layout.adapter_plugin_manager, R.id.plugin_manager_name, b()));
        listView.setVisibility(0);
    }

    private List<String> b() {
        ArrayList arrayList;
        Intent intent = getIntent();
        ArrayList<String> stringArrayListExtra = intent != null ? intent.getStringArrayListExtra("PluginModules") : null;
        if (stringArrayListExtra == null || stringArrayListExtra.isEmpty()) {
            arrayList = new ArrayList(AppBundle.c().getAllBundleModules(this, true));
        } else {
            arrayList = new ArrayList(stringArrayListExtra);
        }
        List<String> a2 = a(arrayList);
        Collections.sort(a2);
        return a2;
    }

    private List<String> a(List<String> list) {
        list.removeAll(mwq.d().a());
        return list;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.f10341a <= 1000) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        this.f10341a = currentTimeMillis;
        startActivity(new Intent(this, (Class<?>) PluginSettingActivity.class));
        ViewClickInstrumentation.clickOnView(view);
    }

    static class c extends ArrayAdapter<String> {
        private final AppBundleExtension b;

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean isEnabled(int i) {
            return false;
        }

        c(Context context, int i, int i2, List<String> list) {
            super(context, i, i2, list);
            this.b = AppBundle.c();
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public String getItem(int i) {
            return this.b.getModuleTitle(getContext(), (String) super.getItem(i));
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            String str = (String) super.getItem(i);
            Context context = getContext();
            View view2 = super.getView(i, view, viewGroup);
            ((HealthTextView) view2.findViewById(R.id.plugin_manager_desc)).setText(context.getString(R$string.IDS_bundle_desc, this.b.getModuleDescription(context, str)));
            ((HealthTextView) view2.findViewById(R.id.plugin_manager_size)).setText(context.getString(R$string.IDS_bundle_size, nsn.b(context, this.b.getModuleZipSize(context, str))));
            ((HealthTextView) view2.findViewById(R.id.plugin_manager_dependency)).setText(context.getString(R$string.IDS_bundle_dependency, e(context, str)));
            HealthDownLoadWidget healthDownLoadWidget = (HealthDownLoadWidget) view2.findViewById(R.id.plugin_manager_install);
            HealthDownLoadWidget healthDownLoadWidget2 = (HealthDownLoadWidget) view2.findViewById(R.id.plugin_manager_uninstall);
            String string = context.getString(com.huawei.ui.commonui.R$string.IDS_bundle_uninstall_button);
            healthDownLoadWidget2.setIdleText(string);
            healthDownLoadWidget2.setPauseText(string);
            view2.setTag(new d(this, str, healthDownLoadWidget, healthDownLoadWidget2));
            return view2;
        }

        private String e(Context context, String str) {
            List<String> moduleDependencies = this.b.getModuleDependencies(context, str);
            if (moduleDependencies.isEmpty()) {
                return context.getString(com.huawei.ui.commonui.R$string.IDS_bundle_dependency_none);
            }
            StringBuilder sb = new StringBuilder(64);
            for (String str2 : moduleDependencies) {
                if (sb.length() != 0) {
                    sb.append(' ');
                }
                sb.append(this.b.getModuleTitle(context, str2));
                sb.append(' ');
                sb.append(g4.k);
                sb.append(nsn.b(context, this.b.getModuleZipSize(context, str2)));
                sb.append(g4.l);
            }
            return sb.toString();
        }

        int e(String str) {
            if (d(str)) {
                return com.huawei.ui.commonui.R$string.IDS_bundle_install_button;
            }
            if (this.b.isExistLocalModule(str)) {
                return com.huawei.ui.commonui.R$string.IDS_bundle_complete_button;
            }
            if (this.b.getUpdateModules(getContext(), true).contains(str)) {
                return com.huawei.ui.commonui.R$string.IDS_bundle_update_button;
            }
            return com.huawei.ui.commonui.R$string.IDS_bundle_install_button;
        }

        boolean d(String str) {
            return this.b.isUnistalledModule(str);
        }

        boolean b(String str) {
            return this.b.getSetting().isAllowDownloadModule(getContext(), str);
        }
    }

    static class d extends ContextWrapper implements BundleInstaller.InstallHandler, View.OnClickListener, Runnable {

        /* renamed from: a, reason: collision with root package name */
        private final BundleInstaller f10342a;
        private final c b;
        private final HealthDownLoadWidget c;
        private boolean d;
        private final String e;
        private final HealthDownLoadWidget i;

        d(c cVar, String str, HealthDownLoadWidget healthDownLoadWidget, HealthDownLoadWidget healthDownLoadWidget2) {
            super(cVar.getContext());
            this.b = cVar;
            this.e = str;
            this.c = healthDownLoadWidget;
            this.i = healthDownLoadWidget2;
            this.f10342a = BundleInstallTaskManager.d().b(str, this);
            healthDownLoadWidget.setOnClickListener(this);
            healthDownLoadWidget2.setOnClickListener(this);
            a();
            if (BundleInstallTaskManager.d("Bundle_PluginManagerActivity", str)) {
                this.d = true;
                healthDownLoadWidget.c(0);
                healthDownLoadWidget.setEnabled(false);
                healthDownLoadWidget2.setEnabled(false);
            }
        }

        @Override // com.huawei.haf.bundle.guide.BundleInstaller.InstallHandler
        public boolean onForceDownloads() {
            LogUtil.a("Bundle_PluginManagerActivity", "onForceDownloads module=", this.e);
            this.d = true;
            this.c.c(0);
            this.c.setEnabled(false);
            this.i.setEnabled(false);
            BundleInstallTaskManager.b("Bundle_PluginManagerActivity", this.e, this.f10342a);
            return false;
        }

        @Override // com.huawei.haf.bundle.guide.BundleInstaller.InstallHandler
        public boolean onInstallRequestError(int i, String str, boolean z) {
            if (!TextUtils.isEmpty(str)) {
                nrh.d(this, str);
            }
            return z;
        }

        @Override // com.huawei.haf.bundle.guide.BundleInstaller.InstallHandler
        public void onRequiresUserConfirmation(InstallSessionState installSessionState) {
            boolean z = true;
            if (!this.d && installSessionState.moduleNames().size() != 1) {
                z = false;
            }
            try {
                this.f10342a.startConfirmationDialogForResult(installSessionState, dNA_(), 111, z);
            } catch (IntentSender.SendIntentException e) {
                LogUtil.b("Bundle_PluginManagerActivity", "onRequiresUserConfirmation ex=", LogAnonymous.b((Throwable) e));
            }
        }

        @Override // com.huawei.haf.bundle.guide.BundleInstaller.InstallHandler
        public void onPending(InstallSessionState installSessionState, String str) {
            this.c.c(0);
            this.c.setEnabled(false);
            this.c.setPauseText(str);
            this.i.setEnabled(false);
        }

        @Override // com.huawei.haf.bundle.guide.BundleInstaller.InstallHandler
        public void onProgressMessage(InstallSessionState installSessionState, int i, String str) {
            this.c.c(i - this.c.getProgress());
            this.c.setEnabled(false);
            this.c.setPauseText(str);
            this.i.setEnabled(false);
        }

        @Override // com.huawei.haf.bundle.guide.BundleInstaller.InstallHandler
        public void onFinish(boolean z) {
            LogUtil.a("Bundle_PluginManagerActivity", "onFinish module=", this.e, ", result=", Boolean.valueOf(z));
            BundleInstallTaskManager.e("Bundle_PluginManagerActivity");
            this.d = false;
            HandlerExecutor.d(this, 1000L);
            if (z) {
                nrh.b(dNA_(), R$string.IDS_bundle_install_desc);
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (view.getId() == R.id.plugin_manager_install) {
                LogUtil.a("Bundle_PluginManagerActivity", "startInstall module=", this.e);
                this.c.setEnabled(false);
                if (this.f10342a.getInstallManager().getInstalledModules().contains(this.e)) {
                    this.f10342a.getInstallManager().deferredInstall(Collections.singletonList(this.e)).addOnCompleteListener(new OnCompleteListener<Void>() { // from class: com.huawei.ui.main.stories.me.activity.PluginManagerActivity.d.3
                        @Override // com.huawei.hmf.tasks.OnCompleteListener
                        public void onComplete(Task<Void> task) {
                            HandlerExecutor.d(d.this, 1000L);
                            nrh.b(d.this.dNA_(), R$string.IDS_bundle_install_desc);
                        }
                    });
                } else {
                    this.f10342a.startInstall(Collections.singletonList(this.e));
                }
            } else {
                LogUtil.a("Bundle_PluginManagerActivity", "deferredUninstall module=", this.e);
                this.i.setEnabled(false);
                this.f10342a.getInstallManager().deferredUninstall(Collections.singletonList(this.e)).addOnCompleteListener(new OnCompleteListener<Void>() { // from class: com.huawei.ui.main.stories.me.activity.PluginManagerActivity.d.1
                    @Override // com.huawei.hmf.tasks.OnCompleteListener
                    public void onComplete(Task<Void> task) {
                        HandlerExecutor.d(d.this, 1000L);
                        nrh.b(d.this.dNA_(), R$string.IDS_bundle_uninstall_desc);
                    }
                });
            }
            ViewClickInstrumentation.clickOnView(view);
        }

        @Override // java.lang.Runnable
        public void run() {
            a();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Activity dNA_() {
            Context context = this.b.getContext();
            if (context instanceof Activity) {
                return (Activity) context;
            }
            return null;
        }

        private void a() {
            int e = this.b.e(this.e);
            String string = getString(e);
            this.c.setIdleText(string);
            this.c.setPauseText(string);
            this.c.b();
            this.c.d();
            boolean z = false;
            this.c.setProgress(0);
            this.c.setEnabled((e == com.huawei.ui.commonui.R$string.IDS_bundle_complete_button || this.f10342a.isRunning()) ? false : true);
            if (e != com.huawei.ui.commonui.R$string.IDS_bundle_install_button && !this.b.b(this.e)) {
                z = true;
            }
            this.i.setEnabled(z);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
