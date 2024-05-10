package pojos.HerokuApp;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Hero_BookingDates_Pojo {
    private String checkin;
    private String checkout;

    public Hero_BookingDates_Pojo(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public Hero_BookingDates_Pojo() {
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    @Override
    public String toString() {
        return "Hero_BookingDates_Pojo{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}
