package org.sejonguniv.if_2020.ui.admin.excel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseActivity;
import org.sejonguniv.if_2020.databinding.ActivityAdminExcelAddBinding;
import org.sejonguniv.if_2020.model.ManageStatus;
import org.sejonguniv.if_2020.model.People;

public class AdminExcelAddActivity extends BaseActivity<ActivityAdminExcelAddBinding> {

    int ADD_DONE = 20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_excel_add);
        setBinding(R.layout.activity_admin_excel_add);

        binding.checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ManageStatus manageStatus = new ManageStatus(
                        binding.firstduesEdittext.getText().toString(),
                        binding.secondduesEdittext.getText().toString(),
                        binding.openingEdittext.getText().toString(),
                        binding.closingEdittext.getText().toString()
                );
                People people = new People(
                        binding.stateEdittext.getText().toString(),
                        binding.generationEdittext.getText().toString(),
                        binding.nameEdittext.getText().toString(),
                        binding.departmentEdittext.getText().toString(),
                        binding.studentidEdittext.getText().toString(),
                        binding.phonenumberEdittext.getText().toString(),
                        binding.contactEdittext.getText().toString(),
                        manageStatus);
                Intent intent = getIntent();
                intent.putExtra("peopleInfo", people);
                setResult(ADD_DONE, intent);
                finish();
            }
        });
    }
}