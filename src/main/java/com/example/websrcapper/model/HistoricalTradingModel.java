package com.example.websrcapper.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class HistoricalTradingModel {
    private Date date;
    private String symbol;
    private Double prior;
    private Double open;
    private Double high;
    private Double low;
    private Double average;
    private Double close;
    private Double change;
    private Double percentChange;
    private Double totalVolume;
    private Double totalValue;
    private Double pe;
    private Double pbv;
    private Double bookValuePerShare;
    private Double dividendYield;
    private Double marketCap;
    private Double listedShare;
    private Double par;
    private Date financialDate;
    private Double nav;
    private Double marketIndex;
    private Double marketPercentChange;


}
