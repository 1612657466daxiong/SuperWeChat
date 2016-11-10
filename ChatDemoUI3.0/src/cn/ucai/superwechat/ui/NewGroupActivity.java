/**
 * Copyright (C) 2016 Hyphenate Inc. All rights reserved.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.ucai.superwechat.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.chat.EMGroupManager;
import com.hyphenate.chat.EMGroupManager.EMGroupOptions;
import com.hyphenate.chat.EMGroupManager.EMGroupStyle;
import com.hyphenate.easeui.domain.GroupAvatar;
import com.hyphenate.easeui.utils.EaseImageUtils;
import com.hyphenate.easeui.widget.EaseAlertDialog;
import com.hyphenate.exceptions.HyphenateException;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.bean.Result;
import cn.ucai.superwechat.net.NetDao;
import cn.ucai.superwechat.utils.CommonUtils;
import cn.ucai.superwechat.utils.I;
import cn.ucai.superwechat.utils.OkHttpUtils;

public class NewGroupActivity extends BaseActivity {
    private static final int REQUESTCODE_PICK = 1;
    private static final int REQUESTCODE_CUTTING = 2;
    private static final int REQUESTCODE_CHOSEFRIEND=3;

    @InjectView(R.id.newgroup_picture_avatar)
    ImageView newgroupPictureAvatar;
    @InjectView(R.id.edit_group_name)
    EditText groupNameEditText;
    @InjectView(R.id.edit_group_introduction)
    EditText introductionEditText;
    @InjectView(R.id.cb_public)
    CheckBox publibCheckBox;
    @InjectView(R.id.second_desc)
    TextView secondTextView;
    @InjectView(R.id.cb_member_inviter)
    CheckBox memberCheckbox;
    @InjectView(R.id.iv_back)
    ImageView mivBack;
    @InjectView(R.id.tv_title)
    TextView mtvTitle;
    @InjectView(R.id.tvRig)
    TextView mtvRig;
    // private EditText groupNameEditText;
    private ProgressDialog progressDialog;
    File avatarfile=null;
    // private EditText introductionEditText;
    // private CheckBox publibCheckBox;
    // private CheckBox memberCheckbox;
    //  private TextView secondTextView;
    // private RelativeLayout mlayoutpic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.em_activity_new_group);
        ButterKnife.inject(this);
        initView();
        setlistener();

    }

    private void setlistener() {
        publibCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    secondTextView.setText(R.string.join_need_owner_approval);
                } else {
                    secondTextView.setText(R.string.Open_group_members_invited);
                }
            }
        });
    }

    private void initView() {
        mivBack.setVisibility(View.VISIBLE);
        mtvTitle.setVisibility(View.VISIBLE);
        mtvTitle.setText(getString(R.string.The_new_group_chat));
        mtvRig.setVisibility(View.VISIBLE);
        mtvRig.setText(getString(R.string.button_save));
    }


    public void save() {
        String name = groupNameEditText.getText().toString();
        if (TextUtils.isEmpty(name)) {
            new EaseAlertDialog(this, R.string.Group_name_cannot_be_empty).show();
        } else {
            // select from contact list
            startActivityForResult(new Intent(this, GroupPickContactsActivity.class).putExtra("groupName", name), REQUESTCODE_CHOSEFRIEND);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        switch (requestCode) {
            case REQUESTCODE_PICK:
                if (data == null || data.getData() == null) {
                    return;
                }
                startPhotoZoom(data.getData());
                break;
            case REQUESTCODE_CUTTING:
                if (data != null) {
                    setPicToView(data);
                }
                break;
            case REQUESTCODE_CHOSEFRIEND:
                if (resultCode==RESULT_OK){
                    creategroupafterchose(requestCode, resultCode, data);
                }
                break;
            default:
                break;
        }

    }

    private void creategroupafterchose(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String st1 = getResources().getString(R.string.Is_to_create_a_group_chat);
        final String st2 = getResources().getString(R.string.Failed_to_create_groups);
        if (resultCode == RESULT_OK) {

            //new group
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(st1);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    final String groupName = groupNameEditText.getText().toString().trim();
                    String desc = introductionEditText.getText().toString();
                    String[] members = data.getStringArrayExtra("newmembers");
                    try {
                        EMGroupOptions option = new EMGroupOptions();
                        option.maxUsers = 200;

                        String reason = NewGroupActivity.this.getString(R.string.invite_join_group);
                        reason = EMClient.getInstance().getCurrentUser() + reason + groupName;

                        if (publibCheckBox.isChecked()) {
                            option.style = memberCheckbox.isChecked() ? EMGroupStyle.EMGroupStylePublicJoinNeedApproval : EMGroupStyle.EMGroupStylePublicOpenJoin;
                        } else {
                            option.style = memberCheckbox.isChecked() ? EMGroupStyle.EMGroupStylePrivateMemberCanInvite : EMGroupStyle.EMGroupStylePrivateOnlyOwnerInvite;
                        }
                        EMGroup emGroup = EMClient.getInstance().groupManager().createGroup(groupName, desc, members, reason, option);

                        createGroupService(emGroup);

                    } catch (final HyphenateException e) {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                progressDialog.dismiss();
                                Toast.makeText(NewGroupActivity.this, st2 + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                }
            }).start();
        }
    }

    private void  creategroupsuccess(){
        runOnUiThread(new Runnable() {
            public void run() {
                progressDialog.dismiss();
                setResult(RESULT_OK);
                finish();
            }
        });
    }

    private void createGroupService(EMGroup emGroup) {
        if (avatarfile==null){
        NetDao.creategroup2service(this, emGroup, new OkHttpUtils.OnCompleteListener<Result>() {
            @Override
            public void onSuccess(Result result) {
                afternetdaosuccess(result);
            }

            @Override
            public void onError(String error) {

            }
        });
        }else {
            NetDao.creategroup2service(this, emGroup, avatarfile, new OkHttpUtils.OnCompleteListener<Result>() {
                @Override
                public void onSuccess(Result result) {
                    afternetdaosuccess(result);
                }

                @Override
                public void onError(String error) {

                }
            });
        }
    }

    private void afternetdaosuccess(Result result) {
        if (result!=null){
           if (result.isRetMsg()){
               Gson gson = new Gson();
               GroupAvatar group = gson.fromJson(result.getRetData().toString(), GroupAvatar.class);
               creategroupsuccess();
           }
        }

    }


    @OnClick({R.id.newgroup_picture, R.id.iv_back, R.id.tvRig})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.newgroup_picture:
                uploadHeadPhoto();
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tvRig:
                save();
                break;
        }
    }

    private void uploadHeadPhoto() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dl_title_upload_photo);
        builder.setItems(new String[]{getString(R.string.dl_msg_take_photo), getString(R.string.dl_msg_local_upload)},
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        switch (which) {
                            case 0:
                                Toast.makeText(NewGroupActivity.this, getString(R.string.toast_no_support),
                                        Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
                                pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                                startActivityForResult(pickIntent, REQUESTCODE_PICK);
                                break;
                            default:
                                break;
                        }
                    }
                });
        builder.create().show();
    }
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", true);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, REQUESTCODE_CUTTING);
    }

    /**
     * save the picture data
     *
     * @param picdata
     */
    private void setPicToView(Intent picdata) {
        Bundle extras = picdata.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            Drawable drawable = new BitmapDrawable(getResources(), photo);
            newgroupPictureAvatar.setImageDrawable(drawable);
            saveBitmap2file(picdata);

            Toast.makeText(NewGroupActivity.this, getString(R.string.toast_updatephoto_success),
                    Toast.LENGTH_SHORT).show();

        }

    }
    public void saveBitmap2file(Intent data){
        Bundle extras = data.getExtras();
        if (extras!=null){
            Bitmap bitmap = extras.getParcelable("data");
            String ptah = EaseImageUtils.getImagePath(System.currentTimeMillis()+ I.AVATAR_SUFFIX_JPG);
            File file = new File(ptah);
            try {
                BufferedOutputStream bos =new BufferedOutputStream(new FileOutputStream(file));
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,bos);
                bos.flush();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            avatarfile=file;
        }

    }


}
