package com.huawei.ui.main.stories.history;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.ui.sporttypeconfig.bean.SportGroupData;
import com.huawei.healthcloud.plugintrack.ui.sporttypeconfig.bean.SportTypeData;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewScrollInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.adapter.BaseRecyclerAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.searchview.HealthSearchView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.history.adapter.LeftListSportAdapter;
import com.huawei.ui.main.stories.history.adapter.RightListSportAdapter;
import com.huawei.ui.main.stories.history.adapter.SearchTypeAdapter;
import com.huawei.ui.main.stories.history.model.SearchSportThreadManager;
import com.huawei.ui.main.stories.history.model.SpacesItemDecoration;
import com.huawei.ui.main.stories.template.BaseActivity;
import defpackage.bkz;
import defpackage.hkc;
import defpackage.hln;
import defpackage.ixx;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.rdh;
import defpackage.rdi;
import defpackage.rdm;
import defpackage.rds;
import defpackage.rdy;
import defpackage.trg;
import health.compact.a.LanguageUtil;
import health.compact.a.util.LogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class ChooseSportTypeActivity extends BaseActivity implements SearchSportThreadManager.SearchCallback, RightListSportAdapter.SportOnSelectPositionListener {

    /* renamed from: a, reason: collision with root package name */
    private CustomTitleBar f10277a;
    private int ad;
    private Context b;
    private RelativeLayout c;
    private e d;
    private hln f;
    private HealthRecycleView h;
    private LeftListSportAdapter i;
    private ImageView k;
    private HealthRecycleView l;
    private SearchTypeAdapter m;
    private RightListSportAdapter o;
    private HealthTextView p;
    private List<rdy> q;
    private String r;
    private LinearLayout s;
    private SearchSportThreadManager u;
    private LinearLayout v;
    private HealthRecycleView w;
    private LinearLayout x;
    private HealthSearchView y;
    private String z;
    private Map<Integer, Integer> g = new HashMap(16);
    private List<rdh> j = new ArrayList(16);
    private List<rdi> n = new ArrayList(16);
    private List<rdy> t = new ArrayList(16);
    private boolean e = false;

    @Override // com.huawei.ui.main.stories.history.model.SearchSportThreadManager.SearchCallback
    public void onSearchResult(List<rdy> list) {
        LogUtil.d("Track_SportMotionTypeActivity", "onSearchResult");
        Message obtain = Message.obtain();
        obtain.what = 1;
        obtain.obj = list;
        this.d.sendMessage(obtain);
    }

    @Override // com.huawei.ui.main.stories.history.adapter.RightListSportAdapter.SportOnSelectPositionListener
    public void selectedPosition(int i, int i2) {
        if (trg.a(this.n, i)) {
            LogUtil.c("Track_SportMotionTypeActivity", "selectedPosition parentPosition is out of bounds.");
            return;
        }
        rdi rdiVar = this.n.get(i);
        if (rdiVar == null) {
            LogUtil.c("Track_SportMotionTypeActivity", "selectedPosition motionTypeItem is null.");
            return;
        }
        List<rdy> c = rdiVar.c();
        if (trg.a(c, i2)) {
            LogUtil.c("Track_SportMotionTypeActivity", "selectedPosition position is out of typeItemList.");
        } else {
            c(c.get(i2));
        }
    }

    protected static class e extends Handler {
        WeakReference<ChooseSportTypeActivity> d;

        e(ChooseSportTypeActivity chooseSportTypeActivity) {
            this.d = new WeakReference<>(chooseSportTypeActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            ChooseSportTypeActivity chooseSportTypeActivity = this.d.get();
            if (chooseSportTypeActivity == null || message == null) {
                LogUtil.e("Track_SportMotionTypeActivity", "handleMessage mActivity is null");
            } else {
                if (message.what == 1) {
                    if (message.obj instanceof List) {
                        chooseSportTypeActivity.d((List) message.obj);
                        return;
                    }
                    return;
                }
                LogUtil.c("Track_SportMotionTypeActivity", "other message");
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b = this;
        this.d = new e(this);
        setContentView(R.layout.activity_sport_motion_type);
        this.u = SearchSportThreadManager.b();
        this.f = hln.c(this);
        Intent intent = getIntent();
        if (intent != null) {
            this.ad = intent.getIntExtra("sportTypeId", 0);
            this.z = intent.getStringExtra("sportGroupTypeId");
        }
        e();
        j();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<rdy> list) {
        this.q.clear();
        if (list.isEmpty()) {
            LogUtil.d("Track_SportMotionTypeActivity", "deviceListData is empty");
            SearchTypeAdapter searchTypeAdapter = this.m;
            if (searchTypeAdapter != null) {
                searchTypeAdapter.notifyDataSetChanged();
            }
            this.p.setText("");
            this.p.setVisibility(8);
            this.w.setVisibility(8);
            return;
        }
        if (this.m == null) {
            SearchTypeAdapter searchTypeAdapter2 = new SearchTypeAdapter(this.q);
            this.m = searchTypeAdapter2;
            this.w.setAdapter(searchTypeAdapter2);
        }
        this.p.setVisibility(0);
        this.w.setVisibility(0);
        this.q.addAll(list);
        this.m.a(this.r);
        this.m.notifyDataSetChanged();
        this.p.setText(getResources().getQuantityString(R.plurals._2130903136_res_0x7f030060, this.q.size(), Integer.valueOf(this.q.size())));
        this.m.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() { // from class: com.huawei.ui.main.stories.history.ChooseSportTypeActivity.3
            @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter.OnItemClickListener
            public void onItemClicked(RecyclerHolder recyclerHolder, int i, Object obj) {
                if (obj instanceof rdy) {
                    ChooseSportTypeActivity.this.a(i);
                    rdy rdyVar = (rdy) obj;
                    ChooseSportTypeActivity.this.c(rdyVar);
                    ChooseSportTypeActivity.this.c(2, rdyVar.a());
                    ChooseSportTypeActivity.this.d();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        for (int i2 = 0; i2 < this.q.size(); i2++) {
            if (i2 == i) {
                this.q.get(i2).d(true);
            } else {
                this.q.get(i2).d(false);
            }
        }
        this.m.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(rdy rdyVar) {
        if (rdyVar == null) {
            LogUtil.c("Track_SportMotionTypeActivity", "setSportMotionResults item is null.");
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("itemNameId", rdyVar.c());
        intent.putExtra("resIconName", rdyVar.f());
        intent.putExtra("sportTypeId", rdyVar.a());
        intent.putExtra("sportGroupTypeId", rdyVar.b());
        intent.putExtra("distanceFlag", rdyVar.j());
        intent.putExtra("typeMet", rdyVar.e());
        setResult(-1, intent);
        finish();
    }

    private void e() {
        this.j.clear();
        this.n.clear();
        this.t.clear();
        SportGroupData a2 = this.f.a();
        SportTypeData b = this.f.b();
        if (a2.getVersion().equals("0") || b.getVersion().equals("0")) {
            LogUtil.c("Track_SportMotionTypeActivity", "sportGroupData or sportTypeData is null");
            return;
        }
        for (int i = 0; i < a2.getSportTypeGroups().size(); i++) {
            rdh rdhVar = new rdh();
            rdhVar.c(rds.e(a2.getSportTypeGroups().get(i).getGroupNameRse()));
            String groupId = a2.getSportTypeGroups().get(i).getGroupId();
            rdhVar.a(groupId.equals(this.z));
            rdhVar.c(groupId);
            this.j.add(rdhVar);
        }
        a();
        d(this.j, b);
        for (int i2 = 0; i2 < this.n.size(); i2++) {
            this.t.addAll(this.n.get(i2).c());
        }
        b();
    }

    private void a() {
        if (trg.d(rds.b(this.ad, this.z))) {
            LogUtil.c("Track_SportMotionTypeActivity", "handleRecentyAdd typeItems is null");
            return;
        }
        rdh rdhVar = new rdh();
        rdhVar.c(R$string.IDS_type_motion_add);
        rdhVar.c("recentlyAdded");
        this.j.add(0, rdhVar);
    }

    private void d(List<rdh> list, SportTypeData sportTypeData) {
        for (int i = 0; i < list.size(); i++) {
            rdi rdiVar = new rdi();
            if (list.get(i).b().equals("recentlyAdded")) {
                rdiVar.b(list.get(i).a());
                rdiVar.a(i);
                rdiVar.c(rds.b(this.ad, this.z));
            } else {
                rdiVar.b(list.get(i).a());
                rdiVar.a(i);
                rdiVar.c(b(sportTypeData, this.j.get(i).b()));
            }
            this.n.add(rdiVar);
        }
    }

    private List<rdy> b(SportTypeData sportTypeData, String str) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < sportTypeData.getSportTypeInfos().size(); i++) {
            if (!str.equals(sportTypeData.getSportTypeInfos().get(i).getGroup()) || rds.d(sportTypeData.getSportTypeInfos().get(i).getSportTypeId())) {
                LogUtil.c("Track_SportMotionTypeActivity", "groupId is not equals ,triathlon or golf is not supported");
            } else {
                rdy rdyVar = new rdy();
                rdyVar.c(str);
                rdyVar.a(sportTypeData.getSportTypeInfos().get(i).getSportTypeId());
                rdyVar.b(sportTypeData.getSportTypeInfos().get(i).getMet());
                rdyVar.d(sportTypeData.getSportTypeInfos().get(i).getSportTypeId() == this.ad && str.equals(this.z));
                rdyVar.c(rds.e(sportTypeData.getSportTypeInfos().get(i).getSportTypeRes()));
                rdyVar.a(sportTypeData.getSportTypeInfos().get(i).getHistoryList().getItemImg());
                rdyVar.dMB_(sportTypeData.getSportTypeInfos().get(i).getHistoryList().getItemDrawable());
                rdyVar.b(hkc.d(sportTypeData.getSportTypeInfos().get(i).getSportTypeId()));
                arrayList.add(rdyVar);
            }
        }
        return arrayList;
    }

    private void j() {
        CustomTitleBar customTitleBar = (CustomTitleBar) nsy.cMc_(this, R.id.search_custom_title);
        this.f10277a = customTitleBar;
        customTitleBar.setTitleBarBackgroundColor(getResources().getColor(R.color._2131296690_res_0x7f0901b2));
        this.y = (HealthSearchView) nsy.cMc_(this, R.id.device_search_view);
        this.k = (ImageView) nsy.cMc_(this, R.id.iv_search_back);
        this.v = (LinearLayout) nsy.cMc_(this, R.id.search_mask_layer);
        this.s = (LinearLayout) nsy.cMc_(this, R.id.search_container);
        this.x = (LinearLayout) nsy.cMc_(this, R.id.search_holder);
        this.h = (HealthRecycleView) nsy.cMc_(this, R.id.left_sport_type);
        this.l = (HealthRecycleView) nsy.cMc_(this, R.id.right_sport_type);
        this.p = (HealthTextView) nsy.cMc_(this, R.id.search_result_header);
        this.w = (HealthRecycleView) nsy.cMc_(this, R.id.search_device_result_list);
        this.c = (RelativeLayout) nsy.cMc_(this, R.id.data_layout);
        this.w.setLayoutManager(new LinearLayoutManager(this));
        this.q = new ArrayList(16);
        int dimensionPixelOffset = BaseApplication.getContext().getResources().getDimensionPixelOffset(R.dimen._2131363752_res_0x7f0a07a8);
        this.y.setPadding(dimensionPixelOffset, 0, dimensionPixelOffset, 0);
        c();
        g();
        o();
        ViewGroup.LayoutParams layoutParams = this.h.getLayoutParams();
        int c = nsn.c(BaseApplication.getContext(), 84.0f);
        if (!LanguageUtil.h(this.b)) {
            c = nsn.c(BaseApplication.getContext(), 64.0f);
        }
        layoutParams.width = c;
        this.h.setLayoutParams(layoutParams);
        f();
    }

    private void b() {
        this.g.clear();
        for (int i = 0; i < this.n.size(); i++) {
            if (this.n.get(i).d() != -1 && !this.g.containsKey(Integer.valueOf(this.n.get(i).d()))) {
                this.g.put(Integer.valueOf(this.n.get(i).d()), Integer.valueOf(i));
            }
        }
    }

    private void c() {
        LogUtil.d("Track_SportMotionTypeActivity", "enter initLeftRecyclerView");
        this.h.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.ItemAnimator itemAnimator = this.h.getItemAnimator();
        if (itemAnimator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
        }
        LogUtil.d("Track_SportMotionTypeActivity", "mLeftList.size ", Integer.valueOf(this.j.size()));
        LeftListSportAdapter leftListSportAdapter = new LeftListSportAdapter(this.j);
        this.i = leftListSportAdapter;
        this.h.setAdapter(leftListSportAdapter);
        this.i.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() { // from class: com.huawei.ui.main.stories.history.ChooseSportTypeActivity.1
            @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter.OnItemClickListener
            public void onItemClicked(RecyclerHolder recyclerHolder, int i, Object obj) {
                if (obj instanceof rdh) {
                    LogUtil.d("Track_SportMotionTypeActivity", "mLeftAdapter onItemClicked position: ", Integer.valueOf(i), " name: ", Integer.valueOf(((rdh) obj).a()));
                    ChooseSportTypeActivity.this.e = true;
                    ChooseSportTypeActivity.this.i.d(i);
                    if (ChooseSportTypeActivity.this.l.getLayoutManager() == null) {
                        return;
                    }
                    RecyclerView.LayoutManager layoutManager = ChooseSportTypeActivity.this.l.getLayoutManager();
                    if (layoutManager instanceof LinearLayoutManager) {
                        ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(((Integer) ChooseSportTypeActivity.this.g.get(Integer.valueOf(i))).intValue(), 0);
                    }
                }
            }
        });
    }

    private void f() {
        if (TextUtils.isEmpty(this.z)) {
            LogUtil.c("Track_SportMotionTypeActivity", "mSportGroupTypeId is bull");
            return;
        }
        if (bkz.e(this.j)) {
            LogUtil.c("Track_SportMotionTypeActivity", "mLeftList is bull");
            return;
        }
        LogUtil.d("Track_SportMotionTypeActivity", "scrollToTargetGroupType:", this.z);
        int i = 0;
        while (true) {
            if (i >= this.j.size()) {
                i = 0;
                break;
            }
            if (this.z.equals(this.j.get(i).b())) {
                break;
            } else {
                i++;
            }
        }
        if (i == 0) {
            this.i.d(0);
            return;
        }
        Integer num = this.g.get(Integer.valueOf(i));
        if (num == null || num.intValue() == 0) {
            LogUtil.c("Track_SportMotionTypeActivity", "scrollToTargetKindType rightPosition is invalid");
            return;
        }
        this.i.d(i);
        if (this.h.getLayoutManager() instanceof LinearLayoutManager) {
            this.h.getLayoutManager().scrollToPosition(i);
        }
        RecyclerView.LayoutManager layoutManager = this.l.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(num.intValue(), 0);
        }
    }

    private void g() {
        LogUtil.d("Track_SportMotionTypeActivity", "enter initRightRecyclerView");
        RecyclerView.ItemAnimator itemAnimator = this.l.getItemAnimator();
        if (itemAnimator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
        }
        LogUtil.d("Track_SportMotionTypeActivity", "mRightList.size ", Integer.valueOf(this.n.size()));
        this.l.setLayoutManager(new LinearLayoutManager(this));
        RightListSportAdapter rightListSportAdapter = new RightListSportAdapter(this.n, this);
        this.o = rightListSportAdapter;
        rightListSportAdapter.c(this);
        this.l.setAdapter(this.o);
        this.l.addItemDecoration(new SpacesItemDecoration(28));
        this.l.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.huawei.ui.main.stories.history.ChooseSportTypeActivity.2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                ChooseSportTypeActivity.this.h();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        int d;
        LogUtil.d("Track_SportMotionTypeActivity", "Right onScrollRightListScrolled ");
        RecyclerView.LayoutManager layoutManager = this.l.getLayoutManager();
        if (!(layoutManager instanceof LinearLayoutManager)) {
            LogUtil.c("Track_SportMotionTypeActivity", "layoutManager is not LinearLayoutManager");
            return;
        }
        int findFirstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        if (findFirstVisibleItemPosition < 0 || findFirstVisibleItemPosition > this.n.size()) {
            findFirstVisibleItemPosition = 0;
        }
        if (bkz.e(this.n) || this.n.get(findFirstVisibleItemPosition) == null || (d = this.n.get(findFirstVisibleItemPosition).d()) == -1 || this.i.a() == d) {
            return;
        }
        LogUtil.d("Track_SportMotionTypeActivity", "onScrollRightListScrolled currentPosition:", Integer.valueOf(d));
        e(d);
        if (this.h.getLayoutManager() instanceof LinearLayoutManager) {
            this.h.getLayoutManager().scrollToPosition(d);
        }
    }

    private void e(int i) {
        if (!this.e) {
            this.i.d(i);
        }
        this.e = false;
    }

    private void o() {
        this.k.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.history.ChooseSportTypeActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ChooseSportTypeActivity.this.d();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.f10277a.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.history.ChooseSportTypeActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ChooseSportTypeActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.v.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.history.ChooseSportTypeActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ChooseSportTypeActivity.this.d();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        m();
    }

    private void m() {
        this.y.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.huawei.ui.main.stories.history.ChooseSportTypeActivity.9
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                LogUtil.d("Track_SportMotionTypeActivity", "onFocusChange:", Boolean.valueOf(z));
                if (!z) {
                    ChooseSportTypeActivity.this.f10277a.setTitleBarBackgroundColor(ChooseSportTypeActivity.this.getResources().getColor(R.color._2131296679_res_0x7f0901a7));
                    ChooseSportTypeActivity.this.c.setBackgroundColor(ChooseSportTypeActivity.this.getResources().getColor(R.color._2131296679_res_0x7f0901a7));
                    ChooseSportTypeActivity.this.s.setVisibility(8);
                    ChooseSportTypeActivity.this.k();
                    ChooseSportTypeActivity.this.u.d();
                    ViewScrollInstrumentation.focusChangeOnView(view, z);
                    return;
                }
                ChooseSportTypeActivity.this.f10277a.setTitleBarBackgroundColor(ChooseSportTypeActivity.this.getResources().getColor(R.color._2131296679_res_0x7f0901a7));
                ChooseSportTypeActivity.this.c.setBackgroundColor(ChooseSportTypeActivity.this.getResources().getColor(R.color._2131296679_res_0x7f0901a7));
                ChooseSportTypeActivity.this.s.setVisibility(0);
                ChooseSportTypeActivity.this.l();
                ChooseSportTypeActivity.this.n();
                ViewScrollInstrumentation.focusChangeOnView(view, z);
            }
        });
        this.y.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: com.huawei.ui.main.stories.history.ChooseSportTypeActivity.6
            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextSubmit(String str) {
                ChooseSportTypeActivity.this.s.setVisibility(0);
                ChooseSportTypeActivity.this.e(str);
                return false;
            }

            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextChange(String str) {
                ChooseSportTypeActivity.this.s.setVisibility(0);
                ChooseSportTypeActivity.this.e(str);
                return false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        c(1, 0);
        this.v.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        Object systemService = getSystemService("input_method");
        if (systemService instanceof InputMethodManager) {
            InputMethodManager inputMethodManager = (InputMethodManager) systemService;
            if (inputMethodManager.isActive()) {
                inputMethodManager.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 2);
            }
        }
        this.v.setVisibility(8);
        this.y.setQuery("", false);
        this.y.clearFocus();
        this.s.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        LogUtil.d("Track_SportMotionTypeActivity", "start do search");
        if (TextUtils.isEmpty(str)) {
            this.p.setText("");
            this.q.clear();
            SearchTypeAdapter searchTypeAdapter = this.m;
            if (searchTypeAdapter != null) {
                searchTypeAdapter.notifyDataSetChanged();
            }
            this.w.setVisibility(8);
            return;
        }
        this.r = str;
        this.u.c(str, this.t);
        this.u.b(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        rdm.d(this, i());
        this.k.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        rdm.c(this, i());
        this.k.setVisibility(4);
    }

    private rdm.d i() {
        rdm.d dVar = new rdm.d();
        dVar.c(this.f10277a);
        dVar.dMs_(this.x);
        dVar.dMt_(this.v);
        dVar.c(this.y);
        return dVar;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        SearchSportThreadManager searchSportThreadManager = this.u;
        if (searchSportThreadManager != null) {
            searchSportThreadManager.d();
            this.u.c();
            this.u = null;
        }
        e eVar = this.d;
        if (eVar != null) {
            eVar.removeCallbacksAndMessages(null);
            this.d = null;
        }
        HealthSearchView healthSearchView = this.y;
        if (healthSearchView != null) {
            healthSearchView.setOnQueryTextListener(null);
            this.y.setOnQueryTextFocusChangeListener(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, int i2) {
        LogUtil.d("Track_SportMotionTypeActivity", "setBiEvent eventValue is", Integer.valueOf(i));
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        hashMap.put("event", Integer.valueOf(i));
        if (i2 != 0) {
            hashMap.put(BleConstants.SPORT_TYPE, Integer.valueOf(i2));
        }
        ixx.d().d(this.b, AnalyticsValue.MOTION_RECORD_SEARCH_CLICK_EVENT.value(), hashMap, 0);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
