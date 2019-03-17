package se.BTH.BusReservation.domain;

public class StaticData {
    //static data
    public static Integer ticketNum = 999;// index for  increase ticket number automatically
    public static int[][] TravelTime = new int[14][14]; // static array for travel time between locations
    public static final int delay=5; // every bus has delay time 5 minutes in every location
    static {
        TravelTime[0][1]= 240;
        TravelTime[0][2]= 210; TravelTime[1][2]=60;
        TravelTime[0][3]= 210; TravelTime[1][3]=360; TravelTime[2][3]=300;
        TravelTime[0][4]= 120; TravelTime[1][4]=180; TravelTime[2][4]=210;  TravelTime[3][4]=240 ;
        TravelTime[0][5]= 180; TravelTime[1][5]=210; TravelTime[2][5]=90;   TravelTime[3][5]=90 ;
        TravelTime[0][6]= 210; TravelTime[1][6]=30;  TravelTime[2][6]=90;   TravelTime[3][6]=90 ;
        TravelTime[0][7]= 210; TravelTime[1][7]=90;  TravelTime[2][7]=45;   TravelTime[3][7]=180 ;
        TravelTime[0][8]= 45;  TravelTime[1][8]=600; TravelTime[2][8]=90;   TravelTime[3][8]=260 ;
        TravelTime[0][9]= 300; TravelTime[1][9]=300; TravelTime[2][9]=260;  TravelTime[3][9]=180 ;
        TravelTime[0][10]= 270;TravelTime[1][10]=90; TravelTime[2][10]=180; TravelTime[3][10]=120 ;
        TravelTime[0][11]= 120;TravelTime[1][11]=300;TravelTime[2][11]=180; TravelTime[3][11]=420 ;
        TravelTime[0][12]= 120;TravelTime[1][12]=210;TravelTime[2][12]=210; TravelTime[3][12]=180 ;
        TravelTime[0][13]= 210;TravelTime[1][13]=210;TravelTime[2][13]=330; TravelTime[3][13]=210 ;

        TravelTime[4][5]= 420;
        TravelTime[4][6]= 300; TravelTime[5][6]=180;
        TravelTime[4][7]= 480; TravelTime[5][7]=180; TravelTime[6][7]=480;
        TravelTime[4][8]= 300; TravelTime[5][8]=150; TravelTime[6][8]=260; TravelTime[7][8]=260 ;
        TravelTime[4][9]= 270; TravelTime[5][9]=180; TravelTime[6][9]=90;  TravelTime[7][9]=260 ;
        TravelTime[4][10]= 240;TravelTime[5][10]=150;TravelTime[6][10]=210;TravelTime[7][10]=210 ;
        TravelTime[4][11]= 180;TravelTime[5][11]=270;TravelTime[6][11]=45; TravelTime[7][11]=260 ;
        TravelTime[4][12]= 360;TravelTime[5][12]=150;TravelTime[6][12]=270;TravelTime[7][12]=270 ;
        TravelTime[4][13]= 210;TravelTime[5][13]=150;TravelTime[6][13]=330;TravelTime[7][13]=180 ;

        TravelTime[8][9]= 540;
        TravelTime[8][10]= 120;TravelTime[9][10]=260;
        TravelTime[8][11]= 330;TravelTime[9][11]=30; TravelTime[10][11]=600;
        TravelTime[8][12]= 260;TravelTime[9][12]=270;TravelTime[10][12]=270; TravelTime[11][12]=270 ;
        TravelTime[8][13]= 270;TravelTime[9][13]=270;TravelTime[10][13]=300; TravelTime[11][13]=150 ;

        TravelTime[12][13]=210 ;
    }
    // first number for ticket will be 1000 and increase automatically
        public static int getTicketNum() {
            ticketNum++;
            return ticketNum;
        }

    }
