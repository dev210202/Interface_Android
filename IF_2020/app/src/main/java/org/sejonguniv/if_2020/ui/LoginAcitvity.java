package org.sejonguniv.if_2020.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseActivity;
import org.sejonguniv.if_2020.databinding.ActivityLoginAcitvityBinding;

public class LoginAcitvity extends BaseActivity<ActivityLoginAcitvityBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acitvity);
        setBinding(R.layout.activity_login_acitvity);
        LoginViewModel viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        setProgressBar();

        binding.cancelButton.setOnClickListener(v -> binding.editText.setText(""));
        binding.loginButton.setOnClickListener(v -> {
            dialog.show();
            viewModel.login(binding.editText.getText().toString());
        });

        Observer<String> responseObserver = response -> {
            Intent intent;
            dialog.dismiss();
            if (response.equals("관리자(으)로 로그인됐습니다.")) {
                intent = new Intent(getApplicationContext(), AdminMainActivity.class);
                startActivity(intent);
            } else if (response.equals("회원(으)로 로그인됐습니다.")) {
                intent = new Intent(getApplicationContext(), UserMainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
            }
        };
        viewModel.loginResponse.observe(this, responseObserver);
    }

}