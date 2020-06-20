package sample;

public class Flappy {

    int x;
    int y;
    int height;
    int widht;
    int up;

    double fallspeed;
    double fallacceleration;
    double fallacceleration_last;
    double time_last;
    double step;

    int [][] rand = new int [14][2];


    public Flappy (int x1, int y1, int h, int w, int u, double fallsp, double fallacc){
        x = x1;
        y = y1;
        height = h;
        widht = w;
        up = u;
        fallspeed = fallsp;
        time_last = 0;
        step = 0;
        fallacceleration = fallacc;
        fallacceleration_last = 1;
        rand[0][0] = 35;
        rand[0][1] = 0;
        rand[1][0] = 41;
        rand[1][1] = 6;
        rand[2][0] = 44;
        rand[2][1] = 9;
        rand[3][0] = 50;
        rand[3][1] = 21;
        rand[4][0] = 45;
        rand[4][1] = 30;
        rand[5][0] = 30;
        rand[5][1] = 34;
        rand[6][0] = 14;
        rand[7][1] = 34;
        rand[8][0] = 6;
        rand[8][1] = 29;
        rand[9][0] = 3;
        rand[9][1] = 24;
        rand[10][0] = 0;
        rand[10][1] = 21;
        rand[11][0] = 0;
        rand[11][1] = 11;
        rand[12][0] = 9;
        rand[12][1] = 6;
        rand[13][0] = 17;
        rand[13][1] = 0;

    }

    public int getX (){
        return x;
    }
    public int getY (){
        return y;
    }
    public int getHeight () {return height;}
    public int getWidht () {return widht;}
    public int getUp (){return up;}


    public void fall (double t){

        double time_diff;
        int i;

        time_diff = t - time_last;

        step = step + time_diff * fallspeed * fallacceleration_last /10;

        i = (int) step / 1;

        y = y + i;

        if (i > 0){
            step = step - i;
            fallacceleration_last = fallacceleration_last + fallacceleration;
        }

        time_last = t;
    }

    public void presskey (){

        y = y - up;
        fallacceleration_last = 1;
    }

    public int getrand_x (int pos ){
            return rand [pos][0];
    }

    public int getrand_y (int pos ){
        return rand [pos][1];
    }

    public int getrand_number (){
        return 14;
    }

    public  void reset (int sety, double t){
        y = sety;
        time_last = t;
        fallacceleration_last = 1;
    }

    public void setTime_last (double t){
        time_last = t;
    }

}

