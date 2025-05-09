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
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.callback.SimpleItemTouchHelperCallback;
import com.huawei.health.ecologydevice.open.data.model.CommonUiResponse;
import com.huawei.health.ecologydevice.ui.measure.adapter.voicecourse.VoiceCourseRecyclerViewAdapter;
import com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.CourseModel;
import com.huawei.hianalytics.visual.autocollect.instrument.FragmentInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import defpackage.dds;
import defpackage.dko;
import defpackage.koq;
import defpackage.msb;
import defpackage.nsn;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class DownloadedCoursesListFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "DownloadedRopeMediaFragment";
    private HealthButton mConfirmBtn;
    private Activity mContext;
    private ImageView mImgSelectAll;
    private LinearLayout mLinearLayoutCoursesList;
    private LinearLayout mLinearLayoutNoData;
    private LinearLayout mLlRootDownloadedCoursesOperate;
    private MediaManager mMediaManager;
    private int mMediaType;
    private OnDownloadedListener mOnDownloadedListener;
    private LinearLayout mRbDeleteCourse;
    private LinearLayout mRbSelectAll;
    private VoiceCourseRecyclerViewAdapter mRecycleViewAdapter;
    private RelativeLayout mRlRootConfirm;
    private ArrayList<CourseModel.Course> mRopeMediaList;
    private RecyclerView mRvDownloadedCoursesList;
    private HealthTextView mTvNoData;
    private HealthTextView mTvNoDataTip;
    private HealthTextView mTvSelectAll;
    private List<CourseModel.Course> mMediaList = new ArrayList();
    private List<CourseModel.Course> mMediaEnableList = new ArrayList();
    private List<CourseModel.Course> mMediaDisableList = new ArrayList();
    private List<CourseModel.Course> mMediaEnableBeforeSortList = new ArrayList();

    public interface OnDownloadedListener {
        void onDownloadedItemClick(CourseModel.Course course);

        void onGotoAllClick();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mMediaType = arguments.getInt(MediaManager.KEY_MEDIA_TYPE);
            this.mRopeMediaList = arguments.getParcelableArrayList(MediaManager.KEY_MEDIA_LIST);
        }
        this.mContext = getActivity();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        LogUtil.a(TAG, "onCreateView");
        View inflate = layoutInflater.inflate(R.layout.downloaded_courses_list, viewGroup, false);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initView(View view) {
        this.mMediaManager = new MediaManager();
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ll_no_download_data);
        this.mLinearLayoutNoData = linearLayout;
        linearLayout.setOnClickListener(this);
        this.mTvNoData = (HealthTextView) view.findViewById(R.id.tv_no_download_data);
        this.mTvNoDataTip = (HealthTextView) view.findViewById(R.id.tv_no_download_data_tip);
        this.mLinearLayoutCoursesList = (LinearLayout) view.findViewById(R.id.ll_courses_list);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_courses_list);
        this.mRvDownloadedCoursesList = recyclerView;
        recyclerView.setLayoutManager(new HealthLinearLayoutManager(this.mContext));
        this.mRlRootConfirm = (RelativeLayout) view.findViewById(R.id.rl_root_management);
        HealthButton healthButton = (HealthButton) view.findViewById(R.id.bt_manage_course);
        this.mConfirmBtn = healthButton;
        healthButton.setOnClickListener(this);
        this.mLlRootDownloadedCoursesOperate = (LinearLayout) view.findViewById(R.id.ll_root_downloaded_courses_operate);
        LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.rb_delete_course);
        this.mRbDeleteCourse = linearLayout2;
        linearLayout2.setOnClickListener(this);
        LinearLayout linearLayout3 = (LinearLayout) view.findViewById(R.id.rb_select_all);
        this.mRbSelectAll = linearLayout3;
        linearLayout3.setOnClickListener(this);
        this.mImgSelectAll = (ImageView) view.findViewById(R.id.img_select_all);
        this.mTvSelectAll = (HealthTextView) view.findViewById(R.id.tv_select_all);
    }

    private void initData() {
        VoiceCourseRecyclerViewAdapter voiceCourseRecyclerViewAdapter = new VoiceCourseRecyclerViewAdapter(this.mContext, this.mMediaList, 1, this.mMediaType);
        this.mRecycleViewAdapter = voiceCourseRecyclerViewAdapter;
        this.mRvDownloadedCoursesList.setAdapter(voiceCourseRecyclerViewAdapter);
        final SimpleItemTouchHelperCallback simpleItemTouchHelperCallback = new SimpleItemTouchHelperCallback(this.mRecycleViewAdapter);
        new ItemTouchHelper(simpleItemTouchHelperCallback).attachToRecyclerView(this.mRvDownloadedCoursesList);
        this.mRecycleViewAdapter.d(new VoiceCourseRecyclerViewAdapter.OnCheckedChange() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.DownloadedCoursesListFragment.1
            @Override // com.huawei.health.ecologydevice.ui.measure.adapter.voicecourse.VoiceCourseRecyclerViewAdapter.OnCheckedChange
            public void onDownloadClick(int i) {
            }

            @Override // com.huawei.health.ecologydevice.ui.measure.adapter.voicecourse.VoiceCourseRecyclerViewAdapter.OnCheckedChange
            public void onSortTouch(int i) {
                simpleItemTouchHelperCallback.b(true);
            }

            @Override // com.huawei.health.ecologydevice.ui.measure.adapter.voicecourse.VoiceCourseRecyclerViewAdapter.OnCheckedChange
            public void onItemClick(int i) {
                if (!nsn.o()) {
                    if (DownloadedCoursesListFragment.this.mOnDownloadedListener == null || !koq.d(DownloadedCoursesListFragment.this.mMediaList, i)) {
                        return;
                    }
                    DownloadedCoursesListFragment.this.mOnDownloadedListener.onDownloadedItemClick((CourseModel.Course) DownloadedCoursesListFragment.this.mMediaList.get(i));
                    return;
                }
                LogUtil.h(DownloadedCoursesListFragment.TAG, "onItemClick click too fast");
            }

            @Override // com.huawei.health.ecologydevice.ui.measure.adapter.voicecourse.VoiceCourseRecyclerViewAdapter.OnCheckedChange
            public void onSwitchClick(int i) {
                DownloadedCoursesListFragment.this.sendCourseAbleOrDisableSetting(i);
            }

            @Override // com.huawei.health.ecologydevice.ui.measure.adapter.voicecourse.VoiceCourseRecyclerViewAdapter.OnCheckedChange
            public void onCheckBoxClick() {
                DownloadedCoursesListFragment.this.refreshSelectAllView();
            }
        });
        readFileToData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendCourseAbleOrDisableSetting(int i) {
        if (this.mMediaManager.isRopeDeviceConnected(this.mContext) && koq.d(this.mMediaList, i)) {
            CourseModel.Course course = this.mMediaList.get(i);
            dds.c().d(4, course.getStatus() == 2 ? 3 : 4, new int[]{course.getId()});
            ArrayList arrayList = new ArrayList();
            arrayList.add(Integer.valueOf(course.getId()));
            dko.b(arrayList.toString(), course.getStatus() == 2 ? "disable" : "able");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshSelectAllView() {
        boolean isAllSelected = this.mMediaManager.isAllSelected(false, this.mMediaList);
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

    private void readFileToData() {
        String a2 = msb.a(new File(MediaManager.ROPE_JSON_FILE, this.mMediaType == 0 ? MediaManager.ROPE_MEDIA_COURSE_JSON : MediaManager.ROPE_MEDIA_MUSIC_JSON));
        if (TextUtils.isEmpty(a2)) {
            return;
        }
        LogUtil.a(TAG, "readFileToData json");
        try {
            List<CourseModel.Course> courses = ((CourseModel) new Gson().fromJson(a2, new TypeToken<CourseModel>() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.DownloadedCoursesListFragment.2
            }.getType())).getCourses();
            if (koq.b(courses)) {
                return;
            }
            this.mMediaList.clear();
            this.mMediaEnableList.clear();
            this.mMediaDisableList.clear();
            getDownloadedData(courses);
            this.mMediaList.addAll(setBackGroundResouceId(this.mMediaEnableList));
            if (koq.c(this.mMediaDisableList)) {
                this.mMediaList.add(new CourseModel.Course(null));
                this.mMediaList.addAll(setBackGroundResouceId(this.mMediaDisableList));
            }
            this.mRecycleViewAdapter.notifyDataSetChanged();
            if (koq.b(this.mMediaList)) {
                this.mLinearLayoutNoData.setVisibility(0);
                this.mLinearLayoutCoursesList.setVisibility(8);
                this.mTvNoData.setText(getResources().getString(this.mMediaType == 0 ? R.string._2130850400_res_0x7f023260 : R.string._2130850401_res_0x7f023261));
                this.mTvNoDataTip.setText(getResources().getString(this.mMediaType == 0 ? R.string._2130850405_res_0x7f023265 : R.string._2130850406_res_0x7f023266));
                return;
            }
            this.mLinearLayoutNoData.setVisibility(8);
            this.mLinearLayoutCoursesList.setVisibility(0);
        } catch (JsonSyntaxException e) {
            LogUtil.h(TAG, "JsonSyntaxException exception is ", e.getMessage());
        }
    }

    protected void refreshDataAdapter(ArrayList<CourseModel.Course> arrayList) {
        VoiceCourseRecyclerViewAdapter voiceCourseRecyclerViewAdapter = this.mRecycleViewAdapter;
        if (voiceCourseRecyclerViewAdapter == null) {
            return;
        }
        voiceCourseRecyclerViewAdapter.e(false);
        this.mLlRootDownloadedCoursesOperate.setVisibility(8);
        this.mRopeMediaList = arrayList;
        readFileToData();
    }

    private void getDownloadedData(List<CourseModel.Course> list) {
        if (koq.b(this.mRopeMediaList)) {
            return;
        }
        Iterator<CourseModel.Course> it = this.mRopeMediaList.iterator();
        while (it.hasNext()) {
            setSplitMediaData(list, it.next());
        }
    }

    private void setSplitMediaData(List<CourseModel.Course> list, CourseModel.Course course) {
        for (int i = 0; i < list.size(); i++) {
            if (course.getId() == list.get(i).getId()) {
                CourseModel.Course course2 = list.get(i);
                course2.setStatus(course.getStatus());
                if (course2.getStatus() == 2) {
                    this.mMediaEnableList.add(course2);
                    return;
                } else if (this.mMediaType == 0 && course2.getStatus() == 3) {
                    this.mMediaDisableList.add(course2);
                    return;
                } else {
                    LogUtil.h(TAG, "setSplitMediaData err status is ", Integer.valueOf(course2.getStatus()));
                    return;
                }
            }
        }
    }

    private List<CourseModel.Course> setBackGroundResouceId(List<CourseModel.Course> list) {
        if (koq.b(list)) {
            return list;
        }
        if (list.size() == 1) {
            list.get(0).setBackgroundResourceId(R.drawable.list_item_background_single_normal);
        } else {
            list.get(0).setBackgroundResourceId(R.drawable.list_item_background_top_normal);
            list.get(list.size() - 1).setBackgroundResourceId(R.drawable.list_item_background_bottom_normal);
        }
        return list;
    }

    public boolean isMultipleSelectStatus() {
        return this.mRecycleViewAdapter.e();
    }

    public void setIsMultipleSelectStatus(boolean z) {
        if (!z) {
            Iterator<CourseModel.Course> it = this.mMediaList.iterator();
            while (it.hasNext()) {
                it.next().setIsCheckboxCheck(false);
            }
        }
        if (isSortStatus()) {
            this.mRecycleViewAdapter.c(false);
            switchNoSortStatusSetData(false);
        }
        this.mRecycleViewAdapter.e(z);
        this.mRecycleViewAdapter.notifyDataSetChanged();
        this.mLlRootDownloadedCoursesOperate.setVisibility(z ? 0 : 8);
        this.mRlRootConfirm.setVisibility(8);
        refreshSelectAllView();
    }

    public boolean isSortStatus() {
        return this.mRecycleViewAdapter.a();
    }

    public void setIsSortStatus(boolean z, boolean z2) {
        if (z) {
            this.mLinearLayoutCoursesList.setBackgroundResource(R.drawable.list_item_background_single_normal);
            this.mMediaEnableBeforeSortList.clear();
            this.mMediaEnableBeforeSortList.addAll(this.mMediaEnableList);
            this.mMediaList.clear();
            this.mMediaList.addAll(setBackGroundResouceId(this.mMediaEnableList));
        } else {
            switchNoSortStatusSetData(z2);
        }
        this.mRecycleViewAdapter.c(z);
        this.mRecycleViewAdapter.e(false);
        this.mRecycleViewAdapter.notifyDataSetChanged();
        this.mRlRootConfirm.setVisibility(z ? 0 : 8);
        this.mLlRootDownloadedCoursesOperate.setVisibility(8);
    }

    private void switchNoSortStatusSetData(boolean z) {
        this.mLinearLayoutCoursesList.setBackgroundResource(R.color._2131296971_res_0x7f0902cb);
        this.mMediaEnableList.clear();
        this.mMediaEnableList.addAll(z ? this.mMediaList : this.mMediaEnableBeforeSortList);
        this.mMediaList.clear();
        this.mMediaList.addAll(setBackGroundResouceId(this.mMediaEnableList));
        if (koq.c(this.mMediaDisableList)) {
            this.mMediaList.add(new CourseModel.Course(null));
            this.mMediaList.addAll(setBackGroundResouceId(this.mMediaDisableList));
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        OnDownloadedListener onDownloadedListener;
        if (nsn.a(500)) {
            LogUtil.h(TAG, "click too fast");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view.getId() == R.id.bt_manage_course) {
            if (!this.mMediaManager.isRopeDeviceConnected(this.mContext)) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            int[] iArr = new int[this.mMediaList.size()];
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < this.mMediaList.size(); i++) {
                int id = this.mMediaList.get(i).getId();
                iArr[i] = id;
                sb.append(id);
                sb.append(" ");
            }
            LogUtil.a(TAG, "sortArray = ", sb.toString());
            dds c = dds.c();
            int i2 = this.mMediaType;
            c.d(i2 == 0 ? 4 : 7, i2 == 0 ? 5 : 4, iArr);
            setIsSortStatus(false, true);
        } else if (view.getId() == R.id.rb_select_all) {
            if (koq.b(this.mMediaList)) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            boolean isAllSelected = this.mMediaManager.isAllSelected(false, this.mMediaList);
            Iterator<CourseModel.Course> it = this.mMediaList.iterator();
            while (it.hasNext()) {
                it.next().setIsCheckboxCheck(!isAllSelected);
            }
            this.mRecycleViewAdapter.notifyDataSetChanged();
            this.mImgSelectAll.setImageResource(isAllSelected ? R.drawable._2131430355_res_0x7f0b0bd3 : R.drawable._2131430354_res_0x7f0b0bd2);
            ImageView imageView = this.mImgSelectAll;
            Activity activity = this.mContext;
            int i3 = R.color._2131299237_res_0x7f090ba5;
            imageView.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(activity, isAllSelected ? R.color._2131299011_res_0x7f090ac3 : R.color._2131299237_res_0x7f090ba5)));
            HealthTextView healthTextView = this.mTvSelectAll;
            Resources resources = getResources();
            if (isAllSelected) {
                i3 = R.color._2131299236_res_0x7f090ba4;
            }
            healthTextView.setTextColor(resources.getColor(i3));
        } else if (view.getId() == R.id.rb_delete_course) {
            showDeleteDialog();
        } else if (view.getId() == R.id.ll_no_download_data && (onDownloadedListener = this.mOnDownloadedListener) != null) {
            onDownloadedListener.onGotoAllClick();
        } else {
            LogUtil.h(TAG, "onClick else");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void showDeleteDialog() {
        ArrayList arrayList = new ArrayList();
        for (CourseModel.Course course : this.mMediaList) {
            if (course.isCheckboxCheck()) {
                arrayList.add(Integer.valueOf(course.getId()));
            }
        }
        if (arrayList.size() == 0) {
            return;
        }
        LogUtil.a(TAG, "deleteArray = ", arrayList);
        final int[] iArr = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            iArr[i] = ((Integer) arrayList.get(i)).intValue();
        }
        this.mMediaManager.showNoTitleCustomAlertDialog(this.mContext, this.mMediaType == 0 ? R.string._2130850427_res_0x7f02327b : R.string._2130850428_res_0x7f02327c, R.string._2130841131_res_0x7f020e2b, new CommonUiResponse() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.DownloadedCoursesListFragment$$ExternalSyntheticLambda0
            @Override // com.huawei.health.ecologydevice.open.data.model.CommonUiResponse
            public final void onResponse(int i2, Object obj) {
                DownloadedCoursesListFragment.this.m389xa31aecfe(iArr, i2, (String) obj);
            }
        });
    }

    /* renamed from: lambda$showDeleteDialog$0$com-huawei-health-ecologydevice-ui-measure-fragment-ropefragment-mediamanager-DownloadedCoursesListFragment, reason: not valid java name */
    /* synthetic */ void m389xa31aecfe(int[] iArr, int i, String str) {
        if (this.mMediaManager.isRopeDeviceConnected(this.mContext)) {
            dds.c().d(this.mMediaType == 0 ? 4 : 7, 2, iArr);
            dko.b(Arrays.toString(iArr), "delete");
        }
    }

    public void setOnDownloadedListener(OnDownloadedListener onDownloadedListener) {
        this.mOnDownloadedListener = onDownloadedListener;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        VoiceCourseRecyclerViewAdapter voiceCourseRecyclerViewAdapter = this.mRecycleViewAdapter;
        if (voiceCourseRecyclerViewAdapter != null) {
            voiceCourseRecyclerViewAdapter.c();
        }
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
