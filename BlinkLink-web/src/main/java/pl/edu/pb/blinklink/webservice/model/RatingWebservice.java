package pl.edu.pb.blinklink.webservice.model;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import pl.edu.pb.blinklink.model.Rate;
import pl.edu.pb.blinklink.model.Rating;

@XmlRootElement
public class RatingWebservice {
	
	private List<RateWebservice> rates = new LinkedList<RateWebservice>();
	
	private double rate;
	
	private int ratesSize;
	
	public RatingWebservice() {}
	
	public RatingWebservice(Rating rating) {
		this.rate = rating.recalculateRate();
		this.ratesSize = rating.getRatesSize();
		this.rates = transformToRateWebserviceObjects(rating.getRates());
	}
	
	private List<RateWebservice> transformToRateWebserviceObjects(List<Rate> rates) {
		List<RateWebservice> result = new LinkedList<RateWebservice>();
		for(Rate rate : rates)
			result.add(new RateWebservice(rate));
		return result;
	}
	
	
	@XmlElement(name="rates")
	public Collection<RateWebservice> getRates() {
		return rates;
	}
	
	@XmlElement(name="rate")
	public double getRate() {
		return rate;
	}
	
	@XmlElement(name="ratesSize")
	public int getRatesSize() {
		return ratesSize;
	}
}
