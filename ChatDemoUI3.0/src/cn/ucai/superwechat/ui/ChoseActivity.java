package cn.ucai.superwechat.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.utils.MFGT;

public class ChoseActivity extends AppCompatActivity {
    Button btnlogin, btnregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose);
        ButterKnife.inject(this);
        btnlogin = (Button) findViewById(R.id.chose_login);
        btnregister = (Button) findViewById(R.id.chose_sign_up);

    }

    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.chose_login:
                MFGT.gotoLoginActivity(this);
                break;
            case R.id.chose_sign_up:
                MFGT.gotoRegisterActivity(this);
                break;
        }
    }
}
