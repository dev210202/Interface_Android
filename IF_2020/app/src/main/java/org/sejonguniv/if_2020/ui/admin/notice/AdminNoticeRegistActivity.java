package org.sejonguniv.if_2020.ui.admin.notice;

import android.content.Intent;
import android.os.Bundle;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseActivity;
import org.sejonguniv.if_2020.databinding.ActivityAdminNoticeRegistBinding;

public class AdminNoticeRegistActivity extends BaseActivity<ActivityAdminNoticeRegistBinding> {

    int REGIST_DONE = 22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_notice_regist);
        setBinding(R.layout.activity_admin_notice_regist);

        binding.completeButton.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("registTitle", binding.titleEdittext.getText().toString());
            intent.putExtra("registContent", binding.contentEdittext.getText().toString());
            setResult( REGIST_DONE, intent);
            finish();
        });
    }
}