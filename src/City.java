import com.google.gson.annotations.SerializedName;

public class City {
    @SerializedName("LatD")
    private int latD;
    @SerializedName("LatM")
    private int latM;
    @SerializedName("LatS")
    private int latS;
    @SerializedName("NS")
    private String ns;
    @SerializedName("LonD")
    private int lonD;
    @SerializedName("LonM")
    private int lonM;
    @SerializedName("LonS")
    private int lonS;
    @SerializedName("EW")
    private String ew;
    @SerializedName("City")
    private String city;
    @SerializedName("State")
    private String state;

    public City(){

    }

    public City(int latD, int latM, int latS, String ns, int lonD, int lonM, int lonS, String ew, String city, String state){
        this.latD = latD;
        this.latM = latM;
        this.latS = latS;
        this.ns = ns;
        this.lonD = lonD;
        this.lonM = lonM;
        this.lonS = lonS;
        this.ew = ew;
        this.city = city;
        this.state = state;
    }

    public City(String[] metadata){
        latD = Integer.parseInt(metadata[0]);
        latM = Integer.parseInt(metadata[1]);
        latS = Integer.parseInt(metadata[2]);
        ns = metadata[3];
        lonD = Integer.parseInt(metadata[4]);
        lonM = Integer.parseInt(metadata[5]);
        lonS = Integer.parseInt(metadata[6]);
        ew = metadata[7];
        city = metadata[8];
        state = metadata[9];
    }

    public int getLatD() {
        return latD;
    }

    public void setLatD(int latD) {
        this.latD = latD;
    }

    public int getLatM() {
        return latM;
    }

    public void setLatM(int latM) {
        this.latM = latM;
    }

    public int getLatS() {
        return latS;
    }

    public void setLatS(int latS) {
        this.latS = latS;
    }

    public String getNs() {
        return ns;
    }

    public void setNs(String ns) {
        this.ns = ns;
    }

    public int getLonD() {
        return lonD;
    }

    public void setLonD(int lonD) {
        this.lonD = lonD;
    }

    public int getLonM() {
        return lonM;
    }

    public void setLonM(int lonM) {
        this.lonM = lonM;
    }

    public int getLonS() {
        return lonS;
    }

    public void setLonS(int lonS) {
        this.lonS = lonS;
    }

    public String getEw() {
        return ew;
    }

    public void setEw(String ew) {
        this.ew = ew;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString(){
        return "LatD: "+latD+" \tLatM: "+latM+" \tLatS: "+latS+" \tNS: "+ns+" \tLonD: "+lonD+" \tLonM: "+lonM+" \tLonS: "+lonS+" \tEW: "+ew+" \tCity: "+city+"       \tState: "+state;
    }
}
