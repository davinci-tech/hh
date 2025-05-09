package com.huawei.health.sport.model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/* loaded from: classes8.dex */
public class CalorieConsumptionViewModel extends ViewModel {
    private MutableLiveData<c> e = new MutableLiveData<>();

    public static class c {

        /* renamed from: a, reason: collision with root package name */
        public int f2989a;
        public int b;
        public int c;

        public int e() {
            return this.b;
        }

        public int a() {
            return this.f2989a;
        }

        public int b() {
            return this.c;
        }
    }
}
