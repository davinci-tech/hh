package defpackage;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.outofprocess.util.HwWatchFaceUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.device.activity.pairing.DevicePairGuideActivity;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import com.huawei.ui.homewear21.home.card.WearHomeBaseCard;
import com.huawei.ui.homewear21.home.holder.WearHomeWatchFaceHolder;
import com.huawei.watchface.mvp.ui.activity.PrivacyStatementActivity;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Map;

/* loaded from: classes6.dex */
public class pdz extends WearHomeBaseCard {
    private oxi d;
    private Activity e;
    private String f;
    private String h;
    private View i;
    private WearHomeWatchFaceHolder k = null;
    private boolean c = false;
    private View.OnClickListener b = new View.OnClickListener() { // from class: pdy
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            pdz.this.dmk_(view);
        }
    };
    private View.OnClickListener g = new View.OnClickListener() { // from class: pec
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            pdz.this.dml_(view);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private final BroadcastReceiver f16089a = new BroadcastReceiver() { // from class: pdz.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            ReleaseLogUtil.e("WearHomeWatchFaceCard", "mNonLocalBroadcastReceiver intent:", intent.getAction());
            if (PrivacyStatementActivity.ACTION_WATCHFACE_SERVICE_DISABLE.equals(intent.getAction())) {
                if (Utils.l()) {
                    return;
                }
                jqi.a().setSwitchSetting("watch_face_privacy_service_status", "2", null);
                return;
            }
            LogUtil.h("WearHomeWatchFaceCard", "mNonLocalBroadcastReceiver()  intent:", intent.getAction());
        }
    };
    private final BroadcastReceiver j = new BroadcastReceiver() { // from class: pdz.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            LogUtil.a("WearHomeWatchFaceCard", "onReceive mWatchFaceReceiver action is ", action);
            if (pdz.this.h() && pdz.this.g() && "com.huawei.watchface.action.ACTION_WATCHFACE_LIST".equals(action)) {
                jls.bHX_(intent);
                pdz.this.k();
            }
        }
    };

    /* synthetic */ void dmk_(View view) {
        LogUtil.a("WearHomeWatchFaceCard", "Enter watchFaceClick Listener");
        this.mCurrentDeviceInfo = oxa.a().b(this.mDeviceMac);
        if (this.mCurrentDeviceInfo != null && ((this.mCurrentDeviceInfo.getDeviceConnectState() == 2 || this.mCurrentDeviceInfo.getDeviceConnectState() == 1) && jkj.d().c(this.mDeviceMac) == 6)) {
            LogUtil.a("WearHomeWatchFaceCard", "Enter watchFaceClick Listener ，wear device is OTA");
            o();
            ViewClickInstrumentation.clickOnView(view);
        } else {
            LogUtil.a("WearHomeWatchFaceCard", "click goto WatchFace");
            d(-1);
            HwWatchFaceUtil.b().e(2);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* synthetic */ void dml_(View view) {
        LogUtil.a("WearHomeWatchFaceCard", "Enter watchFaceClick Listener");
        this.mCurrentDeviceInfo = oxa.a().b(this.mDeviceMac);
        if (this.mCurrentDeviceInfo != null && ((this.mCurrentDeviceInfo.getDeviceConnectState() == 2 || this.mCurrentDeviceInfo.getDeviceConnectState() == 1) && jkj.d().c(this.mDeviceMac) == 6)) {
            LogUtil.a("WearHomeWatchFaceCard", "Enter watchFaceClick Listener ，wear device is OTAing");
            o();
            ViewClickInstrumentation.clickOnView(view);
        } else {
            LogUtil.a("WearHomeWatchFaceCard", "click goto WatchFace");
            if (view != null) {
                d(((Integer) view.getTag()).intValue());
                HwWatchFaceUtil.b().e(3);
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public pdz(Activity activity, String str) {
        dmi_(activity, str);
        LogUtil.a("WearHomeWatchFaceCard", "TESTCARD new WearHomeWatchFaceCard mDeviceMac: ", CommonUtil.l(this.mDeviceMac));
    }

    private void dmi_(Activity activity, String str) {
        this.e = activity;
        this.mContext = activity;
        this.mDeviceMac = str;
        this.mCurrentDeviceInfo = oxa.a().b(this.mDeviceMac);
        this.mDeviceCapability = DeviceSettingsInteractors.d(BaseApplication.getContext()).e(this.mDeviceMac);
    }

    @Override // com.huawei.ui.homewear21.home.card.WearHomeBaseCard
    public RecyclerView.ViewHolder getCardViewHolder(ViewGroup viewGroup, LayoutInflater layoutInflater) {
        this.i = layoutInflater.inflate(R.layout.wear_home_watch_face_layout, viewGroup, false);
        m();
        return this.k;
    }

    public void dmj_(View view) {
        View findViewById = view.findViewById(R.id.card_parent);
        this.i = findViewById;
        RelativeLayout relativeLayout = (RelativeLayout) findViewById.findViewById(R.id.card_watchface_view);
        if (relativeLayout.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) relativeLayout.getLayoutParams();
            layoutParams.setMargins(layoutParams.leftMargin, 0, layoutParams.rightMargin, layoutParams.bottomMargin);
        }
        m();
    }

    public void e() {
        WearHomeWatchFaceHolder wearHomeWatchFaceHolder = this.k;
        if (wearHomeWatchFaceHolder == null || wearHomeWatchFaceHolder.dmJ_() == null) {
            return;
        }
        this.k.dmJ_().setVisibility(8);
    }

    public void d() {
        WearHomeWatchFaceHolder wearHomeWatchFaceHolder = this.k;
        if (wearHomeWatchFaceHolder == null || wearHomeWatchFaceHolder.dmJ_() == null) {
            return;
        }
        this.k.dmI_().setVisibility(8);
    }

    private void m() {
        WearHomeWatchFaceHolder wearHomeWatchFaceHolder = new WearHomeWatchFaceHolder(this.i);
        this.k = wearHomeWatchFaceHolder;
        wearHomeWatchFaceHolder.dmK_().setOnClickListener(nkx.cwY_(new View.OnClickListener() { // from class: pea
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                pdz.this.dmm_(view);
            }
        }, this.e, true, ""));
        f();
    }

    /* synthetic */ void dmm_(View view) {
        this.b.onClick(view);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void f() {
        WearHomeWatchFaceHolder wearHomeWatchFaceHolder;
        if (this.e == null || (wearHomeWatchFaceHolder = this.k) == null) {
            LogUtil.h("WearHomeWatchFaceCard", "initView fail");
            return;
        }
        wearHomeWatchFaceHolder.a().setText(this.mContext.getResources().getString(R.string._2130844034_res_0x7f021982));
        this.mDeviceCapability = DeviceSettingsInteractors.d(BaseApplication.getContext()).e(this.mDeviceMac);
        if (this.mDeviceCapability == null && this.mCurrentDeviceInfo != null && !pep.a(this.mCurrentDeviceInfo.getProductType(), this.mDeviceMac)) {
            LogUtil.h("WearHomeWatchFaceCard", "mDeviceCapability is null");
            this.k.dmI_().setVisibility(8);
            return;
        }
        if (this.mCurrentDeviceInfo != null && this.mCurrentDeviceInfo.getPowerSaveModel() == 1 && pep.d(this.mCurrentDeviceInfo.getProductType())) {
            LogUtil.h("WearHomeWatchFaceCard", "getPowerSaveModel is power state");
            this.k.dmI_().setVisibility(8);
            return;
        }
        this.mCurrentDeviceInfo = oxa.a().b(this.mDeviceMac);
        if (this.mCurrentDeviceInfo == null) {
            LogUtil.h("WearHomeWatchFaceCard", "initView mCurrentDeviceInfo == null");
            return;
        }
        if ((this.mCurrentDeviceInfo.getPowerSaveModel() != 1 || !pep.d(this.mCurrentDeviceInfo.getProductType())) && h()) {
            j();
        } else {
            this.k.dmI_().setVisibility(8);
        }
        if (this.mCurrentDeviceInfo.getDeviceConnectState() != 2) {
            e();
        }
    }

    private void j() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: pee
            @Override // java.lang.Runnable
            public final void run() {
                pdz.this.c();
            }
        });
    }

    /* synthetic */ void c() {
        String c = GRSManager.a(this.mContext).c();
        Map<String, String> d = jls.d(c);
        this.f = d.get("watchFaceH5");
        this.h = d.get("watchFace");
        ReleaseLogUtil.e("HWWEAR_WearHomeWatchFaceCard", "countryCode:", c, ",watchFaceH5New:", CommonUtil.b(this.f, "/", 2), ",watchFace:", CommonUtil.b(this.h, "/", 2), ",watchFaceTop:");
        this.e.runOnUiThread(new Runnable() { // from class: peb
            @Override // java.lang.Runnable
            public final void run() {
                pdz.this.a();
            }
        });
    }

    /* synthetic */ void a() {
        if (!this.c) {
            n();
            pep.dmS_(this.j, "com.huawei.watchface.action.ACTION_WATCHFACE_LIST");
            LogUtil.a("WearHomeWatchFaceCard", "initView() mIsRegistration ", Boolean.valueOf(this.c));
            this.c = true;
        }
        LogUtil.a("WearHomeWatchFaceCard", "onUiCreate startLoadingView");
        r();
    }

    private void n() {
        LogUtil.a("WearHomeWatchFaceCard", "enter registerNonLocalBroadcastReceiver");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(PrivacyStatementActivity.ACTION_WATCHFACE_SERVICE_DISABLE);
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(BaseApplication.getContext());
        if (localBroadcastManager != null) {
            localBroadcastManager.registerReceiver(this.f16089a, intentFilter);
        }
    }

    private void l() {
        try {
            LocalBroadcastManager.getInstance(BaseApplication.getContext()).unregisterReceiver(this.f16089a);
        } catch (IllegalArgumentException e) {
            LogUtil.b("WearHomeWatchFaceCard", "unRegisterNonLocalBroadcastReceiver Exception: ", e.getMessage());
        }
    }

    @Override // com.huawei.ui.homewear21.home.card.WearHomeBaseCard
    public void deviceConnectionChange(int i) {
        f();
    }

    @Override // com.huawei.ui.homewear21.home.card.WearHomeBaseCard
    public void onResume() {
        f();
    }

    @Override // com.huawei.ui.homewear21.home.card.WearHomeBaseCard
    public void onDestroy() {
        this.c = false;
        l();
        pep.dmZ_(this.j);
    }

    public void b() {
        DeviceInfo b = oxa.a().b(this.mDeviceMac);
        if (b != null && b.getDeviceConnectState() == 2) {
            LogUtil.a("WearHomeWatchFaceCard", "have DeviceInfo");
            DeviceSettingsInteractors d = DeviceSettingsInteractors.d(BaseApplication.getContext());
            if (d == null || d.e() == null) {
                return;
            }
            LogUtil.a("WearHomeWatchFaceCard", "deviceInter.capabilityNegotiation() != null");
            a(d, b);
            return;
        }
        LogUtil.h("WearHomeWatchFaceCard", "deviceInfo is not connected");
    }

    private void a(DeviceSettingsInteractors deviceSettingsInteractors, DeviceInfo deviceInfo) {
        if (deviceSettingsInteractors.e().isSupportWatchFace() && SharedPreferenceManager.i(this.mContext).equals(deviceInfo.getDeviceIdentify())) {
            LogUtil.a("WearHomeWatchFaceCard", "device.capabilityNegotiation().isSupportWatchFace() is true");
            if (SharedPreferenceManager.f(BaseApplication.getContext()) && SharedPreferenceManager.g(this.mContext, HwWatchFaceUtil.b().d())) {
                LogUtil.a("WearHomeWatchFaceCard", "data and Language is same");
                jls.a();
                k();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        DeviceInfo b = oxa.a().b(this.mDeviceMac);
        if (b != null && b.getDeviceConnectState() != 2) {
            LogUtil.h("WearHomeWatchFaceCard", "deviceInfo is not connected");
            return;
        }
        if (jls.b()) {
            LogUtil.h("WearHomeWatchFaceCard", "updateWatchFaceList return!");
            return;
        }
        int i = nsn.ag(this.mContext) ? 6 : 3;
        for (int i2 = 3; i2 < 6; i2++) {
            RelativeLayout relativeLayout = (RelativeLayout) nsy.cMd_(this.i, BaseApplication.getContext().getResources().getIdentifier("rl_watchface" + i2, "id", BaseApplication.getContext().getPackageName()));
            if (relativeLayout != null) {
                LogUtil.a("WearHomeWatchFaceCard", "relativeLayout index:", Integer.valueOf(i2));
                relativeLayout.setVisibility(8);
            }
        }
        int min = Math.min(i, jls.c());
        if (min > 0) {
            this.k.dmI_().setVisibility(0);
            this.k.dmJ_().setVisibility(0);
        }
        for (int i3 = 0; i3 < min; i3++) {
            c(i3);
        }
        ReleaseLogUtil.e("DEVMGR_WearHomeWatchFaceCard", "top3 watchFace uri is ok.");
    }

    private void c(int i) {
        LogUtil.a("WearHomeWatchFaceCard", "updateWatchFaceItem position:", Integer.valueOf(i));
        RelativeLayout relativeLayout = (RelativeLayout) this.i.findViewById(BaseApplication.getContext().getResources().getIdentifier("rl_watchface" + i, "id", BaseApplication.getContext().getPackageName()));
        if (relativeLayout == null) {
            LogUtil.h("WearHomeWatchFaceCard", "updateWatchFaceItem relativeLayout == null.");
            return;
        }
        relativeLayout.setTag(Integer.valueOf(i));
        relativeLayout.setOnClickListener(this.g);
        relativeLayout.setVisibility(0);
        if (i < 0 || i >= jls.c()) {
            return;
        }
        dmg_(jls.e(i), (ImageView) this.i.findViewById(BaseApplication.getContext().getResources().getIdentifier("im_watchface" + i, "id", BaseApplication.getContext().getPackageName())));
        HealthTextView healthTextView = (HealthTextView) this.i.findViewById(BaseApplication.getContext().getResources().getIdentifier("tv_watchface" + i, "id", BaseApplication.getContext().getPackageName()));
        String d = jls.d(i);
        LogUtil.a("WearHomeWatchFaceCard", "update Watch Face item title is:", d);
        healthTextView.setText(d);
    }

    private void dmg_(String str, final ImageView imageView) {
        int i = jls.b(this.mContext) ? R.mipmap._2131821200_res_0x7f110290 : R.mipmap._2131821449_res_0x7f110389;
        LogUtil.a("WearHomeWatchFaceCard", "loadPicture mContext : ", this.mContext);
        if (this.mContext == null || i()) {
            return;
        }
        nrf.b(str, new RequestOptions().placeholder(i), new CustomTarget<Drawable>() { // from class: pdz.4
            @Override // com.bumptech.glide.request.target.Target
            public void onLoadCleared(Drawable drawable) {
            }

            @Override // com.bumptech.glide.request.target.Target
            /* renamed from: dmn_, reason: merged with bridge method [inline-methods] */
            public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
                try {
                    pdz.this.dmh_(imageView, drawable);
                } catch (OutOfMemoryError unused) {
                    LogUtil.b("WearHomeWatchFaceCard", "loadPicture() OutOfMemoryError");
                }
            }
        });
    }

    private boolean i() {
        Activity activity = this.e;
        if (activity != null && !activity.isFinishing() && !this.e.isDestroyed()) {
            return false;
        }
        LogUtil.a("WearHomeWatchFaceCard", "mActivity is destroyed");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dmh_(ImageView imageView, Drawable drawable) {
        Bitmap cHF_;
        LogUtil.a("WearHomeWatchFaceCard", "setImageToWatchFaceItem()");
        if (drawable == null || imageView == null) {
            LogUtil.h("WearHomeWatchFaceCard", "setImageToWatchFaceItem(), drawable or imageView is null.");
            return;
        }
        if (drawable instanceof BitmapDrawable) {
            cHF_ = ((BitmapDrawable) drawable).getBitmap();
            LogUtil.a("WearHomeWatchFaceCard", "setImageToWatchFaceItem() drawable instanceof BitmapDrawable");
        } else {
            cHF_ = nrf.cHF_(drawable);
            LogUtil.a("WearHomeWatchFaceCard", "setImageToWatchFaceItem() else");
        }
        if (cHF_ == null) {
            LogUtil.h("WearHomeWatchFaceCard", "setImageToWatchFaceItem, bitmap is null");
            return;
        }
        if (jls.b(this.mContext)) {
            LogUtil.a("WearHomeWatchFaceCard", "setImageToWatchFaceItem(), set circle image.");
            int min = Math.min(cHF_.getHeight(), cHF_.getWidth());
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            layoutParams.width = nsn.c(this.mContext, 80.0f);
            layoutParams.height = nsn.c(this.mContext, 80.0f);
            imageView.setLayoutParams(layoutParams);
            imageView.setAdjustViewBounds(true);
            imageView.setImageBitmap(nrf.cHY_(cHF_, min / 2));
            return;
        }
        float c = nsn.c(this.mContext, 64.0f) / cHF_.getWidth();
        LogUtil.a("WearHomeWatchFaceCard", "scaleWidth is,", Float.valueOf(c));
        ViewGroup.LayoutParams layoutParams2 = imageView.getLayoutParams();
        layoutParams2.width = nsn.c(this.mContext, 64.0f);
        layoutParams2.height = (int) (cHF_.getHeight() * c);
        imageView.setLayoutParams(layoutParams2);
        imageView.setAdjustViewBounds(true);
        LogUtil.a("WearHomeWatchFaceCard", "setImageToWatchFaceItem(), set image.");
        Bitmap cJq_ = nrf.cJq_(cHF_, cHF_.getWidth(), cHF_.getHeight(), nsn.c(this.mContext, 8.0f / c));
        if (this.mCurrentDeviceInfo != null) {
            if (this.mCurrentDeviceInfo.getProductType() == 94) {
                cJq_ = nrf.cJq_(cHF_, cHF_.getWidth(), cHF_.getHeight(), 94);
            }
            if (this.mCurrentDeviceInfo.getProductType() == 86 || this.mCurrentDeviceInfo.getProductType() == 87) {
                cJq_ = nrf.cJq_(cHF_, cHF_.getWidth(), cHF_.getHeight(), 95);
            }
            if (this.mCurrentDeviceInfo.getProductType() == 100 || this.mCurrentDeviceInfo.getProductType() == 102) {
                cJq_ = nrf.cJq_(cHF_, cHF_.getWidth(), cHF_.getHeight(), 95);
            }
        }
        if (cJq_ != null) {
            imageView.setImageBitmap(cJq_);
        } else {
            LogUtil.h("WearHomeWatchFaceCard", "setImageToWatchFaceItem, roundBitmap is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean h() {
        if (this.mDeviceCapability == null || !this.mDeviceCapability.isSupportWatchFace()) {
            return this.mCurrentDeviceInfo != null && pep.a(this.mCurrentDeviceInfo.getProductType(), this.mDeviceMac);
        }
        return true;
    }

    private void r() {
        if (h() && g()) {
            LogUtil.a("WearHomeWatchFaceCard", "initGeneralList HwWatchFaceManager init");
            if (!DevicePairGuideActivity.e || CommonUtil.bh()) {
                pep.g(BaseApplication.getContext());
                this.k.dmI_().setVisibility(0);
                return;
            }
            return;
        }
        this.k.dmI_().setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean g() {
        if (!TextUtils.isEmpty(this.f) && !TextUtils.isEmpty(this.h)) {
            LogUtil.a("WearHomeWatchFaceCard", "can get isGetGrsAbility");
            return true;
        }
        LogUtil.h("WearHomeWatchFaceCard", "can not isGetGrsAbility");
        return false;
    }

    private void o() {
        if (this.e == null) {
            LogUtil.h("WearHomeWatchFaceCard", "showTipDialog mActivity is null");
            return;
        }
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.e).e(this.e.getResources().getString(R.string.IDS_device_ota_later_note)).czC_(R.string._2130843756_res_0x7f02186c, new View.OnClickListener() { // from class: ped
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                pdz.dmf_(view);
            }
        }).e();
        e.setCancelable(false);
        e.show();
    }

    static /* synthetic */ void dmf_(View view) {
        LogUtil.a("WearHomeWatchFaceCard", "showTipDialog，click known button");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d(int i) {
        if (this.e == null) {
            LogUtil.h("WearHomeWatchFaceCard", "gotoWatchFace mActivity is null");
            return;
        }
        if (this.d == null) {
            this.d = new oxi(this.e, "com.huawei.ui.homewear21.home.WearHomeActivity");
        }
        if (nsn.o()) {
            LogUtil.h("WearHomeWatchFaceCard", "click too fast");
        } else if (i != -1 && !jls.b()) {
            c(i, jls.b(i), jls.a(i));
        } else {
            this.d.e(i);
        }
    }

    private void c(int i, String str, String str2) {
        if (this.e == null) {
            LogUtil.h("WearHomeWatchFaceCard", "gotoWatchFace mActivity is null");
        } else {
            this.d.d(i);
        }
    }
}
