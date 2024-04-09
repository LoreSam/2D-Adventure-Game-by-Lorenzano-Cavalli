package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gp;//Giovanni Pantaleo
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[100];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/world01.txt");
    }

    public void getTileImage(){
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass00.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass00.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass00.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass00.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass00.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass00.png"));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass00.png"));

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass00.png"));

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass00.png"));

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass00.png"));

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass00.png"));

            tile[11] = new Tile();
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass01.png"));

            tile[12] = new Tile();
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water00.png"));
            tile[12].collision = true;

            tile[13] = new Tile();
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water01.png"));
            tile[13].collision = true;

            tile[14] = new Tile();
            tile[14].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water02.png"));
            tile[14].collision = true;

            tile[15] = new Tile();
            tile[15].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water03.png"));
            tile[15].collision = true;

            tile[16] = new Tile();
            tile[16].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water04.png"));
            tile[16].collision = true;

            tile[17] = new Tile();
            tile[17].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water05.png"));
            tile[17].collision = true;

            tile[18] = new Tile();
            tile[18].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water06.png"));
            tile[18].collision = true;

            tile[19] = new Tile();
            tile[19].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water07.png"));
            tile[19].collision = true;

            tile[20] = new Tile();
            tile[20].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water08.png"));
            tile[20].collision = true;

            tile[21] = new Tile();
            tile[21].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water09.png"));
            tile[21].collision = true;

            tile[22] = new Tile();
            tile[22].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water10.png"));
            tile[22].collision = true;

            tile[23] = new Tile();
            tile[23].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water11.png"));
            tile[23].collision = true;

            tile[24] = new Tile();
            tile[24].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water12.png"));
            tile[24].collision = true;

            tile[25] = new Tile();
            tile[25].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water13.png"));
            tile[25].collision = true;

            tile[26] = new Tile();
            tile[26].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road00.png"));

            tile[27] = new Tile();
            tile[27].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road01.png"));

            tile[28] = new Tile();
            tile[28].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road02.png"));

            tile[29] = new Tile();
            tile[29].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road03.png"));

            tile[30] = new Tile();
            tile[30].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road04.png"));

            tile[31] = new Tile();
            tile[31].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road05.png"));

            tile[32] = new Tile();
            tile[32].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road06.png"));

            tile[33] = new Tile();
            tile[33].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road07.png"));

            tile[34] = new Tile();
            tile[34].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road08.png"));

            tile[35] = new Tile();
            tile[35].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road09.png"));

            tile[36] = new Tile();
            tile[36].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road10.png"));

            tile[37] = new Tile();
            tile[37].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road11.png"));

            tile[38] = new Tile();
            tile[38].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road12.png"));

            tile[39] = new Tile();
            tile[39].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tile[40] = new Tile();
            tile[40].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[40].collision = true;

            tile[41] = new Tile();
            tile[41].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tile[41].collision = true;

        /*try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/original/pavimento.png")));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/original/p_doccia.png")));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/original/grata.png")));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/original/murov.png")));
            tile[3].collision = true;

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/original/muroo.png")));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/original/angbdx.png")));
            tile[5].collision = true;

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/original/angbsx.png")));
            tile[6].collision = true;

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/original/angtsx.png")));
            tile[7].collision = true;

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/original/angtdx.png")));
            tile[8].collision = true;

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/original/ang_2.png")));
            tile[9].collision = true;

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/original/angd_2.png")));
            tile[10].collision = true;

            tile[11] = new Tile();
            tile[11].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/original/ang_3.png")));
            tile[11].collision = true;

            tile[12] = new Tile();
            tile[12].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/original/angd_3.png")));
            tile[12].collision = true;

            tile[13] = new Tile();
            tile[13].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/original/ang_4.png")));
            tile[13].collision = true;

            tile[14] = new Tile();
            tile[14].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/original/erba.png")));

            tile[15] = new Tile();
            tile[15].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/original/porta_c.png")));
            tile[15].collision = true;*/

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String s){
        try {
            InputStream is = getClass().getResourceAsStream(s);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col=0, row = 0;
            while (col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();
                while (col < gp.maxWorldCol ){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row]=num;
                    col++;
                }
                if (col == gp.maxWorldCol)
                {
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch (IOException e){

        }
    }

    public void draw(Graphics2D g2){

        int worldCol = 0, worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            worldCol++;

            if (worldCol == gp.maxWorldCol){
                worldCol=0;
                worldRow++;
            }
        }

    }
}
