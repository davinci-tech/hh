package com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huawei.btsportdevice.callback.MessageOrStateCallback;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.open.data.model.CommonUiResponse;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.AllCoursesListFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.CourseModel;
import com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.DownloadedCoursesListFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.VoiceCourseFragment;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.popupview.PopViewList;
import com.huawei.ui.commonui.subtab.HealthSubTabWidget;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.uikit.hwviewpager.widget.HwViewPager;
import defpackage.dds;
import defpackage.dij;
import defpackage.koq;
import defpackage.msb;
import defpackage.nqx;
import defpackage.nrh;
import defpackage.nsf;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import java.io.File;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes3.dex */
public class VoiceCourseFragment extends BaseFragment implements MessageOrStateCallback, AllCoursesListFragment.OnMediaItemClick, DownloadedCoursesListFragment.OnDownloadedListener {
    private static final int DEFAULT_LIST_SIZE = 5;
    private static final int DOWNLOAD_PROFILE_SUCCESS = 10001;
    private static final int HANDLER_POSTDELAYED_100MS = 100;
    private static final int HANDLER_POSTDELAYED_200MS = 200;
    private static final int REFRESH_DOWNLOADED_FRAGMENT = 10002;
    private static final int SET_REMIND_MEMORY = 10003;
    private static final String TAG = "VoiceCourseFragment";
    private static final float WIDTH_HEIGHT_PROPORTIONS = 0.56f;
    private AllCoursesListFragment mAllCoursesListFragment;
    private HealthImageView mCourseRecommend;
    private DownloadedCoursesListFragment mDownloadedCoursesListFragment;
    private HealthTextView mHealthTextViewRemind;
    private LinearLayout mLinearLayoutRemind;
    private MediaManager mMediaManager;
    private int mMediaType;
    private int mPageSelected;
    private PopViewList mPopViewList;
    private int mProfileVersion;
    private String mRopeBrMac;
    private int mRopeCourseProfileVersion;
    private ArrayList<CourseModel.Course> mRopeMediaList;
    private int mRopeMusicProfileVersion;
    private int mRopeRemainingMemory;
    private HealthSubTabWidget mSubTabCoursePageSelect;
    private HealthViewPager mViewPagerCourseList;
    private nqx mVoiceCourseTabAdapter;
    private List<Fragment> mFragments = new ArrayList();
    private List<String> mTabNewList = new ArrayList(5);
    private VoiceCourseFragmentHandler mHandler = new VoiceCourseFragmentHandler(this, null);
    private boolean mIsGetMemory = false;

    @Override // com.huawei.btsportdevice.callback.MessageOrStateCallback
    public void onStateChange(String str) {
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        AllCoursesListFragment allCoursesListFragment;
        if (this.mPageSelected == 0 && (allCoursesListFragment = this.mAllCoursesListFragment) != null && allCoursesListFragment.isMultipleSelectStatus()) {
            this.mAllCoursesListFragment.setIsMultipleSelectStatus(false);
            return false;
        }
        if (this.mPageSelected == 1 && isDownloadedSelectOrSortStatus()) {
            return false;
        }
        if (!TextUtils.isEmpty(this.mRopeBrMac)) {
            dds.c().d(1, 3, new int[0]);
        }
        return super.onBackPressed();
    }

    private boolean isDownloadedSelectOrSortStatus() {
        DownloadedCoursesListFragment downloadedCoursesListFragment = this.mDownloadedCoursesListFragment;
        if (downloadedCoursesListFragment == null) {
            return false;
        }
        if (downloadedCoursesListFragment.isMultipleSelectStatus()) {
            this.mDownloadedCoursesListFragment.setIsMultipleSelectStatus(false);
            return true;
        }
        if (this.mDownloadedCoursesListFragment.isSortStatus()) {
            this.mDownloadedCoursesListFragment.setIsSortStatus(false, false);
            return true;
        }
        LogUtil.h(TAG, "onBackPressed else");
        return false;
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mMediaType = arguments.getInt(MediaManager.KEY_MEDIA_TYPE);
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewGroup viewGroup2 = super.onCreateView(layoutInflater, viewGroup, bundle) instanceof ViewGroup ? (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle) : null;
        if (layoutInflater == null) {
            LogUtil.a(TAG, "onCreateView inflater is null");
            return viewGroup2;
        }
        this.child = layoutInflater.inflate(R.layout.fragment_course_list, viewGroup, false);
        if (viewGroup2 != null) {
            viewGroup2.addView(this.child);
            initView(this.child);
            initData();
        }
        return viewGroup2;
    }

