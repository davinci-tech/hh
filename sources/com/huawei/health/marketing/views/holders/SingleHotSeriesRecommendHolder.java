package com.huawei.health.marketing.views.holders;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.SingleDailyMomentContent;
import com.huawei.health.marketing.datatype.SingleHotSeriesRecommendContent;
import com.huawei.health.marketing.request.ActivityInfoListFactory;
import com.huawei.health.marketing.request.RespSleepAudioInfo;
import com.huawei.health.marketing.request.SleepAudioInfo;
import com.huawei.health.marketing.request.SleepAudioInfoApi;
import com.huawei.health.marketing.request.SleepAudioInfoReq;
import com.huawei.health.marketing.views.ColumnLinearLayout;
import com.huawei.health.marketing.views.holders.SingleHotSeriesRecommendHolder;
import com.huawei.health.vip.api.VipApi;
import com.huawei.health.vip.api.VipCallback;
import com.huawei.health.vip.datatypes.UserMemberInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.pluginfitnessadvice.Workout;
import com.huawei.pluginfitnessadvice.WorkoutPackageInfo;
import com.huawei.pluginfitnessadvice.audio.SleepAudioPackage;
import com.huawei.pluginfitnessadvice.audio.SleepAudioSeries;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.eil;
import defpackage.ffk;
import defpackage.ffl;
import defpackage.gpn;
import defpackage.ixx;
import defpackage.koq;
import defpackage.lqi;
import defpackage.lql;
import defpackage.nrf;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.Services;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class SingleHotSeriesRecommendHolder extends RecyclerView.ViewHolder {

    /* renamed from: a, reason: collision with root package name */
    private boolean f2913a;
    private Context b;
    private ImageView c;
    private HealthTextView d;
    private ImageView e;
    private HealthTextView f;
    private HealthTextView g;
    private HealthTextView h;
    private HealthTextView i;
    private HealthButton j;
    private HealthTextView k;
    private HealthTextView l;
    private HealthTextView m;
    private HealthTextView n;
    private HealthTextView o;
    private int p;
    private int q;
    private HealthTextView r;
    private Handler s;
    private ResourceBriefInfo t;
    private long v;

    public SingleHotSeriesRecommendHolder(Context context, View view, ResourceBriefInfo resourceBriefInfo, int i) {
        super(view);
        this.f2913a = false;
        this.s = new Handler(Looper.getMainLooper()) { // from class: com.huawei.health.marketing.views.holders.SingleHotSeriesRecommendHolder.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message == null) {
                    return;
                }
                super.handleMessage(message);
                if (message.what == 101 && (message.obj instanceof SingleHotSeriesRecommendContent)) {
                    SingleHotSeriesRecommendContent singleHotSeriesRecommendContent = (SingleHotSeriesRecommendContent) message.obj;
                    SingleHotSeriesRecommendHolder.this.q(singleHotSeriesRecommendContent);
                    SingleHotSeriesRecommendHolder.this.b(singleHotSeriesRecommendContent, false);
                }
                if (message.what == 102 && (message.obj instanceof SingleHotSeriesRecommendContent)) {
                    SingleHotSeriesRecommendContent singleHotSeriesRecommendContent2 = (SingleHotSeriesRecommendContent) message.obj;
                    SingleHotSeriesRecommendHolder.this.o.setVisibility(8);
                    SingleHotSeriesRecommendHolder.this.b(singleHotSeriesRecommendContent2, true);
                }
            }
        };
        if (view == null) {
            LogUtil.h("SingleHotSeriesRecommendHolder", "getOneGridBigHolder() itemView is null.");
            return;
        }
        this.b = context;
        this.p = i;
        this.t = resourceBriefInfo;
        this.e = (ImageView) view.findViewById(R.id.course_item_bg_img);
        this.c = (ImageView) view.findViewById(R.id.course_item_bleed_img);
        this.k = (HealthTextView) view.findViewById(R.id.item_courses_theme);
        this.h = (HealthTextView) view.findViewById(R.id.item_courses_flag);
        this.d = (HealthTextView) view.findViewById(R.id.item_courses_description);
        this.l = (HealthTextView) view.findViewById(R.id.item_courses_more);
        this.f = (HealthTextView) view.findViewById(R.id.item_courses_detail);
        this.r = (HealthTextView) view.findViewById(R.id.item_courses_title);
        this.i = (HealthTextView) view.findViewById(R.id.item_courses_first_num);
        this.g = (HealthTextView) view.findViewById(R.id.item_courses_first_desc);
        this.m = (HealthTextView) view.findViewById(R.id.item_courses_second_num);
        this.n = (HealthTextView) view.findViewById(R.id.item_courses_second_desc);
        this.o = (HealthTextView) view.findViewById(R.id.item_courses_preview_link);
        this.j = (HealthButton) view.findViewById(R.id.item_courses_experience);
    }

    public void d(final SingleHotSeriesRecommendContent singleHotSeriesRecommendContent, int i) {
        this.v = System.currentTimeMillis();
        this.q = i;
        nrf.cIU_(singleHotSeriesRecommendContent.getBackgroundPicture(), this.e, nrf.d);
        nrf.cJB_(singleHotSeriesRecommendContent.getPicture(), this.c);
        if (singleHotSeriesRecommendContent.isThemeVisibility()) {
            this.k.setText(singleHotSeriesRecommendContent.getTheme());
            this.k.setVisibility(0);
        } else {
            this.k.setVisibility(8);
        }
        p(singleHotSeriesRecommendContent);
        t(singleHotSeriesRecommendContent);
        if (singleHotSeriesRecommendContent.isDetailVisibility()) {
            this.f.setText(singleHotSeriesRecommendContent.getDetail());
            this.f.setVisibility(0);
        } else {
            this.f.setVisibility(8);
        }
        if (TextUtils.isEmpty(singleHotSeriesRecommendContent.getTitle())) {
            this.r.setVisibility(8);
        } else {
            this.r.setVisibility(0);
            this.r.setText(singleHotSeriesRecommendContent.getTitle());
        }
        this.i.setText(singleHotSeriesRecommendContent.getDataOne());
        this.g.setText(singleHotSeriesRecommendContent.getDataOneDescription());
        this.m.setText(singleHotSeriesRecommendContent.getDataTwo());
        this.n.setText(singleHotSeriesRecommendContent.getDataTwoDescription());
        this.o.setVisibility(8);
        this.j.setVisibility(8);
        j(singleHotSeriesRecommendContent);
        this.s.postDelayed(new Runnable() { // from class: elz
            @Override // java.lang.Runnable
            public final void run() {
                SingleHotSeriesRecommendHolder.this.b(singleHotSeriesRecommendContent);
            }
        }, 100L);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void j(SingleHotSeriesRecommendContent singleHotSeriesRecommendContent) {
        char c2;
        String itemCategory = singleHotSeriesRecommendContent.getItemCategory();
        if (TextUtils.isEmpty(itemCategory)) {
            q(singleHotSeriesRecommendContent);
            b(singleHotSeriesRecommendContent, false);
            return;
        }
        itemCategory.hashCode();
        switch (itemCategory.hashCode()) {
            case -903439607:
                if (itemCategory.equals("WorkoutPackage")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -797118430:
                if (itemCategory.equals("SleepingSeries")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 1581516639:
                if (itemCategory.equals("SleepAudio")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 2024262715:
                if (itemCategory.equals(SingleDailyMomentContent.COURSE_TYPE)) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        if (c2 == 0) {
            a(singleHotSeriesRecommendContent);
            return;
        }
        if (c2 == 1) {
            d(singleHotSeriesRecommendContent);
            return;
        }
        if (c2 == 2) {
            c(singleHotSeriesRecommendContent);
        } else if (c2 == 3) {
            e(singleHotSeriesRecommendContent);
        } else {
            q(singleHotSeriesRecommendContent);
            b(singleHotSeriesRecommendContent, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l, reason: merged with bridge method [inline-methods] */
    public void a(final SingleHotSeriesRecommendContent singleHotSeriesRecommendContent) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: elv
                @Override // java.lang.Runnable
                public final void run() {
                    SingleHotSeriesRecommendHolder.this.a(singleHotSeriesRecommendContent);
                }
            });
            return;
        }
        LogUtil.a("SingleHotSeriesRecommendHolder", "enter queryWorkoutPackageById id :", singleHotSeriesRecommendContent.getDynamicDataId());
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("SingleHotSeriesRecommendHolder", "queryWorkoutPackageById : courseApi is null.");
            a(singleHotSeriesRecommendContent, false);
        } else {
            courseApi.getWorkoutPackageById(new ffk.d(singleHotSeriesRecommendContent.getDynamicDataId()).c(), new UiCallback<WorkoutPackageInfo>() { // from class: com.huawei.health.marketing.views.holders.SingleHotSeriesRecommendHolder.4
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.h("SingleHotSeriesRecommendHolder", "queryWorkoutPackageById onFailure err:", Integer.valueOf(i));
                    SingleHotSeriesRecommendHolder.this.a(singleHotSeriesRecommendContent, false);
                }

                /* JADX WARN: Code restructure failed: missing block: B:10:0x002b, code lost:
                
                    if (r3.getTransactionStatus().intValue() == 1) goto L13;
                 */
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public void onSuccess(com.huawei.pluginfitnessadvice.WorkoutPackageInfo r3) {
                    /*
                        r2 = this;
                        java.lang.String r0 = "queryWorkoutPackageById onSuccess"
                        java.lang.Object[] r0 = new java.lang.Object[]{r0}
                        java.lang.String r1 = "SingleHotSeriesRecommendHolder"
                        com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
                        if (r3 == 0) goto L20
                        java.lang.Integer r0 = r3.getCommodityFlag()
                        int r0 = r0.intValue()
                        r1 = 2
                        if (r0 != r1) goto L20
                        com.huawei.health.marketing.views.holders.SingleHotSeriesRecommendHolder r3 = com.huawei.health.marketing.views.holders.SingleHotSeriesRecommendHolder.this
                        com.huawei.health.marketing.datatype.SingleHotSeriesRecommendContent r0 = r2
                        com.huawei.health.marketing.views.holders.SingleHotSeriesRecommendHolder.b(r3, r0)
                        return
                    L20:
                        if (r3 == 0) goto L2e
                        java.lang.Integer r3 = r3.getTransactionStatus()
                        int r3 = r3.intValue()
                        r0 = 1
                        if (r3 != r0) goto L2e
                        goto L2f
                    L2e:
                        r0 = 0
                    L2f:
                        com.huawei.health.marketing.views.holders.SingleHotSeriesRecommendHolder r3 = com.huawei.health.marketing.views.holders.SingleHotSeriesRecommendHolder.this
                        com.huawei.health.marketing.datatype.SingleHotSeriesRecommendContent r1 = r2
                        com.huawei.health.marketing.views.holders.SingleHotSeriesRecommendHolder.d(r3, r1, r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.marketing.views.holders.SingleHotSeriesRecommendHolder.AnonymousClass4.onSuccess(com.huawei.pluginfitnessadvice.WorkoutPackageInfo):void");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public void c(final SingleHotSeriesRecommendContent singleHotSeriesRecommendContent) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: emd
                @Override // java.lang.Runnable
                public final void run() {
                    SingleHotSeriesRecommendHolder.this.c(singleHotSeriesRecommendContent);
                }
            });
            return;
        }
        String dynamicDataId = singleHotSeriesRecommendContent.getDynamicDataId();
        LogUtil.a("SingleHotSeriesRecommendHolder", "enter querySleepAudioById id:", dynamicDataId);
        SleepAudioInfoReq sleepAudioInfoReq = new SleepAudioInfoReq();
        sleepAudioInfoReq.setIds(dynamicDataId);
        sleepAudioInfoReq.setPageNo("1");
        sleepAudioInfoReq.setPageSize("100");
        SleepAudioInfoApi sleepAudioInfoApi = (SleepAudioInfoApi) lqi.d().b(SleepAudioInfoApi.class);
        ActivityInfoListFactory activityInfoListFactory = new ActivityInfoListFactory(BaseApplication.getContext());
        Map<String, Object> body = activityInfoListFactory.getBody(sleepAudioInfoReq);
        body.put(CloudParamKeys.CLIENT_TYPE, eil.a());
        try {
            Response<RespSleepAudioInfo> execute = sleepAudioInfoApi.getSleepAudioList(sleepAudioInfoReq.getUrl(), activityInfoListFactory.getHeaders(), lql.b(body)).execute();
            if (!execute.isOK()) {
                a(singleHotSeriesRecommendContent, false);
                return;
            }
            boolean z = true;
            LogUtil.a("SingleHotSeriesRecommendHolder", "requestSleepAudioData response is OK.");
            RespSleepAudioInfo body2 = execute.getBody();
            if (body2 != null && body2.getResultCode() == 0 && !koq.b(body2.getSleepAudios()) && body2.getSleepAudios().get(0) != null) {
                SleepAudioInfo sleepAudioInfo = body2.getSleepAudios().get(0);
                if (sleepAudioInfo.getIsVip() == 1) {
                    n(singleHotSeriesRecommendContent);
                    return;
                }
                if (sleepAudioInfo.getPurchasedStatus() != 1) {
                    z = false;
                }
                a(singleHotSeriesRecommendContent, z);
                return;
            }
            LogUtil.h("SingleHotSeriesRecommendHolder", "requestSleepAudioData result is null or error");
            a(singleHotSeriesRecommendContent, false);
        } catch (IOException | NumberFormatException unused) {
            LogUtil.b("SingleHotSeriesRecommendHolder", "requestSleepAudioData exception.");
            a(singleHotSeriesRecommendContent, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g, reason: merged with bridge method [inline-methods] */
    public void d(final SingleHotSeriesRecommendContent singleHotSeriesRecommendContent) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: elx
                @Override // java.lang.Runnable
                public final void run() {
                    SingleHotSeriesRecommendHolder.this.d(singleHotSeriesRecommendContent);
                }
            });
            return;
        }
        String dynamicDataId = singleHotSeriesRecommendContent.getDynamicDataId();
        LogUtil.a("SingleHotSeriesRecommendHolder", "enter querySleepSeriesById id:", dynamicDataId);
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("SingleHotSeriesRecommendHolder", "requestRelativeCourse : courseApi is null.");
            a(singleHotSeriesRecommendContent, false);
        } else {
            courseApi.queryAudiosPackageBySeriesId(dynamicDataId, new UiCallback<List<SleepAudioPackage>>() { // from class: com.huawei.health.marketing.views.holders.SingleHotSeriesRecommendHolder.5
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.h("SingleHotSeriesRecommendHolder", "querySleepSeriesById fail, errorCode： ", Integer.valueOf(i), ", errorInfo: ", str);
                    SingleHotSeriesRecommendHolder.this.a(singleHotSeriesRecommendContent, false);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<SleepAudioPackage> list) {
                    if (koq.b(list) || list.get(0) == null || list.get(0).getSleepAudioSeries() == null) {
                        LogUtil.h("SingleHotSeriesRecommendHolder", "querySleepSeriesById data is empty");
                        SingleHotSeriesRecommendHolder.this.a(singleHotSeriesRecommendContent, false);
                        return;
                    }
                    SleepAudioSeries sleepAudioSeries = list.get(0).getSleepAudioSeries();
                    if (sleepAudioSeries.getIsVip() == 1) {
                        SingleHotSeriesRecommendHolder.this.n(singleHotSeriesRecommendContent);
                    } else {
                        SingleHotSeriesRecommendHolder.this.a(singleHotSeriesRecommendContent, sleepAudioSeries.getPurchasedStatus() == 1);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k, reason: merged with bridge method [inline-methods] */
    public void e(final SingleHotSeriesRecommendContent singleHotSeriesRecommendContent) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: elw
                @Override // java.lang.Runnable
                public final void run() {
                    SingleHotSeriesRecommendHolder.this.e(singleHotSeriesRecommendContent);
                }
            });
            return;
        }
        LogUtil.a("SingleHotSeriesRecommendHolder", "enter queryWorkoutById id :", singleHotSeriesRecommendContent.getDynamicDataId());
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("SingleHotSeriesRecommendHolder", "queryWorkoutById : courseApi is null.");
            a(singleHotSeriesRecommendContent, false);
        } else {
            courseApi.getCourseById(new ffl.d(singleHotSeriesRecommendContent.getDynamicDataId()).b(), new UiCallback<Workout>() { // from class: com.huawei.health.marketing.views.holders.SingleHotSeriesRecommendHolder.3
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.h("SingleHotSeriesRecommendHolder", "getCourseById onFailure err:", Integer.valueOf(i));
                    SingleHotSeriesRecommendHolder.this.a(singleHotSeriesRecommendContent, false);
                }

                /* JADX WARN: Code restructure failed: missing block: B:10:0x0027, code lost:
                
                    if (r3.acquireAuthResult() == 1) goto L13;
                 */
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public void onSuccess(com.huawei.pluginfitnessadvice.Workout r3) {
                    /*
                        r2 = this;
                        java.lang.String r0 = "getCourseById onSuccess"
                        java.lang.Object[] r0 = new java.lang.Object[]{r0}
                        java.lang.String r1 = "SingleHotSeriesRecommendHolder"
                        com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
                        com.huawei.pluginfitnessadvice.FitWorkout r3 = defpackage.mod.a(r3)
                        if (r3 == 0) goto L20
                        int r0 = r3.acquireCommodityFlag()
                        r1 = 2
                        if (r0 != r1) goto L20
                        com.huawei.health.marketing.views.holders.SingleHotSeriesRecommendHolder r3 = com.huawei.health.marketing.views.holders.SingleHotSeriesRecommendHolder.this
                        com.huawei.health.marketing.datatype.SingleHotSeriesRecommendContent r0 = r2
                        com.huawei.health.marketing.views.holders.SingleHotSeriesRecommendHolder.b(r3, r0)
                        return
                    L20:
                        if (r3 == 0) goto L2a
                        int r3 = r3.acquireAuthResult()
                        r0 = 1
                        if (r3 != r0) goto L2a
                        goto L2b
                    L2a:
                        r0 = 0
                    L2b:
                        com.huawei.health.marketing.views.holders.SingleHotSeriesRecommendHolder r3 = com.huawei.health.marketing.views.holders.SingleHotSeriesRecommendHolder.this
                        com.huawei.health.marketing.datatype.SingleHotSeriesRecommendContent r1 = r2
                        com.huawei.health.marketing.views.holders.SingleHotSeriesRecommendHolder.d(r3, r1, r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.marketing.views.holders.SingleHotSeriesRecommendHolder.AnonymousClass3.onSuccess(com.huawei.pluginfitnessadvice.Workout):void");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n(final SingleHotSeriesRecommendContent singleHotSeriesRecommendContent) {
        ((VipApi) Services.c("vip", VipApi.class)).getVipInfo(new VipCallback() { // from class: com.huawei.health.marketing.views.holders.SingleHotSeriesRecommendHolder.2
            @Override // com.huawei.health.vip.api.VipCallback
            public void onSuccess(Object obj) {
                boolean z = false;
                if (obj instanceof UserMemberInfo) {
                    UserMemberInfo userMemberInfo = (UserMemberInfo) obj;
                    if (userMemberInfo.getMemberFlag() == 1 && !gpn.d(userMemberInfo)) {
                        z = true;
                    }
                    LogUtil.a("SingleHotSeriesRecommendHolder", "getVipInfo isVip:", Boolean.valueOf(z));
                }
                SingleHotSeriesRecommendHolder.this.a(singleHotSeriesRecommendContent, z);
            }

            @Override // com.huawei.health.vip.api.VipCallback
            public void onFailure(int i, String str) {
                LogUtil.a("SingleHotSeriesRecommendHolder", "getVipInfo errCode:", Integer.valueOf(i));
                SingleHotSeriesRecommendHolder.this.a(singleHotSeriesRecommendContent, false);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(SingleHotSeriesRecommendContent singleHotSeriesRecommendContent, boolean z) {
        Message obtainMessage = this.s.obtainMessage();
        obtainMessage.what = z ? 102 : 101;
        obtainMessage.obj = singleHotSeriesRecommendContent;
        this.s.sendMessage(obtainMessage);
    }

    private void t(final SingleHotSeriesRecommendContent singleHotSeriesRecommendContent) {
        if (singleHotSeriesRecommendContent.isDescriptionVisibility()) {
            this.d.setText(singleHotSeriesRecommendContent.getDescription());
            this.d.setVisibility(0);
        } else {
            this.d.setVisibility(8);
        }
        this.l.setVisibility(TextUtils.isEmpty(singleHotSeriesRecommendContent.getLink()) ? 8 : 0);
        this.l.setOnClickListener(new View.OnClickListener() { // from class: elr
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SingleHotSeriesRecommendHolder.this.arh_(singleHotSeriesRecommendContent, view);
            }
        });
        this.d.setOnClickListener(new View.OnClickListener() { // from class: elq
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SingleHotSeriesRecommendHolder.this.ari_(singleHotSeriesRecommendContent, view);
            }
        });
    }

    public /* synthetic */ void arh_(SingleHotSeriesRecommendContent singleHotSeriesRecommendContent, View view) {
        i(singleHotSeriesRecommendContent);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void ari_(SingleHotSeriesRecommendContent singleHotSeriesRecommendContent, View view) {
        i(singleHotSeriesRecommendContent);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void i(SingleHotSeriesRecommendContent singleHotSeriesRecommendContent) {
        String link = singleHotSeriesRecommendContent.getLink();
        if (TextUtils.isEmpty(link)) {
            LogUtil.h("SingleHotSeriesRecommendHolder", "detailClick linkUrl is empty, return");
            return;
        }
        LogUtil.a("SingleHotSeriesRecommendHolder", "detailClick");
        eil.a(link, this.p, this.t);
        a(1, singleHotSeriesRecommendContent.getTheme());
    }

    private void p(SingleHotSeriesRecommendContent singleHotSeriesRecommendContent) {
        if (TextUtils.isEmpty(singleHotSeriesRecommendContent.getCorner())) {
            this.h.setVisibility(8);
            return;
        }
        int color = ContextCompat.getColor(BaseApplication.getContext(), R.color._2131297002_res_0x7f0902ea);
        if (!TextUtils.isEmpty(singleHotSeriesRecommendContent.getCornerColor())) {
            color = Color.parseColor(singleHotSeriesRecommendContent.getCornerColor());
        }
        try {
            float c2 = nsn.c(BaseApplication.getContext(), 4.0f);
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(0);
            gradientDrawable.setCornerRadius(c2);
            gradientDrawable.setStroke(nsn.c(BaseApplication.getContext(), 1.0f), color);
            this.h.setBackground(gradientDrawable);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("SingleHotSeriesRecommendHolder", "IllegalArgumentException");
        }
        this.h.setTextColor(color);
        this.h.setText(singleHotSeriesRecommendContent.getCorner());
        this.h.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q(final SingleHotSeriesRecommendContent singleHotSeriesRecommendContent) {
        if (TextUtils.isEmpty(singleHotSeriesRecommendContent.getLeftButtonDesc())) {
            this.o.setVisibility(8);
            return;
        }
        this.o.setText(singleHotSeriesRecommendContent.getLeftButtonDesc());
        this.o.setVisibility(0);
        this.o.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.marketing.views.holders.SingleHotSeriesRecommendHolder.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (TextUtils.isEmpty(singleHotSeriesRecommendContent.getLeftButtonLink())) {
                    LogUtil.h("SingleHotSeriesRecommendHolder", "attemptLink is empty, return");
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    eil.c(singleHotSeriesRecommendContent.getLeftButtonLink(), SingleHotSeriesRecommendHolder.this.p, SingleHotSeriesRecommendHolder.this.t, SingleHotSeriesRecommendHolder.this.q + 1);
                    SingleHotSeriesRecommendHolder.this.a(2, singleHotSeriesRecommendContent.getTheme());
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final SingleHotSeriesRecommendContent singleHotSeriesRecommendContent, final boolean z) {
        int color = ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296927_res_0x7f09029f);
        if (!TextUtils.isEmpty(singleHotSeriesRecommendContent.getRightButtonColor())) {
            color = Color.parseColor(singleHotSeriesRecommendContent.getRightButtonColor());
        }
        try {
            float dimension = BaseApplication.getContext().getResources().getDimension(R.dimen._2131364930_res_0x7f0a0c42);
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(0);
            gradientDrawable.setCornerRadius(dimension);
            gradientDrawable.setColor(color);
            this.j.setBackground(gradientDrawable);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("SingleHotSeriesRecommendHolder", "IllegalArgumentException");
        }
        this.j.setText(z ? singleHotSeriesRecommendContent.getRightButtonDescAfter() : singleHotSeriesRecommendContent.getRightButtonDescBefore());
        this.j.setVisibility(0);
        final String rightButtonLinkAfter = z ? singleHotSeriesRecommendContent.getRightButtonLinkAfter() : singleHotSeriesRecommendContent.getRightButtonLinkBefore();
        this.j.setOnClickListener(new View.OnClickListener() { // from class: ely
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SingleHotSeriesRecommendHolder.this.arj_(rightButtonLinkAfter, z, singleHotSeriesRecommendContent, view);
            }
        });
    }

    public /* synthetic */ void arj_(String str, boolean z, SingleHotSeriesRecommendContent singleHotSeriesRecommendContent, View view) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("SingleHotSeriesRecommendHolder", "Experience url is empty, return");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            eil.c(str, this.p, this.t, this.q + 1);
            a(z ? 4 : 3, singleHotSeriesRecommendContent.getTheme());
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: o, reason: merged with bridge method [inline-methods] */
    public void b(SingleHotSeriesRecommendContent singleHotSeriesRecommendContent) {
        if (this.itemView == null || this.f2913a) {
            return;
        }
        nsy.cMa_(this.itemView, new c(this, singleHotSeriesRecommendContent));
        nsy.cMb_(this.itemView, new c(this, singleHotSeriesRecommendContent));
    }

    static class c implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<SingleHotSeriesRecommendHolder> f2915a;
        private final SingleHotSeriesRecommendContent d;

        public c(SingleHotSeriesRecommendHolder singleHotSeriesRecommendHolder, SingleHotSeriesRecommendContent singleHotSeriesRecommendContent) {
            this.f2915a = new WeakReference<>(singleHotSeriesRecommendHolder);
            this.d = singleHotSeriesRecommendContent;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            SingleHotSeriesRecommendHolder singleHotSeriesRecommendHolder = this.f2915a.get();
            if (singleHotSeriesRecommendHolder == null) {
                return;
            }
            if (!singleHotSeriesRecommendHolder.f2913a) {
                singleHotSeriesRecommendHolder.h(this.d);
            } else {
                nsy.cMf_(singleHotSeriesRecommendHolder.itemView, this);
            }
        }

        @Override // android.view.ViewTreeObserver.OnScrollChangedListener
        public void onScrollChanged() {
            SingleHotSeriesRecommendHolder singleHotSeriesRecommendHolder = this.f2915a.get();
            if (singleHotSeriesRecommendHolder == null) {
                return;
            }
            if (!singleHotSeriesRecommendHolder.f2913a) {
                singleHotSeriesRecommendHolder.h(this.d);
            } else {
                nsy.cMg_(singleHotSeriesRecommendHolder.itemView, this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h(SingleHotSeriesRecommendContent singleHotSeriesRecommendContent) {
        if (this.itemView == null || this.itemView.getVisibility() != 0) {
            return;
        }
        int height = this.itemView.getHeight();
        int width = this.itemView.getWidth();
        Rect rect = new Rect();
        if (this.itemView.getLocalVisibleRect(rect) && ColumnLinearLayout.aoN_(rect, height, width) && !this.f2913a) {
            ResourceBriefInfo resourceBriefInfo = this.t;
            LogUtil.a("SingleHotSeriesRecommendHolder", "visible to user, resource name: ", resourceBriefInfo != null ? resourceBriefInfo.getResourceName() : null, ", positionId: ", Integer.valueOf(this.p), ", height: ", Integer.valueOf(height), ", width: ", Integer.valueOf(width), ", getLocalVisibleRect: ", Boolean.valueOf(this.itemView.getLocalVisibleRect(rect)));
            this.f2913a = true;
            m(singleHotSeriesRecommendContent);
        }
    }

    private void m(SingleHotSeriesRecommendContent singleHotSeriesRecommendContent) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("resourcePositionId", Integer.valueOf(this.p));
        hashMap.put("event", 1);
        hashMap.put("itemCardName", singleHotSeriesRecommendContent.getTheme());
        hashMap.put("itemId", singleHotSeriesRecommendContent.getDynamicDataId());
        hashMap.put("pullOrder", Integer.valueOf(this.q + 1));
        if (this.t != null) {
            hashMap.put("algId", "");
            hashMap.put("smartRecommend", Boolean.valueOf(this.t.getSmartRecommend()));
            hashMap.put("resourceId", this.t.getResourceId());
            hashMap.put("resourceName", this.t.getResourceName());
        }
        long currentTimeMillis = System.currentTimeMillis();
        hashMap.put("durationTime", Long.valueOf(currentTimeMillis - this.v));
        this.v = currentTimeMillis;
        ixx.d().d(this.b, AnalyticsValue.MARKETING_RESOURCE.value(), hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, String str) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        hashMap.put("event", Integer.valueOf(i));
        hashMap.put("name", str);
        ixx.d().d(this.b, AnalyticsValue.HOT_SERIES_CLICK_EVENT_VALUE.value(), hashMap, 0);
    }
}
