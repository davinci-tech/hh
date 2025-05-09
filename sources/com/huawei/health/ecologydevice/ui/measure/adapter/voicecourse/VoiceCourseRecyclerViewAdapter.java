package com.huawei.health.ecologydevice.ui.measure.adapter.voicecourse;

import android.content.Context;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.callback.SimpleItemTouchHelperCallback;
import com.huawei.health.ecologydevice.ui.measure.adapter.voicecourse.VoiceCourseRecyclerViewAdapter;
import com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.CourseModel;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import defpackage.dju;
import java.util.List;

/* loaded from: classes3.dex */
public class VoiceCourseRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> implements SimpleItemTouchHelperCallback.ItemTouchHelperAdapter {

    /* renamed from: a, reason: collision with root package name */
    private final List<CourseModel.Course> f2338a;
    private boolean b;
    private int c;
    private boolean d;
    private final Context e;
    private OnCheckedChange f;
    private int h;
    private Vibrator i;

    public interface OnCheckedChange {
        void onCheckBoxClick();

        void onDownloadClick(int i);

        void onItemClick(int i);

        void onSortTouch(int i);

        void onSwitchClick(int i);
    }

    public VoiceCourseRecyclerViewAdapter(Context context, List<CourseModel.Course> list, int i, int i2) {
        this.e = context;
        this.f2338a = list;
        this.c = i;
        this.h = i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: UK_, reason: merged with bridge method [inline-methods] */
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LogUtil.a("VoiceCourseRecyclerViewAdapter", "onCreateViewHolder, parent = ", viewGroup, ", viewType = ", Integer.valueOf(i));
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_course_grid_view, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        LogUtil.a("VoiceCourseRecyclerViewAdapter", "onBindViewHolder, viewHolder = ", viewHolder, ", position = ", Integer.valueOf(i));
        if (i >= this.f2338a.size()) {
            return;
        }
        CourseModel.Course course = this.f2338a.get(i);
        if (!TextUtils.isEmpty(course.getName()) || !TextUtils.isEmpty(course.getShortDesc())) {
            viewHolder.n.setVisibility(8);
            viewHolder.h.setVisibility(0);
            viewHolder.i.setText(course.getName());
            viewHolder.f.setText(course.getLevel() + " " + this.e.getResources().getQuantityString(R.plurals._2130903305_res_0x7f030109, (int) (course.getTime() / 60.0f), dju.a(course.getTime())));
            if (!this.b) {
                c(viewHolder, i, course);
                return;
            }
            viewHolder.h.setBackgroundResource(R.color._2131296971_res_0x7f0902cb);
            viewHolder.g.setVisibility(0);
            viewHolder.c.setVisibility(8);
            viewHolder.d.setVisibility(8);
            viewHolder.e.setVisibility(8);
            viewHolder.j.setVisibility(8);
            viewHolder.h.setOnTouchListener(new View.OnTouchListener() { // from class: dgs
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return VoiceCourseRecyclerViewAdapter.this.UF_(i, view, motionEvent);
                }
            });
            viewHolder.h.setClickable(false);
            return;
        }
        viewHolder.n.setVisibility(0);
        viewHolder.h.setVisibility(8);
    }

    public /* synthetic */ boolean UF_(int i, View view, MotionEvent motionEvent) {
        OnCheckedChange onCheckedChange = this.f;
        if (onCheckedChange == null || !this.b) {
            return false;
        }
        onCheckedChange.onSortTouch(i);
        return false;
    }

    private void c(ViewHolder viewHolder, final int i, final CourseModel.Course course) {
        if (course.getBackgroundResourceId() == 0) {
            viewHolder.h.setBackgroundResource(R.color._2131297213_res_0x7f0903bd);
        } else {
            viewHolder.h.setBackgroundResource(course.getBackgroundResourceId());
        }
        viewHolder.g.setVisibility(8);
        int i2 = 0;
        viewHolder.c.setVisibility(0);
        Glide.with(this.e).load(course.getSmallImgUrl()).transform(new RoundedCorners(this.e.getResources().getDimensionPixelOffset(R.dimen._2131363122_res_0x7f0a0532))).into(viewHolder.c);
        if (!this.d) {
            b(viewHolder, i, course);
        } else {
            HealthCheckBox healthCheckBox = viewHolder.d;
            if (this.c == 0 && course.getStatus() > 1) {
                i2 = 8;
            }
            healthCheckBox.setVisibility(i2);
            viewHolder.e.setVisibility(8);
            viewHolder.j.setVisibility(8);
            viewHolder.d.setChecked(course.isCheckboxCheck());
            viewHolder.d.setOnClickListener(new View.OnClickListener() { // from class: dgy
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    VoiceCourseRecyclerViewAdapter.this.UI_(course, view);
                }
            });
        }
        viewHolder.h.setClickable(true);
        viewHolder.h.setOnClickListener(new View.OnClickListener() { // from class: dgz
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VoiceCourseRecyclerViewAdapter.this.UJ_(i, view);
            }
        });
    }

    public /* synthetic */ void UI_(CourseModel.Course course, View view) {
        course.setIsCheckboxCheck(!course.isCheckboxCheck());
        OnCheckedChange onCheckedChange = this.f;
        if (onCheckedChange != null) {
            onCheckedChange.onCheckBoxClick();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void UJ_(int i, View view) {
        LogUtil.a("VoiceCourseRecyclerViewAdapter", "setNoSortStatusView mRelativeLayout setOnClickListener");
        OnCheckedChange onCheckedChange = this.f;
        if (onCheckedChange != null) {
            onCheckedChange.onItemClick(i);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b(ViewHolder viewHolder, final int i, CourseModel.Course course) {
        viewHolder.d.setVisibility(8);
        int i2 = this.c;
        if (i2 != 0) {
            if (i2 != 1 || this.h != 0) {
                LogUtil.a("VoiceCourseRecyclerViewAdapter", "setCommonStatusView else");
                return;
            }
            viewHolder.j.setVisibility(0);
            viewHolder.f2339a.setChecked(course.getStatus() == 2);
            viewHolder.j.setOnClickListener(new View.OnClickListener() { // from class: dha
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    VoiceCourseRecyclerViewAdapter.this.UH_(i, view);
                }
            });
            return;
        }
        viewHolder.e.setVisibility(0);
        if (course.getStatus() == 2) {
            viewHolder.e.setText(this.e.getResources().getString(R.string._2130839744_res_0x7f0208c0));
            viewHolder.e.setEnabled(false);
        } else {
            viewHolder.e.setText(this.e.getResources().getString(course.getStatus() == 3 ? R.string._2130850429_res_0x7f02327d : R.string._2130850454_res_0x7f023296));
            viewHolder.e.setEnabled(true);
            viewHolder.e.setOnClickListener(new View.OnClickListener() { // from class: dgx
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    VoiceCourseRecyclerViewAdapter.this.UG_(i, view);
                }
            });
        }
    }

    public /* synthetic */ void UG_(int i, View view) {
        OnCheckedChange onCheckedChange = this.f;
        if (onCheckedChange != null) {
            onCheckedChange.onDownloadClick(i);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void UH_(int i, View view) {
        LogUtil.a("VoiceCourseRecyclerViewAdapter", "mHealthSwitch setOnClickListener");
        OnCheckedChange onCheckedChange = this.f;
        if (onCheckedChange != null) {
            onCheckedChange.onSwitchClick(i);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f2338a.size();
    }

    @Override // com.huawei.health.ecologydevice.callback.SimpleItemTouchHelperCallback.ItemTouchHelperAdapter
    public void onItemMove(int i, int i2) {
        CourseModel.Course course = this.f2338a.get(i);
        this.f2338a.remove(i);
        this.f2338a.add(i2, course);
        notifyItemMoved(i, i2);
        b();
    }

    public void b() {
        if (this.i == null) {
            Object systemService = this.e.getSystemService("vibrator");
            if (systemService instanceof Vibrator) {
                this.i = (Vibrator) systemService;
            }
        }
        Vibrator vibrator = this.i;
        if (vibrator != null) {
            vibrator.vibrate(150L);
        }
    }

    public void c() {
        Vibrator vibrator = this.i;
        if (vibrator != null) {
            vibrator.cancel();
            this.i = null;
        }
    }

    public boolean e() {
        return this.d;
    }

    public void e(boolean z) {
        this.d = z;
    }

    public boolean a() {
        return this.b;
    }

    public void c(boolean z) {
        this.b = z;
        notifyDataSetChanged();
    }

    public void d(OnCheckedChange onCheckedChange) {
        this.f = onCheckedChange;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthSwitchButton f2339a;
        private HealthImageView c;
        private HealthCheckBox d;
        private HealthButton e;
        private HealthTextView f;
        private HealthImageView g;
        private RelativeLayout h;
        private HealthTextView i;
        private RelativeLayout j;
        private LinearLayout n;

        public ViewHolder(View view) {
            super(view);
            this.n = (LinearLayout) view.findViewById(R.id.tv_media_disable);
            this.h = (RelativeLayout) view.findViewById(R.id.rl_item_course);
            this.g = (HealthImageView) view.findViewById(R.id.iv_sort);
            this.c = (HealthImageView) view.findViewById(R.id.iv_course);
            this.i = (HealthTextView) view.findViewById(R.id.tv_course_name);
            this.f = (HealthTextView) view.findViewById(R.id.tv_course_description);
            this.d = (HealthCheckBox) view.findViewById(R.id.check_box_select_all);
            this.e = (HealthButton) view.findViewById(R.id.bt_download);
            this.j = (RelativeLayout) view.findViewById(R.id.rl_media_item_switch);
            this.f2339a = (HealthSwitchButton) view.findViewById(R.id.rope_media_item_switch);
        }
    }
}
