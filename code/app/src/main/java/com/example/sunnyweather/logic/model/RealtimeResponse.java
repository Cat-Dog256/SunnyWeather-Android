package com.example.sunnyweather.logic.model;

public class RealtimeResponse {
    private String status;
    private Result result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public class Result {
        private Realtime realtime;

        public Realtime getRealtime() {
            return realtime;
        }

        public void setRealtime(Realtime realtime) {
            this.realtime = realtime;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "realtime=" + realtime +
                    '}';
        }
    }
    public class Realtime{
        private Float temperature;
        private String skycon;
        private AirQuality air_quality;

        public Float getTemperature() {
            return temperature;
        }

        public void setTemperature(Float temperature) {
            this.temperature = temperature;
        }

        public String getSkycon() {
            return skycon;
        }

        public void setSkycon(String skycon) {
            this.skycon = skycon;
        }

        public AirQuality getAir_quality() {
            return air_quality;
        }

        public void setAir_quality(AirQuality air_quality) {
            this.air_quality = air_quality;
        }
    }
    public class AirQuality{
        private AQI aqi;

        public AQI getAqi() {
            return aqi;
        }

        public void setAqi(AQI aqi) {
            this.aqi = aqi;
        }
    }
    public class AQI{
        private float chn;

        public float getChn() {
            return chn;
        }

        public void setChn(float chn) {
            this.chn = chn;
        }
    }

    @Override
    public String toString() {
        return "RealtimeResponse{" +
                "status='" + status + '\'' +
                ", result=" + result +
                '}';
    }
}
