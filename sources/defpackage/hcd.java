package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.apprichtap.haptic.player.d;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.socialshare.api.SocialShareCenterApi;
import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.ReTrackSimplify;
import com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceReTrack;
import com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceUpdateReTrack;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.albumview.AlbumEditText;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.albumview.GridViewBaseAdapter;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.albumview.OnGridViewListener;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.albumview.SlideBar;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.albumview.VideoViewAdapter;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.emotion.GridViewEmotionAdapter;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.PhotoModel;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.VideoModel;
import com.huawei.healthcloud.plugintrack.ui.activity.PhotoAndVideoSelectActivity;
import com.huawei.healthcloud.plugintrack.ui.activity.PhotoSelectActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.gridview.HealthGridView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import defpackage.hcd;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.util.LogUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class hcd {
    private boolean ab;
    private InterfaceReTrack ae;
    private RelativeLayout ai;
    private HealthTextView aj;
    private ArrayList<PhotoModel> ak;
    private HealthTextView al;
    private int am;
    private ReTrackSimplify an;
    private HealthTextView ao;
    private SlideBar ap;
    private HealthTextView ar;
    private String at;
    private HealthSwitchButton au;
    private ImageView av;
    private LinearLayout aw;
    private HealthTextView ax;
    private VideoViewAdapter ay;
    private ArrayList<VideoModel> bb;
    private LinearLayout bc;
    private View d;
    private CustomTextAlertDialog f;
    private HealthCardView g;
    private HealthTextView h;
    private HealthTextView i;
    private Context j;
    private AlbumEditText l;
    private ImageButton m;
    private GridViewBaseAdapter p;
    private LinearLayout q;
    private HealthGridView r;
    private HealthGridView t;
    private InterfaceUpdateReTrack w;
    private boolean x;
    private boolean z;
    private int n = 0;
    private int k = 0;
    private int as = 0;
    private int aq = 0;
    private int o = 0;
    private Map<Integer, String> u = new LinkedHashMap();
    private Map<Integer, ArrayList<PhotoModel>> e = new LinkedHashMap();
    private Map<Integer, ArrayList<PhotoModel>> y = new LinkedHashMap();
    private Map<Integer, ArrayList<VideoModel>> v = new LinkedHashMap();
    private Map<Integer, List<Bitmap>> s = new HashMap();
    private String ac = "";
    private List<hce> ah = hch.d();
    private int c = -1;
    private Map<PhotoModel, Bitmap> ad = new HashMap();
    private Handler af = null;
    private boolean aa = false;
    private int ag = 9;

    /* renamed from: a, reason: collision with root package name */
    private OnGridViewListener f13083a = new OnGridViewListener() { // from class: hcd.3
        @Override // com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.albumview.OnGridViewListener
        public void onPhotoOrVideoDelete(int i) {
            hcd.b(hcd.this);
            hcd.c(hcd.this);
            ViewGroup.LayoutParams layoutParams = hcd.this.i.getLayoutParams();
            if (layoutParams instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
                if (hcd.this.n == 3) {
                    layoutParams2.setMarginStart(hcn.a(hcd.this.j, (hcd.this.n * 73) + 6));
                } else {
                    layoutParams2.setMarginStart(hcn.a(hcd.this.j, ((hcd.this.n + 1) * 73) + 6));
                }
                hcd.this.i.setLayoutParams(layoutParams2);
                hcd.this.i.setText(hcd.this.j.getResources().getString(R.string._2130839946_res_0x7f02098a, Integer.valueOf(hcd.this.n), 3));
            }
            ArrayList arrayList = (ArrayList) hcd.this.y.get(Integer.valueOf(hcd.this.o));
            List list = (List) hcd.this.s.get(Integer.valueOf(hcd.this.o));
            if (koq.b(arrayList, i) || koq.b(list, i)) {
                LogUtil.c("AlbumDialog", "onPhotoDelete out of bounds");
                return;
            }
            arrayList.remove(i);
            list.remove(i);
            if (hcd.this.n == 0) {
                hcd.this.y.remove(Integer.valueOf(hcd.this.o));
                hcd.this.s.remove(Integer.valueOf(hcd.this.o));
                hcd.this.w.clearMarker(hcd.this.o);
                hcd.this.w.clearCurMarker(hcd.this.c);
                if (hcd.this.u.containsKey(Integer.valueOf(hcd.this.o))) {
                    hcd.this.w.addMarker(hcd.this.o, InterfaceUpdateReTrack.MarkerType.Text_type);
                    hcd hcdVar = hcd.this;
                    hcdVar.c = hcdVar.w.addCurMarker(hcd.this.o, InterfaceUpdateReTrack.MarkerType.Text_type);
                }
            }
            hcd.this.ap.setIndexOfPhoto(hcd.this.y, hcd.this.o);
            hcd.this.w.addPhotosList(hcd.this.s);
            hcd.this.w.setPhotosNumber(hcd.this.as);
            hcd.this.ak();
        }
    };
    private OnGridViewListener b = new OnGridViewListener() { // from class: hcd.6
        @Override // com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.albumview.OnGridViewListener
        public void onPhotoOrVideoDelete(int i) {
            hcd.j(hcd.this);
            hcd.f(hcd.this);
            ViewGroup.LayoutParams layoutParams = hcd.this.i.getLayoutParams();
            if (layoutParams instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
                if (hcd.this.k == 1) {
                    layoutParams2.setMarginStart(hcn.a(hcd.this.j, (hcd.this.k * 73) + 6));
                } else {
                    layoutParams2.setMarginStart(hcn.a(hcd.this.j, ((hcd.this.k + 1) * 73) + 6));
                }
                hcd.this.i.setLayoutParams(layoutParams2);
                hcd.this.i.setText(hcd.this.j.getResources().getString(R.string._2130839946_res_0x7f02098a, Integer.valueOf(hcd.this.n), 1));
            }
            ArrayList arrayList = (ArrayList) hcd.this.v.get(Integer.valueOf(hcd.this.o));
            if (koq.b(arrayList, i)) {
                LogUtil.c("AlbumDialog", "onVideoDelete out of bounds");
                return;
            }
            arrayList.remove(i);
            if (hcd.this.k <= 0) {
                hcd.this.v.remove(Integer.valueOf(hcd.this.o));
                hcd.this.w.clearMarker(hcd.this.o);
                hcd.this.w.clearCurMarker(hcd.this.c);
                if (hcd.this.u.containsKey(Integer.valueOf(hcd.this.o))) {
                    hcd.this.w.addMarker(hcd.this.o, InterfaceUpdateReTrack.MarkerType.Text_type);
                    hcd hcdVar = hcd.this;
                    hcdVar.c = hcdVar.w.addCurMarker(hcd.this.o, InterfaceUpdateReTrack.MarkerType.Text_type);
                }
            }
            hcd.this.ap.setIndexOfVideo(hcd.this.v, hcd.this.o);
            LogUtil.d("AlbumDialog", " onPhotoOrVideoDelete  setIndexOfVideo");
            hcd.this.w.addVideoPath(hcd.this.v);
            hcd.this.w.setVideoNumber(hcd.this.as);
            hcd.this.ak();
        }
    };

    static /* synthetic */ int b(hcd hcdVar) {
        int i = hcdVar.as;
        hcdVar.as = i - 1;
        return i;
    }

    static /* synthetic */ int c(hcd hcdVar) {
        int i = hcdVar.n;
        hcdVar.n = i - 1;
        return i;
    }

    static /* synthetic */ int f(hcd hcdVar) {
        int i = hcdVar.k;
        hcdVar.k = i - 1;
        return i;
    }

    static /* synthetic */ int j(hcd hcdVar) {
        int i = hcdVar.aq;
        hcdVar.aq = i - 1;
        return i;
    }

    public hcd(Context context, InterfaceReTrack interfaceReTrack) {
        this.ae = null;
        this.w = null;
        this.an = null;
        this.j = context;
        this.ae = interfaceReTrack;
        InterfaceUpdateReTrack obtainInterfaceUpdateReTrack = interfaceReTrack.obtainInterfaceUpdateReTrack();
        this.w = obtainInterfaceUpdateReTrack;
        this.an = obtainInterfaceUpdateReTrack.obtainSimlify();
        this.am = this.w.getTrackNumber();
        this.at = hji.e(this.an.getValidTotalDistance());
    }

    public void aZD_(View view) {
        this.d = view;
        this.aw = (LinearLayout) view.findViewById(R.id.track_album_mark_layout);
        this.bc = (LinearLayout) this.d.findViewById(R.id.track_album_triangle);
        this.ai = (RelativeLayout) this.d.findViewById(R.id.retrack_album_londing_rl);
        this.av = (ImageView) this.d.findViewById(R.id.trackalbum_white_triangle_image);
        this.au = (HealthSwitchButton) this.d.findViewById(R.id.switch_btn);
        k();
    }

    private void af() {
        this.w.resetDismiss();
        this.w.deleteMoveMarker();
    }

    public void a(boolean z) {
        this.aa = z;
        q();
    }

    public void g() {
        this.w.resetShow();
        this.w.clearCurMarker(this.c);
        this.c = -1;
        if (this.y.containsKey(Integer.valueOf(this.o))) {
            this.c = this.w.addCurMarker(this.o, InterfaceUpdateReTrack.MarkerType.Image_type);
        } else if (this.v.containsKey(Integer.valueOf(this.o))) {
            this.c = this.w.addCurMarker(this.o, InterfaceUpdateReTrack.MarkerType.Video_type);
        } else if (this.u.containsKey(Integer.valueOf(this.o))) {
            this.c = this.w.addCurMarker(this.o, InterfaceUpdateReTrack.MarkerType.Text_type);
        } else {
            LogUtil.d("AlbumDialog", "No new edits");
        }
        if (this.y.containsKey(Integer.valueOf(this.o))) {
            this.n = this.y.get(Integer.valueOf(this.o)).size();
        } else {
            this.n = 0;
        }
        if (this.v.containsKey(Integer.valueOf(this.o))) {
            this.k = this.v.get(Integer.valueOf(this.o)).size();
        } else {
            this.k = 0;
        }
        ag();
        ai();
        ak();
        this.w.addMoveMarker(this.o);
        this.w.updateTrack(0, this.o);
    }

    private void k() {
        m();
        w();
        r();
        t();
        q();
        s();
        p();
        u();
        b(true);
        d((List<PhotoModel>) null);
    }

    private void p() {
        View view = this.d;
        if (view == null) {
            LogUtil.c("AlbumDialog", "initShareSwitchLayout mAlbumDialogView == null");
            return;
        }
        ((LinearLayout) view.findViewById(R.id.share_name_open)).setVisibility(8);
        HealthScrollView healthScrollView = (HealthScrollView) this.d.findViewById(R.id.album_scrollview);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) healthScrollView.getLayoutParams();
        layoutParams.height = -2;
        layoutParams.width = -1;
        layoutParams.topMargin = hcn.a(this.j, 90.0f);
        healthScrollView.setLayoutParams(layoutParams);
        this.g = (HealthCardView) this.d.findViewById(R.id.state_layout);
    }

    private void u() {
        this.au.setChecked(((SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class)).isShowUserInfo());
    }

    private void b(boolean z) {
        if (nrt.a(this.j)) {
            if (z) {
                this.m.setImageDrawable(nrf.cJH_(this.j.getResources().getDrawable(R.drawable._2131431953_res_0x7f0b1211), this.j.getResources().getColor(R.color._2131297782_res_0x7f0905f6)));
            } else {
                this.m.setImageDrawable(nrf.cJH_(this.j.getResources().getDrawable(R.drawable._2131431954_res_0x7f0b1212), this.j.getResources().getColor(R.color._2131297782_res_0x7f0905f6)));
            }
            this.av.setBackground(this.j.getResources().getDrawable(R.drawable._2131431957_res_0x7f0b1215));
            this.l.setBackground(this.j.getResources().getDrawable(R.drawable._2131431793_res_0x7f0b1171));
            this.ap.setBackgroundColor(this.j.getResources().getColor(R.color._2131296666_res_0x7f09019a));
            this.bc.setBackgroundColor(this.j.getResources().getColor(R.color._2131296666_res_0x7f09019a));
            this.aw.setBackgroundColor(this.j.getResources().getColor(R.color._2131296666_res_0x7f09019a));
            HealthCardView healthCardView = this.g;
            if (healthCardView != null) {
                healthCardView.setCardBackgroundColor(this.j.getResources().getColor(R.color._2131296666_res_0x7f09019a));
            }
            LogUtil.d("AlbumDialog", "setDarkLayout night", Boolean.valueOf(nsn.v(this.j)));
            return;
        }
        if (z) {
            this.m.setImageDrawable(this.j.getResources().getDrawable(R.drawable._2131431953_res_0x7f0b1211));
        } else {
            this.m.setImageDrawable(this.j.getResources().getDrawable(R.drawable._2131431954_res_0x7f0b1212));
        }
        this.av.setBackground(this.j.getResources().getDrawable(R.drawable._2131431956_res_0x7f0b1214));
        this.l.setBackground(this.j.getResources().getDrawable(R.drawable._2131431792_res_0x7f0b1170));
        this.ap.setBackgroundColor(this.j.getResources().getColor(R.color._2131296690_res_0x7f0901b2));
        this.bc.setBackgroundColor(this.j.getResources().getColor(R.color._2131296690_res_0x7f0901b2));
        this.aw.setBackgroundColor(this.j.getResources().getColor(R.color._2131296690_res_0x7f0901b2));
        LogUtil.d("AlbumDialog", "setDarkLayout", Boolean.valueOf(nsn.v(this.j)));
    }

    private void m() {
        this.h = (HealthTextView) this.d.findViewById(R.id.clear_all);
    }

    private void w() {
        this.ap = (SlideBar) this.d.findViewById(R.id.slide_bar);
        if (LanguageUtil.bc(this.j)) {
            this.ap.setRotationY(180.0f);
        }
        this.ap.setNumberPoints(this.am);
        this.ap.setTrackSimplify(this.an);
        this.ap.a();
        this.ap.c();
        this.ap.invalidate();
    }

    private void r() {
        this.l = (AlbumEditText) this.d.findViewById(R.id.edittext);
        HealthTextView healthTextView = (HealthTextView) this.d.findViewById(R.id.current_distance);
        this.al = healthTextView;
        healthTextView.setText("0.00/" + this.at + hcr.b(this.j));
        HealthTextView healthTextView2 = (HealthTextView) this.d.findViewById(R.id.information_split);
        this.ao = healthTextView2;
        healthTextView2.setText(" - ");
        this.ao.setVisibility(8);
        HealthTextView healthTextView3 = (HealthTextView) this.d.findViewById(R.id.max_information);
        this.ar = healthTextView3;
        healthTextView3.setText(this.ap.getWonderfulText());
        c(this.ap.getWonderfulText());
        this.aj = (HealthTextView) this.d.findViewById(R.id.photo_limit);
        HealthTextView healthTextView4 = (HealthTextView) this.d.findViewById(R.id.thinking_limit);
        this.ax = healthTextView4;
        healthTextView4.setText(this.j.getResources().getString(R.string._2130839947_res_0x7f02098b, 0, 30));
    }

    private void c(String str) {
        this.ao.setVisibility(8);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (str.startsWith(Constants.LINK) || str.startsWith(" -")) {
            if (str.startsWith(Constants.LINK)) {
                this.ao.setText(" ");
                this.ao.setVisibility(0);
                return;
            }
            return;
        }
        this.ao.setVisibility(0);
    }

    private void t() {
        this.r = (HealthGridView) this.d.findViewById(R.id.gridView_photo);
        HealthTextView healthTextView = (HealthTextView) this.d.findViewById(R.id.choose_number);
        this.i = healthTextView;
        healthTextView.getLayoutParams();
        b(R.string._2130839946_res_0x7f02098a);
        if (this.aa) {
            this.i.setVisibility(8);
            this.bb = new ArrayList<>();
        } else {
            this.i.setVisibility(0);
        }
        this.ak = new ArrayList<>();
    }

    private void q() {
        HealthTextView healthTextView = this.i;
        if (healthTextView == null || this.aj == null) {
            LogUtil.c("AlbumDialog", "initPremiumEditionView: view is null");
            return;
        }
        if (this.aa) {
            healthTextView.setVisibility(8);
            this.bb = new ArrayList<>();
        } else {
            healthTextView.setVisibility(0);
        }
        if (this.aa) {
            this.aj.setText(this.j.getResources().getString(R.string._2130840066_res_0x7f020a02));
        } else {
            this.aj.setText(this.j.getResources().getString(R.string._2130839937_res_0x7f020981));
        }
    }

    private void b(int i) {
        ViewGroup.LayoutParams layoutParams = this.i.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            layoutParams2.setMarginStart(hcn.a(this.j, 79.0f));
            this.i.setLayoutParams(layoutParams2);
            this.i.setText(this.j.getResources().getString(i, Integer.valueOf(this.n), 3));
        }
    }

    private void s() {
        Drawable drawable;
        this.q = (LinearLayout) this.d.findViewById(R.id.grip_view_expression_layout);
        this.m = (ImageButton) this.d.findViewById(R.id.add_emoji);
        HealthGridView healthGridView = (HealthGridView) this.d.findViewById(R.id.grip_view_expression);
        this.t = healthGridView;
        healthGridView.setAdapter((ListAdapter) new GridViewEmotionAdapter(this.j, this.ah));
        ImageView imageView = (ImageView) this.d.findViewById(R.id.track_expression_delete_image);
        if (nsn.v(this.j)) {
            drawable = nrf.cJH_(this.j.getResources().getDrawable(R.drawable._2131431199_res_0x7f0b0f1f), this.j.getResources().getColor(R.color._2131297782_res_0x7f0905f6));
        } else {
            drawable = this.j.getResources().getDrawable(R.drawable._2131431199_res_0x7f0b0f1f);
        }
        imageView.setImageDrawable(drawable);
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            float dimension = this.j.getResources().getDimension(R.dimen._2131363572_res_0x7f0a06f4);
            layoutParams2.width = (int) (((nsn.n() - dimension) - dimension) / 7.0f);
            layoutParams2.rightMargin = (int) dimension;
            imageView.setLayoutParams(layoutParams2);
        }
        imageView.setOnClickListener(new View.OnClickListener() { // from class: hcd.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                KeyEvent keyEvent = new KeyEvent(0, 67);
                KeyEvent keyEvent2 = new KeyEvent(1, 67);
                hcd.this.l.onKeyDown(67, keyEvent);
                hcd.this.l.onKeyUp(67, keyEvent2);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    public void i() {
        v();
        aa();
        ac();
        ad();
        y();
        z();
    }

    private void z() {
        this.au.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.albumview.AlbumDialog$$ExternalSyntheticLambda0
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                hcd.this.aZE_(compoundButton, z);
            }
        });
    }

    public /* synthetic */ void aZE_(CompoundButton compoundButton, boolean z) {
        LogUtil.d("AlbumDialog", "onListenUserInfo isChecked", Boolean.valueOf(z));
        if (z) {
            ae();
            ((SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class)).setIsShowUserInfo(true);
        } else {
            al();
        }
        ViewClickInstrumentation.clickOnView(compoundButton);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ae() {
        Message obtain = Message.obtain();
        obtain.what = 131;
        this.af.sendMessage(obtain);
    }

    public void aZG_(Handler handler) {
        this.af = handler;
    }

    public void aZF_(Message message) {
        if (message == null) {
            return;
        }
        aZz_(message);
    }

    private void aZz_(Message message) {
        int i = message.what;
        if (i == 517) {
            if (message.obj instanceof String) {
                this.ax.setText((String) message.obj);
                return;
            }
            return;
        }
        if (i != 528) {
            switch (i) {
                case 513:
                    if (hbz.d(message.obj, Integer.class, String.class)) {
                        Pair pair = (Pair) message.obj;
                        this.ax.setText(this.j.getResources().getString(R.string._2130839947_res_0x7f02098b, pair.first, 30));
                        this.l.setText((CharSequence) pair.second);
                        break;
                    }
                    break;
                case d.a.d /* 514 */:
                    if (message.obj instanceof String) {
                        this.al.setText(hcr.c((String) message.obj, this.an.getValidTotalDistance()) + "/" + this.at + hcr.b(this.j));
                        break;
                    }
                    break;
                case 515:
                    if (message.obj instanceof String) {
                        String str = (String) message.obj;
                        this.ar.setText(str);
                        c(str);
                        e(str);
                        break;
                    }
                    break;
                default:
                    switch (i) {
                        case 519:
                            aZA_(message);
                            break;
                        case 520:
                            this.ai.setVisibility(8);
                            break;
                        case 521:
                            this.ai.setVisibility(8);
                            nrh.b(this.j, R.string._2130843631_res_0x7f0217ef);
                            break;
                    }
            }
            return;
        }
        aZB_(message);
    }

    private void e(String str) {
        if (TextUtils.equals(str, this.ac)) {
            return;
        }
        hcj.e(this.j, this.an.getSportType(), this.x, 5, str);
        this.ac = str;
    }

    private void aZB_(Message message) {
        if (hbz.e(message.obj, VideoModel.class)) {
            b((ArrayList<VideoModel>) message.obj);
        }
    }

    private void aZA_(Message message) {
        this.ai.setVisibility(8);
        if (hbz.e(message.obj, PhotoModel.class)) {
            c((ArrayList<PhotoModel>) message.obj);
        }
        LogUtil.d("AlbumDialog", "AddPhotoIndex0:", String.valueOf(this.o));
    }

    private void b(ArrayList<VideoModel> arrayList) {
        if (arrayList == null) {
            return;
        }
        this.w.clearCurMarker(this.c);
        this.c = -1;
        this.aq = (this.aq - this.k) + arrayList.size();
        this.k = arrayList.size();
        ViewGroup.LayoutParams layoutParams = this.i.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            if (this.k == 1) {
                layoutParams2.setMarginStart(hcn.a(this.j, (this.n * 73) + 6));
            } else {
                layoutParams2.setMarginStart(hcn.a(this.j, ((this.n + 1) * 73) + 6));
            }
            this.i.setLayoutParams(layoutParams2);
            this.i.setText(this.j.getResources().getString(R.string._2130839946_res_0x7f02098a, Integer.valueOf(this.n), 1));
        }
        if (arrayList.size() > 0) {
            this.w.addMarker(this.o, InterfaceUpdateReTrack.MarkerType.Video_type);
            this.c = this.w.addCurMarker(this.o, InterfaceUpdateReTrack.MarkerType.Video_type);
            this.v.put(Integer.valueOf(this.o), arrayList);
        } else {
            this.v.remove(Integer.valueOf(this.o));
            this.w.clearMarker(this.o);
            this.w.clearCurMarker(this.c);
            if (this.u.containsKey(Integer.valueOf(this.o))) {
                LogUtil.d("AlbumDialog", "[changeCurrentVideo] mIndexOfTexts contains key currentPointIndex.");
                this.w.addMarker(this.o, InterfaceUpdateReTrack.MarkerType.Text_type);
                this.c = this.w.addCurMarker(this.o, InterfaceUpdateReTrack.MarkerType.Text_type);
            }
        }
        e(arrayList);
        this.w.addVideoPath(this.v);
        ak();
        this.ap.setIndexOfVideo(this.v, this.o);
        this.w.setVideoNumber(this.aq);
    }

    private void e(List<VideoModel> list) {
        ArrayList<VideoModel> arrayList = new ArrayList<>();
        a(list, arrayList);
        arrayList.add(null);
        if (arrayList.equals(this.bb)) {
            return;
        }
        if (!koq.d(this.ak, 0) || this.ak.get(0) == null) {
            this.bb = arrayList;
            VideoViewAdapter videoViewAdapter = new VideoViewAdapter(this.j, this.bb);
            this.ay = videoViewAdapter;
            videoViewAdapter.b(this.b);
            this.r.setAdapter((ListAdapter) this.ay);
        }
    }

    private void a(List<VideoModel> list, ArrayList<VideoModel> arrayList) {
        if (list == null) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                arrayList.add(list.get(i));
            }
        }
    }

    public void b(IBaseResponseCallback iBaseResponseCallback) {
        hcm hcmVar = new hcm();
        hcmVar.b(this.as);
        hcmVar.d(this.aq);
        String obj = this.l.getText().toString();
        hcmVar.d((obj == null || obj.isEmpty()) ? false : true);
        iBaseResponseCallback.d(1, hcmVar);
        if (c()) {
            e();
        }
        if (this.z) {
            aZy_(this.l);
        }
        af();
    }

    private void v() {
        this.ap.getViewTreeObserver().addOnDrawListener(new ViewTreeObserver.OnDrawListener() { // from class: hcd.8
            @Override // android.view.ViewTreeObserver.OnDrawListener
            public void onDraw() {
                int currentPointIndex = hcd.this.ap.getCurrentPointIndex();
                Message obtain = Message.obtain();
                obtain.what = d.a.d;
                obtain.obj = hji.e(Math.abs(hcd.this.ae.getCurrentDistance(currentPointIndex)));
                hcd.this.af.sendMessage(obtain);
                Message obtain2 = Message.obtain();
                obtain2.what = 515;
                obtain2.obj = hcd.this.ap.getWonderfulText();
                hcd.this.af.sendMessage(obtain2);
                if (currentPointIndex != hcd.this.o) {
                    hcd.this.w.updateTrack(hcd.this.o, currentPointIndex);
                    hcd.this.o = currentPointIndex;
                    hcd.this.w.clearCurMarker(hcd.this.c);
                    hcd.this.w.moveMarkerToCurrent(hcd.this.o);
                    hcd.this.c = -1;
                    if (!hcd.this.y.containsKey(Integer.valueOf(hcd.this.o))) {
                        hcd.this.n = 0;
                    } else {
                        hcd hcdVar = hcd.this;
                        hcdVar.n = ((ArrayList) hcdVar.y.get(Integer.valueOf(hcd.this.o))).size();
                    }
                    if (!hcd.this.v.containsKey(Integer.valueOf(hcd.this.o))) {
                        hcd.this.k = 0;
                    } else {
                        hcd hcdVar2 = hcd.this;
                        hcdVar2.k = ((ArrayList) hcdVar2.v.get(Integer.valueOf(hcd.this.o))).size();
                    }
                    hcd.this.ag();
                    hcd.this.ai();
                    hcd.this.ah();
                    hcj.e(hcd.this.j, hcd.this.an.getSportType(), hcd.this.x, 4, "");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ag() {
        ViewGroup.LayoutParams layoutParams = this.i.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            if (this.k == 1) {
                layoutParams2.setMarginStart(hcn.a(this.j, (r1 * 73) + 6));
            } else {
                layoutParams2.setMarginStart(hcn.a(this.j, ((r1 + 1) * 73) + 6));
            }
            this.i.setLayoutParams(layoutParams2);
            this.i.setText(this.j.getResources().getString(R.string._2130839946_res_0x7f02098a, Integer.valueOf(this.n), 1));
        }
        if (this.v.containsKey(Integer.valueOf(this.o))) {
            ArrayList<VideoModel> arrayList = this.v.get(Integer.valueOf(this.o));
            if (arrayList == null) {
                LogUtil.c("AlbumDialog", "tempVideoList  is null");
                e((List<VideoModel>) null);
                return;
            }
            e(arrayList);
            if (arrayList.size() <= 0 || this.c != -1) {
                return;
            }
            this.c = this.w.addCurMarker(this.o, InterfaceUpdateReTrack.MarkerType.Video_type);
            return;
        }
        e((List<VideoModel>) null);
    }

    private void aa() {
        this.l.addTextChangedListener(new TextWatcher() { // from class: hcd.15
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (hcd.this.l.getText().length() == 0) {
                    hcd.this.ax.setText(hcd.this.j.getResources().getString(R.string._2130839947_res_0x7f02098b, 0, 30));
                } else {
                    hcd.this.ax.setText(hcd.this.j.getResources().getString(R.string._2130839947_res_0x7f02098b, Integer.valueOf(hcd.this.l.getText().length()), 30));
                }
                hcd.this.h();
                hcd.this.ak();
            }
        });
        this.l.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: hcd.14
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                if (hcd.this.ab) {
                    if (hcd.this.z) {
                        return true;
                    }
                    hcd.this.aj();
                    hcd.this.ab = false;
                    return false;
                }
                if (!hcd.this.c() || !hcd.this.z) {
                    return true;
                }
                hcd.this.e();
                return false;
            }
        });
        o();
    }

    private void o() {
        this.l.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: hcd.11
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() != 66 || !(textView.getContext().getSystemService("input_method") instanceof InputMethodManager)) {
                    return false;
                }
                InputMethodManager inputMethodManager = (InputMethodManager) textView.getContext().getSystemService("input_method");
                if (!inputMethodManager.isActive()) {
                    return true;
                }
                inputMethodManager.hideSoftInputFromWindow(textView.getApplicationWindowToken(), 0);
                return true;
            }
        });
    }

    private void ac() {
        this.r.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: hcd.13
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (hcd.this.bb == null || hcd.this.bb.size() <= 1) {
                    if ((i == hcd.this.ak.size() - 1 && i < 3) || (i == 0 && hcd.this.ak.size() == 0)) {
                        if (!nsn.cLk_(view)) {
                            hcd.this.f();
                        } else {
                            LogUtil.d("AlbumDialog", "click too fast");
                            ViewClickInstrumentation.clickOnListView(adapterView, view, i);
                            return;
                        }
                    }
                    ViewClickInstrumentation.clickOnListView(adapterView, view, i);
                    return;
                }
                LogUtil.d("AlbumDialog", "add is video");
                ViewClickInstrumentation.clickOnListView(adapterView, view, i);
            }
        });
    }

    private void ad() {
        this.m.setOnClickListener(new View.OnClickListener() { // from class: hcd.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                hcd.this.ab();
                hcj.e(hcd.this.j, hcd.this.an.getSportType(), hcd.this.x, 3, "");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.t.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: hcd.1
            /* JADX WARN: Type inference failed for: r6v1, types: [android.widget.Adapter] */
            /* JADX WARN: Type inference failed for: r6v4, types: [android.widget.Adapter] */
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (adapterView.getAdapter().getItem(i) instanceof hce) {
                    hce hceVar = (hce) adapterView.getAdapter().getItem(i);
                    if (hceVar != null) {
                        int selectionStart = hcd.this.l.getSelectionStart();
                        SpannableString aZU_ = hci.aZU_(hcd.this.j, hceVar.d(), (int) hcd.this.l.getTextSize());
                        if (hcd.this.l.getText().length() + aZU_.length() <= 30) {
                            hcd.this.l.getText().insert(selectionStart, aZU_);
                        } else {
                            ViewClickInstrumentation.clickOnListView(adapterView, view, i);
                            return;
                        }
                    } else {
                        LogUtil.e("AlbumDialog", "onItemClick note is null.");
                        ViewClickInstrumentation.clickOnListView(adapterView, view, i);
                        return;
                    }
                }
                ViewClickInstrumentation.clickOnListView(adapterView, view, i);
            }
        });
    }

    private void y() {
        this.h.setOnClickListener(new View.OnClickListener() { // from class: hcd.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                hcd.this.j();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        this.as -= this.n;
        this.n = 0;
        d((List<PhotoModel>) null);
        e((List<VideoModel>) null);
        this.y.remove(Integer.valueOf(this.o));
        this.s.remove(Integer.valueOf(this.o));
        this.v.remove(Integer.valueOf(this.o));
        this.ap.setIndexOfPhoto(this.y, this.o);
        this.ap.setIndexOfVideo(this.v, this.o);
        this.w.addPhotosList(this.s);
        this.w.addVideoPath(this.v);
        this.w.setPhotosNumber(this.as);
        this.w.setVideoNumber(this.aq);
        LogUtil.d("AlbumDialog", "[clearCurrentAll] mSumPhotosNumber:", String.valueOf(this.as), " mSumVideoNumber :", String.valueOf(this.aq));
        this.ax.setText(this.j.getResources().getString(R.string._2130839947_res_0x7f02098b, 0, 30));
        if (this.aa) {
            this.aj.setText(this.j.getResources().getString(R.string._2130840066_res_0x7f020a02));
        } else {
            this.aj.setText(this.j.getResources().getString(R.string._2130839937_res_0x7f020981));
        }
        b(R.string._2130839946_res_0x7f02098a);
        this.l.setText("");
    }

    private void d(List<PhotoModel> list) {
        ArrayList<PhotoModel> arrayList = new ArrayList<>();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) != null) {
                    arrayList.add(list.get(i));
                }
            }
        }
        arrayList.add(null);
        if (arrayList.equals(this.ak)) {
            return;
        }
        if (!koq.d(this.bb, 0) || this.bb.get(0) == null) {
            this.ak = arrayList;
            GridViewBaseAdapter gridViewBaseAdapter = new GridViewBaseAdapter(this.j, this.ak);
            this.p = gridViewBaseAdapter;
            gridViewBaseAdapter.b(this.f13083a);
            this.r.setAdapter((ListAdapter) this.p);
        }
    }

    private void c(final List<PhotoModel> list) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: hcd.2
            @Override // java.lang.Runnable
            public void run() {
                Bitmap aZZ_;
                boolean z = false;
                for (int i = 0; i < list.size(); i++) {
                    PhotoModel photoModel = (PhotoModel) list.get(i);
                    if (photoModel != null && !hcd.this.ad.containsKey(photoModel)) {
                        Matrix matrix = new Matrix();
                        Bitmap bitmap = null;
                        try {
                            aZZ_ = hck.aZZ_(hcd.this.j, (PhotoModel) list.get(i));
                        } catch (IOException e) {
                            LogUtil.e("AlbumDialog", LogAnonymous.b((Throwable) e));
                        }
                        if (aZZ_ == null) {
                            return;
                        }
                        matrix.postRotate(hck.baa_(new ExifInterface(photoModel.getPath())));
                        bitmap = Bitmap.createBitmap(aZZ_, 0, 0, aZZ_.getWidth(), aZZ_.getHeight(), matrix, true);
                        if (aZZ_ != bitmap) {
                            aZZ_.recycle();
                        }
                        if (bitmap != null) {
                            hcd.this.ad.put(photoModel, bitmap);
                        } else {
                            z = true;
                        }
                    }
                }
                if (z) {
                    hcd.this.x();
                } else {
                    hcd.this.b((List<PhotoModel>) list);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        this.af.sendEmptyMessageDelayed(521, 500L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<PhotoModel> list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(this.ad.get(list.get(i)));
        }
        if (arrayList.size() == 0) {
            this.s.remove(Integer.valueOf(this.o));
        } else {
            this.s.put(Integer.valueOf(this.o), arrayList);
        }
        this.w.addPhotosList(this.s);
        this.af.sendEmptyMessageDelayed(520, 500L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        String string;
        if (this.aa) {
            this.ag = 30;
        } else {
            this.ag = 9;
        }
        int i = this.n;
        if (i == 3 || this.as >= this.ag) {
            if (i == 3) {
                string = this.j.getResources().getString(R.string._2130839940_res_0x7f020984, 3);
            } else {
                string = this.j.getResources().getString(R.string._2130839941_res_0x7f020985, Integer.valueOf(this.ag));
            }
            Toast.makeText(this.j, string, 0).show();
            return;
        }
        l();
    }

    private void l() {
        PermissionUtil.b(this.j, PermissionUtil.PermissionType.MEDIA_VIDEO_IMAGES, new CustomPermissionAction(this.j) { // from class: hcd.5
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                Intent intent;
                LogUtil.d("AlbumDialog", "[enterImageSelectAct] enter mSumPhotosNumber:", String.valueOf(hcd.this.as));
                if (hcd.this.aa) {
                    intent = new Intent(hcd.this.j, (Class<?>) PhotoAndVideoSelectActivity.class);
                } else {
                    intent = new Intent(hcd.this.j, (Class<?>) PhotoSelectActivity.class);
                }
                intent.putExtra("key_data_max_photo_count", hcd.this.aa ? 30 : 9);
                intent.putExtra("total_selected_image_num_key", hcd.this.as);
                intent.putParcelableArrayListExtra("selected_image_path_key", hcd.this.n());
                intent.putExtra("sport_type", hcd.this.an.getSportType());
                intent.putExtra("trackType", hcd.this.x);
                if (hcd.this.j instanceof Activity) {
                    ((Activity) hcd.this.j).startActivityForResult(intent, 5);
                }
                hcj.e(hcd.this.j, hcd.this.an.getSportType(), hcd.this.x, 1, "");
                LogUtil.d("AlbumDialog", "[enterImageSelectAct] end mSumPhotosNumber:", String.valueOf(hcd.this.as));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<PhotoModel> n() {
        if (this.y.containsKey(Integer.valueOf(this.o))) {
            return this.y.get(Integer.valueOf(this.o));
        }
        return new ArrayList<>();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ai() {
        ViewGroup.LayoutParams layoutParams = this.i.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            if (this.n == 3) {
                layoutParams2.setMarginStart(hcn.a(this.j, (r1 * 73) + 6));
            } else {
                layoutParams2.setMarginStart(hcn.a(this.j, ((r1 + 1) * 73) + 6));
            }
            this.i.setLayoutParams(layoutParams2);
            this.i.setText(this.j.getResources().getString(R.string._2130839946_res_0x7f02098a, Integer.valueOf(this.n), 3));
        }
        if (this.y.containsKey(Integer.valueOf(this.o))) {
            ArrayList<PhotoModel> arrayList = this.y.get(Integer.valueOf(this.o));
            if (arrayList == null) {
                d((List<PhotoModel>) null);
                return;
            }
            d(arrayList);
            if (arrayList.size() <= 0 || this.c != -1) {
                return;
            }
            this.c = this.w.addCurMarker(this.o, InterfaceUpdateReTrack.MarkerType.Image_type);
            return;
        }
        d((List<PhotoModel>) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ah() {
        if (this.u.containsKey(Integer.valueOf(this.o))) {
            String str = this.u.get(Integer.valueOf(this.o));
            if (str == null) {
                this.l.setText("");
                return;
            }
            this.l.setText(hci.aZU_(this.j, str, (int) this.l.getTextSize()));
            this.l.setSelection(str.length());
            if (this.c == -1) {
                this.c = this.w.addCurMarker(this.o, InterfaceUpdateReTrack.MarkerType.Text_type);
                return;
            }
            return;
        }
        this.l.setText("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ab() {
        if (c()) {
            aZC_(this.l);
        } else {
            if (this.z) {
                this.ab = true;
                aj();
                aZy_(this.l);
                return;
            }
            aj();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        String obj = this.l.getText().toString().length() == 0 ? null : this.l.getText().toString();
        if (obj == null) {
            this.u.remove(Integer.valueOf(this.o));
        } else {
            this.u.put(Integer.valueOf(this.o), obj);
        }
        if (!this.u.containsKey(Integer.valueOf(this.o)) && !this.y.containsKey(Integer.valueOf(this.o)) && !this.v.containsKey(Integer.valueOf(this.o))) {
            this.w.clearMarker(this.o);
            this.w.clearCurMarker(this.c);
            this.c = -1;
        } else if (!this.y.containsKey(Integer.valueOf(this.o)) || !this.v.containsKey(Integer.valueOf(this.o))) {
            this.w.addMarker(this.o, InterfaceUpdateReTrack.MarkerType.Text_type);
            if (this.c == -1) {
                this.c = this.w.addCurMarker(this.o, InterfaceUpdateReTrack.MarkerType.Text_type);
            }
        } else {
            LogUtil.d("AlbumDialog", "No need to add icons");
        }
        this.ap.setIndexOfText(this.u);
        this.w.addText(this.o, obj);
    }

    private void c(ArrayList<PhotoModel> arrayList) {
        LogUtil.d("AlbumDialog", "[changeCurrentPhoto] enter mSumPhotosNumber:", Integer.valueOf(this.as), " mCurPhotosNumber: ", Integer.valueOf(this.n), " selectPhotoList is Size: ", Integer.valueOf(arrayList.size()));
        this.w.clearCurMarker(this.c);
        this.c = -1;
        this.as = (this.as - this.n) + arrayList.size();
        this.n = arrayList.size();
        ViewGroup.LayoutParams layoutParams = this.i.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            if (this.n == 3) {
                layoutParams2.setMarginStart(hcn.a(this.j, (r2 * 73) + 6));
            } else {
                layoutParams2.setMarginStart(hcn.a(this.j, ((r2 + 1) * 73) + 6));
            }
            this.i.setLayoutParams(layoutParams2);
            this.i.setText(this.j.getResources().getString(R.string._2130839946_res_0x7f02098a, Integer.valueOf(this.n), 3));
        }
        if (arrayList.size() > 0) {
            this.w.addMarker(this.o, InterfaceUpdateReTrack.MarkerType.Image_type);
            this.c = this.w.addCurMarker(this.o, InterfaceUpdateReTrack.MarkerType.Image_type);
            this.y.put(Integer.valueOf(this.o), arrayList);
        } else {
            this.y.remove(Integer.valueOf(this.o));
            this.w.clearMarker(this.o);
            this.w.clearCurMarker(this.c);
            if (this.u.containsKey(Integer.valueOf(this.o))) {
                LogUtil.d("AlbumDialog", "[changeCurrentPhoto] mIndexOfTexts contains key currentPointIndex.");
                this.w.addMarker(this.o, InterfaceUpdateReTrack.MarkerType.Text_type);
                this.c = this.w.addCurMarker(this.o, InterfaceUpdateReTrack.MarkerType.Text_type);
            }
        }
        d(arrayList);
        c((List<PhotoModel>) arrayList);
        ak();
        this.ap.setIndexOfPhoto(this.y, this.o);
        this.w.setPhotosNumber(this.as);
        LogUtil.d("AlbumDialog", "[changeCurrentPhoto] end mSumPhotosNumber:", String.valueOf(this.as));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ak() {
        if (this.y.containsKey(Integer.valueOf(this.o)) || this.u.containsKey(Integer.valueOf(this.o)) || this.v.containsKey(Integer.valueOf(this.o))) {
            this.h.setVisibility(0);
        } else {
            this.h.setVisibility(8);
        }
    }

    public void d(boolean z) {
        this.z = z;
    }

    public boolean c() {
        return this.q.getVisibility() == 0;
    }

    public boolean b() {
        return this.z;
    }

    private void aZC_(View view) {
        view.requestFocus();
        if (this.j.getSystemService("input_method") instanceof InputMethodManager) {
            ((InputMethodManager) this.j.getSystemService("input_method")).showSoftInput(view, 0);
            b(true);
        }
    }

    private void aZy_(View view) {
        if (this.j.getSystemService("input_method") instanceof InputMethodManager) {
            ((InputMethodManager) this.j.getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
            b(true);
        }
    }

    public void d() {
        AlbumEditText albumEditText = this.l;
        if (albumEditText != null) {
            aZy_(albumEditText);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aj() {
        this.q.setVisibility(0);
        b(false);
    }

    public void e() {
        this.q.setVisibility(8);
        b(true);
    }

    public boolean a() {
        return (this.u.size() == 0 && this.e.equals(this.y) && this.v.size() == 0) ? false : true;
    }

    public void b(Map<Integer, ArrayList<PhotoModel>> map, Map<PhotoModel, Bitmap> map2, HashMap<Integer, List<Bitmap>> hashMap) {
        if (map == null || map2 == null || hashMap == null) {
            LogUtil.d("AlbumDialog", "No self-matching photos");
            return;
        }
        this.y = map;
        int i = 0;
        for (Map.Entry<Integer, ArrayList<PhotoModel>> entry : map.entrySet()) {
            if (hbz.e(entry.getValue(), PhotoModel.class)) {
                this.y.put(entry.getKey(), entry.getValue());
                i += entry.getValue().size();
                SlideBar slideBar = this.ap;
                if (slideBar != null) {
                    slideBar.setAutoMatchPhoto(entry.getKey().intValue(), this.y);
                }
            }
        }
        this.e.putAll(map);
        for (Map.Entry<Integer, List<Bitmap>> entry2 : hashMap.entrySet()) {
            this.s.put(entry2.getKey(), entry2.getValue());
        }
        for (Map.Entry<PhotoModel, Bitmap> entry3 : map2.entrySet()) {
            this.ad.put(entry3.getKey(), entry3.getValue());
        }
        SlideBar slideBar2 = this.ap;
        if (slideBar2 != null) {
            slideBar2.postInvalidate();
        }
        this.as = i;
        this.ae.setSumPhotosNumber(i);
        this.ae.updateTrackAnimationControl();
    }

    public void c(int i) {
        SlideBar slideBar = this.ap;
        if (slideBar != null) {
            slideBar.a(i);
        }
    }

    public void e(boolean z) {
        this.x = z;
    }

    private void al() {
        if (this.f == null) {
            CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.j).b(R.string._2130847207_res_0x7f0225e7).d(R.string._2130847201_res_0x7f0225e1).cyR_(R.string._2130839728_res_0x7f0208b0, new View.OnClickListener() { // from class: hcd.7
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.d("AlbumDialog", "showShareTipsDialog cancel");
                    hcd.this.au.setChecked(true);
                    hcd.this.f.dismiss();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).cyU_(R.string._2130839948_res_0x7f02098c, new View.OnClickListener() { // from class: hcd.9
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.d("AlbumDialog", "showShareTipsDialog ok");
                    hcd.this.f.dismiss();
                    ((SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class)).setIsShowUserInfo(false);
                    hcd.this.ae();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).a();
            this.f = a2;
            a2.setCancelable(false);
        }
        this.f.show();
    }
}