    private void initView(View view) {
        this.mMediaManager = new MediaManager();
        this.mCustomTitleBar.setRightButtonVisibility(0);
        this.mCustomTitleBar.setRightButtonDrawable(this.mainActivity.getResources().getDrawable(R.drawable._2131430194_res_0x7f0b0b32), nsf.h(R.string._2130850635_res_0x7f02334b));
        this.mCustomTitleBar.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.VoiceCourseFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                VoiceCourseFragment.this.m403xd727b2c1(view2);
            }
        });
        this.mCourseRecommend = (HealthImageView) view.findViewById(R.id.iv_course_recommend);
        setBannerImgHeight();
        if (this.mMediaType == 0) {
            this.mCourseRecommend.setVisibility(0);
            this.mCourseRecommend.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.VoiceCourseFragment$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    VoiceCourseFragment.this.m404xf5e1ea82(view2);
                }
            });
        } else {
            initMarketing();
        }
        this.mLinearLayoutRemind = (LinearLayout) view.findViewById(R.id.linearlayout_rope_media_remind);
        this.mHealthTextViewRemind = (HealthTextView) view.findViewById(R.id.textview_rope_media_storage);
        this.mSubTabCoursePageSelect = (HealthSubTabWidget) view.findViewById(R.id.tl_course_page_select);
        HealthViewPager healthViewPager = (HealthViewPager) view.findViewById(R.id.viewpager_course_list);
        this.mViewPagerCourseList = healthViewPager;
        healthViewPager.addOnPageChangeListener(new AnonymousClass1());
    }

    /* renamed from: lambda$initView$0$com-huawei-health-ecologydevice-ui-measure-fragment-ropefragment-mediamanager-VoiceCourseFragment, reason: not valid java name */
    /* synthetic */ void m403xd727b2c1(View view) {
        showUnbindDeviceMenu(0);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: lambda$initView$1$com-huawei-health-ecologydevice-ui-measure-fragment-ropefragment-mediamanager-VoiceCourseFragment, reason: not valid java name */
    /* synthetic */ void m404xf5e1ea82(View view) {
        if (!CommonUtil.aa(this.mainActivity)) {
            LogUtil.h(TAG, "network is error");
            nrh.e(this.mainActivity, R.string._2130844160_res_0x7f021a00);
            ViewClickInstrumentation.clickOnView(view);
        } else {
            dij.a(BaseApplication.getContext(), this.mRopeBrMac, "#/?type=voiceCoursePage");
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* renamed from: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.VoiceCourseFragment$1, reason: invalid class name */
    class AnonymousClass1 implements HwViewPager.OnPageChangeListener {
        @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
        }

        AnonymousClass1() {
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
        public void onPageSelected(final int i) {
            VoiceCourseFragment.this.mPageSelected = i;
            VoiceCourseFragment.this.mCustomTitleBar.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.VoiceCourseFragment$1$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    VoiceCourseFragment.AnonymousClass1.this.m408xd5aafc42(i, view);
                }
            });
        }

        /* renamed from: lambda$onPageSelected$0$com-huawei-health-ecologydevice-ui-measure-fragment-ropefragment-mediamanager-VoiceCourseFragment$1, reason: not valid java name */
        /* synthetic */ void m408xd5aafc42(int i, View view) {
            VoiceCourseFragment.this.showUnbindDeviceMenu(i);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void setBannerImgHeight() {
        this.mMediaManager.setImageHeight(this.mainActivity, getResources().getDimensionPixelOffset(R.dimen._2131362886_res_0x7f0a0446), WIDTH_HEIGHT_PROPORTIONS, this.mCourseRecommend);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRemindMemory() {
        this.mHealthTextViewRemind.setText(getString(R.string._2130850434_res_0x7f023282, getResources().getQuantityString(R.plurals._2130903230_res_0x7f0300be, 5, this.mMediaManager.getRopeMemoryConvertMb(this.mRopeRemainingMemory))));
        AllCoursesListFragment allCoursesListFragment = this.mAllCoursesListFragment;
        if (allCoursesListFragment != null) {
            allCoursesListFragment.setRopeMemory(this.mMediaManager.getRopeMemoryConvertByte(this.mRopeRemainingMemory));
        }
    }

    private void initData() {
        dds.c().e(TAG, this, false);
        this.mCustomTitleBar.setTitleBarBackgroundColor(getResources().getColor(R.color._2131296690_res_0x7f0901b2));
        setTitle(getString(this.mMediaType == 0 ? R.string._2130850457_res_0x7f023299 : R.string._2130850426_res_0x7f02327a));
        this.mTabNewList.add(getString(this.mMediaType == 0 ? R.string._2130850451_res_0x7f023293 : R.string._2130850442_res_0x7f02328a));
        this.mTabNewList.add(getString(this.mMediaType == 0 ? R.string._2130850455_res_0x7f023297 : R.string._2130850441_res_0x7f023289));
        createFragments();
        dds.c().d(1, 2, new int[0]);
        downloadFile();
    }

    private void initMarketing() {
        final MarketingApi marketingApi = (MarketingApi) Services.c("FeatureMarketing", MarketingApi.class);
        Task<Map<Integer, ResourceResultInfo>> resourceResultInfo = marketingApi.getResourceResultInfo(0);
        if (resourceResultInfo == null) {
            LogUtil.h(TAG, "initMarketing task is null");
        } else {
            resourceResultInfo.addOnSuccessListener(new OnSuccessListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.VoiceCourseFragment$$ExternalSyntheticLambda5
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    VoiceCourseFragment.this.m402x52c16b8a(marketingApi, (Map) obj);
                }
            });
            resourceResultInfo.addOnFailureListener(new OnFailureListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.VoiceCourseFragment$$ExternalSyntheticLambda6
                @Override // com.huawei.hmf.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    VoiceCourseFragment.lambda$initMarketing$3(exc);
                }
            });
        }
    }

    /* renamed from: lambda$initMarketing$2$com-huawei-health-ecologydevice-ui-measure-fragment-ropefragment-mediamanager-VoiceCourseFragment, reason: not valid java name */
    /* synthetic */ void m402x52c16b8a(MarketingApi marketingApi, Map map) {
        List<View> marketingViewList = marketingApi.getMarketingViewList(this.mainActivity, marketingApi.filterMarketingRules((Map<Integer, ResourceResultInfo>) map));
        LinearLayout linearLayout = (LinearLayout) this.child.findViewById(R.id.second_rope_skip_course_marketing);
        if (koq.b(marketingViewList)) {
            LogUtil.h(TAG, "initMarketing list is empty");
            return;
        }
        Iterator<View> it = marketingViewList.iterator();
        while (it.hasNext()) {
            linearLayout.addView(it.next());
        }
        linearLayout.setVisibility(0);
    }

    static /* synthetic */ void lambda$initMarketing$3(Exception exc) {
        String str = TAG;
        Object[] objArr = new Object[2];
        objArr[0] = "initMarketing onFailure ";
        objArr[1] = exc == null ? "" : exc.getMessage();
        LogUtil.b(str, objArr);
    }

    private void createFragments() {
        this.mAllCoursesListFragment = new AllCoursesListFragment();
        Bundle bundle = new Bundle(5);
        bundle.putInt(MediaManager.KEY_MEDIA_TYPE, this.mMediaType);
        bundle.putLong(MediaManager.KEY_REMAINING_MEMORY, this.mMediaManager.getRopeMemoryConvertByte(this.mRopeRemainingMemory));
        this.mAllCoursesListFragment.setArguments(bundle);
        this.mAllCoursesListFragment.setOnMediaItemClick(this);
        DownloadedCoursesListFragment downloadedCoursesListFragment = new DownloadedCoursesListFragment();
        this.mDownloadedCoursesListFragment = downloadedCoursesListFragment;
        downloadedCoursesListFragment.setOnDownloadedListener(this);
        Bundle bundle2 = new Bundle(5);
        bundle2.putInt(MediaManager.KEY_MEDIA_TYPE, this.mMediaType);
        this.mDownloadedCoursesListFragment.setArguments(bundle2);
        this.mFragments.add(this.mAllCoursesListFragment);
        this.mFragments.add(this.mDownloadedCoursesListFragment);
        this.mVoiceCourseTabAdapter = new nqx(getActivity(), this.mViewPagerCourseList, this.mSubTabCoursePageSelect);
        int i = 0;
        while (i < this.mTabNewList.size()) {
            this.mVoiceCourseTabAdapter.c(this.mSubTabCoursePageSelect.c(this.mTabNewList.get(i)), this.mFragments.get(i), i == 0);
            i++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsNeedUpdateProfile() {
        CourseModel courseModel;
        String a2 = msb.a(new File(MediaManager.ROPE_JSON_FILE, this.mMediaType == 0 ? MediaManager.ROPE_MEDIA_COURSE_JSON : MediaManager.ROPE_MEDIA_MUSIC_JSON));
        if (TextUtils.isEmpty(a2) || (courseModel = (CourseModel) new Gson().fromJson(a2, new TypeToken<CourseModel>() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.VoiceCourseFragment.2
        }.getType())) == null) {
            return;
        }
        if (this.mMediaType == 0) {
            Glide.with(this.mainActivity).load(courseModel.getBannerImgUrl()).transform(new RoundedCorners(getResources().getDimensionPixelOffset(R.dimen._2131362914_res_0x7f0a0462))).into(this.mCourseRecommend);
        }
        this.mProfileVersion = courseModel.getVersion();
    }

    private boolean isNeedUpdateProfile() {
        boolean z = this.mProfileVersion != (this.mMediaType == 0 ? this.mRopeCourseProfileVersion : this.mRopeMusicProfileVersion);
        LogUtil.a(TAG, "isNeedUpdateProfile is ", Boolean.valueOf(z));
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showUnbindDeviceMenu(int i) {
        if (i == 0) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(getString(R.string._2130850435_res_0x7f023283));
            PopViewList.PopViewClickListener popViewClickListener = new PopViewList.PopViewClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.VoiceCourseFragment$$ExternalSyntheticLambda3
                @Override // com.huawei.ui.commonui.popupview.PopViewList.PopViewClickListener
                public final void setOnClick(int i2) {
                    VoiceCourseFragment.this.m406x5cf6a5d8(i2);
                }
            };
            PopViewList popViewList = new PopViewList(this.mainActivity, this.mCustomTitleBar, arrayList);
            this.mPopViewList = popViewList;
            popViewList.e(popViewClickListener);
            return;
        }
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(getString(R.string._2130841406_res_0x7f020f3e));
        arrayList2.add(getString(R.string._2130841397_res_0x7f020f35));
        PopViewList.PopViewClickListener popViewClickListener2 = new PopViewList.PopViewClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.VoiceCourseFragment$$ExternalSyntheticLambda4
            @Override // com.huawei.ui.commonui.popupview.PopViewList.PopViewClickListener
            public final void setOnClick(int i2) {
                VoiceCourseFragment.this.m407x7bb0dd99(i2);
            }
        };
        PopViewList popViewList2 = new PopViewList(this.mainActivity, this.mCustomTitleBar, arrayList2);
        this.mPopViewList = popViewList2;
        popViewList2.e(popViewClickListener2);
    }

    /* renamed from: lambda$showUnbindDeviceMenu$4$com-huawei-health-ecologydevice-ui-measure-fragment-ropefragment-mediamanager-VoiceCourseFragment, reason: not valid java name */
    /* synthetic */ void m406x5cf6a5d8(int i) {
        AllCoursesListFragment allCoursesListFragment = this.mAllCoursesListFragment;
        if (allCoursesListFragment == null) {
            return;
        }
        allCoursesListFragment.setIsMultipleSelectStatus(true);
    }

    /* renamed from: lambda$showUnbindDeviceMenu$5$com-huawei-health-ecologydevice-ui-measure-fragment-ropefragment-mediamanager-VoiceCourseFragment, reason: not valid java name */
    /* synthetic */ void m407x7bb0dd99(int i) {
        DownloadedCoursesListFragment downloadedCoursesListFragment = this.mDownloadedCoursesListFragment;
        if (downloadedCoursesListFragment == null) {
            return;
        }
        if (i == 0) {
            downloadedCoursesListFragment.setIsSortStatus(true, false);
        } else {
            downloadedCoursesListFragment.setIsMultipleSelectStatus(true);
        }
    }

    private void downloadFile() {
        if (!CommonUtil.aa(BaseApplication.getContext())) {
            LogUtil.h(TAG, "network is error");
            nrh.e(this.mainActivity, R.string._2130844160_res_0x7f021a00);
            setIsNeedUpdateProfile();
            return;
        }
        this.mMediaManager.downloadJsonFile(this.mMediaType, false, new CommonUiResponse() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.VoiceCourseFragment$$ExternalSyntheticLambda2
            @Override // com.huawei.health.ecologydevice.open.data.model.CommonUiResponse
            public final void onResponse(int i, Object obj) {
                VoiceCourseFragment.this.m400x28f0c996(i, (String) obj);
            }
        });
    }

    /* renamed from: lambda$downloadFile$6$com-huawei-health-ecologydevice-ui-measure-fragment-ropefragment-mediamanager-VoiceCourseFragment, reason: not valid java name */
    /* synthetic */ void m400x28f0c996(int i, String str) {
        sendEmptyHandleMessage(10001);
    }

    @Override // com.huawei.btsportdevice.callback.MessageOrStateCallback
    public void onNewMessage(int i, Bundle bundle) {
        HashMap<Integer, Object> hashMap;
        if (this.mHandler == null) {
            LogUtil.h(TAG, "onNewMessage mHandler is null");
            return;
        }
        if (i != 902 || bundle == null) {
            return;
        }
        int i2 = bundle.getInt("com.huawei.health.fitness.KEY_MESSAGE_FITNESS_DATA_TYPE");
        String str = TAG;
        LogUtil.a(str, "code is ", Integer.valueOf(i), " dataType is ", Integer.valueOf(i2));
        Serializable serializable = bundle.getSerializable("com.huawei.health.fitness.KEY_MESSAGE_FOR_CALLBACK_FITNESS_DATA");
        if ((serializable instanceof HashMap) && (hashMap = (HashMap) serializable) != null) {
            LogUtil.a(str, "onNewMessage fitnessData");
            onReceiveMessage(i2, hashMap);
        }
    }

    private void onReceiveMessage(int i, HashMap<Integer, Object> hashMap) {
        if (i == 25) {
            String stringValue = getStringValue(hashMap, 40022);
            this.mRopeBrMac = stringValue;
            this.mRopeBrMac = TextUtils.isEmpty(stringValue) ? "" : this.mRopeBrMac.toUpperCase(Locale.ENGLISH);
            return;
        }
        if (i == 28) {
            if (hashMap.get(40045) != null) {
                this.mRopeCourseProfileVersion = this.mMediaManager.getIntValue(hashMap, 40045);
            }
            if (hashMap.get(40046) != null) {
                this.mRopeRemainingMemory = this.mMediaManager.getIntValue(hashMap, 40046);
                sendEmptyHandleMessage(10003);
            }
            if (hashMap.get(40047) != null) {
                refreshDownloadedList(hashMap, 40047);
            }
            LogUtil.a(TAG, "onReceiveMessage mRopeCourseProfileVersion = ", Integer.valueOf(this.mRopeCourseProfileVersion), ", mRopeRemainingMemory = ", Integer.valueOf(this.mRopeRemainingMemory));
            return;
        }
        if (i != 31) {
            return;
        }
        this.mRopeMusicProfileVersion = this.mMediaManager.getIntValue(hashMap, 40067);
        if (hashMap.get(40068) != null) {
            this.mRopeRemainingMemory = this.mMediaManager.getIntValue(hashMap, 40068);
            sendEmptyHandleMessage(10003);
        }
        if (hashMap.get(40066) != null) {
            refreshDownloadedList(hashMap, 40066);
        }
        LogUtil.a(TAG, "onReceiveMessage mRopeMusicProfileVersion = ", Integer.valueOf(this.mRopeMusicProfileVersion), ", mRopeRemainingMemory = ", Integer.valueOf(this.mRopeRemainingMemory));
    }

    private void refreshDownloadedList(HashMap<Integer, Object> hashMap, int i) {
        LogUtil.a(TAG, "refreshDownloadedList");
        ArrayList<CourseModel.Course> arrayList = (ArrayList) getValue(hashMap, i);
        if ((arrayList != null && this.mRopeMediaList != null && arrayList.size() != this.mRopeMediaList.size()) || this.mIsGetMemory) {
            getRopeMemory();
        }
        this.mRopeMediaList = arrayList;
        sendEmptyHandleMessage(10002);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshDownloadedFragment() {
        if (koq.b(this.mRopeMediaList)) {
            LogUtil.a(TAG, "onReceiveMessage refreshDownloadedFragment size is null");
        } else {
            LogUtil.a(TAG, "onReceiveMessage refreshDownloadedFragment size = ", Integer.valueOf(this.mRopeMediaList.size()));
        }
        AllCoursesListFragment allCoursesListFragment = this.mAllCoursesListFragment;
        if (allCoursesListFragment != null) {
            allCoursesListFragment.refreshDataAdapter(this.mRopeMediaList);
        }
        DownloadedCoursesListFragment downloadedCoursesListFragment = this.mDownloadedCoursesListFragment;
        if (downloadedCoursesListFragment != null) {
            downloadedCoursesListFragment.refreshDataAdapter(this.mRopeMediaList);
        }
    }

    private String getStringValue(HashMap<Integer, Object> hashMap, int i) {
        return (hashMap.get(Integer.valueOf(i)) == null || !(hashMap.get(Integer.valueOf(i)) instanceof String)) ? "" : (String) hashMap.get(Integer.valueOf(i));
    }

    private <T> T getValue(HashMap<Integer, Object> hashMap, int i) {
        if (hashMap.get(Integer.valueOf(i)) != null) {
            return (T) hashMap.get(Integer.valueOf(i));
        }
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        dds.c().c(TAG, false);
        removeHandleMessage();
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.AllCoursesListFragment.OnMediaItemClick
    public void onItemClick(CourseModel.Course course) {
        Bundle bundle = new Bundle(5);
        bundle.putInt(MediaManager.KEY_MEDIA_TYPE, this.mMediaType);
        bundle.putLong(MediaManager.KEY_REMAINING_MEMORY, this.mMediaManager.getRopeMemoryConvertByte(this.mRopeRemainingMemory));
        bundle.putString(RopeCourseDetailFragment.KEY_ROPE_BR_MAC, this.mRopeBrMac);
        bundle.putBoolean(RopeCourseDetailFragment.KEY_IS_UPDATE_ROPE_BIN, isNeedUpdateProfile());
        bundle.putParcelable(RopeCourseDetailFragment.KEY_MEDIA, course);
        RopeCourseDetailFragment ropeCourseDetailFragment = new RopeCourseDetailFragment();
        ropeCourseDetailFragment.setArguments(bundle);
        addFragment(ropeCourseDetailFragment);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.AllCoursesListFragment.OnMediaItemClick
    public void onDownloadClick(List<CourseModel.Course> list, String str) {
        jumpToH5Page(list, str);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.AllCoursesListFragment.OnMediaItemClick
    public void onGotoDownloadedClick() {
        if (koq.b(this.mFragments)) {
            return;
        }
        this.mViewPagerCourseList.setCurrentItem(1);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.DownloadedCoursesListFragment.OnDownloadedListener
    public void onGotoAllClick() {
        if (koq.b(this.mFragments)) {
            return;
        }
        this.mViewPagerCourseList.setCurrentItem(0);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.DownloadedCoursesListFragment.OnDownloadedListener
    public void onDownloadedItemClick(CourseModel.Course course) {
        onItemClick(course);
    }

    private void jumpToH5Page(List<CourseModel.Course> list, String str) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        for (CourseModel.Course course : list) {
            if (sb.length() > 0) {
                sb.append("_");
                sb.append(course.getId());
            } else {
                sb.append(course.getId());
            }
            if (sb2.length() > 0) {
                sb2.append("_");
                sb2.append(course.getName());
            } else {
                sb2.append(course.getName());
            }
            if (sb3.length() > 0) {
                sb3.append("_");
                sb3.append(course.getShortDesc());
            } else {
                sb3.append(course.getShortDesc());
            }
        }
        StringBuilder sb4 = new StringBuilder("#/?type=");
        sb4.append(this.mMediaType == 0 ? "voiceCourseFileDownloadPage" : "songDownloadPage");
        sb4.append("&isUpdateRopeBin=");
        sb4.append(isNeedUpdateProfile());
        sb4.append("&quality=");
        sb4.append(str);
        sb4.append("&id=");
        sb4.append(sb.toString());
        sb4.append("&name=");
        sb4.append(sb2.toString());
        sb4.append("&short_desc=");
        sb4.append(sb3.toString());
        dij.a(this.mainActivity, this.mRopeBrMac, sb4.toString());
    }

    private void sendEmptyHandleMessage(int i) {
        VoiceCourseFragmentHandler voiceCourseFragmentHandler = this.mHandler;
        if (voiceCourseFragmentHandler != null) {
            voiceCourseFragmentHandler.sendEmptyMessage(i);
        }
    }

    static class VoiceCourseFragmentHandler extends Handler {
        private WeakReference<VoiceCourseFragment> mFragment;

        /* synthetic */ VoiceCourseFragmentHandler(VoiceCourseFragment voiceCourseFragment, AnonymousClass1 anonymousClass1) {
            this(voiceCourseFragment);
        }

        private VoiceCourseFragmentHandler(VoiceCourseFragment voiceCourseFragment) {
            this.mFragment = new WeakReference<>(voiceCourseFragment);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            VoiceCourseFragment voiceCourseFragment = this.mFragment.get();
            if (voiceCourseFragment == null || !voiceCourseFragment.isAdded() || voiceCourseFragment.isRemoving() || voiceCourseFragment.isDetached()) {
                return;
            }
            switch (message.what) {
                case 10001:
                    LogUtil.a(VoiceCourseFragment.TAG, "handleMessage DOWNLOAD_PROFILE_SUCCESS");
                    voiceCourseFragment.refreshDownloadedFragment();
                    voiceCourseFragment.setIsNeedUpdateProfile();
                    break;
                case 10002:
                    LogUtil.a(VoiceCourseFragment.TAG, "handleMessage REFRESH_DOWNLOADED_FRAGMENT");
                    voiceCourseFragment.refreshDownloadedFragment();
                    break;
                case 10003:
                    LogUtil.a(VoiceCourseFragment.TAG, "handleMessage SET_REMIND_MEMORY");
                    voiceCourseFragment.setRemindMemory();
                    break;
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        AllCoursesListFragment allCoursesListFragment = this.mAllCoursesListFragment;
        if (allCoursesListFragment != null && allCoursesListFragment.isMultipleSelectStatus()) {
            this.mAllCoursesListFragment.setIsMultipleSelectStatus(false);
        }
        this.mHandler.postDelayed(new Runnable() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.VoiceCourseFragment$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                VoiceCourseFragment.this.m405x1b4520dc();
            }
        }, 100L);
        getRopeCourseList();
        this.mIsGetMemory = true;
    }

    /* renamed from: lambda$onStart$7$com-huawei-health-ecologydevice-ui-measure-fragment-ropefragment-mediamanager-VoiceCourseFragment, reason: not valid java name */
    /* synthetic */ void m405x1b4520dc() {
        dds c = dds.c();
        int i = this.mMediaType;
        c.d(i == 0 ? 4 : 7, i != 0 ? 6 : 7, new int[0]);
    }

    private void getRopeCourseList() {
        LogUtil.a(TAG, "getRopeCourseList");
        this.mHandler.postDelayed(new Runnable() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.VoiceCourseFragment$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                VoiceCourseFragment.this.m401xc6750afb();
            }
        }, 200L);
    }

    /* renamed from: lambda$getRopeCourseList$8$com-huawei-health-ecologydevice-ui-measure-fragment-ropefragment-mediamanager-VoiceCourseFragment, reason: not valid java name */
    /* synthetic */ void m401xc6750afb() {
        dds.c().d(this.mMediaType == 0 ? 4 : 7, 0, new int[0]);
    }

    private void getRopeMemory() {
        LogUtil.a(TAG, "getRopeMemory");
        dds c = dds.c();
        int i = this.mMediaType;
        c.d(i == 0 ? 4 : 7, i == 0 ? 8 : 7, new int[0]);
        this.mIsGetMemory = false;
    }

    private void removeHandleMessage() {
        VoiceCourseFragmentHandler voiceCourseFragmentHandler = this.mHandler;
        if (voiceCourseFragmentHandler != null) {
            voiceCourseFragmentHandler.removeCallbacksAndMessages(null);
            this.mHandler = null;
        }
    }
}
