package defpackage;

import android.os.Handler;
import android.os.Message;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.ui.main.stories.health.request.InvoiceDetailInfo;
import com.huawei.ui.main.stories.health.request.InvoiceRequestInfo;
import health.compact.a.LogUtil;
import java.util.List;

/* loaded from: classes8.dex */
public class qrk {
    public static void dHJ_(String str, Handler handler) {
        dHH_(str, handler, 3000L);
    }

    public static void dHI_(String str, Handler handler) {
        dHH_(str, handler, 6000L);
    }

    public static void dHH_(final String str, final Handler handler, long j) {
        handler.postDelayed(new Runnable() { // from class: qri
            @Override // java.lang.Runnable
            public final void run() {
                qrk.dHG_(str, handler);
            }
        }, j);
    }

    public static void dHG_(String str, final Handler handler) {
        qrg.a(str, new UiCallback<List<InvoiceRequestInfo>>() { // from class: qrk.3
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str2) {
                Message.obtain(handler, 2).sendToTarget();
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List<InvoiceRequestInfo> list) {
                if (koq.b(list)) {
                    LogUtil.e("InvoiceUtil", "query results is null");
                    Message.obtain(handler, 2).sendToTarget();
                    return;
                }
                InvoiceRequestInfo invoiceRequestInfo = list.get(0);
                InvoiceDetailInfo b = qrk.b(list);
                Message obtain = Message.obtain(handler, invoiceRequestInfo.getRequestStatus().intValue());
                obtain.obj = b.getInvoiceTime();
                obtain.arg1 = invoiceRequestInfo.getCustomerType().intValue();
                obtain.sendToTarget();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static InvoiceDetailInfo b(List<InvoiceRequestInfo> list) {
        LogUtil.c("InvoiceUtil", "parseQueryResult");
        InvoiceDetailInfo invoiceDetailInfo = new InvoiceDetailInfo();
        invoiceDetailInfo.setInvoiceStatus(0);
        if (koq.b(list)) {
            return invoiceDetailInfo;
        }
        List<InvoiceDetailInfo> invoiceList = list.get(0).getInvoiceList();
        return koq.b(invoiceList) ? invoiceDetailInfo : invoiceList.get(0);
    }
}
