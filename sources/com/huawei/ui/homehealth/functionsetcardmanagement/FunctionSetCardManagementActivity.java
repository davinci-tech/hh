package com.huawei.ui.homehealth.functionsetcardmanagement;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.health.utils.functionsetcard.CardFlowInteractors;
import com.huawei.health.health.utils.functionsetcard.manager.model.CardConfig;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.httputils.pluginoperation.HttpResCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ResultCallback;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.homehealth.functionsetcardmanagement.FunctionSetCardManagementActivity;
import com.huawei.ui.homehealth.homeinterface.OnStartDragListener;
import com.huawei.ui.homehealth.util.FunctionSetCardMarketingUtil;
import defpackage.efb;
import defpackage.gmz;
import defpackage.jae;
import defpackage.jaf;
import defpackage.jbs;
import defpackage.koq;
import defpackage.nrt;
import defpackage.nrv;
import defpackage.nsf;
import defpackage.ohz;
import defpackage.oia;
import defpackage.oib;
import defpackage.ojs;
import defpackage.omz;
import defpackage.oun;
import defpackage.owi;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class FunctionSetCardManagementActivity extends BaseActivity implements OnStartDragListener {

    /* renamed from: a, reason: collision with root package name */
    private ArrayList<ojs> f9438a;
    private HealthTextView c;
    private FunctionSetCardManagementViewAdapter e;
    private oia f;
    private Context g;
    private HealthColumnSystem h;
    private CustomViewDialog i;
    private List<CardConfig> j;
    private ItemTouchHelper k;
    private FunctionSetCardMarketingUtil m;
    private boolean o;
    private StaggeredGridLayoutManager p;
    private HealthTextView q;
    private HealthSwitchButton r;
    private FunctionSetCardManagementNotShowViewAdapter s;
    private CustomTitleBar u;
    private StaggeredGridLayoutManager v;
    private FunctionSetCardManagementViewChineseAdapter x;
    private boolean d = false;
    private boolean b = false;
    private List<ojs> w = new ArrayList();
    private ArrayList<ojs> n = new ArrayList<>();
    private boolean l = true;
    private boolean t = false;
    private boolean y = false;
    private boolean ac = false;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("FunctionSetCardManagementActivity", "onCreate");
        this.g = this;
        if (efb.k()) {
            setContentView(R.layout.function_set_card_management_chinese);
        } else {
            setContentView(R.layout.function_set_card_management);
        }
        this.m = new FunctionSetCardMarketingUtil(this.g);
        g();
        j();
        if (efb.k()) {
            new ManagementBootPageHelper(BaseApplication.getContext()).showBootPage((LinearLayout) findViewById(R.id.extra_layout_container));
        }
    }

    @Override // com.huawei.ui.homehealth.homeinterface.OnStartDragListener
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        if (efb.k()) {
            return;
        }
        this.k.startDrag(viewHolder);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.t = true;
        omz.a().c();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        LogUtil.a("FunctionSetCardManagementActivity", "onStop()");
        if (efb.k()) {
            return;
        }
        f();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        LogUtil.a("FunctionSetCardManagementActivity", "onBackPressed()");
        if (efb.k()) {
            if (f()) {
                finish();
                return;
            }
            return;
        }
        super.onBackPressed();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        a();
    }

    public void a() {
        HealthColumnSystem healthColumnSystem = this.h;
        if (healthColumnSystem != null) {
            healthColumnSystem.e(this.g);
            StaggeredGridLayoutManager staggeredGridLayoutManager = this.v;
            if (staggeredGridLayoutManager != null) {
                staggeredGridLayoutManager.setSpanCount(this.h.f() / 2);
            }
            StaggeredGridLayoutManager staggeredGridLayoutManager2 = this.p;
            if (staggeredGridLayoutManager2 != null) {
                staggeredGridLayoutManager2.setSpanCount(this.h.f() / 2);
            }
        }
    }

    private void j() {
        LogUtil.a("FunctionSetCardManagementActivity", "initListView");
        cancelAdaptRingRegion();
        setViewSafeRegion(false, findViewById(R.id.card_management_relative_layout));
        if (efb.k()) {
            b();
        }
        HealthRecycleView healthRecycleView = (HealthRecycleView) findViewById(R.id.id_recycler_card_management);
        healthRecycleView.setOnTouchListener(new View.OnTouchListener() { // from class: com.huawei.ui.homehealth.functionsetcardmanagement.FunctionSetCardManagementActivity.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return FunctionSetCardManagementActivity.this.dbL_(view, motionEvent);
            }
        });
        if (efb.k()) {
            this.x = new FunctionSetCardManagementViewChineseAdapter(this.f9438a, this.g, this);
            HealthColumnSystem healthColumnSystem = new HealthColumnSystem(this.g, 1);
            this.h = healthColumnSystem;
            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(healthColumnSystem.f() / 2, 1);
            this.v = staggeredGridLayoutManager;
            healthRecycleView.setLayoutManager(staggeredGridLayoutManager);
            healthRecycleView.setAdapter(this.x);
            healthRecycleView.a(false);
            healthRecycleView.d(false);
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ManagementViewCardTouchHelperChineseCallBack(this.x, this.g));
            this.k = itemTouchHelper;
            itemTouchHelper.attachToRecyclerView(healthRecycleView);
            h();
            return;
        }
        this.e = new FunctionSetCardManagementViewAdapter(this.f9438a, this.g, this);
        healthRecycleView.setLayoutManager(new LinearLayoutManager(this.g));
        healthRecycleView.setAdapter(this.e);
        healthRecycleView.a(false);
        healthRecycleView.d(false);
        ItemTouchHelper itemTouchHelper2 = new ItemTouchHelper(new ManagementViewCardTouchHelperCallBack(this.e, this.g));
        this.k = itemTouchHelper2;
        itemTouchHelper2.attachToRecyclerView(healthRecycleView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean dbL_(View view, MotionEvent motionEvent) {
        if (motionEvent != null && motionEvent.getAction() == 1) {
            if (efb.k()) {
                if (this.x != null) {
                    LogUtil.a("FunctionSetCardManagementActivity", "mShowAdapter dispatchTouchEvent");
                    this.x.c();
                }
            } else if (this.e != null) {
                LogUtil.a("FunctionSetCardManagementActivity", "mAdapter dispatchTouchEvent");
                this.e.notifyDataSetChanged();
            }
        }
        return false;
    }

    private void h() {
        HealthRecycleView healthRecycleView = (HealthRecycleView) findViewById(R.id.id_recycler_card_management_not_show);
        this.s = new FunctionSetCardManagementNotShowViewAdapter(this.f9438a, this);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(this.h.f() / 2, 1);
        this.p = staggeredGridLayoutManager;
        healthRecycleView.setLayoutManager(staggeredGridLayoutManager);
        healthRecycleView.setAdapter(this.s);
        healthRecycleView.a(false);
        healthRecycleView.d(false);
    }

    private void b() {
        ImageView imageView = (ImageView) findViewById(R.id.left_bottom_line);
        if (nrt.a(this.g)) {
            imageView.setBackground(this.g.getResources().getDrawable(R.drawable._2131430768_res_0x7f0b0d70));
        } else {
            imageView.setBackground(this.g.getResources().getDrawable(R.drawable._2131430767_res_0x7f0b0d6f));
        }
        ImageView imageView2 = (ImageView) findViewById(R.id.right_bottom_line);
        if (nrt.a(this.g)) {
            imageView2.setBackground(this.g.getResources().getDrawable(R.drawable._2131431256_res_0x7f0b0f58));
        } else {
            imageView2.setBackground(this.g.getResources().getDrawable(R.drawable._2131431255_res_0x7f0b0f57));
        }
        ((HealthTextView) findViewById(R.id.show_card_title)).setText(this.g.getResources().getString(R.string._2130839593_res_0x7f020829));
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.recommend_text);
        this.q = healthTextView;
        healthTextView.setText(this.g.getResources().getString(R.string._2130839594_res_0x7f02082a));
        HealthSwitchButton healthSwitchButton = (HealthSwitchButton) findViewById(R.id.recommend_button);
        this.r = healthSwitchButton;
        healthSwitchButton.setOnClickListener(new View.OnClickListener() { // from class: ojt
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FunctionSetCardManagementActivity.this.dbM_(view);
            }
        });
        this.c = (HealthTextView) findViewById(R.id.card_management_bottom_text);
        t();
        n();
    }

    public /* synthetic */ void dbM_(View view) {
        if (this.r.isChecked()) {
            LogUtil.a("FunctionSetCardManagementActivity", "open switch");
            p();
        } else {
            LogUtil.a("FunctionSetCardManagementActivity", "close switch");
            owi.c(this.g, false, this.f.h(), 1);
            k();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void n() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.card_management_title_layout);
        this.u = customTitleBar;
        customTitleBar.setTitleBarBackgroundColor(this.g.getResources().getColor(R.color._2131296690_res_0x7f0901b2));
        this.u.setRightButtonClickable(true);
        if (nrt.a(this.g)) {
            this.u.setRightButtonDrawable(this.g.getDrawable(R.drawable._2131428284_res_0x7f0b03bc), nsf.h(R.string._2130841395_res_0x7f020f33));
        } else {
            this.u.setRightButtonDrawable(this.g.getDrawable(R.drawable._2131428283_res_0x7f0b03bb), nsf.h(R.string._2130841395_res_0x7f020f33));
        }
        this.u.setRightButtonVisibility(0);
        this.u.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.homehealth.functionsetcardmanagement.FunctionSetCardManagementActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("FunctionSetCardManagementActivity", "mTitleBar right button is clicked, mIsEditState: ", Boolean.valueOf(FunctionSetCardManagementActivity.this.l), ", mCardEditedByUser: ", Boolean.valueOf(FunctionSetCardManagementActivity.this.d));
                if (FunctionSetCardManagementActivity.this.l) {
                    if (FunctionSetCardManagementActivity.this.d && FunctionSetCardManagementActivity.this.l()) {
                        FunctionSetCardManagementActivity.this.o = false;
                        FunctionSetCardManagementActivity.this.b = true;
                        FunctionSetCardManagementActivity.this.f.a(false);
                        FunctionSetCardManagementActivity.this.d = false;
                    }
                    FunctionSetCardManagementActivity.this.d(false);
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.u.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.homehealth.functionsetcardmanagement.FunctionSetCardManagementActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("FunctionSetCardManagementActivity", "back");
                if (FunctionSetCardManagementActivity.this.f()) {
                    LogUtil.a("FunctionSetCardManagementActivity", "isFinish");
                    FunctionSetCardManagementActivity.this.finish();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    public void d(boolean z) {
        if (z) {
            this.d = false;
        }
        this.l = z;
        if (!z) {
            c(this.w, this.x.d());
            owi.b(this.g, 1, this.o, this.n, d(), this.w);
            c(this.n, this.w);
            this.y = this.o;
            String b = SharedPreferenceManager.b(this.g, Integer.toString(10000), "personalized_recommend");
            if (!this.ac && "1".equals(b)) {
                this.ac = true;
            }
            LogUtil.a("FunctionSetCardManagementActivity", "mTmpIsUpdateByMarketing: ", Boolean.valueOf(this.y), ", mTmpPersonSwitch: ", Boolean.valueOf(this.ac));
        }
        t();
        if (z) {
            this.u.setRightButtonVisibility(0);
        } else {
            this.u.setRightButtonVisibility(8);
        }
        this.x.a();
        this.x.b(z);
        this.s.b(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int d() {
        ArrayList<ojs> arrayList = this.n;
        int i = 0;
        if (arrayList == null) {
            return 0;
        }
        Iterator<ojs> it = arrayList.iterator();
        while (it.hasNext()) {
            if (it.next().c() == 1) {
                i++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<ojs> c(List<ojs> list, List<ojs> list2) {
        list.clear();
        for (int i = 0; i < list2.size(); i++) {
            ojs ojsVar = list2.get(i);
            if (ojsVar != null) {
                list.add(ojsVar.b());
            }
        }
        return list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean f() {
        this.f.e("FunctionSetCardManagementActivity", "quit card edit start save changes");
        boolean l = l();
        LogUtil.a("FunctionSetCardManagementActivity", "isCardListEdited = ", Boolean.valueOf(l));
        if (l) {
            LogUtil.a("FunctionSetCardManagementActivity", "mCardManager.saveEditCards(mCardDataList) mIsEditState: ", Boolean.valueOf(this.l));
            if (efb.k() && this.l) {
                q();
                return false;
            }
            this.f.e(e(), this.b);
        }
        this.f.d("FunctionSetCardManagementActivity", "quit card edit end save changes");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<ojs> e() {
        if (efb.k()) {
            FunctionSetCardManagementViewChineseAdapter functionSetCardManagementViewChineseAdapter = this.x;
            if (functionSetCardManagementViewChineseAdapter != null) {
                return functionSetCardManagementViewChineseAdapter.d();
            }
        } else {
            FunctionSetCardManagementViewAdapter functionSetCardManagementViewAdapter = this.e;
            if (functionSetCardManagementViewAdapter != null) {
                return functionSetCardManagementViewAdapter.d();
            }
        }
        return null;
    }

    private List<ojs> i() {
        if (!efb.k() || this.x == null) {
            return null;
        }
        return this.w;
    }

    private void q() {
        LogUtil.a("FunctionSetCardManagementActivity", "showNoticeDialog");
        CustomViewDialog customViewDialog = this.i;
        if (customViewDialog != null && customViewDialog.isShowing()) {
            LogUtil.h("FunctionSetCardManagementActivity", "mAuthDialog isShowing");
            return;
        }
        View inflate = View.inflate(this.g, R.layout.function_set_management_dialog_des, null);
        ((HealthTextView) inflate.findViewById(R.id.dialog_text_alert_message)).setText(this.g.getResources().getString(R.string._2130839590_res_0x7f020826));
        CustomViewDialog e = new CustomViewDialog.Builder(this.g).a(this.g.getResources().getString(R.string._2130839589_res_0x7f020825)).czg_(inflate).czd_(this.g.getResources().getString(R.string._2130839592_res_0x7f020828), new View.OnClickListener() { // from class: com.huawei.ui.homehealth.functionsetcardmanagement.FunctionSetCardManagementActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("FunctionSetCardManagementActivity", "NegativeButton is clicked");
                FunctionSetCardManagementActivity.this.o();
                FunctionSetCardManagementActivity.this.i.dismiss();
                FunctionSetCardManagementActivity.this.finish();
                FunctionSetCardManagementActivity.this.f.d("FunctionSetCardManagementActivity", "quit card edit end save changes");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czf_(this.g.getResources().getString(R.string.IDS_device_measureactivity_result_save), new View.OnClickListener() { // from class: com.huawei.ui.homehealth.functionsetcardmanagement.FunctionSetCardManagementActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("FunctionSetCardManagementActivity", "PositiveButton is clicked");
                FunctionSetCardManagementActivity.this.x.a();
                if ((FunctionSetCardManagementActivity.this.d && FunctionSetCardManagementActivity.this.l()) || FunctionSetCardManagementActivity.this.b) {
                    FunctionSetCardManagementActivity.this.b = true;
                }
                String b = SharedPreferenceManager.b(FunctionSetCardManagementActivity.this.g, Integer.toString(10000), "personalized_recommend");
                if (!FunctionSetCardManagementActivity.this.ac) {
                    if ("1".equals(b)) {
                        FunctionSetCardManagementActivity.this.ac = true;
                        FunctionSetCardManagementActivity functionSetCardManagementActivity = FunctionSetCardManagementActivity.this;
                        functionSetCardManagementActivity.d(functionSetCardManagementActivity.ac, FunctionSetCardManagementActivity.this.ac ? "1" : "0");
                    }
                }
                FunctionSetCardManagementActivity.this.f.e(FunctionSetCardManagementActivity.this.e(), FunctionSetCardManagementActivity.this.b);
                FunctionSetCardManagementActivity.this.i.dismiss();
                FunctionSetCardManagementActivity.this.finish();
                owi.b(FunctionSetCardManagementActivity.this.g, 1, FunctionSetCardManagementActivity.this.o, FunctionSetCardManagementActivity.this.n, FunctionSetCardManagementActivity.this.d(), FunctionSetCardManagementActivity.this.e());
                FunctionSetCardManagementActivity functionSetCardManagementActivity2 = FunctionSetCardManagementActivity.this;
                functionSetCardManagementActivity2.c(functionSetCardManagementActivity2.n, (List<ojs>) FunctionSetCardManagementActivity.this.e());
                FunctionSetCardManagementActivity.this.f.d("FunctionSetCardManagementActivity", "quit card edit end save changes");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        this.i = e;
        e.setCancelable(false);
        this.i.show();
        LogUtil.a("FunctionSetCardManagementActivity", ParamConstants.CallbackMethod.ON_SHOW);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        LogUtil.a("FunctionSetCardManagementActivity", "saveTmpStatus, mCardEditedByUserTotal: ", Boolean.valueOf(this.b), ", mTmpIsUpdateByMarketing: ", Boolean.valueOf(this.y), ", mTmpPersonSwitch: ", Boolean.valueOf(this.ac));
        this.f.e(i(), this.b);
        this.f.a(this.y);
        boolean z = this.ac;
        d(z, z ? "1" : "0");
    }

    private void p() {
        LogUtil.a("FunctionSetCardManagementActivity", "showSwitchDialog");
        CustomViewDialog customViewDialog = this.i;
        if (customViewDialog != null && customViewDialog.isShowing()) {
            LogUtil.h("FunctionSetCardManagementActivity", "mAuthDialog isShowing");
            return;
        }
        View inflate = View.inflate(this.g, R.layout.function_set_management_dialog_des, null);
        ((HealthTextView) inflate.findViewById(R.id.dialog_text_alert_message)).setText(this.g.getResources().getString(R.string._2130839591_res_0x7f020827));
        CustomViewDialog e = new CustomViewDialog.Builder(this.g).czg_(inflate).czd_(this.g.getResources().getString(R.string._2130839505_res_0x7f0207d1), new View.OnClickListener() { // from class: com.huawei.ui.homehealth.functionsetcardmanagement.FunctionSetCardManagementActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("FunctionSetCardManagementActivity", "NegativeButton is clicked");
                FunctionSetCardManagementActivity.this.b(false);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czf_(this.g.getResources().getString(R.string._2130837520_res_0x7f020010), new View.OnClickListener() { // from class: com.huawei.ui.homehealth.functionsetcardmanagement.FunctionSetCardManagementActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("FunctionSetCardManagementActivity", "PositiveButton is clicked");
                owi.c(FunctionSetCardManagementActivity.this.g, true, FunctionSetCardManagementActivity.this.f.h(), 1);
                FunctionSetCardManagementActivity.this.c();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        this.i = e;
        e.setCancelable(false);
        this.i.show();
        LogUtil.a("FunctionSetCardManagementActivity", ParamConstants.CallbackMethod.ON_SHOW);
    }

    private void k() {
        this.m.e(new HiDataReadResultListener() { // from class: com.huawei.ui.homehealth.functionsetcardmanagement.FunctionSetCardManagementActivity.15
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                LogUtil.a("FunctionSetCardManagementActivity", "errorCode: ", Integer.valueOf(i), ", object: ", obj);
                if (i == 0) {
                    JSONObject e = FunctionSetCardManagementActivity.this.m.e(obj);
                    if (e != null) {
                        FunctionSetCardManagementActivity.this.a(e);
                        return;
                    }
                    LogUtil.a("FunctionSetCardManagementActivity", "config is null");
                    FunctionSetCardManagementActivity functionSetCardManagementActivity = FunctionSetCardManagementActivity.this;
                    functionSetCardManagementActivity.a(functionSetCardManagementActivity.f.i());
                    return;
                }
                FunctionSetCardManagementActivity functionSetCardManagementActivity2 = FunctionSetCardManagementActivity.this;
                functionSetCardManagementActivity2.a(functionSetCardManagementActivity2.f.i());
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
                LogUtil.a("FunctionSetCardManagementActivity", "getUserConfig intentType ", Integer.valueOf(i), " object ", obj, " errorCode ", Integer.valueOf(i2), " anchor ", Integer.valueOf(i3));
                FunctionSetCardManagementActivity functionSetCardManagementActivity = FunctionSetCardManagementActivity.this;
                functionSetCardManagementActivity.a(functionSetCardManagementActivity.f.i());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(JSONObject jSONObject) {
        LogUtil.a("FunctionSetCardManagementActivity", "updateCardByUserConfig");
        if (jSONObject == null) {
            LogUtil.a("FunctionSetCardManagementActivity", "JSONObject is null");
            a(this.f.i());
            return;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("userCardOrders");
        if (optJSONArray == null) {
            LogUtil.a("FunctionSetCardManagementActivity", "array is null");
            a(this.f.i());
            return;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            if (optJSONArray.opt(i) instanceof JSONObject) {
                JSONObject jSONObject2 = (JSONObject) optJSONArray.opt(i);
                String optString = jSONObject2.optString("cardId");
                String optString2 = jSONObject2.optString("showFlag");
                if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(optString2)) {
                    d(optString, "1".equals(optString2), i);
                }
            }
        }
        c(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ohz ohzVar) {
        LogUtil.a("FunctionSetCardManagementActivity", "updateCardByUserSp");
        if (ohzVar == null || koq.b(ohzVar.d())) {
            LogUtil.a("FunctionSetCardManagementActivity", "cardConfig is invalid");
            b(true);
            return;
        }
        LinkedList<CardConfig> d = ohzVar.d();
        if (koq.b(d)) {
            LogUtil.a("FunctionSetCardManagementActivity", "cardConfigLinkedList is invalid");
            b(true);
            return;
        }
        for (int i = 0; i < d.size(); i++) {
            CardConfig cardConfig = d.get(i);
            if (cardConfig != null) {
                d(cardConfig.getCardId(), cardConfig.isShow(), i);
            }
        }
        c(true);
    }

    private void d(String str, boolean z, int i) {
        ojs c = c(str);
        if (c == null) {
            return;
        }
        c.b(i);
        c.d(z ? 1 : 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final boolean z) {
        if (!HandlerExecutor.b()) {
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.homehealth.functionsetcardmanagement.FunctionSetCardManagementActivity.13
                @Override // java.lang.Runnable
                public void run() {
                    FunctionSetCardManagementActivity.this.b(z);
                }
            });
            return;
        }
        LogUtil.a("FunctionSetCardManagementActivity", "updateSwitchButton isButtonOpen: ", Boolean.valueOf(z));
        this.o = z;
        this.r.setChecked(z);
        this.f.a(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.homehealth.functionsetcardmanagement.FunctionSetCardManagementActivity.4
            @Override // java.lang.Runnable
            public void run() {
                jbs.a(BaseApplication.getContext()).a("function_card", new jaf(), new ResultCallback<jae>() { // from class: com.huawei.ui.homehealth.functionsetcardmanagement.FunctionSetCardManagementActivity.4.1
                    @Override // com.huawei.networkclient.ResultCallback
                    /* renamed from: c, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(jae jaeVar) {
                        FunctionSetCardManagementActivity.this.c(jaeVar);
                    }

                    @Override // com.huawei.networkclient.ResultCallback
                    public void onFailure(Throwable th) {
                        LogUtil.b("FunctionSetCardManagementActivity", "get strategy error:", ExceptionUtils.d(th));
                        FunctionSetCardManagementActivity.this.b(false);
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(jae jaeVar) {
        if (jaeVar == null) {
            LogUtil.h("FunctionSetCardManagementActivity", "rsp is null.");
            b(false);
            return;
        }
        LogUtil.a("FunctionSetCardManagementActivity", "rsp: ", jaeVar.toString());
        if (jaeVar.getResultCode().intValue() != 0) {
            LogUtil.h("FunctionSetCardManagementActivity", "getStrategy rsp resultCode is ", jaeVar.getResultCode());
            b(false);
            return;
        }
        if (koq.b(jaeVar.a()) || koq.b(jaeVar.e()) || koq.b(jaeVar.b())) {
            LogUtil.h("FunctionSetCardManagementActivity", "getStrategy rsp data empty");
            b(false);
            return;
        }
        String str = jaeVar.b().get(0);
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("FunctionSetCardManagementActivity", "getStrategyName empty");
            b(false);
            return;
        }
        LogUtil.a("FunctionSetCardManagementActivity", "json: ", nrv.a(jaeVar));
        if (str.equals("[B]")) {
            LogUtil.a("FunctionSetCardManagementActivity", "test B");
            m();
        } else {
            LogUtil.a("FunctionSetCardManagementActivity", "test A");
            b(false);
        }
    }

    private void m() {
        this.m.a(new HttpResCallback() { // from class: com.huawei.ui.homehealth.functionsetcardmanagement.FunctionSetCardManagementActivity.2
            @Override // com.huawei.hwcommonmodel.utils.httputils.pluginoperation.HttpResCallback
            public void onFinished(int i, String str) {
                LogUtil.a("FunctionSetCardManagementActivity", "resCode: ", Integer.valueOf(i), ", response: ", str);
                if (TextUtils.isEmpty(str)) {
                    LogUtil.b("FunctionSetCardManagementActivity", "response is null");
                    FunctionSetCardManagementActivity.this.b(false);
                    return;
                }
                ArrayList arrayList = new ArrayList();
                try {
                    JSONArray optJSONArray = new JSONObject(str).optJSONArray("sortedCardIds");
                    if (optJSONArray == null) {
                        LogUtil.b("FunctionSetCardManagementActivity", "array is null");
                        FunctionSetCardManagementActivity.this.b(false);
                        return;
                    }
                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                        Object obj = optJSONArray.get(i2);
                        if (obj instanceof String) {
                            arrayList.add((String) obj);
                        }
                    }
                    if (!arrayList.isEmpty()) {
                        if (!FunctionSetCardManagementActivity.this.t) {
                            FunctionSetCardManagementActivity.this.d(arrayList);
                            FunctionSetCardManagementActivity.this.c(false);
                            return;
                        } else {
                            LogUtil.a("FunctionSetCardManagementActivity", "activity is destroyed");
                            FunctionSetCardManagementActivity.this.b(false);
                            return;
                        }
                    }
                    LogUtil.b("FunctionSetCardManagementActivity", "list is null");
                    FunctionSetCardManagementActivity.this.b(false);
                } catch (JsonSyntaxException | JSONException unused) {
                    LogUtil.b("FunctionSetCardManagementActivity", "JsonSyntaxException");
                    FunctionSetCardManagementActivity.this.b(false);
                }
            }
        });
    }

    private void b(List<String> list) {
        String name = CardFlowInteractors.CardNameConstants.THREE_CIRCLE_CARD.getName();
        if ("threeLeafCard".equals(oun.a()) && list.contains("HEALTH_MODEL_CARD_KEY_NEW") && !list.contains(name)) {
            list.set(list.indexOf("HEALTH_MODEL_CARD_KEY_NEW"), name);
        }
        if ("threeCircleCard".equals(oun.a()) && list.contains(name) && !list.contains("HEALTH_MODEL_CARD_KEY_NEW")) {
            list.set(list.indexOf(name), "HEALTH_MODEL_CARD_KEY_NEW");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<String> list) {
        b(list);
        b(list);
        LogUtil.a("FunctionSetCardManagementActivity", "updateCardDataListByMarketing, list: ", list);
        ArrayList arrayList = new ArrayList();
        boolean d = oib.d();
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            if (!TextUtils.isEmpty(str) && c(str) != null && (!"PHYSIOLOGICAL_CYCLE_CARD_KEY_NEW".equals(str) || d)) {
                arrayList.add(str);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < this.f9438a.size(); i2++) {
            String d2 = this.f9438a.get(i2).d();
            if (!arrayList.contains(d2)) {
                arrayList2.add(d2);
            }
        }
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            ojs c = c((String) arrayList.get(i3));
            if (c != null) {
                c.b(i3);
                c.d(1);
            }
        }
        int size = arrayList.size();
        for (int i4 = 0; i4 < arrayList2.size(); i4++) {
            ojs c2 = c((String) arrayList2.get(i4));
            if (c2 != null) {
                c2.b(i4 + size);
                c2.d(2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z) {
        Collections.sort(this.f9438a, new Comparator<ojs>() { // from class: com.huawei.ui.homehealth.functionsetcardmanagement.FunctionSetCardManagementActivity.5
            @Override // java.util.Comparator
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public int compare(ojs ojsVar, ojs ojsVar2) {
                return ojsVar.g() - ojsVar2.g();
            }
        });
        LogUtil.a("FunctionSetCardManagementActivity", "sortListAndRefreshView, mCardDataList: ", this.f9438a);
        this.x.d(this.f9438a);
        this.s.e(this.f9438a);
        if (!z) {
            d(!z, z ? "0" : "1");
        }
        boolean z2 = !z;
        b(z2);
        this.f.b(z2);
    }

    private ojs c(String str) {
        for (int i = 0; i < this.f9438a.size(); i++) {
            if (str.equals(this.f9438a.get(i).d())) {
                return this.f9438a.get(i);
            }
        }
        return null;
    }

    private void t() {
        if (this.o) {
            LogUtil.a("FunctionSetCardManagementActivity", "mRadioButton is opening");
            this.r.setChecked(true);
        } else {
            LogUtil.a("FunctionSetCardManagementActivity", "mRadioButton is closing");
            this.r.setChecked(false);
        }
        if (this.f.n()) {
            this.r.setVisibility(0);
            this.r.setClickable(true);
            this.q.setVisibility(0);
        } else {
            this.r.setVisibility(4);
            this.r.setClickable(false);
            this.q.setVisibility(4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z, String str) {
        gmz d = gmz.d();
        SharedPreferenceManager.e(this.g, Integer.toString(10000), "personalized_recommend", str, new StorageParams());
        d.c(12, z, String.valueOf(12), (IBaseResponseCallback) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean l() {
        List<ojs> e = e();
        if (e == null) {
            LogUtil.a("FunctionSetCardManagementActivity", "cardDataList is null");
            return false;
        }
        int size = e.size();
        if (size != this.j.size()) {
            return true;
        }
        LogUtil.a("FunctionSetCardManagementActivity", "cardDataList, getAdapter.size = ", Integer.valueOf(size));
        for (int i = 0; i < e.size(); i++) {
            if (!this.j.get(i).getCardId().equalsIgnoreCase(e.get(i).d()) || this.j.get(i).getShowFlag() != e.get(i).c()) {
                return true;
            }
        }
        return false;
    }

    private void g() {
        LogUtil.a("FunctionSetCardManagementActivity", "initData()");
        oia c = oia.c();
        this.f = c;
        boolean h = c.h();
        this.o = h;
        this.y = h;
        this.ac = "1".equals(SharedPreferenceManager.b(this.g, Integer.toString(10000), "personalized_recommend"));
        LogUtil.a("FunctionSetCardManagementActivity", "init mIsUpdateByMarketing: ", Boolean.valueOf(this.o), ", mTmpPersonSwitch: ", Boolean.valueOf(this.ac));
        this.f9438a = new ArrayList<>(10);
        this.f.c("FunctionSetCardManagementActivity", "enter card Edit");
        List<CardConfig> b = this.f.b();
        this.j = b;
        if (b == null || b.size() == 0) {
            return;
        }
        LogUtil.a("FunctionSetCardManagementActivity", "mCurrentCardConfigs: ", this.j);
        for (int i = 0; i < this.j.size(); i++) {
            CardConfig cardConfig = this.j.get(i);
            if (efb.k()) {
                this.f9438a.add(new ojs(this.g, d(cardConfig), i, cardConfig.getShowFlag(), cardConfig.getCardId(), b(cardConfig.getCardId())));
            } else {
                this.f9438a.add(new ojs(this.g, d(cardConfig), i, cardConfig.getShowFlag(), cardConfig.getCardId()));
            }
        }
        LogUtil.a("FunctionSetCardManagementActivity", "mCardDataList: ", this.f9438a);
        c(this.n, this.f9438a);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private String b(String str) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -1680798863:
                if (str.equals("BLOODPRESSURE_CARD_KEY_NEW")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1499674596:
                if (str.equals("Health_Record_CARD_KEY_NEW")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1432856068:
                if (str.equals("STRESS_CARD_KEY_NEW")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1003199015:
                if (str.equals("SLEEP_CARD_KEY_NEW")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -914184908:
                if (str.equals("BLOODSUGAR_CARD_KEY_NEW")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -787142052:
                if (str.equals("TEMPERATURE_CARD_KEY_NEW")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -532597256:
                if (str.equals("PHYSIOLOGICAL_CYCLE_CARD_KEY_NEW")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 390072694:
                if (str.equals("BLOODOXYGEN_CARD_KEY_NEW")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 737757578:
                if (str.equals("HEALTH_MODEL_CARD_KEY_NEW")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 1330694072:
                if (str.equals("WEIGHT_CARD_KEY_NEW")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 1518049390:
                if (str.equals("HEARTRATE_CARD_KAY_NEW")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 1810459679:
                if (str.equals("THREE_CIRCLE_CARD_KEY_NEW")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case 1956046353:
                if (str.equals("SPORTS_CARD_KEY_NEW")) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return "marketing_default_img_bloodpressure";
            case 1:
                return "health_card_default_img_small";
            case 2:
                return "marketing_default_img_pressure";
            case 3:
                return "marketing_default_img_sleep";
            case 4:
                return "marketing_default_img_bloodsugar";
            case 5:
                return "marketing_default_img_temperature";
            case 6:
                return "marketing_default_img_physiological_cycle";
            case 7:
                return "marketing_default_img_bloodoxygen";
            case '\b':
                return "health_life_default_img_small";
            case '\t':
                return "marketing_default_img_weight";
            case '\n':
                return "marketing_default_img_heartrate";
            case 11:
                return "ic_three_circle";
            case '\f':
                return "sports_record_small_img_default";
            default:
                return "";
        }
    }

    private String d(CardConfig cardConfig) {
        return c(cardConfig);
    }

    private String c(CardConfig cardConfig) {
        Resources resources = this.g.getResources();
        String cardNameRes = cardConfig.getCardNameRes();
        if (cardNameRes == null) {
            return "";
        }
        if (Utils.o() && CardFlowInteractors.CardNameConstants.TEMPERATURE_CARD.getName().equals(cardConfig.getCardId())) {
            cardNameRes = "IDS_health_skin_temperature";
        } else if (Utils.o() && CardFlowInteractors.CardNameConstants.WEIGHT_CARD.getName().equals(cardConfig.getCardId())) {
            cardNameRes = "IDS_hw_show_main_home_page_weight";
        } else if ("HEALTH_MODEL_CARD_KEY_NEW".equals(cardConfig.getCardId())) {
            cardNameRes = "IDS_health_clover_title";
        } else if (Utils.o() && CardFlowInteractors.CardNameConstants.HEALTH_RECORD_CARD.getName().equals(cardConfig.getCardId())) {
            cardNameRes = "IDS_health_examination";
        } else if (CardFlowInteractors.CardNameConstants.STRESS_CARD.getName().equals(cardConfig.getCardId()) && efb.e()) {
            cardNameRes = "IDS_hw_show_main_home_page_card_emotion_title";
        } else {
            LogUtil.a("FunctionSetCardManagementActivity", "getEmptyCardTitle cardNameRes is other");
        }
        int identifier = resources.getIdentifier(cardNameRes, "string", this.g.getPackageName());
        return identifier > 0 ? this.g.getResources().getString(identifier) : "";
    }

    public void d(int i, int i2) {
        this.d = true;
        this.x.c(i);
    }

    public void b(int i, int i2) {
        this.d = true;
        this.s.d(i);
    }

    public void a(int i, int i2) {
        this.d = true;
    }

    public void a(List<ojs> list) {
        this.s.e(list);
    }
}
