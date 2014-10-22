package pl.edu.pb.blinklink.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Class for representing rating of one link.
 * @author dawid
 */
@Entity
public class Rating implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @OneToMany(fetch = FetchType.EAGER)
    List<Rate> rates = new LinkedList<Rate>();
    
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 89 * hash + (this.rates != null ? this.rates.hashCode() : 0);
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.rate) ^ (Double.doubleToLongBits(this.rate) >>> 32));
        hash = 89 * hash + this.ratesSize;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rating other = (Rating) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.rates != other.rates && (this.rates == null || !this.rates.equals(other.rates))) {
            return false;
        }
        if (Double.doubleToLongBits(this.rate) != Double.doubleToLongBits(other.rate)) {
            return false;
        }
        if (this.ratesSize != other.ratesSize) {
            return false;
        }
        return true;
    }
    
}
