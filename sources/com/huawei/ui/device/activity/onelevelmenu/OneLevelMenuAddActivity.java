package com.huawei.ui.device.activity.onelevelmenu;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.device.views.onelevelmenu.OneLevelMenuAddListAdapter;
import defpackage.nsy;
import defpackage.oai;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class OneLevelMenuAddActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private ArrayList<Integer> f9168a;
    private LinearLayout b;
    private Context c;
    private ArrayList<Integer> d;
    private CustomTitleBar e;
    private oai f;
    private ListView g;
    private LinearLayout h;
    private OneLevelMenuAddListAdapter i;
    private ArrayList<Integer> j;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_one_level_add_menu_layout);
        a();
        b();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    private void a() {
        this.c = getApplicationContext();
        this.f = oai.a();
        if (getIntent() != null) {
            try {
                this.f9168a = getIntent().getIntegerArrayListExtra("intent_to_next_all_list");
                this.j = getIntent().getIntegerArrayListExtra("intent_to_next_open_list");
            } catch (ArrayIndexOutOfBoundsException e) {
                LogUtil.a("OneLevelMenuAddActivity", "ArrayIndexOutOfBoundsException occur message is ", e.getMessage());
            }
        }
        ArrayList<Integer> arrayList = this.f9168a;
        if (arrayList == null) {
            return;
        }
        ArrayList<Integer> arrayList2 = this.j;
        if (arrayList2 == null) {
            this.d = arrayList;
            return;
        }
        LogUtil.a("OneLevelMenuAddActivity", "mAllItemTables:", arrayList, "mMenuItemTables:", arrayList2);
        this.d = oai.a().e(this.j, this.f9168a);
        LogUtil.a("OneLevelMenuAddActivity", "mContactTables size = ", Integer.valueOf(this.j.size()));
    }

    private void b() {
        CustomTitleBar customTitleBar = (CustomTitleBar) nsy.cMc_(this, R.id.one_level_add_menu_titlebar);
        this.e = customTitleBar;
        customTitleBar.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.onelevelmenu.OneLevelMenuAddActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                OneLevelMenuAddActivity.this.e.setRightButtonClickable(true);
                OneLevelMenuAddActivity.this.c();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.h = (LinearLayout) nsy.cMc_(this, R.id.one_level_add_menu_no_content_view);
        this.b = (LinearLayout) nsy.cMc_(this, R.id.one_level_add_menu_layout);
        this.g = (ListView) nsy.cMc_(this, R.id.one_level_add_menu_listview);
        ArrayList<Integer> arrayList = this.d;
        if (arrayList != null && arrayList.size() > 0) {
            c(false);
            LogUtil.a("OneLevelMenuAddActivity", "mDuplicateListItemTables", this.d);
            this.g.setSelector(R.drawable.device_settings_contact_listview_item_selector_black);
            OneLevelMenuAddListAdapter oneLevelMenuAddListAdapter = new OneLevelMenuAddListAdapter(this.c, this.d);
            this.i = oneLevelMenuAddListAdapter;
            this.g.setAdapter((ListAdapter) oneLevelMenuAddListAdapter);
            this.g.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.huawei.ui.device.activity.onelevelmenu.OneLevelMenuAddActivity.4
                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    OneLevelMenuAddListAdapter.b bVar = view.getTag() instanceof OneLevelMenuAddListAdapter.b ? (OneLevelMenuAddListAdapter.b) view.getTag() : null;
                    if (bVar == null) {
                        LogUtil.b("OneLevelMenuAddActivity", "holder == null");
                        ViewClickInstrumentation.clickOnListView(adapterView, view, i);
                    } else {
                        bVar.b().toggle();
                        OneLevelMenuAddListAdapter.c().put(Integer.valueOf(i), Boolean.valueOf(bVar.b().isChecked()));
                        LogUtil.a("OneLevelMenuAddActivity", "ContactDeleteListAdapter map = ", OneLevelMenuAddListAdapter.c());
                        ViewClickInstrumentation.clickOnListView(adapterView, view, i);
                    }
                }
            });
            return;
        }
        c(true);
    }

    private void c(boolean z) {
        if (z) {
            this.h.setVisibility(0);
            this.b.setVisibility(8);
        } else {
            this.h.setVisibility(8);
            this.b.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        ArrayList<Integer> arrayList;
        LogUtil.c("OneLevelMenuAddActivity", "saveData() delete data before mContactTables = " + this.j);
        if (this.f.e() || (arrayList = this.d) == null) {
            return;
        }
        int size = arrayList.size();
        LogUtil.a("OneLevelMenuAddActivity", "map=", OneLevelMenuAddListAdapter.c());
        while (true) {
            size--;
            if (size >= 0) {
                LogUtil.a("OneLevelMenuAddActivity", "mContactTables i=", Integer.valueOf(size));
                if (!OneLevelMenuAddListAdapter.c().get(Integer.valueOf(size)).booleanValue()) {
                    LogUtil.a("OneLevelMenuAddActivity", "getIsSelected i=", Integer.valueOf(size), " is selected!");
                    this.d.remove(size);
                }
            } else {
                LogUtil.a("OneLevelMenuAddActivity", "saveData() delete data after mContactTables = " + this.d);
                Intent intent = new Intent();
                intent.putIntegerArrayListExtra("intent_from_next_open_list", this.d);
                LogUtil.a("OneLevelMenuAddActivity", "mMenuTables gotonext mMenuTables" + oai.a().e(this.f9168a, this.d));
                setResult(1, intent);
                finish();
                return;
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
