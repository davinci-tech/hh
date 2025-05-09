package com.huawei.ui.homewear21.home.card;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.thirdpartservice.wechatdevice.WechatDeviceProviderApi;
import defpackage.cvz;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public final class WechatDeviceProviderHelper {

    /* renamed from: a, reason: collision with root package name */
    private boolean f9669a;
    private final Map<WechatDeviceProviderApi.CheckResultListener<Boolean>, CheckResultListener> b = Collections.synchronizedMap(new HashMap());
    private final List<CheckResultListener> c = new ArrayList();
    private boolean d;
    private final DeviceInfo e;
    private final WeakReference<Handler> g;
    private final WechatDeviceProviderApi j;

    public interface CheckResultListener {
        void onResult(boolean z);
    }

    public WechatDeviceProviderHelper(DeviceInfo deviceInfo, Handler handler) {
        this.e = deviceInfo;
        if (TextUtils.isEmpty(deviceInfo.getHiLinkDeviceId())) {
            cvz.a(deviceInfo);
        }
        this.g = new WeakReference<>(handler);
        this.j = (WechatDeviceProviderApi) Services.c("FeatureDataOpen", WechatDeviceProviderApi.class);
        b(new CheckResultListener() { // from class: com.huawei.ui.homewear21.home.card.WechatDeviceProviderHelper.3
            @Override // com.huawei.ui.homewear21.home.card.WechatDeviceProviderHelper.CheckResultListener
            public void onResult(boolean z) {
                WechatDeviceProviderHelper.this.f9669a = z;
                WechatDeviceProviderHelper.this.d = true;
                Iterator it = WechatDeviceProviderHelper.this.c.iterator();
                while (it.hasNext()) {
                    ((CheckResultListener) it.next()).onResult(z);
                }
                WechatDeviceProviderHelper.this.c.clear();
                if (z) {
                    WechatDeviceProviderHelper.this.c((CheckResultListener) null);
                }
            }
        });
    }

    private void b(CheckResultListener checkResultListener) {
        WechatDeviceProviderApi.CheckResultListener<Boolean> checkResultListener2 = new WechatDeviceProviderApi.CheckResultListener<Boolean>() { // from class: com.huawei.ui.homewear21.home.card.WechatDeviceProviderHelper.1
            @Override // com.huawei.thirdpartservice.wechatdevice.WechatDeviceProviderApi.CheckResultListener
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onResult(Boolean bool) {
                LogUtil.a("WechatDeviceProviderHelper", "checkSupportWechatDevice onResult:", bool);
                WechatDeviceProviderHelper wechatDeviceProviderHelper = WechatDeviceProviderHelper.this;
                wechatDeviceProviderHelper.e((CheckResultListener) wechatDeviceProviderHelper.b.remove(this), bool.booleanValue());
            }

            @Override // com.huawei.thirdpartservice.wechatdevice.WechatDeviceProviderApi.CheckResultListener
            public void onError(int i, String str) {
                LogUtil.a("WechatDeviceProviderHelper", "checkIsSupportWechatDevice onError: code:", Integer.valueOf(i), "msg: ", str);
                WechatDeviceProviderHelper wechatDeviceProviderHelper = WechatDeviceProviderHelper.this;
                wechatDeviceProviderHelper.e((CheckResultListener) wechatDeviceProviderHelper.b.remove(this), false);
            }
        };
        this.b.put(checkResultListener2, checkResultListener);
        this.j.checkSupportWechatDevice(this.e, checkResultListener2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(CheckResultListener checkResultListener) {
        WechatDeviceProviderApi.CheckResultListener<Boolean> checkResultListener2 = new WechatDeviceProviderApi.CheckResultListener<Boolean>() { // from class: com.huawei.ui.homewear21.home.card.WechatDeviceProviderHelper.5
            @Override // com.huawei.thirdpartservice.wechatdevice.WechatDeviceProviderApi.CheckResultListener
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onResult(Boolean bool) {
                LogUtil.a("WechatDeviceProviderHelper", "checkIsBindWechat onResult:", bool);
                WechatDeviceProviderHelper wechatDeviceProviderHelper = WechatDeviceProviderHelper.this;
                wechatDeviceProviderHelper.e((CheckResultListener) wechatDeviceProviderHelper.b.remove(this), bool.booleanValue());
            }

            @Override // com.huawei.thirdpartservice.wechatdevice.WechatDeviceProviderApi.CheckResultListener
            public void onError(int i, String str) {
                LogUtil.a("WechatDeviceProviderHelper", "checkIsBindWechat onError: code:", Integer.valueOf(i), "msg: ", str);
                WechatDeviceProviderHelper wechatDeviceProviderHelper = WechatDeviceProviderHelper.this;
                wechatDeviceProviderHelper.e((CheckResultListener) wechatDeviceProviderHelper.b.remove(this), true);
            }
        };
        this.b.put(checkResultListener2, checkResultListener);
        this.j.isBindWechat(this.e, checkResultListener2);
    }

    private void a(CheckResultListener checkResultListener) {
        WechatDeviceProviderApi.CheckResultListener<Boolean> checkResultListener2 = new WechatDeviceProviderApi.CheckResultListener<Boolean>() { // from class: com.huawei.ui.homewear21.home.card.WechatDeviceProviderHelper.2
            @Override // com.huawei.thirdpartservice.wechatdevice.WechatDeviceProviderApi.CheckResultListener
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onResult(Boolean bool) {
                LogUtil.a("WechatDeviceProviderHelper", "checkIsBindMaxCount onResult:", bool);
                WechatDeviceProviderHelper wechatDeviceProviderHelper = WechatDeviceProviderHelper.this;
                wechatDeviceProviderHelper.e((CheckResultListener) wechatDeviceProviderHelper.b.remove(this), bool.booleanValue());
            }

            @Override // com.huawei.thirdpartservice.wechatdevice.WechatDeviceProviderApi.CheckResultListener
            public void onError(int i, String str) {
                LogUtil.a("WechatDeviceProviderHelper", "checkIsBindMaxCount onError: code:", Integer.valueOf(i), "msg: ", str);
                WechatDeviceProviderHelper wechatDeviceProviderHelper = WechatDeviceProviderHelper.this;
                wechatDeviceProviderHelper.e((CheckResultListener) wechatDeviceProviderHelper.b.remove(this), true);
            }
        };
        this.b.put(checkResultListener2, checkResultListener);
        this.j.isBindMaxCount(checkResultListener2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final CheckResultListener checkResultListener, final boolean z) {
        if (checkResultListener == null) {
            return;
        }
        if (Looper.getMainLooper().isCurrentThread()) {
            checkResultListener.onResult(z);
            return;
        }
        Handler handler = this.g.get();
        if (handler == null) {
            e();
        } else {
            handler.post(new Runnable() { // from class: com.huawei.ui.homewear21.home.card.WechatDeviceProviderHelper.4
                @Override // java.lang.Runnable
                public void run() {
                    checkResultListener.onResult(z);
                }
            });
        }
    }

    public void d(CheckResultListener checkResultListener) {
        if (this.d) {
            checkResultListener.onResult(this.f9669a);
        } else {
            this.c.add(checkResultListener);
        }
    }

    public void e(final CheckResultListener checkResultListener) {
        d(new CheckResultListener() { // from class: com.huawei.ui.homewear21.home.card.WechatDeviceProviderHelper.7
            @Override // com.huawei.ui.homewear21.home.card.WechatDeviceProviderHelper.CheckResultListener
            public void onResult(boolean z) {
                if (z) {
                    WechatDeviceProviderHelper.this.c(new CheckResultListener() { // from class: com.huawei.ui.homewear21.home.card.WechatDeviceProviderHelper.7.5
                        @Override // com.huawei.ui.homewear21.home.card.WechatDeviceProviderHelper.CheckResultListener
                        public void onResult(boolean z2) {
                            LogUtil.a("WechatDeviceProviderHelper", "isNeedShowWechatTip checkIsBindWechat onResult:", Boolean.valueOf(z2));
                            checkResultListener.onResult(!z2);
                        }
                    });
                } else {
                    LogUtil.a("WechatDeviceProviderHelper", "isNeedShowWechatTip checkIsBindWechat isSupportWechatDevice: false");
                    checkResultListener.onResult(false);
                }
            }
        });
    }

    public void d(final Context context) {
        if (context == null) {
            ReleaseLogUtil.d("R_WechatDeviceProviderHelper", "gotoWechat context is null");
        } else {
            c(new CheckResultListener() { // from class: com.huawei.ui.homewear21.home.card.WechatDeviceProviderHelper.10
                @Override // com.huawei.ui.homewear21.home.card.WechatDeviceProviderHelper.CheckResultListener
                public void onResult(boolean z) {
                    if (z) {
                        LogUtil.a("WechatDeviceProviderHelper", "gotoWechat jumpToWechatDeviceActivity");
                        WechatDeviceProviderHelper.this.j.jumpToWechatDeviceActivity(context);
                    } else {
                        WechatDeviceProviderHelper.this.c(context);
                    }
                }
            });
        }
    }

    public void c(final Context context) {
        if (context == null) {
            ReleaseLogUtil.d("R_WechatDeviceProviderHelper", "bindWechat context is null");
        } else {
            a(new CheckResultListener() { // from class: com.huawei.ui.homewear21.home.card.WechatDeviceProviderHelper.9
                @Override // com.huawei.ui.homewear21.home.card.WechatDeviceProviderHelper.CheckResultListener
                public void onResult(boolean z) {
                    if (z) {
                        LogUtil.a("WechatDeviceProviderHelper", "bindWechat jumpToWechatDeviceActivity");
                        WechatDeviceProviderHelper.this.j.jumpToWechatDeviceActivity(context);
                    } else {
                        LogUtil.a("WechatDeviceProviderHelper", "bindWechat bindWechat");
                        WechatDeviceProviderHelper.this.j.bindWechat(context, WechatDeviceProviderHelper.this.e);
                    }
                }
            });
        }
    }

    public void e() {
        this.b.clear();
        this.c.clear();
        this.j.destroy();
    }
}
