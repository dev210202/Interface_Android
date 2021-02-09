package org.sejonguniv.if_2020.ui.admin.excel;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.multidex.BuildConfig;

import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseFragment;
import org.sejonguniv.if_2020.databinding.FragmentAdminExcelBinding;
import org.sejonguniv.if_2020.model.Notice;
import org.sejonguniv.if_2020.model.People;
import org.sejonguniv.if_2020.ui.adapter.ExcelAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class AdminExcelFragment extends BaseFragment<FragmentAdminExcelBinding, AdminExcelFragmentViewModel> {

    int ADD = 10;
    int ADD_DONE = 20;
    int CREATE_FILE = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setBinding(inflater, R.layout.fragment_admin_excel, container);
        setViewModel(AdminExcelFragmentViewModel.class);

        binding.setPeopleList(viewModel.peopleArrayList);
        setProgressBar();
        dialog.show();
        viewModel.getExcelDataToServer();

        binding.addMemberButton.setOnClickListener(new onClickListener());
        binding.saveLocalButton.setOnClickListener(new onClickListener());
        binding.saveServerButton.setOnClickListener(new onClickListener());

        Observer<ArrayList<People>> arrayListObserver = new Observer<ArrayList<People>>() {
            @Override
            public void onChanged(ArrayList<People> people) {
                binding.setPeopleList(viewModel.peopleArrayList);
            }
        };

        Observer<Boolean> dialogObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                dialog.dismiss();
            }
        };


        viewModel.peopleArrayList.observe(this, arrayListObserver);
        viewModel.isDataReceive.observe(this, dialogObserver);
        return binding.getRoot();
    }

    private class onClickListener implements View.OnClickListener {

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.add_member_button: {
                    Intent intent = new Intent(getContext(), AdminExcelAddActivity.class);
                    startActivityForResult(intent, ADD);
                    break;
                }


                case R.id.save_local_button: {



                    if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                        createFile();
                        Toast.makeText(getActivity().getApplicationContext(), "다운로드 폴더에 저장되었습니다.", Toast.LENGTH_LONG).show();
                    }
                    else{
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    }

                    break;
                }

                case R.id.save_server_button: {
                    viewModel.saveDataToServer();
                    break;
                }


            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == ADD_DONE) {

            viewModel.peopleArrayList.getValue().add((People) data.getSerializableExtra("peopleInfo"));
            viewModel.saveDataToServer();
            // viewModel에서 멤버추가 메소드 실행

        }
        if(requestCode == CREATE_FILE){
            Uri uri = data.getData();
            viewModel.saveDataToLocal(uri, getActivity());
        }

    }
    private void createFile() {
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_TITLE, "명부.xls");

        intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, 1);

        startActivityForResult(intent, CREATE_FILE);
    }
}