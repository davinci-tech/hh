package com.huawei.ui.main.stories.fitness.interactors.stressAdvice;

/* loaded from: classes9.dex */
public class PressureAdeviceRetrunValue {
    private d addition;
    private int advice_num_1;
    private int advice_num_2;
    private int err_code;

    static class d {
    }

    public int getError_code() {
        return this.err_code;
    }

    public int getAdvice_num_1() {
        return this.advice_num_1;
    }

    public int getAdvice_num_2() {
        return this.advice_num_2;
    }

    public String toString() {
        return "error_code = " + this.err_code + ",advice_num_1 = " + this.advice_num_1 + ",advice_num_2 = " + this.advice_num_2;
    }
}
