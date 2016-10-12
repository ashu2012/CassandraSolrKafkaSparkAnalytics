package atppath.model;

import java.util.UUID;

public class Product {

	private String UUID;
	
    private String id;

    private String city;

    private String country;

    private String zipCode;

    private String type;
    
    private boolean hasAirCondition;
    
    private boolean hasGarden;
    
    private boolean hasPool;
    
    private boolean isCloseToBeach;
    
    private double dailyPrice;
    
    private String currency;
    
    private int roomsNumber;

    Product() {
    }

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isHasAirCondition() {
		return hasAirCondition;
	}

	public void setHasAirCondition(boolean hasAirCondition) {
		this.hasAirCondition = hasAirCondition;
	}

	public boolean isHasGarden() {
		return hasGarden;
	}

	public void setHasGarden(boolean hasGarden) {
		this.hasGarden = hasGarden;
	}

	public boolean isHasPool() {
		return hasPool;
	}

	public void setHasPool(boolean hasPool) {
		this.hasPool = hasPool;
	}

	public boolean isCloseToBeach() {
		return isCloseToBeach;
	}

	public void setCloseToBeach(boolean isCloseToBeach) {
		this.isCloseToBeach = isCloseToBeach;
	}

	public double getDailyPrice() {
		return dailyPrice;
	}

	public void setDailyPrice(double dailyPrice) {
		this.dailyPrice = dailyPrice;
	}

	public int getRoomsNumber() {
		return roomsNumber;
	}

	public void setRoomsNumber(int roomsNumber) {
		this.roomsNumber = roomsNumber;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
    
   
}