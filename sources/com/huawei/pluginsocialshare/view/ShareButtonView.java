package com.huawei.pluginsocialshare.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.kit.awareness.status.CapabilityStatus;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.fdu;
import defpackage.jcu;
import defpackage.jdv;
import defpackage.mto;
import defpackage.mvs;
import defpackage.mwd;
import defpackage.nrf;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class ShareButtonView extends RelativeLayout implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private DoBeforeClick f8529a;
    private View b;
    private View c;
    private View d;
    private Context e;
    private View f;
    private View g;
    private mto h;
    private LinearLayout i;
    private String j;
    private HashMap<String, String> n;
    private fdu o;

    public interface DoBeforeClick {
        void onBeforeClick();
    }

    public ShareButtonView(Context context) {
        super(context);
        this.n = new HashMap<>();
        e(context);
    }

    public ShareButtonView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.n = new HashMap<>();
        e(context);
    }

    public ShareButtonView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.n = new HashMap<>();
        e(context);
    }

    private void e(Context context) {
        this.e = context;
        View inflate = View.inflate(context, R.layout.activity_share_layout, this);
        this.g = inflate;
        View findViewById = inflate.findViewById(R.id.dst_selector_list);
        this.b = findViewById;
        LinearLayout linearLayout = (LinearLayout) findViewById.findViewById(R.id.share_wechat_friends_layout);
        LinearLayout linearLayout2 = (LinearLayout) this.b.findViewById(R.id.share_wechat_chat_layout);
        LinearLayout linearLayout3 = (LinearLayout) this.b.findViewById(R.id.share_weibo_layout);
        LinearLayout linearLayout4 = (LinearLayout) this.b.findViewById(R.id.share_family_group_layout);
        LinearLayout linearLayout5 = (LinearLayout) this.b.findViewById(R.id.share_more_layout);
        LinearLayout linearLayout6 = (LinearLayout) this.b.findViewById(R.id.share_save_pdf_layout);
        LinearLayout linearLayout7 = (LinearLayout) this.b.findViewById(R.id.share_print_layout);
        this.i = (LinearLayout) this.b.findViewById(R.id.share_save_to_local_layout);
        linearLayout6.setVisibility(8);
        linearLayout7.setVisibility(8);
        int c = nsn.c(context, 16.0f);
        crh_(linearLayout2, c);
        crh_(linearLayout, c);
        crh_(linearLayout3, c);
        crh_(linearLayout4, c);
        crh_(this.i, c);
        if (EnvironmentInfo.k()) {
            linearLayout5.setVisibility(8);
            this.i.setVisibility(8);
        }
        crh_(linearLayout5, 0);
        crf_(linearLayout).setImageResource(R.drawable.share_wechat_friends_icon);
        crf_(linearLayout2).setImageResource(R.drawable.share_wechat_chat_icon);
        crf_(linearLayout3).setImageResource(R.drawable.share_weibo_icon);
        crf_(linearLayout4).setImageResource(R.mipmap._2131821471_res_0x7f11039f);
        crf_(linearLayout5).setImageResource(R.drawable.share_more_icon);
        crf_(this.i).setImageResource(R.drawable.share_save_local);
        crg_(linearLayout).setText(R.string._2130843622_res_0x7f0217e6);
        crg_(linearLayout2).setText(R.string._2130843623_res_0x7f0217e7);
        crg_(linearLayout3).setText(R.string._2130843624_res_0x7f0217e8);
        crg_(linearLayout4).setText(R.string.IDS_hwh_family_health_zone);
        crg_(linearLayout5).setText(R.string._2130844034_res_0x7f021982);
        crg_(this.i).setText(R.string._2130842376_res_0x7f021308);
        linearLayout.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        this.i.setOnClickListener(this);
        linearLayout3.setOnClickListener(this);
        linearLayout4.setOnClickListener(this);
        linearLayout5.setOnClickListener(this);
    }

    private void crh_(LinearLayout linearLayout, int i) {
        if (linearLayout != null && (linearLayout.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
            layoutParams.setMarginEnd(i);
            linearLayout.setLayoutParams(layoutParams);
            return;
        }
        LogUtil.h("Share_ShareButtonView", "linearLayout is null or layoutParams isn't the instance of LinearLauout.LayoutParams");
    }

    public void setMap(HashMap<String, String> hashMap) {
        this.n = hashMap;
    }

    public void setBeforeClick(DoBeforeClick doBeforeClick) {
        this.f8529a = doBeforeClick;
    }

    private ImageView crf_(LinearLayout linearLayout) {
        return (ImageView) linearLayout.findViewById(R.id.share_img);
    }

    private HealthTextView crg_(LinearLayout linearLayout) {
        return (HealthTextView) linearLayout.findViewById(R.id.share_tv);
    }

    public void setOverSeaLayout() {
        this.b.setVisibility(8);
        View inflate = View.inflate(this.e, R.layout.share_buttom_view, this);
        this.g = inflate;
        View findViewById = inflate.findViewById(R.id.oversea_share_layout);
        this.d = findViewById;
        findViewById.setVisibility(0);
        HealthButton healthButton = (HealthButton) this.g.findViewById(R.id.oversea_save_local_btn);
        HealthButton healthButton2 = (HealthButton) this.g.findViewById(R.id.oversea_share_btn);
        healthButton.setOnClickListener(this);
        healthButton2.setOnClickListener(this);
    }

    public void setPerViewImage(View view) {
        this.f = view;
    }

    public void setLogLayout(View view) {
        this.c = view;
    }

    public void setShareContent(fdu fduVar) {
        if (fduVar == null) {
            LogUtil.b("Share_ShareButtonView", "call setShareContent please ShareContent before!");
            return;
        }
        if (fduVar.aa() == 0) {
            LogUtil.b("Share_ShareButtonView", "call setShareContent please setShareType before!");
            return;
        }
        this.o = fduVar;
        if (!Utils.o() || this.o.u() == 7) {
            return;
        }
        setOverSeaLayout();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.o == null) {
            LogUtil.b("Share_ShareButtonView", "use ShareButtonView please call method setShareContent before!");
            ViewClickInstrumentation.clickOnView(view);
        } else if (this.f == null) {
            LogUtil.b("Share_ShareButtonView", "use ShareButtonView please call method setPerViewImage before!");
            ViewClickInstrumentation.clickOnView(view);
        } else if (this.c == null) {
            LogUtil.b("Share_ShareButtonView", "use ShareButtonView please call method setLogLayout before!");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            cre_(view);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void cre_(View view) {
        if (nsn.a(500)) {
            LogUtil.a("Share_ShareButtonView", "onClick() if (isClickFast())");
            return;
        }
        fdu shareContent = getShareContent();
        if (this.h == null) {
            this.h = mto.b();
        }
        int id = view.getId();
        if (id == R.id.share_save_to_local_layout || id == R.id.oversea_save_local_btn) {
            LogUtil.a("Share_ShareButtonView", "share_save_to_local_layout");
            mto mtoVar = this.h;
            Context context = this.e;
            if (!(context instanceof Activity)) {
                context = BaseApplication.wa_();
            }
            mtoVar.e(context, 4, shareContent);
            c();
            return;
        }
        if (!CommonUtil.aa(com.huawei.hwcommonmodel.application.BaseApplication.getContext())) {
            Toast.makeText(this.e, getResources().getString(R.string._2130841392_res_0x7f020f30), 0).show();
            return;
        }
        if (id == R.id.share_wechat_friends_layout) {
            LogUtil.a("Share_ShareButtonView", "share_wechat_friends_layout");
            this.h.e(this.e, 1, shareContent);
            c();
            return;
        }
        if (id == R.id.share_wechat_chat_layout) {
            LogUtil.a("Share_ShareButtonView", "share_wechat_chat_layout");
            this.h.e(this.e, 2, shareContent);
            c();
            return;
        }
        if (id == R.id.share_weibo_layout) {
            LogUtil.a("Share_ShareButtonView", "share_weibo_layout");
            this.h.e(this.e, 3, shareContent);
            c();
            return;
        }
        if (id == R.id.share_family_group_layout) {
            LogUtil.a("Share_ShareButtonView", "share_family_group_layout");
            this.h.e(this.e, 6, shareContent);
            c();
        } else {
            if (id != R.id.share_more_layout && id != R.id.oversea_share_btn) {
                if (id == R.id.background_layout) {
                    LogUtil.a("Share_ShareButtonView", "click background_layout, destroy!");
                    a();
                    return;
                } else {
                    LogUtil.a("Share_ShareButtonView", "click outside activity! viewId:", Integer.valueOf(id));
                    return;
                }
            }
            mto mtoVar2 = this.h;
            Context context2 = this.e;
            if (!(context2 instanceof Activity)) {
                context2 = BaseApplication.wa_();
            }
            mtoVar2.e(context2, 5, shareContent);
            c();
        }
    }

    private void a() {
        Context context = this.e;
        if (context instanceof Activity) {
            ((Activity) context).finish();
        }
    }

    private void c() {
        DoBeforeClick doBeforeClick = this.f8529a;
        if (doBeforeClick != null) {
            doBeforeClick.onBeforeClick();
        }
        HashMap<String, String> hashMap = this.n;
        if (hashMap.size() == 0) {
            return;
        }
        String str = hashMap.get("currentSportType");
        String str2 = hashMap.get("shareLastRecommend" + str);
        String str3 = hashMap.get("shareLastBackground" + str);
        String str4 = hashMap.get("shareLastDataMark" + str);
        SharedPreferenceManager.e(this.e, Integer.toString(20002), "shareLastRecommend" + str, str2, new StorageParams());
        SharedPreferenceManager.e(this.e, Integer.toString(20002), "shareLastBackground" + str, str3, new StorageParams());
        SharedPreferenceManager.e(this.e, Integer.toString(20002), "shareLastDataMark" + str, str4, new StorageParams());
        mvs.d(Integer.parseInt(str), mto.a());
        SharedPreferenceManager.e(this.e, Integer.toString(CapabilityStatus.AWA_CAP_CODE_DARK_MODE), "shareLastBackground" + str, "1001", new StorageParams());
    }

    private fdu getShareContent() {
        this.o.i(false);
        fdu c = c(this.o);
        this.o = c;
        return c;
    }

    private fdu c(fdu fduVar) {
        Object clone = fduVar.clone();
        if (clone instanceof fdu) {
            fduVar = (fdu) clone;
        }
        Bitmap bGg_ = jdv.bGg_(this.f);
        if (bGg_ == null) {
            return fduVar;
        }
        Bitmap cJj_ = nrf.cJj_(bGg_, 0, mwd.cqx_(this.c), bGg_.getHeight());
        LogUtil.a("Share_ShareButtonView", "onClick: screenCut: ", cJj_);
        if (cJj_ == null) {
            LogUtil.h("Share_ShareButtonView", "onClick: screenCut is null!");
            return fduVar;
        }
        int cqn_ = mwd.cqn_(cJj_);
        LogUtil.a("Share_ShareButtonView", "initView all medal share bitmap size =", Integer.valueOf(cqn_));
        if ((cqn_ / 1024) / 1024 >= 1) {
            String str = jcu.f + "step_day_detail.jpg";
            this.j = str;
            nrf.cJt_(cJj_, str);
            fduVar.d(this.j);
            fduVar.a(4);
        } else {
            fduVar.awp_(cJj_);
            fduVar.a(1);
        }
        return fduVar;
    }
}
