package com.example.sharingbookshelf.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.sharingbookshelf.Activities.BarcodeActivity;
import com.example.sharingbookshelf.Activities.SelfAddBookPopupActivity;
import com.example.sharingbookshelf.R;

public class NoEmptyShelfFragment extends Fragment {

    private static final int BARCODE_ACTIVITY = 10000;
    private static final int ADDSELF_ACTIVITY = 10001;
    private static final int BOOKPOPUP_ACTIVITY = 10002;
    private Button btn_addBook;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_no_empty_shelf, container, false);

        btn_addBook = v.findViewById(R.id.btn_AddBook);
        btn_addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final PopupMenu popupMenu = new PopupMenu(getActivity().getApplicationContext(), view);
                getActivity().getMenuInflater().inflate(R.menu.menu_register_book, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.bookFind_barcode) { // 메뉴 홈페이지 만들고 intent로 수정
                            Intent intent = new Intent(getActivity(), BarcodeActivity.class);
                            getActivity().startActivityForResult(intent, BARCODE_ACTIVITY);
                        } else if (menuItem.getItemId() == R.id.bookFind_ISBN) {
                            Intent intent = new Intent(getActivity(), SelfAddBookPopupActivity.class);
                            getActivity().startActivityForResult(intent, ADDSELF_ACTIVITY);
                        } else if (menuItem.getItemId() == R.id.bookFind_direct) {
                            Toast.makeText(getActivity(), "수동으로 정보 입력", Toast.LENGTH_SHORT).show();
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        return v;
    }
}