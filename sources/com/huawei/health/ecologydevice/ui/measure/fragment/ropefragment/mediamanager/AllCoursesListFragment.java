package com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.open.data.model.CommonUiResponse;
import com.huawei.health.ecologydevice.ui.measure.adapter.voicecourse.VoiceCourseRecyclerViewAdapter;
import com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.CourseModel;
import com.huawei.hianalytics.visual.autocollect.instrument.FragmentInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.dko;
import defpackage.koq;
import defpackage.msb;
import defpackage.nsn;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class AllCoursesListFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "AllRopeMediaFragment";
    private Activity mContext;
    private ImageView mImgSelectAll;
    private LinearLayout mLlRootSelectAll;
    private List<CourseModel.Course> mMediaList = new ArrayList();
    private MediaManager mMediaManager;
    private int mMediaType;
    private OnMediaItemClick mOnMediaItemClick;
    private LinearLayout mRbDownloadCourse;
    private LinearLayout mRbSelectAll;
    private VoiceCourseRecyclerViewAdapter mRecycleViewAdapter;
    private RecyclerView mRecycleViewCourseList;
    private ArrayList<CourseModel.Course> mRopeMediaList;
    private long mRopeRemainingMemory;
    private HealthTextView mTvSelectAll;

    public interface OnMediaItemClick {
        void onDownloadClick(List<CourseModel.Course> list, String str);

        void onGotoDownloadedClick();

        void onItemClick(CourseModel.Course course);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mMediaType = arguments.getInt(MediaManager.KEY_MEDIA_TYPE);
            this.mRopeRemainingMemory = arguments.getLong(MediaManager.KEY_REMAINING_MEMORY);
            this.mRopeMediaList = arguments.getParcelableArrayList(MediaManager.KEY_MEDIA_LIST);
        }
        this.mContext = getActivity();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        LogUtil.a(TAG, "onCreateView");
        View inflate = layoutInflater.inflate(R.layout.all_courses_list_fragment, viewGroup, false);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initView(View view) {
        this.mMediaManager = new MediaManager();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_courses_list);
        this.mRecycleViewCourseList = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mLlRootSelectAll = (LinearLayout) view.findViewById(R.id.ll_root_select_all);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.rb_download);
        this.mRbDownloadCourse = linearLayout;
        linearLayout.setOnClickListener(this);
        LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.rb_select_all);
        this.mRbSelectAll = linearLayout2;
        linearLayout2.setOnClickListener(this);
        this.mImgSelectAll = (ImageView) view.findViewById(R.id.img_select_all);
        this.mTvSelectAll = (HealthTextView) view.findViewById(R.id.tv_select_all);
    }

    private void initData() {
        VoiceCourseRecyclerViewAdapter voiceCourseRecyclerViewAdapter = new VoiceCourseRecyclerViewAdapter(this.mContext, this.mMediaList, 0, this.mMediaType);
        this.mRecycleViewAdapter = voiceCourseRecyclerViewAdapter;
        this.mRecycleViewCourseList.setAdapter(voiceCourseRecyclerViewAdapter);
        this.mRecycleViewAdapter.d(new VoiceCourseRecyclerViewAdapter.OnCheckedChange() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.AllCoursesListFragment.1
            @Override // com.huawei.health.ecologydevice.ui.measure.adapter.voicecourse.VoiceCourseRecyclerViewAdapter.OnCheckedChange
            public void onSortTouch(int i) {
            }

            @Override // com.huawei.health.ecologydevice.ui.measure.adapter.voicecourse.VoiceCourseRecyclerViewAdapter.OnCheckedChange
            public void onSwitchClick(int i) {
            }

            @Override // com.huawei.health.ecologydevice.ui.measure.adapter.voicecourse.VoiceCourseRecyclerViewAdapter.OnCheckedChange
            public void onItemClick(int i) {
                if (!nsn.o()) {
                    if (AllCoursesListFragment.this.mOnMediaItemClick == null || !koq.d(AllCoursesListFragment.this.mMediaList, i)) {
                        return;
                    }
                    AllCoursesListFragment.this.mOnMediaItemClick.onItemClick((CourseModel.Course) AllCoursesListFragment.this.mMediaList.get(i));
                    return;
                }
                LogUtil.h(AllCoursesListFragment.TAG, "onItemClick click too fast");
            }

            @Override // com.huawei.health.ecologydevice.ui.measure.adapter.voicecourse.VoiceCourseRecyclerViewAdapter.OnCheckedChange
            public void onDownloadClick(int i) {
                if (!nsn.o()) {
                    if (!koq.b(AllCoursesListFragment.this.mMediaList, i)) {
                        AllCoursesListFragment.this.goDownLoadedPageOrDownload(i);
                        return;
                    } else {
                        LogUtil.h(AllCoursesListFragment.TAG, "onDownloadClick isOutOfBounds");
                        return;
                    }
                }
                LogUtil.h(AllCoursesListFragment.TAG, "onDownloadClick click too fast");
            }

            @Override // com.huawei.health.ecologydevice.ui.measure.adapter.voicecourse.VoiceCourseRecyclerViewAdapter.OnCheckedChange
            public void onCheckBoxClick() {
                AllCoursesListFragment.this.refreshSelectAllView();
            }
        });
        readFileToData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshSelectAllView() {
        boolean isAllSelected = this.mMediaManager.isAllSelected(true, this.mMediaList);
        this.mImgSelectAll.setImageResource(isAllSelected ? R.drawable._2131430354_res_0x7f0b0bd2 : R.drawable._2131430355_res_0x7f0b0bd3);
        ImageView imageView = this.mImgSelectAll;
        Activity activity = this.mContext;
        int i = R.color._2131299237_res_0x7f090ba5;
        imageView.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(activity, isAllSelected ? R.color._2131299237_res_0x7f090ba5 : R.color._2131299011_res_0x7f090ac3)));
        HealthTextView healthTextView = this.mTvSelectAll;
        Resources resources = getResources();
        if (!isAllSelected) {
            i = R.color._2131299236_res_0x7f090ba4;
        }
        healthTextView.setTextColor(resources.getColor(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void goDownLoadedPageOrDownload(int i) {
        final CourseModel.Course course = this.mMediaList.get(i);
        if (course.getStatus() == 3) {
            this.mMediaManager.showNoTitleCustomAlertDialog(this.mContext, R.string._2130850421_res_0x7f023275, R.string._2130842298_res_0x7f0212ba, new CommonUiResponse() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.AllCoursesListFragment$$ExternalSyntheticLambda0
                @Override // com.huawei.health.ecologydevice.open.data.model.CommonUiResponse
                public final void onResponse(int i2, Object obj) {
                    AllCoursesListFragment.this.m386x4ea7b08d(i2, (String) obj);
                }
            });
            return;
        }
        MediaManager mediaManager = this.mMediaManager;
        Activity activity = this.mContext;
        mediaManager.showRadioButtonSelectDialog(activity, mediaManager.getSelectHashMap(activity), new CommonUiResponse() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.AllCoursesListFragment$$ExternalSyntheticLambda1
            @Override // com.huawei.health.ecologydevice.open.data.model.CommonUiResponse
            public final void onResponse(int i2, Object obj) {
                AllCoursesListFragment.this.m387x1705d52c(course, i2, (String) obj);
            }
        });
    }

    /* renamed from: lambda$goDownLoadedPageOrDownload$0$com-huawei-health-ecologydevice-ui-measure-fragment-ropefragment-mediamanager-AllCoursesListFragment, reason: not valid java name */
    /* synthetic */ void m386x4ea7b08d(int i, String str) {
        OnMediaItemClick onMediaItemClick = this.mOnMediaItemClick;
        if (onMediaItemClick != null) {
            onMediaItemClick.onGotoDownloadedClick();
        }
    }

    /* renamed from: lambda$goDownLoadedPageOrDownload$1$com-huawei-health-ecologydevice-ui-measure-fragment-ropefragment-mediamanager-AllCoursesListFragment, reason: not valid java name */
    /* synthetic */ void m387x1705d52c(CourseModel.Course course, int i, String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(course);
        downloadMedia(arrayList, MediaManager.ROPE_MEDIA_STANDARD.equals(str) ? course.getFileSize() : course.getHighQualitySize(), str);
    }

    private void readFileToData() {
        String a2 = msb.a(new File(MediaManager.ROPE_JSON_FILE, this.mMediaType == 0 ? MediaManager.ROPE_MEDIA_COURSE_JSON : MediaManager.ROPE_MEDIA_MUSIC_JSON));
        if (TextUtils.isEmpty(a2)) {
            return;
        }
        try {
            List<CourseModel.Course> courses = ((CourseModel) new Gson().fromJson(a2, new TypeToken<CourseModel>() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.AllCoursesListFragment.2
            }.getType())).getCourses();
            if (koq.b(courses)) {
                return;
            }
            this.mMediaList.clear();
            this.mMediaList.addAll(setDataStatus(courses));
            if (this.mMediaList.size() == 1) {
                this.mMediaList.get(0).setBackgroundResourceId(R.drawable.list_item_background_single_normal);
            } else {
                this.mMediaList.get(0).setBackgroundResourceId(R.drawable.list_item_background_top_normal);
                List<CourseModel.Course> list = this.mMediaList;
                list.get(list.size() - 1).setBackgroundResourceId(R.drawable.list_item_background_bottom_normal);
            }
            this.mRecycleViewAdapter.notifyDataSetChanged();
        } catch (JsonSyntaxException e) {
            LogUtil.h(TAG, "JsonSyntaxException exception is ", e.getMessage());
        }
    }

    private List<CourseModel.Course> setDataStatus(List<CourseModel.Course> list) {
        if (koq.b(this.mRopeMediaList)) {
            return list;
        }
        Iterator<CourseModel.Course> it = this.mRopeMediaList.iterator();
        while (it.hasNext()) {
            setDataStatus(list, it.next());
        }
        return list;
    }

    private void setDataStatus(List<CourseModel.Course> list, CourseModel.Course course) {
        for (int i = 0; i < list.size(); i++) {
            if ((course.getStatus() == 2 || course.getStatus() == 3) && course.getId() == list.get(i).getId()) {
                list.get(i).setStatus(course.getStatus());
                return;
            }
        }
    }

    public void setIsMultipleSelectStatus(boolean z) {
        if (!z) {
            Iterator<CourseModel.Course> it = this.mMediaList.iterator();
            while (it.hasNext()) {
                it.next().setIsCheckboxCheck(false);
            }
        }
        this.mRecycleViewAdapter.e(z);
        this.mRecycleViewAdapter.notifyDataSetChanged();
        this.mLlRootSelectAll.setVisibility(z ? 0 : 8);
        refreshSelectAllView();
    }

    public boolean isMultipleSelectStatus() {
        VoiceCourseRecyclerViewAdapter voiceCourseRecyclerViewAdapter = this.mRecycleViewAdapter;
        if (voiceCourseRecyclerViewAdapter == null) {
            return false;
        }
        return voiceCourseRecyclerViewAdapter.e();
    }

    protected void setRopeMemory(long j) {
        this.mRopeRemainingMemory = j;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (nsn.a(500)) {
            LogUtil.h(TAG, "click too fast");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view.getId() == R.id.rb_select_all) {
            selectAll();
        } else if (view.getId() == R.id.rb_download) {
            if (!this.mMediaManager.isHasMediaSelected(this.mMediaList)) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            } else {
                MediaManager mediaManager = this.mMediaManager;
                Activity activity = this.mContext;
                mediaManager.showRadioButtonSelectDialog(activity, mediaManager.getSelectHashMap(activity), new CommonUiResponse() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.AllCoursesListFragment$$ExternalSyntheticLambda2
                    @Override // com.huawei.health.ecologydevice.open.data.model.CommonUiResponse
                    public final void onResponse(int i, Object obj) {
                        AllCoursesListFragment.this.m388xdd097aa5(i, (String) obj);
                    }
                });
            }
        } else {
            LogUtil.h(TAG, "onClick else");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: lambda$onClick$2$com-huawei-health-ecologydevice-ui-measure-fragment-ropefragment-mediamanager-AllCoursesListFragment, reason: not valid java name */
    /* synthetic */ void m388xdd097aa5(int i, String str) {
        downloadMedia(str);
    }

    private void selectAll() {
        boolean isAllSelected = this.mMediaManager.isAllSelected(true, this.mMediaList);
        for (CourseModel.Course course : this.mMediaList) {
            if (course.getStatus() < 2) {
                course.setIsCheckboxCheck(!isAllSelected);
            }
        }
        this.mRecycleViewAdapter.notifyDataSetChanged();
        this.mImgSelectAll.setImageResource(isAllSelected ? R.drawable._2131430355_res_0x7f0b0bd3 : R.drawable._2131430354_res_0x7f0b0bd2);
        ImageView imageView = this.mImgSelectAll;
        Activity activity = this.mContext;
        int i = R.color._2131299237_res_0x7f090ba5;
        imageView.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(activity, isAllSelected ? R.color._2131299011_res_0x7f090ac3 : R.color._2131299237_res_0x7f090ba5)));
        HealthTextView healthTextView = this.mTvSelectAll;
        Resources resources = getResources();
        if (isAllSelected) {
            i = R.color._2131299236_res_0x7f090ba4;
        }
        healthTextView.setTextColor(resources.getColor(i));
    }

    private void downloadMedia(String str) {
        ArrayList arrayList = new ArrayList();
        long j = 0;
        for (CourseModel.Course course : this.mMediaList) {
            if (course.isCheckboxCheck()) {
                j += MediaManager.ROPE_MEDIA_STANDARD.equals(str) ? course.getFileSize() : course.getHighQualitySize();
                arrayList.add(course);
            }
        }
        downloadMedia(arrayList, j, str);
    }

    private void downloadMedia(final List<CourseModel.Course> list, long j, final String str) {
        this.mMediaManager.showDownloadDialog(this.mContext, this.mMediaType, this.mRopeRemainingMemory, j, new CommonUiResponse() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.AllCoursesListFragment$$ExternalSyntheticLambda3
            @Override // com.huawei.health.ecologydevice.open.data.model.CommonUiResponse
            public final void onResponse(int i, Object obj) {
                AllCoursesListFragment.this.m385xac8fecd1(list, str, i, (String) obj);
            }
        });
    }

    /* renamed from: lambda$downloadMedia$3$com-huawei-health-ecologydevice-ui-measure-fragment-ropefragment-mediamanager-AllCoursesListFragment, reason: not valid java name */
    /* synthetic */ void m385xac8fecd1(List list, String str, int i, String str2) {
        if (this.mOnMediaItemClick == null) {
            return;
        }
        addCourseBiEvent(list);
        this.mOnMediaItemClick.onDownloadClick(list, str);
    }

    private void addCourseBiEvent(List<CourseModel.Course> list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<CourseModel.Course> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(it.next().getId()));
        }
        dko.b(arrayList.toString(), "add");
    }

    protected void refreshDataAdapter(ArrayList<CourseModel.Course> arrayList) {
        if (this.mRecycleViewAdapter == null) {
            return;
        }
        this.mRopeMediaList = arrayList;
        readFileToData();
    }

    public void setOnMediaItemClick(OnMediaItemClick onMediaItemClick) {
        this.mOnMediaItemClick = onMediaItemClick;
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        FragmentInstrumentation.setUserVisibleHintByFragment(this, z);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        FragmentInstrumentation.onViewCreatedByFragment(this, view, bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        FragmentInstrumentation.onResumeByFragment(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        FragmentInstrumentation.onPauseByFragment(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        FragmentInstrumentation.onHiddenChangedByFragment(this, z);
    }
}
