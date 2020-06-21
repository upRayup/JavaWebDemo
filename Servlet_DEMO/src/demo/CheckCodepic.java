package demo;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/checkCodepic")
public class CheckCodepic extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int width=100;
        int height=50;
        //创建图片对象
        BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        //美化图片
        //创建画笔
        Graphics g = image.getGraphics();
        //画笔颜色
        g.setColor(Color.pink);
        //画个矩形，填充为粉红色
        g.fillRect(0,0,width,height);
        //给矩形加边框
        g.setColor(Color.blue);
        g.drawRect(0,0,width-1,height-1);
        //写字母或数字
        g.setColor(Color.green);
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random rd=new Random();
        for(int i=1;i<=4;i++){
            int index = rd.nextInt(str.length());
            char c = str.charAt(index);
            g.drawString(c+"",width/5*i,height/2);
        }
        //加干扰线
        g.setColor(Color.blue);
        for(int i=1;i<=10;i++){
            int x1 = rd.nextInt(width);
            int x2 = rd.nextInt(width);
            int y1 = rd.nextInt(height);
            int y2 = rd.nextInt(height);
            g.drawLine(x1,y1,x2,y2);
        }

        //输出展示
        ImageIO.write(image,"jpg",resp.getOutputStream());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
