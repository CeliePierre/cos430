package climatdata;

/**
 *
 * @author kalalajoel
 */
public class DailyReading {

    Date date;
    int maxTemp;
    int minTemp;

    DailyReading(Date date, int maxTemp, int minTemp) {

        this.date = date;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;

    }

    Date getDate() {
        return date;
    }

    int getmaxTemp() {
        return maxTemp;

    }

    int getminTemp() {
        return minTemp;
    }

    @Override
    public String toString() {
        return String.format("%s %6d %6d",date.toString(),maxTemp,minTemp);
    }
}
