package org.sejonguniv.if_2020.ui.admin.notice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseActivity;
import org.sejonguniv.if_2020.databinding.ActivityAdminNoticeEditBinding;

public class AdminNoticeEditActivity extends BaseActivity<ActivityAdminNoticeEditBinding> {
    int EDIT = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_notice_edit);
        setBinding(R.layout.activity_admin_notice_edit);

        binding.titleEdittext.setText(getIntent().getStringExtra("title"));
        binding.contentEdittext.setText(getIntent().getStringExtra("content"));
        binding.completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("editTitle", binding.titleEdittext.getText().toString());
                intent.putExtra("editContent", binding.contentEdittext.getText().toString());

                setResult(getIntent().getIntExtra("position", 0), intent);
                finish();
            }
        });
    }
}