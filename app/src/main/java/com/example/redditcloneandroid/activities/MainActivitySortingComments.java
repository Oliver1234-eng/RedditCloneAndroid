package com.example.redditcloneandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.redditcloneandroid.R;
import com.example.redditcloneandroid.adapters.SortingCommentsAdapter;
import com.example.redditcloneandroid.model.SortingComments;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivitySortingComments extends AppCompatActivity {

    public static ArrayList<SortingComments> commentList = new ArrayList<SortingComments>();

    private ListView listView;
    private Button sortButton;
    private Button filterButton;
    private LinearLayout filterView1;
    private LinearLayout filterView2;
    private LinearLayout sortView;

    boolean sortHidden = true;
    boolean filterHidden = true;

    private Button circleButton, squareButton, rectangleButton, triangleButton, octagonButton, allButton;
    private Button idAscButton, idDescButton, replyAscButton, replyDescButton;

    private ArrayList<String> selectedFilters = new ArrayList<String>();
    private String currentSearchText = "";
    private SearchView searchView;

    private int white, darkGray, red;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sorting_comments);

        initSearchWidgets();
        initWidgets();
        setupData();
        setUpList();
        setUpOnclickListener();
        hideFilter();
        hideSort();
        initColors();
        lookSelected(idAscButton);
        lookSelected(allButton);
        selectedFilters.add("all");
    }

    private void initColors() {
        white = ContextCompat.getColor(getApplicationContext(), R.color.white);
        red = ContextCompat.getColor(getApplicationContext(), R.color.red);
        darkGray = ContextCompat.getColor(getApplicationContext(), R.color.darkerGray);
    }

    private void unselectAllSortButtons() {
        lookUnselected(idAscButton);
        lookUnselected(idDescButton);
        lookUnselected(replyAscButton);
        lookUnselected(replyDescButton);
    }

    private void unselectAllFilterButtons() {
        lookUnselected(allButton);
        lookUnselected(circleButton);
        lookUnselected(rectangleButton);
        lookUnselected(octagonButton);
        lookUnselected(triangleButton);
        lookUnselected(squareButton);
    }

    private void lookSelected(Button parsedButton) {
        parsedButton.setTextColor(white);
        parsedButton.setBackgroundColor(red);
    }

    private void lookUnselected(Button parsedButton) {
        parsedButton.setTextColor(red);
        parsedButton.setBackgroundColor(darkGray);
    }

    private void initWidgets() {
        sortButton = (Button) findViewById(R.id.sortButton);
        filterButton = (Button) findViewById(R.id.filterButton);
        filterView1 = (LinearLayout) findViewById(R.id.filterTabsLayout);
        filterView2 = (LinearLayout) findViewById(R.id.filterTabsLayout2);
        sortView = (LinearLayout) findViewById(R.id.sortTabsLayout2);

        circleButton = (Button) findViewById(R.id.circleFilter);
        squareButton = (Button) findViewById(R.id.squareFilter);
        rectangleButton = (Button) findViewById(R.id.rectangleFilter);
        triangleButton = (Button) findViewById(R.id.triangleFilter);
        octagonButton = (Button) findViewById(R.id.octagonFilter);
        allButton = (Button) findViewById(R.id.allFilter);

        idAscButton = (Button) findViewById(R.id.idAsc);
        idDescButton = (Button) findViewById(R.id.idDesc);
        replyAscButton = (Button) findViewById(R.id.replyAsc);
        replyDescButton = (Button) findViewById(R.id.replyDesc);
    }

    private void initSearchWidgets() {
        searchView = (SearchView) findViewById(R.id.commentListSearchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                currentSearchText = s;
                ArrayList<SortingComments> filteredShapes = new ArrayList<SortingComments>();

                for(SortingComments sortingComment : commentList) {
                    if(sortingComment.getReply().toLowerCase().contains(s.toLowerCase())) {
                        if(selectedFilters.contains("all")) {
                            filteredShapes.add(sortingComment);
                        }
                    }
                    else {
                        for(String filter : selectedFilters) {
                            if (sortingComment.getReply().toLowerCase().contains(filter)) {
                                filteredShapes.add(sortingComment);
                            }
                        }
                    }
                }

                setAdapter(filteredShapes);

                return false;
            }
        });
    }

    private void setupData()
    {
        SortingComments circle = new SortingComments("0", "Komentar1");
        commentList.add(circle);

        SortingComments triangle = new SortingComments("1","Komentar2");
        commentList.add(triangle);

        SortingComments square = new SortingComments("2","Komentar3");
        commentList.add(square);

        SortingComments rectangle = new SortingComments("3","Komentar4");
        commentList.add(rectangle);

        SortingComments octagon = new SortingComments("4","Komentar5");
        commentList.add(octagon);

    }

    private void setUpList()
    {
        listView = (ListView) findViewById(R.id.commentListView);

        setAdapter(commentList);
    }

    private void setUpOnclickListener()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                SortingComments selectShape = (SortingComments) (listView.getItemAtPosition(position));
                Intent showDetail = new Intent(getApplicationContext(), DetailActivitySortingComments.class);
                showDetail.putExtra("id",selectShape.getId());
                startActivity(showDetail);
            }
        });

    }

    private void filterList(String status) {

        if(status != null && !selectedFilters.contains(status)) {
            selectedFilters.add(status);
        }

        ArrayList<SortingComments> filteredShapes = new ArrayList<SortingComments>();

        for(SortingComments sortingComment : commentList) {

            for(String filter : selectedFilters) {
                if(sortingComment.getReply().toLowerCase().contains(filter)) {
                    if (currentSearchText == "") {
                        filteredShapes.add(sortingComment);
                    }
                    else {
                        if (sortingComment.getReply().toLowerCase().contains(currentSearchText.toLowerCase())) {
                            filteredShapes.add(sortingComment);
                        }
                    }
                }
            }
        }

        setAdapter(filteredShapes);

    }

    public void allFilterTapped(View view) {
        selectedFilters.clear();
        selectedFilters.add("all");

        unselectAllFilterButtons();
        lookSelected(allButton);

        setAdapter(commentList);
    }

    public void triangleFilterTapped(View view) {
        filterList("odgovor1");
        selectFilterUI(triangleButton);
    }

    public void squareFilterTapped(View view) {
        filterList("odgovor2");
        selectFilterUI(squareButton);
    }

    public void octagonFilterTapped(View view) {
        filterList("odgovor3");
        selectFilterUI(octagonButton);
    }

    public void rectangleFilterTapped(View view) {
        filterList("odgovor4");
        selectFilterUI(rectangleButton);
    }

    public void circleFilterTapped(View view) {
        filterList("odgovor5");
        selectFilterUI(circleButton);
    }

    private void selectFilterUI(Button button) {
        lookSelected(button);
        lookUnselected(allButton);
        selectedFilters.remove("all");
    }

    public void showFilterTapped(View view) {

        if(filterHidden == true) {
            filterHidden = false;
            showFilter();
        }
        else {
            filterHidden = true;
            hideFilter();
        }
    }

    public void showSortTapped(View view) {

        if(sortHidden == true) {
            sortHidden = false;
            showSort();
        }
        else {
            sortHidden = true;
            hideSort();
        }
    }

    private void hideFilter() {
        searchView.setVisibility(View.GONE);
        filterView1.setVisibility(View.GONE);
        filterView2.setVisibility(View.GONE);
        filterButton.setText("FILTER");
    }

    private void showFilter() {
        searchView.setVisibility(View.VISIBLE);
        filterView1.setVisibility(View.VISIBLE);
        filterView2.setVisibility(View.VISIBLE);
        filterButton.setText("HIDE");
    }

    private void hideSort() {
        sortView.setVisibility(View.GONE);
        sortButton.setText("SORT");
    }

    private void showSort() {
        sortView.setVisibility(View.VISIBLE);
        sortButton.setText("HIDE");
    }

    public void idASCTapped(View view) {
        Collections.sort(commentList, SortingComments.idAscending);
        chechForFilter();
        unselectAllSortButtons();
        lookSelected(idAscButton);
    }

    public void idDESCTapped(View view) {
        Collections.sort(commentList, SortingComments.idAscending);
        Collections.reverse(commentList);
        chechForFilter();
        unselectAllSortButtons();
        lookSelected(idDescButton);
    }

    public void replyASCTapped(View view) {
        Collections.sort(commentList, SortingComments.replyAscending);
        chechForFilter();
        unselectAllSortButtons();
        lookSelected(replyAscButton);
    }

    public void replyDESCTapped(View view) {
        Collections.sort(commentList, SortingComments.replyAscending);
        Collections.reverse(commentList);
        chechForFilter();
        unselectAllSortButtons();
        lookSelected(replyDescButton);
    }

    private void chechForFilter() {
        if(selectedFilters.contains("all")) {
            if(currentSearchText.equals("")) {
                setAdapter(commentList);
            }
            else {
                ArrayList<SortingComments> filteredShapes = new ArrayList<SortingComments>();

                for(SortingComments sortingComment : commentList) {
                    if(sortingComment.getReply().toLowerCase().contains(currentSearchText)) {
                        filteredShapes.add(sortingComment);
                    }
                }
                setAdapter(filteredShapes);
            }
        }
        else {
            filterList(null);
        }
    }

    private void setAdapter(ArrayList<SortingComments> shapeList) {
        SortingCommentsAdapter adapter = new SortingCommentsAdapter(getApplicationContext(), 0, shapeList);
        listView.setAdapter(adapter);
    }
}
