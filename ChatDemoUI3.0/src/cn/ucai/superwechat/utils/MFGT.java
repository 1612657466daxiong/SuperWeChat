package cn.ucai.superwechat.utils;

import android.app.Activity;
import android.content.Intent;

import com.hyphenate.easeui.domain.User;

import cn.ucai.superwechat.R;
import cn.ucai.superwechat.ui.AddContactActivity;
import cn.ucai.superwechat.ui.AddFrinendActivity;
import cn.ucai.superwechat.ui.ChatActivity;
import cn.ucai.superwechat.ui.FriendProfileActivity;
import cn.ucai.superwechat.ui.GroupsActivity;
import cn.ucai.superwechat.ui.LoginActivity;
import cn.ucai.superwechat.ui.MainActivity;
import cn.ucai.superwechat.ui.NewFriendsMsgActivity;
import cn.ucai.superwechat.ui.NewGroupActivity;
import cn.ucai.superwechat.ui.RegisterActivity;
import cn.ucai.superwechat.ui.SettingsActivity;
import cn.ucai.superwechat.ui.UserProfileActivity;

/**
 * Created by Administrator on 2016/11/1.
 */
public class MFGT {
    public static void finish(Activity context){
        context.finish();
    }
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
    public static void gotoSettingsActivity(Activity context){
        Intent intent = new Intent(context, SettingsActivity.class);
        startActivity(context,intent);
    }

    public static void gotoUserProfileActivity(Activity context){
        Intent intent = new Intent(context, UserProfileActivity.class);
        startActivity(context,intent);
    }
    public static void gotoaddfirend(Activity context){
        Intent intent = new Intent(context, AddContactActivity.class);
        startActivity(context,intent);
    }

    public static void gotoaddfriendActivity(Activity context,String username){
        Intent intent = new Intent(context, AddFrinendActivity.class);
        intent.putExtra("UserName",username);
        startActivity(context,intent);
    }
    public static void gotochat(Activity context,String username){
        Intent intent = new Intent(context, ChatActivity.class);
        intent.putExtra("userId",username);
        startActivity(context,intent);
    }


    public static void gotofriendactivity(Activity context, User user) {
        Intent intent = new Intent(context, FriendProfileActivity.class);
        intent.putExtra("User",user);
        startActivity(context,intent);
    }
    public static void gotoNewFriendsMsgActivity(Activity context) {
        Intent intent = new Intent(context, NewFriendsMsgActivity.class);
        startActivity(context,intent);
    }

    public static void gotoGroupsActivity(Activity context){
        Intent intent = new Intent(context, GroupsActivity.class);
        startActivity(context,intent);
    }
    public static void gotoNewGroupActivity(Activity context){
        Intent intent = new Intent(context, NewGroupActivity.class);
        startActivity(context,intent);
    }
}
