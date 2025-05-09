package com.huawei.health.marketing.views.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.templates.PopUpMemberTemplate;
import com.huawei.health.marketing.utils.MarketingBiUtils;
import com.huawei.health.marketing.views.dialog.MemberRenewalDialog;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.health.vip.api.VipApi;
import com.huawei.health.vip.api.VipCallback;
import com.huawei.health.vip.datatypes.UserMemberInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.eil;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nsn;
import health.compact.a.Services;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes3.dex */
public class MemberRenewalDialog extends BaseDialog implements View.OnClickListener {
    private static volatile List<WeakReference<MemberRenewalDialog>> e = new ArrayList(4);

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f2907a;
    private ResourceBriefInfo b;
    private Context c;
    private AtomicInteger d;
    private Handler f;
    private HealthButton g;
    private int h;
    private UserMemberInfo i;
    private long j;
    private ImageView l;
    private HealthTextView m;
    private PopUpMemberTemplate o;

    public MemberRenewalDialog(Context context) {
        super(context, R.style.CustomDialog);
        this.j = 0L;
        this.h = 0;
        this.d = new AtomicInteger(0);
        this.f = new Handler(Looper.getMainLooper());
        this.c = context;
        d();
    }

    public void a(int i, ResourceBriefInfo resourceBriefInfo, PopUpMemberTemplate popUpMemberTemplate) {
        this.b = resourceBriefInfo;
        this.h = i;
        this.o = popUpMemberTemplate;
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.commonui_dialog_vip_renewal, (ViewGroup) null);
        setContentView(inflate);
        this.l = (ImageView) inflate.findViewById(R.id.renewal_top_img);
        this.m = (HealthTextView) inflate.findViewById(R.id.renewal_title);
        this.f2907a = (HealthTextView) inflate.findViewById(R.id.renewal_description);
        this.g = (HealthButton) inflate.findViewById(R.id.renewal_submit);
        nrf.d(getContext(), popUpMemberTemplate.getPicture(), new RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC), new d(this));
        this.g.setText(popUpMemberTemplate.getButtonName());
        this.g.setOnClickListener(this);
        a();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        MarketRouterApi marketRouterApi;
        if (nsn.cLk_(view)) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view.getId() == R.id.renewal_submit && (marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class)) != null) {
            marketRouterApi.router(this.o.getLinkValue());
            MarketingBiUtils.b(this.h, this.b, this.j);
        }
        dismiss();
        MarketingBiUtils.c(this.h, this.b, this.j);
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.commonui.base.BaseDialog, android.app.Dialog
    public void show() {
        if (!c() || isShowing()) {
            return;
        }
        setCanceledOnTouchOutside(true);
        super.show();
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -2;
        attributes.height = -2;
        window.setAttributes(attributes);
        this.j = System.currentTimeMillis();
        MarketingBiUtils.d(this.h, this.b);
    }

    private void d() {
        List<WeakReference<MemberRenewalDialog>> list = e;
        if (koq.c(list)) {
            Iterator<WeakReference<MemberRenewalDialog>> it = list.iterator();
            while (it.hasNext()) {
                MemberRenewalDialog memberRenewalDialog = it.next().get();
                boolean z = memberRenewalDialog != null && memberRenewalDialog.isShowing();
                if (z || memberRenewalDialog == null) {
                    it.remove();
                }
                if (z) {
                    memberRenewalDialog.dismiss();
                }
            }
        }
        e.add(new WeakReference<>(this));
    }

    @Override // com.huawei.ui.commonui.base.BaseDialog, android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        List<WeakReference<MemberRenewalDialog>> list = e;
        if (koq.c(list)) {
            Iterator<WeakReference<MemberRenewalDialog>> it = list.iterator();
            while (it.hasNext()) {
                MemberRenewalDialog memberRenewalDialog = it.next().get();
                if (memberRenewalDialog == this || memberRenewalDialog == null) {
                    it.remove();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c() {
        Context context = this.c;
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (!activity.isDestroyed() && !activity.isFinishing()) {
                return true;
            }
        }
        return false;
    }

    private void a() {
        VipApi vipApi = null;
        for (int i = 0; vipApi == null && i < 2; i++) {
            vipApi = (VipApi) Services.a("vip", VipApi.class);
        }
        if (vipApi == null) {
            LogUtil.a("MemeberRenewalDialog", " setPopUpVipTemplate instantiation VipApi failed .");
        } else {
            vipApi.getVipInfo(new c(this));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        this.d.incrementAndGet();
        LogUtil.a("MemeberRenewalDialog", "MarketingResourceSetting-Drawable:3");
        if (this.d.get() >= 2) {
            LogUtil.a("MemeberRenewalDialog", "MarketingResourceSetting-Drawable:4");
            this.f.post(new Runnable() { // from class: elt
                @Override // java.lang.Runnable
                public final void run() {
                    MemberRenewalDialog.this.e();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void e() {
        LogUtil.a("MemeberRenewalDialog", " setPopUpVipTemplate showDialog .");
        UserMemberInfo userMemberInfo = this.i;
        boolean z = this.b.getContentType() == 68;
        long expireTime = userMemberInfo.getExpireTime();
        long nowTime = userMemberInfo.getNowTime();
        if (!z || expireTime - nowTime > 3000) {
            long b = eil.b(userMemberInfo.getNowTime(), userMemberInfo.getExpireTime());
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(this.o.getTheme());
            if (!TextUtils.isEmpty(this.o.getThemePost())) {
                stringBuffer.append(b);
                stringBuffer.append(this.o.getThemePost());
            }
            this.m.setText(stringBuffer.toString());
            stringBuffer.setLength(0);
            if (!TextUtils.isEmpty(this.o.getDescription()) || !TextUtils.isEmpty(this.o.getDescriptionPost())) {
                String description = this.o.getDescription();
                if (description == null) {
                    description = "";
                }
                stringBuffer.append(description);
                stringBuffer.append(DateFormatUtil.b(userMemberInfo.getExpireTime(), DateFormatUtil.DateFormatType.DATE_FORMAT_MINUTE));
                String descriptionPost = this.o.getDescriptionPost();
                stringBuffer.append(descriptionPost != null ? descriptionPost : "");
                this.f2907a.setText(stringBuffer.toString());
                stringBuffer.setLength(0);
            }
            if (!c() || isShowing()) {
                return;
            }
            LogUtil.a("MemeberRenewalDialog", " setPopUpVipTemplate show.");
            show();
        }
    }

    static final class c implements VipCallback {
        private final SoftReference<MemberRenewalDialog> c;

        c(MemberRenewalDialog memberRenewalDialog) {
            this.c = new SoftReference<>(memberRenewalDialog);
        }

        @Override // com.huawei.health.vip.api.VipCallback
        public void onSuccess(Object obj) {
            MemberRenewalDialog memberRenewalDialog = this.c.get();
            LogUtil.a("MemeberRenewalDialog", " MemberVipCallback getVipInfo successed.");
            if (memberRenewalDialog != null && memberRenewalDialog.c() && (obj instanceof UserMemberInfo)) {
                LogUtil.a("MemeberRenewalDialog", "MarketingResourceSetting-Drawable:2");
                memberRenewalDialog.i = (UserMemberInfo) obj;
                memberRenewalDialog.h();
            }
        }

        @Override // com.huawei.health.vip.api.VipCallback
        public void onFailure(int i, String str) {
            LogUtil.a("MemeberRenewalDialog", " MemberVipCallback getVipInfo failed . Then errCode = ", Integer.valueOf(i));
        }
    }

    static class d implements RequestListener<Drawable> {
        private final SoftReference<MemberRenewalDialog> d;

        d(MemberRenewalDialog memberRenewalDialog) {
            this.d = new SoftReference<>(memberRenewalDialog);
        }

        @Override // com.bumptech.glide.request.RequestListener
        public boolean onLoadFailed(GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
            LogUtil.a("MemeberRenewalDialog", "MarketingResourceSetting-Drawable: failed");
            return false;
        }

        @Override // com.bumptech.glide.request.RequestListener
        /* renamed from: aqQ_, reason: merged with bridge method [inline-methods] */
        public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
            MemberRenewalDialog memberRenewalDialog = this.d.get();
            Object[] objArr = new Object[2];
            objArr[0] = "MarketingResourceSetting-Drawable:";
            objArr[1] = Boolean.valueOf(drawable != null);
            LogUtil.a("MemeberRenewalDialog", objArr);
            if (memberRenewalDialog != null && memberRenewalDialog.c() && memberRenewalDialog.o != null) {
                LogUtil.a("MemeberRenewalDialog", "MarketingResourceSetting-Drawable:1");
                nrf.cIu_(memberRenewalDialog.o.getPicture(), memberRenewalDialog.l);
                memberRenewalDialog.h();
            }
            return false;
        }
    }
}
