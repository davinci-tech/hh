package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.utils.MarketingBiUtils;
import com.huawei.healthcloud.plugintrack.ui.view.CircleProgressButton;
import com.huawei.healthcloud.plugintrack.ui.view.ShowDataPanelLayout;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.LinkedList;

/* loaded from: classes4.dex */
public class hog {
    private View ab;
    private ShowDataPanelLayout ac;
    private HealthTextView ag;
    private int ah;
    private View ao;
    private HealthTextView bc;
    private GridView f;
    private Context g;
    private RelativeLayout h;
    private RelativeLayout j;
    private boolean p;
    private boolean q;
    private int u;
    private ResourceBriefInfo x;
    private ImageView am = null;
    private ImageView aj = null;
    private CircleProgressButton al = null;
    private ImageButton ak = null;
    private HealthTextView au = null;
    private HealthProgressBar ap = null;
    private ImageView ar = null;
    private HealthTextView ay = null;
    private View ax = null;
    private HealthTextView as = null;
    private hok v = null;
    private hok ad = null;
    private hok y = null;
    private hok o = null;
    private hok k = null;
    private hok l = null;
    private ImageButton ae = null;
    private ImageButton an = null;
    private HealthTextView at = null;
    private HealthTextView aw = null;
    private ImageView aq = null;
    private HealthTextView av = null;
    private FrameLayout ba = null;
    private LinearLayout n = null;
    private RelativeLayout r = null;
    private LinearLayout t = null;
    private LinearLayout s = null;
    private LinearLayout w = null;
    private LinearLayout m = null;
    private HealthTextView ai = null;
    private HealthTextView af = null;
    private hok bb = null;
    private SparseArray<hok> aa = new SparseArray<>();
    private SparseArray<hok> z = new SparseArray<>();
    private RelativeLayout b = null;
    private HealthTextView d = null;
    private HealthTextView e = null;
    private HealthTextView c = null;

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f13295a = null;
    private HealthTextView az = null;
    private HealthTextView i = null;

    public hog(View view, int i, boolean z, boolean z2, Context context) {
        this.ab = null;
        this.ah = 0;
        this.p = false;
        this.ab = view;
        this.ah = i;
        this.p = z;
        this.q = z2;
        this.g = context;
        av();
    }

    public SparseArray<hok> bno_() {
        return this.aa;
    }

    private void av() {
        View view = this.ab;
        if (view == null) {
            return;
        }
        this.aq = (ImageView) view.findViewById(R.id.track_main_page_gps_sign);
        this.aw = (HealthTextView) this.ab.findViewById(R.id.track_main_page_gps);
        this.at = (HealthTextView) this.ab.findViewById(R.id.track_main_page_gps_sign_text);
        this.av = (HealthTextView) this.ab.findViewById(R.id.track_main_page_gps_sign_tip_text);
        this.ao = this.ab.findViewById(R.id.track_main_page_gps_ll);
        this.r = (RelativeLayout) this.ab.findViewById(R.id.layout_operation);
        this.t = (LinearLayout) this.ab.findViewById(R.id.layout_lockoperation);
        this.w = (LinearLayout) this.ab.findViewById(R.id.layout_updown);
        LinearLayout linearLayout = (LinearLayout) this.ab.findViewById(R.id.track_main_page_suggest_show_layout);
        this.s = linearLayout;
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
        this.ak = (ImageButton) this.ab.findViewById(R.id.track_main_page_btn_updown);
        this.am = (ImageView) this.ab.findViewById(R.id.track_main_page_btn_pause);
        this.aj = (ImageView) this.ab.findViewById(R.id.track_main_page_btn_play);
        CircleProgressButton circleProgressButton = (CircleProgressButton) this.ab.findViewById(R.id.track_main_page_btn_stop);
        this.al = circleProgressButton;
        jcf.bEG_(circleProgressButton, nsf.j(R.string._2130839724_res_0x7f0208ac));
        this.au = (HealthTextView) this.ab.findViewById(R.id.track_main_page_text_targetValue);
        this.ap = (HealthProgressBar) this.ab.findViewById(R.id.track_main_page_progressBar);
        this.ar = (ImageView) this.ab.findViewById(R.id.next_action_image);
        this.ay = (HealthTextView) this.ab.findViewById(R.id.track_main_page_text_trainplan);
        this.ax = this.ab.findViewById(R.id.track_main_page_split_line);
        this.as = (HealthTextView) this.ab.findViewById(R.id.track_main_page_intensity_msg);
        this.ae = (ImageButton) this.ab.findViewById(R.id.track_main_page_btn_lock);
        this.an = (ImageButton) this.ab.findViewById(R.id.track_main_page_btn_setting);
        this.h = (RelativeLayout) this.ab.findViewById(R.id.data_value_show_container);
        this.ag = (HealthTextView) this.ab.findViewById(R.id.switch_data_hint_text);
        RelativeLayout relativeLayout = (RelativeLayout) this.ab.findViewById(R.id.layout_hidden_right);
        RelativeLayout relativeLayout2 = (RelativeLayout) this.ab.findViewById(R.id.layout_hidden_left);
        int i = this.ah;
        if (i != 264 && i != 265) {
            this.ai = (HealthTextView) relativeLayout.findViewById(R.id.data_value);
            this.af = (HealthTextView) relativeLayout2.findViewById(R.id.data_value);
        }
        this.ba = (FrameLayout) this.ab.findViewById(R.id.wholefragment);
        this.n = (LinearLayout) this.ab.findViewById(R.id.halffragment);
        this.bc = (HealthTextView) this.ab.findViewById(R.id.track_stop_content);
        this.az = (HealthTextView) this.ab.findViewById(R.id.track_main_page_text_progress);
        this.ac = (ShowDataPanelLayout) this.ab.findViewById(R.id.fragment_sportdata);
        aq();
        ax();
        au();
        ar();
        ao();
        as();
        at();
        ap();
    }

