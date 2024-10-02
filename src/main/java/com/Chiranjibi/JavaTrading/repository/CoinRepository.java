package com.Chiranjibi.JavaTrading.repository;

import com.Chiranjibi.JavaTrading.models.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinRepository  extends JpaRepository<Coin,String> {



}
