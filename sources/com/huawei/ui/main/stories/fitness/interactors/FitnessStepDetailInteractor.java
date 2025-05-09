package com.huawei.ui.main.stories.fitness.interactors;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiAggregateListenerEx;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.step.StepServiceCardAdapter;
import com.huawei.ui.main.stories.fitness.interactors.FitnessStepDetailInteractor;
import com.huawei.ui.main.stories.soical.interactor.OperationInteractorsApi;
import com.huawei.ui.openservice.OpenServiceUtil;
import com.huawei.ui.openservice.db.control.OpenServiceControl;
import com.huawei.ui.openservice.db.model.ChildService;
import com.huawei.ui.openservice.db.model.UserServiceAuth;
import defpackage.ceb;
import defpackage.gnm;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nsy;
import defpackage.pwa;
import health.compact.a.GRSManager;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class FitnessStepDetailInteractor {

    /* renamed from: a, reason: collision with root package name */
    private View f9912a;
    private ExecutorService c;
    private Activity d;
    private Context e;
    private String f;
    private ImageView g;
    private FrameLayout i;
    private boolean j;
    private List<ChildService> l;
    private HealthRecycleView m;
    private OpenServiceControl n;
    private StepServiceCardAdapter o;
    private ImageView q;
    private RelativeLayout r;
    private HealthTextView s;
    private HealthTextView t;
    private ceb k = null;
    private List<MessageObject> h = new ArrayList();
    private Handler b = new Handler() { // from class: com.huawei.ui.main.stories.fitness.interactors.FitnessStepDetailInteractor.3
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what != 10010) {
                return;
            }
            if (!koq.b(FitnessStepDetailInteractor.this.h)) {
                FitnessStepDetailInteractor.this.f9912a.setVisibility(0);
                FitnessStepDetailInteractor.this.i.setVisibility(0);
                FitnessStepDetailInteractor.this.r.setVisibility(0);
                FitnessStepDetailInteractor.this.g.setVisibility(8);
                final MessageObject messageObject = (MessageObject) FitnessStepDetailInteractor.this.h.get(0);
                if (messageObject != null) {
                    FitnessStepDetailInteractor.this.s.setText(messageObject.getMsgTitle());
                    FitnessStepDetailInteractor.this.t.setText(pwa.a(FitnessStepDetailInteractor.this.e, messageObject.getCreateTime()));
                    nrf.cIS_(FitnessStepDetailInteractor.this.q, messageObject.getImgUri(), (int) FitnessStepDetailInteractor.this.e.getResources().getDimension(R.dimen._2131364527_res_0x7f0a0aaf), 0, 0);
                    FitnessStepDetailInteractor.this.r.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.interactors.FitnessStepDetailInteractor.3.4
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            FitnessStepDetailInteractor.this.c(messageObject);
                            ViewClickInstrumentation.clickOnView(view);
                        }
                    });
                    return;
                }
                LogUtil.b("SCUI_FitnessStepDetailInteractor", "firstMessageObject is null");
                return;
            }
            LogUtil.a("SCUI_FitnessStepDetailInteractor", "initStepDetailWalkInfo no message");
            FitnessStepDetailInteractor.this.f9912a.setVisibility(8);
            FitnessStepDetailInteractor.this.i.setVisibility(8);
        }
    };
    private final OperationInteractorsApi p = (OperationInteractorsApi) Services.a("OperationBundle", OperationInteractorsApi.class);

    public FitnessStepDetailInteractor(Context context) {
        this.e = context;
    }

    public void duG_(Activity activity, boolean z) {
        this.d = activity;
        this.j = Utils.o();
        this.c = Executors.newSingleThreadExecutor();
        if (this.j || !z) {
            return;
        }
        activity.findViewById(R.id.extension).setVisibility(0);
        duE_(activity);
        duF_(activity);
    }

    public void e() {
        Handler handler = this.b;
        if (handler != null) {
            handler.removeMessages(10010);
        }
        ExecutorService executorService = this.c;
        if (executorService != null) {
            executorService.shutdownNow();
            this.c = null;
        }
    }

    private void duE_(Activity activity) {
        this.f9912a = nsy.cMc_(activity, R.id.step_detail_walk_info_view_divider);
        this.i = (FrameLayout) nsy.cMc_(activity, R.id.fl_step_detail_operation_and_walk_info);
        this.r = (RelativeLayout) nsy.cMc_(activity, R.id.step_detail_walk_info_view);
        this.g = (ImageView) nsy.cMc_(activity, R.id.step_detail_operation_info_view);
        this.s = (HealthTextView) nsy.cMc_(activity, R.id.step_detail_walk_info_view_title);
        this.t = (HealthTextView) nsy.cMc_(activity, R.id.step_detail_walk_info_view_time);
        this.q = (ImageView) nsy.cMc_(activity, R.id.step_detail_walk_info_view_img);
        String b2 = SharedPreferenceManager.b(this.e, Integer.toString(10011), "OPERATION_ACTIVITY_STEP");
        LogUtil.a("SCUI_FitnessStepDetailInteractor", "initOperationAndWalkInfo activity1 = ", b2);
        try {
            if (!TextUtils.isEmpty(b2)) {
                this.k = this.p.expoundOperationActivity(new JSONObject(b2));
            }
        } catch (JSONException e) {
            LogUtil.b("SCUI_FitnessStepDetailInteractor", "Json data error! JSONException:", e.getMessage());
        }
        LogUtil.a("SCUI_FitnessStepDetailInteractor", "initOperationAndWalkInfo mOperationObject = ", this.k);
        ceb cebVar = this.k;
        if (cebVar != null && !b(cebVar.n())) {
            b();
        } else {
            d();
        }
    }

    private void d() {
        ExecutorService executorService = this.c;
        if (executorService != null) {
            executorService.execute(new Runnable() { // from class: com.huawei.ui.main.stories.fitness.interactors.FitnessStepDetailInteractor.4
                @Override // java.lang.Runnable
                public void run() {
                    for (MessageObject messageObject : ((MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class)).getMessages(CommonUtil.WALK_INFO)) {
                        if (messageObject != null && !TextUtils.isEmpty(messageObject.getImgUri()) && !TextUtils.isEmpty(messageObject.getDetailUri())) {
                            FitnessStepDetailInteractor.this.h.add(messageObject);
                        }
                    }
                    LogUtil.a("SCUI_FitnessStepDetailInteractor", "initStepDetailWalkInfo mInfoMessageList size = ", Integer.valueOf(FitnessStepDetailInteractor.this.h.size()));
                    FitnessStepDetailInteractor.this.b.sendEmptyMessage(10010);
                }
            });
        }
    }

    private void b() {
        this.f9912a.setVisibility(0);
        this.i.setVisibility(0);
        this.r.setVisibility(8);
        this.g.setVisibility(0);
        nrf.cIS_(this.g, this.k.d(), (int) this.e.getResources().getDimension(R.dimen._2131364527_res_0x7f0a0aaf), 0, 0);
        this.g.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.interactors.FitnessStepDetailInteractor.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GRSManager.a(FitnessStepDetailInteractor.this.e).e("domainContentcenterDbankcdnNew", new GrsQueryCallback() { // from class: com.huawei.ui.main.stories.fitness.interactors.FitnessStepDetailInteractor.2.2
                    @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                    public void onCallBackSuccess(String str) {
                        LogUtil.c("SCUI_FitnessStepDetailInteractor", "GRSManager onCallBackSuccess ACTIVITY_KEY url = ", str);
                        if (FitnessStepDetailInteractor.this.p.judgeVersionSupport(FitnessStepDetailInteractor.this.k.i())) {
                            FitnessStepDetailInteractor.this.d(str, FitnessStepDetailInteractor.this.k);
                        } else {
                            FitnessStepDetailInteractor.this.c();
                        }
                    }

                    @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                    public void onCallBackFail(int i) {
                        LogUtil.c("SCUI_FitnessStepDetailInteractor", "GRSManager onCallBackFail ACTIVITY_KEY index = ", Integer.valueOf(i));
                    }
                });
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        new NoTitleCustomAlertDialog.Builder(this.e).e(this.e.getResources().getString(R$string.IDS_hw_update_app_tips)).czB_(R$string.IDS_user_permission_know, R.color._2131296970_res_0x7f0902ca, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.interactors.FitnessStepDetailInteractor.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e().show();
    }

    public void d(String str, ceb cebVar) {
        if (cebVar == null) {
            LogUtil.h("SCUI_FitnessStepDetailInteractor", "gotoOperationActivityDetail operationObject == null");
            return;
        }
        String operationActivityUrl = this.p.getOperationActivityUrl(str, cebVar);
        Intent intent = new Intent(this.d, (Class<?>) WebViewActivity.class);
        intent.putExtra("url", operationActivityUrl);
        intent.putExtra("EXTRA_BI_ID", cebVar.c());
        intent.putExtra("EXTRA_BI_NAME", cebVar.b());
        intent.putExtra("EXTRA_BI_SOURCE", "StepDetail");
        intent.putExtra("EXTRA_BI_SHOWTIME", "SHOW_TIME_BI");
        gnm.aPB_(this.d, intent);
    }

    public void c(MessageObject messageObject) {
        if (messageObject == null) {
            LogUtil.h("SCUI_FitnessStepDetailInteractor", "gotoWalkInfoActivityDetail messageObject == null");
            return;
        }
        String msgId = messageObject.getMsgId();
        String detailUri = messageObject.getDetailUri();
        if (msgId == null || detailUri == null) {
            LogUtil.h("SCUI_FitnessStepDetailInteractor", "Information messageId||detailUrl is null");
            return;
        }
        Intent intent = new Intent(this.d, (Class<?>) WebViewActivity.class);
        intent.putExtra("url", detailUri);
        intent.putExtra("type", "RecommendInfo");
        intent.putExtra("title", this.d.getResources().getString(R$string.IDS_social_information));
        intent.putExtra("EXTRA_BI_ID", msgId);
        intent.putExtra("EXTRA_BI_NAME", messageObject.getMsgTitle());
        intent.putExtra("EXTRA_BI_SOURCE", "INFO");
        intent.putExtra("EXTRA_BI_SHOWTIME", "SHOW_TIME_BI");
        gnm.aPB_(this.d, intent);
    }

    public boolean b(String str) {
        if (str == null) {
            LogUtil.h("SCUI_FitnessStepDetailInteractor", "isOperationActivityOver dateString == null");
            return true;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            long time = simpleDateFormat.parse(str).getTime();
            LogUtil.a("SCUI_FitnessStepDetailInteractor", "isOperationActivityOver activityEndTime =", Long.valueOf(time), "System.currentTimeMillis()=", Long.valueOf(System.currentTimeMillis()));
            return time <= System.currentTimeMillis();
        } catch (ParseException unused) {
            LogUtil.b("SCUI_FitnessStepDetailInteractor", "isOperationActivityOver ParseException");
            return false;
        }
    }

    private void a(ChildService childService) {
        UserServiceAuth userServiceAuth = new UserServiceAuth();
        userServiceAuth.configHuid(this.f);
        userServiceAuth.configServiceID(childService.getServiceID());
        userServiceAuth.configAuthType(1);
        this.n.insertOrUpdateUserAuth(userServiceAuth);
        Intent intent = new Intent(this.d, (Class<?>) WebViewActivity.class);
        intent.putExtra("url", childService.getUrl());
        intent.putExtra("title", childService.getServiceName());
        intent.putExtra("EXTRA_BI_ID", childService.getServiceID());
        intent.putExtra("EXTRA_BI_NAME", childService.getServiceName());
        intent.putExtra("EXTRA_BI_SOURCE", "StepDetail");
        intent.putExtra("type", Constants.OPEN_SERVICE_TYPE);
        gnm.aPB_(this.d, intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(ChildService childService) {
        UserServiceAuth queryServiceAuth = this.n.queryServiceAuth(this.f, childService.getServiceID());
        if (queryServiceAuth != null && queryServiceAuth.fetchAuthType() == 1) {
            Intent intent = new Intent(this.d, (Class<?>) WebViewActivity.class);
            intent.putExtra("url", childService.getUrl());
            intent.putExtra("title", childService.getServiceName());
            intent.putExtra("EXTRA_BI_ID", childService.getServiceID());
            intent.putExtra("EXTRA_BI_NAME", childService.getServiceName());
            intent.putExtra("EXTRA_BI_SOURCE", "StepDetail");
            intent.putExtra("type", Constants.OPEN_SERVICE_TYPE);
            gnm.aPB_(this.d, intent);
            return;
        }
        if (childService.getHmsAuth() != 2) {
            a(childService);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void duF_(Activity activity) {
        this.f = LoginInit.getInstance(this.d).getUsetId();
        this.n = OpenServiceControl.getInstance(this.d);
        HealthSubHeader healthSubHeader = (HealthSubHeader) activity.findViewById(R.id.step_detail_services);
        this.m = (HealthRecycleView) activity.findViewById(R.id.step_service_recycleview);
        this.m.setLayoutManager(new LinearLayoutManager(this.d, 0, 0 == true ? 1 : 0) { // from class: com.huawei.ui.main.stories.fitness.interactors.FitnessStepDetailInteractor.5
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollHorizontally() {
                return true;
            }
        });
        BaseActivity.setViewSafeRegion(false, this.m);
        if (this.m.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.m.getLayoutParams();
            this.m.setPadding(0, 0, 0, 0);
            int marginStart = layoutParams.getMarginStart();
            layoutParams.setMarginEnd(layoutParams.getMarginEnd());
            layoutParams.setMarginStart(marginStart);
            this.m.setLayoutParams(layoutParams);
        }
        if (TextUtils.isEmpty(this.f)) {
            return;
        }
        List<ChildService> queryServiceByLocation = this.n.queryServiceByLocation(OpenServiceUtil.Location.STEP);
        this.l = queryServiceByLocation;
        if (koq.c(queryServiceByLocation)) {
            StepServiceCardAdapter stepServiceCardAdapter = new StepServiceCardAdapter(this.e, this.l);
            this.o = stepServiceCardAdapter;
            stepServiceCardAdapter.e(new StepServiceCardAdapter.OnItemClickListener() { // from class: com.huawei.ui.main.stories.fitness.interactors.FitnessStepDetailInteractor.9
                @Override // com.huawei.ui.main.stories.fitness.activity.step.StepServiceCardAdapter.OnItemClickListener
                public void onItemClick(View view, int i) {
                    if (koq.b(FitnessStepDetailInteractor.this.l, i)) {
                        return;
                    }
                    FitnessStepDetailInteractor fitnessStepDetailInteractor = FitnessStepDetailInteractor.this;
                    fitnessStepDetailInteractor.c((ChildService) fitnessStepDetailInteractor.l.get(i));
                }
            });
            this.m.setHasFixedSize(true);
            this.m.setNestedScrollingEnabled(false);
            this.m.setAdapter(this.o);
            return;
        }
        healthSubHeader.setVisibility(8);
        this.m.setVisibility(8);
    }

    public void d(final int i, final Map<Integer, String> map, final Date date, final IBaseResponseCallback iBaseResponseCallback) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: pwl
            @Override // java.lang.Runnable
            public final void run() {
                FitnessStepDetailInteractor.c(i, map, date, iBaseResponseCallback);
            }
        });
    }

    public static /* synthetic */ void c(int i, Map map, Date date, IBaseResponseCallback iBaseResponseCallback) {
        ArrayList arrayList = new ArrayList();
        int[] iArr = {i};
        for (Map.Entry entry : map.entrySet()) {
            String[] strArr = {"dayStepSum_" + entry.getKey()};
            HiAggregateOption hiAggregateOption = new HiAggregateOption();
            int b2 = DateFormatUtil.b(date.getTime());
            hiAggregateOption.setTimeInterval(String.valueOf(b2), String.valueOf(b2), HiDataReadOption.TimeFormatType.DATE_FORMAT_YYYY_MM_DD);
            hiAggregateOption.setType(iArr);
            hiAggregateOption.setGroupUnitType(3);
            hiAggregateOption.setAggregateType(1);
            hiAggregateOption.setSortOrder(1);
            hiAggregateOption.setConstantsKey(strArr);
            hiAggregateOption.setDeviceUuid((String) entry.getValue());
            hiAggregateOption.setReadType(2);
            arrayList.add(hiAggregateOption);
        }
        HiHealthManager.d(BaseApplication.e()).aggregateHiHealthDataEx(arrayList, new b(iBaseResponseCallback, map));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static class b implements HiAggregateListenerEx {
        private final WeakReference<IBaseResponseCallback> b;
        private final Map<Integer, String> e;

        private b(IBaseResponseCallback iBaseResponseCallback, Map<Integer, String> map) {
            this.b = new WeakReference<>(iBaseResponseCallback);
            this.e = map;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListenerEx
        public void onResult(SparseArray<List<HiHealthData>> sparseArray, int i, int i2) {
            LogUtil.a("SCUI_FitnessStepDetailInteractor", "getDayTotalStepDataById dataList is ", sparseArray);
            IBaseResponseCallback iBaseResponseCallback = this.b.get();
            if (iBaseResponseCallback == null || this.e == null) {
                LogUtil.h("SCUI_FitnessStepDetailInteractor", "callback == null || deviceIdMap == null");
                return;
            }
            if (sparseArray == null) {
                LogUtil.h("SCUI_FitnessStepDetailInteractor", "getDayTotalStepDataById dataList is null");
                return;
            }
            if (sparseArray.size() == 0) {
                LogUtil.h("SCUI_FitnessStepDetailInteractor", "getDayTotalStepDataById dataList is empty");
                iBaseResponseCallback.d(1, null);
                return;
            }
            ArrayList arrayList = new ArrayList(10);
            for (int i3 = 0; i3 < this.e.size(); i3++) {
                if (sparseArray.get(i3) != null) {
                    arrayList.addAll(sparseArray.get(i3));
                }
            }
            iBaseResponseCallback.d(0, arrayList);
        }
    }

    public void d(final String str, final int i, final IBaseResponseCallback iBaseResponseCallback) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: pwq
            @Override // java.lang.Runnable
            public final void run() {
                FitnessStepDetailInteractor.d(i, str, iBaseResponseCallback);
            }
        });
    }

    public static /* synthetic */ void d(int i, String str, IBaseResponseCallback iBaseResponseCallback) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(String.valueOf(i), String.valueOf(i), HiDataReadOption.TimeFormatType.DATE_FORMAT_YYYY_MM_DD);
        hiDataReadOption.setType(new int[]{2});
        hiDataReadOption.setReadType(2);
        hiDataReadOption.setDeviceUuid(str);
        HiHealthManager.d(BaseApplication.e()).readHiHealthData(hiDataReadOption, new c(iBaseResponseCallback));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static class c implements HiDataReadResultListener {
        private final WeakReference<IBaseResponseCallback> b;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        c(IBaseResponseCallback iBaseResponseCallback) {
            this.b = new WeakReference<>(iBaseResponseCallback);
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            if (!(obj instanceof SparseArray)) {
                LogUtil.h("SCUI_FitnessStepDetailInteractor", "getDayStepDataById the data is not instanceof SparseArray");
                return;
            }
            IBaseResponseCallback iBaseResponseCallback = this.b.get();
            if (iBaseResponseCallback == null) {
                LogUtil.h("SCUI_FitnessStepDetailInteractor", "IBaseResponseCallback is null");
                return;
            }
            SparseArray sparseArray = (SparseArray) obj;
            if (sparseArray.size() == 0) {
                LogUtil.h("SCUI_FitnessStepDetailInteractor", "getDayStepDataById the map is empty");
                iBaseResponseCallback.d(1, null);
                return;
            }
            Object obj2 = sparseArray.get(2);
            if (!(obj2 instanceof List)) {
                LogUtil.h("SCUI_FitnessStepDetailInteractor", "getDayStepDataById the mapObject is instanceof List");
                iBaseResponseCallback.d(1, null);
            } else {
                iBaseResponseCallback.d(0, (List) obj2);
            }
        }
    }
}