    private void b(HealthTextView healthTextView, String str) {
        Context context;
        if (healthTextView == null || TextUtils.isEmpty(str) || (context = this.g) == null) {
            LogUtil.b("Track_TrackMainViewHolderBean", "setTypeface(), view == null || TextUtils.isEmpty(path) || mContext == null");
        } else {
            healthTextView.setTypeface(Typeface.createFromAsset(context.getAssets(), str));
        }
    }

    private void aq() {
        hok bnf_;
        LinkedList<RelativeLayout> linkedList = new LinkedList();
        linkedList.add((RelativeLayout) this.ab.findViewById(R.id.data_first_line_left));
        linkedList.add((RelativeLayout) this.ab.findViewById(R.id.data_first_line_right));
        linkedList.add((RelativeLayout) this.ab.findViewById(R.id.data_second_line_left));
        linkedList.add((RelativeLayout) this.ab.findViewById(R.id.data_second_line_right));
        linkedList.add((RelativeLayout) this.ab.findViewById(R.id.data_third_line_left));
        linkedList.add((RelativeLayout) this.ab.findViewById(R.id.data_third_line_right));
        linkedList.add((RelativeLayout) this.ab.findViewById(R.id.data_value_show_container));
        int i = 1;
        for (RelativeLayout relativeLayout : linkedList) {
            if (relativeLayout != null && (bnf_ = bnf_(relativeLayout)) != null) {
                this.aa.put(i, bnf_);
                i++;
            }
        }
    }

    private hok bnf_(RelativeLayout relativeLayout) {
        if (relativeLayout == null) {
            return null;
        }
        HealthTextView healthTextView = (HealthTextView) relativeLayout.findViewById(R.id.data_value);
        HealthTextView healthTextView2 = (HealthTextView) relativeLayout.findViewById(R.id.data_name);
        b(healthTextView, "font/hw-digit-bold.otf");
        if (LanguageUtil.ai(this.g)) {
            relativeLayout.setLayoutDirection(1);
        }
        return new hok(healthTextView, healthTextView2, relativeLayout);
    }

    private void ao() {
        hok bnf_ = bnf_((RelativeLayout) this.ab.findViewById(R.id.layout_hidden_left));
        if (bnf_ == null) {
            LogUtil.b("Track_TrackMainViewHolderBean", "constructHiddenDistance(), item == null");
        } else {
            bnf_.d().setText(UnitUtil.h() ? R.string._2130841383_res_0x7f020f27 : R.string._2130841382_res_0x7f020f26);
            this.z.put(0, bnf_);
        }
    }

