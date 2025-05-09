package defpackage;

import android.content.Intent;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.ui.homehealth.device.CardDeviceFragment;
import com.huawei.ui.homehealth.device.callback.ReconnectCallback;
import com.huawei.ui.homehealth.device.sitting.RecommendedItem;
import com.huawei.ui.homehealth.view.CloudReconnectFailDialog;
import com.huawei.ui.homewear21.home.manager.hwnfcwalletmgr.WalletAppManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class oee extends BaseHandler<CardDeviceFragment> {
    public oee(CardDeviceFragment cardDeviceFragment) {
        super(cardDeviceFragment);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.haf.handler.BaseHandler
    /* renamed from: cXp_, reason: merged with bridge method [inline-methods] */
    public void handleMessageWhenReferenceNotNull(CardDeviceFragment cardDeviceFragment, Message message) {
        int i = message.what;
        if (i == 34) {
            LogUtil.a("CardDeviceFragment CardDeviceFragmentHandler", "handleMessageWhenReferenceNotNull battery or place");
            cardDeviceFragment.j.notifyDataSetChanged();
            return;
        }
        if (i == 43) {
            if (cardDeviceFragment.s != null) {
                LogUtil.a("CardDeviceFragment CardDeviceFragmentHandler", "handleMessageWhenReferenceNotNull fragment.mGetDataService is: ", cardDeviceFragment.s);
                cardDeviceFragment.s.execute(new Runnable() { // from class: oei
                    @Override // java.lang.Runnable
                    public final void run() {
                        ogj.j();
                    }
                });
                return;
            }
            return;
        }
        if (i != 46) {
            switch (i) {
                case 30:
                    LogUtil.a("CardDeviceFragment CardDeviceFragmentHandler", "reconnect device timeout: ", Integer.valueOf(message.what));
                    cXk_(cardDeviceFragment, message);
                    break;
                case 31:
                    LogUtil.a("CardDeviceFragment CardDeviceFragmentHandler", "handleMessageWhenReferenceNotNull device connect time out");
                    removeMessages(31);
                    cardDeviceFragment.j.a(false);
                    cardDeviceFragment.h();
                    if (message.arg1 == 1 && ogj.i()) {
                        cXg_(message, cardDeviceFragment);
                        break;
                    }
                    break;
                case 32:
                    cXh_(cardDeviceFragment, message);
                    break;
                default:
                    cXl_(message, cardDeviceFragment);
                    break;
            }
            return;
        }
        cardDeviceFragment.cXa_(message);
    }

    private void cXg_(Message message, final CardDeviceFragment cardDeviceFragment) {
        if (!(message.obj instanceof cpm)) {
            LogUtil.h("CardDeviceFragment CardDeviceFragmentHandler", "cloudDeviceDialog message.obj instanceof DeviceInfoForWear");
            return;
        }
        final cpm cpmVar = (cpm) message.obj;
        if (message.arg2 == 1) {
            LogUtil.a("CardDeviceFragment CardDeviceFragmentHandler", "cloudDeviceDialog clickFrom = 1");
            ogj.cZy_(cardDeviceFragment.g, cpl.c().Kj_(cpmVar.i()));
            return;
        }
        CloudReconnectFailDialog.Builder builder = new CloudReconnectFailDialog.Builder(cardDeviceFragment.g);
        builder.c(cpmVar.i(), jfu.c(cpmVar.i()).d()).e(new ReconnectCallback() { // from class: oee.3
            @Override // com.huawei.ui.homehealth.device.callback.ReconnectCallback
            public void reconnect() {
                cardDeviceFragment.a(cpmVar.a());
                cardDeviceFragment.j.a(true);
                cardDeviceFragment.h();
                cardDeviceFragment.x.c(cardDeviceFragment, cpmVar, 1);
            }
        });
        CloudReconnectFailDialog b = builder.b();
        b.setCanceledOnTouchOutside(false);
        if (b.isShowing() || cardDeviceFragment.getActivity().isFinishing()) {
            return;
        }
        b.show();
    }

    private void cXh_(CardDeviceFragment cardDeviceFragment, Message message) {
        jiw.a().c();
        String str = (String) message.obj;
        if (!TextUtils.isEmpty(str) && str.equals(cardDeviceFragment.ao)) {
            LogUtil.a("CardDeviceFragment CardDeviceFragmentHandler", "handleMessageWhenReferenceNotNull MSG_DISCONNECTED");
            removeMessages(31);
            cardDeviceFragment.j.a(false);
            cardDeviceFragment.a((String) null);
        }
        cardDeviceFragment.h();
        cardDeviceFragment.a(jpt.e());
        if (cardDeviceFragment.bg != null && (message.obj instanceof String) && cardDeviceFragment.p.equals((String) message.obj)) {
            cardDeviceFragment.bg.e();
        }
        if (ogj.i() && cardDeviceFragment.j.b() == 3) {
            cardDeviceFragment.j.e();
            ogj.cZy_(cardDeviceFragment.g, cpl.c().Kj_(message.arg1));
        }
    }

    private void cXk_(CardDeviceFragment cardDeviceFragment, Message message) {
        String str = (String) message.obj;
        if (!TextUtils.isEmpty(str) && str.equals(cardDeviceFragment.ao)) {
            LogUtil.a("CardDeviceFragment CardDeviceFragmentHandler", "dealWithMsgConnected enter");
            removeMessages(31);
            cardDeviceFragment.j.a(false);
            cardDeviceFragment.a((String) null);
        }
        cardDeviceFragment.j.a(false);
        cardDeviceFragment.j.e();
        cardDeviceFragment.h();
        ogj.b(cardDeviceFragment.s);
        cardDeviceFragment.a(jpt.e());
    }

    private void cXl_(Message message, CardDeviceFragment cardDeviceFragment) {
        int i = message.what;
        if (i == 12) {
            LogUtil.a("CardDeviceFragment CardDeviceFragmentHandler", "DEVICE_GET_CURRENT_INFO_LIST message");
            if (message.obj instanceof List) {
                cardDeviceFragment.ak.clear();
                cardDeviceFragment.ak.addAll((List) message.obj);
                cardDeviceFragment.y = true;
            }
            cardDeviceFragment.j();
            return;
        }
        if (i != 33) {
            if (i == 35) {
                LogUtil.a("CardDeviceFragment CardDeviceFragmentHandler", "MSG_DEVICE_DELETE message: ", Integer.valueOf(message.what));
                cardDeviceFragment.h();
                return;
            } else {
                if (i == 36) {
                    removeMessages(31);
                    cardDeviceFragment.j.a(false);
                    cardDeviceFragment.h();
                    ogj.a(cardDeviceFragment.g, message.arg1);
                    return;
                }
                cXn_(message, cardDeviceFragment);
                return;
            }
        }
        LogUtil.a("CardDeviceFragment CardDeviceFragmentHandler", "msg_connect_change state:", Boolean.valueOf(cardDeviceFragment.j.a()), "message: ", Integer.valueOf(message.what));
        if (!(message.obj instanceof String)) {
            LogUtil.h("CardDeviceFragment CardDeviceFragmentHandler", "!(object instanceof String)");
            return;
        }
        if (!ogj.d((String) message.obj)) {
            LogUtil.h("CardDeviceFragment CardDeviceFragmentHandler", "isContinueConnecting false");
            return;
        }
        cardDeviceFragment.a((String) message.obj);
        cardDeviceFragment.j.a(true);
        cardDeviceFragment.h();
        cardDeviceFragment.cWY_(message);
    }

    private void cXn_(Message message, CardDeviceFragment cardDeviceFragment) {
        int i = message.what;
        if (i != 37) {
            if (i == 38) {
                cXj_(message, cardDeviceFragment);
                return;
            }
            if (i == 44) {
                cardDeviceFragment.j(cardDeviceFragment);
                return;
            } else if (i == 47) {
                cXi_(message, cardDeviceFragment);
                return;
            } else {
                cXm_(message, cardDeviceFragment);
                return;
            }
        }
        if (!(message.obj instanceof String)) {
            LogUtil.h("CardDeviceFragment CardDeviceFragmentHandler", "handlerDeviceWatchMessage message.obj instanceof String");
            return;
        }
        String str = (String) message.obj;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("CardDeviceFragment CardDeviceFragmentHandler", "handlerDeviceWatchMessage httpUrl is null");
            return;
        }
        Intent intent = new Intent();
        intent.setClass(cardDeviceFragment.g, WebViewActivity.class);
        intent.putExtra("url", str);
        ogj.cZC_(cardDeviceFragment.g, intent, "WebViewActivity");
    }

    private void cXi_(Message message, CardDeviceFragment cardDeviceFragment) {
        if (!(message.obj instanceof cpm)) {
            LogUtil.h("CardDeviceFragment CardDeviceFragmentHandler", "dealOverseaWallet message.obj not instanceof DeviceInfoForWear");
            return;
        }
        cpm cpmVar = (cpm) message.obj;
        int i = message.arg1;
        boolean isSupportWalletApp = WalletAppManager.getInstance().getIsSupportWalletApp(cpmVar.a());
        boolean z = i == 1;
        if (isSupportWalletApp == z) {
            LogUtil.a("CardDeviceFragment CardDeviceFragmentHandler", "dealOverseaWallet oversea wallet not change");
            return;
        }
        WalletAppManager.getInstance().setIsSupportWalletApp(cpmVar.a(), z);
        if (z) {
            LogUtil.a("CardDeviceFragment CardDeviceFragmentHandler", "dealOverseaWallet show oversea wallet");
            if (cardDeviceFragment.b(3)) {
                return;
            } else {
                cardDeviceFragment.as.add(ogj.c(3, cpmVar));
            }
        } else {
            LogUtil.a("CardDeviceFragment CardDeviceFragmentHandler", "dealOverseaWallet hide oversea wallet");
            if (!cardDeviceFragment.b(3)) {
                return;
            }
            Iterator<RecommendedItem> it = cardDeviceFragment.as.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (it.next().getId() == 3) {
                    it.remove();
                    break;
                }
            }
        }
        ogj.e(cardDeviceFragment.as);
        cardDeviceFragment.ae = true;
        cardDeviceFragment.g();
    }

    private void cXj_(Message message, CardDeviceFragment cardDeviceFragment) {
        if (!(message.obj instanceof cpm)) {
            LogUtil.h("CardDeviceFragment CardDeviceFragmentHandler", "handlerDeviceWatchMessage message.obj instanceof DeviceInfoForWear");
            return;
        }
        cardDeviceFragment.as.clear();
        cpm cpmVar = (cpm) message.obj;
        cardDeviceFragment.be = message.arg1;
        DeviceCapability c = ogj.c(cpmVar.a());
        if (c == null && cpmVar.e() == 2) {
            LogUtil.h("CardDeviceFragment CardDeviceFragmentHandler", "handlerDeviceWatchMessage deviceCapability is null");
            cardDeviceFragment.ae = true;
            cardDeviceFragment.g();
            return;
        }
        if (cardDeviceFragment.be == 1) {
            if (!Utils.o()) {
                cardDeviceFragment.as.add(ogj.c(1, cpmVar));
            } else {
                cardDeviceFragment.c(cpmVar);
                if (cpmVar.e() != 2) {
                    return;
                }
            }
        }
        if (c == null) {
            LogUtil.h("CardDeviceFragment CardDeviceFragmentHandler", "deviceCapability is null");
            return;
        }
        cardDeviceFragment.c(cpmVar, c);
        ogj.e(cardDeviceFragment.as);
        cardDeviceFragment.ae = true;
        cardDeviceFragment.g();
    }

    private void cXm_(Message message, CardDeviceFragment cardDeviceFragment) {
        int i = message.what;
        if (i == 10) {
            DeviceInfo deviceInfo = message.obj instanceof DeviceInfo ? (DeviceInfo) message.obj : null;
            if (deviceInfo == null || deviceInfo.getDeviceConnectState() != 2) {
                return;
            }
            cardDeviceFragment.o();
            return;
        }
        if (i == 11) {
            d(cardDeviceFragment);
            return;
        }
        if (i != 13) {
            if (i == 39) {
                cardDeviceFragment.cXc_(message);
                return;
            } else if (i == 391) {
                cardDeviceFragment.cXd_(message);
                return;
            } else {
                cXo_(message, cardDeviceFragment);
                return;
            }
        }
        if (message.obj instanceof DeviceInfo) {
            DeviceInfo deviceInfo2 = (DeviceInfo) message.obj;
            cardDeviceFragment.a(deviceInfo2.getDeviceIdentify());
            cardDeviceFragment.j.a(true);
            cardDeviceFragment.h();
            cardDeviceFragment.ab = false;
            cardDeviceFragment.w.sendMessageDelayed(ogj.cZB_(0, deviceInfo2.getProductType()), 20000L);
        }
    }

    private void cXo_(Message message, CardDeviceFragment cardDeviceFragment) {
        int i = message.what;
        if (i == 14) {
            if (cardDeviceFragment.x != null) {
                cardDeviceFragment.x.cXK_(cardDeviceFragment, cardDeviceFragment.o);
            }
        } else if (i == 41) {
            cardDeviceFragment.cXf_(message);
        } else if (i == 42) {
            cardDeviceFragment.a();
        } else {
            LogUtil.h("CardDeviceFragment CardDeviceFragmentHandler", "message default");
        }
    }

    private void d(CardDeviceFragment cardDeviceFragment) {
        LogUtil.a("CardDeviceFragment CardDeviceFragmentHandler", "updateLayout isEnableShowUpdate ", Boolean.valueOf(ogj.m()), " isCountCancelTimes ", Boolean.valueOf(ogj.h()));
        if (cardDeviceFragment.h == null || !ogj.m() || !ogj.h()) {
            if (cardDeviceFragment.w != null) {
                cardDeviceFragment.w.removeMessages(14);
                cardDeviceFragment.w.sendEmptyMessage(14);
                return;
            }
            return;
        }
        if (cardDeviceFragment.f != null && cardDeviceFragment.f.getVisibility() == 0) {
            SharedPreferenceManager.e(String.valueOf(10024), "auto_ota_tip_status", true);
        } else {
            cardDeviceFragment.h.setVisibility(0);
        }
    }
}
