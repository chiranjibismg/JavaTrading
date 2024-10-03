package com.Chiranjibi.JavaTrading.service;

import com.Chiranjibi.JavaTrading.models.*;

public interface WatchlistService {



    Watchlist findUserWatchlist(Long userId) throws Exception;

    Watchlist createWatchList(User user);

    Watchlist findById(Long id) throws Exception;

    Coin addItemToWatchlist(Coin coin, User user) throws Exception;
}
