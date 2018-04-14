package com.example.bookstore2.demo.DAO;

import com.example.bookstore2.demo.Entity.SearchOptions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SearchOptionsDAO {
    private static List<SearchOptions> OPTIONS = new ArrayList<SearchOptions>();

    static{
        initData();
    }

    private static void initData(){
        SearchOptions category = new SearchOptions(1, "Category");
        SearchOptions title = new SearchOptions(2, "Title");
        SearchOptions author = new SearchOptions(3, "Author");

        OPTIONS.add(category);
        OPTIONS.add(title);
        OPTIONS.add(author);

    }

    public static List<SearchOptions> getOptions() {
        return OPTIONS;
    }
}
