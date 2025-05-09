package com.huawei.featureuserprofile.todo.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.TextAppearanceSpan;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.featureuserprofile.todo.activity.OnItemClickListener;
import com.huawei.featureuserprofile.todo.adapter.RecyclerViewAdpater;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.bvw;
import defpackage.eme;
import defpackage.gka;
import defpackage.koq;
import defpackage.nmj;
import defpackage.nrf;
import defpackage.nsk;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class RecyclerViewAdpater extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements RecyclerView.OnItemTouchListener {

    /* renamed from: a, reason: collision with root package name */
    private ViewGroup f2049a;
    private List<gka> b;
    private OnItemClickListener<gka> c;
    private GestureDetectorCompat d;
    private LayoutInflater e;

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onRequestDisallowInterceptTouchEvent(boolean z) {
    }

    public void d(OnItemClickListener<gka> onItemClickListener) {
        this.c = onItemClickListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (this.e == null) {
            this.e = LayoutInflater.from(viewGroup.getContext());
        }
        if (this.d == null && (viewGroup instanceof RecyclerView)) {
            this.d = new GestureDetectorCompat(viewGroup.getContext(), new c((RecyclerView) viewGroup, this, this.c));
        }
        if (this.f2049a == null) {
            this.f2049a = viewGroup;
        }
        if (i == 1) {
            return new a(this.e.inflate(R.layout.item_todo_list_recyclerview, (ViewGroup) null));
        }
        if (i == 2) {
            View view = new View(viewGroup.getContext());
            view.setLayoutParams(new ViewGroup.LayoutParams(1, viewGroup.getContext().getResources().getDimensionPixelSize(R.dimen._2131363094_res_0x7f0a0516)));
            return new RecyclerHolder(view);
        }
        LogUtil.a("RecyclerViewAdpater", "error type");
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        if (!koq.b(this.b, i) && (viewHolder instanceof a)) {
            a aVar = (a) viewHolder;
            aVar.d(this.b.get(i));
            aVar.itemView.setOnClickListener(new View.OnClickListener() { // from class: bvg
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RecyclerViewAdpater.this.vB_(i, view);
                }
            });
        }
    }

    public /* synthetic */ void vB_(int i, View view) {
        this.c.onItemClick(view, i, this.b.get(i));
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (koq.b(this.b) || i < 0 || i > this.b.size()) {
            return -1;
        }
        return i == this.b.size() ? 2 : 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (koq.b(this.b)) {
            return 0;
        }
        return this.b.size() + 1;
    }

    public void b(List<gka> list) {
        if (koq.c(list)) {
            if (this.b == null) {
                this.b = new ArrayList(list.size());
            }
            this.b.clear();
            this.b.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        GestureDetectorCompat gestureDetectorCompat = this.d;
        if (gestureDetectorCompat == null) {
            return false;
        }
        gestureDetectorCompat.onTouchEvent(motionEvent);
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        this.d.onTouchEvent(motionEvent);
    }

    static class c extends GestureDetector.SimpleOnGestureListener {
        private final OnItemClickListener<gka> b;
        private final RecyclerViewAdpater c;
        private final RecyclerView d;

        c(RecyclerView recyclerView, RecyclerViewAdpater recyclerViewAdpater, OnItemClickListener<gka> onItemClickListener) {
            this.d = recyclerView;
            this.c = recyclerViewAdpater;
            this.b = onItemClickListener;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            RecyclerView.ViewHolder childViewHolder;
            RecyclerView recyclerView = this.d;
            if (recyclerView == null || this.c == null || this.b == null) {
                return false;
            }
            View findChildViewUnder = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
            if (findChildViewUnder != null && (childViewHolder = this.d.getChildViewHolder(findChildViewUnder)) != null && childViewHolder.getAdapterPosition() >= 0) {
                return true;
            }
            this.b.onOtherClick();
            return true;
        }
    }

    static class a extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f2050a;
        ImageView b;
        HealthTextView d;

        private boolean b(int i) {
            return i == 256 || i == 512;
        }

        a(View view) {
            super(view);
        }

        public void d(gka gkaVar) {
            this.b = (ImageView) this.itemView.findViewById(R.id.todo_icon);
            this.d = (HealthTextView) this.itemView.findViewById(R.id.todo_name);
            this.f2050a = (HealthTextView) this.itemView.findViewById(R.id.todo_progress);
            vD_(this.b, gkaVar);
            d(this.d, gkaVar);
            e(this.f2050a, gkaVar);
        }

        private void vD_(ImageView imageView, gka gkaVar) {
            int i;
            int i2;
            if (imageView == null || gkaVar == null) {
                return;
            }
            int k = gkaVar.k();
            imageView.setBackgroundResource(R.color._2131299296_res_0x7f090be0);
            if (gkaVar.d() > 0) {
                i2 = gkaVar.d();
            } else if (b(k)) {
                i2 = gkaVar.o().getIconId();
            } else {
                if (k == 768) {
                    try {
                        i = Integer.parseInt(bvw.d(BleConstants.SPORT_TYPE, gkaVar.m()));
                    } catch (NumberFormatException e) {
                        LogUtil.b("RecyclerViewAdpater", "setIcon Exception = ", ExceptionUtils.d(e));
                        i = 258;
                    }
                    Drawable acquireSportTypeDrawable = eme.b().acquireSportTypeDrawable(i);
                    Context context = BaseApplication.getContext();
                    imageView.setBackground(nrf.cJH_(context.getDrawable(R.drawable._2131431552_res_0x7f0b1080), context.getColor(R.color._2131296927_res_0x7f09029f)));
                    imageView.setImageDrawable(acquireSportTypeDrawable);
                    return;
                }
                i2 = R.drawable._2131430520_res_0x7f0b0c78;
            }
            imageView.setImageResource(i2);
        }

        private void d(HealthTextView healthTextView, gka gkaVar) {
            if (healthTextView == null || gkaVar == null) {
                LogUtil.h("RecyclerViewAdpater", "initTitle textView or bean is null");
            } else {
                healthTextView.setText(gkaVar.n());
            }
        }

        private void e(HealthTextView healthTextView, gka gkaVar) {
            if (healthTextView == null || gkaVar == null) {
                LogUtil.h("RecyclerViewAdpater", "initValue textView or bean is null");
            } else {
                if (!TextUtils.isEmpty(gkaVar.f())) {
                    healthTextView.setVisibility(0);
                    healthTextView.setText(vC_(gkaVar.f()));
                    healthTextView.setLayoutDirection(LanguageUtil.bc(healthTextView.getContext()) ? 1 : 0);
                    return;
                }
                healthTextView.setVisibility(8);
            }
        }

        private SpannableString vC_(CharSequence charSequence) {
            LogUtil.a("RecyclerViewAdpater", charSequence);
            Context context = this.itemView.getContext();
            SpannableString spannableString = new SpannableString(charSequence);
            int length = spannableString.length();
            spannableString.setSpan(new TextAppearanceSpan(this.itemView.getContext(), R.style.health_todo_suffix_style), 0, length, 18);
            String[] split = charSequence.toString().split("/");
            int length2 = split[0].length();
            if (length2 == 0) {
                length2 = length;
            }
            boolean z = length2 == length || "--".equals(split[0]);
            if (length2 <= length) {
                spannableString.setSpan(new TextAppearanceSpan(context, R.style.health_todo_prefix_style), 0, length2, 17);
                spannableString.setSpan(new AbsoluteSizeSpan(nsn.a(context, context.getResources().getDimensionPixelSize(z ? R.dimen._2131365061_res_0x7f0a0cc5 : R.dimen._2131365076_res_0x7f0a0cd4)), true), 0, length2, 17);
            }
            spannableString.setSpan(new nmj(nsk.cKN_()), 0, length, 17);
            LogUtil.a("RecyclerViewAdpater", spannableString.toString());
            return spannableString;
        }
    }
}