    private void as() {
        hok bnf_ = bnf_((RelativeLayout) this.ab.findViewById(R.id.layout_hidden_right));
        if (bnf_ == null) {
            LogUtil.b("Track_TrackMainViewHolderBean", "constructHiddenDistance(), item == null");
        } else {
            bnf_.d().setText(R.string._2130843686_res_0x7f021826);
            this.z.put(1, bnf_);
        }
    }

    private void ar() {
        RelativeLayout relativeLayout = (RelativeLayout) this.ab.findViewById(R.id.track_main_page_tuba_layout);
        HealthTextView healthTextView = (HealthTextView) this.ab.findViewById(R.id.text_targetUnit);
        if (LanguageUtil.ai(this.g)) {
            relativeLayout.setLayoutDirection(1);
        }
        b(this.au, "font/hw-digit-bold.otf");
        hok hokVar = new hok(this.au, healthTextView, relativeLayout);
        this.bb = hokVar;
        this.aa.put(0, hokVar);
    }

    private void at() {
        this.j = (RelativeLayout) this.ab.findViewById(R.id.all_data_type_panel_layout);
        this.f = (GridView) this.ab.findViewById(R.id.grid_view);
    }

    ShowDataPanelLayout p() {
        return this.ac;
    }

    public View bnn_() {
        return this.ab;
    }

    public View bnu_() {
        return this.ao;
    }

    public GridView bng_() {
        return this.f;
    }

    private void au() {
        if (this.q) {
            HealthTextView healthTextView = (HealthTextView) this.ab.findViewById(R.id.climb3DDis);
            this.i = healthTextView;
            if (healthTextView != null) {
                healthTextView.setVisibility(0);
                return;
            }
            return;
        }
        LogUtil.a("Track_TrackMainViewHolderBean", "is not Show3DDis");
    }

    private void ax() {
        if (this.p) {
            this.d = (HealthTextView) this.ab.findViewById(R.id.calibrationDistanceSteps);
            this.e = (HealthTextView) this.ab.findViewById(R.id.calibrationDistanceTimes);
            this.c = (HealthTextView) this.ab.findViewById(R.id.calibrationNormalDistance);
            this.f13295a = (HealthTextView) this.ab.findViewById(R.id.beforeCalibrationDistance);
            RelativeLayout relativeLayout = (RelativeLayout) this.ab.findViewById(R.id.calibrationDistanceDevolper);
            this.b = relativeLayout;
            relativeLayout.setVisibility(0);
            return;
        }
        LogUtil.a("Track_TrackMainViewHolderBean", "mIsOpenIndoorRunningInfo is false");
    }

    public LinearLayout bni_() {
        return this.n;
    }

    public HealthTextView aj() {
        return this.bc;
    }

    public FrameLayout bny_() {
        return this.ba;
    }

    public LinearLayout bnj_() {
        return this.t;
    }

    public RelativeLayout bnk_() {
        return this.r;
    }

    public LinearLayout bnl_() {
        return this.s;
    }

    public LinearLayout bnm_() {
        return this.w;
    }

    public HealthTextView t() {
        return this.af;
    }

    public HealthTextView q() {
        return this.ai;
    }

    public ImageButton bnt_() {
        return this.an;
    }

    public ImageButton bnq_() {
        return this.ae;
    }

    public ImageView bns_() {
        return this.aj;
    }

    public RelativeLayout bnh_() {
        return this.h;
    }

    public ImageView bnr_() {
        return this.am;
    }

    public CircleProgressButton al() {
        return this.al;
    }

    public SparseArray<hok> bnp_() {
        return this.z;
    }

    public ImageView bnv_() {
        return this.aq;
    }

    public HealthProgressBar ab() {
        return this.ap;
    }

    public ImageView bnw_() {
        return this.ar;
    }

    public HealthTextView ai() {
        return this.at;
    }

    public HealthTextView af() {
        return this.aw;
    }

    public HealthTextView ah() {
        return this.av;
    }

    public HealthTextView b() {
        return this.d;
    }

    public HealthTextView e() {
        return this.e;
    }

    public HealthTextView d() {
        return this.c;
    }

    public HealthTextView a() {
        return this.f13295a;
    }

