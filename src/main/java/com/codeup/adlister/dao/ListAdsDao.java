package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;

import java.util.ArrayList;
import java.util.List;

/////UNUSED/////

public class ListAdsDao implements Ads {
    private List<Ad> ads;

    public List<Ad> all() {
        if (ads == null) {
            ads = generateAds();
        }
        return ads;
    }

    public Long insert(Ad ad) {
        // make sure we have ads
        if (ads == null) {
            ads = generateAds();
        }
        // we'll assign an "id" here based on the size of the ads list
        // really the dao would handle this
        ad.setId((long) ads.size());
        ads.add(ad);
        return ad.getId();
    }

    @Override
    public void update(Ad ad) {

    }

    @Override
    public Ad find(Long id) {
        return null;
    }

    @Override
    public List<Ad> getAdsByUserId(Long userId) {
        return null;
    }

    @Override
    public void deleteAdById(Long adId) {

    }

    @Override
    public void deleteAdsByUserId(Long userId) {

    }

    @Override
    public List<Ad> search(String term) {
        return null;
    }

    private List<Ad> generateAds() {
        List<Ad> ads = new ArrayList<>();
        ads.add(new Ad(
                1,
                "playstation for sale",
                "This is a slightly used playstation",
                "for sale"
        ));
        return ads;
    }
}
