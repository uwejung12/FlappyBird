package sample;

public class Flappy {

    int x;
    int y;
    int height;
    int widht;
    int up;
    double fallspeed;
    // t_tmp for the time to save
    double time_last;
    double step;

    int [][] rand = new int [14][2];


    public Flappy (int x1, int y1, int h, int w, int u, double fallsp){
        x = x1;
        y = y1;
        height = h;
        widht = w;
        up = u;
        fallspeed = fallsp;
        time_last = 0;
        step = 0;
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

        //System.out.println("t: " + t);
        //System.out.println("time_last: " + time_last);

        time_diff = t - time_last;

        System.out.println("time_diff: " + time_diff);

        step = step + time_diff * fallspeed / 10;

        // System.out.println("step: " + step);

        i = (int) step / 1;

        y = y + i;

        // System.out.println("i: " + i);

        if (i > 0){
            step = step - i;
        }

        System.out.println("y: " + y);

        time_last = t;

    }

    public void presskey (){
        y = y - up;
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
}

