package org.sejonguniv.if_2020.ui.admin.excel;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseFragment;
import org.sejonguniv.if_2020.databinding.FragmentAdminExcelBinding;
import org.sejonguniv.if_2020.model.People;

import java.util.ArrayList;


public class AdminExcelFragment extends BaseFragment<FragmentAdminExcelBinding, AdminExcelFragmentViewModel> {

    int ADD = 10;
    int ADD_DONE = 20;
    int EDIT = 30;
    int EDIT_DONE = 40;
    int CREATE_FILE = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setBinding(inflater, R.layout.fragment_admin_excel, container);
        setViewModel(AdminExcelFragmentViewModel.class);
        setProgressBar();

        dialog.show();
        viewModel.getExcelDataToServer();

        binding.setPeopleList(viewModel.peopleArrayList.getValue());
        binding.addMemberButton.setOnClickListener(new onClickListener());
        binding.saveLocalButton.setOnClickListener(new onClickListener());
        binding.deleteMemberButton.setOnClickListener(new onClickListener());
        binding.editMemberButton.setOnClickListener(new onClickListener());

        Observer<ArrayList<People>> arrayListObserver = people -> {
            binding.setPeopleList(people);
            dialog.dismiss();
        };

        Observer<String> responseObserver = s -> {
            Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
            dialog.dismiss();
        };

        viewModel.peopleArrayList.observe(this, arrayListObserver);
        viewModel.isResponseReceive.observe(this, responseObserver);

        return binding.getRoot();
    }

    private class onClickListener implements View.OnClickListener {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.add_member_button) {
                Intent intent = new Intent(getContext(), AdminExcelAddActivity.class);
                startActivityForResult(intent, ADD);
            } else if (id == R.id.delete_member_button) {
                showDeleteDialog();
            } else if (id == R.id.edit_member_button) {
                showEditDialog();
            } else if (id == R.id.save_local_button) {
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    createFile();
                } else {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ADD_DONE) {
            People people = (People) data.getSerializableExtra("peopleInfo");
            viewModel.saveDataToServer(people);
        }
        if (resultCode == EDIT_DONE) {
            People people = (People) data.getSerializableExtra("editResult");
            int id = data.getIntExtra("id", 0);
            viewModel.editDataToServer(id, people);
        }
        if (requestCode == CREATE_FILE) {
            try {
                Uri uri = data.getData();
                viewModel.saveDataToLocal(uri, getActivity());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createFile() {
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_TITLE, "명부.xls");

        intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, 1);

        startActivityForResult(intent, CREATE_FILE);
    }

    private void showDeleteDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_member_delete, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialogView);
        AlertDialog deleteDialog = builder.create();
        deleteDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        deleteDialog.show();
        EditText numberEditText = dialogView.findViewById(R.id.number_edittext);
        Button cancelButton = dialogView.findViewById(R.id.cancel_button);
        Button okButton = dialogView.findViewById(R.id.ok_button);

        cancelButton.setOnClickListener(v -> deleteDialog.dismiss());
        okButton.setOnClickListener(v -> {
            deleteDialog.dismiss();
            int value = Integer.parseInt(numberEditText.getText().toString());
            int id = viewModel.peopleArrayList.getValue().get(value - 1).getId();
            viewModel.deleteMember(id);
        });

    }

    private void showEditDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_member_edit, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialogView);
        AlertDialog editDialog = builder.create();
        editDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        editDialog.show();
        EditText numberEditText = dialogView.findViewById(R.id.number_edittext);
        Button cancelButton = dialogView.findViewById(R.id.cancel_button);
        Button okButton = dialogView.findViewById(R.id.ok_button);

        cancelButton.setOnClickListener(v -> editDialog.dismiss());
        okButton.setOnClickListener(v -> {
            editDialog.dismiss();
            int value = Integer.parseInt(numberEditText.getText().toString());
            try {
                People people = viewModel.peopleArrayList.getValue().get(value - 1);
                Intent intent = new Intent(getActivity(), AdminExcelEditActivity.class);
                intent.putExtra("editPeople", people);
                intent.putExtra("id", people.getId());
                startActivityForResult(intent, EDIT);
            } catch (IndexOutOfBoundsException e) {
                viewModel.isResponseReceive.setValue("수정하려는 회원을 찾을 수 없습니다.");
            }
        });
    }
}