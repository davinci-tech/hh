package defpackage;

import com.amap.api.services.core.AMapException;
import java.io.Serializable;

/* loaded from: classes3.dex */
public class dbb implements Serializable {
    private String d;

    public dbb(byte b) {
        this.d = "";
        if (b == 2) {
            this.d = "血压模块自检错误，可能是传感器或A/D错误";
            return;
        }
        if (b != 19) {
            switch (b) {
                case 6:
                    this.d = "袖带松或者未连接袖带";
                    break;
                case 7:
                    this.d = "漏气（阀门等处）";
                    break;
                case 8:
                    this.d = "气压错误（可能是阀门没有正常打开）";
                    break;
                case 9:
                    this.d = "弱信号（可能是袖带太松等, 可能与错误0x02有关）";
                    break;
                case 10:
                    this.d = "超范围 （测量对象超过设备测量范围）";
                    break;
                case 11:
                    this.d = "过分运动（有信号干扰等）";
                    break;
                case 12:
                    this.d = "过压";
                    break;
                case 13:
                    this.d = "信号饱和";
                    break;
                case 14:
                    this.d = "漏气（类似于错误0x03）";
                    break;
                case 15:
                    this.d = "系统错误";
                    break;
                default:
                    this.d = AMapException.AMAP_CLIENT_UNKNOWN_ERROR;
                    break;
            }
            return;
        }
        this.d = "测量超时";
    }

    public String a() {
        return this.d;
    }
}
