package com.huawei.health.marketing.views.dialog;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.templates.PopUpCombinationTemplate;
import com.huawei.health.marketing.views.CouponInfoAdapter;
import com.huawei.health.servicesui.R$string;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.trade.PayApi;
import com.huawei.trade.datatype.Coupon;
import com.huawei.trade.datatype.CouponResult;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.koq;
import defpackage.njn;
import defpackage.nrf;
import defpackage.nrh;
import defpackage.nsn;
import health.compact.a.Services;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes3.dex */
public class ReceiveCouponDialog extends BaseDialog implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private boolean f2908a;
    private PopUpCombinationTemplate ab;
    private RelativeLayout ac;
    private RelativeLayout ad;
    private boolean b;
    private boolean c;
    private boolean d;
    private boolean e;
    private Context f;
    private ResourceBriefInfo g;
    private ImageView h;
    private CouponInfoAdapter i;
    private ImageView j;
    private LinearLayout k;
    private RecyclerView l;
    private LinearLayout m;
    private ImageView n;
    private ImageView o;
    private List<Coupon> p;
    private List<com.huawei.health.marketing.datatype.Coupon> q;
    private List<String> r;
    private int s;
    private final DialogHandler t;
    private ImageView u;
    private ImageView v;
    private HealthTextView w;
    private List<String> x;
    private HealthTextView y;

    public ReceiveCouponDialog(Context context) {
        super(context, R.style.CustomDialog);
        this.s = 0;
        this.q = new ArrayList();
        this.f2908a = false;
        this.e = false;
        this.c = false;
        this.p = new CopyOnWriteArrayList();
        this.x = new CopyOnWriteArrayList();
        this.r = new CopyOnWriteArrayList();
        this.t = new DialogHandler();
        this.b = true;
        this.d = false;
        this.f = context;
    }

    public void b(int i, ResourceBriefInfo resourceBriefInfo, PopUpCombinationTemplate popUpCombinationTemplate) {
        if (popUpCombinationTemplate == null) {
            LogUtil.h("ReceiveCouponDialog", "template = null");
            return;
        }
        this.g = resourceBriefInfo;
        this.s = i;
        this.ab = popUpCombinationTemplate;
        List<com.huawei.health.marketing.datatype.Coupon> coupon = popUpCombinationTemplate.getCoupon();
        this.q = coupon;
        if (koq.b(coupon)) {
            LogUtil.h("ReceiveCouponDialog", "mMarketingCoupon is empty");
            return;
        }
        LogUtil.a("ReceiveCouponDialog", "template:", popUpCombinationTemplate.toString());
        setContentView(LayoutInflater.from(getContext()).inflate(R.layout.common_dialog_receive_coupon, (ViewGroup) null));
        b();
        e();
    }

    private void e() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BaseApplication.getContext());
        linearLayoutManager.setOrientation(1);
        this.l.setLayoutManager(linearLayoutManager);
        a(this.l);
        CouponInfoAdapter couponInfoAdapter = new CouponInfoAdapter(this.f, new ArrayList());
        this.i = couponInfoAdapter;
        this.l.setAdapter(couponInfoAdapter);
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.marketing.views.dialog.ReceiveCouponDialog.4
            @Override // java.lang.Runnable
            public void run() {
                ReceiveCouponDialog.this.a();
            }
        });
    }

    private void b() {
        this.m = (LinearLayout) findViewById(R.id.coupon_dialog);
        this.n = (ImageView) findViewById(R.id.coupon_img);
        this.w = (HealthTextView) findViewById(R.id.coupon_submit);
        this.h = (ImageView) findViewById(R.id.coupon_close_dialog);
        this.v = (ImageView) findViewById(R.id.submit_img);
        this.ac = (RelativeLayout) findViewById(R.id.submit_view);
        this.ad = (RelativeLayout) findViewById(R.id.submit_view_success);
        this.k = (LinearLayout) findViewById(R.id.coupon_dialog_success);
        this.o = (ImageView) findViewById(R.id.coupon_img_success);
        this.y = (HealthTextView) findViewById(R.id.coupon_submit_success);
        this.j = (ImageView) findViewById(R.id.coupon_close_dialog_success);
        this.u = (ImageView) findViewById(R.id.submit_img_success);
        this.l = (RecyclerView) findViewById(R.id.coupon_recycle_success);
        Context context = BaseApplication.getContext();
        nrf.d(context, this.ab.getPicture(), new RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC), new d(this, 1));
        nrf.d(context, this.ab.getPicture(), new RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC), new d(this, 2));
        nrf.d(context, this.ab.getPicture(), new RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC), new d(this, 3));
        this.y.setText(this.ab.getViewButtonName());
        this.w.setText(this.ab.getButtonName());
        this.h.setOnClickListener(this);
        this.j.setOnClickListener(this);
        this.ac.setOnClickListener(this);
        this.ad.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        LogUtil.a("ReceiveCouponDialog", "enter getCouponInfo");
        ((PayApi) Services.a("TradeService", PayApi.class)).couponListQuery(null, null, new e(this, 1));
    }

    private void c(final List<String> list) {
        final e eVar = new e(this, 2);
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.marketing.views.dialog.ReceiveCouponDialog.5
            @Override // java.lang.Runnable
            public void run() {
                ((PayApi) Services.a("TradeService", PayApi.class)).receiveCouponList(list, eVar);
            }
        });
    }

    static class e implements IBaseResponseCallback {
        private int d;
        private WeakReference<ReceiveCouponDialog> e;

        e(ReceiveCouponDialog receiveCouponDialog, int i) {
            this.e = new WeakReference<>(receiveCouponDialog);
            this.d = i;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            ReceiveCouponDialog receiveCouponDialog = this.e.get();
            if (receiveCouponDialog != null) {
                if (receiveCouponDialog.t == null) {
                    LogUtil.h("ReceiveCouponDialog", "handler is null");
                    return;
                }
                LogUtil.a("ReceiveCouponDialog", "GetAssetInfoResponseCallback errorCode = ", Integer.valueOf(i), " mResultType = ", Integer.valueOf(this.d));
                int i2 = this.d;
                if (i2 == 1) {
                    receiveCouponDialog.d(i, obj);
                    return;
                } else if (i2 == 2) {
                    receiveCouponDialog.a(i, obj);
                    return;
                } else {
                    LogUtil.h("ReceiveCouponDialog", "GetAssetInfoResponseCallback error type ");
                    return;
                }
            }
            LogUtil.h("ReceiveCouponDialog", "personalCenterFragment is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, Object obj) {
        LogUtil.a("ReceiveCouponDialog", "enter dealCouponInfo");
        if (i != 0 || !(obj instanceof List)) {
            LogUtil.h("ReceiveCouponDialog", "GET_COUPON_INFO couponListQuery fail:", Integer.valueOf(i));
            this.d = false;
            return;
        }
        LogUtil.a("ReceiveCouponDialog", "dealReceiveInfo errorCode:", Integer.valueOf(i), "objData:", obj);
        HashMap hashMap = new HashMap();
        for (Coupon coupon : (List) obj) {
            hashMap.put(coupon.getCouponId(), coupon);
        }
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (com.huawei.health.marketing.datatype.Coupon coupon2 : this.q) {
            if (hashMap.containsKey(coupon2.getCouponsId())) {
                if (!((Coupon) hashMap.get(coupon2.getCouponsId())).isOutOfStockFlag()) {
                    z = true;
                }
                arrayList.add((Coupon) hashMap.get(coupon2.getCouponsId()));
            }
        }
        if (!z) {
            LogUtil.a("ReceiveCouponDialog", "coupon has been received, not show dialog");
            this.d = false;
            return;
        }
        this.p.clear();
        this.p.addAll(arrayList);
        this.d = true;
        this.t.sendEmptyMessage(1);
        this.t.sendEmptyMessage(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, Object obj) {
        LogUtil.a("ReceiveCouponDialog", "enter dealReceiveInfo");
        if (i != 0) {
            LogUtil.c("ReceiveCouponDialog", "dealReceiveInfo errorCode:", Integer.valueOf(i), "objData:", obj);
            if (obj instanceof List) {
                LogUtil.a("ReceiveCouponDialog", "enter RECEIVE_COUPON");
                this.x.clear();
                this.r.clear();
                for (CouponResult couponResult : (List) obj) {
                    if (couponResult.getResultCode() == 0 || couponResult.getResultCode() == 3090004) {
                        LogUtil.a("ReceiveCouponDialog", "receive success");
                    } else if (couponResult.getResultCode() == 3090005) {
                        LogUtil.h("ReceiveCouponDialog", "receive limit", couponResult.getCouponId());
                        e(couponResult);
                        this.r.add(couponResult.getCouponId());
                    } else {
                        LogUtil.a("ReceiveCouponDialog", "code:", Integer.valueOf(couponResult.getResultCode()));
                        this.x.add(couponResult.getCouponId());
                    }
                }
                if (koq.b(this.x) && koq.b(this.r)) {
                    this.b = true;
                    this.t.sendEmptyMessage(3);
                    return;
                } else if (koq.b(this.x) && koq.c(this.r)) {
                    LogUtil.a("ReceiveCouponDialog", "mLimitIds:", this.r.toString());
                    c();
                    return;
                } else {
                    this.b = false;
                    this.t.sendEmptyMessage(4);
                    return;
                }
            }
            this.b = false;
            this.t.sendEmptyMessage(4);
            return;
        }
        this.b = true;
        this.t.sendEmptyMessage(3);
    }

    private void e(CouponResult couponResult) {
        for (Coupon coupon : this.p) {
            if (coupon.getCouponId().equals(couponResult.getCouponId())) {
                coupon.setOutOfStockFlag(true);
            }
        }
    }

    private void c() {
        if (this.r.size() == this.q.size()) {
            this.b = false;
            this.t.sendEmptyMessage(5);
        } else {
            this.b = true;
            this.t.sendEmptyMessage(2);
            this.t.sendEmptyMessage(3);
        }
    }

    static class d implements RequestListener<Drawable> {
        private final SoftReference<ReceiveCouponDialog> d;
        private int e;

        d(ReceiveCouponDialog receiveCouponDialog, int i) {
            this.d = new SoftReference<>(receiveCouponDialog);
            this.e = i;
        }

        @Override // com.bumptech.glide.request.RequestListener
        public boolean onLoadFailed(GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
            LogUtil.a("ReceiveCouponDialog", "onLoadFailed");
            return false;
        }

        @Override // com.bumptech.glide.request.RequestListener
        /* renamed from: ara_, reason: merged with bridge method [inline-methods] */
        public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
            ReceiveCouponDialog receiveCouponDialog = this.d.get();
            if (receiveCouponDialog == null || receiveCouponDialog.d()) {
                return false;
            }
            LogUtil.a("ReceiveCouponDialog", "mResultType:", Integer.valueOf(this.e));
            int i = this.e;
            if (i == 1) {
                receiveCouponDialog.f2908a = true;
                receiveCouponDialog.t.sendEmptyMessage(1);
            } else if (i == 2) {
                receiveCouponDialog.e = true;
                receiveCouponDialog.t.sendEmptyMessage(1);
            } else if (i == 3) {
                receiveCouponDialog.c = true;
                receiveCouponDialog.t.sendEmptyMessage(1);
            } else {
                LogUtil.h("ReceiveCouponDialog", "not match");
            }
            return true;
        }
    }

    static final class DialogHandler extends BaseHandler<ReceiveCouponDialog> {
        private DialogHandler(ReceiveCouponDialog receiveCouponDialog) {
            super(receiveCouponDialog);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: aqZ_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(ReceiveCouponDialog receiveCouponDialog, Message message) {
            if (receiveCouponDialog.d()) {
                LogUtil.h("ReceiveCouponDialog", "object.isDestory()");
                return;
            }
            LogUtil.a("ReceiveCouponDialog", "message.what:", Integer.valueOf(message.what));
            int i = message.what;
            if (i != 1) {
                if (i == 2) {
                    receiveCouponDialog.h();
                    return;
                }
                if (i == 3) {
                    receiveCouponDialog.m.setVisibility(8);
                    receiveCouponDialog.k.setVisibility(0);
                    receiveCouponDialog.aqY_(receiveCouponDialog.k, 400);
                    return;
                } else if (i == 4) {
                    nrh.d(com.huawei.haf.application.BaseApplication.e(), com.huawei.haf.application.BaseApplication.e().getString(R$string.IDS_receive_fail));
                    return;
                } else if (i == 5) {
                    receiveCouponDialog.dismiss();
                    nrh.d(com.huawei.haf.application.BaseApplication.e(), com.huawei.haf.application.BaseApplication.e().getString(R$string.IDS_receive_limit));
                    return;
                } else {
                    LogUtil.h("ReceiveCouponDialog", "handleMessageWhenReferenceNotNull not match");
                    return;
                }
            }
            if (receiveCouponDialog.f2908a && receiveCouponDialog.e && receiveCouponDialog.c && receiveCouponDialog.d) {
                nrf.cIu_(receiveCouponDialog.ab.getPicture(), receiveCouponDialog.n);
                nrf.cIu_(receiveCouponDialog.ab.getVibesPicture(), receiveCouponDialog.o);
                nrf.cIu_(receiveCouponDialog.ab.getButtonPicture(), receiveCouponDialog.v);
                nrf.cIu_(receiveCouponDialog.ab.getButtonPicture(), receiveCouponDialog.u);
                if (receiveCouponDialog.isShowing()) {
                    return;
                }
                LogUtil.a("MarketingResourceSetting", "dialog.setBackground: dialog show");
                receiveCouponDialog.show();
                receiveCouponDialog.setCancelable(false);
                receiveCouponDialog.aqY_(receiveCouponDialog.m, 400);
                return;
            }
            LogUtil.h("ReceiveCouponDialog", "not ready");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d() {
        Context context = this.f;
        return context == null || ((Activity) context).isFinishing();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null || nsn.o()) {
            LogUtil.h("ReceiveCouponDialog", "onClick view is null or isFaskClick");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        if (id == R.id.submit_view) {
            if (this.b) {
                List<com.huawei.health.marketing.datatype.Coupon> coupon = this.ab.getCoupon();
                ArrayList arrayList = new ArrayList();
                Iterator<com.huawei.health.marketing.datatype.Coupon> it = coupon.iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next().getCouponsId());
                }
                c(arrayList);
            } else {
                c(this.x);
            }
        } else if (id == R.id.coupon_close_dialog) {
            dismiss();
        } else if (id == R.id.submit_view_success) {
            njn.a(4, 3);
            dismiss();
        } else if (id == R.id.coupon_close_dialog_success) {
            dismiss();
        } else {
            LogUtil.h("ReceiveCouponDialog", "id not match");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        LogUtil.a("ReceiveCouponDialog", "showSuccessInfo");
        this.i.c(this.p);
    }

    @Override // com.huawei.ui.commonui.base.BaseDialog, android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
    }

    @Override // com.huawei.ui.commonui.base.BaseDialog, android.app.Dialog
    public void show() {
        if (isShowing()) {
            return;
        }
        super.show();
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        window.setAttributes(attributes);
    }

    public void a(RecyclerView recyclerView) {
        if (recyclerView == null || this.f == null) {
            return;
        }
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() { // from class: com.huawei.health.marketing.views.dialog.ReceiveCouponDialog.3
            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView2, RecyclerView.State state) {
                super.getItemOffsets(rect, view, recyclerView2, state);
                int childAdapterPosition = recyclerView2.getChildAdapterPosition(view);
                int dimension = (int) ReceiveCouponDialog.this.f.getResources().getDimension(R.dimen._2131362886_res_0x7f0a0446);
                if (childAdapterPosition != 0) {
                    rect.top = dimension;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aqY_(View view, int i) {
        LogUtil.a("ReceiveCouponDialog", "enter setAnimator");
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "rotationY", 280.0f, 360.0f);
        long j = i;
        ofFloat.setDuration(j);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, "alpha", 0.1f, 1.0f);
        ofFloat2.setStartDelay(50L);
        ofFloat2.setDuration(j);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(view, "scaleY", 0.3f, 1.0f);
        ofFloat3.setDuration(j);
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(view, "scaleX", 0.3f, 1.0f);
        ofFloat3.setDuration(j);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ofFloat, ofFloat2, ofFloat4, ofFloat3);
        animatorSet.setStartDelay(500L);
        animatorSet.setInterpolator(new Interpolator() { // from class: com.huawei.health.marketing.views.dialog.ReceiveCouponDialog.1
            @Override // android.animation.TimeInterpolator
            public float getInterpolation(float f) {
                return (float) Math.sin((float) (f * 1.5707963267948966d));
            }
        });
        animatorSet.start();
    }
}
