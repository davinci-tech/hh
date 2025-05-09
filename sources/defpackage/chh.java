package defpackage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import com.huawei.framework.servicemgr.Consumer;
import com.huawei.health.device.kit.wsp.task.BleTaskQueueUtil;
import com.huawei.health.device.util.EventBus;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import health.compact.a.GRSManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes3.dex */
public class chh {
    private static final Map<String, Consumer<cje>> e = new HashMap(16);

    /* renamed from: a, reason: collision with root package name */
    private int f721a;
    private final cha c;
    private final cgz d;

    public chh(cgz cgzVar, cha chaVar) {
        this.d = cgzVar;
        this.c = chaVar;
        e();
    }

    private void e() {
        LogUtil.a("WspEventBusUtil", "initEventBusHandlerMap start");
        h();
        a();
        j();
        b();
        o();
        f();
        g();
        d();
        i();
        LogUtil.a("WspEventBusUtil", "initEventBusHandlerMap end");
    }

    private void h() {
        Map<String, Consumer<cje>> map = e;
        map.put("real_time_weight_info", new Consumer() { // from class: cil
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.ao((cje) obj);
            }
        });
        map.put("real_time_weight_info_failed", new Consumer() { // from class: cin
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                LogUtil.h("WspEventBusUtil", "getRealtimeWightData is fail!");
            }
        });
        map.put("history_weight_info", new Consumer() { // from class: cik
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.as((cje) obj);
            }
        });
        map.put("send_real_time_weight_info", new Consumer() { // from class: cio
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.aq((cje) obj);
            }
        });
        map.put("send_history_weight_info", new Consumer() { // from class: cir
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.ar((cje) obj);
            }
        });
    }

    /* synthetic */ void ao(cje cjeVar) {
        e(cjeVar.e());
    }

    /* synthetic */ void as(cje cjeVar) {
        c(cjeVar.e());
    }

    /* synthetic */ void aq(cje cjeVar) {
        c(cjeVar.e(), cjeVar.b());
    }

    /* synthetic */ void ar(cje cjeVar) {
        k();
    }

    private void a() {
        Map<String, Consumer<cje>> map = e;
        map.put("config_info_result", new Consumer() { // from class: cho
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.f((cje) obj);
            }
        });
        map.put("send_config_info", new Consumer() { // from class: chk
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.k((cje) obj);
            }
        });
        map.put("send_config_info_hag_2021", new Consumer() { // from class: chl
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.l((cje) obj);
            }
        });
    }

    /* synthetic */ void f(cje cjeVar) {
        this.c.Fi_(cjeVar.b(), -12, cjeVar.e().Kl_());
    }

    /* synthetic */ void k(cje cjeVar) {
        Fw_(cjeVar.e().Kl_(), cjeVar.b());
    }

    /* synthetic */ void l(cje cjeVar) {
        Fv_(cjeVar.e().Kl_());
    }

    private void j() {
        Map<String, Consumer<cje>> map = e;
        map.put("weight_device_ota_update", new Consumer() { // from class: ciq
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.u((cje) obj);
            }
        });
        map.put("weight_device_tlv_ota_update", new Consumer() { // from class: cit
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.y((cje) obj);
            }
        });
        map.put("event_bus_send_ota_url", new Consumer() { // from class: ciw
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.x((cje) obj);
            }
        });
    }

    /* synthetic */ void u(cje cjeVar) {
        j(cjeVar.e());
    }

    /* synthetic */ void y(cje cjeVar) {
        k(cjeVar.e());
    }

    /* synthetic */ void x(cje cjeVar) {
        m();
    }

    private void b() {
        Map<String, Consumer<cje>> map = e;
        map.put("request_auth", new Consumer() { // from class: cja
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.g((cje) obj);
            }
        });
        map.put("request_auth_token", new Consumer() { // from class: chf
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.h((cje) obj);
            }
        });
        map.put("request_auth_failed", new Consumer() { // from class: chn
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.i((cje) obj);
            }
        });
        map.put("request_auth_pass", new Consumer() { // from class: chm
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.j((cje) obj);
            }
        });
    }

    /* synthetic */ void g(cje cjeVar) {
        h(cjeVar.e());
    }

    /* synthetic */ void h(cje cjeVar) {
        f(cjeVar.e());
    }

    /* synthetic */ void i(cje cjeVar) {
        b(cjeVar.b());
    }

    /* synthetic */ void j(cje cjeVar) {
        d(cjeVar.g(), cjeVar.b());
    }

    private void o() {
        Map<String, Consumer<cje>> map = e;
        map.put("get_device_ssid", new Consumer() { // from class: chz
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.ap((cje) obj);
            }
        });
        map.put("get_device_ssid_result", new Consumer() { // from class: cic
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.ax((cje) obj);
            }
        });
        map.put("send_ssid", new Consumer() { // from class: cib
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.aw((cje) obj);
            }
        });
        map.put("send_wifi_password", new Consumer() { // from class: cia
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.av((cje) obj);
            }
        });
        map.put("reset_wifi", new Consumer() { // from class: cie
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.at((cje) obj);
            }
        });
    }

    /* synthetic */ void ap(cje cjeVar) {
        a(cjeVar.d());
    }

    /* synthetic */ void ax(cje cjeVar) {
        e(cjeVar.e(), cjeVar.g(), cjeVar.b());
    }

    /* synthetic */ void aw(cje cjeVar) {
        i(cjeVar.e());
    }

    /* synthetic */ void av(cje cjeVar) {
        this.c.d(cgt.e().n());
    }

    /* synthetic */ void at(cje cjeVar) {
        this.c.i();
    }

    private void f() {
        Map<String, Consumer<cje>> map = e;
        map.put("manager_info_success", new Consumer() { // from class: chg
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.t((cje) obj);
            }
        });
        map.put("manager_info_failed", new Consumer() { // from class: chj
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.s((cje) obj);
            }
        });
        map.put("set_manager_info", new Consumer() { // from class: chu
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.r((cje) obj);
            }
        });
        map.put("set_manager_info_result", new Consumer() { // from class: cig
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.v((cje) obj);
            }
        });
        map.put("get_manager_info", new Consumer() { // from class: cis
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.w((cje) obj);
            }
        });
    }

    /* synthetic */ void t(cje cjeVar) {
        Fu_(cjeVar.e(), cjeVar.c(), cjeVar.b(), cjeVar.g());
    }

    /* synthetic */ void s(cje cjeVar) {
        b(cjeVar.e(), cjeVar.b(), cjeVar.g());
    }

    /* synthetic */ void r(cje cjeVar) {
        Ft_(cjeVar.e().Kl_());
    }

    /* synthetic */ void v(cje cjeVar) {
        this.c.Fi_(cjeVar.b(), -13, cjeVar.e().Kl_());
    }

    /* synthetic */ void w(cje cjeVar) {
        Fr_(cjeVar.e().Kl_());
    }

    private void g() {
        Map<String, Consumer<cje>> map = e;
        map.put("weight_measure_set_user", new Consumer() { // from class: chp
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                cgt.e().Fd_(((cje) obj).e().Kl_());
            }
        });
        map.put("set_user_info_result", new Consumer() { // from class: chs
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.ae((cje) obj);
            }
        });
        map.put("delete_user_data", new Consumer() { // from class: chq
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.am((cje) obj);
            }
        });
        map.put("get_user_data", new Consumer() { // from class: chr
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.ak((cje) obj);
            }
        });
        map.put("get_user_data_next", new Consumer() { // from class: chw
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.aj((cje) obj);
            }
        });
        map.put("get_user_data_again", new Consumer() { // from class: chx
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.an((cje) obj);
            }
        });
        map.put("get_user_data_send_again", new Consumer() { // from class: chv
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.al((cje) obj);
            }
        });
        map.put("event_bus_current_user_changed", new Consumer() { // from class: chy
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                cgt.e().Fd_(null);
            }
        });
    }

    /* synthetic */ void ae(cje cjeVar) {
        b(cjeVar.e(), cjeVar.b());
    }

    /* synthetic */ void am(cje cjeVar) {
        d(cjeVar.e());
    }

    /* synthetic */ void ak(cje cjeVar) {
        b(cjeVar.e());
    }

    /* synthetic */ void aj(cje cjeVar) {
        this.c.f();
    }

    /* synthetic */ void an(cje cjeVar) {
        this.c.l();
    }

    /* synthetic */ void al(cje cjeVar) {
        c();
    }

    private void d() {
        Map<String, Consumer<cje>> map = e;
        map.put("event_bus_upload_ble_log", new Consumer() { // from class: cif
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.n((cje) obj);
            }
        });
        map.put("event_bus_file_check", new Consumer() { // from class: cii
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.o((cje) obj);
            }
        });
        map.put("event_bus_file_parameter", new Consumer() { // from class: cih
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.m((cje) obj);
            }
        });
        map.put("event_bus_file_data_request", new Consumer() { // from class: cij
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.p((cje) obj);
            }
        });
        map.put("event_bus_file_result_notify", new Consumer() { // from class: cim
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.q((cje) obj);
            }
        });
    }

    /* synthetic */ void n(cje cjeVar) {
        Fo_(cjeVar.e().Kl_());
    }

    /* synthetic */ void o(cje cjeVar) {
        Fm_(cjeVar.e().Kl_());
    }

    /* synthetic */ void m(cje cjeVar) {
        Fp_(cjeVar.e().Kl_());
    }

    /* synthetic */ void p(cje cjeVar) {
        Fn_(cjeVar.e().Kl_());
    }

    /* synthetic */ void q(cje cjeVar) {
        Fq_(cjeVar.e().Kl_());
    }

    private void i() {
        Map<String, Consumer<cje>> map = e;
        map.put("event_bus_disable_notification", new Consumer() { // from class: cix
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.ad((cje) obj);
            }
        });
        map.put("bind_result", new Consumer() { // from class: ciu
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.z((cje) obj);
            }
        });
        map.put("workkey_info", new Consumer() { // from class: chi
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.ab((cje) obj);
            }
        });
        map.put("get_scale_version_code", new Consumer() { // from class: cht
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                cgt.e().a(((cje) obj).e());
            }
        });
        map.put("set_weight_unit", new Consumer() { // from class: cid
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.aa((cje) obj);
            }
        });
        map.put("device_reset", new Consumer() { // from class: cip
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.ac((cje) obj);
            }
        });
        map.put("get_weight_unit", new Consumer() { // from class: civ
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.ah((cje) obj);
            }
        });
        map.put("get_scale_version", new Consumer() { // from class: cjb
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.af((cje) obj);
            }
        });
        map.put("send_wake_up", new Consumer() { // from class: cjc
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.ag((cje) obj);
            }
        });
        map.put("bind_process_completed", new Consumer() { // from class: ciz
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                chh.this.ai((cje) obj);
            }
        });
    }

    /* synthetic */ void ad(cje cjeVar) {
        this.c.Fg_(cjeVar.e().Kl_());
    }

    /* synthetic */ void z(cje cjeVar) {
        Fs_(cjeVar.e().Kl_(), cjeVar.b());
    }

    /* synthetic */ void ab(cje cjeVar) {
        c(cjeVar.e(), cjeVar.b(), cjeVar.g(), cjeVar.j());
    }

    /* synthetic */ void aa(cje cjeVar) {
        g(cjeVar.e());
    }

    /* synthetic */ void ac(cje cjeVar) {
        this.c.o();
    }

    /* synthetic */ void ah(cje cjeVar) {
        a(cjeVar.f());
    }

    /* synthetic */ void af(cje cjeVar) {
        a(cjeVar.e());
    }

    /* synthetic */ void ag(cje cjeVar) {
        e(cjeVar.a());
    }

    /* synthetic */ void ai(cje cjeVar) {
        this.c.Fi_(cjeVar.b(), -14, cjeVar.e().Kl_());
    }

    public void e(cje cjeVar) {
        if (cjeVar == null) {
            LogUtil.h("WspEventBusUtil", "bean is null");
            return;
        }
        if (cjeVar.e() == null) {
            LogUtil.h("WspEventBusUtil", "handleEventBusMsg event is null");
            return;
        }
        if (cjeVar.e().Kl_() == null && cjeVar.e().Km_() == null) {
            LogUtil.h("WspEventBusUtil", "handleEventBusMsg bundle and intent are null");
            return;
        }
        Consumer<cje> consumer = e.get(cjeVar.e().e());
        if (consumer != null) {
            ReleaseLogUtil.e("R_Weight_WspEventBusUtil", "event action is ", cjeVar.e().e());
            consumer.accept(cjeVar);
        }
    }

    private void Fq_(Bundle bundle) {
        this.c.m().e(bundle.getInt("key_file_id"), bundle.getInt("key_file_result_type"));
        LogUtil.a("WspEventBusUtil", "5.44.6 ble send command ", "event_bus_file_result_notify");
    }

    private void Fn_(Bundle bundle) {
        this.c.m().e(bundle.getInt("key_file_id"), bundle.getInt("key_file_offset"), bundle.getInt("key_file_length"));
        LogUtil.a("WspEventBusUtil", "5.44.4 ble send command ", "event_bus_file_data_request");
    }

    private void Fp_(Bundle bundle) {
        this.c.m().d(bundle.getInt("key_file_id"));
        LogUtil.a("WspEventBusUtil", "5.44.3 ble send command ", "event_bus_file_parameter");
    }

    private void Fm_(Bundle bundle) {
        this.c.m().a(bundle.getInt("key_file_id"), bundle.getInt("key_check_mode"));
        LogUtil.a("WspEventBusUtil", "5.44.2 ble send command ", "event_bus_upload_ble_log");
    }

    private void Fo_(Bundle bundle) {
        this.c.m().c(bundle.getString("com.huawei.health.scale.log"), bundle.getString("com.huawei.health.scale.log"));
        LogUtil.a("WspEventBusUtil", "5.44.8 ble send command ", "event_bus_upload_ble_log");
    }

    private void m() {
        String url = GRSManager.a(BaseApplication.getContext()).getUrl("ROOT");
        LogUtil.a("WspEventBusUtil", "[grs] begin handle sending grsUrl: ", url);
        this.c.b(url);
    }

    private void i(EventBus.b bVar) {
        Bundle Kl_ = bVar.Kl_();
        if (Kl_ == null) {
            return;
        }
        String string = Kl_.getString("wifiSsid");
        cgt.e().d(Kl_.getString("wifiPwd"));
        this.c.a(string);
    }

    private void e(cgp cgpVar) {
        if (cgpVar != null) {
            this.c.q();
        }
    }

    private void b(EventBus.b bVar) {
        this.c.Fh_(bVar.Kl_());
        cgt.e().a(-1);
    }

    private void b(EventBus.b bVar, cxh cxhVar) {
        Bundle Kl_ = bVar.Kl_();
        if (Kl_ == null) {
            LogUtil.h("WspEventBusUtil", "handlerEventBusActionFive bundle is null");
            return;
        }
        int i = Kl_.getInt("ret");
        if (i != 0) {
            LogUtil.h("WspEventBusUtil", "set user failed, maybe user exists");
            this.c.e(cxhVar, i);
        }
    }

    private void k() {
        if (!cgt.e().l()) {
            this.c.e(new byte[0]);
        } else {
            LogUtil.h("WspEventBusUtil", "HwWspMeasureController is getting history data");
        }
    }

    private void c(EventBus.b bVar, cxh cxhVar) {
        LogUtil.a("WspEventBusUtil", "sendRealtimeWightData is start!");
        Bundle Kl_ = bVar.Kl_();
        if (Kl_ == null) {
            LogUtil.h("WspEventBusUtil", "handlerEventBusActionFive bundle is null");
            return;
        }
        int i = Kl_.getInt("ret");
        if (i != 0) {
            LogUtil.h("WspEventBusUtil", "set user failed.");
            cgt.e().p();
            this.c.e(cxhVar, i);
            this.c.a(cxhVar, 3);
            return;
        }
        cgt.e().q();
    }

    private void g(EventBus.b bVar) {
        Bundle Kl_ = bVar.Kl_();
        if (Kl_ != null) {
            int i = Kl_.getInt("weightUnit");
            cgt.e().b(i);
            this.c.d(BleTaskQueueUtil.TaskType.SET_WEIGHT_UNIT, cgz.c(i));
        }
    }

    private void k(EventBus.b bVar) {
        Intent Km_ = bVar.Km_();
        if (Km_ != null) {
            chd.b().d(Km_.getStringExtra("scaleUniqueId"), Km_.getStringExtra("scaleNewVersion"), Km_.getStringExtra("scaleFilePath"));
            return;
        }
        LogUtil.h("WspEventBusUtil", "case EVEBUS_SINLGE_DEVICE_TLV_OTA_UPDATE intent is null");
    }

    private void j(EventBus.b bVar) {
        Intent Km_ = bVar.Km_();
        if (Km_ != null) {
            ciy.c().c(Km_.getStringExtra("scalePath"));
        } else {
            LogUtil.a("WspEventBusUtil", "case EVEBUS_SINLGE_DEVICE_OTA_UPDATE intent is null");
        }
    }

    private void Fs_(Bundle bundle, cxh cxhVar) {
        byte b = bundle.getByte("ret");
        this.c.Fi_(cxhVar, -2, bundle);
        if (b != 2) {
            this.c.y();
        }
    }

    private void Fu_(EventBus.b bVar, Handler handler, cxh cxhVar, int i) {
        if (handler != null) {
            handler.sendEmptyMessageDelayed(7, 200L);
        }
        this.c.Fi_(cxhVar, -7, bVar.Kl_());
        if (i != -2 || cgt.e().h()) {
            return;
        }
        this.c.d(this.d);
    }

    private void b(EventBus.b bVar, cxh cxhVar, int i) {
        this.c.Fi_(cxhVar, -8, bVar.Kl_());
        if (i != -2 || cgt.e().h()) {
            return;
        }
        this.c.d(this.d);
    }

    private void Ft_(Bundle bundle) {
        String string = bundle.getString("huid");
        String string2 = bundle.getString("deviceId");
        String string3 = bundle.getString("accountInfo");
        if (string3 == null || TextUtils.isEmpty(string3)) {
            this.c.e(string, string2, this.d);
        } else {
            this.c.b(string, string2, string3, this.d);
        }
    }

    private void Fr_(Bundle bundle) {
        this.c.c(bundle.getBoolean("isGetAccountInfo"));
    }

    private void a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("WspEventBusUtil", "handlerEventActionTwo deviceSsid isEmpty");
            this.c.g();
        } else {
            LogUtil.a("WspEventBusUtil", "handlerEventActionTwo deviceSsid is not Empty");
            Bundle bundle = new Bundle();
            bundle.putString("deviceSsid", str);
            EventBus.d(new EventBus.b("get_device_ssid_result", bundle));
        }
    }

    private void e(EventBus.b bVar, int i, cxh cxhVar) {
        if (i == -2) {
            LogUtil.h("WspEventBusUtil", "HwWspMeasureController mBaseResponseCallback is not BindIHealthDeviceCallback.");
            cgt.e().e(true);
            this.c.Fi_(cxhVar, -9, bVar.Kl_());
        }
    }

    private void h(EventBus.b bVar) {
        byte[] bArr;
        ReleaseLogUtil.e("R_Weight_WspEventBusUtil", "request auth");
        Bundle Kl_ = bVar.Kl_();
        if (Kl_ == null) {
            LogUtil.h("WspEventBusUtil", "getRequestAuth, bundle is null");
            return;
        }
        if ("auth".equals(Kl_.getString("auth"))) {
            this.c.p();
            return;
        }
        try {
            bArr = Kl_.getByteArray("randA");
        } catch (ArrayIndexOutOfBoundsException unused) {
            LogUtil.b("WspEventBusUtil", "randomBytes ArrayIndexOutOfBoundsException!");
            bArr = null;
        }
        byte[] e2 = cgd.e(bArr);
        if (e2.length == 0) {
            EventBus.d(new EventBus.b("request_auth_failed", new Bundle()));
        } else {
            this.c.c(e2);
        }
    }

    private void f(EventBus.b bVar) {
        byte[] bArr;
        ReleaseLogUtil.e("R_Weight_WspEventBusUtil", "request auth token");
        Bundle Kl_ = bVar.Kl_();
        if (Kl_ == null) {
            LogUtil.h("WspEventBusUtil", "getRequestAuthToken, bundle is null");
            return;
        }
        try {
            bArr = Kl_.getByteArray("token");
        } catch (ArrayIndexOutOfBoundsException unused) {
            LogUtil.b("WspEventBusUtil", "tokens ArrayIndexOutOfBoundsException!");
            bArr = null;
        }
        Bundle bundle = new Bundle();
        if (bArr != null && bArr.length == 0) {
            EventBus.d(new EventBus.b("request_auth_failed", bundle));
        } else if (cgd.b(cvx.d(bArr))) {
            EventBus.d(new EventBus.b("request_auth_pass", bundle));
        } else {
            EventBus.d(new EventBus.b("request_auth_failed", bundle));
        }
    }

    private void b(cxh cxhVar) {
        LogUtil.h("WspEventBusUtil", "request auth failed.");
        cgt.e().p();
        this.c.a(cxhVar, -10);
        this.c.a(cxhVar, 3);
    }

    private void d(int i, cxh cxhVar) {
        ReleaseLogUtil.e("R_Weight_WspEventBusUtil", "requset auth pass, type:", Integer.valueOf(i));
        cgt.e().a(true);
        byte[] d = cgh.d(cxhVar.getAddress());
        if (d.length != 0) {
            LogUtil.a("WspEventBusUtil", "handlerEventBusActionThree workerKeys:", cvx.d(d));
            this.c.i(d);
        } else {
            LogUtil.h("WspEventBusUtil", "workerKey is isEmpty");
        }
    }

    private void c(EventBus.b bVar, cxh cxhVar, int i, String str) {
        cgt.e().a(false);
        Bundle Kl_ = bVar.Kl_();
        if (Kl_ == null) {
            LogUtil.h("WspEventBusUtil", "handlerEventBusActionThree event bundle is null");
            return;
        }
        if (Kl_.getInt("ret") != 0) {
            LogUtil.h("WspEventBusUtil", "get workerKey failed.");
            cgt.e().p();
            this.c.a(cxhVar, -11);
            this.c.a(cxhVar, 3);
            return;
        }
        ReleaseLogUtil.e("R_Weight_WspEventBusUtil", "get workerKey pass, type:", Integer.valueOf(i));
        if (i == -2) {
            if (cpa.ah(str)) {
                this.c.d(this.d);
                return;
            } else {
                Fr_(Kl_);
                return;
            }
        }
        if (i == -4 || i == -1) {
            this.c.y();
        } else {
            LogUtil.h("WspEventBusUtil", "handlerEventActionThree not found type:", Integer.valueOf(i));
        }
    }

    private void e(EventBus.b bVar) {
        String str;
        byte[] byteArray;
        ReleaseLogUtil.e("R_Weight_WspEventBusUtil", "getRealtimeWightData is success!");
        Bundle Kl_ = bVar.Kl_();
        byte[] bArr = null;
        bArr = null;
        String str2 = null;
        if (Kl_ != null) {
            try {
                byteArray = Kl_.getByteArray("realTimeData");
            } catch (ArrayIndexOutOfBoundsException unused) {
                str = null;
            }
            try {
                str2 = Kl_.getString("unique_id");
                this.f721a = Kl_.getInt("age", 0);
                str = str2;
                bArr = byteArray;
            } catch (ArrayIndexOutOfBoundsException unused2) {
                str = str2;
                bArr = byteArray;
                LogUtil.b("WspEventBusUtil", "realtimeWeightDatas ArrayIndexOutOfBoundsException!");
                if (bArr != null) {
                }
                ReleaseLogUtil.d("R_Weight_WspEventBusUtil", "realtimeWeightDatas is empty!");
                return;
            }
        } else {
            str = null;
        }
        if (bArr != null || bArr.length <= 0) {
            ReleaseLogUtil.d("R_Weight_WspEventBusUtil", "realtimeWeightDatas is empty!");
            return;
        }
        LogUtil.c("WspEventBusUtil", "getRealtimeWightData realtimeWeightDatas is:", cvx.d(bArr));
        e(bArr, false, str);
        EventBus.d(new EventBus.b("send_history_weight_info", new Bundle()));
    }

    private void c(EventBus.b bVar) {
        ReleaseLogUtil.e("R_Weight_WspEventBusUtil", "getHistoryWightData is success!");
        Bundle Kl_ = bVar.Kl_();
        if (Kl_ == null) {
            LogUtil.h("WspEventBusUtil", "handlerEventBusActionFour bundle is null");
            return;
        }
        byte[] bArr = new byte[10];
        String str = null;
        try {
            bArr = Kl_.getByteArray("historyData");
            str = Kl_.getString("unique_id");
            this.f721a = Kl_.getInt("age", 0);
        } catch (ArrayIndexOutOfBoundsException unused) {
            LogUtil.b("WspEventBusUtil", "historyWeightDatas ArrayIndexOutOfBoundsException!");
        }
        if (bArr.length > 0) {
            cgt.e().d(true);
            LogUtil.a("WspEventBusUtil", "getHistoryWightData historyWeightDatas is:", cvx.d(bArr));
            e(bArr, true, str);
            this.c.e(new byte[]{0});
            return;
        }
        cgt.e().d(false);
        ReleaseLogUtil.d("R_Weight_WspEventBusUtil", "history weight data is empty!");
        cgt.e().ending();
    }

    private void d(EventBus.b bVar) {
        byte[] bArr;
        Bundle Kl_ = bVar.Kl_();
        if (Kl_ == null) {
            LogUtil.b("WspEventBusUtil", "handlerDeleteUserData, bundle is null");
            return;
        }
        try {
            bArr = Kl_.getByteArray(JsbMapKeyNames.H5_USER_ID);
        } catch (Exception unused) {
            LogUtil.b("WspEventBusUtil", "getByteArray exception");
            bArr = null;
        }
        this.c.b(bArr);
    }

    private void a(int i) {
        LogUtil.a("WspEventBusUtil", "receiver get_weight_unit action unitType = ", Integer.valueOf(i));
        if (i == -1) {
            cgt.e().b(true);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("weightUnit", i);
        cnn.b().Jm_(bundle);
    }

    private void a(EventBus.b bVar) {
        LogUtil.a("WspEventBusUtil", "EVEBUS_GET_SCALE_VERSION");
        Bundle Kl_ = bVar.Kl_();
        if (Kl_ == null) {
            LogUtil.b("WspEventBusUtil", "bundle is null");
        } else {
            cgt.e().b(Kl_.getBoolean("isManualGetUnit", false));
        }
    }

    private void c() {
        byte[] bArr = new byte[31];
        byte[] d = new cgz().d(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011));
        System.arraycopy(d, 0, bArr, 0, d.length);
        this.c.f(bArr);
    }

    private void e(byte[] bArr, boolean z, String str) {
        if (d(bArr, z)) {
            ckm ckmVar = new ckm();
            ckmVar.e(false);
            float b = cgf.b(bArr, 0, 2) / 100.0f;
            ckmVar.setWeight(b);
            float b2 = cgf.b(bArr, 2, 2) / 10.0f;
            LogUtil.a("WspEventBusUtil", "weight is:", Float.valueOf(b), "; fatRate is:", Float.valueOf(b2));
            ckmVar.setBodyFatRat(b2);
            if (cgs.e(str, this.f721a)) {
                ReleaseLogUtil.e("R_Weight_WspEventBusUtil", "isSupportFats is true");
                ckmVar.setBodyFatRat(0.0f);
            }
            String e2 = cgz.e(bArr, 4);
            long time = new Date().getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            if (z) {
                try {
                    time = simpleDateFormat.parse(e2).getTime();
                } catch (ParseException unused) {
                    LogUtil.b("WspEventBusUtil", "Illegal time format:", e2);
                    return;
                }
            }
            ckmVar.setStartTime(time);
            ckmVar.setEndTime(time);
            e(bArr, 12, ckmVar, str);
            int b3 = cgf.b(bArr, 24, 2);
            ckmVar.g(b3);
            e(bArr, ckmVar, 24, str);
            LogUtil.c("WspEventBusUtil", "saveWeightData:", Float.valueOf(ckmVar.getWeight()), "; bodyFatRat:", Float.valueOf(ckmVar.getBodyFatRat()), "; time:", e2, "; heart:", Integer.valueOf(b3));
            cgt.e().c(z, ckmVar, bArr, 24);
        }
    }

    private void e(byte[] bArr, int i, ckm ckmVar, String str) {
        int i2 = 0;
        for (int i3 = 0; i3 < 12; i3 += 2) {
            ckmVar.b(i2, cgf.b(bArr, i3 + i, 2));
            i2++;
        }
        if (cgs.e(str, this.f721a)) {
            ckmVar.b(i2, 0.0d);
        }
    }

    private void e(byte[] bArr, ckm ckmVar, int i, String str) {
        boolean a2 = cji.a(str, 18);
        LogUtil.a("WspEventBusUtil", "deviceMac:", str, "isSupportMultiChannel:", Boolean.valueOf(a2), "weightDataBytes:", this.c.a(bArr), "length:", Integer.valueOf(bArr.length));
        if (!a2 || bArr.length < 38) {
            return;
        }
        ckmVar.d(2);
        int i2 = 0;
        for (int i3 = 0; i3 < 12; i3 += 2) {
            int b = cgf.b(bArr, i + 2 + i3, 2);
            ckmVar.c(i2, b);
            if (cgs.e(str, this.f721a)) {
                ckmVar.c(i2, 0.0d);
            }
            LogUtil.a("WspEventBusUtil", "moreResisData:", Integer.valueOf(b));
            i2++;
        }
    }

    private boolean d(byte[] bArr, boolean z) {
        if (z) {
            if (bArr.length == 28 || bArr.length == 40 || bArr.length == 27 || bArr.length == 39) {
                return true;
            }
            ReleaseLogUtil.e("R_Weight_WspEventBusUtil", "History data illegal length!");
            return false;
        }
        if (bArr.length == 26 || bArr.length == 38) {
            return true;
        }
        ReleaseLogUtil.e("R_Weight_WspEventBusUtil", "realtime data illegal length!");
        return false;
    }

    private void Fv_(Bundle bundle) {
        String string = bundle.getString("configjson");
        this.c.c(string);
        LogUtil.c("WspEventBusUtil", "hilink_json sendWifiConfigFor2021 configjson=", string);
    }

    private void Fw_(Bundle bundle, cxh cxhVar) {
        LogUtil.a("WspEventBusUtil", "AssembleDataPackage enter");
        if (bundle == null) {
            LogUtil.h("WspEventBusUtil", "AssembleDataPackage bundle is null");
            return;
        }
        String string = bundle.getString("ssid");
        String string2 = bundle.getString("pwd");
        int i = bundle.getInt("encryptMode");
        String string3 = bundle.getString("deviceSsid");
        String string4 = bundle.getString("registerMsg");
        byte[] a2 = ctx.a();
        String d = ctx.d();
        byte[] b = ctu.b(cha.e(string2), d, a2);
        if (!TextUtils.isEmpty(string4)) {
            LogUtil.c("WspEventBusUtil", "AssembleDataPackage encodeRegisterInfos length:", Integer.valueOf(ctu.b(cha.e(string4), d, a2).length));
        } else {
            LogUtil.h("WspEventBusUtil", "AssembleDataPackage registerMessage is null");
        }
        byte[] a3 = ctv.a(i, this.c.d(b), d);
        byte[] e2 = cha.e(string);
        boolean z = a3 == null || cha.e(string3) == null;
        boolean z2 = e2 == null || b == null;
        if (z || z2 || a3.length != 16) {
            this.c.e(cxhVar, -1);
            LogUtil.h("WspEventBusUtil", "encrypt data length is not correct");
        } else {
            this.c.a(string, string2, i, string3, string4);
        }
    }
}
