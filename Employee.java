import java.util.ArrayList;
import java.util.List;

public class Employee   {
    private String name;
    private String uid;
    private List<Shift> shifts;
    private CalendarTime lastSignInTime; // Track the last sign-in time

    public Employee(String name, String uid) {
      this.name = name;
      this.uid =uid;
       this.shifts = new ArrayList<>();
     this.lastSignInTime =null;
   }

  public String name() {
        return name;
     }

  public String UID(){
        return uid;
          }

  public void signIn(Date d, Time t) {
       if (lastSignInTime == null)   {
            lastSignInTime = new CalendarTime(d, t);
        } else    {
            // Handle the case where a sign-in occurs while the previous sign-out is missing
            Shift lastShift = new Shift(lastSignInTime, null);
            shifts.add(lastShift);
            lastSignInTime = new CalendarTime(d, t); }
    }

    public void signOut(Date d, Time t){
        if (lastSignInTime != null)     {
            Shift lastShift = new Shift(lastSignInTime, new CalendarTime(d, t));
            shifts.add(lastShift);
            lastSignInTime = null;
         }
      }

  public boolean present(){
        return lastSignInTime != null;
  }

  public boolean worked(Date d) {
        for (Shift shift : shifts) {
            if (shift.includesDate(d)) {
                return true;
        }
      }
      return false;
    }

 public boolean worked(Week w) {
     for (Shift shift : shifts) {
         if (shift.inWeek(w)) {
              return true;
            }
        }
        return false;
    }

    public List<Shift> get(Date d) {
        List<Shift> shiftsOnDate = new ArrayList<>();
        for (Shift shift : shifts) {
            if (shift.includesDate(d)) {
                shiftsOnDate.add(shift);
            }
        }
        return shiftsOnDate;
    }

    public List<Shift> get(Week w) {
        List<Shift> shiftsInWeek = new ArrayList<>();
        for (Shift shift : shifts) {
            if (shift.inWeek(w)) {
                shiftsInWeek.add(shift);
            }
        }
        return shiftsInWeek;
    }

    public Duration hours(Week w) {
        Duration totalHours = new Duration(0);
        List<Shift> shiftsInWeek = get(w);
        for (Shift shift : shiftsInWeek) {
            totalHours = totalHours.add(shift.length());
        }
        return totalHours;
    }
}
