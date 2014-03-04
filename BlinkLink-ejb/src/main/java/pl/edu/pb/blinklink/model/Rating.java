package pl.edu.pb.blinklink.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Class for representing rating of one link.
 * @author dawid
 */
@Entity
public class Rating implements Serializable {
    @Id
    private Long id;
    
    @OneToMany
    List<Rate> rates;
    
    protected double rate;
    
    protected int ratesSize;
    
    //functionalities
    
    /**
     * Function simple recalculates actual rate summary.
     * Even rating wasn't changed past last use, 
     * there will be recalculated values.
     * It might cause some problems with performance.
     * @return double new rating
     */
    public double recalculateRate()
    {
        ratesSize = getRates().size();
        double result = 0.0;
        for(Rate r: getRates())
            result += r.getRate();
        result /= ratesSize;
        this.rate = result;
        return result;
    }
    
    //getters and setters
    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getRatesSize() {
        return ratesSize;
    }

    public void setRatesSize(int ratesSize) {
        this.ratesSize = ratesSize;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
