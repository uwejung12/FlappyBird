package sample;

import java.util.Random;

public class Piepe {

    int x;
    int xtop;
    int piepetop;
    int piepebottom;

    public Piepe (int setx){

        Random rand = new Random();
        int n = rand.nextInt(28);

        x = setx;
        xtop = 4;

        piepetop = rand.nextInt(n);
        piepebottom = n - piepetop;
    }

    public int gettop (){
        return piepetop + 2;
    }
    public int getbottom (){
        return piepebottom + 2;
    }
    public int getX (){
        return x ;
    }
    public int getXtop (){
        return xtop ;
    }

    public void move (){
        x = x-1;
    }

    public boolean Checkcollision (int FlappyX, int FlappyY){
            if (
                //FlappyX >= x && FlappyX <= x + 40 && FlappyY <= 20 * (piepetop + 2) ||
                //FlappyX >= x - xtop && FlappyX <= x + 40 + xtop && FlappyY <= 20 * (piepetop + 2) + 24 ||
                FlappyX >= x && FlappyY >= 0 && FlappyX <= x + 40 && FlappyY <= 20 * (piepetop + 2) ||


                FlappyX >= x - xtop  &&  FlappyY >= 20 * (piepetop + 2) && FlappyX <= x + 40 + xtop && FlappyY <= 20 * (piepetop + 2) ||

                FlappyX >= x && FlappyX <= x + 40 && FlappyY >= 750 -(20 * (piepebottom + 2)) ||
                FlappyX >= x - xtop && FlappyX <= x + 40 + xtop && FlappyY >= 750 - (20 * (piepebottom + 2) - 24)

            ){

                System.out.println("FlappyX: " + FlappyX );
                System.out.println("FlappyY: " + FlappyY );
                System.out.println("Piepe: " + piepebottom );

                int tmp = x - xtop;
                System.out.println("X : " + tmp );

                tmp = 750 - (20 * (piepebottom + 2) - 24 );
                System.out.println("Y : " + tmp );
            return true;
        }else {
                return  false;
            }
    }
}