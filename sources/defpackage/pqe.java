package defpackage;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.health.R;
import com.huawei.health.facardagds.FaCardAgdsApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import health.compact.a.LogAnonymous;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes6.dex */
public class pqe {

    /* renamed from: a, reason: collision with root package name */
    private final PopupWindow f16226a;
    private final FaCardAgdsApi b = dlf.c();
    private final View c;
    private final int e;

    public boolean c() {
        int i = this.e;
        boolean z = false;
        if (i == 0) {
            z = SharedPreferenceManager.a("MMKV_SUGGEST_MODULE_TAG", "FA_BUBBLE_SHOWN", false);
        } else if (i == 1) {
            z = SharedPreferenceManager.a(String.valueOf(PrebakedEffectId.RT_COIN_DROP), "FA_BUBBLE_SHOWN", false);
        }
        LogUtil.a("FaCardHelper", "isShown: ", Boolean.valueOf(z), "type:", Integer.valueOf(this.e));
        return z;
    }

    public boolean d() {
        int i = this.e;
        boolean z = false;
        if (i == 0) {
            z = SharedPreferenceManager.a("MMKV_SUGGEST_MODULE_TAG", "SERVICE_EXPRESS_CARD_CLICKED", false);
        } else if (i == 1) {
            z = SharedPreferenceManager.a(String.valueOf(PrebakedEffectId.RT_COIN_DROP), "SERVICE_EXPRESS_CARD_CLICKED", false);
        } else {
            LogUtil.b("FaCardHelper", "error type: ", Integer.valueOf(i));
        }
        LogUtil.a("FaCardHelper", "isClicked: ", Boolean.valueOf(z), "type:", Integer.valueOf(this.e));
        return z;
    }

    public void c(int i) {
        LogUtil.a("FaCardHelper", "setBubbleShownSp type:", Integer.valueOf(i));
        if (i == 0) {
            SharedPreferenceManager.e("MMKV_SUGGEST_MODULE_TAG", "FA_BUBBLE_SHOWN", true);
        } else if (i == 1) {
            SharedPreferenceManager.e(String.valueOf(PrebakedEffectId.RT_COIN_DROP), "FA_BUBBLE_SHOWN", true);
        }
    }

    public void e() {
        LogUtil.a("FaCardHelper", "setCardClickedSp type:", Integer.valueOf(this.e));
        int i = this.e;
        if (i == 0) {
            SharedPreferenceManager.e("MMKV_SUGGEST_MODULE_TAG", "SERVICE_EXPRESS_CARD_CLICKED", true);
        } else if (i == 1) {
            SharedPreferenceManager.e(String.valueOf(PrebakedEffectId.RT_COIN_DROP), "SERVICE_EXPRESS_CARD_CLICKED", true);
        }
    }

    public void d(int i) {
        LogUtil.a("FaCardHelper", "jumpToServiceExpressCard, type:", Integer.valueOf(this.e), "requestCode:", Integer.valueOf(i));
        dlf.c().faQuickServicesBiEvent(1, (i == 300 || i == 200) ? 3 : 2, pqm.b(this.e));
        this.b.open(i, e(this.e), new FaCardAgdsApi.OpenAgdsResultCallback() { // from class: pqj
            @Override // com.huawei.health.facardagds.FaCardAgdsApi.OpenAgdsResultCallback
            public final void onResponse(int i2) {
                pqe.b(i2);
            }
        });
    }

    static /* synthetic */ void b(int i) {
        if (i != 0) {
            LogUtil.b("FaCardHelper", "jumpToServiceExpressCard error,", " errorCode: ", Integer.valueOf(i));
            ReleaseLogUtil.c("R_Sleep_FaCardHelper", "JTSE error");
        }
    }

