package com.example.weatherforcast;

public class Weather {
    private String weather ;
    private String cityName ;
    private Integer temp ;
    private Integer tempFL;
    private Integer tempL ;
    private Integer tempH ;

    public Weather(String weather, String cityName, Integer temp, Integer tempFL, Integer tempL, Integer tempH) {
        this.weather = weather;
        this.cityName = cityName;
        this.temp = temp;
        this.tempFL = tempFL;
        this.tempL = tempL;
        this.tempH = tempH;
    }

    public Integer C2F(Integer cTemp) {
        Integer fTemp = (int)(cTemp * (9/5) - 32);
        return fTemp;
    }

    public Integer F2C(Integer fTemp) {
        Integer cTemp = (int)((fTemp + 32) * (5/9));
        return cTemp;
    }

    public String getWeather() {
        return weather;
    }

    public String getCityName() {
        return cityName;
    }

    public Integer getTemp() {
        return temp;
    }

    public Integer getTempFL() {
        return tempFL;
    }

    public Integer getTempL() {
        return tempL;
    }

    public Integer getTempH() {
        return tempH;
    }
}
