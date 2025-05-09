package com.huawei.phoneservice.feedback.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.phoneservice.faq.base.util.s;
import com.huawei.phoneservice.feedbackcommon.photolibrary.internal.entity.MediaItem;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class ProblemSuggestPhotoAdapter extends BaseAdapter {

    /* renamed from: a, reason: collision with root package name */
    private List<MediaItem> f8252a = new ArrayList();
    private b d;
    private Context e;

    public interface b {
        void a(int i);

        void b(int i);

        void f();
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        d dVar;
        if (view == null) {
            dVar = new d();
            view2 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.feedback_sdk_problem_item_image, viewGroup, false);
            dVar.c = (ImageView) view2.findViewById(R.id.feedback_sdk_iv_pic);
            dVar.e = (ImageView) view2.findViewById(R.id.feedback_sdk_iv_del);
            dVar.b = (ImageView) view2.findViewById(R.id.feedback_sdk_iv_add);
            view2.setTag(dVar);
        } else {
            view2 = view;
            dVar = (d) view.getTag();
        }
        if (i != getCount() - 1 || this.f8252a.size() >= com.huawei.phoneservice.feedbackcommon.utils.b.a()) {
            dVar.c.setVisibility(0);
            dVar.b.setVisibility(8);
            dVar.e.setVisibility(0);
            a aVar = new a(i);
            dVar.e.setOnClickListener(aVar);
            dVar.c.setOnClickListener(aVar);
            MediaItem item = getItem(i);
            if (this.e != null && item != null && item.getFilePath() != null && dVar.c != null) {
                Glide.with(this.e.getApplicationContext()).load(item.getFilePath()).into(dVar.c);
            }
        } else {
            dVar.c.setVisibility(8);
            dVar.b.setVisibility(0);
            dVar.e.setVisibility(8);
            dVar.b.setOnClickListener(new a(i));
        }
        return view2;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f8252a.size() >= com.huawei.phoneservice.feedbackcommon.utils.b.a() ? com.huawei.phoneservice.feedbackcommon.utils.b.a() : this.f8252a.size() + 1;
    }

    public void c(List<MediaItem> list) {
        this.f8252a = list;
        if (list == null) {
            this.f8252a = new ArrayList();
        }
        notifyDataSetChanged();
    }

    public void e(b bVar) {
        this.d = bVar;
    }

    class a implements View.OnClickListener {
        private int d;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (s.cdx_(view) || ProblemSuggestPhotoAdapter.this.d == null) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            int id = view.getId();
            if (id == R.id.feedback_sdk_iv_pic) {
                ProblemSuggestPhotoAdapter.this.d.a(this.d);
            } else if (id == R.id.feedback_sdk_iv_add) {
                ProblemSuggestPhotoAdapter.this.d.f();
            } else if (id == R.id.feedback_sdk_iv_del) {
                ProblemSuggestPhotoAdapter.this.d.b(this.d);
            }
            ViewClickInstrumentation.clickOnView(view);
        }

        a(int i) {
            this.d = i;
        }
    }

    @Override // android.widget.Adapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public MediaItem getItem(int i) {
        List<MediaItem> list = this.f8252a;
        if (list == null || i < 0 || i >= list.size()) {
            return null;
        }
        return this.f8252a.get(i);
    }

    static class d {
        ImageView b;
        ImageView c;
        ImageView e;

        d() {
        }
    }

    public ProblemSuggestPhotoAdapter(Context context) {
        this.e = context;
    }
}
