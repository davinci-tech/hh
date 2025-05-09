package com.huawei.pluginsocialshare.interactors;

import android.content.Context;
import com.huawei.caas.messageservice.HwCaasShareCallBack;
import com.huawei.caas.messageservice.HwCaasShareHandler;
import com.huawei.caas.messageservice.HwCaasShareManager;
import com.huawei.caas.messageservice.HwShareMsgInfo;
import com.huawei.caas.messageservice.HwShareUtils;
import com.huawei.health.socialshare.model.CaasShareCallBack;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes9.dex */
public class HwCaasShareInteractors {
    private Context c;
    private HwCaasShareManager d = HwCaasShareManager.getInstance();
    private a e;

    public HwCaasShareInteractors(Context context) {
        this.c = context;
    }

    public void b(CaasShareCallBack caasShareCallBack) {
        a aVar = new a(caasShareCallBack);
        this.e = aVar;
        this.d.init(this.c, aVar);
    }

    public void c(int i, HwShareMsgInfo hwShareMsgInfo) {
        if (this.e.b() != null) {
            this.e.b().sendShareMsgInfo(i, hwShareMsgInfo);
            LogUtil.a("Share_CaaSShareInteractors", "sendShareMsgInfo");
        }
    }

    public void e() {
        HwCaasShareManager hwCaasShareManager = this.d;
        if (hwCaasShareManager != null) {
            hwCaasShareManager.release();
            this.c = null;
            LogUtil.a("Share_CaaSShareInteractors", "release");
        }
    }

    static class a implements HwCaasShareCallBack {
        private CaasShareCallBack d;
        private HwCaasShareHandler e;
        private int c = 0;

        /* renamed from: a, reason: collision with root package name */
        private int f8526a = -1;

        a(CaasShareCallBack caasShareCallBack) {
            this.d = caasShareCallBack;
        }

        @Override // com.huawei.caas.messageservice.HwCaasShareCallBack
        public void initSuccess(HwCaasShareHandler hwCaasShareHandler) {
            if (hwCaasShareHandler == null) {
                LogUtil.b("Share_CaaSShareInteractors", "initSuccess error");
                CaasShareCallBack caasShareCallBack = this.d;
                if (caasShareCallBack != null) {
                    caasShareCallBack.onInitCallback(this.f8526a);
                    return;
                }
                return;
            }
            this.e = hwCaasShareHandler;
            CaasShareCallBack caasShareCallBack2 = this.d;
            if (caasShareCallBack2 != null) {
                caasShareCallBack2.onInitCallback(this.c);
            }
            LogUtil.a("Share_CaaSShareInteractors", "initSuccess");
        }

        @Override // com.huawei.caas.messageservice.HwCaasShareCallBack
        public void initFail(int i) {
            LogUtil.b("Share_CaaSShareInteractors", "initFail retCode: ", Integer.valueOf(i));
            CaasShareCallBack caasShareCallBack = this.d;
            if (caasShareCallBack != null) {
                caasShareCallBack.onInitCallback(i);
            }
        }

        @Override // com.huawei.caas.messageservice.HwCaasShareCallBack
        public void releaseSuccess() {
            LogUtil.a("Share_CaaSShareInteractors", "releaseSuccess");
            this.e = null;
            this.d = null;
        }

        @Override // com.huawei.caas.messageservice.HwCaasShareCallBack
        public void sendResult(HwShareUtils.SendResultEnum sendResultEnum) {
            int i = AnonymousClass3.d[sendResultEnum.ordinal()];
            if (i == 1) {
                LogUtil.a("Share_CaaSShareInteractors", "SEND_SUCCESS");
            } else if (i == 2) {
                LogUtil.h("Share_CaaSShareInteractors", "SEND_FAIL");
            } else if (i == 3) {
                LogUtil.h("Share_CaaSShareInteractors", "SEND_CANCEL");
            } else {
                LogUtil.h("Share_CaaSShareInteractors", "SEND_FAIL with unknown");
            }
            CaasShareCallBack caasShareCallBack = this.d;
            if (caasShareCallBack != null) {
                caasShareCallBack.onSendCallback(sendResultEnum.ordinal());
            }
        }

        public HwCaasShareHandler b() {
            return this.e;
        }
    }

    /* renamed from: com.huawei.pluginsocialshare.interactors.HwCaasShareInteractors$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] d;

        static {
            int[] iArr = new int[HwShareUtils.SendResultEnum.values().length];
            d = iArr;
            try {
                iArr[HwShareUtils.SendResultEnum.SEND_SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                d[HwShareUtils.SendResultEnum.SEND_FAIL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                d[HwShareUtils.SendResultEnum.SEND_CANCEL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
