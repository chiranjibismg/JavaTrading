package com.Chiranjibi.JavaTrading.service;

import com.Chiranjibi.JavaTrading.models.Asset;
import com.Chiranjibi.JavaTrading.models.Coin;
import com.Chiranjibi.JavaTrading.models.User;

import java.util.List;

public interface AssetService {

    Asset createAsset(User user, Coin coin, double quantity);

    Asset getAssetById(Long assetId) throws Exception;

    Asset getAssetByUserAndId(Long userId,Long assetId);

    List<Asset> getUsersAssets(Long userId);

    Asset updateAsset(Long assetId,double quantity) throws Exception;

    Asset findAssetByUserIdAndCoinId(Long userId,String coinId) throws Exception;

    void deleteAsset(Long assetId);
}
