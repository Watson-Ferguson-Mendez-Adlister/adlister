package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;

import java.util.List;

/////Ads Interface/////

public interface Ads {
    // get a list of all the ads
    List<Ad> all();
    // insert a new ad and return the new ad's id
    Long insert(Ad ad);

    void update(Ad ad);

    Ad find(Long id);

    List<Ad> getAdsByUserId(Long userId);

    void deleteAdById(Long adId);

    void deleteAdsByUserId(Long userId);

    List<Ad> search(String term);
}
