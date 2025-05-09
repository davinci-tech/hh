package com.huawei.phoneservice.feedback.mvp.presenter;

import android.content.Context;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.phoneservice.feedback.mvp.contract.e;
import com.huawei.phoneservice.feedbackcommon.entity.FeedBackRequest;
import com.huawei.phoneservice.feedbackcommon.entity.FeedBackResponse;
import com.huawei.phoneservice.feedbackcommon.utils.OnHistoryListener;
import java.util.List;

/* loaded from: classes5.dex */
public class d extends com.huawei.phoneservice.feedback.mvp.base.a<e> implements com.huawei.phoneservice.feedback.mvp.contract.d {
    private Context b;
    private OnHistoryListener c;

    @Override // com.huawei.phoneservice.feedback.mvp.base.f
    public void a() {
    }

    @Override // com.huawei.phoneservice.feedback.mvp.base.f
    public void b() {
    }

    public void d(FeedBackRequest feedBackRequest) {
        com.huawei.phoneservice.feedbackcommon.utils.b.e().getFeedBackList(this.b, feedBackRequest.getProblemId(), feedBackRequest.getPageSize(), feedBackRequest.getStartWith(), feedBackRequest.getOrderType(), this.c);
    }

    public void c(Context context) {
        this.b = context;
    }

    class b implements OnHistoryListener {
        @Override // com.huawei.phoneservice.feedbackcommon.utils.OnHistoryListener
        public void showData(List<FeedBackResponse.ProblemEnity> list, List<FeedBackResponse.ProblemEnity> list2) {
            ((e) d.this.f8267a).a(list, list2);
        }

        @Override // com.huawei.phoneservice.feedbackcommon.utils.OnHistoryListener
        public void setThrowableView(Throwable th) {
            ((e) d.this.f8267a).setThrowableView(th);
        }

        @Override // com.huawei.phoneservice.feedbackcommon.utils.OnHistoryListener
        public void setErrorView(FaqConstants.FaqErrorCode faqErrorCode) {
            ((e) d.this.f8267a).setErrorView(faqErrorCode);
        }

        b() {
        }
    }

    public d(e eVar) {
        super(eVar);
        this.c = new b();
    }
}
