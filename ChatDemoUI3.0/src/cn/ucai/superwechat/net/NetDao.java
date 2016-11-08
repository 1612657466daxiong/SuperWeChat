package cn.ucai.superwechat.net;

import android.content.Context;

import com.baidu.platform.comapi.map.C;

import java.io.File;

import cn.ucai.superwechat.bean.Result;
import cn.ucai.superwechat.utils.I;
import cn.ucai.superwechat.utils.MD5;
import cn.ucai.superwechat.utils.OkHttpUtils;

/**
 * Created by Administrator on 2016/11/1.
 */
public class NetDao {
    public static void register(Context context, String username, String usernick, String passwrod, OkHttpUtils.OnCompleteListener listener){
        OkHttpUtils<Result> utils = new OkHttpUtils<>(context);
        utils.url(I.SERVER_ROOT+I.REQUEST_REGISTER)
                .addParam(I.User.USER_NAME,username)
                .addParam(I.User.NICK,usernick)
                .addParam(I.User.PASSWORD, MD5.getMessageDigest(passwrod))
                .targetClass(Result.class)
                .post()
                .execute(listener);
    }

    public static void unregister(Context context,String username,OkHttpUtils.OnCompleteListener listener){
        OkHttpUtils<Result> utils = new OkHttpUtils<>(context);
        utils.url(I.SERVER_ROOT+I.REQUEST_UNREGISTER)
                .addParam(I.User.USER_NAME,username)
                .targetClass(Result.class)
                .execute(listener);
    }

    public static void login(Context context,String username,String pwd,OkHttpUtils.OnCompleteListener listener){
        OkHttpUtils<Result> utils = new OkHttpUtils<>(context);
        utils.url(I.SERVER_ROOT+I.REQUEST_LOGIN)
                .addParam(I.User.USER_NAME,username)
                .addParam(I.User.PASSWORD,MD5.getMessageDigest(pwd))
                .targetClass(Result.class)
                .execute(listener);
    }
    public static void updatenick(Context context,String username,String nick,OkHttpUtils.OnCompleteListener listener){
        OkHttpUtils<Result> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_UPDATE_USER_NICK)
                .addParam(I.User.USER_NAME,username)
                .addParam(I.User.NICK,nick)
                .targetClass(Result.class)
                .execute(listener);
    }
    public static void updateAvater(Context context, String username, File file, OkHttpUtils.OnCompleteListener listener){
        OkHttpUtils<Result> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_UPDATE_AVATAR)
                .addParam(I.NAME_OR_HXID,username)
                .addParam(I.AVATAR_TYPE,I.AVATAR_TYPE_USER_PATH)
                .addFile2(file)
                .targetClass(Result.class)
                .post()
                .execute(listener);
    }
    public static void searchuser(Context context,String username,OkHttpUtils.OnCompleteListener listener){
        OkHttpUtils<Result> utils = new OkHttpUtils<>(context);
        utils.url(I.SERVER_ROOT+I.REQUEST_FIND_USER)
                .addParam(I.User.USER_NAME,username)
                .targetClass(Result.class)
                .execute(listener);
    }
    public static void  addcontact(Context context,String username,String cusername,OkHttpUtils.OnCompleteListener listener){
        OkHttpUtils<Result> utils = new OkHttpUtils<>(context);
        utils.url(I.SERVER_ROOT+I.REQUEST_ADD_CONTACT)
                .addParam(I.Contact.USER_NAME,username)
                .addParam(I.Contact.CU_NAME,cusername)
                .targetClass(Result.class)
                .execute(listener);
    }

    public static void deletecontact(Context context,String username,String cusername,OkHttpUtils.OnCompleteListener listener){
        OkHttpUtils<Result> utils = new OkHttpUtils<>(context);
        utils.url(I.SERVER_ROOT+I.REQUEST_DELETE_CONTACT)
                .addParam(I.Contact.USER_NAME,username)
                .addParam(I.Contact.CU_NAME,cusername)
                .targetClass(Result.class)
                .execute(listener);
    }


}
