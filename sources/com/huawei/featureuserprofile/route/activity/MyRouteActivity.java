package com.huawei.featureuserprofile.route.activity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.featureuserprofile.route.activity.MyRouteActivity;
import com.huawei.featureuserprofile.route.navigationparser.NavigationFileParser;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.R;
import com.huawei.health.servicesui.R$string;
import com.huawei.health.userprofilemgr.model.RouteResultCallBack;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.network.base.common.trans.FileBinary;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.unite.RouteData;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.route.CpInfo;
import com.huawei.route.GetRouteDetailRsp;
import com.huawei.route.Point;
import com.huawei.thirdpartservice.OauthStatusCallback;
import com.huawei.thirdpartservice.komootapi.KomootProviderApi;
import com.huawei.ui.commonui.adapter.LoadMoreRecyclerViewAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.swiperefreshlayout.HealthSwipeRefreshLayout;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.toolbar.HealthToolBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.uikit.hwswiperefreshlayout.widget.HwSwipeRefreshLayout;
import defpackage.bto;
import defpackage.btq;
import defpackage.buc;
import defpackage.eme;
import defpackage.ixx;
import defpackage.koq;
import defpackage.nrh;
import defpackage.nrt;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.sqm;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes.dex */
public class MyRouteActivity extends BaseActivity {
    private long b;
    private long d;
    private LoadMoreRecyclerViewAdapter<RouteData> e;
    private LinkedHashMap<Long, e> h;
    private HealthSwipeRefreshLayout i;
    private CustomViewDialog k;
    private RelativeLayout l;
    private Iterator<Map.Entry<Long, e>> m;
    private RelativeLayout n;
    private HealthRecycleView o;
    private CustomTitleBar q;
    private int r;
    private HealthToolBar w;
    private int y;
    private final List<Long> s = new ArrayList();
    private final Map<Long, Bitmap> c = new HashMap();
    private boolean f = false;
    private boolean g = false;
    private boolean j = false;

