package com.example.sharingbookshelf.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.sharingbookshelf.Activities.HomeActivity;
import com.example.sharingbookshelf.Activities.MainActivity;
import com.example.sharingbookshelf.Adapters.MyBookshelfAdapter;
import com.example.sharingbookshelf.HttpRequest.RetrofitClient;
import com.example.sharingbookshelf.HttpRequest.RetrofitServiceApi;
import com.example.sharingbookshelf.Models.BookApiResponse;
import com.example.sharingbookshelf.Models.CommonResponse;
import com.example.sharingbookshelf.R;
import com.kyleduo.switchbutton.SwitchButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class BookInfoPopupFragment extends DialogFragment {

    private static BookInfoPopupFragment bookInfoPopupFragment = null;
    private ImageView iv_thumbNail;
    private TextView tv_ISBN, tv_title, tv_authors, tv_publisher, tv_category;
    private AppCompatButton btn_addBook;
    private AppCompatButton btn_back;
    private SwitchButton switchButton;

    boolean isSetCategory = false;
    private HashMap<String, Object> parameters = new HashMap<>();
    private BookApiResponse.Document book;

    public static BookInfoPopupFragment getInstance() {
        if (bookInfoPopupFragment == null) {
            bookInfoPopupFragment = new BookInfoPopupFragment();
        }
        return bookInfoPopupFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bookinfo_popup, container, false);

        iv_thumbNail = v.findViewById(R.id.iv_thumbnail);
        tv_ISBN = v.findViewById(R.id.tv_ISBN);
        tv_title = v.findViewById(R.id.tv_title);
        tv_authors = v.findViewById(R.id.tv_authors);
        tv_publisher = v.findViewById(R.id.tv_publisher);
        btn_addBook = v.findViewById(R.id.btn_addBook);
        btn_back = v.findViewById(R.id.btn_back);
        switchButton = v.findViewById(R.id.switch_category);
        tv_category = v.findViewById(R.id.tv_category);

        book = getBook();
        setView(book);
        thumbnailClickListener();
        categorySwitchListener();

        btn_addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSetCategory) {
                    RetrofitServiceApi retrofitServiceApi = RetrofitClient.createService(RetrofitServiceApi.class, MainActivity.getJWT());
                    Call<CommonResponse> call = retrofitServiceApi.addBookInShelf(MainActivity.getMemId(), parameters);
                    call.enqueue(new Callback<CommonResponse>() {
                        @Override
                        public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                            Log.d(MainActivity.MAIN_TAG, response.body().getCode() + " : " + response.body().getMsg());
                            getDialog().dismiss();

                            //액티비티 전체 말고 NoEmptyFragment 만 reload 되는 방법 찾기
                            Intent intent = new Intent(getActivity(), HomeActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        }

                        @Override
                        public void onFailure(Call<CommonResponse> call, Throwable t) {

                        }
                    });

                } else {
                    Toast.makeText(getContext(), "장르를 선택한 뒤에 추가가 가능합니다!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    switchButton.setChecked(true);
                    isSetCategory = true;
                    String category = extras.getString("category");
                    int categoryNum = extras.getInt("categoryNum");
                    parameters.put("category", categoryNum);
                    tv_category.setText(category);
                    tv_category.setTextColor(Color.parseColor("#000000"));
                }
            }
        }
    }

    private void categorySwitchListener() {
        if (switchButton != null) {
            switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        Log.d(MainActivity.MAIN_TAG, "switch button TEST - 활성화");
                        isSetCategory = false;
                        SelectCategoryFragment selectCategoryFragment = new SelectCategoryFragment();
                        getFragmentManager().executePendingTransactions();
                        Fragment targetFragment = getFragmentManager().findFragmentByTag("BookInfoPopupFragment");
                        selectCategoryFragment.setTargetFragment(targetFragment, 2);
                        selectCategoryFragment.show(getFragmentManager(), "SelectCategoryFragment");
                        getFragmentManager().executePendingTransactions();
                        selectCategoryFragment.getDialog().setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                if (!isSetCategory) {
                                    switchButton.setChecked(false);
                                }
                            }
                        });
                    } else {
                        isSetCategory = false;
                        switchButton.setChecked(false);
                        tv_category.setText("장르를 설정해주세요!");
                        tv_category.setTextColor(-1979711488);
                    }
                }
            });
        }
    }

    private void thumbnailClickListener() {
        iv_thumbNail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("contents", book.getContents());
                BookContentsPopupFragment bookContentsPopupFragment = new BookContentsPopupFragment();
                bookContentsPopupFragment.setArguments(bundle);
                bookContentsPopupFragment.show(getActivity().getSupportFragmentManager(), "BookContentsPopupFragment");

            }
        });
    }

    private BookApiResponse.Document getBook() {
        Bundle bundle = getArguments();
        ArrayList<BookApiResponse.Document> documentList = (ArrayList<BookApiResponse.Document>) bundle.getSerializable("documentList");
        //BookApiResponse.Meta meta = (BookApiResponse.Meta) intent.getSerializableExtra("meta");
        return documentList.get(0);
    }

    private void setView(BookApiResponse.Document book) {
        String isbn = book.getIsbn();
        String title = book.getTitle();
        List<String> authors = book.getAuthors();
        String publisher = book.getPublisher();
        String thumbnail = book.getThumbnail();

        parameters.put("ISBN", isbn);
        parameters.put("title", title);
        parameters.put("author", authors.get(0));
        parameters.put("publisher", publisher);
        parameters.put("thumbnail", thumbnail);

        Glide.with(this).load(thumbnail).into(iv_thumbNail);
        tv_ISBN.setText(isbn);
        tv_title.setText(title);
        tv_authors.setText(authors.get(0));
        tv_publisher.setText(publisher);

    }

    @Override
    public void onResume() {
        super.onResume();

        try {
            Window window = getDialog().getWindow();
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}