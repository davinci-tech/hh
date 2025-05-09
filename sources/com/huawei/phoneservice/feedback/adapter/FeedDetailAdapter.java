package com.huawei.phoneservice.feedback.adapter;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.phoneservice.faq.base.util.ModuleConfigUtils;
import com.huawei.phoneservice.faq.base.util.k;
import com.huawei.phoneservice.faq.base.util.s;
import com.huawei.phoneservice.feedback.widget.a;
import com.huawei.phoneservice.feedbackcommon.db.FeedbackMediaData;
import com.huawei.phoneservice.feedbackcommon.entity.FeedBackResponse;
import com.huawei.phoneservice.feedbackcommon.entity.FeedMedia;
import com.huawei.phoneservice.feedbackcommon.entity.MediaEntity;
import com.huawei.phoneservice.feedbackcommon.photolibrary.MimeType;
import com.huawei.phoneservice.feedbackcommon.photolibrary.internal.entity.MediaItem;
import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class FeedDetailAdapter extends com.huawei.phoneservice.feedback.widget.a<FeedBackResponse.ProblemEnity> {
    private boolean b;
    private Context d;
    private f e;

    public interface f {
        void a(com.huawei.phoneservice.feedback.entity.d dVar);

        void a(String str, String str2, ImageView imageView, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, ImageView imageView2, long j);

        void a(List<MediaItem> list, int i);

        void b(com.huawei.phoneservice.feedback.entity.d dVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(com.huawei.phoneservice.feedback.widget.c cVar, int i) {
        c((com.huawei.phoneservice.feedback.adapter.d) cVar, a(i), i);
    }

    public void c(f fVar) {
        this.e = fVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public com.huawei.phoneservice.feedback.widget.c onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new com.huawei.phoneservice.feedback.adapter.d(LayoutInflater.from(this.d).inflate(R.layout.feedback_sdk_item_imgfrom_questionlist, viewGroup, false));
    }

    private void d(com.huawei.phoneservice.feedback.adapter.d dVar, FeedBackResponse.ProblemEnity problemEnity, int i) {
        if (TextUtils.isEmpty(problemEnity.getAnswer()) || !TextUtils.isEmpty(problemEnity.getScore()) || !ModuleConfigUtils.feedbackAssessmentEnabled(this.b)) {
            dVar.f.setVisibility(4);
            return;
        }
        dVar.d.getViewTreeObserver().addOnPreDrawListener(new d(dVar));
        dVar.f.setImageResource(R.drawable.feedback_sdk_ic_comment_useful);
        dVar.h.setImageResource(R.drawable.feedback_sdk_ic_comment_useless);
        dVar.h.setVisibility(0);
        dVar.f.setVisibility(0);
        dVar.f.setOnClickListener(new b(dVar, problemEnity, i));
        dVar.h.setOnClickListener(new c(dVar, problemEnity, i));
    }

    private void c(com.huawei.phoneservice.feedback.adapter.d dVar, FeedBackResponse.ProblemEnity problemEnity, int i) {
        b(dVar, problemEnity, i);
        b(dVar, problemEnity);
        a(dVar, problemEnity);
        if (TextUtils.isEmpty(problemEnity.getAnswer())) {
            dVar.b.setVisibility(8);
            dVar.g.setVisibility(8);
            dVar.k.setVisibility(8);
        } else {
            if (com.huawei.phoneservice.feedback.photolibrary.internal.utils.c.d(problemEnity.getAnswerTime(), this.d)) {
                dVar.b.setText(com.huawei.phoneservice.feedback.photolibrary.internal.utils.c.a(problemEnity.getAnswerTime(), "HH:mm", this.d));
            } else {
                dVar.b.setText(k.a(com.huawei.phoneservice.feedback.photolibrary.internal.utils.c.a(problemEnity.getAnswerTime(), "yyyy-MM-dd HH:mm", this.d), this.d).replace(Constants.LINK, "/"));
            }
            dVar.d.setText(problemEnity.getAnswer());
        }
        d(dVar, problemEnity, i);
        if (TextUtils.isEmpty(problemEnity.getAnswer()) || TextUtils.isEmpty(problemEnity.getScore()) || !ModuleConfigUtils.feedbackAssessmentEnabled(this.b)) {
            return;
        }
        dVar.g.setVisibility(0);
        if ("1".equals(problemEnity.getScore())) {
            dVar.f.setImageResource(R.drawable.feedback_sdk_ic_comment_useful_gray);
            dVar.f.setVisibility(0);
            dVar.f.setEnabled(false);
            dVar.h.setVisibility(8);
            dVar.f8254a.setText(this.d.getResources().getString(R.string._2130850911_res_0x7f02345f));
        }
        if ("0".equals(problemEnity.getScore())) {
            dVar.h.setImageResource(R.drawable.feedback_sdk_ic_comment_useless_gray);
            dVar.h.setVisibility(0);
            dVar.h.setEnabled(false);
            dVar.f.setVisibility(8);
            dVar.f8254a.setText(this.d.getResources().getString(R.string._2130850910_res_0x7f02345e));
        }
    }

    private void b(com.huawei.phoneservice.feedback.adapter.d dVar, FeedBackResponse.ProblemEnity problemEnity) {
        if (com.huawei.phoneservice.faq.base.util.b.b(problemEnity.getMediaItemList())) {
            dVar.i.setVisibility(8);
            return;
        }
        dVar.i.setVisibility(0);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.d);
        com.huawei.phoneservice.feedback.adapter.b bVar = new com.huawei.phoneservice.feedback.adapter.b(this.d, new e(new ArrayList(problemEnity.getMediaItemList())));
        dVar.i.setLayoutManager(linearLayoutManager);
        dVar.i.setAdapter(bVar);
        bVar.a(problemEnity.getMediaItemList());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<MediaItem> e(List<FeedMedia> list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            FeedMedia feedMedia = list.get(i);
            MediaEntity b2 = FeedbackMediaData.a(this.d).b(feedMedia.getAttachId());
            if (b2 != null) {
                String str = new File(b2.path).exists() ? b2.path : new File(b2.cache).exists() ? b2.cache : null;
                if (!TextUtils.isEmpty(str)) {
                    String suffixFromUrl = MimeType.getSuffixFromUrl(b2.url);
                    long parseLong = Long.parseLong(feedMedia.getSize());
                    Long l = b2.duration;
                    arrayList.add(new MediaItem(suffixFromUrl, str, parseLong, l == null ? 0L : l.longValue(), feedMedia.getAttachId(), b2.strUri));
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(com.huawei.phoneservice.feedback.entity.d dVar, com.huawei.phoneservice.feedback.adapter.d dVar2, FeedBackResponse.ProblemEnity problemEnity, int i, String str) {
        dVar.cdQ_(dVar2.f);
        dVar.a(dVar2.h);
        dVar.cdP_(dVar2.f8254a);
        dVar.cdO_(dVar2.g);
        dVar.c(problemEnity.getProblemId());
        dVar.d(i);
        dVar.d(str);
    }

    private void b(com.huawei.phoneservice.feedback.adapter.d dVar, FeedBackResponse.ProblemEnity problemEnity, int i) {
        TextView textView;
        String replace;
        if (i != 0 || this.d == null) {
            dVar.c.setText(problemEnity.getProblemDesc());
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(this.d.getResources().getString(R.string._2130850870_res_0x7f023436, problemEnity.getProblemDesc())).append("\n").append(this.d.getResources().getString(R.string._2130850871_res_0x7f023437)).append("\n").append(problemEnity.getProblemId());
            dVar.c.setText(stringBuffer);
        }
        if (com.huawei.phoneservice.feedback.photolibrary.internal.utils.c.d(problemEnity.getCreateTime(), this.d)) {
            textView = dVar.e;
            replace = com.huawei.phoneservice.feedback.photolibrary.internal.utils.c.a(problemEnity.getCreateTime(), "HH:mm", this.d);
        } else {
            String a2 = com.huawei.phoneservice.feedback.photolibrary.internal.utils.c.a(problemEnity.getCreateTime(), "yyyy-MM-dd HH:mm", this.d);
            textView = dVar.e;
            replace = k.a(a2, this.d).replace(Constants.LINK, "/");
        }
        textView.setText(replace);
    }

    private void a(com.huawei.phoneservice.feedback.adapter.d dVar, FeedBackResponse.ProblemEnity problemEnity) {
        if (problemEnity.getPicURL() == null) {
            dVar.j.setVisibility(8);
            return;
        }
        dVar.j.setVisibility(0);
        ArrayList arrayList = new ArrayList();
        arrayList.add(problemEnity.getPicURL());
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(problemEnity.getPicURL());
        dVar.j.setLayoutManager(new LinearLayoutManager(this.d));
        com.huawei.phoneservice.feedback.adapter.c cVar = new com.huawei.phoneservice.feedback.adapter.c(this.d, new a(arrayList2));
        dVar.j.setAdapter(cVar);
        cVar.a(arrayList);
    }

    /* loaded from: classes9.dex */
    static class UriDeserializer implements JsonDeserializer<Uri> {
        @Override // com.google.gson.JsonDeserializer
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Uri deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return Uri.parse(jsonElement.getAsString());
        }

        UriDeserializer() {
        }
    }

    class a implements a.InterfaceC0234a {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ List f8248a;

        @Override // com.huawei.phoneservice.feedback.widget.a.InterfaceC0234a
        public void a(int i, ImageView imageView, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, ImageView imageView2, String str, String str2, long j, boolean z) {
            if (FeedDetailAdapter.this.e != null) {
                FeedDetailAdapter feedDetailAdapter = FeedDetailAdapter.this;
                if (!z) {
                    List<MediaItem> e = feedDetailAdapter.e(this.f8248a);
                    int b = FeedDetailAdapter.this.b(e, str2);
                    if (b != -1) {
                        FeedDetailAdapter.this.e.a(e, b);
                        return;
                    }
                    return;
                }
                feedDetailAdapter.e.a(str, str2, imageView, relativeLayout, relativeLayout2, imageView2, j);
            }
        }

        a(List list) {
            this.f8248a = list;
        }
    }

    class b implements View.OnClickListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ FeedBackResponse.ProblemEnity f8249a;
        final /* synthetic */ com.huawei.phoneservice.feedback.adapter.d c;
        final /* synthetic */ int d;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (s.cdx_(view)) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            if (FeedDetailAdapter.this.e != null) {
                com.huawei.phoneservice.feedback.entity.d dVar = new com.huawei.phoneservice.feedback.entity.d();
                FeedDetailAdapter.this.e(dVar, this.c, this.f8249a, this.d, "1");
                FeedDetailAdapter.this.e.b(dVar);
            }
            ViewClickInstrumentation.clickOnView(view);
        }

        b(com.huawei.phoneservice.feedback.adapter.d dVar, FeedBackResponse.ProblemEnity problemEnity, int i) {
            this.c = dVar;
            this.f8249a = problemEnity;
            this.d = i;
        }
    }

    class c implements View.OnClickListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ FeedBackResponse.ProblemEnity f8250a;
        final /* synthetic */ int b;
        final /* synthetic */ com.huawei.phoneservice.feedback.adapter.d c;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (s.cdx_(view)) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            if (FeedDetailAdapter.this.e != null) {
                com.huawei.phoneservice.feedback.entity.d dVar = new com.huawei.phoneservice.feedback.entity.d();
                FeedDetailAdapter.this.e(dVar, this.c, this.f8250a, this.b, "0");
                FeedDetailAdapter.this.e.a(dVar);
            }
            ViewClickInstrumentation.clickOnView(view);
        }

        c(com.huawei.phoneservice.feedback.adapter.d dVar, FeedBackResponse.ProblemEnity problemEnity, int i) {
            this.c = dVar;
            this.f8250a = problemEnity;
            this.b = i;
        }
    }

    static class d implements ViewTreeObserver.OnPreDrawListener {
        private final com.huawei.phoneservice.feedback.adapter.d d;

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            this.d.d.getViewTreeObserver().removeOnPreDrawListener(this);
            if (this.d.d.getLineCount() >= 3) {
                this.d.o.setOrientation(1);
            } else {
                this.d.o.setOrientation(0);
            }
            return false;
        }

        d(com.huawei.phoneservice.feedback.adapter.d dVar) {
            this.d = dVar;
        }
    }

    class e implements a.InterfaceC0234a {
        final /* synthetic */ List e;

        @Override // com.huawei.phoneservice.feedback.widget.a.InterfaceC0234a
        public void a(int i, ImageView imageView, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, ImageView imageView2, String str, String str2, long j, boolean z) {
            if (FeedDetailAdapter.this.e != null) {
                FeedDetailAdapter feedDetailAdapter = FeedDetailAdapter.this;
                if (!z) {
                    List<MediaItem> e = feedDetailAdapter.e(this.e);
                    int b = FeedDetailAdapter.this.b(e, str2);
                    if (b != -1) {
                        FeedDetailAdapter.this.e.a(e, b);
                        return;
                    }
                    return;
                }
                feedDetailAdapter.e.a(str, str2, imageView, relativeLayout, relativeLayout2, imageView2, j);
            }
        }

        e(List list) {
            this.e = list;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int b(List<MediaItem> list, String str) {
        if (list == null) {
            return -1;
        }
        for (int i = 0; i < list.size(); i++) {
            if (str.equals(list.get(i).getAttachId())) {
                return i;
            }
        }
        return -1;
    }

    public FeedDetailAdapter(Context context, boolean z) {
        this.d = context;
        this.b = z;
    }
}
