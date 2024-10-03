package com.Chiranjibi.JavaTrading.repository;

import com.Chiranjibi.JavaTrading.models.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssetRepository extends JpaRepository<Asset,Long> {

    public List<Asset> findByUserId(Long userId);

    Asset findByUserIdAndCoinId(Long userId, String coinId);

    Asset findByIdAndUserId(Long assetId, Long userId);
}
