package com.huawei.ui.main.stories.configuredpage;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import com.huawei.health.R;
import com.huawei.health.sport.configuredpage.ConfiguredPageItemDecoration;
import com.huawei.health.userlabelmgr.api.UserLabelServiceApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.httputils.pluginoperation.HttpResCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.operation.utils.ActivityHtmlPathApi;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.soical.adpters.SocialActRecyclerAdapter;
import com.huawei.ui.main.stories.soical.interactor.OperationInteractorsApi;
import defpackage.ceb;
import defpackage.jdx;
import defpackage.koq;
import defpackage.nrr;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class ActivityListFragment extends BaseFragment implements View.OnClickListener {
    private HealthRecycleView b;
    private View c;
    private HealthSubHeader d;
    private LinearLayout e;
    private Context g;
    private int j;
    private final OperationInteractorsApi k;
    private int m;
    private JSONArray f = null;
    private List<ceb> n = new ArrayList(2);

    /* renamed from: a, reason: collision with root package name */
    private SocialActRecyclerAdapter f9683a = null;
    private boolean i = true;
    private Pair<Integer, Integer> o = BaseActivity.getSafeRegionWidth();
    private Handler h = new Handler() { // from class: com.huawei.ui.main.stories.configuredpage.ActivityListFragment.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                return;
            }
            if (message.what == 1 && (message.obj instanceof List)) {
                ActivityListFragment.this.d((List) message.obj);
            }
            super.handleMessage(message);
        }
    };

    public ActivityListFragment() {
        LogUtil.a("Opera_activityListFragment", "ActivityListFragment()");
        this.k = (OperationInteractorsApi) Services.a("OperationBundle", OperationInteractorsApi.class);
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("Opera_activityListFragment", "onCreate");
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.g = getActivity();
        Bundle arguments = getArguments();
        if (arguments == null) {
            LogUtil.h("Opera_activityListFragment", "onCreateView Bundle is null");
        } else {
            this.m = arguments.getInt("PAGE_TYPE");
        }
        LogUtil.a("Opera_activityListFragment", "mPageType: ", Integer.valueOf(this.m));
        View inflate = layoutInflater.inflate(R.layout.fragment_activity_card_list, viewGroup, false);
        this.c = inflate;
        this.e = (LinearLayout) inflate.findViewById(R.id.activity_list_container);
        HealthSubHeader healthSubHeader = (HealthSubHeader) this.c.findViewById(R.id.activity_list_title);
        this.d = healthSubHeader;
        ViewGroup.LayoutParams layoutParams = healthSubHeader.getLayoutParams();
        LinearLayout.LayoutParams layoutParams2 = layoutParams instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) layoutParams : null;
        if (layoutParams2 != null) {
            int i = layoutParams2.leftMargin;
            int i2 = layoutParams2.rightMargin;
            layoutParams2.leftMargin = i + ((Integer) this.o.first).intValue();
            layoutParams2.rightMargin = i2 + ((Integer) this.o.second).intValue();
        }
        this.d.setSubHeaderBackgroundColor(0);
        this.d.setMoreText("");
        this.d.setMoreViewClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.configuredpage.ActivityListFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ActivityListFragment.this.b();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        jdx.b(new Runnable() { // from class: com.huawei.ui.main.stories.configuredpage.ActivityListFragment.2
            @Override // java.lang.Runnable
            public void run() {
                ActivityListFragment.this.c();
            }
        });
        this.j = nrr.b(this.g);
        CommonUtil.a("TimeEat_activityListFragment", "Leave onCreateView");
        return this.c;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        LogUtil.a("Opera_activityListFragment", "onConfigurationChanged");
        a();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.b = (HealthRecycleView) this.c.findViewById(R.id.activity_recycle_view);
        SocialActRecyclerAdapter socialActRecyclerAdapter = new SocialActRecyclerAdapter(this.g, this.n);
        this.f9683a = socialActRecyclerAdapter;
        this.b.setAdapter(socialActRecyclerAdapter);
        a();
    }

    private void a() {
        int i;
        int i2;
        if (nsn.ag(this.g)) {
            i2 = this.j;
            i = 2;
        } else {
            i = 1;
            i2 = 0;
        }
        if (this.b.getItemDecorationCount() > 0 && this.b.getItemDecorationAt(0) != null) {
            this.b.removeItemDecorationAt(0);
        }
        int[] iArr = new int[4];
        iArr[1] = 0;
        iArr[3] = 0;
        int i3 = this.m;
        if (i3 == 6 || i3 == 4 || i3 == 5 || i3 == 1) {
            iArr[0] = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e) + ((Integer) this.o.first).intValue();
            iArr[2] = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d) + ((Integer) this.o.second).intValue();
        } else {
            iArr[0] = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131364635_res_0x7f0a0b1b) + ((Integer) this.o.first).intValue();
            iArr[2] = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131364634_res_0x7f0a0b1a) + ((Integer) this.o.second).intValue();
        }
        this.b.addItemDecoration(new ConfiguredPageItemDecoration(i2, BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8), i, iArr));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.g, i);
        gridLayoutManager.setOrientation(1);
        this.b.setLayoutManager(gridLayoutManager);
        this.b.setHasFixedSize(true);
        this.b.setNestedScrollingEnabled(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        GRSManager.a(this.g).e("domainContentcenterDbankcdnNew", new GrsQueryCallback() { // from class: com.huawei.ui.main.stories.configuredpage.ActivityListFragment.4
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                String str2 = str + ((ActivityHtmlPathApi) Services.c("PluginOperation", ActivityHtmlPathApi.class)).getActivityHtmlPath() + Constants.ACTIVITY_HTML;
                Intent intent = new Intent(ActivityListFragment.this.g, (Class<?>) WebViewActivity.class);
                intent.putExtra("url", str2);
                intent.putExtra("type", "ACT_MORE");
                intent.putExtra("EXTRA_BI_ID", "");
                intent.putExtra("EXTRA_BI_NAME", ActivityListFragment.this.g.getResources().getString(R$string.IDS_main_home_bottom_text_activity));
                intent.putExtra("EXTRA_BI_SOURCE", "ACT_MORE");
                intent.putExtra("EXTRA_BI_SHOWTIME", "SHOW_TIME_BI");
                intent.setFlags(268435456);
                try {
                    ActivityListFragment.this.g.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    LogUtil.b("Opera_activityListFragment", "ActivityNotFoundException", e.getMessage());
                }
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                LogUtil.c("Opera_activityListFragment", "GRSManager onCallBackFail HEALTH_RECOMMEND code = ", Integer.valueOf(i));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (LoginInit.getInstance(this.g).isKidAccount() || !Utils.o()) {
            LogUtil.a("Opera_activityListFragment", "refreshActivityList isKidAccount return.");
        } else {
            this.k.getOperationList(this.g.getApplicationContext(), -1, null, new HttpResCallback() { // from class: com.huawei.ui.main.stories.configuredpage.ActivityListFragment.3
                @Override // com.huawei.hwcommonmodel.utils.httputils.pluginoperation.HttpResCallback
                public void onFinished(int i, String str) {
                    LogUtil.a("Opera_activityListFragment", "getActivities resCode = ", Integer.valueOf(i));
                    if (i != 200) {
                        return;
                    }
                    if (!TextUtils.isEmpty(str)) {
                        ActivityListFragment.this.e(str);
                    } else {
                        LogUtil.h("Opera_activityListFragment", "refreshActivityList result is empty.");
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("resultCode");
            if (!TextUtils.isEmpty(string) && "0".equals(string)) {
                String optString = jSONObject.optString("currentTime");
                c(optString);
                JSONArray jSONArray = jSONObject.getJSONArray("activities");
                if (jSONArray.length() >= 0 && !jSONArray.equals(this.f)) {
                    this.f = jSONArray;
                    ArrayList arrayList = new ArrayList(2);
                    ArrayList arrayList2 = new ArrayList(2);
                    List<String> allLabels = ((UserLabelServiceApi) Services.c("HWUserLabelMgr", UserLabelServiceApi.class)).getAllLabels(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011));
                    for (int i = 0; i < this.f.length(); i++) {
                        ceb expoundOperationActivity = this.k.expoundOperationActivity(this.f.getJSONObject(i));
                        if (expoundOperationActivity != null && this.k.getActivityStatus(optString, expoundOperationActivity.t(), expoundOperationActivity.n()) != -1) {
                            if (this.k.isMatchPage(this.m, this.k.getActivityPageType(expoundOperationActivity))) {
                                if (expoundOperationActivity.h() > -1 && !arrayList.contains(expoundOperationActivity)) {
                                    arrayList.add(expoundOperationActivity);
                                }
                                if (expoundOperationActivity.h() == -1 && this.k.isUserLabelActivity(expoundOperationActivity, allLabels) && !arrayList2.contains(expoundOperationActivity)) {
                                    arrayList2.add(expoundOperationActivity);
                                }
                            }
                        }
                    }
                    b(arrayList, arrayList2);
                }
            }
        } catch (JSONException e) {
            LogUtil.b("Opera_activityListFragment", "Json data error! JSONException:", e.getMessage());
        }
    }

    private void c(String str) {
        SharedPreferenceManager.e(this.g, Integer.toString(10011), OperationInteractorsApi.OPERATION_ACTIVITY_CURRENT_TIME, str, new StorageParams());
    }

    private void b(List<ceb> list, List<ceb> list2) {
        if (koq.b(list)) {
            list = list2;
        }
        Message obtainMessage = this.h.obtainMessage();
        obtainMessage.what = 1;
        obtainMessage.obj = list;
        this.h.sendMessage(obtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<ceb> list) {
        LogUtil.a("Opera_activityListFragment", "updateActivityCardList");
        if (koq.b(list)) {
            this.e.setVisibility(8);
            return;
        }
        this.n.clear();
        this.n.addAll(list);
        this.f9683a.notifyDataSetChanged();
        this.e.setVisibility(0);
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        LogUtil.a("Opera_activityListFragment", "ConfiguredPageFragment onResume");
        if (!this.i && getUserVisibleHint()) {
            jdx.b(new Runnable() { // from class: com.huawei.ui.main.stories.configuredpage.ActivityListFragment.9
                @Override // java.lang.Runnable
                public void run() {
                    ActivityListFragment.this.c();
                }
            });
        }
        this.i = false;
        super.onResume();
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        LogUtil.a("Opera_activityListFragment", "ConfiguredPageFragment onPause");
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        Handler handler = this.h;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        super.onDestroy();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ViewClickInstrumentation.clickOnView(view);
    }
}