    /* renamed from: a, reason: collision with root package name */
    private final Handler f2000a = new a();
    private final List<RouteData> t = new ArrayList();
    private final List<RouteData> p = new ArrayList();

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.hw_show_user_route_activity);
        overridePendingTransition(R.anim._2130771980_res_0x7f01000c, R.anim._2130771980_res_0x7f01000c);
        k();
        c(false);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.l.setVisibility(0);
        this.n.setVisibility(8);
        LoadMoreRecyclerViewAdapter<RouteData> loadMoreRecyclerViewAdapter = this.e;
        if (loadMoreRecyclerViewAdapter != null) {
            loadMoreRecyclerViewAdapter.noMoreLoad();
            this.e.clear();
        }
        c(false);
    }

    private void k() {
        getWindow().setBackgroundDrawableResource(R.color._2131296690_res_0x7f0901b2);
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.title_bar);
        this.q = customTitleBar;
        customTitleBar.setLeftButtonVisibility(0);
        this.q.setLeftButtonDrawable(ContextCompat.getDrawable(this, R.drawable._2131429371_res_0x7f0b07fb), nsf.h(R.string._2130850617_res_0x7f023339));
        this.q.setTitleText(getString(R.string._2130838785_res_0x7f020501));
        this.n = (RelativeLayout) findViewById(R.id.rel_no_data);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lin_no_data);
        if (linearLayout.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) linearLayout.getLayoutParams();
            layoutParams.setMargins(0, (int) (nsn.j() * 0.3d), 0, 0);
            linearLayout.setLayoutParams(layoutParams);
        }
        this.l = (RelativeLayout) findViewById(R.id.rel_route_loading);
        HealthSwipeRefreshLayout healthSwipeRefreshLayout = (HealthSwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        this.i = healthSwipeRefreshLayout;
        healthSwipeRefreshLayout.setPadding(0, 0, 0, 0);
        n();
        l();
        this.w = (HealthToolBar) findViewById(R.id.toolbar_route);
        View inflate = View.inflate(this, R.layout.hw_toolbar_bottomview, null);
        this.w.cHc_(inflate);
        inflate.findViewById(R.id.lin_root_view).setBackgroundColor(ContextCompat.getColor(this, R.color._2131296690_res_0x7f0901b2));
        b(false, false, 0);
        this.w.setIconEnabled(1, true);
        this.w.setIconEnabled(3, true);
        this.w.setIconVisible(2, 8);
        o();
        h();
    }

    private void o() {
        this.w.setOnSingleTapListener(new HealthToolBar.OnSingleTapListener() { // from class: bst
            @Override // com.huawei.ui.commonui.toolbar.HealthToolBar.OnSingleTapListener
            public final void onSingleTap(int i) {
                MyRouteActivity.this.e(i);
            }
        });
    }

    public /* synthetic */ void e(int i) {
        if (this.f) {
            LogUtil.a("Track_MyRouteActivity", "don't click to fast");
            return;
        }
        this.w.postDelayed(new Runnable() { // from class: btd
            @Override // java.lang.Runnable
            public final void run() {
                MyRouteActivity.this.c();
            }
        }, 500L);
        this.f = true;
        if (i == 1) {
            b(d.f2007a);
            if (this.g) {
                if (!koq.b(this.s)) {
                    d(-1);
                    return;
                } else {
                    nrh.d(this, getString(R.string._2130838793_res_0x7f020509));
                    return;
                }
            }
            b(true, false, 0);
            this.e.notifyDataSetChanged();
            return;
        }
        if (i != 3) {
            LogUtil.a("Track_MyRouteActivity", "position is not right");
            return;
        }
        b(d.c);
        if (this.g) {
            if (this.j) {
                this.s.clear();
            } else {
                a();
            }
            boolean z = this.j;
            b(true, !z, z ? 0 : this.s.size());
            this.e.notifyDataSetChanged();
            return;
        }
        j();
    }

    public /* synthetic */ void c() {
        this.f = false;
    }

    private void a() {
        for (RouteData routeData : this.e.getData()) {
            if (!this.s.contains(Long.valueOf(routeData.getRouteVersion()))) {
                this.s.add(Long.valueOf(routeData.getRouteVersion()));
            }
        }
    }

    private void h() {
        this.q.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.featureuserprofile.route.activity.MyRouteActivity.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                MyRouteActivity.this.q.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                MyRouteActivity myRouteActivity = MyRouteActivity.this;
                myRouteActivity.r = myRouteActivity.q.getHeight();
            }
        });
        this.w.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.featureuserprofile.route.activity.MyRouteActivity.3
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                MyRouteActivity.this.w.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                MyRouteActivity myRouteActivity = MyRouteActivity.this;
                myRouteActivity.y = myRouteActivity.w.getHeight();
                MyRouteActivity.this.w.setVisibility(8);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z) {
        if (!CommonUtil.aa(this)) {
            nsn.a((Context) this, MyRouteActivity.class.getName(), getString(R.string._2130838785_res_0x7f020501), false);
            finish();
        } else {
            bto.e(0, 0L, new c(this, z));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        HashMap hashMap = new HashMap(1);
        hashMap.put("click", "1");
        hashMap.put("MY_ROUTE_SIZE", Integer.valueOf(i));
        ixx.d().d(this, AnalyticsValue.MY_ROUTE_ACTIVITY_EVENT.value(), hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("event", 2);
        hashMap.put("pageStatus", z ? "true" : "false");
        ixx.d().d(BaseApplication.e(), "1040087", hashMap, 0);
    }

    private void b(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("event", Integer.valueOf(i));
        ixx.d().d(BaseApplication.e(), "1040088", hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z, Long l, int i, long j) {
        bto.e(1, l.longValue(), new b(this, l, i, j, z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Long l) {
        LinkedHashMap<Long, e> linkedHashMap = this.h;
        if (linkedHashMap == null) {
            LogUtil.a("mNextOperationLinkedHashMap is empty", new Object[0]);
            return;
        }
        Iterator<Map.Entry<Long, e>> it = linkedHashMap.entrySet().iterator();
        while (it.hasNext()) {
            if (l.equals(it.next().getKey())) {
                this.d = l.longValue();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(GetRouteDetailRsp getRouteDetailRsp) {
        if (koq.b(this.p)) {
            return;
        }
        getRouteDetailRsp.getRouteDatas().addAll(0, this.p);
        this.p.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static LinkedHashMap<Long, e> d(Long[] lArr) {
        int length = lArr.length;
        if (length == 0) {
            return new LinkedHashMap<>();
        }
        int i = length % 6;
        int i2 = length / 6;
        if (i != 0) {
            i2++;
        }
        LinkedHashMap<Long, e> linkedHashMap = new LinkedHashMap<>();
        int i3 = 0;
        while (true) {
            if (i3 >= i2 - 1) {
                break;
            }
            if (i != 0) {
                int i4 = (length - (i3 * 6)) - i;
                linkedHashMap.put(lArr[i4 - 1], new e(i3 == 0 ? i : 6, lArr[i4 + 3 > length ? 0 : i4 + 2].longValue()));
            } else {
                int i5 = length - ((i3 + 1) * 6);
                linkedHashMap.put(lArr[i5 - 1], new e(6, lArr[i5 + 2].longValue()));
            }
            i3++;
        }
        if (length >= 6) {
            i = 6;
        }
        linkedHashMap.put(0L, new e(i, length > 2 ? lArr[2].longValue() : 0L));
        return linkedHashMap;
    }

    /* loaded from: classes3.dex */
    static class e {

        /* renamed from: a, reason: collision with root package name */
        int f2008a;
        long b;

        e(int i, long j) {
            this.f2008a = i;
            this.b = j;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final long j) {
        runOnUiThread(new Runnable() { // from class: bss
            @Override // java.lang.Runnable
            public final void run() {
                MyRouteActivity.this.a(j);
            }
        });
    }

    public /* synthetic */ void a(long j) {
        if (isFinishing()) {
            return;
        }
        if (j == 0) {
            s();
        } else {
            this.e.loadError();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ui_(Message message) {
        if (this.d == this.b) {
            if (message.obj != null) {
                if (message.obj instanceof GetRouteDetailRsp) {
                    uf_(message, (GetRouteDetailRsp) message.obj);
                    return;
                }
                return;
            }
            s();
            return;
        }
        if (message.obj != null) {
            if (message.obj instanceof GetRouteDetailRsp) {
                GetRouteDetailRsp getRouteDetailRsp = (GetRouteDetailRsp) message.obj;
                if (!koq.b(getRouteDetailRsp.getRouteDatas())) {
                    LogUtil.a("Track_MyRouteActivity", "load more success,", ",data size = " + getRouteDetailRsp.getRouteDatas().size());
                    b(this.g, false, this.s.size());
                    e(false, true, getRouteDetailRsp.getRouteDatas());
                    return;
                }
                m();
                return;
            }
            return;
        }
        m();
        this.e.loadError();
    }

    private void uf_(Message message, GetRouteDetailRsp getRouteDetailRsp) {
        if (!koq.b(getRouteDetailRsp.getRouteDatas())) {
            LogUtil.a("Track_MyRouteActivity", "refresh,", "data size = " + getRouteDetailRsp.getRouteDatas().size());
            e(message.arg1 == 1, false, getRouteDetailRsp.getRouteDatas());
            return;
        }
        s();
    }

    public static /* synthetic */ int e(RouteData routeData, RouteData routeData2) {
        return -Long.compare(routeData.getRouteVersion(), routeData2.getRouteVersion());
    }

    private void e(boolean z, boolean z2, List<RouteData> list) {
        Iterator<Map.Entry<Long, e>> it;
        Collections.sort(list, new Comparator() { // from class: bta
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return MyRouteActivity.e((RouteData) obj, (RouteData) obj2);
            }
        });
        ArrayList arrayList = new ArrayList();
        if (!koq.b(this.t)) {
            arrayList.addAll(this.t);
        } else if (!koq.b(this.e.getData()) && z2) {
            arrayList.addAll(this.e.getData());
        } else {
            LogUtil.a("Track_MyRouteActivity", "mTempRouteDataList and mAdapter.getData() is both empty");
        }
        arrayList.addAll(list);
        ArrayList arrayList2 = new ArrayList(new LinkedHashSet(arrayList));
        if ((((nsn.j() - nsn.r(this)) - this.r) - this.y) - ((getResources().getDimensionPixelOffset(R.dimen._2131362875_res_0x7f0a043b) * arrayList2.size()) + getResources().getDimensionPixelOffset(R.dimen._2131363122_res_0x7f0a0532)) > 0 && (it = this.m) != null && it.hasNext()) {
            this.o.setVisibility(z ? 0 : 8);
            this.t.addAll(list);
            Map.Entry<Long, e> next = this.m.next();
            LogUtil.a("Track_MyRouteActivity", "Data can't fill all screen,request next page");
            a(false, next.getKey(), next.getValue().f2008a, next.getValue().b);
            return;
        }
        this.e.notifyDataChange(arrayList2, true);
        this.t.clear();
        m();
    }

    private void m() {
        this.o.setVisibility(0);
        this.w.setVisibility(0);
        this.n.setVisibility(8);
        HealthSwipeRefreshLayout healthSwipeRefreshLayout = this.i;
        if (healthSwipeRefreshLayout != null) {
            healthSwipeRefreshLayout.a();
        }
        this.l.setVisibility(8);
        Iterator<Map.Entry<Long, e>> it = this.m;
        if (it != null && it.hasNext()) {
            this.e.enableMoreLoad();
        } else {
            this.e.noMoreLoad();
        }
    }

    private void l() {
        HealthRecycleView healthRecycleView = (HealthRecycleView) findViewById(R.id.rv_route);
        this.o = healthRecycleView;
        healthRecycleView.setLayoutManager(new LinearLayoutManager(this));
        LoadMoreRecyclerViewAdapter<RouteData> loadMoreRecyclerViewAdapter = new LoadMoreRecyclerViewAdapter<RouteData>(new ArrayList(), this.o, R.layout.item_route_data) { // from class: com.huawei.featureuserprofile.route.activity.MyRouteActivity.5
            @Override // com.huawei.ui.commonui.adapter.LoadMoreRecyclerViewAdapter
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void convert(RecyclerHolder recyclerHolder, int i, RouteData routeData) {
                MyRouteActivity.this.e(recyclerHolder, i, routeData);
            }
        };
        this.e = loadMoreRecyclerViewAdapter;
        loadMoreRecyclerViewAdapter.setOnItemClickListener(new LoadMoreRecyclerViewAdapter.OnItemClickListener<RouteData>() { // from class: com.huawei.featureuserprofile.route.activity.MyRouteActivity.1
            @Override // com.huawei.ui.commonui.adapter.LoadMoreRecyclerViewAdapter.OnItemClickListener
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onItemClicked(RecyclerHolder recyclerHolder, int i, RouteData routeData) {
                if (!nsn.a(500)) {
                    if (MyRouteActivity.this.g) {
                        return;
                    }
                    btq.c(routeData);
                    Intent intent = new Intent();
                    intent.setClassName(BaseApplication.d(), "com.huawei.ui.main.stories.route.activity.MyRouteDetailActivity");
                    intent.putExtra("fromFlag", "route_flag");
                    intent.putExtra("delete_position", i);
                    MyRouteActivity.this.startActivityForResult(intent, 200);
                    return;
                }
                LogUtil.h("Track_MyRouteActivity", "view click too fast.");
            }
        });
        this.e.setOnMoreListener(new LoadMoreRecyclerViewAdapter.OnMoreListener() { // from class: btb
            @Override // com.huawei.ui.commonui.adapter.LoadMoreRecyclerViewAdapter.OnMoreListener
            public final void onLoadingMore() {
                MyRouteActivity.this.e();
            }
        });
        this.e.setDefaultPageSize(3);
        this.o.setAdapter(this.e);
    }

    public /* synthetic */ void e() {
        Iterator<Map.Entry<Long, e>> it = this.m;
        if (it == null || !it.hasNext()) {
            return;
        }
        Map.Entry<Long, e> next = this.m.next();
        a(false, next.getKey(), next.getValue().f2008a, next.getValue().b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(RecyclerHolder recyclerHolder, final int i, final RouteData routeData) {
        a(recyclerHolder, i);
        e(recyclerHolder, routeData);
        CheckBox checkBox = (CheckBox) recyclerHolder.cwK_(R.id.ck_select);
        checkBox.setVisibility(this.g ? 0 : 8);
        if (!koq.b(this.s)) {
            checkBox.setChecked(this.s.contains(Long.valueOf(routeData.getRouteVersion())));
        } else {
            checkBox.setChecked(false);
        }
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.featureuserprofile.route.activity.MyRouteActivity$$ExternalSyntheticLambda1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                MyRouteActivity.this.un_(routeData, compoundButton, z);
            }
        });
        ((HealthTextView) recyclerHolder.cwK_(R.id.tv_route_name)).setText(routeData.getRouteName());
        ImageView imageView = (ImageView) recyclerHolder.cwK_(R.id.img_thumb);
        if (nrt.a(this)) {
            imageView.setBackgroundResource(R.drawable._2131431308_res_0x7f0b0f8c);
        } else {
            imageView.setBackgroundResource(R.drawable._2131431309_res_0x7f0b0f8d);
        }
        ug_(imageView, routeData.getRoutePoints(), routeData.getRouteVersion(), routeData.getRouteType());
        ((HealthTextView) recyclerHolder.cwK_(R.id.tv_route_distance)).setText(d(new BigDecimal(Double.toString(routeData.getRouteDistance())).divide(new BigDecimal(Double.toString(1000.0d)), 2, 4).doubleValue()));
        recyclerHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() { // from class: bsx
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return MyRouteActivity.this.uo_(i, view);
            }
        });
    }

    /* synthetic */ void un_(RouteData routeData, CompoundButton compoundButton, boolean z) {
        if (!compoundButton.isPressed()) {
            ViewClickInstrumentation.clickOnView(compoundButton);
            return;
        }
        if (z) {
            if (!this.s.contains(Long.valueOf(routeData.getRouteVersion()))) {
                this.s.add(Long.valueOf(routeData.getRouteVersion()));
            }
        } else {
            this.s.remove(Long.valueOf(routeData.getRouteVersion()));
        }
        if (this.g) {
            boolean z2 = this.s.size() == this.e.getData().size();
            this.j = z2;
            b(true, z2, this.s.size());
        }
        ViewClickInstrumentation.clickOnView(compoundButton);
    }

    public /* synthetic */ boolean uo_(int i, View view) {
        if (this.g) {
            return false;
        }
        d(i);
        return true;
    }

    private void a(RecyclerHolder recyclerHolder, int i) {
        ViewGroup.LayoutParams layoutParams = recyclerHolder.cwK_(R.id.root_view).getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            if (i == 0) {
                layoutParams2.topMargin = getResources().getDimensionPixelOffset(R.dimen._2131362566_res_0x7f0a0306);
            } else {
                layoutParams2.topMargin = 0;
            }
            recyclerHolder.cwK_(R.id.root_view).setLayoutParams(layoutParams2);
        }
    }

    private void ug_(final ImageView imageView, List<Point> list, final long j, int i) {
        if (this.c.containsKey(Long.valueOf(j))) {
            imageView.setImageBitmap(this.c.get(Long.valueOf(j)));
            return;
        }
        ArrayList arrayList = new ArrayList();
        TreeMap treeMap = new TreeMap();
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2++) {
            Point point = list.get(i2);
            if (!sqm.a(point.getLatitude(), point.getLongitude())) {
                CpInfo cpInfo = point.getCpInfo();
                if (!sqm.e(i, cpInfo) && !sqm.a(arrayList2, i, cpInfo, point)) {
                    arrayList2.add(point);
                    treeMap.put(Long.valueOf(i2), new double[]{point.getLatitude(), point.getLongitude()});
                }
            }
        }
        arrayList.add(treeMap);
        eme.b().getTrackDraw(arrayList, new IBaseResponseCallback() { // from class: bsy
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i3, Object obj) {
                MyRouteActivity.this.um_(j, imageView, i3, obj);
            }
        });
    }

    public /* synthetic */ void um_(long j, ImageView imageView, int i, Object obj) {
        if (obj instanceof Bitmap) {
            Bitmap bitmap = (Bitmap) obj;
            this.c.put(Long.valueOf(j), bitmap);
            imageView.setImageBitmap(bitmap);
        }
    }

    private String d(double d2) {
        if (UnitUtil.h()) {
            return getResources().getQuantityString(R.plurals._2130903095_res_0x7f030037, (int) d2, UnitUtil.e(UnitUtil.e(d2, 3), 1, 2));
        }
        return getResources().getQuantityString(R.plurals._2130903096_res_0x7f030038, (int) d2, UnitUtil.e(d2, 1, 2));
    }

    private void e(RecyclerHolder recyclerHolder, RouteData routeData) {
        String string;
        HealthTextView healthTextView = (HealthTextView) recyclerHolder.cwK_(R.id.tv_route_source);
        int type = routeData.getType();
        if (type == 1) {
            string = getString(R.string._2130838839_res_0x7f020537);
        } else if (type == 2) {
            string = getString(R.string._2130838865_res_0x7f020551);
        } else {
            string = type != 3 ? "" : getString(R.string._2130838866_res_0x7f020552);
        }
        healthTextView.setText(string);
    }

    private void n() {
        this.i.setCallback(new HwSwipeRefreshLayout.Callback() { // from class: com.huawei.featureuserprofile.route.activity.MyRouteActivity.4
            @Override // com.huawei.uikit.hwswiperefreshlayout.widget.HwSwipeRefreshLayout.Callback
            public boolean needToWait() {
                return true;
            }

            @Override // com.huawei.uikit.hwswiperefreshlayout.widget.HwSwipeRefreshLayout.Callback
            public void onScrollUp() {
            }

            @Override // com.huawei.uikit.hwswiperefreshlayout.widget.HwSwipeRefreshLayout.Callback
            public boolean isEnabled() {
                return !MyRouteActivity.this.g;
            }

            @Override // com.huawei.uikit.hwswiperefreshlayout.widget.HwSwipeRefreshLayout.Callback
            public void onRefreshStart() {
                LogUtil.a("Track_MyRouteActivity", "PullRefresh start");
                MyRouteActivity.this.c(true);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        this.l.setVisibility(8);
        this.w.setVisibility(8);
        this.n.setVisibility(0);
        this.s.clear();
        this.c.clear();
        this.e.clear();
        HealthSwipeRefreshLayout healthSwipeRefreshLayout = this.i;
        if (healthSwipeRefreshLayout != null) {
            healthSwipeRefreshLayout.a();
        }
        HealthButton healthButton = (HealthButton) findViewById(R.id.button_import_route);
        if (healthButton != null) {
            float d2 = new HealthColumnSystem(this, 1).d(2);
            LogUtil.a("Track_MyRouteActivity", "set button width");
            healthButton.setMinWidth((int) d2);
            healthButton.setVisibility(0);
            healthButton.setOnClickListener(new View.OnClickListener() { // from class: bsn
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MyRouteActivity.this.uq_(view);
                }
            });
        }
    }

    public /* synthetic */ void uq_(View view) {
        b(d.d);
        j();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d(final int i) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.dialog_confirm_delete_route, (ViewGroup) null);
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this);
        builder.a(getString(R.string._2130838785_res_0x7f020501)).czh_(inflate, 0, 0).czc_(R.string._2130837555_res_0x7f020033, new View.OnClickListener() { // from class: bsz
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MyRouteActivity.uj_(view);
            }
        }).cze_(R.string._2130837648_res_0x7f020090, new View.OnClickListener() { // from class: btc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MyRouteActivity.this.up_(i, view);
            }
        });
        builder.e().show();
    }

    public static /* synthetic */ void uj_(View view) {
        LogUtil.a("Track_MyRouteActivity", "createRouteDialog onClick Negative view");
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void up_(int i, View view) {
        LogUtil.a("Track_MyRouteActivity", "createRouteDialog onClick positive view");
        if (this.f) {
            LogUtil.a("Track_MyRouteActivity", "don't click to fast");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        this.w.postDelayed(new Runnable() { // from class: bsu
            @Override // java.lang.Runnable
            public final void run() {
                MyRouteActivity.this.d();
            }
        }, 500L);
        this.f = true;
        if (!CommonUtil.aa(this)) {
            nrh.b(this, R$string.IDS_hw_show_set_about_privacy_connectting_error);
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        List<Long> arrayList = new ArrayList<>();
        if (i == -1) {
            arrayList = this.s;
        } else {
            LoadMoreRecyclerViewAdapter<RouteData> loadMoreRecyclerViewAdapter = this.e;
            if (loadMoreRecyclerViewAdapter != null) {
                arrayList.add(Long.valueOf(loadMoreRecyclerViewAdapter.get(i).getRouteVersion()));
            } else {
                LogUtil.a("Track_MyRouteActivity", "mAdapter is empty");
            }
        }
        a(i, arrayList);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void d() {
        this.f = false;
    }

    private void a(final int i, List<Long> list) {
        bto.a(list, new RouteResultCallBack<CloudCommonReponse>() { // from class: com.huawei.featureuserprofile.route.activity.MyRouteActivity.6
            @Override // com.huawei.health.userprofilemgr.model.RouteResultCallBack
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(CloudCommonReponse cloudCommonReponse) {
                if (cloudCommonReponse.getResultCode().intValue() == 0) {
                    LogUtil.a("Track_MyRouteActivity", "delete success");
                    Message obtainMessage = MyRouteActivity.this.f2000a.obtainMessage();
                    obtainMessage.what = 2;
                    obtainMessage.obj = Integer.valueOf(i);
                    MyRouteActivity.this.f2000a.sendMessage(obtainMessage);
                    return;
                }
                nrh.e(MyRouteActivity.this, R.string._2130843017_res_0x7f021589);
                LogUtil.c("Track_MyRouteActivity", "delete resp resultCode:", cloudCommonReponse.getResultCode(), ",resp resultDesc:", cloudCommonReponse.getResultDesc());
            }

            @Override // com.huawei.health.userprofilemgr.model.RouteResultCallBack
            public void onFailure(Throwable th) {
                nrh.e(MyRouteActivity.this, R.string._2130843017_res_0x7f021589);
                LogUtil.b("Track_MyRouteActivity", "delete onFailure:", th.getMessage());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z, boolean z2, int i) {
        String string;
        this.g = z;
        this.j = z2;
        this.w.setIconTitle(1, getString(R.string._2130851021_res_0x7f0234cd));
        this.w.setIcon(1, R.drawable._2131430848_res_0x7f0b0dc0);
        if (this.g) {
            this.w.setIcon(3, z2 ? R.drawable._2131427541_res_0x7f0b00d5 : R.drawable._2131430296_res_0x7f0b0b98);
            this.w.setIconTitle(3, z2 ? getString(R.string._2130838801_res_0x7f020511) : getString(R.string._2130838800_res_0x7f020510));
            this.q.setLeftButtonDrawable(ContextCompat.getDrawable(this, R.drawable._2131428766_res_0x7f0b059e), nsf.h(R.string._2130850611_res_0x7f023333));
            CustomTitleBar customTitleBar = this.q;
            if (i > 0) {
                string = getResources().getQuantityString(R.plurals._2130903087_res_0x7f03002f, i, Integer.valueOf(i));
            } else {
                string = getString(R.string._2130838799_res_0x7f02050f);
            }
            customTitleBar.setTitleText(string);
            this.q.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: bsw
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MyRouteActivity.this.uk_(view);
                }
            });
            return;
        }
        this.w.setIcon(3, R.drawable._2131430130_res_0x7f0b0af2);
        this.w.setIconTitle(3, getString(R.string._2130838789_res_0x7f020505));
        this.q.setTitleText(getString(R.string._2130838785_res_0x7f020501));
        this.q.setLeftButtonDrawable(ContextCompat.getDrawable(this, R.drawable._2131429371_res_0x7f0b07fb), nsf.h(R.string._2130850617_res_0x7f023339));
        this.q.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: bsv
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MyRouteActivity.this.ul_(view);
            }
        });
    }

    public /* synthetic */ void uk_(View view) {
        g();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void ul_(View view) {
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void g() {
        this.q.setLeftButtonDrawable(ContextCompat.getDrawable(this, R.drawable._2131429371_res_0x7f0b07fb), nsf.h(R.string._2130850617_res_0x7f023339));
        this.q.setTitleText(getString(R.string._2130838785_res_0x7f020501));
        this.s.clear();
        this.e.notifyDataSetChanged();
        b(false, false, 0);
    }

    private void b() {
        if (EnvironmentInfo.k() && CommonUtil.bu()) {
            return;
        }
        PermissionUtil.b(this, PermissionUtil.PermissionType.MANAGE_EXTERNAL_STORAGE, new CustomPermissionAction(this) { // from class: com.huawei.featureuserprofile.route.activity.MyRouteActivity.10
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                MyRouteActivity.this.f();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
        intent.setType("application/octet-stream|application/vnd.google-earth.kml+xml");
        intent.putExtra("android.intent.extra.MIME_TYPES", new String[]{FileBinary.HEAD_VALUE_CONTENT_TYPE_OCTET_STREAM, "application/vnd.google-earth.kml+xml"});
        intent.addCategory("android.intent.category.OPENABLE");
        try {
            startActivityForResult(intent, 100);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("Track_MyRouteActivity", "openSystemFileManager, ActivityNotFoundException");
            nrh.d(this, getString(R.string._2130843115_res_0x7f0215eb));
        } catch (AndroidRuntimeException unused2) {
            LogUtil.b("Track_MyRouteActivity", "openSystemFileManager, AndroidRuntimeException");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        HealthSwipeRefreshLayout healthSwipeRefreshLayout = this.i;
        if (healthSwipeRefreshLayout != null) {
            healthSwipeRefreshLayout.setCallback(null);
            this.i = null;
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        int intExtra;
        super.onActivityResult(i, i2, intent);
        if (i == 100) {
            uh_(i2, intent);
        } else if (i == 201) {
            c(true);
        } else if (i != 200) {
            LogUtil.a("Track_MyRouteActivity", "unknown resultCode");
        } else if (i2 == -1 && intent != null && (intExtra = intent.getIntExtra("delete_position", -1)) > -1) {
            Message obtainMessage = this.f2000a.obtainMessage();
            obtainMessage.what = 2;
            obtainMessage.obj = Integer.valueOf(intExtra);
            this.f2000a.sendMessage(obtainMessage);
        }
        CustomViewDialog customViewDialog = this.k;
        if (customViewDialog != null) {
            customViewDialog.dismiss();
        }
    }

    private void uh_(int i, Intent intent) {
        if (i != -1) {
            LogUtil.a("Track_MyRouteActivity", "no select route file");
            return;
        }
        if (intent == null) {
            LogUtil.h("Track_MyRouteActivity", "handleRouteFile, intent is null.");
            return;
        }
        Uri data = intent.getData();
        if (data == null) {
            LogUtil.a("Track_MyRouteActivity", "uri is null");
            return;
        }
        LogUtil.c("Track_MyRouteActivity", "File uri is: ", data.toString());
        String str = "";
        try {
            Cursor query = getContentResolver().query(data, null, null, null, null);
            try {
                str = buc.uQ_(query);
                if (query != null) {
                    query.close();
                }
            } finally {
            }
        } catch (SecurityException unused) {
            LogUtil.b("Track_MyRouteActivity", "query file address, catch securityException");
        }
        if (TextUtils.isEmpty(str) || (!str.endsWith(NavigationFileParser.GPX) && !str.endsWith(NavigationFileParser.TCX) && !str.endsWith(NavigationFileParser.KML))) {
            LogUtil.b("Track_MyRouteActivity", "handleRouteFile is error fileName : ", str);
            Toast.makeText(this, R.string._2130838788_res_0x7f020504, 0).show();
        } else {
            AppRouter.b("/Main/MyRouteDetailActivity").e("fromFlag", "file_flag").e("filePath", buc.uR_(data, str)).c(this);
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && keyEvent.getAction() == 0 && this.g) {
            g();
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    /* loaded from: classes3.dex */
    static class a extends BaseHandler<MyRouteActivity> {
        private a(MyRouteActivity myRouteActivity) {
            super(myRouteActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: uu_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(MyRouteActivity myRouteActivity, Message message) {
            int i = message.what;
            if (i == 1) {
                myRouteActivity.ui_(message);
                return;
            }
            if (i != 2) {
                return;
            }
            int intValue = ((Integer) message.obj).intValue();
            if (intValue == -1) {
                int i2 = 0;
                while (i2 < myRouteActivity.e.getData().size()) {
                    RouteData routeData = (RouteData) myRouteActivity.e.get(i2);
                    Iterator it = myRouteActivity.s.iterator();
                    while (it.hasNext()) {
                        if (routeData.getRouteVersion() == ((Long) it.next()).longValue()) {
                            myRouteActivity.e.remove(i2);
                            i2--;
                        }
                    }
                    i2++;
                }
                myRouteActivity.s.clear();
            } else {
                myRouteActivity.s.remove(Long.valueOf(((RouteData) myRouteActivity.e.get(intValue)).getRouteVersion()));
                myRouteActivity.e.remove(intValue);
            }
            nrh.e(myRouteActivity, R.string.IDS_device_wifi_clear_user_success);
            if (myRouteActivity.e.getData().isEmpty()) {
                myRouteActivity.s();
            }
            myRouteActivity.b(false, false, 0);
            myRouteActivity.e.notifyDataSetChanged();
            if (myRouteActivity.getResources().getDimensionPixelOffset(R.dimen._2131362875_res_0x7f0a043b) * myRouteActivity.e.size() >= myRouteActivity.o.getHeight() || myRouteActivity.m == null || !myRouteActivity.m.hasNext()) {
                return;
            }
            LogUtil.a("Track_MyRouteActivity", "first page show complete, load next page");
            Map.Entry entry = (Map.Entry) myRouteActivity.m.next();
            myRouteActivity.a(false, (Long) entry.getKey(), ((e) entry.getValue()).f2008a, ((e) entry.getValue()).b);
        }
    }

    private void j() {
        if (!Utils.h() || !String.valueOf(7).equals(LoginInit.getInstance(this).getAccountInfo(1009))) {
            b();
        } else {
            r();
        }
    }

    private void r() {
        if (this.k == null) {
            if (!(getSystemService("layout_inflater") instanceof LayoutInflater)) {
                return;
            }
            View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.dialog_route_import_select, (ViewGroup) null);
            View findViewById = inflate.findViewById(R.id.htv_local_import);
            CustomViewDialog e2 = new CustomViewDialog.Builder(this).c(false).czg_(inflate).cze_(R.string._2130848866_res_0x7f022c62, new View.OnClickListener() { // from class: bth
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MyRouteActivity.this.ur_(view);
                }
            }).e();
            this.k = e2;
            e2.setCancelable(true);
            findViewById.setOnClickListener(new View.OnClickListener() { // from class: bsq
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MyRouteActivity.this.us_(view);
                }
            });
            inflate.findViewById(R.id.htv_komoot_import).setOnClickListener(new View.OnClickListener() { // from class: bsp
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MyRouteActivity.this.ut_(view);
                }
            });
        }
        this.k.show();
    }

    public /* synthetic */ void ur_(View view) {
        this.k.dismiss();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void us_(View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
        } else {
            b();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public /* synthetic */ void ut_(View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        final KomootProviderApi komootProviderApi = (KomootProviderApi) Services.c("FeatureDataOpen", KomootProviderApi.class);
        komootProviderApi.isOauth(new OauthStatusCallback() { // from class: bsr
            @Override // com.huawei.thirdpartservice.OauthStatusCallback
            public final void onOauthStatusCallback(boolean z) {
                MyRouteActivity.this.c(komootProviderApi, z);
            }
        });
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void c(KomootProviderApi komootProviderApi, boolean z) {
        if (z) {
            Intent intent = new Intent(this, (Class<?>) RouteImportActivity.class);
            intent.putExtra("maxCapacity", i());
            startActivityForResult(intent, 201);
            return;
        }
        komootProviderApi.performOauth(this);
    }

    private int i() {
        LoadMoreRecyclerViewAdapter<RouteData> loadMoreRecyclerViewAdapter = this.e;
        if (loadMoreRecyclerViewAdapter == null || loadMoreRecyclerViewAdapter.size() == 0) {
            return 5;
        }
        return Math.min(50 - this.e.size(), 5);
    }

    /* loaded from: classes3.dex */
    static class c implements RouteResultCallBack<GetRouteDetailRsp> {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<MyRouteActivity> f2006a;
        private boolean d;

        c(MyRouteActivity myRouteActivity, boolean z) {
            this.f2006a = new WeakReference<>(myRouteActivity);
            this.d = z;
        }

        @Override // com.huawei.health.userprofilemgr.model.RouteResultCallBack
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onSuccess(GetRouteDetailRsp getRouteDetailRsp) {
            MyRouteActivity myRouteActivity = this.f2006a.get();
            if (myRouteActivity == null || myRouteActivity.isFinishing()) {
                LogUtil.h("Track_MyRouteActivity", "queryRouteDetailOrSummary success is finishing");
                return;
            }
            if (getRouteDetailRsp.getResultCode().intValue() == 0) {
                myRouteActivity.m = null;
                myRouteActivity.h = null;
                ArrayList arrayList = new ArrayList();
                Iterator<RouteData> it = getRouteDetailRsp.getRouteDatas().iterator();
                while (it.hasNext()) {
                    arrayList.add(Long.valueOf(it.next().getRouteVersion()));
                }
                int size = getRouteDetailRsp.getRouteDatas() != null ? getRouteDetailRsp.getRouteDatas().size() : 0;
                myRouteActivity.a(size);
                myRouteActivity.d(size != 0);
                myRouteActivity.h = MyRouteActivity.d((Long[]) arrayList.toArray(new Long[0]));
                if (myRouteActivity.h.size() > 0) {
                    myRouteActivity.m = myRouteActivity.h.entrySet().iterator();
                    Map.Entry entry = (Map.Entry) myRouteActivity.m.next();
                    myRouteActivity.b = ((Long) entry.getKey()).longValue();
                    myRouteActivity.a(this.d, Long.valueOf(myRouteActivity.b), ((e) entry.getValue()).f2008a, ((e) entry.getValue()).b);
                    return;
                }
                LogUtil.a("Track_MyRouteActivity", "mPaginateVersionArray size is zero");
                myRouteActivity.e(0L);
                return;
            }
            LogUtil.a("Track_MyRouteActivity", "get track summary fail ", "resultDesc:", getRouteDetailRsp.getResultDesc());
            myRouteActivity.e(0L);
            myRouteActivity.d(false);
        }

        @Override // com.huawei.health.userprofilemgr.model.RouteResultCallBack
        public void onFailure(Throwable th) {
            MyRouteActivity myRouteActivity = this.f2006a.get();
            if (myRouteActivity != null && !myRouteActivity.isFinishing()) {
                myRouteActivity.e(0L);
                LogUtil.a("Track_MyRouteActivity", "get track summary onFailure");
            } else {
                LogUtil.h("Track_MyRouteActivity", "queryRouteDetailOrSummary failed is finishing");
            }
        }
    }

    /* loaded from: classes3.dex */
    static class b implements RouteResultCallBack<GetRouteDetailRsp> {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<MyRouteActivity> f2005a;
        private long b;
        private boolean c;
        private int d;
        private Long e;

        b(MyRouteActivity myRouteActivity, Long l, int i, long j, boolean z) {
            this.f2005a = new WeakReference<>(myRouteActivity);
            this.e = l;
            this.d = i;
            this.b = j;
            this.c = z;
        }

        @Override // com.huawei.health.userprofilemgr.model.RouteResultCallBack
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onSuccess(GetRouteDetailRsp getRouteDetailRsp) {
            MyRouteActivity myRouteActivity = this.f2005a.get();
            if (myRouteActivity == null || myRouteActivity.isFinishing()) {
                LogUtil.h("Track_MyRouteActivity", "getRouteDetail success is finishing");
                return;
            }
            if (getRouteDetailRsp.getResultCode().intValue() != 0 || koq.b(getRouteDetailRsp.getRouteDatas())) {
                myRouteActivity.e(this.e.longValue());
                LogUtil.c("Track_MyRouteActivity", "query,resp resultCode:", getRouteDetailRsp.getResultCode(), ",resp resultDesc:", getRouteDetailRsp.getResultDesc());
                return;
            }
            myRouteActivity.d(this.e);
            if (this.d == 0 || getRouteDetailRsp.getRouteDatas().size() >= this.d) {
                myRouteActivity.e(getRouteDetailRsp);
                Message obtainMessage = myRouteActivity.f2000a.obtainMessage();
                obtainMessage.what = 1;
                obtainMessage.obj = getRouteDetailRsp;
                obtainMessage.arg1 = this.c ? 1 : 0;
                myRouteActivity.f2000a.sendMessage(obtainMessage);
                return;
            }
            LogUtil.a("Track_MyRouteActivity", "size:", Integer.valueOf(getRouteDetailRsp.getRouteDatas().size()), ",times:", Integer.valueOf(this.d), " request continue");
            myRouteActivity.p.addAll(getRouteDetailRsp.getRouteDatas());
            myRouteActivity.a(this.c, Long.valueOf(this.b), 0, 0L);
        }

        @Override // com.huawei.health.userprofilemgr.model.RouteResultCallBack
        public void onFailure(Throwable th) {
            MyRouteActivity myRouteActivity = this.f2005a.get();
            if (myRouteActivity != null && !myRouteActivity.isFinishing()) {
                myRouteActivity.e(this.e.longValue());
                LogUtil.b("Track_MyRouteActivity", "queryRouteDetail onFailure:", th.getMessage());
            } else {
                LogUtil.h("Track_MyRouteActivity", "getRouteDetail failed is finishing");
            }
        }
    }

    /* loaded from: classes3.dex */
    static class d {

        /* renamed from: a, reason: collision with root package name */
        private static int f2007a = 4;
        private static int c = 1;
        private static int d = 2;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
