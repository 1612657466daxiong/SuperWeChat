package cn.ucai.superwechat.utils;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/6.
 */
public class ExitAppUtils {
    List<Activity> mActivitys = new LinkedList<>();
    private static ExitAppUtils instance = new ExitAppUtils();
    private ExitAppUtils(){};
    public static ExitAppUtils getInstance(){
        return instance;
    }
    public void addActivity(Activity activity){
        mActivitys.add(activity);
    }
    public void delActivity(Activity activity){
        mActivitys.remove(activity);
    }
    public void exit(){
        for (Activity activity :mActivitys){
            activity.finish();
        }
    }

}
