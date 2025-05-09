package com.huawei.ui.homehealth.runcard.trackfragments;

import android.animation.LayoutTransition;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.loader.app.LoaderManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.facardagds.FaCardAgdsApi;
import com.huawei.health.knit.AdvancedSportFragment;
import com.huawei.health.knit.ui.KnitFragment;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.InputBoxTemplate;
import com.huawei.health.marketing.datatype.MarketingOption;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.health.sport.utils.SportSupportUtil;
import com.huawei.health.suggestion.ui.fitness.viewholder.FitnessSessionManager;
import com.huawei.health.userlabelmgr.api.UserLabelServiceApi;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.health.userprofilemgr.model.BaseResponseCallback;
import com.huawei.healthcloud.plugintrack.bolt.BoltCustomDialog;
import com.huawei.healthcloud.plugintrack.sportmusic.SportMusicInteratorService;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.LoginResultCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.ble.BleConstants;
import com.huawei.pluginachievement.manager.model.MedalInfoDesc;
import com.huawei.pluginachievement.manager.model.NewMedalTabDataBean;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.floatview.DragFloatActionButton;
import com.huawei.ui.commonui.flowlayout.HealthFlowLayout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.popupview.PopViewList;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.scrollview.ScrollViewListener;
import com.huawei.ui.commonui.searchview.HealthSearchView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.commonui.subtab.HealthSubTabPagerAdapter;
import com.huawei.ui.commonui.subtab.HealthSubTabWidget;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.commonui.view.RedPointImageView;
import com.huawei.ui.commonui.viewpager.HealthNativeViewPager;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import com.huawei.ui.homehealth.qrcode.activity.QrCodeScanningActivity;
import com.huawei.ui.homehealth.runcard.SportAssistSettingsActivity;
import com.huawei.ui.homehealth.runcard.trackfragments.SportEntranceFragment;
import com.huawei.ui.homehealth.runcard.trackfragments.viewmodel.SportViewModel;
import com.huawei.ui.homewear21.aw70.Aw70ModeSelectActivity;
import com.huawei.ui.main.stories.fitness.activity.coresleep.utils.BaseBootPagerHelper;
import com.huawei.ui.main.stories.fitness.activity.coresleep.utils.RunningMusicBootPagerHelper;
import com.huawei.up.model.UserInfomation;
import defpackage.ash;
import defpackage.dlf;
import defpackage.dpf;
import defpackage.dum;
import defpackage.eil;
import defpackage.fbh;
import defpackage.ggs;
import defpackage.gho;
import defpackage.gnm;
import defpackage.gso;
import defpackage.gtv;
import defpackage.guz;
import defpackage.gwb;
import defpackage.hpg;
import defpackage.ixx;
import defpackage.jdx;
import defpackage.jpu;
import defpackage.koq;
import defpackage.mjt;
import defpackage.mwx;
import defpackage.nmh;
import defpackage.nmk;
import defpackage.nqu;
import defpackage.nrf;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.ntd;
import defpackage.obb;
import defpackage.opi;
import defpackage.orx;
import defpackage.ory;
import defpackage.owp;
import defpackage.owr;
import defpackage.pqe;
import defpackage.pqm;
import defpackage.rdn;
import defpackage.smy;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.DfxMonitorCenter;
import health.compact.a.EnvironmentInfo;
import health.compact.a.HEXUtils;
import health.compact.a.LanguageUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.io.File;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class SportEntranceFragment extends BaseFragment {
    private static boolean b = true;
    private orx aa;
    private HealthSubHeader ad;
    private boolean ah;
    private boolean aq;
    private KnitFragment as;
    private KnitFragment at;
    private View au;
    private KnitFragment av;
    private KnitFragment aw;
    private KnitFragment ax;
    private HealthFlowLayout ay;
    private HealthTextView ba;
    private RedPointImageView bb;
    private LinearLayout bc;
    private Activity bd;
    private KnitFragment bj;
    private nqu bk;
    private View bl;
    private KnitFragment bm;
    private ImageView bn;
    private HealthSearchView bo;
    private String bq;
    private int br;
    private String bs;
    private String bu;
    private Map<String, ArrayList<MedalInfoDesc>> bv;
    private LinearLayout bx;
    private LinearLayout bz;
    private SportViewModel ca;
    private HealthSubTabWidget cc;
    private HealthSubTabPagerAdapter cd;
    private HealthNativeViewPager ce;
    private View cg;
    private View ch;
    private View cj;
    private ViewStub ck;
    private ViewStub cl;
    private UserLabelServiceApi cm;
    private KnitFragment cn;
    private ViewGroup co;
    private RelativeLayout cp;
    private String cq;
    private KnitFragment cu;
    private BroadcastReceiver d;
    private ViewStub f;
    private String i;
    private ImageView k;
    private HealthTextView l;
    private BaseBootPagerHelper m;
    private HealthColumnSystem p;
    private HealthTextView q;
    private HealthTextView s;
    private LinearLayout t;
    private pqe u;
    private Context v;
    private DragFloatActionButton w;
    private InputBoxTemplate y;
    private boolean c = false;
    private int h = 1;
    private int r = 0;
    private String e = "";
    private c ab = new c(Looper.getMainLooper(), this);
    private int cb = 500001;
    private boolean am = true;
    private boolean ae = false;
    private boolean ai = false;
    private int bw = -1;
    private ArrayList<nqu> x = new ArrayList<>();
    private List<nqu> n = new ArrayList();
    private boolean aj = false;
    private boolean ao = false;
    private boolean ar = false;
    private boolean ac = false;
    private String az = Integer.toString(20002);
    private StorageParams cf = new StorageParams();
    private boolean ak = true;
    private boolean an = true;
    private boolean al = true;

    /* renamed from: a, reason: collision with root package name */
    private boolean f9563a = false;
    private int o = 0;
    private boolean af = false;
    private SparseBooleanArray bp = new SparseBooleanArray();
    private PermissionsResultAction be = null;
    private boolean ap = false;
    private Set<Integer> bt = new HashSet();
    private Map<Integer, InputBoxTemplate> ag = new HashMap();
    private SparseArray<Integer> by = new SparseArray<>();
    private ArrayList<String> j = new ArrayList<>(16);
    private UiCallback z = new UiCallback<String>() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportEntranceFragment.1
        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onSuccess(String str) {
            if (!TextUtils.isEmpty(str)) {
                String b2 = ash.b("coachGenderConfig");
                if (!TextUtils.isEmpty(b2)) {
                    if (CommonUtil.h(str) != CommonUtil.h(b2)) {
                        SportEntranceFragment.this.m();
                    }
                }
                LogUtil.c("Track_SportEntranceFragment", "coach UiCallback onSuccess");
                ash.a("coachGenderConfig", str);
                return;
            }
            LogUtil.a("Track_SportEntranceFragment", "coach UiCallback some error");
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            SportEntranceFragment.this.l();
        }
    };
    private final BroadcastReceiver bg = new BroadcastReceiver() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportEntranceFragment.13
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction()) || "com.huawei.bone.action.DEVICE_THIRD_DELETE".equals(intent.getAction())) {
                LogUtil.c("Track_SportEntranceFragment", "ACTION_CONNECTION_STATE_CHANGED or ACTION_DEVICE_THIRD_DELETE");
                if (SportEntranceFragment.this.ab != null) {
                    SportEntranceFragment.this.ab.postDelayed(new Runnable() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportEntranceFragment.13.5
                        @Override // java.lang.Runnable
                        public void run() {
                            SportEntranceFragment.this.ay();
                        }
                    }, 2000L);
                } else {
                    LogUtil.a("Track_SportEntranceFragment", "onReceive: handler is null");
                }
            }
        }
    };
    private Observer<ArrayList<nqu>> ci = new Observer<ArrayList<nqu>>() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportEntranceFragment.21
        @Override // androidx.lifecycle.Observer
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onChanged(ArrayList<nqu> arrayList) {
            SportEntranceFragment.this.c(arrayList);
        }
    };
    private ViewPager.OnPageChangeListener bh = new ViewPager.OnPageChangeListener() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportEntranceFragment.22
        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            ReleaseLogUtil.b("Track_SportEntranceFragment", "sport page been selected, position: ", Integer.valueOf(i));
            HashMap hashMap = new HashMap(10);
            SportEntranceFragment.this.e("onPageSelected");
            SportEntranceFragment sportEntranceFragment = SportEntranceFragment.this;
            sportEntranceFragment.b(sportEntranceFragment.x, i, hashMap);
            if (koq.d(SportEntranceFragment.this.x, i) && ((nqu) SportEntranceFragment.this.x.get(i)).c() != SportEntranceFragment.this.bw) {
                SportEntranceFragment.this.e();
                SportEntranceFragment sportEntranceFragment2 = SportEntranceFragment.this;
                sportEntranceFragment2.bw = ((nqu) sportEntranceFragment2.x.get(i)).c();
            }
            SportEntranceFragment.this.bd();
            SportEntranceFragment.this.e(hashMap);
            if (SportEntranceFragment.this.cb != 10001 || !SportEntranceFragment.this.ao) {
                pqm.e(SportEntranceFragment.this.u, 8);
            } else {
                pqm.e(SportEntranceFragment.this.u, 0);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
            if (i == 0) {
                LogUtil.c("Track_SportEntranceFragment", "ViewPager Scroll state idle");
            } else {
                if (i != 1) {
                    return;
                }
                LogUtil.c("Track_SportEntranceFragment", "ViewPager Scroll state dragging");
            }
        }
    };
    private final com.huawei.haf.design.pattern.Observer bf = new com.huawei.haf.design.pattern.Observer() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportEntranceFragment.28
        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            if (SportEntranceFragment.this.aq && "PLAN_FRAGMENT_SCROLL".equals(str) && objArr != null && objArr.length >= 4 && (objArr[0] instanceof ViewGroup) && (objArr[1] instanceof ViewGroup) && (objArr[2] instanceof RecyclerView)) {
                Object obj = objArr[3];
                if (obj instanceof Integer) {
                    int intValue = ((Integer) obj).intValue();
                    if (intValue <= 0 || !SportEntranceFragment.this.ap) {
                        if (intValue >= 0 || SportEntranceFragment.this.ap) {
                            ViewGroup viewGroup = (ViewGroup) objArr[0];
                            ViewGroup viewGroup2 = (ViewGroup) objArr[1];
                            RecyclerView recyclerView = (RecyclerView) objArr[2];
                            int computeVerticalScrollOffset = recyclerView.computeVerticalScrollOffset();
                            SportEntranceFragment.this.ao();
                            if (SportEntranceFragment.this.br > 0 && computeVerticalScrollOffset >= SportEntranceFragment.this.br && !SportEntranceFragment.this.ap && dpf.a(computeVerticalScrollOffset, recyclerView.getHeight(), dpf.b(recyclerView))) {
                                SportEntranceFragment.this.w();
                            }
                            if (computeVerticalScrollOffset > 0 || !SportEntranceFragment.this.ap) {
                                return;
                            }
                            dpf.Ys_(SportEntranceFragment.this.bl);
                            ViewGroup[] viewGroupArr = {SportEntranceFragment.this.co, viewGroup, viewGroup2};
                            LayoutTransition[] Yr_ = dpf.Yr_(viewGroupArr);
                            dpf.e(SportEntranceFragment.this.bo);
                            dpf.Yt_(SportEntranceFragment.this.bn, 8);
                            dpf.Yp_(SportEntranceFragment.this.ab, viewGroupArr, Yr_);
                            SportEntranceFragment.this.ap = false;
                        }
                    }
                }
            }
        }
    };
    private final Observer<nqu> g = new Observer() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportEntranceFragment$$ExternalSyntheticLambda3
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            SportEntranceFragment.this.e((nqu) obj);
        }
    };
    private final Observer<nqu> bi = new Observer() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportEntranceFragment$$ExternalSyntheticLambda4
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            SportEntranceFragment.this.d((nqu) obj);
        }
    };

    /* loaded from: classes.dex */
    public interface SportEntranceCallback {
        void onSportEntranceCallback();
    }

    public boolean e(int i) {
        return i == 300 || i == 400 || i == 100;
    }

    public void e() {
        if (SportSupportUtil.f(this.cb)) {
            int intValue = this.by.get(this.cb, -1).intValue();
            if (koq.b(this.x, intValue)) {
                LogUtil.e("Track_SportEntranceFragment", "onSelfPageSelected failed, cause mSportType = ", Integer.valueOf(this.cb), " not found in tabs!");
                return;
            }
            LogUtil.c("Track_SportEntranceFragment", "onSelfPageSelected mSportType = ", Integer.valueOf(this.cb), ", pos = ", Integer.valueOf(intValue));
            Fragment a2 = this.x.get(intValue).a();
            KnitFragment knitFragment = a2 instanceof KnitFragment ? (KnitFragment) a2 : null;
            if (knitFragment != null) {
                knitFragment.onSelfShow();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final ArrayList<nqu> arrayList) {
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        final List<nqu> d2 = d(arrayList);
        if (koq.b(d2)) {
            g(arrayList);
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: ora
                @Override // java.lang.Runnable
                public final void run() {
                    SportEntranceFragment.this.a(d2, arrayList, elapsedRealtime);
                }
            });
        }
    }

    public /* synthetic */ void a(List list, ArrayList arrayList, long j) {
        CountDownLatch countDownLatch = new CountDownLatch(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            try {
                nqu nquVar = (nqu) it.next();
                String f = nquVar.f();
                String h = nquVar.h();
                nquVar.cGk_(nrf.cIb_(BaseApplication.getContext(), f));
                nquVar.cGl_(nrf.cIb_(BaseApplication.getContext(), h));
                countDownLatch.countDown();
            } catch (InterruptedException unused) {
                LogUtil.e("Track_SportEntranceFragment", "load tab img async is interrupted!");
            } finally {
                LogUtil.e("Track_SportEntranceFragment", "load tab img async failed, time is out!");
            }
        }
        if (!countDownLatch.await(3000L, TimeUnit.MILLISECONDS)) {
        }
        g((ArrayList<nqu>) arrayList);
        ReleaseLogUtil.b("Track_SportEntranceFragment", "loadTabImgAsync finished, time cost: " + (SystemClock.elapsedRealtime() - j));
    }

    private void g(final ArrayList<nqu> arrayList) {
        c cVar = this.ab;
        if (cVar == null) {
            LogUtil.e("Track_SportEntranceFragment", "SportEntranceFragment has been destroyed!");
        } else {
            cVar.post(new Runnable() { // from class: oqy
                @Override // java.lang.Runnable
                public final void run() {
                    SportEntranceFragment.this.a(arrayList);
                }
            });
        }
    }

    public /* synthetic */ void a(ArrayList arrayList) {
        b((ArrayList<nqu>) arrayList);
    }

    private List<nqu> d(ArrayList<nqu> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        if (koq.b(arrayList)) {
            ReleaseLogUtil.a("Track_SportEntranceFragment", "extractImgSubTabPagerBeans fragmentSubTabPagerBeans isEmpty");
            return arrayList2;
        }
        Iterator<nqu> it = arrayList.iterator();
        while (it.hasNext()) {
            nqu next = it.next();
            String f = next.f();
            String h = next.h();
            if (!TextUtils.isEmpty(f) || !TextUtils.isEmpty(h)) {
                arrayList2.add(next);
            }
        }
        return arrayList2;
    }

    private void b(ArrayList<nqu> arrayList) {
        int i;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Object[] objArr = new Object[2];
        objArr[0] = "onChanged fragmentSubTabPagerBeans size: ";
        objArr[1] = Integer.valueOf(arrayList == null ? -1 : arrayList.size());
        LogUtil.c("Track_SportEntranceFragment", objArr);
        if (this.cd == null) {
            LogUtil.a("Track_SportEntranceFragment", "mSubTabAdapter is null");
            return;
        }
        if (h(arrayList)) {
            LogUtil.c("Track_SportEntranceFragment", "restoreAllFragments success");
            return;
        }
        this.bk = null;
        boolean b2 = koq.b(this.x);
        a((List<nqu>) arrayList);
        bo();
        if (this.al) {
            Integer num = this.by.get(this.cb);
            if (num == null) {
                LogUtil.a("Track_SportEntranceFragment", "no index for mSportType = ", Integer.valueOf(this.cb));
                i = 0;
            } else {
                i = num.intValue();
            }
            this.cd.a(this.x, i);
        }
        ad();
        if (!this.f9563a) {
            aq();
        }
        e(this.x, b2);
        bb();
        this.al = false;
        ReleaseLogUtil.b("Track_SportEntranceFragment", "handleTabObserver finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    private void aq() {
        this.f9563a = true;
        this.ab.postDelayed(new Runnable() { // from class: orb
            @Override // java.lang.Runnable
            public final void run() {
                SportEntranceFragment.this.c();
            }
        }, 100L);
    }

    public /* synthetic */ void c() {
        HealthNativeViewPager healthNativeViewPager = this.ce;
        if (healthNativeViewPager == null) {
            return;
        }
        int offscreenPageLimit = healthNativeViewPager.getOffscreenPageLimit();
        if (offscreenPageLimit >= this.x.size() - 1) {
            this.ce.setOffscreenPageLimit(this.x.size() - 1);
            this.f9563a = false;
            ReleaseLogUtil.b("Track_SportEntranceFragment", "postSetOffscreenPageLimit end, offscreenPageLimit :" + this.ce.getOffscreenPageLimit());
            return;
        }
        this.ce.setOffscreenPageLimit(offscreenPageLimit + 1);
        aq();
    }

    private HashSet<Integer> c(List<nqu> list) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (nqu nquVar : list) {
            if (nquVar != null) {
                hashSet.add(Integer.valueOf(nquVar.c()));
            }
        }
        return hashSet;
    }

    public SportEntranceFragment() {
        FitnessSessionManager.e().a(opi.d().b());
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onCreate(bundle);
        SportViewModel sportViewModel = (SportViewModel) new ViewModelProvider(this).get(SportViewModel.class);
        this.ca = sportViewModel;
        sportViewModel.a(this, this.g);
        this.ca.d(this, this.bi);
        this.ca.c(getContext());
        this.ca.a();
        orx c2 = orx.c();
        this.aa = c2;
        c2.b();
        UserLabelServiceApi userLabelServiceApi = (UserLabelServiceApi) Services.c("HWUserLabelMgr", UserLabelServiceApi.class);
        this.cm = userLabelServiceApi;
        userLabelServiceApi.registerCallback(2);
        gtv.e();
        if (dum.d() != null) {
            ReleaseLogUtil.b("Track_SportEntranceFragment", "getInstance of Mediator success");
        }
        ReleaseLogUtil.b("Track_SportEntranceFragment", "onCreate finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    private void bb() {
        nqu nquVar;
        if (koq.c(this.x) && (nquVar = this.x.get(0)) != null && this.cb == nquVar.c()) {
            HashMap hashMap = new HashMap(2);
            hashMap.put(BleConstants.SPORT_TYPE, Integer.valueOf(this.cb));
            e(hashMap);
        }
    }

    public void g() {
        LogUtil.c("Track_SportEntranceFragment", "updateMap");
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (layoutInflater == null) {
            LogUtil.c("Track_SportEntranceFragment", "onCreateView return, inflater is null");
            return null;
        }
        this.v = getContext();
        this.bd = getActivity();
        if (CommonUtil.ce()) {
            av();
        }
        ap();
        HealthColumnSystem healthColumnSystem = new HealthColumnSystem(this.v, 1);
        this.p = healthColumnSystem;
        this.o = healthColumnSystem.f();
        this.bl = layoutInflater.inflate(R.layout.layout_frag_track_entrance_sport, viewGroup, false);
        gso.e().init(BaseApplication.getContext());
        if (this.am && this.ac) {
            y();
        }
        this.ca.i();
        ReleaseLogUtil.b("Track_SportEntranceFragment", "onCreateView finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
        return this.bl;
    }

    private void y() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        k();
        if (this.ab == null) {
            this.ab = new c(Looper.getMainLooper(), this);
        }
        ab();
        dgh_(this.bl);
        if (!Utils.f()) {
            new owr().e(this.bd);
        }
        this.am = false;
        this.ar = false;
        ReleaseLogUtil.b("Track_SportEntranceFragment", "initAll finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    private void av() {
        LogUtil.c("Track_SportEntranceFragment", "enter registerNonLocalBroadcastReceiver");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("com.huawei.bone.action.DEVICE_THIRD_DELETE");
        BroadcastManagerUtil.bFC_(BaseApplication.getContext(), this.bg, intentFilter, LocalBroadcast.c, null);
    }

    private void bj() {
        try {
            BaseApplication.getContext().unregisterReceiver(this.bg);
        } catch (IllegalArgumentException e2) {
            LogUtil.e("Track_SportEntranceFragment", "unRegisterNonLocalBroadcastReceiver Exception: ", e2.getMessage());
        }
    }

    static class a extends BroadcastReceiver {
        private WeakReference<SportEntranceFragment> c;

        a(SportEntranceFragment sportEntranceFragment) {
            this.c = new WeakReference<>(sportEntranceFragment);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.c("Track_SportEntranceFragment", "receive account broadcast");
            if (context == null || intent == null) {
                ReleaseLogUtil.a("Track_SportEntranceFragment", "AccountBroadcastReceiver context == null || intent == null");
                return;
            }
            SportEntranceFragment sportEntranceFragment = this.c.get();
            if (sportEntranceFragment == null) {
                LogUtil.a("Track_SportEntranceFragment", "sportEntranceFragment null");
            } else if ("com.huawei.plugin.account.logout".equals(intent.getAction())) {
                ReleaseLogUtil.b("Track_SportEntranceFragment", "AccountBroadcastReceiver receive account broadcast");
                if (sportEntranceFragment.ab != null) {
                    sportEntranceFragment.ab.sendEmptyMessage(100);
                }
                sportEntranceFragment.bi();
            }
        }
    }

    private void ap() {
        if (LoginInit.getInstance(BaseApplication.getContext()).isKidAccount()) {
            this.d = new a(this);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.huawei.plugin.account.logout");
            LocalBroadcastManager.getInstance(BaseApplication.getContext()).registerReceiver(this.d, intentFilter);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bi() {
        if (this.d != null) {
            LocalBroadcastManager.getInstance(BaseApplication.getContext()).unregisterReceiver(this.d);
            this.d = null;
        }
    }

    private void bm() {
        boolean m = LanguageUtil.m(this.v);
        boolean o = Utils.o();
        LogUtil.c("Track_SportEntranceFragment", "Utils.isOversea()", Boolean.valueOf(o), "  isChineseSimplifiedLocal", Boolean.valueOf(m));
        if (o || !m) {
            return;
        }
        String b2 = SharedPreferenceManager.b(this.v, Integer.toString(10005), "health_time_first_in");
        if (TextUtils.isEmpty(b2)) {
            return;
        }
        boolean d2 = owp.d(b2);
        boolean k = owp.k(this.v);
        LogUtil.c("Track_SportEntranceFragment", "showPlanTip = ", Boolean.valueOf(k), "  isDiffDate", Boolean.valueOf(d2));
        if (k || !d2) {
            return;
        }
        View inflate = View.inflate(this.v, R.layout.sport_entrance_tips, null);
        inflate.measure(0, 0);
        PopupWindow popupWindow = new PopupWindow(inflate, -2, -2, true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        Context context = this.v;
        if (!(context instanceof Activity)) {
            LogUtil.c("Track_SportEntranceFragment", "showPlanTip mContext is not instanceof Activity");
        } else {
            if (((Activity) context).isDestroyed() || ((Activity) this.v).isFinishing()) {
                return;
            }
            LogUtil.c("Track_SportEntranceFragment", "PopupWindow = show");
            popupWindow.showAtLocation(this.bl, 0, nsn.c(this.v, 50.0f), nsn.c(this.v, 66.0f));
            owp.a(this.v, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final nqu nquVar) {
        c cVar = this.ab;
        if (cVar == null) {
            return;
        }
        cVar.post(new Runnable() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportEntranceFragment.27
            @Override // java.lang.Runnable
            public void run() {
                if (nquVar != null && SportEntranceFragment.this.cd != null) {
                    if (SportEntranceFragment.this.by.get(nquVar.c()) == null) {
                        SportEntranceFragment.this.x.add(nquVar);
                        SportEntranceFragment.this.bo();
                        boolean z = SportEntranceFragment.this.cb == nquVar.c();
                        String j = nquVar.j();
                        if (SportEntranceFragment.this.cc != null) {
                            SportEntranceFragment.this.cd.b(SportEntranceFragment.this.cc.c(j), nquVar, z);
                        }
                        LogUtil.c("Track_SportEntranceFragment", "addTab");
                        return;
                    }
                    LogUtil.c("Track_SportEntranceFragment", "already add current bean");
                    return;
                }
                LogUtil.c("Track_SportEntranceFragment", "no need to addTab");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final nqu nquVar) {
        c cVar = this.ab;
        if (cVar == null) {
            return;
        }
        cVar.post(new Runnable() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportEntranceFragment.30
            @Override // java.lang.Runnable
            public void run() {
                if (nquVar == null || SportEntranceFragment.this.cd == null) {
                    LogUtil.c("Track_SportEntranceFragment", "no need to removeTab");
                    return;
                }
                int c2 = nquVar.c();
                if (SportEntranceFragment.this.by.get(c2) != null) {
                    for (int i = 0; i < SportEntranceFragment.this.x.size(); i++) {
                        nqu nquVar2 = (nqu) SportEntranceFragment.this.x.get(i);
                        if (nquVar2 != null && c2 == nquVar2.c()) {
                            SportEntranceFragment.this.x.remove(i);
                            SportEntranceFragment.this.bo();
                            SportEntranceFragment.this.cd.b(c2);
                            LogUtil.c("Track_SportEntranceFragment", "removeTab: ", Integer.valueOf(c2));
                            return;
                        }
                    }
                    return;
                }
                LogUtil.c("Track_SportEntranceFragment", "already remove current bean");
            }
        });
    }

    private void ab() {
        e("initData");
        int f = owp.f(this.v);
        this.cb = f;
        if (f == 0) {
            this.cb = Utils.o() ? 258 : 500001;
        }
    }

    private void dgh_(View view) {
        if (view == null) {
            LogUtil.a("Track_SportEntranceFragment", "initView mRootView null");
            return;
        }
        LogUtil.c("Track_SportEntranceFragment", "initView");
        View findViewById = view.findViewById(R.id.more_and_red_point);
        if (this.u == null) {
            this.u = pqm.dsg_(getActivity(), findViewById, 0);
        }
        this.p.e(getContext());
        this.o = this.p.f();
        this.cg = view.findViewById(R.id.statusbar_panel_view);
        this.bc = (LinearLayout) view.findViewById(R.id.more_sport_window);
        this.ad = (HealthSubHeader) view.findViewById(R.id.all_sport_type);
        this.k = (ImageView) view.findViewById(R.id.more_sport_close);
        this.ay = (HealthFlowLayout) view.findViewById(R.id.more_sport_flow_layout);
        this.ba = (HealthTextView) view.findViewById(R.id.more_sport_tips);
        this.ad.setSubHeaderBackgroundColor(this.v.getResources().getColor(R.color._2131296690_res_0x7f0901b2));
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.sport_help_layout);
        this.bx = linearLayout;
        linearLayout.setVisibility(8);
        HealthSearchView healthSearchView = (HealthSearchView) this.bl.findViewById(R.id.sport_global_search_view);
        this.bo = healthSearchView;
        nsn.cLD_(healthSearchView);
        this.bn = (ImageView) this.bl.findViewById(R.id.sport_search_icon);
        dgg_(view);
        dgf_(view);
        bf();
        dgd_(view);
        this.co = (ViewGroup) view.findViewById(R.id.sport_viewPager_container);
        HealthNativeViewPager healthNativeViewPager = (HealthNativeViewPager) view.findViewById(R.id.viewPager_sport);
        this.ce = healthNativeViewPager;
        healthNativeViewPager.clearOnPageChangeListeners();
        this.ce.addOnPageChangeListener(this.bh);
        HealthSubTabPagerAdapter healthSubTabPagerAdapter = this.cd;
        if (healthSubTabPagerAdapter != null) {
            healthSubTabPagerAdapter.a();
        }
        if (this.cc != null) {
            this.cd = new HealthSubTabPagerAdapter(getChildFragmentManager(), this.ce, this.cc);
        }
        al();
        if (this.cb == 264) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportEntranceFragment.29
                @Override // java.lang.Runnable
                public void run() {
                    owp.j(SportEntranceFragment.this.v, SportEntranceFragment.this.cb);
                }
            });
        }
        if (LanguageUtil.bc(getContext())) {
            this.ce.setRotationY(180.0f);
        }
        this.ca.c();
        this.ca.b(this, this.ci);
        this.ca.j();
        c(this.ca.b());
        if (dpf.e()) {
            ai();
        } else {
            this.aq = false;
            nsy.cMA_(this.bo, 8);
        }
    }

    public void dgj_(LinearLayout linearLayout) {
        this.t = linearLayout;
    }

    private void ai() {
        if (this.bo == null) {
            return;
        }
        aw();
        ah();
        this.bo.setInputType(0);
        this.bo.setIconifiedByDefault(false);
        this.aq = true;
        dpf.c(this.bo);
        dpf.Yv_(this.bo, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportEntranceFragment.26
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                InputBoxTemplate p = SportEntranceFragment.this.p();
                fbh.d(SportEntranceFragment.this.v, SportEntranceFragment.this.t(), p);
                dpf.a(SportEntranceFragment.this.v, p);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        dpf.Yn_(this.ab, 4036);
        bd();
        this.bo.setSearchBarContentDescription();
    }

    private void ah() {
        ImageView imageView = this.bn;
        if (imageView == null) {
            return;
        }
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportEntranceFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                InputBoxTemplate p = SportEntranceFragment.this.p();
                fbh.d(SportEntranceFragment.this.v, SportEntranceFragment.this.t(), p);
                dpf.a(SportEntranceFragment.this.v, p);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private ScrollViewListener d(KnitFragment knitFragment) {
        return new d(this, knitFragment);
    }

    private void aw() {
        if (this.an) {
            try {
                ObserverManagerUtil.d(this.bf, "PLAN_FRAGMENT_SCROLL");
            } catch (IllegalStateException e2) {
                LogUtil.e("Track_SportEntranceFragment", "register plan fragment observer exception: ", e2);
            }
            this.an = false;
        }
    }

    private void bn() {
        try {
            ObserverManagerUtil.e(this.bf, "PLAN_FRAGMENT_SCROLL");
        } catch (IllegalStateException e2) {
            LogUtil.e("Track_SportEntranceFragment", "unregister plan fragment observer exception: ", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ao() {
        HealthSearchView healthSearchView;
        if (this.br > 0 || (healthSearchView = this.bo) == null) {
            return;
        }
        this.br = healthSearchView.getHeight();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        dpf.Ys_(this.bl);
        dpf.Yt_(this.bn, 0);
        dpf.d(this.bo);
        this.ap = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bd() {
        if (this.bo == null) {
            return;
        }
        int i = this.cb;
        int t = t();
        if (this.ag.get(Integer.valueOf(i)) != null) {
            dpf.b(this.bo, this.ag.get(Integer.valueOf(i)));
            if (!this.ao || this.bt.contains(Integer.valueOf(i))) {
                return;
            }
            this.bt.add(Integer.valueOf(i));
            fbh.e(this.v, t, this.ag.get(Integer.valueOf(i)));
            return;
        }
        MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi == null) {
            LogUtil.c("Track_SportEntranceFragment", "api is null");
            return;
        }
        Task<Map<Integer, ResourceResultInfo>> resourceResultInfo = marketingApi.getResourceResultInfo(t);
        resourceResultInfo.addOnSuccessListener(new b(this, marketingApi));
        resourceResultInfo.addOnFailureListener(new OnFailureListener() { // from class: org
            @Override // com.huawei.hmf.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                LogUtil.c("Track_SportEntranceFragment", "requestMarketResource onFailure");
            }
        });
    }

    static class b implements OnSuccessListener<Map<Integer, ResourceResultInfo>> {
        private final MarketingApi b;
        private final WeakReference<SportEntranceFragment> e;

        b(SportEntranceFragment sportEntranceFragment, MarketingApi marketingApi) {
            this.e = new WeakReference<>(sportEntranceFragment);
            this.b = marketingApi;
        }

        @Override // com.huawei.hmf.tasks.OnSuccessListener
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onSuccess(Map<Integer, ResourceResultInfo> map) {
            SportEntranceFragment sportEntranceFragment = this.e.get();
            if (sportEntranceFragment == null) {
                LogUtil.c("Track_SportEntranceFragment", "fragment is null");
                return;
            }
            LogUtil.c("Track_SportEntranceFragment", "requestMarketResource onSuccess");
            int t = sportEntranceFragment.t();
            MarketingApi marketingApi = this.b;
            if (marketingApi == null) {
                LogUtil.c("Track_SportEntranceFragment", "mApi is null");
                return;
            }
            InputBoxTemplate d = dpf.d(marketingApi, t, map);
            if (d != null) {
                sportEntranceFragment.ag.put(Integer.valueOf(sportEntranceFragment.cb), d);
                dpf.b(sportEntranceFragment.bo, d);
                if (!sportEntranceFragment.ao || sportEntranceFragment.bt.contains(Integer.valueOf(sportEntranceFragment.cb))) {
                    return;
                }
                sportEntranceFragment.bt.add(Integer.valueOf(sportEntranceFragment.cb));
                fbh.e(sportEntranceFragment.v, t, d);
                return;
            }
            LogUtil.c("Track_SportEntranceFragment", "template is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public InputBoxTemplate p() {
        InputBoxTemplate inputBoxTemplate = this.ag.get(Integer.valueOf(this.cb));
        return inputBoxTemplate != null ? inputBoxTemplate : this.y;
    }

    private void dgd_(View view) {
        if (this.f == null) {
            this.f = (ViewStub) view.findViewById(R.id.viewstub_aw70_connect_tips);
        }
        if (this.f.getParent() != null) {
            this.au = this.f.inflate();
        } else {
            this.au = view.findViewById(R.id.rv_aw70_connect_tips);
        }
        View view2 = this.au;
        if (view2 == null) {
            return;
        }
        BaseActivity.setViewSafeRegion(true, view2);
        this.l = (HealthTextView) this.au.findViewById(R.id.tv_show_tips_close);
        this.q = (HealthTextView) this.au.findViewById(R.id.tv_show_tips_go);
        this.s = (HealthTextView) this.au.findViewById(R.id.tv_connect_tips);
        bk();
    }

    private void dgg_(View view) {
        this.be = new CustomPermissionAction(this.v) { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportEntranceFragment.3
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                LogUtil.c("Track_SportEntranceFragment", "initSportTitleBar() mQrCodeAction onGranted");
                SportEntranceFragment.this.u();
            }

            @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onDenied(String str) {
                LogUtil.c("Track_SportEntranceFragment", "initSportTitleBar() mQrCodeAction onDenied");
                SportEntranceFragment.this.u();
            }

            @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
                LogUtil.c("Track_SportEntranceFragment", "initSportTitleBar() mQrCodeAction onForeverDenied");
                SportEntranceFragment.this.u();
            }
        };
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.tab_layout);
        this.cp = relativeLayout;
        nsy.cMi_(relativeLayout, false);
        this.bz = (LinearLayout) view.findViewById(R.id.sport_tab_titlebar);
        BaseActivity.setViewSafeRegion(false, this.cp);
        ag();
        dge_(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        if (this.v == null) {
            LogUtil.a("Track_SportEntranceFragment", "gotoScan is null");
        } else {
            gnm.aPB_(this.v, new Intent(this.v, (Class<?>) QrCodeScanningActivity.class));
        }
    }

    private void ag() {
        RelativeLayout.LayoutParams cLc_ = nsn.cLc_(this.v);
        View view = this.cg;
        if (view == null || cLc_ == null) {
            return;
        }
        view.setLayoutParams(cLc_);
    }

    private void dge_(View view) {
        this.bb = (RedPointImageView) view.findViewById(R.id.more_and_red_point);
        bg();
        this.bb.b(true);
        this.bb.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportEntranceFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                LoginInit.getInstance(SportEntranceFragment.this.v).browsingToLogin(new LoginResultCallback() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportEntranceFragment.2.4
                    @Override // com.huawei.hwbasemgr.LoginResultCallback
                    public boolean isNeedWait() {
                        return true;
                    }

                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        if (i == 0) {
                            SportEntranceFragment.this.am();
                        } else {
                            LogUtil.a("Track_SportEntranceFragment", "errorCode is not success", Integer.valueOf(i));
                        }
                    }
                }, "");
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void am() {
        if (isDetached() || this.v == null) {
            LogUtil.a("Track_SportEntranceFragment", "moreAndRedPointClickAction fragment is destroyed");
            return;
        }
        this.j.clear();
        this.bq = getResources().getString(R.string.IDS_device_wifi_my_qrcode_sweep_code_add);
        this.i = getResources().getString(R.string.IDS_hw_device_manager_add_device);
        this.bs = getResources().getString(R.string._2130842051_res_0x7f0211c3);
        this.cq = getResources().getString(R.string._2130844739_res_0x7f021c43);
        if (!EnvironmentInfo.k()) {
            this.j.add(this.bq);
            this.j.add(this.i);
        }
        this.j.add(this.bs);
        this.j.add(this.cq);
        int i = this.cb;
        if (i == 2 || i == 500001) {
            this.j.remove(this.bs);
        }
        j(this.j);
        if (this.cb == 10001 && pqm.c()) {
            String string = getResources().getString(R.string._2130847828_res_0x7f022854);
            this.bu = string;
            this.j.add(string);
            i(this.j);
        }
        LogUtil.c("Track_SportEntranceFragment", "moreAndRedPointClickAction() mSportType: ", Integer.valueOf(this.cb), ", mArrayList: ", this.j);
        if (this.x != null) {
            int i2 = 0;
            while (true) {
                if (i2 >= this.x.size()) {
                    break;
                }
                nqu nquVar = this.x.get(i2);
                if (this.cb != nquVar.c()) {
                    i2++;
                } else {
                    Fragment a2 = nquVar.a();
                    if (a2 == null || !(a2 instanceof AdvancedSportFragment)) {
                        LogUtil.c("Track_SportEntranceFragment", "fragment = null or not instanceof AdvancedSportFragment: ", a2);
                    } else {
                        AdvancedSportFragment.e popViewController = ((AdvancedSportFragment) a2).getPopViewController();
                        if (popViewController == null || !popViewController.c()) {
                            LogUtil.c("Track_SportEntranceFragment", "controller = null or disabled: ", popViewController);
                        } else {
                            LogUtil.c("Track_SportEntranceFragment", "IntelligencePlanFragment controller.getItemList(): ", popViewController.d());
                            new PopViewList(this.v, this.bz, popViewController.d()).e(popViewController.b());
                            return;
                        }
                    }
                }
            }
        }
        ak();
    }

    private void ak() {
        new PopViewList(this.v, this.bz, this.j).e(new PopViewList.PopViewClickListener() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportEntranceFragment.5
            @Override // com.huawei.ui.commonui.popupview.PopViewList.PopViewClickListener
            public void setOnClick(int i) {
                if (SportEntranceFragment.this.isDetached() || SportEntranceFragment.this.v == null) {
                    return;
                }
                String str = (String) SportEntranceFragment.this.j.get(i);
                if (!TextUtils.isEmpty(str)) {
                    if (str.equals(SportEntranceFragment.this.bq)) {
                        LoginInit.getInstance(SportEntranceFragment.this.v).browsingToLogin(new IBaseResponseCallback() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportEntranceFragment.5.5
                            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                            /* renamed from: onResponse */
                            public void d(int i2, Object obj) {
                                if (i2 == 0) {
                                    PermissionUtil.b(SportEntranceFragment.this.v, PermissionUtil.PermissionType.CAMERA, SportEntranceFragment.this.be);
                                }
                            }
                        }, "");
                        return;
                    }
                    if (str.equals(SportEntranceFragment.this.i)) {
                        SportEntranceFragment.this.j();
                        return;
                    }
                    if (str.equals(SportEntranceFragment.this.bs)) {
                        SportEntranceFragment.this.aj();
                        return;
                    }
                    if (str.equals(SportEntranceFragment.this.cq)) {
                        SportEntranceFragment.this.as();
                        return;
                    } else if (str.equals(SportEntranceFragment.this.bu)) {
                        pqm.d(SportEntranceFragment.this.u);
                        pqm.c(SportEntranceFragment.this.u, 400);
                        return;
                    } else {
                        LogUtil.a("Track_SportEntranceFragment", "popViewList setOnClick other");
                        return;
                    }
                }
                LogUtil.a("Track_SportEntranceFragment", "clickType is null");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aj() {
        int i = this.cb;
        if (i == 2 || i == 500001) {
            as();
        } else {
            o();
        }
    }

    private void bg() {
        RedPointImageView redPointImageView = this.bb;
        if (redPointImageView == null) {
            LogUtil.a("Track_SportEntranceFragment", "setRedPointShowOrDismiss mMoreAndRedPoint == null");
        } else {
            redPointImageView.e(false);
        }
    }

    private void i(ArrayList<String> arrayList) {
        if (arrayList == null) {
            LogUtil.a("Track_SportEntranceFragment", "setFaServiceCardRedPoint arrayList is null");
        } else {
            if (pqm.b(this.u)) {
                return;
            }
            arrayList.add(File.separator);
            arrayList.add(getResources().getString(R.string._2130847828_res_0x7f022854));
        }
    }

    private void j(ArrayList<String> arrayList) {
        if (arrayList == null) {
            LogUtil.a("Track_SportEntranceFragment", "setSportSettingRedPoint arrayList is null");
            return;
        }
        int i = this.cb;
        if ((i == 258 || i == 264) && !SharedPreferenceManager.a(Integer.toString(20002), "metronome_page_show", false) && hpg.c()) {
            v();
            arrayList.add(File.separator);
            arrayList.add(getResources().getString(R.string._2130842051_res_0x7f0211c3));
        }
    }

    private void v() {
        RedPointImageView redPointImageView = this.bb;
        if (redPointImageView != null) {
            redPointImageView.e(false);
        } else {
            LogUtil.a("Track_SportEntranceFragment", "hideMoreButtonRedPoint mMoreAndRedPoint is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        RelativeLayout relativeLayout = this.cp;
        if (relativeLayout == null) {
            LogUtil.a("Track_SportEntranceFragment", "initMoreLayoutParams mMoreAndRedPoint or mTitleLayout null");
        } else {
            relativeLayout.post(new Runnable() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportEntranceFragment.9
                @Override // java.lang.Runnable
                public void run() {
                    if (SportEntranceFragment.this.v != null) {
                        if (SportEntranceFragment.this.bb != null) {
                            SportEntranceFragment.this.bb.setVisibility(0);
                            return;
                        }
                        return;
                    }
                    LogUtil.e("Track_SportEntranceFragment", "[initMoreLayoutParams] mContext is null");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        ixx.d().d(this.v, AnalyticsValue.HEALTH_HEALTH_MY_DEVICE_2030030.value(), new HashMap(), 0);
        obb.c(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(ArrayList<nqu> arrayList, int i, Map<String, Object> map) {
        if (koq.d(arrayList, i)) {
            int c2 = arrayList.get(i).c();
            LogUtil.c("Track_SportEntranceFragment", "setOtherFragments original type: ", Integer.valueOf(c2), " position: ", Integer.valueOf(i));
            if (c2 != 2) {
                if (c2 != 137) {
                    if (c2 == 264) {
                        a(map);
                    } else if (c2 != 286) {
                        if (c2 == 10001) {
                            c(map);
                        } else if (c2 != 500001) {
                            if (c2 == 282) {
                                f(map);
                            } else if (c2 != 283) {
                                switch (c2) {
                                    case 257:
                                        g(map);
                                        break;
                                    case 258:
                                        h(map);
                                        break;
                                    case 259:
                                        d(map);
                                        break;
                                    case 260:
                                        b(map);
                                        break;
                                    default:
                                        LogUtil.a("Track_SportEntranceFragment", "setOtherFragments default");
                                        this.cb = c2;
                                        break;
                                }
                            }
                        }
                    }
                }
                d(map, c2);
            } else {
                i(map);
            }
            bh();
        } else {
            LogUtil.a("Track_SportEntranceFragment", "setOtherFragments out of beans bounds");
        }
        bg();
        ReleaseLogUtil.b("Track_SportEntranceFragment", "setOtherFragments mSportType = ", Integer.valueOf(this.cb));
        owp.j(this.v, this.cb);
        this.aa.b(this.cb);
    }

    private void i(Map<String, Object> map) {
        this.cb = 2;
        map.put(BleConstants.SPORT_TYPE, 2);
        owp.j(this.v, this.cb);
    }

    private void f(Map<String, Object> map) {
        this.cb = 282;
        map.put(BleConstants.SPORT_TYPE, 282);
        owp.j(this.v, this.cb);
    }

    private void b(Map<String, Object> map) {
        this.cb = 260;
        map.put(BleConstants.SPORT_TYPE, 260);
        owp.j(this.v, this.cb);
    }

    private void dgf_(View view) {
        if (!this.p.e()) {
            if (this.ck == null) {
                this.ck = (ViewStub) view.findViewById(R.id.viewstub_tahiti_tablayout);
            }
            if (this.ck.getParent() != null) {
                this.cj = this.ck.inflate();
            } else {
                this.cj = view.findViewById(R.id.layout_tahiti_tablayout);
            }
            View view2 = this.ch;
            if (view2 != null) {
                view2.setVisibility(8);
            }
            View view3 = this.cj;
            if (view3 != null) {
                view3.setVisibility(0);
                this.cc = (HealthSubTabWidget) this.cj.findViewById(R.id.track_sport_tahiti_tab);
            }
        } else {
            if (this.cl == null) {
                this.cl = (ViewStub) view.findViewById(R.id.viewstub_tablayout);
            }
            if (this.cl.getParent() != null) {
                this.ch = this.cl.inflate();
            } else {
                this.ch = view.findViewById(R.id.layout_tablayout);
            }
            View view4 = this.cj;
            if (view4 != null) {
                view4.setVisibility(8);
            }
            View view5 = this.ch;
            if (view5 != null) {
                view5.setVisibility(0);
                this.cc = (HealthSubTabWidget) this.ch.findViewById(R.id.track_sport_tab);
            }
        }
        if (this.cc != null) {
            ntd.b().cMD_(this.cc, true);
            this.cc.b();
            this.cc.getSubTabViewContainer().setFadingMargin(0);
        } else {
            ReleaseLogUtil.b("Track_SportEntranceFragment", "mSubTabWidget == null ");
        }
        this.k.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportEntranceFragment.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view6) {
                SportEntranceFragment.this.c(false);
                ViewClickInstrumentation.clickOnView(view6);
            }
        });
        this.bx.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportEntranceFragment.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view6) {
                if (SportEntranceFragment.this.bc.getVisibility() == 0) {
                    SportEntranceFragment.this.c(false);
                }
                ViewClickInstrumentation.clickOnView(view6);
            }
        });
        ae();
    }

    private void ae() {
        this.bc.post(new Runnable() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportEntranceFragment.8
            @Override // java.lang.Runnable
            public void run() {
                int r = nsn.r(SportEntranceFragment.this.v);
                ViewGroup.LayoutParams layoutParams = SportEntranceFragment.this.bc.getLayoutParams();
                if (layoutParams instanceof RelativeLayout.LayoutParams) {
                    RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
                    layoutParams2.topMargin = r;
                    SportEntranceFragment.this.bc.setLayoutParams(layoutParams2);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void as() {
        boolean z;
        LogUtil.c("Track_SportEntranceFragment", "onClick more sport type");
        ash.a("sportTypeSortRedClick", "true");
        ArrayList<nmk> arrayList = new ArrayList<>();
        ArrayList<nqu> arrayList2 = this.x;
        if (arrayList2 != null) {
            Iterator<nqu> it = arrayList2.iterator();
            while (it.hasNext()) {
                nqu next = it.next();
                if (next != null && next.c() != 1) {
                    int c2 = next.c();
                    String j = next.j();
                    if (!TextUtils.isEmpty(j)) {
                        arrayList.add(new nmk(c2, j, 0));
                    }
                }
            }
        }
        if (Utils.l() || LoginInit.getInstance(this.v).isBrowseMode()) {
            this.ba.setVisibility(8);
            z = false;
        } else {
            this.ba.setVisibility(0);
            z = true;
        }
        this.ay.e(arrayList, z);
        for (int i = 0; i < arrayList.size(); i++) {
            dgi_(this.ay.getChildAt(i), arrayList.get(i));
        }
        this.bc.setVisibility(0);
        this.bx.setVisibility(0);
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        ixx.d().d(this.v, AnalyticsValue.SPORT_SORT_CLICKED_2040106.value(), hashMap, 0);
    }

    private void dgi_(View view, final nmk nmkVar) {
        view.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportEntranceFragment.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                int a2 = nmkVar.a();
                boolean z = SportEntranceFragment.this.cb != a2;
                SportEntranceFragment.this.cb = a2;
                SportEntranceFragment.this.c(z);
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z) {
        this.bc.setVisibility(8);
        this.bx.setVisibility(8);
        LogUtil.c("Track_SportEntranceFragment", "mCloseMoreSport window misss");
        ArrayList<nqu> b2 = this.ca.b(this.ay.getTagInfos());
        HealthSubTabPagerAdapter healthSubTabPagerAdapter = this.cd;
        if (healthSubTabPagerAdapter == null) {
            return;
        }
        nqu.c(healthSubTabPagerAdapter.c(), b2, false);
        a((List<nqu>) b2);
        if (z || koq.c(b2)) {
            b(this.x, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Map<String, Object> map) {
        map.put("click", 1);
        LogUtil.c("Track_SportEntranceFragment", "setBiEvent for sport type selected: ", map);
        ixx.d().d(this.v, AnalyticsValue.BI_TRACK_SPORT_TYPE_ACTION_KEY.value(), map, 0);
    }

    private void bf() {
        if (this.cc == null || LanguageUtil.j(this.v)) {
            return;
        }
        try {
            Field declaredField = this.cc.getClass().getSuperclass().getDeclaredField("mSubTabItemMargin");
            if (declaredField != null) {
                declaredField.setAccessible(true);
                declaredField.set(this.cc, Integer.valueOf(getResources().getDimensionPixelOffset(R.dimen._2131364456_res_0x7f0a0a68)));
            }
        } catch (IllegalAccessException e2) {
            LogUtil.e("Track_SportEntranceFragment", e2.getMessage());
        } catch (NoSuchFieldException e3) {
            LogUtil.e("Track_SportEntranceFragment", "can't get mSubTabItemMargin field for subtab", e3);
        }
    }

    private void ad() {
        ar();
    }

    private void d(nqu nquVar, KnitFragment knitFragment) {
        int resPosId = knitFragment.getResPosId();
        if (resPosId == 4019) {
            KnitFragment knitFragment2 = this.bj;
            if (knitFragment2 == null) {
                this.bj = knitFragment;
            } else {
                nquVar.e(knitFragment2);
            }
        } else if (resPosId == 4020) {
            KnitFragment knitFragment3 = this.cu;
            if (knitFragment3 == null) {
                this.cu = knitFragment;
            } else {
                nquVar.e(knitFragment3);
            }
        } else if (resPosId == 4022) {
            KnitFragment knitFragment4 = this.av;
            if (knitFragment4 == null) {
                this.av = knitFragment;
                this.bp.put(260, true);
            } else {
                nquVar.e(knitFragment4);
            }
        } else if (resPosId == 4023) {
            KnitFragment knitFragment5 = this.at;
            if (knitFragment5 == null) {
                this.at = knitFragment;
                this.bp.put(282, true);
            } else {
                nquVar.e(knitFragment5);
            }
        } else if (resPosId == 4015) {
            KnitFragment knitFragment6 = this.cn;
            if (knitFragment6 == null) {
                this.cn = knitFragment;
            } else {
                nquVar.e(knitFragment6);
            }
        } else if (resPosId == 4016) {
            KnitFragment knitFragment7 = this.as;
            if (knitFragment7 == null) {
                this.as = knitFragment;
            } else {
                nquVar.e(knitFragment7);
            }
        } else if (resPosId == 4056) {
            KnitFragment knitFragment8 = this.ax;
            if (knitFragment8 == null) {
                this.ax = knitFragment;
            } else {
                nquVar.e(knitFragment8);
            }
        } else if (resPosId == 4040) {
            KnitFragment knitFragment9 = this.bm;
            if (knitFragment9 == null) {
                this.bm = knitFragment;
            } else {
                nquVar.e(knitFragment9);
            }
        } else if (resPosId == 4013) {
            KnitFragment knitFragment10 = this.aw;
            if (knitFragment10 == null) {
                this.aw = knitFragment;
            } else {
                nquVar.e(knitFragment10);
            }
        } else {
            LogUtil.a("Track_SportEntranceFragment", "processSuperUiFragments cause resPosId: ", Integer.valueOf(resPosId), " is unknown");
        }
        knitFragment.setScrollViewListener(d(knitFragment));
    }

    private void ar() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        ax();
        Iterator<nqu> it = this.x.iterator();
        while (it.hasNext()) {
            nqu next = it.next();
            Fragment a2 = next.a();
            if (a2 instanceof KnitFragment) {
                d(next, (KnitFragment) a2);
            }
        }
        ReleaseLogUtil.b("Track_SportEntranceFragment", "processTabFragments finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    private void c(Map<String, Object> map) {
        this.cb = 10001;
        View view = this.au;
        if (view != null) {
            view.setVisibility(8);
        }
        owp.j(this.v, this.cb);
        map.put(BleConstants.SPORT_TYPE, Integer.valueOf(this.cb));
        owp.a(this.v, true);
    }

    private void h(Map<String, Object> map) {
        this.cb = 258;
        map.put(BleConstants.SPORT_TYPE, 258);
        View view = this.au;
        if (view != null) {
            view.setVisibility(8);
        }
        owp.j(this.v, this.cb);
        ObserverManagerUtil.c("RUN_PLAN_UPDATE_HELLO_TIME", "");
        if (this.m == null && !Utils.o()) {
            RunningMusicBootPagerHelper runningMusicBootPagerHelper = new RunningMusicBootPagerHelper(BaseApplication.getContext());
            this.m = runningMusicBootPagerHelper;
            runningMusicBootPagerHelper.showBootPage(this.t);
        }
        LogUtil.c("Track_SportEntranceFragment", "boot page cost: ", Long.valueOf(SystemClock.elapsedRealtime()));
    }

    private void a(Map<String, Object> map) {
        this.cb = 264;
        map.put(BleConstants.SPORT_TYPE, 264);
        View view = this.au;
        if (view != null) {
            view.setVisibility(8);
        }
        owp.j(this.v, this.cb);
        ObserverManagerUtil.c("RUN_PLAN_UPDATE_HELLO_TIME", "");
    }

    private void g(Map<String, Object> map) {
        this.cb = 257;
        View view = this.au;
        if (view != null) {
            view.setVisibility(8);
        }
        map.put(BleConstants.SPORT_TYPE, Integer.valueOf(this.cb));
        owp.j(this.v, this.cb);
    }

    private void d(Map<String, Object> map) {
        this.cb = 259;
        map.put(BleConstants.SPORT_TYPE, 259);
        owp.j(this.v, this.cb);
        ay();
    }

    private void d(Map<String, Object> map, int i) {
        this.cb = i;
        map.put(BleConstants.SPORT_TYPE, Integer.valueOf(i));
        owp.j(this.v, this.cb);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ay() {
        DeviceInfo d2 = jpu.d("Track_SportEntranceFragment");
        if (this.au == null || this.s == null || this.q == null) {
            return;
        }
        LogUtil.c("Track_SportEntranceFragment", "mAutoSelect:", Integer.valueOf(this.h));
        if (d2 == null) {
            this.c = false;
            LogUtil.e("Track_SportEntranceFragment", "deviceInfo is null");
            bh();
            return;
        }
        int n = n();
        this.r = n;
        if (n == 0) {
            this.c = false;
        } else if (this.h == 0) {
            this.c = true;
            this.s.setText(String.format(this.v.getResources().getString(R.string.IDS_HWH_device_aw70_wear_tips), d2.getDeviceName()));
            this.q.setText(this.v.getResources().getString(R.string._2130843768_res_0x7f021878));
        } else if (n == 2) {
            if (d2.getAutoDetectSwitchStatus() == 1) {
                this.c = false;
            } else {
                this.c = true;
                String format = String.format(this.v.getResources().getString(R.string.IDS_hwh_device_aw70_pair_tips), d2.getDeviceName());
                this.q.setText(this.v.getResources().getString(R.string._2130841554_res_0x7f020fd2));
                this.s.setText(format);
            }
        } else if (n == 3) {
            String format2 = String.format(this.v.getResources().getString(R.string.IDS_hwh_device_aw70_connect_tips), d2.getDeviceName());
            this.q.setText(this.v.getResources().getString(R.string._2130843785_res_0x7f021889));
            this.c = true;
            this.s.setText(format2);
        } else {
            this.c = false;
        }
        bh();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bh() {
        View view = this.au;
        if (view == null || this.l == null) {
            return;
        }
        if (this.c && this.cb == 259) {
            view.setVisibility(0);
            if (this.r == 2 && this.h != 0) {
                this.l.setVisibility(8);
                return;
            } else {
                this.l.setVisibility(0);
                return;
            }
        }
        view.setVisibility(8);
    }

    private void bk() {
        this.q.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportEntranceFragment.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SportEntranceFragment.this.h == 0) {
                    SportEntranceFragment.this.bl();
                } else if (SportEntranceFragment.this.r != 3) {
                    SportEntranceFragment.this.c = false;
                    SportEntranceFragment.this.bh();
                } else if (SportEntranceFragment.this.getActivity() instanceof SportEntranceCallback) {
                    ((SportEntranceCallback) SportEntranceFragment.this.getActivity()).onSportEntranceCallback();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.l.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportEntranceFragment.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SportEntranceFragment.this.c = false;
                SportEntranceFragment.this.bh();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ba() {
        c cVar = this.ab;
        if (cVar == null) {
            return;
        }
        cVar.post(new Runnable() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportEntranceFragment.12
            @Override // java.lang.Runnable
            public void run() {
                SportEntranceFragment.this.i();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bc() {
        c cVar = this.ab;
        if (cVar == null) {
            return;
        }
        cVar.post(new Runnable() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportEntranceFragment.11
            @Override // java.lang.Runnable
            public void run() {
                SportEntranceFragment.this.ay();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (this.s == null || this.q == null) {
            LogUtil.e("Track_SportEntranceFragment", "autoOffView mConnectTips or mConnectAw70Text is null");
            return;
        }
        this.c = true;
        this.s.setText(String.format(this.v.getResources().getString(R.string.IDS_HWH_device_aw70_wear_tips), this.e));
        this.q.setText(this.v.getResources().getString(R.string._2130843768_res_0x7f021878));
        bh();
    }

    private void s() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: ord
            @Override // java.lang.Runnable
            public final void run() {
                SportEntranceFragment.this.d();
            }
        });
    }

    public /* synthetic */ void d() {
        ReleaseLogUtil.b(getTag(), "getAutoModeDetectStatus");
        final DeviceInfo d2 = jpu.d("Track_SportEntranceFragment");
        if (d2 == null) {
            LogUtil.a("Track_SportEntranceFragment", "deviceInfo  is null");
        } else if (gwb.e(d2)) {
            DeviceSettingsInteractors.d(this.v).c(d2.getDeviceIdentify(), new IBaseResponseCallback() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportEntranceFragment.18
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (obj instanceof byte[]) {
                        String a2 = HEXUtils.a((byte[]) obj);
                        LogUtil.c("Track_SportEntranceFragment", "getSwitch berfore onResponse objectData is ", a2);
                        try {
                            int parseInt = Integer.parseInt(a2.substring(a2.length() - 2, a2.length()));
                            ReleaseLogUtil.b(SportEntranceFragment.this.getTag(), "getWearPositionSetting value is ", Integer.valueOf(parseInt));
                            if (parseInt == 0) {
                                SportEntranceFragment.this.h = 0;
                                LogUtil.c("Track_SportEntranceFragment", "onResponse mAutoSelect:", Integer.valueOf(SportEntranceFragment.this.h));
                                SportEntranceFragment.this.e = d2.getDeviceName();
                                SportEntranceFragment.this.ba();
                            } else if (parseInt == 1) {
                                SportEntranceFragment.this.h = 1;
                                SportEntranceFragment.this.bc();
                                LogUtil.c("Track_SportEntranceFragment", "onResponse mAutoSelect：", Integer.valueOf(SportEntranceFragment.this.h));
                            } else {
                                LogUtil.a("Track_SportEntranceFragment", "onResponse other value is wrong");
                            }
                        } catch (NumberFormatException e2) {
                            LogUtil.e("Track_SportEntranceFragment", "onResponse() e ", e2.getMessage());
                        }
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bl() {
        DeviceInfo d2 = jpu.d("Track_SportEntranceFragment");
        Intent intent = new Intent(this.v, (Class<?>) Aw70ModeSelectActivity.class);
        if (d2 != null) {
            intent.putExtra("device_id", d2.getDeviceIdentify());
        }
        startActivityForResult(intent, 36);
    }

    private int n() {
        DeviceInfo d2 = jpu.d("Track_SportEntranceFragment");
        if (!gwb.e(d2)) {
            return 0;
        }
        LogUtil.c("Track_SportEntranceFragment", "mConnectState:", Integer.valueOf(d2.getDeviceConnectState()));
        return d2.getDeviceConnectState();
    }

    private void e(List<nqu> list, boolean z) {
        smy cGu_;
        LogUtil.c("Track_SportEntranceFragment", "mergeFragmentSubTabPagers, list: ", list, " isFirstTimeSelected: ", Boolean.valueOf(z));
        if (this.cd == null || this.cc == null) {
            return;
        }
        HashSet<Integer> c2 = c(list);
        HashSet<Integer> c3 = c(this.cd.c());
        LogUtil.c("Track_SportEntranceFragment", "mergeFragmentSubTabPagers sportTypes, ", c2, " currentSportTypes: ", c3);
        nqu.c(this.cd.c(), list, false);
        for (nqu nquVar : list) {
            if (nquVar != null && !c3.contains(Integer.valueOf(nquVar.c()))) {
                Drawable cGi_ = nquVar.cGi_();
                Drawable cGj_ = nquVar.cGj_();
                String j = nquVar.j();
                if (cGi_ == null && cGj_ == null) {
                    cGu_ = this.cc.c(j);
                } else {
                    cGu_ = this.cc.cGu_(j, cGi_, cGi_, cGj_);
                }
                this.cd.b(cGu_, nquVar, false);
            }
        }
        b(list, z);
    }

    private void a(List<nqu> list) {
        if (koq.b(list)) {
            return;
        }
        this.x.clear();
        nqu.c(list, this.x);
        nqu.d(list, this.x);
        au();
    }

    private void b(List<nqu> list, boolean z) {
        ViewPager.OnPageChangeListener onPageChangeListener;
        smy cGu_;
        if (list == null || this.cc == null || this.cd == null) {
            return;
        }
        bo();
        this.cc.h();
        this.ce.removeOnPageChangeListener(this.bh);
        this.cc.getSubTabViewContainer().setFadingMargin(0);
        for (int i = 0; i < list.size(); i++) {
            nqu nquVar = list.get(i);
            Drawable cGi_ = nquVar.cGi_();
            Drawable cGj_ = nquVar.cGj_();
            if (cGi_ == null && cGj_ == null) {
                cGu_ = this.cc.c(nquVar.j());
            } else {
                cGu_ = this.cc.cGu_(nquVar.j(), cGi_, cGi_, cGj_);
            }
            cGu_.e(nquVar);
            this.cc.a(cGu_, this.cb == nquVar.c());
        }
        this.cd.d();
        this.cd.d(list);
        this.cd.e(list);
        this.ce.addOnPageChangeListener(this.bh);
        final int intValue = this.by.get(this.cb, 0).intValue();
        h(this.ao);
        if (z && (onPageChangeListener = this.bh) != null) {
            onPageChangeListener.onPageSelected(intValue);
        }
        this.cc.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportEntranceFragment.20
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                if (SportEntranceFragment.this.cc != null) {
                    SportEntranceFragment.this.cc.b(intValue);
                    SportEntranceFragment.this.cc.setIsViewPagerScroll(false);
                    SportEntranceFragment.this.cc.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        ReleaseLogUtil.b("Track_SportEntranceFragment", "onPause ", this);
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        int i;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onResume();
        if (getUserVisibleHint()) {
            q();
        }
        s();
        if (!this.am && this.ao && (i = this.cb) != 10001) {
            this.aa.b(i);
        }
        z();
        if (this.ah) {
            this.ca.d();
            this.ah = false;
        }
        c("custom.UserPreference_coach_gender_Flag", (UiCallback<String>) this.z);
        ReleaseLogUtil.b("Track_SportEntranceFragment", "onResume finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    private static void c(final String str, final UiCallback<String> uiCallback) {
        if (uiCallback == null) {
            LogUtil.a("Track_SportEntranceFragment", "refreshCoachGenderFromDb callback == null");
        } else {
            LogUtil.c("Track_SportEntranceFragment", "refreshCoachGenderFromDb ");
            jdx.b(new Runnable() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportEntranceFragment.19
                @Override // java.lang.Runnable
                public void run() {
                    HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference(str);
                    if (userPreference != null && !TextUtils.isEmpty(userPreference.getValue())) {
                        uiCallback.onSuccess(userPreference.getValue());
                    } else {
                        uiCallback.onFailure(0, "refreshCoachGenderFromDb failed");
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.a("HWUserProfileMgr", UserProfileMgrApi.class);
        if (userProfileMgrApi == null) {
            LogUtil.a("Track_SportEntranceFragment", "dealUserGenderForCoach : userProfileMgrApi is null.");
        } else {
            userProfileMgrApi.getUserInfo(new e(this));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0030  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int b(com.huawei.up.model.UserInfomation r4) {
        /*
            r3 = this;
            r0 = 1
            if (r4 == 0) goto L2b
            boolean r1 = r4.isGenderValid()
            if (r1 == 0) goto Le
            int r4 = r4.getGender()
            goto L2c
        Le:
            java.lang.String r4 = "coachGenderConfig"
            java.lang.String r4 = defpackage.ash.b(r4)
            java.lang.String r1 = "get failed, local:"
            java.lang.Object[] r1 = new java.lang.Object[]{r1, r4}
            java.lang.String r2 = "Track_SportEntranceFragment"
            health.compact.a.LogUtil.c(r2, r1)
            boolean r1 = android.text.TextUtils.isEmpty(r4)
            if (r1 == 0) goto L26
            goto L2b
        L26:
            int r4 = health.compact.a.CommonUtil.h(r4)
            goto L2c
        L2b:
            r4 = r0
        L2c:
            r1 = 2
            if (r4 != r1) goto L30
            goto L31
        L30:
            r0 = r4
        L31:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.homehealth.runcard.trackfragments.SportEntranceFragment.b(com.huawei.up.model.UserInfomation):int");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        final CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.a("Track_SportEntranceFragment", "confirmClearCache courseApi == null");
        } else {
            courseApi.delCourseUseCache(new UiCallback<Boolean>() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportEntranceFragment.17
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.a("Track_SportEntranceFragment", "cleanMediaFiles delUseCache onFailure errorCode = ", Integer.valueOf(i), ", errorInfo = ", str);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(Boolean bool) {
                    LogUtil.c("Track_SportEntranceFragment", "cleanMediaFiles onSuccess");
                    courseApi.updateCourseDataState();
                    ggs.e();
                    if (SportEntranceFragment.this.ab != null) {
                        SportEntranceFragment.this.ab.sendEmptyMessage(101);
                    } else {
                        LogUtil.a("Track_SportEntranceFragment", "cleanMediaFiles mHandler is null");
                    }
                }
            });
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        LogUtil.c("Track_SportEntranceFragment", "onStart ", this);
        an();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        LogUtil.c("Track_SportEntranceFragment", "onStop ", this);
        i(true);
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        LogUtil.c("Track_SportEntranceFragment", "onSaveInstanceState");
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ag();
        if (this.ao) {
            r();
            this.ar = false;
        } else {
            this.ar = true;
        }
    }

    private void r() {
        Context context = getContext();
        if (context != null) {
            this.p.e(context);
            if (this.o != this.p.f()) {
                this.o = this.p.f();
                y();
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        HealthNativeViewPager healthNativeViewPager = this.ce;
        if (healthNativeViewPager != null) {
            healthNativeViewPager.setOnPageChangeListener(null);
            this.ce.clearOnPageChangeListeners();
        }
        super.onDestroy();
        LogUtil.c("Track_SportEntranceFragment", "onDestroy ", this);
        e("onDestroy");
        if (this.aj) {
            d("action_stop_play_sport_music");
            this.aj = false;
        }
        this.aa.e();
        this.cm.unRegisterCallback(2);
        ThreadPoolManager.d().b("Track_SportEntranceFragment", null);
        if (CommonUtil.ce()) {
            bj();
        }
        if (!this.an) {
            bn();
            this.an = true;
        }
        az();
        c cVar = this.ab;
        if (cVar != null) {
            cVar.removeCallbacksAndMessages(null);
            this.ab = null;
        }
        d(true);
        pqm.c(this.u);
        bi();
    }

    private void az() {
        ax();
        this.cc = null;
        this.bl = null;
        this.v = null;
        this.bd = null;
        this.ce = null;
        this.cd = null;
        this.aa = null;
        this.cf = null;
        ArrayList<nqu> arrayList = this.x;
        if (arrayList != null) {
            arrayList.clear();
        }
        this.bk = null;
    }

    private void ax() {
        this.bj = null;
        this.cu = null;
        this.at = null;
        this.av = null;
        this.cn = null;
        this.as = null;
        this.ax = null;
        this.bm = null;
        this.aw = null;
    }

    private void o() {
        LogUtil.c("Track_SportEntranceFragment", "showSportExtrasSettingView");
        e("onClick");
        Intent intent = new Intent(BaseApplication.getContext(), (Class<?>) SportAssistSettingsActivity.class);
        intent.putExtra("currentSportType", this.cb);
        gnm.aPB_(this.v, intent);
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put("type", 1);
        ixx.d().d(this.v, AnalyticsValue.MOTION_TRACK_1040021.value(), hashMap, 0);
    }

    public static class c extends BaseHandler<SportEntranceFragment> {
        c(Looper looper, SportEntranceFragment sportEntranceFragment) {
            super(looper, sportEntranceFragment);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dgl_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(SportEntranceFragment sportEntranceFragment, Message message) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            int i = message.what;
            if (i != 2) {
                if (i == 42) {
                    if (sportEntranceFragment.bo != null) {
                        dgk_(sportEntranceFragment, message);
                    }
                } else {
                    switch (i) {
                        case 100:
                            LogUtil.c("Track_SportEntranceFragment", "unloadFragmentResources, MSG_FRAGMENT_HIDE");
                            sportEntranceFragment.i(false);
                            break;
                        case 101:
                            if (sportEntranceFragment.aa != null) {
                                sportEntranceFragment.aa.b(sportEntranceFragment.cb);
                                break;
                            }
                            break;
                        case 102:
                            if (sportEntranceFragment.getUserVisibleHint()) {
                                sportEntranceFragment.ac();
                                break;
                            }
                            break;
                        case 103:
                            Object obj = message.obj;
                            if (obj instanceof List) {
                                d(sportEntranceFragment, (List) obj);
                                break;
                            }
                            break;
                    }
                }
            } else if (message.obj instanceof NewMedalTabDataBean) {
                sportEntranceFragment.d((NewMedalTabDataBean) message.obj);
            }
            ReleaseLogUtil.b("Track_SportEntranceFragment", "msg:" + message.what + " finished, time cost:", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
        }

        private void d(final SportEntranceFragment sportEntranceFragment, List<rdn> list) {
            Iterator<rdn> it = list.iterator();
            int i = 0;
            int i2 = 0;
            while (it.hasNext()) {
                if (it.next().m() == 258) {
                    i++;
                } else {
                    i2++;
                }
            }
            ory.a(sportEntranceFragment.bd, i, i2, list.size());
            if (!sportEntranceFragment.ai) {
                ory.b(sportEntranceFragment.bd, list, new IBaseResponseCallback() { // from class: ore
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i3, Object obj) {
                        SportEntranceFragment.this.ae = false;
                    }
                });
                sportEntranceFragment.ai = true;
            } else {
                ReleaseLogUtil.a("Track_SportEntranceFragment", "mIsAutoDetectDialog is Showing");
            }
        }

        private void dgk_(SportEntranceFragment sportEntranceFragment, Message message) {
            Object obj = message.obj;
            if (obj instanceof InputBoxTemplate) {
                InputBoxTemplate inputBoxTemplate = (InputBoxTemplate) obj;
                sportEntranceFragment.y = inputBoxTemplate;
                if (TextUtils.isEmpty(sportEntranceFragment.bo.getQueryHint())) {
                    dpf.b(sportEntranceFragment.bo, inputBoxTemplate);
                    if (sportEntranceFragment.ao) {
                        fbh.e(sportEntranceFragment.v, sportEntranceFragment.t(), sportEntranceFragment.y);
                    }
                }
                dpf.e(sportEntranceFragment.y.getTheme());
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        LogUtil.c("Track_SportEntranceFragment", "setUserVisibleHint ", Boolean.valueOf(z));
        this.ao = z;
        ObserverManagerUtil.c("SPORT_ENTRANCE_IS_VISIBLE_TO_USER", Boolean.valueOf(z));
        g(z);
        e(z);
        if (z) {
            new gho().c();
        }
        if (this.bl == null) {
            LogUtil.a("Track_SportEntranceFragment", "setUserVisibleHint mRootView is null");
            if (this.ao) {
                this.ac = true;
                ObserverManagerUtil.c("ALLOW_VISIBLE_HINT_LOAD", true);
                return;
            }
            return;
        }
        SharedPreferenceManager.e(this.v, this.az, "EXIT_APP_AT_SPORT_TAB", Boolean.toString(z), this.cf);
        if (z) {
            ObserverManagerUtil.c("ALLOW_VISIBLE_HINT_LOAD", true);
            this.ca.i();
            q();
            if (this.am) {
                y();
                LogUtil.c("Track_SportEntranceFragment", "initAll in setUserVisibleHint");
            } else {
                an();
                int i = this.cb;
                if (i != 10001) {
                    this.aa.b(i);
                }
            }
            if (this.ar) {
                r();
                this.ar = false;
            }
            this.ac = true;
            bm();
            ac();
            if (this.ag.containsKey(Integer.valueOf(this.cb)) && !this.bt.contains(Integer.valueOf(this.cb))) {
                this.bt.add(Integer.valueOf(this.cb));
                fbh.e(this.v, t(), this.ag.get(Integer.valueOf(this.cb)));
            }
            if (this.cb == 10001) {
                pqm.e(this.u, 0);
            }
        } else {
            if (this.aj) {
                d("action_stop_play_sport_music");
                this.aj = false;
            }
            at();
            pqm.e(this.u, 8);
        }
        if (getView() != null) {
            int x = x();
            if (x == 0) {
                LogUtil.c("Track_SportEntranceFragment", "current tab type positionId not exist");
                nmh.c(x);
                return;
            }
            eil.alV_(getActivity().getWindow().getDecorView(), z, x, "SportPage");
        }
        LogUtil.c("Track_SportEntranceFragment", "setUserVisibleHint end");
    }

    private void e(boolean z) {
        if (koq.b(this.x)) {
            LogUtil.a("Track_SportEntranceFragment", "setAllTabUserVisibleHint: mFragmentSubTabPagerBeans is empty.");
            return;
        }
        Iterator<nqu> it = this.x.iterator();
        while (it.hasNext()) {
            nqu next = it.next();
            if (next != null) {
                Fragment a2 = next.a();
                if (a2 instanceof KnitFragment) {
                    b((KnitFragment) a2, z);
                }
            }
        }
    }

    private void b(KnitFragment knitFragment, boolean z) {
        if (knitFragment == null) {
            LogUtil.c("Track_SportEntranceFragment", "fragment is null");
        } else if (z) {
            knitFragment.setUserVisibleHint(knitFragment.getIsSetUserVisibleLastTime());
        } else {
            knitFragment.setIsSetUserVisibleLastTime(knitFragment.getIsVisibleToUser());
            knitFragment.setUserVisibleHint(false);
        }
    }

    private void g(boolean z) {
        if (z) {
            int i = this.cb;
            if (i == 258 || i == 259 || i == 264) {
                BoltCustomDialog.a().c();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(NewMedalTabDataBean newMedalTabDataBean) {
        if (newMedalTabDataBean == null || newMedalTabDataBean.getSecondTabRelationshipForSport() == null || !this.ao) {
            return;
        }
        Map<String, ArrayList<MedalInfoDesc>> secondTabRelationshipForSport = newMedalTabDataBean.getSecondTabRelationshipForSport();
        this.bv = secondTabRelationshipForSport;
        if (mjt.a(secondTabRelationshipForSport, this.cb)) {
            DragFloatActionButton dragFloatActionButton = this.w;
            if (dragFloatActionButton != null) {
                dragFloatActionButton.g();
                return;
            } else {
                aa();
                return;
            }
        }
        DragFloatActionButton dragFloatActionButton2 = this.w;
        if (dragFloatActionButton2 != null) {
            dragFloatActionButton2.d();
        }
    }

    private void aa() {
        this.w = new DragFloatActionButton(this.v);
        Drawable drawable = ContextCompat.getDrawable(this.v, R.drawable._2131430481_res_0x7f0b0c51);
        if (drawable == null) {
            return;
        }
        this.w.setIconImage(drawable, mjt.ciP_(this.v), mjt.ciN_(this.v));
        this.w.e(true);
        this.w.setlistener(new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportEntranceFragment.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                mjt.d(SportEntranceFragment.this.v, SportEntranceFragment.this.bv, SportEntranceFragment.this.cb, 0);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.w.cAr_(mjt.ciO_(this.v));
        if (this.ao && mjt.c()) {
            mjt.d(this.v, this.bv, this.cb, 1);
        }
    }

    private int x() {
        LogUtil.c("Track_SportEntranceFragment", "mSportType: ", Integer.valueOf(this.cb));
        int i = this.cb;
        if (i == 137) {
            return 3024;
        }
        if (i == 264) {
            return 3017;
        }
        if (i == 286) {
            return 3043;
        }
        if (i == 10001) {
            return 3018;
        }
        if (i == 500001) {
            return 3042;
        }
        if (i == 282) {
            return 3027;
        }
        if (i == 283) {
            return 3023;
        }
        switch (i) {
            case 257:
                return 3019;
            case 258:
                return 3016;
            case 259:
                return 3020;
            case 260:
                return 3026;
            default:
                return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int t() {
        int i = this.cb;
        if (i == 137) {
            return 4020;
        }
        if (i == 257) {
            return 4015;
        }
        if (i == 264) {
            return 4013;
        }
        if (i == 10001) {
            return 4014;
        }
        if (i == 500001) {
            return 4040;
        }
        if (i == 259) {
            return 4016;
        }
        if (i == 260) {
            return 4022;
        }
        if (i != 282) {
            return i != 283 ? 4012 : 4019;
        }
        return 4023;
    }

    private void c(MarketingApi marketingApi, MarketingOption.Builder builder) {
        builder.setTypeId(51);
        marketingApi.triggerMarketingResourceEvent(builder.build());
        builder.setTypeId(52);
        marketingApi.triggerMarketingResourceEvent(builder.build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ac() {
        if (b) {
            LogUtil.a("Track_SportEntranceFragment", "initMarketingGuide sIsShowAdNow = true");
            return;
        }
        MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi != null) {
            MarketingOption.Builder builder = new MarketingOption.Builder();
            builder.setContext(getContext());
            builder.setPageId(401);
            marketingApi.requestMarketingResource(builder.build());
            HashMap hashMap = new HashMap();
            hashMap.put("open_specific_page", "SportPage");
            builder.setTriggerEventParams(hashMap);
            builder.setTypeId(49);
            marketingApi.triggerMarketingResourceEvent(builder.build());
            c(marketingApi, builder);
        }
    }

    private void q() {
        HashMap hashMap = new HashMap(1);
        hashMap.put("click", 1);
        ixx.d().d(this.v, AnalyticsValue.BI_TRACK_ENTER_SPORT_TAB_1040043.value(), hashMap, 0);
        nsn.e(this.v, 1);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.c("Track_SportEntranceFragment", "showSportTip onActivityResult", Integer.valueOf(i), "resultCode: ", Integer.valueOf(i2));
        FaCardAgdsApi c2 = dlf.c();
        if (c2 != null && i == 400) {
            pqm.dsh_(0, i, intent);
            c2.close();
            return;
        }
        if (i == 102) {
            obb.d(this.v);
            return;
        }
        if (i == 100) {
            if (i2 == c2.getResultcodeAgreeProtocol()) {
                pqm.d(this.u);
                pqm.c(this.u, 400);
            } else if (i2 == c2.getResultcodeNotAgreeProtocol()) {
                ReleaseLogUtil.b("Track_SportEntranceFragment", "openFaCardAgds NotAgreeProtocol");
            }
        }
    }

    public boolean b() {
        return this.ak;
    }

    private void al() {
        if (nsn.ae(this.v)) {
            LogUtil.c("Track_SportEntranceFragment", "isPad not loadMap");
        }
        if (isAdded()) {
            this.bl.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportEntranceFragment.25
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    if (SportEntranceFragment.this.bl != null) {
                        SportEntranceFragment.this.bl.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        SportEntranceFragment.this.z();
                        if (nsn.ae(SportEntranceFragment.this.v)) {
                            LogUtil.c("Track_SportEntranceFragment", "isPad not loadMap");
                            return;
                        }
                        return;
                    }
                    LogUtil.a("Track_SportEntranceFragment", "loadMap mRootView is null");
                }
            });
        }
    }

    private void f() {
        Iterator<nqu> it = this.x.iterator();
        while (it.hasNext()) {
            nqu next = it.next();
            nqu nquVar = new nqu(null, next.j(), next.c());
            nquVar.cGk_(next.cGi_());
            nquVar.cGl_(next.cGj_());
            this.n.add(nquVar);
        }
    }

    private void e(ArrayList<nqu> arrayList) {
        if (arrayList.isEmpty()) {
            return;
        }
        Iterator<nqu> it = arrayList.iterator();
        while (it.hasNext()) {
            nqu next = it.next();
            if (next != this.bk && next != null) {
                next.e((Fragment) null);
            }
        }
    }

    private void h() {
        this.bk = null;
        f();
        Integer num = this.by.get(this.cb);
        if (num != null && koq.d(this.x, num.intValue())) {
            nqu nquVar = this.x.get(num.intValue());
            this.bk = nquVar;
            if (nquVar != null && nquVar.c() != this.cb) {
                LogUtil.a("Track_SportEntranceFragment", "mRetainFragmentSubTabPagerBean's sportType is not equal to mSportType,", " bean's sportType: ", Integer.valueOf(this.bk.c()), " mSportType: ", Integer.valueOf(this.cb));
                this.bk = null;
                return;
            }
        }
        ArrayList<nqu> arrayList = new ArrayList<>(this.x);
        this.x.clear();
        if (this.cd != null) {
            nqu nquVar2 = this.bk;
            if (nquVar2 != null) {
                LogUtil.c("Track_SportEntranceFragment", "save retain fragment sportType=", Integer.valueOf(this.cb), ", fragment=", nquVar2.a());
            }
            if (!this.cd.c(this.bk)) {
                LogUtil.e("Track_SportEntranceFragment", "detachedAllFragment failed");
                this.bk = null;
            }
        }
        SportViewModel sportViewModel = this.ca;
        if (sportViewModel != null) {
            sportViewModel.h();
            this.ah = true;
        }
        ax();
        e(arrayList);
    }

    private boolean h(ArrayList<nqu> arrayList) {
        if (this.bk == null || arrayList == null) {
            return false;
        }
        ArrayList<nqu> arrayList2 = new ArrayList<>(arrayList);
        if (this.bk.c() != this.cb) {
            LogUtil.a("Track_SportEntranceFragment", "mRetainFragmentSubTabPagerBean's sportType is not equal to mSportType,", " bean's sportType: ", Integer.valueOf(this.bk.c()), " mSportType: ", Integer.valueOf(this.cb));
            return false;
        }
        ArrayList arrayList3 = new ArrayList(arrayList2.size());
        int i = -1;
        for (int i2 = 0; i2 < arrayList2.size(); i2++) {
            nqu nquVar = arrayList2.get(i2);
            if (nquVar != null && nquVar.a() != null) {
                if (!Integer.valueOf(i2).equals(this.by.get(nquVar.c()))) {
                    LogUtil.c("Track_SportEntranceFragment", "fragment pos change, sportType:", Integer.valueOf(nquVar.c()));
                    return false;
                }
                if (nquVar.c() == this.bk.c()) {
                    i = i2;
                }
                arrayList3.add(nquVar.a());
            }
        }
        if ((this.cc != null && arrayList3.size() != this.cc.getSubTabCount()) || i == -1) {
            LogUtil.c("Track_SportEntranceFragment", "fragment num change, or retain fragment type not exist, sportType:", Integer.valueOf(this.cb));
            return false;
        }
        arrayList2.set(i, this.bk);
        List<nqu> a2 = nqu.a(arrayList2);
        if (a2.size() < arrayList2.size()) {
            LogUtil.e("Track_SportEntranceFragment", "found duplicated beans in the list: ", Integer.valueOf(arrayList2.size()), " ", Integer.valueOf(a2.size()));
            return false;
        }
        return c(arrayList2, this.bk);
    }

    private void au() {
        for (nqu nquVar : this.n) {
            Iterator<nqu> it = this.x.iterator();
            while (it.hasNext()) {
                nqu next = it.next();
                if (next.c() == nquVar.c()) {
                    next.c(nquVar);
                }
            }
        }
    }

    private boolean c(ArrayList<nqu> arrayList, nqu nquVar) {
        this.x = new ArrayList<>(arrayList);
        LogUtil.c("Track_SportEntranceFragment", "restore retain fragment sportType=", Integer.valueOf(this.cb), ", retainFragBean =", nquVar);
        HealthSubTabPagerAdapter healthSubTabPagerAdapter = this.cd;
        if (healthSubTabPagerAdapter != null && !healthSubTabPagerAdapter.c(this.x, nquVar)) {
            return false;
        }
        if (nquVar != null) {
            int c2 = nquVar.c();
            LogUtil.c("Track_SportEntranceFragment", "retain sport type: ", Integer.valueOf(c2));
            Integer num = this.by.get(c2);
            e(nquVar, c2);
            if (num != null) {
                this.ce.setCurrentItem(num.intValue(), false);
            }
        }
        if (!this.f9563a) {
            aq();
        }
        this.bk = null;
        ad();
        return true;
    }

    private void e(nqu nquVar, int i) {
        if (i == 10001 || i == 137 || i == 258 || i == 264) {
            Fragment a2 = nquVar.a();
            if (a2 instanceof KnitFragment) {
                ((KnitFragment) a2).setUserVisibleHint(true);
                LogUtil.d("Track_SportEntranceFragment", "setUserVisibleHint for fitness fragment ");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(boolean z) {
        boolean af = af();
        LogUtil.c("Track_SportEntranceFragment", "unloadFragmentResources, isUserVisible=", Boolean.valueOf(this.ao), ", isDelayed=", Boolean.valueOf(z), ", needClean=", Boolean.valueOf(af));
        k();
        if (this.am || !af) {
            return;
        }
        if (z) {
            at();
            return;
        }
        a(false);
        h();
        a(true);
        DfxMonitorCenter.a();
    }

    private void a(boolean z) {
        HealthSubTabPagerAdapter healthSubTabPagerAdapter = this.cd;
        if (healthSubTabPagerAdapter == null) {
            return;
        }
        int count = healthSubTabPagerAdapter.getCount();
        LogUtil.c("Track_SportEntranceFragment", "setAllPageResTriggerIsEnabled, isEnabled: ", Boolean.valueOf(z), " count: ", Integer.valueOf(count));
        for (int i = 0; i < count; i++) {
            Fragment item = this.cd.getItem(i);
            if (item instanceof KnitFragment) {
                ((KnitFragment) item).setPageResTriggerEnabled(z);
            }
        }
    }

    private void an() {
        boolean z = !af();
        LogUtil.c("Track_SportEntranceFragment", "loadFragmentResources, isUserVisible=", Boolean.valueOf(this.ao), ", needRestore=", Boolean.valueOf(z));
        k();
        if (!this.am && this.ao && z) {
            this.ca.g();
        }
    }

    private boolean af() {
        ArrayList<nqu> arrayList = this.x;
        return (arrayList == null || arrayList.isEmpty()) ? false : true;
    }

    private void at() {
        c cVar = this.ab;
        if (cVar != null) {
            cVar.sendEmptyMessageDelayed(100, 60000L);
        }
    }

    private void k() {
        c cVar = this.ab;
        if (cVar != null) {
            cVar.removeMessages(100);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public LoaderManager getLoaderManager() {
        return super.getLoaderManager();
    }

    public void d(Context context, boolean z, int i, int i2) {
        LogUtil.c("Track_SportEntranceFragment", "resolveOutterParams launchSource ", Integer.valueOf(i2), " sportType ", Integer.valueOf(i), " isSelected ", Boolean.valueOf(z));
        if (i2 == 1) {
            this.aj = z;
            if (z) {
                SportMusicInteratorService.e();
                return;
            }
            return;
        }
        if (i2 == 6 || i2 == 7 || i2 == 8) {
            e(context, i);
            return;
        }
        if (i2 == 9) {
            e(context, i);
            HashMap hashMap = new HashMap(2);
            hashMap.put("click", 1);
            hashMap.put("type", 1);
            ixx.d().d(this.v, AnalyticsValue.REMIND_NOTIFICATION_CLICK_COUNT.value(), hashMap, 0);
            return;
        }
        if (i2 == 10) {
            HashMap hashMap2 = new HashMap(2);
            hashMap2.put("click", 1);
            hashMap2.put("type", 2);
            ixx.d().d(this.v, AnalyticsValue.REMIND_NOTIFICATION_CLICK_COUNT.value(), hashMap2, 0);
            return;
        }
        if (i2 == 11) {
            LogUtil.c("Track_SportEntranceFragment", "goto jump rope fragment");
            e(context, i);
        } else if (i == 2) {
            LogUtil.c("Track_SportEntranceFragment", "goto plan fragment");
            e(context, 2);
        } else {
            LogUtil.a("Track_SportEntranceFragment", "resolveOutterParams do nothing from error launchSource");
        }
    }

    private void e(Context context, final int i) {
        LogUtil.c("Track_SportEntranceFragment", "gotoExactTabFromHealthModel sportType = ", Integer.valueOf(i));
        if (this.am) {
            LogUtil.c("Track_SportEntranceFragment", "gotoExactTabFromHealthModel need goto ", Integer.valueOf(i));
            Context context2 = this.v;
            if (context2 != null) {
                context = context2;
            }
            this.v = context;
            owp.j(context, i);
            return;
        }
        LogUtil.c("Track_SportEntranceFragment", "gotoExactTabFromHealthModel is not first in sport fragment");
        HandlerExecutor.e(new Runnable() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportEntranceFragment.24
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.c("Track_SportEntranceFragment", "jump to target tab in postUiThread");
                if (SportEntranceFragment.this.ce == null || SportEntranceFragment.this.cd == null) {
                    return;
                }
                SportEntranceFragment.this.cb = i;
                owp.j(SportEntranceFragment.this.v, SportEntranceFragment.this.cb);
                if (i == 2) {
                    SportEntranceFragment.this.ce.setCurrentItem(((Integer) SportEntranceFragment.this.by.get(i, 0)).intValue(), false);
                } else {
                    SportEntranceFragment.this.ce.setCurrentItem(((Integer) SportEntranceFragment.this.by.get(i, 0)).intValue());
                }
            }
        });
    }

    private void d(String str) {
        if (!this.aj || str == null) {
            return;
        }
        LogUtil.c("Track_SportEntranceFragment", str.substring(0, 20));
        Intent intent = new Intent(str);
        e("sendActionToMusicService");
        BroadcastManagerUtil.bFI_(this.v, intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        if (this.v == null && isAdded()) {
            Context context = getContext();
            this.v = context;
            LogUtil.a("Track_SportEntranceFragment", str, " mContext is null, get it again ", context);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bo() {
        this.by.clear();
        for (int i = 0; i < this.x.size(); i++) {
            nqu nquVar = this.x.get(i);
            if (nquVar != null) {
                this.by.put(nquVar.c(), Integer.valueOf(i));
            }
        }
    }

    public void b(boolean z) {
        LogUtil.c("Track_SportEntranceFragment", "updateShowAd: ", Boolean.valueOf(z));
        d(z);
        c cVar = this.ab;
        if (cVar != null) {
            cVar.sendEmptyMessageDelayed(102, 50L);
        }
        ObserverManagerUtil.c("UPDATE_AD_SHOW", true);
    }

    public static boolean a() {
        return b;
    }

    public static void d(boolean z) {
        b = z;
    }

    public static class d implements ScrollViewListener {
        private final WeakReference<KnitFragment> b;
        private final WeakReference<SportEntranceFragment> e;

        public d(SportEntranceFragment sportEntranceFragment, KnitFragment knitFragment) {
            this.b = new WeakReference<>(knitFragment);
            this.e = new WeakReference<>(sportEntranceFragment);
        }

        @Override // com.huawei.ui.commonui.scrollview.ScrollViewListener
        public void onScrollChanged(HealthScrollView healthScrollView, int i, int i2, int i3, int i4) {
            SportEntranceFragment sportEntranceFragment = this.e.get();
            KnitFragment knitFragment = this.b.get();
            if (knitFragment != null && sportEntranceFragment != null) {
                if (sportEntranceFragment.aq) {
                    sportEntranceFragment.ao();
                    if (sportEntranceFragment.br > 0 && i2 >= sportEntranceFragment.br && !sportEntranceFragment.ap && dpf.a(i2, knitFragment.getScrollViewHeight(), knitFragment.getScrollViewRange())) {
                        sportEntranceFragment.w();
                    }
                    if (i2 > 0 || !sportEntranceFragment.ap) {
                        return;
                    }
                    dpf.Ys_(sportEntranceFragment.bl);
                    ViewGroup viewGroup = (knitFragment == null || !(knitFragment.getContentView() instanceof ViewGroup)) ? null : (ViewGroup) knitFragment.getContentView();
                    ViewGroup[] viewGroupArr = {sportEntranceFragment.co, viewGroup};
                    LayoutTransition[] Yr_ = viewGroup != null ? dpf.Yr_(viewGroupArr) : null;
                    dpf.e(sportEntranceFragment.bo);
                    dpf.Yt_(sportEntranceFragment.bn, 8);
                    if (viewGroup != null) {
                        dpf.Yp_(sportEntranceFragment.ab, viewGroupArr, Yr_);
                    }
                    sportEntranceFragment.ap = false;
                    return;
                }
                return;
            }
            LogUtil.e("Track_SportEntranceFragment", "sportEntranceFragment is null");
        }
    }

    static class e implements BaseResponseCallback<UserInfomation> {
        WeakReference<SportEntranceFragment> d;

        e(SportEntranceFragment sportEntranceFragment) {
            this.d = new WeakReference<>(sportEntranceFragment);
        }

        @Override // com.huawei.health.userprofilemgr.model.BaseResponseCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, UserInfomation userInfomation) {
            SportEntranceFragment sportEntranceFragment = this.d.get();
            if (sportEntranceFragment != null) {
                int b = sportEntranceFragment.b(userInfomation);
                String b2 = ash.b("coachGenderConfig");
                LogUtil.c("Track_SportEntranceFragment", "UserGenderCallback onResponse localGender = ", b2);
                int h = CommonUtil.h(b2);
                if ((TextUtils.isEmpty(b2) && b == 0) || (b != h && !TextUtils.isEmpty(b2))) {
                    LogUtil.c("Track_SportEntranceFragment", "UserGenderCallback onResponse localGender = ", b2, ", localGenderInt = ", Integer.valueOf(h), ", user = ", Integer.valueOf(b));
                    sportEntranceFragment.m();
                }
                ash.a("coachGenderConfig", String.valueOf(b));
                return;
            }
            LogUtil.a("Track_SportEntranceFragment", "fragment is null");
        }
    }

    private void h(boolean z) {
        Activity activity = this.bd;
        if (activity == null || !z) {
            LogUtil.a("Track_SportEntranceFragment", "showAutoIdentifyRecordDialog mParentActivity is null or user is not visible");
            return;
        }
        if (LoginInit.getInstance(activity).isBrowseMode()) {
            LogUtil.c("Track_SportEntranceFragment", "browse mode, not show auto identify record data");
            return;
        }
        if (Utils.o()) {
            LogUtil.c("Track_SportEntranceFragment", "isShowAutoRecordsGuideDialog the version is oversea");
        } else if (!SharedPreferenceManager.a("HiHealthService", "pullAllStatus", false)) {
            LogUtil.c("Track_SportEntranceFragment", "sync is not over");
        } else {
            be();
        }
    }

    private void be() {
        String str;
        boolean z;
        guz d2 = gso.e().d(this.bd);
        if (d2.f() || !mwx.b()) {
            str = "auto_identify_record_guide_cancel";
            z = false;
        } else {
            str = "auto_identify_record_guide_cancel_for_walk";
            z = true;
        }
        String b2 = SharedPreferenceManager.b(this.bd, Integer.toString(10005), str);
        if ((!TextUtils.isEmpty(b2) && Boolean.parseBoolean(b2)) || d2.e() || !ory.d(this.bd, d2)) {
            b(this.bd);
        } else if (this.ai) {
            LogUtil.c("Track_SportEntranceFragment", "auto detect dialog is showing");
        } else {
            ory.d(this.bd, d2, z, new IBaseResponseCallback() { // from class: orc
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    SportEntranceFragment.this.a(i, obj);
                }
            });
            this.ai = true;
        }
    }

    public /* synthetic */ void a(int i, Object obj) {
        if (!(obj instanceof Boolean)) {
            LogUtil.a("Track_SportEntranceFragment", "showAutoRecordsGuideDialog objData is not instanceof Boolean");
        } else if (((Boolean) obj).booleanValue()) {
            b(this.bd);
            this.ai = false;
        } else {
            LogUtil.a("Track_SportEntranceFragment", "showAutoRecordsGuideDialog objData is false");
        }
    }

    private void b(final Context context) {
        String b2 = SharedPreferenceManager.b(context, Integer.toString(10005), "auto_identify_record_history_cancel");
        if (!TextUtils.isEmpty(b2) && Boolean.parseBoolean(b2)) {
            LogUtil.c("Track_SportEntranceFragment", "clicked auto records history dialog cancel");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportEntranceFragment.23
                @Override // java.lang.Runnable
                public void run() {
                    ory.e(context, new IBaseResponseCallback() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportEntranceFragment.23.4
                        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                        /* renamed from: onResponse */
                        public void d(int i, Object obj) {
                            Message obtain = Message.obtain();
                            obtain.obj = obj;
                            obtain.what = 103;
                            SportEntranceFragment.this.ab.sendMessage(obtain);
                        }
                    });
                }
            });
        }
    }
}
