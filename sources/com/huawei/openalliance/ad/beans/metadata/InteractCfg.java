package com.huawei.openalliance.ad.beans.metadata;

import com.huawei.openalliance.ad.utils.cz;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class InteractCfg implements Serializable {
    private static final int DEFAULT_TWIST_ACC = 15;
    private static final int MIN_TWIST_ACC = 10;
    private static final long serialVersionUID = 9151867350505964337L;
    private Integer splashAdTagClickableType;
    private Integer splashInteractCfg;
    private String splashRedirectTxt;
    private String swipeClkTxt;
    private Integer swipeDirection;
    private Integer swipeDp;
    private String swipeTxt;
    private Integer twistAcc;
    private String twistClkTxt;
    private Integer twistDegree;
    private String twistTxt;

    public String k() {
        return cz.c(this.splashRedirectTxt);
    }

    public String j() {
        return cz.c(this.twistClkTxt);
    }

    public String i() {
        return cz.c(this.swipeClkTxt);
    }

    public String h() {
        return cz.c(this.twistTxt);
    }

    public String g() {
        return cz.c(this.swipeTxt);
    }

    public void f(Integer num) {
        this.swipeDirection = num;
    }

    public Integer f() {
        Integer num = this.swipeDirection;
        if (num == null || !(num.intValue() == 0 || this.swipeDirection.intValue() == 1)) {
            return 0;
        }
        return this.swipeDirection;
    }

    public void e(String str) {
        this.splashRedirectTxt = str;
    }

    public void e(Integer num) {
        this.twistDegree = num;
    }

    public Integer e() {
        return this.splashAdTagClickableType;
    }

    public void d(String str) {
        this.twistClkTxt = str;
    }

    public void d(Integer num) {
        this.twistAcc = num;
    }

    public Integer d() {
        return this.twistDegree;
    }

    public void c(String str) {
        this.swipeClkTxt = str;
    }

    public void c(Integer num) {
        this.swipeDp = num;
    }

    public Integer c() {
        Integer num = this.twistAcc;
        if (num == null) {
            return 15;
        }
        if (10 > num.intValue()) {
            return 10;
        }
        return this.twistAcc;
    }

    public void b(String str) {
        this.twistTxt = str;
    }

    public void b(Integer num) {
        this.splashAdTagClickableType = num;
    }

    public Integer b() {
        return this.swipeDp;
    }

    public void a(String str) {
        this.swipeTxt = str;
    }

    public void a(Integer num) {
        this.splashInteractCfg = num;
    }

    public Integer a() {
        Integer num = this.splashInteractCfg;
        if (num == null || (num.intValue() >= 0 && this.splashInteractCfg.intValue() <= 4)) {
            return this.splashInteractCfg;
        }
        return 0;
    }
}
