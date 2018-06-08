package no.usn.a151710.hobbyhusetv2;

import org.json.JSONException;
import org.json.JSONObject;

public class Vare {


    private int varekode, lagerAntall, aktiv;
    private String betegnelse;
    private double prisPrEnhet;
    
    private static final String KOL_NAVN_VAREKODE = "Varekode";
    private static final String KOL_NAVN_BETEGNELSE = "Betegnelse";
    private static final String KOL_NAVN_PRIS = "PrisPrEnhet";
    private static final String KOL_NAVN_LAGERANTALL = "LagerAntall";
    private static final String KOL_NAVN_AKTIV = "Aktiv";


    public Vare(int varekode, String betegnelse, double prisPrEnhet, int lagerAntall, int aktiv) {
        this.varekode = varekode;
        this.betegnelse = betegnelse;
        this.prisPrEnhet = prisPrEnhet;
        this.lagerAntall = lagerAntall;
        this.aktiv = aktiv;

    }

    public Vare(JSONObject jsonVare) {
        this.varekode = jsonVare.optInt("Varekode");
        this.betegnelse = jsonVare.optString("Betegnelse");
        this.prisPrEnhet = jsonVare.optDouble("PrisPrEnhet");
        this.lagerAntall = jsonVare.optInt("LagerAntall");
        this.aktiv = jsonVare.optInt("Aktiv");
    }

    public JSONObject lagJSONObject () {
        JSONObject jsonVare = new JSONObject();

        try {
            jsonVare.put(KOL_NAVN_VAREKODE, this.varekode);
            jsonVare.put(KOL_NAVN_BETEGNELSE, this.betegnelse);
            jsonVare.put(KOL_NAVN_PRIS, this.prisPrEnhet);
            jsonVare.put(KOL_NAVN_LAGERANTALL, this.lagerAntall);
            jsonVare.put(KOL_NAVN_AKTIV, this.aktiv);
        } catch (JSONException e) {
            return null;
        }
        return jsonVare;

    }

    // Gettere
    public int getVarekode() {
        return varekode;
    }

    public int getLagerAntall() {
        return lagerAntall;
    }

    public int getAktiv() {
        return aktiv;
    }

    public String getBetegnelse() {
        return betegnelse;
    }

    public double getPrisPrEnhet() {
        return prisPrEnhet;
    }

    // Settere
    public void setVarekode(int varekode) {
        this.varekode = varekode;
    }

    public void setLagerAntall(int lagerAntall) {
        this.lagerAntall = lagerAntall;
    }

    public void setAktiv(int aktiv) {
        this.aktiv = aktiv;
    }

    public void setBetegnelse(String betegnelse) {
        this.betegnelse = betegnelse;
    }

    public void setPrisPrEnhet(double prisPrEnhet) {
        this.prisPrEnhet = prisPrEnhet;
    }
}