    public PopupWindow dsc_(Activity activity, int i) {
        if (activity == null) {
            LogUtil.b("FaCardHelper", "activity == null");
            return null;
        }
        if (!pqm.c()) {
            LogUtil.b("FaCardHelper", "!isSupportFaCardShow");
            return null;
        }
        if (c()) {
            LogUtil.b("FaCardHelper", "BubbleHaveShown");
            return null;
        }
        LogUtil.a("FaCardHelper", "start initFaBubble");
        PopupWindow popupWindow = new PopupWindow(activity.getLayoutInflater().inflate(R.layout.fa_bubble, (ViewGroup) null), activity.getResources().getDisplayMetrics().widthPixels < 730 ? -2 : 730, -2);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(false);
        dsd_(popupWindow.getContentView(), i);
        return popupWindow;
    }

    public void dsd_(View view, final int i) {
        if (view == null) {
            LogUtil.b("FaCardHelper", "faBubbleLayout == null");
            return;
        }
        TextView textView = (TextView) view.findViewById(R.id.fa_cancel);
        if (textView == null) {
            LogUtil.b("FaCardHelper", "cancelTx == null");
            return;
        }
        textView.setOnClickListener(new View.OnClickListener() { // from class: pql
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                pqe.this.dse_(i, view2);
            }
        });
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: pqk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                pqe.this.dsf_(i, view2);
            }
        };
        TextView textView2 = (TextView) view.findViewById(R.id.fa_add_card);
        if (textView2 == null) {
            LogUtil.b("FaCardHelper", "addCardTx == null");
            return;
        }
        textView2.setOnClickListener(onClickListener);
        View findViewById = view.findViewById(R.id.fa_bubble_layout);
        if (findViewById == null) {
            LogUtil.b("FaCardHelper", "relativeLayout == null");
            return;
        }
        findViewById.setOnClickListener(onClickListener);
        TextView textView3 = (TextView) view.findViewById(R.id.fa_bubble_content);
        if (textView3 == null) {
            LogUtil.b("FaCardHelper", "fa_content == null");
        } else if (i == 0) {
            textView3.setText(nsf.h(R$string.IDS_fa_bubble_text));
        } else if (i == 1) {
            textView3.setText(nsf.h(R$string.IDS_fa_bubble_text_sleep));
        }
    }

    /* synthetic */ void dse_(int i, View view) {
        c(i);
        this.f16226a.dismiss();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void dsf_(int i, View view) {
        d(i == 0 ? 300 : 200);
        c(i);
        this.f16226a.dismiss();
        ViewClickInstrumentation.clickOnView(view);
    }

    public pqe(Activity activity, View view, int i) {
        this.e = i;
        this.c = view;
        this.f16226a = dsc_(activity, i);
    }

    public void b() {
        PopupWindow popupWindow = this.f16226a;
        if (popupWindow == null) {
            return;
        }
        popupWindow.dismiss();
        this.b.close();
    }

    public void a(int i) {
        View view;
        PopupWindow popupWindow = this.f16226a;
        if (popupWindow == null) {
            return;
        }
        if (i != 0) {
            if (i == 8) {
                popupWindow.dismiss();
                return;
            }
            return;
        }
        if (c() || (view = this.c) == null) {
            return;
        }
        Context context = view.getContext();
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (activity.isFinishing()) {
                LogUtil.a("FaCardHelper", "activity isFinishing");
                return;
            }
            if (activity.isDestroyed()) {
                LogUtil.a("FaCardHelper", "activity isDestroyed");
            } else if (this.c.getWindowToken() != null) {
                try {
                    this.f16226a.showAsDropDown(this.c, 0, 0);
                } catch (WindowManager.BadTokenException e) {
                    LogUtil.b("FaCardHelper", "setDismissOrShow exception", LogAnonymous.b((Throwable) e));
                }
            }
        }
    }

    private String e(int i) {
        if (i == 0) {
            return FaCardAgdsApi.SPORT_FA_CARD;
        }
        if (i == 1) {
            return FaCardAgdsApi.SLEEP_FA_CARD;
        }
        LogUtil.b("FaCardHelper", "type error: ", Integer.valueOf(i));
        return "ErrorConstants.ERROR";
    }
}
