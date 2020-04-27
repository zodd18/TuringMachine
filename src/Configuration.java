public class Configuration {
    public int state;
    public String E;
    public int z;
    public Configuration(int state, String E, int z) {
        this.state = state;
        this.E = E;
        this.z = z;
    }

    @Override
    public String toString() {
        return "(q" + state + ", ..." + E + "..., " + z + ")";
    }
}