    public HealthTextView c() {
        return this.i;
    }

    public HealthTextView ae() {
        return this.au;
    }

    public HealthTextView ag() {
        return this.ay;
    }

    public HealthTextView aa() {
        return this.as;
    }

    public View bnx_() {
        return this.ax;
    }

    public HealthTextView an() {
        return this.az;
    }

    public void bnz_(View.OnClickListener onClickListener, boolean z) {
        this.r.setOnClickListener(onClickListener);
        this.an.setOnClickListener(onClickListener);
        this.ae.setOnClickListener(onClickListener);
        this.am.setOnClickListener(onClickListener);
        this.aj.setOnClickListener(onClickListener);
        this.ag.setOnClickListener(onClickListener);
        this.h.setOnClickListener(onClickListener);
        if (z) {
            this.ap.setOnClickListener(onClickListener);
            ImageView imageView = this.ar;
            if (imageView != null) {
                imageView.setOnClickListener(onClickListener);
            }
        } else {
            this.ap.setVisibility(8);
            this.ay.setVisibility(8);
            ImageView imageView2 = this.ar;
            if (imageView2 != null) {
                imageView2.setVisibility(8);
            }
        }
        int i = this.ah;
        if (i == 264 || i == 265) {
            return;
        }
        this.w.setOnClickListener(onClickListener);
        this.ak.setOnClickListener(onClickListener);
        this.ak.performClick();
    }

    public void bnA_(SparseArray<hoj> sparseArray, SparseArray<hok> sparseArray2) {
        if (sparseArray == null || sparseArray.size() <= 0 || sparseArray2.size() <= 0) {
            return;
        }
        int size = sparseArray.size();
        for (int i = 0; i < size; i++) {
            int keyAt = sparseArray.keyAt(i);
            hok hokVar = sparseArray2.get(keyAt);
            hoj hojVar = sparseArray.get(keyAt);
            if (hojVar == null || hokVar == null) {
                LogUtil.b("Track_TrackMainViewHolderBean", "updateItemView(), trackShowItemValue == null || trackMainViewHolderItem == null");
            } else {
                hokVar.d(hojVar);
            }
        }
    }

    public boolean ak() {
        int i = this.ah;
        return i == 265 || i == 264;
    }

    private void ap() {
        String str;
        boolean z;
        int i = this.ah;
        if (i == 258 || i == 264) {
            if (i == 258) {
                this.u = 15001;
                str = "SportAdSportingPauseIconOutdoor";
                z = true;
            } else {
                this.u = 15002;
                str = "SportAdSportingPauseIconIndoor";
                z = false;
            }
            gxu a2 = gtv.a(str);
            if (a2 != null && !TextUtils.isEmpty(a2.a())) {
                LogUtil.a("Track_TrackMainViewHolderBean", "checkSportAdData() pause has imageUrl, isOutdoorRun: ", Boolean.valueOf(z));
                nrf.b(a2.a(), new CustomTarget<Bitmap>() { // from class: hog.1
                    @Override // com.bumptech.glide.request.target.Target
                    public void onLoadCleared(Drawable drawable) {
                    }

                    @Override // com.bumptech.glide.request.target.Target
                    /* renamed from: bnB_, reason: merged with bridge method [inline-methods] */
                    public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                        LogUtil.a("Track_TrackMainViewHolderBean", "checkSportAdData() pause show image success");
                        hog.this.am.setImageBitmap(bitmap);
                    }

                    @Override // com.bumptech.glide.request.target.CustomTarget, com.bumptech.glide.request.target.Target
                    public void onLoadFailed(Drawable drawable) {
                        LogUtil.a("Track_TrackMainViewHolderBean", "checkSportAdData() pause show image failed");
                        hog.this.aw();
                    }
                });
                ResourceBriefInfo b = gtv.b(this.u);
                this.x = b;
                MarketingBiUtils.d(this.u, b);
                return;
            }
            aw();
            return;
        }
        aw();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aw() {
        this.am.setImageResource(R.drawable._2131431867_res_0x7f0b11bb);
        this.u = 0;
        this.x = null;
    }

    public int o() {
        return this.u;
    }

    public ResourceBriefInfo l() {
        return this.x;
    }
}
