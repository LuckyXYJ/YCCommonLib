package com.yc.apprestartlib;

import androidx.annotation.StringDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * <pre>
 *     @author yangchong
 *     GitHub : https://github.com/yangchong211/YCCommonLib
 *     email : yangchong211@163.com
 *     time  : 2018/11/9
 *     desc  : 重启APP工厂类
 *     revise: 使用简单工厂模式
 * </pre>
 */
public final class RestartFactory {

    public static final String ALARM = "alarm";
    public static final String SERVICE = "service";
    public static final String LAUNCHER = "launcher";
    public static final String MANIFEST = "manifest";

    /**
     * 使用简单工厂模式
     *
     * @param type 参数类型
     * @return 具体产品
     */
    public static IRestartProduct create(@RestartType String type) {
        switch (type) {
            case ALARM:
                return new AlarmRestartImpl();
            case SERVICE:
                return new ServiceRestartImpl();
            case LAUNCHER:
                return new LauncherRestartImpl();
            case MANIFEST:
                return new ManifestRestartImpl();
            default:
                return new EmptyRestartImpl();
        }
    }

    @StringDef({ALARM, SERVICE, LAUNCHER, MANIFEST})
    @Retention(RetentionPolicy.SOURCE)
    public @interface RestartType {
    }

}
