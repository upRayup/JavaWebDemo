package ServletContext;

import utils.DownLoadUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
/*
文件下载需求：
	1. 页面显示超链接
	2. 点击超链接后弹出下载提示框
	3. 完成图片文件下载
 */
@WebServlet("/download_test")
public class download_test extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取文件名
        String filename = request.getParameter("filename");
        ServletContext servletContext = this.getServletContext();

        //在服务器中找这个文件名的真实路径（服务器路径）
        String realPath = servletContext.getRealPath("/img/"+filename);
        //中文路径防止乱码
        String agent = request.getHeader("user-agent");
        filename = DownLoadUtils.getFileName(agent, filename);

        //设置response的响应头
        //设置响应头类型
        String mimeType = servletContext.getMimeType(filename);
        response.setHeader("content-type",mimeType);
        //设置响应头的打开方式
        response.setHeader("content-disposition","attachment;filename="+filename);

        //通过真实路径，把文件加载进内存
        FileInputStream fis=new FileInputStream(realPath);
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] bytes=new byte[1024*8];
        int len=0;
        while((len=fis.read(bytes))!=-1){
            outputStream.write(bytes,0,len);
        }
        fis.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
