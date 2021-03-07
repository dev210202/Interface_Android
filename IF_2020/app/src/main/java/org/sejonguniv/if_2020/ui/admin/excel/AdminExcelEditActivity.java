package org.sejonguniv.if_2020.ui.admin.excel;

import android.content.Intent;
import android.os.Bundle;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseActivity;
import org.sejonguniv.if_2020.databinding.ActivityAdminExcelEditBinding;
import org.sejonguniv.if_2020.model.ManageStatus;
import org.sejonguniv.if_2020.model.People;

public class AdminExcelEditActivity extends BaseActivity<ActivityAdminExcelEditBinding> {

    int EDIT_DONE = 40;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_excel_edit);
        setBinding(R.layout.activity_admin_excel_edit);

        People getPeople = (People) getIntent().getSerializableExtra("editPeople");

        binding.setPeople(getPeople);
        binding.checkButton.setOnClickListener(v -> {
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
            people.setId(getPeople.getId());
            Intent intent = getIntent();
            intent.putExtra("editResult", people);
            intent.putExtra("id", getIntent().getIntExtra("id", 0));
            setResult(EDIT_DONE, intent);
            finish();
        });
    }
}