package com.Chiranjibi.JavaTrading.service;

import com.Chiranjibi.JavaTrading.models.Asset;
import com.Chiranjibi.JavaTrading.models.Coin;
import com.Chiranjibi.JavaTrading.models.User;
import com.Chiranjibi.JavaTrading.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AssetServiceImpl implements AssetService {


    @Autowired
    private AssetRepository assetRepository;

    @Override
    public Asset createAsset(User user, Coin coin, double quantity) {
        Asset asset = new Asset();

        asset.setQuantity(quantity);
        asset.setBuyPrice(coin.getCurrentPrice());
        asset.setCoin(coin);
        asset.setUser(user);

        return assetRepository.save(asset);
    }

    @Override
    public Asset getAssetById(Long assetId) throws Exception {
        return assetRepository.findById(assetId)
                .orElseThrow(() -> new Exception("Asset not found"));
    }

    @Override
    public Asset getAssetByUserAndId(Long userId, Long assetId) {
        return assetRepository.findByIdAndUserId(assetId,userId);
    }

    @Override
    public List<Asset> getUsersAssets(Long userId) {
        return assetRepository.findByUserId(userId);
    }

    @Override
    public Asset updateAsset(Long assetId, double quantity) throws Exception {
        Asset oldAsset=getAssetById(assetId);
        if(oldAsset==null){
            throw new Exception("Asset not found...");
        }
        oldAsset.setQuantity(quantity+ oldAsset.getQuantity());

        return assetRepository.save(oldAsset);
    }

    @Override
    public Asset findAssetByUserIdAndCoinId(Long userId, String coinId) throws Exception {
        return assetRepository.findByUserIdAndCoinId(userId,coinId);
    }

    @Override
    public void deleteAsset(Long assetId) {

        assetRepository.deleteById(assetId);

    }
}
