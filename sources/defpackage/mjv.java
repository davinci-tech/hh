package defpackage;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.LevelInfoDesc;
import com.huawei.pluginachievement.ui.level.AchieveLevelTaskRvAdapter;
import com.huawei.pluginachievement.ui.views.CardLinearSnapHelper;
import com.huawei.pluginachievement.ui.views.LevelProgress;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class mjv {

    /* renamed from: a, reason: collision with root package name */
    private RelativeLayout f15027a;
    private AchieveLevelTaskRvAdapter b;
    private int f;
    private List<LevelInfoDesc> g;
    private int h;
    private LevelProgress i;
    private View k;
    private double l;
    private View m;
    private ImageView o;
    private LinearLayout q;
    private ArrayList<mkg> r;
    private LinearLayout t;
    private static final Integer[] e = {Integer.valueOf(R.color._2131298692_res_0x7f090984), Integer.valueOf(R.color._2131298696_res_0x7f090988), Integer.valueOf(R.color._2131298693_res_0x7f090985), Integer.valueOf(R.color._2131298695_res_0x7f090987), Integer.valueOf(R.color._2131298694_res_0x7f090986), Integer.valueOf(R.color._2131298690_res_0x7f090982), Integer.valueOf(R.color._2131298691_res_0x7f090983)};
    private static final Integer[] d = {Integer.valueOf(R.color._2131298728_res_0x7f0909a8), Integer.valueOf(R.color._2131298732_res_0x7f0909ac), Integer.valueOf(R.color._2131298729_res_0x7f0909a9), Integer.valueOf(R.color._2131298731_res_0x7f0909ab), Integer.valueOf(R.color._2131298730_res_0x7f0909aa), Integer.valueOf(R.color._2131298726_res_0x7f0909a6), Integer.valueOf(R.color._2131298727_res_0x7f0909a7)};
    private CardLinearSnapHelper n = new CardLinearSnapHelper();
    private int c = -1;
    private int p = 0;
    private String s = "";
    private boolean j = false;

    public void c(RecyclerView recyclerView, final HealthTextView healthTextView, final HealthTextView healthTextView2) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: mjv.2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView2, int i) {
                super.onScrollStateChanged(recyclerView2, i);
                mjv.this.n.c(true);
                mjv.this.d(recyclerView2, i, healthTextView, healthTextView2);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView2, int i, int i2) {
                super.onScrolled(recyclerView2, i, i2);
                mjv.this.c(recyclerView2);
                mjv.this.i.setRotate(i, mjv.this.p, mjv.this.e(), mjv.this.f, mjv.this.l);
            }
        });
        this.n.attachToRecyclerView(recyclerView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(RecyclerView recyclerView, int i, HealthTextView healthTextView, HealthTextView healthTextView2) {
        View findSnapView;
        if (i != 0) {
            if (i == 1) {
                this.o.setAlpha(0.7f);
                this.p = 1;
                return;
            } else {
                if (i == 2) {
                    this.o.setAlpha(0.3f);
                    this.p = 2;
                    this.i.setRotate(0.0f, 2, e(), this.f, this.l);
                    return;
                }
                LogUtil.h("LevelHorizontalHelper", "no branch!");
                return;
            }
        }
        if (koq.b(this.g) || recyclerView.getChildCount() <= 0 || (findSnapView = this.n.findSnapView(recyclerView.getLayoutManager())) == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = findSnapView.getLayoutParams();
        if (!(layoutParams instanceof RecyclerView.LayoutParams)) {
            LogUtil.b("layoutParams error", new Object[0]);
            return;
        }
        int viewAdapterPosition = ((RecyclerView.LayoutParams) layoutParams).getViewAdapterPosition() % this.g.size();
        this.c = viewAdapterPosition;
        c(healthTextView, healthTextView2, viewAdapterPosition);
        this.p = 0;
        this.i.setRotate(0.0f, 0, viewAdapterPosition, this.f, this.l);
    }

    private void c(HealthTextView healthTextView, HealthTextView healthTextView2, int i) {
        if (koq.d(this.g, i)) {
            healthTextView.setText(this.g.get(i).getName());
            healthTextView2.setText(this.g.get(i).getDescription());
            if (i < this.h) {
                this.o.setVisibility(8);
            } else {
                this.o.setAlpha(1.0f);
                this.o.setVisibility(0);
                this.o.setImageResource(this.g.get(i).getIcon());
            }
            Integer[] numArr = e;
            if (i < numArr.length) {
                int color = BaseApplication.getContext().getResources().getColor(numArr[i].intValue());
                this.f15027a.setBackgroundColor(color);
                this.k.setBackgroundColor(color);
                this.m.setBackgroundColor(color);
                d(this.g.get(i).getLabelList(), i);
            }
            this.b.b(this.r, i + 1);
            mlc.a(1, this.f, 0L, this.s, this.j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(RecyclerView recyclerView) {
        int childCount = recyclerView.getChildCount();
        int width = (recyclerView.getWidth() - recyclerView.getChildAt(0).getWidth()) / 2;
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            if (childAt instanceof LinearLayout) {
                LinearLayout linearLayout = (LinearLayout) childAt;
                View childAt2 = linearLayout.getChildAt(0);
                View childAt3 = linearLayout.getChildAt(1);
                if (childAt2 instanceof LinearLayout) {
                    View childAt4 = ((LinearLayout) childAt2).getChildAt(0);
                    View view = childAt4 instanceof ImageView ? childAt4 : childAt;
                    if (!(childAt3 instanceof ImageView)) {
                        childAt3 = null;
                    }
                    View view2 = childAt3;
                    if (view2 != null) {
                        ciW_(recyclerView, childAt, view, view2, width);
                    }
                }
            }
        }
    }

    private void ciW_(RecyclerView recyclerView, View view, View view2, View view3, int i) {
        if (recyclerView == null || view == null || view2 == null || view3 == null) {
            return;
        }
        if (view.getLeft() <= i) {
            float left = view.getLeft() >= i - view.getWidth() ? ((i - view.getLeft()) * 1.0f) / view.getWidth() : 1.0f;
            float f = 1.0f - (left * 0.3f);
            view2.setScaleY(f);
            view2.setScaleX(f);
            float f2 = 1.0f - left;
            view2.setAlpha(Math.max(f2, 0.3f));
            view3.setScaleX(f2);
            view3.setScaleY(f2);
            view3.setAlpha(Math.min(f2, 0.7f));
            return;
        }
        float width = view.getLeft() <= recyclerView.getWidth() - i ? (((recyclerView.getWidth() - i) - view.getLeft()) * 1.0f) / view.getWidth() : 0.0f;
        float f3 = (width * 0.3f) + 0.7f;
        view2.setScaleY(f3);
        view2.setScaleX(f3);
        view2.setAlpha(Math.max(width, 0.3f));
        view3.setAlpha(width);
        view3.setScaleX(width);
        view3.setAlpha(Math.min(width, 0.7f));
    }

    public void d(List<LevelInfoDesc> list, int i, int i2, double d2) {
        this.g = list;
        this.h = i;
        this.f = i2;
        this.l = d2;
    }

    public int e() {
        int i = this.c;
        return i == -1 ? this.h - 1 : i;
    }

    public void d(ArrayList<mkg> arrayList) {
        if (koq.b(arrayList)) {
            LogUtil.h("LevelHorizontalHelper", "setTaskRecyclerViewData taskRecyclerViewDatas is null.");
        } else {
            this.r = arrayList;
        }
    }

    public void ciY_(ImageView imageView, RelativeLayout relativeLayout, LevelProgress levelProgress, AchieveLevelTaskRvAdapter achieveLevelTaskRvAdapter) {
        this.o = imageView;
        this.f15027a = relativeLayout;
        this.i = levelProgress;
        this.b = achieveLevelTaskRvAdapter;
    }

    public void cja_(View view, View view2) {
        this.k = view;
        this.m = view2;
    }

    public void ciZ_(LinearLayout linearLayout, LinearLayout linearLayout2) {
        this.t = linearLayout;
        this.q = linearLayout2;
    }

    public void d(List<String> list, int i) {
        Drawable drawable = ContextCompat.getDrawable(BaseApplication.getContext(), R.mipmap._2131821391_res_0x7f11034f);
        if (drawable != null) {
            Integer[] numArr = d;
            if (i < numArr.length) {
                if (koq.b(list)) {
                    LogUtil.h("LevelHorizontalHelper", "setBadgeDesignation labelList isEmpty!");
                    return;
                }
                int color = BaseApplication.getContext().getResources().getColor(numArr[i].intValue());
                Drawable ckC_ = mlo.ckC_(drawable, color);
                this.t.removeAllViews();
                this.q.removeAllViews();
                for (int i2 = 0; i2 < list.size(); i2++) {
                    if (i2 < 3) {
                        this.t.addView(ciX_(mlc.e().get(Integer.valueOf(mfg.b(list.get(i2)))), ckC_, color));
                    } else {
                        this.q.addView(ciX_(mlc.e().get(Integer.valueOf(mfg.b(list.get(i2)))), ckC_, color));
                    }
                }
                return;
            }
        }
        LogUtil.h("LevelHorizontalHelper", "setBadgeDesignation isOutOfBounds!");
    }

    public View ciX_(String str, Drawable drawable, int i) {
        View inflate = LayoutInflater.from(BaseApplication.getContext()).inflate(R.layout.level_sign_item, (ViewGroup) null);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.level_badge_sign);
        healthTextView.setBackground(drawable);
        healthTextView.setTextColor(i);
        healthTextView.setText(str);
        return inflate;
    }

    public static Integer[] b() {
        return (Integer[]) e.clone();
    }

    public static Integer[] a() {
        return (Integer[]) d.clone();
    }

    public void c(String str, boolean z) {
        this.s = str;
        this.j = z;
    }
}
