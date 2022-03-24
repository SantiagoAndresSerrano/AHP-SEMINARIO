package ufps.ahp.negocio;

import java.util.Calendar;
import java.util.Date;

public class PlayGround {

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR,1);
        Date manana = calendar.getTime();
        System.out.println(manana);
        System.out.println(manana.before(new Date()));

    }
}
