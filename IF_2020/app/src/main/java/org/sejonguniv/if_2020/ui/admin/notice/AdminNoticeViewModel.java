package org.sejonguniv.if_2020.ui.admin.notice;

import androidx.lifecycle.MutableLiveData;

import org.sejonguniv.if_2020.base.BaseViewModel;
import org.sejonguniv.if_2020.model.Notice;
import org.sejonguniv.if_2020.repository.AdminRepository;

import java.util.ArrayList;

public class AdminNoticeViewModel extends BaseViewModel {

    MutableLiveData<ArrayList<Notice>> noticeList = new MutableLiveData<>();
    AdminRepository adminRepository = AdminRepository.getInstance();

    public void getNoticeList() {
        compositeDisposable.add(adminRepository.getNotice().subscribe(
                notices -> {
                    if (notices.isEmpty()) {
                        noticeList.postValue(setEmptyList());
                    } else {
                        noticeList.postValue(notices);
                    }
                },
                error -> noticeList.postValue(setErrorList())
        ));
    }

    public void deleteNotice(int listNumber) {
        compositeDisposable.add(adminRepository.deleteNotice(listNumber).subscribe());
    }

    public void editNotice(int listNumber, Notice notice) {
        compositeDisposable.add(adminRepository.editNotice(listNumber, notice).subscribe());
    }

    public void saveNotice(Notice notice) {
        compositeDisposable.add(adminRepository.saveNotice(notice).subscribe());
    }

    private ArrayList<Notice> setErrorList() {
        Notice notice = new Notice("오류", "데이터를 불러올 수 없습니다. 다시 시도해주세요");
        ArrayList<Notice> errorList = new ArrayList<>();
        errorList.add(notice);
        return errorList;
    }

    private ArrayList<Notice> setEmptyList() {
        Notice notice = new Notice("공지사항이 없습니다.", "");
        ArrayList<Notice> emptyList = new ArrayList<>();
        emptyList.add(notice);
        return emptyList;
    }
}
