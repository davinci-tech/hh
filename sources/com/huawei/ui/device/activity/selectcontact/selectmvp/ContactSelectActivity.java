package com.huawei.ui.device.activity.selectcontact.selectmvp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.SearchView;
import com.huawei.datatype.Contact;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.jsoperation.JsUtil;
import com.huawei.ui.commonui.searchview.HealthSearchView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.toolbar.HealthToolBar;
import com.huawei.ui.device.activity.selectcontact.selectmvp.adapter.ContactGroupListViewAdapter;
import com.huawei.ui.device.views.music.AlphabetIndexWaveSideBarView;
import com.huawei.ui.main.stories.fitness.activity.bloodoxygen.base.CommonBaseMvpActivity;
import defpackage.nrh;
import defpackage.nsf;
import defpackage.nxv;
import defpackage.nxw;
import defpackage.sqo;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class ContactSelectActivity extends CommonBaseMvpActivity<ContactSelectActivityView, nxw> implements ContactSelectActivityView, View.OnClickListener, SearchView.OnQueryTextListener {

    /* renamed from: a, reason: collision with root package name */
    private ContactGroupListViewAdapter f9219a;
    private ListView b;
    private HealthSearchView c;
    private Context d;
    private RelativeLayout e;
    private AlphabetIndexWaveSideBarView f;
    private LinearLayout g;
    private CustomTitleBar h;
    private RelativeLayout i;
    private View j;
    private HealthToolBar l;

    @Override // com.huawei.ui.main.stories.fitness.activity.bloodoxygen.base.CommonBaseActivity
    public int getLayoutId() {
        return R.layout.activity_select_contact;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.ui.main.stories.fitness.activity.bloodoxygen.base.CommonBaseMvpActivity
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public nxw createPresenter() {
        return new nxw();
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.bloodoxygen.base.CommonBaseActivity
    public void initView() {
        this.d = this;
        this.h = (CustomTitleBar) findViewById(R.id.contant_select_titlebar);
        this.b = (ListView) findViewById(R.id.hw_contact_select_list);
        this.f = (AlphabetIndexWaveSideBarView) findViewById(R.id.bar_list);
        this.c = (HealthSearchView) findViewById(R.id.contact_search_view);
        this.l = (HealthToolBar) findViewById(R.id.bottom_operate_toolbar);
        this.g = (LinearLayout) findViewById(R.id.lin_no_data);
        this.i = (RelativeLayout) findViewById(R.id.load_progress);
        this.e = (RelativeLayout) findViewById(R.id.rela_info);
        this.j = findViewById(R.id.search_layout);
        this.c.setOnQueryTextListener(this);
        initTitleBar();
        setCustomTitleBar(0);
        initSideBarView();
        initHealthToolBar();
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.bloodoxygen.base.CommonBaseActivity
    public void initData() {
        LogUtil.a("ContactSelectActivity", "initData enter");
        Intent intent = getIntent();
        if (intent != null) {
            int intExtra = intent.getIntExtra("com.huawei.community.action.MAX_SELECT_COUNT", 0);
            Serializable serializableExtra = intent.getSerializableExtra("com.huawei.community.action.OLD_CONTACTS");
            if (c(serializableExtra, String.class)) {
                ((nxw) this.mPresenter).setMaxSelectCount(intExtra);
                ((nxw) this.mPresenter).setOldContactList((ArrayList) serializableExtra);
                if (PermissionUtil.e(this, PermissionUtil.PermissionType.READ_CONTACT) != PermissionUtil.PermissionResult.GRANTED) {
                    ReleaseLogUtil.d("DEVMGR_ContactSelectActivity", "initData isHasReadContactPermission false");
                    sqo.b("ContactSelectActivity initData: isHasReadContactPermission is false");
                    finish();
                    return;
                }
                ((nxw) this.mPresenter).initData();
            }
        }
    }

    @Override // com.huawei.ui.device.activity.selectcontact.selectmvp.ContactSelectActivityView
    public void setCustomTitleBar(int i) {
        String string;
        LogUtil.a("ContactSelectActivity", "setCustomTitleBar enter selectCount : ", Integer.valueOf(i));
        if (i > 0) {
            this.h.setRightButtonClickable(true);
            string = getResources().getQuantityString(R.plurals._2130903278_res_0x7f0300ee, i, Integer.valueOf(i));
            this.h.setRightButtonDrawable(getResources().getDrawable(R.drawable._2131430292_res_0x7f0b0b94), nsf.h(R.string._2130841395_res_0x7f020f33));
        } else {
            this.h.setRightButtonClickable(false);
            string = this.d.getResources().getString(R.string.IDS_device_wifi_selectNone);
            this.h.setRightButtonDrawable(getResources().getDrawable(R.drawable._2131430219_res_0x7f0b0b4b), nsf.h(R.string._2130841395_res_0x7f020f33));
        }
        this.h.setTitleText(string);
    }

    @Override // com.huawei.ui.device.activity.selectcontact.selectmvp.ContactSelectActivityView
    public void initTitleBar() {
        LogUtil.a("ContactSelectActivity", "initTitleBar enter");
        this.h.setRightButtonVisibility(0);
        this.h.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.selectcontact.selectmvp.ContactSelectActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ((nxw) ContactSelectActivity.this.mPresenter).getResultList();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.h.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.selectcontact.selectmvp.ContactSelectActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ContactSelectActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // com.huawei.ui.device.activity.selectcontact.selectmvp.ContactSelectActivityView
    public Context getContext() {
        return this.d;
    }

    @Override // com.huawei.ui.device.activity.selectcontact.selectmvp.ContactSelectActivityView
    public void setAdapter(List<nxv> list) {
        dismissLoadingView();
        if (list == null || list.isEmpty()) {
            this.g.setVisibility(0);
            this.b.setVisibility(8);
            this.l.setVisibility(8);
            this.f.setVisibility(8);
            return;
        }
        this.g.setVisibility(8);
        this.b.setVisibility(0);
        this.l.setVisibility(0);
        this.f.setVisibility(0);
        ContactGroupListViewAdapter contactGroupListViewAdapter = new ContactGroupListViewAdapter(list, this.d);
        this.f9219a = contactGroupListViewAdapter;
        this.b.setAdapter((ListAdapter) contactGroupListViewAdapter);
        initListView();
    }

    @Override // com.huawei.ui.device.activity.selectcontact.selectmvp.ContactSelectActivityView
    public void initSideBarView() {
        this.f.setOnLetterChangeListener(new AlphabetIndexWaveSideBarView.OnLetterChangeListener() { // from class: com.huawei.ui.device.activity.selectcontact.selectmvp.ContactSelectActivity.2
            @Override // com.huawei.ui.device.views.music.AlphabetIndexWaveSideBarView.OnLetterChangeListener
            public void onLetterChange(String str) {
                int positionForSection = ContactSelectActivity.this.f9219a.getPositionForSection(str.charAt(0));
                if (positionForSection != -1) {
                    ContactSelectActivity.this.b.setSelection(positionForSection);
                }
            }
        });
    }

    @Override // com.huawei.ui.device.activity.selectcontact.selectmvp.ContactSelectActivityView
    public void initListView() {
        this.b.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.huawei.ui.device.activity.selectcontact.selectmvp.ContactSelectActivity.5
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                ((nxw) ContactSelectActivity.this.mPresenter).itemSelected(i);
                ContactSelectActivity.this.f9219a.notifyDataSetChanged();
                ViewClickInstrumentation.clickOnListView(adapterView, view, i);
            }
        });
    }

    @Override // com.huawei.ui.device.activity.selectcontact.selectmvp.ContactSelectActivityView
    public void initHealthToolBar() {
        this.l.cHc_(View.inflate(this.d, R.layout.hw_toolbar_bottomview, null));
        this.l.setOnSingleTapListener(new HealthToolBar.OnSingleTapListener() { // from class: com.huawei.ui.device.activity.selectcontact.selectmvp.ContactSelectActivity.3
            @Override // com.huawei.ui.commonui.toolbar.HealthToolBar.OnSingleTapListener
            public void onSingleTap(int i) {
                if (i == 2) {
                    ((nxw) ContactSelectActivity.this.mPresenter).selectAll();
                    if (ContactSelectActivity.this.f9219a != null) {
                        ContactSelectActivity.this.f9219a.notifyDataSetChanged();
                    }
                }
            }
        });
        this.l.setIcon(2, R.drawable._2131430296_res_0x7f0b0b98);
        this.l.setIconTitle(2, getResources().getString(R.string._2130841399_res_0x7f020f37));
    }

    @Override // com.huawei.ui.device.activity.selectcontact.selectmvp.ContactSelectActivityView
    public void changeHealthToolBarState(boolean z) {
        ((nxw) this.mPresenter).setIsSelectAll(z);
        if (z) {
            this.l.setIconTitle(2, getResources().getString(R.string._2130841400_res_0x7f020f38));
            this.l.setIcon(2, R.drawable._2131430281_res_0x7f0b0b89);
        } else {
            this.l.setIcon(2, R.drawable._2131430296_res_0x7f0b0b98);
            this.l.setIconTitle(2, getResources().getString(R.string._2130841399_res_0x7f020f37));
        }
    }

    @Override // com.huawei.ui.device.activity.selectcontact.selectmvp.ContactSelectActivityView
    public void finishSelect(List<Contact> list) {
        if (list instanceof Serializable) {
            Intent intent = new Intent();
            intent.putExtra(JsUtil.ServiceType.DATA, (Serializable) list);
            setResult(-1, intent);
            finish();
        }
    }

    @Override // com.huawei.ui.device.activity.selectcontact.selectmvp.ContactSelectActivityView
    public void showUpperLimitMessage(int i) {
        nrh.d(this.d, getResources().getQuantityString(R.plurals._2130903279_res_0x7f0300ef, i, Integer.valueOf(i)));
    }

    @Override // com.huawei.ui.device.activity.selectcontact.selectmvp.ContactSelectActivityView
    public void dismissLoadingView() {
        this.i.setVisibility(8);
        this.e.setVisibility(0);
        this.j.setVisibility(0);
    }

    @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
    public boolean onQueryTextSubmit(String str) {
        ((nxw) this.mPresenter).onEditTextInputChange(str);
        return true;
    }

    @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
    public boolean onQueryTextChange(String str) {
        ((nxw) this.mPresenter).onEditTextInputChange(str);
        return true;
    }

    private boolean c(Object obj, Class cls) {
        if (cls != null && obj != null && (obj instanceof ArrayList)) {
            Iterator it = ((ArrayList) obj).iterator();
            while (it.hasNext()) {
                if (cls.isInstance(it.next())) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        HealthSearchView healthSearchView = this.c;
        if (healthSearchView != null) {
            healthSearchView.clearFocus();
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.bloodoxygen.base.CommonBaseMvpActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        HealthSearchView healthSearchView = this.c;
        if (healthSearchView != null) {
            healthSearchView.setOnQueryTextListener(null);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            View currentFocus = getCurrentFocus();
            if (currentFocus != null && !(currentFocus instanceof HealthSearchView)) {
                b();
            }
            return super.dispatchTouchEvent(motionEvent);
        }
        if (getWindow().superDispatchTouchEvent(motionEvent)) {
            return true;
        }
        return onTouchEvent(motionEvent);
    }

    private void b() {
        Object systemService = getSystemService("input_method");
        if (systemService instanceof InputMethodManager) {
            InputMethodManager inputMethodManager = (InputMethodManager) systemService;
            if (inputMethodManager.isActive()) {
                inputMethodManager.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
