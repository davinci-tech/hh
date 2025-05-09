package com.huawei.animationkit.computationalwallpaper.pattern.variable;

/* loaded from: classes8.dex */
public class VariableType {

    public interface IVariableType {
        String getKey();
    }

    public enum Crown implements IVariableType {
        SCROLL("crown_scroll"),
        DEGREE("crown_degree"),
        SMOOTH_DEGREE("smooth_degree");

        private final String mKey;

        Crown(String str) {
            this.mKey = str;
        }

        @Override // com.huawei.animationkit.computationalwallpaper.pattern.variable.VariableType.IVariableType
        public String getKey() {
            return this.mKey;
        }
    }

    public enum Random implements IVariableType {
        RANDOM;

        @Override // com.huawei.animationkit.computationalwallpaper.pattern.variable.VariableType.IVariableType
        public String getKey() {
            return "random";
        }
    }
}
