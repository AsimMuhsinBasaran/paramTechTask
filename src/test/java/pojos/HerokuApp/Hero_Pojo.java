package pojos.HerokuApp;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Hero_Pojo {
    private Integer bookingid;
    private Hero_Booking_Pojo booking;

    public Hero_Pojo(Integer bookingid, Hero_Booking_Pojo booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }

    public Hero_Pojo() {
    }

    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public Hero_Booking_Pojo getBooking() {
        return booking;
    }

    public void setBooking(Hero_Booking_Pojo booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "Hero_Pojo{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}
