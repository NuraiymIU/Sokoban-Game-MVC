import java.io.Serializable;

public class Desktop  implements Serializable {
    private int[][] desktop;

    public Desktop(int[][] desktop) {
        this.desktop = desktop;
    }

    public int[][] getDesktop() {
        return desktop;
    }
}
