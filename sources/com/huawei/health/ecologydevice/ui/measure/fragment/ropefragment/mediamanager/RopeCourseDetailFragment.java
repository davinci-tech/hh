package com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.bumptech.glide.Glide;
import com.huawei.btsportdevice.callback.MessageOrStateCallback;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.open.data.model.CommonUiResponse;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.CourseModel;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.dds;
import defpackage.dij;
import defpackage.diy;
import defpackage.dju;
import defpackage.dko;
import defpackage.nrh;
import defpackage.nsn;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class RopeCourseDetailFragment extends BaseFragment implements MessageOrStateCallback {
    private static final int AUDITION_FAILURE = 20001;
    private static final int DELAY_MILLIS_TWO_HUNDRED = 200;
    public static final String KEY_IS_UPDATE_ROPE_BIN = "key_is_update_rope_bin";
    public static final String KEY_MEDIA = "key_media";
    public static final String KEY_ROPE_BR_MAC = "key_rope_br_mac";
    private static final String TAG = "RopeCourseDetailFragment";
    private static final float WIDTH_HEIGHT_PROPORTIONS = 0.5f;
    private CourseModel.Course mCourse;
    private RopeCourseDetailFragmentHandler mHandler = new RopeCourseDetailFragmentHandler();
    private HealthButton mHealthButton;
    private HealthButton mHealthButtonAudition;
    private boolean mIsNeedUpdateProfile;
    private HealthImageView mLargerImage;
    private LinearLayout mLlAudition;
    private LinearLayout mLlBtnSingle;
    private MediaManager mMediaManager;
    private int mMediaType;
    private String mRopeBrMac;
    private long mRopeRemainingMemory;
    private HealthSubHeader mSubHeaderDescription;
    private HealthSubHeader mSubHeaderPopulations;
    private HealthSubHeader mSubHeaderPrecautions;
    private HealthSubHeader mSubHeaderSize;
    private HealthSubHeader mSubHeaderSuggestions;
    private HealthSubHeader mSubHeaderTaboo;
    private HealthTextView mTextViewHighSize;
    private HealthTextView mTextViewSize;
    private HealthTextView mTvDescription;
    private HealthTextView mTvImgItemEnd;
    private HealthTextView mTvImgItemEndTitle;
    private HealthTextView mTvImgItemMiddle;
    private HealthTextView mTvImgItemStart;
    private HealthTextView mTvImgShortNotice;
    private HealthTextView mTvPopulations;
    private HealthTextView mTvPrecautions;
    private HealthTextView mTvSuggestions;
    private HealthTextView mTvTaboo;

    @Override // com.huawei.btsportdevice.callback.MessageOrStateCallback
    public void onStateChange(String str) {
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mMediaType = arguments.getInt(MediaManager.KEY_MEDIA_TYPE);
            this.mRopeRemainingMemory = arguments.getLong(MediaManager.KEY_REMAINING_MEMORY);
            this.mRopeBrMac = arguments.getString(KEY_ROPE_BR_MAC);
            this.mIsNeedUpdateProfile = arguments.getBoolean(KEY_IS_UPDATE_ROPE_BIN);
            this.mCourse = (CourseModel.Course) arguments.getParcelable(KEY_MEDIA);
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewGroup viewGroup2 = super.onCreateView(layoutInflater, viewGroup, bundle) instanceof ViewGroup ? (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle) : null;
        if (layoutInflater == null) {
            LogUtil.a(TAG, "RopeCourseDetailFragment onCreateView inflater is null");
            return viewGroup2;
        }
        this.child = layoutInflater.inflate(R.layout.fragment_course_detail, viewGroup, false);
        if (viewGroup2 != null) {
            viewGroup2.addView(this.child);
            initView(this.child);
            initData();
        }
        return viewGroup2;
    }

    private void initView(View view) {
        CourseModel.Course course;
        this.mMediaManager = new MediaManager();
        this.mLargerImage = (HealthImageView) view.findViewById(R.id.rope_media_lager_img);
        setLargerImgHeight();
        this.mTvImgShortNotice = (HealthTextView) view.findViewById(R.id.rope_media_short_notice);
        this.mTvImgItemStart = (HealthTextView) view.findViewById(R.id.item_tv_start_val);
        this.mTvImgItemMiddle = (HealthTextView) view.findViewById(R.id.item_tv_middle_val);
        this.mTvImgItemEndTitle = (HealthTextView) view.findViewById(R.id.item_tv_end);
        this.mTvImgItemEnd = (HealthTextView) view.findViewById(R.id.item_tv_end_val);
        this.mSubHeaderDescription = (HealthSubHeader) view.findViewById(R.id.rope_media_detail);
        this.mTvDescription = (HealthTextView) view.findViewById(R.id.rope_media_textview_detail);
        this.mSubHeaderSuggestions = (HealthSubHeader) view.findViewById(R.id.rope_media_suggestions);
        this.mTvSuggestions = (HealthTextView) view.findViewById(R.id.rope_media_textview_suggestions);
        this.mSubHeaderPopulations = (HealthSubHeader) view.findViewById(R.id.rope_media_populations);
        this.mTvPopulations = (HealthTextView) view.findViewById(R.id.rope_media_textview_populations);
        this.mSubHeaderTaboo = (HealthSubHeader) view.findViewById(R.id.rope_media_taboo_people);
        this.mTvTaboo = (HealthTextView) view.findViewById(R.id.rope_media_textview_taboo_people);
        this.mSubHeaderSize = (HealthSubHeader) view.findViewById(R.id.rope_media_textview_size_title);
        this.mTextViewSize = (HealthTextView) view.findViewById(R.id.rope_media_textview_size);
        this.mTextViewHighSize = (HealthTextView) view.findViewById(R.id.rope_media_textview_high_size);
        this.mSubHeaderPrecautions = (HealthSubHeader) view.findViewById(R.id.rope_media_precautions);
        this.mTvPrecautions = (HealthTextView) view.findViewById(R.id.rope_media_textview_precautions);
        if (this.mMediaType == 1 && (course = this.mCourse) != null && course.getStatus() == 2) {
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ll_course_detail_btn);
            this.mLlAudition = linearLayout;
            linearLayout.setVisibility(0);
            this.mHealthButton = (HealthButton) view.findViewById(R.id.rope_media_download);
            HealthButton healthButton = (HealthButton) view.findViewById(R.id.rope_media_audition);
            this.mHealthButtonAudition = healthButton;
            healthButton.setVisibility(0);
            this.mHealthButtonAudition.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.RopeCourseDetailFragment$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    RopeCourseDetailFragment.this.m397xeb8c3bea(view2);
                }
            });
            return;
        }
        LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.ll_course_detail_btn_single);
        this.mLlBtnSingle = linearLayout2;
        linearLayout2.setVisibility(0);
        this.mHealthButton = (HealthButton) view.findViewById(R.id.rope_media_download_single);
    }

    /* renamed from: lambda$initView$0$com-huawei-health-ecologydevice-ui-measure-fragment-ropefragment-mediamanager-RopeCourseDetailFragment, reason: not valid java name */
    /* synthetic */ void m397xeb8c3bea(View view) {
        if (!this.mMediaManager.isRopeDeviceConnected(this.mainActivity)) {
            ViewClickInstrumentation.clickOnView(view);
        } else {
            dds.c().d(7, 5, new int[]{this.mCourse.getId()});
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void initData() {
        CourseModel.Course course = this.mCourse;
        if (course == null) {
            return;
        }
        setTitle(course.getName());
        setImageData();
        this.mSubHeaderDescription.setHeadTitleText(getString(this.mMediaType == 0 ? R.string._2130850420_res_0x7f023274 : R.string._2130850402_res_0x7f023262));
        this.mTvDescription.setText(this.mCourse.getDetailDesc());
        if (!TextUtils.isEmpty(this.mCourse.getSuggestion())) {
            this.mSubHeaderSuggestions.setVisibility(0);
            this.mTvSuggestions.setVisibility(0);
            this.mTvSuggestions.setText(this.mCourse.getSuggestion());
        }
        if (!TextUtils.isEmpty(this.mCourse.getPeople())) {
            this.mSubHeaderPopulations.setVisibility(0);
            this.mTvPopulations.setVisibility(0);
            this.mTvPopulations.setText(this.mCourse.getPeople());
        }
        if (!TextUtils.isEmpty(this.mCourse.getTaboo())) {
            this.mSubHeaderTaboo.setVisibility(0);
            this.mTvTaboo.setVisibility(0);
            this.mTvTaboo.setText(this.mCourse.getTaboo());
        }
        if (!TextUtils.isEmpty(this.mCourse.getNotice())) {
            this.mSubHeaderPrecautions.setVisibility(0);
            this.mTvPrecautions.setVisibility(0);
            this.mTvPrecautions.setText(this.mCourse.getNotice());
        }
        this.mSubHeaderSize.setHeadTitleText(getString(this.mMediaType == 0 ? R.string._2130850422_res_0x7f023276 : R.string._2130850403_res_0x7f023263));
        this.mTextViewSize.setText(getResources().getString(R.string._2130850433_res_0x7f023281) + " " + getResources().getQuantityString(R.plurals._2130903230_res_0x7f0300be, 0, this.mMediaManager.getByteConvertMb(this.mCourse.getFileSize())));
        this.mTextViewHighSize.setText(getResources().getString(R.string._2130850431_res_0x7f02327f) + " " + getResources().getQuantityString(R.plurals._2130903230_res_0x7f0300be, 0, this.mMediaManager.getByteConvertMb(this.mCourse.getHighQualitySize())));
        setButtonTextAndClickListener();
        dds.c().e(TAG, this, false);
    }

    private void setImageData() {
        CharSequence spannableString;
        Glide.with(this.mainActivity).load(this.mCourse.getLargeImgUrl()).into(this.mLargerImage);
        this.mTvImgShortNotice.setText(this.mCourse.getShortDesc());
        this.mTvImgItemStart.setText(TextUtils.isEmpty(this.mCourse.getLevel()) ? getString(R.string._2130851561_res_0x7f0236e9) : this.mMediaManager.getSpannableString(this.mainActivity, "L[1-4]{1}", this.mCourse.getLevel()));
        this.mTvImgItemMiddle.setText(this.mCourse.getTime() == 0 ? getString(R.string._2130851561_res_0x7f0236e9) : this.mMediaManager.getSpannableString(this.mainActivity, "\\d+.\\d+|\\d+", getResources().getQuantityString(R.plurals._2130903305_res_0x7f030109, (int) (this.mCourse.getTime() / 60.0f), dju.a(this.mCourse.getTime()))));
        if (this.mMediaType == 0) {
            this.mTvImgItemEndTitle.setText(getString(R.string._2130840229_res_0x7f020aa5));
            HealthTextView healthTextView = this.mTvImgItemEnd;
            if (TextUtils.isEmpty(this.mCourse.getCal())) {
                spannableString = getString(R.string._2130851561_res_0x7f0236e9);
            } else {
                spannableString = this.mMediaManager.getSpannableString(this.mainActivity, "\\d|[/]", this.mCourse.getCal() + " " + getString(R.string._2130841384_res_0x7f020f28));
            }
            healthTextView.setText(spannableString);
            return;
        }
        this.mTvImgItemEndTitle.setText(getString(R.string._2130844076_res_0x7f0219ac));
        this.mTvImgItemEnd.setText(this.mCourse.getBpm() == 0 ? getString(R.string._2130851561_res_0x7f0236e9) : this.mMediaManager.getSpannableString(this.mainActivity, "\\d|[/]", getResources().getQuantityString(R.plurals._2130903519_res_0x7f0301df, 0, Integer.valueOf(this.mCourse.getBpm()))));
    }

    private void setButtonTextAndClickListener() {
        if (this.mCourse.getStatus() == 2) {
            this.mHealthButton.setText(getString(R.string.IDS_course_device_connect_dialog_negative));
        } else if (this.mCourse.getStatus() == 3) {
            this.mHealthButton.setText(getString(R.string._2130850429_res_0x7f02327d));
            this.mHealthButton.setEnabled(false);
        } else {
            this.mHealthButton.setText(getString(R.string._2130850454_res_0x7f023296));
        }
        this.mHealthButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.RopeCourseDetailFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RopeCourseDetailFragment.this.m399x8f400972(view);
            }
        });
    }

    /* renamed from: lambda$setButtonTextAndClickListener$1$com-huawei-health-ecologydevice-ui-measure-fragment-ropefragment-mediamanager-RopeCourseDetailFragment, reason: not valid java name */
    /* synthetic */ void m399x8f400972(View view) {
        if (this.mCourse.getStatus() == 2) {
            if (!this.mMediaManager.isRopeDeviceConnected(this.mainActivity)) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            diy.a(this.mainActivity, this.mMediaType == 0 ? 10 : 11, this.mCourse.getId(), null, dds.c().j());
        } else {
            downloadMedia();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void setLargerImgHeight() {
        if (nsn.l()) {
            return;
        }
        this.mMediaManager.setImageHeight(this.mainActivity, 0, 0.5f, this.mLargerImage);
    }

    private void downloadMedia() {
        this.mMediaManager.showRadioButtonSelectDialog(this.mainActivity, this.mMediaManager.getSelectHashMap(this.mainActivity), new CommonUiResponse() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.RopeCourseDetailFragment$$ExternalSyntheticLambda2
            @Override // com.huawei.health.ecologydevice.open.data.model.CommonUiResponse
            public final void onResponse(int i, Object obj) {
                RopeCourseDetailFragment.this.m396x4b89d31a(i, (String) obj);
            }
        });
    }

    /* renamed from: lambda$downloadMedia$3$com-huawei-health-ecologydevice-ui-measure-fragment-ropefragment-mediamanager-RopeCourseDetailFragment, reason: not valid java name */
    /* synthetic */ void m396x4b89d31a(int i, final String str) {
        this.mMediaManager.showDownloadDialog(this.mainActivity, this.mMediaType, this.mRopeRemainingMemory, MediaManager.ROPE_MEDIA_STANDARD.equals(str) ? this.mCourse.getFileSize() : this.mCourse.getHighQualitySize(), new CommonUiResponse() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.RopeCourseDetailFragment$$ExternalSyntheticLambda3
            @Override // com.huawei.health.ecologydevice.open.data.model.CommonUiResponse
            public final void onResponse(int i2, Object obj) {
                RopeCourseDetailFragment.this.m395x22225a3b(str, i2, (String) obj);
            }
        });
    }

    /* renamed from: lambda$downloadMedia$2$com-huawei-health-ecologydevice-ui-measure-fragment-ropefragment-mediamanager-RopeCourseDetailFragment, reason: not valid java name */
    /* synthetic */ void m395x22225a3b(String str, int i, String str2) {
        addCourseBiEvent();
        jumpToH5Page(str);
    }

    private void addCourseBiEvent() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(Integer.valueOf(this.mCourse.getId()));
        dko.b(arrayList.toString(), "add");
    }

    private void jumpToH5Page(String str) {
        StringBuilder sb = new StringBuilder("#/?type=");
        sb.append(this.mMediaType == 0 ? "voiceCourseFileDownloadPage" : "songDownloadPage");
        sb.append("&isUpdateRopeBin=");
        sb.append(this.mIsNeedUpdateProfile);
        sb.append("&quality=");
        sb.append(str);
        sb.append("&id=");
        sb.append(this.mCourse.getId());
        sb.append("&name=");
        sb.append(this.mCourse.getName());
        sb.append("&short_desc=");
        sb.append(this.mCourse.getShortDesc());
        dij.a(BaseApplication.getContext(), this.mRopeBrMac, sb.toString());
        new Handler().postDelayed(new Runnable() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.RopeCourseDetailFragment$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                RopeCourseDetailFragment.this.m398x3d3b9a16();
            }
        }, 200L);
    }

    /* renamed from: lambda$jumpToH5Page$4$com-huawei-health-ecologydevice-ui-measure-fragment-ropefragment-mediamanager-RopeCourseDetailFragment, reason: not valid java name */
    /* synthetic */ void m398x3d3b9a16() {
        this.mainActivity.onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showAuditionFailureToast() {
        nrh.d(this.mainActivity, getString(R.string._2130850404_res_0x7f023264));
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
        if (i == 31 && this.mMediaManager.getIntValue(hashMap, 20008) == 5) {
            int intValue = this.mMediaManager.getIntValue(hashMap, 40070);
            LogUtil.a(TAG, "onReceiveMessage musicId is ", Integer.valueOf(intValue));
            if (intValue == 0) {
                sendEmptyHandleMessage(20001);
            }
        }
    }

    private void sendEmptyHandleMessage(int i) {
        RopeCourseDetailFragmentHandler ropeCourseDetailFragmentHandler = this.mHandler;
        if (ropeCourseDetailFragmentHandler != null) {
            ropeCourseDetailFragmentHandler.sendEmptyMessage(i);
        }
    }

    static class RopeCourseDetailFragmentHandler extends Handler {
        private WeakReference<RopeCourseDetailFragment> mFragment;

        private RopeCourseDetailFragmentHandler(RopeCourseDetailFragment ropeCourseDetailFragment) {
            this.mFragment = new WeakReference<>(ropeCourseDetailFragment);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            RopeCourseDetailFragment ropeCourseDetailFragment = this.mFragment.get();
            if (ropeCourseDetailFragment == null || !ropeCourseDetailFragment.isAdded() || ropeCourseDetailFragment.isRemoving() || ropeCourseDetailFragment.isDetached() || message.what != 20001) {
                return;
            }
            ropeCourseDetailFragment.showAuditionFailureToast();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        dds.c().c(TAG, false);
        RopeCourseDetailFragmentHandler ropeCourseDetailFragmentHandler = this.mHandler;
        if (ropeCourseDetailFragmentHandler != null) {
            ropeCourseDetailFragmentHandler.removeCallbacksAndMessages(null);
            this.mHandler = null;
        }
    }
}
