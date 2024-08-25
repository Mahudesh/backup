	package com.example.demo.Modal;
	
	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	
	@Entity
	public class City {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer cityId;
	    private String cityName;
	 
	    public City() {
	    }
	
	
	    public City(Integer cityId) {
	        this.cityId = cityId;
	    }
		public Integer getCityId() {
			return cityId;
		}
		public void setCityId(Integer cityId) {
			this.cityId = cityId;
		}
		public String getCityName() {	
			return cityName;
		}
		public void setCityName(String cityName) {
			this.cityName = cityName;
		}
	    
	 
	}
