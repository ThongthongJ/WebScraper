package com.example.websrcapper.transform;

import com.example.websrcapper.entity.TrTransactionEntity;
import com.example.websrcapper.model.HistoricalTradingModel;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class Transform {
    public static List<TrTransactionEntity> transformModelToEntityList(HistoricalTradingModel[] historicalTradingModels){
        List<TrTransactionEntity> trTransactionEntitiesList = new ArrayList<>();
        for ( HistoricalTradingModel x:historicalTradingModels ) {
            TrTransactionEntity trTransactionEntity = new TrTransactionEntity();
            trTransactionEntity.setSymbol(x.getSymbol());
            trTransactionEntity.setTransactionDate(x.getDate());
            trTransactionEntity.setOpenPrice(x.getOpen());
            trTransactionEntity.setMaxPrice(x.getHigh());
            trTransactionEntity.setMinPrice(x.getLow());
            trTransactionEntity.setClosePrice(x.getClose());
            trTransactionEntity.setChangeRatio(x.getChange());
            trTransactionEntity.setNoOfStock(null);
            trTransactionEntity.setVolume(x.getTotalVolume());
            trTransactionEntity.setReason(null);
            trTransactionEntity.setStatus(null);
            trTransactionEntity.setBatchId(null);

            trTransactionEntitiesList.add(trTransactionEntity);
        }
        return trTransactionEntitiesList;
    }
}
