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
import com.example.redditcloneandroid.adapters.SortingPostsAdapter;
import com.example.redditcloneandroid.model.SortingPosts;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivitySortingPosts extends AppCompatActivity {

    public static ArrayList<SortingPosts> postList = new ArrayList<SortingPosts>();

    private ListView listView;
    private Button sortButton;
    private Button filterButton;
    private LinearLayout filterView1;
    private LinearLayout filterView2;
    private LinearLayout sortView;

    boolean sortHidden = true;
    boolean filterHidden = true;

    private Button circleButton, squareButton, rectangleButton, triangleButton, octagonButton, allButton;
    private Button idAscButton, idDescButton, titleAscButton, titleDescButton;

    private ArrayList<String> selectedFilters = new ArrayList<String>();
    private String currentSearchText = "";
    private SearchView searchView;

    private int white, darkGray, red;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sorting_posts);

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
        lookUnselected(titleAscButton);
        lookUnselected(titleDescButton);
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
        titleAscButton = (Button) findViewById(R.id.titleAsc);
        titleDescButton = (Button) findViewById(R.id.titleDesc);
    }

    private void initSearchWidgets() {
        searchView = (SearchView) findViewById(R.id.postListSearchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                currentSearchText = s;
                ArrayList<SortingPosts> filteredShapes = new ArrayList<SortingPosts>();

                for(SortingPosts sortingPost : postList) {
                    if(sortingPost.getTitle().toLowerCase().contains(s.toLowerCase())) {
                        if(selectedFilters.contains("all")) {
                            filteredShapes.add(sortingPost);
                        }
                    }
                    else {
                        for(String filter : selectedFilters) {
                            if (sortingPost.getTitle().toLowerCase().contains(filter)) {
                                filteredShapes.add(sortingPost);
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
        SortingPosts circle = new SortingPosts("0", "Objava1");
        postList.add(circle);

        SortingPosts triangle = new SortingPosts("1","Objava2");
        postList.add(triangle);

        SortingPosts square = new SortingPosts("2","Objava3");
        postList.add(square);

        SortingPosts rectangle = new SortingPosts("3","Objava4");
        postList.add(rectangle);

        SortingPosts octagon = new SortingPosts("4","Objava5");
        postList.add(octagon);

    }

    private void setUpList()
    {
        listView = (ListView) findViewById(R.id.postListView);

        setAdapter(postList);
    }

    private void setUpOnclickListener()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                SortingPosts selectShape = (SortingPosts) (listView.getItemAtPosition(position));
                Intent showDetail = new Intent(getApplicationContext(), DetailActivitySortingPosts.class);
                showDetail.putExtra("id",selectShape.getId());
                startActivity(showDetail);
            }
        });

    }

    private void filterList(String status) {

        if(status != null && !selectedFilters.contains(status)) {
            selectedFilters.add(status);
        }

        ArrayList<SortingPosts> filteredShapes = new ArrayList<SortingPosts>();

        for(SortingPosts sortingPost : postList) {

            for(String filter : selectedFilters) {
                if(sortingPost.getTitle().toLowerCase().contains(filter)) {
                    if (currentSearchText == "") {
                        filteredShapes.add(sortingPost);
                    }
                    else {
                        if (sortingPost.getTitle().toLowerCase().contains(currentSearchText.toLowerCase())) {
                            filteredShapes.add(sortingPost);
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

        setAdapter(postList);
    }

    public void triangleFilterTapped(View view) {
        filterList("objava1");
        selectFilterUI(triangleButton);
    }

    public void squareFilterTapped(View view) {
        filterList("objava2");
        selectFilterUI(squareButton);
    }

    public void octagonFilterTapped(View view) {
        filterList("objava3");
        selectFilterUI(octagonButton);
    }

    public void rectangleFilterTapped(View view) {
        filterList("objava4");
        selectFilterUI(rectangleButton);
    }

    public void circleFilterTapped(View view) {
        filterList("objava5");
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
        Collections.sort(postList, SortingPosts.idAscending);
        chechForFilter();
        unselectAllSortButtons();
        lookSelected(idAscButton);
    }

    public void idDESCTapped(View view) {
        Collections.sort(postList, SortingPosts.idAscending);
        Collections.reverse(postList);
        chechForFilter();
        unselectAllSortButtons();
        lookSelected(idDescButton);
    }

    public void titleASCTapped(View view) {
        Collections.sort(postList, SortingPosts.titleAscending);
        chechForFilter();
        unselectAllSortButtons();
        lookSelected(titleAscButton);
    }

    public void titleDESCTapped(View view) {
        Collections.sort(postList, SortingPosts.titleAscending);
        Collections.reverse(postList);
        chechForFilter();
        unselectAllSortButtons();
        lookSelected(titleDescButton);
    }

    private void chechForFilter() {
        if(selectedFilters.contains("all")) {
            if(currentSearchText.equals("")) {
                setAdapter(postList);
            }
            else {
                ArrayList<SortingPosts> filteredShapes = new ArrayList<SortingPosts>();

                for(SortingPosts sortingPost : postList) {
                    if(sortingPost.getTitle().toLowerCase().contains(currentSearchText)) {
                        filteredShapes.add(sortingPost);
                    }
                }
                setAdapter(filteredShapes);
            }
        }
        else {
            filterList(null);
        }
    }

    private void setAdapter(ArrayList<SortingPosts> shapeList) {
        SortingPostsAdapter adapter = new SortingPostsAdapter(getApplicationContext(), 0, shapeList);
        listView.setAdapter(adapter);
    }
}
