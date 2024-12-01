package draw;

import java.awt.*;
import java.io.*;
import java.util.Vector;

/**
 * @author dzy
 * @version 1.0
 */
public class Recorder {
    private static int num;
    private static BufferedWriter bufferedWriter = null;
    private static BufferedReader bufferedReader = null;
    private static String Path = "src\\record.txt";
    private static Vector<EnemyTank> enemyTanks = null;
    public static Vector<Node> nodes = new Vector<>();
    public static void Set_enemyTanks(Vector<EnemyTank> enemy_Tanks)
    {
        enemyTanks = enemy_Tanks;
    }

    public static int getNum() {
        return num;
    }
    public static boolean continue_last() // 恢复对局用的 nodes
    {
        File file = new File(Path);
        if(file.exists() == false)
        {
            System.out.println("文件不存在");
            return false;
        }
        try {
            FileReader fileReader = new FileReader(Path);
            bufferedReader = new BufferedReader(fileReader);
            String now = null;
            num = Integer.parseInt(bufferedReader.readLine());
            while((now = bufferedReader.readLine()) != null)
            {
                String[] s = now.split(" ");
                System.out.println(s[0] + " " +s[1] + " " + s[2]);
                nodes.add(new Node(Integer.parseInt(s[0]) , Integer.parseInt(s[1]) , Integer.parseInt(s[2])));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(bufferedReader != null)
                    bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    public static void setNum(int num) {
        Recorder.num = num;
    }
    public static void keepRecord()
    {
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Path) , "utf-8"));
            bufferedWriter.write(num + "");
            bufferedWriter.newLine();
            for(int i = 0 ; i < enemyTanks.size() ; i ++)
            {
                EnemyTank enemyTank = enemyTanks.get(i);
                if(enemyTank.is_live == false)  enemyTanks.remove(enemyTank);
                else
                {
                    bufferedWriter.write(enemyTank.getX() + " " + enemyTank.getY() + " " + enemyTank.getDireact());
                    bufferedWriter.newLine();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
