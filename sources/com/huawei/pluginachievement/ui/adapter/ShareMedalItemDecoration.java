package com.huawei.pluginachievement.ui.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.hms.ads.jsb.constant.Constant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.watchface.videoedit.gles.transition.SquareChangeAnimation;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class ShareMedalItemDecoration extends RecyclerView.ItemDecoration {
    private HashMap<String, Integer> c;
    private Context d;
    private String e = "true";
    private int b = 1;

    public ShareMedalItemDecoration(Context context, int i) {
        this.d = context;
        b(i);
    }

    public int a() {
        return this.b;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        if (rect == null || this.c == null) {
            return;
        }
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager == null) {
            LogUtil.h("PLGACHIEVE_ShareMedalItemDecoration", "getItemOffsets: layoutManager is null");
            return;
        }
        int itemCount = layoutManager.getItemCount();
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        int i = childAdapterPosition % this.b;
        if (this.e.equals("true")) {
            Integer num = this.c.get(SquareChangeAnimation.LEFT);
            if (num != null) {
                rect.left = num.intValue() - ((num.intValue() * i) / this.b);
            } else {
                LogUtil.h("PLGACHIEVE_ShareMedalItemDecoration", "getItemOffsets: itemDecorationLeft is null");
            }
            Integer num2 = this.c.get(SquareChangeAnimation.RIGHT);
            if (num2 != null) {
                rect.right = ((i + 1) * num2.intValue()) / this.b;
            } else {
                LogUtil.h("PLGACHIEVE_ShareMedalItemDecoration", "getItemOffsets: itemDecorationRight is null");
            }
            if (childAdapterPosition < this.b) {
                Integer num3 = this.c.get(Constant.MAP_KEY_TOP);
                if (num3 != null) {
                    rect.top = num3.intValue();
                } else {
                    LogUtil.h("PLGACHIEVE_ShareMedalItemDecoration", "getItemOffsets: itemDecorationTop is null");
                }
            }
            if (childAdapterPosition >= this.b) {
                Integer num4 = this.c.get("bottom");
                if (num4 != null) {
                    rect.top = num4.intValue();
                } else {
                    LogUtil.h("PLGACHIEVE_ShareMedalItemDecoration", "getItemOffsets: itemDecorationBottom is null");
                }
            }
            if (childAdapterPosition >= itemCount - this.b) {
                Integer num5 = this.c.get("end");
                if (num5 != null) {
                    rect.bottom = num5.intValue();
                } else {
                    LogUtil.h("PLGACHIEVE_ShareMedalItemDecoration", "getItemOffsets: itemDecorationEnd is null");
                }
            }
        }
    }

    private void b(int i) {
        this.c = new HashMap<>(5);
        if (i == 1) {
            this.b = 1;
            e(50, 16, 16, 16, 16);
        } else if (i == 2) {
            this.b = 2;
            e(70, 44, 44, 24, 16);
        } else if (i > 2 && i < 5) {
            this.b = 2;
            e(20, 44, 44, 24, 16);
        } else if (i == 5 || i == 6) {
            this.b = 3;
            e(40, 16, 16, 30, 0);
        } else if (i >= 7) {
            this.b = 3;
            e(20, 16, 16, 12, 8);
        } else {
            LogUtil.h("PLGACHIEVE_ShareMedalItemDecoration", "medalCount error:", Integer.valueOf(i));
            return;
        }
        LogUtil.a("PLGACHIEVE_ShareMedalItemDecoration", "medalCount =", Integer.valueOf(i), "spanCount =", Integer.valueOf(this.b), " includeEdge =", this.e);
    }

    private void e(int i, int i2, int i3, int i4, int i5) {
        this.e = "true";
        this.c.put(Constant.MAP_KEY_TOP, Integer.valueOf(a(this.d, i)));
        this.c.put(SquareChangeAnimation.LEFT, Integer.valueOf(a(this.d, i2)));
        this.c.put(SquareChangeAnimation.RIGHT, Integer.valueOf(a(this.d, i3)));
        this.c.put("bottom", Integer.valueOf(a(this.d, i4)));
        this.c.put("end", Integer.valueOf(a(this.d, i5)));
    }

    private int a(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
