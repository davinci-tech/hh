package com.huawei.watchface.api;

import com.huawei.watchface.bi;
import com.huawei.watchface.cd;
import com.huawei.watchface.ce;
import com.huawei.watchface.mvp.model.datatype.BatchReportResp;
import com.huawei.watchface.mvp.model.datatype.ResApplyRecord;
import com.huawei.watchface.mvp.model.datatype.orderhistory.OrderHistoryRequestModel;
import com.huawei.watchface.mvp.model.datatype.orderhistory.OrderHistoryResponse;
import com.huawei.watchface.mvp.model.datatype.querysubinfodetail.QuerySubInfoDetailResp;
import com.huawei.watchface.utils.BackgroundTaskUtils;
import com.huawei.watchface.utils.HwLog;
import java.util.List;

/* loaded from: classes7.dex */
public class MembershipRepository {
    public static void postBatchReport(List<ResApplyRecord> list, ResponseListener<BatchReportResp> responseListener) {
        new bi(list, responseListener);
    }

    public static void getQueryAll(ResponseListener<QuerySubInfoDetailResp> responseListener) {
        new ce(responseListener);
    }

    public static void getOrderHistoryResponse(final List<OrderHistoryRequestModel> list, final ResponseListener<OrderHistoryResponse> responseListener) {
        BackgroundTaskUtils.submit(new Runnable() { // from class: com.huawei.watchface.api.MembershipRepository$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                MembershipRepository.lambda$getOrderHistoryResponse$0(list, responseListener);
            }
        });
    }

    static /* synthetic */ void lambda$getOrderHistoryResponse$0(List list, ResponseListener responseListener) {
        cd cdVar = new cd();
        OrderHistoryResponse c = cdVar.c(cdVar.a((List<OrderHistoryRequestModel>) list));
        if (c == null) {
            HwLog.e("OrderHistoryResponse", "could not get any response");
            responseListener.onError();
        } else {
            responseListener.onResponse(c);
        }
    }
}
