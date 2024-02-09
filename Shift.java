public class Shift {
  private final CalendarTime start;
  private final CalendarTime finish;

  public Shift(CalendarTime start, CalendarTime finish) {
     this.start = start;
     this.finish = finish;
           }

  public CalendarTime start(){
       return start;
       }

    public CalendarTime finish()  {
    return finish;
        }

    public boolean inWeek(Week w)    {
     return w.includes(start.date()) || w.includes(finish.date());
   }

    public boolean includesDate(Date date){
    return (date.compareTo(start.date()) >= 0) && (date.compareTo(finish.date()) <= 0);
    }

    public Duration length()  {
      return finish.subtract(start);
        }

     public String toString(){
      return start.toString() + " - " + finish.toString();
          }
}
