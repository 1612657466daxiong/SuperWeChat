package cn.ucai.superwechat.utils;

import android.app.Activity;
import android.content.Intent;

import cn.ucai.superwechat.R;
import cn.ucai.superwechat.ui.LoginActivity;
import cn.ucai.superwechat.ui.RegisterActivity;

/**
 * Created by Administrator on 2016/11/1.
 */
public class MFGT {
    public static void startActivity(Activity  context, Intent intent){
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.push_left_in,R.anim.push_right_out);
    }
    public static void gotoLoginActivity(Activity context){
        Intent intent = new Intent(context, LoginActivity.class);
        startActivity(context,intent);
    }

    public static void gotoRegisterActivity(Activity context){
        Intent intent = new Intent(context, RegisterActivity.class);
        startActivity(context,intent);
    }
}
