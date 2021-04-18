package com.example.sharingbookshelf.Activities;

import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sharingbookshelf.R;

public class NeighborShelfActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbor_shelf);
        RecyclerView view = findViewById(R.id.);

        view.setHasFixedSize(true);
    }
}

//이웃 정보 - 현재 사용자의 자녀의 나이 +2 ~ -2 계산, 그 나이대 자녀를 가진 부모만 돌면서 가져오는데
//가장 최근에 책장에 책을 등록한 부모순으로 나오게
//검색해서 특정동네 볼 수 있게 -> 강남같이 잘사는 동네 자식들이 뭘 읽을지 궁금할테니까?
//클릭하면 이웃의 책장으로 넘어가게