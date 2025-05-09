package com.huawei.watchface;

import android.util.SparseArray;
import com.huawei.watchface.mvp.model.webview.JsInteractAddition;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.analytice.data.BaseEvent;
import java.util.LinkedHashMap;

/* loaded from: classes7.dex */
public class eg extends BaseEvent<eg> {

    /* renamed from: a, reason: collision with root package name */
    private static final SparseArray<String> f11006a;
    private String h;
    private String i;
    private String j;
    private LinkedHashMap<String, String> k;
    private String l;

    static {
        SparseArray<String> sparseArray = new SparseArray<>();
        f11006a = sparseArray;
        sparseArray.put(1, "安装成功");
        sparseArray.put(140004, "单板上表盘数量达到上限");
        sparseArray.put(20000, "文件不存在");
        sparseArray.put(20001, "APP获取hash值失败");
        sparseArray.put(140001, "参数错误");
        sparseArray.put(140002, "设备侧返回的错误码类型 文件正在下载");
        sparseArray.put(140003, "文件名称太长");
        sparseArray.put(140005, "设备侧文件信息异常");
        sparseArray.put(140007, "文件名中包含单板不支持的特殊字符");
        sparseArray.put(140009, "设备侧空间不足");
        sparseArray.put(140006, "设备侧通用文件不存在");
        sparseArray.put(141000, "设备请求超时");
        sparseArray.put(141001, "设备连接断开");
        sparseArray.put(142000, "表盘任务被挂起");
        sparseArray.put(140008, "单板上已经存在重复（相同文件名称）的文件，无需再重复添加");
        sparseArray.put(142001, "设备文件校验失败");
        sparseArray.put(109022, "OTA传输中");
    }

    @Override // com.huawei.watchface.em
    public LinkedHashMap<String, String> b() {
        return h();
    }

    private LinkedHashMap<String, String> h() {
        if (this.k == null) {
            this.k = new LinkedHashMap<>();
        }
        this.k.put(JsInteractAddition.HI_TOP_ID, q());
        this.k.put("version", r());
        this.k.put("startts", String.valueOf(m()));
        this.k.put("endts", String.valueOf(n()));
        this.k.put("totalTime", String.valueOf(o()));
        this.k.put("errorCode", p());
        this.k.put("title", e());
        this.k.put("cnTitle", f());
        this.k.put("useType", d());
        this.k.put("datalen", g());
        this.k.put(BaseEvent.KEY_DESCINFO, a(q(), r(), CommonUtils.b(p())));
        return this.k;
    }

    public String d() {
        return this.l;
    }

    public void a(String str) {
        this.l = str;
    }

    public String e() {
        return this.h;
    }

    public void b(String str) {
        this.h = str;
    }

    public String f() {
        return this.i;
    }

    public void c(String str) {
        this.i = str;
    }

    public String g() {
        return this.j;
    }

    public void d(String str) {
        this.j = str;
    }

    private static String a(String str, String str2, int i) {
        return "hitopid=" + str + "_version=" + str2 + "_" + f11006a.get(i);
    }

    @Override // com.huawei.watchface.em
    public String a() {
        return "WATCHFACE_INSTALL";
    }
}
