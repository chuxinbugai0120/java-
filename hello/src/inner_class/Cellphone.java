package inner_class;

public class Cellphone {

    public static void main(String[] args) {

        Cellphone cellphone = new Cellphone();
        cellphone.alarmclock(new Bell(){
            @Override
            public void ring() {
                System.out.println("韩亦辰是呆瓜");
            }
        });
    }
    public void alarmclock(Bell p)
    {
        p.ring();
    }

}
interface Bell {
    void ring();
}

