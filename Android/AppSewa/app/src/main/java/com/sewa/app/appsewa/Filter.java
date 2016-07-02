package com.sewa.app.appsewa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sushant. Custom Filter Class to search for sites based upon their names or place
 */
public class Filter {

    private List<Post> siteList=new ArrayList<Post>();
    private String keyword;

    public Filter(List<Post> siteList, String keyword){
        super();
        this.siteList=siteList;
        this.keyword=keyword;
    }

    public List<Post> filter(){
        List<Post> filteredList=new ArrayList<Post>();
        for (int i=0; i< siteList.size();i++) {
            Post newPost=siteList.get(i);
            if  (newPost.getOrg_name().toLowerCase().contains(keyword.toLowerCase()) || newPost.getCause().toLowerCase().contains(keyword.toLowerCase()) || newPost.getDisaster_location().toLowerCase().contains(keyword.toLowerCase())){
                filteredList.add(newPost);
            }
        }
        return filteredList;
    }
}
