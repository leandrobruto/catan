package com.leandro.mapa1;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class ActMain extends Activity {

    Region regPolygon;
    Region regCircle;
    Path polygon;
    Path circle;
    String imagem;

    Map<Integer, Path> map = new LinkedHashMap<>();
    Map<Integer, ArrayList> vizinho = new LinkedHashMap<>();
    ArrayList<Integer> imagens = new ArrayList<>();
    private static final String TAG = "QuicklyNotes";
    private Tela tela;
    Bitmap agua, barro, la, deserto, ferro, madeira, grao, back, next, refresh,
     dois, tres, quatro, cinco, seis, oito, nove, dez, onze, doze, heim, ham;

    int centerY;
    int centerX = 0;
    int raio = 37;
    int nlados = 6;
    float hori = (float) (raio * Math.sqrt(3) / 2);
    float distY = ((raio * 2) - 5);
    float distX = ((raio * 3 / 2) + 5);
    int mainMap[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    int tokens[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    int portos[] = {0, 0, 0, 0, 0, 0, 0, 0 ,0};
    int ilhas[];

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tela = new Tela(this);
        setContentView(tela);

        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        int rotation = display.getRotation();

        Point size = new Point();
        display.getSize(size);

        if (rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180) {
            if (size.x < size.y) {
                if (rotation == Surface.ROTATION_0) {

                } else {

                }
            }
        } else {
            if (size.x > size.y) {
                if (rotation == Surface.ROTATION_90) {

                    centerX = 165;


                } else {

                    centerX = 165;

                }
            }
        }

        //this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);

        agua = BitmapFactory.decodeResource(getResources(), R.drawable.sea);
        agua = Bitmap.createScaledBitmap(agua, raio * 2,(int) hori * 2, true);

        barro = BitmapFactory.decodeResource(getResources(),R.drawable.hills);
        barro = Bitmap.createScaledBitmap(barro, raio * 2,(int) hori * 2, true);

        la = BitmapFactory.decodeResource(getResources(),R.drawable.pasture);
        la = Bitmap.createScaledBitmap(la, raio * 2,(int) hori * 2, true);

        deserto = BitmapFactory.decodeResource(getResources(),R.drawable.desert);
        deserto = Bitmap.createScaledBitmap(deserto, raio * 2,(int) hori * 2, true);

        ferro = BitmapFactory.decodeResource(getResources(),R.drawable.mountains);
        ferro = Bitmap.createScaledBitmap(ferro, raio * 2,(int) hori * 2, true);

        madeira = BitmapFactory.decodeResource(getResources(),R.drawable.forest);
        madeira = Bitmap.createScaledBitmap(madeira, raio * 2,(int) hori * 2, true);

        grao = BitmapFactory.decodeResource(getResources(),R.drawable.fields);
        grao = Bitmap.createScaledBitmap(grao, raio * 2,(int) hori * 2, true);

        back = BitmapFactory.decodeResource(getResources(), R.drawable.setaback);
        back = Bitmap.createScaledBitmap(back, (int) hori * 2, raio * 2, true);

        next = BitmapFactory.decodeResource(getResources(), R.drawable.setanext);
        next = Bitmap.createScaledBitmap(next, (int) hori * 2, raio * 2, true);

        refresh = BitmapFactory.decodeResource(getResources(), R.drawable.random);
        refresh = Bitmap.createScaledBitmap(refresh, (int) hori * 2, raio * 2, true);

        dois = BitmapFactory.decodeResource(getResources(), R.drawable.dois);
        dois = Bitmap.createScaledBitmap(dois, raio, raio, true);

        tres = BitmapFactory.decodeResource(getResources(), R.drawable.tres);
        tres = Bitmap.createScaledBitmap(tres, raio, raio, true);

        quatro = BitmapFactory.decodeResource(getResources(), R.drawable.quatro);
        quatro = Bitmap.createScaledBitmap(quatro, raio, raio, true);

        cinco = BitmapFactory.decodeResource(getResources(), R.drawable.cinco);
        cinco = Bitmap.createScaledBitmap(cinco, raio, raio, true);

        seis = BitmapFactory.decodeResource(getResources(), R.drawable.seis);
        seis = Bitmap.createScaledBitmap(seis, raio, raio, true);

        oito = BitmapFactory.decodeResource(getResources(), R.drawable.oito);
        oito = Bitmap.createScaledBitmap(oito, raio, raio, true);

        nove = BitmapFactory.decodeResource(getResources(), R.drawable.nove);
        nove = Bitmap.createScaledBitmap(nove, raio, raio, true);

        dez = BitmapFactory.decodeResource(getResources(), R.drawable.dez);
        dez = Bitmap.createScaledBitmap(dez, raio, raio, true);

        onze = BitmapFactory.decodeResource(getResources(), R.drawable.onze);
        onze = Bitmap.createScaledBitmap(onze, raio, raio, true);

        doze = BitmapFactory.decodeResource(getResources(), R.drawable.doze);
        doze = Bitmap.createScaledBitmap(doze, raio, raio, true);

        heim = BitmapFactory.decodeResource(getResources(), R.drawable.heim);
        heim = Bitmap.createScaledBitmap(heim, raio, raio, true);

        ham = BitmapFactory.decodeResource(getResources(), R.drawable.ham);
        ham = Bitmap.createScaledBitmap(ham, raio * 2,(int) hori * 2, true);
    }

    class Tela extends View {
        Tela(Context context) {
            super(context);
        }

        private int imgRand(){
            Random gerador = new Random();
            int n;
            do{
                n = gerador.nextInt(10);
            }while (mainMap[n] == 0);
            mainMap[n] -= 1;
            return n;
        }

        private int tokenRand(){
            Random gerador = new Random();
            int n;
            do{
                n = gerador.nextInt(10);
            }while (tokens[n] == 0);
            tokens[n] -= 1;
            return n;
        }

//        private int posesRand(){
//            int n;
//            Random gerador = new Random();
//            do{
//                n = gerador.nextInt(42);
//            }while(portos[n] != 0 && portos[n] > 7);
//            portos[n] -= 1;
//            return n;
//        }

        private int posesRand(){
            System.out.println("okokok");
            Random gerador = new Random();
            int n;
            do {
                n = gerador.nextInt(42);
            }while(imagens.get(n) == 0 || imagens.get(n) == 8 || imagens.get(n) == 9);
            System.out.println("bloco-> "+ (n+1)+"  numero --->" + n + "  imagens->"+imagens.get(n));
            return n + 1;
        }

        private void tiles(){
            mainMap[0] = 7;  //sea
            mainMap[1] = 0;  //desert
            mainMap[2] = 0;  //gold field
            mainMap[3] = 5;  //fields
            mainMap[4] = 4;  //hills
            mainMap[5] = 4;  //mountain
            mainMap[6] = 5;  //pasture
            mainMap[7] = 5;  //forest
            mainMap[8] = 6;  //sea
            mainMap[9] = 6;  //sea
        }

        private void tokens(){
            tokens[0] = 1;  //2
            tokens[1] = 2;  //3
            tokens[2] = 3;  //4
            tokens[3] = 3;  //5
            tokens[4] = 2;  //6
            tokens[5] = 2;  //8
            tokens[6] = 3;  //9
            tokens[7] = 3;  //10
            tokens[8] = 3;  //11
            tokens[9] = 1;  //12
        }

        private void portos(){
            portos[0] = 1;  //grain
            portos[1] = 1;  //brick
            portos[2] = 1;  //ore
            portos[3] = 1;  //wool
            portos[4] = 1;  //lumber
            portos[5] = 4;  //Any resources
        }

        private int direction(){
            Random direct = new Random();
            int n;
             n = direct.nextInt(6);
            return n;
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_UP) {
                Point point = new Point();
                point.x = (int) event.getX();
                point.y = (int) event.getY();

                Log.d(TAG, "Coordenadas " + point);
                for(Integer key : map.keySet()){
                    Path value = map.get(key);
                    Path circle = map.get(44);

                    RectF rectF = new RectF();
                    value.computeBounds(rectF, true);

                    regPolygon = new Region();
                    regPolygon.setPath(value, new Region((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom));

                    regCircle = new Region();
                    regCircle.setPath(circle, new Region((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom));

                    if (regCircle.contains(point.x, point.y)) {
                        Log.d(TAG, "TOUCH IN");
                        Toast.makeText(getApplicationContext(), "Map generated", Toast.LENGTH_SHORT).show();
                        invalidate();
                    } else if(regPolygon.contains(point.x, point.y)){

                        switch (imagens.get(key-1)) {
                            case 0:
                                imagem = "Água";
                                break;
                            case 1:
                                imagem = "Deserto";
                                break;
                            case 2:
                                imagem = "Ouro";
                                break;
                            case 3:
                                imagem = "Grão";
                                break;
                            case 4:
                                imagem = "Barro";
                                break;
                            case 5:
                                imagem = "Ferro";
                                break;
                            case 6:
                                imagem = "Lã";
                                break;
                            case 7:
                                imagem = "Madeira";
                                break;
                            case 8:
                                imagem = "Água";
                                break;
                            case 9:
                                imagem = "Água";
                                break;
                            case 10:
                                imagem = "Back";
                                break;
                            case 12:
                                imagem = "Next";
                                break;
                            default:
                                imagem = "default";
                                break;
                        }
                        Toast.makeText(getApplicationContext(), imagem, Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "TOUCH IN");
                    }else{
                        Log.d(TAG, "TOUCH OUT");
                    }
                }
            }
            return true;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();

            imagens.clear();
            map.clear();
            vizinho.clear();
            tiles();
            tokens();
            portos();

            paint.setStyle(Paint.Style.STROKE);
            canvas.drawColor(Color.GRAY);
            paint.setColor(Color.TRANSPARENT);

            int k = 5;
            int qnt = 1;
            int soma = 0;
            int f = 0;

            for (int n = 0; n < 7; n++) {
                if(n >= 1 && n < 3){
                    k++;
                }else if(n == 3){
                    soma--;
                    k--;
                }else if(n == 4){
//                    soma++;
                    k++;
                }else if (n == 0){
                    k--;
                }else{
                    soma--;
                    k--;
                }
                for (int m = 0; m <= k; m++) {

                    if (n % 2 == 0) {
                        centerY = 150;
                        if (n == 2 || n == 4) {
                            centerY = 150 - (raio - 2) * 2;
                        }
                    } else if (n == 3) {
                        centerY = 150 - (raio - 2);
                    } else {
                        centerY = 150 - raio + 2;
                    }

                    polygon = new Path();
                    double ang = Math.PI * 2 / nlados;
                    polygon.moveTo((float) (centerX + (n + 1) * distX + raio * Math.cos(0)), (float) (centerY + distY * (m + 1) + raio * Math.sin(0)));
                    for (int i = 1; i < nlados; i++)
                        polygon.lineTo((float) (centerX + (n + 1) * distX + raio * Math.cos(ang * i)), (float) (centerY + distY * (m + 1) + raio * Math.sin(ang * i)));
                    polygon.close();
                    canvas.drawPath(polygon, paint);
                    paint.setColor(Color.GRAY);

                    int autoImg = imgRand();
                    switch (autoImg) {
                        case 0:
                            canvas.drawBitmap(agua, centerX - raio * 2 + (n + 1) * distX + raio, centerY - hori * 2 + distY * (m + 1) + raio - 3, paint);
                            break;
                        case 1:
                            canvas.drawBitmap(deserto, centerX - raio * 2 + (n + 1) * distX + raio, centerY - hori * 2 + distY * (m + 1) + raio - 3, paint);
                            break;
                        case 2:
//                            canvas.drawBitmap(ouro, centerX - raio * 2 + (n + 1) * distX + raio, centerY - hori * 2 + distY * (m + 1) + raio - 3, paint);
                            break;
                        case 3:
                            canvas.drawBitmap(grao, centerX - raio * 2 + (n + 1) * distX + raio, centerY - hori * 2 + distY * (m + 1) + raio - 3, paint);
                            break;
                        case 4:
                            canvas.drawBitmap(barro, centerX - raio * 2 + (n + 1) * distX + raio, centerY - hori * 2 + distY * (m + 1) + raio - 3, paint);
                            break;
                        case 5:
                            canvas.drawBitmap(ferro, centerX - raio * 2 + (n + 1) * distX + raio, centerY - hori * 2 + distY * (m + 1) + raio - 3, paint);
                            break;
                        case 6:
                            canvas.drawBitmap(la, centerX - raio * 2 + (n + 1) * distX + raio, centerY - hori * 2 + distY * (m + 1) + raio - 3, paint);
                            break;
                        case 7:
                            canvas.drawBitmap(madeira, centerX - raio * 2 + (n + 1) * distX + raio, centerY - hori * 2 + distY * (m + 1) + raio - 3, paint);
                            break;
                        case 8:
                            canvas.drawBitmap(agua, centerX - raio * 2 + (n + 1) * distX + raio, centerY - hori * 2 + distY * (m + 1) + raio - 3, paint);
                            break;
                        case 9:
                            canvas.drawBitmap(agua, centerX - raio * 2 + (n + 1) * distX + raio, centerY - hori * 2 + distY * (m + 1) + raio - 3, paint);
                            break;
                        default:
                            break;
                    }
                    imagens.add(autoImg);

                    int autotk;
                    if(autoImg == 1 || autoImg ==  2 || autoImg ==  3 || autoImg == 4 || autoImg == 5 || autoImg == 6 || autoImg ==  7) {
                        autotk = tokenRand();
                        switch (autotk) {
                            case 0:
                                canvas.drawBitmap(dois, centerX - raio * 2 + (n + 1) * distX + raio, centerY - hori * 2 + distY * (m + 1) + raio - 3, paint);
                                break;
                            case 1:
                                canvas.drawBitmap(tres, centerX - raio * 2 + (n + 1) * distX + raio, centerY - hori * 2 + distY * (m + 1) + raio - 3, paint);
                                break;
                            case 2:
                                canvas.drawBitmap(quatro, centerX - raio * 2 + (n + 1) * distX + raio, centerY - hori * 2 + distY * (m + 1) + raio - 3, paint);
                                break;
                            case 3:
                                canvas.drawBitmap(cinco, centerX - raio * 2 + (n + 1) * distX + raio, centerY - hori * 2 + distY * (m + 1) + raio - 3, paint);
                                break;
                            case 4:
                                canvas.drawBitmap(seis, centerX - raio * 2 + (n + 1) * distX + raio, centerY - hori * 2 + distY * (m + 1) + raio - 3, paint);
                                break;
                            case 5:
                                canvas.drawBitmap(oito, centerX - raio * 2 + (n + 1) * distX + raio, centerY - hori * 2 + distY * (m + 1) + raio - 3, paint);
                                break;
                            case 6:
                                canvas.drawBitmap(nove, centerX - raio * 2 + (n + 1) * distX + raio, centerY - hori * 2 + distY * (m + 1) + raio - 3, paint);
                                break;
                            case 7:
                                canvas.drawBitmap(dez, centerX - raio * 2 + (n + 1) * distX + raio, centerY - hori * 2 + distY * (m + 1) + raio - 3, paint);
                                break;
                            case 8:
                                canvas.drawBitmap(onze, centerX - raio * 2 + (n + 1) * distX + raio, centerY - hori * 2 + distY * (m + 1) + raio - 3, paint);
                                break;
                            case 9:
                                canvas.drawBitmap(doze, centerX - raio * 2 + (n + 1) * distX + raio, centerY - hori * 2 + distY * (m + 1) + raio - 3, paint);
                                break;
                            default:
                                break;
                        }
                    }

                    map.put(qnt, polygon);
                    vizinho.put(qnt, new ArrayList<Integer>());

                    if(m > 0) {
//                        vizinho.get(qnt).add(qnt - 1);
//                        vizinho.get(qnt - 1).add(qnt);

                        vizinho.get(qnt).add(imagens.get(qnt - 2));
                        vizinho.get(qnt - 1).add(imagens.get(qnt - 1));
//                        System.out.println(qnt + "add" + imagens.get(qnt - 2) + " | " + (qnt-1) + "add" + imagens.get(qnt - 1));
                    }else{
                        f = qnt - soma;
                        if(n==3){
                            f--;
                        }

                    }

                    if(n > 0){
                        int soma2 = soma, f1  = f;
                        if(n > 4){
                            f1--;
                        }
//                        System.out.println("Qnt-"+qnt+" soma-"+soma+" f-"+f+" k-"+k);
//                        System.out.println(" limites(QNTSOMA)-" + (qnt - (soma + 1)) +" (F+K-1)-" +(f +soma2));
                        if((qnt - (soma + 1)) >= f1 && (qnt - (soma + 1) <= (f+soma2))){
                            vizinho.get(qnt).add(imagens.get(qnt - (soma + 1) - 1));
                            vizinho.get(qnt - (soma + 1)).add(imagens.get(qnt - 1));
//                            System.out.println(qnt + "-1->" + imagens.get(qnt - (soma + 1) - 1));
                        }
                        if(qnt == 24){
                            soma2++;
                        }
                        if((qnt - (soma)) >= f && (qnt - (soma) < (f+soma2))){
                            vizinho.get(qnt).add(imagens.get(qnt - (soma) - 1));
                            vizinho.get(qnt - (soma)).add(imagens.get(qnt - 1));
//                            System.out.println(qnt + "-2->" + imagens.get(qnt - (soma) - 1));
                        }
                    }
                    qnt++;
                }
                soma = k + 1;
            }


            int qntPorto = 0;

            while(qntPorto != 9) {

                for (int a = 0; a < 9; a++) {
                    ArrayList<Integer> dire = new ArrayList<>();
                    int autoPose = posesRand();
                    int dir = direction();
                    dire.add(dir);

                    System.out.println(autoPose);

                    ArrayList v = vizinho.get(autoPose);
                    for (int i = 0; i < v.size(); i++) {

                        System.out.println("pose " + autoPose + " i " + i + " dir " + dir);
                        System.out.println("viz " + v.get(dir));


                        if (v.get(dir) == 0 || v.get(dir) == 8 || v.get(dir) == 9) {
                            System.out.println("válido --> " + v.get(dir));

                            Random autoPorto = new Random();
                            int n;
                            do {
                                n = autoPorto.nextInt(6);
                            } while (portos[n] == 0);
                            portos[n] -= 1;
                            qntPorto++;

                            break;

                        } else {
                            System.out.println("Inválido --> " + v.get(dir));
                            while (dire.contains(dir)) {
                                if (dire.size() == 6) {
                                    break;
                                }
                                dir = direction();
                            }
                            dire.add(dir);
                        }
                    }
                    dire.clear();
                }
            }




//            for (int i = 1; i < vizinho.size(); i++) {
//                ArrayList v = vizinho.get(i);
//                for(int j = 0; j < v.size(); j++){
//
//                    System.out.println(i + " <- id " + j + " vizinhos -> " +v.get(j));
//                }
//            }

            int imge = 10;
            for(int m = 0; m < 3; m++) {
                circle = new Path();
                circle.addCircle(120 * (m + 1), 615, raio, Path.Direction.CW);
                circle.close();
                canvas.drawPath(circle, paint);
                map.put(qnt, circle);
                qnt++;

                switch (m){
                    case 0:
                        canvas.drawBitmap(back, 120 * (m + 1) - raio, 615 - raio, paint);
                        break;
                    case 1:
                        canvas.drawBitmap(refresh, 120 * (m + 1) + 4 - raio, 615 - raio, paint);
                        break;
                    case 2:
//                        canvas.rotate(30);
                        canvas.drawBitmap(next, 120 * (m + 1) + 8 - raio, 620 - raio, paint);
                        break;
                }
                imagens.add(imge);
                imge++;
            }
        } //onDraw
    }
}