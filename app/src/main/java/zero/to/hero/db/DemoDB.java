package zero.to.hero.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by azizimusa at 9/15/20 5:32 PM
 */

@Table(name = "demo")
public class DemoDB extends Model {

    @Column
    private String ayam;

    public String getAyam() {
        return ayam;
    }

    public void setAyam(String ayam) {
        this.ayam = ayam;
    }
}
